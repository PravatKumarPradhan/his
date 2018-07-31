(function () {
  "use strict";

  function itemRequestDetailsController($rootScope, $scope, $http, CONSTANTS, $state, GenericService, commonDetailService, rejectReasonService, StatusService, growl) {
    var self = this;
    var itemId, itemRequestId;

    //Default Constructor
    function initializeController() {
      $rootScope.loginpage = true;

      self.model = {
        SelectedItems: [],
        selectedPOId: 0
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
      GetItemRequest(commonDetailService.getDataId());
    }

    var setStatus = function (x){
      var status = self.model.statusList.find(function(obj){
        return obj.status == x;
      });
      
      return status.id;
    }

    var GetItemRequest = function (id) {
      commonDetailService.setDataId(null);
      itemRequestId = id;
      var URI = CONSTANTS.PROCUREMENT.PURCHASE.ITEM_REQUEST.DETAILS_API + id;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          self.model.itemDetails = response.data;
          self.model.requestItems = JSON.parse(JSON.stringify(response.data.items));
          self.model.itemDetails.items.forEach(function(item){
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
      self.model.rejectNote = undefined;
      self.model.cancelReason = undefined;
    }

    var RejectionPopup = function (action, popupName, data) {
      self.model.selectedPOId = data.id;
      if (data.isReject) {
        self.model.action = action;
        var popup = angular.element('#' + popupName);
        popup.modal('show');
      } else {
        return;
      }
    }

    var SaveAcceptReject = function () {
      // if (ValidetReason()) {
        self.model.itemDetails.items.forEach(function (item) {
          if(item.id == self.model.selectedPOId) {
            item.rejectReasonId = self.model.rejectReason.id;
            item.rejectNote = self.model.rejectNote;
          }
        });
        ModalDismiss("qtyaccepatReject");
      // }
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
        var URI = CONSTANTS.PROCUREMENT.PURCHASE.ITEM_REQUEST.APPROVE_API + itemRequestId;

        var data = {
          "items": []
        }

        $rootScope.startLoader();

        self.model.itemDetails.items.forEach(function (item) {
          if (item.isSelected) {
            if (item.isReject) {
              data.items.push({
                "id": item.id,
                "statusId": setStatus('Reject'),
                "rejectReasonId": item.rejectReasonId.id ? item.rejectReasonId.id : 2,
                "rejectNote": item.rejectNote
              });
            }
            else {
              data.items.push({
                "id": item.id,
                "statusId": setStatus('Approved'),
                "rejectReasonId": 1,
                "rejectNote": null
              });
            }
          }
        });

        angular.forEach(self.model.SelectedItems, function (item) {
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

    var NavigateToBack = function () {
      $state.go('itemRequest');
    }

    var isSelected = function (item) {
      return item.isSelected;
    }

    var SelectAllItems = function () {
      if (self.model.selectAllRows) {
        self.model.SelectedItems = [];
        self.model.itemDetails.items.forEach(function (item) {
          if (!isDisable(item)) {
            item.isSelected = true;
            item.isDisabled = false;
            self.model.SelectedItems.push(item);
          }
        });

      } else {
        self.model.SelectedItems = [];
        self.model.itemDetails.items.forEach(function (item) {
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
        var index = items.indexOf(item);
        if (index > -1) {
          items.splice(index, 1);
        }
      }

      if (self.model.itemDetails.items.every(isSelected)) {
        self.model.selectAllRows = true;
      } else {
        self.model.selectAllRows = false;
      }
    }

    var isDisable = function (item) {
      if (item.statusId == 5 || item.statusId == 7 || item.statusId == 8 || item.statusId == 9) {
        return true;
      }
      return false;
    }

    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("itemRequestDetails", {
      url: "/itemRequestDetails",
      templateUrl: 'views/procurement/purchase/item-request-details.html',
      controller: "ItemRequestDetails.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("ItemRequestDetails.Controller", itemRequestDetailsController);
})();