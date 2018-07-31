'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:bedTypeMasterController
 * @description # bedTypeMasterController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'bedTypeMasterController',
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

								$rootScope.loginpage = true;
								$scope.bedtype = {}
								$scope.saveBtnFlag = false;
								$scope.updateBtnFlag = true;

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
								// end for popup

								var data = {};
								// $scope.status = "NO";
								/*
								 * var URI = BASE_URL + ROOT_URL +
								 * GETBEDTYPELIST; GenericService
								 * .serviceAction("GET", URI, data) .then(
								 * function(response) { $scope.bedTypeList = [];
								 * 
								 * if (response.data.status == "success") {
								 * $scope.bedTypeList =
								 * response.data.listObject; console
								 * .log($scope.bedTypeList); } });
								 */

							}

							$scope.init();

							// For Paginations Start
							$scope.setNoOfRecords = function() {
								$scope.initBedTypeMasterList(
										$scope.organizationId, $scope.offset,
										$scope.noOfRecordsPerPage);
							};
							$scope.initBedTypeMasterList = function(orgId,
									offset, noOfRecordsPerPage) {
								$rootScope.startLoader();
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
										: 10;
								var URI = BASE_URL + ROOT_URL + GETBEDTYPELIST;
								var URI1 = BASE_URL + ROOT_URL
										+ COUNTBEDTYPEMASTER;

								var data = {
									organizationId : orgId,
									offset : offset,
									noOfRecordsPerPage : noOfRecordsPerPage
								}

								var data1 = {
									organizationId : $scope.organizationId
								}

								// For Get Bed Type List Using Offset
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.bedTypeList = [];

													if (response.data.status == "success") {
														$rootScope.stopLoader();
														$scope.bedTypeList = response.data.listObject;

													}
												});

								// For Count Bed Type List
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

							$scope.getBedTypeMasterList = function(orgId,
									offset, noOfRecordsPerPage) {
								console.log("offset", offset);
								$rootScope.startLoader();
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
										: 10;
								var URI = BASE_URL + ROOT_URL + GETBEDTYPELIST;
								var data = {
									organizationId : $scope.organizationId,
									offset : offset,
									noOfRecordsPerPage : noOfRecordsPerPage
								}

								console.log("URI", URI);
								// For Get Bed Type List Using Offset
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.bedTypeList = [];

													if (response.data.status == "success") {
														$rootScope.stopLoader();
														$scope.bedTypeList = response.data.listObject;

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
									$scope.getBedTypeMasterList(
											$scope.organizationId,
											$scope.pager.startIndex,
											$scope.pager.pageSize);
								}
							}

							$scope.initBedTypeMasterList($scope.organizationId,
									$scope.offset, $scope.noOfRecordsPerPage);

							// For Paginations End

							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.saveBedType();
							}

							$scope.saveBedType = function() {

								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								$scope.$broadcast('show-errors-check-validity');

								var data = {
									bedTypeCode : $scope.bedtype.bedTypeCode,
									bedTypeDesc : $scope.bedtype.bedTypeName,
									status : 'A',
									createdBy : $scope.userId,
									updatedBy : $scope.userId,
									organizationId : $scope.organizationId,
									createdDate : $scope.currentDate,
									updatedDate : $scope.currentDate
								}
								if ($scope.bedtypeform.$valid) {
									if ($scope.popUpFlag) {
										$scope.showModal = !$scope.showModal;

									} else {
										$rootScope.startLoader();
										console.log(data);

										var URI = BASE_URL + ROOT_URL
												+ ADDBEDTYPE;
										GenericService
												.serviceAction("POST", URI,
														data)
												.then(
														function(response) {

															if (response.data.status == "success") {
																$rootScope.stopLoader();
																growl
																		.success(
																				'Bed Type added sucessfully!!!.',
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
																				'Bed Type already exits!!!!.',
																				{
																					title : 'Error!'
																				});
															}
															$scope
																	.initBedTypeMasterList(
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

									bedTypeId : id
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ GETSINGLEBEDTYPE;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													if (response.data.status == "success") {
														$rootScope.stopLoader();
														$scope.bedtype.bedTypeId = response.data.listObject[0].bedTypeId;
														$scope.bedtype.bedTypeCode = response.data.listObject[0].bedTypeCode;
														$scope.bedtype.bedTypeName = response.data.listObject[0].bedTypeDesc;

													} else {
														$rootScope.stopLoader();
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
									bedTypeId : id,
									status : type,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ UPDATEBEDTYPESTATUS;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {

													if (response.data.status == "success") {
														$rootScope.stopLoader();
														growl
																.success(
																		'Bed Type status changed sucessfully!!!.',
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

							$scope.updateBedType = function() {
								
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								$scope.$broadcast('show-errors-check-validity');
								var data = {
									bedTypeCode : $scope.bedtype.bedTypeCode,
									bedTypeDesc : $scope.bedtype.bedTypeName,
									bedTypeId : $scope.bedtype.bedTypeId,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate
								}
								if ($scope.bedtypeform.$valid) {
									console.log(data);
									$rootScope.startLoader();
									var URI = BASE_URL + ROOT_URL
											+ UPDATEBEDTYPE;
									GenericService
											.serviceAction("POST", URI, data)
											.then(
													function(response) {

														if (response.data.status == "success") {
															$rootScope.stopLoader();
															growl
																	.success(
																			'Bed Type updated sucessfully!!!.',
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
																			'Bed Type already exits!!!!.',
																			{
																				title : 'Error!'
																			});
														}
														$scope
																.initBedTypeMasterList(
																		$scope.organizationId,
																		$scope.offset,
																		$scope.noOfRecordsPerPage);

													});
								}
							}

						} ]);
