(function () {
  "use strict";

  function substoremasterController($scope, $rootScope, $http, CONSTANTS, GenericService, $state, openingBalanceService, productService, growl) {
    var self = this; 
    var uomUnitIdSet;

    //Default Constructor
    function initializeController() {
      $rootScope.loginpage = true;
      self.model = {
        stores: [{
          "store": "Select Store"
        }],
        assetTypeList: [{
          "type": "Select Asset Type"
        }],
        assetCategories: [{
          "category": "Select Asset Category"
        }],
        productCategories: [{
          "category": "Select Product Category"
        }], SelectedItemsIds: [],
        storeIndentData: [],
        selectAllRows: false,
        SelectedItemIds: [],
        requestData: [],
        openingBalanceData: [],
        selectAllRows: false,
        currentUomList: [],
        dateOptions: {
          formatYear: 'yyyy',
          showWeeks: false
        },
        dateOpen: [],
        storeFlag: false
      };

      self.model.GetStoreList = GetStoreList;
      self.model.storeId = null;

      self.model.AddItemListData = AddItemListData;
      self.model.RemoveRowData = RemoveRowData;
      self.model.AddDuplicateRow = AddDuplicateRow;
      self.model.GetAssetCategoryList = GetAssetCategoryList;
      self.model.SaveNewItemRequest = SaveNewItemRequest;
      self.model.CallUOM = CallUOM;
      // self.model.AssetCategoryNameList = AssetCategoryNameList;
      // self.model.ProductCategoryNameList = ProductCategoryNameList;
      self.model.AddButtonClick = AddButtonClick;
      self.model.OpenExpiryDate = OpenExpiryDate;
      self.model.searchNewItem = searchNewItem;
      self.model.SelectItem = SelectItem;
      self.model.SelectAllItems = SelectAllItems;
      self.model.AddItems = AddItems;
      self.model.PopulateDropdown = PopulateDropdown;
      // // self.model.getproductCategory = getproductCategory;
      // self.model.getAllItemDetails = getAllItemDetails;
      self.model.GetProductCategoryList = GetProductCategoryList;
      self.model.uomInfoOnClick = uomInfoOnClick;
      self.model.uomModalOnClick = uomModalOnClick;
      self.model.selectedUOMList = [];
      self.model.selectedUOMUnit = [];
      self.model.selectedItemIndex = 0;
      self.model.NavigteToOpeningBalance = NavigteToOpeningBalance;
      self.model.fillItemList = fillItemList;
      self.model.SaveOpeningBalance = SaveOpeningBalance;
      self.model.uomSelectEvent = uomSelectEvent;
      self.model.modalUOMSelectEvent = modalUOMSelectEvent;
      self.model.ClearFields = ClearFields;
      self.model.isItemChecked = false;
      self.model.ItemDetailList = [];
      self.model.searchNewItemList = [];
      self.model.validateSave = validateSave;
      self.model.GetGenericList = GetGenericList;
      PopulateDropdown();
      PopulateAssetTypeDropdown();
    }
 //Method to get the list of Generic 
 var GetGenericList = function () {
  
        var URI = CONSTANTS.MASTER.STORAGE_UNIT_MASTER.STORAGE_UNIT_API;
        $rootScope.startLoader();
        GenericService.serviceAction("GET", URI, {}).then(
          function (response) {
            self.model.storeMasterList = response.data;
            $rootScope.stopLoader();
          },
          function errorCallback(response) {
            $rootScope.stopLoader();
          });
      }
    var uomSelectEvent = function (datum) {
      datum.uom.forEach(function (uom) {
        if (uom.uomTypeId == datum.uomTypeId) datum.selectedUom = uom;
      });
    }

    var modalUOMSelectEvent = function (datum) {
      self.model.selectedUOMUnit.forEach(function (uom) {
        if (uom.uomTypeId == datum.uomTypeId) datum.selectedUOM = uom;
      });
    }

    var uomInfoOnClick = function (uomList, index) {
      self.model.selectedUOMList = [];
      self.model.selectedUOMUnit = [];
      self.model.selectedItemIndex = index;
      if (uomList)
        uomList.forEach(function (item, k) {
          self.model.selectedUOMList.push(item);
          self.model.selectedUOMUnit.push({ 'uomType': item.uomUnit, 'uomTypeId': item.uomTypeId });

          if(item.selectedUOM && item.selectedUOM.uomTypeId == item.uomTypeId)
            item['selectedUOM'] = self.model.selectedUOMUnit[k];
        });
    }

    var uomModalOnClick = function () {
      self.model.ItemDetailList[self.model.selectedItemIndex].defaultUOM = [];
      if (self.model.selectedUOMList) {
        self.model.selectedUOMList.forEach(function (item) {
          self.model.ItemDetailList[self.model.selectedItemIndex].defaultUOM.push({
            uomTypeId: item.uomTypeId,
            uomUnitId: item.uomUnitId,
            conversion: item.conversion
          });
        });
      }
      $('#selectUOM_modal').modal('hide');
    }

    var OpenExpiryDate = function (idx) {
      self.model.dateOpen[idx] = true;
    }

    var PopulateDropdown = function () {
      var URI = CONSTANTS.GLOBAL.USER_STORE_API;

      self.model.store = self.model.stores[0];

      self.model.stores.splice(1);

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          self.model.stores = self.model.stores.concat(response.data);
          $rootScope.stopLoader();
          GetGenericList();
        },
        function (err) {
          $rootScope.stopLoader();
        });
    }

    var PopulateAssetTypeDropdown = function () {
      var assetTypeDropdown = self.model;
      assetTypeDropdown.assetType = assetTypeDropdown.assetTypeList[0];
      self.model.assetCategory = self.model.assetCategories[0];
      self.model.productCategory = self.model.productCategories[0];

      assetTypeDropdown.assetTypeList.splice(1);
      self.model.assetCategories.splice(1);
      self.model.productCategories.splice(1);
      var URI = CONSTANTS.GLOBAL.ASSET_TYPE_API;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          assetTypeDropdown.assetTypeList = assetTypeDropdown.assetTypeList.concat(response.data);
          $rootScope.stopLoader();
          // PopulateAssetCategoryDropdown();
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
        });

    }
    var GetAssetCategoryList = function (assetTypeId) {
      var URI = CONSTANTS.GLOBAL.ASSET_CATEGORY_API  + assetTypeId.id;

      self.model.assetCategory = self.model.assetCategories[0];
      self.model.productCategory = self.model.productCategories[0];

      self.model.assetCategories.splice(1);
      self.model.productCategories.splice(1);

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          self.model.assetCategories = self.model.assetCategories.concat(response.data);
          $rootScope.stopLoader();
        },
        function (err) {
          $rootScope.stopLoader();
        });
    }



    var GetProductCategoryList = function (id) {
      debugger
      var indents = self.model;

      //TODO : Add asset category to the uri
      var URI = CONSTANTS.GLOBAL.PRODUCT_CATEGORY_API  +  id;
      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          indents.productCategories = indents.productCategories.concat(response.data);
          $rootScope.stopLoader();
        },
        function (err) {
          $rootScope.stopLoader();
        });
    }

    function searchNewItem() {

      var URI = CONSTANTS.GLOBAL.ITEM_API;
      const data = {
        "assetTypeId": 1,
        "assetCategoryId": self.model.assetCategory.id ? self.model.assetCategory.id : undefined,
        "productCategoryId": self.model.productCategory.id ? self.model.productCategory.id : undefined,
        "storeId": self.model.store.id
      }

      self.model.searchNewItemList = [];
      $rootScope.startLoader();
      GenericService.serviceAction("POST", URI, data).then(
        function (response) {
          $rootScope.stopLoader();
          self.model.searchNewItemList = response.data;
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
          // ClearFields(); 
          growl.error('Error');
        });
    }

    var GetOpeningBalance = function (opbId) {
      // var opbId = openingBalanceService.getOpeningBalanceId();

      var URI = CONSTANTS.INVENTORY.STOCK.OPENING_BALANCE.DETAILS_API + opbId;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          self.model.opbId = response.data.id;

          self.model.store = self.model.stores.find(function (item) {
            return response.data.storeId == item.id;
            //self.model.store.id = response.data.storeId;
          });

          self.model.itemDetails = [];
          angular.forEach(response.data.items, function (item, key) {
            item.expiryDate = new Date(item.expiryDate);
          });

          if (response.data.items)
            self.model.fillItemList(response.data.items);
          $rootScope.stopLoader();
        },
        function (err) {
          $rootScope.stopLoader();
        });
    }

    var SaveOpeningBalance = function () {
      if (validateSave()) {
        if (!self.model.opbId) {
          SaveNewItemRequest();
        } else {
          UpdateOpeningBalance();
        }
      }
    }
    var UpdateOpeningBalance = function () {
      
      var opb = self.model;

      var URI = CONSTANTS.INVENTORY.STOCK.OPENING_BALANCE.UPDATE_API + opb.opbId;

      var data = {
        "storeId": opb.store.id,
        "statusId": 1,
        "items": []
      };

      angular.forEach(opb.ItemDetailList, function (item, key) {
        var expDateMonth = item.expiryDate.getMonth();
        var expDateYear = item.expiryDate.getFullYear();
        var expDateDay = new Date(expDateYear, expDateMonth + 1, 0).getDate();

        var storeItem = {
          "itemId": item.itemId,
          "batchNo": item.batchNo,
          "expiryDate": (expDateYear + "-" + expDateMonth + "-" + expDateDay),
          "quantity": item.quantity,
          "cop": item.cop,
          "mrp": item.mrp,
          "copAmount": item.copAmount,
          "markup": item.markup,
          "taxId": item.taxId,
          "taxPercent": item.taxPercent,
          "taxAmount": item.taxAmount,
          "remark": item.remark,
          "statusId": 1,
          "uomTypeId":item.selectedUom.uomTypeId,
          "uomUnitId": item.selectedUom.uomUnitId,
          "uom": item.defaultUOM
        }
        data.items.push(storeItem);


      });
      GenericService.serviceAction("PATCH", URI, data).then(
        function (response) {
          NavigteToOpeningBalance();
          $rootScope.stopLoader();
          growl.success(response.data.message);
        },
        function (err) {
          $rootScope.stopLoader();
          growl.error('Something Went Wrong');
        });
    }

    var AddButtonClick = function (index) {
      var data = self.model.openingBalanceData[index];
      self.model.currentUomList = [];
      if (data.uomList) {
        self.model.currentUomList = [data.uomList[0]];
      }
    }


    var FromOpeningBalance = function () {
      var id = productService.getProductId();
      if (id !== 0) {
        self.model.storeFlag = true;
      var URI = CONSTANTS.INVENTORY.STOCK.OPENING_BALANCE.DETAILS_API   + id;
        var data = {};
        GenericService.serviceAction("GET", URI, data).then(
          function (response) {
            // self.model.storeData = productService.getStoreId();
            self.model.openingBalanceData = response.data;
          });
      } else {
        self.model.storeFlag = false;
      }
    }

    var CallUOM = function (idx) {
      var data = self.model.openingBalanceData[idx];
      if (data.uomUnitId == "1") {
        data.selectedUom = data.uomList[0].puomUnitDesc;
        uomUnitIdSet = data.uomList[0].pUomId;
      } else if (data.uomUnitId == "2") {
        data.selectedUom = data.uomList[0].suomUnitDesc;
        uomUnitIdSet = data.uomList[0].sUomId;
      } else if (data.uomUnitId == "3") {
        data.selectedUom = data.uomList[0].duomUnitDesc;
        uomUnitIdSet = data.uomList[0].dUomId;
      }
    }

    function GetStoreList() {

      self.model.action = 'Open';
      var popup = angular.element('#addNewStore');
      popup.modal('show');

    //  GetAssetCategoryList();


    }


    var AddItemListData = function () {
      self.model.selectAllRows = false;
      for (var i = 0; i < self.model.storeDataList.length; i++) {
        if (self.model.storeDataList[i]["isChecked"] == true) {
          self.model.openingBalanceData.push(self.model.storeDataList[i]);
        }
      }
    }

    var validateSave = function(){
      var isValid = true;
        var ItemDetailList = self.model.ItemDetailList;
        // !self.model.fromStore.id || !self.model.toStore.id || !self.model.priority.id || 
        if (ItemDetailList.length < 1) {
          growl.error('Error', {
            title: "Please validate all required fields"
          });
          isValid = false;
        }
        var expMonth = new Date().getMonth() + 1;
        for (let ItemDetail of ItemDetailList) {
          var expiryDate = ItemDetail.expiryDate;
          if (!expiryDate || (new Date(expiryDate).getMonth() + 1) <= expMonth) {
            growl.error('Error', {
              title: "Please Valid Expiry Date"
            });
            isValid = false;
          }
        }  
        for (let ItemDetail of ItemDetailList) {
        
          if (ItemDetail.batchNo == undefined || ItemDetail.uom.uomTypeId== '' ||ItemDetail.uom.uomUnitId== ''  ) {
            growl.error('Error', {
              title: "Please Validate All fields"
            });
            isValid = false;
          }
        }  
        
        return isValid;
      }
    var SaveNewItemRequest = function () {
      var store = self.model;
      var URI = CONSTANTS.INVENTORY.STOCK.OPENING_BALANCE.SAVE_API ;

      var data = {
        "storeId": store.store.id,
        "statusId": 1,
        "items": []
      };

      angular.forEach(store.ItemDetailList, function (item, key) {
        var expDateMonth = item.expiryDate.getMonth();
        var expDateYear = item.expiryDate.getFullYear();
        var expDateDay = new Date(expDateYear, expDateMonth + 1, 0).getDate();

        var storeItem = {
          "itemId": item.itemId,
          "batchNo": item.batchNo,
          "expiryDate": (expDateYear + "-" + expDateMonth + "-" + expDateDay),
          "quantity": item.quantity,
          "cop": item.cop,
          "mrp": item.mrp,
          "copAmount": item.copAmount,
          "markup": item.markup,
          "taxId": item.taxId,
          "taxPercent": item.taxPercent,
          "taxAmount": item.taxAmount,
          "remark": item.remark,
          "statusId": 1,
          "uomTypeId":item.selectedUom.uomTypeId,
          "uomUnitId": item.selectedUom.uomUnitId,
          "uom": item.defaultUOM
        }
        data.items.push(storeItem);

      });

      /*var uomObj = angular.forEach(storeItem, function (item, key) {
        var uom = {
          "uomTypeId": item.uomTypeId,
          "uomUnitId": item.uomUnitId,
          "conversion": item.conversion
        }
        // storeItem.push(uom);
      });*/
      $rootScope.startLoader();
      GenericService.serviceAction("POST", URI, data).then(
        function (response) {
          // ClearRequests();
          $rootScope.stopLoader();
          NavigteToOpeningBalance();

          growl.success(response.data.message);
        },



        function (err) {
          $rootScope.stopLoader();
          growl.error('Something Went Wrong');
        });
    }

    var NavigteToOpeningBalance = function () {
      $state.go('openingBalance');
    }

  
    // var AddDuplicateRow = function (item) {
    //   self.model.ItemDetailList.splice(index + 1, 0, angular.copy(self.model.ItemDetailList[index]));
    // }

    var AddDuplicateRow = function (item) {
      var data = angular.copy(item);
      data.batchNo = ''; 
      data.expiryDate = ''; 
      data.quantity = ''; 
      data.cop = ''; 
      data.mrp = ''; 
      data.copAmount = '';
      data.markup = '';
      data.copAmount = '';
      data.taxAmount = '';
      data.remark = ''; 
      self.model.ItemDetailList.push(data); 
  }


    var RemoveRowData = function (index) {
      var ItemDetailList = self.model.ItemDetailList;
      self.model.ItemDetailList = ItemDetailList.slice(0, index).concat(ItemDetailList.slice(index + 1))
    }

    var AddItems = function () {
      // var URI = constants.apiurl + '/api/common/items/details';

      // new test api
      var URI = CONSTANTS.GLOBAL.ITEM_DETAILS_API; 
      var data = {
        "itemIds": self.model.SelectedItemsIds,
        "storeId": self.model.store.id
      };

      $rootScope.startLoader();
      GenericService.serviceAction("POST", URI, data).then(
        function (response) {
          self.model.fillItemList(response.data);
          // self.model.ItemDetailList = self.model.ItemDetailList.concat(response.data);
          self.model.SelectedItemsIds = [];
          self.model.items = [];
          // console.log(selectedUom);
          $rootScope.stopLoader();
          // getAllItemDetails();
          growl.success(response.data.message);
          ClearFields();

        },
        function (err) {
          $rootScope.stopLoader();
          growl.error(err.message);
        });
    }

    var fillItemList = function (list) {
      list.forEach(function (item) {
        var defaultUOM = [];
        for (let uom of item.uom) {
          defaultUOM.push({
            uomTypeId: uom.uomTypeId,
            uomUnitId: uom.uomUnitId,
            conversion: uom.conversion,
            ipUom: uom.ipUom,
            opUom: uom.opUom,
            storeUom: uom.storeUom
          });
        }

        item['defaultUOM'] = defaultUOM;
        self.model.ItemDetailList.push(item);
      });
    }

    // Against Indent Item Selection
    var isSelectedItem = function (item) {
      return item.isSelectedItem;

    }    // Against Indent Item Selection
    var isSelected = function (item) {
      return item.isSelected;
    }


    var SelectAllItems = function () {
      if (self.model.selectAllRows) {
        self.model.SelectedItemsIds = self.model.searchNewItemList.map(function (item) {
          item.isSelected = true;
          self.model.isItemChecked = true;
          return item.itemId;
        });
      } else {
        self.model.SelectedItemsIds = [];
        self.model.searchNewItemList.forEach(function (item) {
          item.isSelected = false;
          self.model.isItemChecked = false;
        });
      }
    }

    var SelectItem = function (item) {
      var itemIds = self.model.SelectedItemsIds;

      if (item.isSelected) {
        itemIds.push(item.itemId);
      } else {
        var index = itemIds.indexOf(item.itemId);
        if (index > -1) {
          itemIds.splice(index, 1);
        }
      }

      if (self.model.searchNewItemList.every(isSelected)) {
        self.model.selectAllRows = true;
      } else {
        self.model.selectAllRows = false;
      }

      self.model.isItemChecked = false;
      self.model.searchNewItemList.forEach(function (item) {
        if (item.isSelected == true) {
          self.model.isItemChecked = true;
          return;
        }
      });
    }

    var ClearFields = function (item) {

      // item.id = null;
      // item.storeId = null;
      // item.statusId = null; 
      // item.items = [];
    }


    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("substoremaster", {
      url: "/substoremaster",
      templateUrl: 'views/PharmacyInventory/StockAdjustment/sub-store-master.html',
      controller: "SubStoreMaster.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("SubStoreMaster.Controller", substoremasterController);
})();