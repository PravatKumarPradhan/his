(function () {
    "use strict";

    function pendingIssuesController($scope, $rootScope, $http, CONSTANTS, GenericService, $filter, $state, growl, CurrencyService, commonDetailService) {
        var self = this;
      
        //Default Constructor
        function initializeController() {
            $rootScope.loginpage = true;

            self.model = {
                fromDate: new Date(),
                toDate: new Date(),
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
                visitTypeDataList: [{
                    "type": "All"
                }],
                statusDataList: [{
                    "status": "All"
                }],
            };


            //Methods
            CurrencyService.GetCurrency().then(function (currency) {
                self.model.Currency = currency;
            })
            self.model.OpenFromDate = OpenFromDate;
            self.model.OpenToDate = OpenToDate;
            self.model.SearchpatientNames = SearchpatientNames;
            self.model.SearchdoctorNames = SearchdoctorNames;
            self.model.AddUHID = AddUHID;
            self.model.search = '';
            self.model.prevSearch = '';
            self.model.patientNames = [];
            self.model.GetPendingIssues = GetPendingIssues;
            self.model.NavigateToDetail = NavigateToDetail;

            PopulateDropdown();
            GetPendingIssues();
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

        var AddUHID = function (item) {
            self.model.patientData = item;
            self.model.uhid = item.uhid;
            self.model.patientId = item.id;
            //   self.model.doctorId = item.id;
        }

        var PatientNameNotFound = function (search) {
            var item = {
                "itemFound": false,
                "details": "Patient Not found with name " + search
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
                            return DoctorNotFound(search);

                        }
                    }, function (err) {
                        $rootScope.stopLoader();
                        return DoctorNotFound(search);
                    });
            } else {
                if (!!self.model.doctorNames && self.model.doctorNames.length > 0 &&
                    self.model.doctorNames[0].itemFound != undefined && !self.model.doctorNames[0].itemFound) {
                    return DoctorNotFound(search);
                } else {
                    return $filter('filter')(self.model.doctorNames, {
                        $: search
                    });
                }
            }
        }

        var DoctorNotFound = function (search) {
            var item = {
                "itemFound": false,
                "details": "DOCTOR Not found with name " + search
            };
            self.model.patientNames = [item];
            return self.model.patientNames;
        }

        var PopulateDropdown = function () {
            var URI = CONSTANTS.PHARMACY.OP.SALES_WORKLIST.DROPDOWN_API;
            self.model.visitType = self.model.visitTypeDataList[0];
            self.model.status = self.model.statusDataList[0];

            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {

                    self.model.visitTypeDataList = self.model.visitTypeDataList.concat(response.data.visitType);
                    self.model.statusDataList = self.model.statusDataList.concat(response.data.status);

                    $rootScope.stopLoader();
                });
        }

        var GetPendingIssues = function () {
            var URI = CONSTANTS.PHARMACY.IP.PENDING_ISSUES.SEARCH_API;

            var data = {
                "fromDate": moment(self.model.fromDate).format('YYYY-MM-DD'),
                "toDate": moment(self.model.toDate).format('YYYY-MM-DD'),
                "visitTypeId": !!self.model.visitType && !!self.model.visitType.id ? self.model.visitType.id : undefined,
                "patientName": !!self.model.patientName ? self.model.patientName : undefined,
                "doctorName": !!self.model.doctorName ? self.model.doctorName : undefined,
                "wardName": !!self.model.wardName ? self.model.wardName : undefined,
                "statusId": !!self.model.status && !!self.model.status.id ? self.model.status.id : undefined,
            };

            $rootScope.startLoader();
            GenericService.serviceAction("POST", URI, data).then(
                function (response) {
                    self.model.pendingIssueslist = response.data;
                })
            $rootScope.stopLoader();
        }

        var NavigateToDetail = function (item) {
            commonDetailService.setDataId(item.id);
            $state.go('pendingIssuesDetails');
        }
        initializeController();
    }

    function config($stateProvider) {
        $stateProvider.state("pendingIssues", {
            url: "/pendingIssues",
            templateUrl: 'views/PharmacyInventory/InPatient/pending-issues.html',
            controller: "PendingIssues.Controller",
            controllerAs: "vm"
        });
    }

    angular
        .module("myApp")
        .config(config)
        .controller("PendingIssues.Controller", pendingIssuesController)
})();