
/**
 * @author Ganesh
 */

angular
		.module('myApp')
		.controller(
				"macroScopicExaminationController",
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
							$scope.macroScopicExaminationList = [];
							$scope.totalNoOfRecords;
							$scope.searchKeyword;
							$scope.commonListCount;

							$scope.orgId = 1;
							$scope.orgUnitId = 1;
							$scope.createdBy = 1;
							$scope.updatedBy = 1;
							$scope.deptId = LAB_DEPT ;
						    $scope.subDeptId = MICROBIOLOGY_DEPT;
						    
						   
						    
						    
						     /******* START TAB FOR ALL PAGES IN TREE LEVEL *******/
				        	
							
							
							
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

							$scope.setNoOfRecords = function() {
								$scope.initMacroScopicExaminationList($scope.orgId,
										$scope.orgUnitId, $scope.offset,
										$scope.noOfRecordsPerPage);
							};

							$scope.initMacroScopicExaminationList = function(orgId,
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
											+ MACROSCOPIC_EXAMINATION_WORKLIST;

									var URI2 = BASE_URL
											+ ROOT_URL
											+ LIS_MICROBIOLOGY
											+ MACROSCOPIC_EXAMINATION_WORKLIST_COUNT;

									console.log("URI1", URI1);
									console.log("URI2", URI2);
									promiseFactoryWithObject
											.setPromisesWithObject(URI1, URI2, METHOD_POST,
													METHOD_POST,data,data1)
											.then(
													function(response) {
														$rootScope.stopLoader();
														$scope.macroScopicExaminationList = response[0].listObject;
														$scope.commonListCount = response[1].object;
														$scope.setPage(1,false);
													});
								} catch (e) {
									console.log(e.message);
								}
							}

							$scope.initMacroScopicExaminationList($scope.orgId,
									$scope.orgUnitId, $scope.offset,
									$scope.noOfRecordsPerPage);
							
							
							$scope.getMacroScopicExaminationList = function(orgId,
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
								               	+ MACROSCOPIC_EXAMINATION_WORKLIST;
									console.log("URI", URI);

									GenericService
											.serviceAction(METHOD_POST, URI, data)
											.then(
													function(response) {
														$rootScope.stopLoader();
														$scope.macroScopicExaminationList = response.data.listObject;
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
									$scope.initMacroScopicExaminationList(
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
												$scope.macroScopicExaminationList,
												function(labDetailsObj) {
													labDetailsObj.isAcceptOrReject = $scope.selectedAll
												});
							 }
							
							
							
							$scope.macroscopicExaminationMasterDtoList = [];
							$scope.macroscopicExaminationMasterDto={
								  "tMacroExaId" : "",
								  "labSampleDtlsId" : "",
								  "macroExaResult" : "",
								  "isCompleted" : "N",
								  "orgId" : $scope.orgId,
								  "orgUnitId" : $scope.orgUnitId,
								  "createdBy" : $scope.createdBy,
								  "createdDate" : "",
								  "updatedBy" : "",
								  "updatedDate" : "",
								  "isDeleted" : "N"
								}
							
							$scope.saveExamination = function()
							{
								if($scope.macroscopicExaminationMasterDto.tMacroExaId==""||$scope.macroscopicExaminationMasterDto.tMacroExaId==null)
									{
									  $scope.saveMacroScopicExamination();
									}
								else if($scope.macroscopicExaminationMasterDto.tMacroExaId>0)
									{
									$scope.updateMacroscopicExamination();
									}
							}
							
							$scope.saveMacroScopicExamination= function() 
							{
									try 
									{
										$scope.macroscopicExaminationMasterDtoList = [];
										$rootScope.startLoader();
										var URI =BASE_URL + ROOT_URL
										+ LIS_MICROBIOLOGY
										+ SAVE_MACROSCOPIC_EXAMINATION;
										
										$scope.macroscopicExaminationMasterDto.createdDate = moment().toDate().getTime();
										$scope.macroscopicExaminationMasterDtoList.push($scope.macroscopicExaminationMasterDto);
										console.log("URI",URI);
										console.log("macroscopicExaminationMasterDtoList",JSON.stringify($scope.macroscopicExaminationMasterDtoList));
										GenericService.serviceAction(METHOD_POST, URI,$scope.macroscopicExaminationMasterDtoList).then(function(response) 
										{	
											if (response.data.status == 'success')
											{
												$rootScope.stopLoader();
												growl.success(response.data.message,
														{
															title : 'Success!'
														});
												$scope.initMacroScopicExaminationList($scope.orgId,
														$scope.orgUnitId, $scope.offset,
														$scope.noOfRecordsPerPage);
											} else if(response.data.status == 'error')
											{
												$rootScope.stopLoader();
												growl.error(response.data.message,
														{
															title : 'Error!'
														});
											}
										});
									} catch (e) {
										$rootScope.stopLoader();
										console.log("Exception",e.message);
									}
									
							}
							
							$scope.updateMacroscopicExamination= function() 
							{
								try 
								{
									$scope.macroscopicExaminationMasterDtoList = [];
									$rootScope.startLoader();
									var URI =BASE_URL + ROOT_URL
									+ LIS_MICROBIOLOGY
									+ UPDATE_MACROSCOPIC_EXAMINATION;
									$scope.macroscopicExaminationMasterDto.updatedDate = moment().toDate().getTime();
									$scope.macroscopicExaminationMasterDto.updatedBy = $scope.createdBy;
									$scope.macroscopicExaminationMasterDtoList.push($scope.macroscopicExaminationMasterDto);
									
									console.log("URI",URI);
									console.log("macroscopicExaminationMasterDtoList",JSON.stringify($scope.macroscopicExaminationMasterDtoList));
									GenericService.serviceAction("PUT", URI,$scope.macroscopicExaminationMasterDtoList).then(function(response) 
									{	
										if (response.data.status == 'success')
										{
											$rootScope.stopLoader();
											growl.success(response.data.message,
													{
														title : 'Success!'
													});
											$scope.initMacroScopicExaminationList($scope.orgId,
													$scope.orgUnitId, $scope.offset,
													$scope.noOfRecordsPerPage);
										} else if(response.data.status == 'error')
										{
											$rootScope.stopLoader();
											growl.error(response.data.message,
													{
														title : 'Error!'
													});
										}
									});
								} catch (e) {
									$rootScope.stopLoader();
									console.log("Exception",e.message);
								}
							}
							
							$scope.getMacroscopicExamination = function(labSampleDtlsId,tMacroExaId)
							{
								try{
									
									if(tMacroExaId!=null&&tMacroExaId!=undefined)
										{
										var data = "";
										var URI = BASE_URL + ROOT_URL
									            	+ LIS_MICROBIOLOGY
									               	+ GET_MACROSCOPIC_EXAMINATION+S+tMacroExaId+S+$scope.orgId+S+$scope.orgUnitId;
										console.log("URI", URI);
										GenericService
												.serviceAction(METHOD_GET, URI, data)
												.then(
														function(response) {
															$rootScope.stopLoader();
															$scope.macroscopicExaminationMasterDto = response.data.object;
															$('#macroExampleDetailsModal').modal('show');
															console.log("$scope.macroscopicExaminationMasterDtoList",$scope.macroscopicExaminationMasterDtoList);
														});
										}
									else{
										$scope.macroscopicExaminationMasterDto.labSampleDtlsId = labSampleDtlsId;
										$("#macroExampleDetailsModal").modal('show');
									}
									
								} catch (e) {
									console.log(e.message)
								}
							}
							
				
						} ]);