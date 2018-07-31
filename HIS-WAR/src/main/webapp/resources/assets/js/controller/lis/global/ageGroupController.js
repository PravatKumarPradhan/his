/**
 * @author  Suraj  Kumbhoje
 */

angular.module('myApp').controller(
		"ageGroupController",
		[ '$scope','$rootScope','$state','$cookies','GenericService','PagerService','promiseFactory','$sessionStorage',
			'growl',
				function($scope,$rootScope,$state, $cookies, GenericService,PagerService,promiseFactory,$sessionStorage,growl)
				{
			       
			        $rootScope.loginpage = true;
					$scope.LISDynamicLabel = "Age Type Group";
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
					$(".selectedPageName").text("Age Type Group Master");
					
					$scope.initCommon = function()
					{
						$scope.saveBtnFlag = true; 
						$scope.updateBtnFlag = false;
						$scope.common =
						{
							 "ageGroupId":"",
							 "ageGroupCode":"",
							 "ageGroupTo":"",
							 "ageTypeGrpfromId":"",
							 "ageTypeGrptoId":"",
						     "ageGroupStatus":"A",
						     "ageGroupFrom":"",
						     "ageToday":"",
						     "ageFromDay":"", 
						     "ageTypeGrpName":"",
							 "createdBy":$scope.createdBy,
							 "createdDate":"",
							 "updatedBy":$scope.updatedBy,
							 "updatedDate":"",
						     "orgId":$scope.orgId,
						}
					}
					$scope.initCommon();
					
					$scope.initAgeGroupMaster = function(){
					$scope.ageGroupMaster =
					{
						 "ageGroupId":$scope.common.ageGroupId,
						 "ageGroupCode":$scope.common.ageGroupCode,
						 "ageGroupTo":$scope.common.ageGroupTo,
						 "ageTypeGrpfromId":$scope.common.ageTypeGrpfromId,
						 "ageTypeGrptoId":$scope.common.ageTypeGrptoId,
					     "ageGroupStatus":$scope.common.ageGroupStatus,
					     "ageGroupFrom":$scope.common.ageGroupFrom,
					     "ageToday":$scope.common.ageToday,
					     "ageTypeGrpName":$scope.common.ageTypeGrpName,
					     "ageFromDay":$scope.common.ageFromDay,
						 "createdBy":$scope.common.createdBy,
						 "createdDate":$scope.common.createdDate,
						 "updatedBy":$scope.common.updatedBy,
						 "updatedDate":$scope.common.updatedDate,
					     "orgId":$scope.common.orgId
					 }
					}
					
					// ====================================================CODE START FORFOR AGE_GROUP MASTER LIST===========================================================

					
					
					/*
					 *  Get Hour Master List
					 */
					$scope.getAgeTypeGroupList = function(orgId) {
						try {
							var data = "";
							var URI = BASE_URL + ROOT_URL
							        + LIS_GLOBAL
									+ GET_AGE_TYPE_GROUP_LIST;
							console.log("URI", URI);
							GenericService
									.serviceAction("GET", URI, data)
									.then(
											function(response) {
												$scope.ageTypeGroupList  = response.data.listObject;
											});
						} catch (e) {
							console.log(e.message)
						}

					}
					$scope.getAgeTypeGroupList($scope.orgId);
					
					$scope.setNoOfRecords = function() {
						$scope.initAgeGroupMasterList($scope.orgId, $scope.offset, $scope.noOfRecordsPerPage);
					};
					$scope.initAgeGroupMasterList = function(orgId, offset, noOfRecordsPerPage)
					{
						$rootScope.startLoader();
						var data = "";
						offset = offset != null ? offset : 0;
						noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage : 10;
						var URI1 = BASE_URL + ROOT_URL + LIS_GLOBAL + LIST_AGE_GROUP_MASTER + S + orgId + S + offset
								+ S + noOfRecordsPerPage;
						var URI2 = BASE_URL + ROOT_URL + LIS_GLOBAL + COUNT_AGE_GROUP_MASTER + S + orgId;
						promiseFactory.setPromises(URI1, URI2, "GET", "GET").then(function(response) 
						{
							$rootScope.stopLoader();
							$scope.commonList = response[0].listObject;
							$scope.commonListCount = response[1].object;
							$scope.setPage(1, false);
							console.log($scope.commonList);
						});
					}

					$scope.getAgeGroupMasterList = function(orgId, offset, noOfRecordsPerPage) {
						$rootScope.startLoader();
						console.log("offset", offset);
						var data = "";
						offset = offset != null ? offset : 0;
						noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage : 10;
						var URI = BASE_URL + ROOT_URL + LIS_GLOBAL + LIST_AGE_GROUP_MASTER + S + orgId + S + offset
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
							$scope.getAgeGroupMasterList($scope.orgId, $scope.pager.startIndex, $scope.pager.pageSize);
						}
					}

					$scope.initAgeGroupMasterList($scope.orgId, $scope.offset, $scope.noOfRecordsPerPage);

					// ====================================================CODE END FOR AGE_GROUP MASTER LIST===========================================================
					
					
					
					
					
					//code for Activating Inactivating Sample
					$scope.updateStatus = function(commonId,status)
					{
						try {
							$rootScope.startLoader();
							$scope.initAgeGroupMaster();
							var URI = BASE_URL + ROOT_URL + LIS_GLOBAL+ACTIVE_INACTIVE_AGE_GROUP_MASTER+S+$scope.orgId+S+commonId+S+status;
							console.log("ACT_INACT_URI",URI);
							GenericService.serviceAction("GET", URI, $scope.ageGroupMaster).then(function(response) 
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
							var URI = BASE_URL + ROOT_URL + LIS_GLOBAL+EDIT_AGE_GROUP_MASTER+S+$scope.orgId+S+commonId;
							console.log("EDIT",URI);
							GenericService.serviceAction("GET", URI, $scope.common).then(function(response) 
							{
								console.log("data",response.data);
								if (response.data.status == 'success')
								{
									$rootScope.stopLoader();
									$scope.common = response.data.object;
									$scope.common.ageTypeGrpfromId = response.data.object.ageTypeGrpfromId+"";
									$scope.common.ageTypeGrptoId = response.data.object.ageTypeGrptoId+"";
									$scope.common.ageGroupFrom = response.data.object.ageGroupFrom+"";
									$scope.common.ageGroupTo = response.data.object.ageGroupTo+"";
									
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
						if ($scope.ageGroupform.$valid)
						{
							try {
								$rootScope.startLoader();
								$scope.initAgeGroupMaster();
								console.log("AgeGroupMaster",JSON.stringify($scope.ageGroupMaster));
								
								var URI = BASE_URL + ROOT_URL + LIS_GLOBAL+ADD_AGE_GROUP_MASTER;
								GenericService.serviceAction("POST", URI, $scope.ageGroupMaster).then(function(response) 
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
										$scope.initAgeGroupMasterList($scope.orgId, $scope.offset, $scope.noOfRecordsPerPage);
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
						if ($scope.ageGroupform.$valid)
						{
							try {
								$rootScope.startLoader();
								$scope.initAgeGroupMaster();
								console.log("AgeGroupMaster",JSON.stringify($scope.ageGroupMaster));
								var URI = BASE_URL + ROOT_URL + LIS_GLOBAL+UPDATE_AGE_GROUP_MASTER;
								GenericService.serviceAction("PUT", URI, $scope.ageGroupMaster).then(function(response) 
								{
									if (response.data.status == 'success')
									{
										$rootScope.startLoader();
										growl.success(response.data.message,
												{
													title : 'Success!'
												});
										$scope.$broadcast('show-errors-reset');
										$scope.initCommon();
										$scope.initAgeGroupMasterList($scope.orgId, $scope.offset, $scope.noOfRecordsPerPage);
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
					$scope.fromReq = 'from is required.';
					$scope.toReq = 'to is required.';
					
					$scope.plzSelectFrom = 'Please Select From.';
					$scope.plzSelectTo = 'Please Select To.';
					
				} ]);