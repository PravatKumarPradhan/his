(function () {
  "use strict";

  function itemCreationDetailsController($rootScope, $scope, $http, CONSTANTS, $state, GenericService, commonDetailService, rejectReasonService, StatusService, growl) {
    var self = this;

    function initializeController() {
      $rootScope.loginpage = true;

      self.model = {
        SelectedItems: []
      };
      
      StatusService.GetStatus().then(function(status){
        self.model.statusList = status;
      });

      rejectReasonService.GetReason().then(function(reason) {
        self.model.rejectReasons = reason;
      });

      self.model.NavigateToBack = NavigateToBack;
      self.model.NavigateToItemMaster = NavigateToItemMaster;
      self.model.NavigateToAssetMaster = NavigateToAssetMaster;
      self.model.CreateItem = CreateItem;
      self.model.CreateAsset = CreateAsset;
      self.model.isDisable = isDisable;
      GetPurchaseRequest(commonDetailService.getDataId());
    }

    var setStatus = function (x){
      var status = self.model.statusList.find(function(obj){
        return obj.status == x;
      });
      
      return status.id;
    }

    var GetPurchaseRequest = function (id) {
      commonDetailService.setDataId(null);
      var URI = CONSTANTS.PROCUREMENT.PURCHASE.ITEM_CREATION.DETAILS_API + id;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          self.model.itemDetails = response.data;
          self.model.requestItems = JSON.parse(JSON.stringify(response.data.items));
          $rootScope.stopLoader();
        },
        function (err) {
          $rootScope.stopLoader();
          growl.error(err.data.message, {title: err.status});
        });
    }
    
    var NavigateToBack = function () {
      $state.go('itemCreationPendingList');
    }

    var NavigateToItemMaster = function () {
      $state.go('itemMasterGlobal');
    }

    var NavigateToAssetMaster = function () {
      $state.go('fixedAssetMaster');
    }

    var ValidItem = function () {
      var isValid = true;
      if (!selectedItem()) {
        growl.error('Select Item First');
        isValid = false;
      }

      return isValid;
    }

    var CreateItem = function () {
      if (ValidItem()) {
        commonDetailService.setDataId(selectedItem());
        NavigateToItemMaster();
      }
    }

    var CreateAsset = function () {
      if (ValidItem()) {
        commonDetailService.setDataId(selectedItem());
        NavigateToAssetMaster();
      }
    }

    var selectedItem = function(){
      var returnItem;
      self.model.itemDetails.items.find(function(item){
        if (item.isSelected) {
          returnItem = item;
        }
      });
      return returnItem;
    }

    var isDisable = function (purchase) {
       // || purchase.statusId == 7 || purchase.statusId == 9
      if (purchase.statusId !== 5) {
        return true;
      }
      return false;
    }

    var isSelected = function (item) {
      if (true)
        return true;
      else
        return item.isSelected;
    }

    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("itemCreationDetails", {
      url: "/itemCreationDetails",
      templateUrl: 'views/procurement/purchase/item-creation-details.html',
      controller: "ItemCreationDetails.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("ItemCreationDetails.Controller", itemCreationDetailsController);
})();