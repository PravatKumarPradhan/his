'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:organizationController
 * @description #organizationController Controller of the myApp
 */
angular.module('myApp').controller(
		'organizationController',
		[
				'$scope',
				'$http',
				'$localStorage',
				'$sessionStorage',
				'$cookies',
				'$rootScope',
				'fileReader',
				'GenericService',
				'PagerService',
				'promiseFactory',
				'growl',
				function($scope, $http, $localStorage, $sessionStorage,
						$cookies, $rootScope,fileReader,GenericService,PagerService,promiseFactory,growl) {
					$rootScope.loginpage = true;
					
					$scope.imageSrc = "";
				    
				    $scope.$on("fileProgress", function(e, progress) {
				      $scope.progress = progress.loaded / progress.total;
				    });
				    
				    
				    $scope.dateOptions = {
							formatYear : 'yyyy',
							showWeeks : false
						};
					$scope.openDatePicker = function() {
						$scope.datepicker.opened = true;
					};

					$scope.datepicker = {
						opened : false
					};
				    
				    //Declaration
				    $scope.licenceTypeList;
				    
				    $rootScope.loginpage = true;
					$scope.LISDynamicLabel = "";
					$scope.saveBtnFlag = true;
					$scope.updateBtnFlag = false;
					$scope.updateScope = false;
					
				    $scope.orgId = 1;
				    $scope.listLicenceDetailsMaster = [];
				    
				   
				    /**Variables for pagination */
					$scope.offset = 0;
					$scope.noOfRecordsPerPage = 10;
					$scope.organizationList = [];
					$scope.totalNoOfRecords;
					$scope.searchKeyword;
					$scope.organizationCount;
					$scope.createdBy =1 ;
					$scope.updatedBy =1 ;
				    
				    
				    $scope.addLicenceDetails = function(){
				    	console.log($scope.licenceDetailsMaster);
				    	if($scope.updateScope ==false)
	        	        {
				    	  var licenceDetailsMaster = angular.copy($scope.licenceDetailsMaster);
						  $scope.listLicenceDetailsMaster.push(licenceDetailsMaster);
						  console.log($scope.listLicenceDetailsMaster);
	        	       }
				      else if($scope.updateScope ==true)
			        	{
				    	  var licenceDetailsMaster = angular.copy($scope.licenceDetailsMaster);
				    	  licenceDetailsMaster.organizationId = $scope.licenceDetailsMaster.organizationId;
						  $scope.listLicenceDetailsMaster.push(licenceDetailsMaster);
						  $scope.listLicenceDetailsMaster.splice(deleteIndex, 1);
						  console.log($scope.listLicenceDetailsMaster);
			        	}	
				    }
				    
				    
				    
				    $scope.removeLicenceDetails = function(deleteIndex)
					{
						$scope.listLicenceDetailsMaster.splice(deleteIndex, 1);
				     }
				    
				    
				    $scope.initOrganizationMaster = function(){
				
			        	$scope.licenceDetailsMaster={
			        			"licenceTypeId":"",
			        			"licenceNumber":"",
			        			"expiryDate":""
			        	}
			        	
					$scope.organization =
					{
						 "organizationId":"",
						 "organizationCode":"",
						 "organizationName":"",
						 "status":"A",
						 "createdBy":"",
						 "createdDate":$scope.createdDate,
						 "updatedBy":"",
						 "updatedDate":$scope.updatedDate,
					     "organizationAddress":"",
					     "countryId":"",
					     "stateId":"",
					     "cityId":"",
					     "postCode":"",
					     "organizationContact":"",
					    // "organizationFaxNo":"",
					     "organizationEmailId":"",
					     "organizationLogo":"",
					     "listOrganizationUnitLicenceDetailsDto":""
					 }
			        	
			        	$scope.listLicenceDetailsMaster=[];
						var licenceDetailsMaster = angular.copy($scope.initOrganizationMaster);
			        	$scope.listLicenceDetailsMaster.push(licenceDetailsMaster);
				
					}
				    
				    $scope.initOrganizationMaster();
				    
				    //get Licence Type list
				    $scope.getLicenceTypeList = function(orgId) {
						try {
							var data = "";
							var URI = BASE_URL + ROOT_URL
									+ "/org/getLiceneceTypeMasterList" + S
									+ orgId;
							console.log("URI", URI);
							GenericService
									.serviceAction("GET", URI, data)
									.then(
											function(response) {
												$scope.licenceTypeList = response.data.listObject;
											});
						} catch (e) {
							console.log(e.message)
						}
					}
				    
				    $scope.getLicenceTypeList($scope.orgId);
				    
				    
				    //get country master list
				    
				    $scope.getCountryMasterList = function() {
						try {
							var data = "";
							var URI = BASE_URL + ROOT_URL
									+ "/adt/getAllCountryMasterList";
							console.log("URI", URI);
							GenericService
									.serviceAction("GET", URI, data)
									.then(
											function(response) {
												$scope.countryList = response.data.listObject;
											});
						} catch (e) {
							console.log(e.message)
						}
					}
				    
				    $scope.getCountryMasterList();
				    
				    $scope.getStateMasterList = function(countryId) {
						try {
							var data = "";
							var URI = BASE_URL + ROOT_URL
									+ "/adt//getStateByCountryId/"+countryId;
							console.log("URI", URI);
							GenericService.serviceAction("GET", URI,data)
									.then(
											function(response) {
												$scope.stateList = response.data.listObject;
											});
						} catch (e) {
							console.log(e.message)
						}
					}
				    
				    
				    $scope.getCityMasterList = function(stateId) {
						try {
							var data = "";
							var URI = BASE_URL + ROOT_URL
									+ "/adt//getAllCityMasterList/"+stateId;
							console.log("URI", URI);
							GenericService.serviceAction("GET", URI,data)
									.then(
											function(response) {
												$scope.cityList = response.data.listObject;
											});
						} catch (e) {
							console.log(e.message)
						}
					}
				    
				    /** Code for get list and count for organization **/
				    
				    $scope.setNoOfRecords = function() {
						$scope.initOrganizationList($scope.offset, $scope.noOfRecordsPerPage);
					};
					$scope.initOrganizationList = function(offset, noOfRecordsPerPage)
					{
						$rootScope.startLoader();
						var data = "";
						offset = offset != null ? offset : 0;
						noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage : 10;
						var URI1 = BASE_URL + ROOT_URL + S +"allArganizationList"+ S + offset + S + noOfRecordsPerPage; 
						
						var URI2 = BASE_URL + ROOT_URL + "/organization/count";
						promiseFactory.setPromises(URI1, URI2, "GET", "GET").then(function(response) 
						{
							$rootScope.stopLoader();
							$scope.organizationList = response[0].listObject;
							$scope.organizationCount = response[1].object;
							$scope.setPage(1, false);
							console.log($scope.organizationList);
							
						});
					}
					
					
				    $scope.getOrganizationMasterList = function(offset, noOfRecordsPerPage) {
						$rootScope.startLoader();
						console.log("offset", offset);
						var data = "";
						offset = offset != null ? offset : 0;
						noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage : 10;
						var URI = BASE_URL + ROOT_URL + "/allArganizationList"+ S + offset + S + noOfRecordsPerPage;
						console.log("URI", URI);
						GenericService.serviceAction("GET", URI, data).then(function(response) {
							$rootScope.stopLoader();
							$scope.organizationList = response.data.listObject;
						});
					}
				    
				    
				    $scope.pager = {};
					$scope.page;

					$scope.setPage = function(page, flag) {
						if (page < 1 || page > $scope.pager.totalPages)
						{
							return;
						}
						$scope.pager = PagerService.GetPager($scope.organizationCount, page, $scope.noOfRecordsPerPage);
						if (flag)
						{
							$scope.getOrganizationMasterList($scope.pager.startIndex, $scope.pager.pageSize);
						}
					}

					$scope.initOrganizationList($scope.offset, $scope.noOfRecordsPerPage);
				  
					/**Code End For List**/
				    
					
					/**Code Start For Crud**/
					
					$scope.updateStatus = function(orgId,status){
						try{
							$rootScope.startLoader();
							//$scope.initRackMaster();
							var URI = BASE_URL + ROOT_URL + "/organization/status"+S+orgId+S+status;
							console.log("ACT_INACT_URI",URI);
							GenericService.serviceAction("GET", URI, $scope.organization).then(function(response) 
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
						}catch(e){
							console.log(e);
						}
					}
					
				    $scope.saveOrganization = function(){
				    	
				    	try 
						{
							//$rootScope.startLoader();
				    		$scope.organization.listOrganizationUnitLicenceDetailsDto= $scope.listLicenceDetailsMaster;
							console.log("OrganizationMaster",JSON.stringify($scope.organization));
							var URI = BASE_URL + ROOT_URL + "/organization/save";
							GenericService.serviceAction("POST", URI,$scope.organization).then(function(response) 
							{	
								if (response.data.status == 'success')
								{
									$rootScope.stopLoader();
									growl.success(response.data.message,
											{
												title : 'Success!'
											});
									$scope.$broadcast('show-errors-reset');
									$scope.initOrganizationMaster();
									$scope.initOrganizationList($scope.offset, $scope.noOfRecordsPerPage);
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
				    
				    
				    $scope.editOrganization = function(orgId)
					{
						try {
							$rootScope.startLoader();
							$scope.saveBtnFlag = false;
							$scope.updateScope = true;
							$scope.updateBtnFlag = true;
							var URI = BASE_URL + ROOT_URL +"/organization"+S+orgId;
							console.log("EDIT",URI);
							$scope.listLicenceDetailsMaster = [];
							GenericService.serviceAction("GET", URI, $scope.common).then(function(response) 
							{

								if (response.data.status == 'success')
								{
									if(response.data.object.listOrganizationUnitLicenceDetailsDto.length > 0){
										
										angular.forEach(response.data.object.listOrganizationUnitLicenceDetailsDto,function(value,index){
											var stringDate = "";
											stringDate = typeof(value) != "undefined" ? value.expiryDate : "";
											console.log("StringDate",stringDate);
											var day = stringDate.substring(0,2);
											var month = stringDate.substring(3,5);
											var	year = stringDate.substring(6,10);
											var date = new Date(year,month,day);
											console.log("date--",date);
											value.expiryDate = date;
											
											$scope.listLicenceDetailsMaster.push(value);
										})
									}else{
										$scope.listLicenceDetailsMaster.push($scope.listLicenceDetailsMaster);
									}
									$rootScope.stopLoader();
									$scope.organization = response.data.object;
									$scope.getStateMasterList($scope.organization.organizationId);
									$scope.getCityMasterList($scope.organization.stateId);
									console.log("$scope.organization",$scope.organization);
									console.log("$scope.listLicenceDetailsMaster",$scope.listLicenceDetailsMaster);
									
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
				    
				    
 $scope.updateOrganization = function(){
				    	
				    	try 
						{
							//$rootScope.startLoader();
				    		$scope.organization.listOrganizationUnitLicenceDetailsDto= $scope.listLicenceDetailsMaster;
							console.log("OrganizationMaster",JSON.stringify($scope.organization));
							var URI = BASE_URL + ROOT_URL + "/organization/update";
							GenericService.serviceAction("POST", URI,$scope.organization).then(function(response) 
							{	
								if (response.data.status == 'success')
								{
									$rootScope.stopLoader();
									growl.success(response.data.message,
											{
												title : 'Success!'
											});
									$scope.$broadcast('show-errors-reset');
									$scope.initOrganizationMaster();
									$scope.initOrganizationList($scope.offset, $scope.noOfRecordsPerPage);
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
				    
 
 		$scope.licenceDetails = function(orgId){
 			
 			try{
 				
 				var data = {
 	 					organizationId : orgId
 	 			}
 				
 				var URI = BASE_URL + ROOT_URL + "/licenceDetailsByOrganization";
 				console.log("LicenceDetails",JSON.stringify(data));
 				
 				GenericService.serviceAction("POST", URI,data).then(function(response) 
						{	
							if (response.data.status == 'success')
							{
								$rootScope.stopLoader();
								/*growl.success(response.data.message,
										{
											title : 'Success!'
										});*/
								$scope.listLicenceDetails = response.data.listObject;
								console.log("$scope.listLicenceDetails",$scope.listLicenceDetails);
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
				    /**Code End For Crud**/
				    
				} ]);
