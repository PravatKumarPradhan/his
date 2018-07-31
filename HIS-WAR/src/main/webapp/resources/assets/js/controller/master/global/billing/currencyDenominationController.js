'use strict';

/**
 * @Author
 * @name myApp.controller:currencyDenominationController
 * @description #currencyDenominationController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'currencyDenominationController',
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
								$scope.currencyDenominationMasterObj = {
									lowestDenomination : 'N'
								}
								$scope.saveBtnFlag = true;
								$scope.updateBtnFlag = false;

								// for popup
								$scope.showModal = false;
								$scope.buttonClicked = "";
								$scope.popUpFlag = true;
								// end for popup

								var data = {};
								// getting currency list
								var URI = BASE_URL + ROOT_URL
										+ GETCURRENCYMASTERLIST;

								var data = {
									orgnisationId : $scope.organizationId

								}
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.currencyList = [];

													if (response.data.status == "success") {
														$scope.currencyList = response.data.listObject;
														console
																.log(
																		"currency list",
																		$scope.currencyList);

													}
												});
							}

							$scope.init();

							// For Paginations Start
							$scope.setNoOfRecords = function() {
								$scope.initCurrencyDenominatoinMaster(
										$scope.organizationId, $scope.offset,
										$scope.noOfRecordsPerPage);
							};
							$scope.initCurrencyDenominatoinMaster = function(
									orgId, offset, noOfRecordsPerPage) {
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
										: 10;
								var URI = BASE_URL + ROOT_URL
										+ GETCURRENCYDENOMINATIONMASTERLIST;
								var URI1 = BASE_URL + ROOT_URL
										+ GETCURRENCYDENOMINATIONCOUNT;

								var data = {
									orgnisationId : orgId,
									offset : offset,
									noOfRecordsPerPage : noOfRecordsPerPage
								}

								var data1 = {
									orgnisationId : $scope.organizationId
								}

								// For Get patient Category List Using Offset
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.currencyDenominationList = [];

													if (response.data.status == "success") {
														$scope.currencyDenominationList = response.data.listObject;
														console
																.log(
																		"currencyDenominationListlist",
																		$scope.currencyDenominationList);

													}
												});

								// For Count patient List
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

							$scope.getCurrencyDenominatoinMasterList = function(
									orgId, offset, noOfRecordsPerPage) {
								console.log("offset", offset);
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
										: 10;
								var URI = BASE_URL + ROOT_URL
										+ GETCURRENCYDENOMINATIONMASTERLIST;
								var data = {
									orgnisationId : $scope.organizationId,
									offset : offset,
									noOfRecordsPerPage : noOfRecordsPerPage
								}

								console.log("URI", URI);
								// For Get patientList Using Offset
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.currencyDenominationList = [];

													if (response.data.status == "success") {
														$scope.currencyDenominationList = response.data.listObject;

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
									$scope.getCurrencyDenominatoinMasterList(
											$scope.organizationId,
											$scope.pager.startIndex,
											$scope.pager.pageSize);
								}
							}

							$scope.initCurrencyDenominatoinMaster(
									$scope.organizationId, $scope.offset,
									$scope.noOfRecordsPerPage);

							// For Paginations End

							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.saveCurrencyDenomination();
							}

							// Function For saveCurrencyDenomination()
							$scope.saveCurrencyDenomination = function() {
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								 $scope.$broadcast('show-errors-check-validity');
								var data = {
										currencyId : $scope.currencyDenominationMasterObj.currencyId,
										denominationCode : $scope.currencyDenominationMasterObj.denominationCode,
										denominationDesc : $scope.currencyDenominationMasterObj.denominationDesc,
										denominatonUnit : $scope.currencyDenominationMasterObj.denominatonUnit,
										lowestDenomination : $scope.currencyDenominationMasterObj.lowestDenomination,
									status : 'A',
									createdBy : $scope.userId,
									updatedBy : $scope.userId,
									orgnisationId : $scope.organizationId,
									createdDate : $scope.currentDate,
									updatedDate : $scope.currentDate
								}
								console.log("save", data);
								// return false;
								 if ($scope.currencyDenominationform.$valid) {

								if ($scope.popUpFlag) {
									$scope.showModal = !$scope.showModal;

								} else {

									var URI = BASE_URL + ROOT_URL
											+ SAVECURRENCYDENOMINATIONMASTER;
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
																.initCurrencyDenominatoinMaster(
																		$scope.organizationId,
																		$scope.offset,
																		$scope.noOfRecordsPerPage);

													});
								}
								 }

							}

							// Function For get single currency Denomination()
							$scope.showUpdateBtn = function(id) {

								$scope.saveBtnFlag = false;
								$scope.updateBtnFlag = true;

								var data = {

								}
								var URI = BASE_URL + ROOT_URL
										+ GETCURRENCYDENOMINATIONMASTERBYID
										+ "/" + id + "/"
										+ $scope.organizationId;
								console.log("URI", URI);
								GenericService
										.serviceAction("GET", URI, data)
										.then(
												function(response) {
													if (response.data.status == "success") {
														$scope.currencyDenominationMasterObj.currencyDenominationId = response.data.listObject[0].currencyDenominationId;
														$scope.currencyDenominationMasterObj.denominationDesc = response.data.listObject[0].denominationDesc;
														$scope.currencyDenominationMasterObj.denominatonUnit = response.data.listObject[0].denominatonUnit;
														$scope.currencyDenominationMasterObj.lowestDenomination = response.data.listObject[0].lowestDenomination;
														$scope.currencyDenominationMasterObj.denominationCode = response.data.listObject[0].denominationCode,
														$scope.currencyDenominationMasterObj.currencyId = response.data.listObject[0].currencyId
																.toString();
													} else {
														alert("Error!!");
													}
												});

							};

							// Function For update currency Denomination status
							$scope.updateStatus = function(id, type) {
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								type = (type == "A") ? "A" : "I";

								var data = {
									currencyDenominationId : id,
									status : type,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate
								}
								var URI = BASE_URL
										+ ROOT_URL
										+ UPDATECURRENCYDENOMINATIONMASTERSTATUS;
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

							// Function For currency Denomination()
							$scope.updateCurrencyDenomination = function() {
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');
								 $scope.$broadcast('show-errors-check-validity');
								var data = {
									currencyDenominationId : $scope.currencyDenominationMasterObj.currencyDenominationId,
									denominationDesc : $scope.currencyDenominationMasterObj.denominationDesc,
									denominatonUnit : $scope.currencyDenominationMasterObj.denominatonUnit,
									lowestDenomination : $scope.currencyDenominationMasterObj.lowestDenomination,
									denominationCode : $scope.currencyDenominationMasterObj.denominationCode,
									currencyId : $scope.currencyDenominationMasterObj.currencyId,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate
								}
							

								 if ($scoCpe.currencyDenominationform.$valid) {
								console.log("update", data);
								var URI = BASE_URL + ROOT_URL
										+ UPDATECURRENCYDENOMINATIONMASTER;
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
															.initCurrencyDenominatoinMaster(
																	$scope.organizationId,
																	$scope.offset,
																	$scope.noOfRecordsPerPage);

												});
								 }

							}
						} ]);
