'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:appointmentListController
 * @description #appointmentListController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'appointmentListController',
				[
						'$scope',
						'$cookies',
						'$rootScope',
						'GenericService',
						'BillingGenericService',
						'$state',
						'$timeout',
						'$interval',
						function($scope, $cookies, $rootScope, GenericService,
								BillingGenericService, $state, $timeout,$interval) {

							//alert("appointmentListController");

							/* init() function for form object create. */
							$scope.init = function() {

								$scope.dateOptions = {
									formatYear : 'yyyy',
									showWeeks : false
								};
								
								
								//Date Object For Search DOB
								$scope.openDatePickerSearch = function() {
									$scope.datepickerSearch.opened = true;
								};

								$scope.datepickerSearch = {
									opened : false
								};
								
								//Date Object For Reshedule
								$scope.openDatePickerReschedule = function() {
									$scope.datepickerReschedule.opened = true;
								};

								$scope.datepickerReschedule = {
									opened : false
								};

								//Date For From Date on Search
								
								$scope.openDatePickerFromDate = function() {
										$scope.datepickerFromDate.opened = true;
								};

								$scope.datepickerFromDate = {
										opened : false
								};
								
								//Date For To Date on Search
								
								$scope.openDatePickerToDate = function() {
										$scope.datepickerToDate.opened = true;
								};

								$scope.datepickerToDate = {
										opened : false
								};
								
								$scope.datepickerFromDate = new Date();
								$scope.datepickerToDate = new Date();

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
								
								$scope.slotTypeId = 1;

								$scope.selectAppointment = [];
								
								$scope.doctorSearchFlag = 'N';
								$scope.patientSearchFlag = 'N';
								
								$scope.listStatusId = 0;

								var commonObj = {
									unitId : $scope.unitId,
									organizationId : $scope.organizationId
								};
								var data = {};

								var doctorModalityObj = {
									unitId : $scope.unitId,
									organizationId : $scope.organizationId,
									appointmentTypeId : 1,
									appointmentStatusId : 0,
									appointmentDate:moment(new Date()).format('YYYY-MM-DD'),
									patientId:0,
									specialityId:0,
									doctorId:0,
									specialityId:0,
									subSpecialityId:0,
									modalityId:0,
									visitTypeId:0
								};

								//For Get Appointment List
								var URI = BASE_URL + ROOT_URL
										+ GETAPPOINTMENTSCHEDULELIST;
								BillingGenericService
										.serviceAction(METHOD_POST, URI,
												doctorModalityObj,
												NOTIFICATION_MSG_STATUS_FALSE)
										.then(
												function(response) {
													console.log(response);
													// $rootScope.startLoader();
													if (response.data.status == "success")
														$scope.appointmentList = response.data.listObject;
													    $scope.allCount =response.data.listObject.length;
													    //$scope.pendingCount = response.data.object[0].pending;
													    //$scope.checkInCount = response.data.object[0].checkIn;
													    //$scope.rescheduledAndCancelledCount  = response.data.object[0].rescheduledAndCancelled;
													    
													    $scope.pendingCount = 0;
													    $scope.checkInCount = 0;
													    $scope.rescheduledAndCancelledCount  = 0;
													    $scope.completeCount  = 0;
													    $scope.checkOutCount  = 0;
													    $scope.admittedCount  = 0;
													    
													    angular.forEach($scope.appointmentList,function(value,index) {
																
													    	 switch (value.appointmentStatusId) {
													            case 1:
													            	 $scope.pendingCount +=1;
													                break;
													            case 2:
													            	$scope.rescheduledAndCancelledCount +=1;
													            	break;
													            case 3:
													            	$scope.rescheduledAndCancelledCount +=1;
													            	break;
													            case 5:
													            	$scope.checkInCount +=1;
													                break;
													            case 6:
													            	 $scope.completeCount +=1;
													            	 break;
													            case 7:
													            	 $scope.checkOutCount +=1;
													            	 break;
													            default:

													        }
													    	 if(value.admissionId > 0)
													    		 {
													    		 	$scope.admittedCount +=1;
													    		 	
													    		 }
																
														});
													    $scope.allCount += $scope.admittedCount;
												});
								
								var URI = BASE_URL + ROOT_URL
								+ BLOCKEDSCHEDULEAPPOINTMENTLIST;
						GenericService
								.serviceAction("POST", URI, doctorModalityObj)
								.then(
										function(response) {
											$scope.reScheduleObjList = [];

											if (response.data.status == "success") {
												$scope.reScheduleObjList = response.data.listObject;
												console.log("resdlist",$scope.reScheduleObjList);

											}
										});
								
								//For Get Active Reason List
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
								
								//For Get Active Speciaity List
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
								     

										//For Get Active Speciaity List
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
										     
								     
								     var URI = BASE_URL + ROOT_URL
										+ GETACTIVEGENDERLIST;
								BillingGenericService
										.serviceAction(METHOD_GET, URI, data,
												NOTIFICATION_MSG_STATUS_FALSE)
										.then(
												function(response) {
													console.log(response);
													$scope.sexMasterList = [];
													if (response.data.status == "success")
														$scope.sexMasterList = response.data.listObject;
												});
										

										var URI = BASE_URL + ROOT_URL
												+ GETACTIVEIDENTIFICATION;
										BillingGenericService
										.serviceAction(METHOD_GET, URI, data,
												NOTIFICATION_MSG_STATUS_FALSE)
										.then(
												function(response) {
													console.log(response);
													$scope.identificationList = [];
													if (response.data.status == "success")
														$scope.identificationList = response.data.listObject;
												});
										
								     
								     $scope.appointmentSearchObject = {
								    		 'doctorId':0,
								    		 'specialityId':0,
								    		 'organizationId':$scope.organizationId,
								    		 'unitId':$scope.unitId,
								    		 'patientId':0,
								    		 'specialityId':0,
								    		 'subSpecialityId':0,
								    		 'modalityId':0,
								    		 'visitTypeId':0
								     }
								     
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

							}

							$scope.init();
							
							$scope.setObject = function(item) {
								$scope.selectAppointment = [];
								$scope.selectAppointment = item;
							}
							
							
							$scope.getSubSpecialityBySpecialityId = function(id)
							{
								var data = {
										unitId : $scope.unitId,
										organizationId : $scope.organizationId
									};
									
									//Condition For All Modality Or By Modality Type ID
									if(id > 0)
										{
											data.specialityId = id;
											var URI = BASE_URL + ROOT_URL
											+ GETSUBSPECIALITYBYSPECIALITYID;

										}
									else
									{
										var URI = BASE_URL + ROOT_URL
										+ GETALLACTIVESUBSPECIALITYLIST;
									}
									
									BillingGenericService
											.serviceAction(METHOD_POST, URI, data,
													NOTIFICATION_MSG_STATUS_FALSE)
											.then(
													function(response) {
														console.log(response);
														$scope.subSpecialityList = [];
														// $rootScope.startLoader();
														if (response.data.status == "success")
															$scope.subSpecialityList = response.data.listObject;
													});
								
							}
							
							//Function For Get Doctor List By Speciality
							$scope.getDoctorBySpeciality = function(id) {
								var data = {
									unitId : $scope.unitId,
									organizationId : $scope.organizationId
								};
								
								//Condition For All Doctor Or By Speciality ID
								if(id > 0)
									{
										data.specialityId = id;
										var URI = BASE_URL + ROOT_URL
										+ GETDOCTORBYSPECIALITYID;

									}
								else
								{
									var URI = BASE_URL + ROOT_URL
									+ GETACTIVEDOCTORLIST;
								}
								
								BillingGenericService
										.serviceAction(METHOD_POST, URI, data,
												NOTIFICATION_MSG_STATUS_FALSE)
										.then(
												function(response) {
													console.log(response);
													$scope.doctorList = [];
													// $rootScope.startLoader();
													if (response.data.status == "success")
														$scope.doctorList = response.data.listObject;
												});
									}
							
							//Function For Get Modality List By Modality Type Id
							$scope.getModalityListById = function(id) {
								var data = {
									unitId : $scope.unitId,
									organizationId : $scope.organizationId
								};
								
								//Condition For All Modality Or By Modality Type ID
								if(id > 0)
									{
										data.modalityTypeId = id;
										var URI = BASE_URL + ROOT_URL
										+ GETMODALITYLISTBYMODALITYTYPEID;

									}
								else
								{
									var URI = BASE_URL + ROOT_URL
									+ GETACTIVEMODALITYLIST;
								}
								
								BillingGenericService
										.serviceAction(METHOD_POST, URI, data,
												NOTIFICATION_MSG_STATUS_FALSE)
										.then(
												function(response) {
													console.log(response);
													$scope.modalityList = [];
													// $rootScope.startLoader();
													if (response.data.status == "success")
														$scope.modalityList = response.data.listObject;
												});
									}
							

							//Function For Cancel Appointment
							$scope.cancelAppointment = function() {
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate).format('DD-MM-YYYY HH:mm:ss');
								var data = {
									'slotId' : $scope.selectAppointment.slotId,
									'appointmentId' : $scope.selectAppointment.appointmentId,
									'cancelReasonId' : $scope.cancelReasonId,
									'cancelledBy' : $scope.cancelledBy,
									'updatedBy' : $scope.userId,
									'unitId' : $scope.unitId,
									'organizationId' : $scope.organizationId,
									'updatedDate' : $scope.currentDate
								};

								console.log(data);
								//	return false;
								var URI = BASE_URL + ROOT_URL
										+ CANCELSCHEDULEAPPOINTMENT;
								BillingGenericService
										.serviceAction(METHOD_POST, URI, data,
												NOTIFICATION_MSG_STATUS_TRUE)
										.then(
												function(response) {
													console.log(response);
													//$rootScope.startLoader();
													if (response.data.status == "success") {
														$scope.init();
													}

												});
							}
							
							//For Show Remark On Popup
							$scope.displayRemark = function(remark) {
								$scope.appointmentRemark = remark;
							}
							
							
							//Search Patient
							$scope.searchPatient = function()
							{
								var ConDate = $('#datepickerSearch').val();
								if(ConDate != '')
								$scope.patientSearchObj.dob = $rootScope.getChangedFormatedDateShalse(ConDate);
								
									//return false;
										var URI = BASE_URL + ROOT_URL
												+ GETTEMPORYPATIENTFORPREREGISTRATION;

										BillingGenericService
												.serviceAction(METHOD_POST,
														URI, $scope.patientSearchObj,
														NOTIFICATION_MSG_STATUS_FALSE)
												.then(
														function(response) {
															$scope.patientList = [];
															if (response.data.status == "success")
																$scope.patientList = response.data.listObject;
														});
									
							}
							
							//Function For Selected Patient assign to patient Details Object
							$scope.getPatientDetailsByIndex = function(index) {
								//console.log($rootScope.patientList[index]);
								//return false;
								$scope.appointmentSearchObject.patientId = ($scope.patientList[index].patientId);
								$scope.patientName =($scope.patientList[index].firstName+' '+$scope.patientList[index].middleName+' '+$scope.patientList[index].lastName);
								$scope.uhidNumber = ($scope.patientList[index].uhidNumber);
								$scope.mobileNumber =  ($scope.patientList[index].mobileNumber);

							}
							
							
							//Search Appointment List By Filters
							$scope.searchAppointment = function()
							{
								//alert();
								$scope.doctorSearchFlag = 'Y';
								$scope.patientSearchFlag = 'N';
								$scope.appointmentSearchObject.fromDate = moment($scope.datepickerFromDate).format('YYYY-MM-DD');
								$scope.appointmentSearchObject.toDate = moment($scope.datepickerToDate).format('YYYY-MM-DD');
								$scope.appointmentSearchObject.appointmentTypeId  =$scope.slotTypeId ;
								//$scope.appointmentSearchObject.appointmentStatusId = 0;
								
								console.log($scope.appointmentSearchObject);
							//	return false;
								var URI = BASE_URL + ROOT_URL + SEARCHAPPOINTMENTSBYCRITERIA;
						        BillingGenericService
								.serviceAction(METHOD_POST, URI, $scope.appointmentSearchObject,
										NOTIFICATION_MSG_STATUS_FALSE)
								.then(
										function(response) {
											console.log(response);
											$scope.appointmentList = [];
											// $rootScope.startLoader();
											if (response.data.status == "success")
												$scope.appointmentList = response.data.listObject;
											    $scope.allCount =response.data.listObject.length;
											    //$scope.pendingCount = response.data.object[0].pending;
											    //$scope.checkInCount = response.data.object[0].checkIn;
											    //$scope.rescheduledAndCancelledCount  = response.data.object[0].rescheduledAndCancelled;
											    
											    $scope.pendingCount = 0;
											    $scope.checkInCount = 0;
											    $scope.rescheduledAndCancelledCount  = 0;
											    $scope.completeCount  = 0;
											    $scope.checkOutCount  = 0;
											    $scope.admittedCount  = 0;
											    angular.forEach($scope.appointmentList,function(value,index) {
														
											    	 switch (value.appointmentStatusId) {
											            case 1:
											            	 $scope.pendingCount +=1;
											                break;
											            case 2:
											            	$scope.rescheduledAndCancelledCount +=1;
											            	break;
											            case 3:
											            	$scope.rescheduledAndCancelledCount +=1;
											            	break;
											            case 5:
											            	$scope.checkInCount +=1;
											                break;
											            case 6:
											            	 $scope.completeCount +=1;
											            	 break;
											            case 7:
											            	 $scope.checkOutCount +=1;
											            	 break;
											            default:

											        }
											    	 
											    	 if(value.admissionId > 0)
										    		 {
										    		 	$scope.admittedCount +=1;
										    		 }
														
												});
											    
											    $scope.allCount += $scope.admittedCount;
											    
										    	
										});
						        var URI = BASE_URL + ROOT_URL
								+ BLOCKEDSCHEDULEAPPOINTMENTLIST;
						GenericService
								.serviceAction("POST", URI, $scope.appointmentSearchObject)
								.then(
										function(response) {
											$scope.reScheduleObjList = [];

											if (response.data.status == "success") {
												$scope.reScheduleObjList = response.data.listObject;
												console.log("resdlist",$scope.reScheduleObjList);

											}
										});
						
							}
							
							
							//Just Test
							

							/* $interval(function() {
								 //alert('Interval');
								 var statusId = $scope.listStatusId;
								if($scope.doctorSearchFlag == 'N')
									{
										$scope.commonAppointmentListForInterval();
										$scope.commonAppointmentListByStatusIdForInterval(statusId);
									// alert('All Record');
									}
								 else
									 {
									// alert('Search Record');
									 }
					          }, 5000);*/
							
						//Just Test End 
							$scope.commonAppointmentList  = function()
							{
								var doctorModalityObj = {
										unitId : $scope.unitId,
										organizationId : $scope.organizationId,
										appointmentTypeId : $scope.slotTypeId,
										appointmentStatusId : 0,
										appointmentDate:moment(new Date()).format('YYYY-MM-DD'),
										patientId:0,
										specialityId:0,
										doctorId:0,
										modalityId:0,
										visitTypeId:0
									};

									//For Get Appointment List
									var URI = BASE_URL + ROOT_URL
											+ GETAPPOINTMENTSCHEDULELIST;
									BillingGenericService
											.serviceAction(METHOD_POST, URI,
													doctorModalityObj,
													NOTIFICATION_MSG_STATUS_FALSE)
											.then(
													function(response) {
														console.log(response);
														// $rootScope.startLoader();
														if (response.data.status == "success")
															$scope.commonAppointmentLists = response.data.listObject;
														    $scope.allCount =response.data.listObject.length;
														    //$scope.pendingCount = response.data.object[0].pending;
														    //$scope.checkInCount = response.data.object[0].checkIn;
														    //$scope.rescheduledAndCancelledCount  = response.data.object[0].rescheduledAndCancelled;
														    
														    $scope.pendingCount = 0;
														    $scope.checkInCount = 0;
														    $scope.rescheduledAndCancelledCount  = 0;
														    $scope.completeCount  = 0;
														    $scope.checkOutCount  = 0;
														    $scope.admittedCount = 0;
														    
														    angular.forEach($scope.commonAppointmentLists,function(value,index) {
																	
														    	 switch (value.appointmentStatusId) {
														            case 1:
														            	 $scope.pendingCount +=1;
														                break;
														            case 2:
														            	$scope.rescheduledAndCancelledCount +=1;
														            	break;
														            case 3:
														            	$scope.rescheduledAndCancelledCount +=1;
														            	break;
														            case 5:
														            	$scope.checkInCount +=1;
														                break;
														            case 6:
														            	 $scope.completeCount +=1;
														            	 break;
														            case 7:
														            	 $scope.checkOutCount +=1;
														            	 break;
														            default:

														        }
														    	 
														    	 if(value.admissionId > 0)
													    		 {
													    		 	$scope.admittedCount +=1;
													    		 	
													    		 }
																	
															});
														    $scope.allCount += $scope.admittedCount;
													});
							}
							
							$scope.commonAppointmentListByStatusId  = function(id)
							{
								var doctorModalityObj = {
										unitId : $scope.unitId,
										organizationId : $scope.organizationId,
										appointmentTypeId :$scope.slotTypeId,
										appointmentStatusId : id,
										appointmentDate:moment(new Date()).format('YYYY-MM-DD'),
										patientId:0,
										specialityId:0,
										doctorId:0,
										modalityId:0,
										visitTypeId:0
									};

									//For Get Appointment List
									var URI = BASE_URL + ROOT_URL
											+ GETAPPOINTMENTSCHEDULELIST;
									BillingGenericService
											.serviceAction(METHOD_POST, URI,
													doctorModalityObj,
													NOTIFICATION_MSG_STATUS_FALSE)
											.then(
													function(response) {
														console.log(response);
														// $rootScope.startLoader();
														if (response.data.status == "success")
															$scope.appointmentList = response.data.listObject;
													});
							}
							
							$scope.getPendingAppointmentList = function(statusId)
							{
								$scope.listStatusId = statusId;
								//alert($scope.doctorSearchFlag);
								//alert($scope.patientSearchFlag);
									if($scope.doctorSearchFlag == 'N' && $scope.patientSearchFlag =='N')
									{
										
										$scope.commonAppointmentList();
										$scope.commonAppointmentListByStatusId(statusId);
									}
									else if($scope.doctorSearchFlag == 'Y')
										{
										$scope.appointmentSearchObject.fromDate = moment($scope.datepickerFromDate).format('YYYY-MM-DD');
										$scope.appointmentSearchObject.toDate = moment($scope.datepickerToDate).format('YYYY-MM-DD');
										$scope.appointmentSearchObject.appointmentTypeId  = $scope.slotTypeId;
										$scope.appointmentSearchObject.appointmentStatusId = statusId;
										
										console.log($scope.appointmentSearchObject);
										//return false; 
										var URI = BASE_URL + ROOT_URL + GETAPPOINTMENTSCHEDULELIST;
								        BillingGenericService
										.serviceAction(METHOD_POST, URI, $scope.appointmentSearchObject,
												NOTIFICATION_MSG_STATUS_FALSE)
										.then(
												function(response) {
													console.log(response);
													$scope.appointmentList = [];
													// $rootScope.startLoader();
													if (response.data.status == "success")
														$scope.appointmentList = response.data.listObject;
													   
													switch (statusId) {
													
													case 0:
														$scope.allCount = $scope.appointmentList.length;
														break;
										            case 1:
										            	 $scope.pendingCount = $scope.appointmentList.length;
										                break;
										            case 2:
										            	$scope.rescheduledAndCancelledCount = $scope.appointmentList.length;
										            	break;
										            case 3:
										            	$scope.rescheduledAndCancelledCount = $scope.appointmentList.length;
										            	break;
										            case 5:
										            	$scope.checkInCount = $scope.appointmentList.length;
										                break;
										            case 6:
										            	 $scope.completeCount = $scope.appointmentList.length;
										            	 break;
										            case 7:
										            	 $scope.checkOutCount  = $scope.appointmentList.length;
										            	 break;
										            default:

										        }
													
													
														
													 
													
												});
										
										}
							}
							
							$scope.clearSearchObject = function()
							{
								$scope.appointmentSearchObject = {
							    		 'doctorId':0,
							    		 'specialityId':0,
							    		 'organizationId':$scope.organizationId,
							    		 'unitId':$scope.unitId,
							    		 'patientId':0,
							    		 'specialityId':0,
							    		 'subSpecialityId':0,
							    		 'modalityId':0,
							    		 'visitTypeId':0
							     }
								
								$scope.patientName ='';
								$scope.uhidNumber = '';
								$scope.mobileNumber = '';
								
								$scope.datepickerFromDate = new Date();
								$scope.datepickerToDate = new Date();
								
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
										+ PATIENTAUTOFILLSEARCH;
								
								return BillingGenericService
										.serviceAction(METHOD_POST, URI,
												data,
												NOTIFICATION_MSG_STATUS_FALSE)
										.then(
												function(response) {
													console.log(response);
													// $rootScope.startLoader();
													if (response.data.status == "success")
														return response.data.listObject;
												});

							}
							
							$scope.selectPatientId = function(info, model,
									label) {
								//alert(info.patientId);
								
								$scope.patientName = info.patientName;
								$scope.uhidNumber = info.uhidNumber;
								$scope.mobileNumber = info.mobileNumber;
								
								$scope.appointmentSearchObject.patientId = info.patientId;
							}
							
							//Function For Reshedule Appointment
							$scope.rescheduleAppointment = function() {
								//alert(moment($scope.datepickerReschedule).format('DD-MM-YYYY'));
								var cookieObjectReschedule = {
									'slotId' : $scope.selectAppointment.slotId,
									'appointmentId' : $scope.selectAppointment.appointmentId,
									'rescheduleReasonId' : $scope.rescheduleReasonId,
									'reschedulledBy' : $scope.reschedulledBy,
									'rescheduleAppointmentDate' : moment($scope.datepickerReschedule).format('DD/MM/YYYY'),
									'patientId' : $scope.selectAppointment.patientId,
									'prefixId' : $scope.selectAppointment.prefixId,
									'firstName' : $scope.selectAppointment.firstName,
									'middleName' : $scope.selectAppointment.middleName,
									'lastName' : $scope.selectAppointment.lastName,
									'age' : $scope.selectAppointment.age,
									'dob' : $scope.selectAppointment.dob,
									'genderId' : $scope.selectAppointment.genderId,
									'uhIdNumber' : $scope.selectAppointment.uhidNumber,
									'mobileNumber' : $scope.selectAppointment.mobileNumber,
									'email' : $scope.selectAppointment.email,
									'remark' : $scope.selectAppointment.remark

								};

								if ($scope.slotTypeId == 1) {
									cookieObjectReschedule.specialityId = $scope.selectAppointment.specialityId;
									cookieObjectReschedule.doctorId = $scope.selectAppointment.doctorId;
									cookieObjectReschedule.specialityName = $scope.selectAppointment.specialityName;
									cookieObjectReschedule.doctorName = $scope.selectAppointment.doctorName;
									cookieObjectReschedule.selectType = "doctor";
									cookieObjectReschedule.selectedSlotId = 1;
								} else if ($scope.slotTypeId == 2){
									cookieObjectReschedule.modalityId = $scope.selectAppointment.modalityId;
									cookieObjectReschedule.specialityName = $scope.selectAppointment.modalityTypeDesc;
									cookieObjectReschedule.doctorName = $scope.selectAppointment.modalityDesc;
									cookieObjectReschedule.selectedSlotId = 2;
									cookieObjectReschedule.selectType = "modality";
								}
								else if($scope.slotTypeId == 3)
									{
									cookieObjectReschedule.modalityId = $scope.selectAppointment.modalityId;
									cookieObjectReschedule.specialityName = $scope.selectAppointment.modalityTypeDesc;
									cookieObjectReschedule.doctorName = $scope.selectAppointment.modalityDesc;
									cookieObjectReschedule.selectedSlotId = 3;
									cookieObjectReschedule.selectType = "modality";
										//return false;
									}
								//return false;
								$cookies.putObject('cookieObjectReschedule',
										cookieObjectReschedule);

								console.log(cookieObjectReschedule);
								//angular.element('#appRescheduleModal').modal('hide');
								//$state.go('appointmentScheduling.day');
								//return false;
								$timeout(function() {
									if($scope.slotTypeId != 3)
										{
											$state.go('appointmentScheduling.day');
										}
									else
										{
										$state.go('appointmentScheduling');
										}
									
								}, 500);
							}

							$scope.getPatientDetailsPopUp = function(item) {
								$scope.patientMobileNumber = item.mobileNumber;
								$scope.patientEmail = item.email;

							}
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
									$scope.secondTabLevel = 1;
									var doctorModalityObj = {
										unitId : $scope.unitId,
										organizationId : $scope.organizationId,
										appointmentTypeId : newTab,
										appointmentStatusId : 0,
										appointmentDate:moment(new Date()).format('YYYY-MM-DD'),
										patientId:0,
										specialityId:0,
										doctorId:0,
										specialityId:0,
										subSpecialityId:0,
										modalityId:0,
										visitTypeId:0
									};

									var URI = BASE_URL + ROOT_URL
											+ GETAPPOINTMENTSCHEDULELIST;

									BillingGenericService
											.serviceAction(METHOD_POST, URI,
													doctorModalityObj,
													NOTIFICATION_MSG_STATUS_FALSE)
											.then(
													function(response) {
														console.log(response);
														// $rootScope.startLoader();
														if (response.data.status == "success")
															$scope.appointmentList = response.data.listObject;
														$scope.allCount = response.data.listObject.length;
														
														  $scope.pendingCount = 0;
														    $scope.checkInCount = 0;
														    $scope.rescheduledAndCancelledCount  = 0;
														    $scope.completeCount  = 0;
														    $scope.checkOutCount  = 0;
														    $scope.admittedCount  = 0;
														    
														    angular.forEach($scope.appointmentList,function(value,index) {
																	
														    	 switch (value.appointmentStatusId) {
														            case 1:
														            	 $scope.pendingCount +=1;
														                break;
														            case 2:
														            	$scope.rescheduledAndCancelledCount +=1;
														            	break;
														            case 3:
														            	$scope.rescheduledAndCancelledCount +=1;
														            	break;
														            case 5:
														            	$scope.checkInCount +=1;
														                break;
														            case 6:
														            	 $scope.completeCount +=1;
														            	 break;
														            case 7:
														            	 $scope.checkOutCount +=1;
														            	 break;
														            default:

														        }
														    	 
														    	 if(value.admissionId > 0)
													    		 {
													    		 	$scope.admittedCount +=1;
													    		 	$scope.allCount += $scope.admittedCount;
													    		 }
																	
															});
														  
															var URI = BASE_URL + ROOT_URL
															+ BLOCKEDSCHEDULEAPPOINTMENTLIST;
													GenericService
															.serviceAction("POST", URI, doctorModalityObj)
															.then(
																	function(response) {
																		$scope.reScheduleObjList = [];

																		if (response.data.status == "success") {
																			$scope.reScheduleObjList = response.data.listObject;

																		}
																	});
														    
													});

								} else if (newTab == 2) {
									$("#case-sheet").css("background-color",
											"#5ab7c8");
									$("#complaints").css("background-color",
											"#5F9EA0");
									$scope.slotTypeId = 2;
									$scope.secondTabLevel = 1;
									var doctorModalityObj = {
										unitId : $scope.unitId,
										organizationId : $scope.organizationId,
										appointmentTypeId : newTab,
										appointmentStatusId : 0,
										appointmentDate:moment(new Date()).format('YYYY-MM-DD'),
										patientId:0,
										specialityId:0,
										doctorId:0,
										specialityId:0,
										subSpecialityId:0,
										modalityId:0,
										visitTypeId:0
									};
									
									var URI = BASE_URL + ROOT_URL
											+ GETAPPOINTMENTSCHEDULELIST;

									BillingGenericService
											.serviceAction(METHOD_POST, URI,
													doctorModalityObj,
													NOTIFICATION_MSG_STATUS_FALSE)
											.then(
													function(response) {
														console.log(response);
														// $rootScope.startLoader();
														if (response.data.status == "success")
															$scope.appointmentList = response.data.listObject;
														$scope.allCount = response.data.listObject.length;
														
														  $scope.pendingCount = 0;
														    $scope.checkInCount = 0;
														    $scope.rescheduledAndCancelledCount  = 0;
														    $scope.completeCount  = 0;
														    $scope.checkOutCount  = 0;
														    $scope.admittedCount =0;
														    
														    angular.forEach($scope.appointmentList,function(value,index) {
																	
														    	 switch (value.appointmentStatusId) {
														            case 1:
														            	 $scope.pendingCount +=1;
														                break;
														            case 2:
														            	$scope.rescheduledAndCancelledCount +=1;
														            	break;
														            case 3:
														            	$scope.rescheduledAndCancelledCount +=1;
														            	break;
														            case 5:
														            	$scope.checkInCount +=1;
														                break;
														            case 6:
														            	 $scope.completeCount +=1;
														            	 break;
														            case 7:
														            	 $scope.checkOutCount +=1;
														            	 break;
														            default:

														        }
														    	 
														    	 if(value.admissionId > 0)
													    		 {
													    		 	$scope.admittedCount +=1;
													    		 	$scope.allCount += $scope.admittedCount;
													    		 }
																	
															});
														    
													});
								}
								else if (newTab == 3) {
									$("#case-sheet").css("background-color",
											"#5ab7c8");
									$("#complaints").css("background-color",
											"#5F9EA0");
									$scope.slotTypeId = 3;
									$scope.secondTabLevel = 111;

									var ehcObject = {
										unitId : $scope.unitId,
										organizationId : $scope.organizationId,
										appointmentTypeId : newTab,
										appointmentStatusId : 0,
										appointmentDate:moment(new Date()).format('YYYY-MM-DD'),
										patientId:0,
										specialityId:0,
										doctorId:0,
										specialityId:0,
										subSpecialityId:0,
										modalityId:0,
										visitTypeId:0
										//packageId:0,
									};
									
									var URI = BASE_URL + ROOT_URL
											+ GETAPPOINTMENTSCHEDULELIST;

									BillingGenericService
											.serviceAction(METHOD_POST, URI,
													ehcObject,
													NOTIFICATION_MSG_STATUS_FALSE)
											.then(
													function(response) {
														console.log(response);
														// $rootScope.startLoader();
														if (response.data.status == "success")
															$scope.appointmentList = response.data.listObject;
														$scope.allCount = response.data.listObject.length;
														
														  $scope.pendingCount = 0;
														    $scope.checkInCount = 0;
														    $scope.rescheduledAndCancelledCount  = 0;
														    $scope.completeCount  = 0;
														    $scope.checkOutCount  = 0;
														    $scope.admittedCount =0;
														    
														    angular.forEach($scope.appointmentList,function(value,index) {
																	
														    	 switch (value.appointmentStatusId) {
														            case 1:
														            	 $scope.pendingCount +=1;
														                break;
														            case 2:
														            	$scope.rescheduledAndCancelledCount +=1;
														            	break;
														            case 3:
														            	$scope.rescheduledAndCancelledCount +=1;
														            	break;
														            case 5:
														            	$scope.checkInCount +=1;
														                break;
														            case 6:
														            	 $scope.completeCount +=1;
														            	 break;
														            case 7:
														            	 $scope.checkOutCount +=1;
														            	 break;
														            default:

														        }
														    	 
														    	 if(value.admissionId > 0)
													    		 {
													    		 	$scope.admittedCount +=1;
													    		 	$scope.allCount += $scope.admittedCount;
													    		 }
																	
															});
														    
													});
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
							};

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

							//--------- function for goto Encounter Page for encouter creation -----------//
							
							$scope.gotoEncounter=function(appointmentObj){
								console.log(appointmentObj);
								$state.go("encounter.encounterDetails", {
									appointmentObj: appointmentObj,
								});
							}
							
							$scope.gotoCoverSheet = function(appointmentObj)
							{
								$state.go("OPDCoverSheet", {
									appointmentObj: appointmentObj,
								});
							}
						} ]);