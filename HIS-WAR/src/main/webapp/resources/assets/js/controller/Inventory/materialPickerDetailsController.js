(function () {
    "use strict";

    function materialPickerDetailsController($scope, $rootScope, $http, $state, CONSTANTS, StatusService, materialPickerService,GenericService, $filter, growl) {
        var self = this;
        var selectItemList = [];
        var masterId;
        //Default Constructor
        function initializeController() {
            $rootScope.loginpage = true;
            self.model = {
                id: '',
                requestData: [],


            };
            self.model.fromMaterialPicker = fromMaterialPicker;
            self.model.ApprovePicker = ApprovePicker;
            self.model.uomSelectEvent = uomSelectEvent;
            self.model.materialPickerDetail = [];
            self.model.batchOnChange = batchOnChange;
            //self.model.uomOnChange = uomOnChange;
            self.model.validatePicker = validatePicker;
            self.model.AddDuplicateRow = AddDuplicateRow;
            self.model.RemoveRowData = RemoveRowData;
            self.model.setStatus = setStatus;
            // self.model.ApproveMaterialPicker =ApproveMaterialPicker;
            fromMaterialPicker();
        }


        var batchOnChange = function (selectedBatch, batch) {
            batch.expiry = selectedBatch.expiry;
            //batch.availableStockQuantity = selectedBatch.availableStockQuantity;
            // batch.uomList = selectedBatch.uom;

            // batch.displayQuantity = batch.availableStockQuantity / 
            // (batch.conversion ? batch.conversion : 1);
            var storeUom = selectedBatch.uom.find(function (obj) {
                return obj.storeUom == true;
            });
            batch.uomType = storeUom.uomType;
            batch.uomTypeId = storeUom.uomTypeId;
            batch.uomUnit = storeUom.uomUnit;
            batch.uomUnitId = storeUom.uomUnitId;
            batch.displayQuantity = selectedBatch.availableStockQuantity / storeUom.conversion;
        }

        function fromMaterialPicker() {
            var id = materialPickerService.getMaterialPickerId();

            var URI = CONSTANTS.INVENTORY.STORE.MATERIAL_PICKER.DETAILS_API + id;

            // var URI = constants.apiurl + constants.inventoryApi + '/stores/material/picker/' + id;
            var data = {};
            GenericService.serviceAction("GET", URI, data).then(
                function (response) {
                    self.model.materialPickerDetail = response.data;

                    self.model.materialPickerDetail.items.forEach(function (item) {
                        item['batchList'] = [item.batch[0]];
                        item['batchList']['uomList'] = [];
                        item['tempPickerQuantity'] = item.pickerQuantity;
                    });
                });
        }

        var uomSelectEvent = function (batch) {
            batch.uom.forEach(function (uom) {
                if (uom.uomTypeId == batch.uomTypeId) batch.selectedUom = uom;
            });
        }



        var ApprovePicker = function () { 
            var store = self.model;
            var URI = CONSTANTS.INVENTORY.STORE.MATERIAL_PICKER.SAVE_API;

            // var URI = constants.apiurl + constants.inventoryApi + '/stores/material/picker';
            if (validatePicker()) {
                var data = {
                    "id": materialPickerService.getMaterialPickerId(),
                    "statusId": setStatus('Pending'),
                    //"approvalStatusId": 1,
                    "pickerId": parseInt(self.model.id),
                    "remark": self.model.remark,
                    "items": []
                };

                var statusId = 0;
                angular.forEach(store.materialPickerDetail.items, function (item, key) {
                    if (item.pickerQuantity > 0) {
                        var storeItem = {
                            "id": item.id,
                            //"statusId": 17,
                            "pickerQuantity": parseInt(item.pickerQuantity),
                            "batches": []
                        }

                        angular.forEach(item.batchList, function (batch) {
                            var batch = {
                                "batchId": batch.selectedBatch.batchId,
                                "uomTypeId": batch.uomTypeId,
                                "uomUnitId": batch.uomUnitId,
                                "pickerQuantity": parseInt(batch.pickerQuantity)
                            }
                            storeItem.batches.push(batch);
                        });

                        if (data.statusId == setStatus('Pending') && parseInt(item.pickerQuantity) > 0 &&
                            parseInt(item.pickerQuantity) < item.pendingQuantity) {
                            data.statusId = setStatus('Partially Completed');
                        }
                        else if (data.statusId == setStatus('Pending') && parseInt(item.pickerQuantity) == item.pendingQuantity) {
                            data.statusId = setStatus('Completed');
                        }

                        data.items.push(storeItem);
                    }
                });

                if (data.items.length > 0) {
                    $rootScope.startLoader();
                    GenericService.serviceAction("POST", URI, data).then(
                        function (response) {
                            // ClearRequests();
                            $rootScope.stopLoader();
                            NavigteToMaterialPicker();
                            growl.success(response.data.message);
                        },
                        function (err) {
                            $rootScope.stopLoader();
                            growl.error('Something Went Wrong');
                        });
                }
                else {
                    $rootScope.stopLoader();
                    NavigteToMaterialPicker();
                }
            }
        }

        function setStatus(x) {
            var status = self.model.statuses.find(function (obj) {
                return obj.status == x;
            });

            return status.id;
        }

        StatusService.GetStatus().then(function (status) {
            self.model.statuses = status;
        });


        var NavigteToMaterialPicker = function () {
            $state.go('materialPicker');
        }

        var AddDuplicateRow = function (item) {
            var data = angular.copy(item.batchList[0]);
            data.expiry = "";
            data.selectedBatch.expiry = "";
            data.uomType = '';
            data.uomUnit = '';
            data.batchId = '';
            data.displayQuantity = '';
            data.issueQuantity = '';
            data.batchNo = '';
            data.statusId = '';
            item.batchList.push(data); 
        }


        var RemoveRowData = function (item) {
            // item.batchList.pop(item.batchList[0]);
            //item.batchList = item.batchList.slice(0, item).concat(item.batchList.slice(item + 1))
            var index = item.batchList.indexOf(item);
            item.batchList.splice(index, 1);
        }

        function validatePicker() {

            var isValid = true;
            var materialPickerDetail = self.model.materialPickerDetail.items;
            // !self.model.fromStore.id || !self.model.toStore.id || !self.model.priority.id || 
            if (materialPickerDetail.length < 1) {
                growl.error('Error', {
                    title: "Please validate all required fields"
                });
                isValid = false;
            }
            
            for (let pickerDetail of materialPickerDetail) {
                if (!pickerDetail.pickerQuantity || pickerDetail.pickerQuantity > pickerDetail.pendingQuantity) {
                    growl.error('Error', {
                        title: "Please Enter Valid Picker Quantity"
                    });
                    isValid = false;
                }
            }  

            return isValid;
            // for (let batchDetail of pickerDetail.batch) {
            //   if (batchDetail.pickerQuantity > ItemDetail.maxOrderQuantity) {
            //     growl.error('Error', {
            //       title: "Stock Indent Quantity must be less than Max Order Quantity"
            //     });
            //     isValid = false;
            //   }
        }


        initializeController();
    }



    function config($stateProvider) {
        $stateProvider.state("materialPickerDetails", {
            url: '/detailsMaterialPicker',
            templateUrl: 'views/PharmacyInventory/InPatient/details-material-picker.html',
            controller: "MaterialPickerDetails.Controller",
            controllerAs: 'vm'
        });
    }

    angular
        .module("myApp")
        .config(config)
        .controller("MaterialPickerDetails.Controller", materialPickerDetailsController)

    angular
        .module("myApp")
        .filter('customSumBy', function () {
            return function (collection, column, value) {
                var total = 0; if (!value) value = 0;
                if (collection) {
                    collection.forEach(function (item) {
                        if(item[column])
                         total += parseFloat(item[column]);
                    });
                    total = total + value;
                }
                else total = value;
                return total;
            };
        });
})();
