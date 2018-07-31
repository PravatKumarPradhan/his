'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:hierarchyMasterController
 * @description # hierarchyMasterController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'hierarchyMasterController',
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
								$cookies, $rootScope, GenericService, growl,$state) {

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
								$scope.hierarchy = {}
								$scope.saveBtnFlag = false;
								$scope.updateBtnFlag = true;

								// for popup
								$scope.showModal = false;
								$scope.buttonClicked = "";
								$scope.popUpFlag = true;
								// end for popup

								var data = {};
								// $scope.status = "NO";
								var URI = BASE_URL + ROOT_URL + GETHIERARCHYLIST;
								GenericService
										.serviceAction("GET", URI, data)
										.then(
												function(response) {
													$scope.hierarchyList = [];

													if (response.data.status == "success") {
														$scope.hierarchyList = response.data.listObject;
														console
																.log($scope.hierarchyList);

													}
												});
							}

							$scope.init();

							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.saveHierarchy();
							}

							$scope.saveHierarchy = function() {

								$scope.$broadcast('show-errors-check-validity');
								var data = {
									hierarchyCode : $scope.hierarchy.hierarchyCode,
									hierarchyDesc : $scope.hierarchy.hierarchyName,
									status : 'A',
									createdBy : $scope.userId,
									updatedBy :  $scope.userId,
									organizationId : $scope.organizationId
								}
								if ($scope.hierarchyform.$valid) {

									if ($scope.popUpFlag) {
										$scope.showModal = !$scope.showModal;

									} else {
										console.log(data);

										var URI = BASE_URL + ROOT_URL
												+ ADDHIERARCHY;
										GenericService
												.serviceAction("POST", URI,
														data)
												.then(
														function(response) {

															if (response.data.status == "success") {
																growl
																		.success(
																				'Hierarchy added sucessfully!!!.',
																				{
																					title : 'Success!'
																				});
																$scope
																		.$broadcast('show-errors-reset');
																$scope.init();
															} else {
																growl
																		.error(
																				'Hierarchy already exits!!!!.',
																				{
																					title : 'Error!'
																				});
															}

														});
									}
								}

							}

							$scope.showUpdateBtn = function(id) {

								$scope.saveBtnFlag = true;
								$scope.updateBtnFlag = false;

								var data = {

										hierarchyId : id
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ GETSINGLEHIERARCHY;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													// $scope.speciality =[];
													if (response.data.status == "success") {
														$scope.hierarchy.hierarchyId = response.data.listObject[0].hierarchyId;
														$scope.hierarchy.hierarchyCode = response.data.listObject[0].hierarchyCode;
														$scope.hierarchy.hierarchyName = response.data.listObject[0].hierarchyDesc;

													} else {
														alert("Error!!");
													}
												});

							};

							$scope.checkVal = function(statusType) {
								$scope.status = (statusType == "A") ? "A" : "I";
								return (statusType == "A") ? true : false;
							}

							$scope.updateStatus = function(id, type) {

								type = (type == "A") ? "A" : "I";

								var data = {
									hierarchyId : id,
									status : type,
									updatedBy : $scope.userId
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ UPDATEHIERACHYSTATUS;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {

													if (response.data.status == "success") {
														growl
																.success(
																		'Hierarchy status changed sucessfully!!!.',
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

							$scope.updateHierarchy = function() {
								$scope.$broadcast('show-errors-check-validity');
								var data = {
										hierarchyCode : $scope.hierarchy.hierarchyCode,
										hierarchyDesc : $scope.hierarchy.hierarchyName,
										hierarchyId : $scope.hierarchy.hierarchyId,
									    updatedBy : $scope.userId
								}
								if ($scope.hierarchyform.$valid) {
								console.log(data);
								var URI = BASE_URL + ROOT_URL + UPDATEHIERARCHY;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {

													if (response.data.status == "success") {
														growl
																.success(
																		'Hierarchy updated sucessfully!!!.',
																		{
																			title : 'Success!'
																		});
														$scope.$broadcast('show-errors-reset');
												        $scope.init();
													} else {
														growl
																.error(
																		'Hierarchy already exits!!!!.',
																		{
																			title : 'Error!'
																		});
													}

												});
								}

							}

						} ]);
