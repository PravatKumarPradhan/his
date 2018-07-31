(function () {
  "use strict";

  function detailsGRNController($rootScope, $scope, $http, CONSTANTS, $state, GenericService, commonDetailService, growl) {
    var self = this;

    //Default Constructor
    function initializeController() {
      $rootScope.loginpage = true;

      self.model = {
        otherCharges: [],
        GRNUom: [],
        bonusUoms: [],
        grnStagedQuantity: [],
        poList: []
      };

      self.model.NavigateToBack = NavigateToBack;
      self.model.ViewOtherCharges = ViewOtherCharges;
      self.model.ViewGRNUom = ViewGRNUom;
      self.model.ViewBonusUom = ViewBonusUom;
      self.model.ViewStagedQuantity = ViewStagedQuantity;
      self.model.ViewPOList = ViewPOList;

      GetGRN(commonDetailService.getDataId());
    }

    var GetGRN = function (grnId) {
      commonDetailService.setDataId(null);

      var URI = CONSTANTS.PROCUREMENT.QUOTATION.GRN.GET_GRN_API + grnId;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          self.model.grnList = response.data;
          $rootScope.stopLoader();
        },
        function (err) {
          growl.error(err.data.message);
          $rootScope.stopLoader();
        });
    }

    var ViewOtherCharges = function (item){
      self.model.grnList.items.find(function(x){
        if (item.id == x.id) {
          self.model.otherCharges = x.otherCharges;
        }
      });
    }

    var ViewGRNUom = function (item) {
      self.model.grnList.items.find(function(x){
        if (item.id == x.id) {
          self.model.GRNUom = x.grnUom;
        }
      });
    }

    var ViewBonusUom = function (item) {
      self.model.grnList.items.find(function(x){
        if (item.id == x.id) {
          self.model.bonusUoms = x.bonusUom;
        }
      });
    }

    var ViewStagedQuantity = function (item) {
      self.model.grnList.items.find(function(x){
        if (item.id == x.id) {
          self.model.grnStagedQuantity = x.grnStagedQuantity;
        }
      });
    }

    var ViewPOList = function (item) {
      self.model.grnList.items.find(function(x){
        if (item.id == x.id) {
          self.model.poList = x.poList;
        }
      });
    }

    var NavigateToBack = function (){
      $state.go('GRN');
    }

    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("GRNdetails", {
      url: "/GRNdetails",
      templateUrl: 'views/procurement/quotation/GRN-details.html',
      controller: "DetailsGRN.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("DetailsGRN.Controller", detailsGRNController);
})();