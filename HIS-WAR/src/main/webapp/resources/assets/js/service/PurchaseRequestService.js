angular.module('myApp').service('PurchaseRequestService', ['$rootScope', function ($rootScope) {
  var purchaseId;

  var SetPurchaseId = function (id) {
    purchaseId = id;
  };

  var GetPurchaseId = function () {
    return purchaseId;
  };

  return {
    SetPurchaseId: SetPurchaseId,
    GetPurchaseId: GetPurchaseId,
  };
}]);