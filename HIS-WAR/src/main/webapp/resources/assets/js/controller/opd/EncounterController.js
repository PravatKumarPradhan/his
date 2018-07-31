/**
 * @author Nikhil
 * @date 27-12-2017
 * 		Controller for Encounter Creation
 */
angular.module("myApp").controller("EncounterController", [ '$scope','$rootScope','BillingGenericService','GenericService','$sessionStorage','PagerService','promiseFactory','$cookies','$state','promiseFactoryWithObject','growl',
	function($scope,$rootScope,BillingGenericService,GenericService,$sessionStorage,PagerService,promiseFactory,$cookies,$state,promiseFactoryWithObject,growl){
	var cookieObject = $cookies.getObject('cookieObject');
	if(cookieObject == undefined){
		$state.go('login');
		return;
	}
	$rootScope.loginpage = true;
	$scope.unitId = cookieObject.unitId;
	$scope.organizationId = cookieObject.organizationId;
	$scope.sexMasterList=[];
	$scope.userId = 1;
	
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
		$scope.patientDetails = {
				"patientName" : "",
				"genderCode"  : "",
				"age"         : "", 
				"uhIdNumber"  : ""
		}
	}
	$scope.initPatientSearchObj();
	
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
			organizationId  : $scope.organizationId
		};
	
	$scope.kinDetailsList.push(angular.copy($scope.kinDetails));
  
    $scope.addNewKinDetails = function(){
    	$scope.kinDetails.patientId = $scope.appointmentMasterObj.patientId;
    	$scope.kinDetailsList.push(angular.copy($scope.kinDetails));
    };
    $scope.removeKinDetails = function(indexToBeDeleted){	
		  $scope.kinDetailsList.splice(indexToBeDeleted, 1);
    };
    
    
	
	$scope.initAppointmentMasterObj = function(){
		$scope.appointmentMasterObj = {
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
		}
	}
	$scope.initAppointmentMasterObj();

	$scope.getSexMasterList = function(){
		$rootScope.startLoader();
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
	
	//kin details
	$scope.getKinDetailsByPatientId = function(){
		var URI = BASE_URL + ROOT_URL + KIN_DETAILS + PATIENT + SLASH + $scope.appointmentMasterObj.patientId  + UNIT + SLASH +  $scope.unitId  + ORG + SLASH + $scope.organizationId;
		BillingGenericService.serviceAction(METHOD_GET, URI, "", NOTIFICATION_MSG_STATUS_FALSE).then(function(response) {
			if (response.data.status == "success") {
				$scope.kinsList = response.data.listObject;
			}
		});
	}
	
	$scope.saveAppointmentMaster = function(){
		$rootScope.startLoader();
		var URI = BASE_URL + ROOT_URL + SAVE_APPOINTMENT_MASTER;
		BillingGenericService.serviceAction(METHOD_POST, URI, $scope.appointmentMasterObj, NOTIFICATION_MSG_STATUS_TRUE).then(function(response) {
					if (response.data.status == "success") {
						$scope.initPatientSearchObj();
						$scope.initAppointmentMasterObj();
					}
				});
	}
	
	$scope.getKinDetailsById = function(kinDetailsId){
		$rootScope.startLoader();
		var URI = BASE_URL + ROOT_URL + KIN_DETAILS + SLASH + kinDetailsId;
		BillingGenericService.serviceAction(METHOD_GET, URI, "", NOTIFICATION_MSG_STATUS_FALSE).then(function(response) {
					if (response.data.status == "success") {
						
					}
				});
	}
	
	
	$scope.updateKinDetailsStatus = function(kinDetailsId){
		$rootScope.startLoader();
		var URI = BASE_URL + ROOT_URL + KIN_DETAILS + SLASH + kinDetailsId + SLASH + kinDetailsId + STATUS + 'I';
		BillingGenericService.serviceAction(METHOD_PUT, URI, "", NOTIFICATION_MSG_STATUS_FALSE).then(function(response) {
					if (response.data.status == "success") {
						$scope.getKinDetailsByPatientId();
					}
				});
	}
	
	
  
    
}]);