
/**
 * @author Ganesh Chadhari
 */

angular
		.module('myApp')
		.controller(
				"detailsResultValidationController",
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
						'$stateParams',
						function($scope, $rootScope, $state, $cookies,
								GenericService, PagerService, promiseFactoryWithObject,
								$sessionStorage, growl,$stateParams) {

							
							$scope.isRecollected = false;
							$rootScope.loginpage = true;
							$scope.thirdLevel = false;
							
							$scope.LISDynamicLabel = "Sample";

							/** Variables for pagination */
							$scope.offset = 0;
							$scope.noOfRecordsPerPage = 10;
							$scope.resultEntryWorklist  = [];
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
							
							
							$scope.setFirstTabLevel= function(newTab)
							{ 
								$scope.firstTabLevel = newTab;
								$scope.getParameterHistory();
							};
							
							$scope.isSetFirstTabLevel = function(tabNum)
							{
								 
								return $scope.firstTabLevel === tabNum;
							};
							
							/*Second Level*/
							$scope.secondTabLevel = 1 ;
							
							$scope.setSecondTabLevel = function(newTab)
							{
								$scope.secondTabLevel = newTab;
							};
							
							$scope.isSetSecondTabLevel = function(tabNum)
							{
								return $scope.secondTabLevel === tabNum;
							};
							
							
							/*Third Level*/
							$scope.thirdTabLevel = 1 ;
							
							$scope.setThirdTabLevel = function(newTab)
							{
								$scope.thirdTabLevel = newTab;
							};
							
							$scope.isSetThirdTabLevel = function(tabNum)
							{
								return $scope.thirdTabLevel === tabNum;
							};
							
							
							
							/*FORTH Level*/
							$scope.forthTabLevel = 1 ;
							
							$scope.setForthTabLevel = function(newTab)
							{
								$scope.forthTabLevel = newTab;
							};
							
							$scope.isSetForthTabLevel = function(tabNum)
							{
								return $scope.forthTabLevel === tabNum;
							};
							$scope.labResultDetailsMasterDtoDtlsList=[];
							
							$scope.listLabResultEntryDtls  = [];
							$scope.labResultMasterDto = {
									"labTestResId": "",
						            "orgId": $scope.orgId,
						            "orgUnitId":$scope.orgUnitId,
						            "testId": "",
						            "deptId": $scope.deptId,
						            "patientId": "",
						            "visitTypeId": "",
						            "visitAdmId": "",
						            "orderDetailsId": "",
						            "sampleNo": "",
						            "labSampleDtlsId": "",
						            "resultEnterDatetime": "",
						            "resultEnterBy": "",
						            "resultValidatedDatetime": "",
						            "resultValidatedBy": "",
						            "resultAuthorisedDatetime": "",
						            "resultAuthorisedBy": "",
						            "resultAuthorisedFlag": "",
						            "resultHandoverDatetime": "",
						            "resultHandoverBy": "",
						            "suggetionNotes": "",
						            "footsNotes": "",
						            "wardCode":"",
						            "bedNumber":"",
						            "sampleType":"",
						            "testCode":"",
						            "createdBy": $scope.createdBy,
						            "createdDate": "",
						            "updatedBy": $scope.updatedBy,
						            "updatedDate": "",
						            "sampleBarcode": "",
						            "labSampleNo": "",
						            "subDeptId": $scope.subDeptId,
						            "visitType": "",
						            "uhid": "",
						            "patientDetails": "",
						            "doctorDetails": "",
						            "testDesc": "",
						            "priorityName": "",
						            "colorCode": "",
						            "panelCode":"",
						            "orderDateTime":"",
						            "sampleCollectionDatetime":"",
						            "panelId":"",
						            "genderId":"",
						            "resultLevel":"",
						            "sampleStatusId" :"",
						            "listLabSampleResultDetailsMaster":$scope.labResultDetailsMasterDtoDtls
							}

						
							$scope.labResultDetailsMasterDtoDtls={
									"labResDtlsId": "",
									"labResultId": "",
									"parameterId": "",
									"headerId": "",
									"orgId":$scope.orgId,
									"orgUnitId": $scope.orgUnitId,
									"testId": "",
									"paramName": "",
									"paramUnit": "",
									"sampleNo": "",
									"finalResult": "",
									"firstLevelResult": "",
									"secondLevelResult": "",
									"thirdLevelResult": "",
									"resultTypeFlag": "",
									"parameterMin": "",
									"paramAbnrmlMin": "",
									"parameterMax": "",
									"paramAbnrmlMax": "",
									"paramPrintOrder": "",
									"refrangeTypeId":"",
									"textualRangeId":"",
									"multitextaulRange":"",
									"textualRangeName":"",
									"machineId": "",
									"machineResult": "",
									"remarks": "",
									"isDeltaFlag": "",
									"infromationDtls": "",
									"informationType": "",
									"informedTo": "",
									"informedDatetime": "",
									"paramRecheckedBy": "",
									"parameterFormula": "",
									"deltaPer" :"",
									"deltaChange":"",
									"deltaDaysId":"",
									"createdBy": $scope.createdBy,
									"updatedBy": $scope.updatedBy,
									"updatedDate": ""
							}
							
							
							
							$scope.showHelpValue = function(HelpValueMaster)
							{
								$scope.listHelpValueMaster = HelpValueMaster;
								console.log($scope.listHelpValueMaster);
							}
							$scope.setNoOfRecords = function() {
								$scope.initResultEntryList($scope.orgId,
										$scope.orgUnitId,$scope.deptId,$scope.offset,
										$scope.noOfRecordsPerPage);
							};

							
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
						            GenericService.serviceAction("POST", URI, data).then(function(response) 
						            {
						               console.log("response.data.listObject",response.data.listObject);
						               $scope.testList =  response.data.listObject;
						         });
						        } catch (e) {
						         console.log(e.message)
						        }
						       }
						       $scope.getTestList();
						       
						      
						       $scope.dropDownSetting = {
							    		scrollableHeight: '100px', 
							    		scrollable: true
							    };  
						
						       $scope.parameterList = [];
						       $scope.selectedParameters = [];
						       $scope.multiselectObj ={
						    		   "id":"",
						    		   "label":"",
						       }
								$scope.getParameterList = function(testId)
							       {
							        try 
							        {
							        	$scope.parameterList=[];
							         var data = { "orgId":$scope.orgId,
							           "orgUnitId":$scope.orgUnitId,
							           "deptId":$scope.deptId,
							           "subDeptId":$scope.subDeptId,
							           "searchKeyword":"",
							         }
							         var URI = BASE_URL 
							                 + ROOT_URL
							                 + LIS_COMMON
							                 + TEST_PARAMETERS+S+testId+S+$scope.orgId+S+$scope.orgUnitId;
							            console.log("URI", URI);
							            GenericService.serviceAction("GET", URI, data).then(function(response) 
							            {
							            	angular.forEach(response.data.listObject, function(parameterObj, key)
													{
												       var multiselectObj = angular.copy($scope.multiselectObj); 
												       multiselectObj.id = parameterObj.parameterId;
												       multiselectObj.label = parameterObj.parameterName;
												       $scope.parameterList.push(multiselectObj);
													});
							         });
							        } catch (e) {
							         console.log(e.message)
							        }
							       }
							
							
							
							$scope.getBiochemResultEntryDetails = function() 
							{
								var resultEntry = $scope.labResultMasterDto;
								try {
									$scope.labResultDetailsMasterDtoDtlsList =[];
									$scope.isRecollected = resultEntry.sampleRecollectFlag=='Y'?true:false;
									$scope.sampleRecollectAgainstId  = resultEntry.sampleRecollectAgainstId;
									$scope.testType= resultEntry.testType;
									$scope.patientId  = resultEntry.patientId;
									$scope.labSampleDtlsId  =resultEntry.labSampleDtlsId;
									$rootScope.startLoader();
									var data = {
											"orgId":$scope.orgId,
											"orgUnitId":$scope.orgUnitId,
											"testId":resultEntry.testId,
											"testType":resultEntry.testType,
											"deptId":resultEntry.deptId,
											"subDeptId":resultEntry.subDeptId,
											"patientAgeDays":resultEntry.patientAgeDays,
											"labSampleNo":resultEntry.sampleNo,
											"genderId":resultEntry.genderId,
											"panelId":resultEntry.panelId,
											"labResultId":resultEntry.labTestResId
										};
									var dataList = [];
									dataList.push(data);
									var URI = BASE_URL + ROOT_URL
											+ LIS_BIOCHEMISTRY
											+ GET_WORK_LIST_VALIDATION_ENTERY_DETAILS;
									console.log("GET_DETAILS",JSON.stringify(data));
									console.log("URI", URI);
									GenericService
											.serviceAction(METHOD_POST, URI, dataList)
											.then(
													function(response) {
														$rootScope.stopLoader();
														$scope.testMasterResList = response.data.listObject;
														console.log("testMasterResList", $scope.testMasterResList);
														$scope.getPreviousDeltaReport(resultEntry.testId,resultEntry.testType);
													
													});
								} catch (e) {
									$rootScope.stopLoader();
									console.log(e.message)
								}

							}
							
							/**Redirect Page Code*/
							if(angular.equals({},$stateParams.resultMstObj))
 							{
								$state.go('worklistForValidation');
							} else {
								$scope.labResultMasterDto = $stateParams.resultMstObj;
								$scope.getBiochemResultEntryDetails();
							}
							
							
							$scope.labResultMasterDtoList = [];
							$scope.createLabResultObjectList = function(flag)
							{
								angular.forEach($scope.testMasterResList, function(value, key) {
									
									if(value.testType==0)
									{
										  $scope.listLabResultEntryDtls = [];
										  if(flag)
										  {
										    $scope.labResultMasterDto.labTestResId = null;
										  }  
										  $scope.labResultMasterDto.createdBy =$scope.createdBy;
										  $scope.labResultMasterDto.updatedBy =$scope.createdBy;
							    	      $scope.labResultMasterDto.orgId= $scope.orgId;
							    	      $scope.labResultMasterDto.orgUnitId=$scope.orgUnitId;
							    	      value.labResultDetailsViewDto.orgId = $scope.orgUnitId;
							    	      value.labResultDetailsViewDto.orgUnitId  =$scope.orgUnitId;
							    	      value.labResultDetailsViewDto.listHelpValueMaster = [];
							    	      value.labResultDetailsViewDto.listParamRefrengMaster = [];
							    	      if(flag)
										  {
							    	        value.labResultDetailsViewDto.labResDtlsId = null;
							    	        value.labResultDetailsViewDto.labResultId = null;
										  }
									      $scope.listLabResultEntryDtls.push(value.labResultDetailsViewDto);
									      $scope.labResultMasterDto.footsNotes = value.footsNotes;
									      $scope.labResultMasterDto.listLabSampleResultDetailsMaster = $scope.listLabResultEntryDtls;
									      $scope.labResultMasterDto.resultLevel = RESULT_VALIDATION;
									      $scope.labResultMasterDtoList.push($scope.labResultMasterDto);
									}
									else if(value.testType==1)
									{
										$scope.listLabResultEntryDtls  = [];
										 if(flag)
										  {
										    $scope.labResultMasterDto.labTestResId = null;
										  }
										$scope.labResultMasterDto.createdBy=$scope.createdBy;
					    	            $scope.labResultMasterDto.orgId= $scope.orgId;
					    	            $scope.labResultMasterDto.orgUnitId=$scope.orgUnitId;
										angular.forEach(value.listMultyParamTestHeaderDto, function(headerParamObj, key) 
												{
											angular.forEach(headerParamObj.listLabResultDetailsViewDto, function(labResultDetails, key) 
													{
												      labResultDetails.orgId = $scope.orgUnitId;
												      labResultDetails.orgUnitId =$scope.orgUnitId;
												      labResultDetails.listHelpValueMaster = [];
												      labResultDetails.listParamRefrengMaster = [];
												      if(flag)
													  {
												       labResultDetails.labResDtlsId = null;
												       labResultDetails.labResultId = null;
													  }
												      $scope.listLabResultEntryDtls.push(labResultDetails);
												  });
											});
										  $scope.labResultMasterDto.listLabSampleResultDetailsMaster = $scope.listLabResultEntryDtls;
										  $scope.labResultMasterDto.footsNotes = value.footsNotes;
										  $scope.labResultMasterDto.resultLevel = RESULT_VALIDATION;
									      $scope.labResultMasterDtoList.push($scope.labResultMasterDto);
									 }
									
									});
							}
							
							
							$scope.saveResultEntryDetails= function() 
							{
								$scope.$broadcast('show-errors-check-validity');
								if ($scope.worklistForReportEntryForm.$valid)
								{
									try 
									{
										$rootScope.startLoader();
										$scope.labResultMasterDto.sampleStatusId = RESULT_VALIDATION_DONE;
										$scope.createLabResultObjectList(false);
										console.log("labResultMasterDtoList",JSON.stringify($scope.labResultMasterDtoList));
										var URI =BASE_URL + ROOT_URL
										+ LIS_BIOCHEMISTRY
										+ WORK_VALIDATION_ENTERY_DETAILS;
										console.log("URI",URI);
										GenericService.serviceAction("POST", URI, $scope.labResultMasterDtoList).then(function(response) 
										{	
											if (response.data.status == 'success')
											{
												$rootScope.stopLoader();
												growl.success(response.data.message,
														{
															title : 'Success!'
														});
												$state.go('worklistForValidation');

											} else if(response.data.status == 'error')
											{
												$rootScope.stopLoader();
												growl.error(response.data.message,
														{
															title : 'Error!'
														});
											}
										});
										
									} catch (e) {
										$rootScope.stopLoader();
										console.log("Exception",e.message);
									}
									
								}
								else
								{
									growl.error("Please Fill All Mandatory Fields.",
											{
												title : 'Error!'
											});
								}
							}
							
							

							$scope.reTestResultEntryDetails= function() 
							{
									try 
									{
										$rootScope.startLoader();
										$scope.labResultMasterDto.sampleStatusId = RETESTING;
										$scope.createLabResultObjectList(true);
										var URI =BASE_URL + ROOT_URL
										+ LIS_BIOCHEMISTRY
										+ RETEST_WORK_RESULT_ENTERY_DETAILS;
										console.log("URI",URI);
										console.log("labResultMasterDtoList",JSON.stringify($scope.labResultMasterDtoList));
										GenericService.serviceAction("POST", URI, $scope.labResultMasterDtoList).then(function(response) 
										{	
											if (response.data.status == 'success')
											{
												$rootScope.stopLoader();
												growl.success(response.data.message,
														{
															title : 'Success!'
														});
												$state.go('worklistForValidation');

											} else if(response.data.status == 'error')
											{
												$rootScope.stopLoader();
												growl.error(response.data.message,
														{
															title : 'Error!'
														});
											}
										});
									} catch (e) {
										$rootScope.stopLoader();
										console.log("Exception",e.message);
									}
									
							}
							
							$scope.reCollectResultEntryDetails= function() 
							{
									try 
									{
										$rootScope.startLoader();
										$scope.labResultMasterDto.sampleStatusId = SAMPLE_REJECTED;
										$scope.createLabResultObjectList(true);
										var URI =BASE_URL + ROOT_URL
										+ LIS_BIOCHEMISTRY
										+ RECOLLECT_WORK_RESULT_ENTERY_DETAILS;
										console.log("URI",URI);
										console.log("labResultMasterDtoList",JSON.stringify($scope.labResultMasterDtoList));
										GenericService.serviceAction("POST", URI, $scope.labResultMasterDtoList).then(function(response) 
										{	
											if (response.data.status == 'success')
											{
												$rootScope.stopLoader();
												growl.success(response.data.message,
														{
															title : 'Success!'
														});
												$state.go('worklistForValidation');

											} else if(response.data.status == 'error')
											{
												$rootScope.stopLoader();
												growl.error(response.data.message,
														{
															title : 'Error!'
														});
											}
										});
									} catch (e) {
										$rootScope.stopLoader();
										console.log("Exception",e.message);
									}
							}
							
							$scope.getResultEntryDataList = function() {
								try {
									
									$rootScope.startLoader();
									var data = {
											"orgId":$scope.orgId,
											"orgUnitId":$scope.orgUnitId,
											"sampleRecollectAgainstId":$scope.sampleRecollectAgainstId,
											"firstLevelResult":"firstLevel"
										};
									var URI = BASE_URL + ROOT_URL
											+ LIS_BIOCHEMISTRY
											+ GET_WORK_RESULT_ENTERY_DETAILS;
									console.log("URI", URI);

									GenericService
											.serviceAction(METHOD_POST, URI, data)
											.then(
													function(response) {
														$rootScope.stopLoader();
														$scope.resultEntryData  = response.data.listObject;
													});
								} catch (e) {
									console.log(e.message)
								}

							}
							
							$scope.previousResultFlag = false;
							$scope.getLabHistoryReport = function()
							{
								$("#resetDetails").modal('show');
							}
							$scope.selectLabResult = function(index)
							{
								var paramResObj = {
										"parameterId":"",
										"finalResult":"",
								}
								var resultArray = [];
								
								angular.forEach($scope.previousTest.listParameterHistoryDto, function(previousObj, key) 
								    {
									     var paramRes = angular.copy(paramResObj);
									     paramRes.parameterId = previousObj.parameterId;
									     paramRes.finalResult = previousObj.listParameterResultDto[index].result;
									     resultArray.push(paramRes);
									});
								
								$scope.selectResultValue = function(parameterId) {
									$scope.ResultValue = resultArray
											.filter(function(paramObj) {
												if (paramObj.parameterId == parameterId) {
													return paramObj;
												}
											});
								}
									angular.forEach($scope.testMasterResList, function(testObj, key)
										{
										      if(testObj.testType==0)
										    	  {
										    	  $scope.selectResultValue(testObj.labResultDetailsViewDto.parameterId);
										    	  testObj.labResultDetailsViewDto.finalResult = $scope.ResultValue[0].finalResult;
										    	  }
										      else if(testObj.testType==1)
										    	  {
										    	  angular.forEach(testObj.listMultyParamTestHeaderDto, function(headerObj, key)
															{
														       angular.forEach(headerObj.listLabResultDetailsViewDto, function(resultObj, key)
																{
														    	   $scope.selectResultValue(resultObj.parameterId);
														    	   resultObj.finalResult = $scope.ResultValue[0].finalResult;
																});
															});
										    	  }
										        
										});
									$("#resetDetails").modal('hide');
									
							}
							
							$scope.getRetestRecollectTests = function()
							{
								  try {
									   $scope.previousTest = [];
										$rootScope.startLoader();
										var data = {
												 "orgId":$scope.orgId,
												 "orgUnitId":$scope.orgUnitId,
												 "deptId": $scope.deptId,
												 "testId":$stateParams.resultMstObj.testId,
												 "testType":$stateParams.resultMstObj.testType,
												 "labResultId":"",
												 "subDeptId":$scope.subDeptId,
												 "labSampleDtlsId":$stateParams.resultMstObj.labSampleDtlsId,
												 "patientId":$stateParams.resultMstObj.patientId,
												 "resultLevel":RESULT_VALIDATION
												}
										
										var URI = BASE_URL + ROOT_URL
												+ LIS_BIOCHEMISTRY
												+ LAB_PREVIOUS_RESULT_BY_LEVEL;
										
								
										console.log("URI", URI);
										console.log("data",JSON.stringify(data));
										return GenericService
												.serviceAction(METHOD_POST, URI, data)
												.then(
														function(response) {
															$rootScope.stopLoader();
															console.log("$scope.previousTest",JSON.stringify(response.data.listObject));
															return $scope.previousTest  = response.data.listObject;
															
														
														});
									} catch (e) {
										console.log(e.message)
									}
							}
							
							
							$scope.previousTestResultList = [];
							$scope.getPreviousDeltaReport = function(testId,testType)
							{
								var promise =   $scope.getPreviousResultId(testId,testType);
								promise.then(function(resultId) {
									  if(resultId>0)
										  {
										  try {
												$rootScope.startLoader();
												var data = {
														 "orgId":$scope.orgId,
														 "orgUnitId":$scope.orgUnitId,
														 "deptId": $scope.deptId,
														 "testId":$stateParams.resultMstObj.testId,
														 "testType":$stateParams.resultMstObj.testType,
														 "labResultId":$scope.resultEntryId,
														 "subDeptId":$scope.subDeptId,
														 "labSampleDtlsId":""
														}
												
												var URI = BASE_URL + ROOT_URL
														+ LIS_BIOCHEMISTRY
														+ PRIVOUSRESULTBYSAMPLE_DETAILS;
												console.log("data",JSON.stringify(data));
												console.log("URI", URI);

												GenericService
														.serviceAction(METHOD_POST, URI, data)
														.then(
																function(response) {
																	$rootScope.stopLoader();
																	$scope.previousTestResultList  = response.data.listObject;
																	console.log("$scope.previousTestResultList",JSON.stringify($scope.previousTestResultList));
																});
											} catch (e) {
												console.log(e.message)
											}
										  }
								    });
								
								
								  var promiseRetestRecollect =   $scope.getRetestRecollectTests();
								  promiseRetestRecollect.then(function(testObjList) {
										  if(testObjList!=null)
											  {
											  $scope.previousResultFlag = true;
											  }
										  else{
											  $scope.previousResultFlag = false;
										  }
									    });
								
							}
							
							
							
							
							$scope.getPreviousResultId = function(testId,testType)
							{
								try {
								$rootScope.startLoader();
								var data = {
										  "labSampleDtlsId" : "",
										  "orgId" :$scope.orgId,
										  "orgUnitId" :$scope.orgUnitId,
										  "offset" : "",
										  "deptId" : $scope.deptId,
										  "recordPerPage" : "",
										  "testId" :  $stateParams.resultMstObj.testId,
										  "testType" :  $stateParams.resultMstObj.testType,
										  "patientAgeDays" : "",
										  "panelId" : "",
										  "genderId" : "",
										  "labResultId" : "",
										  "headerId" : "",
										  "subDeptId" : $scope.subDeptId,
										  "labSampleNo" : "",
										  "patientId" : $scope.labResultMasterDto.patientId
										}
								var URI = BASE_URL + ROOT_URL
										+ LIS_BIOCHEMISTRY
										+ GET_RESULT_ID_BY_TEST;
								console.log("URI", URI);
								console.log("data",JSON.stringify(data));
							  return GenericService
										.serviceAction(METHOD_POST, URI, data)
										.then(
												function(response) {
													$rootScope.stopLoader();
													return $scope.resultEntryId  = response.data.object;
												});
							} catch (e) {
								console.log(e.message)
							}
							}
							
							 $scope.changeArrowPosition  = function(testIndex,headerIndex,resultIndex,testType)
							 {
							if(testType==0)
								{
								if($scope.testMasterResList[testIndex].labResultDetailsViewDto.finalResult !=null && $scope.testMasterResList[testIndex].labResultDetailsViewDto.refrangeTypeId ==REFERENCE_VALUE)
									{
									  if($scope.testMasterResList[testIndex].labResultDetailsViewDto.finalResult>=$scope.testMasterResList[testIndex].labResultDetailsViewDto.parameterMin 
											  && $scope.testMasterResList[testIndex].labResultDetailsViewDto.finalResult<=$scope.testMasterResList[testIndex].labResultDetailsViewDto.parameterMax)
										  {
										     $scope.testMasterResList[testIndex].labResultDetailsViewDto.arrowClass  =" ";
										  }
									  else if(($scope.testMasterResList[testIndex].labResultDetailsViewDto.finalResult >=$scope.testMasterResList[testIndex].labResultDetailsViewDto.paramAbnrmlMin 
											  && $scope.testMasterResList[testIndex].labResultDetailsViewDto.finalResult >=$scope.testMasterResList[testIndex].labResultDetailsViewDto.parameterMin) &&
											  
											  ($scope.testMasterResList[testIndex].labResultDetailsViewDto.finalResult >=$scope.testMasterResList[testIndex].labResultDetailsViewDto.paramAbnrmlMax 
													  && $scope.testMasterResList[testIndex].labResultDetailsViewDto.finalResult >=$scope.testMasterResList[testIndex].labResultDetailsViewDto.parameterMax))
										  {
										  
												  if($scope.testMasterResList[testIndex].labResultDetailsViewDto.finalResult < $scope.testMasterResList[testIndex].labResultDetailsViewDto.paramAbnrmlMin){
													  $scope.testMasterResList[testIndex].labResultDetailsViewDto.arrowClass  = 'fa fa-arrow-down color-red';
												  }else if($scope.testMasterResList[testIndex].labResultDetailsViewDto.finalResult >$scope.testMasterResList[testIndex].labResultDetailsViewDto.paramAbnrmlMax){
													 
													  $scope.testMasterResList[testIndex].labResultDetailsViewDto.arrowClass  = 'fa fa-arrow-up color-red';
												  }	
										    
										  }else if(($scope.testMasterResList[testIndex].labResultDetailsViewDto.finalResult <=$scope.testMasterResList[testIndex].labResultDetailsViewDto.paramAbnrmlMin 
												  && $scope.testMasterResList[testIndex].labResultDetailsViewDto.finalResult <=$scope.testMasterResList[testIndex].labResultDetailsViewDto.parameterMin) &&
												  
												  ($scope.testMasterResList[testIndex].labResultDetailsViewDto.finalResult <=$scope.testMasterResList[testIndex].labResultDetailsViewDto.paramAbnrmlMax 
														  && $scope.testMasterResList[testIndex].labResultDetailsViewDto.finalResult <=$scope.testMasterResList[testIndex].labResultDetailsViewDto.parameterMax))
											  {
											  
													  if($scope.testMasterResList[testIndex].labResultDetailsViewDto.finalResult < $scope.testMasterResList[testIndex].labResultDetailsViewDto.parameterMin){
														  $scope.testMasterResList[testIndex].labResultDetailsViewDto.arrowClass  = 'fa fa-arrow-down color-red';
													  }else if($scope.testMasterResList[testIndex].labResultDetailsViewDto.finalResult >$scope.testMasterResList[testIndex].labResultDetailsViewDto.parameterMin){
														 
														  $scope.testMasterResList[testIndex].labResultDetailsViewDto.arrowClass  = 'fa fa-arrow-up color-red';
													  }	
											  }
									  else{
											  if($scope.testMasterResList[testIndex].labResultDetailsViewDto.finalResult<$scope.testMasterResList[testIndex].labResultDetailsViewDto.parameterMin){
											
												  $scope.testMasterResList[testIndex].labResultDetailsViewDto.arrowClass  = 'fa fa-arrow-down ';
												
											  }else{
												 
												  $scope.testMasterResList[testIndex].labResultDetailsViewDto.arrowClass  = 'fa fa-arrow-up ';
											  }
									  }
									}
								else if($scope.testMasterResList[testIndex].labResultDetailsViewDto.finalResult !=null && $scope.testMasterResList[testIndex].labResultDetailsViewDto.refrangeTypeId ==TEXTUAL_RANGE)
									{
									   var finalResult = $scope.testMasterResList[testIndex].labResultDetailsViewDto.finalResult.trim().toLowerCase();
									   var textualRangeName = $scope.testMasterResList[testIndex].labResultDetailsViewDto.textualRangeName.trim().toLowerCase();
									   console.log("finalResult.localeCompare(textualRangeName)",finalResult.localeCompare(textualRangeName));
									   if(finalResult===textualRangeName)
									   {
										   $scope.testMasterResList[testIndex].labResultDetailsViewDto.arrowClass  = '';
									   }
									   else
									   {
										   $scope.testMasterResList[testIndex].labResultDetailsViewDto.arrowClass  = 'fa fa-arrow-up ';
									   }
									}
								else if($scope.testMasterResList[testIndex].labResultDetailsViewDto.finalResult !=null && $scope.testMasterResList[testIndex].labResultDetailsViewDto.refrangeTypeId ==MULTIVALUED_RANGE)
									{
									  var finalResult = $scope.testMasterResList[testIndex].labResultDetailsViewDto.finalResult.trim().toLowerCase();
									  var multitextaulRange = $scope.testMasterResList[testIndex].labResultDetailsViewDto.multitextaulRange.trim().toLowerCase();
									  console.log("finalResult.localeCompare(textualRangeName)",finalResult.localeCompare(multitextaulRange));
									  if(finalResult===multitextaulRange)
									  {
										  $scope.testMasterResList[testIndex].labResultDetailsViewDto.arrowClass  = '';
									  }
									  else{
										  $scope.testMasterResList[testIndex].labResultDetailsViewDto.arrowClass  = 'fa fa-arrow-up ';
									  }
									}
								
								
								   if($scope.previousTestResultList.length>0)
									   {
									   
									   console.log("finalResult current",$scope.testMasterResList[testIndex].labResultDetailsViewDto.finalResult);
										console.log("finalResult previos",$scope.previousTestResultList[testIndex].labResultDetailsViewDto.finalResult);
										console.log("refrangeTypeId", $scope.previousTestResultList[testIndex].labResultDetailsViewDto.refrangeTypeId);
										console.log("deltaDaysId", $scope.previousTestResultList[testIndex].labResultDetailsViewDto.deltaDaysId);
										console.log("resultAuthorisedDatetime", $scope.previousTestResultList[testIndex].resultAuthorisedDatetime);
									   var deltaChange = $scope.calculateDeltaPercentage($scope.testMasterResList[testIndex].labResultDetailsViewDto.finalResult,
											   $scope.previousTestResultList[testIndex].labResultDetailsViewDto.finalResult,
											   $scope.previousTestResultList[testIndex].labResultDetailsViewDto.refrangeTypeId,
											   $scope.previousTestResultList[testIndex].labResultDetailsViewDto.deltaDaysId,
											   $scope.previousTestResultList[testIndex].resultAuthorisedDatetime
											   
											   
									   );
									   if((deltaChange>$scope.testMasterResList[testIndex].labResultDetailsViewDto.deltaPer)||(deltaChange==-1))
										   {
										   $scope.testMasterResList[testIndex].labResultDetailsViewDto.isDeltaFlag = 'Y';
										   if($scope.testMasterResList[testIndex].labResultDetailsViewDto.refrangeTypeId ==REFERENCE_VALUE)
											   {
											    $scope.testMasterResList[testIndex].labResultDetailsViewDto.deltaChange = deltaChange;
											   }
										   
										   }
									   else{
										   $scope.testMasterResList[testIndex].labResultDetailsViewDto.isDeltaFlag = 'N';
										   $scope.testMasterResList[testIndex].labResultDetailsViewDto.deltaChange = 0;
									   }
									     
									   }
								
								}
							else if(testType==1)
								{ 
								if($scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].finalResult !=null && $scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].refrangeTypeId ==REFERENCE_VALUE)
								{	
									  
									  if($scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].finalResult>=$scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].parameterMin 
											  && $scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].finalResult<=$scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].parameterMax)
										  {
										  $scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].arrowClass  =" ";
										  }
									  else if(($scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].finalResult >=$scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].paramAbnrmlMin 
											  && $scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].finalResult >=$scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].parameterMin) &&
											  
											  ($scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].finalResult >=$scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].paramAbnrmlMax && $scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].finalResult >=$scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].parameterMax))
										  {
										  
												  if($scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].finalResult < $scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].paramAbnrmlMin){
													  $scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].arrowClass  = 'fa fa-arrow-down color-red';
												  }else if($scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].finalResult >$scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].paramAbnrmlMax){
													 
													  $scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].arrowClass  = 'fa fa-arrow-up color-red';
												  }	
										    
										  }else if(($scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].finalResult <=$scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].paramAbnrmlMin 
												  && $scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].finalResult <=$scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].parameterMin) &&
												  
												  ($scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].finalResult <=$scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].paramAbnrmlMax 
														  && $scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].finalResult <=$scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].parameterMax))
											  {
											  
													  if($scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].finalResult < $scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].parameterMin){
														  $scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].arrowClass  = 'fa fa-arrow-down color-red';
													  }else if($scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].finalResult >$scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].parameterMin){
														 
														  $scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].arrowClass  = 'fa fa-arrow-up color-red';
													  }	
											  }
									  else{
											  if($scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].finalResult<$scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].parameterMin){
											
												  $scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].arrowClass  = 'fa fa-arrow-down ';
												
											  }else{
												 
												  $scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].arrowClass  = 'fa fa-arrow-up ';
											  }
									  }
								}
								else if($scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].finalResult !=null 
										&& $scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].refrangeTypeId ==TEXTUAL_RANGE)
								{
									 var finalResult = $scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].finalResult.trim().toLowerCase();
									 var textualRangeName = $scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].textualRangeName.trim().toLowerCase();
								   if(finalResult===textualRangeName)
									   {
									   $scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].arrowClass  = '';
									   }
								   else{
									   $scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].arrowClass  = 'fa fa-arrow-up ';
								   }
								}
							   else if($scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].finalResult !=null 
									   && $scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].refrangeTypeId ==MULTIVALUED_RANGE)
								{
								   var finalResult = $scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].finalResult.trim().toLowerCase();
									  var multitextaulRange = $scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].multitextaulRange.trim().toLowerCase();
								   if(finalResult===multitextaulRange)
									   {
									    $scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].arrowClass  = '';
									   }
								   else{
									   $scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].arrowClass  = 'fa fa-arrow-up ';
									   
								   }
								}
								if($scope.previousTestResultList.length>0)
								   {
									
									console.log("finalResult current",$scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].finalResult);
									console.log("finalResult previos",$scope.previousTestResultList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].finalResult);
									console.log("refrangeTypeId",$scope.previousTestResultList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].refrangeTypeId);
									console.log("deltaDaysId",$scope.previousTestResultList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].deltaDaysId);
									console.log("resultAuthorisedDatetime",$scope.previousTestResultList[testIndex].resultAuthorisedDatetime);
								   var deltaChange =  $scope.calculateDeltaPercentage(
										   $scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].finalResult,
										   $scope.previousTestResultList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].finalResult,
										   $scope.previousTestResultList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].refrangeTypeId,
										   $scope.previousTestResultList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].deltaDaysId,
										   $scope.previousTestResultList[testIndex].resultAuthorisedDatetime);
								   
								   if((deltaChange> $scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].deltaPer)||(deltaChange==-1))
									   {
									     $scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].isDeltaFlag = 'Y';
									    // $scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].deltaChange = deltaChange;
									   }
								   else
								   {
									   $scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].isDeltaFlag = 'N';
									  // $scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].deltaChange = 0;
								   }
								   }
								
								if($scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].isFormula=='Y')
								{
								  var result = 0.00 ; 
								  var formula  = $scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].parameterFormula;
								  var simplified = math.simplify(math.parse(formula));
								  var formulaDetailsList = $scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].listFormulaDetails;
								  var parameterList = $scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto;
								  var obj = {};
								  angular.forEach(formulaDetailsList, function(value, key)
								   {
									  console.log("paramVariable",value.paramVariable);
									  $scope.paramObject = parameterList.filter(function( paramObj ) 
									     {
										  return paramObj.parameterId == value.parameterId&&paramObj.refrangeTypeId==REFERENCE_VALUE;
										});
									  if($scope.paramObject!=null||$scope.paramObject!=undefined)
										 { 
										   if($scope.paramObject.finalResult==null||$scope.paramObject.finalResult==undefined)
											   {
											    $scope.paramObject.finalResult=0.00;
											   }
										   obj[value.paramVariable.toString()] = parseFloat($scope.paramObject.finalResult);
										 }
								   });
								 var result = simplified.eval(obj);
								  $scope.testMasterResList[testIndex].listMultyParamTestHeaderDto[headerIndex].listLabResultDetailsViewDto[resultIndex].finalResult =result.toFixed(2);
								}
								
								 };
								}
							
							  
							
							 $scope.resultReqMsg = "Please enter result."
								 
								 
					$scope.calculateDeltaPercentage = function(currentResult,previousResult,refrangeTypeId,deltaDaysId,resultValidateDateTime)
					{
						 var deltelPerncetage = "";
						 dt1 = new Date(resultValidateDateTime);
						 dt2 = new Date();
						 $scope.deltaDays =  Math.floor((Date.UTC(dt2.getFullYear(), dt2.getMonth(), dt2.getDate()) - Date.UTC(dt1.getFullYear(), dt1.getMonth(), dt1.getDate()) ) /(1000 * 60 * 60 * 24));
						 if((currentResult!=null&&currentResult!="")&&(previousResult!=null&&previousResult!=""))
							{
							if(refrangeTypeId==REFERENCE_VALUE)
								{
								  if(deltaDaysId!=null&&deltaDaysId>0 && $scope.deltaDays<=deltaDaysId)
								  {
											
												   if(parseFloat(currentResult) > parseFloat(previousResult))
												   {
												     var increase  = currentResult- previousResult;
												     deltelPerncetage = (parseFloat(increase)/parseFloat(previousResult)) * 100;
												   }
												   else if(parseFloat(previousResult) > parseFloat(currentResult)){
													   var decrease  = previousResult - currentResult ;
													   deltelPerncetage = (parseFloat(decrease)/parseFloat(previousResult)) * 100;
												   }
												   var fixedValue = (parseFloat(deltelPerncetage)).toFixed(2);
												   return fixedValue;
								  }
								  else{
									  return "0";
								  }
								}
							else if(refrangeTypeId==TEXTUAL_RANGE)
							{
								if(deltaDaysId!=null&&deltaDaysId>0 && $scope.deltaDays<=deltaDaysId)
									{
									  var textualCurrentResult = currentResult.trim().toLowerCase();
									  var textualPreviousResult = previousResult.trim().toLowerCase();
										 if(textualCurrentResult===textualPreviousResult)
										 {
										    return 0;
										 }
									 else
									  {
										 return -1
									  }
									} 
								 else{
									  return "0";
								  }
							}
							else if(refrangeTypeId==MULTIVALUED_RANGE)
							{
								if(deltaDaysId!=null&&deltaDaysId>0 && $scope.deltaDays<=deltaDaysId)
								{
									 var mTextualCurrentResult = currentResult.trim().toLowerCase();
									 var mTextualPreviousResult = previousResult.trim().toLowerCase();
									 if(mTextualCurrentResult===mTextualPreviousResult)
										 {
										    return 0;
										 }
									 else{
										 return -1
									 }
									 
								}
								 else{
									  return "0";
								  }
							}
						}
						 else{
							 growl.error("Something went wrong",
										{
											title : 'Error!'
										});
						 }
					}
							 
							 
						 	 
							 
							 
							 $scope.labels = [];
							 $scope.data = [];
							 $scope.paramHistoryList =[];
							 
							  $scope.onClick = function (points, evt) {
							    console.log(points, evt);
							  };
							  $scope.datasetOverride = [{
							        yAxisID: 'y-axis-1'
							    }];
							    $scope.options = {
							        scales: {
							            yAxes: [{
							                id: 'y-axis-1',
							                type: 'linear',
							                display: true,
							                position: 'left'
							            }]
							        },legend: { display: true }
							    };
							 
							    
							    $scope.selectedParameterList=[];
								$scope.getParameterHistory = function() 
								{  
									 $scope.series = [];
									 $scope.labels = [];
									 $scope.data = [];
									 $scope.paramHistoryList =[];
									try {
										
										$rootScope.startLoader();
										var data = 
										         { 
										          "orgUnitId":$scope.orgUnitId,
												   "orgId":$scope.orgId,
												   "patientId":$scope.labResultMasterDto.patientId,
												   "testId":$scope.testId,
												   "limit":$scope.limit,
												   "parameterIds":$scope.selectedParameterList
												 }
										var URI = BASE_URL + ROOT_URL
												+ LIS_BIOCHEMISTRY
												+ MICROBIOLOGY_PARAMETER_HISTORY;
										console.log("URI", URI);

										GenericService
												.serviceAction(METHOD_POST, URI, data)
												.then(
														function(response) {
															$rootScope.stopLoader();
															$scope.paramHistoryList  = response.data.listObject;
															angular.forEach($scope.paramHistoryList, function(value, key)
																	{
																     if(value.refrangeTypeId==REFERENCE_VALUE)
																    	 {
																    	   $scope.series.push(value.parameterName);
																    	   var graphResult = [];
																			angular.forEach(value.listParameterResultDto, function(paramResObj, key)
																					{
																				       if(!isNaN(parseFloat(paramResObj.result)))
																				    	   {
																				    	     graphResult.push(parseFloat(paramResObj.result));
																				    	     
																				    	   }
																					});
																			 
																			$scope.data.push(graphResult);
																    	 }
																	});
															angular.forEach($scope.paramHistoryList[0].listParameterResultDto, function(dateObj, key)
																	{
																        $scope.labels.push(moment(dateObj.resultDateTime).format('DD/MM/YYYY'));
																	});
															
															console.log(JSON.stringify($scope.labels));
															console.log(JSON.stringify($scope.data));
															console.log(JSON.stringify($scope.series));
														});
									} catch (e) {
										console.log(e.message)
									}
									//}
								}	 
								
							
						} ]);