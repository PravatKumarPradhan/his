(function () {
  "use strict";

  function poCreationController($scope, $rootScope, $http, $filter, $state, CONSTANTS, GenericService, commonDetailService, cancelReasonService, StatusService, growl) {
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

        SelectedOrderIds: [],
        storeList: [{
          "store": "Select Store"
        }],
        statuses: [{
          "status": "All"
        }]
      };

      cancelReasonService.GetReason().then(function(reason) {
        self.model.reasons = reason;
      });
      
      StatusService.GetStatus().then(function(status) {
        self.model.statusList = status;
      });

      //Methods
      self.model.OpenFromDate = OpenFromDate;
      self.model.OpenToDate = OpenToDate;
      self.model.GetPurchaseOrderList = GetPurchaseOrderList;
      self.model.NavigateToAddNew = NavigateToAddNew;
      self.model.NavigateToDetails = NavigateToDetails;
      self.model.NavigateToEdit = NavigateToEdit;
      self.model.SelectAllOrders = SelectAllOrders;
      self.model.SelectPurchaseOrder = SelectPurchaseOrder;
      self.model.isDisable = isDisable;
      self.model.isEdit = isEdit;
      self.model.isCancel = isCancel;
      self.model.UpdatePurchaseOrder = UpdatePurchaseOrder;
      self.model.ValidateAndUpdate = ValidateAndUpdate;
      self.model.ModalDismiss = ModalDismiss;
      self.model.SearchVendorNames = SearchVendorNames;
      self.model.AddVendorName = AddVendorName;
      self.model.search = '';
      self.model.prevSearch = '';
      self.model.vendorNames = [];

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

    //Method to populate the list of store indent dropdown
    var PopulateDropdown = function () {
      var purchseOrder = self.model;
      purchseOrder.store = purchseOrder.storeList[0];
      purchseOrder.status = purchseOrder.statuses[0];

      var URI = CONSTANTS.PROCUREMENT.QUOTATION.PURCHSE_ORDER.DROPDOWN_API;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          purchseOrder.storeList = purchseOrder.storeList.concat(response.data.store);
          purchseOrder.statuses = purchseOrder.statuses.concat(response.data.status);
          $rootScope.stopLoader();
          GetPurchaseOrderList();
        },
        function (err) {
          $rootScope.stopLoader();
        });
    }

    function SearchVendorNames(search) {
      if (search.length < 3) return;

      if ((!self.model.prevSearch || self.model.prevSearch != search) && (search.length == 3)) {
        self.model.prevSearch = search;
        var URI = CONSTANTS.GLOBAL.VENDOR_SEARCH_API + search;
        $rootScope.startLoader();
        return GenericService.serviceAction("GET", URI, {})
        .then(function (response) {
          if (!!response.data && response.data.length > 0) {
            self.model.vendorNames = response.data;
            $rootScope.stopLoader();
            return $filter('filter')(self.model.vendorNames, {
              $: search
            });
          } else {
            $rootScope.stopLoader();
            return nameNotFound(search);
          }
        }, function (err) {
          $rootScope.stopLoader();
          return nameNotFound(search);
        });
      } else {
        if (!!self.model.vendorNames && self.model.vendorNames.length > 0 && self.model.vendorNames[0].itemFound != undefined && !self.model.vendorNames[0].itemFound) {
          return nameNotFound(search);
        } else {
          return $filter('filter')(self.model.vendorNames, {
            $: search
          });
        }
      }
    }

    function nameNotFound(search) {
      var item = {
        "itemFound": false,
        "detail": "Vendor Not found with name " + search
      };
      self.model.vendorNames = [item];
      return self.model.vendorNames;
    }

    function AddVendorName (vendor) {
      self.model.vendorName = vendor.vendorName;
      self.model.vendorId = vendor.id;
    }

    var ValidateDate = function () {
      var isValid = true;
      if (self.model.fromDate > self.model.toDate) {
        growl.error('From Date should lesser than To Date');
        isValid = false;
      }

      return isValid;
    }

    var GetPurchaseOrderList = function () {
      if (ValidateDate()) {
        var purchaseOrder = self.model;
        var URI = CONSTANTS.PROCUREMENT.QUOTATION.PURCHSE_ORDER.GET_PO_API;

        var data = {
          "fromDate": moment(purchaseOrder.fromDate).format('YYYY-MM-DD'),
          "toDate": moment(purchaseOrder.toDate).format('YYYY-MM-DD'),
          "storeId": !!purchaseOrder.store && !!purchaseOrder.store.id ? purchaseOrder.store.id : undefined,
          "vendorId": self.model.vendorId,
          "vendorName": self.model.vendorName ? self.model.vendorName : undefined,
          "poNumber": self.model.purchaseOrderNo ? self.model.purchaseOrderNo : undefined,
          "statusId": !!purchaseOrder.status && !!purchaseOrder.status.id ? purchaseOrder.status.id : undefined,
        }

        $rootScope.startLoader();
        GenericService.serviceAction("POST", URI, data).then(
          function (response) {
            purchaseOrder.purchaseOrders = response.data;
            $rootScope.stopLoader();
          },
          function (err) {
            $rootScope.stopLoader();
            growl.error("Data Not Found", {title: err.status});
          });
      }
    }

    var NavigateToAddNew = function () {
      commonDetailService.setDataId(null);
      $state.go('addItemPO');
    }

    var NavigateToDetails = function (purchase) {
      commonDetailService.setDataId(purchase.id);
      $state.go('detailsPOCreation');
    }

    var NavigateToEdit = function (purchase) {
      commonDetailService.setDataId(purchase.id);
      $state.go('addItemPO');
    }

    var SelectAllOrders = function () {
      if (self.model.selectAllRows) {
        self.model.SelectedOrderIds = [];
        self.model.purchaseOrders.forEach(function (purchase) {
          if (!isDisable(purchase)) {
            purchase.isSelected = true;
            self.model.SelectedOrderIds.push(purchase.id);
          }
        });
      } else {
        self.model.SelectedOrderIds = [];
        self.model.purchaseOrders.forEach(function (purchase) {
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
      if (purchase.status == 'New') {
        return false;
      } else {
        return true;
      }
    }

    var isCancel = function (purchase) {
      if (purchase.status == 'New') {
        return false;
      } else if (purchase.status == 'Cancelled' || purchase.status == 'Closed') {
        return true;
      }
    }

    var isSelected = function (purchase) {
      if (!isDisable(purchase))
        return purchase.isSelected;
      else
        return true;
    }

    var SelectPurchaseOrder = function (purchase) {
      var purchaseIds = self.model.SelectedOrderIds;

      if (purchase.isSelected) {
        purchaseIds.push(purchase.id);
      } else {
        var index = purchaseIds.indexOf(purchase.id);
        if (index > -1) {
          purchaseIds.splice(index, 1);
        }
      }

      if (self.model.purchaseOrders.every(isSelected)) {
        self.model.selectAllRows = true;
      } else {
        self.model.selectAllRows = false;
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
      if (!self.model.SelectedOrderIds || self.model.SelectedOrderIds.length == 0) {
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

    var UpdatePurchaseOrder = function () {
      if (self.model.action == 'Update') {
        if (ValidateStatus()) {
          var URI = CONSTANTS.PROCUREMENT.QUOTATION.PURCHSE_ORDER.APPROVE_PO_API;

          var data = {
            "poIdList": self.model.SelectedOrderIds,
            "statusId": setStatus('Pending'),
            "approvalStatusId": setStatus('Pending Approval')
          };

          $rootScope.startLoader();
          GenericService.serviceAction("PATCH", URI, data).then(
            function (response) {
              self.model.SelectedOrderIds = [];
              ModalDismiss('confirmSave');
              $rootScope.stopLoader();
              GetPurchaseOrderList();
              self.model.selectAllRows = false;
              growl.success(response.data.message);
            },
            function (err) {
              ModalDismiss('confirmSave');
              $rootScope.stopLoader();
              growl.error(err.data.message);
            });
        } else {
          growl.error('Select PO to Approve');
          ModalDismiss('confirmSave');
          return;
        }
      } else if (self.model.action == 'Cancel') {
        if (ValidetReason()) {
          var URI = CONSTANTS.PROCUREMENT.QUOTATION.PURCHSE_ORDER.CANCEL_PO_API + self.model.id;
          
          var data = {
            "statusId": setStatus('Closed'),
            "approvalStatusId": setStatus('Cancelled'),
            "cancelReasonId": self.model.cancelReason ? self.model.cancelReason : undefined,
            "cancelNote": self.model.rejectNote
          }

          $rootScope.startLoader();         
          GenericService.serviceAction("PATCH", URI, data).then(
            function (response) {
              self.model.SelectedOrderIds = [];
              ModalDismiss('cancelPO');
              GetPurchaseOrderList();

              $rootScope.stopLoader();
              growl.success(response.data.message);
            }, function (err) {
              $rootScope.stopLoader();
              growl.error(err.data.message, {title: err.status});
            });
        }
      }
    }

    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("POCreation", {
      url: "/POCreation",
      templateUrl: 'views/procurement/quotation/PO-creation.html',
      controller: "PoCreation.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("PoCreation.Controller", poCreationController);
})();