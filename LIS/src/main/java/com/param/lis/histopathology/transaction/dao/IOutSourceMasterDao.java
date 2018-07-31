package com.param.lis.histopathology.transaction.dao;

import java.util.List;
import com.param.entity.lis.histo.OutSourceMaster;
import com.param.entity.lis.histo.TSpecimanMaster;
import com.param.lis.global.common.Response;
import com.param.lis.global.common.SearchDto;
import com.param.lis.histopathology.transaction.dto.HistoParamDto;
import com.param.lis.histopathology.transaction.dto.OutSourceMasterDto;
import com.param.lis.histopathology.transaction.dto.TSpecimanMasterDto;
import com.param.lis.transaction.dto.SearchCommonDto;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings({"rawtypes"})
public interface IOutSourceMasterDao extends IGenericDao<OutSourceMaster, Integer>
{
	public Response listOutSourceMaster(OutSourceMasterDto outSourceMasterDto)throws ApplicationException;
	public Response getOutSourceMasterCount(OutSourceMasterDto outSourceMasterDto) throws ApplicationException;
	public Response saveOutSourceMasterDetails(OutSourceMasterDto outSourceMasterDto) throws ApplicationException;
	
	public Response searchOutSourcelistPatient(SearchCommonDto searchCommonDto) throws ApplicationException;
	public Response getFilteredOutSourceList(SearchDto searchDto) throws ApplicationException;
	public Response getFilteredOutSourceCommonList(SearchDto searchDto) throws ApplicationException;
	/*public Response saveSpecimanDetails(TSpecimanMasterDto tSpecimanMasterDto) throws ApplicationException;
	*/

}
