(function () {
  "use strict";
  var CACHE = {};
  function storeIndentApprovaDetailsController($scope, $rootScope, $location, CONSTANTS, rejectReasonService, $http, StatusService, GenericService, $state, growl, storeIndentApprovalService) {
    var self = this;
    var itemId;
    var selectItemList = [];

    //Default Constructor
    function initializeController() {
      $rootScope.loginpage = true;
      self.model = {
        SelectedItems: [],
        selectItemList: [],
        requestData: [],
        openingBalanceData: [],
        productCategories: []
      };
      rejectReasonService.GetReason().then(function (reason) {
        self.model.rejectReasons = reason;
      });

      self.model.selectAllRows = false;
      self.model.NavigateToBack = NavigateToBack;
      self.model.isConsignment = false;
      self.model.SelectAllItems = SelectAllItems;
      self.model.SelectItem = SelectItem;
      self.model.SaveAcceptReject = SaveAcceptReject;
      self.model.ApprovedQuantity = ApprovedQuantity;
      self.model.RejectionPopup = RejectionPopup;
      self.model.ModalDismiss = ModalDismiss;
      self.model.SaveApproval = SaveApproval;
      self.model.isDisable = isDisable;
      self.model.valueOnChange = valueOnChange;
      // self.model.isDisableSelect = isDisableSelect;

      GetStoreIndent();
    }

    var GetStoreIndent = function (storeIndentId) {
      storeIndentId = storeIndentId;

      var URI = CONSTANTS.INVENTORY.STORE.STORE_INDENT_APPROVAL.DETAILS_API + storeIndentApprovalService.getIndentId();

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          self.model.indentapproval = response.data;
          self.model.requestItems = JSON.parse(JSON.stringify(response.data));
          self.model.indentapproval.items.forEach(function (item) {
            item.isDisabled = true;

          });
          $rootScope.stopLoader();
        },
        function (err) {
          $rootScope.stopLoader();
          growl.error('No Data Found');
        });
    }

    function valueOnChange(item, isSelectAll) {
      if (isSelectAll)
        self.model.indentapproval.items.forEach(function (item) {
          if (self.model.selectAllRows)
            item.approvedQuantity = item.quantity;
          else
            item.approvedQuantity = 0;
        });
      else
        if (item.isSelected)
          item.approvedQuantity = item.quantity;
        else
          item.approvedQuantity = 0;
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
        item.rejectQuantity = prevRequest.rejectQuantity;
        item.approvedQuantity = prevRequest.approvedQuantity;
        item.rejectNote = prevRequest.rejectNote;
        item.rejectReasonId = prevRequest.rejectReasonId;
        item.statusId = prevRequest.statusId;
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


    var ApprovedQuantity = function (item) {
      if (item.approvedQuantity > item.quantity) {
        item.rejectQuantity = 0;
        self.model.rejectQty = undefined;
        item.isReject = false;
        item.status = setStatus('Approved');; // Approved.
      } else if (item.approvedQuantity == 0 || !item.approvedQuantity) {
        item.rejectQuantity = item.quantity;
        self.model.rejectQty = item.rejectQuantity;
        item.isReject = true;
        item.status = setStatus('Reject');; // Rejected
      } else if (item.approvedQuantity < item.quantity) {
        item.rejectQuantity = item.quantity - item.approvedQuantity;
        self.model.rejectQty = item.rejectQuantity;
        item.isReject = true;
        item.status = setStatus('Partially Approved');; // Partially Approved
      } else if (item.approvedQuantity == item.quantity) {
        item.rejectQuantity = item.quantity - item.approvedQuantity;
        self.model.rejectQty = item.rejectQuantity;
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
            if (self.model.rejectQty > item.quantity) {
              growl.error('Please Enter Valid Quantity');
              return false;
            } else if (self.model.rejectQty == item.quantity) {
              item.rejectQuantity = item.quantity;
              item.approvedQuantity = item.quantity - item.rejectQuantity;
              item.rejectReasonId = self.model.rejectReasons[0].id;
              item.statusId = setStatus('Reject'); // Rejected 
            } else if (self.model.rejectQty == 0 || !self.model.rejectQty) {
              item.approvedQuantity = item.quantity;
              item.rejectQuantity = 0;
              item.rejectReasonId = self.model.rejectReasons[0].id;
              item.statusId = setStatus('Pending'); // Approved
            } else {
              item.rejectQuantity = self.model.rejectQty;
              item.rejectNote = self.model.rejectNote;
              item.approvedQuantity = item.quantity - item.rejectQuantity;
              item.rejectReasonId = self.model.rejectReasons[0].id;
              item.statusId = setStatus('Partially Approved'); // Partially Approved
            }
            ModalDismiss('qtyaccepatReject');
            // self.model.rejectQty = undefined;
            // self.model.rejectReason = undefined;
            // self.model.rejectNote = undefined;
          }
        });
      }
    }
    //   var isDisableSelect= function (Item) {
    //   if (Item.status != 'Pending Approval'  && Item.status != 'Cancelled'  ) {
    //     return true;
    //   }
    //   return false;
    // }

    var SaveApproval = function () {

      if (ValidateApproval()) {
        var URI = CONSTANTS.INVENTORY.STORE.STORE_INDENT_APPROVAL.APPROVE_API + storeIndentApprovalService.getIndentId();
        var data = {
          "statusId": setStatus('Pending'),
          "approvalStatusId": setStatus('Reject'),
          "items": []
        }

        $rootScope.startLoader();
        angular.forEach(self.model.SelectedItems, function (item) {
          var newItem = {
            "id": item.id,
            "approvedQuantity": parseInt(item.approvedQuantity),
            "rejectQuantity": parseInt(item.rejectQuantity),
            "rejectReasonId": 1,
            "rejectNote": "Reject Note",
            "statusId": item.status ? item.status : 6
          }

          if (item.approvedQuantity > 0 && item.approvedQuantity < item.quantity) {
            newItem.statusId = setStatus('Partially Approved');

            if (data.approvalStatusId == setStatus('Reject')) {
              data.approvalStatusId = setStatus('Partially Approved');
            }
          }
          else if (item.approvedQuantity >= item.quantity) {
            data.approvalStatusId = setStatus('Approved');

            if (data.approvalStatusId == setStatus('Reject')) {
              data.approvalStatusId = setStatus('Approved');
            }
          }
          else if (item.rejectQuantity >= item.quantity) {
            newItem.statusId = setStatus('Reject');
          }
          else if (item.rejectQuantity > 0 && item.rejectQuantity < item.quantity) {
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

    var isSelected = function (item) {
      return item.isSelected;
    }

    StatusService.GetStatus().then(function (status) {
      self.model.statuses = status;
    });

    function setStatus(x) {

      var status = self.model.statuses.find(function (obj) {
        return obj.status == x;
      });

      return status.id;
    }
    var isDisable = function (items) {
      if (items.statusId == 10) {
        return true;
      }
      return false;
    }

    var SelectAllItems = function () {
      if (self.model.selectAllRows) {
        self.model.SelectedRequestIds = [];
        self.model.indentapproval.items.forEach(function (items) {
          if (!!isDisable(items)) {
            items.isSelected = true;
            self.model.SelectedRequestIds.push(items.id);
            items.isDisabled = false;
            self.model.SelectedItems.push(items);
          }
        });
      } else {
        self.model.SelectedRequestIds = [];
        self.model.SelectedItems = [];
        self.model.indentapproval.items.forEach(function (items) {
          items.isSelected = false;
          items.isDisabled = true;
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
        var index = items.indexOf(item);
        if (index > -1) {
          items.splice(index, 1);
        }
      }

      if (self.model.indentapproval.items.every(isSelected)) {
        self.model.selectAllRows = true;
      } else {
        self.model.selectAllRows = false;
      }
    }

    var NavigateToBack = function () {
      $state.go('storeIndentApproval');
    }
    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("detailsStoreIndentApproval", {
      url: "/detailsStoreIndentApproval",
      templateUrl: 'views/PharmacyInventory/InPatient/details-store-indent-approval.html',
      controller: "StoreIndentApprovaDetails.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("StoreIndentApprovaDetails.Controller", storeIndentApprovaDetailsController);
})();