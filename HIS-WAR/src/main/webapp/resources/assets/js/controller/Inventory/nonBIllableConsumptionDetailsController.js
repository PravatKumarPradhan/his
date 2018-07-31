(function () {
    "use strict";
  
    function nonBIllableConsumptionDetailsController($scope, $rootScope, $http,commonDetailService, CONSTANTS, GenericService, growl) {
      var self = this;
      
      //Default Constructor
      function initializeController() {
        $rootScope.loginpage = true;
        self.model = {
         
  
         
      };
  
      fromNonBillableConsumption()
    }


    function fromNonBillableConsumption() {
        var id = commonDetailService.getDataId();
        var URI = CONSTANTS.INVENTORY.NURSING.NON_BILLABLE_CONSUMPTION.DETAIL_API  + id;
        var data = {};
        GenericService.serviceAction("GET", URI, data).then(
          function (response) { 
            self.model.consumptionDetail = response.data;
          });
      } 
  
      initializeController();
    }
  
    function config($stateProvider) {
      $stateProvider.state("detailsnonbillableConsumption", {
        url: "/detailsnonbillableConsumption",
        templateUrl: 'views/PharmacyInventory/InPatient/details-non-billable-consumption.html',
        controller: "NonBIllableConsumptionDetails.Controller",
        controllerAs: "vm"
      });
    }
  
    angular
      .module("myApp")
      .config(config)
      .controller("NonBIllableConsumptionDetails.Controller", nonBIllableConsumptionDetailsController);
  })(); 