angular.module("myApp").controller("addMultiencounterPackage", [ '$scope','$rootScope','BillingGenericService','$cookies','$state','growl','servicesFactory','itemFactory',
	function($scope,$rootScope,BillingGenericService,$cookies,$state,growl,servicesFactory,itemFactory){
	try{
		$rootScope.loginpage = true;
		
		var cookieObject = $cookies.getObject('cookieObject');
		if (cookieObject == undefined) {
			$state.go('login');
			return;
		}
		$scope.unitId = cookieObject.unitId;
		$scope.orgId = cookieObject.organizationId;
		$scope.userId = 1;
		
		$scope.initPackageObj = function(){
			$scope.packageObj = {packageTypeId:"",sexId:"",minAge:"",maxAge:"",tariffId:"",paymentEntitlementTypeId:"",patientTypeId:"",serviceId:"",markupDownInPercentage:"",isMarkupDown:"1",roundOffPositNeg:"1",visitTypeId:"",billingBedCategoryId:"",serviceId:"",validityPeriodIndays:0,noOfEncounters:0,packagePrice:0}
		}
		$scope.initSummary = function(){
			$scope.summary = {serviceSummary:$rootScope.parseFloatNum(0),grandTotal:$rootScope.parseFloatNum(0)};
		}
		$scope.init = function(){
			$scope.initPackageObj();
			$scope.selectedServiceList = [];
			$scope.searchServObj = {};
			$scope.serviceWiseDetailsList = [];
			$scope.itemWiseDetailsList = [];
			$scope.initSummary();
			$scope.packageService = "";
			$scope.itemObj = {};
			servicesFactory.setScope();
			servicesFactory.initServiceSearch();
			itemFactory.initItemFactory();
			 itemFactory.setScope();
		}
		$scope.init();
		servicesFactory.setOrgUnit($scope.orgId,$scope.unitId);
		servicesFactory.setScope();
		itemFactory.setScope();
		
		$scope.getPackageTypeList = function(orgId,unitId) {
			var URI = BASE_URL + ROOT_URL + "/Unit/Service/EHCpackage/getPackgeType/"+orgId+"/"+unitId;
			BillingGenericService.serviceAction("GET", URI, null).then(function(response){
				$scope.packageTypeList = response.data.listObject;
			});
		}
		
		$scope.getPackageServiceList = function(searchKeyword){
			var URI = BASE_URL + ROOT_URL + "/Unit/Service/EHCpackage/autoCompleteServices/"+$scope.orgId+"/"+$scope.unitId+"/"+searchKeyword;
			return BillingGenericService.serviceAction("GET", URI,null).then(function(response) {
				return response.data.listObject;
			});
		}
		
		$scope.getTariffList = function(orgId,unitId) {
			var URI = BASE_URL + ROOT_URL + "/Unit/Service/EHCpackage/getTariff/"+orgId+"/"+unitId;
			BillingGenericService.serviceAction("GET", URI, null).then(function(response){
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
		
		$scope.getBedBillingCategoryList = function() {
		    var URI = BASE_URL + ROOT_URL + "/adt/getActiveBillingBedCategoryList/orgId/"+$scope.orgId+"/unitId/"+$scope.unitId;
		    BillingGenericService.serviceAction("GET", URI, null).then(function(response){
		     $scope.bedBillingCategoryList= response.data.listObject;
		    });
		  }
	   $scope.getBedBillingCategoryList();
		
		$scope.getPackageTypeList($scope.orgId,$scope.unitId);
		$scope.getTariffList($scope.orgId,$scope.unitId);
		
		$scope.selectPackageService = function($item, $model,$label)
		{
	      $scope.packageObj.serviceId = $item.id;
	    }
		
		$scope.firstTabLevel = 1 ;
		
		$scope.setFirstTabLevel= function(newTab)
		{
			$scope.firstTabLevel = newTab;
		};
		$scope.isSetFirstTabLevel = function(tabNum)
		{
			return $scope.firstTabLevel === tabNum;
		};
		
		$scope.addServices = function(){
			$scope.serviceWiseDetailsList = [];
			angular.forEach($scope.searchServiceList,function(value,key){
				if(value.isSelected){
					value.qty = 0;
					value.total = 0;
					$scope.serviceWiseDetailsList.push(value);
				}
			});
		}
		
		$scope.removeService = function(index){
			$scope.serviceWiseDetailsList.splice(index,1);
		}
		
		$scope.addItems = function(){
			$scope.itemWiseDetailsList = [];
			angular.forEach($scope.searchItemList,function(value,key){
				if(value.isSelected){
					value.qty = 1;
					$scope.itemWiseDetailsList.push(value);
				}
			});
		}
		
		$scope.removeItem = function(index){
			$scope.itemWiseDetailsList.splice(index,1);
		}
		
		$scope.calculateSummaryAmt = function(){
			//service wise summary
			var total = 0;
			$scope.packageObj.packagePrice = $rootScope.parseFloatNum(0);
			angular.forEach($scope.serviceWiseDetailsList,function(value,index){
				total = parseFloat(total) + (isNaN(value.total) ? 0 : parseFloat(value.total));
			});
			
			$scope.summary.serviceSummary = $rootScope.parseFloatNum(total);
			$scope.summary.grandTotal = $rootScope.parseFloatNum(total);
			
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
		
		$scope.saveMultiEncPackage = function(){
			var serviceWiseDetails = [];
			angular.forEach($scope.serviceWiseDetailsList,function(value,index){
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
						isServiceItem : 1,
						apportionedPrice : apportionedPrice,
						numberToBeUse : value.qty,
						createdBy : $scope.userId
					}
				serviceWiseDetails.push(temp);
			});
			
			var itemWiseDetails = [];
			angular.forEach($scope.itemWiseDetailsList,function(value,index){
				var temp = {
						itemId : value.itemId,
						isServiceItem : 2,
						numberToBeUse : value.qty,
						createdBy : $scope.userId
					}
				itemWiseDetails.push(temp);
			});
			
			$scope.packageObj.createdBy = $scope.userId;
			var param = {orgId:$scope.orgId,unitId:$scope.unitId,mPackageMasterDto:$scope.packageObj,serviceWiseDetails:serviceWiseDetails,itemWiseDetails:itemWiseDetails};
			
			$rootScope.startLoader();
			var URI = BASE_URL + ROOT_URL + "/api/packages/multiencounter";
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