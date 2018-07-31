'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:bedTypeAllocationERController
 * @description #bedTypeAllocationERController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'bedTypeAllocationERController',
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

								/** Variables for pagination */
								$scope.offset = 0;
								$scope.noOfRecordsPerPage = 10;
								$scope.totalNoOfRecords;
								$scope.searchKeyword;
								$scope.commonListCount;

								$rootScope.loginpage = true;
								$scope.itemSelecteds = {};
								$scope.beditemSelecteds = {};
								$scope.bedTypeAllocationEr = {};
								$scope.bedEr = {};

								// for popup
								$scope.showModal = false;
								$scope.buttonClicked = "";
								$scope.popUpFlag = true;
								// end for popup
								
								var data = {

										organizationId : $scope.organizationId,
										unitId : $scope.unitId
								};
								var URI = BASE_URL + ROOT_URL
								+ GETACTIVEDOCTORLIST;
						GenericService
								.serviceAction("POST", URI, data)
								.then(
										function(response) {
											$scope.doctorsList = [];

											if (response.data.status == "success") {
												$scope.doctorsList = response.data.listObject;

											}
										});
						

							}

							$scope.init();

							// For Paginations Start
							$scope.setNoOfRecords = function() {
								$scope.initBedTypeAllocationERList(
										$scope.organizationId, $scope.offset,
										$scope.noOfRecordsPerPage);
							};
							$scope.initBedTypeAllocationERList = function(
									orgId, offset, noOfRecordsPerPage) {
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
										: 10;
								var URI = BASE_URL + ROOT_URL
										+ GETBEDTYPEALLOCATIONERLIST;
								var URI1 = BASE_URL + ROOT_URL
										+ COUNTBEDTYPEALLOCATIONRE;

								var data = {
									organizationId : orgId,
									unitId : $scope.unitId,
									offset : offset,
									noOfRecordsPerPage : noOfRecordsPerPage
								}

								var data1 = {
									organizationId : $scope.organizationId,
									unitId : $scope.unitId
								}

								// For Get Bed Type Allocation List Using Offset
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.bedTypeAllocationList = [];

													if (response.data.status == "success") {
														$scope.bedTypeAllocationERLists = response.data.listObject;
														angular
																.forEach(
																		$scope.bedTypeAllocationERLists,
																		function(
																				value,
																				index) {
																			var newArr = {};
																			newArr.admissionId = value.admissionId;
																			newArr.uhidnumber = value.uhidnumber;
																			newArr.genderName = value.genderName;
																			newArr.patientName = value.patientName;
																			newArr.age = value.age;
																			newArr.ageFormat = value.ageFormat;
																			newArr.tpatientId = value.tPatientId;
																			newArr.patientId = value.patientId;
																			newArr.dob = value.dob;
																			newArr.doctorId = 0;
																			newArr.triageCategoryId = 0;
																			newArr.admissionDetailsId = value.admissionDetailsId;
																			newArr.createdDate = value.createdDate;
																			newArr.createdBy = value.createdBy;
																			newArr.admissionNumber = value.admissionNumber;
																			newArr.priorityDesc = value.priorityDesc;
																			newArr.encounterDate = value.encounterDate;
																			$scope.bedTypeAllocationList
																					.push(newArr);
																		});
														console
																.log($scope.bedTypeAllocationList);

													}
												});

								// For Count Bed Type Allocation List
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

							$scope.getBedTypeAllocationERList = function(orgId,
									offset, noOfRecordsPerPage) {
								console.log("offset", offset);
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
										: 10;
								var URI = BASE_URL + ROOT_URL
										+ GETBEDTYPEALLOCATIONERLIST;
								var data = {
									organizationId : $scope.organizationId,
									unitId : $scope.unitId,
									offset : offset,
									noOfRecordsPerPage : noOfRecordsPerPage
								}

								console.log("URI", URI);
								// For Get Bed Type Allocation List Using Offset
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.bedTypeAllocationList = [];

													if (response.data.status == "success") {
														$scope.bedTypeAllocationERLists = response.data.listObject;
														angular
																.forEach(
																		$scope.bedTypeAllocationERLists,
																		function(
																				value,
																				index) {
																			var newArr = {};
																			newArr.admissionId = value.admissionId;
																			newArr.uhidnumber = value.uhidnumber;
																			newArr.genderName = value.genderName;
																			newArr.patientName = value.patientName;
																			newArr.age = value.age;
																			newArr.ageFormat = value.ageFormat;
																			newArr.tpatientId = value.tPatientId;
																			newArr.patientId = value.patientId;
																			newArr.dob = value.dob;
																			newArr.doctorId = 0;
																			newArr.triageCategoryId = 0;
																			newArr.admissionDetailsId = value.admissionDetailsId;
																			newArr.createdDate = value.createdDate;
																			newArr.createdBy = value.createdBy;
																			newArr.admissionNumber = value.admissionNumber;
																			newArr.priorityDesc = value.priorityDesc;
																			newArr.encounterDate = value.encounterDate;
																			$scope.bedTypeAllocationList.push(newArr);
																		});
														console
																.log($scope.bedTypeAllocationList);

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
									$scope.getBedTypeAllocationERList(
											$scope.organizationId,
											$scope.pager.startIndex,
											$scope.pager.pageSize);
								}
							}

							$scope.initBedTypeAllocationERList(
									$scope.organizationId, $scope.offset,
									$scope.noOfRecordsPerPage);

							// For Paginations End

							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.saveAdmittedPatientER();
							}

							$scope.selectItem = function(item) {

								$scope.itemSelecteds = [];
								// If checkbox is checked
								if ($scope.bedTypeAllocationEr) {
									$scope.itemSelecteds = item;
								}
							}
							$scope.selectItemBed = function(item) {
								console.log(item);
								$scope.beditemSelecteds = [];
								// If checkbox is checked
								if ($scope.bedEr) {
									$scope.beditemSelecteds = item;
								}
							}

							$scope.selectedBedObject = function() {
								console.log($scope.beditemSelecteds);
								angular.element('#bedAllocationForER').modal(
										'hide');
							}

							$scope.getERBedPopup = function(id) {
								if (id == '1') {
									$('#bedAllocationForER').modal();

									var data = {
										organizationId : $scope.organizationId,
										unitId : $scope.unitId
									}

									var URI = BASE_URL + ROOT_URL
											+ GETACTIVEERBEDLIST;
									GenericService
											.serviceAction("POST", URI, data)
											.then(
													function(response) {
														$scope.activeERBedList = [];

														if (response.data.status == "success") {
															$scope.activeERBedList = response.data.listObject;

														}
													});

								}
							}

							$scope.saveAdmittedPatientER = function() {

								console.log($scope.itemSelecteds);
								if (Object.keys($scope.itemSelecteds).length === 0) {
									growl
											.error(
													'Please select atleast one patient!!!.',
													{
														title : 'Error!'
													});
								} else {

									if ($scope.popUpFlag) {
										$scope.showModal = !$scope.showModal;

									} else {
										if ($scope.itemSelecteds.ErAreaId == 1) {
											if (Object.keys($scope.beditemSelecteds).length === 0) {
												growl
														.error(
																'Please select ER bed for patient!!!.',
																{
																	title : 'Error!'
																});
											} else {
												
												$scope.currentDate = new Date();
												$scope.currentDate = moment($scope.currentDate)
														.format('DD-MM-YYYY HH:mm:ss');
												
												  var now = new Date();
												  now.setHours(now.getHours()+4);
												  now.setMinutes(now.getMinutes()+0);
												// alert(moment(now).format('DD-MM-YYYY HH:mm:ss'))
												 //$scope.ProductionDate = now; 
												  
												var data = {
														
												        admissionId : $scope.itemSelecteds.admissionId,
												        admissionDetailsId :$scope.itemSelecteds.admissionDetailsId,
														floorId : $scope.beditemSelecteds.floorId,
														wardId : $scope.beditemSelecteds.wardId,
														roomId : $scope.beditemSelecteds.roomId,
														pdd :  moment(now).format('DD-MM-YYYY HH:mm:ss'),
														doa :  $scope.currentDate,
														bedId : $scope.beditemSelecteds.bedId,
														doctorId : $scope.itemSelecteds.doctorId,
														updatedBy : $scope.userId,
														updatedDate : $scope.currentDate,
														erBedTypeAllocation : $scope.itemSelecteds.ErAreaId,
														triageCategoryId : $scope.itemSelecteds.triageCategoryId,
														visitTypeId : '4',
														organizationId:$scope.organizationId,
														unitId : $scope.unitId,
														createdBy : $scope.itemSelecteds.createdBy,
														createdDate : $scope.itemSelecteds.createdDate,
														admissionNumber:$scope.itemSelecteds.admissionNumber,
														bedCategoryId  :$scope.beditemSelecteds.bedCategoryId
													};
												
											}
										}
										else
											{
											
											$scope.currentDate = new Date();
											$scope.currentDate = moment($scope.currentDate)
													.format('DD-MM-YYYY HH:mm:ss');
											
											  var now = new Date();
											  now.setHours(now.getHours()+4);
											  now.setMinutes(now.getMinutes()+0);
											  
											var data = {
													admissionId : $scope.itemSelecteds.admissionId,
													 admissionDetailsId :$scope.itemSelecteds.admissionDetailsId,
													pdd :  moment(now).format('DD-MM-YYYY HH:mm:ss'),
													doa :  $scope.currentDate,
													doctorId : $scope.itemSelecteds.doctorId,
													updatedBy : $scope.userId,
													updatedDate : $scope.currentDate,
													erBedTypeAllocation : $scope.itemSelecteds.ErAreaId,
													triageCategoryId : $scope.itemSelecteds.triageCategoryId,
													visitTypeId : '4',
													organizationId:$scope.organizationId,
													unitId : $scope.unitId,
													createdBy : $scope.itemSelecteds.createdBy,
													createdDate : $scope.itemSelecteds.createdDate,
													admissionNumber:$scope.itemSelecteds.admissionNumber,
												};
											}
										if($scope.itemSelecteds.patientId > 0)
										{
											data.patientId = $scope.itemSelecteds.patientId;
										}
									else{
										data.tPatientId = $scope.itemSelecteds.tpatientId;
									}
										console.log(data);
										
										var URI = BASE_URL + ROOT_URL +
										 ADDBEDTYPEALLOCATIONER;
										 GenericService .serviceAction("POST",
										 URI, data) .then( function(response) {
											 
										 if (response.data.status == "success") { 
											 growl .success( 'ER Patient Admitted Sucessfully!!!.', {title : 'Success!' }); 
											 $scope.init();
											 }
										 else { 
											 growl .error( 'Something wrongs!!!!.', { title : 'Error!' }); 
											 }
										 $scope.initBedTypeAllocationERList($scope.organizationId,
										 $scope.offset,
										 $scope.noOfRecordsPerPage);
										
										 });
										
									}
								}

							}

						} ]);
