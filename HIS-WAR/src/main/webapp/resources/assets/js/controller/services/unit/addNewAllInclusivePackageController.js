/**
 * Santosh
 * 04-06-2018
 */

angular.module("myApp").controller("addAllInclusivePackageController", [ '$scope','$rootScope','BillingGenericService','$cookies','$state','growl','servicesFactory',
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
			$scope.itemObj = {};
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
		
		
		$scope.getPackageTypeList = function(orgId,unitId) {
				var data = "";
				var URI = BASE_URL + ROOT_URL + "/Unit/Service/EHCpackage/getPackgeType/"+orgId+"/"+unitId;
				BillingGenericService.serviceAction("GET", URI, data).then(function(response){
					$scope.packageTypeList = response.data.listObject;
				});
		}
		
		$scope.getTariffList = function(orgId,unitId) {
				/*var data = "";
				var URI = BASE_URL + ROOT_URL + "/Unit/Service/EHCpackage/getTariff/"+orgId+"/"+unitId;
				BillingGenericService.serviceAction("GET", URI, data).then(function(response){
					$scope.tariffList= response.data.listObject;
				});*/
			$scope.tariffList = 1;
		}
		
		$scope.getPaymentEntitlementList = function() {
			var URI = BASE_URL + ROOT_URL + "/global/paymententitlement/"+$scope.orgId;
			BillingGenericService.serviceAction("GET", URI, {}).then(function(response){
				$scope.paymentEntitlementList= response.data.listObject;
			});
		}
		
		$scope.getPatientTypeList = function() {
			var URI = BASE_URL + ROOT_URL + "/global/patientcategory/"+$scope.orgId;
			BillingGenericService.serviceAction("GET", URI, null).then(function(response){
				$scope.patientTypeList= response.data.listObject;
			});
		}
		
		$scope.getPackageTypeList($scope.orgId,$scope.unitId);
		//$scope.getTariffList($scope.orgId,$scope.unitId);
		$scope.getPatientTypeList();
		$scope.getPaymentEntitlementList();
	    
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
		}
		
		$scope.removeSelectedService = function(index){
			$scope.selectedServiceList.splice(index,1);
		
		}
		
		
		
	}catch(e){
		console.log(e.message);
	}
	
	$scope.savePackage = function(){
		var listTPackageIncExcServiceDetailsDto = [];
		angular.forEach($scope.selectedServiceList,function(value,index){
			/*var apportionedPrice = (parseFloat(value.rate) / 100) * parseFloat($scope.packageObj.markupDownInPercentage);
			switch($scope.packageObj.isMarkupDown){
			case "1":
				apportionedPrice = parseFloat(value.rate) + parseFloat(apportionedPrice);
				break;
			case "2":
				apportionedPrice = parseFloat(value.rate) - parseFloat(apportionedPrice);
				break;
			}*/
			var temp = {
					serviceId : value.serviceMasterId,
					isInclusiveService : 2,
					isServiceItem : 1,
					orgId:1,
					orgUnitId:1,
					createdBy : $scope.userId,
					updatedBy : $scope.userId
				}
			listTPackageIncExcServiceDetailsDto.push(temp);
		});
		$scope.packageObj.orgId = $scope.orgId;
		$scope.packageObj.unitId = $scope.unitId;
		$scope.packageObj.createdBy = $scope.userId;
		$scope.packageObj.updatedBy = $scope.userId;
		$scope.packageObj.listTPackageIncExcServiceDetailsDto = listTPackageIncExcServiceDetailsDto;
		//var param = {reqDto:$scope.packageObj};
		console.log("Param: "+ JSON.stringify($scope.packageObj));
		$rootScope.startLoader();
		var URI = BASE_URL + ROOT_URL + "/api/packages/allInclusive";
		BillingGenericService.serviceAction(METHOD_POST,URI, $scope.packageObj,NOTIFICATION_MSG_STATUS_TRUE).then(function(response) {
			if (response.data.status == "success")
				$scope.init();
		});
		$rootScope.stopLoader();
	}
	
	/*First Level*/
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
	
}]);