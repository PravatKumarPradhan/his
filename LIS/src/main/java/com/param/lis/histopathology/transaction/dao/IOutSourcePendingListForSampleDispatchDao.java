package com.param.lis.histopathology.transaction.dao;

import com.param.entity.lis.histo.OutSourceMaster;
import com.param.lis.global.common.Response;
import com.param.lis.histopathology.transaction.dto.OutSourceDetailMasterDto;
import com.param.lis.histopathology.transaction.dto.OutSourceMasterDto;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings({"rawtypes"})
public interface IOutSourcePendingListForSampleDispatchDao   extends IGenericDao<OutSourceMaster, Integer>
{
	public Response outSourcePendingListForSampleDispatch(OutSourceMasterDto outSourceMasterDto)throws ApplicationException;
	public Response getoutSourcePendingListForSampleDispatchCount(OutSourceMasterDto outSourceMasterDto) throws ApplicationException;
	public Response saveOutSourceDetails(OutSourceMasterDto outSourceMasterDto) throws ApplicationException;
	public Response getOutSourceDetailsByOutSourceId(OutSourceDetailMasterDto outSourceDetailMasterDto)throws ApplicationException;
	/*public Response saveSpecimanDetails(TSpecimanMasterDto tSpecimanMasterDto) throws ApplicationException;
	*/

}
