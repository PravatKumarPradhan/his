/**
 * @author Ganesh Chaudhari
 */

angular
		.module('myApp')
		.controller(
				"sampleRecollectionController",
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
							$scope.LISDynamicLabel = "Sample";

							/** Variables for pagination */
							$scope.offset = 0;
							$scope.noOfRecordsPerPage = 10;
							$scope.rejectedSampleList = [];
							$scope.totalNoOfRecords;
							$scope.searchKeyword;
							$scope.commonListCount;

							$scope.orgId = 1;
							$scope.orgUnitId = 1;
							$scope.createdBy = 1;
							$scope.updatedBy = 1;
							$scope.deptId= LAB_DEPT;

							/* code for setting Label */
							$(".selectedPageName").text("Sample Recollection");

							$scope.listLabSampleDtls = [];
							$scope.labSampleDtls = 
							{
								"labSampleDtlsId" : "",
								"orgId" : $scope.orgId,
								"orgUnitId" : $scope.orgUnitId,
								"testId" : "",
								"deptId" : $scope.deptId,
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
								"isOutsourced" : "",
								"outsourcedLabId" : "",
								"sampleSendtocrDatetime" : "",
								"sampleSendtocrBy" : $scope.createdBy,
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
								"samplePendingCount":"",
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


							$scope.setNoOfRecords = function() {
								$scope.initRejectedSampleList($scope.orgId,
										$scope.orgUnitId, $scope.offset,
										$scope.noOfRecordsPerPage);
							};
							$scope.initRejectedSampleList = function(orgId,
									orgUnitId, offset, noOfRecordsPerPage) {
								$rootScope.startLoader();
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
										: 10;
								var URI1 = BASE_URL + ROOT_URL
										+ LIS_CENTRAL_RECEIVING
										+ GET_REJECTED_SAMPLE_LIST + S + orgId + S
										+ orgUnitId + S + $scope.deptId+ S +offset + S
										+ noOfRecordsPerPage;
								var URI2 = BASE_URL + ROOT_URL
										+ LIS_CENTRAL_RECEIVING
										+ GET_TOTAL_RECORD_REJECTED_SAMPLE + S
										+ orgId + S + orgUnitId+ S + $scope.deptId
								console.log("URI1", URI1);
								console.log("URI2", URI2);
								promiseFactory
										.setPromises(URI1, URI2, "GET", "GET")
										.then(
												function(response) 
												{
													$rootScope.stopLoader();
													$scope.rejectedSampleList = response[0].listObject;
													$scope.commonListCount = response[1].object;
													$scope.setPage(1, false);
												});
							}

							$scope.getRejectedSampleList = function(orgId,
									orgUnitId) {
								try {
									$rootScope.startLoader();
									var data = "";
									var URI = BASE_URL + ROOT_URL
											+ LIS_CENTRAL_RECEIVING
											+ GET_REJECTED_SAMPLE_LIST + S + orgId
											+ S + orgUnitId;
									console.log("URI", URI);
									GenericService
											.serviceAction("GET", URI, data)
											.then(
													function(response) {
														$rootScope.stopLoader();
														$scope.rejectedSampleList = response.data.listObject;
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
									$scope.getRejectedSampleList($scope.orgId,
											$scope.pager.startIndex,
											$scope.pager.pageSize);
								}
							}
							$scope.initRejectedSampleList($scope.orgId,
									$scope.orgUnitId, $scope.offset,
									$scope.noOfRecordsPerPage);

							/** code for setting all checkboxes */
							$scope.checkAll = function() {
								if ($scope.selectedAll) {
									$scope.selectedAll = true;
								} else {
									$scope.selectedAll = false;
								}
								angular
										.forEach(
												$scope.rejectedSampleList,
												function(labDetailsObj) {
													labDetailsObj.sampleRecollectFlag = $scope.selectedAll;
												});
							}


							// code for Activating Inactivating Sample
							$scope.sampleRecollect = function() {
								$rootScope.startLoader();
								angular
										.forEach(
												$scope.rejectedSampleList,
												function(value, key) {
													if (value.sampleRecollectFlag == true) {
														var labSampleDtls = angular
																.copy($scope.labSampleDtls);
														labSampleDtls.labSampleDtlsId = value.labSampleDtlsId;
														labSampleDtls.orderDetailsId = value.orderDetailsId;
														labSampleDtls.patientId = value.patientId;
														labSampleDtls.doctorId = value.doctorId;
														labSampleDtls.priorityId = value.priorityId;
														labSampleDtls.orderId = value.orderId;
														labSampleDtls.packageId = value.packageId;
														labSampleDtls.labSampleId = value.labSampleId;
														labSampleDtls.isOutsourced = value.isOutsourced;
														labSampleDtls.testId = value.testId;
														labSampleDtls.sampleReqCount = value.sampleReqCount;
														labSampleDtls.containerId = value.containerId;
														labSampleDtls.sampleTypeId = value.sampleTypeId;
														labSampleDtls.genderId = value.genderId;
														labSampleDtls.deptId = value.deptId;
														labSampleDtls.profileId = value.profileId;
														labSampleDtls.subDeptId = value.subDeptId;
														labSampleDtls.sampleStatusId = SAMPLE_RECOLLECTED;
														labSampleDtls.samplePendingCount = value.samplePendingCount;
														labSampleDtls.isAlliquoteReq = value.isAlliquoteReq;
														labSampleDtls.createdBy = $scope.createdBy;
														$scope.listLabSampleDtls
																.push(labSampleDtls);
													}
												});
								console.log("$scope.listLabSampleDtls", JSON
										.stringify($scope.listLabSampleDtls));
								
								var URI = BASE_URL + ROOT_URL
										+ LIS_CENTRAL_RECEIVING + SAMPLE_RECOLLECTION;

								try {
									console.log("SAMPLE_RECOLLECTION", URI);
									GenericService
											.serviceAction("POST", URI,
													$scope.listLabSampleDtls)
											.then(
													function(response) {
														if (response.data.status == 'success') {
															$rootScope.stopLoader();
															$scope.initRejectedSampleList(
																			$scope.orgId,
																			$scope.orgUnitId,
																			$scope.offset,
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

						} ]);