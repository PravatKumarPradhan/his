angular.module("myApp").controller("addNewCompanyContractController", [ '$scope','$rootScope','BillingGenericService','$cookies','$state','growl','servicesFactory','itemFactory',
	function($scope,$rootScope,BillingGenericService,$cookies,$state,growl,servicesFactory,itemFactory){
	try{
		$rootScope.loginpage = true;
		
		var cookieObject = $cookies.getObject('cookieObject');
		if (cookieObject == undefined) {
			$state.go('login');
			return;
		}
		$scope.unitId = cookieObject.unitId;
		$scope.orgId = 1;//cookieObject.organizationId;
		$scope.userId = 1;
		
		/**DatePicker Code  */
		
		$scope.fromDate = moment().startOf('day').toDate();
	    $scope.toDate = moment().startOf('day').toDate();
	    $scope.completed='N';
		$scope.pending = 'N';
		$scope.outsourced = 'N';
		
		$scope.dateOptions =
		{
			formatYear : 'yyyy',
			showWeeks : false,
			maxDate : "",
		};

		$scope.open = function() {
			$scope.popup.opened = true;
		};

		$scope.popup =
		{
			opened : false
		};
		
		$scope.popup1 =
		{
			opened : false
		};
		
		$scope.open1 = function() 
		{
			$scope.popup1.opened = true;
		};

		
		
		$scope.firstTabLevel = 1 ;
		
		$scope.setFirstTabLevel= function(newTab)
		{
			$scope.firstTabLevel = newTab;
		};
		$scope.isSetFirstTabLevel = function(tabNum)
		{
			return $scope.firstTabLevel === tabNum;
		};
		
		
		
		$scope.initCompanyContarctObj = function(){
			$scope.companyContarctObj = {contractId:"",paymentEntitlementId:"",associateCompanyId:"",contractName:"",validityFrom:"",validityTo:"",gradeId:"",coSharePercentage:"",tariffId:"",isTariffRateApplicable:"",billingBedCategoryId:"",visitTypeId:"",patientTypeId:"",companyId:"",createdBy:"",updatedBy:""}
		}
		$scope.init = function(){
			  $scope.specialityList = [];
			  $scope.groupDetailsList = [];
			  $scope.capAmountDetailsList = [];
			  $scope.groupWiseDetailsList=[]; 
			  servicesFactory.initServiceSearch();
			  $scope.selectedServiceList = [];
			  $scope.notSelectedServiceList=[];
			  $scope.contractBedCategoryList = [];
			  $scope.initSubSpeciality();
			  $scope.initSpeciality();
			  //$scope.setOrgUnit($scope.orgId,$scope.unitId);
			  servicesFactory.setScope();
			   $scope.itemObj = {};
			   itemFactory.setScope();
			   itemFactory.initItemFactory();
			   $scope.pharamExcCategoryWiseList = [];
			   $scope.cAPAmtProductCategoryWiseList= [];
			   $scope.selectedItemList=[];
			   $scope.initCompanyContarctObj();
			  
		}
		
		$scope.initSubSpeciality = function(){
			 $scope.subSpeciality = {subSpecialityName:"",subSpecialityId:""};
		}
		$scope.initSpeciality = function(){
			 $scope.speciality = {specialityName:"",specialityId:""};
		}
		$scope.init();
		
		$scope.getPackageTypeList = function(orgId,unitId) {
			var data = "";
			var URI = BASE_URL + ROOT_URL + "/Unit/Service/EHCpackage/getPackgeType/"+orgId+"/"+unitId;
			BillingGenericService.serviceAction("GET", URI, data).then(function(response){
				$scope.packageTypeList = response.data.listObject;
				console.log("packageTypeList",JSON.stringify($scope.packageTypeList));
			});
	}
	
	$scope.getTariffList = function(orgId,unitId) {
			var data = "";
			var URI = BASE_URL + ROOT_URL + "/Unit/Service/EHCpackage/getTariff/"+orgId+"/"+unitId;
			BillingGenericService.serviceAction("GET", URI, data).then(function(response){
				$scope.tariffList= response.data.listObject;
				console.log("tariffList",JSON.stringify($scope.tariffList));
			});
	}
	
	$scope.getPaymentEntitlementList = function() {
			var data = "";
			var URI = BASE_URL + ROOT_URL + "/global/paymententitlement/"+$scope.orgId;
			BillingGenericService.serviceAction("GET", URI, data).then(function(response){
				$scope.paymentEntitlementList= response.data.listObject;
				console.log("paymentEntitlementList",JSON.stringify($scope.paymentEntitlementList));
			});
	}
	

	
	$scope.getTariffList($scope.orgId,$scope.unitId);
	$scope.getPaymentEntitlementList();
	
	
	  $scope.getBedBillingCategoryList = function() {
		    var data = "";
		    var URI = BASE_URL + ROOT_URL + "/adt/getActiveBillingBedCategoryList/orgId/"+$scope.orgId+"/unitId/"+$scope.unitId;
		    BillingGenericService.serviceAction("GET", URI, data).then(function(response){
		     $scope.bedBillingCategoryList= response.data.listObject;
		     console.log("bedBillingCategoryList",JSON.stringify($scope.bedBillingCategoryList));
		    });
		  }
	  $scope.getBedBillingCategoryList();
	  
	  
	  $scope.getVisitTypeList = function(searchPackageObj) {
		  var param = {organizationId:$scope.orgId,unitId:$scope.unitId};
		    var URI = BASE_URL + ROOT_URL + "/unit/CompanyContract/getVisitTypeList"
		    BillingGenericService.serviceAction(METHOD_POST,URI,param,NOTIFICATION_MSG_STATUS_FALSE).then(function(response) {
				if (response.data.status == "success"){
					 $scope.visityTypeList = response.data.listObject;
					  console.log("visityTypeList",JSON.stringify($scope.visityTypeList));
				}
			});
		  }
		$scope.getVisitTypeList();
		
		
		$scope.getAssociateCompanyMasterList = function(searchPackageObj) {
			  var param = {organizationId:$scope.orgId,unitId:$scope.unitId};
			    var URI = BASE_URL + ROOT_URL + "/unit/CompanyContract/getAssociateCompanyMaster"
			    BillingGenericService.serviceAction(METHOD_POST,URI,param,NOTIFICATION_MSG_STATUS_FALSE).then(function(response) {
					if (response.data.status == "success"){
						 $scope.associateCompanyMasterList = response.data.listObject;
						  console.log("associateCompanyMasterList",JSON.stringify($scope.associateCompanyMasterList));
					}
				});
			  }
			$scope.getAssociateCompanyMasterList();
			
			

			$scope.getGradeListByAssociateCompanyId = function(searchPackageObj) {
							  var param = {organizationId:$scope.orgId,unitId:$scope.unitId};
							    var URI = BASE_URL + ROOT_URL + "/unit/CompanyContract/getGradeListByAssociateCompanyId"
							    BillingGenericService.serviceAction(METHOD_POST,URI,param,NOTIFICATION_MSG_STATUS_FALSE).then(function(response) {
									if (response.data.status == "success"){
										 $scope.gradeListByAssociateCompanyIdList = response.data.listObject;
										  console.log("gradeListByAssociateCompanyIdList",JSON.stringify($scope.gradeListByAssociateCompanyIdList));
									}
								});
							  }
							$scope.getGradeListByAssociateCompanyId();
						
			
			
			
			$scope.getCompanyMasterList = function(searchPackageObj) {
				  var param = {organizationId:$scope.orgId,unitId:$scope.unitId};
				    var URI = BASE_URL + ROOT_URL + "/unit/CompanyContract/getCompanyMasterList"
				    BillingGenericService.serviceAction(METHOD_POST,URI,param,NOTIFICATION_MSG_STATUS_FALSE).then(function(response) {
						if (response.data.status == "success"){
							 $scope.companyMasterList = response.data.listObject;
							 console.log("companyMasterList",JSON.stringify($scope.companyMasterList));
						}
					});
				  }
				$scope.getCompanyMasterList();
				
				
				 //get speciality list for autocomplete
				  $scope.getSpecialityList = function(){
					  var URI = BASE_URL + ROOT_URL + "/adt/getActiveSpecialityList";
					  var param = {organizationId:$scope.orgId,unitId:$scope.unitId};
					    BillingGenericService.serviceAction("POST", URI, param).then(function(response){
					    	if(response.data.status == "success")
					    		$scope.specialityList = response.data.listObject;
					    	 console.log("specialityList",JSON.stringify($scope.specialityList));
					    });
				  }
				  $scope.getSpecialityList();
			
				  $scope.selectSpeciality = function($item, $model,$label)
				  {
				       $scope.speciality.specialityId = $item.specialityId;
				       if($item.specialityId != ""){
				    	   $scope.initSubSpeciality();
				    	   $scope.subSpecialityList = [];
				       var URI = BASE_URL + ROOT_URL + "/adt/subspeciality/"+$item.specialityId;
						BillingGenericService.serviceAction(METHOD_GET,URI, null,NOTIFICATION_MSG_STATUS_FALSE).then(function(response) {
							if (response.data.status == "success")
								$scope.subSpecialityList = response.data.listObject;
							console.log("subSpecialityList",JSON.stringify($scope.subSpecialityList));
						});
				       }
				  }
				  
				  $scope.selectSubSpeciality = function($item, $model,$label){
					  $scope.subSpeciality.subSpecialityId = $item.subSpecialityMasterId;
				  }
		
				//---------------------Group Exclusion Section -----------------------------
					$scope.addGroupDetails = function(){
						if($scope.speciality.specialityId == '' || typeof($scope.speciality.specialityId) == "undefined"){
							growl.error("Please select a group",{
								title : ERROR_MSG
							});
							return;
						}
						var temp = {departmentId:$scope.speciality.specialityId, subDepartmentId:$scope.subSpeciality.subSpecialityId,
									groupDesc:$scope.speciality.specialityName,subGroupDesc:$scope.subSpeciality.subSpecialityName};
						
						$scope.groupDetailsList.push(temp);
						$scope.initSubSpeciality();
						$scope.initSpeciality();
					}
					
					$scope.removeGroupDetails = function(index,obj){
						$scope.groupDetailsList.splice(index,1);
					
					}
					
					
					$scope.addServices = function(){
						$scope.selectedServiceList = [];
						$scope.notSelectedServiceList = [];
							var groupIds = [];
							angular.forEach($scope.groupDetailsList,function(value,index){
								groupIds.push(value.departmentId);
							});
						angular.forEach($scope.searchServiceList,function(value,key){
							if(value.isSelected){
								$scope.selectedServiceList.push(value);
								console.log("selectedServiceList",JSON.stringify($scope.selectedServiceList));
							}else{
								if(groupIds.indexOf(value.specialityId) == -1)
									$scope.notSelectedServiceList.push(value);
								
								console.log("notSelectedServiceList",JSON.stringify($scope.notSelectedServiceList));
							}
						});
					}
					
					$scope.removeSelectedService = function(index){
						$scope.selectedServiceList.splice(index,1);
					}
					
					
					$scope.removeNotSelectedService = function(index){
						$scope.notSelectedServiceList.splice(index,1);
					}
					
					//--------------------- Bed Category Section -----------------------------
					$scope.bedCatName = function(id){
						angular.forEach($scope.bedCategoryList,function(value,index){
							if(value.bedCategoryId == id){
								$scope.bedCategoryName = value.bedCategoryDesc;
							}
						});
					}
					
					
					$scope.addBedCategory = function(){
						if($scope.bedCategory.bedCategoryId != ""){
							$scope.bedCatName($scope.bedCategory.bedCategoryId);
							var obj = {bedCategoryId:$scope.bedCategory.bedCategoryId,applicableDays:0,bedCategoryDesc:$scope.bedCategoryName};
							$scope.contractBedCategoryList.push(obj);
						}else{
							growl.error("Please select bed category",{
								title : ERROR_MSG
							});
						}
					}
					
					$scope.removeBedCategory = function(index){
						$scope.contractBedCategoryList.splice(index);
					}
					
					  //get bed category list
					  $scope.getBedCategoryList = function(){
						  var URI = BASE_URL + ROOT_URL + "/adt/getBedCategoryListByOrgUnit";
						  var param = {organizationId:$scope.orgId,unitId:$scope.unitId};
						    BillingGenericService.serviceAction("POST", URI, param).then(function(response){
						    	if(response.data.status == "success")
						    		$scope.bedCategoryList = response.data.listObject;
						    });
					  }
					  $scope.getBedCategoryList();
		
					//---------------------CAP Amount Section -----------------------------
					  
					  
					  $scope.addCAPAmountDetails = function(){
							if($scope.speciality.specialityId == '' || typeof($scope.speciality.specialityId) == "undefined"){
								growl.error("Please select a group",{
									title : ERROR_MSG
								});
								return;
							}
							var temp = {departmentId:$scope.speciality.specialityId, subDepartmentId:$scope.subSpeciality.subSpecialityId,
										groupDesc:$scope.speciality.specialityName,subGroupDesc:$scope.subSpeciality.subSpecialityName};
							
							isGroupExists=checkGroupIdExistsOrNot($scope.groupDetailsList,temp)
							if (!isGroupExists) {
								$scope.capAmountDetailsList.push(temp);
							}else{
								growl.error("Already group added in group exclusion",{	
									title : ERROR_MSG
								});
								return;
							}
							
							
							
							$scope.initSubSpeciality();
							$scope.initSpeciality();
						}
						
					  //Check Group
					  var isGroupExists;
						function checkGroupIdExistsOrNot(arr, val) {
							return arr
									.some(function(arr) {
										return (val.departmentId === arr.departmentId);
									});
						}
						//check subGroup
						 var isSubGroupExists;
							function checkSubGroupIdExistsOrNot(arr, val) {
								return arr
										.some(function(arr) {
											return (val.subDepartmentId === arr.subSpecialityId);
										});
							}
					  
						$scope.removeCAPAmountDetails = function(index,obj){
							  $scope.capAmountDetailsList.splice(index,1);
						
						}
						
						
						
						
						
						//---------------------Group Wise Details Section -----------------------------
						  
						  
						  $scope.addGroupWiseDetails = function(){
								if($scope.speciality.specialityId == '' || typeof($scope.speciality.specialityId) == "undefined"){
									growl.error("Please select a group",{
										title : ERROR_MSG
									});
									return;
								}
								var temp = {departmentId:$scope.speciality.specialityId, subDepartmentId:$scope.subSpeciality.subSpecialityId,
											groupDesc:$scope.speciality.specialityName,subGroupDesc:$scope.subSpeciality.subSpecialityName,apportionedPercent:0};
								
								

								isGroupExists=checkGroupIdExistsOrNot($scope.groupDetailsList,temp)
								if (!isGroupExists) {
									$scope.groupWiseDetailsList.push(temp);
								}else{
									growl.error("Already group added in group exclusion",{	
										title : ERROR_MSG
									});
									return;
								}
								
								var groupIds = [];
								angular.forEach($scope.groupWiseDetailsList,function(value,index){
									groupIds.push(value.departmentId);
								});
								
							
								$scope.initSubSpeciality();
								$scope.initSpeciality();
							}
							
							$scope.removeGroupWiseDetails = function(index,obj){
								 $scope.groupWiseDetailsList.splice(index,1);
							
							}
							
							//-----------------------category wise consumables-----------------------------------
							$scope.addpharamExcCategory = function(catWise){
								var productCategory = "";
								angular.forEach($scope.productCategoryList,function(value,index){
									if(value.id == catWise.productCategoryId)
										productCategory = value.category;
								});
								var temp = {productCategoryId:catWise.productCategoryId, category:productCategory, qty:0};
								$scope.pharamExcCategoryWiseList.push(temp);
								$scope.cAPAmtProductCategoryWiseList = [];
								
							}
							
							$scope.removepharamExcCategory = function(index){
								$scope.pharamExcCategoryWiseList.splice(index,1);
							}
							
							$scope.addCAPAmtProductCategoryWise = function(catWise){
								var productCategory = "";
								angular.forEach($scope.productCategoryList,function(value,index){
									if(value.id == catWise.productCategoryId)
										productCategory = value.category;
								});
								var temp = {productCategoryId:catWise.productCategoryId, category:productCategory, qty:0};
								
									isProductExists=checkProductIdExistsOrNot($scope.pharamExcCategoryWiseList,temp)
								if (!isProductExists) {
									$scope.cAPAmtProductCategoryWiseList.push(temp)
								}else{
									growl.error("Already product added in pharmacy exclusion",{	
										title : ERROR_MSG
									});
									return;
								}
								
							}
							
							$scope.removeCAPAmtProductCategoryWise = function(index){
								$scope.cAPAmtProductCategoryWiseList.splice(index,1);
							}
							
							
							  //Check Product Wise Category
							  var isProductExists;
								function checkProductIdExistsOrNot(arr, val) {
									return arr
											.some(function(arr) {
												return (val.productCategoryId === arr.productCategoryId);
											});
								}
							
							
							$scope.addCategoryExclusionItems = function(){
								$scope.categoryWiseExclusionItemsList = [];
								angular.forEach($scope.searchItemList,function(value,index){
									if(value.isSelected){
										$scope.categoryWiseExclusionItemsList.push(value);
									}
								});
							}
							
							$scope.removeCategoryExclusionItems = function(index){
								$scope.categoryWiseExclusionItemsList.splice(index,1)
							}
							
							
							$scope.addItemListServices = function(){
								$scope.selectedItemList = [];
								
								angular.forEach($scope.searchItemList,function(value,key){
									if(value.isSelected){
										$scope.selectedItemList.push(value);
									}else{
										
									}
								});
							}
							
							$scope.removeSelectedItemsList = function(index){
								$scope.selectedItemList.splice(index,1)
							}
							
							//---------------------calculate Variance Price section -----------------------------
							
							$scope.calculateVariancePrice = function(){
								var groupDetails = {};
								angular.forEach($scope.groupWiseDetailsList,function(value,index){
									groupDetails[value.departmentId] = value;
								})
								angular.forEach($scope.notSelectedServiceList,function(value,index){
									if(typeof(groupDetails[value.specialityId]) != "undefined"){
										var grpDtls = groupDetails[value.specialityId];
										var percentAmount = isNaN(grpDtls.apportionedPercent) && isNaN(value.rate) ? 0 : (parseFloat(value.rate) / 100) *  parseFloat(grpDtls.apportionedPercent);
										percentAmount = parseFloat(percentAmount) + (isNaN(grpDtls.roundOffAmount) ? 0 : parseFloat(grpDtls.roundOffAmount));
										var apportionedPrice = 0;
										//mark up down
										switch(grpDtls.varianceUpDown){
										case "1" :
											apportionedPrice = parseFloat(percentAmount) + parseFloat(value.rate);
											break;
										case "2" :
											apportionedPrice = parseFloat(value.rate) - parseFloat(percentAmount);
											break;
										}
										apportionedPrice = parseFloat(apportionedPrice) + parseFloat(isNaN(grpDtls.roundOffAmount) ? 0 : grpDtls.roundOffAmount);
										$scope.notSelectedServiceList[index].finalPrice =$rootScope.parseFloatNum(apportionedPrice);
										$scope.notSelectedServiceList[index].apportionedPrice = $rootScope.parseFloatNum(apportionedPrice);
									}
								})
							}
							
							
							
							
							//---------------------SAVE COMPANY CONTARCT DETAILS SECTION -----------------------------
							
							
							/** code for setting all checkboxes */
							$scope.checkAll = function() {
								if ($scope.isTariffRatesApplicable) {
									$scope.isTariffRatesApplicable = true;
								} else {
									$scope.isTariffRatesApplicable = false;
								}
							 }
						
							$scope.saveCompanyContract = function(){
								
								  $scope.companyContarctObj.validityFrom= $scope.fromDate==undefined?"":moment($scope.fromDate.getTime()).format('MM-DD-YYYY');
								  $scope.companyContarctObj.validityTo = $scope.toDate==undefined?"":moment($scope.toDate.getTime()).format('MM-DD-YYYY');
				             
								if($scope.isTariffRatesApplicable== true){
									
									
									$scope.companyContarctObj.createdBy = $scope.userId;
									$scope.companyContarctObj.isTariffRateApplicable="Y";
									var param = {listTContractGroupPharmacyExclusionDetailsDto:[],listTContractCapDetailsDto:[],listTContractGroupDetailsDto:[],
											listTContractServiceDetailsDto:[],orgId:$scope.orgId,unitId:$scope.unitId,companyContractMasterDto:$scope.companyContarctObj}
									
									 console.log("param isTariffRateApplicable",JSON.stringify(param));
									
									$rootScope.startLoader();
									var URI = BASE_URL + ROOT_URL + "/unit/CompanyContract/createCompanyContarct";
									BillingGenericService.serviceAction(METHOD_POST,URI, param,NOTIFICATION_MSG_STATUS_TRUE).then(function(response) {
										if (response.data.status == "success")
											$scope.init();
										$rootScope.stopLoader();
									});
									
								}else{
								//contract Contract Group Exclusion Details  1st list
								var listTContractGroupPharmacyExclusionDetailsDto = [];
								angular.forEach($scope.groupDetailsList,function(value,index){
									var temp = {
											departmentId :value.departmentId,
											subDepartmentId :value.subDepartmentId,
											isServiceItem : 2,
											createdBy:$scope.userId
									};
									
									listTContractGroupPharmacyExclusionDetailsDto.push(temp);
								});
								
								//contract Contract Group Exclusion Details 2nd list
								
								angular.forEach($scope.selectedServiceList,function(value,index){
									var temp = {
											isServiceItem : 1,
											serviceId:value.serviceMasterId,
											createdBy:$scope.userId
									};
									
									listTContractGroupPharmacyExclusionDetailsDto.push(temp);
								});
								
								
								//contract Contract  Pharmacy Exclusion Details 1nd list
								angular.forEach($scope.pharamExcCategoryWiseList,function(value,index){
									var temp = {
											isServiceItem : 2,
											categoryId:value.productCategoryId,
											createdBy:$scope.userId
									};
									
									listTContractGroupPharmacyExclusionDetailsDto.push(temp);
								});
								
								
								//contract Contract  Pharmacy Exclusion Details 2nd list
								angular.forEach($scope.selectedItemList,function(value,index){
									var temp = {
											isServiceItem : 2,
											itemId:value.itemId,
											createdBy:$scope.userId
									};
									
									listTContractGroupPharmacyExclusionDetailsDto.push(temp);
								});
								
								
								//	cap Amount Details  Details
								var listTContractCapDetailsDto = [];
								angular.forEach($scope.capAmountDetailsList,function(value,index){
									var temp = {
											
											departmentId : 	value.departmentId,
											subDepartmentId : value.subDepartmentId,
											departmentCapAmount :value.departmentCapAmount,
											subDepartmentCapAmount:value.subDepartmentCapAmount,
											isServiceItem : 2,
											createdBy:$scope.userId
									};
									listTContractCapDetailsDto.push(temp);
								});
								
								
								angular.forEach($scope.cAPAmtProductCategoryWiseList,function(value,index){
									var temp = {
											
											isServiceItem : 1,
											productCategoryId:value.productCategoryId,
											//productCategoryCapAmount:value.productCategoryCapAmount,
											createdBy:$scope.userId
									};
									listTContractCapDetailsDto.push(temp);
								});
								
								
								
								 
								//  Contract Group wise Details 
								var listTContractGroupDetailsDto = [];
								angular.forEach($scope.groupWiseDetailsList,function(value,index){
									var temp = {
											departmentId : 	value.departmentId,
											subDepartmentId : value.subDepartmentId,
											variancePercentage: value.apportionedPercent,
											varianceUpDown: value.varianceUpDown,
											roundOffAmount: value.roundOffAmount,
										};
									listTContractGroupDetailsDto.push(temp);
								});
								
								
								//Contract Service Details
								var listTContractServiceDetailsDto = [];
								angular.forEach($scope.notSelectedServiceList,function(value,index){
									var temp = {
											contractGroupDetailId :value.contractGroupDetailId,
											serviceId:value.serviceMasterId,
											servicePrice : value.rate,
											finalPrice: value.finalPrice,
											apportionedPrice: value.apportionedPrice,
											createdBy:$scope.userId
										};
									listTContractServiceDetailsDto.push(temp);
								});
								
								
								
								//bed category details
								var listTContractBedCategoryDetailDto = [];
								angular.forEach($scope.contractBedCategoryList,function(value,index){
									var temp = {
											billingBedCategoryId : value.bedCategoryId,
											applicableDays : value.applicableDays,
											createdBy:$scope.userId
									};
									listTContractBedCategoryDetailDto.push(temp);
								});
								$scope.companyContarctObj.createdBy = $scope.userId;
								$scope.companyContarctObj.isTariffRateApplicable="N";
							
								var param = {listTContractGroupPharmacyExclusionDetailsDto:listTContractGroupPharmacyExclusionDetailsDto,listTContractBedCategoryDetailDto:listTContractBedCategoryDetailDto,listTContractCapDetailsDto:listTContractCapDetailsDto,listTContractGroupDetailsDto:listTContractGroupDetailsDto,
										listTContractServiceDetailsDto:listTContractServiceDetailsDto,orgId:$scope.orgId,unitId:$scope.unitId,companyContractMasterDto:$scope.companyContarctObj}

								 console.log("param",JSON.stringify(param));
								$rootScope.startLoader();
								var URI = BASE_URL + ROOT_URL + "/unit/CompanyContract/createCompanyContarct";
								BillingGenericService.serviceAction(METHOD_POST,URI, param,NOTIFICATION_MSG_STATUS_TRUE).then(function(response) {
									if (response.data.status == "success")
										$scope.init();
									
									$rootScope.stopLoader();
								});
								}
							}
		
	}catch(e){
		console.log(e.message);
	}
}]);	