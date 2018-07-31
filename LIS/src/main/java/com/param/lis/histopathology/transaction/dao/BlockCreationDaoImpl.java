package com.param.lis.histopathology.transaction.dao;

import java.math.BigInteger;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.param.entity.lis.histo.TBlockMaster;
import com.param.entity.lis.histo.TGrossMaster;
import com.param.entity.lis.transaction.LabSampleDetailsMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.ITransactionConstants;
import com.param.lis.global.common.Response;
import com.param.lis.histopathology.transaction.dto.HistoParamDto;
import com.param.lis.histopathology.transaction.dto.TGrossMasterDto;
import com.param.lis.histopathology.transaction.dto.TSubSpecimanMasterDto;
import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings(
{ "rawtypes", "unchecked" })
public class BlockCreationDaoImpl  extends GenericDao<TBlockMaster, Integer>
implements IBlockCreationDao, ICommonConstants, IError,ITransactionConstants{

	final static Logger logger = Logger.getLogger(BlockCreationDaoImpl.class);
	
	public BlockCreationDaoImpl()
	{
		super(TBlockMaster.class);
	}
	

	
	@Autowired
	private DozerBeanMapper mapper;

	@Override
	public Response getBlockCreationList(HistoParamDto histoParamDto) throws ApplicationException {
		try
		{
			List<TSubSpecimanMasterDto> listTSubSpecimanMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_GROSS_SPCIMEN_LIST")
					.setInteger("orgId", histoParamDto.getOrgId())
					.setInteger("orgUnitId", histoParamDto.getOrgUnitId())
					.setInteger("sampleStatusId", GROSS_CREATED)
					.setFirstResult(histoParamDto.getOffset() != null ? histoParamDto.getOffset() : 0)
					.setMaxResults(histoParamDto.getRecordPerPage() != null ? histoParamDto.getRecordPerPage() : 10)
					.setResultTransformer(Transformers.aliasToBean(TSubSpecimanMasterDto.class)).list();
			
			if(listTSubSpecimanMasterDto.isEmpty())
			{
				return new Response(ERROR, ERR_500, "No Speciman Records Found.", null, null);
			}
			else{
				return new Response(SUCCESS, SUCCESS_200, null, listTSubSpecimanMasterDto, null);
			}
			
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response getBlockCreationListCount(HistoParamDto histoParamDto) throws ApplicationException {
		try
		{
			Long subSpeciamanCount = (Long) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_GROSS_SPCIMEN_LIST_COUNT")
					.setInteger("orgId", histoParamDto.getOrgId())
					.setInteger("orgUnitId", histoParamDto.getOrgUnitId())
					.setInteger("sampleStatusId", GROSS_CREATED)
					.uniqueResult();
			if (subSpeciamanCount!=null)
			{
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, subSpeciamanCount);
			} else
			{
				return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, BigInteger.ZERO);
			}

		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response getCreatedGross(TSubSpecimanMasterDto tSubSpecimanMasterDto) throws ApplicationException
	{
		try
		{
			
			List<TGrossMasterDto> listTGrossMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_GROSS_LIST_BY_SUBSPECIAMAN")
					.setInteger("orgId", tSubSpecimanMasterDto.getOrgId())
					.setInteger("orgUnitId", tSubSpecimanMasterDto.getOrgUnitId())
					.setInteger("tSubSpecimanId", tSubSpecimanMasterDto.gettSubSpecimanId())
					.setResultTransformer(Transformers.aliasToBean(TGrossMasterDto.class)).list();
			
			if(listTGrossMasterDto.isEmpty())
			{
				return new Response(ERROR, ERR_500, "No Gross Found..", null, null);
			}
			else{
				return new Response(SUCCESS, SUCCESS_200, null, listTGrossMasterDto, null);
			}

		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response creteBlocks(List<TGrossMasterDto> listTGrossMasterDto) throws ApplicationException
	{
		try
		{
		  if(!listTGrossMasterDto.isEmpty())
		  {
			  LabSampleDetailsMaster labSampleDetailsMaster = new LabSampleDetailsMaster(); 
			  for (Iterator iterator =listTGrossMasterDto.iterator(); iterator.hasNext();) 
			  {
				  TGrossMasterDto tGrossMasterDto = (TGrossMasterDto) iterator.next();
				  labSampleDetailsMaster.setLabSampleDtlsId(tGrossMasterDto.getLabSampleDtlsId());
				 
				  TGrossMaster tGrossMaster = mapper.map(tGrossMasterDto, TGrossMaster.class,"TGrossMasterDtoToTGrossMaster");
				 for (Iterator iterator1 =tGrossMaster.getListTBlockMaster().iterator(); iterator1.hasNext();) 
				  {
					TBlockMaster tBlockMaster = (TBlockMaster) iterator1.next();
					tBlockMaster.setLabSampleDtlsId(tGrossMaster.getLabSampleDtlsId());
					tBlockMaster.settGrossId(tGrossMaster.gettGrossId());
					tBlockMaster.setCreatedDate(new Date().getTime());
					
					/**Code for Setting Object for update Lab Sample Status*/
					labSampleDetailsMaster.setUpdatedBy(tBlockMaster.getCreatedBy());
					labSampleDetailsMaster.setUpdatedDate(tBlockMaster.getCreatedDate());
					sessionFactory.getCurrentSession().save(tBlockMaster);
				  }
			
			  }
			  return new Response(SUCCESS, SUCCESS_200, BLOCK_CREATE_SUCCESS, null, labSampleDetailsMaster);
		  }
		  else 
		  {
			  return new Response(ERROR, ERR_500, BLOCK_CREATE_FAIL, null, null);
		  }
			
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	

}
