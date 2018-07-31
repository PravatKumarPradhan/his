angular.module("myApp").controller("addNewEHCpackageController", [ '$scope','$rootScope','BillingGenericService','$cookies','$state','growl','servicesFactory',
	function($scope,$rootScope,BillingGenericService,$cookies,$state,growl,servicesFactory){
	try{
		$rootScope.loginpage = true;
		
		$scope.init = function(){
			$scope.searchServiceList = [];
			$scope.specialityList = [];
			$scope.subSpecialityList = [];
			$scope.searchServObj = {};
			$scope.packageObj = {}
			servicesFactory.initServiceSearch();
			$scope.selectedServiceList = [];
			$scope.initPackageObj();
			$scope.serviceWiseSummary = 0;
			$scope.grandTotal = 0;
			$scope.packageService = "";
		}
		
		$scope.initPackageObj = function(){
			$scope.packageObj = {packageTypeId:"",sexId:"",minAge:"",maxAge:"",tariffId:"",paymentEntitlementTypeId:"",patientTypeId:"",serviceId:"",markupDownInPercentage:"",isMarkupDown:"1",roundOffPositNeg:"1"}
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
		servicesFactory.setScope();
		
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
	    
		/** Service Autocomplete List * */
		$scope.getPackageServiceList = function(searchKeyword)
		{
				var data = "";
				var URI = BASE_URL + ROOT_URL + "/Unit/Service/EHCpackage/autoCompleteServices/"+$scope.orgId+"/"+$scope.unitId+"/"+searchKeyword;
				return BillingGenericService.serviceAction("GET", URI,data).then(function(response) {
					return response.data.listObject;
				});
		}	
	    
		$scope.selectPackageService = function($item, $model,$label)
		{
	      $scope.packageObj.serviceId = $item.id;
        }
		
		$scope.addServices = function(){
			$scope.selectedServiceList = [];
			angular.forEach($scope.searchServiceList,function(value,key){
				if(value.isSelected){
					value.qty = 0;
					value.total = 0;
					$scope.selectedServiceList.push(value);
				}
			});
			$scope.calculatePackageAmounts();
		}
		
		$scope.removeSelectedService = function(index){
			$scope.selectedServiceList.splice(index,1);
			$scope.calculatePackageAmounts();
		}
		
		$scope.calculatePackageAmounts = function(){
			var total = 0;
			$scope.packageObj.packagePrice = $rootScope.parseFloatNum(0);
			angular.forEach($scope.selectedServiceList,function(value,index){
				total = parseFloat(total) + (isNaN(value.total) ? 0 : parseFloat(value.total));
			});
			
			$scope.serviceWiseSummary = $rootScope.parseFloatNum(total);
			$scope.grandTotal = $rootScope.parseFloatNum(total);
			
			//package cost
			switch($scope.packageObj.isMarkupDown){
			case "1" :
				var amount = ($scope.grandTotal/100) * (isNaN($scope.packageObj.markupDownInPercentage) ? 0 : parseFloat($scope.packageObj.markupDownInPercentage));
				$scope.packageObj.packageCost = $rootScope.parseFloatNum(parseFloat($scope.grandTotal) + parseFloat(amount));
				break;
			case "2" :
				var amount = ($scope.grandTotal/100) * (isNaN($scope.packageObj.markupDownInPercentage) ? 0 : parseFloat($scope.packageObj.markupDownInPercentage));
				$scope.packageObj.packageCost = $rootScope.parseFloatNum($scope.grandTotal - amount);
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
		
	}catch(e){
		console.log(e.message);
	}
	
	$scope.saveEhcPackage = function(){
		var listTPackageServicesDetailsDto = [];
		angular.forEach($scope.selectedServiceList,function(value,index){
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
		});
		$scope.packageObj.createdBy = $scope.userId;
		var param = {orgId:$scope.orgId,unitId:$scope.unitId,mPackageMasterDto:$scope.packageObj,listTPackageServicesDetailsDto:listTPackageServicesDetailsDto};
		
		$rootScope.startLoader();
		var URI = BASE_URL + ROOT_URL + "/api/packages/ehc";
		BillingGenericService.serviceAction(METHOD_POST,URI, param,NOTIFICATION_MSG_STATUS_TRUE).then(function(response) {
			if (response.data.status == "success")
				$scope.init();
			$rootScope.stopLoader();
		});
		
	}
	
}]);