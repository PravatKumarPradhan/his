'use strict';

angular.module('myApp',['ui.router','ngAnimate','ngStorage','ngCookies','angular-growl','ui.bootstrap','ui.mask','datatables','ngSanitize','ui.mask','ui.bootstrap','angularjs-dropdown-multiselect','angularjs-dropdown-multiselect-day','checklist-model','chart.js', 'angular.filter', 'barcode', 'checklist-model','katex'])
.run(['$rootScope','$cookies','$state','$window', function ($rootScope,$cookies,$state,$window) {
	
	$rootScope.loginpage= false;
	$rootScope.selectedPageName= "";
	 $rootScope.selectedPageNameForAdmissionNote= "";
	$rootScope.getMasterPage = function(header){
	//	alert('cd');
		//$rootScope.commonType = header;
		 sessionStorage.setItem("commonType",header);
		$cookies.put('selectedPageName', header);
		
		$rootScope.$emit('customEvent', header)
		$state.go('commonMaster',{},{ reload: true });
	}
	
	$rootScope.getFormatDate = function(date)
	{
		if(date != "")
			return date = date.getDate() + '-' + (date.getMonth() + 1) + '-' + date.getFullYear();
	}
	$rootScope.gotBedMgnt = function(){
		$state.go('bedManagement');
	}
	
	$rootScope.gotoOpBilling = function(){
		$state.go('opdBilling',{"appointmentObj" : ""});
	}
	
	//CODE ADDED BY GANESH 13.11.2017
	//Sorting column
	/**Code for sorting column data*/
	$rootScope.reverse = false; 
	 $rootScope.sortColumn = function(col)
	 {
		 $rootScope.column = col;
	  if($rootScope.reverse)
	  {
		  $rootScope.reverse = false;
		  $rootScope.reverseclass = 'fa fa-caret-up';
		  
	  }else
	  {
		  $rootScope.reverse = true;
		  $rootScope.reverseclass = 'fa fa-caret-down';
	  }
	 };
	
	//CODE ADDED BY GANESH 06.11.2017
	$rootScope.startLoader = function()
	{
		  $('#preloader').show();
		  $( "#sidebar-wrapper" ).addClass( "sidebar-wrapper ");
	}
	$rootScope.stopLoader = function()
	{
		 $( "#sidebar-wrapper" ).removeClass( "sidebar-wrapper ");
		 $('#preloader').hide();
	}
	
	
	$rootScope.getChangedFormatedDate = function(datepickerRes)
	{
		var array = datepickerRes.split('-');
		var newDate = "";
	   return newDate = array[2]+'/'+array[1]+'/'+array[0];
	}
	$rootScope.getChangedFormatedDateShalse = function(datepickerRes)
	{
		var array = datepickerRes.split('/');
		var newDate = "";
	   return newDate = array[0]+'-'+array[1]+'-'+array[2];
	}
	$rootScope.getFormatedDate = function(datepickerRes)
	{
		var array = datepickerRes.split('/');
		var newDate = "";
	   return newDate = array[1]+'-'+array[0]+'-'+array[2];
	}
	
	$rootScope.getAge = function(dateOfBirth)
	{
		var ageDifMs = Date.now() - new Date(dateOfBirth);
	    var ageDate = new Date(ageDifMs); // miliseconds from epoch
	   return  Math.abs(ageDate.getUTCFullYear() - 1970);
	}

	$rootScope.getAdmissionNote = function(textBoxHideShow){		
		$cookies.put('selectedPageNameForAdmissionNote', textBoxHideShow);
		$state.go('admissionRequest',{},{reload:true});
		$(".selectedPageName").text("Admission Note");
	}
	
	//added for billing
	$rootScope.parseFloatNum = function(number){
		return (number != '' && typeof(number) != 'undefined' && !isNaN(number)) ? parseFloat(number).toFixed(2) : parseFloat(0).toFixed(2);
	}
	
	$rootScope.getFormattedDate = function(date,isTime){
		var tempDate = new Date(date);
		var date = tempDate.getDate().toString().length == 1 ? "0"+tempDate.getDate().toString() : tempDate.getDate().toString();
		var month =  (tempDate.getMonth() + 1).toString().length == 1 ? "0"+(tempDate.getMonth() + 1).toString() : (tempDate.getMonth() + 1).toString();
		var year = tempDate.getFullYear();
		if(isTime)
			return date + "-" + month + "-" + year + " "+tempDate.getHours()+":"+tempDate.getMinutes()+":"+getSeconds();
		else
			return date + "-" + month + "-" + year + " 	:00";
	}
	
	$rootScope.getVisitTypeById = function(id){
		var visitType = "";
		if(id == 1)
			visitType = "OP";
		else if (id == 2)
			visitType = "IP";
		else if (id == 3)
			visitType = "Day Care";
		else if (id == 4)
			visitType = "ER";
		
		return visitType;
		
	}
	
	$rootScope.getBillStatusById = function(id){
		var status = "";
		if(id == 1)
			status = "Pending";
		else if (id == 2)
			status = "OnHold";
		else if (id == 3)
			status = "Settled";
		
		return status;
	}
	/*Added By Jyoti
	04-10-2017*/
}])



.config(['$stateProvider', '$urlRouterProvider','growlProvider', function($stateProvider, $urlRouterProvider,growlProvider){
	try{
	 growlProvider.globalTimeToLive(3000);
	$urlRouterProvider.otherwise("/login")
	
	$stateProvider
	.state('login', {
        url: '/login',
        templateUrl: 'views/login.html',
        controller:'loginController'
    })
    
    .state('dashBoard', {
        url: '/dashBoard',
        templateUrl: 'views/dashboard.html',
        controller:'MasterController'
    })
    
    .state('allergies', {
        url: '/allergies',
        templateUrl: 'views/Nursing/allergies.html',
        controller:'MasterController'
    })
    
    .state('bedManagement', {
        url: '/bedManagement',
        templateUrl: 'views/bedManagement/bed-management.html',
        controller:'bedManagementController'
    })
   .state('icutypeMaster', {
		 url: '/icutypeMaster',
		 templateUrl: 'views/master/UnitMaster/organizationMaster/icu-type-master.html',
		 controller: "icuTypeController"
	})
	/*.state('organizationMaster', {
		 url: '/organizationMaster',
		 templateUrl: 'views/master/GlobalMaster/hospital-master.html',
		 controller: "organizationController"
	})*/
	.state('specialityMaster', {
		 url: '/specialityMaster',
		 templateUrl: 'views/master/GlobalMaster/department-master-global.html',
		 controller: "specialityMasterController"
	})
	.state('subSpecialityMaster', {
		 url: '/subSpecialityMaster',
		 templateUrl: 'views/master/GlobalMaster/sub-department-Master.html',
		 controller: "subSpecialityMasterController"
	})
	.state('holidayCalenderMaster', {
		 url: '/holidayCalenderMaster',
		 templateUrl: 'views/master/GlobalMaster/holiday-calendar-master.html',
		 controller: "holidayCalenderMasterController"
	})
	.state('serviceMaster', {
		 url: '/serviceMaster',
		 templateUrl: 'views/master/GlobalMaster/service-master.html',
		 controller: "serviceMasterController"
	})
	.state('nationalityMaster', {
		 url: '/nationalityMaster',
		 templateUrl: 'views/master/GlobalMaster/nationality-master.html',
		 controller: "nationalityMasterController"
	})
	.state('raceMaster', {
		 url: '/raceMaster',
		 templateUrl: 'views/master/GlobalMaster/race-master.html',
		 controller: "raceMasterController"
	})
	.state('countryMaster', {
		 url: '/countryMaster',
		 templateUrl: 'views/master/GlobalMaster/country-master.html',
		 controller: "countryMasterController"
	})
	.state('stateMaster', {
		 url: '/stateMaster',
		 templateUrl: 'views/master/GlobalMaster/state-master.html',
		 controller: "stateMasterController"
	})
	.state('districtMaster', {
		 url: '/districtMaster',
		 templateUrl: 'views/master/GlobalMaster/district-master.html',
		 controller: "districtMasterController"
	})
	.state('cityMaster', {
		 url: '/cityMaster',
		 templateUrl: 'views/master/GlobalMaster/city-master.html',
		 controller: "cityMasterController"
	})
	.state('areaMaster', {
		 url: '/areaMaster',
		 templateUrl: 'views/master/GlobalMaster/area-master.html',
		 controller: "areaMasterController"
	})
	.state('identificationMaster', {
		 url: '/identificationMaster',
		 templateUrl: 'views/master/GlobalMaster/identificationMaster.html',
		 controller: "identificationMasterController"
	})
	.state('sexMaster', {
		 url: '/sexMaster',
		 templateUrl: 'views/master/GlobalMaster/sex-master.html',
		 controller: "sexMasterController"
	})
	.state('relationMaster', {
		 url: '/relationMaster',
		 templateUrl: 'views/master/GlobalMaster/relation-master.html',
		 controller: "relationMasterController"
	})
	.state('maritalstatusMaster', {
		 url: '/maritalstatusMaster',
		 templateUrl: 'views/master/GlobalMaster/marital-status-master.html',
		 controller: "maritalstatusMasterController"
	})
	.state('religionMaster', {
		 url: '/religionMaster',
		 templateUrl: 'views/master/GlobalMaster/religion-master.html',
		 controller: "religionMasterController"
	})
	.state('occupationMaster', {
		 url: '/occupationMaster',
		 templateUrl: 'views/master/GlobalMaster/occupation-master.html',
		 controller: "occupationMasterController"
	})
	.state('patientSourceMaster', {
		 url: '/patientSourceMaster',
		 templateUrl: 'views/master/GlobalMaster/patient-source-master.html',
		 controller: "patientSourceMasterController"
	})
	.state('referralTypeMaster', {
		 url: '/referralTypeMaster',
		 templateUrl: 'views/master/GlobalMaster/referral-type-master.html',
		 controller: "referralTypeMasterController"
	})
	.state('commonMaster', {
		 url: '/commonMaster/:url',
		 templateUrl: 'views/master/common-master-screen.html',
		 resolve : {
		            getController : function($stateParams)
		            {
		                if ($stateParams.url === "sampleMaster")
		                {
		                    return "sampleController"
		                }else if ($stateParams.url === "containerMaster")
		                {
		                    return "containerController"
		                }else if ($stateParams.url === "techinqueMaster")
		                {
		                    return "techinqueController"
		                }else if ($stateParams.url === "prerequisitesMaster")
		                {
		                    return "prerequisitesController"
		                }else if ($stateParams.url === "unitMaster")
		                {
		                    return "unitController"
		                }else if ($stateParams.url === "reagentMaster")
		                {
		                    return "reagentController"
		                }else if ($stateParams.url === "reportTypeMaster")
		                {
		                    return "reportTypeController"
		                }else if ($stateParams.url === "bacteriaShapMaster")
		                {
		                    return "bacteriaShapController"
		                }else if ($stateParams.url === "wtifilmStudyMasterMaster")
		                {
		                    return "wtifilmStudyMasterController"
		                }else if ($stateParams.url === "mediaMaster")
		                {
		                    return "mediaController"
		                }else if ($stateParams.url === "colonyMaster")
		                {
		                    return "colonyController"
		                }else if ($stateParams.url === "organismMaster")
		                {
		                    return "organismMasterController"
		                }
		                else if ($stateParams.url === "organismGroupMaster")
		                {
		                    return "organismGroupMasterController"
		                }else if ($stateParams.url === "stainigMaster")
		                {
		                    return "stainigMasterController"
		                }else if ($stateParams.url === "antibioticMaster")
		                {
		                    return "antibioticsController"
		                }else if ($stateParams.url === "antibioticsMaster")
		                {
		                    return "antibioticController" 
		                }
		                /*else if ($stateParams.url === "specimanMaster")
		                {
		                    return "specimanController"
		                }*/ else if ($stateParams.url === "incubationTestMaster")
		                {
		                    return "incubationTestController"
		                } else if ($stateParams.url === "antibioticsGroupMaster")
		                {
		                    return "antibioticsGroupController"
		                }/* else if ($stateParams.url === "sampleTypeMediaMappingMaster")
		                {
		                    return "sampleTypeMediaMappingController"
		                }*/ else if ($stateParams.url === "sensitivityTypeResultMaster")
		                {
		                    return "sensitivityTypeResultController"
		                } else if ($stateParams.url === "slideProcedureMaster")
		                {
		                    return "slideProcedureMasterController"
		                } else if ($stateParams.url === "bacteriaClassificationMaster")
		                {
		                    return "bacteriaClassficationController"
		                }
		                else if ($stateParams.url === "blockMaster")
		                {
		                    return "blockController"
		                }
		                else if ($stateParams.url === "headerMaster")
		                {
		                    return "headerController"	
		                }else if ($stateParams.url === "departmentMaster")
		                {
		                    return "departmentController"	
		                }else if ($stateParams.url === "phlebotomyMaster")
		                {
		                    return "unitPhlebotomyController"	
		                }
		                else if ($stateParams.url === "storageLocationMaster")
		                {
		                    return "storageLocationController"	
		                }
		                else if ($stateParams.url === "sexmaster")
		                {
		                    return "sexMasterController"
		                }
		                else if ($stateParams.url === "relationmaster")
		                {
		                    return "relationMasterController"
		                }
		                else if ($stateParams.url === "maritalstatus")
		                {
		                    return "maritalstatusMasterController"
		                }
		                else if ($stateParams.url === "religion")
		                {
		                    return "religionMasterController"
		                }
		                else if ($stateParams.url === "occupation")
		                {
		                    return "occupationMasterController"
		                }
		                else if ($stateParams.url === "patientsource")
		                {
		                    return "patientSourceMasterController"
		                }
		                else if ($stateParams.url === "referraltype")
		                {
		                    return "referralTypeMasterController"
		                }

		                else if ($stateParams.url === "priority")
		                {
		                    return "priorityMasterController"

		                }
		             /*   else if ($stateParams.url === "referraltype")
		                {
		                    return "referralTypeMasterController"
		                }*/
		            },
		        },
		        controllerProvider: function(getController){
		            return getController;
		        },
	})
	.state('referralMaster', {
		 url: '/referralMaster',
		 templateUrl : 'views/master/UnitMaster/organizationMaster/referral-master.html',
		 controller : "referralMasterController"
	})
	.state('deliveryTypeMaster', {
		 url: '/deliveryTypeMaster',
		 templateUrl: 'views/master/GlobalMaster/delivery-type-master.html',
		 controller: "deliveryTypeMasterController"
	})
	.state('encounterTypeMaster', {
		 url: '/encounterTypeMaster',
		 templateUrl: 'views/master/GlobalMaster/encounter-type-master.html',
		 controller: "encounterTypeMasterController"
	})
	.state('prefixMaster', {
		 url: '/prefixMaster',
		 templateUrl: 'views/master/GlobalMaster/prefix-master.html',
		 controller: "prefixMasterController"
	})
	.state('consentTypeMaster', {
		 url: '/consentTypeMaster',
		 templateUrl: 'views/master/GlobalMaster/consent-type-master.html',
		 controller: "consentTypeMasterController"
	})
	.state('consentMaster', {
		 url: '/consentMaster',
		 templateUrl: 'views/master/GlobalMaster/consent-master.html',
		 controller: "consentMasterController"
	})
	.state('bedCategoryMaster', {
		 url: '/bedCategoryMaster',
		 templateUrl: 'views/master/GlobalMaster/Adt/bed-category-master-global.html',
		 controller: "bedCategoryMasterController"
	})
	.state('bedTypeMaster', {
		 url: '/bedTypeMaster',
		 templateUrl: 'views/master/GlobalMaster/Adt/bed-type-master.html',
		 controller: "bedTypeMasterController"
	})
	.state('admissionTypeMaster', {
		 url: '/admissionTypeMaster',
		 templateUrl: 'views/master/GlobalMaster/Adt/admission-type-master.html',
		 controller: "admissionTypeMasterController"
	})
	.state('occupancyUnitMaster', {
		 url: '/occupancyUnitMaster',
		 templateUrl: 'views/master/GlobalMaster/Adt/occupancy-unit-master.html',
		 controller: "occupancyUnitMasterController"
	})
	.state('dischargeTypeMaster', {
		 url: '/dischargeTypeMaster',
		 templateUrl: 'views/master/GlobalMaster/Adt/discharge-type-master.html',
		 controller: "dischargeTypeMasterController"
	})
	.state('shortLeaveReasonMaster', {
		 url: '/shortLeaveReasonMaster',
		 templateUrl: 'views/master/GlobalMaster/Adt/short-leave-Reasons-master.html',
		 controller: "shortLeaveReasonMasterController"
	})
	.state('hierarchyMaster', {
		 url: '/hierarchyMaster',
		 templateUrl: 'views/master/GlobalMaster/Adt/hierarchy-master.html',
		 controller: "hierarchyMasterController"
	})
	.state('documentTypeMaster', {
		 url: '/documentTypeMaster',
		 templateUrl: 'views/master/GlobalMaster/document-type-master.html',
		 controller: "documentTypeMasterController"
	})
	.state('admissionRequest', {
		 url: '/admissionRequest',
		 templateUrl: 'views/Admission/admission-request.html',
		 controller: "admissionRequestController"
	})
	.state('pendingAdmissionDischarge', {
		 url: '/pendingAdmissionDischarge',
		 templateUrl: 'views/Admission/pending-admission-discharge.html',
		 controller: "pendingAdmissionDischargeController"
	})
	.state('bedBook', {
		 url: '/bedBook',
		 templateUrl: 'views/bedManagement/bedBook.html',
		 controller: "bedBookController"
	})
	.state('bedReservationList', {
		 url: '/bedReservationList',
		 templateUrl: 'views/bedManagement/bed-reservation-list.html',
		 controller: "bedReservationListController"
	})
	.state('bedAvailability', {
		 url: '/bedAvailability',
		 templateUrl: 'views/bedManagement/bed-availability.html',
		 controller: "bedAvailabilityController"
	})
	.state('bedAllocation', {
		 url: '/bedAllocation',
		 templateUrl: 'views/bedManagement/bed-allocation.html',
		 controller: "bedAllocationController",
		 params: {
		        obj: null
		    }
	})
	.state('admittedPatientList', {
		 url: '/admittedPatientList',
		 templateUrl: 'views/Admission/admitted-patient-list.html',
		 controller: "admittedPatientListController"
	})
	.state('insuranceMaster', {
		 url: '/insuranceMaster',
		 templateUrl: 'views/master/UnitMaster/billingMaster/insurance-master.html',
		 controller: "insuranceMasterController"
	})
	.state('passIssued', {
		 url: '/passIssued',
		 templateUrl: 'views/Admission/pass-issued.html',
		 controller: "passIssuedController"
	})
	
	.state('logout', {
        url: '/logout',
        templateUrl: 'views/login.html',
        controller:'logoutController'
    })
    
    
   /*******************************************************************************
    * Start Added BY Jyoti
    Date - 04-10-2017
    **************************************************************************/
    
		    .state('intiateTransferCare', {
	    url: '/intiateTransferCare',
	    templateUrl: 'views/initiate-transfer-care.html',
	    controller: "intiateTransferCareController"
	  })
	  
	     .state('bloodRequest', {
	    url: '/bloodRequest',
	    templateUrl: 'views/blood-request.html',
	    controller: "MasterController"
	  })
	  
	     .state('requestInterConsult', {
	    url: '/requestInterConsult',
	    templateUrl: 'views/request-inter-consult.html',
	    controller: "MasterController"
	  })
	  
	   .state('pendingInterConsult', {
	    url: '/pendingInterConsult',
	    templateUrl: 'views/pending-inter-consult.html',
	    controller: "MasterController"
	  })
	  
	     .state('dueForDischarge', {
	    url: '/dueForDischarge',
	    templateUrl: 'views/due-for-discharge.html',
	    controller: "dueForDischargeController"
	  })
	   .state('ERdueForDischarge', {
	    url: '/ERdueForDischarge',
	    templateUrl: 'views/due-for-discharge.html',
	    controller: "ERDueForDischargeController"
	  })
		  	
     /********************************************** START NURSING *********************************************/
	  
	   /*.state('consumableIndenting', {
	    url: '/consumableIndenting',
	    templateUrl: 'views/Nursing/absconding-patient.html',
	    controller: "MasterController"
	  })
	  */
	   .state('abscondingPatient', {
	    url: '/abscondingPatient',
	    templateUrl: 'views/Nursing/absconding-patient.html',
	    controller: "MasterController"
	  })
	   .state('drugAdministrator', {
	    url: '/drugAdministrator',
	    templateUrl: 'views/Nursing/drug-administrator.html',
	    controller: "MasterController"
	  })
	  
	  .state('medicationAcceptance', {
	    url: '/medicationAcceptance',
	    templateUrl: 'views/Nursing/medication-acceptance.html',
	    controller: "MasterController"
	  })
	  .state('nursingOrderView', {
	    url: '/nursingOrderView',
	    templateUrl: 'views/Nursing/nursing-order-view.html',
	    controller: "MasterController"
	  })
	  .state('nursingOrder', {
	    url: '/nursingOrder',
	    templateUrl: 'views/Nursing/nursing-order.html',
	    controller: "MasterController"
	  })
	  .state('pendingNursingOrder', {
	    url: '/pendingNursingOrder',
	    templateUrl: 'views/Nursing/pending-nursing-order.html',
	    controller: "MasterController"
	  })
	  .state('sampleCollection', {
	    url: '/sampleCollection',
	    templateUrl: 'views/Nursing/sample-collection.html',
	    controller: "MasterController"
	  })
	.state('pendingInvestigations', {
	    url: '/pendingInvestigations',
	    templateUrl: 'views/Nursing/pending-investigations.html',
	    controller: "MasterController"
	  })
	  
	  /********************************************** END NURSING *********************************************/
	  /********************************************** START ICU *********************************************/
	  
	 /* .state('patientAcceptance', {
	    url: '/patientAcceptance',
	    templateUrl: 'views/ICU/patient-acceptance.html',
	    controller: "MasterController"
	  })*/
	  
	  
	  .state('patientAcceptance',
       {
        url : '/patientAcceptance',
        views :
         {
          '@' :
           {
            templateUrl : 'views/ICU/patient-acceptance.html',
            controller : 'MasterController',
           }
         }
       })
       
      .state('patientAcceptance.newAdmissionList',
       {
        url : '/newAdmissionList',
        views :
         {
          '@patientAcceptance' :
           {
            templateUrl : 'views/ICU/new-admission-list.html',
            controller : 'patientAcceptanceController',
           }
         }
       })
       .state('patientAcceptance.transferList',
       {
        url : '/transferList',
        views :
         {
          '@patientAcceptance' :
           {
            templateUrl : 'views/ICU/transfer-list.html',
            controller : 'patientAcceptanceController',
           }
         }
       })
       .state('patientAcceptance.ICUtransferList',
       {
        url : '/ICUtransferList',
        views :
         {
          '@patientAcceptance' :
           {
            templateUrl : 'views/ICU/icu-transfer-list.html',
            controller : 'patientAcceptanceController',
           }
         }
       })
	  
	  
	  
	  
	   .state('houseKeeping', {
	    url: '/houseKeeping',
	    templateUrl: 'views/ICU/pending-for-house-keeping.html',
	    controller: "houseKeepingController"
	  })
	  .state('cleanedBedAccept', {
	    url: '/cleanedBedAccept',
	    templateUrl: 'views/ICU/pending-for-bed-cleaning-acceptance.html',
	    controller: "cleanedBedAcceptController"
	  })
	   .state('transferToICU', {
	    url: '/transferToICU',
	    templateUrl: 'views/ICU/transeferToICU.html',
	    controller: "transferToICUController"
	   
	  })
	  .state('pendingTransferRequest', {
	    url: '/pendingTransferRequest',
	    templateUrl: 'views/ICU/pending-transfer-request.html',
	    controller: "pendingTransferRequestController"
	  })
	   .state('acceptanceOfTransferOfCare', {
	    url: '/acceptanceOfTransferOfCare',
	    templateUrl: 'views/ICU/acceptance-of-transfer-of-care.html',
	    controller: "acceptanceOfTransferOfCareController"
	  })
	  /* vivek start */
	   .state('pendingConsultOrderRequest', {
	    url: '/pendingConsultOrderRequest',
	    templateUrl: 'views/Nursing/pending-inter-consult-request.html',
	    controller: "pendingInterConsultRequestController"
	  })
	  
	  /* vivek end*/
	  
	  /********************************************** END ICU *********************************************/
    /********************************************** Start  MASTER *********************************************/ 
	   /*  LIS  */
		  .state('ageGroupMaster', {
		    url: '/ageGroupMaster',
		    templateUrl: 'views/master/LIS/age-group-master.html',
		    controller: "ageGroupController"
		  })
		  
		  .state('headerMaster', {
		    url: '/headerMaster',
		    templateUrl: 'views/master/LIS/header-master.html',
		    controller: "MasterController"
		  })
		  
		  .state('unknownMaster', {
			    url: '/unknownMaster',
			    templateUrl: 'views/master/LIS/unknown-master.html',
			    controller: "MasterController"
			  })
			  
		   .state('parameterMaster', {
		    url: '/parameterMaster',
		    templateUrl: 'views/master/LIS/parameter-master.html',
		    controller: "parameterMasterController"
		  })
		  
		  
		  .state('outsourceTestMaster', {
		    url: '/outsourceTestMaster',
		    templateUrl: 'views/master/LIS/outsource-test-master.html',
		    controller: "MasterController"
		  })
		  .state('departmentMaster', {
		    url: '/departmentMaster',
		    templateUrl: 'views/master/LIS/department-master.html',
		    controller: "MasterController"
		  })
		  .state('templateMaster', {
		    url: '/templateMaster',
		    templateUrl: 'views/master/LIS/template-master.html',
		    controller: "labTemplateMasterController"
		  })
		  .state('parameterFormulaMaster', {
		    url: '/parameterFormulaMaster',
		    templateUrl: 'views/master/LIS/parameter-formula-master.html',
		    controller: "parameterFormulaController"
		  })
		  .state('profilepanelTestMaster', {
			    url: '/profilepanelTestMaster',
			    templateUrl: 'views/master/LIS/profile-panel-test-master.html',
			    controller: "profilepanelTestMasterController"
			  })
		  .state('incubationPeriodMaster', {
		    url: '/incubationPeriodMaster',
		    templateUrl: 'views/master/LIS/incubation-period-master.html',
		    controller: "incubationPeriodController"
		  })
		.state('storageLocationMaster', {
		    url: '/storageLocationMaster',
		    templateUrl: 'views/master/LIS/storage_location_master.html',
		    controller: "MasterController"
		  })
		  .state('specimanMapping', {
		    url: '/specimanMapping',
		    templateUrl: 'views/master/LIS/speciman_mapping_with_block.html',
		    controller: "MasterController"
		  })
		  .state('organismMapping', {
		    url: '/organismMapping',
		    templateUrl: 'views/master/LIS/organismGroup_mapping_with_organism.html',
		    controller: "organismGroupOrganismMapperController"
		  })
		  .state('slideProcedure', {
		    url: '/slideProcedure',
		    templateUrl: 'views/master/LIS/slide_procedure_master.html',
		    controller: "MasterController"
		  })
		  .state('blockMaster', {
		    url: '/blockMaster',
		    templateUrl: 'views/master/LIS/block_master.html',
		    controller: "MasterController"
		  })
		 /*  .state('specimanMaster', {
		    url: '/specimanMaster',
		    templateUrl: 'views/master/LIS/speciman_master.html',
		    controller: "MasterController"
		  })*/
		   .state('sensitivityTestResult', {
		    url: '/sensitivityTestResult',
		    templateUrl: 'views/master/LIS/sensitivity_test_result.html',
		    controller: "MasterController"
		  })
		  .state('antibioticsGroup', {
		    url: '/antibioticsGroup',
		    templateUrl: 'views/master/LIS/antibiotics_group_master.html',
		    controller: "MasterController"
		  })
		  .state('antibioticsClass', {
			 url: '/antibioticsClass',
			 templateUrl: 'views/master/LIS/antibiotic_class.html',
			 controller: "MasterController"
		})
		.state('antibiotic', {
			 url: '/antibiotic',
			 templateUrl: 'views/master/LIS/antibiotic.html',
			 controller: "MasterController"
		})
		.state('additionofAntibiotic', {
			 url: '/additionofAntibiotic',
			 templateUrl: 'views/master/LIS/antibiotic-addition.html',
			 controller: "antibioticAdditionController"
		})
		.state('mappingofMicroorganism', {
			 url: '/mappingofMicroorganism',
			 templateUrl: 'views/master/LIS/mapping-of-microorganism.html',
			 controller: "antibioticOrganismMpprController"
		})
		.state('bacteriaClassification', {
			 url: '/bacteriaClassification',
			 templateUrl: 'views/master/LIS/bacteria_classification.html',
			 controller: "MasterController"
		})
		.state('colonyMaster', {
			 url: '/colonyMaster',
			 templateUrl: 'views/master/LIS/colony_master.html',
			 controller: "MasterController"
		})
		
		.state('textualReferenceRangeMaster', {
			 url: '/textualReferenceRangeMaster',
			 templateUrl: 'views/master/LIS/textual_reference_range-master.html',
			 controller: "MasterController"
		})
	/********************************************** END  MASTER *********************************************/ 
    
    /********************************************** START ADMISSION DISCHARGE *********************************************/
	
	  .state('deathRegistration', {
	    url: '/deathRegistration',
	    templateUrl: 'views/AdmissionDischarge/death-registration.html',
	    controller: "deathRegistrationController"
	  })
	   .state('cancelForDischarge', {
	    url: '/cancelForDischarge',
	    templateUrl: 'views/AdmissionDischarge/cancel-for-discharge.html',
	    controller: "cancelForDischargeController"
	  })
	    .state('costEstimation', {
	    url: '/costEstimation',
	    templateUrl: 'views/AdmissionDischarge/cost-estimation.html',
	    controller: "MasterController"
	  })
	  
	     
	  
	  .state('erPatientList', {
	    url: '/erPatientList',
	    templateUrl: 'views/AdmissionDischarge/ER/Er-patient-list.html',
	    controller: "erPatientListController"
	  })
	  
	  
	  .state('mapOfBedForER', {
        url: '/mapOfBedForER',
        templateUrl: 'views/bedManagement/bed-management.html',
        controller:'mapOfBedERController'
    })
	  
	  
	    .state('bedTypeAllocation', {
	    url: '/bedTypeAllocation',
	    templateUrl: 'views/AdmissionDischarge/ER/bed-type-allocation.html',
	    controller: "bedTypeAllocationERController"
	  })
	  
	   .state('patientForDischarge', {
	    url: '/patientForDischarge',
	    templateUrl: 'views/AdmissionDischarge/patient-for-discharge.html ',
	    controller: "patientForDischargeController"
	  })
	  
	  .state('readyForBilling', {
	    url: '/readyForBilling',
	    templateUrl: 'views/AdmissionDischarge/ready-for-billing.html',
	    controller: "readyForBillingController"
	  })
	  
	   .state('ERreadyForBilling', {
	    url: '/ERreadyForBilling',
	    templateUrl: 'views/AdmissionDischarge/ready-for-billing.html',
	    controller: "ERReadyForBillingController"
	  })
	  
	  .state('finalDischarge', {
	    url: '/finalDischarge',
	    templateUrl: 'views/AdmissionDischarge/final-discharge.html',
	    controller: "finalDischargeController"
	  })
	  
	  .state('ERfinalDischarge', {
		    url: '/ERfinalDischarge',
		    templateUrl: 'views/AdmissionDischarge/final-discharge.html',
		    controller: "ERfinalDischargeController"
		  })
	  
	  .state('bedUnderMaintenance', {
	    url: '/bedUnderMaintenance',
	    templateUrl: 'views/AdmissionDischarge/bed-under-maintenance.html',
	    controller: "MasterController"
	  })
	  
	  .state('shortLeaveDischarge', {
	    url: '/shortLeaveDischarge',
	    templateUrl: 'views/AdmissionDischarge/short-leave-discharge.html',
	    controller: "shortLeaveDischargeController"
	  })
	  
	   .state('pendingShortLeave', {
	    url: '/pendingShortLeave',
	    templateUrl: 'views/AdmissionDischarge/pending-short-leave.html',
	    controller: "pendingShortLeaveController"
	  })
	  
	  .state('shortLeaveStatus', {
	    url: '/shortLeaveStatus',
	    templateUrl: 'views/AdmissionDischarge/short-leave-status.html',
	    controller: "shortLeaveStatusController"
	  })
	  
	  .state('icuBedBookingStatus', {
	    url: '/icuBedBookingStatus',
	    templateUrl: 'views/AdmissionDischarge/icu-bed-booking-status.html',
	    controller: "MasterController"
	  })
	  
	  .state('newBornRegistration', {
	    url: '/newBornRegistration',
	    templateUrl: 'views/AdmissionDischarge/new-born-registration.html',
	    controller: "MasterController"
	  })
	  
	   .state('cradleBedAllocation', {
	    url: '/cradleBedAllocation',
	    templateUrl: 'views/AdmissionDischarge/cradle-bed-allocation.html',
	    controller: "MasterController"
	  })
	  
	  .state('newBornList', {
	    url: '/newBornList',
	    templateUrl: 'views/AdmissionDischarge/new-born-list.html',
	    controller: "MasterController"
	  })
	  
	 
    
     /********************************************** End ADMISSION DISCHARGE *********************************************/
	  
	  
	  /********************************************** START MORGUE *********************************************/
	  
	  .state('morguePendinglist', {
	    url: '/morguePendinglist',
	    templateUrl: 'views/Morgue/morgue-pending-list.html',
	    controller: "morguePendinglistController"
	  })
	   .state('morgueAvailability', {
	    url: '/morgueAvailability',
	    templateUrl: 'views/Morgue/morgue-availability.html',
	    controller: "morgueAvailabilityController"
	  })
	  .state('morgueReservation', {
	    url: '/morgueReservation',
	    templateUrl: 'views/Morgue/morgue-reservation.html',
	    controller: "morgueReservationController"
	  })
	   .state('morgueReleased', {
	    url: '/morgueReleased',
	    templateUrl: 'views/Morgue/morgue-released.html',
	    controller: "morgueReleasedController"
	  })
	  
	  /********************************************** END MORGUE *********************************************/
	
	  .state('testMaster', {
	    url: '/testMaster',
	    templateUrl: 'views/LIS/test-master.html',
	    controller: "singleParameterTestController"
	  })
	  .state('multiparameterTestMaster', {
	    url: '/multiparameterTestMaster',
	    templateUrl: 'views/LIS/multiparameter-master.html',
	    controller: "multiParameterTestController"
	  })
	  
	  
	  
	  
	  /********************************************** Start LIS Histopathology *********************************************/
	  
	  .state('addendumReport', {
	    url: '/addendumReport',
	    templateUrl: 'views/LIS/Histopathology/addendum-report.html',
	    controller: "MasterController"
	  })
	  .state('blockCreation', {
	    url: '/blockCreation',
	    templateUrl: 'views/LIS/Histopathology/block-creation.html',
	    controller: "blockCreationController"
	  })
	  .state('macroscopeExamination', {
		    url: '/macroscopeExamination',
		    templateUrl: 'views/LIS/Histopathology/macroscope-examination.html',
		    controller: "MasterController"
		  })
		  
		  .state('microscopeExamination', 
		   {
		    url: '/microscopeExamination',
		    views : 
		      {
				'@' : 
				    {
		             templateUrl: 'views/LIS/Histopathology/microscope-examination.html',
		             controller: "histoMicroScopicWorklistController"
				    }
		      }
		  })  
		  
		  	.state('macroscopicExamination', 
		     {
		             url: '/macroscopicExamination',
		             templateUrl: 'views/LIS/Histopathology/macroscopic-examination.html',
		             controller: "histMacroScopicWorklistController",
		             params: 
		             {
		            	 microScopeObj: {} //default value
		             }
		    }) 
			.state('viewMacroscopicExamination', 
		     {
	             url: '/viewMacroscopicExamination',
	             templateUrl: 'views/LIS/Histopathology/details-view-macroscopic-examination.html',
	             controller: "masterController",
		           
		    }) 
		    .state('detailsMicroscopicExamination', 
		     {
	             url: '/detailsMicroscopicExamination',
	             templateUrl: 'views/LIS/Histopathology/details-microscopic-examination.html',
	             controller: "masterController",
		           
		    }) 
	  .state('microscopicExaminiationWorkList', {
		    url: '/microscopicExaminiationWorkList',
		    templateUrl: 'views/LIS/Histopathology/microscopic-examination-worklist.html',
		    controller: "microscopicExaminiationWorkListController"
		  })
		  
		 /*  Details Microscopic Examiniation date - 26April*/
		  
	   .state('detailsMicroscopicExaminiation', {
		    url: '/detailsMicroscopicExaminiation',
		    templateUrl: 'views/LIS/Histopathology/details-microscopic-examination-popup.html',
		    controller: "detailsMicroscopicExaminiationWorkListController",
		    params: 
	             {
		    	    subSpecimenObj: {} //default value
	             }
		  })
	
		  .state('restainingRequestWorklistDetails',
				  {
			  		url : '/restainingRequestWorklistDetails',
			  		templateUrl : 'views/LIS/Histopathology/re-staining-request-worklist-details.html',
			  		controller : "restainRequestWorklistDetailsController",
			  		params: 
			  			{
			  				restainReqMstObj: {} //default value
			  			} 
				  })
		  .state('reportCreationRelease', {
		    url: '/reportCreationRelease',
		    templateUrl: 'views/LIS/Histopathology/report-creation-release.html',
		    controller: "histoReportCreationController",
		    params: 
	             {
	            	 subSpecimenObj: {} //default value
	             }		
		  })
		  .state('restainingRequest', {
		    url: '/restainingRequest',
		    templateUrl: 'views/LIS/Histopathology/restaining-request-creation.html',
		    controller: "histoRestainRequestController",
		    params: 
	             {
	            	 subSpecimenObj: {} //default value
	             }	
		  })
		  .state('slideCreation', {
		    url: '/slideCreation',
		    templateUrl: 'views/LIS/Histopathology/slide-creation.html',
		    controller: "slideCreationController"
		  })
		  .state('restainingRequestWorklist', {
		    url: '/restainingRequestWorklist',
		    templateUrl: 'views/LIS/Histopathology/re-staining-request-worklist.html',
		    controller: "restainRequestWorklistController"
		  })
		  
		  .state('specimenReceipt', {
			    url: '/specimenReceipt',
			    templateUrl: 'views/LIS/Histopathology/specimen-receipt.html',
			    controller: "specimenReceiptController"
			  })
			  
			  .state('requestForSecondOpinion', {
			    url: '/requestForSecondOpinion',
			    templateUrl: 'views/LIS/Histopathology/req-second-opinion.html',
			    controller: "MasterController"
			  	})
			  
			  	.state('secondOpinionRequest', {
			    url: '/secondOpinionRequest',
			    templateUrl: 'views/LIS/Histopathology/second-opinion-request.html',
			    controller: "MasterController"
			  	})
			  	
			  	.state('pendingSlideForSecondOpinion', {
			    url: '/pendingSlideForSecondOpinion',
			    templateUrl: 'views/LIS/Histopathology/pending-slide-from-second-opinion.html',
			    controller: "MasterController"
			  	})
			  	
			  	.state('acceptanceForStorage', {
			    url: '/acceptanceForStorage',
			    templateUrl: 'views/LIS/Histopathology/acceptance-for-storage.html',
			    controller: "MasterController"
			  	})
	  
		.state('storage', {
		    url: '/storage',
		    templateUrl: 'views/LIS/Histopathology/storage.html',
		    controller: "tStorageController"
		  })
		  .state('frozenSectionRequestList ', {
		    url: '/frozenSectionRequestList',
		    templateUrl: 'views/LIS/Histopathology/frozen-section-request-list.html',
		    controller: "frozenSectionRequestListController"
		  })
		  .state('outSourcedPendinglistAssignment ', {
		    url: '/outSourcedPendinglistAssignment',
		    templateUrl: 'views/LIS/Outsource/outsourced-pendinglist-assignment.html',
		    controller: "outSourcePendingListController"
		  })
		   .state('pendinglistForSampleDispatch ', {
		    url: '/pendinglistForSampleDispatch',
		    templateUrl: 'views/LIS/Outsource/pending-list-for-sample-dispatch.html',
		    controller: "pendinglistForSampleDispatchController"
		  })
		   .state('pendinglistForReportUpload ', {
		    url: '/pendinglistForReportUpload',
		    templateUrl: 'views/LIS/Outsource/pending-list-for-report-upload.html',
		    controller: "pendinglistForReportUploadController"
		  })
		  .state('workloadDashboard ', {
		    url: '/workloadDashboard',
		    templateUrl: 'views/LIS/workload-dash-board.html',
		    controller: "workloadDashboardController"
		  })
	  /********************************************** END Histopathology *********************************************/
      
	  /********************************************** Start LIS Microbiology *********************************************/
		  
		  .state('observationDuringIncubation', {
		    url: '/observationDuringIncubation',
		    templateUrl: 'views/LIS/Microbiology/observation-during-incubation.html',
		    controller: "incubationObjservationController"
		  })
		  .state('sampleAcceptance', {
		    url: '/sampleAcceptance',
		    templateUrl: 'views/LIS/Microbiology/sample-acceptance.html',
		    controller: "microbiologyAcceptancePendingController"
		  })
		 .state('sampleProcessingWorkList', {
		    url: '/sampleProcessingWorkList',
		    templateUrl: 'views/LIS/Microbiology/sample-processing-work-list.html',
		    controller: "microbiologyWorklistController"
		  })
		  .state('sensitivityTestingProcess', {
			    url: '/sensitivityTestingProcess',
			    templateUrl: 'views/LIS/Microbiology/sensitivity-testing-process.html',
			    controller: "sensitivityTestingController"
			  })
		  
		.state('biochemicalTesting', {
		    url: '/biochemicalTesting',
		    templateUrl: 'views/LIS/Microbiology/biochemical-testing.html',
		    controller: "biochemicalResultWorklistController"
		 })
		 .state('detailsBiochemicalTesting', {
		    url: '/detailsBiochemicalTesting',
		    templateUrl: 'views/LIS/Microbiology/details-biochemical-testing.html',
		    controller: "biochemicalResultDetailsController",
		    params: 
	        {
		    	biochemicalResultObj: {} //default value
            }	
		 })
		 .state('microscopicExamination', {
		    url: '/microscopicExamination',
		    templateUrl:'views/LIS/Microbiology/microscopic-examination.html',
		    controller: "microscopicExaminationController"
		 })
		 .state('detailsMicroscopicExam', {
		    url: '/detailsMicroscopicExam',
		    templateUrl:'views/LIS/Microbiology/details-microscopic-exam-LIS.html',
		    controller: "detailsMicroscopicExaminationController",
		    params: 
	        {
		    	microExaObj: {} //default value
            }	
		 })
	/********************************************** END LIS Microbiology *********************************************/
    /********************************************** START LIS Phlebotomy *********************************************/
	
		.state('patientArrival', {
		    url: '/patientArrival',
		    templateUrl: 'views/LIS/Phlebotomy/patient-arrival.html',
		    controller: "patientArrivalController"
		  })
		.state('patientArrivalForRepeatSampleCollection', {
		    url: '/patientArrivalForRepeatSampleCollection',
		    templateUrl: 'views/LIS/Phlebotomy/patient-arrival-for-repeat-sample-collection.html',
		    controller: "patientArrivalForRepeatSampleCollectionController"
		  })
		.state('phlebotomyWorkList', {
		    url: '/phlebotomyWorkList',
		    templateUrl: 'views/LIS/Phlebotomy/phlebotomy-work-list.html',
		    controller: "phlebotomyController"
		  })
		.state('sendToCR', {
			    url: '/sendToCR',
			    templateUrl: 'views/LIS/Phlebotomy/send-to-CR.html',
			    controller: "sendToCrController"
			  })

    /********************************************** END LIS Phlebotomy *********************************************/
	/********************************************** Start LIS Central Area *********************************************/
			  
		.state('centrifugationWorkList', {
			    url: '/centrifugationWorkList',
			    templateUrl: 'views/LIS/CentralArea/centrifugation-work-list.html',
			    controller: "centrifugationWorklistController"
			  })
			.state('sampleRecollection', {
			    url: '/sampleRecollection',
			    templateUrl: 'views/LIS/CentralArea/sample-recollection.html',
			    controller: "sampleRecollectionController"
			  })		  
			  
	    .state('configurationWorkList', {
			    url: '/configurationWorkList',
			    templateUrl: 'views/LIS/CentralArea/configuration-work-list.html',
			    controller: "MasterController"
			  })
	    .state('worklistForDepartment', {
		 	    url: '/worklistForDepartment',
			    templateUrl: 'views/LIS/CentralArea/department-worklist.html',
			    controller: "biochemistryWorklistController"
			  })
		.state('inPatient', {
		 	    url: '/inPatient',
			    templateUrl: 'views/LIS/CentralArea/in-patient.html',
			    controller: "inPatientController"
			  })
	    .state('outPatient', {
		 	    url: '/outPatient',
			    templateUrl: 'views/LIS/CentralArea/out-patient.html',
			    controller: "OutPatientController"
			  })
	    .state('pendingListForAcceptance', {
		 	    url: '/pendingListForAcceptance',
			    templateUrl: 'views/LIS/CentralArea/pending-list-for-acceptance.html',
			    controller: "sampleAcceptancePendingController"
			  })
		.state('worklistForPrinting', {
		 	    url: '/worklistForPrinting',
			    templateUrl: 'views/LIS/CentralArea/printing-worklist.html',
			    controller: "worklistForPrintingController"
			  })
	    .state('worklistForReportRelease', {
		 	    url: '/worklistForReportRelease',
			    templateUrl: 'views/LIS/CentralArea/release-report-worklist.html',
			    controller: "worklistForReportReleaseController"
			  })
		.state('detailsWorklistForReportRelease', {
		 	 url: '/detailsWorklistForReportRelease',
			 templateUrl: 'views/LIS/CentralArea/details-release-report-worklist.html',
			 controller: "detailsReportReleaseController", 
			 params: 
				        {
				    		resultMstObj: {} //default value
			            }	
		})
	   .state('worklistForReportEntry', {
			 	    url: '/worklistForReportEntry',
				    templateUrl: 'views/LIS/CentralArea/report-entry-worklist.html',
				    controller: "worklistForReportEntryController"
				  })
		.state('deatilsReportEntryWorklist', {
			 	    url: '/deatilsReportEntryWorklist',
				    templateUrl: 'views/LIS/CentralArea/details-report-entry-worklist.html',
				    controller: "deatilsReportEntryWorklistController", 
				    params: 
				        {
				    		resultMstObj: {} //default value
			            }	
				  })
		.state('techniqunitionworklist', {
			 	    url: '/techniqunitionworklist',
				    templateUrl: 'views/LIS/CentralArea/techniqunition-worklist.html',
				    controller: "MasterController"
				  })
		.state('worklistForValidation', {
	 	    url: '/worklistForValidation',
		    templateUrl: 'views/LIS/CentralArea/validation-worklist.html',
		    controller: "worklistForValidationController"
		  })
		 .state('detailsWorklistForValidation', {
	 	    url: '/detailsWorklistForValidation',
		    templateUrl: 'views/LIS/CentralArea/details-validation-worklist.html',
		    controller: "detailsResultValidationController", 
		    params: 
	        {
	    		resultMstObj: {} //default value
            }	
		  })
   /********************************************** END LIS Central Area *********************************************/
   /********************************************** Start Pharmacy and Inventory *********************************************/
	/******************* Pharmacology - In Patient pages controller start**************************/
	
	
	.state('detailsPatientRejectedRejectionConsumable', {
		 url: '/detailsPatientRejectedRejectionConsumable',
		 templateUrl: 'views/PharmacyInventory/InPatient/details-patient-rejected-rejection-consumable.html',
		 controller: "MasterController"
	})

/*	.state('detailsbillableConsumption', {
		 url: '/detailsbillableConsumption',
		 templateUrl: 'views/PharmacyInventory/InPatient/details-billable-consumption.html',
		 controller: "MasterController"
	})*/


	.state('crashCartRequest', {
		 url: '/crashCartRequest',
		 templateUrl: 'views/PharmacyInventory/InPatient/crash-cart-request.html',
		 controller: "MasterController"
	})
	.state('addNewCrashCartRequest', {
		 url: '/addNewCrashCartRequest',
		 templateUrl: 'views/PharmacyInventory/InPatient/add-new-crash-cart-request.html',
		 controller: "MasterController"
	})
	.state('detailsCrashCartRequest', {
		 url: '/detailsCrashCartRequest',
		 templateUrl: 'views/PharmacyInventory/InPatient/details-crash-cart-request.html',
		 controller: "MasterController"
	})
	.state('administrationApproval', {
		 url: '/administrationApproval',
		 templateUrl: 'views/PharmacyInventory/InPatient/crash-cart-medication-administration-approval.html',
		 controller: "MasterController"
	})
	.state('detailsAdministrationApproval', {
		 url: '/detailsAdministrationApproval',
		 templateUrl: 'views/PharmacyInventory/InPatient/details-crash-cart-medication-administration-approval.html',
		 controller: "MasterController"
	})
	.state('crashCartRequestApproval', {
		 url: '/crashCartRequestApproval',
		 templateUrl: 'views/PharmacyInventory/InPatient/crash-cart-request-approval.html',
		 controller: "MasterController"
	})
	.state('detailsCrashCartRequestApproval', {
		 url: '/detailsCrashCartRequestApproval',
		 templateUrl: 'views/PharmacyInventory/InPatient/details-crash-cart-request-approval.html',
		 controller: "MasterController"
	})
	/*.state('topUpOrder', {
		 url: '/topUpOrder',
		 templateUrl: 'views/PharmacyInventory/InPatient/top-up-order.html',
		 controller: "MasterController"
	})*/
	
	
	/****** patient Indent page Added on - 27/03*****/
	.state('topOrderList', {
		 url: '/topOrderList',
		 templateUrl: 'views/PharmacyInventory/InPatient/top-order-up-list.html',
		 controller: "MasterController"
	})
	
	.state('storageLocationMasterForSlideBlocks', {
		 url: '/storageLocationMasterForSlideBlocks',
		 templateUrl: 'views/PharmacyInventory/InPatient/storage-location-master-for-slide-blocks.html',
		 controller: "storageLocationMstForSlideBlocksController"
	})
	.state('encounterQueueManagement', {
		 url: '/encounterQueueManagement',
		 templateUrl: 'views/master/GlobalMaster/Encounter/encounter-queue-management.html',
		 controller: "encounterQueueManagementController"
	})
	/***********/
	.state('dischargeMedicationReconciliation', {
		 url: '/dischargeMedicationReconciliation',
		 templateUrl: 'views/PharmacyInventory/InPatient/discharge-medication-reconciliation.html',
		 controller: "MasterController"
	})
	.state('dischargeMedicationHandover', {
		 url: '/dischargeMedicationHandover',
		 templateUrl: 'views/PharmacyInventory/InPatient/discharge-medication-hand-over.html',
		 controller: "MasterController"
	})
	.state('erDischargeMedicationHandover', {
		 url: '/erDischargeMedicationHandover',
		 templateUrl: 'views/PharmacyInventory/InPatient/er-discharge-medication-hand-over.html',
		 controller: "erDischargeMedicationHandoverController"
	})
	.state('homecareMedicationEntry', {
		 url: '/homecareMedicationEntry',
		 templateUrl: 'views/PharmacyInventory/InPatient/homecare-medication-entry.html',
		 controller: "MasterController"
	})
	.state('homecareMedicationApproval', {
		 url: '/homecareMedicationApproval',
		 templateUrl: 'views/PharmacyInventory/InPatient/homecare-medication-approval.html',
		 controller: "MasterController"
	})
	/******** 31 july 2017 added *********/
	// .state('billSalesReturn', {
	// 	 url: '/billSalesReturn',
	// 	 templateUrl: 'views/PharmacyInventory/InPatient/bill-sales-return.html',
	// 	 controller: "MasterController"
	// })
	// .state('addNewbillSalesReturn', {
	// 	 url: '/addNewbillSalesReturn',
	// 	 templateUrl: 'views/PharmacyInventory/InPatient/add-new-bill-sales-return.html',
	// 	 controller: "MasterController"
	// })
 
	.state('addNewMaterialIssue', {
		 url: '/addNewMaterialIssue',
		 templateUrl: 'views/PharmacyInventory/InPatient/add-new-material-issue.html',
		 controller: "MasterController"
	})
	.state('topUpIndent', {
		 url: '/topUpIndent',
		 templateUrl: 'views/PharmacyInventory/InPatient/top-up-indent.html',
		 controller: "MasterController"
	})
	.state('expiredItemsReturns', {
		 url: '/expiredItemsReturns',
		 templateUrl: 'views/PharmacyInventory/InPatient/expired-items-returns.html',
		 controller: "MasterController"
	})
	.state('expiredReturnAcceptance', {
		 url: '/expiredReturnAcceptance',
		 templateUrl: 'views/PharmacyInventory/InPatient/expired-return-acceptance.html',
		 controller: "MasterController"
	})
	/*********Inventory new pages Added on - 5-April-2018  controller start *********/
	.state('detailsbillableConsumption', {
		 url: '/detailsbillableConsumption',
		 templateUrl: 'views/PharmacyInventory/InPatient/details-billable-consumption.html',
		 controller: "MasterController"
	})
	/*********Inventory new pages Added on - 5-April-2018  controller Start *********/
	.state('consumbleAssetIndent', {
		 url: '/consumbleAssetIndent',
		 templateUrl: 'views/PharmacyInventory/Consumable-Inventory/consumble-asset-indent.html',
		 controller: "MasterController"
	})
	.state('assetIssue', {
		 url: '/assetIssue',
		 templateUrl: 'views/PharmacyInventory/Consumable-Inventory/asset-issue.html',
		 controller: "MasterController"
	})
	.state('assetIssueDetails', {
		 url: '/assetIssueDetails',
		 templateUrl: 'views/PharmacyInventory/Consumable-Inventory/asset-issue-details.html',
		 controller: "MasterController"
	})	
	.state('internalSiteVisit', {
		 url: '/internalSiteVisit',
		 templateUrl: 'views/PharmacyInventory/Consumable-Inventory/internal-site-visit.html',
		 controller: "MasterController"
	})
	.state('externalSiteVisit', {
		 url: '/externalSiteVisit',
		 templateUrl: 'views/PharmacyInventory/Consumable-Inventory/external-site-visit.html',
		 controller: "MasterController"
	})
	
	.state('biochemicalTestMaster', {
		 url: '/biochemicalTestMaster',
		 templateUrl: '',
		 controller: "MasterController"
	})
	/*********Inventory new pages Added on - 5-April-2018  controller end *********/
	/******************* In Patient pages controller end**************************/
	/******************* Stock Adjustment pages controller start**************************/
	.state('stopTransaction', {
		 url: '/stopTransaction',
		 templateUrl: 'views/PharmacyInventory/StockAdjustment/stop-transaction.html',
		 controller: "MasterController"
	})
	.state('addNewStopTransaction', {
		 url: '/addNewStopTransaction',
		 templateUrl: 'views/PharmacyInventory/StockAdjustment/add-new-stop-transaction.html',
		 controller: "MasterController"
	})
	.state('detailsStopTransaction', {
		 url: '/detailsStopTransaction',
		 templateUrl: 'views/PharmacyInventory/StockAdjustment/details-stop-transaction.html',
		 controller: "MasterController"
	})
	.state('uploadStock', {
		 url: '/uploadStock',
		 templateUrl: 'views/PharmacyInventory/StockAdjustment/upload-stock.html',
		 controller: "MasterController"
	})
	.state('detailsUploadStock', {
		 url: '/detailsUploadStock',
		 templateUrl: 'views/PharmacyInventory/StockAdjustment/details-upload-stock.html',
		 controller: "MasterController"
	})
	.state('uploadStockApproval', {
		 url: '/uploadStockApproval',
		 templateUrl: 'views/PharmacyInventory/StockAdjustment/upload-stock-approval.html',
		 controller: "MasterController"
	})
	.state('detailsUploadStockApproval', {
		 url: '/detailsUploadStockApproval',
		 templateUrl: 'views/PharmacyInventory/StockAdjustment/details-upload-stock-approval.html',
		 controller: "MasterController"
	})
	.state('rejectedUploaded', {
		 url: '/rejectedUploaded',
		 templateUrl: 'views/PharmacyInventory/StockAdjustment/rejected-uploaded.html',
		 controller: "MasterController"
	})
	.state('computeStock', {
		 url: '/computeStock',
		 templateUrl: 'views/PharmacyInventory/StockAdjustment/compute-stock.html',
		 controller: "MasterController"
	})
	.state('detailsComputeStock', {
		 url: '/detailsComputeStock',
		 templateUrl: 'views/PharmacyInventory/StockAdjustment/details-compute-stock.html',
		 controller: "MasterController"
	})
	.state('confirmStock', {
		 url: '/confirmStock',
		 templateUrl: 'views/PharmacyInventory/StockAdjustment/confirm-stock.html',
		 controller: "MasterController"
	})
	.state('detailsConfirmStock', {
		 url: '/detailsConfirmStock',
		 templateUrl: 'views/PharmacyInventory/StockAdjustment/details-confirm-stock.html',
		 controller: "MasterController"
	})
	
	/******************* Stock Adjustment pages controller end**************************/
	/******************* Narcotics store pages controller start**************************/
	
	
	.state('packageMaster', {
		 url: '/packageMaster',
		 templateUrl: 'views/PharmacyInventory/package-master.html',
		 controller: "MasterController"
	})
	.state('unitofMeasurement', {
		 url: '/unitofMeasurement',
		 templateUrl: 'views/PharmacyInventory/unit-Measurement.html',
		 controller: "MasterController"
	})
	// .state('pharmacyunitMaster', {
	// 	 url: '/pharmacyunitMaster',
	// 	 templateUrl: 'views/PharmacyInventory/pharmacy-unit-master.html',
	// 	 controller: "MasterController"
// })
	.state('dispensingMaster', {
		 url: '/dispensingMaster',
		 templateUrl: 'views/PharmacyInventory/dispensing-master.html',
		 controller: "MasterController"
	 })
	// .state('storageunitMaster', {
	// 	 url: '/storageunitMaster',
	// 	 templateUrl: 'views/PharmacyInventory/storage-unit-master.html',
	// 	 controller: "MasterController"
	// })
	.state('procurementunitMaster', {
		 url: '/procurementunitMaster',
		 templateUrl: 'views/PharmacyInventory/procurement-unit-master.html',
		 controller: "MasterController"
	})
	.state('kitMaster', {
		 url: '/kitMaster',
		 templateUrl: 'views/PharmacyInventory/kit-master.html',
		 controller: "MasterController"
	})
	.state('combinationMedicationMaster', {
		 url: '/combinationMedicationMaster',
		 templateUrl: 'views/PharmacyInventory/combination-medication-master.html',
		 controller: "MasterController"
	})
	/******************* Narcotics pages controller end**************************/
	  
	  /*************************************Global Master Start********************************************/
	/*.state('departmentMasterGlobal', {
		 url: '/departmentMasterGlobal',
		 templateUrl: 'views/master/GlobalMaster/department-master-global.html',
		 controller: "MasterController"
	})
	
	.state('subdepartmentMaster', {
		 url: '/subdepartmentMaster',
		 templateUrl: 'views/master/GlobalMaster/sub-department-Master.html',
		 controller: "MasterController"
	})*/
	.state('patientCategory', {
		 url: '/patientCategory',
		 templateUrl: 'views/master/GlobalMaster/patient-category-master.html',
		 controller: "patientCategoryController"
	})
	.state('hospitalMaster', {
		 url: '/hospitalMaster',
		 templateUrl: 'views/master/GlobalMaster/hospital-master.html',
		 controller: "hospitalMasterController"
	})
	.state('unitMaster', {
		 url: '/unitMaster',
		 templateUrl: 'views/master/GlobalMaster/unit-master.html',
		 controller: "MasterController"
	})
	/*.state('serviceMaster', {
		 url: '/serviceMaster',
		 templateUrl: 'views/master/GlobalMaster/service-master.html',
		 controller: "MasterController"
	})*/
	.state('tariffMaster', {
		 url: '/tariffMaster',
		 templateUrl: 'views/master/GlobalMaster/tariff-master.html',
		 controller: "tariffMasterController"
	})
	
		.state('currencyMaster', {
		 url: '/currencyMaster',
		 templateUrl: 'views/master/GlobalMaster/currency-master.html',
		 controller: "currencyMasterController"
	})
	.state('currencydenomination', {
		 url: '/currencydenomination',
		 templateUrl: 'views/master/GlobalMaster/currency-denomination.html',
		 controller: "currencyDenominationController"
	})
	.state('corporateMasterGlobal', {
		 url: '/corporateMasterGlobal',
		 templateUrl: 'views/master/GlobalMaster/corporate-master-global.html',
		 controller: "MasterController"
	})
	.state('doctorCategory', {
		 url: '/doctorCategory',
		 templateUrl: 'views/master/GlobalMaster/doctor-category.html',
		 controller: "doctorCategoryController"
	})
	.state('employeeCategory', {
		 url: '/employeeCategory',
		 templateUrl: 'views/master/GlobalMaster/emplyoee-category.html',
		 controller: "employeeCategoryController"
	})
	.state('bankBranch', {
		 url: '/bankBranch',
		 templateUrl: 'views/master/GlobalMaster/bank-Branch.html',
		 controller: "bankBranchController"
	})
	.state('bankMaster', {
		 url: '/bankMaster',
		 templateUrl: 'views/master/GlobalMaster/bank-master.html',
		 controller: "MasterController"
	})
	.state('clinicMaster', {
		 url: '/clinicMaster',
		 templateUrl: 'views/master/GlobalMaster/clinic-master.html',
		 controller: "clinicMasterController"
	})
	.state('serviceTariffMaster', {
		 url: '/serviceTariffMaster',
		 templateUrl: 'views/master/UnitMaster/billingMaster/service-tariff-master.html',
		 controller: "serviceTariffMasterController"
	})
	.state('addNewServiceTariffMaster', {
		 url: '/addNewServiceTariffMaster',
		 templateUrl : 'views/master/UnitMaster/billingMaster/add-new-service-tariff-master.html',
		 controller: "serviceTariffMasterController"
	})
	.state('OPConsulationConfigurationMaster', {
		 url: '/OPConsulationConfigurationMaster',
		 templateUrl: 'views/master/UnitMaster/billingMaster/op-consultation-configuration-master.html',
		 controller: "OPConsulationConfigurationMasterController"
	})
	.state('doctorConsulationMaster', {
		 url: '/doctorConsulationMaster',
		 templateUrl: 'views/master/UnitMaster/billingMaster/doctor-consultation-master.html',
		 controller: "doctorConsulationMasterController"
	})
	
	.state('organizationMaster', {
		 url: '/organizationMaster',
		 templateUrl: 'views/master/GlobalMaster/organizationMaster.html',
		 controller: "organizationController"
	})
	
	/**************Asset Management Start******************/
	.state('auctionRequistion', {
		 url: '/auctionRequistion',
		 templateUrl: 'views/master/GlobalMaster/AssetManagenent/Master/auction-requistion.html',
		 controller: "MasterController"
	})
	.state('addnewauctionRequistion', {
		 url: '/addnewauctionRequistion',
		 templateUrl: 'views/master/GlobalMaster/AssetManagenent/Master/add-new-auction-requistion.html',
		 controller: "MasterController"
	})
	.state('detailsauctionRequistion', {
		 url: '/detailsauctionRequistion',
		 templateUrl: 'views/master/GlobalMaster/AssetManagenent/Master/details-auction-requistion.html',
		 controller: "MasterController"
	})
	.state('productCategoryMaster', {
		 url: '/productCategoryMaster',
		 templateUrl: 'views/master/GlobalMaster/AssetManagenent/Master/product-category-master.html',
		 controller: "MasterController"
		})
	.state('productstrengthMaster', {
		 url: '/productstrengthMaster',
		 templateUrl: 'views/master/GlobalMaster/product-strength-master.html',
		 controller: "MasterController"
	})
	.state('formulationTypeROAMaster', {
		 url: '/formulationTypeROAMaster',
		 templateUrl: 'views/master/GlobalMaster/AssetManagenent/Master/formulation-type-ROA-master.html',
		 controller: "MasterController"
	})
	.state('nursingOrderMaster', {
		 url: '/nursingOrderMaster',
		 templateUrl: 'views/master/GlobalMaster/nursing-order-master.html',
		 controller: "MasterController"
	})
	.state('bloodComponent', {
		 url: '/bloodComponent',
		 templateUrl: 'views/master/GlobalMaster/blood-component.html',
		 controller: "MasterController"
	})
	.state('vaccinationMaster', {
		 url: '/vaccinationMaster',
		 templateUrl: 'views/master/GlobalMaster/vaccination-master.html',
		 controller: "MasterController"
	})
	.state('immunizationSchedule', {
		 url: '/immunizationSchedule',
		 templateUrl: 'views/master/GlobalMaster/immunization-schedule.html',
		 controller: "MasterController"
	})
	.state('immuniztionHistory', {
		 url: '/immuniztionHistory',
		 templateUrl: 'views/master/GlobalMaster/immunization-history.html',
		 controller: "MasterController"
	})
	.state('rejectionMaster', {
		 url: '/rejectionMaster',
		 templateUrl: 'views/master/GlobalMaster/rejection-master.html',
		 controller: "rejectionMasterController"
	})
	.state('reasonTypeMaster', {
		 url: '/reasonTypeMaster',
		 templateUrl: 'views/master/GlobalMaster/reason-type-master.html',
		 controller: "reasonTypeMasterController"
	})
	
	.state('generalInsuranceMaster', {
		 url: '/generalInsuranceMaster',
		 templateUrl: 'views/master/GlobalMaster/AssetManagenent/Master/general-insurance-master.html',
		 controller: "MasterController"
	}) 
	/**************Asset Management End******************/
	
	/*******************Organization Master start**************************/
	.state('departmentMasterUnit', {
		 url: '/departmentMasterUnit',
		 templateUrl: 'views/master/UnitMaster/organizationMaster/department-master-unit.html',
		 controller: "departmentMasterUnitController"
	})
	.state('subDepartmentMasterUnit', {
		 url: '/subDepartmentMasterUnit',
		 templateUrl: 'views/master/UnitMaster/organizationMaster/sub-department-master-unit.html',
		 controller: "subDepartmentMasterUnitController"
	})
	.state('holidayCalendarMasterUnit', {
		 url: '/holidayCalendarMasterUnit',
		 templateUrl: 'views/master/UnitMaster/organizationMaster/holiday-calendar-master-unit.html',
		 controller: "MasterController"
	})
	.state('ICUtypeMasterUnit', {
		 url: '/ICUtypeMasterUnit',
		 templateUrl: 'views/master/UnitMaster/organizationMaster/ICU-type-master-unit.html',
		 controller: "MasterController"
	})
	.state('bedCategoryMasterUnit', {
		 url: '/bedCategoryMasterUnit',
		 templateUrl: 'views/master/UnitMaster/organizationMaster/bed-category-master-unit.html',
		 controller: "MasterController"
	})
	.state('billingBedCategoryMasterUnit', {
		 url: '/billingBedCategoryMasterUnit',
		 templateUrl: 'views/master/UnitMaster/organizationMaster/billing-bed-category-master-unit.html',
		 controller: "MasterController"
	})
	.state('bedClassMasterUnit', {
		 url: '/bedClassMasterUnit',
		 templateUrl: 'views/master/UnitMaster/organizationMaster/bed-class-master-unit.html',
		 controller: "MasterController"
	})
	.state('bedClassMappingMasterUnit', {
		 url: '/bedClassMappingMasterUnit',
		 templateUrl: 'views/master/UnitMaster/organizationMaster/bed-class-mapping-master-unit.html',
		 controller: "MasterController"
	})
	.state('nursingStationMaster', {
		 url: '/nursingStationMaster',
		 templateUrl: 'views/master/UnitMaster/organizationMaster/nursing-station-master.html',
		 controller: "MasterController"
	})
	.state('nursingStationWardMapping', {
		 url: '/nursingStationWardMapping',
		 templateUrl: 'views/master/UnitMaster/organizationMaster/nursing-station-ward-mapping.html',
		 controller: "MasterController"
	})
	.state('mealPreferenceMaster', {
		 url: '/mealPreferenceMaster',
		 templateUrl: 'views/master/UnitMaster/organizationMaster/meal-preference-master.html',
		 controller: "MasterController"
	})
	.state('roomMaster', {
		 url: '/roomMaster',
		 templateUrl: 'views/master/UnitMaster/organizationMaster/room-master.html',
		 controller: "MasterController"
	})
	.state('wardMaster', {
		 url: '/wardMaster',
		 templateUrl: 'views/master/UnitMaster/organizationMaster/ward-master.html',
		 controller: "MasterController"
	})
	.state('bedMaster', {
		 url: '/bedMaster',
		 templateUrl: 'views/master/UnitMaster/organizationMaster/bed-master.html',
		 controller: "MasterController"
	})
	.state('floorMaster', {
		 url: '/floorMaster',
		 templateUrl: 'views/master/UnitMaster/organizationMaster/floor-master.html',
		 controller: "floorMasterController"
	})
	.state('floorMapMaster', {
		 url: '/floorMapMaster',
		 templateUrl: 'views/master/UnitMaster/organizationMaster/floor-map-master.html',
		 controller: "MasterController"
	})
	.state('amenityMaster', {
		 url: '/amenityMaster',
		 templateUrl: 'views/master/UnitMaster/organizationMaster/amenity-master.html',
		 controller: "MasterController"
	})
	.state('wingMaster', {
		 url: '/wingMaster',
		 templateUrl: 'views/master/UnitMaster/organizationMaster/wing-master.html',
		 controller: "MasterController"
	})
	/*.state('referralMaster', {
		 url: '/referralMaster',
		 templateUrl: 'views/master/UnitMaster/organizationMaster/referral-master.html',
		 controller: "MasterController"
	})*/
	/*.state('deliveryTypeMaster', {
		 url: '/deliveryTypeMaster',
		 templateUrl: 'views/master/UnitMaster/organizationMaster/delivery-type-master.html',
		 controller: "MasterController"
	})*/
	/*.state('icutypeMaster', {
		 url: '/icutypeMaster',
		 templateUrl: 'views/master/UnitMaster/organizationMaster/icu-type-master.html',
		 controller: "MasterController"
	})*/
	/*.state('encounterTypeMaster', {
		 url: '/encounterTypeMaster',
		 templateUrl: 'views/master/UnitMaster/organizationMaster/encounter-type-master.html',
		 controller: "MasterController"
	})*/
	
	/*******************Organization Master End**************************/
	
	
	
	/*******************Billing Master start**************************/
	
	.state('admissionRejectionList', {
		 url: '/admissionRejectionList',
		 templateUrl: 'views/master/UnitMaster/billingMaster/admission-rejection-list.html',
		 controller: "MasterController"
	})
	
	.state('refund', {
		 url: '/refund',
		 templateUrl: 'views/master/UnitMaster/billingMaster/refund.html',
		 controller: "MasterController"
	})
	
	
		.state(
				'refundCRList',
				{
					url : '/refundCRList',
					views : {
						'@' : {
							templateUrl : 'views/master/UnitMaster/billingMaster/refund-CR-list.html',
							controller : 'MasterController',
						}
					}
				})

		.state(
				'refundCRList.cashForRefund',
				{
					url : '/cashForRefund',
					views : {
						'@refundCRList' : {
							templateUrl : 'views/master/UnitMaster/billingMaster/cash-for-refund-list.html',
							controller : 'MasterController',
						}
					}
				})
				
				.state(
				'refundCRList.chequeForRefund',
				{
					url : '/chequeForRefund',
					views : {
						'@refundCRList' : {
							templateUrl : 'views/master/UnitMaster/billingMaster/cheque-refund-list.html',
							controller : 'MasterController',
						}
					}
				})
	
	
				
				
	.state('deposit', {
		 url: '/deposit',
		 templateUrl: 'views/master/UnitMaster/billingMaster/deposit.html',
		 controller: "depositController"
	})
	
	.state('findBillList', {
		 url: '/findBillList',
		 templateUrl: 'views/master/UnitMaster/billingMaster/find-bill-list.html',
		 controller: "MasterController"
	})
	
	.state('updatePayeeInformation', {
		 url: '/updatePayeeInformation',
		 templateUrl: 'views/master/UnitMaster/billingMaster/update-payee-information.html',
		 controller: "MasterController"
	})
	
	.state('chargeConversion', {
		 url: '/chargeConversion',
		 templateUrl: 'views/master/UnitMaster/billingMaster/charge-conversion.html',
		 controller: "MasterController"
	})
	
	.state('entitlementCoversion', {
		 url: '/entitlementCoversion',
		 templateUrl: 'views/master/UnitMaster/billingMaster/entitlement-conversion.html',
		 controller: "MasterController"
	})
	
	.state('autoChargeReview', {
		 url: '/autoChargeReview',
		 templateUrl: 'views/master/UnitMaster/billingMaster/auto-charge-review.html',
		 controller: "MasterController"
	})
	
	.state('writeOff', {
		 url: '/writeOff',
		 templateUrl: 'views/master/UnitMaster/billingMaster/write-off.html',
		 controller: "MasterController"
	})
	
	
	.state('writeOffDetails', {
		 url: '/writeOffDetails',
		 templateUrl: 'views/master/UnitMaster/billingMaster/write-off-details.html',
		 controller: "MasterController"
	})
	
	.state('cashOpeningBalance', {
		 url: '/cashOpeningBalance',
		 templateUrl: 'views/master/UnitMaster/billingMaster/cash-opening-balance.html',
		 controller: "MasterController"
	})
	
	.state('intrimCashHandover', {
		 url: '/intrimCashHandover',
		 templateUrl: 'views/master/UnitMaster/billingMaster/intrim-cash-handover.html',
		 controller: "MasterController"
	})
	
	.state('roundOffConfiguration', {
		 url: '/roundOffConfiguration',
		 templateUrl: 'views/master/UnitMaster/billingMaster/round-off-configuration.html',
		 controller: "MasterController"
	})
	
	.state('cashCounterClosing', {
		 url: '/cashCounterClosing',
		 templateUrl: 'views/master/UnitMaster/billingMaster/cash-opening-closing.html',
		 controller: "MasterController"
	})
	
	.state('reminderForNOK', {
		 url: '/reminderForNOK',
		 templateUrl: 'views/master/UnitMaster/billingMaster/reminder-for-nok.html',
		 controller: "MasterController"
	})
	
	.state('companyInvoiceCreation', {
		 url: '/companyInvoiceCreation',
		 templateUrl: 'views/master/UnitMaster/billingMaster/company-invoice-creation.html',
		 controller: "MasterController"
	})
	.state('detailsCompanyInvoiceCreation', {
		 url: '/detailsCompanyInvoiceCreation',
		 templateUrl: 'views/master/UnitMaster/billingMaster/details-company-invoice-creation.html',
		 controller: "MasterController"
	})
	
	
	.state('companyRemainder', {
		 url: '/companyRemainder',
		 templateUrl: 'views/master/UnitMaster/billingMaster/company-remainder.html',
		 controller: "MasterController"
	})
	
	
	.state('settledInvoices', {
		 url: '/settledInvoices',
		 templateUrl: 'views/master/UnitMaster/billingMaster/settled-invoices.html',
		 controller: "MasterController"
	})
	
	.state('companySettlement', {
		 url: '/companySettlement',
		 templateUrl: 'views/master/UnitMaster/billingMaster/company-settlement.html',
		 controller: "MasterController"
	})
	
	
	.state('paymentCollection', {
		 url: '/paymentCollection',
		 templateUrl: 'views/master/UnitMaster/billingMaster/payment-collection.html',
		 controller: "MasterController"
	})
	
  
		   .state('paymentCollection.cash',
		  	       {
		   	        url : '/cash',
		   	        views :
		   	         {
		    	          '@paymentCollection' :
		   	           {
		    	            templateUrl : 'views/common/cash.html',
		    	            controller : 'MasterController',
		   	           }
		    	         }
    	       })
    	       
    	        .state('paymentCollection.cheque',
		  	       {
		   	        url : '/cheque',
		   	        views :
		   	         {
		    	          '@paymentCollection' :
		   	           {
		    	            templateUrl : 'views/common/cheque.html',
		    	            controller : 'MasterController',
		   	           }
		    	         }
    	       })
    	       
    	       
    	        .state('paymentCollection.creditDebitCard',
		  	       {
		   	        url : '/creditDebitCard',
		   	        views :
		   	         {
		    	          '@paymentCollection' :
		   	           {
		    	            templateUrl : 'views/common/credit-debit-card.html',
		    	            controller : 'MasterController',
		   	           }
		    	         }
    	       })
				
	
    	       
    	       
    .state('pharmacyPaymentCollection', {
		 url: '/pharmacyPaymentCollection',
		 templateUrl: 'views/PharmacyInventory/pharmacy-payment-collection.html',
		 controller: "MasterController"
	})
	
  
		   .state('pharmacyPaymentCollection.cash',
		  	       {
		   	        url : '/cash',
		   	        views :
		   	         {
		    	          '@pharmacyPaymentCollection' :
		   	           {
		    	            templateUrl : 'views/common/cash.html',
		    	            controller : 'MasterController',
		   	           }
		    	         }
    	       })
    	       
    	        .state('pharmacyPaymentCollection.cheque',
		  	       {
		   	        url : '/cheque',
		   	        views :
		   	         {
		    	          '@pharmacyPaymentCollection' :
		   	           {
		    	            templateUrl : 'views/common/cheque.html',
		    	            controller : 'MasterController',
		   	           }
		    	         }
    	       })
    	       
    	       
    	        .state('pharmacyPaymentCollection.creditDebitCard',
		  	       {
		   	        url : '/creditDebitCard',
		   	        views :
		   	         {
		    	          '@pharmacyPaymentCollection' :
		   	           {
		    	            templateUrl : 'views/common/credit-debit-card.html',
		    	            controller : 'MasterController',
		   	           }
		    	         }
    	       })
    	       

	/********************** START UNIT SERVICE MASTER ********************/
	.state('unitServiceMaster', {
		 url: '/unitServiceMaster',
		 templateUrl: 'views/master/UnitMaster/billingMaster/unit-service-master.html',
		 controller: "UnitServiceMasterController"
	})
	
	.state('unitServiceMapping', {
		 url: '/unitServiceMapping',
		 templateUrl: 'views/master/UnitMaster/billingMaster/unit-service-mapping.html',
		 controller: "UnitServiceMappingController"
	})
	
	.state('unitServiceBasePrice', {
		 url: '/unitServiceBasePrice',
		 templateUrl: 'views/master/UnitMaster/billingMaster/unit-service-base-price.html',
		 controller: "MasterController"
	})
	
	.state('schedulePriceUnitService', {
		 url: '/schedulePriceUnitService',
		 templateUrl: 'views/master/UnitMaster/billingMaster/schedule-price-unit-service.html',
		 controller: "MasterController"
	})
	
	
	.state('orderSet', {
		 url: '/orderSet',
		 templateUrl: 'views/master/UnitMaster/billingMaster/order-set.html',
		 controller: "MasterController"
	})
	
	.state('orderSetList', {
		 url: '/orderSetList',
		 templateUrl: 'views/master/UnitMaster/billingMaster/order-set-list.html',
		 controller: "MasterController"
	})
	
	
	/********************** END UNIT SERVICE MASTER ********************/
	/********************** START IPD CHARGES ********************/
										

		.state(
				'IPDCharges',
				{
					url : '/IPDCharges',
					views : {
						'@' : {
							templateUrl : 'views/master/UnitMaster/billingMaster/IPDCharge/IPD-charge.html',
							controller : 'MasterController',
						}
					}
				})

		.state(
				'IPDCharges.serviceDetailsForIPDCharge',
				{
					url : '/serviceDetailsForIPDCharge',
					views : {
						'@IPDCharges' : {
							templateUrl : 'views/master/UnitMaster/billingMaster/IPDCharge/service-details-IPD-charge.html',
							controller : 'MasterController',
						}
					}
				})

		.state(
				'IPDCharges.groupWiseForIPDCharge',
				{
					url : '/groupWiseForIPDCharge',
					views : {
						'@IPDCharges' : {
							templateUrl : 'views/master/UnitMaster/billingMaster/IPDCharge/group-wise-IPD.html',
							controller : 'MasterController',
						}
					}
				})

		.state(
				'IPDCharges.packageForIPDCharge',
				{
					url : '/packageForIPDCharge',
					views : {
						'@IPDCharges' : {
							templateUrl : 'views/master/UnitMaster/billingMaster/IPDCharge/package-details-IPD.html',
							controller : 'MasterController',
						}
					}
				})

		.state(
				'IPDCharges.transferHistoryForIPDCharge',
				{
					url : '/transferHistoryForIPDCharge',
					views : {
						'@IPDCharges' : {
							templateUrl : 'views/master/UnitMaster/billingMaster/IPDCharge/transfer-history-IPD.html',
							controller : 'MasterController',
						}
					}
				})

		.state(
				'IPDCharges.cancelServiceForIPDCharge',
				{
					url : '/cancelServiceForIPDCharge',
					views : {
						'@IPDCharges' : {
							templateUrl : 'views/master/UnitMaster/billingMaster/IPDCharge/cancel-service-IPD.html',
							controller : 'MasterController',
						}
					}
				})

		.state(
				'IPDCharges.procedureDetailsForIPDCharge',
				{
					url : '/procedureDetailsForIPDCharge',
					views : {
						'@IPDCharges' : {
							templateUrl : 'views/master/UnitMaster/billingMaster/IPDCharge/procedure-details-IPD.html',
							controller : 'MasterController',
						}
					}
				})

		.state(
				'IPDCharges.periodicityDetailsForIPDCharge',
				{
					url : '/periodicityDetailsForIPDCharge',
					views : {
						'@IPDCharges' : {
							templateUrl : 'views/master/UnitMaster/billingMaster/IPDCharge/periodicity-details-IPD.html',
							controller : 'MasterController',
						}
					}
				})

		.state(
				'IPDCharges.intrimBillForIPDCharge',
				{
					url : '/intrimBillForIPDCharge',
					views : {
						'@IPDCharges' : {
							templateUrl : 'views/master/UnitMaster/billingMaster/IPDCharge/intrim-bill-IPD.html',
							controller : 'MasterController',
						}
					}
				})

		.state(
				'IPDCharges.drugDetailsForIPDCharge',
				{
					url : '/drugDetailsForIPDCharge',
					views : {
						'@IPDCharges' : {
							templateUrl : 'views/master/UnitMaster/billingMaster/IPDCharge/drug-details-IPD.html',
							controller : 'MasterController',
						}
					}
				})

		.state(
				'IPDCharges.NOKForIPDCharge',
				{
					url : '/NOKForIPDCharge',
					views : {
						'@IPDCharges' : {
							templateUrl : 'views/AdmissionDischarge/nok-details-only.html',
							controller : 'MasterController',
						}
					}
				})
				
				.state(
				'IPDCharges.payerDetailsForIPDCharge',
				{
					url : '/payerDetailsForIPDCharge',
					views : {
						'@IPDCharges' : {
							templateUrl : 'views/master/UnitMaster/billingMaster/IPDCharge/payer-details-IPD.html',
							controller : 'MasterController',
						}
					}
				})
				
				.state(
				'IPDCharges.depositDetailsForIPDCharge',
				{
					url : '/depositDetailsForIPDCharge',
					views : {
						'@IPDCharges' : {
							templateUrl : 'views/master/UnitMaster/billingMaster/IPDCharge/deposit-details-IPD.html',
							controller : 'MasterController',
						}
					}
				})
				
				

		/**
		 * ***********************END IPD
		 * CHARGES********************
		 */

	/********************** START ER CHARGES ********************/
										

		.state(
				'ERCharges',
				{
					url : '/ERCharges',
					views : {
						'@' : {
							templateUrl : 'views/master/UnitMaster/billingMaster/IPDCharge/ER-charge.html',
							controller : 'MasterController',
						}
					}
				})

		.state(
				'ERCharges.serviceDetailsForIPDCharge',
				{
					url : '/serviceDetailsForIPDCharge',
					views : {
						'@IPDCharges' : {
							templateUrl : 'views/master/UnitMaster/billingMaster/IPDCharge/service-details-IPD-charge.html',
							controller : 'MasterController',
						}
					}
				})

		.state(
				'ERCharges.groupWiseForIPDCharge',
				{
					url : '/groupWiseForIPDCharge',
					views : {
						'@IPDCharges' : {
							templateUrl : 'views/master/UnitMaster/billingMaster/IPDCharge/group-wise-IPD.html',
							controller : 'MasterController',
						}
					}
				})

		.state(
				'ERCharges.packageForIPDCharge',
				{
					url : '/packageForIPDCharge',
					views : {
						'@IPDCharges' : {
							templateUrl : 'views/master/UnitMaster/billingMaster/IPDCharge/package-details-IPD.html',
							controller : 'MasterController',
						}
					}
				})

		.state(
				'ERCharges.transferHistoryForIPDCharge',
				{
					url : '/transferHistoryForIPDCharge',
					views : {
						'@IPDCharges' : {
							templateUrl : 'views/master/UnitMaster/billingMaster/IPDCharge/transfer-history-IPD.html',
							controller : 'MasterController',
						}
					}
				})

		.state(
				'ERCharges.cancelServiceForIPDCharge',
				{
					url : '/cancelServiceForIPDCharge',
					views : {
						'@IPDCharges' : {
							templateUrl : 'views/master/UnitMaster/billingMaster/IPDCharge/cancel-service-IPD.html',
							controller : 'MasterController',
						}
					}
				})

		.state(
				'ERCharges.procedureDetailsForIPDCharge',
				{
					url : '/procedureDetailsForIPDCharge',
					views : {
						'@IPDCharges' : {
							templateUrl : 'views/master/UnitMaster/billingMaster/IPDCharge/procedure-details-IPD.html',
							controller : 'MasterController',
						}
					}
				})

		.state(
				'ERCharges.periodicityDetailsForIPDCharge',
				{
					url : '/periodicityDetailsForIPDCharge',
					views : {
						'@IPDCharges' : {
							templateUrl : 'views/master/UnitMaster/billingMaster/IPDCharge/periodicity-details-IPD.html',
							controller : 'MasterController',
						}
					}
				})

		.state(
				'ERCharges.intrimBillForIPDCharge',
				{
					url : '/intrimBillForIPDCharge',
					views : {
						'@IPDCharges' : {
							templateUrl : 'views/master/UnitMaster/billingMaster/IPDCharge/intrim-bill-IPD.html',
							controller : 'MasterController',
						}
					}
				})

		.state(
				'ERCharges.drugDetailsForIPDCharge',
				{
					url : '/drugDetailsForIPDCharge',
					views : {
						'@IPDCharges' : {
							templateUrl : 'views/master/UnitMaster/billingMaster/IPDCharge/drug-details-IPD.html',
							controller : 'MasterController',
						}
					}
				})

		.state(
				'ERCharges.NOKForIPDCharge',
				{
					url : '/NOKForIPDCharge',
					views : {
						'@IPDCharges' : {
							templateUrl : 'views/AdmissionDischarge/nok-details-only.html',
							controller : 'MasterController',
						}
					}
				})
				
				.state(
				'ERCharges.payerDetailsForIPDCharge',
				{
					url : '/payerDetailsForIPDCharge',
					views : {
						'@IPDCharges' : {
							templateUrl : 'views/master/UnitMaster/billingMaster/IPDCharge/payer-details-IPD.html',
							controller : 'MasterController',
						}
					}
				})
				
				.state(
				'ERCharges.depositDetailsForIPDCharge',
				{
					url : '/depositDetailsForIPDCharge',
					views : {
						'@IPDCharges' : {
							templateUrl : 'views/master/UnitMaster/billingMaster/IPDCharge/deposit-details-IPD.html',
							controller : 'MasterController',
						}
					}
				})
				
				

		/**
		 * ***********************END ER
		 * CHARGES********************
		 */
	
				
				
	.state('IPDBilling', {
		 url: '/IPDBilling',
		 templateUrl: 'views/master/UnitMaster/billingMaster/IPD-billing.html',
		 controller: "MasterController"
	})
	
	.state('intermediateBill', {
		 url: '/intermediateBill',
		 templateUrl: 'views/master/UnitMaster/billingMaster/intermediate-bill.html',
		 controller: "MasterController"
	})
	
	
	.state('billEstimation', {
		 url: '/billEstimation',
		 templateUrl: 'views/master/UnitMaster/billingMaster/bill-estimation.html',
		 controller: "MasterController"
	})
	
	.state('packageDefinition', {
		 url: '/packageDefinition',
		 templateUrl: 'views/master/UnitMaster/billingMaster/package-definition.html',
		 controller: "MasterController"
	})
	
	/*.state('insuranceMaster', {
		 url: '/insuranceMaster',
		 templateUrl: 'views/master/UnitMaster/billingMaster/insurance-master.html',
		 controller: "MasterController"
	})*/
	.state('TPAmaster', {
		 url: '/TPAmaster',
		 templateUrl: 'views/master/UnitMaster/billingMaster/TPA-master.html',
		 controller: "MasterController"
	})
	.state('billingGroupMaster', {
		 url: '/billingGroupMaster',
		 templateUrl: 'views/master/UnitMaster/billingMaster/billing-group-master.html',
		 controller: "MasterController"
	})
	.state('corporateMasterUnit', {
		 url: '/corporateMasterUnit',
		 templateUrl: 'views/master/UnitMaster/billingMaster/corporate-master-unit.html',
		 controller: "MasterController"
	})
	
	.state('addNewPackageWithCAP', {
		 url: '/addNewPackageWithCAP',
		 templateUrl: 'views/master/UnitMaster/billingMaster/package-with-CAP.html',
		 controller: "addNewPackageWithCAPController"
	})
	/******** Package With CAP main screen , Added on -28/May/2018 start***********************/
	.state('packageWithCAP', {
		 url: '/packageWithCAP',
		 templateUrl: 'views/master/UnitMaster/billingMaster/package-with-CAP-main.html',
		 controller: "packageWithCAPController"
	})
	
	.state('EHCpackage', {
		 url: '/EHCpackage',
		 templateUrl: 'views/master/UnitMaster/billingMaster/EHC-package-main.html',
		 controller: "ehcPackageController"
	})
	.state('addNewEHCpackage', {
		 url: '/addNewEHCpackage',
		 templateUrl: 'views/master/UnitMaster/billingMaster/add-newEHC-package.html',
		 controller: "addNewEHCpackageController"
	})
	
	
	.state('allExclusivePackage', {
		 url: '/allExclusivePackage',
		 templateUrl: 'views/master/UnitMaster/billingMaster/all-exclusive-package-main.html',
		 controller: "allExclusivePackageController"
	})
	.state('addNewAllExclusivePackage', {
		 url: '/addNewAllExclusivePackage',
		 templateUrl: 'views/master/UnitMaster/billingMaster/add-new-all-exclusive-package-with-CAP.html',
		 controller: "AddNewAllInclusivePackageController"
	})
	
	.state('eitherOrPackage', {
		 url: '/eitherOrPackage',
		 templateUrl: 'views/master/UnitMaster/billingMaster/either-or-package-main.html',
		 controller: "eitherOrPackageController"
	})
	.state('addNewEitherOrPackage', {
		 url: '/addNewEitherOrPackage',
		 templateUrl: 'views/master/UnitMaster/billingMaster/add-new-either-or-package.html',
		 controller: "addEitherOrPackageController"
	})
	
	.state('multicounterPackage', {
		 url: '/multicounterPackage',
		 templateUrl: 'views/master/UnitMaster/billingMaster/multicounter-package-main.html',
		 controller: "multicounterPackageController"
	})
	.state('addNewMulticounterPackage', {
		 url: '/addNewMulticounterPackage',
		 templateUrl: 'views/master/UnitMaster/billingMaster/add-new-multicounter-package.html',
		 controller: "addMultiencounterPackage"
	})
	/******** Package With CAP main screen , Added on -28/May/2018 end***********************/
	.state('packageDefEitherOr', {
		 url: '/packageDefEitherOr',
		 templateUrl: 'views/master/UnitMaster/billingMaster/package-definition-eitheror.html',
		 controller: "MasterController"
	})
	.state('allInclusivePackage', {
		 url: '/allInclusivePackage',
		 templateUrl: 'views/master/UnitMaster/billingMaster/all-inclusive-package.html',
		 controller: "MasterController"
	})
	.state('packageList', {
		 url: '/packageList',
		 templateUrl: 'views/master/UnitMaster/billingMaster/package-list.html',
		 controller: "MasterController"
	})
	
	  /************************* Start Variable Pricing *********************/
    	       .state('variableSequence', {
					 url: '/variableSequence',
					 templateUrl: 'views/master/UnitMaster/billingMaster/variable-sequence.html',
					 controller: "MasterController"
				})
    	        .state('variableSquencePricing',
    	       {
    	        url : '/variableSquencePricing',
    	        views :
    	         {
    	          '@' :
    	           {
    	            templateUrl : 'views/master/UnitMaster/billingMaster/variable-squence-pricing.html',
    	            controller : 'MasterController',
    	           }
    	         }
    	       })
    	       
    	      .state('variableSquencePricing.sequencePricing',
  	       {
   	        url : '/sequencePricing',
   	        views :
   	         {
    	          '@variableSquencePricing' :
   	           {
    	            templateUrl : 'views/master/UnitMaster/billingMaster/sequence-of-variable.html',
    	            controller : 'MasterController',
   	           }
    	         }
    	       })
    	       
    	       
    	           .state('variableSquencePricing.variablePricing',
		  	       {
		   	        url : '/variablePricing',
		   	        views :
		   	         {
		    	          '@variableSquencePricing' :
		   	           {
		    	            templateUrl : 'views/master/UnitMaster/billingMaster/variable-pricing.html',
		    	            controller : 'MasterController',
		   	           }
		    	         }
    	       })
    	       
     .state('accountPayableMaster', {
		    url: '/accountPayableMaster',
		    templateUrl: 'views/master/UnitMaster/billingMaster/account-payable-master.html',
		    controller: "MasterController"
		  })	       
	
	.state('accountPayable', {
		 url: '/accountPayable',
		 templateUrl: 'views/master/UnitMaster/billingMaster/account-payable.html',
		 controller: "MasterController"
	})
	
	
	.state('accountPayableComparsion', {
		 url: '/accountPayableComparsion',
		 templateUrl: 'views/master/UnitMaster/billingMaster/account-payable-comparsion.html',
		 controller: "MasterController"
	})
	
	.state('accountPayableStatus', {
		 url: '/accountPayableStatus',
		 templateUrl: 'views/master/UnitMaster/billingMaster/account-payable-status.html',
		 controller: "MasterController"
	})
	
	
	   .state('opdBilling', {
		    url: '/opdBilling/:patientId/:encounterId',
		    templateUrl: 'views/master/UnitMaster/billingMaster/opd-billing.html',
		    controller: "OpdBillingController",
		    params: {
            	encounterObj: {} //default value
            }
		  })
		  
		 /*  .state('opdBill', {
		    url: '/opdBilling',
		    templateUrl: 'views/master/UnitMaster/billingMaster/opd-billing.html',
		    controller: "OpdBillingController"
		  })
		  */
		 .state('paymentMode', {
		    url: '/paymentMode',
		    templateUrl: 'views/common/payment-mode.html',
		    controller: "MasterController"
		  }) 
	
		  
		  
		   .state('opdBilling.cash',
		  	       {
		   	        url : '/cash',
		   	        views :
		   	         {
		    	          '@opdBilling' :
		   	           {
		    	            templateUrl : 'views/common/cash.html',
		    	            controller : 'MasterController',
		   	           }
		    	         }
    	       })
    	       
    	        .state('opdBilling.cheque',
		  	       {
		   	        url : '/cheque',
		   	        views :
		   	         {
		    	          '@opdBilling' :
		   	           {
		    	            templateUrl : 'views/common/cheque.html',
		    	            controller : 'MasterController',
		   	           }
		    	         }
    	       })
    	       
    	       
    	        .state('opdBilling.creditDebitCard',
		  	       {
		   	        url : '/creditDebitCard',
		   	        views :
		   	         {
		    	          '@opdBilling' :
		   	           {
		    	            templateUrl : 'views/common/credit-debit-card.html',
		    	            controller : 'MasterController',
		   	           }
		    	         }
    	       })
    	       
    	 .state('billList', {
		 url: '/billList',
		 templateUrl: 'views/master/UnitMaster/billingMaster/bill-list.html',
		 controller: "billListController"
    	 })
    	 
    	 
    	 .state('billList.groupDetails',
		  	       {url : '/groupDetails',
		   	        views :{
		    	          '@billList' :{
		    	            templateUrl : 'views/master/UnitMaster/billingMaster/common/group-details.html',
		    	            //controller : 'MasterController',
		    	            }}
		  	       	})
		  	       	
		  .state('billList.serviceDetails',
		  	       {url : '/serviceDetails',
		   	        views :{
		    	          '@billList' :{
		    	            templateUrl : 'views/master/UnitMaster/billingMaster/common/service-details.html',
		    	            //controller : 'MasterController',
		    	            }}
		  	       	})
    	 
		  	       	
		 .state('debitNote', {
		 url: '/debitNote',
		 templateUrl: 'views/master/UnitMaster/billingMaster/debit-note.html',
		 controller: "MasterController"
    	 })
    	 
    	 
    	 
		   .state('debitNote.cash',
		  	       {
		   	        url : '/cash',
		   	        views :
		   	         {
		    	          '@debitNote' :
		   	           {
		    	            templateUrl : 'views/common/cash.html',
		    	            controller : 'MasterController',
		   	           }
		    	         }
    	       })
    	       
    	        .state('debitNote.cheque',
		  	       {
		   	        url : '/cheque',
		   	        views :
		   	         {
		    	          '@debitNote' :
		   	           {
		    	            templateUrl : 'views/common/cheque.html',
		    	            controller : 'MasterController',
		   	           }
		    	         }
    	       })
    	       
    	       
    	        .state('debitNote.creditDebitCard',
		  	       {
		   	        url : '/creditDebitCard',
		   	        views :
		   	         {
		    	          '@debitNote' :
		   	           {
		    	            templateUrl : 'views/common/credit-debit-card.html',
		    	            controller : 'MasterController',
		   	           }
		    	         }
    	       })
    	 
    	 
    	  .state('debitNoteList', {
		 url: '/debitNoteList',
		 templateUrl: 'views/master/UnitMaster/billingMaster/debit-list.html',
		 controller: "MasterController"
    	 })
		  	
    	  .state('creditNote', {
		 url: '/creditNote',
		 templateUrl: 'views/master/UnitMaster/billingMaster/credit-note.html',
		 controller: "MasterController"
    	 })
    	 
    	  .state('creditNoteList', {
		 url: '/creditNoteList',
		 templateUrl: 'views/master/UnitMaster/billingMaster/credit-note-list.html',
		 controller: "MasterController"
    	 })
    	       
	/*******************Billing Master End***************** *********/
	/*******************asset Management Start**************************/
	// .state('fixedAssetType', {
	// 	 url: '/fixedAssetType',
	// 	 templateUrl: 'views/master/UnitMaster/assetManagement/fixed-asset-type.html',
	// 	 controller: "MasterController"
	// })
	.state('addfixedAssetMaster', {
		 url: '/addfixedAssetMaster',
		 templateUrl: 'views/master/UnitMaster/assetManagement/add-fixed-asset-master.html',
		 controller: "MasterController"
	})
	.state('addNewfixedAssetMaster', {
		 url: '/addNewfixedAssetMaster',
		 templateUrl: 'views/master/UnitMaster/assetManagement/add-new-fixed-asset-master.html',
		 controller: "MasterController"
	})
	.state('detailsFixedAssetMaster', {
		 url: '/detailsFixedAssetMaster',
		 templateUrl: 'views/master/UnitMaster/assetManagement/details-fixed-asset-master.html',
		 controller: "MasterController"
	})
	.state('assetPMSScheduler', {
		 url: '/assetPMSScheduler',
		 templateUrl: 'views/master/UnitMaster/assetManagement/asset-PMS-scheduler.html',
		 controller: "MasterController"
	})
	.state('assetScheduler', {
		 url: '/assetScheduler',
		 templateUrl: 'views/master/UnitMaster/assetManagement/create-asset-scheduler.html',
		 controller: "MasterController"
	})
	.state('viewAssetScheduler', {
		 url: '/viewAssetScheduler',
		 templateUrl: 'views/master/UnitMaster/assetManagement/view-asset-scheduler.html',
		 controller: "MasterController"
	})
	.state('PMRequest', {
		url: '/PMRequest',
		templateUrl: 'views/master/UnitMaster/assetManagement/PM-request.html',
		controller: "MasterController"
	})
	.state('PMSchedule', {
		url: '/PMSchedule',
		templateUrl: 'views/master/UnitMaster/assetManagement/PM-schedule.html',
		controller: "MasterController"
	})
	.state('scheduleWorklist', {
		url: '/scheduleWorklist',
		templateUrl: 'views/master/UnitMaster/assetManagement/schedule-worklist.html',
		controller: "MasterController"
	})
	.state('detailScheduleWorklist', {
		url: '/detailScheduleWorklist',
		templateUrl: 'views/master/UnitMaster/assetManagement/details-schedule-worklist.html',
		controller: "MasterController"
	})
	.state('correctiveMaintenance', {
		url: '/correctiveMaintenance',
		templateUrl: 'views/master/UnitMaster/assetManagement/corrective-maintenance.html',
		controller: "MasterController"
	})
	.state('detailsCorrectiveMaintenance', {
		url: '/detailsCorrectiveMaintenance',
		templateUrl: 'views/master/UnitMaster/assetManagement/details-corrective-maintenance.html',
		controller: "MasterController"
	})
	.state('addNewCorrectiveMaintenance', {
		url: '/addNewCorrectiveMaintenance',
		templateUrl: 'views/master/UnitMaster/assetManagement/add-new-corrective-maintenance-worklist.html',
		controller: "MasterController"
	})
	.state('correctiveMaintenanceWorklist', {
		url: '/correctiveMaintenanceWorklist',
		templateUrl: 'views/master/UnitMaster/assetManagement/corrective-maintenance-worklist.html',
		controller: "MasterController"
	})
	.state('detailsCorrectiveMaintenanceWorklist', {
		url: '/detailsCorrectiveMaintenanceWorklist',
		templateUrl: 'views/master/UnitMaster/assetManagement/details-corrective-maintenance-worklist.html',
		controller: "MasterController"
	})
	.state('correctiveMaintenanceScheduleWorklist', {
		url: '/correctiveMaintenanceScheduleWorklist',
		templateUrl: 'views/master/UnitMaster/assetManagement/corrective-maintenance-schedule-worklist.html',
		controller: "MasterController"
	})
	.state('detailsCorrectiveMaintenanceScheduleWorklist', {
		url: '/detailsCorrectiveMaintenanceScheduleWorklist',
		templateUrl: 'views/master/UnitMaster/assetManagement/details-corrective-maintenance-schedule-worklist.html',
		controller: "MasterController"
	})
	/*******************asset Management End**************************/
	
	.state('appointmentSchedulingUnitMaster', {
	  url: '/appointmentSchedulingUnitMaster',
	  templateUrl: 'views/master/UnitMaster/Scheduling/appointment-scheduling-unit-master.html',
	  controller: "appointmentSchedulingUnitMasterController"
	})
	.state('modalityTypeMaster', {
	  url: '/modalityTypeMaster',
	  templateUrl: 'views/master/UnitMaster/Scheduling/modality-type-master.html',
	  controller: "modalityTypeMasterController"
	})
	.state('modalityMaster', {
	  url: '/modalityMaster',
	  templateUrl: 'views/master/UnitMaster/Scheduling/modality-master.html',
	  controller: "modalityMasterController"
	})
	// .state('consumableBarcodeConfig', {
	// 	 url: '/consumableBarcodeConfig',
	// 	 templateUrl: 'views/master/UnitMaster/assetManagement/consumable-barcode-configuration.html',
	// 	 controller: "MasterController"
	// })
	.state('scheduleWise', {
		url: '/scheduleWise',
		templateUrl: 'views/master/UnitMaster/scheduleBlockUnblock/schedule-wise.html',
		controller: "SlotBlockUnblockControllerScheduleWise"
	})
	.state('doctorWise', {
		url: '/doctorWise',
		templateUrl: 'views/master/UnitMaster/scheduleBlockUnblock/doctor-wise.html',
		controller: "SlotBlockUnblockControllerDoctorWise"
	})
	/********************************************** End Unit master*********************************************/
	/******************* Mortuary Start  **************************/
		.state('mortuaryBedMaster', {
		 url: '/mortuaryBedMaster',
		 templateUrl: 'views/master/UnitMaster/Mortuary/Mortuary-Bed-Master.html',
		 controller: "mortuaryBedMasterController"
	})
	/******************* Mortuary Start  **************************/
	/******************* Media Master  **************************/
	.state('mediaMaster', {
		 url: '/mediaMaster',
		 templateUrl: 'views/LIS/media-master.html',
		 controller: "MasterController"
	})
	.state('incubationTestMaster', {
		 url: '/incubationTestMaster',
		 templateUrl: 'views/LIS/incubation-test-master.html',
		 controller: "MasterController"
	})
	.state('sampleTypeMediaMappingMaster', {
		 url: '/sampleTypeMediaMappingMaster',
		 templateUrl: 'views/LIS/sampletype-media-mapping-master.html',
		 controller: "sampleTypeMediaMapperAdditionController"
	})
	/******************* Media Master  **************************/
	  
	
	
	/*.state('unknownPatientReg', {
	    url: '/unknownPatientReg',
	    templateUrl: 'views/AdmissionDischarge/ER/unknown-patient-reg.html',
	    controller: "unknownPatientRegController"
	  })*/
	  
	  
	  .state('unknownPatientReg',
       {
        url : '/unknownPatientReg',
        views :
         {
          '@' :
           {
            templateUrl : 'views/AdmissionDischarge/ER/unknown-patient-reg.html',
            controller : 'unknownPatientRegController',
           }
         }
       })
       
      .state('unknownPatientReg.personalForUnknown',
       {
        url : '/personalForUnknown',
        views :
         {
          '@unknownPatientReg' :
           {
            templateUrl : 'views/AdmissionDischarge/ER/unknown-personal.html',
            controller : 'unknownPatientRegController',
           }
         }
       })
       
        .state('unknownPatientReg.nextKinDetailsForUnknown',
       {
        url : '/nextKinDetailsForUnknown',
        views :
         {
          '@unknownPatientReg' :
           {
            templateUrl : 'views/AdmissionDischarge/next-of-kin.html',
            controller : 'unknownPatientRegController',
           }
         }
       })
	  
	
	/************************* Start Patient Registration *********************/
		  
	.state('patientRegistration',
       {
        url : '/patientRegistration',
        views :
         {
          '@' :
           {
            templateUrl : 'views/AdmissionDischarge/patient-registration.html',
            controller : 'patientRegistrationController',
           }
         }
       })
       
      .state('patientRegistration.personal',
       {
        url : '/personal',
        views :
         {
          '@patientRegistration' :
           {
            templateUrl : 'views/AdmissionDischarge/personal-registration.html',
            //controller : 'patientRegistrationController',
           }
         }
       })
       
       .state('patientRegistration.contact',
       {
        url : '/contact',
        views :
         {
          '@patientRegistration' :
           {
            templateUrl : 'views/AdmissionDischarge/contact-registration.html',
           // controller : 'patientRegistrationController',
           }
         }
       })
       
       .state('patientRegistration.company',
       {
        url : '/company',
        views :
         {
          '@patientRegistration' :
           {
            templateUrl : 'views/AdmissionDischarge/company-patient-registration.html',
           // controller : 'patientRegistrationController',
           }
         }
       })
       /************************* End Patient Registration *********************/
       
       /************************* Start Staff Registration *********************/
       
       /*.state('staffRegistration',
       {
        url : '/staffRegistration',
        views :
         {
          '@' :
           {
            templateUrl : 'views/AdmissionDischarge/staff-registration.html',
            controller : 'MasterController',
           }
         }
       })
       
      .state('staffRegistration.personal',
       {
        url : '/personal',
        views :
         {
          '@staffRegistration' :
           {
            templateUrl : 'views/AdmissionDischarge/personal-registration.html',
            controller : 'MasterController',
           }
         }
       })
       
       .state('staffRegistration.contact',
       {
        url : '/contact',
        views :
         {
          '@staffRegistration' :
           {
            templateUrl : 'views/AdmissionDischarge/contact-registration.html',
            controller : 'MasterController',
           }
         }
       })
       
       
        .state('staffRegistration.professional',
       {
        url : '/professional',
        views :
         {
          '@staffRegistration' :
           {
            templateUrl : 'views/AdmissionDischarge/professional-staff.html',
            controller : 'MasterController',
           }
         }
       })
       
       .state('staffRegistration.employment',
       {
        url : '/employment',
        views :
         {
          '@staffRegistration' :
           {
            templateUrl : 'views/AdmissionDischarge/employment-details.html',
            controller : 'MasterController',
           }
         }
       })
       
        .state('staffRegistration.dependentDetails',
       {
        url : '/dependentDetails',
        views :
         {
          '@staffRegistration' :
           {
            templateUrl : 'views/AdmissionDischarge/next-of-kin.html',
            controller : 'MasterController',
           }
         }
       })*/
       
       
       
       /************************* End Staff Registration *********************/
       
       
       /************************* Start Doctor  Registration *********************/
      /* .state('doctorRegistration',
       {
        url : '/doctorRegistration',
        views :
         {
          '@' :
           {
            templateUrl : 'views/AdmissionDischarge/doctor-registration.html',
            controller : 'doctorRegistrationController',
           }
         }
       })
       
      .state('doctorRegistration.personal',
       {
        url : '/personal',
        views :
         {
          '@doctorRegistration' :
           {
            templateUrl : 'views/AdmissionDischarge/personal-registration.html',
            controller : 'MasterController',
           }
         }
       })
       
        .state('doctorRegistration.contact',
       {
        url : '/contact',
        views :
         {
          '@doctorRegistration' :
           {
            templateUrl : 'views/AdmissionDischarge/contact-registration.html',
            controller : 'MasterController',
           }
         }
       })
       .state('doctorRegistration.professionalDoctor',
    	       {
    	        url : '/professionalDoctor',
    	        views :
    	         {
    	          '@doctorRegistration' :
    	           {
    	            templateUrl : 'views/AdmissionDischarge/professional-doctor.html',
    	            controller : 'MasterController',
    	           }
    	         }
    	       })
    	       
    	       .state('doctorRegistration.employmentDoctor',
    	       {
    	        url : '/employmentDoctor',
    	        views :
    	         {
    	          '@doctorRegistration' :
    	           {
    	            templateUrl : 'views/AdmissionDischarge/employment-details-doctor.html',
    	            controller : 'MasterController',
    	           }
    	         }
    	       })
    	       
    	        .state('doctorRegistration.nextOfKin',
    	       {
    	        url : '/nextOfKin',
    	        views :
    	         {
    	          '@doctorRegistration' :
    	           {
    	            templateUrl : 'views/AdmissionDischarge/next-of-kin.html',
    	            controller : 'MasterController',
    	           }
    	         }
    	       })*/
    	        
       
       /************************* End  Doctor Registration *********************/
    	       
     /************************* Start Appointment Scheduling *********************/
    	       .state('appointmentScheduling',
    	       {
    	        url : '/appointmentScheduling',
    	        views :
    	         {
    	          '@' :
    	           {
    	            templateUrl : 'views/AppointmentScheduling/appointment-scheduling.html',
    	            controller : 'appointmentSchedulingController',
    	           }
    	         }
    	       })
    	       
    	     .state('appointmentScheduling.year',
    	       {
    	        url : '/year',
    	        views :
    	         {
    	          '@appointmentScheduling' :
    	           {
    	            templateUrl : 'views/AppointmentScheduling/year-slot.html',
    	            //controller : 'appointmentSchedulingController',
    	           }
    	         }
    	       })
    	       
    	       .state('appointmentScheduling.month',
    	       {
    	        url : '/month',
    	        views :
    	         {
    	          '@appointmentScheduling' :
    	           {
    	            templateUrl : 'views/AppointmentScheduling/month-slot.html',
    	            //controller : 'appointmentSchedulingController',
    	           }
    	         }
    	       })
    	       
    	       
    	        .state('appointmentScheduling.week',
    	       {
    	        url : '/week',
    	        views :
    	         {
    	          '@appointmentScheduling' :
    	           {
    	            templateUrl : 'views/AppointmentScheduling/week-slot.html',
    	            controller : 'MasterController',
    	           }
    	         }
    	       })
    	       
    	       
    	         .state('appointmentScheduling.day',
    	       {
    	        url : '/day',
    	        views :
    	         {
    	          '@appointmentScheduling' :
    	           {
    	            templateUrl : 'views/AppointmentScheduling/day-slot.html',
    	            controller : 'MasterController',
    	           }
    	         }
    	       })
    	      
    	       
    	       /************************* End Appointment Scheduling *********************/
    	       
    	       
    	       /************************* Start Appointment Scheduling *********************/
    	       
    	        .state('encounter',
    	       {
    	        url : '/encounter',
    	        views :
    	         {
    	          '@' :
    	           {
    	            templateUrl : 'views/master/GlobalMaster/Encounter/encounter.html',
    	            controller : 'EncounterController',
    	           }
    	         },
    	        params: {
	            	appointmentObj: {} //default value
	            }
    	       })
    	       
    	      .state('encounter.encounterDetails',
  	       {
   	        url : '/encounterDetails',
   	        views :
   	         {
    	          '@encounter' :
   	           {
    	            templateUrl : 'views/master/GlobalMaster/Encounter/encounter-details.html',
    	            //controller : 'EncounterController',
   	           }
    	         }
    	       })
    	       
   	        .state('encounter.nextOfKin',
   	       {
    	        url : '/nextOfKin',
    	        views :
   	         {
    	          '@encounter' :
    	           {
    	            templateUrl : 'views/AdmissionDischarge/next-of-kin.html',
    	            //controller : 'EncounterController',
    	           }
    	         }
    	       })
    	       
    	       
    	       
    	       .state('encounter.paymentEntitlement',
   	       {
    	        url : '/paymentEntitlement',
    	        views :
    	         {
   	          '@encounter' :
    	           {
    	            templateUrl : 'views/master/GlobalMaster/Encounter/paymentEntitlement.html',
     	            //controller : 'MasterController',
     	           }
     	         }
    	     
   	       })
    	       /************************* End Appointment Scheduling *********************/
    	       
    	       
    	       
	/******************* Asset Management  **************************/
	.state('auctionRequest', {
		 url: '/auctionRequest',
		 templateUrl: 'views/Asset Management/auction-request.html',
		 controller: "MasterController"
	})
	.state('auctionReqDetails', {
		 url: '/auctionReqDetails',
		 templateUrl: 'views/Asset Management/auction-request-details.html',
		 controller: "MasterController"
	})
	.state('itemsForAuction', {
		 url: '/itemsForAuction',
		 templateUrl: 'views/Asset Management/items-for-auction.html',
		 controller: "MasterController"
	})
	.state('itemsAuctionDetails', {
		 url: '/itemsAuctionDetails',
		 templateUrl: 'views/Asset Management/items-for-auction-details.html',
		 controller: "MasterController"
	})
	.state('auctionVendorApproval', {
		 url: '/auctionVendorApproval',
		 templateUrl: 'views/Asset Management/auction-vendor-approval.html',
		 controller: "MasterController"
	})
	.state('detailsAuctionVendorApproval', {
		 url: '/detailsAuctionVendorApproval',
		 templateUrl: 'views/Asset Management/details-auction-vendor-approval.html',
		 controller: "MasterController"
	})
	.state('nonReturnableGatePass', {
		 url: '/nonReturnableGatePass',
		 templateUrl: 'views/Asset Management/non-returnable-gatepass.html',
		 controller: "MasterController"
	})
	/******************* Asset Management  **************************/
	/******************* Inventory Page Start **************************/

	.state('wasteStore', {
		 url: '/wasteStore',
		 templateUrl: 'views/PharmacyInventory/Store/waste-store.html',
		 controller: "MasterController"
	})

	
	/*******************  Inventory Page End **************************/
    
    
    
     /*******************************************************************************
    * END Added BY Jyoti
    Date - 04-10-2017
    **************************************************************************/
    
/********************************************************************************
* Inventory Page and procurement page start
*Added by Pranita  Date : 06-10-2017
*****************************************************************************************/
	


	.state('addNewItem', {
		 url: '/addNewItem',
		 templateUrl: 'views/PharmacyInventory/Store/add-new-item.html',
		 controller: "MasterController"
	})
	.state('quotationEnquiryApproval', {
		 url: '/quotationEnquiryApproval',
		 templateUrl: 'views/procurement/quotation/quotation-enquiry-approval.html',
		 controller: "MasterController"
	})
	.state('vendorSelection', {
		 url: '/vendorSelection',
		 templateUrl: 'views/procurement/quotation/vendor-selection.html',
		 controller: "MasterController"
	})
	.state('detailsVendorSelection', {
		 url: '/detailsVendorSelection',
		 templateUrl: 'views/procurement/quotation/details-vendor-selection.html',
		 controller: "MasterController"
	})
	/***Added on 25-oct-2017 *****/
	.state('itemWiseMarkUp', {
		 url: '/itemWiseMarkUp',
		 templateUrl: 'views/PharmacyInventory/Store/item-wise-markup.html',
		 controller: "MasterController"
	}) 
	.state('itemLocationAllocation', {
		 url: '/itemLocationAllocation',
		 templateUrl: 'views/PharmacyInventory/Store/item-location-allocation.html',
		 controller: "MasterController"
	})
	.state('ETOitemConfig', {
		 url: '/ETOitemConfig',
		 templateUrl: 'views/PharmacyInventory/Store/ETO-item-config.html',
		 controller: "MasterController"
	})
	// .state('POCreation', {
	//    url: '/POCreation',
	//    templateUrl: 'views/procurement/quotation/PO-creation.html',
	//    controller: "MasterController"
	//  })
	 .state('detailsPOApprovalAssets', {
	   url: '/detailsPOApprovalAssets',
	   templateUrl: 'views/procurement/quotation/details-PO-creation-assets.html',
	   controller: "MasterController"
	 })
	 .state('detailsPOApprovalConsumable', {
	   url: '/detailsPOApprovalConsumable',
	   templateUrl: 'views/procurement/quotation/details-PO-creation-consumables.html',
	   controller: "MasterController"
	 })
	 .state('detailsGRNAssets', {
	   url: '/detailsGRNAssets',
	   templateUrl: 'views/procurement/quotation/details-GRN-assets.html',
	   controller: "MasterController"
	 })
	 .state('detailsGRNConsumable', {
	   url: '/detailsGRNConsumable',
	   templateUrl: 'views/procurement/quotation/details-GRN-consumable.html',
	   controller: "MasterController"
	 })
	 .state('addItemPOCreation', {
	   url: '/addItemPOCreation',
	   templateUrl: 'views/procurement/quotation/add-item-PO-creation.html',
	   controller: "MasterController"
	 })
	 /***************** Added on 17-Nov-2017 start*************************/
	 .state('detailsphysicalInspecAssets', {
	   url: '/detailsphysicalInspecAssets',
	   templateUrl: 'views/procurement/quotation/details-physical-inspection-assets.html',
	   controller: "MasterController"
	 })
	 .state('detailsphysicalInspecConsumables', {
	   url: '/detailsphysicalInspecConsumables',
	   templateUrl: 'views/procurement/quotation/details-physical-inspection-consumables.html',
	   controller: "MasterController"
	 })
	 /***************** Added on 17-Nov-2017 end*************************/
	 // .state('addItemPO', {
	 //   url: '/addItemPO',
	 //   templateUrl: 'views/procurement/quotation/add-item-PO-creation.html',
	 //   controller: "MasterController"
	 // })
	 /*****quotation Enquiry Details for design use quotationEDetails for development ******/
	  .state('quotationEArrovalDetails', {
	   url: '/quotationEArrovalDetails',
	   templateUrl: 'views/procurement/quotation/quotation-enquiry-approval-details.html',
	   controller: "MasterController"
	 })
		 
	.state('holdOnFillInDetails', {
		   url: '/holdOnFillInDetails',
		   templateUrl: 'views/procurement/quotation/quotation-fillinHoldon-details.html',
		   controller: "MasterController"
		 })
	 /***************************** Diet and Nutrition ***************************************/
		.state('nutritionalMaster', {
			 url: '/nutritionalMaster',
			 templateUrl: 'views/master/GlobalMaster/DietNutrition/nutritional-master.html',
			 controller: "nutritionalMasterController"
		})
		.state('keyIngredientsMaster', {
			 url: '/keyIngredientsMaster',
			 templateUrl: 'views/master/GlobalMaster/DietNutrition/KeyIngredients-master.html',
			 controller: "keyIngredientsMasterController"
		})
		.state('dietTypeMaster', {
			 url: '/dietTypeMaster',
			 templateUrl: 'views/master/GlobalMaster/DietNutrition/diet-typemaster.html',
			 controller: "dietTypeMasterController"
		})
		.state('dishMaster', {
			 url: '/dishMaster',
			 templateUrl: 'views/master/GlobalMaster/DietNutrition/dish-master.html',
			 controller: "MasterController"
		})
		.state('mealMaster', {
			 url: '/mealMaster',
			 templateUrl: 'views/master/GlobalMaster/DietNutrition/meal-master.html',
			 controller: "MasterController"
		})
		.state('daywiseDietTemplate', {
			 url: '/daywiseDietTemplate',
			 templateUrl: 'views/master/GlobalMaster/DietNutrition/daywise-diet-template.html',
			 controller: "MasterController"
		})
		.state('addnewdaywiseDietTemplate', {
			 url: '/addnewdaywiseDietTemplate',
			 templateUrl: 'views/master/GlobalMaster/DietNutrition/addnewdaywise-diet-template.html',
			 controller: "MasterController"
		})
		.state('dietPatientRequest', {
			 url: '/dietPatientRequest',
			 templateUrl: 'views/master/GlobalMaster/DietNutrition/diet-patient-request.html',
			 controller: "MasterController"
		})
		.state('dietOrder', {
			 url: '/dietOrder',
			 templateUrl: 'views/master/GlobalMaster/DietNutrition/diet-order.html',
			 controller: "MasterController"
		})
		.state('dietIssue', {
			 url: '/dietIssue',
			 templateUrl: 'views/master/GlobalMaster/DietNutrition/diet-issue.html',
			 controller: "MasterController"
		})
		.state('dietAcceptance', {
			 url: '/dietAcceptance',
			 templateUrl: 'views/master/GlobalMaster/DietNutrition/diet-acceptance.html',
			 controller: "MasterController"
		})
		.state('rejectedDietList', {
			 url: '/rejectedDietList',
			 templateUrl: 'views/master/GlobalMaster/DietNutrition/rejected-diet-list.html',
			 controller: "MasterController"
		})
		

		/*.state('transferRequest', {
			 url: '/transferRequest',
			 templateUrl: 'views/AdmissionDischarge/transfer-request.html',
			 controller: "transferRequestController"
		})*/
		
		
		 .state('transferRequest',
    	       {
    	        url : '/transferRequest',
    	        views :
    	         {
    	          '@' :
    	           {
    	            templateUrl : 'views/AdmissionDischarge/transfer-request.html',
    	            controller : 'transferRequestController',
    	           }
    	         }
    	       })
    	       
    	      .state('transferRequest.initiateRequestTransferBed',
  	       {
   	        url : '/initiateRequestTransferBed',
   	        views :
   	         {
    	          '@transferRequest' :
   	           {
    	            templateUrl : 'views/AdmissionDischarge/transfer-bed-initiate-request.html',
    	            controller : 'transferRequestController',
   	           }
    	         }
    	       })
    	       
    	       .state('transferRequest.physicalTransferBed',
  	       {
   	        url : '/physicalTransferBed',
   	        views :
   	         {
    	          '@transferRequest' :
   	           {
    	            templateUrl : 'views/AdmissionDischarge/transfer-bed-physical.html',
    	            controller : 'transferRequestController',
   	           }
    	         }
    	       })
    	       
    	       //vivek start
    	       
    	/*         .state('transferForInvestigation', {
	    url: '/transferForInvestigation',
	    templateUrl: 'views/Nursing/transfer-for-investigation.html',
	    controller: "transferForInvestigationController"
	  })*/
	  
    	        .state('transferForInvestigation',
    	       {
    	        url : '/transferForInvestigation',
    	        views :
    	         {
    	          '@' :
    	           {
    	            templateUrl :'views/Nursing/transfer-for-investigation.html',
    	            controller : 'transferForInvestigationController',
    	           }
    	         }
    	       })
    	       
    	      .state('transferForInvestigation.investigationTransferRequest',
  	       {
   	        url : '/investigationTransferRequest',
   	        views :
   	         {
    	          '@transferForInvestigation' :
   	           {
    	            templateUrl : 'views/Nursing/transfer-modality-initiate-request.html',
    	            controller : 'transferForInvestigationController',
   	           }
    	         }
    	       })
    	       
    	       .state('transferForInvestigation.modalityTransferRequestStatus',
  	       {
   	        url : '/modalityTransferRequestStatus',
   	        views :
   	         {
    	          '@transferForInvestigation' :
   	           {
    	            templateUrl : 'views/Nursing/modality-transfer-request-status.html',
    	            controller : 'transferForInvestigationController',
   	           }
    	         }
    	       })
    	       
    	       /*.state('transferToOT', {
	    url: '/transferToOT',
	    templateUrl: 'views/Nursing/transfer-to-OT.html',
	    controller: "transferToOTController"
	  })*/
    	       
    	       .state('transferToOT',
    	       {
    	        url : '/transferToOT',
    	        views :
    	         {
    	          '@' :
    	           {
    	            templateUrl :'views/Nursing/transfer-to-OT.html',
    	            controller : 'transferToOTController',
    	           }
    	         }
    	       })
    	       
    	      .state('transferToOT.OTTransferRequest',
  	       {
   	        url : '/OTTransferRequest',
   	        views :
   	         {
    	          '@transferToOT' :
   	           {
    	            templateUrl : 'views/Nursing/transfer-OT-initiate-request.html',
    	            controller : 'transferToOTController',
   	           }
    	         }
    	       })
    	       
    	       .state('transferToOT.OTTransferRequestStatus',
  	       {
   	        url : '/OTTransferRequestStatus',
   	        views :
   	         {
    	          '@transferToOT' :
   	           {
    	            templateUrl : 'views/Nursing/OT-Transfer-request-status.html',
    	            controller : 'transferToOTController',
   	           }
    	         }
    	       })
    	       
    	       
    	       .state('OTRequest', {
    	   	    url: '/OTRequest',
    	   	    templateUrl: 'views/Nursing/OT-request.html',
    	   	    controller: "OTRequestController"
    	   	  })
    	       
    	       //Vivek End
    	       
    	       
    	       
    	       
    	        .state('initiateBedTransfer',
    	       {
    	        url : '/initiateBedTransfer',
    	        views :
    	         {
    	          '@' :
    	           {
    	            templateUrl : 'views/Nursing/initiate-bed-transfer.html',
    	            controller : 'initiateBedTransferController',
    	           }
    	         }
    	       })
    	       
    	      .state('initiateBedTransfer.initiateRequestTransferBed',
  	       {
   	        url : '/initiateRequestTransferBed',
   	        views :
   	         {
    	          '@initiateBedTransfer' :
   	           {
    	            templateUrl : 'views/Nursing/initiate-request-transfer-bed.html',
    	            controller : 'initiateBedTransferController',
   	           }
    	         }
    	       })
    	       
    	       .state('initiateBedTransfer.physicalInitiateTransferBed',
  	       {
   	        url : '/physicalInitiateTransferBed',
   	        views :
   	         {
    	          '@initiateBedTransfer' :
   	           {
    	            templateUrl : 'views/Nursing/initiate-bed-physical-transfer.html',
    	            controller : 'initiateBedTransferController',
   	           }
    	         }
    	       })
		
    	 
    	       
		
		.state('transferRequestAcceptance', {
			 url: '/transferRequestAcceptance',
			 templateUrl: 'views/AdmissionDischarge/transfer-request-acceptance.html',
			 controller: "transferRequestAcceptanceController"
		})
		
		
	/***************************** Diet and Nutrition ***************************************/
/********************************************************************
* OTMS module page start
*Added by Sid  Date : 04-06-2018
*********************************************************************/
	.state('facultyMaster', {
			 url: '/facultyMaster',
			 templateUrl: 'views/master/GlobalMaster/OTMS/faculty-master.html',
			 controller: "MasterController"
		})
	.state('OTIMSUser', {
			 url: '/OTIMSUser',
			 templateUrl: 'views/master/GlobalMaster/OTMS/OTIMS-user-master.html',
			 controller: "MasterController"
		})
		.state('uomMaster', {
			 url: '/uomMaster',
			 templateUrl: 'views/master/GlobalMaster/OTMS/uom-master.html',
			 controller: "MasterController"
		})
		.state('noteMaster', {
			 url: '/noteMaster',
			 templateUrl: 'views/master/GlobalMaster/OTMS/note-master.html',
			 controller: "noteMasterTemplateController"
		})
		
/*********************************************************************/
/********************************************************************
* OTMS module page start
*Added by Pranita  Date : 01-06-2018
*********************************************************************/
	.state('cancellationMaster', {
		 url: '/cancellationMaster',
		 templateUrl: 'views/master/GlobalMaster/OTIMS/cancellation-reason.html',
		 controller: "MasterController"
	})
	.state('operation_Theatre', {
		 url: '/operation_Theatre',
		 templateUrl: 'views/master/GlobalMaster/OTIMS/operation_Theatre.html',
		 controller: "MasterController"
	})
	.state('procedureType', {
		 url: '/procedureType',
		 templateUrl: 'views/master/GlobalMaster/OTIMS/procedure-type.html',
		 controller: "MasterController"
	})
	.state('operationTheatreType', {
		 url: '/operationTheatreType',
		 templateUrl: 'views/master/GlobalMaster/OTIMS/operation-theatre-type.html',
		 controller: "MasterController"
	})
	.state('operationTable', {
		 url: '/operationTable',
		 templateUrl: 'views/master/GlobalMaster/OTIMS/operation_Table.html',
		 controller: "MasterController"
	})
	.state('operationTableType', {
		 url: '/operationTableType',
		 templateUrl: 'views/master/GlobalMaster/OTIMS/operation_Table_Type.html',
		 controller: "MasterController"
	})
	.state('anaesthesiaType', {
		 url: '/anaesthesiaType',
		 templateUrl: 'views/master/GlobalMaster/OTIMS/anaesthesia_type.html',
		 controller: "MasterController"
	})
	.state('checklistItem', {
		 url: '/checklistItem',
		 templateUrl: 'views/master/GlobalMaster/OTIMS/checklist_master.html',
		 controller: "MasterController"
	})
	.state('checklistMaster', {
		 url: '/checklistMaster',
		 templateUrl: 'views/master/GlobalMaster/OTIMS/checklist_master.html',
		 controller: "MasterController"
	})
	.state('staffRole', {
		 url: '/staffRole',
		 templateUrl: 'views/master/GlobalMaster/OTIMS/staff-role.html',
		 controller: "MasterController"
	})
	.state('consentType', {
		 url: '/consentType',
		 templateUrl: 'views/master/GlobalMaster/OTIMS/consent-type.html',
		 controller: "MasterController"
	})
	.state('inventoryItem', {
		 url: '/inventoryItem',
		 templateUrl: 'views/master/GlobalMaster/OTIMS/inventory-item.html',
		 controller: "MasterController"
	})
	.state('inventoryItemType', {
		 url: '/inventoryItemType',
		 templateUrl: 'views/master/GlobalMaster/OTIMS/inventory-item-type.html',
		 controller: "MasterController"
	})
	.state('equipmentType', {
		 url: '/equipmentType',
		 templateUrl: 'views/master/GlobalMaster/OTIMS/equipment-type.html',
		 controller: "MasterController"
	})
	.state('serviceType', {
		 url: '/serviceType',
		 templateUrl: 'views/master/GlobalMaster/OTIMS/service-type.html',
		 controller: "MasterController"
	})
	.state('insuranceCompany', {
		 url: '/insuranceCompany',
		 templateUrl: 'views/master/GlobalMaster/OTIMS/insurance_company.html',
		 controller: "MasterController"
	})
	.state('typeOfStay', {
		 url: '/typeOfStay',
		 templateUrl: 'views/master/GlobalMaster/OTIMS/type_of_stay.html',
		 controller: "MasterController"
	})
	.state('generalPractitioner', {
		 url: '/generalPractitioner',
		 templateUrl: 'views/master/GlobalMaster/OTIMS/general_practitioner.html',
		 controller: "MasterController"
	})
	.state('procedureConfigurations', {
		 url: '/procedureConfigurations',
		 templateUrl: 'views/master/GlobalMaster/OTIMS/procedure_configurations.html',
		 controller: "MasterController"
	})
/******************* OTMS module page end *************/		
	
/********************************************************************
* Inventory Page and procurement page end
*Added by Pranita  Date : 06-10-2017
*********************************************************************/
 /*****************************Demo Added By Vivek *****************/
		
		.state('transferRequestDemo', {
			 url: '/transferRequestDemo',
			 templateUrl: 'views/Transfer/transfer-request-demo.html',
			 controller: "transferRequestController"
		})
 /****************************Demo End******************************/
	
/***************************** Inventory New Pages start *********************************
 * Added by - Pranita  Date:05/12/2017 
 *******************************************************************************************/		
	
	.state('physicalInventory', {
	 url: '/physicalInventory',
	 templateUrl: 'views/procurement/purchase/physical-inventory.html',
	 controller: "MasterController"
	})
	.state('additemwPhysicalInven', {
	 url: '/additemwPhysicalInven',
	 templateUrl: 'views/procurement/purchase/add-item-physical-inventory.html',
	 controller: "MasterController"
	})
	/************ Added by 7-Dec-2017 ************/
	.state('workorderRequest', {
	 url: '/workorderRequest',
	 templateUrl: 'views/procurement/workOrder/workorder-request.html',
	 controller: "MasterController"
	})
	.state('addItemWorkorder', {
	 url: '/addItemWorkorder',
	 templateUrl: 'views/procurement/workOrder/add-item-workorder-request.html',
	 controller: "MasterController"
	})
	.state('workorderQuotationSelect', {
	 url: '/workorderQuotationSelect',
	 templateUrl: 'views/procurement/workOrder/workorder-quotation-selection.html',
	 controller: "MasterController"
	})
	.state('detailsQuotationSelect', {
	 url: '/detailsQuotationSelect',
	 templateUrl: 'views/procurement/workOrder/details-workorder-quotation-selection.html',
	 controller: "MasterController"
	})
	.state('workorderQuotationRequest', {
	 url: '/workorderQuotationRequest',
	 templateUrl: 'views/procurement/workOrder/workorder-quotation-request.html',
	 controller: "MasterController"
	})
	.state('workorderQuotationEntry', {
	 url: '/workorderQuotationEntry',
	 templateUrl: 'views/procurement/workOrder/workorder-quotation-entry.html',
	 controller: "MasterController"
	})
	.state('addItemWorkorderQEntry', {
	 url: '/addItemWorkorderQEntry',
	 templateUrl: 'views/procurement/workOrder/add-item-workorder-quotation-entry.html',
	 controller: "MasterController"
	})
	.state('workorderCreation', {
	 url: '/workorderCreation',
	 templateUrl: 'views/procurement/workOrder/workorder-creation.html',
	 controller: "MasterController"
	})
	.state('addItemWorkorderCreation', {
	 url: '/addItemWorkorderCreation',
	 templateUrl: 'views/procurement/workOrder/add-item-workorder-creation.html',
	 controller: "MasterController"
	})
	.state('workorderApprovalWorklist', {
		 url: '/workorderApprovalWorklist',
		 templateUrl: 'views/procurement/workOrder/workorder-approval-worklist.html',
		 controller: "MasterController"
	})

	.state('drugRecall', {
		url: '/drugRecall',
		templateUrl: 'views/PharmacyInventory/Store/drug-recall.html',
		controller: "MasterController"
	})
	.state('AddNewDrugRecall', {
		url: '/AddNewDrugRecall',
		templateUrl: 'views/PharmacyInventory/Store/add-new-drug-recall.html',
		controller: "MasterController"
	})
	.state('detailsDrugRecall', {
		url: '/detailsDrugRecall',
		templateUrl: 'views/PharmacyInventory/Store/details-drug-recall.html',
		controller: "MasterController"
	})
	.state('recallDrugs', {
		url: '/recallDrugs',
		templateUrl: 'views/PharmacyInventory/Store/recall-drugs.html',
		controller: "MasterController"
	})
	.state('materialInwards', {
		url: '/materialInwards',
		templateUrl: 'views/PharmacyInventory/Store/material-inwords.html',
		controller: "MasterController"
	})
	.state('addItemMaterialInwards', {
		url: '/addItemMaterialInwards',
		templateUrl: 'views/PharmacyInventory/Store/add-item-material-inwords.html',
		controller: "MasterController"
	})
	.state('detailsMaterialInwards', {
		url: '/detailsMaterialInwards',
		templateUrl: 'views/PharmacyInventory/Store/details-material-inwords.html',
		controller: "MasterController"
	})
	.state('materialInwardsApproval', {
		url: '/materialInwardsApproval',
		templateUrl: 'views/PharmacyInventory/Store/material-inwords-approval.html',
		controller: "MasterController"
	})
	.state('detailsMaterialInwardsApproval', {
		url: '/detailsMaterialInwardsApproval',
		templateUrl: 'views/PharmacyInventory/Store/details-material-inwords-approval.html',
		controller: "MasterController"
	})


	.state('addItemRequestForAssestDisposalWorklist', {
		url: '/addItemRequestForAssestDisposalWorklist',
		templateUrl: 'views/PharmacyInventory/Store/add-item-request-for-assest-disposal-worklist.html',
		controller: "MasterController"
	})
	

	.state('addItemWasteAssestDisposalWorklist', {
		url: '/addItemWasteAssestDisposalWorklist',
		templateUrl: 'views/PharmacyInventory/Store/add-item-waste-disposal-worklist.html',
		controller: "MasterController"
	})
	.state('scheduleStatus', {
		url: '/scheduleStatus',
		templateUrl: 'views/Nursing/schedule-status.html',
		controller: "scheduleStatusController"
	})
	
	.state('assetsSupportStatus', {
	  url: '/assetsSupportStatus',
	  templateUrl: 'views/PharmacyInventory/Store/assets-support-status.html',
	  controller: "MasterController"
	 })
	 
	 /************* added on consignmnet module 18-May-2018 start **********/
	 .state('consignmentInward', {
	   url: '/consignmentInward',
	   templateUrl: 'views/procurement/consignment/consignment-inward.html',
	   controller: "MasterController"
	 })
	  .state('addNewConsignmentInward', {
	   url: '/addNewConsignmentInward',
	   templateUrl: 'views/procurement/consignment/add-new-consignment-inward.html',
	   controller: "MasterController"
	 })
	 .state('detailsConsignmentInward', {
	   url: '/detailsConsignmentInward',
	   templateUrl: 'views/procurement/consignment/details-consignment-inward.html',
	   controller: "MasterController"
	 })
	 
	 
	  .state('consignmentOutward', {
	   url: '/consignmentOutward',
	   templateUrl: 'views/procurement/consignment/consignment-outward.html',
	   controller: "MasterController"
	 })
	  .state('addNewConsignmentOutward', {
	   url: '/addNewConsignmentOutward',
	   templateUrl: 'views/procurement/consignment/add-new-consignment-outward.html',
	   controller: "MasterController"
	 })
	 .state('detailsConsignmentOutward', {
	   url: '/detailsConsignmentOutward',
	   templateUrl: 'views/procurement/consignment/details-consignment-outward.html',
	   controller: "MasterController"
	 })
	 /************* added on consignmnet module 17-May-2018 end **********/
	 /************* added on Loan In Out module 18-May-2018 start **********/
	 .state('loanIn', {
	   url: '/loanIn',
	   templateUrl: 'views/procurement/consignment/loan-in.html',
	   controller: "MasterController"
	 })
	  .state('addNewLoanIn', {
	   url: '/addNewLoanIn',
	   templateUrl: 'views/procurement/consignment/add-new-loan-in.html',
	   controller: "MasterController"
	 })
	 .state('detailsLoanIn', {
	   url: '/detailsLoanIn',
	   templateUrl: 'views/procurement/consignment/details-loan-in.html',
	   controller: "MasterController"
	 })
	 
	 .state('loanOut', {
	   url: '/loanOut',
	   templateUrl: 'views/procurement/consignment/loan-out.html',
	   controller: "MasterController"
	 })
	  .state('addNewLoanOut', {
	   url: '/addNewLoanOut',
	   templateUrl: 'views/procurement/consignment/add-new-loan-out.html',
	   controller: "MasterController"
	 })
	 .state('detailsLoanOut', {
	   url: '/detailsLoanOut',
	   templateUrl: 'views/procurement/consignment/details-loan-out.html',
	   controller: "MasterController"
	 })
	 
	 .state('loanInOut', {
	   url: '/loanInOut',
	   templateUrl: 'views/procurement/consignment/loan-in-out.html',
	   controller: "MasterController"
	 })
	  .state('addNewLoanInOut', {
	   url: '/addNewLoanInOut',
	   templateUrl: 'views/procurement/consignment/add-new-loan-in-out.html',
	   controller: "MasterController"
	 })
	 .state('detailsLoanInOut', {
	   url: '/detailsLoanInOut',
	   templateUrl: 'views/procurement/consignment/details-loan-in-out.html',
	   controller: "MasterController"
	 })
	 
	 .state('loanOutReceive', {
	   url: '/loanOutReceive',
	   templateUrl: 'views/procurement/consignment/loan-out-receive.html',
	   controller: "MasterController"
	 })
	  .state('addNewLoanOutReceive', {
	   url: '/addNewLoanOutReceive',
	   templateUrl: 'views/procurement/consignment/add-new-loan-out-receive.html',
	   controller: "MasterController"
	 })
	 .state('detailsLoanOutReceive', {
	   url: '/detailsLoanOutReceive',
	   templateUrl: 'views/procurement/consignment/details-loan-out-receive.html',
	   controller: "MasterController"
	 })
	 /************* added on Loan In Out module 18-May-2018 start **********/
	//
	
	/*  
	.state('appointments', {
		url: '/appointments',
		templateUrl: 'views/common/appointments.html',
		controller: "MasterController"
	})*/
		
		
		 .state('appointments',
    	       {
    	        url : '/appointments',
    	        views :
    	         {
    	          '@' :
    	           {
    	        	  templateUrl: 'views/common/appointments/appointments.html',
    	            controller : 'MasterController',
    	           }
    	         }
    	       })
    	       
    	       .state('appointments.home',
  	       {
   	        url : '/home',
   	        views :
   	         {
    	          '@appointments' :
   	           {
    	            templateUrl : 'views/common/appointments/appointments-home.html',
    	            controller : 'MasterController',
   	           }
    	         }
    	       })
    	       
    	      .state('appointments.emergency',
  	       {
   	        url : '/emergency',
   	        views :
   	         {
    	          '@appointments' :
   	           {
    	            templateUrl : 'views/common/appointments/appointments-emergency.html',
    	            controller : 'MasterController',
   	           }
    	         }
    	       })
    	       
    	       .state('appointments.services',
  	       {
   	        url : '/services',
   	        views :
   	         {
    	          '@appointments' :
   	           {
    	            templateUrl : 'views/common/appointments/appointment-services.html',
    	            controller : 'bedManagementController',
   	           }
    	         }
    	       })
    	       
    	      .state('appointments.home.self', {
			        url: '/self',
			        //templateUrl: 'views/common/appointments/appointments-insurance.html',
			    })
    	       .state('appointments.home.insurance', {
			        url: '/insurance',
			        templateUrl: 'views/common/appointments/appointments-insurance.html',
			    })
			    .state('appointments.home.creditCompany', {
			        url: '/creditCompany',
			        templateUrl: 'views/common/appointments/appointments-credit-company.html',
			    })
			    
			    .state('appointments.services.self', {
			        url: '/self',
			        //templateUrl: 'views/common/appointments/appointments-insurance.html',
			    })
			     .state('appointments.services.insurance', {
			        url: '/insurance',
			        templateUrl: 'views/common/appointments/appointments-insurance.html',
			    })
			    .state('appointments.services.creditCompany', {
			        url: '/creditCompany',
			        templateUrl: 'views/common/appointments/appointments-credit-company.html',
			    })
			    
			    /*appointment list*/
	    	       
	    	       .state('appointmentList',
		  	       {
		   	        url : '/appointmentList',
		   	        views :
		   	         {
		    	          '@' :
		   	           {
		    	        	templateUrl : 'views/common/appointments/appointment-list.html',
		    	        	
		    	            controller : 'appointmentListController',
		   	           }
		    	         }
		    	       })
	    	       
	    	       
	    	       .state('appointmentList.home',
		  	       {
		   	        url : '/home',
		   	        views :
		   	         {
		    	          '@appointmentList' :
		   	           {
		    	        	templateUrl : 'views/common/appointments/appointment-list-home.html',
		    	            controller : 'appointmentListController',
		   	           }
		    	         }
		    	       })
	    	       
	    	       
	    	       .state('appointmentList.services',
		  	       {
		   	        url : '/Services',
		   	        views :
		   	         {
		    	          '@appointmentList' :
		   	           {
		    	        	  templateUrl : 'views/common/appointments/appointment-list-services.html',
		    	            controller : 'appointmentListController',
		   	           }
		    	         }
		    	       })
	    	       
	    	       
	    	       .state('appointmentList.missedcall',
		  	       {
		   	        url : '/Missedcall',
		   	        views :
		   	         {
		    	          '@appointmentList' :
		   	           {
		    	        	  templateUrl : 'views/common/appointments/appointment-list-missedcall.html',
		    	            controller : 'appointmentListController',
		   	           }
		    	         }
		    	       })
	//
	
/***************************** Inventory New Pages end **********************************/
/************************* Bed management - MEWS modal tabs Start*********************/
			      .state('bedManagement.MEWShistory',
			       {
			        url : '/MEWShistory',
			        views :
			         {
			          '@bedManagement' :
			           {
			            templateUrl : 'views/bedManagement/MEWS-history.html',
			            controller : 'MasterController',
			           }
			         }
			       })
			       .state('bedManagement.MEWSnew',
			       {
			        url : '/MEWSnew',
			        views :
			         {
			          '@bedManagement' :
			           {
			            templateUrl : 'views/bedManagement/MEWS-new.html',
			            controller : 'MasterController',
			           }
			         }
			       })
/************************* Bed management - MEWS modal tabs end*********************/		
/************************* Bed management - GCS modal tabs Start*********************/
			      .state('bedManagement.GCShistory',
			       {
			        url : '/GCShistory',
			        views :
			         {
			          '@bedManagement' :
			           {
			            templateUrl : 'views/bedManagement/GCS-history.html',
			            controller : 'MasterController',
			           }
			         }
			       })
			       .state('bedManagement.GCSnew',
			       {
			        url : '/GCSnew',
			        views :
			         {
			          '@bedManagement' :
			           {
			            templateUrl : 'views/bedManagement/GCS-new.html',
			            controller : 'MasterController',
			           }
			         }
			       })
/************************* Bed management - GCS modal tabs end*********************/		
/************************* quotation Enquiry - Expected Terms modal tabs Start*********************/
			      .state('addItemquotationEnquiry.warrantyPeriod',
			       {
			        url : '/warrantyPeriod',
			        views :
			         {
			          '@addItemquotationEnquiry' :
			           {
			            templateUrl : 'views/procurement/quotation/warranty-period.html',
			            controller : 'MasterController',
			           }
			         }
			       })
			       .state('addItemquotationEnquiry.expectedSupportTerms',
			       {
			        url : '/expectedSupportTerms',
			        views :
			         {
			          '@addItemquotationEnquiry' :
			           {
			            templateUrl : 'views/procurement/quotation/expected-support-terms.html',
			            controller : 'MasterController',
			           }
			         }
			       })
			       .state('addItemquotationEnquiry.expectedPaymentTerms',
			       {
			        url : '/expectedPaymentTerms',
			        views :
			         {
			          '@addItemquotationEnquiry' :
			           {
			            templateUrl : 'views/procurement/quotation/expected-payment-terms.html',
			            controller : 'MasterController',
			           }
			         }
			       })
			        .state('addItemquotationEnquiry.expectedTechnicalSpec',
			       {
			        url : '/expectedTechnicalSpec',
			        views :
			         {
			          '@addItemquotationEnquiry' :
			           {
			            templateUrl : 'views/procurement/quotation/expected-technical-specification.html',
			            controller : 'MasterController',
			           }
			         }
			       })
/*********************************************************************/
			        .state('quotationEDetails.warrantyPeriod',
			       {
			        url : '/warrantyPeriod',
			        views :
			         {
			          '@quotationEDetails' :
			           {
			            templateUrl : 'views/procurement/quotation/warranty-period.html',
			            controller : 'MasterController',
			           }
			         }
			       })
			       .state('quotationEDetails.expectedSupportTerms',
			       {
			        url : '/expectedSupportTerms',
			        views :
			         {
			          '@quotationEDetails' :
			           {
			            templateUrl : 'views/procurement/quotation/expected-support-terms.html',
			            controller : 'MasterController',
			           }
			         }
			       })
			       .state('quotationEDetails.expectedPaymentTerms',
			       {
			        url : '/expectedPaymentTerms',
			        views :
			         {
			          '@quotationEDetails' :
			           {
			            templateUrl : 'views/procurement/quotation/expected-payment-terms.html',
			            controller : 'MasterController',
			           }
			         }
			       })
			        .state('quotationEDetails.expectedTechnicalS',
			       {
			        url : '/expectedTechnicalS',
			        views :
			         {
			          '@addItemquotationEnquiry' :
			           {
			            templateUrl : 'views/procurement/quotation/expected-technical-specification.html',
			            controller : 'MasterController',
			           }
			         }
			       })
/************************* quotation Enquiry - Expected Terms modal tabs end*********************/	
/************************* quotation Fill in - Expected Terms modal tabs Start*********************/
			      .state('addNewquotationFillIn.warrantyPeriodFillin',
			       {
			        url : '/warrantyPeriodFillin',
			        views :
			         {
			          '@addNewquotationFillIn' :
			           {
			           templateUrl : 'views/procurement/quotation/warranty-period-fillin.html',
			            controller : 'MasterController',
			           }
			         }
			       })
			       
			       .state('addNewquotationFillIn.expectedSupportTermsfillIn',
			       {
			        url : '/expectedSupportTermsfillIn',
			        views :
			         {
			          '@addNewquotationFillIn' :
			           {
			            templateUrl : 'views/procurement/quotation/expected-support-terms-fillin.html',
			            controller : 'MasterController',
			           }
			         }
			       })
			       .state('addNewquotationFillIn.expectedPaymentTermsfillIn',
			       {
			        url : '/expectedPaymentTermsfillIn',
			        views :
			         {
			          '@addNewquotationFillIn' :
			           {
			            templateUrl : 'views/procurement/quotation/expected-payment-terms-fillin.html',
			            controller : 'MasterController',
			           }
			         }
			       })
			        .state('addNewquotationFillIn.expectedTechnicalSfillIn',
			       {
			        url : '/expectedTechnicalSfillIn',
			        views :
			         {
			          '@addNewquotationFillIn' :
			           {
			            templateUrl : 'views/procurement/quotation/expected-technical-specification-fillin.html',
			            controller : 'MasterController',
			           }
			         }
			       })
			        .state('qfillInDetails.warrantyPFillin',
			       {
			        url : '/warrantyPFillin',
			        views :
			         {
			          '@qfillInDetails' :
			           {
			           templateUrl : 'views/procurement/quotation/warranty-period-fillinDetails.html',
			            controller : 'MasterController',
			           }
			         }
			       })
			        .state('qfillInDetails.expectedSTermsfillIn',
			       {
			        url : '/expectedSTermsfillIn',
			        views :
			         {
			          '@qfillInDetails' :
			           {
			            templateUrl : 'views/procurement/quotation/expected-support-terms-fillinDetails.html',
			            controller : 'MasterController',
			           }
			         }
			       })
			       .state('qfillInDetails.expectedPaymentTfillIn',
			       {
			        url : '/expectedPaymentTfillIn',
			        views :
			         {
			          '@qfillInDetails' :
			           {
			            templateUrl : 'views/procurement/quotation/expected-payment-terms-fillinDetails.html',
			            controller : 'MasterController',
			           }
			         }
			       })
			        .state('qfillInDetails.expectedTSpecificationfillIn',
			       {
			        url : '/expectedTSpecificationfillIn',
			        views :
			         {
			          '@qfillInDetails' :
			           {
			            templateUrl : 'views/procurement/quotation/expected-technical-specification-fillinDetails.html',
			            controller : 'MasterController',
			           }
			         }
			       })
/************************* quotation Fill in - Expected Terms modal tabs End*********************/     
   /*******************************************************************************
* Start Added BY Jyoti
Date - 20-12-2017
**************************************************************************/
  /********************************************** START OPD COVERSHEET *********************************************/
 
  .state('OPDCoverSheet', {
			 url: '/OPDCoverSheet',
			 templateUrl: 'views/OPD/CoverSheet/opd-coversheet.html',
			 controller: "OPDCoverSheetController",
			 params: {
		        	appointmentObj: {} //default value
		        }
		})
 
		.state('adviseMedication', {
			 url: '/adviseMedication',
			 templateUrl: 'views/OPD/advise-medication.html',
			 controller: "MasterController"
		})
		
		.state('interconsultation', {
			 url: '/interconsultation',
			 templateUrl: 'views/OPD/inter-consultation.html',
			 controller: "MasterController"
		})

		
   /********************************************** END OPD COVERSHEET *********************************************/   	       
 
			  .state('patientList', {
			  url: '/patientList',
			  templateUrl: 'views/common/appointments/patient-list.html',
			  controller: "patientListController"
			 })
			 .state('doctorList', {
			  url: '/doctorList',
			  templateUrl: 'views/common/appointments/doctor-list.html',
			  controller: "MasterController"
			 })
			 .state('smsConfig', {
			  url: '/smsConfig',
			  templateUrl: 'views/common/appointments/Master Management/sms-config.html',
			  controller: "MasterController"
			 })

			 .state('complaintConfig', {
			  url: '/complaintConfig',
			  templateUrl: 'views/common/appointments/Master Management/complaint-config.html',
			  controller: "MasterController"
			 })

			 .state('physicalExaminationConfig', {
			  url: '/physicalExaminationConfig',
			  templateUrl: 'views/common/appointments/Master Management/physical-examination-config.html',
			  controller: "MasterController"
			 })
			 
			 .state('instructionConfig', {
			  url: '/instructionConfig',
			  templateUrl: 'views/common/appointments/Master Management/instruction-config.html',
			  controller: "MasterController"
			 })
			 
			 .state('diagnosisConfig', {
			  url: '/diagnosisConfig',
			  templateUrl: 'views/common/appointments/Master Management/diagnosis-config.html',
			  controller: "MasterController"
			 })
			 
			 .state('observationConfig', {
			  url: '/observationConfig',
			  templateUrl: 'views/common/appointments/Master Management/observation-config.html',
			  controller: "MasterController"
			 })
			
			 .state('adviceConfig', {
			  url: '/adviceConfig',
			  templateUrl: 'views/common/appointments/Master Management/advice-config.html',
			  controller: "MasterController"
			 })
				
			 .state('historyConfig', {
			  url: '/historyConfig',
			  templateUrl: 'views/common/appointments/Master Management/history-config.html',
			  controller: "MasterController"
			 })
			 
			 /*.state('complaintConfig', {
			  url: '/complaintConfig',
			  templateUrl: 'views/common/appointments/Coversheet Master/complaint-config.html',
			  controller: "MasterController"
			 })*/
			 
			 .state('prescriptionDetails', {
			  url: '/prescriptionDetails',
			  templateUrl: 'views/common/appointments/Master Management/prescription-Details.html',
			  controller: "MasterController"
			 })
			 /********************************************** END OPD COVERSHEET *********************************************/   	       
			 /************************* Bed management - fall risk modal tabs Start*********************/
			 .state('nursingScale', {
			    url: '/nursingScale',
			    templateUrl: 'views/bedManagement/nursing-scale.html',
			    controller:'MasterController'
			})    
			  .state('nursingScale.FallRiskHistory',
			   {
			    url : '/FallRiskHistory',
			    views :
			     {
			      '@nursingScale' :
			       {
			        templateUrl : 'views/bedManagement/FallRisk-history.html',
			        controller : 'MasterController',
			       }
			     }
			   })
			   .state('nursingScale.FallRiskNew',
			   {
			    url : '/FallRiskNew',
			    views :
			     {
			      '@nursingScale' :
			       {
			        templateUrl : 'views/bedManagement/FallRisk-new.html',
			        controller : 'MasterController',
			       }
			     }
			   })
			/************************* Bed management - fall risk modal tabs end*********************/
    	       
/************************* pharmacy bill list start *********************************/
    	 .state('pharmacyBillList.cash',
  	       {
   	        url : '/cash',
   	        views :
	   	         {
	    	          '@pharmacyBillList' :
	   	           {
	    	            templateUrl : 'views/common/cash.html',
	    	            controller : 'MasterController',
	   	           }
	    	    }
  	       })
	        .state('pharmacyBillList.cheque',
	  	       {
	   	        url : '/cheque',
	   	        views :
	   	         {
	    	          '@pharmacyBillList' :
	   	           {
	    	            templateUrl : 'views/common/cheque.html',
	    	            controller : 'MasterController',
	   	           }
	    	     }
	        })
	        .state('pharmacyBillList.creditDebitCard',
	  	       {
	   	        url : '/creditDebitCard',
	   	        views :
	   	         {
	    	          '@pharmacyBillList' :
	   	           {
	    	            templateUrl : 'views/common/credit-debit-card.html',
	    	            controller : 'MasterController',
	   	           }
	    	     }
	        })
/************************* pharmacy bill list end *********************************/
/******************* Global - LIS Start (Added : 24-Jan-208)**************************/	        
    .state('antibioticAdditionGroupMaster', {
    	url: '/antibioticAdditionGroupMaster',
    	templateUrl: 'views/master/LIS/antibiotic-addition-group-master.html',
    	controller: "antibioticGroupAddAntibioticMapperController"
	})
		.state('colonyTestWithMediaMaster', {
			 url: '/colonyTestWithMediaMaster',
			 templateUrl: 'views/master/LIS/colony-test-with-media-master.html',
			 controller: "mediaColnoyAdditionController"
		})
/******************* Global - LIS End (Added : 24-Jan-208)**************************/		
/******************* Mortuary bed management Start (Added : 24-Jan-208)**************************/
	.state('mortuaryBedManagement', {
	 url: '/mortuaryBedManagement',
	 templateUrl: 'views/master/UnitMaster/Mortuary/mortuary-bed-management.html',
	 controller: "mortuaryBedManagementController"
})
/******************* Mortuary bed management End (Added : 24-Jan-208)**************************/
/******************* Unit master new page Start (Added : 29-Jan-208)**************************/
// .state('storewiseGenericMapper', {
// 	 url: '/storewiseGenericMapper',
// 	 templateUrl: 'views/master/UnitMaster/pharmacy/storewise-generic-mapper.html',
// 	 controller: "MasterController"
// })
.state('addstorewiseGenericMapper', {
	 url: '/addstorewiseGenericMapper',
	 templateUrl: 'views/master/UnitMaster/pharmacy/add-new-storewise-generic-mapper.html',
	 controller: "MasterController"
})
/*******************  Unit master new page End (Added : 29-Jan-208)**************************/
            // the main template will be placed here (relatively named)
            /*'': { templateUrl: 'views/about.html',
                controller: 'aboutCtrl' },*/

            // the child views will be defined here (absolutely named)
            /*'columnOne@about': { template: 'Look I am a column!' },*/

            // for column two, we'll define a separate controller 
          /*  'columnTwo@about': { 
                templateUrl: 'views/table-data.html',
                //controller: 'scotchController'
            }
        }*/

 /*   });*/
 /************************* Tax Master *********************************/	
		// .state('taxComponentMaster', {
		// 	 url: '/taxComponentMaster',
		// 	 templateUrl: 'views/master/GlobalMaster/tax-component-master.html',
		// 	 controller: "MasterController"
		// })
		// .state('taxMaster', {
		// 	 url: '/taxMaster',
		// 	 templateUrl: 'views/master/GlobalMaster/tax-master.html',
		// 	 controller: "MasterController"
		// })
		
/************************* Store Wise Mapping *********************************/
		
		
		
       /************************* Global (Nursing) *********************************/
		.state('shiftMaster', {
		 url: '/shiftMaster',
		 templateUrl: 'views/Nursing/shift-master.html',
		 controller: "MasterController"
	})
	.state('rotaCreation', {
		 url: '/rotaCreation',
		 templateUrl: 'views/Nursing/rota-creation.html',
		 controller: "MasterController"
	})
	.state('addnewRotaCreation', {
		 url: '/addnewRotaCreation',
		 templateUrl: 'views/Nursing/add-new-rota-creation.html',
		 controller: "MasterController"
	})
	.state('daywiseRotaCreation', {
		 url: '/daywiseRotaCreation',
		 templateUrl: 'views/Nursing/day-wise-rota-creation.html',
		 controller: "MasterController"
	})
	.state('addnewDaywiseRotaCreation', {
		 url: '/addnewDaywiseRotaCreation',
		 templateUrl: 'views/Nursing/add-new-day-wise-rota-creation.html',
		 controller: "MasterController"
	})
	 /************************* Unit Master (CSSD) *********************************/
	.state('sterilizationTypeMaster', {
		 url: '/sterilizationTypeMaster',
		 templateUrl: 'views/master/UnitMaster/CSSD/sterilization-type-master.html',
		 controller: "MasterController"
	})
	.state('autoclaveMaster', {
		 url: '/autoclaveMaster',
		 templateUrl: 'views/master/UnitMaster/CSSD/autoclave-master.html',
		 controller: "MasterController"
	})
	.state('wrapperExpiryMaster', {
		 url: '/wrapperExpiryMaster',
		 templateUrl: 'views/master/UnitMaster/CSSD/wrapper-expiry-master.html',
		 controller: "MasterController"
	})
	.state('preSterilizationProcess', {
		 url: '/preSterilizationProcess',
		 templateUrl: 'views/master/UnitMaster/CSSD/pre-sterilization-process.html',
		 controller: "MasterController"
	})
	.state('addNewpreSterilizationProcess',
       {
        url : '/addNewpreSterilizationProcess',
        views :
         {
          '@' :
           {
        	  templateUrl: 'views/master/UnitMaster/CSSD/add-new-pre-sterilization-process.html',
            controller : 'MasterController',
           }
         }
       })
       
      .state('addNewpreSterilizationProcess.instrumentCleaning',
       {
        url : '/instrumentCleaning',
        views :
         {
          '@addNewpreSterilizationProcess' :
           {
            templateUrl : 'views/master/UnitMaster/CSSD/instrumentCleaning.html',
            controller : 'MasterController',
           }
         }
       })
       .state('addNewpreSterilizationProcess.inspection',
       {
        url : '/inspection',
        views :
         {
          '@addNewpreSterilizationProcess' :
           {
            templateUrl : 'views/master/UnitMaster/CSSD/inspection.html',
            controller : 'MasterController',
           }
         }
       })
       .state('addNewpreSterilizationProcess.dis-Infection',
       {
        url : '/dis-Infection',
        views :
         {
          '@addNewpreSterilizationProcess' :
           {
            templateUrl : 'views/master/UnitMaster/CSSD/dis-Infection.html',
            controller : 'MasterController',
           }
         }
       })
       .state('addNewpreSterilizationProcess.kitFormation',
       {
        url : '/kitFormation',
        views :
         {
          '@addNewpreSterilizationProcess' :
           {
            templateUrl : 'views/master/UnitMaster/CSSD/kit-Formation.html',
            controller : 'MasterController',
           }
         }
       })
       .state('addNewpreSterilizationProcess.wrapper',
       {
        url : '/wrapper',
        views :
         {
          '@addNewpreSterilizationProcess' :
           {
            templateUrl : 'views/master/UnitMaster/CSSD/wrapper.html',
            controller : 'MasterController',
           }
         }
       })
       .state('sterilizationProcess', {
		 url: '/sterilizationProcess',
		 templateUrl: 'views/master/UnitMaster/CSSD/sterilization-process.html',
		 controller: "MasterController"
       })
       .state('addNewSterilizationProcess', {
		 url: '/addNewSterilizationProcess',
		 templateUrl: 'views/master/UnitMaster/CSSD/add-new-sterilization-process.html',
		 controller: "MasterController"
       })
       .state('viewSterilizationProcess', {
		 url: '/viewSterilizationProcess',
		 templateUrl: 'views/master/UnitMaster/CSSD/view-sterilization-process.html',
		 controller: "MasterController"
       })
       .state('KitIndent', {
		 url: '/KitIndent',
		 templateUrl: 'views/master/UnitMaster/CSSD/kit-indent.html',
		 controller: "MasterController"
       })
       .state('addNewKitIndent', {
		 url: '/addNewKitIndent',
		 templateUrl: 'views/master/UnitMaster/CSSD/add-new-kit-indent.html',
		 controller: "MasterController"
       })
       .state('KitIndentApproval', {
		 url: '/KitIndentApproval',
		 templateUrl: 'views/master/UnitMaster/CSSD/kit-indent-approval.html',
		 controller: "MasterController"
       })
       .state('KitIndentAcceptance', {
    	   url: '/KitIndentAcceptance',
    	   templateUrl: 'views/master/UnitMaster/CSSD/kit-indent-acceptance.html',
    	   controller: "MasterController"
       })
       .state('detailsKitIndentAcceptance', {
    	   url: '/detailsKitIndentAcceptance',
    	   templateUrl: 'views/master/UnitMaster/CSSD/details-kit-indent-acceptance.html',
    	   controller: "MasterController"
    	})
    	
    	.state('KitReturn', {
			 url: '/KitReturn',
			 templateUrl: 'views/master/UnitMaster/CSSD/kit-return.html',
			 controller: "MasterController"
	       })
	       .state('addNewKitReturn', {
			 url: '/addNewKitReturn',
			 templateUrl: 'views/master/UnitMaster/CSSD/add-new-kit-return.html',
			 controller: "MasterController"
	       })
	       .state('detailsKitReturn', {
			 url: '/detailsKitReturn',
			 templateUrl: 'views/master/UnitMaster/CSSD/details-kit-return.html',
	       })
       .state('kitIssue', {
		 url: '/kitIssue',
		 templateUrl: 'views/master/UnitMaster/CSSD/kit-Issue.html',
		 controller: "MasterController"
       })
       .state('addNewkitIssue', {
		 url: '/addNewkitIssue',
		 templateUrl: 'views/master/UnitMaster/CSSD/add-new-kit-Issue.html',
		 controller: "MasterController"
       })
       .state('detailskitIssue', {
		 url: '/detailskitIssue',
		 templateUrl: 'views/master/UnitMaster/CSSD/details-kit-Issue.html',
		 controller: "MasterController"
       })
       
       /* ********* */
    //    .state('materialPicker', {
	// 	 url: '/materialPicker',
	// 	 templateUrl: 'views/PharmacyInventory/InPatient/material-picker.html',
	// 	 controller: "MasterController"
    //    })
    //    .state('detailsMaterialPicker', {
	// 	 url: '/detailsMaterialPicker',
	// 	 templateUrl: 'views/PharmacyInventory/InPatient/details-material-picker.html',
	// 	 controller: "MasterController"
    //    })
       
       
    	       
    	// .state('materialIssue.pendingIssue',
  	    //    {
   	    //     url : '/pendingIssue',
   	    //     views :
   	    //      {
    	//           '@materialIssue' :
   	    //        {
    	//             templateUrl : 'views/PharmacyInventory/InPatient/material-pending-issue.html',
    	//             controller : 'MasterController',
   	    //        }
    	//          }
    	//        })
    	// .state('materialIssue.issueList',
  	    //    {
   	    //     url : '/issueList',
   	    //     views :
   	    //      {
    	//           '@materialIssue' :
   	    //        {
    	//             templateUrl : 'views/PharmacyInventory/InPatient/material-issue-list.html',
    	//             controller : 'MasterController',
   	    //        }
    	//          }
    	//        })
    // 	.state('detailsMaterialPending', {
	// 	 url: '/detailsMaterialPending',
	// 	 templateUrl: 'views/PharmacyInventory/InPatient/details-material-pending.html',
	// 	 controller: "MasterController"
    //    })
    //    .state('issueListdirectIssue', {
  	// 	 url: '/issueListdirectIssue',
  	// 	 templateUrl: 'views/PharmacyInventory/InPatient/issue-list-directIssue.html',
  	// 	 controller: "MasterController"
    //      })

        //  .state('detailsIssueList', {
  		//  url: '/detailsIssueList',
  		//  templateUrl: 'views/PharmacyInventory/InPatient/details-issue-list.html',
  		//  controller: "MasterController"
        //  })
         
         /************************* Global Common *********************************/
         .state('qualificationMaster', {
  		 url: '/qualificationMaster',
  		 templateUrl: 'views/master/GlobalMaster/qualification-master.html',
  		 controller: "qualificationMasterController"
         })
         .state('degreeMaster', {
  		 url: '/degreeMaster',
  		 templateUrl: 'views/master/GlobalMaster/degree-master.html',
  		 controller: "degreeMasterController"
         })
          .state('reasonMaster', {
  		 url: '/reasonMaster',
  		 templateUrl: 'views/master/GlobalMaster/reason-master.html',
  		 controller: "reasonMasterController"
         })
         .state('employeeCategoryMaster', {
  		 url: '/employeeCategoryMaster',
  		 templateUrl: 'views/master/GlobalMaster/employee-category-master.html',
  		 controller: "MasterController"
         })
         .state('doctorCategoryMaster', {
  		 url: '/doctorCategoryMaster',
  		 templateUrl: 'views/master/GlobalMaster/doctor-category-master.html',
  		 controller: "MasterController"
         })

         
         /**************** StaffRegistration Master *****************/
         .state('staffRegistration', {
  		 url: '/staffRegistration',
  		 templateUrl : 'views/AdmissionDischarge/staff-master.html',
  		 controller: "staffRegistrationController"
         })
         .state('addNewStaffRegistration', {
  		 url: '/addNewStaffRegistration',
  		 templateUrl : 'views/AdmissionDischarge/add-new-staff-registration.html',
  		 controller: "MasterController"
         })
        /* .state('staffRegistration',
       {
        url : '/staffRegistration',
        views :
         {
          '@' :
           {
            templateUrl : 'views/AdmissionDischarge/staff-master.html',
            controller : 'MasterController',
           }
         }
       })
       
      .state('staffRegistration.addNewStaffRegistration',
       {
        url : '/addNewStaffRegistration',
        views :
         {
          '@staffRegistration' :
           {
            templateUrl : 'views/AdmissionDischarge/add-new-staff-registration.html',
            //controller : 'patientRegistrationController',
           }
         }
       })*/
         /**************** DoctorRegistration Master *****************/
         .state('doctorRegistration', {
  		 url: '/doctorRegistration',
  		 templateUrl : 'views/AdmissionDischarge/doctor-master.html',
  		 controller: "doctorRegistrationController"
         })
         .state('addNewDoctorRegistration', {
  		 url: '/addNewDoctorRegistration',
  		 templateUrl : 'views/AdmissionDischarge/add-new-doctor-registration.html',
  		 controller: "MasterController"
         })
         /**************** Speciman Master added by Suraj 06.03.18*****************/
         .state('specimanMaster', {
  		 url: '/specimanMAster',
  		 templateUrl : 'views/master/LIS/speciman_master.html',
  		 controller: "specimanController"
         })
         /**************** Histopathology Test Master  added by Pranita 08.03.18*****************/
         .state('histopathologyTest', {
	  		 url: '/histopathologyTest',
	  		 templateUrl : 'views/master/LIS/histopathology-test.html',
	  		 controller: "histopathologyTestController"
         })
          .state('specimanType', {
	  		 url: '/specimanType',
	  		 templateUrl : 'views/master/LIS/speciman-type.html',
	  		 controller: "MasterController"
         })
         /**************** Store Master *****************/
         .state('pendingReturnablegatepassReceive ', {
  		 url: '/pendingReturnablegatepassReceive',
  		 templateUrl: 'views/PharmacyInventory/Store/returnable-gatepass-receive.html',
  		 controller: "MasterController"
         })
         .state('detailsReturnablegatepassReceivePendingList', {
  		 url: '/detailsReturnablegatepassReceivePendingList',
  		 templateUrl: 'views/PharmacyInventory/Store/details-returnable-gatepass-receive-pending-list.html',
  		 controller: "MasterController"
         })
         .state('detailsReturnablegatepassReceiveIssueList', {
  		 url: '/detailsReturnablegatepassReceiveIssueList',
  		 templateUrl: 'views/PharmacyInventory/Store/details-returnable-gatepass-receive-issue-list.html',
  		 controller: "MasterController"
		 })

	         .state('returnableGatepassRequest',
		       {
		        url : '/returnableGatepassRequest',
		        views :
		         {
		          '@' :
		           {
		        	  templateUrl: 'views/PharmacyInventory/InPatient/returnable-gatepass-request-pending-list.html',
		        	  controller : 'MasterController',
		           }
		         }
		       })
	       
			.state('returnableGatepassRequest.pendingList',
		     {
		        url : '/pendingList',
		        views :
		         {
			          '@returnableGatepassRequest' :
		           {
			            templateUrl : 'views/PharmacyInventory/InPatient/returnable-gatepass-pending-list.html',
			            controller : 'MasterController',
		           }
			         }
			       })
	       .state('returnableGatepassRequest.list',
	       {
	        url : '/list',
	        views :
	         {
		          '@returnableGatepassRequest' :
	           {
		            templateUrl : 'views/PharmacyInventory/InPatient/returnable-gatepass-list.html',
		            controller : 'MasterController',
	           }
		         }
		       })
		       
		       .state('detailsReturnableGatepassRequest', {
		  		 url: '/detailsReturnableGatepassRequest',
		  		 templateUrl: 'views/PharmacyInventory/InPatient/details-returnable-gatepass-pending-list.html',
		  		 controller: "MasterController"
		         })
		         .state('detailsReturnableGatepassRequestList', {
		  		 url: '/detailsReturnableGatepassRequestList',
		  		 templateUrl: 'views/PharmacyInventory/InPatient/details-returnable-gatepass-list.html',
		  		 controller: "MasterController"
		         })
		         
				.state('detailsWasteStore', {
					 url: '/detailsWasteStore',
					 templateUrl: 'views/PharmacyInventory/Store/details-waste-store.html',
					 controller: "MasterController"
				})
		    //    .state('otherCharges', {
			// 		 url: '/otherCharges',
			// 		 templateUrl: 'views/master/GlobalMaster/other-charges.html',
			// 		 controller: "MasterController"
			// 	})
				.state('fixedScroll', {
					 url: '/fixedScroll',
					 templateUrl: 'views/common/fixed-col-scroll.html',
					 controller: "MasterController"
				})
				.state('fixedtheadtfoot', {
					 url: '/fixedtheadtfoot',
					 templateUrl: 'views/common/fixed-thead-tfoot.html',
					 controller: "MasterController"
				})
				.state('companyContractMaster', {
					 url: '/companyContractMaster',
					 templateUrl: 'views/master/UnitMaster/billingMaster/company-contract-master.html',
					 controller: "companyContractController"
				})
				.state('addNewCompanyContract', {
					 url: '/addNewCompanyContract',
					 templateUrl: 'views/master/UnitMaster/billingMaster/add-new-company-contract.html',
					 controller: "addNewCompanyContractController"
				})
				.state('detailsCompanyContract', {
					 url: '/detailsCompanyContract',
					 templateUrl: 'views/master/UnitMaster/billingMaster/details-company-contract.html',
					 controller: "MasterController"
				})
			
				/*InPatient patient Consumable Issue*/
				.state('weekendMaster', {
					 url: '/weekendMaster',
					 templateUrl: 'views/master/UnitMaster/organizationMaster/weekend-master.html',
					 controller: "MasterController"
				})
				.state('globalDoctorShare', {
					 url: '/globalDoctorShare',
					 templateUrl: 'views/master/UnitMaster/billingMaster/doctor-share-master.html',
					 controller: "MasterController"
				})
				.state('detailsGlobalDoctorShare', {
					 url: '/detailsGlobalDoctorShare',
					 templateUrl: 'views/master/UnitMaster/billingMaster/details-doctor-share-master.html',
					 controller: "MasterController"
				})
				.state('detailsDoctorShareM', {
					 url: '/detailsDoctorShareM',
					 templateUrl: 'views/master/UnitMaster/billingMaster/details-doctor-share-master-temp.html',
					 controller: "MasterController"
				})
				.state('addNewGlobalDoctorShare', {
					 url: '/addNewGlobalDoctorShare',
					 templateUrl: 'views/master/UnitMaster/billingMaster/add-new-doctor-share-master.html',
					 controller: "addNewGlobalDoctorShareController"
				})
				.state('doctorWiseShare', {
					 url: '/doctorWiseShare',
					 templateUrl: 'views/master/UnitMaster/billingMaster/doctorwise-share-master.html',
					 controller: "MasterController"
				})
				.state('addNewDoctorWiseShare', {
					 url: '/addNewDoctorWiseShare',
					 templateUrl: 'views/master/UnitMaster/billingMaster/add-new-doctorwise-share-master.html',
					 controller: "addNewDoctorWiseShareController"
				})
				.state('detailsDoctorWiseShare', {
					 url: '/detailsDoctorWiseShare',
					 templateUrl: 'views/master/UnitMaster/billingMaster/details-doctorwise-share-master.html',
					 controller: "MasterController"
				})
				.state('doctorReferralShareMaster', {
					 url: '/doctorReferralShareMaster',
					 templateUrl: 'views/master/UnitMaster/billingMaster/doctor-referrel-share-master.html',
					 controller: "MasterController"
				})
				.state('addNewDoctorReferralShareMaster', {
					 url: '/addNewDoctorReferralShareMaster',
					 templateUrl: 'views/master/UnitMaster/billingMaster/add-new-doctor-referrel-share-master.html',
					 controller: "MasterController"
				})
				.state('detailsDoctorReferralShareMaster', {
					 url: '/detailsDoctorReferralShareMaster',
					 templateUrl: 'views/master/UnitMaster/billingMaster/details-doctor-referrel-share-master.html',
					 controller: "MasterController"
				})
				.state('ARmaster', {
					 url: '/ARmaster',
					 templateUrl: 'views/master/UnitMaster/billingMaster/AR-master.html',
					 controller: "MasterController"
				})
				.state('APmaster', {
					 url: '/APmaster',
					 templateUrl: 'views/master/UnitMaster/billingMaster/AP-master.html',
					 controller: "MasterController"
				})
				.state('GLcode', {
					 url: '/GLcode',
					 templateUrl: 'views/master/UnitMaster/billingMaster/GL-code.html',
					 controller: "MasterController"
				})
				
				.state('doctorShareProcess', {
					 url: '/doctorShareProcess',
					 templateUrl: 'views/master/DoctorShare/doctor-share-process.html',
					 controller: "MasterController"
				})
				
				.state('referralDoctorShareProcess', {
					 url: '/referralDoctorShareProcess',
					 templateUrl: 'views/master/DoctorShare/referral-doctor-share-process.html',
					 controller: "MasterController"
				})
				.state('systemMaster', {
					 url: '/systemMaster',
					 templateUrl: 'views/master/GlobalMaster/system-master.html',
					 controller: "systemMasterController"
				})
				.state('systemObservationMaster', {
					 url: '/systemObservationMaster',
					 templateUrl: 'views/master/GlobalMaster/system-observation-master.html',
					 controller: "systemObservationMasterController"
				})
				.state('doctorSharePaymentVoucher', {
					 url: '/doctorSharePaymentVoucher',
					 templateUrl: 'views/master/DoctorShare/doctor-share-payment-voucher.html',
					 controller: "MasterController"
				})
				
			
			/*******	LINEN SCREENS *********/
			.state('staffExchangelinenIssue', {
				 url: '/staffExchangelinenIssue',
				 templateUrl: 'views/Linen/staff-Exchange-linen-Issue.html',
				 controller: "MasterController"
				})	
			.state('detailStaffExchangelinenIssue', {/*pending*/
				 url: '/detailStaffExchangelinenIssue',
				 templateUrl: 'views/Linen/details-staff-Exchange-linen-Issue.html',
				 controller: "MasterController"
				})	
			.state('detailsIssueListStaffExchange', {
				 url: '/detailsIssueListStaffExchange',
				 templateUrl: 'views/Linen/details-issuelist-staff-Exchange-linen-Issue.html',
				 controller: "MasterController"
				})	
			.state('washingRequest', {
				 url: '/washingRequest',
				 templateUrl: 'views/Linen/washing-request.html',
				 controller: "MasterController"
				})	
			.state('addNewWashingRequest', {
				 url: '/addNewWashingRequest',
				 templateUrl: 'views/Linen/add-new-washing-request.html',
				 controller: "MasterController"
				})	
			.state('detailsWashingRequest', {
				 url: '/detailsWashingRequest',
				 templateUrl: 'views/Linen/details-washing-request.html',
				 controller: "MasterController"
				})	
			.state('acceptanceWashingRequest', {
				 url: '/acceptanceWashingRequest',
				 templateUrl: 'views/Linen/acceptance-of-washing-request.html',
				 controller: "MasterController"
				})	
			.state('detailsAcceptanceWashingRequest', {
				 url: '/detailsAcceptanceWashingRequest',
				 templateUrl: 'views/Linen/details-acceptance-of-washing-request.html',
				 controller: "MasterController"
				})	
			.state('issueWashedLinen', {
				 url: '/issueWashedLinen',
				 templateUrl: 'views/Linen/issue-washed-linen.html',
				 controller: "MasterController"
			})	
			.state('detailsPendingWashedLinen', {
				 url: '/detailsPendingWashedLinen',
				 templateUrl: 'views/Linen/details-pending-issue-washed-linen.html',
				 controller: "MasterController"
			})	
			.state('detailsIssueListWashedLinen', {
				 url: '/detailsIssueListWashedLinen',
				 templateUrl: 'views/Linen/details-issue-list-washed-linen.html',
				 controller: "MasterController"
			})	
			.state('directIssueWashedLinen', {
				 url: '/directIssueWashedLinen',
				 templateUrl: 'views/Linen/direct-issue-washed-linen.html',
				 controller: "MasterController"
			})
			
			.state('linenIndent', {
					 url: '/linenIndent',
					 templateUrl: 'views/Linen/linen-indent.html',
					 controller: "MasterController"
				})	
				
				.state('linenIndentDetails', {
					 url: '/linenIndentDetails',
					 templateUrl: 'views/Linen/linen-indent-details.html',
					 controller: "MasterController"
				})
				
				.state('addNewLinenIndent', {
					 url: '/addNewLinenIndent',
					 templateUrl: 'views/Linen/add-new-linen-indent.html',
					 controller: "MasterController"
				})
				
				.state('linenIndentApproval', {
					 url: '/linenIndentApproval',
					 templateUrl: 'views/Linen/linen-indent-approval.html',
					 controller: "MasterController"
				})
				
				.state('linenIndentApprovalDetails', {
					 url: '/linenIndentApprovalDetails',
					 templateUrl: 'views/Linen/linen-indent-approval-details.html',
					 controller: "MasterController"
				})
				
				.state('linenIssue', {
					 url: '/linenIssue',
					 templateUrl: 'views/Linen/linen-issue.html',
					 controller: "MasterController"
				})
				
				.state('linenIssueDirect', {
					 url: '/linenIssueDirect',
					 templateUrl: 'views/Linen/linen-issue-direct.html',
					 controller: "MasterController"
				})
				
				.state('linenIssuePendingDetails', {
					 url: '/linenIssuePendingDetails',
					 templateUrl: 'views/Linen/linen-issue-pending-details.html',
					 controller: "MasterController"
				})
				
				.state('linenIssueListDetails', {
					 url: '/linenIssueListDetails',
					 templateUrl: 'views/Linen/linen-issue-list-details.html',
					 controller: "MasterController"
				})
				
				.state('linenIssueacceptance', {
					 url: '/linenIssueacceptance',
					 templateUrl: 'views/Linen/linen-issue-acceptance.html',
					 controller: "MasterController"
				})
				
				.state('linenIssueAcceptanceDetails', {
					 url: '/linenIssueAcceptanceDetails',
					 templateUrl: 'views/Linen/linen-issue-acceptance-details.html',
					 controller: "MasterController"
				})
				
				.state('linenReturn', {
					 url: '/linenReturn',
					 templateUrl: 'views/Linen/linen-return.html',
					 controller: "MasterController"
				})
				
				.state('addNewLinenReturn', {
					 url: '/addNewLinenReturn',
					 templateUrl: 'views/Linen/add-new-linen-return.html',
					 controller: "MasterController"
				})
				
				
				.state('linenReturnDetails', {
					 url: '/linenReturnDetails',
					 templateUrl: 'views/Linen/linen-return-details.html',
					 controller: "MasterController"
				})
				
				.state('linenReturnAcceptance', {
					 url: '/linenReturnAcceptance',
					 templateUrl: 'views/Linen/linen-return-acceptance.html',
					 controller: "MasterController"
				})
				
				.state('linenReturnAcceptanceDetails', {
					 url: '/linenReturnAcceptanceDetails',
					 templateUrl: 'views/Linen/linen-return-acceptance-details.html',
					 controller: "MasterController"
				})
				
				.state('rejectedLinenAcceptance', {
					 url: '/rejectedLinenAcceptance',
					 templateUrl: 'views/Linen/rejected-linen-acceptance.html',
					 controller: "MasterController"
				})
				
				.state('rejectedLinenAcceptanceDetails', {
					 url: '/rejectedLinenAcceptanceDetails',
					 templateUrl: 'views/Linen/rejected-linen-acceptance-details.html',
					 controller: "MasterController"
				})
				.state('companyQueryResponseMaster', {
					 url: '/companyQueryResponseMaster',
					 templateUrl: 'views/master/UnitMaster/billingMaster/company-query-response-master.html',
					 controller: "MasterController"
				})
				.state('detailsCompanyQueryResponseMaster', {
					 url: '/detailsCompanyQueryResponseMaster',
					 templateUrl: 'views/master/UnitMaster/billingMaster/details-company-query-response-master.html',
					 controller: "MasterController"
				})
				// .state('applicationConfiguration', {
				// 	 url: '/applicationConfiguration',
				// 	 templateUrl: 'views/master/UnitMaster/configuration/application-master.html',
				// 	 controller: "MasterController"
				// })
				
				.state('sendToCondemnation', {
					 url: '/sendToCondemnation',
					 templateUrl: 'views/Linen/send-to-condemnate-page.html',
					 controller: "MasterController"
				})
				
				.state('addNewSendToCondemnation', {
					 url: '/addNewSendToCondemnation',
					 templateUrl: 'views/Linen/add-new-condmnet.html',
					 controller: "MasterController"
				})
				
				.state('sendToCondemnationDetails', {
					 url: '/sendToCondemnationDetails',
					 templateUrl: 'views/Linen/send-to-condemnation-details.html',
					 controller: "MasterController"
				})
				
				
				.state('acceptanceOfCondemnation ', {
					 url: '/acceptanceOfCondemnation',
					 templateUrl: 'views/Linen/acceptance-of-condemnation.html',
					 controller: "MasterController"
				})
				
				.state('acceptanceOfCondemnationDetails', {
					 url: '/acceptanceOfCondemnationDetails',
					 templateUrl: 'views/Linen/acceptance-of-condemnation-details.html',
					 controller: "MasterController"
				})
				
				.state('convertOfCondemnationStock', {
					 url: '/convertOfCondemnationStock',
					 templateUrl: 'views/Linen/convert-of-condemnation-stock.html',
					 controller: "MasterController"
				})
				
				.state('convertOfCondemnationStockDetails', {
					 url: '/convertOfCondemnationStockDetails',
					 templateUrl: 'views/Linen/convert-of-condemnation-stock-details.html',
					 controller: "MasterController"
				})
				
				.state('issueToStaff', {
					 url: '/issueToStaff',
					 templateUrl: 'views/Linen/issue-to-staff.html',
					 controller: "MasterController"
				})
				
				.state('staffExchangeLinen', {
					 url: '/staffExchangeLinen',
					 templateUrl: 'views/Linen/staff-exchange-linen.html',
					 controller: "MasterController"
				})
				
				.state('addNewStaffExchange', {
					 url: '/addNewStaffExchange',
					 templateUrl: 'views/Linen/add-new-staff-exchange.html',
					 controller: "MasterController"
				})
				
				.state('staffExchangeDetails', {
					 url: '/staffExchangeDetails',
					 templateUrl: 'views/Linen/staff-exchange-detials.html',
					 controller: "MasterController"
				})

				.state('sendtoMicrobiology', {
					 url: '/sendtoMicrobiology',
					 templateUrl: 'views/master/UnitMaster/CSSD/send-to-microbiology.html',
					 controller: "MasterController"
				})
				.state('detailsSendtoMicrobiology', {
					url: '/detailsSendtoMicrobiology',
					templateUrl: 'views/master/UnitMaster/CSSD/details-send-to-microbiology.html',
					controller: "MasterController"
				})
				.state('application', {
					url: '/application',
					templateUrl: 'views/master/UnitMaster/CSSD/application.html',
					controller: "MasterController"
				})
				.state('detailsApplication', {
					 url: '/detailsApplication',
					 templateUrl: 'views/master/UnitMaster/CSSD/details-application.html',
					 controller: "MasterController"
				})
				.state('detailsApplicationKit', {
					url: '/detailsApplicationKit',
					templateUrl: 'views/master/UnitMaster/CSSD/details-application-kit.html',
					controller: "MasterController"
				})

				
				.state('issueDirtyLinen', {
					 url: '/issueDirtyLinen',
					 templateUrl: 'views/Linen/issue-dirty-linen.html',
					 controller: "MasterController"
				})
				
				.state('addNewDirtyLinenIssue', {
					 url: '/addNewDirtyLinenIssue',
					 templateUrl: 'views/Linen/add-new-dirty-linen-issue.html',
					 controller: "MasterController"
				})
				
				.state('dirtyLinenIssueDetails', {
					 url: '/dirtyLinenIssueDetails',
					 templateUrl: 'views/Linen/dirty-linen-issue-details.html',
					 controller: "MasterController"
				})
				
				.state('acceptanceOfDirtyLinen', {
					 url: '/acceptanceOfDirtyLinen',
					 templateUrl: 'views/Linen/acceptance-of-dirty-linen.html',
					 controller: "MasterController"
				})
				
				.state('acceptanceOfDirtyLinenDetails', {
					 url: '/acceptanceOfDirtyLinenDetails',
					 templateUrl: 'views/Linen/dirty-linen-issue-acceptance-details.html',
					 controller: "MasterController"
				})
				.state('preAuthorizationRequest', {
					 url: '/preAuthorizationRequest',
					 templateUrl: 'views/master/UnitMaster/billingMaster/pre-authorization-request.html',
					 controller: "MasterController"
				})
				.state('addNewPreAuthorizationRequest', {
					 url: '/addNewPreAuthorizationRequest',
					 templateUrl: 'views/master/UnitMaster/billingMaster/add-new-pre-authorizationRequest.html',
					 controller: "MasterController"
				})
				.state('surgeryGradeMaster', {
					 url: '/surgeryGradeMaster',
					 templateUrl: 'views/master/GlobalMaster/surgery-grade-master.html',
					 controller: "surgeryGradeMasterController"
				})
				.state('surgeryMaster', {
					 url: '/surgeryMaster',
					 templateUrl: 'views/master/GlobalMaster/surgery-master.html',
					 controller: "surgeryMasterController"
				})
				.state('kitCreation', {
					 url: '/kitCreation',
					 templateUrl: 'views/master/UnitMaster/CSSD/kit-creation.html',
					 controller: "MasterController"
				})
				
				/*** Pre-Sterilization Process(new) ***/
				.state('preSterilizationCleaning', {
					 url: '/preSterilizationCleaning',
					 templateUrl: 'views/master/UnitMaster/CSSD/pre-sterilization-cleaning.html',
					 controller: "MasterController"
				})
				.state('addNewPreSterilizationCleaning', {
					 url: '/addNewPreSterilizationCleaning',
					 templateUrl: 'views/master/UnitMaster/CSSD/add-new-pre-sterilization-cleaning.html',
					 controller: "MasterController"
				})
				.state('viewPreSterilizationCleaning', {
					 url: '/viewPreSterilizationCleaning',
					 templateUrl: 'views/master/UnitMaster/CSSD/view-pre-sterilization-cleaning.html',
					 controller: "MasterController"
				})
				.state('preSterilizationInspection', {
					 url: '/preSterilizationInspection',
					 templateUrl: 'views/master/UnitMaster/CSSD/pre-sterilization-inspection.html',
					 controller: "MasterController"
				})
				.state('viewPreSterilizationInspection', {
					 url: '/viewPreSterilizationInspection',
					 templateUrl: 'views/master/UnitMaster/CSSD/view-pre-sterilization-inspection.html',
					 controller: "MasterController"
				})
				.state('preSterilizationDisInfection', {
					 url: '/preSterilizationDisInfection',
					 templateUrl: 'views/master/UnitMaster/CSSD/pre-sterilization-disinfection.html',
					 controller: "MasterController"
				})
				.state('viewPreSterilizationDisInfection', {
					 url: '/viewPreSterilizationDisInfection',
					 templateUrl: 'views/master/UnitMaster/CSSD/view-pre-sterilization-disinfection.html',
					 controller: "MasterController"
				})
				.state('preSterilizationKitFormation', {
					 url: '/preSterilizationKitFormation',
					 templateUrl: 'views/master/UnitMaster/CSSD/pre-sterilization-kit-formation.html',
					 controller: "MasterController"
				})
				.state('viewPreSterilizationKitFormation', {
					 url: '/viewPreSterilizationKitFormation',
					 templateUrl: 'views/master/UnitMaster/CSSD/view-pre-sterilization-kit-formation.html',
					 controller: "MasterController"
				})
				/*** Sterilization Process NEW ***/
				.state('sterilizationLoadBatch', {
					 url: '/sterilizationLoadBatch',
					 templateUrl: 'views/master/UnitMaster/CSSD/sterilization-load-batch.html',
					 controller: "MasterController"
				})
				.state('addNewSterilizationLoadBatch', {
					 url: '/addNewSterilizationLoadBatch',
					 templateUrl: 'views/master/UnitMaster/CSSD/add-new-sterilization-load-batch.html',
					 controller: "MasterController"
				})
				.state('viewSterilizationLoadBatch', {
					 url: '/viewSterilizationLoadBatch',
					 templateUrl: 'views/master/UnitMaster/CSSD/view-sterilization-load-batch.html',
					 controller: "MasterController"
				})
				.state('sterilizationPendingIndicators', {
					 url: '/sterilizationPendingIndicators',
					 templateUrl: 'views/master/UnitMaster/CSSD/sterilization-pending-indicators.html',
					 controller: "MasterController"
				})
				.state('addNewSterilizationPendingIndicators', {
					 url: '/addNewSterilizationPendingIndicators',
					 templateUrl: 'views/master/UnitMaster/CSSD/add-new-sterilization-pending-indicators.html',
					 controller: "MasterController"
				})
				.state('viewSterilizationPendingIndicators', {
					 url: '/viewSterilizationPendingIndicators',
					 templateUrl: 'views/master/UnitMaster/CSSD/view-sterilization-pending-indicators.html',
					 controller: "MasterController"
				})
				 .state('detailsReturnBillableConsumption', {
					 url: '/detailsReturnBillableConsumption',
					 templateUrl: 'views/PharmacyInventory/InPatient/details-return-billable-consumption.html',
					 controller: "MasterController"
				 })
				 .state('newOPDBilling', {
					 url: '/newOPDBilling',
					 templateUrl: 'views/master/UnitMaster/billingMaster/new-opd-billing.html',
					 controller: "newOPDBillingController"
				 })
				 .state('designationMaster', {
					 url: '/designationMaster',
					 templateUrl: 'views/master/GlobalMaster/designation-master.html',
					 controller: "designationMasterController"
				 })
				//  .state('pharmacyStrengthUnitMaster', {
				// 	url: '/pharmacyStrengthUnitMaster',
				// 	templateUrl: 'views/PharmacyInventory/pharmacy-strength-unit-master.html',
				// 	controller: "MasterController"
				// 	})
				 
				 /*Employee Dependent Contract START*/
				 .state('employeeDependentContractMaster', {
					 url: '/employeeDependentContractMaster',
					 templateUrl: 'views/master/UnitMaster/billingMaster/employee-dependent-contract-master.html',
					 controller: "MasterController"
				})
				.state('addNewEmployeeDependentContractMaster', {
					 url: '/addNewEmployeeDependentContractMaster',
					 templateUrl: 'views/master/UnitMaster/billingMaster/add-new-employee-dependent-contract-master.html',
					 controller: "MasterController"
				})
				/*Employee Dependent Contract END*/
				
				.state('licenseTypeMaster', {
					 url: '/licenseTypeMaster',
					 templateUrl: 'views/master/GlobalMaster/license-type-master.html',
					 controller: "licenseTypeMasterController"
				})
				
				.state('refundAgainstDeposit', {
					 url: '/refundAgainstDeposit',
					 templateUrl: 'views/master/UnitMaster/billingMaster/refund-against-deposit.html',
					 controller: "MasterController"
				})
				.state('refundList', {
					 url: '/refundList',
					 templateUrl: 'views/master/UnitMaster/billingMaster/refund-list.html',
					 controller: "MasterController"
				})
				.state('refundAgainstCR', {
					 url: '/refundAgainstCR',
					 templateUrl: 'views/master/UnitMaster/billingMaster/refund-against-CR.html',
					 controller: "MasterController"
				})
				.state('indentTemplateMaster', {
					 url: '/indentTemplateMaster',
					 templateUrl: 'views/master/UnitMaster/assetManagement/indent-template-master.html',
					 controller: "MasterController"
				})
				.state('addNewIndentTemplateMaster', {
					 url: '/addNewIndentTemplateMaster',
					 templateUrl: 'views/master/UnitMaster/assetManagement/add-new-indent-template-master.html',
					 controller: "MasterController"
				})
				.state('ETOItemConfiguration', {
					 url: '/ETOItemConfiguration',
					 templateUrl: 'views/master/UnitMaster/assetManagement/ETO-item-configuration.html',
					 controller: "MasterController"
				})

				.state('addNewETOItemConfiguration', {
					 url: '/addNewETOItemConfiguration',
					 templateUrl: 'views/master/UnitMaster/assetManagement/add-new-ETO-item-configuration.html',
					 controller: "MasterController"
				})
				.state('prefixConfiguration', {
					 url: '/prefixConfiguration',
					 templateUrl: 'views/master/UnitMaster/configuration/prefix-master.html',
					 controller: "MasterController"
				})
				.state('IncubationMaster', {
				    url: '/IncubationMaster',
				    templateUrl: 'views/LIS/Microbiology/incubation-master.html',
				    controller: "incubationObjservationController"
				})
				.state('detailsIncubationMaster', {
				    url: '/detailsIncubationMaster',
				    templateUrl: 'views/LIS/Microbiology/details-incubation-master.html',
				    controller: "incubationDetailsController",
				    params: 
			  			{
				    	    incuObsrvationObj: {} //default value
			  			} 
				})
				.state('macroscopicExaminationMaster', {
				    url: '/macroscopicExaminationMaster',
				    templateUrl: 'views/LIS/Microbiology/macroscopic-examination-master.html',
				    controller: "macroScopicExaminationController"
				})
	}
	catch (e) {
		console.log(e);
		// TODO: handle exception
	}
						} ]);

// Promise Factory
// Code Added By Ganesh 04.10.2017
angular.module('myApp').factory('promiseFactory', function($http, $q) {
	var promises = [];
	// var promise2;
	return {
		setPromises : function(URI1, URI2, methodType1, methodType2) {
			var deffered = $q.defer();
			var deffered1 = $q.defer();
			$http({
				method : methodType1,
				url : URI1
			}).success(function(data) {
				deffered.resolve(data);
			});// then(function(data) {return data;});
			$http({
				method : methodType2,
				url : URI2
			}).success(function(data) {
				deffered1.resolve(data);
			});
			promises = [];
			promises.push(deffered.promise);
			promises.push(deffered1.promise);
			return $q.all(promises);

		}
	}
});


/*angular.module( "myApp" ).directive(
		'input',
        function bnModelWatchDirective() {
            // Return the directive configuration object.
            return({
                link: link,
                restrict: "A"
            });
            // I bind the JavaScript events to the view-model.
            function link( scope, element, attributes ) {
                // In this approach, we're just going to watch the ngModel expression
                // for changes in the same way we would watch any other scope-based
                // value for changes.
                scope.$watch(
                    attributes.ngModel,
                    function( newValue, oldValue ) {
                        console.log( "ngModel value changed (A):", newValue );
                        alert('chhange: '+ newValue);
                    }
                );
            }
        }
    );*/


angular.module("myApp").directive('input', function(){
	return {
		restrict: 'E',
		link: function (scope, element, attributes){
			//alert('fsd');
			 scope.$watch(
	                    attributes.ngModel,
	                    function( newValue, oldValue ) {
	                    	if(newValue != ''){
	                    		/*//alert('add'+ element);
	                    		addClass(element, 'dirty')*/
	                    		$("input[type=text]").each(function(){
	                    			$(this).addClass('dirty');
	                    		})
	          		        }else{
	          		          /*removeClass(element, 'dirty')*/
	          		        }
	                    }
	                );
		}
	}
});


angular.module("myApp").directive('select', function(){
	return {
		restrict: 'E',
		link: function (scope, element, attributes){
			//alert('fsd');
			 scope.$watch(
	                    attributes.ngModel,
	                    function( newValue, oldValue ) {
	                    	if(newValue != ''){
	                    		/*//alert('add'+ element);
	                    		addClass(element, 'dirty')*/
	                    		$("select").each(function(){
	                    			$(this).addClass('dirty');
	                    		})
	          		        }else{
	          		          /*removeClass(element, 'dirty')*/
	          		        }
	                    }
	                );
		}
	}
});

angular.module("myApp").directive('textarea', function(){
	return {
		restrict: 'E',
		link: function (scope, element, attributes){
			//alert('fsd');
			 scope.$watch(
	                    attributes.ngModel,
	                    function( newValue, oldValue ) {
	                    	if(newValue != ''){
	                    		/*//alert('add'+ element);
	                    		addClass(element, 'dirty')*/
	                    		$("textarea").each(function(){
	                    			$(this).addClass('dirty');
	                    		})
	          		        }else{
	          		          /*removeClass(element, 'dirty')*/
	          		        }
	                    }
	                );
		}
	}
});

Array.prototype.max = function() {
  return Math.max.apply(null, this);
};

Array.prototype.min = function() {
  return Math.min.apply(null, this);
};