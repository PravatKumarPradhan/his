(function () {
  "use strict";

  function detailsLocalPRApprovalController($rootScope, $scope, $http, CONSTANTS, $state, GenericService, commonDetailService, rejectReasonService, StatusService, growl) {
    var self = this;
    var itemId;
    var PrId;

    //Default Constructor
    function initializeController() {
      $rootScope.loginpage = true;

      self.model = {
        SelectedItems: []
      };

      rejectReasonService.GetReason().then(function(reason) {
        self.model.rejectReasons = reason;
      });
      
      StatusService.GetStatus().then(function(status) {
        self.model.statuses = status;
      });

      self.model.NavigateToBack = NavigateToBack;
      self.model.RejectionPopup = RejectionPopup;
      self.model.SelectItem = SelectItem;
      self.model.SelectAllItems = SelectAllItems;
      self.model.SaveApproval = SaveApproval;
      self.model.SaveAcceptReject = SaveAcceptReject;
      self.model.ApprovedQuantity = ApprovedQuantity;
      self.model.ModalDismiss = ModalDismiss;
      self.model.isDisable = isDisable;
      GetPurchaseRequest(commonDetailService.getDataId());
    }

    var GetPurchaseRequest = function (purchaseId) {
      commonDetailService.setDataId(null);
      PrId = purchaseId;
      var URI = CONSTANTS.PROCUREMENT.PURCHASE.LOCAL_PURCHASE_APPROVAL.DETAILS_API + purchaseId;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          self.model.purchaseRequests = response.data;
          self.model.requestItems = JSON.parse(JSON.stringify(response.data));
          self.model.purchaseRequests.items.forEach(function(item){
            item.isDisabled = true;
          });
          self.model.reasons = self.model.rejectReasons;
          $rootScope.stopLoader();
        },
        function (err) {
          $rootScope.stopLoader();
          growl.error('No Data Found', {title: err.status});
        });
    }

    var RejectionPopup = function (item) {
      if (item.isReject) {
        itemId = item.id;
        self.model.action = 'Open';
        var popup = angular.element('#qtyaccepatReject');
        popup.modal('show');
        self.model.reasons = self.model.rejectReasons;
        self.model.rejectReason = self.model.reasons[0];
        // self.model.rejectQty = item.rejectQuantity;
        self.model.rejectNote = undefined;
      } else {
        CancelRejection(item);
      }
    }

    var CancelRejection = function (item) {
      var prevRequest = self.model.requestItems.items.find(function(prevItem){
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

    var SaveAcceptReject = function () {
      if (ValidateRejection()) {
        var rejectItemId = itemId;
        self.model.SelectedItems.forEach(function(item){
          if (item.id == rejectItemId) {
            if (self.model.rejectQty > item.quantity) {
              growl.error('Please Enter Valid Quantity');
              return false;
            } else if (self.model.rejectQty == item.quantity) {
              item.rejectQuantity = item.quantity;
              item.approvedQuantity = item.quantity - item.rejectQuantity;
              item.rejectReasonId = self.model.reasons[0].id;
              item.statusId = setStatus('Reject');
            } else if (self.model.rejectQty == 0 || !self.model.rejectQty) {
              item.approvedQuantity = item.quantity;
              item.rejectQuantity = 0;
              item.rejectReasonId = self.model.reasons[0].id;
              item.statusId = setStatus('Approved');
            } else {
              item.rejectQuantity = self.model.rejectQty;
              item.rejectNote = self.model.rejectNote;
              item.approvedQuantity = item.quantity - item.rejectQuantity;
              item.rejectReasonId = self.model.reasons[0].id;
              item.statusId = setStatus('Partially Approved');
            }
            ModalDismiss('qtyaccepatReject');
          }
        });
      }
    }

    var ApprovedQuantity = function (item) {
      if (item.approvedQuantity > item.quantity) {
        item.rejectQuantity = 0;
        self.model.rejectQty = undefined;
        item.isReject = false;
        item.statusId = setStatus('Approved');
      } else if (item.approvedQuantity == 0 || !item.approvedQuantity) {
        item.approvedQuantity = 0
        item.rejectQuantity = item.quantity;
        self.model.rejectQty = item.rejectQuantity;
        item.isReject = true;
        item.rejectReasonId = self.model.reasons[0].id;
        item.statusId = setStatus('Reject');
      } else if (item.approvedQuantity < item.quantity) {
        item.rejectQuantity = item.quantity - item.approvedQuantity;
        self.model.rejectQty = item.rejectQuantity;
        item.rejectReasonId = self.model.reasons[0].id;
        item.isReject = true;
        item.statusId = setStatus('Partially Approved');
      } else if (item.approvedQuantity == item.quantity) {
        item.rejectQuantity = item.quantity - item.approvedQuantity;
        self.model.rejectQty = item.rejectQuantity;
        item.isReject = false;
        item.statusId = setStatus('Approved');
      }
      
    }

    var SaveApproval = function () {
      if (ValidateApproval()) {
        var URI = CONSTANTS.PROCUREMENT.PURCHASE.LOCAL_PURCHASE_APPROVAL.SAVE_API + PrId;
        
        var data = {
          "items": []
        }

        $rootScope.startLoader();
        angular.forEach(self.model.SelectedItems, function (item) {
          var newItem = {
            "id": item.id,
            "approvedQuantity": item.approvedQuantity,
            "rejectQuantity": item.rejectQuantity,
            "rejectReasonId": item.rejectReasonId ? item.rejectReasonId : 1,
            "rejectNote": item.rejectNote,
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
          function(response){
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

    var SelectAllItems = function () {
      if (self.model.selectAllRows) {
        self.model.purchaseRequests.items.forEach(function(item) {
          if (!isDisable(item)) {
            item.isSelected = true;
            item.isDisabled = false;
            self.model.SelectedItems.push(item);
          }
        });
      } else {
        self.model.SelectedItems = [];
        self.model.purchaseRequests.items.forEach(function (item) {
          CancelRejection(item);
          item.isSelected = false;
          item.isDisabled = true;
          item.isReject = false;
        });
      }
    }

    var SelectItem = function (item) {
      var items = self.model.SelectedItems;

      if (item.isSelected) {
        items.push(item);
        item.isDisabled = false;
        item.approvedQuantity = item.quantity;
      } else {
        item.isReject = false;
        item.isDisabled = true;
        item.approvedQuantity = 0;
        CancelRejection(item);
        var index = items.indexOf(item);
        if (index > -1) {
          items.splice(index, 1);
        }
      }

      if (self.model.purchaseRequests.items.every(isSelected)) {
        self.model.selectAllRows = true;
      } else {
        self.model.selectAllRows = false;
      }
    }

    var isDisable = function (item) {
      if (item.status == "Approved" || item.status == "Partially Approved" || item.status == "Reject" || item.status == "Cancelled") {
        return true;
      }

      return false;
    }

    var ValidateRejection = function () {
      var isValid = true;
      if (!self.model.rejectReason) {
        isValid = false;
        growl.error('Please Select Reason to Reject');
      }

      return isValid;
    }

    var ValidateApproval = function () {
      var isValid = true;

      if (!self.model.SelectedItems || self.model.SelectedItems.length == 0) {
        growl.error('Select Items to Update and Save');
        isValid = false;
      }

      return isValid;
    }

    var NavigateToBack = function () {
      $state.go('localPurchhaseRequestApproval');
    }
    
    var ModalDismiss = function (popupName) {
      var popup = angular.element('#' + popupName);
      popup.modal('hide');
    }

    var setStatus = function (x){
      var status = self.model.statuses.find(function(obj){
        return obj.status == x;
      });
      
      return status.id;
    }

    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("detailslocalPurchhaseRequestApproval", {
      url: "/detailslocalPurchhaseRequestApproval",
      templateUrl: 'views/procurement/purchase/details-local-purchase-approval.html',
      controller: "DetailsLocalPRApproval.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("DetailsLocalPRApproval.Controller", detailsLocalPRApprovalController);
})();