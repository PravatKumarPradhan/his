'use strict';

/**
 * @Author By Vivek Satle
 * @name myApp.controller:intiateTransferCareController
 * @description #intiateTransferCareController Controller of the myApp
 */

angular
		.module('myApp')
		.controller(
				'intiateTransferCareController',
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

							/* init() function for form object create. */
							$scope.init = function() {
								
								$rootScope.patientFullName = '';
								$rootScope.uhidNum = '';
								$rootScope.bedNumber = '';
								$rootScope.doctorFullName = '';
								$rootScope.wardName ='';
								$scope.treatingDoctorId = '';
								$scope.admissionId = '';
								
								/*
								 * $scope.unitId =
								 * sessionStorage.getItem("unitId");
								 * $scope.organizationId =
								 * sessionStorage.getItem("organizationId");
								 * $scope.userId =
								 * sessionStorage.getItem("userId");
								 */
								var cookieObject = $cookies.getObject('cookieObject');
								if(cookieObject == undefined){
									$state.go('login');
									return;
								}
								$scope.unitId = cookieObject.unitId;
								$scope.organizationId = cookieObject.organizationId;
								$scope.userId = 1;

								/*if ($cookies.get('bedManagmentCookies') != null) {
									$scope.bedManagmentCookiesObj = JSON
											.parse($cookies
													.get('bedManagmentCookies'));
									console.log($scope.bedManagmentCookiesObj);
									$scope.patientFullName = $scope.bedManagmentCookiesObj.patientFullName;
									$scope.uhidNum = $scope.bedManagmentCookiesObj.uhidNum;
									$scope.bedNumber = $scope.bedManagmentCookiesObj.bedNumber;
									$scope.doctorFullName = $scope.bedManagmentCookiesObj.doctorFullName;
									$scope.wardName = $scope.bedManagmentCookiesObj.wardName;
									
									var dataCookies = {
										admissionId : $scope.bedManagmentCookiesObj.admissionId,
										organizationId : $scope.organizationId,
										unitId : $scope.unitId,
									};

									var URI = BASE_URL + ROOT_URL
											+ GETOTLISTAGAINTPATIENT;
									GenericService
											.serviceAction("POST", URI,
													dataCookies)
											.then(
													function(response) {

														if (response.data.status == "success") {
															$scope.patientSurgeryList = [];
															$scope.patientSurgeryList = response.data.listObject;
														}
													});

									var URI = BASE_URL
											+ ROOT_URL
											+ GETOTTRANSFERLISTAGAINTPATIENT;
									GenericService
											.serviceAction("POST", URI,
													dataCookies)
											.then(
													function(response) {

														if (response.data.status == "success") {
															$scope.patientSurgeryTransferList = [];
															$scope.patientSurgeryTransferList = response.data.listObject;
														}
													});

								} else {
									$scope.bedManagmentCookiesObj = null;
								}*/

								$rootScope.loginpage = true;

								$scope.patientSurgeryOrder = {}
								$scope.itemSelecteds = {};

								// for popup
								$scope.showModal = false;
								$scope.buttonClicked = "";
								$scope.popUpFlag = true;
								// end for popup
								
								var data = {};
								var commonObj = {
										unitId : $scope.unitId,
										organizationId : $scope.organizationId
									}
								var commonObj2= {
										unitId : $scope.unitId,
										organizationId : $scope.organizationId,
										transferTypeId:4
									}

									var URI = BASE_URL + ROOT_URL
											+ GETACTIVESPECIALITY;
									GenericService
											.serviceAction("POST", URI, commonObj)
											.then(
													function(response) {
														$scope.specialityList = [];

														if (response.data.status == "success") {
															$scope.specialityList = response.data.listObject;

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
							+ GETTRANSFEROFCAREREQUESTLIST;
					GenericService
							.serviceAction("POST", URI, commonObj2)
							.then(
									function(response) {
										$scope.transferOfCareRequestList = [];

										if (response.data.status == "success") {
											$scope.transferOfCareRequestList = response.data.listObject;

										}
									});
							

							}

							$scope.init();
							
							
							$scope.getDoctorBySpeciality = function(id) {

								var data = {
									specialityId : id,
									unitId : $scope.unitId,
									organizationId : $scope.organizationId
								};
								var URI = BASE_URL + ROOT_URL
										+ GETDOCTORBYSPECIALITYID;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.doctorList = [];
													if (response.data.status == "success") {
														$scope.doctorList = response.data.listObject;
													}
												});

							}
							

							$scope.searchGroupList = function(keyword) {

								var data = {
									patientName : keyword,
									unitId : $scope.unitId,
									// visitType :
									// $scope.selectedPageNameForAdmissionNote,
									organizationId : $scope.organizationId
								}
								console.log(data);

								var URI = BASE_URL + ROOT_URL
										+ GETADMITTEDPATIENTBYKEWWORD;
								return GenericService.serviceAction("POST",
										URI, data).then(function(response) {

									if (response.data.status == "success") {
										// alert("ff");
										// console.log(response.data.listObject);
										return response.data.listObject;

									}
								});
								// return data="[{name:'test'}]";
							}

							$scope.selectPatientId = function(info, model,
									label) {
								console.log(info);
								var data = {
									admissionId : info.admissionId,
									organizationId : $scope.organizationId,
									unitId : $scope.unitId
								};

								if (info.tPatientId > 0) {
									$rootScope.patientFullName = info.patientName
											+ ' ' + info.dob + '/'
											+ info.genderName;
								} else {
									var age = $rootScope.getAge(info.dob);
									$rootScope.patientFullName = info.patientName
											+ ' ' + age + '/' + info.genderName;
								}

								$rootScope.uhidNum = info.uhidnumber;
								$rootScope.bedNumber = info.bedNumber;
								$rootScope.doctorFullName = info.dFirstName;
								$rootScope.wardName = info.wardName;
								$scope.treatingDoctorId = info.doctorId;
								$scope.admissionId = info.admissionId;
								
							}

						


							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.saveTransferOfCareRequest();
							}

							$scope.saveTransferOfCareRequest = function() {

									if ($scope.popUpFlag) {
										$scope.showModal = !$scope.showModal;

									} else {

										$scope.currentDate = new Date();
										$scope.currentDate = moment(
												$scope.currentDate).format(
												'DD-MM-YYYY HH:mm:ss');
										var data = {
											admissionId : $scope.admissionId,
											organizationId : $scope.organizationId,
											unitId : $scope.unitId,
											requestBy : $scope.treatingDoctorId,
											specialityId : $scope.specialityId,
											requestTo : $scope.requestDoctorId,
											reasonId : $scope.reasonId,
											createdBy : $scope.userId,
											updatedBy : $scope.userId,
											createdDate : $scope.currentDate,
											updatedDate : $scope.currentDate,
											status : 'A',
											note : $scope.note,
											transferTypeId:4,
											transferStatusId:1
										};
										console.log(data);
										//return false;
										var URI = BASE_URL + ROOT_URL
												+ SAVETRANSFEROFCAREREQUEST;
										GenericService
												.serviceAction("POST", URI,
														data)
												.then(
														function(response) {

															if (response.data.status == "success") {
																growl
																		.success(
																				'OT Request sent sucessfully!!!.',
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
							
							$scope.displayNote=function(note)
							{
								$scope.noteDoctor = note;
								
							}
						
							
							/*$scope.acceptRequest = function(transferId) {
							
											if ($scope.popUpFlag) {
												$scope.showModal = !$scope.showModal;

											} else {
												
												$scope.currentDate = new Date();
												$scope.currentDate = moment($scope.currentDate)
														.format('DD-MM-YYYY HH:mm:ss');
												
										
										
											}
										
								
								
							}*/

						} ]);

