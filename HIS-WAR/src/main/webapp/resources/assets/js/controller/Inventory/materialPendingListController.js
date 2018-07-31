(function () {
    "use strict";

    function materialPendingListController($scope, $rootScope, $http, $state, CONSTANTS, GenericService, commonDetailService, $filter, growl) {
        var self = this;
        var selectItemList = [];
        var masterId;
        //Default Constructor
        function initializeController() {
            $rootScope.loginpage = true;
            self.model = {

                requestData: [],
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
            self.model.MaterialPendingListData = MaterialPendingListData;
            self.model.materialPendingListDetails = materialPendingListDetails;
            self.model.activeItem = '';
            // self.model.AddItemListData = AddItemListData;
            getDropdownsList();
            MaterialPendingListData();
        }

        var getDropdownsList = function () {
            var materialpending = self.model;
            materialpending.fromStore = materialpending.fromStores[0];
            materialpending.toStore = materialpending.toStores[0];
            materialpending.status = materialpending.statuses[0];
            materialpending.indentType = materialpending.indentTypes[0];

           
            var URI = CONSTANTS.INVENTORY.STORE.MATERIAL_ISSUE.MATERIAL_PENDING_ISSUE.DROPDOWN_API;

            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    materialpending.fromStores = materialpending.fromStores.concat(response.data.fromStore);
                    materialpending.toStores = materialpending.toStores.concat(response.data.toStore);
                    materialpending.statuses = materialpending.statuses.concat(response.data.status);
                    materialpending.indentTypes = materialpending.indentTypes.concat(response.data.indentType);
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

        var MaterialPendingListData = function () { 

            var URI = CONSTANTS.INVENTORY.STORE.MATERIAL_ISSUE.MATERIAL_PENDING_ISSUE.SEARCH_API;

            var data = {  
              "fromDate": moment(self.model.fromDate).format('YYYY-MM-DD'),
              "toDate": moment(self.model.toDate).format('YYYY-MM-DD'),
              "fromStoreId": self.model.fromStore.id ? self.model.fromStore.id : undefined,
              "toStoreId": self.model.toStore.id ? self.model.toStore.id:undefined,
              "indentNo": self.model.indentNo? self.model.indentNo:undefined,
              "statusId": self.model.status.id? self.model.status.id:undefined, 
              "indentTypeId": self.model.indentType.id ? self.model.indentType.id:undefined, 
            };
            $rootScope.startLoader()
            GenericService.serviceAction("POST", URI, data).then(
              function (response) {
                $rootScope.stopLoader();
                for (var i = 0; i < response.data.length; i++) {
                  response.data[i]["isChecked"] = false;
                }
                self.model.searchMaterialPendingListData = response.data;
              },
              function errorCallback(response) {
                $rootScope.stopLoader();
                growl.error('Error');
              });
          }
      
          var materialPendingListDetails = function (pending) { 
            commonDetailService.setDataId(pending.id);
            $state.go('detailsMaterialPending');  
          }
      

        initializeController();
    }

    function config($stateProvider) {
        $stateProvider.state("materialIssue.pendingIssue", {
        url: '/pendingIssue',
            templateUrl : 'views/PharmacyInventory/InPatient/material-pending-issue.html',
            controller: "MaterialPendingList.Controller",
            controllerAs: 'vm'
        });
    }

    angular
        .module("myApp")
        .config(config)
        .controller("MaterialPendingList.Controller", materialPendingListController)
})();
