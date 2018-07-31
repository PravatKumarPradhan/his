(function () {
    "use strict";

    function detailsAcceptanceOfEmptyAmpoulesController($scope, $rootScope, $http, CONSTANTS, GenericService, $filter, $state, growl, CurrencyService, StatusService, commonDetailService) {
        var self = this;

        //Default Constructor
        function initializeController() {
            $rootScope.loginpage = true;


            self.model = {
                SelectedRequestItems: []
            };

            //Methods
            CurrencyService.GetCurrency().then(function (currency) {
                self.model.Currency = currency;
            })
            StatusService.GetStatus().then(function (status) {
                self.model.Status = status;
            })

            self.model.GetAccepatnceOfEmptyAmpoulesDetails = GetAccepatnceOfEmptyAmpoulesDetails;
            self.model.SavePopup = SavePopup;
            self.model.SelectAllRequests = SelectAllRequests;
            self.model.SelectRequestItem = SelectRequestItem;
            self.model.SaveReturnEmptyAmpoules = SaveReturnEmptyAmpoules;
            self.model.ClearFields = ClearFields;
            self.model.NavigateToAcceptanceOfEmptyAmpoules = NavigateToAcceptanceOfEmptyAmpoules;

            GetAccepatnceOfEmptyAmpoulesDetails(commonDetailService.getDataId());
        }

        var GetAccepatnceOfEmptyAmpoulesDetails = function (itemId) {
            var URI = CONSTANTS.PHARMACY.NARCOTICS.ACCEPTANCE_EMPTY_AMPOULES.DETAIL_API + itemId;

            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    self.model.emptyAmpoulesDetails = response.data;
                });
            $rootScope.stopLoader();
        }

        var SelectAllRequests = function () {

            if (self.model.selectAllRows) {
                self.model.SelectedRequestItems = [];
                self.model.emptyAmpoulesDetails.items.forEach(function (item) {
                    item.isSelected = true;
                    self.model.SelectedRequestItems.push(item);

                });
            } else {
                self.model.SelectedRequestItems = [];
                self.model.emptyAmpoulesDetails.items.forEach(function (item) {
                    item.isSelected = false;
                });
            }
            // console.log(self.model.SelectedRequestItems);
        }

        var isSelected = function (item) {
            return item.isSelected;
        }

        var SelectRequestItem = function (item) {

            var itemIds = self.model.SelectedRequestItems;

            if (item.isSelected) {
                itemIds.push(item);
            } else {
                var index = itemIds.indexOf(item);
                if (index > -1) {
                    itemIds.splice(index, 1);
                }
            }

            if (self.model.emptyAmpoulesDetails.items.every(isSelected)) {
                self.model.selectAllRows = true;
            } else {
                self.model.selectAllRows = false;
            }
            // console.log(itemIds);
        }

        var SavePopup = function () {
            var popup = angular.element('#confirmSave');
            popup.modal('show');
        }

        var SaveReturnEmptyAmpoules = function (bill) {

            if (ValidateBill(bill)) {
                var URI = CONSTANTS.PHARMACY.NARCOTICS.ACCEPTANCE_EMPTY_AMPOULES.SAVE_API;

                var data = {
                    "id": !!bill.id ? bill.id : undefined,
                    "items": []
                }
                angular.forEach(bill.items, function (item, key) {
                    if (item.isSelected) {
                        var returnItem = {
                            "id": item.id,
                            "returnQuantity": Number(item.returnQuantity),
                            "pendingQuantity": Number(item.pendingQuantity),
                        }
                        data.items.push(returnItem);
                    }
                });

                console.log(data)

                $rootScope.startLoader();
                GenericService.serviceAction("POST", URI, data).then(
                    function (response) {
                        ClearFields(bill);
                        growl.success('Acceptance of empty ampoules successffully saved');
                        $rootScope.stopLoader();
                        NavigateToAcceptanceOfEmptyAmpoules();
                    },
                    function (err) {
                        $rootScope.stopLoader();
                        growl.error('Error while saving acceptance of empty ampoules');
                    });
            }
        }

        var NavigateToAcceptanceOfEmptyAmpoules = function () {
            document.getElementById('popupClose').click();
            setTimeout(function () {
                $state.go('acceptanceOfEmptyAmpoules');
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

        var ValidateBill = function () {

            var isValid = true;

            if (!self.model.SelectedRequestItems || self.model.SelectedRequestItems.length <= 0) {
                isValid = false;
                growl.error("No items in the list to save");
            } else {
                self.model.SelectedRequestItems.forEach(function (billitem) {

                    billitem['isQuantityValid'] = false;

                    if ((!billitem.returnQuantity) || (billitem.quantityIssued < billitem.returnQuantity)) {

                        billitem['isQuantityValid'] = true;
                        isValid = false;
                        growl.error('Enter valid quantity')
                    }
                });
            }

            return isValid;
        }


        initializeController();
    }

    function config($stateProvider) {
        $stateProvider.state("detailsAcceptanceOfEmptyAmpoules", {
            url: "/detailsAcceptanceOfEmptyAmpoules",
            templateUrl: 'views/PharmacyInventory/NarcoticsStore/details-acceptance-of-empty-ampoules.html',
            controller: "DetailsAcceptanceOfEmptyAmpoules.Controller",
            controllerAs: "vm"
        });
    }

    angular
        .module("myApp")
        .config(config)
        .controller("DetailsAcceptanceOfEmptyAmpoules.Controller", detailsAcceptanceOfEmptyAmpoulesController)
})();