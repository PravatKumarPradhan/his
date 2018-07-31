'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:passIssuedController
 * @description # passIssuedController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'passIssuedController',
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

							$scope.openDatePicker = function(index) {
								// alert("in");
								// $scope.expiry.opened = true;
								for (var i = $scope.passIssuedList.length - 1; i >= 0; i--) {
									if (i === index) {

										$scope.txtNewDate.opened[i] = true;
									} else {
										$scope.txtNewDate.opened[i] = false;
									}
								}
							};

							$scope.txtNewDate = {
								opened : [ false ],

							};

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
								$scope.admissiontype = {}
								$scope.saveBtnFlag = false;
								$scope.updateBtnFlag = true;

								$scope.search = {
									floorId : 0,
									wardId : 0,
									bedId : 0
								}

								// for popup
								$scope.showModal = false;
								$scope.buttonClicked = "";
								$scope.popUpFlag = true;
								// end for popup

								var data = {};

								var commonObj = {
									unitId : $scope.unitId,
									organizationId : $scope.organizationId
								};

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
																			newArr.bedCategoryDesc = value.bedCategoryDesc;
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
							$scope.getBedByFloorWardId = function() {

								var commonData = {
									unitId : $scope.unitId,
									organizationId : $scope.organizationId,
									wardId : $scope.search.wardId
								};

								var URI = BASE_URL + ROOT_URL
										+ GETBEDLISTBYFLOORWARDID;
								GenericService
										.serviceAction("POST", URI, commonData)
										.then(
												function(response) {
													$scope.bedList = [];

													if (response.data.status == "success") {
														$scope.bedList = response.data.listObject;

													}
												});
							}

							$scope.getAdmittedPatientByFloorWardBedId = function() {
								var data = {
									organizationId : $scope.organizationId,
									unitId : $scope.unitId,
									floorId : $scope.search.floorId,
									wardId : $scope.search.wardId,
									bedId : $scope.search.bedId
								};

								var URI = BASE_URL + ROOT_URL
										+ GETADMITTEDPATIENTLISTBYFLOORWARDBED;
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
																			newArr.identificationNo = value.identificationNo;
																			newArr.bedCategoryDesc = value.bedCategoryDesc;
																			$scope.listOfAdmittedPatientList
																					.push(newArr);

																		});

													}
												})
							}

							$scope.passDetails = function(infoObj) {
								console.log(infoObj);
								if(infoObj.patientId > 0)
								{
								var age = $rootScope
								.getAge(infoObj.dob);
								$scope.patientFullName =infoObj.patientFullName
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
								$scope.admissionId = infoObj.admissionId;

								var data = {
									organizationId : $scope.organizationId,
									unitId : $scope.unitId,
									admissionId : infoObj.admissionId
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

							$scope.updatePassIssued = function() {

								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								/* console.log($scope.passIssuedStatus); */
								var passFlag = false;
								console.log($scope.passIssuedList);
								var visitorPatientMappersList = [];
								angular
										.forEach(
												$scope.passIssuedList,
												function(value, index) {

													var eD = $rootScope
															.getChangedFormatedDateShalse($scope.patientDischargeDate);
													if (value.passIssuedStatus == 'E') {
														value.txtNewDate = moment(
																value.txtNewDate)
																.format(
																		'DD-MM-YYYY HH:mm:ss');
													}

													var data = {
														admissionId : $scope.admissionId,
														organizationId : $scope.organizationId,
														unitId : $scope.unitId,
														createdBy : $scope.userId,
														updatedBy : $scope.userId,
														passNumber : value.passNumber,
														txtNewDate : value.txtNewDate,
														txtReason : value.txtReason,
														visitorPatientId : value.visitorPatientId,
														visitorPatientMapperId : value.visitorPatientMapperId,
														visitorPassStatus : value.passIssuedStatus,
														visitorPassTypeId : value.visitorPassTypeId,
														expiryDate : eD
																+ ' 00:00:00',
														updatedDate : $scope.currentDate,
														createdDate: $scope.currentDate
													};

													if (value.passIssuedStatus == 'A') {
														if (value.passNumber != value.txtPassNo) {
															growl
																	.error(
																			'Pass Number Mismatch on Pass NO:'
																					+ value.passNumber,
																			{
																				title : 'Error!'
																			});
															passFlag = true;
															return false;
														} else {

															visitorPatientMappersList
																	.push(data);
														}
													} else {
														if (value.passIssuedStatus != null) {
															visitorPatientMappersList
																	.push(data);
														}

													}

												});

								if (passFlag == false) {
									var newData = {
										"visitorDtosList" : visitorPatientMappersList
									};
									console.log(newData);
									var URI = BASE_URL + ROOT_URL
											+ UPDATEVISITORSDETAILS;
									GenericService
											.serviceAction("POST", URI, newData)
											.then(
													function(response) {
														if (response.data.status == "success") {
															growl
																	.success(
																			'Pass Issued Updated sucessfully!!!.',
																			{
																				title : 'Success!'
																			});
															$scope
																	.$broadcast('show-errors-reset');
															$scope.init();
															angular
																	.element(
																			'#passDetails')
																	.modal(
																			'hide');
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
							}

						} ]);
