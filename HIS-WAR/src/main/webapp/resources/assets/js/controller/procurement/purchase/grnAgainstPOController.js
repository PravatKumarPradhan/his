(function () {
  "use strict";

  function GRNAgainstPOController($scope, $rootScope, $http, $filter, $state, CONSTANTS, GenericService, StatusService, cancelReasonService, growl) {
    var self = this;

    //Default Constructor
    function initializeController() {
      $rootScope.loginpage = true;
      self.model = {
        fromDate: new Date(),
        toDate: new Date(),
        fromDateOptions: {
          formatYear: 'yyyy',
          showWeeks: false,
          maxDate : new Date()
        },
        toDateOptions: {
          formatYear: 'yyyy',
          showWeeks: false,
          maxDate : new Date()
        },
        storeList: [{
          "store": "All"
        }],
        statuses: [{
          "status": "All"
        }],
        grnDetailList: [],
        search: '',
        prevSearch: '',
        vendorNames: [],
        poId: null
      };
      
      StatusService.GetStatus().then(function(status) {
        self.model.statusList = status;
      });

      cancelReasonService.GetReason().then(function(reason) {
        self.model.reasons = reason;
      });

      //Methods
      self.model.OpenFromDate = OpenFromDate;
      self.model.OpenToDate = OpenToDate;
      self.model.GetGRNDetails = GetGRNDetails;
      self.model.isDisable = isDisable;
      self.model.CancelPO = CancelPO;
      self.model.CancelRecord = CancelRecord;
      self.model.ModalDismiss = ModalDismiss;
      self.model.grnDetails = grnDetails;
      self.model.SearchVendorNames = SearchVendorNames;
      self.model.AddVendorName = AddVendorName;

      PopulateDropdown();
    }

    var setStatus = function (x){
      var status = self.model.statusList.find(function(obj){
        return obj.status == x;
      });
      
      return status.id;
    }

    var grnDetails = function (item) {
      var URI = CONSTANTS.PROCUREMENT.PURCHASE.GRN_AGAINST_PO.DETAILS_API + item.id;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          var popup = angular.element('#GRNAgaintPODetails_Modal');
          popup.modal('show');
          self.model.grnDetailList = response.data;
          $rootScope.stopLoader();
        },
        function (err) {
          $rootScope.stopLoader();
          growl.error(err.data.message);
        });

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
      self.model.store = self.model.storeList[0];
      self.model.status = self.model.statuses[0];

      var URI = CONSTANTS.PROCUREMENT.PURCHASE.GRN_AGAINST_PO.DROPDOWN_API;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          self.model.storeList = self.model.storeList.concat(response.data.store);
          self.model.statuses = self.model.statuses.concat(response.data.status);
          $rootScope.stopLoader();
          GetGRNDetails();
        },
        function (err) {
          $rootScope.stopLoader();
        });
    }

    var ValidateDate = function () {
      var isValid = true;
      if (self.model.fromDate > self.model.toDate) {
        growl.error('From Date should lesser than To Date');
        isValid = false;
      }

      return isValid;
    }

    var GetGRNDetails = function () {
      if (ValidateDate()) {
        var URI = CONSTANTS.PROCUREMENT.PURCHASE.GRN_AGAINST_PO.SEARCH_API;

        var data = {
          "fromDate": moment(self.model.fromDate).format('YYYY-MM-DD'),
          "toDate": moment(self.model.toDate).format('YYYY-MM-DD'),
          "storeId": !!self.model.store && !!self.model.store.id ? self.model.store.id : undefined,
          "vendorName": self.model.vendorName ? self.model.vendorName : undefined,
          "vendorId": self.model.vendorId ? self.model.vendorId : undefined,
          "poNumber": self.model.poNumber,
          "statusId": !!self.model.status && !!self.model.status.id ? self.model.status.id : undefined
        }

        $rootScope.startLoader();
        GenericService.serviceAction("POST", URI, data).then(
          function (response) {
            self.model.poDetails = response.data;
            $rootScope.stopLoader();
          },
          function (err) {
            $rootScope.stopLoader();
            growl.error(err.data.message);
          });
      }
    }

    var CancelPO = function () {
      if (ValidateReason()) {
        var URI = CONSTANTS.PROCUREMENT.PURCHASE.GRN_AGAINST_PO.CANCEL_API + self.model.poId;

        var data = {
          "statusId": setStatus('Closed'),
          "approvalStatusId": setStatus('Cancelled'),
          "cancelReasonId": self.model.cancelReason ? self.model.cancelReason : undefined,
          "cancelNote": self.model.rejectNote
        }
        
        $rootScope.startLoader();
        GenericService.serviceAction("PATCH", URI, data).then(
          function (response) {
            ModalDismiss('cancelPO');
            GetGRNDetails();
            $rootScope.stopLoader();
            growl.success(response.data.message);
          }, function (err) {
            $rootScope.stopLoader();
            growl.error(err.data.message);
          }
        );
      }
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

    function AddVendorName (vendor) {
      self.model.vendorName = vendor.vendorName;
      self.model.vendorId = vendor.id;
    }

    var isDisable = function (grn) {
      if (grn.status != 'New') {
        return true;
      }
      return false;
    }

    var CancelRecord = function (action, popupName, data) {
      self.model.action = action;
      self.model.poId = data.id;
      var popup = angular.element('#' + popupName);
      popup.modal('show');
    }

    var ValidateReason = function () {
      var isValid = true;

      if (!self.model.cancelReason) {
        self.model.isReasonValid = true;
        isValid = false;
      }
      
      return isValid;
    }

    var ModalDismiss = function (modal) {
      var popup = angular.element('#' + modal);
      popup.modal('hide');
      self.model.rejectNote = undefined;
      self.model.cancelReason = undefined;
    }

    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("GRNAgainstPO", {
      url: "/GRNAgainstPO",
      templateUrl: 'views/procurement/purchase/GRN-Against-PO-Status.html',
      controller: "GRNAgainstPO.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("GRNAgainstPO.Controller", GRNAgainstPOController);
})();