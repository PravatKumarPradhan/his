(function () {
  "use strict";

  function purchaseRequestController($scope, $rootScope, $http, $filter, $state, GenericService, PurchaseRequestService, cancelReasonService, rejectReasonService, StatusService, growl, CONSTANTS) {
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
          "store": "All"
        }],
        statuses: [{
          "status": "All"
        }],
        priorities: [{
          "priority": "All"
        }]
      };

      rejectReasonService.GetReason().then(function(reason) {
        self.model.rejectReasons = reason;
      });

      cancelReasonService.GetReason().then(function(reason) {
        self.model.reasons = reason;
      });
      
      StatusService.GetStatus().then(function(status) {
        self.model.statusList = status;
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
      self.model.OpenPopup = OpenPopup;
      self.model.ModalDismiss = ModalDismiss;
      self.model.isEdit = isEdit;
      self.model.isCancel = isCancel;

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
      purchaseRequests.priority = purchaseRequests.priorities[0];

      var URI = CONSTANTS.PROCUREMENT.PURCHASE.PURCHASE_REQUEST.DROPDOWN_API;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          purchaseRequests.storeList = purchaseRequests.storeList.concat(response.data.store);
          purchaseRequests.statuses = purchaseRequests.statuses.concat(response.data.status);
          purchaseRequests.priorities = purchaseRequests.priorities.concat(response.data.priority);
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
        var URI = CONSTANTS.PROCUREMENT.PURCHASE.PURCHASE_REQUEST.SEARCH_API;

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
          }
        );
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
      if (purchase.status != 'New') {
        return true;
      }
      return false;
    }

    var isEdit = function (purchase) {
      if (purchase.status == 'Pending') {
        return true;
      } else if (purchase.status == 'New') {
        return false;
      } else if (purchase.status == 'Closed' || purchase.status == 'Cancelled') {
        return true;
      }
    }

    var isCancel = function (purchase) {
      if (purchase.status == 'Pending') {
        return false;
      } else if (purchase.status == 'New') {
        return false;
      } else if (purchase.status == 'Closed' || purchase.status == 'Cancelled') {
        return true;
      }
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
      if (self.model.action == 'Update') {
        if (ValidateStatus()) {
          var URI = CONSTANTS.PROCUREMENT.PURCHASE.PURCHASE_REQUEST.APPROVE_API;

          var data = {
            "prId": self.model.SelectedRequestIds,
            "statusId": setStatus('Pending'),
            "approvalStatusId": setStatus('Pending Approval')
          };

          $rootScope.startLoader();
          GenericService.serviceAction("PATCH", URI, data).then(
            function (response) {
              self.model.SelectedRequestIds = [];
              GetPurchaseRequests();
              self.model.selectAllRows = false;

              $rootScope.stopLoader();
              growl.success(response.data.message);
            },
            function (err) {
              $rootScope.stopLoader();
              growl.error(err.data.message);
            });
        } else {
          growl.error('Select Purchase Request to Approve');
          return;
        }
      } else if (self.model.action == 'Cancel') {
        if (ValidetReason()) {
          var URI = CONSTANTS.PROCUREMENT.PURCHASE.PURCHASE_REQUEST.CANCEL_API + self.model.id;

          var data = {
            "statusId": setStatus('Closed'),
            "approvalStatusId": setStatus('Cancelled'),
            "reasonId": self.model.cancelReason ? self.model.cancelReason : undefined,
            "note": self.model.rejectNote
          }
          
          $rootScope.startLoader();
          GenericService.serviceAction("PATCH", URI, data).then(
            function (response) {
              self.model.SelectedRequestIds = [];
              ModalDismiss('cancelPurchaseReqest');
              self.model.rejectNote = undefined;
              self.model.cancelReason = undefined;

              GetPurchaseRequests();
              $rootScope.stopLoader();
              growl.success(response.data.message);
            }, function (err) {
              $rootScope.stopLoader();
              growl.error(err.data.message);
            });
        }
      }
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

    var OpenPopup = function (action, popupName, data) {
      self.model.action = action;
      self.model.id = data.id;
      var popup = angular.element('#' + popupName);

      self.model.isReasonValid = false;
      self.model.rejectNote = undefined;
      popup.modal('show');
    }

    var ModalDismiss = function (modal) {
      var popup = angular.element('#' + modal);
      popup.modal('hide');
    }

    var NavigateToAddNew = function () {
      $state.go('addNewpurchaseRequest');
    }

    var NavigateToDetails = function (purchase) {
      PurchaseRequestService.SetPurchaseId(purchase.id);
      $state.go('detailsPurchaseRequest');
    }

    var NavigateToEdit = function (purchase) {
      PurchaseRequestService.SetPurchaseId(purchase.id);
      $state.go('addNewpurchaseRequest');
    }

    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("purchaseRequest", {
      url: "/purchaseRequest",
      templateUrl: 'views/procurement/purchase/purchase-request.html',
      controller: "PurchaseRequest.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("PurchaseRequest.Controller", purchaseRequestController);
})();