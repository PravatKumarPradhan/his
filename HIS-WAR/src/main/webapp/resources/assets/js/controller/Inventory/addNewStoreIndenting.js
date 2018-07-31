(function () {
  "use strict";
  var CACHE = {};

  function addNewStoreIndentingController($scope, $state, $rootScope, $http, $filter, CONSTANTS, StatusService, GenericService, storeIndentService, growl) {
    var self = this;
    var mainStoreList = [];

    //Default Constructor
    function initializeController() {
      $rootScope.loginpage = true;
      self.model = {
        assetCategories: [{
          "category": "Select Asset Category"
        }],
        productCategories: [{
          "category": "Select Product Category"
        }],
        defaultStores: [{
          "store": "Select Store"
        }],
        statuses: [{
          "status": "All"
        }],
        SelectedItemsIds: [],
        storeIndentData: [],
        selectAllRows: false,
        SelectedItemIds: [],
      };
      // self.model.SelectedStore = selectedStore;
      self.model.GetItemList = getItemList;
      self.model.validateSave = validateSave;
      self.model.ItemDetailList = [];
      self.model.AddItemListData = addItemListData;
      self.model.RemoveRowData = removeRowData;
      self.model.SelectedAll = selectedAll;
      self.model.SaveNewStoreIndentRequest = saveNewStoreIndentRequest;
      self.model.isConsignment = false;
      self.model.fromStoreId = null;
      self.model.SaveStoreIndent = SaveStoreIndent;
      self.model.UpdateStoreIndent = UpdateStoreIndent;
      self.model.toStoreId = null;
      self.model.isItemChecked = false;
      self.model.priorityId = null;
      self.model.assetCategoryId = null;
      self.model.productCategoryId = null;
      self.model.CallUOM = callUOM;
      self.model.remark = null;
      self.model.GetAssetCategoryList = GetAssetCategoryList;
      // self.model.assetCategories = [];
      self.model.GetProductCategoryList = GetProductCategoryList;
      self.model.productCategoryList = [];
      self.model.searchNewItem = searchNewItem;
      self.model.searchNewItemList = [];
      self.model.SelectItem = SelectItem;
      self.model.getAllItemDetails = getAllItemDetails;
      self.model.SelectAllItems = SelectAllItems;
      self.model.AddItems = AddItems;
      self.model.IsMainStore = IsMainStore;
      //self.model.fromStoreOnChange = fromStoreOnChange;
      GetAssetCategoryList();
      getDropdownsList();
      // getAllItemDetails();
    }

    var fromStoreOnChange = function (data) {
    }

    var IsMainStore = function (isOn) {
      var indents = self.model;

      if (isOn) {
        indents.toStores = indents.defaultStores.concat($filter('filter')(indents.allStores, {
          isMainStore: true
        }, true));
      } else {
        indents.toStores = indents.defaultStores.concat(indents.allStores);
      }
    }
    // todo : 

    var getDropdownsList = function () {
      try {
        var indents = self.model;

        indents.fromStores = indents.defaultStores;
        indents.toStores = indents.defaultStores;
        indents.status = indents.statuses[0];
        indents.fromStore = indents.fromStores[0];
        indents.toStore = indents.toStores[0];

        var URI = CONSTANTS.INVENTORY.STORE.STORE_INDENT.DROPDOWN_API;

        $rootScope.startLoader();
        GenericService.serviceAction("GET", URI, {}).then(
          function (response) {
            indents.fromStores = indents.defaultStores.concat(response.data.fromStore);
            indents.allStores = response.data.toStore;
            indents.toStores = indents.defaultStores.concat(response.data.toStore);
            indents.statuses = indents.statuses.concat(response.data.status);
            indents.priorities = response.data.priority;
            indents.priority = indents.priorities[1];

            var indentId = storeIndentService.getIndentId();
            $rootScope.stopLoader();
            if (!!indentId) {
              storeIndentService.setIndentId(null);
              GetStoreIndent(indentId);
            } else {
              return false;
            }
          },
          function (err) {
            $rootScope.stopLoader();
          });
      } catch (error) {
        alert(error);
      }
    }

    var GetAssetCategoryList = function () {
      var indents = self.model;
      var URI = CONSTANTS.GLOBAL.ASSET_CATEGORY_API + 1;

      indents.assetCategory = indents.assetCategories[0];
      indents.productCategory = indents.productCategories[0];

      indents.assetCategories.splice(1);
      indents.productCategories.splice(1);

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          indents.assetCategories = indents.assetCategories.concat(response.data);
          $rootScope.stopLoader();
        },
        function (err) {
          $rootScope.stopLoader();
        });
    }

    function setStatus(x) {
      var status = self.model.statuses.find(function (obj) {
        return obj.status == x;
      });

      return status.id;
    }

    var GetProductCategoryList = function (id) {
      var indents = self.model;

      //TODO : Add asset category to the uri
      var URI = CONSTANTS.GLOBAL.PRODUCT_CATEGORY_API + id;
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



    var GetStoreIndent = function (indentId) {

      var URI = CONSTANTS.INVENTORY.STORE.STORE_INDENT.DETAILS_API + indentId;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          self.model.indentId = response.data.id;

          self.model.fromStore = self.model.fromStores.find(function (item) {
            return response.data.fromStoreId == item.id;
          });

          self.model.toStore = self.model.toStores.find(function (item) {
            return response.data.toStoreId == item.id;
          });

          self.model.priority = self.model.priorities.find(function (item) {
            return response.data.priorityId == item.id;
          });

          self.model.isConsignment = response.data.isConsignment;

          self.model.remark = response.data.remark;

          self.model.itemDetails = [];

          angular.forEach(response.data.items, function (item) {
            var indentItem = {
              "id": item.id,
              "fromStore": item.fromStore,
              "toStore": item.toStore,
              "fromStoreId": item.fromStoreId,
              "toStoreId": item.toStoreId,
              "priorityId": item.id,
              "indentDetailId": item.indentDetailId,
              "itemId": item.itemId,
              "itemCode": item.itemCode,
              "itemName": item.itemName,
              "genericId": item.genericId,
              "genericName": item.genericName,
              "manufacturerId": item.manufacturerId,
              "quantity": item.quantity,
              "manufacturer": item.manufacturer,
              "uomType": item.uomType,
              "uomUnit": item.uomUnit,
              "uomTypeId": item.uomTypeId,
              "uomUnitId": item.uomUnitId,
              "remark": item.remark,
              "indentQuantity": item.indentQuantity,
              "stockAvailable": item.stockAvailable,
              "stockInTransit": item.stockInTransit,
              "lastMonthSale": item.lastMonthSale,
              "currentMonthSale": item.currentMonthSale,
              "maxOrderQuantity": item.maxOrderQuantity
            }

            self.model.ItemDetailList.push(indentItem);
          });
          $rootScope.stopLoader();
        },
        function (err) {
          $rootScope.stopLoader();
        });
    }



    function fromStoreIndent() {
      var id = storeIndentService.getIndentId();

      var URI = CONSTANTS.INVENTORY.STORE.STORE_INDENT.DETAILS_API + indentId;

      var data = {};
      GenericService.serviceAction("GET", URI, data).then(
        function (response) {
          self.model.fromStoreId = self.model.storeList.fromStore.find(x => x.id == response.data.fromStoreId);
          self.model.toStoreId = self.model.storeList.toStore.find(x => x.id == response.data.toStoreId);
          self.model.priorityId = self.model.storeList.priorities.find(x => x.id == response.data.priorityId);
          self.model.isConsignment = response.data.isConsignment;
          self.model.remark = response.data.Remark || null;
          self.model.ItemDetailList = response.data.items;
        });
    }

    function selectedAll() {
      self.model.SelectedItemIds = [];
      self.model.searchNewItemList.forEach(function (x) {
        if (self.model.selectAllRows) {
          x.isSelected = true;
          self.model.SelectedItemIds.push(x.itemId);
        } else {
          x.isSelected = false;
        }
      });
    }

    function searchNewItem() {

      var URI = CONSTANTS.GLOBAL.ITEM_API;

      const data = {
        "assetTypeId": 1,
        "assetCategoryId": self.model.assetCategory.id ? self.model.assetCategory.id : undefined,
        "productCategoryId": self.model.productCategory.id ? self.model.productCategory.id : undefined,
        "fromStoreId": self.model.fromStore.id ? self.model.fromStore.id : undefined,
        "toStoreId": self.model.toStore.id ? self.model.toStore.id : undefined,
        "isConsignment": self.model.isConsignment ? self.model.isConsignment.id : undefined,
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
          growl.error('Error');
        });
    }

    function SelectItem(Item) {
      var itemIds = self.model.SelectedItemIds;
      if (Item.isSelected) {
        itemIds.push(Item.itemId);
      } else {
        var index = itemIds.indexOf(Item.itemId);
        if (index > -1) {
          itemIds.splice(index, 1);
        }
      }
      var allselected = self.model.searchNewItemList.findIndex(x => x.isSelected != true)
      if (allselected < 0) { // As is returns -1 if al rows selected
        self.model.selectAllRows = true;
      } else {
        self.model.selectAllRows = false;
      }
    }

    function getAllItemDetails() {

      var URI = CONSTANTS.GLOBAL.ITEM_DETAILS_API;
      var data = {
        "itemIds": self.model.SelectedItemIds,
      }
      $rootScope.startLoader();
      GenericService.serviceAction("POST", URI, data).then(
        function (response) {
          $rootScope.stopLoader();
          var ItemDetailList = response.data;
          self.model.ItemDetailList = ItemDetailList;
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
          growl.error('Error');
        });
    }

    function removeRowData(index) {
      var ItemDetailList = self.model.ItemDetailList;
      self.model.ItemDetailList = ItemDetailList.slice(0, index).concat(ItemDetailList.slice(index + 1))
    }

    var SaveStoreIndent = function () {
      if (validateSave()) {
        if (!self.model.indentId) {
          saveNewStoreIndentRequest();
        } else {
          UpdateStoreIndent();
        }
      }
    }
    var saveNewStoreIndentRequest = function () {


      var store = self.model;

      var URI = CONSTANTS.INVENTORY.STORE.STORE_INDENT.SAVE_API;

      var data = {
        "fromStoreId": store.fromStore.id,
        "toStoreId": store.toStore.id,
        // "priorityId": store.priorityId ? store.priorityId : undefined,
        "priorityId": store.priority.id,
        "isConsignment": store.isConsignment,
        "indentTypeId": 1,
        "remark": store.remark,
        "statusId": setStatus('New'),
        "approvalStatusId": setStatus('New'),
        "items": []
      };

      angular.forEach(store.ItemDetailList, function (item, key) {
        var storeItem = {
          "itemId": item.itemId,
          "uomTypeId": item.uomTypeId,
          "uomUnitId": item.uomUnitId,
          "quantity": parseInt(item.quantity),
          "statusId": setStatus('New')
        }

        data.items.push(storeItem);
      });

      $rootScope.startLoader();
      GenericService.serviceAction("POST", URI, data).then(
        function (response) {
          // ClearRequests();
          $rootScope.stopLoader();
          NavigteToStoreIndent();
          growl.success(response.data.message);
        },



        function (err) {
          $rootScope.stopLoader();
          growl.error('Something Went Wrong');
        });
    }

    var UpdateStoreIndent = function () {
      var indent = self.model;
      // var newStatus = 0;
      var URI = CONSTANTS.INVENTORY.STORE.STORE_INDENT.UPDATE_API + indent.indentId;
      var data = {
        "priorityId": indent.priority.id,
        "isConsignment": indent.isConsignment,
        "remark": indent.remark,
        "items": []
      };

      angular.forEach(indent.ItemDetailList, function (item, key) {

        var indentItem = {
          "id": item.id,
          "itemId": item.itemId,
          "uomTypeId": item.uomTypeId,
          "uomUnitId": item.uomUnitId,
          "quantity": parseInt(item.quantity),
          //"statusId": newstatus
        }
        if (item.id == '' || item.id == null) {
          indentItem.statusId = setStatus('New')
        }

        data.items.push(indentItem);
      });

      $rootScope.startLoader();
      GenericService.serviceAction("PATCH", URI, data).then(
        function (response) {

          $rootScope.stopLoader();
          NavigteToStoreIndent();
          growl.success(response.data.message);
        },



        function (err) {
          $rootScope.stopLoader();
          growl.error('Something Went Wrong');
        });
    }


    var CheckItems = function (item) {
      if (!item.selectedUom.id || !item.indentQuantity) {
        return true;
      }
      return false;
    }


    var NavigteToStoreIndent = function () {
      $state.go('storeIndenting');
    }

    function validateSave() {
      var isValid = true;
      var ItemDetailList = self.model.ItemDetailList;
      // !self.model.fromStore.id || !self.model.toStore.id || !self.model.priority.id || 
      if (ItemDetailList.length < 1) {
        growl.error('Error', {
          title: "Please validate all required fields"
        });
        isValid = false;
      }
      for (let ItemDetail of ItemDetailList) {
        if (ItemDetail.quantity == null) {
          growl.error('Error', {
            title: "Please Enter Indent Quantity"
          });
          isValid = false;
        }
      }
      for (let ItemDetail of ItemDetailList) {
        if (ItemDetail.quantity > ItemDetail.maxOrderQuantity) {
          growl.error('Error', {
            title: "Stock Indent Quantity must be less than Max Order Quantity"
          });
          isValid = false;
        }
      }

      return isValid;
    }

    function getItemList() {
      self.model.action = 'Open';
      var popup = angular.element('#addNewItemWasteStore');
      popup.modal('show');

    }

    function callUOM(idx) {
      var data = self.model.storeIndentData[idx].uomDropdown;
      if (data == 1) {
        return self.model.storeIndentData[idx].uomList[data - 1].uomUnitDesc;
      } else if (data == 2) {
        return self.model.storeIndentData[idx].uomList[data - 1].uomUnitDesc;
      } else if (data == 3) {
        return self.model.storeIndentData[idx].uomList[data - 1].uomUnitDesc;
      }
    }

    var AddItems = function () {
      // var URI = constants.apiurl + '/api/common/items/details'; 
      var URI = CONSTANTS.GLOBAL.ITEM_DETAILS_API;

      var data = {
        "itemIds": self.model.SelectedItemsIds,
        "storeId": self.model.fromStore.id
      };
      $rootScope.startLoader();
      GenericService.serviceAction("POST", URI, data).then(
        function (response) {
          response.data.forEach(function (item) {
            var isFound = self.model.ItemDetailList.find(function (x) {
              return x.itemId == item.itemId;
            });
            if (!isFound) {
              self.model.ItemDetailList.push(item);
            }
          });
          // self.model.ItemDetailList = self.model.ItemDetailList.concat(response.data);
          self.model.SelectedItemsIds = [];
          self.model.items = [];
          var selectedUom;
          angular.forEach(self.model.ItemDetailList, function (x) {
            if (x.uom) {
              selectedUom = x.uom.find(function (sUom) {
                return sUom.storeUom == true;
              });
              x["uomType"] = selectedUom.uomType;
              x["uomUnit"] = selectedUom.uomUnit;
              x["uomTypeId"] = selectedUom.uomTypeId;
              x["uomUnitId"] = selectedUom.uomUnitId;
            }
            else {
              x["uomType"] = x.uomType;
              x["uomUnit"] = x.uomUnit;
              x["uomTypeId"] = x.uomTypeId;
              x["uomUnitId"] = x.uomUnitId;
            }
          });
          // console.log(selectedUom);
          $rootScope.stopLoader();
          // getAllItemDetails();
          self.model.searchNewItemList = [];
        },
        function (err) {
          $rootScope.stopLoader();
          growl.error(err.data.message);
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
    function addItemListData() {
      // var data = self.model.storeDataList;
      // self.model.storeDataList = [];
      // // self.model.storeIndentData = [];
      // self.model.selectAllRows = false;
      // for (var i = 0; i < data.length; i++) {
      //   if (data[i]["isChecked"] == true) {
      //     self.model.storeIndentData.push(data[i]);
      //   } else {
      //     self.model.storeDataList.push(data[i]);
      //   }
      // }
      // console.log(self.model.storeIndentData)
    }

    // for temporary ------------------[]

    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("addNewStoreIndenting", {
      url: "/addNewStoreIndenting",
      templateUrl: 'views/PharmacyInventory/InPatient/add-new-store-indenting.html',
      controller: "AddNewStoreIndenting.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("AddNewStoreIndenting.Controller", addNewStoreIndentingController);
})();