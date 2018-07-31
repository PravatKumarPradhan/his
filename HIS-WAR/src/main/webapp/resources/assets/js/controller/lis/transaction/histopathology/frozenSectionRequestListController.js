
/**
 * @author Suraj kumbhoje
 */

angular
		.module('myApp')
		.controller(
				"frozenSectionRequestListController",
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
							$scope.LISDynamicLabel = "frozen Section Request List Controller";
							
							$scope.firstLevel = true;
							$scope.secondLevel = true;
							$scope.thirdLevel = false;

							/** Variables for pagination */
							$scope.offset = 0;
							$scope.noOfRecordsPerPage = 10;
							$scope.totalNoOfRecords;
							$scope.searchKeyword;
							$scope.commonListCount;
							$scope.storeListCount;
							$scope.labResultTestDtls;
							$scope.orgId = 1;
							$scope.orgUnitId = 1;
							$scope.createdBy = 1;
							$scope.updatedBy = 1;
							$scope.deptId = LAB_DEPT;
							$scope.subDeptId = HISTOPATHOLOGY_DEPT;
							
							$scope.listTFrozenSectionReqMasterDto=[];
						

							
							$scope.TFrozenSectionReqMasterDto={
									 "requestId":"",
									 "patientId":"",
									 "doctorId":"",
									 "surgeryId":"",
									 "visitTypeId":"",
									 "specimenTypeId":"",
									 "surgeryStartTime":"",
									 "reqRemark":"",
									 "ackRemark":"",
									 "admissionId":"",
									 "orgId":$scope.orgId,
									 "orgUnitId":$scope.orgUnitId,
									 "createdBy":$scope.createdBy,
									 "createdDate":"",
									 "updatedBy":$scope.updatedBy,
									 "isAccepted":"",
									 "isDeleted":"N",
									 "visitType":"",
									 "specimanType":"",
									 "uhid":"",
									 "genderName":"",
									 "dob":"",
									 "age":"",
									 "doctorDetails":"",
									 "patientDetails":"",
										
							}
							
							
						
							$scope.setNoOfRecords = function() {
								$scope.initFrozenSection($scope.orgId,
										$scope.orgUnitId,$scope.deptId,$scope.subDeptId,$scope.offset,
										$scope.noOfRecordsPerPage);
							};

							$scope.initFrozenSection = function(orgId,
									orgUnitId,deptId,subDeptId, offset, noOfRecordsPerPage) {
								try {
									$rootScope.startLoader();
									var data = {
											"orgId":orgId,
											"orgUnitId":orgUnitId,
											"deptId":deptId,
											"subDeptId":subDeptId,
											"offset":offset,
											"recordPerPage":noOfRecordsPerPage									 
										};
									var data1 = {
											"orgId":orgId,
											"orgUnitId":orgUnitId,
											"deptId":deptId,
											"subDeptId":subDeptId,
										};
									offset = offset != null ? offset : 0;
									noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
											: 10;
									var URI1 = BASE_URL 
									        + ROOT_URL
											+ LIS_HISTOPATHOLOGY
											+ FROZEN_SECTION_REQUEST_LIST;

									var URI2 = BASE_URL
											+ ROOT_URL
											+ LIS_HISTOPATHOLOGY
											+ FROZEN_SECTION_REQUEST_LIST_COUNT;

									console.log("URI1", URI1);
									console.log("URI2", URI2);
									promiseFactoryWithObject
											.setPromisesWithObject(URI1, URI2, METHOD_POST,
													METHOD_POST,data,data1)
											.then(
													function(response) {
														$rootScope.stopLoader();
														$scope.frozenSectionReqMasterList = response[0].listObject;
														$scope.commonListCount = response[1].object;
														$scope.setPage(1,false);
														
														console.log("$scope.frozenSectionReqMasterList", JSON
																.stringify($scope.frozenSectionReqMasterList));	
													});
								} catch (e) {
									console.log(e.message);
								}
							}

							$scope.initFrozenSection($scope.orgId,
									$scope.orgUnitId,$scope.deptId,$scope.subDeptId,$scope.offset,
									$scope.noOfRecordsPerPage);
				

							$scope.getFrozenSectionList = function(orgId,
									orgUnitId,deptId,subDeptId,offset,noOfRecordsPerPage) {
								try {
									$rootScope.startLoader();
									var data = {
											"orgId":orgId,
											"orgUnitId":orgUnitId,
											"deptId":deptId,
											"subDeptId":subDeptId,
											"offset":offset,
											"recordPerPage":noOfRecordsPerPage
										};
									var URI = BASE_URL + ROOT_URL
														+ LIS_HISTOPATHOLOGY
														+ FROZEN_SECTION_REQUEST_LIST;
									console.log("URI", URI);

									GenericService
											.serviceAction(METHOD_POST, URI, data)
											.then(
													function(response) {
														$rootScope.stopLoader();
														$scope.frozenSectionReqMasterList  = response.data.listObject;
													});
								} catch (e) {
									console.log(e.message)
								}

							}
							
							
						

							$scope.saveDetails = function() {
								
								
								$rootScope.startLoader();
								
								$scope.frozenSectObj.ackRemark = $scope.ackRemark;
								$scope.listTFrozenSectionReqMasterDto.push($scope.frozenSectObj);
								var URI = BASE_URL + ROOT_URL
								+ LIS_HISTOPATHOLOGY + ACCEPT_FROZEN_SECTION_REQUEST;

								try {
									console.log("$scope.listTFrozenSectionReqMasterDto", URI);
									
									GenericService
											.serviceAction("POST", URI,
													$scope.listTFrozenSectionReqMasterDto)
											.then(
													function(response) {
														if (response.data.status == 'success') {
															$rootScope.stopLoader();
															$scope.initFrozenSection($scope.orgId,
																	$scope.orgUnitId,$scope.deptId,$scope.subDeptId,$scope.offset,
																	$scope.noOfRecordsPerPage);
															growl.success(response.data.message,
																			{
																				title : 'Success!'
																			});
															$scope
																	.$broadcast('show-errors-reset');

														} else if (response.data.status == 'error') {
															growl
																	.error(
																			response.data.message,
																			{
																				title : 'Error!'
																			});
														}
													});
								} catch (e) {
									console.log("Exception", e.message);
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
									$scope.getFrozenSectionList(
											$scope.orgId, $scope.orgUnitId,$scope.deptId, $scope.subDeptId,
											$scope.pager.startIndex,
											$scope.pager.pageSize);
								}
							}
						
		$scope.frozenSectObj = {};
		$scope.acknowledgeFrozenSect = function(frozenSectObj){
			$scope.frozenSectObj = frozenSectObj;
		}					
							
						} ]);