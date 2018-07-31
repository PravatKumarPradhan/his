package com.param.lis.histopathology.transaction.service;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.ITransactionConstants;
import com.param.lis.global.common.Response;
import com.param.lis.histopathology.transaction.dao.IMicroScopicExaminationDao;
import com.param.lis.histopathology.transaction.dto.HistoParamDto;
import com.param.lis.histopathology.transaction.dto.TMicroExaDetailsDto;
import com.param.lis.histopathology.transaction.dto.TSpecimanMasterDto;
import com.param.lis.histopathology.transaction.dto.TSubSpecimanMasterDto;
import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({"rawtypes", "unchecked"})
public class MicroScopicExaminationServiceImpl
    implements IMicroScopicExaminationService, ICommonConstants, ITransactionConstants, IError {

  final static Logger logger = Logger.getLogger(MicroScopicExaminationServiceImpl.class);

  @Autowired
  IMicroScopicExaminationDao iMicroScopicExaminationDao;

  @Override
  @Transactional
  public Response getMicroscopicWorkList(HistoParamDto histoParamDto) throws ApplicationException {
    try {
      return iMicroScopicExaminationDao.getMicroscopicWorkList(histoParamDto);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getMicroscopicWorkListCount(HistoParamDto histoParamDto)
      throws ApplicationException {
    try {
      return iMicroScopicExaminationDao.getMicroscopicWorkListCount(histoParamDto);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getMiscroscopicSlides(TSubSpecimanMasterDto tSubSpecimanMasterDto)
      throws ApplicationException {
    try {
      return iMicroScopicExaminationDao.getMiscroscopicSlides(tSubSpecimanMasterDto);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response saveSlideExaminationsDetails(List<TMicroExaDetailsDto> listTMicroExaDetailsDto)
      throws ApplicationException {
    try {
    	return iMicroScopicExaminationDao.saveSlideExaminationsDetails(listTMicroExaDetailsDto);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

}
