package com.param.lis.transaction.dao;

import java.util.List;
import com.param.entity.lis.transaction.LabSampleMaster;
import com.param.lis.global.common.Response;
import com.param.lis.global.common.SearchDto;
import com.param.lis.transaction.dto.LabSampleDetailsMasterDto;
import com.param.lis.transaction.dto.LabSampleMasterDto;
import com.param.lis.transaction.dto.SearchCommonDto;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface ILabTransactionDao extends IGenericDao<LabSampleMaster, Integer>
{
	
	/**
	*PhlebotomyWorklist Details
	* @version 1.0
	*/
	
	public Response getPhlebotomyWorklist(Integer orgId, Integer orgUnitId,Integer deptId,Integer offset, Integer recordPerPage) throws ApplicationException;

	public Response getTotalPhlobotomyRecord(Integer orgId, Integer orgUnitId,Integer deptId) throws ApplicationException;

	public Response getPhlebotomyWorklistDetails(Integer orgId, Integer orgUnitId, Integer orderId,Integer deptId)
			throws ApplicationException;

	public Response sampleCollection(LabSampleMasterDto labSampleMasterDto) throws ApplicationException;
	
	public Response getCollectedSample(Integer orgId, Integer orgUnitId,Integer deptId,Integer offset, Integer recordPerPage) throws ApplicationException;

	public Response sendToCr(List<LabSampleDetailsMasterDto> listLabSampleDetailsMasterDto) throws ApplicationException;
	
	public Response getTotalSendToCrRecord(Integer orgId,Integer orgUnitId,Integer deptId)  throws ApplicationException;
	
	public Response updateOrderStatus(Integer orderId,Integer orderStatusId)  throws ApplicationException;
	
	public Response updateServiceRenderStatus(Integer orderDetailsId)  throws ApplicationException;
	
	public Response searchPhlebotomyWorklistPatient(SearchCommonDto searchCommonDto) throws ApplicationException;
	

	  public Response getFilteredPhlebotomyWorklistPatient(SearchDto searchDto)
		      throws ApplicationException;
	  
		public Response searchSendToCRPatientlist(SearchCommonDto searchCommonDto) throws ApplicationException;
	
		public Response getFilteredSendToCRPatientlist(SearchDto searchDto)
			      throws ApplicationException;
		public Response searchLabGenralLabSampleNo(SearchCommonDto searchCommonDto) throws ApplicationException;
	/**
	*PhlebotomyWorklist Details
	* @version 1.1
	*/
	public Response v1GetPhlebotomyWorklistDetails(Integer orgId, Integer orgUnitId, Integer orderId)
			throws ApplicationException;
	
}
