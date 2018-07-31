package com.param.lis.microbiology.transaction.dao;

import java.util.Date;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.param.entity.lis.micro.MicroscopicExaminationSubDetailsMaster;
import com.param.entity.lis.micro.SensitivityTestResultDetailsMaster;
import com.param.entity.lis.micro.SensitivityTestResultMaster;
import com.param.entity.lis.micro.SensitivityTestResultSubDetailsMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.ITransactionConstants;
import com.param.lis.global.common.Response;
import com.param.lis.microbiology.transaction.dto.SensitivityTestResultSubDetailsMasterDto;
import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class SensitivityTestResultDetailsMasterDaoImpl  extends GenericDao<SensitivityTestResultDetailsMaster, Integer>
implements ISensitivityTestResultDetailsMasterDao, ICommonConstants, ITransactionConstants, IError{
	
	
	final static Logger logger = Logger.getLogger(SensitivityTestResultDetailsMasterDaoImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
	


	public SensitivityTestResultDetailsMasterDaoImpl() {
		super(SensitivityTestResultDetailsMaster.class);
	}

	@Override
	public Response saveSensitivityTestResultDetailsMaster(
			SensitivityTestResultDetailsMaster sensitivityTestResultDetailsMaster) throws ApplicationException {
		
		try
		{
			if (sensitivityTestResultDetailsMaster != null)
			{
				
				SensitivityTestResultDetailsMaster SensitivityTestResultDetailsMst = save(sensitivityTestResultDetailsMaster);
				if(SensitivityTestResultDetailsMst.getListSensitivityTestResultSubDetailsMaster()!=null)
				{
					for (Iterator iterator = SensitivityTestResultDetailsMst.getListSensitivityTestResultSubDetailsMaster().iterator(); iterator.hasNext();)
					{
						SensitivityTestResultSubDetailsMaster sensitivityTestResultSubDetailsMaster = (SensitivityTestResultSubDetailsMaster) iterator.next();
						sensitivityTestResultSubDetailsMaster.setCreatedDate(new Date().getTime());
						sensitivityTestResultSubDetailsMaster.setOrgId(SensitivityTestResultDetailsMst.getOrgId());
						sensitivityTestResultSubDetailsMaster.setSesitivityResultDetailsId(SensitivityTestResultDetailsMst.getSesitivityResultDetailsId());
						if (sensitivityTestResultSubDetailsMaster.getSesitivityResultSubDetailsId() == 0) 
						{
						sessionFactory.getCurrentSession().save(sensitivityTestResultSubDetailsMaster);
						}
						else 
						{ 
						  sensitivityTestResultSubDetailsMaster.setUpdatedDate(new Date().getTime());
						  sessionFactory.getCurrentSession().update(sensitivityTestResultSubDetailsMaster);
                        }
					}
						return new Response(SUCCESS, SUCCESS_200, "Data Save Successfully", null, null);
				}
				else {
					return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
				}
			} else
			{
				return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
				
			}
		}catch(HibernateException exception){
			logger.error("Exection", exception);
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}	
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		
	}

	@Override
	public Response updateSensitivityTestResultDetailsMaster(
			SensitivityTestResultDetailsMaster sensitivityTestResultDetailsMaster)
			throws ApplicationException {
		try {

			if (sensitivityTestResultDetailsMaster != null) {

					if (sensitivityTestResultDetailsMaster.getListSensitivityTestResultSubDetailsMaster() != null) 
					{
						for (Iterator iterator = sensitivityTestResultDetailsMaster
								.getListSensitivityTestResultSubDetailsMaster().iterator(); iterator.hasNext();) 
						{
							SensitivityTestResultSubDetailsMaster sensitivityTestResultSubDetailsMaster = (SensitivityTestResultSubDetailsMaster) iterator
									.next();
							
							if (sensitivityTestResultSubDetailsMaster.getSesitivityResultSubDetailsId() == 0) 
							{
								sensitivityTestResultSubDetailsMaster.setSesitivityResultDetailsId(sensitivityTestResultDetailsMaster.getSesitivityResultDetailsId());
								sensitivityTestResultSubDetailsMaster.setCreatedDate(new Date().getTime());
							    sessionFactory.getCurrentSession().save(sensitivityTestResultSubDetailsMaster);								
							} else 
							{
							  sensitivityTestResultSubDetailsMaster.setSesitivityResultDetailsId(sensitivityTestResultDetailsMaster.getSesitivityResultDetailsId());
	                          sensitivityTestResultSubDetailsMaster.setUpdatedDate(new Date().getTime());
							  sessionFactory.getCurrentSession().update(sensitivityTestResultSubDetailsMaster);
							 
							}

						}
							return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, null);

					} else {
						return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
					}

			} else {
				return new Response(ERROR, ERR_500, "Sensitivity Examination Details Result list is empty", null, null);

			}
		} catch (HibernateException exception) {
			logger.error("Exection", exception);
		} catch (Exception e) {
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}

}
