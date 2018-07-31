angular.module('myApp').controller("clinicMasterController",
		['$scope','$rootScope','$state','$cookies','GenericService','PagerService','promiseFactory','$sessionStorage','growl',
			function($scope,$rootScope,$state, $cookies, GenericService,PagerService,promiseFactory,$sessionStorage,growl){
					
			$rootScope.loginpage = true;
			$scope.saveBtnFlag = true;
			$scope.updateBtnFlag = false;
			$scope.updateScope = false;
			
			/**Variables for pagination */
			$scope.offset = 0;
			$scope.noOfRecordsPerPage = 10;
			$scope.clinicMasterList = [];
			$scope.totalNoOfRecords;
			$scope.searchKeyword;
			$scope.clinicMasterListCount;
			$scope.orgId = 1;
			$scope.unitId = 1;
			$scope.createdBy =1 ;
			$scope.updatedBy =1 ;
			var date = new Date();
			$scope.dt = moment(date).format('DD-MM-YYYY HH:mm:ss');
			console.log("Dt",$scope.dt);
			$scope.initClinicMaster = function(){
				$scope.clinic ={
						
						clinicMasterId:"",
						floorId:"",
						wingId:"",
						clinicCode:"",
						clinicDescription:"",
						created_by:$scope.createdBy,
						updated_by:$scope.updatedBy,
						cerated_date:$scope.dt,
						updated_date:$scope.dt,
						organizationId:$scope.orgId,
						unitId:$scope.unitId,
						status:'A'
					};
				
				
			}
			
			$scope.initClinicMaster();
			
			
			$scope.getFloorMasterList = function(orgId,unitId){
				
				var URI = BASE_URL + ROOT_URL + "/adt/getFloorMasterList";
				$scope.data = {
						organizationId:orgId,
						unitId:unitId
				}
				
				GenericService.serviceAction("POST",URI,$scope.data).then(function(response){
					console.log("Floor-List-",response);
					if(response.data.status == "success"){
						$scope.floorList = response.data.listObject;
						
					}
					
				});
			}
			$scope.getFloorMasterList($scope.orgId,$scope.unitId);
			
			
			$scope.getWingMasterList = function(orgId,unitId){
				
				var URI = BASE_URL + ROOT_URL + "/adt/getWingMasterListByOrgAndUnit"+ S + orgId + S + unitId ;
				$scope.data = "";
				console.log("WING_URI",URI);
				GenericService.serviceAction("GET",URI,$scope.data).then(function(response){
					console.log("Wing-List-",response);
					if(response.data.status == "success"){
						$scope.wingList = response.data.listObject;
						
					}
					
				});
			}
			$scope.getWingMasterList($scope.orgId,$scope.unitId);
			
			
			/**crud operations start**/
			
			$scope.setNoOfRecords = function() {
				$scope.initClinicMasterList($scope.orgId,$scope.unitId, $scope.offset, $scope.noOfRecordsPerPage);
			};
			$scope.initClinicMasterList = function(orgId, unitId, offset, noOfRecordsPerPage)
			{
				$rootScope.startLoader();
				var data = "";
				offset = offset != null ? offset : 0;
				noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage : 10;
				var URI1 = BASE_URL + ROOT_URL + "/api/global/getClinicMasterList" + S + orgId + S  + unitId + S + offset
						+ S + noOfRecordsPerPage; 
				var URI2 = BASE_URL + ROOT_URL + "/api/global/getClinicMasterCount"+ S + orgId + S  + unitId ;
				promiseFactory.setPromises(URI1, URI2, "GET", "GET").then(function(response) 
				{
					$rootScope.stopLoader();
					$scope.clinicMasterList = response[0].listObject;
					$scope.clinicMasterListCount = response[1].object;
					$scope.setPage(1, false);
					console.log($scope.clinicMasterList);
				});
			}
			
			
			$scope.getClinicMasterList = function(orgId,unitId, offset, noOfRecordsPerPage) {
				$rootScope.startLoader();
				console.log("offset", offset);
				var data = "";
				offset = offset != null ? offset : 0;
				noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage : 10;
				var URI = BASE_URL + ROOT_URL +"/api/global/getClinicMasterList"+ S + orgId + S + unitId + S + offset
						+ S + noOfRecordsPerPage;
				console.log("URI", URI);
				GenericService.serviceAction("GET", URI, data).then(function(response) {
					$rootScope.stopLoader();
					$scope.clinicMasterList = response.data.listObject;
				});
			}

			$scope.pager = {};
			$scope.page;

			$scope.setPage = function(page, flag) {
				if (page < 1 || page > $scope.pager.totalPages)
				{
					return;
				}
				$scope.pager = PagerService.GetPager($scope.clinicMasterListCount, page, $scope.noOfRecordsPerPage);
				if (flag)
				{
					$scope.getClinicMasterList($scope.orgId, $scope.unitId, $scope.pager.startIndex, $scope.pager.pageSize);
				}
			}

			$scope.initClinicMasterList($scope.orgId,$scope.unitId, $scope.offset, $scope.noOfRecordsPerPage);
			
			
			
			$scope.saveClinicMaster = function(){
				
				var URI  = BASE_URL + ROOT_URL + "/api/global/saveClinicMaster";
				console.log("$scope.clinic",$scope.clinic);
				GenericService.serviceAction("POST",URI,$scope.clinic).then(function(response){
					console.log(response);
					if(response.data.status == "success"){
						
						$rootScope.stopLoader();
						growl.success(response.data.message,
								{
									title : 'Success!'
								});
						$scope.$broadcast('show-errors-reset');
						
					}else if(response.data.status == "success"){
					
						$rootScope.stopLoader();
						growl.error(response.data.message,
								{
									title : 'Error!'
								});
					}
					$scope.initClinicMaster();
					$scope.getClinicMasterList($scope.orgId, $scope.unitId, $scope.pager.startIndex, $scope.pager.pageSize);
				});
			}
			
			
			$scope.editClinic = function(clinicMasterId){
					try{
						$rootScope.startLoader();
						$scope.saveBtnFlag = false;
						$scope.updateScope = true;
						$scope.updateBtnFlag = true;
						var data = "";
						var URI = BASE_URL + ROOT_URL + "/api/global/getClinicMasterById/"+S+ clinicMasterId +S+ $scope.orgId +S+ $scope.unitId ;
						console.log("Edit url",URI);
						
						GenericService.serviceAction("GET", URI, data).then(function(response) 
								{

									console.log("data",response.data);

									if (response.data.status == 'success')
									{
										$rootScope.stopLoader();
										$scope.clinic = response.data.object;
										console.log("$scope.clinic",$scope.clinic);
										
									} else if(response.data.status == 'error')
									{
										$rootScope.stopLoader();
										alert("Error In Fetching Data");
									}
								});
					}catch(e){
						console.log("EXception",e);
					}
			}
			
			
			$scope.updateClinicMaster = function(){
							
							var URI  = BASE_URL + ROOT_URL + "/api/global/updateClinicMaster";
							$scope.clinic.cerated_date = $scope.dt;
							$scope.clinic.updated_date = $scope.dt;
							console.log("$scope.clinic",$scope.clinic);
							
							GenericService.serviceAction("POST",URI,$scope.clinic).then(function(response){
								console.log(response);
								if(response.data.status == "success"){
									
									$rootScope.stopLoader();
									growl.success(response.data.message,
											{
												title : 'Success!'
											});
									$scope.$broadcast('show-errors-reset');
									
								}else if(response.data.status == "success"){
								
									$rootScope.stopLoader();
									growl.error(response.data.message,
											{
												title : 'Error!'
											});
								}
								$scope.initClinicMaster();
								$scope.getClinicMasterList($scope.orgId, $scope.unitId, $scope.pager.startIndex, $scope.pager.pageSize);
							});
						}
			
			
			$scope.updateClinicStatus = function(clinicMasterId,status){
				try{
					var URI =  BASE_URL + ROOT_URL + "/api/global/updatedClinicStatus"+S+clinicMasterId + S + status;
					var data ="";
					GenericService.serviceAction("GET",URI,data).then(function(response){
						console.log("Status Response",response);
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
				}catch(e){
					console.log("Exception",e);
				}
			}
			/**crud operations end**/
				}]);