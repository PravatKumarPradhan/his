'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:deliveryTypeMasterController
 * @description # deliveryTypeMasterController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'deliveryTypeMasterController',
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
								$cookies, $rootScope, GenericService, growl,$state) {

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
								$scope.deliverytype = {}
								$scope.saveBtnFlag = false;
								$scope.updateBtnFlag = true;
								
								// for popup
								$scope.showModal = false;
								$scope.buttonClicked = "";
								$scope.popUpFlag = true;
								// end for popup

								var data = {};
								// $scope.status = "NO";
								var URI = BASE_URL + ROOT_URL
										+ GETDELIVERYTYPELIST;
								GenericService
										.serviceAction("GET", URI, data)
										.then(
												function(response) {
													$scope.deliveryTypeList = [];

													if (response.data.status == "success") {
														$scope.deliveryTypeList = response.data.listObject;
														console
																.log($scope.deliveryTypeList);

													}
												});
								
								
						
							}

							$scope.init();
							
							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.saveDeliveryType();
							}

							$scope.saveDeliveryType = function() {
								$scope.$broadcast('show-errors-check-validity');

								var data = {
									deliveryTypeCode : $scope.deliverytype.deliveryCode,
									deliveryTypeName : $scope.deliverytype.deliveryName,
									status : 'A',
									createdBy: $scope.userId,
									updatedBy: $scope.userId,
									organizationId: $scope.organizationId
								}
								if ($scope.deliverytypeform.$valid) {
									if ($scope.popUpFlag) {
										$scope.showModal = !$scope.showModal;

									} else {

								console.log(data);
								
								var URI = BASE_URL + ROOT_URL + ADDDELIVERYTYPE;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {

													if (response.data.status == "success") {
														growl
																.success(
																		'Delivery type added sucessfully!!!.',
																		{
																			title : 'Success!'
																		});
														$scope.$broadcast('show-errors-reset');
														 $scope.init();
													} else {
														growl
																.error(
																		'Delivery type already exits!!!!.',
																		{
																			title : 'Error!'
																		});
													}

												});
									}
								}

							}

							$scope.showUpdateBtn = function(id) {

								$scope.saveBtnFlag = true;
								$scope.updateBtnFlag = false;

								var data = {

									deliveryTypeId : id
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ GETSINGLEDELIVERYTYPE;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													// $scope.speciality =[];
													if (response.data.status == "success") {

														$scope.deliverytype.deliveryTypeId = response.data.listObject[0].deliveryTypeId;
														$scope.deliverytype.deliveryCode = response.data.listObject[0].deliveryTypeCode;
														$scope.deliverytype.deliveryName = response.data.listObject[0].deliveryTypeName;

													} else {
														alert("Error!!");
													}
												});

							};

							$scope.checkVal = function(statusdt) {
								$scope.status = (statusdt == "A") ? "A"
										: "I";
								return (statusdt == "A") ? true
										: false;
							}

							$scope.updateStatus = function(id, type) {

								type = (type == "A") ? "A" : "I";
								
								var data = {
									deliveryTypeId : id,
									status : type,
									updatedBy:$scope.userId
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ UPDATEDELIVERYTYPESTATUS;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {

													if (response.data.status == "success") {
														growl
																.success(
																		'Delivery type status changed sucessfully!!!.',
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

							$scope.updateDeliveryType = function() {
								$scope.$broadcast('show-errors-check-validity');
								var data = {
									deliveryTypeCode : $scope.deliverytype.deliveryCode,
									deliveryTypeName : $scope.deliverytype.deliveryName,
									deliveryTypeId : $scope.deliverytype.deliveryTypeId,
									updatedBy:$scope.userId
								}
								if ($scope.deliverytypeform.$valid) {
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ UPDATEDELIVERYTYPE;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {

													if (response.data.status == "success") {
														growl
																.success(
																		'Delivery type updated sucessfully!!!.',
																		{
																			title : 'Success!'
																		});
														$scope.$broadcast('show-errors-reset');
														 $scope.init();
													} else {
														growl
																.error(
																		'Delivery type already exits!!!!.',
																		{
																			title : 'Error!'
																		});
													}

												});
								}
							}

						} ]);
