(function () {
  "use strict";

  function productRestrictionController($scope, $rootScope, $state) {
      var self = this;

      //Default Constructor
      function initializeController() {
          $rootScope.loginpage = true;

          self.model = {};

          self.model.NavigateToProductCategoryMapping = NavigateToProductCategoryMapping;
          self.model.NavigateToItemRestriction = NavigateToItemRestriction;
      }

      var NavigateToProductCategoryMapping = function () {
          $state.go('productRestriction.productCategoryMapping');
      }

      var NavigateToItemRestriction = function () {
          $state.go('productRestriction.itemRestriction');
      }

      initializeController();
  }

  function config($stateProvider) {
      $stateProvider.state("productRestriction", {
          url: "/productRestriction",
          templateUrl: 'views/PharmacyInventory/StockAdjustment/product-restriction.html',
          controller: "ProductRestriction.Controller",
          controllerAs: "vm"
      });
  }

  angular
      .module("myApp")
      .config(config)
      .controller("ProductRestriction.Controller", productRestrictionController)
})();