/**
 * @author Nikhil
 * 		Controller to handle OPD Billing
 */

angular.module("myApp").controller("OpdBillingController", [ '$scope','$rootScope','$cookies','$state','BillingGenericService','$stateParams','$timeout',function($scope,$rootScope,$cookies,$state,BillingGenericService,$stateParams,$timeout){
	var cookieObject = $cookies.getObject('cookieObject');
	if(cookieObject == undefined){
		$state.go('login');
		return;
	}
	
	var cookieObjectAppConfig = $cookies.getObject('cookieObjectAppConfig');
	if(cookieObjectAppConfig == undefined){
		$state.go('login');
		return;
	}
	else
		{
			console.log('cookieObjectAppConfig',cookieObjectAppConfig)
		}
	
	$scope.defaultSelfTariffId = cookieObjectAppConfig.defaultSelfTariffId;
	$scope.isPreOrPostBilling = cookieObjectAppConfig.isPreOrPostBilling;
	$scope.unitId = cookieObject.unitId;
	$scope.organizationId = cookieObject.organizationId;
	$scope.sexMasterList=[];
	$scope.tempServiceDetailsList=[];
	$scope.tempOrderDetailsList=[];
	$scope.paymentDetailsList=[];
	$scope.discounDetailstList=[];
	$scope.autoRenderedServicesList=[];
	$scope.encounterNumberList=[];
	$scope.userId = 1;
	$scope.visitTypeId=1;
	$scope.currentDate = new Date();
	$scope.isDiscountApplicable=false;
	$rootScope.loginpage = true;
	$scope.amountReceivedFrom="";
	$scope.identificaitonNo="";
	$scope.initTotalAmounts=function(){
		$scope.totalPaymentAmount=parseFloat(0).toFixed(2);
		$scope.dueAmount=parseFloat(0).toFixed(2);
		$scope.refundAmount=parseFloat(0).toFixed(2);
		$scope.totalAmountSummary=parseFloat(0).toFixed(2);
		$scope.totalConcessionSummary=parseFloat(0).toFixed(2);
		$scope.netPayableSummary=parseFloat(0).toFixed(2);
		$scope.totalPayingAmount=parseFloat(0).toFixed(2);
		$scope.totalDiscountSummary=parseFloat(0).toFixed(2);
		//----- for AutoRendered Services --------//
		$scope.AutoRenderedTotalAmountSummary=parseFloat(0).toFixed(2);
		$scope.AutoRenderedTotalConcessionSummary=parseFloat(0).toFixed(2);
		$scope.AutoRenderednetPayableSummary=parseFloat(0).toFixed(2);
		$scope.AutoRenderednetTotalDiscountSummary=parseFloat(0).toFixed(2);
	}
	$scope.initTotalAmounts();
	
	$scope.initPatientSearchObj = function(){
		$scope.patientSearchObj = {
				"uhIdNumber"      : "",
				"patientName"     : "",
				"genderId"        : "",
				"mobileNo"        : "",
				"birthDate"       : "",
				"identificationNo":"",
				"patientType"	  :"1",
				"unitId"          :$scope.unitId,
				"organizationId"  :$scope.organizationId
		}
	}
	$scope.initPatientSearchObj();
	
	$scope.patientDetails = {
			"patientId"	  : "",
			"patientName" : "",
			"genderCode"  : "",
			"age"         : "", 
			"uhIdNumber"  : "",
			"birthDate"	  : "",
			"encounterId" : ""
	}
	

	$scope.getAutoRenderedServicesByPatient=function(encounterObj){
		$scope.autoRenderedServicesBillingDetails=[];
		var URI = BASE_URL + ROOT_URL + "/api/billing/serviceMaster/autoRendered";
		var obj={
				"unitId":$scope.unitId,
				"organizationId":$scope.organizationId,
				"patientId":encounterObj.patientId,
				"visitTypeId":encounterObj.visitTypeId,
				"bedCategoryId":1, //By default its OPD ---> Id==1
				"patientTypeId":1, //need to add after patient registration 
				"paymentEntitlementTypeId":encounterObj.paymentEntitlementTypeId,
				"doctorId":encounterObj.doctorId,
				"defaultSelfTariffId":$scope.defaultSelfTariffId
				
		}
		BillingGenericService.serviceAction(METHOD_POST,URI, obj, NOTIFICATION_MSG_STATUS_FALSE).then(function(response){
			if(response.data.status == "success"){
				$scope.autoRenderedServicesList=response.data.listObject;
				//-----calculate amounts
				console.log($scope.autoRenderedServicesList);
				
				angular.forEach($scope.autoRenderedServicesList, function (value, key) {
				//	var basePriceObj=$scope.getServiceBasePriceByServiceId(value.servceMasterId);
					
					//-------------- get base price-----------------//
					/*var obj={
							 "orgId":$scope.organizationId,
							 "unitId":$scope.unitId,
							 "visitTypeId":$scope.visitTypeId,
							 "bedTypeId":1,
							 "patientTypeId":1,
							 "paymentTypeId":1,
							 "listServiceId":[value.serviceMasterId]
							}*/
					
					//$rootScope.startLoader();
				//	var URI = BASE_URL + ROOT_URL + BILLING + "/variable-pricing/getServiceCharges";
				//	BillingGenericService.serviceAction(METHOD_POST,URI, obj, NOTIFICATION_MSG_STATUS_FALSE).then(function(response2){
				//		console.log(response2);
				//		if(response.data.status=="success"){
						//	var basePriceObj=response2.data.object[0];
						//	console.log(basePriceObj);
							//value.basePrice=basePriceObj.basePrice;
							console.log(value.basePrice, "#", value.quantity);
							value.netPayable=(parseFloat(value.quantity) * parseFloat(value.basePrice) - value.concession - value.discount).toFixed(2);
							
							$scope.AutoRenderedTotalAmountSummary=(parseFloat($scope.AutoRenderedTotalAmountSummary) + parseFloat(1) * parseFloat(value.basePrice)).toFixed(2);
							$scope.AutoRenderedTotalConcessionSummary=(parseFloat($scope.AutoRenderedTotalConcessionSummary) + parseFloat(value.concession)).toFixed(2);
							$scope.AutoRenderednetPayableSummary=(parseFloat($scope.AutoRenderednetPayableSummary) + parseFloat(value.netPayable)).toFixed(2);
							$scope.AutoRenderednetTotalDiscountSummary=(parseFloat($scope.AutoRenderednetTotalDiscountSummary) + parseFloat(0)).toFixed(2);
							//alert($scope.AutoRenderedTotalAmountSummary+"#"+$scope.AutoRenderedTotalConcessionSummary+"#"+$scope.AutoRenderednetPayableSummary);
							$scope.autoRenderedServicesBillingDetails.push($scope.createBillingDetailsObj(value));
							
							/*$scope.totalAmountSummary=(parseFloat($scope.totalAmountSummary) + parseFloat($scope.AutoRenderedTotalAmountSummary)).toFixed(2);
							$scope.totalConcessionSummary=(parseFloat($scope.totalConcessionSummary) + parseFloat($scope.AutoRenderedTotalConcessionSummary)).toFixed(2);
							$scope.netPayableSummary=(parseFloat($scope.netPayableSummary) + parseFloat($scope.AutoRenderednetPayableSummary)).toFixed(2);
							$scope.totalDiscountSummary=(parseFloat($scope.totalDiscountSummary) + parseFloat($scope.AutoRenderednetTotalDiscountSummary)).toFixed(2);*/
						//}
					//});
					
				});// end of loop
				//alert("AUto-summary"+$scope.AutoRenderednetPayableSummary);
				$scope.totalAmountSummary=(parseFloat($scope.AutoRenderedTotalAmountSummary)).toFixed(2);
				$scope.totalConcessionSummary=(parseFloat($scope.AutoRenderedTotalConcessionSummary)).toFixed(2);
				$scope.netPayableSummary=(parseFloat($scope.AutoRenderednetPayableSummary)).toFixed(2);
				$scope.totalDiscountSummary=(parseFloat($scope.AutoRenderednetTotalDiscountSummary)).toFixed(2);
			}
		});
	}
	
	
	//------------ check patient is arrived from scheduled appointment ----------//
	$scope.checkPatient = function(){
		console.log("s",$stateParams);
		 $scope.encounterObj=$stateParams.encounterObj;
		if($scope.encounterObj.patientId != undefined && $scope.encounterObj.encounterId != undefined){
			$rootScope.startLoader();
			var URI = BASE_URL + ROOT_URL + "/api/global/patient/"+$scope.encounterObj.patientId;
			BillingGenericService.serviceAction(METHOD_GET,URI, "", NOTIFICATION_MSG_STATUS_FALSE).then(function(response){
				$scope.patientDetails.patientId = response.data.object.patientId;
				$scope.patientDetails.patientName = response.data.object.patientName;
				$scope.patientDetails.genderCode = response.data.object.genderCode;
				$scope.patientDetails.age = response.data.object.age;
				$scope.patientDetails.uhIdNumber = response.data.object.UHIDNumber;
				$scope.patientDetails.birthDate = response.data.object.dob;
				$scope.getAutoRenderedServicesByPatient($scope.encounterObj);
				$scope.getEncounterDetailsById($scope.encounterObj.encounterId);
				$scope.patientDetails.encounterId = $scope.encounterObj.encounterId;
			});
		}
	}
	$scope.checkPatient();
	
	
	$scope.initBillingMaster = function(){
		$scope.billingMasterObj = {
				"billingMasterId"   :"",
				"encounterId"		:$scope.patientDetails.encounterId,
				"visitTypeId"		:$scope.visitTypeId,
				"patientId"			:$scope.patientDetails.patientId,
				"billNumber"		:"",
				"billDateTime"		:new Date().getTime(),
				"netAmount"			:"",
				"discountAmount"	:"",
				"grossAmount"		:"",
				"concessionAmount"	:"",
				"gst"				:"",
				"totalBillAmount"	:"",
				"outstanding"		:"",
				"roundOff"			:"",	
				"isCreditBill"		:"",
				"unitId"			:$scope.unitId,
				"organisationId"	:$scope.organizationId,
				"status"			:"A",
				"createdDate"		:new Date().getTime(),
				"createdBy"			:$scope.userId,
				"updatedDate"		:new Date().getTime(),
				"updatedBy"			:$scope.userId,
				"bedTypeId":"1",
				"patientTypeId":"1",
				"paymentTypeId":"1",
				"isDiscountInPercentage":'I',
				"isDiscountApplicable":"I",
				"listBillingDetailsDto":[],
				"listAutoRenderedServiesBillingDetails":[],
			//	"orderDetailsDiscountDto":"",
				"orderMasterDto"		:{
						"orderId"		:"",
						"orgId"			:$scope.organizationId,
						"orgUnitId"		:$scope.unitId,
						"orderNo"		:"",
						"orderDate"		:new Date().getTime(),
						"visitTypeId"	:"1",
						"encounterId"	:$scope.patientDetails.encounterId,
						"ordDoctorId"	:"",
						"patientId"		:$scope.patientDetails.patientId,
						"priorityId"	:"2",
						"patientType"	:"",
						"wardId"		:"",
						"drCrId"		:"",
						"ordRemarks"	:"",
						"createdBy"		:"1",
						"createdDate"	:new Date().getTime(),
						"updatedBy"		:"1",
						"updatedDate"	:new Date().getTime(),
						"orderStatus"	:"A",
						"orderStatusId"	:"1",
						"listOrderDetailsMasterDto":[]
					}
				
		}
		
	}
	$scope.initBillingMaster();
	
	
	$scope.getSexMasterList = function(){
		$rootScope.startLoader();
		var URI = BASE_URL + ROOT_URL + "/adt/getActiveGenderList";
		BillingGenericService.serviceAction(METHOD_GET,URI, "", NOTIFICATION_MSG_STATUS_FALSE).then(function(response){
			$scope.sexMasterList = response.data.listObject;
		});
	}
	$scope.getSexMasterList();
	
	$scope.getEncounterDetailsById = function(encounterId){
		$rootScope.startLoader();
		var URI = BASE_URL + ROOT_URL + API + OPD + "/encounter/"+encounterId;
		BillingGenericService.serviceAction(METHOD_GET,URI, "", NOTIFICATION_MSG_STATUS_FALSE).then(function(response){
			if(response.data.status == "success"){
				$scope.encounterDetails = response.data.object;
				$scope.encounterNumberList=[];
				var encounterNumberObj={encounterId : $scope.encounterDetails.encounterId, encounterNumber:$scope.encounterDetails.encounterNumber }
				$scope.encounterNumberList.push(encounterNumberObj);
			
				console.log("encounterList",$scope.encounterNumberList);
			}
			
		});
	}
	
	$rootScope.searchPatient = function(patientsearchObj){
		$rootScope.startLoader();
		var URI = BASE_URL + ROOT_URL + "/adt/patientSearchByMultipleCriteria";
		BillingGenericService.serviceAction(METHOD_POST,URI, patientsearchObj, NOTIFICATION_MSG_STATUS_FALSE).then(function(response){
			$scope.patientList = response.data.listObject;
		});
	}
	
	$scope.getPatientDetailsByIndex = function(index){
		$rootScope.startLoader();
		$scope.patientDetails.age=($scope.patientList[index].age);
		$scope.patientDetails.patientName=($scope.patientList[index].patientName);
		$scope.patientDetails.genderCode=($scope.patientList[index].genderCode);
		$scope.patientDetails.uhIdNumber=($scope.patientList[index].uhIdNumber);
		$scope.patientDetails.patientId=($scope.patientList[index].patientId);
		$scope.patientDetails.birthDate=($scope.patientList[index].birthDate);
		
		$rootScope.stopLoader();
	}
	
	$scope.search="";
	$scope.searchServices = function(searchKeyword){
		$scope.servicesList=[];
		var URI = BASE_URL + ROOT_URL + "/global/serviceMaster/"+searchKeyword+"/unit/"+$scope.unitId+"/org/"+$scope.organizationId;
		return BillingGenericService.serviceAction(METHOD_GET,URI, "", NOTIFICATION_MSG_STATUS_FALSE).then(function(response){
			if(response.data.status == "success"){
				$scope.servicesList = response.data.listObject;
			}
			return $scope.servicesList;
		});
	}
	
	
    var isExists;
    function checkExistsOrNot(arr, val) {
     return arr
       .some(function(arr) {
        return (val.serviceMasterId === arr.serviceMasterId)
       });
    }
    
    $scope.createBillingDetailsObj = function(item){
    	var obj = {
    		"billingDetailsId"	:"",
    		"billingMasterId"	:"",
    		"serviceId"			:item.serviceMasterId,
    		"rate"				:"",
    		"quantity"			:item.quantity,
    		"concession"		:item.concession,
    		"concessionAmount"	:"",
    		"grossAmount"		:(item.basePrice * item.quantity) - item.concession,
    		"coPayPercentage"	:"",
    		"unitId"			:$scope.unitId,
    		"organizationId"	:$scope.organizationId,
    		"status"			:"A",
    		"createdBy"			:$scope.userId,
    		"updatedBy"			:$scope.userId,
    		"createdDate"		:new Date().getTime(),
    		"updatedDate"		:new Date().getTime(),
    		"serviceMasterId"	:item.serviceMasterId,
	    	"serviceStandardName":item.serviceStandardName,
	    	"serviceStandardCode":item.serviceStandardCode,
	    	"specialityId"		:item.specialityId,
	    	"specialityName"	:item.specialityName,
	    	"subSpecialityId"	:item.subSpecialityId,
	    	"subSpecialityName"	:item.subSpecialityName,
	    	"basePrice"			:item.basePrice,
	    	"discount"			:(item.discount == undefined || item.discount == '') ? '0' : item.discount,
	    	"discountPercentage":item.discountPercentage, // direct discount percentage
	    	"discountAmount"	:item.discountAmount, // direct discount amount
	    	"orderDetailsId"	:item.orderDetailsId
    	}
    	return obj;
    }
    
    $scope.createOrderDetailsObj = function(item){
    	var obj={
			"orderDetailsId":"",
			"orgId"			:$scope.organizationId,
			"orgUnitId"		:$scope.unitId,
			"orderId"		:"",
			"orderQty"		:"",
			"serviceId"		:item.serviceMasterId,
			"priorityId"	:"2",
			"isOutsourced"	:"N",
			"orderDate"		:new Date().getTime(),
			"orderSchDate"	:new Date().getTime(),
			"serviceAmt"	:"",
			"ordConcession"	:"",
			"ordDiscount"	:"",
			"netAmount"		:"",
			"tariffId"		:"",
			"billingClassId":"",
			"bedId"			:"",
			"roomId"		:"",
			"wardId"		:"",
			"orderEmergencyFlag":"",
			"packageId"		:"",
			"ordDocId"		:"",
			"drugId"		:"",
			"batchId"		:"",
			"oldOrdDtlId"	:"",
			"serviceRendered":"0",
			"serviceChargeable":"",
			"serviceRenderingDeptId":"8",
			"ordRenderDatetime":new Date().getTime(),
			"serviceIsBilled":"1",
			"serviceBillId"	:"",
			"ordRemarks"	:"",
			"createdDate"	:new Date().getTime(),
			"createdBy"		:$scope.userId,
			"ordCancelled"	:'N',
			"ordCancelReasonId":"",
			"ordCancelDatetime":new Date().getTime(),
			"status"		:"A",
			"updatedBy"		:$scope.userId,
			"updatedDate"	:new Date().getTime(),
		}
    	return obj;
    }
    
	$scope.addServiceToTempServiceList = function($item, $model, $label){
		console.log($item);
		var billingDetailsObj = $scope.createBillingDetailsObj($item)
		var isExist = checkExistsOrNot($scope.tempServiceDetailsList,billingDetailsObj);
		if(!isExist){
			//var basePriceObj=$scope.getServiceBasePriceByServiceId(billingDetailsObj.serviceMasterId);
				var obj={
						/* "orgId":$scope.organizationId,
						 "unitId":$scope.unitId,
						 "visitTypeId":$scope.visitTypeId,
						 "bedTypeId":1,
						 "patientTypeId":1,
						 "paymentTypeId":1,
						 "listServiceId":[billingDetailsObj.serviceMasterId]*/
				
				
							"unitId":$scope.unitId,
							"organizationId":$scope.organizationId,
							"visitTypeId":1,// by default its op
							"billingBedCategoryId":1, //By default its OPD ---> Id==1
							"patientTypeId":1, //need to add after patient registration 
							"paymentEntitlementId":$scope.encounterObj.paymentEntitlementTypeId,
							"tariffId":$scope.defaultSelfTariffId,
							"orderDate":moment(new Date()).format('YYYY-MM-DD HH:mm:ss'),
							"serviceList":[billingDetailsObj.serviceMasterId]
				
						}
				console.log('Base prise',obj)
				$rootScope.startLoader();
				var URI = BASE_URL + ROOT_URL + "/api/unit/getBasePriceOfServicesFromTariffMasterByServiceList";
				BillingGenericService.serviceAction(METHOD_POST,URI, obj, NOTIFICATION_MSG_STATUS_FALSE).then(function(response){
					if(response.data.status=="success"){
						var basePriceObj=response.data.object;
						console.log('rate',basePriceObj);
						billingDetailsObj.basePrice=basePriceObj.rate;
						billingDetailsObj.quantity=1;
						//concession
						console.log(billingDetailsObj);
						billingDetailsObj.netPayable=(parseFloat(basePriceObj.rate) * parseFloat(billingDetailsObj.quantity) - parseFloat(billingDetailsObj.concession)).toFixed(2);
						billingDetailsObj.grossAmount=billingDetailsObj.netPayable;
						console.log(billingDetailsObj);
						$scope.tempServiceDetailsList.push(billingDetailsObj);
						/*var orderDetailsObj = $scope.createOrderDetailsObj($item);
						orderDetailsObj.ordConcession=parseFloat(billingDetailsObj.concession).toFixed(2);
						orderDetailsObj.ordDiscount= parseFloat(0).toFixed(2);
						orderDetailsObj.netAmount=(parseFloat(basePriceObj.basePrice) * parseFloat(billingDetailsObj.quantity) - parseFloat(billingDetailsObj.concession)).toFixed(2);
						orderDetailsObj.orderQty=billingDetailsObj.quantity;
						orderDetailsObj.serviceAmt=(parseFloat(basePriceObj.basePrice) * parseFloat(billingDetailsObj.quantity)).toFixed(2);
						$scope.tempOrderDetailsList.push(orderDetailsObj);*/
						
					}
				});
			
		}
		console.log($scope.tempServiceDetailsList);
		$scope.search = "";
	}
	
	$scope.removeTempServicesByIndex = function(index){
		var serviceIndex = $scope.tempServiceDetailsList.indexOf(index);
		$scope.tempServiceDetailsList.splice(serviceIndex, 1);
		
		//var orderIndex = $scope.tempOrderDetailsList.indexOf(index)
		//$scope.tempOrderDetailsList.splice(orderIndex, 1);
		
	}
	
	$scope.addTempServiesToServiceMasterList = function(){
		var orderDetailsArray=[];
		$scope.totalAmountSummary=parseFloat(0).toFixed(2);
		$scope.totalConcessionSummary=parseFloat(0).toFixed(2);
		$scope.netPayableSummary=parseFloat(0).toFixed(2);
		$scope.totalDiscountSummary=parseFloat(0).toFixed(2);
		
		$scope.serviceMasterList = angular.copy($scope.tempServiceDetailsList);
		//$scope.orderDetailsList =  angular.copy($scope.tempOrderDetailsList);
		//alert();
		console.log($scope.serviceMasterList);
		console.log("totalAmountSummary",$scope.totalAmountSummary);
		console.log("totalConcessionSummary",$scope.totalConcessionSummary);
		console.log("netPayableSummary",$scope.netPayableSummary);
		
		angular.forEach($scope.serviceMasterList, function (value, key) {
			
			//alert(value.discount +"#"+ parseFloat(value.discount));
			value.grossAmount=((value.quantity * value.basePrice) - value.concession - value.discount).toFixed(2);
			//value.netAmount = value.grossAmount;
			$scope.totalAmountSummary=(parseFloat($scope.totalAmountSummary) + parseFloat(value.quantity) * parseFloat(value.basePrice)).toFixed(2);
			$scope.totalConcessionSummary=(parseFloat($scope.totalConcessionSummary) +parseFloat(value.concession)).toFixed(2);
			
			$scope.netPayableSummary=(parseFloat($scope.netPayableSummary) + (parseFloat(value.basePrice) * parseFloat(value.quantity))- parseFloat(value.concession) - parseFloat(value.discount)).toFixed(2);
			$scope.totalDiscountSummary=(parseFloat($scope.totalDiscountSummary) +parseFloat(0)).toFixed(2);
			
			//----- add order details -------//
			var orderDetailsObj = $scope.createOrderDetailsObj(value);
			orderDetailsObj.ordConcession=parseFloat(value.concession).toFixed(2);
			orderDetailsObj.ordDiscount= parseFloat(0).toFixed(2);
			orderDetailsObj.netAmount=(parseFloat(value.basePrice) * parseFloat(value.quantity) - parseFloat(value.concession)).toFixed(2);
			orderDetailsObj.orderQty=value.quantity;
			orderDetailsObj.serviceAmt=(parseFloat(value.basePrice) * parseFloat(value.quantity)).toFixed(2);
			orderDetailsArray.push(orderDetailsObj);
			
		});
		$scope.orderDetailsList =  angular.copy(orderDetailsArray);
		 angular.element('#addServicePopup').modal('hide');
		 console.log("totalAmountSummary",$scope.totalAmountSummary);
			console.log("totalConcessionSummary",$scope.totalConcessionSummary);
			console.log("netPayableSummary",$scope.netPayableSummary);
			console.log($scope.orderDetailsList);
			/*$scope.AutoRenderedTotalAmountSummary=parseFloat(0).toFixed(2);
			$scope.AutoRenderedTotalConcessionSummary=parseFloat(0).toFixed(2);
			$scope.AutoRenderednetPayableSummary=parseFloat(0).toFixed(2);*/
			
			//---------- Add Auto-rendered services Amount summary to total Amount summary ---------//
			$scope.totalAmountSummary=(parseFloat($scope.totalAmountSummary) + parseFloat($scope.AutoRenderedTotalAmountSummary)).toFixed(2);
			$scope.totalConcessionSummary=(parseFloat($scope.totalConcessionSummary) + parseFloat($scope.AutoRenderedTotalConcessionSummary)).toFixed(2);
			$scope.netPayableSummary=(parseFloat($scope.netPayableSummary) + parseFloat($scope.AutoRenderednetPayableSummary)).toFixed(2);
			$scope.totalDiscountSummary=(parseFloat($scope.totalDiscountSummary) + parseFloat($scope.AutoRenderednetTotalDiscountSummary)).toFixed(2);
			
	}	

	$scope.removeServicesByIndex = function(index){
		//alert(index);
		$scope.totalAmountSummary=parseFloat(0).toFixed(2);
		$scope.totalConcessionSummary=parseFloat(0).toFixed(2);
		$scope.netPayableSummary=parseFloat(0).toFixed(2);
		
		var serviceIndex = $scope.serviceMasterList.indexOf(index);
		$scope.serviceMasterList.splice(serviceIndex, 1);
		
		var orderIndex = $scope.orderDetailsList.indexOf(index)
		$scope.orderDetailsList.splice(orderIndex, 1);
		
		angular.forEach($scope.serviceMasterList, function (value, key) {
			$scope.totalAmountSummary=(parseFloat($scope.totalAmountSummary) + parseFloat(value.quantity) * parseFloat(value.basePrice)).toFixed(2);
			$scope.totalConcessionSummary=(parseFloat($scope.totalConcessionSummary) + parseFloat(value.concession)).toFixed(2);
			
			$scope.netPayableSummary=(parseFloat($scope.netPayableSummary) + (parseFloat(value.basePrice) * parseFloat(value.quantity)) - parseFloat(value.concession) - parseFloat(value.discount)).toFixed(2);
			$scope.totalDiscountSummary=(parseFloat($scope.totalDiscountSummary) + parseFloat(0)).toFixed(2);
		});
		
		$scope.totalAmountSummary=(parseFloat($scope.totalAmountSummary) + parseFloat($scope.AutoRenderedTotalAmountSummary)).toFixed(2);
		$scope.totalConcessionSummary=(parseFloat($scope.totalConcessionSummary) + parseFloat($scope.AutoRenderedTotalConcessionSummary)).toFixed(2);
		$scope.netPayableSummary=(parseFloat($scope.netPayableSummary) + parseFloat($scope.AutoRenderednetPayableSummary)).toFixed(2);
		$scope.totalDiscountSummary=(parseFloat($scope.totalDiscountSummary) + parseFloat($scope.AutoRenderednetTotalDiscountSummary)).toFixed(2);
		
		console.log("totalAmountSummary",$scope.totalAmountSummary);
		console.log("totalConcessionSummary",$scope.totalConcessionSummary);
		console.log("netPayableSummary",$scope.netPayableSummary);
		
		$scope.removeTempServicesByIndex(index);
	}
	
	$scope.removeAutoRenderedServicesByIndex = function(index){
		
		//alert();
		$scope.totalAmountSummary=(parseFloat($scope.totalAmountSummary) - parseFloat($scope.AutoRenderedTotalAmountSummary)).toFixed(2);
		$scope.totalConcessionSummary=(parseFloat($scope.totalConcessionSummary) - parseFloat($scope.AutoRenderedTotalConcessionSummary)).toFixed(2);
		$scope.netPayableSummary=(parseFloat($scope.netPayableSummary) - parseFloat($scope.AutoRenderednetPayableSummary)).toFixed(2);
		$scope.totalDiscountSummary=(parseFloat($scope.totalDiscountSummary) - parseFloat($scope.AutoRenderednetTotalDiscountSummary)).toFixed(2);
		
		$scope.AutoRenderedTotalAmountSummary=parseFloat(0).toFixed(2);
		$scope.AutoRenderedTotalConcessionSummary=parseFloat(0).toFixed(2);
		$scope.AutoRenderednetPayableSummary=parseFloat(0).toFixed(2);
		$scope.AutoRenderednetTotalDiscountSummary=parseFloat(0).toFixed(2);
		//alert($scope.totalAmountSummary +"#"+$scope.totalConcessionSummary+"#"+$scope.netPayableSummary);
		
		var serviceIndex = $scope.autoRenderedServicesList.indexOf(index);
		$scope.autoRenderedServicesList.splice(serviceIndex, 1);
		
		var billingDetailsIndex = $scope.autoRenderedServicesBillingDetails.indexOf(index);
		$scope.autoRenderedServicesBillingDetails.splice(billingDetailsIndex, 1);
		
		
		angular.forEach($scope.autoRenderedServicesList, function (value, key) {
			$scope.AutoRenderedTotalAmountSummary=(parseFloat($scope.AutoRenderedTotalAmountSummary) + parseFloat(value.quantity) * parseFloat(value.basePrice)).toFixed(2);
			$scope.AutoRenderedTotalConcessionSummary=(parseFloat($scope.AutoRenderedTotalConcessionSummary) + parseFloat(value.concession)).toFixed(2);
			$scope.AutoRenderednetPayableSummary=(parseFloat($scope.AutoRenderednetPayableSummary) + (parseFloat(value.basePrice) * parseFloat(value.quantity)) - parseFloat(value.concession) - parseFloat(value.discount)).toFixed(2);
			$scope.AutoRenderednetTotalDiscountSummary=(parseFloat($scope.AutoRenderednetTotalDiscountSummary) + parseFloat(0)).toFixed(2);
		});
		
		$scope.totalAmountSummary=(parseFloat($scope.totalAmountSummary) + parseFloat($scope.AutoRenderedTotalAmountSummary)).toFixed(2);
		$scope.totalConcessionSummary=(parseFloat($scope.totalConcessionSummary) + parseFloat($scope.AutoRenderedTotalConcessionSummary)).toFixed(2);
		$scope.netPayableSummary=(parseFloat($scope.netPayableSummary) + parseFloat($scope.AutoRenderednetPayableSummary)).toFixed(2);
		$scope.totalDiscountSummary=(parseFloat($scope.totalDiscountSummary) + parseFloat($scope.AutoRenderednetTotalDiscountSummary)).toFixed(2);
		
	}
	
	//------- get pase price by service_id (variable pricing)----------// 
	$scope.getServiceBasePriceByServiceId = function(serviceId){
		var obj={
				 "orgId":$scope.organizationId,
				 "unitId":$scope.unitId,
				 "visitTypeId":$scope.visitTypeId,
				 "bedTypeId":1,
				 "patientTypeId":1,
				 "paymentTypeId":1,
				 "listServiceId":[serviceId]
				}
		
		$rootScope.startLoader();
		var URI = BASE_URL + ROOT_URL + BILLING + "/variable-pricing/getServiceCharges";
		BillingGenericService.serviceAction(METHOD_POST,URI, obj, NOTIFICATION_MSG_STATUS_FALSE).then(function(response){
			if(response.data.status=="sucsess"){
				return response.data.listObject[0];
			}
		});
	}
	
	
	$scope.saveBillings = function(){
		console.log("encounterObj",$scope.encounterObj)
		$rootScope.startLoader();
		$scope.billingMasterObj.listBillingDetailsDto = angular.copy($scope.serviceMasterList);
		$scope.billingMasterObj.listAutoRenderedServiesBillingDetails =angular.copy($scope.autoRenderedServicesBillingDetails);
		$scope.billingMasterObj.orderMasterDto.listOrderDetailsMasterDto = angular.copy($scope.orderDetailsList);
		//$scope.billingMasterObj.listOrderDetailsDiscountDto=angular.copy($scope.discounDetailstList);
		if($scope.isDiscountApplicable)
			$scope.billingMasterObj.orderDetailsDiscountDto=$scope.discountObj;
		$scope.billingMasterObj.encounterId=$scope.patientDetails.encounterId;
		$scope.billingMasterObj.patientId=$scope.patientDetails.patientId;
		$scope.billingMasterObj.orderMasterDto.encounterId=$scope.patientDetails.encounterId;
		$scope.billingMasterObj.orderMasterDto.patientId=$scope.patientDetails.patientId;
		$scope.billingMasterObj.orderMasterDto.doctorId=$scope.encounterObj.doctorId;
		$scope.billingMasterObj.discountAmount=$scope.totalDiscountSummary;
		$scope.billingMasterObj.concessionAmount=$scope.totalConcessionSummary;
		$scope.billingMasterObj.netAmount=$scope.netPayableSummary;
		$scope.billingMasterObj.totalBillAmount=$scope.totalAmountSummary;
		$scope.billingMasterObj.orderDate=moment($scope.currentDate);
		$scope.billingMasterObj.tariffId=$scope.defaultSelfTariffId;
		
		var URI = BASE_URL + ROOT_URL + "/api/billing/billingMaster";
		console.log("billingMaster:", $scope.billingMasterObj);
		BillingGenericService.serviceAction(METHOD_POST,URI, $scope.billingMasterObj , NOTIFICATION_MSG_STATUS_TRUE).then(function(response){
			console.log(response)
			if(response.data.status == 'success'){
				$scope.billingMaster = response.data.object;
				$scope.totalPaymentAmount=$scope.billingMaster.netAmount;
				$scope.initBillingMaster();
				$scope.serviceMasterList=[];
				$scope.orderDetailsList=[];
				$scope.tempServiceDetailsList=[];
				$scope.tempOrderDetailsList=[];
				$scope.autoRenderedServicesBillingDetails=[];
				$scope.autoRenderedServicesList=[];
				$scope.isDiscountApplicable=false;
				$scope.isDiscountPercentageDisabled=false;
				$scope.isDiscountAmountDisabled=false;
				$scope.initDiscountObj();
			}
			
		});
	}
	
	//-----------------payment Collection----------------//
	$scope.initPaymentDetails=function(){
		$scope.paymentDetailsObj={
				"paymentRecieptDetailsId":"",
				"paymentModeId"			:"1",
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
		$scope.paymentDetailsObj.paymentModeId=paymentModeId;
		$scope.paymentDetailsObj.paymentModeName=$scope.paymentModes[paymentModeId];
	}
	
	$scope.addPaymentDetails = function(){
		var paymentObj = angular.copy($scope.paymentDetailsObj);
		$scope.paymentDetailsList.push(paymentObj);
		$scope.totalPayingAmount=(parseFloat($scope.totalPayingAmount) + parseFloat(paymentObj.amount)).toFixed(2);
		$scope.setFirstTabLevel(1);
		$scope.setPaymentModeId(1);
		$scope.initPaymentDetails();
		$scope.getDueAmount();
	}
	
	$scope.setBankName=function(bankId){
		angular.forEach($scope.bankList, function (value, key) {
			if(value.bankId == bankId){
				$scope.paymentDetailsObj.bankName = value.bankName;
				return;
			}
		});
		
	}
	
	$scope.getDueAmount = function(){
		var total = 0;
	    for(var i = 0; i < $scope.paymentDetailsList.length; i++){
	        var payment = $scope.paymentDetailsList[i];
	        total += parseFloat(payment.amount);
	    }
	    $scope.dueAmount= (parseFloat($scope.totalPaymentAmount) - parseFloat(total)).toFixed(2);
	    if(parseFloat($scope.dueAmount) < 0){
	    	$scope.refundAmount = (parseFloat(total) - parseFloat($scope.totalPaymentAmount)).toFixed(2);
	    	$scope.dueAmount=parseFloat(0).toFixed(2);
	    }else{
	    	$scope.refundAmount=parseFloat(0).toFixed(2);
	    }
	    
	}
	
	$scope.removePaymentDetails = function(index){
		index = $scope.paymentDetailsList.indexOf(index);
		$scope.paymentDetailsList.splice(index, 1);
		$scope.getDueAmount();
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
	
	$scope.collectPayment=function(){
		var paymentObj={
				"paymentRecieptId":"",
				"recieptNumber":"",
				"billingMasterId":$scope.billingMaster.billingMasterId,
				"dateOfReciept":new Date().getTime(),
				"unitId":$scope.unitId,
				"orgnisationId":$scope.organizationId,
				"status":"A",
				"createdDate":new Date().getTime(),
				"createdBy":$scope.userId,
				"updatedDate":new Date().getTime(),
				"updatedBy":$scope.userId,
				"payemetAgainstId":"1",
				"receiptType":"1",
				"counterId":"",
				"settlementId":"",
				"payeeId":"1",
				"receivedFrom":$scope.amountReceivedFrom,
				"identificationId":$scope.identificaitonNo,
				"remark":"",
				"isPrinted":"I",
				"amount":$scope.totalPayingAmount,
				"listPaymentReceiptDetailsDto":angular.copy($scope.paymentDetailsList),
				
		}
		console.log("Collect Payment",paymentObj);
		$rootScope.startLoader();
		var URI = BASE_URL + ROOT_URL + API + BILLING + "/collectPayment";
		BillingGenericService.serviceAction(METHOD_POST,URI, paymentObj, NOTIFICATION_MSG_STATUS_TRUE).then(function(response){
			console.log(response);
			if(response.data.status=='success'){
				
				
				if($scope.encounterObj.appointmentId!=undefined){
				var updateAppointmentStatusObj={
						
						"appointmentId": $scope.encounterObj.appointmentId,
						"updatedBy": $scope.userId,
						"updatedDate": moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
						"appointmentStatusId":5 //appointments Status Id 5 ==> Checked In
						
				}
				console.log(updateAppointmentStatusObj);
				var URI = BASE_URL + ROOT_URL + API + "/opd/updateAppointmentStatus";
				BillingGenericService.serviceAction(METHOD_POST,URI, updateAppointmentStatusObj, NOTIFICATION_MSG_STATUS_FALSE).then(function(response){
					
					$scope.initTotalAmounts();
					$scope.amountReceivedFrom="";
					$scope.identificaitonNo="";
					$scope.paymentDetailsList=[];
					$scope.initPaymentDetails();
					$scope.encounterDetails="";
					$scope.encounterNumberList=[];
					angular.element('#collectPayment').modal('hide');
					 $timeout( function(){
						 $state.go('appointmentList');
	                  },3000);
					
				});
			}else{
				angular.element('#collectPayment').modal('hide');
				 $timeout( function(){
					 $state.go('appointmentList');
                 },3000);
			}
			}
		});
	}
	//--------------- Discount Details --------------//
	$scope.initDiscountObj=function(){
		$scope.discountObj={
				"discountPercentage":"",
				"discountAmount"	:"",
				"discountCategoryId":"",
				"discountGivenBy"	:"",
				"remark"			:"",
				"discountDate"		:"",
				"isCancel"			:"I",
				"cancelBy"			:"",
				"createdBy"			:$scope.userId,
				"createdDate"		:new Date().getTime(),
				"updatedBy"			:$scope.userId,
				"updatedDate"		:new Date().getTime(),
				"status"			:"A",
				"unitId"			:$scope.unitId,
				"orgId"				:$scope.organizationId
		}
	}
	$scope.initDiscountObj();
	
	$scope.applyDiscount=function(){
		try{
			var roundOffAmount=parseFloat(0).toFixed(2);
			$scope.isDiscountApplicable=true;
			var autoRenderdSummary=parseFloat(0).toFixed(2);
			var servicesSummary=parseFloat(0).toFixed(2);
			
			var autoRenderdDiscount=parseFloat(0).toFixed(2);
			var serviceDiscount=parseFloat(0).toFixed(2);
			
			var totalAmount=parseFloat(0).toFixed(2);
			if($scope.discountObj.discountPercentage == ""){
				angular.forEach($scope.autoRenderedServicesList, function (value, key) {
					totalAmount=(parseFloat(totalAmount) + parseFloat(value.basePrice * value.quantity)).toFixed(2);
				});
				
				angular.forEach($scope.serviceMasterList, function (value, key) {
					totalAmount=(parseFloat(totalAmount) + parseFloat(value.basePrice * value.quantity)).toFixed(2);
				});
			}
			
			
			angular.forEach($scope.autoRenderedServicesList, function (value, key) {
				/*if($scope.discountObj.discountPercentage != ""){
					$scope.discountObj.discountPercentage=$scope.discountObj.discountAmount/(value.quantity * value.basePrice);
				}*/
				
				value.discountPercentage=$scope.discountObj.discountPercentage != '' ? parseFloat($scope.discountObj.discountPercentage).toFixed(2) : parseFloat($scope.discountObj.discountAmount/(totalAmount) * 100).toFixed(2);
				value.discount=parseFloat((value.discountPercentage/100) * (value.quantity * value.basePrice)).toFixed(2);
				value.grossAmount=parseFloat((value.quantity * value.basePrice) - value.concession - value.discount).toFixed(2);
				autoRenderdSummary=(parseFloat(autoRenderdSummary) + (parseFloat(value.basePrice) * parseFloat(value.quantity)) - parseFloat(value.concession) -  parseFloat(value.discount) ).toFixed(2);
				autoRenderdDiscount = (parseFloat(autoRenderdDiscount) +parseFloat(value.discount)).toFixed(2);
			});
			
			
			angular.forEach($scope.serviceMasterList, function (value, key) {
				value.discountPercentage=$scope.discountObj.discountPercentage != '' ? parseFloat($scope.discountObj.discountPercentage).toFixed(2) : parseFloat($scope.discountObj.discountAmount/(totalAmount) * 100).toFixed(2);
				value.discount=parseFloat((value.discountPercentage/100) * (value.quantity * value.basePrice)).toFixed(2);
				value.netPayable=(parseFloat(value.grossAmount)-parseFloat(value.discount)).toFixed(2);
				value.grossAmount=value.netPayable;
				servicesSummary=(parseFloat(servicesSummary) + (parseFloat(value.basePrice) * parseFloat(value.quantity)) - parseFloat(value.concession) -  parseFloat(value.discount) ).toFixed(2);
				serviceDiscount = (parseFloat(serviceDiscount) + parseFloat(value.discount)).toFixed(2);
			});
		
			if($scope.autoRenderedServicesList.length > 0 || $scope.serviceMasterList.length >0){
				$scope.netPayableSummary=Math.round((parseFloat(autoRenderdSummary) + parseFloat(servicesSummary)).toFixed(2));
				$scope.AutoRenderednetPayableSummary=(parseFloat(autoRenderdSummary)).toFixed(2);
				
				$scope.totalDiscountSummary=(parseFloat(autoRenderdDiscount) + parseFloat(serviceDiscount)).toFixed(2);
				$scope.AutoRenderednetTotalDiscountSummary=(parseFloat(autoRenderdDiscount)).toFixed(2);
				
				//--------- calculate round off amount ----------//
				/*if(parseFloat($scope.discountObj.discountAmount) < parseFloat($scope.totalDiscountSummary)){
					roundOffAmount=parseFloat($scope.totalDiscountSummary - $scope.discountObj.discountAmount).toFixed(2);
					$scope.totalDiscountSummary=parseFloat($scope.discountObj.discountAmount + roundOffAmount).toFixed(2);
				}else if(parseFloat($scope.discountObj.discountAmount) > parseFloat($scope.totalDiscountSummary)){
					roundOffAmount=parseFloat($scope.discountObj.discountAmount - $scope.totalDiscountSummary).toFixed(2);
					$scope.totalDiscountSummary=(parseFloat($scope.totalDiscountSummary) + parseFloat(roundOffAmount)).toFixed(2);
				}
				alert(roundOffAmount);*/
				
				
			}
			
			
			
			console.log($scope.discountObj);
			//$scope.discounDetailstList.push($scope.discountObj);
			//------ update Order Details List with newly applied discount -----//
			angular.forEach($scope.orderDetailsList, function(orderDtls, key) {
				//orderDtls.ordDiscount=parseFloat($scope.discountObj.discountPercentage).toFixed(2);
				orderDtls.ordDiscount=parseFloat(($scope.discountObj.discountPercentage/100) * (parseFloat(orderDtls.netAmount)  + parseFloat(orderDtls.ordConcession))).toFixed(2);
				orderDtls.netAmount=(parseFloat(orderDtls.netAmount) - parseFloat(orderDtls.ordDiscount)).toFixed(2);
			});
			
			/*angular.forEach($scope.serviceMasterList, function(service, key){
				service.discount=parseFloat($scope.discountObj.discountPercentage).toFixed(2);
			});*/
			
			angular.forEach($scope.autoRenderedServicesBillingDetails,function(autoService, key){
				autoService.discount=($scope.discountObj.discountPercentage/100) * (autoService.quantity * autoService.basePrice);
				autoService.grossAmount=(parseFloat(autoService.grossAmount) - parseFloat(autoService.discount)).toFixed(2);
				//parseFloat($scope.discountObj.discountPercentage).toFixed(2);
			});
			
			$scope.billingMasterObj.isDiscountInPercentage=$scope.discountObj.discountPercentage != "" ? 'A' : 'I';
			$scope.billingMasterObj.isDiscountApplicable=$scope.isDiscountApplicable == true ? 'A' : 'I';
		}catch (e) {
			console.log(e.message);
		}
	}
	
	$scope.calculateDiscountAmount=function(){
		$scope.discountObj.discountAmount=($scope.discountObj.discountPercentage/100) * $scope.totalAmountSummary;
	}
	
	$scope.calculateDiscountPercentage=function(){
		$scope.discountObj.discountPercentage=($scope.discountObj.discountAmount/100) * $scope.totalAmountSummary;
	}
	
	$scope.validateDiscountAmount=function(){
		if($scope.discountObj.discountPercentage != "")
			$scope.isDiscountAmountDisabled=true;
		else
			$scope.isDiscountAmountDisabled=false;
	}
	
	$scope.validateDiscountPercentage=function(){
		
		if($scope.discountObj.discountAmount != '')
			$scope.isDiscountPercentageDisabled=true;
		else
			$scope.isDiscountPercentageDisabled=false;
			
		//alert("validateDiscountPercentage");
		/*if($scope.discountObj.discountPercentage != "")
			$scope.isDiscountPercentageDisabled=true;
		else
			$scope.isDiscountPercentageDisabled=false;*/
		//$scope.isDiscountAmountDisabled=true;
	}
	
	 /******* START TAB FOR Payment Mode *******/
	/*First Level*/
	$scope.firstTabLevel = 1 ;
	
	$scope.setFirstTabLevel= function(newTab)
	{
		$scope.firstTabLevel = newTab;
	};
	
	$scope.isSetFirstTabLevel = function(tabNum)
	{
		return $scope.firstTabLevel === tabNum;
	};
	 /******* END TAB FOR Payment Mode *******/
}]);
	