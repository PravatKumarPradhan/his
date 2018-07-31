(function () {
    "use strict";

    function batchChangeApprovalController($scope, $rootScope, $http, StatusService, cancelReasonService, commonDetailService, $state, CONSTANTS, GenericService, growl) {
        var self = this;
        var batchId;
        //Default Constructor
        function initializeController() {
            $rootScope.loginpage = true;
            self.model = {
                stores: [{
                    "store": "Select Store"
                }],
                fromDateOptions: {
                    formatYear: 'yyyy',
                    showWeeks: false,
                    maxDate: new Date()
                },
                toDateOptions: {
                    formatYear: 'yyyy',
                    showWeeks: false,
                    maxDate: new Date()
                },
                fromDate: new Date(),
                toDate: new Date(),
                SelectedRequestIds: [],
                selectAllRows: false,
            };
            StatusService.GetStatus().then(function (status) {
                self.model.statuselect = status;
            });

            cancelReasonService.GetReason().then(function (reason) {
                self.model.reasons = reason;
            });

            self.model.OpenFromDate = OpenFromDate;
            self.model.OpenToDate = OpenToDate;
            self.model.PopulateDropdown = PopulateDropdown;
            self.model.BatchChangeApprovalSearch = BatchChangeApprovalSearch;
            self.model.SelectAllRequests = SelectAllRequests;
            self.model.SelectBatchRequest = SelectBatchRequest;
            self.model.isSelected = isSelected;
            self.model.isDisable = isDisable;
            self.model.ValidateAndUpdate = ValidateAndUpdate;
            self.model.ApproveBatchChange = ApproveBatchChange;
            self.model.setStatus = setStatus;
            self.model.BatchChangeApprovalDetails = BatchChangeApprovalDetails;
            PopulateDropdown();
        }



        var OpenFromDate = function () {
            self.model.fromDateOpened = true;
            self.model.toDateOptions.minDate = self.model.fromDate;
        }

        var OpenToDate = function () {
            self.model.toDateOpened = true;
            self.model.toDateOptions.minDate = self.model.fromDate;
        }

        var PopulateDropdown = function () {
            var batch = self.model;
            var URI = CONSTANTS.GLOBAL.USER_STORE_API;
            batch.store = batch.stores[0];
            batch.stores.splice(1);
            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    batch.stores = batch.stores.concat(response.data);
                    $rootScope.stopLoader();
                },
                function (err) {
                    $rootScope.stopLoader();
                });
        }

        var BatchChangeApprovalSearch = function () {
            var URI = CONSTANTS.INVENTORY.STORE.BATCH_CHANGE_APPROVAL.SEARCH_API;
            var data = {
                "fromDate": moment(self.model.fromDate).format('YYYY-MM-DD'),
                "toDate": moment(self.model.toDate).format('YYYY-MM-DD'),
                "storeId": self.model.store.id
            };
            $rootScope.startLoader()
            GenericService.serviceAction("POST", URI, data).then(
                function (response) {
                    $rootScope.stopLoader();
                    for (var i = 0; i < response.data.length; i++) {
                        response.data[i]["isChecked"] = false;
                    }
                    self.model.batchChangeApprovalData = response.data;
                },
                function errorCallback(response) {
                    $rootScope.stopLoader();
                    growl.error('Error');
                });
        }


        var SelectAllRequests = function () {
            if (self.model.selectAllRows) {
                self.model.SelectedRequestIds = [];
                self.model.batchChangeApprovalData.forEach(function (batch) {
                    if (!isDisable(batch)) {
                        batch.isSelected = true;
                        self.model.isItemChecked = true;
                        self.model.SelectedRequestIds.push(batch.id);
                    }
                });
            } else {
                self.model.SelectedRequestIds = [];
                self.model.batchChangeApprovalData.forEach(function (batch) {
                    batch.isSelected = false;
                    self.model.isItemChecked = false;
                });
            }

        }
        var isSelected = function (batch) {
            return batch.isSelected;
        }
        
        var isDisable = function (batch) {
            if (batch.approvalStatusId != 10 || batch.statusId != 2) {
                return true;
             }
            return false;
        }

        var SelectBatchRequest = function (batch) {
            var batchIds = self.model.SelectedRequestIds;

            if (batch.isSelected) {
                batchIds.push(batch.id);
            } else {
                var index = batchIds.indexOf(batch.id);
                if (index > -1) {
                    batchIds.splice(index, 1);
                }
            }

            if (self.model.batchChangeApprovalData.every(isSelected)) {
                self.model.selectAllRows = true;
            } else {
                self.model.selectAllRows = false;
            }
            self.model.isItemChecked = false;
            self.model.batchChangeApprovalData.forEach(function (batch) {
                if (batch.isSelected == true) {
                    self.model.isItemChecked = true;
                    return;
                }
            });
        }

        var ValidateAndUpdate = function (action, popupName, id) {
            batchId = id;
            self.model.action = action;
            var popup = angular.element('#' + popupName);
            popup.modal('show');
        }


        var ApproveBatchChange = function () {
            if (self.model.action == 'Update') {
                var URI = CONSTANTS.INVENTORY.STORE.BATCH_CHANGE_APPROVAL.APPROVE_API;

                var data = {
                    "adjustmentId": self.model.SelectedRequestIds,
                    "statusId": setStatus('Closed'),
                    "approvalStatusId": setStatus('Approved')
                };

                $rootScope.startLoader();
                GenericService.serviceAction("PATCH", URI, data).then(
                    function (response) {
                        self.model.SelectedRequestIds = [];
                        $rootScope.stopLoader();
                        growl.success(response.data.message);
                        BatchChangeApprovalSearch();
                    },
                    function (err) {
                        $rootScope.stopLoader();
                        growl.error(err.data.msg);
                    });
            } else if (self.model.action == 'Cancel') {

                var URI = CONSTANTS.INVENTORY.STORE.BATCH_CHANGE.CANCEL_API + batchId;

                var data = {
                    "cancelReasonId": self.model.cancelReason,
                    "cancelNote": self.model.cancelNote,
                    "statusId": setStatus('Cancelled')
                };

                GenericService.serviceAction("PATCH", URI, data).then(
                    function (response) {
                        self.model.SelectedRequestIds = [];
                        $rootScope.stopLoader();
                        growl.success(response.data.message);
                        BatchChangeApprovalSearch();
                    },
                    function (err) {
                        $rootScope.stopLoader();
                        growl.error(err.data.msg);
                    });
            }

        }


        function setStatus(x) {
            var status = self.model.statuselect.find(function (obj) {
                return obj.status == x;
            });

            return status.id;
        }


        var BatchChangeApprovalDetails = function (batch) {
            commonDetailService.setDataId(batch.id);
            $state.go('detailBatchChangeApproval');
        }






        initializeController();
    }

    function config($stateProvider) {
        $stateProvider.state("batchExpDateChangeApproval", {
            url: "/batchExpDateChangeApproval",
            templateUrl: 'views/PharmacyInventory/Store/batch-exp-date-change-approval.html',
            controller: "BatchChangeApproval.Controller",
            controllerAs: "vm"
        });
    }

    angular
        .module("myApp")
        .config(config)
        .controller("BatchChangeApproval.Controller", batchChangeApprovalController);
})();
