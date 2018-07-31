'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:patientForDischargeController
 * @description # patientForDischargeController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'patientForDischargeController',
				[
						'$scope',
						'$http',
						'$localStorage',
						'$sessionStorage',
						'$cookies',
						'$rootScope',
						'GenericService',
						'growl',
						'$state',
						function($scope, $http, $localStorage, $sessionStorage,
								$cookies, $rootScope, GenericService, growl,
								$state) {

							/* init() function for form object create. */
							$scope.init = function() {

								var cookieObject = $cookies.getObject('cookieObject');
								if(cookieObject == undefined){
									$state.go('login');
									return;
								}
								$scope.unitId = cookieObject.unitId;
								$scope.organizationId = cookieObject.organizationId;
								$scope.userId = 1;

								$rootScope.loginpage = true;
								$scope.saveBtnFlag = false;
								$scope.updateBtnFlag = true;

								// for popup
								$scope.showModal = false;
								$scope.buttonClicked = "";
								$scope.popUpFlag = true;
								// end for popup
								
								//$rootScope.billingFlag = false;
								/*angular.element(document.getElementById('billing'))[0].disabled = true;
								angular.element(document.getElementById('damaContent'))[0].disabled = true;
								angular.element(document.getElementById('vacateBed'))[0].disabled = true;
								angular.element(document.getElementById('deathId'))[0].disabled = true;
								angular.element(document.getElementById('dischargeSlip'))[0].disabled = true;
								angular.element(document.getElementById('dischargeSummary'))[0].disabled = true;
								angular.element(document.getElementById('dischargeMedication'))[0].disabled = true;*/
								$scope.pendingServicePopupFlag =false;

								$scope.dischargePatient = {};
								$scope.itemSelecteds = {};
								$scope.visitorPassPopUpFlag = false;
								$scope.deathPdfViewPopupFlag = false;
								$scope.damaPdfViewPopupFlag = false;
								$scope.dischargePdfViewPopupFlag = false;
								$scope.dischargeSlipPdfViewPopupFlag   = false;
								
								
								$scope.templateObj = {};

								var data = {};
								var commonObj = {
									unitId : $scope.unitId,
									organizationId : $scope.organizationId
								};
								
								
								$scope.cD = new Date();
								$scope.cD = moment($scope.cD)
										.format('DD-MM-YYYY');
								
								var commonObjForDischargeList = {
										unitId : $scope.unitId,
										organizationId : $scope.organizationId,
										dischargeDate : $scope.cD
									};
								

								var URI = BASE_URL + ROOT_URL
										+ GETACTIVEREASONLIST;
								GenericService
										.serviceAction("GET", URI, data)
										.then(
												function(response) {
													$scope.reasonList = [];

													if (response.data.status == "success") {
														$scope.reasonList = response.data.listObject;

													}
												});

								var URI = BASE_URL + ROOT_URL
										+ GETVISITORPASSTYPELIST;
								GenericService
										.serviceAction("POST", URI, commonObj)
										.then(
												function(response) {
													$scope.visitorPassTypeList = [];

													if (response.data.status == "success") {
														$scope.visitorPassTypeList = response.data.listObject;

													}
												});

								var URI = BASE_URL + ROOT_URL
										+ GETDISCHARGEPATIENTLISTFORNURSING;
								GenericService
										.serviceAction("POST", URI, commonObjForDischargeList)
										.then(
												function(response) {
													$scope.listOfAdmittedPatientList = [];
													$scope.admittedPatientList = [];

													if (response.data.status == "success") {
														$scope.filterDate = new Date();
														$scope.filterDate = moment($scope.filterDate)
																.format('DD/MM/YYYY');
														$scope.admittedPatientList = response.data.listObject;
														angular
																.forEach(
																		$scope.admittedPatientList,
																		function(
																				value,
																				index) {

																			var newArr = {};
																			newArr.specialityName = value.specialityName;
																			newArr.dFirstName = value.dFirstName;
																			newArr.pFirstName = value.pFirstName;
																			newArr.reasonDesc = value.reasonDesc;
																			newArr.dob = value.dob;
																			newArr.datepicker = value.doa;
																			newArr.datepickerPdd = value.pdd;
																			newArr.genderCode = value.genderCode;
																			newArr.uhidnumber = value.uhidnumber;
																			newArr.patientId = value.patientId;
																			newArr.paymentEntitlementDesc = value.paymentEntitlementDesc;
																			newArr.bedNumber = value.bedNumber;
																			newArr.admissionId = value.admissionId;
																			newArr.prefixCode = value.prefixCode;
																			newArr.identificationNo = value.identificationNo;
																			newArr.dischargeId = value.dischargeId;
																			newArr.futureDischargeDate = value.futureDischargeDate;
																			newArr.dischargeStatusId = value.dischargeStatusId;
																			newArr.dischargeTypeId = value.dischargeTypeId;
																			newArr.dischargeTypeName = value.dischargeTypeName;
																			newArr.dischargeDesc = value.dischargeDesc;
																			$scope.listOfAdmittedPatientList
																					.push(newArr);

																		});

													}
												});

								var URI = BASE_URL + ROOT_URL
										+ GETACTIVEFLOORLIST;
								GenericService
										.serviceAction("POST", URI, commonObj)
										.then(
												function(response) {
													$scope.activeFloorList = [];

													if (response.data.status == "success") {
														$scope.activeFloorList = response.data.listObject;

													}
												});

							}

							$scope.init();
							
							// function for popup ok button call
							/*$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.cancelPendingService();
							}*/
							
							

							$scope.passDetails = function() {
								console.log($scope.itemSelecteds);
								if (Object.keys($scope.itemSelecteds).length === 0) {
									growl
											.error(
													'Please select atleast one patient!!!.',
													{
														title : 'Error!'
													});
								}
								else{
								
								if($scope.itemSelecteds.patientId > 0)
								{
								var age = $rootScope
								.getAge($scope.itemSelecteds.dob);
								$scope.patientFullName =$scope.itemSelecteds.pFirstName
								+ " "
								+age
								+ "/"
								+ $scope.itemSelecteds.genderCode;
								}
							else
								{
								$scope.patientFullName = $scope.itemSelecteds.pFirstName
								+ " "
								+ $scope.itemSelecteds.dob
								+ "/"
								+ $scope.itemSelecteds.genderCode;
								}
								$scope.uhidNum = $scope.itemSelecteds.uhidnumber;
								$scope.doctorFullName =$scope.itemSelecteds.dFirstName;
								$scope.bedNumber = $scope.itemSelecteds.bedNumber;
								$scope.dateOfAdmission =$scope.itemSelecteds.datepicker;
								$scope.patientDischargeDate = $scope.itemSelecteds.datepickerPdd;
								$scope.identificationNo = $scope.itemSelecteds.identificationNo;
								$scope.admissionId = $scope.itemSelecteds.admissionId;

								var data = {
									organizationId : $scope.organizationId,
									unitId : $scope.unitId,
									admissionId : $scope.itemSelecteds.admissionId
								}

								var URI = BASE_URL + ROOT_URL
										+ GETPASSISSUEDLIST;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.passIssuedList = [];

													if (response.data.status == "success") {
														$scope.passIssuedList = response.data.listObject;

													}
												});
							}
							}
							
							$scope.pendingServies = function()
							{
								
								if (Object.keys($scope.itemSelecteds).length === 0) {
									growl
											.error(
													'Please select atleast one patient!!!.',
													{
														title : 'Error!'
													});
								}
								else{
								console.log($scope.itemSelecteds);
								var data = {
										organizationId : $scope.organizationId,
										unitId : $scope.unitId,
										admissionId : $scope.itemSelecteds.admissionId,
										//patientId : $scope.itemSelecteds.patientId
								};
								console.log(data);
								//return false;
								var URI = BASE_URL + ROOT_URL
								+ GETPATIENTPENDINGSERVICESLIST;
						GenericService
								.serviceAction("POST", URI, data)
								.then(
										function(response) {
											$scope.pendingServicesList = [];

											if (response.data.status == "success") {
												$scope.pendingServicesList = response.data.listObject;
												if($scope.pendingServicesList.length == 0)
													{
													/*$rootScope.billingFlag = false;
													alert($rootScope.billingFlag);*/
													//angular.element(document.getElementById('billing'))[0].disabled = false;
													var myEl = angular.element( document.querySelector( '#billing' ) );
									            	myEl.removeClass('disabled');
									            	var myEl1 = angular.element( document.querySelector( '#dischargeMedication' ) );
									            	myEl1.removeClass('disabled');
									            	var myEl2 = angular.element( document.querySelector( '#dischargeSummary' ) );
									            	myEl2.removeClass('disabled');
									            	var myEl3 = angular.element( document.querySelector( '#dischargeSlip' ) );
									            	myEl3.removeClass('disabled');
													}
												else
													{
													/*$rootScope.billingFlag = true;
													alert($rootScope.billingFlag);*/
													//angular.element(document.getElementById('billing'))[0].disabled = true;
													var myEl = angular.element( document.querySelector( '#billing' ) );
									            	myEl.addClass('disabled');
													}
												
												
												  switch ($scope.itemSelecteds.dischargeTypeId) {
										            case 1:
										               // alert("Selected Case Number is 1");
										                break;
										            case 2:
										               ///alert("Selected Case Number is 2");
										            	var myEl = angular.element( document.querySelector( '#damaContent' ) );
										            	myEl.removeClass('disabled');
										                break;
										            case 3:
										               // alert("Selected Case Number is 2");
										            	//angular.element(document.querySelector('#deathId')).removeClass = "disabled";
										            	var myEl = angular.element( document.querySelector( '#deathId' ) );
										            	myEl.removeClass('disabled');
										                break;
										            case 4:
										                //alert("Selected Case Number is 2");
										                break;
										            default:

										        }

											}
										});
							}
						
							}

							$scope.selectPatient = function(item) {

								$scope.itemSelecteds = [];
								// If checkbox is checked
								if ($scope.dischargePatient) {
									$scope.itemSelecteds = item;
									$scope.pendingServicePopupFlag =true;
									$scope.deathPdfViewPopupFlag = true;
									$scope.damaPdfViewPopupFlag = true;
									$scope.dischargePdfViewPopupFlag = true;
									$scope.dischargeSlipPdfViewPopupFlag   = true;
									$scope.pendingServies();

								}
							}
							
							$scope.finalDischarge = function()
							{
								$state.go('finalDischarge');
							}
							
							
							$scope.commonFuntion = function(templateTypeId) {
								console.log($scope.itemSelecteds);
								if (Object.keys($scope.itemSelecteds).length === 0) {
									growl
											.error(
													'Please select atleast one patient!!!.',
													{
														title : 'Error!'
													});
								}
								else{
								
								if($scope.itemSelecteds.patientId > 0)
								{
								var age = $rootScope
								.getAge($scope.itemSelecteds.dob);
								$scope.patientFullName =$scope.itemSelecteds.pFirstName
								+ " "
								+age
								+ "/"
								+ $scope.itemSelecteds.genderCode;
								}
							else
								{
								$scope.patientFullName = $scope.itemSelecteds.pFirstName
								+ " "
								+ $scope.itemSelecteds.dob
								+ "/"
								+ $scope.itemSelecteds.genderCode;
								}
								$scope.uhidNum = $scope.itemSelecteds.uhidnumber;
								$scope.doctorFullName =$scope.itemSelecteds.dFirstName;
								$scope.bedNumber = $scope.itemSelecteds.bedNumber;
								$scope.dateOfAdmission =$scope.itemSelecteds.datepicker;
								$scope.patientDischargeDate = $scope.itemSelecteds.datepickerPdd;
								$scope.identificationNo = $scope.itemSelecteds.identificationNo;
								$scope.admissionId = $scope.itemSelecteds.admissionId;
								
								var templeteData  = {	
								};
								
								var URI = BASE_URL + ROOT_URL+LIS_UNIT
								+ GET_TEMPLATE+"/"+$scope.organizationId+"/"+$scope.unitId+"/0/"+templateTypeId;
								GenericService
								.serviceAction("GET", URI, templeteData)
								.then(
										function(response) {
											$scope.templateList = [];

											if (response.data.status == "success") {
												$scope.templateList = response.data.listObject;
												//$scope.value =$scope.templateList[0].html;
												
												$scope.templateObj = {
														templetText:$scope.templateList[0].html,
														templeId  : $scope.templateList[0].labTemplateId,
														organizationId : $scope.organizationId,
														unitId : $scope.unitId,
														admissionId : $scope.admissionId,
														typeId  : templateTypeId
												}
												
												console.log("Templete For Dama",$scope.templateList);
												
												/*CKEDITOR.addTemplates("default", {
												    imagesPath:CKEDITOR.getUrl(CKEDITOR.plugins.getPath("templates")+"templates/images/"),
												    templates: $scope.templateList
												   });*/
												
											}
										});


							}
							}
							
							$scope.saveTemplate = function()
							{
								console.log('Final Object Template',$scope.templateObj);
								
								 var popupWinindow = window.open('', '_blank', 'width=600,height=700,scrollbars=no,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
							        popupWinindow.document.open();
							        popupWinindow.document.write('<html><head><link rel="stylesheet" type="text/css" href="style.css" /></head><body onload="window.print()">'+$scope.templateObj.templetText+'</html>');
							        popupWinindow.document.close();
							}
							
							$scope.redirectBilling = function()
							{
								$state.go('IPDCharges.serviceDetailsForIPDCharge')
							}
							
							$scope.redirectMedicationhandOver = function()
							{
								$state.go('dischargeMedicationHandover');
							}
							
							/*$scope.visitorPass = function() {

								if (Object.keys($scope.itemSelecteds).length === 0) {
									growl
											.error(
													'Please select atleast one patient!!!.',
													{
														title : 'Error!'
													});
								} else {

									var age = $rootScope
											.getAge($scope.itemSelecteds.dob);
									$scope.patientFullName = $scope.itemSelecteds.pFirstName
											+ " "
											+ $scope.itemSelecteds.pMiddleName
											+ " "
											+ $scope.itemSelecteds.pLastName
											+ " "
											+ age
											+ "/"
											+ $scope.itemSelecteds.genderCode;
									$scope.uhidNum = $scope.itemSelecteds.uhidnumber;
									$scope.doctorFullName = 'Dr. '
											+ $scope.itemSelecteds.dFirstName
											+ " "
											+ $scope.itemSelecteds.dMiddleName
											+ " "
											+ $scope.itemSelecteds.dLastName;
									$scope.bedNumber = $scope.itemSelecteds.bedNumber;
									$scope.dateOfAdmission = $scope.itemSelecteds.datepicker;
									$scope.patientDischargeDate = $scope.itemSelecteds.datepickerPdd;
									$scope
											.getPassIssuedList($scope.itemSelecteds.admissionId);

								}

							}*/

							$scope.cancelPendingService = function(reasonId,admissionId,patientServiceOrderId) {
								
								//alert(reasonId+'dd'+admissionId+'ggg'+patientServiceOrderId);
								//return false;
								/*if ($scope.popUpFlag) {
									$scope.showModal = !$scope.showModal;

								} 
								else{*/
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								var data = {
									organizationId : $scope.organizationId,
									unitId : $scope.unitId,
									patientSevicesOrderId :patientServiceOrderId,
									reasonId : reasonId,
									admissionId : admissionId,
									orderStatusId:4,
									updatedBy : $scope.userId,
									status : 'A',
									updatedDate : $scope.currentDate

								};
								console.log(data);
								// return false;
								var URI = BASE_URL + ROOT_URL
										+ CANCELPAENDINGSERVICEORDER;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {

													if (response.data.status == "success") {
														growl
																.success(
																		'Service Cancel sucessfully!!!.',
																		{
																			title : 'Success!'
																		});
														$scope.pendingServies();
													} else {
														growl
																.error(
																		'Something wrongs!!!!.',
																		{
																			title : 'Error!'
																		});
													}
												});
								/*}*/
							}

						/*	$scope.getPassIssuedList = function(admissionId) {
								var data = {
									organizationId : $scope.organizationId,
									unitId : $scope.unitId,
									admissionId : admissionId
								};
								var URI = BASE_URL + ROOT_URL
										+ GETPASSISSUEDLIST;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.passIssuedList = [];

													if (response.data.status == "success") {
														$scope.passIssuedList = response.data.listObject;

													}
												});
							}*/

						} ]);
