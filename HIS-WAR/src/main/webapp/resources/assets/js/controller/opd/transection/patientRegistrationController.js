'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:patientRegistrationController
 * @description #patientRegistrationController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'patientRegistrationController',
				[
						'$scope',
						'$http',
						'$localStorage',
						'$sessionStorage',
						'$cookies',
						'$rootScope',
						'GenericService',
						'BillingGenericService',
						'growl',
						'$state',
						'$stateParams',
						function($scope, $http, $localStorage, $sessionStorage,
								$cookies, $rootScope, GenericService,
								BillingGenericService, growl, $state,
								$stateParams) {

							/* init() function for form object create. */
							$scope.init = function() {
								
							
								//for datepicker 1
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
								
								// for datePicker2
								$scope.dateOptions = {
									formatYear : 'yyyy',
									showWeeks : false
								};
								$scope.openDatePickerExpiry = function() {
									$scope.datepickerExpiry.opened = true;
								};

								$scope.datepickerExpiry = {
									opened : false
								};
								
								//for datepicker Search
								$scope.dateOptions = {
									formatYear : 'yyyy',
									showWeeks : false
								};

								$scope.openDatePickerSearch = function() {
									$scope.datepickerSearch.opened = true;
								};

								$scope.datepickerSearch = {
									opened : false
								};
								

								var cookieObject = $cookies
										.getObject('cookieObject');
								if (cookieObject == undefined) {
									$state.go('login');
									return;
								}
								$scope.unitId = cookieObject.unitId;
								$scope.organizationId = cookieObject.organizationId;
								$scope.userId = 1;

								$rootScope.loginpage = true;
								$scope.saveBtnFlag = false;
								$scope.updateBtnFlag = true;
								
								$scope.aliseFlag = false;
								
								$scope.expiryDateDisabled = true;
								
								$scope.searchType = 0;
								
								$scope.birthDateSearchFlag = true;
								
								$scope.searchSelectPatient = {}
								$scope.itemSelecteds = {};
								
								$scope.datepickerExpiry = new Date(moment(new Date()).format('MM/DD/YYYY'));
								//alert($scope.datepickerExpiry );
								// for popup
								$scope.showModal = false;
								$scope.buttonClicked = "";
								$scope.popUpFlag = true;
								// end for popup
								
								$scope.tab=1;
								
								$scope.ph_numbr = /^\+?\d{10}$/;
						        $scope.eml_add = /^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/;
								
								$scope.patientObj = {

									prefixId : '',
									firstName : '',
									middleName : '',
									lastName : '',
									genderId : '',
									countryCallingCode : '',
									email : '',
									nationalityId : '',
									raceId : '',
									maritalStatusId : '',
									mobileNumber : '',
									dob : '',
									identificationTypeId : '',
									identificationNumber : '',
									isVip : '',
									address : '',
									countryId : '',
									stateId : '',
									districtId : '',
									cityId : '',
									areaId : '',
									zipCode : '',
									phoneCode : '',
									phoneNumber : '',
									companyName : '',
									companyAddress : '',
									companyCountryId : '',
									companyStateId : '',
									companyDistrictId : '',
									companyCityId : '',
									companyAreaId : '',
									companyZipCode : '',
									companyMobileNumber : '',
									organizationId : $scope.organizationId,
									unitId : $scope.unitId,
									updatedBy : $scope.userId,
									createdBy : $scope.userId,
									status : 'A',
									patientCategoryId:'1',
									permanentAddress:'',
									permanentCountryId:'',
									permanentStateId:'',
									permanentDistrictId:'',
									permanentCityId:'',
									permanentAreaId:'',
									permanentZipCode:'',
									permanentPhoneCode:'',
									permanentPhoneNumber:'',
									aliseName:'',
									identificationExpiryDate:'',
									occupationId:'',
									registrationTypeId:1,
									previousId:'',
									patientId:0,
									patientDetailsId:''
									
									
								};
								
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
										
								}
								$scope.dependentSearchObj = {
										"empCode"      : "",
										"employeeName"     : "",
										"dependentName" : "",
										"identificationNumber":"",
										"identificationTypeId":0,
										"unitId" :$scope.unitId,
										"organizationId"  :$scope.organizationId,
										"typeId" : ""
										
								}
								$scope.employeeSearchObj = {
										"empCode"      : "",
										"employeeName"     : "",
										"specialityId" : "0",
										"identificationNumber":"",
										"identificationTypeId":"0",
										"unitId" :$scope.unitId,
										"organizationId"  :$scope.organizationId,
										"typeId" : ""
								}
								

								var commonObj = {
									unitId : $scope.unitId,
									organizationId : $scope.organizationId
								};
								var commonObj1 = {
									organizationId : $scope.organizationId
								};

								var data = {};
								
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
								
								var URI = BASE_URL + ROOT_URL
								+ GETACTIVEPATIENTCATEGORY;
						GenericService
								.serviceAction("GET", URI, data)
								.then(
										function(response) {
											$scope.patientCategoryList = [];

											if (response.data.status == "success") {
												$scope.patientCategoryList = response.data.listObject;
												console
														.log($scope.patientCategoryList);

											}
										});

								var URI = BASE_URL + ROOT_URL
										+ GETACTIVEPREFIXLIST;
								BillingGenericService
										.serviceAction(METHOD_GET, URI, data,
												NOTIFICATION_MSG_STATUS_FALSE)
										.then(
												function(response) {
													console.log(response);
													$scope.prefixList = [];
													if (response.data.status == "success")
														$scope.prefixList = response.data.listObject;
												});

								var URI = BASE_URL + ROOT_URL
										+ GETACTIVEGENDERLIST;
								BillingGenericService
										.serviceAction(METHOD_GET, URI, data,
												NOTIFICATION_MSG_STATUS_FALSE)
										.then(
												function(response) {
													console.log(response);
													$scope.genderList = [];
													if (response.data.status == "success")
														$scope.genderList = response.data.listObject;
												});
								
								var URI = BASE_URL + ROOT_URL
								+ GETACTIVEOCCUPATIONLIST;
						BillingGenericService
								.serviceAction(METHOD_GET, URI, data,
										NOTIFICATION_MSG_STATUS_FALSE)
								.then(
										function(response) {
											console.log(response);
											$scope.occupationList = [];
											if (response.data.status == "success")
												$scope.occupationList = response.data.listObject;
										});
						
								var URI = BASE_URL + ROOT_URL
										+ GETACTIVENATIONALITYLIST;
								BillingGenericService
										.serviceAction(METHOD_GET, URI, "",
												NOTIFICATION_MSG_STATUS_FALSE)
										.then(
												function(response) {
													console.log(response);
													$scope.nationalityList = [];
													if (response.data.status == "success")
														$scope.nationalityList = response.data.listObject;
												});

								var URI = BASE_URL + ROOT_URL + GETRACELIST;

								BillingGenericService
										.serviceAction(METHOD_POST, URI, commonObj1,
												NOTIFICATION_MSG_STATUS_FALSE)
										.then(
												function(response) {
													console.log(response);
													$scope.raceList = [];
													if (response.data.status == "success")
														$scope.raceList = response.data.listObject;
												});
								
								

								var URI = BASE_URL + ROOT_URL
										+ GETACTIVEMARITALSTATUSLIST;

								BillingGenericService
										.serviceAction(METHOD_GET, URI, data,
												NOTIFICATION_MSG_STATUS_FALSE)
										.then(
												function(response) {
													console.log(response);
													$scope.maritalStatusList = [];
													if (response.data.status == "success")
														$scope.maritalStatusList = response.data.listObject;
												});
								

								var URI = BASE_URL + ROOT_URL
										+ GETACTIVEIDENTIFICATION;
								BillingGenericService
								.serviceAction(METHOD_GET, URI, data,
										NOTIFICATION_MSG_STATUS_FALSE)
								.then(
										function(response) {
											console.log(response);
											$scope.identificationList = [];
											if (response.data.status == "success")
												$scope.identificationList = response.data.listObject;
										});

								var URI = BASE_URL + ROOT_URL
										+ GETACTIVECOUNTRYLIST;								
								BillingGenericService
								.serviceAction(METHOD_GET, URI, data,
										NOTIFICATION_MSG_STATUS_FALSE)
								.then(
										function(response) {
											console.log(response);
											$scope.countryList = [];
											if (response.data.status == "success")
												$scope.countryList = response.data.listObject;
										});

							}

							$scope.init();
							
							$scope.calculateAge= function()
							{
								var dt = moment($scope.datepicker).format('MM/DD/YYYY');
								$scope.age = $rootScope.getAge(dt);
								
							}
							
							$scope.myFunct = function(age) {
									
									var currentYear = (new Date()).getFullYear();
								    var finalYear = currentYear - age;
								    //alert(finalYear);
								    $scope.datepicker = new Date("01/01/"+finalYear);
								}
							
							
							$scope.disableEnableExpiry = function(isExpiryVal)
							{
								 var splittedStringArray = isExpiryVal.split("_");
								if(splittedStringArray[1] == 'Y')
								{
									$scope.expiryDateDisabled = false;
								}
								else
									{
									 $scope.expiryDateDisabled = true;
									}
							}
							
							$scope.hideShowAlise = function(vipVal)
							{
								if(vipVal == 'Y')
									$scope.aliseFlag = true;
								else
									$scope.aliseFlag = false;
									
							}
							
							$scope.sameAsLocalCopy = function(vipVal)
							{
								if(vipVal == 'Y')
									{
									
									if($scope.patientObj.countryId != '' )
									$scope.getStateByCountry($scope.patientObj.countryId,'PA');
									
									if($scope.patientObj.stateId != '' )
									$scope.getDistrictByState($scope.patientObj.stateId,'PA');
									
									if($scope.patientObj.districtId != '' )
									$scope.getCityByDistrict($scope.patientObj.districtId,'PA');
									
									if($scope.patientObj.cityId != '' )
									$scope.getAreaByCity($scope.patientObj.cityId,'PA');
									
									$scope.patientObj.permanentAddress = $scope.patientObj.address;
									$scope.patientObj.permanentCountryId = $scope.patientObj.countryId;
									$scope.patientObj.permanentStateId = $scope.patientObj.stateId;
									$scope.patientObj.permanentDistrictId = $scope.patientObj.districtId;
									$scope.patientObj.permanentCityId = $scope.patientObj.cityId;
									$scope.patientObj.permanentAreaId = $scope.patientObj.areaId;
									$scope.patientObj.permanentZipCode = $scope.patientObj.zipCode;
									$scope.patientObj.permanentPhoneCode = $scope.patientObj.phoneCode;
									$scope.patientObj.permanentPhoneNumber = $scope.patientObj.phoneNumber;
									}
								else
									{
									$scope.patientObj.permanentAddress ='';
									$scope.patientObj.permanentCountryId = '';
									$scope.patientObj.permanentStateId ='';
									$scope.patientObj.permanentDistrictId ='';
									$scope.patientObj.permanentCityId ='';
									$scope.patientObj.permanentAreaId ='';
									$scope.patientObj.permanentZipCode ='';
									$scope.patientObj.permanentPhoneCode ='';
									$scope.patientObj.permanentPhoneNumber ='';
									}
							}

							// for get state by country ID
							$scope.getStateByCountry = function(id,type) {
								var data = {
									countryId : id
								};
								var URI = BASE_URL + ROOT_URL
										+ GETSTATELISTBYCOUNTRYID;
								BillingGenericService
								.serviceAction(METHOD_POST, URI, data,
										NOTIFICATION_MSG_STATUS_FALSE)
								.then(
										function(response) {
											//$scope.stateList = [];
											if (response.data.status == "success"){
												if(type == 'P')
													{
													$scope.stateList = [];
													$scope.stateList = response.data.listObject;
													
													}
												else if(type == 'PA')
													{
													$scope.permanentStateList = [];
													$scope.permanentStateList = response.data.listObject;
													}
												else
													{
													$scope.CompanyStateList = [];
													$scope.CompanyStateList = response.data.listObject;
													}
											}
										});


							}

							$scope.getDistrictByState = function(id,type) {
								var data = {
									stateId : id
								};
								var URI = BASE_URL + ROOT_URL
										+ GETDISTRICTLISTBYSTATEID;
								BillingGenericService
								.serviceAction(METHOD_POST, URI, data,
										NOTIFICATION_MSG_STATUS_FALSE)
								.then(
										function(response) {
											
											if (response.data.status == "success")
											{
												//$scope.districtList = response.data.listObject;
												if(type == 'P')
												{
												$scope.districtList = [];
												$scope.districtList = response.data.listObject;
												
												}
												else if(type == 'PA')
												{
												$scope.permanentDistrictList = [];
												$scope.permanentDistrictList = response.data.listObject;
												}
											else
												{
												$scope.companyDistrictList = [];
												$scope.companyDistrictList = response.data.listObject;
												}
											}
										});
								

							}

							$scope.getCityByDistrict = function(id,type) {

								var data = {
									districtId : id
								};
								var URI = BASE_URL + ROOT_URL
										+ GETCITYLISTBYDISTRICTID;
								BillingGenericService
								.serviceAction(METHOD_POST, URI, data,
										NOTIFICATION_MSG_STATUS_FALSE)
								.then(
										function(response) {
											
											if (response.data.status == "success")
												//$scope.cityList = response.data.listObject;
											{
												//$scope.districtList = response.data.listObject;
												if(type == 'P')
												{
												 $scope.cityList = [];
												 $scope.cityList = response.data.listObject;
												
												}
												else if(type == 'PA')
												{
												$scope.permanentCityList = [];
												$scope.permanentCityList = response.data.listObject;
												}
											else
												{
												$scope.CompanyCityList = [];
												$scope.CompanyCityList = response.data.listObject;
												}
											}
										});

							}
							$scope.getAreaByCity = function(id,type) {

								var data = {
									cityId : id
								};
								var URI = BASE_URL + ROOT_URL
										+ GETAREALISTBYCITY;
								BillingGenericService
								.serviceAction(METHOD_POST, URI, data,
										NOTIFICATION_MSG_STATUS_FALSE)
								.then(
										function(response) {
											
											if (response.data.status == "success")
												//$scope.areaList = response.data.listObject;
											{
												if(type == 'P')
												{
												$scope.areaList = [];
												 $scope.areaList = response.data.listObject;
												
												}
												else if(type == 'PA')
												{
												$scope.permanentAreaList = [];
												$scope.permanentAreaList = response.data.listObject;
												}
											else
												{
												$scope.CompanyAreaList = [];
												$scope.CompanyAreaList = response.data.listObject;
												}
											}
										});

							}

							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.savePatientRegistration();
							}

						/*	$scope.selectItem = function(item) {

								$scope.itemKinSelecteds = [];
								// If checkbox is checked
								if ($scope.kinDetails) {
									$scope.itemKinSelecteds = item;
								}
								console.log($scope.itemKinSelecteds);
							}*/
							
							$scope.savePatientRegistration = function()
							{
								//alert("IN SAVE"+ $scope.datepickerExpiry);
								//console.log($scope.patientRegistrationform.$error.required);
								//console.log($scope.patientRegistrationform);
								//console.log($scope.patientObj);
								$scope.$broadcast('show-errors-check-validity');
								$scope.tab=1;
						        if($scope.patientRegistrationform.$valid){
						           $scope.tab=2;			           
						        	 if($scope.patientRegistrationform2.$valid){
						        		 //test start
						        		 if($scope.patientObj.email != '')
						           			{
							           		 var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
							           		 var email = $scope.patientObj.email;
							           		    if (!filter.test(email)) {
							           		     $scope.tab=1;
						        				    growl.error(EMAIL_MSG,{
														title : ERROR_MSG
													});
						        				    $('#email').focus();
						        				    return false;
							           		    }
							           		    else{
							           		     if($scope.patientObj.phoneNumber != '')
									        		{
									        			 var val = $scope.patientObj.phoneNumber;
									        			 if (/^\d{10}$/.test(val)) {

									        				 $scope.storePatientDetails();
									        				} else {
									        				   
									        				    $scope.tab=2;
									        				    growl.error(PHONE_NUMBER_MSG,{
																	title : ERROR_MSG
																});
									        				    $('#phoneNumber').focus();
									        				    return false;
									        				}
									        		}
							           		     else
							           		    	 {
							           		    	 $scope.storePatientDetails();
							           		    	 }
							           		    }
						           			}
						        		 else
						        			 {
						        			 if($scope.patientObj.phoneNumber != '')
								        		{
								        			 var val = $scope.patientObj.phoneNumber;
								        			 if (/^\d{10}$/.test(val)) {

								        				 $scope.storePatientDetails();
								        				} else {
								        				   
								        				    $scope.tab=2;
								        				    growl.error(PHONE_NUMBER_MSG,{
																title : ERROR_MSG
															});
								        				    $('#phoneNumber').focus();
								        				    return false;
								        				}
								        		}
						        			 else
						        				 {
						        				 $scope.storePatientDetails();
						        				 }
						        			 }
						        		 //test end
								        };
						        }
						        
							}
							
							$scope.storePatientDetails = function()
							{
								//alert("IN STORE");
								console.log($scope.patientObj);
								if ($scope.popUpFlag) {
									$scope.showModal = !$scope.showModal;

								} else {
									$scope.currentDate = new Date();
									$scope.currentDate = moment($scope.currentDate)
											.format('DD-MM-YYYY HH:mm:ss');
									
									$scope.patientObj.createdDate = $scope.currentDate;
									$scope.patientObj.updatedDate = $scope.currentDate;
									$scope.patientObj.dob = moment($scope.datepicker).format('DD-MM-YYYY HH:mm:ss');
									/*if($scope.datepickerExpiry != '')
										{
											$scope.patientObj.identificationExpiryDate = moment($scope.datepickerPdd).format('DD-MM-YYYY HH:mm:ss');
										}*/
									var ConDate = $('#datepickerExpiry').val();
									if(ConDate != '')
										$scope.patientObj.identificationExpiryDate = moment($scope.datepickerExpiry).format('DD-MM-YYYY HH:mm:ss');
									
									 var splittedStringArray = $scope.patientObj.identificationTypeId.split("_");
									 $scope.patientObj.identificationTypeId = splittedStringArray[0]; 
									
									 console.log($scope.patientObj);
									//return false;
									 
									 if($scope.patientObj.registrationTypeId == '1' && $scope.patientObj.patientId > 0)
										 {
										 var URI = BASE_URL + ROOT_URL
											+ UPDATEPATIENTREGISTRATION;

									BillingGenericService
											.serviceAction(METHOD_POST,
													URI, $scope.patientObj,
													NOTIFICATION_MSG_STATUS_TRUE)
											.then(
													function(response) {
														console
																.log(response);
														//$rootScope.startLoader();
														if (response.data.status == "success")
															$scope.init();
													});
										 }
									 else if($scope.patientObj.registrationTypeId == '3' || $scope.patientObj.registrationTypeId == '6' )
										 {
										 var URI = BASE_URL + ROOT_URL
											+ CONVERTPATIENT;

									BillingGenericService
											.serviceAction(METHOD_POST,
													URI, $scope.patientObj,
													NOTIFICATION_MSG_STATUS_TRUE)
											.then(
													function(response) {
														console
																.log(response);
														//$rootScope.startLoader();
														if (response.data.status == "success")
															$scope.init();
													});
										 	
										 }
									 else{
										var URI = BASE_URL + ROOT_URL
												+ ADDPATIENTREGISTRATION;

										BillingGenericService
												.serviceAction(METHOD_POST,
														URI, $scope.patientObj,
														NOTIFICATION_MSG_STATUS_TRUE)
												.then(
														function(response) {
															console
																	.log(response);
															//$rootScope.startLoader();
															if (response.data.status == "success")
																$scope.init();
														});
								}
										
								}
							}
							
							$scope.searchPatient = function()
							{
								//Validate for tab1
								//alert($scope.patientObj.registrationTypeId);
								//alert($scope.searchType);
								if($scope.patientObj.registrationTypeId == 1 && $scope.searchType == 1){
								if($scope.patientSearchObj.uhidNumber == '' && $scope.patientSearchObj.patientName == '' && $scope.patientSearchObj.genderId == '' && $scope.patientSearchObj.mobileNumber == '' && $('#datepickerSearch').val() == '' && $scope.patientSearchObj.identificationTypeId == '' && $scope.patientSearchObj.identificationNumber == '')
				           			{
			        			 			//alert($('#datepickerSearch').val());
											growl.error(SearchFilter_REQUIRED,{title : "WARNING!"
											});
											return false;
					           		}
				        		else
					        			{
				        					//alert("Valid");
					        			}
								}
								if($scope.patientObj.registrationTypeId == 1 && $scope.searchType == 2){
									if($scope.employeeSearchObj.typeId == '' && $scope.employeeSearchObj.empCode == '' &&  $scope.employeeSearchObj.employeeName == '' && $scope.employeeSearchObj.specialityId == '' && $scope.employeeSearchObj.indentificationTypeId == '' && $scope.employeeSearchObj.identificationNumber == '' )
					           			{
				        			 			//alert("Invalid");
												growl.error(SearchFilter_REQUIRED,{title : "WARNING!"
												});
												return false;
						           		}
					        		else
						        			{
					        					//alert("Valid");
						        			}
									}
								if($scope.patientObj.registrationTypeId == 1 && $scope.searchType == 3){
									if($scope.dependentSearchObj.typeId == '' && $scope.employeeSearchObj.empCode == '' &&  $scope.dependentSearchObj.employeeName == '' && $scope.dependentSearchObj.dependentName == '' && $scope.dependentSearchObj.identificationTypeId == '' && $scope.dependentSearchObj.identificationNumber == ''  )
					           			{
				        			 			//alert("Invalid");
												growl.error(SearchFilter_REQUIRED,{title : "WARNING!"
												});
												return false;
						           		}
					        		else
						        			{
					        					//alert("Valid");
						        			}
									}
								//Validate for tab2
								if($scope.patientObj.registrationTypeId == 2 && $scope.searchType == 2){
									if($scope.employeeSearchObj.typeId == '' && $scope.employeeSearchObj.empCode == '' &&  $scope.employeeSearchObj.employeeName == '' && $scope.employeeSearchObj.specialityId == '' && $scope.employeeSearchObj.indentificationTypeId == '' && $scope.employeeSearchObj.identificationNumber == '' )
					           			{
				        			 			//alert("Invalid");
												growl.error(SearchFilter_REQUIRED,{title : "WARNING!"
												});
												return false;
						           		}
					        		else
						        			{
					        					//alert("Valid");
						        			}
									}
								if($scope.patientObj.registrationTypeId == 2 && $scope.searchType == 3){
									if($scope.employeeSearchObj.typeId == '' && $scope.employeeSearchObj.empCode == '' &&  $scope.employeeSearchObj.employeeName == '' && $scope.employeeSearchObj.specialityId == '' && $scope.employeeSearchObj.indentificationTypeId == '' && $scope.employeeSearchObj.identificationNumber == '' )
					           			{
				        			 			//alert("Invalid");
												growl.error(SearchFilter_REQUIRED,{title : "WARNING!"
												});
												return false;
						           		}
					        		else
						        			{
					        					//alert("Valid");
						        			}
									}
								//Validate for 3
								if($scope.patientObj.registrationTypeId == 3 && $scope.searchType == 1){
									if($scope.patientSearchObj.uhidNumber == '' && $scope.patientSearchObj.patientName == '' &&  $scope.patientSearchObj.genderId == '' && $scope.patientSearchObj.mobileNumber == '' && $('#datepickerSearch').val() == '' && $scope.patientSearchObj.identificationTypeId == '' && $scope.patientSearchObj.identificationNumber == '' )
					           			{
				        			 			//alert("Invalid");
												growl.error(SearchFilter_REQUIRED,{title : "WARNING!"
												});
												return false;
						           		}
					        		else
						        			{
					        					//alert("Valid");
						        			}
									}
								if($scope.patientObj.registrationTypeId == 3 && $scope.searchType == 2){
									if($scope.patientSearchObj.uhidNumber == '' && $scope.patientSearchObj.patientName == '' &&  $scope.patientSearchObj.genderId == '' && $scope.patientSearchObj.mobileNumber == '' )
					           			{
				        			 			//alert("Invalid");
												growl.error(SearchFilter_REQUIRED,{title : "WARNING!"
												});
												return false;
						           		}
					        		else
						        			{
					        					//alert("Valid");
						        			}
									}
								if($scope.patientObj.registrationTypeId == 3 && $scope.searchType == 3){
									if($scope.patientSearchObj.uhidNumber == '' && $scope.patientSearchObj.patientName == '' &&  $scope.patientSearchObj.genderId == '' && $scope.patientSearchObj.mobileNumber == '' && $('#datepickerSearch').val() == '' && $scope.patientSearchObj.identificationTypeId == '' && $scope.patientSearchObj.identificationNumber == '')
					           			{
				        			 			//alert("Invalid");
												growl.error(SearchFilter_REQUIRED,{title : "WARNING!"
												});
												return false;
						           		}
					        		else
						        			{
					        					//alert("Valid");
						        			}
									}
								//alert($scope.patientObj.registrationTypeId);
								
								switch ($scope.patientObj.registrationTypeId) {
								case 3:
								if($scope.searchType == 0)
									{
									 growl.error("Please Select Conversion Type!!!",{title : "WARNING!"
										});
									 return false;
									}
								else
									{
									switch ($scope.searchType) {
									case '1':
										var ConDate = $('#datepickerSearch').val();
										if(ConDate != '')
										$scope.patientSearchObj.dob = $rootScope.getChangedFormatedDateShalse(ConDate);
										
										 $scope.patientSearchObj.registrationTypeId = 5;
											//return false;
												var URI = BASE_URL + ROOT_URL
														+ GETTEMPORYPATIENTFORPREREGISTRATION;

												BillingGenericService
														.serviceAction(METHOD_POST,
																URI, $scope.patientSearchObj,
																NOTIFICATION_MSG_STATUS_FALSE)
														.then(
																function(response) {
																	$scope.searchPatientList = [];
																	if (response.data.status == "success")
																		$scope.searchPatientList = response.data.listObject;
																});
										break;
									case '2':
										 console.log($scope.patientSearchObj);
										 $scope.patientSearchObj.registrationTypeId = 4;
											//return false;
												var URI = BASE_URL + ROOT_URL
														+ GETTEMPORYPATIENTFORPREREGISTRATION;

												BillingGenericService
														.serviceAction(METHOD_POST,
																URI, $scope.patientSearchObj,
																NOTIFICATION_MSG_STATUS_FALSE)
														.then(
																function(response) {
																	$scope.searchPatientList = [];
																	if (response.data.status == "success")
																		$scope.searchPatientList = response.data.listObject;
																});

										break;
									case '3':
										//alert($scope.datepickerSearch);
										var ConDate = $('#datepickerSearch').val();
										if(ConDate != '')
										$scope.patientSearchObj.dob = $rootScope.getChangedFormatedDateShalse(ConDate);
										
										 $scope.patientSearchObj.registrationTypeId = 2;
											//return false;
												var URI = BASE_URL + ROOT_URL
														+ GETTEMPORYPATIENTFORPREREGISTRATION;

												BillingGenericService
														.serviceAction(METHOD_POST,
																URI, $scope.patientSearchObj,
																NOTIFICATION_MSG_STATUS_FALSE)
														.then(
																function(response) {
																	$scope.searchPatientList = [];
																	if (response.data.status == "success")
																		$scope.searchPatientList = response.data.listObject;
																});
										break;
									default:

									}
										
									}
								break;
								
								case 1:
									if($scope.searchType == 0)
										{
										 growl.error("Please Select Type!!!",{title : "WARNING!"
											});
										 return false;
										}
									else
										{
										switch ($scope.searchType) {
										case '1':
											var ConDate = $('#datepickerSearch').val();
											if(ConDate != '')
											$scope.patientSearchObj.dob = $rootScope.getChangedFormatedDateShalse(ConDate);
											
											 $scope.patientSearchObj.registrationTypeId = 1;
												//return false;
													var URI = BASE_URL + ROOT_URL
															+ GETTEMPORYPATIENTFORPREREGISTRATION;

													BillingGenericService
															.serviceAction(METHOD_POST,
																	URI, $scope.patientSearchObj,
																	NOTIFICATION_MSG_STATUS_FALSE)
															.then(
																	function(response) {
																		$scope.searchPatientList = [];
																		if (response.data.status == "success")
																			$scope.searchPatientList = response.data.listObject;
																		if($scope.searchPatientList.length == 0){
																			growl.warning("No Result Found!",{
																				title : ERROR_MSG
																			});
																		}
																	});
											break;
										case '2':
											var data = {};
											data.organizationId = $scope.organizationId;
											data.identificationTypeId = $scope.employeeSearchObj.identificationTypeId;
											data.identificationNumber = $scope.employeeSearchObj.identificationNumber;
											data.specialityId = $scope.employeeSearchObj.specialityId;
											data.unitId = $scope.unitId;
											data.typeId = $scope.employeeSearchObj.typeId;
											if($scope.employeeSearchObj.typeId == 1)
												{
												data.doctorName = $scope.employeeSearchObj.employeeName;
												data.docCode =  $scope.employeeSearchObj.empCode;
												data.docDesignationId = '0';
								        		data.docCategoryId = '0	';
												var URI = BASE_URL + ROOT_URL
												+ SEARCHDOCTORDETAILSBYCRITERIA;
												}
											if($scope.employeeSearchObj.typeId == 2)
												{
												data.employeeName = $scope.employeeSearchObj.employeeName;
												data.empCode =  $scope.employeeSearchObj.empCode;
												data.empDesignationId = 0;
												data.empCategoryId = 0;
												
												var URI = BASE_URL + ROOT_URL
												+ SEARCHEMPLOYEEBYCRITERIA;
												}
											

									BillingGenericService
											.serviceAction(METHOD_POST,
													URI, data,
													NOTIFICATION_MSG_STATUS_FALSE)
											.then(
													function(response) {
														$scope.searchEmployeeList = [];
														if (response.data.status == "success")
															$scope.searchEmployeeList = response.data.listObject;
														console.log($scope.searchEmployeeList);
													});
											break;
										case '3':
											
											console.log("ff",$scope.dependentSearchObj);
											var URI = BASE_URL + ROOT_URL
											+ SEARCHDEPENDENTDETAILSBYCRITERIA;

											BillingGenericService
													.serviceAction(METHOD_POST,
															URI, $scope.dependentSearchObj,
															NOTIFICATION_MSG_STATUS_FALSE)
													.then(
															function(response) {
																$scope.searchDependentList = [];
																if (response.data.status == "success")
																	$scope.searchDependentList = response.data.listObject;
																console.log($scope.searchDependentList);
															});
											break;
										default:

										}
											
										}
									break;
								case 2:
									
									if($scope.searchType == 0)
									{
										growl.error("Please Select Type!!!",{title : "WARNING!"
										});
									 return false;
									}
								else
									{
									switch ($scope.searchType) {
									case '1':
										var ConDate = $('#datepickerSearch').val();
										if(ConDate != '')
										$scope.patientSearchObj.dob = $rootScope.getChangedFormatedDateShalse(ConDate);
										
										 $scope.patientSearchObj.registrationTypeId = 1;
											//return false;
												var URI = BASE_URL + ROOT_URL
														+ GETTEMPORYPATIENTFORPREREGISTRATION;

												BillingGenericService
														.serviceAction(METHOD_POST,
																URI, $scope.patientSearchObj,
																NOTIFICATION_MSG_STATUS_FALSE)
														.then(
																function(response) {
																	$scope.searchPatientList = [];
																	if (response.data.status == "success")
																		$scope.searchPatientList = response.data.listObject;
																});
										break;
									case '2':
										var data = {};
										data.organizationId = $scope.organizationId;
										data.identificationTypeId = $scope.employeeSearchObj.identificationTypeId;
										data.identificationNumber = $scope.employeeSearchObj.identificationNumber;
										data.specialityId = $scope.employeeSearchObj.specialityId;
										data.unitId = $scope.unitId;
										data.typeId = $scope.employeeSearchObj.typeId;
										if($scope.employeeSearchObj.typeId == 1)
											{
											data.doctorName = $scope.employeeSearchObj.employeeName;
											data.docCode =  $scope.employeeSearchObj.empCode;
											data.docDesignationId = '0';
							        		data.docCategoryId = '0	';
											var URI = BASE_URL + ROOT_URL
											+ SEARCHDOCTORDETAILSBYCRITERIA;
											}
										if($scope.employeeSearchObj.typeId == 2)
											{
											data.employeeName = $scope.employeeSearchObj.employeeName;
											data.empCode =  $scope.employeeSearchObj.empCode;
											data.empDesignationId = 0;
											data.empCategoryId = 0;
											
											var URI = BASE_URL + ROOT_URL
											+ SEARCHEMPLOYEEBYCRITERIA;
											}
										

								BillingGenericService
										.serviceAction(METHOD_POST,
												URI, data,
												NOTIFICATION_MSG_STATUS_FALSE)
										.then(
												function(response) {
													$scope.searchEmployeeList = [];
													if (response.data.status == "success")
														$scope.searchEmployeeList = response.data.listObject;
													console.log($scope.searchEmployeeList);
												});
										break;
									case '3':
										
										console.log("ff",$scope.dependentSearchObj);
										
										var URI = BASE_URL + ROOT_URL
										+ SEARCHDOCTORDETAILSBYCRITERIA;

										BillingGenericService
												.serviceAction(METHOD_POST,
														URI, $scope.dependentSearchObj,
														NOTIFICATION_MSG_STATUS_FALSE)
												.then(
														function(response) {
															$scope.searchDependentList = [];
															if (response.data.status == "success")
																$scope.searchDependentList = response.data.listObject;
															console.log($scope.searchDependentList);
														});
										break;
									default:

									}
										
									}
									break;
								default:
							}
									
							}
							
							$scope.filterFieldsHideShow = function()
							{
								//alert($scope.searchType);
								switch ($scope.searchType) {
								case '1':
									
									$scope.searchPatientList=[];
									$scope.birthDateSearchFlag = true;
									break;
								case '2':
									
									$scope.searchPatientList=[];
									$scope.birthDateSearchFlag = false;
									break;
								case '3':
									
									$scope.searchPatientList=[];
									$scope.birthDateSearchFlag = true;
									break;
								default:

								}
							}
							
							$scope.selectItem = function(item) {
								
								//$scope.itemSelecteds = [];
								// If checkbox is checked
								if ($scope.searchSelectPatient) {
									
									//if()
									
									if($scope.searchType == '2')
										{
										if($scope.patientObj.registrationTypeId == 3){
										$scope.patientObj.isUnknownReg = 'Y';
										$scope.patientObj.firstName = item.patientName;
										$scope.patientObj.mobileNumber = item.mobile;
										$scope.patientObj.registrationTypeId = 4;
										$scope.patientObj.previousId = item.patientId;
										}
										if($scope.patientObj.registrationTypeId == 1){
											$scope.patientObj.firstName = item.firstName;
											$scope.patientObj.mobileNumber = item.mobileNumber;
											$scope.patientObj.registrationTypeId = 1;
											if( $scope.employeeSearchObj.typeId == 2){
												$scope.patientObj.empDocId = item.employeeId;
											}
											else
												{
													$scope.patientObj.empDocId = item.doctorId;
												}
											$scope.patientObj.empDepTypeId = $scope.employeeSearchObj.typeId;
											//$scope.patientObj.empDocDepId = 0;
											}
										if($scope.patientObj.registrationTypeId == 2){
											$scope.patientObj.firstName = item.firstName;
											$scope.patientObj.mobileNumber = item.mobileNumber;
											$scope.patientObj.registrationTypeId = 2;
											if( $scope.employeeSearchObj.typeId == 2){
												$scope.patientObj.empDocId = item.employeeId;
											}
											else
												{
													$scope.patientObj.empDocId = item.doctorId;
												}
											$scope.patientObj.empDepTypeId = $scope.employeeSearchObj.typeId;
											//$scope.patientObj.empDocDepId = 0;
											}
										}
									else if($scope.searchType == '3')
										{
										if($scope.patientObj.registrationTypeId == 3){
										$scope.patientObj.isOtcReg = 'Y';
										$scope.patientObj.firstName = item.firstName;
										$scope.patientObj.mobileNumber = item.mobileNumber;
										$scope.patientObj.registrationTypeId = 3;
										$scope.patientObj.previousId = item.patientId;
										}
										if($scope.patientObj.registrationTypeId == 1){
											$scope.patientObj.firstName = item.firstName;
											$scope.patientObj.mobileNumber = item.mobileNumber;
											$scope.patientObj.registrationTypeId = 1;
											
													$scope.patientObj.empDocDepId = item.dependentDetailsId;
												
											$scope.patientObj.empDepTypeId = $scope.dependentSearchObj.typeId;
											//$scope.patientObj.empDocDepId = 0;
											}
										if($scope.patientObj.registrationTypeId == 2){
											$scope.patientObj.firstName = item.firstName;
											$scope.patientObj.mobileNumber = item.mobileNumber;
											$scope.patientObj.registrationTypeId = 2;
											
													$scope.patientObj.empDocDepId = item.dependentDetailsId;
												
											$scope.patientObj.empDepTypeId = $scope.dependentSearchObj.typeId;
											//$scope.patientObj.empDocDepId = 0;
											}
										
										}
									else
										{
											if($scope.patientObj.registrationTypeId == 3){
												$scope.patientObj.patientId = item.patientId;
												$scope.patientObj.isPreReg = 'Y';
												$scope.patientObj.firstName = item.firstName;
												$scope.patientObj.mobileNumber = item.mobileNumber;
												$scope.patientObj.registrationTypeId = 6;
											}
											else
												{
												
												$scope.patientObj.patientId = item.patientId;
												$scope.patientObj.patientDetailsId = item.patientDetailsId;
												$scope.patientObj.firstName = item.firstName;
												$scope.patientObj.mobileNumber = item.mobileNumber;
												$scope.patientObj.uhidNumber = item.uhidNumber;
												}
										}
									
									$scope.patientObj.genderId = item.genderId.toString();
									
									$scope.age = item.ageString;
									$scope.datepicker =  new Date(item.dob);
									$scope.patientObj.address = item.address;
									$scope.patientObj.aliseName = item.aliseName;
									
									if($scope.patientObj.registrationTypeId != 1 && $scope.searchType != '3'){
									if(item.countryId != ''){
										$scope.patientObj.countryId = item.countryId.toString();
										
									}
									
									if($scope.stateId != ''){
										$scope.getStateByCountry(item.countryId,'P');
										$scope.patientObj.stateId = item.stateId.toString();
									}
									
									if(item.districtId != ''){
										$scope.getDistrictByState(item.stateId,'P');
										$scope.patientObj.districtId = item.districtId.toString();
									}
									
									if(item.cityId != '')
										$scope.getCityByDistrict(item.districtId,'P');
										$scope.patientObj.cityId = item.cityId.toString();
									
									if(item.areaId != ''){
										$scope.getAreaByCity(item.cityId,'P');
										$scope.patientObj.areaId = item.areaId.toString();
									}
									
									if(item.companyCountryId != null){
										$scope.patientObj.companyCountryId = item.companyCountryId.toString();
									}
									
									if(item.companyStateId != null){
										$scope.getStateByCountry(item.companyCountryId,'C');
										$scope.patientObj.companyStateId = item.companyStateId.toString();
									}
									

									if(item.companyDistrictId != null){
										$scope.getDistrictByState(item.companyStateId,'C');
										$scope.patientObj.companyDistrictId = item.companyDistrictId.toString();
									}
									
									if(item.companyCityId != null){
										$scope.getCityByDistrict(item.companyDistrictId,'C');
										$scope.patientObj.companyCityId = item.companyCityId.toString();
									}
									
									if(item.companyAreaId != null){
										$scope.getAreaByCity(item.companyCityId,'C');
										$scope.patientObj.companyAreaId = item.companyAreaId.toString();
									}
									
									if(item.permanentCountryId != ''){
										$scope.patientObj.permanentCountryId = item.permanentCountryId.toString();
									}
									
									if(item.permanentStateId != ''){
										$scope.getStateByCountry(item.permanentCountryId,'PA');
										$scope.patientObj.permanentStateId = item.permanentStateId.toString();
									}
									
									if(item.permanentDistrictId != ''){
										$scope.getDistrictByState(item.permanentStateId,'PA');
										$scope.patientObj.permanentDistrictId = item.permanentDistrictId.toString();
									}
									
									if(item.permanentCityId != ''){
										$scope.getCityByDistrict(item.permanentDistrictId,'PA');
										$scope.patientObj.permanentCityId = item.permanentCityId.toString();
									}
									
									if(item.permanentAreaId != ''){
										$scope.getAreaByCity(item.permanentCityId,'PA');
										$scope.patientObj.permanentAreaId = item.permanentAreaId.toString();
									}
									
									if(item.maritalStatusId != '')
										$scope.patientObj.maritalStatusId = item.maritalStatusId.toString();
									
									if(item.nationalityId != '')
										$scope.patientObj.nationalityId = item.nationalityId.toString();
									
									if(item.nationalityId != '')
										$scope.patientObj.nationalityId = item.nationalityId.toString();
									
									
									
									if(item.raceId != '')
										$scope.patientObj.raceId = item.raceId.toString();
									
								}
									if(item.prefixId != '')
										$scope.patientObj.prefixId = item.prefixId.toString();
									
									if(item.genderId != '')
										$scope.patientObj.genderId = item.genderId.toString();
									
									
									if(item.identificationExpiryDate != ''){
										$scope.datepickerExpiry = new Date(item.identificationExpiryDate);
										
									}
									
									
								
									$scope.patientObj.companyAddress = item.companyAddress;
									$scope.patientObj.companyMobileNumber = item.companyMobileNumber;
									$scope.patientObj.companyName = item.companyName;
									$scope.patientObj.companyZipCode = item.companyZipCode;
									$scope.patientObj.countryCallingCode = item.countryCallingCode;
									$scope.patientObj.email = item.email;
									$scope.patientObj.identificationNumber = item.identificationNumber;
									$scope.patientObj.isVip = item.isVip;
									$scope.patientObj.lastName = item.lastName;
									$scope.patientObj.middleName = item.middleName;
									$scope.patientObj.permanentAddress = item.permanentAddress;
									$scope.patientObj.permanentPhoneCode = item.permanentPhoneCode;
									$scope.patientObj.permanentPhoneNumber = item.permanentPhoneNumber;
									$scope.patientObj.permanentZipCode = item.permanentZipCode;
									$scope.patientObj.phoneCode = item.phoneCode;
									$scope.patientObj.phoneNumber = item.phoneNumber;
									$scope.patientObj.zipCode = item.zipCode;
									$scope.patientObj.identificationTypeId = item.identificationTypeId+'_'+item.isExpiryRequired;
									$scope.patientObj.occupationId = item.occupationId.toString();
									if(item.isVip == 'Y'){
									$scope.aliseFlag = true;
									$scope.patientObj.aliseName = item.aliseName;
									
									if(item.isExpiryRequired == 'Y')
										$scope.expiryDateDisabled = false;
									}
									
								}
							}
							
							/*TAB CODE ADDED BY JYOTI KADAM 
							DATE: 08-02-2018*/
							/******* START TAB FOR ALL PAGES IN TREE LEVEL *******/
					        	/*First Level*/
						    	$scope.firstTabLevel = 1 ;
								
								$scope.setFirstTabLevel= function(newTab)
								{
									//$scope.patientObj.registrationTypeId = 1;
									//alert($scope.patientObj.registrationTypeId);
									$scope.searchPatientList = [];
									if (newTab == 1) {
									$("#case-sheet").css("background-color",
											"#5F9EA0");
									$("#complaints").css("background-color",
											"#5ab7c8");
									$("#History").css("background-color",
											"#5ab7c8");
									$scope.patientObj.registrationTypeId = 1;
									$scope.searchType = 0;
								} else if (newTab == 2) {
									$("#case-sheet").css("background-color",
											"#5ab7c8");
									$("#complaints").css("background-color",
											"#5F9EA0");
									$("#History").css("background-color",
											"#5ab7c8");
									$scope.patientObj.registrationTypeId = 2;
									$scope.searchType = 0;
								} else {
									$("#case-sheet").css("background-color",
											"#5ab7c8");
									$("#complaints").css("background-color",
											"#5ab7c8");
									$("#History").css("background-color",
											"#5F9EA0");
									$scope.patientObj.registrationTypeId = 3;
									$scope.searchType = 0;
								}
									$scope.firstTabLevel = newTab;
								};
								
								$scope.isSetFirstTabLevel = function(tabNum)
								{
									
									return $scope.firstTabLevel === tabNum;
								};
								
								/*Second Level
								$scope.secondTabLevel = 1 ;
								
								$scope.setSecondTabLevel = function(newTab)
								{
								
									$scope.secondTabLevel = newTab;
								};
								
								$scope.isSetSecondTabLevel = function(tabNum)
								{
									return $scope.secondTabLevel === tabNum;
								};
								
								
								Third Level
								$scope.thirdTabLevel = 1 ;
								
								$scope.setThirdTabLevel = function(newTab)
								{
									
									$scope.thirdTabLevel = newTab;
								};
								
								$scope.isSetThirdTabLevel = function(tabNum)
								{
									return $scope.thirdTabLevel === tabNum;
								};
								
								
								
								FORTH Level
								$scope.forthTabLevel = 1 ;
								
								$scope.setForthTabLevel = function(newTab)
								{
									$scope.forthTabLevel = newTab;
								};
								
								$scope.isSetForthTabLevel = function(tabNum)
								{
									return $scope.forthTabLevel === tabNum;
								};*/
							
						/******* END TAB FOR ALL PAGES IN TREE LEVEL *******/
						
						} ]);



