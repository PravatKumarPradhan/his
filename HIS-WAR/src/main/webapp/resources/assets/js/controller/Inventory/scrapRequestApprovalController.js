(function () {
    "use strict";
  
    function scrapRequestApprovalController($scope, $rootScope,$state, commonDetailService,  $http, CONSTANTS, GenericService, growl) {
      var self = this;
      
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
            toDate: new Date()
          };
  
  self.model.OpenFromDate = OpenFromDate;
  self.model.OpenToDate = OpenToDate;
  self.model.ScrapRequestApprovalSearch = ScrapRequestApprovalSearch;
  self.model.ScrapApprovalDetails = ScrapApprovalDetails;

  PopulateDropdown();
  ScrapRequestApprovalSearch();
    }
    var OpenFromDate = function () {
        self.model.fromDateOpened = true;
        self.model.toDateOptions.minDate = self.model.fromDate;
    }

    var OpenToDate = function () {
        self.model.toDateOpened = true;
        self.model.toDateOptions.minDate = self.model.fromDate;
    }

    var ScrapRequestApprovalSearch = function () {
        var URI = CONSTANTS.INVENTORY.SCRAP.SCRAP_REQUEST_APPROVAL.SEARCH_API;
        var data = {
            "fromDate": moment(self.model.fromDate).format('YYYY-MM-DD'),
            "toDate": moment(self.model.toDate).format('YYYY-MM-DD'),
            // "storeId": self.model.store.id,
            // "statusId": self.model.status.id
        };
        $rootScope.startLoader()
        GenericService.serviceAction("POST", URI, data).then(
            function (response) {
                $rootScope.stopLoader(); 
                self.model.scrapApprovalData = response.data;
            },
            function errorCallback(response) {
                $rootScope.stopLoader();
                growl.error('Error');
            });
    } 
    
      var PopulateDropdown = function () {
        var scrap = self.model;
        scrap.store = scrap.stores[0];
        scrap.status = scrap.statuses[0];
        scrap.assetType = scrap.assetTypes[0];
        var URI = CONSTANTS.INVENTORY.SCRAP.SCRAP_REQUEST_APPROVAL.DROPDOWN_API;

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

    
    var ScrapApprovalDetails = function (scrap) {
        commonDetailService.setDataId(scrap.id);
        $state.go('detailsApprovalScrapRequest');
    }


      initializeController();
    }
  
    function config($stateProvider) {
      $stateProvider.state("approvalScrapRequest", {
        url: "/approvalScrapRequest",
        templateUrl: 'views/PharmacyInventory/Store/approved-scrap-request.html',
        controller: "ScrapRequestApproval.Controller",
        controllerAs: "vm"
      });
    }
  
    angular
      .module("myApp")
      .config(config)
      .controller("ScrapRequestApproval.Controller", scrapRequestApprovalController);
  })();
 