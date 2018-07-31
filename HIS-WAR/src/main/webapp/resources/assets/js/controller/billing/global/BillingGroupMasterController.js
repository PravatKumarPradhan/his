/**
 * @author Nikhil
 * @date 17-11-2017
 * 		Controller to handle Billing Group Master(Unit Level)
 */
angular.module("myApp").controller("BillingGroupMasterController", [ '$scope','$rootScope','BillingGenericService','GenericService','$sessionStorage','PagerService','promiseFactory','$cookies','$state','promiseFactoryWithObject',
	function($scope,$rootScope,BillingGenericService,GenericService,$sessionStorage,PagerService,promiseFactory,$cookies,$state,promiseFactoryWithObject){
	var cookieObject = $cookies.getObject('cookieObject');
	if(cookieObject == undefined){
		$state.go('login');
		return;
	}
	$scope.unitId = cookieObject.unitId;
	$scope.organizationId = cookieObject.organizationId;
	$scope.specialityMasterList = [];
	$scope.chekedSpecialityList = [];
	$scope.selectedSpecialityList = [];
	$scope.tempSelectSpecialty = [];
	$scope.specialityMst = [];
	$scope.saveBtnFlag = true;
	/**Variables for pagination */
	$scope.offset = 0;
	$scope.noOfRecordsPerPage = 10;
	$scope.billingGroupMasterList = [];
	$scope.totalNoOfRecords;
	$scope.searchKeyword;
	$scope.commonListCount;
	$scope.pager = {};
	$scope.page;
	
	$scope.initBillingGroupMaster = function(){
		$scope.billingGroupMasterObj={
				billingGroupId  : "",
				billingGroup    : "",
				billingCode     : "",
				billingGroupDesc: "",
				status          : "A",
				createdDate     : "",
				createdBy       : "",
				updatedDate     : "",
				updatedBy       : "",
				unitId          : $scope.unitId,
				orgnisationId   : $scope.organizationId,
				listSpecialityMasterDto : []
		}
		
	}
	$scope.initBillingGroupMaster();
	
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
	
	function uncheckSpecialityList(){
		angular.forEach($scope.specialityMasterList,function(value,index){
			$scope.specialityMst[value.specialityId] = false;
		})
	}
	$scope.checkToSelectedSpeciality = function(obj){
		$scope.chekedSpecialityList.push(obj);
	}
	
	$scope.selectSpeciality = function(){
		$scope.selectedSpecialityList = angular.copy($scope.tempSelectSpecialty);
	}
	
	//--------------- Select Specuality --------------//
	$scope.addTempSpecialty = function(specialityId, specialtyName){
		var flag = false;
		angular.forEach($scope.tempSelectSpecialty,function(value,index){
			if(value.specialityId == specialityId){
				$scope.tempSelectSpecialty.splice(index,1);
				$scope.specialityMst[value.specialityId] = false;
				flag = true;
			}
		})
		if(!flag){
			var temp = {specialityId:specialityId,specialityName:specialtyName};
			$scope.tempSelectSpecialty.push(temp);
		}
	}
	
	//---------------- Create Billing Group Master ------------//
	$scope.createBillingGroupMaster =  function(){
		$scope.$broadcast('show-errors-check-validity');
		if ($scope.billingGroupMasterForm.$valid){
			$rootScope.startLoader();
			var URI = BASE_URL + ROOT_URL + BILLING + CREATE_BILLING_GROUP_MASTER;
			$scope.billingGroupMasterObj.listSpecialityMasterDto = angular.copy($scope.selectedSpecialityList);
			BillingGenericService.serviceAction(METHOD_POST,URI, $scope.billingGroupMasterObj, NOTIFICATION_MSG_STATUS_TRUE).then(function(response){
				console.log(response);
				if(response.data.status == "success"){
					$scope.initBillingGroupMaster();
					$scope.selectedSpecialityList = [];
					$scope.tempSelectSpecialty = [];
					uncheckSpecialityList();
					$scope.initBillingGroupMasterList($scope.offset, $scope.noOfRecordsPerPage);
				}
			});
		}
	}
	
	//-------------- Change Billing Group Master Status ---------------//
	$scope.changeBillingGroupStatus = function(billingGroupId, status){
		$rootScope.startLoader();
		var URI = BASE_URL + ROOT_URL + BILLING + CHANGE_BILLING_GROUP_MASTER_STATUS;
		var obj = {
					billingGroupId :billingGroupId,
					status         :status,
					orgnisationId  :$scope.organizationId,
					unitId         :$scope.unitId
		}
		BillingGenericService.serviceAction(METHOD_PUT,URI, obj, NOTIFICATION_MSG_STATUS_TRUE).then(function(response){
			console.log(response);
			if(response.data.status == "success"){
				$scope.initBillingGroupMaster();
			}
		});
	}
	
	//-------------- Get Billing Group Master By Id --------------//
	$scope.getBillingGroupMasterById = function(billingGroupId){
		$rootScope.startLoader();
		$scope.selectedSpecialityList=[];
		$scope.tempSelectSpecialty=[];
		uncheckSpecialityList();
		var URI = BASE_URL + ROOT_URL + BILLING + GET_BILLING_GROUP_MASTER_ID;
		var obj ={
			 	 orgnisationId :$scope.organizationId,
				 unitId :$scope.unitId,
				 billingGroupId :billingGroupId
			}
		BillingGenericService.serviceAction(METHOD_POST,URI, obj, NOTIFICATION_MSG_STATUS_FALSE).then(function(response){
			if(response.data.status == "success"){
				
				$scope.billingGroupMasterObj = response.data.listObject[0];
				angular.forEach(response.data.listObject, function(value, key) {
					if(value.specialityId != null){
						 var obj = {specialityId:value.specialityId, specialityName:value.specialityName};
						 $scope.selectedSpecialityList.push(obj);
						 $scope.tempSelectSpecialty.push(obj);
						 $scope.specialityMst[value.specialityId] = true;
					}
					});
				$scope.saveBtnFlag = false;
			}
		});
	}
	
	//-------------- Update Billing Group Master ---------------//
	$scope.updateBillingGroupMaster = function(){
		$scope.$broadcast('show-errors-check-validity');
		if ($scope.billingGroupMasterForm.$valid){
			$rootScope.startLoader();
			var URI = BASE_URL + ROOT_URL + BILLING + UPDATE_BILLING_GROUP_MASTER;
			$scope.billingGroupMasterObj.listSpecialityMasterDto = angular.copy($scope.selectedSpecialityList);
			BillingGenericService.serviceAction(METHOD_PUT,URI, $scope.billingGroupMasterObj, NOTIFICATION_MSG_STATUS_TRUE).then(function(response){
				console.log(response);
				if(response.data.status == "success"){
					$scope.initBillingGroupMaster();
					$scope.selectedSpecialityList = [];
					$scope.tempSelectSpecialty = [];
					uncheckSpecialityList();
					$scope.initBillingGroupMasterList($scope.offset, $scope.noOfRecordsPerPage);
				}
			});
		}
	}
	
	
	//------------ Get Speciality by Group ----------------//
	$scope.getSpecialityByGroup = function(billingGroupId){
		$rootScope.startLoader();
		$scope.specilityListByGroup = [];
		var URI = BASE_URL + ROOT_URL + BILLING + GET_BILLING_GROUP_MASTER_ID;
		var obj ={
			 	 orgnisationId :$scope.organizationId,
				 unitId :$scope.unitId,
				 billingGroupId :billingGroupId
			}
		BillingGenericService.serviceAction(METHOD_POST,URI, obj, NOTIFICATION_MSG_STATUS_FALSE).then(function(response){
			if(response.data.status == "success"){
				$scope.specilityListByGroup = response.data.listObject;
			}
		});
	}
	//======================= Service Master Pagination (Start)=====================//
	$scope.setNoOfRecords = function() {
		$scope.initBillingGroupMasterList($scope.organizationId, $scope.offset, $scope.noOfRecordsPerPage);
	};
	
	$scope.initBillingGroupMasterList = function(offset, noOfRecordsPerPage){
		$rootScope.startLoader();
		var obj1 = {
				 orgnisationId :$scope.organizationId,
				 unitId        :$scope.unitId,
				 offset        :offset != null ? offset : 0,
				 recordPerPage :noOfRecordsPerPage != null ? noOfRecordsPerPage : 10
			}
		
		var obj2 = {
				 orgnisationId :$scope.organizationId,
				 unitId        :$scope.unitId
			}
		var URI_TO_GET_BILLING_GROUP_LIST = BASE_URL + ROOT_URL + BILLING + GET_BILLING_GROUP_MASTER_LIST;
		var URI_TO_GET_BILLING_GROUP_COUNT = BASE_URL + ROOT_URL + BILLING + GET_BILLING_GROUP_MASTER_COUNT;
		promiseFactoryWithObject.setPromisesWithObject(URI_TO_GET_BILLING_GROUP_LIST, URI_TO_GET_BILLING_GROUP_COUNT, METHOD_POST, METHOD_POST, obj1, obj2).then(function(response) 
		{
			$rootScope.stopLoader();
			$scope.billingGroupMasterList = response[0].listObject;
			$scope.commonListCount = response[1].object;
			$scope.setPage(1, false);
		});
	}
	
	
	$scope.getBillingGroupMasterList = function(offset, noOfRecordsPerPage){
		try{
			$rootScope.startLoader();
			var obj = {
					 orgnisationId :$scope.organizationId,
					 unitId        :$scope.unitId,
					 offset        :offset != null ? offset : 0,
					 recordPerPage :noOfRecordsPerPage != null ? noOfRecordsPerPage : 10
				};
			var URI = BASE_URL + ROOT_URL + BILLING + GET_BILLING_GROUP_MASTER_LIST;;
			console.log("URI", URI);
			BillingGenericService.serviceAction(METHOD_GET, URI, obj,NOTIFICATION_MSG_STATUS_FALSE).then(function(response) {
				$rootScope.stopLoader();
				$scope.billingGroupMasterList = response.data.listObject;
				console.log($scope.billingGroupMasterList);
			});
		}catch (e) {
			console.log(e.message);
		}
	}
	
	$scope.setPage = function(page, flag) {
		if (page < 1 || page > $scope.pager.totalPages)
		{
			return;
		}
		$scope.pager = PagerService.GetPager($scope.commonListCount, page, $scope.noOfRecordsPerPage);
		if (flag)
		{
			$scope.getBillingGroupMasterList($scope.pager.startIndex, $scope.pager.pageSize);
		}
	}
	$scope.initBillingGroupMasterList($scope.offset, $scope.noOfRecordsPerPage);
	//=================== Service Master Pagination (End)====================//
}]);