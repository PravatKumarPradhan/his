angular.module('myApp').constant('CONSTANTS', function () {
  var host = '';
  var root = 'HIS-WAR_V1.7';
  var rootUrl = host + root;
  var globalApi = rootUrl + '/api/global';
  var globalsApi = rootUrl + '/api/globals';
  var pharmacyApi = rootUrl + '/api/pharmacy';
  var inventoryApi = rootUrl + '/api/inventory';
  var procurementApi = rootUrl + '/api/procurements';
  var unitsApi = rootUrl + '/api/units';
  var unitApi = rootUrl + '/api/unit';

  return {
    HOST: host,
    ROOT_URL: rootUrl,
    GLOBAL: {
      USER_STORE_API: globalApi + '/user/stores',
      ASSET_TYPE_API: globalApi + '/assets/types/',
      ASSET_CATEGORY_API: globalApi + '/assets/categories/',
      PRODUCT_CATEGORY_API: globalApi + '/products/categories/',
      MASTER_PRODUCT_CATEGORY_API: globalsApi + '/products/categories/',
      ITEM_API: globalApi + '/items/',
      BATCH_API: globalApi + '/items/batches/',
      NEAR_EXPIRY: globalApi + '/items/batches/near/expiry',
      PATIENT_SEARCH_API: globalApi + '/patient/search/',
      DOCTOR_SEARCH_API: globalApi + '/doctor/search/',
      SALE_TYPE_DROPDOWN_API: globalApi + '/sale/type',
      FLOOR_API: globalApi + '/floor',
      WARD_API: globalApi + '/ward',
      VENDOR_SEARCH_API: globalApi + '/vendor/search/',
      MANUFACTURER_SEARCH_API: globalApi + '/manufacturer/search/',
      GET_INDENTS_API: globalApi + '/stores/indents/',
      AGAINST_PO_SEARCH: globalApi + '/against/purchase/order/search/',
      AGAINST_PO_DETAILS: globalApi + '/against/purchase/order/details',
      AGAINST_PR_SEARCH: globalApi + '/against/purchase/request/search',
      AGAINST_PR_DETAILS: globalApi + '/against/purchase/request/details',
      DIRECT_PR_DETAILS: globalApi + '/direct/purchase/request/details',
      STATUS_API: globalApi + '/status',
      ITEM_DETAILS_API: globalApi + '/items/details',
      CANCEL_REASON_API: globalApi + '/cancel/reasons',
      REJECT_REASON_API: globalApi + '/reject/reasons',
      CURRENCY_API: globalApi + '/currencies',
      UHID_SEARCH_API: globalApi + '/patient/search/uhid/',
      BATCH_ITEM_API: globalApi + '/items/batches/details',
      STORE_TYPE_API: globalApi + '/store/type',
      PATIENT_DETAILS_SEARCH_API: globalApi + '/patient/search',
      PATIENT_ISSUE_DROPDOWN_API: globalApi + '/pharmacy/patient/issue/dropdown',
      RACK_API: globalApi + '/racks/',
      SHELF_API: globalApi + '/racks/shelves/',
      PAYMENT_MODE_API: unitApi + '/vendors/paymentModes',
      COUNTRY_API: unitApi + '/vendors/countries',
      STATE_API: unitApi + '/vendors/states/',
      CITY_API: unitApi + '/vendors/cities/',
      AREA_API: unitApi + '/vendors/areas/',
      GRN_API: globalApi + '/against/grn'

    },
    MASTER: {
      GLOBAL: {
        PHARAMACY: {
          ITEM_MASTER:
            {
              SEARCH_API: globalsApi + '/items',
              DETAILS_API: globalsApi + '/items/',
              SAVE_API: globalsApi + '/items',
              FORMULATION_TYPE_API: globalsApi + '/items/formulation/type/',
              GENERIC_API: globalsApi + '/items/generic/',
              UNIT_API: globalsApi + '/strength/units',
              MANUFACTURE_API: globalsApi + '/manufacturers/',
              TAX_API: globalsApi + '/tax/components/',
            },
          MANUFACTURER_MASTER: {
            MANUFATURER_API: globalsApi + '/manufacturers/',
          },
          TAX_COMPONENT_MASTER: {
            TAX_COMPONENT_API: globalsApi + '/tax/components/',
            TAX_COMPONENT_MASTER: globalsApi + '/tax/',
          },
          UNIT_MASTER: {
            UNIT_API: globalsApi + '/uom/units/',
          },
          STORAGE_UNIT_MASTER: {
            STORAGE_UNIT_API: globalsApi + '/storage/units/',
          },
          STRENGTH_UNIT_MASTER: {
            STRENGTH_UNIT_API: globalsApi + '/strength/units/',
          },
          OTHER_CHARGES_MASTER: {
            OTHER_CHARGES_API: globalsApi + '/other/charges/',
          },
        }
      },
      UNIT: {
        FIXED_ASSET: {
          ASSET_CATEGORIES_API: unitsApi + '/assets/categories/',
          FIXED_ASSET_MASTER: unitApi + '/fixed/asset/',
          TYPE_DROPDOWN_API: globalApi + '/fixed/assets/types',
          FIXED_ASSET_TYPE_MASTER_API: unitApi + '/fixed/asset/type/',
        },
        FIXED_ASSET_TYPE_MASTER: {
          FIXED_ASSET_TYPE_LS_API: unitApi + '/fixed/asset/type',
          FIXED_ASSET_TYPE_DU_API: unitApi + '/fixed/asset/type/',
        },
        ITEM: {
          ASSET_CATEGORIES_API: unitsApi + '/assets/categories/',
        },
        PHARAMACY: {
          PHARMALOGICAL_CLASSIFICATION_MASTER: {
            PHARMALOGICAL_CLASSIFICATION_API: unitsApi + '/pharmacological/classifications/',
          },
          MAIN_STORE_MASTER: {
            MAIN_STORE_MASTER_LS_API: unitApi + '/main/stores',
            MAIN_STORE_MASTER_DU_API: unitApi + '/main/stores/',
          },
          VENDOR_MASTER: {
            VENDOR_MASTER_API: unitApi + '/vendors/'
          },
          STORE_ITEM_MAPPER: {
            STORE_ITEM_MAPPER_API: unitApi + '/store/item/mapper',
          },
          STORE_WISE_GENERIC_MAPPER: {
            STORE_WISE_GENERIC_API: unitApi + '/storewise/generic/mapper',
          }
        },
        APPLICATION_CONFIGURATION: {
          APPLICATION_CONFIGURATION_SAVE_API: globalsApi + '/applicationConfiguration/save',
          APPLICATION_CONFIGURATION_UPDATE_API: globalsApi + '/applicationConfiguration/',
          TAX_DROPDOWN: globalsApi + '/tax/dropdown',
        },
        CONSUMABLE_BARCODE_CONFIG: {
          CONSUMABLE_BARCODE_CONFIG_SAVE_API: globalsApi + '/consumableBarcodeConfig/save',
          CONSUMABLE_BARCODE_CONFIG_UPDATE_API: globalsApi + '/consumableBarcodeConfig/',
        },
        RACK_MASTER: {
          RACK_MASTER_API: unitsApi + '/rack/'
        },
        PRODUCT_CATEGORY_MAPPING: {
          PRODUCT_CATEGORY_MAPPING_API: unitApi + '/product/restrictions/product/category/mapper/'
        }
      },

    },
    PHARMACY: {
      OP: {
        WALK_IN_SALES: {
          DROPDOWN_API: pharmacyApi + '/sales/walkins/dropdowns',
          ALLOCATE_API: pharmacyApi + '/sales/walkins/items/1/',
          ALLOCATE_BATCH_API: pharmacyApi + '/sales/walkins/batch/allocate/1/',
          SAVE_API: pharmacyApi + '/sales/walkins'
        },
        BILL_LIST: {
          DROPDOWN_API: pharmacyApi + '/sales/bills/dropdowns',
          SEARCH_API: pharmacyApi + '/sales/bills/search',
          DETAILS_API: pharmacyApi + '/sales/bills/'
        },
        SALES_RETURN: {
          SEARCH_API: pharmacyApi + '/op/sale/return/search',
          DETAILS_API: pharmacyApi + '/op/sale/return/',
          DETAIL_SEARCH_API: pharmacyApi + '/op/sale/return/get/bill/search',
          ADD_BILL_API: pharmacyApi + '/op/sale/return/add/bill/',
          SAVE_API: pharmacyApi + '/op/sale/return/save'
        },
        SALES_WORKLIST: {
          DROPDOWN_API: pharmacyApi + '/op/sale/workList/dropdowns',
          SEARCH_API: pharmacyApi + '/op/sale/workList/search',
          DETAILS_API: pharmacyApi + '/op/sale/workList/',
          SUBSTITUTE_API: pharmacyApi + '/op/sale/workList/substitute/',
        },
        PHARMACOLOGICAL_VALIDATION: {
          SEARCH_API: pharmacyApi + '/ip/pharmacology/validation/search',
          ACTIVE_MEDICATION_API: pharmacyApi + '/ip/pharmacology/validation/',
          DETAIL_SEARCH_API: pharmacyApi + '/ip/pharmacology/validation/',
          SAVE_API: pharmacyApi + '/ip/pharmacology/validation/save'
        },
        ASSIGN_PICKER_DISPENSER: {
          SEARCH_API: pharmacyApi + '/ip/assign/picker/search',
          DETAIL_API: pharmacyApi + '/ip/assign/picker/',
          SUBSTITUTE_API: pharmacyApi + '/ip/assign/picker/substitute/',
          SAVE_API: pharmacyApi + '/ip/assign/picker/save'
        },
        PENDING_HANDOVER: {
          SEARCH_API: pharmacyApi + '/ip/pending/handover/worklist/search',
          DETAIL_API: pharmacyApi + '/ip/pending/handover/worklist/',
          SAVE_API: pharmacyApi + '/ip/pending/handover/worklist/save'
          
        },
        PATIENT_ISSUE_ACCEPTANCE: {
          MEDICATION_SEARCH_API: pharmacyApi + '/ip/pending/issues/acceptance/medication/search',
          MEDICATION_DROPDOWN_API: pharmacyApi + '/ip/pending/issues/acceptance/medication/dropdowns',
          MEDICATION_DETAIL_API: pharmacyApi + '/ip/pending/issues/acceptance/medication/',
          MEDICATION_SAVE_API: pharmacyApi + '/ip/pending/issues/acceptance/medication/save',
          CONSUMABLE_SEARCH_API: pharmacyApi + '/in/patient/consumable/issue/search',
          CONSUMABLE_DROPDOWN_API: pharmacyApi + '/in/patient/consumable/issue/dropdowns',
          CONSUMABLE_DETAIL_API: pharmacyApi + '/in/patient/consumable/issue/',
          CONSUMABLE_SAVE_API: pharmacyApi + '/in/patient/consumable/issue/save'
        },
        PATIENT_ISSUE_REJECTION: {
          MEDICATION_DROPDOWN_API: pharmacyApi + '/ip/patient/issues/Rejection/medication/dropdown',
          MEDICATION_SEARCH_API: pharmacyApi + '/ip/patient/issues/Rejection/medication/search',
          MEDICATION_DETAIL_API: pharmacyApi + '/ip/patient/issues/Rejection/medication/',
          MEDICATION_SAVE_API: pharmacyApi + '/ip/patient/issues/Rejection/medication/save',
          CONSUMABLE_SEARCH_API: pharmacyApi + '/ip/patient/issues/rejection/consumable/search',
          CONSUMABLE_DETAIL_API: pharmacyApi + '/ip/patient/issues/rejection/consumable/',
          CONSUMABLE_SAVE_API: pharmacyApi + '/ip/patient/issues/rejection/consumable/save'
        },
        PENDING_REJECTED_REJECTION: {
          MEDICATION_SEARCH_API: pharmacyApi + '/ip/pending/rejected/rejection/medication/search',
          CONSUMABLE_SEARCH_API: pharmacyApi + '/ip/pending/rejected/rejection/consumable/search',
          MEDICATION_DETAIL_API: pharmacyApi + '/ip/pending/rejected/rejection/medication/',
          CONSUMABLE_DETAIL_API: pharmacyApi + '/ip/pending/rejected/rejection/consumable/',
          MEDICATION_SAVE_API: pharmacyApi + '/ip/patient/Rejecteds/Rejection/medication/save',
          CONSUMABLE_SAVE_API: pharmacyApi + '/ip/pending/rejected/rejection/consumable/save'
        },
        PATIENT_INDENT: {
          SEARCH_API: pharmacyApi + '/nursing/pharmacy/patient/indent/consumable/search',
          PATIENT_SEARCH_API: pharmacyApi + '/nursing/pharmacy/patient/indent/consumable/patient/search',
          DROPDOWN_API: pharmacyApi + '/nursing/pharmacy/patient/indent/consumable/dropdowns',
          SAVE_API: pharmacyApi + '/nursing/pharmacy/patient/indent/consumable/save',
          DETAIL_API: pharmacyApi + '/nursing/pharmacy/patient/indent/consumable/',
          UPDATE_API: pharmacyApi + '/nursing/pharmacy/patient/indent/consumable/',
          APPROVE_API: pharmacyApi + '/nursing/pharmacy/patient/indent/consumable/approve',
          CANCEL_API: pharmacyApi + '/nursing/pharmacy/patient/indent/consumable/cancel/',
        },
        PATIENT_CONSUMABLE_ISSUE: {
          SEARCH_API: pharmacyApi + '/in/patient/consumable/issue/search',
          DETAIL_API: pharmacyApi + '/in/patient/consumable/issue/',
          SAVE_API: pharmacyApi + '/in/patient/consumable/issue/save',
          ISSUE_SEARCH_API: pharmacyApi + '/in/patient/consumable/issue/list/search',
          ISSUE_DETAIL_API: pharmacyApi + '/in/patient/consumable/issue/list1/',
          CANCEL_API: pharmacyApi + '/nursing/pharmacy/patient/indent/consumable/cancel/',
        }
      },

      IP: {
        PENDING_ISSUES: {
          SEARCH_API: pharmacyApi + '/ip/pending/issues/search',
          DETAIL_API: pharmacyApi + '/ip/pending/issues/',
          SAVE_API: pharmacyApi + '/ip/pending/issues/save'
        },
        PATIENT_RETURN_ACCEPTANCE: {
          MEDICATION_SEARCH_API: pharmacyApi + '/ip/patient/return/acceptance/medication/search',
          MEDICATION_DETAIL_API: pharmacyApi + '/ip/patient/return/acceptance/medication/',
          MEDICATION_SAVE_API: pharmacyApi + '/ip/patient/return/acceptance/medication/save',
          CONSUMABLE_SEARCH_API: pharmacyApi + '/ip/patient/return/acceptance/consumable/search',
          CONSUMABLE_DETAIL_API: pharmacyApi + '/ip/patient/return/acceptance/consumable/',
          CONSUMABLE_SAVE_API: pharmacyApi + '/ip/patient/return/acceptance/consumable/save'
        }
      },

      NURSING: {
        PATIENT_ISSUE_RETURN: {
          ITEM_AUTOPOPULATE_API: pharmacyApi + '/nursing/workstation/patient/issue/return/medication/item/search/',
          MEDICATION_DROPDOWN_API: pharmacyApi + '/nursing/workstation/patient/issue/return/medication/dropdown',
          MEDICATION_SEARCH_API: pharmacyApi + '/nursing/workstation/patient/issue/return/medication/search',
          MEDICATION_ISSUE_SEARCH_API: pharmacyApi + '/nursing/workstation/patient/issue/return/medication/againstIssue/search',
          MEDICATION_ITEM_SEARCH_API: pharmacyApi + '/nursing/workstation/patient/issue/return/medication/againstItem/search',
          MEDICATION_ADD_ITEMS_API: pharmacyApi + '/nursing/workstation/patient/issue/return/medication/add/new',
          MEDICATION_SAVE_API: pharmacyApi + '/nursing/workstation/patient/issue/return/medication/save',
          MEDICATION_DETAIL_API: pharmacyApi + '/nursing/workstation/patient/issue/return/medication1/',
          CONSUMABLE_SEARCH_API: pharmacyApi + '/nursing/workstation/patient/issue/return/consumable/search',
          CONSUMABLE_ISSUE_SEARCH_API: pharmacyApi + '/nursing/workstation/patient/issue/return/consumable/againstIssue/search',
          CONSUMABLE_ITEM_SEARCH_API: pharmacyApi + '/nursing/workstation/patient/issue/return/consumable/againstItem/search',
          CONSUMABLE_ADD_ITEMS_API: pharmacyApi + '/nursing/workstation/patient/issue/return/consumable/add/new',
          CONSUMABLE_SAVE_API: pharmacyApi + '/nursing/workstation/patient/issue/return/consumable/save',
          CONSUMABLE_DETAIL_API: pharmacyApi + '',
        },
        RETURN_EMPTY_AMPOULES: {
          SEARCH_API: pharmacyApi + '/nursing/narcoticsStore/returnEmptyAmpoules/search',
          DETAIL_API: pharmacyApi + '/nursing/narcoticsStore/returnEmptyAmpoules/',
          SAVE_API: pharmacyApi + '/nursing/narcoticsStore/returnEmptyAmpoules/save'
        },
        BILLABLE_CONSUMPTION: {
          SEARCH_API: pharmacyApi + '/nursing/pharmacy/billable/Consumption/search'
        }
        ,
        RETURN_BILLABLE_CONSUMPTION:{
          SEARCH_API: pharmacyApi + '/nursing/pharmacy/billable/consumption/search',
          SEARCH_NEW_API: pharmacyApi + '/nursing/pharmacy/return/billable/consumptions/search/new',
          ADD_NEW_API: pharmacyApi + '/nursing/pharmacy/return/billable/consumption/add/new'
        }
      },

      NARCOTICS: {
        NARCOTICS_ISSUE_WORKLIST: {
          DROPDOWN_API: pharmacyApi + '/narcotics/issue/worklist/dropdowns',
          SEARCH_API: pharmacyApi + '/narcotics/issue/worklist/search',
          DETAIL_API: pharmacyApi + '/narcotics/issue/worklist/',
          SAVE_API: pharmacyApi + '/narcotics/issue/worklist/save'
        },
        DELIVERY_WORKLIST: {
          SEARCH_API: pharmacyApi + '/narcotics/delivery/worklist/search',
          DETAIL_API: pharmacyApi + '/narcotics/delivery/worklist/',
          SAVE_API: pharmacyApi + '/narcotics/delivery/worklist/save'
        },
        ACCEPTANCE_EMPTY_AMPOULES: {
          SEARCH_API: pharmacyApi + '/pharmacy/narcotics/acceptanceEmptyAmpoules/search',
          DETAIL_API: pharmacyApi + '/pharmacy/narcotics/acceptanceEmptyAmpoules/',
          SAVE_API: pharmacyApi + '/pharmacy/narcotics/acceptanceEmptyAmpoules/save'
        }
      }
    },

    INVENTORY: {
      STORE: {
        STORE_INDENT: {
          DROPDOWN_API: inventoryApi + '/stores/indents/dropdowns',
          SEARCH_API: inventoryApi + '/stores/indents/search',
          APPROVE_API: inventoryApi + '/stores/indents/approve',
          CANCEL_API: inventoryApi + '/stores/indents/cancel/',
          DETAILS_API: inventoryApi + '/stores/indents/',
          SAVE_API: inventoryApi + '/stores/indents',
          UPDATE_API: inventoryApi + '/stores/indents/',
        },
        STORE_INDENT_APPROVAL: {
          DROPDOWN_API: inventoryApi + '/stores/indents/approval/dropdowns',
          SEARCH_API: inventoryApi + '/stores/indents/approval/search',
          APPROVE_API: inventoryApi + '/stores/indents/approval/approve/',
          CANCEL_API: inventoryApi + '/stores/indents/approval/cancel/',
          DETAILS_API: inventoryApi + '/stores/indents/approval/',
        },
        PENDING_STORE_INDENT: {
          DROPDOWN_API: inventoryApi + '/stores/indents/pending/dropdowns',
          SEARCH_API: inventoryApi + '/stores/indents/pending/search',
          CANCEL_API: inventoryApi + '/stores/indents/pending/close/',
          DETAILS_API: inventoryApi + ' /stores/indents/pending/',

        },
        STORE_STOCK_DASHBOARD:
          {
            SEARCH_API: inventoryApi + '/stores/stock/dashboard/search',
            BATCH_API: inventoryApi + '/stores/stock/dashboard/batch/'
          },
        MATERIAL_RETURN:
          {
            SEARCH_API: inventoryApi + '/stores/material/return/search',
            SAVE_API: inventoryApi + '/stores/material/return',
            DETAILS_API: inventoryApi + '/stores/material/return/',
            DROPDOWN_API: inventoryApi + '/stores/material/return/dropdowns',
            RETURN_REASON_API: inventoryApi + '/stores/material/return//dropdowns/return/reason',
            APPROVE_API: inventoryApi + '/stores/material/return/approve',
            ADD_NEW_DROPDOWN_API: inventoryApi + '/stores/material/return/carrier/dropdowns',
            UPDATE_API: inventoryApi + '/stores/material/return/',
            CANCEL_API: inventoryApi + '/stores/material/return/cancel/'
          },
        MATERIAL_RETURN_ACCEPTANCE:
          {
            DROPDOWN_API: inventoryApi + '/stores/material/return/acceptance/dropdowns',
            SEARCH_API: inventoryApi + '/stores/material/return/acceptance/search',
            APPROVE_API: inventoryApi + '/stores/material/return/acceptance/',
            DETAIL_API: inventoryApi + '/stores/material/return/acceptance/'
          },
        MATERIAL_PICKER:
          {
            SEARCH_API: inventoryApi + '/stores/material/picker/search',
            DROPDOWN_API: inventoryApi + '/stores/material/picker/dropdowns',
            DETAILS_API: inventoryApi + '/stores/material/picker/',
            SAVE_API: inventoryApi + '/stores/material/picker'
          },
        MATERIAL_ISSUE:
          {
            MATERIAL_PENDING_ISSUE:
              {
                DROPDOWN_API: inventoryApi + '/stores/material/issue/pending/dropdowns',
                SEARCH_API: inventoryApi + '/stores/material/issue/pending/search',
                DETAILS_API: inventoryApi + '/stores/material/issue/pending/',
                SAVE_API: inventoryApi + '/stores/material/issue/pending'
              },
            MATERIAL_ISSUE_LIST:
              {
                DROPDOWN_API: inventoryApi + '/stores/material/issue/list/dropdowns',
                SEARCH_API: inventoryApi + '/stores/material/issue/list/search',
                DETAILS_API: inventoryApi + '/stores/material/issue/list/',
                SAVE_API: inventoryApi + '/stores/material/issue/list',
                DIRECT_ISSUE_DROPDOWN: inventoryApi + '/stores/material/issue/list/direct/issue/dropdowns',
              }
          },
        MATERIAL_ISSUE_ACCEPTANCE:
          {
            DROPDOWN_API: inventoryApi + '/stores/material/issue/acceptance/dropdowns',
            SEARCH_API: inventoryApi + '/stores/material/issue/acceptance/search',
            DETAILS_API: inventoryApi + '/stores/material/issue/acceptance/',
            SAVE_API: inventoryApi + '/stores/material/issue/acceptance/',
          },
        REJECTED_MATERIAL_ACCEPTANCE:
          {
            DROPDOWN_API: inventoryApi + '/stores/reject/material/acceptance/dropdowns',
            SEARCH_API: inventoryApi + '/stores/reject/material/acceptance/search',
            DETAILS_API: inventoryApi + '/stores/reject/material/acceptance/',
            SAVE_API: inventoryApi + '/stores/reject/material/acceptance/'
          },
        BATCH_CHANGE:
          {
            SEARCH_API: inventoryApi + '/stores/batch/change/search',
            APPROVE_API: inventoryApi + '/stores/batch/change/submit',
            CANCEL_API: inventoryApi + '/stores/batch/change/cancel/',
            DETAILS_API: inventoryApi + '/stores/batch/change/',
            SAVE_API: inventoryApi + '/stores/batch/change',
            UPDATE_API: inventoryApi + '/stores/batch/change/',
            CANCEL_API: inventoryApi + '/stores/batch/change/cancel/'
          },
        BATCH_CHANGE_APPROVAL:
          {
            SEARCH_API: inventoryApi + '/stores/batch/change/approval/search',
            APPROVE_API: inventoryApi + '/stores/batch/change/approval/approve/',
            DETAILS_API: inventoryApi + '/stores/batch/change/approval/',
            BATCH_APPROVE_API: inventoryApi + '/stores/batch/change/approval/approve/'
          },
        PENDING_SHELF_ALLOCATION:
          {
            SEARCH_API: inventoryApi + '/stores/shelf/allocation/search',
            STATUS_UPDATE_API: inventoryApi + '/stores/shelf/allocation/status/',
            ALLOCATED_ITEMS: inventoryApi + '/stores/shelf/allocation/',
            UPDATE_API: inventoryApi + '/stores/shelf/allocation/'
          },
        RETURNABLE_GATEPASS:
          {
            DROPDOWN_API: inventoryApi + '/stores/returnable/gatepass/dropdowns',
            SEARCH_API: inventoryApi + '/stores/returnable/gatepass/search',
            APPROVE_API: inventoryApi + '/stores/returnable/gatepass/submit',
            CANCEL_API: inventoryApi + '/stores/returnable/gatepass/cancel/',
            SAVE_API: inventoryApi + '/stores/returnable/gatepass/',
            DETAIL_API: inventoryApi + '/stores/returnable/gatepass/',
            UPDATE_API: inventoryApi + '/stores/returnable/gatepass/'
          },
        RETURNABLE_GATEPASS_APPROVAL:
          {
            DROPDOWN_API: inventoryApi + '/stores/returnable/gatepass/approval/dropdowns',
            SEARCH_API: inventoryApi + '/stores/returnable/gatepass/approval/search',
            APPROVE_API: inventoryApi + '/stores/returnable/gatepass/approval/approve/',
            CANCEL_API: inventoryApi + '/stores/returnable/gatepass/approval/cancel/',
            DETAIL_API: inventoryApi + '/stores/returnable/gatepass/approval/'
          }
      },
      NURSING: {
        NON_BILLABLE_CONSUMPTION:
          {
            SEARCH_API: inventoryApi + '/nursing/non/billable/consumption/search',
            DETAIL_API: inventoryApi + '/nursing/non/billable/consumption/',
            SAVE_API: inventoryApi + '/nursing/non/billable/consumption',
            UPDATE_API: inventoryApi + '/nursing/non/billable/consumption/',
            APPROVE_API: inventoryApi + '/nursing/non/billable/consumption/submit',
            CANCEL_API: inventoryApi + '/nursing/non/billable/consumption/cancel/',
            DROPDOWN_API: inventoryApi + '/nursing/non/billable/consumption/dropdowns'
          },
        CONSUMABLE_ACCEPTANCE:
          {
            SEARCH_API: inventoryApi + '/nursing/consumable/acceptance/search',
            DETAIL_API: inventoryApi + '/nursing/consumable/acceptance/',
            APPROVE_API: inventoryApi + '/nursing/consumable/acceptance/approve/',
            DETAIL_APPROVE_API: inventoryApi + '/nursing/consumable/acceptance/approve/',
            CANCEL_API: inventoryApi + '/nursing/consumable/acceptance/cancel/',
            DROPDOWN_API: inventoryApi + '/nursing/consumable/acceptance/dropdowns'
          }
      },
      STOCK: {
        OPENING_BALANCE: {
          SEARCH_API: inventoryApi + '/stock/opening/balances/search',
          DETAILS_API: inventoryApi + '/stock/opening/balances/',
          APPROVE_API: inventoryApi + '/stock/opening/balances/approve/',
          CANCEL_API: inventoryApi + '/stock/opening/balances/cancel/',
          UPDATE_API: inventoryApi + '/stock/opening/balances/',
          SAVE_API: inventoryApi + '/stock/opening/balances',
        }
      },
      SCRAP: {
        SCRAP_REQUEST: {
          DROPDOWN_API: inventoryApi + '/scraps/scrap/request/dropdowns',
          APPROVE_API: inventoryApi + '/scraps/scrap/request/approve',
          SEARCH_API: inventoryApi + '/scraps/scrap/request/search',
          DETAIL_API: inventoryApi + '/scraps/scrap/request/',
          UPDATE_API: inventoryApi + '/scraps/scrap/request/',
          CANCEL_API: inventoryApi + '/scraps/scrap/request/cancel/',
          SAVE_API: inventoryApi + '/scraps/scrap/request',
          SCRAP_REASON_API : globalApi + '/scrap/reasons'
        },
        SCRAP_REQUEST_APPROVAL:
          {
            SEARCH_API: inventoryApi + '/scraps/scrap/request/approval/search',
            DETAIL_API: inventoryApi + '/scraps/scrap/request/approval/',
            DROPDOWN_API: inventoryApi + '/scraps/scrap/request/approval/dropdowns',
            APPROVE_API: inventoryApi + '/scraps/scrap/request/approval/'
          },
        REQUEST_FOR_ASSET_DISPOSAL:
          {
            DROPDOWN_API: inventoryApi + '/scrap/request/asset/disposal/worklist/dropdowns',
            SEARCH_API: inventoryApi + '/scrap/request/asset/disposal/worklist/search',
            SAVE_API: inventoryApi + '/scrap/request/asset/disposal/worklist',
            DETAIL_API: inventoryApi + '/scrap/request/asset/disposal/worklist/'
          },
        WASTE_ASSET_DISPOSAL: {
          DROPDOWN_API: inventoryApi + '/scrap/waste/asset/disposal/worklist/dropdowns',
          SEARCH_API: inventoryApi + '/scrap/waste/asset/disposal/worklist/search',
          SAVE_API: inventoryApi + '/scrap/waste/asset/disposal/worklist',
          DETAIL_API: inventoryApi + '/scrap/waste/asset/disposal/worklist/'
        },
        WITNESS_APPROVAL: {
          DROPDOWN_API: inventoryApi + 'scrap/witness/approval/dropdowns',
          SEARCH_API: inventoryApi + '/scrap/witness/approval/search',
          SAVE_API: inventoryApi + '/scrap/witness/approval/',
          DETAIL_API: inventoryApi + '/scrap/witness/approval/'
        }
      }
    },

    PROCUREMENT: {
      QUOTATION: {
        GET_BATCHES_API: procurementApi + '/quotation/barcode/batches',
        PURCHSE_ORDER: {
          DROPDOWN_API: procurementApi + '/purchase/order/creation/dropdowns',
          GET_PO_API: procurementApi + '/purchase/order/creation/search',
          APPROVE_PO_API: procurementApi + '/purchase/order/creation/approve',
          CANCEL_PO_API: procurementApi + '/purchase/order/creation/cancel/',
          SAVE_API: procurementApi + '/purchase/order/creation/',


        },
        PURCHSE_ORDER_APPROVAL: {
          DROPDOWN_API: procurementApi + '/purchase/order/approval/dropdowns',
          SEARCH_API: procurementApi + '/purchase/order/approval/search',
          APPROVE_API: procurementApi + '/purchase/order/approval/approve',
          DETAILS_API: procurementApi + '/purchase/order/approval/',
          SAVE_API: procurementApi + '/purchase/order/approval/approve/'
        },
        GRN: {
          GRN_DROPDOWN_API: procurementApi + '/quotation/grn/dropdowns',
          SEARCH_GRN_API: procurementApi + '/quotation/grn/search',
          APPROVE_GRN_API: procurementApi + '/quotation/grn/approve',
          CANCEL_GRN_API: procurementApi + '/quotation/grn/cancel/',
          DROPDOWN_API: procurementApi + '/purchase/order/creation/dropdowns',
          GET_GRN_API: procurementApi + '/quotation/grn/',
          SAVE_GRN_API: procurementApi + '/quotation/grn/'
        },
        GRN_APPROVAL: {
          DROPDOWN_API: procurementApi + '/quotation/grn/approval/dropdowns',
          SEARCH_API: procurementApi + '/quotation/grn/approval/search',
          APPROVE_API: procurementApi + '/quotation/grn/approval/approve/',
          DETAILS_API: procurementApi + '/quotation/grn/approval/'
        },
        PHYSICAL_INSPECTION: {
          DROPDOWN_API: procurementApi + '/quotation/physical/inspection/dropdown',
          SEARCH_API: procurementApi + '/quotation/physical/inspection/search',
          DETAILS_API: procurementApi + '/quotation/physical/inspection/',
          SAVE_API: procurementApi + '/quotation/physical/inspection/save'
        },
        QUOTATION_ENQUIRY: {
          DROPDOWN_API: procurementApi + '/quotation/enquiry/dropdowns',
          SEARCH_API: procurementApi + '/quotation/enquiry/search',
          SAVE_API: procurementApi + '/quotation/enquiry/',
          DETAILS_API: procurementApi + '/quotation/enquiry/',
          APPROVE_QUOTATION_API: procurementApi + '/quotation/enquiry/approve',
          CANCEL_QUOTATION_API: procurementApi + '/quotation/enquiry/cancel/',
          SEND_FOR_TECHNICAL_APPROVAL: procurementApi + '/quotation/technical/approval'
        },
        ENQUIRY_SENT_TO_VENDOR: {
          DROPDOWN_API: procurementApi + '/quotation/enquiry/sentToVendor/dropdowns',
          SEARCH_API: procurementApi + '/quotation/enquiry/sentToVendor/search',
          SAVE_API: procurementApi + '/quotation/enquiry/sentToVendor/',
          APPROVE_QUOTATION_API: procurementApi + '/quotation/enquiry/sentToVendor/approve/',
          CANCEL_QUOTATION_API: procurementApi + '/quotation/enquiry/sentToVendor/cancel/',
          SEARCH_QR_API: procurementApi + '/quotation/enquiry/sentToVendor/qr/search/',
          DETAILS_QR_ITEMS: procurementApi + '/quotation/enquiry/sentToVendor/qr/details'
        },
        QUOTATION_FILL_IN: {
          DROPDOWN_API: procurementApi + '/quotation/fill/dropdowns',
          SEARCH_API: procurementApi + '/quotation/fill/search',
          SEARCH_VQR_API: procurementApi + '/quotation/fill/vendor/search',
          DETAILS_API: procurementApi + '/quotation/fill/',
          DETAILS_VQR_ITEMS: procurementApi + '/quotation/fill/vendor/details',
          SAVE_API: procurementApi + '/quotation/fill/',
          APPROVE_API: procurementApi + '/quotation/fill/approve',
          CANCEL_API: procurementApi + '/quotation/fill/cancel/'
        },
        TECHNICAL_APPROVAL: {
          DROPDOWN_API: procurementApi + '/quotation/technical/approval/dropdowns',
          SEARCH_API: procurementApi + '/quotation/technical/approval/search',
          DETAILS_API: procurementApi + '/quotation/technical/approval/',
          SAVE_API: procurementApi + '/quotation/technical/approval/'
        },
        VENDOR_NEGOTIATION: {
          DROPDOWN_API: procurementApi + '/quotation/vendor/negotiation/dropdowns',
          SEARCH_API: procurementApi + '/quotation/vendor/negotiation/search',
          DETAILS_API: procurementApi + '/quotation/vendor/negotiation/'
        }
      },
      PURCHASE: {
        ITEM_REQUEST: {
          DROPDOWN_API: procurementApi + '/purchase/item/request/dropdowns',
          SEARCH_API: procurementApi + '/purchase/item/request/search',
          DETAILS_API: procurementApi + '/purchase/item/request/',
          SAVE_API: procurementApi + '/purchase/item/request/',
          CANCEL_API: procurementApi + '/purchase/item/request/cancel/',
          APPROVE_API: procurementApi + '/purchase/item/request/approve/'
        },
        ITEM_APPROVAL: {
          DROPDOWN_API: procurementApi + '/purchase/item/request/approval/dropdowns',
          SEARCH_API: procurementApi + '/purchase/item/request/approval/search',
          DETAILS_API: procurementApi + '/purchase/item/request/approval/',
          APPROVE_API: procurementApi + '/purchase/item/request/approval/approve',
          CANCEL_API: procurementApi + '/purchase/item/request/approval/cancel/',
          SAVE_API: procurementApi + '/purchase/item/request/approval/approve/'
        },
        ITEM_CREATION: {
          DROPDOWN_API: procurementApi + '/purchase/item/creation/pending/dropdowns',
          SEARCH_API: procurementApi + '/purchase/item/creation/pending/search',
          DETAILS_API: procurementApi + '/purchase/item/creation/pending/'
        },
        PURCHASE_REQUEST: {
          DROPDOWN_API: procurementApi + '/purchase/requests/dropdowns',
          SEARCH_API: procurementApi + '/purchase/requests/search',
          SAVE_API: procurementApi + '/purchase/requests/',
          APPROVE_API: procurementApi + '/purchase/requests/approve',
          CANCEL_API: procurementApi + '/purchase/requests/cancel/'
        },
        PURCHASE_REQUEST_APPROVAL: {
          DROPDOWN_API: procurementApi + '/purchase/requests/approval/dropdowns',
          SEARCH_API: procurementApi + '/purchase/requests/approval/search',
          APPROVE_API: procurementApi + '/purchase/requests/approval/approve',
          CANCEL_API: procurementApi + '/purchase/requests/approval/cancel/',
          SAVE_API: procurementApi + '/purchase/requests/approval/'
        },
        LOCAL_PURCHASE_REQUEST: {
          DROPDOWN_API: procurementApi + '/local/purchase/requests/dropdowns',
          SEARCH_API: procurementApi + '/local/purchase/requests/search',
          SAVE_API: procurementApi + '/local/purchase/requests/',
          APPROVE_API: procurementApi + '/local/purchase/requests/approve',
          CANCEL_API: procurementApi + '/local/purchase/requests/cancel/'
        },
        LOCAL_PURCHASE_APPROVAL: {
          DROPDOWN_API: procurementApi + '/local/purchase/requests/approval/dropdowns',
          SEARCH_API: procurementApi + '/local/purchase/requests/approval/search',
          CANCEL_API: procurementApi + '/local/purchase/requests/approval/cancel/',
          DETAILS_API: procurementApi + '/local/purchase/requests/approval/',
          SAVE_API: procurementApi + '/local/purchase/requests/approval/approve/'
        },
        GRN_AGAINST_PO: {
          DROPDOWN_API: procurementApi + '/purchase/grn/against/po/dropdowns',
          SEARCH_API: procurementApi + '/purchase/grn/against/po/search',
          DETAILS_API: procurementApi + '/purchase/grn/against/po/',
          CANCEL_API: procurementApi + '/purchase/grn/against/po/cancel/'
        }
      }
    }
  };
}());   
