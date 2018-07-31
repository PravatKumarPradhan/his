(function () {
  "use strict";

  function genericMasterController($scope, $rootScope, $http, CONSTANTS, GenericService, growl) {
    var self = this;
    //Default Constructor
    function initializeController() {
      $rootScope.loginpage = true;
      self.model = {
        assetTypeList: [{
          "type": "Select Asset Type"
        }],
        assetCategoryList: [{
          "category": "Select Asset Category Type"
        }],
        productCategoryList: [{
          "category": "Select Product Category"
        }],
      };

      //Methods

      // self.model.PopulateGenericName = PopulateGenericName;
      self.model.ToggleStatus = ToggleStatus;
      self.model.SaveGeneric = SaveGeneric;
      self.model.PopulateAssetTypeDropdown = PopulateAssetTypeDropdown;
      self.model.PopulateAssetCategoryDropdown = PopulateAssetCategoryDropdown;
      self.model.PopulateProductCategoryDropdown = PopulateProductCategoryDropdown;
      self.model.ClearFields = ClearFields;
      self.model.GetGenericData = GetGenericData;
      self.model.ValidateGenericMaster = ValidateGenericMaster;
      // self.model.Popup = Popup;

      // getAssetType();
      // getAssetCategory();
      // getProductCategoryList();
      // GetGenericList();
      PopulateAssetTypeDropdown();
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

    //Method to get the list of ProductCategory
    var PopulateProductCategoryDropdown = function (assetCategoryId, selectedValue) {
      var genericMaster = self.model;
      genericMaster.productCategory = genericMaster.productCategoryList[0];

      genericMaster.productCategoryList.splice(1);
      var URI = CONSTANTS.GLOBAL.PRODUCT_CATEGORY_API + (selectedValue ? assetCategoryId.assetCategoryId : assetCategoryId.id);

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          genericMaster.productCategoryList = genericMaster.productCategoryList.concat(response.data);
          $rootScope.stopLoader();
          // GetAssetCategories();

          if (selectedValue) {
            debugger
            var productCategory = genericMaster.productCategoryList.find(function (type) {
              return assetCategoryId.productCategoryId == type.id;
            });
            genericMaster.productCategory = productCategory;
          }
        },
        function (err) {

        });
    }

    var PopulateAssetTypeDropdown = function () {
      var genericMaster = self.model;
      genericMaster.assetType = genericMaster.assetTypeList[0];
      genericMaster.assetCategory = genericMaster.assetCategoryList[0];
      genericMaster.productCategory = genericMaster.productCategoryList[0];

      genericMaster.assetTypeList.splice(1);
      self.model.assetCategoryList.splice(1);
      self.model.productCategoryList.splice(1);
      var URI = CONSTANTS.GLOBAL.ASSET_TYPE_API;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          genericMaster.assetTypeList = genericMaster.assetTypeList.concat(response.data);
          GetGenericList();
          $rootScope.stopLoader();
          // PopulateAssetCategoryDropdown();
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
        });

    }

    //Method to Populate Asset Category Dropdown
    var PopulateAssetCategoryDropdown = function (assetTypeId, selectedValue) {
      var genericMaster = self.model;
      genericMaster.assetCategory = self.model.assetCategoryList[0];
      genericMaster.assetCategoryList.splice(1);
      self.model.productCategoryList.splice(1);
      var URI = CONSTANTS.GLOBAL.ASSET_CATEGORY_API + (selectedValue ? assetTypeId.assetTypeId : assetTypeId.id);

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          genericMaster.assetCategoryList = genericMaster.assetCategoryList.concat(response.data);
          $rootScope.stopLoader();
          if (selectedValue) {
            var assetCategory = genericMaster.assetCategoryList.find(function (type) {
              return assetTypeId.assetCategoryId == type.id;
            });
            genericMaster.assetCategory = assetCategory;
            PopulateProductCategoryDropdown(assetTypeId, true);
          }
        },
        function (err) {

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

    var ToggleStatus = function (generic) {
      var URI = CONSTANTS.GLOBAL.GENERIC_MASTER_API + generic.id;

      var data = {
        "status": generic.status
      };

      GenericService.serviceAction("PATCH", URI, data).then(
        function (response) {
          $rootScope.stopLoader();
          growl.success('Status changed successfully');
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
          growl.error('Something went wrong');
        });
    }


    //Method to add the Generic
    var CreateGeneric = function () {
      var URI = CONSTANTS.GLOBAL.GENERIC_MASTER_API;

      var data = {
        "productCategoryId": self.model.productCategory.id,
        "code": self.model.genericCode,
        "name": self.model.genericName,
        "status": true
      }

      var assetTypeData = self.model.assetTypeList.find(function (type) {
        return type.id == self.model.assetType.id;
      });
      var assetCategoryData = self.model.assetCategoryList.find(function (type) {
        return type.id == self.model.assetCategory.id;
      });
      var productCategoryData = self.model.productCategoryList.find(function (type) {
        return type.id == self.model.productCategory.id;
      });

      $rootScope.startLoader();
      GenericService.serviceAction("POST", URI, data).then(
        function (response) {
          var genericData = {
            "id": response.data.id,
            "assetType": assetTypeData.type,
            "assetCategory": assetCategoryData.category,
            "productCategory": productCategoryData.category,
            "code": self.model.genericCode,
            "name": self.model.genericName,
            "status": true
          };
          self.model.genericList.push(genericData);
          ClearFields();

          $rootScope.stopLoader();
          growl.success('Record saved successfully');
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
          growl.error('Something went wrong');
        });
    };

    //Method to add or update the Generic
    var SaveGeneric = function () {
      if (ValidateGenericMaster()) {
        if (!self.model.genericId) {
          CreateGeneric();
        } else {
          UpdateGeneric();
        }
      }
    };
    //Method to update the  Generic
    var UpdateGeneric = function () {
      var URI = CONSTANTS.GLOBAL.GENERIC_MASTER_API + self.model.genericId;

      var data = {
        "productCategoryId": self.model.productCategory.id,
        "code": self.model.genericCode,
        "name": self.model.genericName,
        "status": true
      }
      // $rootScope.startLoader();
      GenericService.serviceAction("PATCH", URI, data).then(
        function (response) {
          var genericData = self.model.genericList.find(function (type) {
            return type.id == self.model.genericId;
          });
          var assetTypeData = self.model.assetTypeList.find(function (type) {
            return type.id == self.model.assetType.id;
          });
          var assetCategoryData = self.model.assetCategoryList.find(function (type) {
            return type.id == self.model.assetCategory.id;
          });
          var productCategoryData = self.model.productCategoryList.find(function (type) {
            return type.id == self.model.productCategory.id;
          });

          genericData.code = data.code;
          genericData.name = data.name;
          genericData.category = productCategoryData.category;
          genericData.assetType = assetTypeData.type;
          genericData.assetCategory = assetCategoryData.category;

          ClearFields();
          $rootScope.stopLoader();
          growl.success(response.data.message);
          // self.model.activeProductCategoryId = undefined;
          // self.model.isEdit = false;
        });
    };



    //Method to clear the fileds
    function ClearFields() {
      self.model.assetType = self.model.assetTypeList[0];
      self.model.assetCategory = [self.model.assetCategoryList[0]];
      self.model.assetCategory = self.model.assetCategoryList[0];
      self.model.productCategory = [self.model.productCategoryList[0]];
      self.model.productCategory = self.model.productCategoryList[0];
      self.model.genericCode = "";
      self.model.genericName = "";
      self.model.assetCategoryList.splice(1);
      self.model.productCategoryList.splice(1);
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
    $stateProvider.state("genericMaster", {
      url: "/genericMaster",
      templateUrl: 'views/master/GlobalMaster/AssetManagenent/Master/generic-master.html',
      controller: "GenericMaster.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("GenericMaster.Controller", genericMasterController);
})();