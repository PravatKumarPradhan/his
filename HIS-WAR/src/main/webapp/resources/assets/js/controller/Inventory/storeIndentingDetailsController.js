(function () {
  "use strict";
  var CACHE = {};

  function storeIndentingDetailsController($scope, $http, CONSTANTS, GenericService, storeIndentService) {
    var self = this;
    var selectItemList = [];

    //Default Constructor
    function initializeController() {
      self.model = {
        selectItemList: [],
        requestData: [],
        openingBalanceData: [],
        productCategories: []
      };
       
      // getStoreList();
      fromStoreIndents();
    }

    function fromStoreIndents() {
      var id = storeIndentService.getIndentId();
      var URI = CONSTANTS.INVENTORY.STORE.STORE_INDENT.DETAILS_API + id;
      var data = {};
      GenericService.serviceAction("GET", URI, data).then(
        function (response) {
          self.model.storeData = storeIndentService.passStoreIndentId(id);
          self.model.indentDetail = response.data;
        });
    } 

    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("DetailsStoreIndenting", {
      url: "/DetailsStoreIndenting",
      templateUrl: 'views/PharmacyInventory/InPatient/details-store-indenting.html',
      controller: "StoreIndentingDetails.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("StoreIndentingDetails.Controller", storeIndentingDetailsController);
})();