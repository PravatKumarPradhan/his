
/**
 * @author Ganesh
 */

angular
		.module('myApp')
		.controller(
				"microbiologyWorklistController",
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
							$scope.LISDynamicLabel = "Sample";

							/** Variables for pagination */
							$scope.offset = 0;
							$scope.noOfRecordsPerPage = 10;
							$scope.microbiologyWorklist = [];
							$scope.totalNoOfRecords;
							$scope.searchKeyword;
							$scope.commonListCount;

							$scope.orgId = 1;
							$scope.orgUnitId = 1;
							$scope.createdBy = 1;
							$scope.updatedBy = 1;
							$scope.deptId = LAB_DEPT ;
						    $scope.subDeptId = MICROBIOLOGY_DEPT;

							$scope.listLabSampleDtls = [];
							$scope.labSampleDtls = {
								"labSampleDtlsId" : "",
								"orgId" : $scope.orgId,
								"orgUnitId" : $scope.orgUnitId,
								"testId" : "",
								"deptId" : "",
								"subDeptId" : "",
								"deptName" : "",
								"labSampleId" : "",
								"sampleBarcode" : "",
								"sampleId" : "",
								"sampleNo" : "",
								"orderDetailsId" : "",
								"orderId" : "",
								"orderQty" : "",
								"serviceId" : "",
								"testCode" : "",
								"testDesc" : "",
								"sampleName" : "",
								"sampleVolume" : "",
								"unitName" : "",
								"profileId" : "",
								"panelCode":"",
								"packageId" : "",
								"isCentrifugationReq" : "",
								"isAlliquoteReq" : "",
								"sampleTypeId" : "",
								"containerId" : "",
								"containerName" : "",
								"sampleReqCount" : "",
								"samplePendingCount" : "",
								"sampleGenDatetime" : "",
								"sampleGenBy" : "",
								"currStatus" : "",
								"sampleStatusId" : "",
								"sampWrkStatusId":"",
								"isOutsourced" : "",
								"outsourcedLabId" : "",
								"sampleSendtocrDatetime" : "",
								"sampleSendtocrBy" : "",
								"sampleCollectionDatetime" : "",
								"sampleCollectionBy" : "",
								"sampleCentrifugationDatetime" : "",
								"sampleCentrifugationBy" : "",
								"sampleAcceptDatetime" : "",
								"sampleAcceptBy" : "",
								"sampleRejectDatetime" : "",
								"sampleRejectBy" : "",
								"sampleRejectReasonId" : "",
								"sampleRejectReason" : "",
								"sampleRecollectFlag" : "",
								"sampleRecollectAgainstId" : "",
								"createdBy" : $scope.createdBy,
								"createdDate" : "",
								"updatedBy" : "",
								"updatedDate" : "",
								"sampleTestTime":"",
								"patientVisitAge":"",
								"genderId":"",
								"visitType":"",
								"listTMicroSampleMediaDto":[]	
							}

							$scope.listTMicroSampleMediaDto = [];
							$scope.tMicroSampleMediaDto = {
									  "tSampleMediaId" :"",
									  "labSampleDtlsId" :"",
									  "orgId" :  $scope.orgId,
									  "orgUnitId" :  $scope.orgUnitId,
									  "mediaId" :  "",
									  "remark":"",
									  "isSelected" :  "",
									  "desc" :  "",
									  "createdBy" :  $scope.createdBy,
									  "createdDate" :  "",
									  "updatedBy" :  "",
									  "updatedDate" :  "",
									  "isDeleted" :  "N"
									}
							
							/** Sample Status List  */
							$scope.getSampleStatusList = function(orgId) {
								try {
									var data = "";
									var URI = BASE_URL + ROOT_URL
											+ LIS_COMMON
											+ GET_SAMPLE_STAUS_LIST + S + orgId;
									console.log("URI", URI);
									GenericService
											.serviceAction("GET", URI, data)
											.then(
													function(response)
													{
														$scope.sampleStatusList = response.data.listObject;
													});
								} catch (e) {
									console.log(e.message)
								}
							}
							$scope.getSampleStatusList($scope.orgId);
						
							
							$scope.showRejecPopup = function(index) {
								$scope.rejIndex = index;
								$("#pendingAcceptance").modal('show');
							}
							

							$scope.setNoOfRecords = function() {
								$scope.initMicrobiologyWorkList($scope.orgId,
										$scope.orgUnitId,$scope.deptId,$scope.offset,
										$scope.noOfRecordsPerPage);
							};

							$scope.initMicrobiologyWorkList = function(orgId,
									orgUnitId,deptId, offset, noOfRecordsPerPage) {
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
											+ LIS_MICROBIOLOGY
											+ MICROBIOLOGY_WORKLIST;

									var URI2 = BASE_URL
											+ ROOT_URL
											+ LIS_MICROBIOLOGY
											+ MICROBIOLOGY_WORKLIST_COUNT;

									console.log("URI1", URI1);
									console.log("URI2", URI2);
									promiseFactoryWithObject
											.setPromisesWithObject(URI1, URI2, METHOD_POST,
													METHOD_POST,data,data1)
											.then(
													function(response) {
														$rootScope.stopLoader();
														$scope.microbiologyWorklist = response[0].listObject;
														console.log("microbiologyWorklist",$scope.microbiologyWorklist);
														$scope.commonListCount = response[1].object;
														$scope.setPage(1,false);
													});
								} catch (e) {
									console.log(e.message);
								}
							}

							$scope.initMicrobiologyWorkList($scope.orgId,
									$scope.orgUnitId,$scope.deptId,$scope.offset,
									$scope.noOfRecordsPerPage);

				

							$scope.getMicrobiologyWorkList = function(orgId,
									orgUnitId,deptId,offset,noOfRecordsPerPage) {
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
									var URI = BASE_URL + ROOT_URL
									              + LIS_MICROBIOLOGY
									              + MICROBIOLOGY_WORKLIST;
									console.log("URI", URI);

									GenericService
											.serviceAction(METHOD_POST, URI, data)
											.then(
													function(response) {
														$rootScope.stopLoader();
														$scope.microbiologyWorklist = response.data.listObject;
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
								$scope.pager = PagerService.GetPager(
										$scope.commonListCount, page,
										$scope.noOfRecordsPerPage);
								if (flag) {
									$scope.getMicrobiologyWorkList(
											$scope.orgId, $scope.orgUnitId,$scope.deptId,
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
												$scope.microbiologyWorklist,
												function(labDetailsObj) {
													labDetailsObj.sampleStatusId = $scope.selectedAll
												});
							 }
							
							/** code for setting all checkboxes */
							$scope.checkAllMedia = function() {
								if ($scope.selectedAllMedia) {
									$scope.selectedAllMedia = true;
								} else {
									$scope.selectedAllMedia = false;
								}
								angular
										.forEach(
												$scope.listTMicroSampleMediaDto,
												function(mediaSampleObj) {
													mediaSampleObj.isSelected = $scope.selectedAllMedia;
												});
							}
							
							
							
							$scope.getMediaListBySampleId = function(labSampleDtlsId,sampleId)
							{
								try{
									$scope.listTMicroSampleMediaDto = [];
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
														angular.forEach($scope.mediaList, function(mediaObj, key) {
															 var tMicroSampleMediaDto = angular.copy($scope.tMicroSampleMediaDto);
															 tMicroSampleMediaDto.labSampleDtlsId = labSampleDtlsId;
															 tMicroSampleMediaDto.mediaId = mediaObj.id;
															 tMicroSampleMediaDto.desc = mediaObj.name;
															 $scope.listTMicroSampleMediaDto.push(tMicroSampleMediaDto);
															 $("#mediaDetailsAddModal").modal('show');
															});
														
														
													});
								} catch (e) {
									console.log(e.message)
								}
							}
							
							$scope.saveIncubationMedia = function()
							{
								try{
									
									var data = "";
									var URI = BASE_URL + ROOT_URL
								            	+ LIS_MICROBIOLOGY
								               	+ ADD_INCUBATION_MEDIA;
									
									var tempTMicroSamleMediaList = [];
									
									angular.forEach($scope.listTMicroSampleMediaDto, function(sampleMediaobj, key) {
                                                  if(sampleMediaobj.isSelected==true)
                                                	  {
                                                	   sampleMediaobj.createdDate = moment().toDate().getTime();
                                                	   tempTMicroSamleMediaList.push(sampleMediaobj); 
                                                	  }
										});
									console.log("URI", URI);
									console.log("tempTMicroSamleMediaList", JSON.stringify(tempTMicroSamleMediaList));
									GenericService
											.serviceAction(METHOD_POST, URI, tempTMicroSamleMediaList)
											.then(
													function(response) {
														$rootScope.stopLoader();
														if (response.data.status == 'success') {
															growl.success(response.data.message,
																			{
																				title : 'Success!'
																			});
															$scope.$broadcast('show-errors-reset');
														} else if (response.data.status == 'error') {
															$rootScope.stopLoader();
															growl.error(response.data.message,
																			{
																				title : 'Error!'
																			});
														}
														
														 $("#mediaDetailsAddModal").modal('hide');
													});
								} catch (e) {
									console.log(e.message)
								}
							}
							
							$scope.getIncubationMedia = function(labSampleDtlsId)
							{
								try{
									$scope.listTMicroSampleMediaDto = [];
									var data = "";
									var URI = BASE_URL + ROOT_URL
								            	+ LIS_MICROBIOLOGY
								               	+ GET_INCUBATION_MEDIA+S+labSampleDtlsId+S+$scope.orgId+S+$scope.orgUnitId;
									console.log("URI", URI);
									GenericService
											.serviceAction(METHOD_GET, URI, data)
											.then(
													function(response) {
														$rootScope.stopLoader();
														$scope.listTMicroSampleMediaDto = response.data.listObject;
														$("#mediaDetailsViewModal").modal('show');
														console.log("$scope.listTMicroSampleMediaDto",$scope.listTMicroSampleMediaDto);
													});
								} catch (e) {
									console.log(e.message)
								}
							}
							
							
							

							// code for Activating Inactivating Sample
							$scope.sendToReportEntry = function() {
								$scope.listLabSampleDtls = [];
								$rootScope.startLoader();
								angular
										.forEach(
												$scope.microbiologyWorklist,
												function(value, key) 
												{
													if (value.sampleStatusId == true) 
													{
														var labSampleDtlsforAccept = angular
																.copy($scope.labSampleDtls);
														labSampleDtlsforAccept.labSampleDtlsId = value.labSampleDtlsId;
														labSampleDtlsforAccept.sampleStatusId = SEND_TO_REPORT_ENTRY;
														labSampleDtlsforAccept.createdBy = $scope.createdBy;
														labSampleDtlsforAccept.createdDate = moment().toDate().getTime();
														$scope.listLabSampleDtls
																.push(labSampleDtlsforAccept);
													}
												});

								try {
									console.log("$scope.listLabSampleDtls",JSON.stringify($scope.listLabSampleDtls));
									var URI = BASE_URL + ROOT_URL
								            + LIS_MICROBIOLOGY
											+ MICROBIOLOGY_SEND_TO_REPORT_ENTRY;
									console.log("SEND_TO_microbiology WORKLIST", URI);
									GenericService
											.serviceAction(METHOD_POST, URI,
													$scope.listLabSampleDtls)
											.then(
													function(response) {
														if (response.data.status == 'success') {
															$scope.initMicrobiologyWorkList($scope.orgId,
																	$scope.orgUnitId,$scope.deptId,$scope.offset,
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