/**
 * surgeryMasterController
 */
'use strict';

/**
 * @Author By Dinesh Jagatap
 * @name myApp.controller:surgeryMasterController
 * @description #surgeryMasterController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'surgeryMasterController',
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
						'PagerService',
						'BillingGenericService',
						function($scope, $http, $localStorage, $sessionStorage,
								$cookies, $rootScope, GenericService, growl,
								$state, PagerService, BillingGenericService) {

							/* init() function for form object create. */
							$scope.init = function() {

								/*
								 * $scope.unitId =
								 * sessionStorage.getItem("unitId");
								 * $scope.organizationId =
								 * sessionStorage.getItem("organizationId");
								 * $scope.userId =
								 * sessionStorage.getItem("userId");
								 */
								$scope.unitId = 1;
								$scope.organizationId = 1;
								$scope.userId = 1;
								if ($scope.userId == null) {
									$rootScope.loginpage = false;
									$state.go('login');
								}

								$rootScope.loginpage = true;							
								$scope.saveBtnFlag = false;
								$scope.updateBtnFlag = true;

								// for popup
								$scope.showModal = false;
								$scope.buttonClicked = "";
								$scope.popUpFlag = true;
								// end for popup

								$scope.SurgeryMasterObj = {
									unitId : $scope.unitId,
									organizationId : $scope.organizationId,
									surgery_id:'',
									specialityId:'',
									surgeryCode:'',
									surgeryCodeCpt:'',
									surgeryName:'',
									surgeryGrade : '',
									surgeryGradeId : '',
									status:''
								}

								var commonObj = {
									unitId : $scope.unitId,
									organizationId : $scope.organizationId
								}
								// for getting speciality 
								var URI = BASE_URL + ROOT_URL
										+ GETACTIVESPECIALITY;
								BillingGenericService
										.serviceAction(METHOD_POST, URI,
												commonObj,
												NOTIFICATION_MSG_STATUS_FALSE)
										.then(function(response) {
													console.log(response);
													if (response.data.status == "success")
														$scope.specialityList = response.data.listObject;
													console.log("SPECILITY",$scope.specialityList);
												});
								
								//for getting the surgery master grade 
								
								var commonObj = {
										orgnisationId : $scope.organizationId
									}

									var URI = BASE_URL + ROOT_URL
											+ GETSURGERYGRADEMASTERLIST;
								
									BillingGenericService
											.serviceAction(METHOD_POST, URI,
													commonObj,
													NOTIFICATION_MSG_STATUS_FALSE)
											.then(function(response) {
														console.log(response);
														if (response.data.status == "success")
															$scope.surgeryGradeList= response.data.listObject;
													});		

									//get surgery master list
								var data = {};
								var commonObj = {
										unitId : $scope.unitId,
										organizationId : $scope.organizationId
									}
							
								var URI = BASE_URL + ROOT_URL
										+ GETSURGERYMASTERLIST;

								BillingGenericService
										.serviceAction(METHOD_POST, URI,
												commonObj,
												NOTIFICATION_MSG_STATUS_FALSE)
										.then(function(response) {
													console.log(response);
													if (response.data.status == "success")
														$scope.surgeryMasterList = response.data.listObject;
													 console.log("masterList",$scope.surgeryMasterList);
												});

								$scope.surgeryGradeObj = {
									surgeryGrade : '',
									surgeryGradeId : '',
									surgeryGradeCode : ''
								}

							}

							$scope.init();

							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.saveSurgeryMaster();
							}

							// Function For save surgery master
							$scope.saveSurgeryMaster = function() {
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');
								$scope.$broadcast('show-errors-check-validity');

								var data = {
									specialityId   : $scope.SurgeryMasterObj.specialityId,
									surgeryCode    : $scope.SurgeryMasterObj.surgeryCode,
									surgeryCodeCpt : $scope.SurgeryMasterObj.surgeryCodeCpt,
									surgeryName    : $scope.SurgeryMasterObj.surgeryName,
									surgeryGradeId : $scope.SurgeryMasterObj.surgeryGradeId,
									unitId         : $scope.unitId,
									organizationId : $scope.organizationId,
									status         : 'A',
									createdDate    : $scope.currentDate,
									createdBy      : $scope.userId,
									updatedDate    : $scope.currentDate,
									updatedBy      : $scope.userId,
								}
								console.log("surgery master", data);
//								 return false;
								if ($scope.surgeryMasterForm.$valid) {
									if ($scope.popUpFlag) {
										$scope.showModal = !$scope.showModal;

									} else {
										var URI = BASE_URL + ROOT_URL
												+ SAVESURGERYMASTER;
										GenericService.serviceAction("POST", URI,data)
												.then(function(response) {
															if (response.data.status == "success") {
																growl.success('Surgery master added sucessfully!!!.',
																				{
																					title : 'Success!'
																				});
																$scope.$broadcast('show-errors-reset');
																$scope.init();
															} else {
																growl.error('Surgery master already exits!!!!.',
																				{
																					title : 'Error!'
																				});
															       }
														});
									 } 
								}

							}

							// Function For get single surgery master
							$scope.showUpdateBtn = function(id) {

								$scope.saveBtnFlag = true;
								$scope.updateBtnFlag = false;

								var data = {
										      surgery_id     : id,
										      unitId         : $scope.unitId,
											  organizationId : $scope.organizationId
								           }
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ GETSURGERYMASTERBYID;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													if (response.data.status == "success") {
//														console.log("hello",response.data.listObject[0]);
														$scope.SurgeryMasterObj.surgery_id = response.data.listObject[0].surgery_id;
														$scope.SurgeryMasterObj.specialityId = response.data.listObject[0].specialityId.toString();
														$scope.SurgeryMasterObj.surgeryGradeId = response.data.listObject[0].surgeryGradeId.toString();
														$scope.SurgeryMasterObj.surgeryCode = response.data.listObject[0].surgeryCode;
														$scope.SurgeryMasterObj.surgeryCodeCpt = response.data.listObject[0].surgeryCodeCpt;
														$scope.SurgeryMasterObj.surgeryName = response.data.listObject[0].surgeryName;											

													} else {
														alert("Error!!");
													}
												});

							};

							// Function For update surgery grade status
							$scope.updateStatus = function(id, type) {
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								type = (type == "A") ? "A" : "I";

								var data = {
										surgery_id 	: id,
									    status 		: type,
									    updatedBy 	: $scope.userId,
									    updatedDate : $scope.currentDate
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ UPDATESTATUSFORSURGERYMASTER;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {

													if (response.data.status == "success") {
														growl.success('Surgery master status changed sucessfully!!!.',
																		{
																			title : 'Success!'
																		});
													} else {
														growl.error('something wrong!!!!.',
																		{
																			title : 'Error!'
																		});
													   }
												});
							}

							// Function For update surgery master
							$scope.updateSurgeryMaster = function() {
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');
								$scope.$broadcast('show-errors-check-validity');
								var data = {
										surgery_id 		: $scope.SurgeryMasterObj.surgery_id,
										specialityId 	: $scope.SurgeryMasterObj.specialityId,
										surgeryCode		: $scope.SurgeryMasterObj.surgeryCode,
										surgeryCodeCpt	: $scope.SurgeryMasterObj.surgeryCodeCpt,
										surgeryName 	: $scope.SurgeryMasterObj.surgeryName,
										surgeryGradeId 	: $scope.SurgeryMasterObj.surgeryGradeId,
										updatedBy 		: $scope.userId,
										updatedDate 	: $scope.currentDate
								   }
//								console.log("hello",data); return false;
								if ($scope.surgeryMasterForm.$valid) {
									console.log(data);
									var URI = BASE_URL + ROOT_URL
											+ UPDATESURGERYMASTER;
									GenericService
											.serviceAction("POST", URI, data)
											.then(
													function(response) {
														if (response.data.status == "success") {
															growl.success('Surgery master updated sucessfully!!!.',
																			{
																				title : 'Success!'
																			});
															$scope.$broadcast('show-errors-reset');
															$scope.init();
														} else {
															growl.error('Surgery master already exits!!!!.',
																			{
																				title : 'Error!'
																			});
														}

													});
								       }
							}

						} ]);
