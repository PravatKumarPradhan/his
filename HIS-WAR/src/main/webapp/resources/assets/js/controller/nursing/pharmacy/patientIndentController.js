(function () {
    "use strict";

    function patientIndentController($scope, $rootScope, $http, CONSTANTS, GenericService, $filter, $state, growl, CurrencyService, commonDetailService, StatusService, cancelReasonService) {
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
                floorList: [{
                    "floor": "All"
                }],
                wardList: [{
                    "wardName": "All"
                }],
                storeList: [{
                    "store": "All"
                }],
                SelectedRequestIds: [],
                
            };


            //Methods

            self.model.OpenFromDate = OpenFromDate;
            self.model.OpenToDate = OpenToDate;
            self.model.SearchpatientNames = SearchpatientNames;
            self.model.SearchdoctorNames = SearchdoctorNames;
            self.model.AddUHID = AddUHID;
            self.model.search = '';
            self.model.prevSearch = '';
            self.model.patientNames = [];
            self.model.GetPatientConsumableIndent = GetPatientConsumableIndent;
            self.model.PatientConsumableIndentDetails = PatientConsumableIndentDetails;
            self.model.EditPatientConsumableIndentDetails = EditPatientConsumableIndentDetails;
            self.model.OpenPopUp = OpenPopUp;
            self.model.isDisable = isDisable;
            self.model.SelectItem = SelectItem;
            self.model.SelectAllRequests = SelectAllRequests;
            self.model.ApproveItem = ApproveItem;
            self.model.isEdit = isEdit;
            self.model.isCancel = isCancel;

            PopulateDropdown();
            GetRejectReasonList();
            GetPatientConsumableIndent();

            CurrencyService.GetCurrency().then(function (currency) {
                self.model.Currency = currency;
            })

            StatusService.GetStatus().then(function (status) {
                self.model.statuselect = status;
            });

            cancelReasonService.GetReason().then(function (reason) {
                self.model.reasons = reason;
            });

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

        var PopulateDropdown = function () {

            var URI = CONSTANTS.PHARMACY.OP.PATIENT_INDENT.DROPDOWN_API;
            
            self.model.status = self.model.statusDataList[0];
            self.model.floor = self.model.floorList[0];
            self.model.ward = self.model.wardList[0];
            self.model.store = self.model.storeList[0];


            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    self.model.visitTypeDataList = response.data.visitType;
                    self.model.visitType = self.model.visitTypeDataList[0];
                    self.model.statusDataList = self.model.statusDataList.concat(response.data.status);
                    self.model.floorList = self.model.floorList.concat(response.data.floor);
                    self.model.wardList = self.model.wardList.concat(response.data.ward);
                    self.model.storeList = self.model.storeList.concat(response.data.store);
                    $rootScope.stopLoader();
                });
        }

        var GetPatientConsumableIndent = function () {
            var URI = CONSTANTS.PHARMACY.OP.PATIENT_INDENT.SEARCH_API;

            var data = {
                "fromDate": moment(self.model.fromDate).format('YYYY-MM-DD'),
                "toDate": moment(self.model.toDate).format('YYYY-MM-DD'),
                "visitType": !!self.model.visitType && !!self.model.visitType.id ? self.model.visitType.id : undefined,
                "patientName": !!self.model.patientName ? self.model.patientName : undefined,
                "uhid": !!self.model.uhid ? self.model.uhid : undefined,
                "doctorName": !!self.model.doctorName ? self.model.doctorName : undefined,
                "ward": !!self.model.ward && !!self.model.ward.id ? self.model.ward.id : undefined,
                "floor": !!self.model.floor && !!self.model.floor.id ? self.model.floor.id : undefined,
                "visitNo": !!self.model.visitNo ? self.model.visitNo : undefined,
                "store": !!self.model.store && !!self.model.store.id ? self.model.store.id : undefined,
                "statusId": !!self.model.status && !!self.model.status.id ? self.model.status.id : undefined,
            };

            $rootScope.startLoader();
            GenericService.serviceAction("POST", URI, data).then(
                function (response) {
                    self.model.patientConsumableIndentlist = response.data;
                $rootScope.stopLoader();                    
                })
        }

        var PatientConsumableIndentDetails = function (item) {
            commonDetailService.setDataId(item.id);
            $state.go('deatilsPatientIndent');
        }

        var EditPatientConsumableIndentDetails = function (item) {
            commonDetailService.setDataId(item.id);
            $state.go('consumableIndent');
        }

        var OpenPopUp = function (action, popupName, id) {
            itemId = id;
            self.model.action = action;
            var popup = angular.element('#' + popupName);
            popup.modal('show');
        }

        var GetRejectReasonList = function () {
            var URI = CONSTANTS.GLOBAL.CANCEL_REASON_API;
            var data = {};
            $rootScope.startLoader()
            GenericService.serviceAction("GET", URI, data).then(
                function (response) {
                    $rootScope.stopLoader();
                    self.model.rejectReasonList = response.data;
                });
        }

        var SelectItem = function (Item) {

            var itemIds = self.model.SelectedRequestIds;

            if (Item.isSelected) {
                itemIds.push(Item.id);
            } else {
                var index = itemIds.indexOf(Item);
                if (index > -1) {
                    itemIds.splice(index, 1);
                }
            }

            if (self.model.patientConsumableIndentlist.every(isSelected)) {
                self.model.selectAllRows = true;
            } else {
                self.model.selectAllRows = false;
            }

        }

        var SelectAllRequests = function () {
            if (self.model.selectAllRows) {
                self.model.SelectedRequestIds = [];
                self.model.patientConsumableIndentlist.forEach(function (indent) {
                    if (!isDisable(indent)) {
                        indent.isSelected = true;
                        self.model.SelectedRequestIds.push(indent.id);
                    }
                });
            } else {
                self.model.SelectedRequestIds = [];
                self.model.patientConsumableIndentlist.forEach(function (indent) {
                    indent.isSelected = false;
                });
            }
        }

        var isDisable = function (item) {
            if (item.status != 'New') {
                return true;
            }
            return false;
        }

        var isEdit = function (item) {
            if (item.status == 'Pending') {
                return true;
            } else if (item.status == 'New') {
                return false;
            } else if (item.status == 'Closed' || item.status == 'Cancelled') {
                return true;
            }
        }

        var isCancel = function (item) {
            if (item.status == 'Pending') {
                return false;
            } else if (item.status == 'New') {
                return false;
            } else if (item.status == 'Closed' || item.status == 'Cancelled') {
                return true;
            }
        }

        var isSelected = function (indent) {
            if (!isDisable(indent))
                return indent.isSelected;
            else
                return true;
        }

        var SetStatus = function (x) {
            var status = self.model.statuselect.find(function (obj) {
                return obj.status == x;
            });

            return status.id;
        }

        var ApproveItem = function () {
            if (self.model.action == 'Update') {
                var URI = CONSTANTS.PHARMACY.OP.PATIENT_INDENT.APPROVE_API;

                var data = {
                    "indentId": self.model.SelectedRequestIds,
                    "statusId": SetStatus('Pending'),
                    "approvalStatusId": SetStatus('Pending Approval')
                };

                $rootScope.startLoader();
                GenericService.serviceAction("PATCH", URI, data).then(
                    function (response) {
                        self.model.SelectedRequestIds = [];
                        $rootScope.stopLoader();
                        growl.success(response.data.message);
                        GetPatientConsumableIndent();
                    },
                    function (err) {
                        $rootScope.stopLoader();
                        // growl.error(err.data.msg);
                    });
            } else if (self.model.action == 'Cancel') {

                var URI = CONSTANTS.PHARMACY.OP.PATIENT_INDENT.CANCEL_API + itemId;

                var data = {
                    "cancelReasonId": self.model.cancelReason,
                    "cancelNote": self.model.cancelNote,
                    "statusId": SetStatus('Cancelled')
                };

                GenericService.serviceAction("PATCH", URI, data).then(
                    function (response) {
                        self.model.SelectedRequestIds = [];
                        $rootScope.stopLoader();
                        growl.success(response.data.message);
                        GetPatientConsumableIndent();
                    },
                    function (err) {
                        $rootScope.stopLoader();
                        // growl.error(err.data.msg);
                    });
            }

        }

        initializeController();
    }

    function config($stateProvider) {
        $stateProvider.state("patientIndent", {
            url: '/patientIndent',
            templateUrl: 'views/PharmacyInventory/InPatient/patient-indent.html',
            controller: "PatientIndent.Controller",
            controllerAs: "vm"
        });
    }

    angular
        .module("myApp")
        .config(config)
        .controller("PatientIndent.Controller", patientIndentController)
})();