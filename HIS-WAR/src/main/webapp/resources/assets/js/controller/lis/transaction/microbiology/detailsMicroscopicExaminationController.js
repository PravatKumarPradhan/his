
/**
 * @author Ganesh
 */

angular
		.module('myApp')
		.controller(
				"detailsMicroscopicExaminationController",
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
							$scope.totalNoOfRecords;
							$scope.searchKeyword;
							$scope.commonListCount;
							$scope.orgId = 1;
							$scope.orgUnitId = 1;
							$scope.createdBy = 1;
							$scope.updatedBy = 1;
							$scope.deptId = LAB_DEPT;
							$scope.subDeptId = BIOCHEM_DEPT;
							$('[data-toggle="deltatooltip"]').tooltip();
							
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
					
							
							
							$scope.getMicroscopicExamination = function(labSampleDtlsId) {
								try {
									$rootScope.startLoader();
									var data = {};
									var URI = BASE_URL + ROOT_URL
								            	+ LIS_MICROBIOLOGY
								               	+ GET_MICROSCOPIC_EXAMINATION+S+labSampleDtlsId+S+$scope.orgId+S+$scope.orgUnitId;
									console.log("URI", URI);
									GenericService
											.serviceAction(METHOD_GET, URI, data)
											.then(
													function(response) {
														$rootScope.stopLoader();
														$scope.listMicroscopicExaMaster = response.data.listObject==null?[]:response.data.listObject;
														 angular.forEach($scope.listMicroscopicExaMaster, function(valueObj, parent)
								                        		   {
															 angular.forEach(valueObj.listMicroscopicExaminationDetailsMasters, function(obj, child)
									                        		   {
																          $scope.getMicroOrganismList(obj.organismGroupId,$scope.orgId,true,parent,child);
									                        	 });
								                        	 });
														 console.log("$scope.listMicroscopicExaMaster",JSON.stringify($scope.listMicroscopicExaMaster));
														
													});
								} catch (e) {
									console.log(e.message)
								}

							}
							
							/**Redirect Page Code*/
							if(angular.equals({},$stateParams.microExaObj))
 							{
								/*$state.go('microscopicExamination');*/
								$state.go('microscopicExamination');
							} else 
							{
								$scope.sampleDtls={};
								$scope.sampleDtls  = $stateParams.microExaObj;
								$scope.getMicroscopicExamination($stateParams.microExaObj.labSampleDtlsId);
								/*if($stateParams.incuObsrvationObj.tIncubationId!=null)
									{
									$scope.getIncubationDetails($stateParams.incuObsrvationObj.tIncubationId);
									}*/
							}
							
							
							/******* END TAB FOR ALL PAGES IN TREE LEVEL *******/
		
							
							    /*orginal List*/
								$scope.listMicroscopicExaMaster = [];
								$scope.listMicroscopicExaDetlsMasters = [];
								$scope.listMicroscopicExaminationSubDetailsMaster = [];
								
								/*clone list*/
								$scope.listMicroscopicExaMst = [];
								$scope.listMicroscopicExaDetlsMst=[];
								$scope.listMicroscopicExaminationSubDetailsMst=[];
						
							
								$scope.isCompleted="N";
						
								
								$scope.microscopicExaminationMaster = 
									{
										  "microscopicExaId" : "",
										  "orgId" : $scope.orgId,
										  "orgUnitId" : $scope.orgUnitId,
										  "stainingId" : "",
										  "stainingName":"",
										  "organismGroupId" : "",
										  "labSampleDtlsId" : "",
										  "createdBy" : $scope.createdBy,
										  "createdDate" : "",
										  "updatedBy" : "",
										  "updatedDate" : "",
										  "isDeleted" : "N",
										  "isCompleted":"N",
										  "listMicroscopicExaminationDetailsMasters" : $scope.listMicroscopicExaDetlsMasters
									}
								
								
								$scope.microscopicExaminationDetails = 
								{
									  "examinationDetailsId" : "",
									  "microscopicExaId":"",
									  "orgId" : $scope.orgId,
									  "orgUnitId" : $scope.orgUnitId,
									  "organismGroupId" : "",
									  "microOrgGroupName":"",
									  "createdBy" : $scope.createdBy,
									  "createdDate" : "",
									  "updatedBy" : "",
									  "updatedDate" : "",
									  "isDeleted" : "N",
									  "microscopicExaId":"",
									  "listCommonDto"  :"",
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
							
							
							$scope.getMicroOrganismList = function(organismGroupId,orgId,flag,parentIndex,childIndex)
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
																$scope.listMicroscopicExaMaster[parentIndex].listMicroscopicExaminationDetailsMasters[childIndex].listCommonDto =response.data.listObject;
																console.log("sfsf",JSON.stringify($scope.listMicroscopicExaMaster[parentIndex].listMicroscopicExaminationDetailsMasters[childIndex].listCommonDto));
																
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
							
							
							$scope.getStataingList = function()
							{
								var data = "";
								try{
									var URI = BASE_URL + ROOT_URL
								            	+ LIS_COMMON
								               	+ MICRO_STAINING_LIST_BY_ORG_ID+S+$scope.orgId;
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
							  
							$scope.getStataingList();
					      
					      $scope.addMicroOrgsism = function(parentIndex,childIndex)
							{ 
					    	       try {
					    	    	     var microscopicExaminationSubDetailsMaster = angular.copy($scope.microscopicExaminationSubDetailsMaster);
					    	    	     microscopicExaminationSubDetailsMaster.examinationDetailsId= $scope.listMicroscopicExaMaster[parentIndex].listMicroscopicExaminationDetailsMasters[childIndex].examinationDetailsId;
					    	    	     $scope.listMicroscopicExaMaster[parentIndex].listMicroscopicExaminationDetailsMasters[childIndex].listMicroscopicExaminationSubDetailsMaster.push(microscopicExaminationSubDetailsMaster);
								} catch (e) {
									console.log();
								}
						        	 
							};

					      $scope.removeMicroOrgnism = function(parentIndex,childIndex,subChildDeleteIndex)
							{ 
					    	  /*if( $scope.listMicroscopicExaMaster[parentIndex].listMicroscopicExaminationDetailsMasters[childIndex].listMicroscopicExaminationSubDetailsMaster[subChildDeleteIndex].microexaSubDetailsId==null||
					    			  $scope.listMicroscopicExaMaster[parentIndex].listMicroscopicExaminationDetailsMasters[childIndex].listMicroscopicExaminationSubDetailsMaster[subChildDeleteIndex].microexaSubDetailsId=="")
					    		  {
					    		  $scope.listMicroscopicExaMaster[parentIndex].listMicroscopicExaminationDetailsMasters[childIndex].listMicroscopicExaminationSubDetailsMaster.splice(subChildDeleteIndex, 1);
					    		  }
					    	  else{
					    		  var microscopicExaminationSubDetailsMaster =
					    			  $scope.listMicroscopicExaMaster[parentIndex].listMicroscopicExaminationDetailsMasters[childIndex].listMicroscopicExaminationSubDetailsMaster[subChildDeleteIndex];
					    		       microscopicExaminationSubDetailsMaster.isDeleted = 'Y';
			                           $scope.listMicroscopicExaminationSubDetailsMst.push(microscopicExaminationSubDetailsMaster);
					    		      $scope.listMicroscopicExaMaster[parentIndex].listMicroscopicExaminationDetailsMasters[childIndex].listMicroscopicExaminationSubDetailsMaster.splice(subChildDeleteIndex, 1);
					    		      
					    	  }*/
					    	  $scope.listMicroscopicExaMaster[parentIndex].listMicroscopicExaminationDetailsMasters[childIndex].listMicroscopicExaminationSubDetailsMaster[subChildDeleteIndex].isDeleted='Y';
			    	    	    
				        	}	
							
					      
					      
					      //**Function to getStatining ID*//*
					      $scope.getStainingId = function(stainingId)
					      {
					    	  $scope.stainingId = stainingId;
					      };
					      
					      //**Function to get MicroOrganism Group idD*//*
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
								  microscopicExaminationMaster.labSampleDtlsId = $stateParams.microExaObj.labSampleDtlsId;
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
					     
					      
					      

						$scope.removeStain = function(parentIndex) 
						{
							if($scope.listMicroscopicExaMaster[parentIndex].microscopicExaId==null||
									$scope.listMicroscopicExaMaster[parentIndex].microscopicExaId=="")
								{
								  $scope.listMicroscopicExaMaster.splice(parentIndex,1);
								}
							else{
								  var microscopicExaminationMaster =
			                           $scope.listMicroscopicExaMaster[parentIndex];
								       microscopicExaminationMaster.isDeleted = 'Y';
			                           angular.forEach( $scope.listMicroscopicExaMaster[parentIndex].listMicroscopicExaminationDetailsMasters, function(detls, key)
			                        		   {
			                        	   detls.isDeleted = 'Y';
			                        	   angular.forEach(detls.listMicroscopicExaminationSubDetailsMaster, function(subDtls, key)
				                        		   {
			                        		   subDtls.isDeleted = 'Y';
				                        	 });
			                        	 });
			                           $scope.listMicroscopicExaMaster[parentIndex].isDeleted='Y';
			                           $scope.listMicroscopicExaMst.push(microscopicExaminationMaster);
			                           /*$scope.listMicroscopicExaMaster.splice(parentIndex,1);*/
							 }
                         
                           
						}

						$scope.removeGroup = function(parentIndex,childIndex) 
						{
							if($scope.listMicroscopicExaMaster[parentIndex].listMicroscopicExaminationDetailsMasters[childIndex].examinationDetailsId==null||
									$scope.listMicroscopicExaMaster[parentIndex].listMicroscopicExaminationDetailsMasters[childIndex].examinationDetailsId=="")
								{
								  $scope.listMicroscopicExaMaster[parentIndex].listMicroscopicExaminationDetailsMasters.splice(childIndex,1);
								}
							else{
								  var microscopicExaminationDetails =
			                           $scope.listMicroscopicExaMaster[parentIndex].listMicroscopicExaminationDetailsMasters[childIndex];
								       microscopicExaminationDetails.isDeleted = 'Y';
			                        	   angular.forEach($scope.listMicroscopicExaMaster[parentIndex].listMicroscopicExaminationDetailsMasters[childIndex].listMicroscopicExaminationSubDetailsMaster, function(value, key)
				                          {
			                        		   value.isDeleted = 'Y';
			                        	 });
			                        $scope.listMicroscopicExaDetlsMst.push(microscopicExaminationDetails);
			                       /*	$scope.listMicroscopicExaMaster[parentIndex].listMicroscopicExaminationDetailsMasters.splice(childIndex,1);*/
			                        $scope.listMicroscopicExaMaster[parentIndex].listMicroscopicExaminationDetailsMasters[childIndex].isDeleted='Y';
							 }
							
						}
					      
					     $scope.addOrganismByGroup = function(parentIndex)
					      {
					    	 console.log("parentIndex",parentIndex);
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
						    	 microscopicExaminationDetails.microscopicExaId = $scope.listMicroscopicExaMaster[parentIndex].microscopicExaId;
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
						    	 
						    	  $scope.listMicroscopicExaMaster[parentIndex].listMicroscopicExaminationDetailsMasters.push(microscopicExaminationDetails);
						    	  $scope.getMicroOrganismList($scope.organismGroupId,$scope.orgId,true,parentIndex,$scope.listMicroscopicExaMaster[parentIndex].listMicroscopicExaminationDetailsMasters.length-1);
						    	  if($scope.listMicroscopicExaMaster[parentIndex].listMicroscopicExaminationDetailsMasters!=null)
						    		  {
						    		     var lastIndex = $scope.listMicroscopicExaMaster[parentIndex].listMicroscopicExaminationDetailsMasters.length-1;
						    		     $scope.listMicroscopicExaMaster[parentIndex].listMicroscopicExaminationDetailsMasters[lastIndex].listMicroscopicExaminationSubDetailsMaster.push(microscopicExaminationSubDetailsMaster);
						    		     $scope.organismGroupId = "";
						    		  }	
							}
					    	 
					      }
					      
							/*$scope.getIncucationObjservationDetails = function(incubationObservationObj) {
								try {
							        //** Code for Popup Details*//*
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
							
							
							
							

							
							
							$scope.saveDetails = function()
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
							}
							
							
							$scope.finalRelease = function()
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
							

							$scope.saveMicroscopicExamination = function() 
							{
								
								$rootScope.startLoader();
							    try {
							    	 angular.forEach($scope.listMicroscopicExaMaster, function(value, key) {
							    		 value.createdDate = moment().toDate().getTime();
							    		 if(value.isDeleted == 'Y')
		                        		   {
		                        			   value.isCompleted =  'N';
		                        		   }
		                        		   else{
		                        			   value.isCompleted =  $scope.isCompleted;
		                        		   }
							    		});
									console.log("$scope.listMicroscopicExaMaster",JSON.stringify($scope.listMicroscopicExaMaster));
									var URI = BASE_URL + ROOT_URL
									        + LIS_MICROBIOLOGY
											+ SAVE_MICROSCOPIC_EXAMINATION;
									console.log("SAVE_MICROSCOPIC_EXA", URI);
									 $('#details').modal('hide');
									GenericService
											.serviceAction(METHOD_POST, URI,
													$scope.listMicroscopicExaMaster)
											.then(
													function(response) {
														if (response.data.status == 'success') 
														{
															      growl.success(
																			response.data.message,
																			{
																				title : 'Success!'
																			});
															$scope.$broadcast('show-errors-reset');
															$state.go('microscopicExamination');
														} else if (response.data.status == 'error') 
														{
															
															growl.error(response.data.message,{title : 'Error!'});
														}
														$rootScope.stopLoader();
													});
								} catch (e) {
									console.log("Exception", e.message);
								}
							}
							
								
							
						}]);