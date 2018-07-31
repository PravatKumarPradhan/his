(function () {
  "use strict";
  function frequencyMasterController($scope, $http, constants, GenericService) {
    var self = this;

    //Default Constructor
    function initializeController() {
      self.model = {};
      self.model.PopulateFrequencyMaster = populateFrequencyMaster;
      self.model.ActivateDeactivateFrequencyMaster = activateDeactivateFrequencyMaster;
      self.model.SaveFrequencyMaster = saveFrequencyMaster;
      self.model.ClearFields = clearFields;
      
      getFrequencyMasterData();
      getAdministrationTimesData();
    }

    //Method to get the list of FrequencyMaster
    function getFrequencyMasterData() {
      var URI = constants.apiurl + '/frequencies';
      var data = {};
      GenericService.serviceAction("GET", URI, data).then(
          function (response) {
            self.model.frequencyMasterDataList = response.data;
          });
    };

    //Method to get the list of administration times
    function getAdministrationTimesData() {
      var URI = constants.apiurl + '/frequencies';
      var data = {};
      GenericService.serviceAction("GET", URI, data).then(
        function (response) {
          self.model.administrationTimesDataList = response.data;
        });
  };

    //Method to populate formulation type for editing
    function findInRecordInArray(array, key, value) {
      for (var i = 0; i < array.length; i++) {
        if (array[i][key] == value) {
          return array[i];
        }
      }
      return null;
    }
    function getFrequencyMasterById(frequencyId) {
      return findInRecordInArray(self.model.frequencyMasterDataList, 'frequencyId', frequencyId)
    }
    function populateFrequencyMaster (frequencyId) {
      console.log(frequencyId)
        self.model.activeFrequencyId = frequencyId;
        var URI = constants.apiurl + '/frequencies/' + frequencyId;
        var data = {};
        GenericService.serviceAction("GET", URI, data).then(
          function (response){
            var frequency = getFrequencyMasterById(frequencyId)
            frequency = response.data;
            self.model.frequencyCode = response.data.frequencyCode;
            self.model.frequencyDescription = response.data.frequencyDescription;
            self.model.adminstrationTimes = response.data.adminstrationTimes;
            self.model.isActive = response.data.isActive;
            self.model.isEdit = true;
            
          });
    };

    //Method to activate or deactivate formulation type
    function activateDeactivateFrequencyMaster (frequencyId){
      var frequency = getFrequencyMasterById(frequencyId);
      var data = {};
      var isActive = frequency.isActive;
      var URI = constants.apiurl + '/frequencies/' + frequencyId;
          data = {
            "isActive": isActive
          };
        GenericService.serviceAction("Patch", URI, data).then(
          function (response){
          // console.log(response)
        });
    };

    //Method to add or update the formulation type
    function saveFrequencyMaster () {
      if(self.model.isEdit) {
        updateFrequencyMaster();
      } else{
        createFrequencyMaster();
      }
    };

    //Method to add the formulation type
    function createFrequencyMaster () {
      var URI = constants.apiurl + '/frequencies';
      var data = {
              "frequencyCode": self.frequencyCode,
              "frequencyDescription": self.frequencyDescription,
              "adminstrationTimes": self.adminstrationTimes
          }
        GenericService.serviceAction("POST", URI, data).then(
          function successCallback(response) {
            var insertedData = {
              "frequencyId": response.data,
              "frequencyCode": self.model.frequencyCode,
              "frequencyDescription": self.model.frequencyDescription,
              "adminstrationTimes": self.model.adminstrationTimes,
              "isActive": true
            };
            self.model.frequencyMasterDataList.push(insertedData);
            clearFields();
        }, function errorCallback(response) {
            console.log(response.status);
            console.log(response.data || 'Request Failed'); 
        });
    };

    //Method to update the formulation type
    function updateFrequencyMaster () {
      var URI = constants.apiurl + '/frequencies/' + self.model.activeFrequencyId;
      var frequency = getFrequencyMasterById(self.model.activeFrequencyId);
      var data = {
        "frequencyCode": self.model.frequencyCode,
        "frequencyDescription": self.model.frequencyDescription,
        "adminstrationTimes": self.model.adminstrationTimes,
        "isActive": self.model.isActive
      }
        GenericService.serviceAction("PATCH", URI, data).then(
        function (response) {
        var frequency = getFrequencyMasterById(self.model.activeFrequencyId);

        frequency.frequencyCode = data.frequencyCode;
        frequency.frequencyDescription = data.frequencyDescription;
        frequency.adminstrationTimes = data.adminstrationTimes;
        frequency.isActive = data.isActive;
        clearFields();
        self.model.activeFrequencyId = null;
        self.model.isEdit = false;
      });
    };

    //Method to clear the fileds
    function clearFields() {
      self.model.frequencyCode = "";
      self.model.frequencyDescription = "";
      self.model.adminstrationTimes = "";
    }

    initializeController();
  }
  function config($stateProvider) {
		$stateProvider.state("frequencyMaster", {
		  url: "/frequencyMaster",
			templateUrl: "views/master/GlobalMaster/AssetManagenent/Master/frequency-master.html",
			controller: "FrequencyMaster.Controller",
			controllerAs: "vm",
		});
  }
  angular
    .module("myApp")
    .config(config)
    .controller("FrequencyMaster.Controller", frequencyMasterController)
    .directive('showFocus', function ($timeout) {
      return function (scope, element, attrs) {
        scope.$watch(attrs.showFocus,
        function (newValue, oldValue) {
          $timeout(function () {
            (newValue != oldValue) && element.focus();
          });
        }, true);
      };
    });
})();
