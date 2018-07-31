(function () {
  "use strict";

  function detailsPOController($rootScope, $scope, $http, CONSTANTS, $state, GenericService, commonDetailService, growl) {
    var self = this;

    //Default Constructor
    function initializeController() {
      $rootScope.loginpage = true;

      self.model = {
        otherCharges: [],
        stagedQuantity: [],
        POUoms: [],
        bonusUoms: [],
        prList: []
      };
      self.model.NavigateToBack = NavigateToBack;
      self.model.ViewOtherCharges = ViewOtherCharges;
      self.model.ViewStagedQuantity = ViewStagedQuantity;
      self.model.ViewPOUom = ViewPOUom;
      self.model.ViewBonusUom = ViewBonusUom;
      self.model.ViewPRList = ViewPRList;

      GetPurchaseOrder(commonDetailService.getDataId());
    }

    var GetPurchaseOrder = function (purchaseId) {
      commonDetailService.setDataId(null);

      var URI = CONSTANTS.PROCUREMENT.QUOTATION.PURCHSE_ORDER.SAVE_API + purchaseId;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          self.model.purchaseOrders = response.data;
          $rootScope.stopLoader();
        },
        function (err) {
          growl.error(err.data.message);
          $rootScope.stopLoader();
        });
    }

    var ViewOtherCharges = function (item){
      self.model.purchaseOrders.items.find(function(x){
        if (item.id == x.id) {
          self.model.otherCharges = x.otherCharges;
        }
      });
    }

    var ViewPRList = function (item) {
      self.model.purchaseOrders.items.find(function(x){
        if (item.id == x.id) {
          self.model.prList = x.prId;
        }
      });
    }

    var ViewStagedQuantity = function (item) {
      self.model.purchaseOrders.items.find(function(x){
        if (item.id == x.id) {
          x.purchaseOrderStagedDto.forEach(function(date){
            date.stagedDate = moment(date.stagedDate).format("DD/MM/YYYY");
          });
          
          self.model.stagedQuantity = x.purchaseOrderStagedDto;
        }
      });
    }

    var ViewPOUom = function (item) {
      self.model.purchaseOrders.items.find(function(x){
        if (item.id == x.id) {
          self.model.POUoms = x.poUom;
        }
      });
    }

    var ViewBonusUom = function (item) {
      self.model.purchaseOrders.items.find(function(x){
        if (item.id == x.id) {
          self.model.bonusUoms = x.bonusUom;
        }
      });
    }

    var NavigateToBack = function (){
      $state.go('POCreation');
    }

    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("detailsPOCreation", {
      url: "/detailsPOCreation",
     templateUrl: 'views/procurement/quotation/details-PO-creation.html',
      controller: "DetailsPO.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("DetailsPO.Controller", detailsPOController);
})();