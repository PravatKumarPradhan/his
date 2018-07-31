(function () {
  "use strict";

  function detailsTAController($scope, $rootScope, $http, $filter, $state, GenericService, commonDetailService, growl, StatusService, CONSTANTS) {
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
      self.model.SaveTerms = SaveTerms;
      self.model.NavigateToBack = NavigateToBack;
      self.model.PopupAction = PopupAction;

      PopulateDropdown();
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

    var GetApprovalDetails = function (approvalId) {
      var URI = CONSTANTS.PROCUREMENT.QUOTATION.TECHNICAL_APPROVAL.DETAILS_API + approvalId;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          self.model.approvalId = response.data.id;
          self.model.qrNumber = response.data.qrNumber;
          self.model.itemInfo = response.data.items;

          self.model.itemInfo.forEach(function(item) {
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

    var SaveTerms = function(modelName) {
      var saveTerm = self.model.itemInfo.find(function(item){
        return (self.model.comparisonDetailsModal.currentItemId == item.id)
      });

      if(saveTerm){
        switch(modelName){
          case "warrantyPeriodModal":
            saveWarrentyPeriod(saveTerm);
          break;
          case "supportTermsModal":
            saveSupportTerms(saveTerm);
          break;
          case "paymentTermsModal":
            savePaymentTerms(saveTerm);
          break;
          case "techSpecModal":
            saveTechnicalSpec(saveTerm);
          break;
        } 
      }
    }

    var saveWarrentyPeriod = function(saveTerm){
      var data = self.model.comparisonDetailsModal.vendorDetailsForWarranty;
      self.model.comparisonDetailsModal.vendorDetailsForWarranty.forEach(function(vendorDetail){
        var obj = saveTerm.vendorDetails.find(function(vendor){
          return (vendor.vendorId == vendorDetail.vendorId);
        });
        if(obj){
          obj.warrantyFlag = vendorDetail.warrantyFlag;
          obj.warrantyRejectNote = vendorDetail.warrantyRejectNote;
          self.model.PopupAction('Close', 'warrantyPeriodModal');
        }
      });
    }

    var saveSupportTerms = function(saveTerm){
      var data = self.model.comparisonDetailsModal.vendorDetailsForSupport;
      self.model.comparisonDetailsModal.vendorDetailsForSupport.forEach(function(vendorDetail){
        var obj = saveTerm.vendorDetails.find(function(vendor){
          return (vendor.vendorId == vendorDetail.vendorId);
        });
        if(obj){
          obj.supportFlag = vendorDetail.supportFlag;
          obj.supportRejectNote = vendorDetail.supportRejectNote;
          self.model.PopupAction('Close', 'SupportTermsModal');
        }
      })
    }

    var saveTechnicalSpec = function(saveTerm){
      var data = self.model.comparisonDetailsModal.vendorDetailsForTechSpec;
      self.model.comparisonDetailsModal.vendorDetailsForTechSpec.forEach(function(vendorDetail){
        var obj = saveTerm.vendorDetails.find(function(vendor){
          return (vendor.vendorId == vendorDetail.vendorId);
        });
        if(obj){
          obj.technicalSpecFlag = vendorDetail.technicalSpecFlag;
          obj.technicalSpecRejectNote = vendorDetail.technicalSpecRejectNote;
          self.model.PopupAction('Close', 'TechnicalSpecificationModal');
        }
      })
    }

    var savePaymentTerms = function(saveTerm){
      var data = self.model.comparisonDetailsModal.vendorDetailsForPayment;
      self.model.comparisonDetailsModal.vendorDetailsForPayment.forEach(function(vendorDetail){
        var obj = saveTerm.vendorDetails.find(function(vendor){
          return (vendor.vendorId == vendorDetail.vendorId);
        });
        if(obj){
          obj.paymentFlag = vendorDetail.paymentFlag;
          obj.paymentRejectNote = vendorDetail.paymentRejectNote;
          self.model.PopupAction('Close', 'PaymentTermsModal');
        }
      })
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

    var CreateTechnicalApproval = function () {
      var URI = CONSTANTS.PROCUREMENT.QUOTATION.TECHNICAL_APPROVAL.SAVE_API + self.model.approvalId;

      var data = {
        // "qrId": self.model.qrId,
        // "storeId": self.model.storeId,
        // "assetTypeId": self.model.assetTypeId,
        // "statusId": setStatus('Completed'),
        "items": []
      };

      angular.forEach(self.model.itemInfo, function (item, key) {
        var innerData = {
          "id": item.id,
          "vendorDetails": []
        }

        for (var vendorDetail in item.vendorDetails) {
          if (item.vendorDetails.hasOwnProperty(vendorDetail)) {
            
            var details = {
              "vendorId": item.vendorDetails[vendorDetail].vendorId,
              "warrantyFlag": item.vendorDetails[vendorDetail].warrantyFlag,
              "supportFlag": item.vendorDetails[vendorDetail].supportFlag,
              "technicalSpecFlag": item.vendorDetails[vendorDetail].technicalSpecFlag,
              "paymentFlag": item.vendorDetails[vendorDetail].paymentFlag,
              "warrantyRejectNote": item.vendorDetails[vendorDetail].warrantyRejectNote,
              "supportRejectNote": item.vendorDetails[vendorDetail].supportRejectNote,
              "technicalSpecRejectNote": item.vendorDetails[vendorDetail].technicalSpecRejectNote,
              "paymentRejectNote": item.vendorDetails[vendorDetail].paymentRejectNote,
              "remark": item.vendorDetails[vendorDetail].remark,
              "rejectNote": item.vendorDetails[vendorDetail].rejectNote,
              "accept": false,
              "reject": false,
              "onHold": false
            }

            if (item.vendorDetails[vendorDetail].setVendorStatus == 1) {
              details["accept"] = true;
              details["rejectNote"] = null;
            } else if(item.vendorDetails[vendorDetail].setVendorStatus == 2) {
              details["reject"] = true;
            } else if (item.vendorDetails[vendorDetail].setVendorStatus == 3) {
              details["onHold"] = true;
              details["rejectNote"] = null;
            }

            innerData.vendorDetails.push(details);
          }
        }

        data.items.push(innerData);
      });

      $rootScope.startLoader();
      GenericService.serviceAction("PATCH", URI, data).then(
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

    var SaveRecord = function (action) {
      if (action  == 'save') {
        CreateTechnicalApproval();
      } else if (action  == 'send' && ValidateApproval()) {
        CreateTechnicalApproval();
      }
    }

    var ValidateApproval = function () {
      var isValid = true;
      angular.forEach(self.model.itemInfo, function (item, key) {
        for (var vendorDetail in item.vendorDetails) {
          if (item.vendorDetails.hasOwnProperty(vendorDetail)) {
            var isFound = item.vendorDetails.find(function(x){
              if (x.setVendorStatus == 3) {
                return x;
              }
            });
            
            if (isFound) {
              growl.error('All Vendors should be Accepted');
              isValid = false;
            }
          }
        }
      });

      return isValid;
    }

    var setStatus = function (data) {
      var status = self.model.statuses.find(function (obj) {
        return obj.status == data;
      });

      return status.id;
    }

    var PopupAction = function (action, modal) {
      var popup = angular.element('#' + modal);
      self.model.action = action;
      if (action == 'Close') {
        popup.modal('hide');
      } else if (action == 'Open') {
        popup.modal('show');
      }
    }

    var NavigateToBack = function () {
      $state.go('technicalApproval');
    }

    initializeController();
  }
 
  function config($stateProvider) {
    $stateProvider.state("detailstechnicalApproval", {
      url: "/detailstechnicalApproval",
      templateUrl: 'views/procurement/quotation/details-technical-approval.html',
      controller: "DetailstechnicalApproval.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("DetailstechnicalApproval.Controller", detailsTAController);
})();