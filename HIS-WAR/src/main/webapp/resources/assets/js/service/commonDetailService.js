angular.module('myApp').service('commonDetailService', ['$rootScope', function ($rootScope) {
  var dataId = 0;

  var setDataId = function(id) {
      dataId = id;
  };

  var getDataId = function(){
      return dataId;
  };

  return {
    setDataId: setDataId,
    getDataId: getDataId,
  };
}]);