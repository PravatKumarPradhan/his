(function () {
  "use strict";

  function purchaseRequestApprovalController($scope, $rootScope, $http, $filter, $state, CONSTANTS, GenericService, StatusService, commonDetailService, cancelReasonService, growl) {
    var self = this;

    //Default Constructor
    function initializeController() {
      $rootScope.loginpage = true;

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
          "store": "Select Store"
        }],
        statuses: [{
          "status": "All"
        }]
      };
      
      StatusService.GetStatus().then(function(status) {
        self.model.statusList = status;
      });

      cancelReasonService.GetReason().then(function(reason) {
        self.model.reasons = reason;
      });

      //Methods
      self.model.OpenFromDate = OpenFromDate;
      self.model.OpenToDate = OpenToDate;
      self.model.GetPurchaseRequests = GetPurchaseRequests;
      self.model.NavigateToAddNew = NavigateToAddNew;
      self.model.NavigateToDetails = NavigateToDetails;
      self.model.NavigateToEdit = NavigateToEdit;
      self.model.SelectAllRequests = SelectAllRequests;
      self.model.SelectPurchaseRequest = SelectPurchaseRequest;
      self.model.isDisable = isDisable;
      self.model.UpdatePurchaseRequest = UpdatePurchaseRequest;
      self.model.ValidateAndUpdate = ValidateAndUpdate;
      self.model.ModalDismiss = ModalDismiss;

      PopulateDropdown();
    }

    var setStatus = function (x){
      var status = self.model.statusList.find(function(obj){
        return obj.status == x;
      });
      
      return status.id;
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
      var purchaseRequests = self.model;
      purchaseRequests.store = purchaseRequests.storeList[0];
      purchaseRequests.status = purchaseRequests.statuses[0];

      var URI = CONSTANTS.PROCUREMENT.PURCHASE.PURCHASE_REQUEST_APPROVAL.DROPDOWN_API;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          purchaseRequests.storeList = purchaseRequests.storeList.concat(response.data.store);
          purchaseRequests.statuses = purchaseRequests.statuses.concat(response.data.status);
          purchaseRequests.priorities = response.data.priority;
          $rootScope.stopLoader();
          GetPurchaseRequests();
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

    var GetPurchaseRequests = function () {
      if (ValidateDate()) {
        var purchaseRequests = self.model;
        var URI = CONSTANTS.PROCUREMENT.PURCHASE.PURCHASE_REQUEST_APPROVAL.SEARCH_API;

        var data = {
          "fromDate": moment(purchaseRequests.fromDate).format('YYYY-MM-DD'),
          "toDate": moment(purchaseRequests.toDate).format('YYYY-MM-DD'),
          "priorityId": purchaseRequests.priority ? purchaseRequests.priority.id : undefined,
          "storeId": !!purchaseRequests.store && !!purchaseRequests.store.id ? purchaseRequests.store.id : undefined,
          "statusId": !!purchaseRequests.status && !!purchaseRequests.status.id ? purchaseRequests.status.id : undefined
        }

        $rootScope.startLoader();
        GenericService.serviceAction("POST", URI, data).then(
          function (response) {
            purchaseRequests.purchaseRequests = response.data;
            $rootScope.stopLoader();
          },
          function (err) {
            $rootScope.stopLoader();
            growl.error(err.data.message);
          });
      }
    }

    var SelectAllRequests = function () {
      if (self.model.selectAllRows) {
        self.model.SelectedRequestIds = [];
        self.model.purchaseRequests.forEach(function (purchase) {
          if (!isDisable(purchase)) {
            purchase.isSelected = true;
            self.model.SelectedRequestIds.push(purchase.id);
          }
        });
      } else {
        self.model.SelectedRequestIds = [];
        self.model.purchaseRequests.forEach(function (purchase) {
          purchase.isSelected = false;
        });
      }
    }

    var isDisable = function (purchase) {
      if (purchase.status == 'Cancelled' || purchase.status == 'Closed') {
        return true;
      }
      return false;
    }

    var isSelected = function (purchase) {
      if (!isDisable(purchase))
        return purchase.isSelected;
      else
        return true;
    }

    var SelectPurchaseRequest = function (purchase) {
      var purchaseIds = self.model.SelectedRequestIds;

      if (purchase.isSelected) {
        purchaseIds.push(purchase.id);
      } else {
        var index = purchaseIds.indexOf(purchase.id);
        if (index > -1) {
          purchaseIds.splice(index, 1);
        }
      }

      if (self.model.purchaseRequests.every(isSelected)) {
        self.model.selectAllRows = true;
      } else {
        self.model.selectAllRows = false;
      }
    }

    var UpdatePurchaseRequest = function () {
      if (self.model.action == 'Cancel') {
        if (ValidetReason()) {
          var URI = CONSTANTS.PROCUREMENT.PURCHASE.PURCHASE_REQUEST_APPROVAL.CANCEL_API + self.model.id;

          var data = {
            "statusId": setStatus('Closed'),
            "approvalStatusId": setStatus('Cancelled'),
            "reasonId": self.model.cancelReason ? self.model.cancelReason : undefined,
            "note": self.model.rejectNote
          }
          GenericService.serviceAction("PATCH", URI, data).then(
            function (response) {
              self.model.SelectedRequestIds = [];
              ModalDismiss('cancelPurchaseReqest');
              GetPurchaseRequests();

              $rootScope.stopLoader();
              growl.success(response.data.message);
            },
            function (err) {
              $rootScope.stopLoader();
              growl.error(err.data.message);
            });
        }
      }
    }

    var ValidateAndUpdate = function (action, popupName, data) {
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
      self.model.rejectNote = undefined;
      self.model.cancelReason = undefined;
    }

    var NavigateToAddNew = function () {
      $state.go('addNewpurchaseRequest');
    }

    var NavigateToDetails = function (purchase) {
      commonDetailService.setDataId(purchase.id);
      $state.go('detailsPurchaseRequestApproval');
    }

    var NavigateToEdit = function (purchase) {
      commonDetailService.setDataId(purchase.id);
      $state.go('addNewpurchaseRequest');
    }

    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("purchaseRequestApproval", {
      url: "/purchaseRequestApproval",
      templateUrl: 'views/procurement/purchase/purchase-request-approval.html',
      controller: "PurchaseRequestApproval.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("PurchaseRequestApproval.Controller", purchaseRequestApprovalController);
})();