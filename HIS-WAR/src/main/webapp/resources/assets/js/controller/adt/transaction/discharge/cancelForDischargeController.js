'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:cancelForDischargeController
 * @description #cancelForDischargeController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'cancelForDischargeController',
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


								var data = {
										organizationId : $scope.organizationId,
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
							}
							
							
						
							$scope.init();
							
							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.cancelAndUpdateExtendPdd();
							}
							
							$scope.cancelAndUpdateExtendPdd = function()
							{
								if ($scope.popUpFlag) {
									$scope.showModal = !$scope.showModal;

								} else {
									
								
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								var data = {
									organizationId : $scope.organizationId,
									unitId : $scope.unitId,
									reasonId : $scope.reasonId,
									admissionId : $scope.admissionId,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate,
									pdd : moment($scope.datepicker).format('DD-MM-YYYY HH:mm:ss'),
									remarkDischarge : $scope.note,
									isCancel:'Y'
								};
								console.log(data);
								//return false;
								var URI = BASE_URL + ROOT_URL
										+ UPDATEDISCHARGEPDD;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {

													if (response.data.status == "success") {
														growl
																.success(
																		'Pdd Updated sucessfully!!!.',
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

						

						} ]);
