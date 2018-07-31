(function () {
    "use strict";
    function routeOfAdministrationController($scope, $http, constants, GenericService) {
      var self = this;
      //Default Constructor
      function initializeController() {
        self.model = {};
        self.model.PopulateRouteOfAdministration = populateRouteOfAdministration;
        self.model.ActivateDeactivateRouteOfAdministration = activateDeactivateRouteOfAdministration;
        self.model.SaveRouteOfAdministration = saveRouteOfAdministration;
        self.model.ClearFields = clearFields;
    
        getRouteOfAdministrationData();
      }   
  
      //Method to get the list of administration Rout
      function getRouteOfAdministrationData() {
        var URI =  constants.apiurl + '/administrationRouts';
        var data = {};
        GenericService.serviceAction("GET", URI, data).then(
          function (response){
            self.model.routeOfAdministrationDataList = response.data;
          });
      };
  
      //Method to populate administration Rout for editing
      function findInRecordInArray(array, key, value) {
        for(var i=0; i<array.length; i++){
          if(array[i][key] == value){
            return array[i];
          }
        }
        return null;
      }
      function getRouteOfAdministrationById (administrationRoutId) {
        return findInRecordInArray (self.model.routeOfAdministrationDataList, 'administrationRoutId', administrationRoutId)
      }

      //Method to populate
      function populateRouteOfAdministration (administrationRoutId) {
          self.model.activeadministrationRoutId = administrationRoutId;
          var URI = constants.apiurl + '/administrationsRouts/' + administrationRoutId;
          var data = {};
          GenericService.serviceAction("GET", URI, data).then(
            function (response){
                var administrationRout = getRouteOfAdministrationById(administrationRoutId);
                administrationRout = response.data; 
                self.model.administrationRoutCode = response.data.administrationRoutCode;
                self.model.administrationRoutName = response.data.administrationRoutName;
                self.model.administrationRoutId = response.data.administrationRoutId;
                self.model.isEdit = true;
                
              });
          };
  
      //Method to activate or deactivate administration Rout type
      function activateDeactivateRouteOfAdministration (administrationRoutId){
          var administrationRout = getgetRouteOfAdministrationById(administrationRoutId);
          var isActive = administrationRout.isActive;
          var URI = constants.apiurl + '/administrationRout/administrationRouts/' + administrationRoutId;
          var data = {
            "isActive": isActive
          };
          GenericService.serviceAction("PATCH", URI, data).then(
            function (response) {
            console.log(response)
          });
      };
  
      //Method to add or update the RouteOfAdministration 
      function saveRouteOfAdministration () {
        if(self.model.isEdit) {
          updateRouteOfAdministration();
        } else{
          createRouteOfAdministration();
        }
      };
  
      //Method to add the RouteOfAdministration 
      function createRouteOfAdministration () {
        var URI = constants.apiurl + '/administrationRouts';
        var  data = {
          "administrationRoutName": self.model.administrationRoutName,
          "administrationRoutCode": self.model.administrationRoutCode,
          "isActive": true
      }
        GenericService.serviceAction("POST", URI, data).then(
          function (response){
            var insertedData = {
              "administrationRoutId": response.data,
              "administrationRoutName": self.model.administrationRoutName,
              "administrationRoutCode": self.model.administrationRoutCode,
              "isActive": true
            };
            self.model.routeOfAdministrationDataList.push(insertedData);
            clearFields();
          }, 
           function errorCallback(response) {
              console.log(response.status);
              console.log(response.data || 'Request Failed');
          });
      };
  
      //Method to update the RouteOfAdministration type
      function updateRouteOfAdministration () {
        var URI = constants.apiurl + '/administrationRouts/' + self.model.administrationRoutId;
        var administrationRout = getRouteOfAdministrationById(self.model.activeadministrationRoutId);
        var data = {
          "administrationRoutName": self.model.administrationRoutName,
          "administrationRoutCode": self.model.administrationRoutCode,
          "isActive": administrationRout.isActive
        }
        GenericService.serviceAction("PATCH", URI, data).then(
          function (response){
            var administrationRout = getRouteOfAdministrationById(self.model.activeadministrationRoutId);
          
            administrationRout.administrationRoutName = data.administrationRoutName;
            administrationRout.administrationRoutCode = 
            data.administrationRoutCode;  
            administrationRout.isActive = data.isActive;
            clearFields();
            self.model.activeadministrationRoutId = null;
            self.model.isEdit = false;
        });
      };
  
      //Method to clear the fileds
      function clearFields() {
        self.model.administrationRoutName = "";
        self.model.administrationRoutCode = "";
      }
  
      initializeController();
    }
    function config($stateProvider) {
      $stateProvider.state("routeAdministration", {
        url: "/routeAdministration",
        templateUrl: "views/master/GlobalMaster/AssetManagenent/Master/route-of-adminstration.html",
        controller: "RouteOfAdministration.Controller",
        controllerAs: "vm",
      });
    }
    angular
      .module("myApp")
      .config(config)
      .controller("RouteOfAdministration.Controller", routeOfAdministrationController)
      .directive('showFocus', function($timeout) {
        return function(scope, element, attrs) {
          scope.$watch(attrs.showFocus, 
            function (newValue, oldValue) { 
              $timeout(function() {
                (newValue != oldValue) && element.focus();
              });
            },true);
        };    
      });   
  })();
  
  
