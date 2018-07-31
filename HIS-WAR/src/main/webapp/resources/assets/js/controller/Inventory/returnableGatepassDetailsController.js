(function () {
    "use strict";
  
    function returnableGatepassDetailsController($scope,commonDetailService, $rootScope, $http, CONSTANTS, GenericService, growl) {
      var self = this;
      
      //Default Constructor
      function initializeController() {
        $rootScope.loginpage = true;
        self.model = {
         
  
         
      };
  self.model.returnableGatepassData = [];
      fromReturnableGatepass();
    }

    function fromReturnableGatepass() {
        var id = commonDetailService.getDataId();
        var URI = CONSTANTS.INVENTORY.STORE.RETURNABLE_GATEPASS.DETAIL_API  + id;
        var data = {};
        GenericService.serviceAction("GET", URI, data).then(
          function (response) { 
            self.model.returnableGatepassData = response.data;
          });
      } 
  
    
      initializeController();
    }
  
    function config($stateProvider) {
      $stateProvider.state("detailsReturnableGatepass", {
        url: "/detailsReturnableGatepass",
        templateUrl: 'views/PharmacyInventory/InPatient/details-returnable-gatepass.html',
        controller: "ReturnableGatepassDetails.Controller",
        controllerAs: "vm"
      });
    }
  
    angular
      .module("myApp")
      .config(config)
      .controller("ReturnableGatepassDetails.Controller", returnableGatepassDetailsController);
  })();
  