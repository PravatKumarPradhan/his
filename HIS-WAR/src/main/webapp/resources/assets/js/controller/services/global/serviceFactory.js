angular.module("myApp").factory('servicesFactory',['$rootScope','BillingGenericService','growl',function($rootScope,BillingGenericService,growl){
	var scope = angular.element(document.getElementById('uiView')).scope();
	
	scope.setScope = function(){
		scope = angular.element(document.getElementById('uiView')).scope();
		return scope;
	}
	
	scope.searchServices = function(searchObj){
		var packageObj = scope.packageObj;
		if(typeof(scope.packageObj) != "undefined"){
		if(scope.packageObj.packageTypeId != "" && typeof(scope.packageObj.packageTypeId) != "undefined"){
			if(scope.packageObj.packageTypeId != "4" ){
				if(packageObj.paymentEntitlementTypeId == '' || packageObj.patientTypeId == '' || packageObj.tariffId == ''){
					growl.error("Please Payment Entitlement, PatientType and Tariff",{
						title : ERROR_MSG
					});
					return;
				}
			}
			searchObj.paymentEntitlementId = packageObj.paymentEntitlementTypeId;
			searchObj.patientTypeId = packageObj.patientTypeId;
			searchObj.tariffId = packageObj.tariffId;
		}
		}
		var URI = BASE_URL + ROOT_URL + "/api/packages/services/search";
		searchObj.orgId = scope.orgId;
		BillingGenericService.serviceAction(METHOD_POST,URI, searchObj,NOTIFICATION_MSG_STATUS_FALSE).then(function(response) {
			scope.searchPatientList = [];
			if (response.data.status == "success")
				scope.searchServiceList = response.data.listObject;
			searchObj = {};
		});
	}
	
	scope.initServiceSearch = function(){
		scope.setScope();
		var URI = BASE_URL + ROOT_URL + "/adt/getActiveSpecialityList";
		var obj = {organizationId:scope.orgId, unitId:scope.unitId};
		BillingGenericService.serviceAction(METHOD_POST,URI, obj,NOTIFICATION_MSG_STATUS_FALSE).then(function(response) {
			scope.searchPatientList = [];
			if (response.data.status == "success")
				scope.specialityList = response.data.listObject;
		});
	}
	
	scope.getSubSpecialityBySpecialityId = function(specialityId){
		if(specialityId != ""){
		var URI = BASE_URL + ROOT_URL + "/adt/subspeciality/"+specialityId;
		BillingGenericService.serviceAction(METHOD_GET,URI, null,NOTIFICATION_MSG_STATUS_FALSE).then(function(response) {
			scope.searchPatientList = [];
			if (response.data.status == "success")
				scope.subSpecialityList = response.data.listObject;
		});
		}
	}
	
	scope.selectAllServices = function(flag){
			angular.forEach(scope.searchServiceList,function(value,key){
				value.isSelected = flag;
			});
		}
	
	scope.setOrgUnit = function(orgId,unitId){
		scope.orgId = orgId;
		scope.unitId = unitId;
	}
	
	
	return scope;
}]);