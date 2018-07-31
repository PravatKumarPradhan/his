
/**
 * @author Suraj kumbhoje
 */

angular
		.module('myApp')
		.controller(
				"worklistForPrintingController",
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
							$scope.LISDynamicLabel = "worklistForReportRelease";
							
							$scope.firstLevel = true;
							$scope.secondLevel = true;
							$scope.thirdLevel = false;

							/** Variables for pagination */
							$scope.offset = 0;
							$scope.noOfRecordsPerPage = 10;
							$scope.resultEntryworklistForValidation  = [];
							$scope.totalNoOfRecords;
							$scope.searchKeyword;
							$scope.commonListCount;
							$scope.labResultTestDtls;
							$scope.orgId = 1;
							$scope.orgUnitId = 1;
							$scope.createdBy = 1;
							$scope.updatedBy = 1;
							$scope.deptId = LAB_DEPT;
							$scope.subDeptId = BIOCHEM_DEPT;
							$scope.listHelpValueMaster = [];
							
							$scope.listLabResultMasterDto  = [];
							$scope.labResultMasterDto = {
									"labTestResId": "",
						            "orgId": $scope.orgId,
						            "orgUnitId":$scope.orgUnitId,
						            "testId": "",
						            "deptId": $scope.deptId,
						            "patientId": "",
						            "visitTypeId": "",
						            "visitAdmId": "",
						            "orderDetailsId": "",
						            "sampleNo": "",
						            "labSampleDtlsId": "",
						            "resultEnterDatetime": "",
						            "resultEnterBy": "",
						            "resultValidatedDatetime": "",
						            "resultValidatedBy": "",
						            "resultAuthorisedDatetime": "",
						            "resultAuthorisedBy": "",
						            "resultAuthorisedFlag": "",
						            "resultHandoverDatetime": "",
						            "resultHandoverBy": "",
						            "suggetionNotes": "",
						            "footsNotes": "",
						            "wardCode":"",
						            "bedNumber":"",
						            "sampleType":"",
						            "testCode":"",
						            "createdBy": $scope.createdBy,
						            "createdDate": "",
						            "updatedBy": $scope.updatedBy,
						            "updatedDate": "",
						            "sampleBarcode": "",
						            "labSampleNo": "",
						            "subDeptId":$scope.subDeptId,
						            "visitType": "",
						            "uhid": "",
						            "patientDetails": "",
						            "doctorDetails": "",
						            "testDesc": "",
						            "priorityName": "",
						            "colorCode": "",
						            "panelCode":"",
						            "orderDateTime":"",
						            "sampleCollectionDatetime":"",
						            "panelId":"",
						            "genderId":"",
						            "resultLevel":"",
						            "sampleStatusId" :"",
						            "listLabSampleResultDetailsMaster":[]
							}

						
							$scope.setNoOfRecords = function() {
								$scope.initReportHandover($scope.orgId,
										$scope.orgUnitId,$scope.deptId,$scope.offset,
										$scope.noOfRecordsPerPage);
							};

							$scope.initReportHandover = function(orgId,
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
											+ LIS_REPORT_HAND_OVER
											+ LIST_FOR_REPORT_HANDOVER_RELEASE;

									var URI2 = BASE_URL
											+ ROOT_URL
											+ LIS_REPORT_HAND_OVER
											+ LIST_FOR_REPORT_HANDOVER_RELEASE_COUNT;

									console.log("URI1", URI1);
									console.log("URI2", URI2);
									promiseFactoryWithObject
											.setPromisesWithObject(URI1, URI2, METHOD_POST,
													METHOD_POST,data,data1)
											.then(
													function(response) {
														$rootScope.stopLoader();
														$scope.resultReportHandOver = response[0].listObject;
														$scope.commonListCount = response[1].object;
														$scope.setPage(1,false);
														
														console.log("$scope.resultReportHandOver", JSON
																.stringify($scope.resultReportHandOver));	

													});
								} catch (e) {
									console.log(e.message);
								}
							}

							$scope.initReportHandover($scope.orgId,
									$scope.orgUnitId,$scope.deptId,$scope.offset,
									$scope.noOfRecordsPerPage);
				

							$scope.getReportHandOverList = function(orgId,
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
											+ LIS_REPORT_HAND_OVER
											+ LIST_FOR_REPORT_HANDOVER_RELEASE;
									console.log("URI", URI);

									GenericService
											.serviceAction(METHOD_POST, URI, data)
											.then(
													function(response) {
														$rootScope.stopLoader();
														$scope.resultReportHandOver  = response.data.listObject;
													});
								} catch (e) {
									console.log(e.message)
								}

							}
							
							
							
							/** code for setting all checkboxes */
							$scope.checkAll = function() {
								if ($scope.selectedAll) {
									$scope.selectedAll = true;
								} else {
									$scope.selectedAll = false;
								}
								angular.forEach($scope.resultReportHandOver,
												function(resultEntry)
												{
									              resultEntry.printReport = $scope.selectedAll;
												});
							}

							
							$scope.printReports= function() 
							{
								$scope.$broadcast('show-errors-check-validity');
								/*if ($scope.worklistForReportEntryForm.$valid)*/
								/*{*/
								
									try 
									
								{
										var flag = false;
										var URI = BASE_URL + ROOT_URL
										+ LIS_REPORTS + PRINT_REPORTS;
										$rootScope.startLoader();
										angular
										.forEach(
												$scope.resultReportHandOver,
												function(resultEntryMaster,key) 
												{
										
													if(resultEntryMaster.printReport==true)
														{
															/*  resultEntryMaster.createdBy = $scope.createdBy;
															   resultEntryMaster.orgId = $scope.orgId;
															   resultEntryMaster.orgUnitId = $scope.orgUnitId;
															//   $scope.listLabResultMasterDto.push(resultEntryMaster);
															document.getElementById('printBulkReportsForm').action =URI;
														    document.getElementById("printBulkReportsForm").target = "_blank";
														    document.getElementById("reportPrint").value = '['+JSON.stringify(resultEntryMaster)+']';
														    window.open();
														    document.getElementById("printBulkReportsForm").submit();*/
														
														var form = null;
														resultEntryMaster.createdBy = $scope.createdBy;
														resultEntryMaster.orgId = $scope.orgId;
														resultEntryMaster.orgUnitId = $scope.orgUnitId;
													    form = document.createElement("form");
														form.setAttribute("method", "post");
														form.setAttribute("action", URI);
														form.setAttribute("target", "_blank");

														var hiddenField = document.createElement("input"); 
														hiddenField.setAttribute("type", "hidden");
														hiddenField.setAttribute("name", "nameOfReport");
														hiddenField.setAttribute("value", '['+JSON.stringify(resultEntryMaster)+']');
														form.appendChild(hiddenField);
														document.body.appendChild(form);
														flag = true;
												     }
												});
										
										
										if(flag)
										{
										  $('form').submit();
										}
										$scope.initReportHandover($scope.orgId,
												$scope.orgUnitId,$scope.deptId,$scope.offset,
												$scope.noOfRecordsPerPage);
										console.log("$scope.listLabResultMasterDto", JSON
												.stringify($scope.listLabResultMasterDto));	

									console.log("PRINT_REPORTS", URI);
									/*GenericService
											.serviceAction("POST", URI,
													$scope.listLabResultMasterDto)
											.then(
													function(response) {
														if (response.data.status == 'success') {
															$rootScope.stopLoader();
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
													});	*/
										
									} catch (e) {
										$rootScope.stopLoader();
										console.log("Exception",e.message);
									}
								}
							//}
							
							
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
									$scope.getReportHandOverList(
											$scope.orgId, $scope.orgUnitId,$scope.deptId,
											$scope.pager.startIndex,
											$scope.pager.pageSize);
								}
							}
						} ]);