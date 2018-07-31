angular.module('myApp').service('StatusService',
  function ($rootScope, $q, GenericService, CONSTANTS) {
    var status;

    var GetStatus = function () {
      var defer = $q.defer();

      if (!status) {

        var SetStatus = function (data) {
          status = data;
        };

        var URI = CONSTANTS.GLOBAL.STATUS_API;

        GenericService.serviceAction("GET", URI, {}).then(
          function (response) {
            SetStatus(response.data);
            defer.resolve(response.data);
          },
          function errorCallback(err) {
            defer.reject(err);
          });

      } else {
        defer.resolve(status);
      }

      return defer.promise;
    }

    return {
      GetStatus: GetStatus
    };
  }
);