

(function () {
    "use strict";

    function detailsPatientIssueReturnComsumableController($scope, $rootScope, $http, CONSTANTS, GenericService, $filter, $state, growl, detailService) {
        var self = this;
        var selectedItemId;

        //Default Constructor
        function initializeController() {
            $rootScope.loginpage = true;

            self.model = {};

            //Methods
            self.model.GetPatientIssueReturnConsumableDetails = GetPatientIssueReturnConsumableDetails;
        

            GetPatientIssueReturnConsumableDetails(detailService.getId());
        }

        var GetPatientIssueReturnConsumableDetails = function (itemId) {

            var URI = CONSTANTS.PHARMACY.NURSING.PATIENT_ISSUE_RETURN.MEDICATION_DETAIL_API + itemId;

            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {

                    self.model.patientIssueReturnConsumableDetailList = response.data;

                });
            $rootScope.stopLoader();
        }

        initializeController();
    }

    function config($stateProvider) {
        $stateProvider.state("detailsPatientIssueReturnComsumable", {
            url: "/detailsPatientIssueReturnComsumable",
            templateUrl: 'views/PharmacyInventory/InPatient/details-patient-issue-consumable.html',
            controller: "DetailsPatientIssueReturnComsumable.Controller",
            controllerAs: "vm"
        });
    }

    angular
        .module("myApp")
        .config(config)
        .controller("DetailsPatientIssueReturnComsumable.Controller", detailsPatientIssueReturnComsumableController)
})();

