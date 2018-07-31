'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:bedCategoryMasterController
 * @description # bedCategoryMasterController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'bedCategoryMasterController',
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

							// init function description
							$scope.init = function() {

								var cookieObject = $cookies.getObject('cookieObject');
								if(cookieObject == undefined){
									$state.go('login');
									return;
								}
								$scope.unitId = cookieObject.unitId;
								$scope.organizationId = cookieObject.organizationId;
								$scope.userId = 1;
								

								/** Variables for pagination */
								$scope.offset = 0;
								$scope.noOfRecordsPerPage = 10;
								$scope.totalNoOfRecords;
								$scope.searchKeyword;
								$scope.commonListCount;

								// for popup
								$scope.showModal = false;
								$scope.buttonClicked = "";
								$scope.popUpFlag = true;
								// end for common popup

								$rootScope.loginpage = true;

								$scope.bedCategory = {
									isBedRetention : 'N'
								}

								$scope.saveBtnFlag = false;
								$scope.updateBtnFlag = true;

								var data = {};
								var commonobj = {
										organizationId : $scope.organizationId
								}
								// $scope.status = "NO";
								/*
								 * var URI = BASE_URL + ROOT_URL +
								 * GETBEDCATEGORYLIST; GenericService
								 * .serviceAction("GET", URI, data) .then(
								 * function(response) { $scope.bedCategoryList =
								 * [];
								 * 
								 * if (response.data.status == "success") {
								 * $scope.bedCategoryList =
								 * response.data.listObject;
								 *  } });
								 */

								var URI = BASE_URL + ROOT_URL
										+ GETACTIVEHIERARCHYLIST;
								GenericService
										.serviceAction("GET", URI, data)
										.then(
												function(response) {
													$scope.hierarchyList = [];

													if (response.data.status == "success") {
														$scope.hierarchyList = response.data.listObject;

													}
												});

								var URI = BASE_URL + ROOT_URL
										+ GETOCCUPANCYUNITLIST;
								GenericService
										.serviceAction("POST", URI, commonobj)
										.then(
												function(response) {
													$scope.occupancyUnitList = [];

													if (response.data.status == "success") {
														$scope.occupancyUnitList = response.data.listObject;

													}
												});

							};
							$scope.init();

							// For Paginations Start
							$scope.setNoOfRecords = function() {
								$scope.initBedCategoryMasterList(
										$scope.organizationId, $scope.offset,
										$scope.noOfRecordsPerPage);
							};
							$scope.initBedCategoryMasterList = function(orgId,
									offset, noOfRecordsPerPage) {
								$rootScope.startLoader();
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
										: 10;
								var URI = BASE_URL + ROOT_URL
										+ GETBEDCATEGORYLIST;
								var URI1 = BASE_URL + ROOT_URL
										+ COUNTBEDCATEGORYMASTER;

								var data = {
									organizationId : orgId,
									offset : offset,
									noOfRecordsPerPage : noOfRecordsPerPage
								}

								var data1 = {
									organizationId : $scope.organizationId
								}

								// For Get Bed Category List Using Offset
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.bedCategoryList = [];

													if (response.data.status == "success") {
														$rootScope.stopLoader();
														$scope.bedCategoryList = response.data.listObject;

													}
												});

								// For Count Bed Category List
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

							$scope.getBedCategoryMasterList = function(orgId,
									offset, noOfRecordsPerPage) {
								$rootScope.startLoader();
								console.log("offset", offset);
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
										: 10;
								var URI = BASE_URL + ROOT_URL
										+ GETBEDCATEGORYLIST;
								var data = {
									organizationId : $scope.organizationId,
									offset : offset,
									noOfRecordsPerPage : noOfRecordsPerPage
								}

								console.log("URI", URI);
								// For Get Bed Category List Using Offset
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.bedCategoryList = [];

													if (response.data.status == "success") {
														$rootScope.stopLoader();
														$scope.bedCategoryList = response.data.listObject;

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
									$scope.getBedCategoryMasterList(
											$scope.organizationId,
											$scope.pager.startIndex,
											$scope.pager.pageSize);
								}
							}

							$scope.initBedCategoryMasterList(
									$scope.organizationId, $scope.offset,
									$scope.noOfRecordsPerPage);

							// For Paginations End

							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.saveBedCategory();
							}

							// Save function for add speciality
							$scope.saveBedCategory = function() {
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');
								$scope.$broadcast('show-errors-check-validity');
								if ($scope.bedcategoryform.$valid) {
									if ($scope.popUpFlag) {
										$scope.showModal = !$scope.showModal;

									} else {
										$rootScope.startLoader();
										// var data = $scope.speciality;
										var data = {
											bedCategoryCode : $scope.bedCategory.bedCategoryCode,
											bedCategoryDesc : $scope.bedCategory.bedCategoryName,
											isBedRetention : $scope.bedCategory.isBedRetention,
											hierarchyId : $scope.bedCategory.hierarchyId,
											occupancyUnitId : $scope.bedCategory.occupancyUnitId,
											status : 'A',
											createdBy : $scope.userId,
											updatedBy : $scope.userId,
											organizationId : $scope.organizationId,
											updatedDate : $scope.currentDate,
											createdDate : $scope.currentDate
										}

										console.log(data);
										var URI = BASE_URL + ROOT_URL
												+ ADDBEDCATEGORY;
										GenericService
												.serviceAction("POST", URI,
														data)
												.then(
														function(response) {

															if (response.data.status == "success") {
																$rootScope.stopLoader();
																growl
																		.success(
																				'Bed Category added sucessfully!!!.',
																				{
																					title : 'Success!'
																				});

																$scope
																		.$broadcast('show-errors-reset');
																$scope.init();
															} else {
																$rootScope.stopLoader();
																growl
																		.error(
																				'Bed Category already exits!!!!.',
																				{
																					title : 'Error!'
																				});
															}
															$scope
																	.initBedCategoryMasterList(
																			$scope.organizationId,
																			$scope.offset,
																			$scope.noOfRecordsPerPage);
														});
									}
								}

							};

							// function for get speciality object for update
							$scope.showUpdateBtn = function(id) {
								$rootScope.startLoader();

								$scope.saveBtnFlag = true;
								$scope.updateBtnFlag = false;

								var data = {
									bedCategoryId : id
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ GETSINGLEBEDCATEGORY;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {

													if (response.data.status == "success") {
														$rootScope.stopLoader();

														$scope.bedCategory.bedCategoryId = response.data.listObject[0].bedCategoryId;
														$scope.bedCategory.bedCategoryCode = response.data.listObject[0].bedCategoryCode;
														$scope.bedCategory.bedCategoryName = response.data.listObject[0].bedCategoryDesc;
														$scope.bedCategory.hierarchyId = response.data.listObject[0].hierarchyId
																.toString();
														$scope.bedCategory.occupancyUnitId = response.data.listObject[0].occupancyUnitId
																.toString();
														$scope.bedCategory.isBedRetention = response.data.listObject[0].isBedRetention;
													} else {
														alert("Error!!");
														$rootScope.stopLoader();
													}
												});

							};


							// function for update status speciality
							$scope.updateStatus = function(id, type) {
								$rootScope.startLoader();
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								type = (type == "A") ? "A" : "I";
								var data = {
									bedCategoryId : id,
									status : type,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ UPDATEBEDCATEGORYSTATUS;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {

													if (response.data.status == "success") {
														$rootScope.stopLoader();
														growl
																.success(
																		'Bed Category status changed sucessfully!!!.',
																		{
																			title : 'Success!'
																		});
													} else {
														$rootScope.stopLoader();
														growl
																.error(
																		'something wrong!!!!.',
																		{
																			title : 'Error!'
																		});
													}
												});
							}

							// function for update speciality
							$scope.updateBedCategory = function() {

								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								$scope.$broadcast('show-errors-check-validity');
								if ($scope.bedcategoryform.$valid) {
									$rootScope.startLoader();
									var data = {
										bedCategoryCode : $scope.bedCategory.bedCategoryCode,
										bedCategoryDesc : $scope.bedCategory.bedCategoryName,
										isBedRetention : $scope.bedCategory.isBedRetention,
										hierarchyId : $scope.bedCategory.hierarchyId,
										occupancyUnitId : $scope.bedCategory.occupancyUnitId,
										bedCategoryId : $scope.bedCategory.bedCategoryId,
										updatedBy : $scope.userId,
										updatedDate : $scope.currentDate
									}
									console.log(data);
									var URI = BASE_URL + ROOT_URL
											+ UPDATEBEDCATEGORY;
									GenericService
											.serviceAction("POST", URI, data)
											.then(
													function(response) {

														if (response.data.status == "success") {
															$rootScope.stopLoader();
															growl
																	.success(
																			'Bed category updated sucessfully!!!.',
																			{
																				title : 'Success!'
																			});
															$scope
																	.$broadcast('show-errors-reset');
															$scope.init();
															// alert("ds");

														} else {
															$rootScope.stopLoader();
															growl
																	.error(
																			'Bed category already exits!!!!.',
																			{
																				title : 'Error!'
																			});
														}
														$scope
																.initBedCategoryMasterList(
																		$scope.organizationId,
																		$scope.offset,
																		$scope.noOfRecordsPerPage);
													});
								}
							}

						} ]);
