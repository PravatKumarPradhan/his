package com.param.lis.transaction.service;

import java.util.List;

import com.param.global.dto.OrderDetailsMasterDto;
import com.param.lis.global.common.Response;
import com.param.lis.global.common.SearchDto;
import com.param.lis.transaction.dto.LabSampleDetailsMasterDto;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface ILabCentralReceivingService
{
	public Response getSampleListForInOutPatient(Integer orgId, Integer orgUnitId, Integer visitTypeId,Integer deptId, Integer offset,
			Integer recordPerPage) throws ApplicationException;

	public Response acceptOrRejectSample(List<LabSampleDetailsMasterDto> listLabSampleDetailsMasterDto)
			throws ApplicationException;

	public Response getCentrifugationWorklist(Integer orgId, Integer orgUnitId,Integer deptId, Integer offset, Integer recordPerPage)
			throws ApplicationException;

	public Response getTotalRecordOutPatient(Integer orgId, Integer unitId,Integer deptId) throws ApplicationException;

	public Response getTotalRecordINPatient(Integer orgId, Integer orgUnitId,Integer deptId) throws ApplicationException;

	public Response getTotalcentrifugationWorkList(Integer orgId, Integer unitId,Integer deptId) throws ApplicationException;

	public Response getRejectedSampleList(Integer orgId, Integer orgUnitId,  Integer deptId,Integer offset, Integer recordPerPage)
			throws ApplicationException;

	public Response getTotalRecordRejectedSample(Integer orgId, Integer orgUnitId,Integer deptId) throws ApplicationException;

	public Response sampleRecollect(List<LabSampleDetailsMasterDto> listlabLabSampleDetailsMasterDto) throws ApplicationException;
	
	public Response sendToDepartMent(List<LabSampleDetailsMasterDto> listlabLabSampleDetailsMasterDto) throws ApplicationException;
	
	public Response getFilteredOutPatientList(SearchDto searchDto)
		      throws ApplicationException;
	
	public Response getFilteredCentrifugationWorkList(SearchDto searchDto) throws ApplicationException;

}
