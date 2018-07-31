package com.param.lis.histopathology.transaction.service;

import com.param.lis.global.common.Response;
import com.param.lis.global.common.SearchDto;
import com.param.lis.histopathology.transaction.dto.HistoParamDto;
import com.param.lis.histopathology.transaction.dto.TSpecimanMasterDto;
import com.param.lis.transaction.dto.SearchCommonDto;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IHistoMacroScopicExaminationService 
{
	public Response listSpecimanReceipt(HistoParamDto histoParamDto)throws ApplicationException;
	public Response getCollectedSpecimensCount(HistoParamDto histoParamDto) throws ApplicationException;
	public Response saveMicroscopicExaminationDetails(TSpecimanMasterDto tSpecimanMasterDto) throws ApplicationException;
	public Response saveSpecimanDetails(TSpecimanMasterDto tSpecimanMasterDto) throws ApplicationException;
	
	/**@Code : for LabSearch*/
	public Response searchAcceptedSpecimanByPatient(SearchCommonDto searchCommonDto) throws ApplicationException;
	public Response searchPatientbyVisitType(SearchCommonDto searchCommonDto) throws ApplicationException;
	public Response searchBySpecimenType(SearchCommonDto searchCommonDto) throws ApplicationException;
	public Response searchByTest(SearchCommonDto searchCommonDto) throws ApplicationException;
	public Response accetedSpecimenSearch(SearchDto searchDto) throws ApplicationException;
	public Response getMicroscopicExamDataBySubSpecimenId(Integer subSpecimanId,Integer orgId,Integer orgUnitId)throws ApplicationException;
	
}
