'use strict';

/**
 * @Author By Vivek Satle
 * @name myApp.controller:transferRequestController
 * @description #transferRequestController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'transferRequestController',
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
							
							if($cookies.get('bedManagmentCookies') != null)
							{
							$scope.bedManagmentCookiesObj =JSON.parse($cookies.get('bedManagmentCookies'));
							}
							else
								{
								$scope.bedManagmentCookiesObj =null;
								}
							
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
								$rootScope.itemSelectedsAccept = {};

								var data = {};
								var commonObj = {
									unitId : $scope.unitId,
									organizationId : $scope.organizationId
								};
								
								var wardList = [];
								wardList.push(1);
								wardList.push(2);
								wardList.push(3);
								wardList.push(4);
								wardList.push(5);
								wardList.push(6);
								wardList.push(7);
								var commonObjInitiat = {
										unitId : $scope.unitId,
										organizationId : $scope.organizationId,
										wardList : wardList
									};
								console.log(commonObjInitiat);
								//return false;
								
								/*var transferTypeIdList = [];
								transferTypeIdList.push(1);
								transferTypeIdList.push(3);*/
								var commonObjTransfer = {
										unitId : $scope.unitId,
										organizationId : $scope.organizationId,
										transferTypeId : 1
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
								
								if($scope.bedManagmentCookiesObj != null)
								{
									var commonObjInitiatForBM = {
											admissionId:$scope.bedManagmentCookiesObj.admissionId,
											organizationId:$scope.organizationId,
											unitId : $scope.unitId
											};
									var commonObjTransferBM  = {
											admissionId:$scope.bedManagmentCookiesObj.admissionId,
											organizationId:$scope.organizationId,
											unitId : $scope.unitId
									};
									
									console.log($scope.bedManagmentCookiesObj.admissionId);
									
									var URI = BASE_URL + ROOT_URL +GETINITIATETRANSFERREQUESTFORBM;
									//		+ GETTRANSFERPATIENTLIST;
									GenericService
											.serviceAction("POST", URI, commonObjInitiatForBM)
											.then(
													function(response) {
														$scope.listOfAdmittedPatientInitiatList = [];
														$scope.admittedPatientInitiatList = [];

														if (response.data.status == "success") {
															$scope.admittedPatientInitiatList = response.data.listObject;
															angular
																	.forEach(
																			$scope.admittedPatientInitiatList,
																			function(
																					value,
																					index) {

																				var newArr = {};
																				newArr.patientFullName = value.pFirstName;
																				newArr.dob = value.dob;
																				newArr.genderCode = value.genderCode;
																				newArr.uhidnumber = value.uhidnumber;
																				newArr.patientId = value.patientId;
																				newArr.tPatientId = value.tPatientId;
																				newArr.bedNumber = value.bedNumber;
																				newArr.admissionId = value.admissionId;
																				newArr.transferId = value.transferId;
																				newArr.hierarchyId = value.hierarchyId;
																				newArr.prefixCode = value.prefixCode;
																				newArr.bedCategoryId = value.bedCategoryId;
																				newArr.bedCategoryDesc = value.bedCategoryDesc;
																				newArr.doctorId = value.doctorId;
																				newArr.admissionDetailsId = value.admissionDetailsId;
																				newArr.transferStatusDesc = value.transferStatusDesc;
																				newArr.wardId = value.wardId;
																				newArr.wardName = value.wardName;
																				newArr.transferStatusId = value.transferStatusId;
																				newArr.reasonDesc = value.reasonDesc;
																				newArr.transferTypeId = value.transferTypeId;
																				
																				$scope.listOfAdmittedPatientInitiatList
																						.push(newArr);

																			});

														}
													});
									var URI = BASE_URL + ROOT_URL
									+ GETTRANSFERREQUESTLISTBYADMISSIONIDFORB2B;
								GenericService
										.serviceAction("POST", URI, commonObjTransferBM)
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
																			newArr.patientFullName = value.pFirstName;
																			newArr.dob = value.dob;
																			newArr.genderCode = value.genderCode;
																			newArr.uhidnumber = value.uhidnumber;
																			newArr.patientId = value.patientId;
																			newArr.tPatientId = value.tPatientId;
																			newArr.bedNumber = value.bedNumber;
																			newArr.admissionId = value.admissionId;
																			newArr.transferId = value.transferId;
																			newArr.hierarchyId = value.hierarchyId;
																			newArr.prefixCode = value.prefixCode;
																			newArr.bedCategoryId = value.bedCategoryId;
																			newArr.bedCategoryDesc = value.bedCategoryDesc;
																			newArr.doctorId = value.doctorId;
																			newArr.admissionDetailsId = value.admissionDetailsId;
																			newArr.transferStatusDesc = value.transferStatusDesc;
																			newArr.wardId = value.wardId;
																			newArr.wardName = value.wardName;
																			newArr.transferStatusId = value.transferStatusId;
																			newArr.toBedCategoryDesc = value.toBedCategoryDesc;
																			newArr.toBedNumber = value.toBedNumber;
																			newArr.toWardName = value.toWardname;
																			newArr.rejectReasonDesc = value.rejectReasonDesc;
																			newArr.reasonDesc = value.reasonDesc;
																			newArr.adtReasonDesc = value.adtReasonDesc;
																			newArr.adtRejectReason =  value.adtRejectReason;
																			newArr.doctorReasonDesc =  value.doctorReasonDesc;
																			newArr.doctorRejectReason =  value.doctorRejectReason;
																			newArr.transferRequestId =  value.transferRequestId;
																			newArr.toBedId = value.toBedId;
																			newArr.toWardId = value.toWardId;
																			newArr.toFloorId = value.toFloorId;
																			newArr.toRoomId = value.toRoomId;
																			newArr.fromBedId = value.fromBedId;
																			newArr.toBedCategoryId = value.toBedCategoryId;
																			newArr.toBillingBedCategoryId = value.billingBedCategoryId;
																			newArr.transferTypeId = value.transferTypeId;
																			newArr.remark = value.note;
																			$scope.listOfAdmittedPatientList
																					.push(newArr);

																		});

													}
												});
								
								}
								else
									{
								var URI = BASE_URL+ROOT_URL+GETINITIATETRANSFERREQUEST;
								//		+ GETTRANSFERPATIENTLIST;
								GenericService
										.serviceAction("POST", URI, commonObjInitiat)
										.then(
												function(response) {
													$scope.listOfAdmittedPatientInitiatList = [];
													$scope.admittedPatientInitiatList = [];

													if (response.data.status == "success") {
														$scope.admittedPatientInitiatList = response.data.listObject;
														angular
																.forEach(
																		$scope.admittedPatientInitiatList,
																		function(
																				value,
																				index) {

																			var newArr = {};
																			newArr.patientFullName = value.pFirstName;
																			newArr.dob = value.dob;
																			newArr.genderCode = value.genderCode;
																			newArr.uhidnumber = value.uhidnumber;
																			newArr.patientId = value.patientId;
																			newArr.tPatientId = value.tPatientId;
																			newArr.bedNumber = value.bedNumber;
																			newArr.admissionId = value.admissionId;
																			newArr.transferId = value.transferId;
																			newArr.hierarchyId = value.hierarchyId;
																			newArr.prefixCode = value.prefixCode;
																			newArr.bedCategoryId = value.bedCategoryId;
																			newArr.bedCategoryDesc = value.bedCategoryDesc;
																			newArr.doctorId = value.doctorId;
																			newArr.admissionDetailsId = value.admissionDetailsId;
																			newArr.transferStatusDesc = value.transferStatusDesc;
																			newArr.wardId = value.wardId;
																			newArr.wardName = value.wardName;
																			newArr.transferStatusId = value.transferStatusId;
																			newArr.reasonDesc = value.reasonDesc;
																			
																			$scope.listOfAdmittedPatientInitiatList
																					.push(newArr);

																		});

													}
												});
								
								var URI = BASE_URL + ROOT_URL
								+ GETTRANSFERPATIENTLIST;
							GenericService
									.serviceAction("POST", URI, commonObjTransfer)
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
																		newArr.patientFullName = value.pFirstName;
																		newArr.dob = value.dob;
																		newArr.genderCode = value.genderCode;
																		newArr.uhidnumber = value.uhidnumber;
																		newArr.patientId = value.patientId;
																		newArr.tPatientId = value.tPatientId;
																		newArr.bedNumber = value.bedNumber;
																		newArr.admissionId = value.admissionId;
																		newArr.transferId = value.transferId;
																		newArr.hierarchyId = value.hierarchyId;
																		newArr.prefixCode = value.prefixCode;
																		newArr.bedCategoryId = value.bedCategoryId;
																		newArr.bedCategoryDesc = value.bedCategoryDesc;
																		newArr.doctorId = value.doctorId;
																		newArr.admissionDetailsId = value.admissionDetailsId;
																		newArr.transferStatusDesc = value.transferStatusDesc;
																		newArr.wardId = value.wardId;
																		newArr.wardName = value.wardName;
																		newArr.transferStatusId = value.transferStatusId;
																		newArr.toBedCategoryDesc = value.toBedCategoryDesc;
																		newArr.toBedNumber = value.toBedNumber;
																		newArr.toWardName = value.toWardname;
																		newArr.rejectReasonDesc = value.rejectReasonDesc;
																		newArr.reasonDesc = value.reasonDesc;
																		newArr.adtReasonDesc = value.adtReasonDesc;
																		newArr.adtRejectReason =  value.adtRejectReason;
																		newArr.doctorReasonDesc =  value.doctorReasonDesc;
																		newArr.doctorRejectReason =  value.doctorRejectReason;
																		newArr.transferRequestId =  value.transferRequestId;
																		newArr.toBedId = value.toBedId;
																		/*if(value.toBedCategoryId != null)
																				{
																				newArr.requiredBedCategory = value.toBedCategoryId+'_'+value.toHierarchyId;
																				}
																			else
																				{
																				newArr.requiredBedCategory =value.toBedCategoryId;
																				}
																			if(value.reasonId != null)
																			{
																				newArr.reasonId = value.reasonId.toString();
																			}
																		else
																			{
																			newArr.reasonId =value.reasonId;
																			}*/

																		newArr.remark = value.note;

																		/*if(value.transferStatus != "P")
																			{
																			newArr.disable = true;
																			}
																		else
																			{
																			newArr.disable = false;
																			}*/
																		newArr.transferTypeId = value.transferTypeId;
																		$scope.listOfAdmittedPatientList
																				.push(newArr);

																	});

												}
											});
							}
								

							}

							$scope.init();

							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.submitRequest();
							}

							$scope.selectPatient = function(item) {

								$scope.itemSelecteds = [];
								// If checkbox is checked
								if ($scope.admittedPatient) {
									$scope.itemSelecteds = item;

								}
							}

							$scope.submitRequest = function() {

								if (Object.keys($scope.itemSelecteds).length === 0) {
									growl
											.error(
													'Please select atleast one patient!!!.',
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
											treatingDoctorId : $scope.itemSelecteds.doctorId,
											organizationId : $scope.organizationId,
											unitId : $scope.unitId,
											admissionId : $scope.itemSelecteds.admissionId,
											transferId : $scope.itemSelecteds.transferId,
											fromWardId : $scope.itemSelecteds.wardId,
											reasonId : $scope.itemSelecteds.reasonId,
											note : $scope.itemSelecteds.remark,
											authorizedBy : $scope.userId,
											fromBedCategoryId : $scope.itemSelecteds.bedCategoryId,
											//transferType :
											createdBy : $scope.userId,
											createdDate : $scope.currentDate,
											updatedBy : $scope.userId,
											updatedDate : $scope.currentDate,
											transferTypeId : 1
										}

										console
												.log($scope.itemSelecteds.requiredBedCategory);
										var array = $scope.itemSelecteds.requiredBedCategory
												.split('_');
										//alert($scope.itemSelecteds.hierarchyId);
										//alert(array[1]);
										if (array[1] > $scope.itemSelecteds.hierarchyId) {
											console.log(array[0] + "__"
													+ array[1]);
											data.toBedCategoryId = array[0];
											data.transferStatusId = 3;
										} else {
											console.log(array[0] + "__"
													+ array[1]);
											data.toBedCategoryId = array[0];
											data.transferStatusId = 4;
										}
										console.log(data);
										//return false;
										var URI = BASE_URL + ROOT_URL
												+ ADDTRANSFERREQUEST;
										GenericService
												.serviceAction("POST", URI,
														data)
												.then(
														function(response) {

															if (response.data.status == "success") {
																growl
																		.success(
																				'Transfer Request sent sucessfully!!!.',
																				{
																					title : 'Success!'
																				});
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
								}
							}

							$scope.finalTransfer = function() {
								var data = {
									organizationId : $scope.organizationId,
									unitId : $scope.unitId,
									transferdId : $scope.transferdId,
									tranferRequestId : $scope.transferReqestId,

								};

							}
							$scope.adtReason = '';
							$scope.adtRejectReason = '';
							$scope.doctorReason = '';
							$scope.doctorRejectReason = '';
							$scope.reasonPopupDoctor = function(resonDesc,rejectNote)
							{
								
								$('#doctorReason').val(resonDesc);
								$('#doctorRejectReason').val(rejectNote);
							}
							$scope.reasonPopupADT = function(resonDesc,rejectNote)
							{
								
								$('#adtReason').val(resonDesc);
								$('#adtRejectReason').val(rejectNote);
								
							}
							
							$scope.getAdmissionDataAccept = function(item)
							{
								//alert("sc");
								//console.log(item);
								$rootScope.itemSelectedsAccept = [];
								$rootScope.itemSelectedsAccept = item;
								console.log($rootScope.itemSelectedsAccept);
								
							}
							
							$scope.saveAcceptance = function()
							{
								console.log($rootScope.itemSelectedsAccept);
								$scope.currentDate = new Date();
								$scope.currentDate = moment(
										$scope.currentDate).format(
										'DD-MM-YYYY HH:mm:ss');
							
								var tansferNote = $('#noteTransfer').val();
								var data = {
										organizationId : $scope.organizationId,
										unitId : $scope.unitId,
										transferId:$scope.itemSelectedsAccept.transferId,
											updatedBy:$scope.userId,
											updatedDate:$scope.currentDate,
												transferRequestId:$scope.itemSelectedsAccept.transferRequestId,
													finalNote :tansferNote											
										
								};
								
								console.log(data);
								//return false;
								var URI = BASE_URL + ROOT_URL
										+ ACCEPTTRANSFERREQUESTONREQUEST;
								GenericService
										.serviceAction("POST", URI,
												data)
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
							
							$scope.saveAcceptReject = function()
							{
								
								console.log($rootScope.itemSelectedsAccept);
								$scope.currentDate = new Date();
								$scope.currentDate = moment(
										$scope.currentDate).format(
										'DD-MM-YYYY HH:mm:ss');
							
								var noteReject = $('#noteReject').val();
								var rejectReasonId = $('#rejectReasonId').val();
								var data = {
										organizationId : $scope.organizationId,
										unitId : $scope.unitId,
										transferId:$scope.itemSelectedsAccept.transferId,
											updatedBy:$scope.userId,
											updatedDate:$scope.currentDate,
												transferRequestId:$scope.itemSelectedsAccept.transferRequestId,
												finalNote :noteReject,
												finalReasonId : rejectReasonId,
												toBedId : $scope.itemSelectedsAccept.toBedId,
										
								};
								
								console.log(data);
							//	return false;
								var URI = BASE_URL + ROOT_URL
										+ REJECTTRANSFERREQUESTONREQUEST;
								GenericService
										.serviceAction("POST", URI,
												data)
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
														angular.element(
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
								var commonObjInitiatForBM = {
										admissionId:info.admissionId,
										organizationId:$scope.organizationId,
										unitId : $scope.unitId
										};
								var commonObjTransferBM  = {
										admissionId:info.admissionId,
										organizationId:$scope.organizationId,
										unitId : $scope.unitId
								};
								
								var URI = BASE_URL + ROOT_URL +GETINITIATETRANSFERREQUESTFORBM;
								//		+ GETTRANSFERPATIENTLIST;
								GenericService
										.serviceAction("POST", URI, commonObjInitiatForBM)
										.then(
												function(response) {
													$scope.listOfAdmittedPatientInitiatList = [];
													$scope.admittedPatientInitiatList = [];

													if (response.data.status == "success") {
														$scope.admittedPatientInitiatList = response.data.listObject;
														angular
																.forEach(
																		$scope.admittedPatientInitiatList,
																		function(
																				value,
																				index) {

																			var newArr = {};
																			newArr.patientFullName = value.pFirstName;
																			newArr.dob = value.dob;
																			newArr.genderCode = value.genderCode;
																			newArr.uhidnumber = value.uhidnumber;
																			newArr.patientId = value.patientId;
																			newArr.tPatientId = value.tPatientId;
																			newArr.bedNumber = value.bedNumber;
																			newArr.admissionId = value.admissionId;
																			newArr.transferId = value.transferId;
																			newArr.hierarchyId = value.hierarchyId;
																			newArr.prefixCode = value.prefixCode;
																			newArr.bedCategoryId = value.bedCategoryId;
																			newArr.bedCategoryDesc = value.bedCategoryDesc;
																			newArr.doctorId = value.doctorId;
																			newArr.admissionDetailsId = value.admissionDetailsId;
																			newArr.transferStatusDesc = value.transferStatusDesc;
																			newArr.wardId = value.wardId;
																			newArr.wardName = value.wardName;
																			newArr.transferStatusId = value.transferStatusId;
																			newArr.reasonDesc = value.reasonDesc;
																			newArr.transferTypeId = value.transferTypeId;
																			
																			$scope.listOfAdmittedPatientInitiatList
																					.push(newArr);

																		});

													}
												});
								var URI = BASE_URL + ROOT_URL
								+ GETTRANSFERREQUESTLISTBYADMISSIONIDFORB2B;
							GenericService
									.serviceAction("POST", URI, commonObjTransferBM)
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
																		newArr.patientFullName = value.pFirstName;
																		newArr.dob = value.dob;
																		newArr.genderCode = value.genderCode;
																		newArr.uhidnumber = value.uhidnumber;
																		newArr.patientId = value.patientId;
																		newArr.tPatientId = value.tPatientId;
																		newArr.bedNumber = value.bedNumber;
																		newArr.admissionId = value.admissionId;
																		newArr.transferId = value.transferId;
																		newArr.hierarchyId = value.hierarchyId;
																		newArr.prefixCode = value.prefixCode;
																		newArr.bedCategoryId = value.bedCategoryId;
																		newArr.bedCategoryDesc = value.bedCategoryDesc;
																		newArr.doctorId = value.doctorId;
																		newArr.admissionDetailsId = value.admissionDetailsId;
																		newArr.transferStatusDesc = value.transferStatusDesc;
																		newArr.wardId = value.wardId;
																		newArr.wardName = value.wardName;
																		newArr.transferStatusId = value.transferStatusId;
																		newArr.toBedCategoryDesc = value.toBedCategoryDesc;
																		newArr.toBedNumber = value.toBedNumber;
																		newArr.toWardName = value.toWardname;
																		newArr.rejectReasonDesc = value.rejectReasonDesc;
																		newArr.reasonDesc = value.reasonDesc;
																		newArr.adtReasonDesc = value.adtReasonDesc;
																		newArr.adtRejectReason =  value.adtRejectReason;
																		newArr.doctorReasonDesc =  value.doctorReasonDesc;
																		newArr.doctorRejectReason =  value.doctorRejectReason;
																		newArr.transferRequestId =  value.transferRequestId;
																		newArr.toBedId = value.toBedId;
																		newArr.toWardId = value.toWardId;
																		newArr.toFloorId = value.toFloorId;
																		newArr.toRoomId = value.toRoomId;
																		newArr.fromBedId = value.fromBedId;
																		newArr.toBedCategoryId = value.toBedCategoryId;
																		newArr.toBillingBedCategoryId = value.billingBedCategoryId;
																		newArr.transferTypeId = value.transferTypeId;
																		newArr.remark = value.note;
																		$scope.listOfAdmittedPatientList
																				.push(newArr);

																	});

												}
											});

								
							}

						} ]);
