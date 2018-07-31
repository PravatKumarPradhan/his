'use strict';

/**
 * @Author By Vivek Satle
 * @name myApp.controller:patientAcceptanceController
 * @description #patientAcceptanceController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'patientAcceptanceController',
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
								/*
								 * $scope.unitId =
								 * sessionStorage.getItem("unitId");
								 * $scope.organizationId =
								 * sessionStorage.getItem("organizationId");
								 * $scope.userId =
								 * sessionStorage.getItem("userId");
								 */
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
								$rootScope.itemSelectedsAccept = {};
								$scope.bedTr = {};
								$scope.patientIcuTransfer = {};
								$scope.beditemSelecteds = {};

								var data = {};
								var commonObj = {
									unitId : $scope.unitId,
									organizationId : $scope.organizationId
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
										+ GETACTIVEBEDCATEGORYLISTBYUNIT;
								GenericService
										.serviceAction("POST", URI, commonObj)
										.then(
												function(response) {
													$scope.bedCategoryList = [];

													if (response.data.status == "success") {
														$scope.bedCategoryList = response.data.listObject;

													}
												});

								var URI = BASE_URL + ROOT_URL
										+ GETFINALTRANSFERREQUESTLIST;
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
																			newArr.patientFullName = value.patientName;
																			newArr.dob = value.dob;
																			newArr.genderCode = value.genderCode;
																			newArr.uhidnumber = value.uhidnumber;
																			newArr.patientId = value.patientId;
																			newArr.tPatientId = value.tPatientId;
																			newArr.fromBedId = value.fromBedId;
																			newArr.fromBedNumber = value.fromBedNumber;
																			newArr.admissionId = value.admissionId;
																			newArr.transferId = value.transferId;
																			newArr.prefixCode = value.prefixCode;
																			newArr.fromBedCategoryId = value.fromBedCategoryId;
																			newArr.fromBedCategoryDesc = value.fromBedCategoryDesc;
																			newArr.doctorId = value.doctorId;
																			newArr.admissionDetailsId = value.admissionDetailsId;
																			newArr.transferStatusDesc = value.transferStatusDesc;
																			newArr.fromWardId = value.fromWardId;
																			newArr.fromWardName = value.fromWardName;
																			newArr.transferStatusId = value.transferStatusId;
																			newArr.toBedCategoryId = value.toBedCategoryId;
																			newArr.toBedCategoryDesc = value.toBedCategoryDesc;
																			newArr.toWardId = value.toWardId;
																			newArr.toWardName = value.toWardName;
																			newArr.reasonDesc = value.reasonDesc;
																			newArr.adtRejectReason = value.adtRejectReason;
																			newArr.doctorReasonDesc = value.doctorReasonDesc;
																			newArr.doctorRejectReason = value.doctorRejectReason;
																			newArr.transferRequestId = value.transferRequestId;
																			newArr.toBedNumber = value.toBedNumber;
																			newArr.toBedId = value.toBedId;
																			newArr.doctorName = value.doctorName;
																			newArr.finalNote = value.finalNote;
																			newArr.fromRoomId = value.fromRoomId;
																			newArr.toRoomId = value.toRoomId;
																			newArr.fromFloorId = value.fromFloorId;
																			newArr.toFloorId = value.toFloorId;
																			newArr.toBillingBedCategoryId=value.toBillingBedCategoryId
																			newArr.remark = value.note;
																			$scope.listOfAdmittedPatientList
																					.push(newArr);

																		});

													}
												});

								var URI = BASE_URL + ROOT_URL
										+ GETICUTRANSFERPATIENTLIST;
								GenericService
										.serviceAction("POST", URI, commonObj)
										.then(
												function(response) {
													$scope.transferICUList = [];

													if (response.data.status == "success") {
														$scope.transferICUList = response.data.listObject;

													}
												});

							}

							$scope.init();

							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.acceptRequest();

							}

							$scope.displayNote = function(noteDoctor) {
								$scope.noteDoctor = noteDoctor;
							}

							$scope.reasonPopupDoctor = function(resonDesc,
									rejectNote) {

								$('#doctorReason').val(resonDesc);
								$('#doctorRejectReason').val(rejectNote);
							}
							$scope.reasonPopupADT = function(resonDesc,
									rejectNote) {

								$('#adtReason').val(resonDesc);
								$('#adtRejectReason').val(rejectNote);

							}

							$scope.getAdmissionDataAccept = function(item) {
								//alert("sc");
								//console.log(item);
								$rootScope.itemSelectedsAccept = [];
								$rootScope.itemSelectedsAccept = item;
								console.log($rootScope.itemSelectedsAccept);

							}

							$scope.saveAcceptance = function() {
								console.log($rootScope.itemSelectedsAccept);
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								var tansferNote = $('#noteTransfer').val();
								var data = {
									organizationId : $scope.organizationId,
									unitId : $scope.unitId,
									transferId : $scope.itemSelectedsAccept.transferId,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate,
									transferRequestId : $scope.itemSelectedsAccept.transferRequestId,
									workstationNote : tansferNote,
									toBedId : $scope.itemSelectedsAccept.toBedId,
									admissionId : $scope.itemSelectedsAccept.admissionId,
									toFloorId : $scope.itemSelectedsAccept.toFloorId,
									toWardId : $scope.itemSelectedsAccept.toWardId,
									toRoomId : $scope.itemSelectedsAccept.toRoomId,
									admissionDetailsId : $scope.itemSelectedsAccept.admissionDetailsId,
									fromBedId : $scope.itemSelectedsAccept.fromBedId,
									toBedCategoryId: $scope.itemSelectedsAccept.toBedCategoryId,
									toBillingBedCategoryId:$scope.itemSelectedsAccept.toBillingBedCategoryId

								};

								console.log(data);
								//return false;
								var URI = BASE_URL + ROOT_URL
										+ ACCEPTFINALTRANSFERREQUEST;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {

													if (response.data.status == "success") {
														growl
																.success(
																		'Sucessfully!!!.',
																		{
																			title : 'Success!'
																		});
														//commonNote
														angular.element(
																'#commonNote')
																.modal('hide');
														$scope.init();
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

							$scope.saveAcceptReject = function() {

								console.log($rootScope.itemSelectedsAccept);
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								var noteReject = $('#noteReject').val();
								var rejectReasonId = $('#rejectReasonId').val();
								var data = {
									organizationId : $scope.organizationId,
									unitId : $scope.unitId,
									transferId : $scope.itemSelectedsAccept.transferId,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate,
									transferRequestId : $scope.itemSelectedsAccept.transferRequestId,
									workstationNote : noteReject,
									workstationReasonId : rejectReasonId,
									toBedId : $scope.itemSelectedsAccept.toBedId,

								};

								console.log(data);
								//	return false;
								var URI = BASE_URL + ROOT_URL
										+ REJECTFINALTRANSFERREQUEST;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {

													if (response.data.status == "success") {
														growl
																.success(
																		'Sucessfully!!!.',
																		{
																			title : 'Success!'
																		});
														//accepatReject
														angular
																.element(
																		'#accepatReject')
																.modal('hide');
														$scope.init();
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

							//for ICU Start

							$scope.bedAllocastionTransfer = function(icuTypId) {
								
								var data = {
									organizationId : $scope.organizationId,
									unitId : $scope.unitId,
									icuTypeId : icuTypId
								}

								var URI = BASE_URL + ROOT_URL
										+ GETACTIVEBEDLISTBYICUTYPEID;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.activeBedList = [];

													if (response.data.status == "success") {
														$scope.activeBedList = response.data.listObject;
														/*angular
														.forEach(
																$scope.activeBedList,
																function(
																		value,
																		index) {
																	
																if(value.bedId == toBedId)
																	{
																	var data = {bedId:value.bedId,
																	wardId : value.wardId,
																	roomId : value.roomId,
																	billingBedCategoryId:value.billingBedCategoryId}
																	$scope.beditemSelecteds = data;
																	}
																	console.log($scope.beditemSelecteds);
																	
																});*/

													}
												});

							}

							$scope.selectItem = function(item) {
								console.log(item);
								$scope.itemSelecteds = [];
								// If checkbox is checked
								if ($scope.patientIcuTransfer) {
									$scope.itemSelecteds = item;
								}
							}

							$scope.selectItemBed = function(item) {
								console.log(item);
								$scope.beditemSelecteds = [];
								// If checkbox is checked
								if ($scope.bedTr) {
									$scope.beditemSelecteds = item;
								}
							}

							$scope.selectedBedObject = function() {
								console.log($scope.beditemSelecteds);
								angular.element('#bedAllocationForTransfer')
										.modal('hide');
							}

							$scope.acceptRequest = function() {

								if (Object.keys($scope.itemSelecteds).length === 0) {
									growl
											.error(
													'Please select atleast one patient!!!.',
													{
														title : 'Error!'
													});
								} else {
									if (Object.keys($scope.beditemSelecteds).length === 0) {
										growl
												.error(
														'Please Allocate bed before accept!!!.',
														{
															title : 'Error!'
														});
									} else {
										if ($scope.popUpFlag) {
											$scope.showModal = !$scope.showModal;

										} else {

											$scope.currentDate = new Date();
											$scope.currentDate = moment(
													$scope.currentDate).format(
													'DD-MM-YYYY HH:mm:ss');

											var data = {
												organizationId : $scope.organizationId,
												unitId : $scope.unitId,
												toBedCategoryId : $scope.beditemSelecteds.bedCategoryId,
												toBedId : $scope.beditemSelecteds.bedId,
												toWardId : $scope.beditemSelecteds.wardId,
												toRoomId : $scope.beditemSelecteds.roomId,
												toBillingBedCategoryId : $scope.beditemSelecteds.billingBedCategoryId,
												updatedBy : $scope.userId,
												updatedDate : $scope.currentDate,
												transferId : $scope.itemSelecteds.transferId,
												transferRequestId : $scope.itemSelecteds.transferRequestId,
												transferTypeId : 3,
												admissionId :  $scope.itemSelecteds.admissionId,
												toFloorId : $scope.beditemSelecteds.floorId,
												fromBedId : $scope.itemSelecteds.fromBedId,
												admissionDetailsId : $scope.itemSelecteds.admissionDetailsId,
												toBedCategoryId: $scope.beditemSelecteds.bedCategoryId,
												toBillingBedCategoryId:$scope.beditemSelecteds.billingBedCategoryId
											}
											console.log(data);
											//return false;
											var URI = BASE_URL
													+ ROOT_URL
													+ ACCEPTFINALTRANSFERREQUEST;
											GenericService
													.serviceAction("POST", URI,
															data)
													.then(
															function(response) {

																if (response.data.status == "success") {
																	growl
																			.success(
																					'Transfer Request accepted!!!.',
																					{
																						title : 'Success!'
																					});
																	$scope
																			.init();
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

								}

							}

							//For ICU End
							
							$scope.searchGroupList = function(keyword) {

								var data = {
									patientName : keyword,
									unitId : $scope.unitId,
									// visitType :
									// $scope.selectedPageNameForAdmissionNote,
									organizationId : $scope.organizationId
								}
								console.log(data);

								var URI = BASE_URL + ROOT_URL
										+ GETADMITTEDPATIENTBYKEWWORD;
								return GenericService.serviceAction("POST",
										URI, data).then(function(response) {

									if (response.data.status == "success") {
										// alert("ff");
										// console.log(response.data.listObject);
										return response.data.listObject;

									}
								});
								// return data="[{name:'test'}]";
							}
							
							$scope.selectPatientId = function(info, model,
									label) {
								var data = {
										admissionId:info.admissionId,
										organizationId:$scope.organizationId,
										unitId : $scope.unitId
										};
								var URI = BASE_URL + ROOT_URL
								+ GETICUTRANSFERREQUESTLISTBYADMISSIONID;
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
																	newArr.patientFullName = value.patientName;
																	newArr.dob = value.dob;
																	newArr.genderCode = value.genderCode;
																	newArr.uhidnumber = value.uhidnumber;
																	newArr.patientId = value.patientId;
																	newArr.tPatientId = value.tPatientId;
																	newArr.fromBedId = value.fromBedId;
																	newArr.fromBedNumber = value.fromBedNumber;
																	newArr.admissionId = value.admissionId;
																	newArr.transferId = value.transferId;
																	newArr.prefixCode = value.prefixCode;
																	newArr.fromBedCategoryId = value.fromBedCategoryId;
																	newArr.fromBedCategoryDesc = value.fromBedCategoryDesc;
																	newArr.doctorId = value.doctorId;
																	newArr.admissionDetailsId = value.admissionDetailsId;
																	newArr.transferStatusDesc = value.transferStatusDesc;
																	newArr.fromWardId = value.fromWardId;
																	newArr.fromWardName = value.fromWardName;
																	newArr.transferStatusId = value.transferStatusId;
																	newArr.toBedCategoryId = value.toBedCategoryId;
																	newArr.toBedCategoryDesc = value.toBedCategoryDesc;
																	newArr.toWardId = value.toWardId;
																	newArr.toWardName = value.toWardName;
																	newArr.reasonDesc = value.reasonDesc;
																	newArr.adtRejectReason = value.adtRejectReason;
																	newArr.doctorReasonDesc = value.doctorReasonDesc;
																	newArr.doctorRejectReason = value.doctorRejectReason;
																	newArr.transferRequestId = value.transferRequestId;
																	newArr.toBedNumber = value.toBedNumber;
																	newArr.toBedId = value.toBedId;
																	newArr.doctorName = value.doctorName;
																	newArr.finalNote = value.finalNote;
																	newArr.fromRoomId = value.fromRoomId;
																	newArr.toRoomId = value.toRoomId;
																	newArr.fromFloorId = value.fromFloorId;
																	newArr.toFloorId = value.toFloorId;
																	newArr.toBillingBedCategoryId=value.toBillingBedCategoryId
																	newArr.remark = value.note;
																	$scope.listOfAdmittedPatientList
																			.push(newArr);

																});

											}
										});

						var URI = BASE_URL + ROOT_URL
								+ GETFINALTRANSFERREQUESTLISTBYADMISSIONID;
						GenericService
								.serviceAction("POST", URI, data)
								.then(
										function(response) {
											$scope.transferICUList = [];

											if (response.data.status == "success") {
												$scope.transferICUList = response.data.listObject;

											}
										});
								
								
							}
							

						} ]);
