(function () {
  "use strict";

  function localPurchaseRequestController($scope, $rootScope, $http, $filter, $state, CONSTANTS, GenericService, commonDetailService, rejectReasonService, StatusService, growl) {
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
      
      StatusService.GetStatus().then(function(status) {
        self.model.statusList = status;
      });

      //Methods
      self.model.OpenFromDate = OpenFromDate;
      self.model.OpenToDate = OpenToDate;
      self.model.GetLocalPurchaseRequests = GetLocalPurchaseRequests;
      self.model.NavigateToAddNew = NavigateToAddNew;
      self.model.NavigateToDetails = NavigateToDetails;
      self.model.NavigateToEdit = NavigateToEdit;
      self.model.SelectAllRequests = SelectAllRequests;
      self.model.SelectLocalPurchaseRequest = SelectLocalPurchaseRequest;
      self.model.isDisable = isDisable;
      self.model.UpdateLocalPR = UpdateLocalPR;
      self.model.ValidateAndUpdate = ValidateAndUpdate;
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
      var localPurchase = self.model;
      localPurchase.store = localPurchase.storeList[0];
      localPurchase.status = localPurchase.statuses[0];
      localPurchase.priority = localPurchase.priorities[0];

      var URI = CONSTANTS.PROCUREMENT.PURCHASE.LOCAL_PURCHASE_REQUEST.DROPDOWN_API;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          localPurchase.storeList = localPurchase.storeList.concat(response.data.store);
          localPurchase.statuses = localPurchase.statuses.concat(response.data.status);
          localPurchase.priorities = localPurchase.priorities.concat(response.data.priority);
          $rootScope.stopLoader();
          GetLocalPurchaseRequests();
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

    var GetLocalPurchaseRequests = function () {
      if (ValidateDate()) {
        var localPurchase = self.model;
        var URI = CONSTANTS.PROCUREMENT.PURCHASE.LOCAL_PURCHASE_REQUEST.SEARCH_API;

        var data = {
          "fromDate": moment(localPurchase.fromDate).format('YYYY-MM-DD'),
          "toDate": moment(localPurchase.toDate).format('YYYY-MM-DD'),
          "priorityId": localPurchase.priority ? localPurchase.priority.id : undefined,
          "storeId": !!localPurchase.store && !!localPurchase.store.id ? localPurchase.store.id : undefined,
          "statusId": !!localPurchase.status && !!localPurchase.status.id ? localPurchase.status.id : undefined
        }

        $rootScope.startLoader();
        GenericService.serviceAction("POST", URI, data).then(
          function (response) {
            localPurchase.localPurchaseRequests = response.data;
            $rootScope.stopLoader();
          },
          function (err) {
            $rootScope.stopLoader();
            growl.error(err.data.message);
          }
        );
      }
    }

    var NavigateToAddNew = function () {
      $state.go('addNewlocalPurchhaseRequest');
    }

    var NavigateToDetails = function (purchase) {
      commonDetailService.setDataId(purchase.id);
      $state.go('detailslocalPurchhaseRequest');
    }

    var NavigateToEdit = function (purchase) {
      commonDetailService.setDataId(purchase.id);
      $state.go('addNewlocalPurchhaseRequest');
    }

    var SelectAllRequests = function () {
      if (self.model.selectAllRows) {
        self.model.SelectedRequestIds = [];
        self.model.localPurchaseRequests.forEach(function (purchase) {
          if (!isDisable(purchase)) {
            purchase.isSelected = true;
            self.model.SelectedRequestIds.push(purchase.id);
          }
        });
      } else {
        self.model.SelectedRequestIds = [];
        self.model.localPurchaseRequests.forEach(function (purchase) {
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

    var SelectLocalPurchaseRequest = function (purchase) {
      var purchaseIds = self.model.SelectedRequestIds;

      if (purchase.isSelected) {
        purchaseIds.push(purchase.id);
      } else {
        var index = purchaseIds.indexOf(purchase.id);
        if (index > -1) {
          purchaseIds.splice(index, 1);
        }
      }

      if (self.model.localPurchaseRequests.every(isSelected)) {
        self.model.selectAllRows = true;
      } else {
        self.model.selectAllRows = false;
      }
    }

    var ValidateAndUpdate = function (action, popupName, data) {
      self.model.action = action;
      self.model.id = data.id;
      var popup = angular.element('#' + popupName);
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

    var UpdateLocalPR = function () {
      if (self.model.action == 'Update') {
        if (ValidateStatus()) {
          var URI = CONSTANTS.PROCUREMENT.PURCHASE.LOCAL_PURCHASE_REQUEST.APPROVE_API;

          var data = {
            "prId": self.model.SelectedRequestIds,
            "statusId": setStatus('Pending'),
            "approvalStatusId": setStatus("Pending Approval")
          };
          $rootScope.startLoader();
          GenericService.serviceAction("PATCH", URI, data).then(
            function (response) {
              self.model.SelectedRequestIds = [];
              GetLocalPurchaseRequests();
              self.model.selectAllRows = false;

              $rootScope.stopLoader();
              growl.success(response.data.message);
            },
            function (err) {
              $rootScope.stopLoader();
              growl.error(err.data.message);
            });
        } else {
          growl.error('Select Local Purchase Request to Approve');
          return;
        }
      } else if (self.model.action == 'Cancel') {
        if (ValidetReason()) {
          var URI = CONSTANTS.PROCUREMENT.PURCHASE.LOCAL_PURCHASE_REQUEST.CANCEL_API + self.model.id;

          var data = {
            "statusId": setStatus('Closed'),
            "approvalStatusId": setStatus('Cancelled'),
            "cancelReasonId": self.model.cancelReason ? self.model.cancelReason : undefined,
            "cancelNote": self.model.rejectNote
          }
          
          $rootScope.startLoader();
          GenericService.serviceAction("PATCH", URI, data).then(
            function (response) {
              self.model.SelectedRequestIds = [];

              ModalDismiss('cancelPurchaseReqest');
              GetLocalPurchaseRequests();
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
    $stateProvider.state("localPurchhaseRequest", {
      url: "/localPurchhaseRequest",
      templateUrl: 'views/procurement/purchase/local-purchase-request.html',
      controller: "LocalPurchaseRequest.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("LocalPurchaseRequest.Controller", localPurchaseRequestController);
})();