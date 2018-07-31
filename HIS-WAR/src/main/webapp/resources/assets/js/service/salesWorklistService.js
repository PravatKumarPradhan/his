angular.module('myApp').service('salesWorklistService', ['$rootScope', function ($rootScope) {
    var salesWorklistId = 0;
    var passSalesWorklistId = function(passSalesWorklistId){
      return passSalesWorklistId;
    }
  
    var setWorklistId = function(id) {
        salesWorklistId = id;
    };
  
    var getWorklistId = function(){
        return salesWorklistId;
    };
  
    return {
      passSalesWorklistId: passSalesWorklistId,
      setWorklistId: setWorklistId,
      getWorklistId: getWorklistId,
    };
  }]);