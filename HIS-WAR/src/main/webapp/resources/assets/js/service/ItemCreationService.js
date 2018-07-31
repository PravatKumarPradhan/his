angular.module('myApp').service('ItemCreationService', ['$rootScope', function ($rootScope) {
  var item;

  var SetItem = function (selectItem) {
    item = selectItem;
  };

  var GetItem = function () {
    return item;
  };

  return {
    SetItem: SetItem,
    GetItem: GetItem,
  };
}]);