'use strict';

/**
 * @Author By Vivek Satle
 * @name myApp.controller:erDischargeMedicationHandoverController
 * @description #erDischargeMedicationHandoverController Controller of the myApp
 */
angular
		.module('myApp')
		.controller(
				'erDischargeMedicationHandoverController',
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

								var cookieObject = $cookies.getObject('cookieObject');
								if(cookieObject == undefined){
									$state.go('login');
									return;
								}
								$scope.unitId = cookieObject.unitId;
								$scope.organizationId = cookieObject.organizationId;
								$scope.userId = 1;
								
								
								

							}

							$scope.init();


						} ]);
