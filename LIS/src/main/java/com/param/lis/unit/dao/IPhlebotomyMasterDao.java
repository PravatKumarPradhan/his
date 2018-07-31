package com.param.lis.unit.dao;


import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
import com.param.entity.lis.unit.PhlebotomyMaster;
import com.param.lis.global.common.Response;
import com.param.lis.unit.dto.PhlebotomyMasterDto;

@SuppressWarnings("rawtypes")
public interface IPhlebotomyMasterDao extends IGenericDao<PhlebotomyMaster, Integer>
{
	public Response addPhlebotomyMaster(PhlebotomyMasterDto phlebotomyMasterDto) throws ApplicationException;
	public Response checkPhlebotomyCodeAvaiable(PhlebotomyMasterDto phlebotomyMasterDto) throws ApplicationException;
	public Response updateCheckPhlebotomyCodeAvaiable(PhlebotomyMasterDto phlebotomyMasterDto) throws ApplicationException;
	public Response getPhlebotomyMasteById(Integer orgId,Integer unitId,Integer phlebotomyId) throws ApplicationException;
	public Response updatePhlebotomyMaster(PhlebotomyMasterDto phlebotomyMasterDto) throws ApplicationException;
	public Response ActivateInactivatePhlebotomyMaster(Integer orgId, Integer phlebotomyId, Character phlebotomyStatus)
			throws ApplicationException;
	public Response listPhlebotomyMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;

	public Response getToTalPhlebotomyMasterRecord(Integer orgId) throws ApplicationException;

}
