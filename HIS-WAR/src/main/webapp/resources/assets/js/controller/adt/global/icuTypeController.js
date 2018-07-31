'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:icuTypeController
 * @description # icuTypeController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'icuTypeController',
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
								$scope.icuTypeObj = {
									isOpen : 'N',
									isClose : 'N'
								}
								$scope.saveBtnFlag = false;
								$scope.updateBtnFlag = true;

								// for popup
								$scope.showModal = false;
								$scope.buttonClicked = "";
								$scope.popUpFlag = true;
								// end for common popup

								var data = {};
								// $scope.status = "NO";
								/*
								 * var URI = BASE_URL + ROOT_URL +
								 * GETICUTYPELIST; GenericService
								 * .serviceAction("GET", URI, data) .then(
								 * function(response) { $scope.icuTypeList = [];
								 * 
								 * if (response.data.status == "success") {
								 * $scope.icuTypeList =
								 * response.data.listObject; console
								 * .log($scope.icuTypeList);
								 *  } });
								 */
							}

							$scope.init();

							// For Paginations Start
							$scope.setNoOfRecords = function() {
								$scope.initIcUTypeMasterList(
										$scope.organizationId, $scope.offset,
										$scope.noOfRecordsPerPage);
							};
							$scope.initIcUTypeMasterList = function(orgId,
									offset, noOfRecordsPerPage) {
								$rootScope.startLoader();
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
										: 10;
								var URI = BASE_URL + ROOT_URL + GETICUTYPELIST;
								var URI1 = BASE_URL + ROOT_URL
										+ COUNTICUTYPEMASTER;

								var data = {
									organizationId : orgId,
									offset : offset,
									noOfRecordsPerPage : noOfRecordsPerPage
								}

								var data1 = {
									organizationId : $scope.organizationId
								}

								// For Get icu Type List Using Offset
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.icuTypeList = [];

													if (response.data.status == "success") {
														$rootScope.stopLoader();
														$scope.icuTypeList = response.data.listObject;

													}
												});

								// For Count icu Type List
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

							$scope.getIcUTypeMasterList = function(orgId,
									offset, noOfRecordsPerPage) {
								console.log("offset", offset);
								$rootScope.startLoader();
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
										: 10;
								var URI = BASE_URL + ROOT_URL + GETICUTYPELIST;
								var data = {
									organizationId : $scope.organizationId,
									offset : offset,
									noOfRecordsPerPage : noOfRecordsPerPage
								}

								console.log("URI", URI);
								// For Get icu Type List Using Offset
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.icuTypeList = [];

													if (response.data.status == "success") {
														$rootScope.stopLoader();
														$scope.icuTypeList = response.data.listObject;

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
									$scope.getIcUTypeMasterList(
											$scope.organizationId,
											$scope.pager.startIndex,
											$scope.pager.pageSize);
								}
							}

							$scope.initIcUTypeMasterList($scope.organizationId,
									$scope.offset, $scope.noOfRecordsPerPage);

							// For Paginations End

							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.saveIcuType();
							}

							// Function for save icu Type
							$scope.saveIcuType = function() {
								
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');
								$scope.$broadcast('show-errors-check-validity');
								if ($scope.icuTypeform.$valid) {

									if ($scope.popUpFlag) {
										$scope.showModal = !$scope.showModal;

									} else {
										$rootScope.startLoader();
										
										//alert($scope.icuTypeObj.isOpen);
										if($scope.icuTypeObj.isOpen == 'open')
											{
											$scope.icuTypeObj.isOpen  =  'Y';
											$scope.icuTypeObj.isClose  = 'N';
											}
										else if($scope.icuTypeObj.isOpen == 'close')
											{
											$scope.icuTypeObj.isOpen  =  'N';
											$scope.icuTypeObj.isClose  = 'Y';
											
											}
										
										var data = {
											icutypeCode : $scope.icuTypeObj.icuCode,
											icutype : $scope.icuTypeObj.icuType,
											isCloseICU : $scope.icuTypeObj.isClose,
											isOpenICU : $scope.icuTypeObj.isOpen,
											status : 'A',
											organizationId : $scope.organizationId,
											updatedBy : $scope.userId,
											createdBy : $scope.userId,
											createdDate : $scope.currentDate,
											updatedDate : $scope.currentDate
										}

										var URI = BASE_URL + ROOT_URL
												+ ADDICUTYPE;
										GenericService
												.serviceAction("POST", URI,
														data)
												.then(
														function(response) {

															if (response.data.status == "success") {
																growl
																		.success(
																				'Icu Type added sucessfully!!!.',
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
																				'Icu Type already exits!!!!.',
																				{
																					title : 'Error!'
																				});
																$rootScope.stopLoader();
															}
															$scope
																	.initIcUTypeMasterList(
																			$scope.organizationId,
																			$scope.offset,
																			$scope.noOfRecordsPerPage);

														});
									}
								}

							}

							// Function for get single icu Type
							$scope.showUpdateBtn = function(id) {
								$rootScope.startLoader();
								$scope.saveBtnFlag = true;
								$scope.updateBtnFlag = false;

								var data = {

									icutypeMasterId : id
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ GETSINGLEICUTYPE;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													// $scope.speciality =[];
													if (response.data.status == "success") {
														// $scope.speciality =
														// //
														$rootScope.stopLoader();
														response.data.listObject; //
														// alert(response.data.listObject[0].specialityId);
														$scope.icuTypeObj.icuTypeId = response.data.listObject[0].icutypeMasterId;
														$scope.icuTypeObj.icuCode = response.data.listObject[0].icutypeCode;
														$scope.icuTypeObj.icuType = response.data.listObject[0].icutype;
														
														if( response.data.listObject[0].isOpenICU == 'Y')
														{
														$scope.icuTypeObj.isOpen  =  'open';
														}
													else if(response.data.listObject[0].isCloseICU == 'Y')
														{
														$scope.icuTypeObj.isOpen  =  'close';
														
														}
														
														//$scope.icuTypeObj.isClose = response.data.listObject[0].isCloseICU;
														//$scope.icuTypeObj.isOpen = response.data.listObject[0].isOpenICU;
													} else {
														$rootScope.stopLoader();
														alert("Error!!");
													}
												});

							};

							// Function for update icu Type status
							$scope.updateStatus = function(id, type) {
								$rootScope.startLoader();
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								type = (type == "A") ? "A" : "I";

								var data = {
									icutypeMasterId : id,
									status : type,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ UPDATEICUTYPESTATUS;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {

													if (response.data.status == "success") {
														growl
																.success(
																		'Icu Type status changed sucessfully!!!.',
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

							// Function for update icu Type
							$scope.updateIcuType = function() {
								
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');
								$scope.$broadcast('show-errors-check-validity');

								if ($scope.icuTypeform.$valid) {
									console.log($scope.icuTypeObj);
									$rootScope.startLoader();
									if($scope.icuTypeObj.isOpen == 'open')
									{
									$scope.icuTypeObj.isOpen  =  'Y';
									$scope.icuTypeObj.isClose  = 'N';
									}
								else if($scope.icuTypeObj.isOpen == 'close')
									{
									$scope.icuTypeObj.isOpen  =  'N';
									$scope.icuTypeObj.isClose  = 'Y';
									
									}

									var data = {
										icutypeCode : $scope.icuTypeObj.icuCode,
										icutype : $scope.icuTypeObj.icuType,
										isCloseICU : $scope.icuTypeObj.isClose,
										isOpenICU : $scope.icuTypeObj.isOpen,
										icutypeMasterId : $scope.icuTypeObj.icuTypeId,
										organizationId : $scope.organizationId,
										updatedBy : $scope.userId,
										updatedDate : $scope.currentDate
									}

									console.log(data);
									var URI = BASE_URL + ROOT_URL
											+ UPDATEICUTYPE;
									GenericService
											.serviceAction("POST", URI, data)
											.then(
													function(response) {

														if (response.data.status == "success") {
															growl
																	.success(
																			'Icu Type updated sucessfully!!!.',
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
																			'Icu Type already exits!!!!.',
																			{
																				title : 'Error!'
																			});
														}
														$scope
																.initIcUTypeMasterList(
																		$scope.organizationId,
																		$scope.offset,
																		$scope.noOfRecordsPerPage);

													});
									$rootScope.stopLoader();
								}

							}

						} ]);
