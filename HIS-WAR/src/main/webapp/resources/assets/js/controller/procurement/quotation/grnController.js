(function () {
  "use strict";

  function GRNController($scope, $rootScope, $http, $filter, $state, GenericService, commonDetailService, cancelReasonService, StatusService, growl, CONSTANTS) {
    var self = this;

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
        selectAllRows: false,
        SelectedGRNIds: [],
        storeList: [{
          "store": "Select Store"
        }],
        statuses: [{
          "status": "All"
        }],
        assetTypes: [{
          "type": "Select GRN Type"
        }],
        vendorId: null
      };

      cancelReasonService.GetReason().then(function(reason) {
        self.model.reasons = reason;
      });
      
      StatusService.GetStatus().then(function(status) {
        self.model.statusList = status;
      });

      //Methods
      self.model.OpenFromDate = OpenFromDate;
      self.model.OpenToDate = OpenToDate;
      self.model.GetGRNList = GetGRNList;
      self.model.NavigateToAddNew = NavigateToAddNew;
      self.model.NavigateToDetails = NavigateToDetails;
      self.model.NavigateToEdit = NavigateToEdit;
      self.model.SelectAllGRN = SelectAllGRN;
      self.model.SelectGRN = SelectGRN;
      self.model.isDisable = isDisable;
      self.model.isEdit = isEdit;
      self.model.isCancel = isCancel;
      self.model.UpdateGRN = UpdateGRN;
      self.model.OpenPopup = OpenPopup;
      self.model.ModalDismiss = ModalDismiss;
      self.model.SearchVendorNames = SearchVendorNames;
      self.model.AddVendorName = AddVendorName;
      self.model.search = '';
      self.model.prevSearch = '';
      self.model.vendorNames = [];

      PopulateDropdown();
    }

    var setStatus = function (x){
      var status = self.model.statusList.find(function(obj){
        return obj.status == x;
      });
      
      return status.id;
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
      var grn = self.model;
      grn.store = grn.storeList[0];
      grn.status = grn.statuses[0];
      grn.grnType = grn.assetTypes[0];

      var URI = CONSTANTS.PROCUREMENT.QUOTATION.GRN.GRN_DROPDOWN_API;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          grn.storeList = grn.storeList.concat(response.data.store);
          grn.statuses = grn.statuses.concat(response.data.status);
          grn.assetTypes = grn.assetTypes.concat(response.data.assetType);
          $rootScope.stopLoader();
          GetGRNList();
        },
        function (err) {
          growl.error(err.data.message);
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

    function AddVendorName (vendor) {
      self.model.vendorName = vendor.vendorName;
      self.model.vendorId = vendor.id;
    }

    var ValidateDate = function () {
      var isValid = true;
      if (self.model.fromDate > self.model.toDate) {
        growl.error('From Date should lesser than To Date');
        isValid = false;
      }

      return isValid;
    }

    var GetGRNList = function () {
      if (ValidateDate()) {
        var grn = self.model;
        
        var URI = CONSTANTS.PROCUREMENT.QUOTATION.GRN.SEARCH_GRN_API;
        var data = {
          "fromDate": moment(grn.fromDate).format('YYYY-MM-DD'),
          "toDate": moment(grn.toDate).format('YYYY-MM-DD'),
          "storeId": !!grn.store && !!grn.store.id ? grn.store.id : undefined,
          "grnNumber": grn.grnNumber ? grn.grnNumber : undefined,
          "grnTypeId": grn.grnType ? grn.grnType.id : undefined,
          "vendorName": grn.vendorName ? grn.vendorName : undefined,
          "vendorId": grn.vendorId ? grn.vendorId : undefined,
          "statusId": !!grn.status && !!grn.status.id ? grn.status.id : undefined,
        }

        $rootScope.startLoader();
        GenericService.serviceAction("POST", URI, data).then(
          function (response) {
            grn.grnList = response.data;
            grn.grnList.forEach(function(data){
              data.grnDate = moment(data.grnDate).format("DD/MM/YYYY");
            });
            $rootScope.stopLoader();
          },
          function (err) {
            $rootScope.stopLoader();
            growl.error(err.data.message);
          }
        );
      }
    }

    var NavigateToAddNew = function () {
      commonDetailService.setDataId(null);
      $state.go('addItemGRN');
    }

    var NavigateToDetails = function (grn) {
      commonDetailService.setDataId(grn.id);
      $state.go('GRNdetails');
    }

    var NavigateToEdit = function (grn) {
      commonDetailService.setDataId(grn.id);
      $state.go('addItemGRN');
    }

    var SelectAllGRN = function () {
      if (self.model.selectAllRows) {
        self.model.SelectedGRNIds = [];
        self.model.grnList.forEach(function (grn) {
          if (!isDisable(grn)) {
            grn.isSelected = true;
            self.model.SelectedGRNIds.push(grn.id);
          }
        });
      } else {
        self.model.SelectedGRNIds = [];
        self.model.grnList.forEach(function (grn) {
          grn.isSelected = false;
        });
      }
    }

    var isDisable = function (grn) {
      if (grn.status != 'New') {
        return true;
      }
      return false;
    }

    var isEdit = function (grn) {
      if (grn.status == 'New') {
        return false;
      } else if (grn.status == 'Pending' || grn.status == 'Cancelled' || grn.status == 'Closed') {
        return true;
      }
    }

    var isCancel = function (grn) {
      if (grn.status == 'Pending') {
        return false;
      } else if (grn.status == 'New') {
        return false;
      } else if (grn.status == 'Cancelled' || grn.status == 'Closed') {
        return true;
      }
    }

    var isSelected = function (grn) {
      if (!isDisable(grn))
        return grn.isSelected;
      else
        return true;
    }

    var SelectGRN = function (grn) {
      var purchaseIds = self.model.SelectedGRNIds;

      if (grn.isSelected) {
        purchaseIds.push(grn.id);
      } else {
        var index = purchaseIds.indexOf(grn.id);
        if (index > -1) {
          purchaseIds.splice(index, 1);
        }
      }

      if (self.model.grnList.every(isSelected)) {
        self.model.selectAllRows = true;
      } else {
        self.model.selectAllRows = false;
      }
    }

    var OpenPopup = function (action, popupName, data) {
      self.model.action = action;
      var popup = angular.element('#' + popupName);
      self.model.id = data.id;
      popup.modal('show');
    }

    var ValidateStatus = function (){
      var isValid = true;
      if (!self.model.SelectedGRNIds || self.model.SelectedGRNIds.length == 0) {
        isValid = false;
      }
      return isValid;
    }

    var ValidetReason = function () {
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

    var UpdateGRN = function () {
      if (self.model.action == 'Update') {
        if (ValidateStatus()) {
          var URI = CONSTANTS.PROCUREMENT.QUOTATION.GRN.APPROVE_GRN_API;

          var data = {
            "grnIdList": self.model.SelectedGRNIds,
            "statusId": setStatus('Pending'),
            "approvalStatusId": setStatus('Pending Approval')
          };

          $rootScope.startLoader();
          GenericService.serviceAction("PATCH", URI, data).then(
            function (response) {
              self.model.SelectedGRNIds = [];
              ModalDismiss('confirmSave');
              $rootScope.stopLoader();
              GetGRNList();
              self.model.selectAllRows = false;
              growl.success(response.data.message);
            },
            function (err) {
              ModalDismiss('confirmSave');
              $rootScope.stopLoader();
              growl.error(err.data.message);
            });
        } else {
          growl.error('Select GRN to Approve');
          ModalDismiss('confirmSave');
          return;
        }
      } else if (self.model.action == 'Cancel') {
        if (ValidetReason()) {
          var URI = CONSTANTS.PROCUREMENT.QUOTATION.GRN.CANCEL_GRN_API + self.model.id;;

          var data = {
            "statusId": setStatus('Closed'),
            "approvalStatusId": setStatus('Cancelled'),
            "cancelReasonId": self.model.cancelReason ? self.model.cancelReason : undefined,
            "cancelNote": self.model.rejectNote
          }

          $rootScope.startLoader();         
          GenericService.serviceAction("PATCH", URI, data).then(
            function (response) {
              self.model.SelectedGRNIds = [];
              ModalDismiss('cancelGRN');
              GetGRNList();

              $rootScope.stopLoader();
              growl.success(response.data.message);
            }, function (err) {
              $rootScope.stopLoader();
              growl.error(err.data.message);
            });
        }
      }
    }

    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("GRN", {
      url: "/GRN",
      templateUrl: 'views/procurement/quotation/GRN.html',
      controller: "GRN.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("GRN.Controller", GRNController);
})();