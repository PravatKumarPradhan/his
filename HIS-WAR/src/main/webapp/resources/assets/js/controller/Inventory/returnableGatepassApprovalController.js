(function () {
    "use strict";
  
    function returnableGatepassApprovalController($scope, $rootScope, $http,$state, $filter,commonDetailService, CONSTANTS, GenericService, growl) {
      var self = this;
      
      //Default Constructor
      function initializeController() {
        $rootScope.loginpage = true;
        self.model = {
            stores: [{
                "store": "All Stores"
            }],
            statuses: [{
                "status": "All Status"
            }],
            assetTypes: [{
                "type": "All Asset Type"
            }],
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
            fromDate: new Date(),
            toDate: new Date(),
  
         
      };
      self.model.OpenFromDate = OpenFromDate;
      self.model.OpenToDate = OpenToDate;
      self.model.PopulateDropdown = PopulateDropdown;
      self.model.SearchVendorNames = SearchVendorNames;
      self.model.nameNotFound = nameNotFound;
      self.model.AddVendorName = AddVendorName;
      self.model.search = '';
      self.model.prevSearch = '';
      self.model.vendorNames = [];
      self.model.againstId;
      self.model.ReturnableGatepassSearch = ReturnableGatepassSearch;
      self.model.ReturnableApprovalDetails =ReturnableApprovalDetails;
      PopulateDropdown();
    }

    var OpenFromDate = function () {
        self.model.fromDateOpened = true;
        self.model.toDateOptions.minDate = self.model.fromDate;
    }

    var OpenToDate = function () {
        self.model.toDateOpened = true;
        self.model.toDateOptions.minDate = self.model.fromDate;
    }

    var PopulateDropdown = function () {
        var returnable = self.model;
        returnable.store = returnable.stores[0];
        returnable.status = returnable.statuses[0];
        returnable.assetType = returnable.assetTypes[0];
        var URI = CONSTANTS.INVENTORY.STORE.RETURNABLE_GATEPASS_APPROVAL.DROPDOWN_API;

        $rootScope.startLoader();
        GenericService.serviceAction("GET", URI, {}).then(
            function (response) {
                returnable.stores = returnable.stores.concat(response.data.store);
                returnable.statuses = returnable.statuses.concat(response.data.status);
                returnable.assetTypes = returnable.assetTypes.concat(response.data.assetType);
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

    var ReturnableGatepassSearch = function () {
        var URI = CONSTANTS.INVENTORY.STORE.RETURNABLE_GATEPASS_APPROVAL.SEARCH_API;
        var data = {
            "fromDate": moment(self.model.fromDate).format('YYYY-MM-DD'),
            "toDate": moment(self.model.toDate).format('YYYY-MM-DD'),
            "storeId": self.model.store.id,
            "statusId": self.model.status.id,
            "vendorId": self.model.vendorId,
            "vendor": self.model.vendorName,
            "assetTypeId": self.model.assetType.id,
            "againstId": self.model.againstId,
            "gatepassNo": self.model.gatepassNo, 
            "id": self.model.gatepassId
        }; 
        $rootScope.startLoader()
        GenericService.serviceAction("POST", URI, data).then(
            function (response) {
                $rootScope.stopLoader(); 
                self.model.returnableGatepassApprovalData = response.data;
            },
            function errorCallback(response) {
                $rootScope.stopLoader();
                growl.error('Error');
            });
    }

    
    var ReturnableApprovalDetails = function (returnable) {
        commonDetailService.setDataId(returnable.id);
        $state.go('detailsreturnaleGatepassApproval'); 
      }
  
      initializeController();
    }
  
    function config($stateProvider) {
      $stateProvider.state("returnaleGatepassApproval", {
        url: "/returnaleGatepassApproval",
        templateUrl: 'views/PharmacyInventory/Store/returnable-gatepass-approval.html',
        controller: "ReturnableGatepassApproval.Controller",
        controllerAs: "vm"
      });
    }
  
    angular
      .module("myApp")
      .config(config)
      .controller("ReturnableGatepassApproval.Controller", returnableGatepassApprovalController);
  })(); 