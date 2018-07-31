package com.param.lis.transaction.service;



import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.ITransactionConstants;
import com.param.lis.global.common.Response;
import com.param.lis.transaction.dao.IReportHandOverDao;
import com.param.lis.transaction.dto.BioChemParamDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReportHandOverServiceImpl
		implements IReportHandOverService, ICommonConstants, ITransactionConstants, IError {

	@Autowired
	IReportHandOverDao iReportHandOver;

	final static Logger logger = Logger.getLogger(ReportHandOverServiceImpl.class);

	@Override
	@Transactional
	public Response getReportHandList(BioChemParamDto  bioChemParamDto) throws ApplicationException {
		try {
			return iReportHandOver.getReportHandList(bioChemParamDto);
		} catch (Exception e) {
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getReportHandListCount(BioChemParamDto bioChemParamDto) throws ApplicationException {
		try {
			return iReportHandOver.getReportHandListCount(bioChemParamDto);
		} catch (Exception e) {
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	

}
