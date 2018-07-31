angular.module("myApp").controller("addNewGlobalDoctorShareController", [ '$scope','$rootScope','BillingGenericService','$cookies','$state','growl','servicesFactory',
	function($scope,$rootScope,BillingGenericService,$cookies,$state,growl,servicesFactory){
	try{
		$rootScope.loginpage = true;
		
		var cookieObject = $cookies.getObject('cookieObject');
		if (cookieObject == undefined) {
			$state.go('login');
			return;
		}
		$scope.unitId = cookieObject.unitId;
		$scope.organizationId = cookieObject.organizationId;
		$scope.userId = 1;
		 $scope.multiselectObj = {
		    		"id":"",
		    		"label":""
		    }
		    
		 $scope.initSubSpeciality = function(){
			 $scope.subSpeciality = {subSpecialityName:"",subSpecialityId:""};
		}
		$scope.initSpeciality = function(){
			 $scope.speciality = {specialityName:"",specialityId:""};
		}
		 
		$scope.init = function(){
			$scope.selectedServiceList = [];
			servicesFactory.setScope();
			$scope.setOrgUnit($scope.organizationId,$scope.unitId);
			servicesFactory.initServiceSearch();
		    $scope.searchServObj = {
					isDoctorRequired:"A"
			};
			$scope.bedBillingCategoryList=[];
			$scope.selectedBedBillingCategoryList=[];
			$scope.patientTypeList=[];
			$scope.selectedpatientTypeList=[];
			$scope.specialityList = [];
			$scope.initSpeciality();
			$scope.initSubSpeciality();
			$scope.groupDetailsList = [];
			$scope.addNewDoctorWiseShareList=[];
			$scope.addServicesNewDoctorWiseShareList=[];
			$scope.doctorGradeList=[];
			
		}
		
		
		$scope.init();
		
	   $scope.firstTabLevel = 1 ;
		
		$scope.setFirstTabLevel= function(newTab)
		{
			$scope.firstTabLevel = newTab;
		};
		$scope.isSetFirstTabLevel = function(tabNum)
		{
			return $scope.firstTabLevel === tabNum;
		};
		
		
		//========================= get SpecialityList=====================
		var commonObj = {
			unitId : $scope.unitId,
			organizationId : $scope.organizationId
		}

		var URI = BASE_URL + ROOT_URL
				+ GETACTIVESPECIALITY;
	
		BillingGenericService
				.serviceAction(METHOD_POST, URI,
						commonObj,
						NOTIFICATION_MSG_STATUS_FALSE)
				.then(
						function(response) {
							console.log(response);
							// $rootScope.startLoader();
							if (response.data.status == "success")
								$scope.specialityList = response.data.listObject;
						});
		
		//========================= get Doctor By Speciality=====================
		$scope.getDoctorBySpeciality = function(id) {

			var data = {
				specialityId : id,
				unitId : $scope.unitId,
				organizationId : $scope.organizationId
			};
			var URI = BASE_URL + ROOT_URL
					+ GETDOCTORBYSPECIALITYID;
			
			BillingGenericService
					.serviceAction(METHOD_POST, URI, data,
							NOTIFICATION_MSG_STATUS_FALSE)
					.then(
							function(response) {
								console.log(response);
								$scope.doctorList = [];
								// $rootScope.startLoader();
								if (response.data.status == "success")
									$scope.doctorList = response.data.listObject;
							});

		}
		
		/*getgetBed Billing Category List*/
		$scope.getBedBillingCategoryList = function()
		{
			try 
			{
				 data="";
				 var URI = BASE_URL + ROOT_URL + "/adt/getActiveBillingBedCategoryList/orgId/"+$scope.organizationId+"/unitId/"+$scope.unitId;
				  console.log("URI", URI);
				  BillingGenericService.serviceAction("GET", URI, data,NOTIFICATION_MSG_STATUS_FALSE)
				.then(
						function(response) {
							console.log("response.data.listObject",response.data.listObject);
							angular.forEach(response.data.listObject, function(value, key) {
								var multiselectObj = angular.copy($scope.multiselectObj);
								multiselectObj.id = value.billingBedCategoryId;
								multiselectObj.label = 	value.billingBedDesc;
									$scope.bedBillingCategoryList.push(multiselectObj);
								});
						});
				  
			} catch (e) {
				console.log(e.message)
			}
		}
		
		$scope.getBedBillingCategoryList();
		
		
	
		

		/*get Patient Type List*/
		$scope.getPatientTypeList = function()
		{
			try 
			{
				var data = "";
				var URI = BASE_URL + ROOT_URL + "/Unit/Service/EHCpackage/getPatientTypeByTariffId/"+$scope.organizationId+"/"+$scope.unitId+"/"+17;
				  console.log("URI", URI);
				  BillingGenericService.serviceAction("GET", URI, data,NOTIFICATION_MSG_STATUS_FALSE)
				.then(
						function(response) {
							console.log("response.data.listObject",response.data.listObject);
							angular.forEach(response.data.listObject, function(value, key) {
								var multiselectObj = angular.copy($scope.multiselectObj);
								multiselectObj.id = value.id;
								multiselectObj.label = value.name;
									$scope.patientTypeList.push(multiselectObj);
								});
						});
				  
			} catch (e) {
				console.log(e.message)
			}
		}
		
		$scope.getPatientTypeList();
		
		
		  
	}catch(e){
		console.log(e.message);
	}
	

	//get Doctor Grade List
	$scope.getDoctorGradeList = function() {
		var URI = BASE_URL + ROOT_URL + "/unit/DoctorShare/getDoctorGradeMaster/"+$scope.orgId+"/"+$scope.unitId;
		BillingGenericService.serviceAction("GET", URI, null).then(function(response){
			$scope.doctorGradeList= response.data.listObject;
		});
   }
	$scope.getDoctorGradeList();

	
	
	 //get speciality list for autocomplete
	  $scope.getSpecialityList = function(){
		  var URI = BASE_URL + ROOT_URL + "/adt/getActiveSpecialityList";
		  var param = {organizationId:$scope.orgId,unitId:$scope.unitId};
		    BillingGenericService.serviceAction("POST", URI, param).then(function(response){
		    	if(response.data.status == "success")
		    		$scope.specialityList = response.data.listObject;
		    	 console.log("specialityList",JSON.stringify($scope.specialityList));
		    });
	  }
	  $scope.getSpecialityList();

	  $scope.selectSpeciality = function($item, $model,$label)
	  {
	       $scope.speciality.specialityId = $item.specialityId;
	       if($item.specialityId != ""){
	    	   $scope.initSubSpeciality();
	    	   $scope.subSpecialityList = [];
	       var URI = BASE_URL + ROOT_URL + "/adt/subspeciality/"+$item.specialityId;
			BillingGenericService.serviceAction(METHOD_GET,URI, null,NOTIFICATION_MSG_STATUS_FALSE).then(function(response) {
				if (response.data.status == "success")
					$scope.subSpecialityList = response.data.listObject;
			});
	       }
	  }
	  
	  $scope.selectSubSpeciality = function($item, $model,$label){
		  $scope.subSpeciality.subSpecialityId = $item.subSpecialityMasterId;
	  }

	  $scope.bedBillingCategoryName = function(id){
			for(var i = 0; i < $scope.bedBillingCategoryList.length ; i++){
				if($scope.bedBillingCategoryList[i].id == id)
					return $scope.bedBillingCategoryList[i].label;
			}
	   }
	  
		  
	  
	  $scope.patientTypeName = function(id){
			for(var i = 0; i < $scope.patientTypeList.length ; i++){
				if($scope.patientTypeList[i].id == id)
					return $scope.patientTypeList[i].label;
			}
	   }
	  
	  $scope.departmentName = function(id){
			for(var i = 0; i < $scope.specialityList.length ; i++){
				if($scope.specialityList[i].specialityId == id)
					return $scope.specialityList[i].specialityName;
				
			}
	   }
	  
	  $scope.doctorName = function(id){
			for(var i = 0; i < $scope.doctorList.length ; i++){
				if($scope.doctorList[i].doctorId == id)
					return $scope.doctorList[i].firstName+' '+$scope.doctorList[i].lastName;
			}
	   }
	  
	  
	  $scope.doctorGrade = function(id){
			for(var i = 0; i < $scope.doctorGradeList.length ; i++){
				if($scope.doctorGradeList[i].id == id)
					return $scope.doctorGradeList[i].name;
			}
	   }
  
	  
	 
	
	

	  $scope.addCAPAmountDetails = function(){
		  
		  $scope.tempPatientTypeList=[];
		  $scope.tempBedBillingCategoryList=[];
		  
		  if($scope.selectedpatientTypeList.length < 1 || $scope.selectedpatientTypeList == undefined){
			  $scope.tempPatientTypeList=[0];
			}
		 
		  if($scope.selectedBedBillingCategoryList.length < 1 || $scope.selectedBedBillingCategoryList == undefined){
			  $scope.tempBedBillingCategoryList=[0];
			}
		 
		  
		
		  angular.forEach($scope.selectedpatientTypeList, function(value, key)
					{ 
			  			$scope.tempPatientTypeList.push(value.id);
			  		  
					});
		  
		
		  angular.forEach($scope.selectedBedBillingCategoryList, function(value, key)
					{ 
			  			$scope.tempBedBillingCategoryList.push(value.id);
			  		  
					});
		  
		
				  
			if($scope.speciality.specialityId == '' || typeof($scope.speciality.specialityId) == "undefined"){
				growl.error("Please select a group",{
					title : ERROR_MSG
				});
				return;
			}
			var temp = {departmentId:$scope.speciality.specialityId, subDepartmentId:$scope.subSpeciality.subSpecialityId,
						groupDesc:$scope.speciality.specialityName,subGroupDesc:$scope.subSpeciality.subSpecialityName,
						share:$scope.share,effectiveDate:$scope.effectiveDate,};
			$scope.groupDesc=$scope.speciality.specialityName;
			$scope.subGroupDesc=$scope.subSpeciality.subSpecialityName;
			$scope.departmentId=$scope.speciality.specialityId;
			$scope.subDepartmentId=$scope.subSpeciality.subSpecialityId,
			$scope.groupDetailsList.push(temp);
			$scope.initSubSpeciality();
			$scope.initSpeciality();
			
			$scope.addNewDoctorWiseShareList=[];
			arra=cartesian($scope.tempPatientTypeList,$scope.tempBedBillingCategoryList);
			
			for(i=0;i<arra.length;i++){
				
				var tempObj={departmentId:"",departmentName:"",
						doctorGradeId:"",doctorName:"",
						patientTypeId:"",patientName:"",
						bedBillingCategoryId:"",bedBillingCategoryName:"",departmentId:"", subDepartmentId:"",
						groupDesc:"",subGroupDesc:"",
						share:"",effectiveDate:""};
				
				var tempArr=arra[i];
				$scope.patientTypeId =tempArr[0];
				$scope.bedBillingCategoryId =tempArr[1];
				
				var newTempObj = angular.copy(tempObj);
				
				newTempObj.departmentId=$scope.departmentId;
				newTempObj.departmentName=$scope.departmentName($scope.admissionRequest.specialityId);
				newTempObj.doctorGradeId=$scope.admissionRequest.doctorGradeId;
				newTempObj.doctorName=$scope.doctorGrade($scope.admissionRequest.doctorGradeId);
				newTempObj.patientTypeId=$scope.patientTypeId;
				newTempObj.patientName=$scope.patientTypeName($scope.patientTypeId);
				newTempObj.bedBillingCategoryId=$scope.bedBillingCategoryId;
				newTempObj.bedBillingCategoryName=$scope.bedBillingCategoryName($scope.bedBillingCategoryId);
				newTempObj.departmentId=$scope.departmentId;
				newTempObj.subDepartmentId=$scope.subDepartmentId; 
				newTempObj.groupDesc=$scope.groupDesc;
				newTempObj.subGroupDesc=$scope.subGroupDesc;
				newTempObj.share=$scope.share;
				newTempObj.effectiveDate=$scope.effectiveDate;
				$scope.addNewDoctorWiseShareList.push(newTempObj);
				
			}
			console.log("addNewDoctorWiseShareList",JSON.stringify($scope.addNewDoctorWiseShareList));
			
		}
	  $scope.removeNewDoctorWiseShareList = function(index,obj){
			 $scope.addNewDoctorWiseShareList.splice(index,1);
		
		}
	
	  //========================== Filter get Service Name =========================== 
	  $scope.serviceName = function(id){
			for(var i = 0; i < $scope.selectedServiceList.length ; i++){
				if($scope.selectedServiceList[i].serviceMasterId == id)
				return $scope.selectedServiceList[i].serviceDescription;
			}
	   }
	  
	  $scope.groupName = function(id){
			for(var i = 0; i < $scope.selectedServiceList.length ; i++){
				if($scope.selectedServiceList[i].serviceMasterId == id)
				return $scope.selectedServiceList[i].groupDesc;
			}
	   }
	  
	  $scope.subGroupName = function(id){
			for(var i = 0; i < $scope.selectedServiceList.length ; i++){
				if($scope.selectedServiceList[i].serviceMasterId == id)
				return $scope.selectedServiceList[i].subGroup;
			}
	   }

	//========================== add Selected Services =========================== 
	  $scope.addSelectedServicesDetails = function(){
		  
		  if($scope.selectedServiceList.length < 1 || $scope.selectedServiceList == undefined){
				growl.error("Please select a service",{
					title : ERROR_MSG
				});
				return;
			}
		  
		      $scope.servicesTempPatientTypeList=[];
		     $scope.servicesTempBedBillingCategoryList=[];
			  $scope.servicesTempServiceMasterIdList=[];
			  if($scope.selectedpatientTypeList.length < 1 || $scope.selectedpatientTypeList == undefined){
			  $scope.servicesTempPatientTypeList=[0];
			}
		 
		  if($scope.selectedBedBillingCategoryList.length < 1 || $scope.selectedBedBillingCategoryList == undefined){
			  $scope.servicesTempBedBillingCategoryList=[0];
			}
		  if($scope.selectedServiceList.length < 1 || $scope.selectedServiceList == undefined){
			  $scope.servicesTempServiceMasterIdList=[0];
			}
		 
		  angular.forEach($scope.selectedpatientTypeList, function(value, key)
					{ 
			  			$scope.servicesTempPatientTypeList.push(value.id);
			  		  
					});
		  
		
		  angular.forEach($scope.selectedBedBillingCategoryList, function(value, key)
					{ 
			  			$scope.servicesTempBedBillingCategoryList.push(value.id);
			  		  
					});
		  
		
		  
		  angular.forEach($scope.selectedServiceList, function(value, key)
					{ 
			  			$scope.servicesTempServiceMasterIdList.push(value.serviceMasterId);
			  		  
					});
		  

		  
			var newTemp = {departmentId:$scope.speciality.specialityId, subDepartmentId:$scope.subSpeciality.subSpecialityId,
						groupDesc:$scope.speciality.specialityName,subGroupDesc:$scope.subSpeciality.subSpecialityName,
						share:$scope.share,effectiveDate:$scope.effectiveDate,};
			$scope.groupDesc=$scope.speciality.specialityName;
			$scope.subGroupDesc=$scope.subSpeciality.subSpecialityName;
			$scope.departmentId=$scope.speciality.specialityId;
			$scope.subDepartmentId=$scope.subSpeciality.subSpecialityId,
			$scope.groupDetailsList.push(newTemp);
			
			$scope.addServicesNewDoctorWiseShareList=[];
			serviceArra=cartesian($scope.servicesTempPatientTypeList,
					$scope.servicesTempBedBillingCategoryList,$scope.servicesTempServiceMasterIdList);
			console.log("cartesian :: " + serviceArra.length);
			var tempArray = [];
			for(i=0;i<serviceArra.length;i++){
				
				var serviceTempObj={departmentId:"",departmentName:"",
						doctorGradeId:"",doctorName:"",
						patientTypeId:"",patientName:"",bedBillingCategoryId:"",bedBillingCategoryName:"",departmentId:"", subDepartmentId:"",
						groupDesc:"",subGroupDesc:"",
						share:"",effectiveDate:"",servicesMstId:"",serviceName:""};
				
				var tempArr=serviceArra[i];
				$scope.patientTypeId =tempArr[0];
				$scope.bedBillingCategoryId =tempArr[1];
				$scope.servicesMstId =tempArr[2];
				
				var serviceNewTempObj = angular.copy(serviceTempObj);
				
				serviceNewTempObj.departmentId=$scope.departmentId;
				serviceNewTempObj.departmentName=$scope.departmentName($scope.admissionRequest.specialityId);
				serviceNewTempObj.doctorGradeId=$scope.admissionRequest.doctorGradeId;
				serviceNewTempObj.doctorName=$scope.doctorGrade($scope.admissionRequest.doctorGradeId);
				serviceNewTempObj.patientTypeId=$scope.patientTypeId;
				serviceNewTempObj.patientName=$scope.patientTypeName($scope.patientTypeId);
				serviceNewTempObj.bedBillingCategoryId=$scope.bedBillingCategoryId;
				serviceNewTempObj.bedBillingCategoryName=$scope.bedBillingCategoryName($scope.bedBillingCategoryId);
				serviceNewTempObj.groupDesc=$scope.groupName($scope.servicesMstId);
				serviceNewTempObj.subGroupDesc=$scope.subGroupName($scope.servicesMstId);
				serviceNewTempObj.share=$scope.share;
				serviceNewTempObj.effectiveDate=$scope.effectiveDate;
				serviceNewTempObj.servicesMstId=$scope.servicesMstId;
				serviceNewTempObj.serviceName=$scope.serviceName($scope.servicesMstId);
				tempArray.push(serviceNewTempObj);
				if((i+1)%10 == 0){
					$scope.addServicesNewDoctorWiseShareList = $scope.addServicesNewDoctorWiseShareList.concat(tempArray);
					tempArray = [];
				}else if(i == (serviceArra.length - 1)){
					$scope.addServicesNewDoctorWiseShareList = $scope.addServicesNewDoctorWiseShareList.concat(tempArray);
				}
			}
		}
	  $scope.removeServicesNewDoctorWiseShareList = function(index,obj){
			 $scope.addServicesNewDoctorWiseShareList.splice(index,1);
		
		}
	
	  
	  
	  //=============================== ADD SERVICES =======================
	  
		$scope.addServices = function(){
			$scope.selectedServiceList = [];
			/*$scope.notSelectedServiceList = [];*/
				var groupIds = [];
				angular.forEach($scope.groupDetailsList,function(value,index){
					groupIds.push(value.departmentId);
				});
			angular.forEach($scope.searchServiceList,function(value,key){
				if(value.isSelected){
					$scope.selectedServiceList.push(value);
					//console.log("selectedServiceList",JSON.stringify($scope.selectedServiceList));
					
				}else{
					/*if(groupIds.indexOf(value.specialityId) == -1)
						$scope.notSelectedServiceList.push(value);
					*/
				}
			});
		}
		
		$scope.removeSelectedService = function(index){
			$scope.selectedServiceList.splice(index,1);
		}
	  
	  
	  
		$scope.saveGlobalDocShareGroupWiseDetails = function(){
			
			// Doctor Share Group Wise Details
			var listTGlobalDocShareGroupWiseDto = [];
			angular.forEach($scope.addNewDoctorWiseShareList,function(value,index){
				var temp = {
						
						specialityId :value.departmentId,
						orgId:value.orgId,
						unitId :value.unitId,
						departmentId: value.departmentId,
						subDepartmentId:value.subDepartmentId,
						docShare:value.share,
						status:"A",
						gradeId:value.doctorGradeId,
						effectiveDate:value.effectiveDate,
						billingBedCategoryId:value.bedBillingCategoryId,
						patientTypeId:value.patientTypeId,
						createdBy:$scope.userId,
						updatedBy:$scope.userId
					};
				listTGlobalDocShareGroupWiseDto.push(temp);
			});
			
			
			
			//Doctor Share Service Wise details
			var listTGlobalDocShareServiceWiseDto = [];
			angular.forEach($scope.addServicesNewDoctorWiseShareList,function(value,index){
				var temp = {
						specialityId :value.departmentId,
						orgId : value.orgId,
						serviceId : value.servicesMstId,
						docShare : value.share,
						status : "A",
						effectiveDate : value.effectiveDate,
						gradeId:value.doctorGradeId,
						billingBedCategoryId : value.bedBillingCategoryId,
						patientTypeId : value.patientTypeId,
						createdBy:$scope.userId,
						updatedBy:$scope.userId
						
				};
				listTGlobalDocShareServiceWiseDto.push(temp);
			});
		
			var param = {listTGlobalDocShareGroupWiseDto:listTGlobalDocShareGroupWiseDto,listTGlobalDocShareServiceWiseDto:listTGlobalDocShareServiceWiseDto
					,orgId:$scope.organizationId,unitId:$scope.unitId}

			// console.log("param",JSON.stringify(param));
			
			 console.log("listTGlobalDocShareGroupWiseDto",JSON.stringify(listTGlobalDocShareGroupWiseDto));
			 console.log("listTGlobalDocShareServiceWiseDto",JSON.stringify(listTGlobalDocShareServiceWiseDto));
			$rootScope.startLoader();
			var URI = BASE_URL + ROOT_URL + "/unit/DoctorShare/saveGlobalDoctorShare";
			BillingGenericService.serviceAction(METHOD_POST,URI, param,NOTIFICATION_MSG_STATUS_TRUE).then(function(response) {
				if (response.data.status == "success")
					$scope.init();
				
				$rootScope.stopLoader();
			});
		}
	  
	  
	  
	  function cartesian() {
		    var r = [], arg = arguments, max = arg.length-1;
		    function helper(arr, i) {
		        for (var j=0, l=arg[i].length; j<l; j++) {
		            var a = arr.slice(0); // clone arr
		            a.push(arg[i][j]);
		            if (i==max)
		                r.push(a);
		            else
		                helper(a, i+1);
		        }
		    }
		    helper([], 0);
		    return r;
		}
	
	
}]);