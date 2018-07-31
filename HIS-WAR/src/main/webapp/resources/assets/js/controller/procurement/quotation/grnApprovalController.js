(function () {
  "use strict";

  function GRNApprovalController($scope, $rootScope, $http, $filter, $state, CONSTANTS, GenericService, commonDetailService, cancelReasonService, StatusService, growl) {
    var self = this;
    var rejectData;

    //Default Constructor
    function initializeController() {
      $rootScope.loginpage = true;

      self.model = {
        fromDate: new Date(),
        toDate: new Date(),
        fromDateOptions: {
          formatYear: 'yyyy',
          showWeeks: false,
          maxDate: new Date()
        },
        toDateOptions: {
          formatYear: 'yyyy',
          showWeeks: false,
          maxDate: new Date()
        },
        selectAllRows: false,
        SelectedOrderIds: [],
        selectedItems: [],
        storeList: [{
          "store": "Select Store"
        }],
        statuses: [{
          "status": "All"
        }]
      };

      cancelReasonService.GetReason().then(function (reason) {
        self.model.reasons = reason;
      });

      StatusService.GetStatus().then(function (status) {
        self.model.statusList = status;
      });

      //Methods
      self.model.OpenFromDate = OpenFromDate;
      self.model.OpenToDate = OpenToDate;
      self.model.GetGRNList = GetGRNList;
      self.model.NavigateToDetails = NavigateToDetails;
      self.model.SelectAllGRN = SelectAllGRN;
      self.model.SelectGRN = SelectGRN;
      self.model.isDisable = isDisable;
      self.model.OpenPopup = OpenPopup;
      self.model.ModalDismiss = ModalDismiss;
      self.model.RejectGRN = RejectGRN;
      self.model.SaveAcceptReject = SaveAcceptReject;
      self.model.AcceptReject = AcceptReject;
      self.model.selectedGRNId = 0;

      PopulateDropdown();
    }

    var setStatus = function (x) {
      var status = self.model.statusList.find(function (obj) {
        return obj.status == x;
      });

      return status.id;
    }

    var OpenFromDate = function () {
      self.model.fromDateOpened = true;
      self.model.toDateOptions.minDate = self.model.fromDate;
    }

    var OpenToDate = function () {
      self.model.toDateOpened = true;
      self.model.toDateOptions.minDate = self.model.fromDate;
    }

    var PopulateDropdown = function () {
      self.model.store = self.model.storeList[0];
      self.model.status = self.model.statuses[0];

      var URI = CONSTANTS.PROCUREMENT.QUOTATION.GRN_APPROVAL.DROPDOWN_API;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          self.model.storeList = self.model.storeList.concat(response.data.store);
          self.model.statuses = self.model.statuses.concat(response.data.status);
          self.model.assetTypes = response.data.assetType;

          $rootScope.stopLoader();
          GetGRNList();
        },
        function (err) {
          $rootScope.stopLoader();
        });
    }

    var ValidateDate = function () {
      var isValid = true;
      if (self.model.fromDate > self.model.toDate) {
        growl.error('From Date should lesser than To Date');
        isValid = false;
      }

      return isValid;
    }

    var GetGRNList = function () {
      if (ValidateDate()) {
        var URI = CONSTANTS.PROCUREMENT.QUOTATION.GRN_APPROVAL.SEARCH_API;

        var data = {
          "fromDate": moment(self.model.fromDate).format('YYYY-MM-DD'),
          "toDate": moment(self.model.toDate).format('YYYY-MM-DD'),
          "storeId": !!self.model.store && !!self.model.store.id ? self.model.store.id : undefined,
          "assetTypeId": self.model.grnType ? self.model.grnType.id : undefined,
          "grnNumber": self.model.grnNumber ? self.model.grnNumber : undefined,
          "statusId": !!self.model.status && !!self.model.status.id ? self.model.status.id : undefined,
        }

        $rootScope.startLoader();
        GenericService.serviceAction("POST", URI, data).then(
          function (response) {
            self.model.grnList = response.data;
            angular.forEach(self.model.grnList, function (item) {
              item["disableFlag"] = true;
              item['rejectReasonId'] = 1;
              item['rejectNote'] = ''
            });
            $rootScope.stopLoader();
          },
          function (err) {
            $rootScope.stopLoader();
            growl.error(err.data.message);
          });
      }
    }

    var NavigateToDetails = function (grn) {
      commonDetailService.setDataId(grn.id);
      $state.go('detailsGRNApproval');
    }

    var SelectGRN = function (grn) {
      var grnIds = self.model.SelectedOrderIds;
      if (grn.isSelected) {
        grn.disableFlag = false;
        grnIds.push(grn.id);
        self.model.selectedItems.push(grn);
      } else {
        grn.isReject = false;
        grn.disableFlag = true;
        var index = grnIds.indexOf(grn.id);

        if (index > -1) {
          grnIds.splice(index, 1);
        }
      }

      if (self.model.grnList.every(isSelected)) {
        self.model.selectAllRows = true;
      } else {
        self.model.selectAllRows = false;
      }
    }

    var SelectAllGRN = function () {
      if (self.model.selectAllRows) {
        self.model.SelectedOrderIds = [];
        self.model.grnList.forEach(function (grn) {
          if (!isDisable(grn)) {
            grn.isSelected = true;
            grn.disableFlag = false;
            self.model.SelectedOrderIds.push(grn.id);
          }
        });
      } else {
        self.model.SelectedOrderIds = [];
        self.model.grnList.forEach(function (grn) {
          grn.isSelected = false;
          grn.disableFlag = true;
          grn.isReject = false;
        });
      }
    }

    var isDisable = function (grn) {
      // grn.status == 'New' || grn.status == 'Pending' || 
      if (grn.status == 'Closed' || grn.status == 'Approved' || grn.approvalStatus == 'Approved') {
        return true;
      }
      return false;
    }

    var isSelected = function (grn) {
      if (!isDisable(grn))
        return grn.isSelected;
      else
        return true;
    }

    var OpenPopup = function (action, popupName, data) {
      self.model.action = action;
      var popup = angular.element('#' + popupName);
      self.model.id = data.id;
      popup.modal('show');
    }

    var RejectGRN = function (action, popupName, data) {
      self.model.selectedGRNId = data.poId;
      if (data.isReject) {
        self.model.action = action;
        var popup = angular.element('#' + popupName);
        popup.modal('show');
      } else {
        return;
      }
    }

    var AcceptReject = function () {
      if (ValidetReason()) {
        self.model.grnList.forEach(function (grn) {
          if(grn.poId == self.model.selectedGRNId) {
            grn.rejectReasonId = self.model.cancelReason;
            grn.rejectNote = self.model.rejectNote;
          }
        });
        ModalDismiss("RejectGRN");
      }
    }

    var ValidateApproval = function () {
      var isValid = true;
      if(!self.model.SelectedOrderIds || self.model.SelectedOrderIds.length == 0){
        growl.error('Select GRN to Approve');

        isValid = false;
      }

      return isValid;
    }

    var SaveAcceptReject = function () {
      if (ValidateApproval()) {
        var URI = CONSTANTS.PROCUREMENT.QUOTATION.GRN_APPROVAL.APPROVE_API;

        var data = [];
        self.model.grnList.forEach(function (grn) {
          if (grn.isSelected) {
            if (grn.isReject) {
              data.push({
                "grnId": grn.id,
                "statusId": setStatus('Closed'),
                "approvalStatusId": setStatus('Reject'),
                "rejectReasonId": grn.rejectReasonId,
                "rejectNote": grn.rejectNote
              });
            }
            else {
              data.push({
                "grnId": grn.id,
                "statusId": setStatus('Pending'),
                "approvalStatusId": setStatus('Approved'),
                "rejectReasonId": self.model.reasons[0].id,
                "rejectNote": null
              });
            }
          }
        });

        GenericService.serviceAction("PATCH", URI, data).then(
          function (response) {
            self.model.SelectedOrderIds = [];
            ModalDismiss('confirmSave');
            $rootScope.stopLoader();
            GetGRNList();
            self.model.selectAllRows = false;
            growl.success(response.data.message);
          },
          function (err) {
            ModalDismiss('confirmSave');
            $rootScope.stopLoader();
            growl.error(err.data.message);
          }
        );
      }
    }

    var ValidateStatus = function () {
      var isValid = true;
      if (!self.model.SelectedOrderIds || self.model.SelectedOrderIds.length == 0) {
        isValid = false;
      }
      return isValid;
    }

    var ValidetReason = function () {
      var isValid = true;

      if (!self.model.cancelReason) {
        self.model.isReasonValid = true;
        isValid = false;
      }

      return isValid;
    }

    var ModalDismiss = function (modal) {
      var popup = angular.element('#' + modal);
      popup.modal('hide');
      self.model.rejectNote = undefined;
      self.model.cancelReason = undefined;
    }

    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("GRNApproval", {
      url: "/GRNApproval",
      templateUrl: 'views/procurement/quotation/GRN-approval.html',
      controller: "GRNApproval.Controller",
      controllerAs: "vm"
    });
  }
    
  angular
    .module("myApp")
    .config(config)
    .controller("GRNApproval.Controller", GRNApprovalController);
})();