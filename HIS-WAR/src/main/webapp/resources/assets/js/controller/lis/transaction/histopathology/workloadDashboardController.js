
/**
 * @author Suraj kumbhoje
 */

angular
		.module('myApp')
		.controller(
				"workloadDashboardController",
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
							$scope.LISDynamicLabel = "workload Dashboard Controller";
							
							$scope.firstLevel = true;
							$scope.secondLevel = true;
							$scope.thirdLevel = false;

							/** Variables for pagination */
							$scope.offset = 0;
							$scope.noOfRecordsPerPage = 10;
							$scope.totalNoOfRecords;
							$scope.searchKeyword;
							$scope.commonListCount;
							$scope.storeListCount;
							$scope.labResultTestDtls;
							$scope.orgId = 1;
							$scope.orgUnitId = 1;
							$scope.createdBy = 1;
							$scope.updatedBy = 1;
							$scope.deptId = LAB_DEPT;
							$scope.subDeptId = HISTOPATHOLOGY_DEPT;
							
							$scope.departmentList= [];
							$scope.listLabDashBoardDto=[];
							$scope.visityTypeList = [];
							$scope.specimanTypeList =[];
							$scope.testList = [];
							
							$scope.selectedVisitType = [];
							$scope.selectedspecimanTypeList = [];
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

							
							
							$scope.LabDashBoardDto={
									
									 "currentStatus":"",
									 "isOutSource":"",
									 "sendToDept":"",
									 "statusId":"",
									 "isArrival":"",
									 "deptId":$scope.deptId,
									 "orgId":$scope.orgId,
									 "orgUnitId":$scope.orgUnitId,
									 "createdBy":$scope.createdBy,
									 "createdDate":"",
									 "updatedBy":$scope.updatedBy,
							}
							
							
						
							$scope.setNoOfRecords = function() {
								$scope.initLabDashBoard($scope.orgId,
										$scope.orgUnitId,$scope.deptId,$scope.subDeptId,$scope.offset,
										$scope.noOfRecordsPerPage);
							};

							$scope.initLabDashBoard = function(orgId,
									orgUnitId,deptId,subDeptId, offset, noOfRecordsPerPage) {
								try {
									$rootScope.startLoader();
									var data = {
											"orgId":orgId,
											"orgUnitId":orgUnitId,
											"deptId":deptId,
											"offset":offset,
											"noOfRecordsPerPage":noOfRecordsPerPage									 
										};
									var data1 = {
											"orgId":orgId,
											"orgUnitId":orgUnitId,
											"deptId":deptId,
											"offset":offset,
											"noOfRecordsPerPage":noOfRecordsPerPage		
										};
									offset = offset != null ? offset : 0;
									noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
											: 10;
									var URI1 = BASE_URL 
									        +ROOT_URL + LIS
											+ LAB_DASHBORD_LIST;

									var URI2 = BASE_URL
								         	+ROOT_URL + LIS
											+ LAB_DASHBORD_LIST_COUNT;

									console.log("URI1", URI1);
									console.log("URI2", URI2);
									promiseFactoryWithObject
											.setPromisesWithObject(URI1, URI2, METHOD_POST,
													METHOD_POST,data,data1)
											.then(
													function(response) {
														$rootScope.stopLoader();
														$scope.labDashBoardList = response[0].listObject;
														$scope.commonListCount = response[1].object;
														$scope.setPage(1,false);
														
														console.log("$scope.labDashBoardList", JSON
																.stringify($scope.labDashBoardList));	
													});
								} catch (e) {
									console.log(e.message);
								}
							}

							$scope.initLabDashBoard($scope.orgId,
									$scope.orgUnitId,$scope.deptId,$scope.subDeptId,$scope.offset,
									$scope.noOfRecordsPerPage);
				

							$scope.getLabDashBoardList = function(orgId,
									orgUnitId,deptId,subDeptId,offset,noOfRecordsPerPage) {
								try {
									$rootScope.startLoader();
									var data = {
											"orgId":orgId,
											"orgUnitId":orgUnitId,
											"deptId":deptId,
											"offset":offset,
											"noOfRecordsPerPage":noOfRecordsPerPage
										};
									var URI = BASE_URL+ROOT_URL+ LIS
														+ LAB_DASHBORD_LIST;
									console.log("URI", URI);

									GenericService
											.serviceAction(METHOD_POST, URI, data)
											.then(
													function(response) {
														$rootScope.stopLoader();
														$scope.labDashBoardList  = response.data.listObject;
													});
								} catch (e) {
									console.log(e.message)
								}

							}
							

							/*
							 *  Get Sample Details List
							 */
							$scope.getSampleDtlsList = function(labSampleDtlsId) {
								try {
									var data = "";
									var URI = BASE_URL + ROOT_URL
									        + LIS
											+ GET_LAB_SAMPLE_DETAILS
											+ S + labSampleDtlsId
											+ S + $scope.orgId
											+ S + $scope.orgUnitId;
									console.log("URI", URI);
									GenericService
											.serviceAction("GET", URI, data)
											.then(
													function(response) {
														$scope.sampleDtlsList  = response.data.listObject;
													});
								} catch (e) {
									console.log(e.message)
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
							$scope.getMicroPatientList = function(searchKeyword)
							{
								try 
								{
									var data = {
											"orgId":$scope.orgId,
											"orgUnitId":$scope.orgUnitId,
											"deptId":$scope.deptId,
											"subDeptId":$scope.subDeptId,
											"searchKeyword":searchKeyword,
									}
									
									var URI = BASE_URL 
									        + ROOT_URL
											+ LIS
											+ SEARCH_DOCTOR_DETAILS
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
							

							$scope.getSpecimanList = function()
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
											+ HISTO_MICRO_SPECIMAN_LIST
									console.log("URI", URI);
									 GenericService.serviceAction("POST", URI,
											data).then(function(response) 
									{
										console.log("response.data.listObject",response.data.listObject);
									  $scope.specimanTypeList =  response.data.listObject;
									});
								} catch (e) {
									console.log(e.message)
								}
							}
							$scope.getSpecimanList();
							
							$scope.getTestList = function()
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
							
							
							$scope.getSampleStatusList = function(orgId) {
								try {
									var data = "";
									var URI = BASE_URL + ROOT_URL
											+ LIS_COMMON + GET_SAMPLE_STATUS_DETAILS
											+ S + orgId;
									console.log("URI", URI);
									GenericService.serviceAction("GET", URI, data)
											  .then(function(response) {
														$scope.sampleStatusList = response.data.listObject;
													});
								   } catch (e) {
									console.log(e.message)
								}
							}
							$scope.getSampleStatusList($scope.orgId);
							
							
							/*get Sample Number List*/
							$scope.getSampleNoList = function(searchKeyword)
							{
								try 
								{
									var data = {
											"orgId":$scope.orgId,
											"orgUnitId":$scope.orgUnitId,
											"searchKeyword":searchKeyword,
									}
									
									var URI = BASE_URL 
									        + ROOT_URL
											+ LIS
											+ SEARCH_SAMPLE_NO_DETAILS
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

							$scope.saveDetails = function() {
								
								
								$rootScope.startLoader();
								
								$scope.frozenSectObj.ackRemark = $scope.ackRemark;
								$scope.listLabDashBoardDto.push($scope.frozenSectObj);
								var URI = BASE_URL + ROOT_URL
								+ LIS + LAB_DASHBORD_SEARCH;

								try {
									console.log("$scope.listLabDashBoardDto", URI);
									
									GenericService
											.serviceAction("POST", URI,
													$scope.listLabDashBoardDto)
											.then(
													function(response) {
														if (response.data.status == 'success') {
															$rootScope.stopLoader();
															$scope.initLabDashBoard($scope.orgId,
																	$scope.orgUnitId,$scope.deptId,$scope.subDeptId,$scope.offset,
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
									$scope.getLabDashBoardList(
											$scope.orgId, $scope.orgUnitId,$scope.deptId, $scope.subDeptId,
											$scope.pager.startIndex,
											$scope.pager.pageSize);
								}
							}
							
							/** code for checkOutsourced  checkboxes */
							$scope.checkOutsourced  = function() {
								if ($scope.selectedOutsourced) {
									$scope.selectedOutsourced = true;
									$scope.outsourced = 'Y';
								} else {
									$scope.selectedOutsourced = false;
									
								}
								
							}
							
							/** code for checkPending checkboxes */
							$scope.checkPending  = function() {
								if ($scope.selectedPending) {
									$scope.selectedPending = true;
									$scope.Pending  = 'Y';
								} else {
									$scope.selectedPending = false;
									$scope.pending = 'N';
								}
								
							}
							
							/** code for checkCompleted checkboxes */
							$scope.checkCompleted  = function() {
								if ($scope.selectedCompleted) {
									$scope.selectedCompleted = true;
									$scope.completed='Y';
								} else {
									$scope.selectedCompleted = false;
									$scope.completed='N';
								}
								
							}
						
							 $scope.searchWorkLoadDashBoardList = function()
								{
									try 
									{
										var searchObj = {
												
													  "orgId" : $scope.orgId,
													  "orgUnitId" :$scope.orgUnitId,
													  "fromDate": $scope.fromDate==undefined?"":moment($scope.fromDate.getTime()).format('YYYY-MM-DD') ,
									                  "toDate":  $scope.toDate==undefined?"":moment($scope.toDate.getTime()).format('YYYY-MM-DD'),
													  "patientId" :  $scope.selectedPatient,
													  "outsourced" : $scope.outsourced,
													  "outSource" : $scope.pending,
													  "completed" : $scope.completed,
													  "sampleStatusId" : $scope.statusId,
													  "labSampleDtlsId" : $scope.labSampleDtlsId,
													  "visitTypes" :$scope.selectedVisitType,
													  "specimanTypes":$scope.selectedspecimanTypeList,
													  "testTypes" : $scope.selectedtestList,
													  "subDepts" : $scope.selectedDepartMentList,
													  "deptId":$scope.deptId
													
			                             }
										console.log("labDashBoardList",JSON.stringify(searchObj));
										var URI = BASE_URL 
										        + ROOT_URL
												+ LIS
												+ LAB_DASHBORD_SEARCH
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
																	
																	console.log("labDashBoardList",JSON.stringify(response.data.listObject));
																	$scope.labDashBoardList =  response.data.listObject;

																} else if (response.data.status == 'error') {
																	growl
																			.error(
																					response.data.message,
																					{
																						title : 'Error!'
																					});
																	 $scope.labDashBoardList=[];
																}
															});
									} catch (e) {
										console.log(e.message)
									}
								}					
						} ]);