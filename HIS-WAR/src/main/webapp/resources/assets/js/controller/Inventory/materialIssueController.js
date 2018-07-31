(function () {
    "use strict";

    function materialIssueController($scope, $rootScope, $state) {
        var self = this;

        //Default Constructor
        function initializeController() {
            $rootScope.loginpage = true;

            self.model = {};

            self.model.NavigateToPendingIssue = NavigateToPendingIssue;
            self.model.NavigateToIssueList = NavigateToIssueList;
        }

        var NavigateToPendingIssue = function () {
            $state.go('materialIssue.pendingIssue');
        }

        var NavigateToIssueList = function () {
            $state.go('materialIssue.issueList');
        }

        initializeController();
    }

    function config($stateProvider) {
        $stateProvider.state("materialIssue", {
            url: "/materialIssue",
            templateUrl: 'views/PharmacyInventory/InPatient/material-issue.html',
            controller: "MaterialIssue.Controller",
            controllerAs: "vm"
        });
    }

    angular
        .module("myApp")
        .config(config)
        .controller("MaterialIssue.Controller", materialIssueController)
})();