(function () {
  "use strict";
  function unitMasterController($scope, $rootScope, $http, CONSTANTS, GenericService, growl) {
    var self = this;
    //Default Constructor
    function initializeController() {
      $rootScope.loginpage = true;
      self.model = {};
      //Methods 
      self.model.ToggleStatus = ToggleStatus;
      self.model.SaveUnits = SaveUnits;
      self.model.ClearFields = ClearFields;
      self.model.PopulateUnits = PopulateUnits;
      self.model.ValidateUnit = ValidateUnit;
      getUnits();
    }

    var SaveUnits = function () {
      if (ValidateUnit()) {
        if (!self.model.id) {
          CreateUnit();
        } else {
          updateUnit();
        }
      }
    }

    //Method to get the list of Unit
    var getUnits = function () {
      var URI = CONSTANTS.MASTER.GLOBAL.PHARAMACY.UNIT_MASTER.UNIT_API;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          self.model.UnitList = response.data;
          $rootScope.stopLoader();
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
        });
    }

    //Method to populate Unit  for editing
    function findInRecordInArray(array, key, value) {
      for (var i = 0; i < array.length; i++) {
        if (array[i][key] == value) {
          return array[i];
        }
      }
      return null;
    }

    function getUnitById(id) {
      return findInRecordInArray(self.model.UnitList, 'id', id)
    }

    function PopulateUnits(id) {
      self.model.ispharmacyUnitCodeValid = false;
      self.model.ispharmacyDescriptionValid = false;
      self.model.activeUnitId = id;
      var URI = CONSTANTS.MASTER.GLOBAL.PHARAMACY.UNIT_MASTER.UNIT_API + id;
      var data = {};
      GenericService.serviceAction("GET", URI, data).then(
        function (response) {
          var unit = getUnitById(id);
          unit = response.data;
          self.model.code = response.data.code;
          self.model.units = response.data.units;
          self.model.status = response.data.status;
          self.model.id = response.data.id;
          self.model.isEdit = true;
        });
    };

    //Method to activate or deactivate  Unit
    var ToggleStatus = function (unit) {
      var URI = CONSTANTS.MASTER.GLOBAL.PHARAMACY.UNIT_MASTER.UNIT_API + unit.id;

      var data = {
        "status": unit.status
      };

      GenericService.serviceAction("PATCH", URI, data).then(
        function (response) {
          $rootScope.stopLoader();
          growl.success('Status changed successfully', { title: response.status });
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
          unit.status = !unit.status;
          growl.error('Something went wrong', { title: response.status });
        });
    }

    //Method to add the unit
    var CreateUnit = function () {
      var URI = CONSTANTS.MASTER.GLOBAL.PHARAMACY.UNIT_MASTER.UNIT_API;
      var data = {
        "id": self.model.id,
        "code": self.model.code,
        "units": self.model.units,
        "status": true
      }
      $rootScope.startLoader();
      GenericService.serviceAction("POST", URI, data).then(
        function (response) {
          var unit = {
            "id": response.data.id,
            "code": self.model.code,
            "units": self.model.units,
            "status": true
          };
          self.model.UnitList.push(unit);
          ClearFields();

          $rootScope.stopLoader();
          growl.success('Record saved successfully', { title: response.status });
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
          growl.error('Something went wrong', { title: response.status });
        });
    };

    //Method to update the  Unit
    var updateUnit = function () {
      var URI = CONSTANTS.MASTER.GLOBAL.PHARAMACY.UNIT_MASTER.UNIT_API + self.model.id;
      var data = {
        "code": self.model.code,
        "units": self.model.units,
      }
      // $rootScope.startLoader();
      GenericService.serviceAction("PATCH", URI, data).then(
        function (response) {
          var unit = self.model.UnitList.find(function (type) {
            return type.id == self.model.id;
          });
          unit.code = data.code;
          unit.units = data.units;

          ClearFields();
          self.model.activeUnitId = null;
          self.model.isEdit = false;
        });
    };

    //Method to clear the fileds
    function ClearFields() {
      self.model.code = "";
      self.model.units = "";
    }

    var ValidateUnit = function () {
      var isValid = true;
      if (!self.model.code) {
        isValid = false;
        self.model.ispharmacyUnitCodeValid = true;
      }
      if (!self.model.units) {
        isValid = false;
        self.model.ispharmacyDescriptionValid = true;
      }
      return isValid;
    }

    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("pharmacyunitMaster", {
      url: "/pharmacyunitMaster",
      templateUrl: "views/PharmacyInventory/pharmacy-unit-master.html",
      controller: "UnitMaster.Controller",
      controllerAs: "vm",
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("UnitMaster.Controller", unitMasterController)
})();