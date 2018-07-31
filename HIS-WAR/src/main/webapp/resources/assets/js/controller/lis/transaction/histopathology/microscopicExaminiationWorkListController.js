
/**
 * @author Ganesh
 */

angular
		.module('myApp')
		.controller(
				"microscopicExaminiationWorkListController",
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
							$scope.LISDynamicLabel = "microscopic Examiniation WorkList Controller";

							/** Variables for pagination */
							$scope.offset = 0;
							$scope.noOfRecordsPerPage = 10;
							$scope.microscopicExaminiationWorkList = [];
							$scope.totalNoOfRecords;
							$scope.searchKeyword;
							$scope.commonListCount;

							$scope.orgId = 1;
							$scope.orgUnitId = 1;
							$scope.createdBy = 1;
							$scope.updatedBy = 1;
							$scope.deptId = LAB_DEPT ;
							$scope.subDeptId = HISTOPATHOLOGY_DEPT;
							
							$scope.visityTypeList = [];
							$scope.specimanTypeList =[];
							$scope.testList = [];
							
							$scope.selectedVisitType = [];
							$scope.selectedspecimanTypeList = [];
							$scope.selectedtestList = [];
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

							   
							    
							    $scope.selectedItems = [];
							    $scope.dropDownSetting = {
							    		scrollableHeight: '100px', 
							    		scrollable: true
							    };  
							    
							
							       $scope.listTBlockMaster = [];
							       $scope.listTMicroExaDetails = [];
							       $scope.tGrossMaster ={
		                            		  "tGrossId" : "",
		                            		  "orgId" : $scope.orgId,
		                            		  "orgUnitId" : $scope.orgUnitId,
		                            		  "tSubSpecimanId" : "",
		                            		  "labSampleDtlsId" : "",
		                            		  "grossBarcode" : "",
		                            		  "grossNo" : "",
		                            		  "grossNum" : "",
		                            		  "grossDesc" : "",
		                            		  "captureNote" : "",
		                            		  "createdBy" : $scope.createdBy,
		                            		  "createdDate" : "",
		                            		  "updatedBy" : "",
		                            		  "updatedDate" : "",
		                            		  "isDeleted" : "N",
		                            		  "listTBlockMaster" : $scope.listTBlockMaster,
		                            		  "listTRestainingRequestDetailsMasterDto" :[]
		                            		}
							    
							    
							    
							    $scope.tBlockMaster = {
							    		  "tBlockId" : "",
							    		  "orgId" : $scope.orgId,
							    		  "orgUnitId" : $scope.orgUnitId,
							    		  "tGrossId" : "",
							    		  "labSampleDtlsId" : "",
							    		  "blockBarcode" : "",
							    		  "blockNo" : "",
							    		  "blockNum" : "",
							    		  "captureNote" : "",
							    		  "createdBy" : $scope.createdBy,
							    		  "createdDate" : "",
							    		  "updatedBy" : "",
							    		  "updatedDate" : "",
							    		  "isDeleted" : "N",
							    		  "listTSlideMaster" : []
							    		}
						
							       $scope.tTMicroExaDetails={
							    		   "tMicroExaId": "",
							    		   "orgId" : $scope.orgId,
		                            	   "orgUnitId" : $scope.orgUnitId,
							               "tSlideId": "",
							               "slideNo" :"",
							               "labSampleDtlsId": "",
							               "stainingId": "",
							               "isComplete": "N",
							               "captureNote": "",
							               "sendForStorage": "N",
							               "createdBy" : $scope.createdBy,
							               "createdDate": "",
							               "updatedBy": "",
							               "updatedDate": "",
							               "isDeleted": "N",
							               "isRestained":"N",
							               "subSpecimanName": "",
							               "grossNo": "",
							               "blockNo": "",
							               "stainName": "",
							               "isExaComplete":""
							       }
							       
							       $scope.tSubSpecimanMaster ={
		                            		  "tSubSpecimanId" : "",
		                            		  "orgId" : $scope.orgId,
		                            		  "orgUnitId" : $scope.orgUnitId,
		                            		  "subSpecimanName":"",
		                            		  "tSpecimanId" : "",
		                            		  "subSpecimanExaminination" : "",
		                            		  "labSampleDtlsId" : "",
		                            		  "subSpcimanNo" : "",
		                            		  "subSpecimanBarcode" : "",
		                            		  "captureNote" : "",
		                            		  "createdBy" : $scope.createdBy,
		                            		  "createdDate" : "",
		                            		  "updatedBy" : "",
		                            		  "updatedDate" : "",
		                            		  "isDeleted" : "N",
		                            		  "specimanTypeId" : "",
		                            		  "specimanId" : "",
		                            		  "specimanName" : "",
		                            		  "subSpecimanNum" : "",
		                            		  "uhid" : "",
		                            		  "genderName" : "",
		                            		  "patientDetails" : "",
		                            		  "doctorDetails" : "",
		                            		  "dob" : "",
		                            		  "age" : 0,
		                            		  "templateResId":"",
		                            		  "listTGrossMaster" : []
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
							

							$scope.addBlocks = function(grossIndex,index)
							{
							    if(index==0)
						    	{
						    	    index=1;
						    	    $scope.blockNumber = index;
						    	    $scope.blockNumber++;
						    	}
							    else if(index==undefined)
							    	{
							    	   index=0;
							    	   $scope.blockNumber = index;
							    	   $scope.blockNumber++;
							    	}
							    else
							    	{
							    	  $scope.blockNumber = ++index;
							    	  $scope.blockNumber++;
							    	}
							  
							    console.log("blockNumber",$scope.blockNumber);
							    $scope.listTBlockMaster = [];
							    var tBlockMaster = angular.copy($scope.tBlockMaster);
							    tBlockMaster.blockNo =  $scope.listTMicroExaDetails[grossIndex].grossNo+"-B"+$scope.blockNumber;
							    $scope.listTMicroExaDetails[grossIndex].listTBlockMaster= $scope.listTMicroExaDetails[grossIndex].listTBlockMaster==null?[]:$scope.listTMicroExaDetails[grossIndex].listTBlockMaster;
							    $scope.listTMicroExaDetails[grossIndex].listTBlockMaster.push(tBlockMaster);
							    console.log("$scope.listTMicroExaDetails",$scope.listTMicroExaDetails);
							}
							       
							   	$scope.removeBlock = function(grossIndex,index)
								{
								    $scope.listTMicroExaDetails[grossIndex].listTBlockMaster.splice(index,1);
								    console.log("$scope.listTMicroExaDetails",$scope.listTMicroExaDetails);
								}
							    
							       
							$scope.setNoOfRecords = function() {
								$scope.initSlideCreationWorklist($scope.orgId,
										$scope.orgUnitId,$scope.deptId,$scope.subDeptId,$scope.offset,
										$scope.noOfRecordsPerPage);
							};

							$scope.initSlideCreationWorklist = function(orgId,
									orgUnitId,deptId,subDeptId, offset, noOfRecordsPerPage) {
								try {
									$rootScope.startLoader();
									var data = {
											"orgId":orgId,
											"orgUnitId":orgUnitId,
											"offset":offset,
											"recordPerPage":noOfRecordsPerPage,
											"deptId":$scope.subDeptId,
											"subDeptId":$scope.subDeptId
											
										};
									var data1 = {
											"orgId":orgId,
											"orgUnitId":orgUnitId,
											"deptId":$scope.subDeptId,
											"subDeptId":$scope.subDeptId
										};
									offset = offset != null ? offset : 0;
									noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
											: 10;
									var URI1 = BASE_URL 
									        + ROOT_URL
											+ LIS_HISTOPATHOLOGY
											+ HISTO_MICRO_EXAM_WORK_LIST_CREATION;

									var URI2 = BASE_URL
											+ ROOT_URL
											+ LIS_HISTOPATHOLOGY
											+ HISTO_MICRO_EXAM_WORK_LIST_CREATION_COUNT;

									console.log("URI1", URI1);
									console.log("URI2", URI2);
									promiseFactoryWithObject
											.setPromisesWithObject(URI1, URI2, METHOD_POST,
													METHOD_POST,data,data1)
											.then(
													function(response) {
														$rootScope.stopLoader();
														$scope.microscopicExaminiationWorkList = response[0].listObject;
														$scope.commonListCount = response[1].object;
														$scope.setPage(1, false);
														console.log("$scope.microscopicExaminiationWorkList",JSON.stringify($scope.microscopicExaminiationWorkList));
														console.log("$scope.commonListCount",$scope.commonListCount);
													});
									
								} catch (e) {
									console.log(e.message);
								}
							}

							$scope.initSlideCreationWorklist($scope.orgId,
									$scope.orgUnitId,$scope.deptId,$scope.subDeptId,$scope.offset,
									$scope.noOfRecordsPerPage);
							
           
							$scope.getSlideCreationWorklist = function(orgId,
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
											+ HISTO_BLOCK_CREATION;
									console.log("URI", URI);
									
									GenericService
											.serviceAction(METHOD_POST, URI, data)
											.then(
													function(response) {
														$rootScope.stopLoader();
														$scope.microscopicExaminiationWorkList = response.data.listObject;
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
									$scope.getSlideCreationWorklist($scope.orgId, $scope.orgUnitId,$scope.deptId, $scope.subDeptId,$scope.pager.startIndex,
											$scope.pager.pageSize);
								}
							}

							/* //** code for setting all checkboxes *//*
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
							 }*/
							
							/** code for generating url * */
											
							$scope.generateReportCreationUrl = function(tSubSpecimanMaster)
							{
							  if(tSubSpecimanMaster!=null)
								  {
									 $scope.tSubSpecimanMaster = tSubSpecimanMaster;
									 $scope.tSubSpecimanMaster.orgId = $scope.orgId;
									 $scope.tSubSpecimanMaster.orgUnitId = $scope.orgUnitId;
									 $scope.tSubSpecimanMaster.createdBy = $scope.createdBy;
									 $scope.tSubSpecimanMaster.listTGrossMaster = [];
												$state.go('reportCreationRelease',
													{
													  subSpecimenObj : $scope.tSubSpecimanMaster,
													});
								  }
							  else
							     {
								    growl.error("Something went Wrong.",{title : 'Error!'});
							     }
						
							}
							
							
							$scope.genrateSlideDetilsUrl = function(tSubSpecimanMaster)
							{		 
								     $scope.tSubSpecimanMaster = tSubSpecimanMaster;
									 $scope.tSubSpecimanMaster.orgId = $scope.orgId;
									 $scope.tSubSpecimanMaster.orgUnitId = $scope.orgUnitId;
									 $scope.tSubSpecimanMaster.createdBy = $scope.createdBy;
									 $scope.tSubSpecimanMaster.listTGrossMaster = [];
												$state.go('detailsMicroscopicExaminiation',
													{
													  subSpecimenObj : $scope.tSubSpecimanMaster,
													});
							}
							
						/*	$scope.getSlideCreationDetails = function(slideObj) {
								try 
								{
									$rootScope.startLoader();
									$scope.histopathlogyNumber = slideObj.histopathlogyNumber;
									$scope.subSpecimanName = slideObj.subSpecimanName;
									$scope.tSubSpecimanMaster = slideObj;
									$scope.tSubSpecimanMaster.orgId = $scope.orgId;
									$scope.tSubSpecimanMaster.orgUnitId = $scope.orgUnitId;
									
									var URI = BASE_URL 
											+ ROOT_URL
											+ LIS_HISTOPATHOLOGY
											+ HISTO_GET_EXAMINIATION_DETAILS;
									console.log("URI", URI);
									
									GenericService
											.serviceAction(METHOD_POST, URI, $scope.tSubSpecimanMaster)
											.then(
													function(response)
													{
														if (response.data.status == 'success') 
														{
															$rootScope.stopLoader();
															$scope.listTMicroExaDetails = response.data.listObject;
															console.log("$scope.listTMicroExaDetails",JSON.stringify($scope.listTMicroExaDetails));
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

							}*/
							
							
							/** code for setting all checkboxes */
							$scope.checkAll = function() {
						
								if ($scope.selectedAll) {
									$scope.selectedAll = true;
								} else {
									$scope.selectedAll = false;
								}
								angular
										.forEach(
												$scope.listTMicroExaDetails,
												function(tMicroExaDetails) {
													tMicroExaDetails.isExaComplete = $scope.selectedAll;
												});
							}
							
							
							$scope.microscopicCaptureNoteReq = "Capture note is required.";	

						} ]);