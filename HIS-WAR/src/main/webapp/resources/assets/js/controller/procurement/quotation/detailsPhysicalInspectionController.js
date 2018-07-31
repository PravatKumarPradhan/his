(function () {
  "use strict";

  function detailsPhysicalInspectionController($scope, $rootScope, $http, $filter, $state, CONSTANTS, GenericService, commonDetailService, cancelReasonService, StatusService, growl) {
    var self = this;
    var rejectData;

    //Default Constructor
    function initializeController() {
      $rootScope.loginpage = true;

      self.model = {
        selectAllRows: false,
        selectedItemId: 0,
        storageDetailsList: [],
        SelectedOrderIds: [],
        selectedItems: [],
        selectedStorageIndex: null,
        selectItemId: null,
        inspectId: null,
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
      self.model.SelectAllOrders = SelectAllOrders;
      self.model.SelectItem = SelectItem;
      self.model.isDisable = isDisable;
      self.model.OpenPopup = OpenPopup;
      self.model.ModalDismiss = ModalDismiss;
      self.model.RejectPI = RejectPI;
      self.model.SaveAcceptReject = SaveAcceptReject;
      self.model.SaveApproval = SaveApproval;
      self.model.SaveRejection = SaveRejection;
      self.model.selectedApproveRejectList = [];
      self.model.NavigateToBack = NavigateToBack;
      
      self.model.DetailsClick = DetailsClick;
      self.model.ChangeRack = ChangeRack;
      self.model.AddRack = AddRack;
      self.model.lessRack = lessRack;
      self.model.SaveStorageDetails = SaveStorageDetails;
      self.model.selfList = [];
      GetPhysicalInspectionData(commonDetailService.getDataId());
    }

    var SaveStorageDetails = function (){
      self.model.physicalInspectionData.items.forEach(function(item){
        if (self.model.selectItemId == item.id) {
          self.model.storageDetailsList.forEach(function(x){
            item["storageDetails"].push({rackId: x.rackName.rackId, shelfId: x.selfName.id, remark: x.remark});
          });
          // item.storageDetails = self.model.storageDetailsList;
        }
      });
      console.log(self.model.physicalInspectionData.items);
    }

    var ChangeRack = function (rackName) {
      rackName.shelfs = rackName.shelfs;
    }

    var DetailsClick = function (item) {
      item.storageDetails = [];
      self.model.selectItemId = item.id;
      self.model.selectedStorageIndex = self.model.physicalInspectionData.items.indexOf(item);
    }

    var AddRack = function (idx) {
      self.model.storageDetailsList.push({ rackId: '', shelfId:'', remark: ''});
    }

    var lessRack = function (idx) {
      self.model.storageDetailsList.splice(idx, 1);
    }

    var setStatus = function (x) {
      var status = self.model.statusList.find(function (obj) {
        return obj.status == x;
      });

      return status.id;
    }

    var GetPhysicalInspectionData = function (id) {
      commonDetailService.setDataId(null);
      self.model.inspectId = id;
      var URI = CONSTANTS.PROCUREMENT.QUOTATION.PHYSICAL_INSPECTION.DETAILS_API + id;

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          self.model.physicalInspectionData = response.data;
          self.model.requestItems = JSON.parse(JSON.stringify(response.data));
          self.model.physicalInspectionData.items.forEach(function(item){
            item.isDisabled = true;
            item.storageDetails = [];
          });
          self.model.rackList = response.data.racks;
          self.model.rejectReasons = self.model.reasons;
          // todo:
          self.model.storageDetailsList = [{rackId: '', shelfId: '', remark: ''}]
          $rootScope.stopLoader();
        },
        function (err) {
          $rootScope.stopLoader();
          growl.error(err.data.message);
        });
    }

    var NavigateToBack = function () {
      $state.go('physicalInspec');
    }

    var OpenPopup = function (action, popupName, data) {
      self.model.action = action;
      var popup = angular.element('#' + popupName);
      self.model.id = data.id;
      popup.modal('show');
    }

    var RejectPI = function (action, popupName, data) {
      self.model.selectedItemId = data.id;
      if (data.isReject) {
        self.model.action = action;
        var popup = angular.element('#' + popupName);
        self.model.rejectReasonId = self.model.reasons[0].id;
        popup.modal('show');
      } else {
        CancelRejection(data);
        return;
      }
    }

    var SaveApproval = function (item) {
      if (item.approvedQuantity > item.receivedQuantity) {
        item.rejectQuantity = 0;
        self.model.rejectQty = 0;
        item.rejectReasonId = self.model.reasons[0].id;
        item.isReject = false;
        item.statusId = setStatus('Approved');
      } else if (item.approvedQuantity == 0 || !item.approvedQuantity) {
        item.approvedQuantity = 0
        item.rejectQuantity = item.receivedQuantity;
        self.model.rejectQty = item.rejectQuantity;
        item.rejectReasonId = self.model.reasons[0].id;
        item.isReject = true;
        item.statusId = setStatus('Reject');
      } else if (item.approvedQuantity < item.receivedQuantity) {
        item.rejectQuantity = item.receivedQuantity - item.approvedQuantity;
        self.model.rejectQty = item.rejectQuantity;
        item.rejectReasonId = self.model.reasons[0].id;
        item.isReject = true;
        item.statusId = setStatus('Partially Approved');
      } else if (item.approvedQuantity == item.receivedQuantity) {
        item.rejectQuantity = item.receivedQuantity - item.approvedQuantity;
        self.model.rejectQty = item.rejectQuantity;
        item.rejectReasonId = self.model.reasons[0].id;
        item.isReject = false;
        item.statusId = setStatus('Approved');
      }
    }

    var SaveRejection = function () {
      // if (ValidateRejection()) {
        self.model.physicalInspectionData.items.forEach(function (item) {
          if (item.id == self.model.selectedItemId) {
            if (self.model.rejectQty > item.receivedQuantity) {
              growl.error('Please Enter Valid Quantity');
              return false;
            } else if (self.model.rejectQty == item.receivedQuantity) {
              item.rejectQuantity = item.receivedQuantity;
              item.approvedQuantity = item.receivedQuantity - item.rejectQuantity;
              item.rejectReasonId = self.model.rejectReasons[0].id;
              item.statusId = setStatus('Reject');
            } else if (self.model.rejectQty == 0 || !self.model.rejectQty) {
              item.approvedQuantity = item.receivedQuantity;
              item.rejectQuantity = 0;
              item.rejectReasonId = self.model.rejectReasons[0].id;
              item.statusId = setStatus('Approved');
            } else {
              item.rejectQuantity = self.model.rejectQty;
              item.rejectNote = self.model.rejectNote;
              item.approvedQuantity = item.receivedQuantity - item.rejectQuantity;
              item.rejectReasonId = self.model.rejectReasons[0].id;
              item.statusId = setStatus('Partially Approved');
            }
            ModalDismiss('qtyaccepatReject');
          }
        });
      // }
    }

    var ValidateSelectedItems = function () {
      var isValid = true;

      if (!self.model.selectedItems || self.model.selectedItems.length <= 0) {
        growl.error('Select Items to Save');
        isValid = false;
      }

      return isValid;
    }

    var SaveAcceptReject = function () {
      if (ValidateSelectedItems()) {
        var URI = CONSTANTS.PROCUREMENT.QUOTATION.PHYSICAL_INSPECTION.SAVE_API;

        var data = {
          "id": self.model.inspectId,
          "remark": self.model.remark,
          "statusId": setStatus('Closed'),
          "items": []
        };
        
        $rootScope.startLoader();
        self.model.physicalInspectionData.items.forEach(function (purchase) {
          if (purchase.isSelected) {
            if (purchase.isReject) {
              data.items.push({
                "id": purchase.id,
                "approvedQuantity": parseInt(purchase.approvedQuantity),
                "rejectQuantity": parseInt(purchase.rejectQuantity),
                "rejectReasonId": purchase.rejectReasonId,
                "rejectNote": purchase.rejectNote,
                "statusId": purchase.statusId,
                "storageDetails": purchase.storageDetails
              });
            }
            else {
              data.items.push({
                "id": purchase.id,
                "approvedQuantity": parseInt(purchase.approvedQuantity),
                "rejectQuantity": parseInt(purchase.rejectQuantity),
                "rejectReasonId": 1,
                "rejectNote": null,
                "statusId": purchase.statusId,
                "storageDetails": purchase.storageDetails
              });
            }
          }
        });

        GenericService.serviceAction("POST", URI, data).then(
          function (response) {
            self.model.SelectedOrderIds = [];
            self.model.selectAllRows = false;
            growl.success(response.data.message);
            NavigateToBack();
            $rootScope.stopLoader();
          },
          function (err) {
            growl.error(err.data.message);
            $rootScope.stopLoader();
          }
        );
      }
    }

    var SelectItem = function (purchase) {
      var purchaseIds = self.model.SelectedOrderIds;
      if (purchase.isSelected) {
        purchase.isDisabled = false;
        purchase.approvedQuantity = purchase.receivedQuantity;
        purchaseIds.push(purchase.id);
        self.model.selectedItems.push(purchase);
      } else {
        purchase.isReject = false;
        purchase.isDisabled = true;
        purchase.approvedQuantity = 0;
        CancelRejection(purchase);
        var index = purchaseIds.indexOf(purchase.id);

        if (index > -1) {
          purchaseIds.splice(index, 1);
        }
      }

      if (self.model.physicalInspectionData.items.every(isSelected)) {
        self.model.selectAllRows = true;
      } else {
        self.model.selectAllRows = false;
      }
    }

    var SelectAllOrders = function () {
      if (self.model.selectAllRows) {
        self.model.SelectedOrderIds = [];
        self.model.physicalInspectionData.items.forEach(function (purchase) {
          if (!isDisable(purchase)) {
            purchase.isSelected = true;
            purchase.isDisabled = false;
            purchase.approvedQuantity = purchase.receivedQuantity;
            self.model.SelectedOrderIds.push(purchase.id);
          }
        });
      } else {
        self.model.SelectedOrderIds = [];
        self.model.physicalInspectionData.items.forEach(function (purchase) {
          CancelRejection(purchase);
          purchase.isSelected = false;
          purchase.isDisabled = true;
          purchase.isReject = false;
          purchase.approvedQuantity = 0;
        });
      }
    }

    var isDisable = function (purchase) {
      // if (purchase.status == 'Closed' || purchase.status == 'Reject' || purchase.status == 'Approved'){
        // purchase.status !== 'Partially Approved' || purchase.status !== 'Pending' || purchase.status !== 'New' || purchase.status !== 'Partially Completed'
      if (true){
        return true;
      }
      return false;
    }

    var isSelected = function (purchase) {
      if (!isDisable(purchase))
        return purchase.isSelected;
      else
        return true;
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

    var CancelRejection = function (item) {
      var prevRequest = self.model.requestItems.items.find(function(prevItem){
        return prevItem.id == item.id;
      });

      if (prevRequest) {
        item.statusId = prevRequest.statusId;
        item.rejectQuantity = 0;
      }
    }

    initializeController();
  }

  function config($stateProvider) {
    $stateProvider.state("detailsphysicalInspec", {
      url: "/detailsphysicalInspec",
      templateUrl: 'views/procurement/quotation/details-physical-inspection.html',
      controller: "PIDetails.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("PIDetails.Controller", detailsPhysicalInspectionController);
})();