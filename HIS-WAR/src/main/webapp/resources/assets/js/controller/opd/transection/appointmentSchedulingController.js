'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:appointmentSchedulingController
 * @description #appointmentSchedulingController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'appointmentSchedulingController',
				[
						'$scope',
						'$cookies',
						'$rootScope',
						'GenericService',
						'BillingGenericService',
						'$state',
						'$timeout',
						'growl',
						function($scope, $cookies, $rootScope, GenericService,
								BillingGenericService, $state, $timeout,growl) {
							
							
							
							//  alert(first_day.getDate() + '.' +  first_day.getMonth()  + '.' + first_day.getFullYear());
							//  alert(last_day.getDate()  + '.' +  first_day.getMonth()   + '.' + last_day.getFullYear());
							  
							//alert();
							/*var getDaysArray = function(year, month) {
								  var names = [ 'sun', 'mon', 'tue', 'wed', 'thu', 'fri', 'sat' ];
							  var date = new Date(year, month - 1, 1);
							  var result = [];
							  while (date.getMonth() == month - 1) {
							    result.push(date.getDate() + "-" + date.getDay());
							    date.setDate(date.getDate() + 1);
							  }
							  return result;
							}
							$scope.finalDateArray = getDaysArray(2018,'03');
							console.log($scope.finalDateArray);*/

							//Test Start
							/*function getDaysInMonth(month, year) {
							     // Since no month has fewer than 28 days
							     var date = new Date(year, month, 1);
							     var days = [];
							     console.log('month', month, 'date.getMonth()', date.getMonth())
							     while (date.getMonth() === month) {
							        days.push(new Date(date));
							        date.setDate(date.getDate() + 1);
							     }
							     return days;
							}
							    
							console.log(getDaysInMonth(2, 2018));
							return false;*/
							/*//2 nd test
							function endFirstWeek(firstDate, firstDay) {
							    if (! firstDay) {
							        return 7 - firstDate.getDay();
							    }
							    if (firstDate.getDay() < firstDay) {
							        return firstDay - firstDate.getDay();
							    } else {
							        return 7 - firstDate.getDay() + firstDay;
							    }
							}

							function getWeeksStartAndEndInMonth(month, year, start) {
							    let weeks = [],
							        firstDate = new Date(year, month, 1),
							        lastDate = new Date(year, month + 1, 0),
							        numDays = lastDate.getDate();

							    let start = 1;
							    let end = endFirstWeek(firstDate, 2);
							    while (start <= numDays) {
							        weeks.push({start: start, end: end});
							        start = end + 1;
							        end = end + 7;
							        end = start === 1 && end === 8 ? 1 : end;
							        if (end > numDays) {
							            end = numDays;
							        }
							    }
							    console.log('asc');
							    console.log(weeks);
							    //return weeks;
							}
							
							getWeeksStartAndEndInMonth(2,2018,1);*/
							/*	getWeeksStartAndEndInMonth(3,2018,1);
								function getWeeksStartAndEndInMonth(month, year, start) {
								    let weeks = [],
								        firstDate = new Date(year, month, 1),
								        lastDate = new Date(year, month + 1, 0),
								        numDays = lastDate.getDate();
								    
								    
								    //let start = 1;
								    let end = 7 - firstDate.getDay();
								    //alert(firstDate.getDay());
								 
								    if (start === 'monday') {
								        if (firstDate.getDay() === 0) {
								            end = 1;
								            eD = 0;
								        } else {
								            end = 7 - firstDate.getDay() + 1;
								            eD = firstDate.getDay() + 1;
								        }
								    }
								   
								    console.log("StartS:"+start +">>EndS:"+end);
								   return false;
								    while (start <= numDays) {
								    	console.log("Start:"+start +">>End :"+end);
								        weeks.push({start: start, end: end});
								        start = end + 1;
								        end = end + 7;
								        end = start === 1 && end === 8 ? 1 : end;
								        if (end > numDays) {
								            end = numDays;
								        }
								    }
								    console.log('asc');
								    console.log(weeks);
								    //return weeks;
								}
							 */

							/*function weeks (month) {
							    month = moment(month, 'YYYY-MM-DD');

							    var first = month.day() == 0 ? 6 : month.day()-1;
							    var day = 7-first;

							    var last = month.daysInMonth();
							    var count = (last-day)/7;

							    var weeks = [];
							    weeks.push([1, day]);
							    for (var i=0; i < count; i++) {
							        weeks.push([(day+1), (Math.min(day+=7, last))]);
							        
							    }
							    return weeks;
							}


							// call

							
							    var month = '2018-03-01';
							    console.log(weeks(month));
							 */

							//Test End

							/* init() function for form object create. */
							$scope.init = function() {

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
								
								//for New/Registerd
								$scope.openDatePickerDOB = function() {
									$scope.datepickerDOB.opened = true;
								};

								$scope.datepickerDOB = {
									opened : false
								};
								
								

								// Loop DatePicker End
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

								// for popup
								$scope.showModal = false;
								$scope.buttonClicked = "";
								$scope.popUpFlag = true;
								// end for popup

								$scope.isReshedule = 'N';

								$scope.regiType = "1";

								$scope.fieldsDisabled = false;

								$scope.selectType = "doctor";
								$scope.selectedSlotPeriod = "Y";

								$scope.selectedSlotId = "1";
								
								$scope.allowedOverBookingSlots = 0;
								$scope.slotStatusId = 4;
								$scope.priorityId = 1;

								var commonObj = {
									unitId : $scope.unitId,
									organizationId : $scope.organizationId
								};
								var data = {};

								//Get Active Prefix List
								var URI = BASE_URL + ROOT_URL
										+ GETACTIVEPREFIXLIST;
								BillingGenericService
										.serviceAction(METHOD_GET, URI, data,
												NOTIFICATION_MSG_STATUS_FALSE)
										.then(
												function(response) {
													console.log(response);
													$scope.prefixList = [];
													if (response.data.status == "success")
														$scope.prefixList = response.data.listObject;
												});

								//Get Active Speciality List
								var URI = BASE_URL + ROOT_URL
										+ GETACTIVESPECIALITY;
								BillingGenericService
										.serviceAction(METHOD_POST, URI,
												commonObj,
												NOTIFICATION_MSG_STATUS_FALSE)
										.then(
												function(response) {
													console.log(response);
													// $rootScope.startLoader();
													if (response.data.status == "success")
														$scope.specialityList = response.data.listObject;
												});

								//Get Active Modality Type List
								/*var URI = BASE_URL + ROOT_URL
										+ GETACTIVEMODALITYTYPELIST;
								BillingGenericService
										.serviceAction(METHOD_POST, URI,
												commonObj,
												NOTIFICATION_MSG_STATUS_FALSE)
										.then(
												function(response) {
													console.log(response);
													// $rootScope.startLoader();
													if (response.data.status == "success")
														$scope.modalityTypeList = response.data.listObject;
												});*/

								//Object For Patient Search
								$scope.patientSearchObj = {
									"uhidNumber" : "",
									"patientName" : "",
									"genderId" : "",
									"mobileNumber" : "",
									"dob" : "",
									"identificationNumber" : "",
									"identificationTypeId" : "",
									"unitId" : $scope.unitId,
									"organizationId" : $scope.organizationId,
									"registrationTypeId" : '1'
								}

								//Object For Patient Details
								$scope.patientDetails = {
									"patientName" : "",
									"genderCode" : "",
									"age" : "",
									"uhIdNumber" : "",
									"prefixId" : "",
									"genderId" : "",
									"birthDate" : "",
									"mobikeNumber" : "",
									"firstName" : "",
									"lastName" : "",
									"middleName" : "",
									"email" : "",
									"remark" : "",
									"patientId" : ""
								}
								
								$scope.dayList =[{id:"1",name:"Monday"},{id:"2",name:"Tuesday"},{id:"3",name:"Wednesday"},{id:"4",name:"Thursday"},{id:"5",name:"Friday"},{id:"6",name:"Saturday"},{id:"7",name:"Sunday"}];

							}

							$scope.init();
							

							//Function For Get Doctor Lisy By Multiple Speciality 
							$scope.getDoctorBySpeciality = function(id) {

								var speciality = [];
								speciality.push(id);

								var data = {
									organizationId : $scope.organizationId,
									unitId : $scope.unitId,
									specialityArray : speciality
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ GETDOCTORSLISTAGANISTSPECIALITIES;
								BillingGenericService
										.serviceAction(METHOD_POST, URI, data,
												NOTIFICATION_MSG_STATUS_FALSE)
										.then(
												function(response) {
													console.log(response);
													$scope.doctorList = [];
													$scope.commonListDoctorModality = [];

													// $rootScope.startLoader();
													if (response.data.status == "success") {
														$scope.doctorList = response.data.listObject;
														$scope.commonListDoctorModality = response.data.listObject;

													}

												});

							}

							//For Get Active Gender List
							$scope.getSexMasterList = function() {
								var URI = BASE_URL + ROOT_URL
										+ GETACTIVEGENDERLIST;
								BillingGenericService
										.serviceAction(METHOD_GET, URI, "",
												NOTIFICATION_MSG_STATUS_FALSE)
										.then(
												function(response) {
													$scope.sexMasterList = response.data.listObject;
												});
							}
							$scope.getSexMasterList();

							//Function For Search Patient
							$rootScope.searchPatient = function(
									patientsearchObj) {
								var URI = BASE_URL + ROOT_URL
										+ GETTEMPORYPATIENTFORPREREGISTRATION;
								BillingGenericService
										.serviceAction(METHOD_POST, URI,
												patientsearchObj,
												NOTIFICATION_MSG_STATUS_FALSE)
										.then(
												function(response) {
													$rootScope.patientList = response.data.listObject;
													console.log("PatientList",$scope.patientList);
												});
							}

							//Function For Selected Patient assign to patient Details Object
							$scope.getPatientDetailsByIndex = function(index) {
								console.log($rootScope.patientList[index]);
								//return false;
								$scope.patientDetails.prefixId = ($scope.patientList[index].prefixId
										.toString());
								$scope.patientDetails.firstName = ($scope.patientList[index].firstName);
								$scope.patientDetails.middleName = ($scope.patientList[index].middleName);
								$scope.patientDetails.lastName = ($scope.patientList[index].lastName);
								$scope.patientDetails.age = ($scope.patientList[index].ageString);
								$scope.patientDetails.birthDate = ($scope.patientList[index].dob);
								$scope.patientDetails.genderId = ($scope.patientList[index].genderId
										.toString());
								$scope.patientDetails.uhIdNumber = ($scope.patientList[index].uhIdNumber);
								$scope.patientDetails.mobileNumber = ($scope.patientList[index].mobileNumber);
								$scope.patientDetails.email = ($scope.patientList[index].email);
								$scope.patientDetails.patientId = ($scope.patientList[index].patientId);
								$scope.regiType = "2";
								
								$scope.datepickerDOB = new Date($rootScope.getFormatedDate($scope.patientList[index].dob));
								$scope.fieldsDisabled = true;
								
								if($scope.selectedSlotId == 3)
									{
										var data ={
												orgId:$scope.organizationId,
												unitId : $scope.unitId,
												age : $scope.patientList[index].ageString,
												sexId : $scope.patientList[index].genderId
										}
										
										var URI = BASE_URL + ROOT_URL + "/api/packages/ehcPackages";
										BillingGenericService
												.serviceAction(METHOD_POST, URI, data,
														NOTIFICATION_MSG_STATUS_FALSE)
												.then(
														function(response) {
															console.log(response);
															$scope.packageEHCList = [];
															// $rootScope.startLoader();
															if (response.data.status == "success") {
																$scope.packageEHCList = response.data.listObject;
																console.log($scope.packageEHCList);
															}

														});
										
										
									}
								

							}
							
							$scope.calculateAge= function()
							{
								var dt = moment($scope.datepickerDOB).format('MM/DD/YYYY');
								$scope.patientDetails.age = $rootScope.getAge(dt);
								
								if($scope.selectedSlotId == '3')
									{
									var data ={
											orgId:$scope.organizationId,
											unitId : $scope.unitId,
											age : $scope.patientDetails.age,
											sexId : $scope.patientDetails.genderId
									}
									
									var URI = BASE_URL + ROOT_URL + "/api/packages/ehcPackages";
									BillingGenericService
											.serviceAction(METHOD_POST, URI, data,
													NOTIFICATION_MSG_STATUS_FALSE)
											.then(
													function(response) {
														console.log(response);
														$scope.packageEHCList = [];
														// $rootScope.startLoader();
														if (response.data.status == "success") {
															$scope.packageEHCList = response.data.listObject;
															console.log($scope.packageEHCList);
														}

													});
									}
								
							}

							//Function For Get Date Wise Slots
							$scope.getSlotsOfDate = function() {
								var dt = $('#datepicker').val();
								//alert(dt);
								//	alert(moment($('#datepicker').val()).format('DD-MM-YYYY'));
								var array = dt.split('/');
								var newDate = "";
								newDate = array[2] + '-' + array[1] + '-'
										+ array[0];

								//alert($scope.selectedSlotId);

								if ($scope.selectedSlotId == '1') {
									var data = {
										specialityId : $scope.specialityId,
										doctorId : $scope.doctorId,
										appointmentDate : newDate,
										organizationId : $scope.organizationId,
										unitId : $scope.unitId,
										slotTypeId : $scope.selectedSlotId
									};
								} else if ($scope.selectedSlotId == '2'){
									var data = {
										subSpecialityId : $scope.subSpecialityId,
										specialityId : $scope.specialityId,
										modalityId : $scope.modalityId,
										appointmentDate : newDate,
										organizationId : $scope.organizationId,
										unitId : $scope.unitId,
										slotTypeId : $scope.selectedSlotId
									};
								}
								else
									{
									var data = {
											appointmentDate : newDate,
											organizationId : $scope.organizationId,
											unitId : $scope.unitId,
											slotTypeId : $scope.selectedSlotId
										};
									}

								console.log(data);
								//return false;
								var URI = BASE_URL + ROOT_URL + GETDAILYSLOTS;
								BillingGenericService
										.serviceAction(METHOD_POST, URI, data,
												NOTIFICATION_MSG_STATUS_FALSE)
										.then(
												function(response) {
													console.log(response);
													$scope.slotDetailsList = [];
													// $rootScope.startLoader();
													if (response.data.status == "success") {
														$scope.slotDetailsList = response.data.listObject;
														console
																.log($scope.slotDetailsList);
													}

												});

							}
							
							/*$scope.Test = function()
							{
								angular
								.element(
										'#patientSearchForRegistration')
								.modal('show');
							}*/
								
							$scope.getOverBookSlotCheckPopup = function(item)
							{
								//alert($scope.allowedOverBookingSlots);
								if($scope.allowedOverBookingSlots > 0)
									{
										var data = {
												
												appointmentDate : item.appointmentDate2,
												doctorId:item.doctorId,
												specialityId : item.specialityId,
												organizationId:$scope.organizationId,
												unitId:$scope.unitId,
												slotTypeId : $scope.selectedSlotId
										}
										
										//0- Over booking Not Allowed
										//1- Over booking Allowed
										var URI = BASE_URL + ROOT_URL + ISOVERBOOKINGALLOWEDORNOT;
										BillingGenericService
												.serviceAction(METHOD_POST, URI, data,
														NOTIFICATION_MSG_STATUS_FALSE)
												.then(
														function(response) {
															console.log(response);
															if (response.data.listObject[0] == "0") {
																growl.error("Over Booking Not Allowed",{
																	title : ERROR_MSG
																});
																return false;
															}
															else
																{
																if (response.data.listObject[1] == $scope.allowedOverBookingSlots) {
																	growl.error("Over Booking Slots Full",{
																		title : ERROR_MSG
																	});
																	return false;
																}
																else
																{
																
																	$scope.doctorName = item.doctorName;
																	$scope.specialityname = item.specialityName;
																	$scope.fromTime = item.fromTimeString2;
																	$scope.toTime = item.toTimeString2;
																	$scope.appointmentDate = item.appointmentDate;
																	$scope.slotId = item.slotId;
																	$scope.slotStatusId = 6;
																	$scope.priorityId  = 2;
																	$scope.bookedpatientLength = item.slotPatientDetailsListsSize;
																	angular
																	.element(
																			'#patientSearchForRegistration')
																	.modal('show');
																}
																}

														});
										
										//ISOVERBOOKINGALLOWEDORNOT
//										console.log('sdcdsc',item);
//										$scope.doctorName = item.doctorName;
//										$scope.specialityname = item.specialityName;
//										$scope.fromTime = item.fromTimeString2;
//										$scope.toTime = item.toTimeString2;
//										$scope.appointmentDate = item.appointmentDate;
//										$scope.slotId = item.slotId;
//										
//										$scope.bookedpatientLength = item.slotPatientDetailsListsSize;
//										angular
//										.element(
//												'#patientSearchForRegistration')
//										.modal('show');
									}
								
							}
							
							/*  $scope.$watch('datepicker',function(){
							      console.log($scope.datepicker);
							    })*/

							//Function For Set Value On Popup
							$scope.getSlotDetailsPopup = function(item) {
								$scope.doctorName = item.doctorName;
								$scope.specialityname = item.specialityName;
								$scope.fromTime = item.fromTimeString2;
								$scope.toTime = item.toTimeString2;
								$scope.appointmentDate = item.appointmentDate;
								$scope.slotId = item.slotId;
								$scope.slotStatusId = 4;
								$scope.priorityId  = 1;
								$scope.bookedpatientLength = item.slotPatientDetailsListsSize;
								//$scope.regiType = "1";
							}
							
							$scope.newPatientChange = function()
							{
								$scope.patientDetails.prefixId = "";
								$scope.patientDetails.firstName = "";
								$scope.patientDetails.middleName = "";
								$scope.patientDetails.lastName = "";
								$scope.patientDetails.age = "";
								$scope.patientDetails.birthDate = "";
								$scope.patientDetails.genderId = "";
								$scope.patientDetails.mobileNumber = "";
								$scope.patientDetails.email = "";
								$scope.patientDetails.remark = "";
								$scope.patientDetails.patientId = 0;
								
								$scope.datepickerDOB = new Date();
								
								$rootScope.patientList = [];
								
								$scope.regiType = "1";
								$scope.fieldsDisabled = false;
							}
							$scope.registerPatientChange = function()
							{
								$scope.patientDetails.prefixId = "";
								$scope.patientDetails.firstName = "";
								$scope.patientDetails.middleName = "";
								$scope.patientDetails.lastName = "";
								$scope.patientDetails.age = "";
								$scope.patientDetails.birthDate = "";
								$scope.patientDetails.genderId = "";
								$scope.patientDetails.mobileNumber = "";
								$scope.patientDetails.email = "";
								$scope.patientDetails.remark = "";
								$scope.patientDetails.patientId = 0;
								
								$scope.datepickerDOB = new Date();
								
								$scope.regiType = "2";
								$scope.fieldsDisabled = true;
							}

							//Function For Select Slot
							$scope.getDoctorModalityList = function(type) {

								$scope.commonListDoctorModality = [];
								if (type == 'doctor') {
									$scope.selectedSlotId = "1";
								} else {
									$scope.selectedSlotId = "2";
								}
							}

							//Function For Get Modality List by Multiple Modality Type Id
							$scope.getSubSpecialityBySpeciality = function(id) {
								var data = {
									organizationId : $scope.organizationId,
									unitId : $scope.unitId,
									specialityId : id
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ GETSUBSPECIALITYBYSPECIALITYID;
								BillingGenericService
										.serviceAction(METHOD_POST, URI, data,
												NOTIFICATION_MSG_STATUS_FALSE)
										.then(
												function(response) {
													console.log(response);
													// $rootScope.startLoader();
													$scope.subSpecialityList = [];

													if (response.data.status == "success") {
														$scope.subSpecialityList = response.data.listObject;

													}

												});
							}
							
							//Function For Get Modality List by Multiple Modality Type Id
							$scope.getModalityBySubSpeciality = function(id) {
								//alert('assa');
								var array = id.split('_');
								
								var data = {
									organizationId : $scope.organizationId,
									unitId : $scope.unitId,
									specialityId : array[0],
									subSpecialityId :array[1]
								}
								console.log(data);
								//return false;
								var URI = BASE_URL + ROOT_URL
										+ GETMODALITYBYSUBSPECIALITYID;
								BillingGenericService
										.serviceAction(METHOD_POST, URI, data,
												NOTIFICATION_MSG_STATUS_FALSE)
										.then(
												function(response) {
													console.log(response);
													// $rootScope.startLoader();
													$scope.modalityMaster = [];
													$scope.commonListDoctorModality = [];


													if (response.data.status == "success") {
														$scope.modalityMaster = response.data.listObject;
														$scope.commonListDoctorModality =response.data.listObject;
													}

												});
							}
							

							//Function For Get Slot By Doctor/Modality
							$scope.getSlotsUsingDoctorId = function(
									specDoctorId) {
								var array = specDoctorId.split('_');
								if ($scope.selectedSlotId == 1) {
									$scope.getSlots(array[0], array[1],
											array[2], array[3],array[4]);
									//$scope.allowedOverBookingSlots = array[4];
								} else {
									$scope.getSlotsForModality(array[0],
											array[1], array[2],array[3], array[4]);
								}
							}

							//Function For Get Slot By Doctor
							$scope.getSlots = function(docId, specialityId,
									docName, specialityName,allowedOverBookingSlots) {
								//alert(docId +' '+ specialityId + ' '+ $scope.selectedSlotId);
								$(".tabular").removeClass("activeTabular");
								$("#tabular" + docId).addClass("activeTabular");
								$scope.doctorName = docName;
								$scope.specialityName = specialityName;
								$scope.doctorId = docId;
								$scope.allowedOverBookingSlots = allowedOverBookingSlots;
								$scope.specialityId = specialityId;
								var data = {
									organizationId : $scope.organizationId,
									unitId : $scope.unitId,
									slotTypeId : $scope.selectedSlotId,
									doctorId : docId,
									specialityId : specialityId
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL + GETYEARLYSLOTS;
								BillingGenericService
										.serviceAction(METHOD_POST, URI, data,
												NOTIFICATION_MSG_STATUS_FALSE)
										.then(
												function(response) {
													console.log(response);
													// $rootScope.startLoader();
													$scope.yearSlotList = [];

													if (response.data.status == "success") {
														$scope.yearSlotList = response.data.listObject;
														console
																.log($scope.yearSlotList);
													}

												});

								if ($scope.selectedSlotPeriod == "D") {
									$scope.getSlotsOfDate();
								}

							}

							//Function For Get Slot By Modality
							$scope.getSlotsForModality = function(modId,
									subSpecId,specId,modName, subSpecialityName) {
								//alert(modId +' '+ modTypeId+ ' '+ $scope.selectedSlotId);
								$(".tabular").removeClass("activeTabular");
								$("#tabular" + modId).addClass("activeTabular");
								$scope.doctorName = modName;
								$scope.specialityName = subSpecialityName;
								$scope.modalityId = modId;
								$scope.subSpecialityId = subSpecId;
								$scope.specialityId = specId;
								var data = {
									organizationId : $scope.organizationId,
									unitId : $scope.unitId,
									slotTypeId : $scope.selectedSlotId,
									modalityId : modId,
									subSpecialityId :$scope.subSpecialityId,
									specialityId :$scope.specialityId 
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL + GETYEARLYSLOTS;
								BillingGenericService
										.serviceAction(METHOD_POST, URI, data,
												NOTIFICATION_MSG_STATUS_FALSE)
										.then(
												function(response) {
													console.log(response);
													// $rootScope.startLoader();
													$scope.yearSlotList = [];

													if (response.data.status == "success") {
														$scope.yearSlotList = response.data.listObject;
														console
																.log($scope.yearSlotList);
													}

												});

							}

							/*$scope.test = function()
							{
								//alert("xcd");
							}*/

							/******* START TAB FOR ALL PAGES IN TREE LEVEL *******/
							/*First Level*/
							$scope.firstTabLevel = 1;

							$scope.setFirstTabLevel = function(newTab) {
								if (newTab == 1) {
									$("#case-sheet").css("background-color",
											"#5F9EA0");
									$("#complaints").css("background-color",
											"#5ab7c8");
									$scope.slotTypeId = 1;
								} else if (newTab == 2) {

									$("#case-sheet").css("background-color",
											"#5ab7c8");
									$("#complaints").css("background-color",
											"#5F9EA0");
									//alert("dv");
									$scope.slotTypeId = 2;
									 $scope.selectedSlotId = 3;
									var data = {
											organizationId : $scope.organizationId,
											unitId : $scope.unitId,
											slotTypeId : $scope.selectedSlotId,
										}
										console.log(data);
										var URI = BASE_URL + ROOT_URL + GETYEARLYSLOTS;
										BillingGenericService
												.serviceAction(METHOD_POST, URI, data,
														NOTIFICATION_MSG_STATUS_FALSE)
												.then(
														function(response) {
															console.log(response);
															// $rootScope.startLoader();
															$scope.yearSlotList = [];

															if (response.data.status == "success") {
																$scope.yearSlotList = response.data.listObject;
																console
																		.log($scope.yearSlotList);
															}

														});
									

									//$scope.getECHSlots(3);
								}

								$scope.firstTabLevel = newTab;
							};

							$scope.isSetFirstTabLevel = function(tabNum) {
								return $scope.firstTabLevel === tabNum;
							};

							//Second Level
							$scope.secondTabLevel = 1;

							$scope.setSecondTabLevel = function(newTab) {
								$scope.secondTabLevel = newTab;
								//alert('Day!!!!');
								$scope.dayDate = moment(new Date()).format();
								
								
							};

							$scope.leftArrow = function() {
								//alert("left arrow");
								$scope.dayDate = moment($scope.dayDate).add(
										'days', -1).format();
							}

							$scope.rightArrow = function() {
								//alert("right arrow");
								$scope.dayDate = moment($scope.dayDate).add(
										'days', 1).format();
							}

							$scope.isSetSecondTabLevel = function(tabNum) {
								return $scope.secondTabLevel === tabNum;
							};

							//Third Level
							$scope.thirdTabLevel = 1;

							$scope.setThirdTabLevel = function(newTab) {
								$scope.thirdTabLevel = newTab;
							};

							$scope.isSetThirdTabLevel = function(tabNum) {
								return $scope.thirdTabLevel === tabNum;
							};

							//FORTH Level
							$scope.forthTabLevel = 1;

							$scope.setForthTabLevel = function(newTab) {
								$scope.forthTabLevel = newTab;
							};

							$scope.isSetForthTabLevel = function(tabNum) {
								return $scope.forthTabLevel === tabNum;
							};
							/******* END TAB FOR ALL PAGES IN TREE LEVEL *******/

							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.saveAppointmentSchedule();
							}

							/*$scope.getECHSlots(selecteSlotId)
							{

								
								var data = {
										organizationId : $scope.organizationId,
										unitId : $scope.unitId,
										slotTypeId :selecteSlotId
									}
									console.log(data);
									var URI = BASE_URL + ROOT_URL + GETYEARLYSLOTSFORECH;
									BillingGenericService
											.serviceAction(METHOD_POST, URI, data,
													NOTIFICATION_MSG_STATUS_FALSE)
											.then(
													function(response) {
														console.log(response);
														// $rootScope.startLoader();
														$scope.yearSlotListECH = [];

														if (response.data.status == "success") {
															$scope.yearSlotListECH = response.data.listObject;
															console
																	.log($scope.yearSlotListECH);
														}

													});
									
							}*/

							//Save Appointment Schduling
							$scope.saveAppointmentSchedule = function() {
								if ($scope.popUpFlag) {
									$scope.showModal = !$scope.showModal;

								} else {
									$scope.currentDate = new Date();
									$scope.currentDate = moment(
											$scope.currentDate).format(
											'DD-MM-YYYY HH:mm:ss');

									var data = {
										slotId : $scope.slotId,
										//patientId : $scope.patientDetails.patientId,
										remark : $scope.patientDetails.remark,
										organizationId : $scope.organizationId,
										unitId : $scope.unitId,
										createdDate : $scope.currentDate,
										updatedDate : $scope.currentDate,
										createdBy : $scope.userId,
										updatedBy : $scope.userId,
										status : 'A',
										appointmentTypeId : $scope.selectedSlotId,
										slotStatusId : $scope.slotStatusId,
										priorityId : $scope.priorityId,
										allowedOverBookingSlots : $scope.allowedOverBookingSlots
									}
									
									if($scope.selectedSlotId == '3')
										{
											data.ehcPackageId = $scope.patientDetails.ehcPackageId;
										}
									
									
									if($scope.regiType == '2' && $scope.patientDetails.patientId == 0)
										{
											growl.error("Please Select Patient!",{
												title : ERROR_MSG
											});
											return false;
										}
									else 
										{
											data.patientId = $scope.patientDetails.patientId;
										}
									
									if($scope.regiType == '1')
										{
											data.prefixId = $scope.patientDetails.prefixId;
											data.firstName = $scope.patientDetails.firstName;
											data.middleName = $scope.patientDetails.middleName;
											data.lastName =$scope.patientDetails.lastName;
											data.dob =moment($scope.datepickerDOB).format('DD-MM-YYYY HH:mm:ss');
											data.genderId =$scope.patientDetails.genderId;
											data.mobileNumber =$scope.patientDetails.mobileNumber;
											data.email =$scope.patientDetails.email;
											data.registrationTypeId = 5;
											data.patientId = 0;
										}

									if ($scope.isReshedule == 'N') {
										var URI = BASE_URL + ROOT_URL
												+ SCHEDULEAPPOINTMENT;
									} else {

										data.previousSlotId = $scope.previousSlotId;
										data.appointmentId = $scope.appointmentId;
										data.rescheduleReasonId = $scope.rescheduleReasonId;
										data.rescheduledBy = $scope.reschedulledBy;

										var URI = BASE_URL + ROOT_URL
												+ RESCHEDULEAPPOINTMENT;
									}

									console.log(data);
									//return false;

									BillingGenericService
											.serviceAction(METHOD_POST, URI,
													data,
													NOTIFICATION_MSG_STATUS_TRUE)
											.then(
													function(response) {
														console.log(response);
														//$rootScope.startLoader();
														if (response.data.status == "success") {
															angular
																	.element(
																			'#patientSearchForRegistration')
																	.modal(
																			'hide');

															//alert(item.date);
															// $cookies.putObject('cookieObjectReschedule',null);
															$cookies
																	.remove("cookieObjectReschedule");
															var array = $scope.appointmentDate
																	.split('/');
															var newDate = "";
															newDate = array[2]
																	+ '-'
																	+ array[1]
																	+ '-'
																	+ array[0];

															if ($scope.selectedSlotId == '1') {
																var data = {
																	specialityId : $scope.specialityId,
																	doctorId : $scope.doctorId,
																	appointmentDate : newDate,
																	organizationId : $scope.organizationId,
																	unitId : $scope.unitId,
																	slotTypeId : $scope.selectedSlotId
																};
															} else {
																var data = {
																	modalityTypeId : $scope.modalityTypeId,
																	modalityId : $scope.modalityId,
																	appointmentDate : newDate,
																	organizationId : $scope.organizationId,
																	unitId : $scope.unitId,
																	slotTypeId : $scope.selectedSlotId
																};
															}

															console.log(data);
															//return false;
															var URI = BASE_URL
																	+ ROOT_URL
																	+ GETDAILYSLOTS;
															BillingGenericService
																	.serviceAction(
																			METHOD_POST,
																			URI,
																			data,
																			NOTIFICATION_MSG_STATUS_FALSE)
																	.then(
																			function(
																					response) {
																				console
																						.log(response);
																				$scope.slotDetailsList = [];
																				// $rootScope.startLoader();
																				if (response.data.status == "success") {
																					$scope.slotDetailsList = response.data.listObject;
																					console
																							.log($scope.slotDetailsList);
																				}

																			});

														}

													});

								}
							}

							$scope.getMonthWiseSlot = function(monthDate,typeFlag) {
								
								if(typeFlag == 'M')
									{
									 var copyright = new Date();
									 var year = copyright.getFullYear();
									 var month = copyright.getMonth()+1;
									
									}
								else
									{
										var arr = monthDate.split('-');
										 var year = arr[1];
										 var month = arr[0];
									}
								
								var data = {
									"organizationId" : $scope.organizationId,
									"unitId" : $scope.unitId,
									"slotTypeId" : $scope.selectedSlotId,
									"month" : month,
									"year" : year,

								}
								if ($scope.selectedSlotId == 1) {
									data.doctorId = $scope.doctorId;
									data.specialityId = $scope.specialityId;
								}
								else if($scope.selectedSlotId == 2)
										{
											//var arrSeSubSpe = $scope.subSpecialityId.split('_');
											data.specialityId = $scope.specialityId;
											data.subSpecialityId = $scope.subSpecialityId;
											data.modalityId = $scope.modalityId;
										}
								else
									{
									$scope.secondTabLevel = 2;
									}
								
								//console.log($scope.dayList);
								console.log(data);
								//return false;
								var URI = BASE_URL + ROOT_URL + GETMONTHLYSLOTS;

								BillingGenericService
										.serviceAction(METHOD_POST, URI, data,
												NOTIFICATION_MSG_STATUS_FALSE)
										.then(
												function(response) {
													console.log(response);
													$scope.slotDetailsList = [];
													// $rootScope.startLoader();
													if (response.data.status == "success") {
														$scope.slotListMonth = response.data.listObject;
														//console.log(response.data.listObject);
														$scope.weekArray = [];
														for(var i=1;i <= response.data.listObject.length;i++)
															{
																var weekArr ={};
																weekArr.id = i;
																weekArr.name = "w"+i;
																$scope.weekArray.push(weekArr);
															}
														console.log('week');
														console.log($scope.weekArray);
														
														//$scope.firstDate = response.data.listObject[0][0];
														//$scope.lastDate = response.data.listObject[response.data.listObject.length][0];
														//alert(response.data.listObject[response.data.listObject.length][0]);
														//alert("d"+response.data.listObject[0][0]);
														//console.log("first:"+console.log(response.data.listObject[0][0]));
														//console.log("last:"+console.log(response.data.listObject[response.data.listObject.length][0]));

														 var now = new Date(year,month,1);
														 $scope.lastDate  = new Date(now.getFullYear(), now.getMonth(), 0);
														 $scope.firstDate = new Date(year,month-1,1);
													}

												});

							}
							
							$scope.getWeekWiseSlots = function(weekId)
							{
								$scope.wkId = weekId;
								console.log($scope.slotListMonth[weekId-1]);
								//alert($scope.slotListMonth[weekId-1].length);
								//alert($scope.slotListMonth[weekId-1][0]);
								//alert($scope.slotListMonth[weekId-1][$scope.slotListMonth[weekId-1].length-1]);
								var arrFrom =$scope.slotListMonth[weekId-1][0].split('_');
								$scope.fromDateForWeek = arrFrom[1];
								
								var arrTo =$scope.slotListMonth[weekId-1][$scope.slotListMonth[weekId-1].length-1].split('_');
								$scope.toDateForWeek = arrTo[1];
								
								var data = {
										organizationId:$scope.organizationId,
										unitId:$scope.unitId,
										
										slotTypeId:$scope.selectedSlotId,
										fromDate:$scope.fromDateForWeek,
										toDate:$scope.toDateForWeek
								}
								
								if($scope.selectedSlotId == 1)
									{
										data.doctorId=$scope.doctorId;
										data.specialityId=$scope.specialityId;
									}
								else if($scope.selectedSlotId == 2)
									{
										data.specialityId=$scope.specialityId;
										data.subSpecialityId=$scope.subSpecialityId;
										data.modalityId=$scope.modalityId;
									}
								else
								{
								$scope.secondTabLevel = 3;
								}
							
								
								console.log(data);
								//return false;
								
								var URI = BASE_URL+ ROOT_URL+ GETWEEKLYSLOTS;
								BillingGenericService.serviceAction(METHOD_POST,URI,data,NOTIFICATION_MSG_STATUS_FALSE)
								.then(function(response) {
											console.log(response);
											$scope.slotDetailsList = [];
											// $rootScope.startLoader();
											if (response.data.status == "success") {
												$scope.slotsListWeek = response.data.listObject;
												console.log($scope.slotsListWeek);
											}

										});
							
							}
							
							$scope.changeClass = function(colorFlag,weekId,co)
							{
								//alert(colorFlag);
								if(colorFlag == 'Y')
								 $('#color'+co+weekId).addClass("bg-green");
							}

							////Function For Set Value on assign patient on appointement
							$scope.getBookSlotDetails = function(item) {
								$scope.patientName = item.patientName;
								$scope.mobileNumber = item.mobileNumber;
								$scope.email = item.email;
								$scope.address = item.address;
								
								$scope.bookedPatientList = item.slotPatientDetailsList;
								console.log($scope.bookedPatientList);
							}

							//for get cookie object from appointment list
							if ($cookies.get('cookieObjectReschedule') != null
									&& $cookies.get('cookieObjectReschedule') != undefined) {
								//alert('sc');
								$scope.rescheduleCookiesObj = JSON
										.parse($cookies
												.get('cookieObjectReschedule'));
								console.log($scope.rescheduleCookiesObj);

								$scope.selectType = $scope.rescheduleCookiesObj.selectType;
								$scope.selectedSlotPeriod = "D";
								$scope.selectedSlotId = $scope.rescheduleCookiesObj.selectedSlotId;

								if ($scope.selectedSlotId == 1) {

									$scope.specialityId = $scope.rescheduleCookiesObj.specialityId
											.toString();

									$scope
											.getDoctorBySpeciality($scope.specialityId);

									$scope.specDoctorId = $scope.rescheduleCookiesObj.doctorId
											+ '_'
											+ $scope.rescheduleCookiesObj.specialityId
											+ '_'
											+ $scope.rescheduleCookiesObj.doctorName
											+ '_'
											+ $scope.rescheduleCookiesObj.specialityName;
									$scope.doctorId = $scope.rescheduleCookiesObj.doctorId;

									$timeout(
											function() {
												$(".tabular").removeClass(
														"activeTabular");
												$(
														"#tabular"
																+ $scope.rescheduleCookiesObj.doctorId)
														.addClass(
																"activeTabular");
												$('#datepicker')
														.val(
																$scope.rescheduleCookiesObj.rescheduleAppointmentDate);
												$scope.datepicker = new Date(
														$scope.rescheduleCookiesObj.rescheduleAppointmentDate);
												$scope.getSlotsOfDate();
											}, 500);

								} else  if ($scope.selectedSlotId == 2)  {
									$scope.modalityTypeId = $scope.rescheduleCookiesObj.modalityTypeId
											.toString();

									$scope
											.getModalityByModalityType($scope.modalityTypeId);

									$scope.specDoctorId = $scope.rescheduleCookiesObj.modalityId
											+ '_'
											+ $scope.rescheduleCookiesObj.modalityTypeId
											+ '_'
											+ $scope.rescheduleCookiesObj.doctorName
											+ '_'
											+ $scope.rescheduleCookiesObj.specialityName;
									$scope.modalityId = $scope.rescheduleCookiesObj.modalityId;

									$timeout(
											function() {
												$(".tabular").removeClass(
														"activeTabular");
												$(
														"#tabular"
																+ $scope.rescheduleCookiesObj.modalityId)
														.addClass(
																"activeTabular");
												$('#datepicker')
														.val(
																$scope.rescheduleCookiesObj.rescheduleAppointmentDate);
												$scope.datepicker = new Date(
														$scope.rescheduleCookiesObj.rescheduleAppointmentDate);
												$scope.getSlotsOfDate();
											}, 500);

								}
								else if ($scope.selectedSlotId == 3) 
									{
									$scope.firstTabLevel = 2;
									$scope.secondTabLevel = 4;
									$timeout(
											function() {
												$('#datepicker')
														.val(
																$scope.rescheduleCookiesObj.rescheduleAppointmentDate);
												$scope.datepicker = new Date(
														$scope.rescheduleCookiesObj.rescheduleAppointmentDate);
												$scope.getSlotsOfDate();
											}, 500);
									}

								$scope.doctorName = $scope.rescheduleCookiesObj.doctorName;
								$scope.specialityName = $scope.rescheduleCookiesObj.specialityName;

								$scope.patientDetails.prefixId = ($scope.rescheduleCookiesObj.prefixId
										.toString());
								$scope.patientDetails.firstName = ($scope.rescheduleCookiesObj.firstName);
								$scope.patientDetails.middleName = ($scope.rescheduleCookiesObj.middleName);
								$scope.patientDetails.lastName = ($scope.rescheduleCookiesObj.lastName);
								$scope.patientDetails.age = ($scope.rescheduleCookiesObj.age);
								$scope.patientDetails.dob = ($scope.rescheduleCookiesObj.dob);
								$scope.patientDetails.genderId = ($scope.rescheduleCookiesObj.genderId
										.toString());
								$scope.patientDetails.uhIdNumber = ($scope.rescheduleCookiesObj.uhIdNumber);
								$scope.patientDetails.mobileNumber = ($scope.rescheduleCookiesObj.mobileNumber);
								$scope.patientDetails.email = ($scope.rescheduleCookiesObj.email);
								$scope.patientDetails.patientId = ($scope.rescheduleCookiesObj.patientId);
								$scope.patientDetails.remark = $scope.rescheduleCookiesObj.remark;
								$scope.regiType = "2";
								$scope.fieldsDisabled = true;

								$scope.fieldsDisabled = true;

								$scope.isReshedule = 'Y';

								$scope.previousSlotId = $scope.rescheduleCookiesObj.slotId;
								$scope.appointmentId = $scope.rescheduleCookiesObj.appointmentId;
								$scope.rescheduleReasonId = $scope.rescheduleCookiesObj.rescheduleReasonId;
								$scope.reschedulledBy = $scope.rescheduleCookiesObj.reschedulledBy;
								

							}

						} ]);