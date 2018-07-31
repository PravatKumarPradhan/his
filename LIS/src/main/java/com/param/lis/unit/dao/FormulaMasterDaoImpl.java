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
import com.param.entity.lis.unit.FormulaDetails;
import com.param.entity.lis.unit.FormulaMaster;
import com.param.entity.lis.unit.TPanelDetailsMaster;
import com.param.entity.lis.unit.TPanelMaster;
import com.param.lis.common.dto.CommonDto;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.unit.dto.FormulaDetailsDto;
import com.param.lis.unit.dto.FormulaMasterDto;
import com.param.lis.unit.dto.TPanelDetailsMasterDto;
import com.param.lis.unit.dto.TPanelMasterDto;
import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings(
{ "rawtypes", "unchecked" })
public class FormulaMasterDaoImpl extends GenericDao<FormulaMaster, Integer>
implements IFormulaMasterDao, ICommonConstants, IError{

  public FormulaMasterDaoImpl() {
    super(FormulaMaster.class);
  }
  
  @Autowired
  private DozerBeanMapper mapper;
  
  @Autowired
  private SessionFactory sessionFactory;
  
  final static Logger logger = Logger.getLogger(FormulaMasterDaoImpl.class);

  @Override
  public Response addFormula(FormulaMasterDto formulaMasterDto)
      throws ApplicationException 
  {
    
    try
    {
      Integer count = 0;
      formulaMasterDto.setCreatedDate(new Date().getTime());
      FormulaMaster formulaMaster = mapper.map(formulaMasterDto, FormulaMaster.class, "FormulaMasterDtoTOFormulaMaster");
      FormulaMaster formulaMst = save(formulaMaster);
      
      
      for (Iterator iterator = formulaMst.getListFormulaDetails().iterator(); iterator.hasNext();) {
    	  FormulaDetails formulaDetails = (FormulaDetails) iterator.next();
    	  formulaDetails.setFormulaId(formulaMst.getFormulaId());
    	  formulaDetails.setCreatedDate(new Date().getTime());
		        sessionFactory.getCurrentSession().save(formulaDetails);
		        count++;
      }
      if(count>0)
      {
        return new Response(SUCCESS, SUCCESS_200, FORMULA_ADD_SUCC, null, null);
      }
      else {
        return new Response(ERROR, ERR_500,FORMULA_ADD_FAIL, null, null);
      }
        
    } catch (Exception e)
    {
        logger.error("Exection", e);
        return new Response(ERROR, ERR_500,FORMULA_ADD_FAIL, null, null);
    }
   
    
  }

  @Override
  public Response updateFormula(FormulaMasterDto formulaMasterDto)
      throws ApplicationException {
    try
    {
      Integer count = 0;
      formulaMasterDto.setUpdatedDate(new Date().getTime());
      FormulaMaster formulaMaster = mapper.map(formulaMasterDto, FormulaMaster.class, "FormulaMasterDtoTOFormulaMaster");
      FormulaMaster formulaMst = update(formulaMaster);
      
      Integer statusCount = (Integer) sessionFactory.getCurrentSession()
				.getNamedQuery("UPDATE_FORMULA_STATUS")
				.setInteger("formulaId", formulaMasterDto.getFormulaId())
				.setInteger("orgId", formulaMasterDto.getOrgId())
				.setInteger("orgUnitId", formulaMasterDto.getOrgUnitId())
				.executeUpdate();
      
  	if(statusCount >0) {
  		for (Iterator iterator = formulaMst.getListFormulaDetails().iterator(); iterator.hasNext();) {
  			FormulaDetails formulaDetails = (FormulaDetails) iterator.next();
  			formulaDetails.setFormulaId(formulaMst.getFormulaId());
  			formulaDetails.setCreatedDate(new Date().getTime());
	        sessionFactory.getCurrentSession().save(formulaDetails);
	        count++;
  	      }
  	      if(count>0)
  	      {
  	        return new Response(SUCCESS, SUCCESS_200, FORMULA_UPDATE_SUCC, null, null);
  	      }
  	      else {
  	        return new Response(ERROR, ERR_500,FORMULA_UPDATE_FAIL, null, null);
  	      }
  	}else {
  		 return new Response(ERROR, ERR_500,FORMULA_UPDATE_FAIL, null, null);
  	}
      
        
    } catch (Exception e)
    {
        logger.error("Exection", e);
        return new Response(ERROR, ERR_500,FORMULA_UPDATE_FAIL, null, null);
    }
  }
  
  @Override
  public Response getFormulaById(Integer formulaId, Integer orgId, Integer orgUnitId)
      throws ApplicationException {
	  try {
		  
	    FormulaMasterDto formulaMasterDto =
            (FormulaMasterDto) sessionFactory.getCurrentSession().getNamedQuery("GET_FORMULA_MASTER")
          .setInteger("formulaId", formulaId)
          .setInteger("orgId", orgId)
          .setInteger("orgUnitId", orgUnitId)
          .setResultTransformer(Transformers.aliasToBean(FormulaMasterDto.class)).uniqueResult();
	    
			if(formulaMasterDto!=null)
			{
				  List<FormulaDetailsDto> listFormulaDetailsDto =
				          sessionFactory.getCurrentSession().getNamedQuery("GET_FORMULA_MASTER_DETAILS")
				              .setInteger("formulaId", formulaId)
				              .setInteger("orgId", orgId)
				              .setInteger("orgUnitId", orgUnitId)
				              .setResultTransformer(Transformers.aliasToBean(FormulaDetailsDto.class)).list();
				  formulaMasterDto.setListFormulaDetails(listFormulaDetailsDto);
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, formulaMasterDto);
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
  public Response activeInactiveFormula(Integer formulaId, Integer orgId, Integer orgUnitId,
      Character status) throws ApplicationException {
	try
	{
		  FormulaMaster formulaMaster = (FormulaMaster)sessionFactory.getCurrentSession().get(FormulaMaster.class, formulaId);
		if (formulaId> 0)
		{
		     formulaMaster.setStatus(status);
			 sessionFactory.getCurrentSession().update(formulaMaster);
			 
				Integer statusCount = (Integer) sessionFactory.getCurrentSession()
						.getNamedQuery("ACTIVE_INACTIVE_FORMULA")
						.setInteger("orgId", orgId)
						.setInteger("orgUnitId", orgUnitId)
						.setInteger("formulaId", formulaId)
						.setCharacter("status", status) 
						.executeUpdate();
				
			 
			 if(statusCount>0) 
			 {
				 if (status == ACTIVE)
					{
						return new Response(SUCCESS, SUCCESS_200,  FORMULA_ACTIVATE_SUCC, null, null);
					} else if (status == INACTIVE)
					{
						return new Response(SUCCESS, SUCCESS_200, FORMULA_INACTIVATE_SUCC, null, null);
					} else
					{
						if (status == ACTIVE)
						{
							return new Response(SUCCESS, SUCCESS_200, FORMULA_INACTIVATE_SUCC, null, null);
						} else
						{
							return new Response(SUCCESS, SUCCESS_200, FORMULA_INACTIVATE_SUCC, null, null);
						}
					}
				} else
				{
					return new Response(SUCCESS, SUCCESS_200, FORMULA_INACTIVATE_FAIL, null, null);
				}
			 }else {
				 return new Response(SUCCESS, SUCCESS_200, FORMULA_INACTIVATE_FAIL, null, null);
			 }
			

	} catch (Exception e)
	{
		logger.error("Exection", e);
		return new Response(ERROR, ERR_500, PANEL_NOT_FOUND, null, null);
	}
  }

  @Override
  public Response getFormulaList(Integer orgId, Integer orgUnitId, Integer offset,
      Integer recordPerPage) throws ApplicationException {
	  try
		{
			List<FormulaMasterDto> formulaMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_FORMULA_MASTER_LIST").setInteger("orgId", orgId)
					.setInteger("orgUnitId", orgUnitId)
					.setFirstResult(offset != null ? offset : 0)
					.setMaxResults(recordPerPage != null ? recordPerPage : 10)
					.setResultTransformer(Transformers.aliasToBean(FormulaMasterDto.class)).list();
			
			if(formulaMasterDtoList.isEmpty())
			{
				return new Response(ERROR, ERR_500, FORMULA_RECORDS_NOT_FOUND, null, null);
			}
			else{
				return new Response(SUCCESS, SUCCESS_200, null, formulaMasterDtoList, null);
			}
			
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
  }

  @Override
  public Response getFormulaListCount(Integer orgId, Integer orgUnitId) throws ApplicationException {
	  try
		{
			Long formulaCount = (Long) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_FORMULA_MASTER_LIST_COUNT")
					.setInteger("orgId", orgId)
					.setInteger("orgUnitId", orgUnitId).uniqueResult();
			if (formulaCount >0)
			{
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, formulaCount);
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
	public Response getParameterListForFormula(Integer orgId,Integer orgUnitId,Character isFormula) throws ApplicationException
	{
		List<CommonDto>  listHeaderDto  = null; 
		try
		{
			listHeaderDto = (List<CommonDto>) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PARAMETER_FOR_FORMULA")
					.setInteger("orgId", orgId)
					.setInteger("orgUnitId", orgUnitId)
					.setCharacter("isFormula", isFormula)
                  .setResultTransformer(Transformers.aliasToBean(CommonDto.class)).list();					
			return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, listHeaderDto, null);

		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

  @Override
  public Response getFormulaDetails(Integer formulaId, Integer orgId, Integer orgUnitId)
      throws ApplicationException {
    List<FormulaDetailsDto>  listFormulaDetailsDto  = null; 
    try
    {
      listFormulaDetailsDto = (List<FormulaDetailsDto>) sessionFactory.getCurrentSession()
                .getNamedQuery("GET_FORMULA_DETAILS")
                .setInteger("orgId", orgId)
                .setInteger("orgUnitId", orgUnitId)
                .setInteger("formulaId", formulaId)
              .setResultTransformer(Transformers.aliasToBean(FormulaDetailsDto.class)).list();                  
        return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, listFormulaDetailsDto, null);

    } catch (Exception e)
    {
        logger.error("Exection", e);
        return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  public Response getFormulaByParameter(Integer parameterId, Integer orgId, Integer orgUnitId)
      throws ApplicationException 
  {
    try {
      
                List<FormulaDetailsDto> listFormulaDetailsDto =
                        sessionFactory.getCurrentSession().getNamedQuery("GET_FORMULA_DETAILS_BY_PARAMETER")
                            .setInteger("parameterId", parameterId)
                            .setInteger("orgId", orgId)
                            .setInteger("orgUnitId", orgUnitId)
                            .setResultTransformer(Transformers.aliasToBean(FormulaDetailsDto.class)).list();
              return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, listFormulaDetailsDto, null);
               
          
  } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
  }
  }

  

}
