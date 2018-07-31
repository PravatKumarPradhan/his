
/**
 * @author Ganesh Chaudhari
 */

angular
		.module('myApp')
		.controller(
				"microscopicExaminationController",
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
						$scope.microScopicExaminationList = [];
						$scope.totalNoOfRecords;
						$scope.searchKeyword;
						$scope.commonListCount;
						$scope.microscopicExamination;

						$scope.orgId = 1;
						$scope.orgUnitId = 1;
						$scope.createdBy = 1;
						$scope.updatedBy = 1;
						$scope.deptId = LAB_DEPT ;
					    $scope.subDeptId = MICROBIOLOGY_DEPT;
					    $scope.isComplete = "";
					    
					    
					    
					     
						
						
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
						
				

						

						$scope.setNoOfRecords = function() {
							$scope.initMicroScopicExaminationList($scope.orgId,
									$scope.orgUnitId, $scope.offset,
									$scope.noOfRecordsPerPage);
						};

						$scope.initMicroScopicExaminationList = function(orgId,
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
										+ MICROSCOPIC_EXAMINATION_WORKLIST;

								var URI2 = BASE_URL
										+ ROOT_URL
										+ LIS_MICROBIOLOGY
										+ MICROSCOPIC_EXAMINATION_WORKLIST_COUNT;

								console.log("URI1", URI1);
								console.log("URI2", URI2);
								promiseFactoryWithObject
										.setPromisesWithObject(URI1, URI2, METHOD_POST,
												METHOD_POST,data,data1)
										.then(
												function(response) {
													$rootScope.stopLoader();
													$scope.microScopicExaminationList = response[0].listObject;
													$scope.commonListCount = response[1].object;
													$scope.setPage(1,false);
												});
							} catch (e) {
								console.log(e.message);
							}
						}

						$scope.initMicroScopicExaminationList($scope.orgId,
								$scope.orgUnitId, $scope.offset,
								$scope.noOfRecordsPerPage);
						
						
						$scope.getMicroScopicExaminationList = function(orgId,
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
							               	+ MICROSCOPIC_EXAMINATION_WORKLIST;
								console.log("URI", URI);

								GenericService
										.serviceAction(METHOD_POST, URI, data)
										.then(
												function(response) {
													$rootScope.stopLoader();
													$scope.microScopicExaminationList = response.data.listObject;
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
								$scope.getMicroScopicExaminationList(
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
						
						
			
						
						
						
						
						$scope.generateMicroDetailsUrl = function(microScopicExaminationObj)
						{
							if(microScopicExaminationObj!=null)
							  {
								microScopicExaObj = microScopicExaminationObj;
								microScopicExaObj.orgId = $scope.orgId;
								microScopicExaObj.orgUnitId = $scope.orgUnitId;
								microScopicExaObj.createdBy = $scope.createdBy;
								microScopicExaObj.listTMicroSampleMediaDto = [];
											$state.go('detailsMicroscopicExam',
												{
												   microExaObj : microScopicExaObj,
												});
							  }
						  else
						     {
							    growl.error("Something went Wrong.",{title : 'Error!'});
						     }
						}
						
					
					} 
					]);