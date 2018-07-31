package com.param.lis.histopathology.transaction.service;

import java.util.List;

import com.param.lis.global.common.Response;
import com.param.lis.global.common.SearchDto;
import com.param.lis.histopathology.transaction.dto.HistoParamDto;
import com.param.lis.histopathology.transaction.dto.OutSourceDetailMasterDto;
import com.param.lis.histopathology.transaction.dto.OutSourceMasterDto;
import com.param.lis.histopathology.transaction.dto.TSpecimanMasterDto;
import com.param.lis.transaction.dto.SearchCommonDto;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IOutSourcePendingListForSampleDispatchService 
{
	public Response outSourcePendingListForSampleDispatch(OutSourceMasterDto outSourceMasterDto)throws ApplicationException;
	public Response getoutSourcePendingListForSampleDispatchCount(OutSourceMasterDto outSourceMasterDto) throws ApplicationException;
	public Response saveOutSourceDetails( List<OutSourceMasterDto> outSourceMasterDto ) throws ApplicationException;
	public Response getOutSourceDetailsByOutSourceId(OutSourceDetailMasterDto outSourceDetailMasterDto)throws ApplicationException;
	
	/*public Response saveMicroscopicExaminationDetails(TSpecimanMasterDto tSpecimanMasterDto) throws ApplicationException;
	public Response saveSpecimanDetails(TSpecimanMasterDto tSpecimanMasterDto) throws ApplicationException;
	*/
	
}
