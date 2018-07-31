(function () {
    "use strict";
  
    function billSalesReturnController($scope, $rootScope, $http, CONSTANTS, GenericService, $filter, growl, CurrencyService) {
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
          isReturnQuantityValid : []
        };
  
        //Methods
        CurrencyService.GetCurrency().then(function(currency){
          self.model.Currency = currency;
        })
        self.model.OpenFromDate = OpenFromDate;
        self.model.OpenToDate = OpenToDate;
        self.model.SearchpatientNames = SearchpatientNames;
        self.model.AddUHID = AddUHID;
        self.model.search = '';
        self.model.prevSearch = '';
        self.model.patientNames = [];
        self.model.GetSalesReturnBillList = GetSalesReturnBillList;
        self.model.SalesReturnBillDetails = SalesReturnBillDetails;
        GetSalesReturnBillList();
  
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
              "details": "patient Not found with name " + search
          };
          self.model.patientNames = [item];
          return self.model.patientNames;
      }
  
      var GetSalesReturnBillList = function () {
        var bills = self.model;
        var URI = CONSTANTS.PHARMACY.OP.SALES_RETURN.SEARCH_API;

        var data = {
          "fromDate": moment(bills.fromDate).format('YYYY-MM-DD'),
          "toDate": moment(bills.toDate).format('YYYY-MM-DD'),
          "patientName": !!bills.patientName ? bills.patientName : undefined,
          "uhid": !!bills.uhid ? bills.uhid : undefined,
          "returnNo": !!bills.returnNo ? bills.returnNo : undefined,
        }
        $rootScope.startLoader();
        GenericService.serviceAction("POST", URI, data).then(
          function (response) {
            bills.salesReturnBillList = response.data;

          });
          $rootScope.stopLoader();
      }
  
      var SalesReturnBillDetails = function (item){
        var URI = CONSTANTS.PHARMACY.OP.SALES_RETURN.DETAILS_API + item.id;
        
        $rootScope.startLoader();
        GenericService.serviceAction("GET", URI, {}).then(
          function (response) {

            self.model.SalesReturnBillDetailList = response.data;
           
          });
          $rootScope.stopLoader();
      }

      initializeController();
    }
  
    function config($stateProvider) {
      $stateProvider.state("billSalesReturn", {
        url: "/billSalesReturn",
        templateUrl: 'views/PharmacyInventory/InPatient/bill-sales-return.html',
        controller: "BillSalesReturn.Controller",
        controllerAs: "vm"
      });
    }
  
    angular
      .module("myApp")
      .config(config)
      .controller("BillSalesReturn.Controller", billSalesReturnController);
  })();