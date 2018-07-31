package com.param.lis.unit.dao;

import java.math.BigInteger;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.param.entity.lis.unit.RackMaster;
import com.param.entity.lis.unit.RackShelfMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.unit.dto.RackMasterDto;
import com.param.lis.unit.dto.RackShelfMasterDto;
import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings(
{ "rawtypes", "unchecked" })
public class RackMasterDaoImpl extends GenericDao<RackMaster, Integer>
		implements IRackMasterDao, ICommonConstants, IError
{

	public RackMasterDaoImpl()
	{
		super(RackMaster.class);
	}

	
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private DozerBeanMapper mapper;

	final static Logger logger = Logger.getLogger(RackMaster.class);

	@Override
	public Response addRackMaster(RackMasterDto rackMasterDto) throws ApplicationException
	{
		/*try
		{
			RackMaster techniqueMaster = mapper.map(rackMasterDto, RackMaster.class,
					"RackMasterDtoTORackMaster");
			RackMaster rackMst = save(techniqueMaster);
			if (rackMst != null)
			{
				return new Response(SUCCESS, SUCCESS_200, RACK_ADD_SUCC, null, null);
			} else
			{
				return new Response(SUCCESS, SUCCESS_200, RACK_ADD_FAIL, null, null);
			}
		}catch(HibernateException exception){
			logger.error("Exection", exception);
		} catch (Exception e)
		{
			logger.error("Exection", e);
		}
		return new Response(ERROR, ERR_500,RACK_ADD_FAIL, null, null);*/
		
		
		
		Date date = new Date();
		rackMasterDto.setCreatedDate(date.getTime());
		rackMasterDto.setStatus('A');
		RackMaster rackMaster = mapper.map(rackMasterDto, RackMaster.class,
				"RackMasterDtoTORackMaster");
		
		Integer rackMst= (Integer ) sessionFactory.getCurrentSession().save(rackMaster);
		try
		{
		
		  if(!rackMasterDto.getListRackShelfMasterDto().isEmpty())
			  {		
			  
				  for (Iterator iterator = rackMasterDto.getListRackShelfMasterDto().iterator(); iterator.hasNext();) 
				  {
					  RackShelfMasterDto rackShelfMasterDto = (RackShelfMasterDto) iterator.next();
					  RackShelfMaster master= new RackShelfMaster();
					  master.setRackId(rackMst);
					  master.setShelfCode(rackShelfMasterDto.getShelfCode());
					  master.setShelfName(rackShelfMasterDto.getShelfName());
					  master.setOrgId(rackShelfMasterDto.getOrgId());
					  master.setOrgUnitId(rackShelfMasterDto.getOrgUnitId());
					  master.setUpdatedBy(rackShelfMasterDto.getUpdatedBy());
					  master.setUpdatedDate(rackShelfMasterDto.getUpdatedDate());
					  master.setCreatedBy(rackShelfMasterDto.getCreatedBy());
					  master.setCreatedDate(date.getTime());
					  master.setStatus('A');
					  master.setIsDeleted('N');
	  				  sessionFactory.getCurrentSession().save(master);
				  }
				  return new Response(SUCCESS, SUCCESS_200, RACK_ADD_SUCC, null, null);
			  }
		  else 
		  {
			  return new Response(ERROR, ERR_500,RACK_ADD_FAIL, null, null);
		  }
			
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500,RACK_ADD_FAIL, null, null);
		}
	}

	@Override
	public Response getRackMasterById(Integer orgId,Integer orgUnitId,Integer rackId) throws ApplicationException
	{
		/*
		try
		{
			RackMasterDto rackMaster = (RackMasterDto) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_RACK_MASTER_BY_ID")
					.setInteger("rackId", rackId)
					.setInteger("orgId", orgId)
					.setInteger("orgUnitId", orgUnitId)
                    .setResultTransformer(Transformers.aliasToBean(RackMasterDto.class))					
					.uniqueResult();
			
			return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, rackMaster);

		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, RACK_NOT_FOUND, null, null);
		}*/
		
		try {
			RackMaster rackMaster = (RackMaster)sessionFactory.getCurrentSession().get(RackMaster.class, rackId);
			if(rackId!=null)
			{
				RackMasterDto labRackMasterDto =mapper.map(rackMaster, RackMasterDto.class,
						"RackMasterDtoTORackMaster");
				
				  List<RackShelfMasterDto> listRackShelfMasterDto =
				          sessionFactory.getCurrentSession().getNamedQuery("GET_RACK_SHELF_MASTER_LIST")
			              .setInteger("rackId", rackId)
				          .setResultTransformer(Transformers.aliasToBean(RackShelfMasterDto.class)).list();

				  labRackMasterDto.setListRackShelfMasterDto(listRackShelfMasterDto);
				
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE,null , labRackMasterDto);
			}
			     
			else {
				return new Response(ERROR, ERR_500, RACK_NOT_FOUND, null, null);
			}
		}catch(Exception e) {
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, RACK_NOT_FOUND, null, null);
		}
		
		
	}

	@Override
	public Response updateRackMaster(RackMasterDto rackMasterDto) throws ApplicationException
	{
		/*try
		{
			RackMaster rackMaster = mapper.map(rackMasterDto, RackMaster.class,
					"RackMasterDtoTORackMaster");
			RackMaster techniqueMat = update(rackMaster);
			if (techniqueMat != null)
			{
				return new Response(SUCCESS, SUCCESS_200, RACK_UPDATE_SUCC, null, null);
			} else
			{
				return new Response(SUCCESS, SUCCESS_200, RACK_UPDATE_FAIL, null, null);
			}
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, RACK_ADD_FAIL, null, null);
		}*/
		
		
		try {
			if(rackMasterDto!=null)
			{
				Date date = new Date();
				rackMasterDto.setUpdatedDate(date.getTime());
				RackMaster rackMasterMst = mapper.map(rackMasterDto, RackMaster.class,
						"RackMasterDtoTORackMaster");
				sessionFactory.getCurrentSession().update(rackMasterMst) ;
				
				
				Integer rackMstUpdate = (Integer) sessionFactory.getCurrentSession()
						.getNamedQuery("UPDATE_BY_RANK_SHELF_ID")
						.setInteger("rackId", rackMasterDto.getRackId())
						.setInteger("orgId", rackMasterDto.getOrgId())
						.setInteger("orgUnitId", rackMasterDto.getOrgUnitId())
						.executeUpdate();
				
				if(rackMstUpdate >0) {
					
				try
				{
				
				  if(!rackMasterDto.getListRackShelfMasterDto().isEmpty())
					  {		
					  
						  for (Iterator iterator = rackMasterDto.getListRackShelfMasterDto().iterator(); iterator.hasNext();) 
						  {
							  RackShelfMasterDto rackShelfMasterDto = (RackShelfMasterDto) iterator.next();
							  RackShelfMaster master= new RackShelfMaster();
							  master.setRackId(rackShelfMasterDto.getRackId());
							  master.setShelfCode(rackShelfMasterDto.getShelfCode());
							  master.setShelfName(rackShelfMasterDto.getShelfName());
							  master.setOrgId(rackShelfMasterDto.getOrgId());
							  master.setOrgUnitId(rackShelfMasterDto.getOrgUnitId());
							  master.setStatus(rackShelfMasterDto.getStatus());
							  master.setUpdatedBy(rackShelfMasterDto.getUpdatedBy());
							  master.setUpdatedDate(rackShelfMasterDto.getUpdatedDate());
							  master.setCreatedBy(rackShelfMasterDto.getCreatedBy());
							  master.setIsDeleted('N');
							  master.setCreatedDate(date.getTime());
			  				  sessionFactory.getCurrentSession().save(master);
						  }
						  return new Response(SUCCESS, SUCCESS_200, RACK_UPDATE_SUCC, null, null);
					  }
				  else 
				  {
						return new Response(ERROR, ERR_500, RACK_NOT_FOUND, null, null);
				  }
					
				} catch (Exception e)
				{
					logger.error("Exection", e);
					return new Response(ERROR, ERR_500, RACK_NOT_FOUND, null, null);
				}
			}  
			}else {
				
				return new Response(ERROR, ERR_500, RACK_NOT_FOUND, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, RACK_NOT_FOUND, null, null);
		}
		return new Response(ERROR, ERR_500, RACK_NOT_FOUND, null, null);
	}


	
	@Override
	public Response ActivateInactivateRackMaster(Integer orgId, Integer rackId, Character rackStatus)
			throws ApplicationException
	{/*
		try
		{
			RackMaster rackMaster = findById(rackId);
			if (rackMaster.getRackId()!= 0)
			{
				rackMaster.setStatus(rackStatus);
				RackMaster rackMst = update(rackMaster);
				if (rackMst.getStatus() == ACTIVE)
				{
					return new Response(SUCCESS, SUCCESS_200, RACK_ACTIVATE_SUCC, null, null);
				} else if (rackMst.getStatus() == INACTIVE)
				{
					return new Response(SUCCESS, SUCCESS_200, RACK_INACTIVATE_SUCC, null, null);
				} else
				{
					if (rackStatus == ACTIVE)
					{
						return new Response(SUCCESS, SUCCESS_200, RACK_ACTIVATE_FAIL, null, null);
					} else
					{
						return new Response(SUCCESS, SUCCESS_200, RACK_INACTIVATE_FAIL, null, null);
					}
				}
			} else
			{
				return new Response(SUCCESS, SUCCESS_200, RACK_NOT_FOUND, null, null);
			}

		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, RACK_NOT_FOUND, null, null);
		}*/
		
		try
		{
			RackMaster rackMaster = (RackMaster) sessionFactory.getCurrentSession().get(RackMaster.class, rackId);
			if (rackMaster.getRackId() != 0)
			{
			
				rackMaster.setStatus(rackStatus);
				 sessionFactory.getCurrentSession().update(rackMaster);
				 
					Integer labTemplateMstUpdate = (Integer) sessionFactory.getCurrentSession()
							.getNamedQuery("UPDATE_STATUS_RANK_SHELF_MASTER")
							.setInteger("rackId", rackId)
							.setCharacter("rackStatus", rackStatus) 
							.executeUpdate();
					
				 
				 if(labTemplateMstUpdate>0) {
					 if (rackStatus == ACTIVE)
						{
						 return new Response(SUCCESS, SUCCESS_200, RACK_ACTIVATE_SUCC, null, null);
						} else if (rackStatus == INACTIVE)
						{
							return new Response(SUCCESS, SUCCESS_200, RACK_INACTIVATE_SUCC, null, null);
						} else
						{
							if (rackStatus == ACTIVE)
							{
								return new Response(SUCCESS, SUCCESS_200, RACK_ACTIVATE_FAIL, null, null);
							} else
							{
								return new Response(SUCCESS, SUCCESS_200, RACK_INACTIVATE_FAIL, null, null);
							}
						}
					} else
					{
						return new Response(SUCCESS, SUCCESS_200, RACK_INACTIVATE_FAIL, null, null);
					}
				 }else {
					 return new Response(SUCCESS, SUCCESS_200, RACK_INACTIVATE_FAIL, null, null);
				 }
				

		} catch (Exception e)
		{
			//logger.error("Exection", e);
			return new Response(ERROR, ERR_500, SAMPLE_NOT_FOUND, null, null);
		}
	}

	@Override
	public Response listRackMaster(Integer orgId,Integer orgUnitId, Integer offset, Integer recordPerPage) throws ApplicationException
	{
		try
		{
			List<RackMasterDto> listRackMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_RACK_MASTER_LIST").setInteger("orgId", orgId)
					.setInteger("orgUnitId", orgUnitId)
					.setFirstResult(offset != null ? offset : 0)
					.setMaxResults(recordPerPage != null ? recordPerPage : 10)
					.setResultTransformer(Transformers.aliasToBean(RackMasterDto.class)).list();
			
			if(listRackMasterDto.isEmpty())
			{
				return new Response(ERROR, ERR_500, RACK_RECORDS_NOT_FOUND, null, null);
			}
			else{
				return new Response(SUCCESS, SUCCESS_200, null, listRackMasterDto, null);
			}
			
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response getToTalRackMasterRecord(Integer orgId,Integer orgUnitId) throws ApplicationException
	{
		try
		{
			BigInteger rackMasterTotalRecordCount = (BigInteger) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_TOTAL_RACK_MASTER_RECORD")
					 .setInteger("orgId", orgId)
					 .setInteger("orgUnitId", orgUnitId).uniqueResult();
			if (rackMasterTotalRecordCount.compareTo(BigInteger.ZERO) == 1)
			{
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, rackMasterTotalRecordCount);
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

}
