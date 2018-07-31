
/**
 * @author Ganesh
 */

angular
		.module('myApp')
		.controller(
				"detailsMicroscopicExaminiationWorkListController",
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
							    
							
							  
							    $scope.tTMicroExaDetails ={
											 "tMicroExaId" : "",
											"orgId" : $scope.orgId,
											"orgUnitId" : $scope.orgUnitId,
											"tSlideId" : "",
											"labSampleDtlsId" : "",
											"stainingId" : "",
											"isComplete" : "",
											"createdBy" : $scope.createdBy,
											"captureNote" : "",
											"grossNo" : "",
											"blockNo" : "",
											"sendForStorage":"N"
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
							       $scope.tSubSpecimanMaster = $stateParams.subSpecimenObj;
							    
							    if(angular.equals({},$stateParams.subSpecimenObj))
								{
								  $state.go('microscopicExaminiationWorkList');
								}
							    
							       
							       $scope.patientDetails = {
											 "uhIdNumber":$stateParams.subSpecimenObj.uhid,
											 "patientName":$stateParams.subSpecimenObj.patientDetails,
											 "genderCode":$stateParams.subSpecimenObj.genderName,
											 "age":$stateParams.subSpecimenObj.age,
											 "birthDate":moment($stateParams.subSpecimenObj.dob).format('DD-MM-YYYY')
									   }
							       $scope.histoPathNo = $stateParams.subSpecimenObj.histopathlogyNumber;
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
							

							

							
							
           
					

							

							
							
							$scope.viewMicroScopeExaminations = function()
							{
								$state.go('detailsMicroscopicExaminiation',
										{
										  subSpecimenObj : $scope.tSubSpecimanMaster,
										});
					  
						
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
												$scope.listTMicroExaDetails,
												function(tMicroExaDetails) {
													tMicroExaDetails.isExaComplete = $scope.selectedAll;
												});
							}
							
							$scope.getSlideCreationDetails = function() {
								try 
								{
									$rootScope.startLoader();
									$scope.histopathlogyNumber = $stateParams.subSpecimenObj.histopathlogyNumber;
									$scope.subSpecimanName = $stateParams.subSpecimenObj.subSpecimanName;
									$scope.tSubSpecimanMaster = $stateParams.subSpecimenObj;
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
							
							
							
							$scope.getSlideCreationDetails();
							
							 // code for saveMicroExaDetails
							$scope.saveMicroExaDetails = function() 
							{
								$scope.$broadcast('show-errors-check-validity');
								console.log("$scope.microExaDetailsForm.$valid",$scope.microExaDetailsForm.$valid)
								if ($scope.microExaDetailsForm.$valid)
								{
								$scope.listTMicroExaDetls = [];
								$rootScope.startLoader();
								angular
										.forEach(
												$scope.listTMicroExaDetails,
												function(value, key) {
													if (value.isExaComplete == true && value.isComplete=='N') 
													{
														var labTMicroExaDetails = angular
																.copy($scope.tTMicroExaDetails);
														labTMicroExaDetails.labSampleDtlsId = value.labSampleDtlsId;
														labTMicroExaDetails.tSlideId = value.tSlideId;
														labTMicroExaDetails.captureNote = value.captureNote;
														labTMicroExaDetails.grossNo = value.grossNo;
														labTMicroExaDetails.blockNo = value.blockNo;
														labTMicroExaDetails.orgId = $scope.orgId;
														labTMicroExaDetails.orgUnitId = $scope.orgUnitId;
														labTMicroExaDetails.isComplete = 'Y';
														labTMicroExaDetails.sendForStorage = value.sendForStorage==null?'N':value.sendForStorage;
														labTMicroExaDetails.createdBy = $scope.createdBy;
																											
														$scope.listTMicroExaDetls.push(labTMicroExaDetails);
													}
												
												});
								
								try {
									console.log("$scope.listTMicroExaDetls",JSON.stringify($scope.listTMicroExaDetls));
									var URI = BASE_URL 
											+ ROOT_URL
											+ LIS_HISTOPATHOLOGY
											+ HISTO_MICRO_EXAM_WORK_LIST_CREATE_SLIDE;
									console.log("CREATE GROSS", URI);
									GenericService
											.serviceAction(METHOD_POST, URI,
													$scope.listTMicroExaDetls)
											.then(
													function(response) {
														if (response.data.status == 'success') 
														{
															$state.go('microscopicExaminiationWorkList');
															 growl.success(response.data.message,
																{
																	title : 'Success!'
																});
															$scope.$broadcast('show-errors-reset');

														} else if (response.data.status == 'error') 
														{
															$rootScope.stopLoader();
															$state.go('microscopicExaminiationWorkList');
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
							
							
							$scope.getMacroscopicExaminationTemplateDtls = function() {
								try {
									var data = "";
									var URI = BASE_URL + ROOT_URL
											+ LIS_HISTOPATHOLOGY + HISTO_GET_MACROSCOPIC_EXAM_DETIALS
											+ S + $scope.orgId
											+ S + $scope.orgUnitId
											+ S + $scope.tSubSpecimanMaster.tSubSpecimanId;
									console.log("URI", URI);
									GenericService.serviceAction("GET", URI, data)
											  .then(function(response) 
													{
														$scope.getMicroscopicExamData = response.data.object;
														console.log("$scope.getMicroscopicExamData",JSON.stringify($scope.getMicroscopicExamData));
													});
								   } catch (e) {
									console.log(e.message)
								}
							}
							
							

						} ]);