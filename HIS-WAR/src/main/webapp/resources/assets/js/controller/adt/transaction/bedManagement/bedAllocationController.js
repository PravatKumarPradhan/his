'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:bedAllocationController
 * @description # bedAllocationController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'bedAllocationController',
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
						'$stateParams',
						function($scope, $http, $localStorage, $sessionStorage,
								$cookies, $rootScope, GenericService, growl,
								$state, $stateParams) {

							/* init() function for form object create. */
							$scope.init = function() {

								// if (Object.keys($stateParams.obj).length !=
								// 0)
								// {
								// $rootScope.admissionObj = $stateParams.obj;
								// }
								$scope.admissionNoteData = JSON
										.parse(sessionStorage
												.getItem("admissionNoteObj"));
								console.log($scope.admissionNoteData);

								var bedAllocationFrom = sessionStorage
										.getItem("bedAllocationFrom");

								if (bedAllocationFrom == 'pendingRequest') {
									$scope.admissionNoteData.datepicker = moment(
											$scope.admissionNoteData.datepicker)
											.format('DD/MM/YYYY');
									$scope.admissionNoteData.datepickerPdd = moment(
											$scope.admissionNoteData.datepickerPdd)
											.format('DD/MM/YYYY');
								}
								console.log($scope.admissionNoteData);
								// for datePicker
								$scope.dateOptions = {
									formatYear : 'yyyy',
									showWeeks : false
								};
								$scope.dataList = [ {
									prefixId : '',
									kinName : '',
									relationId : '',
									mobileNo : '',
									phoneNo : '',
									expiry : '',
									identificationId : '',
									identificationNo : '',
									isGuarantor : 'N',
									address : '',
									countryId : '',
									stateId : '',
									districtId : '',
									cityId : '',
									areaId : '',
									postCode : ''
								} ];

								$scope.openDatePicker = function(index) {
									// alert("in");
									// $scope.expiry.opened = true;
									for (var i = $scope.dataList.length - 1; i >= 0; i--) {
										if (i === index) {

											$scope.expiry.opened[i] = true;
										} else {
											$scope.expiry.opened[i] = false;
										}
									}
								};

								$scope.expiry = {
									opened : [ false ],

								};
								
								var cookieObject = $cookies.getObject('cookieObject');
								if(cookieObject == undefined){
									$state.go('login');
									return;
								}
								$scope.unitId = cookieObject.unitId;
								$scope.organizationId = cookieObject.organizationId;
								$scope.userId = 1;
								

								$rootScope.loginpage = true;
								$scope.saveBtnFlag = false;
								$scope.updateBtnFlag = true;

								$scope.bedsearch = {
									floorId : 0,
									nursingStationId : 0,
									bedCategoryId : 0,
									bedStatusId : 0,
									wardId : 0,
									isVirtual : 'N',
									organizationId : $scope.organizationId,
									unitId : $scope.unitId,

								}

								// for popup
								$scope.showModal = false;
								$scope.buttonClicked = "";
								$scope.popUpFlag = true;
								// end for popup

								var commonObj = {
									unitId : $scope.unitId,
									organizationId : $scope.organizationId
								};

								var bedObj = {
									bedCategoryId : $scope.admissionNoteData.bedCategoryId,
									unitId : $scope.unitId,
									organizationId : $scope.organizationId
								}
								var URI = BASE_URL + ROOT_URL
										+ GETACTIVEBEDLIST;
								GenericService
										.serviceAction("POST", URI, bedObj)
										.then(
												function(response) {
													$scope.activeBedList = [];

													if (response.data.status == "success") {
														$scope.activeBedList = response.data.listObject;

													}
												});
								var URI = BASE_URL + ROOT_URL
										+ GETACTIVEFLOORLIST;
								GenericService
										.serviceAction("POST", URI, commonObj)
										.then(
												function(response) {
													$scope.activeFloorList = [];

													if (response.data.status == "success") {
														$scope.activeFloorList = response.data.listObject;

													}
												});

								var URI = BASE_URL + ROOT_URL
										+ GETACTIVEBEDCATEGORYLISTBYUNIT;
								GenericService
										.serviceAction("POST", URI, commonObj)
										.then(
												function(response) {
													$scope.bedCategoryList = [];

													if (response.data.status == "success") {
														$scope.bedCategoryList = response.data.listObject;

													}
												});
								var URI = BASE_URL + ROOT_URL
										+ GETACTIVEMEALPREFERENCELIST;
								GenericService
										.serviceAction("POST", URI, commonObj)
										.then(
												function(response) {
													$scope.mealPreferenceList = [];

													if (response.data.status == "success") {
														$scope.mealPreferenceList = response.data.listObject;

													}
												});

								var data = {};
								var URI = BASE_URL + ROOT_URL
										+ GETACTIVEBEDSTATUSLIST;
								GenericService
										.serviceAction("GET", URI, data)
										.then(
												function(response) {
													$scope.bedStatusList = [];

													if (response.data.status == "success") {
														$scope.bedStatusList = response.data.listObject;

													}
												});

								var URI = BASE_URL + ROOT_URL
										+ GETACTIVEPAYMENTENTITLEMENT;
								GenericService
										.serviceAction("GET", URI, data)
										.then(
												function(response) {
													$scope.paymentEntitlementList = [];

													if (response.data.status == "success") {
														$scope.paymentEntitlementList = response.data.listObject;
													}
												});
								var URI = BASE_URL + ROOT_URL
										+ GETACTIVEPREFIXLIST;
								GenericService
										.serviceAction("GET", URI, data)
										.then(
												function(response) {
													$scope.prefixList = [];

													if (response.data.status == "success") {
														$scope.prefixList = response.data.listObject;

													}
												});
								var URI = BASE_URL + ROOT_URL
										+ GETACTIVERELATIONLIST;
								GenericService
										.serviceAction("GET", URI, data)
										.then(
												function(response) {
													$scope.relationList = [];

													if (response.data.status == "success") {
														$scope.relationList = response.data.listObject;

													}
												});
								var URI = BASE_URL + ROOT_URL
										+ GETACTIVEIDENTIFICATION;
								GenericService
										.serviceAction("GET", URI, data)
										.then(
												function(response) {
													$scope.identificationList = [];

													if (response.data.status == "success") {
														$scope.identificationList = response.data.listObject;

													}
												});
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

							}

							$scope.init();

							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.saveBedAllocation();
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
													}
												});

							}

							$scope.getBedById = function(commonType) {

								console.log($scope.bedsearch);

								var commonData = {
									unitId : $scope.unitId,
									organizationId : $scope.organizationId
								};
								switch (commonType) {
								case 'floor':

									commonData.floorId = $scope.bedsearch.floorId;
									commonData.genderId = $scope.admissionNoteData.genderId;

									var URI = BASE_URL + ROOT_URL
											+ GETACTIVEWARDLISTBYFLOORID;
									GenericService
											.serviceAction("POST", URI,
													commonData)
											.then(
													function(response) {
														$scope.wardList = [];

														if (response.data.status == "success") {
															$scope.wardList = response.data.listObject;

														}
													});
									break;
								case 'ward':

									commonData.wardId = $scope.bedsearch.wardId;

									var URI = BASE_URL
											+ ROOT_URL
											+ GETACTIVENURSINGSTATIONLISTBYWARDID;
									GenericService
											.serviceAction("POST", URI,
													commonData)
											.then(
													function(response) {
														$scope.nursingStationList = [];

														if (response.data.status == "success") {
															$scope.nursingStationList = response.data.listObject;

														}
													});
									break;
								default:

								}

							}

							$scope.bedSearchByMultipleCriteria = function() {

								// $scope.bedsearch.isVirtual =
								// ($scope.bedsearch.isVirtual == true) ? "Y":
								// "N";
								console.log($scope.bedsearch);
								// return false;
								var URI = BASE_URL + ROOT_URL
										+ GETBEDLISTBYMULTIPLECRITERIA;
								GenericService
										.serviceAction("POST", URI,
												$scope.bedsearch)
										.then(
												function(response) {
													$scope.activeBedList = [];

													if (response.data.status == "success") {
														$scope.activeBedList = response.data.listObject;

													}
												});

							}

							$scope.bedAllocationPopUp = function(info) {

								console.log(info);

								$scope.admissionNoteData.bedId = info.bedId;
								$scope.admissionNoteData.wardId = info.wardId;
								$scope.admissionNoteData.wardName = info.wardName;

								$scope.admissionNoteData.floorId = info.floorId;
								$scope.admissionNoteData.floorName = info.floorName;

								$scope.admissionNoteData.bedCategoryId = info.bedCategoryId;
								$scope.admissionNoteData.bedCategoryDesc = info.bedCategoryDesc;
								$scope.admissionNoteData.billingBedDesc = info.billingBedDesc;

								$scope.admissionNoteData.roomId = info.roomId;
								$scope.admissionNoteData.billingBedCategoryId = info.billingBedCategoryId
										.toString();
								$scope.admissionNoteData.mealPreferenceId = 0;
								$scope.admissionNoteData.attendantMealPreferenceId = 0;
								/*var data = {
									organizationId : $scope.organizationId,
									unitId : $scope.unitId,
									bedCategoryId : $scope.admissionNoteData.bedCategoryId
								}

								var URI = BASE_URL
										+ ROOT_URL
										+ GETACTIVEBILLINGBEDCATEGORYBYBEDCATEGORYID;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.billingBedCategoryList = [];

													if (response.data.status == "success") {
														$scope.billingBedCategoryList = response.data.listObject;

													}
												});*/
							}

							$scope.add = function() {
								var data = {
									prefixId : '',
									kinName : '',
									relationId : '',
									mobileNo : '',
									phoneNo : '',
									expiry : '',
									identificationId : '',
									identificationNo : '',
									isGuarantor : 'N',
									address : '',
									countryId : '',
									stateId : '',
									districtId : '',
									cityId : '',
									areaId : '',
									postCode : ''
								};
								$scope.dataList.push(data);
							};

							$scope.remove = function() {
								var newDataList = [];
								var le = $scope.dataList.length;
								var i = 1;
								angular.forEach($scope.dataList, function(v) {
									if (le != i) {
										newDataList.push(v);
									}
									i++;
								});
								$scope.dataList = newDataList;
							};

							$scope.saveBedAllocation = function() {

								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								console.log($scope.dataList);
								angular.forEach($scope.dataList, function(
										value, index) {
									value.expiry = moment(value.expiry).format(
											'DD-MM-YYYY HH:mm:ss');
								});
								var dateOfPdd = $rootScope
										.getChangedFormatedDateShalse($scope.admissionNoteData.datepickerPdd);
								var dateOfAdmission = $rootScope
										.getChangedFormatedDateShalse($scope.admissionNoteData.datepicker);
								var data = {
									admissionNoteId : $scope.admissionNoteData.admissionNoteId,
									floorId : $scope.admissionNoteData.floorId,
									wardId : $scope.admissionNoteData.wardId,
									roomId : $scope.admissionNoteData.roomId,
									bedCategoryId : $scope.admissionNoteData.bedCategoryId,
									billingBedCategoryId : $scope.admissionNoteData.billingBedCategoryId,
									pdd : dateOfPdd + ' 00:00:00',
									doa : dateOfAdmission + ' 00:00:00',
									mealPrefId : $scope.admissionNoteData.mealPreferenceId,
									attendentMealPrefId : $scope.admissionNoteData.attendantMealPreferenceId,
									organizationId : $scope.organizationId,
									unitId : $scope.unitId,
									patientId : $scope.admissionNoteData.patientId,
									tPatientId : $scope.admissionNoteData.tPatientId,
									specialityId : $scope.admissionNoteData.doctorSpecialityId,
									visitTypeId : $scope.admissionNoteData.visitTypeId,
									bedId : $scope.admissionNoteData.bedId,
									paymentEntitlementId : $scope.admissionNoteData.paymentEntitlementId,
									doctorId : $scope.admissionNoteData.requestToDoctorId,
									patientCategoryId : $scope.admissionNoteData.patientCategoryId,
									updatedBy : $scope.userId,
									createdBy : $scope.userId,
									kinArray : $scope.dataList,
									createdDate : $scope.currentDate,
									updatedDate : $scope.currentDate,
									admissionTypeId: '1', //For OP
									bedCategoryWaitingListId:$scope.admissionNoteData.bedCategoryWaitingListId
								};
								console.log(data);

								// return false;
								var URI = BASE_URL + ROOT_URL
										+ ADDBEDALLOCATION;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {

													if (response.data.status == "success") {
														growl
																.success(
																		'Patient Admitted sucessfully!!!.',
																		{
																			title : 'Success!'
																		});
														$scope.init();
														sessionStorage
																.setItem(
																		"admissionNoteObj",
																		null);
														angular
																.element(
																		'#patientAdmission')
																.modal('hide');
														// $state.go('admittedPatientList');
													} else {
														growl
																.error(
																		'Something wrongs!!!!.',
																		{
																			title : 'Error!'
																		});
													}

												});

							}

						} ]);
