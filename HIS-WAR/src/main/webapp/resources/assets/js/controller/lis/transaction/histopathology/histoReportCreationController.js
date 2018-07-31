
/**
 * @author Ganesh
 */

angular
		.module('myApp')
		.controller(
				"histoReportCreationController",
				[
						'$scope',
						'$rootScope',
						'$state',
						'$cookies',
						'GenericService',
						'PagerService',
						'promiseFactoryWithObject',
						'$sessionStorage',
						'growl',
						'$stateParams',
						function($scope, $rootScope, $state, $cookies,
								GenericService, PagerService, promiseFactoryWithObject,
								$sessionStorage, growl,$stateParams) {

							$rootScope.loginpage = true;
							$scope.LISDynamicLabel = "Specimen Receipt";

							/** Variables for pagination */
							$scope.offset = 0;
							$scope.noOfRecordsPerPage = 10;
							$scope.specimenReceiptList = [];
							$scope.totalNoOfRecords;
							$scope.searchKeyword;
							$scope.commonListCount;

							$scope.orgId = 1;
							$scope.orgUnitId = 1;
							$scope.createdBy = 1;
							$scope.updatedBy = 1;
							$scope.deptId = LAB_DEPT ;
							$scope.subDeptId = HISTOPATHOLOGY_DEPT;
							
							
						   $scope.tSubSpecimanMaster ={
                            		  "tSubSpecimanId" : "",
                            		  "orgId" : $scope.orgId,
                            		  "orgUnitId" : $scope.orgUnitId,
                            		  "subSpecimanName":"",
                            		  "tSpecimanId" : "",
                            		  "subSpecimanExaminination" : "",
                            		  "labSampleDtlsId" : "",
                            		  "subSpcimanNo" : "",
                            		  "subSpecimanBarcode" : "",
                            		  "captureNote" : "",
                            		  "createdBy" : $scope.createdBy,
                            		  "createdDate" : "",
                            		  "updatedBy" : "",
                            		  "updatedDate" : "",
                            		  "isDeleted" : "N",
                            		  "specimanTypeId" : "",
                            		  "specimanId" : "",
                            		  "specimanName" : "",
                            		  "subSpecimanNum" : "",
                            		  "uhid" : "",
                            		  "genderName" : "",
                            		  "patientDetails" : "",
                            		  "doctorDetails" : "",
                            		  "dob" : "",
                            		  "age" : "",
                            		  "templateResId":"",
                            		  "listTGrossMaster" : []
                            		}
							
							 $scope.patientDetails = {
									 "uhIdNumber":$stateParams.subSpecimenObj.uhid,
									 "patientName":$stateParams.subSpecimenObj.patientDetails,
									 "genderCode":$stateParams.subSpecimenObj.genderName,
									 "age":$stateParams.subSpecimenObj.age,
									 "birthDate":moment($stateParams.subSpecimenObj.dob).format('DD-MM-YYYY')
							   }
						   /** code for generating url * */
						    $scope.tSubSpecimanMaster = $stateParams.subSpecimenObj;
						     $scope.histoPathNo = $stateParams.subSpecimenObj.histopathlogyNumber;
						   console.log("Report Creation",$scope.tSubSpecimanMaster);
							$scope.generateRestainingRequestUrl = function()
								{
											 if($scope.tSubSpecimanMaster!=null)
											  {
															$state.go('restainingRequest',
																{
																 subSpecimenObj : $scope.tSubSpecimanMaster,
																});
											  }
										  else
										     {
											    growl.error("Something went Wrong.",{title : 'Error!'});
										     }			
											
								}
							
		
							  $scope.labTemplateList = [];
						      $scope.templateObj = 
						      {
						    	      "labTemplateId":"",	  
						    		  "title":"",
						    		  "image":"",
						    		  "description":"",
						    		  "html" :""
						      }
							
							$scope.getLabTemplateList = function() {
								try {
									var data = "";
									var URI = BASE_URL + ROOT_URL
											+ LIS_UNIT + GET_LAB_TEMPLATES
											+ S 
											+ $scope.orgId+
									        S + $scope.orgUnitId;
									console.log("URI", URI);
									GenericService.serviceAction("GET", URI, data)
											  .then(function(response) 
													{
														$scope.labTemlateList = response.data.listObject;
														angular.forEach($scope.labTemlateList, function(value, key) 
																{
															      var templateObj = angular.copy($scope.templateObj);
															      templateObj.labTemplateId = value.labTemplateId;
															      templateObj.title = value.templateCode;
															      templateObj.image  = 'template1.gif';
															      templateObj.description = value.templateDesc;
															      templateObj.html = value.htmlData;
															      $scope.labTemplateList.push(templateObj);
															});
													});
								   } catch (e) {
									console.log(e.message)
								}
							}
							
							$scope.getLabTemplateList();

							
							
							$scope.getLabResult = function(templateResId) {
								try {
									var data = "";
									var URI = BASE_URL 
									        + ROOT_URL
											+ LIS_HISTOPATHOLOGY + HISTO_GET_RESULT_ENTRY
											+ S+templateResId 
											+ S+$scope.orgId+
									        S + $scope.orgUnitId;
									console.log("URI", URI);
									GenericService.serviceAction("GET", URI, data)
											  .then(function(response) 
													{
														$scope.templateResultMaster = response.data.object;
														$scope.value = response.data.object.templateResult;
													});
								   } catch (e) {
									console.log(e.message)
								}
							}
							
							if($stateParams.subSpecimenObj.templateResId!=null&&$stateParams.subSpecimenObj.templateResId!='')
								{
								  $scope.getLabResult($stateParams.subSpecimenObj.templateResId);
								}
							
							$scope.value="";
							
							$scope.saveHistoResultEntry = function() 
							{
								$scope.templateResultMaster.tSubSpecimanId = $scope.tSubSpecimanMaster.tSubSpecimanId;
								$scope.templateResultMaster.labSampleDtlsId = $scope.tSubSpecimanMaster.labSampleDtlsId;
								$scope.templateResultMaster.templateResult = $scope.value;
								$rootScope.startLoader();
								try {
								
									console.log("$scope.templateResultMaster",JSON.stringify($scope.templateResultMaster));
									var URI = BASE_URL 
											+ ROOT_URL
											+ LIS_HISTOPATHOLOGY
											+ HISTO_RESULT_ENTRY;
									console.log("SAVE_RESTAIN_REQUEST", URI);
								 GenericService
											.serviceAction(METHOD_POST, URI,
													$scope.templateResultMaster)
											.then(
													function(response) {
														if (response.data.status == 'success') 
														{
															
															$state.go('microscopicExaminiationWorkList');
															growl.success(response.data.message,
																{
																	title : 'Success!'
																});
															$scope
																	.$broadcast('show-errors-reset');
															$rootScope.stopLoader();

														} else if (response.data.status == 'error') 
														{
															$rootScope.stopLoader();
															growl.error(response.data.message,
															{
																title : 'Error!'
															});
														}
													});
								} catch (e) {
									console.log("Exception", e.message);
								}
							}
							
							
							/**===================Template Editor CODE START============================================================================================================================*/
								CKEDITOR.config.width = '50%';
								CKEDITOR.config.height = '500';
								CKEDITOR.config.extraPlugins = 'imageuploader';
								//CKEDITOR.config.readOnly = true;


						      
						      $scope.showTemplate = function ()
								{
									//CKEDITOR.tools.callFunction(19,this);return false;
									CKEDITOR.tools.callFunction(1050,this);return false;
								}
						      
							 CKEDITOR.addTemplates("default", {
							    imagesPath:CKEDITOR.getUrl(CKEDITOR.plugins.getPath("templates")+"templates/images/"),
							    templates: $scope.labTemplateList
							   });
								/**===================Template Editor CODE END============================================================================================================================*/
							 
							 
							 
							 
							 
						$scope.templateResultMaster = 
							{
								      "templateResId" : "",
								      "orgId" : $scope.orgId,
									  "orgUnitId" : $scope.orgUnitId,
									  "tSubSpecimanId" : "",
									  "labTemplateId" : "",
									  "reportNo" : "",
									  "stainingId" : "",
									  "labSampleDtlsId" : "",
									  "createdBy" : $scope.createdBy,
									  "createdDate" : "",
									  "updatedBy" : "",
									  "updatedDate" : "",
									  "isDeleted" : "N",
									  "sampleStatusId":"",
									  "templateResult" : ""
							}
							 

						
						$scope.saveResult = function()
						{
							
							$scope.templateResultMaster.sampleStatusId = 0;
							$scope.saveHistoResultEntry();
						}
						$scope.provisionalRelease = function()
						{
							$scope.templateResultMaster.sampleStatusId = PROVISIONAL_REPORT_RELEASED;
							$scope.saveHistoResultEntry();
						}
						$scope.finalRelease = function()
						{
							$scope.templateResultMaster.sampleStatusId = FINAL_REPORT_RELEASED;
							$scope.saveHistoResultEntry();
						}

						} ]);