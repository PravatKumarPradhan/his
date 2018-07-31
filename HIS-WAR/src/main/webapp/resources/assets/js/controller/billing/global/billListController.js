angular.module("myApp").controller("billListController", [ '$scope','$rootScope','BillingGenericService','$cookies','$state','growl','paymentCollFactory',
	function($scope,$rootScope,BillingGenericService,$cookies,$state,growl,paymentCollFactory){
	try{
		$rootScope.loginpage = true;
		$scope.page = "billList";
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
		
		var cookieObject = $cookies.getObject('cookieObject');
		if (cookieObject == undefined) {
			$state.go('login');
			return;
		}
		$scope.unitId = cookieObject.unitId;
		$scope.organizationId = cookieObject.organizationId;
		$scope.userId = 1;
		
		//vivek added
		$scope.payeeId = '';
		$scope.consumedAmt = parseFloat(0);
		
		$scope.depositeList = {
			    depositeIds: [1]
			  };
		
		$scope.depositeDisabledFlag = false;
		
		//vivek added
		
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
		
		
		$scope.searchBills = function(billSearch){
			
			var billSearchObj = angular.copy(billSearch);
			billSearchObj.patientId = $scope.billSearch.patientId;
			billSearchObj.billStatusId = billSearch.billStatusId == '-' ? null : billSearch.billStatusId;
			if($scope.billSearch.isOtcReg)
				billSearchObj.isOtcReg = 'Y';
			else
				billSearchObj.isOtcReg = 'N';
			
			if($scope.billSearch.fromDate != '' && $scope.billSearch.toDate != '' && typeof($scope.billSearch.fromDate) != "undefined" && typeof($scope.billSearch.toDate) != "undefined"){
				billSearchObj.fromDate = $rootScope.getFormattedDate($scope.billSearch.fromDate,false);
				billSearchObj.toDate = $rootScope.getFormattedDate($scope.billSearch.toDate,false);
			}
			
			var URI = BASE_URL + ROOT_URL + "/api/billing/billlist/filter";
			BillingGenericService.serviceAction(METHOD_POST,URI,billSearchObj,NOTIFICATION_MSG_STATUS_FALSE).then(function(response) {
				if (response.data.status == "success"){
					$scope.billList = response.data.listObject;
				}
			});
		
	}
		
		$scope.initBillSearchObj = function(){
			
				//$scope.billSearch = {fromDate:"",toDate:"",visitTypeId:"",billNo:"",visitNo:"",billStatusId:"",patientId:"",orgId:$scope.organizationId,unitId:$scope.unitId,isOtcReg:false};
			$scope.billSearch = {fromDate:new Date(),toDate:new Date(),visitTypeId:"",billNo:"",visitNo:"",billStatusId:"",patientId:"",orgId:$scope.organizationId,unitId:$scope.unitId,isOtcReg:false};
			$scope.searchBills($scope.billSearch);
		}
		
		$scope.init = function(){
			
			$scope.billing = true;
			$scope.billSearch = {};
			$scope.initBillSearchObj();
			$scope.billList = [];
			$scope.payeeList = [];
			$scope.paymentDetailsList=[];
			$scope.listPaymentDetails = [];
			$scope.availableDeposits = [];
			$scope.activeDeposit = 0;
			$scope.billDisabled = false;
		}
		$scope.initTotalAmounts=function(){
			$scope.totalPaymentAmount=parseFloat(0).toFixed(2);
			$scope.dueAmount=parseFloat(0).toFixed(2);
			$scope.refundAmount=parseFloat(0).toFixed(2);
			$scope.totalAmountSummary=parseFloat(0).toFixed(2);
			$scope.totalConcessionSummary=parseFloat(0).toFixed(2);
			$scope.netPayableSummary=parseFloat(0).toFixed(2);
			$scope.totalPayingAmount=parseFloat(0).toFixed(2);
		}
		
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
		$scope.init();
		$scope.initTotalAmounts();
		
		$scope.firstTabLevel = 1 ;
		
		$scope.setFirstTabLevel= function(newTab)
		{
			$scope.firstTabLevel = newTab;
		};
		$scope.isSetFirstTabLevel = function(tabNum)
		{
			return $scope.firstTabLevel === tabNum;
		};
		
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
					$scope.billSearch.patientId = $scope.patientDetails.patientId; 
				}
			});
		}
		
		
	
		$scope.billDetails = function(bill){
			
			console.log("BILL",bill);
			$scope.billDisabled = bill.billStatusId == 3 ? true : false;
			$scope.billId = bill.billingMasterId;
			$scope.totalAmount = bill.netAmount; 
			$scope.dueAmount = bill.outstanding;
			$scope.billDueAmount = bill.outstanding;
			$scope.currentBill = bill;
			//Vivek added
			$scope.encounterId = bill.visitId;
			$scope.appointmentId = bill.appointmentId;
			//End Vivek Added
			//get payer details
			var URI = BASE_URL + ROOT_URL + "/api/billing/payee/bill/"+bill.billingMasterId;
			BillingGenericService.serviceAction(METHOD_GET,URI,null,NOTIFICATION_MSG_STATUS_FALSE).then(function(response) {
				if (response.data.status == "success"){
					$scope.payeeList = response.data.listObject;
					//vivek added
					$scope.billServicesList = response.data.object;
					var totalAmtQuaBase = parseFloat(0);
					var totalAmtConcession = parseFloat(0);
					var totalAmtDiscount = parseFloat(0);
					var totalAmtGst = parseFloat(0);
					var totalNetPayableAmt = parseFloat(0);
					angular.forEach($scope.billServicesList,function(value,key){
						totalAmtQuaBase = totalAmtQuaBase + parseFloat(value.grossAmount);
						totalAmtConcession = totalAmtConcession + parseFloat(value.concession);
						totalAmtDiscount = totalAmtDiscount + parseFloat(value.discount);
						totalAmtGst = totalAmtGst + parseFloat(value.taxAmount);
						totalNetPayableAmt = totalNetPayableAmt + parseFloat(value.netAmt);
					});
					
					$scope.totalAmtQuaBase =totalAmtQuaBase;
					$scope.totalAmtConcession = totalAmtConcession;
					$scope.totalAmtDiscount =totalAmtDiscount;
					$scope.totalAmtGst = (totalAmtGst).toFixed(2);
					$scope.totalNetPayableAmt = totalNetPayableAmt;
					
					//vivek added
				}
			});
			
			//get available active deposits by patient
			var URI = BASE_URL + ROOT_URL + "/api/billing/unit/deposit/patient/"+$scope.organizationId+"/"+$scope.unitId+"/"+bill.patientId;
			BillingGenericService.serviceAction(METHOD_GET,URI,null,NOTIFICATION_MSG_STATUS_FALSE).then(function(response) {
				if (response.data.status == "success"){
					$scope.availableDeposits = response.data.listObject;
					var activeDeposit = parseFloat(0);
					var activeDepositPatient = parseFloat(0);
					var i = 0
					angular.forEach($scope.availableDeposits,function(value,key){
						if(value.depositTypeId != '1'){
						activeDeposit = activeDeposit + parseFloat(value.availableDeposit);
						//Start Vivek Start
						var diffAmt = parseFloat($scope.dueAmount)- parseFloat($scope.consumedAmt);
						if(diffAmt == 0)
						{
							$scope.availableDeposits[i].depositeFlag = 'N';
							$scope.depositeDisabledFlag = true;
						}
						else if(diffAmt > value.availableDeposit)
								{
									$scope.consumedAmt = (parseFloat($scope.consumedAmt)+parseFloat(value.availableDeposit)).toFixed(2);
									$scope.availableDeposits[i].consumedDeposit = parseFloat(value.availableDeposit);
									$scope.availableDeposits[i].availableDeposit = parseFloat(0);
									$scope.availableDeposits[i].depositeFlag = 'Y';
								}							
							else if(diffAmt < value.availableDeposit)
								{
									$scope.consumedAmt = (parseFloat($scope.consumedAmt)+parseFloat(diffAmt)).toFixed(2);
									$scope.availableDeposits[i].availableDeposit = parseFloat(value.availableDeposit)- parseFloat(diffAmt);
									$scope.availableDeposits[i].consumedDeposit = parseFloat(diffAmt);
									$scope.availableDeposits[i].depositeFlag = 'Y';
								}
								
						 //End Vivek start
						
						
						}
						else
							{
							activeDepositPatient = activeDepositPatient + parseFloat(value.availableDeposit);
							}
						i++;
					});
					$scope.activeDeposit = activeDeposit;
					if($scope.activeDeposit == parseFloat(0))
						{
							$scope.useActiveDepo = true;
							$scope.activeDeposit = activeDepositPatient;
						}
					
				}
			});
			
			
		}
		
		//-----------------payment Collection----------------//
		$scope.initPaymentDetails=function(){
			$scope.paymentDetails={
					"paymentRecieptDetailsId":"",
					"paymentModeId"			:1,
					"paymentModeName"		:"Cash",
					"amount"				:"",
					"bankId"				:"",
					"bankName"				:"",
					"accountNumber"			:"",
					"checkNumber"			:"",
					"cardNo"				:"",
					"cardHolderName"		:"",
					"dateOfTransaction"		:new Date().getTime(),
					"expiryDate"			:"",
					"paymentVoucherNo"		:"",
					"cvv"					:"",
					"unitId"				:$scope.unitId,
					"orgnisationId"			:$scope.organizationId,
					"status"				:"A",
					"createdDate"			:new Date().getTime(),
					"createdBy"				:$scope.userId,
					"updatedDate"			:new Date().getTime(),
					"updatedBy"				:$scope.userId,
					"chequeDate"			:"",
					"cardTypeId"			:"",
					"isCancle"				:"I"
					
			}
		}
		$scope.initPaymentDetails();
		
		$scope.paymentModes = {
				1: "Cash",
				2: "Cheque",
				3: "Credit Card",
				4: "Debit Card"
		}
		
		$scope.setPaymentModeId=function(paymentModeId){
			$scope.paymentDetails.paymentModeId=paymentModeId;
			$scope.paymentDetails.paymentModeName=$scope.paymentModes[paymentModeId];
		}
		
		$scope.setBankName=function(bankId){
			angular.forEach($scope.bankList, function (value, key) {
				if(value.bankId == bankId){
					$scope.paymentDetails.bankName = value.bankName;
					return;
				}
			});
			
		}
		
		$scope.getBankList = function(){
			var obj={
					"unitId":$scope.unitId,
					"orgnisationId" : $scope.organizationId
			}
			$rootScope.startLoader();
			var URI = BASE_URL + ROOT_URL + API + BILLING + "/bankMaster";
			BillingGenericService.serviceAction(METHOD_POST,URI, obj, NOTIFICATION_MSG_STATUS_FALSE).then(function(response){
				if(response.data.status == "success"){
					$scope.bankList = response.data.listObject;
				}
			});
		}
		
		$scope.getBankList();
		
		$scope.getCardTypeList = function(){
			var obj={
					"unitId":$scope.unitId,
					"orgnisationId" : $scope.organizationId
			}
			$rootScope.startLoader();
			var URI = BASE_URL + ROOT_URL + API + BILLING + "/cardTypeMaster";
			BillingGenericService.serviceAction(METHOD_POST,URI, obj, NOTIFICATION_MSG_STATUS_FALSE).then(function(response){
				if(response.data.status == "success"){
					$scope.cardTypeList = response.data.listObject;
				}
			});
		}
		$scope.getCardTypeList();
		
		$scope.calculateTotalDueRefAmount = function(){
			var amount = 0;
			var dueAmount = $rootScope.parseFloatNum($scope.dueAmount);
			angular.forEach($scope.listPaymentDetails,function(value,key){
				amount = amount + parseFloat(value.amount); 
			});
			var tempDueAmt = $scope.billDueAmount - amount;
			$scope.dueAmount = tempDueAmt >= 0 ? tempDueAmt : 0;
			$scope.refundAmount = tempDueAmt >= 0 ? 0 : Math.abs(tempDueAmt);
			$scope.amountCollected = amount;
		}
		
		$scope.changePayee = function(payeeId){
			$scope.listPaymentDetails = [];
			$scope.calculateTotalDueRefAmount();
			$scope.payeeId = payeeId;
		}
		
		$scope.settleBill = function(){
			var param = {
					billingMasterId : $scope.billId,
					payeeId : $scope.payeeId,
					listBillingPaymentDetailsReqDto : $scope.listPaymentDetails,
					orgId : $scope.organizationId,
					unitId : $scope.unitId 
			}
			
			if($scope.payeeId == '' || typeof($scope.payeeId) == "undefined"){
				growl.error("Please select a payee",{
					title : ERROR_MSG
				});
				return;
			}
			
			if($scope.listPaymentDetails.length == 0 || $scope.listPaymentDetails == 'undefined'){
				growl.error("Please select at least one payment",{
					title : ERROR_MSG
				});
				return;
			}
			
			if($scope.amountCollected == '' || $scope.amountCollected <= 0){
				growl.error("Amount can't be zero",{
					title : ERROR_MSG
				});
				return;
			}
			
			$rootScope.startLoader();
			var URI = BASE_URL + ROOT_URL + API + BILLING + "/payments";
			BillingGenericService.serviceAction(METHOD_POST,URI, param, NOTIFICATION_MSG_STATUS_TRUE).then(function(response){
				if(response.data.status == "success"){
					//collectPayment
					//billListDetails
					angular.element('#collectPayment').modal('hide');
					angular.element('#billListDetails').modal('hide');
					$scope.billDetails($scope.currentBill);
					$scope.calculateTotalDueRefAmount();
					$scope.listPaymentDetails = [];
					$scope.searchBills($scope.billSearch);
				}
			});
			$rootScope.stopLoader();
		}
		
		/*Vivek start*/
		$scope.settlementClick=function()
		{
			if($scope.payeeId > 0)
				{
					angular.element('#collectPayment').modal('show');
				}
			else
				{
					growl.warning("Please select payer!!",{
						title : WARNING_MSG
					});
					return false;
				}
		}
		
		
		$scope.depositeSub = function(item,idx)
		{
			//$scope.availableDeposits = [];
			console.log("ITEM",item);
			//consumedAmt
			//dueAmount
			if(item.depositeFlag == 'Y')
				{
					var diffAmt = parseFloat($scope.dueAmount)- parseFloat($scope.consumedAmt);
					if(diffAmt > item.availableDeposit)
						{
							$scope.consumedAmt = (parseFloat($scope.consumedAmt)+parseFloat(item.availableDeposit)).toFixed(2);
							$scope.availableDeposits[idx].consumedDeposit = parseFloat(item.availableDeposit);
							$scope.availableDeposits[idx].availableDeposit = parseFloat(0);
						}
					else if(diffAmt < item.availableDeposit)
						{
							$scope.consumedAmt = (parseFloat($scope.consumedAmt)+parseFloat(diffAmt)).toFixed(2);
							$scope.availableDeposits[idx].availableDeposit = parseFloat(item.availableDeposit)- parseFloat(diffAmt);
							$scope.availableDeposits[idx].consumedDeposit = parseFloat(diffAmt);
						}
				}
			if(item.depositeFlag == 'N')
			{
				
						$scope.consumedAmt = (parseFloat($scope.consumedAmt) - parseFloat(item.consumedDeposit)).toFixed(2);
						$scope.availableDeposits[idx].availableDeposit = parseFloat(item.consumedDeposit);
						$scope.availableDeposits[idx].consumedDeposit = parseFloat(0);
					
			}
			
			if(parseFloat($scope.dueAmount) == parseFloat($scope.consumedAmt))
				{
					$scope.depositeDisabledFlag = true;
				}
			
		}
		
		$scope.checkAciveDepo  = function(useActiveDepo)
		{
			if(useActiveDepo == true)
				{
					var i = 0;
					angular.forEach($scope.availableDeposits,function(value,key){
						if(value.depositTypeId == '1'){
						$scope.activeDeposit = $scope.activeDeposit + parseFloat(value.availableDeposit);
						$scope.availableDeposits[i].depositeFlag = 'N';
						}
						
						i++;
					});
				}
			else
				{
				var i = 0;
					angular.forEach($scope.availableDeposits,function(value,key){
						if(value.depositTypeId == '1'){
						//$scope.consumedAmt 
							if(value.depositeFlag == 'Y'){
								$scope.consumedAmt = $scope.consumedAmt - $scope.availableDeposits[i].consumedDeposit;
							}
						$scope.availableDeposits[i].availableDeposit = $scope.availableDeposits[i].depositAmount;
						$scope.availableDeposits[i].consumedDeposit = parseFloat(0);
						$scope.activeDeposit = $scope.activeDeposit - parseFloat(value.depositAmount);
						$scope.availableDeposits[i].depositeFlag = 'N';
						}
						
						i++;
					});
				}
		}
		
		/*Vivek end*/
		
	}catch(e){
		console.log(e.message);
	}
}]);