(function () {
  "use strict";

  function addNewItemRequestController($scope, $rootScope, $filter, $http, $state, CONSTANTS, GenericService, commonDetailService, StatusService, growl) {
    var self = this;

    //Default Constructor
    function initializeController() {
      $rootScope.loginpage = true;

      self.model = {
        isItemNameValid: [],
        isManufacturerValid: [],
        isVendorValid: [],
        isPriceValid: [],
        isReasonValid: [],
        items: [],
        vendorSearch: '',
        manufacturerSearch: '',
        prevSearch: '',
        mPrevSearch: '',
        vendorNames: [],
        manufacturerNames: []
      };
      
      StatusService.GetStatus().then(function(status) {
        self.model.statuses = status;
      });

      //Methods
      self.model.SavePurchaseRequest = SavePurchaseRequest;
      self.model.NavigateToItemRequest = NavigateToItemRequest;
      self.model.AddDuplicateRow = AddDuplicateRow;
      self.model.RemoveRowData = RemoveRowData;
      self.model.SearchVendorNames = SearchVendorNames;
      self.model.SearchManufacturer = SearchManufacturer;
      self.model.AddVendorName = AddVendorName;
      self.model.AddManufacturer = AddManufacturer;
      self.model.SetDecimal = SetDecimal;

      PopulateItems();
    }

    var setStatus = function (x){
      var status = self.model.statuses.find(function(obj){
        return obj.status == x;
      });
      
      return status.id;
    }

    var SearchVendorNames = function(search) {
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
        if (!!self.model.vendorNames && self.model.vendorNames.length > 0 && self.model.vendorNames[0].vendorFound != undefined && !self.model.vendorNames[0].vendorFound) {
          return nameNotFound(search);
        } else {
          return $filter('filter')(self.model.vendorNames, {
            $: search
          });
        }
      }
    }

    var nameNotFound = function(search) {
      var item = {
        "vendorFound": false,
        "detail": "Vendor Not Found with Name " + search,
        "search": search
      };
      self.model.vendorNames = [item];
      return self.model.vendorNames;
    }

    var AddVendorName = function (vendor, item) {
      if (!vendor.id) {
        item.vendorName = vendor.search;
        item.vendorId = undefined
      } else {
        item.vendorName = vendor.vendorName;
        item.vendorId = vendor.id;
      }
    }

    var SearchManufacturer = function(search) {
      if (search.length < 3) return;
      if ((!self.model.mPrevSearch || self.model.mPrevSearch != search) && (search.length == 3)) {
        self.model.mPrevSearch = search;
        var URI = CONSTANTS.GLOBAL.MANUFACTURER_SEARCH_API + search;

        $rootScope.startLoader();
        return GenericService.serviceAction("GET", URI, {})
        .then(function (response) {
          if (!!response.data && response.data.length > 0) {
            self.model.manufacturerNames = response.data;
            $rootScope.stopLoader();
            return $filter('filter')(self.model.manufacturerNames, {
              $: search
            });
          } else {
            $rootScope.stopLoader();
            return manufacturerNotFound(search);
          }
        }, function (err) {
          $rootScope.stopLoader();
          return manufacturerNotFound(search);
        });
      } else {
        if (!!self.model.manufacturerNames && self.model.manufacturerNames.length > 0 && self.model.manufacturerNames[0].manufacturerFound != undefined && !self.model.manufacturerNames[0].manufacturerFound) {
          return manufacturerNotFound(search);
        } else {
          return $filter('filter')(self.model.manufacturerNames, {
            $: search
          });
        }
      }
    }

    var manufacturerNotFound = function (search) {
      var item = {
        "manufacturerFound": false,
        "detail": "Manufacturer Not Found with Name " + search,
        "search": search
      };
      self.model.manufacturerNames = [item];
      return self.model.manufacturerNames;
    }

    var AddManufacturer = function (manufacture, item) {
      if (!manufacture.id) {
        item.manufacturer = manufacture.search;
        item.manufacturerId = undefined;
      } else {
        item.manufacturer = manufacture.manufacturerName;
        item.manufacturerId = manufacture.id;
      }
    }
    
    var GetEmptyData = function (index){
      return {
        itemName: '',
        manufacturer: '',
        vendorName: '',
        expectedPrice: '',
        reasonForItemCreation: '',
        status: ''
      }
    }
      
    var AddDuplicateRow = function (index){
      self.model.items.splice(index+1, 0, GetEmptyData(index));
    }

    var RemoveRowData = function (index){
      var items = self.model.items;
      self.model.items = items.slice(0, index).concat(items.slice(index+1))
    }

    var SetDecimal = function (item) {
      if (isNaN(item.expectedPrice) || !item.expectedPrice || item.expectedPrice == 0) {
        item.expectedPrice = 0;
      } else {
        item.expectedPrice = parseFloat(item.expectedPrice).toFixed(2);
      }
    }

    var PopulateItems = function () {
      try {
        $rootScope.startLoader();
        var itemId = commonDetailService.getDataId();

        $rootScope.stopLoader();
        AddDuplicateRow(0);
        if (!!itemId) {
          commonDetailService.setDataId(null);
          GetItemRequest(itemId);
        }
      } catch (error) {
        alert(error);
      }
    }

    var GetItemRequest = function (itemId) {
      var URI = CONSTANTS.PROCUREMENT.PURCHASE.ITEM_REQUEST.DETAILS_API + itemId;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          self.model.itemId = response.data.id;
          self.model.assetType = response.data.assetTypeId;
          self.model.items = [];

          angular.forEach(response.data.items, function (item) {
            if (!item.manufacturer) {
              item.manufacturer = item.manufactureTemp;
            }
            if (!item.vendor) {
              item.vendor = item.vendorTemp;
            }

            var indentItem = {
              "id": item.id,
              "itemName": item.itemName,
              "manufacturer": item.manufacturer,
              "vendorName": item.vendor,
              "vendorId": item.vendorId,
              "manufacturerId": item.manufacturerId,
              "expectedPrice": item.expectedPrice,
              "reasonForItemCreation": item.itemCreationReason,
              "statusId": item.statusId
            }
            self.model.items.push(indentItem);

          });

          $rootScope.stopLoader();
        },
        function (err) {
          $rootScope.stopLoader();
          growl.error(err.data.message);
        });
    }

    var ValidateItems = function (){
      var isValidItem = true;

      if (self.model.items.length <= 0) {
        isValidItem = false;
      }else {
        if (!self.model.assetType) {
          isValidItem = false;
          self.model.isAssetTypeValid = true;
        }

        for (var i = 0; i < self.model.items.length; i++) {
          if(!self.model.items[i].itemName){
            self.model.isItemNameValid[i] = true;
            isValidItem = false;
          }

          if (!self.model.items[i].manufacturer) {
            self.model.isManufacturerValid[i] = true;
            isValidItem = false;
          }

          if(!self.model.items[i].vendorName){
            self.model.isVendorValid[i] = true;
            isValidItem = false;
          }

          if (!self.model.items[i].expectedPrice) {
            self.model.isPriceValid[i] = true;
            isValidItem = false;
          }

          if (!self.model.items[i].reasonForItemCreation) {
            self.model.isReasonValid[i] = true;
            isValidItem = false;
          }

          if (!self.model.items[i].expectedPrice || !self.model.items[i].reasonForItemCreation || !self.model.items[i].vendorName || !self.model.items[i].manufacturer || !self.model.items[i].itemName) {
            growl.error('Fill All Data');
          }
        }
      }

      return isValidItem;
    }

    var SavePurchaseRequest = function () {
      if (ValidateItems()) {
        if (!self.model.itemId) {
          CreateItemRequest();
        } 
        else {
          UpdateItemRequest();
        }
      }
    }

    var CreateItemRequest = function () {
      var URI = CONSTANTS.PROCUREMENT.PURCHASE.ITEM_REQUEST.SAVE_API;

      var data = {
        // "requestNumber": "REQ-001",
        // "requestDate": moment(new Date()).format('YYYY-MM-DD'),
        "statusId": setStatus('New'),
        "approvalStatusId": setStatus('New'),
        "assetTypeId": self.model.assetType,
        "items": []
      };

      angular.forEach(self.model.items, function (item, key) {
        var requestItem = {
          "itemName": item.itemName,
          "manufacturerId": item.manufacturerId,
          "vendorId": item.vendorId,
          "expectedPrice": item.expectedPrice,
          "itemCreationReason": item.reasonForItemCreation,
          "manufactureTemp": item.manufacturer,
          "vendorTemp": item.vendorName,
          "statusId": setStatus('New'),
        }

        data.items.push(requestItem);
      });

      $rootScope.startLoader();
      GenericService.serviceAction("POST", URI, data).then(
        function (response) {
          $rootScope.stopLoader();
          growl.success(response.data.message);
          NavigateToItemRequest();
        },
        function (err) {
          $rootScope.stopLoader();
          growl.error(err.data.message);
        });
    }

    var UpdateItemRequest = function () {
      var URI = CONSTANTS.PROCUREMENT.PURCHASE.ITEM_REQUEST.SAVE_API + self.model.itemId;

      var data = {
        // "requestNumber": "REQ-001",
        // "requestDate": moment(new Date()).format('YYYY-MM-DD'),
        // "assetTypeId": self.model.assetType,
        // "statusId": setStatus('New'),
        "items": []
      };

      angular.forEach(self.model.items, function (item) {
        var requestItem = {
          "id": item.id,
          "itemName": item.itemName,
          "expectedPrice": item.expectedPrice,
          "itemCreationReason": item.reasonForItemCreation,
          "manufacturerId": item.manufacturerId ? item.manufacturerId : undefined,
          "vendorId": item.vendorId ? item.vendorId : undefined,
          "manufactureTemp": item.manufacturer ? item.manufacturer : undefined,
          "vendorTemp": item.vendorName ? item.vendorName : undefined,
          "statusId": setStatus('New')
        }

        data.items.push(requestItem);
      });

      $rootScope.startLoader();
      GenericService.serviceAction("PATCH", URI, data).then(
        function (response) {
          $rootScope.stopLoader();
          growl.success(response.data.message);
          NavigateToItemRequest();
        },
        function (err) {
          $rootScope.stopLoader();
          growl.error(err.data.message);
        });
    }

    var NavigateToItemRequest = function () {
      $state.go('itemRequest');
    }

    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("addNewitemRequest", {
      url: "/addNewitemRequest",
      templateUrl: 'views/procurement/purchase/add-item-request-purchase.html',
      controller: "AddNewItemRequest.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("AddNewItemRequest.Controller", addNewItemRequestController);
})();