/**
 * 
 */

angular.module('myApp')
		.controller("priorityController",
		['$scope','$rootScope','$state','$cookies','GenericService','PagerService','promiseFactory','$sessionStorage','growl',
		function($scope,$rootScope,$state, $cookies, GenericService,PagerService,promiseFactory,$sessionStorage,growl){
	
			$rootScope.loginpage = true;
			$scope.saveBtnFlag = true;
			$scope.updateBtnFlag = false;
			$scope.updateScope = false;
			$scope.LISDynamicLabel = "Priority Description";
	
			/**Variables for pagination */
			$scope.offset = 0;
			$scope.noOfRecordsPerPage = 10;
			$scope.commonList = [];
			$scope.totalNoOfRecords;
			$scope.searchKeyword;
			$scope.commonListCount;
			$scope.orgId = 1;
			$scope.orgUnitId = 1;
			$scope.createdBy =1 ;
			$scope.updatedBy =1 ;
			
			
			$scope.initPriorityMaster = function(){
				
				$scope.common = {
					id:"",
					code:"",
					desc:""
				};
				console.log("Common-init",$scope.common);
			}
			$scope.initPriorityMaster();
			
			
			
			/**Crud Operations Start**/
			
			/**List And Pagination Start**/
			$scope.setNoOfRecords = function() {
				$scope.initPriorityMasterList($scope.orgId,$scope.offset, $scope.noOfRecordsPerPage);
			};
			
			$scope.initPriorityMasterList = function(orgId,offset, noOfRecordsPerPage)
			{
				$rootScope.startLoader();
				
				offset = offset != null ? offset : 0;
				noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage : 10;
				
				var data1 = {
						organizationId:orgId,
						offset:offset,
						noOfRecordsPerPage:noOfRecordsPerPage
						};
				
				var data2 = {
						organizationId:orgId
						};
				
				var URI1 = BASE_URL + ROOT_URL + "/api/common/getPriorityMasterList"; 
				var URI2 = BASE_URL + ROOT_URL + "/api/common/getPriorityMasterCount";
				/*promiseFactory.setPromises(URI1, URI2, "POST", "POST",data1,data2).then(function(response) 
				{
					$rootScope.stopLoader();
					$scope.commonList = response[0].listObject;
					$scope.commonListCount = response[1].object;
					$scope.setPage(1, false);
					console.log("CommonList-",$scope.commonList);
				});*/
				
				GenericService.serviceAction("POST",URI1,data1).then(function(response){
					if(response.data.status == "success"){
					$rootScope.stopLoader();
					console.log("response1",response);
					$scope.commonList = response.data.listObject;
					}
				});
				
				GenericService.serviceAction("POST",URI2,data2).then(function(response){
					if(response.data.status == "success"){
					console.log("response2",response);
						$rootScope.stopLoader();
						$scope.commonListCount = response.data.object;
						$scope.setPage(1,false);
					}
				});
				
			}
			
			
			
			$scope.PriorityMasterList = function(orgId,offset, noOfRecordsPerPage) {
				$rootScope.startLoader();
				console.log("offset", offset);
				offset = offset != null ? offset : 0;
				noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage : 10;
				
				var data = {
						organizationId:orgId,
						offset:offset,
						noOfRecordsPerPage:noOfRecordsPerPage
						};
				
				var URI = BASE_URL + ROOT_URL +"/api/common/getPriorityMasterList";
				console.log("URI", URI);
				GenericService.serviceAction("POST", URI, data).then(function(response) {
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
					$scope.PriorityMasterList($scope.orgId ,$scope.pager.startIndex, $scope.pager.pageSize);
				}
			}

			$scope.initPriorityMasterList($scope.orgId,$scope.offset, $scope.noOfRecordsPerPage);
			
			/**List And Pagination End**/
			
			$scope.save = function(){
				try{
					
					$rootScope.startLoader();
					var date = new Date();
					$scope.dt = moment(date).format('DD-MM-YYYY HH:mm:ss');
					
					var priorityObj = {
							priorityCode : $scope.common.code,
							priorityDesc : $scope.common.desc,
							createdBy:$scope.createdBy,
							createdDate:$scope.dt,
							status:'A',
							organizationId:$scope.orgId,
							updatedDate:$scope.dt
					}
					var URI = BASE_URL + ROOT_URL +"/api/common/savePriorityMasterMaster";
					console.log("Save-URI-",URI);
					console.log("OBJ",priorityObj);
					
					GenericService.serviceAction("POST",URI,priorityObj).then(function(response){
						
						if (response.data.status == 'success')
						{
							$rootScope.stopLoader();
							growl.success(response.data.message,
									{
										title : 'Success!'
									});
							$scope.$broadcast('show-errors-reset');
							$scope.initPriorityMaster();
						} else if(response.data.status == 'error')
						{
							$rootScope.stopLoader();
							growl.error(response.data.message,
									{
										title : 'Error!'
									});
						}
					});
					
				}catch(e){
					console.log("Exception-",e);
				}
			}
			
			$scope.showUpdateBtn = function(priorityId){
				try{
					//$rootScope.startLoader();
					$scope.saveBtnFlag = false;
					$scope.updateScope = true;
					$scope.updateBtnFlag = true;
					
					var URI = BASE_URL + ROOT_URL + "/api/common/getPriorityMasterById"+S+priorityId+S+$scope.orgId;
					console.log("EDIT",URI);
					GenericService.serviceAction("GET",URI).then(function(response){
						console.log("Edit",response);
						
						if(response.data.status == "success"){
							$rootScope.stopLoader();
							$scope.common = response.data.listObject[0];
							
						}else if(response.data.status == 'error'){
							$rootScope.stopLoader();
							growl.error(response.data.message,
									{
										title : 'Error!'
									});
						}
					});
					
				}catch(e){
					console.log("Exception-",e);
				}
			}
			
			
			$scope.update = function(){
				try{
					
					$rootScope.startLoader();
					var date = new Date();
					$scope.dt = moment(date).format('DD-MM-YYYY HH:mm:ss');
					
					var priorityObj = {
							priorityCode : $scope.common.code,
							priorityDesc : $scope.common.desc,
							createdBy:$scope.createdBy,
							createdDate:$scope.dt,
							status:'A',
							organizationId:$scope.orgId,
							updatedDate:$scope.dt
					}
					var URI = BASE_URL + ROOT_URL +"/api/common/updatePriorityMaster";
					console.log("Update-URI-",URI);
					console.log("OBJ",priorityObj);
					
					GenericService.serviceAction("POST",URI,priorityObj).then(function(response){
						
						if (response.data.status == 'success')
						{
							$rootScope.stopLoader();
							growl.success(response.data.message,
									{
										title : 'Success!'
									});
							$scope.$broadcast('show-errors-reset');
							$scope.initPriorityMaster();
						} else if(response.data.status == 'error')
						{
							$rootScope.stopLoader();
							growl.error(response.data.message,
									{
										title : 'Error!'
									});
						}
					});
					
				}catch(e){
					console.log("Exception-",e);
				}
			}
			
			$scope.updateStatus = function(priorityId,status){
				try{
					var date = new Date();
					$scope.dt = moment(date).format('DD-MM-YYYY HH:mm:ss');
					
					var priorityObj = {
							priorityId:priorityId,
							status:status,
							updatedDate:$scope.dt,
							updatedBy:1
					}
					var URI = BASE_URL + ROOT_URL +"/api/common/updatePriorityMasterStatus";
					
					GenericService.serviceAction("POST",URI,priorityObj).then(function(response){
						console.log("Response-",response);
					});
				}catch(e){
					console.log("Exception",e);
				}
			}
			
			/**Crud Operations End**/
}]);