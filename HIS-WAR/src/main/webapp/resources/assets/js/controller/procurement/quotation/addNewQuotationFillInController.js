(function () {
  "use strict";

  function addNewQuotationFillInController($scope, $rootScope, $http, $filter, $state, GenericService, commonDetailService, CurrencyService, growl, StatusService, CONSTANTS) {
    var self = this;

    function initializeController() {
      $rootScope.loginpage = true;

      self.model = {
        fromDate: new Date(),
        toDate: new Date(),
        dueDate: new Date(),
        // refDate: "Select Reference Date",
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
        dueDateOptions: {
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
        assetTypes: [{
          "type": "Select Type"
        }],
        SelectedItemsIds: [],
        itemDetails: [],
        intendItemIds: [],
        indentItemDetails: [],
        dateOptions: {
          formatYear: 'yyyy',
          showWeeks: false
        },
        prDetailList: [],
        expectedAccessories: [], 
        accessoryItem: [],
        termsVisible: 1,
        termsList: [],
        vendordetailsValid: [],
        quantity: [],
        vendorCOP: [],
        qrSearch: undefined
      };

      StatusService.GetStatus().then(function (status) {
        self.model.statuses = status;
      });

      CurrencyService.GetCurrency().then(function(currency){
        self.model.Currency = currency;
      });

      self.model.OpenDueDate = OpenDueDate;
      self.model.OpenFromDate = OpenFromDate;
      self.model.OpenToDate = OpenToDate;
      self.model.OpenRefDate = OpenRefDate;
      self.model.PopulateAssetCategoryDropdown = PopulateAssetCategoryDropdown;
      self.model.PopulateProductCategory = PopulateProductCategory;
      self.model.GetItemList = GetItemList;
      self.model.SelectAllItems = SelectAllItems;
      self.model.SelectItem = SelectItem;
      self.model.AddItems = AddItems;
      self.model.RemoveItem = RemoveItem;
      self.model.SaveEnquiry = SaveEnquiry;
      self.model.NavigateToBack = NavigateToBack;
      self.model.GetQRPopup = GetQRPopup;
      self.model.GetQRList = GetQRList;
      self.model.Expand = Expand;
      self.model.SelectAllIndentItems = SelectAllIndentItems;
      self.model.SelectIndentItem = SelectIndentItem;
      self.model.AddApprovedPOItems = AddApprovedPOItems;
      self.model.SearchVendorNames = SearchVendorNames;
      self.model.AddVendorName = AddVendorName;
      self.model.OpenPopup = OpenPopup;
      self.model.ClosePopup = ClosePopup;
      self.model.ViewAccessories = ViewAccessories;
      self.model.FillAccessoryDetails = FillAccessoryDetails;
      self.model.AddAccessory = AddAccessory;
      self.model.LessAccessory = LessAccessory;
      self.model.SaveAccessoryDetails = SaveAccessoryDetails;
      self.model.ViewTerms = ViewTerms;
      self.model.SaveTerms = SaveTerms;

      PopulateDropdown();
    }

    var PopulateDropdown = function () {
      try {
        $rootScope.startLoader();
        self.model.store = self.model.stores[0];
        self.model.assetType = self.model.assetTypes[0];
        
        var URI = CONSTANTS.PROCUREMENT.QUOTATION.GRN.DROPDOWN_API;

        GenericService.serviceAction("GET", URI, {}).then(
          function (response) {
            self.model.stores = self.model.stores.concat(response.data.store);
            self.model.assetTypes = self.model.assetTypes.concat(response.data.assetType);

            var enquiryId = commonDetailService.getDataId();
            $rootScope.stopLoader();
            if (!!enquiryId) {
              commonDetailService.setDataId(null);
              GetEnquiry(enquiryId);
            }
          },
          function (err) {
            $rootScope.stopLoader();
          });
      } catch (error) {
        alert(error);
      }
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

    var GetQRPopup = function () {
      if (ValidateDetails()) {
        self.model.qrItemList = [];
        self.model.fromDate = new Date();
        self.model.toDate = new Date();
        self.model.vqrNumberSearch = undefined;
        self.model.qrSearch = undefined;
        self.model.action = 'Open';
        var popup = angular.element('#GetVQRList');
        popup.modal('show');
      }
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

    var GetQRList = function () {
      var URI = CONSTANTS.PROCUREMENT.QUOTATION.QUOTATION_FILL_IN.SEARCH_VQR_API;
      var data = {
        "fromDate": moment(self.model.fromDate).format('YYYY-MM-DD'),
        "toDate": moment(self.model.toDate).format('YYYY-MM-DD'),
        "assetTypeId": self.model.assetType.id,
        "vqrNumber": self.model.vqrNumberSearch,
        "storeId": self.model.store.id,
        "vendorId": self.model.vendorId
      }

      GenericService.serviceAction("POST", URI, data).then(
        function (response) {
          self.model.qrItemList = response.data;
          angular.forEach(self.model.qrItemList, function (qr) {
            qr["isChecked"] = false;
            qr.isSelectedIndent = false;
            angular.forEach(qr.items, function (item) {
              item["isChecked"] = false;
            });
          });

          $rootScope.stopLoader();
        },
        function (err) {
          $rootScope.stopLoader();
          self.model.qrItemList = [];
          growl.error(err.data.message);
        });
    }

    var AddApprovedPOItems = function () {
      if (self.model.intendItemIds.length == 0) {
        growl.error('Please Select Items to Add');
        return false;
      } else {
        var URI = CONSTANTS.PROCUREMENT.QUOTATION.QUOTATION_FILL_IN.DETAILS_VQR_ITEMS;

        SelectedQRItems();

        var data = self.model.qrDetails;

        $rootScope.startLoader();
        GenericService.serviceAction("POST", URI, data).then(
          function (response) {
            var qrResponse = response.data;
            updateItemDetails(qrResponse);
            self.model.qrNumber = response.data[0].qrNumber;
            self.model.qrDate = moment(response.data[0].qrDate).format('MM-DD-YYYY');
            self.model.vqrNumber = response.data[0].vqrNumber;
            self.model.vqrDate = moment(response.data[0].vqrDate).format('MM-DD-YYYY');
            self.model.qrId = response.data[0].qrId;
            self.model.vqrId = response.data[0].vqrId;

            self.model.indentItemDetails = [];
            self.model.intendItemIds = [];
            self.model.items = [];
            self.model.qrItemList = [];

            self.model.ClosePopup('close', 'GetVQRList');
            $rootScope.stopLoader();
          },
          function (err) {
            $rootScope.stopLoader();
            self.model.SelectedItemsIds = [];
            growl.error(err.data.message);
          });
      }
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
              var isFound = self.model.itemDetails.find(function (x) {
                return x.itemId == value.itemId;
              });

              if (!isFound) {
                self.model.itemDetails.push(value);
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

    var updateItemDetails = function (responseData) {
      var newItemsHash = {};
      var itemDetailsHash = {};
      self.model.itemDetails.forEach(function (itemDetail) {
        itemDetailsHash[itemDetail.itemId] = itemDetail;
      });

      responseData.forEach(function (poItem) {
        var poNo = poItem.poNo;
        self.model.poId = poItem.id;
        poItem.items.forEach(function (item) {
          var itemId = item.itemId;
          var poId = item.id;
          var poQuantity = Number(item.poQuantity);
          var bonusQuantity = Number(item.bonusQuantity);

          var poObj = { id: poId, poNo: poNo, poQuantity: poQuantity };

          if (itemDetailsHash.hasOwnProperty(itemId)) {
            // item found in already saved list
            var itemDetail = itemDetailsHash[itemId];
            if (itemDetail.poIds && itemDetail.poIds.length) {
              // poIds list is present
              if (itemDetail.poIds.indexOf(poId) == -1) {
                itemDetail.poNumbersList.push(poObj);

                itemDetail.poIds.push(poId);
                itemDetail.poDetailId = poId;
                itemDetail['poDetailId'] = poId;
              } else {
                //DO nothing, Item is already preset in saved table
              }
            } else {
              // poIds list not present
              itemDetail.poNumbersList = [poObj]
              itemDetail.poIds = [poId];
              itemDetail.poDetailId = poId;
            }

          } else if (newItemsHash.hasOwnProperty(itemId)) {
            // item found in new item hash
            var savedItem = newItemsHash[itemId];
            savedItem.poNumbersList.push(poObj);
            savedItem.poIds.push(poId);
            savedItem.poDetailId = poId;
          } else {
            // item not found in item hash
            var clonedItem = JSON.parse(JSON.stringify(item));
            delete clonedItem.id;
            delete clonedItem.poQuantity;
            delete clonedItem.bonusQuantity;
            clonedItem.poNumbersList = [poObj]
            clonedItem.poIds = [poId];
            clonedItem.poDetailId = poId;
            newItemsHash[itemId] = clonedItem;
          }
        });
      });

      for (var itemId in newItemsHash) {
        self.model.itemDetails.push(newItemsHash[itemId]);
      }

      console.log(self.model.itemDetails, "self.model.itemDetails");
    }

    var SelectedQRItems = function () {
      var qr = self.model.isSelectedIndent;
      if (qr) {
        self.model.qrDetails = [];
        var qrData = {
          "vqrId": qr.id,
          "itemIds": []
        }
        angular.forEach(qr.items, function (item) {
          if (item.isChecked) {
            qrData.itemIds.push(item.itemId);
          }
        });
        if (qr.isChecked && qrData.itemIds.length > 0) {
          self.model.qrDetails.push(qrData);
        } 
      } 
    }

    var GetEnquiry = function (enquiryId) {
      var URI = CONSTANTS.PROCUREMENT.QUOTATION.QUOTATION_FILL_IN.DETAILS_API + enquiryId;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          self.model.enquiryId = response.data.id;
          self.model.store = self.model.stores.find(function (store) {
            return response.data.storeId == store.id;
          });

          self.model.assetType = self.model.assetTypes.find(function (assetType) {
            return response.data.assetTypeId == assetType.id;
          });

          self.model.refDate = response.data.refDate;
          self.model.refNumber = response.data.refNumber;
          self.model.qrNumber = response.data.qrNumber;
          self.model.qrDate = moment(response.data.qrDate).format('DD-MM-YYYY');
          self.model.vqrNumber = response.data.vqrNumber;
          self.model.vqrDate = moment(response.data.vqrDate).format('DD-MM-YYYY');
          self.model.vendorName = response.data.vendorName;
          self.model.vendorId = response.data.vendorId;
          self.model.remark = response.data.remark;
          self.model.itemDetails = [];

          angular.forEach(response.data.items, function (item) {
            var enquiryItem = {
              "id": item.id,
              "itemId": item.itemId,
              "itemCode": item.itemCode,
              "itemName": item.itemName,
              "uomType": item.uomType,
              "uomUnit": item.uomUnit,
              "vqrQuantity": item.qrQuantity,
              "quantity": item.quantity,
              "vendorCOP": item.vendorCop,
              "remark": item.remark,
              "prList": item.prList,
              "accessoriesDetails": item.expectedAccessories,
              "warrantyPeriod": item.warrantyPeriod,
              "supportTerms": item.supportTerms,
              "paymentTerms": item.paymentTerms,
              "technicalSpecification": item.technicalSpecification,
              "vendorWarrantyPeriod": item.vendorWarrantyPeriod,
              "vendorExpectedSupport": item.vendorExpectedSupport,
              "vendorExpectedPayment": item.vendorExpectedPayment,
              "vendorTechnicalSpecification": item.vendorTechnicalSpecification
            }

            self.model.itemDetails.push(enquiryItem);
          });

          $rootScope.stopLoader();
        },
        function (err) {
          $rootScope.stopLoader();
          growl.error(err.data.message);
        });
    }

    var CreateEnquiry = function () {
      var URI = CONSTANTS.PROCUREMENT.QUOTATION.QUOTATION_FILL_IN.SAVE_API;

      var data = {
        "storeId": self.model.store.id,
        "vendorId": self.model.vendorId,
        "assetTypeId": self.model.assetType.id,
        "qrId": self.model.qrId,
        "vqrId": self.model.vqrId,
        "refNumber": self.model.refNumber,
        "refDate": moment(self.model.refDate).format('YYYY-MM-DD'),
        "statusId": setStatus('New'),
        "approvalStatusId": setStatus('New'),
        "remark": self.model.remark,
        "items": []
      };

      angular.forEach(self.model.itemDetails, function (item, key) {
        var accessoriesDetail = [];

        angular.forEach(item.accessoriesDetails, function(details){
          accessoriesDetail.push({
            "id": details.id,
            "vendorDetails": details.vendorDetails
          });
        });

        var enquiryItem = {
          // "itemId": item.itemId,
          "vendorCop": item.vendorCOP,
          "remark": item.remark,
          "qrDetailId": item.qrDetailId,
          "vqrDetailId": item.vqrDetailId,
          "accessoriesDetails": accessoriesDetail,
          "vendorWarrantyPeriod": item.vendorWarrantyPeriod,
          "vendorExpectedSupport": item.vendorExpectedSupport,
          "vendorExpectedPayment": item.vendorExpectedPayment,
          "vendorTechnicalSpecification": item.vendorTechnicalSpecification,
          "statusId": setStatus('New'),
        }

        data.items.push(enquiryItem);
      });

      $rootScope.startLoader();
      GenericService.serviceAction("POST", URI, data).then(
        function (response) {
          $rootScope.stopLoader();
          NavigateToBack();
          growl.success(response.data.message);
        },
        function (err) {
          $rootScope.stopLoader();
          growl.error(err.data.message);
        });
    }

    var UpdateEnquiry = function () {
      var URI = CONSTANTS.PROCUREMENT.QUOTATION.QUOTATION_FILL_IN.SAVE_API + self.model.enquiryId;

      var data = {
        "id": self.model.enquiryId,
        "remark": self.model.remark,
        "items": []
      };
      angular.forEach(self.model.itemDetails, function (item, key) {
        var accessoriesDetails = [];

        angular.forEach(item.accessoriesDetails, function(details){
          accessoriesDetails.push({
            "id": details.id,
            "vendorDetails": details.vendorDetails
          });
        });

        if (!item.id) {
          var enquiryItem = {
            "vendorCop": item.vendorCOP,
            "remark": item.remark,
            "qrDetailId": item.qrDetailId,
            "vqrDetailId": item.vqrDetailId,
            "accessoriesDetails": accessoriesDetails,
            "vendorWarrantyPeriod": item.vendorWarrantyPeriod,
            "vendorExpectedSupport": item.vendorExpectedSupport,
            "vendorExpectedPayment": item.vendorExpectedPayment,
            "vendorTechnicalSpecification": item.vendorTechnicalSpecification,
            "statusId": setStatus('New'),
          }
        } else {
          var enquiryItem = {
            "id": item.id,
            "vendorCop": item.vendorCOP,
            "remark": item.remark,
            "accessoriesDetails": accessoriesDetails,
            "vendorWarrantyPeriod": item.vendorWarrantyPeriod,
            "vendorExpectedSupport": item.vendorExpectedSupport,
            "vendorExpectedPayment": item.vendorExpectedPayment,
            "vendorTechnicalSpecification": item.vendorTechnicalSpecification,
          }
        }

        data.items.push(enquiryItem);
      });

      $rootScope.startLoader();
      GenericService.serviceAction("PATCH", URI, data).then(
        function (response) {
          $rootScope.stopLoader();
          NavigateToBack();
          growl.success(response.data.message);
        },
        function (err) {
          $rootScope.stopLoader();
          growl.error(err.data.message);
        });
    }

    var SaveEnquiry = function () {
      if (ValidateItems()) {
        if (!self.model.enquiryId) {
          CreateEnquiry();
        } else {
          UpdateEnquiry();
        }
      }
    }

    var FillAccessoryDetails = function (item) {
      self.model.accessoryItem = item;
      self.model.expectedAccessories = item.accessoriesDetails;
    }

    var AddAccessory = function () {
      self.model.expectedAccessories.push({"name": "", "details": "", "vendorDetails": ""});
    }

    var LessAccessory = function (item) {
      var index = self.model.expectedAccessories.indexOf(item);
      self.model.expectedAccessories.splice(index, 1);
    }

    var ValidVendorDetails = function () {
      var isValid = true;
      angular.forEach(self.model.expectedAccessories, function(detail, index){
        if (!detail.vendorDetails) {
          growl.error('Fill Required Fields');
          self.model.vendordetailsValid[index] = true;
          isValid = false;
        }
      });

      return isValid;
    }

    var SaveAccessoryDetails = function () {
      if (ValidVendorDetails()) {
        self.model.accessoryItem.accessoriesDetails = self.model.expectedAccessories;
        ClosePopup('Close', 'AccessoriesDetailsQEModal');
      }
    }

    var ViewAccessories = function (item) {
      self.model.itemDetails.find(function(x){
        if (item.itemId == x.itemId) {
          self.model.expectedAccessories = x.expectedAccessories;
        }
      });
    }

    var ViewTerms = function (item) {
      var popup = angular.element('#vendorSelectionDetailsModal2');
      popup.modal('show');
      self.model.termsOfItem = item;
      self.model.itemDetails.find(function(x){
        if (item.itemId == x.itemId) {
          self.model.warrantyPeriod = x.warrantyPeriod;
          self.model.supportTerms = x.supportTerms;
          self.model.paymentTerms = x.paymentTerms;
          self.model.technicalSpecification = x.technicalSpecification;
          self.model.vendorWarrantyPeriod = x.vendorWarrantyPeriod;
          self.model.vendorExpectedSupport = x.vendorExpectedSupport;
          self.model.vendorExpectedPayment = x.vendorExpectedPayment;
          self.model.vendorTechnicalSpecification = x.vendorTechnicalSpecification;
        }
      });
    }

    var SaveTerms = function () {
      self.model.termsOfItem["vendorWarrantyPeriod"] = self.model.vendorWarrantyPeriod;
      self.model.termsOfItem["vendorExpectedSupport"] = self.model.vendorExpectedSupport;
      self.model.termsOfItem["vendorExpectedPayment"] = self.model.vendorExpectedPayment;
      self.model.termsOfItem["vendorTechnicalSpecification"] = self.model.vendorTechnicalSpecification;

      ClosePopup('Close', 'vendorSelectionDetailsModal2');
    }

    var setStatus = function (x) {
      var status = self.model.statuses.find(function (obj) {
        return obj.status == x;
      });

      return status.id;
    }

    var OpenDueDate = function () {
      self.model.dueDateOpened = true;
    }

    var OpenFromDate = function () {
      self.model.fromDateOpened = true;
      self.model.toDateOptions.minDate = self.model.fromDate;
    }

    var OpenToDate = function () {
      self.model.toDateOpened = true;
      self.model.toDateOptions.minDate = self.model.fromDate;
    }

    var OpenRefDate = function () {
      self.model.refDateOpened = true;
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

    var ValidateDetails = function () {
      var isValid = true;
      
      if (!self.model.vendorName) {
        self.model.vendorId = undefined;
      }

      if (!self.model.store.id || !self.model.vendorId) {
        growl.error('Select Required Fields');
        isValid = false;
      }

      if (!self.model.store.id) {
        self.model.isStoreValid = true;
        isValid = false;
      }

      if (!self.model.vendorId) {
        self.model.isVendorValid = true;
        isValid = false;
      }

      return isValid;
    }

    var ValidateItems = function () {
      var isValidItem = true;
      if (self.model.itemDetails.length <= 0) {
        growl.error('Add Items to Save Enquiry');
        isValidItem = false;
      } else {
        angular.forEach(self.model.itemDetails, function (item, index){
          // if (!item.quantity || item.quantity == 0) {
          //   self.model.quantity[index] = true;
          //   isValidItem = false;
          // }

          if (!item.vendorCOP) {
            self.model.vendorCOP[index] = true;
            isValidItem = false;
          }

          // !item.quantity || item.quantity == 0 ||
          if (!item.vendorCOP) {
            growl.error("Enter Required Fields");
            isValidItem = false;
          }

          // angular.forEach(item, function (accessory, idx){
          //   if (!accessory.vendorDetails) {
          //     growl.error("Enter Vendor Details for Accessory");
          //     self.model.accessoryDetails[idx] = true;
          //     isValidItem = false;
          //   }
          // });
        });
        if (!self.model.vendorId || !self.model.vendorName) {
          self.model.isVendorValid = true;
          growl.error('Select Required Fields');
          isValidItem = false;
        }
      }

      return isValidItem;
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

    // Against Indent Item Selection
    var isSelectedItem = function (item) {
      return item.isSelectedItem;
    }

    var SelectAllIndentItems = function () {
      var selecteItem = self.model.isSelectedIndent;
      self.model.intendItemIds = [];
      angular.forEach(self.model.qrItemList, function(po){
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
        angular.forEach(self.model.qrItemList, function(po){
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

    var NavigateToBack = function () {
      $state.go('enquirySentToVendor');
    }

    var Expand = function (item) {
      item.Expanded = !item.Expanded;
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

    initializeController();
  }
 
  function config($stateProvider) {
    $stateProvider.state("addNewquotationFillIn", {
      url: "/addNewquotationFillIn",
      templateUrl: 'views/procurement/quotation/add-new-quotation-fill-in.html',
      controller: "AddNewQuotationFillIn.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("AddNewQuotationFillIn.Controller", addNewQuotationFillInController);
})();