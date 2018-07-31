(function () {
  "use strict";
  function otherChargesMasterController($scope, $rootScope, $http, CONSTANTS, GenericService, growl) {
    var self = this;
    //Default Constructor
    function initializeController() {
      $rootScope.loginpage = true;
      self.model = {};
      //Methods 
      self.model.ToggleStatus = ToggleStatus;
      self.model.SaveOtherCharges = SaveOtherCharges;
      self.model.ClearFields = ClearFields;
      self.model.PopulateOtherCharge = PopulateOtherCharge;
      self.model.ValidateOtherCharge = ValidateOtherCharge;
      getOtherCharge();
    }

    var SaveOtherCharges = function () {
      if (ValidateOtherCharge()) {
        if (!self.model.id) {
          CreateOtherCharge();
        } else {
          updateOtherCharge();
        }
      }
    }

    //Method to get the list of Other Charge
    var getOtherCharge = function () {
      var URI = CONSTANTS.MASTER.GLOBAL.PHARAMACY.OTHER_CHARGES_MASTER.OTHER_CHARGES_API;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          self.model.OtherChargesList = response.data;
          $rootScope.stopLoader();
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
        });
    }

    //Method to populate Other Charge for editing
    function findInRecordInArray(array, key, value) {
      for (var i = 0; i < array.length; i++) {
        if (array[i][key] == value) {
          return array[i];
        }
      }
      return null;
    }

    function getOtherChargeById(id) {
      return findInRecordInArray(self.model.OtherChargesList, 'id', id)
    }

    function PopulateOtherCharge(id) {
      self.model.isotherChargeCodeValid = false;
      self.model.isotherChargeDescriptionValid = false;
      self.model.activeOtherChargeId = id;
      var URI = CONSTANTS.MASTER.GLOBAL.PHARAMACY.OTHER_CHARGES_MASTER.OTHER_CHARGES_API + id;
      var data = {};
      GenericService.serviceAction("GET", URI, data).then(
        function (response) {
          var otherCharge = getOtherChargeById(id);
          otherCharge = response.data;
          self.model.code = response.data.code;
          self.model.description = response.data.description;
          self.model.status = response.data.status;
          self.model.id = response.data.id;
          self.model.isEdit = true;
        });
    };

    //Method to activate or deactivate Other Charge
    var ToggleStatus = function (othercharge) {
      var URI = CONSTANTS.MASTER.GLOBAL.PHARAMACY.OTHER_CHARGES_MASTER.OTHER_CHARGES_API + othercharge.id;

      var data = {
        "status": othercharge.status
      };

      GenericService.serviceAction("PATCH", URI, data).then(
        function (response) {
          $rootScope.stopLoader();
          growl.success('Status changed successfully', { title: response.status });
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
          othercharge.status = !othercharge.status;
          growl.error('Something went wrong', { title: response.status });
        });
    }

    //Method to add the OtherCharge
    var CreateOtherCharge = function () {
      var URI = CONSTANTS.MASTER.GLOBAL.PHARAMACY.OTHER_CHARGES_MASTER.OTHER_CHARGES_API;
      var data = {
        "id": self.model.id,
        "code": self.model.code,
        "description": self.model.description,
        "status": true
      }
      $rootScope.startLoader();
      GenericService.serviceAction("POST", URI, data).then(
        function (response) {
          var othercharge = {
            "id": response.data.id,
            "code": self.model.code,
            "description": self.model.description,
            "status": true
          };
          self.model.OtherChargesList.push(othercharge);
          ClearFields();

          $rootScope.stopLoader();
          growl.success('Record saved successfully', { title: response.status });
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
          growl.error('Something went wrong', { title: response.status });
        });
    };

    //Method to update the Other Charge
    var updateOtherCharge = function () {
      var URI = CONSTANTS.MASTER.GLOBAL.PHARAMACY.OTHER_CHARGES_MASTER.OTHER_CHARGES_API + self.model.id;
      var data = {
        "code": self.model.code,
        "description": self.model.description,
      }
      GenericService.serviceAction("PATCH", URI, data).then(
        function (response) {
          var othercharge = self.model.OtherChargesList.find(function (type) {
            return type.id == self.model.id;
          });
          othercharge.code = data.code;
          othercharge.description = data.description;

          ClearFields();
          self.model.activeOtherChargeId = null;
          self.model.isEdit = false;
        });
    };

    //Method to clear the fileds
    function ClearFields() {
      self.model.code = "";
      self.model.description = "";
    }

    var ValidateOtherCharge = function () {
      var isValid = true;

      if (!self.model.code) {
        isValid = false;
        self.model.isotherChargeCodeValid = true;
      }
      if (!self.model.description) {
        isValid = false;
        self.model.isotherChargeDescriptionValid = true;
      }
      return isValid;
    }

    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("otherCharges", {
      url: "/otherCharges",
      templateUrl: "views/master/GlobalMaster/other-charges.html",
      controller: "OtherCharges.Controller",
      controllerAs: "vm",
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("OtherCharges.Controller", otherChargesMasterController)
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