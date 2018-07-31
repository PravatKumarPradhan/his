(function () {
    "use strict";

    function detailsPatientReturnAcceptanceMedicationController($scope, $rootScope, $http, GenericService, $filter, $state, growl, CurrencyService, commonDetailService, CONSTANTS, rejectReasonService, StatusService) {
        var self = this;
        var selectedItemId;
       

        //Default Constructor
        function initializeController() {
            $rootScope.loginpage = true;

            self.model = {
                isRejectQuantityValid: [],
                SelectedRequestItems: [],
               
            };

            //Methods
            CurrencyService.GetCurrency().then(function (currency) {
                self.model.Currency = currency;
            })


            self.model.GetDetailsPatientReturnAcceptanceMedication = GetDetailsPatientReturnAcceptanceMedication;
            self.model.SavePopup = SavePopup;
            self.model.SelectAllRequests = SelectAllRequests;
            self.model.SelectRequestItem = SelectRequestItem;
            self.model.RejectQty = RejectQty;
            self.model.SaveRejectPopup = SaveRejectPopup;
            self.model.ValidateBill = ValidateBill;
            self.model.SavePatientReturnAcceptance = SavePatientReturnAcceptance;
            self.model.NevigateToPatientReturnAcceptance = NevigateToPatientReturnAcceptance;

            GetDetailsPatientReturnAcceptanceMedication(commonDetailService.getDataId());
        }
        rejectReasonService.GetReason().then(function (reason) {
            self.model.rejectReasons = reason;
        });

        StatusService.GetStatus().then(function (status) {
            self.model.statuselect = status;
        });

        var GetDetailsPatientReturnAcceptanceMedication = function (itemId) {

            var URI =  CONSTANTS.PHARMACY.IP.PATIENT_RETURN_ACCEPTANCE.MEDICATION_DETAIL_API + itemId;

            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {

                    self.model.detailsPatientReturnAcceptanceMedicationList = response.data;
                });
            $rootScope.stopLoader();
        }

        var SelectAllRequests = function () {

            if (self.model.selectAllRows) {
                self.model.SelectedRequestItems = [];
                self.model.detailsPatientReturnAcceptanceMedicationList.items.forEach(function (item) {
                    item.isSelected = true;
                    self.model.SelectedRequestItems.push(item);

                });
            } else {
                self.model.SelectedRequestItems = [];
                self.model.detailsPatientReturnAcceptanceMedicationList.items.forEach(function (item) {
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

            if (self.model.detailsPatientReturnAcceptanceMedicationList.items.every(isSelected)) {
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
                    item['isRejectQuantityValid'] = false;

                    if ((item.returnQuantity < (item.acceptQuantity)) || !item.acceptQuantity) {
                        item['isRejectQuantityValid'] = true;
                        isValid = false;
                        growl.error("Please enter valid quantity");
                    }

                    if (item.acceptQuantity == 0) {
                        item['isRejectQuantityValid'] = true;
                        isValid = false;
                        growl.error("Please enter quantity greater than zero");
                    }
                });
            }
            return isValid;
        }

        var ClearFields = function (itemList) {

            itemList.Id = "1" + (new Date()).getTime();
            itemList.rejectedNo = null;
            itemList.rejectedDate = null;
            itemList.wardName = null;
            itemList.uhid = null;
            itemList.patientDetail = null;
            itemList.doctorDetail = null;
            itemList.store = null;
            itemList.items = [];
        }

        var RejectQty = function (item) {

            if (item.isRejected) {
                selectedItemId = item.id;
                self.model.qty = item.rejectQuantity;
                self.model.rejectReason = self.model.rejectReasons[0];
                self.model. rejectionFlag = false;

                self.model.action = 'Open';
                var popup = angular.element('#qtyaccepatReject');
                popup.modal('show');
              } 
        }

        var SaveRejectPopup = function () {

            self.model.SelectedRequestItems.forEach(function (item) {
                
                if (item.id == selectedItemId) {
                    item.rejectQuantity = self.model.qty;
                    item.acceptQuantity = item.returnQuantity - self.model.qty;
                    item['rejectReasonId'] = self.model.rejectReason.id;
                    item['note'] = self.model.rejectNote;
                }
            });
        }

        var SetStatus = function (x) {
            var status = self.model.statuselect.find(function (obj) {
                return obj.status == x;
            });
            return status.id;
        }

        var SavePatientReturnAcceptance = function (itemList) {
            debugger
            if (ValidateBill(itemList)) {

                var URI =  CONSTANTS.PHARMACY.IP.PATIENT_RETURN_ACCEPTANCE.MEDICATION_SAVE_API;

                var data = {
                    "id": itemList.id,
                    "statusId": SetStatus('Cancelled'),
                    "items": []
                }
                angular.forEach(itemList.items, function (item, key) {
                    if (item.isSelected) {
                        var item = {
                            "id": item.id,
                            "acceptQuantity": Number(item.acceptQuantity),
                            "rejectedQuantity": item.rejectQuantity,
                            "rejectReason": item.rejectReasonId,
                            "rejectedNote": item.note,
                            "issueDetailBatchMapper":item.issueDetailBatchMapper,
                        }
                         data.items.push(item);
                    }
                });
                // console.log(data);
                $rootScope.startLoader();
                GenericService.serviceAction("PATCH", URI, data).then(
                    function (response) {
                        $rootScope.stopLoader();
                        ClearFields(itemList);
                        NevigateToPatientReturnAcceptance();
                        growl.success('Patient return acceptance successffully saved');
                    },
                    function (err) {
                        $rootScope.stopLoader();
                        growl.error('Error while saving patient return acceptance');
                    });
            }
        }

        var SavePopup = function () {
            var popup = angular.element('#confirmSave');
            popup.modal('show');
        }

        var NevigateToPatientReturnAcceptance = function () {
            document.getElementById('popupClose').click();
            setTimeout(function () {
                $state.go('patientReturnAcceptance');
            }, 1000);
        }

        initializeController();
    }

    function config($stateProvider) {
        $stateProvider.state("detailsPatientReturnAcceptanceMedication", {
            url: "/detailsPatientReturnAcceptanceMedication",
            templateUrl: 'views/PharmacyInventory/InPatient/details-medication-patient-return-acceptance.html',
            controller: "DetailsPatientReturnAcceptanceMedication.Controller",
            controllerAs: "vm"
        });
    }

    angular
        .module("myApp")
        .config(config)
        .controller("DetailsPatientReturnAcceptanceMedication.Controller", detailsPatientReturnAcceptanceMedicationController)
})();

