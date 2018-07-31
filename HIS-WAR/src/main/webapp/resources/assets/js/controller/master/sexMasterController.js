'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:sexMasterController
 * @description #sexMasterController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'sexMasterController',
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
								$state,PagerService) {

							// init function description
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
								
								/**Variables for pagination */
							    $scope.offset = 0;
								$scope.noOfRecordsPerPage = 10;
								$scope.totalNoOfRecords;
								$scope.searchKeyword;
								$scope.commonListCount;
								

								$scope.LISDynamicLabel = "Sex";
								$scope.saveBtnFlag = true;
								$scope.updateBtnFlag = false;

								$scope.common = {};

								/* code for setting Label */
								$(".selectedPageName").text("Sex Master");

								// for popup
								$scope.showModal = false;
								$scope.buttonClicked = "";
								$scope.popUpFlag = true;
								// end for common popup

								$rootScope.loginpage = true;

								var data = {};
								// $scope.status = "NO";
							/*	var URI = BASE_URL + ROOT_URL + GETSEXLIST;
								GenericService
										.serviceAction("GET", URI, data)
										.then(
												function(response) {
													$scope.commonList = [];

													if (response.data.status == "success") {
														$scope.commonList = response.data.listObject;

													}
												});*/

							};
							$scope.init();
							
							
							//For Paginations Start
							$scope.setNoOfRecords = function() {
								$scope.initSexMasterList($scope.organizationId, $scope.offset, $scope.noOfRecordsPerPage);
							};
							$scope.initSexMasterList = function(orgId, offset, noOfRecordsPerPage)
							{
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage : 10;
								//var URI1 = BASE_URL + ROOT_URL +  LIST_SAMPLE_MASTER + S + orgId + S + offset+ S + noOfRecordsPerPage;
								//var URI2 = BASE_URL + ROOT_URL + LIS_GLOBAL + COUNT_SAMPLE_MASTER + S + orgId;
								var URI = BASE_URL + ROOT_URL + GETSEXLIST;
								var URI1 = BASE_URL + ROOT_URL + COUNTSEXMASTER;
								
								var data = {
										organizationId:orgId,
										offset : offset,
										noOfRecordsPerPage : noOfRecordsPerPage
								}
								
								var data1 = {
										organizationId:$scope.organizationId
								}
								
								//For Get Sex List Using Offset
								GenericService
								.serviceAction("POST", URI, data)
								.then(
										function(response) {
											$scope.commonList = [];

											if (response.data.status == "success") {
												$scope.commonList = response.data.listObject;

											}
										});
								
								//For Count Sex List
								GenericService
								.serviceAction("POST", URI1, data1)
								.then(
										function(response) {
											if (response.data.status == "success") {
												console.log(response.data);
												$scope.commonListCount = response.data.object;
												$scope.setPage(1, false);

											}
										});
								
							}

							$scope.getSexMasterList = function(orgId, offset, noOfRecordsPerPage) {
								console.log("offset", offset);
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage : 10;
								var URI = BASE_URL + ROOT_URL + GETSEXLIST;
								var data = {
										organizationId:$scope.organizationId,
										offset : offset,
										noOfRecordsPerPage : noOfRecordsPerPage
								}
								
								console.log("URI", URI);
								//For Get Sex List Using Offset
								GenericService
								.serviceAction("POST", URI, data)
								.then(
										function(response) {
											$scope.commonList = [];

											if (response.data.status == "success") {
												$scope.commonList = response.data.listObject;

											}
										});
							}

							$scope.pager = {};
							$scope.page;

							$scope.setPage = function(page, flag) {
								if (page < 1 || page > $scope.pager.totalPages)
								{
									return;
								}
								$scope.pager = PagerService.GetPager($scope.commonListCount, page, $scope.noOfRecordsPerPage);
								if (flag)
								{
									$scope.getSexMasterList($scope.organizationId, $scope.pager.startIndex, $scope.pager.pageSize);
								}
							}

							$scope.initSexMasterList($scope.organizationId, $scope.offset, $scope.noOfRecordsPerPage);
							
							//For Paginations End

							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.save();
							}

							// Function for save sex
							$scope.save = function() {

								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								$scope.$broadcast('show-errors-check-validity');
								if ($scope.commonform.$valid) {
									if ($scope.popUpFlag) {
										$scope.showModal = !$scope.showModal;

									} else {

										var data = {
											code : $scope.common.code,
											desc : $scope.common.desc,
											status : 'A',
											createdBy : $scope.userId,
											updatedBy : $scope.userId,
											organizationId : $scope.organizationId,
											updatedDate : $scope.currentDate,
											createdDate : $scope.currentDate
										}

										console.log(data);
										var URI = BASE_URL + ROOT_URL + ADDSEX;
										GenericService
												.serviceAction("POST", URI,
														data)
												.then(
														function(response) {

															if (response.data.status == "success") {
																growl
																		.success(
																				'Sex added sucessfully!!!.',
																				{
																					title : 'Success!'
																				});

																$scope
																		.$broadcast('show-errors-reset');
																$scope.init();
															} else {
																growl
																		.error(
																				'Sex already exits!!!!.',
																				{
																					title : 'Error!'
																				});
															}
															$scope.initSexMasterList($scope.organizationId, $scope.offset, $scope.noOfRecordsPerPage);
														});
									}
								}

							};

							// function for get sex object for update
							$scope.showUpdateBtn = function(id) {

								$scope.saveBtnFlag = false;
								$scope.updateBtnFlag = true;

								var data = {
									id : id
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL + GETSINGLESEX;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													if (response.data.status == "success") {
														$scope.common.incremetedId = response.data.listObject[0].id;
														$scope.common.code = response.data.listObject[0].code;
														$scope.common.desc = response.data.listObject[0].desc;
													} else {
														alert("Error!!");
													}
												});

							};

							// function for checked status
							$scope.checkVal = function(statussex) {
								$scope.status = (statussex == "A") ? "A" : "I";
								return (statussex == "A") ? true : false;
							}

							// function for update status sex
							$scope.updateStatus = function(id, type) {

								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								type = (type == "A") ? "A" : "I";
								var data = {
									id : id,
									status : type,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL + UPDATESEXSTATUS;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {

													if (response.data.status == "success") {
														growl
																.success(
																		'Sex status changed sucessfully!!!.',
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

							// function for update sex
							$scope.update = function() {
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								$scope.$broadcast('show-errors-check-validity');
								if ($scope.commonform.$valid) {
									var data = {
										id : $scope.common.incremetedId,
										code : $scope.common.code,
										desc : $scope.common.desc,
										updatedBy : $scope.userId,
										updatedDate : $scope.currentDate
									}
									console.log(data);
									var URI = BASE_URL + ROOT_URL + UPDATESEX;
									GenericService
											.serviceAction("POST", URI, data)
											.then(
													function(response) {

														if (response.data.status == "success") {
															growl
																	.success(
																			'Sex updated sucessfully!!!.',
																			{
																				title : 'Success!'
																			});
															$scope
																	.$broadcast('show-errors-reset');
															$scope.init();
															// alert("ds");

														} else {
															growl
																	.error(
																			'Sex already exits!!!!.',
																			{
																				title : 'Error!'
																			});
														}
														$scope.initSexMasterList($scope.organizationId, $scope.offset, $scope.noOfRecordsPerPage);
													});
								}
							}

						} ]);
