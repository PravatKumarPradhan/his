(function () {
  "use strict";
  function itemMasterController($scope, $rootScope, $http, CONSTANTS, GenericService, growl, commonDetailService) {
    var self = this;

    //Default Constructor
    function initializeController() {
      $rootScope.loginpage = true;
      self.model = {
        itemDetails: commonDetailService.getDataId(),
        itemId: null,
        isitemReusable: false,
        isVaccine: false,
        isCombination: false,
        batchRequired: false,
        highRisk: false,
        psychotropic: false,
        isKit: false,
        isInfusion: false,
        isNarcotics: false,
        hospitalFormulary: false,
        isConsumable: false,
        isConsignment: false,
        isOTC: false,
        assetTypes: [{
          "type": "Select Asset Type"
        }],
        assetCategories: [{
          "category": "Select Asset Category"
        }],
        productCategories: [{
          "category": "Select Product Category"
        }],
        formulationTypes: [{
          "type": "Select Formulation Type"
        }],
        Generics: [{
          "name": "Select Generic Name"
        }],
        Units: [{
          "unit": "Select Unit"
        }],
        Manufactures: [{
          "name": "Select Manufacture"
        }],
        Pharmalogicals: [{
          "classification": "Select Pharmalogical Classification"
        }],
        PurchaseTaxes: [{
          "component": "Select Tax"
        }],
        SaleTaxes: [{
          "component": "Select Tax"
        }],
      };
      self.model.SaveItemMaster = SaveItemMaster;
      self.model.PopulateItemMaster = PopulateItemMaster;
      self.model.PopulateAssetCategory = PopulateAssetCategory;
      self.model.PopulateProductCategory = PopulateProductCategory;
      self.model.PopulateFormulationType = PopulateFormulationType;
      self.model.PopulateGenerics = PopulateGenerics;
      self.model.GetItemDetails = GetItemDetails;
      self.model.ValidateItem = ValidateItem;
      self.model.ToggleStatus = ToggleStatus;

      self.model.Item_Details = {
        'code': "",
        'manufacture': "",
        'salesTax': "",
        'storageUnit': "",
        'isVaccine': false,
        'isCombination': false,
        'isHighrisk': false,
        'isKit': false,
        'isNarcotic': false,
        'pharmacologicalClassification': "",
        'purchaseTax': false,
        'hsnCode': '',
        'isReusable': false,
        'isOtc': false,
        'isBatchRequired': false,
        'isPsychotropic': false,
        'isInfusion': false,
        'isHospitalFormulary': false,
      }
      PopulateAssetType();
      PopulateUnit();
      PopulateManufacture();
      PopulatePharamalogicalClassification();
      PopulateTax();
      GetItemMasterList();
      // PopulateDropdown();
    }

    var GetItemDetails = function (itemMaster) {
      self.model.Item_Details.code = itemMaster.code;
      self.model.Item_Details.manufacture = itemMaster.manufacturer;
      self.model.Item_Details.salesTax = itemMaster.saleTax;
      self.model.Item_Details.storageUnit = itemMaster.storageUnit;
      self.model.Item_Details.isVaccine = itemMaster.isVaccine;
      self.model.Item_Details.isCombination = itemMaster.isCombination;
      self.model.Item_Details.isHighrisk = itemMaster.isHighrisk;
      self.model.Item_Details.isKit = itemMaster.isKit;
      self.model.Item_Details.isNarcotic = itemMaster.isNarcotic;
      self.model.Item_Details.pharmacologicalClassification = itemMaster.pharmalogicalClassification;
      self.model.Item_Details.purchaseTax = itemMaster.purchaseTax;
      self.model.Item_Details.hsnCode = itemMaster.hsnCode;
      self.model.Item_Details.isReusable = itemMaster.isReusable;
      self.model.Item_Details.isOtc = itemMaster.isOtc;
      self.model.Item_Details.isBatchRequired = itemMaster.isBatchRequired;
      self.model.Item_Details.isPsychotropic = itemMaster.isPsychotropic;
      self.model.Item_Details.isInfusion = itemMaster.isInfusion;
      self.model.Item_Details.isHospitalFormulary = itemMaster.isHospitalFormulary;
    }

    //Method to get the list of Vendors
    var GetItemMasterList = function () {

      var URI = CONSTANTS.MASTER.GLOBAL.PHARAMACY.ITEM_MASTER.SEARCH_API;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          self.model.ItemMasterDataList = response.data;
          $rootScope.stopLoader();
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
        });
    }

    //Method to populate VendorMaster for editing
    function findInRecordInArray(array, key, value) {
      for (var i = 0; i < array.length; i++) {
        if (array[i][key] == value) {
          return array[i];
        }
      }
      return null;
    }

    function getItemMasterById(id) {
      return findInRecordInArray(self.model.ItemMasterDataList, 'id', id)
    }

    function PopulateItemMaster(id) {
      self.model.isassetTypeValid = false;
      self.model.isassetCategoryValid = false;
      self.model.isproductCatgeoryValid = false;
      self.model.isformulationTypeValid = false;
      self.model.isitemCodeValid = false;
      self.model.isproductCodeValid = false;
      self.model.isitemNameValid = false;
      self.model.isgenericNameValid = false;
      self.model.isstrengthValid = false;
      self.model.isunitValid = false;
      self.model.ispharmalogicalClassificationValid = false;
      self.model.ismanufacturerValid = false;
      self.model.ispurchaseTaxValid = false;
      self.model.issaleTaxValid = false;
      self.model.ishsnCodeValid = false;
      self.model.isstorageUnitValid = false;

      self.model.activeVendorMasterId = id;
      var URI = CONSTANTS.MASTER.GLOBAL.PHARAMACY.ITEM_MASTER.DETAILS_API + id;
      var data = {};
      GenericService.serviceAction("GET", URI, data).then(
        function (response) {
          var vendor = getItemMasterById(id);
          vendor = response.data;
          var assetType = self.model.assetTypes.find(function (assettype) {
            return response.data.assetTypeId == assettype.id;
          });
          var unitName = self.model.Units.find(function (unitname) {
            return response.data.strengthUnitId == unitname.id;
          });
          var classificationName = self.model.Pharmalogicals.find(function (classificationname) {
            return response.data.pharmalogicalClassificationId == classificationname.id;
          });
          var manufactureName = self.model.Manufactures.find(function (manufacturename) {
            return response.data.manufacturerId == manufacturename.id;
          });
          var purchaseTax = self.model.PurchaseTaxes.find(function (purchasetax) {
            return response.data.purchaseTaxId == purchasetax.id;
          });
          var saleTax = self.model.SaleTaxes.find(function (saletax) {
            return response.data.saleTaxId == saletax.id;
          });
          PopulateAssetCategory(response.data, true);
          PopulateProductCategory(response.data, true);
          PopulateFormulationType(response.data, true);
          PopulateGenerics(response.data, true);

          self.model.id = response.data.id;
          self.model.assetType = assetType;
          self.model.itemCode = response.data.code;
          self.model.productCode = response.data.productCode;
          self.model.itemName = response.data.name;
          self.model.strength = response.data.drugLicenseNo;
          self.model.classificationName = classificationName;
          self.model.manufactureName = manufactureName;
          self.model.purchaseTax = purchaseTax;
          self.model.saleTax = saleTax;
          self.model.unitName = unitName;
          self.model.hsnCode = response.data.hsnCode;
          self.model.strength = response.data.strength;
          self.model.status = response.data.status;
          self.model.storageUnit = response.data.storageUnit;
          self.model.isitemReusable = response.data.isReusable;
          self.model.isVaccine = response.data.isVaccine;
          self.model.isCombination = response.data.isCombination;
          self.model.batchRequired = response.data.isBatchRequired;
          self.model.highRisk = response.data.isHighrisk;
          self.model.psychotropic = response.data.isPsychotropic;
          self.model.isKit = response.data.isKit;
          self.model.isInfusion = response.data.isInfusion;
          self.model.isNarcotics = response.data.isNarcotic;
          self.model.hospitalFormulary = response.data.isHospitalFormulary;
          self.model.isConsumable = response.data.isConsumable;
          self.model.isConsignment = response.data.isConsignment;
          self.model.isOTC = response.data.isOtc;
          self.model.isEdit = true;
        });
    };

    //Method to Populate Asset Type Dropdown
    var PopulateAssetType = function () {
      var itemMaster = self.model;

      var URI = CONSTANTS.GLOBAL.ASSET_TYPE_API;

      itemMaster.assetType = itemMaster.assetTypes[0];
      itemMaster.assetCategory = itemMaster.assetCategories[0];
      itemMaster.productCategory = itemMaster.productCategories[0];
      itemMaster.formulationType = itemMaster.formulationTypes[0];
      itemMaster.genericName = itemMaster.Generics[0];

      itemMaster.assetTypes.splice(1);
      itemMaster.assetCategories.splice(1);
      itemMaster.productCategories.splice(1);
      itemMaster.formulationTypes.splice(1);
      itemMaster.Generics.splice(1);

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          itemMaster.assetTypes = itemMaster.assetTypes.concat(response.data);
          $rootScope.stopLoader();
          self.model.itemId = self.model.itemDetails.id ? self.model.itemDetails.id : null;
          self.model.itemName = self.model.itemDetails.itemName;
          self.model.itemCode = self.model.itemDetails.itemCode;
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
        });

    }

    //Method to Populate Asset Category Dropdown
    var PopulateAssetCategory = function (assetType, selectedvalue) {
      var assettypeId = 0;
      if (assetType.id == undefined) {
        assettypeId = 0;
      }
      else {
        assettypeId = assetType.id;
      }
      if (selectedvalue) {
        assettypeId = assetType.assetTypeId;
      }
      var itemMaster = self.model;

      var URI = CONSTANTS.GLOBAL.ASSET_CATEGORY_API + assettypeId;

      itemMaster.assetCategory = itemMaster.assetCategories[0];
      itemMaster.productCategory = itemMaster.productCategories[0];
      itemMaster.formulationType = itemMaster.formulationTypes[0];
      itemMaster.genericName = itemMaster.Generics[0];

      itemMaster.assetCategories.splice(1);
      itemMaster.productCategories.splice(1);
      itemMaster.formulationTypes.splice(1);
      itemMaster.Generics.splice(1);

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          itemMaster.assetCategories = itemMaster.assetCategories.concat(response.data);
          $rootScope.stopLoader();

          if (selectedvalue) {
            var assetCategory = itemMaster.assetCategories.find(function (assetcategory) {
              return assetType.assetCategoryId == assetcategory.id;
            });
            self.model.assetCategory = assetCategory;
          }
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
        });
    }

    //Method to Populate Product Category Dropdown
    var PopulateProductCategory = function (assetCategory, selectedvalue) {
      var assetcategoryId = 0;
      if (assetCategory.id == undefined) {
        assetcategoryId = 0;
      }
      else {
        assetcategoryId = assetCategory.id;
      }
      if (selectedvalue) {
        assetcategoryId = assetCategory.assetCategoryId;
      }
      var itemMaster = self.model;

      var URI = CONSTANTS.GLOBAL.PRODUCT_CATEGORY_API + assetcategoryId;

      itemMaster.productCategory = itemMaster.productCategories[0];
      itemMaster.formulationType = itemMaster.formulationTypes[0];
      itemMaster.genericName = itemMaster.Generics[0];

      itemMaster.productCategories.splice(1);
      itemMaster.formulationTypes.splice(1);
      itemMaster.Generics.splice(1);

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          itemMaster.productCategories = itemMaster.productCategories.concat(response.data);
          $rootScope.stopLoader();

          if (selectedvalue) {
            var productCategory = itemMaster.productCategories.find(function (productcategory) {
              return assetCategory.productCategoryId == productcategory.id;
            });
            self.model.productCategory = productCategory;
          }
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
        });
    }

    //Method to Populate Formulation Type Dropdown
    var PopulateFormulationType = function (productCategory, selectedvalue) {
      var productcategoryId = 0;
      if (productCategory.id == undefined) {
        productcategoryId = 0;
      }
      else {
        productcategoryId = productCategory.id;
      }
      if (selectedvalue) {
        productcategoryId = productCategory.productCategoryId;
      }
      var itemMaster = self.model;

      itemMaster.formulationType = itemMaster.formulationTypes[0];
      itemMaster.genericName = itemMaster.Generics[0];

      itemMaster.formulationTypes.splice(1);
      itemMaster.Generics.splice(1);

      var URI = CONSTANTS.MASTER.GLOBAL.PHARAMACY.ITEM_MASTER.FORMULATION_TYPE_API + productcategoryId;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          itemMaster.formulationTypes = itemMaster.formulationTypes.concat(response.data);
          $rootScope.stopLoader();

          if (selectedvalue) {
            var formulationType = itemMaster.formulationTypes.find(function (formulationtype) {
              return productCategory.formulationTypeId == formulationtype.id;
            });
            self.model.formulationType = formulationType;
          }
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
        });
    }

    //Method to Populate Generic Dropdown
    var PopulateGenerics = function (productCategory, selectedvalue) {
      var productcategoryId = 0;
      if (productCategory.id == undefined) {
        productcategoryId = 0;
      }
      else {
        productcategoryId = productCategory.id;
      }

      if (selectedvalue) {
        productcategoryId = productCategory.productCategoryId;
      }
      var itemMaster = self.model;

      itemMaster.genericName = itemMaster.Generics[0];

      itemMaster.Generics.splice(1);

      var URI = CONSTANTS.MASTER.GLOBAL.PHARAMACY.ITEM_MASTER.GENERIC_API + productcategoryId;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          itemMaster.Generics = itemMaster.Generics.concat(response.data);
          $rootScope.stopLoader();

          if (selectedvalue) {
            var genericName = itemMaster.Generics.find(function (genericname) {
              return productCategory.genericId == genericname.id;
            });
            self.model.genericName = genericName;
          }
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
        });
    }

    //Method to Populate Unit Dropdown
    var PopulateUnit = function () {
      var itemMaster = self.model;

      var URI = CONSTANTS.MASTER.GLOBAL.PHARAMACY.ITEM_MASTER.UNIT_API;

      itemMaster.unitName = itemMaster.Units[0];

      itemMaster.Units.splice(1);

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          itemMaster.Units = itemMaster.Units.concat(response.data);
          $rootScope.stopLoader();
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
        });
    }

    //Method to Populate Manufacture Dropdown
    var PopulateManufacture = function () {
      var itemMaster = self.model;

      var URI = CONSTANTS.MASTER.GLOBAL.PHARAMACY.MANUFACTURER_MASTER.MANUFATURER_API;

      itemMaster.manufactureName = itemMaster.Manufactures[0];

      itemMaster.Units.splice(1);

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          itemMaster.Manufactures = itemMaster.Manufactures.concat(response.data);
          $rootScope.stopLoader();
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
        });
    }

    //Method to Populate Pharamalogical Classification Dropdown
    var PopulatePharamalogicalClassification = function () {
      var itemMaster = self.model;

      var URI = CONSTANTS.MASTER.UNIT.PHARAMACY.PHARMALOGICAL_CLASSIFICATION_MASTER.PHARMALOGICAL_CLASSIFICATION_API;

      itemMaster.classificationName = itemMaster.Pharmalogicals[0];

      itemMaster.Pharmalogicals.splice(1);

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          itemMaster.Pharmalogicals = itemMaster.Pharmalogicals.concat(response.data);
          $rootScope.stopLoader();
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
        });
    }

    //Method to Populate Purchase and Sale Tax Dropdown
    var PopulateTax = function () {
      var itemMaster = self.model;

      var URI = CONSTANTS.MASTER.GLOBAL.PHARAMACY.ITEM_MASTER.TAX_API;

      itemMaster.purchaseTax = itemMaster.PurchaseTaxes[0];
      itemMaster.saleTax = itemMaster.SaleTaxes[0];

      itemMaster.Pharmalogicals.splice(1);

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          itemMaster.PurchaseTaxes = itemMaster.PurchaseTaxes.concat(response.data);
          itemMaster.SaleTaxes = itemMaster.SaleTaxes.concat(response.data);
          $rootScope.stopLoader();
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
        });
    }

    var SaveItemMaster = function () {
      if (ValidateItem()) {
        if (!self.model.id) {
          CreateItem();
        } else {
          UpdateItem();
        }
      }
    }

    var CreateItem = function () {
      var URI = CONSTANTS.MASTER.GLOBAL.PHARAMACY.ITEM_MASTER.SAVE_API;
      var data = {
        "itemRequestId": self.model.itemId,
        "formulationTypeId": self.model.formulationType.id,
        "code": self.model.itemCode,
        "productCode": self.model.productCode,
        "name": self.model.itemName,
        "genericId": self.model.genericName.id,
        "strength": self.model.strength,
        "strengthUnitId": self.model.unitName.id,
        "pharmacologicalClassificationId": self.model.classificationName.id,
        "manufacturerId": self.model.manufactureName.id,
        "pTaxId": self.model.purchaseTax.id,
        "sTaxId": self.model.saleTax.id,
        "hsnCode": self.model.hsnCode,
        "storageUnitId": self.model.storageUnit,
        "isReusable": self.model.isitemReusable,
        "isVaccine": self.model.isVaccine,
        "isOtc": self.model.isOTC,
        "isCombination": self.model.isCombination,
        "isBatchRequired": self.model.batchRequired,
        "isHighrisk": self.model.highRisk,
        "isPsychotropic": self.model.psychotropic,
        "isKit": self.model.isKit,
        "isInfusion": self.model.isInfusion,
        "isNarcotic": self.model.isNarcotics,
        "isConsumable": self.model.isConsumable,
        "isConsignment": self.model.isConsignment,
        "isHospitalFormulary": self.model.hospitalFormulary
      }
      $rootScope.startLoader();
      GenericService.serviceAction("POST", URI, data).then(
        function (response) {
          var assetType = self.model.assetTypes.find(function (type) {
            return type.id == self.model.assetType.id;
          });
          var assetCategory = self.model.assetCategories.find(function (category) {
            return category.id == self.model.assetCategory.id;
          });
          var productCatgeory = self.model.productCategories.find(function (category) {
            return category.id == self.model.productCategory.id;
          });
          var formulationType = self.model.formulationTypes.find(function (type) {
            return type.id == self.model.formulationType.id;
          });
          var genericName = self.model.Generics.find(function (name) {
            return name.id == self.model.genericName.id;
          });
          var unitName = self.model.Units.find(function (name) {
            return name.id == self.model.unitName.id;
          });
          var classificationName = self.model.Pharmalogicals.find(function (name) {
            return name.id == self.model.classificationName.id;
          });
          var manufactureName = self.model.Manufactures.find(function (name) {
            return name.id == self.model.manufactureName.id;
          });
          var purchaseTax = self.model.PurchaseTaxes.find(function (tax) {
            return tax.id == self.model.purchaseTax.id;
          });
          var saleTax = self.model.SaleTaxes.find(function (tax) {
            return tax.id == self.model.saleTax.id;
          });
          var insertedData = {
            "id": response.data.id,
            "productCode": self.model.productCode,
            "name": self.model.itemName,
            "genericName": genericName.name,
            "assetType": assetType.type,
            "assetCategory": assetCategory.category,
            "productCategory": productCatgeory.category,
            "formulationType": formulationType.type,
            "strength": self.model.strength,
            "strengthUnit": unitName.unit,
            "status": response.data.id,
            "code": self.model.itemCode,
            "manufacture": manufactureName.name,
            "salesTax": saleTax.code,
            "storageUnit": self.model.storageUnit,
            "purchaseTax": purchaseTax.code,
            "hsnCode": self.model.hsnCode,
            "isVaccine": self.model.isVaccine,
            "isCombination": self.model.isCombination,
            "isHighrisk": self.model.highRisk,
            "isKit": self.model.isKit,
            "isNarcotic": self.model.isNarcotics,
            "pharmacologicalClassification": classificationName.classification,
            "isReusable": self.model.isitemReusable,
            "isOtc": self.model.isOTC,
            "isBatchRequired": self.model.batchRequired,
            "isPsychotropic": self.model.psychotropic,
            "isInfusion": self.model.isInfusion,
            "isHospitalFormulary": self.model.hospitalFormulary
          };
          self.model.assetCategories.push(insertedData);
          ClearFields();
          $rootScope.stopLoader();
          growl.success(response.data.message, { title: response.status });
        },
        function (err) {
          $rootScope.stopLoader();
          growl.error('Something Went Wrong', { title: err.status });
        });
    };

    var UpdateItem = function () {
      var URI = CONSTANTS.MASTER.GLOBAL.PHARAMACY.ITEM_MASTER.DETAILS_API + self.model.id;
      var data = {
        "itemRequestId": self.model.itemId,
        "formulationTypeId": self.model.formulationType.id,
        "code": self.model.itemCode,
        "productCode": self.model.productCode,
        "name": self.model.itemName,
        "genericId": self.model.genericName.id,
        "strength": self.model.strength,
        "strengthUnitId": self.model.unitName.id,
        "pharmacologicalClassificationId": self.model.classificationName.id,
        "manufacturerId": self.model.manufactureName.id,
        "pTaxId": self.model.purchaseTax.id,
        "sTaxId": self.model.saleTax.id,
        "hsnCode": self.model.hsnCode,
        "storageUnitId": self.model.storageUnit,
        "isReusable": self.model.isitemReusable,
        "isVaccine": self.model.isVaccine,
        "isOtc": self.model.isOTC,
        "isCombination": self.model.isCombination,
        "isBatchRequired": self.model.batchRequired,
        "isHighrisk": self.model.highRisk,
        "isPsychotropic": self.model.psychotropic,
        "isKit": self.model.isKit,
        "isInfusion": self.model.isInfusion,
        "isNarcotic": self.model.isNarcotics,
        "isConsumable": self.model.isConsumable,
        "isConsignment": self.model.isConsignment,
        "isHospitalFormulary": self.model.hospitalFormulary
      }
      $rootScope.startLoader();
      GenericService.serviceAction("PATCH", URI, data).then(
        function (response) {
          var assetType = self.model.assetTypes.find(function (type) {
            return type.id == self.model.assetType.id;
          });
          var assetCategory = self.model.assetCategories.find(function (category) {
            return category.id == self.model.assetCategory.id;
          });
          var productCatgeory = self.model.productCategories.find(function (category) {
            return category.id == self.model.productCategory.id;
          });
          var formulationType = self.model.formulationTypes.find(function (type) {
            return type.id == self.model.formulationType.id;
          });
          var genericName = self.model.Generics.find(function (name) {
            return name.id == self.model.genericName.id;
          });
          var unitName = self.model.Units.find(function (name) {
            return name.id == self.model.unitName.id;
          });
          var classificationName = self.model.Pharmalogicals.find(function (name) {
            return name.id == self.model.classificationName.id;
          });
          var manufactureName = self.model.Manufactures.find(function (name) {
            return name.id == self.model.manufactureName.id;
          });
          var purchaseTax = self.model.PurchaseTaxes.find(function (tax) {
            return tax.id == self.model.purchaseTax.id;
          });
          var saleTax = self.model.SaleTaxes.find(function (tax) {
            return tax.id == self.model.saleTax.id;
          });
          var itemMaster = self.model.ItemMasterDataList.find(function (item) {
            return item.id == self.model.id;
          });
          itemMaster.productCode = self.model.productCode;
          itemMaster.name = self.model.itemName;
          itemMaster.genericName = genericName.name;
          itemMaster.assetType = assetType.type;
          itemMaster.assetCategory = assetCategory.category;
          itemMaster.productCategory = productCatgeory.category;
          itemMaster.formulationType = formulationType.type;
          itemMaster.strength = self.model.strength;
          itemMaster.strengthUnit = unitName.unit;
          itemMaster.status = response.data.id;
          itemMaster.code = self.model.itemCode;
          itemMaster.manufacture = manufactureName.name;
          itemMaster.salesTax = saleTax.code;
          itemMaster.storageUnit = self.model.storageUnit;
          itemMaster.purchaseTax = purchaseTax.code;
          itemMaster.hsnCode = self.model.hsnCode;
          itemMaster.isVaccine = self.model.isVaccine;
          itemMaster.isCombination = self.model.isCombination;
          itemMaster.isHighrisk = self.model.highRisk;
          itemMaster.isKit = self.model.isKit;
          itemMaster.isNarcotic = self.model.isNarcotics;
          itemMaster.pharmacologicalClassification = classificationName.classification;
          itemMaster.isReusable = self.model.isitemReusable;
          itemMaster.isOtc = self.model.isOTC;
          itemMaster.isBatchRequired = self.model.batchRequired;
          itemMaster.isPsychotropic = self.model.psychotropic;
          itemMaster.isInfusion = self.model.isInfusion;
          itemMaster.isHospitalFormulary = self.model.hospitalFormulary;
          ClearFields();
          $rootScope.stopLoader();
          growl.success(response.data.message, { title: response.status });
        },
        function (err) {
          $rootScope.stopLoader();
          growl.error('Something Went Wrong', { title: err.status });
        });
    };

    //Method to activate or deactivate Main Store
    var ToggleStatus = function (itemMaster) {
      var URI = CONSTANTS.MASTER.GLOBAL.PHARAMACY.ITEM_MASTER.DETAILS_API + itemMaster.id;
      var data = {
        "status": itemMaster.status
      };
      GenericService.serviceAction("PATCH", URI, data).then(
        function (response) {
          $rootScope.stopLoader();
          growl.success('Status changed successfully', { title: response.status });
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
          mainStore.status = !mainStore.status;
          growl.error('Something went wrong', { title: response.status });
        });
    }

    var ValidateItem = function () {
      var isValid = true;
      if (!self.model.assetType.id) {
        isValid = false;
        self.model.isassetTypeValid = true;
      }
      if (!self.model.assetCategory.id) {
        isValid = false;
        self.model.isassetCategoryValid = true;
      }
      if (!self.model.productCategory.id) {
        isValid = false;
        self.model.isproductCatgeoryValid = true;
      }
      if (!self.model.formulationType.id) {
        isValid = false;
        self.model.isformulationTypeValid = true;
      }
      if (!self.model.itemCode) {
        isValid = false;
        self.model.isitemCodeValid = true;
      }
      if (!self.model.productCode) {
        isValid = false;
        self.model.isproductCodeValid = true;
      }
      if (!self.model.itemName) {
        isValid = false;
        self.model.isitemNameValid = true;
      }
      if (!self.model.genericName.id) {
        isValid = false;
        self.model.isgenericNameValid = true;
      }
      if (!self.model.strength) {
        isValid = false;
        self.model.isstrengthValid = true;
      }
      if (!self.model.unitName.id) {
        isValid = false;
        self.model.isunitValid = true;
      }
      if (!self.model.classificationName.id) {
        isValid = false;
        self.model.ispharmalogicalClassificationValid = true;
      }
      if (!self.model.manufactureName.id) {
        isValid = false;
        self.model.ismanufacturerValid = true;
      }
      if (!self.model.purchaseTax.id) {
        isValid = false;
        self.model.ispurchaseTaxValid = true;
      }
      if (!self.model.saleTax.id) {
        isValid = false;
        self.model.issaleTaxValid = true;
      }
      if (!self.model.hsnCode) {
        isValid = false;
        self.model.ishsnCodeValid = true;
      }
      if (!self.model.storageUnit) {
        isValid = false;
        self.model.isstorageUnitValid = true;
      }
      return isValid;
    }

    //Method to clear the fileds
    function ClearFields() {
      self.model.id = 0;
      self.model.assetType = self.model.assetTypes[0];
      self.model.assetCategory = self.model.assetCategories[0];
      self.model.assetCategories.splice(1);
      self.model.productCategory = self.model.productCategories[0];
      self.model.productCategories.splice(1);
      self.model.formulationType = self.model.formulationTypes[0];
      self.model.formulationTypes.splice(1);
      self.model.genericName = self.model.Generics[0];
      self.model.Generics.splice(1);
      self.model.itemCode = "";
      self.model.productCode = "";
      self.model.itemName = "";
      self.model.genericName = "";
      self.model.strength = "";
      self.model.unitName = "";
      self.model.classificationName = self.model.Pharmalogicals[0];
      self.model.manufactureName = self.model.Manufactures[0];
      self.model.purchaseTax = self.model.PurchaseTaxes[0];
      self.model.saleTax = self.model.SaleTaxes[0];
      self.model.unitName = self.model.Units[0];
      self.model.hsnCode = "";
      self.model.storageUnit = "";
      self.model.isitemReusable = false;
      self.model.isVaccine = false;
      self.model.isCombination = false;
      self.model.batchRequired = false;
      self.model.highRisk = false;
      self.model.psychotropic = false;
      self.model.isKit = false;
      self.model.isInfusion = false;
      self.model.isNarcotics = false;
      self.model.hospitalFormulary = false;
      self.model.isConsumable = false;
      self.model.isConsignment = false;
      self.model.isOTC = false;
    }

    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("itemMasterGlobal", {
      url: "/itemMasterGlobal",
      templateUrl: 'views/master/GlobalMaster/AssetManagenent/Master/item-master.html',
      controller: "ItemMasterGlobal.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("ItemMasterGlobal.Controller", itemMasterController)
})();