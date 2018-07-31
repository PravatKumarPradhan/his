'use strict';

/**
 * @Author By Vivek Satle
 * @name myApp.controller: scheduleStatusController
 * @description #scheduleStatusController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'scheduleStatusController',
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

								$scope.scheduleStatus = {};
								$scope.itemSelecteds = {};

								$scope.patientSearchObj = {
									uhIdNumber : '',
									patientName : '',
									wardName : '',
									bedNumber : '',
									unitId : $scope.unitId,
									organizationId : $scope.organizationId,
									transferTypeId : 1,
									visitTypeId : 1
								}

								var data = {
									unitId : $scope.unitId,
									organizationId : $scope.organizationId,
								};
								var commonObj = {
									unitId : $scope.unitId,
									organizationId : $scope.organizationId,
									transferTypeId : 1,
									visitTypeId : 1
								};

								var URI = BASE_URL + ROOT_URL
										+ GETALLPATIENTSERVICEORDERLIST;
								GenericService
										.serviceAction("POST", URI, commonObj)
										.then(
												function(response) {
													$scope.patientServiceOrderList = [];

													if (response.data.status == "success") {
														$scope.patientServiceOrderList = response.data.listObject;

													}
												});

							}

							$scope.init();

							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.cancelScheduledInvestigation();
							}

							$scope.searchInvestigationList = function() {
								console.log($scope.patientSearchObj);

								var URI = BASE_URL
										+ ROOT_URL
										+ SEARCHPATIENTSERVICEORDERSBYMULCRITERIA;
								GenericService
										.serviceAction("POST", URI,
												$scope.patientSearchObj)
										.then(
												function(response) {
													$scope.patientServiceOrderList = [];

													if (response.data.status == "success") {
														$scope.patientServiceOrderList = response.data.listObject;

													}
												});

							}

							// function for popup ok button call
							/*$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								var transferId = $scope.itemSelecteds.transferId;
								$scope.acceptRequest();
							}*/

							$scope.selectItem = function(item) {

								$scope.itemSelecteds = [];
								// If checkbox is checked
								if ($scope.scheduleStatus) {
									$scope.itemSelecteds = item;

								}
							}

							/*$scope.acceptRequest = function(transferId) {
								if($scope.transferIdForCheck == transferId)
									{
										
										console.log($scope.itemSelecteds);
										if (Object.keys($scope.beditemSelecteds).length === 0 && $scope.itemSelecteds.transferTypeId != '3') {
											growl
													.error(
															'Please Allocate bed before accept!!!.',
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
														organizationId:$scope.organizationId,
														unitId : $scope.unitId,
														toBedCategoryId :$scope.itemSelecteds.toBedCategoryId,
														toBedId:$scope.beditemSelecteds.bedId,
														toWardId : $scope.beditemSelecteds.wardId,
														toRoomId :  $scope.beditemSelecteds.roomId,
														toBillingBedCategoryId: $scope.beditemSelecteds.billingBedCategoryId,
														updatedBy:$scope.userId,
														updatedDate : $scope.currentDate,
														transferId : $scope.itemSelecteds.transferId,
														transferRequestId : $scope.itemSelecteds.transferRequestId
												}
												console.log(data);
												return false;
												var URI = BASE_URL + ROOT_URL
												+ ACCEPTTRANSFERREQUESTFORADT;
										GenericService
												.serviceAction("POST", URI,
														data)
												.then(
														function(response) {

															if (response.data.status == "success") {
																growl
																		.success(
																				'Transfer Request accepted!!!.',
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
								else
									{
									growl
									.error(
											'Please select correct patient for accept!!!.',
											{
												title : 'Error!'
											});
									return false;
									}
								
							}*/

							$scope.redirectSchedulePage = function() {

								console.log($scope.itemSelecteds);
								if (Object.keys($scope.itemSelecteds).length === 0) {
									growl
											.error(
													'Please Select Investigation before schedule!!!.',
													{
														title : 'Error!'
													});
								} else {
									$state.go('appointmentScheduling.day');
								}

							}

							$scope.cancelScheduledInvestigation = function() {
								console.log($scope.itemSelecteds);
								if (Object.keys($scope.itemSelecteds).length === 0) {
									growl
											.error(
													'Please Select Investigation before schedule!!!.',
													{
														title : 'Error!'
													});
								} else {
									if ($scope.popUpFlag) {
										$scope.showModal = !$scope.showModal;

									} else {
										alert("x");
									}

								}
							}

							$scope.rescheduleInvestigation = function() {
								console.log($scope.itemSelecteds);
								if (Object.keys($scope.itemSelecteds).length === 0) {
									growl
											.error(
													'Please Select Investigation before schedule!!!.',
													{
														title : 'Error!'
													});
								} else {
									$state.go('appointmentScheduling.day');
								}
							}

						} ]);
