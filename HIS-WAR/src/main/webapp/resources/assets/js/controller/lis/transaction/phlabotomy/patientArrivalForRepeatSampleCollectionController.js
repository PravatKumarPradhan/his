/**
 * @author Sruaj Kumbhoje
 */

angular
		.module('myApp')
		.controller(
				"patientArrivalForRepeatSampleCollectionController",
				[
						'$scope',
						'$rootScope',
						'$state',
						'$cookies',
						'GenericService',
						'PagerService',
						'promiseFactoryWithObject',
						'$sessionStorage',
						'growl',
						function($scope, $rootScope, $state, $cookies,
								GenericService, PagerService, promiseFactoryWithObject,
								$sessionStorage, growl) {

							$rootScope.loginpage = true;
							$scope.LISDynamicLabel = "Patient Arrival";

							/** Variables for pagination */
							$scope.offset = 0;
							$scope.noOfRecordsPerPage = 10;
							$scope.patientArrivalList;
							$scope.totalNoOfRecords;
							$scope.searchKeyword;
							$scope.commonListCount;

							$scope.orgId = 1;
							$scope.orgUnitId = 1;
							$scope.currentStatus="R";
							$scope.createdBy = 1;
							$scope.updatedBy = 1;
							$scope.testId=1;
							$scope.sendToDept=12;
							$scope.statusId=2;
							$scope.deptId=LAB_DEPT;
						
							$scope.listLabPatientArrival = [];
							$scope.labPatientArrivalDetails={
									"patientArrivalMasterId": {
										"statusId": "",
										"orderId": "",
										"labSmpDtlsId": "",
										"labSmpMstId": "",
										"patientId": ""
								
									},
									"testId":$scope.testId,
									"pendingCount": "",
									"orgId": $scope.orgId,
									"orgUnitId":$scope.orgUnitId,
									"deptId": "",
									"doctorId": "",
									"collectionTime":"",
									"createdBy": $scope.createdBy,
									"createdDate": "",
									"orderDetailsId":""
							
								}
							
							/* code for setting Label */
							$(".selectedPageName").text("Patient Arrival List");

							
							// ====================================================CODE
							      // Patient Arrival 
							// LIST===========================================================
							
							
							$scope.setNoOfRecords = function() {
							
								$scope.initPatientArrivalList($scope.orgId,$scope.orgUnitId, $scope.offset, $scope.noOfRecordsPerPage);
							};
							$scope.initPatientArrivalList = function(orgId,orgUnitId,offset, noOfRecordsPerPage)
							{
								var data1 = {
										"orgId":orgId,
										"orgUnitId":orgUnitId,
										"deptId":$scope.deptId,
										"statusId":$scope.statusId,
										"offset":offset,
										"currentStatus":"A",
										"noOfRecordsPerPage":noOfRecordsPerPage
										
								};
								var data2 = {
										"orgId":orgId,
										"deptId":$scope.deptId,
										"orgUnitId":orgUnitId,
										"statusId":$scope.statusId,
										
								};
								
								$rootScope.startLoader();
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage : 10;
								var URI1 = BASE_URL + ROOT_URL + LIS_TRANSACTION + GET_PATIENT_ARRIVAL_LIST;
								var URI2 = BASE_URL + ROOT_URL + LIS_TRANSACTION + GET_PATIENT_ARRIVAL_COUNT;
								console.log("URI1",URI1);
								console.log("URI2",URI2);
								promiseFactoryWithObject.setPromisesWithObject(URI1, URI2, METHOD_POST, METHOD_POST,data1,data2).then(function(response) 
								{
									$rootScope.stopLoader();
									$scope.patientArrivalList = response[0].listObject;
									$scope.commonListCount = response[1].object;
									$scope.setPage(1, false);
								});
							}

						$scope.initPatientArrivalList($scope.orgId, $scope.orgUnitId,$scope.offset, $scope.noOfRecordsPerPage);
							
							
							$scope.getPatientArrivalList = function(orgId,orgUnitId, offset, noOfRecordsPerPage)
							{
									try {
										
										var data = {
												"orgId":orgId,
												"orgUnitId":orgUnitId,
												"statusId":$scope.statusId,
												"offset":offset,
												"currentStatus":"A",
												"noOfRecordsPerPage":noOfRecordsPerPage
												
												
										}
										$rootScope.startLoader();
										console.log("PatientArrival",JSON.stringify($scope.data));
										
										var URI =  BASE_URL + ROOT_URL+ LIS_TRANSACTION + GET_PATIENT_ARRIVAL_LIST ;
										GenericService.serviceAction("POST", URI, data).then(function(response) 
										{	
											if (response.data.status == 'success')
											{
												$rootScope.stopLoader();
												$scope.patientArrivalList = response.data.listObject;
												$rootScope.stopLoader();
											} else if(response.data.status == 'error')
											{
												$rootScope.stopLoader();
												growl.error(response.data.message,
														{
															title : 'Error!'
														});
											}
											
										});
									} catch (e) {
										console.log("Exception",e.message);
									}
									
							}
								
								
							/** code for setting all checkboxes */
							$scope.checkAll = function() {
								if ($scope.selectedAll) {
									$scope.selectedAll = true;
								} else {
									$scope.selectedAll = false;
								}
								angular
										.forEach(
												$scope.patientArrivalList,
												function(labDetailsObj) {
													labDetailsObj.isArrived = $scope.selectedAll;
												});
							}

							
							$scope.pager = {};
							$scope.page;

							$scope.setPage = function(page, flag) {
								if (page < 1 || page > $scope.pager.totalPages)
								{
									return;
								}
								$scope.pager = PagerService.GetPager($scope.commonListCount, page, $scope.noOfRecordsPerPage);
								if (flag)
								{
									$scope.getPatientArrivalList($scope.orgId,$scope.orgUnitId, $scope.pager.startIndex, $scope.pager.pageSize);
								}
							}

						
							/** Code for Clock Picker */
							$scope.timeToDateConverter = function(hourMin)
							{
								try {
									var time = hourMin.split(":");
									var hours = time[0] ;
									var minutes = time[1];
									var timeToDate =     moment([moment().year(), 
										      moment().month()+1,
					                          moment().date(),
					                          hours, 
					                          minutes,
					                          moment().seconds(), 
					                          moment().milliseconds()]).toDate(); 
									return timeToDate.getTime();
								} catch (e) {
									console.log('Exception',e);
								}
							}
						
							
							// code for Activating Patient Arrival
							$scope.saveDetails = function() {
								

								
								$rootScope.startLoader();
								angular
										.forEach(
												$scope.patientArrivalList,
												function(value, key) {
													if (value.isArrived == true) {
														
														var labPatientArrivalDetails = angular
																.copy($scope.labPatientArrivalDetails);
														
														labPatientArrivalDetails.patientArrivalMasterId.statusId = REPAT_PATIENT;
														labPatientArrivalDetails.patientArrivalMasterId.orderId =value.orderId;
														labPatientArrivalDetails.patientArrivalMasterId.labSmpDtlsId=value.labSampleDtlsId;
														labPatientArrivalDetails.patientArrivalMasterId.labSmpMstId=value.labSampleId;
														labPatientArrivalDetails.patientArrivalMasterId.patientId=value.patientId;
														labPatientArrivalDetails.pendingCount =value.samplePendingCount;
														labPatientArrivalDetails.deptId =value.deptId;
														labPatientArrivalDetails.doctorId =value.doctorId;
														labPatientArrivalDetails.orderDetailsId=value.orderDetailsId;
														labPatientArrivalDetails.collectionTime =$scope.timeToDateConverter(value.collectionTime);
														labPatientArrivalDetails.createdBy = $scope.createdBy;
														
														$scope.listLabPatientArrival
																.push(labPatientArrivalDetails);
														
														console.log("$scope.listLabPatientArrival", JSON
																.stringify($scope.listLabPatientArrival));
													}
												});
								
								console.log("$scope.listLabPatientArrival", JSON
										.stringify($scope.listLabPatientArrival));
								var URI = BASE_URL + ROOT_URL
										+ LIS_TRANSACTION + ADD_PATIENT_ARRIVAL_DETAILS;

								try {
									console.log("PatientArrival", URI);
									GenericService
											.serviceAction("POST", URI,
													$scope.listLabPatientArrival)
											.then(
													function(response) {
														if (response.data.status == 'success') {
															$rootScope.stopLoader();
															$scope.initPatientArrivalList(
																			$scope.orgId,
																			$scope.orgUnitId,
																			$scope.offset,
																			$scope.noOfRecordsPerPage);
															growl.success(response.data.message,
																			{
																				title : 'Success!'
																			});
															$scope
																	.$broadcast('show-errors-reset');

														} else if (response.data.status == 'error') {
															growl
																	.error(
																			response.data.message,
																			{
																				title : 'Error!'
																			});
														}
													});
								} catch (e) {
									console.log("Exception", e.message);
								}
							}
							
							//function to set clockpicker in ng-repeat
							$scope.initTime = function(){
								$('.clockpicker').clockpicker();
							}
							
						} ]);