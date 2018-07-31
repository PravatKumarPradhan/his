'use strict';

/**
 * @Author
 * @name myApp.controller:employeeCategoryController
 * @description #employeeCategoryController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'currencyMasterController',
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

								$scope.unitId = 1;
								$scope.orgnisationId = 1;
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
								$scope.currencyMaster = {}
								$scope.country = {}
								$scope.saveBtnFlag = true;
								$scope.updateBtnFlag = false;

								// for popup
								$scope.showModal = false;
								$scope.buttonClicked = "";
								$scope.popUpFlag = true;
								// end for popup

								var data = {};
								var URI = BASE_URL + ROOT_URL
										+ GETACTIVECOUNTRYLIST;
								GenericService
										.serviceAction("GET", URI, data)
										.then(
												function(response) {
													$scope.countryList = [];

													if (response.data.status == "success") {
														$scope.countryList = response.data.listObject;
														console
																.log(
																		"countryList",
																		$scope.countryList);

													}
												});

							}

							$scope.init();

							// For Paginations Start
							$scope.setNoOfRecords = function() {
								$scope.initCurrencyMasterList(
										$scope.orgnisationId, $scope.offset,
										$scope.noOfRecordsPerPage);
							};
							$scope.initCurrencyMasterList = function(orgId,
									offset, noOfRecordsPerPage) {
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
										: 10;
								var URI = BASE_URL + ROOT_URL
										+ GETCURRENCYMASTERLIST;
								var URI1 = BASE_URL + ROOT_URL
										+ GETCURRENCYCOUNT;

								var data = {
									orgnisationId : orgId,
									offset : offset,
									noOfRecordsPerPage : noOfRecordsPerPage
								}

								var data1 = {
									orgnisationId : $scope.orgnisationId
								}

								// For Get Employee Category List Using Offset
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.currencyList = [];

													if (response.data.status == "success") {
														$scope.currencyList = response.data.listObject;
														console.log("list",
																		$scope.currencyList);

													}
												});

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

							
							$scope.getCurrencyMasterList = function(orgId,
									offset, noOfRecordsPerPage) {
								console.log("offset", offset);
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
										: 10;
								var URI = BASE_URL + ROOT_URL
										+ GETCURRENCYMASTERLIST;
								var data = {
									orgnisationId : $scope.orgnisationId,
									offset : offset,
									noOfRecordsPerPage : noOfRecordsPerPage
								}

								console.log("URI", URI);
								// For Get Country List Using Offset
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.currencyList = [];

													if (response.data.status == "success") {
														$scope.currencyList = response.data.listObject;

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
									$scope.getCurrencyMasterList(
											$scope.orgnisationId,
											$scope.pager.startIndex,
											$scope.pager.pageSize);
								}
							}

							$scope.initCurrencyMasterList(
									$scope.orgnisationId, $scope.offset,
									$scope.noOfRecordsPerPage);

							// For Paginations End
							//getting country code from country id
							$scope.getCountryCode = function(id) {
								console.log("id", id);
								if (null == id) {
									console.log("select country");
								} else {
									var data = {
										countryId : id
									};
									var URI = BASE_URL + ROOT_URL
											+ GETSINGLECOUNTRY;
									GenericService
											.serviceAction("POST", URI, data)
											.then(
													function(response) {
														if (response.data.status == "success") {
															$scope.currencyMaster.countryCode = response.data.listObject[0].countryCode;
														} else {
															alert("Error!!");
														}
													});
								}

							}
							
							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.saveCurrency();
							}

							
							// Function For saveEmployee()
							$scope.saveCurrency = function() {
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								 $scope.$broadcast('show-errors-check-validity');
								var data = {
									currencyName : $scope.currencyMaster.currencyName,
									countryId : $scope.currencyMaster.countryId,
									currencySymbol : "",
									currencyCode : $scope.currencyMaster.currencyCode,
									status : 'A',
									createdBy : $scope.userId,
									updatedBy : $scope.userId,
									orgnisationId : $scope.orgnisationId,
									createdDate : $scope.currentDate,
									updatedDate : $scope.currentDate
								}
//								console.log("data",data); return false;
								 if ($scope.currencyform.$valid) {

								if ($scope.popUpFlag) {
									$scope.showModal = !$scope.showModal;

								} else {

									var URI = BASE_URL + ROOT_URL
											+ SAVECURRENCYMASTER;
									GenericService
											.serviceAction("POST", URI, data)
											.then(
													function(response) {
														if (response.data.status == "success") {
															$scope.init();
															growl
																	.success(
																			response.data.message,
																			{
																				title : 'Success!'
																			});
														} else {
															growl
																	.error(
																			response.data.message,
																			{
																				title : 'Error!'
																			});
														}

														$scope
																.initCurrencyMasterList(
																		$scope.orgnisationId,
																		$scope.offset,
																		$scope.noOfRecordsPerPage);

													});
								}
								 }

							}

							// Function For get single Employee()
							$scope.showUpdateBtn = function(id) {

								$scope.saveBtnFlag = false;
								$scope.updateBtnFlag = true;

								var data = {

								}
								var URI = BASE_URL + ROOT_URL
										+ GETCURRENCYMASTERBYID + "/" + id
										+ "/" + $scope.orgnisationId;
								GenericService
										.serviceAction("GET", URI, data)
										.then(
												function(response) {
													// $scope.speciality =[];
													if (response.data.status == "success") {
														$scope.currencyMaster.currencyName = response.data.listObject[0].currencyName;
														$scope.currencyMaster.currencyCode = response.data.listObject[0].currencyCode;
														$scope.currencyMaster.countryName = response.data.listObject[0].countryName;
														$scope.currencyMaster.countryCode = response.data.listObject[0].countryCode;
														$scope.currencyMaster.currencyId = response.data.listObject[0].currencyId;
														$scope.currencyMaster.countryId = response.data.listObject[0].countryId.toString();
													} else {
														alert("Error!!");
													}
												});

							};

							// Function For update country status
							$scope.updateStatus = function(id, type) {
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								type = (type == "A") ? "A" : "I";

								var data = {
										currencyId : id,
									status : type,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ UPDATECURRENCYMASTERSTATUS;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													if (response.data.status == "success") {
														$scope.init();
														growl
																.success(
																		response.data.message,
																		{
																			title : 'Success!'
																		});
													} else {
														growl
																.error(
																		response.data.message,
																		{
																			title : 'Error!'
																		});
													}

												});
							}

							// Function For updateEmployee()
							$scope.updateCurrency = function() {
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');
								 $scope.$broadcast('show-errors-check-validity');
								var data = {
										currencyName : $scope.currencyMaster.currencyName,
										currencyCode :$scope.currencyMaster.currencyCode,										
										currencyId : $scope.currencyMaster.currencyId,
										countryId : $scope.currencyMaster.countryId,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate,
									orgnisationId:$scope.orgnisationId									
								}
								

								 if ($scope.employeeCategoryForm.$valid) {
								console.log("update", data); 
								var URI = BASE_URL + ROOT_URL
										+ UPDATECURRENCYMASTER;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													alert(response.data.status);
													if (response.data.status == "success") {
														$scope.init();
														growl
																.success(
																		response.data.message,
																		{
																			title : 'Success!'
																		});
													} else {
														growl
																.error(
																		response.data.message,
																		{
																			title : 'Error!'
																		});
													}
													$scope
															.initCurrencyMasterList(
																	$scope.orgnisationId,
																	$scope.offset,
																	$scope.noOfRecordsPerPage);

												});
								 }

							}
						} ]);
