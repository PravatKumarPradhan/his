(function () {
    "use strict";

    function rejectedMaterialAcceptanceController($scope, CONSTANTS, $rootScope, $http, $state, GenericService, commonDetailService, $filter, growl) {
        var self = this;
        var selectItemList = [];
        var masterId;
        //Default Constructor
        function initializeController() {
            $rootScope.loginpage = true;
            self.model = {

                requestData: [], 
                fromStores: [{
                    "store": "All Stores"
                }],
                toStores: [{
                    "store": "All Stores"
                }],
                statuses: [{
                    "status": "All"
                }] ,
 
            };
 
            self.model.MaterialRejectAcceptanceData = MaterialRejectAcceptanceData;
            self.model.materialRejectAcceptanceDetails = materialRejectAcceptanceDetails;
            self.model.activeItem = ''; 
            // self.model.AddItemListData = AddItemListData;
            getDropdownsList();
            MaterialRejectAcceptanceData();
        }

        var getDropdownsList = function () {
            var materialpicker = self.model;
            materialpicker.fromStore = materialpicker.fromStores[0];
            materialpicker.toStore = materialpicker.toStores[0];
            materialpicker.status = materialpicker.statuses[0];

            var URI = CONSTANTS.INVENTORY.STORE.REJECTED_MATERIAL_ACCEPTANCE.DROPDOWN_API;
            // var URI = constants.apiurl + constants.inventoryApi + '/stores/indents/dropdowns';

            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    materialpicker.fromStores = materialpicker.fromStores.concat(response.data.fromStore);
                    materialpicker.toStores = materialpicker.toStores.concat(response.data.toStore);
                    materialpicker.statuses = materialpicker.statuses.concat(response.data.status);
                    $rootScope.stopLoader();
                    // GetStoreIndents();
                },
                function (err) {
                    $rootScope.stopLoader();
                });
        } 

        var MaterialRejectAcceptanceData = function () { 
            var URI = CONSTANTS.INVENTORY.STORE.REJECTED_MATERIAL_ACCEPTANCE.SEARCH_API;
            // var URI = constants.apiurl + constants.inventoryApi + '/store/reject/material/acceptance/search';
            var data = {  
         
              "fromStoreId": self.model.fromStore.id ? self.model.fromStore.id : undefined,
              "toStoreId": self.model.toStore.id ? self.model.toStore.id:undefined,
              "returnNo": self.model.returnNo? self.model.returnNo:undefined,
              "statusId": self.model.status.id? self.model.status.id:undefined, 
            };
            $rootScope.startLoader()
            GenericService.serviceAction("POST", URI, data).then(
              function (response) {
                $rootScope.stopLoader();
                for (var i = 0; i < response.data.length; i++) {
                  response.data[i]["isChecked"] = false;
                }
                self.model.searchMaterialRejectData = response.data;
              },
              function errorCallback(response) {
                $rootScope.stopLoader();
                growl.error('Error');
              });
          }
      
          var materialRejectAcceptanceDetails = function (reject) {
            commonDetailService.setDataId(reject.id);
          }
 
  
    
  
        initializeController();
    }

    function config($stateProvider) {
        $stateProvider.state("rejectedMaterialAcceptance", {
            url: '/rejectedMaterialAcceptance',
            templateUrl: 'views/PharmacyInventory/InPatient/rejected-material-acceptance.html',
            controller: "RejectedMaterialAcceptance.Controller",
            controllerAs: 'vm'
        });
    }

    angular
        .module("myApp")
        .config(config)
        .controller("RejectedMaterialAcceptance.Controller", rejectedMaterialAcceptanceController)
})(); 

 