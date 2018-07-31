'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:religionMasterController
 * @description # religionMasterController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'religionMasterController',
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

								$scope.LISDynamicLabel = "Religion";
								$scope.saveBtnFlag = true;
								$scope.updateBtnFlag = false;

								$scope.common = {};

								/* code for setting Label */
								$(".selectedPageName").text("Religion Master");
								var data = {};
								// $scope.status = "NO";
								/*
								 * var URI = BASE_URL + ROOT_URL +
								 * GETRELIGIONLIST; GenericService
								 * .serviceAction("GET", URI, data) .then(
								 * function(response) { $scope.commonList = [];
								 * 
								 * if (response.data.status == "success") {
								 * $scope.commonList = response.data.listObject;
								 *  } });
								 */

							};
							$scope.init();

							// For Paginations Start
							$scope.setNoOfRecords = function() {
								$scope.initReligionMasterList($scope.organizationId, $scope.offset, $scope.noOfRecordsPerPage);($scope.organizationId, $scope.offset, $scope.noOfRecordsPerPage);
							};
							$scope.initReligionMasterList = function(orgId, offset, noOfRecordsPerPage)
							{
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage : 10;
								var URI = BASE_URL + ROOT_URL + GETRELIGIONLIST;
								var URI1 = BASE_URL + ROOT_URL + COUNTRELIGIONMASTER;
								
								var data = {
										organizationId:orgId,
										offset : offset,
										noOfRecordsPerPage : noOfRecordsPerPage
								}
								
								var data1 = {
										organizationId:$scope.organizationId
								}
								
								// For Get Religion List Using Offset
								GenericService
								.serviceAction("POST", URI, data)
								.then(
										function(response) {
											$scope.commonList = [];

											if (response.data.status == "success") {
												$scope.commonList = response.data.listObject;

											}
										});
								
								// For Count Religion List
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

							$scope.getReligionMasterList = function(orgId, offset, noOfRecordsPerPage) {
								console.log("offset", offset);
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage : 10;
								var URI = BASE_URL + ROOT_URL + GETRELIGIONLIST;
								var data = {
										organizationId:$scope.organizationId,
										offset : offset,
										noOfRecordsPerPage : noOfRecordsPerPage
								}
								
								console.log("URI", URI);
								// For Get Relgion List Using Offset
								GenericService
								.serviceAction("POST", URI, data)
								.then(
										function(response) {
											$scope.commonList = [];

											if (response.data.status == "success") {
												$scope.commonList = response.data.listObject;

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
									$scope.getReligionMasterList($scope.organizationId, $scope.pager.startIndex, $scope.pager.pageSize);
								}
							}

							$scope.initReligionMasterList($scope.organizationId, $scope.offset, $scope.noOfRecordsPerPage);
							
							// For Paginations End
							
								
							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.save();
							}

							// Save function for add religion
							$scope.save = function() {
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								$scope.$broadcast('show-errors-check-validity');
								if ($scope.commonform.$valid) {
									if ($scope.popUpFlag) {
										$scope.showModal = !$scope.showModal;

									} else {

										var data = {
											code : $scope.common.code,
											desc : $scope.common.desc,
											status : 'A',
											createdBy : $scope.userId,
											updatedBy : $scope.userId,
											organizationId : $scope.organizationId,
											createdDate : $scope.currentDate,
											updatedDate : $scope.currentDate
										}

										console.log(data);
										var URI = BASE_URL + ROOT_URL
												+ ADDRELIGION;
										GenericService
												.serviceAction("POST", URI,
														data)
												.then(
														function(response) {

															if (response.data.status == "success") {
																growl
																		.success(
																				'Religion added sucessfully!!!.',
																				{
																					title : 'Success!'
																				});

																$scope
																		.$broadcast('show-errors-reset');
																$scope.init();
															} else {
																growl
																		.error(
																				'Religion already exits!!!!.',
																				{
																					title : 'Error!'
																				});
															}
															$scope.initReligionMasterList($scope.organizationId, $scope.offset, $scope.noOfRecordsPerPage);
														});
									}
								}

							};

							// function for get religion object for update
							$scope.showUpdateBtn = function(id) {

								$scope.saveBtnFlag = false;
								$scope.updateBtnFlag = true;

								var data = {
									id : id
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ GETSINGLERELIGION;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													if (response.data.status == "success") {
														$scope.common.incremetedId = response.data.listObject[0].id;
														$scope.common.code = response.data.listObject[0].code;
														$scope.common.desc = response.data.listObject[0].desc;
													} else {
														alert("Error!!");
													}
												});

							};

							// function for checked status
							$scope.checkVal = function(statusreligion) {
								$scope.status = (statusreligion == "A") ? "A"
										: "I";
								return (statusreligion == "A") ? true : false;
							}

							// function for update status religion
							$scope.updateStatus = function(id, type) {

								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								type = (type == "A") ? "A" : "I";
								var data = {
									id : id,
									status : type,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ UPDATERELIGIONSTATUS;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {

													if (response.data.status == "success") {
														growl
																.success(
																		'Religion status changed sucessfully!!!.',
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

							// function for update religion
							$scope.update = function() {
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								$scope.$broadcast('show-errors-check-validity');
								if ($scope.commonform.$valid) {
									var data = {
										id : $scope.common.incremetedId,
										code : $scope.common.code,
										desc : $scope.common.desc,
										updatedBy : $scope.userId,
										updatedDate : $scope.currentDate
									}
									console.log(data);
									var URI = BASE_URL + ROOT_URL
											+ UPDATERELIGION;
									GenericService
											.serviceAction("POST", URI, data)
											.then(
													function(response) {

														if (response.data.status == "success") {
															growl
																	.success(
																			'Religion updated sucessfully!!!.',
																			{
																				title : 'Success!'
																			});
															$scope
																	.$broadcast('show-errors-reset');
															$scope.init();

														} else {
															growl
																	.error(
																			'Religion already exits!!!!.',
																			{
																				title : 'Error!'
																			});
														}
														$scope.initReligionMasterList($scope.organizationId, $scope.offset, $scope.noOfRecordsPerPage);
													});
								}
							}

						} ]);
