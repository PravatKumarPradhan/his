package com.param.lis.transaction.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.ITransactionConstants;
import com.param.lis.global.common.Response;
import com.param.lis.global.common.SearchDto;
import com.param.lis.transaction.dao.ILabDashBoardDao;
import com.param.lis.transaction.dto.LabCommonDto;
import com.param.lis.transaction.dto.SearchCommonDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({"rawtypes", "unchecked"})
public class LabDashBoardServiceImpl 
implements ILabDashBoardService, ICommonConstants, ITransactionConstants, IError {
  
  final  Logger logger = Logger.getLogger(LabDashBoardServiceImpl.class);

  @Autowired
  private ILabDashBoardDao iLabDashBoardDao;

  @Override
  @Transactional
  public Response getlabDashBoardList(LabCommonDto labCommonDto) throws ApplicationException {
    try {
      return iLabDashBoardDao.getlabDashBoardList(labCommonDto);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getlabDashBoardListCount(LabCommonDto labCommonDto) throws ApplicationException {
    try {
      return iLabDashBoardDao.getlabDashBoardListCount(labCommonDto);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getSampleLog(Integer labSampleDtlsId, Integer orgId, Integer orgUnitId)
      throws ApplicationException {
    try {
      return iLabDashBoardDao.getSampleLog(labSampleDtlsId,orgId,orgUnitId);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getFilteredDashBoardList(SearchDto searchDto) throws ApplicationException {
    try {
      return iLabDashBoardDao.getFilteredDashBoardList(searchDto);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }
  
  @Transactional
	public Response searchAcceptedSpecimanByPatient(SearchCommonDto searchCommonDto) throws ApplicationException {
		try
		{
			return iLabDashBoardDao.searchAcceptedSpecimanByPatient(searchCommonDto);
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

@Override
@Transactional
public Response searchAcceptedSampleNo(SearchCommonDto searchCommonDto) throws ApplicationException {
	try
	{
		return iLabDashBoardDao.searchAcceptedSampleNo(searchCommonDto);
	} catch (Exception e)
	{
		logger.error("Exception", e);
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}
}


}
