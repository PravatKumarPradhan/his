(function () {
  "use strict";

  function returnableGatepassAppprovalDetailsController($scope,$state, $rootScope, StatusService, rejectReasonService, commonDetailService, $http, CONSTANTS, GenericService, growl) {
    var self = this;
    var returnableId;

    //Default Constructor
    function initializeController() {
      $rootScope.loginpage = true;
      self.model = {
        SelectedItems: [],
        selectItemList: [],
        selectAllRows: []

      };
      StatusService.GetStatus().then(function (status) {
        self.model.statuses = status;
      });
      rejectReasonService.GetReason().then(function (reason) {
        self.model.rejectReasons = reason;
      });
      self.model.fromReturnableGateepassApproval = fromReturnableGateepassApproval;
      self.model.setStatus = setStatus;
      self.model.SelectAllItems = SelectAllItems;
      self.model.SelectItem = SelectItem;
      self.model.isSelected = isSelected;
      self.model.isDisable = isDisable;
      self.model.valueOnChange = valueOnChange;
      self.model.ApprovedQuantity = ApprovedQuantity;
      self.model.RejectionPopup = RejectionPopup;
      self.model.CancelRejection = CancelRejection;
      self.model.ValidateRejection = ValidateRejection;
      self.model.SaveAcceptReject = SaveAcceptReject;
      self.model.ModalDismiss = ModalDismiss;
      self.model.SaveApproval = SaveApproval;
      self.model.ValidateApproval = ValidateApproval;


      fromReturnableGateepassApproval();


    }
    function fromReturnableGateepassApproval() {
      var id = commonDetailService.getDataId();
      var URI = CONSTANTS.INVENTORY.STORE.RETURNABLE_GATEPASS_APPROVAL.DETAIL_API + id;
      var data = {};
      GenericService.serviceAction("GET", URI, data).then(
        function (response) {
          self.model.returnableGatepassAppprovalDetailData = response.data;
          self.model.returnableGatepassAppprovalDetailData.items.forEach(function (item) {
            item.isDisabled = true;
          });
      });
}
 
    function setStatus(x) {

      var status = self.model.statuses.find(function (obj) {
        return obj.status == x;
      });

      return status.id;
    }

    var isDisable = function (returnable) {
      if (returnable.statusId != 10 || returnable.statusId == 7 || returnable.statusId == 9 || returnable.statusId == 5) {
        return true;
      }
      return false;
    }

    var SelectAllItems = function () {
      if (self.model.selectAllRows) {
        self.model.SelectedRequestIds = [];
        self.model.returnableGatepassAppprovalDetailData.items.forEach(function (returnable) {
          if (!!isDisable(returnable)) {
            returnable.isSelected = true;
            self.model.SelectedRequestIds.push(returnable.id);
            returnable.isDisabled = false;
            self.model.SelectedItems.push(returnable);
          }
        });
      } else {
        self.model.SelectedRequestIds = [];
        self.model.SelectedItems = [];
        self.model.returnableGatepassAppprovalDetailData.items.forEach(function (returnable) {
          returnable.isSelected = false;
          returnable.isDisabled = true;
        });
      }
    }

    var SelectItem = function (returnable) {
      var returnables = self.model.SelectedItems;

      if (returnable.isSelected) {
        returnables.push(returnable);
        returnable.isDisabled = false;
      } else {
        returnable.isDisabled = true;
        returnable.isReject = false;
        var index = returnable.indexOf(returnable);
        if (index > -1) {
          returnables.splice(index, 1);
        }
      }

      if (self.model.returnableGatepassAppprovalDetailData.items.every(isSelected)) {
        self.model.selectAllRows = true;
      } else {
        self.model.selectAllRows = false;
      }
    }


    var isSelected = function (returnable) {
      return returnable.isSelected;
    }

    function valueOnChange(returnable, isSelectAll) {
      if (isSelectAll)
        self.model.returnableGatepassAppprovalDetailData.items.forEach(function (returnable) {
          if (self.model.selectAllRows)
            returnable.approvedQuantity = returnable.returnQuantity;
          else
            returnable.approvedQuantity = 0;
        });
      else
        if (returnable.isSelected)
          returnable.approvedQuantity = returnable.returnQuantity;
        else
          returnable.approvedQuantity = 0;
    }

    var ApprovedQuantity = function (returnable) {
      if (returnable.approvedQuantity > returnable.returnQuantity) {
        returnable.rejectedQuantity = 0;
        self.model.rejectQty = undefined;
        returnable.isReject = false;
        returnable.status = setStatus('Approved');; // Approved.
      } else if (returnable.approvedQuantity == 0 || !returnable.approvedQuantity) {
        returnable.rejectedQuantity = item.quantity;
        self.model.rejectQty = item.rejectedQuantity;
        returnable.isReject = true;
        returnable.status = setStatus('Reject');; // Rejected
      } else if (returnable.approvedQuantity < returnable.returnQuantity) {
        returnable.rejectedQuantity = returnable.returnQuantity - returnable.approvedQuantity;
        self.model.rejectQty = returnable.rejectedQuantity;
        returnable.isReject = true;
        returnable.status = setStatus('Partially Approved');; // Partially Approved
      } else if (returnable.approvedQuantity == returnable.returnQuantity) {
        returnable.rejectedQuantity = returnable.returnQuantity - returnable.approvedQuantity;
        self.model.rejectQty = returnable.rejectedQuantity;
        returnable.isReject = false;
        returnable.status = setStatus('Approved');; // Approved.
      }
    }

    var ValidateRejection = function () {
      var isValid = true;
      if (!self.model.rejectReason) {
        isValid = false;
        growl.error('Please Select Reason to Reject');
      }

      return isValid;
    }

    var RejectionPopup = function (returnable) {
      // console.log(self.model.status);
      if (returnable.isReject) {
        returnableId = returnable.id;
        // self.model.rejectQty = item.rejectQuantity;
        self.model.action = 'Open';
        var popup = angular.element('#qtyaccepatReject');
        popup.modal('show');
        self.model.reasons = self.model.rejectReasons;
        // self.model.rejectReason = self.model.reasons[0];
      } else {
        CancelRejection(returnable);
      }
    }

    var CancelRejection = function (returnable) {
      var prevRequest = self.model.requestItems.returnable.find(function (prevItem) {
        return prevItem.id == returnable.id;
      });

      if (prevRequest) {
        returnable.rejectedQuantity = prevRequest.rejectedQuantity;
        returnable.approvedQuantity = prevRequest.approvedQuantity;
        returnable.rejectNote = prevRequest.rejectNote;
        returnable.rejectReasonId = prevRequest.rejectReasonId;
        returnable.statusId = prevRequest.statusId;
      }
    }


    var ModalDismiss = function (popupName) {
      var popup = angular.element('#' + popupName);
      popup.modal('hide');
    }



    var SaveAcceptReject = function () {
      if (ValidateRejection()) {
        var rejectItemId = returnableId;
        self.model.SelectedItems.forEach(function (returnable) {
          if (returnable.id == rejectItemId) {
            if (self.model.rejectQty > returnable.returnQuantity) {
              growl.error('Please Enter Valid Quantity');
              return false;
            } else if (self.model.rejectQty == returnable.returnQuantity) {
              returnable.rejectedQuantity = returnable.returnQuantity;
              returnable.approvedQuantity = returnable.returnQuantity - returnable.rejectedQuantity;
              returnable.rejectReasonId = self.model.rejectReasons[0].id;
              returnable.statusId = setStatus('Reject'); // Rejected 
            } else if (self.model.rejectQty == 0 || !self.model.rejectQty) {
              returnable.approvedQuantity = returnable.returnQuantity;
              returnable.rejectedQuantity = 0;
              returnable.rejectReasonId = self.model.rejectReasons[0].id;
              returnable.statusId = setStatus('Pending'); // Approved
            } else {
              returnable.rejectedQuantity = self.model.rejectQty;
              returnable.rejectNote = self.model.rejectNote;
              returnable.approvedQuantity = returnable.returnQuantity - returnable.rejectedQuantity;
              returnable.rejectReasonId = self.model.rejectReasons[0].id;
              returnable.statusId = setStatus('Partially Approved'); // Partially Approved
            }
            ModalDismiss('qtyaccepatReject');
          }
        });
      }
    }

    var SaveApproval = function () {

      if (ValidateApproval()) {
        var URI = CONSTANTS.INVENTORY.STORE.RETURNABLE_GATEPASS_APPROVAL.APPROVE_API + commonDetailService.getDataId();
        var data = {
          "statusId": setStatus('Closed'),
          "approvalStatusId": setStatus('Reject'),
          "items": []
        }

        $rootScope.startLoader();
        angular.forEach(self.model.SelectedItems, function (item) {
          var newItem = {
            "id": item.id,
            "approvedQuantity": parseInt(item.approvedQuantity) ? item.approvedQuantity : 0,
            "rejectedQuantity": parseInt(item.rejectedQuantity),
            "rejectReasonId": self.model.rejectReasonId ? self.model.rejectReasonId : 1,
            "rejectNote": self.model.rejectNote ? self.model.rejectNote : "default note",
            "statusId": item.status ? item.status : 6
          }

          if (item.approvedQuantity > 0 && item.approvedQuantity < item.returnQuantity) {
            newItem.statusId = setStatus('Partially Approved');

            if (data.approvalStatusId == setStatus('Reject')) {
              data.approvalStatusId = setStatus('Partially Approved');
            }
          }
          else if (item.approvedQuantity >= item.returnQuantity) {
            data.approvalStatusId = setStatus('Approved');

            if (data.approvalStatusId == setStatus('Reject')) {
              data.approvalStatusId = setStatus('Approved');
            }
          }
          else if (item.rejectedQuantity >= item.returnQuantity) {
            newItem.statusId = setStatus('Reject');
          }
          else if (item.rejectedQuantity > 0 && item.rejectedQuantity < item.returnQuantity) {
            newItem.statusId = setStatus('Partially Approved');

            if (data.approvalStatusId == setStatus('Reject')) {
              data.approvalStatusId = setStatus('Approved');
            }
          }

          data.items.push(newItem);
        });

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
    }

    var ValidateApproval = function () {
      var isValid = true;

      if (!self.model.SelectedItems || self.model.SelectedItems.length == 0) {
        growl.error('Select Items to Update and Save');
        isValid = false;
      }

      return isValid;
    }

    var NavigateToBack = function () {
      $state.go('returnaleGatepassApproval');
    }

    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("detailsreturnaleGatepassApproval", {
      url: "/detailsreturnaleGatepassApproval",
      templateUrl: 'views/PharmacyInventory/Store/details-returnable-gatepass-approval.html',
      controller: "ReturnableGatepassAppprovalDetails.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("ReturnableGatepassAppprovalDetails.Controller", returnableGatepassAppprovalDetailsController);
})();
