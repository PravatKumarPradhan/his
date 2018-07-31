package com.param.lis.microbiology.transaction.service;

import java.util.Date;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.param.entity.lis.transaction.LabSampleDetailsMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.ITransactionConstants;
import com.param.lis.global.common.Response;
import com.param.lis.microbiology.transaction.dao.ISensitivityTestingDao;
import com.param.lis.microbiology.transaction.dto.MicrobioParamDto;
import com.param.lis.microbiology.transaction.dto.SensitivityTestResultMasterDto;
import com.param.lis.transaction.dto.LabSampleDetailsMasterDto;
import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({"rawtypes", "unchecked" })
public class SensitivityTestingServiceImpl implements ISensitivityTestingService, ICommonConstants, IError,ITransactionConstants {
	
	@Autowired
	ISensitivityTestingDao iSensitivityTestingDao;

	
	  @Autowired
	  IMicrobiologyService iMicrobiologyService;
	
	final static Logger logger = Logger.getLogger(MicrobiologyServiceImpl.class);


	@Override
	@Transactional
	public Response getSensitivityTestingList(MicrobioParamDto microbioParamDto) throws ApplicationException {
		try
		{
			return iSensitivityTestingDao.getSensitivityTestingList(microbioParamDto);
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@Override
	@Transactional
	public Response getSensitivityTestingListCount(MicrobioParamDto microbioParamDto) throws ApplicationException {
		try
		{
			return iSensitivityTestingDao.getSensitivityTestingListCount(microbioParamDto);
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@Override
	@Transactional
	public Response getSensitivityTestingDetails(MicrobioParamDto microbioParamDto) throws ApplicationException {
		try
		{
			return iSensitivityTestingDao.getSensitivityTestingDetails(microbioParamDto);
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}



	@Override
	@Transactional
	public Response saveSensitivityTestingObservation(
			SensitivityTestResultMasterDto sensitivityTestResultMasterDto,Integer labSampleStausId)
			throws ApplicationException {
		try
		{
		  LabSampleDetailsMasterDto labSampleDetailsMasterDto = new LabSampleDetailsMasterDto();
	        labSampleDetailsMasterDto.setCreatedBy(sensitivityTestResultMasterDto.getCreatedBy());
	        labSampleDetailsMasterDto.setLabSampleDtlsId(sensitivityTestResultMasterDto.getLabSampleDtlsId());
	        labSampleDetailsMasterDto.setCreatedDate(new Date(sensitivityTestResultMasterDto.getCreatedDate()));
			Response<SensitivityTestResultMasterDto, Integer> res =  iSensitivityTestingDao.saveSensitivityTestingObservation(sensitivityTestResultMasterDto);
			if(res.getCode().equals(SUCCESS_200))
			{
				if(labSampleStausId==SENSITIVITY_TEST)
				{
				  labSampleDetailsMasterDto.setSampleStatusId(SENSITIVITY_TEST);
					Response<LabSampleDetailsMaster, Integer> sampleStatusRes = iMicrobiologyService.updateLabSampleDetailsMaster(labSampleDetailsMasterDto);
					if(sampleStatusRes.getCode().equals(SUCCESS_200))
					{
						return new Response(SUCCESS, SUCCESS_200, SENSITIVITY_FINAL_RESULT_SAVE, null, null);
					}
					else
					{
						return new Response(ERROR, ERR_500, SENSITIVITY_FINAL_RESULT_FAIL, null, null);
					}
				}
				else if(labSampleStausId==SENSITIVITY_TEST_CHECK)
				{
				  labSampleDetailsMasterDto.setSampleStatusId(SENSITIVITY_TEST_CHECK);
				  Response<LabSampleDetailsMaster, Integer> sampleStatusRes = iMicrobiologyService.updateLabSampleDetailsMaster(labSampleDetailsMasterDto);
                  if(sampleStatusRes.getCode().equals(SUCCESS_200))
                  {
                      return new Response(SUCCESS, SUCCESS_200, SENSITIVITY_FINAL_RESULT_SAVE, null, null);
                  }
                  else
                  {
                      return new Response(ERROR, ERR_500, SENSITIVITY_FINAL_RESULT_FAIL, null, null);
                  }
				}
				else if(labSampleStausId==REPORT_RELEASED)
                {
				  labSampleDetailsMasterDto.setSampleStatusId(REPORT_RELEASED);
				  Response<LabSampleDetailsMaster, Integer> sampleStatusRes = iMicrobiologyService.updateLabSampleDetailsMaster(labSampleDetailsMasterDto);
                  if(sampleStatusRes.getCode().equals(SUCCESS_200))
                  {
                      return new Response(SUCCESS, SUCCESS_200, SENSITIVITY_FINAL_RESULT_SAVE, null, null);
                  }
                  else
                  {
                      return new Response(ERROR, ERR_500, SENSITIVITY_FINAL_RESULT_FAIL, null, null);
                  }
                }
				return new Response(SUCCESS, SUCCESS_200, SENSITIVITY_SAVE, null, null);
				
			}
			else {
				return new Response(ERROR, ERR_500, SENSITIVITY_FINAL_RESULT_FAIL, null, null);
			}		
			
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getSensitivityDetails(
	    Integer labSampleDtlsId, Integer orgId, Integer orgUnitId)
			throws ApplicationException {
		try
		{
			return iSensitivityTestingDao.getSensitivityDetails(labSampleDtlsId, orgId, orgUnitId);
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

}
