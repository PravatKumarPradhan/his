'use strict';

/**
 * @Author By Dinesh Jagatap
 * @name myApp.controller:tariffMasterController
 * @description #tariffMasterController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'tariffMasterController',
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

							/* init() function for form object create. */

							$scope.init = function() {

								$scope.unitId = 1;
								$scope.organizationId = 1;
								$scope.userId = 1;
								if ($scope.userId == null) {
									$rootScope.loginpage = false;
									$state.go('login');
								}

								// for From date picker
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
								// for To date picker
								$scope.openDatePickerTo = function() {
									$scope.datepickerTo.opened = true;
								};

								$scope.datepickerTo = {
									opened : false
								};

								$rootScope.loginpage = true;
								$scope.country = {}
								$scope.saveBtnFlag = true;
								$scope.updateBtnFlag = false;

								// for popup
								$scope.showModal = false;
								$scope.buttonClicked = "";
								$scope.popUpFlag = true;
								// end for popup
								$scope.nutritionId = 0;

								var data = {};

								$scope.example14model = [];
								$scope.example14settings = {
									scrollableHeight : '200px',
									scrollable : true,
									enableSearch : true
								};

								$scope.tariffMasterObj = {

									tariffId : "",
									tariffCode : "",
									tariffDesc : "",
									validFrom : "",
									validTo : "",
									status:"",
								}
								// for getting active entitlement

								var URI = BASE_URL + ROOT_URL
										+ GETACTIVEPAYMENTENTITLEMENT;
								GenericService
										.serviceAction("GET", URI, data)
										.then(
												function(response) {
													$scope.paymentEntitlementList = [];

													if (response.data.status == "success") {
														$scope.example14data = [];
														angular
																.forEach(
																		response.data.listObject,
																		function(
																				value,
																				index) {
																			var data = {};
																			data.id = value.paymentEntitlementId;
																			data.label = value.paymentEntitlementDesc;
																			$scope.example14data
																					.push(data);
																		});
														console.log('list',$scope.example14data);
													}
												});

								var data = {
									organizationId : $scope.organizationId,
									unitId : $scope.unitId
								}

								var URI = BASE_URL + ROOT_URL
										+ GETTARIFFMASTERLIST;

								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.tariffMasterList = [];

													if (response.data.status == "success") {
														$scope.tariffMasterList = response.data.listObject;
														console
																.log(
																		"tariffMasterList",
																		$scope.tariffMasterList);

													}
												});

							}
							$scope.init();

							$scope.saveTariff = function() {

								var paymentEntitlementList = [];
								angular.forEach($scope.example14model,
										function(value, index) {
											paymentEntitlementList
													.push(value.id);
										});

								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								var data = {
									tariffCode : $scope.tariffMasterObj.tariffCode,
									tariffDesc : $scope.tariffMasterObj.tariffDesc,
									validFrom : moment(
											$scope.tariffMasterObj.validFrom)
											.format('DD-MM-YYYY HH:mm:ss'),
									validTo : moment(
											$scope.tariffMasterObj.validTo)
											.format('DD-MM-YYYY HH:mm:ss'),
									createdBy : $scope.userId,
									updatedBy : $scope.userId,
									createdDate : $scope.currentDate,
									updatedDate : $scope.currentDate,
									unitId : $scope.unitId,
									organizationId : $scope.organizationId,
									status : 'A',
									applicablePaymentEntitlementIdList : paymentEntitlementList,
								}
								console.log("saveTariffMaster", data);
								// return false;
								var URI = BASE_URL + ROOT_URL
										+ SAVETARIFFMASTER;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													if (response.data.status == "success") {
														growl
																.success(
																		'Tariff master added sucessfully!!!.',
																		{
																			title : 'Success!'
																		});
														$scope
																.$broadcast('show-errors-reset');
														$scope.init();
													} else {
														growl
																.error(
																		'Tariff master already exits!!!!.',
																		{
																			title : 'Error!'
																		});
													}
												});
							}

							$scope.showMapperDetails = function(id) {
								var data = {
									tariffId : id,
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ GETPAYMENTENTITLEMENTLISTBYTARIFFID;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.paymentEntitleDetailsList = [];
													if (response.data.status == "success") {
														$scope.paymentEntitleDetailsList = response.data.listObject;

													} else {
														alert("Error!!");
													}
												});

							};

							$scope.showUpdateBtn = function(id) {

								$scope.saveBtnFlag = false;
								$scope.updateBtnFlag = true;
								$scope.tariffMasterObj.tariffId = id;
								var data = {
									tariffId : id,
								}
								var URI = BASE_URL + ROOT_URL
										+ GETTARIFFLISTBYID;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													if (response.data.status == "success") {
														console
																.log(
																		"SingleRecord",
																		response.data.object);

														// return false;
														$scope.tariffMasterObj.tariffCode = response.data.object.tariffCode;
														$scope.tariffMasterObj.tariffDesc = response.data.object.tariffDesc;
														$scope.tariffMasterObj.validFrom = new Date(
																response.data.object.validFrom);
														$scope.tariffMasterObj.validTo = new Date(
																response.data.object.validTo);
														$scope.tariffId = response.data.object.tariffId;
														$scope.applicablePaymentEntitlement = response.data.object.applicablePaymentEntitlementIdList2;
														console.log("aaa",$scope.applicablePaymentEntitlement);
														$scope.example14model = [];
														angular.forEach($scope.applicablePaymentEntitlement,
																		function(value,index) {
																			var d = {};
																			d.id = value.applicablePaymentEntitlementId;
																			$scope.example14model.push(d);
																		});

													} else {
														alert("Error!!");
													}
												});

							};

							// Function For update Nutrition
							$scope.updateTariff = function() {

								// $scope.$broadcast('show-errors-check-validity');

								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								var paymentEntitlementList = [];
								angular.forEach($scope.example14model,
										function(value, index) {
											paymentEntitlementList
													.push(value.id);
										});

								var paymentEntitlementListOld = [];
								angular.forEach($scope.applicablePaymentEntitlement,
												function(value, index) {
													paymentEntitlementListOld.push(value.applicablePaymentEntitlementId);
												});

								$scope.dataUpdate = {
									tariffId : $scope.tariffMasterObj.tariffId,
									tariffCode : $scope.tariffMasterObj.tariffCode,
									tariffDesc : $scope.tariffMasterObj.tariffDesc,
									validFrom : moment(
											$scope.tariffMasterObj.validFrom)
											.format('DD-MM-YYYY HH:mm:ss'),
									validTo : moment(
											$scope.tariffMasterObj.validTo)
											.format('DD-MM-YYYY HH:mm:ss'),
									applicablePaymentEntitlementIdList : paymentEntitlementList,
									applicablePaymentEntitlementIdListOld : paymentEntitlementListOld,
									unitId : $scope.unitId,
									organizationId : $scope.organizationId,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate,
								}

								console.log("new", paymentEntitlementList);
								console.log("old", paymentEntitlementListOld);
								console.log("Update", $scope.dataUpdate);
//								return false;
								// if ($scope.nutritionform.$valid) {
								var URI = BASE_URL + ROOT_URL
										+ UPDATETARIFFMASTER;
								GenericService.serviceAction("POST", URI,$scope.dataUpdate)
										.then(function(response) {
													if (response.data.status == "success") {
														growl.success('Tariff master updated sucessfully!!!.',
																		{
																			title : 'Success!'
																		});
														$scope.$broadcast('show-errors-reset');
														$scope.init();
													} else {
														growl.error('Tariff master already exits!!!!.',
																		{
																			title : 'Error!'
																		});
													}
												});
								// }

							}
							//update tariff master status
							$scope.updateStatus = function(id, type) {

								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								var data = {
									tariffId : id,
									status : type,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ UPDATETARIFFMASTERSTATUS;
								GenericService.serviceAction("POST", URI, data)
										.then(function(response) {

													if (response.data.status == "success") {
														growl.success('Tariff master status changed sucessfully!!!.',
																		{
																			title : 'Success!'
																		});
													} else {
														growl.error('something wrong!!!!.',
																		{
																			title : 'Error!'
																		});
													}
												});
							}
							//update tariff master mapper status
							$scope.updateStatusMapper = function(id, type) {

								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								var data = {
									tariffPaymentEntitlementMapperId : id,
									status : type,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ UPDATESTATUSFORTARIFFPAYMENTMAPPER;
								GenericService.serviceAction("POST", URI, data)
										.then(function(response) {

													if (response.data.status == "success") {
														growl.success('Payment Entitlement status changed sucessfully!!!.',
																		{
																			title : 'Success!'
																		});
													} else {
														growl.error('something wrong!!!!.',
																		{
																			title : 'Error!'
																		});
													}
												});
							}

						} ]);