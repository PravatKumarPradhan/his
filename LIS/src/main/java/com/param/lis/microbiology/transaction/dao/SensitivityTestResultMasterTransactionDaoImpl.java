package com.param.lis.microbiology.transaction.dao;

import java.util.Date;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.param.entity.lis.micro.SensitivityTestResultDetailsMaster;
import com.param.entity.lis.micro.SensitivityTestResultMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.ITransactionConstants;
import com.param.lis.global.common.Response;
import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class SensitivityTestResultMasterTransactionDaoImpl  extends GenericDao<SensitivityTestResultMaster, Integer>
implements ISensitivityTestResultMasterDao, ICommonConstants, ITransactionConstants, IError{
	
	
	final static Logger logger = Logger.getLogger(SensitivityTestResultMasterTransactionDaoImpl.class);
	@Autowired
	private ISensitivityTestResultDetailsMasterDao iSensitivityTestResultDetailsMasterDao;
	


	public SensitivityTestResultMasterTransactionDaoImpl() {
		super(SensitivityTestResultMaster.class);
	}

	@Override
	public Response saveSensitivityTestResultMasterDao(
			SensitivityTestResultMaster sensitivityTestResultMaster) throws ApplicationException {
		
		try
		{
			if (sensitivityTestResultMaster != null)
			{
				if (sensitivityTestResultMaster.getSensitivityResultId() == 0) {
					SensitivityTestResultMaster SensitivityTestResultMst = save(sensitivityTestResultMaster);
					if(SensitivityTestResultMst.getListSensitivityTestResultDetailsMaster()!=null)
					{
						for (Iterator iterator = SensitivityTestResultMst.getListSensitivityTestResultDetailsMaster().iterator(); iterator.hasNext();)
						{
							SensitivityTestResultDetailsMaster sensitivityTestResultDetailsMaster = (SensitivityTestResultDetailsMaster) iterator.next();
							sensitivityTestResultDetailsMaster.setSensitivityResultId(SensitivityTestResultMst.getSensitivityResultId());
							sensitivityTestResultDetailsMaster.setOrgId(SensitivityTestResultMst.getOrgId());
							sensitivityTestResultDetailsMaster.setOrgUnitId(SensitivityTestResultMst.getOrgUnitId());
							sensitivityTestResultDetailsMaster.setCreatedDate(new Date().getTime());
							if(sensitivityTestResultDetailsMaster.getSesitivityResultDetailsId()==0){
                              iSensitivityTestResultDetailsMasterDao.saveSensitivityTestResultDetailsMaster(sensitivityTestResultDetailsMaster);
                           }else{
                              iSensitivityTestResultDetailsMasterDao.updateSensitivityTestResultDetailsMaster(sensitivityTestResultDetailsMaster);
                         }
						}
						
						return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, null);
					}
					else {
						return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
					}
				}else{
					if(sensitivityTestResultMaster.getListSensitivityTestResultDetailsMaster()!=null)
					{
						for (Iterator iterator = sensitivityTestResultMaster.getListSensitivityTestResultDetailsMaster().iterator(); iterator.hasNext();)
						{
							SensitivityTestResultDetailsMaster sensitivityTestResultDetailsMaster = (SensitivityTestResultDetailsMaster) iterator.next();
							sensitivityTestResultDetailsMaster.setSensitivityResultId(sensitivityTestResultMaster.getSensitivityResultId());
							sensitivityTestResultDetailsMaster.setOrgId(sensitivityTestResultMaster.getOrgId());
							sensitivityTestResultDetailsMaster.setOrgUnitId(sensitivityTestResultMaster.getOrgUnitId());
							sensitivityTestResultDetailsMaster.setCreatedDate(new Date().getTime());
							if(sensitivityTestResultDetailsMaster.getSesitivityResultDetailsId()==0){
								 iSensitivityTestResultDetailsMasterDao.saveSensitivityTestResultDetailsMaster(sensitivityTestResultDetailsMaster);
							}else{
								 iSensitivityTestResultDetailsMasterDao.updateSensitivityTestResultDetailsMaster(sensitivityTestResultDetailsMaster);
							}
						
						}
						
						return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, null);
					}
					else {
						return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
					}
				}
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


}
