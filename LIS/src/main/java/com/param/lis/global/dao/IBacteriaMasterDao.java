package com.param.lis.global.dao;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
import com.param.entity.lis.global.BacteriaMaster;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.BacteriaMasterDto;
import com.param.lis.global.dto.BlockMasterDto;

@SuppressWarnings("rawtypes")
public interface IBacteriaMasterDao extends IGenericDao<BacteriaMaster, Integer>{
	
	public Response addBacteriaMaster(BacteriaMasterDto bacteriaMasterDto)throws ApplicationException;
	public Response getBacteriaMasterById(Integer orgId, Integer bacteriaId) throws ApplicationException;
	public Response updateBacteriaMaster(BacteriaMasterDto BacteriaMasterDto) throws ApplicationException;
	public Response updateCheckBacteriaCodeAvaiable(BacteriaMasterDto bacteriaMasterDto) throws ApplicationException;
	public Response ActivateInactivateBacteriaMaster(Integer orgId, Integer bacteriaId, Character bacteriaStatus) throws ApplicationException;
	public Response listBacteriaMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;
	public Response checkBacteriaMaster(BacteriaMasterDto bacteriaMasterDto) throws ApplicationException;
	public Response getToTalBacteriaMaster(Integer orgId) throws ApplicationException;
}
