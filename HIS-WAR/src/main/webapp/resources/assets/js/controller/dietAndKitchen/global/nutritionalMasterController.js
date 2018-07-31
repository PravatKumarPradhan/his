'use strict';

/**
 * @Author By Dinesh Jagatap
 * @name myApp.controller:nutritionMasterController
 * @description #nutritionMasterController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'nutritionalMasterController',
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
						'PagerService',
						function($scope, $http, $localStorage, $sessionStorage,
								$cookies, $rootScope, GenericService, growl,
								$state, PagerService) {

							/* init() function for form object create. */

							$scope.init = function() {

								$scope.unitId = 1;
								$scope.organizationId = 1;
								$scope.userId = 1;
								if ($scope.userId == null) {
									$rootScope.loginpage = false;
									$state.go('login');
								}

								$rootScope.loginpage = true;
								$scope.country = {}
								$scope.saveBtnFlag = true;
								$scope.updateBtnFlag = false;

								// for popup
								$scope.showModal = false;
								$scope.buttonClicked = "";
								$scope.popUpFlag = true;
								// end for popup
								$scope.nutritionId = 0;

								$scope.nutritionalMasterObj = {
									nutritionCode : "",
									nutritionName : "",
									organizationId : $scope.organizationId,
									createdDate : moment(new Date()).format(
											'DD-MM-YYYY HH:mm:ss'),
									updatedDate : moment(new Date()).format(
											'DD-MM-YYYY HH:mm:ss'),
									status : 'A',
									createdBy : $scope.userId,
									updatedBy : $scope.userId

								};

								var data = {};

								var URI = BASE_URL + ROOT_URL
										+ GETNUTRITIONALMASTERLIST + "/org/"
										+ $scope.organizationId;

								// For Get Nutrition List Using Offset
								GenericService
										.serviceAction("GET", URI, data)
										.then(
												function(response) {
													$scope.nutritionList = [];

													if (response.data.status == "success") {
														$scope.nutritionList = response.data.listObject;
														console
																.log(
																		"nutritionList",
																		$scope.nutritionList);

													}
												});

							}

							$scope.init();

							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.saveNutrition();
							}

							// Function For Save Nutrition
							$scope.saveNutrition = function() {

								// console.log($scope.nutritionalMasterObj);

								$scope.$broadcast('show-errors-check-validity');
								// return false;

								if ($scope.nutritionform.$valid) {

									if ($scope.popUpFlag) {
										$scope.showModal = !$scope.showModal;

									} else {
										// console.log(data);

										var URI = BASE_URL + ROOT_URL
												+ SAVENUTRITIONMASTER;
										GenericService
												.serviceAction(
														"POST",
														URI,
														$scope.nutritionalMasterObj)
												.then(
														function(response) {
//															alert(response.data.status);
															if (response.data.status == "success") {
																growl
																		.success(
																				'Nutrition added sucessfully!!!.',
																				{
																					title : 'Success!'
																				});
																$scope
																		.$broadcast('show-errors-reset');
																$scope.init();
															} else {
																growl
																		.error(
																				'Nutrition already exits!!!!.',
																				{
																					title : 'Error!'
																				});
															}
														});
									}
								}

							}

							// Function For get single Nutrition
							$scope.showUpdateBtn = function(id) {

								$scope.saveBtnFlag = false;
								$scope.updateBtnFlag = true;

								var data = {

									nutritionId : id,
									organizationId : $scope.organizationId
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ GETNUTRITIONALMASTERBYID;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													// $scope.speciality =[];
													if (response.data.status == "success") {
														console
																.log(response.data.listObject);
														// return false;
														$scope.nutritionalMasterObj.nutritionCode = response.data.listObject[0].nutritionCode;
														$scope.nutritionalMasterObj.nutritionName = response.data.listObject[0].nutritionName;
														$scope.nutritionId = response.data.listObject[0].nutritionId;

													} else {
														alert("Error!!");
													}
												});

							};

							// Function For update nutrition status
							$scope.updateStatus = function(id, type) {

								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								var data = {
									nutritionId : id,
									status : type,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ UPDATENUTRITIONMASTERSTATUS;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {

													if (response.data.status == "success") {
														growl
																.success(
																		'Nutrition status changed sucessfully!!!.',
																		{
																			title : 'Success!'
																		});
													} else {
														growl
																.error(
																		'something wrong!!!!.',
																		{
																			title : 'Error!'
																		});
													}
												});
							}

							// Function For update Nutrition
							$scope.updateNutrition = function() {

								$scope.$broadcast('show-errors-check-validity');

								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								$scope.nutritionalMasterObj.nutritionId = $scope.nutritionId;
								$scope.nutritionalMasterObj.updatedDate = $scope.currentDate;
								$scope.nutritionalMasterObj.updatedBy = $scope.userId;
								console.log($scope.nutritionalMasterObj);
								// return false;
								if ($scope.nutritionform.$valid) {
									var URI = BASE_URL + ROOT_URL
											+ UPDATENUTRITIONMASTER;
									GenericService
											.serviceAction("POST", URI,
													$scope.nutritionalMasterObj)
											.then(
													function(response) {

														if (response.data.status == "success") {
															growl
																	.success(
																			'Nutrition updated sucessfully!!!.',
																			{
																				title : 'Success!'
																			});
															$scope
																	.$broadcast('show-errors-reset');
															$scope.init();
														} else {
															growl
																	.error(
																			'Nutrition already exits!!!!.',
																			{
																				title : 'Error!'
																			});
														}
													});
								}

							}
						} ]);
