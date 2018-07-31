(function () {
    "use strict";

    function returnableGatepassController($scope,$state, $rootScope, $http, StatusService, cancelReasonService, commonDetailService,  $filter, CONSTANTS, GenericService, growl) {
        var self = this;
        var returnableId;
        //Default Constructor
        function initializeController() {
            $rootScope.loginpage = true;
            self.model = {
                stores: [{
                    "store": "All Stores"
                }],
                statuses: [{
                    "status": "All Status"
                }],
                assetTypes: [{
                    "type": "All Asset Type"
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
            self.model.SearchVendorNames = SearchVendorNames;
            self.model.nameNotFound = nameNotFound;
            self.model.AddVendorName = AddVendorName;
            self.model.search = '';
            self.model.prevSearch = '';
            self.model.vendorNames = [];
            self.model.againstId;
            self.model.ReturnableGatepassSearch = ReturnableGatepassSearch;
            self.model.SelectAllRequests = SelectAllRequests;
            self.model.SelectGatepassRequest = SelectGatepassRequest;
            self.model.ApproveReturnableGatepass = ApproveReturnableGatepass;
            self.model.isSelected = isSelected;
            self.model.isDisable = isDisable;
            self.model.ValidateAndUpdate = ValidateAndUpdate;
            self.model.setStatus = setStatus;
            self.model.AddReturnable = AddReturnable;
            self.model.EditReturnable = EditReturnable;
            self.model.ReturnableDetails  = ReturnableDetails;

            PopulateDropdown();
            ReturnableGatepassSearch();
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
            var returnable = self.model;
            returnable.store = returnable.stores[0];
            returnable.status = returnable.statuses[0];
            returnable.assetType = returnable.assetTypes[0];
            var URI = CONSTANTS.INVENTORY.STORE.RETURNABLE_GATEPASS.DROPDOWN_API;

            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    returnable.stores = returnable.stores.concat(response.data.store);
                    returnable.statuses = returnable.statuses.concat(response.data.status);
                    returnable.assetTypes = returnable.assetTypes.concat(response.data.assetType);
                    $rootScope.stopLoader();
                },
                function (err) {
                    $rootScope.stopLoader();
                });
        }
        
        function SearchVendorNames(search) {
            if (search.length < 3) return;

            if ((!self.model.prevSearch || self.model.prevSearch != search) && (search.length == 3)) {
                self.model.prevSearch = search;
                var URI = CONSTANTS.GLOBAL.VENDOR_SEARCH_API + search;
                $rootScope.startLoader();
                return GenericService.serviceAction("GET", URI, {})
                    .then(function (response) {
                        if (!!response.data && response.data.length > 0) {
                            self.model.vendorNames = response.data;
                            $rootScope.stopLoader();
                            return $filter('filter')(self.model.vendorNames, {
                                $: search
                            });
                        } else {
                            $rootScope.stopLoader();
                            return nameNotFound(search);
                        }
                    }, function (err) {
                        $rootScope.stopLoader();
                        return nameNotFound(search);
                    });
            } else {
                if (!!self.model.vendorNames && self.model.vendorNames.length > 0 && self.model.vendorNames[0].itemFound != undefined && !self.model.vendorNames[0].itemFound) {
                    return nameNotFound(search);
                } else {
                    return $filter('filter')(self.model.vendorNames, {
                        $: search
                    });
                }
            }
        }

        function nameNotFound(search) {
            var item = {
                "itemFound": false,
                "detail": "Vendor Not found with name " + search
            };
            self.model.vendorNames = [item];
            return self.model.vendorNames;
        }

        function AddVendorName(vendor) {
            self.model.vendorName = vendor.vendorName;
            self.model.vendorId = vendor.id;
        }

        var ReturnableGatepassSearch = function () {
            var URI = CONSTANTS.INVENTORY.STORE.RETURNABLE_GATEPASS.SEARCH_API;
            var data = {
                "fromDate": moment(self.model.fromDate).format('YYYY-MM-DD'),
                "toDate": moment(self.model.toDate).format('YYYY-MM-DD'),
                "storeId": self.model.store.id,
                "statusId": self.model.status.id,
                "vendorId": self.model.vendorId,
                "vendor": self.model.vendorName,
                "assetTypeId": self.model.assetType.id,
                "againstId": self.model.againstId,
                "gatepassNo": self.model.gatepassNo,
            };
            $rootScope.startLoader()
            GenericService.serviceAction("POST", URI, data).then(
                function (response) {
                    $rootScope.stopLoader();
                    for (var i = 0; i < response.data.length; i++) {
                        response.data[i]["isChecked"] = false;
                    }
                    self.model.returnableGatepassData = response.data;
                },
                function errorCallback(response) {
                    $rootScope.stopLoader();
                    growl.error('Error');
                });
        }
        var SelectAllRequests = function () {
            if (self.model.selectAllRows) {
                self.model.SelectedRequestIds = [];
                self.model.returnableGatepassData.forEach(function (returnable) {
                    if (!isDisable(returnable)) {
                        returnable.isSelected = true;
                        self.model.isItemChecked = true;
                        self.model.SelectedRequestIds.push(returnable.id);
                    }
                });
            } else {
                self.model.SelectedRequestIds = [];
                self.model.returnableGatepassData.forEach(function (returnable) {
                    returnable.isSelected = false;
                    self.model.isItemChecked = false;
                });
            }

        }

        var isSelected = function (returnable) {
            if (!isDisable(returnable))
                return returnable.isSelected;
            else
                return true;
        }
        var isDisable = function (returnable) {
            if (returnable.status != 'New') {
                return true;
            }
            return false;
        }

        var SelectGatepassRequest = function (returnable) {
            var returnableIds = self.model.SelectedRequestIds;

            if (returnable.isSelected) {
                returnableIds.push(returnable.id);
            } else {
                var index = returnableIds.indexOf(returnable.id);
                if (index > -1) {
                    returnableIds.splice(index, 1);
                }
            }

            if (self.model.returnableGatepassData.every(isSelected)) {
                self.model.selectAllRows = true;
            } else {
                self.model.selectAllRows = false;
            }
            self.model.isItemChecked = false;
            self.model.returnableGatepassData.forEach(function (returnable) {
                if (returnable.isSelected == true) {
                    self.model.isItemChecked = true;
                    return;
                }
            });
        }

        var ApproveReturnableGatepass = function () {
            if (self.model.action == 'Update') {
                var URI = CONSTANTS.INVENTORY.STORE.RETURNABLE_GATEPASS.APPROVE_API;

                var data = {
                    "gatepassId": self.model.SelectedRequestIds,
                    "statusId": setStatus('Pending'),
                    "approvalStatusId": setStatus('Pending Approval')
                };

                $rootScope.startLoader();
                GenericService.serviceAction("PATCH", URI, data).then(
                    function (response) {
                        self.model.SelectedRequestIds = [];
                        $rootScope.stopLoader();
                        growl.success(response.data.message);
                        ReturnableGatepassSearch();
                    },
                    function (err) {
                        $rootScope.stopLoader();
                        growl.error(err.data.msg);
                    });
            } else if (self.model.action == 'Cancel') {

                var URI = CONSTANTS.INVENTORY.STORE.RETURNABLE_GATEPASS.CANCEL_API + returnableId;

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
                        ReturnableGatepassSearch();
                    },
                    function (err) {
                        $rootScope.stopLoader();
                        growl.error(err.data.msg);
                    });
            }

        }
        var ValidateAndUpdate = function (action, popupName, id) {
            returnableId = id;
            self.model.action = action;
            var popup = angular.element('#' + popupName);
            popup.modal('show');
        }
        var AddReturnable = function () {
            commonDetailService.setDataId(null);
            $state.go('addNewReturnableGatepass');
          }
    
        var EditReturnable = function (returnable) {
            commonDetailService.setDataId(returnable.id);
            $state.go('addNewReturnableGatepass');
          }
      
          var ReturnableDetails = function (returnable) {
            commonDetailService.setDataId(returnable.id);
            $state.go('detailsReturnableGatepass'); 
          }
      
        initializeController();
    }

    function config($stateProvider) {
        $stateProvider.state("returnableGatepass", {
            url: "/returnableGatepass",
            templateUrl: 'views/PharmacyInventory/InPatient/returnable-gatepass.html',
            controller: "ReturnableGatepass.Controller",
            controllerAs: "vm"
        });
    }

    angular
        .module("myApp")
        .config(config)
        .controller("ReturnableGatepass.Controller", returnableGatepassController);
})();
