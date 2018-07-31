(function () {
    "use strict";
  
    function storeStockDashboardController($scope, $rootScope, $http, CONSTANTS, GenericService, growl) {
      var self = this;
      var stockId ;
      //Default Constructor
      function initializeController() {
        $rootScope.loginpage = true;
        self.model = {
            stores: [{
                "store": "Select Store"
              }],
              assetCategories: [{
                "category": "All"
              }],
              productCategories: [{
                "category": "All"
              }],
        
      };
  self.model.GetProductCategoryList  = GetProductCategoryList;
  self.model.searchStock = searchStock;
  self.model.viewModal = viewModal;
  self.model.isConsignment = false;
  self.model.stockBatch =stockBatch;  
  self.model.vtype = 0, 
  self.model.SearchItem = SearchItem;
  self.model.BatchOnChange = BatchOnChange;
  self.model.ValidateDashboard = ValidateDashboard;
  self.model.clearDetail = clearDetail;

  PopulateDropdown();
  GetAssetCategoryList(); 
    }
    var PopulateDropdown = function () {
        var stock = self.model;
  
        var URI = CONSTANTS.GLOBAL.USER_STORE_API;
  
        stock.store = stock.stores[0]; 
        stock.stores.splice(1); 
        $rootScope.startLoader();
        GenericService.serviceAction("GET", URI, {}).then(
          function (response) {
            stock.stores = stock.stores.concat(response.data);
            $rootScope.stopLoader(); 
          },
          function (err) {
            $rootScope.stopLoader();
          });
      }

    var GetAssetCategoryList = function () {
        var stock = self.model;
  
        var URI = CONSTANTS.GLOBAL.ASSET_CATEGORY_API  + 1;
  
        stock.assetCategory = stock.assetCategories[0];
        stock.productCategory = stock.productCategories[0];
  
        stock.assetCategories.splice(1);
        stock.productCategories.splice(1);
  
        $rootScope.startLoader();
        GenericService.serviceAction("GET", URI, {}).then(
          function (response) {
            stock.assetCategories = stock.assetCategories.concat(response.data);
            $rootScope.stopLoader();
          },
          function (err) {
            $rootScope.stopLoader();
          });
      }
      


    var GetProductCategoryList = function (id) {
        var stock = self.model;
  
        //TODO : Add asset category to the uri
        var URI = CONSTANTS.GLOBAL.PRODUCT_CATEGORY_API  +  id;
        $rootScope.startLoader();
        GenericService.serviceAction("GET", URI, {}).then(
          function (response) {
            stock.productCategories = stock.productCategories.concat(response.data);
            $rootScope.stopLoader();
          },
          function (err) {
            $rootScope.stopLoader();
          });
      }
  
      var searchStock = function () {
        if (ValidateDashboard()) {
        var URI = CONSTANTS.INVENTORY.STORE.STORE_STOCK_DASHBOARD.SEARCH_API;
        var data = {
          
          "storeId": self.model.store.id ,
          "isConsignment": self.model.isConsignment ,
          "assetTypeId": 1,
          "itemNo": self.model.itemNo ? self.model.itemNo : undefined,
          "itemCode":self.model.itemCode ? self.model.itemCode : undefined,
          "itemName":self.model.itemName ? self.model.itemName : undefined,
          "assetCategoryId": self.model.assetCategory.id ? self.model.assetCategory.id : undefined,
          "productCategoryId": self.model.productCategory.id  ,
          "vedId" : self.model.vtype ? self.model.vtype: undefined,
        };
        $rootScope.startLoader()
      
        GenericService.serviceAction("POST", URI, data).then(
          function (response) {
            $rootScope.stopLoader();
            // for (var i = 0; i < response.data.length; i++) {
            //   response.data[i]["isChecked"] = false;
            // }
            self.model.searchStockData = response.data;  
          },
       
          function errorCallback(response) {
            $rootScope.stopLoader();
            growl.error('Error');
          });    }
      }
  
      var viewModal = function (stock) { 
        $("#currentStockViewModal").modal()
        self.model.stockId = stock.id;
        stockBatch();
      }
  
      var stockBatch = function () {
        var URI = CONSTANTS.INVENTORY.STORE.STORE_STOCK_DASHBOARD.BATCH_API + self.model.store.id + "/"  + self.model.stockId ; 
        $rootScope.startLoader()
        GenericService.serviceAction("GET", URI, {}).then(
          function (response) {
            $rootScope.stopLoader(); 
            self.model.batchStockData = response.data; 
          },
          function errorCallback(response) {
            $rootScope.stopLoader();
            growl.error('Error');
          });
      }


      var SearchItem = function (search) {

        if (search.length < 3) return;
  
        if ((!self.model.prevSearch || self.model.prevSearch != search) && (search.length == 3)) {
          self.model.prevSearch = search;
          var URI = CONSTANTS.GLOBAL.ITEM_API;
          $rootScope.startLoader();
          var data = {
              "assetTypeId": 1,
              //"assetCategoryId": self.model.assetCategory.id,
              //"productCategoryId": self.model.productCategory.id,
              "fromStoreId": self.model.store.id,
              //"isConsignment" : self.model.isConsignment,
              "itemCode": search,
              //"itemName": '',
              //"vedId": self.model.vtype
            };

          return GenericService.serviceAction("POST", URI, data)
            .then(function (response) {
              $rootScope.stopLoader();
              if (!!response.data && response.data.length > 0) {
                self.model.batchNames = response.data;
                $rootScope.stopLoader();
                //console.log(self.model.patientNames.itemName);
                /*return $filter('filter')(self.model.batchNames, {
                  $: search
                });*/
                return self.model.batchNames;
              } else {
                $rootScope.stopLoader();
                return BatchNotFound(search);
              }
            }, function (err) {
              $rootScope.stopLoader();
              return BatchNotFound(search);
            });
        } else {
          if (!!self.model.batchNames && self.model.batchNames.length > 0 &&
            self.model.batchNames[0].itemFound != undefined && !self.model.batchNames[0].itemFound) {
            return BatchNotFound(search);
          } else {
            // return $filter('filter')(self.model.batchNames, {
            //   $: search
            // });
          
            return self.model.batchNames;
          }
        }
      }
  
      var BatchOnChange = function (item) {
        self.model.itemName = item.itemName;
        self.model.itemId = item.itemId;
        self.model.itemCode = item.itemCode
      }
  
      var BatchNotFound = function (search) {
        var item = {
          "itemFound": false,
          "details": "Batch Not found with name " + search
        };
        self.model.batchNames = [item];
        return self.model.batchNames;
      }
      var ValidateDashboard = function () {
        var isValid = true;
        if (!self.model.store.id) {
          isValid = false;
          growl.warning('Please Select Store');
        }

        return isValid;
      }

      var clearDetail = function()
      {
        self.model.itemName = '';
      }


      initializeController();
    }
  
    function config($stateProvider) {
      $stateProvider.state("storeStockDashboard", {
        url: "/storeStockDashboard",
        templateUrl: 'views/PharmacyInventory/Store/store-stock-dashboard.html',
        controller: "StoreStockDashboard.Controller",
        controllerAs: "vm"
      });
    }
  
    angular
      .module("myApp")
      .config(config)
      .controller("StoreStockDashboard.Controller", storeStockDashboardController);
  })();