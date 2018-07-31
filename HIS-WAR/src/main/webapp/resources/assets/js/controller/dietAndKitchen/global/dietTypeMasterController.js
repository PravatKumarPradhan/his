'use strict';

/**
 * @Author By Dinesh Jagatap
 * @name myApp.controller:nutritionMasterController
 * @description #nutritionMasterController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'dietTypeMasterController',
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
								$scope.dietTypeId = 0;

								$scope.dietTypeMasterObj = {
									dietTypeCode : "",
									dietTypeDescription : "",
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
										+ GETDIETTYPEMASTERLIST + "/org/"
										+ $scope.organizationId;

								// For Get Diet List Using Offset
								GenericService
										.serviceAction("GET", URI, data)
										.then(
												function(response) {
													$scope.dietList = [];
													if (response.data.status == "success") {
														$scope.dietList = response.data.listObject;
														console.log("dietList",$scope.dietList);
													}
												});
							}

							$scope.init();

							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.saveDiet();
							}

							// Function For Save Nutrition
							$scope.saveDiet = function() {

//								 console.log($scope.dietTypeMasterObj); return false;

								$scope.$broadcast('show-errors-check-validity');
								// return false;

								if ($scope.diet_type_form.$valid) {

									if ($scope.popUpFlag) {
										$scope.showModal = !$scope.showModal;

									} else {
										// console.log(data);

										var URI = BASE_URL + ROOT_URL
												+ SAVEDIETTYPEMASTER;
//										console.log($scope.dietTypeMasterObj); return false;
										GenericService
												.serviceAction(
														"POST",
														URI,
														$scope.dietTypeMasterObj)
												.then(
														function(response) {
															alert(response.data.status);
															if (response.data.status == "success") {
																growl
																		.success(
																				'Diet added sucessfully!!!.',
																				{
																					title : 'Success!'
																				});
																$scope
																		.$broadcast('show-errors-reset');
																$scope.init();
															} else {
																growl
																		.error(
																				'Diet already exits!!!!.',
																				{
																					title : 'Error!'
																				});
															}
														});
									}
								}

							}

							// Function For get single Diet
							$scope.showUpdateBtn = function(id) {

								$scope.saveBtnFlag = false;
								$scope.updateBtnFlag = true;

								var data = {

									dietTypeId : id
									
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ GETDIETTYPEMASTERBYID;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													// $scope.speciality =[];
													if (response.data.status == "success") {
														console.log(response.data.listObject);
														// return false;
														$scope.dietTypeMasterObj.dietTypeCode = response.data.listObject[0].dietTypeCode;
														$scope.dietTypeMasterObj.dietTypeDescription = response.data.listObject[0].dietTypeDescription;
														$scope.dietTypeId = response.data.listObject[0].dietTypeId;

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
									dietTypeId : id,
									status : type,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ UPDATEDIETTYPEMASTERSTATUS;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {

													if (response.data.status == "success") {
														growl
																.success(
																		'Diet status changed sucessfully!!!.',
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
							$scope.updateDiet = function() {

								$scope.$broadcast('show-errors-check-validity');

								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								$scope.dietTypeMasterObj.dietTypeId = $scope.dietTypeId;
								$scope.dietTypeMasterObj.updatedDate = $scope.currentDate;
								$scope.dietTypeMasterObj.updatedBy = $scope.userId;
								console.log($scope.dietTypeMasterObj);
								// return false;
								if ($scope.diet_type_form.$valid) {
									var URI = BASE_URL + ROOT_URL
											+ UPDATEDIETTYPEMASTER;
									GenericService
											.serviceAction("POST", URI,
													$scope.dietTypeMasterObj)
											.then(
													function(response) {

														if (response.data.status == "success") {
															growl
																	.success(
																			'Diet updated sucessfully!!!.',
																			{
																				title : 'Success!'
																			});
															$scope
																	.$broadcast('show-errors-reset');
															$scope.init();
														} else {
															growl
																	.error(
																			'Diet already exits!!!!.',
																			{
																				title : 'Error!'
																			});
														}
													});
								}

							}
						} ]);
