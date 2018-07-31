package com.param.lis.histopathology.transaction.service;


import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.param.entity.lis.histo.OutSourceMaster;
import com.param.entity.lis.micro.MicrobioResultEntryMaster;
import com.param.entity.lis.transaction.LabSampleDetailsMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.ITransactionConstants;
import com.param.lis.global.common.Response;
import com.param.lis.global.common.SearchDto;
import com.param.lis.histopathology.transaction.dao.IOutSourceMasterDao;
import com.param.lis.histopathology.transaction.dto.OutSourceMasterDto;
import com.param.lis.microbiology.transaction.dao.IincubationObservationDao;
import com.param.lis.microbiology.transaction.dto.MicrobioResultEntryMasterDto;
import com.param.lis.microbiology.transaction.service.IMicrobiologyService;
import com.param.lis.transaction.dto.LabSampleDetailsMasterDto;
import com.param.lis.transaction.dto.SearchCommonDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings(
{ "rawtypes", "unchecked" })
public class OutSourceMasterServiceImpl  implements IOutSourceMasterService, ICommonConstants,ITransactionConstants ,IError
{
	
	final static Logger logger = Logger.getLogger(OutSourceMasterServiceImpl.class);
	
	@Autowired
	IOutSourceMasterDao iOutSourceMasterDao;
	
	  @Autowired
	  IMicrobiologyService iMicrobiologyService;

	
	
	@Override
	@Transactional
	public Response listOutSourceMaster(OutSourceMasterDto outSourceMasterDto) throws ApplicationException 
	{
		try
		{
			return iOutSourceMasterDao.listOutSourceMaster(outSourceMasterDto);
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getOutSourceMasterCount(OutSourceMasterDto outSourceMasterDto) throws ApplicationException 
	{
		try
		{
			return iOutSourceMasterDao.getOutSourceMasterCount(outSourceMasterDto);
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response saveOutSourceMasterDetails(List<OutSourceMasterDto> listOutSourceMasterDto) throws ApplicationException 
	{/*
		try
		{
			//return iOutSourceMasterDao.saveOutSourceMasterDetails(outSourceMasterDto);
			
			Response<MicrobioResultEntryMaster, Integer> microResultRes =  iOutSourceMasterDao.saveOutSourceMasterDetails(outSourceMasterDto);
			 if(microResultRes.getCode().equals(SUCCESS_200))
			 {
				 MicrobioResultEntryMasterDto microbioResultEntryMasterDto= new MicrobioResultEntryMasterDto();
				 microbioResultEntryMasterDto.setCreatedBy(outSourceMasterDto.getCreatedBy());
				 microbioResultEntryMasterDto.setSampleStatusId(OUT_SOURCE_SAMPLE);
				 microbioResultEntryMasterDto.setLabSampleDtlsId(outSourceMasterDto.getLabSampleDtlsId());
				Response<LabSampleDetailsMaster, Integer> sampleStatusRes = iincubationObservationDao.updateLabSampleDetailsMaster(microbioResultEntryMasterDto);
				if(sampleStatusRes.getCode().equals(SUCCESS_200))
				{
					return microResultRes;
				}
			 }
			
			
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);*/
		
		try
		{
		  if(!listOutSourceMasterDto.isEmpty())
		  {
			  Response<LabSampleDetailsMaster, Integer> sampleStatusRes =null;
			  for (Iterator iterator =listOutSourceMasterDto.iterator(); iterator.hasNext();) 
			  {
				 
				  OutSourceMasterDto outSourceMasterDto = (OutSourceMasterDto) iterator.next();
				  Response<MicrobioResultEntryMaster, Integer> 
				  microResultRes =  iOutSourceMasterDao.saveOutSourceMasterDetails(outSourceMasterDto);
				  if(microResultRes.getCode().equals(SUCCESS_200))
					 {
						LabSampleDetailsMasterDto labSampleDetailsMasterDto 
						= new LabSampleDetailsMasterDto();
						labSampleDetailsMasterDto.setCreatedBy(outSourceMasterDto.getCreatedBy());
						labSampleDetailsMasterDto.setSampleStatusId(OUT_SOURCE_SAMPLE);
						labSampleDetailsMasterDto.setLabSampleDtlsId(outSourceMasterDto.getLabSampleDtlsId());
						sampleStatusRes = iMicrobiologyService
								.updateLabSampleDetailsMaster(labSampleDetailsMasterDto);
						  if(sampleStatusRes.getCode().equals(SUCCESS_200))
							{
								//return new Response(SUCCESS, SUCCESS_200, "Out Source Details Added 								Successfully.", null, null);
							}
					 }
				 
			    }
			
			
			  return new Response(SUCCESS, SUCCESS_200, "Out Source Details Added Successfully.", null,  null);
		  }
		  else 
		  {
			  return new Response(ERROR, ERR_500, "Failed to Added Out Source Details.", null, null);
		  }
			
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	
	
	}
	
	 @Override
	 @Transactional
	  public String uploadDocumentData(MultipartFile inputFile) {
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
	public Response searchOutSourcelistPatient(SearchCommonDto searchCommonDto) throws ApplicationException {
		try
		{
			return iOutSourceMasterDao.searchOutSourcelistPatient(searchCommonDto);
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getFilteredOutSourceList(SearchDto searchDto) throws ApplicationException {
		try
		{
			return iOutSourceMasterDao.getFilteredOutSourceList(searchDto);
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getFilteredOutSourceCommonList(SearchDto searchDto) throws ApplicationException {
		try
		{
			return iOutSourceMasterDao.getFilteredOutSourceCommonList(searchDto);
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}




	

}
