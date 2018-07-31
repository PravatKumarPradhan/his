'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:encounterTypeMasterController
 * @description #encounterTypeMasterController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'encounterTypeMasterController',
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
								$scope.encountertype = {}
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
										+ GETENCOUNTERTYPELIST;
								GenericService
										.serviceAction("GET", URI, data)
										.then(
												function(response) {
													$scope.encounterTypeList = [];

													if (response.data.status == "success") {
														$scope.encounterTypeList = response.data.listObject;
														console
																.log($scope.encounterTypeList);

													}
												});*/

							}

							$scope.init();
							
							//For Paginations Start
							$scope.setNoOfRecords = function() {
								$scope.initEncounterTypeMasterList($scope.organizationId, $scope.offset, $scope.noOfRecordsPerPage);
							};
							$scope.initEncounterTypeMasterList = function(orgId, offset, noOfRecordsPerPage)
							{
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage : 10;
								var URI = BASE_URL + ROOT_URL + GETENCOUNTERTYPELIST;
								var URI1 = BASE_URL + ROOT_URL + COUNTENCOUNTERTYPEMASTER;
								
								var data = {
										organizationId:orgId,
										offset : offset,
										noOfRecordsPerPage : noOfRecordsPerPage
								}
								
								var data1 = {
										organizationId:$scope.organizationId
								}
								
								//For Get Encounter Type List Using Offset
								GenericService
								.serviceAction("POST", URI, data)
								.then(
										function(response) {
											$scope.encounterTypeList = [];

											if (response.data.status == "success") {
												$scope.encounterTypeList = response.data.listObject;

											}
										});
								
								//For Count Encounter Type List
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

							$scope.getEncounterTypeMasterList = function(orgId, offset, noOfRecordsPerPage) {
								console.log("offset", offset);
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage : 10;
								var URI = BASE_URL + ROOT_URL + GETENCOUNTERTYPELIST;
								var data = {
										organizationId:$scope.organizationId,
										offset : offset,
										noOfRecordsPerPage : noOfRecordsPerPage
								}
								
								console.log("URI", URI);
								//For Get Encounter Type List Using Offset
								GenericService
								.serviceAction("POST", URI, data)
								.then(
										function(response) {
											$scope.encounterTypeList = [];

											if (response.data.status == "success") {
												$scope.encounterTypeList = response.data.listObject;

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
									$scope.getEncounterTypeMasterList($scope.organizationId, $scope.pager.startIndex, $scope.pager.pageSize);
								}
							}

							$scope.initEncounterTypeMasterList($scope.organizationId, $scope.offset, $scope.noOfRecordsPerPage);
							
							//For Paginations End
							

							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.saveEncounterType();
							}

							//Function for save Encounter Type
							$scope.saveEncounterType = function() {

								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								$scope.$broadcast('show-errors-check-validity');

								var data = {
									encounterTypeCode : $scope.encountertype.encounterCode,
									encounterTypeName : $scope.encountertype.encounterName,
									status : 'A',
									createdBy : $scope.userId,
									updatedBy : $scope.userId,
									organizationId : $scope.organizationId,
									createdDate : $scope.currentDate,
									updatedDate : $scope.currentDate
								}
								if ($scope.encountertypeform.$valid) {
									if ($scope.popUpFlag) {
										$scope.showModal = !$scope.showModal;

									} else {

										console.log(data);

										var URI = BASE_URL + ROOT_URL
												+ ADDENCOUNTERTYPE;
										GenericService
												.serviceAction("POST", URI,
														data)
												.then(
														function(response) {

															if (response.data.status == "success") {
																growl
																		.success(
																				'Encounter type added sucessfully!!!.',
																				{
																					title : 'Success!'
																				});
																$scope
																		.$broadcast('show-errors-reset');
																$scope.init();
															} else {
																growl
																		.error(
																				'Encounter type already exits!!!!.',
																				{
																					title : 'Error!'
																				});
															}
															$scope.initEncounterTypeMasterList($scope.organizationId, $scope.offset, $scope.noOfRecordsPerPage);

														});
									}
								}

							}
							
							//Function for get single Encounter Type
							$scope.showUpdateBtn = function(id) {

								$scope.saveBtnFlag = true;
								$scope.updateBtnFlag = false;

								var data = {

									encounterTypeId : id
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ GETSINGLEENCOUNTERTYPE;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													// $scope.speciality =[];
													if (response.data.status == "success") {

														$scope.encountertype.encounterTypeId = response.data.listObject[0].encounterTypeId;
														$scope.encountertype.encounterCode = response.data.listObject[0].encounterTypeCode;
														$scope.encountertype.encounterName = response.data.listObject[0].encounterTypeName;

													} else {
														alert("Error!!");
													}
												});

							};
						
							//Function for update Encounter Type status
							$scope.updateStatus = function(id, type) {

								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								type = (type == "A") ? "A" : "I";

								var data = {
									encounterTypeId : id,
									status : type,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ UPDATEENCOUNTERTYPESTATUS;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {

													if (response.data.status == "success") {
														growl
																.success(
																		'Encounter type status changed sucessfully!!!.',
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
							
							//Function for update Encounter Type
							$scope.updateEncounterType = function() {

								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								$scope.$broadcast('show-errors-check-validity');
								var data = {
									encounterTypeCode : $scope.encountertype.encounterCode,
									encounterTypeName : $scope.encountertype.encounterName,
									encounterTypeId : $scope.encountertype.encounterTypeId,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate
								}
								if ($scope.encountertypeform.$valid) {
									console.log(data);
									var URI = BASE_URL + ROOT_URL
											+ UPDATEENCOUNTERTYPE;
									GenericService
											.serviceAction("POST", URI, data)
											.then(
													function(response) {

														if (response.data.status == "success") {
															growl
																	.success(
																			'Encounter type updated sucessfully!!!.',
																			{
																				title : 'Success!'
																			});
															$scope
																	.$broadcast('show-errors-reset');
															$scope.init();
														} else {
															growl
																	.error(
																			'Encounter type already exits!!!!.',
																			{
																				title : 'Error!'
																			});
														}
														$scope.initEncounterTypeMasterList($scope.organizationId, $scope.offset, $scope.noOfRecordsPerPage);

													});
								}
							}

						} ]);
