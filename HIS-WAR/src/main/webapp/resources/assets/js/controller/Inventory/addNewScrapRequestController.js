(function () {
    "use strict";

    function addNewScrapRequestController($scope, $rootScope, $http, $state, commonDetailService, CONSTANTS, GenericService, growl) {
        var self = this; 
        //Default Constructor
        function initializeController() {
            $rootScope.loginpage = true;
            self.model = {
                stores: [{
                    "store": "All Stores"
                }],
                assetTypes: [{
                    "type": "All"
                }],
                assetCategories: [{
                    "category": "Select Asset Category"
                }],
                productCategories: [{
                    "category": "Select Product Category"
                }],
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
                SelectedItemsIds: [],
                selectAllRows: false,
                NewItemList: [],
                itemList: []

            };

            self.model.PopulateDropdown = PopulateDropdown;
            self.model.GetItemList = GetItemList;
            self.model.GetAssetCategoryList = GetAssetCategoryList;
            self.model.GetProductCategoryList = GetProductCategoryList;
            self.model.getReturnReasonList = getReturnReasonList;
            self.model.searchNewItem = searchNewItem;
            self.model.SelectAllItems = SelectAllItems;
            self.model.SelectItem = SelectItem;
            self.model.isSelected = isSelected;
            self.model.getAllItemDetails = getAllItemDetails;
            self.model.removeRowData = removeRowData;
            self.model.SaveScrapRequest = SaveScrapRequest;
            self.model.NavigteToBack = NavigteToBack;
            self.model.OpenFromDate = OpenFromDate;
            self.model.OpenToDate = OpenToDate;
            self.model.searchByNearExpiry = searchByNearExpiry;
            self.model.GetScrapRequestDetails = GetScrapRequestDetails;
            self.model.onNearExpiryChange = onNearExpiryChange;
            self.model.confirmClear = confirmClear;
            self.model.SaveNewScrapRequest = SaveNewScrapRequest;
            self.model.UpdateScrapRequest = UpdateScrapRequest;
            self.model.validateSave = validateSave;

            getReturnReasonList();
            PopulateDropdown();

        }

        var OpenFromDate = function () {
            self.model.fromDateOpened = true;
            self.model.toDateOptions.minDate = self.model.fromDate;
        }

        var OpenToDate = function () {
            self.model.toDateOpened = true;
            self.model.toDateOptions.minDate = self.model.fromDate;
        }

        function removeRowData(item) {
            var index = self.model.itemList.indexOf(item);
            self.model.itemList.splice(index, 1);
        }

        var PopulateDropdown = function () {
            var scrap = self.model;
            scrap.store = scrap.stores[0];
            scrap.assetType = scrap.assetTypes[0];
            var URI = CONSTANTS.INVENTORY.SCRAP.SCRAP_REQUEST.DROPDOWN_API;

            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    scrap.stores = scrap.stores.concat(response.data.store);
                    scrap.assetTypes = scrap.assetTypes.concat(response.data.assetType);
                    $rootScope.stopLoader();
                    var scrapId = commonDetailService.getDataId(); 
                    $rootScope.stopLoader();
                    if (!!scrapId) {
                        commonDetailService.setDataId(null);
                        GetScrapRequestDetails(scrapId);
                    } else {
                        return false;
                    }
                },
                function (err) {
                    $rootScope.stopLoader();
                });
        }

        function GetItemList() {
            if (self.model.nearExpiry == true) {
                self.model.action = 'Open';
                var popup = angular.element('#nearExpiry1');
                popup.modal('show');
            }
            else {
                self.model.action = 'Open';
                var popup = angular.element('#additemlist');
                popup.modal('show');
                GetAssetCategoryList();
            }
        }

        var GetAssetCategoryList = function () {
            var mreturn = self.model;

            var URI = CONSTANTS.GLOBAL.ASSET_CATEGORY_API + 1;

            mreturn.assetCategory = mreturn.assetCategories[0];
            mreturn.productCategory = mreturn.productCategories[0];

            // mreturn.assetCategories.splice(1);
            // mreturn.productCategories.splice(1);

            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    mreturn.assetCategories = mreturn.assetCategories.concat(response.data);
                    $rootScope.stopLoader();
                },
                function (err) {
                    $rootScope.stopLoader();
                });
        }

        var GetProductCategoryList = function (id) {
            var mreturn = self.model;

            //TODO : Add asset category to the uri
            var URI = CONSTANTS.GLOBAL.PRODUCT_CATEGORY_API + id;
            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    mreturn.productCategories = mreturn.productCategories.concat(response.data);
                    $rootScope.stopLoader();
                },
                function (err) {
                    $rootScope.stopLoader();
                });
        }

        var getReturnReasonList = function () {
            var scrap = self.model;

            var URI = CONSTANTS.INVENTORY.SCRAP.SCRAP_REQUEST.SCRAP_REASON_API;

            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    scrap.scrapReasons = response.data;
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
                "storeId": self.model.stores.id
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

        var SaveScrapRequest = function () {
            if (validateSave()) {
                if (!self.model.scrapId) {
                    SaveNewScrapRequest();
                } else {
                    UpdateScrapRequest();
                }
            }
        }

        var SaveNewScrapRequest = function () {
            var store = self.model;
            var URI = CONSTANTS.INVENTORY.SCRAP.SCRAP_REQUEST.SAVE_API;
            var data = {

                "storeId": self.model.store.id,
                "assetTypeId": self.model.assetType.id,
                "nearByExpiry": self.model.nearExpiry ? self.model.nearExpiry : false,
                "remark": self.model.remark,
                "statusId": 1,
                "approvalStatusId": 1,
                "items": []
            };
            angular.forEach(store.itemList, function (item, key) { 
                var storeItem = {
                    "batchId": item.batchId,
                    "scrapQuantity": parseInt(item.scrapQuantity),
                    "scrapReasonId": item.scrapReason.id,
                    "uomTypeId": item.uomTypeId,
                    "uomUnitId": item.uomUnitId,
                    "amountCop" : item.amountAtCop,
                    "statusId": 1
                }
                data.items.push(storeItem);

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
            $state.go('scrapRequest');
        }

        function searchByNearExpiry() {

            var URI = CONSTANTS.GLOBAL.NEAR_EXPIRY;
            const data = {
                "fromDate": moment(self.model.fromDate).format('YYYY-MM-DD'),
                "toDate": moment(self.model.toDate).format('YYYY-MM-DD'),
                "assetTypeId": 1,
                "fromStoreId": self.model.store.id,
                "isConsignment": self.model.isConsignment ? self.model.isConsignment : false
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

        var GetScrapRequestDetails = function (scrapId) {
            var URI = CONSTANTS.INVENTORY.SCRAP.SCRAP_REQUEST.DETAIL_API + scrapId;

            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    self.model.scrapId = response.data.id;
                    self.model.remark = response.data.remark;
                    self.model.itemList = [];
                    self.model.itemList = response.data.items;

                    self.model.store = self.model.stores.find(function (scrap) {
                        return response.data.storeId == scrap.id;
                    });

                    self.model.assetType = self.model.assetTypes.find(function (scrap) {
                        return response.data.assetTypeId == scrap.id;
                    });

                    angular.forEach(self.model.itemList, function (item, key) {
                        item.scrapReason = self.model.scrapReasons.find(function (scrap) {
                            return scrap.id == item.scrapReasonId;
                        });
                    });
 
                    $rootScope.stopLoader();
                },
                function (err) {
                    $rootScope.stopLoader();
                });
        }

        var onNearExpiryChange = function () {
            if (self.model.itemList.length > 0) {
                $("#confirmLogout1").modal()
            }
        }

        var confirmClear = function () {
            self.model.itemList = [];

        }


        var UpdateScrapRequest = function () {

            var scrap = self.model;

            var URI = CONSTANTS.INVENTORY.SCRAP.SCRAP_REQUEST.UPDATE_API + scrap.scrapId;
            var data = {
               "remark": self.model.remark, 
                "items": []
            };

            angular.forEach(self.model.itemList, function (item, key) {
                var storeItem = {
                    "id": item.id,
                    "scrapQuantity": parseInt(item.scrapQuantity),
                    "scrapReasonId": item.scrapReason.id,
                    // "amountCOP":  item.amountAtCop
                }

                if (item.id == '' || item.id == null) { 
                    storeItem.batchId = item.batchId,
                    storeItem.scrapQuantity =parseInt(item.scrapQuantity),
                    storeItem.scrapReasonId = 1,
                    storeItem.uomTypeId = item.uomTypeId,
                    storeItem.uomUnitId = item.uomUnitId, 
                    // storeItem.amountCOP = item.amountAtCop, 
                    storeItem.statusId = 1
                }
                data.items.push(storeItem);


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
                if (ItemDetail.scrapQuantity == null  || !ItemDetail.scrapReasonId == '') {
                    growl.error('Error', {
                        title: "Please Enter Valid Details"
                    });
                    isValid = false;
                }
            }
           
            return isValid;
        }
 
        initializeController();
    }

    function config($stateProvider) {
        $stateProvider.state("addItemScrapApproval", {
            url: "/addItemScrapApproval",
            templateUrl: 'views/PharmacyInventory/Store/scrap-approval-add-new-item.html',
            controller: "AddNewScrapRequest.Controller",
            controllerAs: "vm"
        });
    }

    angular
        .module("myApp")
        .config(config)
        .controller("AddNewScrapRequest.Controller", addNewScrapRequestController);
})();

