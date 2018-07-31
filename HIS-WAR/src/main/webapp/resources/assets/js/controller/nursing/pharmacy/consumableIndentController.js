(function () {
  "use strict";
  var CACHE = {};

  function consumableIndentController($scope, $state, $rootScope, $http, $filter, CONSTANTS, StatusService, GenericService, storeIndentService, growl, commonDetailService) {
    var self = this;
    var mainStoreList = [];

    //Default Constructor
    function initializeController() {
      $rootScope.loginpage = true;
      self.model = {
        storeList: [{
          "store": "All"
        }],
        assetCategories: [{
          "category": "Select Asset Category"
        }],
        productCategories: [{
          "category": "Select Product Category"
        }],
        visitNumbers: [{
          "no": "Select Visit No"
        }],
        SelectedItemsIds: [],
        selectAllRows: false,
        // SelectedItemIds: [],
        ItemDetailList: [],
        indentId: null,
        consignment: false
      };

      StatusService.GetStatus().then(function (status) {
        self.model.Status = status;
      })

      self.model.SearchpatientNames = SearchpatientNames;
      self.model.SearchUhid = SearchUhid;
      self.model.AddPatientDetails = AddPatientDetails;
      self.model.search = '';
      self.model.prevSearch = '';
      self.model.patientNames = [];
      self.model.GetItemList = GetItemList;
      self.model.GetAssetCategoryList = GetAssetCategoryList;
      self.model.GetProductCategoryList = GetProductCategoryList;
      self.model.SearchNewItem = SearchNewItem;
      self.model.searchNewItemList = [];
      self.model.SelectItem = SelectItem;
      self.model.SelectAllItems = SelectAllItems;
      self.model.AddItems = AddItems;
      self.model.RemoveRowData = RemoveRowData;
      self.model.Validate = Validate;
      self.model.SaveConsumableIndent = SaveConsumableIndent;
      self.model.UpdatePatientIndent = UpdatePatientIndent;

      // self.model.indentId = commonDetailService.getDataId();
      // GetPatientIndentDetails(commonDetailService.getDataId());

      PopulateDropdown();
      GetAssetCategoryList();
    }

    var GetPatientIndentDetails = function (itemId) {

      var URI = CONSTANTS.PHARMACY.OP.PATIENT_INDENT.DETAIL_API + itemId;
      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          self.model.indentId = response.data.id;
          self.model.ItemDetailList = response.data.items;
          $rootScope.stopLoader();
        });
     
    }

    var PopulateDropdown = function () {

      var URI = CONSTANTS.PHARMACY.OP.PATIENT_INDENT.DROPDOWN_API;

      self.model.store = self.model.storeList[0];
      self.model.visitNo = self.model.visitNumbers[0];

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          self.model.visitTypeDataList = response.data.visitType;
          self.model.visitType = self.model.visitTypeDataList[0];
          self.model.storeList = self.model.storeList.concat(response.data.store);

          var indentId = commonDetailService.getDataId();
          if (!!indentId) {
            commonDetailService.setDataId(null);
            GetPatientIndentDetails(indentId);
          }
          $rootScope.stopLoader();
        },
        function (err) {
          $rootScope.stopLoader();
        });
    }

    var SearchpatientNames = function (search) {

      if (search.length < 3) return;

      if ((!self.model.prevSearch || self.model.prevSearch != search) && (search.length == 3)) {
        self.model.prevSearch = search;

        var URI = CONSTANTS.GLOBAL.PATIENT_SEARCH_API + search;

        $rootScope.startLoader();
        return GenericService.serviceAction("GET", URI, {})
          .then(function (response) {
            $rootScope.stopLoader();
            if (!!response.data && response.data.length > 0) {
              self.model.patientNames = response.data;
              self.model.patientId = response.data.patientId;
              self.model.uhid = response.data.uhid;
              $rootScope.stopLoader();
              //console.log(self.model.patientNames.itemName);
              return $filter('filter')(self.model.patientNames, {
                $: search
              });
            } else {
              $rootScope.stopLoader();
              return PatientNameNotFound(search);
            }
          }, function (err) {
            $rootScope.stopLoader();
            return PatientNameNotFound(search);
          });
      } else {
        if (!!self.model.patientNames && self.model.patientNames.length > 0 &&
          self.model.patientNames[0].itemFound != undefined && !self.model.patientNames[0].itemFound) {
          return PatientNameNotFound(search);
        } else {
          return $filter('filter')(self.model.patientNames, {
            $: search
          });
        }
      }
    }

    var PatientNameNotFound = function (search) {
      var item = {
        "itemFound": false,
        "details": "Patient Not found with name " + search
      };
      self.model.patientNames = [item];
      return self.model.patientNames;
    }

    var AddPatientDetails = function (item) {

      if (!!item.patientId) {
        var URI = CONSTANTS.GLOBAL.PATIENT_DETAILS_SEARCH_API;

        var data = {
          "visitType": !!self.model.visitType && !!self.model.visitType.id ? self.model.visitType.id : undefined,
          "patientId": !!item.patientId ? item.patientId : undefined,
          // "uhid": !!item.uhid ? item.uhid : undefined,
        };

        $rootScope.startLoader();
        GenericService.serviceAction("POST", URI, data)
          .then(function (response) {

            self.model.patientName = response.data.patientName,
              self.model.patientId = response.data.patientId,
              self.model.uhid = response.data.uhid,
              self.model.wardName = response.data.wardName,
              self.model.wardId = response.data.wardId,
              self.model.bedNo = response.data.bedNo,
              self.model.bedId = response.data.bedId,
              self.model.encounterId = response.data.encounterId,
              self.model.admissionId = response.data.admissionId,
              self.model.visitNumbers = self.model.visitNumbers.concat(response.data.visitNo);

            $rootScope.stopLoader();
          }, function (err) {
            growl.error('Error while fetching the patient details');
            $rootScope.stopLoader();
          });
      }
    }

    var SearchUhid = function (search) {

      if (search.length < 3) return;

      if ((!self.model.prevSearch || self.model.prevSearch != search) && (search.length == 3)) {
        self.model.prevSearch = search;

        var URI = CONSTANTS.GLOBAL.UHID_SEARCH_API + search;

        $rootScope.startLoader();
        return GenericService.serviceAction("GET", URI, {})
          .then(function (response) {
            $rootScope.stopLoader();
            if (!!response.data && response.data.length > 0) {
              self.model.patientNames = response.data;
              self.model.patientId = response.data.patientId;
              self.model.uhid = response.data.uhid;
              $rootScope.stopLoader();
              //console.log(self.model.patientNames.itemName);
              return $filter('filter')(self.model.patientNames, {
                $: search
              });
            } else {
              $rootScope.stopLoader();
              return UHIDNotFound(search);
            }
          }, function (err) {
            $rootScope.stopLoader();
            return UHIDNotFound(search);
          });
      } else {
        if (!!self.model.patientNames && self.model.patientNames.length > 0 &&
          self.model.patientNames[0].itemFound != undefined && !self.model.patientNames[0].itemFound) {
          return UHIDNotFound(search);
        } else {
          return $filter('filter')(self.model.patientNames, {
            $: search
          });
        }
      }
    }

    var UHIDNotFound = function (search) {
      var item = {
        "itemFound": false,
        "details": "UHID Not found with search " + search
      };
      self.model.patientNames = [item];
      return self.model.patientNames;
    }

    var GetItemList = function () {
      self.model.action = 'Open';
      var popup = angular.element('#addNewItemWasteStore');
      popup.modal('show');

    }

    var GetAssetCategoryList = function () {
      var indents = self.model;
      var URI = CONSTANTS.GLOBAL.ASSET_CATEGORY_API + 1;

      indents.assetCategory = indents.assetCategories[0];
      indents.productCategory = indents.productCategories[0];

      indents.assetCategories.splice(1);
      indents.productCategories.splice(1);

      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          indents.assetCategories = indents.assetCategories.concat(response.data);
          $rootScope.stopLoader();
        },
        function (err) {
          $rootScope.stopLoader();
        });
    }

    var GetProductCategoryList = function (id) {
      var indents = self.model;

      var URI = CONSTANTS.GLOBAL.PRODUCT_CATEGORY_API + id;
      $rootScope.startLoader();
      GenericService.serviceAction("GET", URI, {}).then(
        function (response) {
          indents.productCategories = indents.productCategories.concat(response.data);
          $rootScope.stopLoader();
        },
        function (err) {
          $rootScope.stopLoader();
        });
    }

    var SearchNewItem = function () {

      var URI = CONSTANTS.GLOBAL.ITEM_API;

      const data = {
        "assetTypeId": 1,
        "toStoreId": self.model.store.id ? self.model.store.id : undefined,
        "assetCategoryId": self.model.assetCategory.id ? self.model.assetCategory.id : undefined,
        "productCategoryId": self.model.productCategory.id ? self.model.productCategory.id : undefined,
        "isConsignment": self.model.isConsignment ? self.model.isConsignment.id : undefined,
      }
      self.model.searchNewItemList = [];
      $rootScope.startLoader();
      GenericService.serviceAction("POST", URI, data).then(
        function (response) {
          $rootScope.stopLoader();
          self.model.searchNewItemList = response.data;
        },
        function errorCallback(response) {
          $rootScope.stopLoader();
          growl.error('Error');
        });
    }

    var SelectAllItems = function () {
      if (self.model.selectAllRows) {
        self.model.SelectedItemsIds = self.model.searchNewItemList.map(function (item) {
          item.isSelected = true;
          return item.itemId;
        });
      } else {
        self.model.SelectedItemsIds = [];
        self.model.searchNewItemList.forEach(function (item) {
          item.isSelected = false;
        });
      }
    }

    var isSelected = function (item) {
      return item.isSelected;
    }

    var SelectItem = function (item) {
      var itemIds = self.model.SelectedItemsIds;

      if (item.isSelected) {
        itemIds.push(item.itemId);
      } else {
        var index = itemIds.indexOf(item.itemId);
        if (index > -1) {
          itemIds.splice(index, 1);
        }
      }

      if (self.model.searchNewItemList.every(isSelected)) {
        self.model.selectAllRows = true;
      } else {
        self.model.selectAllRows = false;
      }
    }

    var AddItems = function () {
      // var URI = constants.apiurl + '/api/common/items/details'; 
      var URI = CONSTANTS.GLOBAL.ITEM_DETAILS_API;

      var data = {
        "itemIds": self.model.SelectedItemsIds,
        "storeId": self.model.store.id
      };
      $rootScope.startLoader();
      GenericService.serviceAction("POST", URI, data).then(
        function (response) {
          response.data.forEach(function (item) {

            var isFound = self.model.ItemDetailList.find(function (x) {
              return x.itemId == item.itemId;
            });
            if (!isFound) {
              self.model.ItemDetailList.push(item);
            }

          });
          // self.model.ItemDetailList = self.model.ItemDetailList.concat(response.data);
          self.model.SelectedItemsIds = [];
          self.model.items = [];
          var selectedUom;
          angular.forEach(self.model.ItemDetailList, function (item) {
            if (!item.id) {
              selectedUom = item.uom.find(function (obj) {
                return obj.opUom;
              });

              item["uomType"] = selectedUom.uomType;
              item["uomUnit"] = selectedUom.uomUnit;
              item["uomTypeId"] = selectedUom.uomTypeId;
              item["uomUnitId"] = selectedUom.uomUnitId;
            }
          });

          // console.log(selectedUom);
          $rootScope.stopLoader();
          // getAllItemDetails();
          self.model.searchNewItemList = [];
        },
        function (err) {
          $rootScope.stopLoader();
          growl.error(err.data.message);
        });
    }

    var RemoveRowData = function (index) {
      var ItemDetailList = self.model.ItemDetailList;
      self.model.ItemDetailList = ItemDetailList.slice(0, index).concat(ItemDetailList.slice(index + 1))
    }

    var Validate = function () {
      var isValid = true;

      self.model.isPatientNameValid = false;
      self.model.isVisitTypeValid = false;
      self.model.isVisitNoValid = false;

      if (!self.model.patientName) {
        self.model.isPatientNameValid = true;
        isValid = false;
        growl.error('Enter patient name')
      }
      if (!self.model.visitType.id) {
        self.model.isVisitTypeValid = true;
        isValid = false;
        growl.error('Select visit type')
      }
      if (!self.model.visitNo.id) {
        self.model.isVisitNoValid = true;
        isValid = false;
        growl.error('Select visit no')
      }

      var ItemDetailList = self.model.ItemDetailList;
      if (!ItemDetailList || ItemDetailList.length <= 0) {
        isValid = false;
        growl.error("Please add some items in the bill");
      } else {
        ItemDetailList.forEach(function (item) {

          self.model.isRemarkValid = false;
          item['isQuantityValid'] = false;

          if (!item.quantity) {
            item['isQuantityValid'] = true;
            isValid = false;
            growl.error('Enter quantity')
          }
          if (!self.model.remark) {

            self.model.isRemarkValid = true;
            isValid = false;
            growl.error('Enter Remark')
          }
        });
      }
      return isValid;
    }

    var SetStatus = function (x) {
      var status = self.model.Status.find(function (obj) {
        return obj.status == x;
      });
      return status.id;
    }

    var SaveNewPatientIndentRequest = function () {
      var ItemDetailList = self.model.ItemDetailList;
      // if (Validate()) {
      var URI = CONSTANTS.PHARMACY.OP.PATIENT_INDENT.SAVE_API;

      var data = {

        "patientId": !!self.model.patientId ? self.model.patientId : undefined,
        "visitType": !!self.model.visitType && !!self.model.visitType.id ? self.model.visitType.id : undefined,
        "doctorId": !!self.model.doctorId ? self.model.doctorId : undefined,
        "admissionId": !!self.model.admissionId ? self.model.admissionId : undefined,
        "encounterId": !!self.model.encounterId ? self.model.encounterId : undefined,
        "ward": !!self.model.wardId ? self.model.wardId : undefined,
        "bed": !!self.model.bedId ? self.model.bedId : undefined,
        "toStoreId": !!self.model.store && !!self.model.store.id ? self.model.store.id : undefined,
        "consignment": !!self.model.consignment ? self.model.consignment : undefined,
        "remark": self.model.remark,
        "statusId": SetStatus('New'),
        "approvalStatusId": SetStatus('New'),
        "items": []
      }

      angular.forEach(ItemDetailList, function (item, key) {
        var item = {
          "itemId": item.itemId,
          "uomTypeId": item.uomTypeId,
          "uomUnitId": item.uomUnitId,
          "indentQuantity": item.quantity,
          "statusId": SetStatus('New')
        }
        data.items.push(item);
      });

      $rootScope.startLoader();
      GenericService.serviceAction("POST", URI, data).then(
        function (response) {
          NavigteToPatientIndent();
          $rootScope.stopLoader();
          growl.success('Consumable indent successffully saved');
        },
        function (err) {
          $rootScope.stopLoader();
          growl.error('Error while saving consumable indent ');
        });
      // }
    }

    var UpdatePatientIndent = function () {

      var URI = CONSTANTS.PHARMACY.OP.PATIENT_INDENT.UPDATE_API + self.model.indentId;
      var data = {
        "consignment": self.model.consignment,
        "remark": self.model.remark,
        "items": []
      };

      angular.forEach(self.model.ItemDetailList, function (item, key) {
        var indentItem = {
          "id": item.id,
          "itemId": item.itemId,
          "indentQuantity": item.quantity
        }

        data.items.push(indentItem);
      });

      $rootScope.startLoader();
      GenericService.serviceAction("PATCH", URI, data).then(
        function (response) {
          NavigteToPatientIndent();
          $rootScope.stopLoader();
          growl.success('Consumable indent successffully updated');
        },
        function (err) {
          $rootScope.stopLoader();
          growl.error('Error while updating consumable indent ');
        });
    }

    var SaveConsumableIndent = function () {
      if (Validate()) {
        if (!self.model.indentId) {
          SaveNewPatientIndentRequest();
        } else {
          UpdatePatientIndent();
        }
      }
    }

    var NavigteToPatientIndent = function () {
      $state.go('patientIndent');
    }

    initializeController();
  }


  function config($stateProvider) {
    $stateProvider.state("consumableIndent", {
      url: "/consumableIndent",
      templateUrl: 'views/PharmacyInventory/InPatient/consuable-indent-add-new.html',
      controller: "ConsumableIndent.Controller",
      controllerAs: "vm"
    });
  }

  angular
    .module("myApp")
    .config(config)
    .controller("ConsumableIndent.Controller", consumableIndentController);
})();