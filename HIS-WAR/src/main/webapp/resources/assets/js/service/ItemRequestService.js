angular.module('myApp').service('ItemRequestService', ['$rootScope', function ($rootScope) {
  var itemId;

  var SetItemId = function (id) {
    itemId = id;
  };

  var GetItemId = function () {
    return itemId;
  };

  return {
    SetItemId: SetItemId,
    GetItemId: GetItemId,
  };
}]);