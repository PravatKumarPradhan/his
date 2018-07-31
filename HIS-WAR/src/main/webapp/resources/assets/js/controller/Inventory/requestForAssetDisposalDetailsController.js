(function () {
  "use strict";

  function requestForAssetDisposalDetailsController($scope, commonDetailService, $state, $rootScope, $http, CONSTANTS, GenericService, growl) {
    var self = this;

    //Default Constructor
    function initializeController() {
      $rootScope.loginpage = true;
      self.model = {



      };
      self.model.fromRequestDisposal = fromRequestDisposal;
      self.model.SaveApproval =SaveApproval;
      self.model.NavigateToBack = NavigateToBack;
      fromRequestDisposal();

    }
    function fromRequestDisposal() {
      var id = commonDetailService.getDataId();
      var URI = CONSTANTS.INVENTORY.SCRAP.REQUEST_FOR_ASSET_DISPOSAL.DETAIL_API + id;
      var data = {};
      GenericService.serviceAction("GET", URI, data).then(
        function (response) {
          self.model.requestDisposalDetailData = response.data;
        });
    }

    var SaveApproval = function () {

      // if (ValidateApproval()) {
        var URI = CONSTANTS.INVENTORY.SCRAP.REQUEST_FOR_ASSET_DISPOSAL.SAVE_API;
        var data = {
          "scrapRequestId": commonDetailService.getDataId(),
          "statusId": 4, 
          "witnessId": parseInt(self.model.witnessId) ,
        }

        $rootScope.startLoader();
        GenericService.serviceAction("POST", URI, data).then(
          function (response) {
            $rootScope.stopLoader();
            growl.success(response.data.message);
            NavigateToBack();
          }, function (err) {
            $rootScope.stopLoader();
            growl.error('Somthing Went Wrong');
          });
      }
    // }

    var NavigateToBack = function () {
      $state.go('requestForAssetDisposalWorklist');
  }

    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("detailsRequestForAssestDisposalWorklist", {
      url: "/detailsRequestForAssestDisposalWorklist",
      templateUrl: 'views/PharmacyInventory/Store/details-request-for-assest-disposal-worklist.html',
      controller: "RequestForAssetDisposalDetails.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("RequestForAssetDisposalDetails.Controller", requestForAssetDisposalDetailsController);
})(); 