(function () {
  "use strict";

  function addNewBillSalesReturnController($scope, $rootScope, $http, CONSTANTS, GenericService, $filter, growl, CurrencyService, $state) {
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
        salesTypes: [{
          "saleType": "All"
        }],

        isReturnQuantityValid: [],
        isReturnReasonValid: [],
        SelectedRequestItems: []
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
      self.model.GetBillList = GetBillList;
      self.model.GetBillListPopUp = GetBillListPopUp;
      self.model.AddBillToSales = AddBillToSales;
      self.model.SelectBill = SelectBill;
      self.model.SaveBill = SaveBill;
      self.model.ClearFields = ClearFields;
      self.model.SelectAllRequests = SelectAllRequests;
      self.model.SelectSalesReturnItem = SelectSalesReturnItem;
      self.model.ValidateBill = ValidateBill;
      self.model.CalculateQuantity = CalculateQuantity;

      PopulateSalesTypeDropdown();
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

    var PopulateSalesTypeDropdown = function () {
      var URI = CONSTANTS.GLOBAL.SALE_TYPE_DROPDOWN_API;
      self.model.salesType = self.model.salesTypes[0];

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {

          self.model.salesTypes = self.model.salesTypes.concat(response.data);

        });
      $rootScope.stopLoader();
    }

    var GetBillListPopUp = function () {
      self.model.salesReturnBillList = [];
      var popup = angular.element('#getBillsModal');
      popup.modal('show');
    }

    var GetBillList = function () {
      var bills = self.model;
   
      var URI = CONSTANTS.PHARMACY.OP.SALES_RETURN.DETAIL_SEARCH_API;

      var data = {
        "fromDate": moment(bills.fromDate).format('YYYY-MM-DD'),
        "toDate": moment(bills.toDate).format('YYYY-MM-DD'),
        "patientName": !!bills.patientName ? bills.patientName : undefined,
        "uhid": !!bills.uhid ? bills.uhid : undefined,
        "saleTypeId": !!bills.salesType.id && !!bills.salesType.id ? bills.salesType.id : undefined,
        // "walkInFlag": !! bills.walkinFlag 
      }
      $rootScope.startLoader();
      GenericService.serviceAction("POST", URI, data).then(
        function (response) {
          bills.salesReturnBillList = response.data;

          for (let sales of bills.salesReturnBillList) {
            sales.isChecked = false;
          }

          // bills.salesReturnBillList[0]['isChecked'] = true;
          
          // bills.salesReturnBillList
        });
      $rootScope.stopLoader();
    }

    var SelectBill = function (item) {
      self.model.billId = item.id
    }

    var AddBillToSales = function () {
      var URI = CONSTANTS.PHARMACY.OP.SALES_RETURN.ADD_BILL_API + self.model.billId;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {

          self.model.billDetailList = response.data;
        });

        if(self.model.billId == undefined || self.model.billId ==''){
          growl.error('Please select one item from list');
        }

      $rootScope.stopLoader();
     
    }

    var CalculateQuantity = function (item, uom) {
      if (!!uom) {
        var pUom = $filter('filter')(item.uom, {
          uomTypeId: 1
        }, true)[0];

        var sUom = $filter('filter')(item.uom, {
          uomTypeId: 2
        }, true)[0];

        var dUom = $filter('filter')(item.uom, {
          uomTypeId: 3
        }, true)[0];

        if (item.uomTypeId == 1) {
          item.leaseQuantity = pUom.conversion * sUom.conversion * dUom.conversion * item.quantity;
        } else if (item.uomTypeId == 2) {
          item.leaseQuantity = sUom.conversion * dUom.conversion * item.quantity;
        } else if (item.uomTypeId == 3) {
          item.leaseQuantity = dUom.conversion * item.quantity;
        }
      } else {
        item.leaseQuantity = 0;
      }
      // console.log(item.leaseQuantity);
    }

    var ClearFields = function (item) {

      item.quantity = undefined;
      item.reason = undefined;
    }

    var SelectAllRequests = function () {

      if (self.model.selectAllRows) {
        self.model.SelectedRequestItems = [];
        self.model.billDetailList.item.forEach(function (item) {
          item.isSelected = true;
          self.model.SelectedRequestItems.push(item);

        });
      } else {
        self.model.SelectedRequestItems = [];
        self.model.billDetailList.item.forEach(function (item) {
          item.isSelected = false;
        });
      }
      // console.log(self.model.SelectedRequestItems);
    }

    var isSelected = function (item) {
      return item.isSelected;
    }

    var SelectSalesReturnItem = function (item) {

      var itemIds = self.model.SelectedRequestItems;

      if (item.isSelected) {
        itemIds.push(item);
      } else {
        var index = itemIds.indexOf(item);
        if (index > -1) {
          itemIds.splice(index, 1);
        }
      }

      if (self.model.billDetailList.item.every(isSelected)) {
        self.model.selectAllRows = true;
      } else {
        self.model.selectAllRows = false;
      }
      // console.log(itemIds);
    }

    var NevigatetoSalesReturn = function () {
      $state.go('billSalesReturn');
    }

    var SaveBill = function (bill) {
      if (ValidateBill(bill)) {

        var URI = CONSTANTS.PHARMACY.OP.SALES_RETURN.SAVE_API;
        var data = {

          "storeId": 1,
          "saleId": bill.id ? bill.id : undefined,
          "totalRefundAmount": bill.totalRefundAmount ? bill.totalRefundAmount : undefined,
          "item": []
        }

        angular.forEach(bill.item, function (item, key) {
          if (item.isSelected) {
            var billItem = {

              "saleDetailId": item.id,
              "returnQuantity": Number(item.quantity),
              "leaseReturnQuantity": item.leaseQuantity,
              "refundAmount": item.refundAmount,
              "reason": item.reason

            }
            data.item.push(billItem);
          }
        });
        // console.log(data);
        $rootScope.startLoader();
        GenericService.serviceAction("POST", URI, data).then(
          function (response) {
            ClearFields(bill);
            growl.success('Sales return bill successffully saved');
            NevigatetoSalesReturn();
            $rootScope.stopLoader();
          },
          function (err) {
            $rootScope.stopLoader();
            growl.error('Error while saving sales return bill');
          });
      }
    }

    // self.model.SelectedRequestItems


    var ValidateBill = function (bill) {
      var isValid = true;
      if (!self.model.SelectedRequestItems || self.model.SelectedRequestItems.length <= 0) {
        isValid = false;
        growl.error("Please add some items in the bill");
      } else {
        self.model.SelectedRequestItems.forEach(function (item) {

          item['isItemValid'] = false;
          item['isReturnQuantityValid'] = false;
          item['isReasonValid'] = false;

          if (!item.quantity || !item.reason) {
            item['isItemValid'] = true;
            isValid = false;
          }

          if (!item.reason) {
            item['isReasonValid'] = true;
            isValid = false;
          }

          if ((item.quantityIssued < (parseInt(item.quantity) + item.quantityReturned)) || !item.quantity) {
            item['isReturnQuantityValid'] = true;
            isValid = false;
            growl.error("Please enter valid quantity");
          }

          if (item.quantity == 0 ) {
            item['isReturnQuantityValid'] = true;
            isValid = false;
            growl.error("Please enter quantity greater than zero");
          }

        });

      }

      return isValid;
    }


    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("addNewbillSalesReturn", {
      url: "/addNewbillSalesReturn",
      templateUrl: 'views/PharmacyInventory/InPatient/add-new-bill-sales-return.html',
      controller: "AddNewBillSalesReturn.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("AddNewBillSalesReturn.Controller", addNewBillSalesReturnController);
})();