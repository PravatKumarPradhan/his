(function () {
    "use strict";

    function detailsPatientPendingIndentController($scope, $rootScope, $http, CONSTANTS, GenericService, $filter, $state, growl, CurrencyService, commonDetailService, StatusService, cancelReasonService) {
        var self = this;
        var itemId;

        //Default Constructor
        function initializeController() {
            $rootScope.loginpage = true;

            self.model = {
                carrierList: [{
                    "carrierName": "Select carrier name"
                }],
            };


            //Methods
            StatusService.GetStatus().then(function (status) {
                self.model.statuses = status;
            });

            self.model.batchOnChange = batchOnChange;
            self.model.AddDuplicateRow = AddDuplicateRow;
            self.model.RemoveRowData = RemoveRowData;
            self.model.SavePendingIndent = SavePendingIndent;
            GetDetailsPatientPendingIndent(commonDetailService.getDataId());

        }

        var GetDetailsPatientPendingIndent = function (itemId) {
            self.model.carrier = self.model.carrierList[0];

            var URI = CONSTANTS.PHARMACY.OP.PATIENT_CONSUMABLE_ISSUE.DETAIL_API + itemId;
            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    self.model.patientPendingIndentDetailList = response.data;
                    self.model.carrierList = self.model.carrierList.concat(response.data.carrier);
                    angular.forEach(self.model.patientPendingIndentDetailList.items, function (value, key) {
                        value.batchList = [{
                            batchId: '',
                            expiry: '',
                            uomType: '',
                            uomTypeId: '',
                            uomUnit: '',
                            uomUnitId: '',
                            displayQuantity: 0,
                        }]
                    });
                    $rootScope.stopLoader();
                });
        }

        var batchOnChange = function (batchId, rowBatch, batch) {
            var batchDetails = batch.find(function (obj) {
                return obj.batchId == batchId;
            });
            rowBatch.expiry = batchDetails.expiry;

            var uomDetails = batchDetails.uom.find(function (obj) {
                return obj.ipUom == true;
            });
            if (uomDetails) {
                rowBatch.uomType = uomDetails.uomType;
                rowBatch.uomTypeId = uomDetails.uomTypeId;
                rowBatch.uomUnit = uomDetails.uomUnit;
                rowBatch.uomUnitId = uomDetails.uomUnitId;
                rowBatch.displayQuantity = batchDetails.availableStockQuantity / uomDetails.conversion;
            }
        }

        var AddDuplicateRow = function (index, item) {
            var batch = angular.copy(item.batchList[0]);
            batch.expiry = '';
            batch.uomType = '';
            batch.uomTypeId = '';
            batch.uomUnit = '';
            batch.uomUnitId = '';
            batch.displayQuantity = 0;
            item.batchList.push(batch);
        }

        var DuplicateRowItems = function (batches) {
            batches.batch = '',
                batches.batch.expiry = '';
            batches.batch.uomType = '';
            batches.batch.uomUnit = '';
            batches.batch.displayQuantity = '';
            batches.batch.issueQuantity = '';
        }

        var RemoveRowData = function (item) {
            var index = item.batchList.indexOf(item);
            item.batchList.splice(index, 1);
        }

        var setStatus = function (x) {
            var status = self.model.statuses.find(function (obj) {
                return obj.status == x;
            });

            return status.id;
        }

        var SavePendingIndent = function () {
            var URI = CONSTANTS.PHARMACY.OP.PATIENT_CONSUMABLE_ISSUE.SAVE_API;

            if (!!Validate ()) {
                var data = {
                    "patientIndentId": self.model.patientPendingIndentDetailList.id,
                    "visitType": self.model.patientPendingIndentDetailList.visitTypeId,
                    "admissionId": self.model.patientPendingIndentDetailList.admissionId ? self.model.patientPendingIndentDetailList.admissionId : undefined,
                    "encounterId": self.model.patientPendingIndentDetailList.encounterId ? self.model.patientPendingIndentDetailList.encounterId : undefined,
                    "doctorId": self.model.patientPendingIndentDetailList.doctorId,
                    "storeId": self.model.patientPendingIndentDetailList.storeId,
                    "carrierId": self.model.carrier ? self.model.carrier.id : undefined,
                    "remark": self.model.remark,
                    "statusId": setStatus('Completed'),
                    "items": []
                };

                angular.forEach(self.model.patientPendingIndentDetailList.items, function (item, key) {
                    var items = {
                        "patientIndentDetailId" : item.id,
                        "statusId": setStatus('Completed'),
                        "batchList": []
                    }

                    angular.forEach(item.batchList, function (batch) {
                        var batchList = {
                            "batchId": batch.batchId,
                            "uomTypeId": batch.uomTypeId,
                            "uomUnitId": batch.uomUnitId,
                            "issueQuantity": batch.issueQuantity
                        }
                        items.batchList.push(batchList);
                    });

                    data.items.push(items);
                    // console.log(data);

                });

                if (data.items.length > 0) {
                    $rootScope.startLoader();
                    GenericService.serviceAction("POST", URI, data).then(
                        function (response) {
                            // ClearRequests();
                            $rootScope.stopLoader();
                            NavigteToPatientConsumableIssue();
                            growl.success(response.data.message);
                        },
                        function (err) {
                            $rootScope.stopLoader();
                            growl.error('Something Went Wrong');
                        });
                } else {
                    $rootScope.stopLoader();
                    NavigteToPatientConsumableIssue();
                }
            }
        }

        var Validate = function () {
            var isValid = true;

            if (!self.model.patientPendingIndentDetailList.items || self.model.patientPendingIndentDetailList.items.length <= 0) {
                isValid = false;
                growl.error("Please add some items in the list");
            } else {
                self.model.patientPendingIndentDetailList.items.forEach(function (item) {

                    item['isCarrierValid'] = false;                    

                    if (!self.model.carrier.id) {
                        item['isCarrierValid'] = true;
                        isValid = false;
                        growl.error("Please select carrier");
                    }

                    item.batchList.forEach(function (batch) {
                        batch['isBatchValid'] = false;
                        batch['isIssueQuantityValid'] = false;

                        if (!batch.batchId) {
                            batch['isBatchValid'] = true;
                            isValid = false;
                            growl.error("Please select batch");
                        }

                    if (((!batch.issueQuantity) > (batch.displayQuantity)) ||(!batch.issueQuantity) || (batch.issueQuantity == 0)) {
                        isValid = false;
                        batch['isIssueQuantityValid'] = true;
                        growl.error("Please enter issue quantity");
                    }

                });
            });
            }

            return isValid;
        }

        var NavigteToPatientConsumableIssue = function () {
            $state.go('patientConsumableIssue');
        }

        initializeController();
    }

    function config($stateProvider) {
        $stateProvider.state("detailsPatientPendingIndent", {
            url: '/detailsPatientPendingIndent',
            templateUrl: 'views/PharmacyInventory/InPatient/details-patient-pending-Indent.html',
            controller: "DetailsPatientPendingIndent.Controller",
            controllerAs: "vm"
        });
    }

    angular
        .module("myApp")
        .config(config)
        .controller("DetailsPatientPendingIndent.Controller", detailsPatientPendingIndentController)
})();