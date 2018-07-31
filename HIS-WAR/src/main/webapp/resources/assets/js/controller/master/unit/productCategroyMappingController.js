(function () {
    "use strict";

    function productCategroyMappingController($scope, $rootScope, $http, CONSTANTS, GenericService, $filter, $state, growl) {
        var self = this;

        //Default Constructor
        function initializeController() {
            $rootScope.loginpage = true;
            self.model = {
                Stores: [{
                    "store": "Select Store"
                }],
            };
            PopulateStore();
            GetCategoryMappingList();
        }

        //Method to Populate Store Dropdown
        var PopulateStore = function () {
            var categoryMapping = self.model;

            var URI = CONSTANTS.GLOBAL.USER_STORE_API;;

            categoryMapping.store = categoryMapping.Stores[0];

            categoryMapping.Stores.splice(1);

            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    categoryMapping.Stores = categoryMapping.Stores.concat(response.data);
                    $rootScope.stopLoader();
                },
                function errorCallback(response) {
                    $rootScope.stopLoader();
                });
        }

        var GetCategoryMappingList = function (store) {
            if (store == undefined) {
                store.id = 0;
            }
            var categoryMapping = self.model;
            var URI = CONSTANTS.MASTER.UNIT.PHARAMACY.PRODUCT_CATEGORY_MAPPING.PRODUCT_CATEGORY_MAPPING_API + store.id;
            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    self.model.categoryMappingList = response.data;
                    $rootScope.stopLoader();
                },
                function errorCallback(response) {
                    $rootScope.stopLoader();
                });
        }

        initializeController();
    }

    function config($stateProvider) {
        $stateProvider.state("productRestriction.productCategoryMapping", {
            url: "/productCategoryMapping",
            templateUrl: 'views/PharmacyInventory/StockAdjustment/product-category-mapping.html',
            controller: "ProductCategoryMapping.Controller",
            controllerAs: "vm"
        });
    }

    angular
        .module("myApp")
        .config(config)
        .controller("ProductCategoryMapping.Controller", productCategroyMappingController)
})();