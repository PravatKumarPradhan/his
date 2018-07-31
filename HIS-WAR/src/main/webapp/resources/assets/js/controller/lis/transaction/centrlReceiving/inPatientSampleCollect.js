/**
 * @author Ganesh Chaudhari
 */

angular
		.module('myApp')
		.controller(
				"inPatientController",
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
						function($scope, $rootScope, $state, $cookies,
								GenericService, PagerService, promiseFactory,
								$sessionStorage, growl) {

							$rootScope.loginpage = true;
							$scope.LISDynamicLabel = "Sample";

							/** Variables for pagination */
							$scope.offset = 0;
							$scope.noOfRecordsPerPage = 10;
							$scope.inPatientSampleList = [];
							$scope.totalNoOfRecords;
							$scope.searchKeyword;
							$scope.commonListCount;

							$scope.orgId = 1;
							$scope.orgUnitId = 1;
							$scope.createdBy = 1;
							$scope.updatedBy = 1;
							$scope.deptId = LAB_DEPT;
							$scope.visitTypeId = 2;
							
							$scope.departmentList= [];
							$scope.visityTypeList = [];
							$scope.testList = [];
							
							$scope.selectedVisitType = [];
							$scope.selectedtestList = [];
							$scope.selectedDepartMentList = [];


							$scope.listLabSampleDtls = [];
							$scope.labSampleDtls = {
								"labSampleDtlsId" : "",
								"orgId" : $scope.orgId,
								"orgUnitId" : $scope.orgUnitId,
								"testId" : "",
								"deptId" : $scope.deptId,
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
								"sampleGenBy" : "",
								"currStatus" : "",
								"sampleStatusId" : "",
								"isOutsourced" : "",
								"outsourcedLabId" : "",
								"sampleSendtocrDatetime" : "",
								"sampleSendtocrBy" : "",
								"sampleCollectionDatetime" : "",
								"sampleCollectionBy" : "",
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
							}

							
							
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

							
							
							$scope.setNoOfRecords = function() {
								$scope.initSampleListInPatient($scope.orgId,
										$scope.orgUnitId, IP, $scope.offset,
										$scope.noOfRecordsPerPage);
							};

							$scope.initSampleListInPatient = function(orgId,
									orgUnitId, visitTypeId, offset,
									noOfRecordsPerPage) {

								try {
									$rootScope.startLoader();
									var data = "";
									offset = offset != null ? offset : 0;
									noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
											: 10;
									var URI1 = BASE_URL + ROOT_URL
											+ LIS_CENTRAL_RECEIVING
											+ SAMPLE_LIST_FOR_INOUT_PATIENT + S
											+ orgId + S + orgUnitId + S
											+ visitTypeId + S + $scope.deptId+ S +offset + S
											+ noOfRecordsPerPage;

									var URI2 = BASE_URL + ROOT_URL
											+ LIS_CENTRAL_RECEIVING
											+ GET_TOTAL_RECORD_INPATIENT + S
											+ orgId + S + orgUnitId+ S + $scope.deptId;

									console.log("URI1", URI1);
									console.log("URI2", URI2);
									promiseFactory
											.setPromises(URI1, URI2, "GET",
													"GET")
											.then(
													function(response) {
														$rootScope.stopLoader();
														$scope.inPatientSampleList = response[0].listObject;
														$scope.commonListCount = response[1].object;
														$scope
																.setPage(1,
																		false);
													});
								} catch (e) {
									console.log(e.message);
								}
							}

							$scope.getRejectionList = function(orgId) {
								try {
									var data = "";
									var URI = BASE_URL + ROOT_URL
											+ LIS_COMMON
											+ GET_REJECTED_REASON_LIST + S
											+ orgId;
									console.log("URI", URI);
									GenericService
											.serviceAction("GET", URI, data)
											.then(
													function(response) {
														$scope.rejectionList = response.data.listObject;
													});
								} catch (e) {
									console.log(e.message)
								}

							}
							$scope.getRejectionList($scope.orgId);
							
							$scope.showRejecPopup = function(isAccRej,index)
                              {
								if(isAccRej=='R')
								{
								  $scope.rejIndex = index;
								  $("#accptRejInPatient").modal('show');
							    }
							}

							$scope.getSampleListInPatient = function(orgId,
									orgUnitId, visitTypeId, offset,
									noOfRecordsPerPage) {
								try {
									$rootScope.startLoader();
									var data = "";
									var URI = BASE_URL + ROOT_URL
											+ LIS_CENTRAL_RECEIVING
											+ SAMPLE_LIST_FOR_INOUT_PATIENT + S
											+ orgId + S + orgUnitId + S
											+ visitTypeId + S + offset + S
											+ noOfRecordsPerPage;

									console.log("URI", URI);
									GenericService
											.serviceAction("GET", URI, data)
											.then(
													function(response) {
														$rootScope.stopLoader();
														$scope.inPatientSampleList = response.data.listObject;
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
									$scope.getSampleListInPatient($scope.orgId,
											$scope.orgUnitId, IP,
											$scope.pager.startIndex,
											$scope.pager.pageSize);
								}
							}

							$scope.initSampleListInPatient($scope.orgId,
									$scope.orgUnitId, IP, $scope.offset,
									$scope.noOfRecordsPerPage);
							// code for Activating Inactivating Sample
							$scope.collectSampleForInPatient = function() {
								$scope.listLabSampleDtls = [];
								$rootScope.startLoader();
								angular
										.forEach(
												$scope.inPatientSampleList,
												function(value, key) {
													if (value.isAcceptOrReject == 'A') {
														var labSampleDtlsforAccept = angular
																.copy($scope.labSampleDtls);
														labSampleDtlsforAccept.labSampleDtlsId = value.labSampleDtlsId;
														labSampleDtlsforAccept.sampleAcceptBy = $scope.createdBy;
														labSampleDtlsforAccept.currStatus = value.isAcceptOrReject;
														labSampleDtlsforAccept.samplePendingCount = value.samplePendingCount;
														labSampleDtlsforAccept.isCentrifugationReq = value.isCentrifugationReq;
														$scope.listLabSampleDtls
																.push(labSampleDtlsforAccept);
													}
													if (value.isAcceptOrReject == 'R') {
														var labSampleDtlsforReject = angular
																.copy($scope.labSampleDtls);
														labSampleDtlsforReject.labSampleDtlsId = value.labSampleDtlsId;
														labSampleDtlsforReject.currStatus = value.isAcceptOrReject;
														labSampleDtlsforReject.sampleRejectReasonId = value.sampleRejectReasonId;
														labSampleDtlsforReject.sampleRejectReason = value.sampleRejectReason;
														labSampleDtlsforReject.sampleRejectBy = $scope.createdBy;
														$scope.listLabSampleDtls
																.push(labSampleDtlsforReject);
													}
												});

								console.log("$scope.listLabSampleDtls", JSON
										.stringify($scope.listLabSampleDtls));
								var URI = BASE_URL + ROOT_URL
										+ LIS_CENTRAL_RECEIVING
										+ ACCPET_REJECT_SAMPLE_FOR_IN_OUT;

								try {
									console
											.log("$scope.listLabSampleDtls",
													JSON
															.stringify($scope.listLabSampleDtls));
									var URI = BASE_URL + ROOT_URL
											+ LIS_CENTRAL_RECEIVING
											+ ACCPET_REJECT_SAMPLE_FOR_IN_OUT;

									console.log("ACCEPT_REJECT_SAMPLE", URI);
									GenericService
											.serviceAction("POST", URI,
													$scope.listLabSampleDtls)
											.then(
													function(response) {
														if (response.data.status == 'success') {
															$scope
																	.initSampleListInPatient(
																			$scope.orgId,
																			$scope.orgUnitId,
																			IP,
																			$scope.offset,
																			$scope.noOfRecordsPerPage);
															growl
																	.success(
																			response.data.message,
																			{
																				title : 'Success!'
																			});
															$scope
																	.$broadcast('show-errors-reset');

														} else if (response.data.status == 'error') {
															growl
																	.error(
																			response.data.message,
																			{
																				title : 'Error!'
																			});
														}
													});
								} catch (e) {
									console.log("Exception", e.message);
								}
							}
							
							/** code for setting all checkboxes */
							$scope.checkAll = function() {
								if ($scope.selectedAll) {
									$scope.selectedAll = true;
									$scope.value = 'A';
								} else {
									$scope.selectedAll = false;
									$scope.value = 'N';
								}
								angular
										.forEach(
												$scope.inPatientSampleList,
												function(labDetailsObj) {
													labDetailsObj.isAcceptOrReject = $scope.value;
												});
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
						$scope.getSendToCRList = function(searchKeyword)
						{
							try 
							{
								var data = {
										"orgId":$scope.orgId,
										"orgUnitId":$scope.orgUnitId,
										"visitTypeId":$scope.visitTypeId,
										"sampleStatusId":SAMPLE_SEND_TO_CR,
										"searchKeyword":searchKeyword,
								}
								
								var URI = BASE_URL 
								        + ROOT_URL
										+ LIS_TRANSACTION
										+ SEARCH_SEND_TO_CR_LIST_PATIENT;
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
						
						
						$scope.getTestList = function()
						{
							try 
							{
								var data = {
										"orgId":$scope.orgId,
										"orgUnitId":$scope.orgUnitId,
										"deptId":$scope.deptId,
										"subDeptId":$scope.subDeptId
								}
								
								var URI = BASE_URL 
								        + ROOT_URL
										+ LIS_HISTOPATHOLOGY
										+ HISTO_MICRO_TEST_LIST
								console.log("URI", URI);
								 GenericService.serviceAction("POST", URI,
										data).then(function(response) {
									console.log("response.data.listObject",response.data.listObject);
									$scope.testList =  response.data.listObject;
								});
							} catch (e) {
								console.log(e.message)
							}
						}
						$scope.getTestList();
						
						$scope.getDepartMentList = function(orgId) {
							try {
								var data = "";
								var URI = BASE_URL + ROOT_URL
										+ LIS_COMMON + GET_DEPARMENT_LIST
										+ S + orgId;
								console.log("URI", URI);
								GenericService.serviceAction("GET", URI, data)
										  .then(function(response) {
											  angular.forEach(response.data.listObject, function(value, key) {
													var multiselectObj = angular.copy($scope.multiselectObj);
													multiselectObj.id = value.id;
													multiselectObj.label = 	value.name;
														$scope.departmentList.push(multiselectObj);
													});
												});
							   } catch (e) {
								console.log(e.message)
							}
						}
						$scope.getDepartMentList($scope.orgId);
						
						
						/*get Sample Number List*/
						$scope.getSampleNoList = function(searchKeyword)
						{
							try 
							{
								var data = {
										"orgId":$scope.orgId,
										"orgUnitId":$scope.orgUnitId,
										"sampleStatusId":SAMPLE_SEND_TO_CR,
										"searchKeyword":searchKeyword,
								}
								
								var URI = BASE_URL 
								        + ROOT_URL
								    	+ LIS_TRANSACTION
										+ SEARCH_GENRAL_LAB_SAMPLE_NO_LIST
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
						
						
						 $scope.selectSampleNo= function($item, $model,$label)
							{
						      $scope.selectedSampleNo = $item.sampleNo;
						      $scope.labSampleDtlsId = $item.labSampleDtlsId;
						    
					        }
						 $scope.searchOutPatientList = function()
							{
								try 
								{
									var searchObj = {
												  "orgId" : $scope.orgId,
												  "orgUnitId" :$scope.orgUnitId,
												  "fromDate": $scope.fromDate==undefined?"":moment($scope.fromDate.getTime()).format('YYYY-MM-DD') ,
						                          "toDate":  $scope.toDate==undefined?"":moment($scope.toDate.getTime()).format('YYYY-MM-DD'),
												  "patientId" :  $scope.selectedPatient,
												  "labSampleDtlsId" : $scope.labSampleDtlsId,
												  "visitTypes" :$scope.selectedVisitType,
												  "testTypes" : $scope.selectedtestList,
												  "deptId" : $scope.deptId,
												  "subDepts" : $scope.selectedDepartMentList,
												  "visitTypeId" : $scope.visitTypeId,
												 
												
		                             }
									
									console.log("$scope.selectedtestList",$scope.selectedtestList);
									console.log("$scope.selectedDepartMentList",$scope.selectedDepartMentList);
									console.log("collectedSampleList",JSON.stringify(searchObj));
									var URI = BASE_URL 
									        + ROOT_URL
									        + LIS_CENTRAL_RECEIVING
											+ OUT_PATIENT_LIST_SEARCH
									console.log("URI", URI);
									 GenericService.serviceAction("POST", URI,
											 searchObj).then(
													 function(response) {
														 $scope.inPatientSampleList=[];
															if (response.data.status == 'success') {
																$rootScope.stopLoader();
																/*
																growl.success(response.data.message,
																				{
																					title : 'Success!'
																				});*/
																
																console.log("collectedSampleList",JSON.stringify(response.data.listObject));
																$scope.inPatientSampleList =  response.data.listObject;

															} else if (response.data.status == 'error') {
																growl
																		.error(
																				response.data.message,
																				{
																					title : 'Error!'
																				});
																$scope.inPatientSampleList=[];
															}
														});
								} catch (e) {
									console.log(e.message)
								}
							}

						} ]);