(function () {
  "use strict";
  var CACHE = {};

  function storeIndentApprovalController($scope, $state, $rootScope, $http, CONSTANTS, GenericService, storeIndentApprovalService, $filter, growl) {
    var self = this;
    var approvalStoreIndentId;
    var cancelIndex;

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
      self.model.SearchStoreIndent = searchStoreIndent;
      self.model.StoreIndentApproveDetails = storeIndentApproveDetails;
      self.model.CancelIndent = cancelIndent;
      self.model.SaveRejection = saveRejection;
      self.model.rejectid = null;
      storeIndentApprovalService.setIndentId(0); 
      self.model.storeIndentApprovalList = [];
      self.model.getAlldropdown = null;
      self.model.isDisable =isDisable;
      self.model.reasonList = [];
      
      self.model.hashForAllStatus = {
        "statusId": "1,2,3",
        "statusDesc": "All"
      }
      // self.model.getAlldropdown.status.splice(0, 0, self.model.hashForAllStatus);
      self.model.hashForAllTypes = {
        "typeId": "1,2",
        "type": "All"
      }
      
      // self.model.getAlldropdown.types.splice(0, 0, self.model.hashForAllTypes);
      getDropdownsList();
      getrejectReasonList();
      searchStoreIndent();
    }
 
    
    var  getDropdownsList = function () {
      var indents = self.model;
      indents.fromStore = indents.fromStores[0];
      indents.toStore = indents.toStores[0];
      indents.status = indents.statuses[0];
      indents.indentType = indents.indentTypes[0];
      
      var URI = CONSTANTS.INVENTORY.STORE.STORE_INDENT_APPROVAL.DROPDOWN_API;

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


    function getrejectReasonList() {
      var URI = CONSTANTS.GLOBAL.REJECT_REASON_API;
      var data = {};
      $rootScope.startLoader()
      GenericService.serviceAction("GET", URI, data).then(
        function (response) {
          $rootScope.stopLoader();
          self.model.reasonList = response.data;
        });
    }
 

    var searchStoreIndent = function () { 

      var URI = CONSTANTS.INVENTORY.STORE.STORE_INDENT_APPROVAL.SEARCH_API;

      var data = {
        "fromDate": moment(self.model.fromDate).format('YYYY-MM-DD'),
        "toDate": moment(self.model.toDate).format('YYYY-MM-DD'),
        "fromStore": self.model.fromStore.id,
        "toStore": self.model.toStore.id,
        "indentNo": self.model.indentNo,
        "statusId": self.model.status.id, 
        "indentTypeId": self.model.indentType.id 
      }; 
      $rootScope.startLoader()
      GenericService.serviceAction("POST", URI, data).then(
        function (response) {
          $rootScope.stopLoader();
          for (var i = 0; i < response.data.length; i++) {
            response.data[i]["isChecked"] = false;
          }
          self.model.storeIndentApprovalList = response.data;
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
          growl.error('Error', {
            title: response.status
          });
        });
    }
 
    function cancelIndent(idx) {
      cancelIndex = idx;
      approvalStoreIndentId = self.model.storeIndentApprovalList[idx].id;
    }
    var isDisable = function (Item) {
      if (Item.status == 'Cancelled') {
        return true;
      }
      return false;
    }
    function saveRejection() {
      console.log(approvalStoreIndentId);
      var URI = CONSTANTS.INVENTORY.STORE.STORE_INDENT_APPROVAL.CANCEL_API +  approvalStoreIndentId;
      var data = {
        "cancelReasonId": self.model.reasonSelect,
        "cancelNote": self.model.reasonNote,
        "statusId":9
      };
      GenericService.serviceAction("PATCH", URI, data).then(
        function (response) {
          self.model.reasonSelect = self.model.reasonNote = null;
          growl.success(response.data.message);
          // self.model.reasonResponse = response.data;
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
          growl.error('Error');
        });
      removeRowData(cancelIndex);
      console.log(self.model.reasonSelect, self.model.reasonNote);
    }

    function removeRowData(cancelIndex) {
      var storeIndentApprovalList = self.model.storeIndentApprovalList;
      self.model.storeIndentApprovalList = storeIndentApprovalList.slice(0, cancelIndex).concat(storeIndentApprovalList.slice(cancelIndex + 1));
    }
    function storeIndentApproveDetails(id) {
      storeIndentApprovalService.setIndentId(id);
      $state.go('detailsStoreIndentApproval');
    }

    var OpenFromDate = function () {
      self.model.fromDateOpened = true;
      self.model.toDateOptions.minDate = self.model.fromDate;
    }

    var OpenToDate = function () {
      self.model.toDateOpened = true;
      self.model.toDateOptions.minDate = self.model.fromDate;
    }
       
    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("storeIndentApproval", {
      url: "/storeIndentApproval",
      templateUrl: 'views/PharmacyInventory/InPatient/store-indent-approval.html',
      controller: "StoreIndentApproval.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("StoreIndentApproval.Controller", storeIndentApprovalController)
    .service('storeIndentApprovalService', function () {
      var storeIndentId = [];
      var passStoreIndentId = function (passStoreIndentId) {
        return passStoreIndentId;
      }

      var setIndentId = function (indentId) {
        storeIndentId = indentId;
      };

      var getIndentId = function () {
        return storeIndentId;
      };

      return {
        passStoreIndentId: passStoreIndentId,
        setIndentId: setIndentId,
        getIndentId: getIndentId,
      };
    });
})();