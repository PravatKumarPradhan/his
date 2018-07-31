
/**
 * @author Ganesh
 */

angular
		.module('myApp')
		.controller(
				"restainRequestWorklistDetailsController",
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
							$scope.LISDynamicLabel = "Block Creation Controller";

							/** Variables for pagination */
							$scope.offset = 0;
							$scope.noOfRecordsPerPage = 10;
							$scope.restainRequestDetailsList = [];
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
							 if(angular.equals({},$stateParams.restainReqMstObj))
								{
								  $state.go('microscopicExaminiationWorkList');
								}
							
							
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
								  "listTRestainingReqSubDetailsMaster" : []
								  }
						
							$scope.tRestainRequestMaster = $stateParams.restainReqMstObj;
							console.log("$scope.tRestainRequestMaster",$scope.tRestainRequestMaster);
							 $scope.patientDetails = {
									 "uhIdNumber":$stateParams.restainReqMstObj.uhid,
									 "patientName":$stateParams.restainReqMstObj.patientDetails,
									 "genderCode":$stateParams.restainReqMstObj.genderName,
									 "age":$stateParams.restainReqMstObj.age,
									 "birthDate":moment($stateParams.restainReqMstObj.dob).format('DD-MM-YYYY')
							   }
							 
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
							
							
							$scope.getRestainWorkListDetails = function() {
								try 
								{
									$rootScope.startLoader();
									var data ={}
									var URI = BASE_URL 
											+ ROOT_URL
											+ LIS_HISTOPATHOLOGY
											+ HISTO_RESTAIN_REQUEST_WORKLIST_DETAILS
											+S+$scope.tRestainRequestMaster.tRestainingReqId
											+S+$scope.orgId
											+S+$scope.orgUnitId;
									console.log("URI", URI);
									
									GenericService
											.serviceAction(METHOD_GET, URI, data)
											.then(
													function(response)
													{
														if (response.data.status == 'success') 
														{
															$rootScope.stopLoader();
															$scope.restainRequestDetailsList = response.data.listObject;
															console.log("$scope.restainRequestDetailsList",$scope.restainRequestDetailsList);
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
							$scope.getRestainWorkListDetails();
							
							$scope.getMaxSlideNumber = function(tBlockId,blockNo)
							{
							try{
								var data ={};
								var URI = BASE_URL 
										+ ROOT_URL
										+ LIS_HISTOPATHOLOGY
										+ GET_MAX_SLIDE_NO
										+S+$scope.orgId
										+S+$scope.orgUnitId
										+S+tBlockId;
								console.log("URI", URI);
								
								var slideObj = {"slideNum":"","slideNo":""};
								return GenericService
										.serviceAction(METHOD_GET, URI, data)
										.then(
												function(response)
												{
													if (response.data.status == 'success') 
													{
														$rootScope.stopLoader();
														$scope.maxSlide = response.data.object;
														++$scope.maxSlide
														  slideNo  = voucher_codes.generate({
							                    			    prefix:blockNo+SLIDE_PREFIX,
							                    			    inputNumber: $scope.maxSlide,
							                    			    length:NUMBER_WIDTH})[0];
														  console.log("slideNo",slideNo); 
														  slideObj.slideNum = $scope.maxSlide;
														  slideObj.slideNo = slideNo
														 return slideObj;
													} else if (response.data.status == 'error') 
													{
														growl.error("Something went Wrong",	{title : 'Error!'});
													}
													 return slideObj;
												});
							} catch (e)
							 {
								console.log(e.message)
							 }
							}
							
							$scope.getSlideCreationDetails = function(restainReqDtlsObj)
							{
							try{
								var data ={};
								$scope.histopathlogyNumber = $scope.tRestainRequestMaster.histopathlogyNumber;
								$scope.subSpecimanName = $scope.tRestainRequestMaster.specimanName;
								$scope.isNew = restainReqDtlsObj.isNew; //For Checking is new Slide or Not
								$rootScope.startLoader();
								var URI = BASE_URL 
										+ ROOT_URL
										+ LIS_HISTOPATHOLOGY
										+ HISTO_RESTAIN_REQUEST_WORKLIST_SLIDES
										+S+restainReqDtlsObj.tRestainingDetailId
										+S+restainReqDtlsObj.tRestainingSubDetailId
										+S+$scope.isNew
										+S+$scope.orgId
										+S+$scope.orgUnitId;
								console.log("URI", URI);
								
								GenericService
										.serviceAction(METHOD_GET, URI, data)
										.then(
												function(response)
												{
													if (response.data.status == 'success') 
													{
														$rootScope.stopLoader();
														$scope.slideCreationList = response.data.listObject;
													    if($scope.slideCreationList[0].listTBlockMaster[0].listTSlideMaster[0].slideNo == null)
													    	{
													    	var promise = 
													            $scope.getMaxSlideNumber($scope.slideCreationList[0].listTBlockMaster[0].tBlockId,$scope.slideCreationList[0].listTBlockMaster[0].blockNo);
													        promise.then(
													           function(slideObj) { 
															    	 $scope.slideCreationList[0].listTBlockMaster[0].listTSlideMaster[0].slideNo = slideObj.slideNo;
															    	 $scope.slideCreationList[0].listTBlockMaster[0].listTSlideMaster[0].slideNum = slideObj.slideNum;
													           },
													           function(slideObj) {
													               console.log('Faild to Load Slide', slideNo);
													           });
													    	}
														$('#restainSlides').modal('show');
														console.log("$scope.slideCreationList",$scope.slideCreationList);
													} else if (response.data.status == 'error') 
													{
														$rootScope.stopLoader();
														$('#restainSlides').modal('hide');
														growl.error("Something went Wrong",	{title : 'Error!'});
													}
												
												});
							} catch (e)
							 {
								console.log(e.message)
							 }
							}
							
							
							$scope.createRestainedSlide = function() 
							{
						        $scope.slideCreationList[0].listTRestainingRequestDetailsMasterDto = [];
								angular.forEach($scope.slideCreationList[0].listTBlockMaster[0].listTSlideMaster, function(slideObj, key)
										{
									    	      slideObj.listTMicroExaDetails =[];
									    	      slideObj.listTRestainingReqSubDetailsMaster =[];
									    	      slideObj.orgId = $scope.orgId;
									    	      slideObj.orgUnitId = $scope.orgUnitId;
									    	      slideObj.createdBy = $scope.createdBy;
									    	      slideObj.isRestained = $scope.isNew=='Y'?'N':'Y';
										});
								$scope.$broadcast('show-errors-check-validity');
								$rootScope.startLoader();
								try {
									var URI = BASE_URL 
											+ ROOT_URL
											+ LIS_HISTOPATHOLOGY
											+ HISTO_CREATE_SLIDE;
									console.log("new SLide Creation", URI);
									console.log("$scope.listGross",JSON.stringify($scope.slideCreationList));
									GenericService
											.serviceAction(METHOD_POST, URI,
													$scope.slideCreationList)
											.then(
													function(response) {
														if (response.data.status == 'success') 
														{
															$("#restainSlides").modal('hide');
															$scope.getRestainWorkListDetails();
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
							$scope.blockCaptureNoteReq = "Capture note is required.";	

						} ]);