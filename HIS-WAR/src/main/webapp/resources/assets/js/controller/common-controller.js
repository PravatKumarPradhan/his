/**
 * MASTER CONTROLLER
 */

angular
		.module('myApp').controller("MasterController",['$scope','$rootScope','$state','$cookies',
    function($scope,$rootScope,$state, $cookies)
	{
	/*For Tab */
	//alert();
	$scope.dataTab = {static: true}
	
	//alert("in controll");
	$rootScope.loginpage= true;
		$scope.LISDynamicLabel = $cookies.get('selectedPageName');
		$scope.selectedPageNameForAdmissionNote = $cookies.get('selectedPageNameForAdmissionNote');
		
		/* $scope.date4 = moment().startOf('day').toDate();*/
		 
		 $scope.dateOptions = {
		      formatYear: 'yyyy',
		      showWeeks: false
		  };
		 $scope.toDateOptions = {
		      formatYear: 'yyyy',
		      showWeeks: false
		  };
		 $scope.openDatePicker = function() {
			// alert("in");
		      $scope.datepicker.opened = true;
		    };
	    $scope.openToDatePicker = function() {
			// alert("in");
		      $scope.toDatepicker.opened = true;
		    };
		    $scope.datepicker = {
		      opened: false
		    };
		    $scope.toDatepicker = {
				opened: false
			};
		    
		    $scope.openDatePickerForNewDOA = function() {
				// alert("in");
			      $scope.datepickerForNewDOA.opened = true;
			    };
			   
			    $scope.datepickerForNewDOA = {
					      opened: false
					    };
			
			   
			   /* Add table Row For Eduction */
			    $scope.educationDetails = [];
			    $scope.educationDetailsArray = {qualification:"", degreeName:"",documentUpload:"", documentImage:""};
				
				var dtls = angular.copy( $scope.educationDetailsArray);
				$scope.educationDetails.push(dtls);
			  
			    
			    $scope.addNew = function(){
			    	//alert("on click");
			    	var dtls = angular.copy( $scope.educationDetailsArray);
			    	$scope.educationDetails.push(dtls);
		        };
			    
		        
		        
		        /* Add table Row For Experiance */
			    $scope.experienceDetails = [];
			    $scope.experienceDetailsArray = {hospitalName:"",fromDate:"",toDate:"",remark:""};
				
				var dtls = angular.copy( $scope.experienceDetailsArray);
				$scope.experienceDetails.push(dtls);
			  
			    
			    $scope.addNewExperienceRow = function(){
			    	//alert("in experi");
			    	var dtls = angular.copy( $scope.experienceDetailsArray);
			    	$scope.experienceDetails.push(dtls);
		        };
		        
		        /* Add Next Of Kin Details */
			    $scope.nextOfKinDetails = [];
			    $scope.nextOfKinDetailsArray = {prefix:"",firstName:"",middleName:"",lastName:"",birthDate:"",age:"",sex:"",bloodGroup:"",relationShipWidEmployee:"",address:"",country:"",state:"",city:"",area:"",zipCode:"",code:"",contactNumber:""};
				
				var dtls = angular.copy( $scope.nextOfKinDetailsArray);
				$scope.nextOfKinDetails.push(dtls);
			  
			    
			    $scope.addNewKinOfDetails = function(){
			    	//alert("in experi");
			    	var dtls = angular.copy( $scope.nextOfKinDetailsArray);
			    	$scope.nextOfKinDetails.push(dtls);
		        };
		        
		        /* Add CME Details */
			    $scope.CMEDetails = [];
			    $scope.CMEDetailsArray = {cmeDetails:"",cemPoints:"",visitType:""};
				
				var dtls = angular.copy( $scope.CMEDetailsArray);
				$scope.CMEDetails.push(dtls);
			  
			    
			    $scope.addNewCME = function(){
			    	//alert("in experi");
			    	var dtls = angular.copy( $scope.CMEDetailsArray);
			    	$scope.CMEDetails.push(dtls);
		        };
		        
		       
		        /******* START TAB FOR ALL PAGES IN TREE LEVEL *******/
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
					
					/*Second Level*/
					$scope.secondTabLevel = 1 ;
					
					$scope.setSecondTabLevel = function(newTab)
					{
						$scope.secondTabLevel = newTab;
					};
					
					$scope.isSetSecondTabLevel = function(tabNum)
					{
						return $scope.secondTabLevel === tabNum;
					};
					
					
					/*Third Level*/
					$scope.thirdTabLevel = 1 ;
					
					$scope.setThirdTabLevel = function(newTab)
					{
						$scope.thirdTabLevel = newTab;
					};
					
					$scope.isSetThirdTabLevel = function(tabNum)
					{
						return $scope.thirdTabLevel === tabNum;
					};
					
					
					
					/*FORTH Level*/
					$scope.forthTabLevel = 1 ;
					
					$scope.setForthTabLevel = function(newTab)
					{
						$scope.forthTabLevel = newTab;
					};
					
					$scope.isSetForthTabLevel = function(tabNum)
					{
						return $scope.forthTabLevel === tabNum;
					};
					
				/******* END TAB FOR ALL PAGES IN TREE LEVEL *******/
		        
		        
		        
		        
		        
	
/***** checkbox list department master unit start *****/
    $scope.roles = [{
        roleId: 1,
        roleName: "Administrator"
    }, {
        roleId: 2,
        roleName: "Super User"
    }];

    $scope.user = {
        userId: 1,
        username: "JimBob",
        roles: [$scope.roles[0]]
    };
/***** checkbox list department master unit end *****/
    
    $rootScope.$on('customEvent', function(event, message) {
    	$scope.LISDynamicLabel = message;
    	//alert("in message===="+message);
    	
    	var pageFlag = message;//$(".LISDynamicLabel").html().toLowerCase();
    	if(pageFlag == "department")
    	{
    		 $(".LISDynamicLabel").text("Department");
    		 $(".selectedPageName").text("Department Master");
    	
    	}
    	else if(pageFlag == "Phlebotomy")
    	{
    		$(".LISDynamicLabel").text("Phlebotomy");
    		$(".selectedPageName").text("Phlebotomy Master");
    	
    	}
    	else if(pageFlag == "Container")
    	{
    		$(".LISDynamicLabel").text("Container");
    		$(".selectedPageName").text("Container Master");
    	
    	}
    	else if(pageFlag == "Sample")
    	{
    		$(".LISDynamicLabel").text("Sample");
    		$(".selectedPageName").text("Sample Master");
    		
    	}
    	else if(pageFlag == "Report Type")
    	{
    		alert();
    		$(".LISDynamicLabel").text("Report Type");	
    		$(".selectedPageName").text("Report Type Master");
    		
    	}
    	else if(pageFlag == "Techinque")
    	{
    		$(".LISDynamicLabel").text("Techinque");
    		$(".selectedPageName").text("Techinque Master");
    		
    	}
    	else if(pageFlag == "Prerequisites")
    	{
    		$(".LISDynamicLabel").text("Prerequisites");	
    		$(".selectedPageName").text("Prerequisites Master");
    	
    	}
    	/* else if(pageFlag == "ageGroup")
    	{
    		$(".LISDynamicLabel").text("Age Group");
    		$(".selectedPageName").text("Age Group Master");
    	} */
    	else if(pageFlag == "Unit")
    	{
    		$(".LISDynamicLabel").text("Unit");	
    		$(".selectedPageName").text("Unit Master");
    		
    	}
    	else if(pageFlag == "Priority")
    	{
    		$(".LISDynamicLabel").text("Priority");	
    		$(".selectedPageName").text("Priority Master");
    		
    	}
    	else if(pageFlag == "Regent")
    	{
    		$(".LISDynamicLabel").text("Reagent");	
    		$(".selectedPageName").text("Reagent Master");
    		
    	
    	}
    	else if(pageFlag == "Bacteria shape")
    	{
    		$(".LISDynamicLabel").text("Bacteria shape");	
    		$(".selectedPageName").text("Bacteria Shape Master");
    		
    	}
    	/*else if(pageFlag == "microbiology")
    	{
    		$(".LISDynamicLabel").text("Microbiology Strength");	
    		$(".selectedPageName").text("Microbiology Strength Master");
    		
    	}*/
    	else if(pageFlag == "Wet Film Study")
    	{
    		$(".LISDynamicLabel").text("Wet Film Study");	
    		$(".selectedPageName").text("Wet Film Study Master");
    		
    	}
    	/* *******************Billing Master********************* */
    	else if(pageFlag == "Payment Entitlement")
    	{
    		$(".LISDynamicLabel").text("Payment Entitlement");	
    		$(".selectedPageName").text("Payment Entitlement Master");
    	
    	}
    	else if(pageFlag == "Patient Category")
    	{
    		$(".LISDynamicLabel").text("Patient Category");	
    		$(".selectedPageName").text("Patient Category Master");
    	
    	}
    	else if(pageFlag == "Payment Mode")
    	{
    		$(".LISDynamicLabel").text("Payment Mode");	
    		$(".selectedPageName").text("Payment Mode Master");
    	
    	}
    	else if(pageFlag == "Visit Type")
    	{
    		$(".LISDynamicLabel").text("Visit Type");	
    		$(".selectedPageName").text("Visit Type Master");
    	
    	}
    	else if(pageFlag == "Bed Type")
    	{
    		$(".LISDynamicLabel").text("Bed Type");	
    		$(".selectedPageName").text("Bed Type Master");

    	}
    	else if(pageFlag == "Discount Reason")
    	{
    		$(".LISDynamicLabel").text("Discount Reason");	
    		$(".selectedPageName").text("Discount Reason Master");
    	
    	}
    	/*else if(pageFlag == "billing department master")
    	{
    		$(".LISDynamicLabel").text("Department");	
    		$(".selectedPageName").text("Department Master");
    	
    	}*/
    	else if(pageFlag == "Equipment Master")
    	{
    		$(".LISDynamicLabel").text("Equipment Master ");	
    		$(".selectedPageName").text("Equipment Master");
    	
    	}
    	else if(pageFlag == "Consultant Type")
    	{
    		$(".LISDynamicLabel").text("Consultant Type");	
    		$(".selectedPageName").text("Consultant Type Master");

    	}
    	/********************Pharmacy & Intventory Controll Master**********************/
    	else if(pageFlag == "Pharmacy Product")
    	{
    		$(".LISDynamicLabel").text("Pharmacy Product");	
    		$(".selectedPageName").text("Pharmacy Product Master");
    	
    	}
    	else if(pageFlag == "Product Type")
    	{
    		$(".LISDynamicLabel").text("Product Type");	
    		$(".selectedPageName").text("Product Type Master");
    	
    	}
    	else if(pageFlag == "Product Strength")
    	{
    		$(".LISDynamicLabel").text("Product Strength");	
    		$(".selectedPageName").text("Product Strength Master");
    	
    	}
    /*	else if(pageFlag == "admin route master")
    	{
    		$(".LISDynamicLabel").text("Route of Administration");	
    		$(".selectedPageName").text("Administration Route Master");
    		
    	}
    	else if(pageFlag == "frequency master")
    	{
    		$(".LISDynamicLabel").text("Frequency");	
    		$(".selectedPageName").text("Frequency Master");
    		
    	}*/
    	else if(pageFlag == "Schedule")
    	{
    		$(".LISDynamicLabel").text("Schedule");	
    		$(".selectedPageName").text("Schedule Master");
    	
    	}
    	else if(pageFlag == "Manufacture")
    	{
    		$(".LISDynamicLabel").text("Manufacture");	
    		$(".selectedPageName").text("Manufacture Master");
    	
    	}
    	else if(pageFlag == "Vendor")
    	{
    		$(".LISDynamicLabel").text("Vendor");	
    		$(".selectedPageName").text("Vendor Master");
    	
    	}
    	else if(pageFlag == "Rack")
    	{
    		$(".LISDynamicLabel").text("Rack");	
    		$(".selectedPageName").text("Rack Master");
    	
    	}
    	else if(pageFlag == "Store")
    	{
    		$(".LISDynamicLabel").text("Store");	
    		$(".selectedPageName").text("Store Master");
    		
    	}
    	/*else if(pageFlag == "reason master")
    	{
    		$(".LISDynamicLabel").text("Reason");	
    		$(".selectedPageName").text("Reason Master");
    	
    	}*/
    	else if(pageFlag == "ICU Type")
    	{
    		$(".LISDynamicLabelOne").text("ICU Code");	
    		$(".LISDynamicLabel").text("ICU Type");	
    		$(".selectedPageName").text("ICU Type Master");
    	
    	}
    	else if(pageFlag == "Identification Card")
    	{
    		$(".LISDynamicLabel").text("Identification Card");	
    		$(".selectedPageName").text("Identification Card Master");
    	
    	}
    	else if(pageFlag == "Sex")
    	{
    		$(".LISDynamicLabel").text("Sex");	
    		$(".selectedPageName").text("Sex Master");
    	
    	}
    	else if(pageFlag == "Relation")
    	{
    		$(".LISDynamicLabel").text("Relation");	
    		$(".selectedPageName").text("Relation Master");
    	
    	}
    	else if(pageFlag == "Marital Status")
    	{
    		$(".LISDynamicLabel").text("Marital Status");	
    		$(".selectedPageName").text("Marital Status Master");
    	
    	}
    	else if(pageFlag == "Religion")
    	{
    		$(".LISDynamicLabel").text("Religion");	
    		$(".selectedPageName").text("Religion Master");
    	
    	}
    	else if(pageFlag == "Occupation")
    	{
    		$(".LISDynamicLabel").text("Occupation");	
    		$(".selectedPageName").text("Occupation Master");
    	
    	}
    	else if(pageFlag == "Patient Source")
    	{
    		$(".LISDynamicLabel").text("Patient Source");	
    		$(".selectedPageName").text("Patient Source Master");
    	
    	}
    	else if(pageFlag == "Referral Type")
    	{
    		$(".LISDynamicLabel").text("Referral Type");	
    		$(".selectedPageName").text("Referral Type Master");
    	
    	}
    	/******************** Unit Master**********************/
    	else if(pageFlag == "Floor")
    	{
    		$(".LISDynamicLabel").text("Floor");	
    		$(".selectedPageName").text("Floor Master");
    	
    	}
      });

			    
	}]);