'use strict';


/**
 * @Author By  Vivek Satle
 * @name myApp.controller:consentMasterController
 * @description # consentMasterController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'consentMasterController',
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

								var cookieObject = $cookies.getObject('cookieObject');
								if(cookieObject == undefined){
									$state.go('login');
									return;
								}
								$scope.unitId = cookieObject.unitId;
								$scope.organizationId = cookieObject.organizationId;
								$scope.userId = 1;
								
								/**Variables for pagination */
								$scope.offset = 0;
								$scope.noOfRecordsPerPage = 10;
								$scope.totalNoOfRecords;
								$scope.searchKeyword;
								$scope.commonListCount;

								$rootScope.loginpage = true;
								$scope.consent = {}
								$scope.saveBtnFlag = false;
								$scope.updateBtnFlag = true;

								// for popup
								$scope.showModal = false;
								$scope.buttonClicked = "";
								$scope.popUpFlag = true;
								// end for popup

								var data = {};
								// $scope.status = "NO";
								/*var URI = BASE_URL + ROOT_URL + GETCONSENTLIST;
								GenericService
										.serviceAction("GET", URI, data)
										.then(
												function(response) {
													$scope.consentList = [];

													if (response.data.status == "success") {
														$scope.consentList = response.data.listObject;
														console
																.log($scope.consentList);

													}
												});*/
								var URI = BASE_URL + ROOT_URL
										+ GETACTIVECONSENTTYPELIST;
								GenericService
										.serviceAction("GET", URI, data)
										.then(
												function(response) {
													$scope.consentTypeList = [];

													if (response.data.status == "success") {
														$scope.consentTypeList = response.data.listObject;
														console
																.log($scope.consentTypeList);

													}
												});

							}

							$scope.init();
							
							//For Paginations Start
							$scope.setNoOfRecords = function() {
								$scope.initConsentMasterList($scope.organizationId, $scope.offset, $scope.noOfRecordsPerPage);
							};
							$scope.initConsentMasterList = function(orgId, offset, noOfRecordsPerPage)
							{
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage : 10;
								var URI = BASE_URL + ROOT_URL + GETCONSENTLIST;
								var URI1 = BASE_URL + ROOT_URL
										+ COUNTCONSENTMASTER;
								
								var data = {
										organizationId:orgId,
										offset : offset,
										noOfRecordsPerPage : noOfRecordsPerPage
								}
								
								var data1 = {
										organizationId:$scope.organizationId
								}
								
								//For Get Consent List Using Offset
								GenericService
								.serviceAction("POST", URI, data)
								.then(
										function(response) {
											$scope.consentList = [];

											if (response.data.status == "success") {
												$scope.consentList = response.data.listObject;

											}
										});
								
								//For Count Consent List
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

							$scope.getConsentMasterList = function(orgId, offset, noOfRecordsPerPage) {
								console.log("offset", offset);
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage : 10;
								var URI = BASE_URL + ROOT_URL + GETCONSENTLIST;
								var data = {
										organizationId:$scope.organizationId,
										offset : offset,
										noOfRecordsPerPage : noOfRecordsPerPage
								}
								
								console.log("URI", URI);
								//For Get Consent List Using Offset
								GenericService
								.serviceAction("POST", URI, data)
								.then(
										function(response) {
											$scope.consentList = [];

											if (response.data.status == "success") {
												$scope.consentList = response.data.listObject;

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
									$scope.getConsentMasterList($scope.organizationId, $scope.pager.startIndex, $scope.pager.pageSize);
								}
							}

							$scope.initConsentMasterList($scope.organizationId, $scope.offset, $scope.noOfRecordsPerPage);
							
							//For Paginations End
							

							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.saveConsent();
							}

							//Function for save consent
							$scope.saveConsent = function() {

								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								$scope.$broadcast('show-errors-check-validity');

								var data = {
									consentMasterCode : $scope.consent.consentCode,
									consentMasterName : $scope.consent.consentName,
									consentTypeMasterId : $scope.consent.consentTypeId,
									status : 'A',
									createdBy : $scope.userId,
									updatedBy : $scope.userId,
									organizationId : $scope.organizationId,
									createdDate : $scope.currentDate,
									updatedDate : $scope.currentDate
								}
								if ($scope.consentform.$valid) {
									if ($scope.popUpFlag) {
										$scope.showModal = !$scope.showModal;

									} else {

										console.log(data);

										var URI = BASE_URL + ROOT_URL
												+ ADDCONSENT;
										GenericService
												.serviceAction("POST", URI,
														data)
												.then(
														function(response) {

															if (response.data.status == "success") {
																growl
																		.success(
																				'Consent added sucessfully!!!.',
																				{
																					title : 'Success!'
																				});
																$scope
																		.$broadcast('show-errors-reset');
																$scope.init();
															} else {
																growl
																		.error(
																				'Consent already exits!!!!.',
																				{
																					title : 'Error!'
																				});
															}
															$scope.initConsentMasterList($scope.organizationId, $scope.offset, $scope.noOfRecordsPerPage);

														});
									}
								}

							}
							
							//Function for get single consent
							$scope.showUpdateBtn = function(id) {

								$scope.saveBtnFlag = true;
								$scope.updateBtnFlag = false;

								var data = {

									consentMasterId : id
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ GETSINGLECONSENT;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													// $scope.speciality =[];
													if (response.data.status == "success") {

														$scope.consent.consentId = response.data.listObject[0].consentMasterId;
														$scope.consent.consentCode = response.data.listObject[0].consentMasterCode;
														$scope.consent.consentName = response.data.listObject[0].consentMasterName;
														$scope.consent.consentTypeId = response.data.listObject[0].consentTypeMasterId
																.toString();

													} else {
														alert("Error!!");
													}
												});

							};
							
							//Function for get single consent
							$scope.updateStatus = function(id, type) {

								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								type = (type == "A") ? "A" : "I";

								var data = {
									consentMasterId : id,
									status : type,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ UPDATECONSENTSTATUS;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {

													if (response.data.status == "success") {
														growl
																.success(
																		'Consent status changed sucessfully!!!.',
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
							
							//Function for update consent
							$scope.updateConsent = function() {

								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								$scope.$broadcast('show-errors-check-validity');
								var data = {
									consentMasterCode : $scope.consent.consentCode,
									consentMasterName : $scope.consent.consentName,
									consentMasterId : $scope.consent.consentId,
									consentTypeMasterId : $scope.consent.consentTypeId,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate
								}
								if ($scope.consentform.$valid) {
									console.log(data);
									var URI = BASE_URL + ROOT_URL
											+ UPDATECONSENT;
									GenericService
											.serviceAction("POST", URI, data)
											.then(
													function(response) {

														if (response.data.status == "success") {
															growl
																	.success(
																			'Consent updated sucessfully!!!.',
																			{
																				title : 'Success!'
																			});
															$scope
																	.$broadcast('show-errors-reset');
															$scope.init();
														} else {
															growl
																	.error(
																			'Consent already exits!!!!.',
																			{
																				title : 'Error!'
																			});
														}
														$scope.initConsentMasterList($scope.organizationId, $scope.offset, $scope.noOfRecordsPerPage);

													});
								}
							}

						} ]);
