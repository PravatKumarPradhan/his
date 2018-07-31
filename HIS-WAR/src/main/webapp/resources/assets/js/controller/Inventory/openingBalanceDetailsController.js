 (function () {
    "use strict";
    var CACHE = {};
  
    function openingBalanceDetailsController($scope, $rootScope, growl,$http,CONSTANTS,$state, GenericService, openingBalanceService) {
      var self = this;
      var selectItemList = [];
  
      //Default Constructor
      function initializeController() {
        $rootScope.loginpage = true;
        self.model = {
          selectAllRows: false,
          SelectedItems: [],
          opbDetail:[],
          SelectedRequestIds : []
        };
        
        self.model.fillItemList = fillItemList;
        self.model.uomInfoOnClick = uomInfoOnClick;
        self.model.uomModalOnClick  = uomModalOnClick;
        self.model.SelectAllRequests =SelectAllRequests;
        self.model.SelectAllApprove = SelectAllApprove;
        self.model.SelectOpeningBalance = SelectOpeningBalance;
        self.model.opbDetail= [];
        self.model.SaveApproval = SaveApproval;
        self.model.ValidateApproval = ValidateApproval;
        self.model.isSelected = isSelected;
        self.model.isDisable = isDisable;
        fromOpeningBalance();
    }
        function fromOpeningBalance() {
           
          var id = openingBalanceService.getOpeningBalanceId();
          var URI = CONSTANTS.INVENTORY.STOCK.OPENING_BALANCE.DETAILS_API + id;
          var data = {};
          GenericService.serviceAction("GET", URI, data).then(
            function (response) {
              // self.model.storeData = openingBalanceService.passOpeningBalanceId(id);
              // self.model.detailsStoreIndenting = response.data.items;
              self.model.fillItemList(response.data.items);
              self.model.opbDetail = response.data;
              
            });
        }

        var uomInfoOnClick = function (uomList, index) {
          self.model.selectedUOMList = [];
          self.model.selectedUOMUnit = [];
          self.model.selectedItemIndex = index;
          if (uomList)
            uomList.forEach(function (item) {
              item['selectedUOM'] = item.uomUnit;
              item['selectedUOM'] = item.uomUnit;
              self.model.selectedUOMList.push(item);
              self.model.selectedUOMUnit.push({ 'uomType': item.uomUnit });
            });
        }
    
        var uomModalOnClick = function () {
          self.model.opbDetail[self.model.selectedItemIndex].defaultUOM = [];
          if (self.model.selectedUOMList) {
            self.model.selectedUOMList.forEach(function (item) {
              self.model.opbDetail[self.model.selectedItemIndex].defaultUOM.push({
                uomTypeId: item.uomTypeId,
                uomUnitId: item.uomUnitId,
                conversion: item.conversion
              });
            });
          }
        }

        var fillItemList = function (list) {
          list.forEach(function (item) {
            var defaultUOM = [];
            for (let uom of item.uom) {
              defaultUOM.push({
                uomTypeId: uom.uomTypeId,
                uomUnitId: uom.uomUnitId,
                conversion: uom.conversion
              });
            }
  
            item['defaultUOM'] = defaultUOM;
            self.model.opbDetail.push(item);
          });
        }
  

        var SaveApproval = function () { 
           
          
          var id = openingBalanceService.getOpeningBalanceId();
          var URI = CONSTANTS.INVENTORY.STOCK.OPENING_BALANCE.APPROVE_API + id; 
            var data = {
              "openingBalanceDetailId": self.model.SelectedRequestIds,
              "statusId": 8
            }
    
            GenericService.serviceAction("PATCH", URI, data).then(
              function (response) {
    
                $rootScope.stopLoader();
                growl.success(response.data.message);
                NavigateToBack();
              }, function (err) {
                $rootScope.stopLoader();
                growl.error('Somthing Went Wrong');
              });
          
        }
         
        var NavigateToBack = function () {
          $state.go('openingBalance');
        }
        
        var SelectAllRequests = function () {
           
          if (self.model.selectAllRows) {
            self.model.SelectedRequestIds = [];
            self.model.opbDetail.items.forEach(function (opb) {
              if (!isDisable(opb)) {
                opb.isSelected = true;
                self.model.SelectedRequestIds.push(opb.id);
              }
            });
          } else {
            self.model.SelectedRequestIds = [];
            self.model.opbDetail.items.forEach(function (opb) {
              opb.isSelected = false;
            });
          }
        }
    
        var isSelected = function (opb) {
          if (!isDisable(opb))
            return opb.isSelected;
          else
            return true;
        }
        var isDisable = function (opb) {
          if (opb.statusId == 7 || opb.statusId == 5 || opb.statusId == 9||opb.statusId != 1) {
            return true;
          }
          return false;
        }
      var isSelected = function (opb) {
          if (!isDisable(opb))
            return opb.isSelected;
          else
            return true;
        }
        var isDisable = function (opb) {
          if (opb.statusId == 7 || opb.statusId == 5 || opb.statusId == 9||opb.statusId != 1) {
            return true;
          }
          return false;
        }
    
    
    
        var SelectOpeningBalance = function (opb) {
            
          var opbIds = self.model.SelectedRequestIds;
    
          if (opb.isSelected) {
            opbIds.push(opb.id);
          } else {
            var index = opbIds.indexOf(opb.id);
            if (index > -1) {
              opbIds.splice(index, 1);
            }
          }
    
          if (self.model.opbDetail.items.every(isSelected)) {
            self.model.selectAllRows = true;
          } else {
            self.model.selectAllRows = false;
          }
        }


        var ValidateApproval = function () {
          // var isValid = true;
    
          // if (!self.model.SelectedItems || self.model.SelectedItems.length == 0) {
          //   growl.error('Select Items to Update and Save');
          //   isValid = false;
          // }
    
          // return isValid;
        }
    

        var SelectAllApprove = function () {
          self.model.selectItemList = [];
          self.model.opbDetail.forEach(function (x) {
            if (self.model.selectAllApprove) {
              x.isApprove = true
              self.model.selectItemList.push(x.id);
            } else {
              x.isApprove = false;
            }
          });
        }
        initializeController();
    }


    function config($stateProvider) {
      $stateProvider.state("DetailsOpeningBalance", {
        url: "/DetailsOpeningBalance",
        templateUrl: 'views/PharmacyInventory/Store/details-opening-balance.html',
        controller: "OpeningBalanceDetails.Controller",
        controllerAs: "vm"
      });
    }
  
    angular
      .module("myApp")
      .config(config)
      .controller("OpeningBalanceDetails.Controller", openingBalanceDetailsController);
  })();