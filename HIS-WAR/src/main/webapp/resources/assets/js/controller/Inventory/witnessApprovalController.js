(function () {
    "use strict";
  
    function witnessApprovalController($scope, $rootScope, $http, commonDetailService, $state, CONSTANTS, GenericService, growl) {
      var self = this;
      
      //Default Constructor
      function initializeController() {
        $rootScope.loginpage = true;
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
        assetTypes: [{
          "type": "All Types"
        }],
        toDateOptions: {
          formatYear: 'yyyy',
          showWeeks: false,
          maxDate: new Date()
        },
        fromDate: new Date(),
        toDate: new Date(),


      };

      self.model.PopulateDropdown = PopulateDropdown;
      self.model.OpenFromDate = OpenFromDate;
      self.model.OpenToDate = OpenToDate;
      self.model.WitnessApprovalSearch = WitnessApprovalSearch;
      self.model.WitnessApprovalDetails = WitnessApprovalDetails;

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
      var disposal = self.model;
      disposal.store = disposal.stores[0];
      disposal.status = disposal.statuses[0];
      disposal.assetType = disposal.assetTypes[0];
      var URI = CONSTANTS.INVENTORY.SCRAP.WITNESS_APPROVAL.DROPDOWN_API;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          disposal.stores = disposal.stores.concat(response.data.store);
          disposal.assetTypes = disposal.assetTypes.concat(response.data.assetType);
          $rootScope.stopLoader();
          // GetStoreIndents();
        },
        function (err) {
          $rootScope.stopLoader();
        });
    }
    var WitnessApprovalSearch = function () {
      var URI = CONSTANTS.INVENTORY.SCRAP.WITNESS_APPROVAL.SEARCH_API;
      var data = {
        "fromDate": moment(self.model.fromDate).format('YYYY-MM-DD'),
        "toDate": moment(self.model.toDate).format('YYYY-MM-DD'),
        "storeId": self.model.store.id,
        "statusId": self.model.status.id,
        "assetTypeId": self.model.assetType.id
      };
      $rootScope.startLoader()
      GenericService.serviceAction("POST", URI, data).then(
        function (response) {
          $rootScope.stopLoader();
          self.model.witnessApprovalData = response.data;
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
          growl.error('Error');
        });
    }
    var WitnessApprovalDetails = function (disposal) {
      commonDetailService.setDataId(disposal.id);
      $state.go('detailsWitnessApproval');
    }

    initializeController();
  }
  
    function config($stateProvider) {
      $stateProvider.state("witnessApproval", {
        url: "/witnessApproval", 
        templateUrl: 'views/PharmacyInventory/Store/witness-approval.html',
        controller: "WitnessApproval.Controller",
        controllerAs: "vm"
      });
    }
  
    angular
      .module("myApp")
      .config(config)
      .controller("WitnessApproval.Controller", witnessApprovalController);
  })();
 