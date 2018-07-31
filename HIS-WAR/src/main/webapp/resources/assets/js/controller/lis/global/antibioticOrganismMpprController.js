/**
 * @author  Suraj  Kumbhoje
 */

angular.module('myApp').controller(
		"antibioticOrganismMpprController",
		[ '$scope','$rootScope','$state','$cookies','GenericService','PagerService','promiseFactory','$sessionStorage',
			'growl',
				function($scope,$rootScope,$state, $cookies, GenericService,PagerService,promiseFactory,$sessionStorage,growl)
				{
			       
			        $rootScope.loginpage = true; 
					$scope.LISDynamicLabel = "antibiotic Organism Mapprer";
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
					$scope.antibioticGroupList = [];
					$scope.getAntibioticByAntibioticGroupIdList =[];
					$scope.selectedAntibioticList = [];
					
					/* code for setting Label */
					$(".selectedPageName").text("antibiotic Organism Mapprer Master");
					
					$scope.initCommon = function()
					{
						$scope.saveBtnFlag = true; 
						$scope.updateBtnFlag = false;
						$scope.common =
						{
							 "antiboiticOrganismMpprId":"",
							 "antiboiticId":"",
							 "organismId":"",
						     "status":"A",
							 "createdBy":$scope.createdBy,
							 "createdDate":"",
							 "updatedBy":$scope.updatedBy,
							 "updatedDate":"",
						     "orgId":$scope.orgId,
						     "antibioticName":$scope.antibioticName, 
						     "antibioticClassDesc":$scope.antibioticClassDesc 
						     
						}
					}
					$scope.initCommon();
					
					$scope.initAntibioticAdditionMaster = function(){
					$scope.antibioticAdditionMaster =
					{
						 "antiboiticOrganismMpprId":$scope.common.antiboiticOrganismMpprId,
						 "antiboiticId":$scope.common.antiboiticId,
						 "organismId":$scope.common.organismId,
					     "status":$scope.common.status,
						 "createdBy":$scope.common.createdBy,
						 "createdDate":$scope.common.createdDate,
						 "updatedBy":$scope.common.updatedBy,
						 "updatedDate":$scope.common.updatedDate,
					     "orgId":$scope.common.orgId,
					     "antibioticName":$scope.antibioticName, 
					     "antibioticClassDesc":$scope.antibioticClassDesc,
					     "isDeleted": "N"
					 }
					
					}
					
					// ====================================================CODE START FORFOR AGE_GROUP MASTER LIST===========================================================

					
					
					/*
					 *  Get Antibiotic Group Master List
					 */
					$scope.getAntibioticGroupList = function() {
						try {
							var data = "";
							var URI = BASE_URL + ROOT_URL
							        + LIS_COMMON
									+ GET_ANTIBIOTIC_GROUP_LIST
									+ S
									+ $scope.orgId;
							console.log("URI", URI);
							GenericService
									.serviceAction("GET", URI, data)
									.then(
											function(response) {
												$scope.antibioticGroupList  = response.data.listObject;
												console.log("antibioticGroupList",JSON.stringify($scope.antibioticGroupList));
											});
						} catch (e) {
							console.log(e.message)
						}

					}
					$scope.getAntibioticGroupList();
					
					
					
					/*
					 *  Get Antibiotic By  Antibiotic Group Id List
					 */
					$scope.getAntibioticByAntibioticGroupIdList = function(antibioticGroupId) {
						try {
							alert(""+antibioticGroupId);
							var data = "";
							var URI = BASE_URL + ROOT_URL
							        + LIS_COMMON
									+ GET_ANTIBIOTIC_LIST_BY_ANTIBIOTIC_GROUP_ID
									+ S +"antibioticGroupId"+ S
									+ antibioticGroupId
									+ S +"orgId"+ S
									+ $scope.orgId;
							console.log("URI", URI);
							GenericService
									.serviceAction("GET", URI, data)
									.then(
											function(response) {
												$scope.getAntibioticByAntibioticGroupIdList  = response.data.listObject;
												console.log("getAntibioticByAntibioticGroupIdList",JSON.stringify($scope.getAntibioticByAntibioticGroupIdList));
											});
						} catch (e) {
							console.log(e.message)
						}

					}
					
					
					/*
					 *  Get OrganismList Master List
					 */
					$scope.getOrganismListList = function() {
						try {
							var data = "";
							var URI = BASE_URL + ROOT_URL
							        + LIS_COMMON
									+ GET_ORGANISM_LIST 
									+ S
									+ $scope.orgId;
							console.log("URI", URI);
							GenericService
									.serviceAction("GET", URI, data)
									.then(
											function(response) {
												$scope.organismList  = response.data.listObject;
											});
						} catch (e) {
							console.log(e.message)
						}

					}
					$scope.getOrganismListList();
					
					$scope.setNoOfRecords = function() {
						$scope.initAntibioticAdditionMasterList($scope.orgId, $scope.offset, $scope.noOfRecordsPerPage);
					};
					$scope.initAntibioticAdditionMasterList = function(orgId, offset, noOfRecordsPerPage)
					{
						$rootScope.startLoader();
						var data = "";
						offset = offset != null ? offset : 0;
						noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage : 10;
						var URI1 = BASE_URL + ROOT_URL + LIS_GLOBAL + GET_TOTAL_ANTIBIOTIC_ORGANISM_LIST_MAPPER_MASTER + S + orgId + S + offset
								+ S + noOfRecordsPerPage;
						var URI2 = BASE_URL + ROOT_URL + LIS_GLOBAL + GET_COUNT_ANTIBIOTIC_ORGANISM_LIST_MAPPER_MASTER_LIST + S + orgId;
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
						var URI = BASE_URL + ROOT_URL + LIS_GLOBAL + GET_ANTIBIOTIC_ADDTION_ANTIBIOTIC_CLASS_LIST + S + orgId + S + offset
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
							var URI = BASE_URL + ROOT_URL + LIS_GLOBAL+ACTIVE_INACTIVE_ANTIBIOTIC_ORGANISM_LIST_MAPPER_MASTER+S+$scope.orgId+S+commonId+S+status;
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
					
					
					$scope.showUpdateBtn = function(commonId)
					{
						try {
							$rootScope.startLoader();
							$scope.saveBtnFlag = false;
							$scope.updateBtnFlag = true;
							var URI = BASE_URL + ROOT_URL + LIS_GLOBAL+EDIT_ANTIBIOTIC_ADDTION_MASTER+S+$scope.orgId+S+commonId;
							console.log("EDIT",URI);
							GenericService.serviceAction("GET", URI, $scope.common).then(function(response) 
							{
								console.log("data",response.data);
								
								if (response.data.status == 'success')
								{
									$rootScope.stopLoader();
									$scope.common.antiboiticClassId = response.data.listObject[0].antiboiticClassId + "";
									$scope.selectedAntibioticList = [];
									$scope.tempAntibiotics = [];
									angular.forEach($scope.antibio,function(value,index){
										$scope.antibio[index] = false;
										var i = $scope.tempSelectAnti.indexOf(value);
										$scope.tempSelectAnti.splice(i,1);
									})
									angular.forEach(response.data.listObject,function(value,index){
										var temp = {antibioticId:value.antibioticId,desc:value.antibioticName};
										$scope.tempSelectAnti.push(temp);
										$scope.antibio[value.antibioticId] = true;
										$scope.selectedAntibioticList.push(temp);
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
						if ($scope.commonformss.$valid)
						{
							try {
								$rootScope.startLoader();
								$scope.initAntibioticAdditionMaster();
								$scope.listAntibioticAdditionMaster =
								{
									 "organismId":$scope.common.organismId,
								     "orgId":$scope.common.orgId,
								     "status":"A",
									 "createdBy":$scope.common.createdBy,
									 "createdDate":$scope.common.createdDate,
									 "updatedBy":$scope.common.updatedBy,
									 "updatedDate":$scope.common.updatedDate,
								     "selectAntiboiticOrganismMppList":[]
								     
								 }
								angular.forEach($scope.finalAntibiotic,function(value,index){
										$scope.listAntibioticAdditionMaster.selectAntiboiticOrganismMppList.push(value.antibioticId);
								})
								console.log("AntibioticAdditionMaster",JSON.stringify($scope.listAntibioticAdditionMaster));
								var URI = BASE_URL + ROOT_URL + LIS_GLOBAL+ADD_ANTIBIOTIC_ORGANISM_LIST_MAPPER_MASTER;
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
									 "antiboiticClassId":$scope.common.antiboiticClassId,
								     "orgId":$scope.common.orgId,
								     "status":"A",
									 "createdBy":$scope.common.createdBy,
									 "createdDate":$scope.common.createdDate,
									 "updatedBy":$scope.common.updatedBy,
									 "updatedDate":$scope.common.updatedDate,
								     "selectAntiboiticOrganismMppList":[]
								     
								 }
								angular.forEach($scope.selectedAntibioticList,function(value,index){
                                         
										$scope.listAntibioticAdditionMaster.selectAntiboiticOrganismMppList.push(value.antibioticId);
								})
								console.log("AntibioticAdditionMaster",JSON.stringify($scope.listAntibioticAdditionMaster));
							
								var URI = BASE_URL + ROOT_URL + LIS_GLOBAL+UPDATE_ANTIBIOTIC_ADDTION_MASTER;
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
					$scope.finalAntibiotic = [];
					$scope.addTempAntibiotic = function(antibioticId,desc){
						$scope.finalAntibiotic = [];
						var flag = false;
						angular.forEach($scope.tempSelectAnti,function(value,index){
							if(value.antibioticId == antibioticId){
								$scope.tempSelectAnti.splice(index,1);
								$scope.antibio[value.antibioticId] = false;
								flag = true;
							}
						})
						if(!flag){
							var temp = {antibioticId:antibioticId,desc:desc};
							$scope.tempSelectAnti.push(temp);
							
						}
						
						
						$scope.selectedAntibioticList = angular.copy($scope.tempSelectAnti);
						$scope.listAntibioticMaster =
						{
						     "orgId":$scope.common.orgId,
						     "selectAntibioticList":[]
						     
						 }
						
						
						angular.forEach($scope.selectedAntibioticList,function(value,index){
							$scope.listAntibioticMaster.selectAntibioticList.push(value.antibioticId);
				     	})
				     	
				    	console.log("selectedAntibioticList",JSON.stringify($scope.selectedAntibioticList));
				     	if($scope.selectedAntibioticList.length == 0){
				     		$scope.listAntibioticMaster.selectAntibioticList.push(0);
				     	}
					
						try {
								$rootScope.startLoader();
								
								console.log("listAntibioticMaster",JSON.stringify($scope.listAntibioticMaster));
								var URI = BASE_URL + ROOT_URL + LIS_COMMON + GET_ANTIBIOTIC_LIST_BY_ANTIBIOTIC_GROUP_ID;
								GenericService.serviceAction("POST", URI, $scope.listAntibioticMaster).then(function(response) 
								{	
									if (response.data.status == 'success')
									{
										$rootScope.stopLoader();
										growl.success(response.data.message,
												{
													title : 'Success!'
												});
										$scope.$broadcast('show-errors-reset');
										$scope.antibioticSelectedList  = response.data.listObject;
										console.log("antibioticSelectedList",JSON.stringify($scope.antibioticSelectedList ));
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
					
					$scope.selectAntibiotic = function()
					{
						$scope.selectedAntibioticList = angular.copy($scope.tempSelectAnti);
						$scope.listAntibioticMaster =
						{
						     "orgId":$scope.common.orgId,
						     "selectAntibioticList":[]
						     
						 }
						angular.forEach($scope.selectedAntibioticList,function(value,index){
							$scope.listAntibioticMaster.selectAntibioticList.push(value.antibioticId);
				     	})
					
						try {
								$rootScope.startLoader();
								
								console.log("listAntibioticMaster",JSON.stringify($scope.listAntibioticMaster));
								var URI = BASE_URL + ROOT_URL + LIS_COMMON + GET_ANTIBIOTIC_LIST_BY_ANTIBIOTIC_GROUP_ID;
								GenericService.serviceAction("POST", URI, $scope.listAntibioticMaster).then(function(response) 
								{	
									if (response.data.status == 'success')
									{
										$rootScope.stopLoader();
										growl.success(response.data.message,
												{
													title : 'Success!'
												});
										$scope.$broadcast('show-errors-reset');
										$scope.antibioticSelectedList  = response.data.listObject;
										console.log("antibioticSelectedList",JSON.stringify($scope.antibioticSelectedList ));
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
					
				
					
					/*
					 *  Get Antibiotics List By Organism Id
					 */
					$scope.antibioticsListByorganismId = function(organismId) {
						try {
							var data = "";
							var URI = BASE_URL + ROOT_URL
							        + LIS_GLOBAL
									+ GET_ANTIBIOTICS_LIST_BY_ORGANISM_ID
									+ SLASH
									+ organismId;
							console.log("URI", URI);
							GenericService
									.serviceAction("GET", URI, data)
									.then(
											function(response) {
												$scope.antibioticsListByorganismId  = response.data.listObject;
												console.log("antibioticsListByorganismId",JSON.stringify($scope.antibioticsListByorganismId));
											});
						} catch (e) {
							console.log(e.message)
						}
						

					}
					
					$scope.addFinalAnti = function(selectedAntibiotic,flag){
						var temp = {antibioticId:selectedAntibiotic.id,antibioticName:selectedAntibiotic.name};
						var index = $scope.finalAntibiotic.findIndex(item => {return item.antibioticId == selectedAntibiotic.id});
						if(index == -1){
							$scope.finalAntibiotic.push(temp);
						}else{
							$scope.finalAntibiotic.splice(index,1);
						}
						
					}
					
					
					/**=============================>Errors Messages  <=======================================**/
					
					$scope.plzSelectAntibioticClass= 'Please Select Antibiotic Class.';
					
				} ]);