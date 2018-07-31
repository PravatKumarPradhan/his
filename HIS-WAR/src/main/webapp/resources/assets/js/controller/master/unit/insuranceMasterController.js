'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:insuranceMasterController
 * @description #insuranceMasterController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'insuranceMasterController',
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
								
								 $scope.unitId = sessionStorage.getItem("unitId");
								   $scope.organizationId = sessionStorage.getItem("organizationId");
								   $scope.userId = sessionStorage.getItem("userId");
								   if($scope.userId == null)
									   {
									   $rootScope.loginpage = false;
									   	$state.go('login');
									   }
								   
								$rootScope.loginpage = true;
								$scope.admissiontype = {}
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
										+ GETADMISSIONTYPELIST;
								GenericService
										.serviceAction("GET", URI, data)
										.then(
												function(response) {
													$scope.admissionTypeList = [];

													if (response.data.status == "success") {
														$scope.admissionTypeList = response.data.listObject;
														console
																.log($scope.admissionTypeList);

													}
												});
							}

							$scope.init();
							
							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.saveAdmissionType();
							}

							$scope.saveAdmissionType = function() {
								$scope.$broadcast('show-errors-check-validity');

								var data = {
									admissionTypeCode : $scope.admissiontype.admissionTypeCode,
									admissionTypeDesc : $scope.admissiontype.admissionTypeName,
									status : 'A',
									createdBy:'1',
									updatedBy:'1'
								}
								if ($scope.admissiontypeform.$valid) {
									if ($scope.popUpFlag) {
										$scope.showModal = !$scope.showModal;

									} else {

								console.log(data);
								
								var URI = BASE_URL + ROOT_URL + ADDADMISSIONTYPE;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {

													if (response.data.status == "success") {
														growl
																.success(
																		'Admission Type added sucessfully!!!.',
																		{
																			title : 'Success!'
																		});
														$scope.$broadcast('show-errors-reset');
														 $scope.init();
													} else {
														growl
																.error(
																		'Admission Type already exits!!!!.',
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

										admissionTypeId : id
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ GETSINGLEADMISSIONTYPE;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													// $scope.speciality =[];
													if (response.data.status == "success") {

														$scope.admissiontype.admissionTypeId = response.data.listObject[0].admissionTypeId;
														$scope.admissiontype.admissionTypeCode = response.data.listObject[0].admissionTypeCode;
														$scope.admissiontype.admissionTypeName = response.data.listObject[0].admissionTypeDesc;

													} else {
														alert("Error!!");
													}
												});

							};

							$scope.checkVal = function(statusAt) {
								$scope.status = (statusAt == "A") ? "A"
										: "I";
								return (statusAt == "A") ? true
										: false;
							}

							$scope.updateStatus = function(id, type) {

								type = (type == "A") ? "A" : "I";
								
								var data = {
									admissionTypeId : id,
									status : type,
									updatedBy:'1'
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ UPDATEADMISSIONTYPESTATUS;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {

													if (response.data.status == "success") {
														growl
																.success(
																		'Admission Type status changed sucessfully!!!.',
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

							$scope.updateAdmissionType = function() {
								$scope.$broadcast('show-errors-check-validity');
								var data = {
										admissionTypeCode : $scope.admissiontype.admissionTypeCode,
										admissionTypeDesc : $scope.admissiontype.admissionTypeName,
										admissionTypeId : $scope.admissiontype.admissionTypeId,
										updatedBy:'1'
								}
								if ($scope.admissiontypeform.$valid) {
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ UPDATEADMISSIONTYPE;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {

													if (response.data.status == "success") {
														growl
																.success(
																		'Admission Type updated sucessfully!!!.',
																		{
																			title : 'Success!'
																		});
														$scope.$broadcast('show-errors-reset');
														 $scope.init();
													} else {
														growl
																.error(
																		'Admission Type already exits!!!!.',
																		{
																			title : 'Error!'
																		});
													}

												});
								}
							}

						} ]);
