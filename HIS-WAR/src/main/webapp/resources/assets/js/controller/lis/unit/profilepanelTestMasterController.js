/**
 * @author  Suraj  Kumbhoje
 */

angular.module('myApp').controller(
		"profilepanelTestMasterController",
		[ '$scope','$rootScope','$state','$cookies','GenericService','PagerService','promiseFactory','$sessionStorage',
			'growl',
				function($scope,$rootScope,$state, $cookies, GenericService,PagerService,promiseFactory,$sessionStorage,growl)
				{
			       
			        $rootScope.loginpage = true;
					$scope.LISDynamicLabel = "Profile Panel Test Mastern";
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
					$scope.orgUnitId = 1;
					$scope.createdBy =1 ;
					$scope.updatedBy =1 ;
					$scope.testMasterList = [];
					$scope.selectedTestMasterList = [];
					/* code for setting Label */
					$(".selectedPageName").text("Profile Panel Test Mastern");
					
					
					$scope.initTpanelTestMpprMaster = function(){
					$scope.tPanelMaster =
					{
							
								"serviceId":"",
								"orgId":$scope.orgId,
								"orgUnitId":$scope.orgUnitId,
								"panelAlies":"",
								"panelCode":"",
								"status":"A",
								"isDeleted":"N",		
								"createdBy":$scope.createdBy,
								"createdDate":"",
								"updatedBy":$scope.updatedBy,
								"updatedDate":"",
						        "listTPanelDetailsMaster":[]
					 }
					
					}
					$scope.initTpanelTestMpprMaster();
					$scope.tPanelDetailsMasterList=[]
					$scope.tPanelDetailsMaster =
					{
							"panelId":"",
						    "panelDetailsId":"",
						    "serviceId":$scope.serviceId,
							"testId":"1",
							"headerId":"1",
							"orgId":$scope.orgId,
							"orgUnitId":$scope.orgUnitId,
							"status":"A",
							"isDeleted":"N",		
							"createdBy":$scope.createdBy,
							"createdDate":"",
							"printOrder":"",
							"updatedBy":$scope.updatedBy,
							"updatedDate":""
							
					 }
					
					// ====================================================CODE START FORFOR AGE_GROUP MASTER LIST===========================================================

					
					
					/*
					 *  Get Header List
					 */
					$scope.getHeaderList = function() {
						try {
							var data = "";
							var URI = BASE_URL + ROOT_URL
							        + LIS_COMMON
									+ GET_HEADER_LIST
									+ S + $scope.orgId + S + $scope.orgUnitId;
							console.log("URI", URI);
							GenericService
									.serviceAction("GET", URI, data)
									.then(
											function(response) {
												$scope.headerList  = response.data.listObject;
											});
						} catch (e) {
							console.log(e.message)
						}

					}
					$scope.getHeaderList();
					
						$scope.getTestMasterList = function()
					{
						try 
						{
							var data = {
									"orgId":$scope.orgId,
									"orgUnitId":$scope.orgUnitId,
									"deptId":$scope.deptId,
									"subDeptId":$scope.subDeptId,
									"searchKeyword":"",
							}
							
							var URI = BASE_URL 
							        + ROOT_URL
									+ LIS_HISTOPATHOLOGY
									+ HISTO_MICRO_TEST_LIST
							console.log("URI", URI);
							 GenericService.serviceAction("POST", URI,
									data).then(function(response) {
								console.log("response.data.listObject",response.data.listObject);
								$scope.testMasterList =  response.data.listObject;
							});
						} catch (e) {
							console.log(e.message)
						}
					}
					$scope.getTestMasterList();
					
					/*
					 *  Get Unit Service Mapper List
					 */
					$scope.getUnitServiceMapperList = function() {
						try {
							var data = "";
							var URI = BASE_URL + ROOT_URL
							        + LIS_UNIT
									+ GET_SERVICE_PANEL_TEST
									+ S + $scope.orgId + S + $scope.orgUnitId;
							console.log("URI", URI);
							GenericService
									.serviceAction("GET", URI, data)
									.then(
											function(response) {
												$scope.unitServiceMapperList  = response.data.listObject;
											});
						} catch (e) {
							console.log(e.message)
						}

					}
					$scope.getUnitServiceMapperList();
					
					$scope.setNoOfRecords = function() {
						$scope.initProfilePanelTestMasterList($scope.orgId, $scope.orgUnitId, $scope.offset, $scope.noOfRecordsPerPage);
					};
					$scope.initProfilePanelTestMasterList = function(orgId, orgUnitId, offset, noOfRecordsPerPage)
					{
						$rootScope.startLoader();
						var data = "";
						offset = offset != null ? offset : 0;
						noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage : 10;
						var URI1 = BASE_URL + ROOT_URL + LIS_UNIT + LIST_PRIOFILE_PANEL_MASTER + S + orgId + S + orgUnitId + S + offset
								+ S + noOfRecordsPerPage;
						var URI2 = BASE_URL + ROOT_URL + LIS_UNIT + COUNT_PRIOFILE_PANEL_MASTER + S + orgId + S + orgUnitId;
						promiseFactory.setPromises(URI1, URI2, "GET", "GET").then(function(response) 
						{
							$rootScope.stopLoader();
							$scope.commonList = response[0].listObject;
							$scope.commonListCount = response[1].object;
							$scope.setPage(1, false);
							console.log($scope.commonList);
						});
					}

					$scope.getProfilePanelTestMasterList = function(orgId, offset, noOfRecordsPerPage) {
						$rootScope.startLoader();
						console.log("offset", offset);
						var data = "";
						offset = offset != null ? offset : 0;
						noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage : 10;
						var URI = BASE_URL + ROOT_URL + LIS_UNIT + LIST_PRIOFILE_PANEL_MASTER + S + orgId + S + orgUnitId + offset
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
							$scope.getProfilePanelTestMasterList($scope.orgId, $scope.pager.startIndex, $scope.pager.pageSize);
						}
					}

					$scope.initProfilePanelTestMasterList($scope.orgId, $scope.orgUnitId, $scope.offset, $scope.noOfRecordsPerPage);

					// ====================================================CODE END FOR AGE_GROUP MASTER LIST===========================================================
					
					
					
					
					
					//code for Activating Inactivating ProfilePanelTest
					$scope.updateStatus = function(serviceId,status)
					{
						try {
							$rootScope.startLoader();
							var URI = BASE_URL + ROOT_URL + LIS_UNIT+ACTIVE_INACTIVE_PRIOFILE_PANEL_MASTER+S+serviceId+S+$scope.orgId+S+$scope.orgUnitId+S+status;
							console.log("ACT_INACT_URI",URI);
							GenericService.serviceAction("GET", URI, $scope.tPanelMaster).then(function(response) 
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
					
					
					$scope.save = function() 
					{
						$scope.$broadcast('show-errors-check-validity');
						if ($scope.profilePanelform.$valid)
						{
							try {
								$rootScope.startLoader();
								$scope.$broadcast('show-errors-reset');
								$scope.tPanelDetailsMasterList=[]
									
								angular.forEach($scope.headerObjList,function(headerObj,index){
									
										angular.forEach(headerObj.testObjList,function(testObj,index){
											
											var tPanelDetailsMaster=angular.copy($scope.tPanelDetailsMaster);
												tPanelDetailsMaster.serviceId=$scope.tPanelMaster.serviceId,
												tPanelDetailsMaster.testId=testObj.testId,
												tPanelDetailsMaster.headerId=headerObj.headerId,
												tPanelDetailsMaster.orgId=$scope.orgId,
												tPanelDetailsMaster.orgUnitId=$scope.orgUnitId,
												tPanelDetailsMaster.status="A",
												tPanelDetailsMaster.isDeleted="A",
												tPanelDetailsMaster.printOrder=index+1;
												tPanelDetailsMaster.createdBy=$scope.createdBy,
												tPanelDetailsMaster.updatedBy=$scope.updatedBy
												$scope.tPanelDetailsMasterList.push(tPanelDetailsMaster);
												
												
									})
									
									
								})
								$scope.tPanelMaster.listTPanelDetailsMaster=$scope.tPanelDetailsMasterList;
								console.log("tPanelMaster",JSON.stringify($scope.tPanelMaster));
								var URI = BASE_URL + ROOT_URL + LIS_UNIT+ADD_PANEL_MASTER;
								GenericService.serviceAction("POST", URI, $scope.tPanelMaster).then(function(response) 
								{	
									if (response.data.status == 'success')
									{
										$rootScope.stopLoader();
										growl.success(response.data.message,
												{
													title : 'Success!'
												});
										$scope.$broadcast('show-errors-reset');
										$scope.initProfilePanelTestMasterList($scope.orgId,$scope.orgUnitId, $scope.offset, $scope.noOfRecordsPerPage);
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
					

				
					$scope.showUpdateBtn = function(panelId)

					{
						try {
							$rootScope.startLoader();
							$scope.saveBtnFlag = false;
							$scope.updateBtnFlag = true;
							$scope.headerObjList=[];
							var URI = BASE_URL + ROOT_URL + LIS_UNIT + EDIT_SERVICE_PANEL_MASTER +S+panelId+S+$scope.orgId+S+$scope.orgUnitId;

							console.log("EDIT",URI);
							GenericService.serviceAction("GET", URI, $scope.tPanelMaster).then(function(response) 
							{
								console.log("data",response.data);
								
								if (response.data.status == 'success')
								{
									
									$rootScope.stopLoader();
									$scope.tPanelMaster = response.data.object;
									$scope.tPanelMaster.serviceId = response.data.object.serviceId + "";
									$scope.headerId= "";

									angular.forEach(response.data.object.listTPanelDetailsMaster,function(value,index){
									
										
										$scope.testObjList = [];
										if($scope.tempHederId==value.headerId)
											{
											  var testObj = angular.copy($scope.testObj);
											  $scope.selectTest(value.testId);
											  testObj.testId=$scope.testObject[0].id;
											  testObj.label=$scope.testObject[0].label;
											  testObj.headerId = $scope.headerObject[0].id;
											  $scope.testObjList.push(testObj);
											  var headerindex = $scope.headerObjList.findIndex(x=>x.headerId==value.headerId);
											  $scope.headerObjList[headerindex].testObjList = $scope.headerObjList[headerindex].testObjList.concat($scope.testObjList);
											}
										else{
											
											$scope.tempHederId = value.headerId;
											var headerObj = angular.copy($scope.headerObj);
											$scope.selectHeader(value.headerId);
											headerObj.headerId=$scope.headerObject[0].id;
											headerObj.label=$scope.headerObject[0].name;
											
											var testObj = angular.copy($scope.testObj);
											$scope.selectTest(value.testId);
											testObj.testId=$scope.testObject[0].id;
											testObj.label=$scope.testObject[0].label;
											testObj.headerId = $scope.headerObject[0].id;
											$scope.testObjList.push(testObj);
											headerObj.testObjList = $scope.testObjList;
											$scope.headerObjList.push(headerObj);
										}
									});
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
					

				

					$scope.update = function() 
					{
						$scope.$broadcast('show-errors-check-validity');
					/*	if ($scope.profilePanelform.$valid)
						{*/
							try {
								$rootScope.startLoader();
								
								$scope.tPanelDetailsMasterList=[]
								
								angular.forEach($scope.headerObjList,function(headerObj,index){
									
										angular.forEach(headerObj.testObjList,function(testObj,index){
											
											var tPanelDetailsMaster=angular.copy($scope.tPanelDetailsMaster);
												tPanelDetailsMaster.serviceId=$scope.tPanelMaster.serviceId,
												tPanelDetailsMaster.testId=testObj.testId,
												tPanelDetailsMaster.headerId=headerObj.headerId,
												tPanelDetailsMaster.orgId=$scope.orgId,
												tPanelDetailsMaster.orgUnitId=$scope.orgUnitId,
												tPanelDetailsMaster.status="A",
												tPanelDetailsMaster.isDeleted="N",
												tPanelDetailsMaster.printOrder=index+1;
												tPanelDetailsMaster.createdBy=$scope.createdBy,
												tPanelDetailsMaster.updatedBy=$scope.updatedBy
												$scope.tPanelDetailsMasterList.push(tPanelDetailsMaster);
												
												
									})
									
								})
								$scope.tPanelMaster.listTPanelDetailsMaster=$scope.tPanelDetailsMasterList;
								console.log("tPanelMaster",JSON.stringify($scope.tPanelMaster));
								var URI = BASE_URL + ROOT_URL + LIS_UNIT + UPDATE_SERVICE_PANEL_MASTER ;
							  GenericService.serviceAction("PUT", URI,$scope.tPanelMaster).then(function(response) 
								{
									if (response.data.status == 'success')
									{
										$rootScope.startLoader();
										growl.success(response.data.message,
												{
													title : 'Success!'
												});
										$scope.$broadcast('show-errors-reset');
										$scope.initProfilePanelTestMasterList($scope.orgId, $scope.orgUnitId, $scope.offset, $scope.noOfRecordsPerPage);
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
							
						/*}*/
					}
					
					
					$scope.tempSelectTest = [];
					$scope.addTempAntibiotic = function(testId,label){
						var flag = false;
						angular.forEach($scope.tempSelectTest,function(value,index){
							if(value.testId == testId){
								$scope.tempSelectTest.splice(index,1);
								$scope.antibio[value.testId] = false;
								flag = true;
							}
						})
						if(!flag){
							var temp = {testId:testId,label:label};
							$scope.tempSelectTest.push(temp);
						}
					}
					$scope.selectHeader = function(headerId) {
						
						$scope.headerObject = $scope.headerList
								.filter(function(headerMaster) {
									if (headerMaster.id == headerId) {
										return headerMaster;
									}
								});
					}
					
					$scope.selectTest = function(testId) {
						
						$scope.testObject = $scope.testMasterList
								.filter(function(testMaster) {
									if (testMaster.id == testId) {
										return testMaster;
									}
								});
					}
					
					
					

					
					$scope.headerObjList=[];
					$scope.testObjList=[];
					$scope.headerObj={"headerId":"","label":"","testObjList":$scope.testObjList}
					$scope.testObj={"headerId":"","testId":"","label":""}

					var isExists;
					function checkExistsOrNot(arr, val) {
						return arr
								.some(function(arr) {
									return (val.id === arr.headerId);
								});
					}
					
					$scope.headerObjList=[];
					$scope.testObjList=[];
					$scope.headerObj={"headerId":"","label":"","testObjList":[]}
					$scope.testObj={"headerId":"","testId":"","label":"","printOrder":""}

					
					$scope.selectAntibiotic = function()
					{
						$scope.testObjList=[];
						var headerObj = angular.copy($scope.headerObj);
						headerObj.headerId=$scope.headerObject[0].id;
						headerObj.label=$scope.headerObject[0].name;
						
						angular.forEach($scope.tempSelectTest,function(value,index){
							var testObj = angular.copy($scope.testObj);
							testObj.testId=value.testId;
							testObj.label=value.label;
							testObj.headerId=headerObj.headerId;
							testObj.printOrder=index+1;
							$scope.testObjList.push(testObj);
						})
						headerObj.testObjList=$scope.testObjList;

						isExists = checkExistsOrNot(
								$scope.headerObjList,
								$scope.headerObject[0]);
						
						if (!isExists) {
							$scope.headerObjList.push(headerObj);
						} else {
							var isTestExists;
							function checkTestsExistsOrNot(arr, val) {
								return arr
										.some(function(arr) {
											return (val.testId === arr.testId);
										});
							}
							
							$scope.testObjList=[];
							var headerindex = $scope.headerObjList.findIndex(x=>x.headerId==headerObj.headerId);
							
							angular.forEach($scope.tempSelectTest,function(value,index){
								var testObj = angular.copy($scope.testObj);
								testObj.testId=value.testId;
								testObj.label=value.label;
								testObj.headerId=headerObj.headerId;
								isTestExists=checkTestsExistsOrNot($scope.headerObjList[headerindex].testObjList,testObj)
								testObj.printOrder=index+1;
								if (!isTestExists) {
									$scope.testObjList.push(testObj);
								} else{
									growl.error("Test is already Added .",
											{
												title : 'Error!'
											});
								}
								
							})
							
							$scope.headerObjList[headerindex].testObjList =$scope.headerObjList[headerindex].testObjList.concat($scope.testObjList);
							/*growl.error("Header is already Added .",
									{
										title : 'Error!'
									});*/
						}
						
						console.log("headerObjList",JSON.stringify($scope.headerObjList));
						
						angular.forEach($scope.tempSelectTest,function(value,index){
							$scope.antibio[value.testId] = false;
						})
						
						$scope.tempSelectTest = [];
					}
					
					$scope.deleteSelectedHeader= function(index){
						$scope.headerObjList.splice(index,1);
					}
					$scope.deleteSelectedHeaderTest= function(header,index){
						$scope.headerObjList[header].testObjList.splice(index,1);
							if($scope.headerObjList[header].testObjList.length==0){
								$scope.deleteSelectedHeader(header);
							}
					}
					
					
					
					var move = function (origin, destination) {
						if($scope.headerObjList[$scope.headerIndex].testObjList.length>0)
						{
							var temp =$scope.headerObjList[$scope.headerIndex].testObjList[destination];
							///var printOrder=$scope.headerObjList[$scope.headerIndex].testObjList[destination].printOrder;
					        $scope.headerObjList[$scope.headerIndex].testObjList[destination] = $scope.headerObjList[$scope.headerIndex].testObjList[origin];
					       // $scope.headerObjList[$scope.headerIndex].testObjList[destination].printOrder=$scope.headerObjList[$scope.headerIndex].testObjList[origin].printOrder;
					        $scope.headerObjList[$scope.headerIndex].testObjList[origin] = temp;
					       // $scope.headerObjList[$scope.headerIndex].testObjList[origin].printOrder=printOrder;
						}
						};

						$scope.moveUp = function(){
						  var tempIndex=$scope.getIndex();
						   if(tempIndex!=0){
							   move($scope.index, $scope.index - 1);
						   }
							
						};

						$scope.moveDown = function(){	
						 var tempIndex=  $scope.getIndex();
						 if($scope.headerObjList[$scope.headerIndex].testObjList.length-1!=tempIndex)
							move($scope.index, $scope.index + 1);
						//$scope.printOrder=$scope.index + 1;
						};
						
						$scope.selectValue= function(headerId,id)
						{
						  //alert(id);
						   $scope.id = id;
						   $scope.headerIndex=headerId;
						   
						}
						
						$scope.getIndex = function()
						{
						  
						     	 $scope.index = $scope.headerObjList[$scope.headerIndex].testObjList.findIndex(x=>x.testId==$scope.id);
						     	 return $scope.index;
						}		
						

					
			
					/**=============================>Errors Messages  <=======================================**/
					
					$scope.plzSelectAntibioticClass= 'Please Select Antibiotic Class.';
					
				} ]);