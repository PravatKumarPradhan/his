'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:deathRegistrationController
 * @description #deathRegistrationController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'deathRegistrationController',
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
									
									
									// for datePicker2 expiry
									$scope.dateOptions = {
										formatYear : 'yyyy',
										showWeeks : false
									};
									$scope.openDatePickerPdd = function() {
										$scope.datepickerPdd.opened = true;
									};

									$scope.datepickerPdd = {
										opened : false
									};
									

									//for datepicker 3 expected
									$scope.dateOptions = {
											formatYear : 'yyyy',
											showWeeks : false
										};

										$scope.openDatePickerExpected = function() {
											$scope.datepickerExpected.opened = true;
										};

										$scope.datepickerExpected = {
											opened : false
										};
							
								$scope.dataList = [ {
									documentTypeId : '',
									document : ''
								} ];
								
								$scope.deathObject = {
									firstName : '',
									lastName : '',
									genderId:'',
									markOnDecease:'',
									dateOfDeath:'',
									durationFormat:'',
									durationValue:'',
									isMedicoLegal:'N',
									expectedArrivalTime :'',
									expectedArrivalDate :'',
									organizationId : $scope.organizationId,
									unitId : $scope.unitId,
									createdBy:$scope.userId,
									updatedBy:$scope.userId,
									status:'A',
									mortuaryStatusId :1,
									visitTypeId : 5
									
								};
								
								$scope.kinObject = {
										prefixId : '',
										kinName : '',
										relationId : '',
										mobileNo : '',
										phoneNo : '',
										expiry : '',
										identificationId : '',
										identificationNo : '',
										isGuarantor : 'N',
										address : '',
										countryId : '',
										stateId : '',
										districtId : '',
										cityId : '',
										areaId : '',
										postCode : ''
									};


							
							
								

								$rootScope.loginpage = true;
								$scope.saveBtnFlag = false;
								$scope.updateBtnFlag = true;

							

								// for popup
								$scope.showModal = false;
								$scope.buttonClicked = "";
								$scope.popUpFlag = true;
								// end for popup

								var commonObj = {
									unitId : $scope.unitId,
									organizationId : $scope.organizationId
								};
								var data = {};
								
								var URI = BASE_URL + ROOT_URL
								+ GETACTIVEGENDERLIST;
						GenericService
								.serviceAction("GET", URI, data)
								.then(
										function(response) {
											$scope.genderList = [];

											if (response.data.status == "success") {
												$scope.genderList = response.data.listObject;

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
								$scope.saveDeathRegistration();
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



							$scope.add = function() {
								var data = {
									documentTypeId : '',
									document : ''
								};
								$scope.dataList.push(data);
							};

							$scope.remove = function() {
								var newDataList = [];
								var le = $scope.dataList.length;
								var i = 1;
								angular.forEach($scope.dataList, function(v) {
									if (le != i) {
										newDataList.push(v);
									}
									i++;
								});
								$scope.dataList = newDataList;
							};
							

							// function for initializa clockpicker
							$scope.initClockPicker = function() {

								$('.clockpicker').clockpicker();
							}
							
							$scope.saveDeathRegistration = function() {
								if ($scope.popUpFlag) {
									$scope.showModal = !$scope.showModal;

								} else {
								
								console.log($scope.kinObject);
								console.log($scope.deathObject);

								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');
								
								$scope.dataList = [];
								
								$scope.kinObject.expiry =moment($scope.datepickerPdd).format(
								'DD-MM-YYYY HH:mm:ss');
								$scope.dataList.push($scope.kinObject);
								
								$scope.deathObject.dateOfDeath = moment($scope.datepicker).format(
								'DD-MM-YYYY HH:mm:ss');
								
								$scope.deathObject.expectedArrivalDate = moment($scope.datepickerExpected).format(
								'DD-MM-YYYY HH:mm:ss');
								
								if($scope.deathObject.durationFormat == '1')
									{
									$scope.deathObject.durationValue = $scope.timeValueOfDuration;
									}
								else
									{
									$scope.deathObject.durationValue = $scope.timeValueOfDay;
									}
								
								$scope.deathObject.createdDate = $scope.currentDate;
								$scope.deathObject.updatedDate = $scope.currentDate;
								
								$scope.deathObject.kinArray = $scope.dataList;
								
								console.log($scope.deathObject);

								//return false;
								var URI = BASE_URL + ROOT_URL
										+ SAVEDEATHREGISTATION;
								GenericService
										.serviceAction("POST", URI,$scope.deathObject)
										.then(
												function(response) {

													if (response.data.status == "success") {
														growl
																.success(
																		'Death Registration sucessfully!!!.',
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
