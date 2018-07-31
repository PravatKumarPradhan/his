/**@author Ganesh***/

package com.param.lis.common.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.param.lis.common.dto.CommonAutoCompleteDto;
import com.param.lis.common.dto.CommonDto;
import com.param.lis.common.dto.RejectionReasonDto;
import com.param.lis.common.dto.SampleStatusDto;
import com.param.lis.common.service.ICommonListService;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.SelectedAntibioticListDto;

@RestController
@SuppressWarnings(
{ "rawtypes", "unchecked" })
@RequestMapping(value = "/LIS/common")
public class CommonListController implements ICommonConstants, IError
{
	@Autowired
	private ICommonListService iCommonListService;

	final static Logger logger = Logger.getLogger(CommonListController.class);

	@RequestMapping(value = "/getSampleStatusList/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getSampleStatusList(@PathVariable(value = "orgId") Integer orgId)
	{
		Response<SampleStatusDto, Integer> response = null;
		try
		{
			response = iCommonListService.getSampleStatusList(orgId);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@RequestMapping(value = "/getRejectionReasonList/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getRejectionReasonList(@PathVariable(value = "orgId") Integer orgId
	)
	{
		Response<RejectionReasonDto, Integer> response = null;
		try
		{
			response = iCommonListService.getRejectionReasonList(orgId);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@RequestMapping(value = "/getDeparmentList/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getDeparmentList(@PathVariable(value = "orgId") Integer orgId
	)
	{
		Response<CommonDto, Integer> response = null;
		try
		{
			response = iCommonListService.getDeparmentList(orgId);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@RequestMapping(value = "/getSampleTypeList/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getSampleTypeList(@PathVariable(value = "orgId") Integer orgId
	)
	{
		Response<CommonDto, Integer> response = null;
		try
		{
			response = iCommonListService.getSampleTypeList(orgId);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@RequestMapping(value = "/getUnitList/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getUnitList(@PathVariable(value = "orgId") Integer orgId
	)
	{
		Response<CommonDto, Integer> response = null;
		try
		{
			response = iCommonListService.getUnitList(orgId);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@RequestMapping(value = "/getContainerList/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getContainerList(@PathVariable(value = "orgId") Integer orgId
	)
	{
		Response<CommonDto, Integer> response = null;
		try
		{
			response = iCommonListService.getContainerList(orgId);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@RequestMapping(value = "/getReportTypeList/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getReportTypeList(@PathVariable(value = "orgId") Integer orgId
	)
	{
		Response<CommonDto, Integer> response = null;
		try
		{
			response = iCommonListService.getReportTypeList(orgId);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@RequestMapping(value = "/getTechniqueList/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getTechniqueList(@PathVariable(value = "orgId") Integer orgId
	)
	{
		Response<CommonDto, Integer> response = null;
		try
		{
			response = iCommonListService.getTechniqueList(orgId);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@RequestMapping(value = "/getPrerequisitesList/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getPrerequisitesList(@PathVariable(value = "orgId") Integer orgId
	)
	{
		Response<CommonDto, Integer> response = null;
		try
		{
			response = iCommonListService.getPrerequisitesList(orgId);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@RequestMapping(value = "/getHeaderList/{orgId}/{orgUnitId}", method = RequestMethod.GET)
	public @ResponseBody Response getHeaderList(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "orgUnitId") Integer orgUnitId)
	{
		Response<CommonDto, Integer> response = null;
		try
		{
			response = iCommonListService.getHeaderList(orgId,orgUnitId);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@RequestMapping(value = "/getParameterList/{orgId}/{orgUnitId}", method = RequestMethod.GET)
	public @ResponseBody Response getParameterList(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "orgUnitId") Integer orgUnitId	)
	{
		Response<CommonDto, Integer> response = null;
		try
		{
			response = iCommonListService.getParameterList(orgId,orgUnitId);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value = "/getAgeGroupList/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getAgeGroupList(@PathVariable(value = "orgId") Integer orgId)
	{
		Response<CommonDto, Integer> response = null;
		try
		{
			response = iCommonListService.getAgeGroupList(orgId);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	

	@RequestMapping(value = "/getGenderList/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getGenderList(@PathVariable(value = "orgId") Integer orgId)
	{
		Response<CommonDto, Integer> response = null;
		try
		{
			response = iCommonListService.getGenderList(orgId);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	

	@RequestMapping(value = "/getDayList/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getDayList(@PathVariable(value = "orgId") Integer orgId)
	{
		Response<CommonDto, Integer> response = null;
		try
		{
			response = iCommonListService.getDayList(orgId);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value = "/autoCompleteServices/{orgId}/{orgUnitId}/{deptId}/{serviceName}", method = RequestMethod.GET)
	public @ResponseBody Response getAutoCompleteServices(
			@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "orgUnitId") Integer orgUnitId,
			@PathVariable(value = "deptId") Integer deptId,
			@PathVariable(value = "serviceName") String serviceName)
	{
		Response<CommonAutoCompleteDto, Integer> response = null;
		try
		{
			response = iCommonListService.getAutoCompleteServices(orgId,orgUnitId,deptId, serviceName);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	@RequestMapping(value = "/getTrunAroundTimeList/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getTrunAroundTimeList(@PathVariable(value = "orgId") Integer orgId
	)
	{
		Response<CommonDto, Integer> response = null;
		try
		{
			response = iCommonListService.getTrunAroundTime(orgId);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value ="/MediaList/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response MediaList(
			@PathVariable(value = "orgId") Integer orgId)
	{
		Response<CommonDto, Integer> response = null;
		try
		{
			response = iCommonListService.getMediaList(orgId);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value ="/colonyList/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response ColonyList(
			@PathVariable(value = "orgId") Integer orgId)
	{
		Response<CommonDto, Integer> response = null;
		try
		{
			response = iCommonListService.getColonyList(orgId);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	
	@RequestMapping(value ="/antibioticGroupList/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getAntibioticGroupList(
			@PathVariable(value = "orgId") Integer orgId)
	{
		Response<CommonDto, Integer> response = null;
		try
		{
			response = iCommonListService.getAntibioticGroupList(orgId);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value ="/microOrganism/list/orgId/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getOrganismGroupList(
			@PathVariable(value = "orgId") Integer orgId)
	{
		Response<CommonDto, Integer> response = null;
		try
		{
			response = iCommonListService.getOrganismGroupList(orgId);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	@RequestMapping(value ="/incubationTime/orgId/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getIncubationTimeList(
			@PathVariable(value = "orgId") Integer orgId)
	{
		Response<CommonDto, Integer> response = null;
		try
		{
			response = iCommonListService.getIncubationTimeList(orgId);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	
	
	@RequestMapping(value ="/media/list/sampleId/{sampleId}/orgId/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getMediaListBySampleType(@PathVariable(value = "sampleId") Integer sampleId,
			@PathVariable(value = "orgId") Integer orgId)
	{
		Response<CommonDto, Integer> response = null;
		try
		{
			response = iCommonListService.getMediaListBySampleType(sampleId,orgId);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	
	@RequestMapping(value ="/incubationPeriod/list/orgId/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getIncubationPeriodList(
			@PathVariable(value = "orgId") Integer orgId)
	{
		Response<CommonDto, Integer> response = null;
		try
		{
			response = iCommonListService.getIncubationPeriodList(orgId);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	
	@RequestMapping(value ="/staining/list/orgId/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getStainingListByOrgId(
			@PathVariable(value = "orgId") Integer orgId)
	{
		Response<CommonDto, Integer> response = null;
		try
		{
			response = iCommonListService.getStainingListByOrgId(orgId);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	   @RequestMapping(value ="/micribiology/stain/{orgId}", method = RequestMethod.GET)
	    public @ResponseBody Response getMicrobiologyStainingList(
	            @PathVariable(value = "orgId") Integer orgId)
	    {
	        Response<CommonDto, Integer> response = null;
	        try
	        {
	            response = iCommonListService.getMicrobiologyStainingList(orgId);
	            return response;
	        } catch (Exception e)
	        {
	            logger.error("Exection", e);
	            return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	        }
	    }
	
	
	@RequestMapping(value ="/microOrgasm/list/organismGroup/{organismGroupId}/orgId/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getMicroOrganismByGroup(
			@PathVariable(value = "organismGroupId") Integer organismGroupId,
			@PathVariable(value = "orgId") Integer orgId)
	{
		Response<CommonDto, Integer> response = null;
		try
		{
			response = iCommonListService.getMicroOrganismByGroup(organismGroupId,orgId);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	
	/*@RequestMapping(value ="/antibiotic/list/antibioticGroupId/{antibioticGroupId}/orgId/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getAntibioticByantibioticGroup(
			@PathVariable(value = "antibioticGroupId") Integer antibioticGroupId,
			@PathVariable(value = "orgId") Integer orgId)
	{
		Response<CommonDto, Integer> response = null;
		try
		{
			response = iCommonListService.getAntibioticGroupByGroup(antibioticGroupId, orgId);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}*/
	
	@RequestMapping(value = "/antibiotic/list", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response addAntibioticAddtionClassMaster(@RequestBody SelectedAntibioticListDto selectedAntibioticListDto ){
		try{
			return iCommonListService.getAntibioticGroupByGroup(selectedAntibioticListDto);
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return new Response<>(ERROR, ERR_201,ANTIBIOTIC_ADD_FAIL, null, null);
	}
	
	@RequestMapping(value ="/getOrganismList/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getOrganismList(
			@PathVariable(value = "orgId") Integer orgId)
	{
		Response<CommonDto, Integer> response = null;
		try
		{
			response = iCommonListService.getOrganismList(orgId);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value = "/autoCompleteServicesForAntibiotic/orgId/{orgId}/antibiotic/{antibioticName}", method = RequestMethod.GET)
	public @ResponseBody Response getAutoCompleteServicesForAntibiotic(
			@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "antibioticName") String antibioticName)
	{
		Response<CommonAutoCompleteDto, Integer> response = null;
		try
		{
			response = iCommonListService.getAutoCompleteServicesForAntibiotic(orgId,antibioticName);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value ="/getMicrolabPriorityList/orgId/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getMicrolabPriorityList(@PathVariable(value = "orgId") Integer orgId)
	{
		Response<CommonDto, Integer> response = null;
		try
		{
			response = iCommonListService.getMicrolabPriorityList(orgId);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value ="/antibiotics/list/orgId/{orgId}/organismId/{organismId}", method = RequestMethod.GET)
	public @ResponseBody Response getAntibioticsByOrganismIdList(
			@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "organismId") Integer organismId
			)
	{
		Response<CommonDto, Integer> response = null;
		try
		{
			response = iCommonListService.getAntibioticsByOrganismIdList(orgId, organismId);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value ="/spmecimantype/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getSpecimanTypeList(@PathVariable(value = "orgId") Integer orgId)
	{
		Response<CommonDto, Integer> response = null;
		try
		{
			response = iCommonListService.getSpecimanTypeList(orgId);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value ="/getAccountPayableList/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getAccountPayableList(@PathVariable(value = "orgId") Integer orgId)
	{
		Response<CommonDto, Integer> response = null;
		try
		{
			response = iCommonListService.getAccountPayableList(orgId);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	
	@RequestMapping(value = "/getRackList/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getRackList(@PathVariable(value = "orgId") Integer orgId)
	{
		Response<CommonDto, Integer> response = null;
		try
		{
			response = iCommonListService.getRackList(orgId);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value = "/getRackListByShelfId/{rackId}/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getShelfListByRackId(@PathVariable(value = "rackId") Integer rackId,@PathVariable(value = "orgId") Integer orgId)
	{
		Response<CommonDto, Integer> response = null;
		try
		{
			response = iCommonListService.getRackListByShelfId(rackId, orgId);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	
	@RequestMapping(value ="/getSampleStatus/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getSampleStatus(@PathVariable(value = "orgId") Integer orgId)
	{
		Response<CommonDto, Integer> response = null;
		try
		{
			response = iCommonListService.getSampleStatus(orgId);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	   @RequestMapping(value ="/testParameters/{testId}/{orgId}/{orgUnitId}", method = RequestMethod.GET)
	    public @ResponseBody Response getTestParameters(@PathVariable(value = "testId") Integer testId,
	        @PathVariable(value = "orgId") Integer orgId,
	        @PathVariable(value = "orgUnitId") Integer orgUnitId)
	    {
	        Response<CommonDto, Integer> response = null;
	        try
	        {
	            response = iCommonListService.getTestParameters(testId,orgId,orgUnitId);
	            return response;
	        } catch (Exception e)
	        {
	            logger.error("Exection", e);
	            return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	        }
	    }
	   
	   @RequestMapping(value ="/refRengeTypes/{orgId}/{orgUnitId}", method = RequestMethod.GET)
       public @ResponseBody Response getRefRangeTypeList(
           @PathVariable(value = "orgId") Integer orgId,
           @PathVariable(value = "orgUnitId") Integer orgUnitId)
       {
           Response<CommonDto, Integer> response = null;
           try
           {
               response = iCommonListService.getRefRangeTypeList(orgId,orgUnitId);
               return response;
           } catch (Exception e)
           {
               logger.error("Exection", e);
               return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
           }
       }
	   
	   
	   @RequestMapping(value ="/textualReferenceRanges/{orgId}/{orgUnitId}", method = RequestMethod.GET)
       public @ResponseBody Response getTextualReferenceRanges(
           @PathVariable(value = "orgId") Integer orgId,
           @PathVariable(value = "orgUnitId") Integer orgUnitId)
       {
           Response<CommonDto, Integer> response = null;
           try
           {
               response = iCommonListService.getTextualReferenceRanges(orgId,orgUnitId);
               return response;
           } catch (Exception e)
           {
               logger.error("Exection", e);
               return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
           }
       }
	   
	   @RequestMapping(value ="/microbiology/test/{orgId}/{orgUnitId}", method = RequestMethod.GET)
	    public @ResponseBody Response getMicrobiologyTestList(
	            @PathVariable(value = "orgId") Integer orgId,
	            @PathVariable(value = "orgUnitId") Integer orgUnitId)
	    {
	        Response<CommonDto, Integer> response = null;
	        try
	        {
	            response = iCommonListService.getMicrobiologyTestList(orgId,orgUnitId);
	            return response;
	        } catch (Exception e)
	        {
	            logger.error("Exection", e);
	            return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	        }
	    }
	
}
