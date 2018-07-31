(function () {
    "use strict";
  
    function nonBillableConsumptionController($scope,StatusService,cancelReasonService,commonDetailService,  $state, $rootScope, $http, CONSTANTS, GenericService, growl) {
      var self = this;
      var consumptionId;
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

          cancelReasonService.GetReason().then(function (reason) {
              self.model.reasons = reason;
          });

    self.model.PopulateDropdown = PopulateDropdown;
    self.model.OpenFromDate = OpenFromDate;
    self.model.OpenToDate = OpenToDate;
    self.model.NonBillableSearch = NonBillableSearch;
    self.model.SelectAllRequests = SelectAllRequests;
    self.model.isSelected = isSelected;
    self.model.isDisable = isDisable;
    self.model.SelectConsumptionRequest = SelectConsumptionRequest;
    self.model.ApproveNonBIllableConsumption = ApproveNonBIllableConsumption; 
    self.model.setStatus = setStatus;
    self.model.ValidateAndUpdate = ValidateAndUpdate;
    self.model.AddConsumption = AddConsumption;;
    self.model.EditConsumption = EditConsumption;
    self.model.ConsumptionDetails = ConsumptionDetails;

    PopulateDropdown();
    NonBillableSearch(); 
  
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

    var NonBillableSearch = function () {
        var URI = CONSTANTS.INVENTORY.NURSING.NON_BILLABLE_CONSUMPTION.SEARCH_API;
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
                self.model.nonBillableData = response.data;
            },
            function errorCallback(response) {
                $rootScope.stopLoader();
                growl.error('Error');
            });
    }

    var SelectAllRequests = function () {
        if (self.model.selectAllRows) {
            self.model.SelectedRequestIds = [];
            self.model.nonBillableData.forEach(function (consumption) {
                if (!isDisable(consumption)) {
                    consumption.isSelected = true;
                    self.model.isItemChecked = true;
                    self.model.SelectedRequestIds.push(consumption.id);
                }
            });
        } else {
            self.model.SelectedRequestIds = [];
            self.model.nonBillableData.forEach(function (consumption) {
                consumption.isSelected = false;
                self.model.isItemChecked = false;
            });
        }

    }

    var isSelected = function (consumption) {
        if (!isDisable(consumption))
            return consumption.isSelected;
        else
            return true;
    }
    var isDisable = function (consumption) {
        if (consumption.status != 'New') {
            return true;
        }
        return false;
    }

    var SelectConsumptionRequest = function (consumption) {
        var consumptionIds = self.model.SelectedRequestIds;

        if (consumption.isSelected) {
            consumptionIds.push(consumption.id);
        } else {
            var index = consumptionIds.indexOf(consumption.id);
            if (index > -1) {
                consumptionIds.splice(index, 1);
            }
        }

        if (self.model.nonBillableData.every(isSelected)) {
            self.model.selectAllRows = true;
        } else {
            self.model.selectAllRows = false;
        }
        self.model.isItemChecked = false;
        self.model.nonBillableData.forEach(function (consumption) {
            if (consumption.isSelected == true) {
                self.model.isItemChecked = true;
                return;
            }
        });
    }

    var ApproveNonBIllableConsumption = function () {
        if (self.model.action == 'Update') {
            var URI = CONSTANTS.INVENTORY.NURSING.NON_BILLABLE_CONSUMPTION.APPROVE_API; 

            var data = {
                "consumptionId": self.model.SelectedRequestIds,
                "statusId": setStatus('Pending'),
                "approvalStatusId": setStatus('Pending Approval')
            };

            $rootScope.startLoader();
            GenericService.serviceAction("PATCH", URI, data).then(
                function (response) {
                    self.model.SelectedRequestIds = [];
                    $rootScope.stopLoader();
                    growl.success(response.data.message);
                    NonBillableSearch();
                },
                function (err) {
                    $rootScope.stopLoader();
                    growl.error(err.data.msg);
                });
        } else if (self.model.action == 'Cancel') {

            var URI = CONSTANTS.INVENTORY.NURSING.NON_BILLABLE_CONSUMPTION.CANCEL_API + consumptionId;

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
                    NonBillableSearch();
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

    var ValidateAndUpdate = function (action, popupName, id) {
        consumptionId = id;
        self.model.action = action;
        var popup = angular.element('#' + popupName);
        popup.modal('show');
    }

    var AddConsumption = function () {
        commonDetailService.setDataId(null);
        $state.go('addNewnonbillableConsumption');
      }

    var EditConsumption = function (consumption) {
        commonDetailService.setDataId(consumption.id);
        $state.go('addNewnonbillableConsumption');
      }
  
      var ConsumptionDetails = function (consumption) {
        commonDetailService.setDataId(consumption.id);
        $state.go('detailsnonbillableConsumption'); 
      }
  

      initializeController();
    }
  
    function config($stateProvider) {
      $stateProvider.state("nonBillableConsumption", {
        url: "/nonBillableConsumption",
        templateUrl: 'views/PharmacyInventory/non-billable-consumption.html',
        controller: "NonBillableConsumption.Controller",
        controllerAs: "vm"
      });
    }
  
    angular
      .module("myApp")
      .config(config)
      .controller("NonBillableConsumption.Controller", nonBillableConsumptionController);
  })();   