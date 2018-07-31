'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:OPDCoverSheetController
 * @description #OPDCoverSheetController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'OPDCoverSheetController',
				[
						'$scope',
						'$cookies',
						'$rootScope',
						'GenericService',
						'BillingGenericService',
						'$state',
						'$timeout',
						'$interval',
						'$stateParams',
						'GenericServiceParamHeader',
						function($scope, $cookies, $rootScope, GenericService,
								BillingGenericService, $state, $timeout,$interval,$stateParams,GenericServiceParamHeader) {

							//alert("OPDCoverSheetController");
							
							
							 /*$scope.labels = ["January", "February", "March", "April", "May", "June", "July"];
							    $scope.series = ['Series A', 'Series B'];
							    $scope.data = [
							        [65, 59, 80, 81, 56, 55, 40]
							    ];*/
							    $scope.datasetOverride = [{
							        yAxisID: 'y-axis-1'
							    }, {
							        yAxisID: 'y-axis-2'
							    }];
							    $scope.options = {
							        scales: {
							            yAxes: [{
							                id: 'y-axis-1',
							                type: 'linear',
							                display: true,
							                position: 'left'
							            }]
							        }
							    };

							/* init() function for form object create. */
							$scope.init = function() {
								 $scope.dateLetter = new Date();
								
                                
                                $scope.openDatePicker = function(index) {

                                    for (var i = $scope.drugHistoryList.length - 1; i >= 0; i--) {
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
                                
                                
                                
                                
                                //for Travel
                                $scope.openDatePickerTravel = function(index) {

                                    for (var i = $scope.historyTravelsList.length - 1; i >= 0; i--) {
                                        if (i === index) {

                                            $scope.datepickerTravel.opened[i] = true;
                                        } else {
                                            $scope.datepickerTravel.opened[i] = false;
                                        }
                                    }
                                };

                                $scope.datepickerTravel = {
                                    opened : [ false ],

                                };
                                //End Travel
                                

    							$scope.dateOptions = {
    								formatYear : 'yyyy',
    								showWeeks : false
    							};

    							$scope.openDatePickerWhen = function() {
    								$scope.datepickerWhen.opened = true;
    							};

    							$scope.datepickerWhen = {
    								opened : false
    							};
    							
    							$scope.openDatePickerImmunization = function() {
    								$scope.datepickerImmunization.opened = true;
    							};

    							$scope.datepickerImmunization = {
    								opened : false
    							};
                                
                                $scope.patient = {
                                		//exercise: ['Yoga']
                                	  };

								
								// var vm = this;

								  /* $scope.priceSlider = {
								        value: 200,
								        options: {
								            floor: 0,
								            ceil: 500
								        }
								    }*/
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
								
								$scope.allergyButtonFlag = true;
								
								
								//Start get 50 Previous Year with Current Year and month
								
								 var year = new Date().getFullYear();
							      var range = [];

							      range.push(year);

							      for (var i = 1; i < 50; i++) {
							        range.push(year - i);
							      }

							      $scope.years = range;
							      
							      
							      $scope.months = [];
						            $scope.months.push({value:1, text:'January'});
						            $scope.months.push({value:2, text:'February'});
						            $scope.months.push({value:3, text:'March'});
						            $scope.months.push({value:4, text:'April'});
						            $scope.months.push({value:5, text:'May'});
						            $scope.months.push({value:6, text:'June'});
						            $scope.months.push({value:7, text:'July'});
						            $scope.months.push({value:8, text:'August'});
						            $scope.months.push({value:9, text:'September'});
						            $scope.months.push({value:10, text:'October'});
						            $scope.months.push({value:11, text:'November'});
						            $scope.months.push({value:12, text:'December'});
							      
							      
							      //End  50 Previous Year with Current Year and month
						            
						            $scope.oldPersonalHistory = false;
						            
						            $scope.noSignificantPersonal = 'N';
						            $scope.noSignificantFamiliy = 'N';
						            $scope.noSignificantSurgical = 'N';
						            $scope.noSignificantMedical = 'N';
						            
								$scope.patientDetails = 
									{
										uhIdNumber:"",
										patientName:"",
										genderCode:"",
										age:"",
										birthDate:"",
										encounterId:""
									};
								
								
							
								
								//-------------- function to check create opd cover sheet based on appointment -------------//
								$scope.checkAppointment=function(){
									 /*console.log("stateObj", $stateParams.appointmentObj);
									$scope.appointmentDetailsObj=$stateParams.appointmentObj;
									 console.log($scope.appointmentDetailsObj);
									//alert($scope.appointmentDetailsObj.length);
									 if($scope.appointmentDetailsObj != null)
										 {
										
											 $scope.patientDetails.patientName = $scope.appointmentDetailsObj.patientName;
											 $scope.patientDetails.uhIdNumber = $scope.appointmentDetailsObj.uhidNumber;
											 $scope.patientDetails.genderCode = $scope.appointmentDetailsObj.genderCode;
											 $scope.patientDetails.age = $scope.appointmentDetailsObj.age;
											 $scope.patientDetails.birthDate = $scope.appointmentDetailsObj.dob;
											 $scope.patientDetails.appointmentId = $scope.appointmentDetailsObj.appointmentId;
										 }*/
									
									$scope.encounterDetailsObj = $cookies.getObject('cookieEncounterObject');
									
									console.log("Encounter Object",$scope.encounterDetailsObj);
									
									if ($scope.encounterDetailsObj  != null) {
											$scope.patientDetails.patientName = $scope.encounterDetailsObj.patientName;
											$scope.patientDetails.uhIdNumber = $scope.encounterDetailsObj.uhidNumber;
											$scope.patientDetails.genderCode = $scope.encounterDetailsObj.genderCode;
											$scope.patientDetails.age = $scope.encounterDetailsObj.age;
											$scope.patientDetails.birthDate = $scope.encounterDetailsObj.dob;
											$scope.patientDetails.encounterId = $scope.encounterDetailsObj.encounterId;
											$scope.roleId = $scope.encounterDetailsObj.roleId;
									}
									else
										{
											$state.go('encounterQueueManagement');
											return;
										}
									
								}
								
							
								$scope.checkAppointment();
								

								$rootScope.loginpage = true;
								$scope.saveBtnFlag = false;
								$scope.updateBtnFlag = true;

								// for popup
								$scope.showModal = false;
								$scope.buttonClicked = "";
								$scope.popUpFlag = true;
								// end for popup
								
							
								
								var commonObj = {
									unitId : $scope.unitId,
									organizationId : $scope.organizationId
								};
								var data = {};

								
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
								     
								  
								     
								     var headerObj = {
								    		 unit_id : $scope.unitId,
												organization_id : $scope.organizationId
								     };
								     
								     var paramObj = {
								    		 encounterId : $scope.encounterDetailsObj.encounterId,
								    		 roleId : $scope.roleId
									    		 //AppointmentId : 14,
									     };
								     
								     console.log(headerObj);
								     
								     
								     
								     //For Get Active Complaints List
										var URI = BASE_URL + ROOT_URL
											+ GETCOMPLAINTSLIST;
										GenericServiceParamHeader
											.serviceAction(METHOD_GET, URI,
													data,
													NOTIFICATION_MSG_STATUS_FALSE,paramObj,headerObj)
											.then(
													function(response) {
														console.log(response);
														// $rootScope.startLoader();
														if (response.data.status == "success")
															$scope.complaintsPatientList = response.data.listObject;
														$scope.complaintsPatientListDoctor = [];
															if($scope.roleId == '2')
																{
																	
																	angular.forEach(response.data.listObject, function(value,key) {
																		
																			if(value.roleId == 3 && value.isReviewedFlag == 'N')
																				{
																			var data = {};
																			data.diagnosisId = value.diagnosisId;
																			data.since = value.since;
																			data.duration = value.duration;
																			data.remark = value.remark;
																			data.isReviewedFlag = 'N',
																			data.complaintAppoId  = value.complaintAppoId;
																			$scope.complaintsPatientListDoctor.push(data);
																	}
																	});
																}
															
															
															
													});
										
										 //For Get Active History Personal
										
										var paramObjForPer = {
												patientId:$scope.encounterDetailsObj.patientId,
												encounterId : $scope.encounterDetailsObj.encounterId,
									    		roleId : $scope.roleId
										};
									
										var URI = BASE_URL + ROOT_URL
										+ GETOLDPATIENTPERSONALHISTORYDETAILS;
									GenericServiceParamHeader
										.serviceAction(METHOD_GET, URI,
												data,
												NOTIFICATION_MSG_STATUS_FALSE,paramObjForPer,headerObj)
										.then(
												function(response) {
													console.log(response);
													// $rootScope.startLoader();
													if (response.data.status == "success")
													$scope.personalHistoryOldList = response.data.listObject;
												});
									
									var URI = BASE_URL + ROOT_URL
									+ GETCURRENTPATIENTPERSONALHISTORYDETAILS;
								GenericServiceParamHeader
									.serviceAction(METHOD_GET, URI,
											data,
											NOTIFICATION_MSG_STATUS_FALSE,paramObjForPer,headerObj)
									.then(
											function(response) {
												console.log(response);
												
												// $rootScope.startLoader();
												if (response.data.status == "success")
												$scope.personalHistoryCurrentList = response.data.listObject;
												
												$scope.personalHistoryCurrentListDoctor = [];
												if($scope.roleId == '2' )
													{
														
														angular.forEach(response.data.listObject, function(value,key) {
															
																if(value.roleId == 3 && value.isReviewedFlag == 'N')
																	{
																var data = {};
																data.isNoSignificantHistoryStatus = value.isNoSignificantHistoryStatus;
																data.description = value.description;
																data.remark = value.remark;
																data.isReviewedFlag = 'N',
																data.isEnterInErrorStatus='N',
																data.personalHistoryDetailsId  = value.personalHistoryDetailsId;
																$scope.personalHistoryCurrentListDoctor.push(data);
														}
														});
													}
												
											});
								//END Get Personal History
								
										 //Start For Get Active History Family
										/*var URI = BASE_URL + ROOT_URL
										+ GETPATIENTFAMILYHISTORY;
									GenericServiceParamHeader
										.serviceAction(METHOD_GET, URI,
												data,
												NOTIFICATION_MSG_STATUS_FALSE,paramObjForPer,headerObj)
										.then(
												function(response) {
													console.log(response);
													// $rootScope.startLoader();
													if (response.data.status == "success")
														$scope.familyHistoryList = response.data.listObject;
												});*/
								
								var URI = BASE_URL + ROOT_URL
								+ GETOLDPATIENTFAMILYHISTORY;
							GenericServiceParamHeader
								.serviceAction(METHOD_GET, URI,
										data,
										NOTIFICATION_MSG_STATUS_FALSE,paramObjForPer,headerObj)
								.then(
										function(response) {
											console.log(response);
											// $rootScope.startLoader();
											if (response.data.status == "success")
											$scope.familyHistoryOldList = response.data.listObject;
										});
							
							var URI = BASE_URL + ROOT_URL
							+ GETCURRENTPATIENTFAMILYHISTORY;
						GenericServiceParamHeader
							.serviceAction(METHOD_GET, URI,
									data,
									NOTIFICATION_MSG_STATUS_FALSE,paramObjForPer,headerObj)
							.then(
									function(response) {
										console.log(response);
										
										// $rootScope.startLoader();
										if (response.data.status == "success")
										$scope.familyHistoryCurrentList = response.data.listObject;
										
										$scope.familyHistoryCurrentListDoctor = [];
										if($scope.roleId == '2' )
											{
												
												angular.forEach(response.data.listObject, function(value,key) {
													
														if(value.roleId == 3 && value.isReviewedFlag == 'N')
															{
														var data = {};
														data.isNoFamilyHistoryStatus = value.isNoFamilyHistoryStatus;
														data.remark = value.remark;
														data.isReviewedFlag = 'N',
														data.isEnterInErrorStatus='N',
														data.diagnosisId = value.diagnosisId,
														data.relationId = value.relationId,
														data.familyHistoryId  = value.familyHistoryId;
														$scope.familyHistoryCurrentListDoctor.push(data);
												}
												});
											}
										
									});
						
										//End For For Get Active History Family
									
									
										 //START For Get Active History Surgical
										/*var URI = BASE_URL + ROOT_URL
										+ GETPATIENTSURGICALHISTORY;
									GenericServiceParamHeader
										.serviceAction(METHOD_GET, URI,
												data,
												NOTIFICATION_MSG_STATUS_FALSE,paramObjForPer,headerObj)
										.then(
												function(response) {
													console.log(response);
													// $rootScope.startLoader();
													if (response.data.status == "success")
														$scope.surgicalHistoryList = response.data.listObject;
												});*/
						
						var URI = BASE_URL + ROOT_URL
						+ GETOLDPATIENTSURGICALHISTORY;
					GenericServiceParamHeader
						.serviceAction(METHOD_GET, URI,
								data,
								NOTIFICATION_MSG_STATUS_FALSE,paramObjForPer,headerObj)
						.then(
								function(response) {
									console.log(response);
									// $rootScope.startLoader();
									if (response.data.status == "success")
									$scope.surgicalHistoryOldList = response.data.listObject;
								});
					
					var URI = BASE_URL + ROOT_URL
					+ GETCURRENTPATIENTSURGICALHISTORY;
				GenericServiceParamHeader
					.serviceAction(METHOD_GET, URI,
							data,
							NOTIFICATION_MSG_STATUS_FALSE,paramObjForPer,headerObj)
					.then(
							function(response) {
								console.log(response);
								
								// $rootScope.startLoader();
								if (response.data.status == "success")
								$scope.surgicalHistoryCurrentList = response.data.listObject;
								
								$scope.surgicalHistoryCurrentListDoctor = [];
								if($scope.roleId == '2' )
									{
										
										angular.forEach(response.data.listObject, function(value,key) {
											
												if(value.roleId == 3 && value.isReviewedFlag == 'N')
													{
												var data = {};
												data.isNoSignificantStatus = value.isNoSignificantStatus;
												data.remark = value.remark;
												data.isReviewedFlag = 'N',
												data.isEnterInErrorStatus='N',
												data.surgenName = value.surgenName,
												data.surgeryId = value.surgeryId,
												data.month= value.month,
												data.year = value.year,
												data.surgicalHistoryDetailsId  = value.surgicalHistoryDetailsId;
												$scope.surgicalHistoryCurrentListDoctor.push(data);
										}
										});
									}
								
							});
									
									//END For Get Active History Surgical
										
										//START For Get Active History Medical
										/*var URI = BASE_URL + ROOT_URL
										+ GETPATIENTMEDICALHISTORYDETAILS;
									GenericServiceParamHeader
										.serviceAction(METHOD_GET, URI,
												data,
												NOTIFICATION_MSG_STATUS_FALSE,paramObjForPer,headerObj)
										.then(
												function(response) {
													console.log(response);
													// $rootScope.startLoader();
													if (response.data.status == "success")
														$scope.medicalHistoryList = response.data.listObject;
												});*/
				var URI = BASE_URL + ROOT_URL
				+ GETOLDPATIENTMEDICALHISTORYDETAILS;
			GenericServiceParamHeader
				.serviceAction(METHOD_GET, URI,
						data,
						NOTIFICATION_MSG_STATUS_FALSE,paramObjForPer,headerObj)
				.then(
						function(response) {
							console.log(response);
							// $rootScope.startLoader();
							if (response.data.status == "success")
							$scope.medicalHistoryOldList = response.data.listObject;
						});
			
			var URI = BASE_URL + ROOT_URL
			+ GETCURRENTPATIENTMEDICALHISTORYDETAILS;
		GenericServiceParamHeader
			.serviceAction(METHOD_GET, URI,
					data,
					NOTIFICATION_MSG_STATUS_FALSE,paramObjForPer,headerObj)
			.then(
					function(response) {
						console.log(response);
						
						// $rootScope.startLoader();
						if (response.data.status == "success")
						$scope.medicalHistoryCurrentList = response.data.listObject;
						
						$scope.medicalHistoryCurrentListDoctor = [];
						if($scope.roleId == '2' )
							{
								
								angular.forEach(response.data.listObject, function(value,key) {
									
										if(value.roleId == 3 && value.isReviewedFlag == 'N')
											{
										var data = {};
										data.isNoSignificantStatus = value.isNoSignificantStatus;
										data.remark = value.remark;
										data.isReviewedFlag = 'N',
										data.isEnterInError='N',
										data.diagnosisId = value.diagnosisId,
										data.since= value.since,
										data.duration = value.duration,
										data.patientMedicalHistoryId  = value.patientMedicalHistoryId;
										$scope.medicalHistoryCurrentListDoctor.push(data);
								}
								});
							}
						
					});
				
									//END For Get Active History Medical
									
								//START GET ALLERT PATIENT
									/*var URI = BASE_URL + ROOT_URL
									+ GETALLERGYPATIENTMAPPER;
								GenericServiceParamHeader
									.serviceAction(METHOD_GET, URI,
											data,
											NOTIFICATION_MSG_STATUS_FALSE,paramObjForPer,headerObj)
									.then(
											function(response) {
												console.log(response);
												// $rootScope.startLoader();
												if (response.data.status == "success")
													$scope.allergyList = response.data.listObject;
													$scope.allergyFlag = false;
												
													if($scope.allergyList.length > 0)
														{
															$scope.allergyFlag = true;
														}
											});*/
		
									var URI = BASE_URL + ROOT_URL
									+ GETOLDALLERGYPATIENTMAPPER;
								GenericServiceParamHeader
									.serviceAction(METHOD_GET, URI,
											data,
											NOTIFICATION_MSG_STATUS_FALSE,paramObjForPer,headerObj)
									.then(
											function(response) {
												console.log(response);
												// $rootScope.startLoader();
												if (response.data.status == "success")
												$scope.allergyOldList = response.data.listObject;
											});
								
								var URI = BASE_URL + ROOT_URL
								+ GETCURRENTALLERGYPATIENTMAPPER;
							GenericServiceParamHeader
								.serviceAction(METHOD_GET, URI,
										data,
										NOTIFICATION_MSG_STATUS_FALSE,paramObjForPer,headerObj)
								.then(
										function(response) {
											console.log(response);
											
											// $rootScope.startLoader();
											if (response.data.status == "success")
											$scope.allergyCurrentList = response.data.listObject;
											
											$scope.allergyCurrentListDoctor = [];
											if($scope.roleId == '2' )
												{
													
													angular.forEach(response.data.listObject, function(value,key) {
														
															if(value.roleId == 3 && value.isReviewedFlag == 'N')
																{
															var data = {};
															data.isNoKnownAllergies = value.isNoKnownAllergies;
															data.remark = value.remark;
															data.isReviewedFlag = 'N';
															data.isEnterInErrorStatus='N';
															data.allergyId = value.allergyId;
															data.allergyTypeId= value.allergyTypeId;
															data.type = value.type;
															data.natureOfReaction = value.natureOfReaction;
															data.severityId  = value.severityId;
															data.month = value.month;
															data.year = value.year;
															data.allergyPatientMapperId = value.allergyPatientMapperId;
															$scope.allergyCurrentListDoctor.push(data);
													}
													});
												}
											
										});
								
								//END GET ALLERT PATIENT
									
									
									
									//For Get ALL Allergy Type List
									var URI = BASE_URL + ROOT_URL
									+ GETALLERGYTYPEMASTER;
								GenericServiceParamHeader
									.serviceAction(METHOD_GET, URI,
											data,
											NOTIFICATION_MSG_STATUS_FALSE,data,headerObj)
									.then(
											function(response) {
												console.log(response);
												if (response.data.status == "success")
													$scope.allergyTypeList = response.data.listObject;
											});
									
									
									//For Get ALL Severity List
									var URI = BASE_URL + ROOT_URL
									+ GETSEVERITYMASTER;
								GenericServiceParamHeader
									.serviceAction(METHOD_GET, URI,
											data,
											NOTIFICATION_MSG_STATUS_FALSE,data,headerObj)
									.then(
											function(response) {
												console.log(response);
												// $rootScope.startLoader();
												if (response.data.status == "success")
													$scope.severityList = response.data.listObject;
											});
								
								
									
	                                    
	                        //START IMMUNIZATION
	                        var URI = BASE_URL + ROOT_URL
	        				+ GETOLDIMMUNIZATIONHISTORYMAPPER;
	        			GenericServiceParamHeader
	        				.serviceAction(METHOD_GET, URI,
	        						data,
	        						NOTIFICATION_MSG_STATUS_FALSE,paramObjForPer,headerObj)
	        				.then(
	        						function(response) {
	        							console.log(response);
	        							// $rootScope.startLoader();
	        							if (response.data.status == "success")
	        							$scope.immunizationHistoryOldList = response.data.listObject;
	        						});
	        			
	        			var URI = BASE_URL + ROOT_URL
	        			+ GETCURRENTIMMUNIZATIONHISTORYMAPPER;
	        		GenericServiceParamHeader
	        			.serviceAction(METHOD_GET, URI,
	        					data,
	        					NOTIFICATION_MSG_STATUS_FALSE,paramObjForPer,headerObj)
	        			.then(
	        					function(response) {
	        						console.log(response);
	        						
	        						// $rootScope.startLoader();
	        						if (response.data.status == "success"){
	        						$scope.immunizationHistoryCurrentList = response.data.listObject;
	        						}
	        						
	        					});
	        		
	        				
	        		 //For Get ALL DRUG List
                    var paramObjForDrug = {};
                    var URI = BASE_URL + ROOT_URL
                    + GETDRUGMASTERLIST;
                GenericServiceParamHeader
                    .serviceAction(METHOD_GET, URI,
                            data,
                            NOTIFICATION_MSG_STATUS_FALSE,paramObjForDrug,headerObj)
                    .then(
                            function(response) {
                                console.log(response);
                                // $rootScope.startLoader();
                                if (response.data.status == "success")
                                    $scope.drugHistoryList = [];
                                    
                                console.log("DrugList",response.data.listObject);
                                console.log("IMMunizationList",$scope.immunizationHistoryCurrentList);
                                 angular.forEach(response.data.listObject, function(value , key) {
                                	 var checkFlag = false;
                                     angular.forEach($scope.immunizationHistoryCurrentList, function(value1 , key1) {
                                         	
                                    	
                                    	 if(value.drugId == value1.drugId)
                                    		 {
                                    		 	checkFlag = true;
                                    		 	$scope.newArr = {};
                                    		 	$scope.newArr.drugId = value1.drugId;
                                    		 	$scope.newArr.isAdministeredStatus = value1.isAdministeredStatus;
                                    		 	$scope.newArr.datepicker = new Date(value1.immunizationDate);
                                    		 	$scope.newArr.medicationName = value.medicationName;
                                    		 	$scope.newArr.isOld = 'Y';
                                    		 	$scope.newArr.immunizationHistoryMapperId = value1.immunizationHistoryMapperId;
                                    		 	$scope.newArr.isReviewedFlag = value1.isReviewedFlag
                                                //$scope.drugHistoryList.push(newArr);
                                    		 }
                                    	
                                     		});
														if (checkFlag == true) {
																$scope.drugHistoryList
																		.push($scope.newArr);
															} else {
																//alert("fdvfd");
																var newArr = {};
																newArr.drugId = value.drugId;
																newArr.isAdministeredStatus = '';
																newArr.datepicker = new Date();
																newArr.medicationName = value.medicationName;
																newArr.isOld = 'N';
																newArr.isReviewedFlag = 'N';
																$scope.drugHistoryList.push(newArr);
															}
                                	       
                                    });
                                 
                                 
                                 console.log("Final Arr",$scope.drugHistoryList);
                                    
                            });
	                        
	                        //END IMMUNIZATION
                
                //START HABIT GET
	                        			$scope.habitTypeList = [
	                        				{"habitId" : 6, "habitName":"Alcohal"},
	                        				{"habitId" : 1, "habitName":"Smoking"},
	                        				{"habitId" : 2, "habitName":"Tabacco"},
	                        				{"habitId" : 3, "habitName":"Hookah"},
	                        				{"habitId" : 4, "habitName":"Recreation Drug"},
	                        				{"habitId" : 5, "habitName":"Exercise"},
	                        				
	                        			];
	                                    
	                        			var URI = BASE_URL + ROOT_URL
										+ GETOLDPATIENTHABITDETAILS;
										GenericServiceParamHeader
											.serviceAction(METHOD_GET, URI,
													data,
													NOTIFICATION_MSG_STATUS_FALSE,paramObjForPer,headerObj)
											.then(
													function(response) {
														console.log(response);
														if (response.data.status == "success")
															{
															$scope.patientHabitOldList = response.data.listObject;
															console.log("patinet Habit Old List"+$scope.patientHabitOldList.habitName);
															}
													});
	                        			
	                        			
				                      //For Get ALL patient Habits List
										var URI = BASE_URL + ROOT_URL
										+ GETCURRENTPATIENTHABITDETAILS;
										GenericServiceParamHeader
											.serviceAction(METHOD_GET, URI,
													data,
													NOTIFICATION_MSG_STATUS_FALSE,paramObjForPer,headerObj)
											.then(
													function(response) {
														console.log(response);
														// $rootScope.startLoader();
														if (response.data.status == "success")
															$scope.patientHabitList = response.data.listObject;
														//console.log("HABIT lIST",$scope.patientHabitList);
														
														
														//START TEST
														$scope.habitFinalList = [];
														console.log("HabitList",$scope.habitTypeList);
				                                        console.log("PatientHabitList",response.data.listObject);
				                                         angular.forEach($scope.habitTypeList, function(value , key) {
				                                        	 var checkFlag = false;
				                                             angular.forEach($scope.patientHabitList, function(value1 , key1) {
				                                                 	
				                                            	
				                                            	 if(value.habitId == value1.habitId)
				                                            		 {
				                                            		 	checkFlag = true;
				                                            		 	$scope.childObj =[];
				                                            		 	$scope.newArr = {};
				                                            		 	$scope.newArr.habitId = value1.habitId;
				                                            		 	$scope.newArr.habitTypeId = value1.habitTypeId;
				                                            		 	$scope.newArr.remark = value1.remark;
				                                            		 	$scope.newArr.cigarettesPerDay = value1.cigarettesPerDay;
				                                            		 	$scope.newArr.yearsSmoked = value1.yearsSmoked;
				                                            		 	$scope.newArr.leftWhen = value1.leftWhen;
				                                            		 	$scope.newArr.duration = value1.durationOfLeftWhen;
				                                            		 	$scope.newArr.packYear = value1.packYear;
				                                            		 	$scope.newArr.gmsPerDay = value1.gmsPerDay;
				                                            		 	$scope.newArr.yearsUsed = value1.yearsUsed;
				                                            		 	$scope.newArr.mlsPerDay = value1.mlsPerDay;
				                                            		 	$scope.newArr.frequency = value1.frequency;
				                                            		 	$scope.newArr.typeOfExercise = value1.typeOfExercise;
				                                            		 	$scope.newArr.mlsPerDay = value1.mlsPerDay;
				                                            		 	$scope.newArr.isReviewedFlag = value1.isReviewedFlag;
				                                            		 	$scope.newArr.isEnterInErrorStatus = value1.isEnterInErrorStatus;
				                                            		 	if($scope.roleId == 2)
				                                            		 		{
				                                            		 	$scope.newArr.patientHabitDetailsId = value1.patientHabitDetailsId;
				                                            		 		}
				                                            		 	$scope.newArr.drug = value1.drug;
				                                            		 	if(value1.habitId == 5)
				                                            		 		{
				                                            		 		
				                                            		 			var res = value1.typeOfExercise.split(",");
				                                            		 			//alert(res.length);
				                                            		 			$scope.patient.exercise = [];
				                                            		 			var tdata = {};
				                                            		 			for(var j=0;j<res.length;j++)
				                                            		 				{
				                                            		 					if(res[j] != '')
				                                            		 						{
				                                            		 						$scope.patient.exercise.push(res[j]); 
				                                            		 							//tdata[j] = res[j];
				                                            		 						}
				                                            		 					//console.log(tdata);
				                                            		 				}
				                                            		 			//$scope.patient.exercise.push(tdata); 
				                                            		 			$scope.newArr.exerciseList = [
				                                            		 					"Yoga",
				                                            		 					"WeightTraining",
				                                            		 					"Cardio",
				                                            		 					"Sports",
				                                            		 			];
				                                            		 			/*$scope.newArr.exerciseOne = "Yoga";
				                                            		 			$scope.newArr.exerciseTwo = "WeightTraining";
				                                            		 			$scope.newArr.exerciseThree = "Cardio";
				                                            		 			$scope.newArr.exerciseFour = "Sports";
				                                            		 			$scope.newArr.exerciseFive = "All";*/
				                                            		 		}
				                                                        $scope.childObj.push($scope.newArr);
				                                            		 }
				                                            	
				                                             		});
																				if (checkFlag == true) {
																					var data = {
																							"habitId":value.habitId,
																							"habitName":value.habitName,
																							"childHabitObj":$scope.childObj
																					}
																					$scope.habitFinalList.push(data);
																					//console.log("ccc",data);
																					} else {
																						//alert("fdvfd");
																					
																						$scope.childObj =[];
								                                            		 	var newArr = {};
								                                            		 	newArr.habitId = value.habitId;
								                                            		 	newArr.habitTypeId = '';
								                                            		 	newArr.remark = '';
								                                            		 	newArr.cigarettesPerDay = '';
								                                            		 	newArr.yearsSmoked ='';
								                                            		 	newArr.leftWhen = '';
								                                            		 	newArr.duration = '';
								                                            		 	newArr.packYear = '';
								                                            		 	newArr.gmsPerDay = '';
								                                            		 	newArr.yearsUsed = '';
								                                            		 	newArr.mlsPerDay = '';
								                                            		 	newArr.frequency = '';
								                                            		 	newArr.typeOfExercise = '';
								                                            		 	newArr.mlsPerDay = '';
								                                            		 	newArr.drug = '';
								                                            		 	newArr.isReviewedFlag = 'N';
								                                            		 	newArr.isEnterInErrorStatus = 'N';
								                                            		 	if($scope.roleId == 2)
							                                            		 		{
								                                            		 		newArr.patientHabitDetailsId ='';
							                                            		 		}
								                                            		 	
								                                            		 	if(value.habitId == 5)
							                                            		 		{
								                                            		 		newArr.exerciseList = [
						                                            		 					"Yoga",
						                                            		 					"WeightTraining",
						                                            		 					"Cardio",
						                                            		 					"Sports",
						                                            		 			];
								                                            		 		
							                                            		 			/*newArr.exerciseOne = "";
							                                            		 			newArr.exerciseTwo = "";
							                                            		 			newArr.exerciseThree = "";
							                                            		 			newArr.exerciseFour = "";
							                                            		 			newArr.exerciseFive = "";*/
							                                            		 		}
								                                            		 	
								                                                        $scope.childObj.push(newArr);
								                                                        
								                                                    	
																						var data = {
																								"habitId":value.habitId,
																								"habitName":value.habitName,
																								"childHabitObj":$scope.childObj
																						}
																						$scope.habitFinalList.push(data);
																						
																						
																						
								                                                       // $scope.habitFinalList.push($scope.newArr);
								                                            		 //	console.log("blan",data);
																					}
				                                        	       
				                                            });
				                                         
				                                         
				                                        console.log("Final Arr Habit",$scope.habitFinalList);
														
														//END TEST
													});
											
										//START HABIT GET
								
										
										  //For Get Patient Vitals List
		                                var URI = BASE_URL + ROOT_URL
		                                + GETVITALMASTERLIST;
		                            GenericServiceParamHeader
		                                .serviceAction(METHOD_GET, URI,
		                                        data,
		                                        NOTIFICATION_MSG_STATUS_FALSE,paramObjForPer,headerObj)
		                                .then(
		                                        function(response) {
		                                            console.log(response);
		                                            // $rootScope.startLoader();
		                                            if (response.data.status == "success")
		                                                $scope.vitalsList = response.data.listObject;
		                                            console.log("Vital List", $scope.vitalsList);
		                                        });
		                            
		                            $rootScope.startLoader();
		                            $timeout( function(){
		                            	  $rootScope.stopLoader();
		                            },5000);
										
										 //For Get Patient Vitals List in Examination
		                            
		                            $timeout( function(){
		                            	
		                            		var paramObjForVital = {
		                            				//PatientId:1,
		                            				//AppointmentId :14
		                            				encounterId : $scope.encounterDetailsObj.encounterId,
		                            				patientId:$scope.encounterDetailsObj.patientId,
		                            				roleId:$scope.roleId
		                            		}
			                                var URI = BASE_URL + ROOT_URL
			                                + GETCURRENTAPPOINTMENTVITALMAPPER;
			                            GenericServiceParamHeader
			                                .serviceAction(METHOD_GET, URI,
			                                        data,
			                                        NOTIFICATION_MSG_STATUS_FALSE,paramObjForVital,headerObj)
			                                .then(
			                                        function(response) {
			                                            console.log(response);
	                                         
	                                        
	                                        if (response.data.status == "success")
	                                            $scope.vitalFinalList = [];
	                                            $scope.patientViatalList  = response.data.listObject;
	                                        console.log("patientVitalList",response.data.listObject);
	                                        console.log("vitalList",$scope.vitalsList);
	                                         angular.forEach($scope.vitalsList, function(value , key) {
	                                        	 var checkFlag = false;
	                                             angular.forEach($scope.patientViatalList, function(value1 , key1) {
	                                                 	
	                                            	
	                                            	 if(value.vitalId == value1.vitalId)
	                                            		 {
	                                            		 	checkFlag = true;
	                                            		 	$scope.newArr = {};
	                                            		 	$scope.newArr.vitalId = value.vitalId;
	                                            		 	$scope.newArr.minValue = value.minValue;
	                                            		 	$scope.newArr.maxValue = value.maxValue;
	                                            		 	$scope.newArr.vitalName = value.vitalName;
	                                            		 	$scope.newArr.vitalIconPath = value.vitalIconPath;
	                                            		 	$scope.newArr.unit = value.unit;
	                                            		 	$scope.newArr.vitalValue = value1.value;
	                                            		 	$scope.newArr.isReviewedFlag = value1.isReviewedFlag;
	                                            		 	if($scope.roleId == 2)
                                            		 		{
	                                            		 		$scope.newArr.appointmentVitalMapperId = value1.appointmentVitalMapperId;
                                            		 		}
	                                            		 	
	                                                       
	                                            		 }
	                                            	
	                                             		});
																	if (checkFlag == true) {
																			$scope.vitalFinalList.push($scope.newArr);
																		} else {
																			//alert("fdvfd");
																			var newArr = {};
																			newArr.vitalId = value.vitalId;
					                                            		 	newArr.minValue = value.minValue;
					                                            		 	newArr.maxValue = value.maxValue;
					                                            		 	newArr.vitalName = value.vitalName;
					                                            		 	newArr.vitalIconPath = value.vitalIconPath;
					                                            		 	newArr.unit = value.unit;
					                                            		 	newArr.vitalValue = 0;
					                                            		 	newArr.isReviewedFlag = 'N';
					                                            		 	if($scope.roleId == 2)
				                                            		 		{
					                                            		 		newArr.appointmentVitalMapperId ='';
				                                            		 		}
																			$scope.vitalFinalList.push(newArr);
																		}
	                                        	       
	                                            });
	                                         
	                                         
	                                         console.log("Final Vital Arr",$scope.vitalFinalList);
	                                         
	                                       
			                                                                                          
			                                        });
		                            
		                            },4000);
										
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
								
								
								 //For Get ALL Patient Diagnosis List
                              /*  var URI = BASE_URL + ROOT_URL
                                + GETIMMUNIZATIONHISTORYLIST;
                            GenericServiceParamHeader
                                .serviceAction(METHOD_GET, URI,
                                        data,
                                        NOTIFICATION_MSG_STATUS_FALSE,paramObjForPer,headerObj)
                                .then(
                                        function(response) {
                                            console.log(response);
                                            // $rootScope.startLoader();
                                            if (response.data.status == "success")
                                                $scope.diagnosisList = response.data.listObject;                                                
                                        });*/
								
								 //For Get ALL Patient Diagnosis List
								
								 var headerObjDia = {
										 	'unit_id' : $scope.unitId,
											'organization_id' : $scope.organizationId,
											'patient_id' : $scope.encounterDetailsObj.patientId,
											'Content-Type':'application/json',
							     };
	                                var URI = BASE_URL + ROOT_URL
	                                + GETPATIENTDIAGNOSISBYPATIENTID;
	                            GenericServiceParamHeader
	                                .serviceAction(METHOD_GET, URI,
	                                        data,
	                                        NOTIFICATION_MSG_STATUS_FALSE,data,headerObjDia)
	                                .then(
	                                        function(response) {
	                                            console.log(response);
	                                            // $rootScope.startLoader();
	                                            if (response.data.status == "success")
	                                                $scope.patientDiagnosisList = response.data.listObject; 
	                                            console.log("patientDiagnosisList",$scope.patientDiagnosisList);
	                                            	$scope.comorbidityFlag = false;
	                                            	$scope.notifiableFlag = false;
												
													if($scope.patientDiagnosisList.length > 0)
														{
														
															angular.forEach($scope.patientDiagnosisList,function(value,key) {
																if(value.isNotifiable == 'Y')
																{
																	$scope.notifiableFlag = true;
																}
																else if(value.isComorbidity == 'Y')
																{
																	$scope.comorbidityFlag = true;
																}
															});
														}
	                                            
	                                        });
	                            
	                            
	                         
	                            
	                            //For Get Patient General Examination 
	                            var paramObjForGenExa = {
	                            		patientId:$scope.encounterDetailsObj.patientId,
	                            		typeId : 1
	                            		//PatientId:2
	                            };
	                            
	                            
	                            var headerObjSys = {
	                            		"unit_id" : $scope.unitId,
										"organization_id" : $scope.organizationId,
										"gender_id" : $scope.encounterDetailsObj.genderId,
										"type_id" : 1
	                            };
	                            
	                            var URI = BASE_URL + ROOT_URL
	                            + GETGENERALEXAMINATIONAPPOMAPPER;
	                        GenericServiceParamHeader
	                            .serviceAction(METHOD_GET, URI,
	                                    data,
	                                    NOTIFICATION_MSG_STATUS_FALSE,paramObjForGenExa,headerObj)
	                            .then(
	                                    function(response) {
	                                        console.log(response);
	                                       if (response.data.status == "success")
	                                    	   {
	                                    	  // alert("in general");
	                                    	   		console.log("Added GEn EXA",response.data.listObject);
	                                    	   		
	                                    	   		$scope.patGenExaList = response.data.listObject;
	                                    	   		//$scope.patGenExa = response.data.listObject;
	                                    	   		$scope.patGenExa = [];
													var URI = BASE_URL+ ROOT_URL+ GETSYSTEMMASTER;
													
													$scope.patientSystemFinalArray = [];
													
												GenericServiceParamHeader.serviceAction(METHOD_GET,URI,data,NOTIFICATION_MSG_STATUS_FALSE,data,headerObjSys)
																.then(function(response1) {
																	console.log(response1);
													if (response1.data.status == "success") {
														
														$scope.systemList = response1.data.listObject;
														console.log("systemMaster1",$scope.systemList);
														
														//loop for systenList
														angular.forEach($scope.systemList,function(value,key) {
															
															
															var dataObj = {};
															dataObj.systemId = value.systemId;
															dataObj.systemName = value.systemName;
															dataObj.isNADValue = 'N';
															//new added
															dataObj.isCheckedSys = 'N';
															//end new added
															
															 var systemCheckFlag= false;
															//loopp for patient General Examination
															angular.forEach($scope.patGenExa,function(value1,key1) {
																	if(value1.systemId == value.systemId)
																		{
																			systemCheckFlag= true;
																		    //console.log("Match",value1.systemId);
																			//alert(value.systemId);
																			//console.log("dfdf",value.systemObervationMasterList);
																			dataObj.isNADValue = value1.isNADValue;
																			//new added
																			dataObj.isCheckedSys = 'Y';
																			//end new added
																			
																			dataObj.systemObervationList = [];
																			angular.forEach(value.systemObervationMasterList,function(value2,key2) {
																				
																				var dataObjObervation = {};
																				dataObjObervation.observationId = value2.observationId;
																				dataObjObervation.observationName = value2.observationName;
																				
																				
																				if((value1.systemObervationId == value2.observationId) && (value2.isPropertyRequired == 'Y'))
																					{
																					//console.log("Observation  match and property Y");
																					
																						dataObjObervation.remark = '';
																						dataObjObervation.isPropertyRequired = 'Y';
																						//new added
																						dataObjObervation.isCheckedOber = 'Y';
																						//end new added
																						dataObjObervation.systemObservationPropertList = [];
																							
																							
																							
																								angular.forEach(value2.systemObervationPropertyMasterList,function(value3,key3) {
																									var dataObjObervationProperty = {};
																									dataObjObervationProperty.propertyId = value3.propertyId;
																									dataObjObervationProperty.propertyName = value3.propertyName;
																									
																										if(value1.systemObervationPropertyId == value3.propertyId)
																											{
																											//new added
																											dataObjObervationProperty.isCheckedOberPro = true;
																											//end new added
																												dataObjObervationProperty.remark = value1.remark;
																											}
																										else
																											{
																												dataObjObervationProperty.remark = '';
																												//new added
																												dataObjObervationProperty.isCheckedOberPro = false;
																												//end new added
																											}
																										dataObjObervation.systemObservationPropertList.push(dataObjObervationProperty);
																									
																								});
																								//dataObj.systemObervationList.push(dataObjObervation);
																								
																							
																					}
																				else if((value1.systemObervationId == value2.observationId) && (value2.isPropertyRequired == 'N'))
																					{
																						
																						
																						dataObjObervation.isPropertyRequired = 'N';
																						//new added
																						dataObjObervation.isCheckedOber = 'Y';
																						//end new added
																						dataObjObervation.remark = value1.remark;
																						dataObjObervation.systemObservationPropertList = [];
																						//dataObj.systemObervationList.push(dataObjObervation);
																					}
																				else if((value1.systemObervationId != value2.observationId) && (value2.isPropertyRequired == 'Y'))
																					{
																						dataObjObervation.remark = '';
																						//new added
																						dataObjObervation.isCheckedOber = 'N';
																						//end new added
																						dataObjObervation.isPropertyRequired = 'Y';
																						dataObjObervation.systemObservationPropertList = [];
																						
																						
																						angular.forEach(value.systemObervationMasterList.systemObervationPropertyMasterList,function(value3,key3) {
																							var dataObjObervationProperty = {};
																							dataObjObervationProperty.propertyId = value3.propertyId;
																							dataObjObervationProperty.propertyName = value3.propertyName;
																							dataObjObervationProperty.remark = value1.remark;
																							//new added
																							dataObjObervationProperty.isCheckedOberPro = false;
																							//end new added
																							dataObjObervation.systemObservationPropertList.push(dataObjObervationProperty);
																							
																						});
																						
																						//dataObj.systemObervationList.push(dataObjObervation);
																						
																					}
																				else if((value1.systemObervationId != value2.observationId) && (value2.isPropertyRequired == 'N'))
																					{
																						dataObjObervation.remark = '';
																						//new added
																						dataObjObervation.isCheckedOber = 'N';
																						//end new added
																						dataObjObervation.isPropertyRequired = 'N';
																						dataObjObervation.systemObservationPropertList = [];
																						//dataObj.systemObervationList.push(dataObjObervation);
																					}
																				
																				dataObj.systemObervationList.push(dataObjObervation);
																				
																			});
																			
																		}
															});
															
															
															if(systemCheckFlag == true)
																{
																	$scope.patientSystemFinalArray.push(dataObj);
																}
															else
															{
																
																dataObj.isNADValue = 'N';
																dataObj.systemObervationList = [];
																
																//Test Start
																angular.forEach(value.systemObervationMasterList,function(value2,key2) {
																	
																	var dataObjObervation = {};
																	dataObjObervation.observationId = value2.observationId;
																	dataObjObervation.observationName = value2.observationName;
																	if(value2.isPropertyRequired == 'Y')
																		{
																		
																		dataObjObervation.isPropertyRequired = 'Y';
																		dataObjObervation.remark = '';
																		//new added
																		dataObjObervation.isCheckedOber = 'N';
																		//end new added
																		dataObjObervation.systemObservationPropertList = [];
																		angular.forEach(value2.systemObervationPropertyMasterList,function(value3,key3) {
																						var dataObjObervationProperty = {};
																						dataObjObervationProperty.propertyId = value3.propertyId;
																						dataObjObervationProperty.propertyName = value3.propertyName;
																						dataObjObervationProperty.remark = '';
																						//new added
																						dataObjObervationProperty.isCheckedOberPro = false;
																						//end new added
																						dataObjObervation.systemObservationPropertList.push(dataObjObervationProperty);
																						
																			});
																		}
																	else
																		{
																		
																			dataObjObervation.isPropertyRequired = 'N';
																			dataObjObervation.remark = '';
																			//new added
																			dataObjObervation.isCheckedOber = 'N';
																			//end new added
																			dataObjObervation.systemObservationPropertList = [];
																		}
																	dataObj.systemObervationList.push(dataObjObervation);
																	
																});
																
																
																$scope.patientSystemFinalArray.push(dataObj);
																//Test End
															}
															
															
														});
														//console.log("JSON",JSON.stringify($scope.patientSystemFinalArray));
														console.log("Finalll System Array",$scope.patientSystemFinalArray);
													}
												});
	           	                            
	                                    	   }
	                                     
	                                    });
	                        
	                        //for System Physical Exa
	                        
                            var paramObjForPhyExa = {
                            		patientId:$scope.encounterDetailsObj.patientId,
                            		typeId : 2
                            		//PatientId:2
                            };
	                        var headerObjSysPhy = {
                            		"unit_id" : $scope.unitId,
									"organization_id" : $scope.organizationId,
									"gender_id" : $scope.encounterDetailsObj.genderId,
									"type_id" : 2
                            };
                            
                            var URI = BASE_URL + ROOT_URL
                            + GETGENERALEXAMINATIONAPPOMAPPER;
                        GenericServiceParamHeader
                            .serviceAction(METHOD_GET, URI,
                                    data,
                                    NOTIFICATION_MSG_STATUS_FALSE,paramObjForPhyExa,headerObj)
                            .then(
                                    function(response) {
                                        console.log("physical exam======"+response);
                                       if (response.data.status == "success")
                                    	   {
                                    	   //alert("in physica");
                                    	   		console.log("Added Physical",response.data.listObject);
                                    	   		
                                    	   		//$scope.patPhyExa = response.data.listObject;
                                    	   		$scope.patPhyExa = [];
												var URI = BASE_URL+ ROOT_URL+ GETSYSTEMMASTER;
												
												$scope.patientPhysicalSystemFinalArray = [];
												
											GenericServiceParamHeader.serviceAction(METHOD_GET,URI,data,NOTIFICATION_MSG_STATUS_FALSE,data,headerObjSysPhy)
															.then(function(response1) {
																console.log(response1);
												if (response1.data.status == "success") {
													
													$scope.systemListPhy = response1.data.listObject;
													console.log("systemMasterPhy",$scope.systemListPhy);
													
													//loop for systenList
													angular.forEach($scope.systemListPhy,function(value,key) {
														
														
														var dataObj = {};
														dataObj.systemId = value.systemId;
														dataObj.systemName = value.systemName;
														dataObj.isNADValue = 'N';
														//new added
														dataObj.isCheckedSys = 'N';
														//end new added
														
														 var systemCheckFlag= false;
														//loopp for patient General Examination
														angular.forEach($scope.patPhyExa,function(value1,key1) {
																if(value1.systemId == value.systemId)
																	{
																		systemCheckFlag= true;
																	    //console.log("Match",value1.systemId);
																		//alert(value.systemId);
																		//console.log("dfdf",value.systemObervationMasterList);
																		dataObj.isNADValue = value1.isNADValue;
																		//new added
																		dataObj.isCheckedSys = 'Y';
																		//end new added
																		
																		dataObj.systemObervationList = [];
																		angular.forEach(value.systemObervationMasterList,function(value2,key2) {
																			
																			var dataObjObervation = {};
																			dataObjObervation.observationId = value2.observationId;
																			dataObjObervation.observationName = value2.observationName;
																			
																			
																			if((value1.systemObervationId == value2.observationId) && (value2.isPropertyRequired == 'Y'))
																				{
																				//console.log("Observation  match and property Y");
																				
																					dataObjObervation.remark = '';
																					dataObjObervation.isPropertyRequired = 'Y';
																					//new added
																					dataObjObervation.isCheckedOber = 'Y';
																					//end new added
																					dataObjObervation.systemObservationPropertList = [];
																						
																						
																						
																							angular.forEach(value2.systemObervationPropertyMasterList,function(value3,key3) {
																								var dataObjObervationProperty = {};
																								dataObjObervationProperty.propertyId = value3.propertyId;
																								dataObjObervationProperty.propertyName = value3.propertyName;
																								
																									if(value1.systemObervationPropertyId == value3.propertyId)
																										{
																										//new added
																										dataObjObervationProperty.isCheckedOberPro = true;
																										//end new added
																											dataObjObervationProperty.remark = value1.remark;
																										}
																									else
																										{
																											dataObjObervationProperty.remark = '';
																											//new added
																											dataObjObervationProperty.isCheckedOberPro = false;
																											//end new added
																										}
																									dataObjObervation.systemObservationPropertList.push(dataObjObervationProperty);
																								
																							});
																							//dataObj.systemObervationList.push(dataObjObervation);
																							
																						
																				}
																			else if((value1.systemObervationId == value2.observationId) && (value2.isPropertyRequired == 'N'))
																				{
																					
																					
																					dataObjObervation.isPropertyRequired = 'N';
																					//new added
																					dataObjObervation.isCheckedOber = 'Y';
																					//end new added
																					dataObjObervation.remark = value1.remark;
																					dataObjObervation.systemObservationPropertList = [];
																					//dataObj.systemObervationList.push(dataObjObervation);
																				}
																			else if((value1.systemObervationId != value2.observationId) && (value2.isPropertyRequired == 'Y'))
																				{
																					dataObjObervation.remark = '';
																					//new added
																					dataObjObervation.isCheckedOber = 'N';
																					//end new added
																					dataObjObervation.isPropertyRequired = 'Y';
																					dataObjObervation.systemObservationPropertList = [];
																					
																					
																					angular.forEach(value.systemObervationMasterList.systemObervationPropertyMasterList,function(value3,key3) {
																						var dataObjObervationProperty = {};
																						dataObjObervationProperty.propertyId = value3.propertyId;
																						dataObjObervationProperty.propertyName = value3.propertyName;
																						dataObjObervationProperty.remark = value1.remark;
																						//new added
																						dataObjObervationProperty.isCheckedOberPro = false;
																						//end new added
																						dataObjObervation.systemObservationPropertList.push(dataObjObervationProperty);
																						
																					});
																					
																					//dataObj.systemObervationList.push(dataObjObervation);
																					
																				}
																			else if((value1.systemObervationId != value2.observationId) && (value2.isPropertyRequired == 'N'))
																				{
																					dataObjObervation.remark = '';
																					//new added
																					dataObjObervation.isCheckedOber = 'N';
																					//end new added
																					dataObjObervation.isPropertyRequired = 'N';
																					dataObjObervation.systemObservationPropertList = [];
																					//dataObj.systemObervationList.push(dataObjObervation);
																				}
																			
																			dataObj.systemObervationList.push(dataObjObervation);
																			
																		});
																		
																	}
														});
														
														
														if(systemCheckFlag == true)
															{
																$scope.patientPhysicalSystemFinalArray.push(dataObj);
															}
														else
														{
															
															dataObj.isNADValue = 'N';
															dataObj.systemObervationList = [];
															
															//Test Start
															angular.forEach(value.systemObervationMasterList,function(value2,key2) {
																
																var dataObjObervation = {};
																dataObjObervation.observationId = value2.observationId;
																dataObjObervation.observationName = value2.observationName;
																if(value2.isPropertyRequired == 'Y')
																	{
																	
																	dataObjObervation.isPropertyRequired = 'Y';
																	dataObjObervation.remark = '';
																	//new added
																	dataObjObervation.isCheckedOber = 'N';
																	//end new added
																	dataObjObervation.systemObservationPropertList = [];
																	angular.forEach(value2.systemObervationPropertyMasterList,function(value3,key3) {
																					var dataObjObervationProperty = {};
																					dataObjObervationProperty.propertyId = value3.propertyId;
																					dataObjObervationProperty.propertyName = value3.propertyName;
																					dataObjObervationProperty.remark = '';
																					//new added
																					dataObjObervationProperty.isCheckedOberPro = false;
																					//end new added
																					dataObjObervation.systemObservationPropertList.push(dataObjObervationProperty);
																					
																		});
																	}
																else
																	{
																	
																		dataObjObervation.isPropertyRequired = 'N';
																		dataObjObervation.remark = '';
																		//new added
																		dataObjObervation.isCheckedOber = 'N';
																		//end new added
																		dataObjObervation.systemObservationPropertList = [];
																	}
																dataObj.systemObervationList.push(dataObjObervation);
																
															});
															
															
															$scope.patientPhysicalSystemFinalArray.push(dataObj);
															//Test End
														}
														
														
													});
													
													console.log("Finalll Physical System Array",$scope.patientPhysicalSystemFinalArray);
												}
											});
           	                            
                                    	   }
                                     
                                    });
	                        //End For Physical Exa
                        
                        
                        //get Letter of opd cover sheets
                        
                        $scope.getLetterOpdCoverSheets = function() {
							var templeteData  = {};
							
							$scope.finalOpdCoverSheetLetterList = [];
							
							var URI = BASE_URL + ROOT_URL+LIS_UNIT
							+ GET_TEMPLATE+"/"+$scope.organizationId+"/"+$scope.unitId+"/0/10";
							GenericService
							.serviceAction("GET", URI, templeteData)
							.then(
									function(response) {
										$scope.templateListSickList = [];

										if (response.data.status == "success") {
											//$scope.templateSickList = response.data.listObject;
											
											angular.forEach(response.data.listObject,function(value,key) {
												var data = {};
												data.html = value.html;
												data.title = value.title;
												data.labTemplateId = value.labTemplateId;
												$scope.finalOpdCoverSheetLetterList.push(data);
											});
											/*CKEDITOR.addTemplates("default", {
											    imagesPath:CKEDITOR.getUrl(CKEDITOR.plugins.getPath("templates")+"templates/images/"),
											    templates: $scope.templateList
											   });*/
											
										}
									});
							
							var URI = BASE_URL + ROOT_URL+LIS_UNIT
							+ GET_TEMPLATE+"/"+$scope.organizationId+"/"+$scope.unitId+"/0/11";
							GenericService
							.serviceAction("GET", URI, templeteData)
							.then(
									function(response1) {
										//$scope.templateFitnessList = [];

										if (response1.data.status == "success") {
											//$scope.templateFitnessList = response1.data.listObject;

											angular.forEach(response1.data.listObject,function(value,key) {
												var data = {};
												data.html = value.html;
												data.title = value.title;
												data.labTemplateId = value.labTemplateId;
												$scope.finalOpdCoverSheetLetterList.push(data);
											});
										}
									});
							
							//$scope.finalOpdCoverSheetLetterList = $scope.templateSickList.concat($scope.templateFitnessList);
							
							console.log("FInal Letter",$scope.finalOpdCoverSheetLetterList);

						}
						
                        $scope.getLetterOpdCoverSheets();
                        
                        
                        //START For Get History Travel
						/*var URI = BASE_URL + ROOT_URL
						+ GETPATIENTTRAVELHISTORYDETAILS;
					GenericServiceParamHeader
						.serviceAction(METHOD_GET, URI,
								data,
								NOTIFICATION_MSG_STATUS_FALSE,paramObjForPer,headerObj)
						.then(
								function(response) {
									console.log(response);
									// $rootScope.startLoader();
									if (response.data.status == "success")
										$scope.travelHistoryList = response.data.listObject;
									console.log("sxsaxs",$scope.travelHistoryList);
								});*/
                        
                        var URI = BASE_URL + ROOT_URL
        				+ GETOLDPATIENTTRAVELHISTORYDETAILS;
        			GenericServiceParamHeader
        				.serviceAction(METHOD_GET, URI,
        						data,
        						NOTIFICATION_MSG_STATUS_FALSE,paramObjForPer,headerObj)
        				.then(
        						function(response) {
        							console.log(response);
        							// $rootScope.startLoader();
        							if (response.data.status == "success")
        							$scope.travelHistoryOldList = response.data.listObject;
        						});
        			
        			var URI = BASE_URL + ROOT_URL
        			+ GETCURRENTPATIENTTRAVELHISTORYDETAILS;
        		GenericServiceParamHeader
        			.serviceAction(METHOD_GET, URI,
        					data,
        					NOTIFICATION_MSG_STATUS_FALSE,paramObjForPer,headerObj)
        			.then(
        					function(response) {
        						console.log(response);
        						
        						// $rootScope.startLoader();
        						if (response.data.status == "success")
        						$scope.travelHistoryCurrentList = response.data.listObject;
        						
        						$scope.travelHistoryCurrentListDoctor = [];
        						if($scope.roleId == '2' )
        							{
        								
        								angular.forEach(response.data.listObject, function(value,key) {
        									
        										if(value.roleId == 3 && value.isReviewedFlag == 'N')
        											{
        										var data = {};
        										data.whenTravel = moment(value.whenTravel).format('DD-MM-YYYY HH:mm:ss'),
        										data.isReviewedFlag = 'N',
        										data.isEnterInErrorStatus='N',
        										data.description = value.description,
        										data.travelHistoryDetailsId  = value.travelHistoryDetailsId;
        										$scope.travelHistoryCurrentListDoctor.push(data);
        								}
        								});
        							}
        						
        					});
        		
        		
					//END For Get History Travel
                        
                        // object for Complaints
					     $scope.historyTravelsList = [ {
					    	 description : '',
					    	 isReviewedFlag :'N',
					    	 isEnterInErrorStatus:'N',
					    	 
							} ];
					     
                      		     
								     // object for Complaints
								     $scope.dataListComplaints = [ {
								    	 	diagnosisId : '',
											since :'',
											duration:'Day',
											remark:'',
											isReviewedFlag : 'N',
											complaintAppoId : '0'
											
										} ];
								     
								   //object for History Personal
								     $scope.historyPersonalList = [{
								    	 isNoSignificantHistoryStatus:'N',
											description:"",
											remark:"",
											isEnterInErrorStatus:"N",
											isReviewedFlag:"N"
									}];
								   //object for History family
								     $scope.historyFamilyList = [{
								    	 isNoFamilyHistoryStatus:'N',
								    	 diagnosisId:"",
										 relationId:"",
										 remark:"",
										 isEnterInErrorStatus:"N",
										 isReviewedFlag:"N"
									}];
								     
								     //object for History Surgical
								     $scope.historySurgicalList = [{
								    	    isNoSignificantStatus:'N',
											surgeryId:0,
											surgenName:"",
											month:"",
											year:"",
											remark:"",
											isEnterInErrorStatus:"N",
											isReviewedFlag:"N"
									}];
								     
								     //object for History Surgical
								     $scope.historyMedicalList = [{
								    	 isNoSignificantStatus:'N',
								    	 	diagnosisId:'Y',
											since:"",
											duration:"Day",
											remark:"",
											isEnterInError:"N",
											isReviewedFlag:"N"
									}];
								     
								     
								     //object for patient Allergy
								     $scope.patientAllergyObject = {
								    	 	isNoKnownAllergies:'N',
								    	 	allergyTypeId:0,
								    	 	allergyId:"",
								    	 	month:"",
								    	 	year:"",
								    	 	type:"",
								    	 	natureOfReaction:"",
								    	 	severityId:"",
								    	 	remark:"",
								    	 	isEnterInErrorStatus:"N",
											isReviewedFlag:"N",
											
								    	 	
									};
								
								     
								     //object for patient vitals in Examination section
								     $scope.examinationVitalsObject = {
								    	 	weightValue:72,
								    	 	heightValue:157.3,
								    	 	temperatureValue:17.7,
								    	 	bloodPresureSystolicValue:120,
								    	 	bloodPresureDiastolicValue:180,
								    	 	bloodSugarValue:326,
								    	 	pulseRateValue:76,
								    	 	bmiValue:33.14
									};
								     
									       /* $scope.weightMin = 10;
									        $scope.weightMax=150;
									        $scope.heightMin=10.5;
									        $scope.heightMax=450.3;
									        $scope.temperatureMin=7.7;
									        $scope.temperatureMax=27.7;
									        $scope.bloodPresureSystolicMin=20;
									        $scope.bloodPresureSystolicMax=180;
									        $scope.bloodPresureDiastolicMin=18;
									        $scope.bloodPresureDiastolicMax=250;
									        $scope.bloodSugarMin=32;
									        $scope.bloodSugarMax=400;
									        $scope.pulseRateMin=10;
									        $scope.pulseRateMax=176;*/
								     
								     
								     //object for Patient General Examination in Examination Section
								     $scope.examinationGeneralExaminationList = [{
								    	 	system:'',
								    	 	nad:'N',
											remarks:""
									}];
								     
								     
								     //object for Diagnosis List
								     $scope.dataListDiagnosis = [ {
								    	 	diagnosisId : '',
											type :'',
											remark:''
										} ];
								    
								    	 
								     
								     
								     
							}

							$scope.init();
							
							
								//Get Allergen by Allergy Type Id
							$scope.getAllergenListByAllergyId = function(id)
							{
								var paramObjForPer = {
										allergy_type_id:id
								};
								var data = {};
								var headerObj = {
										organization_id:$scope.organizationId,
										unit_id:$scope.unitId
								};
								var URI = BASE_URL + ROOT_URL
								+ GETALLERGYMASTERBYALLERYTYPEID;
							GenericServiceParamHeader
								.serviceAction(METHOD_GET, URI,
										data,
										NOTIFICATION_MSG_STATUS_FALSE,paramObjForPer,headerObj)
								.then(
										function(response) {
											console.log(response);
											// $rootScope.startLoader();
											if (response.data.status == "success")
												$scope.allergenList = response.data.listObject;
										});
							
							
							}
							
							
							//for added HOISTORY Travel
							 $scope.addHistoryTravel = function(index) 
							 {
								 console.log("index add",index);
									var data = {
											description:"",
											isEnterInErrorStatus:"N",
											isReviewedFlag:"N"
									};
									var len = $scope.historyTravelsList.length;
									
									$scope.historyTravelsList.push(data);
								};
								
								//for HOISTORY Travel
								$scope.removeHistoryTravel = function(index) {
									console.log("removeIndex",index);
									var newDataList = [];
									var le = $scope.historyTravelsList.length;
									var i = 1;
									if(le != i){
									angular.forEach($scope.historyTravelsList, function(v) {
										if (index != i) {
											newDataList.push(v);
										}
										i++;
									});
									
									$scope.historyTravelsList = newDataList;
									}
								};
								
							
							//for added diagnosis
							 $scope.addDiagnosis = function(index) 
							 {
								 console.log("index add",index);
									var data = {
											diagnosisId : '',
											type :'',
											remark:''
									};
									var len = $scope.dataListDiagnosis.length;
									
									$scope.dataListDiagnosis.push(data);
								};
								
								//for remove diagnosis
								$scope.removeDiagnosis = function(index) {
									console.log("removeIndex",index);
									var newDataList = [];
									var le = $scope.dataListDiagnosis.length;
									var i = 1;
									if(le != i){
									angular.forEach($scope.dataListDiagnosis, function(v) {
										if (index != i) {
											newDataList.push(v);
										}
										i++;
									});
									
									$scope.dataListDiagnosis = newDataList;
									}
								};
								
								
								//for added complaints
								 $scope.add = function(index) 
								 {
									 console.log("index add",index);
										var data = {
												diagnosisId : '',
												since :'',
												duration:'Day',
												remark:'',
												isReviewedFlag : 'N',
												complaintAppoId : '0'
										};
										var len = $scope.dataListComplaints.length;
										
										$scope.dataListComplaints.push(data);
									};
									
									//for remove complaints
									$scope.remove = function(index) {
										console.log("removeIndex",index);
										var newDataList = [];
										var le = $scope.dataListComplaints.length;
										var i = 1;
										if(le != i){
										angular.forEach($scope.dataListComplaints, function(v) {
											if (index != i) {
												newDataList.push(v);
											}
											i++;
										});
										
										$scope.dataListComplaints = newDataList;
										}
									};
									
									//for added HOISTORY PERSONAL
									 $scope.addHistoryPersonal = function(index) 
									 {
										 console.log("index add",index);
											var data = {
													isNoSignificantHistoryStatus:'N',
													description:"",
													remark:"",
													isEnterInErrorStatus:"N",
													 isReviewedFlag:"N"
											};
											var len = $scope.historyPersonalList.length;
											
											$scope.historyPersonalList.push(data);
										};
										
										//for HOISTORY PERSONAL
										$scope.removeHistoryPersonal = function(index) {
											console.log("removeIndex",index);
											var newDataList = [];
											var le = $scope.historyPersonalList.length;
											var i = 1;
											if(le != i){
											angular.forEach($scope.historyPersonalList, function(v) {
												if (index != i) {
													newDataList.push(v);
												}
												i++;
											});
											
											$scope.historyPersonalList = newDataList;
											}
										};
										
										
										//for added HOISTORY FAMILY
										 $scope.addHistoryFamily = function(index) 
										 {
											 console.log("index add",index);
												var data = {
														isNoFamilyHistoryStatus:'N',
														diagnosisId:"",
														relationId:"",
														remark:"",
														isEnterInErrorStatus:"N",
														isReviewedFlag:"N"
												};
												var len = $scope.historyFamilyList.length;
												
												$scope.historyFamilyList.push(data);
											};
											
											//Remove for HOISTORY FAMILY
											$scope.removeHistoryFamily = function(index) {
												console.log("removeIndex",index);
												var newDataList = [];
												var le = $scope.historyFamilyList.length;
												var i = 1;
												if(le != i){
												angular.forEach($scope.historyFamilyList, function(v) {
													if (index != i) {
														newDataList.push(v);
													}
													i++;
												});
												
												$scope.historyFamilyList= newDataList;
												}
											};
											
											//for added HOISTORY Surgical addHistorySurgical
											 $scope.addHistorySurgical = function(index) 
											 {
												 console.log("index add",index);
													var data = {
															isNoSignificantStatus:'N',
															surgeryId:0,
															surgenName:"",
															month:"",
															year:"",
															remark:"",
															isEnterInErrorStatus:"N",
															isReviewedFlag:"N"
													};
													var len = $scope.historySurgicalList.length;
													
													$scope.historySurgicalList.push(data);
												};
												
												//Remove for HOISTORY Surgical
												$scope.removeHistorySurgical = function(index) {
													console.log("removeIndex",index);
													var newDataList = [];
													var le = $scope.historySurgicalList.length;
													var i = 1;
													if(le != i){
													angular.forEach($scope.historySurgicalList, function(v) {
														if (index != i) {
															newDataList.push(v);
														}
														i++;
													});
													
													$scope.historySurgicalList= newDataList;
													}
												};
												
												
												//for added History Medical
												 $scope.addHistoryMedical = function(index) 
												 {
													 console.log("index add",index);
														var data = {
																isNoSignificantStatus:'N',
																diagnosisId:'Y',
																since:"",
																duration:"Day",
																remark:"",
																isEnterInError:"N",
																isReviewedFlag:"N"
														};
														var len = $scope.historyMedicalList.length;
														
														$scope.historyMedicalList.push(data);
													};
													
													//for remove History Medical
													$scope.removeHistoryMedical = function(index) {
														console.log("removeIndex",index);
														var newDataList = [];
														var le = $scope.historyMedicalList.length;
														var i = 1;
														if(le != i){
														angular.forEach($scope.historyMedicalList, function(v) {
															if (index != i) {
																newDataList.push(v);
															}
															i++;
														});
														
														$scope.historyMedicalList = newDataList;
														}
													};
													
													/*$scope.setDefaultValuesPersonal = function(val,idx)
													{
														if(val == 'Y')
															{
															$scope.historyPersonalList[idx].description = "No Significant History";
															$scope.historyPersonalList[idx].remark = "No Significant History";
															}
														else
															{
															$scope.historyPersonalList[idx].description = "";
															$scope.historyPersonalList[idx].remark = "";
															}
														
													}
													$scope.setDefaultValueFamily  = function(val,idx)
													{
														alert(val+"dscds"+idx);
														if(val == 'Y')
															{
															$scope.historyFamilyList[idx].remark = "No Significant History";
															}
														else
															{
															$scope.historyFamilyList[idx].remark = "";
															}
														
													}*/
													
													
													
													//for added General Examination
													 $scope.addGeneralExamination = function(index) 
													 {
														 console.log("index add",index);
															var data = {
																	system:'',
																	nad:'N',
																	remark:""
															};
															var len = $scope.examinationGeneralExaminationList.length;
															
															$scope.examinationGeneralExaminationList.push(data);
														};
														
														//for remove General Examination
														$scope.removeGeneralExamination = function(index) {
															console.log("removeIndex",index);
															var newDataList = [];
															var le = $scope.examinationGeneralExaminationList.length;
															var i = 1;
															if(le != i){
															angular.forEach($scope.examinationGeneralExaminationList, function(v) {
																if (index != i) {
																	newDataList.push(v);
																}
																i++;
															});
															$scope.examinationGeneralExaminationList = newDataList;
															}
														};

				
														
														//Search Vital By Dates
														$scope.searchVitalByDates = function()
														{
															
															var data = {
																	
															};
															var paramObj = {
																	FromDate:moment($scope.datepickerFromDate).format('YYYY-MM-DD'),
																	ToDate:moment($scope.datepickerToDate).format('YYYY-MM-DD'),
																	VitalId:$scope.searchVitalId,
																	encounterId:$scope.encounterDetailsObj.encounterId
															};
															var headers={
																	'organization_id': $scope.organizationId,
																	'unit_id':$scope.unitId,
																	'patient_id':$scope.encounterDetailsObj.patientId,
																	'Content-Type':'application/json'
															};
															//return false;
															var URI = BASE_URL + ROOT_URL
															+ GETAPPOINTMENTVITALMAPPERBYTRAND;
													
															GenericServiceParamHeader
															.serviceAction(METHOD_GET, URI,
																	data,
																	NOTIFICATION_MSG_STATUS_FALSE,paramObj,headers)
															.then(
																	function(response) {
																		console.log(response);
																		// $rootScope.startLoader();
																		if (response.data.status == "success")
																			$scope.searcVitalsList = response.data.listObject;
																		
																		/*$scope.labels = ["January", "February", "March", "April", "May", "June", "July"];
																	    $scope.series = ['Series A', 'Series B'];*/
																		 $scope.series = ['Series A'];
																	   /* $scope.data = [
																	        [65, 59, 80, 81, 56]
																	    ];*/
																		$scope.labels = [];
																		$scope.data =[];
																		 angular.forEach($scope.searcVitalsList, function(value , key) {
																			 
																			 		$scope.labels.push(value.createdDate);
																			 		 $scope.data.push(value.value);
																			 
							                                             		});
																		
																		
																	});
														}
														
														
														
							//for ICD 10 Typehead
							$scope.searchGroupList = function(keyword) {
								
								var data = {};
								var paramObj = {
										diagnosisName : keyword
								};
								var headerObj = {
									//doctor_id : $scope.appointmentDetailsObj.doctorId,
										//doctor_id :1,
									unit_id : $scope.unitId,
									organization_id : $scope.organizationId
								}
								console.log(data);

								var URI = BASE_URL + ROOT_URL
										+ DIAGNOSISAUTOFILLSEARCH;
								
								return GenericServiceParamHeader
										.serviceAction(METHOD_GET, URI,
												data,
												NOTIFICATION_MSG_STATUS_FALSE,paramObj,headerObj)
										.then(
												function(response) {
													console.log(response);
													// $rootScope.startLoader();
													if (response.data.status == "success")
														return response.data.listObject;
												});

							}
							
							
							
							
							//Selected TypeHead on Diagnosis
							$scope.selectDignosisIdForDiagnosis = function(info, model,
									label,ind) {
								console.log(info);
								$scope.dataListDiagnosis[ind].diagnosisId = info.diagnosisId;
							}
							
							
							//Selected TypeHead on Complaints
							$scope.selectPatientId = function(info, model,
									label,ind) {
								console.log(info);
								$scope.dataListComplaints[ind].diagnosisId = info.diagnosisId;
							}
							
							//Selected TypeHead on Family History
							$scope.selectDignosisIdForFamily = function(info, model,
									label,ind) {
								console.log(info);
								$scope.historyFamilyList[ind].diagnosisId = info.diagnosisId;
							}
							
							//Selected TypeHead on Medical History
							$scope.selectDignosisIdForMedical = function(info, model,
									label,ind) {
								console.log(info);
								$scope.historyMedicalList[ind].diagnosisId = info.diagnosisId;
							}
							
							
							//Search Surgery with Typehead
							$scope.searchGroupSurgery = function(keyword) {
								
								var data = {};
								var paramObj = {
										surgery_name : keyword
								};
								var headerObj = {
									unit_id : $scope.unitId,
									organization_id : $scope.organizationId
								}
								console.log(data);

								var URI = BASE_URL + ROOT_URL
										+ GETSURGERYMASTERLISTAUTOFILLSERACH;
								
								return GenericServiceParamHeader
										.serviceAction(METHOD_GET, URI,
												data,
												NOTIFICATION_MSG_STATUS_FALSE,paramObj,headerObj)
										.then(
												function(response) {
													console.log(response);
													// $rootScope.startLoader();
													if (response.data.status == "success")
														return response.data.listObject;
												});

							}
							
							//Selected TypeHead on Complaints
							$scope.selectSurgeryId = function(info, model,
									label,ind) {
								console.log(info);
								$scope.historySurgicalList[ind].surgeryId = info.surgery_id;
							}
							
							
							//Search Surgen with Typehead
							$scope.searchGroupSurgen = function(keyword) {
								
								var data = {};
								var paramObj = {
										doctor_name : keyword
								};
								var headerObj = {
									unit_id : $scope.unitId,
									organization_id : $scope.organizationId
								}
								console.log(data);

								var URI = BASE_URL + ROOT_URL
										+ GETDOCTORMASTERLISTAUTOFILLSERACH;
								
								return GenericServiceParamHeader
										.serviceAction(METHOD_GET, URI,
												data,
												NOTIFICATION_MSG_STATUS_FALSE,paramObj,headerObj)
										.then(
												function(response) {
													console.log(response);
													// $rootScope.startLoader();
													if (response.data.status == "success")
														return response.data.listObject;
												});

							}
							
							//START TRAVEL HISTORY
							$scope.saveTravelHistory = function()
							{
								
								$scope.savePatientTravelHistory = [];
								angular.forEach($scope.historyTravelsList, function(value, key) {
                                   	
                                  			var newArr = {};
                                		 	newArr.description = value.description;
                                		 	newArr.whenTravel = moment(new Date(value.datepickerTravel)).format('DD-MM-YYYY HH:mm:ss');
                                		 	newArr.isEnterInErrorStatus=value.isEnterInErrorStatus;
                                		 	newArr.isReviewedFlag=value.isReviewedFlag;
                                		 	$scope.savePatientTravelHistory.push(newArr);
                                		
                                	
                                 		});
								
								
								var data = {
										
										createdDate:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updatedDate:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updatedBy:$scope.userId,
										createdBy:$scope.userId,
										status:'A',
										encounterId: $scope.encounterDetailsObj.encounterId,
										patientId : $scope.encounterDetailsObj.patientId,
										roleId:$scope.roleId
										//listOfPatientTravelHistoryDtos:$scope.savePatientTravelHistory
										
								};
								
								if($scope.roleId == 3)
								{
									data.listOfPatientTravelHistoryDtos=$scope.savePatientTravelHistory;
									
								}
							else 
								{
								console.log('ds',$scope.travelHistoryCurrentListDoctor);
									//angular.extend($scope.dataListComplaints,$scope.complaintsPatientListDoctor);
								angular.forEach($scope.savePatientTravelHistory, function(value , key) {
										
							 				var dData = {
							 						description:value.description,
							 						whenTravel:value.whenTravel,
													isEnterInErrorStatus:value.isEnterInErrorStatus,
													isReviewedFlag:value.isReviewedFlag,
							 				}
							 				$scope.travelHistoryCurrentListDoctor.push(dData);
								
							 
                         		});
									data.listOfPatientTravelHistoryDtos = $scope.travelHistoryCurrentListDoctor;
								}
								
								console.log(data);
								
								var paramObj = {};
								var headers={
										'organization_id': $scope.organizationId,
										'unit_id':$scope.unitId,
										'Content-Type':'application/json'
										
								};
								//return false;
								var URI = BASE_URL + ROOT_URL
								+ SAVEPATIENTTRAVELHISTORYDETAILS;
						
								GenericServiceParamHeader
								.serviceAction(METHOD_POST, URI,
										data,
										NOTIFICATION_MSG_STATUS_TRUE,paramObj,headers)
								.then(
										function(response) {
											console.log(response);
											// $rootScope.startLoader();
											if (response.data.status == "success")
												$scope.init();
												return response.data.listObject;
										});
							}
							
							
							$scope.showUpdateTravelHistory = function(item)
							{
								console.log(item);
								$scope.travelUpdateObj = {
										
										description:item.description,
										isEnterInErrorStatus : item.isEnterInErrorStatus,
										isReviewedFlag : item.isReviewedFlag,
										travelHistoryDetailsId :item.travelHistoryDetailsId,
								};
								$scope.datepickerWhen = new Date(item.whenTravel);
								$scope.itemRoleId = item.roleId;
								
							}
							
							
							$scope.updateTravelHistory = function()
							{
								$scope.savePatientTravelHistory = [];
								$scope.travelUpdateObj.whenTravel = moment(new Date($scope.datepickerWhen)).format('DD-MM-YYYY HH:mm:ss');
								$scope.savePatientTravelHistory.push($scope.travelUpdateObj);
										var data = {
										createdDate:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updatedDate:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updatedBy:$scope.userId,
										createdBy:$scope.userId,
										status:'A',
										patientId : $scope.encounterDetailsObj.patientId,
										roleId : $scope.roleId,
										encounterId: $scope.encounterDetailsObj.encounterId,
										listOfPatientTravelHistoryDtos:$scope.savePatientTravelHistory
										
								};
								
								console.log(data);
								//return false;
								var paramObj = {};
								var headers={
										'organization_id': $scope.organizationId,
										'unit_id':$scope.unitId,
										'Content-Type':'application/json'
								};
								//return false;
								var URI = BASE_URL + ROOT_URL
								+ UPDATEPATIENTTRAVELHISTORYDETAILS;
						
								GenericServiceParamHeader
								.serviceAction(METHOD_POST, URI,
										data,
										NOTIFICATION_MSG_STATUS_TRUE,paramObj,headers)
								.then(
										function(response) {
											console.log(response);
											// $rootScope.startLoader();
											if (response.data.status == "success")
												$scope.init();
												return response.data.listObject;
										});
							}
							
							$scope.updateStatusForAcronymsTravel = function(item)
							{
									
								$scope.savePatientTravelHistory = [];
									var perObj = {
										
											description:item.description,
											isEnterInErrorStatus : item.isEnterInErrorStatus,
											isReviewedFlag : item.isReviewedFlag,
											travelHistoryDetailsId :item.travelHistoryDetailsId,
											whenTravel : item.whenTravel
								};
								var paramObj = {};
								
								$scope.savePatientTravelHistory.push(perObj);
								var headers={
										'organization_id': $scope.organizationId,
										'unit_id':$scope.unitId,
										'Content-Type':'application/json'
								};
								
										var data = {
										
										createdDate:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updatedDate:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updatedBy:$scope.userId,
										createdBy:$scope.userId,
										status:'A',
										encounterId : $scope.encounterDetailsObj.encounterId,
										patientId : $scope.encounterDetailsObj.patientId,
										roleId : $scope.roleId,
										listOfPatientTravelHistoryDtos : $scope.savePatientTravelHistory
										}
								
										
										console.log(data);
										//return false;
										var URI = BASE_URL + ROOT_URL
										+ UPDATEENTERERRORSTATUSPATIENTTRAVELHISTORYDETAILS;
								
										GenericServiceParamHeader
										.serviceAction(METHOD_POST, URI,
												data,
												NOTIFICATION_MSG_STATUS_TRUE,paramObj,headers)
										.then(
												function(response) {
													console.log(response);
													// $rootScope.startLoader();
													if (response.data.status == "success")
														$scope.init();
														return response.data.listObject;
												});
							}
							
							//END TRAVEL HISTORY
							
							$scope.saveDiagnosis = function()
							{
								var data = {
										
										created_date:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updated_date:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updated_by:$scope.userId,
										created_by:$scope.userId,
										status:'A',
										listDiagnosisPatientAppointmentDetailsDto:$scope.dataListDiagnosis
										
								};
								console.log(data);
								
								var paramObj = {};
								var headers={
										'organization_id': $scope.organizationId,
										'unit_id':$scope.unitId,
										'encounterId': $scope.encounterDetailsObj.encounterId,
										'Content-Type':'application/json',
										'patient_id' : $scope.encounterDetailsObj.patientId,
								};
								//return false;
								var URI = BASE_URL + ROOT_URL
								+ SAVEPATIENTDIAGNOSIS;
						
								GenericServiceParamHeader
								.serviceAction(METHOD_POST, URI,
										data,
										NOTIFICATION_MSG_STATUS_TRUE,paramObj,headers)
								.then(
										function(response) {
											console.log(response);
											// $rootScope.startLoader();
											if (response.data.status == "success")
												$scope.init();
												return response.data.listObject;
										});
							}
							
							
							//START COMPLAINTS
							//Save Complaints
							$scope.saveComplaints = function()
							{
								
								console.log("com",$scope.dataListComplaints);
								
								var data = {
										
										ceratedDate:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updatedDate:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updatedBy:$scope.userId,
										createdBy:$scope.userId,
										status:'A',
										roleId : $scope.roleId,
										encounterId: $scope.encounterDetailsObj.encounterId,
										
										
								};
								
								if($scope.roleId == 3)
								{
									
									data.listComplaintAppointmentDetailsDto = $scope.dataListComplaints;
								}
							else 
								{
									//angular.extend($scope.dataListComplaints,$scope.complaintsPatientListDoctor);
								angular.forEach($scope.dataListComplaints, function(value , key) {
									 
							 		if(value.diagnosisId > 0)
							 			{
							 				var dData = {
							 						diagnosisId : value.diagnosisId,
													duration:value.duration,
													since:value.since,
													remark : value.remark,
													isReviewedFlag : 'N',
													complaintAppoId : '0'
							 				}
							 				$scope.complaintsPatientListDoctor.push(dData);
							 			}
							 
                         		});
									data.listComplaintAppointmentDetailsDto = $scope.complaintsPatientListDoctor;
								}
								
								console.log(data);
								
								var paramObj = {};
								var headers={
										'organization_id': $scope.organizationId,
										'unit_id':$scope.unitId,
										'Content-Type':'application/json'
								};
								//return false;
								var URI = BASE_URL + ROOT_URL
								+ SAVECOMPLAINTSLIST;
						
								GenericServiceParamHeader
								.serviceAction(METHOD_POST, URI,
										data,
										NOTIFICATION_MSG_STATUS_TRUE,paramObj,headers)
								.then(
										function(response) {
											console.log(response);
											// $rootScope.startLoader();
											if (response.data.status == "success")
												$scope.init();
												return response.data.listObject;
										});
							}
							
							
							$scope.showUpdateComplaints = function(item)
							{
								$scope.complaintsUpdateObj = {
										
										diagnosisId : item.diagnosisId,
										duration:item.duration,
										since:item.since,
										remark : item.remark
								};
								$scope.diagnosisNameUpdate = item.diagnosisName;
								$scope.complaintAppoId = item.complaintAppoId;
								$scope.itemRoleId = item.roleId;
							}
							
							$scope.selectDiagnosisIdForComplaintsUpdate = function(info, model,
									label,ind) {
								console.log(info);
							$scope.complaintsUpdateObj.diagnosisId = info.diagnosisId;
							
							}
							
							$scope.updateComplaints = function()
							{
								$scope.dataListComplaints = [];
								$scope.dataListComplaints.push($scope.complaintsUpdateObj);
										var data = {
										ceratedDate:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updatedDate:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updatedBy:$scope.userId,
										createdBy:$scope.userId,
										status:'A',
										roleId : $scope.roleId,
										encounterId: $scope.encounterDetailsObj.encounterId,
										complaintAppoId : $scope.complaintAppoId,
										listComplaintAppointmentDetailsDto:$scope.dataListComplaints
										
								};
										
								/*if($scope.itemRoleId == 3 && $scope.roleId == 3)
									{
									data.complaintAppoId = $scope.complaintAppoId;
									}
								if($scope.itemRoleId == 2 &&  $scope.roleId == 2 )
									{
										data.complaintAppoId = $scope.complaintAppoId;
									}*/
								console.log(data);
							//	return false;
								var paramObj = {};
								var headers={
										'organization_id': $scope.organizationId,
										'unit_id':$scope.unitId,
										'Content-Type':'application/json'
								};
								//return false;
								var URI = BASE_URL + ROOT_URL
								+ UPDATECOMPLAINTAPPOINTMENT;
						
								GenericServiceParamHeader
								.serviceAction(METHOD_POST, URI,
										data,
										NOTIFICATION_MSG_STATUS_TRUE,paramObj,headers)
								.then(
										function(response) {
											console.log(response);
											// $rootScope.startLoader();
											if (response.data.status == "success")
												$scope.init();
												return response.data.listObject;
										});
							}
							
							//END COMPLAINTS
							
							
							//START PERSONAL HISTORY
							
							$scope.showUpdatePerosnalHistory = function(item)
							{
								console.log(item);
								$scope.personalUpdateObj = {
										
										description:item.description,
										remark : item.remark,
										isEnterInErrorStatus : item.isEnterInErrorStatus,
										isReviewedFlag : item.isReviewedFlag,
										personalHistoryDetailsId :item.personalHistoryDetailsId,
										isNoSignificantHistoryStatus : item.isNoSignificantHistoryStatus
								};
								$scope.personalHistoryDetailsId = item.personalHistoryDetailsId;
								$scope.itemRoleId = item.roleId;
							}
							
							$scope.updatePersonalHistory = function()
							{
								$scope.historyPersonalList = [];
								$scope.historyPersonalList.push($scope.personalUpdateObj);
										var data = {
										createdDate:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updatedDate:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updatedBy:$scope.userId,
										createdBy:$scope.userId,
										status:'A',
										patientId : $scope.encounterDetailsObj.patientId,
										roleId : $scope.roleId,
										encounterId: $scope.encounterDetailsObj.encounterId,
										listOfPatientPersonalHistoryDtos:$scope.historyPersonalList
										
								};
								
								console.log(data);
							//	return false;
								var paramObj = {};
								var headers={
										'organization_id': $scope.organizationId,
										'unit_id':$scope.unitId,
										'Content-Type':'application/json'
								};
								//return false;
								var URI = BASE_URL + ROOT_URL
								+ UPDATEPATIENTPERSONALHISTORYDETAILS;
						
								GenericServiceParamHeader
								.serviceAction(METHOD_POST, URI,
										data,
										NOTIFICATION_MSG_STATUS_TRUE,paramObj,headers)
								.then(
										function(response) {
											console.log(response);
											// $rootScope.startLoader();
											if (response.data.status == "success")
												$scope.init();
												return response.data.listObject;
										});
							}
							
							
							$scope.updateStatusForAcronyms = function(item)
							{
									
								$scope.historyPersonalList = [];
									var perObj = {
										
										description:item.description,
										remark : item.remark,
										isEnterInErrorStatus : item.isEnterInErrorStatus,
										isReviewedFlag : item.isReviewedFlag,
										personalHistoryDetailsId :item.personalHistoryDetailsId,
										isNoSignificantHistoryStatus : item.isNoSignificantHistoryStatus
								};
								var paramObj = {};
								
								$scope.historyPersonalList.push(perObj);
								var headers={
										'organization_id': $scope.organizationId,
										'unit_id':$scope.unitId,
										'Content-Type':'application/json'
								};
								
										var data = {
										
										createdDate:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updatedDate:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updatedBy:$scope.userId,
										createdBy:$scope.userId,
										status:'A',
										encounterId : $scope.encounterDetailsObj.encounterId,
										patientId : $scope.encounterDetailsObj.patientId,
										roleId : $scope.roleId,
										listOfPatientPersonalHistoryDtos : $scope.historyPersonalList
										}
								
										
										console.log(data);
										//return false;
										var URI = BASE_URL + ROOT_URL
										+ UPDATESTATUSENTERINERROR;
								
										GenericServiceParamHeader
										.serviceAction(METHOD_POST, URI,
												data,
												NOTIFICATION_MSG_STATUS_TRUE,paramObj,headers)
										.then(
												function(response) {
													console.log(response);
													// $rootScope.startLoader();
													if (response.data.status == "success")
														$scope.init();
														return response.data.listObject;
												});
										
								
								
							}
							$scope.saveHistoryPersonal = function()
							{
								
								
							var data = {
										
										createdDate:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updatedDate:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updatedBy:$scope.userId,
										createdBy:$scope.userId,
										status:'A',
										encounterId : $scope.encounterDetailsObj.encounterId,
										patientId : $scope.encounterDetailsObj.patientId,
										roleId : $scope.roleId,
										
								}

								if($scope.noSignificantPersonal == 'Y')
									{
										$scope.historyPersonalList = [{
								    	 isNoSignificantHistoryStatus:'Y',
											description:"",
											remark:"",
											isEnterInErrorStatus:"N",
											 isReviewedFlag:"N"
											}];
									}
								
								if($scope.roleId == 3)
								{
									data.listOfPatientPersonalHistoryDtos=$scope.historyPersonalList;
								}
							else 
								{
								console.log('ds',$scope.personalHistoryCurrentListDoctor);
									//angular.extend($scope.dataListComplaints,$scope.complaintsPatientListDoctor);
								angular.forEach($scope.historyPersonalList, function(value , key) {
									
							 				var dData = {
							 						isNoSignificantHistoryStatus : value.isNoSignificantHistoryStatus,
							 						description:value.description,
													remark : value.remark,
													isEnterInErrorStatus:value.isEnterInErrorStatus,
													isReviewedFlag:value.isReviewedFlag
							 				}
							 				$scope.personalHistoryCurrentListDoctor.push(dData);
							 			
							 
                         		});
									data.listOfPatientPersonalHistoryDtos = $scope.personalHistoryCurrentListDoctor;
								}
								
								
								
								
								
								console.log("Personal",data);
								//return false;
								var paramObj = {};
								var headers={
										'organization_id': $scope.organizationId,
										'unit_id':$scope.unitId,
										'Content-Type':'application/json'
								};
								//return false;
								var URI = BASE_URL + ROOT_URL
								+ SAVEPATIENTPERSONALHISTORYDETAILS;
						
								GenericServiceParamHeader
								.serviceAction(METHOD_POST, URI,
										data,
										NOTIFICATION_MSG_STATUS_TRUE,paramObj,headers)
								.then(
										function(response) {
											console.log(response);
											// $rootScope.startLoader();
											if (response.data.status == "success")
												$scope.init();
												return response.data.listObject;
										});
								
								
							}
							//END PERSONAL HISTORY
							
							//START FAMILY HISTORY
							
							$scope.showUpdateFamilyHistory = function(item)
							{
								console.log(item);
								$scope.familyUpdateObj = {
										
										diagnosisId:item.diagnosisId,
										relationId:item.relationId.toString(),
										remark : item.remark,
										isEnterInErrorStatus : item.isEnterInErrorStatus,
										isReviewedFlag : item.isReviewedFlag,
										familyHistoryId :item.familyHistoryId,
										isNoFamilyHistoryStatus : item.isNoFamilyHistoryStatus
								};
								$scope.itemRoleId = item.roleId;
							}
							
							
							$scope.updateFamilyHistory = function()
							{
								$scope.historyFamilyList = [];
								$scope.historyFamilyList.push($scope.familyUpdateObj);
										var data = {
										createdDate:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updatedDate:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updatedBy:$scope.userId,
										createdBy:$scope.userId,
										status:'A',
										patientId : $scope.encounterDetailsObj.patientId,
										roleId : $scope.roleId,
										encounterId: $scope.encounterDetailsObj.encounterId,
										listOfPatientFamilyHistoryDtos:$scope.historyFamilyList
										
								};
								
								console.log(data);
								//return false;
								var paramObj = {};
								var headers={
										'organization_id': $scope.organizationId,
										'unit_id':$scope.unitId,
										'Content-Type':'application/json'
								};
								//return false;
								var URI = BASE_URL + ROOT_URL
								+ UPDATEPATIENTFAMILYHISTORY;
						
								GenericServiceParamHeader
								.serviceAction(METHOD_POST, URI,
										data,
										NOTIFICATION_MSG_STATUS_TRUE,paramObj,headers)
								.then(
										function(response) {
											console.log(response);
											// $rootScope.startLoader();
											if (response.data.status == "success")
												$scope.init();
												return response.data.listObject;
										});
							}
							
							$scope.updateStatusForAcronymsFamily = function(item)
							{
									
								$scope.historyFamilyList = [];
									var perObj = {
										
											diagnosisId:item.diagnosisId,
											relationId:item.relationId,
											remark : item.remark,
											isEnterInErrorStatus : item.isEnterInErrorStatus,
											isReviewedFlag : item.isReviewedFlag,
											familyHistoryId :item.familyHistoryId,
											isNoFamilyHistoryStatus : item.isNoFamilyHistoryStatus
								};
								var paramObj = {};
								
								$scope.historyFamilyList.push(perObj);
								var headers={
										'organization_id': $scope.organizationId,
										'unit_id':$scope.unitId,
										'Content-Type':'application/json'
								};
								
										var data = {
										
										createdDate:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updatedDate:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updatedBy:$scope.userId,
										createdBy:$scope.userId,
										status:'A',
										encounterId : $scope.encounterDetailsObj.encounterId,
										patientId : $scope.encounterDetailsObj.patientId,
										roleId : $scope.roleId,
										listOfPatientFamilyHistoryDtos : $scope.historyFamilyList
										}
								
										
										console.log(data);
										//return false;
										var URI = BASE_URL + ROOT_URL
										+ UPDATEFAMILYSTATUSENTERINERROR;
								
										GenericServiceParamHeader
										.serviceAction(METHOD_POST, URI,
												data,
												NOTIFICATION_MSG_STATUS_TRUE,paramObj,headers)
										.then(
												function(response) {
													console.log(response);
													// $rootScope.startLoader();
													if (response.data.status == "success")
														$scope.init();
														return response.data.listObject;
												});
										
								
								
							}
							
							$scope.saveHistoryFamily = function()
							{
								//console.log($scope.historyFamilyList);
								

								var data = {
										
										createdDate:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updatedDate:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updatedBy:$scope.userId,
										createdBy:$scope.userId,
										status:'A',
										encounterId : $scope.encounterDetailsObj.encounterId,
										patientId : $scope.encounterDetailsObj.patientId,
										roleId : $scope.roleId,
										
								};
								
								if($scope.noSignificantFamiliy == 'Y')
								{
									 $scope.historyFamilyList = [{
								    	 isNoFamilyHistoryStatus:'N',
								    	 diagnosisId:"",
										 relationId:"",
										 remark:"",
										 isEnterInErrorStatus:"N",
										 isReviewedFlag:"N"
									}];
								}
								
								/*if($scope.noSignificantFamiliy == 'Y')
								{
									 $scope.historyFamilyList = [{
								    	 isNoFamilyHistoryStatus:'N',
								    	 diagnosisId:"",
										 relationId:"",
										 remark:"",
										 isEnterInErrorStatus:"N",
										 isReviewedFlag:"N"
									}];
									data.listOfPatientFamilyHistoryDtos=$scope.historyFamilyList;
								}
							else
								{
									data.listOfPatientFamilyHistoryDtos=$scope.historyFamilyList;
								}*/
								
								
								if($scope.roleId == 3)
								{
									data.listOfPatientFamilyHistoryDtos=$scope.historyFamilyList;
									
								}
							else 
								{
								console.log('ds',$scope.familyHistoryCurrentListDoctor);
									//angular.extend($scope.dataListComplaints,$scope.complaintsPatientListDoctor);
								angular.forEach($scope.historyFamilyList, function(value , key) {
										if(value.diagnosisId > 0){
							 				var dData = {
							 						isNoFamilyHistoryStatus : value.isNoFamilyHistoryStatus,
							 						diagnosisId:value.diagnosisId,
													remark : value.remark,
													isEnterInErrorStatus:value.isEnterInErrorStatus,
													isReviewedFlag:value.isReviewedFlag,
													diagnosisId :value.diagnosisId,
													relationId : value.relationId
							 				}
							 				$scope.familyHistoryCurrentListDoctor.push(dData);
								}
							 
                         		});
									data.listOfPatientFamilyHistoryDtos = $scope.familyHistoryCurrentListDoctor;
								}
								

								console.log(data);
								var paramObj = {};
								var headers={
										'organization_id': $scope.organizationId,
										'unit_id':$scope.unitId,
										'Content-Type':'application/json'
								};
								//return false;
								var URI = BASE_URL + ROOT_URL
								+ SAVEPATIENTFAMILYHISTORY;
						
								GenericServiceParamHeader
								.serviceAction(METHOD_POST, URI,
										data,
										NOTIFICATION_MSG_STATUS_TRUE,paramObj,headers)
								.then(
										function(response) {
											console.log(response);
											// $rootScope.startLoader();
											if (response.data.status == "success")
												$scope.init();
												return response.data.listObject;
										});
								
							}
							//END FAMILY HISTORY
							
							//START SURGICAL HISTORY
							$scope.showUpdateSurgicalHistory = function(item)
							{
								console.log(item);
								$scope.surgicalUpdateObj = {
										
										surgeryId:item.surgeryId,
										surgenName:item.surgenName,
										remark : item.remark,
										month : item.month,
										year : item.year,
										isEnterInErrorStatus : item.isEnterInErrorStatus,
										isReviewedFlag : item.isReviewedFlag,
										surgicalHistoryDetailsId :item.surgicalHistoryDetailsId,
										isNoSignificantStatus : item.isNoSignificantStatus
								};
								$scope.itemRoleId = item.roleId;
								
							}
							
							
							$scope.updateSurgicalHistory = function()
							{
								$scope.historySurgicalList = [];
								$scope.historySurgicalList.push($scope.surgicalUpdateObj);
										var data = {
										createdDate:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updatedDate:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updatedBy:$scope.userId,
										createdBy:$scope.userId,
										status:'A',
										patientId : $scope.encounterDetailsObj.patientId,
										roleId : $scope.roleId,
										encounterId: $scope.encounterDetailsObj.encounterId,
										listOfPatientSurgicalHistoryDto:$scope.historySurgicalList
										
								};
								
								console.log(data);
								//return false;
								var paramObj = {};
								var headers={
										'organization_id': $scope.organizationId,
										'unit_id':$scope.unitId,
										'Content-Type':'application/json'
								};
								//return false;
								var URI = BASE_URL + ROOT_URL
								+ UPDATEPATIENTSURGICALHISTORY;
						
								GenericServiceParamHeader
								.serviceAction(METHOD_POST, URI,
										data,
										NOTIFICATION_MSG_STATUS_TRUE,paramObj,headers)
								.then(
										function(response) {
											console.log(response);
											// $rootScope.startLoader();
											if (response.data.status == "success")
												$scope.init();
												return response.data.listObject;
										});
							}
							
							$scope.updateStatusForAcronymsSurgical = function(item)
							{
									
								$scope.historySurgicalList = [];
									var perObj = {
										
											surgeryId:item.surgeryId,
											surgenName:item.surgenName,
											remark : item.remark,
											month : item.month,
											year : item.year,
											isEnterInErrorStatus : item.isEnterInErrorStatus,
											isReviewedFlag : item.isReviewedFlag,
											surgicalHistoryDetailsId :item.surgicalHistoryDetailsId,
											isNoSignificantStatus : item.isNoSignificantStatus
											
								};
								var paramObj = {};
								
								$scope.historySurgicalList.push(perObj);
								var headers={
										'organization_id': $scope.organizationId,
										'unit_id':$scope.unitId,
										'Content-Type':'application/json'
								};
								
										var data = {
										
										createdDate:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updatedDate:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updatedBy:$scope.userId,
										createdBy:$scope.userId,
										status:'A',
										encounterId : $scope.encounterDetailsObj.encounterId,
										patientId : $scope.encounterDetailsObj.patientId,
										roleId : $scope.roleId,
										listOfPatientSurgicalHistoryDto : $scope.historySurgicalList
										}
								
										
										console.log(data);
										//return false;
										var URI = BASE_URL + ROOT_URL
										+ UPDATEENTERERRORSTATUSPATIENTSURGICALHISTORY;
								
										GenericServiceParamHeader
										.serviceAction(METHOD_POST, URI,
												data,
												NOTIFICATION_MSG_STATUS_TRUE,paramObj,headers)
										.then(
												function(response) {
													console.log(response);
													// $rootScope.startLoader();
													if (response.data.status == "success")
														$scope.init();
														return response.data.listObject;
												});
										
								
								
							}
							
							
							
							$scope.saveHistorySurgical = function()
							{
								
								//console.log($scope.historySurgicalList);
										var data = {
										
										createdDate:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updatedDate:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updatedBy:$scope.userId,
										createdBy:$scope.userId,
										status:'A',
										encounterId : $scope.encounterDetailsObj.encounterId,
										patientId : $scope.encounterDetailsObj.patientId,
										roleId : $scope.roleId
										
								};
										if($scope.noSignificantSurgical == 'Y')
										{
											 $scope.historySurgicalList = [{
										    	    isNoSignificantStatus:'N',
													surgeryId:0,
													surgenName:"",
													month:"",
													year:"",
													remark:"",
													isReviewedFlag :"",
													isEnterInErrorStatus:""
													
											}];
										}
								/*if($scope.noSignificantSurgical == 'Y')
								{
									 $scope.historySurgicalList = [{
								    	    isNoSignificantStatus:'N',
											surgeryId:0,
											surgenName:"",
											month:"",
											year:"",
											remark:""
									}];
								     
									data.listOfPatientSurgicalHistoryDto=$scope.historySurgicalList;
								}
							else
								{
									data.listOfPatientSurgicalHistoryDto=$scope.historySurgicalList;
								}*/
								
								
								if($scope.roleId == 3)
								{
									data.listOfPatientSurgicalHistoryDto=$scope.historySurgicalList;
									
								}
							else 
								{
								console.log('ds',$scope.surgicalHistoryCurrentListDoctor);
									//angular.extend($scope.dataListComplaints,$scope.complaintsPatientListDoctor);
								angular.forEach($scope.historySurgicalList, function(value , key) {
										if(value.surgeryId > 0){
							 				var dData = {
							 						month:value.month,
							 						year:value.year,
													remark : value.remark,
													isEnterInErrorStatus:value.isEnterInErrorStatus,
													isReviewedFlag:value.isReviewedFlag,
													surgeryId :value.surgeryId,
													surgenName : value.surgenName,
													isNoSignificantStatus : value.isNoSignificantStatus
							 				}
							 				$scope.surgicalHistoryCurrentListDoctor.push(dData);
								}
							 
                         		});
									data.listOfPatientSurgicalHistoryDto = $scope.surgicalHistoryCurrentListDoctor;
								}
								
								
								console.log(data);
								var paramObj = {};
								var headers={
										'organization_id': $scope.organizationId,
										'unit_id':$scope.unitId,
										'Content-Type':'application/json'
								};
								//return false;
								var URI = BASE_URL + ROOT_URL
								+ SAVEPATIENTSURGICALHISTORY;
						
								GenericServiceParamHeader
								.serviceAction(METHOD_POST, URI,
										data,
										NOTIFICATION_MSG_STATUS_TRUE,paramObj,headers)
								.then(
										function(response) {
											console.log(response);
											// $rootScope.startLoader();
											if (response.data.status == "success")
												$scope.init();
												return response.data.listObject;
										});
							}
							//END SURGICAL HISTORY
							
							//START MEDICAL HISTORY
							
							$scope.showUpdateMedicalHistory = function(item)
							{
								console.log(item);
								$scope.medicalUpdateObj = {
										
										diagnosisId:item.diagnosisId,
										since:item.since,
										remark : item.remark,
										duration : item.duration,
										isEnterInError : item.isEnterInError,
										isReviewedFlag : item.isReviewedFlag,
										patientMedicalHistoryId :item.patientMedicalHistoryId,
										isNoSignificantStatus : item.isNoSignificantStatus
								};
								$scope.itemRoleId = item.roleId;
								
							}
							
							
							$scope.updateMedicalHistory = function()
							{
								$scope.historyMedicalList = [];
								$scope.historyMedicalList.push($scope.medicalUpdateObj);
										var data = {
										created_date:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updated_date:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updated_by:$scope.userId,
										created_by:$scope.userId,
										status:'A',
										patientId : $scope.encounterDetailsObj.patientId,
										roleId : $scope.roleId,
										encounterId: $scope.encounterDetailsObj.encounterId,
										listOfPatientMedicalHistoryDto:$scope.historyMedicalList
										
								};
								
								console.log(data);
								//return false;
								var paramObj = {};
								var headers={
										'organization_id': $scope.organizationId,
										'unit_id':$scope.unitId,
										'Content-Type':'application/json'
								};
								//return false;
								var URI = BASE_URL + ROOT_URL
								+ UPDATEPATIENTMEDICALHISTORYDETAILS;
						
								GenericServiceParamHeader
								.serviceAction(METHOD_POST, URI,
										data,
										NOTIFICATION_MSG_STATUS_TRUE,paramObj,headers)
								.then(
										function(response) {
											console.log(response);
											// $rootScope.startLoader();
											if (response.data.status == "success")
												$scope.init();
												return response.data.listObject;
										});
							}
							
							$scope.updateStatusForAcronymsMedical = function(item)
							{
									
								$scope.historyMedicalList = [];
									var perObj = {
										
											diagnosisId:item.diagnosisId,
											since : item.since,
											duration : item.duration,
											isEnterInError : item.isEnterInError,
											isReviewedFlag : item.isReviewedFlag,
											patientMedicalHistoryId :item.patientMedicalHistoryId,
											isNoSignificantStatus : item.isNoSignificantStatus
											
								};
								var paramObj = {};
								
								$scope.historyMedicalList.push(perObj);
								var headers={
										'organization_id': $scope.organizationId,
										'unit_id':$scope.unitId,
										'Content-Type':'application/json'
								};
								
										var data = {
										
										created_date:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updated_date:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updated_by:$scope.userId,
										created_by:$scope.userId,
										status:'A',
										encounterId : $scope.encounterDetailsObj.encounterId,
										patientId : $scope.encounterDetailsObj.patientId,
										roleId : $scope.roleId,
										listOfPatientMedicalHistoryDto : $scope.historyMedicalList
										}
								
										
										console.log(data);
										//return false;
										var URI = BASE_URL + ROOT_URL
										+ UPDATEENTERERRORSTATUSPATIENTMEDICALHISTORYDETAILS;
								
										GenericServiceParamHeader
										.serviceAction(METHOD_POST, URI,
												data,
												NOTIFICATION_MSG_STATUS_TRUE,paramObj,headers)
										.then(
												function(response) {
													console.log(response);
													// $rootScope.startLoader();
													if (response.data.status == "success")
														$scope.init();
														return response.data.listObject;
												});
							}
							
							$scope.saveHistoryMedical = function()
							{
								//console.log($scope.historyMedicalList);
										var data = {
										
										created_date:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updated_date:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updated_by:$scope.userId,
										created_by:$scope.userId,
										status:'A',
										encounterId : $scope.encounterDetailsObj.encounterId,
										patientId : $scope.encounterDetailsObj.patientId,
										roleId : $scope.roleId
								};

								if($scope.noSignificantMedical == 'Y')
								{
									 $scope.historyMedicalList = [{
								    	 isNoSignificantStatus:'N',
								    	 	diagnosisId:'0',
											since:"",
											duration:"Day",
											remark:"",
											isEnterInError:"N",
											 isReviewedFlag:"N"
									}];
								}
							
								
								
								if($scope.roleId == 3)
								{
									data.listOfPatientMedicalHistoryDto=$scope.historyMedicalList;
									
								}
							else 
								{
								console.log('ds',$scope.medicalHistoryCurrentListDoctor);
									//angular.extend($scope.dataListComplaints,$scope.complaintsPatientListDoctor);
								angular.forEach($scope.historyMedicalList, function(value , key) {
										if(value.diagnosisId > 0){
							 				var dData = {
							 						diagnosisId:value.diagnosisId,
							 						since:value.since,
													remark : value.remark,
													isEnterInError:value.isEnterInError,
													isReviewedFlag:value.isReviewedFlag,
													duration :value.duration,
													isNoSignificantStatus : value.isNoSignificantStatus
							 				}
							 				$scope.medicalHistoryCurrentListDoctor.push(dData);
								}
							 
                         		});
									data.listOfPatientMedicalHistoryDto = $scope.medicalHistoryCurrentListDoctor;
								}
								
								
								console.log(data);
								var paramObj = {};
								var headers={
										'organization_id': $scope.organizationId,
										'unit_id':$scope.unitId,
										'Content-Type':'application/json'
								};
								//return false;
								var URI = BASE_URL + ROOT_URL
								+ SAVEPATIENTMEDICALHISTORYDETAILS;
						
								GenericServiceParamHeader
								.serviceAction(METHOD_POST, URI,
										data,
										NOTIFICATION_MSG_STATUS_TRUE,paramObj,headers)
								.then(
										function(response) {
											console.log(response);
											// $rootScope.startLoader();
											if (response.data.status == "success")
												$scope.init();
												return response.data.listObject;
										});
							}
							//END MEDICAL HISTORY
							//START IMMUNIZATION
							
							
							
							$scope.showUpdateImmunizationHistory = function(item)
							{
								console.log(item);
								$scope.ImmunizationUpdateObj = {
										
										drugId:item.drugId,
										isAdministeredStatus:item.isAdministeredStatus,
										isReviewedFlag : item.isReviewedFlag,
										immunizationHistoryMapperId :item.immunizationHistoryMapperId,
								};
								$scope.drugName = item.drugName;
								$scope.datepickerImmunization = new Date(item.immunizationDate);
								$scope.itemRoleId = item.roleId;
								
							}
							
							
							$scope.updateImmunizationHistory = function()
							{
								$scope.historyImmunizationList = [];
								$scope.ImmunizationUpdateObj.immunizationDate = moment(new Date($scope.datepickerImmunization)).format('DD-MM-YYYY HH:mm:ss');
								$scope.historyImmunizationList.push($scope.ImmunizationUpdateObj);
										var data = {
										createdDate:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updatedDate:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updatedBy:$scope.userId,
										createdBy:$scope.userId,
										status:'A',
										patientId : $scope.encounterDetailsObj.patientId,
										roleId : $scope.roleId,
										encounterId: $scope.encounterDetailsObj.encounterId,
										listListImmunizationHistoryMapperDto:$scope.historyImmunizationList
										
								};
								
								console.log(data);
								//return false;
								var paramObj = {};
								var headers={
										'organization_id': $scope.organizationId,
										'unit_id':$scope.unitId,
										'Content-Type':'application/json'
								};
								//return false;
								var URI = BASE_URL + ROOT_URL
								+ UPDATEIMMUNIZATIONHISTORYMAPPER;
						
								GenericServiceParamHeader
								.serviceAction(METHOD_POST, URI,
										data,
										NOTIFICATION_MSG_STATUS_TRUE,paramObj,headers)
								.then(
										function(response) {
											console.log(response);
											// $rootScope.startLoader();
											if (response.data.status == "success")
												$scope.init();
												return response.data.listObject;
										});
							}
							
							
							$scope.saveHistoryImmunization = function()
							{
								console.log($scope.drugHistoryList);
								
								var paramObj = {};
								var headers={
										'organization_id': $scope.organizationId,
										'unit_id':$scope.unitId,
										'Content-Type':'application/json'
								};
								
									$scope.saveImmunizationFinalArr = [];
								  angular.forEach($scope.drugHistoryList, function(value, key) {
                                   	
                                  	
                                 	 if(value.isAdministeredStatus != '')
                                 		 {
                                 		 	
                                 		 	var newArr = {};
                                 		 	newArr.drugId = value.drugId;
                                 		 	newArr.isAdministeredStatus = value.isAdministeredStatus;
                                 		 	newArr.immunizationDate = moment(new Date(value.datepicker)).format('DD-MM-YYYY HH:mm:ss');
                                 		 	newArr.isReviewedFlag = value.isReviewedFlag;
                                 		 	newArr.immunizationHistoryMapperId = value.immunizationHistoryMapperId;
                                 		 	$scope.saveImmunizationFinalArr.push(newArr);
                                 		 	//newArr.patientId = $scope.appointmentDetailsObj.patientId;
                                 		 	//newArr.appointmentId =  $scope.appointmentDetailsObj.appointmentId;
                                 		 	
                                 		 	//listListImmunizationHistoryMapperDto 
                                 		 }
                                 	
                                  		});
								  
								  var data = {
										patientId :$scope.encounterDetailsObj.patientId,
                               		 	encounterId : $scope.encounterDetailsObj.encounterId,
										 // patientId : 1,
                               		 	//appointmentId :  14,
                               		 	createdDate:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
  										updatedDate:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
  										updatedBy:$scope.userId,
  										createdBy:$scope.userId,
  										status:'A',
  										roleId : $scope.roleId,
  										listListImmunizationHistoryMapperDto : $scope.saveImmunizationFinalArr
								  }
								  console.log(data);
								 //return false;
								  
								  var URI = BASE_URL + ROOT_URL
									+ SAVEIMMUNIZATIONHISTORYMAPPER;
							
									GenericServiceParamHeader
									.serviceAction(METHOD_POST, URI,
											data,
											NOTIFICATION_MSG_STATUS_TRUE,paramObj,headers)
									.then(
											function(response) {
												console.log(response);
												// $rootScope.startLoader();
												if (response.data.status == "success"){
													$scope.init();
												}
													//return response.data.listObject;
											});
									
								  
							}
							
							//END IMMUNAZITION
							$scope.checkAll = function() {
								$scope.patient.exercise = [
        		 					"Yoga",
        		 					"WeightTraining",
        		 					"Cardio",
        		 					"Sports",
        		 					];
							};
							  
							//STARY HABIT
							
							$scope.updateStatusForAcronymsHabit = function(item)
							{
									
								$scope.habitAcronymsObj = [];
									var perObj = {
										
											  	"habitId": item.habitId,
										        "habitTypeId":item.habitTypeId,
										        "remark":item.remark,
										        "cigarettesPerDay":item.cigarettesPerDay,
										        "yearsSmoked":item.yearsSmoked,
										        "gmsPerDay":item.gmsPerDay,
										        "yearsUsed":item.yearsUsed,
										        "mlsPerDay":item.mlsPerDay,
										        "frequency":item.frequency,
										        "leftWhen":item.leftWhen,
										        "packYear":item.packYear,
										        "typeOfExercise": item.typeOfExercise,
										        "isReviewedFlag":item.isReviewedFlag,
										        "isEnterInErrorStatus":item.isEnterInErrorStatus,
										        "status":item.status,
										        "patientHabitDetailsId":item.patientHabitDetailsId
											
								};
								var paramObj = {};
								
								$scope.habitAcronymsObj.push(perObj);
								var headers={
										'organization_id': $scope.organizationId,
										'unit_id':$scope.unitId,
										'Content-Type':'application/json'
								};
								
										var data = {
										
										createdDate:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updatedDate:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updatedBy:$scope.userId,
										createdBy:$scope.userId,
										status:'A',
										encounterId : $scope.encounterDetailsObj.encounterId,
										patientId : $scope.encounterDetailsObj.patientId,
										roleId : $scope.roleId,
										listOfPatientHabitDetailsDto : $scope.habitAcronymsObj
										}
								
										
										console.log(data);
										//return false;
										var URI = BASE_URL + ROOT_URL
										+ UPDATEENTERERRORSTATUSPATIENTHABITDETAILS;
								
										GenericServiceParamHeader
										.serviceAction(METHOD_POST, URI,
												data,
												NOTIFICATION_MSG_STATUS_TRUE,paramObj,headers)
										.then(
												function(response) {
													console.log(response);
													// $rootScope.startLoader();
													if (response.data.status == "success")
														$scope.init();
														return response.data.listObject;
												});
							}
							
							$scope.savePatientHabits = function()
							{
								
								var paramObj = {};
								var headers={
										'organization_id': $scope.organizationId,
										'unit_id':$scope.unitId,
										'Content-Type':'application/json'
								};
								
								console.log("HHBBTT",$scope.habitFinalList);
								//console.log($scope.habitFinalList.childHabitObj.length);
								$scope.savePatientHabitFinalArr = [];
								  angular.forEach($scope.habitFinalList, function(value, key) {
									  
									  if(value.childHabitObj[0].habitTypeId > 0)
										  {
									  var finalExerciseStr = '';
									  var data = {
											  habitId:value.childHabitObj[0].habitId,
											  habitTypeId:value.childHabitObj[0].habitTypeId,
											  cigarettesPerDay:value.childHabitObj[0].cigarettesPerDay,
											  remark:value.childHabitObj[0].remark,
											  yearsSmoked:value.childHabitObj[0].yearsSmoked,
											  gmsPerDay:value.childHabitObj[0].gmsPerDay,
											  yearsUsed:value.childHabitObj[0].yearsUsed,
											  mlsPerDay:value.childHabitObj[0].mlsPerDay,
											  frequency:value.childHabitObj[0].frequency,
											  leftWhen:value.childHabitObj[0].leftWhen,
											  packYear:value.childHabitObj[0].packYear,
											  typeOfExercise:finalExerciseStr,
											  durationOfLeftWhen:value.childHabitObj[0].duration,
											  packYear:value.childHabitObj[0].packYear,
											  drug:value.childHabitObj[0].drug,
											  isReviewedFlag : value.childHabitObj[0].isReviewedFlag,
                          		 			  isEnterInErrorStatus : value.childHabitObj[0].isEnterInErrorStatus,
                          		 	          //patientHabitDetailsId : value.childHabitObj[0].patientHabitDetailsId,
											  
									  }
										if($scope.roleId == 2)
                        		 		{
                            		 		data.patientHabitDetailsId =value.childHabitObj[0].patientHabitDetailsId;
                        		 		}
									  
									  if(value.childHabitObj[0].habitId == 5)
										  {
										  
										  if($scope.patient.exercise)
											{
												
												for(var i=0;i < $scope.patient.exercise.length;i++)
													{
														
														finalExerciseStr+=$scope.patient.exercise[i]+',';
													}
											}
										  
										  	data.typeOfExercise = finalExerciseStr;
										  }
									  
									  $scope.savePatientHabitFinalArr.push(data);
									  
								  }
                               });
								  
								  console.log("SAVE HBBTT",$scope.savePatientHabitFinalArr);
								  //return false;
								  
								  var data = {
										patientId : $scope.encounterDetailsObj.patientId,
                             		 	createdDate:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updatedDate:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updatedBy:$scope.userId,
										createdBy:$scope.userId,
										status:'A',
										roleId:$scope.roleId,
										encounterId: $scope.encounterDetailsObj.encounterId,
										listOfPatientHabitDetailsDto : $scope.savePatientHabitFinalArr
								  }
								  
								  
								 
								  console.log("Final Habit Save Arr",data);
								 // return false;
								  
								  var URI = BASE_URL + ROOT_URL
									+ SAVEPATIENTHABITDETAILS;
							
									GenericServiceParamHeader
									.serviceAction(METHOD_POST, URI,
											data,
											NOTIFICATION_MSG_STATUS_TRUE,paramObj,headers)
									.then(
											function(response) {
												console.log(response);
												// $rootScope.startLoader();
												if (response.data.status == "success")
													$scope.init();
													return response.data.listObject;
											});
									
							}
							
							//END HABIT
							//START SAVE ALLEGRY LIST
							$scope.savePatientAllergy = function()
							{
								
								
								//$scope.patientAllergyObject.patientId = 1;
								$scope.saveAllergyList = [];
								$scope.saveAllergyList.push($scope.patientAllergyObject);
								var data = {
										createdDate:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updatedDate:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updatedBy:$scope.userId,
										createdBy:$scope.userId,
										status : 'A',
										patientId : $scope.encounterDetailsObj.patientId,
										roleId : $scope.roleId,
										encounterId :$scope.encounterDetailsObj.encounterId,
										comments : ''
										//listOfAllergyPatientMapperDto : $scope.saveAllergyList
										
								}
								
								if($scope.roleId == 3)
								{
									data.listOfAllergyPatientMapperDto=$scope.saveAllergyList;
									
								}
							else 
								{
								console.log('ds',$scope.allergyCurrentListDoctor);
									//angular.extend($scope.dataListComplaints,$scope.complaintsPatientListDoctor);
								angular.forEach($scope.saveAllergyList, function(value , key) {
										if(value.allergyTypeId > 0){
							 				var dData = {
							 						isNoKnownAllergies:value.isNoKnownAllergies,
										    	 	allergyTypeId:value.allergyTypeId,
										    	 	allergyId:value.allergyId,
										    	 	month:value.month,
										    	 	year:value.year,
										    	 	type:value.type,
										    	 	natureOfReaction:value.natureOfReaction,
										    	 	severityId:value.severityId,
										    	 	remark:value.remark,
										    	 	comments:value.comments,
										    	 	isEnterInErrorStatus:value.isEnterInErrorStatus,
													isReviewedFlag:value.isReviewedFlag,
							 				}
							 				$scope.allergyCurrentListDoctor.push(dData);
								}
							 
                         		});
									data.listOfAllergyPatientMapperDto = $scope.allergyCurrentListDoctor;
								}
								
							
								//console.log($scope.patientAllergyObject);
								var paramObj = {};
								var headers={
										'organization_id': $scope.organizationId,
										'unit_id':$scope.unitId,
										'Content-Type':'application/json'
								};
								console.log(data);
								//return false;
								var URI = BASE_URL + ROOT_URL
								+ SAVEALLERGYPATIENTMAPPER;
						
								GenericServiceParamHeader
								.serviceAction(METHOD_POST, URI,
										data,
										NOTIFICATION_MSG_STATUS_TRUE,paramObj,headers)
								.then(
										function(response) {
											console.log(response);
											// $rootScope.startLoader();
											if (response.data.status == "success")
												$scope.init();
												return response.data.listObject;
										});
								
								
							}
							
							$scope.inActiveAllergy = function(item)
							{
								console.log(item);
								var paramObj = {
										allergy_id:item.allergyId,
										allergy_type_id:item.allergyTypeId,
										patient_id:$scope.encounterDetailsObj.patientId
										//patient_id:1
								};
								var headers={
								};
								//return false;
								var URI = BASE_URL + ROOT_URL
								+ CANCELALLERGYPATIENTMAPPER;
						
								GenericServiceParamHeader
								.serviceAction(METHOD_GET, URI,
										headers,
										NOTIFICATION_MSG_STATUS_TRUE,paramObj,headers)
								.then(
										function(response) {
											console.log(response);
											// $rootScope.startLoader();
											if (response.data.status == "success")
												$scope.init();
												
										});
								
							}
							
							
							$scope.showUpdateAllergryHistory = function(item)
							{
								$scope.allergyButtonFlag = false;
								console.log(item);
								$scope.patientAllergyObject = {
										
										isNoKnownAllergies:item.isNoKnownAllergies,
							    	 	allergyTypeId:item.allergyTypeId.toString(),
							    	 	allergyId:item.allergyId.toString(),
							    	 	month:item.month.toString(),
							    	 	year:item.year,
							    	 	type:item.type,
							    	 	natureOfReaction:item.natureOfReaction,
							    	 	severityId:item.severityId.toString(),
							    	 	remark:item.remark,
							    	 	isEnterInErrorStatus:item.isEnterInErrorStatus,
										isReviewedFlag:item.isReviewedFlag,
										allergyPatientMapperId : item.allergyPatientMapperId
								};
								$scope.itemRoleId = item.roleId;
								
								$scope.getAllergenListByAllergyId(item.allergyTypeId);
								
							}
							
							
							$scope.updateAllergyHistory = function()
							{
								$scope.saveAllergyList = [];
								$scope.saveAllergyList.push($scope.patientAllergyObject);
										var data = {
										createdDate:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updatedDate:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updatedBy:$scope.userId,
										createdBy:$scope.userId,
										status:'A',
										patientId : $scope.encounterDetailsObj.patientId,
										roleId : $scope.roleId,
										encounterId: $scope.encounterDetailsObj.encounterId,
										comments:'',
										listOfAllergyPatientMapperDto:$scope.saveAllergyList
										
								};
								
								console.log(data);
								//return false;
								var paramObj = {};
								var headers={
										'organization_id': $scope.organizationId,
										'unit_id':$scope.unitId,
										'Content-Type':'application/json'
								};
								//return false;
								var URI = BASE_URL + ROOT_URL
								+ UPDATEALLERGYPATIENTMAPPER;
						
								GenericServiceParamHeader
								.serviceAction(METHOD_POST, URI,
										data,
										NOTIFICATION_MSG_STATUS_TRUE,paramObj,headers)
								.then(
										function(response) {
											console.log(response);
											// $rootScope.startLoader();
											if (response.data.status == "success")
												$scope.init();
												return response.data.listObject;
										});
							}
							
							$scope.updateStatusForAcronymsAllergy = function(item)
							{
								$scope.saveAllergyList = [];
								$scope.patientAllergyObject = {
										
										isNoKnownAllergies:item.isNoKnownAllergies,
							    	 	allergyTypeId:item.allergyTypeId,
							    	 	allergyId:item.allergyId,
							    	 	month:item.month,
							    	 	year:item.year,
							    	 	type:item.type,
							    	 	natureOfReaction:item.natureOfReaction,
							    	 	severityId:item.severityId,
							    	 	remark:item.remark,
							    	 	isEnterInErrorStatus:item.isEnterInErrorStatus,
										isReviewedFlag:item.isReviewedFlag,
										allergyPatientMapperId : item.allergyPatientMapperId
								};
								var paramObj = {};
								
								$scope.saveAllergyList.push($scope.patientAllergyObject);
								var headers={
										'organization_id': $scope.organizationId,
										'unit_id':$scope.unitId,
										'Content-Type':'application/json'
								};
								
										var data = {
										
										createdDate:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updatedDate:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updatedBy:$scope.userId,
										createdBy:$scope.userId,
										status:'A',
										encounterId : $scope.encounterDetailsObj.encounterId,
										patientId : $scope.encounterDetailsObj.patientId,
										roleId : $scope.roleId,
										comments:'',
										listOfAllergyPatientMapperDto:$scope.saveAllergyList
										}
								
										
										console.log(data);
										//return false;
										var URI = BASE_URL + ROOT_URL
										+ UPDATEENTERERRORSTATUSALLERGYPATIENTMAPPER;
								
										GenericServiceParamHeader
										.serviceAction(METHOD_POST, URI,
												data,
												NOTIFICATION_MSG_STATUS_TRUE,paramObj,headers)
										.then(
												function(response) {
													console.log(response);
													// $rootScope.startLoader();
													if (response.data.status == "success")
														$scope.init();
														return response.data.listObject;
												});
							}
							
							
							//END SAVE ALLEGRY LIST
							
							//START SAVE VITALS
							$scope.savePatientVitals = function()
							{
								console.log($scope.vitalFinalList);
								
								var paramObj = {};
								var headers={
										'organization_id': $scope.organizationId,
										'unit_id':$scope.unitId,
										'Content-Type':'application/json'
								};
								
								$scope.savePatientVitalFinalArr = [];
								  angular.forEach($scope.vitalFinalList, function(value, key) {
									  
									  var data = {
											  vitalId:value.vitalId,
											  value:value.vitalValue,
											  isReviewedFlag:value.isReviewedFlag,
									  }
									  if($scope.roleId == 2)
                      		 		{
                          		 		data.appointmentVitalMapperId =value.appointmentVitalMapperId;
                      		 		}
									  
									  $scope.savePatientVitalFinalArr.push(data);
                                });
								  var data = {
										 patientId : $scope.encounterDetailsObj.patientId,
                             		 	encounterId : $scope.encounterDetailsObj.encounterId,
                             		 	roleId : $scope.roleId,
                             		 	createdDate:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updatedDate:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updatedBy:$scope.userId,
										createdBy:$scope.userId,
										status:'A',
										listAppointmentVitalMapperDtos : $scope.savePatientVitalFinalArr
								  }
								  console.log("Final Vital Save Arr",data);
								 //return false;
								  var URI = BASE_URL + ROOT_URL
									+ SAVEAPPOINTMENTVITALMAPPER;
							
									GenericServiceParamHeader
									.serviceAction(METHOD_POST, URI,
											data,
											NOTIFICATION_MSG_STATUS_TRUE,paramObj,headers)
									.then(
											function(response) {
												console.log(response);
												// $rootScope.startLoader();
												if (response.data.status == "success")
													$scope.init();
													return response.data.listObject;
											});
									
									
								
								
							}
							//END SAVE VITALS
							/*$scope.saveGeneralExamination = function()
							{
								console.log($scope.examinationGeneralExaminationList);
							}*/
						
							$scope.checkedRadio = function(idx,val)
							{
								//alert(idx);
								if(val =='Day')
									{
									$('#dayRadio_'+idx).prop('checked', true);
									}
								else if(val == 'Month')
									{
									$('#monthRadio_'+idx).prop('checked', true);
									}
								else
									{
									$('#yearRadio_'+idx).prop('checked', true);
									}
								
							}
							
							$scope.checkedRadioMedi =function(idx,val)
							{
								//alert(idx);
								if(val =='Day')
									{
									$('#dayRadioMedi_'+idx).prop('checked', true);
									}
								else if(val == 'Month')
									{
									$('#monthRadioMedi_'+idx).prop('checked', true);
									}
								else
									{
									$('#yearRadioMedi_'+idx).prop('checked', true);
									}
								
							}
							
							$scope.commonFuntion = function(templateTypeId) {
								var templeteData  = {	
								};
								
								var URI = BASE_URL + ROOT_URL+LIS_UNIT
								+ GET_TEMPLATE+"/"+$scope.organizationId+"/"+$scope.unitId+"/0/"+templateTypeId;
								GenericService
								.serviceAction("GET", URI, templeteData)
								.then(
										function(response) {
											$scope.templateList = [];

											if (response.data.status == "success") {
												$scope.templateList = response.data.listObject;
												//$scope.value =$scope.templateList[0].html;
												
												/*$scope.templateObj = {
														templetText:$scope.templateList[0].html,
														templeId  : $scope.templateList[0].labTemplateId,
														organizationId : $scope.organizationId,
														unitId : $scope.unitId,
														admissionId : $scope.admissionId,
														typeId  : templateTypeId
												}
												
												console.log("Templete For Dama",$scope.templateList);*/
												
												CKEDITOR.addTemplates("default", {
												    imagesPath:CKEDITOR.getUrl(CKEDITOR.plugins.getPath("templates")+"templates/images/"),
												    templates: $scope.templateList
												   });
												
											}
										});

							}
							
							$scope.saveTemplate = function()
							{
								console.log('Final Object Template',$scope.value);
								
								 var popupWinindow = window.open('', '_blank', 'width=600,height=700,scrollbars=no,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
							        popupWinindow.document.open();
							        popupWinindow.document.write('<html><head><link rel="stylesheet" type="text/css" href="style.css" /></head><body onload="window.print()">'+$scope.value+'</html>');
							        popupWinindow.document.close();
							}
							
							  
	                        $scope.showLetterTemplates = function(htmlVal)
	                        {
	                        	console.log("htmlView",htmlVal);
	                        	$scope.value = htmlVal;
	                        }
	                        
	                        
	                        $scope.saveGeneralExamination = function(typeId)
	                        {
	                        	console.log("array Gene",$scope.patientSystemFinalArray);
								var paramObj = {};
								var headers={
										'organization_id': $scope.organizationId,
										'unit_id':$scope.unitId,
										'Content-Type':'application/json'
								};
								
								$scope.listOfListOfGeneralExamSystemMaster = [];
								if(typeId == 1){
								angular.forEach($scope.patientSystemFinalArray, function(value,index) 
										{
											if(value.isCheckedSys == 'Y')
											{
												var parentObj = {};
												parentObj.systemId = value.systemId;
												parentObj.isNADValue = value.isNADValue;
										         if(value.isNADValue == 'Y')
										        	 {
										        	 	parentObj.listOfGeneralSystemObservation = [];
										        	 }
										         else
										        	 {
										        	 parentObj.listOfGeneralSystemObservation = [];
										        	 angular.forEach(value.systemObervationList, function(value1,index1) 
																{
										        		 			//var 
										        		 		if(value1.isCheckedOber == 'Y')
										        		 			{
										        		 				var childObservationObj = {};
										        		 				childObservationObj.systemObervationId = value1.observationId; 
											        		 			if(value1.isPropertyRequired == 'N')
											        		 				{
											        		 					childObservationObj.remark = value1.remark;
											        		 					childObservationObj.listOfGeneralSystemProperty = [];
											        		 				}
											        		 			else
											        		 				{
											        		 					childObservationObj.remark = '';
											        		 					childObservationObj.listOfGeneralSystemProperty = [];
											        		 					angular.forEach(value1.systemObservationPropertList, function(value2,index2) 
																						{
											        		 					 			var subChildObservationPropertyObj = {};
														        		 					if(value2.isCheckedOberPro == true)
																        		 			{
														        		 						subChildObservationPropertyObj.systemObervationPropertyId = value2.propertyId;
														        		 						subChildObservationPropertyObj.remark = value2.remark;
														        		 						childObservationObj.listOfGeneralSystemProperty.push(subChildObservationPropertyObj);
																        		 			}
																						});
											        		 				
											        		 				}
											        		 			parentObj.listOfGeneralSystemObservation.push(childObservationObj);
										        		 			}
										        		 		
																});
										        	 }
										         $scope.listOfListOfGeneralExamSystemMaster.push(parentObj);
											}
										});
	                        }
								if(typeId == 2){
									angular.forEach($scope.patientPhysicalSystemFinalArray, function(value,index) 
											{
												if(value.isCheckedSys == 'Y')
												{
													var parentObj = {};
													parentObj.systemId = value.systemId;
													parentObj.isNADValue = value.isNADValue;
											         if(value.isNADValue == 'Y')
											        	 {
											        	 	parentObj.listOfGeneralSystemObservation = [];
											        	 }
											         else
											        	 {
											        	 parentObj.listOfGeneralSystemObservation = [];
											        	 angular.forEach(value.systemObervationList, function(value1,index1) 
																	{
											        		 			//var 
											        		 		if(value1.isCheckedOber == 'Y')
											        		 			{
											        		 				var childObservationObj = {};
											        		 				childObservationObj.systemObervationId = value1.observationId; 
												        		 			if(value1.isPropertyRequired == 'N')
												        		 				{
												        		 					childObservationObj.remark = value1.remark;
												        		 					childObservationObj.listOfGeneralSystemProperty = [];
												        		 				}
												        		 			else
												        		 				{
												        		 					childObservationObj.remark = '';
												        		 					childObservationObj.listOfGeneralSystemProperty = [];
												        		 					angular.forEach(value1.systemObservationPropertList, function(value2,index2) 
																							{
												        		 					 			var subChildObservationPropertyObj = {};
															        		 					if(value2.isCheckedOberPro == true)
																	        		 			{
															        		 						subChildObservationPropertyObj.systemObervationPropertyId = value2.propertyId;
															        		 						subChildObservationPropertyObj.remark = value2.remark;
															        		 						childObservationObj.listOfGeneralSystemProperty.push(subChildObservationPropertyObj);
																	        		 			}
																							});
												        		 				
												        		 				}
												        		 			parentObj.listOfGeneralSystemObservation.push(childObservationObj);
											        		 			}
											        		 		
																	});
											        	 }
											         $scope.listOfListOfGeneralExamSystemMaster.push(parentObj);
												}
											});
		                        }
								  var data = {
										patientId : $scope.encounterDetailsObj.patientId,
                             		 	encounterId : $scope.encounterDetailsObj.encounterId,
                             		 	createdDate:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updatedDate:moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
										updatedBy:$scope.userId,
										createdBy:$scope.userId,
										status:'A',
										typeId:typeId,
										listOfListOfGeneralExamSystemMaster : $scope.listOfListOfGeneralExamSystemMaster
								  }
								  console.log("Final General Examination Save Arr",data);
								  //return false;
								  var URI = BASE_URL + ROOT_URL
									+ SAVEGENERALEXAMINATIONAPPOMAPPER;
								  //console.log("URI",URI);
								  //return false;
									GenericServiceParamHeader
									.serviceAction(METHOD_POST, URI,
											data,
											NOTIFICATION_MSG_STATUS_TRUE,paramObj,headers)
									.then(
											function(response) {
												console.log(response);
												// $rootScope.startLoader();
												if (response.data.status == "success")
													$scope.init();
													return response.data.listObject;
											});
	                        	
	                        	/*angular.forEach($scope.patientSystemFinalArray, function(value,index) {
	                        		
								});*/
	                        	
	                        }
							
	                        $scope.getRadioButtonChecked = function(pIdx,cIdx,sCIdx)
	                        {
	                        	
	                        	//console.log("Testing",$scope.patientSystemFinalArray[pIdx].systemObervationList[cIdx].systemObservationPropertList[sCIdx]);
	                        	//return false;
	                        	//var i = 0;
	                        	angular.forEach($scope.patientSystemFinalArray[pIdx].systemObervationList[cIdx].systemObservationPropertList, function(value,index) {
	                        		console.log(value.propertyId);
									if (sCIdx == index) {
										value.isCheckedOberPro = true;
									}
									else
										{
										value.isCheckedOberPro = false;
										}
									//i++;
								});
	                        }
							
							/******* START TAB FOR ALL PAGES IN TREE LEVEL *******/
							/*First Level*/
							$scope.firstTabLevel = 10;

							$scope.setFirstTabLevel = function(newTab) {
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
							
							/*
							 //on View  ng-change="markEdited('C')" 
							  $scope.markEdited = function(which) {
							    $scope.edited = which;
							  };
							 $scope.$watch('examinationVitalsObject.temperatureValue', function(value) {
								    if($scope.edited == 'C') {
								      console.log(value+'F -> C');
								      $scope.fahrenheit = value * 9.0 / 5.0 + 32;
								    }
								  });*/
							 
							
							
							/********************************************	STARTED JYOTI KADAM
							DATE:- 21-05-2018
							OPD COVER SHEET FUNCTIONALITY ********************************************************************************/
		
		
		/********************* START COMPLETE CONSULTATION ************************/
		
		$scope.completeConsultation=function(roleId){
			//alert("in");
			var cookieWObject = $cookies.getObject('cookieEncounterObject');
			//alert(cookieWObject.encounterId);
			var headerObj={
					'organization_id': $scope.organizationId,
					'unit_id':$scope.unitId,
					'Content-Type':'application/json'
			};
			
			if(roleId == 3)
			{
				var data = {
						'assignmentStartTime':"",
						'assignmentStopTime':moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
						'isAssignmentCompStatus':'Y',
						'assignmentStartBy':"",
						'assignmentStopBy': $scope.userId,
						'assignmentStartRoleId': "",
						'assignmentStopRoleId': roleId,
						'isConsultationStatus': "N",
						'consultationStartTime': "",
						'consultationStopTime': "",
						'consultationStartBy': "",
						'consultationStopBy': "",
						'consultationStartRoleId': "",
						'consultationStopRoleId': "",
						'encounterId': cookieWObject.encounterId,
						"patientId": cookieWObject.patientId
					}
			}
			else
				{
				
				var data = {
						'assignmentStartTime':"",
						'assignmentStopTime':"",
						'isAssignmentCompStatus':'Y',
						'assignmentStartBy':"",
						'assignmentStopBy': "",
						'assignmentStartRoleId': "",
						'assignmentStopRoleId': "",
						'isConsultationStatus': "Y",
						'consultationStartTime': moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
						'consultationStopTime': moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
						'consultationStartBy': $scope.userId,
						'consultationStopBy': $scope.userId,
						'consultationStartRoleId':roleId,
						'consultationStopRoleId': roleId,
						'encounterId': cookieWObject.encounterId,
						"patientId": cookieWObject.patientId
					}
				
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
								// $rootScope.startLoader();
								if (response.data.status == "success")
									//alert("success");
								$state.go("encounterQueueManagement");
							});
		}
		
		
		/*********************END COMPLETE CONSULTATION ************************/
		
		/******************* START COMMON DATA FOR API ****************************/
		
		var commonObj = $cookies.getObject('cookieEncounterObject');
		console.log("old encounter==="+commonObj.patientId);
		var paramObjForPer = {
				
				'patientId':commonObj.patientId
		};

		 var headerObj = {
	    		 unit_id : $scope.unitId,
					organization_id : $scope.organizationId
	     };
		
		 var data = {};
		 /******************* END COMMON DATA FOR API ****************************/
		
		
		/*********************START GET OLD ENCOUNTER LIST ************************/
		
					var URI = BASE_URL + ROOT_URL+ GETOLDENCOUNTERLIST;
					GenericServiceParamHeader
						.serviceAction(METHOD_GET, URI,
								data,
								NOTIFICATION_MSG_STATUS_FALSE,paramObjForPer,headerObj)
						.then(
								function(response) {
									console.log("old encounter==="+response);
									// $rootScope.startLoader();
									if (response.data.status == "success")
									$scope.oldEncounterList = response.data.listObject;
								});
		
	/*********************END GET OLD ENCOUNTER LIST ************************/
	
	/******************* START COMMON DATA FOR API ****************************//*
	
	var commonObj = $cookies.getObject('cookieEncounterObject');
	console.log("old encounter==="+commonObj.patientId);
	var paramObjForPer = {
			
			'patientId':commonObj.patientId
	};

	 var headerObj = {
    		 unit_id : $scope.unitId,
				organization_id : $scope.organizationId
     };
	
	 var data = {};
	 *//******************* END COMMON DATA FOR API ****************************//*

	*//**********************START VITAL LIST BY PATIENT **********************//*
		  
		var URI = BASE_URL + ROOT_URL
            + GETAPPOINTMENTVITALMAPPERBYPATIENTIDANDAPPOINTMENTID;
        GenericServiceParamHeader
            .serviceAction(METHOD_GET, URI,
                    data,
                    NOTIFICATION_MSG_STATUS_FALSE,paramObjForPer,headerObj)
            .then(
                    function(response) {
                        console.log(response);
                        // $rootScope.startLoader();
                        if (response.data.status == "success")
                            $scope.vitalsListByPatientId = response.data.listObject;
                        console.log("Vital List", $scope.vitalsListByPatientId);
                    });
        *//*******************END VITAL LIST BY PATIENT *******************/
        
        /******************* START COMMON DATA FOR API ****************************/
		
		var commonObj = $cookies.getObject('cookieEncounterObject');
		console.log("old encounter==="+commonObj.patientId);
		
		 var headerObj = {
	    		 unit_id : $scope.unitId,
					organization_id : $scope.organizationId
	     };
		
		 var data = {};
		 /******************* END COMMON DATA FOR API ****************************/
        
        
        /**********************START SAVED GENERALEXAM BY PATIENT **********************/
		  
        var paramObjForGen = {
				
				'patientId':commonObj.patientId,
				'typeId':1,
				//'genderId':commonObj.genderId
		};
        
		var URI = BASE_URL + ROOT_URL
            + GETGENERALEXAMLIST;
        GenericServiceParamHeader
            .serviceAction(METHOD_GET, URI,
                    data,
                    NOTIFICATION_MSG_STATUS_FALSE,paramObjForGen,headerObj)
            .then(
                    function(response) {
                        console.log(response);
                        // $rootScope.startLoader();
                        if (response.data.status == "success")
                            $scope.getSavedGeneralExamList = response.data.listObject;
                        console.log("General Exam List", $scope.getSavedGeneralExamList);
                    });
        /*******************START SAVED GENERALEXAM BY PATIENT*******************/
		
        /**********************START SAVEDPHYSICAL EXAM BY PATIENT **********************/
		  
        var paramObjForGen = {
				
				'patientId':commonObj.patientId,
				'typeId':2,
				//'genderId':commonObj.genderId
		};
        
		var URI = BASE_URL + ROOT_URL
            + GETGENERALEXAMLIST;
        GenericServiceParamHeader
            .serviceAction(METHOD_GET, URI,
                    data,
                    NOTIFICATION_MSG_STATUS_FALSE,paramObjForGen,headerObj)
            .then(
                    function(response) {
                        console.log(response);
                        // $rootScope.startLoader();
                        if (response.data.status == "success")
                            $scope.getSavedPhysicalExamList = response.data.listObject;
                        console.log("physical Exam List", $scope.getSavedPhysicalExamList);
                    });
        /*******************START SAVED SAVEDPHYSICAL BY PATIENT*******************/
							


						} ]);