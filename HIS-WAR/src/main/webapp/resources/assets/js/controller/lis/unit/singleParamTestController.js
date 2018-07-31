/**
 * @author Ganesh Chaudhari
 */

angular
		.module('myApp')
		.controller(
				"singleParameterTestController",
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
							$scope.LISDynamicLabel = "";
							$scope.saveBtnFlag = true;
							$scope.updateBtnFlag = false;
							$scope.updateScope = false;
							
							$scope.deltaNo = true;
							$scope.deltaText = false;

							/** Variables for pagination */
							$scope.offset = 0;
							$scope.noOfRecordsPerPage = 10;
							$scope.totalNoOfRecords;
							$scope.searchKeyword;
							$scope.singleParamTestMasterListCount;

							$scope.orgId = 1;
							$scope.orgUnitId = 1;
							$scope.createdBy = 1;
							$scope.updatedBy = 1;
							$scope.deptId = 8;
							$scope.isHistoCyto="N";

							/* code for setting Label */
							$(".selectedPageName").text(
									"Single Parameter Test Master");

							
							$scope.singleParamTestList = [];
							$scope.departmentList;
							$scope.sampleList;
							$scope.unitList;
							$scope.containerList;
							$scope.reportList;
							$scope.techniqueList;
							$scope.prerequisitesList;
							$scope.timeList;
							$scope.noOfDayList;
							$scope.ageGroupList;
							$scope.genderList;

							$scope.getDepartMentList = function(orgId) {
								try {
									var data = "";
									var URI = BASE_URL + ROOT_URL
											+ LIS_COMMON + GET_DEPARMENT_LIST
											+ S + orgId;
									console.log("URI", URI);
									GenericService.serviceAction("GET", URI, data)
											  .then(function(response) {
														$scope.departmentList = response.data.listObject;
													});
								   } catch (e) {
									console.log(e.message)
								}
							}
							$scope.getDepartMentList($scope.orgId);
							
							
							$scope.getSampleList = function(orgId) {
								try {
									var data = "";
									var URI = BASE_URL + ROOT_URL
											+ LIS_COMMON + GET_SAMPLETYPE_LIST
											+ S + orgId;
									console.log("URI", URI);
									GenericService
											.serviceAction("GET", URI, data)
											.then(
													function(response) {
														$scope.sampleList = response.data.listObject;
													});
								} catch (e) {
									console.log(e.message)
								}
							}
							$scope.getSampleList($scope.orgId);

							$scope.getUnitList = function(orgId) {
								try {
									var data = "";
									var URI = BASE_URL + ROOT_URL
											+ LIS_COMMON + GET_UNIT_LIST + S
											+ orgId;
									console.log("URI", URI);
									GenericService
											.serviceAction("GET", URI, data)
											.then(
													function(response) {
														$scope.unitList = response.data.listObject;
													});
								} catch (e) {
									console.log(e.message)
								}
							}
							$scope.getUnitList($scope.orgId);
							
							$scope.getContainerList = function(orgId) {
								try {
									var data = "";
									var URI = BASE_URL + ROOT_URL
											+ LIS_COMMON + GET_CONTAINER_LIST + S
											+ orgId;
									console.log("URI", URI);
									GenericService.serviceAction("GET", URI, data)
											.then(function(response) {
														$scope.containerList = response.data.listObject;
													});
								} catch (e) {
									console.log(e.message)
								}
							}
							$scope.getContainerList($scope.orgId);

							$scope.getReportList = function(orgId) {
								try {
									var data = "";
									var URI = BASE_URL + ROOT_URL
											+ LIS_COMMON + GET_REPORTTYPE_LIST
											+ S + orgId;
									console.log("URI", URI);
									GenericService
											.serviceAction("GET", URI, data)
											.then(function(response) {
														$scope.reportList = response.data.listObject;
													});
								} catch (e) {
									console.log(e.message)
								}
							}
							$scope.getReportList($scope.orgId);

							$scope.getTechniqueList = function(orgId) {
								try {
									var data = "";
									var URI = BASE_URL + ROOT_URL
											+ LIS_COMMON + GET_TECHNIQUE_LIST
											+ S + orgId;
									console.log("URI", URI);
									GenericService
											.serviceAction("GET", URI, data)
											.then(function(response) {
												$scope.techniqueList = response.data.listObject;
													});
								} catch (e) {
									console.log(e.message)
								}
							}
							$scope.getTechniqueList($scope.orgId);

							$scope.getPrerequisitesList = function(orgId) {
								try {
									var data = "";
									var URI = BASE_URL + ROOT_URL
											+ LIS_COMMON
											+ GET_PREREQUISITES_LIST + S
											+ orgId;
									console.log("URI", URI);
									GenericService
											.serviceAction("GET", URI, data)
											.then(function(response) {
												$scope.prerequisitesList = response.data.listObject;
													});
								} catch (e) {
									console.log(e.message)
								}
							}
							$scope.getPrerequisitesList($scope.orgId);

							$scope.getTurnArroundTimeList = function(orgId) {
								try {
									var data = "";
									var URI = BASE_URL + ROOT_URL
											+ LIS_COMMON + GET_TRUNAROUND_TIME_LIST
											+ S + orgId;
									console.log("URI", URI);
									GenericService
											.serviceAction("GET", URI, data)
											.then(
													function(response) {
														$scope.timeList = response.data.listObject;
													});
								} catch (e) {
									console.log(e.message)
								}
							}
							$scope.getTurnArroundTimeList($scope.orgId);

							$scope.getnoOfDayList = function(orgId) {
								try {
									var data = "";
									var URI = BASE_URL + ROOT_URL
											+ LIS_COMMON + GET_DAY_LIST + S
											+ orgId;
									console.log("URI", URI);
									GenericService
											.serviceAction("GET", URI, data)
											.then(
													function(response) {
														$scope.getnoOfDayList = response.data.listObject;
													});
								} catch (e) {
									console.log(e.message)
								}
							}
							$scope.getnoOfDayList($scope.orgId);

							$scope.getAgeGroupList = function(orgId) {
								try {
									var data = "";
									var URI = BASE_URL + ROOT_URL
											+ LIS_COMMON + GET_AGE_GROUP_LIST
											+ S + orgId;
									console.log("URI", URI);
									GenericService
											.serviceAction("GET", URI, data)
											.then(
													function(response) {
														$scope.ageGroupList = response.data.listObject;
													});
								} catch (e) {
									console.log(e.message)
								}
							}
							$scope.getAgeGroupList($scope.orgId);

							$scope.getGenderList = function(orgId) {
								try {
									var data = "";
									var URI = BASE_URL + ROOT_URL
											+ LIS_COMMON
											+ GET_GENDER_LIST + S + orgId;
									console.log("URI", URI);
									GenericService
											.serviceAction("GET", URI, data)
											.then(
													function(response) {
														$scope.genderList = response.data.listObject;
													});
								} catch (e) {
									console.log(e.message)
								}
							}
							$scope.getGenderList($scope.orgId);
							
							$scope.getRefRangeTypeList = function(orgId,orgUnitId) {
								try {
									var data = "";
									var URI = BASE_URL + ROOT_URL
											+ LIS_COMMON
											+ GET_REF_RANGE_TYPES + S + orgId+S+orgUnitId;
									console.log("URI", URI);
									GenericService
											.serviceAction("GET", URI, data)
											.then(
													function(response) {
														$scope.refRangeTypeList = response.data.listObject;
													});
								} catch (e) {
									console.log(e.message)
								}
							}
							$scope.getRefRangeTypeList($scope.orgId,$scope.orgUnitId);
							
							
							$scope.getTextualRangeList = function(orgId,orgUnitId) {
								try {
									var data = "";
									var URI = BASE_URL + ROOT_URL
											+ LIS_COMMON
											+ TEXTUAL_REFERENCE_RANGES + S + orgId+S+orgUnitId;
									console.log("URI", URI);
									GenericService
											.serviceAction("GET", URI, data)
											.then(
													function(response) {
														$scope.textualRangeList = response.data.listObject;
													});
								} catch (e) {
									console.log(e.message)
								}
							}
							$scope.getTextualRangeList($scope.orgId,$scope.orgUnitId);

					/** =====================>Code for Push Pop Objects<===============*/
							
					$scope.helpValueList = [];
					$scope.refRengeList = [];
					$scope.refRengeTextualList = [];	
					$scope.refRengeMultiTextualList = [];	
					$scope.addHelpValue = function()
							{ 
						        if($scope.updateScope ==false)
						        	{
								      var helpValueMaster = angular.copy($scope.helpValueMaster);
								      $scope.listHelpValueMaster.push(helpValueMaster);
						        	}
						        else if($scope.updateScope ==true)
						        	{
						        	  var helpValueMaster = angular.copy($scope.helpValueMaster);
						        	  helpValueMaster.parameterId = $scope.singleParamTestMaster.listParameterMasterDto[0].parameterId;
								      $scope.listHelpValueMaster.push(helpValueMaster);
						        	}
							};

					$scope.removeHelpvalue = function(deleteIndex)
							{ 
						    if($scope.updateScope ==false)
			        	    {
								$scope.listHelpValueMaster.splice(deleteIndex, 1);
						    }
						   else if($scope.updateScope ==true)
				        	{
							   var helpValueMaster = angular.copy($scope.singleParamTestMaster.listParameterMasterDto[0].listHelpValueMaster[deleteIndex]);
					           helpValueMaster.parameterId = $scope.singleParamTestMaster.listParameterMasterDto[0].parameterId;
					           helpValueMaster.isDeleted = 'Y';
							   $scope.helpValueList.push(helpValueMaster);
							   $scope.listHelpValueMaster.splice(deleteIndex, 1);
				        	}	
						}		
					$scope.addParamRefrengMaster = function()
							{
						      if($scope.updateScope ==false)
			        	        {
						    	  if($scope.singleParamTestMaster.listParameterMasterDto[0].refrangeTypeId ==REFERENCE_VALUE)
						    	   {
						    		   var paramRefrengMaster = angular.copy($scope.paramRefrengMaster);
										  $scope.listParamRefrengMaster.push(paramRefrengMaster);
						    	   }
						    	   else if($scope.singleParamTestMaster.listParameterMasterDto[0].refrangeTypeId ==TEXTUAL_RANGE)
						    		   {
						    		   var paramTextualRangeMaster = angular.copy($scope.paramTextualRangeMaster);
										  $scope.listParamTextualRangeMaster.push(paramTextualRangeMaster);
						    		   }
						    	   else if($scope.singleParamTestMaster.listParameterMasterDto[0].refrangeTypeId ==MULTIVALUED_RANGE)
						    		   {
						    		   var paramMultiTextualRangeMaster = angular.copy($scope.paramMultiTextualRangeMaster);
										  $scope.listParamMultiTextualRangeMaster.push(paramMultiTextualRangeMaster);
						    		   }
			        	       }
						      else if($scope.updateScope ==true)
					        	{
						    	  
						    	  if($scope.singleParamTestMaster.listParameterMasterDto[0].refrangeTypeId ==REFERENCE_VALUE)
						    	   {
						    		  var paramRefrengMast = angular.copy($scope.paramRefrengMaster);
						    		  paramRefrengMast.parameterId = $scope.singleParamTestMaster.listParameterMasterDto[0].parameterId;
									  $scope.listParamRefrengMaster.push(paramRefrengMast);
						    	   }
						    	   else if($scope.singleParamTestMaster.listParameterMasterDto[0].refrangeTypeId ==TEXTUAL_RANGE)
						    		   {
						    		   var paramTextualRangeMast = angular.copy($scope.paramTextualRangeMaster);
						    		   paramTextualRangeMast.parameterId = $scope.singleParamTestMaster.listParameterMasterDto[0].parameterId;
										$scope.listParamTextualRangeMaster.push(paramTextualRangeMast);
						    		   }
						    	   else if($scope.singleParamTestMaster.listParameterMasterDto[0].refrangeTypeId ==MULTIVALUED_RANGE)
						    		   {
						    		   var paramMultiTextualRangeMast = angular.copy($scope.paramMultiTextualRangeMaster);
						    		   paramMultiTextualRangeMast.parameterId = $scope.singleParamTestMaster.listParameterMasterDto[0].parameterId;
										$scope.listParamMultiTextualRangeMaster.push(paramMultiTextualRangeMast);
						    		   }
					        	}	
							};

					$scope.removeParamRefrengMaster = function(deleteIndex)
							{
						       if($scope.updateScope ==false)
	        	                {
						    	   if($scope.singleParamTestMaster.listParameterMasterDto[0].refrangeTypeId ==REFERENCE_VALUE)
						    	   {
						    		   $scope.listParamTextualRangeMaster.splice(deleteIndex, 1);
						    	   }
						    	   else if($scope.singleParamTestMaster.listParameterMasterDto[0].refrangeTypeId ==TEXTUAL_RANGE)
						    	   {
						    		   $scope.listParamTextualRangeMaster.splice(deleteIndex, 1);
						    		}
						    	   else if($scope.singleParamTestMaster.listParameterMasterDto[0].refrangeTypeId ==MULTIVALUED_RANGE)
						    		  {
						    		    $scope.listParamMultiTextualRangeMaster.splice(deleteIndex, 1);
						    		  }
								  
	        	                }
						       else if($scope.updateScope ==true)
					        	{
						    	   if($scope.singleParamTestMaster.listParameterMasterDto[0].refrangeTypeId ==REFERENCE_VALUE)
						    	   {
						    		   var paramRefrengMaster = angular.copy($scope.listParamRefrengMaster[deleteIndex]);
								       paramRefrengMaster.isDeleted = 'Y'
									   $scope.refRengeList.push(paramRefrengMaster);
								       $scope.listParamRefrengMaster.splice(deleteIndex, 1);
						    	   }
						    	   else if($scope.singleParamTestMaster.listParameterMasterDto[0].refrangeTypeId ==TEXTUAL_RANGE)
						    	   {
						    		   var paramTextualRangeMst = angular.copy($scope.listParamTextualRangeMaster[deleteIndex]);
						    		   paramTextualRangeMst.isDeleted = 'Y'
									   $scope.refRengeTextualList.push(paramTextualRangeMst);
								       $scope.listParamTextualRangeMaster.splice(deleteIndex, 1);
						    		   
						    		   $scope.listParamTextualRangeMaster.splice(deleteIndex, 1);
						    		}
						    	   else if($scope.singleParamTestMaster.listParameterMasterDto[0].refrangeTypeId ==MULTIVALUED_RANGE)
						    		  {
						    		   var paramMultiTextualRangeMst = angular.copy($scope.listParamMultiTextualRangeMaster[deleteIndex]);
						    		   paramMultiTextualRangeMst.isDeleted = 'Y'
									   $scope.refRengeMultiTextualList.push(paramMultiTextualRangeMst);
								       $scope.listParamMultiTextualRangeMaster.splice(deleteIndex, 1);
						    		  }
					        	}	
							};		
							
							$scope.showRefRange = function(refrangeTypeId)
							{
								$scope.listParamRefrengMaster = [];
								$scope.listParamTextualRangeMaster = [];
								$scope.listParamMultiTextualRangeMaster = [];
								$scope.helpValueList = [];
								if(refrangeTypeId==REFERENCE_VALUE)
									{
									  $scope.listParamRefrengMaster.push(angular.copy($scope.paramRefrengMaster));
									}
								else if(refrangeTypeId==TEXTUAL_RANGE)
									{
									$scope.listParamTextualRangeMaster.push(angular.copy($scope.paramTextualRangeMaster));
									}
								else if(refrangeTypeId==MULTIVALUED_RANGE)
									{
									$scope.listParamMultiTextualRangeMaster.push(angular.copy($scope.paramMultiTextualRangeMaster));
									}
							}
							
					$scope.showFootNote = function(testIndex)
					{
						$('#footNoteSingle').modal('show');
						$scope.footNote  = $scope.singleParamTestList[testIndex].footNote;
					}
					
					$scope.showPanicValue = function(parameterId,refrangeTypeId)
					{
						$scope.refrangeTypeId = refrangeTypeId;
						try {
							var data = "";
							var URI = BASE_URL + ROOT_URL
							+ LIS_UNIT
							+ REFRANGES_LIST +S + parameterId+S+refrangeTypeId+S + $scope.orgId+S+$scope.orgUnitId;
							console.log("URI", URI);
							if(refrangeTypeId ==REFERENCE_VALUE)
							{
								GenericService
								.serviceAction("GET", URI, data)
								.then(
										function(response) {
											$scope.paramRefRengeList = response.data.listObject;
										});
							}
							else if(refrangeTypeId ==TEXTUAL_RANGE)
							{
								GenericService
								.serviceAction("GET", URI, data)
								.then(
										function(response) {
											$scope.textualParameterList = response.data.listObject;
										});
							}
							else if(refrangeTypeId ==MULTIVALUED_RANGE) 
							{ 
								GenericService
								.serviceAction("GET", URI, data)
								.then(
										function(response) {
											$scope.multiTextualList = response.data.listObject;
										});
							}
							$('#panicValueSingle').modal('show');
						} catch (e) {
							console.log(e.message)
						}
					}
					
					$scope.showHelpValue = function(parameterId)
					{
					
						try {
							$scope.helpValueViewList = [];
							var data = "";
							var URI = BASE_URL + ROOT_URL
							+ LIS_UNIT
							+ GET_HELP_VALUES +S + parameterId+S+S + $scope.orgId+S+$scope.orgUnitId;
							console.log("URI", URI);
								GenericService
								.serviceAction("GET", URI, data)
								.then(
										function(response) {
											$scope.helpValueViewList = response.data.listObject;
										});
								$('#helpValueSingle').modal('show');
							}
						catch (e) {
							console.log(e.message)
						}
					}
							
					
					/** Service Autocomplete List * */
					$scope.getServiceList = function(searchKeyword) {
						try {
							var data = "";
							var URI = BASE_URL + ROOT_URL
									+ LIS_COMMON
									+ AUTO_COMPLETE_SERVICES + S
									+ $scope.orgId + S
									+ $scope.orgUnitId +S
									+ $scope.deptId +S
									+ searchKeyword;
							console.log("URI", URI);
							return GenericService.serviceAction("GET", URI,
									data).then(function(response) {
								return response.data.listObject;
							});
						} catch (e) {
							console.log(e.message)
						}
					}

					$scope.selectServiceId = function($item, $model,
							$label) {
						$scope.singleParamTestMaster.serviceId = $item.id;
						try {
							var data = {
								serviceId : $item.id,
								orgId : $scope.orgId,
								orgUnitId : $scope.orgUnitId,
							};
							var URI = BASE_URL + ROOT_URL
									+ LIS_UNIT + CHECK_SERVICE_AVAIABLE;
							console.log("URI", URI);
							GenericService
									.serviceAction("POST", URI, data)
									.then(
											function(response) {
												$scope.serviceCount = response.data.object;
												if (response.data.object > 0) {
													$scope.isServiceExist = true;
													growl.error("Service is already Exists.",
															{
																title : 'Error!'
															});
													$scope.singleParamTestList.testDesc = "";
												} else {
													$scope.isServiceExist = false;
												}
											});
						} catch (e) {
							console.log(e.message)
						}

					}
					
						/**=================>OBJECT DELCLARATION <========================= **/	
					$scope.listParameterMaster = [];
					$scope.listHelpValueMaster = [];
					$scope.listTestParamMpprMaster = [];
					$scope.listParamRefrengMaster = [];
					$scope.listParamTextualRangeMaster = [];
					$scope.listParamMultiTextualRangeMaster = [];
					
					$scope.initMasters = function()
					{
						$scope.listParameterMaster = [];
						$scope.listTestParamMpprMaster = [];
						
						$scope.listParamRefrengMaster = [];
						$scope.listHelpValueMaster = [];
						$scope.listParamTextualRangeMaster = [];
						$scope.listParamMultiTextualRangeMaster = [];
						
						$scope.helpValueList = [];
						$scope.refRengeList = [];
						$scope.refRengeTextualList = [];	
						$scope.refRengeMultiTextualList = [];									
					}
					

							$scope.paramRefrengMaster = {
								"refrengId" : "",
								"parameterId" : "",
								"genderId" : "",
								"minValue" : "",
								"maxValue" : "",
								"lessThan" : "",
								"moreThan" : "",
								"ageGroupId" : "",
								"remark" : "",
								"refrengStatus" : "A",
								"createdBy" : $scope.createdBy,
								"createdDate" : "",
								"updatedBy" : "",
								"updatedDate" : "",
								"orgId" : $scope.orgId,
								"orgUnitId" : $scope.orgUnitId,
								"isDeleted" : "N"
							}

							$scope.helpValueMaster = {
								"helpValueId" : "",
								"parameterId" : "",
								"helpValue" : "",
								"status" : "A",
								"createdBy" : $scope.createdBy,
								"createdDate" : "",
								"updatedBy" : "",
								"updatedDate" : "",
								"orgId" : $scope.orgId,
								"orgUnitId" : $scope.orgUnitId,
								"isDeleted" : "N"
							}

							$scope.testParamMpprMaster = {
								"testPerMpprId" : "",
								"testId" : "",
								"parameterId" : "",
								"paraSequence" : "",
								"headerId" : "1",
								"testParaStatus" : "A",
								"createdBy" : "",
								"createdDate" : "",
								"updatedBy" : "",
								"updatedDate" : "",
								"orgId" : $scope.orgId,
								"orgUnitId" : $scope.orgUnitId,
								"isDeleted" : "N"
							}

							$scope.parameterMaster = {
									"parameterId" : "",
									"refrangeTypeId":"1",
									"parameterName" : "",	
									"aliesName" : "",
									"parameterCode" : "",
									"unitId" :"",
									"deltaDaysId" : "",
									"deltaPer" : "",
									"testType" : 0,
									"isMultyparameter" : "N",
									"createdBy" : $scope.createdBy,
									"createdDate" :"",
									"updatedBy" : $scope.updatedBy,
									"updatedDate" : "",
									"orgId" : $scope.orgId,
									"orgUnitId" : $scope.orgUnitId,
									"status" : "A",
									"listParamRefrengMaster" : $scope.listParamRefrengMaster,
									"listHelpValueMaster" : $scope.listHelpValueMaster,
									"listParamTextualRanageMaster":$scope.listParamTextualRangeMaster,
									"listParamMultiTextualRangeMaster":$scope.listParamMultiTextualRangeMaster
									
								}
								
								$scope.paramTextualRangeMaster = {
									  "paramTextualRangeId" : "",
									  "parameterId" : "",
									  "textualRangeId" : "",
									  "textualRangeName" : "",
									  "createdBy" : $scope.createdBy,
									  "createdDate" : "",
									  "updatedBy" :$scope.createdBy,
									  "updatedDate" : "",
									  "status" : "",
									  "orgId" : $scope.orgId,
									  "orgUnitId" : $scope.orgUnitId,
									  "isDeleted" : "N",
									  "ageGroupId" : "",
									  "remark" : "",
									  "genderId" : "",
									  "genderName" : "",
									  "ageGroupName" : "",
									  "ageFromDay" : "",
									  "ageToDay" : ""
									}


								$scope.paramMultiTextualRangeMaster = 	{
									  "paramMultiTextualRangeId" : "",
									  "parameterId" : "",
									  "multitextaulRange" : "",
									  "createdBy" : $scope.createdBy,
									  "createdDate" : "",
									  "updatedBy" : $scope.createdBy,
									  "updatedDate" : "",
									  "status" : "",
									  "orgId" : $scope.orgId,
									  "orgUnitId" : $scope.orgUnitId,
									  "isDeleted" : "N",
									  "ageGroupId" : "",
									  "remark" : "",
									  "genderId" : "",
									  "genderName" : "",
									  "ageGroupName" : "",
									  "ageFromDay" : "",
									  "ageToDay" : ""
									}

							$scope.initSingleParamTest = function() 
							{
								$scope.listParameterMaster.push(angular.copy($scope.parameterMaster));
								$scope.listHelpValueMaster.push(angular.copy($scope.helpValueMaster));
								$scope.listParamRefrengMaster.push(angular.copy($scope.paramRefrengMaster));
								
								$scope.singleParamTestMaster = {
									"testId" : "",
									"testCode" : "",
									"testDesc" : "",
									"testAlies" : "",
									"deptId" : "",
									"sampleId" : "",
									"noOfSampleReq" : "",
									"sampleVolume" : "",
									"containerId" : "",
									"reportTypeId" : "",
									"techniqueId" : "",
									"identificationNo" : "",
									"prerequisitsId" : "",
									"proRelease" : "",
									"testStatus" : "",
									"isOutsourced" : "",
									"footNote" : "",
									"createdBy" : $scope.createdBy,
									"createdDate" : "",
									"updatedBy" : "",
									"updatedDate" : "",
									"orgId" : $scope.orgId,
									"orgUnitId" : $scope.orgUnitId,
									"normlTatTime" : "",
									"normlTatId" : "",
									"urgentTatTime" : "",
									"urgentTatId" : "",
									"serviceId" : "",
									"testType" : 0,
									"unitId" :"",
									"isHistoCyto":"N",
									"specimanId":"",
									"isCentrifugationRequried":"A",
									"listParameterMasterDto" : $scope.listParameterMaster,
									"listTestParamMppr" : $scope.listTestParamMpprMaster
								}
							}
							$scope.initSingleParamTest();
							// ====================================================CODE START FOR SINGLE PARAM MASTER LIST===========================================================

							$scope.setNoOfRecords = function() {
								$scope.initSingleParamTestMasterList($scope.orgId,$scope.orgUnitId, $scope.offset, $scope.noOfRecordsPerPage);
							};
							$scope.initSingleParamTestMasterList = function(orgId,orgUnitId, offset, noOfRecordsPerPage)
							{
								$rootScope.startLoader();
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage : 10;
								var URI1 = BASE_URL + ROOT_URL + LIS_UNIT + LIST_TEST_MASTER + S + orgId + S + $scope.isHistoCyto + S +orgUnitId+S+ offset
										+ S + noOfRecordsPerPage;
								var URI2 = BASE_URL + ROOT_URL + LIS_UNIT + GET_TOTAL_TEST_RECORD + S + orgId+ S + $scope.isHistoCyto + S +orgUnitId;
								console.log("URI1", URI1);
								console.log("URI2", URI2);
								promiseFactory.setPromises(URI1, URI2, "GET", "GET").then(function(response) 
								{
									$rootScope.stopLoader();
									$scope.singleParamTestList = response[0].listObject;
									$scope.singleParamTestMasterListCount = response[1].object;
									$scope.setPage(1, false);
								});
							}

							$scope.getSingleParamTestMasterList = function(orgId,orgUnitId, offset, noOfRecordsPerPage)
							{
								$rootScope.startLoader();
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage : 10;
								var URI = BASE_URL + ROOT_URL + LIS_UNIT + LIST_TEST_MASTER + S + orgId +  S + $scope.isHistoCyto + S +orgUnitId+S+ offset
								+ S + noOfRecordsPerPage;  
								console.log("URI", URI);
								GenericService.serviceAction("GET", URI, data).then(function(response) {
									$rootScope.stopLoader();
									$scope.singleParamTestList = response.data.listObject;
								});
							}

							$scope.pager = {};
							$scope.page;

							$scope.setPage = function(page, flag) {
								if (page < 1 || page > $scope.pager.totalPages)
								{
									return;
								}
								$scope.pager = PagerService.GetPager($scope.singleParamTestMasterListCount, page, $scope.noOfRecordsPerPage);
								if (flag)
								{
									$scope.getSingleParamTestMasterList($scope.orgId,$scope.orgUnitId, $scope.pager.startIndex, $scope.pager.pageSize);
								}
							}

							$scope.initSingleParamTestMasterList($scope.orgId,$scope.orgUnitId, $scope.offset, $scope.noOfRecordsPerPage);

							// ====================================================CODE END FOR SINGLE PARAM LIST===========================================================
							
							
							
							/** CRUD FOR SINGLE PARAM TEST MASTER  **/
							//code for Activating Inactivating Sample
							$scope.activeInactiveSingleParamTest = function(orgId,orgUnitId,testId,parameterId,testStatus)
							{
								try {
									$rootScope.startLoader();
									var URI = BASE_URL + ROOT_URL + LIS_UNIT+ACTIVE_INACTIVE_TEST+S+orgId+S+orgUnitId+S+testId+S+parameterId+S+testStatus;
									console.log("ACT_INACT_URI",URI);
									GenericService.serviceAction("GET", URI, $scope.singleParamTestMaster).then(function(response) 
									{
										if (response.data.status == 'success')
										{
											$rootScope.stopLoader();
											growl.success(response.data.message,
													{
														title : 'Success!'
													});
											$scope.$broadcast('show-errors-reset');
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
									console.log("Exception",e.message);
								}
								
							}
							
							
							$scope.getSingleParamTestById = function(orgId,orgUnitId,testId)
							{
								try {
									$rootScope.startLoader();
									$scope.updateScope = true;
									$scope.saveBtnFlag = false;
									$scope.updateBtnFlag = true;
									var URI = BASE_URL + ROOT_URL + LIS_UNIT+GET_SINGLE_PARAMETER_TEST+S+orgId+S+orgUnitId+S+testId;
									console.log("EDIT",URI);
									GenericService.serviceAction("GET", URI, $scope.singleParamTestMaster).then(function(response) 
									{
										console.log("data",response.data);
										if (response.data.status == 'success')
										{
											$rootScope.stopLoader();
											$scope.singleParamTestMaster = response.data.object;
											$scope.listHelpValueMaster = $scope.singleParamTestMaster.listParameterMasterDto[0].listHelpValueMaster;
											$scope.listParamRefrengMaster = $scope.singleParamTestMaster.listParameterMasterDto[0].listParamRefrengMaster;
											$scope.listParamTextualRangeMaster = $scope.singleParamTestMaster.listParameterMasterDto[0].listParamTextualRanageMaster;
											$scope.listParamMultiTextualRangeMaster = $scope.singleParamTestMaster.listParameterMasterDto[0].listParamMultiTextualRangeMaster;
											$scope.singleParamTestMaster.listParameterMasterDto[0].unitId =  response.data.object.listParameterMasterDto[0].unitId+"";
											$scope.singleParamTestMaster.listParameterMasterDto[0].deltaDaysId = response.data.object.listParameterMasterDto[0].deltaDaysId+"";
											console.log("$scope.singleParamTestMaster",$scope.singleParamTestMaster);
										} else if(response.data.status == 'error')
										{
											$rootScope.stopLoader();
											alert("Error In Fetching Data");
										}
									});
								} catch (e) {
									console.log("Exception",e.message);
								}
								
							}
							
							
							$scope.saveSingleParamTest = function() 
							{
								$scope.$broadcast('show-errors-check-validity');
								if ($scope.singleParamTestForm.$valid)
								{
									try 
									{
										$rootScope.startLoader();
										$scope.singleParamTestMaster.listParameterMasterDto = $scope.listParameterMaster;
										$scope.singleParamTestMaster.listParameterMasterDto[0].isMultyparameter ='N';
										$scope.singleParamTestMaster.listParameterMasterDto[0].status ='A';
										$scope.singleParamTestMaster.listParameterMasterDto[0].parameterName = $scope.singleParamTestMaster.testDesc;
										$scope.singleParamTestMaster.listParameterMasterDto[0].aliesName = $scope.singleParamTestMaster.testAlies;
										$scope.singleParamTestMaster.listParameterMasterDto[0].parameterCode = $scope.singleParamTestMaster.testCode;
										$scope.singleParamTestMaster.unitId = $scope.singleParamTestMaster.listParameterMasterDto[0].unitId;
										if($scope.singleParamTestMaster.listParameterMasterDto[0].refrangeTypeId==REFERENCE_VALUE)
										{
											$scope.singleParamTestMaster.listParameterMasterDto[0].listParamRefrengMaster = $scope.listParamRefrengMaster;
											$scope.singleParamTestMaster.listParameterMasterDto[0].listParamTextualRanageMaster = [];
											$scope.singleParamTestMaster.listParameterMasterDto[0].listParamMultiTextualRangeMaster = [];
										}
									else if($scope.singleParamTestMaster.listParameterMasterDto[0].refrangeTypeId==TEXTUAL_RANGE)
										{
										  $scope.singleParamTestMaster.listParameterMasterDto[0].listParamRefrengMaster =[];
										  $scope.singleParamTestMaster.listParameterMasterDto[0].listParamTextualRanageMaster = $scope.listParamTextualRangeMaster;
										  $scope.singleParamTestMaster.listParameterMasterDto[0].listParamMultiTextualRangeMaster = [];
										}
									else if($scope.singleParamTestMaster.listParameterMasterDto[0].refrangeTypeId==MULTIVALUED_RANGE)
										{
										  $scope.singleParamTestMaster.listParameterMasterDto[0].listParamRefrengMaster =[];
										  $scope.singleParamTestMaster.listParameterMasterDto[0].listParamTextualRanageMaster =[];
										  $scope.singleParamTestMaster.listParameterMasterDto[0].listParamMultiTextualRangeMaster = $scope.listParamMultiTextualRangeMaster;
										}
										$scope.singleParamTestMaster.listParameterMasterDto[0].listHelpValueMaster =$scope.listHelpValueMaster;
										console.log("singleParamTestMaster",JSON.stringify($scope.singleParamTestMaster));
										var URI = BASE_URL + ROOT_URL + LIS_UNIT+ADD_SINGLE_PARAMETER_TEST;
										GenericService.serviceAction("POST", URI, $scope.singleParamTestMaster).then(function(response) 
										{	
											if (response.data.status == 'success')
											{
												$rootScope.stopLoader();
												growl.success(response.data.message,
														{
															title : 'Success!'
														});
												$scope.$broadcast('show-errors-reset');
												$scope.initMasters();
												$scope.initSingleParamTest();
												$scope.initSingleParamTestMasterList($scope.orgId,$scope.orgUnitId, $scope.offset, $scope.noOfRecordsPerPage);
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
										console.log("Exception",e.message);
									}
									
								}
							}
							$scope.updateSingleParamTest = function() 
							{
								$scope.$broadcast('show-errors-check-validity');
								if ($scope.singleParamTestForm.$valid)
								{
									try {
										$rootScope.startLoader();
										$scope.singleParamTestMaster.listParameterMasterDto[0].isMultyparameter ='N';
										$scope.singleParamTestMaster.listParameterMasterDto[0].status ='A';
										$scope.singleParamTestMaster.listParameterMasterDto[0].parameterName = $scope.singleParamTestMaster.testDesc;
										$scope.singleParamTestMaster.listParameterMasterDto[0].aliesName = $scope.singleParamTestMaster.testAlies;
										$scope.singleParamTestMaster.listParameterMasterDto[0].parameterCode = $scope.singleParamTestMaster.testCode;
										$scope.singleParamTestMaster.unitId = $scope.singleParamTestMaster.listParameterMasterDto[0].unitId;
										if($scope.singleParamTestMaster.listParameterMasterDto[0].refrangeTypeId==REFERENCE_VALUE)
										{
											$scope.singleParamTestMaster.listParameterMasterDto[0].listParamRefrengMaster = $scope.listParamRefrengMaster.concat($scope.refRengeList);
										}
									else if($scope.singleParamTestMaster.listParameterMasterDto[0].refrangeTypeId==TEXTUAL_RANGE)
										{
										$scope.singleParamTestMaster.listParameterMasterDto[0].listParamTextualRanageMaster = $scope.listParamTextualRangeMaster.concat($scope.refRengeTextualList);
										}
									else if($scope.singleParamTestMaster.listParameterMasterDto[0].refrangeTypeId==MULTIVALUED_RANGE)
										{
										  $scope.singleParamTestMaster.listParameterMasterDto[0].listParamMultiTextualRangeMaster =$scope.listParamMultiTextualRangeMaster.concat($scope.refRengeMultiTextualList);
										}
										
										$scope.singleParamTestMaster.listParameterMasterDto[0].listHelpValueMaster =$scope.listHelpValueMaster.concat($scope.helpValueList);
										console.log("singleParamTestMaster",JSON.stringify($scope.singleParamTestMaster));
										var URI = BASE_URL + ROOT_URL + LIS_UNIT+UPDATE_SINGLEPARAMETER_TEST;
										GenericService.serviceAction("PUT", URI, $scope.singleParamTestMaster).then(function(response) 
										{
											if (response.data.status == 'success')
											{
												$rootScope.stopLoader();
												growl.success(response.data.message,
														{
															title : 'Success!'
														});
												$scope.$broadcast('show-errors-reset');
												$scope.initMasters();
												$scope.initSingleParamTest();
												$scope.initSingleParamTestMasterList($scope.orgId,$scope.orgUnitId, $scope.offset, $scope.noOfRecordsPerPage);
											} else if(response.data.status == 'error')
											{
												$rootScope.stopLoader();
												growl.error(response.data.message,
														{
															title : 'Error!'
														});
											}
										});
									} catch (e) 
									{
										console.log("Exception",e.message);
									}
									
								}
							}
							 $scope.checkDelta=function(value)
								{
								    if(value==1){
										$scope.deltaNo = true;
							            $scope.deltaText = false;
								    }else{

										$scope.deltaNo = false;
							            $scope.deltaText = true;
								    }
								}
	/**=============================>Errors Messages  <=======================================**/
		$scope.testNameReq = 'Test name is required.';
		$scope.testCodeReq = 'Test code is required.';
		$scope.testAliesReq = 'Alies name is required.';
		$scope.deptReq = 'Please select department.';
		$scope.sampleReq = 'Please select sample.';
		$scope.noOfSampleReq = 'Please enter no of sample.';
		$scope.sampleVolumeReq = 'Please enter sample volume.';
		$scope.sampleUnitReq = 'Please select sample unit.';
		$scope.containerReq = 'Please select container type.';
		$scope.reportTypeReq = 'Please select report type.';
		$scope.techniqueReq = 'Please select technique type.';
		$scope.identificationNoReq = 'Please enter identification number.';
		$scope.footNoteReq = 'Please enter fooot note.';
		$scope.urgentTatReq = 'Please select urgent time.';
		$scope.urgentTatTimeReq = 'Please enter argent time.';
		$scope.normlTimeReq = 'Please select normal time.';
		$scope.normlTatTimeReq = 'Please enter normal time.';
		
		$scope.minValReq = 'Required Min Val.';
		$scope.maxValReq = 'Required Max Val.';
		$scope.lessValReq = 'Required Less Val.';
		$scope.moreValReq = 'Required More Val.';
		$scope.helpValueReq = 'Required Help Val.';
		$scope.remarkValReq = 'Required Remark Val.';
		
		$scope.dayReq = 'Please Select Day.';
		$scope.deltaPer = 'Required delta no in %.';
		
		$scope.plzSelectUnit = 'Please Select Unit.';
		$scope.plzSelectAgeGroup = 'Please Select AgeGroup.';
		$scope.plzSelectSex='Please Select Sex.';
						} ]);