
/**
 * @author Ganesh
 */

angular
		.module('myApp')
		.controller(
				"histMacroScopicWorklistController",
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
							
							$scope.visityTypeList = [];
							$scope.specimanTypeList =[];
							$scope.testList = [];
							
							$scope.selectedVisitType = [];
							$scope.selectedspecimanTypeList = [];
							$scope.selectedtestList = [];
							/**DatePicker Code  */
							
							$scope.fromDate = moment().startOf('day').toDate();
						    $scope.toDate = moment().startOf('day').toDate();

							$scope.dateOptions =
							{
								formatYear : 'yyyy',
								showWeeks : false,
								maxDate : new Date(),
							};

							$scope.open = function() {
								$scope.popup.opened = true;
							};

							$scope.popup =
							{
								opened : false
							};
							
							$scope.popup1 =
							{
								opened : false
							};
							
							$scope.open1 = function() 
							{
								$scope.popup1.opened = true;
							};
							
							if(angular.equals({},$stateParams.microScopeObj))
								{
								  $state.go('microscopeExamination');
								}

							
							 $scope.patientDetails = {
									 "uhIdNumber":$stateParams.microScopeObj.uhid,
									 "patientName":$stateParams.microScopeObj.patientDetails,
									 "genderCode":$stateParams.microScopeObj.genderName,
									 "age":$stateParams.microScopeObj.age,
									 "birthDate":moment($stateParams.microScopeObj.dob).format('DD-MM-YYYY')
							 }
							   
							    
							    $scope.selectedItems = [];
							    $scope.dropDownSetting = {
							    		scrollableHeight: '100px',  	
							    		scrollable: true
							    };  
						
							/** Service Autocomplete List * *//*
							$scope.getMicroPatientList = function(searchKeyword)
							{
								try 
								{
									var data = {
											"orgId":$scope.orgId,
											"orgUnitId":$scope.orgUnitId,
											"deptId":$scope.deptId,
											"subDeptId":$scope.subDeptId,
											"searchKeyword":searchKeyword,
									}
									
									var URI = BASE_URL 
									        + ROOT_URL
											+ LIS_HISTOPATHOLOGY
											+ HISTO_MICRO_PATIENT_SEARCH
									console.log("URI", URI);
									return GenericService.serviceAction("POST", URI,
											data).then(function(response) {
										console.log("response.data.listObject",response.data.listObject);
										return response.data.listObject;
									});
								} catch (e) {
									console.log(e.message)
								}
							}	
							
							$scope.getVisitTypeList = function()
							{
								try 
								{
									var data = {
											"orgId":$scope.orgId,
											"orgUnitId":$scope.orgUnitId,
											"deptId":$scope.deptId,
											"subDeptId":$scope.subDeptId,
											"searchKeyword":"",
									}
									
									var URI = BASE_URL 
									        + ROOT_URL
											+ LIS_HISTOPATHOLOGY
											+ HISTO_MICRO_VISIT_LIST
									console.log("URI", URI);
									 GenericService.serviceAction("POST", URI,
											data).then(function(response) {
										console.log("response.data.listObject",response.data.listObject);
										$scope.visityTypeList =  response.data.listObject;
									});
								} catch (e) {
									console.log(e.message)
								}
							}
							
							$scope.getVisitTypeList();
							
							$scope.getSpecimanList = function()
							{
								try 
								{
									var data = {
											"orgId":$scope.orgId,
											"orgUnitId":$scope.orgUnitId,
											"deptId":$scope.deptId,
											"subDeptId":$scope.subDeptId,
											"searchKeyword":"",
									}
									
									var URI = BASE_URL 
									        + ROOT_URL
											+ LIS_HISTOPATHOLOGY
											+ HISTO_MICRO_SPECIMAN_LIST
									console.log("URI", URI);
									 GenericService.serviceAction("POST", URI,
											data).then(function(response) 
									{
										console.log("response.data.listObject",response.data.listObject);
									  $scope.specimanTypeList =  response.data.listObject;
									});
								} catch (e) {
									console.log(e.message)
								}
							}
							$scope.getSpecimanList();
							
							$scope.getTestList = function()
							{
								try 
								{
									var data = {
											"orgId":$scope.orgId,
											"orgUnitId":$scope.orgUnitId,
											"deptId":$scope.deptId,
											"subDeptId":$scope.subDeptId,
											"searchKeyword":"",
									}
									
									var URI = BASE_URL 
									        + ROOT_URL
											+ LIS_HISTOPATHOLOGY
											+ HISTO_MICRO_TEST_LIST
									console.log("URI", URI);
									 GenericService.serviceAction("POST", URI,
											data).then(function(response) {
										console.log("response.data.listObject",response.data.listObject);
										$scope.testList =  response.data.listObject;
									});
								} catch (e) {
									console.log(e.message)
								}
							}
							$scope.getTestList();
							
							$scope.selectPatient = function($item, $model,$label)
									{
								      $scope.selectedPatient = $item.id;
							        }
							
                            $scope.searchWorkList = function()
							{
								try 
								{
									var searchObj = {
											"orgId":$scope.orgId,
											"orgUnitId":$scope.orgUnitId,
		                            		"fromDate": moment($scope.fromDate.getTime()).format('YYYY-MM-DD') ,
		                            		"toDate":  moment($scope.toDate.getTime()).format('YYYY-MM-DD'),
		                            		"patientId": $scope.selectedPatient,
		                            		"visitTypes":$scope.selectedVisitType,
		                            		"specimanTypes":$scope.selectedspecimanTypeList,
		                            		"testTypes":$scope.selectedtestList,
		                             }
									console.log("microscopicExaminationList",JSON.stringify(searchObj));
									var URI = BASE_URL 
									        + ROOT_URL
											+ LIS_HISTOPATHOLOGY
											+ HISTO_MICRO_SEARCH_MICRO_ORGANISM_LIST
									console.log("URI", URI);
									 GenericService.serviceAction("POST", URI,
											 searchObj).then(function(response) {
										console.log("microscopicExaminationList",response.data.listObject);
										$scope.microscopicExaminationList =  response.data.listObject;
									});
								} catch (e) {
									console.log(e.message)
								}
							}*/
                            $scope.isSubSpecimanReq;
                            $scope.captureNote;
                            $scope.tSpecimanMaster = {
									"tSpecimanId": "",
									"orgId": $scope.orgId,
									"orgUnitId": $scope.orgUnitId,
									"specimanExaminination": "",
									"captureNote" :"",
									"labSampleDtlsId": "",
									"histopathlogyNumber": "",
									"barcodeNo": "",
									"createdBy": $scope.createdBy,
									"createdDate": "",
									"updatedBy": $scope.updatedBy,
									"updatedDate": "",
									"isDeleted": "N",
									"specimanTypeId": "",
									"specimanId": "",
									"specimanName": "",
									"uhid": "",
									"genderName": "",
									"patientDetails": "",
									"doctorDetails": "",
									"dob": "",
									"age": "",
									"visitType": "",
									"visitTypeId": "",
									"testDesc": "",
									"specimanType": "",
									"frozenSection": "",
									"notes": "",
									"listTSubSpecimanMaster": $scope.listTSubSpecimanMaster
								}
                            
                            $scope.tSpecimanMaster = $stateParams.microScopeObj;

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
                            		  "age" : 0,
                            		  "templateResId":"",
                            		  "listTGrossMaster" : $scope.listTGrossMaster
                            		}
                            
                            
                              $scope.tGrossMaster =
                              {
                            		  "tGrossId" : "",
                            		  "orgId" : $scope.orgId,
                            		  "orgUnitId" : $scope.orgUnitId,
                            		  "tSubSpecimanId" : "",
                            		  "labSampleDtlsId" : "",
                            		  "grossBarcode" : "",
                            		  "grossNo" : "",
                            		  "grossNum" : "",
                            		  "grossDesc" : "",
                            		  "captureNote" : "",
                            		  "createdBy" : $scope.createdBy,
                            		  "createdDate" : "",
                            		  "updatedBy" : "",
                            		  "updatedDate" : "",
                            		  "isDeleted" : "N",
                            		  "listTBlockMaster" : [],
                                      "listTRestainingRequestDetailsMasterDto" :[]
                            	}
							
                            
                            $scope.listTSubSpecimanMaster = [];
                            $scope.listTGrossMaster = [];
                            
                            
                            $scope.findMaxSubSpecimanNumber = function(inputList)
                                {
                            	  if(Array.isArray(inputList))
                            		  {
                            		  if(inputList.length>0)
                              		    {
                              		      return Math.max.apply(
                              		    		   Math,inputList.map(function(o)
                              		    				   {
                              		    			         return parseInt(o.subSpecimanNum)+1;
                              		    			         }
                              		    		        ));
                              		    }
                            		  else
                              	  		{
                            			  return 1;
                              	  		}
                            		  }
                            	 
                               }
                            $scope.findMaxGrossNumber=  function(inputList)
                            {
                             if(Array.isArray(inputList))
                      		  {
                               if(inputList.length>0)
                     		     {
                            	   return Math.max.apply(Math,inputList.map(function(o){
                            		   return parseInt(o.grossNum)+1}));
                     		     }
                     	     else
                     	       {
                     		     return 1;
                     	       }
                             }
                            }
                            
                            $scope.addSpecimen = function()
                            {
                            	if($scope.isSubSpecimanReq==0)
                            		{
                            		 $scope.listTSubSpecimanMaster = [];
                            		 $scope.listTGrossMaster = [];
                            		 var tGrossMaster = angular.copy($scope.tGrossMaster);
                            		 tGrossMaster.grossNum =  $scope.findMaxGrossNumber($scope.listTGrossMaster);
                            		 var grossNo = voucher_codes.generate({
                            			    prefix: $scope.tSpecimanMaster.histopathlogyNumber+GROSS_PREFIX,
                            			    inputNumber: tGrossMaster.grossNum,
                            			    length:NUMBER_WIDTH
                            			})[0];
                            		 tGrossMaster.grossNo = grossNo;
                            		 $scope.listTGrossMaster.push(tGrossMaster);
                            		 var tSubSpecimanMaster = angular.copy($scope.tSubSpecimanMaster);
                             		 tSubSpecimanMaster.listTGrossMaster = $scope.listTGrossMaster;
                             		 tSubSpecimanMaster.subSpecimanNum = $scope.findMaxSubSpecimanNumber([]);
                             		 $scope.listTSubSpecimanMaster.push(tSubSpecimanMaster);
                             		 console.log("$scope.listTSubSpecimanMaster 0",$scope.listTSubSpecimanMaster);
                            		}
                            	else if($scope.isSubSpecimanReq==1)
                            	{
                            		
                            		$scope.listTSubSpecimanMaster = [];
                            		$scope.listTGrossMaster = [];
                            		
                            		var tSubSpecimanMaster = angular.copy($scope.tSubSpecimanMaster);
                            		var tGrossMaster = angular.copy($scope.tGrossMaster);
                            		
                            		tSubSpecimanMaster.subSpecimanNum = $scope.findMaxSubSpecimanNumber([]);
                            		var subSpecimenNo = voucher_codes.generate({
                        			    prefix: $scope.tSpecimanMaster.histopathlogyNumber+SUB_SPECIMEN_PREFIX,
                        			    inputNumber: tSubSpecimanMaster.subSpecimanNum,
                        			    length:NUMBER_WIDTH
                        			})[0];
                            		
                            		tGrossMaster.grossNum =  $scope.findMaxGrossNumber([]);
                            		
                            		var grossNo = voucher_codes.generate({
                        			    prefix: subSpecimenNo+GROSS_PREFIX,
                        			    inputNumber: tGrossMaster.grossNum,
                        			    length:NUMBER_WIDTH
                        			})[0];
                            		
                            		console.log("subSpecimenNo",subSpecimenNo);
                            		console.log("grossNo",grossNo);
                        		     tGrossMaster.grossNo = grossNo;
                            		$scope.listTGrossMaster.push(tGrossMaster);
                            		
                            		
                            		tSubSpecimanMaster.subSpecimanNum = 1;
                            		tSubSpecimanMaster.listTGrossMaster = $scope.listTGrossMaster;
                            		tSubSpecimanMaster.subSpcimanNo =  subSpecimenNo;
                            		$scope.listTSubSpecimanMaster.push(tSubSpecimanMaster);
                            		
                            		$scope.tSpecimanMaster.listTSubSpecimanMaster = $scope.listTSubSpecimanMaster;
                            		console.log("$scope.listTSubSpecimanMaster 1",$scope.listTSubSpecimanMaster);
                            	}
                            }
                            
                            $scope.addSubSpecimen = function()
                            {
                        		
                        		$scope.listTSubSpecimanMaster = [];
                        		$scope.listTGrossMaster = [];
                        		
                                var tSubSpecimanMaster = angular.copy($scope.tSubSpecimanMaster);
                            	var tGrossMaster = angular.copy($scope.tGrossMaster);
                            	
                            	tSubSpecimanMaster.subSpecimanNum = $scope.findMaxSubSpecimanNumber($scope.tSpecimanMaster.listTSubSpecimanMaster);
                            	tGrossMaster.grossNum =  $scope.findMaxGrossNumber([]);
                            	 
                            	 tGrossMaster.grossNum =  tGrossMaster.grossNum;
                            		var subSpecimenNo = voucher_codes.generate({
                        			    prefix: $scope.tSpecimanMaster.histopathlogyNumber+SUB_SPECIMEN_PREFIX,
                        			    inputNumber: tSubSpecimanMaster.subSpecimanNum,
                        			    length:NUMBER_WIDTH
                        			})[0]; 
                            	 
                            	 var grossNo = voucher_codes.generate({
                     			    prefix: subSpecimenNo+GROSS_PREFIX,
                     			    inputNumber: tGrossMaster.grossNum,
                     			    length:NUMBER_WIDTH
                     			})[0];
                            	 
                            	 
                            	 
                            	 tGrossMaster.grossNo = grossNo;
                       		     $scope.listTGrossMaster.push(tGrossMaster);
                       		     
                       		   
                       		     tSubSpecimanMaster.listTGrossMaster = [];
                       			 tSubSpecimanMaster.subSpcimanNo = subSpecimenNo;
                       		
                        		 tSubSpecimanMaster.listTGrossMaster = $scope.listTGrossMaster;
                        		 $scope.tSpecimanMaster.listTSubSpecimanMaster.push(tSubSpecimanMaster);
                            }
                            $scope.addGross = function(index)
                            {
                            	var tGrossMaster = angular.copy($scope.tGrossMaster);
                            	if($scope.isSubSpecimanReq==0)
                            		{
                            		 tGrossMaster.grossNum =  $scope.findMaxGrossNumber($scope.listTGrossMaster);
                            			 var grossNo = voucher_codes.generate({
                              			    prefix:  $scope.tSpecimanMaster.histopathlogyNumber+GROSS_PREFIX,
                              			    inputNumber:  tGrossMaster.grossNum,
                              			    length:NUMBER_WIDTH})[0]; 
                            		  tGrossMaster.grossNo = grossNo;
                            		 
                            		  $scope.listTGrossMaster.push(tGrossMaster);
                            		}
                            	else if($scope.isSubSpecimanReq==1)
                            		{
                            		var subSpecimanNum = $scope.findMaxSubSpecimanNumber($scope.tSpecimanMaster.listTSubSpecimanMaster);
                            	  		var subSpecimenNo = voucher_codes.generate({
                            			    prefix: $scope.tSpecimanMaster.histopathlogyNumber+SUB_SPECIMEN_PREFIX,
                            			    inputNumber: --subSpecimanNum,
                            			    length:NUMBER_WIDTH
                            			})[0]; 
                                	 
                            	  	 tGrossMaster.grossNum =  $scope.findMaxGrossNumber($scope.tSpecimanMaster.listTSubSpecimanMaster[index].listTGrossMaster);	
                                	 var grossNo = voucher_codes.generate({
                         			    prefix: subSpecimenNo+GROSS_PREFIX,
                         			    inputNumber:tGrossMaster.grossNum,
                         			    length:NUMBER_WIDTH
                         			})[0];
                            		 tGrossMaster.grossNo = grossNo;
                            		 tGrossMaster.grossNum =  tGrossMaster.grossNum;
                            		 $scope.tSpecimanMaster.listTSubSpecimanMaster[index].listTGrossMaster.push(tGrossMaster);
                            		}
                            	
                            }
                            
                            $scope.removeSubSpecimen = function(subSpecimenIndex)
                            {
                            	$scope.tSpecimanMaster.listTSubSpecimanMaster.splice(subSpecimenIndex, 1);
                            }
                            $scope.removeGrossWithSubSpeciman = function(subSpecimenIndex,grossIndex)
                            {
                            	$scope.tSpecimanMaster.listTSubSpecimanMaster[subSpecimenIndex].listTGrossMaster.splice(grossIndex, 1);
                            }
                            $scope.removeGrossWithoutSubSpeciman = function(grossIndex)
                            {
                            	$scope.listTSubSpecimanMaster[0].listTGrossMaster.splice(grossIndex, 1);
                            }
                            
                          
                            

							$scope.setNoOfRecords = function() {
								$scope.initHistoMicroscopicWorklist($scope.orgId,
										$scope.orgUnitId,$scope.deptId,$scope.subDeptId,$scope.offset,
										$scope.noOfRecordsPerPage);
							};

							$scope.initHistoMicroscopicWorklist = function(orgId,
									orgUnitId,deptId,subDeptId, offset, noOfRecordsPerPage) {
								try {
									$rootScope.startLoader();
									var data = {
											"orgId":orgId,
											"orgUnitId":orgUnitId,
											"offset":offset,
											"recordPerPage":noOfRecordsPerPage,
											"deptId":$scope.deptId,
											"subDeptId":$scope.subDeptId
											
										};
									var data1 = {
											"orgId":orgId,
											"orgUnitId":orgUnitId,
											"deptId":$scope.deptId,
											"subDeptId":$scope.subDeptId
										};
									offset = offset != null ? offset : 0;
									noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
											: 10;
									var URI1 = BASE_URL 
									        + ROOT_URL
											+ LIS_HISTOPATHOLOGY
											+ HISTO_MICRO_SCOPIC_WORKLIST;

									var URI2 = BASE_URL
											+ ROOT_URL
											+ LIS_HISTOPATHOLOGY
											+ HISTO_MICRO_SCOPIC_WORKLIST_COUNT;

									console.log("URI1", URI1);
									console.log("URI2", URI2);
									promiseFactoryWithObject
											.setPromisesWithObject(URI1, URI2, METHOD_POST,
													METHOD_POST,data,data1)
											.then(
													function(response) {
														$rootScope.stopLoader();
														$scope.microscopicExaList = response[0].listObject;
														$scope.commonListCount = response[1].object;
														$scope.setPage(1, false);
														console.log("$scope.specimenReceiptList",$scope.microscopicExaList);
														console.log("$scope.commonListCount",$scope.commonListCount);
													});
									
								} catch (e) {
									console.log(e.message);
								}
							}

							$scope.initHistoMicroscopicWorklist($scope.orgId,
									$scope.orgUnitId,$scope.deptId,$scope.subDeptId,$scope.offset,
									$scope.noOfRecordsPerPage);
							
           
							$scope.getHistoMicroscopicWorklist = function(orgId,
									orgUnitId,deptId,subDeptId,offset,noOfRecordsPerPage) {
								try {
									$rootScope.startLoader();
									var data = {
											"orgId":orgId,
											"orgUnitId":orgUnitId,
											"offset":offset,
											"recordPerPage":noOfRecordsPerPage,
											"deptId":$scope.deptId,
											"subDeptId":$scope.subDeptId
										};
									var URI = BASE_URL 
											+ ROOT_URL
											+ LIS_HISTOPATHOLOGY
											+ HISTO_MICRO_SCOPIC_WORKLIST;
									console.log("URI", URI);
									
									GenericService
											.serviceAction(METHOD_POST, URI, data)
											.then(
													function(response) {
														$rootScope.stopLoader();
														$scope.microscopicExaList = response.data.listObject;
													});
								} catch (e) {
									console.log(e.message)
								}

							}

							$scope.pager = {};
							$scope.page;
							$scope.setPage = function(page, flag)
							{
								if (page < 1 || page > $scope.pager.totalPages)
								{
									return;
								}
								$scope.pager = PagerService.GetPager($scope.commonListCount, page,$scope.noOfRecordsPerPage);
								if (flag) 
								{
									$scope.getHistoMicroscopicWorklist($scope.orgId, $scope.orgUnitId,$scope.deptId, $scope.subDeptId,$scope.pager.startIndex,
											$scope.pager.pageSize);
								}
							}

							 //** code for setting all checkboxes *//*
							$scope.checkAll = function() {
								if ($scope.selectedAll) {
									$scope.selectedAll = true;
								} else {
									$scope.selectedAll = false;
								}
								angular
										.forEach(
												$scope.microscopicExaList,
												function(specimanObj) {
													specimanObj.isAccpted = $scope.selectedAll
												});
							 }

							 // code for Activating Inactivating Sample
							$scope.histoCreateGross = function() 
							{
								$scope.$broadcast('show-errors-check-validity');
								if ($scope.macroscopicExaminationForm.$valid)
									{
								$rootScope.startLoader();
								$scope.tSpecimanMaster.updatedBy = $scope.createdBy
                      		    $scope.tSpecimanMaster.updatedDate = moment().toDate().getTime();
								if($scope.isSubSpecimanReq==0)
                        		{  
                        		  $scope.tSpecimanMaster.listTSubSpecimanMaster = $scope.listTSubSpecimanMaster;
                        		  $scope.tSpecimanMaster.listTSubSpecimanMaster[0].subSpecimanName =  $scope.tSpecimanMaster.specimanName;
                        		  $scope.tSpecimanMaster.listTSubSpecimanMaster[0].tSpecimanId =  $scope.tSpecimanMaster.tSpecimanId;
                        		  $scope.tSpecimanMaster.listTSubSpecimanMaster[0].subSpecimanExaminination=  $scope.tSpecimanMaster.specimanExaminination;
                        		  $scope.tSpecimanMaster.listTSubSpecimanMaster[0].labSampleDtlsId =  $scope.tSpecimanMaster.labSampleDtlsId;
                        		  $scope.tSpecimanMaster.listTSubSpecimanMaster[0].subSpcimanNo=  $scope.tSpecimanMaster.histopathlogyNumber;
                        		  $scope.tSpecimanMaster.listTSubSpecimanMaster[0].subSpecimanBarcode =  $scope.tSpecimanMaster.barcodeNo;
                        		  $scope.tSpecimanMaster.listTSubSpecimanMaster[0].captureNote =  $scope.tSpecimanMaster.captureNote;
                        		  $scope.tSpecimanMaster.listTSubSpecimanMaster[0].specimanTypeId =  $scope.tSpecimanMaster.specimanTypeId;
                        		  $scope.tSpecimanMaster.listTSubSpecimanMaster[0].specimanId =  $scope.tSpecimanMaster.specimanId;
                        		  $scope.tSpecimanMaster.listTSubSpecimanMaster[0].specimanName =  $scope.tSpecimanMaster.specimanName;
                        		  $scope.tSpecimanMaster.listTSubSpecimanMaster[0].listTGrossMaster = $scope.listTGrossMaster;
                        		}
                        	else if($scope.isSubSpecimanReq==1)
                        	{
                        		$scope.tSpecimanMaster.listTSubSpecimanMaster = $scope.listTSubSpecimanMaster;
                        		console.log("$scope.listTSubSpecimanMaster 1",$scope.listTSubSpecimanMaster);
                        	}
								try {
									console.log("$scope.tSpecimanMaster",JSON.stringify($scope.tSpecimanMaster));
									var URI = BASE_URL 
											+ ROOT_URL
											+ LIS_HISTOPATHOLOGY
											+ HISTO_MICRO_CREATE_GROSS;
									console.log("CREATE GROSS", URI);
									GenericService
											.serviceAction(METHOD_POST, URI,
													$scope.tSpecimanMaster)
											.then(
													function(response) {
														if (response.data.status == 'success') 
														{
															$state.go('microscopeExamination');
															growl.success(response.data.message,
																{
																	title : 'Success!'
																});
															$scope
																	.$broadcast('show-errors-reset');

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
						}
							
							
				$scope.specimenCaptureNoteReq = "Speciman note is required.";	
				$scope.subSpspecimenNameReq = "Please select sub specimen name.";	
				$scope.subSpspecimenCaptureNoteReq = "sub Speciman note is required.";	
				$scope.grossReq = "Gross Note is Required.";
				$scope.captureReq = "Capture Note is Required.";
				$scope.subSpecimenReq = "Please select value.";	
				$scope.specimenCaptureNote = "Speciman note is required.";	
				$scope.specimenCaptureNote = "Speciman note is required.";	
				

						} ]);