(function () {
  "use strict";

  function addNewQuotationController($scope, $rootScope, $http, $filter, $state, CONSTANTS, GenericService, commonDetailService, growl, CurrencyService, StatusService) {
    var self = this;

    function initializeController() {
      $rootScope.loginpage = true;

      self.model = {
        fromDate: new Date(),
        toDate: new Date(),
        fromDateOptions: {
          formatYear: 'yyyy',
          showWeeks: false,
          maxDate: new Date()
        },
        toDateOptions: {
          formatYear: 'yyyy',
          showWeeks: false,
          maxDate: new Date()
        },
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
          "storeName": "Select Store"
        }],
        toStores: [{
          "storeName": "Select Store"
        }],
        paymentModes: [{
          "code": "Select Payment Mode"
        }],
        assetTypes: [{
          "type": "Select Asset Type"
        }],
        SelectedItemsIds: [],
        itemDetails: [],
        intendItemIds: [],
        indentItemDetails: [],
        prQuantity: [],
        prDetails: [],
        prModalList: [],
        prDetailList: [],
        expectedAccessories: [], 
        accessoryItem: [],
        termsVisible: 1,
        termsList: [],
        search: {
          itemName: undefined,
          itemCode: undefined
        }
      };

      StatusService.GetStatus().then(function (status) {
        self.model.statuses = status;
      });

      CurrencyService.GetCurrency().then(function(currency){
        self.model.Currency = currency;
      });

      //Methods
      self.model.OpenFromDate = OpenFromDate;
      self.model.OpenToDate = OpenToDate;
      self.model.PopulateAssetCategoryDropdown = PopulateAssetCategoryDropdown;
      self.model.PopulateProductCategory = PopulateProductCategory;
      self.model.GetItemList = GetItemList;
      self.model.SelectAllItems = SelectAllItems;
      self.model.SelectItem = SelectItem;
      self.model.AddItems = AddItems;
      self.model.RemoveItem = RemoveItem;
      self.model.SaveQuotationEnquiry = SaveQuotationEnquiry;
      self.model.NavigateToQuotationEnquiry = NavigateToQuotationEnquiry;
      self.model.ChangeRequestType = ChangeRequestType;
      self.model.GetIndentList = GetIndentList;
      self.model.Expand = Expand;
      self.model.SelectAllIndentItems = SelectAllIndentItems;
      self.model.SelectIndentItem = SelectIndentItem;
      self.model.AddIndentItems = AddIndentItems;
      self.model.OpenPopup = OpenPopup;
      self.model.ClosePopup = ClosePopup;
      self.model.fillPRModalList = fillPRModalList;
      self.model.PrDetails = PrDetails;
      self.model.FillAccessoryDetails = FillAccessoryDetails;
      self.model.AddAccessory = AddAccessory;
      self.model.LessAccessory = LessAccessory;
      self.model.SaveAccessoryDetails = SaveAccessoryDetails;
      self.model.ViewTerms = ViewTerms;
      self.model.SaveTerms = SaveTerms;

      PopulateDropdown();
    }

    var ViewTerms = function (item) {
      self.model.termsList = item;
      self.model.warrantyPeriod = item.warrantyPeriod;
      self.model.supportTerms = item.supportTerms;
      self.model.paymentTerms = item.paymentTerms;
      self.model.technicalSpecification = item.technicalSpecification;

      var popup = angular.element('#vendorSelectionDetailsModal2');
      popup.modal('show');
    }

    var SaveTerms = function () {
      self.model.termsList["warrantyPeriod"] = self.model.warrantyPeriod;
      self.model.termsList["supportTerms"] = self.model.supportTerms;
      self.model.termsList["paymentTerms"] = self.model.paymentTerms;
      self.model.termsList["technicalSpecification"] = self.model.technicalSpecification;

      ClosePopup('Close', 'vendorSelectionDetailsModal2');
    }

    var PrDetails = function (prDetailList) {
      self.model.prDetailList = prDetailList;
      var popup = angular.element('#PRDetails');
      popup.modal('show');
    }

    var fillPRModalList = function (list) {
      self.model.prModalList = list;
    }

    var FillAccessoryDetails = function (item) {
      self.model.accessoryItem = item;
      self.model.expectedAccessories = item.expectedAccessories;
    }

    var AddAccessory = function () {
      self.model.expectedAccessories.push({"name": "", "details": ""});
    }

    var LessAccessory = function (item) {
      var index = self.model.expectedAccessories.indexOf(item);
      self.model.expectedAccessories.splice(index, 1);
    }

    var SaveAccessoryDetails = function () {
      self.model.accessoryItem.expectedAccessories = self.model.expectedAccessories;
      ClosePopup('Close', 'AccessoriesDetailsQEModal');
    }

    var setStatus = function (x) {
      var status = self.model.statuses.find(function (obj) {
        return obj.status == x;
      });

      return status.id;
    }

    var OpenFromDate = function () {
      self.model.fromDateOpened = true;
      self.model.toDateOptions.minDate = self.model.fromDate;
    }

    var OpenToDate = function () {
      self.model.toDateOpened = true;
      self.model.toDateOptions.minDate = self.model.fromDate;
    }

    var OpenPopup = function (action, modal) {
      self.model.action = action;
      var popup = angular.element('#' + modal);
      popup.modal('show');
    }

    var ClosePopup = function (action, modal) {
      self.model.action = action;
      var popup = angular.element('#' + modal);
      popup.modal('hide');
    }

    var PopulateDropdown = function () {
      try {
        $rootScope.startLoader();
        self.model.store = self.model.stores[0];
        self.model.assetType = self.model.assetTypes[0];

        var URI = CONSTANTS.PROCUREMENT.QUOTATION.QUOTATION_ENQUIRY.DROPDOWN_API;

        GenericService.serviceAction("GET", URI, {}).then(
          function (response) {
            self.model.stores = self.model.stores.concat(response.data.store);
            self.model.assetTypes = self.model.assetTypes.concat(response.data.assetType);

            var prId = commonDetailService.getDataId();
            $rootScope.stopLoader();
            if (!!prId) {
              commonDetailService.setDataId(null);
              GetQuotationEnquiry(prId);
            }
          },
          function (err) {
            $rootScope.stopLoader();
          });
      } catch (error) {
        alert(error);
      }
    }

    var ChangeRequestType = function (requestType) {
      if (ValidateDetails()) {
        if (requestType == 1) {
          self.model.action = 'Open';
          var popup = angular.element('#againstPRmodal');
          self.model.fromDate = new Date();
          self.model.toDate = new Date();
          self.model.prNumber = undefined;
          self.model.searchItem = undefined;
          self.model.indents = [];
          popup.modal('show');
        } else if (requestType == 2) {
          self.model.items = [];
          PopulateAssetCategoryDropdown();
          self.model.search.itemName = undefined;
          self.model.search.itemCode = undefined;
          self.model.action = 'Open';
          var popup = angular.element('#addNewItemWasteStore');
          popup.modal('show');
        }
      }
    }

    var GetQuotationEnquiry = function (prId) {
      var URI = CONSTANTS.PROCUREMENT.QUOTATION.QUOTATION_ENQUIRY.SAVE_API + prId;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          self.model.quotationId = response.data.id;
          self.model.store = self.model.stores.find(function (store) {
            return response.data.storeId == store.id;
          });
          self.model.assetType = self.model.assetTypes.find(function (assetType) {
            return response.data.assetTypeId == assetType.id;
          });

          self.model.requestType = response.data.requestTypeId;
          self.model.remark = response.data.remark;

          self.model.itemDetails = [];

          angular.forEach(response.data.items, function (item) {

            var expectedAccessories = [];
            angular.forEach(item.expectedAccessories, function (accessoryItem) {
              var item = {
                "id": accessoryItem.id,
                "name": accessoryItem.name,
                "details": accessoryItem.details
              }
              expectedAccessories.push(item);
            });

            var prItem = {
              "id": item.id,
              "itemId": item.itemId,
              "itemCode": item.itemCode,
              "itemName": item.itemName,
              "uom": item.uom,
              "prQuantity": item.quantity,
              "prDetailList": item.prDetails,
              "expectedAccessories": expectedAccessories,
              "warrantyPeriod": item.warrantyPeriod,
              "supportTerms": item.supportTerms,
              "paymentTerms": item.paymentTerms,
              "technicalSpecification": item.technicalSpecification,
              "selectedUom": item.uom.find(function (uom) {
                return item.uomTypeId == uom.uomTypeId;
              }),
            }

            self.model.itemDetails.push(prItem);
          });

          $rootScope.stopLoader();
        },
        function (err) {
          $rootScope.stopLoader();
          growl.error(err.data.message);
        });
    }

    var ValidateDetails = function () {
      var isValid = true;

      if (!self.model.store.id) {
        self.model.isStoreValid = true;
        isValid = false;
      }

      if (!self.model.assetType.id) {
        self.model.isAssetTypeValid = true;
        isValid = false;
      }

      if (!self.model.requestType) {
        self.model.isRequestTypeValid = true;
        isValid = false;
      }

      if (!self.model.store.id || !self.model.assetType.id || !self.model.requestType) {
        growl.error('Please Select Required Fields');
        isValid = false;
      }

      return isValid;
    }

    var ValidateItems = function () {
      var isValidItem = true;
      if (self.model.itemDetails.length <= 0) {
        growl.error('Add Item to Save Quotation Enquiry');
        isValidItem = false;
      } else {
        angular.forEach(self.model.itemDetails, function (item, index) {
          if (!item.prQuantity || item.prQuantity <= 0) {
            self.model.prQuantity[index] = true;
            growl.error('Please Enter Valid Quantity for Items');
            isValidItem = false;
          }
        });
      }

      return isValidItem;
    }

    var PopulateAssetCategoryDropdown = function () {
      var URI = CONSTANTS.GLOBAL.ASSET_CATEGORY_API + 1;

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

    var PopulateProductCategory = function (assetCategory) {
      var URI = CONSTANTS.GLOBAL.PRODUCT_CATEGORY_API + self.model.assetCategory.id;

      self.model.productCategory = self.model.productCategories[0];
      self.model.productCategories.splice(1);

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          self.model.productCategories = self.model.productCategories.concat(response.data);
          $rootScope.stopLoader();
        },
        function (err) {
          $rootScope.stopLoader();
        });
    }

    var ValidateDate = function () {
      var isValid = true;
      if (self.model.fromDate > self.model.toDate) {
        growl.error('From Date should lesser than To Date');
        isValid = false;
      }

      return isValid;
    }

    var GetIndentList = function () {
      if (ValidateDate()) {
        var URI = CONSTANTS.GLOBAL.AGAINST_PR_SEARCH;

        var data = {
          "fromDate": moment(self.model.fromDate).format('YYYY-MM-DD'),
          "toDate": moment(self.model.toDate).format('YYYY-MM-DD'),
          "prNo": self.model.prNumber
        }

        GenericService.serviceAction("POST", URI, data).then(
          function (response) {
            self.model.indents = response.data;
            angular.forEach(self.model.indents, function (pr) {
              pr["isChecked"] = false;
              angular.forEach(pr.items, function (item) {
                item["isChecked"] = false;
              });
            });

            $rootScope.stopLoader();
          },
          function (err) {
            $rootScope.stopLoader();
            self.model.indents = [];
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
        growl.error('Please Select Items to Add');
        return false;
      } else {
        var URI = CONSTANTS.GLOBAL.DIRECT_PR_DETAILS;
        var data = {
          "itemIds": self.model.SelectedItemsIds,
          "storeId": self.model.store.id
        };

        $rootScope.startLoader();
        GenericService.serviceAction("POST", URI, data).then(
          function (response) {
            response.data.itemDetailsList.forEach(function (value, key) {
              value["expectedAccessories"] = [{"name": "", "details": ""}];
              var isFound = self.model.itemDetails.find(function (x) {
                return x.itemId == value.itemId;
              });
              if (!isFound) {
                self.model.itemDetails.push(value);
                selectUOM(value);
              }
            });
            
            var popup = angular.element('#addNewItemWasteStore');
            popup.modal('hide');

            self.model.SelectedItemsIds = [];
            self.model.items = [];
            $rootScope.stopLoader();
          },
          function (err) {
            $rootScope.stopLoader();
            self.model.SelectedItemsIds = [];
            growl.error(err.data.message);
          });
      }
    }

    var selectUOM = function (item) {
      item.selectedUom = item.uom[0];
    }

    // Against Indent Item Selection
    var isSelectedItem = function (item) {
      return item.isSelectedItem;
    }

    // select indent row
    var SelectAllIndentItems = function (indent, items) {
      if (indent.isSelectedIndent) {
        items.map(function (x) {
          self.model.indentItemDetails.push(x);
          x.isSelectedItem = true;
          x.isChecked = true;
          indent.isChecked = true;
          return self.model.intendItemIds.push(x.itemId);
        });
      } else {
        self.model.intendItemIds = [];
        items.forEach(function (x) {
          x.isSelectedItem = false;
          x.isChecked = false;
        });
        indent.isChecked = false;
        items.map(function (x) {
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
        item.isChecked = true;
        indent.isChecked = true;
        itemIds.push(item.itemId);
        self.model.indentItemDetails.push(item);
      } else {
        item.isChecked = false;
        indent.isChecked = false;
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

    var updatePrItemDetails = function (prResponseData) {
      var newItemsHash = {};
      var itemDetailsHash = {};
      self.model.itemDetails.forEach(function (itemDetail) {
        itemDetailsHash[itemDetail.itemId] = itemDetail;
      });

      prResponseData.forEach(function (prItem) {
        var prNo = prItem.prNo;
        prItem.items.forEach(function (item) {
          var itemId = item.itemId;
          var prId = item.id;
          var prQuantity = Number(item.prQuantity);
          var prObj = { id: prId, prNo: prNo, prQuantity: prQuantity };

          if (itemDetailsHash.hasOwnProperty(itemId)) {
            // item found in already saved list
            var itemDetail = itemDetailsHash[itemId];
            if (itemDetail.prIds && itemDetail.prIds.length) {
              // prIds list is present
              if (itemDetail.prIds.indexOf(prId) == -1) {
                itemDetail.prDetailList.push(prObj);
                itemDetail.prIds.push(prId);
                // itemDetail.prQuantity = item.prQuantity
              }
            } else {
              // prIds list not present
              itemDetail.prDetailList = [prObj]
              itemDetail.prIds = [prId];
            }

          } else if (newItemsHash.hasOwnProperty(itemId)) {
            // item found in new item hash
            var savedItem = newItemsHash[itemId];
            savedItem.prDetailList.push(prObj);
            savedItem.prIds.push(prId);
          } else {
            // item not found in item hash
            var clonedItem = JSON.parse(JSON.stringify(item));
            delete clonedItem.id;
            delete clonedItem.prQuantity;
            clonedItem.prDetailList = [prObj]
            clonedItem.prIds = [prId];
            newItemsHash[itemId] = clonedItem;
          }
        });
      });

      for (var itemId in newItemsHash) {
        newItemsHash[itemId]["expectedAccessories"] = [{"name": "", "details": ""}];
        self.model.itemDetails.push(newItemsHash[itemId]);
        // selectDefaultUOM(self.model.itemDetails);
        selectUOM(item);
      }

      console.log(self.model.itemDetails, "self.model.itemDetails");
    }

    var SelectedItems = function () {
      self.model.prDetails = [];
      var itemIdArray = [];
      angular.forEach(self.model.indents, function (pr) {
        var prData = null;
        if (pr.isChecked) {
          prData = {
            "prId": pr.id,
            "itemIds": []
          }
        }

        angular.forEach(pr.items, function (item) {
          if (item.isChecked) {
            prData.itemIds.push(item.itemId);
          }
        });

        if (pr.isChecked && prData.itemIds.length > 0)
          self.model.prDetails.push(prData);
      });
    }

    // Add Indent Items to List
    var AddIndentItems = function () {
      if (self.model.intendItemIds.length == 0) {
        growl.error('Please Select Items to Add');
        return false;
      } else {
        var URI = CONSTANTS.GLOBAL.AGAINST_PR_DETAILS;

        SelectedItems();

        var data = self.model.prDetails;

        $rootScope.startLoader();
        GenericService.serviceAction("POST", URI, data).then(
          function (response) {
            var responseData = response.data;
            var purchaseRequestListResponse = responseData.purchaseRequestList;
            updatePrItemDetails(purchaseRequestListResponse);

            self.model.indentItemDetails = [];
            self.model.intendItemIds = [];
            self.model.items = [];
            self.model.indents = [];

            var popup = angular.element('#againstPRmodal');
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

      self.model.itemDetails.map(function (obj) {
        if (obj.itemId !== item.itemId) {
          newArray.push(obj);
        }
      });

      self.model.itemDetails = newArray;
    }

    var SaveQuotationEnquiry = function () {
      if (ValidateItems()) {
        if (!self.model.quotationId) {
          CreateQuotationEnquiry();
        } else {
          UpdateQuotationEnquiry();
        }
      }
    }

    var CreateQuotationEnquiry = function () {
      var URI = CONSTANTS.PROCUREMENT.QUOTATION.QUOTATION_ENQUIRY.SAVE_API;

      var data = {
        "storeId": self.model.store.id,
        "assetTypeId": self.model.assetType.id,
        "requestTypeId": self.model.requestType,
        "remark": self.model.remark,
        "statusId": setStatus('New'),
        "approvalStatusId": setStatus('New'),
        "items": []
      };

      angular.forEach(self.model.itemDetails, function (item, key) {
        var prItem = {
          "itemId": item.itemId,
          "quantity": item.prQuantity,
          "uomTypeId": item.selectedUom.uomTypeId,
          "uomUnitId": item.selectedUom.uomUnitId,
          "statusId": setStatus('New'),
          "prId": item.prIds,
          "expectedAccessories": item.expectedAccessories,
          "warrantyPeriod": item.warrantyPeriod,
          "supportTerms": item.supportTerms,
          "paymentTerms": item.paymentTerms,
          "technicalSpecification": item.technicalSpecification
        }

        data.items.push(prItem);
      });

      $rootScope.startLoader();
      GenericService.serviceAction("POST", URI, data).then(
        function (response) {
          $rootScope.stopLoader();
          NavigateToQuotationEnquiry();
          growl.success(response.data.message);
        },
        function (err) {
          $rootScope.stopLoader();
          growl.error(err.data.message);
        });
    }

    var UpdateQuotationEnquiry = function () {
      var URI = CONSTANTS.PROCUREMENT.QUOTATION.QUOTATION_ENQUIRY.SAVE_API + self.model.quotationId;

      var data = {
        "id": self.model.quotationId,
        "remark": self.model.remark,
        "items": []
      };
      angular.forEach(self.model.itemDetails, function (item, key) {
        var expectedAccessories = [];
        angular.forEach(item.expectedAccessories, function (accessoryItem) {
          var item = {
            "id": accessoryItem.id,
            "name": accessoryItem.name,
            "details": accessoryItem.details
          }
          expectedAccessories.push(item);
        });
        
        var prItem = {
          "id": item.id,
          "uomTypeId": item.selectedUom.uomTypeId,
          "uomUnitId": item.selectedUom.uomUnitId,
          "quantity": item.prQuantity,
          "prId": item.prIds,
          "expectedAccessories": expectedAccessories,
          "warrantyPeriod": item.warrantyPeriod,
          "supportTerms": item.supportTerms,
          "paymentTerms": item.paymentTerms,
          "technicalSpecification": item.technicalSpecification
        }

        data.items.push(prItem);
      });

      $rootScope.startLoader();
      GenericService.serviceAction("PATCH", URI, data).then(
        function (response) {
          $rootScope.stopLoader();
          NavigateToQuotationEnquiry();
          growl.success(response.data.message);
        },
        function (err) {
          $rootScope.stopLoader();
          growl.error(err.data.message);
        });
    }

    var NavigateToQuotationEnquiry = function () {
      $state.go('quotationEnquiry');
    }

    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("addItemquotationEnquiry", {
      url: "/addItemquotationEnquiry",
      templateUrl: 'views/procurement/quotation/add-item-quotation-enquiry.html',
      controller: "AddNewQuotation.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("AddNewQuotation.Controller", addNewQuotationController);
})();