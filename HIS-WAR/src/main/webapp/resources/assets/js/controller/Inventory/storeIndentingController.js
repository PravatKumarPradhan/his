(function () {
  "use strict";
  var CACHE = {};

  function storeIndentingController($scope, $rootScope, $http, CONSTANTS, StatusService, GenericService, cancelReasonService, storeIndentService, $state, $filter, growl) {
    var self = this;
    var itemId;
    //Default Constructor
    function initializeController() {
      $rootScope.loginpage = true;
      self.model = {

        selectItemList: [],
        requestData: [],
        storeIndentingRow: [],
        selectAllRows: false,
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
        fromDate: new Date(),
        toDate: new Date(),
        SelectedRequestIds: [],
        // indentNumber: 'IHI-123'
        fromStores: [{
          "store": "All Stores"
        }],
        toStores: [{
          "store": "All Stores"
        }],
        statuses: [{
          "status": "All"
        }],
        indentTypes: [{
          "type": "All"
        }],

      };

      self.model.OpenFromDate = OpenFromDate;
      self.model.OpenToDate = OpenToDate;
      self.model.AddItemListData = AddItemListData;
      self.model.RemoveRowData = RemoveRowData;
      self.model.StoreIndentingDetails = StoreIndentingDetails;
      self.model.StoreIndentData = StoreIndentData;
      self.model.EditStoreIndentDetails = EditStoreIndentDetails;
      self.model.ApproveStoreIndent = ApproveStoreIndent;
      self.model.GetStatusData = GetStatusData;
      self.model.ValidateAndUpdate = ValidateAndUpdate;
      self.model.rejectReasonId = null;
      self.model.noteReject = null;
      self.model.rejectid = null;
      self.model.isDisable = isDisable;
      self.model.SelectItem = SelectItem;
      self.model.ApproveItem = ApproveItem;
      self.model.SelectPurchaseRequest = SelectPurchaseRequest;
      self.model.SelectAllRequests = SelectAllRequests;


      StatusService.GetStatus().then(function (status) {
        self.model.statuselect = status;
      });
  
      cancelReasonService.GetReason().then(function (reason) {
        self.model.reasons = reason;
      });
      
      storeIndentService.setIndentId(0);
      getDropdownsList();
      getrejectReasonList();
      StoreIndentData();
    }

    var ValidateAndUpdate = function (action, popupName, id) {
      itemId = id;
      self.model.action = action;
      var popup = angular.element('#' + popupName);
      popup.modal('show');
    }

    var getDropdownsList = function () {
      var indents = self.model;
      indents.fromStore = indents.fromStores[0];
      indents.toStore = indents.toStores[0];
      indents.status = indents.statuses[0];
      indents.indentType = indents.indentTypes[0];

      var URI = CONSTANTS.INVENTORY.STORE.STORE_INDENT.DROPDOWN_API;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          indents.fromStores = indents.fromStores.concat(response.data.fromStore);
          indents.toStores = indents.toStores.concat(response.data.toStore);
          indents.statuses = indents.statuses.concat(response.data.status);
          indents.indentTypes = indents.indentTypes.concat(response.data.indentType);
          $rootScope.stopLoader();
          // GetStoreIndents();
        },
        function (err) {
          $rootScope.stopLoader();
        });
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

    var StoreIndentData = function () {
      var URI = CONSTANTS.INVENTORY.STORE.STORE_INDENT.SEARCH_API;
      var data = {
        "fromDate": moment(self.model.fromDate).format('YYYY-MM-DD'),
        "toDate": moment(self.model.toDate).format('YYYY-MM-DD'),
        "fromStoreId": self.model.fromStore.id ? self.model.fromStore.id : undefined,
        "toStoreId": self.model.toStore.id ? self.model.toStore.id : undefined,
        "indentNo": self.model.indentNumber ? self.model.indentNumber : undefined,
        "statusId": self.model.status.id ? self.model.status.id : undefined,
        "approvalStatus": self.model.approxvalStatus ? self.model.approvalStatus : undefined,
        "indentTypeId": self.model.indentType ? self.model.indentType.id : undefined,
        "approvalStatusId": self.model.approvalStatusId ? self.model.approvalStatusId : undefined
      };
      $rootScope.startLoader()
      GenericService.serviceAction("POST", URI, data).then(
        function (response) {
          $rootScope.stopLoader();
          for (var i = 0; i < response.data.length; i++) {
            response.data[i]["isChecked"] = false;
          }
          self.model.searchStoreIndentData = response.data;
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
          growl.error('Error');
        });
    }

    function getrejectReasonList() {
      var URI = CONSTANTS.GLOBAL.CANCEL_REASON_API;
      var data = {};
      $rootScope.startLoader()
      GenericService.serviceAction("GET", URI, data).then(
        function (response) {
          $rootScope.stopLoader();
          self.model.rejectReasonList = response.data;
        });
    }

    var ModalDismiss = function (modal) {
      var popup = angular.element('#' + modal);
      popup.modal('hide');
    }



    function SelectItem(Item) {
      if (Item.isChecked) {
        self.model.selectItemList.push(Item.id);
      } else {
        var index = self.model.selectItemList.indexOf(Item.itemId);
        if (index > -1) {
          self.model.selectItemList.splice(index, 1);
        }
      }
      var allselected = self.model.searchStoreIndentData.findIndex(x => (x.isChecked != true && x.status == 'New'))
      if (allselected < 0) { // As is returns -1 if al rows selected
        self.model.selectAllRows = true;
      } else {
        self.model.selectAllRows = false;
      }
    }


    var ApproveItem = function () {
      if (self.model.action == 'Update') {
        var URI = CONSTANTS.INVENTORY.STORE.STORE_INDENT.APPROVE_API;

        var data = {
          "indentId": self.model.SelectedRequestIds,
          "statusId": setStatus('Pending'),
          "approvalStatusId": setStatus('Pending Approval')
        };

        $rootScope.startLoader();
        GenericService.serviceAction("PATCH", URI, data).then(
          function (response) {
            self.model.SelectedRequestIds = [];
            $rootScope.stopLoader();
            // GetStoreIndents();
            growl.success(response.data.message);
            StoreIndentData();
          },
          function (err) {
            $rootScope.stopLoader();
            growl.error(err.data.msg);
          });
      } else if (self.model.action == 'Cancel') {

        var URI = CONSTANTS.INVENTORY.STORE.STORE_INDENT.CANCEL_API + itemId;

        var data = {
          "cancelReasonId": self.model.cancelReason,
          "cancelNote": self.model.cancelNote,
          "statusId": setStatus('Cancelled')
        };

        GenericService.serviceAction("PATCH", URI, data).then(
          function (response) {
            self.model.SelectedRequestIds = [];
            $rootScope.stopLoader();
            // GetStoreIndents();
            growl.success(response.data.message);
            // ModalDismiss('confirmLogout1');
            StoreIndentData();
          },
          function (err) {
            $rootScope.stopLoader();
            growl.error(err.data.msg);
          });
      }

    }

  

    function setStatus(x) {
      var status = self.model.statuselect.find(function (obj) {
        return obj.status == x;
      });

      return status.id;
    }
    var ApproveStoreIndent = function () {
      for (var i = 0; i < self.model.searchStoreIndentData.length; i++) {
        if (self.model.searchStoreIndentData[i]["isChecked"] == true) {
          // self.model.searchStoreIndentData[i].statusCode = 'P';
          self.model.searchStoreIndentData[i].isChecked = true;
        }
      }
    }

    var GetStatusData = function () {
      var URI = constants.apiurl + constants.inventoryApi + '/statusIndents';
      var data = {};
      GenericService.serviceAction("GET", URI, data).then(
        function (response) {
          self.model.statusData = response.data;
        })
    }

    var EditStoreIndentDetails = function (Item) {
      storeIndentService.setIndentId(Item.id);
      $state.go('addNewStoreIndenting');
    }

    var StoreIndentingDetails = function (Item) {
      storeIndentService.setIndentId(Item.id);
      $state.go('DetailsStoreIndenting');
      storeIndentService.passStoreIndentId(Item.id);
    }

    var RemoveRowData = function (index) {
      console.log(self.model.storeIndentingData)
      var storeIndentingRow = self.model.storeIndentingData;
      self.model.storeIndentingData = storeIndentingRow.slice(0, index).concat(storeIndentingRow.slice(index + 1))
    }


    var AddItemListData = function () {
      var data = self.model.storeDataList;
      self.model.storeDataList = [];
      self.model.selectAllRows = false;
      for (var i = 0; i < data.length; i++) {
        if (data[i]["isChecked"] == true) {
          self.model.storeIndentingData.push(data[i]);
        } else {
          self.model.storeDataList.push(data[i]);
        }
      }
      console.log(self.model.storeIndentingData)
    }

    var SelectAllRequests = function () {
      if (self.model.selectAllRows) {
        self.model.SelectedRequestIds = [];
        self.model.searchStoreIndentData.forEach(function (indent) {
          if (!isDisable(indent)) {
            indent.isSelected = true;
            self.model.isItemChecked = true;
            self.model.SelectedRequestIds.push(indent.id);
          }
        });
      } else {
        self.model.SelectedRequestIds = [];
        self.model.searchStoreIndentData.forEach(function (indent) {
          indent.isSelected = false;
          self.model.isItemChecked = false;
        });
      }
      
    }

    var isSelected = function (indent) {
      if (!isDisable(indent))
        return indent.isSelected;
      else
        return true;
    }

    var SelectPurchaseRequest = function (indent) {
      var indentIds = self.model.SelectedRequestIds;

      if (indent.isSelected) {
        indentIds.push(indent.id);
      } else {
        var index = indentIds.indexOf(indent.id);
        if (index > -1) {
          indentIds.splice(index, 1);
        }
      }

      if (self.model.searchStoreIndentData.every(isSelected)) {
        self.model.selectAllRows = true;
      } else {
        self.model.selectAllRows = false;
      }
      self.model.isItemChecked = false;
      self.model.searchStoreIndentData.forEach(function (indent) {
        if (indent.isSelected == true) {
          self.model.isItemChecked = true;
          return;
        }
      });
    }


    var ClearFields = function () {

      self.model.assetCategory.isWalkInSales = false;
      self.model.isOTC = false;

    }


    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("storeIndenting", {
      url: "/storeIndenting",
      templateUrl: 'views/PharmacyInventory/InPatient/store-indenting.html',
      controller: "StoreIndenting.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("StoreIndenting.Controller", storeIndentingController)
})();