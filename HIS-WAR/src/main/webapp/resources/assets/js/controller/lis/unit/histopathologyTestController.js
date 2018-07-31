/**
 * @author Ganesh Chaudhari
 */

angular
		.module('myApp')
		.controller(
				"histopathologyTestController",
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
							$scope.isHistoCyto="Y";

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

							$scope.getSpecimanTypeList = function(orgId) {
								try {
									var data = "";
									var URI = BASE_URL + ROOT_URL
											+ LIS_COMMON + GET_SPECIMAN_TYPE_LIST + S
											+ orgId;
									console.log("URI", URI);
									GenericService
											.serviceAction("GET", URI, data)
											.then(
													function(response) {
														$scope.specimenTypeList = response.data.listObject;
													});
								} catch (e) {
									console.log(e.message)
								}
							}
							$scope.getSpecimanTypeList($scope.orgId);
							
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

							

							

					/** =====================>Code for Push Pop Objects<===============*/
							
					
							
					$scope.showFootNote = function(testIndex)
					{
						$('#footNoteSingle').modal('show');
						$scope.footNote  = $scope.singleParamTestList[testIndex].footNote;
					}
					$scope.showPanicValue = function(testIndex)
					{
						
						$scope.paramRefRengeList = $scope.singleParamTestList[testIndex].listParameterMasterDto[0].listParamRefrengMaster;
						$('#panicValueSingle').modal('show');
						$scope.testIndex  = testIndex;
					}
					
					$scope.showHelpValue = function(testIndex)
					{
						$scope.helpValueList = $scope.singleParamTestList[testIndex].listParameterMasterDto[0].listHelpValueMaster;
						$('#helpValueSingle').modal('show');
						$scope.testIndex  = testIndex;
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
												console.log("$scope.serviceCount",$scope.serviceCount);
												if (response.data.object > 0) {
													$scope.isServiceExist = true;
													growl.error("Service is already Exists.",{
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

					       $scope.listParameterMaster =[];

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
								"parameterName" : "",	
								"aliesName" : "",
								"parameterCode" : "",
								"unitId" : "",
								"deltaDaysId" : "",
								"deltaPer" : "",
								"testType" : 0,
								"isMultyparameter" : "N",
								"createdBy" : $scope.createdBy,
								"createdDate" : "",
								"updatedBy" : "",
								"updatedDate" : "",
								"orgId" : $scope.orgId,
								"orgUnitId" : $scope.orgUnitId,
								"status" : "A",
								"listParamRefrengMaster" : [],
								"listHelpValueMaster" :[]
							}

							$scope.initSingleParamTest = function() 
							{
								
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
									"isHistoCyto":"Y",
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
								GenericService.serviceAction("GET", URI, data).then(function(response) 
								{
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
											$scope.singleParamTestMaster.deptId =  response.data.object.deptId+"";
											$scope.singleParamTestMaster.sampleId =  response.data.object.sampleId+"";
											$scope.singleParamTestMaster.containerId = response.data.object.containerId+"";
											$scope.singleParamTestMaster.reportTypeId =response.data.object.reportTypeId+"";
											$scope.singleParamTestMaster.specimanId = response.data.object.specimanId+"";
											$scope.singleParamTestMaster.normlTatId = response.data.object.normlTatId+"";
											$scope.singleParamTestMaster.urgentTatId = response.data.object.urgentTatId+"";
											console.log("$scope.singleParamTestMaster",$scope.singleParamTestMaster);
										} else if(response.data.status == 'error')
										{
											$rootScope.stopLoader();
											growl.error("Error In Fetching Data",{title : 'Error!'});
										}
									});
								} catch (e) {
									console.log("Exception",e.message);
								}
								
							}
							
							
							$scope.saveSingleParamTest = function() 
							{
								$scope.$broadcast('show-errors-check-validity');
								if ($scope.histopathTestForm.$valid)
								{
									try 
									{
										$rootScope.startLoader();
										$scope.singleParamTestMaster.listParameterMasterDto = $scope.listParameterMaster;
										console.log("singleParamTestMaster",JSON.stringify($scope.singleParamTestMaster));
										var URI = BASE_URL + ROOT_URL + LIS_UNIT+ADD_SINGLE_PARAMETER_TEST;
										GenericService.serviceAction("POST", URI, $scope.singleParamTestMaster).then(function(response) 
										{	
											if (response.data.status == 'success')
											{
												$rootScope.stopLoader();
												growl.success(response.data.message,{title : 'Success!'});
												$scope.$broadcast('show-errors-reset');
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
								if ($scope.histopathTestForm.$valid)
								{
									try {
										$rootScope.startLoader();
										$scope.singleParamTestMaster.listParameterMasterDto[0].isMultyparameter ='N';
										$scope.singleParamTestMaster.listParameterMasterDto[0].status ='A';
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
		$scope.speciamnReq = 'Speciman Type is required.';
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