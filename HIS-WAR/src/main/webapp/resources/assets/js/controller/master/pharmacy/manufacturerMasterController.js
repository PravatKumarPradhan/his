(function () {
  "use strict";
  function manufacturerMasterController($scope, $rootScope, $http, CONSTANTS, GenericService, growl) {
    var self = this;
    //Default Constructor
    function initializeController() {
      $rootScope.loginpage = true;
      self.model = {};
      //Methods 
      self.model.ToggleStatus = ToggleStatus;
      self.model.SaveManufacturer = SaveManufacturer;
      self.model.ClearFields = ClearFields;
      self.model.PopulateManufacturer = PopulateManufacturer;
      self.model.ValidateManufacturer = ValidateManufacturer;
      self.model.detail_address = '';
      getManufacturer();
    }

    var SaveManufacturer = function () {
      if (ValidateManufacturer()) {
        if (!self.model.id) {
          CreateManufacturer();
        } else {
          updateManufacturer();
        }
      }
    }

    //Method to get the list of Manufacturer
    var getManufacturer = function () {
      var URI = CONSTANTS.MASTER.GLOBAL.PHARAMACY.MANUFACTURER_MASTER.MANUFATURER_API;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          self.model.manufactureDataList = response.data;
          $rootScope.stopLoader();
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
        });
    }

    //Method to populate Manufacturer for editing
    function findInRecordInArray(array, key, value) {
      for (var i = 0; i < array.length; i++) {
        if (array[i][key] == value) {
          return array[i];
        }
      }
      return null;
    }

    function getManufacturerById(id) {
      return findInRecordInArray(self.model.manufactureDataList, 'id', id)
    }

    function PopulateManufacturer(id) {
      self.model.ismanufacturerCodeValid = false;
      self.model.ismanufacturerValid = false;
      self.model.isaddressValid = false;
      self.model.activeManufacturerId = id;
      var URI = CONSTANTS.MASTER.GLOBAL.PHARAMACY.MANUFACTURER_MASTER.MANUFATURER_API + id;
      var data = {};
      GenericService.serviceAction("GET", URI, data).then(
        function (response) {
          var manufacture = getManufacturerById(id);
          manufacture = response.data;
          self.model.code = response.data.code;
          self.model.name = response.data.name;
          self.model.address = response.data.address;
          self.model.id = response.data.id;
          self.model.status = response.data.status;
          self.model.isEdit = true;
        });
    };

    //Method to activate or deactivate Manufacturer
    var ToggleStatus = function (manufacture) {

      var URI = CONSTANTS.MASTER.GLOBAL.PHARAMACY.MANUFACTURER_MASTER.MANUFATURER_API + self.model.id;

      var data = {
        "status": manufacture.status
      };

      GenericService.serviceAction("PATCH", URI, data).then(
        function (response) {
          $rootScope.stopLoader();
          growl.success('Status changed successfully', { title: response.status });
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
          manufacture.status = !manufacture.status;
          growl.error('Something went wrong', { title: response.status });
        });
    }

    //Method to add the Manufacturer
    var CreateManufacturer = function () {
      var URI = CONSTANTS.MASTER.GLOBAL.PHARAMACY.MANUFACTURER_MASTER.MANUFATURER_API;

      var data = {
        "name": self.model.name,
        "code": self.model.code,
        "address": self.model.address,
        "status": true,
      }

      $rootScope.startLoader();
      GenericService.serviceAction("POST", URI, data).then(
        function (response) {
          var manufacture = {
            "id": self.model.id,
            "name": self.model.name,
            "code": self.model.code,
            "address": self.model.address,
            "status": true
          };
          self.model.manufactureDataList.push(manufacture);
          ClearFields();

          $rootScope.stopLoader();
          growl.success('Record saved successfully', { title: response.status });
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
          growl.error('Something went wrong', { title: response.status });
        });
    };

    //Method to update the Manufacturer
    var updateManufacturer = function () {
      var URI = CONSTANTS.MASTER.GLOBAL.PHARAMACY.MANUFACTURER_MASTER.MANUFATURER_API + self.model.id;

      var data = {
        "name": self.model.name,
        "code": self.model.code,
        "address": self.model.address,
      }

      // $rootScope.startLoader();
      GenericService.serviceAction("PATCH", URI, data).then(
        function (response) {
          var manufacture = self.model.manufactureDataList.find(function (type) {
            return type.id == self.model.id;
          });
          manufacture.name = data.name;
          manufacture.code = data.code;
          self.model.detail_address = data.address;

          ClearFields();
          self.model.activeManufacturerId = null;
          self.model.isEdit = false;
        });
    };

    //Method to clear the fileds
    function ClearFields() {
      self.model.code = "";
      self.model.name = "";
      self.model.address = "";
    }

    var ValidateManufacturer = function () {
      var isValid = true;

      if (!self.model.code) {
        isValid = false;
        self.model.ismanufacturerCodeValid = true;
      }
      if (!self.model.name) {
        isValid = false;
        self.model.ismanufacturerValid = true;
      }
      if (!self.model.address) {
        isValid = false;
        self.model.isaddressValid = true;
      }
      return isValid;
    }

    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("manufacturerMaster", {
      url: "/manufacturerMaster",
      templateUrl: "views/master/GlobalMaster/manufacturer-master.html",
      controller: "ManufacturerMaster.Controller",
      controllerAs: "vm",
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("ManufacturerMaster.Controller", manufacturerMasterController)
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