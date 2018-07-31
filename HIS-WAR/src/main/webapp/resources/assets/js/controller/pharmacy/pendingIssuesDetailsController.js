(function () {
    "use strict";

    function pendingIssuesDetailsController($scope, $rootScope, $http, CONSTANTS, GenericService, $filter, growl, CurrencyService, StatusService, commonDetailService, $state) {
        var self = this;
        var discountLimit = 50;

        //Default Constructor
        function initializeController() {
            $rootScope.loginpage = true;

            self.model = {
                isBatchValid: [],
                isUOMValid: [],
                isQuantityValid: [],
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

            self.model.GetPendingIssuesDetails = GetPendingIssuesDetails;
            self.model.BatchChange = BatchChange;
            self.model.UomChange = UomChange;
            self.model.ApplyDiscount = ApplyDiscount;
            self.model.AddItem = AddItem;
            self.model.RemoveItem = RemoveItem;
            self.model.AllocateQuantity = AllocateQuantity;
            self.model.ValidateBill = ValidateBill;

            self.model.SaveBill = SaveBill;
            self.model.SavePopup = SavePopup;
            self.model.SetDecimal = SetDecimal;
            self.model.ApplyDiscountValidation = ApplyDiscountValidation;
            self.model.NavigateToPendingIssues = NavigateToPendingIssues;

            GetPendingIssuesDetails(commonDetailService.getDataId());
        }


        var SetStatus = function (x) {
            var status = self.model.Status.find(function (obj) {
                return obj.status == x;
            });

            return status.id;
        }

        var GetPendingIssuesDetails = function (issueId) {
          var URI = CONSTANTS.PHARMACY.IP.PENDING_ISSUES.DETAIL_API + issueId;
           
            // self.model.worklistDetails.discountCatgeory = self.model.discountCategoryList[0];

            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    self.model.pendingIssuesDetails = response.data;
                    self.model.pendingIssuesDetails.discountCatgeory = self.model.discountCategoryList[0];
                    self.model.discountCategoryList = self.model.discountCategoryList.concat(response.data.discountCategory);

                    self.model.pendingIssuesDetails['discountType'] = '2';

                    if (self.model.pendingIssuesDetails.items) {
                        self.model.pendingIssuesDetails.items.forEach(function (item) {
                            item.batch.unshift({
                                "batchNo": "Select Batch"
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

        var BatchChange = function (index, bill, batch) {
            var item = bill.items[index];

            item.uom = [{
                uomDetails: "Select UOM",
            }];

            if (!!batch.batchId) {
                item.uom = item.uom.concat(batch.uom);

                item.selectedUom = item.uom.find(function (obj) {
                    return obj.opUom;
                });

                CalculateRate(item, item.selectedUom, item.uom);

                AllocateQuantity(index, bill);
            } else {
                item.selectedUom = item.uom[0];
                item.quantity = null;
                item.rate = 0;
            }
        }

        var UomChange = function (index, bill, uom) {
            var item = bill.items[index];
            if (!!uom) {
                var uomList = item.uom
                CalculateRate(item, uom, uomList);

            } else {
                item.rate = 0;
            }

            AllocateQuantity(index);
        }

        var CalculateRate = function (item, uom, uomList) {
            if (!!uom) {
                var sellingPrice = !!item.selectedBatch ? item.selectedBatch.sellingPrice : 0;

                var conversion = 1;
                var flag = false;

                angular.forEach(uomList, function (value, key) {
                    if (value.uomTypeId == uom.uomTypeId || flag) {
                        conversion = conversion * value.conversion;
                        flag = true;
                    }
                });

                item.rate = conversion * sellingPrice;
            } else {
                item.rate = 0;
            }
        }

        var SetDecimal = function () {
            if (isNaN(self.model.pendingIssuesDetails.discount) || !self.model.pendingIssuesDetails.discount) {
                self.model.pendingIssuesDetails.discount = 0;
            } else {
                self.model.pendingIssuesDetails.discount = parseFloat(self.model.pendingIssuesDetails.discount).toFixed(2);
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
                    if (self.model.pendingIssuesDetails.discount <= discountLimit) {
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

        // var ApplyDiscountValidation = function (bill) {
        //     if (!self.model.pendingIssuesDetails.discount) {
        //         growl.error('Please enter discount amount');
        //     }
        // }

        var ApplyDiscountValidation = function (bill) {
            
            self.model.discountTypeFlag = false
            if (!self.model.pendingIssuesDetails.discount) {
                if (bill.discountType == '1') {
                    growl.error('Please enter % discount');
                }
                if (bill.discountType == '2') {
                    growl.error('Please enter discount amount');
                }
            }
        }

        var AddItem = function (index, bill) {
            bill.items.splice(index + 1, 0, CopyItem(index, bill));
        }

        var CopyItem = function (index, bill) {
            var item = bill.items[index];

            return {
                id: item.id,
                itemCode: item.itemCode,
                itemName: item.itemName,
                genericName: item.genericName,
                manufacturerName: item.manufacturerName,
                quantityOrdered: item.quantityOrdered,
                quantity: item.quantity,
                batch: item.batch,
                selectedBatch: item.batch[0],
                uom: item.uom,
                selectedUom: item.uom[0],
                taxId: item.taxId,
                tax: item.tax,
                taxPercentage: item.taxPercentage,
            }
        }

        var RemoveItem = function (index, bill) {
            var item = bill.items[index];

            if (!!item.allocationId) {
                var URI = CONSTANTS.PHARMACY.OP.WALK_IN_SALES.ALLOCATE_API + bill.id + "/" + item.allocationId;

                $rootScope.startLoader();
                GenericService.serviceAction("DELETE", URI, {}).then(
                    function (response) {
                        if (!!response.data) {
                            bill.items.splice(index, 1);
                        }
                        $rootScope.stopLoader();
                        growl.success(response.data.message);

                    },
                    function (ex) {
                        $rootScope.stopLoader();
                        growl.error('Error while deleting allocated quantity');
                    });
            } else {
                bill.items.splice(index, 1);
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
        }

        var AllocateQuantity = function (index, bill) {
            if (!!bill) {
                var item = bill.items[index];

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
            if (!!item.selectedBatch && !!item.selectedUom &&
                (!!item.quantity) && !(item.prevQuantity == item.quantity && item.quantity == '')) {
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
                    uomType: "Select UOM"
                }];
                item.selectedUom = item.uom[0];
                item.rate = 0;
            }

            item.quantity = item.prevQuantity;

            CalculateRate(item, item.selectedUom, item.uom);
        }

        var SavePopup = function () {
            var popup = angular.element('#confirmSave');
            popup.modal('show');
        }

        var SaveBill = function (bill) {

            if (ValidateBill(bill)) {
                var URI = CONSTANTS.PHARMACY.IP.PENDING_ISSUES.SAVE_API;
                var data = {
                    // "screenId": 1,
                    "prescriptionId": !!bill.id ? bill.id : undefined,
                    "patientId": !!bill.patientId ? bill.patientId : undefined,
                    "doctorId": !!bill.doctorId ? bill.doctorId : undefined,
                    "storeId": !!bill.storeId ? bill.storeId : undefined,
                    "visitType": !!bill.visitType ? bill.visitType : undefined,
                    "encounterId": !!bill.encounterId ? bill.encounterId : undefined,
                    "admissionId": !!bill.admissionId ? bill.admissionId : undefined,
                    "remark":  !!bill.remark ? bill.remark : undefined,
                    "statusId": SetStatus('Pending'),
                    "discountTypeId": !!bill.discountType ? bill.discountType : undefined,
                    "discountCategoryId": !!bill.discountCatgeory.id ? bill.discountCatgeory.id : undefined,
                    "discount": !!bill.discount ? bill.discount : 0,
                    "totalAmount": !!bill.totalAmount ? bill.totalAmount : undefined,
                    "totalDiscountAmount": !!bill.totalDiscountAmount ? bill.totalDiscountAmount : undefined,
                    "netAmount": !!bill.netAmount ? bill.netAmount : undefined,
                    "roundOffAmount": !!bill.roundOff ? bill.roundOff : 0,
                    "taxAmount": !!bill.totalTaxAmount ? bill.totalTaxAmount : 0,
                    // "orderId": !!bill.orderId ? bill.orderId : undefined,
                    "items": []
                }

                angular.forEach(bill.items, function (item, key) {
                    if (!item.disabled) {
                        var billItem = {
                            "prescriptionDetailId": item.id,
                            // "orderDetailId": item.orderDetailId,
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
                });
                console.log(data)

                $rootScope.startLoader();
                GenericService.serviceAction("POST", URI, data).then(
                    function (response) {
                        ClearFields(bill);
                        growl.success('Pending issues bill successffully saved');
                        NavigateToPendingIssues();
                        $rootScope.stopLoader();
                    },
                    function (err) {
                        $rootScope.stopLoader();
                        growl.error('Error while saving pending issues bill');
                    });
            }
        }

        var ClearFields = function (bill) {

            bill.Id = "1" + (new Date()).getTime();
            bill.uhid = null;
            bill.patientDetail = null;
            bill.wardName = null;
            bill.pdd = null;
            bill.doa = null;
            bill.bedNo = null;
            bill.prescriptionDate = null;
            bill.doctorDetail = null;
            bill.discountTypeId = null;
            bill.discountCatgeory = self.model.discountCategoryList[0];
            bill.discount = null;
            bill.totalAmount = null;
            bill.totalDiscountAmount = null;
            bill.taxAmount = null;
            bill.netAmount = null;
            bill.items = [];
        }

        var ValidateBill = function (bill) {

            var isValid = true;

            if (self.model.pendingIssuesDetails.discount) {
                if (!bill.discountCatgeory.id) {
                    isValid = false;
                    self.model.isDiscountCategoryValid = true;
                }

                if (!self.model.isDiscountApplied) {
                    isValid = false;
                    self.model.isDiscountAppliedValid = true;
                    growl.error('Please apply discount on the bill items');
                }

                if (self.model.discountTypeFlag == true) {
                    isValid = false;
                    self.model.isDiscountAppliedValid = true;
                    growl.error('Please apply discount on the bill items');
                }

                if (self.model.isAppliedDiscountValid == true){
                isValid = false;
                growl.error('Please apply discount on the bill items');
                }               
            }

            if (self.model.pendingIssuesDetails.discountCatgeory.id) {
                if (!self.model.pendingIssuesDetails.discount) {
                    isValid = false;
                    self.model.isDiscountAmountValid = true;
                    growl.error('Please enter discount amount');
                }
            }


            if (!bill.items || bill.items.length <= 0) {
                isValid = false;
                growl.error("Please add some items in the bill");
            } else {
                bill.items.forEach(function (billitem) {

                    billitem['isItemValid'] = false;
                    billitem['isBatchValid'] = false;
                    billitem['isUOMValid'] = false;
                    billitem['isQuantityValid'] = false;

                    if (!billitem.selectedBatch.batchId || !billitem.selectedUom.uomUnitId || !billitem.quantity) {

                        billitem['isItemValid'] = true;
                        isValid = false;
                    }

                    if (!billitem.selectedBatch.batchId) {
                        billitem['isBatchValid'] = true;
                        isValid = false;
                    }

                    if (!billitem.selectedUom.uomUnitId) {
                        billitem['isUOMValid'] = true;
                        isValid = false;
                    }

                    if ((!billitem.quantity) || (billitem.quantityOrdered < billitem.quantity)) {

                        billitem['isQuantityValid'] = true;
                        isValid = false;
                        growl.error('Enter valid quantity')
                    }
                });
            }

            return isValid;
        }

        var NavigateToPendingIssues = function () {
            document.getElementById('popupClose').click();
            setTimeout(function () {
                $state.go('pendingIssues');
            }, 1000);
        }

        initializeController();
    }

    function config($stateProvider) {
        $stateProvider.state("pendingIssuesDetails", {
            url: "/pendingIssuesDetails",
            templateUrl: 'views/PharmacyInventory/InPatient/pending-issues-details.html',
            controller: "PendingIssuesDetails.Controller",
            controllerAs: "vm"
        });
    }

    angular
        .module("myApp")
        .config(config)
        .controller("PendingIssuesDetails.Controller", pendingIssuesDetailsController)
})();