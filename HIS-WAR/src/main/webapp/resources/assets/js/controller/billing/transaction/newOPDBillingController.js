/**
 * @author Vivek
 * 		Controller to handle OPD Billing
 */

angular.module("myApp").controller("newOPDBillingController", [ '$scope','$rootScope','$cookies','$state','BillingGenericService','GenericService','$stateParams','$timeout','growl','paymentCollFactory','GenericServiceParamHeader',function($scope,$rootScope,$cookies,$state,BillingGenericService,GenericService,$stateParams,$timeout,growl,paymentCollFactory,GenericServiceParamHeader)
	{
	
	var addService = 0;
	var init = 1;
	var quantity = 2;
	var unitPrice = 3;
	var discountPer = 4;
	var discount = 5;
	var discountCat = 5;
	
	//alert(parseFloat('6987').toFixed(2));
	
	$scope.init = function() {
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
			console.log('cookieObjectAppConfig',cookieObjectAppConfig);
			
		}
	$scope.defaultSelfTariffId = cookieObjectAppConfig.defaultSelfTariffId;
	$scope.isPreOrPostBilling = cookieObjectAppConfig.isPreOrPostBilling;
	
	$scope.unitId = cookieObject.unitId;
	$scope.organizationId = cookieObject.organizationId;
	$scope.userId = 1;
	$scope.visitTypeId=1;
	$rootScope.loginpage = true;
	$scope.currentDate = new Date();
	$scope.serviceMasterIdCheck = {};
	$scope.itemSelecteds = {};
	$scope.autoRenderedServicesFinalList = [];
	$scope.removeDiscountList = [];
	$scope.patientList  = [];
	$scope.removeServicesList = [];
	
	$scope.finalBill = "N";
	$scope.roundOffAmt = parseFloat(0).toFixed(2);
	 $scope.billRemark = '';
	 
	 	$scope.totalAmountSummary = "0.00";
		$scope.totalConcessionSummary = "0.00";
		$scope.netPayableSummary = "0.00";
		$scope.totalDiscountSummary = "0.00";
		$scope.totalGstTaxSummary = "0.00";
		$scope.totalCreditPayble = "0.00";
		$scope.totalSelfPayble = "0.00";
	
	var commonObj = {
			unitId : $scope.unitId,
			organizationId : $scope.organizationId
		};
	
	// for popup
	$scope.showModal = false;
	$scope.buttonClicked = "";
	$scope.popUpFlag = true;
	// end for popup
	
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
	
	$scope.patientDetails = {
			"patientId"	  : "",
			"patientName" : "",
			"genderCode"  : "",
			"age"         : "", 
			"uhIdNumber"  : "",
			"birthDate"	  : "",
			"encounterId" : ""
	}
		
		$scope.encounterDetails = {
				encounterId:""
		}
		
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
						"visitAdmId"	:$scope.patientDetails.encounterId,
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
		
		var URI = BASE_URL + ROOT_URL
		+ GETACTIVESPECIALITY;
BillingGenericService.serviceAction(METHOD_POST, URI,commonObj,
				NOTIFICATION_MSG_STATUS_FALSE)
		.then(function(response) {
					console.log(response);
					// $rootScope.startLoader();
					if (response.data.status == "success")
						{
						$scope.specialityMasterList = response.data.listObject;
							 
						}
						
				});

var URI = BASE_URL + ROOT_URL
+ GETACTIVEREASONLIST;

BillingGenericService
.serviceAction(METHOD_GET, URI,
		commonObj,
		NOTIFICATION_MSG_STATUS_FALSE)
.then(
		function(response) {
			console.log(response);
			// $rootScope.startLoader();
			if (response.data.status == "success")
				$scope.reasonList = response.data.listObject;
		});

var URI = BASE_URL + ROOT_URL
+ GETACTIVEDOCTORLIST;
GenericService
.serviceAction("POST", URI, commonObj)
.then(
		function(response) {
			$scope.doctorsList = [];

			if (response.data.status == "success") {
				$scope.doctorsList = response.data.listObject;

			}
		});


var URI = BASE_URL + ROOT_URL + "/api/billing/discountCategories/orgId/"+$scope.organizationId+"/unitId/"+$scope.unitId;
BillingGenericService.serviceAction(METHOD_GET,URI, "", NOTIFICATION_MSG_STATUS_FALSE).then(function(response){
	$scope.discountCategoryList = response.data.listObject;
});

	$scope.servicesSelectedList = {
	    services: []
	  };
	
	$scope.groupWiseServicesSelectedList = {
			services: []
	}
	
	
	$scope.billWiseDiscountObj = {
			"discountGivenBy":"",
			"discountCategoryId" : "",
			"discountAmountBillWise" : "",
			"discountPercentageBillWise" : ""
	}

	//yogesh
	$scope.searchPackageList = [];
	$scope.selectedPackageList = [];
	$scope.packageDetailsList = [];
	}
	
	$scope.init();
	
	 $scope.checkAll = function() {
		 $scope.servicesSelectedList.services = $scope.autoRenderedServicesFinalList.map(function(item) { return item.serviceMasterId; });
		  };
		  
			
$scope.groupWiseCheckAll= function(chkFlg) {
			$scope.disPerPopupForGroup = '';
			$scope.disAmtPopupForGroup = '';
			$scope.disCategoryIdForGroup = '';
			if(chkFlg == 'Y')
			$scope.groupWiseServicesSelectedList.services = $scope.groupWiseDiscountServicesList.map(function(item) { return item.serviceMasterId; });
	 };
	
	
	$scope.getSexMasterList = function(){
		$rootScope.startLoader();
		var URI = BASE_URL + ROOT_URL + "/adt/getActiveGenderList";
		BillingGenericService.serviceAction(METHOD_GET,URI, "", NOTIFICATION_MSG_STATUS_FALSE).then(function(response){
			$scope.sexMasterList = response.data.listObject;
		});
	}
	$scope.getSexMasterList();
	
	$scope.selectItem = function(item) {

		$scope.itemSelecteds = [];
		// If checkbox is checked
		if ($scope.serviceMasterIdCheck) {
			$scope.itemSelecteds = item;
		}
		console.log(item);
	}
	
	/*$scope.getById = function(encounterId){
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
	}*/
	
	$rootScope.searchPatient = function(patientsearchObj){
		$rootScope.startLoader();
		var URI = BASE_URL + ROOT_URL + "/adt/patientSearchByMultipleCriteria";
		BillingGenericService.serviceAction(METHOD_POST,URI, patientsearchObj, NOTIFICATION_MSG_STATUS_FALSE).then(function(response){
			$scope.patientList = response.data.listObject;
			
			$scope.patientEncounterList = [];
			
			$scope.encounterDetails = {
					encounterId : "",
					encounterDateString : "",
					referralDesc : "",
					specialityName : "",
					doctorName : "",
					patientCategory : "",
					paymentEntitlementDesc : "",
					companyDesc : "",
					paymentEntitlementTypeId : "",
					payeeId : "",
					patientId : "",
					doctorId : "",
			}
			$scope.autoRenderedServicesFinalList = [];
			
			$scope.patientDetails ={};
			
			$scope.totalAmountSummary = "0.00";
			$scope.totalConcessionSummary = "0.00";
			$scope.netPayableSummary = "0.00";
			$scope.totalDiscountSummary = "0.00";
			$scope.totalGstTaxSummary = "0.00";
			
			$scope.totalCreditPayble = "0.00";
			$scope.totalSelfPayble = "0.00";
			
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
		var data = {};
		var URI = BASE_URL + ROOT_URL + "/api/opd/encounters/orgId/"+$scope.organizationId+"/unitId/"+$scope.unitId+"/patientId/"+$scope.patientList[index].patientId;
		BillingGenericService.serviceAction(METHOD_GET,URI, data, NOTIFICATION_MSG_STATUS_FALSE).then(function(response){
			if(response.data.status == "success"){
				console.log("Encouner List",response.data.listObject);
				$scope.patientEncounterList = response.data.listObject;
			}
		});
	
			$(".patienttSearchResult").toggle();
		
			$rootScope.stopLoader();
	}
	
	$scope.getEncounterDetails = function(encounterId)
	{
		
		 /*var encounterNumber = $scope.encounterNumber;
         var encounterId = $.grep($scope.patientEncounterList, function (fruit) {
             return fruit.encounterNumber == encounterNumber;
         })[0].encounterId;
         $scope.encounterDetails.encounterId = encounterId;*/
		//console.log(encounterId);
		var data = {};
		var URI = BASE_URL + ROOT_URL + "/api/opd/encounter/"+encounterId;
		BillingGenericService.serviceAction(METHOD_GET,URI, data, NOTIFICATION_MSG_STATUS_FALSE).then(function(response){
			if(response.data.status == "success"){
				$scope.encounterDetails = {
						encounterId : response.data.object.encounterId.toString(),
						encounterDateString : response.data.object.encounterDateString,
						referralDesc : response.data.object.referralDesc,
						specialityName : response.data.object.specialityName,
						doctorName : response.data.object.doctorName,
						patientCategory : response.data.object.patientCategory,
						paymentEntitlementDesc : response.data.object.paymentEntitlementDesc,
						companyDesc : response.data.object.companyDesc,
						paymentEntitlementTypeId : response.data.object.paymentEntitlementTypeId,
						payeeId : response.data.object.payeeId,
						patientId : response.data.object.patientId,
						doctorId : response.data.object.doctorId
				}
				
				
				$scope.getAutoRenderedServicesByPatient(response.data.object);
		
			}
		});
		
		$scope.getActivePackagesList();//yogesh
	}
	
	
	$scope.getAutoRenderedServicesByPatient=function(encounterObj){
		$scope.autoRenderedServicesBillingDetails=[];
		//var URI = BASE_URL + ROOT_URL + "/api/billing/serviceMaster/autoRendered";
		var URI = BASE_URL + ROOT_URL + "/api/billing/serviceMaster/renderedServices";
		var obj={
				//"unitId":$scope.unitId,
				//"organizationId":$scope.organizationId,
				//"patientId":encounterObj.patientId,
				//"visitTypeId":1,
				//"bedCategoryId":1, //By default its OPD ---> Id==1
				//"patientTypeId":1, //need to add after patient registration 
				//"paymentEntitlementTypeId":encounterObj.paymentEntitlementTypeId,
				//"doctorId":encounterObj.doctorId,
				//"defaultSelfTariffId":$scope.defaultSelfTariffId
				"encounterId":encounterObj.encounterId
				
		}
		console.log("AUTORENDER",obj);
		//return false;
		BillingGenericService.serviceAction(METHOD_POST,URI, obj, NOTIFICATION_MSG_STATUS_FALSE).then(function(response){
			if(response.data.status == "success"){
				$scope.autoRenderedServicesList=response.data.listObject;
				console.log("$scope.autoRenderedServicesList",$scope.autoRenderedServicesList);
				//return false;
				  $scope.calculationTest($scope.autoRenderedServicesList,init);
				/*$scope.AutoRenderedTotalAmountSummary = 0;
				$scope.AutoRenderedTotalConcessionSummary = 0;
				$scope.AutoRenderednetPayableSummary = 0;
				$scope.AutoRenderednetTotalDiscountSummary=0;
				$scope.AutoRenderednetTotalTax=0;
				$scope.AutoRenderednetTotalDiscount=0;
				//-----calculate amounts
				console.log($scope.autoRenderedServicesList);
				//return false;
				
				$scope.autoRenderedServicesFinalList = [];
				angular.forEach($scope.autoRenderedServicesList, function (value, key) {
							console.log(value.basePrice, "#", value.quantity);
							var newArr = {};
							newArr.serviceMasterId = value.serviceMasterId;
							newArr.serviceStandardCode = value.serviceStandardCode;
							newArr.serviceStandardName = value.serviceStandardName;
							newArr.isDiscountApplicable = value.isDiscountApplicable;
							newArr.isQuantityEditable = value.isQuantityEditable;
							newArr.isRateEditable = value.isRateEditable;
							newArr.minRateEditable = value.minRateEditable;
							newArr.maxRateEditable = value.maxRateEditable;
							newArr.specialityName = value.specialityName;
							newArr.doctorName = value.doctorName;
							if(value.specialityName == null)
							{
								newArr.specialityName = encounterObj.specialityName;
								newArr.doctorName = encounterObj.doctorName;
							}
							newArr.quantity = value.quantity;
							newArr.basePrice = value.basePrice;
							newArr.basePriceOld = value.basePrice;
							newArr.totalAmt = (parseFloat(value.quantity) * parseFloat(value.basePrice)).toFixed(2);
							newArr.concession = parseFloat(value.concession).toFixed(2);
							newArr.discountPercentage = value.discountPercentage;
							newArr.discount = value.discount;
							var dis = value.discount;
							var disPer =value.discountPercentage;
							if(value.discount == null)
								{
									newArr.discountPercentage = 0;
									newArr.discount = 0;
									dis = 0;
									disPer = 0;
								}
							newArr.taxName = value.taxName;
							newArr.taxPercentage = value.taxPercentage;
							
							var totalAmt = (parseFloat(value.quantity) * parseFloat(value.basePrice)).toFixed(2);
							var concession = parseFloat(value.concession).toFixed(2);
							var withConDic =((totalAmt - (concession + dis)));
							newArr.taxAmt = parseFloat((withConDic * value.taxPercentage)/100).toFixed(2);
							var gstAmt = (withConDic * value.taxPercentage)/100;
							newArr.netPay =   parseFloat((totalAmt -(parseFloat(value.concession).toFixed(2) + dis) + gstAmt)).toFixed(2);
							var newPatAmt = (totalAmt -(parseFloat(value.concession).toFixed(2) + dis) + gstAmt);
							
							$scope.AutoRenderedTotalAmountSummary=(parseFloat($scope.AutoRenderedTotalAmountSummary) + parseFloat(1) * parseFloat(value.basePrice)).toFixed(2);
							$scope.AutoRenderedTotalConcessionSummary=(parseFloat($scope.AutoRenderedTotalConcessionSummary) + parseFloat(value.concession)).toFixed(2);
							$scope.AutoRenderednetPayableSummary=(parseFloat($scope.AutoRenderednetPayableSummary) + parseFloat(newPatAmt)).toFixed(2);
							$scope.AutoRenderednetTotalDiscountSummary=(parseFloat($scope.AutoRenderednetTotalDiscountSummary) + parseFloat(0)).toFixed(2);
							$scope.autoRenderedServicesBillingDetails.push($scope.createBillingDetailsObj(value));
							$scope.AutoRenderednetTotalTax = (parseFloat($scope.AutoRenderednetTotalTax) + gstAmt).toFixed(2);
							$scope.AutoRenderednetTotalDiscount = (parseFloat($scope.AutoRenderednetTotalDiscount) + dis).toFixed(2);
							$scope.autoRenderedServicesFinalList.push(newArr);
					
				});// end of loop
				$scope.totalAmountSummary=(parseFloat($scope.AutoRenderedTotalAmountSummary)).toFixed(2);
				$scope.totalConcessionSummary=(parseFloat($scope.AutoRenderedTotalConcessionSummary)).toFixed(2);
				$scope.netPayableSummary=(parseFloat($scope.AutoRenderednetPayableSummary)).toFixed(2);
				$scope.totalDiscountSummary=(parseFloat($scope.AutoRenderednetTotalDiscountSummary)).toFixed(2);
				$scope.totalGstTaxSummary=(parseFloat($scope.AutoRenderednetTotalTax)).toFixed(2);*/
			}
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
   
    $scope.calculationTest = function(autoRenderedServicesList,typeId)
    {
    	console.log("AUTO",autoRenderedServicesList);
    	$scope.AutoRenderedTotalAmountSummary = 0;
		$scope.AutoRenderedTotalConcessionSummary = 0;
		$scope.AutoRenderednetPayableSummary = 0;
		$scope.AutoRenderednetTotalDiscountSummary=0;
		$scope.AutoRenderednetTotalTax=0;
		$scope.AutoRenderednetTotalDiscount=0;
		$scope.AutoRenderednetTotalCreditPayble=0;
		$scope.AutoRenderednetTotalSelfPayble=0;
		//-----calculate amounts
		//console.log(autoRenderedServicesList);
		//return false;
		
		$scope.autoRenderedServicesFinalList = [];
		
		angular.forEach(autoRenderedServicesList, function (value, key) {
					console.log(value.basePrice, "#", value.quantity);
					var newArr = {};
					if(value.orderDetailsId > 0)
						{
						newArr.orderDetailsId = value.orderDetailsId;
						}
					else
						{
						newArr.orderDetailsId = null;
						}
					if(value.orderId > 0)
					{
					newArr.orderId = value.orderId;
					}
				else
					{
					newArr.orderId = null;
					}
					
					if(value.orderDate != null)
						{
							newArr.orderDate  = value.orderDate;
						}
					
					if(value.orderNo != null)
					{
						newArr.orderNo  = value.orderNo;
					}
					else
						{
							newArr.orderNo  = '';
						}
					
					newArr.priorityId  = value.priorityId;
					newArr.isOutsourced = value.isOutsourced;
					newArr.specialityId = value.specialityId;
					newArr.taxId = value.taxId;
					newArr.doctorId = value.doctorId;
					newArr.serviceSpecialityId = value.serviceSpecialityId;
					newArr.serviceSubSpecialityId = value.serviceSubSpecialityId;
					newArr.serviceRendered = value.serviceRendered;
					newArr.serviceRenderingDeptId = value.serviceRenderingDeptId;
					newArr.oldNetPay = value.oldNetPay;
					newArr.discountDetailsList = value.discountDetailsList; 
					newArr.serviceMasterId = value.serviceMasterId;
					newArr.serviceStandardCode = value.serviceStandardCode;
					newArr.serviceStandardName = value.serviceStandardName;
					//newArr.isDiscountApplicable = value.isDiscountApplicable;
					newArr.isDiscountApplicable = value.isDiscountApplicable; //isDiscountApplicable
					newArr.isQuantityEditable = value.isQuantityEditable;
					newArr.isRateEditable = value.isRateEditable;
					newArr.minRateEditable = value.minRateEditable;
					newArr.maxRateEditable = value.maxRateEditable;
					newArr.specialityName = value.specialityName;
					newArr.doctorName = value.doctorName;
					newArr.discountDisabledFlag = 'N';
					newArr.isPackage = value.isPackage;
					newArr.packageMasterId = value.packageMasterId;
					newArr.packageTypeId = value.packageTypeId;
					if(value.specialityName == null)
					{
						newArr.specialityName = "";
						newArr.doctorName = "";
					}
					newArr.quantity = value.quantity;
					newArr.basePrice = parseFloat(value.basePrice).toFixed(2);
					newArr.basePriceOld = value.basePrice;
					newArr.totalAmt = (parseFloat(value.quantity) * parseFloat(value.basePrice)).toFixed(2);
					newArr.concession = parseFloat(value.concession).toFixed(2);
					newArr.discountCategoryId = '';
					newArr.discountPercentage = value.discountPercentage;
					newArr.discount = value.discount;
					var dis = parseFloat(value.discount).toFixed(2);
					var disPer =value.discountPercentage;
					//alert($scope.discountDetailsList.length);
					if(value.discount == null || value.discount == 0 )
						{
							newArr.discountPercentage = 0;
							newArr.discount = 0;
							dis = 0;
							disPer = 0;
							newArr.discountDisabledFlag = 'Y';
							
						}
					
						if(value.discountDetailsList == null)
						{
							value.discountDetailsList = [];
							newArr.discountDetailsList = [];
						}
					
					
					//alert(Object.keys(value.discountDetailsList).length);
					//return false;Object.keys(card).length === 0
					//if(value.discountDetailsList.length > 1)
					if(Object.keys(value.discountDetailsList).length > 1)
						{
								newArr.discountCategoryId = '0';
								var tempDiscPer = 0;
								var tempDiscAmt = 0;
								angular.forEach(value.discountDetailsList, function (value, key) {
										
									tempDiscPer += parseFloat(value.discountPercentage);
									tempDiscAmt += parseFloat(value.discountAmount);
									
									
									
								});
								dis = tempDiscAmt;
								disPer = tempDiscPer;
								newArr.discountPercentage =disPer.toFixed(2);
								newArr.discount = dis.toFixed(2);
								newArr.discountDisabledFlag = 'N';
								
						}
					else
						{
							if(Object.keys(value.discountDetailsList).length == 0)
								{
									//newArr.discountPercentage = disPer;parseFloat(value.quantity) * parseFloat(value.basePrice))
									newArr.discount = dis;
									newArr.discountPercentage = isNaN(((parseFloat(dis)/(parseFloat(value.quantity) * parseFloat(value.basePrice) - (parseFloat(value.concession))))*100).toFixed(2)) ? parseFloat(0).toFixed(2) : ((parseFloat(dis)/(parseFloat(value.quantity) * parseFloat(value.basePrice) - (parseFloat(value.concession))))*100).toFixed(2);
									//dis = dis;
									//disPer = disPer;
									if(value.discountCategoryId != null)
										{
											newArr.discountCategoryId = value.discountCategoryId.toString();
										}
									
									newArr.discountDisabledFlag = 'Y';
								}
							else /*if(typeId == '1')*/  //just test changes
								{
									//alert("gg");
									newArr.discountPercentage = value.discountDetailsList[0].discountPercentage;
									newArr.discount = value.discountDetailsList[0].discountAmount;
									dis = value.discountDetailsList[0].discountAmount;
									disPer = value.discountDetailsList[0].discountPercentage;
									newArr.discountCategoryId = value.discountDetailsList[0].discountCategoryId.toString();
									newArr.discountDisabledFlag = 'Y';
								}
							//newArr.discountCategoryId = $scope.discountDetailsList[0].discountCategoryId;
						}
					newArr.taxName = value.taxName;
					newArr.taxPercentage = value.taxPercentage;
					
					var totalAmt = (parseFloat(value.quantity) * parseFloat(value.basePrice)).toFixed(2);
					var concession = parseFloat(value.concession).toFixed(2);
					
					

					if(typeId == '4' || typeId == '2' || typeId == '3' ) // for discount percentage
						{
							var calDicAmt = ((parseFloat(totalAmt) - parseFloat(concession))*value.discountPercentage/100).toFixed(2);	
							dis = calDicAmt;
							newArr.discount  = calDicAmt;
							newArr.discountPercentage  = parseFloat(value.discountPercentage).toFixed(2);//new added
							if(value.discountDetailsList.length == 0)
							{
								newArr.discountDisabledFlag = 'Y';
							}
							else
								{
								newArr.discountDisabledFlag = 'N';
								}
							
						}
					if(typeId == '5')// for discount amount
						{
							disPer = ((parseFloat(dis)/(totalAmt - (parseFloat(concession))))*100).toFixed(2);
							newArr.discountPercentage =disPer;
							//newArr.discountDisabledFlag = 'Y';
							if(value.discountDetailsList.length == 0)
							{
								newArr.discountDisabledFlag = 'Y';
							}
							else
								{
								newArr.discountDisabledFlag = 'N';
								}
						}
					
					
					var withConDic =parseFloat(totalAmt - (parseFloat(concession) + parseFloat(dis))).toFixed(2);
					
			
					
					newArr.taxAmt = parseFloat((parseFloat(withConDic) * value.taxPercentage)/100).toFixed(2);
					
					var gstAmt =  parseFloat((parseFloat(withConDic) * value.taxPercentage)/100).toFixed(2);
					newArr.netPay =   parseFloat((totalAmt -(parseFloat(value.concession) + parseFloat(dis)) + parseFloat(gstAmt))).toFixed(2);
					newArr.selfPayable =   parseFloat((totalAmt -(parseFloat(value.concession) + parseFloat(dis)) + parseFloat(gstAmt))).toFixed(2);
					newArr.creditPayable =   parseFloat(0).toFixed(2);
					var selfPayableV =   parseFloat((totalAmt -(parseFloat(value.concession) + parseFloat(dis)) + parseFloat(gstAmt))).toFixed(2);
					var creditPayableV =   "0";
					var newPatAmt = parseFloat(((parseFloat(totalAmt) -(parseFloat(value.concession) + parseFloat(dis))) + parseFloat(gstAmt))).toFixed(2);
					$scope.AutoRenderedTotalAmountSummary=(parseFloat($scope.AutoRenderedTotalAmountSummary) + parseFloat(value.quantity) * parseFloat(value.basePrice)).toFixed(2);
					$scope.AutoRenderedTotalConcessionSummary=(parseFloat($scope.AutoRenderedTotalConcessionSummary) + parseFloat(value.concession)).toFixed(2);
					$scope.AutoRenderednetPayableSummary=(parseFloat($scope.AutoRenderednetPayableSummary) + parseFloat(newPatAmt)).toFixed(2);
					$scope.AutoRenderednetTotalDiscountSummary=(parseFloat($scope.AutoRenderednetTotalDiscountSummary) + parseFloat(0)).toFixed(2);
					$scope.autoRenderedServicesBillingDetails.push($scope.createBillingDetailsObj(value));
					$scope.AutoRenderednetTotalTax = (parseFloat($scope.AutoRenderednetTotalTax) + parseFloat(gstAmt)).toFixed(2);
					$scope.AutoRenderednetTotalDiscount = (parseFloat($scope.AutoRenderednetTotalDiscount) + parseFloat(dis)).toFixed(2);
					$scope.AutoRenderednetTotalCreditPayble=(parseFloat($scope.AutoRenderednetTotalCreditPayble) + parseFloat(creditPayableV)).toFixed(2);
					$scope.AutoRenderednetTotalSelfPayble=(parseFloat($scope.AutoRenderednetTotalSelfPayble) + parseFloat(selfPayableV)).toFixed(2);
					newArr.createdBy = value.userId;
					newArr.createdDate = value.createdDate;
					$scope.autoRenderedServicesFinalList.push(newArr);
			
		});// end of loop
		$scope.totalAmountSummary=(parseFloat($scope.AutoRenderedTotalAmountSummary)).toFixed(2);
		$scope.totalConcessionSummary=(parseFloat($scope.AutoRenderedTotalConcessionSummary)).toFixed(2);
		$scope.netPayableSummary=(parseFloat($scope.AutoRenderednetPayableSummary)).toFixed(2);
		$scope.totalDiscountSummary=(parseFloat($scope.AutoRenderednetTotalDiscount)).toFixed(2);
		$scope.totalGstTaxSummary=(parseFloat($scope.AutoRenderednetTotalTax)).toFixed(2);
		
		$scope.totalCreditPayble=(parseFloat($scope.AutoRenderednetTotalCreditPayble)).toFixed(2);
		$scope.totalSelfPayble=(parseFloat($scope.AutoRenderednetTotalSelfPayble)).toFixed(2);
		
		return 1;
    }
    $scope.testAdd = function()
    {
    	
    	$scope.discountDetailsList = [/*{
			"discountPercentage":"10",
			"discountAmount":"78",
			"discountCategoryDesc":"Doctor Discount",
			"discountCategoryId":"1"
		},
		{
			"discountPercentage":"5",
			"discountAmount":"39",
			"discountCategoryDesc":"Hospital Discount",
			"discountCategoryId":"2"
		}*/];
    	
    	var newArr = {};
		newArr.serviceMasterId = '123';
		newArr.serviceStandardCode = 'Test';
		newArr.serviceStandardName = 'Test';
		newArr.isDiscountApplicable = 'Y';
		newArr.isQuantityEditable = 'Y';
		newArr.isRateEditable = 'Y';
		newArr.minRateEditable = '10';
		newArr.maxRateEditable = '25';
		newArr.specialityName = 'Speciality';
		newArr.doctorName = 'Dr. Prasun Rao';
		newArr.quantity = '1';
		newArr.basePrice = '500';
		newArr.basePriceOld = '500';
		newArr.discountDisabledFlag = 'Y';
		newArr.totalAmt = parseFloat(1) * parseFloat(500).toFixed(2);
		newArr.concession = parseFloat(0).toFixed(2);
		newArr.discountPercentage = 0;
		newArr.discount =0;
		var dis = 0;
		var disPer =0;
		
		newArr.taxName = 'CGST';
		newArr.taxPercentage = 6;
		newArr.discountDetailsList = [];
		newArr.selfPayable =  "0";
		newArr.creditPayable =   "0";
		
		var totalAmt = parseFloat(1) * parseFloat(500).toFixed(2);
		var concession = parseFloat(0).toFixed(2);
		var withConDic =((totalAmt - (concession + dis)));
		
		newArr.taxAmt = parseFloat((withConDic * 6)/100).toFixed(2);
		var gstAmt = (withConDic * 6)/100;
		newArr.netPay =   parseFloat((totalAmt -(parseFloat(0).toFixed(2) + dis) + gstAmt)).toFixed(2);
		$scope.autoRenderedServicesFinalList.push(newArr);
		$scope.calculationTest($scope.autoRenderedServicesFinalList,addService);
    }
    
    $scope.getQuantityCal = function(itemObj,idx)
    {
    	//alert(idx);
    	 var autoRenderedServicesList = $scope.autoRenderedServicesFinalList;
    	 $scope.calculationTest(autoRenderedServicesList,quantity);
    }
    
    $scope.getBasePriceCal = function(itemObj,idx)
    {
    	//alert(idx);
    	console.log("OBJ",itemObj);
    	var basepriceNew = parseFloat(itemObj.basePrice);
    	var basePriceOld = parseFloat(itemObj.basePriceOld).toFixed(2);
    	var minRate = itemObj.minRateEditable;
    	var maxRate = itemObj.maxRateEditable;
    	
    	/*var diffRate = basePriceOld - basepriceNew;
    	alert(diffRate);*/
    	var minRatePerAmount = ((basePriceOld*minRate)/100).toFixed(2);
    	var maxRatePerAmount = ((basePriceOld*maxRate)/100).toFixed(2);
    	
    	var minPerDiff = parseFloat(basePriceOld) - parseFloat(minRatePerAmount);
    	var maxPerAdd = parseFloat(basePriceOld) + parseFloat(maxRatePerAmount);
    	
    	//alert(minRatePerAmount);
    	//alert(maxRatePerAmount);
    	
    	//alert("minPerDiff"+minPerDiff);
    	//alert("maxPerAdd"+maxPerAdd);
    	
    	if(minPerDiff >basepriceNew)
    	{
    		growl.warning("Unit price should be Minimum RS."+minPerDiff,{
				title : ERROR_MSG
			});
    		$scope.autoRenderedServicesFinalList[idx].basePrice = basePriceOld;
    		return false;
    	}
    	
    	if(basepriceNew > maxPerAdd)
    	{
    		growl.warning("Unit price should be Maximun RS."+maxPerAdd,{
				title : ERROR_MSG
			});
    		$scope.autoRenderedServicesFinalList[idx].basePrice = basePriceOld;
    		return false;	
    	}
    	
    	
    	 var autoRenderedServicesList = $scope.autoRenderedServicesFinalList;
    	 $scope.calculationTest(autoRenderedServicesList,unitPrice);
    }
    
    $scope.getDiscountPerCal = function(itemObj,idx)
    {
    	 var autoRenderedServicesList = $scope.autoRenderedServicesFinalList;
    	 $scope.calculationTest(autoRenderedServicesList,discountPer);
    }
    
    $scope.getDiscountCal = function(itemObj,idx)
    {
    	 var autoRenderedServicesList = $scope.autoRenderedServicesFinalList;
    	 $scope.calculationTest(autoRenderedServicesList,discount);
    }
    
    $scope.getDiscountListForInfo  = function(itemObj,idx)
    {
    	console.log(itemObj);
    }
    $scope.getDiscountList  = function(itemObj,idx)
    {
    	$scope.disPerPopup = '';
		$scope.disAmtPopup = '';
		$scope.disCategoryId = '';
		
    	$scope.serciveNamePop = itemObj.serviceStandardName;
    	$scope.totalAmtPopup =(parseFloat(itemObj.quantity) * parseFloat(itemObj.basePrice)).toFixed(2);
    	var totalAmtPopup = (parseFloat(itemObj.quantity) * parseFloat(itemObj.basePrice)).toFixed(2);
    	$scope.totalDiscountAmtPopup = ((parseFloat(totalAmtPopup) - parseFloat(itemObj.concession))*itemObj.discountPercentage/100).toFixed(2);
    	
    	$scope.discountDetailsArrayListPopup = itemObj.discountDetailsList;
    	
    	$scope.objectIndex = idx;
    	//alert($scope.objectIndex);
    	//alert();
    }
    $scope.getDiscountListPopup  = function(itemObj,idx)
    {
    	console.log("dsdsd",itemObj);
    	
    	$scope.serciveNamePop = itemObj[idx].serviceStandardName;
    	$scope.totalAmtPopup =(parseFloat(itemObj[idx].quantity) * parseFloat(itemObj[idx].basePrice)).toFixed(2);
    	var totalAmtPopup = (parseFloat(itemObj[idx].quantity) * parseFloat(itemObj[idx].basePrice)).toFixed(2);
    	$scope.totalDiscountAmtPopup = ((parseFloat(totalAmtPopup) - parseFloat(itemObj[idx].concession))*itemObj[idx].discountPercentage/100).toFixed(2);
    	
    	$scope.discountDetailsArrayListPopup = itemObj[idx].discountDetailsList;
    	
    	
    	
    	$scope.objectIndex = idx;
    	//alert("idc"+$scope.objectIndex);
    	
    }
    
    $scope.getDisPerPopup = function(disPer)
    {
    	var idx  = $scope.objectIndex;
    	//alert(idx);
    	//alert($scope.autoRenderedServicesFinalList[idx].totalAmt);
    	var totalAmtPopup = $scope.autoRenderedServicesFinalList[idx].totalAmt;
    	var concessionPopup = $scope.autoRenderedServicesFinalList[idx].concession;
    	var calDicAmt = ((parseFloat(totalAmtPopup) - parseFloat(concessionPopup))*disPer/100).toFixed(2);	
		$scope.disAmtPopup = calDicAmt;
		
    }
    
    $scope.getDisAmtPopup = function(disAmt)
    {
    	var idx  = $scope.objectIndex;
    	//alert(disAmt);
    	//alert($scope.autoRenderedServicesFinalList[idx].totalAmt);
    	var totalAmtPopup = $scope.autoRenderedServicesFinalList[idx].totalAmt;
    	var concessionPopup = $scope.autoRenderedServicesFinalList[idx].concession;
		//alert(totalAmtPopup);
		//alert(concessionPopup);
		var disPer = ((parseFloat(disAmt)/(totalAmtPopup - (parseFloat(concessionPopup))))*100).toFixed(2);
		//alert(disPer);
		$scope.disPerPopup = disPer;
		
	/*	var dataOne = {
			"discountPercentage":disPer,
			"discountAmount":(parseFloat(disAmt)).toFixed(2),
			"discountCategoryDesc":"Doctor Discount",
			"discountCategoryId":"1"
			};
		
		$scope.autoRenderedServicesFinalList[idx].discountDetailsList.push(dataOne);
		$scope.calculationTest($scope.autoRenderedServicesFinalList,init);*/
		
    }
    
    $scope.addDiscountInDiscountList = function()
    {
    	var idx  = $scope.objectIndex;
    	console.log(idx);
    	console.log("hhh",$scope.autoRenderedServicesFinalList[idx]);
    	//alert($scope.disPerPopup);
    	
    	var disCatName = $.grep($scope.discountCategoryList, function (disCat) {
            return disCat.id == $scope.disCategoryId;
        })[0].type;
    	
    	var popupData = {
    			"discountPercentage":$scope.disPerPopup,
    			"discountAmount":(parseFloat($scope.disAmtPopup)).toFixed(2),
    			"discountCategory":disCatName,
    			"discountCategoryId":$scope.disCategoryId,
    			"createdBy" : $scope.userId,
				"createdDate" :new Date().getTime()
    			
    	}
    	
    	$scope.autoRenderedServicesFinalList[idx].discountDetailsList.push(popupData);
    	var autoRenderedServicesList = $scope.autoRenderedServicesFinalList;
    	//$scope.getDiscountList();
    	console.log("ff",$scope.autoRenderedServicesFinalList);
    	$scope.calculationTest(autoRenderedServicesList,init);
    	
    	$scope.getDiscountListPopup($scope.autoRenderedServicesFinalList,idx);
    	
    	$scope.disPerPopup = '';
		$scope.disAmtPopup = '';
		$scope.disCategoryId = '';
    }
    
    
    $scope.getSubSpecialityListById = function(spId)
    {
    	 var specialityIDS = [];
    	 specialityIDS.push(spId);
			
         var data = {
       		  organizationId:$scope.organizationId,
       		  unitId:$scope.unitId,
       		  specialityIdList:specialityIDS
         }
         console.log(data);
         var URI = BASE_URL + ROOT_URL
			+ GETSUBSPECIALITYBYSPECIALITYARRAY;
	BillingGenericService
			.serviceAction(METHOD_POST, URI,
					data,
					NOTIFICATION_MSG_STATUS_FALSE)
			.then(
					function(response) {
						console.log(response);
						// $rootScope.startLoader();
						if (response.data.status == "success")
							{
							$scope.subSpecialityMaterList = response.data.listObject;
							}
							
					});
    }
    $scope.getservicesPop = function()
    {
    	$scope.servicesListPopup = [];
    }
    $scope.addServiceToTempServiceList = function($item, $model, $label){
		console.log($item);
		
		$scope.search = '';
		$('#searchServiceId').val('');
		//var billingDetailsObj = $scope.createBillingDetailsObj($item)
		//var isExist = checkExistsOrNot($scope.tempServiceDetailsList,billingDetailsObj);
		
				var obj={
							"unitId":$scope.unitId,
							"organizationId":$scope.organizationId,
							"visitTypeId":1,// by default its op
							"billingBedCategoryId":1, //By default its OPD ---> Id==1
							"patientTypeId":1, //need to add after patient registration 
							"paymentEntitlementId":$scope.encounterDetails.paymentEntitlementTypeId,
							"tariffId":$scope.defaultSelfTariffId,
							"orderDate":moment(new Date()).format('YYYY-MM-DD HH:mm:ss'),
							"serviceList":[$item.serviceMasterId]
				
						}
				console.log('Base prise',obj)
				//return false;
				$rootScope.startLoader();
				var URI = BASE_URL + ROOT_URL + "/api/unit/getBasePriceOfServicesFromTariffMasterByServiceList";
				BillingGenericService.serviceAction(METHOD_POST,URI, obj, NOTIFICATION_MSG_STATUS_FALSE).then(function(response){
					if(response.data.status=="success"){
						var basePriceObj=response.data.object;
						console.log('rate',basePriceObj);
						$item.basePrice=basePriceObj.rate;
						$item.quantity=1;
						//concession
						console.log("FINAL ITEM",$item);
						
						$scope.servicePopupCalculate($item);
						
						/*billingDetailsObj.netPayable=(parseFloat(basePriceObj.rate) * parseFloat(billingDetailsObj.quantity) - parseFloat(billingDetailsObj.concession)).toFixed(2);
						billingDetailsObj.grossAmount=billingDetailsObj.netPayable;
						console.log(billingDetailsObj);
						$scope.tempServiceDetailsList.push(billingDetailsObj);*/
						
					}
				});
			
		
		console.log($scope.tempServiceDetailsList);
		$scope.search = "";
	}
    
    $scope.servicesListPopup = [];
    $scope.servicePopupCalculate = function(item)
    {
    	//$scope.servicesListPopup.push(item);
    	
    	var newArr = {};
    	newArr.specialityId = item.specialityId;
    	newArr.serviceSpecialityId = item.specialityId;
		newArr.serviceSubSpecialityId = item.subSpecialityId;
    	newArr.taxId = item.taxId;
		newArr.serviceMasterId =item.serviceMasterId;
		newArr.serviceStandardCode = item.serviceStandardCode;
		newArr.serviceStandardName = item.serviceStandardName;
		newArr.isDiscountApplicable = item.isDiscountApplicable;
		newArr.isQuantityEditable = 'Y';
		newArr.isRateEditable = item.isRateEditable;
		newArr.minRateEditable = item.minRateEditable;
		newArr.maxRateEditable = item.maxRateEditable;
		newArr.specialityName = item.specialityName;
		newArr.subSpecialityName = item.subSpecialityName;
		newArr.quantity = item.quantity;
		newArr.basePrice = item.basePrice;
		newArr.basePriceOld = item.basePrice;
		newArr.totalAmt = parseFloat(item.quantity) * parseFloat(item.basePrice).toFixed(2);
		newArr.concession = parseFloat(item.concession).toFixed(2);
		newArr.discountPercentage = 0;
		newArr.discount =0;
		newArr.doctorId = 0;
		newArr.discountDetailsList = [];
		newArr.taxName = item.taxName;
		newArr.taxId = item.taxId;
		newArr.taxPercentage = item.taxPercentage;
		newArr.selfPayable =  "0";
		newArr.creditPayable =   "0";
		newArr.serviceRendered = "0";
		newArr.serviceRenderingDeptId = item.subSpecialityId;
		newArr.createdBy  = $scope.userId;
		newArr.createdDate = new Date().getTime();
		
		$scope.servicesListPopup.push(newArr);
		
	    $scope.servicePopupCalculateFinal($scope.servicesListPopup); 
		
		
    }
    
    $scope.servicePopupCalculateFinal = function(itemObj)
    {
    	$scope.AutoRenderedTotalAmountSummaryPopUp = 0;
    	angular.forEach($scope.servicesListPopup, function (value, key) {
			
			$scope.AutoRenderedTotalAmountSummaryPopUp=(parseFloat($scope.AutoRenderedTotalAmountSummaryPopUp) + parseFloat(value.quantity) * parseFloat(value.basePrice)).toFixed(2);
			
		});
		$scope.totalAmountSummaryPopup = $scope.AutoRenderedTotalAmountSummaryPopUp;
    }
    
    $scope.removeTempServicesByIndex = function(index){
		var serviceIndex = $scope.servicesListPopup.indexOf(index);
		$scope.servicesListPopup.splice(serviceIndex, 1);
	}
    
    
    $scope.getQuantityCalPopup = function(itemObj,idx)
    {
    	//alert(idx);
    	$scope.servicesListPopup[idx].totalAmt = parseFloat(itemObj.quantity) * parseFloat(itemObj.basePrice).toFixed(2);
    	 var autoRenderedServicesList = $scope.servicesListPopup;
    	 $scope.servicePopupCalculateFinal(autoRenderedServicesList);
    }
    
    $scope.getBasePriceCalPopup = function(itemObj,idx)
    {
    	//alert(idx);
    	
    	var basepriceNew = parseFloat(itemObj.basePrice);
    	var basePriceOld = parseFloat(itemObj.basePriceOld).toFixed(2);
    	var minRate = itemObj.minRateEditable;
    	var maxRate = itemObj.maxRateEditable;
    	
    	/*var diffRate = basePriceOld - basepriceNew;
    	alert(diffRate);*/
    	var minRatePerAmount = ((basePriceOld*minRate)/100).toFixed(2);
    	var maxRatePerAmount = ((basePriceOld*maxRate)/100).toFixed(2);
    	
    	var minPerDiff = parseFloat(basePriceOld) - parseFloat(minRatePerAmount);
    	var maxPerAdd = parseFloat(basePriceOld) + parseFloat(maxRatePerAmount);
    	
    	//alert(minRatePerAmount);
    	//alert(maxRatePerAmount);
    	
    	//alert("minPerDiff"+minPerDiff);
    	//alert("maxPerAdd"+maxPerAdd);
    	
    	if(minPerDiff >basepriceNew)
    	{
    		growl.warning("Unit price should be Minimum RS."+minPerDiff,{
				title : ERROR_MSG
			});
    		$scope.servicesListPopup[idx].basePrice = basePriceOld;
    		return false;
    	}
    	
    	if(basepriceNew > maxPerAdd)
    	{
    		growl.warning("Unit price should be Maximun RS."+maxPerAdd,{
				title : ERROR_MSG
			});
    		$scope.servicesListPopup[idx].basePrice = basePriceOld;
    		return false;	
    	}
    	
    	$scope.servicesListPopup[idx].totalAmt = parseFloat(itemObj.quantity) * parseFloat(itemObj.basePrice).toFixed(2);
    	 var autoRenderedServicesList = $scope.servicesListPopup;
    	 $scope.servicePopupCalculateFinal(autoRenderedServicesList);
    }
    
    $scope.addTempServiesToServiceMasterList = function(){
    	
    	
    	
    	
    		$scope.disLis = {};
    		angular.forEach($scope.servicesListPopup, function (value, key) {
			

    	    	var docName = $.grep($scope.doctorsList, function (doc) {
    	            return doc.doctorId == value.doctorId;
    	        })[0].doctorName;
    	    	
    			var newArr = {};
    			newArr.priorityId  = '2';
    			newArr.isOutsourced ='N';
    			newArr.specialityId = value.specialityId;
    			newArr.serviceSpecialityId = value.serviceSpecialityId;
    			newArr.serviceSubSpecialityId = value.serviceSubSpecialityId;
    			newArr.doctorId = value.doctorId;
    			newArr.serviceRendered = "0";
    			newArr.serviceRenderingDeptId = value.serviceRenderingDeptId;
    			newArr.serviceMasterId = value.serviceMasterId;
    			newArr.serviceStandardCode = value.serviceStandardCode;
    			newArr.serviceStandardName = value.serviceStandardName;
    			newArr.isDiscountApplicable = value.isDiscountApplicable;
    			newArr.isQuantityEditable = value.isQuantityEditable;
    			newArr.isRateEditable = value.isRateEditable;
    			newArr.minRateEditable = value.minRateEditable;
    			newArr.maxRateEditable = value.maxRateEditable;
    			newArr.specialityName = value.specialityName;
    			newArr.doctorName =docName;
    			newArr.quantity = value.quantity;
    			newArr.basePrice = value.basePrice;
    			newArr.basePriceOld = value.basePrice;
    			newArr.discountDisabledFlag = 'Y';
    			newArr.totalAmt = parseFloat(value.quantity) * parseFloat(value.basePrice).toFixed(2);
    			newArr.concession = parseFloat(value.concession);
    			newArr.discountPercentage = value.discountPercentage;
    			newArr.discount = value.discount;
    			var dis =value.discount;;
    			var disPer =value.discountPercentage;
    			newArr.taxId = value.taxId;
    			newArr.taxName = value.taxName;
    			newArr.taxPercentage = value.taxPercentage;
    			newArr.discountDetailsList = [];
    			newArr.selfPayable =  "0";
    			newArr.creditPayable =   "0";
    			newArr.oldNetPay = "0";
    			newArr.createdBy  = $scope.userId;
    			newArr.createdDate = new Date().getTime();
    			
    			var totalAmt = parseFloat(1) * parseFloat(500).toFixed(2);
    			var concession = parseFloat(0).toFixed(2);
    			var withConDic =((totalAmt - (concession + dis)));
    			
    			newArr.taxAmt = parseFloat((withConDic * 6)/100).toFixed(2);
    			var gstAmt = (withConDic * 6)/100;
    			newArr.netPay =   parseFloat((totalAmt -(parseFloat(0).toFixed(2) + dis) + gstAmt)).toFixed(2);
    			
    			$scope.autoRenderedServicesFinalList.push(newArr);
			
		});
    		
    		//yogesh start 
    		angular.forEach($scope.selectedPackageList, function (value, key) {
    			
    			//get services if package type is EHC
    	    	if(typeof(value.packageTypeId) != "undefined" && value.packageTypeId == "1"){
    	    		var packageIds = [];
    	    		packageIds.push(value.packageMasterId);
    	    		//get ehc package services 
    	    		var URI = BASE_URL + ROOT_URL + "/api/packages/bill/package/"+packageIds.toString();
    				BillingGenericService.serviceAction("GET", URI, null).then(function(response){
    					if(response.data.status == "success"){
    						var ehcPackageServiceList = response.data.listObject[0].listTPackageServicesDetailsDto;
    						angular.forEach(ehcPackageServiceList,function(val,i){
    							var newArr = {};
    			    			newArr.priorityId  = '2';
    			    			newArr.isOutsourced ='N';
    			    			newArr.specialityId = val.specialityId;
    			    			newArr.serviceSpecialityId = val.specialityId;
    			    			newArr.serviceSubSpecialityId = val.subSpecialityId;
    			    			newArr.serviceRendered = "0";
    			    			newArr.serviceMasterId = val.serviceId;
    			    			newArr.serviceStandardCode = val.serviceCode;
    			    			newArr.serviceStandardName = val.serviceName;
    			    			newArr.isDiscountApplicable = "N";
    			    			newArr.isQuantityEditable = "N";
    			    			newArr.isRateEditable = "N";
    			    			newArr.minRateEditable = 0;
    			    			newArr.maxRateEditable = 0;
    			    			newArr.specialityName = val.specialityName;
    			    			newArr.quantity = 1;//val.numberToBeUse;
    			    			newArr.basePrice = 0;//val.apportionedPrice;
    			    			newArr.basePriceOld = 0;//val.apportionedPrice;
    			    			newArr.discountDisabledFlag = 'N';
    			    			newArr.totalAmt = parseFloat(val.numberToBeUse) * parseFloat(/*val.apportionedPrice*/0).toFixed(2);
    			    			newArr.concession = 0;
    			    			newArr.discountPercentage = 0;
    			    			newArr.discount = 0;
    			    			var dis =0;
    			    			var disPer =0;
    			    			newArr.taxPercentage = 0;
    			    			newArr.discountDetailsList = [];
    			    			newArr.selfPayable =  "0";
    			    			newArr.creditPayable =   "0";
    			    			newArr.oldNetPay = "0";
    			    			newArr.createdBy  = $scope.userId;
    			    			newArr.createdDate = new Date().getTime();
    			    			newArr.packageMasterId = value.packageMasterId;
    			    			newArr.packageTypeId = value.packageTypeId;
    			    			
    			    			var totalAmt = 0//parseFloat(1) * parseFloat(500).toFixed(2);
    			    			var concession = 0//parseFloat(0).toFixed(2);
    			    			var withConDic =((totalAmt - (concession + dis)));
    			    			
    			    			newArr.taxAmt = parseFloat((withConDic * 6)/100).toFixed(2);
    			    			var gstAmt = (withConDic * 6)/100;
    			    			newArr.netPay =   parseFloat((totalAmt -(parseFloat(0).toFixed(2) + dis) + gstAmt)).toFixed(2);
    			    			
    			    			$scope.autoRenderedServicesFinalList.push(newArr);
    						})
    					}
    				});
    	    	}
    			
	    		var newArr = {};
				newArr.priorityId  = '2';
				newArr.isOutsourced ='N';
				newArr.specialityId = value.specialityId;
				newArr.serviceSpecialityId = value.serviceSpecialityId;
				newArr.serviceSubSpecialityId = value.serviceSubSpecialityId;
				newArr.serviceRendered = "0";
				newArr.serviceRenderingDeptId = value.serviceRenderingDeptId;
				newArr.serviceMasterId = value.serviceMasterId;
				newArr.serviceStandardCode = value.serviceStandardCode;
				newArr.serviceStandardName = value.serviceStandardName;
				newArr.isDiscountApplicable = value.isDiscountApplicable;
				newArr.isQuantityEditable = value.isQuantityEditable;
				newArr.isRateEditable = value.isRateEditable;
				newArr.minRateEditable = value.minRateEditable;
				newArr.maxRateEditable = value.maxRateEditable;
				newArr.specialityName = value.specialityName;
				newArr.quantity = value.quantity;
				newArr.basePrice = value.basePrice;
				newArr.basePriceOld = value.basePrice;
				newArr.discountDisabledFlag = 'Y';
				newArr.totalAmt = parseFloat(value.quantity) * parseFloat(value.basePrice).toFixed(2);
				newArr.concession = parseFloat(value.concession);
				newArr.discountPercentage = value.discountPercentage;
				newArr.discount = value.discount;
				var dis =value.discount;;
				var disPer =value.discountPercentage;
				newArr.taxId = value.taxId;
				newArr.taxName = value.taxName;
				newArr.taxPercentage = value.taxPercentage;
				newArr.discountDetailsList = [];
				newArr.selfPayable =  "0";
				newArr.creditPayable =   "0";
				newArr.oldNetPay = "0";
				newArr.createdBy  = $scope.userId;
				newArr.createdDate = new Date().getTime();
				newArr.isPackage = true;
				newArr.packageMasterId = value.packageMasterId;
				newArr.packageTypeId = value.packageTypeId;
				
				var totalAmt = parseFloat(1) * parseFloat(500).toFixed(2);
				var concession = parseFloat(0).toFixed(2);
				var withConDic =((totalAmt - (concession + dis)));
				
				newArr.taxAmt = parseFloat((withConDic * 6)/100).toFixed(2);
				var gstAmt = (withConDic * 6)/100;
				newArr.netPay =   parseFloat((totalAmt -(parseFloat(0).toFixed(2) + dis) + gstAmt)).toFixed(2);
				
				$scope.autoRenderedServicesFinalList.push(newArr);
    		});
    		$scope.selectedPackageList = [];
    		// yogesh end
    		
    		angular.element('#addServicePopup').modal('hide');
    		console.log("ddfdfd",$scope.autoRenderedServicesFinalList);
    		var listServicepopup = $scope.autoRenderedServicesFinalList;
    		$scope.calculationTest(listServicepopup,addService);
    	
    	//addServicePopup
    	//angular.element('#addServicePopup').modal('hide');
    	//return false;
    	//$scope.autoRenderedServicesFinalList.push($scope.servicesListPopup);
		//$scope.calculationTest($scope.autoRenderedServicesFinalList,addService);
    }
    
    
    $scope.getRemoveDiscountPopup = function(item,idxDis)
    {
    	//var idx  = $scope.objectIndex;
    	$scope.objectIndexDiscount = idxDis;
    	$scope.discountObjectForRemove = item;
    	//alert(idx);
    	
    	/*if(item.orderDetailsDiscountId > 0)
    		{
    			var disArr = {};
    			disArr.
    		}*/
    	
    	/*var currDisAmt = $scope.autoRenderedServicesFinalList[idx].discount;
    	var currDisPer = $scope.autoRenderedServicesFinalList[idx].discountPercentage;
    	
    	var finalDisAmt = (parseFloat(currDisAmt) - parseFloat(item.discountAmount)).toFixed(2);
    	var finalDisPer = (parseFloat(currDisPer) - parseFloat(item.discountPercentage)).toFixed(2);
    	
    	$scope.autoRenderedServicesFinalList[idx].discount = finalDisAmt;
    	$scope.autoRenderedServicesFinalList[idx].discountPercentage = finalDisPer;
    	
    	var serviceIndex = $scope.autoRenderedServicesFinalList[idx].discountDetailsList.indexOf(idx);
    	$scope.autoRenderedServicesFinalList[idx].discountDetailsList.splice(serviceIndex, 1);
    	
    	
    	
    	var autoRenderedServicesList = $scope.autoRenderedServicesFinalList;
    	
    	$scope.calculationTest(autoRenderedServicesList,init);
    	$scope.getDiscountListPopup($scope.autoRenderedServicesFinalList,idx);*/
    	
    }
    
    
    $scope.removeDiscount = function()
    {
    	var idx  = $scope.objectIndex;
    	
    	var discIdx = $scope.objectIndexDiscount;
    	//alert(idx);
    	//alert($scope.discountReasonId);
    	//alert($scope.discountRemark);
    	//console.log("DISCOUNT OBJ",$scope.discountObjectForRemove);
    	//console.log("DISCOUN INDEX",$scope.objectIndexDiscount);
    	
    	var item = $scope.discountObjectForRemove;
    	//return false;
    	if(item.orderDetailsDiscountId > 0)
    		{
    			var disArr = {};
    			disArr.orderDetailsDiscountId  = item.orderDetailsDiscountId;
    			disArr.orderDetailsId = item.orderDetailsId;
    			disArr.discountCategoryId = item.discountCategoryId;
    			disArr.discountPercentage = item.discountPercentage;
    			disArr.discountAmount = item.discountAmount;
    			disArr.isCancel = 'Y';
    			disArr.cancelBy  = $scope.userId;
    			disArr.cancelReasonId  = $scope.discountReasonId;
    			disArr.remark = $scope.discountRemark;
    			disArr.createdBy  = item.createdBy;
    			disArr.createdDate  = item.createdDate;
    			disArr.updatedBy  = $scope.userId;
    			disArr.updatedDate  = new Date().getTime();
    			disArr.cancelledDate = new Date().getTime();
    			disArr.orgId  = $scope.organizationId;
    			disArr.unitId  = $scope.unitId;
    			disArr.discountDate = new Date().getTime();
    			disArr.status = "I";
    			disArr.isConsessionDiscount = "N";
    			$scope.removeDiscountList.push(disArr);
    		}
    	
    	
    	var currDisAmt = $scope.autoRenderedServicesFinalList[idx].discount;
    	var currDisPer = $scope.autoRenderedServicesFinalList[idx].discountPercentage;
    	
    	var finalDisAmt = (parseFloat(currDisAmt) - parseFloat(item.discountAmount)).toFixed(2);
    	var finalDisPer = (parseFloat(currDisPer) - parseFloat(item.discountPercentage)).toFixed(2);
    	
    	$scope.autoRenderedServicesFinalList[idx].discount = finalDisAmt;
    	$scope.autoRenderedServicesFinalList[idx].discountPercentage = finalDisPer;
    	
    	var serviceIndex = $scope.autoRenderedServicesFinalList[idx].discountDetailsList.indexOf(item);
    	$scope.autoRenderedServicesFinalList[idx].discountDetailsList.splice(serviceIndex, 1);
    	
    	if($scope.autoRenderedServicesFinalList[idx].discountDetailsList.length == 0)
    		{
    		$scope.autoRenderedServicesFinalList[idx].discountCategoryId = '';
    		}
    	
    	var autoRenderedServicesList = $scope.autoRenderedServicesFinalList;
    	
    	$scope.calculationTest(autoRenderedServicesList,init);
    	$scope.getDiscountListPopup($scope.autoRenderedServicesFinalList,idx);
    	
    	$scope.discountReasonId = '';
    	$scope.discountRemark = '';
    	
    }
    
    $scope.cancelSevices = function()
    {
    	var idx  = $scope.objectIndexService;
    	//alert(idx);
    	var item = $scope.serciveObjectForRemove;
    	
    	console.log("BEFORE",item);
    	
    	//return false;
    	if(item.orderDetailsId > 0)
    		{
    			if(item.discountDetailsList.length > 0){
    				angular.forEach(item.discountDetailsList, function (value, key) {
    					if(value.orderDetailsDiscountId > 0)
    		    		{
    		    			var disArr = {};
    		    			disArr.orderDetailsDiscountId  = value.orderDetailsDiscountId;
    		    			disArr.orderDetailsId = value.orderDetailsId;
    		    			disArr.discountCategoryId = value.discountCategoryId;
    		    			disArr.discountPercentage = value.discountPercentage;
    		    			disArr.discountAmount = value.discountAmount;
    		    			disArr.isCancel = 'Y';
    		    			disArr.cancelBy  = $scope.userId;
    		    			disArr.cancelReasonId  = "";
    		    			disArr.remark ="";
    		    			disArr.createdBy  = value.createdBy;
    		    			disArr.createdDate  = value.createdDate;
    		    			disArr.updatedBy  = $scope.userId;
    		    			disArr.updatedDate  = new Date().getTime();
    		    			disArr.cancelledDate = new Date().getTime();
    		    			disArr.orgId  = $scope.organizationId;
    		    			disArr.unitId  = $scope.unitId;
    		    			disArr.discountDate = new Date().getTime();
    		    			disArr.status = "I";
    		    			disArr.isConsessionDiscount = "N";
    		    			$scope.removeDiscountList.push(disArr);
    		    		}
    	    			
    	    			
    	    		});
    			}
    	

        		var serviceIndex = $scope.autoRenderedServicesFinalList.indexOf(item);
        		
        		$scope.autoRenderedServicesFinalList.splice(serviceIndex, 1);
        		
        		var autoRenderedServicesList = $scope.autoRenderedServicesFinalList;
            	
        		console.log("AFTER",autoRenderedServicesList);
        		
            	$scope.calculationTest(autoRenderedServicesList,init);
            	
            	//test code
            	
            	$scope.removeServicesList = [];
            	
            	var saveArr = {};
    			if(item.orderDetailsId > 0){
    				saveArr.orderDetailsId = item.orderDetailsId;
    			}
    			saveArr.orgId = $scope.organizationId;
    			saveArr.orgUnitId  = $scope.unitId;
    			saveArr.orderId = item.orderId;
    			saveArr.orderQty = item.quantity;
    			saveArr.serviceId = item.serviceMasterId;
    			saveArr.priorityId = item.priorityId;
    			saveArr.isOutsourced = item.isOutsourced;
    			saveArr.orderDate = new Date().getTime();
    			saveArr.orderSchDate = new Date().getTime();
    			saveArr.serviceAmt = item.basePrice;
    			saveArr.ordConcession = item.concession;
    			saveArr.ordDiscount = item.discount;
    			saveArr.netAmount = item.netPay;
    			saveArr.ordDocSplId = item.specialityId;
    			saveArr.taxId = item.taxId;
    			saveArr.taxPer  = item.taxPercentage;
    			saveArr.taxAmount = item.taxAmt;
    			saveArr.selfPayable = item.selfPayable;
    			saveArr.creditPayable = item.creditPayable;
    			saveArr.payeeId = $scope.encounterDetails.payeeId;
    			saveArr.ordTotalAmt =item.totalAmt;
    			saveArr.tariffId = $scope.defaultSelfTariffId;
    			saveArr.billingClassId = '1';
    			saveArr.ordDocId = item.doctorId;
    			saveArr.serviceRendered = item.serviceRendered;
    			saveArr.serviceRenderingDeptId = item.serviceRenderingDeptId;
    			saveArr.ordRenderDatetime = new Date().getTime();
    			saveArr.serviceIsBilled = '0';
    			saveArr.status = 'A';
    			saveArr.updatedBy = $scope.userId;
    			saveArr.updatedDate =new Date().getTime();
    			saveArr.createdBy  = item.createdBy;
    			saveArr.createdDate  = item.createdDate;
    			saveArr.oldNetPay =  parseFloat(item.oldNetPay).toFixed(2);
    			saveArr.ordCancelled = 'N';
    			saveArr.orderEmergencyFlag = 'N';
    			saveArr.isDrug = 'N';
    			
    			saveArr.ordCancelled = "Y"; 
    			saveArr.ordCancelReasonId  = $scope.servicesCancelReasonId;
    			saveArr.ordCancelDatetime  = new Date().getTime();
    			saveArr.ordCancelRemark  = $scope.servicesCancelRemark ;
    			saveArr.status = "I";
    			
    			saveArr.discountDetailsList = [];
    			
    			/*if(item.discountDetailsList == null)
    				{
    				item.discountDetailsList = [];
    				}
    			
    			if(Object.keys(item.discountDetailsList).length > 0)
    				{
    				angular.forEach(item.discountDetailsList, function (value1, key1) {
    					var discounntArr = {};
    					if(value1.orderDetailsDiscountId > 0)
    						{
    							discounntArr.orderDetailsDiscountId = value1.orderDetailsDiscountId;
    							discounntArr.orderDetailsId  = value1.orderDetailsId;
    						}
    					discounntArr.discountCategoryId = value1.discountCategoryId;
    					discounntArr.discountPercentage = value1.discountPercentage;
    					discounntArr.discountAmount  = value1.discountAmount;
    					discounntArr.discountDate = new Date().getTime();
    					discounntArr.status = 'A';
    					discounntArr.updatedBy = $scope.userId;
    					discounntArr.updatedDate =new Date().getTime();
    					discounntArr.createdDate = new Date().getTime();
    					discounntArr.createdBy = $scope.userId;
    					saveArr.discountDetailsList.push(discounntArr);
    				});
    				
    				}
    			else
    				{
    					if(item.discount > 0)
    						{
    							var discounntArr = {};
    							
    							discounntArr.discountCategoryId = item.discountCategoryId;
    							discounntArr.discountPercentage = item.discountPercentage;
    							discounntArr.discountAmount  = item.discount;
    							discounntArr.discountDate = new Date().getTime();
    							discounntArr.status = 'A';
    							discounntArr.updatedBy = $scope.userId;
    							discounntArr.updatedDate =new Date().getTime();
    							discounntArr.createdDate = new Date().getTime();
    							discounntArr.createdBy = $scope.userId;
    							saveArr.discountDetailsList.push(discounntArr);
    					
    						}
    				}*/
    			
    			
    			$scope.removeServicesList.push(saveArr);
    		
    			console.log("RE DIS LISt",$scope.removeDiscountList)
    			console.log("RE SER LISt",$scope.removeServicesList);
    			
            	
            	//test code
            	
            	$scope.servicesCancelReasonId = '';
            	$scope.servicesCancelRemark = '';
        		
    		}else
    	      {
    		      var serviceIndex = $scope.autoRenderedServicesFinalList.indexOf(item);
    		      
    		      $scope.autoRenderedServicesFinalList.splice(serviceIndex, 1);
    		      
    		      var autoRenderedServicesList = $scope.autoRenderedServicesFinalList;
    		         
    		      console.log("AFTER",autoRenderedServicesList);
    		      
    		         $scope.calculationTest(autoRenderedServicesList,init);
		      }
    	
    }
    
    $scope.getRemoveServicePopup = function(item,idx)
    {
    	//console.log("ITEM",item);
    	$scope.objectIndexService = idx;
    	$scope.serciveObjectForRemove = item;
    	
    	
    }
    
    $scope.funDiscountPercentageBillWise = function()
    {
    	//alert($scope.billWiseDiscountObj.discountPercentageBillWise);
    	if($scope.autoRenderedServicesFinalList.length > 0){
    		var totalDisAmount =(((parseFloat($scope.totalAmountSummary) - parseFloat($scope.totalConcessionSummary))*(parseFloat($scope.billWiseDiscountObj.discountPercentageBillWise)/100))).toFixed(2);
    		$scope.billWiseDiscountObj.discountPercentageBillWise = parseFloat($scope.billWiseDiscountObj.discountPercentageBillWise).toFixed(2);
    		$scope.billWiseDiscountObj.discountAmountBillWise = totalDisAmount;
    	}
    }
    
    $scope.funDiscountAmountBillWise = function()
    {
    	//alert($scope.billWiseDiscountObj.discountAmountBillWise);
    	if($scope.autoRenderedServicesFinalList.length > 0){
    		var totalDisPercentage =((parseFloat($scope.billWiseDiscountObj.discountAmountBillWise)/(parseFloat($scope.totalAmountSummary)-parseFloat($scope.totalConcessionSummary)))*100).toFixed(2);
    		$scope.billWiseDiscountObj.discountPercentageBillWise = totalDisPercentage;
    		$scope.billWiseDiscountObj.discountAmountBillWise = parseFloat($scope.billWiseDiscountObj.discountAmountBillWise).toFixed(2);
    	}
    }
    
    $scope.getApplyDiscountPopup = function()
    {
    	if($scope.billWiseDiscountObj.discountPercentageBillWise == '')
    		{
	    		growl.warning("Discount Percentage is required.",{
					title : ERROR_MSG
				});
	    		return false;
    			
    		}

    	else if($scope.billWiseDiscountObj.discountAmountBillWise == '')
    		{
	    		growl.warning("Discount Amount is required.",{
					title : ERROR_MSG
				});
	    		return false;
    		}
    	else if($scope.billWiseDiscountObj.discountCategoryId == '')
    		{
	    		growl.warning("Discount Category is required.",{
					title : ERROR_MSG
				});
	    		return false;
    		}
    	else if($scope.billWiseDiscountObj.discountGivenBy == '')
    		{
	    		growl.warning("discount Giver By is required.",{
					title : ERROR_MSG
				});
	    		return false;
    		}
    	else
    		{
    		 	//$scope.servicesIds = $scope.autoRenderedServicesFinalList.map(function(item) { return item.serviceMasterId; });
    			//console.log(" $scope.servicesIds", $scope.servicesIds);
    			angular.element('#applyDiscountPopup').modal('show');
    		}
    }
    
    $scope.applyDiscount  = function()
    {
    	/*$scope.servicesIds = $scope.autoRenderedServicesFinalList.map(function(item) { return item.serviceMasterId; });
		console.log(" $scope.servicesIds", $scope.servicesIds);*/
    	var i = 0;
    	angular.forEach($scope.autoRenderedServicesFinalList, function (value, key) {
    			
    		$scope.autoRenderedServicesFinalList[i].discountCategoryId = $scope.billWiseDiscountObj.discountCategoryId.toString();
    		$scope.autoRenderedServicesFinalList[i].discountPercentage = $scope.billWiseDiscountObj.discountPercentageBillWise;
    		//for remove discount
    		if($scope.autoRenderedServicesFinalList[i].discountDetailsList.length > 0){
				angular.forEach($scope.autoRenderedServicesFinalList[i].discountDetailsList, function (value, key) {
					if(value.orderDetailsDiscountId > 0)
		    		{
		    			var disArr = {};
		    			disArr.orderDetailsDiscountId  = value.orderDetailsDiscountId;
		    			disArr.orderDetailsId = value.orderDetailsId;
		    			disArr.discountCategoryId = value.discountCategoryId;
		    			disArr.discountPercentage = value.discountPercentage;
		    			disArr.discountAmount = value.discountAmount;
		    			disArr.isCancel = 'Y';
		    			disArr.cancelBy  = $scope.userId;
		    			disArr.cancelReasonId  = "";
		    			disArr.remark ="";
		    			disArr.createdBy  = value.createdBy;
		    			disArr.createdDate  = value.createdDate;
		    			disArr.updatedBy  = $scope.userId;
		    			disArr.updatedDate  = new Date().getTime();
		    			disArr.cancelledDate = new Date().getTime();
		    			disArr.orgId  = $scope.organizationId;
		    			disArr.unitId  = $scope.unitId;
		    			disArr.discountDate = new Date().getTime();
		    			disArr.status = "I";
		    			disArr.isConsessionDiscount = "N";
		    			$scope.removeDiscountList.push(disArr);
		    		}
	    			
	    			
	    		});
			}
    		//end remove discount
    		$scope.autoRenderedServicesFinalList[i].discountDetailsList = [];
    		i++;
    	});
    	console.log("gg",$scope.removeDiscountList);
    	//return false;
    	var autoRenderedServicesList = $scope.autoRenderedServicesFinalList;
    	
		
    	$scope.calculationTest(autoRenderedServicesList,discountPer);
    }
    
    $scope.groupWiseDiscountPopup = function()
    {
    	//$scope.groupWiseDiscountServicesList  = $scope.autoRenderedServicesFinalList;
    	$scope.groupWiseServicesSelectedList.services = [];
    	$scope.chkFlgGroup = 'N';
    	$scope.groupWiseDiscountServicesList = angular.copy($scope.autoRenderedServicesFinalList);
    }
    
    $scope.serachGroupWiseServicesClear = function()
    {
    	$scope.serviceSpecialityIdForGroup = '';
    	$scope.serviceSubSpecialityIdForGroup = '';
    	$scope.chkFlgGroup = 'N';
    	$scope.groupWiseServicesSelectedList.services = [];
    	//$scope.groupWiseDiscountServicesList  = $scope.autoRenderedServicesFinalList;
    	$scope.groupWiseDiscountServicesList = angular.copy($scope.autoRenderedServicesFinalList);
    }
    
    $scope.serachGroupWiseServices = function()
    {
    	if($scope.serviceSpecialityIdForGroup > 0 && $scope.serviceSubSpecialityIdForGroup > 0)
    		{
    			$scope.groupWiseDiscountServicesList = [];
    			//alert("Both Grater");
    			var i = 0;
    			angular.forEach($scope.autoRenderedServicesFinalList, function (value, key) {
        			if(value.serviceSpecialityId == $scope.serviceSpecialityIdForGroup && value.serviceSubSpecialityId == $scope.serviceSubSpecialityIdForGroup)
        				{
        					$scope.groupWiseDiscountServicesList.push($scope.autoRenderedServicesFinalList[i]);
        				}
    				
    	    		i++;
    	    	});
    			
    			
    			
    		}
    	else if($scope.serviceSpecialityIdForGroup > 0)
    		{
	    		$scope.groupWiseDiscountServicesList = [];
				//alert("Both Grater");
				var i = 0;
				angular.forEach($scope.autoRenderedServicesFinalList, function (value, key) {
	    			if(value.serviceSpecialityId == $scope.serviceSpecialityIdForGroup)
	    				{
	    					$scope.groupWiseDiscountServicesList.push($scope.autoRenderedServicesFinalList[i]);
	    				}
					
		    		i++;
		    	});
    		}
    	
    	
    	
    }
    
    $scope.getDisPerPopupForGroup = function(disPer)
    {
    	var len = $scope.groupWiseServicesSelectedList.services.length;
    	if(len == 0)
		{
			growl.warning("Please select at least one service!",{
				title : ERROR_MSG
			});
			$scope.disPerPopupForGroup = '';
    		return false;
		}
    	else
    		{
    		var totalAmtPopupAllServices = 0;
    		for(var i = 0;i < len;i++)
    		{
    			//alert($scope.groupWiseServicesSelectedList.services[i]);
	    		angular.forEach($scope.groupWiseDiscountServicesList, function (value, key) {
	    			if(value.serviceMasterId == $scope.groupWiseServicesSelectedList.services[i])
	    				{
	    					var totalAmtPopup = value.totalAmt;
	    					var concessionPopup = value.concession;
	    					var finalTotalAmt = totalAmtPopup - parseFloat(concessionPopup);
	    					totalAmtPopupAllServices =(parseFloat(totalAmtPopupAllServices) + parseFloat(finalTotalAmt)).toFixed(2);
	    				}
					
		    	});
    		}
    	}
    		
    	var calDicAmt = (totalAmtPopupAllServices*disPer/100).toFixed(2);	
		$scope.disAmtPopupForGroup = calDicAmt;
    }
    
    $scope.getDisAmtPopupForGroup = function(disAmt)
    {
    	var len = $scope.groupWiseServicesSelectedList.services.length;
    	if(len == 0)
		{
			growl.warning("Please select at least one service!",{
				title : ERROR_MSG
			});
			$scope.disAmtPopupForGroup  = '';
    		return false;
		}
    	else
    		{
    		
    		var totalAmtPopupAllServices = 0;
    		for(var i = 0;i < len;i++)
    		{
    			//alert($scope.groupWiseServicesSelectedList.services[i]);
	    		angular.forEach($scope.groupWiseDiscountServicesList, function (value, key) {
	    			if(value.serviceMasterId == $scope.groupWiseServicesSelectedList.services[i])
	    				{
	    					var totalAmtPopup = value.totalAmt;
	    					var concessionPopup = value.concession;
	    					var finalTotalAmt = totalAmtPopup - parseFloat(concessionPopup);
	    					totalAmtPopupAllServices =(parseFloat(totalAmtPopupAllServices) + parseFloat(finalTotalAmt)).toFixed(2);
	    				}
					
		    	});
    		}
    		alert("totalAmtPopupAllServices"+totalAmtPopupAllServices);
    		
    		var disPer = ((parseFloat(disAmt)/totalAmtPopupAllServices)*100).toFixed(2);
    		$scope.disPerPopupForGroup = disPer;
    		}
    	
		
    }
    
    $scope.clearDiscount = function()
    {
    	$scope.disPerPopupForGroup = '';
		$scope.disAmtPopupForGroup = '';
		$scope.disCategoryIdForGroup = '';
    }
    
    $scope.addDiscountInDiscountListForGroup = function()
    {
    	if($scope.disPerPopupForGroup == undefined || $scope.disPerPopupForGroup == '' )
    		{
	    		growl.warning("All fields are required.",{
					title : ERROR_MSG
				});
	    		return false;
    		}
    	else if($scope.disAmtPopupForGroup == undefined || $scope.disAmtPopupForGroup == '')
    		{
	    		growl.warning("All fields are required.",{
					title : ERROR_MSG
				});
	    		return false;
    		}
    	else if($scope.disCategoryIdForGroup == undefined || $scope.disCategoryIdForGroup == '')
		{
    		growl.warning("All fields are required.",{
				title : ERROR_MSG
			});
    		return false;
		}
    	else
    		{
    		var len = $scope.groupWiseServicesSelectedList.services.length;
    			if(len == 0)
    				{
	    				growl.warning("Please select at least one service!",{
	    					title : ERROR_MSG
	    				});
	    	    		return false;
    				}
    			else
    				{
    				
    				for(var i = 0;i < len;i++)
    	    		{
    					var j = 0;
	    				angular.forEach($scope.groupWiseDiscountServicesList, function (value, key) {
	    					var totalAmtPopupForGroup =0;
	    					var concessionPopupForGroup =0;
	    					var finalTotalAmtForGroup = 0;
	    					var calDicAmtForPopup = 0;
	    	    			if(value.serviceMasterId == $scope.groupWiseServicesSelectedList.services[i])
	    	    				{
	    	    					
	    	    				totalAmtPopupForGroup = value.totalAmt;
	    	    				concessionPopupForGroup = value.concession;
	    	    				finalTotalAmtForGroup = totalAmtPopupForGroup - parseFloat(concessionPopupForGroup);
	    	    					
	    	    					$scope.groupWiseDiscountServicesList[j].discountPercentage = parseFloat($scope.disPerPopupForGroup).toFixed(2);
	    	    					$scope.groupWiseDiscountServicesList[j].discountCategoryId = $scope.disCategoryIdForGroup;
	    	    					
	    	    					calDicAmtForPopup = (finalTotalAmtForGroup*$scope.disPerPopupForGroup/100).toFixed(2);	
	    	    					$scope.groupWiseDiscountServicesList[j].discount = calDicAmtForPopup;
	    	    					$scope.groupWiseDiscountServicesList[j].netPay = parseFloat((totalAmtPopupForGroup -(parseFloat(value.concession) + parseFloat(calDicAmtForPopup)) + parseFloat(value.taxAmt))).toFixed(2);
	    	    					
	    	    				}
	    	    			j++;
	    		    	});
	    				
    				}
    				console.log('scs',$scope.groupWiseDiscountServicesList);
    				console.log("ff",$scope.autoRenderedServicesFinalList);
    			}
    		}
    	
    	
    }
    
    $scope.saveGroupDiscountToFinalList = function()
    {
    	
    	var len = $scope.groupWiseServicesSelectedList.services.length;
		if(len == 0)
			{
				growl.warning("Please select at least one service!",{
					title : ERROR_MSG
				});
	    		return false;
			}
		else
			{
			
    	for(var i = 0;i < len;i++)
		{
			var j = 0;
			angular.forEach($scope.autoRenderedServicesFinalList, function (value, key) {
				var totalAmtPopupForGroup =0;
				var concessionPopupForGroup =0;
				var finalTotalAmtForGroup = 0;
				var calDicAmtForPopup = 0;
    			if(value.serviceMasterId == $scope.groupWiseServicesSelectedList.services[i])
    				{
    					
    				totalAmtPopupForGroup = value.totalAmt;
    				concessionPopupForGroup = value.concession;
    				finalTotalAmtForGroup = totalAmtPopupForGroup - parseFloat(concessionPopupForGroup);
    					
    					$scope.autoRenderedServicesFinalList[j].discountPercentage = parseFloat($scope.disPerPopupForGroup).toFixed(2);
    					$scope.autoRenderedServicesFinalList[j].discountCategoryId = $scope.disCategoryIdForGroup;
    					
    					calDicAmtForPopup = (finalTotalAmtForGroup*$scope.disPerPopupForGroup/100).toFixed(2);	
    					$scope.autoRenderedServicesFinalList[j].discount = calDicAmtForPopup;
    					//for remove discount
    		    		if($scope.autoRenderedServicesFinalList[j].discountDetailsList.length > 0){
    						angular.forEach($scope.autoRenderedServicesFinalList[j].discountDetailsList, function (value, key) {
    							if(value.orderDetailsDiscountId > 0)
    				    		{
    				    			var disArr = {};
    				    			disArr.orderDetailsDiscountId  = value.orderDetailsDiscountId;
    				    			disArr.orderDetailsId = value.orderDetailsId;
    				    			disArr.discountCategoryId = value.discountCategoryId;
    				    			disArr.discountPercentage = value.discountPercentage;
    				    			disArr.discountAmount = value.discountAmount;
    				    			disArr.isCancel = 'Y';
    				    			disArr.cancelBy  = $scope.userId;
    				    			disArr.cancelReasonId  = "";
    				    			disArr.remark ="";
    				    			disArr.createdBy  = value.createdBy;
    				    			disArr.createdDate  = value.createdDate;
    				    			disArr.updatedBy  = $scope.userId;
    				    			disArr.updatedDate  = new Date().getTime();
    				    			disArr.cancelledDate = new Date().getTime();
    				    			disArr.orgId  = $scope.organizationId;
    				    			disArr.unitId  = $scope.unitId;
    				    			disArr.discountDate = new Date().getTime();
    				    			disArr.status = "I";
    				    			disArr.isConsessionDiscount = "N";
    				    			$scope.removeDiscountList.push(disArr);
    				    		}
    			    			
    			    			
    			    		});
    					}
    		    		//end remove discount  
    					$scope.autoRenderedServicesFinalList[j].discountDetailsList = [];
    					
    				}
    			j++;
	    	});
			
			}
    	
    		var autoRenderedServicesList = $scope.autoRenderedServicesFinalList;
    		$scope.calculationTest(autoRenderedServicesList,discountPer);
    	}	
    }
    
    
    $scope.cancelDiscountForServices = function()
    {
    	
    	var len = $scope.servicesSelectedList.services.length;
		if(len == 0)
			{
				growl.warning("Please select at least one service!",{
					title : ERROR_MSG
				});
	    		return false;
			}
		else
			{
				angular.element('#cancelDiscountPopup').modal('show');
			}
    	
    }
    
    $scope.finalCancelDiscount = function()
    {
    	
    	var len = $scope.servicesSelectedList.services.length;
    	
    	var cancelDiscountObjectList = [];
    	var cancelRemoveDiscountList = [];
    	for(var i = 0;i < len;i++)
		{
			var j = 0;
			angular.forEach($scope.autoRenderedServicesFinalList, function (value, key) {
				
				var totalAmt = 0;
				var dis = 0;
				var gstAmt = 0;
				var withConDic = 0;
    			if(value.serviceMasterId == $scope.servicesSelectedList.services[i] && value.discount > 0)
    				{
    				$scope.autoRenderedServicesFinalList[j].discountPercentage = 0;
					$scope.autoRenderedServicesFinalList[j].discountCategoryId = '';
					$scope.autoRenderedServicesFinalList[j].discount = 0;
					
					totalAmt = (parseFloat(value.quantity) * parseFloat(value.basePrice)).toFixed(2);
					gstAmt = parseFloat((parseFloat(withConDic) * value.taxPercentage)/100).toFixed(2);
					withConDic =parseFloat(totalAmt - (parseFloat(value.concession) + parseFloat(dis))).toFixed(2);

					$scope.autoRenderedServicesFinalList[j].taxAmt = parseFloat((parseFloat(withConDic) * value.taxPercentage)/100).toFixed(2);
					
					$scope.autoRenderedServicesFinalList[j].netPay =   parseFloat((totalAmt -(parseFloat(value.concession) + parseFloat(dis)) + parseFloat(gstAmt))).toFixed(2);

					$scope.autoRenderedServicesFinalList[j].selfPayable =   parseFloat((totalAmt -(parseFloat(value.concession) + parseFloat(dis)) + parseFloat(gstAmt))).toFixed(2);

					$scope.autoRenderedServicesFinalList[j].totalAmt = (parseFloat(value.quantity) * parseFloat(value.basePrice)).toFixed(2);
					
					
					
					//for remove discount
		    		if($scope.autoRenderedServicesFinalList[j].discountDetailsList.length > 0){
						angular.forEach($scope.autoRenderedServicesFinalList[j].discountDetailsList, function (value1, key) {
							if(value1.orderDetailsDiscountId > 0)
				    		{
				    			var disArr = {};
				    			disArr.orderDetailsDiscountId  = value1.orderDetailsDiscountId;
				    			disArr.orderDetailsId = value1.orderDetailsId;
				    			disArr.discountCategoryId = value1.discountCategoryId;
				    			disArr.discountPercentage = value1.discountPercentage;
				    			disArr.discountAmount = value1.discountAmount;
				    			disArr.isCancel = 'Y';
				    			disArr.cancelBy  = $scope.userId;
				    			disArr.cancelReasonId  = "";
				    			disArr.remark ="";
				    			disArr.createdBy  = value1.createdBy;
				    			disArr.createdDate  = value1.createdDate;
				    			disArr.updatedBy  = $scope.userId;
				    			disArr.updatedDate  = new Date().getTime();
				    			disArr.cancelledDate = new Date().getTime();
				    			disArr.orgId  = $scope.organizationId;
				    			disArr.unitId  = $scope.unitId;
				    			disArr.discountDate = new Date().getTime();
				    			disArr.status = "I";
				    			disArr.isConsessionDiscount = "N";
				    			cancelRemoveDiscountList.push(disArr);
				    		}
			    			
			    			
			    		});
					}
		    		//end remove discount  
		    		$scope.autoRenderedServicesFinalList[j].discountDetailsList = [];
		    		if(value.orderDetailsId > 0)
		    		{
		    			cancelDiscountObjectList.push($scope.autoRenderedServicesFinalList[j]);
		    		}
    			}
    			j++;
	    	});
			
		}//for End
    	
    	if(cancelDiscountObjectList.length > 0){
    	var autoRenderedServicesList = $scope.autoRenderedServicesFinalList;
		var reVal = $scope.calculationTest(autoRenderedServicesList,discountPer);
    	//return false;
    	//for SaveCancel
    	if(reVal == 1)
    	{
    	var data={
    			
				patientId :$scope.encounterDetails.patientId,
				encounterId :$scope.encounterDetails.encounterId,
				createdBy :$scope.userId,
				createdDate :new Date().getTime(),
				updatedBy :$scope.userId,
				updatedDate :new Date().getTime(),
				orderDate :new Date().getTime(),
				organisationId :$scope.organizationId,
				unitId :$scope.unitId,
				isFinalBill : 'N',
				ordDoctorId :$scope.encounterDetails.doctorId,
				visitTypeId : '1',
				billDateTime : new Date().getTime(),
				netAmount : $scope.netPayableSummary,
				discountAmount : $scope.totalDiscountSummary,
				concessionAmount : $scope.totalConcessionSummary,
				gst : $scope.totalGstTaxSummary,
				totalBillAmount : $scope.totalAmountSummary,
				roundOff : $scope.roundOffAmt,
				isCreditBill:'N',
				billCancelled : 'N',
				billRemark : $scope.billRemark,
				billStatusId : '1', // 1. Pending,
				selfPayable : $scope.totalSelfPayble,
				creditPayable : $scope.totalCreditPayble,
				cancelledDiscountsList : cancelRemoveDiscountList
		};
    	
    	var saveOrderDetailsObj = [];
		angular.forEach(cancelDiscountObjectList, function (value, key) {
			var saveArr = {};
			
			saveArr.orderDetailsId = value.orderDetailsId;
			saveArr.orgId = $scope.organizationId;
			saveArr.orgUnitId  = $scope.unitId;
			saveArr.orderId = value.orderId;
			saveArr.orderQty = value.quantity;
			saveArr.serviceId = value.serviceMasterId;
			saveArr.priorityId = value.priorityId;
			saveArr.isOutsourced = value.isOutsourced;
			saveArr.orderDate = new Date().getTime();
			saveArr.orderSchDate = new Date().getTime();
			saveArr.serviceAmt = value.basePrice;
			saveArr.ordConcession = value.concession;
			saveArr.ordDiscount = value.discount;
			saveArr.netAmount = value.netPay; //P
			saveArr.ordDocSplId = value.specialityId;
			saveArr.taxId = value.taxId;
			saveArr.taxPer  = value.taxPercentage;
			saveArr.taxAmount = value.taxAmt; //P
			saveArr.selfPayable = value.selfPayable; //P
			saveArr.creditPayable = value.creditPayable;
			saveArr.payeeId = $scope.encounterDetails.payeeId;
			saveArr.ordTotalAmt =value.totalAmt; //P
			saveArr.tariffId = $scope.defaultSelfTariffId;
			saveArr.billingClassId = '1';
			saveArr.ordDocId = value.doctorId;
			saveArr.serviceRendered = value.serviceRendered;
			saveArr.serviceRenderingDeptId = value.serviceRenderingDeptId;
			saveArr.ordRenderDatetime = new Date().getTime();
			saveArr.serviceIsBilled = '0';
			saveArr.status = 'A';
			saveArr.updatedBy = $scope.userId;
			saveArr.updatedDate =new Date().getTime();
			saveArr.createdBy  = $scope.userId;
			saveArr.createdDate  = value.createdDate;
			saveArr.oldNetPay =  parseFloat(value.oldNetPay).toFixed(2); //P
			saveArr.ordCancelled = 'N';
			saveArr.orderEmergencyFlag = 'N';
			saveArr.isDrug = 'N';
			saveArr.discountDetailsList = [];
			saveOrderDetailsObj.push(saveArr);
		});
		data.orderDetailsMasterDtosList = saveOrderDetailsObj;
		data.cancelOrderDetailsList = [];
    	console.log("CANCEL DATA",data);
    	
    	var URI = BASE_URL + ROOT_URL + SAVEBILL;
		BillingGenericService.serviceAction(METHOD_POST,URI, data , NOTIFICATION_MSG_STATUS_FALSE).then(function(response){
			console.log(response)
			if(response.data.status == 'success'){
				//alert("Success SET");
				
				growl.success("Discount Canceled Successfully!!",{
					title : SUCCESS_MSG
				});
				$scope.servicesSelectedList = {
					    services: []
					  };
				//$scope.init();
			}
			
		});
		
    }
   }
    	//for End SaveCnacel
    	
    }
	// function for popup ok button call
	$scope.PopupOkBtn = function() {
		$scope.popUpFlag = false;
		$scope.saveBillings();
	}
    
    $scope.saveBillings = function(){
		//alert($scope.finalBill);
		console.log("SAVE ORDER DETAILS",$scope.autoRenderedServicesFinalList);
		
		console.log("tttt",$scope.removeDiscountList);
		//return false;
		var data={
			
				patientId :$scope.encounterDetails.patientId,
				encounterId :$scope.encounterDetails.encounterId,
				createdBy :$scope.userId,
				createdDate :new Date().getTime(),
				updatedBy :$scope.userId,
				updatedDate :new Date().getTime(),
				orderDate :new Date().getTime(),
				organisationId :$scope.organizationId,
				unitId :$scope.unitId,
				isFinalBill : $scope.finalBill,
				ordDoctorId :$scope.encounterDetails.doctorId,
				visitTypeId : '1',
				billDateTime : new Date().getTime(),
				netAmount : $scope.netPayableSummary,
				discountAmount : $scope.totalDiscountSummary,
				concessionAmount : $scope.totalConcessionSummary,
				gst : $scope.totalGstTaxSummary,
				totalBillAmount : $scope.totalAmountSummary,
				roundOff : $scope.roundOffAmt,
				isCreditBill:'N',
				billCancelled : 'N',
				billRemark : $scope.billRemark,
				billStatusId : '1', // 1. Pending,
				selfPayable : $scope.totalSelfPayble,
				creditPayable : $scope.totalCreditPayble,
				cancelledDiscountsList : $scope.removeDiscountList
				
				
		};
		
		var saveOrderDetailsObj = [];
		angular.forEach($scope.autoRenderedServicesFinalList, function (value, key) {
			var saveArr = {};
			if(value.orderDetailsId > 0){
				saveArr.orderDetailsId = value.orderDetailsId;
			}
			saveArr.orgId = $scope.organizationId;
			saveArr.orgUnitId  = $scope.unitId;
			saveArr.orderId = value.orderId;
			saveArr.orderQty = value.quantity;
			saveArr.serviceId = value.serviceMasterId;
			saveArr.priorityId = value.priorityId;
			saveArr.isOutsourced = value.isOutsourced;
			saveArr.orderDate = new Date().getTime();
			saveArr.orderSchDate = new Date().getTime();
			saveArr.serviceAmt = value.basePrice;
			saveArr.ordConcession = value.concession;
			saveArr.ordDiscount = value.discount;
			saveArr.netAmount = value.netPay;
			saveArr.ordDocSplId = value.specialityId;
			saveArr.taxId = value.taxId;
			saveArr.taxPer  = value.taxPercentage;
			saveArr.taxAmount = value.taxAmt;
			saveArr.selfPayable = value.selfPayable;
			saveArr.creditPayable = value.creditPayable;
			saveArr.payeeId = $scope.encounterDetails.payeeId;
			saveArr.ordTotalAmt =value.totalAmt;
			saveArr.tariffId = $scope.defaultSelfTariffId;
			saveArr.billingClassId = '1';
			saveArr.ordDocId = value.doctorId;
			saveArr.serviceRendered = value.serviceRendered;
			saveArr.serviceRenderingDeptId = value.serviceRenderingDeptId;
			saveArr.ordRenderDatetime = new Date().getTime();
			saveArr.serviceIsBilled = '0';
			saveArr.status = 'A';
			saveArr.updatedBy = $scope.userId;
			saveArr.updatedDate =new Date().getTime();
			saveArr.createdBy  = value.createdBy;
			saveArr.createdDate  = value.createdDate;
			saveArr.oldNetPay =  parseFloat(value.oldNetPay).toFixed(2);
			saveArr.ordCancelled = 'N';
			saveArr.orderEmergencyFlag = 'N';
			saveArr.isDrug = 'N';
			saveArr.isPackage = typeof(value.isPackage) != "undefined" ? value.isPackage : false;
			saveArr.packageId = typeof(value.packageMasterId) != "undefined" ? value.packageMasterId : null;
			saveArr.packageTypeId = typeof(value.packageTypeId) != "undefined" ? value.packageTypeId : null;
			saveArr.discountDetailsList = [];
			
			if(value.discountDetailsList == null)
				{
				value.discountDetailsList = [];
				}
			
			if(Object.keys(value.discountDetailsList).length > 0)
				{
				angular.forEach(value.discountDetailsList, function (value1, key1) {
					var discounntArr = {};
					if(value1.orderDetailsDiscountId > 0)
						{
							discounntArr.orderDetailsDiscountId = value1.orderDetailsDiscountId;
							discounntArr.orderDetailsId  = value1.orderDetailsId;
						}
					discounntArr.discountCategoryId = value1.discountCategoryId;
					discounntArr.discountPercentage = value1.discountPercentage;
					discounntArr.discountAmount  = value1.discountAmount;
					discounntArr.discountDate = new Date().getTime();
					discounntArr.status = 'A';
					discounntArr.updatedBy = $scope.userId;
					discounntArr.updatedDate =new Date().getTime();
					discounntArr.createdBy  = value1.createdBy;
					discounntArr.createdDate  = value1.createdDate;
					saveArr.discountDetailsList.push(discounntArr);
				});
				
				}
			else
				{
					if(value.discount > 0)
						{
							var discounntArr = {};
							
							discounntArr.discountCategoryId = value.discountCategoryId;
							discounntArr.discountPercentage = value.discountPercentage;
							discounntArr.discountAmount  = value.discount;
							discounntArr.discountDate = new Date().getTime();
							discounntArr.status = 'A';
							discounntArr.updatedBy = $scope.userId;
							discounntArr.updatedDate =new Date().getTime();
							discounntArr.createdDate = new Date().getTime();
							discounntArr.createdBy = $scope.userId;
							saveArr.discountDetailsList.push(discounntArr);
					
						}
				}
			saveOrderDetailsObj.push(saveArr);
		});
		data.orderDetailsMasterDtosList = saveOrderDetailsObj;
		data.cancelOrderDetailsList = $scope.removeServicesList;
		//return false;
		
			if($scope.finalBill == 'Y')
			{
				if ($scope.popUpFlag) {
					$scope.showModal = !$scope.showModal;

				}
				else{
						$scope.popUpFlag = true;
						console.log("finalSaveData",data);
						console.log(JSON.stringify(data));
						//return false;
						var URI = BASE_URL + ROOT_URL + SAVEBILL;
						BillingGenericService.serviceAction(METHOD_POST,URI, data , NOTIFICATION_MSG_STATUS_TRUE).then(function(response){
							//console.log(response)
							if(response.data.status == 'success'){
								//alert("Success SET Billed");
								$state.go('billList');
								//angular.element('#collectPayment').modal('show');
							}
							
						});
						
						
				}
			}
			else
				{
				if ($scope.popUpFlag) {
					$scope.showModal = !$scope.showModal;

				}
				else{
					//alert("Direct");
					
					
					console.log("finalSaveData",data);
					//return false;
					var URI = BASE_URL + ROOT_URL + SAVEBILL;
					BillingGenericService.serviceAction(METHOD_POST,URI, data , NOTIFICATION_MSG_STATUS_TRUE).then(function(response){
						console.log(response)
						if(response.data.status == 'success'){
							//alert("Success SET");
							$scope.init();
						}
						
					});
					
				}
					
				}
		
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
	
	/** Yogesh Start **/
	
	$scope.initPackageSearchObj = function(){
		$scope.packageSearchObj = {packageTypeId : "1", serviceCode:"",serviceName:""}
	}
	$scope.initPackageSearchObj();
	
	$scope.getPackageTypeList = function(orgId,unitId) {
		var URI = BASE_URL + ROOT_URL + "/Unit/Service/EHCpackage/getPackgeType/"+orgId+"/"+unitId;
		BillingGenericService.serviceAction("GET", URI, null).then(function(response){
			$scope.packageTypeList = response.data.listObject;
		});
	}
	$scope.getPackageTypeList($scope.organizationId,$scope.unitId);
	
	$scope.searchPackages = function(packageSearchObj){
		if(packageSearchObj.packageTypeId != ""){
			packageSearchObj.organisationId = $scope.organizationId;
			packageSearchObj.unitId = $scope.unitId;
			packageSearchObj.visitTypeId = 1;//default op
			packageSearchObj.bedBillingCategoryId = 1;//default op
			var URI = BASE_URL + ROOT_URL + "/api/packages/bill/services/search";
			BillingGenericService.serviceAction("POST", URI, packageSearchObj).then(function(response){
				if(response.data.status == "success")
					$scope.searchPackageList = response.data.listObject;
			});
		}
	}
	
	$scope.selectPackageService = function($item, $model, $label){
		var packageIds = [];
		angular.forEach($scope.selectedPackageList,function(value,index){
			packageIds.push(value.packageMasterId);
		});
		if(packageIds.indexOf($item.packageMasterId) == -1){
			$scope.selectedPackageList.push($item);
			$scope.initPackageSearchObj();
		}else{
			growl.error("Package Already Added",{
				title : ERROR_MSG
			});
		}
	}
	
	$scope.removeSelectedPackage = function(index){
		$scope.selectedPackageList.splice(index,1);
	}
	
	$scope.getPackages = function(){
		var packageIds = [];
		angular.forEach($scope.autoRenderedServicesFinalList,function(value,key){
			if(value.isPackage)
				packageIds.push(value.packageMasterId);
		});
		
		//get package details
		if(packageIds.length > 0){
			var URI = BASE_URL + ROOT_URL + "/api/packages/bill/package/"+packageIds.toString();
			BillingGenericService.serviceAction("GET", URI, null).then(function(response){
				if(response.data.status == "success")
					$scope.packageDetailsList = response.data.listObject;
			});
		}
	}
	
	$scope.getActivePackagesList = function(){
		var URI =BASE_URL + ROOT_URL + "/api/packages/patientId/"+$scope.patientDetails.patientId+"/encounterId/"+$scope.encounterDetails.encounterId+"/visitTypeId/1";
		var header = {organisationId: $scope.organizationId,unitId:$scope.unitId};
		GenericServiceParamHeader.serviceAction("GET", URI, null,NOTIFICATION_MSG_STATUS_FALSE,null,header).then(function(response){
			if(response.data.status == "success")
				$scope.activePackagesList = response.data.listObject;
			
		});
	}
	
	$scope.currentActivePackageServices = function(activePack){
		$scope.currentActivePackage = angular.copy(activePack);
	}
	
	$scope.addCurrentPackageServices = function(){
		angular.forEach($scope.currentActivePackage.listPackageConsumptionServiceDetailDto,function(value,index){
			if(value.isSelected){
				var obj = {
						basePrice:0,
						concession:0,
						discount:0,
						isDiscountApplicable:"N",
						isQuantityEditable:"N",
						isRateEditable:"N",
						isTaxApplicable:"N",
						maxRateEditable:0,
						minRateEditable:0,
						packageMasterId:$scope.currentActivePackage.packageId,
						serviceMasterId:value.serviceId,
						serviceStandardCode:value.serviceCode,
						serviceStandardName:value.serviceStandardName,
						quantity:1,
						specialityId:value.specialityId,
						specialityName:value.specialityName,
						subSpecialityId:value.subSpecialityId,
						subSpecialityName:value.subSpecialityName,
						taxPercentage:0,
						discountPercentage : 0,
						};
				$scope.servicesListPopup.push(obj);
			}
		});
	}
	
	/** Yogesh End **/
}]);
	