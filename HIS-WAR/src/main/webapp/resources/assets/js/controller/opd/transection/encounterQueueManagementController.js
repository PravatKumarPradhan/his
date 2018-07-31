'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:encounterQueueManagementController
 * @description #encounterQueueManagementController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'encounterQueueManagementController',
				[
						'$scope',
						'$cookies',
						'$rootScope',
						'GenericService',
						'BillingGenericService',
						'$state',
						'$timeout',
						'$interval',
						'GenericServiceParamHeader',
						function($scope, $cookies, $rootScope, GenericService,
								BillingGenericService, $state, $timeout,$interval,GenericServiceParamHeader) {

							/*alert("encounterQueueManagementController");*/

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
								$scope.roleId = 2;

								$rootScope.loginpage = true;
								$scope.saveBtnFlag = false;
								$scope.updateBtnFlag = true;

								// for popup
								$scope.showModal = false;
								$scope.buttonClicked = "";
								$scope.popUpFlag = true;
								// end for popup
								
								$scope.slotTypeId = 1;

								
								var commonObj = {
									unitId : $scope.unitId,
									organizationId : $scope.organizationId
								};
								var data = {};
								var headerObj = {
										organization_id:$scope.organizationId,
										unit_id : $scope.unitId
								}
								var paramObj = {};

								//For Get Encounter List
								var URI = BASE_URL + ROOT_URL
										+ GETLISTOFENCOUNTERMASTER;
								GenericServiceParamHeader
										.serviceAction(METHOD_GET, URI,
												data,
												NOTIFICATION_MSG_STATUS_FALSE,paramObj,headerObj)
										.then(
												function(response) {
													console.log(response);
													// $rootScope.startLoader();
													if (response.data.status == "success")
														$scope.allEncounterList = response.data.listObject;
												});
								
								
								//For Get Nursingstation List
								var URI = BASE_URL + ROOT_URL
										+ GETLISTOFNURSINGSTATION;
								GenericServiceParamHeader
										.serviceAction(METHOD_GET, URI,
												data,
												NOTIFICATION_MSG_STATUS_FALSE,paramObj,headerObj)
										.then(
												function(response) {
													console.log(response);
													// $rootScope.startLoader();
													if (response.data.status == "success")
														$scope.nursingStationList = response.data.listObject;
												});
												
												//For Get Nursingstation List
												var URI = BASE_URL + ROOT_URL
														+ GETLISTOFAPPOINTMENTSTATUSMASTER;
												GenericServiceParamHeader
														.serviceAction(METHOD_GET, URI,
																data,
																NOTIFICATION_MSG_STATUS_FALSE,paramObj,headerObj)
														.then(
																function(response) {
																	console.log(response);
																	// $rootScope.startLoader();
																	if (response.data.status == "success")
																		$scope.encounterStatusList = response.data.listObject;
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
								     
								     $scope.encounterSearchObject = {
								    		 'doctorId':0,
								    		 'specialityId':0,
								    		 'patientId':0,
								    		 'specialityId':0,
								    		 'statusId':0,
								    		 'clinicId':0,
								    		 'nursingStationId':0
								     }
							}

							$scope.init();
							
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
							$scope.getClinicByDoctorId = function(doctorId)
							{
								var data = {};
								var paramObj = {
										'doctor_id' : doctorId
								};
								var headers={
										'organization_id': $scope.organizationId,
										'unit_id':$scope.unitId,
										'Content-Type':'application/json'
								};
								
								
							   // return false;
							    var URI = BASE_URL + ROOT_URL
								+ GETCLINICMASTERLISTBYDOCTORID;
						
								GenericServiceParamHeader
								.serviceAction(METHOD_GET, URI,
										data,
										NOTIFICATION_MSG_STATUS_FALSE,paramObj,headers)
								.then(
										function(response) {
											console.log(response);
											// $rootScope.startLoader();
											$scope.clinicList = [];
											if (response.data.status == "success")
												
												$scope.clinicList =response.data.listObject;
												console.log("Clnic List",$scope.clinicList);
										});
							}
							
							
							$scope.getNursingStationByClinicId = function(clinicId)
							{
								var data = {};
								
								var paramObj = {
										'clinic_id' : clinicId
								};
								
								var headers={
										'organization_id': $scope.organizationId,
										'unit_id':$scope.unitId,
										'Content-Type':'application/json'
								};
								
								
							   // return false;
							    var URI = BASE_URL + ROOT_URL
								+ GETLISTOFNURSINGSTATIONBYCLINICID;
						
								GenericServiceParamHeader
								.serviceAction(METHOD_GET, URI,
										data,
										NOTIFICATION_MSG_STATUS_FALSE,paramObj,headers)
								.then(
										function(response) {
											console.log(response);
											// $rootScope.startLoader();
											//$scope.clinicList = [];
											if (response.data.status == "success")
												
												console.log("Clnic Nursing List",response.data.listObject[0].nursingStationId);
											$scope.encounterSearchObject.nursingStationId = response.data.listObject[0].nursingStationId.toString();
										});
							}
							
							
							$scope.nursingStationChange = function()
							{
								$scope.encounterSearchObject.doctorId = '0';
								$scope.encounterSearchObject.clinicId = '0';
							}
							
							//Search Appointment List By Filters
							$scope.searchEncounter = function()
							{
								$scope.encounterSearchObject.fromDate = moment($scope.datepickerFromDate).format('YYYY-MM-DD');
								$scope.encounterSearchObject.toDate = moment($scope.datepickerToDate).format('YYYY-MM-DD');
								
								console.log($scope.encounterSearchObject);
								
								var paramObj = {};
								var headers={
										'organization_id': $scope.organizationId,
										'unit_id':$scope.unitId,
										'Content-Type':'application/json'
								};
								
								
							   // return false;
							    var URI = BASE_URL + ROOT_URL
								+ GETSEARCHEDLISTENCOUNTERMASTER;
						
								GenericServiceParamHeader
								.serviceAction(METHOD_POST, URI,
										$scope.encounterSearchObject,
										NOTIFICATION_MSG_STATUS_FALSE,paramObj,headers)
								.then(
										function(response) {
											console.log(response);
											// $rootScope.startLoader();
											$scope.allEncounterList = [];
											if (response.data.status == "success")
												
												$scope.allEncounterList =response.data.listObject;
										});
						
							}
							
						
							$scope.clearSearchObject = function()
							{
								$scope.encounterSearchObject = {
							    		 'doctorId':0,
							    		 'specialityId':0,
							    		 'organizationId':$scope.organizationId,
							    		 'unitId':$scope.unitId,
							    		 'patientId':0,
							    		 'specialityId':0,
							    		 'statusId':0,
							    		 'nursingStationId':0,
							    		 'clinicId':0
							     }
								
								$scope.clinicList = [];
								
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
								
								$scope.patientName = info.patientName;
								$scope.uhidNumber = info.uhidNumber;
								$scope.mobileNumber = info.mobileNumber;
								
								$scope.encounterSearchObject.patientId = info.patientId;
							}
						
							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.saveAppointmentSchedule();
							}

							$scope.getAppointmentDetails = function(item)
							{
								$scope.appointmentDate = item.appointmentDate;
								$scope.appointmentFromTimeString = item.fromTimeString;
								$scope.appointmentRemark = item.remark;
							}
							
							$scope.getRefralDetails = function(item)
							{
								$scope.referralTypeName = item.referralTypeName;
								$scope.referralName = item.referralName;
								$scope.referralRemark = item.referralRemark;
							}
							
							$scope.gotoCoverSheet = function(encounterObj)
							{
								//alert();
								if(encounterObj.isAssignmentCompStatus == 'N' || encounterObj.isConsultationStatus == 'N' 
									|| encounterObj.isAssignmentCompStatus == null || encounterObj.isConsultationStatus == null )
								{
									
									var headerObj={
											'organization_id': $scope.organizationId,
											'unit_id':$scope.unitId,
											'Content-Type':'application/json'
									};
									
										var data = {
											'assignmentStartTime':moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
											'assignmentStopTime':"",
											'isAssignmentCompStatus':'N',
											'assignmentStartBy':$scope.userId,
											'assignmentStopBy': 0,
											'assignmentStartRoleId': $scope.roleId,
											'assignmentStopRoleId': 0,
											'isConsultationStatus': "",
											'consultationStartTime': "",
											'consultationStopTime': "",
											'consultationStartBy': "",
											'consultationStopBy': "",
											'consultationStartRoleId': "",
											'consultationStopRoleId': "",
											'encounterId': encounterObj.encounterId,
											"patientId": encounterObj.patientId
										}
										

										var paramObjForPer={};
										var URI = BASE_URL + ROOT_URL
										+ ENCOUNTERDETAILS;
										GenericServiceParamHeader
											.serviceAction(METHOD_POST, URI,
													data,
													NOTIFICATION_MSG_STATUS_TRUE,paramObjForPer,headerObj)
											.then(
													function(response) {
														console.log(response);
														console.log(encounterObj.encounterId);
														
														// $rootScope.startLoader();
														if (data.status == "success")
															console.log("in success");
															var cookieObject = 
															{
																	"patientName" :encounterObj.patientName,
																	"uhidNumber" : encounterObj.uhidNumber,
																	"genderCode" : encounterObj.genderCode,
																	"age":  encounterObj.age,
																	"dob": encounterObj.dob,
																	"encounterId":encounterObj.encounterId,
																	"genderId":encounterObj.genderId,
																	"roleId" :$scope.roleId,
																	"patientId":encounterObj.patientId,
																	
															}
															 $cookies.putObject('cookieEncounterObject',cookieObject);	
															console.log("EO",cookieObject);
															//return false;
															$state.go("OPDCoverSheet");
													});
										
								}
								else
								{
									var cookieObject = 
									{
											"patientName" :encounterObj.patientName,
											"uhidNumber" : encounterObj.uhidNumber,
											"genderCode" : encounterObj.genderCode,
											"age":  encounterObj.age,
											"dob": encounterObj.dob,
											"encounterId":encounterObj.encounterId,
											"genderId":encounterObj.genderId,
											"roleId" :$scope.roleId,
											"patientId":encounterObj.patientId,
											
									}
									 $cookies.putObject('cookieEncounterObject',cookieObject);	
									console.log("EO",cookieObject);
									//return false;
									$state.go("OPDCoverSheet");
								}
								
								
							}
							
							
							
						} ]);