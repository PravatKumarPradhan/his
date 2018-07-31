
'use strict';

/**
 * @Author
 * @name myApp.controller:reasonMasterController
 * @description #reasonMasterController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'reasonMasterController',
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

								/** Variables for pagination */
								$scope.offset = 0;
								$scope.noOfRecordsPerPage = 10;
								$scope.totalNoOfRecords;
								$scope.searchKeyword;
								$scope.commonListCount;

								$rootScope.loginpage = true;
								$scope.reasonMasterObj = {}
								$scope.saveBtnFlag = true;
								$scope.updateBtnFlag = false;

								// for popup
								$scope.showModal = false;
								$scope.buttonClicked = "";
								$scope.popUpFlag = true;
								// end for popup
								// For Get reason List 
								var URI = BASE_URL + ROOT_URL+ GETREASONTYPEMASTERLIST;	
						var data = {
									organizationId : $scope.organizationId
									}

						
						GenericService
								.serviceAction("POST", URI, data)
								.then(
										function(response) {
											$scope.reasonTypeList = [];

											if (response.data.status == "success") {
												$scope.reasonTypeList = response.data.listObject;
												console.log("reasonTypeList",$scope.reasonTypeList);
											}
										});

							}

							$scope.init();

							// For Paginations Start
							$scope.setNoOfRecords = function() {
								$scope.initReasonlist(
										$scope.organizationId, $scope.offset,
										$scope.noOfRecordsPerPage);
							};
							$scope.initReasonlist = function(orgId,
									offset, noOfRecordsPerPage) {
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
										: 10;
								var URI = BASE_URL + ROOT_URL
										+ GETREASONMASTERLIST;
								var URI1 = BASE_URL + ROOT_URL
										+ GETREASONMASTERCOUNT;

								var data = {
									organizationId : orgId,
									offset : offset,
									noOfRecordsPerPage : noOfRecordsPerPage
								}
								console.log("data", data);

								var data1 = {
									organizationId : $scope.organizationId
								}

								// For Get Rejection List Using Offset
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.reasonList = [];

													if (response.data.status == "success") {
														$scope.reasonList = response.data.listObject;
														console
																.log(
																		"reasonList",
																		$scope.reasonList);
													}
												});

								// For Count reason List
								GenericService
										.serviceAction("POST", URI1, data1)
										.then(
												function(response) {
													if (response.data.status == "success") {
														console
																.log(response.data);
														$scope.commonListCount = response.data.object;
														$scope
																.setPage(1,
																		false);

													}
												});
							}

							$scope.getReasonList = function(orgId,
									offset, noOfRecordsPerPage) {
								console.log("offset", offset);
								var data = "";
								offset = offset != null ? offset : 0;
								noOfRecordsPerPage = noOfRecordsPerPage != null ? noOfRecordsPerPage
										: 10;
								var URI = BASE_URL + ROOT_URL
										+ GETREASONMASTERLIST;
								var data = {
									organizationId : $scope.organizationId,
									offset : offset,
									noOfRecordsPerPage : noOfRecordsPerPage
								}

								console.log("URI", URI);
								// For Get reason List Using Offset
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.reasonList = [];

													if (response.data.status == "success") {
														$scope.reasonList = response.data.listObject;														
													}
												});
							}

							$scope.pager = {};
							$scope.page;

							$scope.setPage = function(page, flag) {
								if (page < 1 || page > $scope.pager.totalPages) {
									return;
								}
								$scope.pager = PagerService.GetPager(
										$scope.commonListCount, page,
										$scope.noOfRecordsPerPage);
								if (flag) {
									$scope.getReasonList(
											$scope.organizationId,
											$scope.pager.startIndex,
											$scope.pager.pageSize);
								}
							}

							$scope.initReasonlist($scope.organizationId,
									$scope.offset, $scope.noOfRecordsPerPage);

							// For Paginations End

							// function for popup ok button call
							$scope.PopupOkBtn = function() {
								$scope.popUpFlag = false;
								$scope.saveReasonMaster();
							}

							// Function For Save Reason
							$scope.saveReasonMaster = function() {
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

//								 $scope.$broadcast('show-errors-check-validity');
								var data = {
									reasonCode : $scope.reasonMasterObj.reasonCode,
									reasonDesc : $scope.reasonMasterObj.reasonDescription,
									reasonTypeId:$scope.reasonMasterObj.reasonTypeId,
									status : 'A',
									createdBy : $scope.userId,
									updatedBy : $scope.userId,
									organizationId : $scope.organizationId,
									createdDate : $scope.currentDate,
									updatedDate : $scope.currentDate
								}
//								 if ($scope.rejectionForm.$valid) {

								if ($scope.popUpFlag) {
									$scope.showModal = !$scope.showModal;

								} else {
									console.log("save", data);

									var URI = BASE_URL + ROOT_URL
											+ SAVEREASONMASTER;
									GenericService
											.serviceAction("POST", URI, data)
											.then(
													function(response) {
														if (response.data.status == "success") {
															$scope.init();
															growl
																	.success(
																			response.data.message,
																			{
																				title : 'Success!'
																			});
														} else {
															growl
																	.error(
																			response.data.message,
																			{
																				title : 'Error!'
																			});
														}

														$scope
																.initReasonlist(
																		$scope.organizationId,
																		$scope.offset,
																		$scope.noOfRecordsPerPage);

													});
								}
//								 }

							}

							// Function For get single rejection entry
							$scope.showUpdateBtn = function(id) {

								$scope.saveBtnFlag = false;
								$scope.updateBtnFlag = true;

								var data = {

								}
								var URI = BASE_URL + ROOT_URL
										+ GETREASONMASTERBYID + "/"
										+ id + "/" + $scope.organizationId;
								GenericService
										.serviceAction("GET", URI, data)
										.then(
												function(response) {
													if (response.data.status == "success") {
														$scope.reasonMasterObj.reasonMasterId = response.data.listObject[0].reasonMasterId;
														$scope.reasonMasterObj.reasonCode = response.data.listObject[0].reasonCode;
														$scope.reasonMasterObj.reasonDescription = response.data.listObject[0].reasonDesc;
														$scope.reasonMasterObj.reasonTypeId = response.data.listObject[0].reasonTypeId.toString();

													} else {
														alert("Error!!");
													}
												});

							};

							// Function For update reason status
							$scope.updateStatus = function(id, type) {
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');

								type = (type == "A") ? "A" : "I";

								var data = {
									reasonMasterId : id,
									status : type,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate
								}
								var URI = BASE_URL + ROOT_URL
										+ UPDATEREASONMASTERSTATUS;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {

													if (response.data.status == "success") {
														$scope.init();
														growl
																.success(
																		response.data.message,
																		{
																			title : 'Success!'
																		});
													} else {
														growl
																.error(
																		response.data.message,
																		{
																			title : 'Error!'
																		});
													}
												});
							}

							// Function For update reason
							$scope.updateReasonMaster = function() {
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate)
										.format('DD-MM-YYYY HH:mm:ss');
//								$scope.$broadcast('show-errors-check-validity');
								var data = {
									reasonCode : $scope.reasonMasterObj.reasonCode,
									reasonDesc : $scope.reasonMasterObj.reasonDescription,								
									reasonTypeId : $scope.reasonMasterObj.reasonTypeId,
									reasonMasterId : $scope.reasonMasterObj.reasonMasterId,
									updatedBy : $scope.userId,
									updatedDate : $scope.currentDate
								}
								
//								if ($scope.rejectionForm.$valid) {
									var URI = BASE_URL + ROOT_URL
											+ UPDATEREASONMASTER;
									GenericService
											.serviceAction("POST", URI, data)
											.then(
													function(response) {

														if (response.data.status == "success") {
															$scope.init();
															growl
																	.success(
																			response.data.message,
																			{
																				title : 'Success!'
																			});
														} else {
															growl
																	.error(
																			response.data.message,
																			{
																				title : 'Error!'
																			});
														}
														$scope
																.initReasonlist(
																		$scope.organizationId,
																		$scope.offset,
																		$scope.noOfRecordsPerPage);

													});
								}

//							}
						} ]);



//Below code is done by nibo which is commented as per saurab said
/*(function () {
    "use strict";
    function reasonMasterController($scope, $http, constants, GenericService) {
      var self = this;
      //Default Constructor
      function initializeController() {
        self.model = {};
        self.model.PopulateReasonData = populateReasonData;
        self.model.ActivateDeactivateReasonData = activateDeactivateReasonData;
        self.model.SaveReasonData = saveReasonData;
        self.model.ClearFields = clearFields;
        getReasonList();
      }
      
      function findInRecordInArray(array, key, value) {
        for(var i=0; i<array.length; i++){
          if(array[i][key] == value){
            return array[i];
          }
        }
        return null;
      }

      function getReasonById (reasonId) {
        return findInRecordInArray(self.model.reasonList, 'reasonId', reasonId)
      }
      //Method to get the list of Manufacturers
      function getReasonList () {
        var URI = constants.apiurl + '/reasons';
        var data = {};
        GenericService.serviceAction("GET", URI, data).then(
          function (response) {
            self.model.reasonList = response.data;
          });
      }

      //Method to populate Reason Data
      function populateReasonData (reasonId) {
        self.model.activeReasonTypeId = reasonId;
        var URI = constants.apiurl + '/reasons/' + reasonId;
        var data = {};
        GenericService.serviceAction("GET", URI, data).then(
          function (response) {
            var reason = getReasonById(reasonId);
            reason = response.data;
            self.model.reasonName = response.data.reasonName;
            self.model.reasonCode = response.data.reasonCode;
            self.model.reasonDescription = response.data.reasonDescription;
            self.model.isEdit = true;
          });
      };
  
      //Method to activate or deactivate ReasonData
      function activateDeactivateReasonData(reasonId){
          var reason = getReasonById(reasonId);
          var isActive = reason.isActive;
          var URI = constants.apiurl + '/reasons/' + reasonId;
          var data = {
            "isActive": isActive
          };
          GenericService.serviceAction("PATCH", URI, data).then(
            function (response) {
              console.log(response)
            });
      };
  
      //Method to add or update the Manufacturer
      function saveReasonData () {
        if(self.model.isEdit) {
          updateReasonData();
        } else{
          createReasonData();
        }
      };
  
      //Method to add the Reason Data
      function createReasonData () {
        var URI =  constants.apiurl + '/reasons';
        var data = {
            "reasonName": self.model.reasonName,
            "reasonCode": self.model.reasonCode,
            "reasonDescription": self.model.reasonDescription,
            "isActive": true
          }
        GenericService.serviceAction("POST", URI, data).then(
          function (response) {
            var insertedData = {
              "reasonId": response.data,
              "reasonName": self.model.reasonName,
              "reasonCode": self.model.reasonCode,
              "reasonDescription": self.model.reasonDescription,
              "isActive": true
            };
            self.model.reasonList.push(insertedData);
            clearFields();
          }, function errorCallback(response) {
            console.log(response.status);
            console.log(response.data || 'Request Failed');
          });
      };

      //Method to update the Manufacturer
      function updateReasonData () {
        var URI = constants.apiurl + '/reasons/' + self.model.activeReasonTypeId;
        var reason = getReasonById(self.model.activeReasonTypeId);
        var data = {
          "reasonName": self.model.reasonName,
          "reasonCode": self.model.reasonCode,
          "reasonDescription": self.model.reasonDescription,
          "isActive": reason.isActive
        }
        //  
        GenericService.serviceAction("PATCH", URI, data).then(
          function (response) {
            var reason = getReasonById(self.model.activeReasonTypeId);

            reason.reasonCode = data.reasonCode;
            reason.reasonName = data.reasonName;
            reason.reasonDescription = data.reasonDescription;
            reason.isActive = data.isActive;
            clearFields();
            self.model.activeReasonTypeId = null;
            self.model.isEdit = false;
          });
      };
      //Method to clear the fileds
      function clearFields() {
        self.model.reasonCode = "";
        self.model.reasonName = "";
        self.model.reasonDescription = "";
      }
      initializeController();
    }

    function config($stateProvider) {
      $stateProvider.state("reasonMaster", {
        url: "/reasonMaster",
        // data: { title: "About", activetab : "about" },
        templateUrl: 'views/master/GlobalMaster/reason-master.html',
        controller: "ReasonMaster.Controller",
        controllerAs: "vm"
        // authenticate: false
      });
    }
    
    angular
      .module("myApp")
      .config(config)
      .controller("ReasonMaster.Controller", reasonMasterController)
      .directive('showFocus', function ($timeout) {
        return function (scope, element, attrs) {
          scope.$watch(attrs.showFocus,
          function (newValue, oldValue) {
            $timeout(function () {
              (newValue != oldValue) && element.focus();
            });
          }, true);
        };
      });
  })();*/