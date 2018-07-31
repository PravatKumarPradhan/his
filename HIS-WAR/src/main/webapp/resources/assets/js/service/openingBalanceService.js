angular.module('myApp').service('openingBalanceService', ['$rootScope', function ($rootScope) {
  var balanceId = 0;
  var passOpeningBalanceId = function(passOpeningBalanceId){
    return passOpeningBalanceId;
  }

  var setOpeningBalanceId = function(id) {
      balanceId = id;
  };

  var getOpeningBalanceId = function(){
      return balanceId;
  };

  return {
    passOpeningBalanceId: passOpeningBalanceId,
    setOpeningBalanceId: setOpeningBalanceId,
    getOpeningBalanceId: getOpeningBalanceId,
  };
}]);