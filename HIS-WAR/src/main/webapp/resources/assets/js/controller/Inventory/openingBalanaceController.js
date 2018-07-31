(function () {
  "use strict";

  function openingBalanceController($scope, $rootScope, $http, CONSTANTS, GenericService, $state, cancelReasonService, openingBalanceService, productService, $filter, growl) {
    var self = this;
    var selectItemList = [];
    var storeIndentDetailsIndex;
    var masterId;
    var opbId;
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
        selectItemList: [],
        SelectedRequestIds: [],
        detailsOpeningBalance: [],
        opStoreIndentData: [],
        selectAllRows: false,
        stores: [{
          "store": "Select Store"
        }],
        hash: {},
        requestData: [],
        approvedItemList: [],
        approveIndent: [],
        storeData: 1,
        selectedStoreId: 1,
        cancelId: 0
      };
      self.model.editItem = null;
      self.model.OpenFromDate = OpenFromDate;
      self.model.OpenToDate = OpenToDate;
      self.model.OpeningBalanceDetails = OpeningBalanceDetails;
      self.model.SelectAllRequests = SelectAllRequests;
      self.model.SelectAllApprove = SelectAllApprove;
      self.model.ValidateAndUpdate = ValidateAndUpdate;
      // self.model.EditOPBDetails = EditOPBDetails;
      self.model.EditOpeningBalanceDetails = EditOpeningBalanceDetails;
      self.model.OpeningBalanceDetailsAddNew = OpeningBalanceDetailsAddNew;
      
      self.model.SearchByStore = SearchByStore;
      self.model.isDisable = isDisable;
      self.model.SelectOpeningBalance = SelectOpeningBalance;
      self.model.ApproveOpb = ApproveOpb;
      self.PopulateDropdown = PopulateDropdown;
      productService.setProductId(0);
      PopulateDropdown();
      SearchByStore();
    }


    var OpenFromDate = function () {
      self.model.fromDateOpened = true;
      self.model.toDateOptions.minDate = self.model.fromDate;
    }

    var OpenToDate = function () {
      self.model.toDateOpened = true;
      self.model.toDateOptions.minDate = self.model.fromDate;
    }
    var isDisable = function (Item) {
      if (Item.status != 'New') {
        return true;
      }
      return false;
    } 

    var PopulateDropdown = function () {

      var obp = self.model;

      var URI = CONSTANTS.GLOBAL.USER_STORE_API;

      obp.store = obp.stores[0];

      obp.stores.splice(1);

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          obp.stores = obp.stores.concat(response.data);
          $rootScope.stopLoader();
        },
        function (err) {
          $rootScope.stopLoader();
        });
    }

    var SearchByStore = function () {
      if (!self.model.selectedStoreId) {
        growl.error('Select Store First!', {
          title: 'WARNING'
        });
        return;
      } else {
        var URI = CONSTANTS.INVENTORY.STOCK.OPENING_BALANCE.SEARCH_API;
        var data = {
          "fromDate": moment(self.model.fromDate).format('YYYY-MM-DD'),
          "toDate": moment(self.model.toDate).format('YYYY-MM-DD'),
          "storeId": self.model.store.id
        };
        $rootScope.startLoader();
        GenericService.serviceAction("POST", URI, data).then(
          function (response) {
            self.model.opStoreIndentData = response.data;
            $rootScope.stopLoader(); 
          },
          function errorCallback(response) {
            growl.error('Error');
            $rootScope.stopLoader();
          });
      }
    }

    var ValidateAndUpdate = function (action, popupName, opbid) {

      opbId = opbid;
      // opbId = opb.id;
      self.model.action = action;
      var popup = angular.element('#' + popupName);
      popup.modal('show');
    }

    var ApproveOpb = function () {

      if (self.model.action == 'Update') {
        var URI = CONSTANTS.INVENTORY.STOCK.OPENING_BALANCE.APPROVE_API;

        var data = {
          "openingBalanceId": self.model.SelectedRequestIds,
          "statusId": 8
        };

        $rootScope.startLoader();
        GenericService.serviceAction("PATCH", URI, data).then(
          function (response) {
            self.model.SelectedRequestIds = [];
            $rootScope.stopLoader();
            // GetStoreIndents();
            growl.success(response.data.message);
            SearchByStore();
          },
          function (err) {
            $rootScope.stopLoader();
            growl.error(err.data.msg);
          });
      } else if (self.model.action == 'Cancel') {

        var URI = CONSTANTS.INVENTORY.STOCK.OPENING_BALANCE.CANCEL_API + opbId;

        var data = {
          "cancelReasonId": self.model.cancelReason,
          "cancelNote": self.model.cancelNote,
          "statusId": 9
        };

        GenericService.serviceAction("PATCH", URI, data).then(
          function (response) {
            self.model.SelectedRequestIds = [];
            $rootScope.stopLoader();
            // GetStoreIndents();
            growl.success(response.data.message);
            SearchByStore();
          },
          function (err) {
            $rootScope.stopLoader();
            growl.error(err.data.msg);
          });
      }

    }

    cancelReasonService.GetReason().then(function (reason) {
      self.model.reasons = reason;
    });

    var SelectAllRequests = function () {
      if (self.model.selectAllRows) {
        self.model.SelectedRequestIds = [];
        self.model.opStoreIndentData.forEach(function (opb) {
          if (!isDisable(opb)) {
            opb.isSelected = true;
            self.model.SelectedRequestIds.push(opb.id);
            self.model.isItemChecked = true;
          }
        });
      } else {
        self.model.SelectedRequestIds = [];
        self.model.opStoreIndentData.forEach(function (opb) {
          opb.isSelected = false;
          self.model.isItemChecked = false;
        });
      }
    }

    var isSelected = function (opb) {
      if (!isDisable(opb))
        return opb.isSelected;
      else
        return true;
    }

    var SelectOpeningBalance = function (opb) {

      var opbIds = self.model.SelectedRequestIds;

      if (opb.isSelected) {
        opbIds.push(opb.id);
      } else {
        var index = opbIds.indexOf(opb.id);
        if (index > -1) {
          opbIds.splice(index, 1);
        }
      }

      if (self.model.opStoreIndentData.every(isSelected)) {
        self.model.selectAllRows = true;
      } else {
        self.model.selectAllRows = false;
      }
      self.model.isItemChecked = false;
      self.model.opStoreIndentData.forEach(function (opb) {
        if (opb.isSelected == true) {
          self.model.isItemChecked = true;
          return;
        }
      });

    }
    var SelectAllApprove = function () {
      self.model.selectItemList = [];
      self.model.opStoreIndentData.forEach(function (x) {
        if (self.model.selectAllApprove) {
          x.isApprove = true
          self.model.selectItemList.push(x.id);
        } else {
          x.isApprove = false;
        }
      });
    }

    var EditOpeningBalanceDetails = function (Item) {
      openingBalanceService.setOpeningBalanceId(Item.id);
      $state.go('addNewOpeningBalance'); 
    }

    var OpeningBalanceDetails = function (Item) { 
      openingBalanceService.setOpeningBalanceId(Item.id);
      $state.go('DetailsOpeningBalance'); ;
    }

    var OpeningBalanceDetailsAddNew = function (Item) { 
      openingBalanceService.setOpeningBalanceId(null);
      $state.go('addNewOpeningBalance'); ;
    } 

    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("openingBalance", {
      url: "/openingBalance",
      templateUrl: 'views/PharmacyInventory/Store/opening-balance.html',
      controller: "OpeningBalance.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("OpeningBalance.Controller", openingBalanceController)
})();