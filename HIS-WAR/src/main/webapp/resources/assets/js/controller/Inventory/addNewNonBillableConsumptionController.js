(function () {
    "use strict";

    function addNewNonBillableConsumptionController($scope, $rootScope, $http, commonDetailService, $state, CONSTANTS, GenericService, growl) {
        var self = this;
        var batchId;
        //Default Constructor
        function initializeController() {
            $rootScope.loginpage = true;
            self.model = {
                stores: [{
                    "store": "Select Store"
                }],
                assetCategories: [{
                    "category": "Select Asset Category"
                }],
                productCategories: [{
                    "category": "Select Product Category"
                }],
                dateOptions: {
                    formatYear: 'yyyy',
                    showWeeks: false
                },
                dateOpen: [],
                SelectedItemsIds: [],
                selectAllRows: false,
                SelectedItemIds: [],
                NewItemList: [],
                itemList: []


            };

            self.model.GetItemList = GetItemList;
            self.model.GetAssetCategoryList = GetAssetCategoryList;
            self.model.GetProductCategoryList = GetProductCategoryList;
            self.model.searchNewItem = searchNewItem;
            self.model.isSelected = isSelected;
            self.model.SelectAllItems = SelectAllItems;
            self.model.SelectItem = SelectItem;
            self.model.getAllItemDetails = getAllItemDetails;
            self.model.removeRowData = removeRowData;
            self.model.SaveNonBillableConsumption = SaveNonBillableConsumption;
            self.model.NavigteToBack = NavigteToBack; 
            self.model.GetConsumptionDetails = GetConsumptionDetails;
            self.model.SaveNewConsumption = SaveNewConsumption;
            self.model.validateSave = validateSave;

            PopulateDropdown();
        }

        var PopulateDropdown = function () {
            var consumption = self.model;

            var URI = CONSTANTS.GLOBAL.USER_STORE_API;
            consumption.store = consumption.stores[0];

            consumption.stores.splice(1);

            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    consumption.stores = consumption.stores.concat(response.data);
                    $rootScope.stopLoader();
                    var consumptionId = commonDetailService.getDataId();
                    $rootScope.stopLoader();
                    if (!!consumptionId) {
                        commonDetailService.setDataId(null);
                        GetConsumptionDetails(consumptionId);
                    } else {
                        return false;
                    }
                },
                function (err) {
                    $rootScope.stopLoader();
                });
        }

        function GetItemList() {

            self.model.action = 'Open';
            var popup = angular.element('#additemlist');
            popup.modal('show');
            GetAssetCategoryList();
        }
        var GetAssetCategoryList = function () {
            var consumption = self.model;

            var URI = CONSTANTS.GLOBAL.ASSET_CATEGORY_API + 1;

            consumption.assetCategory = consumption.assetCategories[0];
            consumption.productCategory = consumption.productCategories[0];

            consumption.assetCategories.splice(1);
            consumption.productCategories.splice(1);

            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    consumption.assetCategories = consumption.assetCategories.concat(response.data);
                    $rootScope.stopLoader();
                },
                function (err) {
                    $rootScope.stopLoader();
                });
        }

        var GetProductCategoryList = function (id) {
            var consumption = self.model;

            //TODO : Add asset category to the uri
            var URI = CONSTANTS.GLOBAL.PRODUCT_CATEGORY_API + id;
            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    consumption.productCategories = consumption.productCategories.concat(response.data);
                    $rootScope.stopLoader();
                },
                function (err) {
                    $rootScope.stopLoader();
                });
        }

        function searchNewItem() {

            var URI = CONSTANTS.GLOBAL.BATCH_API;
            const data = {
                "assetTypeId": 1,
                "assetCategoryId": self.model.assetCategory.id ? self.model.assetCategory.id : undefined,
                "productCategoryId": self.model.productCategory.id ? self.model.productCategory.id : undefined,
                "storeId": self.model.store.id
            }

            self.model.NewItemList = [];
            $rootScope.startLoader();
            GenericService.serviceAction("POST", URI, data).then(
                function (response) {
                    $rootScope.stopLoader();
                    self.model.NewItemList = response.data;
                },
                function errorCallback(response) {
                    $rootScope.stopLoader();
                    growl.error('Error');
                });
        }

        var SelectAllItems = function () {
            if (self.model.selectAllRows) {
                self.model.SelectedItemsIds = self.model.NewItemList.map(function (item) {
                    item.isSelected = true;
                    self.model.isItemChecked = true;
                    return item.itemId;
                });
            } else {
                self.model.SelectedItemsIds = [];
                self.model.NewItemList.forEach(function (item) {
                    item.isSelected = false;
                    self.model.isItemChecked = false;
                });
            }
        }

        var SelectItem = function (item) {
            var itemIds = self.model.SelectedItemsIds;

            if (item.isSelected) {
                itemIds.push(item.itemId);
            } else {
                var index = itemIds.indexOf(item.itemId);
                if (index > -1) {
                    itemIds.splice(index, 1);
                }
            }

            if (self.model.NewItemList.every(isSelected)) {
                self.model.selectAllRows = true;
            } else {
                self.model.selectAllRows = false;
            }

            self.model.isItemChecked = false;
            self.model.NewItemList.forEach(function (item) {
                if (item.isSelected == true) {
                    self.model.isItemChecked = true;
                    return;
                }
            });
        }

        var isSelected = function (item) {
            return item.isSelected;
        }

        function getAllItemDetails() {
            self.model.NewItemList.forEach(function (item) {
                if (item.isSelected == true) {
                    self.model.itemList.push(item);
                }
            });

        }

        function removeRowData(item) {
            var index = self.model.itemList.indexOf(item);
            self.model.itemList.splice(index, 1);
        }

        var SaveNewConsumption = function () {
            var store = self.model;
            var URI = CONSTANTS.INVENTORY.NURSING.NON_BILLABLE_CONSUMPTION.SAVE_API;
            var data = {
                "storeId": store.store.id,
                "statusId": 1,
                "approvalStatusId": 1,
                "remark": self.model.remark,
                "batch": []
            };

            angular.forEach(store.itemList, function (item, key) {
                var storeItem = {
                    "batchId": item.batchId,
                    "uomTypeId": item.uomTypeId,
                    "uomUnitId": item.uomUnitId,
                    "consumedQuantity": item.consumedQuantity,
                    "statusId": 1
                }
                data.batch.push(storeItem);

            });
            $rootScope.startLoader();
            GenericService.serviceAction("POST", URI, data).then(
                function (response) {
                    // ClearRequests();
                    $rootScope.stopLoader();
                    NavigteToBack();
                    growl.success(response.data.message);
                },
                function (err) {
                    $rootScope.stopLoader();
                    growl.error('Something Went Wrong');
                });
        }

        var NavigteToBack = function () {
            $state.go('nonBillableConsumption');
        }

        var GetConsumptionDetails = function (consumptionId) {
            // var opbId = openingBalanceService.getOpeningBalanceId();
            var URI = CONSTANTS.INVENTORY.NURSING.NON_BILLABLE_CONSUMPTION.DETAIL_API + consumptionId;

            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    self.model.consumptionId = response.data.id;
                    self.model.remark = response.data.remark;
                    self.model.store = self.model.stores.find(function (consumption) {
                        return response.data.storeId == consumption.id;
                        //self.model.store.id = response.data.storeId;
                    });

                    self.model.itemList = [];
                    self.model.itemList = response.data.batch;

                    $rootScope.stopLoader();
                },
                function (err) {
                    $rootScope.stopLoader();
                });
        }


        var SaveNonBillableConsumption = function () {
            if (validateSave()) {
                if (!self.model.consumptionId) {
                    SaveNewConsumption();
                } else {
                    UpdateBatchChange();
                }
            }
        }
        var UpdateBatchChange = function () {

            var consumption = self.model;
            var URI = CONSTANTS.INVENTORY.NURSING.NON_BILLABLE_CONSUMPTION.UPDATE_API + consumption.consumptionId;
            var data = {
                "remark": self.model.remark,
                "batch": []
            };

            angular.forEach(self.model.itemList, function (item, key) {
                var storeItem = {
                    "id": item.id,
                    "consumedQuantity": item.consumedQuantity
                }

                if (item.id == '' || item.id == null) {
                    storeItem.batchId = item.batchId,
                        storeItem.uomTypeId = item.uomTypeId,
                        storeItem.uomUnitId = item.uomUnitId,
                        storeItem.consumedQuantity = item.consumedQuantity,
                        storeItem.statusId = 1
                }
                data.batch.push(storeItem);


            });
            GenericService.serviceAction("PATCH", URI, data).then(
                function (response) {
                    NavigteToBack();
                    $rootScope.stopLoader();
                    growl.success(response.data.message);
                },
                function (err) {
                    $rootScope.stopLoader();
                    growl.error('Something Went Wrong');
                });
        }



        var validateSave = function () {
            var isValid = true;
            var ItemDetailList = self.model.itemList;
            // !self.model.fromStore.id || !self.model.toStore.id || !self.model.priority.id || 
            if (ItemDetailList.length < 1) {
                growl.error('Error', {
                    title: "Please validate all required fields"
                });
                isValid = false;
            }
            for (let ItemDetail of ItemDetailList) {
                if (ItemDetail.consumedQuantity == null || ItemDetail.consumedQuantity > ItemDetail.availableQuantity) {
                    growl.error('Error', {
                        title: "Please Enter Consumed Quantity"
                    });
                    isValid = false;
                }
            }
            return isValid;
        }

        initializeController();
    }

    function config($stateProvider) {
        $stateProvider.state("addNewnonbillableConsumption", {
            url: "/addNewnonbillableConsumption",
            templateUrl: 'views/PharmacyInventory/InPatient/add-new-non-billable-consumption.html',
            controller: "AddNewNonBillableConsumption.Controller",
            controllerAs: "vm"
        });
    }

    angular
        .module("myApp")
        .config(config)
        .controller("AddNewNonBillableConsumption.Controller", addNewNonBillableConsumptionController);
})();
  	/*Non Billable Consumption*/
