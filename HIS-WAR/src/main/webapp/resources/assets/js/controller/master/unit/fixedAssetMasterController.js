(function () {
    "use strict";
    function fixedAssetMasterController($scope, $rootScope, $http, $filter, CONSTANTS, GenericService, growl, commonDetailService) {
      var self = this;

      //Default Constructor
      function initializeController() {
        $rootScope.loginpage = true;
        self.model = {
          itemDetails: commonDetailService.getDataId(),
          itemId: 0,
          assetTypeList: [{
            "type": "Select Asset Type"
          }],
          assetCategoryList: [{
            "category": "Select Asset Category Type"
          }],
          productCategoryList: [{
            "category": "Select Product Category"
          }],
          fixedAssetTypeList: [{
            "type": "Select Fixed Asset Type"
          }],
          manufacturerSearch: '',
          prevSearch: '',
          mPrevSearch: '',
          manufacturerNames: []
        };
        self.model.SearchManufacturer = SearchManufacturer;
        self.model.AddManufacturer = AddManufacturer;
        self.model.SaveFixedAsset = SaveFixedAsset;
        self.model.saveFixedAssetMaster = saveFixedAssetMaster;
        self.model.CreateUpdateFixedAssetType = CreateFixedAssetType;
        self.model.UpdateFixedAssetType = UpdateFixedAssetType;
        self.model.PopulateAssetTypeDropdown = PopulateAssetTypeDropdown;
        self.model.PopulateAssetCategoryDropdown = PopulateAssetCategoryDropdown;
        self.model.PopulateProductCategoryDropdown = PopulateProductCategoryDropdown;
        self.model.PopulateFixedAssetType = PopulateFixedAssetType;
        self.model.getFixedAssetComponent = getFixedAssetComponent;
        getFixedAssetComponent();
        PopulateFixedAssetType();
        
      }

     //Method to get fixed asset type
    var getFixedAssetComponent = function () {
      var URI = CONSTANTS.MASTER.UNIT.FIXED_ASSET.FIXED_ASSET_MASTER;
      PopulateAssetTypeDropdown();
      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          self.model.fixedAssetList = response.data;
          $rootScope.stopLoader();
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
        });

    }

    
    //Method to populate Fixed Asset Master for editing
    var GetFixedAssetMasterData = function (genericId) {
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
      // var PopulateDropdown = function () {
      //   var URI = CONSTANTS.GLOBAL.ASSET_TYPE_API;
      //   GenericService.serviceAction("GET", URI, {}).then(
      //     function (response) {
      //       self.model.categoryDropdown = response.data;
      //       // GetAssetCategories();
      //     },
      //     function (err) {

      //     }
      //   );

      //   self.model.itemId = self.model.itemDetails.id;
      //   self.model.itemName = self.model.itemDetails.itemName;
      //   self.model.itemCode = self.model.itemDetails.itemCode;
      // }

       //Method to get the list of ProductCategory
    var PopulateProductCategoryDropdown = function (assetCategoryId, selectedValue) {
      var fixedAssetMaster = self.model;
      fixedAssetMaster.productCategory = fixedAssetMaster.productCategoryList[0];

      fixedAssetMaster.productCategoryList.splice(1);
      var URI = CONSTANTS.GLOBAL.PRODUCT_CATEGORY_API + (selectedValue ? assetCategoryId.assetCategoryId : assetCategoryId.id);

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          fixedAssetMaster.productCategoryList = fixedAssetMaster.productCategoryList.concat(response.data);
          $rootScope.stopLoader();
          // GetAssetCategories();

          if (selectedValue) {
            debugger
            var productCategory = fixedAssetMaster.productCategoryList.find(function (type) {
              return assetCategoryId.productCategoryId == type.id;
            });
            fixedAssetMaster.productCategory = productCategory;
          }
        },
        function (err) {

        });
           self.model.itemId = self.model.itemDetails.id;
           self.model.itemName = self.model.itemDetails.itemName;
           self.model.itemCode = self.model.itemDetails.itemCode;
    }

    var PopulateAssetTypeDropdown = function () {
      var fixedAssetMaster = self.model;
      fixedAssetMaster.assetType = fixedAssetMaster.assetTypeList[0];
      fixedAssetMaster.assetCategory = fixedAssetMaster.assetCategoryList[0];
      fixedAssetMaster.productCategory = fixedAssetMaster.productCategoryList[0];

      fixedAssetMaster.assetTypeList.splice(1);
      self.model.assetCategoryList.splice(1);
      self.model.productCategoryList.splice(1);
      var URI = CONSTANTS.GLOBAL.ASSET_TYPE_API;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          fixedAssetMaster.assetTypeList = fixedAssetMaster.assetTypeList.concat(response.data);
         // GetGenericList();
          $rootScope.stopLoader();
          // PopulateAssetCategoryDropdown();
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
        });

    }

    //Method to Populate Asset Category Dropdown
    var PopulateAssetCategoryDropdown = function (assetTypeId, selectedValue) {
      var fixedAssetMaster = self.model;
      fixedAssetMaster.assetCategory = self.model.assetCategoryList[0];
      fixedAssetMaster.assetCategoryList.splice(1);
      self.model.productCategoryList.splice(1);
      var URI = CONSTANTS.GLOBAL.ASSET_CATEGORY_API + (selectedValue ? assetTypeId.assetTypeId : assetTypeId.id);

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          fixedAssetMaster.assetCategoryList = fixedAssetMaster.assetCategoryList.concat(response.data);
          $rootScope.stopLoader();
          if (selectedValue) {
            var assetCategory = fixedAssetMaster.assetCategoryList.find(function (type) {
              return assetTypeId.assetCategoryId == type.id;
            });
            fixedAssetMaster.assetCategory = assetCategory;
            PopulateProductCategoryDropdown(assetTypeId, true);
          }
        },
        function (err) {

        });
    }

       //Method to get the list of ProductCategory
       var PopulateFixedAssetType = function () {
        var fixedAssetMaster = self.model;
        fixedAssetMaster.type = fixedAssetMaster.fixedAssetTypeList[0];
  
        fixedAssetMaster.fixedAssetTypeList.splice(1);
        var URI = CONSTANTS.MASTER.UNIT.FIXED_ASSET.TYPE_DROPDOWN_API;
        $rootScope.startLoader();
        GenericService.serviceAction("GET", URI, {}).then(
          function (response) {
            fixedAssetMaster.fixedAssetTypeList = fixedAssetMaster.fixedAssetTypeList.concat(response.data);
            $rootScope.stopLoader();
            // GetAssetCategories();
          },
          function (err) {
  
          });
      }

        //Method to add or update the Generic
    var saveFixedAssetMaster = function () {
      debugger
      if (ValidateFixedAssetMaster()) {
        if (!self.model.fixedAssetTypeId) {
          CreateFixedAsset();
        } else {
          UpdateFixedAsset();
        }
      }
    };

    var SearchManufacturer = function(search) {
      if (search.length < 3) return;
      if ((!self.model.mPrevSearch || self.model.mPrevSearch != search) && (search.length == 3)) {
        self.model.mPrevSearch = search;
        var URI = CONSTANTS.GLOBAL.MANUFACTURER_SEARCH_API + search;

        $rootScope.startLoader();
        return GenericService.serviceAction("GET", URI, {})
        .then(function (response) {
          if (!!response.data && response.data.length > 0) {
            self.model.manufacturerNames = response.data;
            $rootScope.stopLoader();
            return $filter('filter')(self.model.manufacturerNames, {
              $: search
            });
          } else {
            $rootScope.stopLoader();
            return manufacturerNotFound(search);
          }
        }, function (err) {
          $rootScope.stopLoader();
          return manufacturerNotFound(search);
        });
      } else {
        if (!!self.model.manufacturerNames && self.model.manufacturerNames.length > 0 && self.model.manufacturerNames[0].manufacturerFound != undefined && !self.model.manufacturerNames[0].manufacturerFound) {
          return manufacturerNotFound(search);
        } else {
          return $filter('filter')(self.model.manufacturerNames, {
            $: search
          });
        }
      }
    }

    var manufacturerNotFound = function (search) {
      var item = {
        "manufacturerFound": false,
        "detail": "Manufacturer Not Found with Name " + search,
        "search": search
      };
      self.model.manufacturerNames = [item];
      return self.model.manufacturerNames;
    }

    var AddManufacturer = function (manufacture) {
      self.model.manufacturer = manufacture.manufacturerName;
      self.model.manufactureId = manufacture.id;
    }

      var SaveFixedAsset = function () {
        debugger
        if(!self.model.fixedAssetTypeId) {
          CreateFixedAssetType();
        } else{
          UpdateFixedAssetType();
        }
      }

      var CreateFixedAssetType = function () {
        debugger
        var URI = CONSTANTS.MASTER.UNIT.FIXED_ASSET.FIXED_ASSET_MASTER;
        
              var data = {
                "fixedAssetTypeId": self.model.type.id,
                "fixedAssetType": self.model.fixedAssetTypeList.type,
                "productCategoryId": self.model.productCategory.id,
                "manufactureId": self.model.manufactureId,
                "itemCode": self.model.itemCode,
                "productCode": self.model.productCode,
                "Name": self.model.assetName
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
              var fixedAssetTypeData = self.model.fixedAssetTypeList.find(function (type) {
                return type.id == self.model.type.id;
              });
        
              $rootScope.startLoader();
              GenericService.serviceAction("POST", URI, data).then(
                function (response) {
                  var fixedAssetData = {
                    "id": response.data.id,
                    "assetType": assetTypeData.type,
                    "assetCategory": assetCategoryData.category,
                    "productCategory": productCategoryData.category,
                    "fixedAssetType": fixedAssetTypeData.type,
                    "itemCode": self.model.itemCode,
                    "productCode": self.model.productCode,
                    "name": self.model.assetName,
                    "manufactureType": self.model.manufacturer,
                    "status": true
                  };
                  self.model.fixedAssetList.push(fixedAssetData);
                  ClearFields();
        
                  $rootScope.stopLoader();
                  growl.success('Record saved successfully');
                },
                function errorCallback(response) {
                  $rootScope.stopLoader();
                  growl.error('Something went wrong');
                });
      };

      var UpdateFixedAssetType = function () {
        var URI = CONSTANTS.MASTER.UNIT.FIXED_ASSET.ASSET_CATEGORIES_API + self.model.activeCategoryId;
        var data = {
          "assetTypeId": self.model.assetTypeId,
          "code": self.model.categoryCode,
          "category": self.model.categoryDescription
        }
        $rootScope.startLoader();
        GenericService.serviceAction("PATCH", URI, data).then(
          function (response) {
            var assetCategory = self.model.assetCategories.find(function (category) {
              return category.id == self.model.activeCategoryId;
            });
            var assetType = self.model.categoryDropdown.find(function(type){
              return type.typeId == self.model.assetTypeId;
            });
            assetCategory.assetType = assetType.type;
            assetCategory.code = data.code;
            assetCategory.category = data.category;
            ClearFields();
            $rootScope.stopLoader();
            growl.success(response.data.message, {title: response.status});
          },
          function (err) {
            $rootScope.stopLoader();
            growl.error('Something Went Wrong', {title: err.status});
          });
      };
      var ValidateFixedAssetMaster = function () {
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
        if (!self.model.type) {
          isValid = false;
          self.model.isValidFixedAssetType = true;
        }
        if (!self.model.itemCode) {
          isValid = false;
          self.model.isValidItemCode = true;
        }
        if (!self.model.productCode) {
          isValid = false;
          self.model.isValidProductCode = true;
        }
        if (!self.model.assetName) {
          isValid = false;
          self.model.isValidAssetName = true;
        }
        if (!self.model.manufacturer) {
          isValid = false;
          self.model.isValidManufacturer = true;
        }
        return isValid;
      }
      initializeController();
    }

    function config($stateProvider) {
      $stateProvider.state("fixedAssetMaster", {
        url: "/fixedAssetMaster",
        templateUrl: 'views/master/UnitMaster/assetManagement/fixed-asset-master.html',
        controller: "FixedAssetMaster.Controller",
        controllerAs: "vm"
      });
    }

    angular
      .module("myApp")
      .config(config)
      .controller("FixedAssetMaster.Controller", fixedAssetMasterController)
  })();