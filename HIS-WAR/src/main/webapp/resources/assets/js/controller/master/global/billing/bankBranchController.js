'use strict';

/**
 * @Author
 * @name myApp.controller:bankBranchController
 * @description #bankBranchController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'bankBranchController',
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
								$scope.bankBranchMasterObj = {
								}
								$scope.saveBtnFlag = true;
								$scope.updateBtnFlag = false;

								// for popup
								$scope.showModal = false;
								$scope.buttonClicked = "";
								$scope.popUpFlag = true;
								// end for popup

								var data = {};
							}

							$scope.init();

							// For Paginations Start
							$scope.setNoOfRecords = function() {
								$scope.initBankBranchMaster(
										$scope.organizationId, $scope.offset,
										$scope.noOfRecordsPerPage);
							};
							$scope.initBankBranchMaster = function(orgId,
									offset, noOfRecordsPerPage) {
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
										: 10;
								var URI = BASE_URL + ROOT_URL
										+ GETBANKBRANCHMASTERLIST;
								var URI1 = BASE_URL + ROOT_URL
										+ GETBANKBRANCHMASTERCOUNT;

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
													$scope.bankBranchList = [];

													if (response.data.status == "success") {
														$scope.bankBranchList = response.data.listObject;
														console
																.log(
																		"list",
																		$scope.bankBranchList);

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

							$scope.getPatientCategoryMasterList = function(
									orgId, offset, noOfRecordsPerPage) {
								console.log("offset", offset);
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
										: 10;
								var URI = BASE_URL + ROOT_URL
										+ GETBANKBRANCHMASTERLIST;
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
													$scope.bankBranchList = [];

													if (response.data.status == "success") {
														$scope.bankBranchList = response.data.listObject;

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
									$scope.getPatientCategoryMasterList(
											$scope.organizationId,
											$scope.pager.startIndex,
											$scope.pager.pageSize);
								}
							}

							$scope.initBankBranchMaster(
									$scope.organizationId, $scope.offset,
									$scope.noOfRecordsPerPage);

							// For Paginations End

							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.saveBankBranch();
							}

							// Function For saveBankBranch()
							$scope.saveBankBranch = function() {
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								$scope.$broadcast('show-errors-check-validity');
								var data = {
									branchDesc : $scope.bankBranchMasterObj.branchDesc,
									branchCode : $scope.bankBranchMasterObj.branchCode,
									status : 'A',
									createdBy : $scope.userId,
									updatedBy : $scope.userId,
									orgnisationId : $scope.organizationId,
									createdDate : $scope.currentDate,
									updatedDate : $scope.currentDate
								}
								console.log("save",data);
// return false;
								 if ($scope.bankBranchForm.$valid) {

									if ($scope.popUpFlag) {
										$scope.showModal = !$scope.showModal;

									} else {

										var URI = BASE_URL + ROOT_URL
												+ SAVEBANKBRANCHMASTER;
										GenericService
												.serviceAction("POST", URI,
														data)
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
																	.initBankBranchMaster(
																			$scope.organizationId,
																			$scope.offset,
																			$scope.noOfRecordsPerPage);

														});
									}
								 }

							}

							// Function For get single BankBranch()
							$scope.showUpdateBtn = function(id) {

								$scope.saveBtnFlag = false;
								$scope.updateBtnFlag = true;

								var data = {

								}
								var URI = BASE_URL + ROOT_URL
										+ GETBANKBRANCHMASTERBYID + "/"+ id + "/" + $scope.organizationId;
								console.log("URI",URI);
								GenericService
										.serviceAction("GET", URI, data)
										.then(
												function(response) {
													// $scope.speciality =[];
													if (response.data.status == "success") {
														$scope.bankBranchMasterObj.branchId = response.data.listObject[0].branchId;
														$scope.bankBranchMasterObj.branchCode = response.data.listObject[0].branchCode;
														$scope.bankBranchMasterObj.branchDesc = response.data.listObject[0].branchDesc;
													} else {
														alert("Error!!");
													}
												});

							};

							// Function For update bank branch status
							$scope.updateStatus = function(id, type) {
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								type = (type == "A") ? "A" : "I";

								var data = {
									branchId : id,
									status : type,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate
								}
								var URI = BASE_URL + ROOT_URL
										+ UPDATEBANKBRANCHMASTERSTATUS;
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

							// Function For updateBankBranch()
							$scope.updateBankBranch = function() {
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');
								$scope.$broadcast('show-errors-check-validity');
								var data = {
										branchDesc : $scope.bankBranchMasterObj.branchDesc,
									branchCode : $scope.bankBranchMasterObj.branchCode,
									branchId : $scope.bankBranchMasterObj.branchId,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate
								}
								

								if ($scope.bankBranchForm.$valid) {
									console.log("update", data);
									var URI = BASE_URL + ROOT_URL
											+ UPDATEBANKBRANCHMASTER;
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
																.initBankBranchMaster(
																		$scope.organizationId,
																		$scope.offset,
																		$scope.noOfRecordsPerPage);

													});
								}

							}
						} ]);
