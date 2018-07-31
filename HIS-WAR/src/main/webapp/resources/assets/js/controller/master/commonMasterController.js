/**
 * @Author By  Vivek Satle
 * @name myApp.controller:commonMasterController
 * @description #commonMasterController Controller of the myApp
 */

 angular.module('myApp').controller("commonMasterController",['$scope','$rootScope','$state','$cookies','GenericService','$sessionStorage',
		'growl',
    function($scope,$rootScope,$state, $cookies, GenericService,$sessionStorage,growl)
	{
	//alert("Test");
	 $scope.init = function()
	 {
	
	    $rootScope.loginpage= true;
		$scope.LISDynamicLabel = $cookies.get('selectedPageName');
		$scope.selectedPageNameForAdmissionNote = $cookies.get('selectedPageNameForAdmissionNote');
		//$scope.commonType=$scope.LISDynamicLabel;
		$rootScope.commonType =sessionStorage.getItem("commonType");
		//alert($rootScope.commonType);
		/*
		 * $scope.unitId =
		 * sessionStorage.getItem("unitId");
		 * $scope.organizationId =
		 * sessionStorage.getItem("organizationId");
		 * $scope.userId =
		 * sessionStorage.getItem("userId");
		 */
		$scope.unitId = 1;
		$scope.organizationId = 1;
		$scope.userId = 1;
		if ($scope.userId == null) {
			$rootScope.loginpage = false;
			$state.go('login');
		}
		
		 
		 $scope.dateOptions = {
			      formatYear: 'yyyy',
			      showWeeks: false
			    };
		 $scope.openDatePicker = function() {
			// alert("in");
		      $scope.datepicker.opened = true;
		    };

		    $scope.datepicker = {
		      opened: false
		    };
		    
		    $scope.openDatePickerForNewDOA = function() {
				// alert("in");
			      $scope.datepickerForNewDOA.opened = true;
			    };
			   
			    $scope.datepickerForNewDOA = {
					      opened: false
					    };
			    
			    $scope.saveBtnFlag = false;
				$scope.updateBtnFlag = true;
				
				$scope.common = {}
				
			 // for popup
				$scope.showModal = false;
				$scope.buttonClicked = "";
				$scope.popUpFlag = true;
				// end for popup
				
				var data = {};
				
				 switch ($rootScope.commonType) {
		            case 'sexmaster':
		            	
		            	// $scope.status = "NO";
						var URI = BASE_URL + ROOT_URL
								+ GETSEXLIST;
						GenericService
								.serviceAction("GET", URI, data)
								.then(
										function(response) {
											$scope.commonList = [];

											if (response.data.status == "success") {
												$scope.commonList = response.data.listObject;

											}
										});
		                break;
		            case 'relationmaster':
		            	var URI = BASE_URL + ROOT_URL
						+ GETRELATIONLIST;
				GenericService
						.serviceAction("GET", URI, data)
						.then(
								function(response) {
									$scope.commonList = [];

									if (response.data.status == "success") {
										$scope.commonList = response.data.listObject;

									}
								});
		                break;
		            case 'maritalstatus':
		            	var URI = BASE_URL + ROOT_URL
						+ GETMARITALSTATUSLIST;
				GenericService
						.serviceAction("GET", URI, data)
						.then(
								function(response) {
									$scope.commonList = [];

									if (response.data.status == "success") {
										$scope.commonList = response.data.listObject;

									}
								});
		            	break;
		            case 'religion':
		            	var URI = BASE_URL + ROOT_URL+ GETRELIGIONLIST;
						GenericService
						.serviceAction("GET", URI, data)
								.then(
										function(response) {
											$scope.commonList = [];

											if (response.data.status == "success") {
												$scope.commonList = response.data.listObject;

											}
										});
		            	break;
		            case 'occupation':
		            	var URI = BASE_URL + ROOT_URL+ GETOCCUPATIONLIST;
						GenericService
						.serviceAction("GET", URI, data)
								.then(
										function(response) {
											$scope.commonList = [];

											if (response.data.status == "success") {
												$scope.commonList = response.data.listObject;

											}
										});
		            	break;
		            case 'patientsource':
		            	var URI = BASE_URL + ROOT_URL+ GETPATIENTSOURCELIST;
						GenericService
						.serviceAction("GET", URI, data)
								.then(
										function(response) {
											$scope.commonList = [];

											if (response.data.status == "success") {
												$scope.commonList = response.data.listObject;

											}
										});
		            	break;
		            case 'referraltype':
		            	var URI = BASE_URL + ROOT_URL+ GETREFERRALTYPELIST;
						GenericService
						.serviceAction("GET", URI, data)
								.then(
										function(response) {
											$scope.commonList = [];

											if (response.data.status == "success") {
												$scope.commonList = response.data.listObject;

											}
										});
		            	break;
		            default:

		        }
				 
				
				 
	 }
	 $scope.init();
				// function for popup ok button call
				$scope.PopupOkBtn = function() {
					$scope.popUpFlag = false;
					$scope.save();
				}
				
				$scope.save = function()
				{
					var commonTypeStr = $rootScope.commonType;
					$scope.$broadcast('show-errors-check-validity');
					if ($scope.commonform.$valid) {
						 if ($scope.popUpFlag) {
								$scope.showModal = !$scope.showModal;

							} else {
								var data = {
										code : $scope.common.commonCode,
										desc : $scope.common.commonDesc,
										status : 'A',
										createdBy:$scope.userId,
										updatedBy:$scope.userId,
										organizationId:$scope.organizationId
									}
									
								
								 switch (commonTypeStr) {
						            case 'sexmaster':
						            
										console.log(data);								
										var URI = BASE_URL + ROOT_URL + ADDSEX;
										GenericService
												.serviceAction("POST", URI, data)
												.then(
														function(response) {

															if (response.data.status == "success") {
																growl
																		.success(
																				'Sex added sucessfully!!!.',
																				{
																					title : 'Success!'
																				});
																
																$scope.$broadcast('show-errors-reset');
																  $scope.init();
															} else {
																growl
																		.error(
																				'Sex already exits!!!!.',
																				{
																					title : 'Error!'
																				});
															}
														});
						                break;
						            case 'relationmaster':
						            
											console.log(data);								
											var URI = BASE_URL + ROOT_URL + ADDRELATION;
											GenericService
													.serviceAction("POST", URI, data)
													.then(
															function(response) {

																if (response.data.status == "success") {
																	growl
																			.success(
																					'Relation added sucessfully!!!.',
																					{
																						title : 'Success!'
																					});
																	
																	$scope.$broadcast('show-errors-reset');
																	  $scope.init();
																} else {
																	growl
																			.error(
																					'Relation already exits!!!!.',
																					{
																						title : 'Error!'
																					});
																}
															});
						                break;
						            case 'maritalstatus':
						            
										console.log(data);								
										var URI = BASE_URL + ROOT_URL + ADDMARITALSTATUS;
										GenericService
												.serviceAction("POST", URI, data)
												.then(
														function(response) {

															if (response.data.status == "success") {
																growl
																		.success(
																				'Marital Status added sucessfully!!!.',
																				{
																					title : 'Success!'
																				});
																
																$scope.$broadcast('show-errors-reset');
																  $scope.init();
															} else {
																growl
																		.error(
																				'Marital Status already exits!!!!.',
																				{
																					title : 'Error!'
																				});
															}
														});
						            	break;
						            case 'religion':
						            
										console.log(data);								
										var URI = BASE_URL + ROOT_URL + ADDRELIGION;
										GenericService
												.serviceAction("POST", URI, data)
												.then(
														function(response) {

															if (response.data.status == "success") {
																growl
																		.success(
																				'Religion added sucessfully!!!.',
																				{
																					title : 'Success!'
																				});
																
																$scope.$broadcast('show-errors-reset');
																  $scope.init();
															} else {
																growl
																		.error(
																				'Religion already exits!!!!.',
																				{
																					title : 'Error!'
																				});
															}
														});
						            	break;
						            case 'occupation':
						            
									console.log(data);								
									var URI = BASE_URL + ROOT_URL + ADDOCCUPATION;
									GenericService
											.serviceAction("POST", URI, data)
											.then(
													function(response) {

														if (response.data.status == "success") {
															growl
																	.success(
																			'Occupation added sucessfully!!!.',
																			{
																				title : 'Success!'
																			});
															
															$scope.$broadcast('show-errors-reset');
															  $scope.init();
														} else {
															growl
																	.error(
																			'Occupation already exits!!!!.',
																			{
																				title : 'Error!'
																			});
														}
													});
						            	break;
						            case 'patientsource':
						            	
									console.log(data);								
									var URI = BASE_URL + ROOT_URL + ADDPATIENTSOURCE;
									GenericService
											.serviceAction("POST", URI, data)
											.then(
													function(response) {

														if (response.data.status == "success") {
															growl
																	.success(
																			'Patientsource added sucessfully!!!.',
																			{
																				title : 'Success!'
																			});
															
															$scope.$broadcast('show-errors-reset');
															  $scope.init();
														} else {
															growl
																	.error(
																			'Patientsource already exits!!!!.',
																			{
																				title : 'Error!'
																			});
														}
													});
						            	break;
						            case 'referraltype':
						           
									console.log(data);								
									var URI = BASE_URL + ROOT_URL + ADDREFERRALTYPE;
									GenericService
											.serviceAction("POST", URI, data)
											.then(
													function(response) {

														if (response.data.status == "success") {
															growl
																	.success(
																			'ReferralType added sucessfully!!!.',
																			{
																				title : 'Success!'
																			});
															
															$scope.$broadcast('show-errors-reset');
															  $scope.init();
														} else {
															growl
																	.error(
																			'ReferralType already exits!!!!.',
																			{
																				title : 'Error!'
																			});
														}
													});
						            	break;
						            default:

						        }
								
				
							}
					}
				}
				
				//function for checked status
				$scope.checkVal = function(statuspatientsource) {
					$scope.status = (statuspatientsource == "A") ? "A"
							: "I";
					return (statuspatientsource == "A") ? true : false;
				}
				
				$scope.updateStatus = function(id, type) {
					
					var commonTypeStr = $rootScope.commonType;
					
					type = (type == "A") ? "A" : "I";
					
					var data = {
							id : id,
							status : type,
							updatedBy:$scope.userId
						}
						
					
					 switch (commonTypeStr) {
			            case 'sexmaster':
			            	console.log(data);
							var URI = BASE_URL + ROOT_URL
									+ UPDATESEXSTATUS;
							GenericService
									.serviceAction("POST", URI, data)
									.then(
											function(response) {

												if (response.data.status == "success") {
													growl
															.success(
																	'Sex status changed sucessfully!!!.',
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
			                break;
			            case 'relationmaster':
			            
			            
							console.log(data);
							var URI = BASE_URL + ROOT_URL
									+ UPDATERELATIONSTATUS;
							GenericService
									.serviceAction("POST", URI, data)
									.then(
											function(response) {

												if (response.data.status == "success") {
													growl
															.success(
																	'Relation status changed sucessfully!!!.',
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
			                break;
			            case 'maritalstatus':
			            
							console.log(data);
							var URI = BASE_URL + ROOT_URL
									+ UPDATEMARITALSTATUS;
							GenericService
									.serviceAction("POST", URI, data)
									.then(
											function(response) {

												if (response.data.status == "success") {
													growl
															.success(
																	'Marital status changed sucessfully!!!.',
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
			            	break;
			            case 'religion':
			            
							console.log(data);
							var URI = BASE_URL + ROOT_URL
									+ UPDATERELIGIONSTATUS;
							GenericService
									.serviceAction("POST", URI, data)
									.then(
											function(response) {

												if (response.data.status == "success") {
													growl
															.success(
																	'Religion status changed sucessfully!!!.',
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
			            	break;
			            case 'occupation':
			            
							console.log(data);
							var URI = BASE_URL + ROOT_URL
									+ UPDATEOCCUPATIONSTATUS;
							GenericService
									.serviceAction("POST", URI, data)
									.then(
											function(response) {

												if (response.data.status == "success") {
													growl
															.success(
																	'Occupation status changed sucessfully!!!.',
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
			            	break;
			            case 'patientsource':
			          
						console.log(data);
						var URI = BASE_URL + ROOT_URL
								+ UPDATEPATIENTSOURCESTATUS;
						GenericService
								.serviceAction("POST", URI, data)
								.then(
										function(response) {

											if (response.data.status == "success") {
												growl
														.success(
																'Patientsource status changed sucessfully!!!.',
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
			            	break;
			            case 'referraltype':
			            
						console.log(data);
						var URI = BASE_URL + ROOT_URL
								+ UPDATEREFERRALTYPESTATUS;
						GenericService
								.serviceAction("POST", URI, data)
								.then(
										function(response) {

											if (response.data.status == "success") {
												growl
														.success(
																'Referraltype status changed sucessfully!!!.',
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
			            	break;
			            default:

			        }
					
				}
				
				// function for get speciality object for update
				$scope.showUpdateBtn = function(id) {

					var commonTypeStr = $rootScope.commonType;
					
					$scope.saveBtnFlag = true;
					$scope.updateBtnFlag = false;
					
					var data = {
							id : id
						}
					
					 switch (commonTypeStr) {
			            case 'sexmaster':
			            	
							console.log(data);
							var URI = BASE_URL + ROOT_URL
									+ GETSINGLESEX;
							GenericService
									.serviceAction("POST", URI, data)
									.then(
											function(response) {
												if (response.data.status == "success") {
													$scope.common.incremetedId = response.data.listObject[0].id;
													$scope.common.commonCode = response.data.listObject[0].code;
													$scope.common.commonDesc  =  response.data.listObject[0].desc;
												} else {
													alert("Error!!");
												}
											});
			                break;
			            case 'relationmaster':
			            
							console.log(data);
							var URI = BASE_URL + ROOT_URL
									+ GETSINGLERELATION;
							GenericService
									.serviceAction("POST", URI, data)
									.then(
											function(response) {
												if (response.data.status == "success") {
													$scope.common.incremetedId = response.data.listObject[0].id;
													$scope.common.commonCode = response.data.listObject[0].code;
													$scope.common.commonDesc  =  response.data.listObject[0].desc;
												} else {
													alert("Error!!");
												}
											});
			                break;
			            case 'maritalstatus':
			            
							console.log(data);
							var URI = BASE_URL + ROOT_URL
									+ GETSINGLEMARITALSTATUS;
							GenericService
									.serviceAction("POST", URI, data)
									.then(
											function(response) {
												if (response.data.status == "success") {
													$scope.common.incremetedId = response.data.listObject[0].id;
													$scope.common.commonCode = response.data.listObject[0].code;
													$scope.common.commonDesc  =  response.data.listObject[0].desc;
													
												} else {
													alert("Error!!");
												}
											});
			            	break;
			            case 'religion':
			            
							console.log(data);
							var URI = BASE_URL + ROOT_URL
									+ GETSINGLERELIGION;
							GenericService
									.serviceAction("POST", URI, data)
									.then(
											function(response) {
												if (response.data.status == "success") {
													$scope.common.incremetedId = response.data.listObject[0].id;
													$scope.common.commonCode = response.data.listObject[0].code;
													$scope.common.commonDesc = response.data.listObject[0].desc;
												} else {
													alert("Error!!");
												}
											});
			            	break;
			            case 'occupation':
			            
						console.log(data);
						var URI = BASE_URL + ROOT_URL
								+ GETSINGLEOCCUPATION;
						GenericService
								.serviceAction("POST", URI, data)
								.then(
										function(response) {
											if (response.data.status == "success") {
												$scope.common.incremetedId = response.data.listObject[0].id;
												$scope.common.commonCode = response.data.listObject[0].code;
												$scope.common.commonDesc = response.data.listObject[0].desc;
												
											} else {
												alert("Error!!");
											}
										});
			            	break;
			            case 'patientsource':
			           
						console.log(data);
						var URI = BASE_URL + ROOT_URL
								+ GETSINGLEPATIENTSOURCE;
						GenericService
								.serviceAction("POST", URI, data)
								.then(
										function(response) {
											if (response.data.status == "success") {
												$scope.common.incremetedId = response.data.listObject[0].id;
												$scope.common.commonCode = response.data.listObject[0].code;
												$scope.common.commonDesc = response.data.listObject[0].desc;
											} else {
												alert("Error!!");
											}
										});
			            	break;
			            case 'referraltype':
			            
						console.log(data);
						var URI = BASE_URL + ROOT_URL
								+ GETSINGLEREFERRALTYPE;
						GenericService
								.serviceAction("POST", URI, data)
								.then(
										function(response) {
											if (response.data.status == "success") {
												$scope.common.incremetedId = response.data.listObject[0].id;
												$scope.common.commonCode = response.data.listObject[0].code;
												$scope.common.commonDesc = response.data.listObject[0].desc;
											} else {
												alert("Error!!");
											}
										});
			            	break;
			            default:

			        }
					 

					

				};
				
				
				$scope.update = function() {
					var commonTypeStr = $rootScope.commonType;
					$scope.$broadcast('show-errors-check-validity');
					if ($scope.commonform.$valid) 
				{
						var data = {
								code : $scope.common.commonCode,
								desc : $scope.common.commonDesc,
								updatedBy:$scope.userId,
								id:$scope.common.incremetedId
							}
						
						 switch (commonTypeStr) {
				            case 'sexmaster':

								
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ UPDATESEX;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {

													if (response.data.status == "success") {
														growl
																.success(
																		'Sex updated sucessfully!!!.',
																		{
																			title : 'Success!'
																		});
														$scope.$broadcast('show-errors-reset');
														  $scope.init();
														// alert("ds");

													} else {
														growl
																.error(
																		'Sex already exits!!!!.',
																		{
																			title : 'Error!'
																		});
													}
												});
				                break;
				            case 'relationmaster':
				            
				            	
							console.log(data);
							var URI = BASE_URL + ROOT_URL
									+ UPDATERELATION;
							GenericService
									.serviceAction("POST", URI, data)
									.then(
											function(response) {

												if (response.data.status == "success") {
													growl
															.success(
																	'Relation updated sucessfully!!!.',
																	{
																		title : 'Success!'
																	});
													$scope.$broadcast('show-errors-reset');
													  $scope.init();
													// alert("ds");

												} else {
													growl
															.error(
																	'Relation already exits!!!!.',
																	{
																		title : 'Error!'
																	});
												}
											});
				                break;
				            case 'maritalstatus':
				            	
				            	
							console.log(data);
							var URI = BASE_URL + ROOT_URL
									+ UPDATEMARITAL;
							GenericService
									.serviceAction("POST", URI, data)
									.then(
											function(response) {

												if (response.data.status == "success") {
													growl
															.success(
																	'Marital Status updated sucessfully!!!.',
																	{
																		title : 'Success!'
																	});
													$scope.$broadcast('show-errors-reset');
													  $scope.init();
													// alert("ds");

												} else {
													growl
															.error(
																	'Marital Status already exits!!!!.',
																	{
																		title : 'Error!'
																	});
												}
											});
				            	break;
				            case 'religion':
				          
							console.log(data);
							var URI = BASE_URL + ROOT_URL
									+ UPDATERELIGION;
							GenericService
									.serviceAction("POST", URI, data)
									.then(
											function(response) {

												if (response.data.status == "success") {
													growl
															.success(
																	'Religion updated sucessfully!!!.',
																	{
																		title : 'Success!'
																	});
													$scope.$broadcast('show-errors-reset');
													  $scope.init();
													// alert("ds");

												} else {
													growl
															.error(
																	'Religion already exits!!!!.',
																	{
																		title : 'Error!'
																	});
												}
											});
				            	break;
				            case 'occupation':
				          
							var URI = BASE_URL + ROOT_URL
									+ UPDATEOCCUPATION;
							GenericService
									.serviceAction("POST", URI, data)
									.then(
											function(response) {

												if (response.data.status == "success") {
													growl
															.success(
																	'Occupation updated sucessfully!!!.',
																	{
																		title : 'Success!'
																	});
													$scope.$broadcast('show-errors-reset');
													  $scope.init();
													// alert("ds");

												} else {
													growl
															.error(
																	'Occupation already exits!!!!.',
																	{
																		title : 'Error!'
																	});
												}
											});
				            	break;
				            case 'patientsource':
				         
							console.log(data);
							var URI = BASE_URL + ROOT_URL
									+ UPDATEPATIENTSOURCE;
							GenericService
									.serviceAction("POST", URI, data)
									.then(
											function(response) {

												if (response.data.status == "success") {
													growl
															.success(
																	'Patientsource updated sucessfully!!!.',
																	{
																		title : 'Success!'
																	});
													$scope.$broadcast('show-errors-reset');
													  $scope.init();
													// alert("ds");

												} else {
													growl
															.error(
																	'Patientsource already exits!!!!.',
																	{
																		title : 'Error!'
																	});
												}
											});
				            	break;
				            case 'referraltype':
				          
							var URI = BASE_URL + ROOT_URL
									+ UPDATEREFERRALTYPE;
							GenericService
									.serviceAction("POST", URI, data)
									.then(
											function(response) {

												if (response.data.status == "success") {
													growl
															.success(
																	'Referraltype updated sucessfully!!!.',
																	{
																		title : 'Success!'
																	});
													$scope.$broadcast('show-errors-reset');
													  $scope.init();
													// alert("ds");

												} else {
													growl
															.error(
																	'Referraltype already exits!!!!.',
																	{
																		title : 'Error!'
																	});
												}
											});
				            	break;
				            default:

				        }
					
				}
				}
			    
	
/***** checkbox list department master unit start *****/
    $scope.roles = [{
        roleId: 1,
        roleName: "Administrator"
    }, {
        roleId: 2,
        roleName: "Super User"
    }];

    $scope.user = {
        userId: 1,
        username: "JimBob",
        roles: [$scope.roles[0]]
    };
/***** checkbox list department master unit end *****/
    
    $rootScope.$on('customEvent', function(event, message) {
    	$scope.LISDynamicLabel = message;
    	
    	var pageFlag = message.toLowerCase();//$(".LISDynamicLabel").html().toLowerCase();
    	if(pageFlag == "department")
    	{
    		 $(".LISDynamicLabel").text("Department");
    		 $(".selectedPageName").text("Department Master");
    	
    	}
    	else if(pageFlag == "phlebotomy")
    	{
    		$(".LISDynamicLabel").text("Phlebotomy");
    		$(".selectedPageName").text("Phlebotomy Master");
    	
    	}
    	else if(pageFlag == "container")
    	{
    		$(".LISDynamicLabel").text("Container");
    		$(".selectedPageName").text("Container Master");
    	
    	}
    	else if(pageFlag == "sample")
    	{
    		$(".LISDynamicLabel").text("Sample");
    		$(".selectedPageName").text("Sample Master");
    		
    	}
    	else if(pageFlag == "report")
    	{
    		$(".LISDynamicLabel").text("Report Type");	
    		$(".selectedPageName").text("Report Type Master");
    		
    	}
    	else if(pageFlag == "techinque")
    	{
    		$(".LISDynamicLabel").text("Techinque");
    		$(".selectedPageName").text("Techinque Master");
    		
    	}
    	else if(pageFlag == "prerequisites")
    	{
    		$(".LISDynamicLabel").text("Prerequisites");	
    		$(".selectedPageName").text("Prerequisites Master");
    	
    	}
    	/* else if(pageFlag == "ageGroup")
    	{
    		$(".LISDynamicLabel").text("Age Group");
    		$(".selectedPageName").text("Age Group Master");
    	} */
    	else if(pageFlag == "unit")
    	{
    		$(".LISDynamicLabel").text("Unit");	
    		$(".selectedPageName").text("Unit Master");
    		
    	}
    	else if(pageFlag == "priority")
    	{
    		$(".LISDynamicLabel").text("Priority");	
    		$(".selectedPageName").text("Priority Master");
    		
    	}
    	else if(pageFlag == "regent")
    	{
    		$(".LISDynamicLabel").text("Reagent");	
    		$(".selectedPageName").text("Reagent Master");
    		
    	
    	}
    	else if(pageFlag == "bacteria")
    	{
    		$(".LISDynamicLabel").text("Bacteria shape");	
    		$(".selectedPageName").text("Bacteria Shape Master");
    		
    	}
    	else if(pageFlag == "microbiology")
    	{
    		$(".LISDynamicLabel").text("Microbiology Strength");	
    		$(".selectedPageName").text("Microbiology Strength Master");
    		
    	}
    	else if(pageFlag == "wetfilmstudy")
    	{
    		$(".LISDynamicLabel").text("Wet Film Study");	
    		$(".selectedPageName").text("Wet Film Study Master");
    		
    	}
    	/* *******************Billing Master********************* */
    	else if(pageFlag == "payment master")
    	{
    		$(".LISDynamicLabel").text("Payment Entitlement");	
    		$(".selectedPageName").text("Payment Entitlement Master");
    	
    	}
    	else if(pageFlag == "patient category master")
    	{
    		$(".LISDynamicLabel").text("Patient Category");	
    		$(".selectedPageName").text("Patient Category Master");
    	
    	}
    	else if(pageFlag == "payment mode master")
    	{
    		$(".LISDynamicLabel").text("Payment Mode");	
    		$(".selectedPageName").text("Payment Mode Master");
    	
    	}
    	else if(pageFlag == "visit type master")
    	{
    		$(".LISDynamicLabel").text("Visit Type");	
    		$(".selectedPageName").text("Visit Type Master");
    	
    	}
    	else if(pageFlag == "bed type master")
    	{
    		$(".LISDynamicLabel").text("Bed Type");	
    		$(".selectedPageName").text("Bed Type Master");

    	}
    	else if(pageFlag == "discount reason master")
    	{
    		$(".LISDynamicLabel").text("Discount Reason");	
    		$(".selectedPageName").text("Discount Reason Master");
    	
    	}
    	else if(pageFlag == "billing department master")
    	{
    		$(".LISDynamicLabel").text("Department");	
    		$(".selectedPageName").text("Department Master");
    	
    	}
    	else if(pageFlag == "equipment master")
    	{
    		$(".LISDynamicLabel").text("Equipment ");	
    		$(".selectedPageName").text("Equipment Master");
    	
    	}
    	else if(pageFlag == "consultant master")
    	{
    		$(".LISDynamicLabel").text("Consultant Type");	
    		$(".selectedPageName").text("Consultant Type Master");

    	}
    	/********************Pharmacy & Intventory Controll Master**********************/
    	else if(pageFlag == "pharmacy product master")
    	{
    		$(".LISDynamicLabel").text("Product Category");	
    		$(".selectedPageName").text("Product Category Master");
    	
    	}
    	else if(pageFlag == "product type master")
    	{
    		$(".LISDynamicLabel").text("Product Type");	
    		$(".selectedPageName").text("Product Type Master");
    	
    	}
    	else if(pageFlag == "product strength master")
    	{
    		$(".LISDynamicLabel").text("Product Strength");	
    		$(".selectedPageName").text("Product Strength Master");
    	
    	}
    	else if(pageFlag == "admin route master")
    	{
    		$(".LISDynamicLabel").text("Route of Administration");	
    		$(".selectedPageName").text("Administration Route Master");
    		
    	}
    	else if(pageFlag == "frequency master")
    	{
    		$(".LISDynamicLabel").text("Frequency");	
    		$(".selectedPageName").text("Frequency Master");
    		
    	}
    	else if(pageFlag == "schedule master")
    	{
    		$(".LISDynamicLabel").text("Schedule");	
    		$(".selectedPageName").text("Schedule Master");
    	
    	}
    	else if(pageFlag == "manufacture master")
    	{
    		$(".LISDynamicLabel").text("Manufacture");	
    		$(".selectedPageName").text("Manufacture Master");
    	
    	}
    	else if(pageFlag == "vendor master")
    	{
    		$(".LISDynamicLabel").text("Vendor");	
    		$(".selectedPageName").text("Vendor Master");
    	
    	}
    	else if(pageFlag == "rack master")
    	{
    		$(".LISDynamicLabel").text("Rack");	
    		$(".selectedPageName").text("Rack Master");
    	
    	}
    	else if(pageFlag == "store master")
    	{
    		$(".LISDynamicLabel").text("Store");	
    		$(".selectedPageName").text("Store Master");
    		
    	}
    	else if(pageFlag == "reason master")
    	{
    		$(".LISDynamicLabel").text("Reason");	
    		$(".selectedPageName").text("Reason Master");
    	
    	}
    	else if(pageFlag == "icutype")
    	{
    		$(".LISDynamicLabelOne").text("ICU Code");	
    		$(".LISDynamicLabel").text("ICU Type");	
    		$(".selectedPageName").text("ICU Type Master");
    	
    	}
    	else if(pageFlag == "identificationmaster")
    	{
    		$(".LISDynamicLabel").text("Identification Card");	
    		$(".selectedPageName").text("Identification Card Master");
    	
    	}
    	else if(pageFlag == "sexmaster")
    	{
    		$(".LISDynamicLabel").text("Sex");	
    		$(".selectedPageName").text("Sex Master");
    		
    	
    	}
    	else if(pageFlag == "relationmaster")
    	{
    		$(".LISDynamicLabel").text("Relation");	
    		$(".selectedPageName").text("Relation Master");
    	
    	}
    	else if(pageFlag == "maritalstatus")
    	{
    		$(".LISDynamicLabel").text("Marital Status");	
    		$(".selectedPageName").text("Marital Status Master");
    	
    	}
    	else if(pageFlag == "religion")
    	{
    		$(".LISDynamicLabel").text("Religion");	
    		$(".selectedPageName").text("Religion Master");
    	
    	}
    	else if(pageFlag == "occupation")
    	{
    		$(".LISDynamicLabel").text("Occupation");	
    		$(".selectedPageName").text("Occupation Master");
    	
    	}
    	else if(pageFlag == "patientsource")
    	{
    		$(".LISDynamicLabel").text("Patient Source");	
    		$(".selectedPageName").text("Patient Source Master");
    	
    	}
    	else if(pageFlag == "referraltype")
    	{
    		$(".LISDynamicLabel").text("Referral Type");	
    		$(".selectedPageName").text("Referral Type Master");
    	
    	}
    	/******************** Unit Master**********************/
    	else if(pageFlag == "Floor")
    	{
    		$(".LISDynamicLabel").text("Floor");	
    		$(".selectedPageName").text("Floor Master");
    	
    	}
      });

			    
	}]);