'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:patientListController
 * @description #patientListController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'patientListController',
				[
						'$scope',
						'$http',
						'$localStorage',
						'$sessionStorage',
						'$cookies',
						'$rootScope',
						'GenericService',
						'BillingGenericService',
						'growl',
						'$state',
						'$stateParams',
						function($scope, $http, $localStorage, $sessionStorage,
								$cookies, $rootScope, GenericService,
								BillingGenericService, growl, $state,
								$stateParams) {

							/* init() function for form object create. */
							$scope.init = function() {

								var cookieObject = $cookies
										.getObject('cookieObject');
								if (cookieObject == undefined) {
									$state.go('login');
									return;
								}
								$scope.unitId = cookieObject.unitId;
								$scope.organizationId = cookieObject.organizationId;
								$scope.userId = 1;

								$rootScope.loginpage = true;
								$scope.saveBtnFlag = false;
								$scope.updateBtnFlag = true;
								
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
								// end for popup
								
								
								$scope.patientObj = {

									prefixId : '',
									firstName : '',
									middleName : '',
									lastName : '',
									genderId : '',
									countryCallingCode : '',
									email : '',
									nationalityId : '',
									raceId : '',
									maritalStatusId : '',
									mobileNumber : '',
									dob : '',
									identificationTypeId : '',
									identificationNumber : '',
									isVip : '',
									address : '',
									countryId : '',
									stateId : '',
									districtId : '',
									cityId : '',
									areaId : '',
									zipCode : '',
									phoneCode : '',
									phoneNumber : '',
									companyName : '',
									companyAddress : '',
									companyCountryId : '',
									companyStateId : '',
									companyDistrictId : '',
									companyCityId : '',
									companyAreaId : '',
									companyZipCode : '',
									companyMobileNumber : '',
									organizationId : $scope.organizationId,
									unitId : $scope.unitId,
									updatedBy : $scope.userId,
									createdBy : $scope.userId,
									status : 'A',
									patientCategoryId:'1'
								};

								var commonObj = {
									unitId : $scope.unitId,
									organizationId : $scope.organizationId
								};

								var data = {};

								var URI = BASE_URL + ROOT_URL
										+ GETPATIENTLIST;								
								BillingGenericService
								.serviceAction(METHOD_POST, URI, commonObj,
										NOTIFICATION_MSG_STATUS_FALSE)
								.then(
										function(response) {
											$scope.patientList = [];
											if (response.data.status == "success")
												$scope.patientList = response.data.listObject;
										});
								
							}

							$scope.init();
							
							
							/*// For Paginations Start
							$scope.setNoOfRecords = function() {
								$scope.initPatientList(
										$scope.organizationId, $scope.offset,
										$scope.noOfRecordsPerPage);
							};
							$scope.initPatientList = function(orgId,
									offset, noOfRecordsPerPage) {
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
										: 10;
								var URI = BASE_URL + ROOT_URL
										+ GETNATIONALITYLIST;
								var URI1 = BASE_URL + ROOT_URL
										+ COUNTNATIONALITYMASTER;

								var data = {
									organizationId : orgId,
									offset : offset,
									noOfRecordsPerPage : noOfRecordsPerPage
								}

								var data1 = {
									organizationId : $scope.organizationId
								}

								// For Get Nationality List Using Offset
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.nationalityList = [];

													if (response.data.status == "success") {
														$scope.nationalityList = response.data.listObject;

													}
												});

								// For Count Nationality List
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
							
							$scope.getPatientList = function(orgId,
									offset, noOfRecordsPerPage) {
								console.log("offset", offset);
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
										: 10;
								var URI = BASE_URL + ROOT_URL
										+ GETNATIONALITYLIST;
								var data = {
									organizationId : $scope.organizationId,
									offset : offset,
									noOfRecordsPerPage : noOfRecordsPerPage
								}

								console.log("URI", URI);
								// For Get Nationality List Using Offset
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.nationalityList = [];

													if (response.data.status == "success") {
														$scope.nationalityList = response.data.listObject;

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
									$scope.getPatientList(
											$scope.organizationId,
											$scope.pager.startIndex,
											$scope.pager.pageSize);
								}
							}

							$scope.initPatientList(
									$scope.organizationId, $scope.offset,
									$scope.noOfRecordsPerPage);*/
							


							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.savePatientRegistration();
							}
						
						} ]);