(function () {
  "use strict";

  function detailsQFillInController($rootScope, $scope, $http, CONSTANTS, $state, GenericService, commonDetailService, growl) {
    var self = this;

    //Default Constructor
    function initializeController() {
      $rootScope.loginpage = true;

      self.model = {
        prDetailList: [],
        expectedAccessories: [],
        termsVisible: 1
      };
      self.model.NavigateToBack = NavigateToBack;
      self.model.ViewAccessories = ViewAccessories;
      self.model.ViewTerms = ViewTerms;

      GetQuotationEnquiry(commonDetailService.getDataId());
    }

    var GetQuotationEnquiry = function (quotationId) {
      commonDetailService.setDataId(null);

      var URI = CONSTANTS.PROCUREMENT.QUOTATION.QUOTATION_FILL_IN.DETAILS_API + quotationId;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          self.model.quotationList = response.data;
          $rootScope.stopLoader();
        },
        function (err) {
          growl.error(err.data.message);
          $rootScope.stopLoader();
        });
    }

    var ViewAccessories = function (item) {
      self.model.quotationList.items.find(function(x){
        if (item.id == x.id) {
          self.model.expectedAccessories = x.expectedAccessories;
        }
      });
    }

    var ViewTerms = function (item) {
      self.model.termsOfItem = item;
      self.model.quotationList.items.find(function(x){
        if (item.itemId == x.itemId) {
          self.model.warrantyPeriod = x.warrantyPeriod;
          self.model.supportTerms = x.supportTerms;
          self.model.paymentTerms = x.paymentTerms;
          self.model.technicalSpecification = x.technicalSpecification;
          self.model.vendorWarrantyPeriod = x.vendorWarrantyPeriod;
          self.model.vendorExpectedSupport = x.vendorExpectedSupport;
          self.model.vendorExpectedPayment = x.vendorExpectedPayment;
          self.model.vendorTechnicalSpecification = x.vendorTechnicalSpecification;
        }
      });
    }

    var NavigateToBack = function (){
      $state.go('quotationFillIn');
    }

    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("qfillInDetails", {
      url: "/qfillInDetails",
      templateUrl: 'views/procurement/quotation/quotation-fillin-details.html',
      controller: "QfillInDetails.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("QfillInDetails.Controller", detailsQFillInController);
})();