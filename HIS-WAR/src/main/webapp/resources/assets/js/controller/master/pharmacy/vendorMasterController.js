(function () {
  "use strict";
  function vendorMasterController($scope, $rootScope, $http, CONSTANTS, GenericService, growl) {
    var self = this;
    // var apiUrl = constants.apiurl + constants.unitsApi;
    //Default Constructor
    function initializeController() {
      $rootScope.loginpage = true;

      self.model = {
        isLocalPurchase: false,
        Manufactures: [{
          "name": "Select Manufacture"
        }],
        modeOfPayments: [{
          "paymentModeDesc": "Select Mode of Payment"
        }],
        Countries: [{
          "countryName": "Select Country"
        }],
        States: [{
          "stateName": "Select State"
        }],
        Cities: [{
          "cityName": "Select City"
        }],
        Areas: [{
          "areaName": "Select Area"
        }],
      };

      self.model.ToggleStatus = ToggleStatus;
      self.model.PopulateVendorMaster = PopulateVendorMaster;
      self.model.ValidateVendor = ValidateVendor;
      self.model.SaveVendorMaster = SaveVendorMaster;
      self.model.PopulateStates = PopulateStates;
      self.model.PopulateCities = PopulateCities;
      self.model.PopulateAreas = PopulateAreas;
      self.model.ClearFields = clearFields;
      self.model.getAddressDetails = getAddressDetails;
      self.model.detail_address = {
        "address": "",
        "country": "",
        "state": "",
        "city": "",
        "area": "",
        "zipcode": ""
      };

      getVendorMasterList();
      PopulateManufacture();
      PopulateCountry();
      PopulateModeOfPayment();
    }

    //Method to get the list of Vendors
    var getVendorMasterList = function () {

      var URI = CONSTANTS.MASTER.UNIT.PHARAMACY.VENDOR_MASTER.VENDOR_MASTER_API;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          self.model.vendorMasterDataList = response.data;
          $rootScope.stopLoader();
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
        });
    }

    //Method to Populate Manufacture Dropdown
    var PopulateManufacture = function () {
      var vendorMaster = self.model;

      var URI = CONSTANTS.MASTER.GLOBAL.PHARAMACY.MANUFACTURER_MASTER.MANUFATURER_API;

      vendorMaster.manufactureName = vendorMaster.Manufactures[0];

      vendorMaster.Manufactures.splice(1);

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          vendorMaster.Manufactures = vendorMaster.Manufactures.concat(response.data);
          $rootScope.stopLoader();
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
        });
    }

    //Getting Country List
    function PopulateCountry() {
      var vendorMaster = self.model;

      var URI = CONSTANTS.GLOBAL.COUNTRY_API;

      vendorMaster.countryName = vendorMaster.Countries[0];
      vendorMaster.stateName = vendorMaster.States[0];
      vendorMaster.cityName = vendorMaster.Cities[0];
      vendorMaster.areaName = vendorMaster.Areas[0];

      vendorMaster.Countries.splice(1);
      vendorMaster.States.splice(1);
      vendorMaster.Cities.splice(1);
      vendorMaster.Areas.splice(1);

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          vendorMaster.Countries = vendorMaster.Countries.concat(response.data);
          $rootScope.stopLoader();
        });
    };

    //Getting  State List
    function PopulateStates(country, selectedvalue) {
      if (country.countryId == undefined) {
        country.countryId = 0;
      }
      var vendorMaster = self.model;

      var URI = CONSTANTS.GLOBAL.STATE_API + country.countryId;

      vendorMaster.stateName = vendorMaster.States[0];
      vendorMaster.cityName = vendorMaster.Cities[0];
      vendorMaster.areaName = vendorMaster.Areas[0];

      vendorMaster.States.splice(1);
      vendorMaster.Cities.splice(1);
      vendorMaster.Areas.splice(1);

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          vendorMaster.States = vendorMaster.States.concat(response.data);
          $rootScope.stopLoader();

          if (selectedvalue) {
            var State = vendorMaster.States.find(function (state) {
              return country.stateId == state.stateId;
            });
            self.model.stateName = State;
          }
        });
    };

    //Getting City List
    function PopulateCities(state, selectedvalue) {
      if (state.stateId == undefined) {
        state.stateId = 0;
      }
      var vendorMaster = self.model;

      var URI = CONSTANTS.GLOBAL.CITY_API + state.stateId;

      vendorMaster.cityName = vendorMaster.Cities[0];
      vendorMaster.areaName = vendorMaster.Areas[0];

      vendorMaster.Cities.splice(1);
      vendorMaster.Areas.splice(1);

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          vendorMaster.Cities = vendorMaster.Cities.concat(response.data);
          $rootScope.stopLoader();

          if (selectedvalue) {
            var City = vendorMaster.Cities.find(function (city) {
              return state.cityId == city.cityId;
            });
            self.model.cityName = City;
          }
        });
    };

    //Getting City List
    function PopulateAreas(city, selectedvalue) {
      if (city.cityId == undefined) {
        city.cityId = 0;
      }
      var vendorMaster = self.model;

      var URI = CONSTANTS.GLOBAL.AREA_API + city.cityId;

      vendorMaster.areaName = vendorMaster.Areas[0];

      vendorMaster.Areas.splice(1);

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          vendorMaster.Areas = vendorMaster.Areas.concat(response.data);
          $rootScope.stopLoader();

          if (selectedvalue) {
            var Area = vendorMaster.Areas.find(function (area) {
              return city.areaId == area.areaId;
            });
            self.model.areaName = Area;
          }
        });
    };

    //Getting Mode Of Payment List
    function PopulateModeOfPayment() {
      var vendorMaster = self.model;

      var URI = CONSTANTS.GLOBAL.PAYMENT_MODE_API;

      vendorMaster.modeOfPayment = vendorMaster.modeOfPayments[0];
      vendorMaster.modeOfPayments.splice(1);

      var data = {};
      GenericService.serviceAction("GET", URI, data).then(
        function (response) {
          vendorMaster.modeOfPayments = vendorMaster.modeOfPayments.concat(response.data);;
        });
    };

    //Method to populate VendorMaster for editing
    function findInRecordInArray(array, key, value) {
      for (var i = 0; i < array.length; i++) {
        if (array[i][key] == value) {
          return array[i];
        }
      }
      return null;
    }

    function getVendorMasterById(id) {
      return findInRecordInArray(self.model.vendorMasterDataList, 'id', id)
    }

    function PopulateVendorMaster(id) {
      self.model.isManufacturerValid = false;
      self.model.isvendorCodeValid = false;
      self.model.isvendorNameValid = false;
      self.model.iscontactPersonNameValid = false;
      self.model.isdrugLiscenceNoValid = false;
      self.model.isgstNoValid = false;
      self.model.isaddressValid = false;
      self.model.iszipCodeValid = false;
      self.model.isphoneNumberValid = false;
      self.model.isemailValid = false;
      self.model.ismodofPaymentValid = false;
      self.model.ispaymentTermsValid = false;
      self.model.iscountryNameValid = false;
      self.model.isstateNameValid = false;
      self.model.iscityNameValid = false;
      self.model.isareaNameValid = false;

      self.model.activeVendorMasterId = id;
      var URI = CONSTANTS.MASTER.UNIT.PHARAMACY.VENDOR_MASTER.VENDOR_MASTER_API + id;
      var data = {};
      GenericService.serviceAction("GET", URI, data).then(
        function (response) {
          var vendor = getVendorMasterById(id);
          vendor = response.data;
          var mnufacturerName = self.model.Manufactures.find(function (manufacturer) {
            return response.data.manufacturerId == manufacturer.id;
          });
          var paymentMode = self.model.modeOfPayments.find(function (paymentmode) {
            return response.data.paymentModeId == paymentmode.paymentModeId;
          });
          var Country = self.model.Countries.find(function (country) {
            return response.data.countryId == country.countryId;
          });
          PopulateStates(response.data, true);
          PopulateCities(response.data, true);
          PopulateAreas(response.data, true);

          self.model.id = response.data.id;
          self.model.vendorCode = response.data.code;
          self.model.vendorName = response.data.name;
          self.model.address = response.data.address;
          self.model.contactPersonName = response.data.contactPersonName;
          self.model.drugLicenseNo = response.data.drugLicenseNo;
          self.model.emailAddress = response.data.emailId;
          self.model.gstNo = response.data.gstNo;
          self.model.paymentTerms = response.data.paymentTerm;
          self.model.phoneNumber = response.data.phoneNumber;
          self.model.manufactureName = mnufacturerName;
          self.model.modeOfPayment = paymentMode;
          self.model.status = response.data.status;
          self.model.zipCode = response.data.zipcode;
          self.model.isLocalPurchase = response.data.isLocalPurchase;
          self.model.countryName = Country;
          self.model.isEdit = true;
        });
    };

    //Method to activate or deactivate Main Store
    var ToggleStatus = function (vendorMaster) {
      var URI = CONSTANTS.MASTER.UNIT.PHARAMACY.VENDOR_MASTER.VENDOR_MASTER_API + vendorMaster.id;
      var data = {
        "status": vendorMaster.status
      };
      GenericService.serviceAction("PATCH", URI, data).then(
        function (response) {
          $rootScope.stopLoader();
          growl.success('Status changed successfully', { title: response.status });
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
          mainStore.status = !mainStore.status;
          growl.error('Something went wrong', { title: response.status });
        });
    }

    //Method to add or update the VendorMaster
    function SaveVendorMaster() {
      if (ValidateVendor()) {
        if (!self.model.id) {
          createVendorMaster();
        } else {
          updateVendorMaster();
        }
      }
    };

    //Method to add the VendorMaster
    function createVendorMaster() {
      var URI = CONSTANTS.MASTER.UNIT.PHARAMACY.VENDOR_MASTER.VENDOR_MASTER_API;
      var data = {
        "code": self.model.vendorCode,
        "name": self.model.vendorName,
        "address": self.model.address,
        "countryId": self.model.countryName.countryId,
        "stateId": self.model.stateName.stateId,
        "cityId": self.model.cityName.cityId,
        "areaId": self.model.areaName.areaId,
        "zipcode": self.model.zipCode,
        "contactPersonName": self.model.contactPersonName,
        "drugLicenseNo": self.model.drugLicenseNo,
        "manufacturerId": self.model.manufactureName.id,
        "paymentModeId": self.model.modeOfPayment.paymentModeId,
        "gstNo": self.model.gstNo,
        "phoneNumber": self.model.phoneNumber,
        "emailId": self.model.emailAddress,
        "paymentTerm": self.model.paymentTerms,
        "isLocalPurchase": self.model.isLocalPurchase,
        "status": true
      }
      GenericService.serviceAction("POST", URI, data).then(
        function (response) {

          var manufacturerName = self.model.Manufactures.find(function (manufactures) {
            return manufactures.id == self.model.manufactureName.id;
          });
          var paymentModes = self.model.modeOfPayments.find(function (paymentMode) {
            return paymentMode.paymentModeId == self.model.modeOfPayment.paymentModeId;
          });
          var countryName = self.model.Countries.find(function (country) {
            return country.countryId == self.model.countryName.countryId;
          });
          var stateName = self.model.States.find(function (state) {
            return state.stateId == self.model.stateName.stateId;
          });
          var cityName = self.model.Cities.find(function (city) {
            return city.cityId == self.model.cityName.cityId;
          });
          var areaName = self.model.Areas.find(function (area) {
            return area.areaId == self.model.areaName.areaId;
          });
          var insertedData = {
            "manufacturerName": manufacturerName.name,
            "name": self.model.vendorName,
            "code": self.model.vendorCode,
            "contactPersonName": self.model.contactPersonName,
            "drugLicenseNo": self.model.drugLicenseNo,
            "gstNo": self.model.gstNo,
            "address": self.model.address,
            "countryName": countryName.countryName,
            "stateName": stateName.stateName,
            "cityName": cityName.cityName,
            "areaName": areaName.areaName,
            "zipCode": self.model.zipCode,
            "phoneNumber": self.model.phoneNumber,
            "emailId": self.model.emailAddress,
            "paymentModeDesc": paymentModes.paymentModeDesc,
            "paymentTerm": self.model.paymentTerms,
            "isLocalPurchase": self.model.isLocalPurchase,
            "status": true
          };
          self.model.vendorMasterDataList.push(insertedData);
          clearFields();
        }, function errorCallback(response) {
          console.log(response.status);
          console.log(response.data || 'Request Failed');
        });
    };

    //Method to update the  VendorMaster
    function updateVendorMaster() {

      var URI = CONSTANTS.MASTER.UNIT.PHARAMACY.VENDOR_MASTER.VENDOR_MASTER_API + self.model.id;
      var data = {
        "code": self.model.vendorCode,
        "name": self.model.vendorName,
        "address": self.model.address,
        "countryId": self.model.countryName.countryId,
        "stateId": self.model.stateName.stateId,
        "cityId": self.model.cityName.cityId,
        "areaId": self.model.areaName.areaId,
        "zipcode": self.model.zipCode,
        "contactPersonName": self.model.contactPersonName,
        "drugLicenseNo": self.model.drugLicenseNo,
        "manufacturerId": self.model.manufactureName.id,
        "paymentModeId": self.model.modeOfPayment.paymentModeId,
        "gstNo": self.model.gstNo,
        "phoneNumber": self.model.phoneNumber,
        "emailId": self.model.emailAddress,
        "paymentTerm": self.model.paymentTerms,
        "isLocalPurchase": self.model.isLocalPurchase,
      }
      GenericService.serviceAction("PATCH", URI, data).then(
        function (response) {
          var manufacturerName = self.model.Manufactures.find(function (manufactures) {
            return manufactures.id == self.model.manufactureName.id;
          });
          var paymentModes = self.model.modeOfPayments.find(function (paymentMode) {
            return paymentMode.paymentModeId == self.model.modeOfPayment.paymentModeId;
          });
          var countryName = self.model.Countries.find(function (country) {
            return country.countryId == self.model.countryName.countryId;
          });
          var stateName = self.model.States.find(function (state) {
            return state.stateId == self.model.stateName.stateId;
          });
          var cityName = self.model.Cities.find(function (city) {
            return city.cityId == self.model.cityName.cityId;
          });
          var areaName = self.model.Areas.find(function (area) {
            return area.areaId == self.model.areaName.areaId;
          });
          var vendorMaster = self.model.vendorMasterDataList.find(function (vendor) {
            return vendor.id == self.model.id;
          });
          vendorMaster.manufacturerName = manufacturerName.name;
          vendorMaster.name = data.name;
          vendorMaster.contactPersonName = data.contactPersonName;
          vendorMaster.code = data.code;
          vendorMaster.drugLicenseNo = data.drugLicenseNo;
          vendorMaster.gstNo = data.gstNo;
          vendorMaster.address = data.address;
          vendorMaster.country = countryName.countryName;
          vendorMaster.state = stateName.stateName;
          vendorMaster.city = cityName.cityName;
          vendorMaster.area = areaName.areaName;
          vendorMaster.zipcode = data.zipcode;
          vendorMaster.phoneNumber = data.phoneNumber;
          vendorMaster.emailId = data.emailId;
          vendorMaster.paymentModeDesc = paymentModes.paymentModeDesc;
          vendorMaster.paymentTerm = data.paymentTerm;
          vendorMaster.isLocalPurchase = data.isLocalPurchase;
          clearFields();
          self.model.activeVendorMasterId = id;
          self.model.isEdit = false;
        });
    };

    //Method to clear the fileds
    function clearFields() {
      self.model.vendorId = 0;
      self.model.manufactureName = self.model.Manufactures[0];
      self.model.countryName = self.model.Countries[0];
      self.model.stateName = self.model.States[0];
      self.model.States.splice(1);
      self.model.cityName = self.model.Cities[0];
      self.model.Cities.splice(1);
      self.model.areaName = self.model.Areas[0];
      self.model.Areas.splice(1);
      self.model.modeOfPayment = self.model.modeOfPayments[0];
      self.model.vendorName = "";
      self.model.vendorCode = "";
      self.model.contactPersonName = "";
      self.model.drugLicenseNo = "";
      self.model.gstNo = "";
      self.model.address = "";
      self.model.countryId = "";
      self.model.stateId = "";
      self.model.cityId = "";
      self.model.areaId = "";
      self.model.zipCode = "";
      self.model.phoneNumber = "";
      self.model.emailAddress = "";
      self.model.paymentModeId = "";
      self.model.paymentTerms = "";
      self.model.isLocalPurchase = false;
    }

    function getAddressDetails(vendorMaster) {
      self.model.detail_address.address = vendorMaster.address;
      self.model.detail_address.country = vendorMaster.country;
      self.model.detail_address.state = vendorMaster.state;
      self.model.detail_address.city = vendorMaster.city;
      self.model.detail_address.area = vendorMaster.area;
      self.model.detail_address.zipcode = vendorMaster.zipcode;
    }

    var ValidateVendor = function () {
      var isValid = true;
      if (!self.model.manufactureName.id) {
        isValid = false;
        self.model.isManufacturerValid = true;
      }
      if (!self.model.vendorCode) {
        isValid = false;
        self.model.isvendorCodeValid = true;
      }
      if (!self.model.vendorName) {
        isValid = false;
        self.model.isvendorNameValid = true;
      }
      if (!self.model.contactPersonName) {
        isValid = false;
        self.model.iscontactPersonNameValid = true;
      }
      if (!self.model.drugLicenseNo) {
        isValid = false;
        self.model.isdrugLiscenceNoValid = true;
      }
      if (!self.model.gstNo) {
        isValid = false;
        self.model.isgstNoValid = true;
      }
      if (!self.model.address) {
        isValid = false;
        self.model.isaddressValid = true;
      }
      if (!self.model.countryName.countryId) {
        isValid = false;
        self.model.iscountryNameValid = true;
      }
      if (!self.model.stateName.stateId) {
        isValid = false;
        self.model.isstateNameValid = true;
      }
      if (!self.model.cityName.cityId) {
        isValid = false;
        self.model.iscityNameValid = true;
      }
      if (!self.model.areaName.areaId) {
        isValid = false;
        self.model.isareaNameValid = true;
      }
      if (!self.model.zipCode) {
        isValid = false;
        self.model.iszipCodeValid = true;
      }
      if (!self.model.phoneNumber) {
        isValid = false;
        self.model.isphoneNumberValid = true;
      }
      if (!self.model.emailAddress) {
        isValid = false;
        self.model.isemailValid = true;
      }
      if (!self.model.modeOfPayment.paymentModeId) {
        isValid = false;
        self.model.ismodofPaymentValid = true;
      }
      if (!self.model.paymentTerms) {
        isValid = false;
        self.model.ispaymentTermsValid = true;
      }
      return isValid;
    }

    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("vendormaster", {
      url: "/vendormaster",
      templateUrl: "views/PharmacyInventory/StockAdjustment/vendor-master.html",
      controller: "VendorMaster.Controller",
      controllerAs: "vm",
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("VendorMaster.Controller", vendorMasterController)
    .directive('showFocus', function ($timeout) {
      return function (scope, element, attrs) {
        scope.$watch(attrs.showFocus,
          function (newValue, oldValue) {
            $timeout(function () {
              (newValue != oldValue) && element.focus();
            });
          }, true);
      };
    });
})();



