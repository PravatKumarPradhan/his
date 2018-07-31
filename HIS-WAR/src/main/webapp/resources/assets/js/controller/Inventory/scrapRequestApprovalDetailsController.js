(function () {
  "use strict";

  function scrapRequestApprovalDetailsController($scope, $rootScope, $http, $state, rejectReasonService, StatusService, commonDetailService, CONSTANTS, GenericService, growl) {
    var self = this;
    var itemId;
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

      self.model.fromScrapRequest = fromScrapRequest;
      self.model.SelectAllItems = SelectAllItems;
      self.model.SelectItem = SelectItem;
      self.model.isDisable = isDisable;
      self.model.isSelected = isSelected;
      self.model.ApprovedQuantity = ApprovedQuantity;
      self.model.SaveAcceptReject = SaveAcceptReject;
      self.model.valueOnChange = valueOnChange;
      self.model.ModalDismiss = ModalDismiss;
      self.model.RejectionPopup = RejectionPopup;
      self.model.CancelRejection = CancelRejection;
      self.model.NavigateToBack = NavigateToBack;
      self.model.setStatus = setStatus;
      self.model.SaveApproval = SaveApproval;
      self.model.ValidateApproval = ValidateApproval;
      self.model.ValidateRejection = ValidateRejection;

      fromScrapRequest();
    }

    function setStatus(x) {

      var status = self.model.statuses.find(function (obj) {
        return obj.status == x;
      });

      return status.id;
    }
    function fromScrapRequest() {
      var id = commonDetailService.getDataId();
      var URI = CONSTANTS.INVENTORY.SCRAP.SCRAP_REQUEST_APPROVAL.DETAIL_API + id;
      var data = {};
      GenericService.serviceAction("GET", URI, data).then(
        function (response) {
          self.model.scrapDetailData = response.data;
          self.model.scrapDetailData.items.forEach(function (item) {
            item.isDisabled = true;

          });
        });
    }

    var isDisable = function (item) {
      if (item.statusId != 10 || item.statusId == 7 || item.statusId == 9 || item.statusId == 5) {
        return true;
      }
      return false;
    }

    var SelectAllItems = function () {
      if (self.model.selectAllRows) {
        self.model.SelectedRequestIds = [];
        self.model.scrapDetailData.items.forEach(function (item) {
          if (!!isDisable(item)) {
            item.isSelected = true;
            self.model.SelectedRequestIds.push(item.id);
            item.isDisabled = false;
            self.model.SelectedItems.push(item);
          }
        });
      } else {
        self.model.SelectedRequestIds = [];
        self.model.SelectedItems = [];
        self.model.scrapDetailData.items.forEach(function (item) {
          item.isSelected = false;
          item.isDisabled = true;
        });
      }
    }

    var SelectItem = function (item) {
      var items = self.model.SelectedItems;

      if (item.isSelected) {
        items.push(item);
        item.isDisabled = false;
      } else {
        item.isDisabled = true;
        item.isReject = false;
        // var index = item.indexOf(item);
        // if (index > -1) {
        //   items.splice(index, 1);
        // }
      }

      if (self.model.scrapDetailData.items.every(isSelected)) {
        self.model.selectAllRows = true;
      } else {
        self.model.selectAllRows = false;
      }
    }


    var isSelected = function (item) {
      return item.isSelected;
    }
    var ApprovedQuantity = function (item) {
      if (item.acceptedQuantity > item.scrapQuantity) {
        item.rejectedQuantity = 0;
        self.model.rejectQty = undefined;
        item.isReject = false;
        item.status = setStatus('Approved');; // Approved.
      } else if (item.acceptedQuantity == 0 || !item.acceptedQuantity) {
        item.rejectedQuantity = item.quantity;
        self.model.rejectQty = item.rejectedQuantity;
        item.isReject = true;
        item.status = setStatus('Reject');; // Rejected
      } else if (item.acceptedQuantity < item.scrapQuantity) {
        item.rejectedQuantity = item.scrapQuantity - item.acceptedQuantity;
        self.model.rejectQty = item.rejectedQuantity;
        item.isReject = true;
        item.status = setStatus('Partially Approved');; // Partially Approved
      } else if (item.acceptedQuantity == item.scrapQuantity) {
        item.rejectedQuantity = item.scrapQuantity - item.acceptedQuantity;
        self.model.rejectQty = item.rejectedQuantity;
        item.isReject = false;
        item.status = setStatus('Approved');; // Approved.
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
    var SaveAcceptReject = function () {
      if (ValidateRejection()) {
        var rejectItemId = itemId;
        self.model.SelectedItems.forEach(function (item) {
          if (item.id == rejectItemId) {
            if (self.model.rejectQty > item.scrapQuantity) {
              growl.error('Please Enter Valid Quantity');
              return false;
            } else if (self.model.rejectQty == item.scrapQuantity) {
              item.rejectedQuantity = item.scrapQuantity;
              item.acceptedQuantity = item.scrapQuantity - item.rejectedQuantity;
              item.rejectReasonId = self.model.rejectReasons[0].id;
              item.statusId = setStatus('Reject'); // Rejected 
            } else if (self.model.rejectQty == 0 || !self.model.rejectQty) {
              item.acceptedQuantity = item.scrapQuantity;
              item.rejectedQuantity = 0;
              item.rejectReasonId = self.model.rejectReasons[0].id;
              item.statusId = setStatus('Pending'); // Approved
            } else {
              item.rejectedQuantity = self.model.rejectQty;
              item.rejectNote = self.model.rejectNote;
              item.acceptedQuantity = item.scrapQuantity - item.rejectedQuantity;
              item.rejectReasonId = self.model.rejectReasons[0].id;
              item.statusId = setStatus('Partially Approved'); // Partially Approved
            }
            ModalDismiss('qtyaccepatReject');
          }
        });
      }
    }
    function valueOnChange(item, isSelectAll) {
      if (isSelectAll)
        self.model.scrapDetailData.items.forEach(function (item) {
          if (self.model.selectAllRows)
            item.acceptedQuantity = item.scrapQuantity;
          else
            item.acceptedQuantity = 0;
        });
      else
        if (item.isSelected)
          item.acceptedQuantity = item.scrapQuantity;
        else
          item.acceptedQuantity = 0;
    }


    var ModalDismiss = function (popupName) {
      var popup = angular.element('#' + popupName);
      popup.modal('hide');
    }

    var RejectionPopup = function (item) {
      // console.log(self.model.status);
      if (item.isReject) {
        itemId = item.id;
        // self.model.rejectQty = item.rejectQuantity;
        self.model.action = 'Open';
        var popup = angular.element('#qtyaccepatReject');
        popup.modal('show');
        self.model.reasons = self.model.rejectReasons;
        // self.model.rejectReason = self.model.reasons[0];
      } else {
        CancelRejection(item);
      }
    }

    var CancelRejection = function (item) {
      var prevRequest = self.model.requestItems.items.find(function (prevItem) {
        return prevItem.id == item.id;
      });

      if (prevRequest) {
        item.rejectedQuantity = prevRequest.rejectedQuantity;
        item.acceptedQuantity = prevRequest.acceptedQuantity;
        item.rejectNote = prevRequest.rejectNote;
        item.rejectReasonId = prevRequest.rejectReasonId;
        item.statusId = prevRequest.statusId;
      }
    }

    var NavigateToBack = function () {
      $state.go('approvalScrapRequest');
    }

    var SaveApproval = function () {

      if (ValidateApproval()) {
        var URI = CONSTANTS.INVENTORY.SCRAP.SCRAP_REQUEST_APPROVAL.APPROVE_API + commonDetailService.getDataId();
        var data = {
          "statusId": setStatus('Completed'),
          "approvalStatusId": setStatus('Reject'),
          "item": []
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

          if (item.acceptedQuantity > 0 && item.acceptedQuantity < item.scrapQuantity) {
            newItem.statusId = setStatus('Partially Approved');

            if (data.approvalStatusId == setStatus('Reject')) {
              data.approvalStatusId = setStatus('Partially Approved');
            }
          }
          else if (item.acceptedQuantity >= item.scrapQuantity) {
            data.approvalStatusId = setStatus('Approved');

            if (data.approvalStatusId == setStatus('Reject')) {
              data.approvalStatusId = setStatus('Approved');
            }
          }
          else if (item.rejectedQuantity >= item.scrapQuantity) {
            newItem.statusId = setStatus('Reject');
          }
          else if (item.rejectedQuantity > 0 && item.rejectedQuantity < item.scrapQuantity) {
            newItem.statusId = setStatus('Partially Approved');

            if (data.approvalStatusId == setStatus('Reject')) {
              data.approvalStatusId = setStatus('Approved');
            }
          }

          data.item.push(newItem);
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

    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("detailsApprovalScrapRequest", {
      url: "/detailsApprovalScrapRequest",
      templateUrl: 'views/PharmacyInventory/Store/details-approved-scrap-request.html',
      controller: "ScrapRequestApprovalDetails.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("ScrapRequestApprovalDetails.Controller", scrapRequestApprovalDetailsController);
})();
