'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:occupancyUnitMasterController
 * @description # occupancyUnitMasterController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'occupancyUnitMasterController',
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

								$rootScope.loginpage = true;
								$scope.occupancyunit = {}
								$scope.saveBtnFlag = false;
								$scope.updateBtnFlag = true;

								// for popup
								$scope.showModal = false;
								$scope.buttonClicked = "";
								$scope.popUpFlag = true;
								// end for popup

								var data = {};
								// $scope.status = "NO";
								/*
								 * var URI = BASE_URL + ROOT_URL +
								 * GETOCCUPANCYUNITLIST; GenericService
								 * .serviceAction("GET", URI, data) .then(
								 * function(response) { $scope.occupancyUnitList =
								 * [];
								 * 
								 * if (response.data.status == "success") {
								 * $scope.occupancyUnitList =
								 * response.data.listObject; console
								 * .log($scope.occupancyUnitList);
								 *  } });
								 */
							}

							$scope.init();

							// For Paginations Start
							$scope.setNoOfRecords = function() {
								$scope.initOccupancyUnitMasterList(
										$scope.organizationId, $scope.offset,
										$scope.noOfRecordsPerPage);
							};
							$scope.initOccupancyUnitMasterList = function(
									orgId, offset, noOfRecordsPerPage) {
								$rootScope.startLoader();
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
										: 10;
								var URI = BASE_URL + ROOT_URL
										+ GETOCCUPANCYUNITLIST;
								var URI1 = BASE_URL + ROOT_URL
										+ COUNTOCCUPANCYUNITMASTER;

								var data = {
									organizationId : orgId,
									offset : offset,
									noOfRecordsPerPage : noOfRecordsPerPage
								}

								var data1 = {
									organizationId : $scope.organizationId
								}

								// For Get Occupancy Unit List Using Offset
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.occupancyUnitList = [];

													if (response.data.status == "success") {
														$rootScope.stopLoader();
														$scope.occupancyUnitList = response.data.listObject;

													}
												});

								// For Count Occupancy Unit List
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

							$scope.getOccupancyUnitMasterList = function(orgId,
									offset, noOfRecordsPerPage) {
								$rootScope.startLoader();
								console.log("offset", offset);
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
										: 10;
								var URI = BASE_URL + ROOT_URL
										+ GETOCCUPANCYUNITLIST;
								var data = {
									organizationId : $scope.organizationId,
									offset : offset,
									noOfRecordsPerPage : noOfRecordsPerPage
								}

								console.log("URI", URI);
								// For Get Occupancy Unit List Using Offset
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.occupancyUnitList = [];

													if (response.data.status == "success") {
														$rootScope.stopLoader();
														$scope.occupancyUnitList = response.data.listObject;

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
									$scope.getOccupancyUnitMasterList(
											$scope.organizationId,
											$scope.pager.startIndex,
											$scope.pager.pageSize);
								}
							}

							$scope.initOccupancyUnitMasterList(
									$scope.organizationId, $scope.offset,
									$scope.noOfRecordsPerPage);

							// For Paginations End

							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.saveOccupancyUnit();
							}

							$scope.saveOccupancyUnit = function() {

								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								$scope.$broadcast('show-errors-check-validity');

								var data = {
									occupancyUnitCode : $scope.occupancyunit.occupancyCode,
									occupancyUnitDesc : $scope.occupancyunit.occupancyName,
									numberOfHours : $scope.occupancyunit.occupancyHours,
									status : 'A',
									createdBy : $scope.userId,
									updatedBy : $scope.userId,
									organizationId : $scope.organizationId,
									createdDate : $scope.currentDate,
									updatedDate : $scope.currentDate
								}
								if ($scope.occupancyunitform.$valid) {
									if ($scope.popUpFlag) {
										$scope.showModal = !$scope.showModal;

									} else {
										$rootScope.startLoader();
										console.log(data);

										var URI = BASE_URL + ROOT_URL
												+ ADDOCCUPANCYUNIT;
										GenericService
												.serviceAction("POST", URI,
														data)
												.then(
														function(response) {

															if (response.data.status == "success") {
																$rootScope.stopLoader();
																growl
																		.success(
																				'Occupancy unit added sucessfully!!!.',
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
																				'Occupancy unit already exits!!!!.',
																				{
																					title : 'Error!'
																				});
															}
															$scope
																	.initOccupancyUnitMasterList(
																			$scope.organizationId,
																			$scope.offset,
																			$scope.noOfRecordsPerPage);

														});
									}
								}

							}

							$scope.showUpdateBtn = function(id) {
								$rootScope.startLoader();
								$scope.saveBtnFlag = true;
								$scope.updateBtnFlag = false;

								var data = {

									occupancyUnitId : id
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ GETSINGLEOCCUPANCYUNIT;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													// $scope.speciality =[];
													if (response.data.status == "success") {
														$rootScope.stopLoader();
														$scope.occupancyunit.occupancyUnitId = response.data.listObject[0].occupancyUnitId;
														$scope.occupancyunit.occupancyCode = response.data.listObject[0].occupancyUnitCode;
														$scope.occupancyunit.occupancyName = response.data.listObject[0].occupancyUnitDesc;
														$scope.occupancyunit.occupancyHours = response.data.listObject[0].numberOfHours;
													} else {
														alert("Error!!");
													}
												});

							};

							$scope.updateStatus = function(id, type) {
								$rootScope.startLoader();
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								type = (type == "A") ? "A" : "I";

								var data = {
									occupancyUnitId : id,
									status : type,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ UPDATEOCCUPANCYUNITSTATUS;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {

													if (response.data.status == "success") {
														$rootScope.stopLoader();
														growl
																.success(
																		'Occupancy Unit status changed sucessfully!!!.',
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

							$scope.updateOccupancyUnit = function() {

								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								$scope.$broadcast('show-errors-check-validity');
								var data = {
									occupancyUnitCode : $scope.occupancyunit.occupancyCode,
									occupancyUnitDesc : $scope.occupancyunit.occupancyName,
									occupancyUnitId : $scope.occupancyunit.occupancyUnitId,
									numberOfHours : $scope.occupancyunit.occupancyHours,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate
								}
								if ($scope.occupancyunitform.$valid) {
									console.log(data);
									$rootScope.startLoader();
									var URI = BASE_URL + ROOT_URL
											+ UPDATEOCCUPANCYUNIT;
									GenericService
											.serviceAction("POST", URI, data)
											.then(
													function(response) {

														if (response.data.status == "success") {
															$rootScope.stopLoader();
															growl
																	.success(
																			'Occupancy unit updated sucessfully!!!.',
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
																			'Occupancy unit already exits!!!!.',
																			{
																				title : 'Error!'
																			});
														}
														$scope
																.initOccupancyUnitMasterList(
																		$scope.organizationId,
																		$scope.offset,
																		$scope.noOfRecordsPerPage);

													});
								}
							}

						} ]);
