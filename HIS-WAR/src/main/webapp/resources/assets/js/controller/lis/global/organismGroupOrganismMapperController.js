/**
 * @author  Suraj  Kumbhoje
 */

angular.module('myApp').controller(
		"organismGroupOrganismMapperController",
		[ '$scope','$rootScope','$state','$cookies','GenericService','PagerService','promiseFactory','$sessionStorage',
			'growl',
				function($scope,$rootScope,$state, $cookies, GenericService,PagerService,promiseFactory,$sessionStorage,growl)
				{
			       
			        $rootScope.loginpage = true;
					$scope.LISDynamicLabel = "Organism Group";
					$scope.saveBtnFlag = true;
					$scope.updateBtnFlag = false;
					$scope.antibio = [];
					
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
					$scope.antibioticList = [];
					$scope.selectedAntibioticList = [];
					
					/* code for setting Label */
					$(".selectedPageName").text("Organism Group Master");
					
					$scope.initCommon = function()
					{
						$scope.saveBtnFlag = true; 
						$scope.updateBtnFlag = false;
						$scope.common =
						{
							 "orgGroupMpprId":"",
							 "organismId":"",
							 "organismGroupId":"",
						     "status":"A",
							 "createdBy":$scope.createdBy,
							 "createdDate":"",
							 "updatedBy":$scope.updatedBy,
							 "updatedDate":"",
						     "orgId":$scope.orgId,
						     "organismName":$scope.organismName, 
						     "organismGroupName":$scope.organismGroupName 
						     
						}
					}
					$scope.initCommon();
					
					$scope.initAntibioticAdditionMaster = function(){
					$scope.antibioticAdditionMaster =
					{
						 "orgGroupMpprId":$scope.common.orgGroupMpprId,
						 "organismId":$scope.common.organismId,
						 "organismGroupId":$scope.common.organismGroupId,
					     "status":$scope.common.status,
						 "createdBy":$scope.common.createdBy,
						 "createdDate":$scope.common.createdDate,
						 "updatedBy":$scope.common.updatedBy,
						 "updatedDate":$scope.common.updatedDate,
					     "orgId":$scope.common.orgId,
					     "organismName":$scope.organismName, 
					     "organismGroupName":$scope.organismGroupName,
					     "isDeleted": "N"
					 }
					
					}
					
					// ====================================================CODE START FORFOR AGE_GROUP MASTER LIST===========================================================

					
					
					/*
					 *  Get Antibiotic Master List
					 */
					$scope.getAntibioticList = function() {
						try {
							var data = "";
							var URI = BASE_URL + ROOT_URL
							        + LIS_COMMON
									+ GET_ORGANIM_ADDTION_ORGANIM_LIST
									+ S + $scope.orgId;
							console.log("URI", URI);
							GenericService
									.serviceAction("GET", URI, data)
									.then(
											function(response) {
												$scope.antibioticList  = response.data.listObject;
											});
						} catch (e) {
							console.log(e.message)
						}

					}
					$scope.getAntibioticList();
					
					/*
					 *  Get Antibiotic Class Master List
					 */
					$scope.getAntibioticClassList = function() {
						try {
							var data = "";
							var URI = BASE_URL + ROOT_URL
							        + LIS_COMMON
									+ GET_ORGANIM_ADDTION_ORGANIM_CLASS_LIST
									+ S + $scope.orgId;;
							console.log("URI", URI);
							GenericService
									.serviceAction("GET", URI, data)
									.then(
											function(response) {
												$scope.antibioticClassList  = response.data.listObject;
											});
						} catch (e) {
							console.log(e.message)
						}

					}
					$scope.getAntibioticClassList();
					
					$scope.setNoOfRecords = function() {
						$scope.initAntibioticAdditionMasterList($scope.orgId, $scope.offset, $scope.noOfRecordsPerPage);
					};
					$scope.initAntibioticAdditionMasterList = function(orgId, offset, noOfRecordsPerPage)
					{
						$rootScope.startLoader();
						var data = "";
						offset = offset != null ? offset : 0;
						noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage : 10;
						var URI1 = BASE_URL + ROOT_URL + LIS_GLOBAL + GET_TOTAL_ORGANIM_ADDTION_ORGANIM_CLASS_LIST + S + orgId + S + offset
								+ S + noOfRecordsPerPage;
						var URI2 = BASE_URL + ROOT_URL + LIS_GLOBAL + GET_COUNT_ORGANIM_ADDTION_ORGANIM_CLASS_LIST + S + orgId;
						promiseFactory.setPromises(URI1, URI2, "GET", "GET").then(function(response) 
						{
							$rootScope.stopLoader();
							$scope.commonList = response[0].listObject;
							$scope.commonListCount = response[1].object;
							$scope.setPage(1, false);
							console.log($scope.commonList);
						});
					}

					$scope.getAntibioticAdditionMasterList = function(orgId, offset, noOfRecordsPerPage) {
						$rootScope.startLoader();
						console.log("offset", offset);
						var data = "";
						offset = offset != null ? offset : 0;
						noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage : 10;
						var URI = BASE_URL + ROOT_URL + LIS_GLOBAL + GET_ORGANIM_ADDTION_ORGANIM_CLASS_LIST + S + orgId + S + offset
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
							$scope.getAntibioticAdditionMasterList($scope.orgId, $scope.pager.startIndex, $scope.pager.pageSize);
						}
					}

					$scope.initAntibioticAdditionMasterList($scope.orgId, $scope.offset, $scope.noOfRecordsPerPage);

					// ====================================================CODE END FOR AGE_GROUP MASTER LIST===========================================================
					
					
					
					
					
					//code for Activating Inactivating Sample
					$scope.updateStatus = function(commonId,status)
					{
						try {
							$rootScope.startLoader();
							$scope.initAntibioticAdditionMaster();
							var URI = BASE_URL + ROOT_URL + LIS_GLOBAL+ACTIVE_INACTIVE_INCUBATION_ORGANIM_ADDTION_MASTER+S+$scope.orgId+S+commonId+S+status;
							console.log("ACT_INACT_URI",URI);
							GenericService.serviceAction("GET", URI, $scope.antibioticAdditionMaster).then(function(response) 
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
							var URI = BASE_URL + ROOT_URL + LIS_GLOBAL+EDIT_ORGANIM_ADDTION_MASTER+S+$scope.orgId+S+commonId;
							console.log("EDIT",URI);
							GenericService.serviceAction("GET", URI, $scope.common).then(function(response) 
							{
								console.log("data",response.data);
								
								if (response.data.status == 'success')
								{
									$rootScope.stopLoader();
									$scope.common.organismGroupId = response.data.listObject[0].organismGroupId + "";
									$scope.selectedAntibioticList = [];
									$scope.tempAntibiotics = [];
									angular.forEach($scope.antibio,function(value,index){
										$scope.antibio[index] = false;
										var i = $scope.tempSelectAnti.indexOf(value);
										$scope.tempSelectAnti.splice(i,1);
									})
									angular.forEach(response.data.listObject,function(value,index){
										var temp = {organismId:value.organismId,desc:value.organismName};
										$scope.tempSelectAnti.push(temp);
										$scope.antibio[value.organismId] = true;
										$scope.selectedAntibioticList.push(temp);
									});
									console.log("selectedAntibioticList",JSON.stringify($scope.selectedAntibioticList));
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
								$scope.initAntibioticAdditionMaster();
								$scope.listAntibioticAdditionMaster =
								{
									 "organismGroupId":$scope.common.organismGroupId,
								     "orgId":$scope.common.orgId,
								     "status":"A",
									 "createdBy":$scope.common.createdBy,
									 "createdDate":$scope.common.createdDate,
									 "updatedBy":$scope.common.updatedBy,
									 "updatedDate":$scope.common.updatedDate,
								     "selecteOrgGroupMpprList":[]
								     
								 }
								angular.forEach($scope.selectedAntibioticList,function(value,index){
										$scope.listAntibioticAdditionMaster.selecteOrgGroupMpprList.push(value.organismId);
								})
								console.log("AntibioticAdditionMaster",JSON.stringify($scope.listAntibioticAdditionMaster));
								var URI = BASE_URL + ROOT_URL + LIS_GLOBAL+ADD_ORGANIM_ADDTION_MASTER;
								GenericService.serviceAction("POST", URI, $scope.listAntibioticAdditionMaster).then(function(response) 
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
										$scope.initAntibioticAdditionMasterList($scope.orgId, $scope.offset, $scope.noOfRecordsPerPage);
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
								$scope.initAntibioticAdditionMaster();
								$scope.listAntibioticAdditionMaster =
								{
									 "organismGroupId":$scope.common.organismGroupId,
								     "orgId":$scope.common.orgId,
								     "status":"A",
									 "createdBy":$scope.common.createdBy,
									 "createdDate":$scope.common.createdDate,
									 "updatedBy":$scope.common.updatedBy,
									 "updatedDate":$scope.common.updatedDate,
								     "selecteOrgGroupMpprList":[]
								     
								 }
								angular.forEach($scope.selectedAntibioticList,function(value,index){
										$scope.listAntibioticAdditionMaster.selecteOrgGroupMpprList.push(value.organismId);
								})
								console.log("AntibioticAdditionMaster",JSON.stringify($scope.listAntibioticAdditionMaster));
							
								var URI = BASE_URL + ROOT_URL + LIS_GLOBAL+UPDATE_ORGANIM_ADDTION_MASTER;
							    GenericService.serviceAction("PUT", URI, $scope.listAntibioticAdditionMaster).then(function(response) 
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
										$scope.initAntibioticAdditionMasterList($scope.orgId, $scope.offset, $scope.noOfRecordsPerPage);
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
					
					
					$scope.tempSelectAnti = [];
					$scope.addTempAntibiotic = function(organismId,desc){
						
						var flag = false;
						angular.forEach($scope.tempSelectAnti,function(value,index){
							if(value.organismId == organismId){
								$scope.tempSelectAnti.splice(index,1);
								$scope.antibio[value.organismId] = false;
								flag = true;
							}
						})
						if(!flag){
							var temp = {organismId:organismId,desc:desc};
							$scope.tempSelectAnti.push(temp);
						}
					}
					
					$scope.selectAntibiotic = function()
					{
						$scope.selectedAntibioticList = angular.copy($scope.tempSelectAnti);
					}
					
					
					
					/*
					 *  Get Antibiotics List By Antibiotic Class Id
					 */
					$scope.getAntibioticListByAntibioticClassId = function(organismGroupId) {
						try {
							var data = "";
							var URI = BASE_URL + ROOT_URL
							        + LIS_GLOBAL
									+ GET_ORGANIMS_LIST_BY_ORGANIM_CLASS_ID
									+ SLASH
									+ organismGroupId;
							console.log("URI", URI);
							GenericService
									.serviceAction("GET", URI, data)
									.then(
											function(response) {
												$scope.antibioticsListByAntibioticsClassId  = response.data.listObject;
												console.log("antibioticsListByAntibioticsClassId",JSON.stringify($scope.antibioticsListByAntibioticsClassId));
											});
						} catch (e) {
							console.log(e.message)
						}

					}
					
					//$scope.getAntibioticListByAntibioticClassId(2);
					
					/**=============================>Errors Messages  <=======================================**/
					
					$scope.plzSelectAntibioticClass= 'Please Select Antibiotic Class.';
					
				} ]);