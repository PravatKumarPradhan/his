(function () {
  "use strict";

  function detailsEnquirySentController($rootScope, $scope, $http, CONSTANTS, $state, GenericService, commonDetailService, growl) {
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
      self.model.ViewAccessories = ViewAccessories;
      self.model.ViewTerms = ViewTerms;

      GetQuotationEnquiry(commonDetailService.getDataId());
    }

    var GetQuotationEnquiry = function (quotationId) {
      commonDetailService.setDataId(null);

      var URI = CONSTANTS.PROCUREMENT.QUOTATION.ENQUIRY_SENT_TO_VENDOR.SAVE_API + quotationId;

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
          self.model.prDetailList = x.prList;
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
      $state.go('enquirySentToVendor');
    }

    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("detailsEnquirySentToVendor", {
      url: "/detailsEnquirySentToVendor",
      templateUrl: 'views/procurement/quotation/details-enquiry-sentto-vendor.html',
      controller: "DetailsEnquirySent.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("DetailsEnquirySent.Controller", detailsEnquirySentController);
})();