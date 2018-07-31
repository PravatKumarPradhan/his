(function () {
    "use strict";

    function materialPendingListDetailsController($scope, $rootScope, $http, $state, commonDetailService, StatusService, CONSTANTS, GenericService, $filter, growl) {
        var self = this;
        var selectItemList = [];
        var masterId;
        //Default Constructor
        function initializeController() {
            $rootScope.loginpage = true;
            self.model = {

                requestData: [],


            };
            self.model.fromMaterialPending = fromMaterialPending;
            self.model.ApprovePending = ApprovePending;
            self.model.uomSelectEvent = uomSelectEvent;
            self.model.batchOnChange = batchOnChange;
            self.model.AddDuplicateRow = AddDuplicateRow;
            self.model.validatePending = validatePending;
            self.model.RemoveRowData = RemoveRowData;
            self.model.setStatus = setStatus;

            fromMaterialPending();
        }
        var uomSelectEvent = function (batch) {
            batch.uom.forEach(function (uom) {
                if (uom.uomTypeId == batch.uomTypeId) batch.selectedUom = uom;
            });
        }

        var batchOnChange = function (batches, item) {
            var batch = item.batch.find(function (obj) {
                return obj.batchId == batches.batchId;
            });

            var uom = batch.uom.find(function (obj) {
                return obj.storeUom;
            });

            batches.expiry = batch.expiry;
            // batches.issueQuantity = batch.issueQuantity;
            if (uom) {
                batches.uomType = uom.uomType;
                batches.uomTypeId = uom.uomTypeId;
                batches.uomUnit = uom.uomUnit;
                batches.uomUnitId = uom.uomUnitId;
                batches.displayQuantity = batch.availableStockQuantity / uom.conversion;
            }
        }

        function fromMaterialPending() {

            var pendingId = commonDetailService.getDataId();
            var URI = CONSTANTS.INVENTORY.STORE.MATERIAL_ISSUE.MATERIAL_PENDING_ISSUE.DETAILS_API + pendingId;
            var data = {};
            GenericService.serviceAction("GET", URI, data).then(
                function (response) {
                    self.model.materialIssuePendingDetail = response.data;
                    self.model.materialIssuePendingDetail.items.forEach(function (item) {
                        item.batches.forEach(function (batch) {
                            batch.batchId = batch.batchId.toString();
                            self.model.batchOnChange(batch, item);
                        });
                    });
                });
        }


        var ApprovePending = function () {
            var store = self.model;
            var pId = commonDetailService.getDataId();

            var URI = CONSTANTS.INVENTORY.STORE.MATERIAL_ISSUE.MATERIAL_PENDING_ISSUE.SAVE_API;

            // var URI = constants.apiurl + constants.inventoryApi + '/stores/material/issue/pending';
            if (validatePending()) {
                var data = {
                    "id": pId,
                    "statusId": setStatus('Completed'),
                    //"approvalStatusId": 1,
                    "carrierId": parseInt(self.model.id),
                    "remark": self.model.remark,
                    "items": []
                };

                angular.forEach(store.materialIssuePendingDetail.items, function (item, key) {
                    if (item.issueQuantity > 0) {
                        var storeItem = {
                            "id": item.id,
                            //"statusId": 17,
                            "issueQuantity": item.issueQuantity,
                            "batches": []
                        }

                        angular.forEach(item.batches, function (batch) {
                            var batch = {
                                "id": batch.id ? batch.id : undefined,
                                "batchId": batch.batchId,
                                "uomTypeId": batch.uomTypeId,
                                "uomUnitId": batch.uomUnitId,
                                "issueQuantity": batch.issueQuantity
                            }
                            storeItem.batches.push(batch);
                        });

                        data.items.push(storeItem);
                    }
                });

                if (data.items.length > 0) {
                    $rootScope.startLoader();
                    GenericService.serviceAction("POST", URI, data).then(
                        function (response) {
                            // ClearRequests();
                            $rootScope.stopLoader();
                            NavigteToMaterialPending();
                            growl.success(response.data.message);
                        },
                        function (err) {
                            $rootScope.stopLoader();
                            growl.error('Something Went Wrong');
                        });
                }
                else {
                    $rootScope.stopLoader();
                    NavigteToMaterialPending();
                }
            }
        }
        function validatePending() {

            var isValid = true;
            var materialPendingDetail = self.model.materialIssuePendingDetail.items;
            // !self.model.fromStore.id || !self.model.toStore.id || !self.model.priority.id || 
            if (materialPendingDetail.length < 1) {
                growl.error('Error', {
                    title: "Please validate all required fields"
                });
                isValid = false;
            }
            for (let pendingDetail of materialPendingDetail) {
                if (pendingDetail.issueQuantity < 0) {
                    growl.error('Error', {
                        title: "Please Enter Valid Issue Quantity"
                    });
                    isValid = false;
                }
            }
            for (let pendingDetail of materialPendingDetail) {
                if (pendingDetail.issueQuantity > pendingDetail.pendingQuantity) {
                    growl.error('Error', {
                        title: "Issue Quantity Should be less than Pending Quantity"
                    });
                    isValid = false;
                }
            }

            return isValid;
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

        var AddDuplicateRow = function (item) {
            var data = angular.copy(item.batches[0]);
            data.expiry = '';
            data.uomType = '';
            data.uomUnit = '';
            data.batchId = '';
            data.displayQuantity = '';
            data.issueQuantity = '';
            data.batchNo = '';
            data.statusId = '';
            item.batches.push(data);
            // batch.splice(index + 1, 0, angular.copy(batch[index]));
        }


        var RemoveRowData = function (item) {
            var index = item.batches.indexOf(item);
            item.batches.splice(index, 1);
        }

        var NavigteToMaterialPending = function () {
            $state.go('materialIssue.pendingIssue');
        }

        initializeController();
    }

    function config($stateProvider) {
        $stateProvider.state("detailsMaterialPending", {
            url: '/detailsMaterialPending',
            templateUrl: 'views/PharmacyInventory/InPatient/details-material-pending.html',
            controller: "MaterialPendingListDetails.Controller",
            controllerAs: 'vm'
        });
    }

    angular
        .module("myApp")
        .config(config)
        .controller("MaterialPendingListDetails.Controller", materialPendingListDetailsController)
})();  