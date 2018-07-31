/**
 * @author Ganesh Chaudhari
 */

angular
		.module('myApp')
		.controller(
				"multiParameterTestController",
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
							$scope.LISDynamicLabel = "Department";
							$scope.saveBtnFlag = true;
							$scope.updateBtnFlag = false;
							$scope.updateScope = false;

							/** Variables for pagination */
							$scope.offset = 0;
							$scope.noOfRecordsPerPage = 10;
							$scope.commonList = [];
							$scope.totalNoOfRecords;
							$scope.searchKeyword;
							$scope.multiParamTestMasterListCount;

							$scope.orgId = 1;
							$scope.orgUnitId = 1;
							$scope.createdBy = 1;
							$scope.updatedBy = 1;
							$scope.deptId = 8;
							$scope.isHistoCyto="N";

							/* code for setting Label */
							$(".selectedPageName").text("Multyparameter Test");

							$scope.multyParamTestList = [];
							$scope.departmentList;
							$scope.sampleList;
							$scope.unitList;
							$scope.containerList;
							$scope.reportList;
							$scope.techniqueList;
							$scope.prerequisitesList;
							$scope.timeList;
							$scope.noOfDayList;
							$scope.ageGroupList;
							$scope.genderList;
							$scope.headerList;
							$scope.parameterList;

							$scope.getDepartMentList = function(orgId) {
								try {
									var data = "";
									var URI = BASE_URL + ROOT_URL
											+ LIS_COMMON + GET_DEPARMENT_LIST
											+ S + orgId;
									console.log("URI", URI);
									GenericService
											.serviceAction("GET", URI, data)
											.then(
													function(response) {
														$scope.departmentList = response.data.listObject;
													});
								} catch (e) {
									console.log(e.message)
								}
							}
							$scope.getDepartMentList($scope.orgId);

							$scope.getSampleList = function(orgId) {
								try {
									var data = "";
									var URI = BASE_URL + ROOT_URL
											+ LIS_COMMON + GET_SAMPLETYPE_LIST
											+ S + orgId;
									console.log("URI", URI);
									GenericService
											.serviceAction("GET", URI, data)
											.then(
													function(response) {
														$scope.sampleList = response.data.listObject;
													});
								} catch (e) {
									console.log(e.message)
								}
							}
							$scope.getSampleList($scope.orgId);

							$scope.getUnitList = function(orgId) {
								try {
									var data = "";
									var URI = BASE_URL + ROOT_URL
											+ LIS_COMMON + GET_UNIT_LIST + S
											+ orgId;
									console.log("URI", URI);
									GenericService
											.serviceAction("GET", URI, data)
											.then(
													function(response) {
														$scope.unitList = response.data.listObject;
													});
								} catch (e) {
									console.log(e.message)
								}
							}
							$scope.getUnitList($scope.orgId);

							$scope.getContainerList = function(orgId) {
								try {
									var data = "";
									var URI = BASE_URL + ROOT_URL
											+ LIS_COMMON + GET_CONTAINER_LIST
											+ S + orgId;
									console.log("URI", URI);
									GenericService
											.serviceAction("GET", URI, data)
											.then(
													function(response) {
														$scope.containerList = response.data.listObject;
													});
								} catch (e) {
									console.log(e.message)
								}
							}
							$scope.getContainerList($scope.orgId);

							$scope.getReportList = function(orgId) {
								try {
									var data = "";
									var URI = BASE_URL + ROOT_URL
											+ LIS_COMMON + GET_REPORTTYPE_LIST
											+ S + orgId;
									console.log("URI", URI);
									GenericService
											.serviceAction("GET", URI, data)
											.then(
													function(response) {
														$scope.reportList = response.data.listObject;
													});
								} catch (e) {
									console.log(e.message)
								}
							}
							$scope.getReportList($scope.orgId);

							$scope.getTechniqueList = function(orgId) {
								try {
									var data = "";
									var URI = BASE_URL + ROOT_URL
											+ LIS_COMMON + GET_TECHNIQUE_LIST
											+ S + orgId;
									console.log("URI", URI);
									GenericService
											.serviceAction("GET", URI, data)
											.then(
													function(response) {
														$scope.techniqueList = response.data.listObject;
													});
								} catch (e) {
									console.log(e.message)
								}
							}
							$scope.getTechniqueList($scope.orgId);

							$scope.getPrerequisitesList = function(orgId) {
								try {
									var data = "";
									var URI = BASE_URL + ROOT_URL
											+ LIS_COMMON
											+ GET_PREREQUISITES_LIST + S
											+ orgId;
									console.log("URI", URI);
									GenericService
											.serviceAction("GET", URI, data)
											.then(
													function(response) {
														$scope.prerequisitesList = response.data.listObject;
													});
								} catch (e) {
									console.log(e.message)
								}
							}
							$scope.getPrerequisitesList($scope.orgId);

							$scope.getTurnArroundTimeList = function(orgId) {
								try {
									var data = "";
									var URI = BASE_URL + ROOT_URL
											+ LIS_COMMON
											+ GET_TRUNAROUND_TIME_LIST + S
											+ orgId;
									console.log("URI", URI);
									GenericService
											.serviceAction("GET", URI, data)
											.then(
													function(response) {
														$scope.timeList = response.data.listObject;
													});
								} catch (e) {
									console.log(e.message)
								}
							}
							$scope.getTurnArroundTimeList($scope.orgId);

							$scope.getnoOfDayList = function(orgId) {
								try {
									var data = "";
									var URI = BASE_URL + ROOT_URL
											+ LIS_COMMON + GET_DAY_LIST + S
											+ orgId;
									console.log("URI", URI);
									GenericService
											.serviceAction("GET", URI, data)
											.then(
													function(response) {
														$scope.getnoOfDayList = response.data.listObject;
													});
								} catch (e) {
									console.log(e.message)
								}
							}
							$scope.getnoOfDayList($scope.orgId);

							$scope.getAgeGroupList = function(orgId) {
								try {
									var data = "";
									var URI = BASE_URL + ROOT_URL
											+ LIS_COMMON + GET_AGE_GROUP_LIST
											+ S + orgId;
									console.log("URI", URI);
									GenericService
											.serviceAction("GET", URI, data)
											.then(
													function(response) {
														$scope.ageGroupList = response.data.listObject;
													});
								} catch (e) {
									console.log(e.message)
								}
							}
							$scope.getAgeGroupList($scope.orgId);

							$scope.getGenderList = function(orgId, orgUnitId) {
								try {
									var data = "";
									var URI = BASE_URL + ROOT_URL
											+ LIS_COMMON + GET_GENDER_LIST + S
											+ orgId;
									console.log("URI", URI);
									GenericService
											.serviceAction("GET", URI, data)
											.then(
													function(response) {
														$scope.genderList = response.data.listObject;
													});
								} catch (e) {
									console.log(e.message)
								}
							}
							$scope.getGenderList($scope.orgId);

							$scope.getHeaderList = function(orgId, orgUnitId) {
								try {
									var data = "";
									var URI = BASE_URL + ROOT_URL
											+ LIS_COMMON + GET_HEADER_LIST + S
											+ orgId + S + orgUnitId;
									console.log("URI", URI);
									GenericService
											.serviceAction("GET", URI, data)
											.then(
													function(response) {
														$scope.headerList = response.data.listObject;
													});
								} catch (e) {
									console.log(e.message)
								}
							}
							$scope
									.getHeaderList($scope.orgId,
											$scope.orgUnitId);

							$scope.getParameterList = function(orgId, orgUnitId) {
								try {
									var data = "";
									var URI = BASE_URL + ROOT_URL
											+ LIS_COMMON + GET_PARAMETER_LIST
											+ S + orgId + S + orgUnitId;
									console.log("URI", URI);
									GenericService
											.serviceAction("GET", URI, data)
											.then(
													function(response) {
														$scope.parameterList = response.data.listObject;
													});
								} catch (e) {
									console.log(e.message)
								}
							}
							$scope.getParameterList($scope.orgId,
									$scope.orgUnitId);

							/** Service Autocomplete List * */
							$scope.getServiceList = function(searchKeyword) {
								try {
									var data = "";
									var URI = BASE_URL + ROOT_URL
											+ LIS_COMMON
											+ AUTO_COMPLETE_SERVICES + S
											+ $scope.orgId + S
											+ $scope.orgUnitId + S
											+ $scope.deptId + S
											+ searchKeyword;
								 	console.log("URI", URI);
								 	return GenericService.serviceAction("GET", URI,
											data).then(function(response) {
										return response.data.listObject;
									});
								} catch (e) {
									console.log(e.message)
								}
							}

							$scope.selectServiceId = function($item, $model,
									$label) {
								$scope.multiParamTestMaster.serviceId = $item.id;
								try {
									var data = {
										serviceId : $item.id,
										orgId : $scope.orgId,
										orgUnitId : $scope.orgUnitId,
									};
									var URI = BASE_URL + ROOT_URL
											+ LIS_UNIT + CHECK_SERVICE_AVAIABLE;
									console.log("URI", URI);
									GenericService
											.serviceAction("POST", URI, data)
											.then(
													function(response) {
														$scope.serviceCount = response.data.object;
														if (response.data.object > 0) {
															$scope.isServiceExist = true;
															growl.error("Service is already Exists.",
																	{
																		title : 'Error!'
																	});
															$scope.multiParamTestMaster.testDesc = "";
															
														} else {
															$scope.isServiceExist = false;
														}
													});
								} catch (e) {
									console.log(e.message)
								}

							}

							/** =====================>Code for Push Pop Objects<=============== */

							$scope.headerObj;
							$scope.parameterObj;
							$scope.selectHeader = function(headerId) {
								$scope.headerObj = $scope.headerList
										.filter(function(headerMaster) {
											if (headerMaster.id == headerId) {
												return headerMaster;
											}
										});
							}

							$scope.selectParameter = function(parameterId) {
								$scope.parameterObj = $scope.parameterList
										.filter(function(parameterMaster) {
											if (parameterMaster.id == parameterId) {
												return parameterMaster;
											}
										});
							}

							$scope.testParamList = [];
							$scope.addParameterMapping = function() {

								var isExists;
								function checkExistsOrNot(arr, val) {
									return arr
											.some(function(arr) {
												return (val.parameterId === arr.parameterId)
														&& (val.headerId === arr.headerId);
											});
								}

								if ($scope.updateScope == false) {

									var testParamMpprMaster = angular
											.copy($scope.testParamMpprMaster);
									testParamMpprMaster.headerId = $scope.headerObj[0].id;
									testParamMpprMaster.headerDesc = $scope.headerObj[0].name;
									testParamMpprMaster.parameterId = $scope.parameterObj[0].id;
									testParamMpprMaster.parameterDesc = $scope.parameterObj[0].name;
									testParamMpprMaster.paraSequence = $scope.paraSequnce;
									isExists = checkExistsOrNot(
											$scope.listTestParamMpprMaster,
											testParamMpprMaster);
									if (!isExists) {
										$scope.listTestParamMpprMaster
												.push(testParamMpprMaster);
									} else {
										growl.error("Parameter is already Added To.",
												{
													title : 'Error!'
												});
									}

								} else if ($scope.updateScope == true) {
									testParamMpprMaster = angular
											.copy($scope.testParamMpprMaster);
									testParamMpprMaster.testId = $scope.multiParamTestMaster.listTestParamMppr[0].testId;
									testParamMpprMaster.headerId = $scope.headerObj[0].id;
									testParamMpprMaster.headerDesc = $scope.headerObj[0].name;
									testParamMpprMaster.parameterId = $scope.parameterObj[0].id;
									testParamMpprMaster.parameterDesc = $scope.parameterObj[0].name;
									testParamMpprMaster.paraSequence = $scope.paraSequnce;
									isExists = checkExistsOrNot(
											$scope.listTestParamMpprMaster,
											testParamMpprMaster);
									if (!isExists) {
										$scope.listTestParamMpprMaster
												.push(testParamMpprMaster);
									} else {
										growl.error("Parameter is already Added To.",
												{
													title : 'Error!'
												});
									}
								}

							};

							$scope.removeParameterMapping = function(
									deleteIndex) {
								if ($scope.updateScope == false) {
									$scope.listTestParamMpprMaster.splice(
											deleteIndex, 1);
								} else if ($scope.updateScope == true) {
									var testParamMpprMaster = angular
											.copy($scope.multiParamTestMaster.listTestParamMppr[deleteIndex]);
									testParamMpprMaster.parameterId = $scope.multiParamTestMaster.listTestParamMppr[deleteIndex].parameterId;
									testParamMpprMaster.testId = $scope.multiParamTestMaster.listTestParamMppr[0].testId;
									testParamMpprMaster.isDeleted = 'Y';
									$scope.testParamList
											.push(testParamMpprMaster);
									$scope.listTestParamMpprMaster.splice(
											deleteIndex, 1);
								}
							}

							$scope.showFootNote = function(testIndex) 
							{
								$('#footNoteSingle').modal('show');
								$scope.footNote = $scope.multiParamTestList[testIndex].footNote;

							}
							$scope.showParameters = function(testIndex) 
							{
								$('#headerParameter').modal('show');
								$scope.testIndex = testIndex;
								console.log("testMst:",JSON.stringify($scope.multiParamTestList[testIndex].listTestParamMppr));
							}

							/**
							 * =================>OBJECT DELCLARATION
							 * <========================= *
							 */
							$scope.listParameterMaster = [];
							$scope.listParamRefrengMaster = [];
							$scope.listHelpValueMaster = [];
							$scope.listTestParamMpprMaster = [];

							$scope.paramRefrengMaster = {
								"refrengId" : "",
								"parameterId" : "",
								"genderId" : "",
								"minValue" : "",
								"maxValue" : "",
								"lessThan" : "",
								"moreThan" : "",
								"ageGroupId" : "",
								"remark" : "",
								"refrengStatus" : "A",
								"createdBy" : $scope.createdBy,
								"createdDate" : "",
								"updatedBy" : "",
								"updatedDate" : "",
								"orgId" : $scope.orgId,
								"orgUnitId" : $scope.orgUnitId,
								"isDeleted" : "N"
							}

							$scope.helpValueMaster = {
								"helpValueId" : "",
								"parameterId" : "",
								"helpValue" : "",
								"status" : "A",
								"createdBy" : $scope.createdBy,
								"createdDate" : "",
								"updatedBy" : "",
								"updatedDate" : "",
								"orgId" : $scope.orgId,
								"orgUnitId" : $scope.orgUnitId,
								"isDeleted" : "N"
							}

							$scope.testParamMpprMaster = {
								"testPerMpprId" : "",
								"testId" : "",
								"parameterId" : "",
								"parameterDesc" : "",
								"paraSequence" : "",
								"headerId" : "1",
								"headerDesc" : "",
								"testParaStatus" : "A",
								"createdBy" : $scope.createdBy,
								"createdDate" : "",
								"updatedBy" : "",
								"updatedDate" : "",
								"orgId" : $scope.orgId,
								"orgUnitId" : $scope.orgUnitId,
								"isDeleted" : "N"
							}

							$scope.parameterMaster = {
								"parameterId" : "",
								"parameterName" : "",
								"aliesName" : "",
								"parameterCode" : "",
								"unitId" : "",
								"deltaDaysId" : "",
								"deltaPer" : "",
								"testType" : 1,
								"isMultyparameter" : "N",
								"createdBy" : $scope.createdBy,
								"createdDate" : "",
								"updatedBy" : "",
								"updatedDate" : "",
								"orgId" : $scope.orgId,
								"orgUnitId" : $scope.orgUnitId,
								"status" : "A",
								"isFormula":"N",
								"listParamRefrengMaster" : $scope.listParamRefrengMaster,
								"listHelpValueMaster" : $scope.listHelpValueMaster
							}

							$scope.initMultyParamTest = function() {
								$scope.testParamList = [];
								$scope.listTestParamMpprMaster = [];
								// $scope.listTestParamMpprMaster.push(angular.copy($scope.testParamMpprMaster));
								$scope.multiParamTestMaster = {
									"testId" : "",
									"testCode" : "",
									"testDesc" : "",
									"testAlies" : "",
									"deptId" : "",
									"sampleId" : "",
									"noOfSampleReq" : "",
									"sampleVolume" : "",
									"containerId" : "",
									"reportTypeId" : "",
									"techniqueId" : "",
									"identificationNo" : "",
									"prerequisitsId" : "",
									"proRelease" : "",
									"testStatus" : "",
									"isOutsourced" : "",
									"footNote" : "",
									"createdBy" : $scope.createdBy,
									"createdDate" : "",
									"updatedBy" : "",
									"updatedDate" : "",
									"orgId" : $scope.orgId,
									"orgUnitId" : $scope.orgUnitId,
									"normlTatTime" : "",
									"normlTatId" : "",
									"urgentTatTime" : "",
									"urgentTatId" : "",
									"serviceId" : "",
									"testType" : 1,
									"isHistoCyto":"N",
									"specimanId":"",
									"isCentrifugationRequried":"A",
									"listParameterMasterDto" : $scope.listParameterMaster,
									"listTestParamMppr" : $scope.listTestParamMpprMaster
								}
							}
							$scope.initMultyParamTest();
							// ====================================================CODE
							// START FOR SINGLE PARAM MASTER
							// LIST===========================================================

							$scope.setNoOfRecords = function() {
								$scope.initMultiParamTestMasterList(
										$scope.orgId, $scope.orgUnitId,
										$scope.offset,
										$scope.noOfRecordsPerPage);
							};
							$scope.initMultiParamTestMasterList = function(
									orgId, orgUnitId, offset,
									noOfRecordsPerPage) {
								$rootScope.startLoader();
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
										: 10;
								var URI1 = BASE_URL + ROOT_URL
										+ LIS_UNIT
										+ LIST_MULTIPARAM_TEST_MASTER + S
										+ orgId + S + $scope.isHistoCyto + S + orgUnitId + S + offset
										+ S + noOfRecordsPerPage;
								var URI2 = BASE_URL + ROOT_URL
										+ LIS_UNIT + GET_TOTAL_MULTITEST_RECORD
										+ S + orgId + S + $scope.isHistoCyto + S + orgUnitId;
								
								console.log("URI1",URI1);
								console.log("URI2",URI2);
								promiseFactory
										.setPromises(URI1, URI2, "GET", "GET")
										.then(
												function(response) {
													$rootScope.stopLoader();
													$scope.multiParamTestList = response[0].listObject;
													$scope.multiParamTestMasterListCount = response[1].object;
													$scope.setPage(1, false);
												});
							}

							$scope.getMultiParamTestMasterList = function(
									orgId, orgUnitId, offset,
									noOfRecordsPerPage) {
								$rootScope.startLoader();
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
										: 10;
								var URI = BASE_URL + ROOT_URL
										+ LIS_UNIT
										+ LIST_MULTIPARAM_TEST_MASTER + S
										+ orgId + S + $scope.isHistoCyto + S + orgUnitId + S + offset
										+ S + noOfRecordsPerPage;
								console.log("URI", URI);
								GenericService
										.serviceAction("GET", URI, data)
										.then(
												function(response) {
													$rootScope.stopLoader();
													$scope.multiParamTestList = response.data.listObject;
												});
							}

							$scope.pager = {};
							$scope.page;

							$scope.setPage = function(page, flag) {
								if (page < 1 || page > $scope.pager.totalPages) {
									return;
								}
								$scope.pager = PagerService.GetPager(
										$scope.multiParamTestMasterListCount,
										page, $scope.noOfRecordsPerPage);
								if (flag) {
									$scope.getMultiParamTestMasterList(
											$scope.orgId, $scope.orgUnitId,
											$scope.pager.startIndex,
											$scope.pager.pageSize);
								}
							}

							$scope.initMultiParamTestMasterList($scope.orgId,
									$scope.orgUnitId, $scope.offset,
									$scope.noOfRecordsPerPage);

							// ====================================================CODE
							// END FOR SINGLE PARAM
							// LIST===========================================================

							/** CRUD FOR SINGLE PARAM TEST MASTER * */
							// code for Activating Inactivating Sample
							$scope.activeInactiveMultiParamTest = function(
									orgId, orgUnitId, testId, testStatus) {
								try {
									$rootScope.startLoader();
									$scope.initMultyParamTest();
									var URI = BASE_URL + ROOT_URL
											+ LIS_UNIT
											+ ACTIVE_INACTIVEMULTIPARAM_TEST
											+ S + orgId + S + orgUnitId + S
											+ testId + S + testStatus;
									console.log("ACT_INACT_URI", URI);
									GenericService
											.serviceAction("GET", URI,
													$scope.multiParamTestMaster)
											.then(
													function(response) {
														if (response.data.status == 'success') {
															$rootScope
																	.stopLoader();
															growl
																	.success(
																			response.data.message,
																			{
																				title : 'Success!'
																			});
															$scope
																	.$broadcast('show-errors-reset');
															$scope
																	.initMultyParamTest();
														} else if (response.data.status == 'error') {
															$rootScope
																	.stopLoader();
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

							$scope.getMultiParamTestById = function(orgId,
									orgUnitId, testId) {
								try {
									$rootScope.startLoader();
									$scope.updateScope = true;
									$scope.saveBtnFlag = false;
									$scope.updateBtnFlag = true;
									var URI = BASE_URL + ROOT_URL
											+ LIS_UNIT
											+ GET_MULTYPARAMETER_TEST + S
											+ orgId + S + orgUnitId + S
											+ testId;
									console.log("EDIT", URI);
									GenericService
											.serviceAction("GET", URI,
													$scope.multiParamTestMaster)
											.then(
													function(response) {
														console.log("data",
																response.data);
														if (response.data.status == 'success') {
															$rootScope
																	.stopLoader();
															$scope.multiParamTestMaster = response.data.object;
															$scope.listTestParamMpprMaster = $scope.multiParamTestMaster.listTestParamMppr;
															$scope.multiParamTestMaster.deptId = response.data.object.deptId
																	+ "";
															$scope.multiParamTestMaster.sampleId = response.data.object.sampleId
																	+ "";
															$scope.multiParamTestMaster.listParameterMasterDto[0].unitId = response.data.object.listParameterMasterDto[0].unitId
																	+ "";
															$scope.multiParamTestMaster.containerId = response.data.object.containerId
																	+ "";
															$scope.multiParamTestMaster.reportTypeId = response.data.object.reportTypeId
																	+ "";
															$scope.multiParamTestMaster.techniqueId = response.data.object.techniqueId
																	+ "";
															$scope.multiParamTestMaster.prerequisitsId = response.data.object.prerequisitsId
																	+ "";
															$scope.multiParamTestMaster.normlTatId = response.data.object.normlTatId
																	+ "";
															$scope.multiParamTestMaster.urgentTatId = response.data.object.urgentTatId
																	+ "";
															$scope.multiParamTestMaster.listParameterMasterDto[0].deltaDaysId = response.data.object.listParameterMasterDto[0].deltaDaysId
																	+ "";
															console
																	.log(
																			"$scope.multiParamTestMaster",
																			$scope.multiParamTestMaster);
														} else if (response.data.status == 'error') {
															$rootScope
																	.stopLoader();
															alert("Error In Fetching Data");
														}
													});
								} catch (e) {
									console.log("Exception", e.message);
								}

							}

							$scope.saveMultiParamTest = function() {
								$scope.$broadcast('show-errors-check-validity');
								if ($scope.multiParamTestForm.$valid) {
									try {
										$rootScope.startLoader();
										$scope.multiParamTestMaster.listParameterMasterDto = [];
										$scope.multiParamTestMaster.listTestParamMppr = $scope.listTestParamMpprMaster;
										console
												.log(
														"multiParamTestMaster",
														JSON
																.stringify($scope.multiParamTestMaster));
										var URI = BASE_URL + ROOT_URL
												+ LIS_UNIT
												+ ADD_MULTIPARAMETER_TEST;

										GenericService
												.serviceAction(
														"POST",
														URI,
														$scope.multiParamTestMaster)
												.then(
														function(response) {
															if (response.data.status == 'success') {
																$rootScope
																		.stopLoader();
																growl
																		.success(
																				response.data.message,
																				{
																					title : 'Success!'
																				});
																$scope
																		.$broadcast('show-errors-reset');
																$scope
																		.initMultyParamTest();
																$scope
																		.initMultiParamTestMasterList(
																				$scope.orgId,
																				$scope.orgUnitId,
																				$scope.offset,
																				$scope.noOfRecordsPerPage);
															} else if (response.data.status == 'error') {
																$rootScope
																		.stopLoader();
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
							}
							$scope.updateMultiParamTest = function() {
								$scope.$broadcast('show-errors-check-validity');
								if ($scope.multiParamTestForm.$valid) {
									try {
										$rootScope.startLoader();
										$scope.multiParamTestMaster.listParameterMasterDto = [];
										$scope.multiParamTestMaster.listTestParamMppr = $scope.listTestParamMpprMaster
												.concat($scope.testParamList);
										console
												.log(
														"multiParamTestMaster",
														JSON
																.stringify($scope.multiParamTestMaster));
										var URI = BASE_URL + ROOT_URL
												+ LIS_UNIT
												+ UPDATE_MULTIPARAMETER_TEST;

										GenericService
												.serviceAction(
														"PUT",
														URI,
														$scope.multiParamTestMaster)
												.then(
														function(response) {
															if (response.data.status == 'success') {
																$rootScope
																		.stopLoader();
																growl
																		.success(
																				response.data.message,
																				{
																					title : 'Success!'
																				});
																$scope
																		.$broadcast('show-errors-reset');
																$scope
																		.initMultiParamTestMasterList(
																				$scope.orgId,
																				$scope.orgUnitId,
																				$scope.offset,
																				$scope.noOfRecordsPerPage);
															} else if (response.data.status == 'error') {
																$rootScope
																		.stopLoader();
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
							}
							/**
							 * =============================>Errors Messages
							 * <=======================================*
							 */
							$scope.testNameReq = 'Test name is required.';
							$scope.testCodeReq = 'Test code is required.';
							$scope.testAliesReq = 'Alies name is required.';
							$scope.deptReq = 'Please select department.';
							$scope.sampleReq = 'Please select sample.';
							$scope.noOfSampleReq = 'Please enter no of sample.';
							$scope.sampleVolumeReq = 'Please enter sample volume.';
							$scope.sampleUnitReq = 'Please select sample unit.';
							$scope.containerReq = 'Please select container type.';
							$scope.reportTypeReq = 'Please select report type.';
							$scope.techniqueReq = 'Please select technique type.';
							$scope.identificationNoReq = 'Please enter identification number.';
							$scope.footNoteReq = 'Please enter fooot note.';
							$scope.urgentTatReq = 'Please select urgent time.';
							$scope.urgentTatTimeReq = 'Please enter argent time.';
							$scope.normlTimeReq = 'Please select normal time.';
							$scope.normlTatTimeReq = 'Please enter normal time.';
							$scope.headerNoReq = "Please select header.";
							$scope.headerNoReq = "Please select header name.";
							$scope.parameterReq = "Please select parameter name."
							$scope.paraSequenceReq = "Please select parameter sequence."

						} ]);