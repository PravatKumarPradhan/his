package com.param.lis.histopathology.transaction.dao;

import java.util.List;
import com.param.entity.lis.histo.TMicroExaDetails;
import com.param.lis.global.common.Response;
import com.param.lis.histopathology.transaction.dto.HistoParamDto;
import com.param.lis.histopathology.transaction.dto.TMicroExaDetailsDto;
import com.param.lis.histopathology.transaction.dto.TSubSpecimanMasterDto;
import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings({"rawtypes"})
public interface IMicroScopicExaminationDao extends IGenericDao<TMicroExaDetails, Integer>
{
  public Response getMicroscopicWorkList(HistoParamDto histoParamDto)throws ApplicationException;
  public Response getMicroscopicWorkListCount(HistoParamDto histoParamDto) throws ApplicationException;
  public Response getMiscroscopicSlides(TSubSpecimanMasterDto tSubSpecimanMasterDto) throws ApplicationException;
  public Response saveSlideExaminationsDetails(List<TMicroExaDetailsDto> listTMicroExaDetailsDto) throws ApplicationException;
	
}
