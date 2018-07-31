'use strict';

/**
 * @Author By  Vivek Satle
 * @name myApp.controller:logoutController
 * @description #logoutController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'logoutController',
				[
						'$scope',
						'$rootScope',
						'$state',
						'$http',
						'GenericService',
						'$sessionStorage',
						function($scope, $rootScope, $state, $http,
								GenericService,$sessionStorage) {
							sessionStorage.clear();
							 $scope.unitId = sessionStorage.getItem("unitId");
							   $scope.organizationId = sessionStorage.getItem("organizationId");
							   $scope.userId = sessionStorage.getItem("userId");
							   if($scope.userId == null)
								   {
								   $rootScope.loginpage = false;
								   	$state.go('login');
								   }
							   
						} ]);
