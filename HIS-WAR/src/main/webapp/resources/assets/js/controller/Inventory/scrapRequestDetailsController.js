(function () {
    "use strict";
  
    function scrapRequestDetailsController($scope, $rootScope, $http,commonDetailService, CONSTANTS, GenericService, growl) {
      var self = this;
      
      //Default Constructor
      function initializeController() {
        $rootScope.loginpage = true;
        self.model = {
         
  
         
      };
  
  self.model.fromScrapRequest = fromScrapRequest;
  fromScrapRequest();
    }

    function fromScrapRequest() {
        var id = commonDetailService.getDataId();
        var URI = CONSTANTS.INVENTORY.SCRAP.SCRAP_REQUEST.DETAIL_API + id;
        var data = {};
        GenericService.serviceAction("GET", URI, data).then(
          function (response) {
            self.model.scrapDetailData = response.data;
            self.model.scrapDetailData.items.forEach(function (item) {
              item.isDisabled = true;
            });
          });
      }
    
      initializeController();
    }
  
    function config($stateProvider) {
      $stateProvider.state("detailsScrapApproval", {
        url: "/detailsScrapApproval",
        templateUrl: 'views/PharmacyInventory/Store/details-scrap-approval.html',
        controller: "ScrapRequestDetails.Controller",
        controllerAs: "vm"
      });
    }
  
    angular
      .module("myApp")
      .config(config)
      .controller("ScrapRequestDetails.Controller", scrapRequestDetailsController);
  })();