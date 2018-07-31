(function () {
    "use strict";

    function pharmacologyValidationWorklistDetailsController($scope, $rootScope, $http, CONSTANTS, GenericService, $filter, $state, growl, CurrencyService, pharmacologyValidationService, StatusService) {
        var self = this;
        

        //Default Constructor
        function initializeController() {
            $rootScope.loginpage = true;

            self.model = {
                // intervention : []
            };

            //Methods
            CurrencyService.GetCurrency().then(function (currency) {
                self.model.Currency = currency;
            })
            StatusService.GetStatus().then(function (status) {
                self.model.Status = status;
            })

            self.model.pharmacologyValidationDetails = pharmacologyValidationDetails;
            self.model.IsValidateToggle = IsValidateToggle;
            self.model.Validation = Validation;
            self.model.SavePopup = SavePopup;
            self.model.SavePharmacologyValidationWorkList = SavePharmacologyValidationWorkList;
            self.model.NavigateToPharmacologicalValidation = NavigateToPharmacologicalValidation;
            pharmacologyValidationDetails(pharmacologyValidationService.getPharmacologyValidationId());
        }

     

        var pharmacologyValidationDetails = function (itemId) {

            var URI = CONSTANTS.PHARMACY.OP.PHARMACOLOGICAL_VALIDATION.DETAIL_SEARCH_API + itemId;

            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {

                    self.model.pharmacologyValidationDetailsList = response.data;

                    var intervention = [];
                    angular.forEach(self.model.pharmacologyValidationDetailsList.items, function (item, key) {
                        intervention.push(item.intervention);
                    });
                    // console.log(intervention);
                    if (intervention.indexOf(true) !== -1) {
                        self.model.isValidateValid = true;
                    } else {
                        self.model.isValidateValid = false;
                    }

                });
            $rootScope.stopLoader();
        }

        var IsValidateToggle = function (bill) {
            var Interventions = [];
            angular.forEach(bill.items, function (item, key) {
                if (item.intervention) {
                    self.model.isValidateValid = true;
                    self.model.validate = false;
                } else {
                    self.model.isValidateValid = false;
                }
                Interventions.push(item.intervention);
            });

            var index = Interventions.indexOf(true);
            if (index !== -1) {
                //  if true 
                self.model.isValidateValid = true;
                self.model.validate = false;
            }

        }

        var Validation = function (bill) {
            var isValid = true;
            var isChecked = false;
           
            bill.items.forEach(function (item) {
                item['isinterventionRemarkValid'] = false;
                
                if (item.intervention && !isChecked){ 
                    isChecked = true;
                }

                if(item.intervention && (item.interventionRemark == undefined || item.interventionRemark == '')) { 
                    isValid = false;
                    item['isinterventionRemarkValid'] = true;
                    growl.error("Remark is required for intervention items");
                }

            });
            
            return isValid;
        }

        var ClearFields = function (itemList){
            itemList.Id = "1" + (new Date()).getTime();
            itemList.uhid = null;
            itemList.patientDetail = null;
            itemList.wardName = null;
            itemList.pdd = null;
            itemList.doa = null;
            itemList.bedNo = null;
            itemList.doctorDetail = null;
            self.model.pharmacologyValidationDetailsList = null;
            self.model.interventionRemark = null;
            self.model.validate = null;
            itemList.items = [];
        }

        var SetStatus = function (x){
            var status = self.model.Status.find(function(obj){
              return obj.status == x;
            });
            
            return status.id;
        }


        var SavePharmacologyValidationWorkList = function (itemList) {
            
            if (Validation(itemList)) {
                var URI = CONSTANTS.PHARMACY.OP.PHARMACOLOGICAL_VALIDATION.SAVE_API;
                var statusId;
                var itemStatusId;

                if(self.model.validate){
                    statusId = SetStatus('Pending Assign Picker');
                    itemStatusId = SetStatus('Prescribed');
                }else{
                    statusId = SetStatus('Intervention');
                    itemStatusId = SetStatus('Intervention');
                }

                var data = {
                    "id": !!itemList.id ? itemList.id : undefined,
                    "validate": !!self.model.validate ?  self.model.validate : undefined,
                    "statusId": !!statusId ? statusId : undefined,
                    "items": []
                }

                angular.forEach(itemList.items, function (item, key) {
                    var item = {

                        "id": item.id,
                        "itemStatusId": !!itemStatusId ? itemStatusId : undefined,
                        "intervention": !!item.intervention ? item.intervention : undefined,
                        "remark": !!item.interventionRemark ? item.interventionRemark : undefined
                    }
                    data.items.push(item);
                });

            //   console.log(data);
                $rootScope.startLoader();
                GenericService.serviceAction("PATCH", URI, data).then(
                    function (response) {
                        ClearFields(itemList);
                        $rootScope.stopLoader();
                        self.model.NavigateToPharmacologicalValidation();
                        growl.success('Pharmacology validation worklist  successffully saved');
                    },
                    function (err) {
                        $rootScope.stopLoader();
                        growl.error('Error while saving pharmacology validation worklist ');
                    });


            }
        }

        var NavigateToPharmacologicalValidation = function () {
            $state.go('pharmacologyValidaionWorklist');
          }

        var SavePopup = function () {
            var popup = angular.element('#confirmSave');
            popup.modal('show');
        }
        initializeController();
    }

    function config($stateProvider) {
        $stateProvider.state("pharmacologyValidationWorklistDetails", {
            url: "/pharmacologyValidationWorklistDetails",
            templateUrl: 'views/PharmacyInventory/InPatient/pharmacology-validation-worklist-details.html',
            controller: "PharmacologyValidationWorklistDetails.Controller",
            controllerAs: "vm"
        });
    }

    angular
        .module("myApp")
        .config(config)
        .controller("PharmacologyValidationWorklistDetails.Controller", pharmacologyValidationWorklistDetailsController)
})();