'use strict';

/**
 * @Author By Vivek Satle
 * @name myApp.controller:transferForInvestigationController
 * @description #transferForInvestigationController Controller of the myApp
 */

angular
		.module('myApp')
		.controller(
				'transferForInvestigationController',
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

								var cookieObject = $cookies.getObject('cookieObject');
								if(cookieObject == undefined){
									$state.go('login');
									return;
								}
								$scope.unitId = cookieObject.unitId;
								$scope.organizationId = cookieObject.organizationId;
								$scope.userId = 1;

								if ($cookies.get('bedManagmentCookies') != null) {
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
										visitTypeId : 1,
										transferTypeId:1 //modality/investigation
									};

									var URI = BASE_URL + ROOT_URL
											+ GETMODALITYLISTAGAINTPATIENT;
									GenericService
											.serviceAction("POST", URI,
													dataCookies)
											.then(
													function(response) {

														if (response.data.status == "success") {
															$scope.patientModalityList = [];
															$scope.patientModalityList = response.data.listObject;
														}
													});

									var URI = BASE_URL
											+ ROOT_URL
											+ GETMODALITYTRANSFERLISTAGAINTPATIENT;
									GenericService
											.serviceAction("POST", URI,
													dataCookies)
											.then(
													function(response) {

														if (response.data.status == "success") {
															$scope.patientModalityTransferList = [];
															$scope.patientModalityTransferList = response.data.listObject;
														}
													});

								} else {
									$scope.bedManagmentCookiesObj = null;
								}

								$rootScope.loginpage = true;

								$scope.patientServiceOrder = {}
								$scope.itemSelecteds = {};

								// for popup
								$scope.showModal = false;
								$scope.buttonClicked = "";
								$scope.popUpFlag = true;
								// end for popup

								var data = {};
								var commonObj = {
									unitId : $scope.unitId,
									organizationId : $scope.organizationId,
									visitTypeId : 4
								};

							}

							$scope.init();

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
								
								var data = {
									admissionId : info.admissionId,
									organizationId : $scope.organizationId,
									unitId : $scope.unitId,
									visitTypeId : 1,
									transferTypeId:1 //modality/investigation
								};

								if (info.t_patient_id > 0) {
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
								
								
								var URI = BASE_URL + ROOT_URL
										+ GETMODALITYLISTAGAINTPATIENT;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													if (response.data.status == "success") {
														$scope.patientModalityList = [];
														$scope.patientModalityList = response.data.listObject;
													}
												});
								
								var URI = BASE_URL + ROOT_URL
										+ GETMODALITYTRANSFERLISTAGAINTPATIENT;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {

													if (response.data.status == "success") {
														$scope.patientModalityTransferList = [];
														$scope.patientModalityTransferList = response.data.listObject;
													}
												});

							}

							$scope.selectItem = function(item) {

								$scope.itemSelecteds = [];
								// If checkbox is checked
								if ($scope.patientServiceOrder) {
									$scope.itemSelecteds = item;
								}
								console.log($scope.itemSelecteds);
							}

							// function for initializa clockpicker
							$scope.initClockPicker = function() {

								$('.clockpicker').clockpicker();
							}

							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.saveModalityRequest();
							}

							$scope.saveModalityRequest = function() {

								if (Object.keys($scope.itemSelecteds).length === 0) {
									growl
											.error(
													'Please select atleast one Service!!!.',
													{
														title : 'Error!'
													});
								} else {
									if ($scope.popUpFlag) {
										$scope.showModal = !$scope.showModal;

									} else {

										$scope.currentDate = new Date();
										$scope.currentDate = moment(
												$scope.currentDate).format(
												'DD-MM-YYYY HH:mm:ss');
										var data = {
											admissionId : $scope.itemSelecteds.admissionId,
											organizationId : $scope.organizationId,
											unitId : $scope.unitId,
											modalityTime : $scope.itemSelecteds.fromTime2,
											transferTime : $scope.itemSelecteds.transferTime,
											destinationId : 0,
											schedularId : $scope.itemSelecteds.scheduleId,
											patientServiceOrderId : $scope.itemSelecteds.patientSevicesOrderId,
											createdBy : $scope.userId,
											updatedBy : $scope.userId,
											createdDate : $scope.currentDate,
											updatedDate : $scope.currentDate,
											status : 'A',
											note : $scope.itemSelecteds.note,
											modalityId : $scope.itemSelecteds.modalityId,
										};
										console.log(data);
										//return false;
										var URI = BASE_URL + ROOT_URL
												+ SAVEMODALITYTRANSFERREQUEST;
										GenericService
												.serviceAction("POST", URI,
														data)
												.then(
														function(response) {

															if (response.data.status == "success") {
																growl
																		.success(
																				'Modality Request sent sucessfully!!!.',
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
