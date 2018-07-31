angular.module('myApp').service('assigningPickerDispenserService', ['$rootScope', function ($rootScope) {
    var assigningPickerDispenserId = 0;
  
    var setAssigningPickerDispenserId = function(id) {
        assigningPickerDispenserId = id;
    };
  
    var getAssigningPickerDispenserId = function(){
        return assigningPickerDispenserId;
    };
  
    return {
      setAssigningPickerDispenserId: setAssigningPickerDispenserId,
      getAssigningPickerDispenserId: getAssigningPickerDispenserId,
    };
  }]);

 