package com.param.lis.unit.service;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.param.global.common.ICommonConstants;
import com.param.lis.global.common.CheckReportType;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.transaction.service.LabTemplateService;
import com.param.lis.unit.dao.ILabTemplateDao;
import com.param.lis.unit.dto.LabTemplateMasterDto;
import in.param.exception.ApplicationException;


@SuppressWarnings({ "unchecked", "rawtypes" })
@Service("labTemplateService")
@Transactional



public class LabTemplateServiceImpl implements LabTemplateService ,ICommonConstants, IError {


	@Autowired
	ILabTemplateDao labTemplateDao;

	@Autowired
	ServletContext context;
	
	@Autowired
	private SessionFactory sessionFactory;
	

	@Override
	@Transactional
	public List<LabTemplateMasterDto> retrieveAllLabTemplates(Integer orgId, Integer orgUnitId, Integer offset,
			Integer recordPerPage) {
		return labTemplateDao.retrieveAllLabTemplates(orgId, orgUnitId, offset, recordPerPage);
	}

	@Override
	public boolean createLabTemplate(LabTemplateMasterDto labTemplateMasterDto) {
		try {
		  /*if(!labTemplateMasterDto.getInputFile().isEmpty())
		  {
		    String fileUploadPath = "C:\\fileUpload";
            String originalFilename = labTemplateMasterDto.getInputFile().getOriginalFilename();
             File destinationFile = new File(context.getRealPath(fileUploadPath)+ File.separator + originalFilename); 
            String documentName = labTemplateMasterDto.getOrgId()+"_"+labTemplateMasterDto.getOrgUnitId()+"_"+labTemplateMasterDto.getTemplateCode();
            File destinationFile = new File(fileUploadPath + File.separator + documentName+"."+(originalFilename.substring(originalFilename.lastIndexOf(".")+1)));
            labTemplateMasterDto.getInputFile().transferTo(destinationFile);
            labTemplateMasterDto.setTemplatePath(destinationFile.getPath());
		  }*/
			/*try
			{
				GlobalCommonDto sampleMstDto = (GlobalCommonDto) sessionFactory.getCurrentSession()
						.getNamedQuery("GET_SAMPLE_BY_CODE")
						.setString("code", sampleMasterDto.getCode().trim().toLowerCase())
						.setInteger("orgId", sampleMasterDto.getOrgId())
						.setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).uniqueResult();
				if (sampleMstDto == null)
				{
					sampleMasterDto.setCreatedDate(new Date().getTime());
					Response response = iSampleMasterDao.addSampleMaster(sampleMasterDto);
					return response;
				} else
				{
					return new Response(ERROR, ERR_500, SAMPLE_CODE_EXISIS, null, null);
				}

			} catch (Exception e)
			{
				e.printStackTrace();
				return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
			}*/
			if(labTemplateMasterDto !=null) {
				LabTemplateMasterDto labTemplateMaster= (LabTemplateMasterDto) sessionFactory.getCurrentSession()
						.getNamedQuery("GET_LAB_TEMPLATE_BY_CODE")
						.setString("templateCode", labTemplateMasterDto.getTemplateCode().trim().toLowerCase())
						.setInteger("orgId", labTemplateMasterDto.getOrgId())
						.setResultTransformer(Transformers.aliasToBean(LabTemplateMasterDto.class)).uniqueResult();
				
				if (labTemplateMaster == null)
				{
					labTemplateMasterDto.setCreatedDate(new Date().getTime());
					return labTemplateDao.createLabTemplate(labTemplateMasterDto);
				} else
				{
					return false;
				}
			}else {
				return false;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean deleteLabTemplate(Integer labTemplateId) {
		return labTemplateDao.deleteLabTemplate(labTemplateId);
	}

	@Override
	public boolean updateLabTemplate(LabTemplateMasterDto labTemplateMasterDto) {
		try {
		 /* if(!labTemplateMasterDto.getInputFile().isEmpty())
          {
			String fileUploadPath = "C:\\fileUpload";
			String originalFilename = labTemplateMasterDto.getInputFile().getOriginalFilename();
			 File destinationFile = new File(context.getRealPath(fileUploadPath)+ File.separator + originalFilename); 
			String documentName = labTemplateMasterDto.getOrgId()+"_"+labTemplateMasterDto.getOrgUnitId()+"_"+labTemplateMasterDto.getTemplateCode();
			File destinationFile = new File(fileUploadPath + File.separator + documentName+"."+(originalFilename.substring(originalFilename.lastIndexOf(".")+1)));
			labTemplateMasterDto.getInputFile().transferTo(destinationFile);
			labTemplateMasterDto.setTemplatePath(destinationFile.getPath());
			System.out.println(labTemplateMasterDto.getTemplatePath());
          }*/
			return labTemplateDao.updateLabTemplate(labTemplateMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public LabTemplateMasterDto findLabTemplateById(Integer labTemplateId) {
		return labTemplateDao.findLabTemplateById(labTemplateId);
	}

  @Override
  @Transactional
  public String readTemplateData(MultipartFile inputFile) {
    try {
      String templateData = null;
      if(!inputFile.isEmpty())
      {
        File destinationFile = new File(inputFile.getOriginalFilename());
        FileInputStream fis = new FileInputStream(destinationFile.getPath());
        XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));
        XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc);
        templateData =  extractor.getText();
        extractor.close();
        return templateData;
      }
      else {
        return templateData;
      }
    } catch (Exception e)
    {
        e.printStackTrace();
        return "";
    }
  }


  @Override
  @Transactional
  public Response getLabTemplates(Integer orgId, Integer orgUnitId) throws ApplicationException {
    try
    {
        return labTemplateDao.getLabTemplates(orgId,orgUnitId);
    } catch (Exception e)
    {
        e.printStackTrace();
        return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }


@Override
@Transactional
public Response getToTalLabTemplateMasterRecord(Integer orgId,Integer orgUnitId) throws ApplicationException {
	try
	{
		return labTemplateDao.getToTalLabTemplateMasterRecord(orgId, orgUnitId);
	} catch (Exception e)
	{
		e.printStackTrace();
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}
}

@Override
@Transactional
public Response activateInactivateLabTemplateMaster(Integer orgId, Integer labTemplateId, Character sampleStatus)
		throws ApplicationException {
	try
	{
		return labTemplateDao.activateInactivateLabTemplateMaster(orgId, labTemplateId, sampleStatus);
	} catch (Exception e)
	{
		e.printStackTrace();
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}
}


@SuppressWarnings("static-access")
@Override


@Transactional
public Response getTemplateTypes(Integer orgId, Integer labTemplateId) throws ApplicationException {

	return new Response(ERROR, ERR_500, ERR_500_MESSAGE, CheckReportType.getTemplateType(), null);
}
@Override
public Response getLabTemplatesByDoctor(Integer orgId, Integer orgUnitId, Integer doctorId, Integer templateTypeId)
    throws ApplicationException {
  try
  {
      return labTemplateDao.getLabTemplatesByDoctor(orgId, orgUnitId, doctorId, templateTypeId);
  } catch (Exception e)
  {
      e.printStackTrace();
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
  }

}

@Override
@Transactional
public Response isTemplateExistOrNot(Integer orgId, Integer orgUnitId, Integer labTemplateTypeId)
		throws ApplicationException {
	try
	{
		return labTemplateDao.isTemplateExistOrNot(orgId, orgUnitId, labTemplateTypeId);
	} catch (Exception e)
	{
		e.printStackTrace();
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}
}




}
