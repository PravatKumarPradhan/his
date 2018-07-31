package com.param.adt.admission.dao;


import com.param.adt.admission.dto.BedAllocationDto;
import com.param.adt.admission.model.AdmissionDetails;
import com.param.adt.common.Response;
import com.param.adt.master.global.dto.BedCategoryMasterDto;
import com.param.adt.master.global.dto.BedMasterDto;
import com.param.adt.master.unit.dto.BillingBedCategoryMasterDto;
import com.param.adt.master.unit.dto.MealPreferenceMasterDto;
import com.param.adt.transfer.dto.TransferDto;
import com.param.global.dto.AdmissionDto;
import com.param.global.dto.KinDetailsDto;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IBedAllocationDao extends IGenericDao<AdmissionDetails, Integer>
{
	
	Response getActiveBedList(BedMasterDto bedMasterDto) throws ApplicationException;
	
	Response getActiveFloorMasterList(BedAllocationDto bedAllocationDto) throws ApplicationException;
	
	Response getWardListByFloorId(BedAllocationDto bedAllocationDto) throws ApplicationException;
	
	Response getNursingStationListByWard(BedAllocationDto bedAllocationDto)throws ApplicationException;
	
	Response getBedCategoryList(BedCategoryMasterDto bedCategoryMasterDto)throws ApplicationException;
	
	Response getBedStatusList() throws ApplicationException;

	Response getMealPreference(MealPreferenceMasterDto mealPreferenceMasterDto)throws ApplicationException;

	Response savePatientAdmission(AdmissionDto admissionDto)throws ApplicationException;

	Response savePatientDetails(AdmissionDto admissionDto)throws ApplicationException;
	
	Response getBedListByMultipleCriteria(BedMasterDto bedMasterDto)throws ApplicationException;

	Response saveTransfer(TransferDto transferDto) throws ApplicationException;

	Response updateWatingListNumber(AdmissionDto admissionDto)throws ApplicationException;
	
	Response getWatingListNumbers(AdmissionDto admissionDto)throws ApplicationException;
	
}
