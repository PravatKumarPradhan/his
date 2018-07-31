(function () {
  "use strict";

  function detailsVendorNegotiationController($scope, $rootScope, $http, $filter, $state, GenericService, commonDetailService, growl, StatusService, CONSTANTS) {
    var self = this;

    function initializeController() {
      $rootScope.loginpage = true;

      self.model = {
        comparisonDetailsModal: {}
      };

      StatusService.GetStatus().then(function (status) {
        self.model.statuses = status;
      });

      self.model.SaveRecord = SaveRecord;
      self.model.ViewTermsModal = ViewTermsModal;
      self.model.NavigateToBack = NavigateToBack;
      self.model.OpenPopup = OpenPopup;
      self.model.ClosePopup = ClosePopup;

      PopulateDropdown();
    }

    var PopulateDropdown = function () {
      try {
        $rootScope.startLoader();
        var approvalId = commonDetailService.getDataId();
        if (!!approvalId) {
          $rootScope.stopLoader();
          commonDetailService.setDataId(null);
          GetVendorDetails(approvalId);
        }
      } catch (error) {
        growl.error(error);
      }
    }

    var GetVendorDetails = function (approvalId) {
      var URI = CONSTANTS.PROCUREMENT.QUOTATION.VENDOR_NEGOTIATION.DETAILS_API + approvalId;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          self.model.nigotiationId = response.data.id;
          self.model.qrNumber = response.data.qrNumber;
          self.model.qrDate = response.data.qrDate;
          self.model.vqrNumber = response.data.vqrNumber;
          self.model.vqrDate = response.data.vqrDate;
          self.model.storeName = response.data.storeName;
          self.model.vendorName = response.data.vendorName;
          self.model.refNumber = response.data.refNumber;
          self.model.refDate = response.data.refDate;
          self.model.itemInfo = response.data.items;
          self.model.columnNumber = response.data.items[0].negotiationDetails.length;
          self.model.itemInfo.forEach(function(item) {
            var negotiationDetails = item.negotiationDetails;
            var dataLength = negotiationDetails.length;
            var lastData = negotiationDetails[dataLength-1];
            if (negotiationDetails.length < 5 && negotiationDetails.length > 0 && lastData.accept != true) {
              negotiationDetails.push({
                "negotiationDate": moment(new Date()).format("DD/MM/YYYY"),
                "negotiationCop": lastData.negotiationCop,
                "negotiationRemark": lastData.negotiationRemark,
                "negotiationWarrantyPeriod": lastData.negotiationWarrantyPeriod,
                "negotiationSupportTerms": lastData.negotiationSupportTerms,
                "negotiationTechSpec": lastData.negotiationTechSpec,
                "negotiationPaymentTerms": lastData.negotiationPaymentTerms,
                "accept": lastData.accept,
                "reject": lastData.reject,
                "onHold": lastData.onHold,
                "rejectNote": lastData.rejectNote
              });
            } else if (negotiationDetails.length <= 0) {
              negotiationDetails.push({
                "negotiationDate": moment(new Date()).format("DD/MM/YYYY"),
                "negotiationCop": item.vendorCop,
                "negotiationRemark": item.vendorRemark,
                "negotiationWarrantyPeriod": item.vendorWarrantyPeriod,
                "negotiationSupportTerms": item.vendorSupportTerms,
                "negotiationTechSpec": item.vendorTechSpec,
                "negotiationPaymentTerms": item.vendorPaymentTerms,
                "accept": item.accept,
                "reject": item.reject,
                "onHold": item.onHold,
                "rejectNote": ""
              });
            }

            negotiationDetails.forEach(function(detail){
              if (detail.accept == true) {
                detail.negotitateStatus = 1;
              } else if (detail.reject == true) {
                detail.negotitateStatus = 2;
              } else if (detail.onHold == true) {
                detail.negotitateStatus = 3;
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

    var ViewTermsModal = function (item, viewModal) {
      self.model.comparisonDetailsModal.currentItemId = item.id;

      switch(viewModal){
        case "cop":
          self.model.title = "Expected COP";
          self.model.vendorTitle = "Vendor COP";
          self.model.comparisonDetailsModal.term = item.cop;
          self.model.comparisonDetailsModal.vendorTerm = item.vendorCop;
        break;
        case "warrantyPeriod":
          self.model.title = "Expected Warranty Period";
          self.model.vendorTitle = "Vendor Warranty Period";
          self.model.comparisonDetailsModal.term = item.warrantyPeriod;
          self.model.comparisonDetailsModal.vendorTerm = item.vendorWarrantyPeriod;
        break;
        case "supportTerms":
          self.model.title = "Expected Support Terms";
          self.model.vendorTitle = "Vendor Support Terms";
          self.model.comparisonDetailsModal.term = item.supportTerms;
          self.model.comparisonDetailsModal.vendorTerm = item.vendorSupportTerms;
        break;
        case "techSpecs":
          self.model.title = "Expected Technical Specification";
          self.model.vendorTitle = "Vendor Technical Specification";
          self.model.comparisonDetailsModal.term = item.technicalSpecifications;
          self.model.comparisonDetailsModal.vendorTerm = item.vendorTechSpec;
        break;
        case "paymentTerms":
          self.model.title = "Expected Payment Terms";
          self.model.vendorTitle = "Vendor Payment Terms";
          self.model.comparisonDetailsModal.term = item.paymentTerms;
          self.model.comparisonDetailsModal.vendorTerm = item.vendorPaymentTerms;
        break;
        case "remark":
          self.model.title = "Expected Remark";
          self.model.vendorTitle = "Vendor Remark";
          self.model.comparisonDetailsModal.term = item.remark;
          self.model.comparisonDetailsModal.vendorTerm = item.vendorRemark;
        break;
      }
    }

    var SaveRecord = function (action) {
      if (action  == 'save') {
        CreateVendorNegotiation();
      } else if (action  == 'send' && ValidateData()) {
        CreateVendorNegotiation();
      }
    }

    var CreateVendorNegotiation = function () {
      var URI = CONSTANTS.PROCUREMENT.QUOTATION.TECHNICAL_APPROVAL.SAVE_API;

      var data = {
        "id": self.model.nigotiationId,
        "statusId": setStatus('Pending'),
        "items": []
      };

      angular.forEach(self.model.itemInfo, function (item, key) {
        var innerData = {
          "id": item.id,
          "negotiationDetails": {}
        }

        angular.forEach(item.negotiationDetails, function(detail){
          if (!detail.id) {
            innerData.negotiationDetails = {
              "negotiationDate": moment(detail.negotiationDate).format("YYYY-MM-DD"),
              "negotiationCop": parseFloat(detail.negotiationCop),
              "negotiationWarrantyPeriod": detail.negotiationWarrantyPeriod,
              "negotiationSupportTerms": detail.negotiationSupportTerms,
              "negotiationTechSpec": detail.negotiationTechSpec,
              "negotiationPaymentTerms": detail.negotiationPaymentTerms,
              "negotiationRemark": detail.negotiationRemark,
              "rejectNote": detail.negotiationRemark,
              "accept": false,
              "reject": false,
              "onHold": false
            }

            if (detail.negotitateStatus == 1) {
              innerData.negotiationDetails["accept"] = true;
              innerData.negotiationDetails["rejectNote"] = null;
            } else if(detail.negotitateStatus == 2) {
              innerData.negotiationDetails["reject"] = true;
            } else if (detail.negotitateStatus == 3) {
              innerData.negotiationDetails["onHold"] = true;
              innerData.negotiationDetails["rejectNote"] = null;
            }
          }
        });

        data.items.push(innerData);
      });

      $rootScope.startLoader();
      GenericService.serviceAction("POST", URI, data).then(
        function (response) {
          $rootScope.stopLoader();
          NavigateToBack();
          growl.success(response.data.message);
        },
        function (err) {
          $rootScope.stopLoader();
          growl.error(err.data.message);
        });
    }

    var ValidateData = function () {
      var isValid = true;
      angular.forEach(self.model.itemInfo, function (item, key) {
        angular.forEach(item.negotiationDetails, function(detail){
          if (!detail.id) {
            if (detail.negotitateStatus == 3) {
              growl.error('All Vendors should be Accepted or Rejected');
              isValid = false;
            }
          }
        });
      });

      return isValid;
    }

    var setStatus = function (x) {
      var status = self.model.statuses.find(function (obj) {
        return obj.status == x;
      });

      return status.id;
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
      $state.go('vendorNegotitation');
    }

    initializeController();
  }
 
  function config($stateProvider) {
    $stateProvider.state("detailsVendorNegotitation", {
      url: "/detailsVendorNegotitation",
      templateUrl: 'views/procurement/quotation/details-vendor-negotitation.html',
      controller: "DetailsVN.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("DetailsVN.Controller", detailsVendorNegotiationController);
})();