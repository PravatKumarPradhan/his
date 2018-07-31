
(function () {
    "use strict";

    function detailsPatientIssueReturnMedicationController($scope, $rootScope, $http, CONSTANTS, GenericService, $filter, $state, growl, detailService) {
        var self = this;
        var selectedItemId;

        //Default Constructor
        function initializeController() {
            $rootScope.loginpage = true;

            self.model = {};

            //Methods
            self.model.GetPatientIssueReturnMedicationDetails = GetPatientIssueReturnMedicationDetails;
        

            GetPatientIssueReturnMedicationDetails(detailService.getId());
        }

        var GetPatientIssueReturnMedicationDetails = function (itemId) {

            var URI = CONSTANTS.PHARMACY.NURSING.PATIENT_ISSUE_RETURN.MEDICATION_DETAIL_API + itemId;

            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {

                    self.model.patientIssueReturnMedicationDetaillist = response.data;

                });
            $rootScope.stopLoader();
        }

        initializeController();
    }

    function config($stateProvider) {
        $stateProvider.state("detailsPatientIssueReturnMedication", {
            url: "/detailsPatientIssueReturnMedication",
            templateUrl: 'views/PharmacyInventory/InPatient/details-patient-issue-medication.html',
            controller: "DetailsPatientIssueReturnMedication.Controller",
            controllerAs: "vm"
        });
    }

    angular
        .module("myApp")
        .config(config)
        .controller("DetailsPatientIssueReturnMedication.Controller", detailsPatientIssueReturnMedicationController)
})();
