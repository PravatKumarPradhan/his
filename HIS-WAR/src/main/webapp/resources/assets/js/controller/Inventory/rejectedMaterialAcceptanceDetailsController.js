(function () {
  "use strict";

  function rejectedMaterialAcceptanceDetailsController($scope, CONSTANTS, $rootScope, $http, $state, commonDetailService, StatusService, GenericService, $filter, growl) {
    var self = this;
    var selectItemList = [];
    var masterId;
    //Default Constructor
    function initializeController() {
      $rootScope.loginpage = true;
      self.model = {
        SelectedItems: [],
        requestData: [],


      };
      self.model.fromMaterialRejectAcceptance = fromMaterialRejectAcceptance;
      self.model.SelectAllItems = SelectAllItems;
      self.model.SelectItem = SelectItem;
      self.model.ApprovedQuantity = ApprovedQuantity;
      self.model.SaveApproval = SaveApproval;
      self.model.setStatus = setStatus;
      self.model.RejectionPopup = RejectionPopup;
      self.model.valueOnChange = valueOnChange;
      StatusService.GetStatus().then(function (status) {
        self.model.statuses = status;
      });
      fromMaterialRejectAcceptance();
    }


    function fromMaterialRejectAcceptance() {
      var id = commonDetailService.getDataId();
      var URI = CONSTANTS.INVENTORY.STORE.REJECTED_MATERIAL_ACCEPTANCE.DETAILS_API + id;
      var data = {};
      GenericService.serviceAction("GET", URI, data).then(
        function (response) {
          self.model.materialRejectDetail = response.data;
          self.model.requestItems = JSON.parse(JSON.stringify(response.data));
          self.model.materialRejectDetail.items.forEach(function (item) {
            item.isDisabled = true;

          });
        });
    }


    function selectedAll() {
      self.model.materialRejectDetail.forEach(function (x) {
        if (self.model.selectAllRows) {
          x.isChecked = true;
        }
        else {
          x.isChecked = false;
        }
      });
    }

    var isSelected = function (item) {
      return item.isSelected;
    }

    var SelectAllItems = function () {
      if (self.model.selectAllRows) {
        self.model.SelectedRequestIds = [];
        self.model.materialRejectDetail.items.forEach(function (items) {
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
        self.model.materialRejectDetail.items.forEach(function (items) {
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

      if (self.model.materialRejectDetail.items.every(isSelected)) {
        self.model.selectAllRows = true;
      } else {
        self.model.selectAllRows = false;
      }
    }



    function valueOnChange(item, isSelectAll) {
      if (isSelectAll)
        self.model.materialRejectDetail.items.forEach(function (item) {
          if (self.model.selectAllRows)
            item.acceptedQuantity = item.returnQuantity;
          else
            item.acceptedQuantity = 0;
        });
      else
        if (item.isSelected)
          item.acceptedQuantity = item.returnQuantity;
        else
          item.acceptedQuantity = 0;
    }
  
  
    var ApprovedQuantity = function (item) {
      if (item.acceptedQuantity > item.returnQuantity) {
        item.rejectQuantity = 0;
        self.model.rejectQty = undefined;
        item.isReject = false; 
      } else if (item.acceptedQuantity == 0 || !item.acceptedQuantity) {
        item.rejectQuantity = item.returnQuantity;
        self.model.rejectQty = item.rejectQuantity;
        item.isReject = true;
      } else if (item.acceptedQuantity < item.returnQuantity) {
        item.rejectQuantity = item.returnQuantity - item.acceptedQuantity;
        self.model.rejectQty = item.rejectQuantity;
        item.isReject = true; 
      } else if (item.acceptedQuantity == item.returnQuantity) {
        item.rejectQuantity = item.returnQuantity - item.acceptedQuantity;
        self.model.rejectQty = item.rejectQuantity;
        item.isReject = false; 
      }
    }

    var RejectionPopup = function (item) {
      // console.log(self.model.status);
      if (item.isReject) {
        var itemId = item.id;
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
      var prevRequest = self.model.requestItems.items.find(function (prevItem) {
        return prevItem.id == item.id;
      });
      if (prevRequest) {
        item.rejectQuantity = prevRequest.rejectQuantity;
        item.returnQuantity = prevRequest.returnQuantity;
        item.rejectNote = prevRequest.rejectNote;
        item.rejectReasonId = prevRequest.rejectReasonId;
        item.statusId = prevRequest.statusId;
      }
    }
    var SaveApproval = function () {
      if (ValidateApproval()) {
        var URI = CONSTANTS.INVENTORY.STORE.REJECTED_MATERIAL_ACCEPTANCE.SAVE_API + commonDetailService.getDataId();;
        var data = {
          "statusId": setStatus('Completed'),
          "items": []
        }

        $rootScope.startLoader();
        angular.forEach(self.model.SelectedItems, function (item) {
          var newItem = {
            "batchMapperId": item.batchMapperId,
            "acceptedQuantity": parseInt(item.acceptedQuantity),
            "rejectQuantity": parseInt(item.rejectQuantity),
            "rejectReasonId": 1,
            "rejectNote": "Note"
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

      return isValid;
    }
    var NavigateToBack = function () {
      $state.go('materialIssueAcceptance');
    }

    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("detailsRejectedMaterialAcceptance", {
      url: '/detailsRejectedMaterialAcceptance',
      templateUrl: 'views/PharmacyInventory/InPatient/details-rejected-material-acceptance.html',
      controller: "RejectedMaterialAcceptanceDetails.Controller",
      controllerAs: 'vm'
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("RejectedMaterialAcceptanceDetails.Controller", rejectedMaterialAcceptanceDetailsController)
})();
