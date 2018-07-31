'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:modalityMasterController
 * @description #modalityMasterController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'systemMasterController',
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
						'BillingGenericService',
						'GenericServiceParamHeader',
						function($scope, $http, $localStorage, $sessionStorage,
								$cookies, $rootScope, GenericService, growl,
								$state, PagerService,BillingGenericService,GenericServiceParamHeader) {

							/* init() function for form object create. */
							$scope.init = function() {

								$scope.unitId = 1;
								$scope.organizationId = 1;
								$scope.userId = 1;
								if ($scope.userId == null) {
									$rootScope.loginpage = false;
									$state.go('login');
								}

								/** Variables for pagination */
								$scope.offset = 0;
								$scope.noOfRecordsPerPage = 10;
								$scope.totalNoOfRecords;
								$scope.searchKeyword;
								$scope.commonListCount;

								$rootScope.loginpage = true;
								
								$scope.saveBtnFlag = true;
								$scope.updateBtnFlag = false;

								// for popup
								$scope.showModal = false;
								$scope.buttonClicked = "";
								$scope.popUpFlag = true;
								// end for common popup
								
								
								$scope.systemObj = 
								{
										
										systemCode : '',
										systemName:'',
										genderId:'',									
										updatedBy:$scope.userId,
										createdBy:$scope.userId,
										status:'A',
										typeId:''
								};
								
								

								var data = {};
								var commonObj = {
									unitId : $scope.unitId,
									organizationId : $scope.organizationId
								}

								var URI = BASE_URL + ROOT_URL
								+ GETACTIVEGENDERLIST;
					
						BillingGenericService
								.serviceAction(METHOD_GET, URI,
										commonObj,
										NOTIFICATION_MSG_STATUS_FALSE)
								.then(
										function(response) {
											console.log(response); 
											// $rootScope.startLoader();
											if (response.data.status == "success")
												$scope.genderList = response.data.listObject;
											console.log("GEN",$scope.genderList);
										});
//						
						
						 var headerObj = {
					    		 unit_id : $scope.unitId,
								 organization_id : $scope.organizationId
					     };
					     
					     var paramObj = { };
					     
//					     console.log(headerObj);
					     
					     //For Get Active Complaints List
							var URI = BASE_URL + ROOT_URL
								+ GETLISTOFSYSTEMMASTER;
							GenericServiceParamHeader
								.serviceAction(METHOD_GET, URI,
										data,
										NOTIFICATION_MSG_STATUS_FALSE,paramObj,headerObj)
								.then(
										function(response) {
											console.log(response);
											// $rootScope.startLoader();
											if (response.data.status == "success")
												
												$scope.systemList = response.data.listObject;
										});						
							
								
							}
							
							$scope.examinationList = [
							       {
							           typeId: 1,
							           typeName: 'General Examination'
							          
							       },
							       {
							    	   typeId: 2,
							    	   typeName: 'Physical Examination'
							       },
							       {
							    	   typeId: 3,
							    	   typeName: 'Both'
							       }
							 ];							
						

							$scope.init();
							
						

							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.saveSystem();
							}
							
							// Function for save System Type
							$scope.saveSystem = function() {
								
								
								 var headerObj = {
							    		 "unit_id" : $scope.unitId,
										 "organization_id" : $scope.organizationId,
										 'Content-Type':'application/json'
							     };							     
							     var paramObj = { };
								
								$scope.$broadcast('show-errors-check-validity');
								
								if ($scope.systemMasterForm.$valid) {
									
									if ($scope.popUpFlag) {
										$scope.showModal = !$scope.showModal;

									} else {
										
										$scope.currentDate = new Date();
										$scope.currentDate = moment($scope.currentDate)
												.format('DD-MM-YYYY HH:mm:ss');
										
										$scope.systemObj.createdDate  = $scope.currentDate;
										$scope.systemObj.updatedDate  = $scope.currentDate;
										console.log($scope.systemObj);
										
//										return false;
										var URI = BASE_URL + ROOT_URL + SAVESYSTEMMASTER;
										
										GenericServiceParamHeader
										.serviceAction(METHOD_POST, URI,
												$scope.systemObj,
												NOTIFICATION_MSG_STATUS_FALSE,paramObj,headerObj)
										.then(
												function(response) {
													console.log(response);
													// $rootScope.startLoader();
													if (response.data.status == "success")
														{
														growl.success("Added Successfully",{
																title : 'Success!'
																	
														});
														$scope.init();
														
														}												
												});											
									}
								}

							}

							// Function for get single system Master
							$scope.showUpdateBtn = function(id) {

								$scope.saveBtnFlag = false;
								$scope.updateBtnFlag = true;

								var data = {

									systemId : id,
									unitId:$scope.unitId,
									organizationId:$scope.organizationId
								}
								console.log(data);
//								return false;
								var URI = BASE_URL + ROOT_URL + GETLISTOFSYSTEMMASTERBYID;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {												
													if (response.data.status == "success") {
														$scope.systemObj.systemId = response.data.listObject[0].systemId;
														$scope.systemObj.systemName = response.data.listObject[0].systemName;
														$scope.systemObj.systemCode = response.data.listObject[0].systemCode;
														$scope.systemObj.systemCode = response.data.listObject[0].systemCode;
														$scope.systemObj.genderId = response.data.listObject[0].genderId.toString();
														$scope.systemObj.desc = response.data.listObject[0].genderName;
														$scope.systemObj.typeId = response.data.listObject[0].typeId.toString();
														
													} else {
														alert("Error!!");
													}
												});

							};

							// Function for update system status
							$scope.updateStatus = function(id, type) {
								
								var headerObj = { };							     
							     var paramObj = { };
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								type = (type == "A") ? "A" : "I";
								var data = {
									systemId : id,
									status : type,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate,
									unitId:$scope.unitId,
									organizationId:$scope.organizationId
								}
//								console.log(data);
//								return false;
								var URI = BASE_URL + ROOT_URL + UPDATESYSTEMMASTERSTATUS;
								GenericServiceParamHeader
								.serviceAction(METHOD_POST, URI,
										data,NOTIFICATION_MSG_STATUS_FALSE,paramObj,headerObj)
								.then(
										function(response) {
											console.log(response);
											// $rootScope.startLoader();
											if (response.data.status == "success")
												{
												growl.success("updated Successfully",{
														title : 'Success!'
															
												});
												$scope.init();
												}												
										});	
							}

							// Function for update system
							$scope.updateSystem = function() {
								
								$scope.$broadcast('show-errors-check-validity');

								if ($scope.systemMasterForm.$valid) {
								
								 var headerObj = {
							    		 "unit_id" : $scope.unitId,
										 "organization_id" : $scope.organizationId,
										 'Content-Type':'application/json'
							     };							     
							     var paramObj = { };
									
									$scope.currentDate = new Date();
									$scope.currentDate = moment($scope.currentDate)
											.format('DD-MM-YYYY HH:mm:ss');
									
									$scope.systemObj.updatedDate  = $scope.currentDate;
									console.log($scope.systemObj);
//									 return false;
									var URI = BASE_URL + ROOT_URL + UPDATESYSTEMMASTER;
									GenericServiceParamHeader
									.serviceAction(METHOD_POST, URI,
											$scope.systemObj,
											NOTIFICATION_MSG_STATUS_FALSE,paramObj,headerObj)
									.then(
											function(response) {
												console.log(response);
												// $rootScope.startLoader();
												if (response.data.status == "success")
													{
													growl.success("Updatd successfully",{
															title : 'Success!'
																
													});
													$scope.init();
													$scope.clearData();
													}												
											});
								}
							}
						} ]);
