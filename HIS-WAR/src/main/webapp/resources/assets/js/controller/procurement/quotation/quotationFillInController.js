(function () {
  "use strict";

  function quotationFillInController($scope, $rootScope, $http, $filter, $state, CONSTANTS, GenericService, commonDetailService, rejectReasonService, StatusService, growl) {
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
        SelectedRequestIds: [],
        storeList: [{
          "store": "All"
        }],
        statuses: [{
          "status": "All"
        }],
        search: '',
        prevSearch: '',
        vendorNames: []
      };

      rejectReasonService.GetReason().then(function(reason) {
        self.model.rejectReasons = reason;
      });
      
      StatusService.GetStatus().then(function(status) {
        self.model.statusList = status;
      });

      //Methods
      self.model.OpenFromDate = OpenFromDate;
      self.model.OpenToDate = OpenToDate;
      self.model.GetQuotations = GetQuotations;
      self.model.NavigateToAddNew = NavigateToAddNew;
      self.model.NavigateToDetails = NavigateToDetails;
      self.model.NavigateToEdit = NavigateToEdit;
      self.model.SelectAllRequests = SelectAllRequests;
      self.model.SelectQuotation = SelectQuotation;
      self.model.isDisable = isDisable;
      self.model.UpdateQuotation = UpdateQuotation;
      self.model.OpenPopup = OpenPopup;
      self.model.ModalDismiss = ModalDismiss;
      self.model.isEdit = isEdit;
      self.model.isCancel = isCancel;
      self.model.SearchVendorNames = SearchVendorNames;
      self.model.AddVendorName = AddVendorName;
      self.model.VendorDetails = VendorDetails;
      commonDetailService.setDataId(null);

      PopulateDropdown();
    }

    var VendorDetails = function (vendorDetails) {
      self.model.personName = vendorDetails.contactPerson;
      self.model.address = vendorDetails.address;
      self.model.country = vendorDetails.country;
      self.model.state = vendorDetails.state;
      self.model.city = vendorDetails.city;
      self.model.area = vendorDetails.area;
      self.model.zipCode = vendorDetails.zipCode;    }

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

      var URI = CONSTANTS.PROCUREMENT.QUOTATION.QUOTATION_FILL_IN.DROPDOWN_API;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          self.model.storeList = self.model.storeList.concat(response.data.store);
          self.model.statuses = self.model.statuses.concat(response.data.status);
          $rootScope.stopLoader();
          GetQuotations();
        },
        function (err) {
          $rootScope.stopLoader();
        });
    }

    var GetQuotations = function () {
      var URI = CONSTANTS.PROCUREMENT.QUOTATION.QUOTATION_FILL_IN.SEARCH_API;

      var data = {
        "fromDate": moment(self.model.fromDate).format('YYYY-MM-DD'),
        "toDate": moment(self.model.toDate).format('YYYY-MM-DD'),
        "storeId": !!self.model.store && !!self.model.store.id ? self.model.store.id : undefined,
        "statusId": !!self.model.status && !!self.model.status.id ? self.model.status.id : undefined,
        "vendorName": self.model.vendorName ? self.model.vendorName : undefined,
        "vendorId": self.model.vendorId ? self.model.vendorId : undefined,
      }

      $rootScope.startLoader();
      GenericService.serviceAction("POST", URI, data).then(
        function (response) {
          self.model.quotationEnquiries = response.data;
          $rootScope.stopLoader();
        },
        function (err) {
          $rootScope.stopLoader();
          growl.error(err.data.message);
        });
    }

    var SelectAllRequests = function () {
      if (self.model.selectAllRows) {
        self.model.SelectedRequestIds = [];
        self.model.quotationEnquiries.forEach(function (quotation) {
          if (!isDisable(quotation)) {
            quotation.isSelected = true;
            self.model.SelectedRequestIds.push(quotation.id);
          }
        });
      } else {
        self.model.SelectedRequestIds = [];
        self.model.quotationEnquiries.forEach(function (quotation) {
          quotation.isSelected = false;
        });
      }
    }

    var UpdateQuotation = function () {
      if (self.model.action == 'Update') {
        if (ValidateStatus()) {
          var URI = CONSTANTS.PROCUREMENT.QUOTATION.QUOTATION_FILL_IN.APPROVE_API;

          var data = {
            "quotationFillId": self.model.SelectedRequestIds,
            "statusId": setStatus('Pending'),
            "approvalStatusId": setStatus("Pending Approval")
          };
          $rootScope.startLoader();
          GenericService.serviceAction("PATCH", URI, data).then(
            function (response) {
              self.model.SelectedRequestIds = [];
              GetQuotations();
              self.model.selectAllRows = false;

              $rootScope.stopLoader();
              growl.success(response.data.message);
            },
            function (err) {
              $rootScope.stopLoader();
              growl.error(err.data.message);
            });
        } else {
          growl.error('Select Quotation to Approve');
          return;
        }
      } else if (self.model.action == 'Cancel') {
        if (ValidetReason()) {
          var URI = CONSTANTS.PROCUREMENT.QUOTATION.QUOTATION_FILL_IN.CANCEL_API + self.model.id;

          var data = {
            "statusId": setStatus('Closed'),
            "approvalStatusId": setStatus('Cancelled'),
            "cancelReasonId": self.model.cancelReason ? self.model.cancelReason : undefined,
            "cancelNote": self.model.rejectNote
          }
          
          $rootScope.startLoader();
          GenericService.serviceAction("PATCH", URI, data).then(
            function (response) {
              self.model.SelectedRequestIds = [];

              ModalDismiss('cancelQuotation');
              GetQuotations();
              $rootScope.stopLoader();
              growl.success(response.data.message);
            }, function (err) {
              $rootScope.stopLoader();
              growl.error(err.data.message);
            });
        }
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

    var isDisable = function (quotation) {
      if (quotation.status != 'New') {
        return true;
      }
      return false;
    }

    var isEdit = function (quotation) {
      if (quotation.status == 'New') {
        return false;
      } else if (quotation.status == 'Pending' || quotation.status == 'Closed' || quotation.status == 'Cancelled') {
        return true;
      }
    }

    var isCancel = function (quotation) {
      if (quotation.status == 'Pending' || quotation.status == 'New') {
        return false;
      } else if (quotation.status == 'Closed' || quotation.status == 'Cancelled') {
        return true;
      }
    }

    var isSelected = function (quotation) {
      if (!isDisable(quotation))
        return quotation.isSelected;
      else
        return true;
    }

    var SelectQuotation = function (quotation) {
      var quotationIds = self.model.SelectedRequestIds;

      if (quotation.isSelected) {
        quotationIds.push(quotation.id);
      } else {
        var index = quotationIds.indexOf(quotation.id);
        if (index > -1) {
          quotationIds.splice(index, 1);
        }
      }

      if (self.model.quotationEnquiries.every(isSelected)) {
        self.model.selectAllRows = true;
      } else {
        self.model.selectAllRows = false;
      }
    }

    var OpenPopup = function (action, popupName, data) {
      self.model.action = action;
      self.model.id = data.id;
      var popup = angular.element('#' + popupName);
      popup.modal('show');
    }

    var ModalDismiss = function (modal) {
      var popup = angular.element('#' + modal);
      popup.modal('hide');
      self.model.rejectNote = undefined;
      self.model.cancelReason = undefined;
    }

    var ValidateStatus = function (){
      var isValid = true;

      if (!self.model.SelectedRequestIds || self.model.SelectedRequestIds.length == 0) {
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

    var NavigateToAddNew = function () {
      commonDetailService.setDataId(null);
      $state.go('addNewquotationFillIn');
    }

    var NavigateToDetails = function (purchase) {
      commonDetailService.setDataId(purchase.id);
      $state.go('qfillInDetails');
    }

    var NavigateToEdit = function (purchase) {
      commonDetailService.setDataId(purchase.id);
      $state.go('addNewquotationFillIn');
    }

    var setStatus = function (x){
      var status = self.model.statusList.find(function(obj){
        return obj.status == x;
      });
      
      return status.id;
    }

    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("quotationFillIn", {
      url: "/quotationFillIn",
     templateUrl: 'views/procurement/quotation/quotation-fill-in.html',
      controller: "QuotationFillIn.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("QuotationFillIn.Controller", quotationFillInController);
})();