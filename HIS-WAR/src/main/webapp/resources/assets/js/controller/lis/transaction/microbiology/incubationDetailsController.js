
/**
 * @author Ganesh Chaudhari
 */

angular
		.module('myApp')
		.controller(
				"incubationDetailsController",
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
							$scope.listHelpValueMaster = [];
							$scope.incubationStartDate = moment().startOf('day').toDate();
							$scope.incubationStartTime;
							$scope.incubationPeriodId;
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
					
							
							
							$scope.getIncubationPeriodList = function(orgId)
							{
								var data = "";
								try{
									var URI = BASE_URL + ROOT_URL
								            	+ LIS_COMMON
								               	+ INCUBATION_PERIOD_LIST+S+$scope.orgId;
									console.log("URI", URI);

									GenericService
											.serviceAction(METHOD_GET, URI, data)
											.then(
													function(response) {
														$scope.incubationPeriodList = response.data.listObject;
														console.log("$scope.incubationPeriodList",$scope.incubationPeriodList);
													});
								} catch (e) {
									console.log(e.message)
								}
							}
							$scope.getIncubationPeriodList($scope.orgId);
							
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
							
							$scope.getIncubationDetails= function(tIncubationId) 
							{
									try 
									{
										$rootScope.startLoader();
										console.log("labResultMasterDtoList",JSON.stringify($scope.labResultMasterDtoList));
										var URI =BASE_URL + ROOT_URL
										+ LIS_MICROBIOLOGY
										+ GET_INCUBATION_TRANSACTION+S+tIncubationId+S+$scope.orgId+S+$scope.orgUnitId;
										console.log("URI",URI);
										GenericService.serviceAction("GET", URI, {}).then(function(response) 
										{	
											$scope.tMicroIncubationMasterDto = 	response.data.object;
											$scope.tMicroIncubationMasterDto.listTMicroIncubationDetailsMaster = response.data.object.listTMicroIncubationDetailsMaster;
										});
										
									} catch (e) {
										$rootScope.stopLoader();
										console.log("Exception",e.message);
									}
								
							}
							
							
							/**Redirect Page Code*/
							if(angular.equals({},$stateParams.incuObsrvationObj))
 							{
								$state.go('IncubationMaster');
							} else {
								$scope.sampleDtls={};
								$scope.sampleDtls  = $stateParams.incuObsrvationObj;
								$scope.getIncubationMedia($stateParams.incuObsrvationObj.labSampleDtlsId);
								if($stateParams.incuObsrvationObj.tIncubationId!=null)
									{
									$scope.getIncubationDetails($stateParams.incuObsrvationObj.tIncubationId);
									}
							}
							
							$scope.listTMicroIncubationDetailsMaster = [];
							
							$scope.tMicroIncubationMasterDto = {
								  "tIncubationId" : "",
								  "orgId" : $scope.orgId,
								  "orgUnitId" : $scope.orgUnitId,
								  "labSampleDtlsId" : $stateParams.incuObsrvationObj.labSampleDtlsId,
								  "isGrowthFound" : "N",
								  "isCompleted" : "N",
								  "createdBy" : $scope.createdBy,
								  "createdDate" : "",
								  "updatedBy" : "",
								  "updatedDate" : "",
								  "isDeleted" : "N",
								  "incubationDueTime" : "",
								  "listTMicroIncubationDetailsMaster" : []
								}
							
							
							
							$scope.tMicroIncubationDetailsMasterDto =  {
								  "tIncubationDetailsId" : "",
								  "tIncubationId" : "",
								  "incubationPeriodId":"",
								  "orgId" : $scope.orgId,
								  "orgUnitId" : $scope.orgUnitId,
								  "incubationStartTime" : "",
								  "incubationDueTime" : "",
								  "incubationStopTime" : "",
								  "remark" : "",
								  "createdBy" : $scope.createdBy,
								  "createdDate" : "",
								  "updatedBy" : "",
								  "updatedDate" : "",
								  "isDeleted" : "N",
								  "isIncubationStop" : "N"
								}
							
							
							$scope.createDateTime = function(date,time)
							{
								hourMinuitArray = time.split(":");
								
								var currentDate = $scope.incubationStartDate;
								var years =	moment(currentDate.getTime()).year();
								var month 	= moment(currentDate.getTime()).month()+1;
									var date 	= moment(currentDate.getTime()).date();
									var hour 	= parseInt(hourMinuitArray[0]);
									var minute = parseInt(hourMinuitArray[1]); 
									var second =	moment().second();
									var millisecond =  moment().millisecond();	
									console.log("date",moment({ years:years, months:month, date:date, hours:hour, minutes:minute, seconds:second, milliseconds:millisecond}));
									$scope.incubationDate = moment({ years:years, months:month, date:date, hours:hour, minutes:minute, seconds:second, milliseconds:millisecond}).toDate();
								    return moment({ years:years, months:month, date:date, hours:hour, minutes:minute, seconds:second, milliseconds:millisecond});
							}
							
							
						
							
							
							$scope.stopIncubation = function(index)
							{
								if(index!=null)
									{
									$scope.tMicroIncubationMasterDto.listTMicroIncubationDetailsMaster[index].isIncubationStop = 'Y';
									$scope.tMicroIncubationMasterDto.listTMicroIncubationDetailsMaster[index].incubationStopTime = moment().toDate().getTime();
									growl.success('Incubation Stop Successfully',
											{
												title : 'Success!'
											});
									}
								else{
									growl.error('Something Went wrong..',
											{
												title : 'Error!'
											});
								}
							}
							
							$scope.getHours = function(incubationPeriodId)
							   {
								$scope.inHours = $scope.incubationPeriodList
										.filter(function(incubationPeriodObj) 
										{
											if (incubationPeriodObj.id == incubationPeriodId) 
											{
												return incubationPeriodObj;
											}
										});
							   }
							
							$scope.startIncubation = function()
							{
								var tMicroIncubationDetailsMasterDto = angular.copy($scope.tMicroIncubationDetailsMasterDto);
								tMicroIncubationDetailsMasterDto.incubationPeriodId = $scope.incubationPeriodId;
								$scope.createDateTime($scope.incubationStartDate,$scope.incubationStartTime);
								tMicroIncubationDetailsMasterDto.incubationStartTime = $scope.incubationDate.getTime();
								$scope.getHours($scope.incubationPeriodId);
								tMicroIncubationDetailsMasterDto.incubationDueTime = moment(tMicroIncubationDetailsMasterDto.incubationStartTime).add(parseInt($scope.inHours[0].label), 'hours').toDate().getTime();
								$scope.tMicroIncubationMasterDto.listTMicroIncubationDetailsMaster.push(tMicroIncubationDetailsMasterDto);
								$scope.incubationPeriodId = "";
							}
							
							$scope.restartIncubation =function(index)
							{
								var tMicroIncubationDetailsMasterDto = angular.copy($scope.tMicroIncubationDetailsMasterDto);
								tMicroIncubationDetailsMasterDto.incubationPeriodId=$scope.tMicroIncubationMasterDto.listTMicroIncubationDetailsMaster[index].incubationPeriodId;
								tMicroIncubationDetailsMasterDto.incubationStartTime = moment().toDate().getTime();
								$scope.getHours(tMicroIncubationDetailsMasterDto.incubationPeriodId);
								tMicroIncubationDetailsMasterDto.incubationDueTime = moment(tMicroIncubationDetailsMasterDto.incubationStartTime).add(parseInt($scope.inHours[0].label), 'hours').toDate().getTime();
								$scope.tMicroIncubationMasterDto.listTMicroIncubationDetailsMaster.push(tMicroIncubationDetailsMasterDto);
							}
							
							
							
							
							$scope.saveTranaction = function()
							{
								if($stateParams.incuObsrvationObj.tIncubationId!=null){
									$scope.updateIncubationDetails();
								}
								else{
									$scope.saveIncubationDetails();
								}
								
							}
							

							$scope.saveIncubationDetails= function() 
							{
									try 
									{
										$rootScope.startLoader();
										var URI =BASE_URL + ROOT_URL
										+ LIS_MICROBIOLOGY
										+ SAVE_INCUBATION_TRANSACTION;
										
										$scope.tMicroIncubationMasterDto.createdDate = moment().toDate().getTime();
										
										console.log("URI",URI);
										console.log("tMicroIncubationMasterDto",JSON.stringify($scope.tMicroIncubationMasterDto));
										GenericService.serviceAction("POST", URI,$scope.tMicroIncubationMasterDto).then(function(response) 
										{	
											if (response.data.status == 'success')
											{
												$rootScope.stopLoader();
												growl.success(response.data.message,
														{
															title : 'Success!'
														});
												$state.go('IncubationMaster');
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
							
							$scope.updateIncubationDetails= function() 
							{
								try 
								{
									$rootScope.startLoader();
									var URI =BASE_URL + ROOT_URL
									+ LIS_MICROBIOLOGY
									+ UPDATE_INCUBATION_TRANSACTION;
									
									$scope.tMicroIncubationMasterDto.createdDate = moment().toDate().getTime();
									console.log("URI",URI);
									console.log("tMicroIncubationMasterDto",JSON.stringify($scope.tMicroIncubationMasterDto));
									GenericService.serviceAction("POST", URI,$scope.tMicroIncubationMasterDto).then(function(response) 
									{	
										if (response.data.status == 'success')
										{
											$rootScope.stopLoader();
											growl.success(response.data.message,
													{
														title : 'Success!'
													});
											$state.go('IncubationMaster');
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