
/**
 * @author Ganesh
 */

angular
		.module('myApp')
		.controller(
				"restainRequestWorklistController",
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
							$scope.LISDynamicLabel = "Block Creation Controller";

							/** Variables for pagination */
							$scope.offset = 0;
							$scope.noOfRecordsPerPage = 10;
							$scope.restainRequestWorkList = [];
							$scope.totalNoOfRecords;
							$scope.searchKeyword;
							$scope.commonListCount;

							$scope.orgId = 1;
							$scope.orgUnitId = 1;
							$scope.createdBy = 1;
							$scope.updatedBy = 1;
							$scope.deptId = LAB_DEPT ;
							$scope.subDeptId = HISTOPATHOLOGY_DEPT;
							
							//$scope.visityTypeList = [];
							//$scope.specimanTypeList =[];
							//$scope.testList = [];
							
							//$scope.selectedVisitType = [];
							//$scope.selectedspecimanTypeList = [];
							//$scope.selectedtestList = [];
							/**DatePicker Code  */
							
							$scope.fromDate = moment().startOf('day').toDate();
						    $scope.toDate = moment().startOf('day').toDate();

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

							   
							    
							   /* $scope.selectedItems = [];
							    $scope.dropDownSetting = {
							    		scrollableHeight: '100px', 
							    		scrollable: true
							    };  */
							    
							
							$scope.tRestainRequestMaster =  {
									  "tRestainingReqId" : "",
									  "orgId" : $scope.orgId,
									  "orgUnitId" : $scope.orgUnitId,
									  "labSampleDtlsId" : "",
									  "tSubSpecimanId" : "",
								      "createdBy" : $scope.createdBy,
									  "createdDate" : "",
									  "updatedBy" : "",
									  "updatedDate" : "",
									  "isDeleted" : "N",
									  "histopathlogyNumber" : "",
									  "subSpcimanNo" : "",
									  "subSpecimanBarcode" : "",
									  "specimanId" : "",
									  "specimanName" : "",
									  "uhid" : "",
									  "genderName" : "",
									  "patientDetails" : "",
									  "doctorDetails" : "",
									  "dob" : "",
									  "age" : 0,
									  "visitType" : "",
									  "specimanType" : "",
									  "specimanTypeId" : "",
									  "listTRestainingRequestDetailsMaster" : []
									}
						
							/** Service Autocomplete List * *//*
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
											+ LIS_HISTOPATHOLOGY
											+ HISTO_MICRO_PATIENT_SEARCH
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
										$scope.visityTypeList =  response.data.listObject;
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
							
							$scope.selectPatient = function($item, $model,$label)
									{
								      $scope.selectedPatient = $item.id;
							        }
							
                            $scope.searchWorkList = function()
							{
								try 
								{
									var searchObj = {
											"orgId":$scope.orgId,
											"orgUnitId":$scope.orgUnitId,
		                            		"fromDate": moment($scope.fromDate.getTime()).format('YYYY-MM-DD') ,
		                            		"toDate":  moment($scope.toDate.getTime()).format('YYYY-MM-DD'),
		                            		"patientId": $scope.selectedPatient,
		                            		"visitTypes":$scope.selectedVisitType,
		                            		"specimanTypes":$scope.selectedspecimanTypeList,
		                            		"testTypes":$scope.selectedtestList,
		                             }
									console.log("microscopicExaminationList",JSON.stringify(searchObj));
									var URI = BASE_URL 
									        + ROOT_URL
											+ LIS_HISTOPATHOLOGY
											+ HISTO_MICRO_SEARCH_MICRO_ORGANISM_LIST
									console.log("URI", URI);
									 GenericService.serviceAction("POST", URI,
											 searchObj).then(function(response) {
										console.log("microscopicExaminationList",JSON.stringify(response.data.listObject));
										$scope.microscopicExaminationList =  response.data.listObject;
									});
								} catch (e) {
									console.log(e.message)
								}
							}*/
							

						
							    
							       
							$scope.setNoOfRecords = function() {
								$scope.initRestainRequestWorkList($scope.orgId,
										$scope.orgUnitId,$scope.deptId,$scope.subDeptId,$scope.offset,
										$scope.noOfRecordsPerPage);
							};

							$scope.initRestainRequestWorkList = function(orgId,
									orgUnitId,deptId,subDeptId, offset, noOfRecordsPerPage) {
								try {
									$rootScope.startLoader();
									var data = {
											"orgId":orgId,
											"orgUnitId":orgUnitId,
											"offset":offset,
											"recordPerPage":noOfRecordsPerPage,
											"deptId":$scope.deptId,
											"subDeptId":$scope.subDeptId
											
										};
									var data1 = {
											"orgId":orgId,
											"orgUnitId":orgUnitId,
											"deptId":$scope.deptId,
											"subDeptId":$scope.subDeptId
										};
									offset = offset != null ? offset : 0;
									noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
											: 10;
									var URI1 = BASE_URL 
									        + ROOT_URL
											+ LIS_HISTOPATHOLOGY
											+ HISTO_RESTAIN_REQUEST_WORKLIST;

									var URI2 = BASE_URL
											+ ROOT_URL
											+ LIS_HISTOPATHOLOGY
											+ HISTO_RESTAIN_REQUEST_WORKLIST_COUNT;

									console.log("URI1", URI1);
									console.log("URI2", URI2);
									promiseFactoryWithObject
											.setPromisesWithObject(URI1, URI2, METHOD_POST,
													METHOD_POST,data,data1)
											.then(
													function(response) {
														$rootScope.stopLoader();
														$scope.restainRequestWorkList = response[0].listObject;
														$scope.commonListCount = response[1].object;
														$scope.setPage(1, false);
														console.log("$scope.restainRequestWorkList",JSON.stringify($scope.restainRequestWorkList));
														console.log("$scope.commonListCount",$scope.commonListCount);
													});
									
								} catch (e) {
									console.log(e.message);
								}
							}

							$scope.initRestainRequestWorkList($scope.orgId,
									$scope.orgUnitId,$scope.deptId,$scope.subDeptId,$scope.offset,
									$scope.noOfRecordsPerPage);
							
           
							$scope.getRestainRequestWorkList = function(orgId,
									orgUnitId,deptId,subDeptId,offset,noOfRecordsPerPage) {
								try {
									$rootScope.startLoader();
									var data = {
											"orgId":orgId,
											"orgUnitId":orgUnitId,
											"offset":offset,
											"recordPerPage":noOfRecordsPerPage,
											"deptId":$scope.deptId,
											"subDeptId":$scope.subDeptId
										};
									var URI = BASE_URL 
											+ ROOT_URL
											+ LIS_HISTOPATHOLOGY
											+ HISTO_RESTAIN_REQUEST_WORKLIST;
									console.log("URI", URI);
									
									GenericService
											.serviceAction(METHOD_POST, URI, data)
											.then(
													function(response) {
														$rootScope.stopLoader();
														$scope.restainRequestWorkList = response.data.listObject;
													});
								} catch (e) {
									console.log(e.message)
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
								$scope.pager = PagerService.GetPager($scope.commonListCount, page,$scope.noOfRecordsPerPage);
								if (flag) 
								{
									$scope.getRestainRequestWorkList($scope.orgId, $scope.orgUnitId,$scope.deptId, $scope.subDeptId,$scope.pager.startIndex,
											$scope.pager.pageSize);
								}
							}

						/*	 //** code for setting all checkboxes *//*
							$scope.checkAll = function() {
								if ($scope.selectedAll) {
									$scope.selectedAll = true;
								} else {
									$scope.selectedAll = false;
								}
								angular
										.forEach(
												$scope.microscopicExaList,
												function(specimanObj) {
													specimanObj.isAccpted = $scope.selectedAll
												});
							 }
							*/
							/** code for generating url * */
							$scope.generateRestainDetailsUrl = function(restainReqMst)
								{
								 $scope.tRestainRequestMaster = restainReqMst;
								 $scope.tRestainRequestMaster.createdBy = $scope.createdBy;
								 $scope.tRestainRequestMaster.orgId = $scope.orgId;
								 $scope.tRestainRequestMaster.orgUnitId = $scope.orgUnitId;
								 $scope.tRestainRequestMaster.listTRestainingRequestDetailsMaster = []; 
											$state.go('restainingRequestWorklistDetails',
												{
												  restainReqMstObj : $scope.tRestainRequestMaster,
												});
								}
							
							
							$scope.getBlockCreationDetails = function(grossObj) {
								try 
								{
									$rootScope.startLoader();
									$scope.histopathlogyNumber = grossObj.histopathlogyNumber;
									$scope.subSpecimanName = grossObj.subSpecimanName;
									$scope.tGrossMaster = grossObj;
									$scope.tGrossMaster.orgId = $scope.orgId;
									$scope.tGrossMaster.orgUnitId = $scope.orgUnitId;
									var URI = BASE_URL 
											+ ROOT_URL
											+ LIS_HISTOPATHOLOGY
											+ HISTO_GET_GROSS_DETAILS;
									console.log("URI", URI);
									
									GenericService
											.serviceAction(METHOD_POST, URI, $scope.tGrossMaster)
											.then(
													function(response)
													{
														if (response.data.status == 'success') 
														{
															$rootScope.stopLoader();
															$scope.listTGrossMaster = response.data.listObject;
															$("#details").modal('show');

														} else if (response.data.status == 'error') 
														{
															$rootScope.stopLoader();
															growl.error("Something went Wrong",	{title : 'Error!'});
														}
													
													});
								} catch (e) {
									console.log(e.message)
								}

							}
							
							 // code for Activating Inactivating Sample
							$scope.saveBlockDetails = function() 
							{
								$scope.$broadcast('show-errors-check-validity');
								if ($scope.blockCreationForm.$valid)
								{
								$rootScope.startLoader();
								try {
									console.log("$scope.listTGrossMaster",JSON.stringify($scope.listTGrossMaster));
									var URI = BASE_URL 
											+ ROOT_URL
											+ LIS_HISTOPATHOLOGY
											+ HISTO_CREATE_BLICKS;
									console.log("CREATE GROSS", URI);
									GenericService
											.serviceAction(METHOD_POST, URI,
													$scope.listTGrossMaster)
											.then(
													function(response) {
														if (response.data.status == 'success') 
														{
															$("#details").modal('hide');
															$scope.initBlockCreationWorklist($scope.orgId,
																	$scope.orgUnitId,$scope.deptId,$scope.subDeptId,$scope.offset,
																	$scope.noOfRecordsPerPage);
															 growl.success(response.data.message,
																{
																	title : 'Success!'
																});
															$scope.$broadcast('show-errors-reset');

														} else if (response.data.status == 'error') 
														{
															$rootScope.stopLoader();
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
						}
							$scope.blockCaptureNoteReq = "Capture note is required.";	

						} ]);