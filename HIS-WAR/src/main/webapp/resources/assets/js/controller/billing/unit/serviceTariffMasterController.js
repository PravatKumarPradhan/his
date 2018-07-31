'use strict';

/**
 * @Author By Dinesh Jagatap
 * @name myApp.controller:tariffMasterController
 * @description #tariffMasterController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'serviceTariffMasterController',
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
						'PagerService',
						function($scope, $http, $localStorage, $sessionStorage,
								$cookies, $rootScope, GenericService,
								BillingGenericService, growl, $state,
								PagerService) {

							// for datePicker
							$scope.dateOptions = {
								formatYear : 'yyyy',
								showWeeks : false
							};
							$scope.openDatePicker = function() {
								// alert("in");
								$scope.datepicker.opened = true;
							};

							$scope.datepicker = {
								opened : false
							};

							$scope.openDatePickerForNewDOA = function() {

								$scope.datepickerForNewDOA.opened = true;
							};

							$scope.datepickerForNewDOA = {
								opened : false
							};
							$scope.effectiveDateObj = {
								effectiveDate : ""
							}
							/* init() function for form object create. */

							$scope.init = function() {

								$scope.unitId = 1;
								$scope.organizationId = 1;
								$scope.userId = 1;
								if ($scope.userId == null) {
									$rootScope.loginpage = false;
									$state.go('login');
								}

								$rootScope.loginpage = true;
								$scope.country = {}
								$scope.saveBtnFlag = true;
								$scope.updateBtnFlag = false;

								// for popup
								$scope.showModal = false;
								$scope.buttonClicked = "";
								$scope.popUpFlag = true;
								// end for popup

								$scope.effectiveDate = '';
								var data = {};

								// multi select for visitType master
								$scope.visitTypeModel = [];
								$scope.visitTypeData = [ {
									id : 1,
									label : "OP"
								}, {
									id : 2,
									label : "IP"
								}, {
									id : 3,
									label : "Day Care"
								}, {
									id : 4,
									label : "ER"
								} ];
								$scope.visitTypeModelsettings = {
									scrollableHeight : '200px',
									scrollable : true,
									enableSearch : true
								};
								// multi select for tariff master
								$scope.tariffModel = [];
								$scope.tariffModelsettings = {
									scrollableHeight : '200px',
									scrollable : true,
									enableSearch : true
								};

								// multi select for paymentEntitlement
								$scope.paymentEntitlementModel = [];
								$scope.paymentEntitlementModelsettings = {
									scrollableHeight : '200px',
									scrollable : true,
									enableSearch : true
								};
								// multi select for patient
								$scope.patientTypeModel = [];
								$scope.patientTypeModelsettings = {
									scrollableHeight : '200px',
									scrollable : true,
									enableSearch : true
								};
								// multi select for speciality
								$scope.specialityModel = [];
								$scope.specialityModelsettings = {
									scrollableHeight : '200px',
									scrollable : true,
									enableSearch : true
								};
								// multi select for sub-speciality
								$scope.subSpecialityModel = [];
								$scope.subSpecialitysettings = {
									scrollableHeight : '200px',
									scrollable : true,
									enableSearch : true
								};
								// multi select for bed category
								$scope.bedTypeCategoryModel = [];
								$scope.bedTypeCategorySettings = {
									scrollableHeight : '200px',
									scrollable : true,
									enableSearch : true
								};
								// multi select for service
								$scope.serviceModel = [];
								$scope.serviceSettings = {
									scrollableHeight : '200px',
									scrollable : true,
									enableSearch : true
								};

								var data = {
									organizationId : $scope.organizationId,
									unitId : $scope.unitId
								}
								// for getting active tariff master
								var URI = BASE_URL + ROOT_URL
										+ GETACTIVETARIFFMASTERLIST;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													if (response.data.status == "success") {
														$scope.tariffData = [];
														angular
																.forEach(
																		response.data.listObject,
																		function(
																				value,
																				index) {
																			var data = {};
																			data.id = value.tariffId;
																			data.label = value.tariffDesc;
																			$scope.tariffData
																					.push(data);
																		});
														console
																.log(
																		"ActiveTariffMasterList",
																		$scope.tariffData);
													}
												});

								// for getting active entitlement

								/*
								 * var URI = BASE_URL + ROOT_URL +
								 * GETACTIVEPAYMENTENTITLEMENT; GenericService
								 * .serviceAction("GET", URI, data) .then(
								 * function(response) {
								 * $scope.paymentEntitlementList = []; if
								 * (response.data.status == "success") {
								 * $scope.example14data = [];
								 * angular.forEach(response.data.listObject,
								 * function(value,index) { var data = {};
								 * data.id = value.paymentEntitlementId;
								 * data.label = value.paymentEntitlementDesc;
								 * $scope.example14data.push(data); }); } });
								 */

								// For Speciality under modality
								$scope.specialityModel = [];
								$scope.subSpecialityModel = [];

								var commonObj = {
									unitId : $scope.unitId,
									organizationId : $scope.organizationId
								};
								var URI = BASE_URL + ROOT_URL
										+ GETACTIVESPECIALITY;
								BillingGenericService
										.serviceAction(METHOD_POST, URI,
												commonObj,
												NOTIFICATION_MSG_STATUS_FALSE)
										.then(
												function(response) {
													console.log(response);
													if (response.data.status == "success") {
														$scope.specialityModelData = [];
														angular
																.forEach(
																		response.data.listObject,
																		function(
																				value,
																				index) {
																			var data = {};
																			data.id = value.specialityId;
																			data.label = value.specialityName;
																			$scope.specialityModelData
																					.push(data);

																		});

													}
													$scope
															.getSpecialityAndSubspeciality();
													// $scope.clicked();

												});
								// for getting active patient category
								var data = {
									organizationId : $scope.organizationId,
									unitId : $scope.unitId
								}
								var URI = BASE_URL + ROOT_URL
										+ GETACTIVEPATIENTCATEGORY;
								GenericService
										.serviceAction("GET", URI, data)
										.then(
												function(response) {
													if (response.data.status == "success") {
														$scope.patientTypeData = [];
														console
																.log(
																		"PatientCategory",
																		response.data.listObject);
														angular
																.forEach(
																		response.data.listObject,
																		function(
																				value,
																				index) {
																			var data = {};
																			data.id = value.patientCategoryId;
																			data.label = value.patientCategory;
																			$scope.patientTypeData
																					.push(data);
																		});
													}
												});
								// for getting active bed category
								var data = {
									organizationId : $scope.organizationId,
									unitId : $scope.unitId
								}
								var URI = BASE_URL + ROOT_URL
										+ "/adt/getActiveBillingBedCategoryList/orgId/"+$scope.organizationId+"/unitId/"+$scope.unitId;
								GenericService
										.serviceAction("GET", URI, data)
										.then(
												function(response) {
													if (response.data.status == "success") {
														$scope.bedTypeCategoryData = [];
														console
																.log(
																		"BillingBedCategory",
																		response.data.listObject);
														angular
																.forEach(
																		response.data.listObject,
																		function(
																				value,
																				index) {
																			var data = {};
																			data.id = value.billingBedCategoryId;
																			data.label = value.billingBedDesc;
																			$scope.bedTypeCategoryData
																					.push(data);
																		});
													}
												});

							}
							$scope.init();
							$scope.getSelectedValue = function() {
								var paymentEntitlementList = [];
								angular.forEach($scope.tariffModel, function(
										value, index) {
									paymentEntitlementList.push(value.id);
								});
								console.log("length",
										paymentEntitlementList.length);
								var data = {
									tariffIdList : paymentEntitlementList,
								}
								console.log("paymentEntitleMentID", data);
								if (paymentEntitlementList.length > 0) {
									var URI = BASE_URL
											+ ROOT_URL
											+ GETPAYMENTENTITLEMENTLISTBYTARIFFIDLIST;
									GenericService
											.serviceAction("POST", URI, data)
											.then(
													function(response) {
														if (response.data.status == "success") {
															$scope.paymentEntitlementModel = [];
															$scope.paymentEntitlementModeldata = [];
															angular
																	.forEach(
																			response.data.listObject,
																			function(
																					value,
																					index) {
																				var data = {};
																				data.id = value.applicablePaymentEntitlementId;
																				data.label = value.paymentEntitlementDesc;
																				$scope.paymentEntitlementModeldata
																						.push(data);
																				$scope.paymentEntitlementModel
																						.push(data);
																			});
															console
																	.log(
																			'list',
																			$scope.paymentEntitlementModeldata);
														}
													});
								}
							}
							$scope.clicked = function(type) {
								if (type == 'speciality') {
									if ($scope.specialityModel.length > 0) {
										var specialityType = [];
										angular.forEach($scope.specialityModel,
												function(value, index) {
													specialityType
															.push(value.id);
												});
										var data = {
											organizationId : $scope.organizationId,
											unitId : $scope.unitId,
											specialityIdList : specialityType
										}
										console.log(data);
										var URI = BASE_URL
												+ ROOT_URL
												+ GETSUBSPECIALITYBYSPECIALITYARRAYFORTARIFF;
										BillingGenericService
												.serviceAction(METHOD_POST,
														URI, data,
														NOTIFICATION_MSG_STATUS_FALSE)
												.then(
														function(response) {
															console
																	.log(response);
															if (response.data.status == "success") {
																console
																		.log(
																				"subspeciality list",
																				response.data.listObject);
																$scope.dataSubSpecialityData = [];
																angular
																		.forEach(
																				response.data.listObject,
																				function(
																						value,
																						index) {
																					var data = {};
																					data.id = value.subSpecialityId;
																					data.label = value.subSpecialityName;
																					$scope.dataSubSpecialityData
																							.push(data);

																				});

															}

														});
									}

								}
							}
							$scope.getSpecialityAndSubspeciality = function() {
								var specialistList = [];
								angular.forEach($scope.specialityModel,
										function(value, index) {
											specialistList.push(value.id);
										});
								var subSpecialistList = [];
								angular.forEach($scope.subSpecialityModel,
										function(value, index) {
											subSpecialistList.push(value.id);
										});
								/*
								 * console.log("specialistListLength",
								 * specialistList.length);
								 * console.log("subSpecialistListLength",
								 * subSpecialistList.length);
								 */
								// return false;
								var data = {
									unitId : $scope.unitId,
									orgnisationId : $scope.organizationId,
									specialityIdList : specialistList,
									subSpecialityIdList : subSpecialistList
								}

								// console.log("aaaa", data); return false;

								// alert(subSpecialistList.length);
								// if (specialistList.length > 0
								// && subSpecialistList.length > 0) {
								var URI = BASE_URL
										+ ROOT_URL
										+ "/global/searchUnitServiceByMultipleSpecialityAndSubSpeciality";
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													if (response.data.status == "success") {
														console
																.log(
																		"ad",
																		response.data.listObject);
														$scope.serviceModel = [];
														$scope.serviceData = [];
														angular
																.forEach(
																		response.data.listObject,
																		function(
																				value,
																				index) {
																			var data = {};
																			data.id = value.serviceId;
																			console
																					.log(
																							'aaaa',
																							data.id);
																			data.label = value.serviceStandardName;
																			$scope.serviceData
																					.push(data);
																			$scope.serviceModel
																					.push(data);
																		});
														console
																.log(
																		'list',
																		$scope.paymentEntitlementModeldata);
													}
												});
								// }
							}

							$scope.searchTariffServices = function() {
								var tariffMasterList = [];
								angular.forEach($scope.tariffModel, function(
										value, index) {
									var data = {};
									data.id = value.id,
											data.lable = value.label

									tariffMasterList.push(data);
								});
								console.log("tariffMasterList",
										tariffMasterList);
								var visitTypeList = [];
								angular.forEach($scope.visitTypeModel,
										function(value, index) {
											var data = {};
											data.id = value.id,
													data.lable = value.label

											visitTypeList.push(data);
										});
								console.log("visitTypeList", visitTypeList);

								var paymentEntitlementTypeList = [];
								angular.forEach($scope.paymentEntitlementModel,
										function(value, index) {
											var data = {};
											data.id = value.id,
													data.lable = value.label

											paymentEntitlementTypeList
													.push(data);
										});
								console.log("paymentEntitlementTypeList",
										paymentEntitlementTypeList);

								var patientTypeList = [];
								angular.forEach($scope.patientTypeModel,
										function(value, index) {
											var data = {};
											data.id = value.id,
													data.lable = value.label

											patientTypeList.push(data);
										});
								console.log("patientTypeList", patientTypeList);

								var bedTypeCategoryList = [];
								angular.forEach($scope.bedTypeCategoryModel,
										function(value, index) {
											var data = {};
											data.id = value.id,
													data.lable = value.label

											bedTypeCategoryList.push(data);
										});
								console.log("bedTypeCategoryList",
										bedTypeCategoryList);

								var specialityTypeList = [];
								angular.forEach($scope.specialityModel,
										function(value, index) {

											specialityTypeList.push(value.id);
										});
								console.log("specialityTypeList",
										specialityTypeList);

								var subSpecialityTypeList = [];
								angular.forEach($scope.subSpecialityModel,
										function(value, index) {
											subSpecialityTypeList
													.push(value.id);
										});
								console.log("subSpecialityTypeList",
										subSpecialityTypeList);

								var serviceTypeList = [];
								angular.forEach($scope.serviceModel, function(
										value, index) {
									serviceTypeList.push(value.id);
								});
								console.log("serviceTypeList",
										$scope.serviceModel);

								var data = {
									unitId : $scope.unitId,
									organizationId : $scope.organizationId,
									tariffMasterList : tariffMasterList,
									paymentEntitlementList : paymentEntitlementTypeList,
									visitTypeList : visitTypeList,
									patientTypeList : patientTypeList,
									bedCategoryList : bedTypeCategoryList,
									specialityList : specialityTypeList,
									subSpecialityList : subSpecialityTypeList,
									serviceList : serviceTypeList

								}
								// console.log("dinesh", data)
								// return false;
								var URI = BASE_URL
										+ ROOT_URL
										+ GETMASTERSERVICELISTBYMULTIPLEPARAMETERS;
								BillingGenericService
										.serviceAction(METHOD_POST, URI, data,
												NOTIFICATION_MSG_STATUS_FALSE)
										.then(
												function(response) {
													$scope.allDetailsList = [];
													if (response.data.status == "success") {

														$scope.allDetailsList = response.data.listObject;
														console
																.log(
																		"allDetailsList",
																		$scope.allDetailsList);
													}

												});
							}

							$scope.applyDate = function() {
								var date = moment($scope.datepicker).format(
										'DD/MM/YYYY');
								angular.forEach(
												$scope.allDetailsList,
												function(value, index) {
													// $('#id_' +
													// index).val(date);
													$scope.allDetailsList[index].effectiveDate = date;
												});

							}
							
							
							$scope.remove = function(index) {
								var newDataList = [];
								var i = 1;
								angular.forEach($scope.allDetailsList, function(v) {
									if (index != i) {
										newDataList.push(v);
									}
									i++;
								});
								
								$scope.allDetailsList = newDataList;
							};
							
							
							$scope.saveUnitServiceTariffMaster = function() {
								console.log('Final', $scope.allDetailsList);
								var date = moment($scope.datepicker).format(
								'YYYY-MM-DD HH:mm:ss');
									
						angular.forEach(
										$scope.allDetailsList,
										function(value, index) {
											// $('#id_' +
											// index).val(date);
											$scope.allDetailsList[index].effectiveDate = date;
										});

//								var dateNew=$scope.allDetailsList.
								var data={
										unitServiceTariffMasterDtosList:$scope.allDetailsList,
										unitId:$scope.unitId,
								        organizationId:$scope.organizationId,
								        createdBy : $scope.userId,
										updatedBy : $scope.userId,
										createdDate :moment(new Date())
												.format('DD-MM-YYYY HH:mm:ss'),
										updatedDate :moment(new Date())
										.format('DD-MM-YYYY HH:mm:ss')
								}
//								console.log('Final', data); return false;
								var URI = BASE_URL
										+ ROOT_URL
										+ SAVEUNITSERVICETARIFFMASTER;
								BillingGenericService
										.serviceAction(METHOD_POST, URI, data,
												NOTIFICATION_MSG_STATUS_FALSE)
										.then(
												function(response) {
													if (response.data.status == "success") {
														growl
																.success(
																		response.data.message,
																		{
																			title : 'Success!'
																		});
													} else {
													/*	growl
																.error(
																		response.data.message,
																		{
																			title : 'Error!'
																		}
																);*/
														$scope.allDetailsList=response.data.listObject
														console.log('Response data list --->',$scope.allDetailsList)
													}

												});
							}

						} ]);
