package com.param.billing.global.transaction.config.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.param.exception.ApplicationException;

import com.param.billing.common.ICommonConstants;
import com.param.billing.common.Response;
import com.param.billing.global.transaction.config.dao.IVariablePricingSequenceDao;
import com.param.billing.global.transaction.config.dto.VariablePricingSequenceDto;

@Service
@Transactional
public class VariablePricingSequenceServiceImpl implements IVariablePricingSequenceService, ICommonConstants{

	@Autowired
	IVariablePricingSequenceDao iVariablePricingSeqDao;
	
	@Override
	public Response saveVariablePricingSequenceMaster(
			VariablePricingSequenceDto variablePricingSequenceDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iVariablePricingSeqDao.saveVariablePricingSequenceMaster(variablePricingSequenceDto);
	}

	@Override
	public Response getVariablePricingSequenceByOrganisationId(VariablePricingSequenceDto variablePricingSequenceDto) throws ApplicationException {
		// TODO Auto-generated method stub
		return iVariablePricingSeqDao.getVariablePricingSequenceByOrganisationId(variablePricingSequenceDto);
	}

}
