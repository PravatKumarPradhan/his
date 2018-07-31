package com.param.lis.transaction.controller;



import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.common.SearchDto;
import com.param.lis.transaction.dto.BioChemParamDto;
import com.param.lis.transaction.dto.LabDashBoardDto;
import com.param.lis.transaction.dto.LabResultMasterDto;
import com.param.lis.transaction.dto.LabSampleDetailsMasterDto;
import com.param.lis.transaction.dto.LabSampleMasterDto;
import com.param.lis.transaction.dto.ParameterHistoryDto;
import com.param.lis.transaction.dto.ParameterSearchDto;
import com.param.lis.transaction.dto.ResultEntryDataDto;
import com.param.lis.transaction.dto.SampleAcceptancePendingDto;
import com.param.lis.transaction.dto.SearchCommonDto;
import com.param.lis.transaction.service.IBioChemistryService;

@RestController
@SuppressWarnings(
{ "rawtypes", "unchecked" })
@RequestMapping(value = "/LIS/BioChemistry")
public class BioChemistryController implements ICommonConstants, IError
{
	@Autowired
	private IBioChemistryService iBioChemistryService;

	final static Logger logger = Logger.getLogger(BioChemistryController.class);
	
	@RequestMapping(value = "/Aacceptance/Pending", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response sampleAcceptancePending(
			@RequestBody BioChemParamDto bioChemParamDto)
	{
		Response<SampleAcceptancePendingDto, Integer> response = null;
		try
		{
			response = iBioChemistryService.sampleAcceptancePending(bioChemParamDto);
			return response;
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	@RequestMapping(value = "/Aacceptance/Pending/Count", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response sampleAcceptancePendingCount(@RequestBody BioChemParamDto bioChemParamDto)
	{
		Response<SampleAcceptancePendingDto, Integer> response = null;
		try
		{
			response = iBioChemistryService.sampleAcceptancePendingCount(bioChemParamDto);
			return response;
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value = "/Biochemistry/Pending/Sample", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response bioChemistryAcceptRejectSample(@RequestBody List<LabSampleDetailsMasterDto> listlabSampleDetailsMasterDto)
	{
		Response<LabSampleDetailsMasterDto, Integer> response = null;
		try
		{
			response = iBioChemistryService.bioChemistryAcceptRejectSample(listlabSampleDetailsMasterDto);
			return response;
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	
	@RequestMapping(value = "/Worklist", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getBioChemistryWorklist(@RequestBody BioChemParamDto bioChemParamDto)
	{
		Response<LabSampleDetailsMasterDto, Integer> response = null;
		try
		{
			response = iBioChemistryService.getBioChemistryWorklist(bioChemParamDto);
			return response;
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value = "/Worklist/Count", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getBioChemistryWorklistCount(@RequestBody BioChemParamDto bioChemParamDto)
	{
		Response<LabSampleDetailsMasterDto, Integer> response = null;
		try
		{
			response = iBioChemistryService.getBioChemistryWorklistCount(bioChemParamDto);
			return response;
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value = "/Worklist/ReportEntry", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response sendToResultEntry(@RequestBody List<LabSampleDetailsMasterDto> listlabSampleDetailsMasterDto)
	{
		Response<LabSampleDetailsMasterDto, Integer> response = null;
		try
		{
			response = iBioChemistryService.sendToResultEntry(listlabSampleDetailsMasterDto);
			return response;
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value = "/retest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response sampleRetest(@RequestBody List<LabResultMasterDto> labSampleDetailsMasterDto)
	{
		Response<LabSampleDetailsMasterDto, Integer> response = null;
		try
		{
			response = iBioChemistryService.sampleRetest(labSampleDetailsMasterDto);
			return response;
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value = "/recollect", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response sampleRecollect(@RequestBody List<LabResultMasterDto> listLabResultMasterDto)
	{
		Response<LabSampleDetailsMasterDto, Integer> response = null;
		try
		{
			response = iBioChemistryService.sampleRecollect(listLabResultMasterDto);
			return response;
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value = "/resultEntryData", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response resultEntryData(@RequestBody ResultEntryDataDto resultEntryDataDto)
	{
		Response<ResultEntryDataDto, Integer> response = null;
		try
		{
			response = iBioChemistryService.resultEntryData(resultEntryDataDto);
			return response;
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	 @RequestMapping(value = "/bioChemistry/patient/Search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	  public @ResponseBody Response searchbioChemistryPatientlist(@RequestBody SearchCommonDto searchCommonDto)
	  {
	      Response<LabDashBoardDto, Integer> response = null;
	      try
	      {
	          response = iBioChemistryService.searchbioChemistryPatientlist(searchCommonDto);
	          return response;
	      } catch (Exception e)
	      {
	          logger.error("Exception", e);
	          return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	      }
	  }
	 
	 @RequestMapping(value = "/bioChemistry/sampleNo/Search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	  public @ResponseBody Response searchBioChemistrySampleNo(@RequestBody SearchCommonDto searchCommonDto)
	  {
	      Response<LabDashBoardDto, Integer> response = null;
	      try
	      {
	          response = iBioChemistryService.searchBioChemistrySampleNo(searchCommonDto);
	          return response;
	      } catch (Exception e)
	      {
	          logger.error("Exception", e);
	          return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	      }
	  }
	 
	 @RequestMapping(value = "/bioChemistryList/Search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	  public @ResponseBody Response getFilteredBioChemistryList(@RequestBody SearchDto searchDto)
	  {
	      Response<LabSampleMasterDto, Integer> response = null;
	      try
	      {
	          response = iBioChemistryService.getFilteredBioChemistryList(searchDto);
	          return response;
	      } catch (Exception e)
	      {
	          logger.error("Exception", e);
	          return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	      }
	  }
	 
	 @RequestMapping(value = "/WorklistForDepartmentList/Search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	  public @ResponseBody Response getFilteredWorklistForDepartment(@RequestBody SearchDto searchDto)
	  {
	      Response<LabSampleMasterDto, Integer> response = null;
	      try
	      {
	          response = iBioChemistryService.getFilteredWorklistForDepartment(searchDto);
	          return response;
	      } catch (Exception e)
	      {
	          logger.error("Exception", e);
	          return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	      }
	  }
	 
	   @RequestMapping(value = "/parameterHistory", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	    public @ResponseBody Response getParameterHistory(@RequestBody ParameterSearchDto parameterSearchDto)
	    {
	        Response<ParameterHistoryDto, Integer> response = null;
	        try
	        {
	            response = iBioChemistryService.getParameterHistory(parameterSearchDto);
	            return response;
	        } catch (Exception e)
	        {
	            logger.error("Exception", e);
	            return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	        }
	    }
	   @RequestMapping(value = "/ResultId", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
       public @ResponseBody Response getPreviousResultIdByTest(@RequestBody BioChemParamDto bioChemParamDto)
       {
           Response<ParameterHistoryDto, Integer> response = null;
           try
           {
               response = iBioChemistryService.getPreviousResultIdByTest(bioChemParamDto);
               return response;
           } catch (Exception e)
           {
               logger.error("Exception", e);
               return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
           }
       }
	   
	   
	   @RequestMapping(value = "/previouResults", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
       public @ResponseBody Response getRetestedRecollectedResult(@RequestBody BioChemParamDto bioChemParamDto)
       {
           Response<ParameterHistoryDto, Integer> response = null;
           try
           {
               response = iBioChemistryService.getRetestedRecollectedResult(bioChemParamDto);
               return response;
           } catch (Exception e)
           {
               logger.error("Exception", e);
               return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
           }
       }
	   

	   @RequestMapping(value = "/ResultEnterBy", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
       public @ResponseBody Response getResultEnteryByDateTime(@RequestBody BioChemParamDto bioChemParamDto)
       {
           Response<ParameterHistoryDto, Integer> response = null;
           try
           {
               response = iBioChemistryService.getResultEnteryByDateTime(bioChemParamDto);
               return response;
           } catch (Exception e)
           {
               logger.error("Exception", e);
               return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
           }
       }
	   
	   @RequestMapping(value = "/ResultValidatedBy", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
       public @ResponseBody Response getResultValidatedByDateTime(@RequestBody BioChemParamDto bioChemParamDto)
       {
           Response<ParameterHistoryDto, Integer> response = null;
           try
           {
               response = iBioChemistryService.getResultValidatedByDateTime(bioChemParamDto);
               return response;
           } catch (Exception e)
           {
               logger.error("Exception", e);
               return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
           }
       }
	   
	   @RequestMapping(value = "/ResultReleaseBy", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
       public @ResponseBody Response getResultReleaseByDateTime(@RequestBody BioChemParamDto bioChemParamDto)
       {
           Response<ParameterHistoryDto, Integer> response = null;
           try
           {
               response = iBioChemistryService.getResultReleaseByDateTime(bioChemParamDto);
               return response;
           } catch (Exception e)
           {
               logger.error("Exception", e);
               return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
           }
       }
       
}
