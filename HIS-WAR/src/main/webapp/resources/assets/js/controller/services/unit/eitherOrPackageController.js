angular.module("myApp").controller("eitherOrPackageController", [ '$scope','$rootScope','BillingGenericService','$cookies','$state','growl',
	function($scope,$rootScope,BillingGenericService,$cookies,$state,growl){
	try{
		$rootScope.loginpage = true;
		
		$scope.init = function(){
		}
		var cookieObject = $cookies.getObject('cookieObject');
		if (cookieObject == undefined) {
			$state.go('login');
			return;
		}
		$scope.unitId = cookieObject.unitId;
		$scope.orgId = cookieObject.organizationId;
		$scope.userId = 1;
		//$scope.init();
		
		
		$scope.getPackageTypeList = function(orgId,unitId) {
		    var data = "";
		    var URI = BASE_URL + ROOT_URL + "/Unit/Service/EHCpackage/getPackgeType/"+orgId+"/"+unitId;
		    BillingGenericService.serviceAction("GET", URI, data).then(function(response){
		     $scope.packageTypeList = response.data.listObject;
		    });
		  }
		$scope.getPackageTypeList($scope.orgId,$scope.unitId);
		
		  $scope.init = function(){
			  $scope.initSearchPackageObj();
			  $scope.searchPackageList = [];
		  }
		  $scope.initSearchPackageObj = function(){
			  $scope.searchPackageObj =$scope.searchPackageObj = {orgId:$scope.orgId,unitId:$scope.unitId,serviceCode:"",serviceDescription:"",packagetypeId:""};
		  }
		  $scope.init();
		  
		  $scope.getSearchServicesList = function(searchPackageObj) {
			  var param = {orgId:$scope.orgId,unitId:$scope.unitId,serviceCode:searchPackageObj.serviceCode,serviceDescription:searchPackageObj.serviceDescription,packagetypeId:searchPackageObj.packagetypeId};
			    var URI = BASE_URL + ROOT_URL + "/Unit/Service/EHCpackage/autoCompletePackageName"
			    BillingGenericService.serviceAction(METHOD_POST,URI,param,NOTIFICATION_MSG_STATUS_FALSE).then(function(response) {
					if (response.data.status == "success"){
						 $scope.searchPackageList = response.data.listObject;
					}
				});
			  }
		  
	}catch(e){
		console.log(e.message);
	}
	
	
	
	
	
	
}]);