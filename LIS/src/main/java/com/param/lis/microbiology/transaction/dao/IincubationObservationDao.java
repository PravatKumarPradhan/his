package com.param.lis.microbiology.transaction.dao;

import com.param.entity.lis.micro.TMicroIncubationMaster;
import com.param.lis.global.common.Response;
import com.param.lis.microbiology.transaction.dto.MicrobioParamDto;
import com.param.lis.microbiology.transaction.dto.TMicroIncubationMasterDto;
import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IincubationObservationDao extends IGenericDao<TMicroIncubationMaster, Integer> {

	public Response getIncubationObservationList(MicrobioParamDto microbioParamDto) throws ApplicationException;

	public Response getIncubationObservationListCount(MicrobioParamDto microbioParamDto) throws ApplicationException;
	
	public Response saveIncubationTrasaction(TMicroIncubationMasterDto tMicroIncubationMasterDto) throws ApplicationException;
	
	public Response getIncubationDetails(Integer tIncubationId,Integer orgId,Integer orgUnitId) throws ApplicationException;
	
	public Response updateIncubationTransaction(TMicroIncubationMasterDto tMicroIncubationMasterDto) throws ApplicationException;
    
}
