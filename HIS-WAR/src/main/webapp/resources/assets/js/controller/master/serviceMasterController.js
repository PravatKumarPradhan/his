'use strict';

/**
 * @Author By Vivek Satle
 * @name myApp.controller:serviceMasterController
 * @description #serviceMasterController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'serviceMasterController',
				[
						'$scope',
						'$http',
						'$localStorage',
						'$sessionStorage',
						'$cookies',
						'$rootScope',
						'GenericService',
						'growl',
						'PagerService',
						function($scope, $http, $localStorage, $sessionStorage,
								$cookies, $rootScope, GenericService, growl,PagerService) {

							$scope.specialityMasterList = [];
							$scope.subSpecialityMasterList = [];

							// for datePicker
							$scope.dateOptions = {
								formatYear : 'yyyy',
								showWeeks : false
							};
							$scope.openDatePicker = function() {
								$scope.datepicker.opened = true;
							};

							$scope.datepicker = {
								opened : false
							};

							// for datePicker2
							$scope.dateOptions = {
								formatYear : 'yyyy',
								showWeeks : false
							};
							$scope.openDatePickerPdd = function() {
								$scope.datepickerPdd.opened = true;
							};

							$scope.datepickerPdd = {
								opened : false
							};
							// init function description
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
								$scope.masterObj = {}
								$scope.saveBtnFlag = true;
								$scope.updateBtnFlag = false;

								// for popup
								$scope.showModal = false;
								$scope.buttonClicked = "";
								$scope.popUpFlag = true;
								// end for popup

								// ------------ Get Speciality Master List
								// ------------//

								try {
									var URI = BASE_URL + ROOT_URL
											+ GETACTIVESPECIALITY;
									var obj = {
										organizationId : $scope.organizationId,
										unitId : $scope.unitId
									}
									GenericService
											.serviceAction("POST", URI, obj)
											.then(
													function(response) {
														if (response.data.status == "success")
															$scope.specialityMasterList = response.data.listObject;
														console
																.log(
																		"specialityMasterList",
																		$scope.specialityMasterList);
													});
								} catch (e) {
									console.log("Exception", e.message);
								}

								// to get general ledger list
								var URI = BASE_URL + ROOT_URL
										+ GETGENERALLEDGERMASTERACTIVELIST
										+ "/" + $scope.organizationId;
								var data = {

								}
								GenericService
										.serviceAction("GET", URI, data)
										.then(
												function(response) {
													$scope.ledgerList = [];

													if (response.data.status == "success") {
														$rootScope.stopLoader();
														$scope.ledgerList = response.data.listObject;
														console
																.log(
																		"ledgerList",
																		$scope.ledgerList);
													}
												});
								/*// getting service list
								var URI = BASE_URL + ROOT_URL
										+ GET_ORGANIZATION_SERVICE_MASTER_LIST
										+ "/" + $scope.organizationId + "/0/50";
								var data = {

								}
								GenericService
										.serviceAction("GET", URI, data)
										.then(
												function(response) {
													$scope.serviceList = [];

													if (response.data.status == "success") {
														$rootScope.stopLoader();
														$scope.serviceList = response.data.listObject;
														console
																.log(
																		"serviceList",
																		$scope.serviceList);
													}
												});*/
							};
							$scope.init();
							
							// For Paginations Start
							$scope.setNoOfRecords = function() {
								$scope.initServiceList(
										$scope.organizationId, $scope.offset,
										$scope.noOfRecordsPerPage);
							};
							$scope.initServiceList = function(
									orgId, offset, noOfRecordsPerPage) {
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
										: 10;
								var URI = BASE_URL + ROOT_URL
								+ GET_ORGANIZATION_SERVICE_MASTER_LIST
								+ "/" + $scope.organizationId + "/"+offset+"/"+noOfRecordsPerPage;
								var URI1 = BASE_URL + ROOT_URL
										+ GET_ORGANIZATION_SERVICE_MASTER_COUNT+"/"+$scope.organizationId;

								var data = {
									
								}

								var data1 = {
									
								}

								// For Get Employee Category List Using Offset
								GenericService
										.serviceAction("GET", URI, data)
										.then(
												function(response) {
													$scope.serviceList = [];

													if (response.data.status == "success") {
														$scope.serviceLists = response.data.listObject;
														console.log("serviceList",$scope.serviceList);

													}
												});

								// For Count service List
								GenericService
										.serviceAction("GET", URI1, data1)
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

							$scope.getServiceList = function(orgId,
									offset, noOfRecordsPerPage) {
								console.log("offset", offset);
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
										: 10;
								var URI = BASE_URL + ROOT_URL
								+ GET_ORGANIZATION_SERVICE_MASTER_LIST
								+ "/" + $scope.organizationId + "/"+offset+"/"+noOfRecordsPerPage;
								var data = {
									
								}

								console.log("URI", URI);
								// For Get Country List Using Offset
								GenericService
										.serviceAction("GET", URI, data)
										.then(
												function(response) {
													$scope.serviceList = [];

													if (response.data.status == "success") {
														$scope.serviceList = response.data.listObject;

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
									$scope.getServiceList(
											$scope.organizationId,
											$scope.pager.startIndex,
											$scope.pager.pageSize);
								}
							}

							$scope.initServiceList(
									$scope.organizationId, $scope.offset,
									$scope.noOfRecordsPerPage);

							// For Paginations End
							// ------------ Get SubSpeciality By SpecialityId
							// ----------//
							$scope.getSubSpecialityBySpecialityId = function(
									specialityId) {
								if (specialityId != null) {
									console.log("chk", specialityId);
									var URI = BASE_URL
											+ ROOT_URL
											+ GET_SUBSPECIALITY_BY_SPECIALITY_ID;
									var obj = {
										organizationId : $scope.organizationId,
										unitId : $scope.unitId,
										specialityId : specialityId
									}
									GenericService
											.serviceAction("POST", URI, obj)
											.then(
													function(response) {
														if (response.data.status == "success")
															$scope.subSpecialityMasterList = response.data.listObject;
														console
																.log(
																		"subSpecialityMasterList",
																		$scope.subSpecialityMasterList);
													});
								} else {
									console.log("department not selected yet");
								}
							}
							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.saveServiceMaster();
							}
							// ------------ Save Organization Service Master
							// ---------------//
							$scope.saveServiceMaster = function() {
								try {
									$scope.currentDate = new Date();
									$scope.currentDate = moment(
											$scope.currentDate).format(
											'DD-MM-YYYY HH:mm:ss');
									var data = {
										specialityId : $scope.masterObj.specialityId,
										subSpecialityId : $scope.masterObj.subSpecialityId,
										serviceStandardName : $scope.masterObj.serviceName,
										codificationServiceName : $scope.masterObj.codificationServiceName,
										serviceStandardCode : $scope.masterObj.serviceCode,
										generalLedgerId : $scope.masterObj.generalLedgerId,
										validFrom : moment($scope.datepicker)
												.format('DD-MM-YYYY HH:mm:ss'),
										validTill : moment($scope.datepickerPdd)
												.format('DD-MM-YYYY HH:mm:ss'),
										status : 'A',
										createdBy : $scope.userId,
										updatedBy : $scope.userId,
										organizationId : $scope.organizationId,
										createdDate : $scope.currentDate,
										updatedDate : $scope.currentDate
									}
//									 console.log("masterObj",data); return
									// false;
									var URI = BASE_URL + ROOT_URL + BILLING
											+ SAVE_ORGANIZATION_SERVICE_MASTER;
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
														.initServiceList(
																$scope.organizationId,
																$scope.offset,
																$scope.noOfRecordsPerPage);
													});
								} catch (e) {
									console.log("Exception", e.message);
								}
							}
							// Function For get single Service()
							$scope.showUpdateBtn = function(id) {
								$scope.saveBtnFlag = false;
								$scope.updateBtnFlag = true;

								var data = {
										serviceMasterId:id,
										unitId:$scope.unitId,
										organizationId:$scope.organizationId
								}
								var URI = BASE_URL + ROOT_URL
										+"/billing"+ GET_ORGANIZATION_SERVICE_MASTER_BY_ID;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
//													console.log("BYId",response);
													// $scope.speciality =[];
													if (response.data.status == "success") {
														console.log("object",response.data.object);
														$scope.masterObj.serviceMasterId=response.data.object.serviceMasterId;
														$scope.masterObj.specialityId=response.data.object.specialityId.toString();
														 $scope.masterObj.subSpecialityId = response.data.object.subSpecialityId.toString();
														 $scope.masterObj.serviceName = response.data.object.serviceStandardName;
														 $scope.masterObj.codificationServiceName = response.data.object.codificationServiceName;
														 $scope.masterObj.serviceCode=response.data.object.serviceStandardCode;
														 $scope.masterObj.generalLedgerId=response.data.object.generalLedgerId.toString();
														 $scope.datepicker=new Date(response.data.object.validFrom);
														 $scope.datepickerPdd=new Date(response.data.object.validTill);
														 $scope.getSubSpecialityBySpecialityId($scope.masterObj.specialityId);
//														 alert($scope.masterObj.subSpecialityId);
													} 
													else {
														alert("Error!!");
													}
													
												});
//								 
							};

							// Function For update service status
							$scope.updateStatus = function(id, type) {
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								type = (type == "A") ? "A" : "I";

								var data = {
										serviceMasterId : id,
										organizationId : $scope.organizationId,
									status : type,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+"/billing"+ CHANGE_ORGANIZATION_SERVICE_MASTER_STATUS;
								GenericService
										.serviceAction("PUT", URI, data)
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

							// Function For updateServiceMaster()
							$scope.updateServiceMaster = function() {
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');
//								$scope.$broadcast('show-errors-check-validity');
								var data = {
										specialityId : $scope.masterObj.specialityId,
										subSpecialityId : $scope.masterObj.subSpecialityId,
										serviceStandardName : $scope.masterObj.serviceName,
										codificationServiceName : $scope.masterObj.codificationServiceName,
										serviceStandardCode : $scope.masterObj.serviceCode,
										generalLedgerId : $scope.masterObj.generalLedgerId,
										validFrom : moment($scope.datepicker)
												.format('DD-MM-YYYY HH:mm:ss'),
										validTill : moment($scope.datepickerPdd)
												.format('DD-MM-YYYY HH:mm:ss'),
										status : 'A',
										updatedBy : $scope.userId,
										organizationId : $scope.organizationId,
										updatedDate : $scope.currentDate,
										serviceMasterId:$scope.masterObj.serviceMasterId
								}
								
								
//								if ($scope.employeeCategoryForm.$valid) {
									console.log("update",data);  
									var URI = BASE_URL + ROOT_URL
											+ UPDATEGLOBALSERVICEMASTER;
									GenericService
											.serviceAction("PUT", URI, data)
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
																.initServiceList(
																		$scope.organizationId,
																		$scope.offset,
																		$scope.noOfRecordsPerPage);

													});
								}

//							}

						} ]);
