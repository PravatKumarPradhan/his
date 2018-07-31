angular.module("myApp").controller("addNewPackageWithCAPController", [ '$scope','$rootScope','BillingGenericService','$cookies','$state','growl','servicesFactory','itemFactory',
	function($scope,$rootScope,BillingGenericService,$cookies,$state,growl,servicesFactory,itemFactory){
	try{
		$rootScope.loginpage = true;
		$scope.initPackageObj = function(){
			$scope.packageObj = {packageTypeId:"",sexId:"",minAge:"",maxAge:"",tariffId:"",paymentEntitlementTypeId:"",patientTypeId:"",serviceId:"",markupDownInPercentage:"",isMarkupDown:"1",roundOffPositNeg:"1",visitTypeId:"",billingBedCategoryId:""}
		}
		$scope.initSubSpeciality = function(){
			 $scope.subSpeciality = {subSpecialityName:"",subSpecialityId:""};
		}
		$scope.initSpeciality = function(){
			 $scope.speciality = {specialityName:"",specialityId:""};
		}
		
		$scope.initSummary = function(){
			$scope.summary = {serviceSummary:$rootScope.parseFloatNum(0),groupWiseSummary:$rootScope.parseFloatNum(0),bedCatSummary:$rootScope.parseFloatNum(0),grandTotal:$rootScope.parseFloatNum(0),
							consumDtlsSummary:$rootScope.parseFloatNum(0),catWiseConsumSummary:$rootScope.parseFloatNum(0),consumWiseSummary:$rootScope.parseFloatNum(0)}
		}
		
		$scope.init = function(){
		   $scope.searchServiceList = [];
		   servicesFactory.initServiceSearch();
		   $scope.selectedServiceList = [];
		   $scope.specialityList = [];
		   $scope.subSpecialityList = [];
		   $scope.searchServObj = {};
		   $scope.initPackageObj();
		   $scope.bedCategoryList = [];
		   $scope.specialityList = [];
		   $scope.subSpecialityList = [];
		   $scope.serviceDtlsServiceList = [];
		   $scope.initSpeciality();
		   $scope.initSubSpeciality();
		   $scope.groupDetailsList = [];
		   $scope.groupWiseIncExcList = [];
		   $scope.bedCategory = {bedCategoryId:""};
		   $scope.packageBedCategoryList = [];
		   $scope.initSummary();
		   $scope.itemObj = {};
		   itemFactory.setScope();
		   itemFactory.initItemFactory();
		   $scope.consumableDetailsList = [];
		   $scope.categoryWiseConsumables = [];
		   $scope.categoryWiseExclusionItemsList = [];
		   $scope.consumableWiseProductList = [];
		   $scope.consumableWiseIncExcItemList = [];
		}
		
		var cookieObject = $cookies.getObject('cookieObject');
		if (cookieObject == undefined) {
			$state.go('login');
			return;
		}
		$scope.unitId = cookieObject.unitId;
		$scope.orgId = cookieObject.organizationId;
		$scope.userId = 1;
		$scope.init();
		
		
		$scope.getPackageTypeList = function(orgId,unitId) {
		    var URI = BASE_URL + ROOT_URL + "/Unit/Service/EHCpackage/getPackgeType/"+orgId+"/"+unitId;
		    BillingGenericService.serviceAction("GET", URI, null).then(function(response){
		     $scope.packageTypeList = response.data.listObject;
		    });
		  }
		$scope.getPackageTypeList($scope.orgId,$scope.unitId);
		
		
		
		$scope.getTariffList = function(orgId,unitId) {
		    var URI = BASE_URL + ROOT_URL + "/Unit/Service/EHCpackage/getTariff/"+orgId+"/"+unitId;
		    BillingGenericService.serviceAction("GET", URI, null).then(function(response){
		     $scope.tariffList= response.data.listObject;
		    });
		  }
		$scope.getTariffList($scope.orgId,$scope.unitId);
		
		
		 $scope.getPaymentEntitlementList = function(tariffId) {
			    var URI = BASE_URL + ROOT_URL + "/Unit/Service/EHCpackage/getPaymentEntitlementByTariffId/"+$scope.orgId+"/"+$scope.unitId+"/"+tariffId;
			    BillingGenericService.serviceAction("GET", URI, null).then(function(response){
			     $scope.paymentEntitlementList= response.data.listObject;
			    });
			  }
		
		  $scope.getPatientTypeList = function(tariffId) {
			    var URI = BASE_URL + ROOT_URL + "/Unit/Service/EHCpackage/getPatientTypeByTariffId/"+$scope.orgId+"/"+$scope.unitId+"/"+tariffId;
			    BillingGenericService.serviceAction("GET", URI, null).then(function(response){
			     $scope.patientTypeList= response.data.listObject;
			    });
			  }
	    
		/** Service Autocomplete List * */
		  $scope.getPackageServiceList = function(searchKeyword)
		  {
		    var URI = BASE_URL + ROOT_URL + "/Unit/Service/EHCpackage/autoCompleteServices/"+$scope.orgId+"/"+$scope.unitId+"/"+searchKeyword;
		    return BillingGenericService.serviceAction("GET", URI,null).then(function(response) {
		     return response.data.listObject;
		    });
		  } 
		     
		  $scope.selectPackageService = function($item, $model,$label)
		  {
		       $scope.selectedPackageService = $item.id;
		       $scope.packageObj.serviceId = $item.id;
		  }
		
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
				});
		       }
		  }
		  
		  $scope.selectSubSpeciality = function($item, $model,$label){
			  $scope.subSpeciality.subSpecialityId = $item.subSpecialityMasterId;
		  }
		
		  $scope.getBedBillingCategoryList = function() {
			    var URI = BASE_URL + ROOT_URL + "/adt/getActiveBillingBedCategoryList/orgId/"+$scope.orgId+"/unitId/"+$scope.unitId;
			    BillingGenericService.serviceAction("GET", URI, null).then(function(response){
			     $scope.bedBillingCategoryList= response.data.listObject;
			    });
			  }
		  $scope.getBedBillingCategoryList();
		  
		  //get bed category list
		  $scope.getBedCategoryList = function(){
			  var URI = BASE_URL + ROOT_URL + "/adt/getBedCategoryListByOrgUnit";
			  var param = {organizationId:$scope.orgId,unitId:$scope.unitId};
			    BillingGenericService.serviceAction("POST", URI, param).then(function(response){
			    	if(response.data.status == "success")
			    		$scope.bedCategoryList = response.data.listObject;
			    });
		  }
		  
		  //get speciality list for autocomplete
		  $scope.getSpecialityList = function(){
			  var URI = BASE_URL + ROOT_URL + "/adt/getActiveSpecialityList";
			  var param = {organizationId:$scope.orgId,unitId:$scope.unitId};
			    BillingGenericService.serviceAction("POST", URI, param).then(function(response){
			    	if(response.data.status == "success")
			    		$scope.specialityList = response.data.listObject;
			    });
		  }
		  $scope.getSpecialityList();
		  $scope.getBedCategoryList();
		  
		  /* first level */
		$scope.firstTabLevel = 1 ;
		$scope.setFirstTabLevel= function(newTab)
		{
			$scope.firstTabLevel = newTab;
		};
		$scope.isSetFirstTabLevel = function(tabNum)
		{
			return $scope.firstTabLevel === tabNum;
		};
		
		/*Second Level*/
		$scope.secondTabLevel = 1 ;

		$scope.setSecondTabLevel = function(newTab)
		{
			$scope.secondTabLevel = newTab;
		};

		$scope.isSetSecondTabLevel = function(tabNum)
		{
			return $scope.secondTabLevel === tabNum;
		};
		
		//---------------------Service Details Section -----------------------------
		$scope.addServiceDetailsServices = function(){
			if($scope.isSetFirstTabLevel(1)){
				$scope.serviceDtlsServiceList = [];
				angular.forEach($scope.searchServiceList,function(value,key){
					if(value.isSelected){
						value.qty = 0;
						value.total = 0;
						$scope.serviceDtlsServiceList.push(value);
					}
				});
			}else if($scope.isSetFirstTabLevel(2)){
				//get group and sub group ids for services inclusion and exclusion
				var groupIds = [];
				var subGroupIds = [];
				var duplicates = false;
				
				angular.forEach($scope.groupDetailsList,function(value,index){
					groupIds.push(value.departmentId);
					if(value.subDepartmentId != '')
						subGroupIds.push(value.subDepartmentId);
				});
				
				var isInclusiveService = '';
				if($scope.isSetSecondTabLevel(2))
					isInclusiveService = 1;
				else if($scope.isSetSecondTabLevel(3))
					isInclusiveService = 2;
				
				var addedServiceIds = [];
				angular.forEach($scope.groupWiseIncExcList,function(value,index){
					addedServiceIds.push(value.serviceMasterId);
				});
				
				angular.forEach($scope.searchServiceList,function(value,index){
					if(value.isSelected){
						value.isInclusiveService = isInclusiveService;
						if(groupIds.indexOf(value.specialityId) != -1 && addedServiceIds.indexOf(value.serviceMasterId) == -1){
							$scope.groupWiseIncExcList.push(angular.copy(value));
						}else{
							duplicates = true;
						}
					}
				});
				
				if(duplicates){
					growl.warning("Some services already added",{
						title : ERROR_MSG
					});
				}
				servicesFactory.selectAllServices(false);
			}
		}
		
		$scope.removeServiceDtlsService = function(index){
			$scope.serviceDtlsServiceList.splice(index,1);
			$scope.calculateSummaryAmt();
		}
		
		//---------------------Group Wise Cap Details Section -----------------------------
		$scope.addGroupDetails = function(){
			if($scope.speciality.specialityId == '' || typeof($scope.speciality.specialityId) == "undefined"){
				growl.error("Please select a group",{
					title : ERROR_MSG
				});
				return;
			}
			var departmentCapAmount = '';
			var subDepartmentCapAmount = '';
			if($scope.subSpeciality.subSpecialityId != '' && typeof($scope.subSpeciality.subSpecialityId) != "undefined")
				subDepartmentCapAmount = $rootScope.parseFloatNum(0);
			else
				departmentCapAmount = $rootScope.parseFloatNum(0);
			
			var temp = {departmentId:$scope.speciality.specialityId, subDepartmentId:$scope.subSpeciality.subSpecialityId,departmentCapAmount:departmentCapAmount,subDepartmentCapAmount:subDepartmentCapAmount,
						groupDesc:$scope.speciality.specialityName,subGroupDesc:$scope.subSpeciality.subSpecialityName};
			
			$scope.groupDetailsList.push(temp);
			$scope.initSubSpeciality();
			$scope.initSpeciality();
			
		}
		
		$scope.removeGroupDetails = function(index,obj){
			$scope.groupDetailsList.splice(index,1);
			var tempList = angular.copy($scope.groupWiseIncExcList);
			var value = {};
			for (var i=tempList.length; i--; ) {
				value = tempList[i];
				if(value.specialityId == obj.departmentId){
					$scope.groupWiseIncExcList.splice(i,1);
				}
			}
			$scope.calculateSummaryAmt();	
		}
		
		$scope.removeIncExGrpServices = function(index){
			$scope.groupWiseIncExcList.splice(index,1);
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
				$scope.packageBedCategoryList.push(obj);
			}else{
				growl.error("Please select bed category",{
					title : ERROR_MSG
				});
			}
		}
		
		$scope.removeBedCategory = function(index){
			$scope.packageBedCategoryList.splice(index);
		}
		
		// consumable details
		$scope.addConsumableItems = function(){
			$scope.consumableDetailsList = [];
			angular.forEach($scope.searchItemList,function(value,index){
				if(value.isSelected){
					value.qty = 0;
					$scope.consumableDetailsList.push(value);
				}
			});
		}
		
		$scope.removeConsumableDetails = function(index){
			$scope.consumableDetailsList.splice(index,1);
			$scope.calculateSummaryAmt();
		}
		
		//category wise consumables
		$scope.addCategoryWiseConsumableProduct = function(catWise){
			var productCategory = "";
			angular.forEach($scope.productCategoryList,function(value,index){
				if(value.id == catWise.productCategoryId)
					productCategory = value.category;
			});
			var temp = {productCategoryId:catWise.productCategoryId, category:productCategory, qty:0};
			$scope.categoryWiseConsumables.push(temp)
		}
		
		$scope.removeCategoryWiseConsumableProduct = function(index){
			$scope.categoryWiseConsumables.splice(index,1);
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
		
		//consumable wise amounts
		$scope.addConsumableWiseProduct = function(consumWise){
			var productCategory = "";
			angular.forEach($scope.productCategoryList,function(value,index){
				if(value.id == consumWise.productCategoryId)
					productCategory = value.category;
			});
			var temp = {productCategoryId:consumWise.productCategoryId, category:productCategory, qty:0};
			$scope.consumableWiseProductList.push(temp)
		}
		
		$scope.addConsumableWiseIncExc = function(){
			var itemIds = [];
			angular.forEach($scope.consumableWiseIncExcItemList,function(value,index){
				itemIds.push(value.itemId);
			});
			angular.forEach($scope.searchItemList,function(value,index){
				if(value.isSelected){
					if(itemIds.indexOf(value.itemId) == -1){
						value.isInclusiveService = $scope.isSetSecondTabLevel(2) ? 1 : 2;
						value.isServiceItem = 2;
						$scope.consumableWiseIncExcItemList.push(angular.copy(value));
					}
				}
			});
			itemFactory.selectAllItems(false);
		}
		
		$scope.removeConsumableWiseProduct = function(index){
			$scope.consumableWiseProductList.splice(index,1);
			$scope.calculateSummaryAmt();
		}
		
		$scope.removeConsumIncExcItem = function(index){
			$scope.consumableWiseIncExcItemList.splice(index,1);
		}
		
		//----------- Calculate Summary Amounts -----------------
		$scope.calculateSummaryAmt = function(){
			//calculate Service Wise Summary 
			var temp = parseFloat(0);
			angular.forEach($scope.serviceDtlsServiceList, function(value,index){
				temp = temp + parseFloat(value.total);
			});
			$scope.summary.serviceSummary = $rootScope.parseFloatNum(temp);
			
			//calculate group wise cap amount
			temp = parseFloat(0);
			angular.forEach($scope.groupDetailsList, function(value,index){
				var subDepartmentCapAmount = isNaN(value.subDepartmentCapAmount) || value.subDepartmentCapAmount == "" || typeof(value.subDepartmentCapAmount) == "undefined" ? parseFloat(0) : value.subDepartmentCapAmount;
				var departmentCapAmount = isNaN(value.departmentCapAmount) || value.departmentCapAmount == "" || typeof(value.departmentCapAmount) == "undefined" ? parseFloat(0) : value.departmentCapAmount;
				temp = parseFloat(temp) + parseFloat(subDepartmentCapAmount) + parseFloat(departmentCapAmount);
			});
			$scope.summary.groupWiseSummary =  $rootScope.parseFloatNum(temp);
			
			//calculate consumable wise cap amount
			temp = parseFloat(0);
			angular.forEach($scope.consumableWiseProductList, function(value,index){
				var productCateroyCapAmount = isNaN(value.productCateroyCapAmount) || value.productCateroyCapAmount == "" || typeof(value.productCateroyCapAmount) == "undefined" ? parseFloat(0) : value.productCateroyCapAmount;
				temp = parseFloat(temp) + parseFloat(productCateroyCapAmount);
			});
			$scope.summary.consumWiseSummary = $rootScope.parseFloatNum(temp);
			
			$scope.summary.grandTotal = $rootScope.parseFloatNum(parseFloat($scope.summary.serviceSummary) + parseFloat($scope.summary.groupWiseSummary) + parseFloat($scope.summary.consumWiseSummary));
			
			//package cost
			switch($scope.packageObj.isMarkupDown){
			case "1" :
				var amount = ($scope.summary.grandTotal/100) * (isNaN($scope.packageObj.markupDownInPercentage) ? 0 : parseFloat($scope.packageObj.markupDownInPercentage));
				$scope.packageObj.packageCost = $rootScope.parseFloatNum(parseFloat($scope.summary.grandTotal) + parseFloat(amount));
				break;
			case "2" :
				var amount = ($scope.summary.grandTotal/100) * (isNaN($scope.packageObj.markupDownInPercentage) ? 0 : parseFloat($scope.packageObj.markupDownInPercentage));
				$scope.packageObj.packageCost = $rootScope.parseFloatNum($scope.summary.grandTotal - amount);
				break;	
			}
			
			//package price
			switch($scope.packageObj.roundOffPositNeg){
			case "1":
				$scope.packageObj.packagePrice = $rootScope.parseFloatNum(parseFloat($scope.packageObj.packageCost) + (isNaN($scope.packageObj.manualRoundoffAmount) ? 0 : parseFloat($scope.packageObj.manualRoundoffAmount)));
				break;
			case "2":
				$scope.packageObj.packagePrice = $rootScope.parseFloatNum(parseFloat($scope.packageObj.packageCost) - (isNaN($scope.packageObj.manualRoundoffAmount) ? 0 : parseFloat($scope.packageObj.manualRoundoffAmount)));
				break;	
			}
		}
		
		$scope.savePackageWithCap = function(){
			var listTPackageServicesDetailsDto = [];
			//calculate apportioned price
			angular.forEach($scope.serviceDtlsServiceList,function(value,index){
				var apportionedPrice = (parseFloat(value.rate) / 100) * parseFloat($scope.packageObj.markupDownInPercentage);
				switch($scope.packageObj.isMarkupDown){
				case "1":
					apportionedPrice = parseFloat(value.rate) + parseFloat(apportionedPrice);
					break;
				case "2":
					apportionedPrice = parseFloat(value.rate) - parseFloat(apportionedPrice);
					break;
				}
				var temp = {
						serviceId : value.serviceMasterId,
						servicePrice : parseFloat(value.rate),
						apportionedPrice : apportionedPrice,
						isServiceItem : 1,
						numberToBeUse : value.qty,
						createdBy : $scope.userId
					};
				listTPackageServicesDetailsDto.push(temp);
			});
			
			//package cap details for group wise cap
			var listTPackageGroupWiseCapDetailsDto = [];
			angular.forEach($scope.groupDetailsList,function(value,index){
				var temp = {
						departmentId : 	value.departmentId,
						subDepartmentId : value.subDepartmentId,
						departmentCapAmount : value.departmentCapAmount,
						subDepartmentCapAmount : value.subDepartmentCapAmount,
						isServiceItem : 1,
						createdBy:$scope.userId
				};
				listTPackageGroupWiseCapDetailsDto.push(temp);
			});
			
			//package group wise inclusion exclusion details
			var listGroupWiseIncExc = [];
			angular.forEach($scope.groupWiseIncExcList,function(value,index){
				var temp = {
						serviceId : value.serviceMasterId,
						isInclusiveService : value.isInclusiveService,
						isServiceItem : 1,
						createdBy:$scope.userId
				};
				listGroupWiseIncExc.push(temp);
			});
			
			//bed category details
			var listTPackageBedCategoryDetailDto = [];
			angular.forEach($scope.packageBedCategoryList,function(value,index){
				var temp = {
						bedCategoryId : value.bedCategoryId,
						applicableDays : value.applicableDays,
						createdBy:$scope.userId
				};
				listTPackageBedCategoryDetailDto.push(temp);
			});
			
			//consumable details
			var listTPackageConsumableDetailsDto = [];
			angular.forEach($scope.consumableDetailsList,function(value,index){
				var temp = {
						numberToBeUse : value.qty,
						createdBy:$scope.userId,
						isServiceItem : 2,
						itemId : value.itemId
				}
				listTPackageConsumableDetailsDto.push(temp);
			});
			
			//category wise consumable details
			var listTPackageCategoryWiseConsumableDetailsDto = [];
			angular.forEach($scope.categoryWiseConsumables,function(value,index){
				var temp = {
						createdBy:$scope.userId,
						categoryId : value.productCategoryId,
						numberToBeUse : value.qty 
				}
				listTPackageCategoryWiseConsumableDetailsDto.push(temp);
			})
			
			//category wise exclusion 
			var listCategoryWiseExc = [];
			angular.forEach($scope.categoryWiseExclusionItemsList,function(value,index){
				var temp = {
						createdBy:$scope.userId,
						isInclusiveService : 2,
						isServiceItem : 2,
						itemId : value.itemId,
				}
				listCategoryWiseExc.push(temp);
			});
			
			//consumable wise product details
			var listTPackageConsumableWiseCapDetailsDto = []
			angular.forEach($scope.consumableWiseProductList,function(value,index){
				var temp = {
						createdBy:$scope.userId,
						isServiceItem : 2,
						productCateroyId : value.productCategoryId,
						productCateroyCapAmount : value.productCateroyCapAmount
				}
				listTPackageConsumableWiseCapDetailsDto.push(temp);
			});
			
			//consumable wise inc exc details
			var listConsumableWiseIncExc = [];
			angular.forEach($scope.consumableWiseIncExcItemList,function(value,index){
				var temp = {
						createdBy:$scope.userId,
						isInclusiveService : value.isInclusiveService,
						isServiceItem : 2,
						itemId : value.itemId
				}
				listConsumableWiseIncExc.push(temp);
			});
			
			$scope.packageObj.createdBy = $scope.userId;
			
			var param = {listTPackageServicesDetailsDto:listTPackageServicesDetailsDto,listTPackageGroupWiseCapDetailsDto:listTPackageGroupWiseCapDetailsDto,listGroupWiseIncExc:listGroupWiseIncExc,
					listTPackageBedCategoryDetailDto:listTPackageBedCategoryDetailDto,orgId:$scope.orgId,unitId:$scope.unitId,mPackageMasterDto:$scope.packageObj,listTPackageConsumableDetailsDto:listTPackageConsumableDetailsDto,
					listTPackageCategoryWiseConsumableDetailsDto:listTPackageCategoryWiseConsumableDetailsDto,listCategoryWiseExc:listCategoryWiseExc,listTPackageConsumableWiseCapDetailsDto:listTPackageConsumableWiseCapDetailsDto,
					listConsumableWiseIncExc:listConsumableWiseIncExc}
			
			$rootScope.startLoader();
			var URI = BASE_URL + ROOT_URL + "/api/packages/packagewithcap";
			BillingGenericService.serviceAction(METHOD_POST,URI, param,NOTIFICATION_MSG_STATUS_TRUE).then(function(response) {
				if (response.data.status == "success")
					$scope.init();
				
				$rootScope.stopLoader();
			});
		}
			
	}catch(e){
		console.log(e.message);
	}
	
}]);