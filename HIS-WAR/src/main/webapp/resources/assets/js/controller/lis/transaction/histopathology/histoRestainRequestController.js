
/**
 * @author Ganesh
 */

angular
		.module('myApp')
		.controller(
				"histoRestainRequestController",
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

							$rootScope.loginpage = true;
							$scope.LISDynamicLabel = "Specimen Receipt";

							/** Variables for pagination */
							$scope.offset = 0;
							$scope.noOfRecordsPerPage = 10;
							$scope.specimenReceiptList = [];
							$scope.totalNoOfRecords;
							$scope.searchKeyword;
							$scope.commonListCount;

							$scope.orgId = 1;
							$scope.orgUnitId = 1;
							$scope.createdBy = 1;
							$scope.updatedBy = 1;
							$scope.deptId = LAB_DEPT ;
							$scope.subDeptId = HISTOPATHOLOGY_DEPT;
							
							
							
							
							$scope.listTRestainRequestDetailsMaster =[];
							
							 $scope.tSubSpecimanMaster = {
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
                            		  "age" : "",
                            		  "templateResId":"",
                            		  "listTGrossMaster" : []
                            		}

							

				
							  $scope.patientDetails = {
										 "uhIdNumber":$stateParams.subSpecimenObj.uhid,
										 "patientName":$stateParams.subSpecimenObj.patientDetails,
										 "genderCode":$stateParams.subSpecimenObj.genderName,
										 "age":$stateParams.subSpecimenObj.age,
										 "birthDate":moment($stateParams.subSpecimenObj.dob).format('DD-MM-YYYY')
								   }
						
							
							
							
							  
							  $scope.tRestainRequestMaster =  {
								  "tRestainingReqId" : "",
								  "orgId" : $scope.orgId,
								  "orgUnitId" : $scope.orgUnitId,
								  "labSampleDtlsId" : $stateParams.subSpecimenObj.labSampleDtlsId,
								  "tSubSpecimanId" : $stateParams.subSpecimenObj.tSubSpecimanId,
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
							  
							  $scope.tRestainRequestDetailsMaster = 
							  { 
								  "tRestainingDetailId" : "",
								  "tRestainingSubDetailId":"",
								  "orgId" : $scope.orgId,
								  "orgUnitId" : $scope.orgUnitId,
								  "tRestainingReqId" : "",
								  "labSampleDtlsId" : "",
								  "tGrossId" : "",
								  "tBlockId" : "",
								  "tSlideId" : "",
								  "blockNo" : "",
								  "grossNo" : "",
							      "createdBy" : "1",
								  "createdDate" : "",
								  "updatedBy" : "",
								  "updatedDate" : "",
								  "isDeleted" : "N",
								  "subSpecimanName" : "",
								  "subSpcimanNo" : "",
								  "listSlidesNo" : "",
								  "againstSlide" : "",
								  "noOfSlides" : "",
								  "stainingName" : "",
								  "remark" : "",
								  "isNew" :"N",
								  "listTRestainingReqSubDetailsMaster" : []
								  }
							  
							  $scope.tRestainingReqSubDetailsMaster={
									  "tRestainingSubDetailId" : "",
									  "orgId" : $scope.orgId,
									  "orgUnitId" : $scope.orgUnitId,
									  "tRestainingDetailId" : "",
									  "tSlideId" : "",
									  "slideNo"  :"",
									  "labSampleDtlsId" : "",
									  "stainingId" : "",
									  "remark" : "",
								      "createdBy" : "1",
									  "createdDate" : "",
									  "updatedBy" : "",
									  "updatedDate" : "",
									  "isDeleted" : "N",
									  "isNew" : "N",
									  "isSlideCreated":"N"	  
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
														function(response) 
														{
															$scope.stainingList  = response.data.listObject;
															console.log("$scope.stainingList",JSON.stringify($scope.stainingList));
														});
									} catch (e) {
										console.log(e.message)
									}

								}
								
								$scope.getStainingList(); 
							  
							  
							$scope.getRestainingRequestDetails = function() 
							{
								$scope.tSubSpecimanMaster = $stateParams.subSpecimenObj;
								console.log("$scope.tSubSpecimanMaster",$scope.tSubSpecimanMaster);
								try {
									$rootScope.startLoader();
									var URI = BASE_URL 
											+ ROOT_URL
											+ LIS_HISTOPATHOLOGY
											+ HISTO_RESTAIN_REQUEST_DETAILS;
									console.log("URI", URI);
									
									GenericService
											.serviceAction(METHOD_POST, URI, $scope.tSubSpecimanMaster)
											.then(
													function(response) 
													{
														$rootScope.stopLoader();
														$scope.listTRestainRequestDetailsMaster = [];
														$scope.listTRestainRequestDetailsMaster = response.data.listObject;
														angular.forEach(response.data.listObject, function(grossObj, key) 
															{
															 angular.forEach(grossObj.listTRestainingRequestDetailsMasterDto, function(blockObj, key) 
																	{
																     blockObj.orgId = $scope.orgId;
																     blockObj.orgUnitId = $scope.orgUnitId;
																     blockObj.createdBy = $scope.createdBy;
																     blockObj.isDeleted = 'N';
																     blockObj.listTRestainingReqSubDetailsMaster = [];
																     /*var tRestainingReqSubDetailsMaster = angular.copy($scope.tRestainingReqSubDetailsMaster);
																      blockObj.listTRestainingReqSubDetailsMaster.push(tRestainingReqSubDetailsMaster);*/
																	});
															});
														
														console.log("$scope.listTRestainRequestDetailsMaster",JSON.stringify($scope.listTRestainRequestDetailsMaster));
													});
								} catch (e) {
									console.log(e.message)
								}

							}
							 $scope.getRestainingRequestDetails(); 
							 
							 
							 $scope.remark ="";
							 $scope.tSlideId ="";
							 $scope.stainingId ="";
							 $scope.noOfslide = "";
							 $scope.removeRestainSlides = function(grossIndex,blockIndex,slideIndex)
							 {
								 if(grossIndex!=null&&blockIndex!=null,slideIndex!=null)
									 {
									 $scope.listTRestainRequestDetailsMaster[$scope.grossIndex]
				                      .listTRestainingRequestDetailsMasterDto[$scope.blockIndex]
				                      .listTRestainingReqSubDetailsMaster.splice(slideIndex,1);
									 growl.success("Slide Deleted Succesfully.",
												{
													title : 'Success!'
												});
								 }
								 else{
									 growl.error("Failed To Delete Slide.",
												{
													title : 'Error!'
												});
								 }
									 }
								
							 
							 $scope.getSlidesAgainstExisting = function(grossIndex,blockIndex)
							 {
								 $scope.remark ="";
								 $scope.tSlideId ="";
								 $scope.stainingId ="";
								 $scope.noOfslide = "";
								 if(grossIndex!=null&&blockIndex!=null )
									 {
									   $scope.grossIndex = grossIndex;
									   $scope.blockIndex = blockIndex;
									   $scope.slideList = $scope.listTRestainRequestDetailsMaster[grossIndex].listTRestainingRequestDetailsMasterDto[blockIndex].listSlidesNo;
									   $('#existingSlideModal').modal('show');
									 }
								 else
								 {
									 $('#existingSlideModal').modal('hide');
									 growl.error("Oops something went wrong.",
												{
													title : 'Error!'
												});
								 }
								
							 }
							 
							 
							 $scope.noOfSlideAdd = function()
							 {
								 $scope.noOfSlideList = [];
								 var noOfSlidesObj ={
										 "id" :"",
										 "no" :""
								 };
								
									 for (var i = 1; i <11; i++) 
									   {
										 var noOfSlidesObj = angular.copy(noOfSlidesObj);
										 noOfSlidesObj.id = i;
										 noOfSlidesObj.no = i;
										 $scope.noOfSlideList.push(noOfSlidesObj);
									   }
							 }
						
							 
							 $scope.getSlidesAgainstNew = function(grossIndex,blockIndex)
							 {
								 $scope.listTRestainingReqSubDetailsMaster = [];
								 $scope.remark ="";
								 $scope.tSlideId ="";
								 $scope.stainingId ="";
								 $scope.noOfslide = "";
								 if(grossIndex!=null&&blockIndex!=null )
								 {	
								   $scope.noOfSlideAdd();
								   $scope.grossIndex = grossIndex;
								   $scope.blockIndex = blockIndex;
								   $('#newSlideModal').modal('show');
								 }
							 else
							 { 
								   $('#newSlideModal').modal('hide');
								 growl.error("Oops something went wrong.",
											{
												title : 'Error!'
											});
							 }
								 
							 }
							 
							 
							 $scope.addSlidesAgainstExisting = function()
							 {
								 var tRestainingReqSubDetailsMaster = angular.copy($scope.tRestainingReqSubDetailsMaster);
								 tRestainingReqSubDetailsMaster.tSlideId = $scope.tSlideId;
								 tRestainingReqSubDetailsMaster.stainingId =  $scope.stainingId;
								 tRestainingReqSubDetailsMaster.remark = $scope.remark;
								 tRestainingReqSubDetailsMaster.slideNo = $scope.slideList.filter(slide => slide.id == $scope.tSlideId)[0].name;
								 tRestainingReqSubDetailsMaster.isNew = 'N';
								 $scope.listTRestainRequestDetailsMaster[$scope.grossIndex]
								                      .listTRestainingRequestDetailsMasterDto[$scope.blockIndex]
								                      .listTRestainingReqSubDetailsMaster.push(tRestainingReqSubDetailsMaster);
								 $('#existingSlideModal').modal('hide');
								 growl.success("Slide Added Succesfully.",
											{
												title : 'Success!'
											});
							 }
							 
							 
							 
							
							 
							 
							 $scope.selectNewSlides = function(noOfSlides)
							 { 
								 if(noOfSlides!=null)
									 {
									 $scope.listTRestainingReqSubDetailsMaster = [];
									 for (var i = 0; i <noOfSlides; i++) 
									   {
										 var tRestainingReqSubDetailsMaster = angular.copy($scope.tRestainingReqSubDetailsMaster);
										 tRestainingReqSubDetailsMaster.isNew = 'Y';
										 $scope.listTRestainingReqSubDetailsMaster.push(tRestainingReqSubDetailsMaster);
									   }
									 }
							 }
							 
							 $scope.addSlidesAgainstNew = function(noOfSlides)
							 { 
								 if($scope.listTRestainingReqSubDetailsMaster.length>0)
								  {
									  $scope.listTRestainRequestDetailsMaster[$scope.grossIndex]
				                      .listTRestainingRequestDetailsMasterDto[$scope.blockIndex]
				                      .listTRestainingReqSubDetailsMaster =  $scope.listTRestainRequestDetailsMaster[$scope.grossIndex]
				                      .listTRestainingRequestDetailsMasterDto[$scope.blockIndex]
				                      .listTRestainingReqSubDetailsMaster.concat($scope.listTRestainingReqSubDetailsMaster);
									   $('#newSlideModal').modal('hide');
									   growl.success("New Slides Added Successfully.",
												{
													title : 'Success!'
												});
								  }
								 else{
									 growl.error("Please Add Some Slides.",
												{
													title : 'Error!'
												});
								 }
							 }
							
							 
							 $scope.generateReportCreationUrl = function()
								{
								  if($scope.tSubSpecimanMaster!=null)
									  {
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

							$scope.saveRestainRequestDetails = function() 
							{
								var listTRestainingRequestDetailsMst = [];
								angular.forEach($scope.listTRestainRequestDetailsMaster, function(tGrossObj, key) 
										{
									  angular.forEach(tGrossObj.listTRestainingRequestDetailsMasterDto, function(restainReqDtlsObj, key) 
											{
										      if(restainReqDtlsObj.listTRestainingReqSubDetailsMaster.length>0)
										        {
										          listTRestainingRequestDetailsMst.push(restainReqDtlsObj); 	 
										        }
											});
										});
								$scope.tRestainRequestMaster.listTRestainingRequestDetailsMaster  = listTRestainingRequestDetailsMst;
								$rootScope.startLoader();
								try {
								
									console.log("$scope.tRestainRequestMaster",JSON.stringify($scope.tRestainRequestMaster));
									var URI = BASE_URL 
											+ ROOT_URL
											+ LIS_HISTOPATHOLOGY
											+ HISTO_RESTAIN_REQUEST;
									console.log("SAVE_RESTAIN_REQUEST", URI);
								 GenericService
											.serviceAction(METHOD_POST, URI,
													$scope.tRestainRequestMaster)
											.then(
													function(response) {
														if (response.data.status == 'success') 
														{
															
															$state.go('restainingRequest',
																	{
																	  subSpecimenObj : $scope.tSubSpecimanMaster,
																	});
															growl.success(response.data.message,
																{
																	title : 'Success!'
																});
															$scope
																	.$broadcast('show-errors-reset');
															$rootScope.stopLoader();

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

						} ]);