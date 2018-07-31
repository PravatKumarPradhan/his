'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:referralMasterController
 * @description # referralMasterController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'referralMasterController',
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
								
								/**Variables for pagination */
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

								$scope.referral = {}

								$scope.saveBtnFlag = false;
								$scope.updateBtnFlag = true;

								var data = {};
								// $scope.status = "NO";
								/*
								 * var URI = BASE_URL + ROOT_URL +
								 * GETREFERRALLIST; GenericService
								 * .serviceAction("GET", URI, data) .then(
								 * function(response) { $scope.referralList =
								 * [];
								 * 
								 * if (response.data.status == "success") {
								 * $scope.referralList =
								 * response.data.listObject; } });
								 */
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
																.log($scope.countryList);

													}
												});
								var URI = BASE_URL + ROOT_URL
										+ GETACTIVEREFERRALTYPELIST;
								var obj={organizationId : $scope.organizationId};
								GenericService
										.serviceAction("POST", URI, obj)
										.then(
												function(response) {
													$scope.referralTypeList = [];

													if (response.data.status == "success") {
														$scope.referralTypeList = response.data.listObject;
														console
																.log($scope.referralTypeList);

													}
												});

							};
							$scope.init();
							
							//For Paginations Start
							$scope.setNoOfRecords = function() {
								$scope.initReferralMasterList($scope.organizationId, $scope.offset, $scope.noOfRecordsPerPage);
							};
							$scope.initReferralMasterList = function(orgId, offset, noOfRecordsPerPage)
							{
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage : 10;
								var URI = BASE_URL + ROOT_URL + GETREFERRALLIST;
								var URI1 = BASE_URL + ROOT_URL + COUNTREFERRALMASTER;
								
								var data = {
										organizationId:orgId,
										offset : offset,
										noOfRecordsPerPage : noOfRecordsPerPage
								}
								
								var data1 = {
										organizationId:$scope.organizationId
								}
								
								//For Get Referral List Using Offset
								GenericService
								.serviceAction("POST", URI, data)
								.then(
										function(response) {
											$scope.referralList = [];

											if (response.data.status == "success") {
												$scope.referralList = response.data.listObject;

											}
										});
								
								//For Count Referral List
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

							$scope.getReferralMasterList = function(orgId, offset, noOfRecordsPerPage) {
								console.log("offset", offset);
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage : 10;
								var URI = BASE_URL + ROOT_URL + GETREFERRALLIST;
								var data = {
										organizationId:$scope.organizationId,
										offset : offset,
										noOfRecordsPerPage : noOfRecordsPerPage
								}
								
								console.log("URI", URI);
								//For Get Referral List Using Offset
								GenericService
								.serviceAction("POST", URI, data)
								.then(
										function(response) {
											$scope.referralList = [];

											if (response.data.status == "success") {
												$scope.referralList = response.data.listObject;

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
									$scope.getReferralMasterList($scope.organizationId, $scope.pager.startIndex, $scope.pager.pageSize);
								}
							}

							$scope.initReferralMasterList($scope.organizationId, $scope.offset, $scope.noOfRecordsPerPage);
							
							//For Paginations End
							

							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.saveReferral();
							}

							// for get state by country ID
							$scope.getStateByCountry = function(id) {
								var data = {
									countryId : id
								};
								var URI = BASE_URL + ROOT_URL
										+ GETSTATELISTBYCOUNTRYID;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.stateList = [];

													if (response.data.status == "success") {
														$scope.stateList = response.data.listObject;
														console
																.log($scope.stateList);

													}
												});

							}

							$scope.getDistrictByState = function(id) {
								var data = {
									stateId : id
								};
								var URI = BASE_URL + ROOT_URL
										+ GETDISTRICTLISTBYSTATEID;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.districtList = [];

													if (response.data.status == "success") {
														$scope.districtList = response.data.listObject;
														console
																.log($scope.districtList);

													}
												});

							}

							$scope.getCityByDistrict = function(id) {

								var data = {
									districtId : id
								};
								var URI = BASE_URL + ROOT_URL
										+ GETCITYLISTBYDISTRICTID;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.cityList = [];

													if (response.data.status == "success") {
														$scope.cityList = response.data.listObject;
														console
																.log($scope.cityList);

													}
												});

							}

							$scope.getAreaByCity = function(id) {

								var data = {
									cityId : id
								};
								var URI = BASE_URL + ROOT_URL
										+ GETAREALISTBYCITY;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.areaList = [];

													if (response.data.status == "success") {
														$scope.areaList = response.data.listObject;
														console
																.log($scope.areaList);

													}
												});

							}

							// Save function for add Referral
							$scope.saveReferral = function() {

								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								$scope.$broadcast('show-errors-check-validity');
								if ($scope.referralform.$valid) {
									if ($scope.popUpFlag) {
										$scope.showModal = !$scope.showModal;

									} else {

										var data = {
											id : $scope.referral.referralTypeId,
											referralName : $scope.referral.referralName,
											referralCode : "Code",
											referralAddress : $scope.referral.address,
											referralContactPerson : $scope.referral.contactPerson,
											referralEmail : $scope.referral.email,
											referralContact : $scope.referral.contact,
											countryId : $scope.referral.countryId,
											stateId : $scope.referral.stateId,
											districtId : $scope.referral.districtId,
											cityId : $scope.referral.cityId,
											areaId : $scope.referral.areaId,
											status : 'A',
											createdBy : $scope.userId,
											updatedBy : $scope.userId,
											organizationId : $scope.organizationId,
											updatedDate : $scope.currentDate,
											createdDate : $scope.currentDate
										}

										console.log(data);
										var URI = BASE_URL + ROOT_URL
												+ ADDREFERRAL;
										GenericService
												.serviceAction("POST", URI,
														data)
												.then(
														function(response) {

															if (response.data.status == "success") {
																growl
																		.success(
																				'Referral added sucessfully!!!.',
																				{
																					title : 'Success!'
																				});

																$scope
																		.$broadcast('show-errors-reset');
																$scope.init();
															} else {
																growl
																		.error(
																				'Referral already exits!!!!.',
																				{
																					title : 'Error!'
																				});
															}
															$scope.initReferralMasterList($scope.organizationId, $scope.offset, $scope.noOfRecordsPerPage);
														});
									}
								}

							};

							// function for get Referral  object for update
							$scope.showUpdateBtn = function(id) {

								$scope.saveBtnFlag = true;
								$scope.updateBtnFlag = false;

								var data = {
									referralId : id
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ GETSINGLEREFERRAL;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													if (response.data.status == "success") {
														$scope.referral.referralId = response.data.listObject[0].referralId;
														$scope.referral.referralTypeId = response.data.listObject[0].id
																.toString();
														$scope.referral.referralName = response.data.listObject[0].referralName;
														$scope.referral.address = response.data.listObject[0].referralAddress;
														$scope.referral.email = response.data.listObject[0].referralEmail;
														$scope.referral.contact = response.data.listObject[0].referralContact;
														$scope.referral.contactPerson = response.data.listObject[0].referralContactPerson;
														$scope.referral.countryId = response.data.listObject[0].countryId
																.toString();
														$scope.referral.stateId = response.data.listObject[0].stateId
																.toString();
														$scope.referral.districtId = response.data.listObject[0].districtId
																.toString();
														$scope.referral.areaId = response.data.listObject[0].areaId
																.toString();
														$scope.referral.cityId = response.data.listObject[0].cityId
																.toString();
														$scope
																.getStateByCountry($scope.referral.countryId);
														$scope
																.getDistrictByState($scope.referral.stateId);
														$scope
																.getCityByDistrict($scope.referral.districtId);
														$scope
																.getAreaByCity($scope.referral.cityId);

													} else {
														alert("Error!!");
													}
												});

							};

							// function for update status Referral
							$scope.updateStatus = function(id, type) {

								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								type = (type == "A") ? "A" : "I";
								var data = {
									referralId : id,
									status : type,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ UPDATEREFERRALSTATUS;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {

													if (response.data.status == "success") {
														growl
																.success(
																		'Referral status changed sucessfully!!!.',
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

							// function for update Referral
							$scope.updateReferral = function() {
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								$scope.$broadcast('show-errors-check-validity');
								if ($scope.referralform.$valid) {
									var data = {
										referralId : $scope.referral.referralId,
										id : $scope.referral.referralTypeId,
										referralName : $scope.referral.referralName,
										referralCode : "Code",
										referralAddress : $scope.referral.address,
										referralContactPerson : $scope.referral.contactPerson,
										referralEmail : $scope.referral.email,
										referralContact : $scope.referral.contact,
										countryId : $scope.referral.countryId,
										stateId : $scope.referral.stateId,
										districtId : $scope.referral.districtId,
										cityId : $scope.referral.cityId,
										areaId : $scope.referral.areaId,
										updatedBy : $scope.userId,
										updatedDate : $scope.currentDate
									}
									console.log(data);
									var URI = BASE_URL + ROOT_URL
											+ UPDATEREFERRAL;
									GenericService
											.serviceAction("POST", URI, data)
											.then(
													function(response) {

														if (response.data.status == "success") {
															growl
																	.success(
																			'Referral updated sucessfully!!!.',
																			{
																				title : 'Success!'
																			});
															$scope
																	.$broadcast('show-errors-reset');
															$scope.init();
															// alert("ds");

														} else {
															growl
																	.error(
																			'Referral already exits!!!!.',
																			{
																				title : 'Error!'
																			});
														}
														$scope.initReferralMasterList($scope.organizationId, $scope.offset, $scope.noOfRecordsPerPage);
													});
								}
							}

							$scope.PopUpDetails = function(info) {
								console.log("AddressDetails",info);
								$scope.address11 = info.referralAddress;
								$scope.countryName = info.countryName;
								$scope.stateName = info.stateName;
								$scope.districtName = info.districtName;
								$scope.cityName = info.cityName;
								$scope.areaName = info.areaName;
								$scope.postCode = info.postCode;
							}
							/*
							 * $rootScope.showpopup = function(info,filename,
							 * controllername) {
							 * AuthenticationService.showpopup(filename,
							 * controllername);
							 *  }; $rootScope.popupClose = function() {
							 * AuthenticationService.popupClose();
							 *  };
							 */

						} ]);
