'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:mapOfBedERController
 * @description #mapOfBedERController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'mapOfBedERController',
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
						'$timeout',
						'PagerService',
						function($scope, $http, $localStorage, $sessionStorage,
								$cookies, $rootScope, GenericService, growl,
								$state,$timeout,PagerService) {

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

								/** Variables for pagination */
								$scope.offset = 0;
								$scope.noOfRecordsPerPage = 10;
								$scope.totalNoOfRecords;
								$scope.searchKeyword;
								$scope.commonListCount;

								$rootScope.loginpage = true;
								
								$scope.patientSearchDiv=false;
								$rootScope.linkHideShow=false;

								// for popup
								$scope.showModal = false;
								$scope.buttonClicked = "";
								$scope.popUpFlag = true;
								// end for popup
								
								$scope.bedsearch = {
										patientId : 0,
										tPatientId : 0,
										erBedTypeAllocation : 0,
										organizationId : $scope.organizationId,
										unitId : $scope.unitId,

									}
								$scope.groupName = "";

								$scope.itemSelecteds = {};

								var data = {};
								var commonObj = {
									unitId : $scope.unitId,
									organizationId : $scope.organizationId
								};
								
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
						+ GETERMAPOFBEDLIST;
				GenericService
						.serviceAction("POST", URI, commonObj)
						.then(
								function(response) {
									$scope.patientBedList = [];

									if (response.data.status == "success") {
										$scope.patientBedListTEMP = response.data.listObject;
										
										angular .forEach($scope.patientBedListTEMP, function( value, index) {
											      var newArr = {};
											      newArr.patientId = value.patientId;
											      newArr.dob = value.dob;
											      newArr.pFirstName = value.patientName;
											      newArr.genderCode = value.genderName;
											      newArr.uhidnumber = value.uhidnumber;
											      newArr.doa = value.doa;
											      newArr.pdd = value.pdd;
											      newArr.dFirstName = value.dFirstName;
											      newArr.paymentEntitlementDesc = value.paymentEntitlementDesc;
											      newArr.bedNumber = value.bedNumber;
											      newArr.admissionDetailsId = value.admissionDetailsId;
											      newArr.admissionId  = value.admissionId;
											      newArr.admissionNumber = value.admissionNumber;
											      newArr.bedId = value.bedId;
											      newArr.dFirstName  = value.dFirstName;
											      newArr.tPatientId = value.tPatientId;
											      newArr.visitTypeId = value.visitTypeId;
											      newArr.floorId = value.floorId;
											    newArr.patientCategoryId = value.patientCategoryId
											    newArr.prefixId  = value.prefixId;
											    newArr.genderId = value.genderId;
											      $scope.patientBedList.push(newArr);
												  });
										
										

									}
								});

							}

							$scope.init();
							
							$scope.searchGroupList = function(keyword) {

								var data = {
									firstName : keyword,
									unitId : $scope.unitId,
									// visitType :
									// $scope.selectedPageNameForAdmissionNote,
									organizationId : $scope.organizationId
								}
								console.log(data);

								var URI = BASE_URL + ROOT_URL
										+ GETPATIENTBYKEYWORD;
								return GenericService.serviceAction("POST",
										URI, data).then(function(response) {

									if (response.data.status == "success") {
										// alert("ff");
										// console.log(response.data.listObject);
										return response.data.listObject;

									}
								});
								// return data="[{name:'test'}]";

							}
							
							$scope.selectPatientIdOnSearch = function(info, model,
									label) {
									if(info.patientId != null)
									$scope.bedsearch.patientId  = info.patientId;
									if(info.tPatientId !=null)
									$scope.bedserach.tPatientId = info.tPatientId;
							}
							
							
							$scope.patientSearchByMultipleCriteria = function() {

								// $scope.bedsearch.isVirtual =
								// ($scope.bedsearch.isVirtual == true) ? "Y":
								// "N";
								console.log($scope.bedsearch);
								//return false;
								var URI = BASE_URL + ROOT_URL
										+ GETERADMITTEDPATIENTBYMULTIPLECRITERIA;
								GenericService
										.serviceAction("POST", URI,
												$scope.bedsearch)
										.then(
												function(response) {
													$scope.patientBedList =[];
													if (response.data.status == "success") {
														
														$scope.patientBedListTEMP = response.data.listObject;
														
														angular .forEach($scope.patientBedListTEMP, function( value, index) {
															      var newArr = {};
															      newArr.patientId = value.patientId;
															      newArr.dob = value.dob;
															      newArr.pFirstName = value.patientName;
															      newArr.genderCode = value.genderName;
															      newArr.uhidnumber = value.uhidnumber;
															      newArr.doa = value.doa;
															      newArr.pdd = value.pdd;
															      newArr.dFirstName = value.dFirstName;
															      newArr.paymentEntitlementDesc = value.paymentEntitlementDesc;
															      newArr.bedNumber = value.bedNumber;
															      newArr.admissionDetailsId = value.admissionDetailsId;
															      newArr.admissionId  = value.admissionId;
															      newArr.admissionNumber = value.admissionNumber;
															      newArr.bedId = value.bedId;
															      newArr.dFirstName  = value.dFirstName;
															      newArr.tPatientId = value.tPatientId;
															      newArr.visitTypeId = value.visitTypeId;
															      newArr.floorId = value.floorId;
															    newArr.patientCategoryId = value.patientCategoryId
															    newArr.prefixId  = value.prefixId;
															    newArr.genderId = value.genderId;
															      $scope.patientBedList.push(newArr);
																  });

														$scope.bedsearch = {
																patientId : 0,
																tPatientId : 0,
																erBedTypeAllocation : 0,
																organizationId : $scope.organizationId,
																unitId : $scope.unitId,

															}
														$scope.groupName = "";

													}
												});

							}
							
							
							$scope.popupData = function(item)
							{
								
								if(item.patientId > 0)
									{
									 var age = $rootScope.getAge(item.dob);
									
									 $scope.patientFullName =item.pFirstName+' '+age+'/'+item.genderCode; 
									}
								else
									{
									 $scope.patientFullName =item.pFirstName+' '+item.dob+'/'+item.genderCode;
									}
								$scope.uhidNum = item.uhidnumber;
								$scope.dateOfAdmission = item.doa;
								$scope.patientDischargeDate = item.pdd;
								$scope.bedNumber = item.bedNumber;
								$scope.doctorFullName = item.dFirstName;
							
								sessionStorage
								.setItem("admissionNoteObjER",JSON.stringify(item));
								
							}
							
							$scope.callAdmissionNote = function()
							{
								angular
								.element(
										'#patientServices')
								.modal('hide');
								
								//$rootScope.getAdmissionNote('ER');
								$timeout( function()
										{ 
									$rootScope.getAdmissionNote('ER');
									}, 2000);
								
							}

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
									offset : offset,
									noOfRecordsPerPage : noOfRecordsPerPage
								}

								var data1 = {
									organizationId : $scope.organizationId
								}

								// For Get ErPatient List Using Offset
								/*
								 * GenericService .serviceAction("POST", URI,
								 * commonObj) .then( function(response) {
								 * $scope.listOfERAdmittedPatientList = [];
								 * $scope.ERadmittedPatientList = [];
								 * 
								 * if (response.data.status == "success") {
								 * $scope.ERadmittedPatientList =
								 * response.data.listObject; angular .forEach(
								 * $scope.ERadmittedPatientList, function(
								 * value, index) {
								 * 
								 * var newArr = {}; newArr.specialityName =
								 * value.specialityName; newArr.dFirstName =
								 * value.dFirstName; newArr.dMiddleName =
								 * value.dMiddleName; newArr.dFirstName =
								 * value.dFirstName; newArr.dLastName =
								 * value.dLastName; newArr.pFirstName =
								 * value.pFirstName; newArr.pMiddleName =
								 * value.pMiddleName; newArr.pLastName =
								 * value.pLastName; newArr.reasonDesc =
								 * value.reasonDesc; newArr.dob = value.dob;
								 * newArr.datepicker = value.doa;
								 * newArr.datepickerPdd = value.pdd;
								 * newArr.genderCode = value.genderCode;
								 * newArr.uhidnumber = value.uhidnumber;
								 * newArr.patientId = value.patientId;
								 * newArr.paymentEntitlementDesc =
								 * value.paymentEntitlementDesc;
								 * newArr.bedNumber = value.bedNumber;
								 * newArr.admissionId = value.admissionId;
								 * newArr.prefixCode = value.prefixCode;
								 * newArr.identificationNo =
								 * value.identificationNo;
								 * $scope.listOfERAdmittedPatientList
								 * .push(newArr);
								 * 
								 * });
								 *  } });
								 */

								// For Count ErPatient List
								/*
								 * GenericService .serviceAction("POST", URI1,
								 * data1) .then( function(response) { if
								 * (response.data.status == "success") {
								 * console.log(response.data);
								 * $scope.commonListCount =
								 * response.data.object; $scope.setPage(1,
								 * false);
								 *  } });
								 */
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
									offset : offset,
									noOfRecordsPerPage : noOfRecordsPerPage
								}

								console.log("URI", URI);
								/*
								 * GenericService .serviceAction("POST", URI,
								 * commonObj) .then( function(response) {
								 * $scope.listOfERAdmittedPatientList = [];
								 * $scope.ERadmittedPatientList = [];
								 * 
								 * if (response.data.status == "success") {
								 * $scope.ERadmittedPatientList =
								 * response.data.listObject; angular .forEach(
								 * $scope.ERadmittedPatientList, function(
								 * value, index) {
								 * 
								 * var newArr = {}; newArr.specialityName =
								 * value.specialityName; newArr.dFirstName =
								 * value.dFirstName; newArr.dMiddleName =
								 * value.dMiddleName; newArr.dFirstName =
								 * value.dFirstName; newArr.dLastName =
								 * value.dLastName; newArr.pFirstName =
								 * value.pFirstName; newArr.pMiddleName =
								 * value.pMiddleName; newArr.pLastName =
								 * value.pLastName; newArr.reasonDesc =
								 * value.reasonDesc; newArr.dob = value.dob;
								 * newArr.datepicker = value.doa;
								 * newArr.datepickerPdd = value.pdd;
								 * newArr.genderCode = value.genderCode;
								 * newArr.uhidnumber = value.uhidnumber;
								 * newArr.patientId = value.patientId;
								 * newArr.paymentEntitlementDesc =
								 * value.paymentEntitlementDesc;
								 * newArr.bedNumber = value.bedNumber;
								 * newArr.admissionId = value.admissionId;
								 * newArr.prefixCode = value.prefixCode;
								 * newArr.identificationNo =
								 * value.identificationNo;
								 * $scope.listOfERAdmittedPatientList
								 * .push(newArr);
								 * 
								 * });
								 *  } });
								 */
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

							$scope.search = {
								allocationTypeId : 0,
								patientId : 0
							}

							$scope.searchGroupList = function(keyword) {

								var data = {
									firstName : keyword,
									unitId : $scope.unitId,
									// visitType :
									// $scope.selectedPageNameForAdmissionNote,
									organizationId : $scope.organizationId
								}
								console.log(data);

								var URI = BASE_URL + ROOT_URL
										+ GETPATIENTBYKEYWORD;
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
							}

							$scope.getERAdmittedPatientByAllocationPatientId = function() {
								var data = {
									organizationId : $scope.organizationId,
									unitId : $scope.unitId,
									allocationType : $scope.search.allocationTypeId,
									patientId : $scope.search.patientId
								};
								console.log(data);
								/*
								 * var URI = BASE_URL + ROOT_URL +
								 * GETERADMITTEDPATIENTLISTBYALLOCATIONPATIENT;
								 * GenericService .serviceAction("POST", URI,
								 * data) .then( function(response) {
								 * $scope.listOfAdmittedPatientList = [];
								 * $scope.admittedPatientList = [];
								 * 
								 * if (response.data.status == "success") {
								 * $scope.admittedPatientList =
								 * response.data.listObject; angular .forEach(
								 * $scope.admittedPatientList, function( value,
								 * index) {
								 * 
								 * var newArr = {}; newArr.specialityName =
								 * value.specialityName; newArr.dFirstName =
								 * value.dFirstName; newArr.dMiddleName =
								 * value.dMiddleName; newArr.dFirstName =
								 * value.dFirstName; newArr.dLastName =
								 * value.dLastName; newArr.pFirstName =
								 * value.pFirstName; newArr.pMiddleName =
								 * value.pMiddleName; newArr.pLastName =
								 * value.pLastName; newArr.reasonDesc =
								 * value.reasonDesc; newArr.dob = value.dob;
								 * newArr.datepicker = value.doa;
								 * newArr.datepickerPdd = value.pdd;
								 * newArr.genderCode = value.genderCode;
								 * newArr.uhidnumber = value.uhidnumber;
								 * newArr.patientId = value.patientId;
								 * newArr.paymentEntitlementDesc =
								 * value.paymentEntitlementDesc;
								 * newArr.bedNumber = value.bedNumber;
								 * newArr.admissionId = value.admissionId;
								 * newArr.prefixCode = value.prefixCode;
								 * newArr.identificationNo =
								 * value.identificationNo;
								 * $scope.listOfAdmittedPatientList
								 * .push(newArr);
								 * 
								 * });
								 *  }
								 * //$scope.initRelationMasterList($scope.organizationId,
								 * $scope.offset, $scope.noOfRecordsPerPage);
								 * });
								 */
							}

							$scope.selectPatient = function(item) {

								$scope.itemSelecteds = [];
								// If checkbox is checked
								if ($scope.admittedPatient) {
									$scope.itemSelecteds = item;
									$scope.visitorPassPopUpFlag = true;

								}
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
							 * .getPassIssuedList($scope.itemSelecteds.admissionId);
							 *  }
							 *  }
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
							 * $scope.currentDate
							 *  }; console.log(data); // return false; var URI =
							 * BASE_URL + ROOT_URL + ADDVISITORPASSISSUE;
							 * GenericService .serviceAction("POST", URI, data)
							 * .then( function(response) {
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
