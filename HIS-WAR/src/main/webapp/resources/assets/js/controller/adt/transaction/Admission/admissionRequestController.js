'use strict';

/**
 * @Author By Vivek Satle
 * @name myApp.controller:admissionRequestController
 * @description # admissionRequestController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'admissionRequestController',
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
						'BillingGenericService',
						function($scope, $http, $localStorage, $sessionStorage,
								$cookies, $rootScope, GenericService, growl,
								$state, BillingGenericService) {

							// for datePicker

							$scope.admissionNoteDataER = JSON
									.parse(sessionStorage
											.getItem("admissionNoteObjER"));

							$scope.dateOptions = {
								formatYear : 'yyyy',
								showWeeks : false
							};

							$scope.openDatePicker = function() {
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

							// for datePicker2
							$scope.dateOptions = {
								formatYear : 'yyyy',
								showWeeks : false
							};
							$scope.openDatePickerPdd = function() {
								$scope.datepickerPdd.opened = true;
							};

							$scope.datepickerPdd = {
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

								$scope.LISDynamicLabel = $cookies
										.get('selectedPageName');
								$scope.selectedPageNameForAdmissionNote = $cookies
										.get('selectedPageNameForAdmissionNote');
								
								var cookieObject = $cookies.getObject('cookieObject');
								if(cookieObject == undefined){
									$state.go('login');
									return;
								}
								$scope.unitId = cookieObject.unitId;
								$scope.organizationId = cookieObject.organizationId;
								$scope.userId = 1;

								$scope.groupName = "";
								$scope.UHIDNumber = "";
								$scope.firstName = "";
								$scope.middleName = "";
								$scope.lastName = "";
								$scope.genderCode = "";
								$scope.age = "";
								$scope.patientFullName = "";

								var data = {};
								$scope.patientList = [];
								$scope.patientSearchObj = {
									"uhIdNumber" : "",
									"patientName" : "",
									"genderId" : "",
									"mobileNo" : "",
									"birthDate" : "",
									//"indentificationTypeId":"",
									"identificationNo" : "",
									"patientType" : 1,
									"unitId" : $scope.unitId,
									"organizationId" : $scope.organizationId
								}
								$scope.patientDetails = {
									"patientName" : "",
									"genderCode" : "",
									"age" : "",
									"uhIdNumber" : "",
									"birthDate" : ""
								}

								var commonObj = {
									unitId : $scope.unitId,
									organizationId : $scope.organizationId
								}

								var URI = BASE_URL + ROOT_URL
										+ GETACTIVESPECIALITY;
							
								BillingGenericService
										.serviceAction(METHOD_POST, URI,
												commonObj,
												NOTIFICATION_MSG_STATUS_FALSE)
										.then(
												function(response) {
													console.log(response);
													// $rootScope.startLoader();
													if (response.data.status == "success")
														$scope.specialityList = response.data.listObject;
												});

								var URI = BASE_URL + ROOT_URL
										+ GETACTIVEBEDCATEGORYLISTBYUNIT;
							
								BillingGenericService
										.serviceAction(METHOD_POST, URI,
												commonObj,
												NOTIFICATION_MSG_STATUS_FALSE)
										.then(
												function(response) {
													console.log(response);
													// $rootScope.startLoader();
													if (response.data.status == "success")
														$scope.bedCategoryList = response.data.listObject;
												});

								var URI = BASE_URL + ROOT_URL
										+ GETACTIVEREASONLIST;
								
								BillingGenericService
										.serviceAction(METHOD_GET, URI,
												commonObj,
												NOTIFICATION_MSG_STATUS_FALSE)
										.then(
												function(response) {
													console.log(response);
													// $rootScope.startLoader();
													if (response.data.status == "success")
														$scope.reasonList = response.data.listObject;
												});

								$scope.searchGroupList = function(keyword) {

									var data = {
										firstName : keyword,
										unitId : $scope.unitId,
										// visitType :
										// $scope.selectedPageNameForAdmissionNote,
										organizationId : $scope.organizationId
									}
									console.log(data);

									var URI = BASE_URL + ROOT_URL
											+ GETPATIENTBYKEYWORD;
									
									return BillingGenericService
											.serviceAction(METHOD_POST, URI,
													data,
													NOTIFICATION_MSG_STATUS_FALSE)
											.then(
													function(response) {
														console.log(response);
														// $rootScope.startLoader();
														if (response.data.status == "success")
															return response.data.listObject;
													});
									// return data="[{name:'test'}]";

								}

								$rootScope.loginpage = true;
								$scope.admissionRequest = {
									isFlexiable : false,
									visitTypeId : 1,
									patientId : '',
									tPatientId : '',
									patientCategoryId : 0,
									genderId : 0,
									note : ""
								}

								// for popup
								$scope.showModal = false;
								$scope.buttonClicked = "";
								$scope.popUpFlag = true;
								// end for popup

								if ($scope.selectedPageNameForAdmissionNote == 'ER') {

									if ($scope.admissionNoteDataER != null) {

										if ($scope.admissionNoteDataER.patientId > 0) {
											$scope.admissionRequest.patientId = $scope.admissionNoteDataER.patientId;
										} else {
											$scope.admissionRequest.tPatientId = $scope.admissionNoteDataER.tPatientId;
										}

										$scope.admissionRequest.id = $scope.admissionNoteDataER.genderId;
										// $scope.UHIDNumber =
										// $scope.admissionNoteDataER.uhidnumber;
										$scope.patientDetails.uhIdNumber = $scope.admissionNoteDataER.uhidnumber;
										$scope.age = $rootScope
												.getAge($scope.admissionNoteDataER.dob);
										// $scope.patientFullName =
										// $scope.admissionNoteDataER.pFirstName
										// + " " + $scope.age+ "/" +
										// $scope.admissionNoteDataER.genderCode;
										$scope.patientDetails.patientName = $scope.admissionNoteDataER.pFirstName
												+ $scope.admissionNoteDataER.genderCode;
										$scope.patientDetails.age = $scope.admissionNoteDataER.dob;
										$scope.patientDetails.genderCode = $scope.admissionNoteDataER.genderCode;
										$scope.patientDetails.birthDate = "-";
										$scope.admissionRequest.visitTypeId = $scope.admissionNoteDataER.visitTypeId;
										$scope.admissionRequest.patientCategoryId = $scope.admissionNoteDataER.patientCategoryId;
										$scope.admissionRequest.prefixId = $scope.admissionNoteDataER.prefixId;

										var arrayN = $scope.admissionNoteDataER.doa
												.split(' ');
										var array = arrayN[0].split('/');
										var newDateDoa = array[1] + '/'
												+ array[0] + '/' + array[2];
										$scope.datepicker = new Date(newDateDoa);
									}
								}

							}

							$scope.init();
							
							
							$scope.getSexMasterList = function(){
								var URI = BASE_URL + ROOT_URL + "/adt/getActiveGenderList";
								BillingGenericService.serviceAction(METHOD_GET,URI, "", NOTIFICATION_MSG_STATUS_FALSE).then(function(response){
									$scope.sexMasterList = response.data.listObject;
								});
							}
							$scope.getSexMasterList();
							
							

							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.saveAdmissionRequest();
							}

							$scope.searchPatient = function(patientsearchObj) {

								var URI = BASE_URL
										+ ROOT_URL
										+ PATIENTSERACHBYMULTIPLECRITERIAUNKNOWN;
								BillingGenericService
										.serviceAction(METHOD_POST, URI,
												patientsearchObj,
												NOTIFICATION_MSG_STATUS_FALSE)
										.then(
												function(response) {
													console.log(response);
													$scope.patientList = [];
													// $rootScope.startLoader();
													if (response.data.status == "success")
														$scope.patientList = response.data.listObject;
												});
							}

							$scope.getPatientDetailsByIndex = function(index) {

								$scope.patientDetails.age = ($scope.patientList[index].age);
								$scope.patientDetails.patientName = ($scope.patientList[index].patientName);
								$scope.patientDetails.genderCode = ($scope.patientList[index].genderCode);
								$scope.patientDetails.uhIdNumber = ($scope.patientList[index].uhIdNumber);
								$scope.patientDetails.birthDate = ($scope.patientList[index].birthDate);

								if ($scope.patientList[index].patientType == 1) {
									$scope.admissionRequest.patientId = $scope.patientList[index].patientId;
								} else {
									$scope.admissionRequest.tPatientId = $scope.patientList[index].patientId;
								}

								$scope.admissionRequest.prefixId = $scope.patientList[index].prefixId;
								$scope.admissionRequest.id = $scope.patientList[index].genderId;
								$scope.admissionRequest.patientCategoryId = $scope.patientList[index].patientCategoryId

							}

							$scope.getDoctorBySpeciality = function(id) {

								var data = {
									specialityId : id,
									unitId : $scope.unitId,
									organizationId : $scope.organizationId
								};
								var URI = BASE_URL + ROOT_URL
										+ GETDOCTORBYSPECIALITYID;
								
								BillingGenericService
										.serviceAction(METHOD_POST, URI, data,
												NOTIFICATION_MSG_STATUS_FALSE)
										.then(
												function(response) {
													console.log(response);
													$scope.doctorList = [];
													// $rootScope.startLoader();
													if (response.data.status == "success")
														$scope.doctorList = response.data.listObject;
												});

							}

							$scope.selectPatientId = function(info, model,
									label) {
								var data = {
									patientId : info.patientId
								};
								var URI = BASE_URL + ROOT_URL
										+ GETPATIENTDETAILSBYPATIENTID;

								BillingGenericService
										.serviceAction(METHOD_POST, URI, data,
												NOTIFICATION_MSG_STATUS_FALSE)
										.then(
												function(response) {
													console.log(response);
													$scope.doctorList = [];
													// $rootScope.startLoader();
													if (response.data.status == "success")
														$scope.admissionRequest.patientId = response.data.listObject[0].patientId;
													$scope.admissionRequest.prefixId = response.data.listObject[0].prefixId;
													$scope.admissionRequest.id = response.data.listObject[0].id;
													$scope.admissionRequest.patientCategoryId = response.data.listObject[0].patientCategoryId;
													$scope.UHIDNumber = response.data.listObject[0].uhidnumber;
													$scope.firstName = response.data.listObject[0].firstName;
													$scope.middleName = response.data.listObject[0].middleName;
													$scope.lastName = response.data.listObject[0].lastName;
													$scope.genderCode = response.data.listObject[0].genderCode;
													$scope.age = $rootScope
															.getAge(response.data.listObject[0].dob);
													$scope.patientFullName = $scope.firstName
															+ " "
															+ $scope.middleName
															+ " "
															+ $scope.lastName
															+ " "
															+ $scope.age
															+ "/"
															+ $scope.genderCode;
													$scope.groupName = "";
												});

							}

							// Function for save Admission Request
							$scope.saveAdmissionRequest = function() {
								
								if($scope.patientDetails.patientName == '')
									{
									growl.error("Please Search And Select Patient!!! ",{
										title : ERROR_MSG
									});
									
									}
								else
									{
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								$scope.$broadcast('show-errors-check-validity');

								if ($scope.admissionrequestform.$valid) {
									if ($scope.popUpFlag) {
										$scope.showModal = !$scope.showModal;

									} else {

										if ($scope.admissionRequest.isFlexiable == true) {
											$scope.admissionRequest.isFlexiable = "Y";
										} else {
											$scope.admissionRequest.isFlexiable = "N";
										}

										var data = {
											doctorSpecialityId : $scope.admissionRequest.specialityId,
											requestToDoctorId : $scope.admissionRequest.doctorId,
											reasonId : $scope.admissionRequest.reasonId,
											doa : moment($scope.datepicker)
													.format(
															'DD-MM-YYYY HH:mm:ss'),
											pdd : moment($scope.datepickerPdd)
													.format(
															'DD-MM-YYYY HH:mm:ss'),
											status : 'A',
											createdBy : $scope.userId,
											updatedBy : $scope.userId,
											organizationId : $scope.organizationId,
											activeStatus : 'P',
											visitTypeId : $scope.admissionRequest.visitTypeId,
											requestBy : $scope.userId,
											// admissionId : '0',
											unitId : $scope.unitId,
											patientId : $scope.admissionRequest.patientId,
											tPatientId : $scope.admissionRequest.tPatientId,
											preVisitId : '0',
											isFlexiableDate : $scope.admissionRequest.isFlexiable,
											admissionStatus : 'P',
											isCancel : 'N',
											bedCategoryId : $scope.admissionRequest.bedCategoryId,
											paymentEntitlementId : '1',
											patientCategoryId : $scope.admissionRequest.patientCategoryId,
											// patientCategoryId : '1',
											prefixId : $scope.admissionRequest.prefixId,
											id : $scope.admissionRequest.id,
											updatedDate : $scope.currentDate,
											createdDate : $scope.currentDate,
											note : $scope.admissionRequest.note

										}
										console.log(data);
										//return false;
										var URI = BASE_URL + ROOT_URL
												+ ADDADMISSIONREQUEST;

										BillingGenericService
												.serviceAction(METHOD_POST,
														URI, data,
														NOTIFICATION_MSG_STATUS_TRUE)
												.then(
														function(response) {
															console
																	.log(response);
															//$rootScope.startLoader();
															if (response.data.status == "success")
																$scope.init();
														});
									}
								}
							}

							}

						} ]);