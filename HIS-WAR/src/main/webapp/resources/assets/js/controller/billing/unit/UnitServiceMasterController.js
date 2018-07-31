/**
 * @author Nikhil
 * @date 21-12-2017 Controller to Mapping Org level Service Master to Unit (Unit
 *       Level)
 */
angular
		.module("myApp")
		.controller(
				"UnitServiceMasterController",
				[
						'$scope',
						'$rootScope',
						'BillingGenericService',
						'GenericService',
						'$sessionStorage',
						'PagerService',
						'promiseFactory',
						'$cookies',
						'$state',
						'promiseFactoryWithObject',
						'growl',
						function($scope, $rootScope, BillingGenericService,
								GenericService, $sessionStorage, PagerService,
								promiseFactory, $cookies, $state,
								promiseFactoryWithObject, growl) {
							var cookieObject = $cookies
									.getObject('cookieObject');
							if (cookieObject == undefined) {
								$state.go('login');
								return;
							}
							$rootScope.loginpage = true;
							$scope.unitId = cookieObject.unitId;
							$scope.organizationId = cookieObject.organizationId;
							$scope.specialityMasterList = [];
							$scope.subSpecialityMasterList = [];
							$scope.unitService = {
								specialityId : "",
								subSpecialityId : "",
								searchServiceName : ""
							};
							$scope.userId = 1;
							$scope.saveBtnFlag = true;

							/** Variables for pagination */
							$scope.offset = 0;
							$scope.noOfRecordsPerPage = 10;
							$scope.unitServiceMasterDetailsList = [];
							$scope.totalNoOfRecords;
							$scope.searchKeyword;
							$scope.commonListCount;
							$scope.pager = {};
							$scope.page;

							$scope.initUnitServiceMaster = function() {
								$scope.unitServiceMaster = {
									"serviceMasterId" : "",
									"isOutsource" : "N",
									"isPackage" : "N",
									"isOutsource" : "N",
									"isPackage" : "N",
									"isRateEditable" : "N",
									"rateEditbleMinValue" : "",
									"rateEditableMaxValue" : "",
									"isTaxApplicable" : "N",
									"isRefDoctorShare" : "N",
									"isDiscount" : "N",
									"isPanel" : "N",
									"serviceStandardCode" : "",
									"isProcedure" : "N",
									"isDocReq" : "N",
									"isBedSide" : "N",
									"isPeriodicity":"N",
									"isPoc" : "N",
									"gstTypeId" : "",
									"isAutoRender":"N",
									"isQuantityEditable":"N",
									"otcGstTypeId" : "",
									"organizationId" : $scope.organizationId,
									"unitId" : $scope.unitId,									
									"updatedBy" : $scope.userId,
									"updatedDate" : new Date().getTime()
									

								}
								var data = {

								}
								var URI = BASE_URL + ROOT_URL
										+ GETALLACTIVETAX + "/" + $scope.unitId;
								$scope.taxList=[];
								GenericService								
										.serviceAction("GET", URI, data)
										.then(
												function(response) {
													if (response.data.status == "success") {
														console.log("Tax Details",response.data.listObject);														
														$scope.taxList=response.data.listObject;
													} else {
														alert("Error!!");
													}
												});
								
							}
							$scope.initUnitServiceMaster();

							// ------------ Get Speciality Master List
							// ------------//
							// $scope.getSpecialityMasterList = function(){
							// try{
							// var URI = BASE_URL + ROOT_URL +
							// GETACTIVESPECIALITY;
							// var obj = {
							// organizationId : $scope.organizationId,
							// unitId :$scope.unitId
							// }
							// BillingGenericService.serviceAction(METHOD_POST,URI,
							// obj,
							// NOTIFICATION_MSG_STATUS_FALSE).then(function(response){
							// console.log(response);
							// if(response.data.status == "success")
							// $scope.specialityMasterList =
							// response.data.listObject;
							// });
							// }catch (e) {
							// console.log("Exception",e.message);
							// }
							// }
							// $scope.getSpecialityMasterList();

							// ------------ Get SubSpeciality By SpecialityId
							// ----------//
							// $scope.getSubSpecialityBySpecialityId =
							// function(specialityId){
							// $rootScope.startLoader();
							// $scope.unitService.subSpecialityId="";
							// $scope.servicesList=[];
							// var URI = BASE_URL + ROOT_URL +
							// GET_SUBSPECIALITY_BY_SPECIALITY_ID;
							// var obj = {
							// organizationId :$scope.organizationId,
							// unitId :$scope.unitId,
							// specialityId :specialityId
							// }
							// BillingGenericService.serviceAction(METHOD_POST,URI,
							// obj,
							// NOTIFICATION_MSG_STATUS_FALSE).then(function(response){
							// if(response.data.status == "success")
							// $scope.subSpecialityMasterList =
							// response.data.listObject;
							// });
							// }
							//	

							// ------------- Search Service Master
							// ---------------//
							// $scope.searchServiceMaster =
							// function(searchKeyword){
							// if($scope.unitService.specialityId == "" ||
							// $scope.unitService.specialityId == undefined){
							// growl.warning("Please select Department",{
							// title : WARNING_MSG
							// });
							// }else if($scope.unitService.subSpecialityId == ""
							// || $scope.unitService.subSpecialityId ==
							// undefined){
							// growl.warning("Please select Sub Department",{
							// title : WARNING_MSG
							// });
							// }
							// else{
							// var serviceMasterList = [];
							// var URI = BASE_URL + ROOT_URL + GLOBAL +
							// UNIT_SERVICE_MAPPER + SEARCH + SLASH +
							// searchKeyword + SPECIALITY + SLASH +
							// $scope.unitService.specialityId + SUB_SPECIALITY
							// + SLASH + $scope.unitService.subSpecialityId +
							// UNIT + SLASH + $scope.unitId + ORG + SLASH +
							// $scope.organizationId;
							// return
							// BillingGenericService.serviceAction(METHOD_GET,URI,"",NOTIFICATION_MSG_STATUS_FALSE).then(function(response){
							// if(response.data.status == "success"){
							// serviceMasterList = response.data.listObject;
							// }
							// return serviceMasterList;
							// });
							// }
							// }

							// ------------- Save Unit Service Master Details
							// ------------//
							$scope.saveUnitServiceMasterDetails = function() {
								try {
									$rootScope.startLoader();
									var URI = BASE_URL + ROOT_URL + API
											+ GLOBAL
											+ UNIT_SERVICE_MASTER_DETAILS;
									BillingGenericService
											.serviceAction(METHOD_POST, URI,
													$scope.unitServiceMaster,
													NOTIFICATION_MSG_STATUS_TRUE)
											.then(
													function(response) {
														if (response.data.status == "success") {
															$scope
																	.initUnitServiceMaster();
															$scope.unitService = {
																specialityId : "",
																subSpecialityId : "",
																searchServiceName : ""
															};
															$scope
																	.initUnitServiceMasterDetailsList(
																			$scope.organizationId,
																			$scope.unitId,
																			$scope.offset,
																			$scope.noOfRecordsPerPage);
														}
													});
								} catch (e) {
									console.log(e.message);
								}
							}

							// ------------- get Unit Service Master Details By
							// Id------------//
							$scope.getUnitServiceMasterDetailsById = function(
									unitServiceMasterDetailsId) {
								try {
									$rootScope.startLoader();
									var URI = BASE_URL + ROOT_URL + API
											+ GLOBAL
											+ UNIT_SERVICE_MASTER_DETAILS
											+ SLASH
											+ unitServiceMasterDetailsId;
									BillingGenericService
											.serviceAction(METHOD_GET, URI, "",
													NOTIFICATION_MSG_STATUS_FALSE)
											.then(
													function(response) {
														if (response.data.status == "success") {
															$scope.saveBtnFlag = false;
															$scope.unitServiceMaster = angular
																	.copy(response.data.object);
															console
																	.log($scope.unitServiceMaster);
															$scope.unitServiceMaster.serviceMasterId=response.data.object.serviceMasterId;
															$scope.unitServiceMaster.serviceStandardCode=response.data.object.serviceStandardCode;
															$scope.unitServiceMaster.serviceStandardName=response.data.object.serviceStandardName;
															$scope.unitServiceMaster.specialityName=response.data.object.specialityName;
															$scope.unitServiceMaster.subSpecialityName=response.data.object.subSpecialityName;
															$scope.unitServiceMaster.isOutsource=response.data.object.isOutsource;
															$scope.unitServiceMaster.isPackage=response.data.object.isPackage;
															$scope.unitServiceMaster.isRateEditable=response.data.object.isRateEditable;
															$scope.unitServiceMaster.rateEditbleMinValue=response.data.object.rateEditbleMinValue;
															$scope.unitServiceMaster.rateEditableMaxValue=response.data.object.rateEditableMaxValue;
															$scope.unitServiceMaster.isTaxApplicable=response.data.object.isTaxApplicable;
															$scope.unitServiceMaster.isRefDoctorShare=response.data.object.isRefDoctorShare;
															$scope.unitServiceMaster.isDiscount=response.data.object.isDiscount;
															$scope.unitServiceMaster.isPanel=response.data.object.isPanel;															
															$scope.unitServiceMaster.isProcedure=response.data.object.isProcedure;
															$scope.unitServiceMaster.isDocReq=response.data.object.isDocReq;
															$scope.unitServiceMaster.isBedSide=response.data.object.isBedSide;
															$scope.unitServiceMaster.isPeriodicity=response.data.object.isPeriodicity;
															$scope.unitServiceMaster.isPoc=response.data.object.isPoc;
															if(response.data.object.gstTypeId!=null){
																$scope.unitServiceMaster.gstTypeId=response.data.object.gstTypeId.toString();
															}															
															if(response.data.object.otcGstTypeId!=null){
																$scope.unitServiceMaster.otcGstTypeId=response.data.object.otcGstTypeId.toString();
															}
															
															$scope.unitServiceMaster.isAutoRender=response.data.object.isAutoRender;
															$scope.unitServiceMaster.isQuantityEditable=response.data.object.isQuantityEditable;
															$scope.unitServiceMaster.discountValue=response.data.object.discountValue;
															$scope.unitServiceMaster.unitServiceMasterDetailsId=response.data.object.unitServiceMasterDetailsId;
															
														}
													});
								} catch (e) {
									console.log(e.message);
								}
							}

							// ------------- Update Unit Service Master Details
							// ------------//
							$scope.updateUnitServiceMasterDetails = function() {
								try {
									$rootScope.startLoader();
									var URI = BASE_URL + ROOT_URL + API
											+ GLOBAL
											+ UNIT_SERVICE_MASTER_DETAILS;
									data={
											serviceMasterId:$scope.unitServiceMaster.serviceMasterId,
											unitServiceMasterDetailsId:$scope.unitServiceMaster.unitServiceMasterDetailsId,
											isOutsource:$scope.unitServiceMaster.isOutsource,
											isPackage:$scope.unitServiceMaster.isPackage,
											isRateEditable:$scope.unitServiceMaster.isRateEditable,
											rateEditbleMinValue:$scope.unitServiceMaster.rateEditbleMinValue,
											rateEditableMaxValue:$scope.unitServiceMaster.rateEditableMaxValue,
											isTaxApplicable:$scope.unitServiceMaster.isTaxApplicable,
											isRefDoctorShare:$scope.unitServiceMaster.isRefDoctorShare,
											isDiscount:$scope.unitServiceMaster.isDiscount,
											discountValue:$scope.unitServiceMaster.discountValue,
											isPanel:$scope.unitServiceMaster.isPanel,															
											isProcedure:$scope.unitServiceMaster.isProcedure,
											isDocReq:$scope.unitServiceMaster.isDocReq,
											isBedSide:$scope.unitServiceMaster.isBedSide,
											isPoc:$scope.unitServiceMaster.isPoc,
											isPeriodicity:$scope.unitServiceMaster.isPeriodicity,
											gstTypeId:$scope.unitServiceMaster.gstTypeId,
											otcGstTypeId:$scope.unitServiceMaster.otcGstTypeId,
											isAutoRender:$scope.unitServiceMaster.isAutoRender,
											isQuantityEditable:$scope.unitServiceMaster.isQuantityEditable,
											organizationId : $scope.organizationId,
											unitId : $scope.unitId,									
											updatedBy : $scope.userId,
											status : "A",
											updatedDate : new Date().getTime()
									};
//									console.log("update data",data); return false;
									BillingGenericService
											.serviceAction("PUT", URI,
													data,
													NOTIFICATION_MSG_STATUS_TRUE)
											.then(
													function(response) {
														if (response.data.status == "success") {
															$scope
																	.initUnitServiceMaster();
//															$scope.unitService = "";
															
														
															
															$scope
																	.initUnitServiceMasterDetailsList(
																			$scope.organizationId,
																			$scope.unitId,
																			$scope.offset,
																			$scope.noOfRecordsPerPage);
															$scope.saveBtnFlag = true;
														}
													});
								} catch (e) {
									console.log(e.message);
								}
							}

							// ======================= Unit Service Master
							// Details List Pagination
							// (Start)=====================//
							$scope.setNoOfRecords = function() {
								$scope.initUnitServiceMasterDetailsList(
										$scope.organizationId, $scope.unitId,
										$scope.offset,
										$scope.noOfRecordsPerPage);
							};

							$scope.initUnitServiceMasterDetailsList = function(
									orgId, unitId, offset, noOfRecordsPerPage) {
								$rootScope.startLoader();
								var obj = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
										: 10;
								var URI_TO_GET_UNIT_SERVICE_MASTER_DETAILS_LIST = BASE_URL
										+ ROOT_URL
										+ API
										+ GLOBAL
										+ UNIT_SERVICE_MASTER_DETAILS
										+ ORG
										+ SLASH
										+ orgId
										+ UNIT
										+ SLASH
										+ unitId
										+ SLASH
										+ offset
										+ SLASH
										+ noOfRecordsPerPage;
								var URI_TO_GET_UNIT_SERVICE_MASTER_DETAILS_COUNT = BASE_URL
										+ ROOT_URL
										+ API
										+ GLOBAL
										+ UNIT_SERVICE_MASTER_DETAILS
										+ COUNT
										+ ORG
										+ SLASH
										+ orgId
										+ UNIT
										+ SLASH
										+ unitId;
								promiseFactory
										.setPromises(
												URI_TO_GET_UNIT_SERVICE_MASTER_DETAILS_LIST,
												URI_TO_GET_UNIT_SERVICE_MASTER_DETAILS_COUNT,
												"GET", "GET")
										.then(
												function(response) {
													$rootScope.stopLoader();
													$scope.unitServiceMasterDetailsList = response[0].listObject;
													console
															.log(
																	"unitServiceMasterDetailsList",
																	$scope.unitServiceMasterDetailsList);
													$scope.commonListCount = response[1].object;
													$scope.setPage(1, false);
												});
							}

							$scope.getUnitServiceMasterDetailsList = function(
									orgId, unitId, offset, noOfRecordsPerPage) {
								$rootScope.startLoader();
								var obj = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
										: 10;
								var URI = BASE_URL + ROOT_URL + API + GLOBAL
										+ UNIT_SERVICE_MASTER_DETAILS + ORG
										+ SLASH + orgId + UNIT + SLASH + unitId
										+ SLASH + offset + SLASH
										+ noOfRecordsPerPage;
								console.log("URI", URI);
								BillingGenericService
										.serviceAction(METHOD_GET, URI, obj,
												NOTIFICATION_MSG_STATUS_FALSE)
										.then(
												function(response) {
													$rootScope.stopLoader();
													$scope.unitServiceMasterDetailsList = response.data.listObject;
												});
							}

							$scope.setPage = function(page, flag) {
								if (page < 1 || page > $scope.pager.totalPages) {
									return;
								}
								$scope.pager = PagerService.GetPager(
										$scope.commonListCount, page,
										$scope.noOfRecordsPerPage);
								if (flag) {
									$scope.getUnitServiceMasterDetailsList(
											$scope.organizationId,
											$scope.unitId,
											$scope.pager.startIndex,
											$scope.pager.pageSize);
								}
							}
							$scope.initUnitServiceMasterDetailsList(
									$scope.organizationId, $scope.unitId,
									$scope.offset, $scope.noOfRecordsPerPage);
							// =================== Service Master Pagination
							// (End)====================//
						} ]);