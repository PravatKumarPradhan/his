angular
		.module('myApp')
		.controller(
				"noteMasterTemplateController",
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
							
							$scope.formeo = new Formeo({
						          container: '#formeo-editor',
						          svgSprite: 'resources/assets/images/LIS/formeo-sprite.svg',
						        });
							
						} ]);