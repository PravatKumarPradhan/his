'use strict';

/**
 * @Author By Vivek Satle
 * @name myApp.controller:erPatientListController
 * @description #erPatientListController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'erPatientListController',
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
							
							/*
							 * var now = new Date();
							 * now.setHours(now.getHours()+4);
							 * now.setMinutes(now.getMinutes()+0);
							 * $scope.ProductionDate = now; // alert(
							 * $scope.ProductionDate);
							 * //alert($scope.ProductionDate.getHours());
							 * 
							 * var now1 = new Date(); var nextDay = new
							 * Date(now1); var tommorowDate =
							 * nextDay.setDate(now.getDate()+1); //
							 * nextDay.setHours(nextDay.getHours()+10); //
							 * nextDay.setMinutes(nextDay.getMinutes()+30);
							 * alert(moment(tommorowDate).format('DD-MM-YYYY
							 * HH:mm:ss'));
							 */

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
								
								
								//$rootScope.billingFlag = false;
								angular.element(document.getElementById('billing'))[0].disabled = true;
								angular.element(document.getElementById('damaContent'))[0].disabled = true;
								angular.element(document.getElementById('vacateBed'))[0].disabled = true;
								angular.element(document.getElementById('deathId'))[0].disabled = true;
								angular.element(document.getElementById('dischargeSlip'))[0].disabled = true;
								angular.element(document.getElementById('dischargeSummary'))[0].disabled = true;
								angular.element(document.getElementById('dischargeMedication'))[0].disabled = true;
								$scope.pendingServicePopupFlag =false;
								
								$scope.dischargePatient = {};
								$scope.itemSelecteds = {};
								$scope.visitorPassPopUpFlag = false;
								$scope.deathPdfViewPopupFlag = false;
								$scope.damaPdfViewPopupFlag = false;
								$scope.dischargePdfViewPopupFlag = false;
								$scope.dischargeSlipPdfViewPopupFlag   = false;
								
								/** Variables for pagination */
								$scope.offset = 0;
								$scope.noOfRecordsPerPage = 10;
								$scope.totalNoOfRecords;
								$scope.searchKeyword;
								$scope.commonListCount;

								$rootScope.loginpage = true;

								// for popup
								$scope.showModal = false;
								$scope.buttonClicked = "";
								$scope.popUpFlag = true;
								// end for popup

								$scope.itemSelecteds = {};

								var data = {};
								var commonObj = {
									unitId : $scope.unitId,
									organizationId : $scope.organizationId
								};
								
								var URI = BASE_URL + ROOT_URL
								+ GETDISCHARGEPATIENTLISTFORER;
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
																	newArr.dFirstName = value.dFirstName;
																	newArr.patientName = value.pFirstName;
																	newArr.reasonDesc = value.reasonDesc;
																	newArr.dob = value.dob;
																	newArr.datepicker = value.doa;
																	newArr.datepickerPdd = value.pdd;
																	newArr.genderCode = value.genderCode;
																	newArr.uhidnumber = value.uhidnumber;
																	newArr.patientId = value.patientId;
																	newArr.paymentEntitlementDesc = value.paymentEntitlementDesc;
																	newArr.bedNumber = value.bedNumber;
																	newArr.admissionId = value.admissionId;
																	newArr.prefixCode = value.prefixCode;
																	newArr.identificationNo = value.identificationNo;
																	newArr.dischargeId = value.dischargeId;
																	newArr.futureDischargeDate = value.futureDischargeDate;
																	newArr.dischargeStatusId = value.dischargeStatusId;
																	newArr.dischargeTypeId = value.dischargeTypeId;
																	newArr.dischargeTypeName = value.dischargeTypeName;
																	newArr.dischargeDesc = value.dischargeDesc;
																	newArr.tpatientId = value.tPatientId;
																	newArr.remarkDischarge = value.remarkDischarge;
																	newArr.dischargeDesc = value.dischargeDesc
																	$scope.listOfAdmittedPatientList
																			.push(newArr);

																});

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
						

							}

							$scope.init();

							/*// For Paginations Start
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
													$scope.listOfERAdmittedPatientList = [];
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
																			newArr.dFirstName = value.dFirstName;
																			newArr.patientName = value.patientName;
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
																			$scope.listOfERAdmittedPatientList
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
													$scope.listOfERAdmittedPatientList = [];
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
																			newArr.dFirstName = value.dFirstName;
																			newArr.patientName = value.patientName;
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
*/
							// For Paginations End
							
							$scope.pendingServies = function()
							{

								if (Object.keys($scope.itemSelecteds).length === 0) {
									growl
											.error(
													'Please select atleast one patient!!!.',
													{
														title : 'Error!'
													});
								}
								else{
								console.log($scope.itemSelecteds);
								
								var data = {
										organizationId : $scope.organizationId,
										unitId : $scope.unitId,
										admissionId : $scope.itemSelecteds.admissionId,
										//patientId : $scope.itemSelecteds.patientId
								};
								console.log(data);
								//return false;
								var URI = BASE_URL + ROOT_URL
								+ GETPATIENTPENDINGSERVICESLIST;
						GenericService
								.serviceAction("POST", URI, data)
								.then(
										function(response) {
											$scope.pendingServicesList = [];

											if (response.data.status == "success") {
												$scope.pendingServicesList = response.data.listObject;
											
												if($scope.pendingServicesList.length == 0)
													{
													/*$rootScope.billingFlag = false;
													alert($rootScope.billingFlag);*/
													angular.element(document.getElementById('billing'))[0].disabled = false;

													angular.element(document.getElementById('dischargeSlip'))[0].disabled = false;
													angular.element(document.getElementById('dischargeSummary'))[0].disabled = false;
													angular.element(document.getElementById('dischargeMedication'))[0].disabled = false;
													}
												else
													{
													/*$rootScope.billingFlag = true;
													alert($rootScope.billingFlag);*/
													angular.element(document.getElementById('billing'))[0].disabled = true;
													}
												
												
												  switch ($scope.itemSelecteds.dischargeTypeId) {
										            case 1:
										               // alert("Selected Case Number is 1");
										                break;
										            case 2:
										               ///alert("Selected Case Number is 2");
										            	angular.element(document.getElementById('damaContent'))[0].disabled = false;
										                break;
										            case 3:
										               // alert("Selected Case Number is 2");
										            	angular.element(document.getElementById('deathId'))[0].disabled = false;
										                break;
										            case 4:
										                //alert("Selected Case Number is 2");
										                break;
										            default:

										        }

											}
										});
							}
						
							}

							$scope.search = {
								allocationTypeId : 0,
								patientId : 0,
								tPatientId : 0,
								admissionId : 0
							}

							$scope.searchGroupList = function(keyword) {

								var data = {
									patientName : keyword,
									unitId : $scope.unitId,
									// visitType :
									// $scope.selectedPageNameForAdmissionNote,
									organizationId : $scope.organizationId
								}
								console.log(data);

								var URI = BASE_URL + ROOT_URL
										+ GETERPATIENTBYKEYWORD;
								return GenericService.serviceAction("POST",
										URI, data).then(function(response) {

									if (response.data.status == "success") {
										return response.data.listObject;

									}
								});

							}

							$scope.selectPatientId = function(info, model,
									label) {
								$scope.search.patientId = info.patientId
								$scope.search.tPatientId = info.tPatientId,
								$scope.search.admissionId = info.admissionId
							}

							$scope.getERAdmittedPatientByAllocationPatientId = function() {
								var data = {
									organizationId : $scope.organizationId,
									unitId : $scope.unitId,
									erBedTypeAllocation : $scope.search.allocationTypeId,
									admissionId:$scope.search.admissionId 
								};

								if ($scope.search.patientId != null) {
									data.patientId = $scope.search.patientId;
								} else {
									data.patientId = 0;
								}

								if ($scope.search.tPatientId != null) {
									data.tPatientId = $scope.search.tPatientId;
								} else {
									data.tPatientId = 0;
								}
								console.log(data);
								
								var URI = BASE_URL
										+ ROOT_URL
										+ GETERADMITTEDPATIENTLISTBYALLOCATIONPATIENT;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
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
																			newArr.specialityName = value.specialityName;
																			newArr.dFirstName = value.dFirstName;
																			newArr.patientName = value.pFirstName;
																			newArr.reasonDesc = value.reasonDesc;
																			newArr.dob = value.dob;
																			newArr.datepicker = value.doa;
																			newArr.datepickerPdd = value.pdd;
																			newArr.genderCode = value.genderCode;
																			newArr.uhidnumber = value.uhidnumber;
																			newArr.patientId = value.patientId;
																			newArr.paymentEntitlementDesc = value.paymentEntitlementDesc;
																			newArr.bedNumber = value.bedNumber;
																			newArr.admissionId = value.admissionId;
																			newArr.prefixCode = value.prefixCode;
																			newArr.identificationNo = value.identificationNo;
																			newArr.dischargeId = value.dischargeId;
																			newArr.futureDischargeDate = value.futureDischargeDate;
																			newArr.dischargeStatusId = value.dischargeStatusId;
																			newArr.dischargeTypeId = value.dischargeTypeId;
																			newArr.dischargeTypeName = value.dischargeTypeName;
																			newArr.dischargeDesc = value.dischargeDesc;
																			newArr.tpatientId = value.tPatientId;
																			newArr.remarkDischarge = value.remarkDischarge;
																			newArr.dischargeDesc = value.dischargeDesc
																			$scope.listOfAdmittedPatientList
																					.push(newArr);

																		});
													}
													
													/*$scope
															.initRelationMasterList(
																	$scope.organizationId,
																	$scope.offset,
																	$scope.noOfRecordsPerPage);*/
												});

							}

							$scope.selectPatient = function(item) {
								
								angular.element(document.getElementById('billing'))[0].disabled = true;
								angular.element(document.getElementById('damaContent'))[0].disabled = true;
								angular.element(document.getElementById('vacateBed'))[0].disabled = true;
								angular.element(document.getElementById('deathId'))[0].disabled = true;
								angular.element(document.getElementById('dischargeSlip'))[0].disabled = true;
								angular.element(document.getElementById('dischargeSummary'))[0].disabled = true;
								angular.element(document.getElementById('dischargeMedication'))[0].disabled = true;

								$scope.itemSelecteds = [];
								// If checkbox is checked
								if ($scope.dischargePatient) {
									$scope.itemSelecteds = item;
									$scope.pendingServicePopupFlag =true;
									$scope.deathPdfViewPopupFlag = true;
									$scope.damaPdfViewPopupFlag = true;
									$scope.dischargePdfViewPopupFlag = true;
									$scope.dischargeSlipPdfViewPopupFlag = true;
									$scope.pendingServies();

								}
							}
							
							$scope.commonFuntion = function() {
								console.log($scope.itemSelecteds);
								if (Object.keys($scope.itemSelecteds).length === 0) {
									growl
											.error(
													'Please select atleast one patient!!!.',
													{
														title : 'Error!'
													});
								}
								else{
								
								if($scope.itemSelecteds.patientId > 0)
								{
								var age = $rootScope
								.getAge($scope.itemSelecteds.dob);
								$scope.patientFullName =$scope.itemSelecteds.patientName
								+ " "
								+age
								+ "/"
								+ $scope.itemSelecteds.genderCode;
								}
							else
								{
								$scope.patientFullName = $scope.itemSelecteds.patientName
								+ " "
								+ $scope.itemSelecteds.dob
								+ "/"
								+ $scope.itemSelecteds.genderCode;
								}
								$scope.uhidNum = $scope.itemSelecteds.uhidnumber;
								$scope.doctorFullName =$scope.itemSelecteds.dFirstName;
								$scope.bedNumber = $scope.itemSelecteds.bedNumber;
								$scope.dateOfAdmission =$scope.itemSelecteds.datepicker;
								$scope.patientDischargeDate = $scope.itemSelecteds.datepickerPdd;
								$scope.identificationNo = $scope.itemSelecteds.identificationNo;
								$scope.admissionId = $scope.itemSelecteds.admissionId;

							}
							}
							
							
							$scope.finalDischarge = function()
							{
								$state.go('finalDischarge');
							}
							
$scope.cancelPendingService = function(reasonId,admissionId,patientServiceOrderId) {
								
								//alert(reasonId+'dd'+admissionId+'ggg'+patientServiceOrderId);
								//return false;
								/*if ($scope.popUpFlag) {
									$scope.showModal = !$scope.showModal;

								} 
								else{*/
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								var data = {
									organizationId : $scope.organizationId,
									unitId : $scope.unitId,
									patientSevicesOrderId :patientServiceOrderId,
									reasonId : reasonId,
									admissionId : admissionId,
									orderStatusId:4,
									updatedBy : $scope.userId,
									status : 'A',
									updatedDate : $scope.currentDate

								};
								console.log(data);
								// return false;
								var URI = BASE_URL + ROOT_URL
										+ CANCELPAENDINGSERVICEORDER;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {

													if (response.data.status == "success") {
														growl
																.success(
																		'Service Cancel sucessfully!!!.',
																		{
																			title : 'Success!'
																		});
														$scope.pendingServies();
													} else {
														growl
																.error(
																		'Something wrongs!!!!.',
																		{
																			title : 'Error!'
																		});
													}
												});
								/*}*/
							}

							$scope.redirectMedication = function()
							{
								$state.go('erDischargeMedicationHandover');
							}
							
							$scope.redirectBilling = function()
							{
								$state.go('ERCharges.serviceDetailsForIPDCharge');
							}

							/*
							 * $scope.visitorPass = function() {
							 * 
							 * if (Object.keys($scope.itemSelecteds).length ===
							 * 0) { growl .error( 'Please select atleast one
							 * patient!!!.', { title : 'Error!' }); } else {
							 * 
							 * var age = $rootScope
							 * .getAge($scope.itemSelecteds.dob);
							 * $scope.patientFullName =
							 * $scope.itemSelecteds.pFirstName + " " +
							 * $scope.itemSelecteds.pMiddleName + " " +
							 * $scope.itemSelecteds.pLastName + " " + age + "/" +
							 * $scope.itemSelecteds.genderCode; $scope.uhidNum =
							 * $scope.itemSelecteds.uhidnumber;
							 * $scope.doctorFullName = 'Dr. ' +
							 * $scope.itemSelecteds.dFirstName + " " +
							 * $scope.itemSelecteds.dMiddleName + " " +
							 * $scope.itemSelecteds.dLastName; $scope.bedNumber =
							 * $scope.itemSelecteds.bedNumber;
							 * $scope.dateOfAdmission =
							 * $scope.itemSelecteds.datepicker;
							 * $scope.patientDischargeDate =
							 * $scope.itemSelecteds.datepickerPdd; $scope
							 * .getPassIssuedList($scope.itemSelecteds.admissionId); } }
							 */

							/*
							 * $scope.savePassIssue = function() {
							 * $scope.currentDate = new Date();
							 * $scope.currentDate = moment($scope.currentDate)
							 * .format('DD-MM-YYYY HH:mm:ss');
							 * 
							 * var pdd = $rootScope
							 * .getChangedFormatedDateShalse($scope.itemSelecteds.datepickerPdd);
							 * var data = { organizationId :
							 * $scope.organizationId, unitId : $scope.unitId,
							 * visitorPassTypeId : $scope.visitorPassTypeId,
							 * passNumber : $scope.passNumber, admissionId :
							 * $scope.itemSelecteds.admissionId, expiryDate :
							 * pdd + ' 00:00:00', createdBy : $scope.userId,
							 * updatedBy : $scope.userId, status : 'A',
							 * visitorPassStatus : 'I', createdDate :
							 * $scope.currentDate, updatedDate :
							 * $scope.currentDate }; console.log(data); //
							 * return false; var URI = BASE_URL + ROOT_URL +
							 * ADDVISITORPASSISSUE; GenericService
							 * .serviceAction("POST", URI, data) .then(
							 * function(response) {
							 * 
							 * if (response.data.status == "success") { growl
							 * .success( 'Pass Issued sucessfully!!!.', { title :
							 * 'Success!' }); $scope
							 * .$broadcast('show-errors-reset'); $scope
							 * .getPassIssuedList($scope.itemSelecteds.admissionId); }
							 * else { growl .error( 'Something wrongs!!!!.', {
							 * title : 'Error!' }); } }); }
							 */

						} ]);
