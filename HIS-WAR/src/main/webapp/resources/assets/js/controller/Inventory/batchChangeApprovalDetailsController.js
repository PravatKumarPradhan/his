(function () {
  "use strict";

  function batchChangeApprovalDetailsController($scope, $rootScope, $http, CONSTANTS, StatusService, GenericService, rejectReasonService, $state, commonDetailService, growl) {
    var self = this;
    var batchId;
    //Default Constructor
    function initializeController() {
      $rootScope.loginpage = true;
      self.model = {
        selectAllRows: false,
        SelectedItems: [],
        SelectedRequestIds: []


      };

      rejectReasonService.GetReason().then(function (reason) {
        self.model.rejectReasons = reason;
      });
      StatusService.GetStatus().then(function (status) {
        self.model.statuses = status;
      });

      self.model.fromBatchChangeApproval = fromBatchChangeApproval;
      self.model.SelectAllRequests = SelectAllRequests;
      self.model.SelectBatch = SelectBatch;
      self.model.isDisable = isDisable;
      self.model.isSelected = isSelected;
      self.model.RejectionPopup = RejectionPopup;
      self.model.SaveApproval = SaveApproval;
      self.model.NavigateToBack = NavigateToBack;
      self.model.setStatus = setStatus;

      fromBatchChangeApproval();
    }


    function fromBatchChangeApproval() {
      var id = commonDetailService.getDataId();
      var URI = CONSTANTS.INVENTORY.STORE.BATCH_CHANGE_APPROVAL.DETAILS_API + id;
      var data = {};
      GenericService.serviceAction("GET", URI, data).then(
        function (response) {
          self.model.batchChangeApprovalDetail = response.data;
          self.model.batchChangeApprovalDetail.batch.forEach(function (batch) {
            batch.isDisabled = true;

          });
        });
    }

    var isSelected = function (batch) {
      if (!isDisable(batch))
        return batch.isSelected;
      else
        return true;
    }

    var isDisable = function (batch) { 
      if (batch.statusId != 10 ) {
        return true;
      }
      return false;
    }

    var SelectBatch = function (batch) {

      var batchIds = self.model.SelectedRequestIds;

      if (batch.isSelected) {
        batchIds.push(batch.id);
        batch.isDisabled = false;
      } else {
        batch.isDisabled = true;
        var index = batchIds.indexOf(batch.id);
        if (index > -1) {
          batchIds.splice(index, 1);
        }
      }

      if (self.model.batchChangeApprovalDetail.batch.every(isSelected)) {
        self.model.selectAllRows = true;
      } else {
        self.model.selectAllRows = false;
      }
    }
    var SelectAllRequests = function () {

      if (self.model.selectAllRows) {
        self.model.SelectedRequestIds = [];
        self.model.batchChangeApprovalDetail.batch.forEach(function (batch) {
          if (!isDisable(batch)) {
            batch.isSelected = true;
            batch.isDisabled = false;
            self.model.SelectedRequestIds.push(batch.id);
          }
        });
      } else {
        self.model.SelectedRequestIds = [];
        self.model.batchChangeApprovalDetail.batch.forEach(function (batch) {
          batch.isSelected = false;
          batch.isDisabled = true;
        });
      }
    }


    function setStatus(x) {

      var status = self.model.statuses.find(function (obj) {
        return obj.status == x;
      });

      return status.id;
    }
    var SaveApproval = function () {
 
      var id = commonDetailService.getDataId();
      var URI = CONSTANTS.INVENTORY.STORE.BATCH_CHANGE_APPROVAL.BATCH_APPROVE_API + id;
      var data = {
        "statusId": setStatus('Partially Approved'),
        "approvalStatusId": setStatus('Reject'),
        "batch": [],
      };

      var is_allapproved = true;
      angular.forEach(self.model.batchChangeApprovalDetail.batch, function (item, key) {
        var storeItem = {
          "id": item.id,
          "rejectReasonId": self.model.cancelReason ? self.model.cancelReason : undefined,
          "rejectNote": self.model.cancelNote ? self.model.cancelNote : undefined,
          "statusId": (item.isReject ? setStatus('Reject') : setStatus('Approved'))
        }

        if (data.approvalStatusId == setStatus('Reject') && !item.isReject) {
          data.approvalStatusId = setStatus('Partially Approved');
        }
        else if (is_allapproved && item.isReject) {
          is_allapproved = false;
        }

        data.batch.push(storeItem);
      });

      if (is_allapproved){
         data.approvalStatusId = setStatus('Approved');
         data.statusId = setStatus('Closed');
      }
      else if(data.approvalStatusId == setStatus('Reject'))
      {
        data.statusId = setStatus('Closed');
      }
      GenericService.serviceAction("PATCH", URI, data).then(
        function (response) {

          $rootScope.stopLoader();
          growl.success(response.data.message);
          NavigateToBack();
        }, function (err) {
          $rootScope.stopLoader();
          growl.error('Somthing Went Wrong');
        });

    }

    var NavigateToBack = function () {
      $state.go('batchExpDateChangeApproval');
    }
    var RejectionPopup = function (batch) {
      // console.log(self.model.status);
      if (batch.isReject) {
        batchId = batch.id;
        // self.model.rejectQty = item.rejectQuantity;
        self.model.action = 'Open';
        var popup = angular.element('#confirmCancel');
        popup.modal('show');
        self.model.reasons = self.model.rejectReasons;
        // self.model.rejectReason = self.model.reasons[0];
      }
    }
    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("detailBatchChangeApproval", {
      url: "/detailBatchChangeApproval",
      templateUrl: 'views/PharmacyInventory/Store/details-batch-change-approval.html',
      controller: "BatchChangeApprovalDetails.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("BatchChangeApprovalDetails.Controller", batchChangeApprovalDetailsController);
})();