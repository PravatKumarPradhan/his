'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:cleanedBedAcceptController
 * @description #cleanedBedAcceptController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'cleanedBedAcceptController',
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
								var data ={};
								var commonObj = {
									unitId : $scope.unitId,
									organizationId : $scope.organizationId
								};

								var URI = BASE_URL + ROOT_URL
										+ GETLISTOFVACANTBEDSFORNURSING;
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
																	newArr.assignedPersonId = value.assignedPersonId.toString();
																	newArr.floorName = value.floorName;
																	newArr.nursingStationDesc = value.nursingStationDesc;
																	newArr.bedCategoryDesc = value.bedCategoryDesc;
																	newArr.bedNumber = value.bedNumber;
																	newArr.priorityDesc = value.priorityDesc;
																	newArr.note = value.note;
																	newArr.housekeepingStatusDesc = value.housekeepingStatusDesc;
																	newArr.userName = value.userName;
																	$scope.housekeepingBedList.push(newArr);
																});
														
														
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
										+ SEARCHBEDBYMULTIPLECRITERIAFORNURSING;
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
																	newArr.userName = value.userName;
																	$scope.housekeepingBedList.push(newArr);
																});

													}
												});

							}

							// Toggle selection for a given Trans Req by id
							$scope.toggleSelectionAccept = function(item) {
								$scope.itemSelecteds = [];
								$scope.itemSelecteds = item;

							};
							
							$scope.saveAcceptReject = function()
							{
							
								console.log($scope.itemSelecteds);
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');


								var data = {
									updatedDate : $scope.currentDate,
									updatedBy : $scope.userId,
									organizationId : $scope.organizationId,
									unitId : $scope.unitId,
									note : $('#noteReject').val(),
									rejectionReasonId : $('#rejectReasonId').val(),
									bedId :$scope.itemSelecteds.bedId,
									housekeepingId : $scope.itemSelecteds.housekeepingId
								}
								
								console.log(data);
								//return false;
								var URI = BASE_URL + ROOT_URL
								+ HOUSEKEEPINGREJECTREQUESTFORNURSING;
						GenericService
								.serviceAction("POST", URI,
										data)
								.then(
										function(response) {

											if (response.data.status == "success") {
												growl
														.success(
																'Housekeeping rejected sucessfully!!!.',
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
							
							
							
							
							$scope.saveAcceptance = function()
							{
								console.log($scope.itemSelecteds);
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');


								var data = {
									updatedDate : $scope.currentDate,
									updatedBy : $scope.userId,
									organizationId : $scope.organizationId,
									unitId : $scope.unitId,
									note : $('#noteTransfer').val(),
									bedId :$scope.itemSelecteds.bedId,
									housekeepingId : $scope.itemSelecteds.housekeepingId
								}
							
								console.log(data);
								//return false;
								var URI = BASE_URL + ROOT_URL
								+ HOUSEKEEPINGACCEPTREQUESTFORNURSING;
						GenericService
								.serviceAction("POST", URI,
										data)
								.then(
										function(response) {

											if (response.data.status == "success") {
												growl
														.success(
																'Housekeeping accepeted sucessfully!!!.',
																{
																	title : 'Success!'
																});
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
							

						} ]);
