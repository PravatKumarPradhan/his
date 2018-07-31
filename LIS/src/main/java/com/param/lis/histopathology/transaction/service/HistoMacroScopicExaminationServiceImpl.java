package com.param.lis.histopathology.transaction.service;

import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.param.entity.lis.transaction.LabSampleDetailsMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.ITransactionConstants;
import com.param.lis.global.common.Response;
import com.param.lis.global.common.SearchDto;
import com.param.lis.histopathology.transaction.dao.IHistoMacroScopicExaminationDao;
import com.param.lis.histopathology.transaction.dao.ISpecimanReceiptDao;
import com.param.lis.histopathology.transaction.dto.HistoParamDto;
import com.param.lis.histopathology.transaction.dto.TSpecimanMasterDto;
import com.param.lis.transaction.dto.SearchCommonDto;
import in.param.exception.ApplicationException;

@Service
@SuppressWarnings(
{ "rawtypes", "unchecked" })
public class HistoMacroScopicExaminationServiceImpl  implements IHistoMacroScopicExaminationService, ICommonConstants,ITransactionConstants ,IError
{
	
	final static Logger logger = Logger.getLogger(HistoMacroScopicExaminationServiceImpl.class);
	
	@Autowired
	IHistoMacroScopicExaminationDao iHistoMicroScopicExaminationDao;
	
	@Autowired
	private ISpecimanReceiptDao iSpecimanReceiptDao;

	@Override
	@Transactional
	public Response listSpecimanReceipt(HistoParamDto histoParamDto) throws ApplicationException 
	{
		try
		{
			return iHistoMicroScopicExaminationDao.listSpecimanReceipt(histoParamDto);
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getCollectedSpecimensCount(HistoParamDto histoParamDto) throws ApplicationException 
	{
		try
		{
			return iHistoMicroScopicExaminationDao.getCollectedSpecimensCount(histoParamDto);
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response saveMicroscopicExaminationDetails(TSpecimanMasterDto tSpecimanMasterDto)
			throws ApplicationException 
	{
		try
		{
			Response grossRes = iHistoMicroScopicExaminationDao.saveMicroscopicExaminationDetails(tSpecimanMasterDto);
			if(grossRes.getCode().equals(SUCCESS_200))
			{
				LabSampleDetailsMaster labSampleDetailsMaster = new LabSampleDetailsMaster();
				labSampleDetailsMaster.setUpdatedBy(tSpecimanMasterDto.getUpdatedBy());
				labSampleDetailsMaster.setUpdatedDate(tSpecimanMasterDto.getUpdatedDate());
				labSampleDetailsMaster.setLabSampleDtlsId(tSpecimanMasterDto.getLabSampleDtlsId());
				labSampleDetailsMaster.setSampleStatusId(GROSS_CREATED);
				Response labSampleDtlsRes =  iSpecimanReceiptDao.updateLabSampleDetailsMaster(labSampleDetailsMaster);
				if(labSampleDtlsRes.getCode().equals(SUCCESS_200))
				{
					return new Response(SUCCESS, SUCCESS_200, GROSS_CREATE_SUCCESS, null, null);
				}
				else {
					return new Response(ERROR, ERR_500, GROSS_CREATE_FAIL, null, null);
				}
			}
			else
			{
				return new Response(ERROR, ERR_500, GROSS_CREATE_FAIL, null, null);
			}
			
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response saveSpecimanDetails(TSpecimanMasterDto tSpecimanMasterDto) throws ApplicationException 
	{
		try
		{
			return iHistoMicroScopicExaminationDao.saveSpecimanDetails(tSpecimanMasterDto);
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response searchPatientbyVisitType(SearchCommonDto searchCommonDto) throws ApplicationException {
		try
		{
			return iHistoMicroScopicExaminationDao.searchPatientbyVisitType(searchCommonDto);
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response searchBySpecimenType(SearchCommonDto searchCommonDto) throws ApplicationException {
		try
		{
			return iHistoMicroScopicExaminationDao.searchBySpecimenType(searchCommonDto);
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response searchByTest(SearchCommonDto searchCommonDto) throws ApplicationException {
		try
		{
			return iHistoMicroScopicExaminationDao.searchByTest(searchCommonDto);
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response searchAcceptedSpecimanByPatient(SearchCommonDto searchCommonDto) throws ApplicationException {
		try
		{
			return iHistoMicroScopicExaminationDao.searchAcceptedSpecimanByPatient(searchCommonDto);
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response accetedSpecimenSearch(SearchDto searchDto) throws ApplicationException 
	{
		try
		{
			return iHistoMicroScopicExaminationDao.accetedSpecimenSearch(searchDto);
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getMicroscopicExamDataBySubSpecimenId(Integer subSpecimanId, Integer orgId, Integer orgUnitId)
			throws ApplicationException {
		try
		{
			return iHistoMicroScopicExaminationDao.getMicroscopicExamDataBySubSpecimenId(subSpecimanId, orgId, orgUnitId);
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

}
