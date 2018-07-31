(function () {
    "use strict";

    function addNewMaterialReturnController($scope, $rootScope, $http, $state, commonDetailService, CONSTANTS, GenericService, growl) {
        var self = this;

        //Default Constructor
        function initializeController() {
            $rootScope.loginpage = true;
            self.model = {
                fromStores: [{
                    "store": "Select Stores"
                }],
                toStores: [{
                    "store": "Select Stores"
                }],
                assetCategories: [{
                    "category": "Select Asset Category"
                }],
                carriers: [{
                    "name": "Select Carrier "
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
            self.model.GetAssetCategoryList = GetAssetCategoryList;
            self.model.GetProductCategoryList = GetProductCategoryList;
            self.model.searchNewItem = searchNewItem;
            self.model.searchByNearExpiry = searchByNearExpiry;
            self.model.SelectAllItems = SelectAllItems;
            self.model.SelectItem = SelectItem;
            self.model.getAllItemDetails = getAllItemDetails;;
            self.model.isSelected = isSelected;
            self.model.OpenFromDate = OpenFromDate;
            self.model.OpenToDate = OpenToDate;
            self.model.GetItemList = GetItemList;
            self.model.SaveMaterialReturn = SaveMaterialReturn;
            self.model.NavigteToBack = NavigteToBack;
            self.model.getReturnReasonList = getReturnReasonList;
            self.model.GetMaterialReturnDetails = GetMaterialReturnDetails;
            self.model.removeRowData = removeRowData;
            self.model.onNearExpiryChange = onNearExpiryChange;
            self.model.confirmClear = confirmClear;
            self.model.SaveNewMaterialReturn = SaveNewMaterialReturn;
            self.model.UpdateMaterialReturn = UpdateMaterialReturn;
            self.model.validateSave = validateSave;


            getDropdownsList();
            getReturnReasonList();
        }
        var OpenFromDate = function () {
            self.model.fromDateOpened = true;
            self.model.toDateOptions.minDate = self.model.fromDate;
        }

        var OpenToDate = function () {
            self.model.toDateOpened = true;
            self.model.toDateOptions.minDate = self.model.fromDate;
        }

        var getDropdownsList = function () {
            var mreturn = self.model;
            mreturn.fromStore = mreturn.fromStores[0];
            mreturn.toStore = mreturn.toStores[0];
            mreturn.carrier = mreturn.carriers[0];

            var URI = CONSTANTS.INVENTORY.STORE.MATERIAL_RETURN.ADD_NEW_DROPDOWN_API;

            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    mreturn.fromStores = mreturn.fromStores.concat(response.data.fromStore);
                    mreturn.toStores = mreturn.toStores.concat(response.data.toStore);
                    mreturn.carriers = mreturn.carriers.concat(response.data.carrier);
                    $rootScope.stopLoader();
                    var mreturnId = commonDetailService.getDataId();
                    $rootScope.stopLoader();
                    if (!!mreturnId) {
                        commonDetailService.setDataId(null);
                        GetMaterialReturnDetails(mreturnId);
                    } else {
                        return false;
                    }
                },
                function (err) {
                    $rootScope.stopLoader();
                });
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
            var mreturn = self.model;

            var URI = CONSTANTS.INVENTORY.STORE.MATERIAL_RETURN.RETURN_REASON_API;

            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    mreturn.returnReasons = response.data.returnReason;
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
                "storeId": self.model.fromStore.id
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

        function searchByNearExpiry() {

            var URI = CONSTANTS.GLOBAL.NEAR_EXPIRY;
            const data = {
                "fromDate": moment(self.model.fromDate).format('YYYY-MM-DD'),
                "toDate": moment(self.model.toDate).format('YYYY-MM-DD'),
                "assetTypeId": 1,
                "fromStoreId": self.model.fromStore.id,
                "toStoreId": self.model.toStore.id,
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

        var SaveNewMaterialReturn = function () {
            var store = self.model;
            var URI = CONSTANTS.INVENTORY.STORE.MATERIAL_RETURN.SAVE_API;

            var data = {
                "fromStoreId": self.model.fromStore.id,
                "toStoreId": self.model.toStore.id,
                "isConsignment": self.model.isConsignment ? self.model.isConsignment : false,
                "carrierId": self.model.carrier.id,
                "statusId": 1,
                "remark": self.model.remark,
                "approvalStatusId": 1,
                "nearByExpiry": self.model.nearExpiry,
                "items": []
            };

            angular.forEach(store.itemList, function (item, key) {
                var storeItem = {
                    "batchId": item.batchId,
                    "returnQuantity": parseInt(item.returnQuantity),
                    "returnReasonId": item.returnReason.id,
                    "uomTypeId": item.uomTypeId,
                    "uomUnitId": item.uomUnitId,
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
            $state.go('materialReturn');
        }

        var onNearExpiryChange = function () {
            if (self.model.itemList.length > 0) {
                $("#confirmLogout1").modal()
            }
        }
        var confirmClear = function () {
            self.model.itemList = [];

        }
        var GetMaterialReturnDetails = function (mreturnId) {
            var URI = CONSTANTS.INVENTORY.STORE.MATERIAL_RETURN.DETAILS_API + mreturnId;

            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    self.model.mreturnId = response.data.id;
                    self.model.remark = response.data.remark;
                    self.model.itemList = [];
                    self.model.itemList = response.data.items;

                    self.model.fromStore = self.model.fromStores.find(function (mreturn) {
                        return response.data.fromStoreId == mreturn.id;
                    });
                    self.model.toStore = self.model.toStores.find(function (mreturn) {
                        return response.data.toStoreId == mreturn.id;
                    });

                    self.model.carrier = self.model.carriers.find(function (mreturn) {
                        return response.data.carrierId == mreturn.id;
                    });

                    angular.forEach(self.model.itemList, function (item, key) {
                        item.returnReason = self.model.returnReasons.find(function (mreturn) {
                            return mreturn.id == item.returnReasonId;
                        });
                    });

                    // self.model.returnReason = self.model.returnReasons.find(function (mreturn) {

                    //     // return response.data.items.returnReasonId == mreturn.id;
                    //     response.data.items.find(function (item) {
                    //         return item.returnReasonId == mreturn.id
                    //     });
                    // });



                    $rootScope.stopLoader();
                },
                function (err) {
                    $rootScope.stopLoader();
                });
        }


        function removeRowData(item) {
            var index = self.model.itemList.indexOf(item);
            self.model.itemList.splice(index, 1);
        }
        var SaveMaterialReturn = function () {
            // if (validateSave()) {
                if (!self.model.mreturnId) {
                    SaveNewMaterialReturn();
                } else {
                    UpdateMaterialReturn();
                }
            // }
        }
        var UpdateMaterialReturn = function () {

            var mreturn = self.model;

            var URI = CONSTANTS.INVENTORY.STORE.MATERIAL_RETURN.UPDATE_API + mreturn.mreturnId;
            var data = {
                "carrierId": self.model.carrier.id,
                "remark": self.model.remark,
                "items": []
            };

            angular.forEach(self.model.itemList, function (item, key) {
                var storeItem = {
                    "id": item.itemId,
                    "returnQuantity": parseInt(item.returnQuantity),
                    "returnReasonId": item.returnReason.id,
                }

                if (item.id == '' || item.id == null) {
                    storeItem.batchId = item.batchId,
                        storeItem.returnQuantity = item.returnQuantity,
                        storeItem.returnReasonId = item.returnReasonId,
                        storeItem.uomTypeId = item.uomTypeId,
                        storeItem.uomUnitId = item.uomUnitId,
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
                if (ItemDetail.returnQuantity < 0 || ItemDetail.returnReasonId == '') {
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
        $stateProvider.state("addNewMaterialReturn", {
            url: "/addNewMaterialReturn",
            templateUrl: 'views/PharmacyInventory/InPatient/add-new-material-return.html',
            controller: "AddNewMaterialReturn.Controller",
            controllerAs: "vm"
        });
    }

    angular
        .module("myApp")
        .config(config)
        .controller("AddNewMaterialReturn.Controller", addNewMaterialReturnController);
})(); 