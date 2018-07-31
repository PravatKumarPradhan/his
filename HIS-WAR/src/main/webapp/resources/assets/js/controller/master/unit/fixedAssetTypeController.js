(function () {
    "use strict";
    function fixedAssetTypeMasterController($scope, $rootScope, $http, CONSTANTS, GenericService, growl) {
        var self = this;
        //Default Constructor
        function initializeController() {
            $rootScope.loginpage = true;
            self.model = {};
            //Methods 
            self.model.ToggleStatus = ToggleStatus;
            self.model.SaveFixedAssetType = SaveFixedAssetType;
            self.model.ClearFields = ClearFields;
            self.model.PopulateFixedAssetType = PopulateFixedAssetType;
            self.model.ValidateFixedAssetType = ValidateFixedAssetType;
            self.model.getFixedAssetType = getFixedAssetType;
            self.model.getFixedAssetTypeForEdit = getFixedAssetTypeForEdit;
            self.model.ValidateFixedAssetType = ValidateFixedAssetType;
            getFixedAssetType();
        }

        var SaveFixedAssetType = function () {
            if (ValidateFixedAssetType()) {
                if (!self.model.id) {
                    CreateFixedAssetType();
                } else {
                    updateFixedAssetType();
                }
            }
        }

        //Method to get the list of Fixed Asset Type
        var getFixedAssetType = function () {
            var URI = CONSTANTS.MASTER.UNIT.FIXED_ASSET.FIXED_ASSET_TYPE_MASTER_API;

            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    self.model.FixedAssetTypeList = response.data;
                    $rootScope.stopLoader();
                },
                function errorCallback(response) {
                    $rootScope.stopLoader();
                });
        }
        
        
    //Method to populate ProductCategory for editing
    var getFixedAssetTypeForEdit = function (fixedAssetTypeId) {
        debugger
        var URI = CONSTANTS.MASTER.UNIT.FIXED_ASSET.FIXED_ASSET_TYPE_MASTER_API + fixedAssetTypeId;
  
        $rootScope.startLoader();
        GenericService.serviceAction("GET", URI, {}).then(
          function (response) {
            self.model.fixedAssetTypeId = response.data.id;
            self.model.fixedAssetCode = response.data.fixedAssetCode;
            self.model.fixedAssetType = response.data.fixedAssetType;
            $rootScope.stopLoader();
          });
      };
        //Method to populate Fixed Asset Type for editing
        function findInRecordInArray(array, key, value) {
            for (var i = 0; i < array.length; i++) {
                if (array[i][key] == value) {
                    return array[i];
                }
            }
            return null;
        }

        function getFixedAssetTypeById(id) {
            return findInRecordInArray(self.model.FixedAssetTypeList, 'id', id)
        }

        function PopulateFixedAssetType(id) {
            self.model.isfixedAssetCodeValid = false;
            self.model.isfixedAssetTypeValid = false;
            self.model.activeFixedAssetTypeId = id;
            var URI = CONSTANTS.MASTER.UNIT.FIXED_ASSET_TYPE_MASTER.FIXED_ASSET_TYPE_DU_API + id;
            var data = {};
            GenericService.serviceAction("GET", URI, data).then(
                function (response) {
                    var fixedassettype = getFixedAssetTypeById(id);
                    fixedassettype = response.data;
                    self.model.fixedAssetCode = response.data.fixedAssetCode;
                    self.model.fixedAssetType = response.data.fixedAssetType;
                    self.model.status = response.data.status;
                    self.model.id = response.data.id;
                    self.model.isEdit = true;
                });
        };

        //Method to activate or deactivate Fixed Asset Type
        var ToggleStatus = function (fixedassettype) {
            var URI = CONSTANTS.MASTER.UNIT.FIXED_ASSET.FIXED_ASSET_TYPE_MASTER_API + fixedassettype.id;

            var data = {
                "status": fixedassettype.status
            };

            GenericService.serviceAction("PATCH", URI, data).then(
                function (response) {
                    $rootScope.stopLoader();
                    growl.success('Status changed successfully');
                },
                function errorCallback(response) {
                    $rootScope.stopLoader();
                    othercharge.status = !othercharge.status;
                    growl.error('Something went wrong');
                });
        }
  //Method to add or update the Generic
  var SaveFixedAssetType = function () {
    if (ValidateFixedAssetType()) {
      if (!self.model.fixedAssetTypeId) {
        CreateFixedAssetType();
      } else {
        updateFixedAssetType();
      }
    }
  };

  var ValidateFixedAssetType = function () {
    var isValid = true;

    if (!self.model.fixedAssetCode) {
      isValid = false;
      self.model.isfixedAssetCodeValid = true;
    }
    if (!self.model.fixedAssetType) {
      isValid = false;
      self.model.isfixedAssetTypeValid = true;
    }
    return isValid;
  }
        //Method to add the Fixed Asset Type
        var CreateFixedAssetType = function () {
            var URI = CONSTANTS.MASTER.UNIT.FIXED_ASSET.FIXED_ASSET_TYPE_MASTER_API;
            var data = {
                "fixedAssetCode": self.model.fixedAssetCode,
                "fixedAssetType": self.model.fixedAssetType,
                "status": true
            }
            $rootScope.startLoader();
            GenericService.serviceAction("POST", URI, data).then(
                function (response) {
                    var fixedassettype = {
                        "id": response.data.id,
                        "fixedAssetCode": self.model.fixedAssetCode,
                        "fixedAssetType": self.model.fixedAssetType,
                        "status": true
                    };
                    self.model.FixedAssetTypeList.push(fixedassettype);
                    ClearFields();

                    $rootScope.stopLoader();
                    growl.success('Record saved successfully');
                },
                function errorCallback(response) {
                    $rootScope.stopLoader();
                    growl.error('Something went wrong');
                });
        };

        //Method to update the Fixed Asset Type
        var updateFixedAssetType = function () {
            var URI = CONSTANTS.MASTER.UNIT.FIXED_ASSET.FIXED_ASSET_TYPE_MASTER_API + self.model.fixedAssetTypeId;
            var data = {
                "fixedAssetCode": self.model.fixedAssetCode,
                "fixedAssetType": self.model.fixedAssetType,
                "status": true
            }
            GenericService.serviceAction("PATCH", URI, data).then(
                function (response) {
                    var fixedassettype = self.model.FixedAssetTypeList.find(function (type) {
                        return type.id == self.model.fixedAssetTypeId;
                    });
                    fixedassettype.fixedAssetCode = data.fixedAssetCode;
                    fixedassettype.fixedAssetType = data.fixedAssetType;

                    ClearFields();
                    $rootScope.stopLoader();
                    growl.success('Record Updated successfully');
                },
                function errorCallback(response) {
                    $rootScope.stopLoader();
                    growl.error('Something went wrong');

                    self.model.activeFixedAssetTypeId = null;
                    self.model.isEdit = false;
                });
        };

        //Method to clear the fileds
        function ClearFields() {
            self.model.fixedAssetCode = "";
            self.model.fixedAssetType = "";
        }

        var ValidateFixedAssetType = function () {
            var isValid = true;

            if (!self.model.fixedAssetCode) {
                isValid = false;
                self.model.isfixedAssetCodeValid = true;
            }
            if (!self.model.fixedAssetType) {
                isValid = false;
                self.model.isfixedAssetTypeValid = true;
            }
            return isValid;
        }

        initializeController();
    }

    function config($stateProvider) {
        $stateProvider.state("fixedAssetType", {
            url: "/fixedAssetType",
            templateUrl: "views/master/UnitMaster/assetManagement/fixed-asset-type.html",
            controller: "FixedAssetType.Controller",
            controllerAs: "vm",
        });
    }

    angular
        .module("myApp")
        .config(config)
        .controller("FixedAssetType.Controller", fixedAssetTypeMasterController)
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