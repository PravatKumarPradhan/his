'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:morguePendinglistController
 * @description #morguePendinglistController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'morguePendinglistController',
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
						'$stateParams',
						function($scope, $http, $localStorage, $sessionStorage,
								$cookies, $rootScope, GenericService, growl,
								$state, $stateParams) {

							/* init() function for form object create. */
							$scope.init = function() {
								
								
								$scope.reserveObject = {};
								$scope.reserveCancelFlag = '';
								
								var cookieObject = $cookies.getObject('cookieObject');
								if(cookieObject == undefined){
									$state.go('login');
									return;
								}
								
								$scope.itemSelectedsReject = {};
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
								var data = {};
								var commonObj = {
										unitId : $scope.unitId,
										organizationId : $scope.organizationId
									};
									
									var URI = BASE_URL + ROOT_URL
									+ GETPENDINGMORTUARYREQUESTLIST;
							GenericService
									.serviceAction("POST", URI, commonObj)
									.then(
											function(response) {
												$scope.mortuaryPendingList = [];

												if (response.data.status == "success") {
													$scope.mortuaryPendingList = response.data.listObject;

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
							

							}

							$scope.init();

							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								var info = $scope.reserveObject;
								var resCanVal = $scope.reserveCancelFlag;
								$scope.reservePatientForMorgue(info,resCanVal);
							}
							
							$scope.bedAllocation = function(info)
							{
								console.log(info);
								//href="#/morgueAvailability"
								 var cookieMorgueBedObject = 
								 {'patientName':info.patientName, 'uhidNumber':info.uhidNumber,'dPatientId':info.dPatientId,'admissionId':info.admissionId,'patientId':info.patientId,'tPatientId':info.tPatientId,'mortuaryRequestId':info.mortuaryRequestId,'durationFormat':info.durationFormat,'durationValue':info.durationValue}
								
								 $cookies.putObject('cookieMorgueBedObject',cookieMorgueBedObject);
								 $state.go('morgueAvailability');
							}
							

							
							$scope.reservePatientForMorgue = function(info,resCanVal) {
								
								$scope.reserveObject = {};
								$scope.reserveCancelFlag = '';
								$scope.reserveCancelFlag = resCanVal;
								$scope.reserveObject = info;
								
								if ($scope.popUpFlag) {
									$scope.showModal = !$scope.showModal;

								} else {
								
									$scope.currentDate = new Date();
									$scope.currentDate = moment($scope.currentDate)
											.format('DD-MM-YYYY HH:mm:ss');
									
								var data = {
										mortuaryRequestId : $scope.reserveObject.mortuaryRequestId,
										organizationId : $scope.organizationId,
										unitId : $scope.unitId,
										updatedBy : $scope.userId,
										updatedDate : $scope.currentDate
								}
								if($scope.reserveCancelFlag == 'R')
									{
									  data.mortuaryStatusId  =  2;
									}
								else
									{
									 data.mortuaryStatusId  = 5;
									}
								console.log(data);

								//return false;
								var URI = BASE_URL + ROOT_URL
										+ ACCEPTMORTUARYREQUESTFROMPENDINGLIST;
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
							
							$scope.getDataReject = function(info)
							{
								console.log(info);
								$scope.itemSelectedsReject = [];
								$scope.itemSelectedsReject = info;
							}
							
							$scope.saveAcceptReject = function()
							{
								
								console.log($scope.itemSelectedsReject);
								$scope.currentDate = new Date();
								$scope.currentDate = moment(
										$scope.currentDate).format(
										'DD-MM-YYYY HH:mm:ss');
							
								var noteReject = $('#noteReject').val();
								var rejectReasonId = $('#rejectReasonId').val();
								var data = {
										organizationId : $scope.organizationId,
										unitId : $scope.unitId,
										mortuaryRequestId:$scope.itemSelectedsReject.mortuaryRequestId,
											updatedBy:$scope.userId,
											updatedDate:$scope.currentDate,
												rejectionNote :noteReject,
												rejectReasonId : rejectReasonId,
								};
								
								console.log(data);
							   // return false;
								var URI = BASE_URL + ROOT_URL
										+ CANCELMORGUEREQUEST;
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

						} ]);
