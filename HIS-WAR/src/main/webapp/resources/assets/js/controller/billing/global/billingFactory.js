angular.module("myApp").factory('paymentCollFactory',['$rootScope','BillingGenericService',function($rootScope,BillingGenericService){
	var scope = angular.element(document.getElementById('uiView')).scope();

	scope.initPaymentDetailsObj = function(){
		scope.paymentDetails = angular.copy({paymentModeId:1,cashAmount:"",bankId:"",chequeNumber:"",chequeDate:"",chequeAmount:"",credCardHolderName:"",credCardTypeId:"",credAmount:"",credCardNumber:"",credExpiryDate:"",debCardHolderName:"",debCardTypeId:"",debAmount:"",debCardNumber:"",debExpiryDate:""});
	}
	
	scope.addPaymentDetails = function(){
		switch(scope.paymentDetails.paymentModeId){
			case 1 :
				var paymentDetails = {paymentModeId:scope.paymentDetails.paymentModeId, amount:parseFloat(scope.paymentDetails.cashAmount).toFixed(2),createdBy:scope.userId};
				scope.listPaymentDetails.push(paymentDetails);
				break;
			case 2 :
				var bankName = "";
				//Get Bank Name
				angular.forEach(scope.bankList,function(value,key){
					if(scope.paymentDetails.bankId == value.bankId)
						bankName = value.bankName;
				})
				var chequeDate = $rootScope.getFormattedDate(scope.paymentDetails.chequeDate,false);
				var paymentDetails = {paymentModeId:scope.paymentDetails.paymentModeId, amount:parseFloat(scope.paymentDetails.chequeAmount).toFixed(2),createdBy:scope.userId, bankId:scope.paymentDetails.bankId, chequeNumber:scope.paymentDetails.chequeNumber, chequeDate:chequeDate, bankName:bankName};
				scope.listPaymentDetails.push(paymentDetails);
				break;
			case 3 :
				var expiryDate =  $rootScope.getFormattedDate(scope.paymentDetails.credExpiryDate,false);
				var paymentDetails = {paymentModeId:scope.paymentDetails.paymentModeId, amount:parseFloat(scope.paymentDetails.credAmount).toFixed(2),createdBy:scope.userId, cardHolderName:scope.paymentDetails.credCardHolderName,cardTypeId:scope.paymentDetails.credCardTypeId,cardNo:scope.paymentDetails.credCardNumber,expiryDate:expiryDate};
				scope.listPaymentDetails.push(paymentDetails);
				break;
			case 4 :
				var expiryDate =  $rootScope.getFormattedDate(scope.paymentDetails.debExpiryDate,false);
				var paymentDetails = {paymentModeId:scope.paymentDetails.paymentModeId, amount:parseFloat(scope.paymentDetails.debAmount).toFixed(2),createdBy:scope.userId, cardHolderName:scope.paymentDetails.debCardHolderName,cardTypeId:scope.paymentDetails.debCardTypeId,cardNo:scope.paymentDetails.debCardNumber,expiryDate:expiryDate};
				scope.listPaymentDetails.push(paymentDetails);
				break;
		}
		scope.initPaymentDetailsObj();
		
		//calculate due amount by page
		switch(scope.page){
		case "billList":
			scope.calculateTotalDueRefAmount();
			break;
		}
		
	}
	
	scope.removePaymentDetails = function(index){
		scope.listPaymentDetails.splice(index,1);
		
		//calculate due amount by page
		switch(scope.page){
		case "billList":
			scope.calculateTotalDueRefAmount();
			break;
		}
	}
	
	return scope;
}]);