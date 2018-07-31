
/**
 * @author Ganesh
 */

angular
		.module('myApp')
		.controller(
				"sensitivityTestingController",
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

							/** Variables for pagination */
							$scope.offset = 0;
							$scope.noOfRecordsPerPage = 10;
							$scope.incubationObservationList = [];
							$scope.totalNoOfRecords;
							$scope.searchKeyword;
							$scope.commonListCount;

							$scope.orgId = 1;
							$scope.orgUnitId = 1;
							$scope.createdBy = 1;
							$scope.updatedBy = 1;
							$scope.deptId = LAB_DEPT ;
						    $scope.subDeptId = MICROBIOLOGY_DEPT;
						    
						    $scope.organismGroupId = "";
						    $scope.organismId = "";
						    
						    
						    
						     /******* START TAB FOR ALL PAGES IN TREE LEVEL *******/
				        	/*First Level*/
					    	$scope.firstTabLevel = 1 ;
							
							$scope.setFirstTabLevel= function(newTab)
							{
								$scope.firstTabLevel = newTab;
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
							
						/******* END TAB FOR ALL PAGES IN TREE LEVEL *******/

							$scope.listSensitivityTestResultMasterDto = [];
							$scope.listSensitivityTestResultDetailsMasterDto = [];
							$scope.listSensitivityTestResultSubDetailsMasterDto = [];
							
					
							$scope.SensitivityTestResultMasterDto = 
							{
									  "sensitivityResultId" : "",
									  "orgId" : $scope.orgId ,
									  "orgUnitId" : $scope.orgUnitId,
									  "createdBy" : $scope.createdBy,
									  "createdDate" : "",
									  "updatedBy" : "",
									  "updatedDate" : "",
									  "isDeleted" : 'N',
									  "checkTest" : "Y",
									  "labSampleDtlsId" : "",
									  "listSensitivityTestResultDetailsMaster" : $scope.listSensitivityTestResultDetailsMasterDto
							}
							
							$scope.SensitivityTestResultDetailsMasterDto = 
							{
									  "sesitivityResultDetailsId" : "",
									  "sensitivityResultId":"",
									  "orgId" : $scope.orgId ,
									  "orgUnitId" : $scope.orgUnitId,
									  "organismGroupId":"",
									  "organismGroupName":"",
									  "organismId" : "",
									  "organismName":"",
									  "selectedAll":"",
									  "createdBy" : $scope.createdBy,
									  "createdDate" : "",
									  "updatedBy" : "",
									  "updatedDate" : "",
									  "isDeleted" : "N",
									  "listSensitivityTestResultSubDetailsMaster" : $scope.listSensitivityTestResultSubDetailsMasterDto
							}
							
							$scope.SensitivityTestResultSubDetailsMasterDto = 
								{
									  "sesitivityResultSubDetailsId" : "",
									  "orgId" : $scope.orgId,
									  "orgUnitId" : $scope.orgUnitId,
									  "antibioticId" : "",
									  "antibioticName" : "",
									  "isChecked":"",
									  "sensitivityId":"",
									  "microLabPriorityId" : "",
									  "mic":"",
									  "remark":"",
									  "createdDate":"",
									  "createdBy" : $scope.createdBy,
									  "updatedBy" : "",
									  "updatedDate" : "",
									  "isDeleted" : "N",
									  "sesitivityResultDetailsId":""
								}
							

							

							$scope.setNoOfRecords = function() {
								$scope.initIncucationObjservationList($scope.orgId,
										$scope.orgUnitId, $scope.offset,
										$scope.noOfRecordsPerPage);
							};

							$scope.initIncucationObjservationList = function(orgId,
									orgUnitId, offset, noOfRecordsPerPage) {
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
									var URI1 = BASE_URL + ROOT_URL
											+ LIS_MICROBIOLOGY
											+ SENSITIVITY_TESTING_LIST;

									var URI2 = BASE_URL
											+ ROOT_URL
											+ LIS_MICROBIOLOGY
											+ SENSITIVITY_TESTING_LIST_COUNT;

									console.log("URI1", URI1);
									console.log("URI2", URI2);
									promiseFactoryWithObject
											.setPromisesWithObject(URI1, URI2, METHOD_POST,
													METHOD_POST,data,data1)
											.then(
													function(response) {
														$rootScope.stopLoader();
														$scope.incubationObservationList = response[0].listObject;
														$scope.commonListCount = response[1].object;
														$scope.setPage(1,false);
													});
								} catch (e) {
									console.log(e.message);
								}
							}

							$scope.initIncucationObjservationList($scope.orgId,
									$scope.orgUnitId, $scope.offset,
									$scope.noOfRecordsPerPage);
							
							
							$scope.getIncucationObjservationList = function(orgId,
									orgUnitId,offset,noOfRecordsPerPage) {
								try {
									$rootScope.startLoader();
									var data = {
											"orgId":orgId,
											"orgUnitId":orgUnitId,
											"deptId":$scope.deptId,
											"subDeptId":$scope.subDeptId,
											"offset":offset,
											"recordPerPage":noOfRecordsPerPage
										};
									var URI = BASE_URL + ROOT_URL
								            	+ LIS_MICROBIOLOGY
								               	+ SENSITIVITY_TESTING_LIST;
									console.log("URI", URI);

									GenericService
											.serviceAction(METHOD_POST, URI, data)
											.then(
													function(response) {
														$rootScope.stopLoader();
														$scope.listMicrobioResultDtlsMaster = response.data.listObject;
													});
								} catch (e) {
									console.log(e.message)
								}

							}
							
							$scope.getAntibioticAutocompleteList = function(searchKeyword) {
								try {
									var data = "";
									var URI = BASE_URL + ROOT_URL
					            	+ LIS_COMMON
					               	+ GET_ANTIBIOTICS_AUTOCOMPLETE+S+$scope.orgId+S+'antibiotic'+S+searchKeyword;
								 	console.log("URI", URI);
								 	return GenericService.serviceAction("GET", URI,
											data).then(function(response) {
										return response.data.listObject;
									});
								} catch (e) {
									console.log(e.message)
								}
							}
							
							$scope.selectAnitbiotic = function($item, $model,
									$label) 
									{
								   $scope.antibioticId = $item.id;
								   $scope.antibioticName = $item.name;
								
								}
							
							$scope.addAntibiotic = function(index)
							{
								var isExists;
								function checkExistsOrNot(arr, val) {
									return arr
											.some(function(arr) {
												return (arr.antibioticId == val);
											});
								}
								
								isExists = checkExistsOrNot(
										$scope.listSensitivityTestResultDetailsMasterDto[index].listSensitivityTestResultSubDetailsMaster,$scope.antibioticId);	
							
								if(isExists)
									{
									 alert("Antibiotics is already exists.");
									 $scope.antibiotics = undefined;
									}
								else
								{
									var SensitivityTestResultSubDetailsMasterDto = angular.copy($scope.SensitivityTestResultSubDetailsMasterDto);
								     SensitivityTestResultSubDetailsMasterDto.antibioticId = $scope.antibioticId;
								     SensitivityTestResultSubDetailsMasterDto.antibioticName = $scope.antibioticName;
								     $scope.listSensitivityTestResultDetailsMasterDto[index].listSensitivityTestResultSubDetailsMaster
											.push(SensitivityTestResultSubDetailsMasterDto);	
								     $scope.antibiotics = undefined;
								}
						     
							}
							
							$scope.removeAntibiotics = function(parentIndex,childIndex)
							{
								$scope.listSensitivityTestResultDetailsMasterDto[parentIndex].listSensitivityTestResultSubDetailsMaster.splice(childIndex, 1);
							}
							
						
							
						
							
							$scope.checkAll = function(parentIndex)
							{
								if ($scope.listSensitivityTestResultDetailsMasterDto[parentIndex].selectedAll) 
								{
									$scope.listSensitivityTestResultDetailsMasterDto[parentIndex].selectedAll = true;
								} else {
									$scope.listSensitivityTestResultDetailsMasterDto[parentIndex].selectedAll = false;
								}
								angular
										.forEach(
												$scope.listSensitivityTestResultDetailsMasterDto[parentIndex].listSensitivityTestResultSubDetailsMaster,
												function(antibiocsObj) {
													antibiocsObj.isChecked = $scope.listSensitivityTestResultDetailsMasterDto[parentIndex].selectedAll;
													console.log("antibiocsObj.isChecked",antibiocsObj.isChecked)
												});
							
							}
							/** Micro_Lab priority List*/
							$scope.getMicroLabPriorityMaster = function()
							{
								var data = "";
								try{
									var URI = BASE_URL + ROOT_URL
								            	+ LIS_COMMON
								               	+ GET_MICRO_LAB_PRIORITY_LIST+S+$scope.orgId;
									console.log("URI", URI);

									 GenericService
											.serviceAction(METHOD_GET, URI, data)
											.then(
													function(response) 
													{
														$scope.microLabPriorityList = response.data.listObject;
														console.log("$scope.microLabPriorityList",$scope.anitibioticList);
														 response.data.listObject;
													});
								} catch (e) {
									console.log(e.message)
								}
							}
							
							$scope.getMicroLabPriorityMaster();
							
							/** Micro_organism Group List*/
							$scope.getAntibioticsByOrganism = function(orgId,organismId)
							{
								var data = "";
								try{
									var URI = BASE_URL + ROOT_URL
								            	+ LIS_COMMON
								               	+ GET_ANTIBIOTICS_BY_ORGAINSM+S+orgId+S+'organismId'+S+organismId;
									console.log("URI", URI);

									return GenericService
											.serviceAction(METHOD_GET, URI, data)
											.then(
													function(response) 
													{
														$scope.anitibioticList = response.data.listObject;
														console.log("$scope.anitibioticList",$scope.anitibioticList);
														return response.data.listObject;
													});
								} catch (e) {
									console.log(e.message)
								}
							}
							
							
							/** Micro_organism Group List*/
							$scope.getMicroOrganismGroupList = function(orgId)
							{
								var data = "";
								try{
									var URI = BASE_URL + ROOT_URL
								            	+ LIS_COMMON
								               	+ MICRO_ORGANISM_GROUP_LIST_BY_ORG_ID+S+$scope.orgId;
									console.log("URI", URI);

									GenericService
											.serviceAction(METHOD_GET, URI, data)
											.then(
													function(response) {
														$scope.microOrganismGroupList = response.data.listObject;
														console.log("$scope.microOrganismGroupList",$scope.microOrganismGroupList);
													});
								} catch (e) {
									console.log(e.message)
								}
							}
							$scope.getMicroOrganismGroupList($scope.orgId);
							
							
							$scope.getMicroOrganismList = function(organismGroupId)
							{
								var data = "";
								try{
									var URI = BASE_URL + ROOT_URL
								            	+ LIS_COMMON
								               	+ MICRO_GET_ORGANISM_BY_GROUP+S+organismGroupId+S+'orgId'+S+$scope.orgId;
									console.log("URI", URI);

									GenericService
											.serviceAction(METHOD_GET, URI, data)
											.then(
													function(response) 
													{
														$scope.organismList = response.data.listObject;
														console.log("$scope.organismList",$scope.organismList);
													});
								} catch (e) {
									console.log(e.message)
								}
							}
							
							
					      
					    
					      
					      /**Function to get MicroOrganism Group idD*/
					      $scope.getMicroOrganismGroupId = function(organismGroupId)
					      {
					    	  $scope.organismGroupId = organismGroupId;
					      };
					      
				
					     
					      
					     $scope.addMicroOrganismGroup = function()
					      {
					    	 
					    	 var isExists;
					    	 var data = {
										"organismGroupId":"",
										"organismId":""
									};
					    	 
					    	 
					    	 data.organismGroupId = $scope.organismGroupId;
					    	 data.organismId = $scope.organismId;
					    	 
								function checkExistsOrNot(arr, val) {
									return arr
											.some(function(arr) {
												return (val.organismGroupId == arr.organismGroupId)
														&& (val.organismId == arr.organismId);
											});
								}
					    	
							
								isExists = checkExistsOrNot(
										$scope.listSensitivityTestResultDetailsMasterDto,
										data);
								
								if(isExists)
									{
								        alert("Organism Group And Organism Already Exists");	
									}
								else{
									
									 var SensitivityTestResultDetailsMasterDto = angular.copy($scope.SensitivityTestResultDetailsMasterDto);
							    	 $scope.miroOrgGroupList = $scope.microOrganismGroupList
										.filter(function(microOrgGroupObj) {
											if (microOrgGroupObj.id ==  $scope.organismGroupId) 
											{
												SensitivityTestResultDetailsMasterDto.organismGroupName = microOrgGroupObj.name;
												SensitivityTestResultDetailsMasterDto.organismGroupId = microOrgGroupObj.id;
											}
										});
							    	 $scope.orgList = $scope.organismList
										.filter(function(microOrgObj) {
											if (microOrgObj.id ==  $scope.organismId) 
											{
												SensitivityTestResultDetailsMasterDto.organismName = microOrgObj.name;
												SensitivityTestResultDetailsMasterDto.organismId = microOrgObj.id;
											}
										});
							    	 
							    	 var promise = $scope.getAntibioticsByOrganism($scope.orgId,$scope.organismId);
							    	 promise.then(function(commonList)
												{
													if (commonList != null)
														{
														$scope.listSensitivityTestResultSubDetailsMasterDto =[];
														angular
														.forEach(
																commonList,
																function(value, key) 
																{
																	 var SensitivityTestResultSubDetailsMasterDto = angular
																				.copy($scope.SensitivityTestResultSubDetailsMasterDto);
																	 SensitivityTestResultSubDetailsMasterDto.antibioticId = value.id;
																	 SensitivityTestResultSubDetailsMasterDto.antibioticName =value.name;
																	 $scope.listSensitivityTestResultSubDetailsMasterDto.push(SensitivityTestResultSubDetailsMasterDto);
																});
														}
													
													 SensitivityTestResultDetailsMasterDto.listSensitivityTestResultSubDetailsMaster = $scope.listSensitivityTestResultSubDetailsMasterDto;
													 $scope.listSensitivityTestResultDetailsMasterDto.push(SensitivityTestResultDetailsMasterDto);
												}, function(commonList)
												{
													console.log("ERROR FETCHING SUPER Antibiotics", commonList);
												});
							    	 
							    	 
							    	 console.log("$scope.listSensitivityTestResultDetailsMasterDto",$scope.listSensitivityTestResultDetailsMasterDto);

									
								    }
								 $scope.organismId = "";
						    	 $scope.organismGroupId = "";
					    	 
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
									$scope.IncucationObjservationList(
											$scope.orgId, $scope.orgUnitId,
											$scope.pager.startIndex,
											$scope.pager.pageSize);
								}
							}


							$scope.getSensitivityCheckTestResultDetails = function(labSampleDtlsId) {
								try {
									$rootScope.startLoader();
									var data = {};
									var URI = BASE_URL + ROOT_URL
								            	+ LIS_MICROBIOLOGY
								               	+ GET_SENSITIVITY_CHECK_TEST_DETAILS+S+labSampleDtlsId+S+$scope.orgId+S+$scope.orgUnitId;
									console.log("URI", URI);

									GenericService
											.serviceAction(METHOD_GET, URI, data)
											.then(
													function(response) {
														$rootScope.stopLoader();
														$scope.SensitivityTestResultMasterDto = response.data.object;
														$scope.listSensitivityTestResultDetailsMasterDto = response.data.object.listSensitivityTestResultDetailsMaster;
													});
								} catch (e) {
									console.log(e.message)
								}

							}
							
							
							$scope.getSenisitivityDetails = function(sensitivityObj)
							{
								//$scope.incubationObservationObjList=[];
								 $('#details').modal('show');
								 $scope.SensitivityTestResultMasterDto.labSampleDtlsId = sensitivityObj.labSampleDtlsId;
								 $scope.labSampleDtlsId = sensitivityObj.labSampleDtlsId;
								 /*$scope.incubationObservationObjList = $scope.incubationObservationList
									.filter(function(incuObservationObj) {
										if (incuObservationObj.labSampleDtlsId == sensitivityObj.labSampleDtlsId) {
											$scope.incubationObservationObjList = incuObservationObj;
											return incuObservationObj;
										}
									});*/
							}
							$scope.getSensitivityCheckTestDetails = function(sensitivityObj)
							{
								//$scope.incubationObservationObjList=[];
								 $('#sensitivityTesting').modal('show');
								 $scope.SensitivityTestResultMasterDto.labSampleDtlsId = sensitivityObj.labSampleDtlsId;
								 $scope.labSampleDtlsId = sensitivityObj.labSampleDtlsId;
								/* $scope.incubationObservationObjList = $scope.incubationObservationList
									.filter(function(incuObservationObj) {
										if (incuObservationObj.labSampleDtlsId == sensitivityObj.labSampleDtlsId) {
											$scope.incubationObservationObjList = incuObservationObj;
											return incuObservationObj;
										}
									});*/
								 
								 if(sensitivityObj.labSampleDtlsId!=null)
									 {
									   $scope.getSensitivityCheckTestResultDetails(sensitivityObj.labSampleDtlsId);
									 }
							}
							
							$scope.saveSensitivityResult = function(labSampleStausId) 
							{
								$scope.SensitivityTestResultMasterDto.createdDate = moment().toDate().getTime();
								$scope.SensitivityTestResultMasterDto.labSampleDtlsId = $scope.labSampleDtlsId;
							    $scope.SensitivityTestResultMasterDto.listSensitivityTestResultDetailsMaster = $scope.listSensitivityTestResultDetailsMasterDto;
								$rootScope.startLoader();
								try {
									console.log("$scope.listLabSampleDtls",JSON.stringify($scope.SensitivityTestResultMasterDto));
									var URI = BASE_URL + ROOT_URL
									        + LIS_MICROBIOLOGY
											+ SAVE_SENSITIVTY_DETAILS + S + labSampleStausId;
									console.log("SEND_TO_Microbiology WORKLIST", URI);
									GenericService
											.serviceAction(METHOD_POST, URI,
													$scope.SensitivityTestResultMasterDto)
											.then(
													function(response) {
														if (response.data.status == 'success') 
														{
															$scope.initIncucationObjservationList($scope.orgId,
																	$scope.orgUnitId, $scope.offset,
																	$scope.noOfRecordsPerPage);
															growl.success(
																			response.data.message,
																			{
																				title : 'Success!'
																			});
															$scope
																	.$broadcast('show-errors-reset');

														} else if (response.data.status == 'error') {
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
							
							

						} ]);