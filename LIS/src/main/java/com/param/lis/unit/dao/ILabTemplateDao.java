package com.param.lis.unit.dao;

import java.util.List;



import com.param.lis.global.common.Response;
import com.param.lis.unit.dto.LabTemplateMasterDto;
import in.param.exception.ApplicationException;




@SuppressWarnings("rawtypes")

public interface ILabTemplateDao {
	
	public List<LabTemplateMasterDto> retrieveAllLabTemplates(Integer orgId,Integer orgUnitId,Integer offset, Integer recordPerPage);
	boolean createLabTemplate(LabTemplateMasterDto labTemplateMaster);
	boolean deleteLabTemplate(Integer labTemplateId);
	boolean updateLabTemplate(LabTemplateMasterDto labTemplateMaster);
	public LabTemplateMasterDto findLabTemplateById(Integer labTemplateId);
	public Response getLabTemplates(Integer orgId,Integer orgUnitId) throws ApplicationException;
	public Response getToTalLabTemplateMasterRecord(Integer orgId,Integer orgUnitId) throws ApplicationException;
	public Response activateInactivateLabTemplateMaster(Integer orgId, Integer labTemplateId, Character sampleStatus)
			throws ApplicationException;

	
	public Response getLabTemplatesByDoctor(Integer orgId, Integer orgUnitId, Integer doctorId, Integer templateTypeId)throws ApplicationException;

	public Response isTemplateExistOrNot(Integer orgId, Integer orgUnitId, Integer labTemplateTypeId)throws ApplicationException;


}
