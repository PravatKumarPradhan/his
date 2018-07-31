'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:finalDischargeController
 * @description #finalDischargeController Controller of the myApp
 */

angular
		.module('myApp')
		.controller(
				'ERfinalDischargeController',
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
								
								
								var data = {};
								var commonObj = {
									unitId : $scope.unitId,
									organizationId : $scope.organizationId,
									visitTypeId: 4
								};
			

				

								var URI = BASE_URL + ROOT_URL
										+ GETFINALDISCHARGEPATIENTLIST;
								GenericService
										.serviceAction("POST", URI, commonObj)
										.then(
												function(response) {
													$scope.listOfAdmittedPatientList = [];
													$scope.admittedPatientList = [];

													if (response.data.status == "success") {
														$scope.admittedPatientList = response.data.listObject;
														angular
																.forEach(
																		$scope.admittedPatientList,
																		function(
																				value,
																				index) {

																			var newArr = {};
																			newArr.specialityName = value.specialityName;
																			newArr.dFirstName = value.dFirstName;
																			newArr.pFirstName = value.pFirstName;
																			newArr.reasonDesc = value.reasonDesc;
																			newArr.dob = value.dob;
																			newArr.datepicker = value.doa;
																			newArr.datepickerPdd = value.pdd;
																			newArr.genderCode = value.genderCode;
																			newArr.uhidnumber = value.uhidnumber;
																			newArr.patientId = value.patientId;
																			newArr.tPatientId = value.tPatientId;
																			newArr.paymentEntitlementDesc = value.paymentEntitlementDesc;
																			newArr.bedNumber = value.bedNumber;
																			newArr.admissionId = value.admissionId;
																			newArr.prefixCode = value.prefixCode;
																			newArr.identificationNo = value.identificationNo;
																			newArr.dischargeId = value.dischargeId;
																			newArr.futureDischargeDate = value.futureDischargeDate;
																			newArr.dischargeStatusId = value.dischargeStatusId;
																			newArr.dischargeTypeId = value.dischargeTypeId;
																			newArr.floorName = value.floorName;
																			newArr.dischargeTypeName = value.dischargeTypeName;
																			newArr.dischargeDesc= value.dischargeDesc;
																			newArr.bedId = value.bedId;
																			newArr.wardId  = value.wardId;
																			newArr.nursingStationId = value.nursingStationId;
																			newArr.floorId = value.floorId;
																			newArr.roomId = value.roomId;
																			newArr.bedCategoryId = value.bedCategoryId;
																			newArr.isIcu = value.isIcu;
																			
																			if(value.erBedTypeAllocation == '1')
																			{
																			newArr.erBedTypeAllocationDesc = "ER";
																			}
																		if(value.erBedTypeAllocation == '2')
																		{
																		newArr.erBedTypeAllocationDesc = "Observation";
																		}
																		if(value.erBedTypeAllocation == '3')
																			{
																			newArr.erBedTypeAllocationDesc = "Resuctination";
																			}
																			$scope.listOfAdmittedPatientList
																					.push(newArr);

																		});

													}
												});

						

							}

							$scope.init();

						$scope.vocateBedAgainstPatient = function(item)
						{
							console.log(item);
							$scope.currentDate = new Date();
							$scope.currentDate = moment($scope.currentDate)
									.format('DD-MM-YYYY HH:mm:ss');
							
							var data={
									organizationId :$scope.organizationId,
									unitId : $scope.unitId,
									dischargeId : item.dischargeId,
									admissionId :item.admissionId,
									bedId : item.bedId,
									updatedDate: $scope.currentDate,
									updatedBy : $scope.unitId,
									wardId  : item.wardId,
									nursingStationId : item.nursingStationId,
									floorId : item.floorId,
									roomId : item.roomId,
									bedCategoryId:item.bedCategoryId,
									isIcu : item.isIcu
						
							
							}
							
							console.log(data);
							//return false;
							var URI = BASE_URL + ROOT_URL
									+ PATIENTFINALDISCHARGE;
							GenericService
									.serviceAction("POST", URI, data)
									.then(
											function(response) {

												if (response.data.status == "success") {
													growl
															.success(
																	'Patient Discharged Sucessfully!!!.',
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

						} ]);

