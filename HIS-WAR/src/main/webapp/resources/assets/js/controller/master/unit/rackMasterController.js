(function () {
    "use strict";
    function rackMasterController($scope, $rootScope, $http, CONSTANTS, GenericService, growl) {
        var self = this;
        //Default Constructor
        function initializeController() {
            $rootScope.loginpage = true;
            self.model = {
                Stores: [{
                    "store": "Select Store"
                }],
                shelf_details: [
                    {
                        shelfId: undefined,
                        shelfCode: '',
                        shelfName: '',
                    }
                ],
                isShelfCodeValid: [],
                isShelfNameValid: [],
            };
            //Methods 
            self.model.ToggleStatus = ToggleStatus;
            self.model.SaveRackMaster = SaveRackMaster;
            self.model.ClearFields = ClearFields;
            self.model.PopulateRackMaster = PopulateRackMaster;
            self.model.ValidateRack = ValidateRack;
            self.model.AddDuplicateRow = AddDuplicateRow;
            self.model.RemoveRowData = RemoveRowData;
            getRacks();
            PopulateStore();
        }

        var SaveRackMaster = function () {
            if (ValidateRack()) {
                if (!self.model.id) {
                    CreateRack();
                } else {
                    UpdateRack();
                }
            }
        }

        //Method to Populate Store Dropdown
        var PopulateStore = function () {
            var rackMaster = self.model;

            var URI = CONSTANTS.GLOBAL.USER_STORE_API;;

            rackMaster.store = rackMaster.Stores[0];

            rackMaster.Stores.splice(1);

            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    rackMaster.Stores = rackMaster.Stores.concat(response.data);
                    $rootScope.stopLoader();
                },
                function errorCallback(response) {
                    $rootScope.stopLoader();
                });
        }

        var GetEmptyData = function (index) {
            return {
                shelfCode: '',
                shelfName: '',
            }
        }

        var AddDuplicateRow = function (index) {
            self.model.shelf_details.splice(index + 1, 0, GetEmptyData(index));
        }

        var RemoveRowData = function (index) {
            var shelf_details = self.model.shelf_details;
            self.model.shelf_details = shelf_details.slice(0, index).concat(shelf_details.slice(index + 1))
        }

        //Method to get the list of Rack
        var getRacks = function () {
            var URI = CONSTANTS.MASTER.UNIT.PHARAMACY.RACK_MASTER.RACK_MASTER_API;

            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    self.model.RackList = response.data;
                    $rootScope.stopLoader();
                },
                function errorCallback(response) {
                    $rootScope.stopLoader();
                });
        }

        //Method to populate Rack  for editing
        function findInRecordInArray(array, key, value) {
            for (var i = 0; i < array.length; i++) {
                if (array[i][key] == value) {
                    return array[i];
                }
            }
            return null;
        }

        function getRackById(id) {
            return findInRecordInArray(self.model.RackList, 'id', id)
        }

        function PopulateRackMaster(id) {
            self.model.ispharmacyUnitCodeValid = false;
            self.model.ispharmacyDescriptionValid = false;
            self.model.activeRackId = id;
            var URI = CONSTANTS.MASTER.UNIT.PHARAMACY.RACK_MASTER.RACK_MASTER_API + id;
            var data = {};
            GenericService.serviceAction("GET", URI, data).then(
                function (response) {
                    var rack = getRackById(id);
                    rack = response.data[0];
                    var store = self.model.Stores.find(function (stores) {
                        return rack.storeId == stores.id;
                    });
                    self.model.rackCode = rack.rackCode;
                    self.model.rackName = rack.rackName;
                    self.model.shelf_details = rack.items;
                    self.model.store = store;
                    self.model.status = rack.status;
                    self.model.id = rack.id;
                    self.model.isEdit = true;
                });
        };

        //Method to activate or deactivate Rack
        var ToggleStatus = function (rack) {
            var URI = CONSTANTS.MASTER.UNIT.PHARAMACY.RACK_MASTER.RACK_MASTER_API + rack.id;

            var data = {
                "status": rack.status
            };

            GenericService.serviceAction("PATCH", URI, data).then(
                function (response) {
                    $rootScope.stopLoader();
                    growl.success('Status changed successfully', { title: response.status });
                },
                function errorCallback(response) {
                    $rootScope.stopLoader();
                    rack.status = !rack.status;
                    growl.error('Something went wrong', { title: response.status });
                });
        }

        //Method to add the Rack
        var CreateRack = function () {
            var URI = CONSTANTS.MASTER.UNIT.PHARAMACY.RACK_MASTER.RACK_MASTER_API;
            var data = {
                "storeId": self.model.store.id,
                "rackCode": self.model.rackCode,
                "rackName": self.model.rackName,
                "status": "true",
                "items": self.model.shelf_details
            }
            $rootScope.startLoader();
            GenericService.serviceAction("POST", URI, data).then(
                function (response) {
                    var store = self.model.Stores.find(function (stores) {
                        return stores.id == self.model.store.id;
                    });
                    var rack = {
                        "id": response.data.id,
                        "storeName": store.store,
                        "rackCode": self.model.rackCode,
                        "rackName": self.model.rackName,
                        "status": true
                    };
                    self.model.RackList.push(rack);
                    ClearFields();
                    $rootScope.stopLoader();
                    growl.success('Record saved successfully', { title: response.status });
                },
                function errorCallback(response) {
                    $rootScope.stopLoader();
                    growl.error('Something went wrong', { title: response.status });
                });
        };

        //Method to update the Rack
        var UpdateRack = function () {
            var URI = CONSTANTS.MASTER.UNIT.PHARAMACY.RACK_MASTER.RACK_MASTER_API + self.model.id;
            var data = {
                "id": self.model.id,
                "storeId": self.model.store.id,
                "storeName": self.model.store.store,
                "rackCode": self.model.rackCode,
                "rackName": self.model.rackName,
                "items": self.model.shelf_details,
                "status": self.model.status
            }
            // $rootScope.startLoader();
            GenericService.serviceAction("PATCH", URI, data).then(
                function (response) {
                    var store = self.model.Stores.find(function (stores) {
                        return stores.id == self.model.store.id;
                    });
                    var rack = self.model.RackList.find(function (racks) {
                        return racks.id == self.model.id;
                    });
                    rack.storeName = store.store;
                    rack.rackCode = data.rackCode;
                    rack.rackName = data.rackName;
                    ClearFields();
                    self.model.activeRackId = null;
                    self.model.isEdit = false;
                });
        };

        //Method to clear the fileds
        function ClearFields() {
            self.model.id = null;
            self.model.rackCode = "";
            self.model.rackName = "";
            self.model.store = self.model.Stores[0];
            self.model.shelf_details = [{
                shelfId: undefined,
                shelfCode: '',
                shelfName: '',
            }];
        }

        var ValidateRack = function () {
            var isValid = true;
            if (self.model.shelf_details.length <= 0) {
                isValid = false;
            }
            else {
                if (!self.model.store.id) {
                    isValid = false;
                    self.model.isstoreValid = true;
                }
                if (!self.model.rackCode) {
                    isValid = false;
                    self.model.israckCodeValid = true;
                }
                if (!self.model.rackName) {
                    isValid = false;
                    self.model.israckNameValid = true;
                }
                for (var i = 0; i < self.model.shelf_details.length; i++) {
                    if (!self.model.shelf_details[i].shelfCode) {
                        self.model.isShelfCodeValid[i] = true;
                        isValid = false;
                    }

                    if (!self.model.shelf_details[i].shelfName) {
                        self.model.isShelfNameValid[i] = true;
                        isValid = false;
                    }
                    if (!self.model.shelf_details[i].shelfName || !self.model.shelf_details[i].shelfCode) {
                        growl.error('Fill Shelf Details');
                    }
                }
            }
            return isValid;
        }

        initializeController();
    }

    function config($stateProvider) {
        $stateProvider.state("rackMaster", {
            url: "/rackMaster",
            templateUrl: "views/PharmacyInventory/StockAdjustment/rack-master.html",
            controller: "RackMaster.Controller",
            controllerAs: "vm",
        });
    }

    angular
        .module("myApp")
        .config(config)
        .controller("RackMaster.Controller", rackMasterController)
})();