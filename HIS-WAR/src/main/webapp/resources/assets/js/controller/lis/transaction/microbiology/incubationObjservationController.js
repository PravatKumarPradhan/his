
/**
 * @author Ganesh
 */

angular
		.module('myApp')
		.controller(
				"incubationObjservationController",
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
							$scope.incubationPeriodId;
							$scope.incubationStartDate = moment().startOf('day').toDate();
							$scope.incubationStartTime;
							$scope.microscopicExamination;

							$scope.orgId = 1;
							$scope.orgUnitId = 1;
							$scope.createdBy = 1;
							$scope.updatedBy = 1;
							$scope.deptId = LAB_DEPT ;
						    $scope.subDeptId = MICROBIOLOGY_DEPT;
						    $scope.isComplete = "";
						    
						    $scope.incuDetailsObj =
						    	{
						    		"sampleId":"",
						    		"tabLevel":"",
						    		"microbioResultEntryId":"",
						    		"isIncubationComplete":"",
						    		"isMacro_exaComplete":"",
						    		"isMicro_exaComplete":""
						    	}
						    
						    
						     /******* START TAB FOR ALL PAGES IN TREE LEVEL *******/
				        	/*First Level*/
					    	$scope.firstTabLevel = 1 ;
							
							$scope.setFirstTabLevel= function(newTab)
							{ 
								$scope.incuDetailsObj.tabLevel = newTab;
								$scope.getIncuationPopupDetails($scope.incuDetailsObj);
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
							
							
							
							/**DatePicker Code  */

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
							
							
						/******* END TAB FOR ALL PAGES IN TREE LEVEL *******/
/*
							$scope.listMicrobioResultDtlsMaster = [];
							$scope.listMicroscopicExaMaster = [];
							$scope.listMicroscopicExaDetlsMasters = [];
							$scope.listMicroscopicExaminationSubDetailsMaster = [];
							
					
							$scope.microbioResultEntryMaster = 
							{
									  "microbioResultEntryId" : "",
									  "orgId" : $scope.orgId ,
									  "orgUnitId" : $scope.orgUnitId,
									  "patientId" : "",
									  "doctorId" : "",
									  "labSampleDtlsId" : "",
									  "microscopicExamination" : "",
									  "incuObservationFlag":"",
									  "createdBy" : $scope.createdBy,
									  "createdDate" : "",
									  "updatedBy" : "",
									  "updatedDate" : "",
									  "isDeleted" : 'N',
									  "incubationStartDate": "",
									  "incubationStartTime" : "",
									  "isIncubationComplete": "N",
									  "isMacro_exaComplete": "N",
									  "isMicro_exaComplete": "N",
									  "labSampleNo" : "",
									  "visitType" : "",
									  "isAlliquoteReq" : "",
									  "sampleStatusId" : "",
									  "sampleRecollectFlag" : "",
									  "sampleId" : "",
									  "listMicrobioResultDetailsMaster" : $scope.listMicrobioResultDtlsMaster,
									  "listMicroscopicExaminationMaster" : $scope.listMicroscopicExaMaster
							}
							
							$scope.microbioResultEntryDetailsMaster = 
							{
									  "microbioResultDetailsId" : "",
									  "orgId" : $scope.orgId ,
									  "orgUnitId" : $scope.orgUnitId,
									  "incuRemark" : "",
									  "microbioResultEntryId" : "",
									  "mediaId" : "",
									  "incubationPeriodId" : "",
									  "isGrowthFound" : "N",
									  "createdBy" : $scope.createdBy,
									  "createdDate" : "",
									  "updatedBy" : "",
									  "updatedDate" : "",
									  "isDeleted" : "N"
							}
							
							$scope.microscopicExaminationMaster = 
								{
									  "microscopicExaId" : "",
									  "orgId" : $scope.orgId,
									  "orgUnitId" : $scope.orgUnitId,
									  "stainingId" : "",
									  "stainingName":"",
									  "organismGroupId" : "",
									  "listCommonDto"  :"",
									  "microbioResultEntryId" : "",
									  "createdBy" : $scope.createdBy,
									  "createdDate" : "",
									  "updatedBy" : "",
									  "updatedDate" : "",
									  "isDeleted" : "N",
									  "listMicroscopicExaminationDetailsMasters" : $scope.listMicroscopicExaDetlsMasters
								}
							
							
							$scope.microscopicExaminationDetails = 
							{
								  "examinationDetailsId" : "",
								  "orgId" : $scope.orgId,
								  "orgUnitId" : $scope.orgUnitId,
								  "organismGroupId" : "",
								  "microOrgGroupName":"",
								  "createdBy" : $scope.createdBy,
								  "createdDate" : "",
								  "updatedBy" : "",
								  "updatedDate" : "",
								  "isDeleted" : "N",
								  "listMicroscopicExaminationSubDetailsMaster" : $scope.listMicroscopicExaminationSubDetailsMaster
							}
							
							$scope.microscopicExaminationSubDetailsMaster  =
							{
									  "microexaSubDetailsId" : "",
									  "orgId" : $scope.orgId,
									  "orgUnitId" :$scope.orgUnitId,
									  "microRemark" : "",
									  "examinationDetailsId" : "",
									  "organismId" : "",
									  "createdBy" : $scope.createdBy,
									  "createdDate" :"",
									  "updatedBy" : "",
									  "updatedDate" : "",
									  "isDeleted" : "N",
							}*/

							

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
											+ INCUBATION_OBSERVATION_LIST;

									var URI2 = BASE_URL
											+ ROOT_URL
											+ LIS_MICROBIOLOGY
											+ INCUBATION_OBSERVATION_LIST_COUNT;

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
								               	+ INCUBATION_OBSERVATION_LIST;
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

							/** code for setting all checkboxes */
							$scope.checkAll = function() {
								if ($scope.selectedAll) {
									$scope.selectedAll = true;
								} else {
									$scope.selectedAll = false;
								}
								angular
										.forEach(
												$scope.microbiologyAccPendingList,
												function(labDetailsObj) {
													labDetailsObj.isAcceptOrReject = $scope.selectedAll
												});
							 }
							
							
				
							
							
							
							
							$scope.generateIncubationDetailsUrl = function(incubationObservationObj)
							{
								if(incubationObservationObj!=null)
								  {
									incubationObservationObj = incubationObservationObj;
									incubationObservationObj.orgId = $scope.orgId;
									incubationObservationObj.orgUnitId = $scope.orgUnitId;
									incubationObservationObj.createdBy = $scope.createdBy;
									incubationObservationObj.listTMicroSampleMediaDto = [];
												$state.go('detailsIncubationMaster',
													{
													  incuObsrvationObj : incubationObservationObj,
													});
								  }
							  else
							     {
								    growl.error("Something went Wrong.",{title : 'Error!'});
							     }
							}
							
							/** Micro_organism Group List*//*
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
							
							
							$scope.getMicroOrganismList = function(organismGroupId,orgId,flag,parentIndex)
							{
								var data = "";
								try{
									var URI = BASE_URL + ROOT_URL
								            	+ LIS_COMMON
								               	+ MICRO_GET_ORGANISM_BY_GROUP+S+organismGroupId+S+'orgId'+S+orgId;
									console.log("URI", URI);

									return GenericService
											.serviceAction(METHOD_GET, URI, data)
											.then(
													function(response) {
														
														if(flag)
															{
															  if($scope.listMicroscopicExaMaster)
															  {
																$scope.listMicroscopicExaMaster[parentIndex].listCommonDto =response.data.listObject;
															  }
															}
														$scope.organismList = response.data.listObject;
														console.log("$scope.organismList",$scope.organismList);
														return response.data.listObject;
													
													});
								} catch (e) {
									console.log(e.message)
								}
							}
							
							
							$scope.getMediaListBySampleId = function(sampleId)
							{
								try{
									var data = "";
									var URI = BASE_URL + ROOT_URL
								            	+ LIS_COMMON
								               	+ MEDIA_LIST_BY_SAMPLEID+S+sampleId+S+'orgId'+S+$scope.orgId;
									console.log("URI", URI);
									GenericService
											.serviceAction(METHOD_GET, URI, data)
											.then(
													function(response) {
														$rootScope.stopLoader();
														$scope.mediaList = response.data.listObject;
														console.log("$scope.mediaList",$scope.mediaList);
													});
								} catch (e) {
									console.log(e.message)
								}
							}
							
							
							$scope.getStataingList = function(orgId)
							{
								var data = "";
								try{
									var URI = BASE_URL + ROOT_URL
								            	+ LIS_COMMON
								               	+ STAINING_LIST_BY_ORG_ID+S+$scope.orgId;
									console.log("URI", URI);

									GenericService
											.serviceAction(METHOD_GET, URI, data)
											.then(
													function(response) {
														$scope.stainingList = response.data.listObject;
														console.log("$scope.stainingList",$scope.stainingList);
													});
								} catch (e) {
									console.log(e.message)
								}
							}
							   $scope.addMedia = function()
							{
								
								angular
								.forEach(
										$scope.mediaList,
										function(value, key) 
										{
											 var microbioResultEntryDetailsMaster = angular
														.copy($scope.microbioResultEntryDetailsMaster);
											 microbioResultEntryDetailsMaster.mediaId = value.id+"";
											 microbioResultEntryDetailsMaster.incubationPeriodId = $scope.incubationPeriodId+""; 
											 microbioResultEntryDetailsMaster.isGrowthFound = value.isGrowthFound==""?'N':value.isGrowthFound;
											 console.log("microbioResultEntryDetailsMaster.incubationPeriodId",microbioResultEntryDetailsMaster.incubationPeriodId);
											 $scope.listMicrobioResultDtlsMaster
														.push(microbioResultEntryDetailsMaster);
										});
								
								console.log("listMicrobioResultDtlsMaster",$scope.listMicrobioResultDtlsMaster);
								
							}
							
							$scope.addMediaRow = function()
							{ 
						        	  var microbioResultEntryDetailsMaster = angular.copy($scope.microbioResultEntryDetailsMaster);
								      $scope.listMicrobioResultDtlsMaster.push(microbioResultEntryDetailsMaster);
							};

					      $scope.removeMediaRow = function(deleteIndex)
							{ 
								$scope.listMicrobioResultDtlsMaster.splice(deleteIndex, 1);
				        	}	
					      
					      
					      
					      
					      
					      $scope.addMicroOrgsism = function(parentIndex,childIndex)
							{ 
					    	       try {
					    	    	   
					    	    		 console.log("addMicroOrgsism parentIndex childIndex",parentIndex+""+childIndex);
					    	    	     var microscopicExaminationSubDetailsMaster = angular.copy($scope.microscopicExaminationSubDetailsMaster);
					    	    	     microscopicExaminationSubDetailsMaster.examinationDetailsId= $scope.listMicroscopicExaMaster[parentIndex].listMicroscopicExaminationDetailsMasters[childIndex].examinationDetailsId;
					    	    	     $scope.listMicroscopicExaMaster[parentIndex].listMicroscopicExaminationDetailsMasters[childIndex].listMicroscopicExaminationSubDetailsMaster.push(microscopicExaminationSubDetailsMaster);
								} catch (e) {
									console.log();
								}
						        	 
							};

					      $scope.removeMicroOrgnism = function(parentIndex,childIndex,subChildDeleteIndex)
							{ 
					    	     console.log("removeMicroOrgnism parentIndex childIndex subChildDeleteIndex",parentIndex+""+childIndex+""+subChildDeleteIndex);
			    	    	     $scope.listMicroscopicExaMaster[parentIndex].listMicroscopicExaminationDetailsMasters[childIndex].listMicroscopicExaminationSubDetailsMaster.splice(subChildDeleteIndex, 1);
				        	}	
							
					      
					      
					      *//**Function to getStatining ID*//*
					      $scope.getStainingId = function(stainingId)
					      {
					    	  $scope.stainingId = stainingId;
					      };
					      
					      *//**Function to get MicroOrganism Group idD*//*
					      $scope.getMicroOrganismGroupId = function(organismGroupId)
					      {
					    	  $scope.organismGroupId = organismGroupId;
					      };
					      
					      $scope.addStaining = function()
					      {
					    	  
					    	  var isExists;
								function checkExistsOrNot(arr, val) {
									return arr
											.some(function(arr) 
											{
												return (arr.stainingId == val);
											});
								}
					    	  
								isExists = checkExistsOrNot(
										 $scope.listMicroscopicExaMaster,$scope.stainingId);	
							if(isExists)
								{
								 growl.error("Stain is already Exists.",{title : 'Error!'});
								 $scope.stainingId = "";
								}
							else
							{
								  var microscopicExaminationMaster = angular.copy($scope.microscopicExaminationMaster);
						    	  $scope.statingFilterList = $scope.stainingList
									.filter(function(stainingObj) {
										if (stainingObj.id ==  $scope.stainingId) 
										{
											  microscopicExaminationMaster.stainingName = stainingObj.name;
											  microscopicExaminationMaster.stainingId = stainingObj.id;
										}
									});
						    	  
						    	  $scope.listMicroscopicExaMaster.push(microscopicExaminationMaster);	
						    	  $scope.stainingId = "";
							}
					 }
					     
					      
					     $scope.addOrganismByGroup = function(parentIndex)
					      {
					    	 
					    	 var isExists;
								function checkExistsOrNot(arr, val) {
									return arr
											.some(function(arr) {
												return (arr.organismGroupId == val);
											});
								}
								
								
							
								isExists = checkExistsOrNot(
										$scope.listMicroscopicExaMaster[parentIndex].listMicroscopicExaminationDetailsMasters,$scope.organismGroupId);	
							
							if(isExists)
								{
								 growl.error("Organism Group is already Exists.",{title : 'Error!'});
								 $scope.organismGroupId = "";
								}
							else
							{
								console.log("addOrganismByGroup parentIndex",parentIndex);
						    	 var microscopicExaminationDetails = angular.copy($scope.microscopicExaminationDetails);
						    	 var microscopicExaminationSubDetailsMaster = angular.copy($scope.microscopicExaminationSubDetailsMaster);
						    	 
						    	 $scope.miroOrgGroupList = $scope.microOrganismGroupList
									.filter(function(microOrgGroupObj) 
									{
										if (microOrgGroupObj.id ==  $scope.organismGroupId) 
										{
											microscopicExaminationDetails.microOrgGroupName = microOrgGroupObj.name;
											microscopicExaminationDetails.organismGroupId = microOrgGroupObj.id;
										}
									});
						    	 
						    	  $scope.getMicroOrganismList($scope.organismGroupId,$scope.orgId,true,parentIndex);
						    	  $scope.listMicroscopicExaMaster[parentIndex].listMicroscopicExaminationDetailsMasters.push(microscopicExaminationDetails);
						    	  if($scope.listMicroscopicExaMaster[parentIndex].listMicroscopicExaminationDetailsMasters!=null)
						    		  {
						    		     var lastIndex = $scope.listMicroscopicExaMaster[parentIndex].listMicroscopicExaminationDetailsMasters.length-1;
						    		     $scope.listMicroscopicExaMaster[parentIndex].listMicroscopicExaminationDetailsMasters[lastIndex].listMicroscopicExaminationSubDetailsMaster.push(microscopicExaminationSubDetailsMaster);
						    		     $scope.organismGroupId = "";
						    		  }	
							}
					    	 
					      }*/
					      
							/*$scope.getIncucationObjservationDetails = function(incubationObservationObj) {
								try {
							        *//** Code for Popup Details*//*
									$scope.incubationObservationObjList = $scope.incubationObservationList
									.filter(function(incuObservationObj) {
										if (incuObservationObj.labSampleDtlsId == incubationObservationObj.labSampleDtlsId) {
											$scope.incubationObservationObjList = incuObservationObj;
											return incuObservationObj;
										}
									});
									
									 $('#details').modal('show');
									if(incubationObservationObj.microbioResultEntryId==null)
										{
										  $scope.getMediaListBySampleId(incubationObservationObj.sampleId);
										    
											 if(incubationObservationObj.sampleStatusId == SEND_TO_REPORT_ENTRY)
												 {
												   $scope.getMediaListBySampleId(incubationObservationObj.sampleId);
												   $scope.getStataingList($scope.orgId);
												 }
											 else if(incubationObservationObj.microbioResultEntryId == null)
												 {
												 
													var data = {
															"orgId":$scope.orgId,
															"orgUnitId":$scope.orgUnitId,
															"sampleId":incubationObservationObj.sampleId,
															"sampleStatusId":incubationObservationObj.sampleStatusId,
															"microbioResultEntryId":incubationObservationObj.microbioResultEntryId,
															"labSampleDtlsId":incubationObservationObj.labSampleDtlsId
														};
												 var URI = BASE_URL + ROOT_URL
									            	+ LIS_MICROBIOLOGY
									               	+ INCUBATION_OBSERVATION_DETAILS;
										                  console.log("URI", URI);
										                  	GenericService
										                  			.serviceAction(METHOD_POST, URI, data)
												.then(
														function(response) {
															$rootScope.stopLoader();
															$scope.incubationObservationList = response.data.listObject;
														});
												 }
										}
									else if(incubationObservationObj.microbioResultEntryId != null)
										{
										   $scope.incubationStartTime = incubationObservationObj.incuStartTime;
										   $scope.incubationStartDate = moment(incubationObservationObj.incuStartDate).toDate();
										   $scope.incuDetailsObj.sampleId =incubationObservationObj.sampleId;
										   $scope.incuDetailsObj.tabLevel =$scope.firstTabLevel;
										   $scope.incuDetailsObj.microbioResultEntryId = incubationObservationObj.microbioResultEntryId;
										   $scope.incuDetailsObj.isIncubationComplete = incubationObservationObj.isIncubationComplete;
										   $scope.incuDetailsObj.isMacro_exaComplete = incubationObservationObj.isMacro_exaComplete;
										   $scope.incuDetailsObj.isMicro_exaComplete = incubationObservationObj.isMicro_exaComplete;
										   $scope.getIncuationPopupDetails($scope.incuDetailsObj);
										}
								   
									
									
								} catch (e) {
									console.log(e.message)
								}

							}
							
							
							$scope.getIncuationPopupDetails = function(incuDetailsObj)
							{
								 $scope.getMediaListBySampleId(incuDetailsObj.sampleId);
								   $scope.getStataingList($scope.orgId);
								   if(incuDetailsObj.tabLevel==INCUBATION_FLAG)
									   {
									   $scope.isComplete = incuDetailsObj.isIncubationComplete;
									   $scope.getIncubationDetails(incuDetailsObj.microbioResultEntryId);
									   }
								   else if(incuDetailsObj.tabLevel==MACROSCOPICEXAMINATION_FLAG)
									   {
									   $scope.isComplete =incuDetailsObj.isMacro_exaComplete;
									   $scope.getMacroscopicDetails(incuDetailsObj.microbioResultEntryId);
									   }
								   else if(incuDetailsObj.tabLevel==MICROSCOPICEXAMINATION_FLAG)
									   {
									   $scope.isComplete =incuDetailsObj.isMicro_exaComplete;
									   $scope.getMicroscopicDetails(incuDetailsObj.microbioResultEntryId);
									   }
							}*/
							

							
							
							/*$scope.saveDetails = function()
							{
								if($scope.firstTabLevel==1)
									
									{
									
									 console.log("this.incubationform.$valid",JSON.stringify(this.incubationform.$valid));
									$scope.$broadcast('show-errors-check-validity');
									if (this.incubationform.$valid)
										{
										   $scope.saveIncubationObservationDetails($scope.firstTabLevel,0);
										}else{
											 $('#details').modal('show');
										}
									
									}
								else if($scope.firstTabLevel==2)
									{
									
									
									$scope.$broadcast('show-errors-check-validity');
									if (this.macroscopicform.$valid)
										{
										 $scope.saveIncubationObservationDetails($scope.firstTabLevel,0);
										}else{
											 $('#details').modal('show');
										}
									
									 
									}
								else if($scope.firstTabLevel==3)
								{
									$scope.$broadcast('show-errors-check-validity');
									if (this.microscopicform.$valid)
									{
										$scope.saveIncubationObservationDetails($scope.firstTabLevel,0);
									}else{
										 $('#details').modal('show');
									}
									
								}
							}*/
							
							
							/*$scope.finalRelease = function()
							{
								if($scope.firstTabLevel==1)
									
								{
									$scope.$broadcast('show-errors-check-validity');
									if (this.incubationform.$valid)
										{
										  $scope.saveIncubationObservationDetails($scope.firstTabLevel,FINAL_REPORT_RELEASED);
										}else{
											 $('#details').modal('show');
										}
									
									}
									
								else if($scope.firstTabLevel==2)
									{
									$scope.$broadcast('show-errors-check-validity');
									if (this.macroscopicform.$valid)
										{
										 $scope.saveIncubationObservationDetails($scope.firstTabLevel,FINAL_REPORT_RELEASED);
										}else{
											 $('#details').modal('show');
										}
									
									 
									}
								else if($scope.firstTabLevel==3)
								{
									$scope.$broadcast('show-errors-check-validity');
									if (this.microscopicform.$valid)
									{
										$scope.saveIncubationObservationDetails($scope.firstTabLevel,FINAL_REPORT_RELEASED);
									}else{
										 $('#details').modal('show');
									}
									
								}
							}
							
							
							$scope.partialRelease = function()
							{
								if($scope.firstTabLevel==1){
									$scope.$broadcast('show-errors-check-validity');
									if (this.incubationform.$valid)
										{
										$scope.saveIncubationObservationDetails($scope.firstTabLevel,PARTIAL_REPORT_RELEASED);
										}else{
											 $('#details').modal('show');
										}
									
								}
								else if($scope.firstTabLevel==2)
									{
									
									$scope.$broadcast('show-errors-check-validity');
									if (this.macroscopicform.$valid)
										{
										 $scope.saveIncubationObservationDetails($scope.firstTabLevel,PARTIAL_REPORT_RELEASED);
										}else{
											 $('#details').modal('show');
										}
									 
									}
								else if($scope.firstTabLevel==3)
								{
									$scope.$broadcast('show-errors-check-validity');
									if (this.microscopicform.$valid)
									{
										$scope.saveIncubationObservationDetails($scope.firstTabLevel,PARTIAL_REPORT_RELEASED);
									}else{
										 $('#details').modal('show');
									}
								 
									
								}
							}
							
							$scope.releaseForSensitivity = function()
							{
								
								if($scope.firstTabLevel==1){
									$scope.$broadcast('show-errors-check-validity');
									if (this.incubationform.$valid)
										{
										$scope.saveIncubationObservationDetails($scope.firstTabLevel,RELEASE_FOR_SENSITIVITY_ENTRY);
										}else{
											 $('#details').modal('show');
										}
									
								}
								else if($scope.firstTabLevel==2)
									{
									
									$scope.$broadcast('show-errors-check-validity');
									if (this.macroscopicform.$valid)
										{
										 $scope.saveIncubationObservationDetails($scope.firstTabLevel,RELEASE_FOR_SENSITIVITY_ENTRY);
										}else{
											 $('#details').modal('show');
										}
									 
									}
								else if($scope.firstTabLevel==3)
								{
									$scope.$broadcast('show-errors-check-validity');
									if (this.microscopicform.$valid)
									{
										$scope.saveIncubationObservationDetails($scope.firstTabLevel,RELEASE_FOR_SENSITIVITY_ENTRY);
									}else{
										 $('#details').modal('show');
									}
									
								}
							}*/
							
					/*		

							$scope.saveIncubationObservationDetails = function(incuObservationFlag,sampleStatusId) 
							{
								 $scope.isComplete = $scope.isComplete==undefined?"":$scope.isComplete;
								$scope.microbioResultEntryMaster =  $scope.incubationObservationObjList[0];
								$scope.microbioResultEntryMaster.orgId = $scope.orgId;
								$scope.microbioResultEntryMaster.orgUnitId = $scope.orgUnitId;
								$scope.microbioResultEntryMaster.createdBy = $scope.createdBy;
								$scope.microbioResultEntryMaster.sampleStatusId = sampleStatusId;
								$scope.microbioResultEntryMaster.incuObservationFlag = incuObservationFlag;
								$scope.microbioResultEntryMaster.isDeleted = 'N';
								if(incuObservationFlag==INCUBATION_FLAG)
									{
									
									 $scope.microbioResultEntryMaster.incubationStartDate = $scope.incubationStartDate.getTime();
									 $scope.microbioResultEntryMaster.incubationStartTime = $scope.incubationStartTime;
									 $scope.microbioResultEntryMaster.isIncubationComplete = ($scope.isComplete=="")?'N':$scope.isComplete;
									 $scope.microbioResultEntryMaster.isMacro_exaComplete = 'N';
									 $scope.microbioResultEntryMaster.isMicro_exaComplete = 'N';
									 $scope.microbioResultEntryMaster.listMicrobioResultDetailsMaster = $scope.listMicrobioResultDtlsMaster;
									 $scope.microbioResultEntryMaster.listMicroscopicExaminationMaster = [];
									 console.log("$scope.listMicrobioResultDtlsMaster",JSON.stringify($scope.listMicrobioResultDtlsMaster));
									}
								else if(incuObservationFlag==MACROSCOPICEXAMINATION_FLAG)
									{
									  $scope.microbioResultEntryMaster.isIncubationComplete = "Y";
									  $scope.microbioResultEntryMaster.isMacro_exaComplete = ($scope.isComplete=="")?'N':$scope.isComplete;
									  $scope.microbioResultEntryMaster.microscopicExamination = $scope.microscopicExamination;
									  $scope.microbioResultEntryMaster.listMicroscopicExaminationMaster = [];
									  $scope.microbioResultEntryMaster.listMicrobioResultDetailsMaster = [];
									  
								    }
								else if(incuObservationFlag==MICROSCOPICEXAMINATION_FLAG)
								{
									$scope.microbioResultEntryMaster.isIncubationComplete ="Y";
									$scope.microbioResultEntryMaster.isMacro_exaComplete  ="Y";
									$scope.microbioResultEntryMaster.isMicro_exaComplete = ($scope.isComplete=="")?'N':$scope.isComplete;
									$scope.microbioResultEntryMaster.listMicroscopicExaminationMaster = $scope.listMicroscopicExaMaster;
									$scope.microbioResultEntryMaster.listMicrobioResultDetailsMaster =[];
								}
							
								$rootScope.startLoader();
							    try {
									console.log("$scope.microbioResultEntryMaster",JSON.stringify($scope.microbioResultEntryMaster));
									var URI = BASE_URL + ROOT_URL
									        + LIS_MICROBIOLOGY
											+ SAVE_INCUBATION_OBSERVATION_LIST;
									console.log("SAVE_OBJSERVATION_DETAILS", URI);
									 $('#details').modal('hide');
									GenericService
											.serviceAction(METHOD_POST, URI,
													$scope.microbioResultEntryMaster)
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
															$scope.listMicrobioResultDtlsMaster = [];
															$scope.listMicroscopicExaMaster = [];
															$scope.listMicroscopicExaDetlsMasters = [];
															$scope.listMicroscopicExaminationSubDetailsMaster = [];
															$scope.initIncucationObjservationList($scope.orgId,
																	$scope.orgUnitId, $scope.offset,
																	$scope.noOfRecordsPerPage);
														} else if (response.data.status == 'error') 
														{
															$rootScope.stopLoader();
															growl.error(response.data.message,{title : 'Error!'});
														}
													});
								} catch (e) {
									console.log("Exception", e.message);
								}
							}
							
							$scope.getIncubationDetails = function(microbioResultEntryId) {
								try {
									$rootScope.startLoader();
									var data = {
											"orgId":$scope.orgId,
											"orgUnitId":$scope.orgUnitId,
											"microbioResultEntryId":microbioResultEntryId,
										};
									var URI = BASE_URL + ROOT_URL
								            	+ LIS_MICROBIOLOGY
								               	+ GET_INCUBATION_DETAILS;
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
							
							$scope.getMacroscopicDetails = function(microbioResultEntryId) {
								try {
									$rootScope.startLoader();
									var data = {
											"orgId":$scope.orgId,
											"orgUnitId":$scope.orgUnitId,
											"microbioResultEntryId":microbioResultEntryId,
										};
									var URI = BASE_URL + ROOT_URL
								            	+ LIS_MICROBIOLOGY
								               	+ GET_MACROSOCIPIC_DETAILS;
									console.log("URI", URI);

									GenericService
											.serviceAction(METHOD_POST, URI, data)
											.then(
													function(response) {
														$rootScope.stopLoader();
														$scope.microbioResultEntryMaster = response.data.object;
														$scope.microscopicExamination = response.data.object.microscopicExamination;
													});
								} catch (e) {
									console.log(e.message)
								}

							}
							
							
							$scope.getMicroscopicDetails = function(microbioResultEntryId) {
								try 
								{
									var data = {
											"orgId":$scope.orgId,
											"orgUnitId":$scope.orgUnitId,
											"microbioResultEntryId":microbioResultEntryId,
										};
									var URI = BASE_URL + ROOT_URL
								            	+ LIS_MICROBIOLOGY
								               	+ GET_MICROSOCIPIC_DETAILS;
									console.log("URI", URI);

									GenericService
											.serviceAction(METHOD_POST, URI, data)
											.then(
													function(response) {
														$rootScope.stopLoader();
														$scope.listMicroscopicExaMaster = response.data.listObject;
														//$scope.getMicroOrganismList(organismGroupId,orgId)
														
														angular
														.forEach(
																$scope.listMicroscopicExaMaster,
																function(value, key) 
																{
																	value.organismGroupId = value.listMicroscopicExaminationDetailsMasters[0].organismGroupId;
																	var organismGroupId = value.listMicroscopicExaminationDetailsMasters[0].organismGroupId;
																	var promise =  $scope.getMicroOrganismList(organismGroupId,$scope.orgId,false,null);
																	promise.then(function(commonList)
																			{
																				if (commonList != null)
																					{
																					    value.listCommonDto = commonList;
																						console.log("value.listCommonDto", value.listCommonDto);
																					}
																			}, function(commonList)
																			{
																				console.log("ERROR FETCHING SUPER organism Group", commonList);
																			});
																});
														
														
													});
								} catch (e) {
									console.log(e.message)
								}

							}*/
							
							$scope.plzSelectIncubationPeriod = 'Please Select Incubation Period.';
							$scope.plzSelectIncubationStartDate= 'Please Select Incubation Start Date.';
							$scope.plzSelectIncubationStartTime= 'Please Select Incubation StartTime.';
							$scope.plzSelectIncubationName= 'Please Select Incubation Name.';
							$scope.plzSelectMediaName= 'Please Select Media Name.';
							$scope.microscopicExaminationDetials = 'Microscopic Examination Details Requried.';
							$scope.plzSelectStainingName= 'Please Select Staining Name.';
							$scope.plzSelectOrganismGroup= 'Please Select Organism Group.';
						} ]);