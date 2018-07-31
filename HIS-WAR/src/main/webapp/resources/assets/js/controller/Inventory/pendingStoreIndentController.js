(function () {
  "use strict";
  var CACHE = {};
  function pendingStoreIndentController($scope, $rootScope, $http, CONSTANTS, growl, GenericService, storeIndentService, $state, $filter) {
    var self = this;
    var selectItemList = [];
    var pendingStoreIndentId;
    var cancelIndex;
    // var typeIdArrray = [];
    // var priorityIdArray = [];

    //Default Constructor
    function initializeController() {
      $rootScope.loginpage = true;
      self.model = {
        selectItemList: [],
        fromStores: [{
          "store": "All Stores"
        }],
        toStores: [{
          "store": "All Stores"
        }],
        indentTypes: [{
          "type": "All"
        }],
        priorities: [{
          "priority": "All"
        }],

      };
      // self.model.indentNumber = 'IHI-123';
      self.model.SearchPendingStoreIndent = searchPendingStoreIndent;
      self.model.storeIndentPendingDetails = storeIndentPendingDetails;
      self.model.saveRejection = saveRejection;
      self.model.openmodal = openmodal;
      PopulateDropdown();

      getrejectReasonList();
      searchPendingStoreIndent();

    }


    //Method to populate the list of store indent dropdown
    var PopulateDropdown = function () {
      var indents = self.model;
      indents.fromStore = indents.fromStores[0];
      indents.toStore = indents.toStores[0];
      indents.indentType = indents.indentTypes[0];
      indents.priority = indents.priorities[0];

      var URI = CONSTANTS.INVENTORY.STORE.PENDING_STORE_INDENT.DROPDOWN_API;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          indents.fromStores = indents.fromStores.concat(response.data.fromStore);
          indents.toStores = indents.toStores.concat(response.data.toStore);
          indents.indentTypes = indents.indentTypes.concat(response.data.indentType);
          indents.priorities = indents.priorities.concat(response.data.priority);

          $rootScope.stopLoader();
          // GetStoreIndents();
        },
        function (err) {
          $rootScope.stopLoader();
        });
    }

    function storeIndentPendingDetails(data) {
      storeIndentService.setIndentId(data.id);
      $state.go('detailspendingStoreIndents');
    }


    var searchPendingStoreIndent = function () {
      var URI = CONSTANTS.INVENTORY.STORE.PENDING_STORE_INDENT.SEARCH_API;
      var data = {
        "fromStoreId": self.model.fromStore.id ? self.model.fromStore.id : undefined,
        "toStoreId": self.model.toStore.id ? self.model.toStore.id : undefined,
        "indentNo": self.model.indentNumber ? self.model.indentNumber : undefined,
        "indentTypeId": self.model.indentType.id ? self.model.indentType.id : undefined,
        "priorityId": self.model.priority.id ? self.model.priority.id : undefined
      };
      $rootScope.startLoader()
      GenericService.serviceAction("POST", URI, data).then(
        function (response) {
          $rootScope.stopLoader();
          self.model.pendingStoreIndentsList = response.data;
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
          growl.error('Error');
        });
    }

    var openmodal = function (pid) {
      $("#CanellationModalForPendingStoreIndend").modal();
      pendingStoreIndentId = pid;
    }

    var saveRejection = function () {
      var URI = CONSTANTS.INVENTORY.STORE.PENDING_STORE_INDENT.CANCEL_API + pendingStoreIndentId;
      var data = {
        "closeReasonId": self.model.cancelReason,
        "closeNote": self.model.cancelNote,
        "statusId": 9
      };
      GenericService.serviceAction("PATCH", URI, data).then(
        function (response) {
          self.model.reasonResponse = response.data; 
        })
      searchPendingStoreIndent();
        removeRowData(cancelIndex);
        self.model.cancelNote = '';
        self.model.cancelReason =[];

      
    }

    function removeRowData(cancelIndex) {
      self.model.pendingStoreIndentsList.splice(cancelIndex, 1);
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


    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("pendingStoreIndents", {
      url: "/pendingStoreIndents",
      templateUrl: 'views/PharmacyInventory/InPatient/pending-store-indents.html',
      controller: "PendingStoreIndent.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("PendingStoreIndent.Controller", pendingStoreIndentController);
})();