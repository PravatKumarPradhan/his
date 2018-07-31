'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:bedReservationListController
 * @description # bedReservationListController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'bedReservationListController',
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
								$state, PagerService) {

							/* init() function for form object create. */

							$scope.init = function() {

								// for datePicker

								$scope.datepickerForNewDateOfAdmission = {
									opened : false
								};

								$scope.openDatePickerForNewDOA = function() {
									$scope.datepickerForNewDateOfAdmission.opened = true;
								};

								$scope.dateOptions = {
									formatYear : 'yyyy',
									showWeeks : false
								};

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
								$scope.reserveAdmissionRequest = {}
								$scope.itemSelecteds = {};
								$scope.unReservePopUpFlag = false;
								$scope.unreserveFlag = false;
								$scope.assignContractPopUpFlag = false;
								$scope.insurancePopUpFlag = false;
								$scope.depositeMatrixPopUpFlag = false;
								$scope.btnValue = "Refund Deposit";
								$scope.cancelFlag = true;
								$scope.canres = "cancel";

								var data = {};
								var commonObj = {
									unitId : $scope.unitId,
									organizationId : $scope.organizationId
								}

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

								/*
								 * var URI = BASE_URL + ROOT_URL +
								 * GETBEDRESERVEDLIST; GenericService
								 * .serviceAction("POST", URI, commonObj) .then(
								 * function(response) { $scope.bedReservedList =
								 * [];
								 * 
								 * if (response.data.status == "success") {
								 * $scope.bedReservetionList =
								 * response.data.listObject; angular .forEach(
								 * $scope.bedReservetionList, function( value,
								 * index) { var newArr = {};
								 * newArr.admissionNoteId =
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
								 * newArr.dob = value.dob; newArr.datepicker =
								 * value.doa; newArr.datepickerPdd = value.pdd;
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
								 * $scope.bedReservedList .push(newArr); });
								 *  } });
								 */

								var URI = BASE_URL + ROOT_URL
										+ GETACTIVEREASONLIST;
								GenericService
										.serviceAction("GET", URI, data)
										.then(
												function(response) {
													$scope.reasonList = [];

													if (response.data.status == "success") {
														$scope.reasonList = response.data.listObject;

													}
												});
							}
							$scope.init();

							// For Paginations Start
							$scope.setNoOfRecords = function() {
								$scope.initBedReservetionList(
										$scope.organizationId, $scope.offset,
										$scope.noOfRecordsPerPage);
							};
							$scope.initBedReservetionList = function(orgId,
									offset, noOfRecordsPerPage) {
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
										: 10;
								var URI = BASE_URL + ROOT_URL
										+ GETBEDRESERVEDLIST;
								var URI1 = BASE_URL + ROOT_URL
										+ COUNTBEDRESERVETIONLIST;

								var data = {
									organizationId : orgId,
									unitId : $scope.unitId,
									offset : offset,
									noOfRecordsPerPage : noOfRecordsPerPage
								}

								var data1 = {
									organizationId : $scope.organizationId,
									unitId : $scope.unitId,
								}

								// For Get Bed Reservation List Using Offset
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.bedReservedList = [];

													if (response.data.status == "success") {
														$scope.bedReservetionList = response.data.listObject;
														angular
																.forEach(
																		$scope.bedReservetionList,
																		function(
																				value,
																				index) {
																			var newArr = {};
																			newArr.admissionNoteId = value.admissionNoteId;
																			newArr.reasonDesc = value.reasonDesc;
																			newArr.specialityName = value.specialityName;
																			newArr.dFirstName = value.dFirstName;
																			newArr.dMiddleName = value.dMiddleName;
																			newArr.dFirstName = value.dFirstName;
																			newArr.dLastName = value.dLastName;
																			newArr.pFirstName = value.pFirstName;
																			newArr.pMiddleName = value.pMiddleName;
																			newArr.pLastName = value.pLastName;
																			newArr.visitTypeName = value.visitTypeName;
																			newArr.isFlexiableDate = value.isFlexiableDate;
																			newArr.reasonDesc = value.reasonDesc;
																			newArr.paymentEntitlementId = value.paymentEntitlementId
																					.toString();
																			newArr.dob = value.dob;
																			newArr.datepicker = value.doa;
																			newArr.datepickerPdd = value.pdd;
																			newArr.bedCategoryDesc = value.bedCategoryDesc;
																			newArr.genderCode = value.genderCode;
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
																			newArr.patientCategoryDesc = value.patientCategory;
																			newArr.paymentEntitlementDesc = value.paymentEntitlementDesc;
																			$scope.bedReservedList
																					.push(newArr);
																		});

													}
												});

								// For Count Bed Reservation List
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

							$scope.getBedReservetionList = function(orgId,
									offset, noOfRecordsPerPage) {
								console.log("offset", offset);
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
										: 10;
								var URI = BASE_URL + ROOT_URL
										+ GETBEDRESERVEDLIST;
								var data = {
									organizationId : $scope.organizationId,
									unitId : $scope.unitId,
									offset : offset,
									noOfRecordsPerPage : noOfRecordsPerPage
								}

								console.log("URI", URI);
								// For Get Bed Reservation List Using Offset
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.bedReservedList = [];

													if (response.data.status == "success") {
														$scope.bedReservetionList = response.data.listObject;
														angular
																.forEach(
																		$scope.bedReservetionList,
																		function(
																				value,
																				index) {
																			var newArr = {};
																			newArr.admissionNoteId = value.admissionNoteId;
																			newArr.reasonDesc = value.reasonDesc;
																			newArr.specialityName = value.specialityName;
																			newArr.dFirstName = value.dFirstName;
																			newArr.dMiddleName = value.dMiddleName;
																			newArr.dFirstName = value.dFirstName;
																			newArr.dLastName = value.dLastName;
																			newArr.pFirstName = value.pFirstName;
																			newArr.pMiddleName = value.pMiddleName;
																			newArr.pLastName = value.pLastName;
																			newArr.visitTypeName = value.visitTypeName;
																			newArr.isFlexiableDate = value.isFlexiableDate;
																			newArr.reasonDesc = value.reasonDesc;
																			newArr.paymentEntitlementId = value.paymentEntitlementId
																					.toString();
																			newArr.dob = value.dob;
																			newArr.datepicker = value.doa;
																			newArr.datepickerPdd = value.pdd;
																			newArr.bedCategoryDesc = value.bedCategoryDesc;
																			newArr.genderCode = value.genderCode;
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
																			newArr.patientCategoryDesc = value.patientCategory;
																			newArr.paymentEntitlementDesc = value.paymentEntitlementDesc;
																			$scope.bedReservedList
																					.push(newArr);
																		});

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
									$scope.getBedReservetionList(
											$scope.organizationId,
											$scope.pager.startIndex,
											$scope.pager.pageSize);
								}
							}

							$scope.initBedReservetionList(
									$scope.organizationId, $scope.offset,
									$scope.noOfRecordsPerPage);

							// For Paginations End
							$scope.selectPatientReserve = function(item) {
								console.log(item);
								$scope.itemSelecteds = [];
								// If checkbox is checked
								if ($scope.reserveAdmissionRequest) {
									$scope.itemSelecteds = item;
									$scope.unReservePopUpFlag = true;

									$scope.assignContractPopUpFlag = true;
									$scope.insurancePopUpFlag = true;
									$scope.depositeMatrixPopUpFlag = true;
								}
								console.log($scope.itemSelecteds);
							}

							$scope.bedAllocation = function() {
								// alert($scope.itemSelecteds.datepicker);
								var todayDate = new Date();
								//todayDate = $rootScope.getFormatDate(todayDate);
								todayDate = moment(new Date()).format('DD-MM-YYYY');
								/*alert(todayDate);
								alert($rootScope
										.getChangedFormatedDateShalse($scope.itemSelecteds.datepicker));*/
								
								
								// var myObj = $scope.itemSelecteds;
								if (Object.keys($scope.itemSelecteds).length === 0) {
									growl
											.error(
													'Please select atleast one patient!!!.',
													{
														title : 'Error!'
													});
								} else {
									if (todayDate == $rootScope
											.getChangedFormatedDateShalse($scope.itemSelecteds.datepicker)) {

										sessionStorage
												.setItem(
														"admissionNoteObj",
														JSON
																.stringify($scope.itemSelecteds));
										sessionStorage.setItem(
												"bedAllocationFrom",
												"bedReserve");
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

							$scope.unreservetion = function() {
								console.log($scope.itemSelecteds);
								var age = $rootScope
										.getAge($scope.itemSelecteds.dob);
								$scope.patientFullName = $scope.itemSelecteds.pFirstName
										+ " "
										+ $scope.itemSelecteds.pMiddleName
										+ " "
										+ $scope.itemSelecteds.pLastName
										+ " "
										+ age
										+ "/"
										+ $scope.itemSelecteds.genderCode;
								$scope.uhidNum = $scope.itemSelecteds.uhidnumber;
								$scope.doctorFullName = 'Dr. '
										+ $scope.itemSelecteds.dFirstName + " "
										+ $scope.itemSelecteds.dMiddleName
										+ " " + $scope.itemSelecteds.dLastName;
								$scope.patientCategoryDesc = $scope.itemSelecteds.patientCategoryDesc;
								$scope.paymentEntitlementDesc = $scope.itemSelecteds.paymentEntitlementDesc;
								$scope.bedCategoryId = $scope.itemSelecteds.bedCategoryId;
								$scope.paymentEntitlementId = $scope.itemSelecteds.paymentEntitlementId;
								$scope.dateOfAdmission = $scope.itemSelecteds.datepicker;
								$scope.patientDischargeDate = $scope.itemSelecteds.datepickerPdd;
								$scope.isFlexiableDate = $scope.itemSelecteds.isFlexiableDate;

								$scope.unReserveModel = {
									admissionReasonId : 0,
									cancelReasonId : 0,
									unreserveReasonId : 0,
									paymentEntitlementId : $scope.itemSelecteds.paymentEntitlementId,
									patientCategoryId : $scope.itemSelecteds.patientCategoryId,
									admissionNoteId : $scope.itemSelecteds.admissionNoteId,
									doa : $scope.dateOfAdmission + ' 00:00:00',
									isFlexiableDateUN : $scope.isFlexiableDate,
									datepickerForNewDateOfAdmission : ''
								};

							}
							
							$scope.depositeMatrix = function() {
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
								$scope.patientFullName = $scope.itemSelecteds.pFirstName
										+ " "
										+ $scope.itemSelecteds.pMiddleName
										+ " "
										+ $scope.itemSelecteds.pLastName
										+ " "
										+ age
										+ "/"
										+ $scope.itemSelecteds.genderCode;
								$scope.uhidNum = $scope.itemSelecteds.uhidnumber;
								$scope.doctorFullName = 'Dr. '
										+ $scope.itemSelecteds.dFirstName + " "
										+ $scope.itemSelecteds.dMiddleName
										+ " " + $scope.itemSelecteds.dLastName;
								$scope.patientCategoryDesc = $scope.itemSelecteds.patientCategoryDesc;
								$scope.paymentEntitlementDesc = $scope.itemSelecteds.paymentEntitlementDesc;
								$scope.bedCategoryId = $scope.itemSelecteds.bedCategoryId;
								$scope.paymentEntitlementId = $scope.itemSelecteds.paymentEntitlementId;
								$scope.dateOfAdmission = $scope.itemSelecteds.datepicker;
								$scope.patientDischargeDate = $scope.itemSelecteds.datepickerPdd;
								$scope.isFlexiableDate = $scope.itemSelecteds.isFlexiableDate;

								$scope.unReserveModel = {
									admissionReasonId : 0,
									cancelReasonId : 0,
									unreserveReasonId : 0,
									paymentEntitlementId : $scope.itemSelecteds.paymentEntitlementId,
									patientCategoryId : $scope.itemSelecteds.patientCategoryId,
									admissionNoteId : $scope.itemSelecteds.admissionNoteId,
									doa : $scope.dateOfAdmission + ' 00:00:00',
									isFlexiableDateUN : $scope.isFlexiableDate,
									datepickerForNewDateOfAdmission : ''
								};
							}

							}
							

							$scope.insurance = function() {
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
								$scope.patientFullName = $scope.itemSelecteds.pFirstName
										+ " "
										+ $scope.itemSelecteds.pMiddleName
										+ " "
										+ $scope.itemSelecteds.pLastName
										+ " "
										+ age
										+ "/"
										+ $scope.itemSelecteds.genderCode;
								$scope.uhidNum = $scope.itemSelecteds.uhidnumber;
								$scope.doctorFullName = 'Dr. '
										+ $scope.itemSelecteds.dFirstName + " "
										+ $scope.itemSelecteds.dMiddleName
										+ " " + $scope.itemSelecteds.dLastName;
								$scope.patientCategoryDesc = $scope.itemSelecteds.patientCategoryDesc;
								$scope.paymentEntitlementDesc = $scope.itemSelecteds.paymentEntitlementDesc;
								$scope.bedCategoryId = $scope.itemSelecteds.bedCategoryId;
								$scope.paymentEntitlementId = $scope.itemSelecteds.paymentEntitlementId;
								$scope.dateOfAdmission = $scope.itemSelecteds.datepicker;
								$scope.patientDischargeDate = $scope.itemSelecteds.datepickerPdd;
								$scope.isFlexiableDate = $scope.itemSelecteds.isFlexiableDate;

								$scope.unReserveModel = {
									admissionReasonId : 0,
									cancelReasonId : 0,
									unreserveReasonId : 0,
									paymentEntitlementId : $scope.itemSelecteds.paymentEntitlementId,
									patientCategoryId : $scope.itemSelecteds.patientCategoryId,
									admissionNoteId : $scope.itemSelecteds.admissionNoteId,
									doa : $scope.dateOfAdmission + ' 00:00:00',
									isFlexiableDateUN : $scope.isFlexiableDate,
									datepickerForNewDateOfAdmission : ''
								};
							}

							}

							$scope.assignContract = function() {
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
								$scope.patientFullName = $scope.itemSelecteds.pFirstName
										+ " "
										+ $scope.itemSelecteds.pMiddleName
										+ " "
										+ $scope.itemSelecteds.pLastName
										+ " "
										+ age
										+ "/"
										+ $scope.itemSelecteds.genderCode;
								$scope.uhidNum = $scope.itemSelecteds.uhidnumber;
								$scope.doctorFullName = 'Dr. '
										+ $scope.itemSelecteds.dFirstName + " "
										+ $scope.itemSelecteds.dMiddleName
										+ " " + $scope.itemSelecteds.dLastName;
								$scope.patientCategoryDesc = $scope.itemSelecteds.patientCategoryDesc;
								$scope.paymentEntitlementDesc = $scope.itemSelecteds.paymentEntitlementDesc;
								$scope.bedCategoryId = $scope.itemSelecteds.bedCategoryId;
								$scope.paymentEntitlementId = $scope.itemSelecteds.paymentEntitlementId;
								$scope.dateOfAdmission = $scope.itemSelecteds.datepicker;
								$scope.patientDischargeDate = $scope.itemSelecteds.datepickerPdd;
								$scope.isFlexiableDate = $scope.itemSelecteds.isFlexiableDate;

								$scope.unReserveModel = {
									admissionReasonId : 0,
									cancelReasonId : 0,
									unreserveReasonId : 0,
									paymentEntitlementId : $scope.itemSelecteds.paymentEntitlementId,
									patientCategoryId : $scope.itemSelecteds.patientCategoryId,
									admissionNoteId : $scope.itemSelecteds.admissionNoteId,
									doa : $scope.dateOfAdmission + ' 00:00:00',
									isFlexiableDateUN : $scope.isFlexiableDate,
									datepickerForNewDateOfAdmission : ''
								};
							}

							}

							$scope.hideShowUnreserveDiv = function(type) {
								if (type == 'cancel') {
									$scope.unreserveFlag = false;
									$scope.cancelFlag = true;
									$scope.btnValue = "Refund Deposit";
								} else {
									$scope.unreserveFlag = true;
									$scope.cancelFlag = false;
									$scope.btnValue = "Reshedule";
								}
							}
							$scope.saveUnreserve = function() {
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								var data = {
									createdBy : $scope.userId,
									updatedBy : $scope.userId,
									organizationId : $scope.organizationId,
									unitId : $scope.unitId,
									admissionNoteId : $scope.unReserveModel.admissionNoteId,
									doa : $rootScope
											.getChangedFormatedDateShalse($scope.dateOfAdmission)
											+ ' 00:00:00',
									createdDate : $scope.currentDate,
									updatedDate : $scope.currentDate
								};
								if ($scope.canres == 'cancel') {

									data.isCancelReservation = 'Y';
									data.reasonForCancelation = $scope.unReserveModel.cancelReasonId;

								} else {
									var newDoaDate = $rootScope
											.getChangedFormatedDateShalse(document
													.getElementById('datepickerForNewDateOfAdmission').value);
									data.doa = $rootScope
											.getChangedFormatedDateShalse($scope.dateOfAdmission)
											+ ' 00:00:00';
									data.bedCategoryId = $scope.bedCategoryId;
									data.reasonForAdmission = $scope.unReserveModel.admissionReasonId;
									data.reasonForUnreserve = $scope.unReserveModel.unreserveReasonId;
									data.isFlexible = $scope.unReserveModel.isFlexiableDateUN;
									data.newDoa = newDoaDate + ' 00:00:00'
											data.paymentEntitlementId = $scope.unReserveModel.paymentEntitlementId,
											data.patientCategoryId = $scope.unReserveModel.patientCategoryId

								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ UNRESERVERESHEDULEPATIENT;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.reasonList = [];
													if (response.data.status == "success") {
														growl
																.success(
																		'Sucessfully!!!.',
																		{
																			title : 'Success!'
																		});
														$scope.init();
														angular.element(
																'#unreserve')
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
													$scope
															.initBedReservetionList(
																	$scope.organizationId,
																	$scope.offset,
																	$scope.noOfRecordsPerPage);
												});

							}

						} ]);
