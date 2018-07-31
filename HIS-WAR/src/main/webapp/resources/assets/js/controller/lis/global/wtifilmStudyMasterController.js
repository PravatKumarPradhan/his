/**
 * @author  Suraj  Kumbhoje
 */

angular.module('myApp').controller(
		"wtifilmStudyMasterController",
		[ '$scope','$rootScope','$state','$cookies','GenericService','PagerService','promiseFactory','$sessionStorage',
			'growl',
				function($scope,$rootScope,$state, $cookies, GenericService,PagerService,promiseFactory,$sessionStorage,growl)
				{
			       
			        $rootScope.loginpage = true;
					$scope.LISDynamicLabel = "Wet film Study";
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
					$(".selectedPageName").text("Wet film Study Master");
					
					$scope.initCommon = function()
					{
						$scope.saveBtnFlag = true; 
						$scope.updateBtnFlag = false;
						$scope.common =
						{
							 "id":"",
							 "code":"",
							 "desc":"",
						     "status":"A",
							 "createdBy":$scope.createdBy,
							 "createdDate":"",
							 "updatedBy":$scope.updatedBy,
							 "updatedDate":"",
						     "orgId":$scope.orgId,
						}
					}
					$scope.initCommon();
					
					$scope.initWtifilmStudyMaster = function(){
					$scope.wtifilmStudyMaster =
					{
						 "wetfilmStudyId":$scope.common.id,
						 "code":$scope.common.code,
						 "desc":$scope.common.desc,
					     "status":$scope.common.status,
						 "createdBy":$scope.common.createdBy,
						 "createdDate":$scope.common.createdDate,
						 "updatedBy":$scope.common.updatedBy,
						 "updatedDate":$scope.common.updatedDate,
					     "orgId":$scope.common.orgId
					 }
					}
					
					// ====================================================CODE START FORFOR WT_FILM_STUDY MASTER LIST===========================================================

					$scope.setNoOfRecords = function() {
						$scope.initWtifilmStudyMasterList($scope.orgId, $scope.offset, $scope.noOfRecordsPerPage);
					};
					$scope.initWtifilmStudyMasterList = function(orgId, offset, noOfRecordsPerPage)
					{
						var data = "";
						$rootScope.startLoader();
						offset = offset != null ? offset : 0;
						noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage : 10;
						var URI1 = BASE_URL + ROOT_URL + LIS_GLOBAL + LIST_WT_FILM_STUDY_MASTER + S + orgId + S + offset
								+ S + noOfRecordsPerPage;
						var URI2 = BASE_URL + ROOT_URL + LIS_GLOBAL + COUNT_WT_FILM_STUDY_MASTER + S + orgId;
						promiseFactory.setPromises(URI1, URI2, "GET", "GET").then(function(response) 
						{
							$rootScope.stopLoader();
							$scope.commonList = response[0].listObject;
							$scope.commonListCount = response[1].object;
							$scope.setPage(1, false);
							console.log($scope.commonList);
						});
					}

					$scope.getWtifilmStudyMasterList = function(orgId, offset, noOfRecordsPerPage) {
						console.log("offset", offset);
						var data = "";
						$rootScope.startLoader();
						offset = offset != null ? offset : 0;
						noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage : 10;
						var URI = BASE_URL + ROOT_URL + LIS_GLOBAL + LIST_WT_FILM_STUDY_MASTER + S + orgId + S + offset
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
							$scope.getWtifilmStudyMasterList($scope.orgId, $scope.pager.startIndex, $scope.pager.pageSize);
						}
					}

					$scope.initWtifilmStudyMasterList($scope.orgId, $scope.offset, $scope.noOfRecordsPerPage);

					// ====================================================CODE END FOR WT_FILM_STUDY MASTER LIST===========================================================
					
					
					
					
					
					//code for Activating Inactivating Sample
					$scope.updateStatus = function(commonId,status)
					{
						try {
							$rootScope.startLoader();
							$scope.initWtifilmStudyMaster();
							var URI = BASE_URL + ROOT_URL + LIS_GLOBAL+ACTIVE_INACTIVE_WT_FILM_STUDY_MASTER+S+$scope.orgId+S+commonId+S+status;
							console.log("ACT_INACT_URI",URI);
							GenericService.serviceAction("GET", URI, $scope.wtifilmStudyMaster).then(function(response) 
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
							var URI = BASE_URL + ROOT_URL + LIS_GLOBAL+EDIT_WT_FILM_STUDY_MASTER+S+$scope.orgId+S+commonId;
							console.log("EDIT",URI);
							GenericService.serviceAction("GET", URI, $scope.common).then(function(response) 
							{
								console.log("data",response.data);
								if (response.data.status == 'success')
								{
									$rootScope.stopLoader();
									$scope.common = response.data.object;
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
						if ($scope.commonform.$valid)
						{
							try {
								$rootScope.startLoader();
								$scope.initWtifilmStudyMaster();
								console.log("WtifilmStudyMaster",JSON.stringify($scope.wtifilmStudyMaster));
								var URI = BASE_URL + ROOT_URL + LIS_GLOBAL+ADD_WT_FILM_STUDY_MASTER;
								GenericService.serviceAction("POST", URI, $scope.wtifilmStudyMaster).then(function(response) 
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
										$scope.initWtifilmStudyMasterList($scope.orgId, $scope.offset, $scope.noOfRecordsPerPage);
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
						if ($scope.commonform.$valid)
						{
							try {
								$rootScope.startLoader();
								$scope.initWtifilmStudyMaster();
								console.log("WtifilmStudyMaster",JSON.stringify($scope.wtifilmStudyMaster));
								var URI = BASE_URL + ROOT_URL + LIS_GLOBAL+UPDATE_WT_FILM_STUDY_MASTER;
								GenericService.serviceAction("PUT", URI, $scope.wtifilmStudyMaster).then(function(response) 
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
										$scope.initWtifilmStudyMasterList($scope.orgId, $scope.offset, $scope.noOfRecordsPerPage);
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
					
				} ]);