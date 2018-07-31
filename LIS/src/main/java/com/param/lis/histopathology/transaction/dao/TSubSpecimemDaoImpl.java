package com.param.lis.histopathology.transaction.dao;

import java.util.Iterator;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.param.entity.lis.histo.TGrossMaster;
import com.param.entity.lis.histo.TSubSpecimanMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.ITransactionConstants;
import com.param.lis.global.common.Response;
import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings(
{ "rawtypes", "unchecked" })
public class TSubSpecimemDaoImpl extends GenericDao<TSubSpecimanMaster, Integer>
implements ITSubSpecimemDao, ICommonConstants, IError,ITransactionConstants
{

	final static Logger logger = Logger.getLogger(TSubSpecimemDaoImpl.class);
	
	public TSubSpecimemDaoImpl() 
	{
		super(TSubSpecimanMaster.class);
	}
	
	@Autowired
	private SessionFactory sessionFactory;
	
	

	@Override
	public Response saveSubSpecimanDetails(TSubSpecimanMaster tSubSpecimanMaster) throws ApplicationException {
		try
		{
		 TSubSpecimanMaster tSubSpecimenObj = save(tSubSpecimanMaster);
		  if(!tSubSpecimanMaster.getListTGrossMaster().isEmpty())
		  {
			  for (Iterator iterator = tSubSpecimanMaster.getListTGrossMaster().iterator(); iterator.hasNext();) 
			  {
				TGrossMaster tGrossMaster = (TGrossMaster) iterator.next();
				tGrossMaster.setLabSampleDtlsId(tSubSpecimenObj.getLabSampleDtlsId());
				tGrossMaster.settSubSpecimanId(tSubSpecimenObj.gettSubSpecimanId());
				tGrossMaster.setCreatedBy(tSubSpecimenObj.getCreatedBy());
				tGrossMaster.setCreatedDate(tSubSpecimenObj.getCreatedDate());
				sessionFactory.getCurrentSession().save(tGrossMaster);
			  }
			  return new Response(SUCCESS, SUCCESS_200, GROSS_CREATE_SUCCESS, null, null);
		  }
		  else 
		  {
			  return new Response(ERROR, ERR_500, GROSS_CREATE_FAIL, null, null);
		  }
			
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

}
