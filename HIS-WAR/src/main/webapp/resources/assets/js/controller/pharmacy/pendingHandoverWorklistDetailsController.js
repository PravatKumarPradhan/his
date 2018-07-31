(function () {
    "use strict";

    function pendingHandoverWorklistDetailsController($scope, $rootScope, $http, CONSTANTS, GenericService, $filter, $state, growl, CurrencyService, detailService) {
        var self = this;

        //Default Constructor
        function initializeController() {
            $rootScope.loginpage = true;
            self.model = {};
            CurrencyService.GetCurrency().then(function (currency) {
                self.model.Currency = currency;
            })

            //Methods
            // self.model.GetPendingHandoverWorklistDetails = GetPendingHandoverWorklistDetails;
            self.model.SavePopup = SavePopup;
            self.model.SavePendingHandoverWorkList = SavePendingHandoverWorkList;

            GetPendingHandoverWorklistDetails(detailService.getId());
        }



        var GetPendingHandoverWorklistDetails = function (itemId) {

            var URI = CONSTANTS.PHARMACY.OP.PENDING_HANDOVER.DETAIL_API + itemId;

            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {

                    self.model.pendingHandoverWorklistDetailsList = response.data;

                });
            $rootScope.stopLoader();
        }


        var Validation = function (itemList) {
            var isValid = true;

            if (!itemList.items || itemList.items.length <= 0) {
                isValid = false;
                growl.error("Please add some items in the list");
            }
            else {
                if(self.model.receiver == undefined || self.model.receiver == ''){
                    isValid = false;
                    self.model.isReceiverValid = true;
                }
            }
            return isValid;
        }

        var ClearFields = function (itemList) {

            itemList.uhid = null;
            itemList.doa = null;
            itemList.patientDetail = null;
            itemList.doctorDetail = null;
            itemList.pdd = null;
            itemList.dob = null;
            itemList.wardName = null;
            itemList.bedNo = null;
            self.model.receiver = null;
            itemList.items = [];
        }

        var SavePendingHandoverWorkList = function (itemList) {


            if (Validation(itemList)) {
                var URI = CONSTANTS.PHARMACY.OP.PENDING_HANDOVER.SAVE_API;

                var data = {
                    "id": !!itemList.id ? itemList.id : undefined,
                    "receiverName": self.model.receiver
                    // "items": []
                }

                // angular.forEach(itemList.items, function (item, key) {
                //     var item = {

                //         "saleDetailId": item.id,
                //         "receiverName": self.model.receiver,
                //     }
                //     data.items.push(item);
                // });

                // console.log(data);
                $rootScope.startLoader();
                GenericService.serviceAction("PATCH", URI, data).then(
                    function (response) {
                      
                        $rootScope.stopLoader();
                        NavigateToPendingHandoverWorkList();
                        growl.success('pending handover worklist successffully saved');
                        ClearFields(itemList);
                    },
                    function (err) {
                        $rootScope.stopLoader();
                        growl.error('Error while saving pending handover worklist ');
                    });


            }
        }

        var NavigateToPendingHandoverWorkList = function () {
            document.getElementById('popupClose').click();
            setTimeout(function () {
                $state.go('pendingHandoverWorkList');
            }, 1000);
        }

        var SavePopup = function () {
            var popup = angular.element('#confirmSave');
            popup.modal('show');
        }

        initializeController();
    }


    function config($stateProvider) {
        $stateProvider.state("pendingHandoverWorkListDetails", {
            url: "/pendingHandoverWorkListDetails",
            templateUrl: 'views/PharmacyInventory/Store/pending-handover-worklist-details.html',
            controller: "PendingHandoverWorklistDetails.Controller",
            controllerAs: "vm"
        });
    }

    angular
        .module("myApp")
        .config(config)
        .controller("PendingHandoverWorklistDetails.Controller", pendingHandoverWorklistDetailsController)
})();
