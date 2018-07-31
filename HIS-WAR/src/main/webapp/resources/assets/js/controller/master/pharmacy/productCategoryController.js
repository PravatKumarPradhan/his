(function () {
  "use strict";

  function productCategoryController($scope, $rootScope, $http, CONSTANTS, GenericService, growl) {
    var self = this;
    // var apiUrl = constants.apiurl + constants.unitsApi;

    //Default Constructor
    function initializeController() {
      $rootScope.loginpage = true;
      self.model = {
        PopulateAssetTypeDropdown: [{
          "type": "Select Asset Type"
        }],
        assetCategoryDropdown: [{
          "category": "Select Asset Category Type"
        }],
      };

      //Methods
      self.model.GetProductCategory = GetProductCategory;
      self.model.ToggleStatus = ToggleStatus;
      self.model.SaveProductCategory = SaveProductCategory;
      self.model.PopulateAssetCategoryDropdown = PopulateAssetCategoryDropdown;
      self.model.ClearFields = ClearFields;
      self.model.SavePopup = SavePopup;

      GetProductCategoryList();
      PopulateAssetTypeDropdown();
    }

    //Method to get the list of ProductCategory
    var GetProductCategoryList = function () {
      var URI = CONSTANTS.GLOBAL.MASTER_PRODUCT_CATEGORY_API;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          self.model.productCategoryList = response.data;
          $rootScope.stopLoader();
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
        });
    }

    //Method to Populate Asset Type Dropdown
    var PopulateAssetTypeDropdown = function () {
      var productCategory = self.model;
      productCategory.assetType = productCategory.PopulateAssetTypeDropdown[0];
      self.model.assetCategory = self.model.assetCategoryDropdown[0];

      productCategory.PopulateAssetTypeDropdown.splice(1);
      var URI = CONSTANTS.GLOBAL.ASSET_TYPE_API;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          productCategory.PopulateAssetTypeDropdown = productCategory.PopulateAssetTypeDropdown.concat(response.data);
          $rootScope.stopLoader();
          // PopulateAssetCategoryDropdown();
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
        });

    }

    //Method to Populate Asset Category Dropdown
    var PopulateAssetCategoryDropdown = function (assetTypeId, selectedValue) {
      var assetCategory = self.model;
      assetCategory.assetCategory = assetCategory.assetCategoryDropdown[0];

      // assetCategory.PopulateAssetCategoryDropdown.splice(1);
      var URI = CONSTANTS.GLOBAL.ASSET_CATEGORY_API + assetTypeId.id;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          self.model.assetCategoryDropdown = self.model.assetCategoryDropdown.concat(response.data);
          $rootScope.stopLoader();
          // GetAssetCategories();

          if (selectedValue) {
            var assetCategory = self.model.assetCategoryDropdown.find(function (type) {
              return assetTypeId.assetCategoryId == type.id;
            });
            self.model.assetCategory = assetCategory;
          }
        },
        function (err) {

        });
    }

    //Method to populate ProductCategory for editing
    var GetProductCategory = function (productCategoryId) {
      var URI = CONSTANTS.GLOBAL.MASTER_PRODUCT_CATEGORY_API + productCategoryId;
      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          var assetType = self.model.PopulateAssetTypeDropdown.find(function (type) {
            return response.data.assetTypeId == type.id;
          });
          self.model.assetType = assetType;
          PopulateAssetCategoryDropdown(response.data, true);
          self.model.id = response.data.id;
          self.model.productCategoryCode = response.data.code;
          self.model.productCategoryName = response.data.category;
        });
      $rootScope.stopLoader();
    };

    //Method to activate or deactivate ProductCategory
    var ToggleStatus = function (productCategory) {
      var URI = CONSTANTS.GLOBAL.PRODUCT_CATEGORY_API + productCategory.id;

      var data = {
        "status": productCategory.status
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

    //Method for popup
    var SavePopup = function () {
      var popup = angular.element('#confirmSave');
      popup.modal('show');
    }

    //Method to add or update the ProductCategory
    var SaveProductCategory = function () {
      if (Validate()) {
        if (!self.model.id) {
          CreateProductCategory();
        } else {
          UpdateProductCategory();
        }
      }
    };

    //Method to add the ProductCategory
    var CreateProductCategory = function () {
      var URI = CONSTANTS.GLOBAL.PRODUCT_CATEGORY_API;

      var data = {
        "assetCategoryId": self.model.assetCategory.id,
        "code": self.model.productCategoryCode,
        "category": self.model.productCategoryName,
        "status": true
      }
      var assetType = self.model.PopulateAssetTypeDropdown.find(function (type) {
        return type.id == self.model.assetType.id;
      });
      var assetCategoryData = self.model.assetCategoryDropdown.find(function (type) {
        return type.id == self.model.assetCategory.id;
      });
      $rootScope.startLoader();
      GenericService.serviceAction("POST", URI, data).then(
        function (response) {
          var productCategory = {
            "id": response.data.id,
            "code": self.model.productCategoryCode,
            "category": self.model.productCategoryName,
            "assetType": assetType.type,
            "assetCategory": assetCategoryData.category,
            "status": true
          };
          self.model.productCategoryList.push(productCategory);
          ClearFields();

          $rootScope.stopLoader();
          growl.success('Record saved successfully');
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
          growl.error('Something went wrong');
        });
    };

    //Method to update the  ProductCategory
    var UpdateProductCategory = function () {
      var URI = CONSTANTS.GLOBAL.PRODUCT_CATEGORY_API + self.model.id;

      var data = {
        "assetCategoryId": self.model.assetCategory.id,
        "code": self.model.productCategoryCode,
        "category": self.model.productCategoryName,
        "status": true
      }

      // $rootScope.startLoader();
      GenericService.serviceAction("PATCH", URI, data).then(
        function (response) {
          var assetType = self.model.PopulateAssetTypeDropdown.find(function (type) {
            return type.id == self.model.assetType.id;
          });
          var assetCategoryData = self.model.assetCategoryDropdown.find(function (type) {
            return type.id == self.model.assetCategory.id;
          });
          var productCategory = self.model.productCategoryList.find(function (type) {
            return type.id == self.model.id;
          });

          productCategory.code = data.code;
          productCategory.category = data.category;
          productCategory.assetType = assetType.type;
          productCategory.assetCategory = assetCategoryData.category;

          ClearFields();
          self.model.activeProductCategoryId = undefined;
          self.model.isEdit = false;
          $rootScope.stopLoader();
          growl.success(response.data.message);
        });
    };

    //Method to clear the fileds
    var ClearFields = function () {
      self.model.assetType = self.model.PopulateAssetTypeDropdown[0];
      self.model.assetCategoryDropdown = [self.model.assetCategoryDropdown[0]];
      self.model.assetCategory = self.model.assetCategoryDropdown[0];
      self.model.productCategoryCode = '';
      self.model.productCategoryName = '';
    }

    var Validate = function () {
      var isValid = true;

      if (!self.model.assetType) {
        isValid = false;
        self.model.isAssetTypeIdValid = true;
      }
      if (!self.model.assetCategory) {
        isValid = false;
        self.model.isAssetCategoryIdValid = true;
      }
      if (!self.model.productCategoryCode) {
        isValid = false;
        self.model.isProductCategoryCodeValid = true;
      }
      if (!self.model.productCategoryName) {
        isValid = false;
        self.model.isProductCategoryNameValid = true;
      }

      return isValid;
    }

    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("pharmacyproductCategoryMaster", {
      url: "/pharmacyproductCategoryMaster",
      // data: { title: "About", activetab : "about" },
      templateUrl: 'views/master/GlobalMaster/pharmacy-product-category-master.html',
      controller: "ProductCategory.Controller",
      controllerAs: "vm"
      // authenticate: false
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("ProductCategory.Controller", productCategoryController);
})();


