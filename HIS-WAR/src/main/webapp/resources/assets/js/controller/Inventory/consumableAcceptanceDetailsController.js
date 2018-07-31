(function () {
    "use strict";

    function consumableAcceptanceDetailsController($scope, $rootScope, $http, StatusService, $state, commonDetailService, rejectReasonService, CONSTANTS, GenericService, growl) {
        var self = this;
        var itemId;
        //Default Constructor
        function initializeController() {
            $rootScope.loginpage = true;
            self.model = {
                SelectedItems: [],
                selectItemList: [],
                selectAllRows: []

            };
            StatusService.GetStatus().then(function (status) {
                self.model.statuses = status;
            });
            rejectReasonService.GetReason().then(function (reason) {
                self.model.rejectReasons = reason;
            });
            self.model.fromConsumableAcceptance = fromConsumableAcceptance;
            self.model.setStatus = setStatus;
            self.model.SelectAllItems = SelectAllItems;
            self.model.SelectItem = SelectItem;
            self.model.isDisable = isDisable;
            self.model.valueOnChange = valueOnChange;
            self.model.ApprovedQuantity = ApprovedQuantity;  
            self.model.ValidateApproval = ValidateApproval;
            self.model.SaveApproval = SaveApproval;
            self.model.NavigateToBack = NavigateToBack;
            self.model.RejectionPopup = RejectionPopup;
            self.model.CancelRejection = CancelRejection;
            self.model.SaveAcceptReject = SaveAcceptReject;
            self.model.ModalDismiss = ModalDismiss;

            fromConsumableAcceptance();

        }
        function fromConsumableAcceptance() {
            var id = commonDetailService.getDataId();
            var URI = CONSTANTS.INVENTORY.NURSING.CONSUMABLE_ACCEPTANCE.DETAIL_API + id;
            var data = {};
            GenericService.serviceAction("GET", URI, data).then(
                function (response) {
                    self.model.consumableDetail = response.data;
                    self.model.consumableDetail.batch.forEach(function (item) {
                        item.isDisabled = true;

                    });
                });
        }

        function setStatus(x) {

            var status = self.model.statuses.find(function (obj) {
                return obj.status == x;
            });

            return status.id;
        }

        var isDisable = function (batch) {
            if (batch.statusId != 10 || batch.statusId == 7 ||batch.statusId == 9||batch.statusId == 5) {
                return true;
            }
            return false;
        }
        
        var SelectAllItems = function () {
            if (self.model.selectAllRows) {
                self.model.SelectedRequestIds = [];
                self.model.consumableDetail.batch.forEach(function (batch) {
                    if (!!isDisable(batch)) {
                        batch.isSelected = true;
                        self.model.SelectedRequestIds.push(batch.id);
                        batch.isDisabled = false;
                        self.model.SelectedItems.push(batch);
                    }
                });
            } else {
                self.model.SelectedRequestIds = [];
                self.model.SelectedItems = [];
                self.model.consumableDetail.batch.forEach(function (batch) {
                    batch.isSelected = false;
                    batch.isDisabled = true;
                });
            }
        }

        var SelectItem = function (batch) {
            var batches = self.model.SelectedItems;

            if (batch.isSelected) {
                batches.push(batch);
                batch.isDisabled = false;
            } else {
                batch.isDisabled = true;
                batch.isReject = false;
                var index = batch.indexOf(batch);
                if (index > -1) {
                    batches.splice(index, 1);
                }
            }

            if (self.model.consumableDetail.batch.every(isSelected)) {
                self.model.selectAllRows = true;
            } else {
                self.model.selectAllRows = false;
            }
        }


        var isSelected = function (batch) {
            return batch.isSelected;
        }
        
        var ApprovedQuantity = function (batch) {
            if (batch.acceptedQuantity > batch.consumedQuantity) {
                batch.rejectedQuantity = 0;
                self.model.rejectQty = undefined;
                batch.isReject = false;
                batch.status = setStatus('Approved');; // Approved.
            } else if (batch.acceptedQuantity == 0 || !batch.acceptedQuantity) {
                batch.rejectedQuantity = item.quantity;
                self.model.rejectQty = item.rejectedQuantity;
                batch.isReject = true;
                batch.status = setStatus('Reject');; // Rejected
            } else if (batch.acceptedQuantity < batch.consumedQuantity) {
                batch.rejectedQuantity = batch.consumedQuantity - batch.acceptedQuantity;
                self.model.rejectQty = batch.rejectedQuantity;
                batch.isReject = true;
                batch.status = setStatus('Partially Approved');; // Partially Approved
            } else if (batch.acceptedQuantity == batch.consumedQuantity) {
                batch.rejectedQuantity = batch.consumedQuantity - batch.acceptedQuantity;
                self.model.rejectQty = batch.rejectedQuantity;
                batch.isReject = false;
                batch.status = setStatus('Approved');; // Approved.
            }
        }

        var ValidateRejection = function () {
            var isValid = true;
            if (!self.model.rejectReason) {
                isValid = false;
                growl.error('Please Select Reason to Reject');
            }

            return isValid;
        }

        var SaveAcceptReject = function () {
            if (ValidateRejection()) {
                var rejectItemId = itemId;
                self.model.SelectedItems.forEach(function (batch) {
                    if (batch.id == rejectItemId) {
                        if (self.model.rejectQty > batch.consumedQuantity) {
                            growl.error('Please Enter Valid Quantity');
                            return false;
                        } else if (self.model.rejectQty == batch.consumedQuantity) {
                            batch.rejectedQuantity = batch.consumedQuantity;
                            batch.acceptedQuantity = batch.consumedQuantity - batch.rejectQuantity;
                            batch.rejectReasonId = self.model.rejectReasons[0].id;
                            batch.statusId = setStatus('Reject'); // Rejected 
                        } else if (self.model.rejectQty == 0 || !self.model.rejectQty) {
                            batch.acceptedQuantity = batch.consumedQuantity;
                            batch.rejectedQuantity = 0;
                            batch.rejectReasonId = self.model.rejectReasons[0].id;
                            batch.statusId = setStatus('Pending'); // Approved
                        } else {
                            batch.rejectedQuantity = self.model.rejectQty;
                            batch.rejectNote = self.model.rejectNote;
                            batch.acceptedQuantity = batch.consumedQuantity - batch.rejectQuantity;
                            batch.rejectReasonId = self.model.rejectReasons[0].id;
                            batch.statusId = setStatus('Partially Approved'); // Partially Approved
                        }
                        ModalDismiss('qtyaccepatReject');
                    }
                });
            }
        }
        function valueOnChange(batch, isSelectAll) {
            if (isSelectAll)
                self.model.consumableDetail.batch.forEach(function (batch) {
                    if (self.model.selectAllRows)
                    batch.acceptedQuantity = batch.consumedQuantity;
                    else
                    batch.acceptedQuantity = 0;
                });
            else
                if (batch.isSelected)
                batch.acceptedQuantity = batch.consumedQuantity;
                else
                batch.acceptedQuantity = 0;
        }

        var SaveApproval = function () {

            if (ValidateApproval()) {
                var URI = CONSTANTS.INVENTORY.NURSING.CONSUMABLE_ACCEPTANCE.DETAIL_APPROVE_API + commonDetailService.getDataId();
                var data = {
                    "statusId": setStatus('Closed'),
                    "approvalStatusId": setStatus('Reject'),
                    "batch": []
                }

                $rootScope.startLoader();
                angular.forEach(self.model.SelectedItems, function (item) {
                    var newItem = {
                        "id": item.id,
                        "acceptedQuantity": parseInt(item.acceptedQuantity)?item.acceptedQuantity:0,
                        "rejectedQuantity": parseInt(item.rejectedQuantity),
                        "rejectReasonId": self.model.rejectReasonId ? self.model.rejectReasonId : 1,
                        "rejectNote": self.model.rejectNote ? self.model.rejectNote : "default note",
                        "statusId": item.status ? item.status : 6
                    }

                    if (item.acceptedQuantity > 0 && item.acceptedQuantity < item.consumedQuantity) {
                        newItem.statusId = setStatus('Partially Approved');

                        if (data.approvalStatusId == setStatus('Reject')) {
                            data.approvalStatusId = setStatus('Partially Approved');
                        }
                    }
                    else if (item.acceptedQuantity >= item.consumedQuantity) {
                        data.approvalStatusId = setStatus('Approved');

                        if (data.approvalStatusId == setStatus('Reject')) {
                            data.approvalStatusId = setStatus('Approved');
                        }
                    }
                    else if (item.rejectedQuantity >= item.consumedQuantity) {
                        newItem.statusId = setStatus('Reject');
                    }
                    else if (item.rejectedQuantity > 0 && item.rejectedQuantity < item.consumedQuantity) {
                        newItem.statusId = setStatus('Partially Approved');

                        if (data.approvalStatusId == setStatus('Reject')) {
                            data.approvalStatusId = setStatus('Approved');
                        }
                    }

                    data.batch.push(newItem);
                });

                GenericService.serviceAction("PATCH", URI, data).then(
                    function (response) {
                        $rootScope.stopLoader();
                        growl.success(response.data.message);
                        NavigateToBack();
                    }, function (err) {
                        $rootScope.stopLoader();
                        growl.error('Somthing Went Wrong');
                    });
            }
        }

        var ValidateApproval = function () {
            var isValid = true;

            if (!self.model.SelectedItems || self.model.SelectedItems.length == 0) {
                growl.error('Select Items to Update and Save');
                isValid = false;
            }

            return isValid;
        }

        var NavigateToBack = function () {
            $state.go('consumableAcceptance');
        } 

        var RejectionPopup = function (batch) {
            // console.log(self.model.status);
            if (batch.isReject) {
                itemId = batch.id;
                // self.model.rejectQty = item.rejectQuantity;
                self.model.action = 'Open';
                var popup = angular.element('#qtyaccepatReject');
                popup.modal('show');
                self.model.reasons = self.model.rejectReasons;
                // self.model.rejectReason = self.model.reasons[0];
            } else {
                CancelRejection(batch);
            }
        }

        var CancelRejection = function (batch) {
            var prevRequest = self.model.requestItems.batch.find(function (prevItem) {
                return prevItem.id == batch.id;
            });

            if (prevRequest) {
                batch.rejectedQuantity = prevRequest.rejectedQuantity;
                batch.acceptedQuantity = prevRequest.acceptedQuantity;
                batch.rejectNote = prevRequest.rejectNote;
                batch.rejectReasonId = prevRequest.rejectReasonId;
                batch.statusId = prevRequest.statusId;
            }
        } 

        var ModalDismiss = function (popupName) {
            var popup = angular.element('#' + popupName);
            popup.modal('hide');
          }
      
        initializeController();
    }

    function config($stateProvider) {
        $stateProvider.state("detailsConsumableAcceptance", {
            url: "/detailsConsumableAcceptance",
            templateUrl: 'views/PharmacyInventory/InPatient/details-consumable-acceptance.html',
            controller: "ConsumableAcceptanceDetails.Controller",
            controllerAs: "vm"
        });
    }

    angular
        .module("myApp")
        .config(config)
        .controller("ConsumableAcceptanceDetails.Controller", consumableAcceptanceDetailsController);
})(); 