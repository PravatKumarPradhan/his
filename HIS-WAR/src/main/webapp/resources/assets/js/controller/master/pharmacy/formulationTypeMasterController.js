(function () {
  "use strict";
  function formulationTypeMasterController($scope,$rootScope, $http, CONSTANTS, GenericService, growl) {
    var self = this;
    $rootScope.loginpage = true;
    //Default Constructor
    function initializeController() {
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
      self.model.PopulateAssetTypeDropdown = PopulateAssetTypeDropdown;
      self.model.PopulateAssetCategoryDropdown = PopulateAssetCategoryDropdown;
      self.model.PopulateProductCategoryDropdown = PopulateProductCategoryDropdown;
      self.model.PopulateFormulationType = populateFormulationType;
      self.model.ToggleStatus = ToggleStatus;
      self.model.SaveFormulationType = saveFormulationType;
      self.model.ClearFields = ClearFields;
      self.model.ValidateFormulationMaster = validateFormulationMaster;

        getFormulationTypeData();
      PopulateAssetTypeDropdown();
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
      var URI = CONSTANTS.GLOBAL.ASSET_TYPE_API;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          genericMaster.assetTypeList = genericMaster.assetTypeList.concat(response.data);
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
    

    //Method to get the list of Formulation Type
    function getFormulationTypeData() {
      var URI = CONSTANTS.MASTER.GLOBAL.PHARAMACY.ITEM_MASTER.FORMULATION_TYPE_API;
      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          self.model.formulationTypeDataList = response.data;
          $rootScope.stopLoader();
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
        });
    };

    //Method to populate formulation type for editing
    function populateFormulationType(formulationTypeId) {
      self.model.activeFormulationTypeId = formulationTypeId;
      var URI = CONSTANTS.MASTER.GLOBAL.PHARAMACY.ITEM_MASTER.FORMULATION_TYPE_API + formulationTypeId;
      var data = {};
      GenericService.serviceAction("GET", URI, data).then(
        function (response) {
       //   var formulation = getFormulationByTypeId(formulationTypeId);
        //  formulation = response.data;
        var assetType = self.model.assetTypeList.find(function (type) {
          return response.data.assetTypeId == type.id;
        });
        self.model.assetType = assetType;
        PopulateAssetCategoryDropdown(response.data, true);
          self.model.formulationTypeCode = response.data.code;
          self.model.formulationTypeName = response.data.type;
          self.model.formulationTypeId = response.data.id;
          self.model.status = response.data.status;
          self.model.isEdit = true;
        });
    };

    //Method to activate or deactivate formulation type
    function ToggleStatus(formulationType) {
    //  var formulation = getFormulationByTypeId(formulationTypeId);
     // var status = formulation.isActive;
      var URI = CONSTANTS.MASTER.GLOBAL.PHARAMACY.ITEM_MASTER.FORMULATION_TYPE_API + formulationType.id;
      var data = {
        "status": formulationType.status
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
    };

    //Method to add or update the formulation type
    function saveFormulationType() {
      if(validateFormulationMaster()){
      if (!self.model.formulationTypeId) {
        createFormulationType();
       
      } else {
        updateFormulationType();
      }
    }
    };

    //Method to add the formulation type
    function createFormulationType() {
      var URI = CONSTANTS.MASTER.GLOBAL.PHARAMACY.ITEM_MASTER.FORMULATION_TYPE_API;
      var data = {
        "productCategoryId": self.model.productCategory.id,
        "code": self.model.formulationTypeCode,
        "type": self.model.formulationTypeName,
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
      GenericService.serviceAction("POST", URI, data).then(
        function (response) {
          var insertedData = {
            "id": response.data.id,
            "assetType": assetTypeData.type,
            "assetCategory": assetCategoryData.category,
            "productCategory": productCategoryData.category,
            "type": self.model.formulationTypeName,
            "code": self.model.formulationTypeCode,
            "status": true
          };
          self.model.formulationTypeDataList.push(insertedData);
          ClearFields();
          $rootScope.stopLoader();
          growl.success(response.data.message)
        }, function errorCallback(response) {
          console.log(response.data || 'Request Failed');
        });
    };

    //Method to update the formulation type
    function updateFormulationType() {
      var URI = CONSTANTS.MASTER.GLOBAL.PHARAMACY.ITEM_MASTER.FORMULATION_TYPE_API + self.model.formulationTypeId;
    //  var formulation = getFormulationByTypeId(self.model.activeFormulationTypeId);
      var data = {
        "productCategoryId": self.model.productCategory.id,
        "type": self.model.formulationTypeName,
        "code": self.model.formulationTypeCode,
        "status": true
      }
      GenericService.serviceAction("PATCH", URI, data).then(
        function (response) {
         // var formulation = getFormulationByTypeId(self.model.activeFormulationTypeId);
         var formulationData = self.model.formulationTypeDataList.find(function (type) {
          return type.id == self.model.formulationTypeId;
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
        formulationData.type = data.type;
        formulationData.code = data.code;
        formulationData.status = data.status;
        formulationData.category = productCategoryData.category;
        formulationData.assetType = assetTypeData.type;
        formulationData.assetCategory = assetCategoryData.category;
        ClearFields();
        $rootScope.stopLoader();
        growl.success(response.data.message);
          self.model.activeFormulationTypeId = null;
          self.model.isEdit = false;
        });
    };

    //Method to clear the fileds
    function ClearFields() {
      self.model.formulationTypeName = "";
      self.model.formulationTypeCode = "";
      self.model.assetType = self.model.assetTypeList[0];
      self.model.assetCategory = [self.model.assetCategoryList[0]];
      self.model.assetCategory = self.model.assetCategoryList[0];
      self.model.productCategory = [self.model.productCategoryList[0]];
      self.model.productCategory = self.model.productCategoryList[0];
      self.model.assetCategoryList.splice(1);
      self.model.productCategoryList.splice(1);
    }
    var validateFormulationMaster = function () {
      var isValid = true;

      if (!self.model.assetType.id) {
        isValid = false;
        self.model.isAssetTypeIdValid = true;
      }
      if (!self.model.assetCategory.id) {
        isValid = false;
        self.model.isAssetCategoryIdValid = true;
      }
      if (!self.model.productCategory.id) {
        isValid = false;
        self.model.isValidProductCat = true;
      }
      if (!self.model.formulationTypeCode) {
        isValid = false;
        self.model.isValidFormulationTypeCode = true;
      }
      if (!self.model.formulationTypeName) {
        isValid = false;
        self.model.isValidFormulationType = true;
      }
      return isValid;
    }
    initializeController();
  }
  function config($stateProvider) {
    $stateProvider.state("pharmacyformulationTypeMaster", {
      url: "/pharmacyformulationTypeMaster",
      templateUrl: "views/master/GlobalMaster/pharmacy-formulation-type-master.html",
      controller: "FormulationTypeMaster.Controller",
      controllerAs: "vm",
    });
  }
  angular
    .module("myApp")
    .config(config)
    .controller("FormulationTypeMaster.Controller", formulationTypeMasterController);
})();


