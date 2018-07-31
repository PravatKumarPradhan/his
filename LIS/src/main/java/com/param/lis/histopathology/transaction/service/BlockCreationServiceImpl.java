package com.param.lis.histopathology.transaction.service;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.param.entity.lis.transaction.LabSampleDetailsMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.ITransactionConstants;
import com.param.lis.global.common.Response;
import com.param.lis.histopathology.transaction.dao.IBlockCreationDao;
import com.param.lis.histopathology.transaction.dao.ISpecimanReceiptDao;
import com.param.lis.histopathology.transaction.dto.HistoParamDto;
import com.param.lis.histopathology.transaction.dto.TGrossMasterDto;
import com.param.lis.histopathology.transaction.dto.TSubSpecimanMasterDto;
import in.param.exception.ApplicationException;


@Service
@SuppressWarnings({"rawtypes", "unchecked" })
public class BlockCreationServiceImpl implements IBlockCreationService, ICommonConstants,ITransactionConstants ,IError{

	
	final static Logger logger = Logger.getLogger(BlockCreationServiceImpl.class);
	
	@Autowired
	private IBlockCreationDao iBlockCreationDao;
	
	@Autowired
	private ISpecimanReceiptDao iSpecimanReceiptDao;

	@Override
	@Transactional
	public Response getBlockCreationList(HistoParamDto histoParamDto) throws ApplicationException {
		try
		{
			return iBlockCreationDao.getBlockCreationList(histoParamDto);
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getBlockCreationListCount(HistoParamDto histoParamDto) throws ApplicationException {
		try
		{
			return iBlockCreationDao.getBlockCreationListCount(histoParamDto);
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getCreatedGross(TSubSpecimanMasterDto tSubSpecimanMasterDto) throws ApplicationException {
		try
		{
			return iBlockCreationDao.getCreatedGross(tSubSpecimanMasterDto);
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response creteBlocks(List<TGrossMasterDto> listTGrossMasterDto) throws ApplicationException {
		try
		{
			Response grossRes = iBlockCreationDao.creteBlocks(listTGrossMasterDto);
			if(grossRes.getCode().equals(SUCCESS_200)&&grossRes.getObject()!=null)
			{
				LabSampleDetailsMaster labSampleDetailsMaster = (LabSampleDetailsMaster)grossRes.getObject();
				labSampleDetailsMaster.setSampleStatusId(BLOCK_CREATED);
				Response labSampleDtlsRes =  iSpecimanReceiptDao.updateLabSampleDetailsMaster(labSampleDetailsMaster);
				if(labSampleDtlsRes.getCode().equals(SUCCESS_200))
				{
					return new Response(SUCCESS, SUCCESS_200, BLOCK_CREATE_SUCCESS, null, null);
				}
				else {
					return new Response(ERROR, ERR_500, BLOCK_CREATE_FAIL, null, null);
				}
			}
			else
			{
				return new Response(ERROR, ERR_500, GROSS_CREATE_FAIL, null, null);
			}
			
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}



}
