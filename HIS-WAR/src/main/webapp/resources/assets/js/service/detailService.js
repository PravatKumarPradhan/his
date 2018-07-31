angular.module('myApp').service('detailService', ['$rootScope', function ($rootScope) {
    var id = 0;
  
    var setId = function(itemId) {
        id = itemId;
    };
  
    var getId = function(){
        return id;
    };
  
    return {
      setId: setId,
      getId: getId,
    };
  }]);

 