(function () {
  "use strict";

  function itemApprovalController($scope, $rootScope, $http, $filter, $state, CONSTANTS, GenericService, growl, cancelReasonService, StatusService, commonDetailService) {
    var self = this;

    //Default Constructor
    function initializeController() {
      $rootScope.loginpage = true;

      cancelReasonService.GetReason().then(function(reason){
        self.model.reasons = reason;
      });
      
      StatusService.GetStatus().then(function(status) {
        self.model.statusList = status;
      });

      self.model = {
        fromDate: new Date(),
        toDate: new Date(),
        fromDateOptions: {
          formatYear: 'yyyy',
          showWeeks: false,
          maxDate : new Date()
        },
        toDateOptions: {
          formatYear: 'yyyy',
          showWeeks: false,
          maxDate : new Date()
        },
        selectAllRows: false,
        SelectedRequestIds: [],
        storeList: [{
          "storeName": "Select Store"
        }],
        statuses: [{
          "status": "All"
        }],
        priorities: [{
          "priority": "All"
        }]
      };
      //Methods
      self.model.OpenFromDate = OpenFromDate;
      self.model.OpenToDate = OpenToDate;
      self.model.GetItemRequests = GetItemRequests;
      self.model.SelectAllRequests = SelectAllRequests;
      self.model.SelectItemRequest = SelectItemRequest;
      self.model.isDisable = isDisable;
      self.model.UpdateItemRequest = UpdateItemRequest;
      self.model.OpenPopup = OpenPopup;
      self.model.ModalDismiss = ModalDismiss;
      self.model.NavigateToDetails = NavigateToDetails;

      PopulateDropdown();
    }

    var setStatus = function (x){
      var status = self.model.statusList.find(function(obj){
        return obj.status == x;
      });
      
      return status.id;
    }

    var NavigateToDetails = function (item) {
      commonDetailService.setDataId(item.id);
      $state.go('itemApprovalDetails');
    }

    var OpenFromDate = function () {
      self.model.fromDateOpened = true;
      self.model.toDateOptions.minDate = self.model.fromDate;
    }

    var OpenToDate = function () {
      self.model.toDateOpened = true;
      self.model.toDateOptions.minDate = self.model.fromDate;
    }

    var PopulateDropdown = function () {
      self.model.status = self.model.statuses[0];

      var URI = CONSTANTS.PROCUREMENT.PURCHASE.ITEM_APPROVAL.DROPDOWN_API;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          self.model.statuses = self.model.statuses.concat(response.data.status);
          $rootScope.stopLoader();
          GetItemRequests();
        },
        function (err) {
          $rootScope.stopLoader();
        });
    }

    var ValidateDate = function () {
      var isValid = true;
      if (self.model.fromDate > self.model.toDate) {
        growl.error('From Date should lesser than To Date');
        isValid = false;
      }

      return isValid;
    }

    var GetItemRequests = function () {
      if (ValidateDate()) {
        var itemRequest = self.model;
        var URI = CONSTANTS.PROCUREMENT.PURCHASE.ITEM_APPROVAL.SEARCH_API;

        var data = {
          "fromDate": moment(itemRequest.fromDate).format('YYYY-MM-DD'),
          "toDate": moment(itemRequest.toDate).format('YYYY-MM-DD'),
          "statusId": !!itemRequest.status && !!itemRequest.status.id ? itemRequest.status.id : undefined
        }
        $rootScope.startLoader();
        GenericService.serviceAction("POST", URI, data).then(
          function (response) {
            itemRequest.items = response.data;
            $rootScope.stopLoader();
          },
          function (err) {
            $rootScope.stopLoader();
            growl.error(err.data.message);
          });
      }
    }

    var isSelected = function (item) {
      if (!isDisable(item))
        return item.isSelected;
      else
        return true;
    }

    var SelectItemRequest = function (item) {
      var itemRequestIds = self.model.SelectedRequestIds;

      if (item.isSelected) {
        itemRequestIds.push(item.id);
      } else {
        var index = itemRequestIds.indexOf(item.id);
        if (index > -1) {
          itemRequestIds.splice(index, 1);
        }
      }

      if (self.model.items.every(isSelected)) {
        self.model.selectAllRows = true;
      } else {
        self.model.selectAllRows = false;
      }
    }

    var SelectAllRequests = function () {
      if (self.model.selectAllRows) {
        self.model.SelectedRequestIds = [];
        self.model.items.forEach(function (item) {
          if (!isDisable(item)) {
            item.isSelected = true;
            self.model.SelectedRequestIds.push(item.id);
          }
        });
      } else {
        self.model.SelectedRequestIds = [];
        self.model.items.forEach(function (item) {
          item.isSelected = false;
        });
      }
    }

    var isDisable = function (item) {
      if (item.status == 'Cancelled' || item.status == 'Closed' ||  item.approvalStatus == "Approved") {
        return true;
      }
      return false;
    }

    var OpenPopup = function (action, popupName, data) {
      self.model.action = action;
      var popup = angular.element('#' + popupName);
      self.model.id = data.id;
      popup.modal('show');
    }

    var ValidateStatus = function (){
      var isValid = true;
      if (!self.model.SelectedRequestIds || self.model.SelectedRequestIds.length == 0) {
        isValid = false;
      }
      return isValid;
    }

    var ValidetReason = function () {
      var isValid = true;

      if (!self.model.cancelReason) {
        self.model.isReasonValid = true;
        isValid = false;
      }
      
      return isValid;
    }

    var ModalDismiss = function (modal) {
      var popup = angular.element('#' + modal);
      popup.modal('hide');
    }

    var UpdateItemRequest = function () {
      if (self.model.action == 'Update') {
        if (ValidateStatus()) {
          var URI = CONSTANTS.PROCUREMENT.PURCHASE.ITEM_APPROVAL.APPROVE_API;

          var data = {
            "itemRequestId": self.model.SelectedRequestIds,
            "statusId": setStatus('Pending'),
            "approvalStatusId": setStatus('Approved')
          };

          $rootScope.startLoader();
          GenericService.serviceAction("PATCH", URI, data).then(
            function (response) {
              self.model.SelectedRequestIds = [];
              $rootScope.stopLoader();
              GetItemRequests();
              self.model.selectAllRows = false;
              growl.success(response.data.message);
            },
            function (err) {
              $rootScope.stopLoader();
              growl.error(err.data.message);
            });
        } else {
          growl.error('Select Item to Approve');
          return;
        }
      } else if (self.model.action == 'Cancel') {
        if (ValidetReason()) {
          var URI = CONSTANTS.PROCUREMENT.PURCHASE.ITEM_APPROVAL.CANCEL_API + self.model.id;

          var data = {
            "statusId": setStatus('Closed'),
            "approvalStatusId": setStatus('Cancelled'),
            "cancelReasonId": self.model.cancelReason ? self.model.cancelReason : undefined,
            "cancelNote": self.model.rejectNote
          }

          GenericService.serviceAction("PATCH", URI, data).then(
            function (response) {
              self.model.SelectedRequestIds = [];
              self.model.rejectNote = undefined;
              self.model.cancelReason = undefined;
              ModalDismiss('cancelItemRequest');
              GetItemRequests();

              $rootScope.stopLoader();
              growl.success(response.data.message);
            }, function (err) {
              $rootScope.stopLoader();
              growl.error(err.data.message);
            });
        }
      }
    }

    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("itemApproval", {
      url: "/itemApproval",
      templateUrl: 'views/procurement/purchase/item-approval.html',
      controller: "itemApproval.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("itemApproval.Controller", itemApprovalController);
})();