(function () {
  "use strict";

  function detailsLocalPRController($rootScope, $scope, $http, CONSTANTS, $state, GenericService, commonDetailService, growl) {
    var self = this;

    //Default Constructor
    function initializeController() {
      $rootScope.loginpage = true;

      self.model = {};
      self.model.NavigateToBack = NavigateToBack;
      GetPurchaseRequest(commonDetailService.getDataId());
    }

    var GetPurchaseRequest = function (purchaseId) {
      commonDetailService.setDataId(null);

      var URI = CONSTANTS.PROCUREMENT.PURCHASE.LOCAL_PURCHASE_REQUEST.SAVE_API + purchaseId;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          self.model.localPurchaseRequests = response.data;
          $rootScope.stopLoader();
        },
        function (err) {
          $rootScope.stopLoader();
          growl.error(err.data.message);
        });
    }

    var NavigateToBack = function (){
      $state.go('localPurchhaseRequest');
    }

    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("detailslocalPurchhaseRequest", {
      url: "/detailslocalPurchhaseRequest",
      templateUrl: 'views/procurement/purchase/details-local-purchase-request.html',
      controller: "DetailsLocalPR.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("DetailsLocalPR.Controller", detailsLocalPRController);
})();