(function () {
    "use strict";

    function consumableAcceptanceController($scope, $rootScope, $http, CONSTANTS, StatusService,commonDetailService, $state, GenericService, growl) {
        var self = this;

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
            self.model.ConsumableAcceptanceSearch = ConsumableAcceptanceSearch;
            self.model.OpenFromDate = OpenFromDate;
            self.model.OpenToDate = OpenToDate;
            self.model.isSelected = isSelected;
            self.model.isDisable = isDisable;
            self.model.SelectAllRequests = SelectAllRequests;
            self.model.SelectConsumableRequest = SelectConsumableRequest;
            self.model.setStatus = setStatus;
            self.model.ApproveConsumableAcceptance = ApproveConsumableAcceptance;
            self.model.ConsumableDetails = ConsumableDetails;


            PopulateDropdown();
            ConsumableAcceptanceSearch();
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
        var consumption = self.model; 
        consumption.store = consumption.stores[0];
        consumption.status = consumption.statuses[0]; 
        var URI = CONSTANTS.INVENTORY.NURSING.NON_BILLABLE_CONSUMPTION.DROPDOWN_API;
  
        $rootScope.startLoader();
        GenericService.serviceAction("GET", URI, {}).then(
          function (response) {
            consumption.stores = consumption.stores.concat(response.data.store); 
            consumption.statuses = consumption.statuses.concat(response.data.status); 
            $rootScope.stopLoader();
            // GetStoreIndents();
          },
          function (err) {
            $rootScope.stopLoader();
          });
      }


        var ConsumableAcceptanceSearch = function () {
            var URI = CONSTANTS.INVENTORY.NURSING.CONSUMABLE_ACCEPTANCE.SEARCH_API;
            var data = {
                "fromDate": moment(self.model.fromDate).format('YYYY-MM-DD'),
                "toDate": moment(self.model.toDate).format('YYYY-MM-DD'),
                "storeId": self.model.store.id,
                "statusId": self.model.status.id
            };
            $rootScope.startLoader()
            GenericService.serviceAction("POST", URI, data).then(
                function (response) {
                    $rootScope.stopLoader();
                    for (var i = 0; i < response.data.length; i++) {
                        response.data[i]["isChecked"] = false;
                    }
                    self.model.consumableData = response.data;
                },
                function errorCallback(response) {
                    $rootScope.stopLoader();
                    growl.error('Error');
                });
        }

        var SelectAllRequests = function () {
            if (self.model.selectAllRows) {
                self.model.SelectedRequestIds = [];
                self.model.consumableData.forEach(function (consumable) {
                    if (!isDisable(consumable)) {
                        consumable.isSelected = true;
                        self.model.isItemChecked = true;
                        self.model.SelectedRequestIds.push(consumable.id);
                    }
                });
            } else {
                self.model.SelectedRequestIds = [];
                self.model.consumableData.forEach(function (consumable) {
                    consumable.isSelected = false;
                    self.model.isItemChecked = false;
                });
            }

        }

        var isSelected = function (consumable) {
            if (!isDisable(consumable))
                return consumable.isSelected;
            else
                return true;
        }
        var isDisable = function (consumable) {
            if (consumable.status != 'Pending') {
                return true;
            }
            return false;
        }

        var SelectConsumableRequest = function (consumable) {
            var consumableIds = self.model.SelectedRequestIds;

            if (consumable.isSelected) {
                consumableIds.push(consumable.id);
            } else {
                var index = consumableIds.indexOf(consumable.id);
                if (index > -1) {
                    consumableIds.splice(index, 1);
                }
            }

            if (self.model.consumableData.every(isSelected)) {
                self.model.selectAllRows = true;
            } else {
                self.model.selectAllRows = false;
            }
            self.model.isItemChecked = false;
            self.model.consumableData.forEach(function (consumable) {
                if (consumable.isSelected == true) {
                    self.model.isItemChecked = true;
                    return;
                }
            });
        }
        var ApproveConsumableAcceptance = function () {

            var URI = CONSTANTS.INVENTORY.NURSING.CONSUMABLE_ACCEPTANCE.APPROVE_API;

            var data = {
                "consumptionId": self.model.SelectedRequestIds,
                "statusId": setStatus('Closed'),
                "approvalStatusId": setStatus('Approved')
            };

            $rootScope.startLoader();
            GenericService.serviceAction("PATCH", URI, data).then(
                function (response) {
                    self.model.SelectedRequestIds = [];
                    $rootScope.stopLoader();
                    growl.success(response.data.message);
                    ConsumableAcceptanceSearch();
                },
                function (err) {
                    $rootScope.stopLoader();
                    growl.error(err.data.msg);
                });

        }
        function setStatus(x) {
            var status = self.model.statuselect.find(function (obj) {
                return obj.status == x;
            });

            return status.id;
        }
        var ConsumableDetails = function (consumable) {
            commonDetailService.setDataId(consumable.id);
            $state.go('detailsConsumableAcceptance'); 
          }
      
    

        initializeController();
    }

    function config($stateProvider) {
        $stateProvider.state("consumableAcceptance", {
            url: "/consumableAcceptance",
            templateUrl: 'views/PharmacyInventory/InPatient/consumable-acceptance.html',
            controller: "ConsumableAcceptance.Controller",
            controllerAs: "vm"
        });
    }

    angular
        .module("myApp")
        .config(config)
        .controller("ConsumableAcceptance.Controller", consumableAcceptanceController);
})(); 