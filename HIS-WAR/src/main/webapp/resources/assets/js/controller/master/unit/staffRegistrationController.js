'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:staffMasterController
 * @description #staffMasterController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'staffRegistrationController',
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
						'$timeout',
						function($scope, $http, $localStorage, $sessionStorage,
								$cookies, $rootScope, GenericService,
								BillingGenericService, growl, $state,
								$stateParams,$timeout) {

							/* init() function for form object create. */
							$scope.init = function() {
								
								$scope.staffRegistrationFlag  = false;
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
								
								
								//for date of joining
								$scope.openDatePickerDOJ = function() {
									$scope.datepickerDOJ.opened = true;
								};

								$scope.datepickerDOJ = {
									opened : false
								};
								
								
								//for next of kin
								$scope.openDatePickerDob = function(index) {
									// alert("in");
									// $scope.expiry.opened = true;
									for (var i = $scope.dataList.length - 1; i >= 0; i--) {
										if (i === index) {

											$scope.dobKin.opened[i] = true;
										} else {
											$scope.dobKin.opened[i] = false;
										}
									}
								};

								$scope.dobKin = {
									opened : [ false ],

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
								
								$scope.fieldsDisable = false;
								
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
						        
						        $scope.employeeSearchObj = {
						        		"employeeName":'',
						        		"specialityId":'0',
						        		"empDesignationId":'0',
						        		"empCategoryId":'0',
						        		"organizationId":$scope.organizationId,
						        		"unitId" : $scope.unitId,
						        		"typeId" : 2
						        }
								
								$scope.employeeObj = {

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
									isVip : 'N',
									address : '',
									countryId : '',
									stateId : '',
									districtId : '',
									cityId : '',
									areaId : '',
									zipCode : '',
									phoneCode : '',
									phoneNumber : '',
									organizationId : $scope.organizationId,
									unitId : $scope.unitId,
									updatedBy : $scope.userId,
									createdBy : $scope.userId,
									status : 'A',
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
									employeeId:'',
									employeeDetailsId:'',
									specialityId:'',
									subSpecialityId:'',
									empCategoryId:'',
									empDesignationId:'',
									empCode:'',
									dateOfJoining:'',
									accessCardNumber:'',
									bankName:'',
									accountNumber:'',
									bankBranchName:'',
									ifscCode:'',
									barCode:'',
									vipRemark:'',
									typeId:'2'
									
									
								};
								
								//For Kin Object
								$scope.dataList = [ {
									prefixId : '',
									firstName : '',
									middleName : '',
									lastName : '',
									mobileNo : '',
									genderId : '',
									dobKin : '',
									occupationId : '',
									relationId : '',
									isNok : 'N',
									typeId:'2',
									identificationTypeId:'',
									identificationNumber:'',
									createdDate:moment($scope.currentDate)
									.format('DD-MM-YYYY HH:mm:ss'),
									createdBy:$scope.userId,
									updatedDate:moment($scope.currentDate)
									.format('DD-MM-YYYY HH:mm:ss'),
									updatedBy:$scope.userId,
									status:'A',
									organizationId:$scope.organizationId,
									unitId:$scope.unitId
									
								} ];
								

								var commonObj = {
									unitId : $scope.unitId,
									organizationId : $scope.organizationId
								};
								var commonObj1 = {
									organizationId : $scope.organizationId
								};
								
								var commonObj2 = {
										unitId : $scope.unitId,
										organizationId : $scope.organizationId,
										typeId : '2'
									};
								var data = {};
								
								/*var URI = BASE_URL + ROOT_URL
								+ GETEMPLOYEEDETAILS;

						BillingGenericService
								.serviceAction(METHOD_POST,
										URI, commonObj2,
										NOTIFICATION_MSG_STATUS_FALSE)
								.then(
										function(response) {
											$scope.searchEmployeeList = [];
											if (response.data.status == "success")
												$scope.searchEmployeeList = response.data.listObject;
										});*/
								
								/*var URI = BASE_URL + ROOT_URL
								+ GETEMPLOYEECATEGORYLIST;
						GenericService
								.serviceAction("GET", URI, data)
								.then(
										function(response) {
											$scope.relationList = [];

											if (response.data.status == "success") {
												$scope.relationList = response.data.listObject;

											}
										});
						
						var URI = BASE_URL + ROOT_URL
						+ GETEMPLOYEEDESIGNATIONLIST;
				GenericService
						.serviceAction("GET", URI, data)
						.then(
								function(response) {
									$scope.relationList = [];

									if (response.data.status == "success") {
										$scope.relationList = response.data.listObject;

									}
								});*/
								
								var URI = BASE_URL + ROOT_URL
								+ GETACTIVERELATIONLIST;
						GenericService
								.serviceAction("GET", URI, data)
								.then(
										function(response) {
											$scope.relationList = [];

											if (response.data.status == "success") {
												$scope.relationList = response.data.listObject;

											}
										});
								
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
							
							$scope.searchEmployee = function()
							{
								//console.log($scope.employeeSearchObj);
								
								var URI = BASE_URL + ROOT_URL
								+ SEARCHEMPLOYEEBYCRITERIA;

						BillingGenericService
								.serviceAction(METHOD_POST,
										URI, $scope.employeeSearchObj,
										NOTIFICATION_MSG_STATUS_FALSE)
								.then(
										function(response) {
											$scope.searchEmployeeList = [];
											if (response.data.status == "success")
												$scope.searchEmployeeList = response.data.listObject;
										});
							}
							
							$scope.add = function() {
								var data = {
										prefixId : '',
										firstName : '',
										middleName : '',
										lastName : '',
										mobileNo : '',
										genderId : '',
										dobKin : '',
										occupationId : '',
										relationId : '',
										isNok : 'N',
										typeId:'2',
										identificationTypeId:'',
										identificationNumber:'',
										createdDate:moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss'),
										createdBy:$scope.userId,
										updatedDate:moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss'),
										updatedBy:$scope.userId,
										status:'A',
										organizationId:$scope.organizationId,
										unitId:$scope.unitId
								};
								$scope.dataList.push(data);
							};

							$scope.remove = function() {
								var newDataList = [];
								var le = $scope.dataList.length;
								var i = 1;
								alert(le);
								if(le > 1){
								angular.forEach($scope.dataList, function(v) {
									if (le != i) {
										newDataList.push(v);
									}
									i++;
								});
							
								$scope.dataList = newDataList;
								}
							};
							
							$scope.getSubSpecialityBySpeciality = function(id)
							{
								var data = {
										unitId : $scope.unitId,
										organizationId : $scope.organizationId
									};
									data.specialityId = id;
									var URI = BASE_URL + ROOT_URL
											+ GETSUBSPECIALITYBYSPECIALITYID;

									
									BillingGenericService
											.serviceAction(METHOD_POST, URI, data,
													NOTIFICATION_MSG_STATUS_FALSE)
											.then(
													function(response) {
														console.log(response);
														$scope.subSpecialityList = [];
														// $rootScope.startLoader();
														if (response.data.status == "success")
															$scope.subSpecialityList = response.data.listObject;
													});
								
							}
							
							$scope.showStaffRegistrationSearch = function()
							{
								$rootScope.startLoader();
								$scope.staffRegistrationFlag  = false;
								$timeout( function(){
	                            	  $rootScope.stopLoader();
	                            },2000);
								
							}
							
							$scope.showStaffRegistrationDiv = function()
							{
								$rootScope.startLoader();
								$scope.staffRegistrationFlag  = true;
								$scope.fieldsDisable = false;
								$timeout( function(){
	                            	  $rootScope.stopLoader();
	                            },2000);
							}
							
							
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
							
							$scope.sameAsLocalCopy = function(sameLoc)
							{
								if(sameLoc == 'Y')
									{
									
									if($scope.employeeObj.countryId != '' )
									$scope.getStateByCountry($scope.employeeObj.countryId,'PA');
									
									if($scope.employeeObj.stateId != '' )
									$scope.getDistrictByState($scope.employeeObj.stateId,'PA');
									
									if($scope.employeeObj.districtId != '' )
									$scope.getCityByDistrict($scope.employeeObj.districtId,'PA');
									
									if($scope.employeeObj.cityId != '' )
									$scope.getAreaByCity($scope.employeeObj.cityId,'PA');
									
									$scope.employeeObj.permanentAddress = $scope.employeeObj.address;
									$scope.employeeObj.permanentCountryId = $scope.employeeObj.countryId;
									$scope.employeeObj.permanentStateId = $scope.employeeObj.stateId;
									$scope.employeeObj.permanentDistrictId = $scope.employeeObj.districtId;
									$scope.employeeObj.permanentCityId = $scope.employeeObj.cityId;
									$scope.employeeObj.permanentAreaId = $scope.employeeObj.areaId;
									$scope.employeeObj.permanentZipCode = $scope.employeeObj.zipCode;
									$scope.employeeObj.permanentPhoneCode = $scope.employeeObj.phoneCode;
									$scope.employeeObj.permanentPhoneNumber = $scope.employeeObj.phoneNumber;
									}
								else
									{
									$scope.employeeObj.permanentAddress ='';
									$scope.employeeObj.permanentCountryId = '';
									$scope.employeeObj.permanentStateId ='';
									$scope.employeeObj.permanentDistrictId ='';
									$scope.employeeObj.permanentCityId ='';
									$scope.employeeObj.permanentAreaId ='';
									$scope.employeeObj.permanentZipCode ='';
									$scope.employeeObj.permanentPhoneCode ='';
									$scope.employeeObj.permanentPhoneNumber ='';
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
								$scope.saveEmployeeRegistration();
							}

						
							$scope.updateStatus = function(empId,type)
							{
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								var data = {
									employeeId : empId,
									status : type,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate,
									organizationId:$scope.organizationId,
									unitId : $scope.unitId,
									typeId : "2"
								}
								console.log(data);
								//return false;
								var URI = BASE_URL + ROOT_URL
										+ UPDATEEMPLOYEESTATUS;
								BillingGenericService
										.serviceAction(METHOD_POST, URI, data,
												NOTIFICATION_MSG_STATUS_TRUE)
										.then(
												function(response) {

												});
							}
							
							$scope.showUpdateBtn = function(item,type)
							{
								if(type == 'V')
									{
										$scope.fieldsDisable = true;
									}
								else
									{
										$scope.fieldsDisable = false;
									}
								$scope.employeeObj = {

										prefixId :item.prefixId.toString(),
										firstName :item.firstName,
										middleName : item.middleName,
										lastName : item.lastName,
										genderId :  item.genderId.toString(),
										countryCallingCode : item.countryCallingCode,
										email : item.email,
										nationalityId : item.nationalityId.toString(),
										raceId : item.raceId.toString(),
										maritalStatusId : item.maritalStatusId.toString(),
										mobileNumber : item.mobileNumber,
										dob : '',
										identificationTypeId : item.identificationTypeId.toString(),
										identificationNumber : item.identificationNumber,
										isVip : item.isVip,
										address : item.address,
										countryId : item.countryId.toString(),
										stateId : item.stateId.toString(),
										districtId : item.districtId.toString(),
										cityId : item.cityId.toString(),
										areaId : item.areaId.toString(),
										zipCode : item.zipCode,
										phoneCode : item.phoneCode,
										phoneNumber : item.phoneNumber,
										organizationId : $scope.organizationId,
										unitId : $scope.unitId,
										updatedBy : $scope.userId,
										createdBy :  item.createdBy,
										status : 'A',
										permanentAddress:item.permanentAddress,
										permanentCountryId:item.permanentCountryId.toString(),
										permanentStateId:item.permanentStateId.toString(),
										permanentDistrictId:item.permanentDistrictId.toString(),
										permanentCityId:item.permanentCityId.toString(),
										permanentAreaId:item.permanentAreaId.toString(),
										permanentZipCode:item.permanentZipCode,
										permanentPhoneCode:item.permanentPhoneCode,
										permanentPhoneNumber:item.permanentPhoneNumber,
										aliseName:item.aliseName,
										identificationExpiryDate:'',
										occupationId:item.occupationId.toString(),
										employeeId:item.employeeId,
										employeeDetailsId:item.employeeDetailsId,
										specialityId:item.specialityId.toString(),
										subSpecialityId:item.subSpecialityId.toString(),
										empCategoryId:item.empCategoryId.toString(),
										empDesignationId:item.empDesignationId.toString(),
										empCode:item.empCode,
										dateOfJoining:'',
										accessCardNumber:item.accessCardNumber,
										bankName:item.bankName,
										accountNumber:item.accountNumber,
										bankBranchName:item.bankBranchName,
										ifscCode:item.ifscCode,
										barCode:item.barCode,
										vipRemark:item.vipRemark,
										typeId:'2',
										createdDate : item.createdDate
										
										
									};
								$scope.age = item.ageString;
								$scope.datepicker = new Date(item.dob);
								//alert($scope.datepicker);
								$scope.datepickerDOJ = new Date(item.dateOfJoining);
								$scope.datepickerExpiry = new Date(item.identificationExpiryDate);
								//$scope.dataList = item.dependentDetailsDtosList;
								$scope.getSubSpecialityBySpeciality(item.specialityId);
								if(item.countryId > 0)
									{
										$scope.getStateByCountry(item.countryId,'P');
										$scope.getDistrictByState(item.stateId,'P');
										$scope.getCityByDistrict(item.districtId,'P');
										$scope.getAreaByCity(item.cityId,'P');
									}
								if(item.permanentCountryId > 0)
								{
									$scope.getStateByCountry(item.permanentCountryId,'PA');
									$scope.getDistrictByState(item.permanentStateId,'PA');
									$scope.getCityByDistrict(item.permanentDistrictId,'PA');
									$scope.getAreaByCity(item.permanentCityId,'PA');
								}
								
								$scope.dataList = [];
								if(item.dependentDetailsDtosList.length > 0)
									{
								angular.forEach(item.dependentDetailsDtosList, function(value,key) {
									var data = {};
									data.prefixId = value.prefixId.toString();
									data.firstName = value.firstName;
									data.middleName  = value.middleName;
									data.lastName = value.lastName;
									data.mobileNo = value.mobileNo;
									data.genderId = value.genderId.toString();
									data.dobKin  = new Date(value.dobKin);
									data.occupationId = value.occupationId.toString();
									data.relationId  = value.relationId.toString();
									data.isNok = value.isNok;
									data.typeId = '2',
									data.identificationTypeId = value.identificationTypeId.toString();
									data.identificationNumber = value.identificationNumber;
									data.updatedDate = moment($scope.currentDate)
									.format('DD-MM-YYYY HH:mm:ss');
									data.updatedBy=$scope.userId;
									data.status='A';
									data.organizationId=$scope.organizationId;
									data.unitId = $scope.unitId;
									data.createdDate = moment($scope.currentDate)
									.format('DD-MM-YYYY HH:mm:ss');
									data.createdBy=$scope.userId;
									$scope.dataList.push(data);
								});
							}
								else
									{
									var data = {
											prefixId : '',
											firstName : '',
											middleName : '',
											lastName : '',
											mobileNo : '',
											genderId : '',
											dobKin : '',
											occupationId : '',
											relationId : '',
											isNok : 'N',
											typeId:'2',
											identificationTypeId:'',
											identificationNumber:'',
											createdDate:moment($scope.currentDate)
											.format('DD-MM-YYYY HH:mm:ss'),
											createdBy:$scope.userId,
											updatedDate:moment($scope.currentDate)
											.format('DD-MM-YYYY HH:mm:ss'),
											updatedBy:$scope.userId,
											status:'A',
											organizationId:$scope.organizationId,
											unitId:$scope.unitId
									};
									$scope.dataList.push(data);
									}
								$scope.tab = 1;
								$scope.staffRegistrationFlag = true;
							}
							
							$scope.saveEmployeeRegistration = function()
							{
								//console.log($scope.patientRegistrationform.$error.required);
								//console.log($scope.patientRegistrationform);
								//console.log($scope.employeeObj);
								$scope.$broadcast('show-errors-check-validity');
								$scope.tab=1;
						        if($scope.employeeRegistrationform.$valid){
						        	//alert('dss00');
						           $scope.tab=2;			           
						        	 if($scope.employeeRegistrationform2.$valid){
						        		 $scope.tab=4;
						        		 if($scope.employeeRegistrationform4.$valid){
						        		 //test start
						        		 if($scope.employeeObj.email != '')
						           			{
							           		 var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
							           		 var email = $scope.employeeObj.email;
							           		    if (!filter.test(email)) {
							           		     $scope.tab=1;
						        				    growl.error(EMAIL_MSG,{
														title : ERROR_MSG
													});
						        				    $('#email').focus();
						        				    return false;
							           		    }
							           		    else{
							           		     if($scope.employeeObj.phoneNumber != '')
									        		{
									        			 var val = $scope.employeeObj.phoneNumber;
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
						        			 if($scope.employeeObj.phoneNumber != '')
								        		{
								        			 var val = $scope.employeeObj.phoneNumber;
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
						        		 //test end
								        };
						        }
						        
							}
							
							$scope.storePatientDetails = function()
							{
								//alert("IN STORE");
								//return false;
								console.log($scope.employeeObj);
								if ($scope.popUpFlag) {
									$scope.showModal = !$scope.showModal;

								} else {
									$scope.currentDate = new Date();
									$scope.currentDate = moment($scope.currentDate)
											.format('DD-MM-YYYY HH:mm:ss');
									 if($scope.employeeObj.employeeId == ''){
									$scope.employeeObj.createdDate = $scope.currentDate;
									 }
									$scope.employeeObj.updatedDate = $scope.currentDate;
									$scope.employeeObj.dob = moment($scope.datepicker).format('DD-MM-YYYY HH:mm:ss');
									$scope.employeeObj.dateOfJoining = moment($scope.datepickerDOJ).format('DD-MM-YYYY HH:mm:ss');
									/*if($scope.datepickerExpiry != '')
										{
											$scope.employeeObj.identificationExpiryDate = moment($scope.datepickerPdd).format('DD-MM-YYYY HH:mm:ss');
										}*/
									var ConDate = $('#datepickerExpiry').val();
									if(ConDate != '')
										$scope.employeeObj.identificationExpiryDate = moment($scope.datepickerExpiry).format('DD-MM-YYYY HH:mm:ss');
									
									 var splittedStringArray = $scope.employeeObj.identificationTypeId.split("_");
									 $scope.employeeObj.identificationTypeId = splittedStringArray[0]; 
									
									 
									 console.log($scope.dataList);
										angular.forEach($scope.dataList, function(
												value, index) {
											value.dobKin = moment(value.dobKin).format(
													'DD-MM-YYYY HH:mm:ss');
										});
									  $scope.employeeObj.dependentDetailsDtosList = $scope.dataList;
									 console.log("EMP OBJ",$scope.employeeObj);
									//return false;
									 
									 if($scope.employeeObj.employeeId > 0)
										 {
										 var URI = BASE_URL + ROOT_URL
											+ UPDATEEMPLOYEEDETAILS;

									BillingGenericService
											.serviceAction(METHOD_POST,
													URI, $scope.employeeObj,
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
												+ EMPLOYEEREGISTRATION;

										BillingGenericService
												.serviceAction(METHOD_POST,
														URI, $scope.employeeObj,
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
								
								//Second Level
								$scope.secondTabLevel = 1 ;
								
								$scope.setSecondTabLevel = function(newTab)
								{
								
									$scope.secondTabLevel = newTab;
								};
								
								$scope.isSetSecondTabLevel = function(tabNum)
								{
									return $scope.secondTabLevel === tabNum;
								};
								
								
								//Third Level
								$scope.thirdTabLevel = 1 ;
								
								$scope.setThirdTabLevel = function(newTab)
								{
									
									$scope.thirdTabLevel = newTab;
								};
								
								$scope.isSetThirdTabLevel = function(tabNum)
								{
									return $scope.thirdTabLevel === tabNum;
								};
								
								
								
								//FORTH Level
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
						
						} ]);



