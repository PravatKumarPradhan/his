(function () {
  "use strict";
  function storageUnitMasterController($scope, $rootScope, $http, CONSTANTS, GenericService, growl) {
    var self = this;
    //Default Constructor
    function initializeController() {
      $rootScope.loginpage = true;
      self.model = {};
      //Methods 
      self.model.ToggleStatus = ToggleStatus;
      self.model.SaveStorageUnits = SaveStorageUnits;
      self.model.ClearFields = ClearFields;
      self.model.PopulateStorageUnit = PopulateStorageUnit;
      self.model.ValidateStorageUnit = ValidateStorageUnit;
      getStorageUnit();

    }

    var SaveStorageUnits = function () {
      if (ValidateStorageUnit()) {
        if (!self.model.id) {
          CreateStorageUnit();
        } else {
          updateStorageUnit();
        }
      }
    }

    //Method to get the list of Storage Unit
    var getStorageUnit = function () {
      var URI = CONSTANTS.MASTER.GLOBAL.PHARAMACY.STORAGE_UNIT_MASTER.STORAGE_UNIT_API;
      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          self.model.storageUnitList = response.data;
          $rootScope.stopLoader();
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
        });
    }

    //Method to populate Storage Unit for editing
    function findInRecordInArray(array, key, value) {
      for (var i = 0; i < array.length; i++) {
        if (array[i][key] == value) {
          return array[i];
        }
      }
      return null;
    }

    function getStorageUnitById(id) {
      return findInRecordInArray(self.model.storageUnitList, 'id', id)
    }

    function PopulateStorageUnit(id) {
      self.model.isstorageUnitCodeValid = false;
      self.model.isstorageUnitValid = false;
      self.model.activeStorageUnitId = id;
      var URI = CONSTANTS.MASTER.GLOBAL.PHARAMACY.STORAGE_UNIT_MASTER.STORAGE_UNIT_API + id;
      var data = {};
      GenericService.serviceAction("GET", URI, data).then(
        function (response) {
          var storageUnit = getStorageUnitById(id);
          storageUnit = response.data;
          self.model.code = response.data.code;
          self.model.unitName = response.data.unitName;
          self.model.status = response.data.status;
          self.model.id = response.data.id;
          self.model.isEdit = true;
        });
    };

    //Method to activate or deactivate Storage Unit
    var ToggleStatus = function (storageUnit) {
      var URI = CONSTANTS.MASTER.GLOBAL.PHARAMACY.STORAGE_UNIT_MASTER.STORAGE_UNIT_API + storageUnit.id;
      var data = {
        "status": storageUnit.status
      };
      GenericService.serviceAction("PATCH", URI, data).then(
        function (response) {
          $rootScope.stopLoader();
          growl.success('Status changed successfully', { title: response.status });
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
          storageUnit.status = !storageUnit.status;
          growl.error('Something went wrong', { title: response.status });
        });
    }

    //Method to add the StorageUnit
    var CreateStorageUnit = function () {
      var URI = CONSTANTS.MASTER.GLOBAL.PHARAMACY.STORAGE_UNIT_MASTER.STORAGE_UNIT_API;
      var data = {
        "id": self.model.id,
        "code": self.model.code,
        "unitName": self.model.unitName,
        "status": true
      }
      $rootScope.startLoader();
      GenericService.serviceAction("POST", URI, data).then(
        function (response) {
          var storageUnit = {
            "id": response.data.id,
            "code": self.model.code,
            "unitName": self.model.unitName,
            "status": true
          };
          self.model.storageUnitList.push(storageUnit);
          ClearFields();

          $rootScope.stopLoader();
          growl.success('Record saved successfully', { title: response.status });
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
          growl.error('Something went wrong', { title: response.status });
        });
    };

    //Method to update the Storage Unit
    var updateStorageUnit = function () {
      var URI = CONSTANTS.MASTER.GLOBAL.PHARAMACY.STORAGE_UNIT_MASTER.STORAGE_UNIT_API + self.model.id;
      var data = {
        "code": self.model.code,
        "unitName": self.model.unitName,
      }
      GenericService.serviceAction("PATCH", URI, data).then(
        function (response) {
          var storageUnit = self.model.storageUnitList.find(function (type) {
            return type.id == self.model.id;
          });
          storageUnit.code = data.code;
          storageUnit.unitName = data.unitName;

          ClearFields();
          self.model.activeStorageUnitId = null;
          self.model.isEdit = false;
        });
    };

    //Method to clear the fileds
    function ClearFields() {
      self.model.code = "";
      self.model.unitName = "";
    }

    var ValidateStorageUnit = function () {
      var isValid = true;

      if (!self.model.code) {
        isValid = false;
        self.model.isstorageUnitCodeValid = true;
      }
      if (!self.model.unitName) {
        isValid = false;
        self.model.isstorageUnitValid = true;
      }
      return isValid;
    }

    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("storageunitMaster", {
      url: "/storageunitMaster",
      templateUrl: 'views/PharmacyInventory/storage-unit-master.html',
      controller: "StorageController.Controller",
      controllerAs: "vm",
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("StorageController.Controller", storageUnitMasterController)
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