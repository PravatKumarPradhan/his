(function () {
  "use strict";

  function wasteAssetDisposalWorklistDetailsController($scope, $rootScope, commonDetailService, $state, $http, CONSTANTS, GenericService, growl) {
    var self = this;

    //Default Constructor
    function initializeController() {
      $rootScope.loginpage = true;
      self.model = {



      };
      self.model.fromWasteDisposal = fromWasteDisposal;
      self.model.SaveApproval = SaveApproval;
      self.model.NavigateToBack = NavigateToBack;
      self.model.ValidateApproval = ValidateApproval;
      fromWasteDisposal();

    }

    function fromWasteDisposal() {
      var id = commonDetailService.getDataId();
      var URI = CONSTANTS.INVENTORY.SCRAP.WASTE_ASSET_DISPOSAL.DETAIL_API + id;
      var data = {};
      GenericService.serviceAction("GET", URI, data).then(
        function (response) {
          self.model.wasteDisposalDetailData = response.data;
        });
    }
    var SaveApproval = function () {

      // if (ValidateApproval()) {
        var URI = CONSTANTS.INVENTORY.SCRAP.WASTE_ASSET_DISPOSAL.SAVE_API ; 
        var data = {
          "assetDisposalWorklistRequestId": commonDetailService.getDataId(),
          "statusId": 4,
          "items": []
        }

        $rootScope.startLoader();
        angular.forEach(self.model.wasteDisposalDetailData.items, function (item) {
          var newItem = {
            "assetDisposalWorklistRequestDetailId": item.id,
            "disposeQuantity": parseInt(item.disposeQuantity) ,
            "statusId": 4
          }
          data.items.push(newItem);
        });

        GenericService.serviceAction("POST", URI, data).then(
          function (response) {
            $rootScope.stopLoader();
            growl.success(response.data.message);
            NavigateToBack();
          }, function (err) {
            $rootScope.stopLoader();
            growl.error('Somthing Went Wrong');
          });
      // }
    }
    var NavigateToBack = function () {
      $state.go('wasteAssetDisposalWorklist');
    }


    var ValidateApproval = function () {
      // var isValid = true;

      // // if (!self.model.wasteDisposalDetailData.items || self.model.SelectedItems.length == 0) {
      // //     growl.error('Select Items to Update and Save');
      // //     isValid = false;
      // // }

      // return isValid;
  }


    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("detailswasteAssetDisposalWorklist", {
      url: "/detailswasteAssetDisposalWorklist",
      templateUrl: 'views/PharmacyInventory/Store/details-waste-disposal-worklist.html',
      controller: "WasteAssetDisposalWorklistDetails.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("WasteAssetDisposalWorklistDetails.Controller", wasteAssetDisposalWorklistDetailsController);
})(); 