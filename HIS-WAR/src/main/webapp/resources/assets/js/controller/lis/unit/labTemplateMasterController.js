
/**
 * @author Ganesh
 */

angular
		.module('myApp')
		.controller(
				"labTemplateMasterController",
				[
						'$scope',
						'$rootScope',
						'$state',
						'$cookies',
						'GenericService',
						'PagerService',
						'promiseFactory',
						'$sessionStorage',
						'growl',
						'$stateParams',
						function($scope, $rootScope, $state, $cookies,
								GenericService, PagerService, promiseFactory,
								$sessionStorage, growl,$stateParams) {

							$rootScope.loginpage = true;
							$scope.LISDynamicLabel = "Lab Template Master";

							/** Variables for pagination */
							$scope.offset = 0;
							$scope.noOfRecordsPerPage = 10;
							$scope.labTemplateMasterDtoList = [];
							$scope.specialtyTemplateMapperDtoList  = [];
							$scope.totalNoOfRecords;
							$scope.searchKeyword;
							$scope.commonListCount;
							
							$scope.selectedDoctorList = [];
							$scope.saveBtnFlag = true;
							$scope.updateBtnFlag = false;

							$scope.orgId = 1;
							$scope.orgUnitId = 1;
							$scope.createdBy = 1;
							$scope.updatedBy = 1;
							$scope.deptId = LAB_DEPT ;
							$scope.subDeptId = HISTOPATHOLOGY_DEPT;
							
						   $scope.labTemplateMasterDto ={
                            		  "labTemplateId" : "",
                            		  "orgId" : $scope.orgId,
                            		  "orgUnitId" : $scope.orgUnitId,
                            		  "templateCode":$scope.templateCode,
                            		  "templateDesc" : $scope.templateDesc,
                            		  "pathologistId" :$scope.pathologistId,
                            		  "genderId" :$scope.genderId,
                            		  "templatePath" : "",
                            		  "status" : "A",
                            		  "createdBy" : $scope.createdBy,
                            		  "createdDate" : "",
                            		  "updatedBy" : $scope.updatedBy,
                            		  "updatedDate" : "",
                            		  "isDeleted" : "N",
                            		  "templateData" : "",
                            		  "htmlData" : "",
                            		  "listSpecialtyTemplateMapperDto":[]
                            		  
                            		}
						   
						   
						   $scope.SpecialtyTemplateMapper ={
                         		  "specialtyTempId" : "",
                         		  "orgId" : $scope.orgId,
                         		  "orgUnitId" : $scope.orgUnitId,
                         		  "doctorId":$scope.doctorId,
                         		  "labTemplateId" : $scope.labTemplateId,
                         		  "templateTypeId" :$scope.templateTypeId,
                         		  "status" : "A",
                         		  "createdBy" : $scope.createdBy,
                         		  "createdDate" : "",
                         		  "updatedBy" : $scope.updatedBy,
                         		  "updatedDate" : "",
                         		  "isDeleted" : "N",
                         		  "specialityId" : ""
                         			  
						   }
						   
						   
						   //$scope.selectedItems = [];
						    $scope.dropDownSetting = {
						    		scrollableHeight: '100px', 
						    		scrollable: true
						    };  
						   
							$scope.getGenderList = function(orgId) {
								try {
									var data = "";
									var URI = BASE_URL + ROOT_URL
											+ LIS_COMMON
											+ GET_GENDER_LIST + S + orgId;
									console.log("URI", URI);
									GenericService
											.serviceAction("GET", URI, data)
											.then(
													function(response) {
														$scope.genderList = response.data.listObject;
													});
								} catch (e) {
									console.log(e.message)
								}
							}
							$scope.getGenderList($scope.orgId);
							// ====================================================CODE START FOR LAB TEMPLATE MASTER LIST===========================================================

							$scope.setNoOfRecords = function() {
								$scope.initLabTemplateMasterList($scope.orgId, $scope.offset, $scope.noOfRecordsPerPage);
							};
							$scope.initLabTemplateMasterList = function(orgId, offset, noOfRecordsPerPage)
							{
								var data = "";
								$rootScope.startLoader();
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage : 10;
								var URI1 = BASE_URL + ROOT_URL + LIS_UNIT + LIST_LAB_TEMPLATE_MASTER + S + $scope.orgId + S + $scope.orgUnitId + S + offset
										+ S + noOfRecordsPerPage;
								var URI2 = BASE_URL + ROOT_URL + LIS_UNIT + COUNT_LAB_TEMPLATE_MASTER + S + $scope.orgId + S + $scope.orgUnitId;
								promiseFactory.setPromises(URI1, URI2, "GET", "GET").then(function(response) 
								{
									$rootScope.stopLoader();
									$scope.commonList = response[0];
									$scope.commonListCount = response[1].object;
									$scope.setPage(1, false);
									console.log($scope.commonList);
								});
							}
							
							
						

							$scope.getLabTemplateMasterList = function(orgId, offset, noOfRecordsPerPage) 
							{
								console.log("offset", offset);
								var data = "";
								$rootScope.startLoader();
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage : 10;
								var URI = BASE_URL + ROOT_URL + LIS_UNIT + LIST_LAB_TEMPLATE_MASTER + S + $scope.orgId + S + $scope.orgUnitId + S + offset
										+ S + noOfRecordsPerPage;
								console.log("URI", URI);
								GenericService.serviceAction("GET", URI, data).then(function(response) {
									$rootScope.stopLoader();
									$scope.commonList = response.data;
									console.log("data"+JSON.stringify($scope.commonList));
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
									$scope.getLabTemplateMasterList($scope.orgId, $scope.pager.startIndex, $scope.pager.pageSize);
								}
							}

						    $scope.initLabTemplateMasterList($scope.orgId, $scope.offset, $scope.noOfRecordsPerPage);
							
							//$scope.getLabTemplateMasterList($scope.orgId, $scope.offset, $scope.noOfRecordsPerPage);

							// ====================================================CODE END FOR LAB TEMPLATE MASTER LIST===========================================================
							
							
							

							
						    $scope.value= "";
						    $scope.flag = false;
							
				            
				           /* $rootScope.firstName="Ramesh";*/
							$scope.setJsonData = function()
							{
								
								$scope.renderContainer = document.querySelector('.render-form');
								$scope.formeo.render($scope.renderContainer);
								$scope.value = document.getElementById("myHtml").innerHTML;
								 /* $scope.jsonArray = $scope.formData.actions.getData('json', true);
								  $scope.jsonData =JSON.parse($scope.jsonArray);
								  var formData = JSON.stringify($scope.jsonData);
								  var $markup = $('<div/>');
						          $markup.formRender({formData});
						          $scope.value = $scope.addLineBreaks($markup[0].innerHTML);*/
							}
							
							
							$scope.save = function() 
							{
								
								if(!$scope.flag)
									{
									 $('#templatePopup').modal('show');
									}
							}
							
							$scope.updateTemplate = function() 
							{
								
								if(!$scope.flag)
									{
									  $('#templatePopup').modal('show');
									}
							}
							
							
							$scope.saveWithoutEditingTemplate = function()
							{
								$scope.saveTemplate();
							}
							

							$scope.saveEditedTemplate = function ()
							{
								$scope.flag = false;
								$scope.setJsonData();
							}
							
							$scope.updateWithoutEditingTemplate = function()
							{
								$scope.update();
							}
							

							$scope.updateEditedTemplate = function ()
							{
								$scope.flag = false;
								$scope.setJsonData();
							}
							
							
							
							var commonObj = {
									unitId : $scope.orgUnitId ,
									organizationId :$scope.orgId
									
								}

								var URI = BASE_URL + ROOT_URL
										+ GETACTIVESPECIALITY;
							
							GenericService
										.serviceAction(METHOD_POST, URI,
												commonObj,
												NOTIFICATION_MSG_STATUS_FALSE)
										.then(
												function(response) {
													console.log(response);
													// $rootScope.startLoader();
													if (response.data.status == "success")
														$scope.specialityList = response.data.listObject;
												});
							
							$scope.specialityId;
							$scope.getDoctorBySpeciality = function(id) {

								var data = {
									specialityId : id,
									unitId : $scope.orgUnitId ,
									organizationId :$scope.orgId
								};
								var URI = BASE_URL + ROOT_URL
										+ GETDOCTORBYSPECIALITYID;
								console.log("URI",JSON.stringify(URI));
								return GenericService
										.serviceAction(METHOD_POST, URI, data)
										.then(
												function(response) 
												{
													console.log(response);
													$scope.doctorList = [];
													// $rootScope.startLoader();
													if (response.data.status == "success")
														$scope.doctorList =  [];
													var data={
															"id":"",
															"label":""
													}
															angular
															.forEach(
																	response.data.listObject,
																	function(doctorObj) {
																		  var doctObj = angular.copy(data);
																		  doctObj.id=doctorObj.doctorId;
																		  doctObj.label=doctorObj.firstName+""+doctorObj.lastName+"";
																		  $scope.doctorList.push(doctObj);
																			console.log("$scope.doctorList",JSON.stringify($scope.doctorList));
																	});
															
													return $scope.doctorList;		
															
												});

							}
							
							
							$scope.getTemplateTypeList = function() {
								try {  
									var data = "";
									var URI = BASE_URL + ROOT_URL
									  + LIS_UNIT
									  + LIST_LAB_TEMPLATE_TYPES +S + $scope.orgId + S + $scope.orgUnitId;
									console.log("URI", URI);
									GenericService
											.serviceAction("GET", URI, data)
											.then(
													function(response) {
														$scope.getTemplateTypeList = response.data.listObject;
													});
								} catch (e) {
									console.log(e.message)
								}
							}
							
							$scope.getTemplateTypeList();
							
							
							
							$scope.getCheckTemplateTypeExistsOrNot = function() {
								try {  
									var data = "";
									var URI = BASE_URL + ROOT_URL
									  + LIS_UNIT
									  + GET_LAB_TEMPLATES_EXISTS_OR_NOT +S + $scope.orgId + S + $scope.orgUnitId + S + $scope.templateTypeId;
									console.log("URI", URI);
									return GenericService
											.serviceAction("GET", URI, data)
											.then(
													function(response) {
														if (response.data.status =='success')
														{
															return response.data.object;
															
														} else if(response.data.status =='error')
														{
															return response.data.object;
														}
													});
								} catch (e) {
									console.log(e.message)
								}
							}
							
							
							$scope.saveTemplate = function ()
							{
	
						        $scope.labTemplateMasterDto.templateData  = $scope.formeo.formData;
								$scope.labTemplateMasterDto.htmlData= $scope.value;
								$scope.getTemplateMapperList();
								
								var checkTemplateTypes=[1,2,3,4,6,7,9,10,11];
								var found = checkTemplateTypes.find(function(element) {
									  return element == $scope.templateTypeId;
									});
								if(found!=undefined){
									var promise = $scope.getCheckTemplateTypeExistsOrNot();
									promise.then(function(isTemplateObj)
											{
										
										if(isTemplateObj==0)
											{
											$scope.$broadcast('show-errors-check-validity');
											if ($scope.labTemplateMasterform.$valid)
											{
												try {
													$rootScope.startLoader();
													$scope.initLabTemplateMasterList();
													console.log("labTemplateMasterDto",JSON.stringify($scope.labTemplateMasterDto));
													var URI = BASE_URL + ROOT_URL + LIS_UNIT+ADD_LAB_TEMPLATE_MASTER;
													GenericService.serviceAction("POST", URI, $scope.labTemplateMasterDto).then(function(response) 
													{	
														if (response.data ==true)
														{
															$rootScope.stopLoader();
															growl.success("Template Saved Successfully.",
																	{
																		title : 'Success!'
																	});
															$scope.$broadcast('show-errors-reset');
															 $scope.labTemplateMasterDto={};
															 $scope.cleareTemplate();
														    $scope.initLabTemplateMasterList($scope.orgId, $scope.offset, $scope.noOfRecordsPerPage);
															
														} else if(response.data !=true)
														{
															$rootScope.stopLoader();
															growl.error('Failed To Save Template',
																	{
																		title : 'Error!'
																	});
														}
													});
												} catch (e) {
													console.log("Exception",e.message);
												}
												
											}
											}
										else{
											growl.error('Template is already added..',
													{
														title : 'Error!'
													});
										}
										
										
								    }, function(error) {
								      console.error(error)
								    })
									
								}else{
									try {
										$rootScope.startLoader();
										$scope.initLabTemplateMasterList();
										console.log("labTemplateMasterDto",JSON.stringify($scope.labTemplateMasterDto));
										var URI = BASE_URL + ROOT_URL + LIS_UNIT+ADD_LAB_TEMPLATE_MASTER;
										GenericService.serviceAction("POST", URI, $scope.labTemplateMasterDto).then(function(response) 
										{	
											if (response.data ==true)
											{
												$rootScope.stopLoader();
												growl.success("Template Saved Successfully.",
														{
															title : 'Success!'
														});
												$scope.$broadcast('show-errors-reset');
												 $scope.labTemplateMasterDto={};
												 $scope.cleareTemplate();
											    $scope.initLabTemplateMasterList($scope.orgId, $scope.offset, $scope.noOfRecordsPerPage);
												
											} else if(response.data !=true)
											{
												$rootScope.stopLoader();
												growl.error('Failed To Save Template',
														{
															title : 'Error!'
														});
											}
										});
									} catch (e) {
										console.log("Exception",e.message);
									}
								}
								
								
							

							
								
							}
							
							$scope.getTemplateMapperList=function()
							{
								$scope.specialtyTemplateMapperDtoList=[];
								angular
								.forEach(
										$scope.selectedDoctorList,
										function(value) {
											
											var specialtyTemplateMapperDtls = angular
											.copy($scope.SpecialtyTemplateMapper);
											
											//specialtyTemplateMapperDtls.specialtyTempId= $scope.specialtyTempId;
											specialtyTemplateMapperDtls.orgId=  $scope.orgId;
											specialtyTemplateMapperDtls.orgUnitId= $scope.orgUnitId;
											specialtyTemplateMapperDtls.doctorId= value.id;
											specialtyTemplateMapperDtls.labTemplateId= $scope.labTemplateId;
											specialtyTemplateMapperDtls.templateTypeId= $scope.templateTypeId;	
											specialtyTemplateMapperDtls.status="A";
											specialtyTemplateMapperDtls.createdBy= $scope.createdBy;
											specialtyTemplateMapperDtls.createdDate= $scope.createdDate;
											specialtyTemplateMapperDtls.updatedBy= $scope.updatedBy;
											specialtyTemplateMapperDtls.updatedDate= $scope.updatedDate;
											specialtyTemplateMapperDtls.isDeleted= "N";
											specialtyTemplateMapperDtls.specialityId=$scope.specialityId;
											
											 $scope.specialtyTemplateMapperDtoList
						    					.push(specialtyTemplateMapperDtls);
										});
							 
								$scope.labTemplateMasterDto.listSpecialtyTemplateMapperDto= $scope.specialtyTemplateMapperDtoList;
							}
							
							
							
							$scope.showViewTemplate = function(templatelist)
							{
								CKEDITOR.config.readOnly = true;
								$scope.htmlData=templatelist.htmlData;
							}
							
						
						
							
							$scope.showUpdateBtn = function(labTemplateId)
							{
								try {
									$rootScope.startLoader();
									$scope.saveBtnFlag = false;
									$scope.updateBtnFlag = true;
									
									
									var URI = BASE_URL + ROOT_URL + LIS_UNIT+EDIT_LAB_TEMPLATE_MASTER+S+labTemplateId;
									
									GenericService.serviceAction("GET", URI, $scope.common).then(function(response) 
									{
										
										$rootScope.stopLoader();
										$scope.formeo = new Formeo({
											  container: '#formeo-editor',
											  svgSprite: 'resources/assets/images/LIS/formeo-sprite.svg',
											},JSON.stringify(JSON.parse(response.data.templateData)));
										$scope.labTemplateMasterDto = response.data;
										$scope.value = response.data.htmlData;
										$scope.templateTypeId=$scope.labTemplateMasterDto.listSpecialtyTemplateMapperDto[0].templateTypeId;
										$scope.flag = false;
										if($scope.labTemplateMasterDto.listSpecialtyTemplateMapperDto[0].specialityId==null)
											{
											$scope.specialityId = "";
											}
										else{
											$scope.specialityId=$scope.labTemplateMasterDto.listSpecialtyTemplateMapperDto[0].specialityId +"";
											var promise = $scope.getDoctorBySpeciality($scope.specialityId);
											promise.then(function(doct)
													{
												$scope.selectedDoctorList = [];
												var data={
														"id":"",
														"label":""
												}
														angular
														.forEach(
																$scope.labTemplateMasterDto.listSpecialtyTemplateMapperDto,
																function(doctorObj) {
																	  var doctObj = angular.copy(data);
																	  doctObj.id=doctorObj.doctorId;
																	  $scope.doctorDetailsObj = $scope.doctorList.filter(function (doctObj)
																		{
																		  if(doctObj.id  ==doctorObj.doctorId)
																			  {
																			     return doctObj;
																			  }
																		});
																	  doctObj.label= $scope.doctorDetailsObj[0].label;
																	  $scope.selectedDoctorList.push(doctObj);
																		console.log("$scope.doctorList",JSON.stringify($scope.doctorList));
																});
										    }, function(error) {
										      console.error(error)
										    })
											
										}
										
										
										console.log("getupdateData",JSON.stringify(response.data));
										/*if (response.data ==true)
										{
											$rootScope.stopLoader();
											$scope.formData.actions.setData(JSON.stringify(JSON.parse(response.data.templateData)));
											$scope.labTemplateMasterDto = response.data;
										
										} else if(response.data !=true)
										{
											$rootScope.stopLoader();
											alert("Error In Fetching Data");
										}*/
									});
								} catch (e) {
									console.log("Exception",e.message);
								}
								
							}
							
							
							$scope.update = function() 
							{
								$scope.$broadcast('show-errors-check-validity');
								if ($scope.labTemplateMasterform.$valid)
								{
									try {
										$rootScope.startLoader();
								        $scope.labTemplateMasterDto.templateData =$scope.formeo.formData;
										$scope.labTemplateMasterDto.htmlData = $scope.value;
										var URI = BASE_URL + ROOT_URL + LIS_UNIT+UPDATE_LAB_TEMPLATE_MASTER;
										GenericService.serviceAction("PUT", URI,$scope.labTemplateMasterDto).then(function(response) 
										{
											$rootScope.stopLoader();
											$scope.labTemplateMasterDto={};
											 $scope.cleareTemplate();
											if (response.data == true)
											{
												 
												$rootScope.stopLoader();
												$scope.labTemplateMasterDto={};
												 $scope.cleareTemplate();
												 growl.success("Template Updated Successfully.",
														{
															title : 'Success!'
														});
												$scope.$broadcast('show-errors-reset');
												 $scope.labTemplateMasterDto={};
												 $scope.cleareTemplate();
											    $scope.initLabTemplateMasterList($scope.orgId, $scope.offset, $scope.noOfRecordsPerPage);
												
											} else if(response.data !=true)
											{
												$rootScope.stopLoader();
												growl.error("Failed To Update Template.",
														{
															title : 'Error!'
														});
											}
										});
									} catch (e) 
									{
										console.log("Exception",e.message);
									}
									
								}
							}
							
							//code for Activating Inactivating Lab Templates
							$scope.updateStatus = function(commonId,status)
							{
								try {
									$rootScope.startLoader();
									var URI = BASE_URL + ROOT_URL + LIS_UNIT+ACTIVE_INACTIVE_LAB_TEMPLATE_MASTER+S+$scope.orgId+S+commonId+S+status;
									console.log("ACT_INACT_URI",URI);
									
									
									GenericService.serviceAction("GET", URI, $scope.labTemplateMasterDto).then(function(response) 
									{
										if (response.data ==true)
										{
											$rootScope.stopLoader();
											growl.success(response.data.message,
													{
														title : 'Success!'
													});
											$scope.$broadcast('show-errors-reset');
											$scope.initCommon();
										} else if(response.data !=true)
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
							
							$scope.plzSelectSex='Please select gender.';
							$scope.plzTemplateCode='Template code is requried.';
							$scope.plzTemplateDesc='Template description is requried.';
							$scope.plzTemplatepatho='Please select pathologist.';
							
							
							
							$scope.formeo = new Formeo({
								  container: '#formeo-editor',
								  svgSprite: 'resources/assets/images/LIS/formeo-sprite.svg',
								});
							$scope.cleareTemplate = function()
							{
								$scope.value = "";
								location.reload();
							}
							
							
							
							/**=======================================*//*
							 * <div id="formeo-editor"></div>
				         <div id="myHtml" class="render-form" style="display:none"></div>
				    <input type="button" value="format Template" class="btn btn-primary btn-save-box-shadow" ng-click="newSave();" />
					<input type="button" value="format Template" class="btn btn-primary btn-save-box-shadow" ng-click="getData(); "/>
					
							
							
							
							$scope.newSave = function()
							{
								console.log("JSON DATA",$scope.formeo.formData);
								console.log("formeo",$scope.formeo);
								$scope.renderContainer = document.querySelector('.render-form');
								$scope.formeo.render($scope.renderContainer);
								console.log(document.getElementById("myHtml").innerHTML);
								
								
							}
							$scope.getData = function()
							{
								$scope.formeo = new Formeo({
									  container: '#formeo-editor',
									  svgSprite: 'resources/assets/images/LIS/formeo-sprite.svg',
									},JSON.stringify(data));
							
							}
							CKEDITOR.stylesSet.add( 'my_styles', [
							    // Block-level styles.
							    { name: 'Blue Title', element: 'h2', styles: { color: 'Blue' } },
							    { name: 'Red Title',  element: 'h3', styles: { color: 'Red' } },

							    // Inline styles.
							    { name: 'CSS Style', element: 'span', attributes: { 'class': 'my_style' } },
							    { name: 'Marker: Yellow', element: 'span', styles: { 'background-color': 'Yellow' } }
							]);*/
							/**=======================================*/
						

						} ]);