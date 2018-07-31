angular.module('myApp').service('rejectReasonService',
  function ($rootScope, $q, GenericService, CONSTANTS) {
    var reason;

    var GetReason = function () {
      var defer = $q.defer();

      if (!reason) {

        var SetReason = function (data) {
          reason = data;
        };

        var URI = CONSTANTS.GLOBAL.REJECT_REASON_API;
        
        GenericService.serviceAction("GET", URI, {}).then(
          function (response) {
            SetReason(response.data);
            defer.resolve(response.data);
          },
          function errorCallback(err) {
            defer.reject(err);
          });

      } else {
        defer.resolve(reason);
      }

      return defer.promise;
    }

    return {
      GetReason: GetReason
    };
  }
);