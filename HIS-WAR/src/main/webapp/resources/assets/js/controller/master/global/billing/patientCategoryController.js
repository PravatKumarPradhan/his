'use strict';

/**
 * @Author
 * @name myApp.controller:patientCategoryController
 * @description #patientCategoryController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'patientCategoryController',
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
						function($scope, $http, $localStorage, $sessionStorage,
								$cookies, $rootScope, GenericService, growl,
								$state, PagerService) {

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
								$scope.patientCategory = {
										isForeginerCategory:'N'
								}
								$scope.saveBtnFlag = true;
								$scope.updateBtnFlag = false;

								// for popup
								$scope.showModal = false;
								$scope.buttonClicked = "";
								$scope.popUpFlag = true;
								// end for popup

								var data = {};
							}

							$scope.init();

							// For Paginations Start
							$scope.setNoOfRecords = function() {
								$scope.initPatientCategoryMaster(
										$scope.organizationId, $scope.offset,
										$scope.noOfRecordsPerPage);
							};
							$scope.initPatientCategoryMaster = function(orgId,
									offset, noOfRecordsPerPage) {
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
										: 10;
								var URI = BASE_URL + ROOT_URL
										+ GETPATIENTCATEGORYMASTERLIST;
								var URI1 = BASE_URL + ROOT_URL
										+ GETPATIENTCATEGORYMASTERCOUNT;

								var data = {
									organizationId : orgId,
									offset : offset,
									noOfRecordsPerPage : noOfRecordsPerPage
								}

								var data1 = {
									organizationId : $scope.organizationId
								}

								// For Get patient Category List Using Offset
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.patientList = [];

													if (response.data.status == "success") {
														$scope.patientList = response.data.listObject;
														console
																.log(
																		"list",
																		$scope.patientList);

													}
												});

								// For Count patient List
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

							$scope.getPatientCategoryMasterList = function(
									orgId, offset, noOfRecordsPerPage) {
								console.log("offset", offset);
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
										: 10;
								var URI = BASE_URL + ROOT_URL
										+ GETPATIENTCATEGORYMASTERLIST;
								var data = {
									organizationId : $scope.organizationId,
									offset : offset,
									noOfRecordsPerPage : noOfRecordsPerPage
								}

								console.log("URI", URI);
								// For Get patientList Using Offset
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.patientList = [];

													if (response.data.status == "success") {
														$scope.patientList = response.data.listObject;

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
									$scope.getPatientCategoryMasterList(
											$scope.organizationId,
											$scope.pager.startIndex,
											$scope.pager.pageSize);
								}
							}

							$scope.initPatientCategoryMaster(
									$scope.organizationId, $scope.offset,
									$scope.noOfRecordsPerPage);

							// For Paginations End

							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.savePatient();
							}

							// Function For savePatient()
							$scope.savePatient = function() {
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								$scope.$broadcast('show-errors-check-validity');
								var data = {
									isForeginerCategory : $scope.patientCategory.isForeginerCategory,
									patientCategory : $scope.patientCategory.patientCate,
									patientCategoryCode : $scope.patientCategory.patientCategoryCode,
									status : 'A',
									createdBy : $scope.userId,
									updatedBy : $scope.userId,
									organizationId : $scope.organizationId,
									createdDate : $scope.currentDate,
									updatedDate : $scope.currentDate
								}
								console.log("save",data);
// return false;
								 if ($scope.patientCategoryForm.$valid) {

									if ($scope.popUpFlag) {
										$scope.showModal = !$scope.showModal;

									} else {

										var URI = BASE_URL + ROOT_URL
												+ SAVEPATIENTCATEGORYMASTER;
										GenericService
												.serviceAction("POST", URI,
														data)
												.then(
														function(response) {
															if (response.data.status == "success") {
																$scope.init();
																growl
																		.success(
																				response.data.message,
																				{
																					title : 'Success!'
																				});
															} else {
																growl
																		.error(
																				response.data.message,
																				{
																					title : 'Error!'
																				});
															}

															$scope
																	.initPatientCategoryMaster(
																			$scope.organizationId,
																			$scope.offset,
																			$scope.noOfRecordsPerPage);

														});
									}
								 }

							}

							// Function For get single Patient()
							$scope.showUpdateBtn = function(id) {

								$scope.saveBtnFlag = false;
								$scope.updateBtnFlag = true;

								var data = {

								}
								var URI = BASE_URL + ROOT_URL
										+ GETPATIENTCATEGORYMASTERBYID + "/"+ id + "/" + $scope.organizationId;
								console.log("URI",URI);
								GenericService
										.serviceAction("GET", URI, data)
										.then(
												function(response) {
													// $scope.speciality =[];
													if (response.data.status == "success") {
														$scope.patientCategory.patientCategoryId = response.data.listObject[0].patientCategoryId;
														$scope.patientCategory.patientCategoryCode = response.data.listObject[0].patientCategoryCode;
														$scope.patientCategory.patientCate = response.data.listObject[0].patientCategory;
														$scope.patientCategory.isForeginerCategory = response.data.listObject[0].isForeginerCategory;
													} else {
														alert("Error!!");
													}
												});

							};

							// Function For update country status
							$scope.updateStatus = function(id, type) {
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								type = (type == "A") ? "A" : "I";

								var data = {
										patientCategoryId : id,
									status : type,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate
								}
// console.log(data); return false;
								var URI = BASE_URL + ROOT_URL
										+ UPDATEPATIENTCATEGORYMASTERSTATUS;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													if (response.data.status == "success") {
														$scope.init();
														growl
																.success(
																		response.data.message,
																		{
																			title : 'Success!'
																		});
													} else {
														growl
																.error(
																		response.data.message,
																		{
																			title : 'Error!'
																		});
													}

												});
							}

							// Function For updateEmployee()
							$scope.updatePatient = function() {
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');
								$scope.$broadcast('show-errors-check-validity');
								var data = {
									isForeginerCategory : $scope.patientCategory.isForeginerCategory,
									patientCategory : $scope.patientCategory.patientCate,
									patientCategoryCode : $scope.patientCategory.patientCategoryCode,
									patientCategoryId : $scope.patientCategory.patientCategoryId,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate
								}
								

								if ($scope.patientCategoryForm.$valid) {
									console.log("update", data);
									var URI = BASE_URL + ROOT_URL
											+ UPDATEPATIENTCATEGORYMASTER;
									GenericService
											.serviceAction("POST", URI, data)
											.then(
													function(response) {
														if (response.data.status == "success") {
															$scope.init();
															growl
																	.success(
																			response.data.message,
																			{
																				title : 'Success!'
																			});
														} else {
															growl
																	.error(
																			response.data.message,
																			{
																				title : 'Error!'
																			});
														}
														$scope
																.initPatientCategoryMaster(
																		$scope.organizationId,
																		$scope.offset,
																		$scope.noOfRecordsPerPage);

													});
								}

							}
						} ]);
