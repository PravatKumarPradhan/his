(function () {
    "use strict";

    function addNewBatchExpDateChangeController($scope, $rootScope, $http, commonDetailService, $state, CONSTANTS, GenericService, growl) {
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
            self.model.SelectAllItems = SelectAllItems;
            self.model.SelectItem = SelectItem;
            self.model.isSelected = isSelected;
            self.model.getAllItemDetails = getAllItemDetails;
            self.model.removeRowData = removeRowData;
            self.model.OpenExpiryDate = OpenExpiryDate;
            self.model.SaveBatchChange = SaveBatchChange;
            self.model.SaveNewBatchRequest = SaveNewBatchRequest;
            self.model.UpdateBatchChange = UpdateBatchChange;
            self.model.NavigteToBatchChange = NavigteToBatchChange;
            self.model.setSelectedDate = setSelectedDate;
            self.model.GetBatchDetails = GetBatchDetails;
            self.model.validateSave = validateSave;
            PopulateDropdown();

        }

        var PopulateDropdown = function () {
            var batch = self.model;

            var URI = CONSTANTS.GLOBAL.USER_STORE_API;
            batch.store = batch.stores[0];

            batch.stores.splice(1);

            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    batch.stores = batch.stores.concat(response.data);
                    $rootScope.stopLoader();
                    var batchId = commonDetailService.getDataId();
                    $rootScope.stopLoader();
                    if (!!batchId) {
                        commonDetailService.setDataId(null);
                        GetBatchDetails(batchId);
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
            var batch = self.model;

            var URI = CONSTANTS.GLOBAL.ASSET_CATEGORY_API + 1;

            batch.assetCategory = batch.assetCategories[0];
            batch.productCategory = batch.productCategories[0];

            // batch.assetCategories.splice(1);
            // batch.productCategories.splice(1);

            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    batch.assetCategories = batch.assetCategories.concat(response.data);
                    $rootScope.stopLoader();
                },
                function (err) {
                    $rootScope.stopLoader();
                });
        }

        var GetProductCategoryList = function (id) {
            var batch = self.model;

            //TODO : Add asset category to the uri
            var URI = CONSTANTS.GLOBAL.PRODUCT_CATEGORY_API + id;
            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    batch.productCategories = batch.productCategories.concat(response.data);
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

        var OpenExpiryDate = function (idx) {
            self.model.dateOpen[idx] = true;
        }

        var GetBatchDetails = function (batchId) {
            // var opbId = openingBalanceService.getOpeningBalanceId();

            var URI = CONSTANTS.INVENTORY.STORE.BATCH_CHANGE.DETAILS_API + batchId;

            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    self.model.batchId = response.data.id;

                    self.model.store = self.model.stores.find(function (batch) {
                        return response.data.storeId == batch.id;
                        //self.model.store.id = response.data.storeId;
                    });

                    self.model.itemList = [];
                    self.model.itemList = response.data.batch;
                    angular.forEach(self.model.itemList, function (itemChange, key) {
                        itemChange.newExpiry = new Date(itemChange.newBatchExpiry);
                        itemChange.batchNo = itemChange.oldBatchNo;
                        itemChange.expiry = new Date(itemChange.oldBatchExpiry);
                        itemChange.mrp = itemChange.oldMrp;
                    });

                    $rootScope.stopLoader();
                },
                function (err) {
                    $rootScope.stopLoader();
                });
        }



        var SaveNewBatchRequest = function () {
            var store = self.model;
            var URI = CONSTANTS.INVENTORY.STORE.BATCH_CHANGE.SAVE_API;

            var data = {
                "storeId": store.store.id,
                "statusId": 1,
                "approvalStatusId": 1,
                "batch": []
            };

            angular.forEach(store.itemList, function (item, key) {
                var storeItem = {
                    "batchId": item.batchId,
                    "oldBatchNo": item.batchNo,
                    "oldBatchExpiry": item.expiry,
                    "oldMrp": item.mrp,
                    "newBatchNo": item.newBatchNo,
                    "newBatchExpiry": item.newExpiry,
                    "newMrp": item.newMrp,
                    "statusId": 1
                }
                data.batch.push(storeItem);

            });
            $rootScope.startLoader();
            GenericService.serviceAction("POST", URI, data).then(
                function (response) {
                    // ClearRequests();
                    $rootScope.stopLoader();
                    NavigteToBatchChange();
                    growl.success(response.data.message);
                },
                function (err) {
                    $rootScope.stopLoader();
                    growl.error('Something Went Wrong');
                });
        }

        var NavigteToBatchChange = function () {
            $state.go('batchExpDateChange');
        }

        var setSelectedDate = function (item) {
            var expDateYear = item.newExpiry.getFullYear();
            var expDateMonth = item.newExpiry.getMonth() + 1;
            var expDateDay = new Date(expDateYear, expDateMonth, 0).getDate();
            item.newBatchExpiry = (expDateYear + "-" + expDateMonth + "-" + expDateDay);
        }

        var SaveBatchChange = function () {
            if (validateSave()) {
                if (!self.model.batchId) {
                    SaveNewBatchRequest();
                } else {
                    UpdateBatchChange();
                }
            }
        }
        var UpdateBatchChange = function () {

            var batch = self.model;

            var URI = CONSTANTS.INVENTORY.STORE.BATCH_CHANGE.UPDATE_API + batch.batchId;
            var data = {

                "batch": []
            };

            angular.forEach(batch.itemList, function (item, key) {
                var storeItem = {
                    "id": item.id,
                    "newBatchNo": item.newBatchNo,
                    "newBatchExpiry": item.newBatchExpiry,
                    "newMrp": item.newMrp,
                }

                if (item.id == '' || item.id == null) {
                    storeItem.batchId = item.batchId,
                        storeItem.oldBatchNo = item.batchNo,
                        storeItem.oldBatchExpiry = item.expiry,
                        storeItem.oldMrp = item.mrp,
                        storeItem.statusId = 1
                }
                data.batch.push(storeItem);


            });
            GenericService.serviceAction("PATCH", URI, data).then(
                function (response) {
                    NavigteToBatchChange();
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
                if (ItemDetail.newBatchNo == null || ItemDetail.newMrp == null) {
                    growl.error('Error', {
                        title: "Please Enter Valid Details"
                    });
                    isValid = false;
                }
            }
            var expMonth = new Date().getMonth() + 1;
            for (let ItemDetail of ItemDetailList) {
                var newExpiry = ItemDetail.newExpiry;
                if (!newExpiry || (new Date(newExpiry).getMonth() + 1) <= expMonth) {
                    growl.error('Error', {
                        title: "Please Valid Expiry Date"
                    });
                    isValid = false;
                }
            }
            return isValid;
        }

        initializeController();
    }

    function config($stateProvider) {
        $stateProvider.state("additemBatchChange", {
            url: "/additemBatchChange",
            templateUrl: 'views/PharmacyInventory/Store/add-item-batch-exp-date-change.html',
            controller: "AddItemBatchChange.Controller",
            controllerAs: "vm"
        });
    }

    angular
        .module("myApp")
        .config(config)
        .controller("AddItemBatchChange.Controller", addNewBatchExpDateChangeController);
})();