(function () {
    "use strict";
    var CACHE = {};
    function detailsPendingStoreIndentController($scope, $rootScope, growl, CONSTANTS, $location, $http, GenericService, storeIndentService) {
      var self = this;
      var selectItemList = [];

      //Default Constructor
      function initializeController() {
        $rootScope.loginpage = true;
        self.model = {
          selectItemList: [],
          requestData: [],
          openingBalanceData: [],
          productCategories: []
        };
        // getStoreList();
        // fromOpeningBalance();
        GetStoreIndent(storeIndentService.getIndentId());
      }

      var GetStoreIndent = function (storeIndentId) { 

        var URI = CONSTANTS.INVENTORY.STORE.PENDING_STORE_INDENT.DETAILS_API + storeIndentId;

        $rootScope.startLoader();
        GenericService.serviceAction("GET", URI, {}).then(
          function (response) {
            self.model.pendingindent = response.data;``
            self.model.requestItems = JSON.parse(JSON.stringify(response.data));
            self.model.pendingindent.items.forEach(function(item){
              item.isDisabled = true;
            });
            $rootScope.stopLoader();
          },
          function (err) {
            $rootScope.stopLoader();
            growl.error('No Data Found');
          });
      }
      
       
      initializeController();
    }

    function config($stateProvider) {
      $stateProvider.state("detailspendingStoreIndents", {
        url: "/detailspendingStoreIndents",
        templateUrl: 'views/PharmacyInventory/InPatient/details-pending-store-indents.html',
        controller: "DetailsPendingStoreIndent.Controller",
        controllerAs: "vm"
      });
    }
    
    angular
      .module("myApp")
      .config(config)
      .controller("DetailsPendingStoreIndent.Controller", detailsPendingStoreIndentController);
  })();