(function () {
    "use strict";

    function materialIssueAcceptanceController($scope, $rootScope, $http, $state, GenericService,CONSTANTS, commonDetailService, $filter, growl) {
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
            self.model.MaterialIssueListData = MaterialIssueListData;
            self.model.materialIssueListDetails = materialIssueListDetails;
            self.model.activeItem = '';
            // self.model.AddItemListData = AddItemListData;
            getDropdownsList();
            MaterialIssueListData();
        }

        var getDropdownsList = function () {
            var materialissue = self.model;
            materialissue.fromStore = materialissue.fromStores[0];
            materialissue.toStore = materialissue.toStores[0];
            materialissue.status = materialissue.statuses[0];
            materialissue.indentType = materialissue.indentTypes[0];

            // var URI = constants.apiurl + constants.inventoryApi + '/stores/indents/dropdowns';
            var URI = CONSTANTS.INVENTORY.STORE.MATERIAL_ISSUE_ACCEPTANCE.DROPDOWN_API; 
            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    materialissue.fromStores = materialissue.fromStores.concat(response.data.fromStore);
                    materialissue.toStores = materialissue.toStores.concat(response.data.toStore);
                    materialissue.statuses = materialissue.statuses.concat(response.data.status);
                    materialissue.indentTypes = materialissue.indentTypes.concat(response.data.indentType);
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

        var MaterialIssueListData = function () { 
            // var URI = constants.apiurl + constants.inventoryApi + '/store/material/issue/acceptance/search';
            var URI = CONSTANTS.INVENTORY.STORE.MATERIAL_ISSUE_ACCEPTANCE.SEARCH_API; 
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
                self.model.searchMaterialIssueListData = response.data;
              },
              function errorCallback(response) {
                $rootScope.stopLoader();
                growl.error('Error' );
              });
          }
      
          var materialIssueListDetails = function (issue) {
            // var URI = CONSTANTS.INVENTORY.STORE.MATERIAL_ISSUE_ACCEPTANCE.DETAILS_API; 
            // var id = self.model.searchStoreIndentData[idx].storeIndentMasterId; 
            commonDetailService.setDataId(issue.id);
            //$state.go('detailsMaterialPicker'); 
            // storeIndentService.passCurrentData(self.model.searchStoreIndentData);
          }
      

        initializeController();
    }

    function config($stateProvider) {
        $stateProvider.state("materialIssueAcceptance", {
            url: '/materialIssueAcceptance',
            templateUrl : 'views/PharmacyInventory/InPatient/material-issue-acceptance.html',
            controller: "MaterialIssueAcceptance.Controller",
            controllerAs: 'vm'
        });
    }

    angular
        .module("myApp")
        .config(config)
        .controller("MaterialIssueAcceptance.Controller", materialIssueAcceptanceController)
})();  