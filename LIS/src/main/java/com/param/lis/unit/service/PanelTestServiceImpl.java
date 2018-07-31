package com.param.lis.unit.service;

import java.math.BigInteger;
import java.util.Date;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.unit.dao.IPanelTestDao;
import com.param.lis.unit.dto.LabTemplateMasterDto;
import com.param.lis.unit.dto.TPanelMasterDto;
import in.param.exception.ApplicationException;

@Service
@SuppressWarnings(
{ "rawtypes", "unchecked" })
public class PanelTestServiceImpl  implements IPanelTestService, ICommonConstants, IError{
  
  @Autowired
  IPanelTestDao iPanelTestDao;

  @Autowired
  private SessionFactory sessionFactory;

  @Override
  @Transactional
  public Response addPanel(TPanelMasterDto tpanelTestMpprMasterDto)
      throws ApplicationException {
   /* try
    {
        return iPanelTestDao.addPanel(tpanelTestMpprMasterDto);
    } catch (Exception e)
    {
        e.printStackTrace();
        return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }*/
	  
	  try
		{
		  BigInteger tpanelTestMpprCodeExists = (BigInteger) sessionFactory.getCurrentSession()
					.getNamedQuery("CHECK_PANEL_CODE_BY_PANEL_ID")
					.setString("panelCode", tpanelTestMpprMasterDto.getPanelCode().trim().toLowerCase())
					.setInteger("orgId", tpanelTestMpprMasterDto.getOrgId())// labTemplateMasterDto.getListSpecialtyTemplateMapperDto().get(0).getSpecialityId()
					.setInteger("orgUnitId", tpanelTestMpprMasterDto.getOrgUnitId())
		  			.uniqueResult();
		  
		  if (tpanelTestMpprCodeExists.compareTo(BigInteger.ZERO) == 0) {
			  
			  BigInteger tpanelTestExists = (BigInteger) sessionFactory.getCurrentSession()
						.getNamedQuery("CHECK_PANEL_TEST_EXISTS")
						.setInteger("serviceId", tpanelTestMpprMasterDto.getServiceId())
						.setInteger("orgId", tpanelTestMpprMasterDto.getOrgId())
						.setInteger("orgUnitId", tpanelTestMpprMasterDto.getOrgUnitId())
						.uniqueResult();
			  if (tpanelTestExists.compareTo(BigInteger.ZERO) == 0)
				{
					tpanelTestMpprMasterDto.setCreatedDate(new Date().getTime());
					Response response = iPanelTestDao.addPanel(tpanelTestMpprMasterDto);
					return response;
				} else
				{
					return new Response(ERROR, ERR_500, PANEL_TEST_EXISIS, null, null);
				}
			  
		  } else
		  {
				return new Response(ERROR, ERR_500, PANEL_CODE_EXISIS, null, null);
		  }
		  
		  
		  
			/*if (tpanelTestMpprMstDto == null)
			{
				tpanelTestMpprMasterDto.setCreatedDate(new Date().getTime());
				Response response = iPanelTestDao.addPanel(tpanelTestMpprMasterDto);
				return response;
			} else
			{
				return new Response(ERROR, ERR_500, PANEL_CODE_EXISIS, null, null);
			}*/

		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
  }

  @Override
  @Transactional
  public Response getPanelById(Integer panelId, Integer orgId, Integer orgUnitId)
      throws ApplicationException {
    try
    {
        return iPanelTestDao.getPanelById(panelId,orgId,orgUnitId);
    } catch (Exception e)
    {
        e.printStackTrace();
        return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response updatePanel(TPanelMasterDto tpanelTestMpprMasterDto) throws ApplicationException {
    try
    {
        return iPanelTestDao.updatePanel(tpanelTestMpprMasterDto);
    } catch (Exception e)
    {
        e.printStackTrace();
        return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response activeInactivePanelStatus(Integer panelId, Integer orgId, Integer orgUnitid,
      Character status) throws ApplicationException {
    try
    {
        return iPanelTestDao.activeInactivePanelStatus(panelId,orgId,orgUnitid,status);
    } catch (Exception e)
    {
        e.printStackTrace();
        return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getPanelList(Integer orgId, Integer orgUnitId, Integer offset,
      Integer recordPerPage) throws ApplicationException {
    try
    {
        return iPanelTestDao.getPanelList(orgId,orgUnitId,offset,recordPerPage);
    } catch (Exception e)
    {
        e.printStackTrace();
        return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getPanelListCount(Integer orgId, Integer orgUnitId) throws ApplicationException {
    try
    {
        return iPanelTestDao.getPanelListCount(orgId,orgUnitId);
    } catch (Exception e)
    {
        e.printStackTrace();
        return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

@Override
@Transactional
public Response getUnitServiceMapperList(Integer orgId, Integer orgUnitId) throws ApplicationException {
	 try
	    {
	        return iPanelTestDao.getUnitServiceMapperList(orgId,orgUnitId);
	    } catch (Exception e)
	    {
	        e.printStackTrace();
	        return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	    }
}

@Override
@Transactional
public Response getPanelDescByPanelId(Integer panelId, Integer orgId, Integer orgUnitId)
    throws ApplicationException {
  try
  {
      return iPanelTestDao.getPanelDescByPanelId(panelId,orgId,orgUnitId);
  } catch (Exception e)
  {
      e.printStackTrace();
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
  }
}

}
