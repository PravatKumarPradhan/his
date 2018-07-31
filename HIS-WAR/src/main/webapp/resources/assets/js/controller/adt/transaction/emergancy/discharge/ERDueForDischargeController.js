'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:ERDueForDischargeController
 * @description #ERDueForDischargeController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'ERDueForDischargeController',
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
							
							 $scope.dateOptions = {							 
								      formatYear: 'yyyy',
								      showWeeks: false
								  };
								 $scope.openDatePicker = function() {
									// alert("in");
								      $scope.datepicker.opened = true;
								    };
								    $scope.datepicker = {
										      opened: false
										    };

							/* init() function for form object create. */
							$scope.init = function() {

								$scope.dueForDischargeFlage = true;
								$(".selectedPageName").text("ER Due For Discharge");
								
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

								$scope.admittedPatient = {};
								$scope.itemSelecteds = {};

								var data = {
										organizationId : $scope.organizationId
								};
								
								$scope.cD = new Date();
								$scope.cD = moment($scope.cD)
										.format('DD-MM-YYYY');
								var commonObj = {
									unitId : $scope.unitId,
									organizationId : $scope.organizationId,
									pdd : $scope.cD
								};
								
								var URI = BASE_URL + ROOT_URL
								+ GETACTIVEDISCHARGETYPELIST;
						GenericService
								.serviceAction("POST", URI, data)
								.then(
										function(response) {
											$scope.dischargeTypeList = [];

											if (response.data.status == "success") {
												$scope.dischargeTypeList = response.data.listObject;

											}
										});
						
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

								
/*
								var URI = BASE_URL + ROOT_URL
										+ GETDISCHARGEADMITTEDPATIENTLIST;
								GenericService
										.serviceAction("POST", URI, commonObj)
										.then(
												function(response) {
													$scope.listOfAdmittedPatientList = [];
													$scope.admittedPatientList = [];

													if (response.data.status == "success") {
														$scope.filterDate = new Date();
														$scope.filterDate = moment($scope.filterDate)
																.format('DD/MM/YYYY');
														$scope.admittedPatientList = response.data.listObject;
														angular
																.forEach(
																		$scope.admittedPatientList,
																		function(
																				value,
																				index) {

																			var newArr = {};
																			newArr.specialityName = value.specialityName;
																			newArr.doctorFullName = value.dFirstName;
																			newArr.patientFullName = value.pFirstName;
																			newArr.reasonDesc = value.reasonDesc;
																			newArr.dob = value.dob;
																			newArr.datepicker = value.doa;
																			newArr.datepickerPdd = value.pdd;
																			newArr.genderCode = value.genderCode;
																			newArr.uhidnumber = value.uhidnumber;
																			newArr.patientId = value.patientId;
																			newArr.tPatientId = value.tPatientId;
																			newArr.paymentEntitlementDesc = value.paymentEntitlementDesc;
																			newArr.bedNumber = value.bedNumber;
																			newArr.admissionId = value.admissionId;
																			newArr.prefixCode = value.prefixCode;
																			newArr.identificationNo = value.identificationNo;
																			newArr.doctorId = value.doctorId;
																			newArr.bedId = value.bedId;
																			$scope.listOfAdmittedPatientList
																					.push(newArr);

																		});

													}
												});*/


							}
							

							$scope.init();
							// For Paginations Start
							$scope.setNoOfRecords = function() {
								$scope.initERAdmittedPatientList(
										$scope.organizationId, $scope.offset,
										$scope.noOfRecordsPerPage);
							};
							$scope.initERAdmittedPatientList = function(orgId,
									offset, noOfRecordsPerPage) {
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
										: 10;
								var URI = BASE_URL + ROOT_URL
										+ GETERADMITTEDPATIENTLIST;
								var URI1 = BASE_URL + ROOT_URL
										+ COUNTERADMITTEDPATIENT;

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

								// For Get ErPatient List Using Offset
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													//$scope.listOfERAdmittedPatientList = [];
													//$scope.ERadmittedPatientList = [];
													
													$scope.listOfAdmittedPatientList = [];
													$scope.ERadmittedPatientList = [];
													

													if (response.data.status == "success") {
														$scope.ERadmittedPatientList = response.data.listObject;
														angular
																.forEach(
																		$scope.ERadmittedPatientList,
																		function(
																				value,
																				index) {

																			var newArr = {};
																			newArr.doctorFullName = value.dFirstName;
																			newArr.patientFullName = value.patientName;
																			newArr.dob = value.dob;
																			newArr.datepicker = value.doa;
																			newArr.datepickerPdd = value.pdd;
																			newArr.genderCode = value.genderName;
																			newArr.uhidnumber = value.uhidnumber;
																			newArr.patientId = value.patientId;
																			newArr.tpatientId = value.tPatientId;
																			newArr.bedNumber = value.bedNumber;
																			newArr.admissionId = value.admissionId;
																			newArr.admissionDetailsId = value.admissionDetailsId;
																			newArr.bedId  = value.bedId;
																			newArr.doctorId = value.doctorId;
																			newArr.noOfHours = value.noOfHours;
																			$scope.listOfAdmittedPatientList
																					.push(newArr);

																		});

													}
												});

								// For Count ErPatient List
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

							$scope.getERAdmittedPatientList = function(orgId,
									offset, noOfRecordsPerPage) {
								console.log("offset", offset);
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
										: 10;
								var URI = BASE_URL + ROOT_URL
										+ GETERADMITTEDPATIENTLIST;
								var data = {
									organizationId : $scope.organizationId,
									unitId : $scope.unitId,
									offset : offset,
									noOfRecordsPerPage : noOfRecordsPerPage
								}

								console.log("URI", URI);
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													/*$scope.listOfERAdmittedPatientList = [];
													$scope.ERadmittedPatientList = [];*/
													$scope.listOfAdmittedPatientList = [];
													$scope.ERadmittedPatientList = [];
													
													if (response.data.status == "success") {
														$scope.ERadmittedPatientList = response.data.listObject;
														angular
																.forEach(
																		$scope.ERadmittedPatientList,
																		function(
																				value,
																				index) {
																			var newArr = {};
																			newArr.doctorFullName = value.dFirstName;
																			newArr.patientFullName = value.patientName;
																			newArr.dob = value.dob;
																			newArr.datepicker = value.doa;
																			newArr.datepickerPdd = value.pdd;
																			newArr.genderCode = value.genderName;
																			newArr.uhidnumber = value.uhidnumber;
																			newArr.patientId = value.patientId;
																			newArr.tpatientId = value.tPatientId;
																			newArr.bedNumber = value.bedNumber;
																			newArr.admissionId = value.admissionId;
																			newArr.admissionDetailsId = value.admissionDetailsId;
																			newArr.bedId  = value.bedId;
																			$scope.listOfERAdmittedPatientList
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
									$scope.getERAdmittedPatientList(
											$scope.organizationId,
											$scope.pager.startIndex,
											$scope.pager.pageSize);
								}
							}

							$scope.initERAdmittedPatientList(
									$scope.organizationId, $scope.offset,
									$scope.noOfRecordsPerPage);

							// For Paginations End
							
						

							$scope.popupDischarge = function(item)
							{
								$scope.dischargeTypeId =0;
								$scope.itemForDischargePatient = item;
							}
							
							$scope.dueForDischarge = function(item)
							{
								$scope.note="";
								$scope.reasonId = 0;
								$scope.itemForDischargePatient = item;
								
								 var arrayN = item.datepickerPdd.split(' ');
								 var array = arrayN[0].split('/');
								var newDateDoa =array[1]+'/'+array[0]+'/'+array[2];
								newDateDoa = newDateDoa+' '+arrayN[1];
								//alert(newDateDoa);
								var now = new Date(newDateDoa);
								//alert(now);
								//alert(item.noOfHours);
								now.setHours(now.getHours()+4);
								now.setMinutes(now.getMinutes()+0);
								//alert(now);
								$scope.noOfHours = moment(now).format('DD/MM/YYYY HH:mm:ss');
								$scope.noOfHoursFormat = moment(now).format('DD-MM-YYYY HH:mm:ss');
								
							}

							$scope.saveDischargeRequest = function() {
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								var data = {
									organizationId : $scope.organizationId,
									unitId : $scope.unitId,
									dischargeTypeId : $scope.dischargeTypeId,
									admissionId : $scope.itemForDischargePatient.admissionId,
									doctorId :  $scope.itemForDischargePatient.doctorId,
									createdBy : $scope.userId,
									updatedBy : $scope.userId,
									status : 'A',
									createdDate : $scope.currentDate,
									updatedDate : $scope.currentDate,
									destinationId :$scope.itemForDischargePatient.bedId,
									dischargeStatusId: 1,
									visitTypeId : 4
								};
								console.log(data);
								//return false;
								var URI = BASE_URL + ROOT_URL
										+ ADDDISCHARGEREQUEST;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {

													if (response.data.status == "success") {
														growl
																.success(
																		'Discharge Request Raised sucessfully!!!.',
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
												});
							}
							
							$scope.updateExtendPdd = function()
							{
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								var data = {
									organizationId : $scope.organizationId,
									unitId : $scope.unitId,
									reasonId : $scope.reasonId,
									admissionId : $scope.itemForDischargePatient.admissionId,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate,
									pdd : $scope.noOfHoursFormat,
									noteCancel : $scope.note
								};
								console.log(data);
								//return false;
								var URI = BASE_URL + ROOT_URL
										+ UPDATEDISCHARGEPDD;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {

													if (response.data.status == "success") {
														growl
																.success(
																		'Discharge Request Raised sucessfully!!!.',
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
												});
							}

						

						} ]);
