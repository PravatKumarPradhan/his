(function () {
    "use strict";

    function detailsPatientIssueListController($scope, $rootScope, $http, CONSTANTS, GenericService, $filter, $state, growl, CurrencyService, commonDetailService, StatusService, cancelReasonService) {
        var self = this;
        var itemId;

        //Default Constructor
        function initializeController() {
            $rootScope.loginpage = true;

            self.model = {};


            //Methods
            self.model.batchOnChange = batchOnChange;
            self.model.RemoveRowData = RemoveRowData;
            GetDetailsPatientIssueList(commonDetailService.getDataId());

        }

        var GetDetailsPatientIssueList = function (itemId) {

            var URI = CONSTANTS.PHARMACY.OP.PATIENT_CONSUMABLE_ISSUE.ISSUE_DETAIL_API + itemId;
            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    self.model.patientIssueDetailList = response.data;
                    $rootScope.stopLoader();
                });
        }

        var batchOnChange = function (selectedBatch, batch) {
            batch.expiry = selectedBatch.expiry;
            //batch.availableStockQuantity = selectedBatch.availableStockQuantity;
            // batch.uomList = selectedBatch.uom;

            // batch.displayQuantity = batch.availableStockQuantity / 
            // (batch.conversion ? batch.conversion : 1);
            var ipUom = selectedBatch.uom.find(function (obj) {
                return obj.ipUom == true;
            });
            batch.uomType = ipUom.uomType;
            batch.uomTypeId = ipUom.uomTypeId;
            batch.uomUnit = ipUom.uomUnit;
            batch.uomUnitId = ipUom.uomUnitId;
            batch.displayQuantity = selectedBatch.availableStockQuantity / ipUom.conversion;
        }

        var RemoveRowData = function (item) {
            var index = item.batches.indexOf(item);
            item.batches.splice(index, 1);
        }

        var NavigteToMaterialPending = function () {
            $state.go('materialIssue.pendingIssue');
        }

        initializeController();
    }

    function config($stateProvider) {
        $stateProvider.state("detailsPatientIssueList", {
            url: '/detailsPatientIssueList',
            templateUrl: 'views/PharmacyInventory/InPatient/details-patient-issue-list.html',
            controller: "DetailsPatientIssueList.Controller",
            controllerAs: "vm"
        });
    }

    angular
        .module("myApp")
        .config(config)
        .controller("DetailsPatientIssueList.Controller", detailsPatientIssueListController)
})();
