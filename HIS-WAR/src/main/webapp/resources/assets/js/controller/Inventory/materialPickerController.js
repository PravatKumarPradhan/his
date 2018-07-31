(function () {
    "use strict";

    function materialPickerController($scope, $rootScope, $http, $state, CONSTANTS, GenericService, materialPickerService, $filter, growl) {
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
            self.model.MaterialPickerData = MaterialPickerData;
            self.model.materialPickerDetails = materialPickerDetails;
            self.model.activeItem = '';
            // self.model.AddItemListData = AddItemListData;
            getDropdownsList();
            MaterialPickerData();
        }

        var getDropdownsList = function () {
            var materialpicker = self.model;
            materialpicker.fromStore = materialpicker.fromStores[0];
            materialpicker.toStore = materialpicker.toStores[0];
            materialpicker.status = materialpicker.statuses[0];
            materialpicker.indentType = materialpicker.indentTypes[0];

            var URI = CONSTANTS.INVENTORY.STORE.MATERIAL_PICKER.DROPDOWN_API;

            // var URI = constants.apiurl + constants.inventoryApi + '/material/picker/dropdowns';

            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    materialpicker.fromStores = materialpicker.fromStores.concat(response.data.fromStore);
                    materialpicker.toStores = materialpicker.toStores.concat(response.data.toStore);
                    materialpicker.statuses = materialpicker.statuses.concat(response.data.status);
                    materialpicker.indentTypes = materialpicker.indentTypes.concat(response.data.indentType);
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

        var MaterialPickerData = function () { 

            var URI = CONSTANTS.INVENTORY.STORE.MATERIAL_PICKER.SEARCH_API;

            // var URI = constants.apiurl + constants.inventoryApi + '/stores/material/picker/search';
            var data = {  
              "fromDate": moment(self.model.fromDate).format('YYYY-MM-DD'),
              "toDate": moment(self.model.toDate).format('YYYY-MM-DD'),
              "fromStoreId": self.model.fromStore.id ? self.model.fromStore.id : undefined,
              "toStoreId": self.model.toStore.id ? self.model.toStore.id:undefined,
              "indentNo": self.model.indentNo? self.model.indentNo:undefined,
               "statusId": self.model.status.id? self.model.status.id:undefined, 
              "indentType": self.model.indentType.id ? self.model.indentType.id:undefined, 
            };
            $rootScope.startLoader()
            GenericService.serviceAction("POST", URI, data).then(
              function (response) {
                $rootScope.stopLoader();
                for (var i = 0; i < response.data.length; i++) {
                  response.data[i]["isChecked"] = false;
                }
                self.model.searchMaterialPickerData = response.data;
              },
              function errorCallback(response) {
                $rootScope.stopLoader();
                growl.error('Error');
              });
          }
      
          var materialPickerDetails = function (picker) {
            // var id = self.model.searchStoreIndentData[idx].storeIndentMasterId; 
            materialPickerService.setMaterialPickerId(picker.id);
            //$state.go('detailsMaterialPicker'); 
            // storeIndentService.passCurrentData(self.model.searchStoreIndentData);
          }
      

        initializeController();
    }

    function config($stateProvider) {
        $stateProvider.state("materialPicker", {
            url: '/materialPicker',
            templateUrl: 'views/PharmacyInventory/InPatient/material-picker.html',
            controller: "MaterialPicker.Controller",
            controllerAs: 'vm'
        });
    }

    angular
        .module("myApp")
        .config(config)
        .controller("MaterialPicker.Controller", materialPickerController)
})();
