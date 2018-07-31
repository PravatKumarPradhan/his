'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:pendingAdmissionDischargeController
 * @description # pendingAdmissionDischargeController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'pendingAdmissionDischargeController',
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
						'$filter',
						'PagerService',
						function($scope, $http, $localStorage, $sessionStorage,
								$cookies, $rootScope, GenericService, growl,
								$state, $filter, PagerService) {

							$scope.openDatePicker = function(index) {

								for (var i = $scope.pendingAdmissionList.length - 1; i >= 0; i--) {
									if (i === index) {

										$scope.datepicker.opened[i] = true;
									} else {
										$scope.datepicker.opened[i] = false;
									}
								}
							};

							$scope.datepicker = {
								opened : [ false ],

							};

							$scope.openDatePickerPdd = function(index) {

								for (var i = $scope.pendingAdmissionList.length - 1; i >= 0; i--) {
									if (i === index) {

										$scope.datepickerPdd.opened[i] = true;
									} else {
										$scope.datepickerPdd.opened[i] = false;
									}
								}
							};

							$scope.datepickerPdd = {
								opened : [ false ],

							};

							$scope.dateOptions = {
								formatYear : 'yyyy',
								showWeeks : false
							};

							/* init() function for form object create. */
							    $scope.init = function() {

								$scope.reserverBedPopUpFlag = false;
								
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

								$rootScope.loginpage = true;
								$scope.pendingAdmissionRequest = {}
								$scope.itemSelecteds = {};

								// for popup
								$scope.showModal = false;
								$scope.buttonClicked = "";
								$scope.popUpFlag = true;
								// end for popup

								var data = {};

								var commonObj = {
									unitId : $scope.unitId,
									organizationId : $scope.organizationId
								}

								// $scope.status = "NO";
								/*
								 * var URI = BASE_URL + ROOT_URL +
								 * GETPENDINGADMISSIONREQUEST; GenericService
								 * .serviceAction("POST", URI, commonObj) .then(
								 * function(response) {
								 * $scope.pendingAdmissionList = [];
								 * 
								 * if (response.data.status == "success") {
								 * $scope.pendingLists =
								 * response.data.listObject; angular .forEach(
								 * $scope.pendingLists, function( value, index) {
								 * var newArr = {}; newArr.admissionNoteId =
								 * value.admissionNoteId; newArr.reasonDesc =
								 * value.reasonDesc; newArr.specialityName =
								 * value.specialityName; newArr.dFirstName =
								 * value.dFirstName; newArr.dMiddleName =
								 * value.dMiddleName; newArr.dFirstName =
								 * value.dFirstName; newArr.dLastName =
								 * value.dLastName; newArr.pFirstName =
								 * value.pFirstName; newArr.pMiddleName =
								 * value.pMiddleName; newArr.pLastName =
								 * value.pLastName; newArr.visitTypeName =
								 * value.visitTypeName; newArr.isFlexiableDate =
								 * value.isFlexiableDate; newArr.reasonDesc =
								 * value.reasonDesc; newArr.paymentEntitlementId =
								 * value.paymentEntitlementId .toString();
								 * newArr.dob = value.dob;
								 * newArr.datepickerNoFle = value.doa;
								 * newArr.datepickerPddNoFle = value.pdd;
								 * newArr.datepicker = new Date( value.doa);
								 * newArr.datepickerPdd = new Date( value.pdd);
								 * newArr.bedCategoryDesc =
								 * value.bedCategoryDesc; newArr.genderCode =
								 * value.genderCode; newArr.patientCategoryId =
								 * value.patientCategoryId .toString();
								 * newArr.bedCategoryId = value.bedCategoryId
								 * .toString(); newArr.uhidnumber =
								 * value.uhidnumber; newArr.genderId = value.id;
								 * newArr.doctorSpecialityId =
								 * value.doctorSpecialityId;
								 * newArr.requestToDoctorId =
								 * value.requestToDoctorId; newArr.visitTypeId =
								 * value.visitTypeId; newArr.patientId =
								 * value.patientId; newArr.patientCategoryDesc =
								 * value.patientCategory;
								 * newArr.paymentEntitlementDesc =
								 * value.paymentEntitlementDesc;
								 * $scope.pendingAdmissionList .push(newArr);
								 * }); console .log($scope.pendingLists);
								 *  } });
								 */

								var URI = BASE_URL + ROOT_URL
										+ GETACTIVEPATIENTCATEGORY;
								GenericService
										.serviceAction("GET", URI, data)
										.then(
												function(response) {
													$scope.patientCategoryList = [];

													if (response.data.status == "success") {
														$scope.patientCategoryList = response.data.listObject;
														console
																.log($scope.patientCategoryList);

													}
												});

								var URI = BASE_URL + ROOT_URL
										+ GETACTIVEDOCUMENTTYPELIST;
								GenericService
										.serviceAction("GET", URI, data)
										.then(
												function(response) {
													$scope.documentTypeList = [];

													if (response.data.status == "success") {
														$scope.documentTypeList = response.data.listObject;

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
														console
																.log($scope.paymentEntitlementList);

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

							}

							$scope.init();

							// For Paginations Start
							$scope.setNoOfRecords = function() {
								$scope.initPendingAdmissionList(
										$scope.organizationId, $scope.offset,
										$scope.noOfRecordsPerPage);
							};
							$scope.initPendingAdmissionList = function(orgId,
									offset, noOfRecordsPerPage) {
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
										: 10;
								var URI = BASE_URL + ROOT_URL
										+ GETPENDINGADMISSIONREQUEST;
								var URI1 = BASE_URL + ROOT_URL
										+ COUNTPENDINGADMISSIONLIST;

								var data = {
									organizationId : orgId,
									unitId : $scope.unitId,
									offset : offset,
									noOfRecordsPerPage : noOfRecordsPerPage
								}

								var data1 = {
									organizationId : $scope.organizationId,
									unitId : $scope.unitId
								}

								// For Get Pending Admission Request List Using
								// Offset
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.pendingAdmissionList = [];

													if (response.data.status == "success") {
														$scope.pendingLists = response.data.listObject;
														angular
																.forEach(
																		$scope.pendingLists,
																		function(
																				value,
																				index) {
																			var newArr = {};
																			newArr.admissionNoteId = value.admissionNoteId;
																			newArr.reasonDesc = value.reasonDesc;
																			newArr.specialityName = value.specialityName;
																			newArr.doctorFullName = value.dFirstName;
																			newArr.patientFullName = value.pFirstName;
																			newArr.visitTypeName = value.visitTypeName;
																			newArr.isFlexiableDate = value.isFlexiableDate;
																			newArr.reasonDesc = value.reasonDesc;
																			newArr.paymentEntitlementId = value.paymentEntitlementId
																					.toString();
																			newArr.dob = value.dob;
																			newArr.datepickerNoFle = value.doa;
																			newArr.datepickerPddNoFle = value.pdd;
																			newArr.datepicker = new Date(
																					value.doa);
																			newArr.datepickerPdd = new Date(
																					value.pdd);
																			newArr.bedCategoryDesc = value.bedCategoryDesc;
																			newArr.genderCode = value.genderCode;
																			if(value.patientCategoryId == null)
																				{
																				newArr.patientCategoryId = '';
																				}
																			else
																				{
																				newArr.patientCategoryId = value.patientCategoryId
																				.toString();
																				}
																			
																			
																			newArr.bedCategoryId = value.bedCategoryId
																					.toString();
																			newArr.uhidnumber = value.uhidnumber;
																			newArr.genderId = value.id;
																			newArr.doctorSpecialityId = value.doctorSpecialityId;
																			newArr.requestToDoctorId = value.requestToDoctorId;
																			newArr.visitTypeId = value.visitTypeId;
																			newArr.patientId = value.patientId;
																			newArr.tPatientId = value.tPatientId;
																			newArr.patientCategoryDesc = value.patientCategory;
																			newArr.paymentEntitlementDesc = value.paymentEntitlementDesc;
																			newArr.oldDoa = value.doa;
																			newArr.oldBedCategoryId = value.bedCategoryId;
																			newArr.bedCategoryWaitingListId = value.bedCategoryWaitingListId;
																			$scope.pendingAdmissionList
																					.push(newArr);
																		});
														console
																.log($scope.pendingLists);

													}
												});

								// For Count Pending Admission Request List
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

							$scope.getPendingAdmissionList = function(orgId,
									offset, noOfRecordsPerPage) {
								console.log("offset", offset);
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
										: 10;
								var URI = BASE_URL + ROOT_URL
										+ GETPENDINGADMISSIONREQUEST;
								var data = {
									organizationId : $scope.organizationId,
									unitId : $scope.unitId,
									offset : offset,
									noOfRecordsPerPage : noOfRecordsPerPage
								}

								console.log("URI", URI);
								// For Get Pending Admission Request List Using
								// Offset
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.pendingAdmissionList = [];

													if (response.data.status == "success") {
														$scope.pendingLists = response.data.listObject;
														angular
																.forEach(
																		$scope.pendingLists,
																		function(
																				value,
																				index) {
																			var newArr = {};
																			newArr.admissionNoteId = value.admissionNoteId;
																			newArr.reasonDesc = value.reasonDesc;
																			newArr.specialityName = value.specialityName;
																			newArr.doctorFullName = value.dFirstName;
																			newArr.patientFullName = value.pFirstName;
																			newArr.visitTypeName = value.visitTypeName;
																			newArr.isFlexiableDate = value.isFlexiableDate;
																			newArr.reasonDesc = value.reasonDesc;
																			newArr.paymentEntitlementId = value.paymentEntitlementId
																					.toString();
																			newArr.dob = value.dob;
																			newArr.datepickerNoFle = value.doa;
																			newArr.datepickerPddNoFle = value.pdd;
																			newArr.datepicker = new Date(
																					value.doa);
																			newArr.datepickerPdd = new Date(
																					value.pdd);
																			newArr.bedCategoryDesc = value.bedCategoryDesc;
																			newArr.genderCode = value.genderCode;
																			if(value.patientCategoryId == null)
																			{
																			newArr.patientCategoryId = '';
																			}
																		else
																			{
																			newArr.patientCategoryId = value.patientCategoryId
																			.toString();
																			}
																			newArr.patientCategoryId = value.patientCategoryId
																					.toString();
																			newArr.bedCategoryId = value.bedCategoryId
																					.toString();
																			newArr.uhidnumber = value.uhidnumber;
																			newArr.genderId = value.id;
																			newArr.doctorSpecialityId = value.doctorSpecialityId;
																			newArr.requestToDoctorId = value.requestToDoctorId;
																			newArr.visitTypeId = value.visitTypeId;
																			newArr.patientId = value.patientId;
																			newArr.tPatientId = value.tPatientId;
																			newArr.patientCategoryDesc = value.patientCategory;
																			newArr.paymentEntitlementDesc = value.paymentEntitlementDesc;
																			newArr.oldDoa = value.doa;
																			newArr.oldBedCategoryId = value.bedCategoryId;
																			newArr.bedCategoryWaitingListId = value.bedCategoryWaitingListId;
																			$scope.pendingAdmissionList
																					.push(newArr);
																		});
														console
																.log($scope.pendingLists);

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
									$scope.getPendingAdmissionList(
											$scope.organizationId,
											$scope.pager.startIndex,
											$scope.pager.pageSize);
								}
							}

							$scope.initPendingAdmissionList(
									$scope.organizationId, $scope.offset,
									$scope.noOfRecordsPerPage);

							// For Paginations End

							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.updatePendingRequest();
							}

							$scope.selectItem = function(item) {

								$scope.itemSelecteds = [];
								// If checkbox is checked
								if ($scope.pendingAdmissionRequest) {
									$scope.itemSelecteds = item;
									if($scope.itemSelecteds.visitTypeId != 4)
										{
											$scope.reserverBedPopUpFlag = true;
										}
									
								}
							}

							// Function for update Admission Request
							$scope.updatePendingRequest = function() {

								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								console.log($scope.itemSelecteds);
								// console.log($scope.itemSelecteds.pLastName);
								if (Object.keys($scope.itemSelecteds).length === 0) {
									growl
											.error(
													'Please select atleast one patient!!!.',
													{
														title : 'Error!'
													});
								} else {
									var waitListNumber = 0;
									var dateOfPdd = moment(
											$scope.itemSelecteds.datepickerPdd)
											.format('DD-MM-YYYY HH:mm:ss');
									var dateOfAdmission = moment(
											$scope.itemSelecteds.datepicker)
											.format('DD-MM-YYYY HH:mm:ss');
									if($scope.itemSelecteds.bedCategoryWaitingListId != null)
										{
										waitListNumber = $scope.itemSelecteds.bedCategoryWaitingListId;
										}
									var data = {

										admissionNoteId : $scope.itemSelecteds.admissionNoteId,
										bedCategoryId : $scope.itemSelecteds.bedCategoryId,
										paymentEntitlementId : $scope.itemSelecteds.paymentEntitlementId,
										patientCategoryId : $scope.itemSelecteds.patientCategoryId,
										doa : dateOfAdmission,
										pdd : dateOfPdd,
										updatedBy : $scope.userId,
										updatedDate : $scope.currentDate,
										createdDate : $scope.currentDate,
										organizationId:$scope.organizationId,
										unitId : $scope.unitId,
										oldDoa : moment($scope.itemSelecteds.oldDoa)
												.format('DD-MM-YYYY HH:mm:ss'),
									    oldBedCategoryId: $scope.itemSelecteds.oldBedCategoryId,
									    status : 'A',
									    bedCategoryWaitingListId :waitListNumber
									}
									console.log(data);
									var URI = BASE_URL + ROOT_URL
											+ UPDATEPENDINGADMISSION;
									GenericService
											.serviceAction("POST", URI, data)
											.then(
													function(response) {

														if (response.data.status == "success") {
															growl
																	.success(
																			'Admission updated sucessfully!!!.',
																			{
																				title : 'Success!'
																			});
															$scope.init();
														} else {
															growl
																	.error(
																			'Something wrongs!!!!.',
																			{
																				title : 'Error!'
																			});
														}
														$scope
																.initPendingAdmissionList(
																		$scope.organizationId,
																		$scope.offset,
																		$scope.noOfRecordsPerPage);

													});
								}

							}

							// Function for bed-allocation
							$scope.bedAllocation = function() {
								
								var todayDate = new Date();
								todayDate = moment(todayDate).format(
										'DD/MM/YYYY');
								if (Object.keys($scope.itemSelecteds).length === 0) {
									growl
											.error(
													'Please select atleast one patient!!!.',
													{
														title : 'Error!'
													});
								} else {
									if (todayDate == moment(
											$scope.itemSelecteds.datepicker)
											.format('DD/MM/YYYY')) {
										sessionStorage
												.setItem(
														"admissionNoteObj",
														JSON
																.stringify($scope.itemSelecteds));
										sessionStorage.setItem(
												"bedAllocationFrom",
												"pendingRequest");
										$state.go('bedAllocation');
									} else {
										growl
												.error(
														'Patient Date of Admission and Today Date Should be Same!!.',
														{
															title : 'Error!'
														});
									}
								}
							}

							// Function For bed reserve popup values
							$scope.bedReserved = function() {
								console.log($scope.itemSelecteds);
								
								
								if (Object.keys($scope.itemSelecteds).length === 0) {
									growl
											.error(
													'Please select atleast one patient!!!.',
													{
														title : 'Error!'
													});
								} else {
									console.log($scope.itemSelecteds);
									var age = $rootScope
											.getAge($scope.itemSelecteds.dob);
									$scope.patientFullName = $scope.itemSelecteds.patientFullName
											+ " "
											+ age
											+ "/"
											+ $scope.itemSelecteds.genderCode;
									$scope.uhidNum = $scope.itemSelecteds.uhidnumber;
									$scope.doctorFullName = 'Dr. '
											+ $scope.itemSelecteds.doctorFullName;
									$scope.patientCategoryDesc = $scope.itemSelecteds.patientCategoryDesc;
									$scope.paymentEntitlementDesc = $scope.itemSelecteds.paymentEntitlementDesc;
									$scope.bedCategoryId = $scope.itemSelecteds.bedCategoryId;
									$scope.paymentEntitlementId = $scope.itemSelecteds.paymentEntitlementId;
									$scope.dateOfAdmission = moment(
											$scope.itemSelecteds.datepicker)
											.format('DD/MM/YYYY');
									$scope.patientDischargeDate = moment(
											$scope.itemSelecteds.datepickerPdd)
											.format('DD/MM/YYYY');
								}
							}


							// Function For Save bed reservetion
							$scope.savebedReservation = function() {

								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');
								var dateOfPdd = moment(
										$scope.itemSelecteds.datepickerPdd)
										.format('DD-MM-YYYY HH:mm:ss');
								var dateOfAdmission = moment(
										$scope.itemSelecteds.datepicker)
										.format('DD-MM-YYYY HH:mm:ss');

								var data = {
									admissionNoteId : $scope.itemSelecteds.admissionNoteId,
									bedCategoryId : $scope.bedCategoryId,
									paymentEntitlementId : $scope.paymentEntitlementId,
									createdBy : $scope.userId,
									organizationId : $scope.organizationId,
									patientCategoryId : $scope.itemSelecteds.patientCategoryId,
									doa : dateOfAdmission,
									pdd : dateOfPdd,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate,
									createdDate : $scope.currentDate
								};
								console.log(data);

								var URI = BASE_URL + ROOT_URL
										+ UPDATERESERVATIONADMISSION;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {

													if (response.data.status == "success") {
														growl
																.success(
																		'Reserve sucessfully!!!.',
																		{
																			title : 'Success!'
																		});
														$scope.init();
														angular.element(
																'#reservedBed')
																.modal('hide');
													} else {
														growl
																.error(
																		'Something wrongs!!!!.',
																		{
																			title : 'Error!'
																		});
													}
													$scope
															.initPendingAdmissionList(
																	$scope.organizationId,
																	$scope.offset,
																	$scope.noOfRecordsPerPage);

												});

							}

							$scope.dataList = [ {
								documentTypeId : '',
								documentPath : ''
							} ];

							$scope.add = function() {
								var data = {
									documentTypeId : '',
									documentPath : ''
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

							// Function For Save Document
							$scope.saveDocuments = function() {

								console.log($scope.dataList);
								angular.forEach($scope.dataList, function(
										value, index) {

									var fd = new FormData();
									fd.append('file', value.documentPath);
									value.documentPath = fd;
								});
								var data = {
									admissionNoteId : $scope.itemSelecteds.admissionNoteId,
									createdBy : $scope.userId,
									organizationId : $scope.organizationId,
									updatedBy : $scope.userId,
									documentArray : $scope.dataList,
									unitId : $scope.unitId
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ UPLOADPATIENTDOCUMENTS;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {

													if (response.data.status == "success") {
														growl
																.success(
																		'Document uploaded  sucessfully!!!.',
																		{
																			title : 'Success!'
																		});

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
