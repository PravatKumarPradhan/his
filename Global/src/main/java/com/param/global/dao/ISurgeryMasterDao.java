package com.param.global.dao;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.global.dto.SurgeryMasterDto;
import com.param.global.model.SurgeryMaster;

@SuppressWarnings("rawtypes")
public interface ISurgeryMasterDao extends IGenericDao<SurgeryMaster, Integer> {

	public Response getListOfSurgeryMaster(SurgeryMasterDto surgeryMasterDto) throws ApplicationException;

	public Response getSurgeryMasterList(SurgeryMasterDto surgeryMasterDto) throws ApplicationException;

	public Response saveSurgeryMaster(SurgeryMasterDto surgeryMasterDto) throws ApplicationException;

	public Response getSurgeryMasterByName(SurgeryMasterDto surgeryMasterDto) throws ApplicationException;

	public Response getSurgeryMasterById(SurgeryMasterDto surgeryMasterDto) throws ApplicationException;

	public Response updateSurgeryMaster(SurgeryMasterDto surgeryMasterDto) throws ApplicationException;

	public Response updateStatusForSurgeryMaster(SurgeryMasterDto surgeryMasterDto) throws ApplicationException;

}
