package com.param.lis.global.dao;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
import com.param.entity.lis.global.SpecimanMaster;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.ReportTypeMasterDto;
import com.param.lis.global.dto.SpecimanMasterDto;

@SuppressWarnings("rawtypes")
public interface ISpecimanMasterDao extends IGenericDao<SpecimanMaster, Integer>{
	
	public Response addSpecimanMaster(SpecimanMasterDto specimanMasterDto)throws ApplicationException;
	public Response getSpecimanMasteById(Integer orgId, Integer specimanId) throws ApplicationException;
	public Response updateSpecimanMaster(SpecimanMasterDto specimanMasterDto) throws ApplicationException;
	public Response updateCheckReportTypeCodeAvaiable(SpecimanMasterDto specimanMasterDto) throws ApplicationException;
	public Response ActivateInactivateSpecimanMaster(Integer orgId, Integer specimanId, Character specimanStatus) throws ApplicationException;
	public Response listSpecimanMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;
	public Response checkSpecimanMaster(SpecimanMasterDto specimanMasterDto) throws ApplicationException;
	public Response getToTalSpecimanMaster(Integer orgId) throws ApplicationException;
	public Response specimanGrossRequriedInSpecimanMaster(Integer orgId, Integer specimanId, Character specimanGross) throws ApplicationException;
	public Response specimanBlockRequriedInSpecimanMaster(Integer orgId, Integer specimanId, Character specimanBlock) throws ApplicationException;
}
