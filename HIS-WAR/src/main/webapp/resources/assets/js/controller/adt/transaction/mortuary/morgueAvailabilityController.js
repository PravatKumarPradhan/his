'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:morgueAvailabilityController
 * @description #morgueAvailabilityController Controller of the myApp
 */
angular
 .module('myApp')
 .controller(
  'morgueAvailabilityController', [
   '$scope',
   '$http',
   '$localStorage',
   '$sessionStorage',
   '$cookies',
   '$rootScope',
   'GenericService',
   'growl',
   '$state',
   '$stateParams',
   function($scope, $http, $localStorage, $sessionStorage,
    $cookies, $rootScope, GenericService, growl,
    $state, $stateParams) {

    /* init() function for form object create. */
    $scope.init = function() {

     var cookieObject = $cookies.getObject('cookieObject');
     if (cookieObject == undefined) {
      $state.go('login');
      return;
     }
     $scope.unitId = cookieObject.unitId;
     $scope.organizationId = cookieObject.organizationId;
     $scope.userId = 1;

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
      mortuaryStatusId: 2,
      bedStatusId: 4
     };


     $scope.cookieMorgueBedObject = $cookies.getObject('cookieMorgueBedObject');
     if ($scope.cookieMorgueBedObject == undefined) {
      //$state.go('login');
      //return;
     } else {
      $scope.deathObject.durationFormat = $scope.cookieMorgueBedObject.durationFormat;
      if ($scope.cookieMorgueBedObject.durationFormat == '1') {
       if ($scope.cookieMorgueBedObject.durationValue != null)
        $scope.timeValueOfDuration = $scope.cookieMorgueBedObject.durationValue.toString();
      } else {
       if ($scope.cookieMorgueBedObject.durationValue != null)
        $scope.timeValueOfDay = $scope.cookieMorgueBedObject.durationValue.toString();
      }
     }

     //console.log($scope.cookieMorgueBedObject);
     // for datePicker

     $scope.vacantMortuaryFlag = true;
     $scope.occupiedMortuaryFlag = false;



     $rootScope.loginpage = true;
     $scope.saveBtnFlag = false;
     $scope.updateBtnFlag = true;

     $scope.itemSelecteds = {};

     // for popup
     $scope.showModal = false;
     $scope.buttonClicked = "";
     $scope.popUpFlag = true;
     // end for popup



     var commonObj = {
      unitId: $scope.unitId,
      organizationId: $scope.organizationId
     };

     var bedObj = {
      unitId: $scope.unitId,
      organizationId: $scope.organizationId
     }
     var URI = BASE_URL + ROOT_URL + GETMORTUARTBEDLISTBYSTATUSID;
     GenericService
      .serviceAction("POST", URI, bedObj)
      .then(
       function(response) {
        $scope.mortuaryBedList = [];

        if (response.data.status == "success") {
         $scope.mortuaryBedList = response.data.listObject;

        }
       });
    }

    $scope.init();

    // function for popup ok button call
    $scope.PopupOkBtn = function() {
     $scope.popUpFlag = false;
     $scope.saveBedAllocation();
    }

    $scope.patientDetails = function(info) {
     console.log(info);
     $scope.itemSelecteds = [];
     $scope.itemSelecteds = info;

     if ($scope.cookieMorgueBedObject == undefined) {
      $state.go('login');
      return;
     }
     $scope.patientName = $scope.cookieMorgueBedObject.patientName;
     $scope.uhidNumber = $scope.cookieMorgueBedObject.uhidNumber;
    }

    $scope.vacantMortuaryBed = function() {
     $("#vacanttab").css("background-color", "#5F9EA0");
     $("#occupiedtab").css("background-color", "#5ab7c8");
     $scope.vacantMortuaryFlag = true;
     $scope.occupiedMortuaryFlag = false;
    }

    $scope.occupiedMortuaryBed = function() {
     $("#occupiedtab").css("background-color", "#5F9EA0");
     $("#vacanttab").css("background-color", "#5ab7c8");
     $scope.vacantMortuaryFlag = false;
     $scope.occupiedMortuaryFlag = true;
    }

    $scope.allotMorgueBed = function() {

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
     $scope.deathObject.mortuaryBedId = $scope.itemSelecteds.mortuaryBedId;
     //console.log($scope.deathObject);
     //console.log($scope.cookieMorgueBedObject);
     $scope.deathObject.mortuaryRequestId = $scope.cookieMorgueBedObject.mortuaryRequestId;

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
           'Bed Reserved sucessfully!!!.', {
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
  ]);