(function () {
  "use strict";
  var CACHE = {};
  function detailsGRNApprovalController($scope, $rootScope, $location, rejectReasonService, $http, StatusService, CONSTANTS, GenericService, $state, growl, commonDetailService) {
    var self = this;
    var itemId;

    //Default Constructor
    function initializeController() {
      $rootScope.loginpage = true;
      self.model = {
        SelectedItems: [],
        GRNUoms: [],
        bonusUoms: [],
        otherCharges: [],
        selectAllRows: false,
        poList: []
      };

      rejectReasonService.GetReason().then(function (reason) {
        self.model.rejectReasons = reason;
      });

      StatusService.GetStatus().then(function (status) {
        self.model.statuses = status;
      });

      self.model.NavigateToBack = NavigateToBack;
      self.model.SelectAllItems = SelectAllItems;
      self.model.SelectItem = SelectItem;
      self.model.SaveRejection = SaveRejection;
      self.model.SaveApproval = SaveApproval;
      self.model.ViewGRNUom = ViewGRNUom;
      self.model.ViewBonusUom = ViewBonusUom;
      self.model.RejectionPopup = RejectionPopup;
      self.model.ModalDismiss = ModalDismiss;
      self.model.SaveGRNApproval = SaveGRNApproval;
      self.model.isDisable = isDisable;
      self.model.ViewStagedQuantity = ViewStagedQuantity;
      self.model.ViewOtherCharges = ViewOtherCharges;
      self.model.ViewPOList = ViewPOList;
      GetGRNList();
    }

    var ViewStagedQuantity = function (item) {
      self.model.grnList.items.find(function(x){
        if (item.id == x.id) {
          self.model.grnStagedQuantity = x.grnStagedQuantity;
        }
      });
    }

    var ViewPOList = function (item) {
      self.model.grnList.items.find(function(x){
        if (item.id == x.id) {
          self.model.poList = x.poList;
        }
      });
    }

    var GetGRNList = function () {
      var URI = CONSTANTS.PROCUREMENT.QUOTATION.GRN_APPROVAL.DETAILS_API + commonDetailService.getDataId();
      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          self.model.grnList = response.data;
          self.model.requestItems = JSON.parse(JSON.stringify(response.data.items));
          self.model.grnList.items.forEach(function (item) {
            item.isDisabled = true;
            // item.bonusApprovedQty = item.bonusApprovedQuantity;
          });
          $rootScope.stopLoader();
        },
        function (err) {
          $rootScope.stopLoader();
          growl.error(err.data.message);
        });
    }

    var SaveApproval = function (item) {
      if (item.approvedQuantity > item.grnReceivedQuantity) {
        item.rejectQuantity = 0;
        self.model.rejectQty = 0;
        item.isReject = false;
        item.statusId = setStatus('Approved');
      } else if (item.approvedQuantity == 0 || !item.approvedQuantity) {
        item.rejectQuantity = item.grnReceivedQuantity;
        self.model.rejectQty = item.rejectQuantity;
        item.isReject = true;
        item.statusId = setStatus('Reject');
      } else if (item.approvedQuantity < item.grnReceivedQuantity) {
        item.rejectQuantity = item.grnReceivedQuantity - item.approvedQuantity;
        self.model.rejectQty = item.rejectQuantity;
        item.isReject = true;
        item.statusId = setStatus('Partially Approved');
      } else if (item.approvedQuantity == item.grnReceivedQuantity) {
        item.rejectQuantity = item.grnReceivedQuantity - item.approvedQuantity;
        item.isReject = false;
        item.statusId = setStatus('Approved');
      }
    }

    var SaveRejection = function () {
      if (ValidateRejection()) {
        var rejectItemId = itemId;
        self.model.SelectedItems.forEach(function (item) {
          if (item.id == rejectItemId) {
            if (self.model.rejectQty > item.grnReceivedQuantity) {
              growl.error('Please Enter Valid Quantity');
              return false;
            } else if (self.model.rejectQty == item.grnReceivedQuantity) {
              item.rejectQuantity = item.grnReceivedQuantity;
              item.approvedQuantity = item.grnReceivedQuantity - item.rejectQuantity;
              item.rejectReasonId = self.model.rejectReasons[0].id;
              item.statusId = setStatus('Reject');
            } else if (self.model.rejectQty == 0 || !self.model.rejectQty) {
              item.approvedQuantity = item.grnReceivedQuantity;
              item.rejectQuantity = 0;
              item.rejectReasonId = self.model.rejectReasons[0].id;
              item.statusId = setStatus('Approved');
            } else {
              item.rejectQuantity = self.model.rejectQty;
              item.rejectNote = self.model.rejectNote;
              item.approvedQuantity = item.grnReceivedQuantity - item.rejectQuantity;
              item.rejectReasonId = self.model.rejectReasons[0].id;
              item.statusId = setStatus('Partially Approved');
            }
            ModalDismiss('qtyaccepatReject');
          }
        });
      }
    }

    var SaveGRNApproval = function () {
      if (ValidateApproval()) {
        var URI = CONSTANTS.PROCUREMENT.QUOTATION.GRN_APPROVAL.APPROVE_API + commonDetailService.getDataId();
        
        var data = {
          "items": []
        }

        $rootScope.startLoader();
        angular.forEach(self.model.SelectedItems, function (item) {
          var newItem = {
            "id": item.id,
            "grnApprovedQuantity": parseInt(item.approvedQuantity),
            "bonusApprovedQuantity": item.bonusReceivedQuantity,
            "rejectedQuantity": parseInt(item.rejectQuantity),
            "rejectReasonId": self.model.rejectReason ? self.model.rejectReason.id : 1,
            "rejectNote": self.model.rejectNote,
            "statusId": item.statusId
          }

          data.items.push(newItem);

          var statusHash = {};
          data.statusId = null;
          data.approvalStatusId = null;
          data.items.forEach(function(rec) {
            var statusId = rec.statusId;
            statusHash[statusId] = true;
          });

          var statuses = Object.keys(statusHash);
          if(statuses.length > 1) {
            data.statusId = setStatus('Pending');
            data.approvalStatusId = setStatus('Partially Approved');
          } else if(statuses[0] == 5) {
            data.statusId = setStatus('Pending');
            data.approvalStatusId = setStatus('Approved');
          } else if(statuses[0] == 7) {
            data.statusId = setStatus('Closed');
            data.approvalStatusId = setStatus('Reject');
          } else {
            data.statusId = setStatus('Pending');
            data.approvalStatusId = setStatus('Partially Approved');
          }

        });

        GenericService.serviceAction("PATCH", URI, data).then(
          function (response) {
            $rootScope.stopLoader();
            growl.success(response.data.message);
            NavigateToBack();
          }, function (err) {
            $rootScope.stopLoader();
            growl.error(err.data.message);
          });
      }
    }

    var isSelected = function (item) {
      return item.isSelected;
    }

    function setStatus(x) {
      var status = self.model.statuses.find(function (obj) {
        return obj.status == x;
      });

      return status.id;
    }

    var isDisable = function (items) {
      // if (items.statusId == 10) {
      if (items.statusId == 5 || items.statusId == 7 || items.statusId == 9 || items.statusId == 6) {
        return true;
      }
      return false;
    }

    var SelectAllItems = function () {
      if (self.model.selectAllRows) {
        self.model.SelectedItems = [];
        self.model.grnList.items.forEach(function (item) {
          if (!isDisable(item)) {
            item.isSelected = true;
            item.isDisabled = false;
            item.approvedQuantity = item.grnReceivedQuantity;
            item.bonusApprovedQty = item.bonusApprovedQuantity;
            self.model.SelectedItems.push(item);
          }
        });
      } else {
        self.model.SelectedItems = [];
        self.model.grnList.items.forEach(function (item) {
          item.isSelected = false;
          item.isDisabled = true;
          item.approvedQuantity = 0;
          item.bonusApprovedQty = 0;
        });
      }
    }

    var SelectItem = function (item) {
      var items = self.model.SelectedItems;

      if (item.isSelected) {
        items.push(item);
        item.isDisabled = false;
        item.approvedQuantity = item.grnReceivedQuantity;
        item.bonusApprovedQty = item.bonusReceivedQuantity;
      } else {
        item.isDisabled = true;
        item.isReject = false;
        item.approvedQuantity = 0;
        item.bonusApprovedQuantity = 0;
        var index = items.indexOf(item);
        if (index > -1) {
          items.splice(index, 1);
        }
      }

      if (self.model.grnList.items.every(isSelected)) {
        self.model.selectAllRows = true;
      } else {
        self.model.selectAllRows = false;
      }
    }

    var NavigateToBack = function () {
      $state.go('GRNApproval');
    }

    var RejectionPopup = function (item) {
      if (item.isReject) {
        itemId = item.id;
        self.model.rejectQty = item.rejectQuantity;
        self.model.action = 'Open';
        var popup = angular.element('#qtyaccepatReject');
        popup.modal('show');
        self.model.reasons = self.model.rejectReasons;
        self.model.rejectReason = self.model.reasons[0];
      } else {
        CancelRejection(item);
      }
    }

    var CancelRejection = function (item) {
      var prevRequest = self.model.requestItems.find(function (prevItem) {
        return prevItem.id == item.id;
      });

      if (prevRequest) {
        item.rejectQuantity = prevRequest.rejectQuantity;
        item.approvedQuantity = prevRequest.approvedQuantity;
        item.rejectNote = prevRequest.rejectNote;
        item.rejectReasonId = prevRequest.rejectReasonId;
        item.statusId = prevRequest.statusId;
      }
    }

    var ValidateApproval = function () {
      var isValid = true;

      if (!self.model.SelectedItems || self.model.SelectedItems.length == 0) {
        growl.error('Select Items to Update and Save');
        isValid = false;
      }

      return isValid;
    }

    var ValidateRejection = function () {
      var isValid = true;
      if (!self.model.rejectReason) {
        isValid = false;
        growl.error('Please Select Reason to Reject');
      }

      return isValid;
    }

    var ViewOtherCharges = function (item){
      self.model.grnList.items.find(function(x){
        if (item.id == x.id) {
          self.model.otherCharges = x.otherCharges;
        }
      });
    }

    var ViewGRNUom = function (item) {
      self.model.grnList.items.find(function(x){
        if (item.id == x.id) {
          self.model.GRNUoms = x.grnUom;
        }
      });
    }

    var ViewBonusUom = function (item) {
      self.model.grnList.items.find(function(x){
        if (item.id == x.id) {
          self.model.bonusUoms = x.bonusUom;
        }
      });
    }

    var ModalDismiss = function (popupName) {
      var popup = angular.element('#' + popupName);
      popup.modal('hide');
      self.model.rejectNote = undefined;
    }

    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("detailsGRNApproval", {
      url: "/detailsGRNApproval",
      templateUrl: 'views/procurement/quotation/details-GRN-approval.html',
      controller: "DetailsGRNApproval.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("DetailsGRNApproval.Controller", detailsGRNApprovalController);
})();