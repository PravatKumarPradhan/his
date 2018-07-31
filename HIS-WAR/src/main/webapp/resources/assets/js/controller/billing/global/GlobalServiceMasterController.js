/**
 * @author Nikhil
 * @date 13-11-2017
 * 		Controller to handle Organization Level Service Master
 */
angular.module("myApp").controller("OrganizationServiceMasterController", [ '$scope','$rootScope','BillingGenericService','GenericService','$sessionStorage','PagerService','promiseFactory','$cookies',
	function($scope,$rootScope,BillingGenericService,GenericService,$sessionStorage,PagerService,promiseFactory,$cookies){
	var cookieObject = $cookies.getObject('cookieObject');
	if(cookieObject == undefined){
		$state.go('login');
		return;
	}
	$scope.unitId = cookieObject.unitId;
	$scope.organizationId = cookieObject.organizationId;
	$scope.specialityMasterList=[];
	$scope.subSpecialityMasterList=[];
	
	/**Variables for pagination */
	$scope.offset = 0;
	$scope.noOfRecordsPerPage = 10;
	$scope.organizationServiceMasterList = [];
	$scope.totalNoOfRecords;
	$scope.searchKeyword;
	$scope.commonListCount;
	$scope.pager = {};
	$scope.page;
	
	
	$scope.initOrganizationServiceMaster = function(){
		$scope.organizationServiceMasterObj = {
				specialityId        : "",
				subSpecialityId     :"",
				organizationId      :$scope.organizationId,
				serviceStandardName :"",
				status              :""
				//codification    :"",
				//serviceStandardName :"",
				//serviceCode : "",
		}
	}
	$scope.initOrganizationServiceMaster();
	
	//------------ Save Organization Service Master ---------------//
	$scope.saveOrganizationServiceMaster= function(){
		try {
			$rootScope.startLoader();
			var URI = BASE_URL + ROOT_URL + BILLING + SAVE_ORGANIZATION_SERVICE_MASTER;
			BillingGenericService.serviceAction(METHOD_POST,URI, $scope.organizationServiceMasterObj,NOTIFICATION_MSG_STATUS_TRUE).then(function(response){
				if(response.data.status == "success"){
					$scope.initOrganizationServiceMaster();
					$scope.initOrganizationServiceMasterList($scope.organizationId, $scope.offset, $scope.noOfRecordsPerPage);
				}
			});
		}catch (e) {
			console.log("Exception",e.message);
		}
	}
	
	//------------ Get Speciality Master List ------------//
	$scope.getSpecialityMasterList = function(){
		try{
			var URI = BASE_URL + ROOT_URL + GETACTIVESPECIALITY;
			var obj = {
						organizationId : $scope.organizationId,
						unitId         :$scope.unitId
				    }
			BillingGenericService.serviceAction(METHOD_POST,URI, obj, NOTIFICATION_MSG_STATUS_FALSE).then(function(response){
				console.log(response);
				if(response.data.status == "success")
					$scope.specialityMasterList = response.data.listObject;
			});
		}catch (e) {
			console.log("Exception",e.message);
		}
	}
	$scope.getSpecialityMasterList();
	
	//------------ Get SubSpeciality By SpecialityId ----------//
	$scope.getSubSpecialityBySpecialityId = function(specialityId){
		$scope.organizationServiceMasterObj.subSpecialityId="";
		var URI = BASE_URL + ROOT_URL + GET_SUBSPECIALITY_BY_SPECIALITY_ID;
		var obj = {
					organizationId : $scope.organizationId,
					unitId         :$scope.unitId,
					specialityId   :specialityId
			    }
		BillingGenericService.serviceAction(METHOD_POST,URI, obj, NOTIFICATION_MSG_STATUS_FALSE).then(function(response){
			if(response.data.status == "success")
				$scope.subSpecialityMasterList = response.data.listObject;
		});
	}
	
	//------------ Get Service Master By Id -----------------//
	$scope.getOrganizationServiceMasterById = function(serviceMasterId){
		var URI = BASE_URL + ROOT_URL + BILLING + GET_ORGANIZATION_SERVICE_MASTER_BY_ID;
		var obj = {
				serviceMasterId: serviceMasterId,
				organizationId : $scope.organizationId,
		}
		BillingGenericService.serviceAction(METHOD_POST,URI, obj, NOTIFICATION_MSG_STATUS_FALSE).then(function(response){
			if(response.data.status == "success")
				$scope.getSubSpecialityBySpecialityId(response.data.object.specialityId);
				$scope.organizationServiceMasterObj = angular.copy(response.data.object);
			
		});
		$scope.saveBtnFlag = false;
	}

	//------------ Change Organization ServiceMaster Status---------------//
	$scope.changeOrganizationServiceMasterStatus = function(serviceMasterId, status){
		var URI = BASE_URL + ROOT_URL + BILLING + CHANGE_ORGANIZATION_SERVICE_MASTER_STATUS;
		var obj = {
				serviceMasterId : serviceMasterId,
				organizationId  : $scope.organizationId,
				status          : status
		}
		BillingGenericService.serviceAction(METHOD_PUT,URI, obj, NOTIFICATION_MSG_STATUS_TRUE).then(function(response){
			if(response.data.status == "success")
				$scope.initOrganizationServiceMaster();
		});
	}
	//------------ Update Service Master -----------------//
	
	//======================= Service Master Pagination (Start)=====================//
	$scope.setNoOfRecords = function() {
		$scope.initOrganizationServiceMasterList($scope.organizationId, $scope.offset, $scope.noOfRecordsPerPage);
	};
	
	$scope.initOrganizationServiceMasterList = function(orgId, offset, noOfRecordsPerPage){
		$rootScope.startLoader();
		var obj = "";
		offset = offset != null ? offset : 0;
		noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage : 10;
		var URI_TO_GET_SERVICE_MASTER_LIST = BASE_URL + ROOT_URL + BILLING + GET_ORGANIZATION_SERVICE_MASTER_LIST + SLASH + orgId + SLASH + offset + SLASH + noOfRecordsPerPage;
		var URI_TO_GET_SERVICE_MASTER_COUNT = BASE_URL + ROOT_URL + BILLING + GET_ORGANIZATION_SERVICE_MASTER_COUNT + SLASH + orgId;
		promiseFactory.setPromises(URI_TO_GET_SERVICE_MASTER_LIST, URI_TO_GET_SERVICE_MASTER_COUNT, "GET", "GET").then(function(response) 
		{
			$rootScope.stopLoader();
			$scope.organizationServiceMasterList = response[0].listObject;
			$scope.commonListCount = response[1].object;
			$scope.setPage(1, false);
		});
	}
	
	$scope.getOrganizationlistServiceMaster = function(orgId, offset, noOfRecordsPerPage){
		$rootScope.startLoader();
		var obj = "";
		offset = offset != null ? offset : 0;
		noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage : 10;
		var URI =  BASE_URL + ROOT_URL + BILLING + GET_ORGANIZATION_SERVICE_MASTER_LIST + SLASH + orgId + SLASH + offset + SLASH + noOfRecordsPerPage;
		console.log("URI", URI);
		BillingGenericService.serviceAction(METHOD_GET, URI, obj,NOTIFICATION_MSG_STATUS_FALSE).then(function(response) {
			$rootScope.stopLoader();
			$scope.organizationServiceMasterList = response.data.listObject;
			console.log($scope.organizationServiceMasterList);
		});
	}
	
	$scope.setPage = function(page, flag) {
		if (page < 1 || page > $scope.pager.totalPages)
		{
			return;
		}
		$scope.pager = PagerService.GetPager($scope.commonListCount, page, $scope.noOfRecordsPerPage);
		if (flag)
		{
			$scope.getOrganizationlistServiceMaster($scope.organizationId, $scope.pager.startIndex, $scope.pager.pageSize);
		}
	}
	$scope.initOrganizationServiceMasterList($scope.organizationId, $scope.offset, $scope.noOfRecordsPerPage);
	//=================== Service Master Pagination (End)====================//
}]);