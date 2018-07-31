
angular.module('myApp').controller(
		'hospitalMasterController',
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
					$scope.unitMasterList = [];
					$scope.totalNoOfRecords;
					$scope.searchKeyword;
					$scope.unitCount;
					$scope.createdBy =1 ;
					$scope.updatedBy =1 ;
				    
				    
				    $scope.addLicenceDetails = function(){
				    	console.log($scope.licenceDetailsMaster);
				    	if($scope.updateScope ==false)
	        	        {
				    	  var licenceDetailsMaster = angular.copy($scope.licenceDetailsMaster);
						  $scope.listLicenceDetailsMaster.push(licenceDetailsMaster);
						  console.log("$scope.listLicenceDetailsMaster",JSON.stringify($scope.listLicenceDetailsMaster));
	        	       }
				      else if($scope.updateScope ==true)
			        	{
				    	  var licenceDetailsMaster = angular.copy($scope.licenceDetailsMaster);
				    	  licenceDetailsMaster.unitId = $scope.unit.unitId;
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
			        	
					$scope.unit =
					{
			        	 "unitId" :"",		
						 "organizationId":"",
						 "unitCode":"",
						 "unitName":"",
						 "status":"A",
						 "unitDesc":"",
						 "createdBy":"",
						 "createdDate":$scope.createdDate,
						 "updatedBy":"",
						 "updatedDate":$scope.updatedDate,
					     "unitAddress":"",
					     "unitCountryId":"",
					     "unitStateId":"",
					     "unitCityId":"",
					     "postCode":"",
					     "unitContact":"",
					    // "organizationFaxNo":"",
					     "unitEmailId":"",
					     "unitLogo":"",
					     "listOrganizationUnitLicenceDetailsDto":""
					 }
			        	
			        	$scope.listLicenceDetailsMaster=[];
						var licenceDetailsMaster = angular.copy($scope.licenceDetailsMaster);
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
				    
				    $scope.getOrganizationList =function(){
				    	try{
				    		var data ="";
				    		var URI = BASE_URL + ROOT_URL + S +"/organization/active";
				    		console.log("URI",URI);
				    		GenericService.serviceAction("GET",URI,data)
				    		.then(
				    				function(response){
				    					$scope.organizationMasterList = response.data.listObject;
				    				})
				    	}catch(e){
				    		console.log("Exception",e);
				    	}
				    }
				    $scope.getOrganizationList();
				    
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
						var URI1 = BASE_URL + ROOT_URL + S +"unit"+ S + offset + S + noOfRecordsPerPage; 
						
						var URI2 = BASE_URL + ROOT_URL + "/unit/count";
						promiseFactory.setPromises(URI1, URI2, "GET", "GET").then(function(response) 
						{
							$rootScope.stopLoader();
							$scope.unitMasterList = response[0].listObject;
							$scope.unitCount = response[1].object;
							$scope.setPage(1, false);
							console.log($scope.unitMasterList);
							
						});
					}
					
					
				    $scope.getUnitMasterList = function(offset, noOfRecordsPerPage) {
						$rootScope.startLoader();
						console.log("offset", offset);
						var data = "";
						offset = offset != null ? offset : 0;
						noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage : 10;
						var URI = BASE_URL + ROOT_URL + "/unit"+ S + offset + S + noOfRecordsPerPage;
						console.log("URI", URI);
						GenericService.serviceAction("GET", URI, data).then(function(response) {
							$rootScope.stopLoader();
							$scope.unitMasterList = response.data.listObject;
						});
					}
				    
				    
				    $scope.pager = {};
					$scope.page;

					$scope.setPage = function(page, flag) {
						if (page < 1 || page > $scope.pager.totalPages)
						{
							return;
						}
						$scope.pager = PagerService.GetPager($scope.unitCount, page, $scope.noOfRecordsPerPage);
						if (flag)
						{
							$scope.getUnitMasterList($scope.pager.startIndex, $scope.pager.pageSize);
						}
					}

					$scope.initOrganizationList($scope.offset, $scope.noOfRecordsPerPage);
				  
					/**Code End For List**/
				    
					
					/**Code Start For Crud**/
					
					$scope.updateStatus = function(unitId,status){
						try{
							$rootScope.startLoader();
							//$scope.initRackMaster();
							var URI = BASE_URL + ROOT_URL + "/unit/status"+S+unitId+S+status;
							console.log("ACT_INACT_URI",URI);
							GenericService.serviceAction("GET", URI, $scope.unit).then(function(response) 
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
					
				    $scope.saveUnit = function(){
				    	
				    	try 
						{
							//$rootScope.startLoader();
				    		console.log("$scope.listLicenceDetailsMaster",$scope.listLicenceDetailsMaster);
				    		$scope.unit.listOrganizationUnitLicenceDetailsDto= $scope.listLicenceDetailsMaster;
							console.log("UnitMaster",JSON.stringify($scope.unit));
							var URI = BASE_URL + ROOT_URL + "/unit/save";
							GenericService.serviceAction("POST", URI,$scope.unit).then(function(response) 
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
				    
				    
				    $scope.editUnit = function(unitId)
					{
						try {
							$rootScope.startLoader();
							$scope.saveBtnFlag = false;
							$scope.updateScope = true;
							$scope.updateBtnFlag = true;
							var URI = BASE_URL + ROOT_URL +"/unit"+S+unitId;
							console.log("EDIT",URI);
							GenericService.serviceAction("GET", URI, $scope.common).then(function(response) 
							{

								console.log("data",response.data);

								if (response.data.status == 'success')
								{
									$rootScope.stopLoader();
									$scope.unit = response.data.object;
									$scope.listLicenceDetailsMaster = [];
									if(response.data.object.listOrganizationUnitLicenceDetailsDto.length > 0){
										
										angular.forEach(response.data.object.listOrganizationUnitLicenceDetailsDto,function(value,index){
											var stringDate = "";
											stringDate = typeof(value) != "undefined" ? value.expiryDate : "";
											day = stringDate.substring(0,2);
											month = stringDate.substring(3,5);
											year = stringDate.substring(6,10);
											console.log("StringDate",stringDate);
											var date = new Date(year,month,day);
											console.log("date--",date);
											value.expiryDate = date;
											
											$scope.listLicenceDetailsMaster.push(value);
										})
										
									}else{
										$scope.listLicenceDetailsMaster.push($scope.listLicenceDetailsMaster);
									}
									
									$scope.getStateMasterList($scope.unit.unitCountryId);
									$scope.getCityMasterList($scope.unit.unitStateId);
									console.log("$scope.unit",$scope.unit);
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
				    
				    
				    $scope.updateUnit = function(){
				    	try 
						{
							//$rootScope.startLoader();
				    		
				    		//if($scope.listLicenceDetailsMaster != ""){
				    		$scope.unit.listOrganizationUnitLicenceDetailsDto= $scope.listLicenceDetailsMaster;
				    		/*}else{
				    			$scope.listLicenceDetailsMaster=[];
								var licenceDetailsMaster = angular.copy($scope.licenceDetailsMaster);
					        	$scope.listLicenceDetailsMaster.push(licenceDetailsMaster);
				    		}*/
				    		console.log("Length-",$scope.unit.listOrganizationUnitLicenceDetailsDto.length);	
							console.log("UnitMaster",$scope.unit);
							var URI = BASE_URL + ROOT_URL + "/unit/update";
							/*console.log("URI",URI);
							GenericService.serviceAction("POST", URI,$scope.unit).then(function(response) 
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
							
							});*/
						} catch (e) {
							console.log("Exception",e.message);
						}
				    }
				    
 
 		$scope.licenceDetails = function(unitId){
 			
 			try{
 				
 				var data = {
 	 					unitId : unitId
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
