(function () {
  "use strict";

  function viewTAController($scope, $rootScope, $http, $filter, $state, GenericService, commonDetailService, growl, CONSTANTS) {
    var self = this;

    function initializeController() {
      $rootScope.loginpage = true;

      self.model = {
        comparisonDetailsModal: {}
      };

      self.model.ViewTermsModal = ViewTermsModal;
      self.model.NavigateToBack = NavigateToBack;
      self.model.OpenPopup = OpenPopup;
      self.model.ClosePopup = ClosePopup;

      PopulateDropdown();
    }

    var ViewTermsModal = function (item, viewModal) {
      self.model.comparisonDetailsModal.currentItemId = item.id;

      switch(viewModal){
        case "warrantyPeriod":
          self.model.comparisonDetailsModal.warrantyPeriod = item.warrantyPeriod;
          self.model.comparisonDetailsModal.vendorDetailsForWarranty = JSON.parse(JSON.stringify(item.vendorDetails));
        break;
        case "supportTerms":
          self.model.comparisonDetailsModal.supportTerms = item.supportTerms;
          self.model.comparisonDetailsModal.vendorDetailsForSupport = JSON.parse(JSON.stringify(item.vendorDetails));
        break;
        case "techSpecs":
          self.model.comparisonDetailsModal.technicalSpecifications = item.technicalSpecifications;
          self.model.comparisonDetailsModal.vendorDetailsForTechSpec = JSON.parse(JSON.stringify(item.vendorDetails));
        break;
        case "paymentTerms":
          self.model.comparisonDetailsModal.paymentTerms = item.paymentTerms;
          self.model.comparisonDetailsModal.vendorDetailsForPayment = JSON.parse(JSON.stringify(item.vendorDetails));
        break;
      }
    }

    var GetApprovalDetails = function (approvalId) {
      var URI = CONSTANTS.PROCUREMENT.QUOTATION.TECHNICAL_APPROVAL.DETAILS_API + approvalId;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          self.model.approvalId = response.data.id;
          self.model.qrNumber = response.data.qrNumber;
          self.model.itemInfo = response.data.items;

          self.model.itemInfo.forEach(function(item) {
              var vendorDetailsHash = {};
              var vendorDetails = item.vendorDetails;
              vendorDetails.forEach(function(vendorDetail){
                if (vendorDetail.accept == true) {
                  vendorDetail.setVendorStatus = 1;
                } else if (vendorDetail.reject == true) {
                  vendorDetail.setVendorStatus = 2;
                } else if (vendorDetail.onHold == true) {
                  vendorDetail.setVendorStatus = 3;
                } else {
                  vendorDetail.setVendorStatus = 1;
                }
              });
          });

          $rootScope.stopLoader();
        },
        function (err) {
          $rootScope.stopLoader();
          growl.error(err.data.message);
        });
    }

    var PopulateDropdown = function () {
      try {
        $rootScope.startLoader();
        var approvalId = commonDetailService.getDataId();
        if (!!approvalId) {
          $rootScope.stopLoader();
          commonDetailService.setDataId(null);
          GetApprovalDetails(approvalId);
        }
      } catch (error) {
        growl.error(error);
      }
    }

    var OpenPopup = function (action, modal) {
      self.model.action = action;
      var popup = angular.element('#' + modal);
      popup.modal('show');
    }

    var ClosePopup = function (action, modal) {
      self.model.action = action;
      var popup = angular.element('#' + modal);
      popup.modal('hide');
    }

    var NavigateToBack = function () {
      $state.go('technicalApproval');
    }

    initializeController();
  }
 
  function config($stateProvider) {
    $stateProvider.state("viewtechnicalApproval", {
      url: "/viewtechnicalApproval",
      templateUrl: 'views/procurement/quotation/view-Technical-Approval.html',
      controller: "ViewtechnicalApproval.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("ViewtechnicalApproval.Controller", viewTAController);
})();