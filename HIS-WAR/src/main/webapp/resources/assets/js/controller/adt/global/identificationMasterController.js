'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:identificationMasterController
 * @description # identificationMasterController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'identificationMasterController',
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

								$scope.identification = {
									isExpire : 'N'
								}

								$scope.saveBtnFlag = false;
								$scope.updateBtnFlag = true;

								var data = {};
								// $scope.status = "NO";
								/*
								 * var URI = BASE_URL + ROOT_URL +
								 * GETIDENTIFICATIONLIST; GenericService
								 * .serviceAction("GET", URI, data) .then(
								 * function(response) {
								 * $scope.identificationList = [];
								 * 
								 * if (response.data.status == "success") {
								 * $scope.identificationList =
								 * response.data.listObject;
								 *  } });
								 */

							};
							$scope.init();

							// For Paginations Start
							$scope.setNoOfRecords = function() {
								$scope.initIdentificationMasterList(
										$scope.organizationId, $scope.offset,
										$scope.noOfRecordsPerPage);
							};
							$scope.initIdentificationMasterList = function(
									orgId, offset, noOfRecordsPerPage) {
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
										: 10;
								var URI = BASE_URL + ROOT_URL
										+ GETIDENTIFICATIONLIST;
								var URI1 = BASE_URL + ROOT_URL
										+ COUNTIDENTIFICATIONMASTER;

								var data = {
									organizationId : orgId,
									offset : offset,
									noOfRecordsPerPage : noOfRecordsPerPage
								}

								var data1 = {
									organizationId : $scope.organizationId
								}

								// For Get Identification List Using Offset
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.identificationList = [];

													if (response.data.status == "success") {
														$scope.identificationList = response.data.listObject;

													}
												});

								// For Count Identification List
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

							$scope.getIdentificationMasterList = function(
									orgId, offset, noOfRecordsPerPage) {
								console.log("offset", offset);
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
										: 10;
								var URI = BASE_URL + ROOT_URL
										+ GETIDENTIFICATIONLIST;
								var data = {
									organizationId : $scope.organizationId,
									offset : offset,
									noOfRecordsPerPage : noOfRecordsPerPage
								}

								console.log("URI", URI);
								// For Get Identification List Using Offset
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.identificationList = [];

													if (response.data.status == "success") {
														$scope.identificationList = response.data.listObject;

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
									$scope.getIdentificationMasterList(
											$scope.organizationId,
											$scope.pager.startIndex,
											$scope.pager.pageSize);
								}
							}

							$scope.initIdentificationMasterList(
									$scope.organizationId, $scope.offset,
									$scope.noOfRecordsPerPage);

							// For Paginations End

							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.saveIdentification();
							}

							// Function for save identification
							$scope.saveIdentification = function() {
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								$scope.$broadcast('show-errors-check-validity');
								if ($scope.identificationform.$valid) {
									if ($scope.popUpFlag) {
										$scope.showModal = !$scope.showModal;

									} else {

										/*
										 * if ($scope.identification.isExpire ==
										 * true) {
										 * $scope.identification.isExpire = "Y"; }
										 * else { $scope.identification.isExpire =
										 * "N"; }
										 */

										// var data = $scope.speciality;
										var data = {
											identificationCode : $scope.identification.identificationCode,
											identificationName : $scope.identification.identificationName,
											isExpiryRequired : $scope.identification.isExpire,
											status : 'A',
											createdBy : $scope.userId,
											updatedBy : $scope.userId,
											organizationId : $scope.organizationId,
											updatedDate : $scope.currentDate,
											createdDate : $scope.currentDate
										}

										console.log(data);
										var URI = BASE_URL + ROOT_URL
												+ ADDIDENTIFICATION;
										GenericService
												.serviceAction("POST", URI,
														data)
												.then(
														function(response) {

															if (response.data.status == "success") {
																growl
																		.success(
																				'Identification added sucessfully!!!.',
																				{
																					title : 'Success!'
																				});

																$scope
																		.$broadcast('show-errors-reset');
																$scope.init();
															} else {
																growl
																		.error(
																				'Identification already exits!!!!.',
																				{
																					title : 'Error!'
																				});
															}
															$scope
																	.initIdentificationMasterList(
																			$scope.organizationId,
																			$scope.offset,
																			$scope.noOfRecordsPerPage);
														});
									}
								}

							};

							// function for get identification object for update
							$scope.showUpdateBtn = function(id) {

								$scope.saveBtnFlag = true;
								$scope.updateBtnFlag = false;

								var data = {
									identificationId : id
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ GETSINGLEIDENTIFICATION;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													// $scope.speciality = [];
													if (response.data.status == "success") {
														// $scope.speciality =
														// response.data.listObject;
														// alert(response.data.listObject[0].specialityId);
														$scope.identification.identificationId = response.data.listObject[0].identificationId;
														$scope.identification.identificationCode = response.data.listObject[0].identificationCode;
														$scope.identification.identificationName = response.data.listObject[0].identificationName;
														$scope.identification.isExpire = response.data.listObject[0].isExpiryRequired;
													} else {
														alert("Error!!");
													}
												});

							};

							// function for update status identification
							$scope.updateStatus = function(id, type) {

								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								type = (type == "A") ? "A" : "I";
								var data = {
									identificationId : id,
									status : type,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ UPDATEIDENTIFICATIONSTATUS;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {

													if (response.data.status == "success") {
														growl
																.success(
																		'Identification status changed sucessfully!!!.',
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

							// function for update identification
							$scope.updateIdentification = function() {

								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								$scope.$broadcast('show-errors-check-validity');
								if ($scope.identificationform.$valid) {
									var data = {
										identificationId : $scope.identification.identificationId,
										identificationCode : $scope.identification.identificationCode,
										identificationName : $scope.identification.identificationName,
										isExpiryRequired : $scope.identification.isExpire,
										status : 'A',
										updatedBy : $scope.userId,
										updatedDate : $scope.currentDate
									}
									console.log(data);
									var URI = BASE_URL + ROOT_URL
											+ UPDATEIDENTIFICATION;
									GenericService
											.serviceAction("POST", URI, data)
											.then(
													function(response) {

														if (response.data.status == "success") {
															growl
																	.success(
																			'Identification updated sucessfully!!!.',
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
																			'Identification already exits!!!!.',
																			{
																				title : 'Error!'
																			});
														}
														$scope
																.initIdentificationMasterList(
																		$scope.organizationId,
																		$scope.offset,
																		$scope.noOfRecordsPerPage);
													});
								}
							}

						} ]);
