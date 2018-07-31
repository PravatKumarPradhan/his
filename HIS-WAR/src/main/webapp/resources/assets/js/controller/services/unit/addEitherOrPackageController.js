angular.module("myApp").controller("addEitherOrPackageController", [ '$scope','$rootScope','BillingGenericService','$cookies','$state','growl','servicesFactory',
	function($scope,$rootScope,BillingGenericService,$cookies,$state,growl,servicesFactory){
	try{
		$rootScope.loginpage = true;
		
		$scope.initPackageObj = function(){
			$scope.packageObj = {packageTypeId:"",sexId:"",minAge:"",maxAge:"",tariffId:"",paymentEntitlementTypeId:"",patientTypeId:"",serviceId:"",markupDownInPercentage:"",isMarkupDown:"1",roundOffPositNeg:"1",visitTypeId:"",billingBedCategoryId:""}
		}
		
		$scope.initPackageGroupDtls = function(){
			$scope.packageGroup = {packageGroupName:"",maxPrice:$rootScope.parseFloatNum(0),minPrice:$rootScope.parseFloatNum(0),avgPrice:$rootScope.parseFloatNum(0),groupPrice:$rootScope.parseFloatNum(0),listTPackageServicesDetailsDto:[]};
			$scope.groupServices = [];
		}
		
		$scope.initViewPackageObj = function(){
			$scope.viewPackageGroupServices = [];
			$scope.viewPackageObj = {maxPrice:$rootScope.parseFloatNum(0),minPrice:$rootScope.parseFloatNum(0),avgPrice:$rootScope.parseFloatNum(0),groupPrice:$rootScope.parseFloatNum(0)};
		}
		
		$scope.initSummary = function(){
			$scope.summary = {packageGroupAmt:$rootScope.parseFloatNum(0),serviceGroupAmt:$rootScope.parseFloatNum(0),groupTotal:$rootScope.parseFloatNum(0)};
		}
		
		var cookieObject = $cookies.getObject('cookieObject');
		if (cookieObject == undefined) {
			$state.go('login');
			return;
		}
		$scope.unitId = cookieObject.unitId;
		$scope.orgId = cookieObject.organizationId;
		$scope.userId = 1;
		
		$scope.init = function(){
			$scope.searchServiceList = [];
			$scope.initPackageObj();
			servicesFactory.initServiceSearch();
			$scope.selectedServiceList = [];
			$scope.specialityList = [];
			$scope.subSpecialityList = [];
			$scope.searchServObj = {};
			$scope.groupServices = [];
			$scope.initPackageGroupDtls();
			$scope.listTPackageEitherorGroupDetailsDto = [];
			$scope.initViewPackageObj();
			$scope.listTPackageServicesDetailsDto = [];
			$scope.groupServAmounts = [];
			$scope.initSummary();
			$scope.packageService = "";
		}
		$scope.init();
		servicesFactory.setOrgUnit($scope.orgId,$scope.unitId);
		
		$scope.getPackageTypeList = function(orgId,unitId) {
			var data = "";
			var URI = BASE_URL + ROOT_URL + "/Unit/Service/EHCpackage/getPackgeType/"+orgId+"/"+unitId;
			BillingGenericService.serviceAction("GET", URI, data).then(function(response){
				$scope.packageTypeList = response.data.listObject;
			});
	}
	
	$scope.getTariffList = function(orgId,unitId) {
			var data = "";
			var URI = BASE_URL + ROOT_URL + "/Unit/Service/EHCpackage/getTariff/"+orgId+"/"+unitId;
			BillingGenericService.serviceAction("GET", URI, data).then(function(response){
				$scope.tariffList= response.data.listObject;
			});
	}
	
	$scope.getPaymentEntitlementList = function(tariffId) {
			var data = "";
			var URI = BASE_URL + ROOT_URL + "/Unit/Service/EHCpackage/getPaymentEntitlementByTariffId/"+$scope.orgId+"/"+$scope.unitId+"/"+tariffId;
			BillingGenericService.serviceAction("GET", URI, data).then(function(response){
				$scope.paymentEntitlementList= response.data.listObject;
			});
	}
	
	$scope.getPatientTypeList = function(tariffId) {
			var data = "";
			var URI = BASE_URL + ROOT_URL + "/Unit/Service/EHCpackage/getPatientTypeByTariffId/"+$scope.orgId+"/"+$scope.unitId+"/"+tariffId;
			BillingGenericService.serviceAction("GET", URI, data).then(function(response){
				$scope.patientTypeList= response.data.listObject;
			});
	}
	
	$scope.getPackageTypeList($scope.orgId,$scope.unitId);
	$scope.getTariffList($scope.orgId,$scope.unitId);
    
	$scope.getPackageServiceList = function(searchKeyword){
			var URI = BASE_URL + ROOT_URL + "/Unit/Service/EHCpackage/autoCompleteServices/"+$scope.orgId+"/"+$scope.unitId+"/"+searchKeyword;
			return BillingGenericService.serviceAction("GET", URI,null).then(function(response) {
				return response.data.listObject;
			});
	}	
    
	$scope.selectPackageService = function($item, $model,$label)
	{
      $scope.packageObj.serviceId = $item.id;
    }
	
	  $scope.getBedBillingCategoryList = function() {
		    var data = "";
		    var URI = BASE_URL + ROOT_URL + "/adt/getActiveBillingBedCategoryList/orgId/"+$scope.orgId+"/unitId/"+$scope.unitId;
		    BillingGenericService.serviceAction("GET", URI, data).then(function(response){
		     $scope.bedBillingCategoryList= response.data.listObject;
		    });
		  }
	  $scope.getBedBillingCategoryList();
		
	  	$scope.firstTabLevel = 1 ;
		
		$scope.setFirstTabLevel= function(newTab)
		{
			$scope.firstTabLevel = newTab;
		};
		$scope.isSetFirstTabLevel = function(tabNum)
		{
			return $scope.firstTabLevel === tabNum;
		};
		
	//group services	
	$scope.addGroupServices = function(){
		switch($scope.firstTabLevel){
		case 1:
			$scope.groupServices = [];
			$scope.groupServAmounts = [];
		angular.forEach($scope.searchServiceList,function(value,key){
			if(value.isSelected){
				$scope.groupServices.push(value);
				$scope.groupServAmounts.push(value.rate);
			}
		});
		$scope.calculateGroupAmounts();
		break;
		case 2:
			$scope.listTPackageServicesDetailsDto = [];
			angular.forEach($scope.searchServiceList,function(value,key){
			if(value.isSelected){
				value.qty = 0;
				value.total = 0;
				$scope.listTPackageServicesDetailsDto.push(value);
			}
		});	
		$scope.calculateSummaryAmounts();
		break;
		}
	}
	
	$scope.removeGroupServices = function(index){
		$scope.groupServices.splice(index,1);
		$scope.groupServAmounts.splice(index,1);
		$scope.calculateGroupAmounts();
	}
	
	$scope.calculateGroupAmounts = function(){
		var amount = parseFloat(0);
		angular.forEach($scope.groupServices,function(value,key){
			amount = amount + parseFloat(value.rate);
		});
		amount = $rootScope.parseFloatNum(amount);
		var avgPrice = amount/$scope.groupServices.length;
		$scope.packageGroup.maxPrice = $rootScope.parseFloatNum($scope.groupServAmounts.max());
		$scope.packageGroup.minPrice = $rootScope.parseFloatNum($scope.groupServAmounts.min());
		$scope.packageGroup.avgPrice = $rootScope.parseFloatNum(avgPrice);
		$scope.packageGroup.groupPrice = $rootScope.parseFloatNum(0);
		
	}
	
	$scope.addPackageGoup = function(packageGroup){
		if($scope.groupServices.length == 0){
			growl.error("Please add atleast one service",{
				title : ERROR_MSG
			});
			return;
		}
		var packageGroupObj = angular.copy(packageGroup);
		packageGroupObj.listTPackageServicesDetailsDto = $scope.groupServices;
		$scope.listTPackageEitherorGroupDetailsDto.push(packageGroupObj);
		$scope.initPackageGroupDtls();
		$scope.calculateSummaryAmounts();
	}
	
	$scope.viewPackageGroup = function(packageGroup){
		$scope.viewPackageGroupServices = packageGroup.listTPackageServicesDetailsDto;
		$scope.viewPackageObj = packageGroup;
	}
	
	$scope.removePackageGroup = function(index){
		$scope.listTPackageEitherorGroupDetailsDto.splice(index,1);
		$scope.calculateSummaryAmounts();
	}
	
	//service wise details
	$scope.removeService = function(index){
		$scope.listTPackageServicesDetailsDto.splice(index,1);
		$scope.calculateSummaryAmounts();
	}
	
	$scope.calculateSummaryAmounts = function(){
		//calculate package group summary 
		var temp = parseFloat(0);
		angular.forEach($scope.listTPackageEitherorGroupDetailsDto, function(value,index){
			temp = temp + parseFloat(value.groupPrice);
		});
		$scope.summary.packageGroupAmt = $rootScope.parseFloatNum(temp);
		
		//calculate Service Wise Summary 
		temp = parseFloat(0);
		angular.forEach($scope.listTPackageServicesDetailsDto, function(value,index){
			temp = temp + parseFloat(value.total);
		});
		$scope.summary.serviceGroupAmt = $rootScope.parseFloatNum(temp);
		
		$scope.summary.groupTotal = $rootScope.parseFloatNum(parseFloat($scope.summary.packageGroupAmt) + parseFloat($scope.summary.serviceGroupAmt));
		
		//package cost
		switch($scope.packageObj.isMarkupDown){
		case "1" :
			var amount = ($scope.summary.groupTotal/100) * (isNaN($scope.packageObj.markupDownInPercentage) ? 0 : parseFloat($scope.packageObj.markupDownInPercentage));
			$scope.packageObj.packageCost = $rootScope.parseFloatNum(parseFloat($scope.summary.groupTotal) + parseFloat(amount));
			break;
		case "2" :
			var amount = ($scope.summary.groupTotal/100) * (isNaN($scope.packageObj.markupDownInPercentage) ? 0 : parseFloat($scope.packageObj.markupDownInPercentage));
			$scope.packageObj.packageCost = $rootScope.parseFloatNum($scope.summary.groupTotal - amount);
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
	
	//save EitherOrPackage
	$scope.saveEitherOrPackage = function(){
		if($scope.listTPackageServicesDetailsDto.length == 0 && $scope.listTPackageEitherorGroupDetailsDto.length == 0){
			growl.error("Please add package group or service details",{
				title : ERROR_MSG
			});
			return;
		}
		
		var listTPackageServicesDetailsDto = [];
		//calculate apportioned price
		angular.forEach($scope.listTPackageServicesDetailsDto,function(value,index){
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
				}
			listTPackageServicesDetailsDto.push(temp);
		})
		$scope.packageObj.createdBy = $scope.userId;
		
		var listTPackageEitherorGroupDetailsDto = [];
		angular.forEach($scope.listTPackageEitherorGroupDetailsDto,function(value,index){
			var listServices =  value.listTPackageServicesDetailsDto;
			var finalServiceList = [];
			angular.forEach(listServices,function(service,servIndex){
				var temp = {
						serviceId : service.serviceMasterId,
						servicePrice : parseFloat(service.rate),
						apportionedPrice : parseFloat(service.rate),
						isServiceItem : 1,
						numberToBeUse : 1,
						createdBy : $scope.userId
					}
				finalServiceList.push(temp);
			});
			value.createdBy=$scope.userId;
			value.listTPackageServicesDetailsDto = finalServiceList;
			listTPackageEitherorGroupDetailsDto.push(value);
		});
		
		var param = {orgId:$scope.orgId,unitId:$scope.unitId,mPackageMasterDto:$scope.packageObj,listTPackageServicesDetailsDto:listTPackageServicesDetailsDto,listTPackageEitherorGroupDetailsDto:listTPackageEitherorGroupDetailsDto};
		$rootScope.startLoader();
		var URI = BASE_URL + ROOT_URL + "/api/packages/eitheror";
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