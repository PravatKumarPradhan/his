(function () {
    "use strict";
    function mainstoreMasterController($scope, $rootScope, $http, CONSTANTS, GenericService, growl) {
        var self = this;
        //Default Constructor
        function initializeController() {
            $rootScope.loginpage = true;
            self.model = {
                storeTypes: [{
                    "type": "Select Store Type"
                }],
                visitTypes: null
            };
            //Methods 
            self.model.ToggleStatus = ToggleStatus;
            self.model.SaveMainStore = SaveMainStore;
            self.model.ClearFields = ClearFields;
            self.model.PopulateMainStore = PopulateMainStore;
            self.model.ValidateMainStore = ValidateMainStore;
            self.model.PopulateStoreType = PopulateStoreType;
            getMainStore();
            PopulateStoreType();
            PopulateVisitType();
        }

        var SaveMainStore = function () {
            if (ValidateMainStore()) {
                if (!self.model.id) {
                    CreateMainStore();
                } else {
                    updateMainStore();
                }
            }
        }

        var PopulateVisitType = function () {
            self.model.visitTypes = [
                { 'visit_type_id': 1, 'visit_type_name': 'OP' },
                { 'visit_type_id': 2, 'visit_type_name': 'IP' },
                { 'visit_type_id': 3, 'visit_type_name': 'Day Care' },
                { 'visit_type_id': 4, 'visit_type_name': 'ER' }
            ];
        }

        //Method to Populate Store Type Dropdown
        var PopulateStoreType = function () {
            var storeType = self.model;

            var URI = CONSTANTS.GLOBAL.STORE_TYPE_API;

            storeType.storeType = storeType.storeTypes[0];

            storeType.storeTypes.splice(1);

            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    storeType.storeTypes = storeType.storeTypes.concat(response.data);
                    $rootScope.stopLoader();
                },
                function errorCallback(response) {
                    $rootScope.stopLoader();
                });

        }

        //Method to get the list of Main Store
        var getMainStore = function () {
            var URI = CONSTANTS.MASTER.UNIT.PHARAMACY.MAIN_STORE_MASTER.MAIN_STORE_MASTER_LS_API;
            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    self.model.mainStoreList = response.data;
                    $rootScope.stopLoader();
                },
                function errorCallback(response) {
                    $rootScope.stopLoader();
                });
        }

        //Method to populate Main Store for editing
        function findInRecordInArray(array, key, value) {
            for (var i = 0; i < array.length; i++) {
                if (array[i][key] == value) {
                    return array[i];
                }
            }
            return null;
        }

        function getMainStoreById(id) {
            return findInRecordInArray(self.model.mainStoreList, 'id', id)
        }

        function PopulateMainStore(id) {
            self.model.ismainstoreCodeValid = false;
            self.model.ismainstoreNameValid = false;
            self.model.ismainstoreTypeValid = false;
            self.model.activeMainStoreId = id;
            var URI = CONSTANTS.MASTER.UNIT.PHARAMACY.MAIN_STORE_MASTER.MAIN_STORE_MASTER_DU_API + id;
            var data = {};
            GenericService.serviceAction("GET", URI, data).then(
                function (response) {
                    var mainStore = getMainStoreById(id);
                    mainStore = response.data;
                    var storeType = self.model.storeTypes.find(function (type) {
                        return response.data.storeTypeId == type.id;
                    });
                    self.model.code = response.data.storeCode;
                    self.model.storeName = response.data.storeName;
                    self.model.storeType = storeType;
                    self.model.status = response.data.status;
                    self.model.visit_type_id = response.data.visitTypeId;
                    self.model.id = response.data.id;
                    self.model.isEdit = true;
                });
        };

        //Method to activate or deactivate Main Store
        var ToggleStatus = function (mainStore) {
            var URI = CONSTANTS.MASTER.UNIT.PHARAMACY.MAIN_STORE_MASTER.MAIN_STORE_MASTER_DU_API + mainStore.id;
            var data = {
                "status": mainStore.status
            };
            GenericService.serviceAction("PATCH", URI, data).then(
                function (response) {
                    $rootScope.stopLoader();
                    growl.success('Status changed successfully', { title: response.status });
                },
                function errorCallback(response) {
                    $rootScope.stopLoader();
                    mainStore.status = !mainStore.status;
                    growl.error('Something went wrong', { title: response.status });
                });
        }

        //Method to add the Main Store
        var CreateMainStore = function () {
            var URI = CONSTANTS.MASTER.UNIT.PHARAMACY.MAIN_STORE_MASTER.MAIN_STORE_MASTER_LS_API;
            var data = {
                "storeTypeId": self.model.storeType.id,
                "storeName": self.model.storeName,
                "storeCode": self.model.code,
                "visitTypeId": self.model.visit_type_id,
                "status": true
                // "opcheckboxId": self.model.opcheckboxId
            }
            $rootScope.startLoader();
            GenericService.serviceAction("POST", URI, data).then(
                function (response) {
                    var storeType = self.model.storeTypes.find(function (store) {
                        return store.id == self.model.storeType.id;
                    });
                    var mainStore = {
                        "id": response.data.id,
                        "storeTypeId": self.model.storeType.id,
                        "storeType": storeType.type,
                        "storeName": self.model.storeName,
                        "storeCode": self.model.code,
                        "status": true
                    };
                    self.model.mainStoreList.push(mainStore);
                    ClearFields();

                    $rootScope.stopLoader();
                    growl.success('Record saved successfully', { title: response.status });
                },
                function errorCallback(response) {
                    $rootScope.stopLoader();
                    growl.error('Something went wrong', { title: response.status });
                });
        };

        //Method to update the Main Store
        var updateMainStore = function () {
            var URI = CONSTANTS.MASTER.UNIT.PHARAMACY.MAIN_STORE_MASTER.MAIN_STORE_MASTER_DU_API + self.model.id;
            var data = {
                "storeTypeId": self.model.storeType.id,
                "storeName": self.model.storeName,
                "storeCode": self.model.code,
                "visitTypeId": self.model.visit_type_id
            }
            GenericService.serviceAction("PATCH", URI, data).then(
                function (response) {
                    var storeType = self.model.storeTypes.find(function (type) {
                        return type.id == self.model.storeType.id;
                    });
                    var mainStore = self.model.mainStoreList.find(function (type) {
                        return type.id == self.model.id;
                    });
                    mainStore.id = response.data.id;
                    mainStore.storeType = storeType.type;
                    mainStore.storeName = self.model.storeName;
                    mainStore.storeCode = self.model.code;

                    ClearFields();
                    self.model.activeMainStoreId = null;
                    self.model.isEdit = false;
                });
        };

        //Method to clear the fileds
        function ClearFields() {
            self.model.code = "";
            self.model.storeName = "";
            PopulateStoreType();
            PopulateVisitType();
        }

        var ValidateMainStore = function () {
            var isValid = true;
            if (!self.model.code) {
                isValid = false;
                self.model.ismainstoreCodeValid = true;
            }
            if (!self.model.storeName) {
                isValid = false;
                self.model.ismainstoreNameValid = true;
            }
            if (!self.model.storeType.id) {
                isValid = false;
                self.model.ismainstoreTypeValid = true;
            }
            if (!self.model.visit_type_id) {
                isValid = false;
                growl.error('Please Select Visit Type!', { title: 'Warning' });
            }
            return isValid;
        }

        initializeController();
    }

    function config($stateProvider) {
        $stateProvider.state("mainstoremaster", {
            url: "/mainstoremaster",
            templateUrl: 'views/PharmacyInventory/StockAdjustment/main-store-master.html',
            controller: "MainStoreMaster.Controller",
            controllerAs: "vm",
        });
    }

    angular
        .module("myApp")
        .config(config)
        .controller("MainStoreMaster.Controller", mainstoreMasterController)
        .directive('showFocus', function ($timeout) {
            return function (scope, element, attrs) {
                scope.$watch(attrs.showFocus,
                    function (newValue, oldValue) {
                        $timeout(function () {
                            (newValue != oldValue) && element.focus();
                        });
                    }, true);
            };
        });
})();