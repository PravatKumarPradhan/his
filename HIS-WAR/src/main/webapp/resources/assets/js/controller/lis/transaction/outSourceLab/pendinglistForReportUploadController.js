
/**
 * @author Suraj kumbhoje
 */

angular
		.module('myApp')
		.controller(
				"pendinglistForReportUploadController",
				[
						'$scope',
						'$rootScope',
						'$state',
						'$cookies',
						'GenericService',
						'PagerService',
						'promiseFactoryWithObject',
						'$sessionStorage',
						'growl',
						function($scope, $rootScope, $state, $cookies,
								GenericService, PagerService, promiseFactoryWithObject,
								$sessionStorage, growl) {

							$rootScope.loginpage = true;
							$scope.LISDynamicLabel = "pendinglistForSampleDispatchController";
							
							$scope.firstLevel = true;
							$scope.secondLevel = true;
							$scope.thirdLevel = false;

							/** Variables for pagination */
							$scope.offset = 0;
							$scope.noOfRecordsPerPage = 10;
							$scope.resultOutSourcePendingListController  = [];
							$scope.totalNoOfRecords;
							$scope.searchKeyword;
							$scope.commonListCount;
							$scope.labResultTestDtls;
							$scope.orgId = 1;
							$scope.orgUnitId = 1;
							$scope.createdBy = 1;
							$scope.updatedBy = 1;
							$scope.deptId = LAB_DEPT;
							$scope.subDeptId = BIOCHEM_DEPT;
							$scope.listHelpValueMaster = [];
							
							$scope.listOutSourceMasterDto  = [];
							

							$scope.visitTypeId = 1;
							$scope.selectedVisitTypeIds = [1,2];
							$scope.sampleStatusIds=[2];
							$scope.departmentList= [];
							$scope.visityTypeList = [];
							$scope.testList = [];
							
							$scope.selectedVisitType = [];
							$scope.selectedtestList = [];
							$scope.selectedDepartMentList = [];



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

							
							$scope.outSourceMasterDto = {
									"outSourcedId": "",
						            "orgId": $scope.orgId,
						            "orgUnitId":$scope.orgUnitId,
						            "testId": "",
						            "patientId": "",
						            "visitTypeId": "",
						            "orderId": "",
						            "sampleNo": "",
						            "labSampleDtlsId": "",
						            "createdBy": $scope.createdBy,
						            "createdDate": "",
						            "updatedBy": $scope.updatedBy,
						            "updatedDate": "",
						            "labSampleNo": "",
						            "visitTypeCode": "",
						            "containerName": "",
						            "uhid": "",
						            "patientDetails": "",
						            "doctorDetails": "",
						            "testDesc": "",
						            "deptName": "",
						            "priorityName": "",
						            "colorCode": "",
						            "sampleStatusId": "",
						            "uploadDoc": "",
						            "notes": "",
						            "status": "",
						            "recordPerPage": "",
						            "accPayableMstId":"",
						            "sampleStatus": "",
						            "listOutSourceDetailMasterDto":[]
							}
							
							$scope.OutSourceDetailMasterDto = {
									"outSourceDetailId": "",
						            "orgId": $scope.orgId,
						            "orgUnitId":$scope.orgUnitId,
						            "resource": "",
						            "sampleSendThroughId": "",
						            "courierNumber": "",
						            "remarks": "",
						            "expectedDate": "",
						            "outSourcedId": "",
						            "createdBy": $scope.createdBy,
						            "createdDate": "",
						            "updatedBy": $scope.updatedBy,
						            "updatedDate": "",
						            "status": "",
						            
							}

							
						
							$scope.setNoOfRecords = function() {
								$scope.initOutSourcePendingList($scope.orgId,
										$scope.orgUnitId,$scope.deptId,$scope.offset,
										$scope.noOfRecordsPerPage);
							};

							$scope.initOutSourcePendingList = function(orgId,
									orgUnitId,deptId, offset, noOfRecordsPerPage) {
								try {
									$rootScope.startLoader();
									var data = {
											"orgId":orgId,
											"orgUnitId":orgUnitId,
											"offset":offset,
											"recordPerPage":noOfRecordsPerPage,
											"sampleStatusId":"2"
											
									 
										};
									var data1 = {
											"orgId":orgId,
											"orgUnitId":orgUnitId,
											"sampleStatusId":"2"
										};
									offset = offset != null ? offset : 0;
									noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
											: 10;
									var URI1 = BASE_URL 
									        + ROOT_URL
											+ LIS_OUT_SOURCE_LAB
											+ LIST_PENDING_LIST_FOR_SAMPLE_DISPATCH;


									var URI2 = BASE_URL
											+ ROOT_URL
											+ LIS_OUT_SOURCE_LAB
											+ LIST_PENDING_LIST_FOR_SAMPLE_DISPATCH_COUNT;

									console.log("URI1", URI1);
									console.log("URI2", URI2);
									promiseFactoryWithObject
											.setPromisesWithObject(URI1, URI2, METHOD_POST,
													METHOD_POST,data,data1)
											.then(
													function(response) {
														$rootScope.stopLoader();
														$scope.resultOutSourceMasterList = response[0].listObject;
														$scope.commonListCount = response[1].object;
														$scope.setPage(1,false);
														
														console.log("$scope.resultOutSourceMasterList", JSON
																.stringify($scope.resultOutSourceMasterList));
													});
									
								} catch (e) {
									console.log(e.message);
								}
							}

							$scope.initOutSourcePendingList($scope.orgId,
									$scope.orgUnitId,$scope.deptId,$scope.offset,
									$scope.noOfRecordsPerPage);
				

							$scope.getOutSourcePendingList = function(orgId,
									orgUnitId,deptId,offset,noOfRecordsPerPage) {
								try {
									$rootScope.startLoader();
									var data = {
											"orgId":orgId,
											"orgUnitId":orgUnitId,
											"offset":offset,
											"recordPerPage":noOfRecordsPerPage,
											"sampleStatusId":"2"
										};
									var URI = BASE_URL 
							        + ROOT_URL
									+ LIS_OUT_SOURCE_LAB
									+ LIST_PENDING_LIST_FOR_SAMPLE_DISPATCH;

									GenericService
											.serviceAction(METHOD_POST, URI, data)
											.then(
													function(response) {
														$rootScope.stopLoader();
														$scope.resultOutSourceMasterList  = response.data.listObject;
													});
								} catch (e) {
									console.log(e.message)
								}

							}
							
							
							
							/** code for setting all checkboxes */
							$scope.checkAll = function() {
								if ($scope.selectedAll) {
									$scope.selectedAll = true;
								} else {
									$scope.selectedAll = false;
								}
								angular.forEach($scope.resultOutSourceMasterList,
												function(resultEntry)
												{
									              resultEntry.printReport = $scope.selectedAll;
												});
							}

							
							$scope.getOutSourceDetailsByOutSourceId = function(outSourcedId) {
								try {
									$rootScope.startLoader();
									var data = {
											"orgId":$scope.orgId,
											"orgUnitId":$scope.orgUnitId,
											"outSourcedId":outSourcedId
										};
									var URI = BASE_URL 
							        + ROOT_URL
									+ LIS_OUT_SOURCE_LAB
									+ GET_OUT_SOURCE_DETAILS_BY_OUT_SOURCE_ID;

									GenericService
											.serviceAction(METHOD_POST, URI, data)
											.then(
													function(response) {
														$rootScope.stopLoader();
														$scope.resultOutSourceDetails= response.data.object;
														console.log("$scope.resultOutSourceDetails", JSON
																.stringify($scope.resultOutSourceDetails));
													});
								} catch (e) {
									console.log(e.message)
								}

							}
							
							
							$scope.saveDetails = function() {
						
								$rootScope.startLoader();
								angular
								.forEach(
										$scope.resultOutSourceMasterList,
										function(value,key) 
										{
											if(value.printReport==true)
												{
											
										var outSourceMasterDto = angular
												.copy($scope.outSourceMasterDto);
										outSourceMasterDto.patientId =value.patientId;
										outSourceMasterDto.doctorId=value.doctorId;
										outSourceMasterDto.visitTypeId=value.visitTypeId;
										outSourceMasterDto.testId=value.testId;
										outSourceMasterDto.uploadDoc=value.uploadDoc;
										outSourceMasterDto.notes=value.notes;
										outSourceMasterDto.createdBy = $scope.createdBy;
										outSourceMasterDto.orgId=$scope.orgId;
										outSourceMasterDto.orgUnitId=$scope.orgUnitId;
										outSourceMasterDto.orderId =value.orderId;
										outSourceMasterDto.labSampleDtlsId=value.labSampleDtlsId;	
										outSourceMasterDto.status=ACCEPT;
										outSourceMasterDto.labSampleNo=value.labSampleNo;
										outSourceMasterDto.sampleStatus=1;
										$scope.listOutSourceMasterDto
												.push(outSourceMasterDto);
										console.log("$scope.listOutSourceMasterDto", JSON
												.stringify($scope.listOutSourceMasterDto));
										     }
										});
							
								
								console.log("$scope.listOutSourceMasterDto", JSON
										.stringify($scope.listOutSourceMasterDto));
								var URI = BASE_URL + ROOT_URL
										+ LIS_OUT_SOURCE_LAB + ADD_OUT_SOURCE_MST_LAB;

								try {
									console.log("OutSourceMaster", URI);
									GenericService
											.serviceAction("POST", URI,
													$scope.listOutSourceMasterDto)
											.then(
													function(response) {
														if (response.data.status == 'success') {
															$rootScope.stopLoader();
															$scope.initOutSourcePendingList(
																			$scope.orgId,
																			$scope.orgUnitId,
																			$scope.offset,
																			$scope.noOfRecordsPerPage);
															growl.success(response.data.message,
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
							
							
							$scope.pager = {};
							$scope.page;
							$scope.setPage = function(page, flag)
							{
								if (page < 1 || page > $scope.pager.totalPages)
								{
									return;
								}
								$scope.pager = PagerService.GetPager(
										$scope.commonListCount, page,
										$scope.noOfRecordsPerPage);
								if (flag) {
									$scope.getOutSourcePendingList(
											$scope.orgId, $scope.orgUnitId,$scope.deptId,
											$scope.pager.startIndex,
											$scope.pager.pageSize);
								}
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
															"visitTypes":$scope.selectedVisitTypeIds,
															"sampleStatusIds":$scope.sampleStatusIds,
															"searchKeyword":searchKeyword,
															"deptId":$scope.deptId,
															"subDeptId":$scope.subDeptId,
													}
													
													var URI = BASE_URL 
													        + ROOT_URL
													        + LIS_OUT_SOURCE_LAB
															+ OUT_SOURCE_LIST_PATIENT_LIST_SEARCH;
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
																	  "subDeptId" : $scope.subDeptId,
																	  "subDepts" : $scope.selectedDepartMentList,
																	  "sampleStatusId" :2,
																	 
																	
							                             }
														
														console.log("$scope.selectedtestList",$scope.selectedtestList);
														console.log("$scope.selectedDepartMentList",$scope.selectedDepartMentList);
														console.log("collectedSampleList",JSON.stringify(searchObj));
														var URI = BASE_URL 
														        + ROOT_URL
														        + LIS_OUT_SOURCE_LAB
																+ OUT_SOURCE_COMMON_LIST_PATIENT_LIST_SEARCH
														console.log("URI", URI);
														 GenericService.serviceAction("POST", URI,
																 searchObj).then(
																		 function(response) {
																			 $scope.resultOutSourceMasterList=[];
																				if (response.data.status == 'success') {
																					$rootScope.stopLoader();
																					/*
																					growl.success(response.data.message,
																									{
																										title : 'Success!'
																									});*/
																					
																					console.log("collectedSampleList",JSON.stringify(response.data.listObject));
																					$scope.resultOutSourceMasterList =  response.data.listObject;

																				} else if (response.data.status == 'error') {
																					growl
																							.error(
																									response.data.message,
																									{
																										title : 'Error!'
																									});
																					$scope.resultOutSourceMasterList=[];
																				}
																			});
													} catch (e) {
														console.log(e.message)
													}
												}
						} ]);