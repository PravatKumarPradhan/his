'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:specialityMasterController
 * @description #specialityMasterController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'specialityMasterController',
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

								$scope.speciality = {
									isSurgicalCode : 'N',
									isClinicalSpecialty:'N'
								}
								$scope.speciality.specialityId = 0;
								$scope.saveBtnFlag = false;
								$scope.updateBtnFlag = true;

								// for popup
								$scope.showModal = false;
								$scope.buttonClicked = "";
								$scope.popUpFlag = true;
								// end for popup

								var data = {};
								/*
								 * var URI = BASE_URL + ROOT_URL +
								 * GETSPECIALITYLIST; GenericService
								 * .serviceAction("GET", URI, data) .then(
								 * function(response) { $scope.specialityList =
								 * [];
								 * 
								 * if (response.data.status == "success") {
								 * $scope.specialityList =
								 * response.data.listObject;
								 *  } });
								 */

							};
							$scope.init();

							// For Paginations Start
							$scope.setNoOfRecords = function() {
								$scope.initSpecialityMasterList(
										$scope.organizationId, $scope.offset,
										$scope.noOfRecordsPerPage);
							};
							$scope.initSpecialityMasterList = function(orgId,
									offset, noOfRecordsPerPage) {
								$rootScope.startLoader();
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
										: 10;
								var URI = BASE_URL + ROOT_URL
										+ GETSPECIALITYLIST;
								var URI1 = BASE_URL + ROOT_URL
										+ COUNTSPECIALITYMASTER;

								var data = {
									organizationId : orgId,
									offset : offset,
									noOfRecordsPerPage : noOfRecordsPerPage
								}

								var data1 = {
									organizationId : $scope.organizationId
								}

								// For Get Speciality List Using Offset
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.specialityList = [];

													if (response.data.status == "success") {
														$rootScope.stopLoader();
														$scope.specialityList = response.data.listObject;
														console.log("specialityList",$scope.specialityList);
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

							$scope.getSpecialityMasterList = function(orgId,
									offset, noOfRecordsPerPage) {
								$rootScope.startLoader();
								console.log("offset", offset);
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
										: 10;
								var URI = BASE_URL + ROOT_URL
										+ GETSPECIALITYLIST;
								var data = {
									organizationId : $scope.organizationId,
									offset : offset,
									noOfRecordsPerPage : noOfRecordsPerPage
								}

								console.log("URI", URI);
								// For Get Speciality List Using Offset
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.specialityList = [];

													if (response.data.status == "success") {
														$rootScope.stopLoader();
														$scope.specialityList = response.data.listObject;
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
									$scope.getSpecialityMasterList(
											$scope.organizationId,
											$scope.pager.startIndex,
											$scope.pager.pageSize);
								}
							}

							$scope.initSpecialityMasterList(
									$scope.organizationId, $scope.offset,
									$scope.noOfRecordsPerPage);

							// For Paginations End

							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.saveSpeciality();
							}

							// Save function for add speciality
							$scope.saveSpeciality = function() {
								$rootScope.startLoader();
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');
								$scope.$broadcast('show-errors-check-validity');
								var data = {
									specialityCode : $scope.speciality.specialityCode,
									specialityName : $scope.speciality.specialityName,
									generalLedgerId : $scope.speciality.generalLedgerId,
									isSurgicalCode : $scope.speciality.isSurgicalCode,
									isClinicalSpeciality : $scope.speciality.isClinicalSpecialty,
									status : 'A',
									createdBy : $scope.userId,
									updatedBy : $scope.userId,
									organizationId : $scope.organizationId,
									createdDate : $scope.currentDate,
									updatedDate : $scope.currentDate
								}
//								console.log("specilityDetails",data); return false;
								if ($scope.Specialityform.$valid) {
									console.log(data);
									if ($scope.popUpFlag) {
										$scope.showModal = !$scope.showModal;

									} else {

										var URI = BASE_URL + ROOT_URL
												+ ADDSPECIALITY;
										GenericService
												.serviceAction("POST", URI,
														data)
												.then(
														function(response) {

															if (response.data.status == "success") {
																growl
																		.success(
																				'Department added sucessfully!!!.',
																				{
																					title : 'Success!'
																				});
																// $scope.speciality
																// = {};
																$scope
																		.$broadcast('show-errors-reset');
																$scope.init();
																$rootScope.stopLoader();
															} else {
																growl
																		.error(
																				'Department already exits!!!!.',
																				{
																					title : 'Error!'
																				});
															}
															$scope
																	.initSpecialityMasterList(
																			$scope.organizationId,
																			$scope.offset,
																			$scope.noOfRecordsPerPage);
														});
										$rootScope.stopLoader();
									}
								}

							};

							// function for get speciality object for update
							$scope.showUpdateBtn = function(id) {
								$rootScope.startLoader();
								$scope.saveBtnFlag = true;
								$scope.updateBtnFlag = false;

								var data = {
									specialityId : id
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ GETSINGLESPECIALITY;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													if (response.data.status == "success") {
														$rootScope.stopLoader();
														console.log("list obj",response.data.listObject[0]);
														$scope.speciality.specialityId = response.data.listObject[0].specialityId;
														$scope.speciality.specialityName = response.data.listObject[0].specialityName;
														$scope.speciality.specialityCode = response.data.listObject[0].specialityCode;
														$scope.speciality.generalLedgerId = response.data.listObject[0].generalLedgerId.toString();
														$scope.speciality.isSurgicalCode = response.data.listObject[0].isSurgicalCode;
														$scope.speciality.isClinicalSpecialty = response.data.listObject[0].isClinicalSpeciality;
													} else {
														$rootScope.stopLoader();
														alert("Error!!");
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
									specialityId : id,
									status : type,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ UPDATESPECIALITYSTATUS;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {

													if (response.data.status == "success") {
														growl
																.success(
																		'Department status changed sucessfully!!!.',
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

							// function for update speciality
							$scope.updateSpeciality = function() {
								$rootScope.startLoader();
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');
								$scope.$broadcast('show-errors-check-validity');
								console.log($scope.speciality);
								var data = {
									specialityId : $scope.speciality.specialityId,
									specialityCode : $scope.speciality.specialityCode,
									specialityName : $scope.speciality.specialityName,
									generalLedgerId : $scope.speciality.generalLedgerId,
									isSurgicalCode : $scope.speciality.isSurgicalCode,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate
								}
								if ($scope.Specialityform.$valid) {
									console.log(data);
									var URI = BASE_URL + ROOT_URL
											+ UPDATESPECIALITY;
									GenericService
											.serviceAction("POST", URI, data)
											.then(
													function(response) {

														if (response.data.status == "success") {
															growl
																	.success(
																			'Department updated sucessfully!!!.',
																			{
																				title : 'Success!'
																			});
															$scope.saveBtnFlag = false;
															$scope.updateBtnFlag = true;
															$scope.init();
															$rootScope.stopLoader();

														} else {
															growl
																	.error(
																			'Department already exits!!!!.',
																			{
																				title : 'Error!'
																			});
														}
														$scope
																.initSpecialityMasterList(
																		$scope.organizationId,
																		$scope.offset,
																		$scope.noOfRecordsPerPage);
													});
									$rootScope.stopLoader();
								}
							}

						} ]);
