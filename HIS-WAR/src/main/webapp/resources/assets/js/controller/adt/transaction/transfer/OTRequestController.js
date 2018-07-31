'use strict';

/**
 * @Author By Vivek Satle
 * @name myApp.controller:OTRequestController
 * @description #OTRequestController Controller of the myApp
 */

angular
		.module('myApp')
		.controller(
				'OTRequestController',
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

								/*
								 * $scope.unitId =
								 * sessionStorage.getItem("unitId");
								 * $scope.organizationId =
								 * sessionStorage.getItem("organizationId");
								 * $scope.userId =
								 * sessionStorage.getItem("userId");
								 */
								var cookieObject = $cookies.getObject('cookieObject');
								if(cookieObject == undefined){
									$state.go('login');
									return;
								}
								$scope.unitId = cookieObject.unitId;
								$scope.organizationId = cookieObject.organizationId;
								$scope.userId = 1;


								$rootScope.loginpage = true;

								$scope.patientOTRequest = {}
								$scope.itemSelecteds = {};

								// for popup
								$scope.showModal = false;
								$scope.buttonClicked = "";
								$scope.popUpFlag = true;
								// end for popup

								
								var data = {
									unitId : $scope.unitId,
									organizationId : $scope.organizationId,
								};
								
								var URI = BASE_URL + ROOT_URL
								+ GETALLOTREQUESTLIST;
						GenericService
								.serviceAction("POST", URI, data)
								.then(
										function(response) {

											if (response.data.status == "success") {
												$scope.OTRequestList = [];
												$scope.OTRequestList = response.data.listObject;
											}
										});
						

							}

							$scope.init();
							
							

							$scope.searchGroupList = function(keyword) {

								var data = {
									patientName : keyword,
									unitId : $scope.unitId,
									// visitType :
									// $scope.selectedPageNameForAdmissionNote,
									organizationId : $scope.organizationId
								}
								console.log(data);

								var URI = BASE_URL + ROOT_URL
										+ GETADMITTEDPATIENTBYKEWWORD;
								return GenericService.serviceAction("POST",
										URI, data).then(function(response) {

									if (response.data.status == "success") {
										// alert("ff");
										// console.log(response.data.listObject);
										return response.data.listObject;

									}
								});
								// return data="[{name:'test'}]";
							}

							$scope.selectPatientId = function(info, model,
									label) {
								
								var data = {
									admissionId : info.admissionId,
									organizationId : $scope.organizationId,
									unitId : $scope.unitId
								};
								 console.log(data);
								 
								 var URI = BASE_URL + ROOT_URL
									+ GETOTREQUESTLISTBYADMISSIONID;
							GenericService
									.serviceAction("POST", URI, data)
									.then(
											function(response) {
												if (response.data.status == "success") {
													$scope.OTRequestList = [];
													$scope.OTRequestList = response.data.listObject;
												}
											});
							}

							$scope.selectItem = function(item) {

								$scope.itemSelecteds = [];
								// If checkbox is checked
								if ($scope.patientOTRequest) {
									$scope.itemSelecteds = item;
								}
								console.log($scope.itemSelecteds);
							}

							$scope.initiateOTRequest = function(item) {

								if (Object.keys($scope.itemSelecteds).length === 0) {
									growl
											.error(
													'Please select atleast one patient!!!.',
													{
														title : 'Error!'
													});
								} else {
									
									if(item.patientId > 0)
									{
									 var age = $rootScope.getAge(item.dob);
									
									 $scope.patientFullName =item.pFirstName+' '+age+'/'+item.genderCode; 
									}
								else
									{
									 $scope.patientFullName =item.pFirstName+' '+item.dob+'/'+item.genderCode;
									}
									
									
									var bedManagmentCookiesData = {
											admissionId:item.admissionId,
											patientFullName :$scope.patientFullName,
											uhidNum:item.uhidnumber,
											bedNumber : item.bedNumber,
											doctorFullName : item.dFirstName,
											wardName : item.wardName
											};

									$cookies.put('bedManagmentCookies',JSON.stringify(bedManagmentCookiesData));
									$state.go('transferToOT.OTTransferRequest');
								}
							}

						} ]);

