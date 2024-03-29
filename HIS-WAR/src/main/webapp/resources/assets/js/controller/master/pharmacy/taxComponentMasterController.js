(function () {
  "use strict";
  function taxComponentMasterController($scope, $rootScope, $http, CONSTANTS, GenericService, growl) {
    var self = this;
    //Default Constructor
    function initializeController() {
      $rootScope.loginpage = true;
      self.model = {};
      //Methods 
      self.model.ToggleStatus = ToggleStatus;
      self.model.SaveTaxComponents = SaveTaxComponents;
      self.model.ClearFields = ClearFields;
      self.model.PopulateTaxComponent = PopulateTaxComponent;
      self.model.ValidateTaxComponent = ValidateTaxComponent;
      getTaxComponent();
    }

    var SaveTaxComponents = function () {
      if (ValidateTaxComponent()) {
        if (!self.model.id) {
          CreateTaxComponent();
        } else {
          updateTaxComponent();
        }
      }
    }

    //Method to get the list of Tax Component
    var getTaxComponent = function () {
      var URI = CONSTANTS.MASTER.GLOBAL.PHARAMACY.TAX_COMPONENT_MASTER.TAX_COMPONENT_API;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          self.model.taxComponentList = response.data;
          $rootScope.stopLoader();
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
        });
    }

    //Method to populate Tax Component for editing
    function findInRecordInArray(array, key, value) {
      for (var i = 0; i < array.length; i++) {
        if (array[i][key] == value) {
          return array[i];
        }
      }
      return null;
    }

    function getTaxComponentById(id) {
      return findInRecordInArray(self.model.taxComponentList, 'id', id)
    }

    function PopulateTaxComponent(id) {
      self.model.istaxComponentCodeValid = false;
      self.model.isdescriptionValid = false;
      self.model.activeTaxComponentId = id;
      var URI = CONSTANTS.MASTER.GLOBAL.PHARAMACY.TAX_COMPONENT_MASTER.TAX_COMPONENT_API + id;
      var data = {};
      GenericService.serviceAction("GET", URI, data).then(
        function (response) {
          var taxComponent = getTaxComponentById(id);
          taxComponent = response.data;
          self.model.code = response.data.code;
          self.model.component = response.data.component;
          self.model.status = response.data.status;
          self.model.id = response.data.id;
          self.model.isEdit = true;
        });
    };

    //Method to activate or deactivate Tax Component
    var ToggleStatus = function (taxComponent) {

      var URI = CONSTANTS.MASTER.GLOBAL.PHARAMACY.TAX_COMPONENT_MASTER.TAX_COMPONENT_API + taxComponent.id;

      var data = {
        "status": taxComponent.status
      };

      GenericService.serviceAction("PATCH", URI, data).then(
        function (response) {
          $rootScope.stopLoader();
          growl.success('Status changed successfully', { title: response.status });
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
          taxComponent.status = !taxComponent.status;
          growl.error('Something went wrong', { title: response.status });
        });
    }

    //Method to add the TaxComponent
    var CreateTaxComponent = function () {
      var URI = CONSTANTS.MASTER.GLOBAL.PHARAMACY.TAX_COMPONENT_MASTER.TAX_COMPONENT_API;
      var data = {
        "id": self.model.id,
        "code": self.model.code,
        "component": self.model.component,
        "status": true
      }
      $rootScope.startLoader();
      GenericService.serviceAction("POST", URI, data).then(
        function (response) {
          var taxComponent = {
            "id": response.data.id,
            "code": self.model.code,
            "component": self.model.component,
            "status": true
          };
          self.model.taxComponentList.push(taxComponent);
          ClearFields();

          $rootScope.stopLoader();
          growl.success('Record saved successfully', { title: response.status });
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
          growl.error('Something went wrong', { title: response.status });
        });
    };

    //Method to update the Tax Component
    var updateTaxComponent = function () {
      var URI = CONSTANTS.MASTER.GLOBAL.PHARAMACY.TAX_COMPONENT_MASTER.TAX_COMPONENT_API + self.model.id;

      var data = {
        "code": self.model.code,
        "component": self.model.component,
      }

      // $rootScope.startLoader();
      GenericService.serviceAction("PATCH", URI, data).then(
        function (response) {
          var taxComponent = self.model.taxComponentList.find(function (type) {
            return type.id == self.model.id;
          });
          taxComponent.code = data.code;
          taxComponent.component = data.component;

          ClearFields();
          self.model.activeTaxComponentId = null;
          self.model.isEdit = false;
        });
    };

    //Method to clear the fileds
    function ClearFields() {
      self.model.code = "";
      self.model.component = "";
    }

    var ValidateTaxComponent = function () {
      var isValid = true;
      if (!self.model.code) {
        isValid = false;
        self.model.istaxComponentCodeValid = true;
      }
      if (!self.model.component) {
        isValid = false;
        self.model.isdescriptionValid = true;
      }
      return isValid;
    }

    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("taxComponentMaster", {
      url: "/taxComponentMaster",
      templateUrl: 'views/master/GlobalMaster/tax-component-master.html',
      controller: "TaxComponents.Controller",
      controllerAs: "vm",
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("TaxComponents.Controller", taxComponentMasterController)
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