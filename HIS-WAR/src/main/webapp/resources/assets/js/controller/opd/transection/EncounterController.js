/**
 * @author Nikhil
 * 		Controller for Encounter Creation
 */
angular.module("myApp").controller("EncounterController", [ '$scope','$rootScope','BillingGenericService','GenericService','GenericServiceParamHeader','$sessionStorage','PagerService','promiseFactory','$cookies','$state','promiseFactoryWithObject','growl','$stateParams','$http',
	function($scope,$rootScope,BillingGenericService,GenericService,GenericServiceParamHeader,$sessionStorage,PagerService,promiseFactory,$cookies,$state,promiseFactoryWithObject,growl,$stateParams,$http){
	
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
	 //alert("defaultSelfTariffId"+$scope.defaultSelfTariffId)
	 
	$scope.enableDisabled  = false;
	$rootScope.loginpage = true;
	$scope.unitId = cookieObject.unitId;
	$scope.organizationId = cookieObject.organizationId;
	$scope.sexMasterList=[];
	$scope.doctorList=[];
	$scope.associatedCompanyList = [];
	$scope.userId = 1;
	$scope.nextOfKinId = "";
	
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
		/*$scope.patientDetails = {
				"patientName" : "",
				"genderCode"  : "",
				"age"         : "", 
				"uhIdNumber"  : ""
		}*/
		$scope.patientDetails = {
				"patientId"	  : "",
				"patientName" : "",
				"genderCode"  : "",
				"age"         : "", 
				"uhIdNumber"  : "",
				"birthDate"	  : "",
				"encounterId" : ""
		}
	}
	$scope.initPatientSearchObj();
	
	$scope.initKinDetails = function(){
		  /* Add Next Of Kin Details */
	    $scope.kinDetailsList = [];
	    $scope.kinDetails = {
				prefixId   		: '',
				kinName  		: '',
				relationId 		: '',
				mobileNo 		: '',
				phoneNo 		: '',
				expiry 			: '',
				identificationId: '',
				identificationNo: '',
				isGuarantor 	: 'N',
				address 		: '',
				countryId 		: '',
				stateId 		: '',
				districtId 		: '',
				cityId 			: '',
				areaId 			: '',
				postCode 		: '',
				patientId       : '',
				unitId			: $scope.unitId,
				organizationId  : $scope.organizationId,
				createdBy		: $scope.userId,
				updatedBy		: $scope.userId,
				createdDate		: moment(new Date()).format('DD-MM-YYYY HH:mm:ss'),
				updatedDate		: moment(new Date()).format('DD-MM-YYYY HH:mm:ss')
			};
		
		$scope.kinDetailsList.push(angular.copy($scope.kinDetails));
	}
	$scope.initKinDetails();
  
    $scope.addNewKinDetails = function(){
    	$scope.kinDetails.patientId = $scope.appointmentMasterObj.patientId;
    	$scope.kinDetailsList.push(angular.copy($scope.kinDetails));
    };
    $scope.removeKinDetails = function(indexToBeDeleted){	
		  $scope.kinDetailsList.splice(indexToBeDeleted, 1);
    };
    
	$scope.initAppointmentMasterObj = function(){
		/*$scope.appointmentMasterObj = {
				"appointmentMediaTypeId":"",
				"patientId" 			:"1",
				"doctorId"				:"",
				"opVisitType"			:"",
				"appointmentDate"		:1515474059174,
				"timeSlot"				:"",
				"appointmentTypeId"		:"",
				"categoryTypeId"		:"",
				"appointmentStatusId"	:"",
				"registrationDate"		:"",
				"departmentId"			:"",
				"isMlc"					:"I",
				"sourceId"				:"",
				"isReferal"				:"I",
				"remark"				:"",
				"kinDetailsId"			:"",
				"status"				:"A",
				"createdBy"				:$scope.unitId,
				"createdDate"			:new Date().getTime(),
				"updatedBy"				:$scope.userId,
				"updatedDate"			:new Date().getTime(),
				"unitId"				:$scope.userId,
				"organizationId"		:$scope.organizationId,
				"listKinDetailsDto"		:$scope.kinDetailsList
		}*/
		$scope.commonObj = {
				"unitId"				:$scope.userId,
				"organizationId"		:$scope.organizationId,
				
		}
		
		$scope.appointmentMasterObj = {
				 "encounterId"			:"",
				 "patientId"			:"",
				 "visitTypeId"			:"",
				 "doctorId"				:"",
				 "sourceId"				:"",
				 "encounterDate"		:new Date().getTime(),
				 "encounterTime"		:"",
				 "isReferal"			:"A",
				 "deptId"				:"",
				 "remark"				:"",
				 "kinDetailsId"			:"",
				 "paymentEntitlementTypeId":"1",
				 "encounterNumber"		:"",
				 "createdDate"			:new Date().getTime(),
				 "createdBy"			:$scope.unitId,
				 "updatedDate"			:new Date().getTime(),
				 "updatedBy"			:$scope.unitId,
				 "status"				:"A",
				 "unitId"				:$scope.userId,
				 "organizationId"		:$scope.organizationId,
				 "listKinDetailsDto"	:$scope.kinDetailsList,
				 "isScheduledAppointment":"I",
				 "modalityId":"",
				 "specialityId":"",
				 "systemGeneratedVisitTypeId":"",
				 "userDefinedVisitTypeId":"",
				 "reasonIdForChangingVisitType":"",
				 "remarkForChangingVisitType":"",
				 "encounterTypeId":"",
				 "clinicId":"",
				 "referralTypeId":"",
				 "referralId":"",
				 "referralRemark":"",
				 "defaultSelfTariffId" : $scope.defaultSelfTariffId,
				 "currentDate" :  moment(new Date()).format('YYYY-MM-DD')
			}	
			
	}
	$scope.initAppointmentMasterObj();
	
	$scope.getClinicByDoctorId = function(doctorId)
	{
		var data = {};
		var paramObj = {
				'doctor_id' : doctorId
		};
		var headers={
				'organization_id': $scope.organizationId,
				'unit_id':$scope.unitId,
				'Content-Type':'application/json'
		};
		
		
	   // return false;
	    var URI = BASE_URL + ROOT_URL
		+ GETCLINICMASTERLISTBYDOCTORID;

		GenericServiceParamHeader
		.serviceAction(METHOD_GET, URI,
				data,
				NOTIFICATION_MSG_STATUS_FALSE,paramObj,headers)
		.then(
				function(response) {
					console.log(response);
					// $rootScope.startLoader();
					$scope.clinicList = [];
					if (response.data.status == "success")
						
						$scope.clinicList =response.data.listObject;
						console.log("Clnic List",$scope.clinicList);
				});
		
		//console.log("ff",$scope.encounterDetailsObj);
		//console.log("ff2",$scope.encounterDetailsObj.patientId);
		if( $scope.encounterDetailsObj.patientId == undefined){
			if($scope.patientDetails.patientId > 0)
				{
				 var  testData = {
					    	
			    		 unitId :$scope.unitId,
						 organizationId  :$scope.organizationId,
			    		 patientId : $scope.patientDetails.patientId  ,
			    		 doctorId : doctorId ,
			    		 specialityId : $scope.appointmentMasterObj.deptId,
			    		 currentDate :  moment(new Date()).format('YYYY-MM-DD')
				    		 
			     };
				 $scope.appointmentMasterObj.specialityId  = $scope.appointmentMasterObj.deptId;
				 console.log("SpecilaityId:",$scope.appointmentMasterObj.specialityId);
				 
			    // testData.currentDate = moment(new Date()).format('YYYY-MM-DD');
			     console.log("new direct obj",testData);
			     //return false;
			     var URI = BASE_URL + ROOT_URL + GETSYSTEMGENERATEDVISITTYPEID;
			     BillingGenericService.serviceAction(METHOD_POST,URI, testData, NOTIFICATION_MSG_STATUS_FALSE).then(function(response){
			     
			    $scope.appointmentMasterObj.systemGeneratedVisitTypeId=response.data.toString();
			    $scope.appointmentMasterObj.userDefinedVisitTypeId=response.data.toString();
			     
			     });
			     
				}
			else
				{
					growl.warning("Please Select Patient",{
						title : WARNING_MSG
					});
					return;
				}
		}
		
	}
	
	//-------------- function to check create Encounter based on appointment -------------//
	$scope.checkAppointment=function(){
		 console.log("stateObj", $stateParams.appointmentObj);
		 var appointmentObj=$stateParams.appointmentObj;
		 console.log(appointmentObj);
		 
		 $scope.encounterDetailsObj=appointmentObj;
			 
				if(appointmentObj.patientId != undefined && appointmentObj.appointmentTypeId == 1){
					$rootScope.startLoader();
					
					 //Test
				     
				     var  testData = {
				    	
				    		 unitId          :$scope.unitId,
							 organizationId  :$scope.organizationId,
				    		 patientId : appointmentObj.patientId ,
				    		 doctorId : appointmentObj.doctorId ,
				    		 specialityId : appointmentObj.specialityId,
				    		 currentDate :  moment(new Date()).format('YYYY-MM-DD')
 				    		 
				     };
				    // testData.currentDate = moment(new Date()).format('YYYY-MM-DD');
				     console.log("new obj",testData);
				     //return false;
				     var URI = BASE_URL + ROOT_URL + GETSYSTEMGENERATEDVISITTYPEID;
				     BillingGenericService.serviceAction(METHOD_POST,URI, testData, NOTIFICATION_MSG_STATUS_FALSE).then(function(response){
				     
				    $scope.appointmentMasterObj.systemGeneratedVisitTypeId=response.data.toString();
				    $scope.appointmentMasterObj.userDefinedVisitTypeId=response.data.toString();
				     
				     });
				     
				     //End Test
				    //return false;
					
					//alert(appointmentObj.appointmentTypeId);
				     $scope.getClinicByDoctorId(appointmentObj.doctorId);
					if(appointmentObj.appointmentTypeId == 2)
						{
							$scope.enableDisabled  = true;
							$scope.appointmentMasterObj.modalityId=appointmentObj.modalityId;
						}
					
					var URI = BASE_URL + ROOT_URL + "/api/global/patient/"+appointmentObj.patientId;
					BillingGenericService.serviceAction(METHOD_GET,URI, "", NOTIFICATION_MSG_STATUS_FALSE).then(function(response){
						$scope.patientDetails.patientId = response.data.object.patientId,
						$scope.patientDetails.patientName = response.data.object.patientName,
						$scope.patientDetails.genderCode = response.data.object.genderCode,
						$scope.patientDetails.age = response.data.object.age, 
						$scope.patientDetails.uhIdNumber = response.data.object.UHIDNumber,
						$scope.patientDetails.birthDate = response.data.object.dob,
						$scope.appointmentMasterObj.patientId=$scope.patientDetails.patientId;
						$scope.patientDetails.doctorName=appointmentObj.doctorName;
						$scope.patientDetails.uhIdNumber=appointmentObj.uhidNumber;

						$scope.appointmentMasterObj.specialityId = appointmentObj.specialityId;
						if(appointmentObj.appointmentTypeId == 1)
							{
							$scope.appointmentMasterObj.deptId=appointmentObj.specialityId;
							$scope.getDoctorBySpeciality($scope.appointmentMasterObj.deptId);
							$scope.appointmentMasterObj.encounterTypeId = "1";
							}
						else
							{
							$scope.appointmentMasterObj.deptId=appointmentObj.modalitySpecialityId;
							}
						
						
						$scope.getKinDetailsByPatientId();
						$scope.kinDetailsList[0].patientId=appointmentObj.patientId;
						$scope.appointmentMasterObj.doctorId=appointmentObj.doctorId;
						$scope.appointmentMasterObj.encounterTime=appointmentObj.fromTimeString;
						$scope.appointmentMasterObj.appointmentId=appointmentObj.appointmentId;
						$scope.appointmentMasterObj.isScheduledAppointment='A';
						
					});
				}
				if(appointmentObj.patientId != undefined && appointmentObj.appointmentTypeId == 2){
					 $scope.appointmentMasterObj.systemGeneratedVisitTypeId="4";
					 $scope.appointmentMasterObj.userDefinedVisitTypeId="4";
				}
				if(appointmentObj.patientId != undefined && appointmentObj.appointmentTypeId == 3){
					 $scope.appointmentMasterObj.systemGeneratedVisitTypeId="5";
					 $scope.appointmentMasterObj.userDefinedVisitTypeId="5";
				}
	}
		
	$scope.checkAppointment();
	
	
	$scope.enableDisableDoctor = function(encounterTypeId)
	{
		if(encounterTypeId != 1)
			{
				$scope.enableDisabled  = true;
			}
		else{
				$scope.enableDisabled  = false;
			}
	}
	
	$scope.getLastEncounterDetails=function(patientId){
		
		
		$scope.encounterDetailsObj={
			
				 unitId          :$scope.unitId,
				 organizationId  :$scope.organizationId,
	    		 patientId : $scope.encounterDetailsObj.patientId  ,
	    		 doctorId :  $scope.encounterDetailsObj.doctorId ,
	    		 specialityId :  $scope.encounterDetailsObj.specialityId,
		}
		
		var URI = BASE_URL + ROOT_URL + GETENCOUNTERDETAILS;
		BillingGenericService.serviceAction(METHOD_POST,URI,$scope.encounterDetailsObj, NOTIFICATION_MSG_STATUS_FALSE).then(function(response){
			$scope.encounterList = response.data.listObject;
		});
		
		
	}
	
	
	$scope.getSexMasterList = function(){
		var URI = BASE_URL + ROOT_URL + "/adt/getActiveGenderList";
		BillingGenericService.serviceAction(METHOD_GET,URI, "", NOTIFICATION_MSG_STATUS_FALSE).then(function(response){
			$scope.sexMasterList = response.data.listObject;
		});
	}
	$scope.getSexMasterList();
	
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
		$scope.patientDetails.patientId=($scope.patientList[index].patientId);
		$scope.patientDetails.patientName=($scope.patientList[index].patientName);
		$scope.patientDetails.genderCode=($scope.patientList[index].genderCode);
		$scope.patientDetails.uhIdNumber=($scope.patientList[index].uhIdNumber);
		$scope.patientDetails.birthDate=($scope.patientList[index].birthDate);
		$scope.appointmentMasterObj.patientId = ($scope.patientList[index].patientId);
		$scope.getKinDetailsByPatientId();
		$scope.kinDetailsList[0].patientId=$scope.appointmentMasterObj.patientId;
		$rootScope.stopLoader();
	}

	
	//Idetification List
	$scope.getIdentificationList = function(){
		var URI = BASE_URL + ROOT_URL+ GETACTIVEIDENTIFICATION;
		GenericService.serviceAction("GET", URI, "").then(function(response) {
			$scope.identificationList = [];
			if (response.data.status == "success") {
				$scope.identificationList = response.data.listObject;

			}
		});
	}
	$scope.getIdentificationList();
	// Country List
	$scope.getCountryList = function(){
		var URI = BASE_URL + ROOT_URL + GETACTIVECOUNTRYLIST;
		BillingGenericService.serviceAction("GET", URI, "", NOTIFICATION_MSG_STATUS_FALSE).then(function(response) {
					$scope.countryList = [];
					if (response.data.status == "success") {
						$scope.countryList = response.data.listObject;
					}
				});
	}
	$scope.getCountryList();
	
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
	
	$scope.getVisitTypeMasterList = function(){
		try{
			var URI = BASE_URL + ROOT_URL + VISIT_TYPE_MASTER + UNIT + SLASH + $scope.unitId + ORG + SLASH + $scope.organizationId;
			BillingGenericService.serviceAction(METHOD_GET,URI, "", NOTIFICATION_MSG_STATUS_FALSE).then(function(response){
				console.log(response);
				if(response.data.status == "success")
					$scope.visitTypeMasterList = response.data.listObject;
			});
		}catch (e) {
			console.log("Exception",e.message);
		}
	}
	$scope.getVisitTypeMasterList();

	$scope.getSourceMasterList = function(){
		var URI = BASE_URL + ROOT_URL + GETACTIVEPATIENTSOURCELIST;
		BillingGenericService.serviceAction(METHOD_GET, URI, "", NOTIFICATION_MSG_STATUS_FALSE).then(function(response) {
					$scope.sourceMasterList = [];
					if (response.data.status == "success") {
						$scope.sourceMasterList = response.data.listObject;
					}
				});
	}
	$scope.getSourceMasterList();
	
	$scope.getReferralList = function(){
	var URI = BASE_URL + ROOT_URL + GETACTIVEREFERRALTYPELIST;
	var obj={organizationId : $scope.organizationId};
		BillingGenericService.serviceAction(METHOD_POST, URI, obj).then(function(response) {
				$scope.referralTypeList = [];
				if (response.data.status == "success") {
					$scope.referralTypeList = response.data.listObject;
				}
		});
	};
	$scope.getReferralList();
	
	$scope.getReferralMasterList = function(referralTypeId){
		var URI = BASE_URL + ROOT_URL + "/adt/getReferralListByReferralTypeId";
		var obj=
		      { 
				 organizationId:$scope.organizationId,
				 referralTypeId:referralTypeId
		      };
			BillingGenericService.serviceAction(METHOD_POST, URI, obj).then(function(response) {
					$scope.referralMasterList = [];
					if (response.data.status == "success") {
						$scope.referralMasterList = response.data.listObject;
					}
			});
		};
		

	// get state by country ID
	$scope.getStateByCountry = function(id) {
		var data = {
			countryId : id
		};
		var URI = BASE_URL + ROOT_URL + GETSTATELISTBYCOUNTRYID;
		BillingGenericService.serviceAction(METHOD_POST, URI, data, NOTIFICATION_MSG_STATUS_FALSE).then(function(response) {
							$scope.stateList = [];
							if (response.data.status == "success") {
								$scope.stateList = response.data.listObject;
							}
		});
	}
	
	//get District by State 
	$scope.getDistrictByState = function(id) {
		var data = {
			stateId : id
		};
		var URI = BASE_URL + ROOT_URL + GETDISTRICTLISTBYSTATEID;
		GenericService.serviceAction("POST", URI, data).then(function(response) {	
			$scope.districtList = [];
			if (response.data.status == "success") {
				$scope.districtList = response.data.listObject;
			}
		});

	}
	
	//get City By District
	$scope.getCityByDistrict = function(id) {
		var data = {
			districtId : id
		};
		var URI = BASE_URL + ROOT_URL + GETCITYLISTBYDISTRICTID;
		GenericService.serviceAction("POST", URI, data).then(function(response) {
				$scope.cityList = [];
				if (response.data.status == "success") {
					$scope.cityList = response.data.listObject;
				}
			});

	}
	
	//get Area By City
	$scope.getAreaByCity = function(id) {
		var data = {
			cityId : id
		};
		var URI = BASE_URL + ROOT_URL + GETAREALISTBYCITY;
		GenericService.serviceAction("POST", URI, data).then(function(response) {
			$scope.areaList = [];
			if (response.data.status == "success") {
				$scope.areaList = response.data.listObject;
			}
		});
	}
	
	//Prefix list
	$scope.getPrefixList = function(){
		var URI = BASE_URL + ROOT_URL + GETACTIVEPREFIXLIST;
		BillingGenericService.serviceAction(METHOD_GET, URI, "", NOTIFICATION_MSG_STATUS_FALSE).then(
				function(response) {
					$scope.prefixList = [];
					if (response.data.status == "success") {
						$scope.prefixList = response.data.listObject;
					}
				});
	}
	$scope.getPrefixList();
	
	//get Reason List
	$scope.getReasonList = function(){
		
	var URI = BASE_URL + ROOT_URL
			+ GETACTIVEREASONLIST;

	BillingGenericService.serviceAction(METHOD_GET, URI,$scope.commonObj,NOTIFICATION_MSG_STATUS_FALSE)
				.then(
						function(response) {
							console.log("ReasonList",response);
							// $rootScope.startLoader();
							if (response.data.status == "success")
								$scope.reasonList = response.data.listObject;
							
						});
	}
	$scope.getReasonList();
	
//relation list
	$scope.getRelationshipList = function(){
		var URI = BASE_URL + ROOT_URL + GETACTIVERELATIONLIST;
		BillingGenericService.serviceAction(METHOD_GET, URI, "", NOTIFICATION_MSG_STATUS_FALSE).then(function(response) {
					$scope.relationList = [];
					if (response.data.status == "success") {
						$scope.relationList = response.data.listObject;
					}
				});
	}
	$scope.getRelationshipList();
	
	//http://localhost:8080/HIS-WAR/adt/getDoctorListAganistSpeciality
	$scope.getDoctorBySpeciality = function(id) {

		var data = {
			specialityId : id,
			unitId : $scope.unitId,
			organizationId : $scope.organizationId
		};
		var URI = BASE_URL + ROOT_URL + GETDOCTORBYSPECIALITYID;
		BillingGenericService.serviceAction(METHOD_POST, URI, data,NOTIFICATION_MSG_STATUS_FALSE).then(function(response) {
							console.log(response);
							$scope.doctorList = [];
							// $rootScope.startLoader();
							if (response.data.status == "success")
								$scope.doctorList = response.data.listObject;
		});

	}

	
	//kin details
	$scope.getKinDetailsByPatientId = function(){
		var URI = BASE_URL + ROOT_URL + KIN_DETAILS + PATIENT + SLASH + $scope.appointmentMasterObj.patientId  + UNIT + SLASH +  $scope.unitId  + ORG + SLASH + $scope.organizationId;
		BillingGenericService.serviceAction(METHOD_GET, URI, "", NOTIFICATION_MSG_STATUS_FALSE).then(function(response) {
			if (response.data.status == "success") {
				$scope.kinsList = response.data.listObject;
			}
		});
	}
	
	
	$scope.getReferralTypeDesc=function(referral){
		
		$scope.referralDesc=referral.desc;
		
	}
	
	$scope.saveAppointmentMaster = function(){
		if($scope.appointmentMasterObj.kinDetailsId == "" && $scope.kinDetailsList[0].kinName == ""){
			growl.warning("Please add kin Details",{
				title : WARNING_MSG
			});
			return;
		}
		$rootScope.startLoader();
		//----------------------------------!@#$%^&*(
		var objForGettingDoctorServiceId = {
				doctorId 		: $scope.appointmentMasterObj.doctorId,
				specialityId 	: $scope.appointmentMasterObj.deptId,
				unitId 			: $scope.unitId,
				organizationId 	: $scope.organizationId
			};
		
		
		
		
		var URI = BASE_URL + ROOT_URL + "/api/opd/encounter";
		console.log('Encounter obj',$scope.appointmentMasterObj);
		
		BillingGenericService.serviceAction(METHOD_POST, URI, $scope.appointmentMasterObj, NOTIFICATION_MSG_STATUS_TRUE).then(function(response) {
					if (response.data.status == "success") {
						//$state.go('opdBilling',{patientId:$scope.appointmentMasterObj.patientId, encounterId :response.data.object.encounterId});
						var encounterObj={
								appointmentId : $scope.appointmentMasterObj.appointmentId,
								specialityId : $scope.appointmentMasterObj.deptId,
								patientId : $scope.appointmentMasterObj.patientId,
								encounterId :response.data.object.encounterId,
								visitTypeId:response.data.object.visitTypeId,
								doctorId:response.data.object.doctorId,
								doctorName:$scope.encounterDetailsObj.doctorName,
								specialityName: $scope.encounterDetailsObj.specialityName,
								paymentEntitlementTypeId:$scope.appointmentMasterObj.paymentEntitlementTypeId,
								referralId : $scope.appointmentMasterObj.referralId,
								referralDesc : $scope.referralDesc
								
						}
						/*$state.go("opdBilling", {
							encounterObj: encounterObj,
						});*/
						$state.go("newOPDBilling");
						$scope.initPatientSearchObj();
						$scope.initAppointmentMasterObj();
						$scope.initKinDetails();
					}
				});
	}
	
	$scope.getKinDetailsById = function(kinDetailsId){
		$rootScope.startLoader();
		var URI = BASE_URL + ROOT_URL + KIN_DETAILS + SLASH + kinDetailsId;
		BillingGenericService.serviceAction(METHOD_GET, URI, "", NOTIFICATION_MSG_STATUS_FALSE).then(function(response) {
					if (response.data.status == "success") {
						$scope.kinDetailsList[0]=(angular.copy(response.data.object));
						$scope.getStateByCountry($scope.kinDetailsList[0].countryId);
						$scope.getDistrictByState($scope.kinDetailsList[0].stateId);
						$scope.getCityByDistrict($scope.kinDetailsList[0].districtId);
						$scope.getAreaByCity($scope.kinDetailsList[0].cityId);
						console.log($scope.kinDetailsList);
					}
				});
	}
	
	
	$scope.updateKinDetailsStatus = function(kinDetailsId){
		$rootScope.startLoader();
		var URI = BASE_URL + ROOT_URL + KIN_DETAILS + SLASH + kinDetailsId + STATUS + SLASH + 'I';
		BillingGenericService.serviceAction(METHOD_PUT, URI, "", NOTIFICATION_MSG_STATUS_FALSE).then(function(response) {
			if (response.data.status == "success") {
				$scope.getKinDetailsByPatientId();
			}
		});
	}
	
	$scope.setKinId = function(kinId){
		$scope.appointmentMasterObj.kinDetailsId = kinId;
	}
	
	/*$scope.getReferralType = function(){
		$rootScope.startLoader();
		var URI = BASE_URL + ROOT_URL + GETACTIVEREFERRALTYPELIST;
		var obj={organizationId : $scope.organizationId};
		BillingGenericService.serviceAction(METHOD_POST, URI, obj, NOTIFICATION_MSG_STATUS_FALSE).then(function(response) {
			$scope.referralTypeList = [];
			if (response.data.status == "success") {
				$scope.referralTypeList = response.data.listObject;
			}
		});
	}
	$scope.getReferralType();*/
	
	
	
	//======================= Payment Entitlement ==========================//
	$scope.getDocumentType = function(){
		$rootScope.startLoader();
		var URI = BASE_URL + ROOT_URL + GETACTIVEDOCUMENTTYPELIST;
		BillingGenericService.serviceAction(METHOD_GET, URI, "", NOTIFICATION_MSG_STATUS_FALSE).then(function(response) {
				$scope.documentTypeList = [];
				if (response.data.status == "success") {
					$scope.documentTypeList = response.data.listObject;
				}
			});
	}
	$scope.getDocumentType();
	
	$scope.getCompanyMasterList = function(){
		$rootScope.startLoader();
		var URI = BASE_URL + ROOT_URL + COMPANY_MASTER + UNIT + SLASH + $scope.unitId + ORG + SLASH + $scope.organizationId;
		BillingGenericService.serviceAction(METHOD_GET, URI, "", NOTIFICATION_MSG_STATUS_FALSE).then(function(response) {
				$scope.companyMasterList = [];
				$scope.associatedCompanyList = [];
				if (response.data.status == "success") {
					$scope.companyMasterList = response.data.listObject;
				}
			});
	}
	$scope.getCompanyMasterList();
	
	$scope.getAssociatedCompanyMasterListByCompanyId = function(companyId){
		$rootScope.startLoader();
		var URI = BASE_URL + ROOT_URL + ASSOCIATED_COMPANY_MASTER + "/companyId/" + companyId + UNIT + SLASH + $scope.unitId + ORG + SLASH + $scope.organizationId;
		BillingGenericService.serviceAction(METHOD_GET, URI, "", NOTIFICATION_MSG_STATUS_FALSE).then(function(response) {
				if (response.data.status == "success") {
					$scope.associatedCompanyList = response.data.listObject;
				}
			});
	}
	
	
	
}]);