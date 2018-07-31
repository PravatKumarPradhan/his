(function () {
    "use strict";

    function addNewReturnableGatepassController($scope, $filter, $rootScope, commonDetailService, CurrencyService, $http, $state, CONSTANTS, GenericService, growl) {
        var self = this;

        //Default Constructor
        function initializeController() {
            $rootScope.loginpage = true;
            self.model = {
                stores: [{

                    "store": "All Stores"
                }],
                assetTypeId: 2,
                // againstIds: [
                //     {
                //         "id": 0,
                //         "against": "Select GRN"
                //     },
                //     {
                //         "id": 5,
                //         "against": "Against GRN"
                //     },
                //     {
                //         "id": 5,
                //         "against": "Against Non-Moving"
                //     },
                //     {
                //         "id": 5,
                //         "against": "Against Expiry"
                //     },
                // ],
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
                SelectedItemIds: [],
                NewItemList: [],
                itemList: []

            };
            CurrencyService.GetCurrency().then(function (currency) {
                self.model.Currency = currency;
            })
            self.model.PopulateDropdown = PopulateDropdown;
            self.model.SearchVendorNames = SearchVendorNames;
            self.model.nameNotFound = nameNotFound;
            self.model.AddVendorName = AddVendorName;
            self.model.search = '';
            self.model.prevSearch = '';
            self.model.vendorNames = [];
            self.model.againstId;
            self.model.addItemModal = addItemModal;
            self.model.GetAssetCategoryList = GetAssetCategoryList;
            self.model.GetProductCategoryList = GetProductCategoryList;
            self.model.searchNewItem = searchNewItem;
            self.model.SelectAllItems = SelectAllItems;
            self.model.SelectItem = SelectItem;
            self.model.isSelected = isSelected;
            self.model.getAllItemDetails = getAllItemDetails;
            self.model.OpenFromDate = OpenFromDate;
            self.model.OpenToDate = OpenToDate;
            self.model.searchByNearExpiry = searchByNearExpiry;
            self.model.validateSave = validateSave;
            self.model.SaveReturnableGatepass = SaveReturnableGatepass;
            self.model.SaveNewGatepass = SaveNewGatepass;
            self.model.removeRowData = removeRowData;
            self.model.searchNewGRN = searchNewGRN;
            self.model.GetReturnableGatepassDetails = GetReturnableGatepassDetails;
            self.model.SelectAllGRNItems = SelectAllGRNItems;
            self.model.SelectGRNItem = SelectGRNItem;
            self.model.getAllGRNItemDetails = getAllGRNItemDetails;
            self.model.UpdateGatepass = UpdateGatepass;


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
            var returnable = self.model;
            returnable.store = returnable.stores[0];

            var URI = CONSTANTS.INVENTORY.STORE.RETURNABLE_GATEPASS.DROPDOWN_API;

            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    returnable.stores = returnable.stores.concat(response.data.store);

                    $rootScope.stopLoader();
                    var returnableId = commonDetailService.getDataId();
                    $rootScope.stopLoader();
                    if (!!returnableId) {
                        commonDetailService.setDataId(null);
                        GetReturnableGatepassDetails(returnableId);
                    } else {
                        return false;
                    }
                },
                function (err) {
                    $rootScope.stopLoader();
                });
        }

        var GetReturnableGatepassDetails = function (returnableId) {
            var URI = CONSTANTS.INVENTORY.STORE.RETURNABLE_GATEPASS.DETAIL_API + returnableId;

            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    self.model.returnableId = response.data.id;
                    self.model.remark = response.data.remark;
                    self.model.store = self.model.stores.find(function (returnable) {
                        return response.data.storeId == returnable.id;
                    });
                    self.model.againstId = response.data.againstId.toString();
                    self.model.assetTypeId = response.data.assetTypeId;
                    self.model.referenceNo = response.data.referenceNo;
                    self.model.totalAmount = response.data.totalAmount;
                    self.model.totalTaxAmount = response.data.totalTaxAmount;
                    self.model.netAmount = response.data.netAmount;
                    self.model.vendorName = response.data.vendor;  
                    self.model.vendorId = response.data.vendorId;
                    
                    self.model.itemList = [];
                    self.model.itemList = response.data.items;

                    $rootScope.stopLoader();
                },
                function (err) {
                    $rootScope.stopLoader();
                });
        }


        function SearchVendorNames(search) {
            if (search.length < 3) return;

            if ((!self.model.prevSearch || self.model.prevSearch != search) && (search.length == 3)) {
                self.model.prevSearch = search;
                var URI = CONSTANTS.GLOBAL.VENDOR_SEARCH_API + search;
                $rootScope.startLoader();
                return GenericService.serviceAction("GET", URI, {})
                    .then(function (response) {
                        if (!!response.data && response.data.length > 0) {
                            self.model.vendorNames = response.data;
                            $rootScope.stopLoader();
                            return $filter('filter')(self.model.vendorNames, {
                                $: search
                            });
                        } else {
                            $rootScope.stopLoader();
                            return nameNotFound(search);
                        }
                    }, function (err) {
                        $rootScope.stopLoader();
                        return nameNotFound(search);
                    });
            } else {
                if (!!self.model.vendorNames && self.model.vendorNames.length > 0 && self.model.vendorNames[0].itemFound != undefined && !self.model.vendorNames[0].itemFound) {
                    return nameNotFound(search);
                } else {
                    return $filter('filter')(self.model.vendorNames, {
                        $: search
                    });
                }
            }
        }

        function nameNotFound(search) {
            var item = {
                "itemFound": false,
                "detail": "Vendor Not found with name " + search
            };
            self.model.vendorNames = [item];
            return self.model.vendorNames;
        }

        function AddVendorName(vendor) {
            self.model.vendorName = vendor.vendorName;
            self.model.vendorId = vendor.id;
        }

        var addItemModal = function () {
            if (self.model.againstId == 5) {
                $("#getGRNModal").modal()
            }
            else if (self.model.againstId == 6) {
                $("#additemlist").modal()
                GetAssetCategoryList();
            }
            else if (self.model.againstId == 7) {
                $("#addItemExpiryWise").modal()
            }
        }

        var GetAssetCategoryList = function () {
            var returnable = self.model;

            var URI = CONSTANTS.GLOBAL.ASSET_CATEGORY_API + self.model.assetTypeId;

            returnable.assetCategory = returnable.assetCategories[0];
            returnable.productCategory = returnable.productCategories[0];

            returnable.assetCategories.splice(1);
            returnable.productCategories.splice(1);

            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    returnable.assetCategories = returnable.assetCategories.concat(response.data);
                    $rootScope.stopLoader();
                },
                function (err) {
                    $rootScope.stopLoader();
                });
        }

        var GetProductCategoryList = function (id) {
            var returnable = self.model;

            //TODO : Add asset category to the uri
            var URI = CONSTANTS.GLOBAL.PRODUCT_CATEGORY_API + id;
            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    returnable.productCategories = returnable.productCategories.concat(response.data);
                    $rootScope.stopLoader();
                },
                function (err) {
                    $rootScope.stopLoader();
                });
        }

        function searchNewItem() {

            var URI = CONSTANTS.GLOBAL.BATCH_API;
            const data = {
                "assetTypeId": self.model.assetTypeId,
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

        var SelectAllGRNItems = function (newItem) {

            if (self.model.selectAllRows) {
                self.model.SelectedItemsIds = newItem.items.map(function (item) {
                    item.isSelected = true;
                    self.model.isItemChecked = true;
                    return item.itemId;
                });
            } else {
                self.model.SelectedItemsIds = [];
                newItem.items.forEach(function (item) {
                    item.isSelected = false;
                    self.model.isItemChecked = false;
                });
            }
            self.model.itemnew = newItem.items;
        }

        var SelectGRNItem = function (item, newItem) {
            var itemIds = self.model.SelectedItemsIds;

            if (item.isSelected) {
                itemIds.push(item.itemId);
            } else {
                var index = itemIds.indexOf(item.itemId);
                if (index > -1) {
                    itemIds.splice(index, 1);
                }
            }

            if (newItem.items.every(isSelected)) {
                self.model.selectAllRows = true;
            } else {
                self.model.selectAllRows = false;
            }

            self.model.isItemChecked = false;
            newItem.items.forEach(function (item) {
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

        function getAllGRNItemDetails() {
            self.model.itemnew.forEach(function (item) {
                if (item.isSelected == true) {
                    self.model.itemList.push(item);
                }
            });

        }

        var SaveReturnableGatepass = function () {
            if (validateSave()) {
                if (!self.model.returnableId) {
                    SaveNewGatepass();
                } else {
                    UpdateGatepass();
                }
            }
        }

        var SaveNewGatepass = function () {
            var store = self.model;
            var URI = CONSTANTS.INVENTORY.STORE.RETURNABLE_GATEPASS.SAVE_API;
            var data = {
                "storeId": self.model.store.id,
                "vendorId": self.model.vendorId,
                "assetTypeId": parseInt(self.model.assetTypeId),
                "againstId": parseInt(self.model.againstId),
                "referenceNo": self.model.referenceNo,
                "statusId": 1,
                "approvalStatusId": 1,
                "totalAmount": self.model.totalAmount,
                "totalTaxAmount": self.model.totalTaxAmount,
                "netAmount": self.model.netAmount,
                "remark": self.model.remark,
                "items": []
            };

            angular.forEach(store.itemList, function (item, key) {
                var storeItem = {
                    "batchId": item.batchId,
                    "vendorId": item.vendorId,
                    "uomTypeId": item.uomTypeId,
                    "uomUnitId": item.uomUnitId,
                    "taxId": item.taxId,
                    "taxPercentage": item.taxPercentage,
                    "returnQuantity": item.returnQuantity,
                    "totalAmount": item.totalAmount,
                    "totalTaxAmount": item.taxAmount,
                    "netAmount": item.netAmount,
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

        var UpdateGatepass = function () {

            var URI = CONSTANTS.INVENTORY.STORE.RETURNABLE_GATEPASS.UPDATE_API + self.model.returnableId;
            var data = {
                "totalAmount": self.model.totalAmount,
                "totalTaxAmount": self.model.totalTaxAmount,
                "netAmount": self.model.netAmount,
                "remark": self.model.remark,
                "referenceNo": self.model.referenceNo,
                "items": [],
            };

            angular.forEach(self.model.itemList, function (item, key) {
                var storeItem = {
                    "id": item.id,
                    "returnQuantity": item.returnQuantity,
                    "totalAmount": item.totalAmount,
                    "totalTaxAmount":item.totalTaxAmount,
                    "netAmount": item.netAmount
                }
                if (item.id == '' || item.id == null) {
                        storeItem.batchId = item.batchId,
                        storeItem.uomTypeId = item.uomTypeId,
                        storeItem.uomUnitId = item.uomUnitId,
                        storeItem.vendorId = item.vendorId,
                        storeItem.taxId = item.taxId,
                        storeItem.taxPercentage = item.taxPercentage,
                        storeItem.returnQuantity = item.returnQuantity,
                        storeItem.totalAmount = item.totalAmount,
                        storeItem.totalTaxAmount = item.totalTaxAmount,
                        storeItem.netAmount = item.netAmount,
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


        var NavigteToBack = function () {
            $state.go('returnableGatepass');
        }

        function searchByNearExpiry() {

            var URI = CONSTANTS.GLOBAL.NEAR_EXPIRY;
            const data = {
                "fromDate": moment(self.model.fromDate).format('YYYY-MM-DD'),
                "toDate": moment(self.model.toDate).format('YYYY-MM-DD'),
                "assetTypeId": self.model.assetTypeId,
                "fromStoreId": self.model.store.id
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

        var validateSave = function () {
            var isValid = true;
            var ItemDetailList = self.model.itemList;
            // !self.model.fromStore.id || !self.model.toStore.id || !self.model.priority.id || 
            if (ItemDetailList.length < 1) {
                growl.error('Error', {
                    title: "Please Add Items"
                });
                isValid = false;
            }
            if (!self.model.assetTypeId) {
                growl.error('Error', {
                    title: "Please Enter Valid Asset Type"
                });
                isValid = false;
            }
            if (!self.model.vendorId) {
                growl.error('Error', {
                    title: "Please Enter Valid Vendor Type"
                });
                isValid = false;
            }
            for (let ItemDetail of ItemDetailList) {
                if (ItemDetail.returnQuantity == null || ItemDetail.returnQuantity > ItemDetail.availableQuantity) {
                    growl.error('Error', {
                        title: "Please Enter Valid Return Quantity Details"
                    });
                    isValid = false;
                }
            }
            return isValid;
        }


        function searchNewGRN() {

            var URI = CONSTANTS.GLOBAL.GRN_API;
            const data = {
                "fromDate": moment(self.model.fromDate).format('YYYY-MM-DD'),
                "toDate": moment(self.model.toDate).format('YYYY-MM-DD'),
                "storeId": self.model.store.id,
                "vendorId": self.model.vendorId
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

        initializeController();
    }

    function config($stateProvider) {
        $stateProvider.state("addNewReturnableGatepass", {
            url: "/addNewReturnableGatepass",
            templateUrl: 'views/PharmacyInventory/InPatient/add-new-returnable-gatepass.html',
            controller: "addNewReturnableGatepass.Controller",
            controllerAs: "vm"
        });
    }

    angular
        .module("myApp")
        .config(config)
        .controller("addNewReturnableGatepass.Controller", addNewReturnableGatepassController);
})();
