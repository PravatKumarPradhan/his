'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:modalityMasterController
 * @description #modalityMasterController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'systemObservationMasterController',
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
						'GenericServiceParamHeader',
						function($scope, $http, $localStorage, $sessionStorage,
								$cookies, $rootScope, GenericService, growl,
								$state, PagerService,GenericServiceParamHeader) {

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
								$scope.saveBtnFlag = true;
								$scope.updateBtnFlag = false;

								// for popup
								$scope.showModal = false;
								$scope.buttonClicked = "";
								$scope.popUpFlag = true;
								// end for common popup
								
								
								$scope.observationObj = 
								{
										systemId:'',
										observationCode : '',
										observationName:'',
										isPropertyRequired:'N',
										organizationId:$scope.organizationId,
										unitId:$scope.unitId,
										updatedBy:$scope.userId,
										createdBy:$scope.userId,
										status:'A',
								};
								
								$scope.oservationPropertyObjList = [{
										"propertyName":""
								}];
								
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
								
								

								var data = {};
								
								 var headerObj = {
							    		 unit_id : $scope.unitId,
										 organization_id : $scope.organizationId
							     };
								
								
								 
							     //For Get Active System List
									/*var URI = BASE_URL + ROOT_URL
										+ GETLISTOFSYSTEMMASTER;
									GenericServiceParamHeader
										.serviceAction(METHOD_GET, URI,
												data,
												NOTIFICATION_MSG_STATUS_FALSE,data,headerObj)
										.then(
												function(response) {
													console.log(response);
													// $rootScope.startLoader();
													if (response.data.status == "success")
														$scope.systemList = response.data.listObject;
												});*/
									
									
									 
								     //For Get All Observation Property List
										var URI = BASE_URL + ROOT_URL
											+ GETLISTOFSYSTEMOBSERVATIONPROPERTY;
										GenericServiceParamHeader
											.serviceAction(METHOD_GET, URI,
													data,
													NOTIFICATION_MSG_STATUS_FALSE,data,headerObj)
											.then(
													function(response) {
														console.log(response);
														// $rootScope.startLoader();
														if (response.data.status == "success")
															$scope.systemObservationList = response.data.listObject;
														console.log("System ObserVationList",$scope.systemObservationList);
													});
									
									
								
								
							}

							$scope.init();
							

							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.saveSystemObservation();
							}
							
							
							$scope.getSystemListByObservationId = function(id)
							{
								//alert(id);
								
								var data = {};
								
								 var headerObj = {
							    		 unit_id : $scope.unitId,
										 organization_id : $scope.organizationId,
										 type_id :id
							     };
								
								
								 
							     //For Get Active System List
									var URI = BASE_URL + ROOT_URL
										+ GETLISTOFSYSTEMMASTERBYTYPE;
									GenericServiceParamHeader
										.serviceAction(METHOD_POST, URI,
												data,
												NOTIFICATION_MSG_STATUS_FALSE,data,headerObj)
										.then(
												function(response) {
													console.log(response);
													// $rootScope.startLoader();
													if (response.data.status == "success")
														$scope.systemList = response.data.listObject;
												});
									
							}
							
							$scope.displayNote = function(propertList,isPropertReq) {
								console.log("Property List",propertList);
								$scope.observationPropertyList = [];
								if(isPropertReq == 'Y'){
									
									angular.forEach(propertList,function(value,key) {
										var dataObjObervationProperty = {};
										dataObjObervationProperty.propertyId = value.propertyId;
										dataObjObervationProperty.observationId = value.observationId;
										dataObjObervationProperty.propertyName = value.propertyName;
										dataObjObervationProperty.status = value.propertyStatus;
										$scope.observationPropertyList.push(dataObjObervationProperty);
										
									});
									
								}
							}
							
							
							 $scope.add = function(index) 
							 {
								 console.log("index add",index);
									var data = {
											propertyName : '',
									};
									var len = $scope.oservationPropertyObjList.length;
									
									$scope.oservationPropertyObjList.push(data);
								};
								
								//for remove complaints
								$scope.remove = function(index) {
									console.log("removeIndex",index);
									var newDataList = [];
									var le = $scope.oservationPropertyObjList.length;
									var i = 1;
									if(le != i){
									angular.forEach($scope.oservationPropertyObjList, function(v) {
										if (index != i) {
											newDataList.push(v);
										}
										i++;
									});
									$scope.oservationPropertyObjList = newDataList;
									}
								};
								
								
								 $scope.addUpdate = function(index) 
								 {
									 console.log("index add",index);
										var data = {
												propertyName : '',
												propertyId:'0'
										};
										var len = $scope.oservationPropertyObjList.length;
										
										$scope.oservationPropertyObjList.push(data);
									};
									
									//for remove complaints
									$scope.removeUpdate = function(index) {
										console.log("removeIndex",index);
										var newDataList = [];
										var le = $scope.oservationPropertyObjList.length;
										var i = 1;
										if(le != i){
										angular.forEach($scope.oservationPropertyObjList, function(v) {
											if (index != i) {
												newDataList.push(v);
											}
											i++;
										});
										$scope.oservationPropertyObjList = newDataList;
										}
									};
							// Function for save Modality Type
							$scope.saveSystemObservation = function() {
								
								$scope.$broadcast('show-errors-check-validity');
								
								if ($scope.systemObservationForm.$valid) {
									
									if ($scope.popUpFlag) {
										$scope.showModal = !$scope.showModal;

									} else {
										
										$scope.currentDate = new Date();
										$scope.currentDate = moment($scope.currentDate)
												.format('DD-MM-YYYY HH:mm:ss');
										
										$scope.observationObj.createdDate  = $scope.currentDate;
										$scope.observationObj.updatedDate  = $scope.currentDate;
										
										if($scope.observationObj.isPropertyRequired == 'Y')
											{
											$scope.observationObj.listSystemPropertyDto = $scope.oservationPropertyObjList;
											}
										else
											{
											$scope.observationObj.listSystemPropertyDto = [];
											}
										
										
										console.log($scope.observationObj);
										//return false;
										var paramObj = {};
										var headers={
												'organization_id': $scope.organizationId,
												'unit_id':$scope.unitId,
												'Content-Type':'application/json'
										};
										//return false;
										var URI = BASE_URL + ROOT_URL
										+ SAVESYSTEMOBSERVATIONPROPERTY;
								
										GenericServiceParamHeader
										.serviceAction(METHOD_POST, URI,
												$scope.observationObj,
												NOTIFICATION_MSG_STATUS_TRUE,paramObj,headers)
										.then(
												function(response) {
													console.log(response);
													// $rootScope.startLoader();
													if (response.data.status == "success")
														$scope.init();
												});
									}
								}

							}

							// Function for get Obervation 
							$scope.showUpdateBtn = function(item,itemPro) {
								
								
								
								console.log("ob",item);
								console.log("Obs Pro",itemPro);
								//return false;
								$scope.saveBtnFlag = false;
								$scope.updateBtnFlag = true;
								
								$scope.observationObj = 
								{
										systemId:item.systemId.toString(),
										observationCode : item.observationCode,
										observationName:item.observationName,
										isPropertyRequired:item.isPropertyRequired,
										organizationId:$scope.organizationId,
										unitId:$scope.unitId,
										updatedBy:$scope.userId,
										createdBy:$scope.userId,
										status:'A',
										observationId : item.observationId
								};
								if(item.isPropertyRequired == 'Y')
									{
									$scope.oservationPropertyObjList =[];
										angular.forEach(itemPro,function(value,key) {
										var dataObjObervationProperty = {};
										dataObjObervationProperty.propertyId = value.propertyId;
										dataObjObervationProperty.propertyName = value.propertyName;
										$scope.oservationPropertyObjList.push(dataObjObervationProperty);
										
										});
								}
								else
									{
										$scope.oservationPropertyObjList = [{
											"propertyName":""
									}];
									}
								
								console.log("final",$scope.observationObj);
								

							};

							// Function for update state status
							$scope.updateStatus = function(id, type) {
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');
								
								var paramObj = {};
								var data = {
									observationId : id,
									status : type,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate,
									unitId:$scope.unitId,
									organizationId:$scope.organizationId
								}
								console.log(data);
								//return false;
								var URI = BASE_URL + ROOT_URL
										+ UPDATESYSTEMOBSERVATIONPROPERTYSTATUS;
								GenericServiceParamHeader
										.serviceAction(METHOD_POST, URI,
												data,
												NOTIFICATION_MSG_STATUS_TRUE,paramObj,paramObj)
										.then(
												function(response) {
													console.log(response);
													// $rootScope.startLoader();
													if (response.data.status == "success")
														$scope.init();
												});
							}
							
							$scope.updateStatusObservationProperty = function(item,type)
							{
								console.log("Proi OBj",item);
								
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');
								
								var paramObj = {};
								
								var data = {
									observationId : item.observationId,
									propertyId : item.propertyId,
									status : type,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate,
									unitId:$scope.unitId,
									organizationId:$scope.organizationId
								}
								console.log(data);
								//return false;
								var URI = BASE_URL + ROOT_URL
										+ UPDATESYSTEMOBSERVATIONSINGLEPROPERTYSTATUS;
								GenericServiceParamHeader
										.serviceAction(METHOD_POST, URI,
												data,
												NOTIFICATION_MSG_STATUS_TRUE,paramObj,paramObj)
										.then(
												function(response) {
													console.log(response);
													// $rootScope.startLoader();
													if (response.data.status == "success")
														$scope.init();
												});
								
							}

							// Function for update 
							$scope.updateSystemObservation = function() {
								
								$scope.$broadcast('show-errors-check-validity');

								if ($scope.systemObservationForm.$valid) {
									
									$scope.currentDate = new Date();
									$scope.currentDate = moment($scope.currentDate)
											.format('DD-MM-YYYY HH:mm:ss');
									
									$scope.modalityObj.updatedDate  = $scope.currentDate;
									console.log($scope.modalityObj);
									 //return false;
									var URI = BASE_URL + ROOT_URL + UPDATEMODALITYMASTER;
									GenericService
											.serviceAction("POST", URI, $scope.modalityObj)
											.then(
													function(response) {

														if (response.data.status == "success") {
															growl
																	.success(
																			'Modality updated sucessfully!!!.',
																			{
																				title : 'Success!'
																			});
															$scope
																	.$broadcast('show-errors-reset');
															$scope.init();
														} else {
															growl
																	.error(
																			'Modality already exits!!!!.',
																			{
																				title : 'Error!'
																			});
														}
														$scope.initStateMasterList(
																		$scope.organizationId,
																		$scope.offset,
																		$scope.noOfRecordsPerPage);

													});
								}

							}
						} ]);
