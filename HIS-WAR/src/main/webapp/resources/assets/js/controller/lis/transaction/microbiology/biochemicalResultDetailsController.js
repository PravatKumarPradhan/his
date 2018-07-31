
/**
 * @author Ganesh Chaudhari
 */

angular
		.module('myApp')
		.controller(
				"biochemicalResultDetailsController",
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

							
							$scope.isRecollected = false;
							$rootScope.loginpage = true;
							$scope.thirdLevel = false;
							
							$scope.LISDynamicLabel = "Sample";

							/** Variables for pagination */
							$scope.offset = 0;
							$scope.noOfRecordsPerPage = 10;
							$scope.resultEntryWorklist  = [];
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
							$scope.microTestId = "";
							$('[data-toggle="deltatooltip"]').tooltip();
							
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
					
							
							
							$scope.getMicroBiologyTestList = function()
							{
								var data = "";
								try{
									var URI = BASE_URL + ROOT_URL
								            	+ LIS_COMMON
								               	+ MICROBIOLOGY_TEST_LIST+S+$scope.orgId+S+$scope.orgUnitId;
									console.log("URI", URI);

									GenericService
											.serviceAction(METHOD_GET, URI, data)
											.then(
													function(response) {
														$scope.microBiologyTestList = response.data.listObject;
														console.log("$scope.microBiologyTestList",$scope.microBiologyTestList);
													});
								} catch (e) {
									console.log(e.message)
								}
							}
							$scope.getMicroBiologyTestList();
							
							
							$scope.getBiochemicalResultDetails= function(tbiochemicalTestId) 
							{
									try 
									{
										$rootScope.startLoader();
										var URI =BASE_URL + ROOT_URL
										+ LIS_MICROBIOLOGY
										+ GET_BIOCHEMICAL_RESULT+S+tbiochemicalTestId+S+$scope.orgId+S+$scope.orgUnitId;
										console.log("URI",URI);
										GenericService.serviceAction("GET", URI, {}).then(function(response) 
										{	
											$scope.tBiochemicalTestMasterDto = 	response.data.object;
											$rootScope.stopLoader();
										});
										
									} catch (e) {
										$rootScope.stopLoader();
										console.log("Exception",e.message);
									}
								
							}
							
							
							/**Redirect Page Code*/
							if(angular.equals({},$stateParams.biochemicalResultObj))
 							{
								$state.go('biochemicalTesting');
							} else {
								$scope.sampleDtls={};
								$scope.sampleDtls  = $stateParams.biochemicalResultObj;
								console.log($stateParams.biochemicalResultObj.tbiochemicalTestId);
								if($stateParams.biochemicalResultObj.tbiochemicalTestId!=null)
									{
									 $scope.getBiochemicalResultDetails($stateParams.biochemicalResultObj.tbiochemicalTestId);
									}
							}
							
							
							$scope.listTBiochemicalTestDetailsMasterDto = [];
							$scope.tBiochemicalTestMasterDto ={
								  "tBiochemicalTestId" : "",
								  "labSampleDtlsId" : $stateParams.biochemicalResultObj.labSampleDtlsId,
								  "orgId" : $scope.orgId ,
								  "orgUnitId" : $scope.orgUnitId ,
								  "createdBy" : $scope.createdBy ,
								  "createdDate" : "",
								  "updatedBy" : "",
								  "updatedDate" : "",
								  "isDeleted" : "N",
								  "isCompleted" : "N",
								  "listTBiochemicalTestDetailsMaster" : $scope.listTBiochemicalTestDetailsMasterDto
								}
							
							$scope.tBiochemicalTestDetailsMasterDto ={
								  "tBiochemicalTestDetailsId" : "",
								  "microTestId" : "",
								  "orgId" : $scope.orgId ,
								  "orgUnitId" : $scope.orgUnitId ,
								  "biochemTestId" : "",
								  "result" : "",
								  "remark" : "",
								  "createdBy" : $scope.createdBy ,
								  "createdDate" : "",
								  "updatedBy" : "",
								  "updatedDate" : "",
								  "isDeleted" : "N"
								}
							
						
							
							$scope.listTBiochemicalTestDtlsMst = [];
							$scope.removeTBiochemicalResult = function(deleteIndex)
							{
								var tBiochemicalTestDetailsMasterDto = angular
								.copy($scope.tBiochemicalTestMasterDto.listTBiochemicalTestDetailsMaster[deleteIndex]);
								tBiochemicalTestDetailsMasterDto.isDelted="Y";
								$scope.listTBiochemicalTestDtlsMst.push(tBiochemicalTestDetailsMasterDto);
								$scope.tBiochemicalTestMasterDto.listTBiochemicalTestDetailsMaster.splice(deleteIndex, 1);
							}
							$scope.addTest = function()
							{
								var isExists;
								function checkExistsOrNot(arr, val) {
									return arr
											.some(function(arr) {
												return (val.microTestId === arr.microTestId);
											});
								}
								
								var tBiochemicalTestDetailsMasterDto = angular
								.copy($scope.tBiochemicalTestDetailsMasterDto);
								tBiochemicalTestDetailsMasterDto.microTestId = $scope.microTestId;
								isExists = checkExistsOrNot(
								$scope.tBiochemicalTestMasterDto.listTBiochemicalTestDetailsMaster,
												tBiochemicalTestDetailsMasterDto);
								if (!isExists) {
									$scope.tBiochemicalTestMasterDto.listTBiochemicalTestDetailsMaster
											.push(tBiochemicalTestDetailsMasterDto);
								} else {
									growl.error("Test is Alredy Added.",
											{
												title : 'Error!'
											});
								}
							}
							
							
							$scope.saveTranaction = function()
							{
								if($stateParams.biochemicalResultObj.tBiochemicalTestId==null){
									$scope.saveBiochemicalResult();
								}
								else{
									$scope.updateBiochemicalResult();
								}
								
							}
							

							$scope.saveBiochemicalResult= function() 
							{
									try 
									{
										$rootScope.startLoader();
										var URI =BASE_URL + ROOT_URL
										+ LIS_MICROBIOLOGY
										+ SAVE_BIOCHEMICAL_RESULT;
										console.log("URI",URI);
										$scope.tBiochemicalTestMasterDto.createdDate = moment().toDate().getTime();
										console.log("S tMicroIncubationMasterDto",JSON.stringify($scope.tBiochemicalTestMasterDto));
										GenericService.serviceAction("POST", URI,$scope.tBiochemicalTestMasterDto).then(function(response) 
										{	
											if (response.data.status == 'success')
											{
												$rootScope.stopLoader();
												growl.success(response.data.message,
														{
															title : 'Success!'
														});
												$state.go('biochemicalTesting');
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
							
							$scope.updateBiochemicalResult= function() 
							{
								try 
								{
									$rootScope.startLoader();
									var URI =BASE_URL + ROOT_URL
									+ LIS_MICROBIOLOGY
									+ UPDATE_BIOCHEMICAL_RESULT;
									console.log("URI",URI);
									$scope.tBiochemicalTestMasterDto.updatedDate = moment().toDate().getTime();
									console.log("u tMicroIncubationMasterDto",JSON.stringify($scope.tBiochemicalTestMasterDto));
									GenericService.serviceAction("PUT", URI,$scope.tBiochemicalTestMasterDto).then(function(response) 
									{	
										if (response.data.status == 'success')
										{
											$rootScope.stopLoader();
											growl.success(response.data.message,
													{
														title : 'Success!'
													});
											$state.go('biochemicalTesting');
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
							
							
								
							
						} ]);