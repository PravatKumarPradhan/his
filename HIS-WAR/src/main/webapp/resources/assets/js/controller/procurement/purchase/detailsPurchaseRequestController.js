(function () {
  "use strict";

  function detailsPurchaseRequestController($rootScope, $scope, $http, CONSTANTS, $state, GenericService, PurchaseRequestService) {
    var self = this;

    //Default Constructor
    function initializeController() {
      $rootScope.loginpage = true;

      self.model = {};
      self.model.NavigateToBack = NavigateToBack;
      GetPurchaseRequest(PurchaseRequestService.GetPurchaseId());
    }

    var GetPurchaseRequest = function (purchaseId) {
      PurchaseRequestService.SetPurchaseId(null);

      var URI = CONSTANTS.PROCUREMENT.PURCHASE.PURCHASE_REQUEST.SAVE_API + purchaseId;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          self.model.purchaseRequests = response.data;
          $rootScope.stopLoader();
        },
        function (err) {
          $rootScope.stopLoader();
        });
    }

    var NavigateToBack = function (){
      $state.go('purchaseRequest');
    }

    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("detailsPurchaseRequest", {
      url: "/detailsPurchaseRequest",
      templateUrl: 'views/procurement/purchase/details-purchase-request.html',
      controller: "DetailsPurchaseRequest.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("DetailsPurchaseRequest.Controller", detailsPurchaseRequestController);
})();