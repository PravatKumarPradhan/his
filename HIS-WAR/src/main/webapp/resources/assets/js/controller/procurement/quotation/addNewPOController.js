(function () {
  "use strict";

  function addNewPOController($scope, $rootScope, $http, $filter, $state, CONSTANTS, GenericService, commonDetailService, growl, CurrencyService, StatusService) {
    var self = this;

    //Default Constructor
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
        stagedQtyDateOptions: {
          formatYear: 'yyyy',
          showWeeks: false
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
        poTypes: [{
          "type": "Select PO Type"
        }],
        SelectedItemsIds: [],
        itemDetails: [],
        intendItemIds: [],
        indentItemDetails: [],
        bonusQuantity: [],
        costOfPurchase: [],
        POAmountMRP: [],
        isValidPoQuantity: [],
        isUOMValid: [],
        screenId: 1,
        selectedUOMList: [],
        selectedUOMUnit: [],
        bonusSelectedUOMList: [],
        bonusSelectedUOMUnit: [],
        stageQuantityList: [],
        selectedItemIndex: 0,
        bonusSelectedItemIndex: 0,
        selectedIndex: null,
        selectedOtherChargeIndex: null,
        otherChargesDropdownList: [],
        otherChargesList: [],
        prDetails: [],
        isConsignment: false,
        prModalList: [],
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

      self.model.OpenFromDate = OpenFromDate;
      self.model.OpenStagedQtyDate = OpenStagedQtyDate;
      self.model.OpenToDate = OpenToDate;
      self.model.PopulateAssetCategoryDropdown = PopulateAssetCategoryDropdown;
      self.model.PopulateProductCategory = PopulateProductCategory;
      self.model.GetItemList = GetItemList;
      self.model.SelectAllItems = SelectAllItems;
      self.model.SelectItem = SelectItem;
      self.model.AddItems = AddItems;
      self.model.RemoveItem = RemoveItem;
      self.model.SavePurchaseOrder = SavePurchaseOrder;
      self.model.NavigateToPO = NavigateToPO;
      self.model.ChangePRType = ChangePRType;
      self.model.GetIndentList = GetIndentList;
      self.model.Expand = Expand;
      self.model.SelectAllIndentItems = SelectAllIndentItems;
      self.model.SelectIndentItem = SelectIndentItem;
      self.model.AddIndentItems = AddIndentItems;
      self.model.SearchVendorNames = SearchVendorNames;
      self.model.AddVendorName = AddVendorName;
      self.model.OpenPopup = OpenPopup;
      self.model.ClosePopup = ClosePopup;
      self.model.uomInfoOnClick = uomInfoOnClick;
      self.model.uomModalOnClick = uomModalOnClick;
      self.model.bonusUomInfoOnClick = bonusUomInfoOnClick;
      self.model.bonusUomModalOnClick = bonusUomModalOnClick;
      self.model.isConsignmentFlag = isConsignmentFlag;
      self.model.fillPOQuantity = fillPOQuantity;
      self.model.addPOQuantity = addPOQuantity;
      self.model.lessPOQuantity = lessPOQuantity;
      self.model.sumPOQuantity = sumPOQuantity;
      self.model.AddOtherCharge = AddOtherCharge;
      self.model.lessOtherCharge = lessOtherCharge;
      self.model.sumOtherCharges = sumOtherCharges;
      self.model.ApplyDiscount = ApplyDiscount;
      self.model.fillOtherChargesList = fillOtherChargesList;
      self.model.fillPRModalList = fillPRModalList;
      self.model.SetDecimal = SetDecimal;

      PopulateDropdown();
    }
    var fillPRModalList = function (list) {
      self.model.prModalList = list;
    }

    var fillOtherChargesList = function (item) {
      self.model.selectedOtherChargeIndex = self.model.itemDetails.indexOf(item);
      angular.forEach(item.selectedOtherCharges, function (value, key) {
        value.otherCharge = self.model.otherChargesDropdownList[0];
      });

      item.selectedOtherCharges.forEach(function(othercharge){
        othercharge.amount = parseFloat(othercharge.amount);
      });
      self.model.otherChargesList = item.selectedOtherCharges;
    }

    var AddOtherCharge = function (ocId, index) {
      self.model.otherChargesList.push({ otherChargesId: ocId, amount: 0 });
      self.model.otherChargesList[index+1].otherCharge = self.model.otherChargesList[index].otherCharge;
    }

    var lessOtherCharge = function (item, idx) {
      var index = self.model.otherChargesList.indexOf(item);
      self.model.otherChargesList.splice(index, 1);
    }

    var sumOtherCharges = function () {
      var total = 0;
      angular.forEach(self.model.otherChargesList, function (value, key) {
        delete value['otherCharge'];
        total = total + parseFloat(value.amount);
      });
      self.model.itemDetails[self.model.selectedOtherChargeIndex].otherChargesTotal = total;
      self.model.ClosePopup('close', 'otherCharges_Modal');
    }

    var fillPOQuantity = function (item) {
      item.stageQuantityList.forEach(function (x) {
        x.stagedDate = new Date(x.stagedDate);
      });
      self.model.stageQuantityList = item.stageQuantityList;
      self.model.selectedIndex = self.model.itemDetails.indexOf(item);
    }

    var addPOQuantity = function (date) {
      self.model.stageQuantityList.push({ stagedDate: date, quantity: parseInt(0) });
    }

    var lessPOQuantity = function (item) {
      var index = self.model.stageQuantityList.indexOf(item);
      self.model.stageQuantityList.splice(index, 1);
    }

    var sumPOQuantity = function () {
      var total = 0;
      angular.forEach(self.model.stageQuantityList, function (value, key) {
        delete value['stagedQtyDate'];
        value['stagedDate'] = moment(value['stagedDate']).format('YYYY-MM-DD');
        self.model.isValidPoQuantity[key] = false;
        total = total + parseInt(value.quantity);
      });
      self.model.itemDetails[self.model.selectedIndex].poQuantity = total;
      self.model.ClosePopup('close', 'AddQtyModal_ETOitemConfig');
    }

    var setStatus = function (x) {
      var status = self.model.statuses.find(function (obj) {
        return obj.status == x;
      });

      return status.id;
    }

    var OpenStagedQtyDate = function (quantity) {
      quantity.stagedQtyDate = true;
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

    var isConsignmentFlag = function (flag) {
      if (flag) {
        self.model.isConsignment = true;
      } else {
        self.model.isConsignment = false;
      }
    }

    var PopulateDropdown = function () {
      try {
        $rootScope.startLoader();
        var purchaseOrder = self.model;
        purchaseOrder.store = purchaseOrder.stores[0];
        purchaseOrder.paymentMode = purchaseOrder.paymentModes[0];
        purchaseOrder.poType = purchaseOrder.poTypes[0];

        var URI = CONSTANTS.PROCUREMENT.QUOTATION.PURCHSE_ORDER.DROPDOWN_API;

        GenericService.serviceAction("GET", URI, {}).then(
          function (response) {
            purchaseOrder.stores = purchaseOrder.stores.concat(response.data.store);
            purchaseOrder.paymentModes = purchaseOrder.paymentModes.concat(response.data.paymentMode);
            purchaseOrder.poTypes = purchaseOrder.poTypes.concat(response.data.assetType);

            // for Test 
            // purchaseOrder.store = purchaseOrder.stores[1];
            // purchaseOrder.paymentMode = purchaseOrder.paymentModes[1];
            // purchaseOrder.poType = response.data.assetType[0];

            var poId = commonDetailService.getDataId();
            $rootScope.stopLoader();
            if (!!poId) {
              commonDetailService.setDataId(null);
              GetPO(poId);
            }
          },
          function (err) {
            $rootScope.stopLoader();
          });
      } catch (error) {
        alert(error);
      }
    }

    function SearchVendorNames(search) {
      if (search.length < 3) return;

      if ((!self.model.prevSearch || self.model.prevSearch != search) && (search.length == 3)) {
        self.model.prevSearch = search;
        var URI = CONSTANTS.GLOBAL.VENDOR_SEARCH_API + search;
        $rootScope.startLoader();
        return GenericService.serviceAction("GET", URI, {})
          .then(function (response) {
            if (!!response.data && response.data.length > 0) {
              self.model.vendorNames = response.data;
              $rootScope.stopLoader();
              return $filter('filter')(self.model.vendorNames, {
                $: search
              });
            } else {
              $rootScope.stopLoader();
              return nameNotFound(search);
            }
          }, function (err) {
            $rootScope.stopLoader();
            return nameNotFound(search);
          });
      } else {
        if (!!self.model.vendorNames && self.model.vendorNames.length > 0 && self.model.vendorNames[0].itemFound != undefined && !self.model.vendorNames[0].itemFound) {
          return nameNotFound(search);
        } else {
          return $filter('filter')(self.model.vendorNames, {
            $: search
          });
        }
      }
    }

    function nameNotFound(search) {
      var item = {
        "itemFound": false,
        "detail": "Vendor Not found with name " + search
      };
      self.model.vendorNames = [item];
      return self.model.vendorNames;
    }

    function AddVendorName(vendor) {
      self.model.vendorName = vendor.vendorName;
      self.model.vendorId = vendor.id;
    }

    var ChangePRType = function (prType) {
      if (ValidateDetails()) {
        if (prType == 1) {
          self.model.action = 'Open';
          var popup = angular.element('#addNewItemWasteStore');
          popup.modal('show');
          PopulateAssetCategoryDropdown();
          self.model.items = [];
          self.model.search.itemCode = undefined;
          self.model.search.itemName = undefined;
        } else if (prType == 3) {
          self.model.action = 'Open';
          var popup = angular.element('#againstPRmodal');
          popup.modal('show');
          self.model.fromDate = new Date();
          self.model.toDate = new Date();
          self.model.indents = [];
          self.model.searchIndent = undefined;
          self.model.prNumber = undefined;
        }
      }
    }

    var GetPO = function (poId) {

      var URI = CONSTANTS.PROCUREMENT.QUOTATION.PURCHSE_ORDER.SAVE_API + poId;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          self.model.store = self.model.stores.find(function (store) {
            return response.data.storeId == store.id;
          });
          self.model.poType = self.model.poTypes.find(function (potype) {
            return response.data.assetTypeId == potype.id;
          });
          self.model.paymentMode = self.model.paymentModes.find(function (payment) {
            return response.data.paymentModeId == payment.id;
          });

          self.model.otherChargesDropdownList = response.data.otherChargesList;
          self.model.poId = response.data.id;
          self.model.PRType = response.data.poTypeId;
          self.model.vendorId = response.data.vendorId;
          self.model.vendorName = response.data.vendorName;
          self.model.purchaseOrderRemark = response.data.remark;
          self.model.isConsignment = response.data.isConsignment;
          self.model.discountType = response.data.discountTypeId;
          self.model.finalDiscount = response.data.discount;
          self.model.totalNetAmount = response.data.netAmount;
          self.model.roundOffAmount = response.data.roundOffAmount;
          self.model.totalAmount = response.data.totalAmount;
          self.model.totalDiscount = response.data.totalDiscount;
          self.model.totalOtherCharges = response.data.totalOtherAmount;
          self.model.totalTax = response.data.totalTaxAmount;

          self.model.itemDetails = [];

          angular.forEach(response.data.items, function (item) {
            var poUom = [];
            angular.forEach(item.poUom, function (uom) {
              poUom.push({
                id: uom.id,
                uomUnitId: { uomUnit: uom.uomUnit, uomUnitId: uom.uomUnitId },
                conversion: parseInt(uom.conversion),
                ipUom: uom.ipUom,
                opUom: uom.opUom,
                storeUom: uom.storeUom,
                uomTypeId: uom.uomTypeId
              });
            });

            var bonusUom = [];
            angular.forEach(item.bonusUom, function (uom) {
              bonusUom.push({
                id: uom.id,
                uomUnitId: { uomUnit: uom.uomUnit, uomUnitId: uom.uomUnitId },
                conversion: parseInt(uom.conversion),
                ipUom: uom.ipUom,
                opUom: uom.opUom,
                storeUom: uom.storeUom,
                uomTypeId: uom.uomTypeId
              });
            });

            var prDetailsList = [];
            angular.forEach(item.prId, function(prData){
              prDetailsList.push({
                "prNo": prData.prNo,
                "prQuantity": prData.approvedQuantity
              });
            });

            var poItem = {
              "id": item.id,
              "itemId": item.itemId,
              "itemCode": item.itemCode,
              "itemName": item.itemName,
              "poQuantity": item.poQuantity,
              "bonusQuantity": item.bonusQuantity,
              "costOfPurchase": item.cop,
              "POAmountMRP": item.mrp,
              "discountPercent": item.discount,
              "taxName": item.taxCode,
              "taxPercent": item.taxPercentage,
              "poAmount": item.totalAmount,
              "taxAmount": item.taxAmount,
              "manufacturerId": item.manufacturerId,
              "manufacturerName": item.manufacturerName,
              "netAmount": item.netAmount,
              "discountAmount": item.totalDiscount,
              "otherChargesTotal": item.totalOtherAmount,
              "specification": item.specification,
              "remark": item.remark,
              "uom": item.uom,
              "selectedUom": item.uom.find(function (uom) {
                return item.poUomTypeId == uom.uomTypeId;
              }),
              "bonusSelectedUom": item.uom.find(function (uom) {
                return item.bonusUomTypeId == uom.uomTypeId;
              }),
              "stageQuantityList": item.purchaseOrderStagedDto,
              "selectedOtherCharges": item.otherCharges,
              "poUom": poUom,
              "bonusUom": bonusUom,
              "prNumbersList": prDetailsList
            }
            self.model.itemDetails.push(poItem);
          });

          $rootScope.stopLoader();
        },
        function (err) {
          $rootScope.stopLoader();
          growl.error(err.data.message, { title: err.status });
        });
    }

    var ValidateDetails = function () {
      var isValid = true;

      if (!self.model.store.id) {
        self.model.isValidStore = true;
        isValid = false;
      }

      if (!self.model.poType.id) {
        self.model.isValidPOType = true;
        isValid = false;
      }

      if (!self.model.PRType) {
        self.model.isPRTypeValid = true;
        isValid = false;
      }

      if (!self.model.vendorId) {
        self.model.isVendorValid = true;
        isValid = false;
      }

      if (!self.model.store.id || !self.model.poType.id || !self.model.PRType) {
        growl.error('Select Required Fields');
        isValid = false;
      }

      return isValid;
    }

    var ValidateItems = function () {
      var isValidItem = true;
      if (self.model.itemDetails.length <= 0) {
        growl.error('Add Item to Save Purchase Order');
        isValidItem = false;
      } else {
        angular.forEach(self.model.itemDetails, function (item, index) {
          if (!item.bonusQuantity || item.bonusQuantity <= 0) {
            self.model.bonusQuantity[index] = true;
            isValidItem = false;
          }

          if (!item.costOfPurchase) {
            self.model.costOfPurchase[index] = true;
            isValidItem = false;
          }

          if (!item.POAmountMRP) {
            self.model.POAmountMRP[index] = true;
            isValidItem = false;
          }

          if (!item.poQuantity) {
            self.model.isValidPoQuantity[index] = true;
            growl.error('Enter Quantity for Items');
            isValidItem = false;
          }

          if (!item.taxPercent) {
            self.model.taxPercent[index] = true;
            isValidItem = false;
          }

          if (item.discountPercent >= 50) {
            self.model.discountPercent[index] = true;
            growl.error('Disount Should be less than 50%');
            isValidItem = false;
          }
        });

        if (!self.model.paymentMode.id) {
          isValidItem = false;
          self.model.isPaymentValid = true;
        }

        if (!self.model.vendorName) {
          isValidItem = false;
          self.model.isVendorValid = true;
        }

        if (!self.model.paymentMode.id || !self.model.vendorName) {
          growl.error('Select Required Fields');
          isValidItem = false;
        }
      }

      return isValidItem;
    }

    var SetDecimal = function (item, value) {
      if (value == item.costOfPurchase) {
        if (isNaN(item.costOfPurchase) || !item.costOfPurchase || item.costOfPurchase == 0) {
          item.costOfPurchase = 0;
        } else {
          item.costOfPurchase = parseFloat(item.costOfPurchase).toFixed(2);
        }
      }

      if (value == item.POAmountMRP) {
        if (isNaN(item.POAmountMRP) || !item.POAmountMRP || item.POAmountMRP == 0) {
          item.POAmountMRP = 0;
        } else {
          item.POAmountMRP = parseFloat(item.POAmountMRP).toFixed(2);
        }
      }

      if (value == item.taxPercent) {
        if (isNaN(item.taxPercent) || !item.taxPercent || item.taxPercent == 0) {
          item.taxPercent = 0;
        } else {
          item.taxPercent = parseFloat(item.taxPercent).toFixed(2);
        }
      }

      if (value == item.discountPercent) {
        if (isNaN(item.discountPercent) || !item.discountPercent || item.discountPercent == 0) {
          item.discountPercent = 0;
        } else {
          item.discountPercent = parseFloat(item.discountPercent).toFixed(2);
        }
      }
    }

    var PopulateAssetCategoryDropdown = function () {
      var purchaseOrder = self.model;

      var URI = CONSTANTS.GLOBAL.ASSET_CATEGORY_API + 1;

      purchaseOrder.assetCategory = purchaseOrder.assetCategories[0];
      purchaseOrder.productCategory = purchaseOrder.productCategories[0];

      purchaseOrder.assetCategories.splice(1);
      purchaseOrder.productCategories.splice(1);

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          purchaseOrder.assetCategories = purchaseOrder.assetCategories.concat(response.data);
          $rootScope.stopLoader();
        },
        function (err) {
          $rootScope.stopLoader();
        });
    }

    var PopulateProductCategory = function (assetCategory) {
      var purchaseOrder = self.model;

      var URI = CONSTANTS.GLOBAL.PRODUCT_CATEGORY_API + self.model.assetCategory.id;

      purchaseOrder.productCategory = purchaseOrder.productCategories[0];
      purchaseOrder.productCategories.splice(1);

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          purchaseOrder.productCategories = purchaseOrder.productCategories.concat(response.data);
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
        growl.error('Select Items to Add');
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
            self.model.otherChargesDropdownList = response.data.otherChargesList;
            response.data.itemDetailsList.forEach(function (value, key) {
              value['poQuantity'] = 0;
              value['prQuantity'] = '-';
              value['stageQuantityList'] = [{ stagedDate: moment(new Date()).format('YYYY-MM-DD'), quantity: parseInt(0) }];

              value['otherChargesTotal'] = 0;
              value['selectedOtherCharges'] = [{ otherChargesId: self.model.otherChargesDropdownList[0].id, amount: 0 }];
              value.discountPercent = 0;
              var isFound = self.model.itemDetails.find(function (x) {
                return x.itemId == value.itemId;
              });
              if (!isFound) {
                self.model.itemDetails.push(value);
                selectDefaultUOM(value);
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

    var selectDefaultUOM = function (item) {
      item.selectedUom = item.uom[0];
      item.bonusSelectedUom = item.uom[0];
      item.bonusUom = [
        {
          "uomUnitId": {
            "uomUnit": "BOXES",
            "uomUnitId": 1
          },
          "conversion": 10,
          "ipUom": false,
          "opUom": false,
          "storeUom": false,
          "uomTypeId": 1
        },
        {
          "uomUnitId": {
            "uomUnit": "Strip",
            "uomUnitId": 34
          },
          "conversion": 10,
          "ipUom": false,
          "opUom": true,
          "storeUom": true,
          "uomTypeId": 2
        },
        {
          "uomUnitId": {
            "uomUnit": "TABLET",
            "uomUnitId": 6
          },
          "conversion": 1,
          "ipUom": true,
          "opUom": false,
          "storeUom": false,
          "uomTypeId": 3
        }
      ];
      item.poUom = [
        {
          "uomUnitId": {
            "uomUnit": "BOXES",
            "uomUnitId": 1
          },
          "conversion": 10,
          "ipUom": false,
          "opUom": false,
          "storeUom": false,
          "uomTypeId": 1
        },
        {
          "uomUnitId": {
            "uomUnit": "Strip",
            "uomUnitId": 34
          },
          "conversion": 10,
          "ipUom": false,
          "opUom": true,
          "storeUom": true,
          "uomTypeId": 2
        },
        {
          "uomUnitId": {
            "uomUnit": "TABLET",
            "uomUnitId": 6
          },
          "conversion": 1,
          "ipUom": true,
          "opUom": false,
          "storeUom": false,
          "uomTypeId": 3
        }
      ];
    }

    var bonusUomInfoOnClick = function (batchItem, index) {
      self.model.bonusSelectedUOMList = [];
      self.model.bonusSelectedUOMUnit = [];
      self.model.bonusSelectedItemIndex = index;
      if (batchItem)
        batchItem.uom.forEach(function (item, index) {
          var data = { 'uomUnit': item.uomUnit, 'uomUnitId': item.uomUnitId };
          self.model.bonusSelectedUOMUnit.push(data);

          var uomUnitId = null, conversion = 0;
          if (!batchItem.bonusUom || batchItem.bonusUom.length == 0) {
            uomUnitId = data;
            conversion = item.conversion;
          }
          else {
            uomUnitId = batchItem.bonusUom[index].uomUnitId;
            conversion = batchItem.bonusUom[index].conversion;
          }

          self.model.bonusSelectedUOMList.push({
            uomType: item.uomType,
            uomUnitId: uomUnitId,
            conversion: conversion,
            ipUom: item.ipUom,
            opUom: item.opUom,
            storeUom: item.storeUom,
            uomTypeId: item.uomTypeId
          });
        });
    }

    var bonusUomModalOnClick = function () {
      self.model.itemDetails[self.model.bonusSelectedItemIndex].bonusUom = [];
      var modal_list = self.model.bonusSelectedUOMList;

      if (ValidConversionFactor(self.model.bonusSelectedUOMList)) {
        if (modal_list) {
          modal_list.forEach(function (modal, index) {
            self.model.itemDetails[self.model.bonusSelectedItemIndex].bonusUom.push({
              uomUnitId: modal.uomUnitId,
              conversion: parseInt(modal.conversion),
              ipUom: modal.ipUom,
              opUom: modal.opUom,
              storeUom: modal.storeUom,
              uomTypeId: modal.uomTypeId
            });
          });
          self.model.ClosePopup('Close', 'bouns_selectUOM_modal');
        }
      }
    }

    var ValidConversionFactor = function(data){
      var isValid = true;
      data.find(function(obj){
        if (!parseInt(obj.conversion)) {
          growl.error('Conversion factor should valid');
          isValid = false;
        }
      });

      return isValid;
    }

    var uomInfoOnClick = function (batchItem, index) {
      self.model.selectedUOMList = [];
      self.model.selectedUOMUnit = [];
      self.model.selectedItemIndex = index;
      if (batchItem)
        batchItem.uom.forEach(function (item, index) {
          var data = { 'uomUnit': item.uomUnit, 'uomUnitId': item.uomUnitId };
          self.model.selectedUOMUnit.push(data);

          var uomUnitId = null, conversion = 0;
          if (!batchItem.poUom || batchItem.poUom.length == 0) {
            uomUnitId = data;
            conversion = item.conversion;
          }
          else {
            uomUnitId = batchItem.poUom[index].uomUnitId;
            conversion = batchItem.poUom[index].conversion;
          }

          self.model.selectedUOMList.push({
            uomType: item.uomType,
            uomUnitId: uomUnitId,
            conversion: conversion,
            ipUom: item.ipUom,
            opUom: item.opUom,
            storeUom: item.storeUom,
            uomTypeId: item.uomTypeId
          });
        });
    }

    var uomModalOnClick = function () {
      self.model.itemDetails[self.model.selectedItemIndex].poUom = [];
      var modal_list = self.model.selectedUOMList;

      if (ValidConversionFactor(self.model.selectedUOMList)) {
        if (modal_list) {
          modal_list.forEach(function (modal, index) {
            self.model.itemDetails[self.model.selectedItemIndex].poUom.push({
              uomUnitId: modal.uomUnitId,
              conversion: parseInt(modal.conversion),
              ipUom: modal.ipUom,
              opUom: modal.opUom,
              storeUom: modal.storeUom,
              uomTypeId: modal.uomTypeId
            });
          });
          self.model.ClosePopup('Close', 'selectUOM_modal');
        }
      }
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
                itemDetail.prNumbersList.push(prObj);
                itemDetail.prIds.push(prId);
              } else {
                //DO nothing, Item is already preset in saved table
              }
            } else {
              // prIds list not present
              itemDetail.prNumbersList = [prObj]
              itemDetail.prIds = [prId];
            }

          } else if (newItemsHash.hasOwnProperty(itemId)) {
            // item found in new item hash
            var savedItem = newItemsHash[itemId];
            savedItem.prNumbersList.push(prObj);
            savedItem.prIds.push(prId);
          } else {
            // item not found in item hash
            var clonedItem = JSON.parse(JSON.stringify(item));
            delete clonedItem.id;
            delete clonedItem.prQuantity;
            clonedItem.prNumbersList = [prObj]
            clonedItem.prIds = [prId];
            newItemsHash[itemId] = clonedItem;
          }

        });
      });

      for (var itemId in newItemsHash) {
        newItemsHash[itemId]['poQuantity'] = 0;
        newItemsHash[itemId]['stageQuantityList'] = [{ stagedDate: moment(new Date()).format('YYYY-MM-DD'), quantity: parseInt(0) }];
        newItemsHash[itemId]['otherChargesTotal'] = 0;
        newItemsHash[itemId]['selectedOtherCharges'] = [{
          otherChargesId: self.model.otherChargesDropdownList[0].id,
          amount: 0
        }];
        newItemsHash[itemId]['prList'] = [];
        newItemsHash[itemId].discountPercent = 0;
        self.model.itemDetails.push(newItemsHash[itemId]);
        
        selectDefaultUOM(newItemsHash[itemId]);
      }

      console.log(self.model.itemDetails, "self.model.itemDetails");
    }

    var getSelectedIds = function () {
      var selecteItemIds = self.model.indentItemDetails.map(function (item) {
        return item.itemId;
      });
      return _.uniq(selecteItemIds);
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
        growl.error('Select Items to Add');
        return false;
      } else {
        var URI = CONSTANTS.GLOBAL.AGAINST_PR_DETAILS;

        SelectedItems();
        var selectedIds = getSelectedIds();

        var data = self.model.prDetails;

        $rootScope.startLoader();
        GenericService.serviceAction("POST", URI, data).then(
          function (response) {
            var responseData = response.data;
            var purchaseRequestListResponse = responseData.purchaseRequestList;
            self.model.otherChargesDropdownList = responseData.otherChargesList;
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

    var SavePurchaseOrder = function () {
      if (ValidateItems()) {
        if (!self.model.poId) {
          CreatePurchaseOrder();
        } else {
          UpdatePurchaseOrder();
        }
      }
    }

    var CreatePurchaseOrder = function () {
      var purchaseOrder = self.model;

      var URI = CONSTANTS.PROCUREMENT.QUOTATION.PURCHSE_ORDER.SAVE_API;

      var data = {
        "storeId": purchaseOrder.store.id,
        "vendorId": purchaseOrder.vendorId,
        "assetTypeId": purchaseOrder.poType.id,
        "poTypeId": purchaseOrder.PRType,
        "totalAmount": purchaseOrder.totalAmount,
        "discountTypeId": !!purchaseOrder.discountType ? purchaseOrder.discountType : 1,
        "discount": !!purchaseOrder.finalDiscount ? purchaseOrder.finalDiscount : 0,
        "totalDiscount": purchaseOrder.totalDiscount,
        "totalTaxAmount": purchaseOrder.totalTax,
        "totalOtherAmount": purchaseOrder.totalOtherCharges,
        "netAmount": purchaseOrder.totalNetAmount,
        "roundOffAmount": purchaseOrder.roundOffAmount,
        "isConsignment": purchaseOrder.isConsignment,
        "remark": purchaseOrder.purchaseOrderRemark,
        "paymentModeId": purchaseOrder.paymentMode.id,
        "statusId": setStatus('New'),
        "approvalStatusId": setStatus('New'),
        "items": []
      };

      angular.forEach(purchaseOrder.itemDetails, function (item, key) {
        var bonusUom = [];
        angular.forEach(item.bonusUom, function (bonus) {
          bonusUom.push({
            "conversion": bonus.conversion,
            "id": bonus.id,
            "ipUom": bonus.ipUom,
            "opUom": bonus.opUom,
            "storeUom": bonus.storeUom,
            "uomTypeId": bonus.uomTypeId,
            "uomUnitId": bonus.uomUnitId.uomUnitId
          });
        });

        var poUom = [];
        angular.forEach(item.poUom, function (puom) {
          poUom.push({
            "conversion": puom.conversion,
            "id": puom.id,
            "ipUom": puom.ipUom,
            "opUom": puom.opUom,
            "storeUom": puom.storeUom,
            "uomTypeId": puom.uomTypeId,
            "uomUnitId": puom.uomUnitId.uomUnitId
          });
        });

        var poItem = {
          "itemId": item.itemId,
          "poQuantity": item.poQuantity,
          "poUomTypeId": item.selectedUom.uomTypeId,
          "poUomUnitId": item.selectedUom.uomUnitId,
          "bonusQuantity": item.bonusQuantity,
          "bonusUomUnitId": item.bonusSelectedUom.uomUnitId,
          "bonusUomTypeId": item.bonusSelectedUom.uomTypeId,
          "manufactureId": item.manufacturerId,
          "cop": item.costOfPurchase,
          "mrp": item.POAmountMRP,
          "discount": item.discountPercent,
          "taxId": item.taxId,
          "taxPercentage": item.taxPercent,
          "taxAmount": item.taxAmount,
          "totalAmount": item.poAmount,
          "totalDiscount": item.discountAmount,
          "totalTaxAmount": item.taxAmount,
          "totalOtherAmount": item.otherChargesTotal,
          "netAmount": item.netAmount,
          "statusId": setStatus('New'),
          "specification": item.specification,
          "remark": item.remark,
          "poUom": poUom,
          "bonusUom": bonusUom,
          "otherCharges": item.selectedOtherCharges,
          "purchaseOrderStaged": item.stageQuantityList,
          "prId": item.prIds
        }

        data.items.push(poItem);
      });

      $rootScope.startLoader();
      GenericService.serviceAction("POST", URI, data).then(
        function (response) {
          $rootScope.stopLoader();
          NavigateToPO();
          growl.success(response.data.message);
        },
        function (err) {
          $rootScope.stopLoader();
          growl.error(err.data.message);
        });
    }

    var UpdatePurchaseOrder = function () {
      var URI = CONSTANTS.PROCUREMENT.QUOTATION.PURCHSE_ORDER.SAVE_API + self.model.poId;

      var data = {
        "totalAmount": self.model.totalAmount,
        "discountTypeId": parseInt(self.model.discountType),
        "discount": parseInt(self.model.finalDiscount),
        "totalDiscount": self.model.totalDiscount,
        "totalTaxAmount": self.model.totalTax,
        "totalOtherAmount": self.model.otherChargesTotal,
        "netAmount": self.model.totalNetAmount,
        "roundOffAmount": self.model.roundOffAmount,
        "isConsignment": self.model.isConsignment,
        "remark": self.model.purchaseOrderRemark,
        "poTypeId": self.model.poType.id,
        "paymentModeId": self.model.paymentMode.id,
        "items": []
      };
      angular.forEach(self.model.itemDetails, function (item, key) {

        var stagedQtyList = [];
        angular.forEach(item.stageQuantityList, function (staged) {
          stagedQtyList.push({
            "id": staged.id,
            "quantity": staged.quantity,
            "stagedDate": staged.stagedDate
          });
        });

        var otherCharges = [];
        angular.forEach(item.selectedOtherCharges, function (othercharge) {
          otherCharges.push({
            "id": othercharge.id,
            "otherChargesId": othercharge.otherChargesId,
            "amount": othercharge.amount
          });
        });

        var bonusUom = [];
        angular.forEach(item.bonusUom, function (bonus) {
          bonusUom.push({
            "conversion": bonus.conversion,
            "id": bonus.id,
            "ipUom": bonus.ipUom,
            "opUom": bonus.opUom,
            "storeUom": bonus.storeUom,
            "uomTypeId": bonus.uomTypeId,
            "uomUnitId": bonus.uomUnitId.uomUnitId
          });
        });

        var poUom = [];
        angular.forEach(item.poUom, function (uom) {
          poUom.push({
            "conversion": uom.conversion,
            "id": uom.id,
            "ipUom": uom.ipUom,
            "opUom": uom.opUom,
            "storeUom": uom.storeUom,
            "uomTypeId": uom.uomTypeId,
            "uomUnitId": uom.uomUnitId.uomUnitId
          });
        });

        var indentItem = {
          "id": item.id,
          "itemId": item.itemId,
          "poQuantity": parseInt(item.poQuantity),
          "poUomTypeId": item.selectedUom.uomTypeId,
          "poUomUnitId": item.selectedUom.uomUnitId,
          "bonusQuantity": parseInt(item.bonusQuantity),
          "bonusUomTypeId": item.bonusSelectedUom.uomTypeId,
          "bonusUomUnitId": item.bonusSelectedUom.uomUnitId,
          // "manufactureId": item.manufacturerId,
          "cop": item.costOfPurchase,
          "mrp": item.POAmountMRP,
          "discount": item.discountPercent,
          "taxId": item.taxId,
          "taxPercentage": item.taxPercent,
          "taxAmount": item.taxAmount,
          "totalAmount": item.poAmount,
          "totalDiscount": item.discountAmount,
          "totalTaxAmount": item.taxAmount,
          "totalOtherAmount": item.otherChargesTotal,
          "netAmount": item.netAmount,
          "poUom": poUom,
          "bonusUom": bonusUom,
          "otherCharges": otherCharges,
          "purchaseOrderStaged": item.stageQuantityList,
          "prId": item.prIds
        }

        data.items.push(indentItem);
      });

      $rootScope.startLoader();
      GenericService.serviceAction("PATCH", URI, data).then(
        function (response) {
          $rootScope.stopLoader();
          NavigateToPO();
          growl.success(response.data.message);
        },
        function (err) {
          $rootScope.stopLoader();
          growl.error(err.data.message);
        });

    }

    var updateDiscountForEachItems = function (percentageDiscount, items) {
      var itemDiscount = (percentageDiscount / self.model.totalAmount) * 100;

      angular.forEach(items, function (item, key) {
        item.discountPercent = itemDiscount;
        item.discountPercent = parseFloat(item.discountPercent).toFixed(2);
      });
    }

    var ApplyDiscount = function (items) {
      if (!self.model.discountType) {
        growl.error('Select Discount Type');
        return false;
      } else {
        var item = items.item;
        var discountInputValue = parseFloat(self.model.finalDiscount);

        if (!isNaN(discountInputValue)) {
          if (self.model.discountType == '2') {
            var discountInValue = discountInputValue;
            if (discountInValue < self.model.totalAmount) {
              updateDiscountForEachItems(discountInValue, items);
            } else {
              growl.error('Enter discount amount less than Total Amount');
            }
          } else if (self.model.discountType == '1') {
            var discountLimit = 50;
            var discountInPercentage = discountInputValue;
            if (discountInPercentage <= discountLimit) {
              var discountedAmount = (self.model.totalAmount * discountInPercentage) / 100;
              updateDiscountForEachItems(discountedAmount, items);
            } else {
              growl.error('Enter discount amount less than ' + discountLimit + '%');
            }

          }
          // self.model.finalDiscount = "";
          // self.model.discountType = null;
        }
      }
    }

    var NavigateToPO = function () {
      $state.go('POCreation');
    }

    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("addItemPO", {
      url: "/addItemPO",
      templateUrl: 'views/procurement/quotation/add-item-PO-creation.html',
      controller: "addNewPO.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("addNewPO.Controller", addNewPOController);
})();