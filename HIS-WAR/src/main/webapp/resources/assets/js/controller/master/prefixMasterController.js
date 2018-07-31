'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:prefixMasterController
 * @description #prefixMasterController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'prefixMasterController',
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
								$scope.prefix = {}
								$scope.saveBtnFlag = false;
								$scope.updateBtnFlag = true;

								// for popup
								$scope.showModal = false;
								$scope.buttonClicked = "";
								$scope.popUpFlag = true;
								// end for popup

								var data = {};
								// $scope.status = "NO";
								/*var URI = BASE_URL + ROOT_URL + GETPREFIXLIST;
								GenericService
										.serviceAction("GET", URI, data)
										.then(
												function(response) {
													$scope.prefixList = [];

													if (response.data.status == "success") {
														$scope.prefixList = response.data.listObject;
														console
																.log($scope.prefixList);

													}
												});*/
								var URI = BASE_URL + ROOT_URL
										+ GETACTIVEGENDERLIST;
								GenericService
										.serviceAction("GET", URI, data)
										.then(
												function(response) {
													$scope.genderList = [];

													if (response.data.status == "success") {
														$scope.genderList = response.data.listObject;
														console
																.log($scope.genderList);

													}
												});

							}

							$scope.init();
							
							//For Paginations Start
							$scope.setNoOfRecords = function() {
								$scope.initPrefixMasterList($scope.organizationId, $scope.offset, $scope.noOfRecordsPerPage);
							};
							$scope.initPrefixMasterList = function(orgId, offset, noOfRecordsPerPage)
							{
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage : 10;
								var URI = BASE_URL + ROOT_URL + GETPREFIXLIST;
								var URI1 = BASE_URL + ROOT_URL
										+ COUNTPREFIXMASTER;
								
								var data = {
										organizationId:orgId,
										offset : offset,
										noOfRecordsPerPage : noOfRecordsPerPage
								}
								
								var data1 = {
										organizationId:$scope.organizationId
								}
								
								//For Get Prefix List Using Offset
								GenericService
								.serviceAction("POST", URI, data)
								.then(
										function(response) {
											$scope.prefixList = [];

											if (response.data.status == "success") {
												$scope.prefixList = response.data.listObject;

											}
										});
								
								//For Count Prefix List
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

							$scope.getPrefixMasterList = function(orgId, offset, noOfRecordsPerPage) {
								console.log("offset", offset);
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage : 10;
								var URI = BASE_URL + ROOT_URL + GETPREFIXLIST;
								var data = {
										organizationId:$scope.organizationId,
										offset : offset,
										noOfRecordsPerPage : noOfRecordsPerPage
								}
								
								console.log("URI", URI);
								//For Get Prefix List Using Offset
								GenericService
								.serviceAction("POST", URI, data)
								.then(
										function(response) {
											$scope.prefixList = [];

											if (response.data.status == "success") {
												$scope.prefixList = response.data.listObject;

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
									$scope.getPrefixMasterList($scope.organizationId, $scope.pager.startIndex, $scope.pager.pageSize);
								}
							}

							$scope.initPrefixMasterList($scope.organizationId, $scope.offset, $scope.noOfRecordsPerPage);
							
							//For Paginations End
							

							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.savePrefix();
							}

							//Function for save Prefix
							$scope.savePrefix = function() {
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								$scope.$broadcast('show-errors-check-validity');

								var data = {
									prefixCode : $scope.prefix.prefixCode,
									prefixDesc : $scope.prefix.prefixName,
									id : $scope.prefix.genderId,
									status : 'A',
									createdBy : $scope.userId,
									updatedBy : $scope.userId,
									organizationId : $scope.organizationId,
									createdDate : $scope.currentDate,
									updatedDate : $scope.currentDate
								}
								if ($scope.prefixform.$valid) {
									if ($scope.popUpFlag) {
										$scope.showModal = !$scope.showModal;

									} else {

										console.log(data);

										var URI = BASE_URL + ROOT_URL
												+ ADDPREFIX;
										GenericService
												.serviceAction("POST", URI,
														data)
												.then(
														function(response) {

															if (response.data.status == "success") {
																growl
																		.success(
																				'Prefix added sucessfully!!!.',
																				{
																					title : 'Success!'
																				});
																$scope
																		.$broadcast('show-errors-reset');
																$scope.init();
															} else {
																growl
																		.error(
																				'Prefix already exits!!!!.',
																				{
																					title : 'Error!'
																				});
															}
															$scope.initPrefixMasterList($scope.organizationId, $scope.offset, $scope.noOfRecordsPerPage);

														});
									}
								}

							}
							
							//Function for get single Prefix
							$scope.showUpdateBtn = function(id) {

								$scope.saveBtnFlag = true;
								$scope.updateBtnFlag = false;

								var data = {

									prefixId : id
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL + GETSINGLEPREFIX;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													// $scope.speciality =[];
													if (response.data.status == "success") {

														$scope.prefix.prefixId = response.data.listObject[0].prefixId;
														$scope.prefix.prefixCode = response.data.listObject[0].prefixCode;
														$scope.prefix.prefixName = response.data.listObject[0].prefixDesc;
														$scope.prefix.genderId = response.data.listObject[0].id
																.toString();
													} else {
														alert("Error!!");
													}
												});

							};

							//Function for update Prefix status
							$scope.updateStatus = function(id, type) {

								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								type = (type == "A") ? "A" : "I";

								var data = {
									prefixId : id,
									status : type,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ UPDATEPREFIXSTATUS;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {

													if (response.data.status == "success") {
														growl
																.success(
																		'Prefix status changed sucessfully!!!.',
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

							//Function for update Prefix
							$scope.updatePrefix = function() {

								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								$scope.$broadcast('show-errors-check-validity');
								var data = {
									prefixCode : $scope.prefix.prefixCode,
									prefixDesc : $scope.prefix.prefixName,
									prefixId : $scope.prefix.prefixId,
									id : $scope.prefix.genderId,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate
								}
								if ($scope.prefixform.$valid) {
									console.log(data);
									var URI = BASE_URL + ROOT_URL
											+ UPDATEPREFIX;
									GenericService
											.serviceAction("POST", URI, data)
											.then(
													function(response) {

														if (response.data.status == "success") {
															growl
																	.success(
																			'Prefix updated sucessfully!!!.',
																			{
																				title : 'Success!'
																			});
															$scope
																	.$broadcast('show-errors-reset');
															$scope.init();
														} else {
															growl
																	.error(
																			'Prefix already exits!!!!.',
																			{
																				title : 'Error!'
																			});
														}
														$scope.initPrefixMasterList($scope.organizationId, $scope.offset, $scope.noOfRecordsPerPage);

													});
								}
							}

						} ]);
