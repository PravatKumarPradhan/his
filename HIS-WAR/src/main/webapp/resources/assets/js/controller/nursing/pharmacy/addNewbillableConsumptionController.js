    (function () {
        "use strict";
        var CACHE = {};

        function addNewbillableConsumptionController($scope, $state, $rootScope, $http, $filter, CONSTANTS, StatusService, GenericService, growl, commonDetailService) {
            var self = this;
            var mainStoreList = [];
            var visitNumber = '';
            //Default Constructor
            function initializeController() {
                $rootScope.loginpage = true;
                self.model = {
                    storeList: [{
                        "store": "All"
                    }],
                    assetCategories: [{
                        "category": "Select Asset Category"
                    }],
                    productCategories: [{
                        "category": "Select Product Category"
                    }],
                    wardList: [{
                        "wardName": "All"
                    }],
                    storeList: [{
                        "store": "All"
                    }],
                    visitDetails: [{
                        "visitNo": "Select Visit No"
                    }],
                    SelectedItemsIds: [],
                    selectAllRows: false,
                    SelectedItemIds: [],
                    ItemDetailList: [],
                    // indentId: null,
                    consignment: false,
                    itemSearch: {
                        itemCode : undefined,
                        itemName : undefined,
                        batchNo : undefined
                    }
                };

                StatusService.GetStatus().then(function (status) {
                    self.model.Status = status;
                })

                self.model.SearchpatientNames = SearchpatientNames;
                self.model.SearchUhid = SearchUhid;
                self.model.AddPatientDetails = AddPatientDetails;
                self.model.search = '';
                self.model.prevSearch = '';
                self.model.patientNames = [];
                self.model.GetItemList = GetItemList;
                self.model.GetAssetCategoryList = GetAssetCategoryList;
                self.model.GetProductCategoryList = GetProductCategoryList;
                self.model.SearchNewItem = SearchNewItem;
                self.model.NewItemList = [];
                self.model.SelectItem = SelectItem;
                self.model.SelectAllItems = SelectAllItems;
                self.model.getAllItemDetails = getAllItemDetails;
                self.model.RemoveRowData = RemoveRowData;
                self.model.Validate = Validate;
                self.model.SaveNewbillableConsumption = SaveNewbillableConsumption;
                self.model.NavigteToBillableConsumption = NavigteToBillableConsumption;

                // self.model.indentId = commonDetailService.getDataId();
                // GetPatientIndentDetails(commonDetailService.getDataId());

                PopulateDropdown();
                // GetAssetCategoryList();
            }

            // var GetPatientIndentDetails = function (itemId) {

            //     var URI = CONSTANTS.PHARMACY.OP.PATIENT_INDENT.DETAIL_API + itemId;
            //     $rootScope.startLoader();
            //     GenericService.serviceAction("GET", URI, {}).then(
            //         function (response) {
            //             self.model.indentId = response.data.id;
            //             self.model.ItemDetailList = response.data.items;
            //             $rootScope.stopLoader();
            //         });

            // }

            var PopulateDropdown = function () {

                var URI = CONSTANTS.GLOBAL.PATIENT_ISSUE_DROPDOWN_API;

                self.model.visitNumber = self.model.visitDetails[0];
                self.model.store = self.model.storeList[0];
                self.model.ward = self.model.wardList[0];

                $rootScope.startLoader();
                GenericService.serviceAction("GET", URI, {}).then(
                    function (response) {
                        self.model.visitTypeList = response.data.visitType;
                        self.model.visitType = self.model.visitTypeList[0];
                        self.model.storeList = self.model.storeList.concat(response.data.store);
                        self.model.wardList = self.model.wardList.concat(response.data.ward);
                        $rootScope.stopLoader();
                    });
            }

            var SearchpatientNames = function (search) {

                if (search.length < 3) return;

                if ((!self.model.prevSearch || self.model.prevSearch != search) && (search.length == 3)) {
                    self.model.prevSearch = search;

                    var URI = CONSTANTS.GLOBAL.PATIENT_SEARCH_API + search;

                    $rootScope.startLoader();
                    return GenericService.serviceAction("GET", URI, {})
                        .then(function (response) {
                            $rootScope.stopLoader();
                            if (!!response.data && response.data.length > 0) {
                                self.model.patientNames = response.data;
                                self.model.patientId = response.data.patientId;
                                self.model.uhid = response.data.uhid;
                                $rootScope.stopLoader();
                                //console.log(self.model.patientNames.itemName);
                                return $filter('filter')(self.model.patientNames, {
                                    $: search
                                });
                            } else {
                                $rootScope.stopLoader();
                                return PatientNameNotFound(search);
                            }
                        }, function (err) {
                            $rootScope.stopLoader();
                            return PatientNameNotFound(search);
                        });
                } else {
                    if (!!self.model.patientNames && self.model.patientNames.length > 0 &&
                        self.model.patientNames[0].itemFound != undefined && !self.model.patientNames[0].itemFound) {
                        return PatientNameNotFound(search);
                    } else {
                        return $filter('filter')(self.model.patientNames, {
                            $: search
                        });
                    }
                }
            }

            var PatientNameNotFound = function (search) {
                var item = {
                    "itemFound": false,
                    "details": "Patient Not found with name " + search
                };
                self.model.patientNames = [item];
                return self.model.patientNames;
            }

            var AddPatientDetails = function (item) {

                if (!!item.patientId) {
                    var URI = CONSTANTS.GLOBAL.PATIENT_DETAILS_SEARCH_API;

                    var data = {
                        "visitType": !!self.model.visitType && !!self.model.visitType.id ? self.model.visitType.id : undefined,
                        "patientId": !!item.patientId ? item.patientId : undefined,
                        // "uhid": !!item.uhid ? item.uhid : undefined,
                    };

                    $rootScope.startLoader();
                    GenericService.serviceAction("POST", URI, data)
                        .then(function (response) {

                            self.model.patientName = response.data.patientName,
                                self.model.patientId = response.data.patientId,
                                self.model.uhid = response.data.uhid,
                                self.model.bedNo = response.data.bedNo,
                                self.model.bedId = response.data.bedId,
                                self.model.encounterId = response.data.encounterId,
                                self.model.admissionId = response.data.admissionId

                            if (self.model.visitType.id == 1) {
                                self.model.visitDetails = self.model.visitDetails.concat(response.data.visitDetails);
                            } else if (self.model.visitType.id != 1) {
                                self.model.visitNumberText = response.data.visitDetails[0].visitNo;
                            }
                            // self.model.visitDetails = self.model.visitDetails.concat(response.data.visitDetails);
                            self.model.ward = self.model.wardList.find(function (ward) {
                                return response.data.wardId == ward.id;
                            });


                            $rootScope.stopLoader();
                        }, function (err) {
                            growl.error('Error while fetching the patient details');
                            $rootScope.stopLoader();
                        });
                }
            }

            var SearchUhid = function (search) {

                if (search.length < 3) return;

                if ((!self.model.prevSearch || self.model.prevSearch != search) && (search.length == 3)) {
                    self.model.prevSearch = search;

                    var URI = CONSTANTS.GLOBAL.UHID_SEARCH_API + search;

                    $rootScope.startLoader();
                    return GenericService.serviceAction("GET", URI, {})
                        .then(function (response) {
                            $rootScope.stopLoader();
                            if (!!response.data && response.data.length > 0) {
                                self.model.patientNames = response.data;
                                self.model.patientId = response.data.patientId;
                                self.model.uhid = response.data.uhid;
                                $rootScope.stopLoader();
                                //console.log(self.model.patientNames.itemName);
                                return $filter('filter')(self.model.patientNames, {
                                    $: search
                                });
                            } else {
                                $rootScope.stopLoader();
                                return UHIDNotFound(search);
                            }
                        }, function (err) {
                            $rootScope.stopLoader();
                            return UHIDNotFound(search);
                        });
                } else {
                    if (!!self.model.patientNames && self.model.patientNames.length > 0 &&
                        self.model.patientNames[0].itemFound != undefined && !self.model.patientNames[0].itemFound) {
                        return UHIDNotFound(search);
                    } else {
                        return $filter('filter')(self.model.patientNames, {
                            $: search
                        });
                    }
                }
            }

            var UHIDNotFound = function (search) {
                var item = {
                    "itemFound": false,
                    "details": "UHID Not found with search " + search
                };
                self.model.patientNames = [item];
                return self.model.patientNames;
            }

            var ValidateToGetItems = function () {
                var isValid = true;

                if (!self.model.patientName) {
                    self.model.isPatientNameValid = true;
                    isValid = false;
                    growl.error('Enter patient name')
                }
                if (!self.model.visitType.id) {
                    self.model.isVisitTypeValid = true;
                    isValid = false;
                    growl.error('Select visit type')
                }
                if (!self.model.store.id) {
                    self.model.isStoreValid = true;
                    isValid = false;
                    growl.error('Select store')
                }
                if(self.model.visitType){
                    if (self.model.visitType.id == 1) {
                        if (!self.model.visitNumber.id) {
                            self.model.isVisitNoValid = true;
                            isValid = false;
                            growl.error('Select visit no')
                        }
                    } else {
                        if (!self.model.visitNumberText) {
                            self.model.isVisitNoValid = true;
                            isValid = false;
                            growl.error('Select visit no')
                        }
                    }
                }
                return isValid;
            }

            var GetItemList = function () {
               if(ValidateToGetItems()) {
                GetAssetCategoryList();
                self.model.NewItemList = [];
                self.model.selectAllRows = false,
                self.model.itemSearch.itemCode = undefined;
                self.model.itemSearch.itemName = undefined;
                self.model.itemSearch.batchNo = undefined;
                self.model.action = 'Open';
                var popup = angular.element('#ItemListPopUp');
                popup.modal('show');
                }
            }

            var GetAssetCategoryList = function () {
                var URI = CONSTANTS.GLOBAL.ASSET_CATEGORY_API + 1;

                self.model.assetCategory = self.model.assetCategories[0];
                self.model.productCategory = self.model.productCategories[0];

                self.model.assetCategories.splice(1);
                self.model.productCategories.splice(1);

                $rootScope.startLoader();
                GenericService.serviceAction("GET", URI, {}).then(
                    function (response) {
                        self.model.assetCategories = self.model.assetCategories.concat(response.data);
                        $rootScope.stopLoader();
                    },
                    function (err) {
                        $rootScope.stopLoader();
                    });
            }

            var GetProductCategoryList = function (id) {

                var URI = CONSTANTS.GLOBAL.PRODUCT_CATEGORY_API + id;
                self.model.productCategory = self.model.productCategories[0];
                self.model.productCategories.splice(1);

                $rootScope.startLoader();
                GenericService.serviceAction("GET", URI, {}).then(
                    function (response) {
                        self.model.productCategories = self.model.productCategories.concat(response.data);
                        $rootScope.stopLoader();
                    },
                    function (err) {
                        $rootScope.stopLoader();
                    });
            }

            var SearchNewItem = function () {

                var URI = CONSTANTS.GLOBAL.BATCH_API;
    
                if(self.model.visitType){
                    if (self.model.visitType.id == 1) {
                        visitNumber = self.model.visitNumber.visitNo;
                    } else {
                        visitNumber = self.model.visitNumberText;
                    }
                }
                var data = {
                    "assetTypeId": 1,
                    "toStoreId": self.model.store.id ? self.model.store.id : undefined,
                    "assetCategoryId": self.model.assetCategory.id ? self.model.assetCategory.id : undefined,
                    "productCategoryId": self.model.productCategory.id ? self.model.productCategory.id : undefined,

                    // "visitType": !!self.model.visitType && !!self.model.visitType.id ? self.model.visitType.id : undefined,
                    // "patientName": !!self.model.patientName ? self.model.patientName : undefined,
                    // "patientId": !!self.model.patientId ? self.model.patientId : undefined,
                    // "uhid": !!self.model.uhid ? self.model.uhid : undefined, 
                    // "visitNo": !!self.model.visitNumber && !!visitNumber ? visitNumber : undefined,
                    // "bedNo": !!self.model.bedNo ? self.model.bedNo : undefined, 
                    // "wardId": !!self.model.ward.id ? self.model.ward.id : undefined,
                    // "storeId": !!self.model.store && !!self.model.store.id ? self.model.store.id : undefined,
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

            var getAllItemDetails = function () {
                self.model.NewItemList.forEach(function (item) {
                    if (item.isSelected == true) {
                        self.model.ItemDetailList.push(item);
                    }
                });

            }

            var RemoveRowData = function (index) {
                var ItemDetailList = self.model.ItemDetailList;
                self.model.ItemDetailList = ItemDetailList.slice(0, index).concat(ItemDetailList.slice(index + 1))
            }
            
            var Validate = function () {
                var isValid = true;

                self.model.isPatientNameValid = false;
                self.model.isVisitTypeValid = false;
                self.model.isVisitNoValid = false;

                if (!self.model.patientName) {
                    self.model.isPatientNameValid = true;
                    isValid = false;
                    growl.error('Enter patient name')
                }
                if (!self.model.visitType.id) {
                    self.model.isVisitTypeValid = true;
                    isValid = false;
                    growl.error('Select visit type')
                }
                if(self.model.visitType){
                    if (self.model.visitType.id == 1) {
                        if (!self.model.visitNumber.id) {
                            self.model.isVisitNoValid = true;
                            isValid = false;
                            growl.error('Select visit no')
                        }
                    } else {
                        if (!self.model.visitNumberText) {
                            self.model.isVisitNoValid = true;
                            isValid = false;
                            growl.error('Select visit no')
                        }
                    }
                }

                var ItemDetailList = self.model.ItemDetailList;
                if (!ItemDetailList || ItemDetailList.length <= 0) {
                    isValid = false;
                    growl.error("Please add some items in the bill");
                } else {
                    ItemDetailList.forEach(function (item) {

                        self.model.isRemarkValid = false;
                        item['isQuantityValid'] = false;

                        if (!item.quantity || item.availableQuantity < item.quantity) {
                            item['isQuantityValid'] = true;
                            isValid = false;
                            growl.error('Enter valid quantity')
                        }

                        if (item.quantity == 0) {
                            item['isQuantityValid'] = true;
                            isValid = false;
                            growl.error('Enter quantity greater than zero')
                        }
                    });
                }
                return isValid;
            }

            var SetStatus = function (x) {
                var status = self.model.Status.find(function (obj) {
                    return obj.status == x;
                });
                return status.id;
            }

            var SaveNewbillableConsumption = function () {
                var ItemDetailList = self.model.ItemDetailList;
                if (Validate()) {
                var URI = CONSTANTS.PHARMACY.OP.PATIENT_INDENT.SAVE_API;

                var data = {

                    "patientId": !!self.model.patientId ? self.model.patientId : undefined,
                    "visitType": !!self.model.visitType && !!self.model.visitType.id ? self.model.visitType.id : undefined,
                    "doctorId": !!self.model.doctorId ? self.model.doctorId : undefined,
                    "admissionId": !!self.model.admissionId ? self.model.admissionId : undefined,
                    "encounterId": !!self.model.encounterId ? self.model.encounterId : undefined,
                    "ward": !!self.model.wardId ? self.model.wardId : undefined,
                    "bed": !!self.model.bedId ? self.model.bedId : undefined,
                    "toStoreId": !!self.model.store && !!self.model.store.id ? self.model.store.id : undefined,
                    "consignment": !!self.model.consignment ? self.model.consignment : undefined,
                    "remark": self.model.remark,
                    "statusId": SetStatus('New'),
                    "approvalStatusId": SetStatus('New'),
                    "items": []
                }

                angular.forEach(ItemDetailList, function (item, key) {
                    var item = {
                        "itemId": item.itemId,
                        "uomTypeId": item.uomTypeId,
                        "uomUnitId": item.uomUnitId,
                        "indentQuantity": item.quantity,
                        "statusId": SetStatus('New')
                    }
                    data.items.push(item);
                });

                $rootScope.startLoader();
                GenericService.serviceAction("POST", URI, data).then(
                    function (response) {
                        NavigteToBillableConsumption();
                        $rootScope.stopLoader();
                        growl.success('New billable consuption successffully saved');
                    },
                    function (err) {
                        $rootScope.stopLoader();
                        growl.error('Error while saving new billable consuption ');
                    });
                 }
            }
            var NavigteToBillableConsumption = function () {        
                    $state.go('billableConsumption');
            }
            // var NavigteToBillableConsumption = function () {
            //     document.getElementById('popupClose').click();
            //     setTimeout(function () {
            //         $state.go('billableConsumption');
            //     }, 1000);
            // }

            initializeController();
        }


        function config($stateProvider) {
            $stateProvider.state("addNewbillableConsumption", {
                url: "/addNewbillableConsumption",
                templateUrl: 'views/PharmacyInventory/InPatient/add-new-billable-consumption.html',
                controller: "AddNewbillableConsumption.Controller",
                controllerAs: "vm"
            });
        }

        angular
            .module("myApp")
            .config(config)
            .controller("AddNewbillableConsumption.Controller", addNewbillableConsumptionController);
    })();