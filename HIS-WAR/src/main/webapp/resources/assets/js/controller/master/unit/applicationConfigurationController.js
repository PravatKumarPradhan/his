(function () {
  "use strict";

  function applicationConfigurationController($scope, $rootScope, $http, CONSTANTS, GenericService, growl) {
    var self = this;
    //Default Constructor
    function initializeController() {
      $rootScope.loginpage = true;
      self.model = {
        taxList: [{
          "name": "Select Tax Type"
        }]
      };

      //Methods

      // self.model.PopulateGenericName = PopulateGenericName;
    //  self.model.ToggleStatus = ToggleStatus;
      self.model.isItemNameFlag = isItemNameFlag;
      self.model.isIssueFlag = isIssueFlag;
      self.model.SaveAppConfig = SaveAppConfig;
      self.model.ClearFields = ClearFields;
      self.model.GetGenericData = GetGenericData;
      self.model.ValidateGenericMaster = ValidateGenericMaster;
      self.model.PopulateTaxDropdown = PopulateTaxDropdown;
      self.model.CreateAppConf = CreateAppConf;
      self.model.UpdateAppConf = UpdateAppConf;
      self.model.isItemName = "false";
      self.model.isIssue = "false";
      // self.model.Popup = Popup;

      // getAssetType();
      // getAssetCategory();
      // getProductCategoryList();
      // GetGenericList();
      PopulateTaxDropdown();
    }

    //Method to get the list of Generic 
    var GetGenericList = function () {

      var URI = CONSTANTS.GLOBAL.GENERIC_MASTER_API;
      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          self.model.genericList = response.data;
          $rootScope.stopLoader();
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
        });
    }


    var PopulateTaxDropdown = function () {
      var appConf = self.model;
      appConf.tax = appConf.taxList[0];

      appConf.taxList.splice(1);
      var URI = CONSTANTS.MASTER.UNIT.APPLICATION_CONFIGURATION.TAX_DROPDOWN;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
            appConf.taxList = appConf.taxList.concat(response.data);
          $rootScope.stopLoader();
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
        });

    }


    //Method to populate ProductCategory for editing
    var GetGenericData = function (genericId) {
      var URI = CONSTANTS.GLOBAL.GENERIC_MASTER_API + genericId;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          var assetType = self.model.assetTypeList.find(function (type) {
            return response.data.assetTypeId == type.id;
          });
          self.model.assetType = assetType;
          PopulateAssetCategoryDropdown(response.data, true);
          self.model.genericId = response.data.id;
          self.model.genericCode = response.data.code;
          self.model.genericName = response.data.name;
          $rootScope.stopLoader();
        });
    };

    var isItemNameFlag = function (flag) {
        if (flag) {
            self.model.isItemName = true;
        } else {
            self.model.isItemName = false;
        }
    }
    var isIssueFlag = function (flag) {
        if (flag) {
            self.model.isIssue = true;
        } else {
            self.model.isIssue = false;
        }
    }


    //Method to add the Generic
    var CreateAppConf = function () {
      var URI = CONSTANTS.MASTER.UNIT.APPLICATION_CONFIGURATION.APPLICATION_CONFIGURATION_SAVE_API;

      var data = {
        "itemName": self.model.isItemName,
        "defaultTaxId": self.model.tax.id,
        "issueMaterialPickerRequired": self.model.isIssue
      }

      var taxData = self.model.taxList.find(function (type) {
        return type.id == self.model.tax.id;
      });

      $rootScope.startLoader();
      GenericService.serviceAction("POST", URI, data).then(
        function (response) {
          var taxComponentData = {
            "id": response.data.id,
            "defaultTaxId": taxData.component,
            "itemName": self.model.isItemName,
            "issueMaterialPickerRequired": self.model.isIssue
          };
          self.model.appConfId = response.data.id, 
        //  self.model.genericList.push(genericData);
         // ClearFields();

          $rootScope.stopLoader();
          growl.success('Record saved successfully');
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
          growl.error('Something went wrong');
        });
    };

    //Method to add or update the Generic
    var SaveAppConfig = function () {
        if (!self.model.appConfId) {
          CreateAppConf();
        } else {
          UpdateAppConf();
        }
    };
    //Method to update the  Generic
    var UpdateAppConf = function () {
      var URI = CONSTANTS.MASTER.UNIT.APPLICATION_CONFIGURATION.APPLICATION_CONFIGURATION_UPDATE_API + self.model.appConfId;

      var data = {
        "itemName": self.model.isItemName,
        "defaultTaxId": self.model.tax.id,
        "issueMaterialPickerRequired": self.model.isIssue
      }
      // $rootScope.startLoader();
      GenericService.serviceAction("PATCH", URI, data).then(
        function (response) {
          $rootScope.stopLoader();
          growl.success(response.data.message);
          // self.model.activeProductCategoryId = undefined;
          // self.model.isEdit = false;
        });
    };



    //Method to clear the fileds
    function ClearFields() {
      self.model.tax = self.model.taxList[0];
      self.model.isItemName = "";
      self.model.isIssue = "";
      self.model.taxList.splice(1);
    }
    var ValidateGenericMaster = function () {
      var isValid = true;

      if (!self.model.assetType.id) {
        isValid = false;
        self.model.isAssetTypeIdValid = true;
      }
      if (!self.model.assetCategory.id) {
        isValid = false;
        self.model.isValidCode = true;
      }
      if (!self.model.productCategory.id) {
        isValid = false;
        self.model.isValidProductCat = true;
      }
      if (!self.model.genericCode) {
        isValid = false;
        self.model.isValidGenericCode = true;
      }
      if (!self.model.genericName) {
        isValid = false;
        self.model.isValidGenericName = true;
      }
      return isValid;
    }
    initializeController();
  }


  function config($stateProvider) {
    $stateProvider.state("applicationConfiguration", {
      url: "/applicationConfiguration",
      templateUrl: 'views/master/UnitMaster/configuration/application-master.html',
      controller: "ApplicationConfiguration.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("ApplicationConfiguration.Controller", applicationConfigurationController);
})();