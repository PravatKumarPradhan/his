(function () {
  "use strict";

  function materialIssueAcceptanceDetailController($scope, $rootScope, $http, $state, CONSTANTS, commonDetailService, rejectReasonService, StatusService, GenericService, $filter, growl) {
    var self = this;
    var selectItemList = [];
    var masterId;

    var itemId;
    //Default Constructor
    function initializeController() {
      $rootScope.loginpage = true;
      self.model = {
        SelectedItems: [],
        requestData: [],


      };
      self.model.fromMaterialIssueAcceptance = fromMaterialIssueAcceptance;
      self.model.SelectAllItems = SelectAllItems;
      self.model.SelectItem = SelectItem;
      self.model.ApprovedQuantity = ApprovedQuantity;
      self.model.SaveApproval = SaveApproval;
      self.model.setStatus = setStatus;
      self.model.RejectionPopup = RejectionPopup;
      self.model.SaveAcceptReject = SaveAcceptReject;
      self.model.valueOnChange = valueOnChange;
      self.model.ModalDismiss =ModalDismiss;

      StatusService.GetStatus().then(function (status) {
        self.model.statuses = status;
      });
      rejectReasonService.GetReason().then(function (reason) {
        self.model.rejectReasons = reason;
      });
      fromMaterialIssueAcceptance();
    }


    function fromMaterialIssueAcceptance() {
      var id = commonDetailService.getDataId();
      var URI = CONSTANTS.INVENTORY.STORE.MATERIAL_ISSUE_ACCEPTANCE.DETAILS_API + id;
      var data = {};
      GenericService.serviceAction("GET", URI, data).then(
        function (response) {
          self.model.materialIssueDetail = response.data;
          self.model.materialIssueDetail.items.forEach(function (item) {
            item.isDisabled = true;

          });
        });
    }

    var isSelected = function (item) {
      return item.isSelected;
    }

    function selectedAll() {
      self.model.materialIssueDetail.forEach(function (x) {
        if (self.model.selectAllRows) {
          x.isChecked = true;
        }
        else {
          x.isChecked = false;
        }
      });
    }

    var SelectAllItems = function () {
      if (self.model.selectAllRows) {
        self.model.SelectedRequestIds = [];
        self.model.materialIssueDetail.items.forEach(function (items) {
          if (!isDisable(items)) {
            items.isSelected = true;
            items.isDisabled = false;
            self.model.SelectedRequestIds.push(items.id);
            self.model.SelectedItems.push(items);
          }
        });
      } else {
        self.model.SelectedRequestIds = [];
        self.model.SelectedItems = [];
        self.model.materialIssueDetail.items.forEach(function (items) {
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

      if (self.model.materialIssueDetail.items.every(isSelected)) {
        self.model.selectAllRows = true;
      } else {
        self.model.selectAllRows = false;
      }
    }

    var RejectionPopup = function (item) {
      // console.log(self.model.status);
      if (item.isReject) {
        itemId = item.id;
        self.model.rejectQty = item.rejectQuantity;
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
      var prevRequest = self.model.materialIssueDetail.items.find(function (prevItem) {
        return prevItem.id == item.id;
      });

      if (prevRequest) {
        item.rejectQuantity = prevRequest.rejectQuantity;
        item.acceptedQuantity = prevRequest.acceptedQuantity;
        item.rejectNote = prevRequest.rejectNote;
        item.rejectReasonId = prevRequest.rejectReasonId;
        item.statusId = prevRequest.statusId;
      }
    }


    var ApprovedQuantity = function (item) {
      if (item.acceptedQuantity > item.issueQuantity) {
        item.rejectQuantity = 0;
        self.model.rejectQty = undefined;
        item.isReject = false;
      } else if (item.acceptedQuantity == 0 || !item.acceptedQuantity) {
        item.rejectQuantity = item.issueQuantity;
        self.model.rejectQty = item.rejectQuantity;
        item.isReject = true;
      } else if (item.acceptedQuantity < item.issueQuantity) {
        item.rejectQuantity = item.issueQuantity - item.acceptedQuantity;
        self.model.rejectQty = item.rejectQuantity;
        item.isReject = true;
      } else if (item.acceptedQuantity == item.issueQuantity) {
        item.rejectQuantity = item.issueQuantity - item.acceptedQuantity;
        self.model.rejectQty = item.rejectQuantity;
        item.isReject = false;
      }
    }


    var SaveApproval = function () {
      if (ValidateApproval()) {
        var URI = CONSTANTS.INVENTORY.STORE.MATERIAL_ISSUE_ACCEPTANCE.SAVE_API + commonDetailService.getDataId();
        var data = {
          "statusId": setStatus('Closed'),
          "items": []
        }

        $rootScope.startLoader();
        angular.forEach(self.model.SelectedItems, function (item) {
          var newItem = {
            "batchMapperId": parseInt(item.batchMapperId),
            "acceptedQuantity": parseInt(item.acceptedQuantity),
            "rejectQuantity": item.rejectQuantity ? item.rejectQuantity : 0,
            "rejectReasonId": self.model.rejectReasons.id ? self.model.rejectReasons.id : 1,
            "rejectNote": self.model.rejectNote ? self.model.rejectNote : "default note "
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


    var ValidateApproval = function () {
      var isValid = true;

      if (!self.model.SelectedItems || self.model.SelectedItems.length == 0) {
        growl.error('Select Items to Update and Save');
        isValid = false;
      }
      for (let issueDetail of self.model.materialIssueDetail.items) {
        if (!issueDetail.acceptedQuantity || issueDetail.acceptedQuantity > issueDetail.issueQuantity) {
          growl.error('Error', {
            title: "Please Enter Valid Accepted Quantity"
          });
          isValid = false;
        }
      }

        for (let issueDetail of self.model.materialIssueDetail.items) {
          if (issueDetail.rejectQuantity > issueDetail.issueQuantity) {
            growl.error('Error', {
              title: "Please Enter Valid Reject Quantity"
            });
            isValid = false;
          }
        }
        return isValid;
      }

      var NavigateToBack = function () {
        $state.go('materialIssueAcceptance');
      }

      var ModalDismiss = function (popupName) {
        var popup = angular.element('#' + popupName);
        popup.modal('hide');
      }

      var SaveAcceptReject = function () {
        if (ValidateRejection()) {
          var rejectItemId = itemId;
          self.model.SelectedItems.forEach(function (item) {
            if (item.id == rejectItemId) {
              if (self.model.rejectQty > item.pendingQuantity) {
                growl.error('Please Enter Valid Quantity');
                return false;
              } else if (self.model.rejectQty == item.pendingQuantity) {
                item.rejectQuantity = item.pendingQuantity;
                item.acceptedQuantity = item.pendingQuantity - item.rejectQuantity;
                item.rejectReasonId = self.model.rejectReasons[0].id;
              } else if (self.model.rejectQty == 0 || !self.model.rejectQty) {
                item.acceptedQuantity = item.pendingQuantity;
                item.rejectQuantity = 0;
                item.rejectReasonId = self.model.rejectReasons[0].id;
              } else {
                item.rejectQuantity = self.model.rejectQty;
                item.rejectNote = self.model.rejectNote;
                item.acceptedQuantity = item.pendingQuantity - item.rejectQuantity;
                item.rejectReasonId = self.model.rejectReasons[0].id;
              }
              ModalDismiss('qtyaccepatReject');
              self.model.rejectQty = undefined;
              self.model.rejectReason = undefined;
              self.model.rejectNote = undefined;
            }
          });
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


      function valueOnChange(item, isSelectAll) {
        if (isSelectAll)
          self.model.materialIssueDetail.items.forEach(function (item) {
            if (self.model.selectAllRows)
              item.acceptedQuantity = item.issueQuantity;
            else
              item.acceptedQuantity = 0;
          });
        else
          if (item.isSelected)
            item.acceptedQuantity = item.issueQuantity;
          else
            item.acceptedQuantity = 0;
      }




      initializeController();
    }



    function config($stateProvider) {
      $stateProvider.state("detailsmaterialIssueAcceptance", {
        url: '/detailsmaterialIssueAcceptance',
        templateUrl: 'views/PharmacyInventory/InPatient/details-material-issue-acceptance.html',
        controller: "MaterialIssueAcceptanceDetail.Controller",
        controllerAs: 'vm'
      });
    }

    angular
      .module("myApp")
      .config(config)
      .controller("MaterialIssueAcceptanceDetail.Controller", materialIssueAcceptanceDetailController)
  }) ();   