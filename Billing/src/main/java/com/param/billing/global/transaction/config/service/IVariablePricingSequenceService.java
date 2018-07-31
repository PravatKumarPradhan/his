package com.param.billing.global.transaction.config.service;

import in.param.exception.ApplicationException;

import com.param.billing.common.Response;
import com.param.billing.global.transaction.config.dto.VariablePricingSequenceDto;

public interface IVariablePricingSequenceService {

	//Method to save variable pricing sequence
	Response saveVariablePricingSequenceMaster(VariablePricingSequenceDto variablePricingSequenceDto) throws ApplicationException;
	
	//Method to get variable pricing sequence by organisation id
	Response getVariablePricingSequenceByOrganisationId(VariablePricingSequenceDto variablePricingSequenceDto) throws ApplicationException;
}
