'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:dueForDischargeController
 * @description #dueForDischargeController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'dueForDischargeController',
				[
						'$scope',
						'$http',
						'$localStorage',
						'$sessionStorage',
						'$cookies',
						'$rootScope',
						'GenericService',
						'growl',
						'$state',
						function($scope, $http, $localStorage, $sessionStorage,
								$cookies, $rootScope, GenericService, growl,
								$state) {
							
							 $scope.dateOptions = {							 
								      formatYear: 'yyyy',
								      showWeeks: false
								  };
								 $scope.openDatePicker = function() {
									// alert("in");
								      $scope.datepicker.opened = true;
								    };
								    $scope.datepicker = {
										      opened: false
										    };

							/* init() function for form object create. */
							$scope.init = function() {

								//$rootScope.dueForDischargeLable = "Due For Discharge";
								$scope.dueForDischargeFlage = false;
								$(".selectedPageName").text("Due For Discharge");    
									    
								var cookieObject = $cookies.getObject('cookieObject');
								if(cookieObject == undefined){
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

								$scope.admittedPatient = {};
								$scope.itemSelecteds = {};

								var data = {
										organizationId : $scope.organizationId
								};
								
								$scope.cD = new Date();
								$scope.cD = moment($scope.cD)
										.format('DD-MM-YYYY');
								var commonObj = {
									unitId : $scope.unitId,
									organizationId : $scope.organizationId,
									pdd : $scope.cD
								};
								
								var URI = BASE_URL + ROOT_URL
								+ GETACTIVEDISCHARGETYPELIST;
						GenericService
								.serviceAction("POST", URI, data)
								.then(
										function(response) {
											$scope.dischargeTypeList = [];

											if (response.data.status == "success") {
												$scope.dischargeTypeList = response.data.listObject;

											}
										});
						
						var URI = BASE_URL + ROOT_URL
						+ GETACTIVEREASONLIST;
				GenericService
						.serviceAction("GET", URI, data)
						.then(
								function(response) {
									$scope.reasonList = [];

									if (response.data.status == "success") {
										$scope.reasonList = response.data.listObject;

									}
								});

								

								var URI = BASE_URL + ROOT_URL
										+ GETDISCHARGEADMITTEDPATIENTLIST;
								GenericService
										.serviceAction("POST", URI, commonObj)
										.then(
												function(response) {
													$scope.listOfAdmittedPatientList = [];
													$scope.admittedPatientList = [];

													if (response.data.status == "success") {
														$scope.filterDate = new Date();
														$scope.filterDate = moment($scope.filterDate)
																.format('DD/MM/YYYY');
														$scope.admittedPatientList = response.data.listObject;
														angular
																.forEach(
																		$scope.admittedPatientList,
																		function(
																				value,
																				index) {

																			var newArr = {};
																			newArr.specialityName = value.specialityName;
																			newArr.doctorFullName = value.dFirstName;
																			newArr.patientFullName = value.pFirstName;
																			newArr.reasonDesc = value.reasonDesc;
																			newArr.dob = value.dob;
																			newArr.datepicker = value.doa;
																			newArr.datepickerPdd = value.pdd;
																			newArr.genderCode = value.genderCode;
																			newArr.uhidnumber = value.uhidnumber;
																			newArr.patientId = value.patientId;
																			newArr.tPatientId = value.tPatientId;
																			newArr.paymentEntitlementDesc = value.paymentEntitlementDesc;
																			newArr.bedNumber = value.bedNumber;
																			newArr.admissionId = value.admissionId;
																			newArr.prefixCode = value.prefixCode;
																			newArr.identificationNo = value.identificationNo;
																			newArr.doctorId = value.doctorId;
																			newArr.bedId = value.bedId;
																			$scope.listOfAdmittedPatientList
																					.push(newArr);

																		});

													}
												});


							}
							
							
						
							$scope.init();

							$scope.popupDischarge = function(item)
							{
								$scope.dischargeTypeId =0;
								$scope.itemForDischargePatient = item;
							}
							
							$scope.dueForDischarge = function(item)
							{
								$scope.note="";
								$scope.reasonId = 0;
								$scope.itemForDischargePatient = item;
								$scope.noOfHours = item.noOfHours;
							}

							$scope.saveDischargeRequest = function() {
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								var data = {
									organizationId : $scope.organizationId,
									unitId : $scope.unitId,
									dischargeTypeId : $scope.dischargeTypeId,
									admissionId : $scope.itemForDischargePatient.admissionId,
									doctorId :  $scope.itemForDischargePatient.doctorId,
									createdBy : $scope.userId,
									updatedBy : $scope.userId,
									status : 'A',
									createdDate : $scope.currentDate,
									updatedDate : $scope.currentDate,
									destinationId :$scope.itemForDischargePatient.bedId,
									dischargeDate : moment($scope.datepicker).format('DD-MM-YYYY HH:mm:ss'),
									dischargeStatusId: 1,
									visitTypeId : 1
								};
								console.log(data);
								//return false;
								var URI = BASE_URL + ROOT_URL
										+ ADDDISCHARGEREQUEST;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {

													if (response.data.status == "success") {
														growl
																.success(
																		'Discharge Request Raised sucessfully!!!.',
																		{
																			title : 'Success!'
																		});
														$scope.init();
													} else {
														growl
																.error(
																		'Something wrongs!!!!.',
																		{
																			title : 'Error!'
																		});
													}
												});
							}
							
							$scope.updateExtendPdd = function()
							{
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								var data = {
									organizationId : $scope.organizationId,
									unitId : $scope.unitId,
									reasonId : $scope.reasonId,
									admissionId : $scope.itemForDischargePatient.admissionId,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate,
									pdd : moment($scope.datepicker).format('DD-MM-YYYY HH:mm:ss'),
									noteCancel : $scope.note
								};
								console.log(data);
								//return false;
								var URI = BASE_URL + ROOT_URL
										+ UPDATEDISCHARGEPDD;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {

													if (response.data.status == "success") {
														growl
																.success(
																		'Discharge Request Raised sucessfully!!!.',
																		{
																			title : 'Success!'
																		});
														$scope.init();
													} else {
														growl
																.error(
																		'Something wrongs!!!!.',
																		{
																			title : 'Error!'
																		});
													}
												});
							}

						

						} ]);
