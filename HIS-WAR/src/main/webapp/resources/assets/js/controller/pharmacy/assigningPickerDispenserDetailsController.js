(function () {
    "use strict";

    function assigningPickerDispenserDetailsController($scope, $rootScope, $http, CONSTANTS, GenericService, $filter, $state, growl, CurrencyService, StatusService, assigningPickerDispenserService) {
        var self = this;
       
        //Default Constructor
        function initializeController() {
            $rootScope.loginpage = true;


            self.model = {
                SelectedItems: [],
            };

            //Methods
            CurrencyService.GetCurrency().then(function (currency) {
                self.model.Currency = currency;
            })
            StatusService.GetStatus().then(function (status) {
                self.model.Status = status;
            })

            self.model.AssigningPickerDispenserDetails = AssigningPickerDispenserDetails;
            self.model.BatchChange = BatchChange;
            self.model.AddItem = AddItem;
            self.model.RemoveItem = RemoveItem;
            self.model.SubstituteItems = SubstituteItems;
            self.model.SelectAllItems = SelectAllItems;
            self.model.SelectSubstituteItem = SelectSubstituteItem;
            self.model.Add = Add;
            self.model.RestorePreviousItem = RestorePreviousItem;
            self.model.AddSubstituteItem = AddSubstituteItem;
            self.model.RemoveSubstituteItem = RemoveSubstituteItem;
            self.model.SavePopup = SavePopup;
            self.model.SaveBill = SaveBill;
            self.model.ClearFields = ClearFields;
            self.model.selectedQuantity = 0;

            AssigningPickerDispenserDetails(assigningPickerDispenserService.getAssigningPickerDispenserId());
        }

        var AssigningPickerDispenserDetails = function (itemId) {
            var URI = CONSTANTS.PHARMACY.OP.ASSIGN_PICKER_DISPENSER.DETAIL_API + itemId;

            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {

                    self.model.assigningPickerDispenserDetails = response.data;

                    if (self.model.assigningPickerDispenserDetails.items) {
                        self.model.assigningPickerDispenserDetails.items.forEach(function (item) {
                            item.batch.unshift({
                                "batchDetails": "Select Batch"
                            });
                            item.uom = [{
                                    "uomDetails": "Select UOM"
                                }],
                                item.selectedBatch = item.batch[0];
                            item.selectedUom = item.uom[0];
                        });
                    }

                });
            $rootScope.stopLoader();
        }

        var BatchChange = function (index, bill, batch, parentIndex) {
            var item = null;
            if (parentIndex == null) {
                bill.items[index].uom = batch.uom;
                item = bill.items[index];
            } else {
                bill.items[parentIndex].selectedItems[index].uom = batch.uom;
                item = bill.items[parentIndex].selectedItems[index];
            }

            item.uom = [{
                uomDetails: "Select UOM",
            }];

            if (!!batch.batchId) {
                item.uom = item.uom.concat(batch.uom);

                item.selectedUom = $filter('filter')(item.uom, {
                    opUom: true
                }, true)[0];

            } else {
                item.uom = [{
                    uomDetails: "Select UOM",
                }];
                item.selectedUom = item.uom[0];
            }
        }

        var AddItem = function (index, bill, parentIndex) {
            //bill.items.splice(index + 1, 0, CopyItem(index, bill, parentIndex));
            if (parentIndex == null) {
                bill.items.splice(index + 1, 0, CopyItem(index, bill, parentIndex));
            } else {
                bill.items[parentIndex].selectedItems.splice(index + 1, 0, CopyItem(index, bill, parentIndex));
                //item = bill.items[parentIndex].selectedItems[index];
            }
        }

        var CopyItem = function (index, bill, parentIndex) {
            // var item = bill.items[index];
            var item = null;
            if (parentIndex == null) {
                item = bill.items[index];
            } else {
                item = bill.items[parentIndex].selectedItems[index];
            }

            return {
                id: item.id,
                itemCode: item.itemCode,
                itemName: item.itemName,
                genericName: item.genericName,
                quantityOrdered: item.quantityOrdered,
                batch: item.batch,
                selectedBatch: item.batch[0],
                uom: item.uom,
                selectedUom: item.uom[0],
                stockAvailable: item.stockAvailable,
                rackNo: item.rackNo,
                picker: item.picker,
                substituteBtn: (parentIndex == null ? false : true)
            }
        }

        var RemoveItem = function (index, bill, parentIndex) {
            // var item = bill.items[index];
            var item = null;
            if (parentIndex == null) {
                item = bill.items[index];
            } else {
                item = bill.items[parentIndex].selectedItems[index];
            }

            //bill.items.splice(index, 1);
            if (parentIndex == null) {
                bill.items.splice(index, 1);
            } else {
                bill.items[parentIndex].selectedItems.splice(index, 1);;
            }

        }


        var SubstituteItems = function (item) {
            self.model.selectedItemId = item.genericId;
            self.model.itemName = item.itemName;
            self.model.genericName = item.genericName;
            self.model.dose = item.dose;
            self.model.selectedQuantity = item.quantityOrdered;
            self.model.prescriptionDetailId = item.id;
            self.model.frequency = item.frequency;
            self.model.dose = item.dose;
            self.model.startDate = item.startDate;
            self.model.stopDate = item.stopDate;
            self.model.substituteDetails = [];
            
            var URI = CONSTANTS.PHARMACY.OP.ASSIGN_PICKER_DISPENSER.SUBSTITUTE_API + item.genericId;

            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    self.model.substituteDetails = response.data;
                  
                    if (!self.model.substituteDetails || self.model.substituteDetails == []) {
                        return;
                    } else {
                        self.model.substituteDetails.forEach(function (item) {
                            self.model.selectAllRows = false;
                            item.isSelected = false;
                            item.batch.unshift({
                                "batchDetails": "Select Batch"
                            });
                            item.uom = [{
                                    "uomDetails": "Select UOM"
                                }],

                                item.selectedBatch = item.batch[0];
                            item.selectedUom = item.uom[0];
                        });
                    }
                });

        }

        var SelectAllItems = function () {
            if (self.model.selectAllRows) {
                self.model.SelectedItems = [];
                self.model.substituteDetails.forEach(function (item) {

                    item.isSelected = true;
                    self.model.SelectedItems.push(item);

                });
            } else {
                self.model.SelectedItems = [];
                self.model.substituteDetails.forEach(function (item) {
                    item.isSelected = false;
                });
            }
        }

        var isSelected = function (item) {
            return item.isSelected;
        }

        var SelectSubstituteItem = function (item) {
            var itemIds = self.model.SelectedItems;

            if (item.isSelected) {
                itemIds.push(item);
            } else {
                var index = itemIds.indexOf(item);
                if (index > -1) {
                    itemIds.splice(index, 1);
                }
            }

            if (self.model.substituteDetails.every(isSelected)) {
                self.model.selectAllRows = true;
            } else {
                self.model.selectAllRows = false;
            }
        }

        var Add = function () {
            if (!self.model.SelectedItems || self.model.SelectedItems == undefined || self.model.SelectedItems == '') {
                growl.error('Please select items')
                return false;
            } else {

                self.model.SelectedItems.forEach(function (selectedItem) {
                    // selectedItem['itemName'] = self.model.itemName;
                    // selectedItem['genericName'] = self.model.genericName;
                    selectedItem['dose'] = self.model.dose;
                    selectedItem['quantityOrdered'] = self.model.selectedQuantity;
                    // selectedItem['picker'] = self.model.picker;
                    selectedItem['id'] = self.model.prescriptionDetailId;
                    selectedItem['substituteBtn'] = true;
                    selectedItem['parentItemId'] = self.model.selectedItemId;
                    selectedItem['selectedUom'] = selectedItem.selectedUom;
                    selectedItem['frequency'] = self.model.frequency;
                    selectedItem['stopDate'] = self.model.stopDate;
                    selectedItem['startDate'] = self.model.startDate;
                     

                    // selectedItem.selectedUom = [{
                    //     uomDetails: "Select UOM"
                    // }];
                    // selectedItem.selectedUom = selectedItem.selectedUom[0];
                });

                self.model.assigningPickerDispenserDetails.items.forEach(function (list) {
                    if (self.model.selectedItemId == list.genericId) {
                        list['disabled'] = true;
                        if (!list['selectedItems'])
                            list['selectedItems'] = self.model.SelectedItems;
                        else {
                            list['selectedItems'] = list['selectedItems'].concat(self.model.SelectedItems);
                        }
                        list.selectedUom = list.selectedUom[0];
                        list.quantity = null;
                    }

                });

                self.model.SelectedItems = [];
            }
        }

        var RestorePreviousItem = function (bill) {

            bill.items.forEach(function (item) {
                if (item.selectedItems) {
                    if (item.selectedItems.length == 0) {
                        item['disabled'] = false;
                    }
                }
            });
        }

        var AddSubstituteItem = function (index, bill) {
            bill.splice(index + 1, 0, CopySubstituteItem(index, bill));
        }

        var CopySubstituteItem = function (index, bill) {
            var item = bill[index];
            return {
                itemCode: item.itemCode,
                itemName: item.itemName,
                genericName: item.genericName,
                // manufacturerName: item.manufacturerName,
                batch: item.batch,
                selectedBatch: item.batch[0],
                uom: item.uom,
                selectedUom: item.uom[0],
                stockAvailable: item.stockAvailable,
                rackNo: item.rackNo,
                picker: item.picker,
                disabled: item.disabled
            }
        }

        var RemoveSubstituteItem = function (index, bill) {
            bill.splice(index, 1);
        }

        var SetStatus = function (x) {
            var status = self.model.Status.find(function (obj) {
                return obj.status == x;
            });

            return status.id;
        }

        var SavePopup = function () {
            var popup = angular.element('#confirmSave');
            popup.modal('show');
        }

        var SaveBill = function (bill) {
            // var SalesWorklistBill = [];

            if (ValidateBill(bill)) {
                var URI = CONSTANTS.PHARMACY.OP.ASSIGN_PICKER_DISPENSER.SAVE_API;

                var data = {
                    "prescriptionId": !!bill.id ? bill.id : undefined,
                    "pickerId": !!bill.pickerId ? bill.pickerId : undefined,
                    "statusId": SetStatus('Pending'),
                    "items": []
                }

                angular.forEach(bill.items, function (item, key) {
                    if (!item.disabled) {
                        var billItem = {
                            "prescriptionDetailId": item.id,
                            "batchId": item.selectedBatch.batchId,
                            "uomTypeId": item.selectedUom.uomTypeId,
                            "uomUnitId": item.selectedUom.uomUnitId,
                            "quanityIssued": Number(item.quantity)
                        }
                        data.items.push(billItem);
                    }

                    if (item.selectedItems) {
                        angular.forEach(item.selectedItems, function (item, key) {
                            var billSubItem = {
                                "prescriptionDetailId": item.id,
                                "batchId": item.selectedBatch.batchId,
                                "uomTypeId": item.selectedUom.uomTypeId,
                                "uomUnitId": item.selectedUom.uomUnitId,
                                "quanityIssued": Number(item.quantity)
                            }
                            data.items.push(billSubItem);
                        });
                    }
                });
                console.log(data)

                $rootScope.startLoader();
                GenericService.serviceAction("POST", URI, data).then(
                    function (response) {
                        ClearFields(bill);
                        growl.success('Assigning picker dispenser successffully saved');
                        $rootScope.stopLoader();
                        NavigateToAssigningPickerDispenser();
                    },
                    function (err) {
                        $rootScope.stopLoader();
                        growl.error('Error while saving assigning picker dispenser');
                    });
            }
        }

        var NavigateToAssigningPickerDispenser = function () {
            document.getElementById('popupClose').click();
            setTimeout(function () {
                $state.go('assigningPickerDispenser');
            }, 1000);
        }

        var ClearFields = function (bill) {

            bill.Id = "1" + (new Date()).getTime();
            bill.uhid = null;
            bill.patientDetail = null;
            bill.dob = null;
            bill.pdd = null;
            bill.doa = null;
            bill.wardName = null;
            bill.bedNo = null;
            bill.prescriptionDate = null;
            bill.doctorDetail = null;
            bill.prescriptionId = null;
            bill.pickerId = null;
           
            bill.items = [];
        }

        var ValidateBill = function (bill) {

            var isValid = true;

            if (!bill.items || bill.items.length <= 0) {
                isValid = false;
                growl.error("Please add some items in the bill");
            } else {
                bill.items.forEach(function (billitem) {

                    var totalQtyOfSelectedItems = 0;


                    if (!billitem.disabled) {
                        billitem['isParentItemValid'] = false;
                        billitem['isParentBatchValid'] = false;
                        billitem['isParentUOMValid'] = false;
                        billitem['isParentQuantityValid'] = false;

                        if (!billitem.selectedBatch.batchId || !billitem.selectedUom.uomUnitId || !billitem.quantity) {

                            billitem['isParentItemValid'] = true;
                            isValid = false;
                        }

                        if (!billitem.selectedBatch.batchId) {
                            billitem['isParentBatchValid'] = true;
                            isValid = false;
                        }

                        if (!billitem.selectedUom.uomUnitId) {
                            billitem['isParentUOMValid'] = true;
                            isValid = false;

                        }
                        if ((!billitem.quantity) || (billitem.quantityOrdered < billitem.quantity)) {

                            billitem['isParentQuantityValid'] = true;
                            isValid = false;
                            growl.error('Enter valid quantity')
                        }

                    }

                    if (billitem.selectedItems) {
                        billitem.selectedItems.forEach(function (item) {

                            item['isItemValid'] = false;
                            item['isBatchValid'] = false;
                            item['isUOMValid'] = false;
                            item['isQuantityValid'] = false;

                            if (!item.selectedBatch.batchId || !item.selectedUom.uomUnitId || !item.quantity) {
                                item['isItemValid'] = true;
                                isValid = false;
                            }
                            if (!item.selectedBatch.batchId) {
                                item['isBatchValid'] = true;
                                isValid = false;
                            }
                            if (!item.selectedUom.uomUnitId) {
                                item['isUOMValid'] = true;
                                isValid = false;
                            }

                            totalQtyOfSelectedItems = totalQtyOfSelectedItems + parseInt(item.quantity);
                            if (!item.quantity) {
                                // totalQtyOfSelectedItems = totalQtyOfSelectedItems + parseInt(item.quantity);
                                item['isQuantityValid'] = true;
                                isValid = false;
                                growl.error('Enter valid quantity')
                            }
                        });

                        if (self.model.selectedQuantity < totalQtyOfSelectedItems) {
                            billitem.selectedItems.forEach(function (item) {
                                item['isQuantityValid'] = true;
                                growl.error('Enter valid quantity')
                            });
                        } else {
                            billitem.selectedItems.forEach(function (item) {
                                item['isQuantityValid'] = false;
                            });
                        }
                    }


                });
            }

            return isValid;
        }


        initializeController();
    }

    function config($stateProvider) {
        $stateProvider.state("assigningPickerDispenserDetails", {
            url: "/assigningPickerDispenserDetails",
            templateUrl: 'views/PharmacyInventory/InPatient/assigning-picker-dispenser-details.html',
            controller: "AssigningPickerDispenserDetails.Controller",
            controllerAs: "vm"
        });
    }

    angular
        .module("myApp")
        .config(config)
        .controller("AssigningPickerDispenserDetails.Controller", assigningPickerDispenserDetailsController)
})();