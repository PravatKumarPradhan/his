(function () {
    "use strict";
  
    function witnessApprovalDetailsController($scope, $rootScope,commonDetailService ,$state, $http, CONSTANTS, GenericService, growl) {
      var self = this;
      
      //Default Constructor
      function initializeController() {
        $rootScope.loginpage = true;
        self.model = {
         
  
         
      };
  
      self.model.fromWitnessApproval = fromWitnessApproval;
      fromWitnessApproval(); 
  
    }

    function fromWitnessApproval() {
        var id = commonDetailService.getDataId();
        var URI = CONSTANTS.INVENTORY.SCRAP.WITNESS_APPROVAL.DETAIL_API + id;
        var data = {};
        GenericService.serviceAction("GET", URI, data).then(
          function (response) {
            self.model.witnesslDetailData = response.data;
          });
      }

      var SaveApproval = function () {

        // if (ValidateApproval()) {
          var URI = CONSTANTS.INVENTORY.SCRAP.WASTE_ASSET_DISPOSAL.SAVE_API + commonDetailService.getDataId(); 
          var data = { 
            "statusId": 4,
            "items": []
          }
  
          $rootScope.startLoader();
          angular.forEach(self.model.wasteDisposalDetailData.items, function (item) {
            var newItem = {
                "id": 1,
                "statusId": 1,
                "approvedDisposedQuantity": 100,
                "rejectQuantity": 50,
                "rejectReasonId": 1,
                "rejectNote": "Note"
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
  
    
      initializeController();
    }
  
    function config($stateProvider) {
      $stateProvider.state("detailsWitnessApproval", {
        url: "/detailsWitnessApproval",
        templateUrl: 'views/PharmacyInventory/Store/details-witness-approval.html',
        controller: "WitnessApprovalDetails.Controller",
        controllerAs: "vm"
      });
    }
  
    angular
      .module("myApp")
      .config(config)
      .controller("WitnessApprovalDetails.Controller", witnessApprovalDetailsController);
  })();
 