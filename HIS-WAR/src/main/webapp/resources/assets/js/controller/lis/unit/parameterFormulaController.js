/**
 * @author  Suraj  Kumbhoje
 */

angular.module('myApp').controller(
		"parameterFormulaController",
		[ '$scope','$rootScope','$state','$cookies','GenericService','PagerService','promiseFactory','$sessionStorage',
			'growl',
				function($scope,$rootScope,$state, $cookies, GenericService,PagerService,promiseFactory,$sessionStorage,growl)
				{
			       
			        $rootScope.loginpage = true;
					$scope.LISDynamicLabel = "Header ";
					$scope.saveBtnFlag = true;
					$scope.updateBtnFlag = false;
					
					
					/**Variables for pagination */
					$scope.offset = 0;
					$scope.noOfRecordsPerPage = 10;
					$scope.commonList = [];
					$scope.totalNoOfRecords;
					$scope.searchKeyword;
					$scope.commonListCount;
					
					$scope.orgId = 1;
					$scope.orgUnitId = 1;
					$scope.createdBy =1 ;
					$scope.updatedBy =1 ;
					
					MathJax.Hub.Config({
					    skipStartupTypeset: true,
					    messageStyle: "none",
					    "HTML-CSS": {
					        showMathMenu: false
					    }
					});
					MathJax.Hub.Configured();
					
		
					
					$scope.formulaDetailsList =[];
					$scope.formulaDetails ={
					  "formulaDetailsId" : "",
					  "formulaId" : "",
					  "parameterId" : "",
					  "formula" : "",
					  "paramDesc" : "",
					  "paramVariable" : "",
					  "orgId" :$scope.orgId ,
					  "orgUnitId" :$scope.orgUnitId ,
					  "isDeleted" : "N",
					  "createdBy" :$scope.createdBy ,
					  "createdDate" : "",
					  "updatedBy" : "",
					  "updatedDate" : ""
					}
					$scope.initFormulaMaster = function(){
					$scope.formulaMaster =
					{
							 "formulaId" : "",
							  "parameterId" : "",
							  "orgId" :$scope.orgId ,
							  "orgUnitId" :$scope.orgUnitId ,
							  "paramDesc" : "",
							  "formula" : "",
							  "formulaLatex" : "",
							  "status" : "A",
							  "createdBy" :$scope.createdBy ,
							  "createdDate" : "",
							  "updatedBy" : "",
							  "updatedDate" : "",
							  "listFormulaDetails" : $scope.formulaDetailsList
					 }
					}
					$scope.initFormulaMaster();
					
					
					/*
					 *  Get Header List
					 */
					$scope.getFormulaParameterList = function() {
						try {
							var data = "";
							var URI = BASE_URL + ROOT_URL
							        + LIS_UNIT
									+ GET_PARAMETER_FOR_FORMULA
									+ S + $scope.orgId + S + $scope.orgUnitId+S+"Y";
							console.log("URI", URI);
							GenericService
									.serviceAction("GET", URI, data)
									.then(
											function(response) {
												$scope.parameterListFormula  = response.data.listObject;
											});
						} catch (e) {
							console.log(e.message)
						}

					}
					$scope.getFormulaParameterList();
					
					$scope.getWithoutFormulaParameterList = function() {
						try {
							var data = "";
							var URI = BASE_URL + ROOT_URL
					        		+ LIS_UNIT
					        		+ GET_PARAMETER_FOR_FORMULA
					        		+ S + $scope.orgId + S + $scope.orgUnitId+S+"N";
							console.log("URI", URI);
							GenericService
									.serviceAction("GET", URI, data)
									.then(
											function(response) {
												$scope.parameterListNoFormula  = response.data.listObject;
											});
						} catch (e) {
							console.log(e.message)
						}

					}
					$scope.getWithoutFormulaParameterList();
					
					$scope.getFormulaDetails = function(formulaId) {
						try {
							var data = "";
							var URI = BASE_URL + ROOT_URL
					        		+ LIS_UNIT
					        		+ FORMULA_DETAILS
					        		+ S+formulaId+S + $scope.orgId + S + $scope.orgUnitId;
							console.log("URI", URI);
							GenericService
									.serviceAction("GET", URI, data)
									.then(
											function(response) {
												$scope.listFormulaDetails  = response.data.listObject;
											});
						} catch (e) {
							console.log(e.message)
						}

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
							$scope.getFormulaMasterList($scope.orgId,$scope.orgUnitId, $scope.pager.startIndex, $scope.pager.pageSize);
						}
					}
					
					
					$scope.setNoOfRecords = function() {
						$scope.initFormulaMasterList($scope.orgId, $scope.orgUnitId, $scope.offset, $scope.noOfRecordsPerPage);
					};
					$scope.initFormulaMasterList = function(orgId, orgUnitId, offset, noOfRecordsPerPage)
					{
						$rootScope.startLoader();
						var data = "";
						offset = offset != null ? offset : 0;
						noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage : 10;
						var URI1 = BASE_URL + ROOT_URL + LIS_UNIT + FORMULA_API + S + orgId + S + orgUnitId + S + offset
								+ S + noOfRecordsPerPage;
						var URI2 = BASE_URL + ROOT_URL + LIS_UNIT + FORMULA_API + S + orgId + S + orgUnitId;
						console.log("URI1",URI1);
						console.log("URI2",URI1);
						promiseFactory.setPromises(URI1, URI2, "GET", "GET").then(function(response) 
						{
							$rootScope.stopLoader();
							$scope.commonList = response[0].listObject;
							$scope.commonListCount = response[1].object;
							console.log("$scope.commonList",$scope.commonList);
							console.log("$scope.commonListCount",$scope.commonListCount);
							$scope.setPage(1, false);
							console.log($scope.commonList);
						});
					}

					$scope.getFormulaMasterList = function(orgId,orgUnitId, offset, noOfRecordsPerPage) {
						$rootScope.startLoader();
						console.log("offset", offset);
						var data = "";
						offset = offset != null ? offset : 0;
						noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage : 10;
						var URI = BASE_URL + ROOT_URL + LIS_UNIT + FORMULA_API + S + orgId + S + orgUnitId +S+ offset
								+ S + noOfRecordsPerPage;
						console.log("URI", URI);
						GenericService.serviceAction("GET", URI, data).then(function(response) {
							$rootScope.stopLoader();
							$scope.commonList = response.data.listObject;
						});
					}
					
					$scope.initFormulaMasterList($scope.orgId, $scope.orgUnitId, $scope.offset, $scope.noOfRecordsPerPage);

					


					// ====================================================CODE END FOR AGE_GROUP MASTER LIST===========================================================
					
					
					
					/**=========================FORMULA_START=======================*/
					 const regex = /^[A-Za-z]{1}$/;
					  var patt = new RegExp(regex);	
						$scope.getVeriables = function()
						{
							 var simplified = math.simplify(math.parse($scope.formulaMaster.formula));
							 var simplifiedString = simplified.toString();
							 console.log("simplifiedString",simplifiedString);
							 var stringArray = simplifiedString.split(/[\s()*/%+-,]+/g);
							 $scope.array = [];
							 for (var i = 0; i < stringArray.length; i++) 
							 {
								var char = stringArray[i];
								var res = patt.test(char);
								if(res==true)
									{
									   var found = $scope.array.find(ar=>ar==char);
									   if(found==undefined)
										   {
										      $scope.array.push(char);
										   }
									}
								
							 }
							 console.log("array",JSON.stringify( $scope.array));
						}	
							
						$scope.generateFormula = function()
						{
							$scope.format="";

							  /*console.log(simplified.eval({Y: 125,C: 1,D: 5}));  // 12
								var res =simplified.eval({Y: 125,C: 1,D: 5});
								  $scope.result=res;*/

							  if($scope.formulaMaster.formula!=''&&$scope.formulaMaster.formula!=null)
								  {
								  $scope.formulaMaster.formulaLatex = math.parse($scope.formulaMaster.formula).toTex();
								  try 
								  {
								      katex.render($scope.formulaMaster.formulaLatex, $scope.format);
								     // $scope.format  = $scope.texexp ;
								      $scope.getVeriables();
								  } catch (err) 
								  {
								     // $scope.format  = $scope.texexp ;
								      $scope.getVeriables();
								  }
								 
								  }
					
						}
						/**=========================FORMULA_END=======================*/
					
					
					
					
					$scope.showModal = function()
					{
						 angular.forEach($scope.array, function(value, key) 
						   {
							 
							 var formulaDetails = angular.copy($scope.formulaDetails);
							 formulaDetails.paramVariable = value;
							 $scope.formulaMaster.listFormulaDetails.push(formulaDetails);
							});
						$('#FormulaDetailsModal').modal('show');
					}
					$scope.showFormulaDetails = function(formulaId)
					{
						$scope.getFormulaDetails(formulaId);
						$('#viewFormulaDetails').modal('show');
					}
					
					
					$scope.updateStatus = function(formulaId,status)
					{
						try { 
							$rootScope.startLoader();
							var URI = BASE_URL + ROOT_URL + LIS_UNIT+FORMULA_STATUS_API+S+formulaId+S+$scope.orgId+S+$scope.orgUnitId+S+status;
							console.log("ACT_INACT_URI",URI);
							GenericService.serviceAction("GET", URI, $scope.formulaMaster).then(function(response) 
							{
								if (response.data.status == 'success')
								{
									$rootScope.stopLoader();
									growl.success(response.data.message,
											{
												title : 'Success!'
											});
									$scope.$broadcast('show-errors-reset');
								} else if(response.data.status == 'error')
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
					
					
					$scope.save = function() 
					{
						//$scope.$broadcast('show-errors-check-validity');
						//{
							try {
								$rootScope.startLoader();
								$scope.$broadcast('show-errors-reset');
								console.log("formulaMaster",JSON.stringify($scope.formulaMaster));
								var URI = BASE_URL + ROOT_URL + LIS_UNIT+FORMULA_API;
								GenericService.serviceAction("POST", URI, $scope.formulaMaster).then(function(response) 
								{	
									if (response.data.status == 'success')
									{
										$rootScope.stopLoader();
										growl.success(response.data.message,
												{
													title : 'Success!'
												});
										$scope.$broadcast('show-errors-reset');
										$scope.initFormulaMasterList($scope.orgId,$scope.orgUnitId, $scope.offset, $scope.noOfRecordsPerPage);
									} else if(response.data.status == 'error')
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
						//}
							
					}
					

				
					$scope.showUpdateBtn = function(formulaId)

					{
						try {
							$rootScope.startLoader();
							$scope.saveBtnFlag = false;
							$scope.updateBtnFlag = true;
							var URI = BASE_URL + ROOT_URL + LIS_UNIT + FORMULA_API +S+formulaId+S+$scope.orgId+S+$scope.orgUnitId;

							console.log("EDIT",URI);
							GenericService.serviceAction("GET", URI, $scope.formulaMaster).then(function(response) 
							{
								console.log("data",response.data);
								
								if (response.data.status == 'success')
								{
									
									$rootScope.stopLoader();
									$scope.formulaMaster = response.data.object;
								} else if(response.data.status == 'error')
								{
									$rootScope.stopLoader();
									alert("Error In Fetching Data");
								}
							});
						} catch (e) {
							console.log("Exception",e.message);
						}
						
					}
					

				

					$scope.update = function() 
					{
						$scope.$broadcast('show-errors-check-validity');
					/*	if ($scope.profilePanelform.$valid)
						{*/
							try {
								$rootScope.startLoader();
								console.log("formulaMaster",JSON.stringify($scope.formulaMaster));
								var URI = BASE_URL + ROOT_URL + LIS_UNIT + FORMULA_API ;
							  GenericService.serviceAction("PUT", URI,$scope.formulaMaster).then(function(response) 
								{
									if (response.data.status == 'success')
									{
										$rootScope.startLoader();
										growl.success(response.data.message,
												{
													title : 'Success!'
												});
										$scope.$broadcast('show-errors-reset');
										$scope.initFormulaMasterList($scope.orgId, $scope.orgUnitId, $scope.offset, $scope.noOfRecordsPerPage);
									} else if(response.data.status == 'error')
									{
										$rootScope.stopLoader();
										growl.error(response.data.message,
												{
													title : 'Error!'
												});
									}
								});
							} catch (e) 
							{
								console.log("Exception",e.message);
							}
							
						/*}*/
					}
					
					$scope.showpopup = function()
					{
						$('#maths-operation-modal').modal('show');
					}
					
					
				
				 
			$scope.forumlaObj = {
					"name":"",
					"latexValue":""
					
			}
				
			$scope.forumlaObjList = [];
			
			$scope.mathSymbols = function()
			{
				var forumlaObj1 = angular.copy($scope.forumlaObj);
				forumlaObj1.name = 'sqrt(x)';
				forumlaObj1.latexValue = '\\sqrt{\\blue{[?]}\\phantom{\\tiny{!}}}';	
				$scope.forumlaObjList.push(forumlaObj1);
				
				var forumlaObj2 = angular.copy($scope.forumlaObj);
				forumlaObj2.name = 'sum(x,y,....)';
				forumlaObj2.latexValue = '\\displaystyle\\sum_{}\\blue{[?]}';	
				$scope.forumlaObjList.push(forumlaObj2);
				
				
				var forumlaObj3 = angular.copy($scope.forumlaObj);
				forumlaObj3.name = 'factorial(x)';
				forumlaObj3.latexValue = '\\blue{[?]}!';	
				$scope.forumlaObjList.push(forumlaObj3);
				
				
				var forumlaObj4 = angular.copy($scope.forumlaObj);
				forumlaObj4.name = 'nthRoot(x, y)';
				forumlaObj4.latexValue = '\\sqrt[\\blue{[?]}]{\\blue{[?]}\\phantom{\\tiny{!}}}';	
				$scope.forumlaObjList.push(forumlaObj4);
				
				
				var forumlaObj5 = angular.copy($scope.forumlaObj);
				forumlaObj5.name = 'pow(x,y)';
				forumlaObj5.latexValue = '{\\blue{[?]}}^{\\blue{[?]}}';	
				$scope.forumlaObjList.push(forumlaObj5);
				
				
				var forumlaObj6 = angular.copy($scope.forumlaObj);
				forumlaObj6.name = 'sqrt(x)';
				forumlaObj6.latexValue = '\\sqrt{\\blue{[?]}\\phantom{\\tiny{!}}}';	
				$scope.forumlaObjList.push(forumlaObj6);
				
				
				var forumlaObj7 = angular.copy($scope.forumlaObj);
				forumlaObj7.name = 'abs';
				forumlaObj7.latexValue = '\\left|\\blue{[?]}\\right|';	
				$scope.forumlaObjList.push(forumlaObj7);
				
				
				var forumlaObj11 = angular.copy($scope.forumlaObj);
				forumlaObj11.name = 'Pi';
				forumlaObj11.latexValue = '\\Pi';	
				$scope.forumlaObjList.push(forumlaObj11);
				
				
				var forumlaObj8 = angular.copy($scope.forumlaObj);
				forumlaObj8.name = '*';
				forumlaObj8.latexValue = '\\cdot';	
				$scope.forumlaObjList.push(forumlaObj8);
				
				
				var forumlaObj10 = angular.copy($scope.forumlaObj);
				forumlaObj10.name = 'ln(x)';
				forumlaObj10.latexValue = '\\ln\\left(\\blue{[?]}\\right)';	
				$scope.forumlaObjList.push(forumlaObj10);
				
				
				var forumlaObj9 = angular.copy($scope.forumlaObj);
				forumlaObj9.name = 'log(x)';
				forumlaObj9.latexValue = '\\log\\left(\\blue{[?]}\\right)';	
				$scope.forumlaObjList.push(forumlaObj9);
				
			}
			$scope.mathSymbols();	
				

					
				} ]);