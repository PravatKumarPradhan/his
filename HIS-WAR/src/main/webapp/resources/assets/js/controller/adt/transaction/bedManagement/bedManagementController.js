'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:bedManagementController
 * @description # bedManagementController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'bedManagementController',
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
						//'kendoDirectives',
						function($scope, $http, $localStorage, $sessionStorage,
								$cookies, $rootScope, GenericService, growl,
								$state) {

							/* init() function for form object create. */
							$scope.init = function() {
								
								$scope.dateOptions = {
										formatYear : 'yyyy',
										showWeeks : false
									};
									$scope.openDatePicker = function() {
										// alert("in");
										$scope.datepicker.opened = true;
									};

									$scope.datepicker = {
										opened : false
									};
								
									$scope.openDatePickerPdd = function() {
										// alert("in");
										$scope.datepickerPdd.opened = true;
									};

									$scope.datepickerPdd = {
										opened : false
									};

									var cookieObject = $cookies.getObject('cookieObject');
									if(cookieObject == undefined){
										$state.go('login');
										return;
									}
									$scope.unitId = cookieObject.unitId;
									$scope.organizationId = cookieObject.organizationId;
									$scope.userId = 1;
								   
								$scope.patientSearchDiv=true;
								$rootScope.linkHideShow=true;
								

								$rootScope.loginpage = true;

								// for popup
								$scope.showModal = false;
								$scope.buttonClicked = "";
								$scope.popUpFlag = true;
								// end for popup
								
								$scope.bedsearch = {
										floorId : 0,
										nursingStationId : 0,
										bedCategoryId : 0,
										wardId : 0,
										patientId : 0,
										tPatientId : 0,
										organizationId : $scope.organizationId,
										unitId : $scope.unitId,

									}

								var data = {};
								var commonObj = {
									unitId : $scope.unitId,
									organizationId : $scope.organizationId
								};
								
								var URI = BASE_URL + ROOT_URL
								+ GETACTIVEBEDCATEGORYLISTBYUNIT;
						GenericService
								.serviceAction("POST", URI, commonObj)
								.then(
										function(response) {
											$scope.bedCategoryList = [];

											if (response.data.status == "success") {
												$scope.bedCategoryList = response.data.listObject;

											}
										});
								
								var URI = BASE_URL + ROOT_URL
								+ GETACTIVEFLOORLIST;
						GenericService
								.serviceAction("POST", URI, commonObj)
								.then(
										function(response) {
											$scope.activeFloorList = [];

											if (response.data.status == "success") {
												$scope.activeFloorList = response.data.listObject;

											}
										});
								
								var URI = BASE_URL + ROOT_URL
								+ GETADMITTEDPATIENTLIST;
						GenericService
								.serviceAction("POST", URI, commonObj)
								.then(
										function(response) {
											//$scope.listOfAdmittedPatientList = [];
											$scope.patientBedList = [];

											if (response.data.status == "success") {
												$scope.patientBedList = response.data.listObject;
												/*angular
														.forEach(
																$scope.admittedPatientList,
																function(
																		value,
																		index) {

																	var newArr = {};
																	newArr.specialityName = value.specialityName;
																	newArr.doctorFullName = value.dFirstName;
																	newArr.patientFullName = value.pFirstName;
																	newArr.reasonDesc = value.reasonDesc;
																	newArr.dob = value.dob;
																	newArr.datepicker = value.doa;
																	newArr.datepickerPdd = value.pdd;
																	newArr.genderCode = value.genderCode;
																	newArr.uhidnumber = value.uhidnumber;
																	newArr.patientId = value.patientId;
																	newArr.tPatientId = value.tPatientId;
																	newArr.paymentEntitlementDesc = value.paymentEntitlementDesc;
																	newArr.bedNumber = value.bedNumber;
																	newArr.admissionId = value.admissionId;
																	newArr.prefixCode = value.prefixCode;
																	newArr.identificationNo = value.identificationNo;
																	$scope.listOfAdmittedPatientList
																			.push(newArr);

																});*/

											}
										});
							}

							$scope.init();
							
							$scope.getAdmittedPatientByIds = function(commonType) {

								console.log($scope.bedsearch);

								var commonData = {
									unitId : $scope.unitId,
									organizationId : $scope.organizationId
								};
								switch (commonType) {
								case 'floor':

									commonData.floorId = $scope.bedsearch.floorId;

									var URI = BASE_URL + ROOT_URL
											+ GETWARDLISTBYFLOORIDORGUNITID;
									GenericService
											.serviceAction("POST", URI,
													commonData)
											.then(
													function(response) {
														$scope.wardList = [];

														if (response.data.status == "success") {
															$scope.wardList = response.data.listObject;

														}
													});
									break;
								case 'ward':

									commonData.wardId = $scope.bedsearch.wardId;

									var URI = BASE_URL
											+ ROOT_URL
											+ GETACTIVENURSINGSTATIONLISTBYWARDID;
									GenericService
											.serviceAction("POST", URI,
													commonData)
											.then(
													function(response) {
														$scope.nursingStationList = [];

														if (response.data.status == "success") {
															$scope.nursingStationList = response.data.listObject;

														}
													});
									break;
								default:

								}

							}
							
							
							$scope.searchGroupList = function(keyword) {

								var data = {
									firstName : keyword,
									unitId : $scope.unitId,
									// visitType :
									// $scope.selectedPageNameForAdmissionNote,
									organizationId : $scope.organizationId
								}
								console.log(data);

								var URI = BASE_URL + ROOT_URL
										+ GETPATIENTBYKEYWORD;
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
							
							$scope.selectPatientIdOnSearch = function(info, model,
									label) {
									if(info.patientId != null)
									$scope.bedsearch.patientId  = info.patientId;
									if(info.tPatientId !=null)
									$scope.bedserach.tPatientId = info.tPatientId;
							}
							
							
							$scope.patientSearchByMultipleCriteria = function() {

								// $scope.bedsearch.isVirtual =
								// ($scope.bedsearch.isVirtual == true) ? "Y":
								// "N";
								console.log($scope.bedsearch);
								//return false;
								var URI = BASE_URL + ROOT_URL
										+ GETADMITTEDPATIENTLISTBYMULTIPLECRITERIA;
								GenericService
										.serviceAction("POST", URI,
												$scope.bedsearch)
										.then(
												function(response) {
													$scope.activeBedList = [];

													if (response.data.status == "success") {
														$scope.patientBedList = response.data.listObject;

														$scope.bedsearch = {
																floorId : 0,
																nursingStationId : 0,
																bedCategoryId : 0,
																wardId : 0,
																patientId : 0,
																tPatientId : 0,
																organizationId : $scope.organizationId,
																unitId : $scope.unitId,

															}
														$scope.groupName = "";

													}
												});

							}
							
							$scope.popupData = function(item)
							{
								console.log(item);
								if(item.patientId > 0)
									{
									 var age = $rootScope.getAge(item.dob);
									
									 $scope.patientFullName =item.pFirstName+' '+age+'/'+item.genderCode; 
									}
								else
									{
									 $scope.patientFullName =item.pFirstName+' '+item.dob+'/'+item.genderCode;
									}
								$scope.uhidNum = item.uhidnumber;
								$scope.dateOfAdmission = item.doa;
								$scope.patientDischargeDate = item.pdd;
								$scope.bedNumber = item.bedNumber;
								$scope.doctorFullName = item.dFirstName;
							
								sessionStorage
								.setItem("admissionNoteObjER",JSON.stringify(item));
								
								var bedManagmentCookiesData = {
										admissionId:item.admissionId,
										type:'bm',
										patientFullName :item.pFirstName+' '+age+'/'+item.genderCode,
										uhidNum:item.uhidnumber,
										bedNumber : item.bedNumber,
										doctorFullName : item.dFirstName,
										wardName : item.wardName,
										patientId : item.patientId,
										tPatientId : item.tPatientId,
										visitTypeId : item.visitTypeId
										};
								
								$cookies.put('bedManagmentCookies',JSON.stringify(bedManagmentCookiesData));
								
								
							}
							
							$scope.callAdmissionNote = function()
							{
								angular
								.element(
										'#patientServices')
								.modal('hide');
								
								$rootScope.getAdmissionNote('ER');
								
							}


							$scope.search = {
								allocationTypeId : 0,
								patientId : 0
							}

							$scope.searchGroupList = function(keyword) {

								var data = {
									firstName : keyword,
									unitId : $scope.unitId,
									// visitType :
									// $scope.selectedPageNameForAdmissionNote,
									organizationId : $scope.organizationId
								}
								console.log(data);

								var URI = BASE_URL + ROOT_URL
										+ GETPATIENTBYKEYWORD;
								return GenericService.serviceAction("POST",
										URI, data).then(function(response) {

									if (response.data.status == "success") {
										return response.data.listObject;

									}
								});

							}

							$scope.selectPatientId = function(info, model,
									label) {
								$scope.search.patientId = info.patientId
							}

							$scope.getERAdmittedPatientByAllocationPatientId = function() {
								var data = {
									organizationId : $scope.organizationId,
									unitId : $scope.unitId,
									allocationType : $scope.search.allocationTypeId,
									patientId : $scope.search.patientId
								};
								console.log(data);
								/*
								 * var URI = BASE_URL + ROOT_URL +
								 * GETERADMITTEDPATIENTLISTBYALLOCATIONPATIENT;
								 * GenericService .serviceAction("POST", URI,
								 * data) .then( function(response) {
								 * $scope.listOfAdmittedPatientList = [];
								 * $scope.admittedPatientList = [];
								 * 
								 * if (response.data.status == "success") {
								 * $scope.admittedPatientList =
								 * response.data.listObject; angular .forEach(
								 * $scope.admittedPatientList, function( value,
								 * index) {
								 * 
								 * var newArr = {}; newArr.specialityName =
								 * value.specialityName; newArr.dFirstName =
								 * value.dFirstName; newArr.dMiddleName =
								 * value.dMiddleName; newArr.dFirstName =
								 * value.dFirstName; newArr.dLastName =
								 * value.dLastName; newArr.pFirstName =
								 * value.pFirstName; newArr.pMiddleName =
								 * value.pMiddleName; newArr.pLastName =
								 * value.pLastName; newArr.reasonDesc =
								 * value.reasonDesc; newArr.dob = value.dob;
								 * newArr.datepicker = value.doa;
								 * newArr.datepickerPdd = value.pdd;
								 * newArr.genderCode = value.genderCode;
								 * newArr.uhidnumber = value.uhidnumber;
								 * newArr.patientId = value.patientId;
								 * newArr.paymentEntitlementDesc =
								 * value.paymentEntitlementDesc;
								 * newArr.bedNumber = value.bedNumber;
								 * newArr.admissionId = value.admissionId;
								 * newArr.prefixCode = value.prefixCode;
								 * newArr.identificationNo =
								 * value.identificationNo;
								 * $scope.listOfAdmittedPatientList
								 * .push(newArr);
								 * 
								 * });
								 *  }
								 * //$scope.initRelationMasterList($scope.organizationId,
								 * $scope.offset, $scope.noOfRecordsPerPage);
								 * });
								 */
							}

							$scope.selectPatient = function(item) {

								$scope.itemSelecteds = [];
								// If checkbox is checked
								if ($scope.admittedPatient) {
									$scope.itemSelecteds = item;
									$scope.visitorPassPopUpFlag = true;

								}
							}
							
							$scope.closePopUpWithRedirect = function(link)
							{
								
								if(link != undefined)
									{
								     angular.element('#patientServices').modal('hide');
								     if(link == 'sendToMortuary')
								    	 {
								    		if ($cookies.get('bedManagmentCookies') != null) {
												$scope.bedManagmentCookiesObj = JSON
														.parse($cookies
																.get('bedManagmentCookies'));
												$scope.currentDate = new Date();
												$scope.currentDate = moment($scope.currentDate).format('DD-MM-YYYY HH:mm:ss');
												 
												var data = {
														admissionId : $scope.bedManagmentCookiesObj.admissionId, 
														patientId:$scope.bedManagmentCookiesObj.patientId,
														tPatientId:$scope.bedManagmentCookiesObj.tPatientId,
														organizationId : $scope.organizationId,
														unitId : $scope.unitId,
														mortuaryStatusId : 1,
														visitTypeId :$scope.bedManagmentCookiesObj.visitTypeId,
														createdBy : $scope.userId,
														updatedBy : $scope.userId,
														createdDate:$scope.currentDate,
														updatedDate:$scope.currentDate,
														status :'A'
												}
												
												console.log(data);
												//return false;
												var URI = BASE_URL + ROOT_URL
														+ SAVEMORTUARYREQUESTFROMMAPOFBED;
												GenericService
														.serviceAction("POST", URI,data)
														.then(
																function(response) {

																	if (response.data.status == "success") {
																		growl
																				.success(
																						'Mortuary Request Sent sucessfully!!!.',
																						{
																							title : 'Success!'
																						});
																		$scope.init();
																	} else {
																		growl
																				.error(
																						'Something wrongs!!!!.',
																						{
																							title : 'Error!'
																						});
																	}

																});
												
								    		}
								    	 }
								     else
								    	 {
								    	  setTimeout(function () {
											     $state.go(link);
										      }, 1000);
								    	 }
								   
									}
							}

							/*
							 * $scope.visitorPass = function() {
							 * 
							 * if (Object.keys($scope.itemSelecteds).length ===
							 * 0) { growl .error( 'Please select atleast one
							 * patient!!!.', { title : 'Error!' }); } else {
							 * 
							 * var age = $rootScope
							 * .getAge($scope.itemSelecteds.dob);
							 * $scope.patientFullName =
							 * $scope.itemSelecteds.pFirstName + " " +
							 * $scope.itemSelecteds.pMiddleName + " " +
							 * $scope.itemSelecteds.pLastName + " " + age + "/" +
							 * $scope.itemSelecteds.genderCode; $scope.uhidNum =
							 * $scope.itemSelecteds.uhidnumber;
							 * $scope.doctorFullName = 'Dr. ' +
							 * $scope.itemSelecteds.dFirstName + " " +
							 * $scope.itemSelecteds.dMiddleName + " " +
							 * $scope.itemSelecteds.dLastName; $scope.bedNumber =
							 * $scope.itemSelecteds.bedNumber;
							 * $scope.dateOfAdmission =
							 * $scope.itemSelecteds.datepicker;
							 * $scope.patientDischargeDate =
							 * $scope.itemSelecteds.datepickerPdd; $scope
							 * .getPassIssuedList($scope.itemSelecteds.admissionId);
							 *  }
							 *  }
							 */

							/*
							 * $scope.savePassIssue = function() {
							 * $scope.currentDate = new Date();
							 * $scope.currentDate = moment($scope.currentDate)
							 * .format('DD-MM-YYYY HH:mm:ss');
							 * 
							 * var pdd = $rootScope
							 * .getChangedFormatedDateShalse($scope.itemSelecteds.datepickerPdd);
							 * var data = { organizationId :
							 * $scope.organizationId, unitId : $scope.unitId,
							 * visitorPassTypeId : $scope.visitorPassTypeId,
							 * passNumber : $scope.passNumber, admissionId :
							 * $scope.itemSelecteds.admissionId, expiryDate :
							 * pdd + ' 00:00:00', createdBy : $scope.userId,
							 * updatedBy : $scope.userId, status : 'A',
							 * visitorPassStatus : 'I', createdDate :
							 * $scope.currentDate, updatedDate :
							 * $scope.currentDate
							 *  }; console.log(data); // return false; var URI =
							 * BASE_URL + ROOT_URL + ADDVISITORPASSISSUE;
							 * GenericService .serviceAction("POST", URI, data)
							 * .then( function(response) {
							 * 
							 * if (response.data.status == "success") { growl
							 * .success( 'Pass Issued sucessfully!!!.', { title :
							 * 'Success!' }); $scope
							 * .$broadcast('show-errors-reset'); $scope
							 * .getPassIssuedList($scope.itemSelecteds.admissionId); }
							 * else { growl .error( 'Something wrongs!!!!.', {
							 * title : 'Error!' }); } }); }
							 */
							/* Tree Structure */
							 $scope.treeData = new kendo.data.HierarchicalDataSource({ data: [
						          
						            { text: "Nursing", items: [
						              { text: "Transfer", items: [
						            	  { 
						            		  text :"Initiate Bed Transfer",link:"initiateBedTransfer.initiateRequestTransferBed"},
						            		  { text:"Transfer to OT",link:"transferToOT.OTTransferRequest"},
						            		  { text:"Transfer for Investigation",link:"transferForInvestigation.investigationTransferRequest"},
						            		  { text:"Transfer Request",link:"transferRequest.initiateRequestTransferBed"}
						            	
						            	  ] },
						              { text: "Pharmacy" , items: [
						            	  	  { text :"Top Up Order",link:"topUpOrder"},
						            		  { text:"Patient Issue Acceptance",link:"patientIssueAcceptance"},
						            		  { text:"Pending Rejected Rejection",link:"pendingRejectedRejections"},
						            		  { text:"Sales Return",link:"salesReturn"},
						            		  { text :"Billable Consumption",link:"billableConsumption"},
						            		  { text:"Consumable Acceptance",link:"consumableAcceptance"},
						            		  { text:"Medication Reconciliation",link:"dischargeMedicationReconciliation"},
						            		  { text:"Medication Hand Over",link:"dischargeMedicationHandover"},
						            		  { text:"Request Short Leave",link:"shortLeaveDischarge"},
						            		  { text:"Short Leave Status",link:"shortLeaveStatus"},
						            		  { text :"Homecare Medication Entry",link:"homecareMedicationEntry"},
						            		  { text:"Return of Empty Ampoules",link:"returnOfEmptyAmpoules"}
						            	
						            	  ]},
						              { text: "Discharge", items: [
					            	  	  { text :"Marked for Discharge",link:"patientForDischarge"},
					            		  { text:"Ready for Billing",link:"readyForBilling"},
					            		  { text:"Final Discharge",link:"finalDischarge"},
					            		  { text:"Absconding patient",link:"abscondingPatient"},
						            	  { text:"Send to mortuary",link:"sendToMortuary"} ]}
						            ] },
						            { text: "Doctor", items: [
							              { text: "Transfer", items: [
							            	  { 
							            		  text :"Pending Transf. Req.",link:"pendingTransferRequest"},
							            		  { text:"Accept. Transf. Care",link:"acceptanceOfTransferOfCare"},
							            		  { text:"Initiate Transfer of Care",link:"intiateTransferCare"},
							            		  { text:"Transfer To ICU",link:"transferToICU"},
							            	
							            	  ] },
							            
							              { text: "Discharge", items: [
						            	  	  { text :"Due for Discharge",link:"dueForDischarge"},
						            		  { text:"Cancel Discharge",link:"cancelForDischarge"}
						            	  ]},
						            	  { text: "Order" , items: [
						            	  	  { text :"Pending Short Leave",link:"pendingShortLeave"},
						            		  { text:"Blood Request",link:"bloodRequest"},
						            		  { text:"Request Inter Consult",link:"requestInterConsult"},
						            		  { text:"Homecare Medication Approval",link:"homecareMedicationApproval"},
						            		  { text :"Pending Inter Consult",link:"pendingInterConsult"},
						            		  { text:"Crash Cart Administration Approval",link:"administrationApproval"},
						            		  { text:"Second Opinion Request",link:"requestForSecondOpinion"},
						            	
						            	  ]}
							            ] },
							            { text: "Billing", items: [
							            	      { text :"Deposit",link:"deposit"},
							            		  { text:"Refund",link:"refund"},
							            		  { text:"IPD Charges",link:"IPDCharges.serviceDetailsForIPDCharge"},
							            		  { text:"IPD Billing",link:"IPDBilling"},
							            		  { text :"Intermediate Bill",link:"intermediateBill"},
							            		  { text:"Debit Note List",link:"debitNoteList"},
							            		  { text:"Credit Note",link:"creditNoteList"},
							            		  { text:"Bill Estimation",link:"billEstimation"},
							            	
							            	  ] },
						            
						          ]});

						         /* $scope.click = function(dataItem) {
						            alert(dataItem.text);
						          };

						          function makeItem() {
						            var txt = kendo.toString(new Date(), "HH:mm:ss");
						            return { text: txt };
						          };

						          $scope.addAfter = function(item) {
						            var array = item.parent();
						            var index = array.indexOf(item);
						            var newItem = makeItem();
						            array.splice(index + 1, 0, newItem);
						          };

						          $scope.addBelow = function() {
						            // can't get this to work by just modifying the data source
						            // therefore we're using tree.append instead.
						            var newItem = makeItem();
						            $scope.tree.append(newItem, $scope.tree.select());
						          };

						          $scope.remove = function(item) {
						            var array = item.parent();
						            var index = array.indexOf(item);
						            array.splice(index, 1);

						            $scope.selectedItem = undefined;
						          };*/
							 	$scope.tab = 1 ;
								
								$scope.setTab = function(newTab)
								{
									$scope.tab = newTab;
								};
								
								$scope.isSet = function(tabNum)
								{
									return $scope.tab === tabNum;
								};
 
 /***************************************************/
  /******* START TAB FOR ALL PAGES IN TREE LEVEL *******/
/*First Level*/
$scope.firstTabLevel = 1 ;

$scope.setFirstTabLevel= function(newTab)
{
	$scope.firstTabLevel = newTab;
};

$scope.isSetFirstTabLevel = function(tabNum)
{
	return $scope.firstTabLevel === tabNum;
};

/*Second Level*/
$scope.secondTabLevel = 1 ;

$scope.setSecondTabLevel = function(newTab)
{
	$scope.secondTabLevel = newTab;
};

$scope.isSetSecondTabLevel = function(tabNum)
{
	return $scope.secondTabLevel === tabNum;
};

/*Third Level*/
$scope.thirdTabLevel = 1 ;

$scope.setThirdTabLevel = function(newTab)
{
	$scope.thirdTabLevel = newTab;
};

$scope.isSetThirdTabLevel = function(tabNum)
{
	return $scope.thirdTabLevel === tabNum;
};
/*FORTH Level*/
$scope.forthTabLevel = 1 ;

$scope.setForthTabLevel = function(newTab)
{
	$scope.forthTabLevel = newTab;
};

$scope.isSetForthTabLevel = function(tabNum)
{
	return $scope.forthTabLevel === tabNum;
};
/******* END TAB FOR ALL PAGES IN TREE LEVEL *******/
/****************************************************/								
								
} ]);
