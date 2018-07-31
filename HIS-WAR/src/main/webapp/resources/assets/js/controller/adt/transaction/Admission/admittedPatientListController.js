'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:admittedPatientListController
 * @description # admittedPatientListController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'admittedPatientListController',
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
						'$window',
						function($scope, $http, $localStorage, $sessionStorage,
								$cookies, $rootScope, GenericService, growl,
								$state,$window) {

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

								$scope.admittedPatient = {};
								$scope.itemSelecteds = {};
								$scope.visitorPassPopUpFlag = false;
								$scope.consentFormPopUpFlag = false;
								$scope.commonPackagePopUpFlag = false;
								$scope.viewBillPopUpFlag = false;
								$scope.depositeMatrixPopUpFlag = false;
								$scope.commonUploadDocPopUpFlag = false;
								$scope.insurancePopUpFlag = false;
								$scope.assignContractPopUpFlag = false;
								$scope.barCodePopUpFlag = false;
								
								$scope.options =  {
							          width: 1,
							          height: 15,
							          displayValue: false,
							          font: 'monospace',
							          textAlign: 'center',
							          textPosition: 'bottom',
							          fontSize: 10,
							          backgroundColor: '#ffffff',
							          lineColor: '#000000',
							          margin: 0
							        };
								
								
								var data = {};
								var commonObj = {
									unitId : $scope.unitId,
									organizationId : $scope.organizationId
								};

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
										+ GETADMITTEDPATIENTLIST;
								GenericService
										.serviceAction("POST", URI, commonObj)
										.then(
												function(response) {
													$scope.listOfAdmittedPatientList = [];
													$scope.admittedPatientList = [];

													if (response.data.status == "success") {
														$scope.admittedPatientList = response.data.listObject;
														angular
																.forEach(
																		$scope.admittedPatientList,
																		function(
																				value,
																				index) {

																			var newArr = {};
																			newArr.specialityName = value.specialityName;
																			newArr.doctorFullName = value.dFirstName;
																			newArr.patientFullName = value.pFirstName;
																			newArr.reasonDesc = value.reasonDesc;
																			newArr.dob = value.dob;
																			newArr.datepicker = value.doa;
																			newArr.datepickerPdd = value.pdd;
																			newArr.genderCode = value.genderCode;
																			newArr.uhidnumber = value.uhidnumber;
																			newArr.patientId = value.patientId;
																			newArr.tPatientId = value.tPatientId;
																			newArr.paymentEntitlementDesc = value.paymentEntitlementDesc;
																			newArr.bedNumber = value.bedNumber;
																			newArr.admissionId = value.admissionId;
																			newArr.prefixCode = value.prefixCode;
																			newArr.identificationNo = value.identificationNo;
																			newArr.wardName = value.wardName;
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

							$scope.getWardByFloorId = function() {
								var commonData = {
									unitId : $scope.unitId,
									organizationId : $scope.organizationId,
									floorId : $scope.search.floorId
								};

								var URI = BASE_URL + ROOT_URL
										+ GETWARDLISTBYFLOORIDORGUNITID;
								GenericService
										.serviceAction("POST", URI, commonData)
										.then(
												function(response) {
													$scope.wardList = [];

													if (response.data.status == "success") {
														$scope.wardList = response.data.listObject;

													}
												});
							}

							$scope.search = {
								floorId : 0,
								wardId : 0
							}

							$scope.getAdmittedPatientByFloorWardId = function() {
								var data = {
									organizationId : $scope.organizationId,
									unitId : $scope.unitId,
									floorId : $scope.search.floorId,
									wardId : $scope.search.wardId
								};

								var URI = BASE_URL + ROOT_URL
										+ GETADMITTEDPATIENTLISTBYFLOORWARD;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.listOfAdmittedPatientList = [];
													$scope.admittedPatientList = [];

													if (response.data.status == "success") {
														$scope.admittedPatientList = response.data.listObject;
														angular
																.forEach(
																		$scope.admittedPatientList,
																		function(
																				value,
																				index) {

																			var newArr = {};
																			newArr.specialityName = value.specialityName;
																			newArr.doctorFullName = value.dFirstName;
																			newArr.patientFullName = value.pFirstName;
																			newArr.reasonDesc = value.reasonDesc;
																			newArr.dob = value.dob;
																			newArr.datepicker = value.doa;
																			newArr.datepickerPdd = value.pdd;
																			newArr.genderCode = value.genderCode;
																			newArr.uhidnumber = value.uhidnumber;
																			newArr.patientId = value.patientId;
																			newArr.tPatientId = value.tPatientId;
																			newArr.paymentEntitlementDesc = value.paymentEntitlementDesc;
																			newArr.bedNumber = value.bedNumber;
																			newArr.admissionId = value.admissionId;
																			newArr.prefixCode = value.prefixCode;
																			newArr.wardName = value.wardName;
																			newArr.identificationNo = value.identificationNo;
																			$scope.listOfAdmittedPatientList
																					.push(newArr);

																		});

													}
												})
							}

							$scope.kinDetails = function(infoObj) {
								// alert(infoObj.admissionId);
								if(infoObj.patientId > 0)
								{
								var age = $rootScope
								.getAge(infoObj.dob);
								$scope.patientFullName = infoObj.patientFullName
								+ " "
								+age
								+ "/"
								+ infoObj.genderCode;
								}
							else
								{
								$scope.patientFullName = infoObj.patientFullName
								+ " "
								+ infoObj.dob
								+ "/"
								+ infoObj.genderCode;
								}
								
							
							$scope.uhidNum = infoObj.uhidnumber;
							$scope.doctorFullName =infoObj.doctorFullName;
								$scope.bedNumber = infoObj.bedNumber;
								$scope.dateOfAdmission = infoObj.datepicker;
								$scope.patientDischargeDate = infoObj.datepickerPdd;
								$scope.identificationNo = infoObj.identificationNo;

								var data = {
									organizationId : $scope.organizationId,
									unitId : $scope.unitId,
									admissionId : infoObj.admissionId
								}

								var URI = BASE_URL + ROOT_URL
										+ GETKINDETAILSBYADMISSIONID;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.kinList = [];

													if (response.data.status == "success") {
														$scope.kinList = response.data.listObject;

													}
												});
							}

							$scope.selectPatient = function(item) {

								$scope.itemSelecteds = [];
								// If checkbox is checked
								if ($scope.admittedPatient) {
									$scope.itemSelecteds = item;
									$scope.visitorPassPopUpFlag = true;
									$scope.consentFormPopUpFlag = true;
									$scope.commonPackagePopUpFlag = true;
									$scope.viewBillPopUpFlag = true;
									$scope.depositeMatrixPopUpFlag = true;
									$scope.commonUploadDocPopUpFlag = true;
									$scope.insurancePopUpFlag = true;
									$scope.assignContractPopUpFlag = true;
									$scope.barCodePopUpFlag = true;

								}
							}

							$scope.PopUpOpen = function() {

								if (Object.keys($scope.itemSelecteds).length === 0) {
									growl
											.error(
													'Please select atleast one patient!!!.',
													{
														title : 'Error!'
													});
								} else {

									
									
									if($scope.itemSelecteds.patientId > 0)
										{
										var age = $rootScope
										.getAge($scope.itemSelecteds.dob);
										$scope.patientFullName = $scope.itemSelecteds.patientFullName
										+ " "
										+age
										+ "/"
										+ $scope.itemSelecteds.genderCode;
										}
									else
										{
										$scope.patientFullName = $scope.itemSelecteds.patientFullName
										+ " "
										+ $scope.itemSelecteds.dob
										+ "/"
										+ $scope.itemSelecteds.genderCode;
										}
										
									
									$scope.uhidNum = $scope.itemSelecteds.uhidnumber;
									$scope.doctorFullName =$scope.itemSelecteds.doctorFullName;
									$scope.bedNumber = $scope.itemSelecteds.bedNumber;
									$scope.dateOfAdmission = $scope.itemSelecteds.datepicker;
									$scope.patientDischargeDate = $scope.itemSelecteds.datepickerPdd;
									$scope
											.getPassIssuedList($scope.itemSelecteds.admissionId);

								}

							}
							
							$scope.commonFuntion = function(templateTypeId) {
								if (Object.keys($scope.itemSelecteds).length === 0) {
									growl
											.error(
													'Please select atleast one patient!!!.',
													{
														title : 'Error!'
													});
								} else {

									
									
									if($scope.itemSelecteds.patientId > 0)
										{
										var age = $rootScope
										.getAge($scope.itemSelecteds.dob);
										$scope.patientFullName = $scope.itemSelecteds.patientFullName
										+ " "
										+age
										+ "/"
										+ $scope.itemSelecteds.genderCode;
										}
									else
										{
										$scope.patientFullName = $scope.itemSelecteds.patientFullName
										+ " "
										+ $scope.itemSelecteds.dob
										+ "/"
										+ $scope.itemSelecteds.genderCode;
										}
										
									
									$scope.uhidNum = $scope.itemSelecteds.uhidnumber;
									$scope.doctorFullName =$scope.itemSelecteds.doctorFullName;
									$scope.bedNumber = $scope.itemSelecteds.bedNumber;
									$scope.dateOfAdmission = $scope.itemSelecteds.datepicker;
									$scope.patientDischargeDate = $scope.itemSelecteds.datepickerPdd;
									$scope
											.getPassIssuedList($scope.itemSelecteds.admissionId);
									
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
							
							
							$scope.savePassIssue = function() {
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								var pdd = $rootScope
										.getChangedFormatedDateShalse($scope.itemSelecteds.datepickerPdd);
								var data = {
									organizationId : $scope.organizationId,
									unitId : $scope.unitId,
									visitorPassTypeId : $scope.visitorPassTypeId,
									passNumber : $scope.passNumber,
									admissionId : $scope.itemSelecteds.admissionId,
									expiryDate : pdd + ' 00:00:00',
									createdBy : $scope.userId,
									updatedBy : $scope.userId,
									status : 'A',
									visitorPassStatus : 'I',
									createdDate : $scope.currentDate,
									updatedDate : $scope.currentDate

								};
								console.log(data);
								// return false;
								var URI = BASE_URL + ROOT_URL
										+ ADDVISITORPASSISSUE;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {

													if (response.data.status == "success") {
														growl
																.success(
																		'Pass Issued sucessfully!!!.',
																		{
																			title : 'Success!'
																		});
														$scope
																.$broadcast('show-errors-reset');
														$scope
																.getPassIssuedList($scope.itemSelecteds.admissionId);
													} else {
														growl
																.error(
																		'Something wrongs!!!!.',
																		{
																			title : 'Error!'
																		});
													}
												});
							}

							$scope.getPassIssuedList = function(admissionId) {
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
							}
							
							$scope.generateBarCode = function()
							{
								if (Object.keys($scope.itemSelecteds).length === 0) {
									growl
											.error(
													'Please select atleast one patient!!!.',
													{
														title : 'Error!'
													});
								} else {
								console.log("ffd",$scope.itemSelecteds);
								
								if($scope.itemSelecteds.patientId > 0)
								{
								var age = $rootScope
								.getAge($scope.itemSelecteds.dob);
								$scope.patientFullName = $scope.itemSelecteds.patientFullName
								+ " "
								+age
								+ "/"
								+ $scope.itemSelecteds.genderCode;
								}
							else
								{
								$scope.patientFullName = $scope.itemSelecteds.patientFullName
								+ " "
								+ $scope.itemSelecteds.dob
								+ "/"
								+ $scope.itemSelecteds.genderCode;
								}
								
								$scope.barcode = {
									        type: "code128",
									        code: $scope.itemSelecteds.uhidnumber,
									        hospital: "Ruby Hall",
									        itemCode: $scope.itemSelecteds.admissionId.toString(),
									        batchCode: "123456",
									        patientFullName : $scope.patientFullName,
									        bedNumber:$scope.itemSelecteds.bedNumber,
									        wardName:$scope.itemSelecteds.wardName
									        //expiry: item.expiry,
									        //mrp: self.model.Currency + $filter('number')(item.mrp, 2)
									      }
								/*	      var popup = angular.element('#barcodePreview');
									      popup.modal('show');*/
							}
									      
							}
							
							$scope.printBarCode = function()
							{
								var printContents = $window.document.getElementById('admittedBarCode').innerHTML;
						        var popupWin = $window.open('', '_blank', 'width=300,height=300');
						        popupWin.document.open();
						        popupWin.document.write('<html><head><link rel="stylesheet" type="text/css" href="style.css" /></head><body onload="window.print()">' + printContents + '</body></html>');
						        popupWin.document.close();
							}

						} ]);
