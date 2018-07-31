/**
 * @author Santosh
 * @date 10-11-2017
 * 		Controller to handle variable pricing sequence 
 */

angular.module("myApp").controller("variablePricingSequenceController", [ '$scope','$rootScope','$state','$cookies','BillingGenericService','PagerService','promiseFactory','$sessionStorage','growl',
	function($scope,$rootScope,$state, $cookies, BillingGenericService,PagerService,promiseFactory,$sessionStorage,growl){
	
	$scope.initVariablePricingSeq = function(){
		// init variable pricing dto
		$scope.variablePricingSeqDTO = {
				"visitType":1,
				"patientTypeSeqNo":0,
				"billingBedCategorySeqNo":0,
				"statChargeSeqNo":0,
				"paymentEntitlementSeqNo":0,
				"unitId":1,
				"orgId":1,
				"status":'A'
		}
		
		$scope.orderByAttribute = 'position';
	    
	    $scope.listData = [
	        {name: "Patient Type", position: $scope.variablePricingSeqDTO.patientTypeSeqNo},
	        {name: "Payment Entitlement", position: $scope.variablePricingSeqDTO.paymentEntitlementSeqNo},
	        {name: "Billing Bed Category", position: $scope.variablePricingSeqDTO.billingBedCategorySeqNo},
	        {name: "Stat Charge", position: $scope.variablePricingSeqDTO.statChargeSeqNo},
		];
	}
	
	// To Save variable pricing sequence
	$scope.saveVariablePricingSeqForLoggedInUnit = function(){
		$rootScope.startLoader();
		console.log("Saving sequence details ---- > UnitId: ");
		var URI = BASE_URL + ROOT_URL + BILLING + VARIABLE_PRICING + SAVE_VARIABLE_PRICING_SEQ_FOR_UNIT;
		console.log("URI", URI);
		console.log("Param: "+ JSON.stringify($scope.variablePricingSeqDTO));
		BillingGenericService.serviceAction(METHOD_POST, URI, $scope.variablePricingSeqDTO, NOTIFICATION_MSG_STATUS_TRUE ).then(function(response) {
			$rootScope.stopLoader();
			if(response.data.status == 'success'){
				console.log('save response: '+ JSON.stringify(response.data.object));
				$scope.variablePricingSeqDTO = response.data.object;
				
				 $scope.listData = [
			        	        {name: "Patient Type", position: $scope.variablePricingSeqDTO.patientTypeSeqNo},
			        	        {name: "Payment Entitlement", position: $scope.variablePricingSeqDTO.paymentEntitlementSeqNo},
			        	        {name: "Billing Bed Category", position: $scope.variablePricingSeqDTO.billingBedCategorySeqNo},
			        	        {name: "Stat Charge", position: $scope.variablePricingSeqDTO.statChargeSeqNo},
			        		];
			}
		});
	}
	
	// To Get variable pricing sequence
	$scope.getVariablePricingSeqForLoggedInUnit = function(visitType){
		$rootScope.startLoader();
		console.log("Fetching sequence details ---- > UnitId: ");
		var URI = BASE_URL + ROOT_URL + BILLING + VARIABLE_PRICING + GET_VARIABLE_PRICING_SEQ_FOR_UNIT;
		console.log("URI", URI);
		console.log("Param: "+ JSON.stringify($scope.variablePricingSeqDTO));
		var param = {
			"visitType": visitType,
			"unitId":1,
			"orgId":1
		}
		BillingGenericService.serviceAction(METHOD_POST, URI, param).then(function(response) {
			$rootScope.stopLoader();
			if(response.data.status == 'success'){
				console.log('get response: '+ JSON.stringify(response.data.listObject[0]));
				if(response.data.listObject.length != 0 )
					$scope.variablePricingSeqDTO = response.data.listObject[0];
				$scope.variablePricingSeqDTO.visitType = visitType;
				
				$scope.listData = [
				        	        {name: "Patient Type", position: $scope.variablePricingSeqDTO.patientTypeSeqNo},
				        	        {name: "Payment Entitlement", position: $scope.variablePricingSeqDTO.paymentEntitlementSeqNo},
				        	        {name: "Billing Bed Category", position: $scope.variablePricingSeqDTO.billingBedCategorySeqNo},
				        	        {name: "Stat Charge", position: $scope.variablePricingSeqDTO.statChargeSeqNo},
				        		];
				
			}
		});
	}
	
	$scope.onVisitTypeRadioChange = function(newValue){
		$scope.initVariablePricingSeq();
		$scope.getVariablePricingSeqForLoggedInUnit(newValue);
	}
	$scope.initVariablePricingSeq();
	$scope.getVariablePricingSeqForLoggedInUnit(1);
	
	/*$scope.$watch('variablePricingSeqDTO', function() {
        if($scope.variablePricingSeqDTO.patientTypeSeqNo == $scope.variablePricingSeqDTO.billingBedCategorySeqNo){
        	growl.error("Sequences can not be same!!",{
				title : ERROR_MSG
			});
        }
    }, false);*/
	
	
}]);

angular.module("myApp").filter('orderObjectBy', function(){
	 return function(input, attribute) {
	    if (!angular.isObject(input)) return input;

	    var array = [];
	    for(var objectKey in input) {
	        array.push(input[objectKey]);
	    }

	    array.sort(function(a, b){
	        a = parseInt(a[attribute]);
	        b = parseInt(b[attribute]);
	        return a - b;
	    });
	    return array;
	 }
	});
					