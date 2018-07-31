'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:unknownPatientRegController
 * @description #unknownPatientRegController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'unknownPatientRegController',
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

							// for datePicker
							
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

							$scope.openDatePickerForNewDOA = function() {

								$scope.datepickerForNewDOA.opened = true;
							};

							$scope.datepickerForNewDOA = {
								opened : false
							};

							/* init() function for form object create. */
							$scope.init = function() {
								
								var cookieObject = $cookies.getObject('cookieObject');
								if(cookieObject == undefined){
									$state.go('login');
									return;
								}
								$scope.unitId = cookieObject.unitId;
								$scope.organizationId = cookieObject.organizationId;
								$scope.userId = 1;

								$rootScope.loginpage = true;
								/*$scope.unknownPatient = {
									isMedicoLegal : 'N'
								}*/
								
								$rootScope.readOnlyFlag = false;
								$scope.patientList = [];
								$scope.patientSearchObj = {
										"uhIdNumber"      : "",
										"patientName"     : "",
										"genderId"        : "",
										"mobileNo"        : "",
										"birthDate"       : "",
										"identificationNo":"",
										"patientType" : 4,
										"unitId"          :$scope.unitId,
										"organizationId"  :$scope.organizationId
								}
								
								$rootScope.unknownPatient = {
										"patientName" : "",
										"genderCode"  : "",
										"age"         : "", 
										"uhIdNumber"  : "",
										"genderId"    : "",
										"mobileNo"    : "",
										"ageFormat" : "",
										"isMedicoLegal" : 'N',
										"patientId":0,
										"tPatientId":0,
										"remark":"",
										"priorityId":0,
										"accompaniedBy":""
								}
								$rootScope.patientLable = {
										"patientName" : "",
										"genderCode"  : "",
										"age"         : "", 
										"uhIdNumber"  : "",
										"birthDate"  : ""
								}
								
								var data = {};

								var URI = BASE_URL + ROOT_URL
										+ GETACTIVEGENDERLIST;
								GenericService
										.serviceAction("GET", URI, data)
										.then(
												function(response) {
													$scope.genderList = [];

													if (response.data.status == "success") {
														$scope.genderList = response.data.listObject;

													}
												});
								var URI = BASE_URL + ROOT_URL
										+ GETACTIVEPRIORITYLIST;
								GenericService
										.serviceAction("GET", URI, data)
										.then(
												function(response) {
													$scope.priorityList = [];

													if (response.data.status == "success") {
														$scope.priorityList = response.data.listObject;

													}
												});

								// for popup
								$scope.showModal = false;
								$scope.buttonClicked = "";
								$scope.popUpFlag = true;
								// end for popup

							}

							$scope.init();

							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.saveUnknownPatient();
							}
							
						
							

							$scope.searchPatient = function(patientsearchObj){
									
								var URI = BASE_URL + ROOT_URL
								+ PATIENTSERACHBYMULTIPLECRITERIAUNKNOWN;
						GenericService
								.serviceAction("POST", URI, patientsearchObj)
								.then(
										function(response) {
											$scope.patientList = [];

											if (response.data.status == "success") {
												$scope.patientList = response.data.listObject;
											}
										});
							}
							
							
							$scope.getPatientDetailsByIndex = function(index){
								
								$rootScope.readOnlyFlag = true;
								
								$rootScope.patientLable.age=($scope.patientList[index].age);
								$rootScope.patientLable.patientName=($scope.patientList[index].patientName);
								$rootScope.patientLable.genderCode=($scope.patientList[index].genderCode);
								$rootScope.patientLable.uhIdNumber=($scope.patientList[index].uhIdNumber);
								$rootScope.patientLable.birthDate=($scope.patientList[index].birthDate);
								
								$rootScope.unknownPatient.age=($scope.patientList[index].age);
								$rootScope.unknownPatient.patientName=($scope.patientList[index].patientName);
								$rootScope.unknownPatient.genderCode=($scope.patientList[index].genderCode);
								$rootScope.unknownPatient.uhIdNumber=($scope.patientList[index].uhIdNumber);
								$rootScope.unknownPatient.genderId=($scope.patientList[index].genderId).toString();
								$rootScope.unknownPatient.mobileNo=($scope.patientList[index].mobileNo);
								$rootScope.unknownPatient.patientType=($scope.patientList[index].patientType);
								$rootScope.unknownPatient.patientId=($scope.patientList[index].patientId);
								if($scope.patientList[index].patientType == 4)
									{
										$rootScope.unknownPatient.ageFormat = $scope.patientList[index].age.slice($scope.patientList[index].age.length-1);
										$rootScope.unknownPatient.age = $scope.patientList[index].age.slice(0,-1);
										
									}
								else
									{
									 var array = $scope.patientList[index].age.split(' ');
									 $rootScope.unknownPatient.ageFormat = array[0].slice( array[0].length-1);
										$rootScope.unknownPatient.age =  array[0].slice(0,-1);
									
									}
								console.log($rootScope.unknownPatient);
							}
							
							$scope.quickReg = function()
							{
								$rootScope.readOnlyFlag = false;
								$scope.init();
							}

							$scope.saveUnknownPatient = function() {

								if ($scope.popUpFlag) {
									$scope.showModal = !$scope.showModal;

								} else {

									/*
									 * var NewDateDoa =
									 * $rootScope.getFormatDate($scope.datepicker);
									 * var NewDatePdd =
									 * $rootScope.getFormatDate($scope.datepickerPdd);
									 */
									$scope.currentDate = new Date();
									$scope.currentDate = moment($scope.currentDate)
											.format('DD-MM-YYYY HH:mm:ss');
									
									var data = {

										patientName : $scope.unknownPatient.patientName,
										genderId : $scope.unknownPatient.genderId,
										age : $scope.unknownPatient.age,
										ageFormat : $scope.unknownPatient.ageFormat,
										accompaniedBy : $scope.unknownPatient.accompaniedName,
										priorityId : $scope.unknownPatient.priorityId,
										isMedicoLegal : $scope.unknownPatient.isMedicoLegal,
										status : 'A',
										createdBy : $scope.userId,
										updatedBy : $scope.userId,
										organizationId : $scope.organizationId,
										unitId : $scope.unitId,
										createdDate:$scope.currentDate,
										updatedDate:$scope.currentDate,
										visitTypeId:4,
										admissionTypeId : 4,
										tUhid : $scope.unknownPatient.uhIdNumber,
										remark : $scope.unknownPatient.remark,
										mobileNo:$scope.unknownPatient.mobileNo
										

									}
									if($rootScope.unknownPatient.patientType == 4)
										{
										data.tPatientId  = $scope.unknownPatient.patientId;
										}
									else if($rootScope.unknownPatient.patientType == 1)
										{
											data.patientId = $scope.unknownPatient.patientId;
										}
									else
										{
										
										}
									console.log(data);
									//return false;
									var URI = BASE_URL + ROOT_URL
											+ ADDUNKNOWNPATIENT;
									GenericService
											.serviceAction("POST", URI, data)
											.then(
													function(response) {

														if (response.data.status == "success") {
															growl
																	.success(
																			'Unknown Patient Registration Sucessfully!!!.',
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

						} ]);
