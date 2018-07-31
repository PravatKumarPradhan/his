(function () {
    "use strict";
    var CACHE = {};
  
    function deatilsPatientIndentController($scope, $state, $rootScope, $http, $filter, CONSTANTS, StatusService, GenericService, storeIndentService, growl, commonDetailService) {
      var self = this;
      var mainStoreList = [];
  
      //Default Constructor
      function initializeController() {
        $rootScope.loginpage = true;
        self.model = {};
  
        StatusService.GetStatus().then(function (status) {
          self.model.Status = status;
        });

        GetPatientIndentDetails(commonDetailService.getDataId());
  
      }
  
      var GetPatientIndentDetails = function (itemId) {
  
        var URI = CONSTANTS.PHARMACY.OP.PATIENT_INDENT.DETAIL_API + itemId;
        $rootScope.startLoader();
        GenericService.serviceAction("GET", URI, {}).then(
          function (response) {
            self.model.indentId = response.data.id;
            self.model.ItemDetailList = response.data.items;
          });
        $rootScope.stopLoader();
      }
  
      var NavigteToPatientIndent = function () {
        $state.go('patientIndent');
      }
  
      initializeController();
    }
  
  
    function config($stateProvider) {
      $stateProvider.state("deatilsPatientIndent", {
        url: "/deatilsPatientIndent",
        templateUrl: 'views/PharmacyInventory/InPatient/details-patient-indent.html',
        controller: "DeatilsPatientIndent.Controller",
        controllerAs: "vm"
      });
    }
  
    angular
      .module("myApp")
      .config(config)
      .controller("DeatilsPatientIndent.Controller", deatilsPatientIndentController);
  })();
