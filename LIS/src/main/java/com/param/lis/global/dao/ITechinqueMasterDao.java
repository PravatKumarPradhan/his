package com.param.lis.global.dao;

import com.param.entity.lis.global.TechniqueMaster;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.TechniqueMasterDto;
import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface ITechinqueMasterDao extends IGenericDao<TechniqueMaster, Integer>
{
	public Response addTechinqueMaster(TechniqueMasterDto techniqueMasterDto) throws ApplicationException;

	public Response getTechinqueMasterById(Integer orgId, Integer TechinqueId) throws ApplicationException;

	public Response updateTechinqueMaster(TechniqueMasterDto techniqueMasterDto) throws ApplicationException;

	public Response ActivateInactivateTechinqueMaster(Integer orgId, Integer TechinqueId, Character TechinqueStatus)
			throws ApplicationException;

	public Response listTechinqueMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;

	public Response getToTalTechinqueMasterRecord(Integer orgId) throws ApplicationException;

}
