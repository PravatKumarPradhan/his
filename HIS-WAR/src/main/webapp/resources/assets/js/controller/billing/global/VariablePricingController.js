/**
 * @author SantoshK.
 * Use - To Define variable pricing multiplication factor 
 */

angular.module("myApp").controller("VariablePricingController", [ '$scope','$rootScope','$state','$cookies','BillingGenericService','PagerService','promiseFactory','$sessionStorage','growl',
   function($scope,$rootScope,$state, $cookies, BillingGenericService,PagerService,promiseFactory,$sessionStorage,growl){
	
	var cookieObject = $cookies.getObject('cookieObject');
	if(cookieObject == undefined){
		$state.go('login');
		return;
	}
	$scope.unitId = cookieObject.unitId;
	$scope.organizationId = cookieObject.organizationId;
	
	/**Variables for pagination */
	$scope.offset = 0;
	$scope.noOfRecordsPerPage = 10;
	$scope.organizationServiceMasterList = [];
	$scope.totalNoOfRecords;
	$scope.searchKeyword;
	$scope.commonListCount;
	$scope.pager = {};
	$scope.page;
	
	$scope.initVariablePricing = function(){
		// init variable pricing dto
		$scope.variablePricingDTO = {
				"visitTypeId":1,
				"unitId":$scope.unitId,
				"organisationId":$scope.organizationId,
				"status":'A'
		}
		
		$scope.visitTypeId = 1;
		
		$scope.variablePricingDTO.listPatientCategoryDto = [];
		$scope.variablePricingDTO.listBedCategoryDto = [];
		$scope.variablePricingDTO.listPaymentEntitlementDto = [];
	}
	
	
		$scope.getPatientCategoryType = function(){
			$rootScope.startLoader();
			var URI = BASE_URL + ROOT_URL + GLOBAL + GET_PATIENT_CATEGORY;
			//console.log("URI", URI);
			var param = {
				"unitId":1,
				"organizationId":1
			}
			BillingGenericService.serviceAction(METHOD_POST, URI, param, NOTIFICATION_MSG_STATUS_FALSE).then(function(response) {
				$rootScope.stopLoader();
				if(response.data.status == 'success'){
					//console.log('get response: '+ JSON.stringify(response.data.listObject));
					if(response.data.listObject.length != 0 )
						$scope.listPatientCategoryType = response.data.listObject;
						
				}
			});
		}
		
		$scope.getBedCategory = function(){
			$rootScope.startLoader();
			/*var URI = BASE_URL + ROOT_URL  + GET_BED_CATEGORY;*/
			var URI = BASE_URL + ROOT_URL
			+ "/adt/getActiveBillingBedCategoryList/orgId/"+$scope.organizationId+"/unitId/"+$scope.unitId;
			//console.log("URI", URI);
			var param = {
				
			}
			BillingGenericService.serviceAction(METHOD_GET, URI, param, NOTIFICATION_MSG_STATUS_FALSE).then(function(response) {
				$rootScope.stopLoader();
				if(response.data.status == 'success'){
					//console.log('get response: '+ JSON.stringify(response.data.listObject));
					if(response.data.listObject.length != 0 )
						$scope.listBedCategory = response.data.listObject;
						
				}
			});
		}
		
		$scope.getPaymentEntitlement = function(){
			$rootScope.startLoader();
			var URI = BASE_URL + ROOT_URL + GLOBAL + GET_PAYMENT_ENTITLEMENT;
			//console.log("URI", URI);
			var param = {
				"unitId":1,
				"organizationId":1
			}
			BillingGenericService.serviceAction(METHOD_POST, URI, param, NOTIFICATION_MSG_STATUS_FALSE).then(function(response) {
				$rootScope.stopLoader();
				if(response.data.status == 'success'){
					//console.log('get response: '+ JSON.stringify(response.data.listObject));
					if(response.data.listObject.length != 0 )
						$scope.listPaymentEntitlement = response.data.listObject;
						
				}
			});
		}
		
		$scope.onVisitTypeRadioChange = function(newValue){
			//$scope.initVariablePricing();
			$scope.getPatientCategoryType();
			$scope.getBedCategory();
			$scope.getPaymentEntitlement();
			$scope.initVariablePricing();
			$scope.getVariablePricingMultiplicationFactor(newValue);
		}
		
		$scope.saveVariablePricingValues = function(){
			/*growl.success("Saving, Please wait..!!",{
				title : SUCCESS_MSG
			});*/
			$rootScope.startLoader();
			//console.log("Saving sequence details ---- > UnitId: ");
			var URI = BASE_URL + ROOT_URL + BILLING + VARIABLE_PRICING + SAVE_VARIABLE_PRICING;
			//console.log("URI", URI);
			$scope.variablePricingDTO.serviceTarrifMasterId = "";
			console.log("Param: "+ JSON.stringify($scope.variablePricingDTO));
			BillingGenericService.serviceAction(METHOD_POST, URI, $scope.variablePricingDTO, NOTIFICATION_MSG_STATUS_TRUE ).then(function(response) {
				$rootScope.stopLoader();
				if(response.data.status == 'success'){
					//console.log('save response: '+ JSON.stringify(response.data.object));
					$scope.variablePricingDTO = response.data.object;
					
				}
			});
		}
		
		$scope.getVariablePricingMultiplicationFactor = function(visitType){
			$rootScope.startLoader();
			console.log("Fetching details ---- > UnitId: ");
			var URI = BASE_URL + ROOT_URL + BILLING + VARIABLE_PRICING + GET_VARIABLE_PRICING_VALUES;
			//console.log("URI", URI);
			var param = {
					"unitId":1,
					"organisationId":1,
					"visitTypeId":visitType,
				}
			BillingGenericService.serviceAction(METHOD_POST, URI, param, NOTIFICATION_MSG_STATUS_FALSE ).then(function(response) {
				$rootScope.stopLoader();
				if(response.data.status == 'success'){
					$scope.variablePricingDTO = response.data.object;
				}
				$scope.variablePricingDTO.visitTypeId = visitType;
				$scope.visitTypeId = visitType;
			});
		}
		
		$scope.getPatientCategoryType();
		$scope.getBedCategory();
		$scope.getPaymentEntitlement();
		$scope.initVariablePricing();
		$scope.getVariablePricingMultiplicationFactor(1);
		
		//======================= Service Master Pagination (Start)=====================//
		$scope.setNoOfRecords = function() {
			$scope.initOrganizationServiceMasterList($scope.organizationId, $scope.unitId, $scope.visitTypeId, $scope.offset, $scope.noOfRecordsPerPage);
		};
		
		$scope.initOrganizationServiceMasterList = function(orgId, unitId, visitTypeId, offset, noOfRecordsPerPage){
			$rootScope.startLoader();
			//alert('read service..')
			var obj = "";
			offset = offset != null ? offset : 0;
			noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage : 10;
			var URI_TO_GET_SERVICE_MASTER_LIST_WITH_PRICE = BASE_URL + ROOT_URL + BILLING + VARIABLE_PRICING + URI_TO_GET_SERVICE_MASTER_LIST_WITH_PRICE_BY_VISITTYPE + SLASH + orgId + SLASH+ unitId + SLASH+ visitTypeId + SLASH + offset + SLASH + noOfRecordsPerPage;
			var URI_TO_GET_SERVICE_MASTER_COUNT = BASE_URL + ROOT_URL + BILLING + GET_ORGANIZATION_SERVICE_MASTER_COUNT + SLASH + orgId;
			promiseFactory.setPromises(URI_TO_GET_SERVICE_MASTER_LIST_WITH_PRICE, URI_TO_GET_SERVICE_MASTER_COUNT, "GET", "GET").then(function(response) 
			{
				$rootScope.stopLoader();
				//console.log('response list : '+JSON.stringify(response[0].object));
				$scope.organizationServiceMasterList = response[0].object;
				$scope.commonListCount = response[1].object;
				$scope.setPage(1, false);
			});
		}
		
		$scope.getOrganizationlistServiceMaster = function(orgId, unitId, visitTypeId, offset, noOfRecordsPerPage){
			$rootScope.startLoader();
			var obj = "";
			offset = offset != null ? offset : 0;
			noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage : 10;
			//var URI =  BASE_URL + ROOT_URL + BILLING + GET_ORGANIZATION_SERVICE_MASTER_LIST + SLASH + orgId + SLASH + offset + SLASH + noOfRecordsPerPage;
			var URI_TO_GET_SERVICE_MASTER_LIST_WITH_PRICE = BASE_URL + ROOT_URL + BILLING + VARIABLE_PRICING + URI_TO_GET_SERVICE_MASTER_LIST_WITH_PRICE_BY_VISITTYPE + SLASH + orgId + SLASH+ unitId + SLASH+ visitTypeId + SLASH + offset + SLASH + noOfRecordsPerPage;
			//console.log("URI", URI);
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
		//$scope.initOrganizationServiceMasterList($scope.organizationId, $scope.unitId, $scope.visitTypeId, $scope.offset, $scope.noOfRecordsPerPage);
		//=================== Service Master Pagination (End)====================//
}]);