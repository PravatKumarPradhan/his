(function () {
    "use strict";

    function scrapRequestController($scope, $rootScope, $http, $state, StatusService, cancelReasonService,commonDetailService, CONSTANTS, GenericService, growl) {
        var self = this;
        var scrapId;
        //Default Constructor
        function initializeController() {
            $rootScope.loginpage = true;
            self.model = {
                stores: [{
                    "store": "All Stores"
                }],
                statuses: [{
                    "status": "All"
                }],
                assetTypes: [{
                    "type": "All"
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
            self.model.ScrapRequestSearch = ScrapRequestSearch;
            self.model.SelectScrapRequest = SelectScrapRequest;
            self.model.SelectAllRequests = SelectAllRequests;
            self.model.isDisable = isDisable;
            self.model.isSelected = isSelected;
            self.model.ValidateAndUpdate = ValidateAndUpdate;
            self.model.ApproveScrapRequest = ApproveScrapRequest;
            self.model.setStatus = setStatus;
            self.model.AddScrap = AddScrap;
            self.model.EditScrap = EditScrap;
            self.model.ScrapDetails = ScrapDetails;

            ScrapRequestSearch();
            PopulateDropdown();

        }

        function setStatus(x) {
            var status = self.model.statuselect.find(function (obj) {
                return obj.status == x;
            });

            return status.id;
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
            var scrap = self.model;
            scrap.store = scrap.stores[0];
            scrap.status = scrap.statuses[0];
            scrap.assetType = scrap.assetTypes[0];
            var URI = CONSTANTS.INVENTORY.SCRAP.SCRAP_REQUEST.DROPDOWN_API;

            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    scrap.stores = scrap.stores.concat(response.data.store);
                    scrap.statuses = scrap.statuses.concat(response.data.status);
                    scrap.assetTypes = scrap.assetTypes.concat(response.data.assetType);

                    $rootScope.stopLoader();
                },
                function (err) {
                    $rootScope.stopLoader();
                });
        }

        var ScrapRequestSearch = function () {
            var URI = CONSTANTS.INVENTORY.SCRAP.SCRAP_REQUEST.SEARCH_API;
            var data = {
                "fromDate": moment(self.model.fromDate).format('YYYY-MM-DD'),
                "toDate": moment(self.model.toDate).format('YYYY-MM-DD'),
                // "storeId": self.model.stores.id , 
                // "statusId": self.model.status.id,
                // "assetTypeId":self.model.assetType.id
            };
            $rootScope.startLoader()
            GenericService.serviceAction("POST", URI, data).then(
                function (response) {
                    $rootScope.stopLoader();
                    for (var i = 0; i < response.data.length; i++) {
                        response.data[i]["isChecked"] = false;
                    }
                    self.model.scrapRequestData = response.data;
                },
                function errorCallback(response) {
                    $rootScope.stopLoader();
                    growl.error('Error');
                });
        }


        var SelectAllRequests = function () {
            if (self.model.selectAllRows) {
                self.model.SelectedRequestIds = [];
                self.model.scrapRequestData.forEach(function (scrap) {
                    if (!isDisable(scrap)) {
                        scrap.isSelected = true;
                        self.model.isItemChecked = true;
                        self.model.SelectedRequestIds.push(scrap.id);
                    }
                });
            } else {
                self.model.SelectedRequestIds = [];
                self.model.scrapRequestData.forEach(function (scrap) {
                    scrap.isSelected = false;
                    self.model.isItemChecked = false;
                });
            }

        }

        var isSelected = function (scrap) {
            if (!isDisable(scrap))
                return scrap.isSelected;
            else
                return true;
        }
        var isDisable = function (scrap) {
            if (scrap.status != 'New') {
                return true;
            }
            return false;
        }

        var SelectScrapRequest = function (scrap) {
            var scrapIds = self.model.SelectedRequestIds;

            if (scrap.isSelected) {
                scrapIds.push(scrap.id);
            } else {
                var index = scrapIds.indexOf(scrap.id);
                if (index > -1) {
                    scrapIds.splice(index, 1);
                }
            }

            if (self.model.scrapRequestData.every(isSelected)) {
                self.model.selectAllRows = true;
            } else {
                self.model.selectAllRows = false;
            }
            self.model.isItemChecked = false;
            self.model.scrapRequestData.forEach(function (scrap) {
                if (scrap.isSelected == true) {
                    self.model.isItemChecked = true;
                    return;
                }
            });
        }

        var ApproveScrapRequest = function () {
            if (self.model.action == 'Update') {
                var URI = CONSTANTS.INVENTORY.SCRAP.SCRAP_REQUEST.APPROVE_API;

                var data = {
                    "scrapId": self.model.SelectedRequestIds,
                    "statusId": setStatus('Pending'),
                    "approvalStatusId": setStatus('Pending Approval')
                };

                $rootScope.startLoader();
                GenericService.serviceAction("PATCH", URI, data).then(
                    function (response) {
                        self.model.SelectedRequestIds = [];
                        $rootScope.stopLoader();
                        growl.success(response.data.message);
                        ScrapRequestSearch();
                    },
                    function (err) {
                        $rootScope.stopLoader();
                        growl.error(err.data.msg);
                    });
            } else if (self.model.action == 'Cancel') {

                var URI = CONSTANTS.INVENTORY.SCRAP.SCRAP_REQUEST.CANCEL_API + scrapId;

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
                        ScrapRequestSearch();
                    },
                    function (err) {
                        $rootScope.stopLoader();
                        growl.error(err.data.msg);
                    });
            }

        }

        var ValidateAndUpdate = function (action, popupName, id) {
            scrapId = id;
            self.model.action = action;
            var popup = angular.element('#' + popupName);
            popup.modal('show');
        }


        var AddScrap = function () {
            commonDetailService.setDataId(null);
            $state.go('addItemScrapApproval');
        }

        var EditScrap = function (scrap) {
            commonDetailService.setDataId(scrap.id);    
            $state.go('addItemScrapApproval');
        }

        var ScrapDetails = function (scrap) {
            commonDetailService.setDataId(scrap.id);
            $state.go('detailsScrapApproval');
        }

        initializeController();
    }

    function config($stateProvider) {
        $stateProvider.state("scrapRequest", {
            url: "/scrapRequest",
            templateUrl: 'views/PharmacyInventory/Store/scrap-approval-worklist.html',
            controller: "ScrapRequest.Controller",
            controllerAs: "vm"
        });
    }

    angular
        .module("myApp")
        .config(config)
        .controller("ScrapRequest.Controller", scrapRequestController);
})();
