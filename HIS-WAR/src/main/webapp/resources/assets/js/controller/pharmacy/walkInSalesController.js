(function () {
  "use strict";

  function walkInSalesController($scope, $rootScope, $window, $http, $filter, CONSTANTS, GenericService, growl, CurrencyService, StatusService) {
    var self = this;
    var discountLimit = 50;

    function initializeController() {
      $rootScope.loginpage = true;

      self.model = {
        isOTC: false,
        walkInSale: {
          id: "1" + (new Date()).getTime(),
          items: [],
          discountType: '2'
        },
        isBatchValid: [],
        isUOMValid: [],
        isQuantityValid: [],
        prefixList: [{
          "prefix": "Select"
        }],
        patientGenderList: [{
          "gender": "Select"
        }],
        discountCategoryList: [{
          "discountCategory": "Select Discount Category"
        }]
      };

      //Methods 
      CurrencyService.GetCurrency().then(function (currency) {
        self.model.Currency = currency;
      })
      StatusService.GetStatus().then(function (status) {
        self.model.Status = status;
      })

      self.model.OtcFlag = OtcFlag;
      self.model.SearchItems = SearchItems;
      self.model.AddItemToBill = AddItemToBill;
      self.model.BatchChange = BatchChange;
      self.model.UomChange = UomChange;
      self.model.ApplyDiscount = ApplyDiscount;
      self.model.ApplyDiscountValidation = ApplyDiscountValidation;
      self.model.AddItem = AddItem;
      self.model.RemoveItem = RemoveItem;
      self.model.AllocateQuantity = AllocateQuantity;
      self.model.SaveBill = SaveBill;
      self.model.SavePopup = SavePopup;
      self.model.WalkInSalesFlagStatus = WalkInSalesFlagStatus;
      self.model.ClearBill = ClearBill;
      self.model.SetDecimal = SetDecimal;
      self.model.BarcodeScan = BarcodeScan;
      self.model.SearchBatch = SearchBatch;
      self.model.AddBatchToBill = AddBatchToBill;

      SetFocus();

      PopulateDropdown();
    }

    var OtcFlag = function () {
      self.model.search = '';
      self.model.prevSearch = '';
      self.model.items = [];

      if (!!self.model.isOTC) {
        self.model.isDoctorNameValid = false;
        self.model.isDoctorAddressValid = false;
      }
    }
    var SetStatus = function (x) {
      var status = self.model.Status.find(function (obj) {
        return obj.status == x;
      });

      return status.id;
    }
    var PopulateDropdown = function () {
      var URI = CONSTANTS.PHARMACY.OP.WALK_IN_SALES.DROPDOWN_API;

      self.model.walkInSale.discountCatgeory = self.model.discountCategoryList[0];
      self.model.walkInSale.prefix = self.model.prefixList[0];
      self.model.walkInSale.patientGender = self.model.patientGenderList[0];

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          self.model.prefixList = self.model.prefixList.concat(response.data.prefix);
          self.model.patientGenderList = self.model.patientGenderList.concat(response.data.gender);
          self.model.discountCategoryList = self.model.discountCategoryList.concat(response.data.discountCategory);

          $rootScope.stopLoader();
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
        });
    }

    var SearchItems = function (search) {
      if (search.length < 3) return;

      if ((!self.model.prevSearch || self.model.prevSearch != search) && (search.length == 3)) {
        self.model.prevSearch = search;

        var URI = CONSTANTS.GLOBAL.ITEM_API + self.model.isOTC + "/" + search;
        $rootScope.startLoader();
        return GenericService.serviceAction("GET", URI, {})
          .then(function (response) {
            $rootScope.stopLoader();
            if (!!response.data && response.data.length > 0) {
              self.model.items = response.data;
              return $filter('filter')(self.model.items, {
                $: search
              });
            } else {
              NoItemFound(search);
              return self.model.items;
            }
          }, function (err) {
            NoItemFound(search);
            $rootScope.stopLoader();
            return self.model.items;
          });
      } else {
        if (!!self.model.items && self.model.items.length > 0 &&
          self.model.items[0].itemFound != undefined && !self.model.items[0].itemFound) {
          return NoItemFound(search);
        } else {
          return $filter('filter')(self.model.items, {
            $: search
          });
        }
      }
    }

    var NoItemFound = function (search) {
      var item = {
        "itemFound": false,
        "details": "No item found with item code or name " + search
      };
      self.model.items = [item];
    }

    var AddItemToBill = function (bill, item, model, label) {
      if (!!item.id) {
        var URI = CONSTANTS.GLOBAL.ITEM_API + item.id;

        $rootScope.startLoader();
        GenericService.serviceAction("GET", URI, {})
          .then(function (response) {
            response.data.batch.unshift({
              batchDetails: "Select Batch"
            });
            var item = {
              itemId: response.data.itemId,
              itemCode: response.data.itemCode,
              itemName: response.data.itemName,
              genericId: response.data.genericId,
              genericName: response.data.genericName,
              manufacturerId: response.data.manufacturerId,
              manufacturerName: response.data.manufacturerName,
              batch: response.data.batch,
              selectedBatch: response.data.batch[0],
              uom: [{
                uomDetails: "Select UOM",
              }],
              taxId: response.data.taxId,
              taxName: response.data.taxName,
              taxPercent: response.data.taxPercent,
              isOTC: self.model.isOTC
            };

            item.selectedUom = item.uom[0];
            bill.items.push(item);
            self.model.search = '';
            $rootScope.stopLoader();
          }, function (err) {
            growl.error('Error while fetching the item details');
            $rootScope.stopLoader();
          });
      }
    }

    var BatchChange = function (index, bill, batch) {
      var item = bill.items[index];

      item.uom = [{
        uomDetails: "Select UOM",
      }];

      if (!!batch.batchId) {
        item.uom = item.uom.concat(batch.uom);

        item.selectedUom = item.uom.find(function (obj) {
          return obj.opUom;
        });

        CalculateRate(item, item.selectedUom, item.uom);

        AllocateQuantity(index, bill);
      } else {
        item.selectedUom = item.uom[0];
        item.quantity = null;
        item.rate = 0;
      }
    }

    var UomChange = function (index, bill, uom) {
      var item = bill.items[index];
      if (!!uom) {
        var uomList = item.uom
        CalculateRate(item, uom, uomList);

      } else {
        item.rate = 0;
      }

      AllocateQuantity(index);
    }

    var CalculateRate = function (item, uom, uomList) {
      if (!!uom) {
        var sellingPrice = !!item.selectedBatch ? item.selectedBatch.sellingPrice : 0;

        var conversion = 1;
        var flag = false;

        angular.forEach(uomList, function (value, key) {
          if (value.uomTypeId == uom.uomTypeId || flag) {
            conversion = conversion * value.conversion;
            flag = true;
          }
        });

        item.rate = conversion * sellingPrice;
      } else {
        item.rate = 0;
      }
    }

    var SetDecimal = function () {
      if (isNaN(self.model.walkInSale.discount) || !self.model.walkInSale.discount) {
        self.model.walkInSale.discount = 0;
      } else {
        self.model.walkInSale.discount = parseFloat(self.model.walkInSale.discount).toFixed(2);
      }
    }

    var ApplyDiscount = function (bill) {
      var item = bill.items;
      var discount = parseFloat(bill.discount);
      if (!isNaN(discount)) {
        if (bill.discountType == '2') {
          if (discount < bill.totalAmount) {
            var itemDiscount = discount / bill.totalAmount;

            angular.forEach(bill.items, function (value, key) {
              if (value.totalAmount > itemDiscount) {
                value.itemDiscount = value.totalAmount * itemDiscount;
              } else {
                value.itemDiscount = 0;
              }

              var unitDiscountAmount = value.itemDiscount / value.quantity;
              bill.items[key]['unitDiscountAmount'] = unitDiscountAmount;
            });
          } else {
            growl.error('Please enter discount amount less than Total Amount');
          }
        } else if (bill.discountType == '1') {
          if (self.model.walkInSale.discount <= discountLimit) {
            angular.forEach(bill.items, function (value, key) {
              value.itemDiscount = value.totalAmount * discount * 0.01;

              var unitDiscountAmount = value.itemDiscount / value.quantity;
              bill.items[key]['unitDiscountAmount'] = unitDiscountAmount;
            });
          } else {
            growl.error('Please enter discount amount less than ' + discountLimit + '%');
          }

        }
      }
    }

    var ApplyDiscountValidation = function (bill) {
      self.model.discountTypeFlag = false
      if (!self.model.walkInSale.discount) {
        if (bill.discountType == '1'){
        growl.error('Please enter % discount');
        }
        if (bill.discountType == '2'){
          growl.error('Please enter discount amount');
        }
      }
    }

    var AddItem = function (index, bill) {
      bill.items.splice(index + 1, 0, CopyItem(index, bill));
      SetFocus();
    }

    var CopyItem = function (index, bill) {
      var item = bill.items[index];
      return {
        itemCode: item.itemCode,
        itemName: item.itemName,
        genericName: item.genericName,
        manufacturerName: item.manufacturerName,
        batch: item.batch,
        selectedBatch: item.batch[0],
        uom: item.uom,
        selectedUom: item.uom[0],
        taxId: item.taxId,
        taxName: item.taxName,
        taxPercent: item.taxPercent
      }
    }

    var RemoveItem = function (index, bill) {
      var item = bill.items[index];

      if (!!item.allocationId) {
        var URI = CONSTANTS.PHARMACY.OP.WALK_IN_SALES.ALLOCATE_API + bill.id + "/" + item.allocationId;

        $rootScope.startLoader();
        GenericService.serviceAction("DELETE", URI, {}).then(
          function (response) {
            if (!!response.data) {
              bill.items.splice(index, 1);
            }
            $rootScope.stopLoader();
            growl.success(response.data.message);

          },
          function (ex) {
            $rootScope.stopLoader();
            growl.error('Error while deleting allocated quantity');
          });
      } else {
        bill.items.splice(index, 1);
      }

        self.model.isItemValid[index] = false;
        self.model.isBatchValid[index] = false;
        self.model.isUOMValid[index] = false;
        self.model.isQuantityValid[index] = false;
     

    }

    var AllocateQuantity = function (index, bill) {

      if (!!bill) {
        var item = bill.items[index];

        CalculateQuantity(item, item.selectedUom, item.uom);

        if (CanAllocate(item)) {
          var URI = CONSTANTS.PHARMACY.OP.WALK_IN_SALES.ALLOCATE_API + bill.id + "/" + (!!item.allocationId ? item.allocationId : "");
          var data = {
            "batchId": item.selectedBatch.batchId,
            "quantity": item.leaseQuantity
          };

          $rootScope.startLoader();
          GenericService.serviceAction("PUT", URI, data).then(
            function (response) {
              if (!!response.data) {
                item.allocationId = response.data.id;
                StorePreviousValue(item);
              }
              $rootScope.stopLoader();
            },
            function (ex) {

              growl.error(ex.data.message);
              RestorePreviousValue(item);
              $rootScope.stopLoader();
            });
        } else {
          StorePreviousValue(item);
        }
      }
    }

    var CanAllocate = function (item) {
      if (!!item.selectedBatch && !!item.selectedUom &&
        (!!item.quantity) && !(item.prevQuantity == item.quantity && item.quantity == '')) {
        return true;
      }

      return false;
    }

    var StorePreviousValue = function (item) {
      item.prevBatch = item.selectedBatch;
      item.prevUom = item.selectedUom;
      item.prevQuantity = item.quantity;

      CalculateRate(item, item.selectedUom, item.uom);
    }

    var RestorePreviousValue = function (item) {
      item.selectedBatch = item.prevBatch;

      if (!!item.selectedBatch) {
        item.selectedUom = item.prevUom;
      } else {
        item.uom = [{
          uomDetails: "Select UOM",
        }];
        item.selectedUom = item.uom[0];
        item.rate = 0;
      }

      item.quantity = item.prevQuantity;

      CalculateRate(item, item.selectedUom, item.uom);
    }

    var CalculateQuantity = function (item, uom, uomList) {
      if (!!uom) {
        var conversion = 1;
        var flag = false;

        angular.forEach(uomList, function (value, key) {
          if (value.uomTypeId == uom.uomTypeId || flag) {
            conversion = conversion * value.conversion;
            flag = true;
          }
        });

        item.leaseQuantity = conversion * item.quantity;
      } else {
        item.leaseQuantity = 0;
      }
    }

    var SavePopup = function () {
      var popup = angular.element('#confirmSave');
      popup.modal('show');
    }

    var SaveBill = function (bill) {
      if (ValidateBill(bill)) {
        var URI = CONSTANTS.PHARMACY.OP.WALK_IN_SALES.SAVE_API;

        var data = {
          "tempBillId": !!bill.id ? bill.id : undefined,
          "screenId": 1,
          "prefixId": (bill.isWalkInSales && !!bill.prefix) ? bill.prefix.id : undefined,
          "patientId": (!bill.isWalkInSales && !!bill.patientId) ? bill.patientId : undefined,
          "patientName": (bill.isWalkInSales && !!bill.patientName) ? bill.patientName : undefined,
          "patientAddress": (bill.isWalkInSales && !!bill.patientAddress) ? bill.patientAddress : undefined,
          "patientAge": (bill.isWalkInSales && !!bill.age) ? bill.age : undefined,
          "patientGenderId": (bill.isWalkInSales && !!bill.patientGender) ? bill.patientGender.id : undefined,
          "doctorId": (!bill.isWalkInSales && !!bill.doctorId) ? bill.doctorId : undefined,
          "doctorName": (bill.isWalkInSales && !!bill.doctorName) ? bill.doctorName : undefined,
          "doctorAddress": (bill.isWalkInSales && !!bill.doctorAddress) ? bill.doctorAddress : undefined,
          "discountTypeId": !!bill.discountType ? bill.discountType : undefined,
          "discountCatgeoryId": !!bill.discountCatgeory.id ? bill.discountCatgeory.id : undefined,
          "discount": !!bill.discount ? bill.discount : 0,
          "totalAmount": !!bill.totalAmount ? bill.totalAmount : undefined,
          "discountAmount": !!bill.totalDiscountAmount ? bill.totalDiscountAmount : undefined,
          "netAmount": !!bill.netAmount ? bill.netAmount : undefined,
          "roundOffAmount": !!bill.roundOff ? bill.roundOff : 0,
          "taxAmount": !!bill.totalTaxAmount ? bill.totalTaxAmount : 0,
          "saleTypeId": 3,
          "statusId": SetStatus('Pending'),
          "items": []
        }

        angular.forEach(bill.items, function (item, key) {
          var billItem = {

            "batchId": item.selectedBatch.batchId,
            "uomTypeId": item.selectedUom.uomTypeId,
            "uomUnitId": item.selectedUom.uomUnitId,
            "rate": item.rate,
            "quantity": Number(item.quantity),
            "leaseRate": item.selectedBatch.sellingPrice,
            "leaseQuantity": item.leaseQuantity,
            "totalAmount": item.totalAmount,
            "discountAmount": item.itemDiscount,
            "unitDiscountAmount": item.unitDiscountAmount,
            "netAmount": item.netAmount,
            "taxId": item.taxId,
            "taxPercentage": item.taxPercent,
            "taxAmount": item.taxAmount

          }
          data.items.push(billItem);
        });

        $rootScope.startLoader();
        GenericService.serviceAction("POST", URI, data).then(
          function (response) {
            ClearFields(bill);
            $rootScope.stopLoader();
            growl.success('Walk-in sales bill successffully saved');
          },
          function (err) {
            $rootScope.stopLoader();
            growl.error('Error while saving walk-in sales bill');
          });
      }
    }

    var WalkInSalesFlagStatus = function (bill) {
      if (!self.model.walkInSale.isWalkInSales) {
        self.model.isPatientNameValid = false;
        self.model.isPrefixValid = false;
        self.model.isPatientAddressValid = false;
        self.model.ispatientGenderValid = false;
        self.model.isAgeValid = false;
        self.model.isDoctorNameValid = false;
        self.model.isDoctorAddressValid = false;

        self.model.walkInSale.prefix = self.model.prefixList[0];
        self.model.walkInSale.patientName = undefined;
        self.model.walkInSale.patientAddress = undefined;
        self.model.walkInSale.patientGender = self.model.patientGenderList[0];
        self.model.walkInSale.age = undefined;
        self.model.walkInSale.doctorName = undefined;
        self.model.walkInSale.doctorAddress = undefined;


      }
    }

    var ValidateBill = function (bill) {
      var isValid = true;

      if(!bill.isWalkInSales){
        if (!bill.patientId) {
          isValid = false;
          self.model.isPatientIdValid = true;
          growl.error('Please add patient details');
        }
      }

      if (!!bill.isWalkInSales) {
        if (!bill.prefix.id) {
          isValid = false;
          self.model.isPrefixValid = true;
        }
        if (!bill.patientName) {
          isValid = false;
          self.model.isPatientNameValid = true;
        }
        if (!bill.patientAddress) {
          isValid = false;
          self.model.isPatientAddressValid = true;
        }
        if (!bill.patientGender.id) {
          isValid = false;
          self.model.isPatientGenderValid = true;
        }
        if (!bill.age) {
          isValid = false;
          self.model.isAgeValid = true;
        }
        if (!bill.doctorName) {
          isValid = false;
          self.model.isDoctorNameValid = true;
        }
        if (!bill.doctorAddress) {
          isValid = false;
          self.model.isDoctorAddressValid = true;
        }
      }

      if (!self.model.isOTC) {
        if (!bill.doctorName) {
          isValid = false;
          self.model.isDoctorNameValid = true;
        }
        if (!bill.doctorAddress) {
          isValid = false;
          self.model.isDoctorAddressValid = true;
        }
      }

      if (self.model.walkInSale.discount) {
        if (!bill.discountCatgeory.id) {
          isValid = false;
          self.model.isDiscountCategoryValid = true;
        }

        if (!self.model.isDiscountApplied) {
          isValid = false;
          self.model.isDiscountAppliedValid = true;
          growl.error('Please apply discount on the bill items');
        }

        if(self.model.discountTypeFlag == true){
          isValid = false;
          self.model.isDiscountAppliedValid = true;
          growl.error('Please apply discount on the bill items');
        }
        
        if (self.model.isAppliedDiscountValid == true){
          isValid = false;
          growl.error('Please apply discount on the bill items');
          }   
      }

      if(self.model.walkInSale.discountCatgeory.id){
        if(!self.model.walkInSale.discount){
          isValid = false;
          self.model.isDiscountAmountValid = true;
          growl.error('Please enter discount amount');
        }
      }

      if (!bill.items || bill.items.length <= 0) {
        isValid = false;
        growl.error("Please add some items in the bill");
      } else {
        for (var i = 0; i < bill.items.length; i++) {
          if (!bill.items[i].isOTC) {
            if (!bill.doctorName) {
              isValid = false;
              self.model.isDoctorNameValid = true;
            }
            if (!bill.doctorAddress) {
              isValid = false;
              self.model.isDoctorAddressValid = true;
            }
          }
          if (!bill.items[i].selectedBatch.batchId || !bill.items[i].selectedUom.uomUnitId || !bill.items[i].quantity) {
            if (!self.model.isItemValid) {
              Object.assign(self.model, {
                isItemValid: []
              })
            }
            self.model.isItemValid[i] = true;
            isValid = false;
          }
          if (!bill.items[i].selectedBatch.batchId) {
            self.model.isBatchValid[i] = true;
            isValid = false;
          }
          if (!bill.items[i].selectedUom.uomUnitId) {
            self.model.isUOMValid[i] = true;
            isValid = false;
          }
          if (!bill.items[i].quantity) {
            self.model.isQuantityValid[i] = true;
            isValid = false;
          }

        }
      }
      return isValid;
    }

    var ClearFields = function (bill) {

      self.model.walkInSale.isWalkInSales = false;
      self.model.isOTC = false;
      bill.patientId = null;
      bill.prefix = self.model.prefixList[0];
      bill.patientName = null;
      bill.patientAddress = null;
      bill.age = null;
      bill.patientGender = self.model.patientGenderList[0];
      bill.doctorName = null;
      bill.doctorAddress = null;
      self.model.search = null;
      self.model.searchBatch = null;
      bill.Id = "1" + (new Date()).getTime();
      bill.discountTypeId = null;
      bill.discountCatgeory = self.model.discountCategoryList[0];
      bill.discount = null;
      bill.totalAmount = null;
      bill.totalDiscountAmount = null;
      bill.taxAmount = null;
      bill.netAmount = null;
      bill.items = [];
    }

    var ClearBill = function (bill) {
      self.model.walkInSale.prefix = self.model.prefixList[0];
      self.model.walkInSale.patientName = undefined;
      self.model.walkInSale.patientAddress = undefined;
      self.model.walkInSale.patientGender = self.model.patientGenderList[0];
      self.model.walkInSale.age = undefined;
      self.model.walkInSale.doctorName = undefined;
      self.model.walkInSale.doctorAddress = undefined;
      self.model.search = undefined;
      self.model.searchBatch = undefined;
      self.model.barcode = undefined;
      self.model.walkInSale.discount = undefined;
      self.model.walkInSale.discountCatgeory = self.model.discountCategoryList[0];

      if (bill.items.length > 0) {
        var URI = CONSTANTS.PHARMACY.OP.WALK_IN_SALES.ALLOCATE_API + bill.id;

        $rootScope.startLoader();
        GenericService.serviceAction("DELETE", URI, {}).then(
          function (response) {
            if (!!response.data) {
              bill.items = [];
            }
            $rootScope.stopLoader();
            growl.success('Bill cleared successfully');
          },
          function (ex) {

            $rootScope.stopLoader();
            growl.error('Error while clearing the bill');
          });
      }
    }

    var SearchBatch = function (search) {
      if (search.length < 3) return;

      if ((!self.model.prevBatchSearch || self.model.prevBatchSearch != search) && (search.length == 3)) {
        self.model.prevBatchSearch = search;
        var URI = CONSTANTS.GLOBAL.BATCH_API + self.model.isOTC + "/" + search;
        $rootScope.startLoader();
        return GenericService.serviceAction("GET", URI, {})
          .then(function (response) {
            $rootScope.stopLoader();
            if (!!response.data && response.data.length > 0) {
              self.model.batches = response.data;
              return $filter('filter')(self.model.batches, {
                $: search
              });
            } else {
              NoBatchFound(search);
              return self.model.batches;
            }
          }, function (err) {
            NoBatchFound(search);
            $rootScope.stopLoader();
            return self.model.batches;
          });
      } else {
        if (!!self.model.batches && self.model.batches.length > 0 &&
          self.model.batches[0].itemFound != undefined && !self.model.batches[0].itemFound) {
          return NoBatchFound(search);
        } else {
          return $filter('filter')(self.model.batches, {
            $: search
          });
        }
      }
    }

    var NoBatchFound = function (search) {
      var item = {
        "itemFound": false,
        "details": "No batch found with batch number " + search
      };
      self.model.batches = [item];
    }

    var SetFocus = function () {
      self.model.barcode = undefined;
      $window.document.getElementById('barcode').focus();
    }

    var BarcodeScan = function (event, barcode) {
      var enterCode = 13;
      if (!barcode && event.which === enterCode) {
        barcode = self.model.barcode;
      }

      if (!!barcode) {
        var itemId = barcode.split(' ')[0];
        var batchId = barcode.split(' ')[1];

        if (!!itemId && !!batchId) {
          AllocateBatch(self.model.walkInSale, itemId, batchId);
        }

        event.stopPropagation();
        event.preventDefault();

        SetFocus();
      }
    }

    var AddBatchToBill = function (bill, item, model, label) {
      if (!!item.id && !!item.batchId) {
        AllocateBatch(bill, item.id, item.batchId);
      }
    }

    var AllocateBatch = function (bill, itemId, batchId) {
      var URI = CONSTANTS.PHARMACY.OP.WALK_IN_SALES.ALLOCATE_BATCH_API + bill.id;
      var data = {
        "itemId": itemId,
        "batchId": batchId
      };

      $rootScope.startLoader();
      GenericService.serviceAction("PUT", URI, data).then(
        function (response) {
          if (!!response.data) {
            var item = bill.items.find(function (obj) {
              return obj.allocationId == response.data.allocationId;
            });

            if (!!item) {
              item.quantity = parseInt(item.quantity) + 1;
              CalculateQuantity(item, item.selectedUom, item.uom);
            } else {
              var batch = response.data.batch.find(function (obj) {
                return obj.batchId == batchId;
              });

              item = {
                allocationId: response.data.allocationId,
                itemId: response.data.itemId,
                itemCode: response.data.itemCode,
                itemName: response.data.itemName,
                genericId: response.data.genericId,
                genericName: response.data.genericName,
                manufacturerId: response.data.manufacturerId,
                manufacturerName: response.data.manufacturerName,
                batch: [{
                  batchDetails: "Select Batch"
                }].concat(response.data.batch),
                uom: [{
                  uomDetails: "Select UOM",
                }],
                taxId: response.data.taxId,
                taxName: response.data.taxName,
                taxPercent: response.data.taxPercent,
                isOTC: response.data.isOTC
              };

              if (!!batch) {
                item.selectedBatch = batch;
                item.uom = item.uom.concat(batch.uom);
                item.selectedUom = batch.uom.find(function (obj) {
                  return obj.opUom;
                });
                item.quantity = 1;
              } else {
                item.selectedBatch = item.batch[0];
                item.selectedUom = item.uom[0];
              }

              CalculateQuantity(item, item.selectedUom, item.uom);
              CalculateRate(item, item.selectedUom, item.uom);

              bill.items.push(item);
            }
          }
          $rootScope.stopLoader();
        },
        function (ex) {
          growl.error(ex.data.message);
          $rootScope.stopLoader();
        });
    }

    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("walkInSales", {
      url: "/walkInSales",
      templateUrl: 'views/PharmacyInventory/InPatient/walk-in-sales.html',
      controller: "WalkInSales.Controller",
      controllerAs: "vm"
    });
  }

  angular.module("myApp")
    .config(config)
    .controller("WalkInSales.Controller", walkInSalesController);

})();