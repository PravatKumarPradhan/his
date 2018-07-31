(function () {
    "use strict";

    function pendingForShelfAllocationController($scope, $rootScope, $http, CONSTANTS, GenericService, growl) {
        var self = this;
        var rackId;
        var shelfId;
        var ItemId;
        //Default Constructor
        function initializeController() {
            $rootScope.loginpage = true;
            self.model = {
                stores: [{
                    "store": "Select Store"
                }],
                racks: [{
                    "rack": "Select Rack"
                }],
                assetCategories: [{
                    "category": "Select Asset Category"
                }],
                productCategories: [{
                    "category": "Select Product Category"
                }],
                itemList: [],
                allocationData: []

            };
            self.model.PopulateStoreDropdown = PopulateStoreDropdown;
            self.model.GetAssetCategoryList = GetAssetCategoryList;
            self.model.GetProductCategoryList = GetProductCategoryList;
            self.model.ActiveTab = 1;
            self.model.PendingShelfAllocationSearch = PendingShelfAllocationSearch;
            self.model.UpdateStatus = UpdateStatus;
            self.model.PopulateRackDropdown = PopulateRackDropdown;
            self.model.PopulateShelfDropdown = PopulateShelfDropdown;
            self.model.allocatePopup = allocatePopup;
            self.model.addButtonClick = addButtonClick;
            self.model.onViewClick = onViewClick;
            self.model.activeIndex = null;
            self.model.saveNewPendingShelf = saveNewPendingShelf;
            self.model.clearFields = clearFields;
            self.model.removeRowData =removeRowData;

            PopulateStoreDropdown();
            GetAssetCategoryList();
        }
        var PopulateStoreDropdown = function () {
            var allocation = self.model;

            var URI = CONSTANTS.GLOBAL.USER_STORE_API;
            allocation.store = allocation.stores[0];

            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    allocation.stores = allocation.stores.concat(response.data);
                    $rootScope.stopLoader();
                },
                function (err) {
                    $rootScope.stopLoader();
                });
        }

        var PopulateRackDropdown = function (item) {
            var allocation = self.model;

            var URI = CONSTANTS.GLOBAL.RACK_API + item.storeId;
            allocation.rack = allocation.racks[0];

            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    allocation.racks = allocation.racks.concat(response.data);
                    $rootScope.stopLoader();
                },
                function (err) {
                    $rootScope.stopLoader();
                });
        }

        var PopulateShelfDropdown = function () {
            var allocation = self.model;

            var URI = CONSTANTS.GLOBAL.SHELF_API + self.model.rack.id;

            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    allocation.shelfs = response.data;
                    $rootScope.stopLoader();
                },
                function (err) {
                    $rootScope.stopLoader();
                });
        }

        var GetAssetCategoryList = function () {
            var allocation = self.model;

            var URI = CONSTANTS.GLOBAL.ASSET_CATEGORY_API + 1;

            allocation.assetCategory = allocation.assetCategories[0];
            allocation.productCategory = allocation.productCategories[0];

            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    allocation.assetCategories = allocation.assetCategories.concat(response.data);
                    $rootScope.stopLoader();
                },
                function (err) {
                    $rootScope.stopLoader();
                });
        }

        var GetProductCategoryList = function (id) {
            var allocation = self.model;
            var URI = CONSTANTS.GLOBAL.PRODUCT_CATEGORY_API + id;
            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    allocation.productCategories = allocation.productCategories.concat(response.data);
                    $rootScope.stopLoader();
                },
                function (err) {
                    $rootScope.stopLoader();
                });
        }

        var PendingShelfAllocationSearch = function () {
            var URI = CONSTANTS.INVENTORY.STORE.PENDING_SHELF_ALLOCATION.SEARCH_API;
            var data = {
                "allocated": (self.model.ActiveTab == 1 ? true : false),
                "storeId": self.model.store.id,
                "batchNo": self.model.batchNo,
                "itemCode": self.model.itemCode,
                "itemName": self.model.itemName
            };
            $rootScope.startLoader()
            GenericService.serviceAction("POST", URI, data).then(
                function (response) {
                    $rootScope.stopLoader();

                    if (self.model.ActiveTab == 1) {
                        self.model.allocationData = response.data;
                    }
                    else {
                        self.model.pendingShelfAllocationData = response.data;
                    }
                },
                function errorCallback(response) {
                    $rootScope.stopLoader();
                    growl.error('Error');
                });
        }

        var UpdateStatus = function (item) {
            var URI = CONSTANTS.INVENTORY.STORE.PENDING_SHELF_ALLOCATION.STATUS_UPDATE_API + item.id;
            var data = {
                "status": item.status
            };

            $rootScope.startLoader();
            GenericService.serviceAction("PATCH", URI, data).then(
                function (response) {
                    $rootScope.stopLoader();
                    growl.success(response.data.message);
                },
                function (err) {
                    $rootScope.stopLoader();
                    growl.error('Something Went Wrong');
                });
        }

        var allocatePopup = function (item) {
            $("#editPendingAllocatedItemModal").modal()
            PopulateRackDropdown(item);
            self.model.storeId = item.storeId;
            self.model.batchId = item.batchId;
            self.model.ItemId = item.id;
            // Item = item;
            self.model.edit = false;
            if (!!item.id) {
                self.model.edit = true;
                var data = {
                    "rack": item.rack,
                    "shelf": item.shelf,
                    "rackId": item.rackId,
                    "shelfId": item.shelfId,
                    "id": item.id

                }
                self.model.itemList.splice(0);
                self.model.itemList.push(data);
            }
        }
        var addButtonClick = function () {
            var data = {
                "rack": self.model.rack.rack,
                "shelf": self.model.shelf.shelf,
                "rackId": self.model.rack.id,
                "shelfId": self.model.shelf.id

            }
            self.model.itemList.push(data);
        }

        function onViewClick(index, item) {


            var URI = CONSTANTS.INVENTORY.STORE.PENDING_SHELF_ALLOCATION.ALLOCATED_ITEMS + self.model.storeId + "/" + item.rackId + "/ " + item.shelfId;

            var data = {};
            GenericService.serviceAction("GET", URI, data).then(
                function (response) {

                    self.model.itemDetailList = response.data;
                });
            self.model.activeIndex = (self.model.activeIndex == index ? null : index);
        }

        var saveNewPendingShelf = function () {
            if (!self.model.edit) {
                saveNewShelf();
            }
            else {
                updateShelf();
            }

        }

        var saveNewShelf = function () {

            var URI = CONSTANTS.INVENTORY.STORE.PENDING_SHELF_ALLOCATION.ALLOCATED_ITEMS;

            var data = {
                "storeId": self.model.storeId,
                "batchId": self.model.batchId,
                "shelf": []
            };

            angular.forEach( function () {
                var storeItem = {
                    "rackId": self.model.rack.id,
                    "shelfId": self.model.shelf.id
                }

                data.shelf.push(storeItem);
            });

            $rootScope.startLoader();
            GenericService.serviceAction("POST", URI, data).then(
                function (response) {
                    clearFields();
                    $rootScope.stopLoader();
                    growl.success(response.data.message);
                },
                function (err) {
                    $rootScope.stopLoader();
                    growl.error('Something Went Wrong');
                });
        }

        var updateShelf = function () {

            var URI = CONSTANTS.INVENTORY.STORE.PENDING_SHELF_ALLOCATION.UPDATE_API + self.model.ItemId;
            var data = {
                "storeId": 1,
                "shelf": []
            };
            angular.forEach(self.model.itemList, function (item) {
            var storeItem = {
                "id": item.id,
                "rackId": item.rackId,
                "shelfId": item.shelfId,
            }
            if (self.model.edit == true) {
                storeItem.rackId = self.model.rack.id,
                storeItem.shelfId = self.model.shelf.id

            }
            data.shelf.push(storeItem);
        });
            GenericService.serviceAction("PATCH", URI, data).then(
                function (response) {
                    clearFields();
                    $rootScope.stopLoader();
                    growl.success(response.data.message);
                },
                function (err) {
                    $rootScope.stopLoader();
                    growl.error('Something Went Wrong');
                });
        }

        var clearFields = function () {
            self.model.rack = [];
            self.model.shelf = [];
            self.model.itemList = [];
            self.model.itemDetailList = [];
        }
        function removeRowData(item) {
            var index = self.model.itemList.indexOf(item);
            self.model.itemList.splice(index, 1);
        }

        initializeController();
    }

    function config($stateProvider) {
        $stateProvider.state("pendingForShelfAllocation", {
            url: "/pendingForShelfAllocation",
            templateUrl: 'views/PharmacyInventory/Store/pending-for-shelf-allocation.html',
            controller: "PendingForShelfAllocation.Controller",
            controllerAs: "vm"
        });
    }

    angular
        .module("myApp")
        .config(config)
        .controller("PendingForShelfAllocation.Controller", pendingForShelfAllocationController);
})();

