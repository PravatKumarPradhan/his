'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:modalityMasterController
 * @description #modalityMasterController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'modalityMasterController',
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
								$state, PagerService,BillingGenericService) {

							/* init() function for form object create. */
							$scope.init = function() {

								$scope.unitId = 1;
								$scope.organizationId = 1;
								$scope.userId = 1;
								if ($scope.userId == null) {
									$rootScope.loginpage = false;
									$state.go('login');
								}

								/** Variables for pagination */
								$scope.offset = 0;
								$scope.noOfRecordsPerPage = 10;
								$scope.totalNoOfRecords;
								$scope.searchKeyword;
								$scope.commonListCount;

								$rootScope.loginpage = true;
								$scope.saveBtnFlag = false;
								$scope.updateBtnFlag = true;

								// for popup
								$scope.showModal = false;
								$scope.buttonClicked = "";
								$scope.popUpFlag = true;
								// end for common popup
								
								
								$scope.modalityObj = 
								{
										modalityId:0,
										modalityCode : null,
										modalityDesc:null,
										equipmentId:'',
										specialityId:'',
										subSpecialityId:'',
										organizationId:$scope.organizationId,
										unitId:$scope.unitId,
										updatedBy:$scope.userId,
										createdBy:$scope.userId,
										status:'A'
								};
								
								

								var data = {};
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
								
							}

							$scope.init();
							
							//Get Sub-speciality By Speciality Id
							$scope.getSubSpeciality = function(specialityId)
							{
								var data = {
										unitId : $scope.unitId,
										organizationId : $scope.organizationId,
										specialityId:specialityId
									}

									var URI = BASE_URL + ROOT_URL
									+ GETSUBSPECIALITYBYSPECIALITYID;
						
							BillingGenericService
									.serviceAction(METHOD_POST, URI,
											data,
											NOTIFICATION_MSG_STATUS_FALSE)
									.then(
											function(response) {
												console.log(response);
												// $rootScope.startLoader();
												if (response.data.status == "success")
													$scope.subSpecialityList = response.data.listObject;
											});
							}

							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.saveModality();
							}
							// For Paginations Start
							$scope.setNoOfRecords = function() {
								$scope.initStateMasterList(
										$scope.organizationId, $scope.offset,
										$scope.noOfRecordsPerPage);
							};
							$scope.initStateMasterList = function(orgId,
									offset, noOfRecordsPerPage) {
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
										: 10;
								var URI = BASE_URL + ROOT_URL + GETMODALITYMASTERLIST;
								var URI1 = BASE_URL + ROOT_URL
										+ GETMODALITYCOUNT;

								var data = {
									organizationId : orgId,
									unitId:$scope.unitId,
									offset : offset,
									noOfRecordsPerPage : noOfRecordsPerPage
								}

								var data1 = {
									organizationId : $scope.organizationId,
									unitId:$scope.unitId
								}

								// For Get State List Using Offset
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.modalityList= [];

													if (response.data.status == "success") {
														$scope.modalityList = response.data.listObject;

													}
												});

								// For Count State List
								GenericService
										.serviceAction("POST", URI1, data1)
										.then(
												function(response) {
													if (response.data.status == "success") {
														console
																.log(response.data);
														$scope.commonListCount = response.data.object;
														$scope
																.setPage(1,
																		false);

													}
												});
							}

							$scope.getStateMasterList = function(orgId, offset,
									noOfRecordsPerPage) {
								console.log("offset", offset);
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
										: 10;
								var URI = BASE_URL + ROOT_URL + GETMODALITYMASTERLIST;
								var data = {
									organizationId : $scope.organizationId,
									unitId:$scope.unitId,
									offset : offset,
									noOfRecordsPerPage : noOfRecordsPerPage
								}

								console.log("URI", URI);
								// For Get State List Using Offset
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.modalityList = [];

													if (response.data.status == "success") {
														$scope.modalityList = response.data.listObject;

													}
												});
							}

							$scope.pager = {};
							$scope.page;

							$scope.setPage = function(page, flag) {
								if (page < 1 || page > $scope.pager.totalPages) {
									return;
								}
								$scope.pager = PagerService.GetPager(
										$scope.commonListCount, page,
										$scope.noOfRecordsPerPage);
								if (flag) {
									$scope.getStateMasterList(
											$scope.organizationId,
											$scope.pager.startIndex,
											$scope.pager.pageSize);
								}
							}

							$scope.initStateMasterList($scope.organizationId,
									$scope.offset, $scope.noOfRecordsPerPage);

							// For Paginations End

							// Function for save Modality Type
							$scope.saveModality = function() {
								
								$scope.$broadcast('show-errors-check-validity');
								
								if ($scope.modalityForm.$valid) {
									
									if ($scope.popUpFlag) {
										$scope.showModal = !$scope.showModal;

									} else {
										
										$scope.currentDate = new Date();
										$scope.currentDate = moment($scope.currentDate)
												.format('DD-MM-YYYY HH:mm:ss');
										
										$scope.modalityObj.createdDate  = $scope.currentDate;
										$scope.modalityObj.updatedDate  = $scope.currentDate;
										console.log($scope.modalityObj);
										//return false;
										var URI = BASE_URL + ROOT_URL
												+ ADDMODALITYMASTER;
										GenericService
												.serviceAction("POST", URI,
														$scope.modalityObj)
												.then(
														function(response) {

															if (response.data.status == "success") {
																growl
																		.success(
																				'Modality added sucessfully!!!.',
																				{
																					title : 'Success!'
																				});
																$scope
																		.$broadcast('show-errors-reset');
																$scope.init();
															} else {
																growl
																		.error(
																				'Modality already exits!!!!.',
																				{
																					title : 'Error!'
																				});
															}
															$scope
																	.initStateMasterList(
																			$scope.organizationId,
																			$scope.offset,
																			$scope.noOfRecordsPerPage);

														});
									}
								}

							}

							// Function for get single Modality Type
							$scope.showUpdateBtn = function(id) {

								$scope.saveBtnFlag = true;
								$scope.updateBtnFlag = false;

								var data = {

									modalityId : id,
									unitId:$scope.unitId,
									organizationId:$scope.organizationId
								}
								console.log(data);
								//return false;
								var URI = BASE_URL + ROOT_URL + GETMODALITYBYID;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													// $scope.speciality =[];
													if (response.data.status == "success") {
														$scope.modalityObj.modalityId = response.data.listObject[0].modalityId;
														$scope.modalityObj.specialityId = response.data.listObject[0].specialityId.toString();
														$scope.getSubSpeciality(response.data.listObject[0].specialityId);
														$scope.modalityObj.subSpecialityId = response.data.listObject[0].subSpecialityId.toString();
														$scope.modalityObj.modalityCode = response.data.listObject[0].modalityCode;
														$scope.modalityObj.modalityDesc = response.data.listObject[0].modalityDesc;
														$scope.modalityObj.equipmentId = response.data.listObject[0].equipmentId.toString();
														
													} else {
														alert("Error!!");
													}
												});

							};

							// Function for update state status
							$scope.updateStatus = function(id, type) {
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								type = (type == "A") ? "A" : "I";
								var data = {
									modalityId : id,
									status : type,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate,
									unitId:$scope.unitId,
									organizationId:$scope.organizationId
								}
								console.log(data);
								//return false;
								var URI = BASE_URL + ROOT_URL
										+ UPDATESTATUSFORMODALITY;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {

													if (response.data.status == "success") {
														growl
																.success(
																		'Modality status changed sucessfully!!!.',
																		{
																			title : 'Success!'
																		});
													} else {
														growl
																.error(
																		'something wrong!!!!.',
																		{
																			title : 'Error!'
																		});
													}
												});
							}

							// Function for update state
							$scope.updateModality = function() {
								
								$scope.$broadcast('show-errors-check-validity');

								if ($scope.modalityForm.$valid) {
									
									$scope.currentDate = new Date();
									$scope.currentDate = moment($scope.currentDate)
											.format('DD-MM-YYYY HH:mm:ss');
									
									$scope.modalityObj.updatedDate  = $scope.currentDate;
									console.log($scope.modalityObj);
									 //return false;
									var URI = BASE_URL + ROOT_URL + UPDATEMODALITYMASTER;
									GenericService
											.serviceAction("POST", URI, $scope.modalityObj)
											.then(
													function(response) {

														if (response.data.status == "success") {
															growl
																	.success(
																			'Modality updated sucessfully!!!.',
																			{
																				title : 'Success!'
																			});
															$scope
																	.$broadcast('show-errors-reset');
															$scope.init();
														} else {
															growl
																	.error(
																			'Modality already exits!!!!.',
																			{
																				title : 'Error!'
																			});
														}
														$scope.initStateMasterList(
																		$scope.organizationId,
																		$scope.offset,
																		$scope.noOfRecordsPerPage);

													});
								}

							}
						} ]);
