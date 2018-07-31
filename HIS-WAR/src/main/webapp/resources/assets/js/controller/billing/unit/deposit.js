//directive to allow only decimal
angular.module("myApp").directive('ngDecimal', function(){
    return {
        restrict: 'A',
        link: function($scope, $element, $attributes){
            var limit = $attributes.ngDecimal;
            function caret(node) {
                if(node.selectionStart) {
                    return node.selectionStart;
                }
                else if(!document.selection) {
                    return 0;
                }
                //node.focus();
                var c		= "\001";
                var sel	= document.selection.createRange();
                var txt	= sel.text;
                var dul	= sel.duplicate();
                var len	= 0;
                try{ dul.moveToElementText(node); }catch(e) { return 0; }
                sel.text	= txt + c;
                len		= (dul.text.indexOf(c));
                sel.moveStart('character',-1);
                sel.text	= "";
                return len;
            }
            $element.bind('keypress', function(event){
            	var charCode = (event.which) ? event.which : event.keyCode;
            	var elem=document.getElementById($element.attr("id"));
            	if (charCode == 45){
                    var caretPosition=caret(elem);
                    if(caretPosition==0){
                    	if($element.val().charAt(0)!="-" ){
                    		if($element.val() <=limit){
                                $element.val("-"+$element.val());
                            }
                        }
                        if($element.val().indexOf("-")!=-1){
                        	event.preventDefault();
                            return false;
                        }
                    }
                    else{
                    	event.preventDefault();
                    }
                }
                if (charCode == 46){
                    if($element.val().length>limit-1){
                    	event.preventDefault();
                    	return false;
                    }
                    if ($element.val().indexOf('.') !=-1){
                    	event.preventDefault();
                        return false;
                    }
                    return true;
                }
                if (charCode != 45 && charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)){
                	event.preventDefault();
                    return false;
                }
                if($element.val().length>limit-1){
                	event.preventDefault();
                	return false;
                }
                return true;
            });
        }
    };
});

angular.module("myApp").controller("depositController", [ '$scope','$rootScope','BillingGenericService','$cookies','$state','growl',
	function($scope,$rootScope,BillingGenericService,$cookies,$state,growl){
	try{
	
	$rootScope.loginpage = true;
	
	
	$scope.dateOptions = {
			formatYear : 'yyyy',
			showWeeks : false
		};
	$scope.openDatePicker = function() {
		$scope.datepicker.opened = true;
	};

	$scope.datepicker = {
		opened : false
	};
	//For Get Active Gender List
	$scope.getSexMasterList = function() {
		var URI = BASE_URL + ROOT_URL + GETACTIVEGENDERLIST;
		BillingGenericService.serviceAction(METHOD_GET, URI, "",NOTIFICATION_MSG_STATUS_FALSE).then(function(response) {
			$scope.sexMasterList = response.data.listObject;
		});
	}
	
	$scope.getIdentificationTypeList = function(){
		var URI = BASE_URL + ROOT_URL + GETACTIVEIDENTIFICATION;
		BillingGenericService.serviceAction(METHOD_GET, URI, "",NOTIFICATION_MSG_STATUS_FALSE).then(function(response) {
			if (response.data.status == "success")
				$scope.identificationTypeList = response.data.listObject;
		});
	}
	
	$scope.getBankList = function(){
		var URI = BASE_URL + ROOT_URL + "/api/billing/bankMaster";
		var param = {orgnisationId:$scope.organizationId,unitId:$scope.unitId};
		BillingGenericService.serviceAction(METHOD_POST,URI,param,NOTIFICATION_MSG_STATUS_FALSE).then(function(response) {
			if (response.data.status == "success"){
				$scope.bankList = response.data.listObject;
			}
		});
	}
	
	$scope.getCardTypesList = function(){
		var URI = BASE_URL + ROOT_URL + "/api/billing/cardTypeMaster";
		var param = {orgnisationId:$scope.organizationId,unitId:$scope.unitId};
		BillingGenericService.serviceAction(METHOD_POST,URI,param,NOTIFICATION_MSG_STATUS_FALSE).then(function(response) {
			if (response.data.status == "success"){
				$scope.cardTypeList = response.data.listObject;
			}
		});
	}
	
	$scope.initPaymentDetailsObj = function(){
		$scope.paymentDetails = angular.copy({paymentModeId:1,cashAmount:"",bankId:"",chequeNumber:"",chequeDate:"",chequeAmount:"",credCardHolderName:"",credCardTypeId:"",credAmount:"",credCardNumber:"",credExpiryDate:"",debCardHolderName:"",debCardTypeId:"",debAmount:"",debCardNumber:"",debExpiryDate:""});
	}
	
	$scope.initDepositObj = function(){
		$scope.deposit = angular.copy({depositTypeId:"",visitTypeId:"",depositAmount:"",remarks:"",receivedFrom:"",identificationNo:"",patientId:"",visitAdmId:""})
	}
	
	$scope.initDepositSearchObj = function(){
		$scope.depositSearch = angular.copy({fromDate:"",toDate:"",patientName:"",uhidNumber:"",visitTypeId:"",depositTypeId:""});
	}
	
	$scope.initDepositSearchDtls = function(){
		$scope.depositSearchDtls = angular.copy({totalDepositAmount:parseFloat(0).toFixed(2),totalConsumedDepositAmount:parseFloat(0).toFixed(2),totalBalanceAmount:parseFloat(0).toFixed(2)});
	}
	
	$scope.init = function(){
		$scope.identificationTypeList = [];
		$scope.sexMasterList = [];
		$scope.getSexMasterList();
		$scope.getIdentificationTypeList();
		$scope.patientDetailsList = [];
		$scope.patientDetails = {}
		$scope.billing = true;
		$scope.deposit = {};
		$scope.initDepositObj();
		$scope.paymentDetails = {};
		$scope.initPaymentDetailsObj();
		$scope.listPaymentDetails = [];
		$scope.getBankList();
		$scope.getCardTypesList();
		$scope.dueAmount = parseFloat(0).toFixed(2);
		$scope.depositSearch = {};
		$scope.initDepositSearchObj();
		$scope.depositSearchList = [];
		$scope.depositSearchDtls = {};
		$scope.visitNoList = [];
	}
	
	var cookieObject = $cookies.getObject('cookieObject');
	if (cookieObject == undefined) {
		$state.go('login');
		return;
	}
	$scope.unitId = cookieObject.unitId;
	$scope.organizationId = cookieObject.organizationId;
	$scope.userId = 1;
	
	$scope.init();
	
	$scope.patientSearchObj = {
			"uhidNumber"      : "",
			"patientName"     : "",
			"genderId"        : "",
			"mobileNumber"        : "",
			"dob"       : "",
			"identificationNumber":"",
			"identificationTypeId":"",
			"unitId"          :$scope.unitId,
			"organizationId"  :$scope.organizationId,
			"registrationTypeId":1
	}
	
	$scope.searchPatient = function()
	{
		var URI = BASE_URL + ROOT_URL + "/api/global/patientSearch";
		var patSearchObj = angular.copy($scope.patientSearchObj);
		if($scope.patientSearchObj.dob != "" && typeof($scope.patientSearchObj.dob) != "undefined"){
			var dt = new Date($scope.patientSearchObj.dob);
			patSearchObj.dob = dt.getDate() + "-" + (dt.getMonth() + 1) + "-" + dt.getFullYear();
		} 
		BillingGenericService.serviceAction(METHOD_POST,URI, patSearchObj,NOTIFICATION_MSG_STATUS_FALSE).then(function(response) {
			$scope.searchPatientList = [];
			if (response.data.status == "success")
				$scope.patientList = response.data.listObject;
			patSearchObj = {};
		});
	}
	
	$scope.getPatientDetailsById = function(patientId){
		$scope.patientDetailsList = [];
		$scope.patientDetails = {}
		var URI = BASE_URL + ROOT_URL + "/api/global/patient/billing/"+patientId;
		BillingGenericService.serviceAction(METHOD_GET,URI,null,NOTIFICATION_MSG_STATUS_FALSE).then(function(response) {
			if (response.data.status == "success"){
				$scope.patientDetailsList = response.data.listObject;
				$scope.patientDetails = response.data.listObject[0];
				$scope.deposit.patientId = $scope.patientDetails.patientId;
			}
		});
	}
		
	$scope.firstTabLevel = 1 ;
	
	$scope.setFirstTabLevel= function(newTab)
	{
		$scope.paymentDetails.paymentModeId = newTab;
		$scope.firstTabLevel = newTab;
	};
	$scope.isSetFirstTabLevel = function(tabNum)
	{
		return $scope.firstTabLevel === tabNum;
	};
	
	$scope.addPaymentDetails = function(){
		
		//check amount collected is greater tha deposit amount
		var depositAmount = $scope.deposit.depositAmount != "" && typeof($scope.deposit.depositAmount) != "undefined" ? parseFloat($scope.deposit.depositAmount) : parseFloat(0) ;
		var collectedAmount = 0;
		var tempAmount = 0;
		
		switch($scope.paymentDetails.paymentModeId){
		case 1 :
			tempAmount = $scope.paymentDetails.cashAmount != '' && typeof($scope.paymentDetails.cashAmount) != 'undefined' ? parseFloat($scope.paymentDetails.cashAmount) : parseFloat(0);
			if(parseFloat($scope.paymentDetails.cashAmount) > depositAmount){
				growl.warning("Collected amount cannot greater than deposit amount",{
					title : WARNING_MSG
				});
				return;
			}
				break;
		case 2 :
			tempAmount = $scope.paymentDetails.chequeAmount != '' && typeof($scope.paymentDetails.chequeAmount) != 'undefined' ? parseFloat($scope.paymentDetails.chequeAmount) : parseFloat(0);
			if(parseFloat($scope.paymentDetails.chequeAmount) > depositAmount){
				growl.warning("Collected amount cannot greater than deposit amount",{
					title : WARNING_MSG
				});
				return;
			}
			break;
		
		case 3 :
			tempAmount = $scope.paymentDetails.credAmount != '' && typeof($scope.paymentDetails.credAmount) != 'undefined' ? parseFloat($scope.paymentDetails.credAmount) : parseFloat(0);
			if(parseFloat($scope.paymentDetails.credAmount) > depositAmount){
				growl.warning("Collected amount cannot greater than deposit amount",{
					title : WARNING_MSG
				});
				return;
			}
			break;
		
		case 4 :
			tempAmount = $scope.paymentDetails.debAmount != '' && typeof($scope.paymentDetails.debAmount) != 'undefined' ? parseFloat($scope.paymentDetails.debAmount) : parseFloat(0);
			if(parseFloat($scope.paymentDetails.debAmount) > depositAmount){
				growl.warning("Collected amount cannot greater than deposit amount",{
					title : WARNING_MSG
				});
				return;
			}
			break;
		}
		
		if(tempAmount == 0){
			growl.warning("Amount cannot be zero",{
				title : WARNING_MSG
			});
			return;
		}
		
		angular.forEach($scope.listPaymentDetails,function(value,key){
			collectedAmount = parseFloat(collectedAmount) + parseFloat(value.amount);
		});
		collectedAmount = collectedAmount + tempAmount;
		if(collectedAmount > depositAmount){
			growl.warning("Collected amount cannot greater than deposit amount",{
				title : WARNING_MSG
			});
			return;
		}
		
		switch($scope.paymentDetails.paymentModeId){
			case 1 :
				var paymentDetails = {paymentModeId:$scope.paymentDetails.paymentModeId, amount:parseFloat($scope.paymentDetails.cashAmount).toFixed(2),createdBy:$scope.userId};
				$scope.listPaymentDetails.push(paymentDetails);
				break;
			case 2 :
				var bankName = "";
				//Get Bank Name
				angular.forEach($scope.bankList,function(value,key){
					if($scope.paymentDetails.bankId == value.bankId)
						bankName = value.bankName;
				})
				var chequeDate = $scope.getFormattedDate($scope.paymentDetails.chequeDate,false);
				var paymentDetails = {paymentModeId:$scope.paymentDetails.paymentModeId, amount:parseFloat($scope.paymentDetails.chequeAmount).toFixed(2),createdBy:$scope.userId, bankId:$scope.paymentDetails.bankId, chequeNumber:$scope.paymentDetails.chequeNumber, chequeDate:chequeDate, bankName:bankName};
				$scope.listPaymentDetails.push(paymentDetails);
				break;
			case 3 :
				var expiryDate =  $scope.getFormattedDate($scope.paymentDetails.credExpiryDate,false);
				var paymentDetails = {paymentModeId:$scope.paymentDetails.paymentModeId, amount:parseFloat($scope.paymentDetails.credAmount).toFixed(2),createdBy:$scope.userId, cardHolderName:$scope.paymentDetails.credCardHolderName,cardTypeId:$scope.paymentDetails.credCardTypeId,cardNo:$scope.paymentDetails.credCardNumber,expiryDate:expiryDate};
				$scope.listPaymentDetails.push(paymentDetails);
				break;
			case 4 :
				var expiryDate =  $scope.getFormattedDate($scope.paymentDetails.debExpiryDate,false);
				var paymentDetails = {paymentModeId:$scope.paymentDetails.paymentModeId, amount:parseFloat($scope.paymentDetails.debAmount).toFixed(2),createdBy:$scope.userId, cardHolderName:$scope.paymentDetails.debCardHolderName,cardTypeId:$scope.paymentDetails.debCardTypeId,cardNo:$scope.paymentDetails.debCardNumber,expiryDate:expiryDate};
				$scope.listPaymentDetails.push(paymentDetails);
				break;
		}
		$scope.initPaymentDetailsObj();
		$scope.calculateDueAmount();
		
	}
	
	$scope.calculateDueAmount = function(){
		//calculate due amount
		var collectedAmount = parseFloat(0).toFixed();
		angular.forEach($scope.listPaymentDetails,function(value,key){
			collectedAmount = parseFloat(collectedAmount) + parseFloat(value.amount);
		});
		$scope.dueAmount = $scope.deposit.depositAmount != '' && typeof($scope.deposit.depositAmount) != 'undefined' ? parseFloat(parseFloat($scope.deposit.depositAmount) - collectedAmount).toFixed(2) : parseFloat(0).toFixed(2);
	}
	
	//remove payment details
	$scope.removePaymentDetails = function(index){
		$scope.listPaymentDetails.splice(index,1);
		$scope.calculateDueAmount();
	}
	
	$scope.parseFloatNum = function(number){
		return (number != '' && typeof(number) != 'undefined' && !isNaN(number)) ? parseFloat(number).toFixed(2) : parseFloat(0).toFixed(2);
	}
	
	$scope.addDeposit = function(){
		if($scope.dueAmount > 0){
			growl.error("Please clear the due amount",{
				title : ERROR_MSG
			});
			return;
		}
		$rootScope.startLoader();
		$scope.deposit.createdBy = $scope.userId;
		$scope.deposit.orgId = $scope.organizationId;
		$scope.deposit.unitId = $scope.unitId; 
		if($scope.deposit.depositTypeId == 1)
			$scope.deposit.payeeId = 1;
		var param = {depositMasterDto:$scope.deposit,listPaymentDepositDetailsDto:$scope.listPaymentDetails};
		var URI = BASE_URL + ROOT_URL + "/api/billing/unit/deposit";
		BillingGenericService.serviceAction(METHOD_POST,URI,param,NOTIFICATION_MSG_STATUS_TRUE).then(function(response) {
			if (response.data.status == "success"){
				$scope.initPaymentDetailsObj();
				$scope.listPaymentDetails = [];
				$scope.initDepositObj();
				$scope.patientDetailsList = [];
				$scope.deposit.patientId = $scope.patientDetails.patientId;
				$scope.patientDetails = {};
			}
			$rootScope.stopLoader();
		});
	}
	
	$scope.getFormattedDate = function(date,isTime){
		var tempDate = new Date(date);
		var date = tempDate.getDate().toString().length == 1 ? "0"+tempDate.getDate().toString() : tempDate.getDate().toString();
		var month =  (tempDate.getMonth() + 1).toString().length == 1 ? "0"+(tempDate.getMonth() + 1).toString() : (tempDate.getMonth() + 1).toString();
		var year = tempDate.getFullYear();
		if(isTime)
			return date + "-" + month + "-" + year + " "+tempDate.getHours()+":"+tempDate.getMinutes()+":"+getSeconds();
		else
			return date + "-" + month + "-" + year + " 00:00:00";
	}
	
	//search deposit
	$scope.searchDeposit = function(){
		$scope.depositSearch.orgId = $scope.organizationId;
		$scope.depositSearch.unitId = $scope.unitId;
		var URI = BASE_URL + ROOT_URL + "/api/billing/unit/deposit/search";
		BillingGenericService.serviceAction(METHOD_POST,URI,$scope.depositSearch,NOTIFICATION_MSG_STATUS_FALSE).then(function(response) {
			if (response.data.status == "success"){
				$scope.initDepositSearchDtls();
				$scope.depositSearchList = response.data.listObject;
				angular.forEach($scope.depositSearchList,function(value,key){
					$scope.depositSearchDtls.totalDepositAmount = parseFloat($scope.depositSearchDtls.totalDepositAmount) + parseFloat(value.depositAmount);
					$scope.depositSearchDtls.totalConsumedDepositAmount = parseFloat($scope.depositSearchDtls.totalConsumedDepositAmount) + parseFloat(value.consumedDeposit);
					$scope.depositSearchDtls.totalBalanceAmount = parseFloat($scope.depositSearchDtls.totalBalanceAmount) + parseFloat(value.availableDeposit);
				});
			}
			$rootScope.stopLoader();
		});
	}
	
	$scope.changeVisit = function(depositTypeId){
		if(depositTypeId == 1){
			$scope.deposit.visitTypeId = '';
			$scope.deposit.visitAdmId = '';
		}
	}
	
	//get encounters for deposit 
	$scope.getEncounters = function(){
		if($scope.deposit.visitTypeId != null && $scope.deposit.visitTypeId != '' && typeof($scope.deposit.visitTypeId) != "undefined" && $scope.deposit.patientId != '' && $scope.deposit.patientId != null && typeof($scope.deposit.patientId) != 'undefined'){
		var param = {patientId:$scope.deposit.patientId,orgId:$scope.organizationId,unitId:$scope.unitId,visitTypeId:$scope.deposit.visitTypeId};
		var URI = BASE_URL + ROOT_URL + "/api/billing/unit/deposit/encounters";
		BillingGenericService.serviceAction(METHOD_POST,URI,param,NOTIFICATION_MSG_STATUS_FALSE).then(function(response) {
			if (response.data.status == "success"){
				$scope.visitNoList = response.data.listObject;
			}
		});
		}
	}
	
	}catch(e){
		console.log(e.message);
	}
}]);