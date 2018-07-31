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
import com.param.lis.unit.dao.IFormulaMasterDao;
import com.param.lis.unit.dao.IPanelTestDao;
import com.param.lis.unit.dto.FormulaMasterDto;
import com.param.lis.unit.dto.LabTemplateMasterDto;
import com.param.lis.unit.dto.TPanelMasterDto;
import in.param.exception.ApplicationException;

@Service
@SuppressWarnings(
{ "rawtypes", "unchecked" })
public class FormulaMasterServiceImpl  implements IFormulaMasterService, ICommonConstants, IError{
  
  @Autowired
  IFormulaMasterDao iFormulaMasterDao;

  @Autowired
  private SessionFactory sessionFactory;

  @Override
  @Transactional
  public Response addFormula(FormulaMasterDto formulaMasterDto)
      throws ApplicationException {
	  
	  try
		{
	    Long formulaParameterExistsCount = (Long) sessionFactory.getCurrentSession()
					.getNamedQuery("CHECK_PARAMETER_EXISTS")
					.setInteger("parameterId", formulaMasterDto.getParameterId())
					.setInteger("orgId", formulaMasterDto.getOrgId())
					.setInteger("orgUnitId", formulaMasterDto.getOrgUnitId())
		  			.uniqueResult();
		  
		  if (formulaParameterExistsCount==0) {
			  
		            formulaMasterDto.setCreatedDate(new Date().getTime());
					Response response = iFormulaMasterDao.addFormula(formulaMasterDto);
					return response;
				} else
				{
					return new Response(ERROR, ERR_500, FORMULA_TEST_EXISIS, null, null);
				}
			  
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
  }

  @Override
  @Transactional
  public Response getFormulaById(Integer formulaId, Integer orgId, Integer orgUnitId)
      throws ApplicationException {
    try
    {
        return iFormulaMasterDao.getFormulaById(formulaId,orgId,orgUnitId);
    } catch (Exception e)
    {
        e.printStackTrace();
        return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response updateFormula(FormulaMasterDto formulaMasterDto) throws ApplicationException {
    try
    {
        return iFormulaMasterDao.updateFormula(formulaMasterDto);
    } catch (Exception e)
    {
        e.printStackTrace();
        return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response activeInactiveFormula(Integer formulaId, Integer orgId, Integer orgUnitid,
      Character status) throws ApplicationException {
    try
    {
        return iFormulaMasterDao.activeInactiveFormula(formulaId,orgId,orgUnitid,status);
    } catch (Exception e)
    {
        e.printStackTrace();
        return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getFormulaList(Integer orgId, Integer orgUnitId, Integer offset,
      Integer recordPerPage) throws ApplicationException {
    try
    {
        return iFormulaMasterDao.getFormulaList(orgId,orgUnitId,offset,recordPerPage);
    } catch (Exception e)
    {
        e.printStackTrace();
        return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getFormulaListCount(Integer orgId, Integer orgUnitId) throws ApplicationException {
    try
    {
        return iFormulaMasterDao.getFormulaListCount(orgId,orgUnitId);
    } catch (Exception e)
    {
        e.printStackTrace();
        return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

@Override
@Transactional
public Response getParameterListForFormula(Integer orgId, Integer orgUnitId,Character isFormula) throws ApplicationException {
	 try
	    {
	        return iFormulaMasterDao.getParameterListForFormula(orgId,orgUnitId,isFormula);
	    } catch (Exception e)
	    {
	        e.printStackTrace();
	        return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	    }
}

@Override
@Transactional
public Response getFormulaDetails(Integer formulaId, Integer orgId, Integer orgUnitId)
    throws ApplicationException {
  try
  {
      return iFormulaMasterDao.getFormulaDetails(formulaId,orgId,orgUnitId);
  } catch (Exception e)
  {
      e.printStackTrace();
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
  }
}

@Override
@Transactional
public Response getFormulaByParameter(Integer parameterId, Integer orgId, Integer orgUnitId)
    throws ApplicationException {
  try
  {
      return iFormulaMasterDao.getFormulaByParameter(parameterId,orgId,orgUnitId);
  } catch (Exception e)
  {
      e.printStackTrace();
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
  }
}


}
