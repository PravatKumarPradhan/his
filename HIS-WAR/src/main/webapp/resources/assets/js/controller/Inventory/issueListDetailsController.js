(function () {
    "use strict";

    function issueListDetailsController($scope, $rootScope, $http, $state,CONSTANTS, commonDetailService, GenericService, $filter, growl) {
        var self = this;
        var selectItemList = [];
        var masterId;
        //Default Constructor
        function initializeController() {
            $rootScope.loginpage = true;
            self.model = {

                requestData: [],
      

            };
            self.model.fromIssueList = fromIssueList;
            fromIssueList();
        }


        function fromIssueList() { 
             
            var issueId = commonDetailService.getDataId();
            var URI = CONSTANTS.INVENTORY.STORE.MATERIAL_ISSUE.MATERIAL_ISSUE_LIST.DETAILS_API + issueId;
            var data = {};
            GenericService.serviceAction("GET", URI, data).then(
              function (response) {
                // self.model.storeData = materialPickerService.passMaterialPickerId(id);
                // self.model.detailsStoreIndenting = response.data.items;
                self.model.issueListDetail = response.data;
 
              });
          }
       
      
        initializeController();
    }

    

    function config($stateProvider) {
        $stateProvider.state("detailsIssueList", {
            url: '/detailsIssueList',
            templateUrl: 'views/PharmacyInventory/InPatient/details-issue-list.html',
            controller: "IssueListDetails.Controller",
            controllerAs: 'vm'
        });
    }

    angular
        .module("myApp")
        .config(config)
        .controller("IssueListDetails.Controller", issueListDetailsController)
})(); 