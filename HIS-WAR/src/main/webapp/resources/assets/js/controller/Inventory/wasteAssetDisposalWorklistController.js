(function () {
  "use strict";

  function wasteAssetDisposalWorklistController($scope, $rootScope,commonDetailService,$state, $http, CONSTANTS, GenericService, growl) {
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
      self.model.WasteDisposalSearch = WasteDisposalSearch;
      self.model.WasteDisposalDetails = WasteDisposalDetails;

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
      var URI = CONSTANTS.INVENTORY.SCRAP.WASTE_ASSET_DISPOSAL.DROPDOWN_API;

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
    var WasteDisposalSearch = function () {
      var URI = CONSTANTS.INVENTORY.SCRAP.WASTE_ASSET_DISPOSAL.SEARCH_API;
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
          self.model.wasteDisposalData = response.data;
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
          growl.error('Error');
        });
    }
    var WasteDisposalDetails = function (disposal) {
      commonDetailService.setDataId(disposal.id);
      $state.go('detailswasteAssetDisposalWorklist');
    }

    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("wasteAssetDisposalWorklist", {
      url: "/wasteAssetDisposalWorklist",
      templateUrl: 'views/PharmacyInventory/Store/waste-disposal-worklist.html',
      controller: "wasteAssetDisposalWorklist.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("wasteAssetDisposalWorklist.Controller", wasteAssetDisposalWorklistController);
})();
