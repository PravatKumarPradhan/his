
/**
 * @author Ganesh
 */

angular
		.module('myApp')
		.controller(
				"slideCreationController",
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
							$scope.LISDynamicLabel = "Slide Creation Controller";

							/** Variables for pagination */
							$scope.offset = 0;
							$scope.noOfRecordsPerPage = 10;
							$scope.slideCreationList = [];
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
							    
							
							       $scope.listTSlideMaster = [];
							       $scope.listTGrossMaster = [];
							       $scope.tGrossMaster ={
		                            		  "tGrossId" : "",
		                            		  "orgId" : $scope.orgId,
		                            		  "orgUnitId" : $scope.orgUnitId,
		                            		  "tSubSpecimanId" : "",
		                            		  "labSampleDtlsId" : "",
		                            		  "grossBarcode" : "",
		                            		  "grossNo" : "",
		                            		  "grossDesc" : "",
		                            		  "captureNote" : "",
		                            		  "createdBy" : $scope.createdBy,
		                            		  "createdDate" : "",
		                            		  "updatedBy" : "",
		                            		  "updatedDate" : "",
		                            		  "isDeleted" : "N",
		                            		  "listTSlideMaster" : $scope.listTSlideMaster,
		                            		  "listTRestainingRequestDetailsMasterDto" :[]
		                            		}
							    
							    
							    
							    $scope.tSlideMaster = {
							    		  "tSlideId" : "",
							    		  "tBlockId":"",
							    		  "orgId" : $scope.orgId,
							    		  "orgUnitId" : $scope.orgUnitId,
							    		  "tSubSpecimanId":"",
							    		  "tGrossId" : "",
							    		  "labSampleDtlsId" : "",
							    		  "stainingId":"",
							    		  "slideBarcode":"",
							    		  "slideNo" : "",
							    		  "slideNum":"",
							    		  "captureNote" : "",
							    		  "createdBy" : $scope.createdBy,
							    		  "createdDate" : "",
							    		  "updatedBy" : "",
							    		  "updatedDate" : "",
							    		  "isDeleted" : "N",
							    		  "isRestained":"N",
							    		  "restainAgainstTslideid":"",
							    		  "tRestainingSubDetailId":"",
							    		  "listTMicroExaDetails" : []
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
							       
							       $scope.findMaxSlideNumber = function(inputList)
	                                {
	                            	  if(Array.isArray(inputList))
	                            		  {
	                            		  if(inputList.length>0)
	                              		    {
	                              		      return Math.max.apply(
	                              		    		   Math,inputList.map(function(o)
	                              		    				   {
	                              		    			         return parseInt(o.slideNum)+1;
	                              		    			         }
	                              		    		        ));
	                              		    }
	                            		  else
	                              	  		{
	                            			  return 1;
	                              	  		}
	                            		  }
	                               }
							

							$scope.addSlide = function(grossIndex,blockIndex,slideIndex)
							{
								//$scope.listTGrossMaster[grossIndex].listTBlockMaster[blockIndex].listTSlideMaster =
								//	$scope.listTGrossMaster[grossIndex].listTBlockMaster[blockIndex].listTSlideMaster==null?[]:$scope.listTGrossMaster[grossIndex].listTBlockMaster[blockIndex].listTSlideMaster;
							  if($scope.listTGrossMaster[grossIndex].listTBlockMaster[blockIndex].listTSlideMaster.length==0)
								  {
								   console.log("slideNumber",$scope.slideNumber);
								    $scope.listTSlideMaster = [];
								    var tSlideMaster = angular.copy($scope.tSlideMaster);
								    tSlideMaster.slideNum =  $scope.findMaxSlideNumber([]);
								    var slideNo  = voucher_codes.generate({
	                    			    prefix:$scope.listTGrossMaster[grossIndex].listTBlockMaster[blockIndex].blockNo+SLIDE_PREFIX,
	                    			    inputNumber: tSlideMaster.slideNum,
	                    			    length:NUMBER_WIDTH
	                    			})[0];
								    tSlideMaster.slideNo = slideNo;
								    $scope.listTGrossMaster[grossIndex].listTBlockMaster[blockIndex].listTSlideMaster= 
								    	$scope.listTGrossMaster[grossIndex].listTBlockMaster[blockIndex].listTSlideMaster==null?[]:$scope.listTGrossMaster[grossIndex].listTBlockMaster[blockIndex].listTSlideMaster;
								    	$scope.listTGrossMaster[grossIndex].listTBlockMaster[blockIndex].listTSlideMaster.push(tSlideMaster);
								  }
							  else{
								    var tSlideMaster = angular.copy($scope.tSlideMaster);
								    tSlideMaster.slideNum =  $scope.findMaxSlideNumber($scope.listTGrossMaster[grossIndex].listTBlockMaster[blockIndex].listTSlideMaster);
								    var slideNo  = voucher_codes.generate({
	                    			    prefix:$scope.listTGrossMaster[grossIndex].listTBlockMaster[blockIndex].blockNo+SLIDE_PREFIX,
	                    			    inputNumber: tSlideMaster.slideNum,
	                    			    length:NUMBER_WIDTH
	                    			})[0];
								    tSlideMaster.slideNo =  slideNo;
								    $scope.listTGrossMaster[grossIndex].listTBlockMaster[blockIndex].listTSlideMaster= 
								    	$scope.listTGrossMaster[grossIndex].listTBlockMaster[blockIndex].listTSlideMaster==null?[]:$scope.listTGrossMaster[grossIndex].listTBlockMaster[blockIndex].listTSlideMaster;
								    	$scope.listTGrossMaster[grossIndex].listTBlockMaster[blockIndex].listTSlideMaster.push(tSlideMaster);
							    }
							  
							  
							}
							       
							   	$scope.removeSlide = function(grossIndex,blockIndex,slideIndex)
								{
							   		$scope.listTGrossMaster[grossIndex].listTBlockMaster[blockIndex].listTSlideMaster.splice(slideIndex,1);
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
											+ HISTO_SLIDE_CREATION;

									var URI2 = BASE_URL
											+ ROOT_URL
											+ LIS_HISTOPATHOLOGY
											+ HISTO_SLIDE_CREATION_COUNT;

									console.log("URI1", URI1);
									console.log("URI2", URI2);
									promiseFactoryWithObject
											.setPromisesWithObject(URI1, URI2, METHOD_POST,
													METHOD_POST,data,data1)
											.then(
													function(response) {
														$rootScope.stopLoader();
														$scope.slideCreationList = response[0].listObject;
														$scope.commonListCount = response[1].object;
														$scope.setPage(1, false);
														console.log("$scope.slideCreationList",JSON.stringify($scope.slideCreationList));
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
														$scope.slideCreationList = response.data.listObject;
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

							 //** code for setting all checkboxes *//*
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
							
							/** code for generating url * */
							$scope.generateCreateGrossUrl = function(microScopwrk)
								{
								$scope.tSpecimanMaster = microScopwrk;
											$state.go('macroscopicExamination',
												{
													microScopeObj : $scope.tSpecimanMaster,
												});
								}
							
							
							$scope.getMicroscopicExamWorkDetails = function(slideObj) {
								try 
								{
									$rootScope.startLoader();
									$scope.histopathlogyNumber = slideObj.histopathlogyNumber;
									$scope.subSpecimanName = slideObj.subSpecimanName;
									$scope.tGrossMaster = slideObj;
									$scope.tGrossMaster.orgId = $scope.orgId;
									$scope.tGrossMaster.orgUnitId = $scope.orgUnitId;
									var URI = BASE_URL 
											+ ROOT_URL
											+ LIS_HISTOPATHOLOGY
											+ HISTO_GET_SLIDE_DETAILS;
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
															console.log("$scope.listTGrossMaster",JSON.stringify($scope.listTGrossMaster));
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
							
							/*
							 *  Get Stain List 
							 */
							$scope.getStainingList = function() {
								try {
									var data = "";
									var URI = BASE_URL + ROOT_URL
									        + LIS_COMMON
											+ GET_STAINING_LIST
											+ $scope.orgId;
									console.log("URI", URI);
									GenericService
											.serviceAction("GET", URI, data)
											.then(
													function(response) {
														$scope.stainingList  = response.data.listObject;
														console.log("$scope.stainingList",JSON.stringify($scope.stainingList));
													});
								} catch (e) {
									console.log(e.message)
								}

							}
							
							$scope.getStainingList();
							
							 // code for Activating Inactivating Sample
							$scope.saveSlideDetails = function() 
							{
								$scope.$broadcast('show-errors-check-validity');
								if ($scope.slideCreationForm.$valid)
								{
								$rootScope.startLoader();
								try {
									var URI = BASE_URL 
											+ ROOT_URL
											+ LIS_HISTOPATHOLOGY
											+ HISTO_CREATE_SLIDE;
									console.log("CREATE GROSS", URI);
									console.log("$scope.listTGrossMaster",JSON.stringify($scope.listTGrossMaster));
									GenericService
											.serviceAction(METHOD_POST, URI,
													$scope.listTGrossMaster)
											.then(
													function(response) {
														if (response.data.status == 'success') 
														{
															$("#details").modal('hide');
															$scope.initSlideCreationWorklist($scope.orgId,
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
							$scope.stainingReq = "Please Select Stain .";

						} ]);