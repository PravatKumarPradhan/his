package com.param.lis.histopathology.transaction.service;

import in.param.exception.ApplicationException;

import java.util.List;

import com.param.lis.global.common.Response;
import com.param.lis.histopathology.transaction.dto.specimanReceiptDto;
@SuppressWarnings("rawtypes")
public interface ISpecimanReceiptService {
	
	public Response listSpecimanReceipt(specimanReceiptDto specimanReceiptDto) throws ApplicationException;
	public Response getTotalSpecimanReceiptRecord(specimanReceiptDto specimanReceiptDto) throws ApplicationException;
	public Response collectionSpeciman(List<specimanReceiptDto> listspecimanReceiptDto);

}
