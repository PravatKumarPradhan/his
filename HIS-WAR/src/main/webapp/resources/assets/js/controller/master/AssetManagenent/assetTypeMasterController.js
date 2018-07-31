(function () {
  "use strict";

  function assetTypeMasterController($scope, $rootScope, $http, constants, GenericService, growl) {
    var self = this;
    var apiUrl = constants.apiurl + constants.unitsApi;

    //Default Constructor
    function initializeController() {
      $rootScope.loginpage = true;

      self.model = {};

      //Methods
      self.model.GetAssetType = GetAssetType;
      self.model.ToggleStatus = ToggleStatus;
      self.model.SaveAssetType = SaveAssetType;
      self.model.ClearFields = ClearFields;
      self.model.Popup = Popup;

      GetAssetTypeList();
    }

    //Method to get the list of Asset Type
    var GetAssetTypeList = function () {
      var URI = apiUrl + '/assets/types';

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          self.model.assetTypeList = response.data;
          $rootScope.stopLoader();
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
        });
    }

    //Method to populate the data
    var GetAssetType = function (typeId) {
      
      var URI = apiUrl + '/assets/types/' + typeId;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          self.model.assetTypeId = response.data.id;
          self.model.assetTypeCode = response.data.code;
          self.model.assetTypeName = response.data.type;
        });
        $rootScope.stopLoader();
    }

    //Method to activate or deactivate Asset Type
    var ToggleStatus = function (assetType) {
      var URI = apiUrl + '/assets/types/' + assetType.id;

      var data = {
        "status": assetType.status
      };

      $rootScope.startLoader();
      GenericService.serviceAction("PATCH", URI, data).then(
        function (response) {
         $rootScope.stopLoader();
         growl.success('Status changed successfully', {title: response.status});
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
          growl.error('Something went wrong', {title: response.status});
        });
    }
    
    //Method for popup
    var Popup = function (){
      var popup = angular.element('#confirmSave');
        popup.modal('show');
    }

    //Method to add or update the Asset Type
    var SaveAssetType = function () {
      if (Validate()) {
        if (!self.model.assetTypeId) {
          CreateAssetType();
        } else {
          UpdateAssetType();
        }
      }
    };

    //Method to add the Asset Type
    var CreateAssetType = function () {
      var URI = apiUrl + '/assets/types';

      var data = {
        "code": self.model.assetTypeCode,
        "type": self.model.assetTypeName,
        "status": true
      }

      $rootScope.startLoader();
      GenericService.serviceAction("POST", URI, data).then(
        function (response) {
          var assetType = {
            "id": response.data.id,
            "code": self.model.assetTypeCode,
            "type": self.model.assetTypeName,
          };
          self.model.assetTypeList.push(assetType);
          ClearFields();

          $rootScope.stopLoader();
          growl.success('Record saved successfully', {title: response.status});
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
          growl.error('Something went wrong', {title: response.status});
        });
    };

    //Method to update the Asset Type
    var UpdateAssetType = function () {
      var URI = apiUrl + '/assets/types/' + self.model.assetTypeId;

      var data = {
        "code": self.model.assetTypeCode,
        "type": self.model.assetTypeName
      }

      $rootScope.startLoader();
      GenericService.serviceAction("PATCH", URI, data).then(
        function (response) {
          var assetType = self.model.assetTypeList.find(function (type) {
            return type.id == self.model.assetTypeId;
          });

          assetType.code = data.code;
          assetType.type = data.type;
          assetType.status = data.status;
          self.model.assetTypeId = undefined;

          ClearFields();
          $rootScope.stopLoader();
          growl.success('Record updated successfully', {title: response.status});
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
          growl.error('Something went wrong', {title: response.status});
        });
        
    }

    //Method to clear the fileds
    var ClearFields = function () {
      self.model.assetTypeCode = undefined;
      self.model.assetTypeName = undefined;
    }

    //Method to Validate the fileds
    var Validate = function () {
      var isValid = true;

      if (!self.model.assetTypeCode) {
        isValid = false;
        self.model.isAssetTypeCodeValid = true;
        
      }
      if (!self.model.assetTypeName) {
        isValid = false;
        self.model.isAssetTypeNameValid = true;
      }

      return isValid;
    }

    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("assetTypeMaster", {
      url: "/assetTypeMaster",
      templateUrl: "views/master/GlobalMaster/AssetManagenent/Master/asset-type-master.html",
      controller: "AssetTypeMaster.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("AssetTypeMaster.Controller", assetTypeMasterController)
})();