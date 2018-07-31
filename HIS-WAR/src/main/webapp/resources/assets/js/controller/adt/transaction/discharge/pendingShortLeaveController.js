'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:pendingShortLeaveController
 * @description #pendingShortLeaveController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'pendingShortLeaveController',
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

								/*	if ($cookies.get('bedManagmentCookies') != null) {
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
									}*/

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
								
								$scope.itemSelectedsAccept = [];
								
								var data = {};
								/*var shortLeaveStatusList = [];
								shortLeaveStatusList.push(1);*/
								
								var commonObj = {
									organizationId : $scope.organizationId,
									unitId : $scope.unitId,
									doctorId : $scope.userId
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
										+ GETSHORTLEAVEREQUESTLISTFORDOCTOR;
								GenericService
										.serviceAction("POST", URI, commonObj)
										.then(
												function(response) {
													$scope.shortLeaveRequestList = [];

													if (response.data.status == "success") {
														$scope.shortLeaveRequestList = response.data.listObject;

													}
												});
								

							}

							$scope.init();

							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.saveShortLeaveRequest();
							}

							$scope.getAdmissionDataAccept = function(item)
							{
								//alert("sc");
								//console.log(item);
								$scope.itemSelectedsAccept = [];
								$scope.itemSelectedsAccept = item;
								console.log($scope.itemSelectedsAccept);
								
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
										shortLeaveId:$scope.itemSelectedsAccept.shortLeaveId,
											updatedBy:$scope.userId,
											updatedDate:$scope.currentDate,
												authorizedBy:$scope.userId,
													doctorsNote :tansferNote											
										
								};
								
								console.log(data);
								//return false;
								var URI = BASE_URL + ROOT_URL
										+ ACCEPTSHOTLEAVEBYDOCTOR;
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
										shortLeaveId:$scope.itemSelectedsAccept.shortLeaveId,
										updatedBy:$scope.userId,
										updatedDate:$scope.currentDate,
										doctorsNote :noteReject,
										rejectionReasonId : rejectReasonId,
								};
								
								console.log(data);
							  //return false;
								var URI = BASE_URL + ROOT_URL
										+ REJECTSHOTLEAVEBYDOCTOR;
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
