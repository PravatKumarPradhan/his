(function () {
  "use strict";

  function addNewLocalPRController($scope, $rootScope, $http, $state, CONSTANTS, GenericService, commonDetailService, CurrencyService, rejectReasonService, StatusService, growl) {
    var self = this;

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
        stores: [{
          "store": "Select Store"
        }],
        fromStores: [{
          "store": "Select Store"
        }],
        toStores: [{
          "store": "Select Store"
        }],
        SelectedItemsIds: [],
        itemDetails: [],
        purchaseId: null,
        intendItemIds: [],
        indentItemDetails: [],
        isQuantity: [],
        isUOMValid: [],
        isCop: [],
        search: {
          itemName: undefined,
          itemCode: undefined
        }
      };

      CurrencyService.GetCurrency().then(function(currency){
        self.model.Currency = currency;
      });

      rejectReasonService.GetReason().then(function(reason) {
        self.model.rejectReasons = reason;
      });
      
      StatusService.GetStatus().then(function(status) {
        self.model.statusList = status;
      });

      //Methods
      self.model.PopulateAssetCategoryDropdown = PopulateAssetCategoryDropdown;
      self.model.PopulateProductCategory = PopulateProductCategory;
      self.model.GetItemList = GetItemList;
      self.model.SelectAllItems = SelectAllItems;
      self.model.SelectItem = SelectItem;
      self.model.AddItems = AddItems;
      self.model.RemoveItem = RemoveItem;
      self.model.SavePurchaseRequest = SavePurchaseRequest;
      self.model.NavigateToLocalPR = NavigateToLocalPR;
      self.model.ChangePRType = ChangePRType;
      self.model.GetIndentList = GetIndentList;
      self.model.Expand = Expand;
      self.model.SelectAllIndentItems = SelectAllIndentItems;
      self.model.SelectIndentItem = SelectIndentItem;
      self.model.AddIndentItems = AddIndentItems;
      self.model.CalculateStock = CalculateStock;
      self.model.SetDecimal = SetDecimal;

      PopulateDropdown();
    }

    var setStatus = function (x){
      var status = self.model.statusList.find(function(obj){
        return obj.status == x;
      });
      
      return status.id;
    }

    //Method to populate the list of Local Purchase Request dropdown
    var PopulateDropdown = function () {
      try {
        $rootScope.startLoader();
        var localPurchase = self.model;
        localPurchase.store = localPurchase.stores[0];

        var URI = CONSTANTS.PROCUREMENT.PURCHASE.LOCAL_PURCHASE_REQUEST.DROPDOWN_API;

        GenericService.serviceAction("GET", URI, {}).then(
          function (response) {
            localPurchase.stores = localPurchase.stores.concat(response.data.store);
            localPurchase.priorities = response.data.priority;
            localPurchase.priority = response.data.priority[1];
            
            var purchaseId = commonDetailService.getDataId();
            $rootScope.stopLoader();
            if (!!purchaseId) {
              commonDetailService.setDataId(null);
              GetLocalPR(purchaseId);
            }
          },
          function (err) {
            $rootScope.stopLoader();
          });
      } catch (error) {
        alert(error);
      }
    }

    var ChangePRType = function (prType){
      if (ValidateDetails()) {
        if (prType == 1) {
          self.model.action = 'Open';
          var popup = angular.element('#addNewItemWasteStore');
          popup.modal('show');
          PopulateAssetCategoryDropdown();
          self.model.items = [];
          self.model.search.itemCode = undefined;
          self.model.search.itemName = undefined;
        }else if(prType == 2){
          self.model.action = 'Open';
          var popup = angular.element('#getRequestWasteStore');
          popup.modal('show');
          PopulateIndentDropdown();
          self.model.indents = [];
          self.model.searchIndent = undefined;
        }
      }
    }

    var GetLocalPR = function (purchaseId) {
      var URI = CONSTANTS.PROCUREMENT.PURCHASE.LOCAL_PURCHASE_REQUEST.SAVE_API + purchaseId;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          self.model.purchaseId = response.data.id;
          self.model.store = self.model.stores.find(function (item) {
            return response.data.storeId == item.id;
          });

          self.model.priority = self.model.priorities.find(function (item) {
            return response.data.priorityId == item.id;
          });
          self.model.assetType = response.data.assetTypeId;
          self.model.PRType = response.data.prTypeId;
          self.model.remark = response.data.remark;
          self.model.itemDetails = [];

          angular.forEach(response.data.items, function (item) {
            var indentItem = {
              "id": item.id,
              "itemId": item.itemId,
              "itemCode": item.itemCode,
              "itemName": item.itemName,
              "uom": item.uom,
              "selectedUom": item.uom.find(function (uom) {
                return item.uomUnitId == uom.uomUnitId;
              }),
              "availableQuantity": item.availableQuantity,
              "itemSpecifications": item.specifications,
              "quantity": item.quantity,
              "cop": item.cop,
              "indentQuantity": item.indentQuantity
            }
            self.model.itemDetails.push(indentItem);
            // CalculateStock(item, item.uom, item.selectedUom);
          });

          $rootScope.stopLoader();
        },
        function (err) {
          growl.error(err.data.message, {title: err.status});
          $rootScope.stopLoader();
        });
    }

    var ValidateDetails = function (){
      var isValid = true;
      if (!self.model.store.id) {
        isValid = false;
        self.model.isStoreValid = true;
      }

      if (!self.model.assetType) {
        isValid = false;
        self.model.isAssetTypeValid = true;
      }

      if (!self.model.PRType) {
        isValid = false;
        self.model.isPRTypeValid = true;
      }

      if (!self.model.store.id || !self.model.assetType || !self.model.PRType) {
        isValid = false;
        growl.error('Please Select Required Field');
      }

      return isValid;
    }

    var ValidateItems = function (){
      var isValidItem = true;

      if (self.model.itemDetails.length <= 0) {
        growl.error('Please Add Items');
        isValidItem = false;
      } else if (!self.model.priority) {
        growl.error('Select Priority');
        isValidItem = false;
      } else {
        for (var i = 0; i < self.model.itemDetails.length; i++) {
          if(!self.model.itemDetails[i].quantity || self.model.itemDetails[i].quantity <= 0){
            self.model.isQuantity[i] = true;
            isValidItem = false;
          }

          if (!self.model.itemDetails[i].selectedUom.uomUnitId) {
            self.model.isUOMValid[i] = true;
            isValidItem = false;
          }

          if (!self.model.itemDetails[i].cop || self.model.itemDetails[i].cop <= 0) {
            self.model.isCop[i] = true;
            isValidItem = false;
          }

          if (!self.model.itemDetails[i].quantity || !self.model.itemDetails[i].selectedUom.uomUnitId || !self.model.itemDetails[i].cop) {
            growl.error('Fill All Required Fields');
          }
        }
      }

      return isValidItem;
    }

    var SetDecimal = function (item) {
      if (isNaN(item.cop) || !item.cop) {
        item.cop = 0;
      } else {
        item.cop = parseFloat(item.cop).toFixed(2);
      }
    }

    var PopulateAssetCategoryDropdown = function () {
      var purchaseRequest = self.model;

      var URI = CONSTANTS.GLOBAL.ASSET_CATEGORY_API + self.model.assetType;

      purchaseRequest.assetCategory = purchaseRequest.assetCategories[0];
      purchaseRequest.productCategory = purchaseRequest.productCategories[0];

      purchaseRequest.assetCategories.splice(1);
      purchaseRequest.productCategories.splice(1);

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          purchaseRequest.assetCategories = purchaseRequest.assetCategories.concat(response.data);
          $rootScope.stopLoader();
        },
        function (err) {
          $rootScope.stopLoader();
        });
    }

    var PopulateProductCategory = function (assetCategory) {
      var purchaseRequest = self.model;

      var URI = CONSTANTS.GLOBAL.PRODUCT_CATEGORY_API + self.model.assetCategory.id;
      
      purchaseRequest.productCategory = purchaseRequest.productCategories[0];
      purchaseRequest.productCategories.splice(1);
      
      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          purchaseRequest.productCategories = purchaseRequest.productCategories.concat(response.data);
          $rootScope.stopLoader();
        },
        function (err) {
          $rootScope.stopLoader();
        });
    }

    var PopulateIndentDropdown = function () {
      var purchaseRequest = self.model;
      self.model.indents = [];
      var URI = CONSTANTS.INVENTORY.STORE.STORE_INDENT.DROPDOWN_API;

      purchaseRequest.fromStore = purchaseRequest.fromStores[0];
      purchaseRequest.toStore = purchaseRequest.toStores[0];

      purchaseRequest.fromStores.splice(1);
      purchaseRequest.toStores.splice(1);

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          purchaseRequest.fromStores = purchaseRequest.fromStores.concat(response.data.fromStore);
          purchaseRequest.toStores = purchaseRequest.toStores.concat(response.data.toStore);
          $rootScope.stopLoader();
        }, function (err) {
          $rootScope.stopLoader();
        });
    }

    var GetIndentList = function () {
      var fromStore = self.model.fromStore.id;
      var toStore = self.model.toStore.id;
      if (!fromStore || !toStore) {
        self.model.indents = [];
        growl.error('Select Valid Store');
        return false;
      }else if (fromStore == toStore) {
        self.model.indents = [];
        growl.error('From Store and To Store should not be same');
        return false;
      } else{
        var URI = CONSTANTS.GLOBAL.GET_INDENTS_API + fromStore + '/' + toStore;

        GenericService.serviceAction("GET", URI, {}).then(
          function (response) {
            self.model.indents = response.data;
            $rootScope.stopLoader();
          },
          function (err) {
            $rootScope.stopLoader();
            growl.error(err.data.message);
          });
      }
    }

    var Expand = function (item) {
      item.Expanded = !item.Expanded;
    }

    var GetItemList = function () {
      var URI = CONSTANTS.GLOBAL.ITEM_API;

      var data = {
        "assetCategoryId": self.model.assetCategory.id,
        "productCategoryId": self.model.productCategory.id,
        "storeId": self.model.store.id
      }

      self.model.items = [];
      $rootScope.startLoader();
      GenericService.serviceAction("POST", URI, data).then(
        function (response) {
          self.model.items = response.data;
          $rootScope.stopLoader();
        },
        function (err) {
          $rootScope.stopLoader();
          growl.error(err.data.message);
        });
    }

    var AddItems = function () {
      if (self.model.SelectedItemsIds.length == 0) {
        growl.error('Select Items to Add');
        return false;
      } else {
        var URI = CONSTANTS.GLOBAL.ITEM_DETAILS_API;

        var data = {
          "itemIds": self.model.SelectedItemsIds,
          "storeId": self.model.store.id
        };

        $rootScope.startLoader();
        GenericService.serviceAction("POST", URI, data).then(
          function (response) {
            response.data.forEach(function(item) {
              var isFound = self.model.itemDetails.find(function(x){
                return x.itemId == item.itemId;
              });
              if (!isFound) {
                self.model.itemDetails.push(item);
                selectUOM(item);
              }
            });
            self.model.SelectedItemsIds = [];
            self.model.items = [];

            var popup = angular.element('#addNewItemWasteStore');
            popup.modal('hide');

            $rootScope.stopLoader();
          },
          function (err) {
            $rootScope.stopLoader();
            self.model.SelectedItemsIds = [];
            growl.error(err.data.message, {title: err.status});
          });
      }
    }

    // var selectUOM = function (array) {
    //   angular.forEach(array, function (item) {
    //     item.selectedUom = item.uom[0];
    //     // CalculateStock(item, item.uom, item.selectedUom);
    //   });
    // }

    var selectUOM = function (item) {
      item.selectedUom = item.uom[0];
    }

    var CalculateStock = function (item, uom, uomList) {
      if (true) {
        var stockAvailable = item.stockAvailable;
        uomList.uomDetails = uomList.uomDetails.split(' ')[0];

        if (uomList.uomDetails == "P") {
          item.newQty = stockAvailable / (uom[0].conversion * uom[1].conversion * uom[2].conversion);
        } else if (uomList.uomDetails == "S") {
          item.newQty = stockAvailable / (uom[1].conversion * uom[2].conversion);
        } else if (uomList.uomDetails == "D") {
          item.newQty = stockAvailable / (uom[2].conversion);
        }
      } else {
        item.stockAvailable = 0;
      }
    }

    // Against Indent Item Selection
    var isSelectedItem = function (item) {
      return item.isSelectedItem;
    }

    // select indent row
    var SelectAllIndentItems = function (indent, items) {
      if (indent.isSelectedIndent) {
        items.map(function(x){
          self.model.indentItemDetails.push(x);
          x.isSelectedItem = true;
          x.indentId = indent.indentId;
          return self.model.intendItemIds.push(x.itemId);
        });
      } else {
        self.model.intendItemIds = [];
        items.forEach(function (x) {
          x.isSelectedItem = false;
        });
        items.map(function(x){
          var idx = self.model.indentItemDetails.indexOf(x);
          if (idx > -1) {
            self.model.indentItemDetails.splice(idx, 1);
          }
        });
      }
    }

    var SelectIndentItem = function (item, indent) {
      var itemIds = self.model.intendItemIds;
      if (item.isSelectedItem) {
        itemIds.push(item.itemId);
        self.model.indentItemDetails.push(item);
      } else {
        var index = itemIds.indexOf(item.isSelectedItem);
        if (index > -1) {
          itemIds.splice(index, 1);
        }

        var itemIndex = self.model.indentItemDetails.indexOf(item);
        if (itemIndex > -1) {
          self.model.indentItemDetails.splice(itemIndex, 1);
        }
      }

      if (indent.items.every(isSelectedItem)) {
        indent.isSelectedIndent = true;
      } else {
        indent.isSelectedIndent = false;
      }
    }

    var getSelectedItems = function(){
      var hash = {};
      self.model.indentItemDetails.forEach(function(item) {
        var itemId = item.itemId;
        var indentId = item.id;
        if(hash.hasOwnProperty(itemId)){
          hash[itemId][indentId] = { pendingQuantity: item.pendingQuantity }
        }else {
          hash[itemId] = {};
          hash[itemId][indentId] = { pendingQuantity: item.pendingQuantity }
        }
      });
      return hash;
    }

    var updateItemDetails = function(responseData, selectedItems) {
      var itemDetailsHash = {};
      self.model.itemDetails.forEach(function(itemDetail){
        itemDetailsHash[itemDetail.itemId] = itemDetail;
      });

      responseData.forEach(function(item){
        var itemId = item.itemId;
        if(selectedItems.hasOwnProperty(itemId)) {
          var selectedItem = selectedItems[itemId]; 
          if(itemDetailsHash.hasOwnProperty(itemId)) {
            //found in old list
            var itemDetail = itemDetailsHash[itemId];
            if(itemDetail.indentDetailId && itemDetail.indentDetailId.length) {
              // indentDetailId List is present
              var indentDetailIds = itemDetail.indentDetailId;
               for(var indentId in selectedItem){
                if(indentDetailIds.indexOf(indentId) == -1){
                  // indentId not found indentDetailIds list
                  itemDetail.indentDetailId.push(indentId);
                  itemDetail.pendingQuantity += selectedItem[indentId].pendingQuantity;  
                }
              }
            }else {
              // indentDetailId List not present
              itemDetail.pendingQuantity = 0;
              itemDetail.indentDetailId = [];
              for(var indentId in selectedItem){
                itemDetail.indentDetailId.push(indentId);
                itemDetail.pendingQuantity += selectedItem[indentId].pendingQuantity;
              }
            }
          }else {
            // not found in old list
            item.pendingQuantity = 0;
            item.indentDetailId = [];
            for(var indentId in selectedItem){
              item.indentDetailId.push(indentId);
              item.pendingQuantity += selectedItem[indentId].pendingQuantity;
              self.model.itemDetails.push(item);
              selectUOM(item);
            }
          }
        }
      });
      console.log(self.model.itemDetails);
    }

    // Add Indent Items to List
    var AddIndentItems = function () {
      if (self.model.intendItemIds.length == 0) {
        growl.error('Please Select Items to Add');
        return false;
      } else {
        var URI = CONSTANTS.GLOBAL.ITEM_DETAILS_API;

        var selectedItems = getSelectedItems();
        var selectedIds = [];
        for(var key in selectedItems){
          selectedIds.push(key);
        }
        
        var data = {
          "itemIds": selectedIds,
          "storeId": self.model.store.id
        };
    
        $rootScope.startLoader();
        GenericService.serviceAction("POST", URI, data).then(
          function (response) {
            var responseData = response.data;
            
            updateItemDetails(responseData, selectedItems);
            self.model.indentItemDetails = [];
            self.model.intendItemIds = [];
            self.model.items = [];
            
            var popup = angular.element('#getRequestWasteStore');
            popup.modal('hide');

            $rootScope.stopLoader();
          },
          function (err) {
            $rootScope.stopLoader();
            self.model.SelectedItemsIds = [];
            growl.error(err.data.message);
          });
      }
    }

// Direct Request Items Selection

    var isSelected = function (item) {
      return item.isSelected;
    }

    var SelectAllItems = function () {
      if (self.model.selectAllRows) {
        self.model.SelectedItemsIds = self.model.items.map(function (item) {
          item.isSelected = true;
          return item.itemId;
        });
      } else {
        self.model.SelectedItemsIds = [];
        self.model.items.forEach(function (item) {
          item.isSelected = false;
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

      if (self.model.items.every(isSelected)) {
        self.model.selectAllRows = true;
      } else {
        self.model.selectAllRows = false;
      }
    }

    var RemoveItem = function (item) {
      var newArray = [];

      self.model.itemDetails.map(function(obj){
        if(obj !== item){
          newArray.push(obj);
        }
      });
      self.model.itemDetails = newArray;
    }

    var SavePurchaseRequest = function () {
      if (ValidateItems()) {
        if (!self.model.purchaseId) {
          CreateLocalPR();
        } else {
          UpdateLocalPR();
        }
      }
    }

    var CreateLocalPR = function () {
      var localPurchase = self.model;

      var URI = CONSTANTS.PROCUREMENT.PURCHASE.LOCAL_PURCHASE_REQUEST.SAVE_API;

      var data = {
        "storeId": localPurchase.store.id,
        "priorityId": localPurchase.priority ? localPurchase.priority.id : undefined,
        "assetTypeId": localPurchase.assetType,
        "prTypeId": localPurchase.PRType,
        "remark": localPurchase.remark,
        "statusId": setStatus('New'),
        "approvalStatusId": setStatus('New'),
        "items": []
      };

      angular.forEach(localPurchase.itemDetails, function (item, key) {
        var purchaseItem = {
          "itemId": 6,
          "uomTypeId": item.selectedUom.uomTypeId,
          "uomUnitId": item.selectedUom.uomUnitId,
          "quantity": item.quantity,
          "cop": item.cop,
          "amountCop": item.quantity * item.cop,
          "specification": item.itemSpecifications,
          "statusId": setStatus('New'),
          "indentId": item.indentDetailId
        }

        data.items.push(purchaseItem);
      });

      $rootScope.startLoader();
      GenericService.serviceAction("POST", URI, data).then(
        function (response) {
          ClearRequests();
          $rootScope.stopLoader();
          NavigateToLocalPR();
          growl.success(response.data.message, {title: response.status});
        },
        function (err) {
          $rootScope.stopLoader();
          growl.error(err.data.message, {title: err.status});
        });
    }

    var UpdateLocalPR = function () {
      var localPurchase = self.model;

      var URI = CONSTANTS.PROCUREMENT.PURCHASE.LOCAL_PURCHASE_REQUEST.SAVE_API + localPurchase.purchaseId;

      var data = {
        "priorityId": localPurchase.priority.id,
        "remark": localPurchase.remark,
        "items": []
      };
      angular.forEach(self.model.itemDetails, function (item, key) {
        var indentItem = {
          "id": item.id,
          "itemId": item.itemId,
          "uomUnitId": item.selectedUom.uomUnitId,
          "uomTypeId": item.selectedUom.uomTypeId,
          "quantity": item.quantity,
          "cop": item.cop,
          "amountCop": item.quantity * item.cop,
          "specification": item.itemSpecifications,
          "statusId": setStatus('New'),
          "indentId": item.indentDetailId
        }

        data.items.push(indentItem);
      });

      $rootScope.startLoader();
      GenericService.serviceAction("PATCH", URI, data).then(
        function (response) {
          ClearRequests();
          $rootScope.stopLoader();
          NavigateToLocalPR();
          growl.success(response.data.message, {title: response.status});
        },
        function (err) {
          $rootScope.stopLoader();
          growl.error(err.data.message, {title: err.status});
        });
    }

    var NavigateToLocalPR = function () {
      $state.go('localPurchhaseRequest');
    }

    var ClearRequests = function () {
      var localPurchase = self.model;

      localPurchase.store = localPurchase.stores[0];
      localPurchase.priority = undefined;
      localPurchase.remark = "";
      localPurchase.SelectedItemsIds = [];
      localPurchase.itemDetails = [];
      localPurchase.items = [];
      localPurchase.purchaseId = null;
    }

    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("addNewlocalPurchhaseRequest", {
      url: "/addNewlocalPurchhaseRequest",
      templateUrl: 'views/procurement/purchase/add-new-local-purchase-request.html',
      controller: "addNewLocalPR.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("addNewLocalPR.Controller", addNewLocalPRController);
})();