(function () {
  "use strict";
  var CACHE = {};

  function issueListDirectIssueController($scope, $state, $rootScope, $http, CONSTANTS, $filter, GenericService, commonDetailService, growl) {
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
        carriers: [{
          "name": "Select Carrier"
        }],
        SelectedItemsIds: [],
        storeIndentData: [],
        selectAllRows: false,
        SelectedItemIds: [],
      };
      // self.model.SelectedStore = selectedStore;
      self.model.GetItemList = getItemList;
      self.model.GetAssetCategoryList = GetAssetCategoryList;
      self.model.GetProductCategoryList = GetProductCategoryList;
      self.model.SelectedAll = selectedAll;
      self.model.searchNewItem = searchNewItem;
      self.model.SelectItem = SelectItem;
      self.model.RemoveRowData = RemoveRowData;
      self.model.AddDuplicateRow = AddDuplicateRow;
      self.model.AddItems = AddItems;
      self.model.fillItemList = fillItemList;
      self.model.SelectAllItems = SelectAllItems;
      self.model.ItemDetailList = [];
      self.model.isConsignment = false;
      self.model.fromStoreId = null;
      self.model.toStoreId = null;
      self.model.priorityId = null;
      self.model.assetCategoryId = null;
      self.model.isItemChecked = false;
      self.model.productCategoryId = null;
      self.model.remark = null;
      self.model.assetCategoriesList = [];
      self.model.productCategoryList = [];
      self.model.searchNewItemList = [];
      self.model.removeRowData = removeRowData;
      self.model.saveIssue = saveIssue;
      self.model.batchOnChange = batchOnChange;
      self.model.NavigteToMaterialIssue = NavigteToMaterialIssue;
      self.model.validateDirectIssue =validateDirectIssue;
      GetAssetCategoryList();
      getDropdownsList();
    }


    var getDropdownsList = function () {
      try {
        var directissue = self.model;

        directissue.fromStores = directissue.defaultStores;
        directissue.toStores = directissue.defaultStores;

        directissue.fromStores = directissue.defaultStores;
        directissue.toStores = directissue.defaultStores;
        directissue.carrier = directissue.carriers[0];
        directissue.fromStore = directissue.fromStores[0];
        directissue.toStore = directissue.toStores[0];

        // var URI = constants.apiurl + constants.inventoryApi + '/stores/indents/dropdowns';
        var URI = CONSTANTS.INVENTORY.STORE.MATERIAL_ISSUE.MATERIAL_ISSUE_LIST.DIRECT_ISSUE_DROPDOWN;
        $rootScope.startLoader();
        GenericService.serviceAction("GET", URI, {}).then(
          function (response) {
            directissue.fromStores = directissue.defaultStores.concat(response.data.fromStore);
            directissue.allStores = response.data.toStore;
            directissue.toStores = directissue.defaultStores.concat(response.data.toStore);
            directissue.carriers = directissue.carriers.concat(response.data.carrier);


            var issueId = commonDetailService.getDataId();
            $rootScope.stopLoader();
            if (!!issueId) {
              commonDetailService.setDataId(null);
              GetStoreIndent(issueId);
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

    var batchOnChange = function (selectedBatch, batch) {
      batch.expiry = selectedBatch.expiry;
      //batch.availableStockQuantity = selectedBatch.availableStockQuantity;
      // batch.uomList = selectedBatch.uom;

      // batch.displayQuantity = batch.availableStockQuantity / 
      // (batch.conversion ? batch.conversion : 1);
      var storeUom = selectedBatch.uom.find(function (obj) {
        return obj.storeUom == true;
      });
      batch.uomType = storeUom.uomType;
      batch.uomTypeId = storeUom.uomTypeId;
      batch.uomUnit = storeUom.uomUnit;
      batch.uomUnitId = storeUom.uomUnitId;
      batch.displayQuantity = selectedBatch.availableStockQuantity / storeUom.conversion;
    }
    var uomSelectEvent = function (batch) {
      batch.uom.forEach(function (uom) {
        if (uom.uomTypeId == batch.uomTypeId) batch.selectedUom = uom;
      });
    }

    var GetAssetCategoryList = function () {
      var directissue = self.model;

      // var URI = constants.apiurl + constants.globalApi + '/assets/categories/' + 1;
      var URI = CONSTANTS.GLOBAL.ASSET_CATEGORY_API + 1;
      directissue.assetCategory = directissue.assetCategories[0];
      directissue.productCategory = directissue.productCategories[0];

      directissue.assetCategories.splice(1);
      directissue.productCategories.splice(1);

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          directissue.assetCategories = directissue.assetCategories.concat(response.data);
          $rootScope.stopLoader();
        },
        function (err) {
          $rootScope.stopLoader();
        });
    }

    var GetProductCategoryList = function (id) {
      var indents = self.model;
      var URI = CONSTANTS.GLOBAL.PRODUCT_CATEGORY_API + id;
      //TODO : Add asset category to the uri
      // var URI = constants.apiurl + constants.globalApi + '/products/categories/' + id;
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
    function removeRowData(index) {
      var searchNewItemList = self.model.searchNewItemList;
      self.model.searchNewItemList = searchNewItemList.slice(0, index).concat(searchNewItemList.slice(index + 1))
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
      // const URI = constants.apiurl + constants.globalApi + '/items';
      var URI = CONSTANTS.GLOBAL.ITEM_API;
      const data = {
        "assetTypeId": 1,
        "assetCategoryId": self.model.assetCategory.id ? self.model.assetCategory.id : undefined,
        "productCategoryId": self.model.productCategory.id ? self.model.productCategory.id : undefined,
        "fromStoreId": self.model.fromStore.id ? self.model.fromStore.id : undefined,
        "toStoreId": self.model.toStore.id ? self.model.toStore.id : undefined,
        "isConsignment": self.model.isConsignment ? self.model.isConsignment : undefined
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
          growl.error('Error', {
            title: response.status
          });
        });
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


    var CheckItems = function (item) {
      if (!item.selectedUom.id || !item.indentQuantity) {
        return true;
      }
      return false;
    }


    var NavigteToStoreIndent = function () {
      $state.go('storeIndenting');
    }

    var AddItems = function () {
      var URI = CONSTANTS.GLOBAL.BATCH_ITEM_API;
      // var URI = constants.apiurl + '/api/common/items/details'; 
      // new test api
      // var URI = constants.apiurl + constants.globalApi + '/items/batches/details';
      var data = {
        "itemIds": self.model.SelectedItemsIds,
        "storeId": self.model.fromStore.id
      };

      $rootScope.startLoader();
      GenericService.serviceAction("POST", URI, data).then(
        function (response) {

          self.model.ItemDetailList = response.data;
          self.model.ItemDetailList.forEach(function (item) {
            item['batchList'] = [item.batch[0]];
            item['batchList']['uomList'] = [];

          });
          angular.forEach(self.model.ItemDetailList, function (item) {
            var storeUom = item.uom.find(function (obj) {
              return obj.storeUom == true;
            });
            item.uomType = storeUom.uomType;
            item.uomUnit = storeUom.uomUnit;
          });

          //   var storeUom = self.model.ItemDetailList.uom.find(function (obj) {
          //     return obj.storeUom == true;
          // });
          // item.uomType = storeUom.uomType;
          // self.model.fillItemList(response.data);
          // self.model.ItemDetailList = self.model.ItemDetailList.concat(response.data);
          self.model.SelectedItemsIds = [];
          self.model.items = [];
          // console.log(selectedUom); 
          $rootScope.stopLoader();
          // getAllItemDetails();
          // ClearFields();
        },
        function (err) {
          $rootScope.stopLoader();
          growl.error(err.message);
        });
    }

    var fillItemList = function (list) {
      list.forEach(function (item) {

        for (let batch of item.batch) {

          var defaultUOM = [];
          for (let uom of batch.uom) {
            defaultUOM.push({
              uomTypeId: uom.uomTypeId,
              uomUnitId: uom.uomUnitId,
              conversion: uom.conversion
            });
          }

          batch['defaultUOM'] = defaultUOM;
        }
        self.model.ItemDetailList.push(item);
      });
    }


    var AddDuplicateRow = function (item) {
      var data = (angular.copy(item.batchList[0])); 
      data.expiry = "";
      data.selectedBatch.expiry = "";
      data.uomType = '';
      data.uomUnit = '';
      data.batchId = '';
      data.issueQuantity = '';
      data.displayQuantity = '';
      data.batchNo = '';
      data.statusId = '';
      item.batchList.push(data); 
      // batch.splice(index + 1, 0, angular.copy(batch[index]));
    }


    var RemoveRowData = function (item) {
      // item.batchList.pop(item.batchList[0]);
      //item.batchList = item.batchList.slice(0, item).concat(item.batchList.slice(item + 1))
      var index = item.batchList.indexOf(item);
      item.batchList.splice(index, 1);
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
    function getItemList() {
      self.model.action = 'Open';
      var popup = angular.element('#addNewItemWasteStore');
      popup.modal('show');

    }

    var saveIssue = function () {
      var store = self.model; 

    
      var URI = CONSTANTS.INVENTORY.STORE.MATERIAL_ISSUE.MATERIAL_ISSUE_LIST.SAVE_API;
 if(validateDirectIssue()){

      // var URI = constants.apiurl + constants.inventoryApi + '/material/issue/list';

      var data = {
        "fromStoreId": self.model.fromStore.id,
        "toStoreId": self.model.toStore.id,
        "statusId": 4,
        "carrierId": self.model.carrier.id,
        "remark": self.model.remark ? self.model.remark : undefined,
        "isConsignment": self.model.isConsignment,
        "items": []
      };

      angular.forEach(store.ItemDetailList, function (item, key) {
        var storeItem = {
          "itemId": item.itemId,
          "issueQuantity": item.issueQuantity,
          "statusId":4, 
          "batches": []
        }

        angular.forEach(item.batchList, function (batch) {
          var batch = {
            "batchId": batch.batchId,
            "uomTypeId": batch.uomTypeId,
            "uomUnitId": batch.uomUnitId,
            "issueQuantity": parseInt(batch.issueQuantity)
          }
          storeItem.batches.push(batch);
        });

        data.items.push(storeItem);
      });
      $rootScope.startLoader();
      GenericService.serviceAction("POST", URI, data).then(
        function (response) {
          // ClearRequests();
          $rootScope.stopLoader();
          NavigteToMaterialIssue();
          growl.success(response.data.message);
        },
        function (err) {
          $rootScope.stopLoader();
          growl.error('Something Went Wrong');
        });

      }
    
  }

    function validateDirectIssue() {

      var isValid = true;
      var ItemDetail = self.model.ItemDetailList; 
      if (ItemDetail.length == 0) {
          growl.error('Error', {
              title: "Please validate all required fields"
          });
          isValid = false;
      } 
      for (let item of self.model.ItemDetailList) {
        if (!item.issueQuantity || item.issueQuantity > item.stockAvailable) {
            growl.error('Error', {
                title: "Please Enter Valid Issue Quantity"
            });
            isValid = false;
        }
    }  


      return isValid;
    }


    var NavigteToMaterialIssue = function () {
      $state.go('materialIssue.issueList');
  }
    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("issueListdirectIssue", {
      url: "/issueListdirectIssue",
      templateUrl: 'views/PharmacyInventory/InPatient/issue-list-directIssue.html',
      controller: "IssueListDirectIssue.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("IssueListDirectIssue.Controller", issueListDirectIssueController);
})();