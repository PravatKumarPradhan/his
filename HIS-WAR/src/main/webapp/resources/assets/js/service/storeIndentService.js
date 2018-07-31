angular.module('myApp').service('storeIndentService', ['$rootScope', function ($rootScope) {
  var storeIndentId = 0;
  var passStoreIndentId = function(passStoreIndentId){
    return passStoreIndentId;
  }

  var setIndentId = function(id) {
      storeIndentId = id;
  };

  var getIndentId = function(){
      return storeIndentId;
  };

  return {
    passStoreIndentId: passStoreIndentId,
    setIndentId: setIndentId,
    getIndentId: getIndentId,
  };
}]);