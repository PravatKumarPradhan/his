/**
 * @author  Suraj Kumbhoje
 */

angular.module('myApp').controller(
		"incubationPeriodController",
		[ '$scope','$rootScope','$state','$cookies','GenericService','PagerService','promiseFactory','$sessionStorage',
			'growl',
				function($scope,$rootScope,$state, $cookies, GenericService,PagerService,promiseFactory,$sessionStorage,growl)
				{
			       
			        $rootScope.loginpage = true;
					$scope.LISDynamicLabel = "Incubation Period";
					$scope.saveBtnFlag = true;
					$scope.updateBtnFlag = false;
					
					
					/**Variables for pagination */
					$scope.offset = 0;
					$scope.noOfRecordsPerPage = 10;
					$scope.commonList = [];
					$scope.totalNoOfRecords;
					$scope.searchKeyword;
					$scope.commonListCount;
					
					$scope.orgId = 1;
					$scope.createdBy =1 ;
					$scope.updatedBy =1 ;
					
					/* code for setting Label */
					$(".selectedPageName").text("Incubation Period Master");
					
					$scope.initCommon = function()
					{
						$scope.saveBtnFlag = true; 
						$scope.updateBtnFlag = false;
						$scope.common =
						{
							 "incubationPeriodId":"",
							 "incubationPeriodCode":"",
							 "incubationPeriodTime":"",
							 "incubationValue":"",
							 "incubationTimeId":"",
							 "incubationInHours":"",
							 "status":"A",
							 "createdBy":$scope.createdBy,
							 "createdDate":"",
							 "updatedBy":$scope.updatedBy,
							 "updatedDate":"",
						     "orgId":$scope.orgId,
						}
					}
					$scope.initCommon();
					
					$scope.initIncubationPeriodMaster = function(){
					$scope.incubationPeriodMaster =
					{
						 "incubationPeriodId":$scope.common.incubationPeriodId,
						 "incubationPeriodCode":$scope.common.incubationPeriodCode,
						 "incubationPeriodTime":$scope.common.incubationPeriodTime,
					     "incubationTimeId":$scope.common.incubationTimeId,
					     "incubationValue":$scope.common.incubationValue,
						 "createdBy":$scope.common.createdBy,
						 "createdDate":$scope.common.createdDate,
						 "updatedBy":$scope.common.updatedBy,
						 "updatedDate":$scope.common.updatedDate,
					     "orgId":$scope.common.orgId,
					     "status":$scope.common.status,
					     "incubationInHours":$scope.common.incubationInHours
					 }
					}
					// ====================================================CODE START FORFOR INCUBATION_PERIOD MASTER LIST===========================================================

					
					$scope.getIncubationTimeList = function(orgId) {
						try {
							var data = "";
							var URI = BASE_URL + ROOT_URL
							        + LIS_COMMON
									+ INCUBATION_TIME_LIST+S+orgId;
							console.log("URI", URI);
							GenericService
									.serviceAction("GET", URI, data)
									.then(
											function(response) {
												$scope.incubationTimeList  = response.data.listObject;
											});
						} catch (e) {
							console.log(e.message)
						}

					}	
					
					$scope.getIncubationTimeList($scope.orgId);
					
					// ====================================================CODE START FORFOR INCUBATION_PERIOD MASTER LIST===========================================================

					$scope.setNoOfRecords = function() {
						$scope.initIncubationPeriodMasterList($scope.orgId, $scope.offset, $scope.noOfRecordsPerPage);
					};
					$scope.initIncubationPeriodMasterList = function(orgId, offset, noOfRecordsPerPage)
					{
						var data = "";
						$rootScope.startLoader();
						offset = offset != null ? offset : 0;
						noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage : 10;
						var URI1 = BASE_URL + ROOT_URL + LIS_GLOBAL + LIST_INCUBATION_PERIOD_MASTER + S + orgId + S + offset
								+ S + noOfRecordsPerPage;
						var URI2 = BASE_URL + ROOT_URL + LIS_GLOBAL + COUNT_INCUBATION_PERIOD_MASTER + S + orgId;
						console.log("URI1", URI1);
						console.log("URI2", URI2);
						promiseFactory.setPromises(URI1, URI2, "GET", "GET").then(function(response) 
						{
							
							$rootScope.stopLoader();
							$scope.commonList = response[0].listObject;
							$scope.commonListCount = response[1].object;
							$scope.setPage(1, false);
							console.log($scope.commonList);
						});
					}

					$scope.getIncubationPeriodMasterList = function(orgId, offset, noOfRecordsPerPage) {
						console.log("offset", offset);
						var data = "";
						$rootScope.startLoader();
						offset = offset != null ? offset : 0;
						noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage : 10;
						var URI = BASE_URL + ROOT_URL + LIS_GLOBAL + LIST_INCUBATION_PERIOD_MASTER + S + orgId + S + offset
								+ S + noOfRecordsPerPage;
						console.log("URI", URI);
						GenericService.serviceAction("GET", URI, data).then(function(response) {
							$rootScope.stopLoader();
							$scope.commonList = response.data.listObject;
						});
					}

					$scope.pager = {};
					$scope.page;

					$scope.setPage = function(page, flag) {
						if (page < 1 || page > $scope.pager.totalPages)
						{
							return;
						}
						$scope.pager = PagerService.GetPager($scope.commonListCount, page, $scope.noOfRecordsPerPage);
						if (flag)
						{
							
							$scope.getIncubationPeriodMasterList($scope.orgId, $scope.pager.startIndex, $scope.pager.pageSize);
						}
					}

					$scope.initIncubationPeriodMasterList($scope.orgId, $scope.offset, $scope.noOfRecordsPerPage);

					// ====================================================CODE END FOR INCUBATION_PERIOD MASTER LIST===========================================================
					
					
					
					
					
					//code for Activating Inactivating Sample
					$scope.updateStatus = function(commonId,status)
					{
						try {
							$rootScope.startLoader();
							$scope.initIncubationPeriodMaster();
							var URI = BASE_URL + ROOT_URL + LIS_GLOBAL+ ACTIVE_INCUBATION_PERIOD_MASTER +S+$scope.orgId+S+commonId+S+status;
							console.log("ACT_INACT_URI",URI);
							GenericService.serviceAction("GET", URI, $scope.incubationPeriodMaster).then(function(response) 
							{
								if (response.data.status == 'success')
								{
									$rootScope.stopLoader();
									growl.success(response.data.message,
											{
												title : 'Success!'
											});
									$scope.$broadcast('show-errors-reset');
									$scope.initCommon();
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
							console.log("Exception",e.message);
						}
						
					}
					
					
					$scope.showUpdateBtn = function(commonId)
					{
						try {
							$rootScope.startLoader();
							$scope.saveBtnFlag = false;
							$scope.updateBtnFlag = true;
							var URI = BASE_URL + ROOT_URL + LIS_GLOBAL+EDIT_INCUBATION_PERIOD_MASTER+S+$scope.orgId+S+commonId;
							console.log("EDIT",URI);
							GenericService.serviceAction("GET", URI, $scope.common).then(function(response) 
							{
								console.log("data",response.data);
								if (response.data.status == 'success')
								{
									$rootScope.stopLoader();
									$scope.common = response.data.object;
									$scope.common.hourId = response.data.object.hourId+"";
									$scope.common.dayMasteId = response.data.object.dayMasteId+"";
									console.log("$scope.common",$scope.common);
								} else if(response.data.status == 'error')
								{
									$rootScope.stopLoader();
									alert("Error In Fetching Data");
								}
							});
						} catch (e) {
							console.log("Exception",e.message);
						}
						
					}
					
					
					$scope.save = function() 
					{
						$scope.$broadcast('show-errors-check-validity');
						if ($scope.incubationPeroidform.$valid)
						{
							try {
								$rootScope.startLoader();
								$scope.initIncubationPeriodMaster();
								console.log("IncubationPeriodMaster",JSON.stringify($scope.incubationPeriodMaster));
								var URI = BASE_URL + ROOT_URL + LIS_GLOBAL+ADD_INCUBATION_PERIOD_MASTER;
								GenericService.serviceAction("POST", URI, $scope.incubationPeriodMaster).then(function(response) 
								{	
									if (response.data.status == 'success')
									{
										$rootScope.stopLoader();
										growl.success(response.data.message,
												{
													title : 'Success!'
												});
										$scope.$broadcast('show-errors-reset');
										$scope.initCommon();
										$scope.initIncubationPeriodMasterList($scope.orgId, $scope.offset, $scope.noOfRecordsPerPage);
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
								console.log("Exception",e.message);
							}
							
						}
					}
					$scope.update = function() 
					{
						$scope.$broadcast('show-errors-check-validity');
						if ($scope.incubationPeroidform.$valid)
						{
							try {
								$rootScope.startLoader();
								$scope.initIncubationPeriodMaster();
								console.log("IncubationPeriodMaster",JSON.stringify($scope.incubationPeriodMaster));
								var URI = BASE_URL + ROOT_URL + LIS_GLOBAL+UPDATE_INCUBATION_PERIOD_MASTER;
								GenericService.serviceAction("PUT", URI, $scope.incubationPeriodMaster).then(function(response) 
								{
									if (response.data.status == 'success')
									{
										$rootScope.stopLoader();
										growl.success(response.data.message,
												{
													title : 'Success!'
												});
										$scope.$broadcast('show-errors-reset');
										$scope.initCommon();
										$scope.initIncubationPeriodMasterList($scope.orgId, $scope.offset, $scope.noOfRecordsPerPage);
									} else if(response.data.status == 'error')
									{	
										$rootScope.stopLoader();
										growl.error(response.data.message,
												{
													title : 'Error!'
												});
									}
								});
							} catch (e) 
							{
								console.log("Exception",e.message);
							}	
							
						}
					}
					/**=============================>Errors Messages  <=======================================**/
					
					$scope.codeReq = 'Code is required.';
					$scope.codeMaxLengthReq = 'Maxmimum 10 character are allowed .';
					$scope.plzSelectHours = 'Please Select Hours.';
					$scope.plzSelectDay = 'Please Select Day.';
					
				} ]);