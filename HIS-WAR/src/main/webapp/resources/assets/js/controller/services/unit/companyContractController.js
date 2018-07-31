angular.module("myApp").controller("companyContractController", [ '$scope','$rootScope','BillingGenericService','$cookies','$state','growl',
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
		
		
		$scope.getPaymentEntitlementList = function() {
			var data = "";
			var URI = BASE_URL + ROOT_URL + "/global/paymententitlement/"+$scope.orgId;
			BillingGenericService.serviceAction("GET", URI, data).then(function(response){
				$scope.paymentEntitlementList= response.data.listObject;
				console.log("paymentEntitlementList",JSON.stringify($scope.paymentEntitlementList));
			});
	   }
		$scope.getPaymentEntitlementList();
		
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
					
					
		  $scope.init = function(){
			  $scope.initSearchComapnyContarctObj();
			  $scope.searchComapnyContarctList = [];
		  }
		  $scope.initSearchComapnyContarctObj = function(){
			 $scope.companyContarctObj = {orgId:$scope.orgId,unitId:$scope.unitId,paymentEntitlementId:"",companyId:"",associateCompanyId:"",tariffId:""};
		  }
		  $scope.init();
		  
		  $scope.getSearchServicesList = function(companyContarctObj) {
			  $scope.searchComapnyContarctList=[];
			  //var param = {orgId:$scope.orgId,unitId:$scope.unitId,paymentEntitlementId:"",companyId:"",associateCompanyId:"",tariffId:""};
			  var param = {orgId:$scope.orgId,unitId:$scope.unitId,paymentEntitlementId:$scope.companyContarctObj.paymentEntitlementId,companyId:$scope.companyContarctObj.companyId,associateCompanyId:$scope.companyContarctObj.associateCompanyId,tariffId:$scope.companyContarctObj.tariffId};
			    var URI = BASE_URL + ROOT_URL + "/unit/CompanyContract/searchCompanyContract"
			    BillingGenericService.serviceAction(METHOD_POST,URI,param,NOTIFICATION_MSG_STATUS_FALSE).then(function(response) {
					if (response.data.status == "success"){
						 $scope.searchComapnyContarctList = response.data.listObject;
						 console.log("searchComapnyContarctList",JSON.stringify($scope.searchComapnyContarctList));
					}
				});
			  }
		  $scope.getSearchServicesList();
		  
	}catch(e){
		console.log(e.message);
	}
	
	
	
	
	
}]);