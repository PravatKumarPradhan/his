/**
 * @author  Suraj  Kumbhoje
 */

angular.module('myApp').controller(
		"mediaColnoyAdditionController",
		[ '$scope','$rootScope','$state','$cookies','GenericService','PagerService','promiseFactory','$sessionStorage',
			'growl',
				function($scope,$rootScope,$state, $cookies, GenericService,PagerService,promiseFactory,$sessionStorage,growl)
				{
			       
			        $rootScope.loginpage = true;
					$scope.LISDynamicLabel = "Media Colnoy Addition";
					$scope.saveBtnFlag = true;
					$scope.updateBtnFlag = false;
					$scope.colony = [];
					
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
					$scope.colonyList = [];
					$scope.selectedColonyList = [];
					
					/* code for setting Label */
					$(".selectedPageName").text("Media Colnoy Master");
					
					$scope.initCommon = function()
					{
						$scope.saveBtnFlag = true; 
						$scope.updateBtnFlag = false;
						$scope.common =
						{
							 "mediaColonyMpprId":"",
							 "mediaId":"",
							 "colonyId":"",
						     "status":"A",
							 "createdBy":$scope.createdBy,
							 "createdDate":"",
							 "updatedBy":$scope.updatedBy,
							 "updatedDate":"",
						     "orgId":$scope.orgId,
						     "mediaCode":$scope.mediaCode, 
						     "mediaDesc":$scope.mediaDesc 
						     
						}
					}
					$scope.initCommon();
					
					$scope.initMediaColonyAdditionMaster = function(){
					$scope.mediaColonyAdditionMaster =
					{
						 "mediaColonyMpprId":$scope.common.mediaColonyMpprId,
						 "mediaId":$scope.common.mediaId,
						 "colonyId":$scope.common.colonyId,
					     "status":$scope.common.status,
						 "createdBy":$scope.common.createdBy,
						 "createdDate":$scope.common.createdDate,
						 "updatedBy":$scope.common.updatedBy,
						 "updatedDate":$scope.common.updatedDate,
					     "orgId":$scope.common.orgId,
					     "mediaCode":$scope.mediaCode, 
					     "mediaDesc":$scope.mediaDesc,
					     "isDeleted": "N"
					 }
					
					}
					
					// ====================================================CODE START FORFOR AGE_GROUP MASTER LIST===========================================================

					
					
					/*
					 *  Get Colony Master List
					 */
					$scope.getColonyList = function() {
						try {
							var data = "";
							var URI = BASE_URL + ROOT_URL
							        + LIS_COMMON
									+ GET_MEDIA_ADDTION_COLONY_LIST
									+ S + $scope.orgId;
							console.log("URI", URI);
							GenericService
									.serviceAction("GET", URI, data)
									.then(
											function(response) {
												$scope.colonyList  = response.data.listObject;
											});
						} catch (e) {
							console.log(e.message)
						}

					}
					$scope.getColonyList();
					
					/*
					 *  Get Media Master List
					 */
					$scope.getMediaList = function() {
						try {
							var data = "";
							var URI = BASE_URL + ROOT_URL
							        + LIS_COMMON
									+ GET_COLONY_ADDTION_MEDIA_CLASS_LIST
									+ S + $scope.orgId;
							console.log("URI", URI);
							GenericService
									.serviceAction("GET", URI, data)
									.then(
											function(response) {
												$scope.mediaList  = response.data.listObject;
											});
						} catch (e) {
							console.log(e.message)
						}

					}
					$scope.getMediaList();
					
					$scope.setNoOfRecords = function() {
						$scope.initMediaColonyAdditionMasterList($scope.orgId, $scope.offset, $scope.noOfRecordsPerPage);
					};
					$scope.initMediaColonyAdditionMasterList = function(orgId, offset, noOfRecordsPerPage)
					{
						$rootScope.startLoader();
						var data = "";
						offset = offset != null ? offset : 0;
						noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage : 10;
						var URI1 = BASE_URL + ROOT_URL + LIS_GLOBAL + GET_TOTAL_COLONY_ADDTION_MEDIA_CLASS_LIST + S + orgId + S + offset
								+ S + noOfRecordsPerPage;
						var URI2 = BASE_URL + ROOT_URL + LIS_GLOBAL + GET_COUNT_COLONY_ADDTION_MEDIA_CLASS_LIST + S + orgId;
						promiseFactory.setPromises(URI1, URI2, "GET", "GET").then(function(response) 
						{
							$rootScope.stopLoader();
							$scope.commonList = response[0].listObject;
							$scope.commonListCount = response[1].object;
							$scope.setPage(1, false);
							console.log($scope.commonList);
						});
					}

					$scope.getMediaColonyAdditionMasterList = function(orgId, offset, noOfRecordsPerPage) {
						$rootScope.startLoader();
						console.log("offset", offset);
						var data = "";
						offset = offset != null ? offset : 0;
						noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage : 10;
						var URI = BASE_URL + ROOT_URL + LIS_GLOBAL + GET_COLONY_ADDTION_MEDIA_CLASS_LIST + S + orgId + S + offset
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
							$scope.getMediaColonyAdditionMasterList($scope.orgId, $scope.pager.startIndex, $scope.pager.pageSize);
						}
					}

					$scope.initMediaColonyAdditionMasterList($scope.orgId, $scope.offset, $scope.noOfRecordsPerPage);

					// ====================================================CODE END FOR AGE_GROUP MASTER LIST===========================================================
					
					
					
					
					
					//code for Activating Inactivating Sample
					$scope.updateStatus = function(commonId,status)
					{
						try {
							$rootScope.startLoader();
							$scope.initMediaColonyAdditionMaster();
							var URI = BASE_URL + ROOT_URL + LIS_GLOBAL+ACTIVE_INACTIVE_INCUBATION_MEDIA_ADDTION_MASTER+S+$scope.orgId+S+commonId+S+status;
							console.log("ACT_INACT_URI",URI);
							GenericService.serviceAction("GET", URI, $scope.mediaColonyAdditionMaster).then(function(response) 
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
									$scope.initMediaColonyAdditionMasterList($scope.orgId, $scope.offset, $scope.noOfRecordsPerPage);
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
							var URI = BASE_URL + ROOT_URL + LIS_GLOBAL+EDIT_MEDIA_ADDTION_MASTER+S+$scope.orgId+S+commonId;
							console.log("EDIT",URI);
							GenericService.serviceAction("GET", URI, $scope.common).then(function(response) 
							{
								console.log("data",response.data);
								
								if (response.data.status == 'success')
								{
									$rootScope.stopLoader();
									$scope.common.mediaId = response.data.listObject[0].mediaId + "";
									$scope.selectedColonyList = [];
									$scope.tempColonys = [];
									angular.forEach($scope.colony,function(value,index){
										$scope.colony[index] = false;
										var i = $scope.tempSelectColony.indexOf(value);
										$scope.tempSelectColony.splice(i,1);
									})
									angular.forEach(response.data.listObject,function(value,index){
										var temp = {colonyId:value.colonyId,desc:value.colonyName};
										$scope.tempSelectColony.push(temp);
										$scope.colony[value.colonyId] = true;
										$scope.selectedColonyList.push(temp);
									});
									
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
								$scope.initMediaColonyAdditionMaster();
								$scope.listMediaColonyAdditionMaster =
								{
									 "mediaId":$scope.common.mediaId,
								     "orgId":$scope.common.orgId,
								     "status":"A",
									 "createdBy":$scope.common.createdBy,
									 "createdDate":$scope.common.createdDate,
									 "updatedBy":$scope.common.updatedBy,
									 "updatedDate":$scope.common.updatedDate,
								     "selectediaColonyMpprList":[]
								     
								 }
								angular.forEach($scope.selectedColonyList,function(value,index){
										$scope.listMediaColonyAdditionMaster.selectediaColonyMpprList.push(value.colonyId);
								})
								console.log("MediaColonyAdditionMaster",JSON.stringify($scope.listMediaColonyAdditionMaster));
								var URI = BASE_URL + ROOT_URL + LIS_GLOBAL+ADD_MEDIA_ADDTION_MASTER;
								GenericService.serviceAction("POST", URI, $scope.listMediaColonyAdditionMaster).then(function(response) 
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
										$scope.initMediaColonyAdditionMasterList($scope.orgId, $scope.offset, $scope.noOfRecordsPerPage);
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
								$scope.initMediaColonyAdditionMaster();
								$scope.listMediaColonyAdditionMaster =
								{
									 "mediaId":$scope.common.mediaId,
								     "orgId":$scope.common.orgId,
								     "status":"A",
									 "createdBy":$scope.common.createdBy,
									 "createdDate":$scope.common.createdDate,
									 "updatedBy":$scope.common.updatedBy,
									 "updatedDate":$scope.common.updatedDate,
								     "selectediaColonyMpprList":[]
								     
								 }
								angular.forEach($scope.selectedColonyList,function(value,index){
										$scope.listMediaColonyAdditionMaster.selectediaColonyMpprList.push(value.colonyId);
								})
								console.log("MediaColonyAdditionMaster",JSON.stringify($scope.listMediaColonyAdditionMaster));
							
								var URI = BASE_URL + ROOT_URL + LIS_GLOBAL+UPDATE_MEDIA_ADDTION_MASTER;
							    GenericService.serviceAction("PUT", URI, $scope.listMediaColonyAdditionMaster).then(function(response) 
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
										$scope.initMediaColonyAdditionMasterList($scope.orgId, $scope.offset, $scope.noOfRecordsPerPage);
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
					
					
					$scope.tempSelectColony = [];
					$scope.addTempColony = function(colonyId,desc){
						
						var flag = false;
						angular.forEach($scope.tempSelectColony,function(value,index){
							if(value.colonyId == colonyId){
								$scope.tempSelectColony.splice(index,1);
								$scope.colony[value.colonyId] = false;
								flag = true;
							}
						})
						if(!flag){
							var temp = {colonyId:colonyId,desc:desc};
							$scope.tempSelectColony.push(temp);
						}
					}
					
					$scope.selectColony = function()
					{
						$scope.selectedColonyList = angular.copy($scope.tempSelectColony);
					}
					
					
					
					/*
					 *  Get Colonys List By Media Class Id
					 */
					$scope.getColonyListByMediaId = function(mediaId) {
						try {
							var data = "";
							var URI = BASE_URL + ROOT_URL
							        + LIS_GLOBAL
									+ GET_COLONY_LIST_BY_MEDIA_CLASS_ID
									+ SLASH
									+ mediaId;
							console.log("URI", URI);
							GenericService
									.serviceAction("GET", URI, data)
									.then(
											function(response) {
												$scope.colonysListByMediaId  = response.data.listObject;
												console.log("colonysListByMediaId",JSON.stringify($scope.colonysListByMediaId));
											});
						} catch (e) {
							console.log(e.message)
						}

					}
					
					//$scope.getColonyListByMediaId(2);
					
					/**=============================>Errors Messages  <=======================================**/
					
					$scope.plzSelectMedia= 'Please Select Media.';
					
				} ]);