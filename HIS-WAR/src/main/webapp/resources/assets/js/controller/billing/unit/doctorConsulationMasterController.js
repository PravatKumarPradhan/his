/**
 * @author Kaustubh
 * @date 03-04-2018
 * 		Controller for doctorConsulationMaster unit level
 */
angular.module("myApp").controller("doctorConsulationMasterController", [ '$scope','$rootScope','BillingGenericService','GenericService','$sessionStorage','PagerService','promiseFactory','$cookies','$state','promiseFactoryWithObject','growl',
	function($scope,$rootScope,BillingGenericService,GenericService,$sessionStorage,PagerService,promiseFactory,$cookies,$state,promiseFactoryWithObject,growl){
	
	
	
	$scope.init = function(){

		var cookieObject = $cookies.getObject('cookieObject');
		if(cookieObject == undefined){
			$state.go('login');
			return;
		}
	$rootScope.loginpage = true;
	$scope.unitId = cookieObject.unitId;
	$scope.organizationId = cookieObject.organizationId;
	$scope.specialityMasterList=[];
	$scope.servicesList =[];
	
	/**Variables for pagination */
	$scope.offset = 0;
	$scope.noOfRecordsPerPage = 10;
	$scope.unitServiceMapperList = [];
	$scope.totalNoOfRecords;
	$scope.searchKeyword;
	$scope.commonListCount;
	$scope.pager = {};
	$scope.page;
	$scope.userId = 1;
	$scope.currentDate = moment(new Date()).format('DD-MM-YYYY HH:mm:ss');
	
	$scope.saveBtnFlag = true;
	$scope.updateBtnFlag = false;
	$scope.disableField=false;
	
	$scope.doctorConsultationObj={
			specialityId : 0,
			unitId : cookieObject.unitId,
			organizationId : cookieObject.organizationId,
			doctorId : 0,
			newVisitServiceId : 0,
			followupVisitServiceId : 0,
			secondaryVisitServiceId : 0
	}
	
	
	 // for popup
    $scope.showModal = false;
    $scope.buttonClicked = "";
    $scope.popUpFlag = true;
    // end for popup
    
    
		try{
		    var URI = BASE_URL + ROOT_URL + API + BILLING + UNIT + GETDOCTORCONSULTATIONSERVICEMAPPERLIST;
			var obj = {
						organizationId :$scope.organizationId,
						unitId         :$scope.unitId
				    }
			BillingGenericService.serviceAction(METHOD_POST,URI, obj, NOTIFICATION_MSG_STATUS_FALSE).then(function(response){
				console.log(response);
				if(response.data.status == "success")
					$scope.doctorConsultationList = response.data.listObject;
			});
		}catch (e) {
			console.log("Exception",e.message);
		}
	}
	//-----Get doctorConsultationList-----//
    
	$scope.init();
    
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
	
	//------------ Get Doctors By SpecialityId ----------//
	$scope.getDoctorsBySpecialityId = function(specialityId){
		$rootScope.startLoader();
		//$scope.unitService.subSpecialityId="";
		$scope.doctorList=[];
		  var URI = BASE_URL + ROOT_URL + GETDOCTORBYSPECIALITYID;
		  var obj = {
					organizationId : $scope.organizationId,
					unitId         : $scope.unitId,
					specialityId   : specialityId
			    }
		BillingGenericService.serviceAction(METHOD_POST,URI, obj, NOTIFICATION_MSG_STATUS_FALSE).then(function(response){
			if(response.data.status == "success")
				$scope.doctorList = response.data.listObject;
		});
	}
	
	
	
	
	//------------- Get All Services by Speciality and sub-speciality id=1000(Consultation) ---------------//
	$scope.getServicesBySpecialityId = function(){
		try{
			$rootScope.startLoader();
			var URI = BASE_URL + ROOT_URL + BILLING + ORGANIZATION_MASTER + SPECIALITY + SLASH + 1000 + SUB_SPECIALITY + SLASH + 1000 + ORG + SLASH + $scope.organizationId;
			BillingGenericService.serviceAction(METHOD_GET,URI, "", NOTIFICATION_MSG_STATUS_FALSE).then(function(response){
				if(response.data.status == "success")
					$scope.servicesList = response.data.listObject;
			});
		}catch (e) {
			console.log("Exception",e.message);
		}
	}
	$scope.getServicesBySpecialityId();
	
	// function for popup ok button call
    $scope.PopupOkBtn = function() {
     $scope.popUpFlag = false;
     $scope.saveDoctorCosultationMaper();
    }
	
	$scope.saveDoctorCosultationMaper= function (){
		try{
			if ($scope.popUpFlag) {
		         $scope.showModal = !$scope.showModal;

		        } else {
				/*$rootScope.startLoader();*/
				var URI = BASE_URL + ROOT_URL + API + BILLING + UNIT + SAVEDOCTORCONSULTATIONSERVICEMAPPER;
				$scope.doctorConsultationObj.createdDate=$scope.currentDate;
				$scope.doctorConsultationObj.updatedDate=$scope.currentDate;
				$scope.doctorConsultationObj.createdBy=$scope.userId;	
				$scope.doctorConsultationObj.updatedBy=$scope.userId;	
				$scope.doctorConsultationObj.status='A';
				console.log($scope.doctorConsultationObj);
				//return false;
				BillingGenericService.serviceAction(METHOD_POST,URI, $scope.doctorConsultationObj, NOTIFICATION_MSG_STATUS_TRUE).then(function(response){
					$scope.init();
				});
		       }
	}catch (e) {
		console.log("Exception",e.message);
	}

	}
	
	
	// getDetailsById
	$scope.getDetailsById = function(id) {

		$scope.saveBtnFlag = false;
		$scope.updateBtnFlag = true;
		$scope.disableField=true;
		

		var data = {

				doctorConsultationServiceMapperId : id
		}
		console.log(data);
		var URI = BASE_URL + ROOT_URL + API + BILLING + UNIT
				+ GETDOCTORCONSULTATIONSERVICEMAPPERBYID;
		
		BillingGenericService.serviceAction(METHOD_POST,URI, data, NOTIFICATION_MSG_STATUS_FALSE).then(function(response){
			
			if (response.data.status == "success") {
				
				$scope.doctorConsultationObj.doctorConsultationServiceMapperId = response.data.listObject[0].doctorConsultationServiceMapperId;
				$scope.doctorConsultationObj.specialityId = response.data.listObject[0].specialityId.toString();
				$scope.getDoctorsBySpecialityId($scope.doctorConsultationObj.specialityId);
				$scope.doctorConsultationObj.doctorId = response.data.listObject[0].doctorId.toString();
				$scope.doctorConsultationObj.newVisitServiceId = response.data.listObject[0].newVisitServiceId.toString();
				$scope.doctorConsultationObj.followupVisitServiceId = response.data.listObject[0].followupVisitServiceId.toString();
				$scope.doctorConsultationObj.secondaryVisitServiceId = response.data.listObject[0].secondaryVisitServiceId.toString();

			}
			
		});
	}
	
		
		// updateDoctorCosultation
		$scope.updateDoctorCosultationMaper = function() {

		
			$scope.doctorConsultationObj.updatedDate=$scope.currentDate;
			$scope.doctorConsultationObj.updatedBy=$scope.userId;
			var URI = BASE_URL + ROOT_URL + API + BILLING + UNIT
					+ UPDATEDOCTORCONSULTATIONSERVICEMAPPER;
			
			BillingGenericService.serviceAction(METHOD_POST,URI, $scope.doctorConsultationObj, NOTIFICATION_MSG_STATUS_TRUE).then(function(response){
				if (response.data.status == "success") {
					$scope.init();
				}
				
			});
		}
		
		
	
	
	
}]);