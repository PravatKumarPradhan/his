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

import com.param.entity.lis.unit.TPanelDetailsMaster;
import com.param.entity.lis.unit.TPanelMaster;
import com.param.lis.common.dto.CommonDto;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.unit.dto.TPanelDetailsMasterDto;
import com.param.lis.unit.dto.TPanelMasterDto;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings(
{ "rawtypes", "unchecked" })
public class PanelTestDaoImpl extends GenericDao<TPanelMaster, Integer>
implements IPanelTestDao, ICommonConstants, IError{

  public PanelTestDaoImpl() {
    super(TPanelMaster.class);
  }
  
  @Autowired
  private DozerBeanMapper mapper;
  
  @Autowired
  private SessionFactory sessionFactory;
  
  final static Logger logger = Logger.getLogger(PanelTestDaoImpl.class);

  @Override
  public Response addPanel(TPanelMasterDto tpanelTestMpprMasterDto)
      throws ApplicationException 
  {
    
    try
    {
      Integer count = 0;
      tpanelTestMpprMasterDto.setCreatedDate(new Date().getTime());
      TPanelMaster tpanelTestMpprMaster = mapper.map(tpanelTestMpprMasterDto, TPanelMaster.class, "TPanelMasterDtoToTPanelMaster");
      TPanelMaster tpanelTestMpprMst = save(tpanelTestMpprMaster);
      
      
      for (Iterator iterator = tpanelTestMpprMst.getListTPanelDetailsMaster().iterator(); iterator.hasNext();) {
    	  TPanelDetailsMaster tpanelTestMpprDetailsMaster = (TPanelDetailsMaster) iterator.next();
		        tpanelTestMpprDetailsMaster.setPanelId(tpanelTestMpprMst.getPanelId());
		        tpanelTestMpprDetailsMaster.setCreatedDate(new Date().getTime());
		        sessionFactory.getCurrentSession().save(tpanelTestMpprDetailsMaster);
		        count++;
      }
      if(count>0)
      {
        return new Response(SUCCESS, SUCCESS_200, PANEL_ADD_SUCC, null, null);
      }
      else {
        return new Response(ERROR, ERR_500,PANEL_ADD_FAIL, null, null);
      }
        
    } catch (Exception e)
    {
        logger.error("Exection", e);
        return new Response(ERROR, ERR_500,PANEL_ADD_FAIL, null, null);
    }
   
    
  }

  @Override
  public Response updatePanel(TPanelMasterDto tpanelTestMpprMasterDto)
      throws ApplicationException {
    try
    {
      Integer count = 0;
      tpanelTestMpprMasterDto.setUpdatedDate(new Date().getTime());
      TPanelMaster tpanelTestMpprMaster = mapper.map(tpanelTestMpprMasterDto, TPanelMaster.class, "TPanelMasterDtoToTPanelMaster");
      TPanelMaster tpanelTestMpprMst = update(tpanelTestMpprMaster);
      
      Integer tpanelTestMpprDetailsMst = (Integer) sessionFactory.getCurrentSession()
				.getNamedQuery("SET_MAPPR_DETAILS_STATUS_BEFORE_UPDATE")
				.setInteger("panelId", tpanelTestMpprMasterDto.getPanelId())
				.setInteger("orgId", tpanelTestMpprMasterDto.getOrgId())
				.setInteger("orgUnitId", tpanelTestMpprMasterDto.getOrgUnitId())
				.executeUpdate();
      
  	if(tpanelTestMpprDetailsMst >0) {
  		for (Iterator iterator = tpanelTestMpprMst.getListTPanelDetailsMaster().iterator(); iterator.hasNext();) {
  			TPanelDetailsMaster tpanelTestMpprDetailsMaster = (TPanelDetailsMaster) iterator.next();
	        tpanelTestMpprDetailsMaster.setPanelId(tpanelTestMpprMst.getPanelId());
	        tpanelTestMpprDetailsMaster.setCreatedDate(new Date().getTime());
	        sessionFactory.getCurrentSession().save(tpanelTestMpprDetailsMaster);
	        count++;
  	        count++;
  	      }
  	      if(count>0)
  	      {
  	        return new Response(SUCCESS, SUCCESS_200, PANEL_UPDATE_SUCC, null, null);
  	      }
  	      else {
  	        return new Response(ERROR, ERR_500,PANEL_UPDATE_FAIL, null, null);
  	      }
  	}else {
  		 return new Response(ERROR, ERR_500,PANEL_UPDATE_FAIL, null, null);
  	}
      
        
    } catch (Exception e)
    {
        logger.error("Exection", e);
        return new Response(ERROR, ERR_500,PANEL_ADD_FAIL, null, null);
    }
  }
  
  @Override
  public Response getPanelById(Integer panelId, Integer orgId, Integer orgUnitId)
      throws ApplicationException {
	
	  
	  try {
		  
		  TPanelMaster panelTestMpprMst = (TPanelMaster)sessionFactory.getCurrentSession().get(TPanelMaster.class, panelId);
			if(panelId!=null)
			{
				TPanelMasterDto tpanelTestMpprMasterDto  =mapper.map(panelTestMpprMst, TPanelMasterDto.class,
						"TPanelMasterDtoToTPanelMaster");
				
				
				  List<TPanelDetailsMasterDto> listTpanelTestMpprDetailsMaster =
				          sessionFactory.getCurrentSession().getNamedQuery("GET_PANEL_MAPPER_DETAILS_BY_SERVICE_ID")
				              .setInteger("panelId", panelId)
				              .setInteger("orgId", orgId)
				              .setInteger("orgUnitId", orgUnitId)
				              .setResultTransformer(Transformers.aliasToBean(TPanelDetailsMasterDto.class)).list();

				  tpanelTestMpprMasterDto.setListTPanelDetailsMaster(listTpanelTestMpprDetailsMaster);
				
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, tpanelTestMpprMasterDto);
			}
			     
			else {
				return null;
			}
			
	} catch (Exception e) {
		logger.error("Exection", e);
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}
  }

 


@Override
  public Response activeInactivePanelStatus(Integer panelId, Integer orgId, Integer orgUnitid,
      Character status) throws ApplicationException {
	
	
	try
	{
		 
		  TPanelMaster tpanelTestMpprMst = (TPanelMaster)sessionFactory.getCurrentSession().get(TPanelMaster.class, panelId);
		if (panelId> 0)
		{
		
			 tpanelTestMpprMst.setStatus(status);
			 sessionFactory.getCurrentSession().update(tpanelTestMpprMst);
			 
				Integer tpanelTestMpprDetailsMasterDto = (Integer) sessionFactory.getCurrentSession()
						.getNamedQuery("UPDATE_TPANEL_TEST_MASTER_ACTIVE_INACTIVE")
						.setInteger("orgId", orgId)
						.setInteger("orgUnitid", orgUnitid)
						.setInteger("panelId", panelId)
						.setCharacter("status", status) 
						.executeUpdate();
				
			 
			 if(tpanelTestMpprDetailsMasterDto>0) {
				 if (status == ACTIVE)
					{
						return new Response(SUCCESS, SUCCESS_200,  PANEL_ACTIVATE_SUCC, null, null);
					} else if (status == INACTIVE)
					{
						return new Response(SUCCESS, SUCCESS_200, PANEL_INACTIVATE_SUCC, null, null);
					} else
					{
						if (status == ACTIVE)
						{
							return new Response(SUCCESS, SUCCESS_200, PANEL_INACTIVATE_SUCC, null, null);
						} else
						{
							return new Response(SUCCESS, SUCCESS_200, PANEL_INACTIVATE_SUCC, null, null);
						}
					}
				} else
				{
					return new Response(SUCCESS, SUCCESS_200, PANEL_INACTIVATE_FAIL, null, null);
				}
			 }else {
				 return new Response(SUCCESS, SUCCESS_200, PANEL_INACTIVATE_FAIL, null, null);
			 }
			

	} catch (Exception e)
	{
		//logger.error("Exection", e);
		return new Response(ERROR, ERR_500, PANEL_NOT_FOUND, null, null);
	}
  }

  @Override
  public Response getPanelList(Integer orgId, Integer orgUnitId, Integer offset,
      Integer recordPerPage) throws ApplicationException {
	  try
		{
			List<TPanelMasterDto> panellist = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PAGINATED_TPANEL_TEST_MASTER_LIST").setInteger("orgId", orgId)
					.setInteger("orgUnitId", orgUnitId)
					.setFirstResult(offset != null ? offset : 0)
					.setMaxResults(recordPerPage != null ? recordPerPage : 10)
					.setResultTransformer(Transformers.aliasToBean(TPanelMasterDto.class)).list();
			
			if(panellist.isEmpty())
			{
				return new Response(ERROR, ERR_500, PANEL_RECORDS_NOT_FOUND, null, null);
			}
			else{
				return new Response(SUCCESS, SUCCESS_200, null, panellist, null);
			}
			
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
  }

  @Override
  public Response getPanelListCount(Integer orgId, Integer orgUnitId) throws ApplicationException {
	  try
		{
			BigInteger panelTotalRecordCount = (BigInteger) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PAGINATED_TPANEL_TEST_MASTER_LIST_COUNT").setInteger("orgId", orgId)
					.setInteger("orgUnitId", orgUnitId).uniqueResult();
			if (panelTotalRecordCount.compareTo(BigInteger.ZERO) == 1)
			{
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, panelTotalRecordCount);
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
	public Response getUnitServiceMapperList(Integer orgId,Integer orgUnitId) throws ApplicationException
	{
		List<CommonDto>  listHeaderDto  = null; 
		try
		{
			listHeaderDto = (List<CommonDto>) sessionFactory.getCurrentSession()
					.createSQLQuery("SELECT  "
							 +"	service_master.service_master_id AS id,  "
							 +"	service_master.service_standard_name AS name  "
							 +"FROM  "
							 +"	service.t_unit_service_mapper unit_service_mapper  "
							 +"INNER JOIN service.t_unit_service_master_details unit_service_master_details ON  "
							 +"	unit_service_master_details.service_master_id = unit_service_mapper.service_id  "
							 +"INNER JOIN service.m_service_master service_master ON  "
							 +"	unit_service_mapper.service_id = service_master.service_master_id "
							 +"WHERE  "
							 +"	unit_service_mapper.orgnisation_id = :orgId "
							 +"	AND unit_service_mapper.unit_id = :orgUnitId "
							 +"	AND unit_service_master_details.is_panel = 'Y' "
							 +"	AND unit_service_mapper.status = 'A'  "
							 +"	AND unit_service_master_details.status = 'A' "
							 +"ORDER BY  "
							 +"	service_master.service_standard_name ")
					.setInteger("orgId", orgId)
					.setInteger("orgUnitId", orgUnitId)
                  .setResultTransformer(Transformers.aliasToBean(CommonDto.class)).list();					
			return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, listHeaderDto, null);

		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

  @Override
  public Response getPanelDescByPanelId(Integer panelId, Integer orgId, Integer orgUnitId)
      throws ApplicationException {
    try {
            if(panelId!=null && panelId>0) {
              TPanelMasterDto tPanelMasterDto =   (TPanelMasterDto) sessionFactory.getCurrentSession().getNamedQuery("GET_PANEL_DETAILS")
                  .setInteger("panelId", panelId)
                  .setInteger("orgId", orgId)
                  .setInteger("orgUnitId", orgUnitId)
                  .setResultTransformer(Transformers.aliasToBean(TPanelMasterDto.class)).uniqueResult();
              return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, tPanelMasterDto);
            }
            return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, null);
        
} catch (Exception e) {
    logger.error("Exection", e);
    return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
}
  }

}
