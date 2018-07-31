package com.param.lis.transaction.service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;



import com.param.lis.global.common.Response;
import com.param.lis.unit.dto.LabTemplateMasterDto;
import in.param.exception.ApplicationException;





@SuppressWarnings("rawtypes")

public interface LabTemplateService {
	
	public List<LabTemplateMasterDto> retrieveAllLabTemplates(Integer orgId,Integer orgUnitId,Integer offset, Integer recordPerPage);
	boolean createLabTemplate(LabTemplateMasterDto labTemplateMasterDto);
	boolean deleteLabTemplate(Integer labTemplateId);
	boolean updateLabTemplate(LabTemplateMasterDto labTemplateMasterDto);
	public  LabTemplateMasterDto findLabTemplateById(Integer labTemplateId);
	public String readTemplateData(MultipartFile inputFile);

	public Response getLabTemplates(Integer orgId,Integer orgUnitId) throws ApplicationException;

	public Response activateInactivateLabTemplateMaster(Integer orgId, Integer labTemplateId, Character sampleStatus)
			throws ApplicationException;

	
	public Response getLabTemplatesByDoctor(Integer orgId,Integer orgUnitId ,Integer doctorId, Integer templateTypeId)throws ApplicationException;

	public Response getToTalLabTemplateMasterRecord(Integer orgId,Integer orgUnitId) throws ApplicationException;


	public Response getTemplateTypes (Integer orgId, Integer labTemplateId)  throws ApplicationException;
	
	public Response isTemplateExistOrNot(Integer orgId, Integer orgUnitId, Integer labTemplateTypeId)throws ApplicationException;
}
