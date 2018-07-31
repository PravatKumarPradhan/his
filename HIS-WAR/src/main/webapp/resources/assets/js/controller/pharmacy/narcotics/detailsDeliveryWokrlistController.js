

(function () {
    "use strict";

    function detailsDeliveryWokrlistController($scope, $rootScope, $http, CONSTANTS, GenericService, $filter, $state, growl, CurrencyService, StatusService, commonDetailService) {
        var self = this;
       
        //Default Constructor
        function initializeController() {
            $rootScope.loginpage = true;


            self.model = {
                carrierList: [{
                    "name": "Select Carrier"
                }]               
            };

            //Methods
            CurrencyService.GetCurrency().then(function (currency) {
                self.model.Currency = currency;
            })
            StatusService.GetStatus().then(function (status) {
                self.model.Status = status;
            })

            self.model.GetDetailDeliveryWorklist = GetDetailDeliveryWorklist;
            self.model.SavePopup = SavePopup;
            self.model.SaveDeliveryWorklist = SaveDeliveryWorklist;
            self.model.ClearFields = ClearFields;

            GetDetailDeliveryWorklist(commonDetailService.getDataId());
        }

        var GetDetailDeliveryWorklist = function (itemId) {
            var URI =  CONSTANTS.PHARMACY.NARCOTICS.DELIVERY_WORKLIST.DETAIL_API + itemId;
            self.model.carrier = self.model.carrierList[0];

            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    self.model.deliveryWorklistDetails = response.data;
                    self.model.carrierList =  self.model.carrierList.concat(response.data.carriers);
                });
            $rootScope.stopLoader();
        }


        var SavePopup = function () {
            var popup = angular.element('#confirmSave');
            popup.modal('show');
        }

        var SaveDeliveryWorklist = function (bill) {

            if (ValidateBill(bill)) {
                var URI = CONSTANTS.PHARMACY.NARCOTICS.DELIVERY_WORKLIST.SAVE_API;

                var data = {
                    "id": !!bill.id ? bill.id : undefined,
                    "carrierId": !!self.model.carrier && !!self.model.carrier.id ? self.model.carrier.id : undefined,
                }

                console.log(data)

                $rootScope.startLoader();
                GenericService.serviceAction("POST", URI, data).then(
                    function (response) {
                        ClearFields(bill);
                        growl.success('Delivery worklist successffully saved');
                        $rootScope.stopLoader();
                        NavigateToDeliveryWorklist();
                    },
                    function (err) {
                        $rootScope.stopLoader();
                        growl.error('Error while saving delivery worklist');
                    });
            }
        }

        var NavigateToDeliveryWorklist = function () {
            document.getElementById('popupClose').click();
            setTimeout(function () {
                $state.go('deliveryWokrlist');
            }, 1000);
        }

        var ClearFields = function (bill) {

            bill.Id = "1" + (new Date()).getTime();
            bill.uhid = null;
            bill.patientDetail = null;
            bill.dob = null;
            bill.pdd = null;
            bill.doa = null;
            bill.wardName = null;
            bill.bedNo = null;
            bill.prescriptionDate = null;
            bill.doctorDetail = null;
            bill.prescriptionId = null;
           
            bill.items = [];
        }

        var ValidateBill = function (bill) {

            var isValid = true;

            if (!bill.items || bill.items.length <= 0) {
                isValid = false;
                growl.error("Please add some items in the bill");
            }
            if (!self.model.carrier.id) {
                isValid = false;
                self.model.isCarrierValid = true
                growl.error("Please select carrier");
            }
       
            return isValid;
        }


        initializeController();
    }

    function config($stateProvider) {
        $stateProvider.state("detailsDeliveryWokrlist", {
            url: "/detailsDeliveryWokrlist",
            templateUrl: 'views/PharmacyInventory/NarcoticsStore/details-delivery-wokrlist.html',
            controller: "DetailsDeliveryWokrlistController.Controller",
            controllerAs: "vm"
        });
    }

    angular
        .module("myApp")
        .config(config)
        .controller("DetailsDeliveryWokrlistController.Controller", detailsDeliveryWokrlistController)
})();
