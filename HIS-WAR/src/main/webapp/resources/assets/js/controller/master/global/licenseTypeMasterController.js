'use strict';

/**
 * @Author By Dinesh Jagatap
 * @name licenseTypeMasterController
 * @description #licenseTypeMasterController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'licenseTypeMasterController',
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

								$rootScope.loginpage = true;
								$scope.country = {}
								$scope.saveBtnFlag = true;
								$scope.updateBtnFlag = false;

								// for popup
								$scope.showModal = false;
								$scope.buttonClicked = "";
								$scope.popUpFlag = true;
								// end for popup
								$scope.licenceTypeId = 0;

								$scope.licenceTypeMasterObj = {
									licenceType : "",
									liceenceTypeCode : "",
									organizationId : $scope.organizationId,
									createdDate : moment(new Date()).format(
											'DD-MM-YYYY HH:mm:ss'),
									updatedDate : moment(new Date()).format(
											'DD-MM-YYYY HH:mm:ss'),
									status : 'A',
									createdBy : $scope.userId,
									updatedBy : $scope.userId

								};

								var data = {};

								var URI = BASE_URL + ROOT_URL + GETLICENECETYPEMASTERLIST +"/"+ $scope.organizationId;

								// For Get licence type List Using Offset
								GenericService
										.serviceAction("GET", URI, data)
										.then(
												function(response) {
													$scope.licenceList = [];
													if (response.data.status == "success") {
														$scope.licenceList = response.data.listObject;
														console.log("licenceList",$scope.licenceList);
													}
												});
							}

							$scope.init();

							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.saveLicenece();
							}

							// Function For  savelicenece
							$scope.saveLicenece = function() {

								// console.log($scope.dietTypeMasterObj); return
								// false;

								$scope.$broadcast('show-errors-check-validity');
								// return false;

								if ($scope.liceneceTypeMasterform.$valid) {

									if ($scope.popUpFlag) {
										$scope.showModal = !$scope.showModal;

									} else {
										 console.log("Save Obj",$scope.licenceTypeMasterObj); 

										var URI = BASE_URL + ROOT_URL
												+ SAVELICENECETYPEMASTER;										
										GenericService
												.serviceAction("POST",URI,$scope.licenceTypeMasterObj)
												.then(
														function(response) {
															 if (response.data.status == "success") {
																	$scope.init();
													               growl.success(
													                   response.data.message,
													                   {
													                    title : 'Success!'
													                   });
															 }else{
																 growl.error(
												                   response.data.message,
												                   {
												                    title : 'Error!'
												                   });
															 }
														});
									}
								}

							}

							// Function For get single licence
							$scope.showUpdateBtn = function(id) {

								$scope.saveBtnFlag = false;
								$scope.updateBtnFlag = true;

								var data = {

								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ GETLICENECETYPEMASTERBYID+"/"+id+"/"+$scope.organizationId;
								GenericService
										.serviceAction("GET", URI, data)
										.then(
												function(response) {
													if (response.data.status == "success") {
														console.log("GET_LICENECE_TYPE_MASTER_BY_ID",response.data.listObject);
//														 return false;
														$scope.licenceTypeMasterObj.liceenceTypeCode = response.data.listObject[0].liceenceTypeCode;
														$scope.licenceTypeMasterObj.licenceType = response.data.listObject[0].licenceType;
														$scope.licenceTypeId = response.data.listObject[0].licenceTypeId;

													} else {
														alert("Error!!");
													}
												});

							};

							// Function For update licence status
							$scope.updateStatus = function(id, type) {

								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								var data = {
									licenceTypeId : id,
									status : type,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ UPDATELICENECETYPEMASTERSTATUS;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													 if (response.data.status == "success") {
															$scope.init();
											               growl.success(
											                   response.data.message,
											                   {
											                    title : 'Success!'
											                   });
													 }else{
														 growl.error(
										                   response.data.message,
										                   {
										                    title : 'Error!'
										                   });
													 }
												});
							}

							// Function For update licence
							$scope.updateLicenece = function() {

								$scope.$broadcast('show-errors-check-validity');

								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								$scope.licenceTypeMasterObj.licenceTypeId = $scope.licenceTypeId;
								$scope.licenceTypeMasterObj.updatedDate = $scope.currentDate;
								$scope.licenceTypeMasterObj.updatedBy = $scope.userId;
								console.log($scope.licenceTypeMasterObj);
//								 return false;
								if ($scope.liceneceTypeMasterform.$valid) {
									var URI = BASE_URL + ROOT_URL
											+ UPDATELICENECETYPEMASTER;
									GenericService
											.serviceAction("POST", URI,$scope.licenceTypeMasterObj)
											.then(function(response) {
														if (response.data.status == "success") {
															$scope.init();
											               growl.success(
											                   response.data.message,
											                   {
											                    title : 'Success!'
											                   });
													 }else{
														 growl.error(
										                   response.data.message,
										                   {
										                    title : 'Error!'
										                   });
													 }
														
													});
								}
							}

							}
						]);
