package com.param.global.opd.dao;

import java.util.List;

import com.param.entity.model.master.Tax;
import com.param.global.common.Response;
import com.param.global.dto.OrderDetailsMasterDto;
import com.param.global.dto.SlotMasterDto;
import com.param.global.dto.UnitServiceTariffMasterDto;
import com.param.opd.coversheet.model.EncounterMaster;
import com.param.opd.encounter.dto.AppointmentStatusMasterDto;
import com.param.opd.encounter.dto.EncounterMasterDto;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IEncounterMasterDao extends IGenericDao<EncounterMaster, Integer>{
	public Response saveEncounterMaster(EncounterMasterDto encounterMasterDto)throws ApplicationException;
	
	public Response getEncounterDetailsById(Integer encounterId)throws ApplicationException;
	
	public Long getNextValEncounterMasterId() throws ApplicationException;
	
	public List<EncounterMasterDto> getEncounterDetailsByPatientDoctorVisitTypeId(EncounterMasterDto encounterMasterDto)throws ApplicationException;

	public Response getEncounterDetails(EncounterMasterDto encounterMasterDto)throws ApplicationException;
	
	/*	ADDED BY JYOTI
	 * 	DATE 03-05-2018
	 * */
	public Response getListOfEncounterMasterDao(SlotMasterDto slotMasterDto)throws ApplicationException;
	public Response getSearchedListEncounterMaster(SlotMasterDto slotMasterDto)throws ApplicationException;
	
	public Response getListOfAppointmentStatus(AppointmentStatusMasterDto appointmentStatusMasterDto)throws ApplicationException;
	public Response getEncounterByIdForSync(int encounterId)throws ApplicationException;

	public Response getListOfEncountersByPatientId(EncounterMasterDto encounterMasterDto)throws ApplicationException;

	public UnitServiceTariffMasterDto getBasePriceByServiceTariffMaster(
			UnitServiceTariffMasterDto unitServiceTariffMasterDto)throws ApplicationException;

	public Tax getTaxPercentageByServiceId(Integer serviceId)throws ApplicationException;

	public Response getAutoRenderedServicesByPatient(EncounterMasterDto encounterMasterDto)throws ApplicationException;

	public Response updateAutorenderServices(OrderDetailsMasterDto orderDetailsMasterDto)throws ApplicationException;
}
