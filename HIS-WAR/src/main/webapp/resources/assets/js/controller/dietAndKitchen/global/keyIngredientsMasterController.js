'use strict';

/**
 * @Author By Dinesh Jagatap
 * @name myApp.controller:keyIngredientsMasterController
 * @description #keyIngredientsMasterController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'keyIngredientsMasterController',
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
								$scope.keyIngredientId = 0;

								$scope.keyIngredientMasterObj = {
									ingredientCode : "",
									ingredientDescription : "",
									ingredientValue : "",
									uomId : '',
									uomName : "",
									organizationId : $scope.organizationId,
									createdDate : moment(new Date()).format(
											'DD-MM-YYYY HH:mm:ss'),
									updatedDate : moment(new Date()).format(
											'DD-MM-YYYY HH:mm:ss'),
									status : 'A',
									createdBy : $scope.userId,
									updatedBy : $scope.userId

								};

								// for nutrition List save start
								$scope.nutrationPropertyObjList = [ {
									"nutritionId" : '',
									"value" : '',
									"uomId" : '',
									"ingredientNutritionId" : ''
								} ];
								// for nutrition List save end

								var data = {};

								var URI = BASE_URL + ROOT_URL
										+ GETALLKEYINGREDIENTSMASTER;

								// For Get Key Ingredient Master List Using
								// Offset
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.keyIngredientMasterList = [];

													if (response.data.status == "success") {
														$scope.keyIngredientMasterList = response.data.listObject;
														console
																.log(
																		"keyIngredientMasterList",
																		$scope.keyIngredientMasterList);

													}
												});

								var URI = BASE_URL + ROOT_URL
										+ GETUNITMASTERDETAILS;
								// For Get Key unit of measurement Master List
								// Using

								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.unitMeasurementList = [];

													if (response.data.status == "success") {
														$scope.unitMeasurementList = response.data.listObject;
														console
																.log(
																		"unitMeasurementList",
																		$scope.unitMeasurementList);

													}
												});

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

							// Function For get single Nutrition
							$scope.showNutritionDetails = function(id) {

								var data = {

									keyIngredientId : id,
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ GETKEYINGREDIENTSDETAILSBYID;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.nutritionMasterList = [];
													if (response.data.status == "success") {
														console
																.log(response.data.listObject);
														$scope.nutritionMasterList = response.data.listObject;
													} else {
														alert("Error!!");
													}
												});

							};

							// Adding the nutrition details
							$scope.add = function(ind) {
								var data = {

									"nutritionId" : '',
									"value" : '',
									"uomId" : '',
									"ingredientNutritionId" : ''
								};

								$scope.nutrationPropertyObjList.push(data);
							};

							$scope.remove = function(index) {
								var newDataList = [];
								var le = $scope.nutrationPropertyObjList.length;
								var i = 1;
								if (le != i) {
									angular.forEach(
											$scope.nutrationPropertyObjList,
											function(v) {
												if (index != i) {
													newDataList.push(v);
												}
												i++;
											});

									$scope.nutrationPropertyObjList = newDataList;
								}
							};

							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.saveKeyIngredients();
							}

							// Function For Save Nutrition
							$scope.saveKeyIngredients = function() {

								// console.log($scope.nutritionalMasterObj);

								// $scope.$broadcast('show-errors-check-validity');
								// return false;

								// if ($scope.nutritionform.$valid) {

								if ($scope.popUpFlag) {
									$scope.showModal = !$scope.showModal;

								} else {

									$scope.keyIngredientMasterObj.nutriciansList = [];
									$scope.keyIngredientMasterObj.nutriciansList = angular
											.copy($scope.nutrationPropertyObjList);
									// console.log("abcd",$scope.keyIngredientMasterObj);
									// return false;
									// $scope.keyIngredientMasterObj.nutriciansList.push($scope.nutrationPropertyObjList);
									console.log("saasd",
											$scope.keyIngredientMasterObj);
									// return false;
									var URI = BASE_URL + ROOT_URL
											+ SAVEKEYINGREDIENTSMASTER;
									GenericService
											.serviceAction(
													"POST",
													URI,
													$scope.keyIngredientMasterObj)
											.then(
													function(response) {
														// alert(response.data.status);
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
									// }
								}

							}

							// Function For get single Nutrition
							$scope.showUpdateBtn = function(id) {

								$scope.saveBtnFlag = false;
								$scope.updateBtnFlag = true;

								var data = {

									keyIngredientId : id,
									organizationId : $scope.organizationId
								}
								// console.log(data); return false;
								var URI = BASE_URL + ROOT_URL
										+ GETKEYINGREDIENTSMASTERBYID;

								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													// $scope.speciality =[];
													if (response.data.status == "success") {
														$scope.keyIngredientMasterObj.ingredientCode = response.data.object.ingredientCode;
														$scope.keyIngredientMasterObj.ingredientDescription = response.data.object.ingredientDescription;
														$scope.keyIngredientMasterObj.ingredientValue = response.data.object.ingredientValue;
														$scope.keyIngredientMasterObj.uomId = response.data.object.uomId
																.toString();
														$scope.keyIngredientId = response.data.object.keyIngredientId;
														console
																.log(
																		"nutrition list",
																		response.data.object.nutriciansList);
														$scope.nutrationPropertyObjList = [];

														if (response.data.object.nutriciansList.length > 0) {
															for (var i = 0; i < (response.data.object.nutriciansList.length); i++) {
																console
																		.log(
																				"nutrition list",
																				i);
																var data = {

																	"nutritionId" : response.data.object.nutriciansList[i].nutritionId
																			.toString(),
																	"value" : response.data.object.nutriciansList[i].value,
																	"uomId" : response.data.object.nutriciansList[i].uomId
																			.toString(),
																	"ingredientNutritionId" : response.data.object.nutriciansList[i].ingredientNutritionId
																};

																$scope.nutrationPropertyObjList
																		.push(data);

															}
														} else {
															$scope.nutrationPropertyObjList = [ {
																"nutritionId" : '',
																"value" : '',
																"uomId" : '',
																"ingredientNutritionId" : ''
															} ];
														}
														// return false;

													} else {
														alert("Error!!");
													}
												});

							};

							// Function For update keyingredients status
							$scope.updateStatus = function(id, type) {

								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								var data = {
									keyIngredientId : id,
									status : type,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ UPDATEKEYINGREDIENTSMASTERSTATUS;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {

													if (response.data.status == "success") {
														growl
																.success(
																		'Key Ingredients status changed sucessfully!!!.',
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
							$scope.updateKeyIngredients = function() {

								$scope.keyIngredientMasterObj.nutriciansList = [];
								$scope.keyIngredientMasterObj.nutriciansList = angular
										.copy($scope.nutrationPropertyObjList);

								// $scope.$broadcast('show-errors-check-validity');

								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								$scope.keyIngredientMasterObj.keyIngredientId = $scope.keyIngredientId;
								$scope.keyIngredientMasterObj.updatedDate = $scope.currentDate;
								$scope.keyIngredientMasterObj.updatedBy = $scope.userId;
								// console.log($scope.nutritionalMasterObj);
								console.log("update key Ingredient Nutrition",
										$scope.keyIngredientMasterObj);// return
								// false;
								// if ($scope.nutritionform.$valid) {
								var URI = BASE_URL + ROOT_URL
										+ UPDATEKEYINGREDIENTSMASTER;
								GenericService
										.serviceAction("POST", URI,
												$scope.keyIngredientMasterObj)
										.then(
												function(response) {

													if (response.data.status == "success") {
														growl
																.success(
																		'Key ingredient updated sucessfully!!!.',
																		{
																			title : 'Success!'
																		});
														$scope
																.$broadcast('show-errors-reset');
														$scope.init();
													} else {
														growl
																.error(
																		'Key ingredient already exits!!!!.',
																		{
																			title : 'Error!'
																		});
													}
												});
								// }

							}
						} ]);
