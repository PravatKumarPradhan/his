package com.param.adt.admission.service;

import javax.transaction.Transactional;

import com.param.adt.admission.dto.BedAllocationDto;
import com.param.adt.common.Response;
import com.param.adt.master.global.dto.BedCategoryMasterDto;
import com.param.adt.master.global.dto.BedMasterDto;
import com.param.adt.master.unit.dto.MealPreferenceMasterDto;
import com.param.global.dto.AdmissionDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IBedAllocationService 
{
	
	@Transactional
	Response getActiveBedList(BedMasterDto bedMasterDto) throws ApplicationException;
	
	@Transactional
	Response getActiveFloorMasterList(BedAllocationDto bedAllocationDto) throws ApplicationException;
	
	@Transactional
	Response getWardListByFloorId(BedAllocationDto bedAllocationDto) throws ApplicationException;
	
	@Transactional
	Response getNursingStationListByWard(BedAllocationDto bedAllocationDto)throws ApplicationException;
	
	@Transactional
	Response getBedCategoryList(BedCategoryMasterDto bedCategoryMasterDto)throws ApplicationException;
	
	@Transactional
	Response getBedStatusList() throws ApplicationException;
	
	@Transactional
	Response getMealPreference(MealPreferenceMasterDto mealPreferenceMasterDto) throws ApplicationException;
	
	@Transactional
	Response savePatientAdmission(AdmissionDto admission)throws ApplicationException;

	@Transactional
	Response savePatientDetails(AdmissionDto admission) throws ApplicationException;

	@Transactional
	Response getBedListByMultipleCriteria(BedMasterDto bedMasterDto)throws ApplicationException;

	@Transactional
	Response saveTransfer(AdmissionDto admission)throws ApplicationException;

	@Transactional
	Response updateWatingListNumber(AdmissionDto admissionDto)throws ApplicationException;
}
