angular.module('myApp').service('pharmacologyValidationService', ['$rootScope', function ($rootScope) {
    var pharmacologyValidationId = 0;
    var passPharmacologyValidationId = function(passPharmacologyValidationId){
      return passPharmacologyValidationId;
    }
  
    var setPhaarmacologyValidationId = function(id) {
        pharmacologyValidationId = id;
    };
  
    var getPharmacologyValidationId = function(){
        return pharmacologyValidationId;
    };
  
    return {
      passPharmacologyValidationId: passPharmacologyValidationId,
      setPhaarmacologyValidationId: setPhaarmacologyValidationId,
      getPharmacologyValidationId: getPharmacologyValidationId,
    };
  }]);
