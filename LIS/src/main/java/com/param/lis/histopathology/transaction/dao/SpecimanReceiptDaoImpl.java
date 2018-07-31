package com.param.lis.histopathology.transaction.dao;

import java.math.BigInteger;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.param.entity.lis.transaction.LabSampleDetailsMaster;
import com.param.entity.lis.transaction.LabSampleMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.ITransactionConstants;
import com.param.lis.global.common.Response;
import com.param.lis.histopathology.transaction.dto.TSpecimanMasterDto;
import com.param.lis.histopathology.transaction.dto.specimanReceiptDto;
import com.param.lis.transaction.dao.ILabCentralReceivingDao;
import com.param.lis.transaction.dao.ILabTransactionDao;
import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings(
{ "rawtypes", "unchecked" })
public class SpecimanReceiptDaoImpl extends GenericDao<LabSampleMaster, Integer>
		implements ISpecimanReceiptDao, ICommonConstants, IError,ITransactionConstants
{

	final static Logger logger = Logger.getLogger(SpecimanReceiptDaoImpl.class);
	public SpecimanReceiptDaoImpl()
	{
		super(LabSampleMaster.class);
	}
	
   @Autowired 
  private ILabTransactionDao iLabTransactionDao;
   
   
   
   @Autowired 
   private ILabCentralReceivingDao iLabCentralReceivingDao;
   
	
	@Override
	public Response listSpecimanReceipt(specimanReceiptDto specimanReceiptDto) throws ApplicationException
	{
		try
		{
			List<specimanReceiptDto> listSampleMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PAGINATED_SPECIMAN_RECEIPT_LIST").setInteger("orgId", specimanReceiptDto.getOrgId())
					.setInteger("orgUnitId", specimanReceiptDto.getOrgUnitId())
					.setInteger("serviceRenderingDeptId", specimanReceiptDto.getDeptId())
					.setInteger("subDeptId", specimanReceiptDto.getSubDeptId())
					.setInteger("serviceIsBilled", 1)
					.setParameterList("orderStatusId",  RENDER_CANCEL)
					.setInteger("serviceRendered", 0)
					.setFirstResult(specimanReceiptDto.getOffset() != null ? specimanReceiptDto.getOffset() : 0)
					.setMaxResults(specimanReceiptDto.getRecordPerPage() != null ? specimanReceiptDto.getRecordPerPage() : 10)
					.setResultTransformer(Transformers.aliasToBean(specimanReceiptDto.class)).list();
			
			if(listSampleMasterDto.isEmpty())
			{
				return new Response(ERROR, ERR_500, SAMPLE_RECORDS_NOT_FOUND, null, null);
			}
			else{
				return new Response(SUCCESS, SUCCESS_200, null, listSampleMasterDto, null);
			}
			
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response getTotalSpecimanReceiptRecord(specimanReceiptDto specimanReceiptDto) throws ApplicationException
	{
		try
		{
			BigInteger sampleMasterTotalRecordCount = (BigInteger) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_SPECIMAN_RECEIPT_COUNT").setInteger("orgId", specimanReceiptDto.getOrgId())
					.setInteger("orgUnitId", specimanReceiptDto.getOrgUnitId())
					.setInteger("serviceRenderingDeptId", specimanReceiptDto.getDeptId())
					.setInteger("subDeptId", specimanReceiptDto.getSubDeptId())
					.setParameterList("orderStatusId",  RENDER_CANCEL)
					.setInteger("serviceIsBilled", 1)
					.setInteger("serviceRendered", 0)
					.uniqueResult();
			if (sampleMasterTotalRecordCount.compareTo(BigInteger.ZERO) == 1)
			{
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, sampleMasterTotalRecordCount);
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
	public Response collectionSpeciman(LabSampleMaster labSampleMaster) throws ApplicationException {
		try
		{
				LabSampleMaster labSampleMst = save(labSampleMaster);
					if (labSampleMst != null)
					{
						for (Iterator iterator = labSampleMst.getListLabSampleDetailsMaster().iterator(); iterator.hasNext();)
						{
							LabSampleDetailsMaster labSampleDetailsMaster = (LabSampleDetailsMaster) iterator
									.next();
							labSampleDetailsMaster.setSampleStatusId(SPECIMAN_ACCEPTED);
							labSampleDetailsMaster.setLabSampleId(labSampleMst.getLabSampleId());
							Response<LabSampleDetailsMaster, Integer> labSampleDetailsRes = iLabCentralReceivingDao.samplCollection(labSampleDetailsMaster);
							 if(labSampleDetailsRes.getCode().equals(SUCCESS_200))
							{
								 Response res =  iLabTransactionDao.updateServiceRenderStatus(labSampleDetailsRes.getObject().getOrderDetailsId());
								 if(res.getCode().equals(SUCCESS_200))
								 {
									
									 iLabTransactionDao.updateOrderStatus(labSampleMaster.getOrderId(), ORDER_COMPLETED);
									 return new Response(SUCCESS, SUCCESS_200, "Speciman Accepted Successfully.", null, labSampleDetailsRes.getObject());
								 }
								 else
								 {
										return new Response(ERROR, ERR_500, "Falied to Accept Speciman.", null, null);
								 }
							}
							else{
								return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
							  }
						}
						return new Response(ERROR, ERR_500, "Opps something went wrong.", null, null);
					} 
					else{
						return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
					}
			
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response updateLabSampleDetailsMaster(LabSampleDetailsMaster labSampleDetailsMaster)
			throws ApplicationException
	{
		try {
			Integer labSampleDetailsStatus = (Integer) sessionFactory.getCurrentSession()
					.getNamedQuery("UPDATE_LAB_SAMPLE_DETAILS_MICRO")
					.setInteger("updatedBy", labSampleDetailsMaster.getUpdatedBy())
					.setDate("updatedDate", new Date())
					.setInteger("sampleStatausId", labSampleDetailsMaster.getSampleStatusId())
					.setInteger("labSampleDtlsId", labSampleDetailsMaster.getLabSampleDtlsId()).executeUpdate();
			if (labSampleDetailsStatus > 0) 
			{
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, labSampleDetailsStatus);
			} else {
				return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, new Integer(0));
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
