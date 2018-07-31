package com.param.lis.microbiology.transaction.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.param.entity.lis.micro.SensitivityTestResultMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.ITransactionConstants;
import com.param.lis.global.common.Response;
import com.param.lis.microbiology.transaction.dto.MicrobioParamDto;
import com.param.lis.microbiology.transaction.dto.SensitivityTestResultDetailsMasterDto;
import com.param.lis.microbiology.transaction.dto.SensitivityTestResultMasterDto;
import com.param.lis.microbiology.transaction.dto.SensitivityTestResultSubDetailsMasterDto;
import com.param.lis.transaction.dto.SampleAcceptancePendingDto;
import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings(
{ "rawtypes", "unchecked" })
public class SensitivityTestingDaoImpl extends GenericDao<SensitivityTestResultMaster, Integer>
implements ISensitivityTestingDao, ICommonConstants, ITransactionConstants, IError{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private DozerBeanMapper mapper;
	
	@Autowired
	private ISensitivityTestResultMasterDao iSensitivityTestResultMasterDao;

	public SensitivityTestingDaoImpl() {
		super(SensitivityTestResultMaster.class);
	}

	final static Logger logger = Logger.getLogger(SensitivityTestingDaoImpl.class);

	@Override
	public Response getSensitivityTestingList(MicrobioParamDto microbioParamDto) throws ApplicationException 
	{
		List<SampleAcceptancePendingDto> listMicrobioResultEntryMasterDto = null;
		try
		{
			listMicrobioResultEntryMasterDto = sessionFactory.getCurrentSession()
						.getNamedQuery("GET_SENSITIVITY_TESTING_LIST")
						.setInteger("orgId", microbioParamDto.getOrgId())
						.setInteger("orgUnitId", microbioParamDto.getOrgUnitId())
						.setParameterList("sampleStatusIds", SENSITIVITY_SAMPLE_STATUS_IDS)
						.setInteger("deptId", microbioParamDto.getDeptId())
						.setInteger("subDeptId", microbioParamDto.getSubDeptId())
						.setFirstResult(microbioParamDto.getOffset() != null ? microbioParamDto.getOffset() : 0)
						.setMaxResults(microbioParamDto.getRecordPerPage() != null ? microbioParamDto.getRecordPerPage()  : 10)
						.setResultTransformer(Transformers.aliasToBean(SampleAcceptancePendingDto.class)).list();
				return new Response(SUCCESS, SUCCESS_200, null, listMicrobioResultEntryMasterDto, null);
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response getSensitivityTestingListCount(MicrobioParamDto microbioParamDto) throws ApplicationException {
		try
		{
			BigInteger sensitivityTestingListCount = (BigInteger) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_SENSITIVITY_TESTING_LIST_COUNT")
					.setInteger("orgId", microbioParamDto.getOrgId())
					.setParameterList("sampleStatusIds", SENSITIVITY_SAMPLE_STATUS_IDS)
					.setInteger("deptId", microbioParamDto.getDeptId())
					.setInteger("subDeptId", microbioParamDto.getSubDeptId())
					.setInteger("orgUnitId", microbioParamDto.getOrgUnitId())
					.uniqueResult();
			if (sensitivityTestingListCount.compareTo(BigInteger.ZERO) == 1)
			{
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, sensitivityTestingListCount);
			} else
			{
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, BigInteger.ZERO);
			}

		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response getSensitivityTestingDetails(MicrobioParamDto microbioParamDto) throws ApplicationException {
		return null;
	}

	@Override
	public Response saveSensitivityTestingObservation(SensitivityTestResultMasterDto sensitivityTestResultMasterDto)
			throws ApplicationException {
		try
		{
			if (sensitivityTestResultMasterDto !=null)
			{
					SensitivityTestResultMaster sensitivityTestResultMaster = mapper.map(sensitivityTestResultMasterDto,
							SensitivityTestResultMaster.class, "SensitivityTestResultMasterDtoToSensitivityTestResultMaster");
					sensitivityTestResultMaster.setOrgId(sensitivityTestResultMasterDto.getOrgId());
					sensitivityTestResultMaster.setOrgUnitId(sensitivityTestResultMasterDto.getOrgUnitId());
					sensitivityTestResultMaster.setCreatedDate(new Date().getTime());
					Response<SensitivityTestResultMaster, Integer> sensitivityTestResultMste = iSensitivityTestResultMasterDao.saveSensitivityTestResultMasterDao(sensitivityTestResultMaster);
                      
				if(sensitivityTestResultMste.getCode().equals(SUCCESS_200))
				{
					return new Response(SUCCESS, SUCCESS_200, SUCCESS_200, null, null);
				}else
				{
					return new Response(ERROR, ERR_500, ERR_500, null, null);
				}
			
			}
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, SAMPLE_REJECT_SUCC, null, null);
		}
		return null;
	}

	
	@Override
	public Response getSensitivityDetails(Integer labSampleDtlsId, Integer orgId, Integer orgUnitId)
			throws ApplicationException {
		try {
			SensitivityTestResultMasterDto listSensitivityTestResultMasterDto = (SensitivityTestResultMasterDto) sessionFactory
					.getCurrentSession().getNamedQuery("GET_SENSITIVITY_EXAMINATION_MASTER_BY_RESULT_ID")
					.setInteger("labSampleDtlsId", labSampleDtlsId)
					.setInteger("orgId", orgId)
					.setInteger("orgUnitId", orgUnitId)
					.setResultTransformer(Transformers.aliasToBean(SensitivityTestResultMasterDto.class))
					.uniqueResult();
					
				if (listSensitivityTestResultMasterDto != null) 
				{
					

					List<SensitivityTestResultDetailsMasterDto> listSensitivityTestResultDetailsMasterDtoMst = (List<SensitivityTestResultDetailsMasterDto>) sessionFactory
							.getCurrentSession().getNamedQuery("GET_SENSITIVITY_EXAMINATION_DETAILS")
							.setInteger("sensitivityResultId", listSensitivityTestResultMasterDto.getSensitivityResultId())
							.setInteger("orgId", orgId)
							.setInteger("orgUnitId", orgUnitId)
							.setResultTransformer(Transformers.aliasToBean(SensitivityTestResultDetailsMasterDto.class))
							.list();

					if (listSensitivityTestResultDetailsMasterDtoMst != null) 
					{
						List<SensitivityTestResultDetailsMasterDto> listMicroscopicExaminationDetailsMasterDto = new ArrayList<>();
						for (Iterator iterator2 = listSensitivityTestResultDetailsMasterDtoMst.iterator(); iterator2
								.hasNext();) {

							SensitivityTestResultDetailsMasterDto sensitivityTestResultDetailsMasterDto = (SensitivityTestResultDetailsMasterDto) iterator2
									.next();

					
							
							List<SensitivityTestResultSubDetailsMasterDto> listSensitivityTestResultSubDetailsMaster = (List<SensitivityTestResultSubDetailsMasterDto>) sessionFactory
									.getCurrentSession().getNamedQuery("GET_SENSITIVITY_EXAMINATION_SUB_DETAILS")
									.setInteger("sesitivityResultDetailsId",
											sensitivityTestResultDetailsMasterDto.getSesitivityResultDetailsId())
									.setInteger("orgId", orgId)
									.setInteger("orgUnitId",orgUnitId)
									.setResultTransformer(Transformers.aliasToBean(SensitivityTestResultSubDetailsMasterDto.class))
									.list();
							
							if(listSensitivityTestResultSubDetailsMaster!=null)
							{
								List<SensitivityTestResultSubDetailsMasterDto> listsensitivityTestResultSubDetailsMasterDto = new ArrayList<>();
								for (Iterator iterator3 = listSensitivityTestResultSubDetailsMaster.iterator(); iterator3
										.hasNext();) {
									SensitivityTestResultSubDetailsMasterDto sensitivityTestResultSubDetailsMasterDto = (SensitivityTestResultSubDetailsMasterDto) iterator3
											.next();
									

									listsensitivityTestResultSubDetailsMasterDto
											.add(sensitivityTestResultSubDetailsMasterDto);
								}
								sensitivityTestResultDetailsMasterDto.setListSensitivityTestResultSubDetailsMaster(
										listsensitivityTestResultSubDetailsMasterDto);
								listMicroscopicExaminationDetailsMasterDto.add(sensitivityTestResultDetailsMasterDto);
							}

							
						}
						listSensitivityTestResultMasterDto.setListSensitivityTestResultDetailsMaster(
								listSensitivityTestResultDetailsMasterDtoMst);
					}

				}

			return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null,listSensitivityTestResultMasterDto);

		} catch (HibernateException exception) {
			logger.error("Exection", exception);
		} catch (Exception e) {
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}

	@Override
	public Response updateLabSampleDetailsMaster(SensitivityTestResultMasterDto sensitivityTestResultMasterDto)
			throws ApplicationException 
	{
		/*try {
			Integer labSampleDetailsStatus = (Integer) sessionFactory.getCurrentSession()
					.getNamedQuery("UPDATE_LAB_SAMPLE_DETAILS_MICRO")
					.setInteger("updatedBy", sensitivityTestResultMasterDto.getCreatedBy())
					.setDate("updatedDate", new Date())
					.setInteger("sampleStatausId", FINAL_REPORT_RELEASED)
					.setInteger("labSampleDtlsId", sensitivityTestResultMasterDto.getLabSampleDtlsId()).executeUpdate();
			if (labSampleDetailsStatus > 0) {
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, labSampleDetailsStatus);
			} else {
				return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, new Integer(0));
			}
		} catch (HibernateException exception) {
			logger.error("Exection", exception);
		} catch (Exception e) {
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}*/
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}
	
	

}
