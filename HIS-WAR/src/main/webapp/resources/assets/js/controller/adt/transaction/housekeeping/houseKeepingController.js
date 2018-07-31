'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:houseKeepingController
 * @description #houseKeepingController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'houseKeepingController',
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

								$scope.bedsearch = {
									floorId : 0,
									nursingStationId : 0,
									bedCategoryId : 0,
									wardId : 0,
									housekeepingStatusId : 0,
									organizationId : $scope.organizationId,
									unitId : $scope.unitId,

								}

								// for popup
								$scope.showModal = false;
								$scope.buttonClicked = "";
								$scope.popUpFlag = true;
								// end for popup
								
								$scope.bedHousekeeping = {}
								$scope.itemSelecteds = {};
								
								var data = {};
								var commonObj = {
									unitId : $scope.unitId,
									organizationId : $scope.organizationId
								};

								var URI = BASE_URL + ROOT_URL
										+ GETACTIVEFLOORLIST;
								GenericService
										.serviceAction("POST", URI, commonObj)
										.then(
												function(response) {
													$scope.activeFloorList = [];

													if (response.data.status == "success") {
														$scope.activeFloorList = response.data.listObject;

													}
												});

								var URI = BASE_URL + ROOT_URL
										+ GETACTIVEBEDCATEGORYLISTBYUNIT;
								GenericService
										.serviceAction("POST", URI, commonObj)
										.then(
												function(response) {
													$scope.bedCategoryList = [];

													if (response.data.status == "success") {
														$scope.bedCategoryList = response.data.listObject;

													}
												});

								

								var URI = BASE_URL + ROOT_URL
										+ GETBEDLISTFORHOUSEKEEPING;
								GenericService
										.serviceAction("POST", URI, commonObj)
										.then(
												function(response) {
													$scope.housekeepingBedList = [];

													if (response.data.status == "success") {
														$scope.housekeepingList = response.data.listObject;
														
														angular
														.forEach( $scope.housekeepingList, function( value, index) {
																	var newArr = {};
																	newArr.housekeepingId = value.housekeepingId;
																	newArr.bedId = value.bedId;
																	newArr.priorityId = value.priorityId;
																	if(value.assignedPersonId != null)
																	newArr.assignedPersonId = value.assignedPersonId.toString();
																	else
																		newArr.assignedPersonId = value.assignedPersonId;	
																	newArr.floorName = value.floorName;
																	newArr.nursingStationDesc = value.nursingStationDesc;
																	newArr.bedCategoryDesc = value.bedCategoryDesc;
																	newArr.bedNumber = value.bedNumber;
																	newArr.priorityDesc = value.priorityDesc;
																	newArr.note = value.note;
																	newArr.housekeepingStatusDesc = value.housekeepingStatusDesc;
																	newArr.housekeepingStatusId = value.housekeepingStatusId.toString();
																	newArr.userName = value.userName;
																	$scope.housekeepingBedList.push(newArr);
																});
														
													}
												});
								var URI = BASE_URL + ROOT_URL
								+ GETUSERSLIST;
						GenericService
								.serviceAction("POST", URI, commonObj)
								.then(
										function(response) {
											$scope.usersList = [];

											if (response.data.status == "success") {
												$scope.usersList = response.data.listObject;
											}
										});

							}

							$scope.init();

							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.updateHouseKeepingRequest();
							}
							
							$scope.selectItem = function(item) {

								$scope.itemSelecteds = [];
								// If checkbox is checked
								if ($scope.bedHousekeeping) {
									$scope.itemSelecteds = item;
								}
								console.log($scope.itemSelecteds);
							}
							
							
							$scope.getBedById = function(commonType) {

								console.log($scope.bedsearch);

								var commonData = {
									unitId : $scope.unitId,
									organizationId : $scope.organizationId
								};
								switch (commonType) {
								case 'floor':

									commonData.floorId = $scope.bedsearch.floorId;

									var URI = BASE_URL + ROOT_URL
											+ GETWARDLISTBYFLOORIDORGUNITID;
									GenericService
											.serviceAction("POST", URI,
													commonData)
											.then(
													function(response) {
														$scope.wardList = [];

														if (response.data.status == "success") {
															$scope.wardList = response.data.listObject;

														}
													});
									break;
								case 'ward':

									commonData.wardId = $scope.bedsearch.wardId;

									var URI = BASE_URL
											+ ROOT_URL
											+ GETACTIVENURSINGSTATIONLISTBYWARDID;
									GenericService
											.serviceAction("POST", URI,
													commonData)
											.then(
													function(response) {
														$scope.nursingStationList = [];

														if (response.data.status == "success") {
															$scope.nursingStationList = response.data.listObject;

														}
													});
									break;
								default:

								}

							}

							$scope.bedSearchByMultipleCriteria = function() {

								// $scope.bedsearch.isVirtual =
								// ($scope.bedsearch.isVirtual == true) ? "Y":
								// "N";
								console.log($scope.bedsearch);
								// return false;
								var URI = BASE_URL + ROOT_URL
										+ SEARCHBEDBYMULTIPLECRITERIAFORHOUSEKEEPING;
								GenericService
										.serviceAction("POST", URI,
												$scope.bedsearch)
										.then(
												function(response) {
													$scope.housekeepingBedList = [];

													if (response.data.status == "success") {
	                                                   $scope.housekeepingList = response.data.listObject;
														
														angular
														.forEach( $scope.housekeepingList, function( value, index) {
																	var newArr = {};
																	newArr.housekeepingId = value.housekeepingId;
																	newArr.bedId = value.bedId;
																	newArr.priorityId = value.priorityId;
																	newArr.assignedPersonId = value.assignedPersonId.toString();
																	newArr.floorName = value.floorName;
																	newArr.nursingStationDesc = value.nursingStationDesc;
																	newArr.bedCategoryDesc = value.bedCategoryDesc;
																	newArr.bedNumber = value.bedNumber;
																	newArr.priorityDesc = value.priorityDesc;
																	newArr.note = value.note;
																	newArr.housekeepingStatusDesc = value.housekeepingStatusDesc;
																	newArr.housekeepingStatusId = value.housekeepingStatusId.toString();
																	newArr.userName = value.userName;
																	$scope.housekeepingBedList.push(newArr);
																});

													}
												});

							}

							$scope.updateMarkedAsClean = function()
							{
								if (Object.keys($scope.itemSelecteds).length === 0) {
									growl
											.error(
													'Please select atleast one Bed!!!.',
													{
														title : 'Error!'
													});
								} else {
									if( $scope.itemSelecteds.housekeepingStatusId == '2')
										{
									$scope.currentDate = new Date();
									$scope.currentDate = moment(
											$scope.currentDate).format(
											'DD-MM-YYYY HH:mm:ss');
									var data = {
										housekeepingId : $scope.itemSelecteds.housekeepingId,
										organizationId : $scope.organizationId,
										unitId : $scope.unitId,
										updatedBy : $scope.userId,
										updatedDate : $scope.currentDate,
										status : 'A',
										bedId : $scope.itemSelecteds.bedId,
										priorityId : $scope.itemSelecteds.priorityId,
										assignedPersonId :  $scope.itemSelecteds.assignedPersonId
									};
									console.log(data);
									//return false;
									var URI = BASE_URL + ROOT_URL
											+ UPDATEFORMARKEDASCLEAR;
									GenericService
											.serviceAction("POST", URI,
													data)
											.then(
													function(response) {

														if (response.data.status == "success") {
															growl
																	.success(
																			'Housekeeping Request sent sucessfully!!!.',
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
									else
										{
										growl
										.error(
												'Please assigned person before marked as clean.',
												{
													title : 'Error!'
												});
										}
								}
							}
							
							$scope.updateHouseKeepingRequest = function()
							{
								if (Object.keys($scope.itemSelecteds).length === 0) {
									growl
											.error(
													'Please select atleast one Bed!!!.',
													{
														title : 'Error!'
													});
								} else {
									if ($scope.popUpFlag) {
										$scope.showModal = !$scope.showModal;

									} else {

										$scope.currentDate = new Date();
										$scope.currentDate = moment(
												$scope.currentDate).format(
												'DD-MM-YYYY HH:mm:ss');
										var data = {
											housekeepingId : $scope.itemSelecteds.housekeepingId,
											organizationId : $scope.organizationId,
											unitId : $scope.unitId,
											updatedBy : $scope.userId,
											updatedDate : $scope.currentDate,
											status : 'A',
											assignedPersonId :  $scope.itemSelecteds.assignedPersonId,
											priorityId : $scope.itemSelecteds.priorityId
										};
										console.log(data);
										//return false;
										var URI = BASE_URL + ROOT_URL
												+ UPDATEHOUSEKEEPING;
										GenericService
												.serviceAction("POST", URI,
														data)
												.then(
														function(response) {

															if (response.data.status == "success") {
																growl
																		.success(
																				'Housekeeping Request sent sucessfully!!!.',
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
							
							$scope.displayNote = function(note)
							{
								$scope.noteDoctor = note;
							}
							

						} ]);
