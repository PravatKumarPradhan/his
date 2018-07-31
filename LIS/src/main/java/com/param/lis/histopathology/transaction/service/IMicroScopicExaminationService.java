package com.param.lis.histopathology.transaction.service;

import java.util.List;
import com.param.lis.global.common.Response;
import com.param.lis.histopathology.transaction.dto.HistoParamDto;
import com.param.lis.histopathology.transaction.dto.TMicroExaDetailsDto;
import com.param.lis.histopathology.transaction.dto.TSubSpecimanMasterDto;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IMicroScopicExaminationService 
{
	public Response getMicroscopicWorkList(HistoParamDto histoParamDto)throws ApplicationException;
	public Response getMicroscopicWorkListCount(HistoParamDto histoParamDto) throws ApplicationException;
	public Response getMiscroscopicSlides(TSubSpecimanMasterDto tSubSpecimanMasterDtos) throws ApplicationException;
	public Response saveSlideExaminationsDetails(List<TMicroExaDetailsDto> listTMicroExaDetailsDto) throws ApplicationException;
}
