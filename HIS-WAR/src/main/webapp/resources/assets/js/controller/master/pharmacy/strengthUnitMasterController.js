(function () {
    "use strict";
    function strengthUnitMasterController($scope, $rootScope, $http, CONSTANTS, GenericService, growl) {
      var self = this;
      //Default Constructor
    function initializeController() {
        $rootScope.loginpage = true;
        self.model = {};
        //Methods 
        self.model.ToggleStatus = ToggleStatus;
        self.model.SaveStrengthUnits = SaveStrengthUnits;
        self.model.ClearFields = ClearFields;
        self.model.PopulateStrengthUnits = PopulateStrengthUnits;
        self.model.ValidateStrengthUnit = ValidateStrengthUnit;
        getStrengthUnits();
      }
  
      var SaveStrengthUnits = function () {
        if (ValidateStrengthUnit()) {
          if (!self.model.id) {
            CreateStrengthUnit();
          } else {
            updateStrengthUnit();
          }
        }
      }
  
      //Method to get the list of Strength Unit
      var getStrengthUnits = function () {
        var URI = CONSTANTS.MASTER.GLOBAL.PHARAMACY.STRENGTH_UNIT_MASTER.STRENGTH_UNIT_API;
  
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
  
      function getStrengthUnitById(id) {
        return findInRecordInArray(self.model.UnitList, 'id', id)
      }
  
      function PopulateStrengthUnits(id) {
        self.model.isunitCodeValid = false;
        self.model.isunitDescriptionValid = false;
        self.model.activeStrengthUnitId = id;
        var URI = CONSTANTS.MASTER.GLOBAL.PHARAMACY.STRENGTH_UNIT_MASTER.STRENGTH_UNIT_API + id;
        var data = {};
        GenericService.serviceAction("GET", URI, data).then(
          function (response) {
            var unit = getStrengthUnitById(id);
            unit = response.data;
            self.model.code = response.data.code;
            self.model.units = response.data.unit;
            self.model.id = response.data.id;
            self.model.isEdit = true;
          });
      };
  
      //Method to activate or deactivate  Unit
      var ToggleStatus = function (unit) {
        var URI = CONSTANTS.MASTER.GLOBAL.PHARAMACY.STRENGTH_UNIT_MASTER.STRENGTH_UNIT_API + unit.id;
  
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
      var CreateStrengthUnit = function () {
        var URI = CONSTANTS.MASTER.GLOBAL.PHARAMACY.STRENGTH_UNIT_MASTER.STRENGTH_UNIT_API;
        var data = {
          "id": self.model.id,
          "code": self.model.code,
          "unit": self.model.units,
          "status": true
        }
        $rootScope.startLoader();
        GenericService.serviceAction("POST", URI, data).then(
          function (response) {
            var unit = {
              "id": response.data.id,
              "code": self.model.code,
              "unit": self.model.units,
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
      var updateStrengthUnit = function () {
        var URI = CONSTANTS.MASTER.GLOBAL.PHARAMACY.STRENGTH_UNIT_MASTER.STRENGTH_UNIT_API + self.model.id;
        var data = {
          "code": self.model.code,
          "unit": self.model.units,
        }
        // $rootScope.startLoader();
        GenericService.serviceAction("PATCH", URI, data).then(
          function (response) {
            var unit = self.model.UnitList.find(function (type) {
              return type.id == self.model.id;
            });
            unit.code = data.code;
            unit.unit = data.unit;
  
            ClearFields();
            self.model.activeStrengthUnitId = null;
            self.model.isEdit = false;
          });
      };
  
      //Method to clear the fileds
      function ClearFields() {
        self.model.code = "";
        self.model.units = "";
      }
  
      var ValidateStrengthUnit = function () {
        var isValid = true;
        if (!self.model.code) {
          isValid = false;
          self.model.isunitCodeValid = true;
        }
        if (!self.model.units) {
          isValid = false;
          self.model.isunitDescriptionValid = true;
        }
        return isValid;
      }
  
      initializeController();
    }
  
    function config($stateProvider) {
      $stateProvider.state("pharmacyStrengthUnitMaster", {
        url: "/pharmacyStrengthUnitMaster",
        templateUrl: "views/PharmacyInventory/pharmacy-strength-unit-master.html",
        controller: "StrengthUnitMaster.Controller",
        controllerAs: "vm",
      });
    }
  
    angular
      .module("myApp")
      .config(config)
      .controller("StrengthUnitMaster.Controller", strengthUnitMasterController)
  })();