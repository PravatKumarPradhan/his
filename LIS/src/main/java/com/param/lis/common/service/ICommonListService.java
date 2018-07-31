
package com.param.lis.common.service;

import in.param.exception.ApplicationException;

import com.param.lis.global.common.Response;
import com.param.lis.global.dto.SelectedAntibioticListDto;

@SuppressWarnings("rawtypes")
public interface ICommonListService
{
	public Response getSampleStatusList(Integer orgId) throws ApplicationException;

	public Response getRejectionReasonList(Integer orgId) throws ApplicationException;

	public Response getDeparmentList(Integer orgId) throws ApplicationException;

	public Response getSampleTypeList(Integer orgId) throws ApplicationException;

	public Response getUnitList(Integer orgId) throws ApplicationException;

	public Response getContainerList(Integer orgId) throws ApplicationException;

	public Response getReportTypeList(Integer orgId) throws ApplicationException;

	public Response getTechniqueList(Integer orgId) throws ApplicationException;

	public Response getPrerequisitesList(Integer orgId) throws ApplicationException;

	public Response getHeaderList(Integer orgId,Integer orgUnitId) throws ApplicationException;

	public Response getParameterList(Integer orgId,Integer orgUnitId) throws ApplicationException;
	
	public Response getAgeGroupList(Integer orgId) throws ApplicationException;
	
	public Response getGenderList(Integer orgId) throws ApplicationException;
	
	public Response getDayList(Integer orgId) throws ApplicationException;
	
	public Response getTrunAroundTime(Integer orgId) throws ApplicationException;
	
	public Response getAutoCompleteServices(Integer orgId,Integer orgUnitId,Integer deptId ,String serviceName) throws ApplicationException;
	
	public Response getMediaList(Integer orgId) throws ApplicationException;
	
	public Response getColonyList(Integer orgId) throws ApplicationException;
	
	public Response getAntibioticGroupList(Integer orgId) throws ApplicationException;
	
	public Response getOrganismGroupList(Integer orgId) throws ApplicationException;
	
    public Response getIncubationTimeList(Integer orgId) throws ApplicationException;
	
	public Response getMediaListBySampleType(Integer sampleId,Integer orgId) throws ApplicationException;
	
	public Response getIncubationPeriodList(Integer orgId) throws ApplicationException;
	
	public Response getStainingListByOrgId(Integer orgId) throws ApplicationException;
	
	public Response getMicrobiologyStainingList(Integer orgId) throws ApplicationException;
	
	public Response getMicroOrganismByGroup(Integer organismGroupId,Integer orgId) throws ApplicationException;
	
	public Response getAntibioticGroupByGroup(SelectedAntibioticListDto selectedAntibioticListDto) throws ApplicationException;
	
	public Response getOrganismList(Integer orgId) throws ApplicationException;
	
	public Response getAutoCompleteServicesForAntibiotic(Integer orgId,String serviceName) throws ApplicationException;
	
	public Response getMicrolabPriorityList(Integer orgId) throws ApplicationException;
	
	public Response getAntibioticsByOrganismIdList(Integer orgId,Integer organismId) throws ApplicationException;
	
	public Response getSpecimanTypeList(Integer orgId) throws ApplicationException;
	
	public Response getAccountPayableList(Integer orgId) throws ApplicationException;
	
	public Response getRackList(Integer orgId) throws ApplicationException;
	
	public Response  getRackListByShelfId(Integer shelfId,Integer orgId)throws ApplicationException;  

	public Response getSampleStatus(Integer orgId) throws ApplicationException;
	
	public Response getTestParameters(Integer testId,Integer orgId,Integer orgUnitId) throws ApplicationException;
	
	public Response getRefRangeTypeList(Integer orgId,Integer orgUnitId) throws ApplicationException;
	
	public Response getTextualReferenceRanges(Integer orgId,Integer orgUnitId) throws ApplicationException;

	public Response getMicrobiologyTestList(Integer orgId,Integer orgUnitId);
	
}
