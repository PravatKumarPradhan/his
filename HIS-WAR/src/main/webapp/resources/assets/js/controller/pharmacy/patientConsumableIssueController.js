(function () {
    "use strict";

    function patientConsumableIssueController($scope, $state, $rootScope, $http, CONSTANTS, GenericService, $filter, growl, CurrencyService, commonDetailService, StatusService, cancelReasonService) {
        var self = this;
        var itemId;

        //Default Constructor
        function initializeController() {
            $rootScope.loginpage = true;

            self.model = {
                fromDate: new Date(),
                toDate: new Date(),
                SetFirstTabLevel: 1,
                fromDateOptions: {
                    formatYear: 'yyyy',
                    showWeeks: false,
                    maxDate: new Date()
                },
                toDateOptions: {
                    formatYear: 'yyyy',
                    showWeeks: false,
                    maxDate: new Date()
                },
                statusDataList: [{
                    "status": "All"
                }],
                storeList: [{
                    "store": "All"
                }],
                visitDetails: [{
                    "visitNo": "Select Visit No"
                }],
                SelectedRequestIds: [],

            };


            //Methods
          

            self.model.OpenFromDate = OpenFromDate;
            self.model.OpenToDate = OpenToDate;
            self.model.SearchpatientNames = SearchpatientNames;
            self.model.SearchUhid = SearchUhid;
            self.model.AddPatientDetails = AddPatientDetails;
            self.model.search = '';
            self.model.prevSearch = '';
            self.model.patientNames = [];
            self.model.GetPatientConsumablePendingIndent = GetPatientConsumablePendingIndent;
            self.model.GetPatientConsumableIssueList = GetPatientConsumableIssueList;
            self.model.DetailsPatientPendingIndent = DetailsPatientPendingIndent;
            self.model.DetailsPatientIssueList = DetailsPatientIssueList;

            PopulateDropdown();
            GetPatientConsumablePendingIndent();
            GetPatientConsumableIssueList();

        }

        var OpenFromDate = function () {
            self.model.fromDateOpened = true;
            self.model.toDateOptions.minDate = self.model.fromDate;
        }

        var OpenToDate = function () {
            self.model.toDateOpened = true;
            self.model.toDateOptions.minDate = self.model.fromDate;
        }

        var SearchpatientNames = function (search) {

            if (search.length < 3) return;

            if ((!self.model.prevSearch || self.model.prevSearch != search) && (search.length == 3)) {
                self.model.prevSearch = search;

                var URI = CONSTANTS.GLOBAL.PATIENT_SEARCH_API + search;

                $rootScope.startLoader();
                return GenericService.serviceAction("GET", URI, {})
                    .then(function (response) {
                        $rootScope.stopLoader();
                        if (!!response.data && response.data.length > 0) {
                            self.model.patientNames = response.data;
                            self.model.patientId = response.data.patientId;
                            self.model.uhid = response.data.uhid;
                            $rootScope.stopLoader();
                            //console.log(self.model.patientNames.itemName);
                            return $filter('filter')(self.model.patientNames, {
                                $: search
                            });
                        } else {
                            $rootScope.stopLoader();
                            return PatientNameNotFound(search);
                        }
                    }, function (err) {
                        $rootScope.stopLoader();
                        return PatientNameNotFound(search);
                    });
            } else {
                if (!!self.model.patientNames && self.model.patientNames.length > 0 &&
                    self.model.patientNames[0].itemFound != undefined && !self.model.patientNames[0].itemFound) {
                    return PatientNameNotFound(search);
                } else {
                    return $filter('filter')(self.model.patientNames, {
                        $: search
                    });
                }
            }
        }

        var PatientNameNotFound = function (search) {
            var item = {
                "itemFound": false,
                "details": "Patient Not found with name " + search
            };
            self.model.patientNames = [item];
            return self.model.patientNames;
        }

        var AddPatientDetails = function (item) {

            if (!!item.patientId) {
                var URI = CONSTANTS.GLOBAL.PATIENT_DETAILS_SEARCH_API;

                var data = {
                    "visitType": !!self.model.visitType && !!self.model.visitType.id ? self.model.visitType.id : undefined,
                    "patientId": !!item.patientId ? item.patientId : undefined,
                    // "uhid": !!item.uhid ? item.uhid : undefined,
                };

                $rootScope.startLoader();
                GenericService.serviceAction("POST", URI, data)
                    .then(function (response) {

                            self.model.patientName = response.data.patientName,
                            self.model.patientId = response.data.patientId,
                            self.model.uhid = response.data.uhid,
                            self.model.wardName = response.data.wardName,
                            self.model.wardId = response.data.wardId,
                            self.model.bedNo = response.data.bedNo,
                            self.model.bedId = response.data.bedId,
                            self.model.encounterId = response.data.encounterId,
                            self.model.admissionId = response.data.admissionId,
                            self.model.visitDetails = self.model.visitDetails.concat(response.data.visitDetails);

                        $rootScope.stopLoader();
                    }, function (err) {
                        growl.error('Error while fetching the patient details');
                        $rootScope.stopLoader();
                    });
            }
        }

        var SearchUhid = function (search) {

            if (search.length < 3) return;

            if ((!self.model.prevSearch || self.model.prevSearch != search) && (search.length == 3)) {
                self.model.prevSearch = search;

                var URI = CONSTANTS.GLOBAL.UHID_SEARCH_API + search;

                $rootScope.startLoader();
                return GenericService.serviceAction("GET", URI, {})
                    .then(function (response) {
                        $rootScope.stopLoader();
                        if (!!response.data && response.data.length > 0) {
                            self.model.patientNames = response.data;
                            self.model.patientId = response.data.patientId;
                            self.model.uhid = response.data.uhid;
                            $rootScope.stopLoader();
                            //console.log(self.model.patientNames.itemName);
                            return $filter('filter')(self.model.patientNames, {
                                $: search
                            });
                        } else {
                            $rootScope.stopLoader();
                            return UHIDNotFound(search);
                        }
                    }, function (err) {
                        $rootScope.stopLoader();
                        return UHIDNotFound(search);
                    });
            } else {
                if (!!self.model.patientNames && self.model.patientNames.length > 0 &&
                    self.model.patientNames[0].itemFound != undefined && !self.model.patientNames[0].itemFound) {
                    return UHIDNotFound(search);
                } else {
                    return $filter('filter')(self.model.patientNames, {
                        $: search
                    });
                }
            }
        }

        var UHIDNotFound = function (search) {
            var item = {
                "itemFound": false,
                "details": "UHID Not found with search " + search
            };
            self.model.patientNames = [item];
            return self.model.patientNames;
        }

        var PopulateDropdown = function () {

            var URI = CONSTANTS.PHARMACY.OP.PATIENT_INDENT.DROPDOWN_API;
            // self.model.visitType = self.model.visitTypeDataList[0];
            self.model.status = self.model.statusDataList[0];
            self.model.store = self.model.storeList[0];
            self.model.visitNo = self.model.visitDetails[0];

            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    self.model.visitTypeDataList = response.data.visitType;
                    self.model.visitType = self.model.visitTypeDataList[0];
                    self.model.statusDataList = self.model.statusDataList.concat(response.data.status);
                    self.model.storeList = self.model.storeList.concat(response.data.store);
                    $rootScope.stopLoader();
                });
        }

        var GetPatientConsumablePendingIndent = function () {
            var URI = CONSTANTS.PHARMACY.OP.PATIENT_CONSUMABLE_ISSUE.SEARCH_API;

            var data = {
                "fromDate": moment(self.model.fromDate).format('YYYY-MM-DD'),
                "toDate": moment(self.model.toDate).format('YYYY-MM-DD'),
                "visitType": !!self.model.visitType && !!self.model.visitType.id ? self.model.visitType.id : undefined,
                "patientId": !!self.model.patientId ? self.model.patientId : undefined,
                "patientName": !!self.model.patientName ? self.model.patientName : undefined,
                "indentNo": !!self.model.indentNo ? self.model.indentNo : undefined,
                // "ward": !!self.model.ward && !!self.model.ward.id ? self.model.ward.id : undefined,
                // "floor": !!self.model.floor && !!self.model.floor.id ? self.model.floor.id : undefined,
                "visitNo": !!self.model.visitNo && !!self.model.visitNo.id ? self.model.visitNo.id : undefined,
                "store": !!self.model.store && !!self.model.store.id ? self.model.store.id : undefined,
                "statusId": !!self.model.status && !!self.model.status.id ? self.model.status.id : undefined,
            };

            $rootScope.startLoader();
            GenericService.serviceAction("POST", URI, data).then(
                function (response) {
                    self.model.patientConsumablePendingIndentList = response.data;
                    $rootScope.stopLoader();
                })
        }

        var GetPatientConsumableIssueList = function () {
            var URI = CONSTANTS.PHARMACY.OP.PATIENT_CONSUMABLE_ISSUE.ISSUE_SEARCH_API;

            var data = {
                "fromDate": moment(self.model.fromDate).format('YYYY-MM-DD'),
                "toDate": moment(self.model.toDate).format('YYYY-MM-DD'),
                "visitType": !!self.model.visitType && !!self.model.visitType.id ? self.model.visitType.id : undefined,
                "patientId": !!self.model.patientId ? self.model.patientId : undefined,
                "uhid": !!self.model.uhid ? self.model.uhid : undefined,
                "wardId": !!self.model.ward && !!self.model.ward.id ? self.model.ward.id : undefined,
                "bedId": !!self.model.bed && !!self.model.bed.id ? self.model.bed.id : undefined,
                "issueNo": !!self.model.issueNo && !!self.model.issueNo.id ? self.model.issueNo.id : undefined,
                "storeId": !!self.model.store && !!self.model.store.id ? self.model.store.id : undefined,
                // "statusId": !!self.model.status && !!self.model.status.id ? self.model.status.id : undefined,
            };

            $rootScope.startLoader();
            GenericService.serviceAction("POST", URI, data).then(
                function (response) {
                    self.model.patientConsumableIssueList = response.data;
                    $rootScope.stopLoader();
                })
        }

        var DetailsPatientPendingIndent = function (item) {
            commonDetailService.setDataId(item.id);
            $state.go('detailsPatientPendingIndent');
        }

        var DetailsPatientIssueList = function (item) {
            commonDetailService.setDataId(item.id);
            $state.go('detailsPatientIssueList');
        }

        initializeController();
    }

    function config($stateProvider) {
        $stateProvider.state("patientConsumableIssue", {
            url: '/patientConsumableIssue',
            templateUrl: 'views/PharmacyInventory/InPatient/patient-consumable-Issue.html',
            controller: "PatientConsumableIssue.Controller",
            controllerAs: "vm"
        });
    }

    angular
        .module("myApp")
        .config(config)
        .controller("PatientConsumableIssue.Controller", patientConsumableIssueController)
})();