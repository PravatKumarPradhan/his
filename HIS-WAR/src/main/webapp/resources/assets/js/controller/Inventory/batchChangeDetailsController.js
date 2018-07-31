(function () {
    "use strict";
  
    function batchChangeDetailsController($scope, $rootScope, $http, CONSTANTS, GenericService, commonDetailService, growl) {
      var self = this;
      
      //Default Constructor
      function initializeController() {
        $rootScope.loginpage = true;
        self.model = {
         
  
         
      };
  self.model.fromBatchChange = fromBatchChange;
      fromBatchChange();
    }

    function fromBatchChange() {
        var id = commonDetailService.getDataId();
        var URI = CONSTANTS.INVENTORY.STORE.BATCH_CHANGE.DETAILS_API + id;
        var data = {};
        GenericService.serviceAction("GET", URI, data).then(
          function (response) { 
            self.model.batchChangeDetail = response.data;
          });
      } 
  
    
      initializeController();
    }
  
    function config($stateProvider) {
      $stateProvider.state("detailBatchChange", {
        url: "/detailBatchChange",
        templateUrl: 'views/PharmacyInventory/Store/details-batch-change.html',
        controller: "BatchChangeDetails.Controller",
        controllerAs: "vm"
      });
    }
  
    angular
      .module("myApp")
      .config(config)
      .controller("BatchChangeDetails.Controller", batchChangeDetailsController);
  })();