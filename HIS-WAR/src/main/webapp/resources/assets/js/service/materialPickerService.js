angular.module('myApp').service('materialPickerService', ['$rootScope', function ($rootScope) {
  var MaterialPickerId = 0;

  var setMaterialPickerId = function(id) {
      MaterialPickerId = id;
  };

  var getMaterialPickerId = function(){
      return MaterialPickerId;
  };

  return {
    setMaterialPickerId: setMaterialPickerId,
    getMaterialPickerId: getMaterialPickerId,
  };
}]);