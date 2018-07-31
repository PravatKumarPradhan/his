(function () {
  "use strict";

  function consumableBarcodeConfigController($scope, $rootScope, $http, CONSTANTS, GenericService, growl) {
    var self = this;
    //Default Constructor
    function initializeController() {
      $rootScope.loginpage = true;
      self.model = {
        taxList: [{
          "component": "Select Tax Component Type"
        }]
      };

      //Methods

      // self.model.PopulateGenericName = PopulateGenericName;
    //  self.model.ToggleStatus = ToggleStatus;
      self.model.isItemNameFlag = isItemNameFlag;
      self.model.isItemCodeFlag = isItemCodeFlag;
      self.model.isBatchNoFlag = isBatchNoFlag;
      self.model.isExpiryDateFlag = isExpiryDateFlag;
      self.model.isLotNoFlag = isLotNoFlag;
      self.model.isMRPFlag = isMRPFlag;
      self.model.isSalesPriceFlag = isSalesPriceFlag;
      self.model.isGrnNoFlag = isGrnNoFlag;
      self.model.isVendorName = isVendorName;
      self.model.isVendorCodeFlag = isVendorCodeFlag;
      self.model.isHopspitalNameFlag = isHopspitalNameFlag;
      self.model.SaveConsumableBarcodeConfig = SaveConsumableBarcodeConfig;
      self.model.CreateConsumableConfig = CreateConsumableConfig;
      self.model.isItemName = "false";
      self.model.isItemCode = "false";
      self.model.isBatchNo = "false";
      self.model.isExpiryDate = "false";
      self.model.isLotNo = "false";
      self.model.isMRP = "false";
      self.model.isSalesPrice = "false";
      self.model.isGrnNo = "false";
      self.model.isVendorName = "false";
      self.model.isVendorCode = "false";
      self.model.isHopspitalName = "false";

    }


    var isItemNameFlag = function (flag) {
        if (flag) {
            self.model.isItemName = true;
        } else {
            self.model.isItemName = false;
        }
    }
    var isItemCodeFlag = function (flag) {
        if (flag) {
            self.model.isItemCode = true;
        } else {
            self.model.isItemCode = false;
        }
    }
    var isBatchNoFlag = function (flag) {
        if (flag) {
            self.model.isBatchNo = true;
        } else {
            self.model.isBatchNo = false;
        }
    }
    var isExpiryDateFlag = function (flag) {
        if (flag) {
            self.model.isExpiryDate = true;
        } else {
            self.model.isExpiryDate = false;
        }
    }
    var isLotNoFlag = function (flag) {
        if (flag) {
            self.model.isLotNo = true;
        } else {
            self.model.isLotNo = false;
        }
    }
    var isMRPFlag = function (flag) {
        if (flag) {
            self.model.isMRP = true;
        } else {
            self.model.isMRP = false;
        }
    }
    var isSalesPriceFlag = function (flag) {
        if (flag) {
            self.model.isSalesPrice = true;
        } else {
            self.model.isSalesPrice = false;
        }
    }
    var isGrnNoFlag = function (flag) {
        if (flag) {
            self.model.isGrnNo = true;
        } else {
            self.model.isGrnNo = false;
        }
    }
    var isVendorName = function (flag) {
        if (flag) {
            self.model.isVendorName = true;
        } else {
            self.model.isVendorName = false;
        }
    }
    var isVendorCodeFlag = function (flag) {
        if (flag) {
            self.model.isVendorCode = true;
        } else {
            self.model.isVendorCode = false;
        }
    }
    var isHopspitalNameFlag = function (flag) {
        if (flag) {
            self.model.isHopspitalName = true;
        } else {
            self.model.isHopspitalName = false;
        }
    }

    //Method to add the Generic
    var CreateConsumableConfig = function () {
      var URI = CONSTANTS.MASTER.UNIT.CONSUMABLE_BARCODE_CONFIG.CONSUMABLE_BARCODE_CONFIG_SAVE_API;

      var data = {
        "itemName": self.model.isItemName,
        "itemCode": self.model.isItemCode,
        "batchNo": self.model.isBatchNo,
        "expiryDate": self.model.isExpiryDate,
        "lotNo": self.model.isLotNo,
        "mrp": self.model.isMRP,
        "salePrice": self.model.isSalesPrice,
        "grnNo": self.model.isGrnNo,
        "vendorName": self.model.isVendorName,
        "vendorCode": self.model.isVendorCode,
        "hospitalName": self.model.isHopspitalName,
      }

      $rootScope.startLoader();
      GenericService.serviceAction("POST", URI, data).then(
        function (response) {
          var consumableBarcodeConfigData = {
            "id": response.data.id,
          };
          self.model.consumableBarcodeConfigId =  response.data.id,
        // self.model.consumableBarcodeConfigList.push(consumableBarcodeConfigData);
        //  ClearFields();

          $rootScope.stopLoader();
          growl.success('Record saved successfully');
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
          growl.error('Something went wrong');
        });
    };

    //Method to add or update the Generic
    var SaveConsumableBarcodeConfig = function () {
        debugger
        if (!self.model.consumableBarcodeConfigId) {
            CreateConsumableConfig();
        } else {
          UpdateConsumableConfig();
        }
    };
    //Method to update the  Generic
    var UpdateConsumableConfig = function () {
        var URI = CONSTANTS.MASTER.UNIT.CONSUMABLE_BARCODE_CONFIG.CONSUMABLE_BARCODE_CONFIG_UPDATE_API + self.model.consumableBarcodeConfigId;

      var data = {
        "itemName": self.model.isItemName,
        "itemCode": self.model.isItemCode,
        "batchNo": self.model.isBatchNo,
        "expiryDate": self.model.isExpiryDate,
        "lotNo": self.model.isLotNo,
        "mrp": self.model.isMRP,
        "salePrice": self.model.isSalesPrice,
        "grnNo": self.model.isGrnNo,
        "vendorName": self.model.isVendorName,
        "vendorCode": self.model.isVendorCode,
        "hospitalName": self.model.isHopspitalName,
      }
      // $rootScope.startLoader();
      GenericService.serviceAction("PATCH", URI, data).then(
        function (response) {
          $rootScope.stopLoader();
          growl.success(response.data.message);
        });
    };

    initializeController();
  }


  function config($stateProvider) {
    $stateProvider.state("consumableBarcodeConfig", {
      url: "/consumableBarcodeConfig",
      templateUrl: 'views/master/UnitMaster/assetManagement/consumable-barcode-configuration.html',
      controller: "ConsumableBarcodeConfig.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("ConsumableBarcodeConfig.Controller", consumableBarcodeConfigController);
})();