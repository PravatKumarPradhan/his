'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:modalityTypeMasterController
 * @description #modalityTypeMasterController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'modalityTypeMasterController',
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
								
								
								$scope.modalityTypeObj = 
								{
										modalityTypeId:0,
										modalityTypeCode : null,
										modalityTypeDesc:null,
										specialityId:'',
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

							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.saveModalityType();
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
								var URI = BASE_URL + ROOT_URL + GETMODALITYTYPEMASTERLIST;
								var URI1 = BASE_URL + ROOT_URL
										+ GETMODALITYTYPECOUNT;

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
													$scope.modalityTypeList= [];

													if (response.data.status == "success") {
														$scope.modalityTypeList = response.data.listObject;

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
								var URI = BASE_URL + ROOT_URL + GETMODALITYTYPEMASTERLIST;
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
													$scope.modalityTypeList = [];

													if (response.data.status == "success") {
														$scope.modalityTypeList = response.data.listObject;

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
							$scope.saveModalityType = function() {
								
								$scope.$broadcast('show-errors-check-validity');
								
								if ($scope.modalityTypeForm.$valid) {
									
									if ($scope.popUpFlag) {
										$scope.showModal = !$scope.showModal;

									} else {
										
										$scope.currentDate = new Date();
										$scope.currentDate = moment($scope.currentDate)
												.format('DD-MM-YYYY HH:mm:ss');
										
										$scope.modalityTypeObj.createdDate  = $scope.currentDate;
										$scope.modalityTypeObj.updatedDate  = $scope.currentDate;
										console.log($scope.modalityTypeObj);
										//return false;
										var URI = BASE_URL + ROOT_URL
												+ ADDMODALITYTYPEMASTER;
										GenericService
												.serviceAction("POST", URI,
														$scope.modalityTypeObj)
												.then(
														function(response) {

															if (response.data.status == "success") {
																growl
																		.success(
																				'Modality type added sucessfully!!!.',
																				{
																					title : 'Success!'
																				});
																$scope
																		.$broadcast('show-errors-reset');
																$scope.init();
															} else {
																growl
																		.error(
																				'Modality type already exits!!!!.',
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

									modalityTypeId : id,
									organizationId:$scope.organizationId,
									unitId:$scope.unitId
								}
								console.log(data);
								//return false;
								var URI = BASE_URL + ROOT_URL + GETMODALITYTYPEBYID;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													// $scope.speciality =[];
													if (response.data.status == "success") {
														$scope.modalityTypeObj.modalityTypeId = response.data.listObject[0].modalityTypeId;
														$scope.modalityTypeObj.modalityTypeCode = response.data.listObject[0].modalityTypeCode;
														$scope.modalityTypeObj.modalityTypeDesc = response.data.listObject[0].modalityTypeDesc;
														$scope.modalityTypeObj.specialityId = response.data.listObject[0].specialityId.toString();
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
									modalityTypeId : id,
									status : type,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate,
									organizationId:$scope.organizationId,
									unitId:$scope.unitId
								}
								console.log(data);
							//	return false;
								var URI = BASE_URL + ROOT_URL
										+ UPDATESTATUSFORMODALITYTYPE;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {

													if (response.data.status == "success") {
														growl
																.success(
																		'Modality type status changed sucessfully!!!.',
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
							$scope.updateModalityType = function() {
								
								$scope.$broadcast('show-errors-check-validity');

								if ($scope.modalityTypeForm.$valid) {
									
									$scope.currentDate = new Date();
									$scope.currentDate = moment($scope.currentDate)
											.format('DD-MM-YYYY HH:mm:ss');
									
									$scope.modalityTypeObj.updatedDate  = $scope.currentDate;
									console.log($scope.modalityTypeObj);
									 //return false;
									var URI = BASE_URL + ROOT_URL + UPDATEMODALITYTYPEMASTER;
									GenericService
											.serviceAction("POST", URI, $scope.modalityTypeObj)
											.then(
													function(response) {

														if (response.data.status == "success") {
															growl
																	.success(
																			'Modality type updated sucessfully!!!.',
																			{
																				title : 'Success!'
																			});
															$scope
																	.$broadcast('show-errors-reset');
															$scope.init();
														} else {
															growl
																	.error(
																			'Modality type already exits!!!!.',
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
						} ]);


