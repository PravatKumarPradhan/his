(function () {
    "use strict";

    function patientIssueAcceptanceController($scope, $rootScope, $http, CONSTANTS, GenericService, $filter, $state, growl, CurrencyService, detailService) {
        var self = this;

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
                floorList: [{
                    "floor": "All"
                }],
                wardList: [{
                    "wardName": "All"
                }],
                storeList: [{
                    "store": "All"
                }],
                visitDetails: [{
                    "visitNo": "Select Visit No"
                }],
                isConsignment: false,
            };


            //Methods
            CurrencyService.GetCurrency().then(function (currency) {
                self.model.Currency = currency;
            })
            self.model.OpenFromDate = OpenFromDate;
            self.model.OpenToDate = OpenToDate;
            self.model.SearchpatientNames = SearchpatientNames;
            self.model.SearchUhid = SearchUhid;
            self.model.AddPatientDetails = AddPatientDetails;
            self.model.SearchdoctorNames = SearchdoctorNames;
            self.model.search = '';
            self.model.prevSearch = '';
            self.model.patientNames = [];
            self.model.GetMedicationIssueAcceptance = GetMedicationIssueAcceptance;
            self.model.GetConsumableIssueAcceptance = GetConsumableIssueAcceptance;
            self.model.NavigateToDetail = NavigateToDetail;
            self.model.isConsignmentFlag = isConsignmentFlag;

            PopulateDropdown();
            GetMedicationIssueAcceptance();
            GetConsumableIssueAcceptance();
        }

        var OpenFromDate = function () {
            self.model.fromDateOpened = true;
            self.model.toDateOptions.minDate = self.model.fromDate;
        }

        var OpenToDate = function () {
            self.model.toDateOpened = true;
            self.model.toDateOptions.minDate = self.model.fromDate;
        }

        var PopulateDropdown = function () {

            var URI = CONSTANTS.GLOBAL.PATIENT_ISSUE_DROPDOWN_API;
                   
            self.model.visitNumber = self.model.visitDetails[0];
            self.model.store = self.model.storeList[0];
            self.model.floor = self.model.floorList[0];
            self.model.ward = self.model.wardList[0];

            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    self.model.visitTypeList = response.data.visitType;
                    self.model.visitType = self.model.visitTypeList[0];
                    self.model.storeList = self.model.storeList.concat(response.data.store);
                    self.model.floorList = self.model.floorList.concat(response.data.floor);
                    self.model.wardList = self.model.wardList.concat(response.data.ward);
                    $rootScope.stopLoader();
                });
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
                            self.model.bedNo = response.data.bedNo,
                            self.model.bedId = response.data.bedId,
                            self.model.encounterId = response.data.encounterId,
                            self.model.admissionId = response.data.admissionId,
                            self.model.visitDetails = self.model.visitDetails.concat(response.data.visitDetails);
                            self.model.ward = self.model.wardList.find(function (ward) {
                            return response.data.wardId == ward.id;
                        });
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

        var SearchdoctorNames = function (search) {

            if (search.length < 3) return;

            if ((!self.model.prevSearch || self.model.prevSearch != search) && (search.length == 3)) {
                self.model.prevSearch = search;
                var URI = CONSTANTS.GLOBAL.DOCTOR_SEARCH_API + search;

                $rootScope.startLoader();
                return GenericService.serviceAction("GET", URI, {})
                    .then(function (response) {
                        $rootScope.stopLoader();
                        if (!!response.data && response.data.length > 0) {
                            self.model.doctorNames = response.data;
                            $rootScope.stopLoader();
                            return $filter('filter')(self.model.doctorNames, {
                                $: search
                            });
                        } else {
                            $rootScope.stopLoader();
                            return DoctorNameNotFound(search);

                        }
                    }, function (err) {
                        $rootScope.stopLoader();
                        return DoctorNameNotFound(search);
                    });
            } else {
                if (!!self.model.doctorNames && self.model.doctorNames.length > 0 &&
                    self.model.doctorNames[0].itemFound != undefined && !self.model.doctorNames[0].itemFound) {
                    return DoctorNameNotFound(search);
                } else {
                    return $filter('filter')(self.model.doctorNames, {
                        $: search
                    });
                }
            }
        }

        var DoctorNameNotFound = function (search) {
            var item = {
                "itemFound": false,
                "details": "Doctor Not found with name " + search
            };
            self.model.patientNames = [item];
            return self.model.patientNames;
        }  

        var GetMedicationIssueAcceptance = function () {
            var URI = CONSTANTS.PHARMACY.OP.PATIENT_ISSUE_ACCEPTANCE.MEDICATION_SEARCH_API;

            var data = {
                "fromDate": moment(self.model.fromDate).format('YYYY-MM-DD'),
                "toDate": moment(self.model.toDate).format('YYYY-MM-DD'),
                "patientId": !!self.model.patientId ? self.model.patientId : undefined,
                "patientName": !!self.model.patientName ? self.model.patientName : undefined,
                "uhid": !!self.model.uhid ? self.model.uhid : undefined,
                "doctorName": !!self.model.doctorName ? self.model.doctorName : undefined,
                "visitType": !!self.model.visitType && !!self.model.visitType.id ? self.model.visitType.id : undefined,
                "wardId": !!self.model.ward.id ? self.model.ward.id : undefined,
                "floor": !!self.model.floor && !!self.model.floor.id ? self.model.floor.id : undefined,
                "issueNo": !!self.model.issueNo ? self.model.issueNo : undefined,
            };

            $rootScope.startLoader();
            GenericService.serviceAction("POST", URI, data).then(
                function (response) {
                    self.model.medicationIssueAcceptancelist = response.data;
                })
            $rootScope.stopLoader();
        }

        var isConsignmentFlag = function (flag) {
            if (flag) {
                self.model.isConsignment = true;
            } else {
                self.model.isConsignment = false;
            }
        }

        var GetConsumableIssueAcceptance = function () {
            var URI = CONSTANTS.PHARMACY.OP.PATIENT_ISSUE_ACCEPTANCE.CONSUMABLE_SEARCH_API;

            var data = {
                "fromDate": moment(self.model.fromDate).format('YYYY-MM-DD'),
                "toDate": moment(self.model.toDate).format('YYYY-MM-DD'),
                "visitType": !!self.model.visitType && !!self.model.visitType.id ? self.model.visitType.id : undefined,
                "patientName": !!self.model.patientName ? self.model.patientName : undefined,
                "patientId": !!self.model.patientId ? self.model.patientId : undefined,
                "uhid": !!self.model.uhid ? self.model.uhid : undefined,
                "wardId": !!self.model.ward.id ? self.model.ward.id : undefined,
                "visitNo": !!self.model.visitNumber && !!self.model.visitNumber.visitNo ? self.model.visitNumber.visitNo : undefined,
                "issueNo": !!self.model.issueNo ? self.model.issueNo : undefined,
                "storeId": !!self.model.store && !!self.model.store.id ? self.model.store.id : undefined,
                "consignment": self.model.isConsignment,

            };

            $rootScope.startLoader();
            GenericService.serviceAction("POST", URI, data).then(
                function (response) {
                    self.model.consumableIssueAcceptancelist = response.data;
                })
            $rootScope.stopLoader();
        }

        var NavigateToDetail = function (item, tab) {
            detailService.setId(item.id);

            if (tab == 2) {
                $state.go('detailsConsumablePatientIssueAcceptance');
            } else {
                $state.go('detailsPatientIssueAcceptance');
            }

        }


        initializeController();
    }

    function config($stateProvider) {
        $stateProvider.state("patientIssueAcceptance", {
            url: '/patientIssueAcceptance',
            templateUrl: 'views/PharmacyInventory/InPatient/patient-issue-acceptance.html',
            controller: "PatientIssueAcceptance.Controller",
            controllerAs: "vm"
        });
    }

    angular
        .module("myApp")
        .config(config)
        .controller("PatientIssueAcceptance.Controller", patientIssueAcceptanceController)
})();