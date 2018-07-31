(function () {
    "use strict";

    function detailsPatientIssueAcceptanceController($scope, $rootScope, $http, CONSTANTS, GenericService, $filter, $state, growl, CurrencyService, detailService, rejectReasonService) {
        var self = this;
        var selectedItemId;

        //Default Constructor
        function initializeController() {
            $rootScope.loginpage = true;

            self.model = {
                isAcceptQuantityValid: [],
                SelectedRequestItems: []
            };

            //Methods
            CurrencyService.GetCurrency().then(function (currency) {
                self.model.Currency = currency;
            })


            self.model.GetMedicationIssueAcceptanceDetails = GetMedicationIssueAcceptanceDetails;
            self.model.SavePopup = SavePopup;
            self.model.SelectAllRequests = SelectAllRequests;
            self.model.SelectRequestItem = SelectRequestItem;
            self.model.RejectQty = RejectQty;
            self.model.SaveRejectPopup = SaveRejectPopup;
            self.model.ValidateBill = ValidateBill;
            self.model.SaveIssueAcceptance = SaveIssueAcceptance;

            GetMedicationIssueAcceptanceDetails(detailService.getId());
        }
        rejectReasonService.GetReason().then(function (reason) {
            self.model.rejectReasons = reason;
        });

        var GetMedicationIssueAcceptanceDetails = function (itemId) {

            var URI = CONSTANTS.PHARMACY.OP.PATIENT_ISSUE_ACCEPTANCE.MEDICATION_DETAIL_API + itemId;

            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {

                    self.model.medicationIssueAcceptanceDetailslist = response.data;

                });
            $rootScope.stopLoader();
        }

        var SelectAllRequests = function () {

            if (self.model.selectAllRows) {
                self.model.SelectedRequestItems = [];
                self.model.medicationIssueAcceptanceDetailslist.items.forEach(function (item) {
                    item.isSelected = true;
                    self.model.SelectedRequestItems.push(item);

                });
            } else {
                self.model.SelectedRequestItems = [];
                self.model.medicationIssueAcceptanceDetailslist.items.forEach(function (item) {
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

            if (self.model.medicationIssueAcceptanceDetailslist.items.every(isSelected)) {
                self.model.selectAllRows = true;
            } else {
                self.model.selectAllRows = false;
            }
            // console.log(itemIds);
        }

        var ValidateBill = function (itemList) {
            var isValid = true;

            if(self.model.rejectionFlag == false){
                var isValid = false;
                growl.error("Please save rejection reason");
            }

            if (!self.model.SelectedRequestItems || self.model.SelectedRequestItems.length <= 0) {
                isValid = false;
                growl.error("Please add some items in the bill");
            } else {
                self.model.SelectedRequestItems.forEach(function (item) {
                    item['isAcceptQuantityValid'] = false;

                    if ((item.quantityIssued < (item.acceptQuantity)) || !item.acceptQuantity) {
                        item['isAcceptQuantityValid'] = true;
                        isValid = false;
                        growl.error("Please enter valid quantity");
                    }

                    if (item.acceptQuantity == 0) {
                        item['isAcceptQuantityValid'] = true;
                        isValid = false;
                        growl.error("Please enter quantity greater than zero");
                    }

                    
                });

            }

            return isValid;
        }

        var ClearFields = function (itemList) {
            itemList.Id = "1" + (new Date()).getTime();
            itemList.uhid = null;
            itemList.patientDetail = null;
            itemList.wardName = null;
            itemList.dob = null;
            itemList.pdd = null;
            itemList.doa = null;
            itemList.bedNo = null;
            itemList.doctorDetail = null;
            itemList.issueNo = null;
            itemList.issueDate = null;
            itemList.indentNo = null;
            itemList.store = null;
            itemList.items = [];
        }

        var RejectQty = function (item) {

            if (item.isRejected) {
                selectedItemId = item.id;
                self.model.qty = item.rejectQuantity;
                self.model.rejectReason = self.model.rejectReasons[0];

                self.model.action = 'Open';
                var popup = angular.element('#qtyaccepatReject');
                popup.modal('show');
             
              } 
        }

        var SaveRejectPopup = function () {
            
            self.model.SelectedRequestItems.forEach(function (item) {
                
                if (item.id == selectedItemId) {
                    item.rejectQuantity = self.model.qty;
                    item.acceptQuantity = item.quantityIssued - self.model.qty;
                    item['rejectReasonId'] = self.model.rejectReason.id;
                    item['note'] = self.model.rejectNote;
                }
            });
            // }
        }

        var SaveIssueAcceptance = function (itemList) {
            if (ValidateBill(itemList)) {

                var URI =  CONSTANTS.PHARMACY.OP.PATIENT_ISSUE_ACCEPTANCE.MEDICATION_SAVE_API;
                var data = {

                    "id": itemList.id,
                    "items": []
                }

                angular.forEach(itemList.items, function (item, key) {
                    if (item.isSelected) {
                        var item = {
                            "id": item.id,
                            "acceptQuantity": Number(item.acceptQuantity),
                            "rejectQuantity": item.rejectQuantity,
                            "reasonId": item.rejectReasonId,
                            "note": item.note,
                            "batchMapperId":item.batchMapperId
                        }
                        data.items.push(item);
                    }
                });
                //  console.log(data);
                $rootScope.startLoader();
                GenericService.serviceAction("PATCH", URI, data).then(
                    function (response) {
                        $rootScope.stopLoader();
                        ClearFields(itemList);
                        growl.success('Patient issue acceptance successffully saved');
                       
                    },
                    function (err) {
                        $rootScope.stopLoader();
                        growl.error('Error while saving patient issue acceptance');
                    });
            }
        }

        var SavePopup = function () {
            var popup = angular.element('#confirmSave');
            popup.modal('show');
        }
        initializeController();
    }

    function config($stateProvider) {
        $stateProvider.state("detailsPatientIssueAcceptance", {
            url: "/detailsPatientIssueAcceptance",
            templateUrl: 'views/PharmacyInventory/InPatient/details-patient-issue-acceptance.html',
            controller: "DetailsPatientIssueAcceptance.Controller",
            controllerAs: "vm"
        });
    }

    angular
        .module("myApp")
        .config(config)
        .controller("DetailsPatientIssueAcceptance.Controller", detailsPatientIssueAcceptanceController)
})();