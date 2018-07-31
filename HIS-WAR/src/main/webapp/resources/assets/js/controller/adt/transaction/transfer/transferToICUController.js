'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:transferToICUController
 * @description #transferToICUController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'transferToICUController',
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
							
							 $scope.dateOptions = {							 
								      formatYear: 'yyyy',
								      showWeeks: false
								  };
								 $scope.openDatePicker = function() {
									// alert("in");
								      $scope.datepicker.opened = true;
								    };
								    $scope.datepicker = {
										      opened: false
										    };

							/* init() function for form object create. */
							$scope.init = function() {

							    
								
								if ($cookies.get('bedManagmentCookies') != null) {
									$scope.bedManagmentCookiesObj = JSON
											.parse($cookies
													.get('bedManagmentCookies'));
									console.log($scope.bedManagmentCookiesObj);
									$scope.patientFullName = $scope.bedManagmentCookiesObj.patientFullName;
									$scope.uhidNum = $scope.bedManagmentCookiesObj.uhidNum;
									$scope.bedNumber = $scope.bedManagmentCookiesObj.bedNumber;
									$scope.doctorFullName = $scope.bedManagmentCookiesObj.doctorFullName;
									$scope.wardName = $scope.bedManagmentCookiesObj.wardName;
									$scope.admissionId  =  $scope.bedManagmentCookiesObj.admissionId;
								  } else {
									$scope.bedManagmentCookiesObj = null;
								}
								
									    
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
								
								$scope.itemSelected = {};
								$scope.patientSelecteds = {};
								$scope.beditemSelecteds ={};
					


								var data = {
										organizationId : $scope.organizationId,
										unitId:$scope.unitId
								};
								
								
								
						
						var URI = BASE_URL + ROOT_URL
						+ GETACTIVEUNITICUTYPELIST;
				GenericService
						.serviceAction("POST", URI, data)
						.then(
								function(response) {
									$scope.icuTypeList = [];

									if (response.data.status == "success") {
										$scope.icuTypeList = response.data.listObject;

									}
								});
				
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
		+ GETICUTRANSFERPATIENTLIST;
GenericService
		.serviceAction("POST", URI, data)
		.then(
				function(response) {
					$scope.transferICUList = [];

					if (response.data.status == "success") {
						$scope.transferICUList = response.data.listObject;

					}
				});

						
		var commonObjTransfer = {
				unitId : $scope.unitId,
				organizationId : $scope.organizationId,
				transferTypeId : 1
			};
		
							}
							
							
						
							$scope.init();
							
							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.saveIcuTransferRequest();
							}
							
							
							$scope.getDoctorByOpenClose = function()
							{
								
								$scope.doctorListForIcu = [];
								$scope.itemSelected =[];
								$scope.itemSelected = JSON.parse($scope.icuTypeId);
								if($scope.itemSelected.isCloseICU == 'Y')
									{
									
									var data = {
											organizationId : $scope.organizationId,
											unitId:$scope.unitId
									};
									
									var URI = BASE_URL + ROOT_URL
									+ GETDOCTORFORICU;
							GenericService
									.serviceAction("POST", URI, data)
									.then(
											function(response) {
												

												if (response.data.status == "success") {
													$scope.doctorListForIcu = response.data.listObject;

												}
											});
							
									
									}
								else
									{
									
										var data = {doctorId:$scope.patientSelecteds.doctorId,
												firstName:$scope.patientSelecteds.dFirstName};
										    
												$scope.doctorListForIcu.push(data);
										}
									
									
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
								
								console.log(info);
								$scope.patientSelecteds = [];
								$scope.patientSelecteds = info;
								console.log($scope.patientSelecteds);
								
								if($scope.patientSelecteds.patientId > 0)
									{
									 $scope.age = $rootScope.getAge($scope.patientSelecteds.dob);
									}
								else
									{
									$scope.age = $scope.patientSelecteds.dob;
									}
								$scope.patientFullName =$scope.patientSelecteds.patientName+' '+$scope.age+'/'+$scope.patientSelecteds.genderName;
								
								$scope.UHIDNumber = $scope.patientSelecteds.uhidnumber;

								
							}
							
							$scope.saveIcuTransferRequest = function()
							{
								if (Object.keys($scope.patientSelecteds).length === 0 && Object.keys($scope.itemSelected).length === 0) {
									growl
											.error(
													'Please fill required fields!!!.',
													{
														title : 'Error!'
													});
								} else {
								if ($scope.popUpFlag) {
									$scope.showModal = !$scope.showModal;

								} else {
									
								
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');
								var data = {
										treatingDoctorId : $scope.icuDoctorId,
										organizationId : $scope.organizationId,
										unitId : $scope.unitId,
										admissionId : $scope.patientSelecteds.admissionId,
										fromWardId : $scope.patientSelecteds.wardId,
										reasonId : $scope.reasonId,
										note : $scope.note,
										authorizedBy : $scope.userId,
										fromBedCategoryId :$scope.patientSelecteds.bedCategoryId,//ask to milind sir
										createdBy : $scope.userId,
										createdDate : $scope.currentDate,
										updatedBy : $scope.userId,
										updatedDate : $scope.currentDate,
										transferTypeId : 3,
										fromBillingBedCategoryId : $scope.patientSelecteds.billingBedCategoryId,//ask to milind sir
										fromFloorId:$scope.patientSelecteds.floorId,
										//transferStatusId:10,//for Send to ADT
										icuTypeId:$scope.itemSelected.icuTypeId,
										fromBedId:$scope.patientSelecteds.bedId,
										fromRoomId : $scope.patientSelecteds.roomId
										
									};
								
								//var array =$scope.icuTypeId.split('_');
								if($scope.itemSelected.isCloseICU == 'Y')
								{
									data.transferStatusId = 3;
								}
							else
								{
									data.transferStatusId = 10;
									
									}
								console.log(data);
								//return false;
								var URI = BASE_URL + ROOT_URL
										+ SAVEICUREQUEST;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {

													if (response.data.status == "success") {
														growl
																.success(
																		'ICU request sucessfully!!!.',
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
							$scope.displayNote=function(note)
							{
								$scope.noteDoctor = note;
								
							}
						
						

						} ]);
