(function () {
    "use strict";

    function returnMaterialAcceptanceController($scope, $rootScope, $http, $state, CONSTANTS, GenericService, commonDetailService, $filter, growl) {
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
                }],
                indentTypes: [{
                    "type": "All"
                }],

            };

            self.model.MaterialReturnAcceptanceSearch = MaterialReturnAcceptanceSearch;
            self.model.materialReturnAcceptanceDetails = materialReturnAcceptanceDetails;
            self.model.activeItem = '';
            // self.model.AddItemListData = AddItemListData;
            getDropdownsList();
            MaterialReturnAcceptanceSearch();
        }

        var getDropdownsList = function () {
            var materialreturn = self.model;
            materialreturn.fromStore = materialreturn.fromStores[0];
            materialreturn.toStore = materialreturn.toStores[0];
            materialreturn.status = materialreturn.statuses[0];
            materialreturn.indentType = materialreturn.indentTypes[0];

            var URI = CONSTANTS.INVENTORY.STORE.MATERIAL_RETURN_ACCEPTANCE.DROPDOWN_API;
            // var URI = constants.apiurl + constants.inventoryApi + '/stores/indents/dropdowns';

            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    materialreturn.fromStores = materialreturn.fromStores.concat(response.data.fromStore);
                    materialreturn.toStores = materialreturn.toStores.concat(response.data.toStore);
                    materialreturn.statuses = materialreturn.statuses.concat(response.data.status);
                    materialreturn.indentTypes = materialreturn.indentTypes.concat(response.data.indentType);
                    $rootScope.stopLoader();
                    // GetStoreIndents();
                },
                function (err) {
                    $rootScope.stopLoader();
                });
        }

        var MaterialReturnAcceptanceSearch = function () {
            var URI = CONSTANTS.INVENTORY.STORE.MATERIAL_RETURN_ACCEPTANCE.SEARCH_API;
            // var URI = constants.apiurl + constants.inventoryApi + '/store/return/material/acceptance/search';
            var data = {
                "fromStoreId": self.model.fromStore.id ? self.model.fromStore.id : undefined,
                "toStoreId": self.model.toStore.id ? self.model.toStore.id : undefined,
                "returnNo": self.model.returnNo ? self.model.returnNo : undefined,
                "statusId": self.model.status.id ? self.model.status.id : undefined,
                "indentTypeId": self.model.indentType.id ? self.model.indentType.id : undefined,
            };
            $rootScope.startLoader()
            GenericService.serviceAction("POST", URI, data).then(
                function (response) {
                    $rootScope.stopLoader(); 
                    self.model.materialReturnData = response.data;
                },
                function errorCallback(response) {
                    $rootScope.stopLoader();
                    growl.error('Error');
                });
        }

        var materialReturnAcceptanceDetails = function (mreturn) {
            commonDetailService.setDataId(mreturn.id);
        }


        initializeController();
    }

    function config($stateProvider) {
        $stateProvider.state("materialReturnAcceptance", {
            url: '/materialReturnAcceptance',
            templateUrl: 'views/PharmacyInventory/InPatient/material-return-acceptance.html',
            controller: "ReturnMaterialAcceptance.Controller",
            controllerAs: 'vm'
        });
    }

    angular
        .module("myApp")
        .config(config)
        .controller("ReturnMaterialAcceptance.Controller", returnMaterialAcceptanceController)
})(); 