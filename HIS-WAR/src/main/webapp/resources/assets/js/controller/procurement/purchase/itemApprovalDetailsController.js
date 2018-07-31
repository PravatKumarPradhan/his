(function () {
  "use strict";

  function itemApprovalDetailsController($rootScope, $scope, $http, CONSTANTS, $state, GenericService, commonDetailService, rejectReasonService, StatusService, growl) {
    var self = this;
    var itemId;
    var PrId;

    //Default Constructor
    function initializeController() {
      $rootScope.loginpage = true;

      self.model = {
        SelectedItems: []
      };
      
      StatusService.GetStatus().then(function(status){
        self.model.statusList = status;
      });

      rejectReasonService.GetReason().then(function(reason) {
        self.model.rejectReasons = reason;
      });

      self.model.NavigateToBack = NavigateToBack;
      self.model.RejectionPopup = RejectionPopup;
      self.model.SelectItem = SelectItem;
      self.model.SelectAllItems = SelectAllItems;
      self.model.SaveApproval = SaveApproval;
      self.model.SaveAcceptReject = SaveAcceptReject;
      self.model.ModalDismiss = ModalDismiss;
      self.model.isDisable = isDisable;
      GetPurchaseRequest(commonDetailService.getDataId());
    }

    var setStatus = function (x){
      var status = self.model.statusList.find(function(obj){
        return obj.status == x;
      });
      
      return status.id;
    }

    var GetPurchaseRequest = function (purchaseId) {
      commonDetailService.setDataId(null);
      PrId = purchaseId;
      var URI = CONSTANTS.PROCUREMENT.PURCHASE.ITEM_APPROVAL.DETAILS_API + purchaseId;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          self.model.approvalItems = response.data;
          self.model.requestItems = JSON.parse(JSON.stringify(response.data.items));
          self.model.approvalItems.items.forEach(function(item){
            item.isDisabled = true;
          });
          self.model.reasons = self.model.rejectReasons;
          $rootScope.stopLoader();
        },
        function (err) {
          $rootScope.stopLoader();
          growl.error(err.data.message);
        });
    }
    
    var ModalDismiss = function (popupName) {
      var popup = angular.element('#' + popupName);
      popup.modal('hide');
    }

    var RejectionPopup = function (item) {
      if (item.isReject) {
        itemId = item.id;
        self.model.action = 'Open';
        var popup = angular.element('#qtyaccepatReject');
        popup.modal('show');
        self.model.rejectReason = self.model.reasons[0];
      } else {
        CancelRejection(item);
      }
    }

    var CancelRejection = function (item) {
      var prevRequest = self.model.requestItems.find(function(prevItem){
        return prevItem.id == item.id;
      });
      
      if (prevRequest) {
        item.rejectReasonId = prevRequest.rejectReasonId;
      }
    }

    var SaveAcceptReject = function () {
      if (ValidateRejection()) {
        var rejectItemId = itemId;
        self.model.SelectedItems.forEach(function(item){
          if (item.id == rejectItemId) {
            // item.statusId = setStatus('Reject');
            item.rejectReasonId = self.model.rejectReason.id;
            item.rejectNote = self.model.rejectNote;
          }
          ModalDismiss('qtyaccepatReject');
        });
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

    var SaveApproval = function () {
      if (ValidateApproval()) {
        var URI = CONSTANTS.PROCUREMENT.PURCHASE.ITEM_APPROVAL.SAVE_API + PrId;

        var data = {
          "items": []
        }

        $rootScope.startLoader();

        self.model.approvalItems.items.forEach(function (item) {
          if (item.isSelected) {
            if (item.isReject) {
              data.items.push({
                "id": item.id,
                "statusId": setStatus('Reject'),
                "rejectReasonId": item.rejectReasonId,
                "rejectNote": item.rejectNote
              });
            }
            else {
              data.items.push({
                "id": item.id,
                "statusId": setStatus('Approved'),
                "rejectReasonId": self.model.reasons[0].id,
                "rejectNote": null
              });
            }

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
          }
        });

        // angular.forEach(self.model.SelectedItems, function (item) {
        //   var newItem = {
        //     "id": item.id,
        //     "rejectReasonId": item.rejectReasonId ? item.rejectReasonId : "1",
        //     "rejectNote": item.rejectNote,
        //     "statusId": item.statusId
        //   }

        //   data.items.push(newItem);

        //   var statusHash = {};
        //   data.statusId = null;
        //   data.approvalStatusId = null;

        //   data.items.forEach(function(rec) {
        //     var statusId = rec.statusId;
        //     statusHash[statusId] = true;
        //   });

        //   var statuses = Object.keys(statusHash);
        //   if(statuses.length > 1) {
        //     data.statusId = setStatus('Pending');
        //     data.approvalStatusId = setStatus('Partially Approved');
        //   } else if(statuses[0] == 5) {
        //     data.statusId = setStatus('Pending');
        //     data.approvalStatusId = setStatus('Approved');
        //   } else if(statuses[0] == 7) {
        //     data.statusId = setStatus('Closed');
        //     data.approvalStatusId = setStatus('Reject');
        //   } else {
        //     data.statusId = setStatus('Pending');
        //     data.approvalStatusId = setStatus('Partially Approved');
        //   }
        // });

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

    var NavigateToBack = function () {
      $state.go('itemApproval');
    }

    var isSelected = function (item) {
      return item.isSelected;
    }

    var SelectAllItems = function () {
      if (self.model.selectAllRows) {
        self.model.approvalItems.items.forEach(function (item) {
          if (!isDisable(item)) {
            item.isSelected = true;
            item.isDisabled = false;
            self.model.SelectedItems.push(item.id);
          }
        });
      } else {
        self.model.SelectedItems = [];
        self.model.approvalItems.items.forEach(function (item) {
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
      } else {
        item.isDisabled = true;
        item.isReject = false;
        CancelRejection(item);
        var index = items.indexOf(item);
        if (index > -1) {
          items.splice(index, 1);
        }
      }

      if (self.model.approvalItems.items.every(isSelected)) {
        self.model.selectAllRows = true;
      } else {
        self.model.selectAllRows = false;
      }
    }

    var isDisable = function (item) {
      if (item.statusId == 5 || item.statusId == 7 || item.statusId == 9 || item.statusId == 8) {
        return true;
      }
      return false;
    }

    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("itemApprovalDetails", {
      url: "/itemApprovalDetails",
      templateUrl: 'views/procurement/purchase/item-approval-details.html',
      controller: "ItemApprovalDetails.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("ItemApprovalDetails.Controller", itemApprovalDetailsController);
})();