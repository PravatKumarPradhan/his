'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:subSpecialityMasterController
 * @description #subSpecialityMasterController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'subSpecialityMasterController',
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

								/** Variables for pagination */
								$scope.offset = 0;
								$scope.noOfRecordsPerPage = 10;
								$scope.totalNoOfRecords;
								$scope.searchKeyword;
								$scope.commonListCount;

								$rootScope.loginpage = true;

								$scope.subSpeciality = {
								// isSurgicalCode : false
								}
								$scope.saveBtnFlag = false;
								$scope.updateBtnFlag = true;

								// for popup
								$scope.showModal = false;
								$scope.buttonClicked = "";
								$scope.popUpFlag = true;
								// end for popup

								var data = {};
								// $scope.status = "NO";
								var URI = BASE_URL + ROOT_URL
										+ GETSPECIALITYACTIVE;
								GenericService
										.serviceAction("GET", URI, data)
										.then(
												function(response) {
													$scope.specialityList = [];

													if (response.data.status == "success") {
														$scope.specialityList = response.data.listObject;
														console
																.log($scope.specialityList);

													}
												});
								
								//to get general ledger list
								var URI = BASE_URL + ROOT_URL
								+ GETGENERALLEDGERMASTERACTIVELIST+"/"+$scope.organizationId;
								var data = {
										
									}
								GenericService
								.serviceAction("GET", URI, data)
								.then(
										function(response) {
											$scope.ledgerList = [];

											if (response.data.status == "success") {
												$rootScope.stopLoader();
												$scope.ledgerList = response.data.listObject;
												console.log("ledgerList",$scope.ledgerList);
											}
										});

								// For Count Speciality List

								/*
								 * var URI = BASE_URL + ROOT_URL +
								 * GETSUBSPECIALITYLIST; GenericService
								 * .serviceAction("GET", URI, data) .then(
								 * function(response) { $scope.subSpecialityList =
								 * [];
								 * 
								 * if (response.data.status == "success") {
								 * $scope.subSpecialityList =
								 * response.data.listObject;
								 *  } });
								 */

							};
							$scope.init();

							// For Paginations Start
							$scope.setNoOfRecords = function() {
								$scope.initSubSpecialityMasterList(
										$scope.organizationId, $scope.offset,
										$scope.noOfRecordsPerPage);
							};
							$scope.initSubSpecialityMasterList = function(
									orgId, offset, noOfRecordsPerPage) {
								$rootScope.startLoader();
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
										: 10;
								var URI = BASE_URL + ROOT_URL
										+ GETSUBSPECIALITYLIST;
								var URI1 = BASE_URL + ROOT_URL
										+ COUNTSUBSPECIALITYMASTER;

								var data = {
									organizationId : orgId,
									offset : offset,
									noOfRecordsPerPage : noOfRecordsPerPage
								}

								var data1 = {
									organizationId : $scope.organizationId
								}

								// For Get subSpeciality List Using Offset
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.subSpecialityList = [];

													if (response.data.status == "success") {
														$rootScope.stopLoader();
														$scope.subSpecialityList = response.data.listObject;

													}
												});

								// For Count subSpeciality List
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

							$scope.getSubSpecialityMasterList = function(orgId,
									offset, noOfRecordsPerPage) {
								$rootScope.startLoader();
								console.log("offset", offset);
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
										: 10;
								var URI = BASE_URL + ROOT_URL
										+ GETSUBSPECIALITYLIST;
								var data = {
									organizationId : $scope.organizationId,
									offset : offset,
									noOfRecordsPerPage : noOfRecordsPerPage
								}

								console.log("URI", URI);
								// For Get subSpeciality List Using Offset
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.subSpecialityList = [];

													if (response.data.status == "success") {
														$rootScope.stopLoader();
														$scope.subSpecialityList = response.data.listObject;

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
									$scope.getSubSpecialityMasterList(
											$scope.organizationId,
											$scope.pager.startIndex,
											$scope.pager.pageSize);
								}
							}

							$scope.initSubSpecialityMasterList(
									$scope.organizationId, $scope.offset,
									$scope.noOfRecordsPerPage);

							// For Paginations End

							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.saveSubSpeciality();
							}

							// Function For save SubSpeciality
							$scope.saveSubSpeciality = function() {
								
								$rootScope.startLoader();
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');
								$scope.$broadcast('show-errors-check-validity');

								var data = {
									specialityId : $scope.subSpeciality.specialityId,
									subSpecialityMasterCode : $scope.subSpeciality.subSpecialityCode,
									subSpecialityMasterName : $scope.subSpeciality.subSpecialityName,
									generalLedgerId :$scope.subSpeciality.generalLedgerId,
									status : 'A',
									createdBy : $scope.userId,
									updatedBy : $scope.userId,
									organizationId : $scope.organizationId,
									createdDate : $scope.currentDate,
									updatedDate : $scope.currentDate
								}
//								console.log("saveSubSpeciality",data); return false;
								if ($scope.subSpecialityform.$valid) {
									console.log(data);

									if ($scope.popUpFlag) {
										$scope.showModal = !$scope.showModal;

									} else {

										var URI = BASE_URL + ROOT_URL
												+ ADDSUBSPECIALITY;
										GenericService
												.serviceAction("POST", URI,
														data)
												.then(
														function(response) {

															if (response.data.status == "success") {
																growl
																		.success(
																				'Sub department added sucessfully!!!.',
																				{
																					title : 'Success!'
																				});
																$scope
																		.$broadcast('show-errors-reset');
																$scope.init();
																$rootScope.stopLoader();
															} else {
																growl
																		.error(
																				'Sub department already exits!!!!.',
																				{
																					title : 'Error!'
																				});
															}
															$scope
																	.initSubSpecialityMasterList(
																			$scope.organizationId,
																			$scope.offset,
																			$scope.noOfRecordsPerPage);
														});
										$rootScope.stopLoader();
									}
								}

							};

							// Function For get single SubSpeciality
							$scope.showUpdateBtn = function(id) {
								$rootScope.startLoader();
								$scope.saveBtnFlag = true;
								$scope.updateBtnFlag = false;

								var data = {
									subSpecialityMasterId : id
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ GETSINGLESUBSPECIALITY;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													// $scope.speciality = [];
													if (response.data.status == "success") {
														$rootScope.stopLoader();
														console.log("getby Id",response.data.listObject);
														$scope.subSpeciality.subSpecialityId = response.data.listObject[0].subSpecialityMasterId;
														$scope.subSpeciality.specialityId = response.data.listObject[0].specialityId
																.toString();
														$scope.subSpeciality.subSpecialityName = response.data.listObject[0].subSpecialityMasterName;
														$scope.subSpeciality.subSpecialityCode = response.data.listObject[0].subSpecialityMasterCode;
														$scope.subSpeciality.generalLedgerId = response.data.listObject[0].generalLedgerId.toString();

													} else {
														$rootScope.stopLoader();
														alert("Error!!");
													}
												});

							};

							// Function For get update SubSpeciality staus
							$scope.updateStatus = function(id, type) {
								$rootScope.startLoader();
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								type = (type == "A") ? "A" : "I";

								var data = {
									subSpecialityMasterId : id,
									status : type,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ UPDATESUBSPECIALITYSTATUS;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {

													if (response.data.status == "success") {
														growl
																.success(
																		'Sub department status changed sucessfully!!!.',
																		{
																			title : 'Success!'
																		});
														$rootScope.stopLoader();
													} else {
														growl
																.error(
																		'something wrong!!!!.',
																		{
																			title : 'Error!'
																		});
														$rootScope.stopLoader();
													}
												});
							}

							// Function For get update SubSpeciality
							$scope.updateSubSpeciality = function() {
								$rootScope.startLoader();
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								$scope.$broadcast('show-errors-check-validity');
								console.log($scope.subSpeciality);

								var data = {
									subSpecialityMasterId : $scope.subSpeciality.subSpecialityId,
									specialityId : $scope.subSpeciality.specialityId,
									subSpecialityMasterCode : $scope.subSpeciality.subSpecialityCode,
									subSpecialityMasterName : $scope.subSpeciality.subSpecialityName,
									// generalLedgerId:$scope.speciality.generalLedgerId,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate
								}
								if ($scope.subSpecialityform.$valid) {
									console.log(data);
									var URI = BASE_URL + ROOT_URL
											+ UPDATESUBSPECIALITY;
									GenericService
											.serviceAction("POST", URI, data)
											.then(
													function(response) {

														if (response.data.status == "success") {
															growl
																	.success(
																			'Sub department updated sucessfully!!!.',
																			{
																				title : 'Success!'
																			});
															$scope
																	.$broadcast('show-errors-reset');
															$scope.init();
															$rootScope.stopLoader();
															// alert("ds");

														} else {
															growl
																	.error(
																			'Sub department already exits!!!!.',
																			{
																				title : 'Error!'
																			});
															$rootScope.stopLoader();
														}
														$scope
																.initSubSpecialityMasterList(
																		$scope.organizationId,
																		$scope.offset,
																		$scope.noOfRecordsPerPage);
													});
								}
							}

						} ]);
