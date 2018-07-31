/**
 * @author  Ganesh Chaudhari
 * @author dell
 */

angular.module('myApp').controller(
		"storageLocationMstForSlideBlocksController",
		[ '$scope','$rootScope','$state','$cookies','GenericService','PagerService','promiseFactory','$sessionStorage',
			'growl',
				function($scope,$rootScope,$state, $cookies, GenericService,PagerService,promiseFactory,$sessionStorage,growl)
				{
			       
			        $rootScope.loginpage = true;
					$scope.LISDynamicLabel = "Storage Location Master For Slide Blocks Controller";
					$scope.saveBtnFlag = true;
					$scope.updateBtnFlag = false;
					$scope.updateScope = false;
					
					/**Variables for pagination */
					$scope.offset = 0;
					$scope.noOfRecordsPerPage = 10;
					$scope.rackList = [];
					$scope.totalNoOfRecords;
					$scope.searchKeyword;
					$scope.rackListCount;
					$scope.orgId = 1;
					$scope.orgUnitId = 1;
					$scope.createdBy =1 ;
					$scope.updatedBy =1 ;
					
					$scope.rackShelfMasterList=[];
					/* code for setting Label */
					$(".selectedPageName").text("Storage Location Master For Slide Blocks ");
					
					
					
					$scope.initRackMaster = function(){
						$scope.rackShelfMasterList=[];
						var rackShelfMaster = angular
						.copy($scope.RackShelfMaster);
			        	$scope.rackShelfMasterList.push(rackShelfMaster);
				
				
					$scope.RackMaster =
					{
						 "rackId":"",
						 "rackCode":$scope.rackCode,
						 "rackName":$scope.rackName,
						 "status":"A",
						 "createdBy":"",
						 "createdDate":$scope.createdDate,
						 "updatedBy":"",
						 "updatedDate":$scope.updatedDate,
					     "orgId":$scope.orgId,
					     "orgUnitId":$scope.orgUnitId,
					     "listRackShelfMasterDto":$scope.listRackShelfMstDto
					 }
				
					}
					
					
					$scope.RackShelfMaster =
					{
						 "rackId":"",
						 "shelfId":"",
						 "shelfCode":"",
						 "shelfName":"",
						 "isDeleted":"N",
					     "status":"A",
						 "createdBy":$scope.createdBy,
						 "createdDate":$scope.createdDate,
						 "updatedBy":$scope.updatedBy,
						 "updatedDate":$scope.updatedDate,
					     "orgId":$scope.orgId,
					     "orgUnitId":$scope.orgUnitId
					 }
					
					// ====================================================CODE START FORFOR RACK MASTERLIST===========================================================

					$scope.setNoOfRecords = function() {
						$scope.initRackMasterList($scope.orgId,$scope.orgUnitId, $scope.offset, $scope.noOfRecordsPerPage);
					};
					$scope.initRackMasterList = function(orgId, orgUnitId, offset, noOfRecordsPerPage)
					{
						$rootScope.startLoader();
						var data = "";
						offset = offset != null ? offset : 0;
						noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage : 10;
						var URI1 = BASE_URL + ROOT_URL + LIS_UNIT + LIST_STORAGE_SLIDE_MASTER + S + orgId + S  + orgUnitId + S + offset
								+ S + noOfRecordsPerPage; 
						var URI2 = BASE_URL + ROOT_URL + LIS_UNIT + COUNT_STORAGE_SLIDE_MASTER + S + orgId + S  + orgUnitId ;
						promiseFactory.setPromises(URI1, URI2, "GET", "GET").then(function(response) 
						{
							$rootScope.stopLoader();
							$scope.rackList = response[0].listObject;
							$scope.rackListCount = response[1].object;
							$scope.setPage(1, false);
							console.log($scope.rackList);
						});
					}

					$scope.getRackMasterList = function(orgId,orgUnitId, offset, noOfRecordsPerPage) {
						$rootScope.startLoader();
						console.log("offset", offset);
						var data = "";
						offset = offset != null ? offset : 0;
						noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage : 10;
						var URI = BASE_URL + ROOT_URL + LIS_UNIT + LIST_STORAGE_SLIDE_MASTER + S + orgId + S + orgUnitId + S + offset
								+ S + noOfRecordsPerPage;
						console.log("URI", URI);
						GenericService.serviceAction("GET", URI, data).then(function(response) {
							$rootScope.stopLoader();
							$scope.rackList = response.data.listObject;
						});
					}

					$scope.pager = {};
					$scope.page;

					$scope.setPage = function(page, flag) {
						if (page < 1 || page > $scope.pager.totalPages)
						{
							return;
						}
						$scope.pager = PagerService.GetPager($scope.rackListCount, page, $scope.noOfRecordsPerPage);
						if (flag)
						{
							$scope.getRackMasterList($scope.orgId, $scope.orgUnitId, $scope.pager.startIndex, $scope.pager.pageSize);
						}
					}

					$scope.initRackMasterList($scope.orgId,$scope.orgUnitId, $scope.offset, $scope.noOfRecordsPerPage);

					// ====================================================CODE END FOR RACK MASTERLIST===========================================================
					
					
					//code for Activating Inactivating Rack and Shelf
					$scope.updateStatus = function(rackId,status)
					{
						try {
							$rootScope.startLoader();
							//$scope.initRackMaster();
							var URI = BASE_URL + ROOT_URL + LIS_UNIT+ACTIVE_INACTIVE_STORAGE_SLIDE_MASTER+S+$scope.orgId+S+rackId+S+status;
							console.log("ACT_INACT_URI",URI);
							GenericService.serviceAction("GET", URI, $scope.rackMaster).then(function(response) 
							{
								if (response.data.status == 'success')
								{
									$rootScope.stopLoader();
									growl.success(response.data.message,
											{
												title : 'Success!'
											});
									$scope.$broadcast('show-errors-reset');
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
					
					$scope.initRackMaster();
					$scope.addRackShelfData=function(){
						
						
						
						 if($scope.updateScope ==false)
				        	{
									var rackShelfMaster = angular
											.copy($scope.RackShelfMaster);
									$scope.rackShelfMasterList.push(rackShelfMaster);
				        	}else{
				        		
							  	var rackShelfMaster = angular.copy($scope.RackShelfMaster);
							  	    rackShelfMaster.rackId=$scope.RackMaster.rackId;
							  		$scope.rackShelfMasterList.push(rackShelfMaster);
				        	}
						
						
					}
					
					$scope.removeRackShelfData = function(deleteIndex)
					{
						$scope.rackShelfMasterList.splice(deleteIndex, 1);
				     }
					
					$scope.showUpdateBtn = function(rackId)
					{
						try {
							$rootScope.startLoader();
							$scope.saveBtnFlag = false;
							$scope.updateScope = true;
							$scope.updateBtnFlag = true;
							var URI = BASE_URL + ROOT_URL + LIS_UNIT+EDIT_STORAGE_SLIDE_MASTER+S+$scope.orgId+S+$scope.orgUnitId +S+rackId;
							console.log("EDIT",URI);
							GenericService.serviceAction("GET", URI, $scope.common).then(function(response) 
							{

								console.log("data",response.data);

								if (response.data.status == 'success')
								{
									$rootScope.stopLoader();
									$scope.RackMaster = response.data.object;
									$scope.rackShelfMasterList = response.data.object.listRackShelfMasterDto;
									console.log("$scope.RackMaster",$scope.RackMaster);
									
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
						if ($scope.rackStoreLocationForm.$valid)
						{
							try 
							{
								$rootScope.startLoader();
								$scope.RackMaster.listRackShelfMasterDto= $scope.rackShelfMasterList;
								console.log("RackMaster",JSON.stringify($scope.RackMaster));
								var URI = BASE_URL + ROOT_URL + LIS_UNIT+ADD_STORAGE_SLIDE_MASTER;
								GenericService.serviceAction("POST", URI,$scope.RackMaster).then(function(response) 
								{	
									if (response.data.status == 'success')
									{
										$rootScope.stopLoader();
										growl.success(response.data.message,
												{
													title : 'Success!'
												});
										$scope.$broadcast('show-errors-reset');
										$scope.initRackMaster();
										$scope.initRackMasterList($scope.orgId,$scope.orgUnitId, $scope.offset, $scope.noOfRecordsPerPage);
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
						if ($scope.rackStoreLocationForm.$valid)
						{
							try {
								$rootScope.startLoader();
								console.log("RackMaster",JSON.stringify($scope.RackMaster));
								var URI = BASE_URL + ROOT_URL + LIS_UNIT+UPDATE_STORAGE_SLIDE_MASTER;
								GenericService.serviceAction("PUT", URI, $scope.RackMaster).then(function(response) 
								{
									if (response.data.status == 'success')
									{
										$rootScope.stopLoader();
										growl.success(response.data.message,
												{
													title : 'Success!'
												});
										$scope.$broadcast('show-errors-reset');
										$scope.initRackMaster();
										$scope.initRackMasterList($scope.orgId,$scope.orgUnitId, $scope.offset, $scope.noOfRecordsPerPage);
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
					$scope.rackCode="Rack code requried."
					$scope.rackName="Rack name requried."
					$scope.rackShelfName="Shelf name requried."
					$scope.rackShelfCode="Shelf code requried."
				} ]);
