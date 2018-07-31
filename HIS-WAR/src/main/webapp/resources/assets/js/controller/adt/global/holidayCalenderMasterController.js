'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:holidayCalenderMasterController
 * @description # holidayCalenderMasterController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'holidayCalenderMasterController',
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

							// for datePicker
							$scope.dateOptions = {
								formatYear : 'yyyy',
								showWeeks : false
							};
							$scope.openDatePicker = function() {
								// alert("in");
								$scope.datepicker.opened = true;
							};

							$scope.datepicker = {
								opened : false
							};

							$scope.openDatePickerForNewDOA = function() {

								$scope.datepickerForNewDOA.opened = true;
							};

							$scope.datepickerForNewDOA = {
								opened : false
							};

							$scope.test = function() {
								var day = $scope.datepicker.getDay() + 1;
								$scope.holiday.holidayDayId = day;
								var data = {
									dayId : day
								}
								var URI = BASE_URL + ROOT_URL + GETDAYOBJ;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {

													if (response.data.status == "success") {
														$scope.dayName = response.data.listObject[0].day;

													}
												});

							}

							/* init() function for form object create. */
							$scope.init = function() {

							
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');
								
								var cookieObject = $cookies.getObject('cookieObject');
								if(cookieObject == undefined){
									$state.go('login');
									return;
								}
								$scope.unitId = cookieObject.unitId;
								$scope.organizationId = cookieObject.organizationId;
								$scope.userId = 1;

								/** Variables for pagination */
								$scope.offset = 0;
								$scope.noOfRecordsPerPage = 10;
								$scope.totalNoOfRecords;
								$scope.searchKeyword;
								$scope.commonListCount;

								$rootScope.loginpage = true;
								$scope.holiday = {}
								$scope.saveBtnFlag = false;
								$scope.updateBtnFlag = true;

								// for popup
								$scope.showModal = false;
								$scope.buttonClicked = "";
								$scope.popUpFlag = true;
								// end for popup

								var data = {};
								// $scope.status = "NO";
								/*
								 * var URI = BASE_URL + ROOT_URL +
								 * GETHOLIDAYLIST; GenericService
								 * .serviceAction("GET", URI, data) .then(
								 * function(response) { $scope.holidayList = [];
								 * 
								 * if (response.data.status == "success") {
								 * $scope.holidayList =
								 * response.data.listObject; console
								 * .log($scope.holidayList);
								 *  } });
								 */

							}

							$scope.init();

							// For Paginations Start
							$scope.setNoOfRecords = function() {
								$scope.initHolidayCalenderMasterList(
										$scope.organizationId, $scope.offset,
										$scope.noOfRecordsPerPage);
							};
							$scope.initHolidayCalenderMasterList = function(
									orgId, offset, noOfRecordsPerPage) {
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
										: 10;
								var URI = BASE_URL + ROOT_URL + GETHOLIDAYLIST;
								var URI1 = BASE_URL + ROOT_URL
										+ COUNTHOLIDAYMASTER;

								var data = {
									organizationId : orgId,
									offset : offset,
									noOfRecordsPerPage : noOfRecordsPerPage
								}

								var data1 = {
									organizationId : $scope.organizationId
								}

								// For Get Holiday Calender List Using Offset
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.holidayList = [];

													if (response.data.status == "success") {
														$scope.holidayList = response.data.listObject;

													}
												});

								// For Count Holiday Calender List
								GenericService
										.serviceAction("POST", URI1, data1)
										.then(
												function(response) {
													if (response.data.status == "success") {
														console
																.log(response.data);
														$scope.commonListCount = response.data.object;
														$scope
																.setPage(1,
																		false);

													}
												});
							}

							$scope.getHolidayCalenderMasterList = function(
									orgId, offset, noOfRecordsPerPage) {
								console.log("offset", offset);
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
										: 10;
								var URI = BASE_URL + ROOT_URL + GETHOLIDAYLIST;
								var data = {
									organizationId : $scope.organizationId,
									offset : offset,
									noOfRecordsPerPage : noOfRecordsPerPage
								}

								console.log("URI", URI);
								// For Get Holiday Calender List Using Offset
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.holidayList = [];

													if (response.data.status == "success") {
														$scope.holidayList = response.data.listObject;

													}
												});
							}

							$scope.pager = {};
							$scope.page;

							$scope.setPage = function(page, flag) {
								if (page < 1 || page > $scope.pager.totalPages) {
									return;
								}
								$scope.pager = PagerService.GetPager(
										$scope.commonListCount, page,
										$scope.noOfRecordsPerPage);
								if (flag) {
									$scope.getHolidayCalenderMasterList(
											$scope.organizationId,
											$scope.pager.startIndex,
											$scope.pager.pageSize);
								}
							}

							$scope.initHolidayCalenderMasterList(
									$scope.organizationId, $scope.offset,
									$scope.noOfRecordsPerPage);

							// For Paginations End

							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.saveHoliday();
							}

							// Function For Save Holiday
							$scope.saveHoliday = function() {

								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');
								$scope.$broadcast('show-errors-check-validity');
								if ($scope.holidayform.$valid) {
									if ($scope.popUpFlag) {
										$scope.showModal = !$scope.showModal;

									} else {
										var NewDate = $rootScope
												.getFormatDate($scope.datepicker);
										NewDate = NewDate + ' 00:00:00';
										var data = {
											holidayCalenderCode : $scope.holiday.holidayCode,
											holidayCalenderDesc : $scope.holiday.holidayDesc,
											holidayDate : NewDate,
											dayId : $scope.holiday.holidayDayId,
											createdBy : $scope.userId,
											updatedBy : $scope.userId,
											organizationId : $scope.organizationId,
											status : 'A',
											createdDate : $scope.currentDate,
											updatedDate : $scope.currentDate
										}

										console.log(data);

										var URI = BASE_URL + ROOT_URL
												+ ADDHOLIDAY;
										GenericService
												.serviceAction("POST", URI,
														data)
												.then(
														function(response) {

															if (response.data.status == "success") {
																growl
																		.success(
																				'Holiday added sucessfully!!!.',
																				{
																					title : 'Success!'
																				});
																// $scope.datepicker.opened
																// = false;
																$scope
																		.$broadcast('show-errors-reset');
																$scope.init();

															} else {
																growl
																		.error(
																				'Holiday already exits!!!!.',
																				{
																					title : 'Error!'
																				});
															}
															$scope
																	.initHolidayCalenderMasterList(
																			$scope.organizationId,
																			$scope.offset,
																			$scope.noOfRecordsPerPage);

														});
									}
								}

							}

							// Function for get single holiday
							$scope.showUpdateBtn = function(id) {

								$scope.saveBtnFlag = true;
								$scope.updateBtnFlag = false;

								var data = {

									holidayCalenderId : id
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ GETSINGLEHOLIDAY;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													// $scope.speciality =[];
													if (response.data.status == "success") {

														$scope.holiday.holidayId = response.data.listObject[0].holidayCalenderId;
														$scope.holiday.holidayCode = response.data.listObject[0].holidayCalenderCode;
														$scope.holiday.holidayDesc = response.data.listObject[0].holidayCalenderDesc;
														$scope.holiday.holidayDayId = response.data.listObject[0].dayId;
														var datepickerRes = response.data.listObject[0].holidayDate;
														var newDate = $rootScope
																.getChangedFormatedDate(datepickerRes);
														$scope.datepicker = new Date(
																newDate);
														$scope.datepicker.opened = false;
														// $scope.dayNameFun(response.data.listObject[0].dayId);
														var data = {
															dayId : $scope.holiday.holidayDayId
														}
														var URI = BASE_URL
																+ ROOT_URL
																+ GETDAYOBJ;
														GenericService
																.serviceAction(
																		"POST",
																		URI,
																		data)
																.then(
																		function(
																				response) {

																			if (response.data.status == "success") {
																				$scope.dayName = response.data.listObject[0].day;

																			}
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

							};

							// Function for update holiday status
							$scope.updateStatus = function(id, type) {
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								type = (type == "A") ? "A" : "I";

								var data = {
									holidayCalenderId : id,
									status : type,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ UPDATEHOLIDAYSTATUS;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {

													if (response.data.status == "success") {
														growl
																.success(
																		'Holiday status changed sucessfully!!!.',
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

							// Function for update holiday
							$scope.updateHoliday = function() {
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');
								$scope.$broadcast('show-errors-check-validity');
								if ($scope.holidayform.$valid) {
									var NewDate = $rootScope
											.getFormatDate($scope.datepicker);

									var data = {

										holidayCalenderId : $scope.holiday.holidayId,
										holidayCalenderCode : $scope.holiday.holidayCode,
										holidayCalenderDesc : $scope.holiday.holidayDesc,
										holidayDate : NewDate + ' 00:00:00',
										dayId : $scope.holiday.holidayDayId,
										updatedBy : $scope.userId,
										updatedDate : $scope.currentDate
									}
									console.log(data);
									var URI = BASE_URL + ROOT_URL
											+ UPDATEHOLIDAY;
									GenericService
											.serviceAction("POST", URI, data)
											.then(
													function(response) {

														if (response.data.status == "success") {
															growl
																	.success(
																			'Holiday updated sucessfully!!!.',
																			{
																				title : 'Success!'
																			});
															/*
															 * $scope.saveBtnFlag =
															 * false;
															 * $scope.updateBtnFlag =
															 * true;
															 * $scope.datepicker.opened =
															 * false;
															 * $scope.init();
															 */
															$scope
																	.$broadcast('show-errors-reset');
															$scope.init();

														} else {
															growl
																	.error(
																			'Holiday already exits!!!!.',
																			{
																				title : 'Error!'
																			});
														}

														$scope
																.initHolidayCalenderMasterList(
																		$scope.organizationId,
																		$scope.offset,
																		$scope.noOfRecordsPerPage);

													});
								}

							}

						} ]);
