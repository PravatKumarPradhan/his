'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:mortuaryBedMasterController
 * @description #mortuaryBedMasterController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'mortuaryBedMasterController',
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

							/* init() function for form object create. */
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

								$rootScope.loginpage = true;
								$scope.mortuaryObj = {}
								$scope.saveBtnFlag = false;
								$scope.updateBtnFlag = true;

								// for popup
								$scope.showModal = false;
								$scope.buttonClicked = "";
								$scope.popUpFlag = true;
								// end for popup

								var data = {};
								// $scope.status = "NO";
								/*var URI = BASE_URL + ROOT_URL
										+ GETADMISSIONTYPELIST;
								GenericService
										.serviceAction("GET", URI, data)
										.then(
												function(response) {
													$scope.admissionTypeList = [];

													if (response.data.status == "success") {
														$scope.admissionTypeList = response.data.listObject;
														console
																.log($scope.admissionTypeList);

													}
												});*/
							}

							$scope.init();
							
							//For Paginations Start
							$scope.setNoOfRecords = function() {
								$scope.initMortuaryBedMasterList($scope.organizationId, $scope.offset, $scope.noOfRecordsPerPage);
							};
							$scope.initMortuaryBedMasterList = function(orgId, offset, noOfRecordsPerPage)
							{
								$rootScope.startLoader();
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage : 10;
								var URI = BASE_URL + ROOT_URL + GETMORTUARYBEDLIST;
								var URI1 = BASE_URL + ROOT_URL + GETMORTUARYBEDCOUNT;
								
								var data = {
										organizationId:orgId,
										offset : offset,
										noOfRecordsPerPage : noOfRecordsPerPage,
										unitId : $scope.unitId
								}
								
								var data1 = {
										organizationId:$scope.organizationId,
										unitId : $scope.unitId
								}
								
								//For Get Admission Type List Using Offset
								GenericService
								.serviceAction("POST", URI, data)
								.then(
										function(response) {
											$scope.mortuaryBedList = [];

											if (response.data.status == "success") {
												$rootScope.stopLoader();
												$scope.mortuaryBedList = response.data.listObject;

											}
										});
								
								//For Count Admission Type List
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

							$scope.getMortuaryBedMasterList = function(orgId, offset, noOfRecordsPerPage) {
								$rootScope.startLoader();
								console.log("offset", offset);
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage : 10;
								var URI = BASE_URL + ROOT_URL + GETMORTUARYBEDLIST;
								var data = {
										organizationId:$scope.organizationId,
										offset : offset,
										noOfRecordsPerPage : noOfRecordsPerPage,
										unitId : $scope.unitId
								}
								
								console.log("URI", URI);
								//For Get Admission Type List Using Offset
								GenericService
								.serviceAction("POST", URI, data)
								.then(
										function(response) {
											$scope.mortuaryBedList = [];

											if (response.data.status == "success") {
												$rootScope.stopLoader();
												$scope.mortuaryBedList = response.data.listObject;

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
									$scope.getMortuaryBedMasterList($scope.organizationId, $scope.pager.startIndex, $scope.pager.pageSize);
								}
							}

							$scope.initMortuaryBedMasterList($scope.organizationId, $scope.offset, $scope.noOfRecordsPerPage);
							
							//For Paginations End

							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.saveMortuaryBed();
							}

							$scope.saveMortuaryBed = function() {

								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								$scope.$broadcast('show-errors-check-validity');

								var data = {
									mortuaryBedCode : $scope.mortuaryObj.mortuaryCode,
									mortuaryBedDesc : $scope.mortuaryObj.mortuaryName,
									status : 'A',
									createdBy : $scope.userId,
									updatedBy : $scope.userId,
									organizationId : $scope.organizationId,
									createdDate : $scope.currentDate,
									updatedDate : $scope.currentDate,
									unitId : $scope.unitId
								}
								
								if ($scope.mortuaryBedform.$valid) {
									if ($scope.popUpFlag) {
										$scope.showModal = !$scope.showModal;

									} else {
										$rootScope.startLoader();
										console.log(data);
										//return false;

										var URI = BASE_URL + ROOT_URL
												+ SAVEMORTUARYBEDS;
										GenericService
												.serviceAction("POST", URI,
														data)
												.then(
														function(response) {

															if (response.data.status == "success") {
																$rootScope.stopLoader();
																growl
																		.success(
																				'Mortuary Bed added sucessfully!!!.',
																				{
																					title : 'Success!'
																				});
																$scope.$broadcast('show-errors-reset');
																$scope.init();
															} else {
																$rootScope.stopLoader();
																growl
																		.error(
																				'Mortuary Bed already exits!!!!.',
																				{
																					title : 'Error!'
																				});
															}
															$scope.initMortuaryBedMasterList($scope.organizationId, $scope.offset, $scope.noOfRecordsPerPage);

														});
									}
								}

							}

							$scope.showUpdateBtn = function(id) {
								$rootScope.startLoader();
								$scope.saveBtnFlag = true;
								$scope.updateBtnFlag = false;

								var data = {

									mortuaryBedId : id
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ GETMOURTUARYBEDBYID;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													// $scope.speciality =[];
													if (response.data.status == "success") {
														$rootScope.stopLoader();
														$scope.mortuaryObj.mortuaryBedId = response.data.listObject[0].mortuaryBedId;
														$scope.mortuaryObj.mortuaryCode = response.data.listObject[0].mortuaryBedCode;
														$scope.mortuaryObj.mortuaryName = response.data.listObject[0].mortuaryBedDesc;

													} else {
														$rootScope.stopLoader();
														alert("Error!!");
													}
												});

							};

							/*
							 * $scope.checkVal = function(statusAt) {
							 * $scope.status = (statusAt == "A") ? "A" : "I";
							 * return (statusAt == "A") ? true : false; }
							 */

							$scope.updateStatus = function(id, type) {
								$rootScope.startLoader();
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								type = (type == "A") ? "A" : "I";

								var data = {
									mortuaryBedId : id,
									status : type,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ UPDATESTATUSFORMORTUARYBED;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {

													if (response.data.status == "success") {
														$rootScope.stopLoader();
														growl
																.success(
																		'Mortuary Bed status changed sucessfully!!!.',
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

							$scope.updateMortuaryBed = function() {

								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								$scope.$broadcast('show-errors-check-validity');
								var data = {
									mortuaryBedCode : $scope.mortuaryObj.mortuaryCode,
									mortuaryBedDesc : $scope.mortuaryObj.mortuaryName,
									mortuaryBedId : $scope.mortuaryObj.mortuaryBedId,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate
								}
								if ($scope.mortuaryBedform.$valid) {
									console.log(data);
									$rootScope.startLoader();
									var URI = BASE_URL + ROOT_URL
											+ UPDATEMORTUARYBEDMASTER;
									GenericService
											.serviceAction("POST", URI, data)
											.then(
													function(response) {

														if (response.data.status == "success") {
															$rootScope.stopLoader();
															growl
																	.success(
																			'Mortuary Bed updated sucessfully!!!.',
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
																			'Mortuary Bed already exits!!!!.',
																			{
																				title : 'Error!'
																			});
														}
														$scope.initMortuaryBedMasterList($scope.organizationId, $scope.offset, $scope.noOfRecordsPerPage);

													});
								}
							}

						} ]);

