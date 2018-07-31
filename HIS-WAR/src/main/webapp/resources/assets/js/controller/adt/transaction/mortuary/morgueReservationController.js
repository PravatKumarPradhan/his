'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:morgueReservationController
 * @description #morgueReservationController Controller of the myApp
 */
angular
    .module('myApp')
    .controller(
        'morgueReservationController', [
            '$scope',
            '$http',
            '$localStorage',
            '$sessionStorage',
            '$cookies',
            '$rootScope',
            'GenericService',
            'BillingGenericService',
            'growl',
            '$state',
            '$stateParams',
            function($scope, $http, $localStorage, $sessionStorage,
                $cookies, $rootScope, GenericService, BillingGenericService, growl,
                $state, $stateParams) {

                /* init() function for form object create. */
                $scope.init = function() {

                    //for datepicker 1
                    $scope.dateOptions = {
                        formatYear: 'yyyy',
                        showWeeks: false
                    };

                    $scope.openDatePicker = function() {
                        $scope.datepicker.opened = true;
                    };

                    $scope.datepicker = {
                        opened: false
                    };




                    var cookieObject = $cookies.getObject('cookieObject');
                    if (cookieObject == undefined) {
                        $state.go('login');
                        return;
                    }
                    $scope.unitId = cookieObject.unitId;
                    $scope.organizationId = cookieObject.organizationId;
                    $scope.userId = 1;


                    $rootScope.loginpage = true;
                    $scope.saveBtnFlag = false;
                    $scope.updateBtnFlag = true;



                    // for popup
                    $scope.showModal = false;
                    $scope.buttonClicked = "";
                    $scope.popUpFlag = true;
                    // end for popup

                    $scope.kinDetails = {}
                    $scope.itemSelecteds = {};
                    $scope.itemKinSelecteds = {};

                    $scope.kinDetails = {
                        prefixId: '',
                        kinName: '',
                        relationId: '',
                        mobileNo: '',
                        phoneNo: '',
                        expiry: '',
                        identificationId: '',
                        identificationNo: '',
                        isGuarantor: 'N',
                        address: '',
                        countryId: '',
                        stateId: '',
                        districtId: '',
                        cityId: '',
                        areaId: '',
                        postCode: ''

                    };


                    $scope.itemSelectedsReject = {};


                    $scope.deathObject = {
                        durationFormat: '',
                        durationValue: '',
                        organizationId: $scope.organizationId,
                        unitId: $scope.unitId,
                        createdBy: $scope.userId,
                        updatedBy: $scope.userId,
                        status: 'A',
                        mortuaryRequestId: '',
                        mortuaryBedId: '',
                        mortuaryStatusId: 3,
                        bedStatusId: 5,
                        admissionKinId: ''
                    };



                    var commonObj = {
                        unitId: $scope.unitId,
                        organizationId: $scope.organizationId
                    };
                    var data = {};

                    var URI = BASE_URL + ROOT_URL + GETACTIVEREASONLIST;
                    GenericService
                        .serviceAction("GET", URI, data)
                        .then(
                            function(response) {
                                $scope.reasonList = [];

                                if (response.data.status == "success") {
                                    $scope.reasonList = response.data.listObject;

                                }
                            });

                    var URI = BASE_URL + ROOT_URL + GETRESERVEMORTUARYREQUESTLIST;
                    GenericService
                        .serviceAction("POST", URI, commonObj)
                        .then(
                            function(response) {
                                $scope.mortuaryReserveList = [];

                                if (response.data.status == "success") {
                                    $scope.mortuaryReserveList = response.data.listObject;

                                }
                            });


                    var URI = BASE_URL + ROOT_URL + GETACTIVEPREFIXLIST;
                    GenericService
                        .serviceAction("GET", URI, data)
                        .then(
                            function(response) {
                                $scope.prefixList = [];

                                if (response.data.status == "success") {
                                    $scope.prefixList = response.data.listObject;

                                }
                            });
                    var URI = BASE_URL + ROOT_URL + GETACTIVERELATIONLIST;
                    GenericService
                        .serviceAction("GET", URI, data)
                        .then(
                            function(response) {
                                $scope.relationList = [];

                                if (response.data.status == "success") {
                                    $scope.relationList = response.data.listObject;

                                }
                            });
                    var URI = BASE_URL + ROOT_URL + GETACTIVEIDENTIFICATION;
                    GenericService
                        .serviceAction("GET", URI, data)
                        .then(
                            function(response) {
                                $scope.identificationList = [];

                                if (response.data.status == "success") {
                                    $scope.identificationList = response.data.listObject;

                                }
                            });
                    var URI = BASE_URL + ROOT_URL + GETACTIVECOUNTRYLIST;
                    GenericService
                        .serviceAction("GET", URI, data)
                        .then(
                            function(response) {
                                $scope.countryList = [];

                                if (response.data.status == "success") {
                                    $scope.countryList = response.data.listObject;
                                    console
                                        .log($scope.countryList);

                                }
                            });

                }

                $scope.init();

                // for get state by country ID
                $scope.getStateByCountry = function(id) {
                    var data = {
                        countryId: id
                    };
                    var URI = BASE_URL + ROOT_URL + GETSTATELISTBYCOUNTRYID;
                    GenericService
                        .serviceAction("POST", URI, data)
                        .then(
                            function(response) {
                                $scope.stateList = [];

                                if (response.data.status == "success") {
                                    $scope.stateList = response.data.listObject;
                                    console
                                        .log($scope.stateList);

                                }
                            });

                }

                $scope.getDistrictByState = function(id) {
                    var data = {
                        stateId: id
                    };
                    var URI = BASE_URL + ROOT_URL + GETDISTRICTLISTBYSTATEID;
                    GenericService
                        .serviceAction("POST", URI, data)
                        .then(
                            function(response) {
                                $scope.districtList = [];

                                if (response.data.status == "success") {
                                    $scope.districtList = response.data.listObject;
                                    console
                                        .log($scope.districtList);

                                }
                            });

                }

                $scope.getCityByDistrict = function(id) {

                    var data = {
                        districtId: id
                    };
                    var URI = BASE_URL + ROOT_URL + GETCITYLISTBYDISTRICTID;
                    GenericService
                        .serviceAction("POST", URI, data)
                        .then(
                            function(response) {
                                $scope.cityList = [];

                                if (response.data.status == "success") {
                                    $scope.cityList = response.data.listObject;
                                    console
                                        .log($scope.cityList);

                                }
                            });

                }
                $scope.getAreaByCity = function(id) {

                    var data = {
                        cityId: id
                    };
                    var URI = BASE_URL + ROOT_URL + GETAREALISTBYCITY;
                    GenericService
                        .serviceAction("POST", URI, data)
                        .then(
                            function(response) {
                                $scope.areaList = [];

                                if (response.data.status == "success") {
                                    $scope.areaList = response.data.listObject;
                                }
                            });

                }

                // function for popup ok button call
                $scope.PopupOkBtn = function() {
                    $scope.popUpFlag = false;
                    $scope.saveKinDetails();
                }

                $scope.selectItem = function(item) {

                    $scope.itemKinSelecteds = [];
                    // If checkbox is checked
                    if ($scope.kinDetails) {
                        $scope.itemKinSelecteds = item;
                    }
                    console.log($scope.itemKinSelecteds);
                }

                $scope.patientDetails = function(info) {
                    console.log("TEST");
                    console.log(info);
                    $scope.itemSelecteds = [];
                    $scope.itemSelecteds = info;

                    $scope.patientName = info.patientName;
                    $scope.uhidNumber = info.uhidNumber;
                    $scope.bedNumber = info.mortuaryBedNumber;

                    $scope.deathObject.durationFormat = info.durationFormat;
                    if (info.durationFormat == '1') {
                        if (info.durationValue != null)
                            $scope.timeValueOfDuration = info.durationValue.toString();
                    } else {
                        if (info.durationValue != null)
                            $scope.timeValueOfDay = info.durationValue.toString();
                    }
                    $scope.deathObject.mortuaryRequestId = info.mortuaryRequestId;
                    $scope.deathObject.mortuaryBedId = info.mortuaryBedId;


                }

                $scope.nextOfKinDetails = function(info) {
                    var data = {
                        patientId: info.patientId,
                        tPatientId: info.tPatientid,
                        dPatientId: info.dPatientId,
                        organizationId: $scope.organizationId,
                        unitId: $scope.unitId
                    };
                    $scope.itemSelecteds = [];
                    $scope.itemSelecteds = info;

                    var URI = BASE_URL + ROOT_URL + GETNEXTOFKINLISTBYPATIENTID;
                    GenericService
                        .serviceAction("POST", URI, data)
                        .then(
                            function(response) {
                                $scope.kinDetailsList = [];

                                if (response.data.status == "success") {
                                    $scope.kinDetailsList = response.data.listObject;

                                }
                            });

                }


                $scope.updateKinDetailsStatus = function(info) {
                    $rootScope.startLoader();
                    var kinDetails = "";
                    var URI = BASE_URL + ROOT_URL + KIN_DETAILS + SLASH + kinDetails + SLASH + info.kinDetailsId + STATUS + SLASH + 'I';
                    BillingGenericService.serviceAction(METHOD_PUT, URI, "", NOTIFICATION_MSG_STATUS_FALSE).then(function(response) {
                        console.log(response);
                        if (response.data.status == "success") {
                            $scope.nextOfKinDetails(info);
                        }
                    });
                }


                $scope.allotMorgueBed = function() {

                    if (Object.keys($scope.itemKinSelecteds).length === 0) {
                        growl
                            .error(
                                'Please select atleast one Kin!!!.', {
                                    title: 'Error!'
                                });
                    } else {
                        $scope.currentDate = new Date();
                        $scope.currentDate = moment($scope.currentDate)
                            .format('DD-MM-YYYY HH:mm:ss');


                        if ($scope.deathObject.durationFormat == '1') {
                            $scope.deathObject.durationValue = $scope.timeValueOfDuration;
                        } else {
                            $scope.deathObject.durationValue = $scope.timeValueOfDay;
                        }

                        $scope.deathObject.createdDate = $scope.currentDate;
                        $scope.deathObject.updatedDate = $scope.currentDate;
                        $scope.deathObject.admissionKinId = $scope.itemKinSelecteds.kinDetailsId;
                        console.log($scope.deathObject);

                        //return false;
                        var URI = BASE_URL + ROOT_URL + SAVEBEDALLOCATIONMORTUARY;
                        GenericService
                            .serviceAction("POST", URI, $scope.deathObject)
                            .then(
                                function(response) {

                                    if (response.data.status == "success") {
                                        growl
                                            .success(
                                                'Bed Allocation sucessfully!!!.', {
                                                    title: 'Success!'
                                                });

                                        angular.element('#BedBooking').modal('hide');
                                        $scope.init();
                                    } else {
                                        growl
                                            .error(
                                                'Something wrongs!!!!.', {
                                                    title: 'Error!'
                                                });
                                    }

                                });

                    }

                }


                $scope.getDataReject = function(info) {
                    console.log(info);
                    $scope.itemSelectedsReject = [];
                    $scope.itemSelectedsReject = info;
                }

                $scope.saveAcceptReject = function() {

                    console.log($scope.itemSelectedsReject);
                    $scope.currentDate = new Date();
                    $scope.currentDate = moment(
                        $scope.currentDate).format(
                        'DD-MM-YYYY HH:mm:ss');

                    var noteReject = $('#noteReject').val();
                    var rejectReasonId = $('#rejectReasonId').val();
                    var data = {
                        organizationId: $scope.organizationId,
                        unitId: $scope.unitId,
                        mortuaryRequestId: $scope.itemSelectedsReject.mortuaryRequestId,
                        updatedBy: $scope.userId,
                        updatedDate: $scope.currentDate,
                        rejectionNote: noteReject,
                        rejectReasonId: rejectReasonId,
                        mortuaryBedId: $scope.itemSelectedsReject.mortuaryBedId
                    };

                    console.log(data);
                    // return false;
                    var URI = BASE_URL + ROOT_URL + CANCELMORGUEREQUEST;
                    GenericService
                        .serviceAction("POST", URI,
                            data)
                        .then(
                            function(response) {

                                if (response.data.status == "success") {
                                    growl
                                        .success(
                                            'Sucessfully!!!.', {
                                                title: 'Success!'
                                            });
                                    //accepatReject
                                    angular.element(
                                            '#accepatReject')
                                        .modal('hide');
                                    $scope.init();
                                } else {
                                    growl
                                        .error(
                                            'Something wrongs!!!!.', {
                                                title: 'Error!'
                                            });
                                }

                            });
                }


                $scope.saveKinDetails = function() {

                    $scope.currentDate = new Date();
                    $scope.currentDate = moment($scope.currentDate)
                        .format('DD-MM-YYYY HH:mm:ss');

                    var data = {
                        organizationId: $scope.organizationId,
                        unitId: $scope.unitId,
                        createdBy: $scope.userId,
                        createdDate: $scope.currentDate,
                        patientId: $scope.itemSelecteds.patientId,
                        tPatientId: $scope.itemSelecteds.tPatientId,
                        dPatientId: $scope.itemSelecteds.dPatientId
                    }


                    $scope.dataList = [];

                    $scope.kinDetails.expiry = moment($scope.datepicker).format(
                        'DD-MM-YYYY HH:mm:ss');
                    $scope.dataList.push($scope.kinDetails);
                    data.kinArray = $scope.dataList;

                    console.log(data);

                    var URI = BASE_URL + ROOT_URL + SAVEKINDEATILSFORMORTUARYALLOCATION;
                    GenericService
                        .serviceAction("POST", URI, data)
                        .then(
                            function(response) {

                                if (response.data.status == "success") {
                                    growl
                                        .success(
                                            'Sucessfully!!!.', {
                                                title: 'Success!'
                                            });
                                    $scope.nextOfKinDetails(data);

                                } else {
                                    growl
                                        .error(
                                            'Something wrongs!!!!.', {
                                                title: 'Error!'
                                            });
                                }

                            });
                }
                $scope.bedAllocation = function(info) {
                    console.log(info);
                    //href="#/morgueAvailability"
                    var cookieMorgueBedObject = {
                        'patientName': info.patientName,
                        'uhidNumber': info.uhidNumber,
                        'dPatientId': info.dPatientId,
                        'admissionId': info.admissionId,
                        'patientId': info.patientId,
                        'tPatientId': info.tPatientId,
                        'mortuaryRequestId': info.mortuaryRequestId,
                        'durationFormat': info.durationFormat,
                        'durationValue': info.durationValue
                    }

                    $cookies.putObject('cookieMorgueBedObject', cookieMorgueBedObject);
                    $state.go('morgueAvailability');
                }

            }
        ]);