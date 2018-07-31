(function () {
    "use strict";

    function outPatientSalesWorklistDetailsController($scope, $rootScope, $http, CONSTANTS, GenericService, $filter, growl, CurrencyService, StatusService, salesWorklistService, $state) {
        var self = this;
      
        var discountLimit = 50;

        //Default Constructor
        function initializeController() {
            $rootScope.loginpage = true;

            self.model = {
                worklistDetails: {
                    id: "1" + (new Date()).getTime(),
                    items: [],
                },

                isBatchValid: [],
                isUOMValid: [],
                isQuantityValid: [],
                isDespensedQuantityValid: [],
                SelectedItems: [],
                discountCategoryList: [{
                    "discountCategory": "Select Discount Category"
                }]

            };

            //Methods
            CurrencyService.GetCurrency().then(function (currency) {
                self.model.Currency = currency;
            })
            StatusService.GetStatus().then(function (status) {
                self.model.Status = status;
            })

            self.model.GetWorklistDetails = GetWorklistDetails;
            self.model.BatchChange = BatchChange;
            self.model.UomChange = UomChange;
            self.model.ApplyDiscount = ApplyDiscount;
            self.model.AddItem = AddItem;
            self.model.RemoveItem = RemoveItem;
            self.model.AllocateQuantity = AllocateQuantity;
            self.model.ValidateBill = ValidateBill;
            self.model.SubstituteItems = SubstituteItems;
            self.model.SelectAllItems = SelectAllItems;
            self.model.SelectSubstituteItem = SelectSubstituteItem;
            self.model.Add = Add;
            self.model.AddSubstituteItem = AddSubstituteItem;
            self.model.RemoveSubstituteItem = RemoveSubstituteItem;
            self.model.selectedQuantity = 0;
            self.model.selectedItemId = 0;

            self.model.SaveBill = SaveBill;
            self.model.SavePopup = SavePopup;
            self.model.SetDecimal = SetDecimal;
            self.model.ApplyDiscountValidation = ApplyDiscountValidation;
            self.model.NavigatetoSalesWorklist = NavigatetoSalesWorklist;
            self.model.RestorePreviousItem = RestorePreviousItem;


            GetWorklistDetails(salesWorklistService.getWorklistId());
        }


        var SetStatus = function (x) {
            var status = self.model.Status.find(function (obj) {
                return obj.status == x;
            });

            return status.id;
        }

        var GetWorklistDetails = function (issueId) {
            var URI = CONSTANTS.PHARMACY.OP.SALES_WORKLIST.DETAILS_API + issueId;

            self.model.worklistDetails.discountCatgeory = self.model.discountCategoryList[0];

            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    $rootScope.stopLoader();
                    self.model.worklistDetails = response.data;
                    self.model.worklistDetails.discountCatgeory = self.model.discountCategoryList[0];
                    self.model.discountCategoryList = self.model.discountCategoryList.concat(response.data.discountCategory);

                    self.model.worklistDetails['discountType'] = '2';

                    if (self.model.worklistDetails.items) {
                        self.model.worklistDetails.items.forEach(function (item) {
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
                },
                function (err) {
                    $rootScope.stopLoader();
                });
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

                if (!item.selectedUom) {
                    item.selectedUom = item.uom[0];
                }

                CalculateRate(item, item.selectedUom, item.uom);

                AllocateQuantity(index, bill, parentIndex);
            } else {
                item.uom = [{
                    uomDetails: "Select UOM",
                }];
                item.selectedUom = item.uom[0];
                item.quantity = null;
                item.rate = 0;
            }
        }

        var UomChange = function (index, bill, uom, parentIndex) {
            var item = null;
            if (parentIndex == null) {
                item = bill.items[index];
            } else {
                item = bill.items[parentIndex].selectedItems[index];
            }

            if (!!uom) {
                var uomList = item.uom
                CalculateRate(item, uom, uomList);

            } else {
                item.rate = "";
            }
            AllocateQuantity(index, bill, parentIndex);
        }

        var CalculateRate = function (item, uom, uomList) {

            var sellingPrice = !!item.selectedBatch ? item.selectedBatch.sellingPrice : 0;

            if (!!uom) {
                var pUom = $filter('filter')(item.uom, {
                    uomTypeId: 1
                }, true)[0];

                var sUom = $filter('filter')(item.uom, {
                    uomTypeId: 2
                }, true)[0];

                var dUom = $filter('filter')(item.uom, {
                    uomTypeId: 3
                }, true)[0];

                if (uom.uomTypeId == 1) {
                    item.rate = pUom.conversion * sUom.conversion * dUom.conversion * sellingPrice;
                } else if (uom.uomTypeId == 2) {
                    item.rate = sUom.conversion * dUom.conversion * sellingPrice;
                } else if (uom.uomTypeId == 3) {
                    item.rate = dUom.conversion * sellingPrice;
                }
            } else {
                item.rate = 0;
            }

        }

        var SetDecimal = function () {
            if (isNaN(self.model.worklistDetails.discount) || !self.model.worklistDetails.discount) {
                self.model.worklistDetails.discount = 0;
            } else {
                self.model.worklistDetails.discount = parseFloat(self.model.worklistDetails.discount).toFixed(2);
            }
        }

        var ApplyDiscount = function (bill) {
            var discount = parseFloat(bill.discount);
            if (!isNaN(discount)) {
                if (bill.discountType == '2') {
                    if (discount < bill.totalAmount) {
                        var itemDiscount = discount / bill.totalAmount;

                        angular.forEach(bill.items, function (value, key) {
                            if (value.itemtotalAmount > itemDiscount) {
                                value.itemDiscount = value.itemtotalAmount * itemDiscount;
                            } else {
                                value.itemDiscount = 0;
                            }

                            var unitDiscountAmount = itemDiscount / value.quantity;
                            bill.items[key]['unitDiscountAmount'] = unitDiscountAmount;

                            angular.forEach(value.selectedItems, function (subvalue, subkey) {
                                if (subvalue.itemtotalAmount > itemDiscount) {
                                    subvalue.itemDiscount = subvalue.itemtotalAmount * itemDiscount;
                                } else {
                                    subvalue.itemDiscount = 0;
                                }

                                var unitDiscountAmount = itemDiscount / subvalue.quantity;
                                bill.items[key].selectedItems[subkey]['unitDiscountAmount'] = unitDiscountAmount;
                            });
                        });
                    } else {
                        growl.error('Please enter discount amount less than Total Amount');
                    }
                } else if (bill.discountType == '1') {
                    if (self.model.worklistDetails.discount <= discountLimit) {
                        angular.forEach(bill.items, function (value, key) {
                            value.itemDiscount = value.itemtotalAmount * discount * 0.01;

                            var unitDiscountAmount = value.itemDiscount / value.quantity;
                            bill.items[key]['unitDiscountAmount'] = unitDiscountAmount;

                            angular.forEach(value.selectedItems, function (subvalue, subkey) {
                                subvalue.itemDiscount = subvalue.itemtotalAmount * discount * 0.01;

                                var unitDiscountAmount = subvalue.itemDiscount / subvalue.quantity;
                                bill.items[key].selectedItems[subkey]['unitDiscountAmount'] = unitDiscountAmount;

                            });
                        });
                    } else {
                        growl.error('Please enter discount amount less than ' + discountLimit + '%');
                    }
                }
            }

        }

        var ApplyDiscountValidation = function (bill) {
            if (!self.model.worklistDetails.discount) {
                growl.error('Please enter discount amount');
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
                manufacturerName: item.manufacturerName,
                batch: item.batch,
                selectedBatch: item.batch[0],
                uom: item.uom,
                selectedUom: item.uom[0],
                taxId: item.taxId,
                tax: item.tax,
                taxPercentage: item.taxPercentage,
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
                // item['disabled'] = false;
            }

            if (!!item.allocationId) {
                var URI = CONSTANTS.PHARMACY.OP.WALK_IN_SALES.ALLOCATE_API + bill.id + "/" + item.allocationId;

                $rootScope.startLoader();
                GenericService.serviceAction("DELETE", URI, {}).then(
                    function (response) {
                        if (!!response.data) {
                            if (parentIndex == null) {
                                bill.items.splice(index, 1);
                            } else {
                                bill.items[parentIndex].selectedItems.splice(index, 1);;
                            }
                        }
                        $rootScope.stopLoader();
                        growl.success(response.data.message);

                    },
                    function (ex) {
                        $rootScope.stopLoader();
                        growl.error('Error while deleting allocated quantity');
                    });
            } else {
                //bill.items.splice(index, 1);
                if (parentIndex == null) {
                    bill.items.splice(index, 1);
                } else {
                    bill.items[parentIndex].selectedItems.splice(index, 1);;
                }
            }
        }

        var CalculateQuantity = function (item, uom, uomList) {
            if (!!uom) {
                var pUom = $filter('filter')(item.uom, {
                    uomTypeId: 1
                }, true)[0];

                var sUom = $filter('filter')(item.uom, {
                    uomTypeId: 2
                }, true)[0];

                var dUom = $filter('filter')(item.uom, {
                    uomTypeId: 3
                }, true)[0];

                if (uom.uomTypeId == 1) {
                    item.leaseQuantity = pUom.conversion * sUom.conversion * dUom.conversion * item.quantity;
                } else if (uom.uomTypeId == 2) {
                    item.leaseQuantity = sUom.conversion * dUom.conversion * item.quantity;
                } else if (uom.uomTypeId == 3) {
                    item.leaseQuantity = dUom.conversion * item.quantity;
                }
            } else {
                item.leaseQuantity = 0;
            }
            // console.log(item.leaseQuantity);
        }

        var AllocateQuantity = function (index, bill, parentIndex) {
            if (!!bill) {
                // var item = bill.items[index];
                var item = null;
                if (parentIndex == null) {
                    item = bill.items[index];
                } else {
                    item = bill.items[parentIndex].selectedItems[index];
                }

                CalculateQuantity(item, item.selectedUom, item.uom);

                if (CanAllocate(item)) {
                    var URI = CONSTANTS.PHARMACY.OP.WALK_IN_SALES.ALLOCATE_API + bill.id + "/" + (!!item.allocationId ? item.allocationId : "");
                    var data = {
                        "batchId": item.selectedBatch.batchId,
                        "quantity": item.leaseQuantity
                    };

                    $rootScope.startLoader();
                    GenericService.serviceAction("PUT", URI, data).then(
                        function (response) {
                            if (!!response.data) {
                                item.allocationId = response.data.id;
                                StorePreviousValue(item);
                            }
                            $rootScope.stopLoader();
                        },
                        function (ex) {

                            growl.error('No enough quantity');
                            RestorePreviousValue(item);
                            $rootScope.stopLoader();
                        });
                } else {
                    StorePreviousValue(item);
                }
            }
        }

        var CanAllocate = function (item) {
            
            if (!!item.selectedBatch && !!item.selectedUom.uomUnitId && (!!item.quantity) && !(item.prevQuantity == item.quantity && item.quantity == '')) {
                return true;
            }

            return false;
        }

        var StorePreviousValue = function (item) {
            item.prevBatch = item.selectedBatch;
            item.prevUom = item.selectedUom;
            item.prevQuantity = item.quantity;

            CalculateRate(item, item.selectedUom, item.uom);
        }

        var RestorePreviousValue = function (item) {
            item.selectedBatch = item.prevBatch;

            if (!!item.selectedBatch) {
                item.selectedUom = item.prevUom;
            } else {
                item.uom = [{
                    uomDetails: "Select UOM"
                }];
                item.selectedUom = item.uom[0];
                item.rate = 0;
            }

            item.quantity = item.prevQuantity;

            CalculateRate(item, item.selectedUom, item.uom);
        }

        var SubstituteItems = function (item) {
            self.model.substituteDetails = [];
            self.model.selectedQuantity = item.qtyPrescribed;
            self.model.selectedItemId = item.id;
            var URI = CONSTANTS.PHARMACY.OP.SALES_WORKLIST.SUBSTITUTE_API + item.genericId;

            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    $rootScope.stopLoader();
                    self.model.substituteDetails = response.data;
                    if (!self.model.substituteDetails.items || self.model.substituteDetails.items == []) {
                        return;
                    } else {
                        self.model.substituteDetails.items.forEach(function (item) {
                            self.model.selectAllRows = false;
                            item.isSelected = false;
                            item.batch.unshift({
                                "batchDetails": "Select Batch"
                            });
                            item.uom = [{
                                "uomDetails": "Select UOM"
                            }];
                            item.selectedBatch = item.batch[0];
                            item.selectedUom = item.uom[0];
                        });
                    }
                },
                function (err) {
                    $rootScope.stopLoader();
                });

        }

        var SelectAllItems = function () {
            if (self.model.selectAllRows) {
                self.model.SelectedItems = [];
                self.model.substituteDetails.items.forEach(function (item) {

                    item.isSelected = true;
                    self.model.SelectedItems.push(item);

                });
            } else {
                self.model.SelectedItems = [];
                self.model.substituteDetails.items.forEach(function (item) {
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

            if (self.model.substituteDetails.items.every(isSelected)) {
                self.model.selectAllRows = true;
            } else {
                self.model.selectAllRows = false;
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

        var Add = function () {
            if (!self.model.SelectedItems || self.model.SelectedItems == undefined || self.model.SelectedItems == '') {
                growl.error('Please select items')
                return false;
            } else {
                self.model.SelectedItems.forEach(function (selectedItem) {
                    selectedItem['qtyPrescribed'] = self.model.selectedQuantity;
                    selectedItem['substituteBtn'] = true;
                    selectedItem['parentItemId'] = self.model.selectedItemId;
                    selectedItem['selectedUom'] = selectedItem.selectedUom;                    
                    selectedItem['uom'] = [];
                });

                self.model.worklistDetails.items.forEach(function (list) {
                    self.model.SelectedItems.forEach(function (item) {
                       
                        item.uom = [{
                            uomDetails: "Select UOM",
                        }];
                        item.uom = item.selectedBatch.uom;
                        item.selectedUom = $filter('filter')(item.selectedBatch.uom, {
                            opUom: true
                        }, true)[0];
                        if(!item.selectedUom) item.selectedUom = "";
                    });

                    if (self.model.selectedItemId == list.id) {
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

        var AddSubstituteItem = function (index, bill) {
            bill.items.splice(index + 1, 0, CopySubstituteItem(index, bill));
        }

        var CopySubstituteItem = function (index, bill) {
            var item = bill.items[index];
            return {
                itemCode: item.itemCode,
                itemName: item.itemName,
                genericName: item.genericName,
                manufacturerName: item.manufacturerName,
                batch: item.batch,
                selectedBatch: item.batch[0],
                uom: item.uom,
                selectedUom: item.uom[0],
                taxId: item.taxId,
                tax: item.tax,
                taxPercentage: item.taxPercentage,
                disabled: item.disabled
            }
        }

        var RemoveSubstituteItem = function (index, bill) {
            bill.items.splice(index, 1);
        }

        var SavePopup = function () {
            var popup = angular.element('#confirmSave');
            popup.modal('show');
        }

        var SaveBill = function (bill) {

            if (ValidateBill(bill)) {
                var URI = CONSTANTS.PHARMACY.OP.WALK_IN_SALES.SAVE_API;
                var data = {
                    "screenId": 1,
                    "patientId": !!bill.patientId ? bill.patientId : undefined,
                    "doctorId": !!bill.doctorId ? bill.doctorId : undefined,
                    "discountTypeId": !!bill.discountType ? bill.discountType : undefined,
                    "discountCatgeoryId": !!bill.discountCatgeory.id ? bill.discountCatgeory.id : undefined,
                    "discount": !!bill.discount ? bill.discount : 0,
                    "totalAmount": !!bill.totalAmount ? bill.totalAmount : undefined,
                    "discountAmount": !!bill.totalDiscountAmount ? bill.totalDiscountAmount : undefined,
                    "netAmount": !!bill.netAmount ? bill.netAmount : undefined,
                    "roundOffAmount": !!bill.roundOff ? bill.roundOff : 0,
                    "taxAmount": !!bill.totalTaxAmount ? bill.totalTaxAmount : 0,
                    "saleTypeId": 2,
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
                            "rate": item.rate,
                            "quantity": Number(item.quantity),
                            "leaseRate": item.selectedBatch.sellingPrice,
                            "leaseQuantity": item.leaseQuantity,
                            "totalAmount": item.itemtotalAmount,
                            "discountAmount": item.itemDiscount,
                            "unitDiscountAmount": item.unitDiscountAmount,
                            "netAmount": item.netAmount,
                            "taxId": item.taxId,
                            "taxPercentage": item.taxPercentage,
                            "taxAmount": item.taxAmount
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
                                "rate": item.rate,
                                "quantity": Number(item.quantity),
                                "leaseRate": item.selectedBatch.sellingPrice,
                                "leaseQuantity": item.leaseQuantity,
                                "totalAmount": item.itemtotalAmount,
                                "discountAmount": item.itemDiscount,
                                "unitDiscountAmount": item.unitDiscountAmount,
                                "netAmount": item.netAmount,
                                "taxId": item.taxId,
                                "taxPercentage": item.taxPercentage,
                                "taxAmount": item.taxAmount
                            }
                            data.items.push(billSubItem);
                        });
                    }
                });

                $rootScope.startLoader();
                GenericService.serviceAction("POST", URI, data).then(
                    function (response) {
                        ClearFields(bill);
                        growl.success('Sales worklist bill successffully saved');
                        $rootScope.stopLoader();
                        $state.go('outPatientSalesWorklist');
                    },
                    function (err) {
                        $rootScope.stopLoader();
                        growl.error('Error while saving sales worklist bill');
                    });
            }
        }

        var ClearFields = function (bill) {

            bill.Id = "1" + (new Date()).getTime();
            bill.uhid = null;
            bill.patientDetail = null;
            bill.wardName = null;
            bill.bedNo = null;
            bill.prescriptionDate = null;
            bill.doctorDetail = null;
            bill.discountTypeId = null;
            bill.discountCatgeory = self.model.discountCategoryList[0];
            bill.discount = null;
            bill.itemtotalAmount = null;
            bill.totalDiscountAmount = null;
            bill.taxAmount = null;
            bill.netAmount = null;
            bill.items = [];
        }

        var ValidateBill = function (bill) {

            var isValid = true;

            if (self.model.worklistDetails.discount) {
                if (!bill.discountCatgeory.id) {
                    isValid = false;
                    self.model.isDiscountCategoryValid = true;
                }

                if (!self.model.isDiscountApplied) {
                    growl.error('Please apply discount on the bill items');
                }
            }


            if (!bill.items || bill.items.length <= 0) {
                isValid = false;
                growl.error("Please add some items in the bill");
            } else {
                bill.items.forEach(function (billitem) {

                    var totalQtyOfSelectedItems = 0;
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
                        if ((!billitem.quantity) || (billitem.qtyPrescribed < billitem.quantity)) {

                            billitem['isParentQuantityValid'] = true;
                            isValid = false;
                            growl.error('Enter valid quantity')
                        }

                    }
                });
            }

            return isValid;
        }

        var NavigatetoSalesWorklist = function () {
            $state.go('outPatientSalesWorklist');
        }

        initializeController();
    }

    function config($stateProvider) {
        $stateProvider.state("outPatientSalesWorklistDetails", {
            url: "/outPatientSalesWorklistDetails",
            templateUrl: 'views/PharmacyInventory/InPatient/out-patient-sales-worklist-details.html',
            controller: "OutPatientSalesWorklistDetails.Controller",
            controllerAs: "vm"
        });
    }

    angular
        .module("myApp")
        .config(config)
        .controller("OutPatientSalesWorklistDetails.Controller", outPatientSalesWorklistDetailsController)
})();