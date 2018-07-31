(function () {
  "use strict";
  function assetCategoryMasterController($scope, $rootScope, $http, CONSTANTS, GenericService, growl) {
    var self = this;

    //Default Constructor
    function initializeController() {
      $rootScope.loginpage = true;
      self.model = {
        categoryDropdown: [{
          "type": "Select Asset Type"
        }],
      };
      self.model.ShowConfirmPopup = ShowConfirmPopup;
      self.model.GetAssetCategory = GetAssetCategory;
      self.model.ToggleStatus = ToggleStatus;
      self.model.SaveAssetCategory = SaveAssetCategory;
      self.model.ClearFields = ClearFields;
      self.model.ValidateAssetCategory = ValidateAssetCategory;
      PopulateDropdown();
    }

    //Get Asset type
    var PopulateDropdown = function () {
      var assetCategory = self.model;
      assetCategory.assetType = assetCategory.categoryDropdown[0];

      assetCategory.categoryDropdown.splice(1);
      var URI = CONSTANTS.GLOBAL.ASSET_TYPE_API;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          assetCategory.categoryDropdown = assetCategory.categoryDropdown.concat(response.data);
          $rootScope.stopLoader();
          GetAssetCategories();
        },
        function (err) {

        });
    }

    var GetAssetCategories = function () {
      var URI = CONSTANTS.MASTER.UNIT.FIXED_ASSET.ASSET_CATEGORIES_API;
      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          self.model.assetCategories = response.data;
          $rootScope.stopLoader();
        },
        function (err) {
          $rootScope.stopLoader();
        });
    }

    var GetAssetCategory = function (categoryId) {
      self.model.activeCategoryId = categoryId;
      var URI = CONSTANTS.MASTER.UNIT.FIXED_ASSET.ASSET_CATEGORIES_API + categoryId;
      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          self.model.categoryId = response.data.id;
          var assetType = self.model.categoryDropdown.find(function (type) {
            return response.data.assetTypeId == type.id;
          });
          self.model.assetType = assetType;
          self.model.categoryCode = response.data.code;
          self.model.categoryDescription = response.data.category;
          $rootScope.stopLoader();
        },
        function (err) {
          $rootScope.stopLoader();
        });
    };

    var ToggleStatus = function (category) {
      var URI = CONSTANTS.MASTER.UNIT.FIXED_ASSET.ASSET_CATEGORIES_API + category.id;
      var data = {
        "status": category.status
      };
      $rootScope.startLoader();
      GenericService.serviceAction("PATCH", URI, data).then(
        function (response) {
          $rootScope.stopLoader();
          growl.success(response.data.message, { title: response.status });
        }, function (err) {
          $rootScope.stopLoader();
          growl.error('Something Went Wrong', { title: err.status });
        });
    };

    var ShowConfirmPopup = function () {
      var popup = angular.element('#confirmSave');
      popup.modal('show');
    }

    var SaveAssetCategory = function () {
      if (ValidateAssetCategory()) {
        if (!self.model.categoryId) {
          CreateAssetCategory();
        } else {
          UpdateAssetCategory();
        }
      }
    }

    var ValidateAssetCategory = function () {
      var isValid = true;
      if (!self.model.categoryCode) {
        isValid = false;
        self.model.isValidCode = true;
      }
      if (!self.model.assetType) {
        isValid = false;
        self.model.isValidType = true;
      }
      if (!self.model.categoryDescription) {
        isValid = false;
        self.model.isValidCategory = true;
      }

      return isValid;
    }

    var CreateAssetCategory = function () {
      var URI = CONSTANTS.MASTER.UNIT.FIXED_ASSET.ASSET_CATEGORIES_API;
      var data = {
        "assetTypeId": self.model.assetType.id,
        "code": self.model.categoryCode,
        "category": self.model.categoryDescription,
        "status": true
      }
      $rootScope.startLoader();
      GenericService.serviceAction("POST", URI, data).then(
        function (response) {
          var assetType = self.model.categoryDropdown.find(function (type) {
            return type.id == self.model.assetType.id;
          });
          var insertedData = {
            "id": response.data.id,
            "assetType": assetType.type,
            "code": data.code,
            "category": data.category,
            "status": data.status
          };
          self.model.assetCategories.push(insertedData);
          ClearFields();
          $rootScope.stopLoader();
          growl.success(response.data.message);
        },
        function (err) {
          $rootScope.stopLoader();
          growl.error(err.data.message);
        });
    };

    var UpdateAssetCategory = function () {
      var URI = CONSTANTS.MASTER.UNIT.FIXED_ASSET.ASSET_CATEGORIES_API + self.model.activeCategoryId;
      var data = {
        "assetTypeId": self.model.assetType.id,
        "code": self.model.categoryCode,
        "category": self.model.categoryDescription,
        "status": true
      }
      $rootScope.startLoader();
      GenericService.serviceAction("PATCH", URI, data).then(
        function (response) {
          var assetCategory = self.model.assetCategories.find(function (category) {
            return category.id == self.model.activeCategoryId;
          });
          var assetType = self.model.categoryDropdown.find(function (type) {
            return type.id == data.assetTypeId;
          });
          assetCategory.assetType = assetType.type;
          assetCategory.code = data.code;
          assetCategory.category = data.category;
          ClearFields();
          $rootScope.stopLoader();
          growl.success(response.data.message);
        },
        function (err) {
          $rootScope.stopLoader();
          growl.error('Something Went Wrong');
        });
    };

    var ClearFields = function () {
      self.model.assetType = self.model.categoryDropdown[0];
      self.model.categoryCode = undefined;
      self.model.categoryDescription = undefined;
      self.model.categoryId = undefined;
      self.model.activeCategoryId = null;
    }

    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("assetCategoryMaster", {
      url: "/assetCategoryMaster",
      templateUrl: 'views/master/GlobalMaster/AssetManagenent/Master/asset-category-master.html',
      controller: "AssetCategoryMaster.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("AssetCategoryMaster.Controller", assetCategoryMasterController)
})();