'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:shortLeaveStatusController
 * @description #shortLeaveStatusController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'shortLeaveStatusController',
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
								
								$scope.shortLeaveRequest = {}
								$scope.itemSelecteds = {};
								
								$rootScope.loginpage = true;
								$scope.saveBtnFlag = false;
								$scope.updateBtnFlag = true;
								
							/*	$scope.consentFormFlag  = false;
								$scope.uploadDocumentFlag = false;
								$scope.handOverMedicationFlag   = false;
								$scope.releasepatientFlag = false;*/
					

								// for popup
								$scope.showModal = false;
								$scope.buttonClicked = "";
								$scope.popUpFlag = true;
								// end for popup

								var shortLeaveStatusList = [];
								shortLeaveStatusList.push(1);
								shortLeaveStatusList.push(2);
								shortLeaveStatusList.push(3);
								shortLeaveStatusList.push(4);
								shortLeaveStatusList.push(5);
								shortLeaveStatusList.push(6);
								shortLeaveStatusList.push(7);
								
								var data = {
									organizationId : $scope.organizationId,
									unitId : $scope.unitId,
									shortLeaveStatusIdList :shortLeaveStatusList
									
								};

								var URI = BASE_URL + ROOT_URL
										+ GETSHORTLEAVEREQUESTLIST;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.shortLeaveRequestList = [];

													if (response.data.status == "success") {
														$scope.shortLeaveRequestList = response.data.listObject;

													}
												});
							

							}

							$scope.init();

							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.saveShortLeaveRequest();
							}

							$scope.selectItem = function(item) {
								
								angular.element( document.querySelector( '#consentForm' ) ).addClass('disabled');
				            	angular.element( document.querySelector( '#uploadDocument' ) ).addClass('disabled');
				            	angular.element( document.querySelector( '#handoverMedication' ) ).addClass('disabled');
				            	angular.element( document.querySelector( '#releasePatient' ) ).addClass('disabled');

								$scope.itemSelecteds = [];
								// If checkbox is checked
								if ($scope.shortLeaveRequest) {
									$scope.itemSelecteds = item;
									
									/*$scope.consentFormFlag  = false;
									$scope.uploadDocumentFlag = false;
									$scope.handOverMedicationFlag   = false;
									$scope.releasepatientFlag = false;*/
									if(item.shortLeaveStatusId != 1 )
										{
										 angular.element( document.querySelector( '#consentForm' ) ).removeClass('disabled');
						            	 angular.element( document.querySelector( '#uploadDocument' ) ).removeClass('disabled');
										}
									if(item.shortLeaveStatusId == 3 && item.isHandoverMedication == 'Y')
										{
										angular.element( document.querySelector( '#releasePatient' ) ).removeClass('disabled');
										}
									if(item.isHandoverMedication == 'Y')
									{
									angular.element( document.querySelector( '#handoverMedication' ) ).removeClass('disabled');
									}
									
									
								}
							}
							$scope.commonFuntion = function() {
								console.log($scope.itemSelecteds);
								if (Object.keys($scope.itemSelecteds).length === 0) {
									growl
											.error(
													'Please select atleast one patient!!!.',
													{
														title : 'Error!'
													});
								}
								else{
								
								if($scope.itemSelecteds.patientId > 0)
								{
								var age = $rootScope
								.getAge($scope.itemSelecteds.dob);
								$scope.patientFullName =$scope.itemSelecteds.patientName
								+ " "
								+age
								+ "/"
								+ $scope.itemSelecteds.genderCode;
								}
							else
								{
								$scope.patientFullName = $scope.itemSelecteds.patientName
								+ " "
								+ $scope.itemSelecteds.dob
								+ "/"
								+ $scope.itemSelecteds.genderCode;
								}
								$scope.uhidNum = $scope.itemSelecteds.uhidNumber;
								$scope.doctorFullName =$scope.itemSelecteds.doctorName;
								$scope.bedNumber = $scope.itemSelecteds.bedNumber;
								$scope.dateOfAdmission =$scope.itemSelecteds.doa;
								$scope.patientDischargeDate = $scope.itemSelecteds.pdd;
								$scope.identificationNo = $scope.itemSelecteds.identificationNo;
								$scope.admissionId = $scope.itemSelecteds.admissionId;

							}
							}
							
							$scope.releasePatient = function()
							{
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');
								if (Object.keys($scope.itemSelecteds).length === 0) {
									growl
											.error(
													'Please select atleast one patient!!!.',
													{
														title : 'Error!'
													});
								}
								else{
									
									var data = {
											admissionId : $scope.itemSelecteds.admissionId,
											shortLeaveId : $scope.itemSelecteds.shortLeaveId,
											updatedBy : $scope.userId,
											organizationId : $scope.organizationId,
											unitId : $scope.unitId,
											updatedDate : $scope.currentDate,

										}
										console.log(data);
										//return false;
										var URI = BASE_URL + ROOT_URL
												+ RELEASEPATIENTFORSHOTLEAVE;
										GenericService
												.serviceAction("POST", URI,
														data)
												.then(
														function(response) {

															if (response.data.status == "success") {
																growl
																		.success(
																				'Patient released sucessfully!!!.',
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
