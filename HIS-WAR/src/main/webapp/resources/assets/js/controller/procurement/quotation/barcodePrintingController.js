(function () {
  "use strict";

  function barcodePrintingController($scope, $rootScope, $http, $filter, $state, $window, $timeout, CONSTANTS, GenericService, CurrencyService, growl) {
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
        storeList: [{
          "store": "Select Store"
        }],
        assetType: 2,
        SelectedItems: [],
        SelectedBatches: [],
        items: [],
        options: {
          width: 1,
          height: 15,
          displayValue: false,
          backgroundColor: '#ffffff',
          lineColor: '#000000',
          margin: 0
        }
      };

      CurrencyService.GetCurrency().then(function (currency) {
        self.model.Currency = currency;
      })

      //Methods
      self.model.ShowModalPopup = ShowModalPopup;
      self.model.PopulateProductCategory = PopulateProductCategory;
      self.model.PopulateBatchList = PopulateBatchList;
      self.model.SelectAllItems = SelectAllItems;
      self.model.SelectItem = SelectItem;
      self.model.AddItems = AddItems;
      self.model.ShowBarcode = ShowBarcode;
      self.model.SelectAllBatches = SelectAllBatches;
      self.model.SelectBatch = SelectBatch;
      self.model.PrintBarcode = PrintBarcode;

      PopulateDropdown();
    }

    //Method to populate the list of Purchase Request dropdown
    var PopulateDropdown = function () {
      var barcode = self.model;
      barcode.store = barcode.storeList[0];

      var URI = CONSTANTS.GLOBAL.USER_STORE_API;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          barcode.storeList = barcode.storeList.concat(response.data);
          $rootScope.stopLoader();
        },
        function (err) {
          $rootScope.stopLoader();
        });
    }

    var ShowModalPopup = function (popup) {

      if (!!self.model.store.id) {
        if (popup == "item") {
          PopulateAssetCategory();
          var popup = angular.element('#getBarcodeItemList');
          popup.modal('show');
        } else if (popup == "grn") {
          var popup = angular.element('#getGRNModal');
          popup.modal('show');
        }
      } else {
        growl.error('Please select the store.');
      }
    }

    var PopulateAssetCategory = function () {
      var barcode = self.model;

      var URI = CONSTANTS.GLOBAL.ASSET_CATEGORY_API + barcode.assetType;

      barcode.assetCategory = barcode.assetCategories[0];
      barcode.productCategory = barcode.productCategories[0];

      barcode.assetCategories.splice(1);
      barcode.productCategories.splice(1);

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          barcode.assetCategories = barcode.assetCategories.concat(response.data);
          $rootScope.stopLoader();
        },
        function (err) {
          $rootScope.stopLoader();
        });
    }

    var PopulateProductCategory = function (assetCategory) {
      var barcode = self.model;

      var URI = CONSTANTS.GLOBAL.PRODUCT_CATEGORY_API + assetCategory.id;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          barcode.productCategories = barcode.productCategories.concat(response.data);
          $rootScope.stopLoader();
        },
        function (err) {
          $rootScope.stopLoader();
        });
    }

    var PopulateBatchList = function () {
      var barcode = self.model;

      var URI = CONSTANTS.PROCUREMENT.QUOTATION.GET_BATCHES_API;

      var data = {
        "assetTypeId": barcode.assetType.id,
        "assetCategoryId": barcode.assetCategory.id,
        "productCategoryId": barcode.productCategory.id,
        "storeId": barcode.store.id
      };

      $rootScope.startLoader();
      GenericService.serviceAction("POST", URI, data).then(
        function (response) {
          barcode.batchList = response.data;
          $rootScope.stopLoader();
        },
        function (err) {
          $rootScope.stopLoader();
        });
    }

    var isSelected = function (item) {
      return item.isSelected;
    }

    var SelectAllItems = function () {
      if (self.model.selectAllItems) {
        self.model.SelectedItems = self.model.items.map(function (item) {
          item.isSelected = true;
          return item;
        });
      } else {
        self.model.SelectedItems.splice(0);
        self.model.items.forEach(function (item) {
          item.isSelected = false;
        });
      }
    }

    var SelectItem = function (item) {
      var items = self.model.SelectedItems;

      if (item.isSelected) {
        items.push(item);
      } else {
        var index = items.indexOf(item);
        if (index > -1) {
          items.splice(index, 1);
        }
      }

      if (self.model.items.every(isSelected)) {
        self.model.selectAllRows = true;
      } else {
        self.model.selectAllRows = false;
      }
    }

    var SelectAllBatches = function () {
      if (self.model.selectAllBatches) {
        self.model.SelectedBatches = self.model.batchList.map(function (batch) {
          batch.isSelected = true;
          return batch;
        });
      } else {
        self.model.SelectedBatches.splice(0);
        self.model.batchList.forEach(function (batch) {
          batch.isSelected = false;
        });
      }
    }

    var SelectBatch = function (batch) {
      var batches = self.model.SelectedBatches;

      if (batch.isSelected) {
        batches.push(batch);
      } else {
        var index = batches.indexOf(batch);
        if (index > -1) {
          batches.splice(index, 1);
        }
      }

      if (self.model.batchList.every(isSelected)) {
        self.model.selectAllBatches = true;
      } else {
        self.model.selectAllBatches = false;
      }
    }

    var AddItems = function () {
      var batches = [];
      angular.copy(self.model.SelectedBatches, batches);

      self.model.batchList = [];

      batches.forEach(function (batch) {
        batch.isSelected = false;
        var item = $filter('filter')(self.model.items, {
          batchId: batch.batchId
        }, true);

        if (item.length === 0) {
          self.model.items.push(batch);
        }
      });

      self.model.selectAllBatches = false;
      self.model.SelectedBatches.splice(0);
    }

    var ShowBarcode = function (item) {
      self.model.barcode = {
        type: "code128",
        code: item.itemId.toString() + " " + item.batchId.toString(),
        hospital: item.hospitalName,
        itemCode: item.itemCode,
        batchCode: item.batchNo,
        expiry: item.expiry,
        mrp: self.model.Currency + $filter('number')(item.mrp, 2)
      }
      var popup = angular.element('#barcodePreview');
      popup.modal('show');
    }

    var PrintBarcode = function (item) {
      self.model.barcode = {
        type: "code128",
        code: item.itemId.toString() + " " + item.batchId.toString(),
        hospital: item.hospitalName,
        itemCode: item.itemCode,
        batchCode: item.batchNo,
        expiry: item.expiry,
        mrp: self.model.Currency + $filter('number')(item.mrp, 2)
      }

      $timeout(function () {
        var printContents = $window.document.getElementById('barcode').innerHTML;
        var popupWin = $window.open('', '_blank', 'width=300,height=400');
        popupWin.document.open();
        popupWin.document.write('<html><head><link rel="stylesheet" type="text/css" href="style.css" /></head><body onload="window.print()">' + printContents + '</body></html>');
        popupWin.document.close();
      }, 100);
    }

    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("barcodePrinting", {
      url: "/barcodePrinting",
      templateUrl: 'views/procurement/quotation/barcode-printing.html',
      controller: "BarcodePrinting.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("BarcodePrinting.Controller", barcodePrintingController);
})();