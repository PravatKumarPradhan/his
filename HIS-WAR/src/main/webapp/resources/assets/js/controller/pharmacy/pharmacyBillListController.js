(function () {
  "use strict";

  function pharmacyBillListController($scope, $rootScope, $http, CONSTANTS, GenericService, $filter, growl, CurrencyService) {
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
          maxDate: new Date()
        },
        toDateOptions: {
          formatYear: 'yyyy',
          showWeeks: false,
          maxDate: new Date()
        },
        salesTypeId: null
      };

      //Methods
      CurrencyService.GetCurrency().then(function (currency) {
        self.model.Currency = currency;
      })
      self.model.OpenFromDate = OpenFromDate;
      self.model.OpenToDate = OpenToDate;
      self.model.SearchpatientNames = SearchpatientNames;
      self.model.AddUHID = AddUHID;
      self.model.search = '';
      self.model.prevSearch = '';
      self.model.patientNames = [];
      self.model.GetBillDetails = GetBillDetails;
      self.model.GetBillList = GetBillList;

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

    var SearchpatientNames = function (search) {

      if (search.length < 3) return;

      if ((!self.model.prevSearch || self.model.prevSearch != search) && (search.length == 3)) {
        self.model.prevSearch = search;
        var URI = CONSTANTS.GLOBAL.PATIENT_SEARCH_API + search;
        $rootScope.startLoader();
        return GenericService.serviceAction("GET", URI, {})
          .then(function (response) {
            $rootScope.stopLoader();
            if (!!response.data && response.data.length > 0) {
              self.model.patientNames = response.data;
              $rootScope.stopLoader();
              //console.log(self.model.patientNames.itemName);
              return $filter('filter')(self.model.patientNames, {
                $: search
              });
            } else {
              $rootScope.stopLoader();
              return PatientNameNotFound(search);
            }
          }, function (err) {
            $rootScope.stopLoader();
            return PatientNameNotFound(search);
          });
      } else {
        if (!!self.model.patientNames && self.model.patientNames.length > 0 &&
          self.model.patientNames[0].itemFound != undefined && !self.model.patientNames[0].itemFound) {
          return PatientNameNotFound(search);
        } else {
          return $filter('filter')(self.model.patientNames, {
            $: search
          });
        }
      }
    }

    var AddUHID = function (item) {
      self.model.patientData = item;
      self.model.uhid = item.uhid;
      self.model.patientId = item.id;
      //   self.model.doctorId = item.id;
    }

    var PatientNameNotFound = function (search) {
      var item = {
        "itemFound": false,
        "details": "Patient Not found with name " + search
      };
      self.model.patientNames = [item];
      return self.model.patientNames;
    }

    //Method to populate the list of billList dropdown
    var PopulateDropdown = function () {
      var bills = self.model;
      var URI = CONSTANTS.PHARMACY.OP.BILL_LIST.DROPDOWN_API;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          $rootScope.stopLoader();
          response.data.salesType.unshift({
            "type": "All"
          });
          response.data.status.unshift({
            "status": "All"
          });

          bills.salesTypes = response.data.salesType;
          bills.statuses = response.data.status;

          bills.saleType = bills.salesTypes[0];
          bills.status = bills.statuses[0];

          GetBillList();
        },
        function (err) {
            $rootScope.stopLoader();
        });
    }

    var GetBillList = function () {
      var bills = self.model;
      var URI = CONSTANTS.PHARMACY.OP.BILL_LIST.SEARCH_API;

      var data = {
        "fromDate": moment(bills.fromDate).format('YYYY-MM-DD'),
        "toDate": moment(bills.toDate).format('YYYY-MM-DD'),
        "uhid": !!bills.uhid ? bills.uhid : undefined,
        "patientName": !!bills.patientName ? bills.patientName : undefined,
        "salesTypeId": !!bills.saleType && !!bills.saleType.id ? bills.saleType.id : undefined,
        "billNo": !!bills.billNo ? bills.billNo : undefined,
        "visitNo": !!bills.visitNo ? bills.visitNo : undefined,
        "statusId": !!bills.status && !!bills.status.id ? bills.status.id : undefined
      }
      $rootScope.startLoader();
      GenericService.serviceAction("POST", URI, data).then(
        function (response) {
          $rootScope.stopLoader();
          bills.bills = response.data;
        },
        function (err) {
            $rootScope.stopLoader();
        });
    }

    var GetBillDetails = function (bill) {

      var bills = self.model;
      var URI = CONSTANTS.PHARMACY.OP.BILL_LIST.DETAILS_API + bill.id;
      bill.billDetailList = [];

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          $rootScope.stopLoader();
          bills.billDetailList = response.data;
          bills.radio = bills.billDetailList.discountTypeId;
          var popup = angular.element('#billListDetails');
          popup.modal('show');
        },
        function (err) {
            $rootScope.stopLoader();
        });
    }

    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("pharmacyBillList", {
      url: "/pharmacyBillList",
      templateUrl: 'views/PharmacyInventory/InPatient/pharmacy-bill-list.html',
      controller: "PharmacyBillList.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("PharmacyBillList.Controller", pharmacyBillListController);
})();
