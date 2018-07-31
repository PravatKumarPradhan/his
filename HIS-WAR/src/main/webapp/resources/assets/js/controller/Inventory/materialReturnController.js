(function () {
    "use strict";

    function materialReturnController($scope, $rootScope, $http, CONSTANTS, GenericService, StatusService, cancelReasonService, commonDetailService, $state, growl) {
        var self = this;
        var returnId;
        //Default Constructor
        function initializeController() {
            $rootScope.loginpage = true;
            self.model = {
                fromStores: [{
                    "store": "All Stores"
                }],
                toStores: [{
                    "store": "All Stores"
                }],
                statuses: [{
                    "status": "All"
                }],
                selectAllRows: false,
                SelectedReturnIds: [],

            };
            StatusService.GetStatus().then(function (status) {
                self.model.statuselect = status;
            });

            cancelReasonService.GetReason().then(function (reason) {
                self.model.reasons = reason;
            });
            self.model.searchMaterialReturn = searchMaterialReturn;
            self.model.SelectAllReturns = SelectAllReturns;
            self.model.SelectReturnRequest = SelectReturnRequest;
            self.model.isSelected = isSelected;
            self.model.isDisable = isDisable;
            self.model.MaterialReturnDetails = MaterialReturnDetails;
            self.model.EditMaterialReturn = EditMaterialReturn;
            self.model.ApproveMaterialReturn = ApproveMaterialReturn;
            self.model.ValidateAndUpdate = ValidateAndUpdate;
            self.model.setStatus = setStatus; 
            self.model.AddNewMaterialReturn = AddNewMaterialReturn;

            getDropdownsList();
            searchMaterialReturn();
        }
        var getDropdownsList = function () {
            var mreturn = self.model;
            mreturn.fromStore = mreturn.fromStores[0];
            mreturn.toStore = mreturn.toStores[0];
            mreturn.status = mreturn.statuses[0];

            var URI = CONSTANTS.INVENTORY.STORE.MATERIAL_RETURN.DROPDOWN_API;

            $rootScope.startLoader();
            GenericService.serviceAction("GET", URI, {}).then(
                function (response) {
                    mreturn.fromStores = mreturn.fromStores.concat(response.data.fromStore);
                    mreturn.toStores = mreturn.toStores.concat(response.data.toStore);
                    mreturn.statuses = mreturn.statuses.concat(response.data.status);
                    $rootScope.stopLoader();
                    // GetStoreIndents();
                },
                function (err) {
                    $rootScope.stopLoader();
                });
        }

        var searchMaterialReturn = function () {
            var URI = CONSTANTS.INVENTORY.STORE.MATERIAL_RETURN.SEARCH_API;
            var data = {
                "fromStoreId": self.model.fromStore.id ? self.model.fromStore.id : undefined,
                "toStoreId": self.model.toStore.id ? self.model.toStore.id : undefined,
                "statusId": self.model.status.id ? self.model.status.id : undefined,
                "returnNo": self.model.returnNo ? self.model.returnNo : undefined
            };
            $rootScope.startLoader()
            GenericService.serviceAction("POST", URI, data).then(
                function (response) {
                    $rootScope.stopLoader();
                    for (var i = 0; i < response.data.length; i++) {
                        response.data[i]["isChecked"] = false;
                    }
                    self.model.searchMaterialReturnData = response.data;
                },
                function errorCallback(response) {
                    $rootScope.stopLoader();
                    growl.error('Error');
                });
        }

        var SelectAllReturns = function () {
            if (self.model.selectAllRows) {
                self.model.SelectedReturnIds = [];
                self.model.searchMaterialReturnData.forEach(function (mreturn) {
                    if (!isDisable(mreturn)) {
                        mreturn.isSelected = true;
                        self.model.searchMaterialReturnData = true;
                        self.model.SelectedReturnIds.push(mreturn.id);
                    }
                });
            } else {
                self.model.SelectedReturnIds = [];
                self.model.searchMaterialReturnData.forEach(function (mreturn) {
                    mreturn.isSelected = false;
                    self.model.isItemChecked = false;
                });
            }

        }

        var SelectReturnRequest = function (mreturn) {
            var returnIds = self.model.SelectedReturnIds;

            if (mreturn.isSelected) {
                returnIds.push(mreturn.id);
            } else {
                var index = returnIds.indexOf(mreturn.id);
                if (index > -1) {
                    returnIds.splice(index, 1);
                }
            }

            if (self.model.searchMaterialReturnData.every(isSelected)) {
                self.model.selectAllRows = true;
            } else {
                self.model.selectAllRows = false;
            }
            self.model.isItemChecked = false;
            self.model.searchMaterialReturnData.forEach(function (mreturn) {
                if (mreturn.isSelected == true) {
                    self.model.isItemChecked = true;
                    return;
                }
            });
        }

        var isSelected = function (mreturn) {
            if (!isDisable(mreturn))
                return mreturn.isSelected;
            else
                return true;
        }

        var isDisable = function (mreturn) {
            if (mreturn.status != 'New') {
                return true;
            }
            return false;
        }
        var EditMaterialReturn = function (mreturn) {
            commonDetailService.setDataId(mreturn.id);
            $state.go('addNewMaterialReturn');
        }

        var MaterialReturnDetails = function (mreturn) {
            commonDetailService.setDataId(mreturn.id);
            $state.go('detailsMaterialReturn');
        }

        var AddNewMaterialReturn = function () {
            commonDetailService.setDataId(null);
            $state.go('addNewMaterialReturn');
        }

        var ApproveMaterialReturn = function () {
            if (self.model.action == 'Update') {
                var URI = CONSTANTS.INVENTORY.STORE.MATERIAL_RETURN.APPROVE_API;

                var data = {
                    "returnId": self.model.SelectedReturnIds,
                    "statusId": setStatus('Pending'),
                    "approvalStatusId": setStatus('Pending Approval')
                };

                $rootScope.startLoader();
                GenericService.serviceAction("PATCH", URI, data).then(
                    function (response) {
                        self.model.SelectedReturnIds = [];
                        $rootScope.stopLoader();
                        growl.success(response.data.message);
                        searchMaterialReturn();
                    },
                    function (err) {
                        $rootScope.stopLoader();
                        growl.error(err.data.msg);
                    });
            } else if (self.model.action == 'Cancel') {

                var URI = CONSTANTS.INVENTORY.STORE.MATERIAL_RETURN.CANCEL_API + returnId;

                var data = {
                    "cancelReasonId": self.model.cancelReason,
                    "cancelNote": self.model.cancelNote,
                    "statusId": setStatus('Cancelled')
                };

                GenericService.serviceAction("PATCH", URI, data).then(
                    function (response) {
                        self.model.SelectedReturnIds = [];
                        $rootScope.stopLoader();
                        growl.success(response.data.message);
                        searchMaterialReturn();
                    },
                    function (err) {
                        $rootScope.stopLoader();
                        growl.error(err.data.msg);
                    });
            }

        }

        var ValidateAndUpdate = function (action, popupName, id) {
            returnId = id;
            self.model.action = action;
            var popup = angular.element('#' + popupName);
            popup.modal('show');
        }
        function setStatus(x) {
            var status = self.model.statuselect.find(function (obj) {
                return obj.status == x;
            });

            return status.id;
        } 
        initializeController();
    }


    function config($stateProvider) {
        $stateProvider.state("materialReturn", {
            url: "/materialReturn",
            templateUrl: 'views/PharmacyInventory/InPatient/material-return.html',
            controller: "MaterialReturn.Controller",
            controllerAs: "vm"
        });
    }

    angular
        .module("myApp")
        .config(config)
        .controller("MaterialReturn.Controller", materialReturnController);
})() 