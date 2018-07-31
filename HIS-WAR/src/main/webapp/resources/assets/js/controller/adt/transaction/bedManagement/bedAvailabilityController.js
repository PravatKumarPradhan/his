'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:bedAvailabilityController
 * @description # bedAvailabilityController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'bedAvailabilityController',
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
						function($scope, $http, $localStorage, $sessionStorage,
								$cookies, $rootScope, GenericService, growl,$state) {

							/* init() function for form object create. */
							$scope.init = function() {
								
								$scope.wlCount = 0;
							/*	$scope.dateArr = [];
								$scope.dateArr.push(moment(new Date()).format('DD-MM-YYYY HH:mm:ss'));
							    for(var i=1;i<7;i++)
							    { 
							      $scope.tomorrow = new Date();
							      $scope.tomorrow.setDate($scope.tomorrow.getDate() + i);
							      var nextDate = moment($scope.tomorrow).format('DD-MM-YYYY HH:mm:ss');
							      $scope.dateArr.push(nextDate);
							      console.log($scope.dateArr);
							    }*/
							    
							    

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

								
								var cookieObject = $cookies.getObject('cookieObject');
								if(cookieObject == undefined){
									$state.go('login');
									return;
								}
								$scope.unitId = cookieObject.unitId;
								$scope.organizationId = cookieObject.organizationId;
								$scope.userId = 1;
								   
								$rootScope.loginpage = true;
								
								$scope.currentDate = new Date();
								$scope.currentDate = moment($scope.currentDate).format('YYYY-MM-DD');
								 
								var data = {
										organizationId : $scope.organizationId,
										unitId : $scope.unitId,
										date :$scope.currentDate 
								};
								// $scope.status = "NO";
								
								var commonObj = {
										organizationId : $scope.organizationId,
										unitId : $scope.unitId
								}
								console.log(data);
								var URI = BASE_URL + ROOT_URL
										+ GETBEDAVAILABILTYLIST;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.bedAvailabiltyList = [];

													if (response.data.status == "success") {
														$scope.bedAvailabiltyList = response.data.listObject;
														$scope.dateList = $scope.bedAvailabiltyList[0];
														$scope.finalBedAvailabiltyList = $scope.bedAvailabiltyList[1];
														console.log($scope.dateList);
														console.log($scope.finalBedAvailabiltyList);

													}
												});

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
								
							}

							$scope.init();
							
							
							
							$scope.searchBedAvaliable  = function()
							{
								var data = {
										organizationId : $scope.organizationId,
										unitId : $scope.unitId,
										date :moment($scope.datepicker).format('YYYY-MM-DD') 
								};
								
								console.log(data);
								//return false;
								var URI = BASE_URL + ROOT_URL
										+ GETBEDAVAILABILTYLIST;
								GenericService
										.serviceAction("POST", URI, data)
										.then(
												function(response) {
													$scope.bedAvailabiltyList = [];

													if (response.data.status == "success") {
														$scope.bedAvailabiltyList = response.data.listObject;
														$scope.dateList = $scope.bedAvailabiltyList[0];
														$scope.finalBedAvailabiltyList = $scope.bedAvailabiltyList[1];
														console.log($scope.dateList);
														console.log($scope.finalBedAvailabiltyList);

													}
												});

							}


						} ]);
