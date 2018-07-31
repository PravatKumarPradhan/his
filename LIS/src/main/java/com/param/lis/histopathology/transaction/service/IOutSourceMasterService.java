package com.param.lis.histopathology.transaction.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.param.lis.global.common.Response;
import com.param.lis.global.common.SearchDto;
import com.param.lis.histopathology.transaction.dto.HistoParamDto;
import com.param.lis.histopathology.transaction.dto.OutSourceMasterDto;
import com.param.lis.histopathology.transaction.dto.TSpecimanMasterDto;
import com.param.lis.transaction.dto.SearchCommonDto;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IOutSourceMasterService 
{
	public Response listOutSourceMaster(OutSourceMasterDto outSourceMasterDto)throws ApplicationException;
	public Response getOutSourceMasterCount(OutSourceMasterDto outSourceMasterDto) throws ApplicationException;
	public Response saveOutSourceMasterDetails(List<OutSourceMasterDto> outSourceMasterDto) throws ApplicationException;
	public String uploadDocumentData(MultipartFile inputFile);
	public Response searchOutSourcelistPatient(SearchCommonDto searchCommonDto) throws ApplicationException;
	public Response getFilteredOutSourceList (SearchDto searchDto) throws ApplicationException;
	public Response getFilteredOutSourceCommonList(SearchDto searchDto) throws ApplicationException;
	
}
