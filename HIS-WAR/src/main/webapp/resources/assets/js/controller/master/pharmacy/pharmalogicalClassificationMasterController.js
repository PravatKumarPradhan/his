(function () {
  "use strict";
  function pharmalogicalclassificationMasterController($scope, $rootScope, $http, CONSTANTS, GenericService, growl) {
    var self = this;
    //Default Constructor
    function initializeController() {
      $rootScope.loginpage = true;
      self.model = {};
      //Methods 
      self.model.ToggleStatus = ToggleStatus;
      self.model.SavePharmalogicalClassification = SavePharmalogicalClassification;
      self.model.ClearFields = ClearFields;
      self.model.PopulatePharmalogicalClassification = PopulatePharmalogicalClassification;
      self.model.ValidatePharmalogicalClassification = ValidatePharmalogicalClassification;
      getPharmalogicalClassification();
    }

    var SavePharmalogicalClassification = function () {
      if (ValidatePharmalogicalClassification()) {
        if (!self.model.id) {
          CreatePharmalogicalClassification();
        } else {
          updatePharmalogicalClassification();
        }
      }
    }

    //Method to get the list of Pharmalogical Classification
    var getPharmalogicalClassification = function () {
      var URI = CONSTANTS.MASTER.UNIT.PHARAMACY.PHARMALOGICAL_CLASSIFICATION_MASTER.PHARMALOGICAL_CLASSIFICATION_API;
      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          self.model.pharmalogicalClassificationList = response.data;
          $rootScope.stopLoader();
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
        });
    }

    //Method to populate Pharmalogical Classification for editing
    function findInRecordInArray(array, key, value) {
      for (var i = 0; i < array.length; i++) {
        if (array[i][key] == value) {
          return array[i];
        }
      }
      return null;
    }

    function getPharmalogicalClassificationById(id) {
      return findInRecordInArray(self.model.pharmalogicalClassificationList, 'id', id)
    }

    function PopulatePharmalogicalClassification(id) {
      self.model.isclassificationCodeValid = false;
      self.model.isclassificationValid = false;
      self.model.activeClassificationId = id;
      var URI = CONSTANTS.MASTER.UNIT.PHARAMACY.PHARMALOGICAL_CLASSIFICATION_MASTER.PHARMALOGICAL_CLASSIFICATION_API + id;
      var data = {};
      GenericService.serviceAction("GET", URI, data).then(
        function (response) {
          var pharmalogicalClassification = getPharmalogicalClassificationById(id);
          pharmalogicalClassification = response.data;
          self.model.code = response.data.code;
          self.model.classification = response.data.classification;
          self.model.status = response.data.status;
          self.model.id = response.data.id;
          self.model.isEdit = true;
        });
    };

    //Method to activate or deactivate Pharmalogical Classification
    var ToggleStatus = function (pharmaClassification) {
      var URI = CONSTANTS.MASTER.UNIT.PHARAMACY.PHARMALOGICAL_CLASSIFICATION_MASTER.PHARMALOGICAL_CLASSIFICATION_API + pharmaClassification.id;
      var data = {
        "status": pharmaClassification.status
      };
      GenericService.serviceAction("PATCH", URI, data).then(
        function (response) {
          $rootScope.stopLoader();
          growl.success('Status changed successfully', { title: response.status });
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
          pharmaClassification.status = !pharmaClassification.status;
          growl.error('Something went wrong', { title: response.status });
        });
    }

    //Method to add the Pharmalogical Classification
    var CreatePharmalogicalClassification = function () {
      var URI = CONSTANTS.MASTER.UNIT.PHARAMACY.PHARMALOGICAL_CLASSIFICATION_MASTER.PHARMALOGICAL_CLASSIFICATION_API;
      var data = {
        "code": self.model.code,
        "classification": self.model.classification,
        "status": true
      }
      $rootScope.startLoader();
      GenericService.serviceAction("POST", URI, data).then(
        function (response) {
          var pharmalogicalClassification = {
            "id": response.data.id,
            "code": self.model.code,
            "classification": self.model.classification,
            "status": true
          };
          self.model.pharmalogicalClassificationList.push(pharmalogicalClassification);
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
    var updatePharmalogicalClassification = function () {
      var URI = CONSTANTS.MASTER.UNIT.PHARAMACY.PHARMALOGICAL_CLASSIFICATION_MASTER.PHARMALOGICAL_CLASSIFICATION_API + self.model.id;
      var data = {
        "code": self.model.code,
        "classification": self.model.classification,
        "status": true
      }
      GenericService.serviceAction("PATCH", URI, data).then(
        function (response) {
          var pharmalogicalClassification = self.model.pharmalogicalClassificationList.find(function (type) {
            return type.id == self.model.id;
          });
          pharmalogicalClassification.code = data.code;
          pharmalogicalClassification.classification = data.classification;
          pharmalogicalClassification.status = data.status;

          ClearFields();
          self.model.activeClassificationId = null;
          self.model.isEdit = false;
        });
    };

    //Method to clear the fileds
    function ClearFields() {
      self.model.code = "";
      self.model.classification = "";
    }

    var ValidatePharmalogicalClassification = function () {
      var isValid = true;

      if (!self.model.code) {
        isValid = false;
        self.model.isclassificationCodeValid = true;
      }
      if (!self.model.classification) {
        isValid = false;
        self.model.isclassificationValid = true;
      }
      return isValid;
    }

    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("pharmacologicalClassificationMaster", {
      url: "/pharmacologicalClassificationMaster",
      templateUrl: 'views/PharmacyInventory/StockAdjustment/pharmacological-classification-master.html',
      controller: "PharmalogicalClassificationController.Controller",
      controllerAs: "vm",
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("PharmalogicalClassificationController.Controller", pharmalogicalclassificationMasterController)
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