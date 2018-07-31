(function () {
  "use strict";

  function addNewGRNController($scope, $rootScope, $http, $filter, $state, GenericService, commonDetailService, CurrencyService, growl, StatusService, CONSTANTS) {
    var self = this;

    //Default Constructor
    function initializeController() {
      $rootScope.loginpage = true;

      self.model = {
        fromDate: new Date(),
        toDate: new Date(),
        invoiceDate: new Date(),
        inwordDate: new Date(),
        dcDate: new Date(),
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
        invoiceDateOptions: {
          formatYear: 'yyyy',
          showWeeks: false
        },
        inwordDateOptions: {
          formatYear: 'yyyy',
          showWeeks: false
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
        poTypes: [{
          "type": "Select Type"
        }],
        SelectedItemsIds: [],
        itemDetails: [],
        intendItemIds: [],
        indentItemDetails: [],
        bonusReceivedQuantity: [],
        costOfPurchase: [],
        mrp: [],
        grnReceivedQuantity: [],
        batchNumber: [],
        expiryDate: [],
        taxPercent: [],
        isUOMValid: [],
        selectedUOMList: [],
        selectedUOMUnit: [],
        bonusSelectedUOMList: [],
        bonusSelectedUOMUnit: [],
        stageQuantityList: [],
        stagedQuantity: [],
        selectedItemIndex: 0,
        bonusSelectedItemIndex: 0,
        selectedIndex: null,
        selectedOtherChargeIndex: null,
        otherChargesDropdownList: [],
        otherChargesList: [],
        prDetails: [],
        isConsignment: false,
        dateOpen: [],
        dateOptions: {
          formatYear: 'yyyy',
          showWeeks: false
        },
        searchGRN: {
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
      self.model.OpenInvoiceDate = OpenInvoiceDate;
      self.model.OpenInwordDate = OpenInwordDate;
      self.model.OpenDCDate = OpenDCDate;
      self.model.OpenExpiryDate = OpenExpiryDate;
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
      self.model.SaveGRN = SaveGRN;
      self.model.NavigateToGRN = NavigateToGRN;
      self.model.ChangeRequestType = ChangeRequestType;
      self.model.GetApprovedPOList = GetApprovedPOList;
      self.model.Expand = Expand;
      self.model.SelectAllIndentItems = SelectAllIndentItems;
      self.model.SelectIndentItem = SelectIndentItem;
      self.model.AddApprovedPOItems = AddApprovedPOItems;
      self.model.SearchVendorNames = SearchVendorNames;
      self.model.SearchVendorForGRN = SearchVendorForGRN;
      self.model.AddVendorName = AddVendorName;
      self.model.AddVendor = AddVendor;
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
      self.model.FillStagedQuantity = FillStagedQuantity;
      self.model.SetDecimal = SetDecimal;
      self.model.fillPOModalList = fillPOModalList;
      self.model.SaveStagedQuantity = SaveStagedQuantity;

      PopulateDropdown();
    }

    var fillPOModalList = function (list) {
      self.model.poNumbersList = list;
    }

    var FillStagedQuantity = function (item, list) {
      self.model.stagedQtyListIndex = self.model.itemDetails.indexOf(item);
      self.model.poStagedList = list;
    }

    var SaveStagedQuantity = function () {
      var total = 0;
      var itemIndex = self.model.itemDetails[self.model.stagedQtyListIndex];
      itemIndex['poReceivedQuantity'] = [];
      angular.forEach(self.model.poStagedList, function (value) {
        total = total + parseInt(value.receivedQuantity);
        itemIndex['poReceivedQuantity'].push({
          "poStagedId": value.id, "receivedQuantity": value.receivedQuantity
        });
      });

      itemIndex.grnReceivedQuantity = total;
      self.model.ClosePopup('close', 'AddQtyModal_ETOitemConfig_one');
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
      otherCharge: self.model.otherChargesDropdownList[0]
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
      var qudate = moment(date).format('YYYY-MM-DD')
      self.model.stageQuantityList.push({ stagedDate: qudate, quantity: parseInt(0) });
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
        self.model.grnReceivedQuantity[key] = false;
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

    var OpenInvoiceDate = function () {
      self.model.invoiceDateOpened = true;
    }

    var OpenInwordDate = function () {
      self.model.inwordDateOpened = true;
    }

    var OpenDCDate = function () {
      self.model.dcDateOpened = true;
    }

    var OpenExpiryDate = function (idx) {
      self.model.dateOpen[idx] = true;
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
        self.model.store = self.model.stores[0];
        self.model.poType = self.model.poTypes[0];
        
        var URI = CONSTANTS.PROCUREMENT.QUOTATION.GRN.DROPDOWN_API;

        GenericService.serviceAction("GET", URI, {}).then(
          function (response) {
            self.model.stores = self.model.stores.concat(response.data.store);
            self.model.poTypes = self.model.poTypes.concat(response.data.assetType);

            var grnId = commonDetailService.getDataId();
            $rootScope.stopLoader();
            if (!!grnId) {
              commonDetailService.setDataId(null);
              GetGRN(grnId);
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

    var ChangeRequestType = function (requestType) {
      if (ValidateDetails()) {
        if (requestType == 4) {
          self.model.action = 'Open';
          var popup = angular.element('#approvedPO');
          popup.modal('show');
          self.model.vendorNameForPO = undefined;
          self.model.searchPO = undefined;
          self.model.poItemList = [];
          self.model.fromDate = new Date();
          self.model.toDate = new Date();
        } else if (requestType == 1) {
          self.model.action = 'Open';
          var popup = angular.element('#addNewItemWasteStore');
          popup.modal('show');
          PopulateAssetCategoryDropdown();
          self.model.searchGRN.itemCode = undefined;
          self.model.searchGRN.itemName = undefined;
          self.model.items = [];
        }
      }
    }

    var GetGRN = function (grnId) {
      var URI = CONSTANTS.PROCUREMENT.QUOTATION.GRN.GET_GRN_API + grnId;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          angular.forEach(response.data.items, function (item, key) {
            item.expiryDate = new Date(item.expiryDate);
          });

          self.model.otherChargesDropdownList = response.data.otherChargesList;
          self.model.grnId = response.data.id;
          self.model.store = self.model.stores.find(function (store) {
            return response.data.storeId == store.id;
          });

          self.model.poType = self.model.poTypes.find(function (potype) {
            return response.data.assetTypeId == potype.id;
          });

          self.model.requestType = response.data.grnTypeId;
          self.model.dcDate = new Date(response.data.dcDate)
          self.model.dcNumber = response.data.dcNumber;
          self.model.finalDiscount = response.data.discount;
          self.model.invoiceDate = new Date(response.data.invoiceDate);
          self.model.invoiceNumber = response.data.invoiceNumber;
          self.model.inwordDate = new Date(response.data.inwardDate);
          self.model.inwordNumber = response.data.inwardNumber;
          self.model.isConsignment = response.data.isConsignment;
          self.model.totalNetAmount = response.data.netAmount;
          self.model.totalAmount = response.data.totalAmount;
          self.model.totalDiscount = response.data.totalDiscount;
          self.model.totalOtherCharges = response.data.totalOtherAmount;
          self.model.totalTax = response.data.totalTaxAmount;
          self.model.vendorName = response.data.vendorName;
          self.model.GRNRemark = response.data.remark;
          self.model.discountType = response.data.discountTypeId;
          self.model.roundOffAmount = response.data.roundOff;
          self.model.vendorId = response.data.vendorId;
          self.model.itemDetails = [];

          angular.forEach(response.data.items, function (item) {
            var grnUom = [];
            angular.forEach(item.grnUom, function (uom) {
              grnUom.push({
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

            var getStagedData = [];
            angular.forEach(item.grnStagedQuantity, function(stagedQty){
              getStagedData.push({
                id: stagedQty.id,
                stagedDate: stagedQty.poStagedDate,
                stagedQuantity: stagedQty.stagedQuantity,
                receivedQuantity: stagedQty.receivedQuantity,
                pendingQuantity: stagedQty.pendingQuantity
              });
            });

            var poList = [];
            angular.forEach(item.poList, function(po){
              poList.push({
                poNo: po.poNo,
                poQuantity: po.approvedQuantity
              });
            });

            var poItem = {
              "id": item.id,
              "itemId": item.itemId,
              "itemCode": item.itemCode,
              "itemName": item.itemName,
              "poQuantity": item.grnApprovedQuantity,
              "poBonusQuantity": item.bonusApprovedQuantity,
              "grnReceivedQuantity": item.grnReceivedQuantity,
              "bonusReceivedQuantity": item.bonusReceivedQuantity,
              "costOfPurchase": item.cop,
              "mrp": item.mrp,
              "discountPercent": item.discount,
              "taxName": item.taxCode,
              "taxPercent": item.taxPercentage,
              "poAmount": item.totalAmount,
              "taxAmount": item.taxAmount,
              "manufacturerId": item.manufacturerId,
              "netAmount": item.netAmount,
              "discountAmount": item.totalDiscount,
              "otherChargesTotal": item.totalOtherAmount,
              "specification": item.specification,
              "itemRemark": item.remark,
              "uom": item.uom,
              "selectedUom": item.uom.find(function (uom) {
                return item.grnUomTypeId == uom.uomTypeId;
              }),
              "bonusSelectedUom": item.uom.find(function (uom) {
                return item.bonusUomTypeId == uom.uomTypeId;
              }),
              "stageQuantityList": item.purchaseOrderStagedDto,
              "selectedOtherCharges": item.otherCharges,
              "poUom": grnUom,
              "bonusUom": bonusUom,
              "batchNumber": item.batchNo,
              "expiryDate": item.expiryDate,
              "manufacturerName": item.manufacturerName,
              "itemSpecification": item.grnSpecification,
              "poStagedList": getStagedData,
              "poNumbersList": poList
            }
            self.model.itemDetails.push(poItem);
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
        self.model.isValidStore = true;
        isValid = false;
      }

      if (!self.model.poType.id) {
        self.model.isValidPOType = true;
        isValid = false;
      }

      if (!self.model.requestType) {
        self.model.isRequestTypeValid = true;
        isValid = false;
      }

      if (!self.model.vendorId || !self.model.vendorName) {
        self.model.isVendorValid = true;
        isValid = false;
      }

      if (!self.model.store.id || !self.model.poType || !self.model.requestType) {
        growl.error('Select Required Fields');
        isValid = false;
      }

      return isValid;
    }

    var ValidateItems = function () {
      var isValidItem = true;
      if (self.model.itemDetails.length <= 0) {
        growl.error('Add Items to Save GRN');
        isValidItem = false;
      } else {
        angular.forEach(self.model.itemDetails, function (item, index) {
          if (!item.bonusReceivedQuantity || item.bonusReceivedQuantity <= 0) {
            self.model.bonusReceivedQuantity[index] = true;
            isValidItem = false;
          }

          if (!item.costOfPurchase || item.costOfPurchase <= 0) {
            self.model.costOfPurchase[index] = true;
            isValidItem = false;
          }

          if (!item.expiryDate || item.expiryDate == '') {
            self.model.expiryDate[index] = true;
            isValidItem = false;
          }

          if (!item.mrp || item.mrp <= 0) {
            self.model.mrp[index] = true;
            isValidItem = false;
          }

          if (!item.grnReceivedQuantity || item.grnReceivedQuantity <= 0) {
            self.model.grnReceivedQuantity[index] = true;
            isValidItem = false;
          }

          if (!item.taxPercent) {
            self.model.taxPercent[index] = true;
            isValidItem = false;
          }

          if (!item.batchNumber) {
            self.model.batchNumber[index] = true;
            isValidItem = false;
          }

          if (!item.expiryDate) {
            self.model.expiryDate[index] = true;
            isValidItem = false;
          }

          if (item.discountPercent >= 50) {
            self.model.discountPercent[index] = true;
            growl.error('Disount Should be less than 50%');
            isValidItem = false;
          }
        });

        if (!self.model.vendorId || !self.model.vendorName) {
          self.model.isVendorValid = true;
          isValidItem = false;
        }

        if (!self.model.invoiceDate) {
          self.model.isInvoiceDateValid = true;
          isValidItem = false; 
        }

        if (!self.model.invoiceNumber) {
          self.model.isInvoiceNumberValid = true;
          isValidItem = false; 
        }

        if (!self.model.vendorId || !self.model.vendorName || !self.model.invoiceDate || !self.model.invoiceNumber) {
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

      if (value == item.mrp) {
        if (isNaN(item.mrp) || !item.mrp || item.mrp == 0) {
          item.mrp = 0;
        } else {
          item.mrp = parseFloat(item.mrp).toFixed(2);
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
      var grn = self.model;

      var URI = CONSTANTS.GLOBAL.ASSET_CATEGORY_API + 1;

      grn.assetCategory = grn.assetCategories[0];
      grn.productCategory = grn.productCategories[0];

      grn.assetCategories.splice(1);
      grn.productCategories.splice(1);

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          grn.assetCategories = grn.assetCategories.concat(response.data);
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

    var ValidateDate = function () {
      var isValid = true;
      if (self.model.fromDate > self.model.toDate) {
        growl.error('From Date should lesser than To Date');
        isValid = false;
      }

      return isValid;
    }

    var GetApprovedPOList = function () {
      if (ValidateDate()) {
        var URI = CONSTANTS.GLOBAL.AGAINST_PO_SEARCH + self.model.store.id;
        if (!self.model.vendorNameForPO) {
          self.model.vendorIdForPO = undefined;
        }
        var data = {
          "fromDate": moment(self.model.fromDate).format('YYYY-MM-DD'),
          "toDate": moment(self.model.toDate).format('YYYY-MM-DD'),
          "vendorId": self.model.vendorIdForPO
          // "vendorName": self.model.vendorNameForPO,
          // "assetType": self.model.poType.id
        }

        GenericService.serviceAction("POST", URI, data).then(
          function (response) {
            self.model.poItemList = response.data;
            angular.forEach(self.model.poItemList, function (po) {
              po["isChecked"] = false;
              po.isSelectedIndent = false;
              angular.forEach(po.items, function (item) {
                item["isChecked"] = false;
              });
            });

            $rootScope.stopLoader();
          },
          function (err) {
            $rootScope.stopLoader();
            self.model.poItemList = [];
            growl.error(err.data.message);
          });
      }
    }

    function SearchVendorForGRN(search) {
      if (search.length < 3) return;

      if ((!self.model.grnPrevSearch || self.model.grnPrevSearch != search) && (search.length == 3)) {
        self.model.grnPrevSearch = search;
        var URI = CONSTANTS.GLOBAL.VENDOR_SEARCH_API + search;
        $rootScope.startLoader();
        return GenericService.serviceAction("GET", URI, {})
          .then(function (response) {
            if (!!response.data && response.data.length > 0) {
              self.model.grnVendorNames = response.data;
              $rootScope.stopLoader();
              return $filter('filter')(self.model.grnVendorNames, {
                $: search
              });
            } else {
              $rootScope.stopLoader();
              return vendorNotFound(search);
            }
          }, function (err) {
            $rootScope.stopLoader();
            return vendorNotFound(search);
          });
      } else {
        if (!!self.model.grnVendorNames && self.model.grnVendorNames.length > 0 && self.model.grnVendorNames[0].itemFound != undefined && !self.model.grnVendorNames[0].itemFound) {
          return vendorNotFound(search);
        } else {
          return $filter('filter')(self.model.grnVendorNames, {
            $: search
          });
        }
      }
    }

    function vendorNotFound(search) {
      var item = {
        "itemFound": false,
        "detail": "Name Not found with name " + search,
        "search": search
      };
      self.model.grnVendorNames = [item];
      return self.model.grnVendorNames;
    }

    function AddVendor(vendor) {
      if (!vendor.id) {
        self.model.vendorNameForPO = vendor.search;
        self.model.vendorIdForPO = undefined;
      } else {
        self.model.vendorNameForPO = vendor.vendorName;
        self.model.vendorIdForPO = vendor.id;
      }
    }

    var Expand = function (item) {
      item.Expanded = !item.Expanded;
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

    var ValidConversionFactor = function(data){
      var isValid = true;
      data.find(function(x){
        if (!parseInt(x.conversion)) {
          growl.error('Conversion factor should valid');
          isValid = false;
        }
      });

      return isValid;
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
    var SelectAllIndentItems = function () {
      var selecteItem = self.model.isSelectedIndent;
      self.model.intendItemIds = [];
      angular.forEach(self.model.poItemList, function(po){
        if (po.id !== selecteItem.id) {
          po.items.forEach(function (x) {
            x.isSelectedItem = false;
            x.isChecked = false;
            po.isChecked = false;
          });
        } else {
          po.items.forEach(function (x) {
            self.model.indentItemDetails.push(x);
            x.isSelectedItem = true;
            x.isChecked = true;
            po.isChecked = true;
            self.model.intendItemIds.push(x.itemId);
          });
        }
      });
    }

    var SelectIndentItem = function (item, indent) {
      var itemIds = self.model.intendItemIds;
      if(self.model.isSelectedIndent != indent){
        angular.forEach(self.model.poItemList, function(po){
          if (po.id !== indent.id) {
            po.items.forEach(function (x) {
              x.isSelectedItem = false;
              x.isChecked = false;
              po.isChecked = false;
            });
          }
        });
      }
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
         self.model.isSelectedIndent = indent;
      } else {
         self.model.isSelectedIndent = null;
      }
    }

    var updatePOItemDetails = function (poResponseData) {
      var newItemsHash = {};
      var itemDetailsHash = {};
      self.model.itemDetails.forEach(function (itemDetail) {
        itemDetailsHash[itemDetail.itemId] = itemDetail;
      });

      poResponseData.forEach(function (poItem) {
        var poNo = poItem.poNo;
        self.model.poId = poItem.id;
        poItem.items.forEach(function (item) {
          var itemId = item.itemId;
          var poId = item.id;
          var poQuantity = Number(item.poQuantity);
          var bonusQuantity = Number(item.bonusQuantity);


          var poObj = { id: poId, poNo: poNo, poQuantity: poQuantity };
          var bonusQtyList = {bonusQuantity: bonusQuantity};

          if (itemDetailsHash.hasOwnProperty(itemId)) {
            // item found in already saved list
            var itemDetail = itemDetailsHash[itemId];
            if (itemDetail.poIds && itemDetail.poIds.length) {
              // poIds list is present
              if (itemDetail.poIds.indexOf(poId) == -1) {
                itemDetail.poNumbersList.push(poObj);
                itemDetail.bonusQuantityList.push(bonusQtyList);

                itemDetail.poIds.push(poId);
                itemDetail.poDetailId = poId;
                itemDetail['poDetailId'] = poId;
              } else {
                //DO nothing, Item is already preset in saved table
              }
            } else {
              // poIds list not present
              itemDetail.poNumbersList = [poObj]
              itemDetail.bonusQuantityList = [bonusQtyList]
              itemDetail.poIds = [poId];
              itemDetail.poDetailId = poId;
            }

          } else if (newItemsHash.hasOwnProperty(itemId)) {
            // item found in new item hash
            var savedItem = newItemsHash[itemId];
            savedItem.poNumbersList.push(poObj);
            savedItem.bonusQuantityList.push(bonusQtyList);
            savedItem.poIds.push(poId);
            savedItem.poDetailId = poId;
          } else {
            // item not found in item hash
            var clonedItem = JSON.parse(JSON.stringify(item));
            delete clonedItem.id;
            delete clonedItem.poQuantity;
            delete clonedItem.bonusQuantity;
            clonedItem.poNumbersList = [poObj]
            clonedItem.bonusQuantityList = [bonusQtyList]
            clonedItem.poIds = [poId];
            clonedItem.poDetailId = poId;
            newItemsHash[itemId] = clonedItem;
          }
        });
      });

      for (var itemId in newItemsHash) {
        newItemsHash[itemId]['poQuantity'] = 0;
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
      var po = self.model.isSelectedIndent;
      if (po) {
        self.model.poDetails = [];
        var poData = {
          "poId": po.id,
          "itemIds": []
        }
        angular.forEach(po.items, function (item) {
          if (item.isChecked) {
            poData.itemIds.push(item.itemId);
          }
        });
        if (po.isChecked && poData.itemIds.length > 0) {
            self.model.poDetails.push(poData);
        } 
      } 
    }

    // Add Indent Items to List
    var AddApprovedPOItems = function () {
      if (self.model.intendItemIds.length == 0) {
        growl.error('Please Select Items to Add');
        return false;
      } else {
        var URI = CONSTANTS.GLOBAL.AGAINST_PO_DETAILS;

        SelectedItems();
        var selectedIds = getSelectedIds();

        var data = self.model.poDetails;

        $rootScope.startLoader();
        GenericService.serviceAction("POST", URI, data).then(
          function (response) {
            var responseData = response.data;
            var grnResponse = responseData.purchaseOrderList;
            self.model.otherChargesDropdownList = responseData.otherChargesList;
            updatePOItemDetails(grnResponse);

            self.model.indentItemDetails = [];
            self.model.intendItemIds = [];
            self.model.items = [];
            self.model.poItemList = [];

            var popup = angular.element('#approvedPO');
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

    var SaveGRN = function () {
      if (ValidateItems()) {
        if (!self.model.grnId) {
          CreateGRN();
        } else {
          UpdateGRN();
        }
      }
    }

    var CreateGRN = function () {
      var grn = self.model;

      var URI = CONSTANTS.PROCUREMENT.QUOTATION.GRN.SAVE_GRN_API;

      var data = {
        "assetTypeId": grn.poType.id,
        "grnTypeId": grn.requestType,
        "invoiceNumber": grn.invoiceNumber,
        "invoiceDate": moment(grn.invoiceDate).format('YYYY-MM-DD'),
        "inwardNumber": grn.inwordNumber,
        "inwardDate": moment(grn.inwordDate).format('YYYY-MM-DD'),
        "dcNumber": grn.dcNumber,
        "dcDate": moment(grn.dcDate).format('YYYY-MM-DD'),
        "vendorId": grn.vendorId,
        "totalAmount": grn.totalAmount,
        "discountTypeId": grn.discountType,
        "discount": grn.finalDiscount,
        "totalDiscount": grn.totalDiscount,
        "totalTaxAmount": grn.totalTax,
        "totalOtherAmount": grn.totalOtherCharges,
        "netAmount": grn.totalNetAmount,
        "roundOff": 0, //grn.roundOffAmount,
        "isConsignment": grn.isConsignment,
        "remark": grn.GRNRemark,
        "statusId": setStatus('New'),
        "approvalStatusId": setStatus('New'),
        "storeId": grn.store.id,
        "poId": grn.poId,
        "items": []
      };

      angular.forEach(grn.itemDetails, function (item, key) {
        var expDateMonth = item.expiryDate.getMonth();
        var expDateYear = item.expiryDate.getYear();
        var expDateDay = new Date(2018, expDateMonth + 1, 0).getDate();

        var bonusUom = [];
        angular.forEach(item.bonusUom, function (uom) {
          bonusUom.push({
            "conversion": uom.conversion,
            "id": uom.id,
            "ipUom": uom.ipUom,
            "opUom": uom.opUom,
            "storeUom": uom.storeUom,
            "uomTypeId": uom.uomTypeId,
            "uomUnitId": uom.uomUnitId.uomUnitId
          });
        });

        var grnUom = [];
        angular.forEach(item.poUom, function (uom) {
          grnUom.push({
            "conversion": uom.conversion,
            "id": uom.id,
            "ipUom": uom.ipUom,
            "opUom": uom.opUom,
            "storeUom": uom.storeUom,
            "uomTypeId": uom.uomTypeId,
            "uomUnitId": uom.uomUnitId.uomUnitId
          });
        });

        var GRNItem = {
          "itemId": item.itemId,
          "grnReceivedQuantity": item.grnReceivedQuantity,
          "bonusReceivedQuantity": item.bonusReceivedQuantity,
          "grnUomTypeId": item.selectedUom.uomTypeId,
          "grnUomUnitId": item.selectedUom.uomUnitId,
          "bonusUomUnitId": item.bonusSelectedUom.uomUnitId,
          "bonusUomTypeId": item.bonusSelectedUom.uomTypeId,
          "manufacturerId": item.manufacturerId,
          "grnUom": grnUom,
          "bonusUom": bonusUom,
          "cop": item.costOfPurchase,
          "mrp": item.mrp,
          "discount": item.discountPercent,
          "taxId": item.taxId,
          "taxPercentage": item.taxPercent,
          "taxAmount": item.taxAmount,
          "totalAmount": item.grnAmount,
          "totalDiscount": item.discountAmount,
          "totalOtherAmount": item.otherChargesTotal,
          "netAmount": item.netAmount,
          "statusId": setStatus('New'),
          "grnSpecification": item.itemSpecification,
          "remark": item.itemRemark,
          "otherCharges": item.selectedOtherCharges,
          "batchNo": item.batchNumber,
          "expiryDate": (2018 + "-" + expDateMonth + "-" + expDateDay),
          "grnStagedQuantity": item.poReceivedQuantity,
          "poDetailId": item.poDetailId
        }

        data.items.push(GRNItem);
      });

      $rootScope.startLoader();
      GenericService.serviceAction("POST", URI, data).then(
        function (response) {
          $rootScope.stopLoader();
          NavigateToGRN();
          growl.success(response.data.message);
        },
        function (err) {
          $rootScope.stopLoader();
          growl.error(err.data.message);
        });
    }

    var UpdateGRN = function () {
      var grn = self.model;
      var URI = CONSTANTS.PROCUREMENT.QUOTATION.GRN.SAVE_GRN_API + grn.grnId;

      var data = {
        "id": grn.grnId,
        "vendorId": grn.vendorId,
        "totalAmount": grn.totalAmount,
        "discountTypeId": parseInt(grn.discountType),
        "discount": parseInt(grn.finalDiscount),
        "totalDiscount": grn.totalDiscount,
        "totalTaxAmount": grn.totalTax,
        "totalOtherAmount": grn.otherChargesTotal,
        "netAmount": grn.totalNetAmount,
        "roundOff": grn.roundOffAmount,
        "isConsignment": grn.isConsignment,
        "remark": grn.GRNRemark,
        "items": []
      };
      angular.forEach(grn.itemDetails, function (item, key) {

        var expDateMonth = item.expiryDate.getMonth();
        var expDateYear = item.expiryDate.getYear();
        var expDateDay = new Date(2018, expDateMonth + 1, 0).getDate();

        // var stagedQtyList = [];
        // angular.forEach(item.stageQuantityList, function (staged) {
        //   stagedQtyList.push({
        //     "id": staged.id,
        //     "quantity": staged.quantity,
        //     "stagedDate": staged.stagedDate
        //   });
        // });

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

        var setStagedData = [];
        angular.forEach(item.poStagedList, function(stagedQty){
          setStagedData.push({
            id: stagedQty.id,
            receivedQuantity: stagedQty.receivedQuantity
          });
        });

        var GRNItem = {
          "id": item.id,
          "itemId": item.itemId,
          "bonusReceivedQuantity": item.bonusReceivedQuantity,
          "grnReceivedQuantity": item.grnReceivedQuantity,
          "grnUomTypeId": item.selectedUom.uomTypeId,
          "grnUomUnitId": item.selectedUom.uomUnitId,
          "bonusUomTypeId": item.bonusSelectedUom.uomTypeId,
          "bonusUomUnitId": item.bonusSelectedUom.uomUnitId,
          "manufacturerId": item.manufacturerId,
          "cop": item.costOfPurchase,
          "mrp": item.mrp,
          "discount": item.discountPercent,
          "taxId": item.taxId,
          "taxPercentage": item.taxPercent,
          "taxAmount": item.taxAmount,
          "totalAmount": item.poAmount,
          "totalDiscount": item.discountAmount,
          "totalOtherAmount": item.otherChargesTotal,
          "netAmount": item.netAmount,
          "grnUom": poUom,
          "bonusUom": bonusUom,
          "otherCharges": otherCharges,
          "batchNo": item.batchNumber,
          "expiryDate": (2018 + "-" + expDateMonth + "-" + expDateDay),
          "remark": item.itemRemark,
          "grnSpecification": item.itemSpecification,
          "statusId": setStatus('New'),
          "grnStagedQuantity": setStagedData,
          "poDetailId": item.poDetailId
        }

        data.items.push(GRNItem);
      });

      $rootScope.startLoader();
      GenericService.serviceAction("PATCH", URI, data).then(
        function (response) {
          $rootScope.stopLoader();
          NavigateToGRN();
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
              growl.error('Please enter discount amount less than Total Amount');
            }
          } else if (self.model.discountType == '1') {
            var discountLimit = 50;
            var discountInPercentage = discountInputValue;
            if (discountInPercentage <= discountLimit) {
              var discountedAmount = (self.model.totalAmount * discountInPercentage) / 100;
              updateDiscountForEachItems(discountedAmount, items);
            } else {
              growl.error('Please enter discount amount less than ' + discountLimit + '%');
            }
          }
        }
      }
    }

    var NavigateToGRN = function () {
      $state.go('GRN');
    }

    // todo: id or itemId
    var RemoveItem = function (item) {
      var newArray = [];

      self.model.itemDetails.map(function (obj) {
        if (obj.itemId !== item.itemId) {
          newArray.push(obj);
        }
      });
      
      self.model.itemDetails = newArray;
    }

    initializeController();
  }
 
  function config($stateProvider) {
    $stateProvider.state("addItemGRN", {
      url: "/addItemGRN",
      templateUrl: 'views/procurement/quotation/add-item-GRN.html',
      controller: "AddNewGRN.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("AddNewGRN.Controller", addNewGRNController);
})();