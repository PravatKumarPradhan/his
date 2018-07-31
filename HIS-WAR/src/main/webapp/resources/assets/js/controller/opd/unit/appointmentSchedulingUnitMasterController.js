'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:appointmentSchedulingUnitMasterController
 * @description #appointmentSchedulingUnitMasterController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'appointmentSchedulingUnitMasterController',
				[
						'$scope',
						'$http',
						'$localStorage',
						'$sessionStorage',
						'$cookies',
						'$rootScope',
						'GenericService',
						'BillingGenericService',
						'growl',
						'$state',
						'$stateParams',
						'PagerService',
						function($scope, $http, $localStorage, $sessionStorage,
								$cookies, $rootScope, GenericService,
								BillingGenericService, growl, $state,
								$stateParams,PagerService) {

							/* init() function for form object create. */
							$scope.init = function() {
								
								
								/*var a = "12:30";
								var b = "01:00";
								alert(parseInt(a) + parseInt(b) );*/
								
								$scope.dataList = [ {
									fromDate : '',
									toDate : '',
									fromTime : '',
									toTime : '',
									timePerSlot : '',
									visitTypeId :1,
									noOfSlots:0,
									example8model:[],
									breakTimeList:[{breakFromTime:'',breakToTime:''}]
								} ];
								
								
								//Loop DatePicker Start
								// for datePicker
								$scope.dateOptions = {
									formatYear : 'yyyy',
									showWeeks : false
								};
								
								//for From Date
								$scope.openDatePicker = function(index) {
									for (var i = $scope.dataList.length - 1; i >= 0; i--) {
										if (i === index) {

											$scope.fromDate.opened[i] = true;
										} else {
											$scope.fromDate.opened[i] = false;
										}
									}
								};

								$scope.fromDate = {
									opened : [ false ],

								};
								//End From Date
								
								//for To Date
								$scope.openDatePickerTo = function(index) {
									for (var i = $scope.dataList.length - 1; i >= 0; i--) {
										if (i === index) {

											$scope.toDate.opened[i] = true;
										} else {
											$scope.toDate.opened[i] = false;
										}
									}
								};

								$scope.toDate = {
									opened : [ false ],

								};
								//End To Date
								
								
								/** Variables for pagination */
								$scope.offset = 0;
								$scope.noOfRecordsPerPage = 10;
								$scope.totalNoOfRecords;
								$scope.searchKeyword;
								$scope.commonListCount;
								
								
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
								
								
								$scope.slotTypeId  = 1;

								var commonObj = {
									unitId : $scope.unitId,
									organizationId : $scope.organizationId
								};
								var data = {};

								
							
								 //For Speciality
								 $scope.example14model = [];
								    $scope.example14settings = {
								        scrollableHeight: '200px',
								        scrollable: true,
								        enableSearch: true
								    };
								   /* $scope.example14data = [{
								        "label": "Cardiology",
								            "id": "1"
								    }, {
								        "label": "Dental",
								            "id": "2"
								    }, {
								        "label": "Radiology",
								            "id": "3"
								    }];*/
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
																{
																$scope.example14data = [];
																angular.forEach(response.data.listObject,function(value,index) {
																			var data = {};
																			data.id=value.specialityId;
																			data.label=value.specialityName;
																			$scope.example14data.push(data);
																		
																		});
																	 
																}
																
														});
										
										
										 //For Speciality under modality
										 $scope.example14ModalityType = [];
										 $scope.example14SubSpeciality=[];
										   
										   
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
																		{
																		$scope.example14ModalityTypeData = [];
																		angular.forEach(response.data.listObject,function(value,index) {
																					var data = {};
																					data.id=value.specialityId;
																					data.label=value.specialityName;
																					$scope.example14ModalityTypeData.push(data);
																				
																				});
																			 
																		}
																		
																});
										
										
										
										
								    $scope.example2settings = {
								        displayProp: 'id'
								    };
								    
								    //For Doctor
								    $scope.example14modelDoctor = [];
								    $scope.example2settingsDoctor = {
								        displayProp: 'id'
								    };
								    
								    //For Modality
								    $scope.example14modelModality = [];
								    $scope.example2settingsModality = {
								        displayProp: 'id'
								    };
								    
								
								//Testing pouspose End
								
							}

							$scope.init();
							
							
							
							// For Paginations Start
							$scope.setNoOfRecords = function() {
								$scope.initDoctorSlotList(
										$scope.organizationId, $scope.offset,
										$scope.noOfRecordsPerPage);
							};
							$scope.initDoctorSlotList = function(orgId,
									offset, noOfRecordsPerPage) {
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
										: 10;
								var URI = BASE_URL + ROOT_URL
										+ GETALLSLOTS;
								var URI1 = BASE_URL + ROOT_URL
										+ GETALLSLOTSCOUNT;

								var data = {
									organizationId : orgId,
									unitId : $scope.unitId,
									slotTypeId : $scope.slotTypeId,
									offset : offset,
									noOfRecordsPerPage : noOfRecordsPerPage
								}

								var data1 = {
									organizationId : $scope.organizationId,
									unitId : $scope.unitId,
									slotTypeId : $scope.slotTypeId,
								}

								// For Get Nationality List Using Offset
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.doctorSlotList = [];

													if (response.data.status == "success") {
														$scope.doctorSlotList = response.data.listObject;

													}
												});

								// For Count Slot List
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

							$scope.getDoctorSlotList = function(orgId,
									offset, noOfRecordsPerPage) {
								console.log("offset", offset);
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
										: 10;
								var URI = BASE_URL + ROOT_URL
										+ GETALLSLOTS;
								var data = {
									organizationId : $scope.organizationId,
									unitId:$scope.unitId,
									slotTypeId : $scope.slotTypeId,
									offset : offset,
									noOfRecordsPerPage : noOfRecordsPerPage
								}

								console.log("URI", URI);
								// For Get Slot List Using Offset
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.doctorSlotList = [];

													if (response.data.status == "success") {
														$scope.doctorSlotList = response.data.listObject;

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
									$scope.getDoctorSlotList(
											$scope.organizationId,
											$scope.pager.startIndex,
											$scope.pager.pageSize);
								}
							}

							$scope.initDoctorSlotList(
									$scope.organizationId, $scope.offset,
									$scope.noOfRecordsPerPage);

							// For Paginations End
							
							
							 /******* START TAB FOR ALL PAGES IN TREE LEVEL *******/
				        	/*First Level*/
					    	$scope.firstTabLevel = 1 ;
							
							$scope.setFirstTabLevel= function(newTab)
							{
								if (newTab == 1) {
									$("#case-sheet").css("background-color",
											"#5F9EA0");
									$("#complaints").css("background-color",
											"#5ab7c8");
									$("#History").css("background-color",
											"#5ab7c8");
									$scope.slotTypeId = 1;
									
								} else if (newTab == 2) {
									$("#case-sheet").css("background-color",
											"#5ab7c8");
									$("#complaints").css("background-color",
											"#5F9EA0");
									$("#History").css("background-color",
											"#5ab7c8");
									$scope.slotTypeId = 2;
								} else {
									$("#case-sheet").css("background-color",
											"#5ab7c8");
									$("#complaints").css("background-color",
											"#5ab7c8");
									$("#History").css("background-color",
											"#5F9EA0");
									$scope.slotTypeId = 3;
								}
								
								$scope.firstTabLevel = newTab;
								
								$scope.initDoctorSlotList(
										$scope.organizationId, $scope.offset,
										$scope.noOfRecordsPerPage);
								
							};
							
							$scope.isSetFirstTabLevel = function(tabNum)
							{
								return $scope.firstTabLevel === tabNum;
							};
							
							/*Second Level
							$scope.secondTabLevel = 1 ;
							
							$scope.setSecondTabLevel = function(newTab)
							{
								$scope.secondTabLevel = newTab;
							};
							
							$scope.isSetSecondTabLevel = function(tabNum)
							{
								return $scope.secondTabLevel === tabNum;
							};
							
							
							Third Level
							$scope.thirdTabLevel = 1 ;
							
							$scope.setThirdTabLevel = function(newTab)
							{
								$scope.thirdTabLevel = newTab;
							};
							
							$scope.isSetThirdTabLevel = function(tabNum)
							{
								return $scope.thirdTabLevel === tabNum;
							};
							
							
							
							FORTH Level
							$scope.forthTabLevel = 1 ;
							
							$scope.setForthTabLevel = function(newTab)
							{
								$scope.forthTabLevel = newTab;
							};
							
							$scope.isSetForthTabLevel = function(tabNum)
							{
								return $scope.forthTabLevel === tabNum;
							};*/
						/******* END TAB FOR ALL PAGES IN TREE LEVEL *******/
							
							
							
							 /* $scope.myEvents = {
									  onItemSelect: function(item) {
					                            console.log(item);
					                         },
					                     onItemDeselect: function(item) {
					                              console.log(item);
					                        },
					                        onSelectionChanged: function(item) {
					                        	alert("sdf");
					                              console.log($scope.example14model);
					                         }
					             };*/
							  
							  $scope.clicked = function(type)
							  {
	                              if(type=='doctor'){
								  if($scope.example14model.length > 0){
	                              var speciality = [];
	                              angular.forEach($scope.example14model,function(value,index) {
	                            	  speciality.push(value.id);
									
									});
	                              var data = {
	                            		  organizationId:$scope.organizationId,
	                            		  unitId:$scope.unitId,
	                            		  specialityArray:speciality
	                              }
	                              console.log(data);
	                              var URI = BASE_URL + ROOT_URL
									+ GETDOCTORSLISTAGANISTSPECIALITIES;
							BillingGenericService
									.serviceAction(METHOD_POST, URI,
											data,
											NOTIFICATION_MSG_STATUS_FALSE)
									.then(
											function(response) {
												console.log(response);
												// $rootScope.startLoader();
												if (response.data.status == "success")
													{
													$scope.example14dataDoctor = [];
													angular.forEach(response.data.listObject,function(value,index) {
																var data = {};
																data.id=value.doctorId+'_'+value.doctorSpecialityId;
																data.label=value.firstName+' '+value.lastName;
																$scope.example14dataDoctor.push(data);
															
															});
																											 
													}
													
											});
							  }
	                          else
	                            {
	                        	  
	                            	  $scope.example14dataDoctor = [];
	                            }
								  
	                         }
	                              else if(type=='speciality'){
	                            	  if($scope.example14ModalityType.length > 0){
	    	                              var modalityType = [];
	    	                              angular.forEach($scope.example14ModalityType,function(value,index) {
	    	                            	  modalityType.push(value.id);
	    									
	    									});
	    	                              var data = {
	    	                            		  organizationId:$scope.organizationId,
	    	                            		  unitId:$scope.unitId,
	    	                            		  specialityIdList:modalityType
	    	                              }
	    	                              console.log(data);
	    	                              var URI = BASE_URL + ROOT_URL
	    									+ GETSUBSPECIALITYBYSPECIALITYARRAY;
	    							BillingGenericService
	    									.serviceAction(METHOD_POST, URI,
	    											data,
	    											NOTIFICATION_MSG_STATUS_FALSE)
	    									.then(
	    											function(response) {
	    												console.log(response);
	    												// $rootScope.startLoader();
	    												if (response.data.status == "success")
	    													{
	    													$scope.example14dataSubSpeciality = [];
	    													angular.forEach(response.data.listObject,function(value,index) {
	    																var data = {};
	    																//alert(value.modalityDesc);
	    																data.id=value.subSpecialityId;
	    																data.label=value.subSpecialityName;
	    																$scope.example14dataSubSpeciality.push(data);
	    															
	    															});
	    																											 
	    													}
	    													
	    											});
	    							  }
	    	                          else
	    	                            {
	    	                        	  
	    	                            	  $scope.example14dataModality = [];
	    	                            }
	                              }
	                              else
	                            	  {

	                            	  if($scope.example14SubSpeciality.length > 0){
	    	                              var subSpecialityType = [];
	    	                              angular.forEach($scope.example14SubSpeciality,function(value,index) {
	    	                            	  subSpecialityType.push(value.id);
	    									
	    									});
	    	                              var data = {
	    	                            		  organizationId:$scope.organizationId,
	    	                            		  unitId:$scope.unitId,
	    	                            		 subSpecialityArray:subSpecialityType
	    	                              }
	    	                              console.log(data);
	    	                              var URI = BASE_URL + ROOT_URL
	    									+ GETMODALITYBYSUBSPECIALITYARRAY
	    							BillingGenericService
	    									.serviceAction(METHOD_POST, URI,
	    											data,
	    											NOTIFICATION_MSG_STATUS_FALSE)
	    									.then(
	    											function(response) {
	    												console.log(response);
	    												// $rootScope.startLoader();
	    												if (response.data.status == "success")
	    													{
	    													$scope.example14dataModality = [];
	    													angular.forEach(response.data.listObject,function(value,index) {
	    																var data = {};
	    																//alert(value.modalityDesc);
	    																data.id=value.modalityId+'_'+value.subSpecialityId+'_'+value.specialityId;
	    																data.label=value.modalityDesc;
	    																$scope.example14dataModality.push(data);
	    															
	    															});
	    																											 
	    													}
	    													
	    											});
	    							  }
	    	                          else
	    	                            {
	    	                        	  
	    	                            	  $scope.example14dataModality = [];
	    	                            }
	                            	  }
							  }
							  
							  $scope.add = function() {
									var data = {
											fromDate : '',
											toDate : '',
											fromTime : '',
											toTime : '',
											timePerSlot : '',
											visitTypeId :1,
											example8model:[],
											breakTimeList:[{breakFromTime:'',breakToTime:''}]
									};
								
									$scope.dataList.push(data);
								};

								$scope.remove = function(index) {
									var newDataList = [];
									var le = $scope.dataList.length;
									var i = 1;
									if(le != i){
									angular.forEach($scope.dataList, function(v) {
										if (index != i) {
											newDataList.push(v);
										}
										i++;
									});
									
									$scope.dataList = newDataList;
									}
								};
								

								// function for initializa clockpicker
								$scope.initClockPicker = function() {

									$('.clockpicker').clockpicker();
								}

							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								if($scope.slotTypeId == '1')
									{
									$scope.saveSlots();
									}
								else if($scope.slotTypeId == '2')
									{
										$scope.saveModalitySlots();
									}
								else
									{
										$scope.saveEchSlots();
									}
								
							}
							
							
							//Save Doctor Slots
							$scope.saveSlots  = function()
							{
								console.log($scope.dataList);
								//return false;
								if ($scope.popUpFlag) {
									$scope.showModal = !$scope.showModal;

								} else {
									$scope.currentDate = new Date();
									$scope.currentDate = moment($scope.currentDate)
											.format('DD-MM-YYYY HH:mm:ss');
									
									var doctorSpecialityList = [];
									 angular.forEach($scope.example14dataDoctor,function(value,index) {
		                            	  var sd = {};
		                            	  var splittedStringArray = value.id.split("_");
		                            	  sd.doctorId =splittedStringArray[0]; 
		                            	  sd.specialityId =splittedStringArray[1];
		                            	  doctorSpecialityList.push(sd);
										});
									 
									 
									 var slotList = [];
									 angular.forEach($scope.dataList,function(value,index) {
		                            	  var slotObj = {};
										  slotObj.fromDate =  moment(value.fromDate).format('YYYY-MM-DD');	
		                            	  slotObj.toDate =  moment(value.toDate).format('YYYY-MM-DD');
		                            	  slotObj.fromTime =  value.fromTime;	
		                            	  slotObj.toTime =  value.toTime;	
		                            	  slotObj.timePerSlot = value.timePerSlot;	
		                            	  slotObj.visitTypeId = value.visitTypeId;
		                            	  var dayList  = [];
		                            	  angular.forEach(value.example8model,function(value,index) {
		                            		  dayList.push(value.id);
		                            	  });
		                            	  slotObj.dayArrayList= dayList;
		                            	  
		                            	  slotObj.breakSlotsList = value.breakTimeList;
		                            	 
		                            	  slotList.push(slotObj);
										});
									 
									
									var data = {
											doctorArrayList:doctorSpecialityList,
											slotMasterDtosList :slotList,
											organizationId : $scope.organizationId,
											unitId:$scope.unitId,
											createdDate:$scope.currentDate,
											updatedDate:$scope.currentDate,
											createdBy:$scope.userId,
											updatedBy:$scope.userId,
											status:'A',
											slotTypeId : $scope.slotTypeId
									}
									
									console.log(data);
									//return false;
									var URI = BASE_URL + ROOT_URL
											+ ADDDOCTORSLOTS;

									BillingGenericService
											.serviceAction(METHOD_POST,
													URI, data,
													NOTIFICATION_MSG_STATUS_TRUE)
											.then(
													function(response) {
														console.log(response);
														//$rootScope.startLoader();
														if (response.data.status == "success")
															$scope.init();
													});
									
									
									
								}
							}
							
							
							//Save Modality Slots
							$scope.saveModalitySlots  = function()
							{
								console.log($scope.dataList);
								//return false;
								if ($scope.popUpFlag) {
									$scope.showModal = !$scope.showModal;

								} else {
									$scope.currentDate = new Date();
									$scope.currentDate = moment($scope.currentDate)
											.format('DD-MM-YYYY HH:mm:ss');
									
									var modalityTypeModalityList = [];
									 angular.forEach($scope.example14dataModality,function(value,index) {
		                            	  var sd = {};
		                            	  var splittedStringArray = value.id.split("_");
		                            	  sd.modalityId =splittedStringArray[0]; 
		                            	  sd.subSpecialityId =splittedStringArray[1];
		                            	  sd.specialityId =splittedStringArray[2];
		                            	  modalityTypeModalityList.push(sd);
										});
									 
									 
									 var slotList = [];
									 angular.forEach($scope.dataList,function(value,index) {
		                            	  var slotObj = {};
										  slotObj.fromDate =  moment(value.fromDate).format('YYYY-MM-DD');	
		                            	  slotObj.toDate =  moment(value.toDate).format('YYYY-MM-DD');
		                            	  slotObj.fromTime =  value.fromTime;	
		                            	  slotObj.toTime =  value.toTime;	
		                            	  slotObj.timePerSlot = value.timePerSlot;	
		                            	  slotObj.visitTypeId = value.visitTypeId;
		                            	  var dayList  = [];
		                            	  angular.forEach(value.example8model,function(value,index) {
		                            		  dayList.push(value.id);
		                            	  });
		                            	  slotObj.dayArrayList= dayList;
		                            	  
		                            	  slotObj.breakSlotsList = value.breakTimeList;
		                            	 
		                            	  slotList.push(slotObj);
										});
									 
									
									var data = {
											doctorArrayList:modalityTypeModalityList,
											slotMasterDtosList :slotList,
											organizationId : $scope.organizationId,
											unitId:$scope.unitId,
											createdDate:$scope.currentDate,
											updatedDate:$scope.currentDate,
											createdBy:$scope.userId,
											updatedBy:$scope.userId,
											status:'A',
											slotTypeId : $scope.slotTypeId
									}
									
									console.log(data);
									//return false;
									var URI = BASE_URL + ROOT_URL
											+ ADDDOCTORSLOTS;

									BillingGenericService
											.serviceAction(METHOD_POST,
													URI, data,
													NOTIFICATION_MSG_STATUS_TRUE)
											.then(
													function(response) {
														console.log(response);
														//$rootScope.startLoader();
														if (response.data.status == "success")
															$scope.init();
													});
									
									
									
								}
							}
							
							//Save ECH Slots
							$scope.saveEchSlots  = function()
							{
								console.log($scope.dataList);
								//return false;
								if ($scope.popUpFlag) {
									$scope.showModal = !$scope.showModal;

								} else {
									$scope.currentDate = new Date();
									$scope.currentDate = moment($scope.currentDate)
											.format('DD-MM-YYYY HH:mm:ss');
									
									 
									 var slotList = [];
									 angular.forEach($scope.dataList,function(value,index) {
		                            	  var slotObj = {};
										  slotObj.fromDate =  moment(value.fromDate).format('YYYY-MM-DD');	
		                            	  slotObj.toDate =  moment(value.toDate).format('YYYY-MM-DD');
		                            	  slotObj.noOfSlots = value.noOfSlots;	
		                            	  var dayList  = [];
		                            	  angular.forEach(value.example8model,function(value,index) {
		                            		  dayList.push(value.id);
		                            	  });
		                            	  slotObj.dayArrayList= dayList;
		                            	  slotList.push(slotObj);
										});
									 
									
									var data = {
											slotMasterDtosList :slotList,
											organizationId : $scope.organizationId,
											unitId:$scope.unitId,
											createdDate:$scope.currentDate,
											updatedDate:$scope.currentDate,
											createdBy:$scope.userId,
											updatedBy:$scope.userId,
											status:'A',
											slotTypeId : $scope.slotTypeId
									}
									
									console.log(data);
									//return false;
									var URI = BASE_URL + ROOT_URL
											+ ADDDOCTORSLOTS;

									BillingGenericService
											.serviceAction(METHOD_POST,
													URI, data,
													NOTIFICATION_MSG_STATUS_TRUE)
											.then(
													function(response) {
														console.log(response);
														//$rootScope.startLoader();
														if (response.data.status == "success")
															$scope.init();
													});
									
									
									
								}
							}
							
							
							//for Days
							  $scope.example8data = [ {id: 1, label: "Monday"}, {id: 2, label: "Tuesday"},{id: 3, label: "Wednesday"},{id: 4, label: "Thursday"},{id: 5, label: "Friday"},{id: 6, label: "Saturday"}, {id: 7, label: "Sunday"}]; 
							  $scope.example8settings = { 
									checkBoxes: true, 
									dynamicTitle: true, 
									showUncheckAll: false, 
									showCheckAll: false ,
									scrollable: true,
							  };
							  
							  $scope.addBreak = function(pIndex,cIndex) {
								 // alert(pIndex);
								  var Pobj = $scope.dataList[pIndex];
								  Pobj.breakTimeList.push({breakFromTime:'',breakToTime:''});
								};

								$scope.removeBreak = function(pIndex,index) {
									
									 var Pobj = $scope.dataList[pIndex];
									 var le =  $scope.dataList[pIndex].breakTimeList.length;
									 
										var i = 1;
										
										if(le != 1){
											 var newDataList1 = [];
										angular.forEach($scope.dataList[pIndex].breakTimeList, function(v) {
											
											if (index != i) {
												newDataList1.push(v);
											}
											
											i++;
										});
										
										Pobj.breakTimeList = newDataList1;
										
										}
									
								};
							
								//Get Doctor Slot Details
								$scope.getSlotDetails = function(item)
								{
									//alert(item.date);
									var data = {
											specialityId : item.specialityId,
											doctorId:item.doctorId,
											appointmentDate :  moment(item.appointmentDate).format('YYYY-DD-MM'),
											dayId : item.dayId,
											organizationId:$scope.organizationId,
											unitId : $scope.unitId,
											slotTypeId : $scope.slotTypeId
									};
									console.log(data);
									//return false;
							var URI = BASE_URL + ROOT_URL
										+ GETSLOTDETAILS;
								BillingGenericService
										.serviceAction(METHOD_POST, URI,
												data,
												NOTIFICATION_MSG_STATUS_FALSE)
										.then(
												function(response) {
													console.log(response);
													$scope.slotDetailsList = [];
													// $rootScope.startLoader();
													if (response.data.status == "success")
														{
														  $scope.slotDetailsList = response.data.listObject;
															console.log( $scope.slotDetailsList); 
														}
														
												});
								}
								
								//Get ECH Slot Details
								$scope.getSlotDetailsForECH = function(item)
								{
									//alert(item.date);
									var data = {
											appointmentDate :  moment(item.appointmentDate).format('YYYY-DD-MM'),
											dayId : item.dayId,
											organizationId:$scope.organizationId,
											unitId : $scope.unitId,
											slotTypeId : $scope.slotTypeId
									};
									console.log(data);
									//return false;
							var URI = BASE_URL + ROOT_URL
										+ GETSLOTDETAILS;
								BillingGenericService
										.serviceAction(METHOD_POST, URI,
												data,
												NOTIFICATION_MSG_STATUS_FALSE)
										.then(
												function(response) {
													console.log(response);
													$scope.slotDetailsList = [];
													// $rootScope.startLoader();
													if (response.data.status == "success")
														{
														  $scope.slotDetailsList = response.data.listObject;
															console.log( $scope.slotDetailsList); 
														}
														
												});
									
								}
								
								//Get Modality Slot Details
								$scope.getSlotDetailsForModality = function(item)
								{
									//alert(item.appointmentDate);
									var data = {
											modalityId : item.modalityId,
											specialityId:item.specialityId,
											subSpecialityId:item.subSpecialityId,
											appointmentDate :  moment(item.appointmentDate).format('YYYY-DD-MM'),
											dayId : item.dayId,
											organizationId:$scope.organizationId,
											unitId : $scope.unitId,
											slotTypeId : $scope.slotTypeId
									};
									console.log(data);
									//return false;
							var URI = BASE_URL + ROOT_URL
										+ GETSLOTDETAILS;
								BillingGenericService
										.serviceAction(METHOD_POST, URI,
												data,
												NOTIFICATION_MSG_STATUS_FALSE)
										.then(
												function(response) {
													console.log(response);
													$scope.slotDetailsList = [];
													// $rootScope.startLoader();
													if (response.data.status == "success")
														{
														  $scope.slotDetailsList = response.data.listObject;
															console.log( $scope.slotDetailsList); 
														}
														
												});
									
								}
								// function for updateStatus
        $scope.updateStatus = function(id, type,index) {
         
         
         $scope.currentDate = new Date();
         $scope.currentDate = moment($scope.currentDate)
           .format('DD-MM-YYYY HH:mm:ss');

         type = (type == "A") ? "A" : "I";

         var data = {
          slotId : id,
          status : type,
          updatedBy : $scope.userId,
          updatedDate : $scope.currentDate,
          organizationId:$scope.organizationId,
          unitId : $scope.unitId
         }
         console.log(data);
         var URI = BASE_URL + ROOT_URL
           + UPDATESLOTSTATUS;
         GenericService
           .serviceAction("POST", URI, data)
           .then(
             function(response) {

              if (response.data.status == "success") {
               growl
                 .success(
                   response.data.message,
                   {
                    title : 'Success!'
                   });
              } else {
               growl
                 .error(
                   response.data.message,
                   {
                    title : 'Error!'
                   });
               if(type == 'A')
                {
                $scope.slotDetailsList[index].status = 'I';
                }
               else
                {
                $scope.slotDetailsList[index].status = 'A';
                }
               
              }
             });
        }
						} ]);