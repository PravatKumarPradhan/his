package com.param.billing.global.transaction.config.dao;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

import com.param.billing.common.Response;
import com.param.billing.global.transaction.config.dto.VariablePricingSequenceDto;
import com.param.billing.global.transaction.config.model.VariablePricingSequenceMaster;

public interface IVariablePricingSequenceDao  extends IGenericDao<VariablePricingSequenceMaster, Integer>{

	//Method to save variable pricing sequence
	Response saveVariablePricingSequenceMaster(VariablePricingSequenceDto variablePricingSequenceDto) throws ApplicationException;
	
	//Method to get variable pricing sequence by organisation id
	Response getVariablePricingSequenceByOrganisationId(VariablePricingSequenceDto variablePricingSequenceDto) throws ApplicationException;
}
