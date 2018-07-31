(function () {
  "use strict";

  function detailsQuotationController($rootScope, $scope, $http, CONSTANTS, $state, GenericService, commonDetailService, growl) {
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
      self.model.ViewPRList = ViewPRList;
      self.model.ViewTerms = ViewTerms;
      self.model.ViewAccessories = ViewAccessories;

      GetQuotationEnquiry(commonDetailService.getDataId());
    }

    var GetQuotationEnquiry = function (quotationId) {
      commonDetailService.setDataId(null);

      var URI = CONSTANTS.PROCUREMENT.QUOTATION.QUOTATION_ENQUIRY.SAVE_API + quotationId;

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

    var ViewPRList = function (item) {
      self.model.quotationList.items.find(function(x){
        if (item.id == x.id) {
          self.model.prDetailList = x.prDetails;
        }
      });
    }

    var ViewTerms = function (item) {
      self.model.quotationList.items.find(function(x){
        if (item.id == x.id) {
          self.model.warrantyPeriod = x.warrantyPeriod;
          self.model.supportTerms = x.supportTerms;
          self.model.paymentTerms = x.paymentTerms;
          self.model.technicalSpecification = x.technicalSpecification;
        }
      });
    }

    var ViewAccessories = function (item) {
      self.model.quotationList.items.find(function(x){
        if (item.id == x.id) {
          self.model.expectedAccessories = x.expectedAccessories;
        }
      });
    }

    var NavigateToBack = function (){
      $state.go('quotationEnquiry');
    }

    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("quotationEDetails", {
      url: "/quotationEDetails",
      templateUrl: 'views/procurement/quotation/quotation-enquiry-details.html',
      controller: "DetailsQuotation.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("DetailsQuotation.Controller", detailsQuotationController);
})();