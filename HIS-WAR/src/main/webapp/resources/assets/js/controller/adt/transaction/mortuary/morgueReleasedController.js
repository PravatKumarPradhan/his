'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:morgueReleasedController
 * @description #morgueReleasedController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'morgueReleasedController',
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
								
								//for datepicker 1
								$scope.dateOptions = {
										formatYear : 'yyyy',
										showWeeks : false
									};

									$scope.openDatePicker = function() {
										$scope.datepicker.opened = true;
									};

									$scope.datepicker = {
										opened : false
									};
									
									
									$scope.kinDetails = {}
									$scope.itemSelecteds = {};
									$scope.itemPatientSelecteds = {};
									
								$scope.reserveObject = {};
								$scope.reserveCancelFlag = '';
								
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
								
								var data = {};
								var commonObj = {
										unitId : $scope.unitId,
										organizationId : $scope.organizationId
									};
									
									var URI = BASE_URL + ROOT_URL
									+ GETADMITTEDMORTUARYLIST;
							GenericService
									.serviceAction("POST", URI, commonObj)
									.then(
											function(response) {
												$scope.mortuaryAdmittedList = [];

												if (response.data.status == "success") {
													$scope.mortuaryAdmittedList = response.data.listObject;
													console.log($scope.mortuaryAdmittedList);
												}
											});
							var URI = BASE_URL + ROOT_URL
							+ GETACTIVEPREFIXLIST;
					GenericService
							.serviceAction("GET", URI, data)
							.then(
									function(response) {
										$scope.prefixList = [];

										if (response.data.status == "success") {
											$scope.prefixList = response.data.listObject;

										}
									});
					var URI = BASE_URL + ROOT_URL
							+ GETACTIVERELATIONLIST;
					GenericService
							.serviceAction("GET", URI, data)
							.then(
									function(response) {
										$scope.relationList = [];

										if (response.data.status == "success") {
											$scope.relationList = response.data.listObject;

										}
									});
					var URI = BASE_URL + ROOT_URL
							+ GETACTIVEIDENTIFICATION;
					GenericService
							.serviceAction("GET", URI, data)
							.then(
									function(response) {
										$scope.identificationList = [];

										if (response.data.status == "success") {
											$scope.identificationList = response.data.listObject;

										}
									});
					var URI = BASE_URL + ROOT_URL
							+ GETACTIVECOUNTRYLIST;
					GenericService
							.serviceAction("GET", URI, data)
							.then(
									function(response) {
										$scope.countryList = [];

										if (response.data.status == "success") {
											$scope.countryList = response.data.listObject;
											console
													.log($scope.countryList);

										}
									});

							}

							$scope.init();

							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								var info = $scope.reserveObject;
								var resCanVal = $scope.reserveCancelFlag;
								$scope.reservePatientForMorgue(info,resCanVal);
							}
							
							$scope.selectItem = function(item) {

								$scope.itemSelecteds = [];
								// If checkbox is checked
								if ($scope.kinDetails) {
									$scope.itemSelecteds = item;
								}
								console.log($scope.itemSelecteds);
							}
							
							
							// for get state by country ID
							$scope.getStateByCountry = function(id) {
								var data = {
									countryId : id
								};
								var URI = BASE_URL + ROOT_URL
										+ GETSTATELISTBYCOUNTRYID;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.stateList = [];

													if (response.data.status == "success") {
														$scope.stateList = response.data.listObject;
														console
																.log($scope.stateList);

													}
												});

							}

							$scope.getDistrictByState = function(id) {
								var data = {
									stateId : id
								};
								var URI = BASE_URL + ROOT_URL
										+ GETDISTRICTLISTBYSTATEID;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.districtList = [];

													if (response.data.status == "success") {
														$scope.districtList = response.data.listObject;
														console
																.log($scope.districtList);

													}
												});

							}

							$scope.getCityByDistrict = function(id) {

								var data = {
									districtId : id
								};
								var URI = BASE_URL + ROOT_URL
										+ GETCITYLISTBYDISTRICTID;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.cityList = [];

													if (response.data.status == "success") {
														$scope.cityList = response.data.listObject;
														console
																.log($scope.cityList);

													}
												});

							}
							$scope.getAreaByCity = function(id) {

								var data = {
									cityId : id
								};
								var URI = BASE_URL + ROOT_URL
										+ GETAREALISTBYCITY;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.areaList = [];

													if (response.data.status == "success") {
														$scope.areaList = response.data.listObject;
													}
												});

							}
							
							$scope.nextOfKinDetails = function(info)
							{
								console.log(info);
							var data = {
										patientId:info.patientId,
										tPatientId:info.tPatientid,
										dPatientId:info.dPatientId,
										organizationId:$scope.organizationId,
										unitId:$scope.unitId
								};
								$scope.itemPatientSelecteds = [];
								$scope.itemPatientSelecteds = info;
								
								var URI = BASE_URL + ROOT_URL
								+ GETNEXTOFKINLISTBYPATIENTID;
						GenericService
								.serviceAction("POST", URI, data)
								.then(
										function(response) {
											$scope.kinDetailsList = [];

											if (response.data.status == "success") {
												$scope.kinDetailsList = response.data.listObject;

											}
										});
						
							}
							
							$scope.updateKinDetailsStatus = function(info){
								$rootScope.startLoader();
								var kinDetails = "";
								var URI = BASE_URL + ROOT_URL + KIN_DETAILS + SLASH + kinDetails + SLASH + info.kinDetailsId + STATUS +SLASH + 'I';
								BillingGenericService.serviceAction(METHOD_PUT, URI, "", NOTIFICATION_MSG_STATUS_FALSE).then(function(response) {
									console.log(response);
											if (response.data.status == "success") {
												$scope.nextOfKinDetails(info);
											}
										});
							}
							
							
							
							
							

							
							$scope.saveKinDetails = function() {
								
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								var data = {
										organizationId:$scope.organizationId,
										unitId:$scope.unitId,
										createdBy:$scope.userId,
										createdDate : $scope.currentDate,
										patientId:$scope.itemSelecteds.patientId,
										tPatientId : $scope.itemSelecteds.tPatientId,
										dPatientId : $scope.itemSelecteds.dPatientId
								}

								
								$scope.dataList = [];
								
								$scope.kinDetails.expiry =moment($scope.datepicker).format(
								'DD-MM-YYYY HH:mm:ss');
								$scope.dataList.push($scope.kinDetails);
								data.kinArray = $scope.dataList;
								
								console.log(data);

								var URI = BASE_URL + ROOT_URL
										+ SAVEKINDEATILSFORMORTUARYALLOCATION;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {

													if (response.data.status == "success") {
														growl
																.success(
																		'Sucessfully!!!.',
																		{
																			title : 'Success!'
																		});
														$scope.nextOfKinDetails(data);
														
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
							
							$scope.releasePatientForMortuary = function() {
								
								if (Object.keys($scope.itemSelecteds).length === 0) {
									growl
											.error(
													'Please select atleast one Kin!!!.',
													{
														title : 'Error!'
													});
								} else {
								
									$scope.currentDate = new Date();
									$scope.currentDate = moment($scope.currentDate)
											.format('DD-MM-YYYY HH:mm:ss');
									
								var data = {
										releaseKinId : $scope.itemSelecteds.kinDetailsId,
										organizationId : $scope.organizationId,
										unitId : $scope.unitId,
										updatedBy : $scope.userId,
										updatedDate : $scope.currentDate,
										releaseNote : $scope.releaseNote,
										mortuaryStatusId : 4,
										mortuaryRequestId :$scope.itemPatientSelecteds.mortuaryRequestId,
										mortuaryBedId : $scope.itemPatientSelecteds.mortuaryBedId
										
								}
								
								console.log(data);

								//return false;
								var URI = BASE_URL + ROOT_URL
										+ RELEASEMORTURYBED;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {

													if (response.data.status == "success") {
														growl
																.success(
																		'Released!!!.',
																		{
																			title : 'Success!'
																		});
														
														angular.element('#nextToKinDetailsModal').modal('hide');
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
