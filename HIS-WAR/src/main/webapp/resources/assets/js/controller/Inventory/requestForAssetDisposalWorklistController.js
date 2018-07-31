(function () {
    "use strict";
  
    function requestForAssetDisposalWorklistController($scope, $state,
         $rootScope, $http, CONSTANTS, commonDetailService, GenericService, growl) {
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
            assetTypes:[{
                "type":"All Types"
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
  
         
      };
  
  self.model.OpenFromDate = OpenFromDate;
  self.model.OpenToDate = OpenToDate;
  self.model.PopulateDropdown = PopulateDropdown;
  self.model.AssetDisposalSearch = AssetDisposalSearch;
  self.model.AssetDisposalDetails = AssetDisposalDetails;

  PopulateDropdown();
  AssetDisposalSearch();
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
        scrap.assetType = scrap.assetTypes[0];
        scrap.status = scrap.statuses[0]; 
        var URI = CONSTANTS.INVENTORY.SCRAP.REQUEST_FOR_ASSET_DISPOSAL.DROPDOWN_API;
  
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

      var AssetDisposalSearch = function () {
        var URI = CONSTANTS.INVENTORY.SCRAP.REQUEST_FOR_ASSET_DISPOSAL.SEARCH_API;
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
                for (var i = 0; i < response.data.length; i++) {
                    response.data[i]["isChecked"] = false;
                }
                self.model.assetDisposalSearchData = response.data;
            },
            function errorCallback(response) {
                $rootScope.stopLoader();
                growl.error('Error');
            });
    }
    
    var AssetDisposalDetails = function (disposal) {
        commonDetailService.setDataId(disposal.id);
        $state.go('detailsRequestForAssestDisposalWorklist'); 
      }
  
      initializeController();
    }
  
    function config($stateProvider) {
      $stateProvider.state("requestForAssetDisposalWorklist", {
        url: "/requestForAssetDisposalWorklist",
        templateUrl: 'views/PharmacyInventory/Store/request-for-assest-disposal-worklist.html',
        controller: "RequestForAssetDisposalWorklist.Controller",
        controllerAs: "vm"
      });
    }
  
    angular
      .module("myApp")
      .config(config)
      .controller("RequestForAssetDisposalWorklist.Controller", requestForAssetDisposalWorklistController);
  })();
 