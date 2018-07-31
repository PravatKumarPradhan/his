/**
 * @author Ganesh Chaudhari
 */

angular
		.module('myApp')
		.controller(
				"phlebotomyController",
				[
						'$scope',
						'$rootScope',
						'$state',
						'$cookies',
						'GenericService',
						'PagerService',
						'promiseFactory',
						'$sessionStorage',
						'growl',
						'$interval',
						function($scope, $rootScope, $state, $cookies,
								GenericService, PagerService, promiseFactory,
								$sessionStorage, growl,$interval) {

							$rootScope.loginpage = true;
							$scope.LISDynamicLabel = "Sample";

							/** Variables for pagination */
							$scope.offset = 0;
							$scope.noOfRecordsPerPage = 10;
							$scope.phlebotomyWorkList = [];
							$scope.phlebotomyWorkListDetails = [];
							$scope.totalNoOfRecords;
							$scope.searchKeyword;
							$scope.commonListCount;

							$scope.orgId = 1;
							$scope.orgUnitId = 1;
							$scope.createdBy = 1;
							$scope.updatedBy = 1;
							$scope.visitTypeId=1;
							$scope.deptId = LAB_DEPT;
							
							$scope.visityTypeList = [];
							
							$scope.selectedVisitType = [];
							

							/* code for setting Label */
							$(".selectedPageName").text("Phlebotomy Work List");
							
							/**DatePicker Code  */
							
							$scope.fromDate = moment().startOf('day').toDate();
						    $scope.toDate = moment().startOf('day').toDate();
						    $scope.completed='N';
							$scope.pending = 'N';
							$scope.outsourced = 'N';
							
							$scope.dateOptions =
							{
								formatYear : 'yyyy',
								showWeeks : false,
								maxDate : new Date(),
							};

							$scope.open = function() {
								$scope.popup.opened = true;
							};

							$scope.popup =
							{
								opened : false
							};
							
							$scope.popup1 =
							{
								opened : false
							};
							
							$scope.open1 = function() 
							{
								$scope.popup1.opened = true;
							};

							

							$scope.initLabSampleMaster = function() {
								$scope.listSampleDtls = [];
								$scope.labSampleMaster = {
									"labSampleId" : "",
									"orgId" : $scope.orgId,
									"orgUnitId" : $scope.orgUnitId,
									"sampleGenDatetime" : "",
									"orderId" : "",
									"visitTypeId" : "",
									"visitAdmId" : "",
									"patientId" : "",
									"doctorId" : "",
									"createdBy" : "",
									"createdDate" : $scope.createdBy,
									"updatedBy" : "",
									"updatedDate" : "",
									"uhid" : "",
									"patientDetails" : "",
									"doctorDetails" : "",
									"priorityId" : "",
									"priorityName" : "",
									"colorCode" : "",
									"isOrderComplete" : "",
									"genderId" :"",
									"visitType":"",
									"listLabSampleDetailsMaster" : $scope.listSampleDtls
								}
							}

							$scope.initLabSampleMaster();
							$scope.labSampleDtls = {
								"labSampleDtlsId" : "",
								"orgId" : $scope.orgId,
								"orgUnitId" : $scope.orgUnitId,
								"testId" : "",
								"deptId" : "",
								"subDeptId" : "",
								"deptName" : "",
								"labSampleId" : "",
								"sampleBarcode" : "",
								"sampleId" : "",
								"sampleNo" : "",
								"orderDetailsId" : "",
								"orderId" : "",
								"orderQty" : "",
								"serviceId" : "",
								"testCode" : "",
								"testDesc" : "",
								"sampleName" : "",
								"sampleVolume" : "",
								"unitName" : "",
								"profileId" : "",
								"panelCode":"",
								"packageId" : "",
								"isCentrifugationReq" : "",
								"isAlliquoteReq" : "",
								"sampleTypeId" : "",
								"containerId" : "",
								"containerName" : "",
								"sampleReqCount" : "",
								"samplePendingCount" : "",
								"sampleGenDatetime" : "",
								"sampleGenBy" : $scope.createdBy,
								"currStatus" : "",
								"sampleStatusId" : "",
								"isOutsourced" : "",
								"outsourcedLabId" : "",
								"sampleSendtocrDatetime" : "",
								"sampleSendtocrBy" : "",
								"sampleCollectionDatetime" : "",
								"sampleCollectionBy" : $scope.createdBy,
								"sampleCentrifugationDatetime" : "",
								"sampleCentrifugationBy" : "",
								"sampleAcceptDatetime" : "",
								"sampleAcceptBy" : "",
								"sampleRejectDatetime" : "",
								"sampleRejectBy" : "",
								"sampleRejectReasonId" : "",
								"sampleRejectReason" : "",
								"sampleRecollectFlag" : "",
								"sampleRecollectAgainstId" : "",
								"createdBy" : $scope.createdBy,
								"createdDate" : "",
								"updatedBy" : "",
								"updatedDate" : "",
								"sampleTestTime":"",
								"patientVisitAge":"",
								"genderId":"",
								"visitType":"",
								"listTMicroSampleMediaDto":[]	
								/*"sampleTestTime":"",
								"patientVisitAge":"",
								"genderId":""*/
									
							}

							// ====================================================CODE
							// START FORFOR SAMPLE MASTER
							// LIST===========================================================

							$scope.setNoOfRecords = function() {
								$scope.initPhlebotomyWorklist($scope.orgId,
										$scope.orgUnitId, $scope.offset,
										$scope.noOfRecordsPerPage);
							};

							$scope.initPhlebotomyWorklist = function(orgId,
									orgUnitId, offset, noOfRecordsPerPage) {

								try {
									$rootScope.startLoader();
									var data = "";
									offset = offset != null ? offset : 0;
									noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
											: 10;
									var URI1 = BASE_URL + ROOT_URL
											+ LIS_TRANSACTION
											+ GET_PHLEBOTOMY_WORKLIST + S
											+ orgId + S + orgUnitId + S
											+ $scope.deptId+S
											+ offset + S + noOfRecordsPerPage;
									
									var URI2 = BASE_URL + ROOT_URL
											+ LIS_TRANSACTION
											+ GET_TOTAL_PHLOBOTOMYRECORD + S
											+ orgId + S + orgUnitId
											+ S + $scope.deptId;
									
									console.log("URI1", URI1);
									console.log("URI2", URI2);
									promiseFactory
											.setPromises(URI1, URI2, "GET",
													"GET")
											.then(
													function(response){
														$rootScope.stopLoader();
														$scope.phlebotomyWorkList = response[0].listObject;
														$scope.commonListCount = response[1].object;
														console.log("$scope.commonListCount",$scope.commonListCount);
														console.log("$scope.phlebotomyWorkList length",$scope.phlebotomyWorkList.length);
														$scope.setPage(1,false);
													});
								} catch (e) {
									console.log(e.message);
								}
							}

							$scope.getPhlebotomyWorklist = function(orgId,
									orgUnitId, offset, noOfRecordsPerPage) {
								try {
									$rootScope.startLoader();
									var data = "";
									var URI = BASE_URL + ROOT_URL
											+ LIS_TRANSACTION
											+ GET_PHLEBOTOMY_WORKLIST + S
											+ orgId + S + orgUnitId + S
											+ $scope.deptId+S
											+ offset + S + noOfRecordsPerPage;

									console.log("URI", URI);
									GenericService
											.serviceAction("GET", URI, data)
											.then(
													function(response)
													{
														$scope.phlebotomyWorkList = response.data.listObject;
														$rootScope.stopLoader();
													});
								} catch (e) {
									console.log(e.message)
								}

							}

							$scope.labSampleDetails = {};
							$scope.getWorkListDetails = function(orgId,
									orgUnitId, orderId) {
								$rootScope.startLoader();
								try {

									$scope.labSampleDetails = $scope.phlebotomyWorkList
											.filter(function(labSampleDtls) {
												if (labSampleDtls.orderId == orderId) {
													$scope.labSampleMaster = labSampleDtls;
													return labSampleDtls;

												}
											});
									var data = "";
									var URI = BASE_URL + ROOT_URL
											+ LIS_TRANSACTION
											+ GET_PHLEBOTOMY_WORKLIST_DETAILS
											+ S + orgId + S + orgUnitId + S
											+ orderId + S + $scope.deptId ;
									console.log("GET_PHLEBOTOMY_WORKLIST_DETAILS", URI);
									GenericService
											.serviceAction("GET", URI, data)
											.then(
													function(response) 
													{
														$rootScope.stopLoader();
														$scope.phlebotomyWorkListDetails = response.data.listObject;
														
													});
								} catch (e) {
									console.log(e.message)
								}
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
									$scope.getPhlebotomyWorklist($scope.orgId,
											$scope.orgUnitId,
											$scope.pager.startIndex,
											$scope.pager.pageSize);
								}
							}

							$scope.initPhlebotomyWorklist($scope.orgId,
									$scope.orgUnitId, $scope.offset,
									$scope.noOfRecordsPerPage);

							/** code for setting all checkboxes */
							$scope.checkAll = function() {
								if ($scope.selectedAll) {
									$scope.selectedAll = true;
								} else {
									$scope.selectedAll = false;
								}
								angular
										.forEach(
												$scope.phlebotomyWorkListDetails,
												function(labDetailsObj) {
													labDetailsObj.sampleStatusId = $scope.selectedAll;
												});
							}

							// ====================================================CODE
							// END FOR SAMPLE MASTER
							// LIST===========================================================

							// code for Activating Inactivating Sample
							$scope.collectAndSaveSample = function() {
								$rootScope.startLoader();
								$scope.listSampleDtls = [];
								var worklistDetails;
								angular
										.forEach(
												$scope.phlebotomyWorkListDetails,
												function(value, key) {
													if (value.sampleStatusId == true) {
														worklistDetails = angular
																.copy(value);
														worklistDetails.sampleStatusId = SAMPLE_COLLECTED;
														worklistDetails.patientId = $scope.labSampleMaster.patientId;
														worklistDetails.doctorId = $scope.labSampleMaster.doctorId;
														worklistDetails.priorityId = $scope.labSampleMaster.priorityId;
														worklistDetails.sampleCollectionBy = $scope.createdBy;
														$scope.listSampleDtls
																.push(worklistDetails);
													}
												});
								$scope.labSampleMaster.listLabSampleDetailsMaster = $scope.listSampleDtls;
								console.log("$scope.labSampleMaster", JSON
										.stringify($scope.labSampleMaster));
								var URI = BASE_URL + ROOT_URL
										+ LIS_TRANSACTION + COLLECT_SAMPLE;

								try {
									console.log("SAVE_SAMPLE_URI", URI);
									GenericService
											.serviceAction("POST", URI,
													$scope.labSampleMaster)
											.then(
													function(response) {
														if (response.data.status == 'success') {
															$rootScope.stopLoader();
															growl.success(
																			response.data.message,
																			{
																				title : 'Success!'
																			});
															$scope
																	.$broadcast('show-errors-reset');
															$scope
																	.initPhlebotomyWorklist(
																			$scope.orgId,
																			$scope.orgUnitId,
																			$scope.offset,
																			$scope.noOfRecordsPerPage);
														} else if (response.data.status == 'error') 
														{	$rootScope.stopLoader();
															growl.error(response.data.message,
																			{
																				title : 'Error!'
																			});
														}
													});
								} catch (e) {
									console.log("Exception", e.message);
								}
							}
							
					
							 $interval(refreshPage, 300000);
							 function refreshPage()
							 {
								 $scope.initPhlebotomyWorklist($scope.orgId,
											$scope.orgUnitId, $scope.offset,
											$scope.noOfRecordsPerPage);
							 }
							 

								/*====================================SEARCH DETAILS=====================================*/

							    $scope.dropDownSetting = {
							    		scrollableHeight: '100px', 
							    		scrollable: true
							    }; 
							    
							    $scope.multiselectObj = {
							    		"id":"",
							    		"label":""
							    }
							    
							    /** Service Autocomplete List * */
								$scope.getPhlebotomyPatientList = function(searchKeyword)
								{
									try 
									{
										var data = {
												"orgId":$scope.orgId,
												"orgUnitId":$scope.orgUnitId,
												"visitTypeId":$scope.visitTypeId,
												"searchKeyword":searchKeyword,
										}
										
										var URI = BASE_URL 
										        + ROOT_URL
												+ LIS_TRANSACTION
												+ SEARCH_PHLEBOTOMY_WORK_LIST_PATIENT
										console.log("URI", URI);
										return GenericService.serviceAction("POST", URI,
												data).then(function(response) {
											console.log("response.data.listObject",response.data.listObject);
											return response.data.listObject;
										});
									} catch (e) {
										console.log(e.message)
									}
								}
							    
							    $scope.selectPatient = function($item, $model,$label)
								{
							      $scope.selectedPatient = $item.id;
							     // $scope.PatientName= $item.firstName +"  "+$item.lastName;
							      $scope.uhid=$item.uhid;
						        }
							    
							    
							    
								/*get Visi tType List*/
								$scope.getVisitTypeList = function()
								{
									try 
									{
										var data = {
												"orgId":$scope.orgId,
												"orgUnitId":$scope.orgUnitId,
												"deptId":$scope.deptId,
												"subDeptId":$scope.subDeptId,
												"searchKeyword":"",
										}
										
										var URI = BASE_URL 
										        + ROOT_URL
												+ LIS_HISTOPATHOLOGY
												+ HISTO_MICRO_VISIT_LIST
										console.log("URI", URI);
										 GenericService.serviceAction("POST", URI,
												data).then(function(response) {
											console.log("response.data.listObject",response.data.listObject);
											angular.forEach(response.data.listObject, function(value, key) {
												var multiselectObj = angular.copy($scope.multiselectObj);
												multiselectObj.id = value.id;
												multiselectObj.label = 	value.label;
													$scope.visityTypeList.push(multiselectObj);
												});
											
											
										});
									} catch (e) {
										console.log(e.message)
									}
								}
								
								$scope.getVisitTypeList();
								

								 $scope.searchPhlebotomyWorkList = function()
									{
										try 
										{
											var searchObj = {
													
														  "orgId" : $scope.orgId,
														  "orgUnitId" :$scope.orgUnitId,
														  "fromDate": $scope.fromDate==undefined?"":moment($scope.fromDate.getTime()).format('YYYY-MM-DD') ,
								                          "toDate":  $scope.toDate==undefined?"":moment($scope.toDate.getTime()).format('YYYY-MM-DD'),
														  "patientId" :$scope.selectedPatient,
														  "visitTypes" :$scope.selectedVisitType,
														  "deptId": $scope.deptId
				                             }
											console.log("phlebotomyWorkList",JSON.stringify(searchObj));
											var URI = BASE_URL 
											        + ROOT_URL
											        + LIS_TRANSACTION
													+ PHLEBOTOMY_WORK_LIST_SEARCH
											console.log("URI", URI);
											 GenericService.serviceAction("POST", URI,
													 searchObj).then(
															 function(response) {
																 $scope.labDashBoardList=[];
																	if (response.data.status == 'success') {
																		$rootScope.stopLoader();
																		/*
																		growl.success(response.data.message,
																						{
																							title : 'Success!'
																						});*/
																		
																		console.log("phlebotomyWorkList",JSON.stringify(response.data.listObject));
																		$scope.phlebotomyWorkList =  response.data.listObject;

																	} else if (response.data.status == 'error') {
																		growl
																				.error(
																						response.data.message,
																						{
																							title : 'Error!'
																						});
																		$scope.phlebotomyWorkList=[];
																	}
																});
										} catch (e) {
											console.log(e.message)
										}
									}	
							 
							
						} ]);