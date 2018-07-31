(function () {
  "use strict";

  function itemCreationController($scope, $rootScope, $http, $filter, $state, CONSTANTS, GenericService, commonDetailService, cancelReasonService, StatusService, growl) {
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
          maxDate: new Date()
        },
        toDateOptions: {
          formatYear: 'yyyy',
          showWeeks: false,
          maxDate: new Date()
        },
        statuses: [{
          "status": "All"
        }],
        itemDetails: [],
        SelectedRequestIds: [],
        screenId: 1
      };

      cancelReasonService.GetReason().then(function(reason){
        self.model.reasons = reason;
      });
      
      StatusService.GetStatus().then(function(status) {
        self.model.statusList = status;
      });

      //Methods
      self.model.OpenFromDate = OpenFromDate;
      self.model.OpenToDate = OpenToDate;
      self.model.GetItemRequests = GetItemRequests;
      self.model.NavigateToAddNew = NavigateToAddNew;
      self.model.NavigateToEdit = NavigateToEdit;
      self.model.NavigateToDetails = NavigateToDetails;
      self.model.OpenPopup = OpenPopup;
      self.model.ModalDismiss = ModalDismiss;
      self.model.isEdit = isEdit;
      self.model.isCancel = isCancel;
      self.model.ItemDetails = ItemDetails;
      self.model.SelectAllRequests = SelectAllRequests;
      self.model.SelectPurchaseRequest = SelectPurchaseRequest;
      self.model.isDisable = isDisable;

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

    //Method to populate the list of Item Request
    var PopulateDropdown = function () {
      var itemRequest = self.model;
      itemRequest.status = itemRequest.statuses[0];

      var URI = CONSTANTS.PROCUREMENT.PURCHASE.ITEM_CREATION.DROPDOWN_API;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          itemRequest.statuses = itemRequest.statuses.concat(response.data.status);
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
        var URI = CONSTANTS.PROCUREMENT.PURCHASE.ITEM_CREATION.SEARCH_API;

        var data = {
          // "screenId": 1,
          "fromDate": moment(itemRequest.fromDate).format('YYYY-MM-DD'),
          "toDate": moment(itemRequest.toDate).format('YYYY-MM-DD'),
          "statusId": !!itemRequest.status && !!itemRequest.status.id ? itemRequest.status.id : undefined
        }
        $rootScope.startLoader();
        GenericService.serviceAction("POST", URI, data).then(
          function (response) {
            itemRequest.itemRequests = response.data;
            $rootScope.stopLoader();
          },
          function (err) {
            $rootScope.stopLoader();
            growl.error("Data Not Found", {title: err.status});
          });
      }
    }

    var NavigateToAddNew = function () {
      $state.go('addNewitemRequest');
    }

    var NavigateToEdit = function (itemRequest) {
      commonDetailService.setDataId(itemRequest.id);
      $state.go('addNewitemRequest');
    }

    var NavigateToDetails = function (itemRequest) {
      commonDetailService.setDataId(itemRequest.id);
      $state.go('itemCreationDetails');
    }

    var SelectAllRequests = function () {
      if (self.model.selectAllRows) {
        self.model.SelectedRequestIds = [];
        self.model.itemRequests.forEach(function (purchase) {
          if (!isDisable(purchase)) {
            purchase.isSelected = true;
            self.model.SelectedRequestIds.push(purchase.id);
          }
        });
      } else {
        self.model.SelectedRequestIds = [];
        self.model.itemRequests.forEach(function (purchase) {
          purchase.isSelected = false;
        });
      }
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

      if (self.model.itemRequests.every(isSelected)) {
        self.model.selectAllRows = true;
      } else {
        self.model.selectAllRows = false;
      }
    }

    var isSelected = function (purchase) {
      if (!isDisable(purchase))
        return purchase.isSelected;
      else
        return true;
    }

    var isDisable = function (item) {
      if (item.status != 'New') {
        return true;
      }
      return false;
    }

    var isEdit = function (item) {
      if (item.status == 'Pending') {
        return true;
      } else if (item.status == 'New') {
        return false;
      } else if (item.status == 'Cancelled') {
        return true;
      }
    }

    var isCancel = function (item) {
      if (item.status == 'Pending') {
        return false;
      } else if (item.status == 'New') {
        return false;
      } else if (item.status == 'Cancelled') {
        return true;
      }
    }

    var OpenPopup = function (action, popupName, data) {
      self.model.action = action;
      self.model.id = data.id;
      var popup = angular.element('#' + popupName);
      popup.modal('show');
    }

    var ValidetReason = function () {
      var isValid = true;

      if (!self.model.cancelReason) {
        self.model.isReasonValid = true;
        isValid = false;
      }
      
      return isValid;
    }

    var ItemDetails = function (item) {
      OpenPopup('Details', 'wasteStoreDetailsModal', {});

      var URI = CONSTANTS.PROCUREMENT.PURCHASE.ITEM_CREATION.DETAILS_API + item.id;

      self.model.itemDetails = [];

      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          self.model.itemDetails = response.data.items;

          $rootScope.stopLoader();
        },
        function (err) {
          $rootScope.stopLoader();
          growl.error(err.data.message, {title: err.status});
        });
    }

    var ModalDismiss = function (modal) {
      var popup = angular.element('#' + modal);
      popup.modal('hide');
    }

    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("itemCreationPendingList", {
      url: "/itemCreationPendingList",
      templateUrl: 'views/procurement/purchase/item-creation-pending-list.html',
      controller: "ItemCreation.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("ItemCreation.Controller", itemCreationController);
})();