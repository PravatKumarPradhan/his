'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:shortLeaveReasonMasterController
 * @description # shortLeaveReasonMasterController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'shortLeaveReasonMasterController',
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
								$scope.shortleavereason = {}
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
								 * GETSHORTLEAVEREASONLIST; GenericService
								 * .serviceAction("GET", URI, data) .then(
								 * function(response) {
								 * $scope.shortLeaveReasonList = [];
								 * 
								 * if (response.data.status == "success") {
								 * $scope.shortLeaveReasonList =
								 * response.data.listObject; console
								 * .log($scope.shortLeaveReasonList);
								 *  } });
								 */

							}

							$scope.init();

							// For Paginations Start
							$scope.setNoOfRecords = function() {
								$scope.initShortLeaveReasonMasterList(
										$scope.organizationId, $scope.offset,
										$scope.noOfRecordsPerPage);
							};
							$scope.initShortLeaveReasonMasterList = function(
									orgId, offset, noOfRecordsPerPage) {
								$rootScope.startLoader();
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
										: 10;
								var URI = BASE_URL + ROOT_URL
										+ GETSHORTLEAVEREASONLIST;
								var URI1 = BASE_URL + ROOT_URL
										+ COUNTSHORTLEAVEREASONMASTER;

								var data = {
									organizationId : orgId,
									offset : offset,
									noOfRecordsPerPage : noOfRecordsPerPage
								}

								var data1 = {
									organizationId : $scope.organizationId
								}

								// For Get Short Leave Reason List Using Offset
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.shortLeaveReasonList = [];

													if (response.data.status == "success") {
														$rootScope.stopLoader();
														$scope.shortLeaveReasonList = response.data.listObject;

													}
												});

								// For Count Short Leave Reason List
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

							$scope.getShortLeaveReasonMasterList = function(
									orgId, offset, noOfRecordsPerPage) {
								console.log("offset", offset);
								$rootScope.startLoader();
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
										: 10;
								var URI = BASE_URL + ROOT_URL
										+ GETSHORTLEAVEREASONLIST;
								var data = {
									organizationId : $scope.organizationId,
									offset : offset,
									noOfRecordsPerPage : noOfRecordsPerPage
								}

								console.log("URI", URI);
								// For Get Short Leave Reason List Using Offset
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.shortLeaveReasonList = [];

													if (response.data.status == "success") {
														$rootScope.stopLoader();
														$scope.shortLeaveReasonList = response.data.listObject;

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
									$scope.getShortLeaveReasonMasterList(
											$scope.organizationId,
											$scope.pager.startIndex,
											$scope.pager.pageSize);
								}
							}

							$scope.initShortLeaveReasonMasterList(
									$scope.organizationId, $scope.offset,
									$scope.noOfRecordsPerPage);

							// For Paginations End

							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.saveShortLeaveReason();
							}

							$scope.saveShortLeaveReason = function() {

								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								$scope.$broadcast('show-errors-check-validity');

								var data = {
									shortLeaveReasonCode : $scope.shortleavereason.shortLeaveReasonCode,
									shortLeaveReasonName : $scope.shortleavereason.shortLeaveReasonName,
									status : 'A',
									createdBy : $scope.userId,
									updatedBy : $scope.userId,
									organizationId : $scope.organizationId,
									createdDate : $scope.currentDate,
									updatedDate : $scope.currentDate
								}
								if ($scope.shortleavereasonform.$valid) {
									if ($scope.popUpFlag) {
										$scope.showModal = !$scope.showModal;

									} else {
										$rootScope.startLoader();
										console.log(data);

										var URI = BASE_URL + ROOT_URL
												+ ADDSHORTLEAVEREASON;
										GenericService
												.serviceAction("POST", URI,
														data)
												.then(
														function(response) {

															if (response.data.status == "success") {
																$rootScope.stopLoader();
																growl
																		.success(
																				'Short Leave Reason added sucessfully!!!.',
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
																				'Short Leave Reason already exits!!!!.',
																				{
																					title : 'Error!'
																				});
															}
															$scope
																	.initShortLeaveReasonMasterList(
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

									shortLeaveReasonId : id
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ GETSINGLESHORTLEAVEREASON;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													// $scope.speciality =[];
													if (response.data.status == "success") {
														$rootScope.stopLoader();
														$scope.shortleavereason.shortLeaveReasonId = response.data.listObject[0].shortLeaveReasonId;
														$scope.shortleavereason.shortLeaveReasonCode = response.data.listObject[0].shortLeaveReasonCode;
														$scope.shortleavereason.shortLeaveReasonName = response.data.listObject[0].shortLeaveReasonName;
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
									shortLeaveReasonId : id,
									status : type,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ UPDATESHORTLEAVEREASONSTATUS;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {

													if (response.data.status == "success") {
														$rootScope.stopLoader();
														growl
																.success(
																		'Short Leave Reason status changed sucessfully!!!.',
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

							$scope.updateShortLeaveReason = function() {
								$rootScope.startLoader();
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								$scope.$broadcast('show-errors-check-validity');
								var data = {
									shortLeaveReasonCode : $scope.shortleavereason.shortLeaveReasonCode,
									shortLeaveReasonName : $scope.shortleavereason.shortLeaveReasonName,
									shortLeaveReasonId : $scope.shortleavereason.shortLeaveReasonId,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate
								}
								if ($scope.shortleavereasonform.$valid) {
									console.log(data);
									var URI = BASE_URL + ROOT_URL
											+ UPDATESHORTLEAVEREASON;
									GenericService
											.serviceAction("POST", URI, data)
											.then(
													function(response) {

														if (response.data.status == "success") {
															$rootScope.stopLoader();
															growl
																	.success(
																			'Short Leave Reason updated sucessfully!!!.',
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
																			'Short Leave Reason already exits!!!!.',
																			{
																				title : 'Error!'
																			});
														}
														$scope
																.initShortLeaveReasonMasterList(
																		$scope.organizationId,
																		$scope.offset,
																		$scope.noOfRecordsPerPage);

													});
								}
							}

						} ]);
