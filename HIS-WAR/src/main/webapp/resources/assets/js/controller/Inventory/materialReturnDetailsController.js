(function () {
    "use strict";
  
    function materialReturnDetailsController($scope, $rootScope, $http, CONSTANTS, commonDetailService, GenericService, growl) {
      var self = this;
      
      //Default Constructor
      function initializeController() {
        $rootScope.loginpage = true;
        self.model = {
            requestData: [],
  
         
      };
  self.model.fromMaterialReturn =fromMaterialReturn;
      fromMaterialReturn()
    }

    function fromMaterialReturn() {  
        
        var id = commonDetailService.getDataId();
        var URI = CONSTANTS.INVENTORY.STORE.MATERIAL_RETURN.DETAILS_API + id;
        var data = {};
        GenericService.serviceAction("GET", URI, data).then(
            function (response) { 
                self.model.materialReturnDetail = response.data; 
            });
    }


    
      initializeController();
    }
  
    function config($stateProvider) {
      $stateProvider.state("detailsMaterialReturn", {
        url: "/detailsMaterialReturn",
        templateUrl: 'views/PharmacyInventory/InPatient/details-material-return.html',
        controller: "MaterialReturnDetails.Controller",
        controllerAs: "vm"
      });
    }
  
    angular
      .module("myApp")
      .config(config)
      .controller("MaterialReturnDetails.Controller", materialReturnDetailsController);
  })();
 