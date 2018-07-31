'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:mortuaryBedManagementController
 * @description #mortuaryBedManagementController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'mortuaryBedManagementController',
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

								$scope.itemSelecteds = {};

								// for popup
								$scope.showModal = false;
								$scope.buttonClicked = "";
								$scope.popUpFlag = true;
								// end for popup
								
							

								var bedObj = {
									unitId : $scope.unitId,
									organizationId : $scope.organizationId
								}
								var URI = BASE_URL + ROOT_URL
										+ GETDATAFORMORTURYMAPOFBED;
								GenericService
										.serviceAction("POST", URI, bedObj)
										.then(
												function(response) {
													$scope.mortuaryBedList = [];

													if (response.data.status == "success") {
														$scope.mortuaryBedList = response.data.listObject;

													}
												});
								
							}

							$scope.init();

							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.saveBedAllocation();
							}
							
							$scope.patientDetails = function(info)
							{
								console.log(info);
								$scope.itemSelecteds = [];
								$scope.itemSelecteds = info;
								
								if($scope.cookieMorgueBedObject == undefined){
									$state.go('login');
									return;
								}
								$scope.patientName = $scope.cookieMorgueBedObject.patientName;
								$scope.uhidNumber = $scope.cookieMorgueBedObject.uhidNumber;
							}
							

						} ]);
