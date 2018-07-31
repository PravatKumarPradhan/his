(function () {
  "use strict";

  function returnMaterialAcceptanceDetailsController($scope, $rootScope, $http, $state, CONSTANTS, rejectReasonService, commonDetailService, StatusService, GenericService, $filter, growl) {
    var self = this;
    var selectItemList = [];
    var masterId;
    var mreturnId;
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
      self.model.fromMaterialReturnAcceptance = fromMaterialReturnAcceptance;
      self.model.SelectAllItems = SelectAllItems;
      self.model.SelectItem = SelectItem;
      self.model.setStatus = setStatus;
      self.model.isSelected = isSelected
      self.model.isDisable = isDisable;
      self.model.setStatus = setStatus;
      self.model.valueOnChange = valueOnChange;
      self.model.ApprovedQuantity = ApprovedQuantity;
      self.model.ValidateRejection = ValidateRejection;
      self.model.RejectionPopup = RejectionPopup;
      self.model.CancelRejection = CancelRejection;
      self.model.ModalDismiss = ModalDismiss;
      self.model.SaveAcceptReject = SaveAcceptReject;
      self.model.SaveApproval = SaveApproval;
      self.model.ValidateApproval = self.model.ValidateApproval;
      self.model.NavigateToBack =NavigateToBack;


      fromMaterialReturnAcceptance();
    }

    function setStatus(x) {

      var status = self.model.statuses.find(function (obj) {
        return obj.status == x;
      });

      return status.id;
    }
    function fromMaterialReturnAcceptance() {
      var id = commonDetailService.getDataId();
      var URI = CONSTANTS.INVENTORY.STORE.MATERIAL_RETURN_ACCEPTANCE.DETAIL_API + id;
      // var URI = constants.apiurl + constants.inventoryApi + '/store/material/return/acceptance/' + id;
      var data = {};
      GenericService.serviceAction("GET", URI, data).then(
        function (response) {
          self.model.materialReturnDetail = response.data;
          self.model.materialReturnDetail.items.forEach(function (item) {
            item.isDisabled = true;
          });
        });
    }

    var SelectAllItems = function () {
      if (self.model.selectAllRows) {
        self.model.SelectedRequestIds = [];
        self.model.materialReturnDetail.items.forEach(function (mreturn) {
          if (!!isDisable(mreturn)) {
            mreturn.isSelected = true;
            self.model.SelectedRequestIds.push(mreturn.id);
            mreturn.isDisabled = false;
            self.model.SelectedItems.push(mreturn);
          }
        });
      } else {
        self.model.SelectedRequestIds = [];
        self.model.SelectedItems = [];
        self.model.materialReturnDetail.items.forEach(function (mreturn) {
          mreturn.isSelected = false;
          mreturn.isDisabled = true;
        });
      }
    }

    var SelectItem = function (mreturn) {
      var mreturns = self.model.SelectedItems;

      if (mreturn.isSelected) {
        mreturns.push(mreturn);
        mreturn.isDisabled = false;
      } else {
        mreturn.isDisabled = true;
        mreturn.isReject = false;
        var index = mreturn.indexOf(mreturn);
        if (index > -1) {
          mreturns.splice(index, 1);
        }
      }

      if (self.model.materialReturnDetail.items.every(isSelected)) {
        self.model.selectAllRows = true;
      } else {
        self.model.selectAllRows = false;
      }
    }

    var isDisable = function (mreturn) {
      if (mreturn.statusId != 10 || mreturn.statusId == 7 || mreturn.statusId == 9 || mreturn.statusId == 5) {
        return true;
      }
      return false;
    }
    var isSelected = function (mreturn) {
      return mreturn.isSelected;
    }


    function valueOnChange(mreturn, isSelectAll) {
      if (isSelectAll)
        self.model.materialReturnDetail.items.forEach(function (mreturn) {
          if (self.model.selectAllRows)
            mreturn.acceptedQuantity = mreturn.returnQuantity;
          else
            mreturn.acceptedQuantity = 0;
        });
      else
        if (mreturn.isSelected)
          mreturn.acceptedQuantity = mreturn.returnQuantity;
        else
          mreturn.acceptedQuantity = 0;
    }


    var ApprovedQuantity = function (mreturn) {
      if (mreturn.acceptedQuantity > mreturn.returnQuantity) {
        mreturn.rejectedQuantity = 0;
        self.model.rejectQty = undefined;
        mreturn.isReject = false;
        mreturn.status = setStatus('Approved');; // Approved.
      } else if (mreturn.acceptedQuantity == 0 || !mreturn.acceptedQuantity) {
        mreturn.rejectedQuantity = item.quantity;
        self.model.rejectQty = item.rejectedQuantity;
        mreturn.isReject = true;
        mreturn.status = setStatus('Reject');; // Rejected
      } else if (mreturn.acceptedQuantity < mreturn.returnQuantity) {
        mreturn.rejectedQuantity = mreturn.returnQuantity - mreturn.acceptedQuantity;
        self.model.rejectQty = mreturn.rejectedQuantity;
        mreturn.isReject = true;
        mreturn.status = setStatus('Partially Approved');; // Partially Approved
      } else if (mreturn.acceptedQuantity == mreturn.returnQuantity) {
        mreturn.rejectedQuantity = mreturn.returnQuantity - mreturn.acceptedQuantity;
        self.model.rejectQty = mreturn.rejectedQuantity;
        mreturn.isReject = false;
        mreturn.status = setStatus('Approved');; // Approved.
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

    var RejectionPopup = function (mreturn) {
      if (mreturn.isReject) {
        mreturnId = mreturn.id;
        self.model.action = 'Open';
        var popup = angular.element('#qtyaccepatReject');
        popup.modal('show');
        self.model.reasons = self.model.rejectReasons;
      } else {
        CancelRejection(mreturn);
      }
    }

    var CancelRejection = function (mreturn) {
      var prevRequest = self.model.requestItems.mreturn.find(function (prevItem) {
        return prevItem.id == mreturn.id;
      });

      if (prevRequest) {
        mreturn.rejectedQuantity = prevRequest.rejectedQuantity;
        mreturn.acceptedQuantity = prevRequest.acceptedQuantity;
        mreturn.rejectNote = prevRequest.rejectNote;
        mreturn.rejectReasonId = prevRequest.rejectReasonId;
        mreturn.statusId = prevRequest.statusId;
      }
    }


    var ModalDismiss = function (popupName) {
      var popup = angular.element('#' + popupName);
      popup.modal('hide');
    }



    var SaveAcceptReject = function () {
      if (ValidateRejection()) {
        var rejectItemId = mreturnId;
        self.model.SelectedItems.forEach(function (mreturn) {
          if (mreturn.id == rejectItemId) {
            if (self.model.rejectQty > mreturn.returnQuantity) {
              growl.error('Please Enter Valid Quantity');
              return false;
            } else if (self.model.rejectQty == mreturn.returnQuantity) {
              mreturn.rejectedQuantity = mreturn.returnQuantity;
              mreturn.acceptedQuantity = mreturn.returnQuantity - mreturn.rejectedQuantity;
              mreturn.rejectReasonId = self.model.rejectReasons[0].id;
              mreturn.statusId = setStatus('Reject'); // Rejected 
            } else if (self.model.rejectQty == 0 || !self.model.rejectQty) {
              mreturn.acceptedQuantity = mreturn.returnQuantity;
              mreturn.rejectedQuantity = 0;
              mreturn.rejectReasonId = self.model.rejectReasons[0].id;
              mreturn.statusId = setStatus('Pending'); // Approved
            } else {
              mreturn.rejectedQuantity = self.model.rejectQty;
              mreturn.rejectNote = self.model.rejectNote;
              mreturn.acceptedQuantity = mreturn.returnQuantity - mreturn.rejectedQuantity;
              mreturn.rejectReasonId = self.model.rejectReasons[0].id;
              mreturn.statusId = setStatus('Partially Approved'); // Partially Approved
            }
            ModalDismiss('qtyaccepatReject');
          }
        });
      }
    }


    var SaveApproval = function () {

      if (ValidateApproval()) {
        var URI = CONSTANTS.INVENTORY.STORE.MATERIAL_RETURN_ACCEPTANCE.APPROVE_API + commonDetailService.getDataId();
        var data = {
          "statusId": setStatus('Closed'),
          "approvalStatusId": setStatus('Reject'),
          "items": []
        }

        $rootScope.startLoader();
        angular.forEach(self.model.SelectedItems, function (item) {
          var newItem = {
            "id": item.id,
            "acceptedQuantity": parseInt(item.acceptedQuantity) ? item.acceptedQuantity : 0,
            "rejectedQty": parseInt(item.rejectedQuantity),
            "rejectReasonId": self.model.rejectReasonId ? self.model.rejectReasonId : 1,
            "rejectNote": self.model.rejectNote ? self.model.rejectNote : "default note",
            "statusId": item.status ? item.status : 6
          }

          if (item.acceptedQuantity > 0 && item.acceptedQuantity < item.returnQuantity) {
            newItem.statusId = setStatus('Partially Approved');

            if (data.approvalStatusId == setStatus('Reject')) {
              data.approvalStatusId = setStatus('Partially Approved');
            }
          }
          else if (item.acceptedQuantity >= item.returnQuantity) {
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
      $state.go('materialReturnAcceptance');
    }


    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("detailsMaterialReturnAcceptance", {
      url: '/detailsMaterialReturnAcceptance',
      templateUrl: 'views/PharmacyInventory/InPatient/details-material-return-acceptance.html',
      controller: "ReturnMaterialAcceptanceDetails.Controller",
      controllerAs: 'vm'
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("ReturnMaterialAcceptanceDetails.Controller", returnMaterialAcceptanceDetailsController)
})();
