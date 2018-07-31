'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:shortLeaveDischargeController
 * @description #shortLeaveDischargeController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'shortLeaveDischargeController',
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
								formatYear : 'yyyy',
								showWeeks : false
							};
							$scope.openDatePicker = function() {
								// alert("in");
								$scope.datepicker.opened = true;
							};
							$scope.datepicker = {
								opened : false
							};

							$scope.openDatePickerTo = function() {
								// alert("in");
								$scope.datepickerTo.opened = true;
							};
							$scope.datepickerTo = {
								opened : false
							};

							/* init() function for form object create. */
							$scope.init = function() {

								/*	if ($cookies.get('bedManagmentCookies') != null) {
										$scope.bedManagmentCookiesObj = JSON
												.parse($cookies
														.get('bedManagmentCookies'));
										console.log($scope.bedManagmentCookiesObj);
										$scope.patientFullName = $scope.bedManagmentCookiesObj.patientFullName;
										$scope.uhidNum = $scope.bedManagmentCookiesObj.uhidNum;
										$scope.bedNumber = $scope.bedManagmentCookiesObj.bedNumber;
										$scope.doctorFullName = $scope.bedManagmentCookiesObj.doctorFullName;
										$scope.wardName = $scope.bedManagmentCookiesObj.wardName;
										$scope.admissionId  =  $scope.bedManagmentCookiesObj.admissionId;
									  } else {
										$scope.bedManagmentCookiesObj = null;
									}*/

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

								$scope.patientList = [];
								$scope.patientSearchObj = {
									"uhIdNumber" : "",
									"patientName" : "",
									"genderId" : "",
									"mobileNo" : "",
									"birthDate" : "",
									"identificationNo" : "",
									"patientType" : 1,
									"unitId" : $scope.unitId,
									"organizationId" : $scope.organizationId
								}
								$scope.patientLable = {
									"patientName" : "",
									"genderCode" : "",
									"age" : "",
									"uhIdNumber" : "",
									"birthDate" : ""
								}

								$scope.shortRequest = {
									patientId : '',
									tPatientId : '',
									note : "",
									fromTime : "",
									toTime : "",
									reasonId : "",
									admissionId : 0,
									doctorId : 0

								}

								var data = {
									organizationId : $scope.organizationId,
									unitId : $scope.unitId
								};

								var URI = BASE_URL + ROOT_URL
										+ GETSHORTLEAVEREASONLIST;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.reasonList = [];

													if (response.data.status == "success") {
														$scope.reasonList = response.data.listObject;

													}
												});

							}

							$scope.init();

							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.saveShortLeaveRequest();
							}

							$scope.searchPatient = function(patientsearchObj) {

								var URI = BASE_URL
										+ ROOT_URL
										+ PATIENTSERACHBYMULTIPLECRITERIAUNKNOWN;
								GenericService
										.serviceAction("POST", URI,
												patientsearchObj)
										.then(
												function(response) {
													$scope.patientList = [];

													if (response.data.status == "success") {
														$scope.patientList = response.data.listObject;
													}
												});
							}

							$scope.getPatientDetailsByIndex = function(index) {

								$scope.patientLable.age = ($scope.patientList[index].age);
								$scope.patientLable.patientName = ($scope.patientList[index].patientName);
								$scope.patientLable.genderCode = ($scope.patientList[index].genderCode);
								$scope.patientLable.uhIdNumber = ($scope.patientList[index].uhIdNumber);
								$scope.patientLable.birthDate = ($scope.patientList[index].birthDate);

								if ($scope.patientList[index].patientType == 1) {
									$scope.shortRequest.patientId = $scope.patientList[index].patientId;
								} else {
									$scope.shortRequest.tPatientId = $scope.patientList[index].patientId;
								}

								$scope.shortRequest.admissionId = $scope.patientList[index].admissionId;
								$scope.shortRequest.doctorId = $scope.patientList[index].doctorId;

							}

							// Function for save Admission Request
							$scope.saveShortLeaveRequest = function() {

								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								if($scope.shortRequest.admissionId == 0)
								 {
									growl
									.error(
											'Please Select Patient!!!.',
											{
												title : 'Error!'
											});
								 }
								 else
									 {
									if ($scope.popUpFlag) {
										$scope.showModal = !$scope.showModal;

									} else {

										var data = {
											admissionId : $scope.shortRequest.admissionId,
											shortLeaveReasonId : $scope.shortRequest.reasonId,
											fromDate : moment($scope.datepicker)
													.format(
															'DD-MM-YYYY HH:mm:ss'),
											toDate : moment($scope.datepickerTo)
													.format(
															'DD-MM-YYYY HH:mm:ss'),

											note : $scope.shortRequest.note,
											fromTime : $scope.shortRequest.fromTime,
											toTime : $scope.shortRequest.toTime,
											status : 'A',
											createdBy : $scope.userId,
											updatedBy : $scope.userId,
											organizationId : $scope.organizationId,
											unitId : $scope.unitId,
											patientId : $scope.shortRequest.patientId,
											tPatientId : $scope.shortRequest.tPatientId,
											updatedDate : $scope.currentDate,
											createdDate : $scope.currentDate,
											shortLeaveStatusId : '1',
											doctorId : $scope.shortRequest.doctorId 

										}
										console.log(data);
										//return false;
										var URI = BASE_URL + ROOT_URL
												+ ADDSHORTLEAVEREQUEST;
										GenericService
												.serviceAction("POST", URI,
														data)
												.then(
														function(response) {

															if (response.data.status == "success") {
																growl
																		.success(
																				'Short Leave Request sent sucessfully!!!.',
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
							}

							}

						} ]);
