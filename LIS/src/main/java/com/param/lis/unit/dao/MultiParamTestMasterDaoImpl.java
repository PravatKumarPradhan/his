package com.param.lis.unit.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.param.entity.lis.unit.ParamRefrengMaster;
import com.param.entity.lis.unit.ParameterMaster;
import com.param.entity.lis.unit.TestMaster;
import com.param.entity.lis.unit.TestParamMppr;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.ITransactionConstants;
import com.param.lis.global.common.Response;
import com.param.lis.unit.dto.HelpValueMasterDto;
import com.param.lis.unit.dto.ParamRefrengMasterDto;
import com.param.lis.unit.dto.ParameterMasterDto;
import com.param.lis.unit.dto.TestMasterDto;
import com.param.lis.unit.dto.TestParamMpprDto;
import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings(
{ "rawtypes", "unchecked" })
public class MultiParamTestMasterDaoImpl extends GenericDao<TestMaster, Integer>
implements IMultiParamTestMasterDao, ICommonConstants, IError, ITransactionConstants
{

	public MultiParamTestMasterDaoImpl() {
		super(TestMaster.class);
	}
	
	@Autowired
	private DozerBeanMapper mapper;

	@Autowired
	private SessionFactory sessionFactory;

	
	 @Autowired
	 private IParameterMasterDao iParameterMasterDao;

	
	
	@Autowired
	private ITestParamMpprDao iTestParamMpprDao;
	
	final static Logger logger = Logger.getLogger(MultiParamTestMasterDaoImpl.class);

	@Override
	public Response addMultiParameterTest(TestMasterDto testMasterDto) throws ApplicationException
	{
		try
		{
			TestMaster testMaster = mapper.map(testMasterDto, TestMaster.class, "TestMasterDtoTOTestMaster");
			testMaster.setCreatedBy(testMasterDto.getCreatedBy());
			testMaster.setUpdatedDate(new Date().getTime());
			TestMaster testMst = save(testMaster);
			if (testMst != null)
			{
				for (Iterator iterator = testMst.getListTestParamMppr().iterator(); iterator.hasNext();)
				{
					    TestParamMppr testParamMppr= (TestParamMppr) iterator.next();
					    testParamMppr.setTestId(testMst.getTestId());
					    testParamMppr.setCreatedBy(testMaster.getCreatedBy());
					    testParamMppr.setCreatedDate(new Date().getTime());
					    testParamMppr.setOrgId(testMaster.getOrgId());
					    testParamMppr.setOrgUnitId(testMaster.getOrgUnitId());
					    sessionFactory.getCurrentSession().save(testParamMppr);
				}
				return new Response(SUCCESS, SUCCESS_200, TEST_ADD_SUCC, null, null);
			} else
			{
				return new Response(SUCCESS, SUCCESS_200, TEST_ADD_FAIL, null, null);
			}
		} catch (HibernateException exception)
		{
			logger.error("Exception", exception);
		} catch (Exception e)
		{
			logger.error("Exception", e);
		}
		return new Response(ERROR, ERR_500, TEST_ADD_FAIL, null, null);
	}

	@Override
	public Response getMultyParameterTest(Integer orgId, Integer orgUnitId, Integer testId) throws ApplicationException
	{
		try
		{
			List<ParameterMasterDto> listParameterMasterDto = new ArrayList();
			List<TestParamMpprDto> listTestParamMpprDto = new ArrayList();
			TestMaster testMaster = (TestMaster) sessionFactory.getCurrentSession().getNamedQuery("GET_TEST_BY_TEST_ID")
					.setInteger("orgId", orgId)
					.setInteger("orgUnitId", orgUnitId)
					.setInteger("testId", testId)
					.setInteger("testType", MULTY_PARAM_TEST)
					.uniqueResult();
			TestMasterDto testMasterDto = testMaster!=null?mapper.map(testMaster, TestMasterDto.class, "TestMasterDtoTOTestMaster"):null;
			if (testMasterDto != null)
			{
				for (Iterator iterator = testMaster.getListTestParamMppr().iterator(); iterator.hasNext();)
				{
					TestParamMppr testParamMppr = (TestParamMppr) iterator.next();
					if(testParamMppr.getIsDeleted()=='N')
					{
					  Response<ParameterMasterDto, Integer> response =  iParameterMasterDao.getParameterById(testParamMppr.getOrgId(), testParamMppr.getOrgUnitId(), testParamMppr.getParameterId());
	                  ParameterMasterDto parameterMasterDto = (ParameterMasterDto) response.getObject();
	                  TestParamMpprDto testParamMpprDto = testParamMppr != null
	                                    ? mapper.map(testParamMppr, TestParamMpprDto.class, "TestParamMpprDtoToTestParamMppr")
	                                    : null;     
	                    testParamMpprDto.setParameterDesc(parameterMasterDto.getParameterName());
	                    testParamMpprDto.setHeaderDesc(testParamMppr.getHeaderMaster().getDesc());
	                    parameterMasterDto.setUnitName(parameterMasterDto.getUnitName());
	                    listParameterMasterDto.add(parameterMasterDto);
	                    listTestParamMpprDto.add(testParamMpprDto);     
	                    listParameterMasterDto.add(parameterMasterDto);
					}
				
				}
				testMasterDto.setListParameterMasterDto(listParameterMasterDto);
				testMasterDto.setListTestParamMppr(listTestParamMpprDto);
			}
			
			return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, testMasterDto);

		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, TEST_NOT_FOUND, null, null);
		}
	}

	@Override
	public Response updateMultiParameterTest(TestMasterDto testMasterDto) throws ApplicationException
	{
		try
		{
			TestMaster testMaster = mapper.map(testMasterDto, TestMaster.class, "TestMasterDtoTOTestMaster");
			testMaster.setUpdatedBy(testMasterDto.getCreatedBy());
			testMaster.setUpdatedDate(new  Date().getTime());
			TestMaster testMst = update(testMaster);
			if (testMst != null)
			{
				for (Iterator iterator = testMst.getListTestParamMppr().iterator(); iterator.hasNext();)
				{
					TestParamMppr testParamMpr= (TestParamMppr) iterator.next();
					if(testParamMpr.getTestPerMpprId()!=0)
					{
						
						testParamMpr.setUpdatedBy(testMaster.getUpdatedBy());
						testParamMpr.setUpdatedDate(new Date().getTime());
						testParamMpr.setOrgId(testMaster.getOrgId());
						testParamMpr.setOrgUnitId(testMaster.getOrgUnitId());
						sessionFactory.getCurrentSession().update(testParamMpr);
					}
					else if(testParamMpr.getTestPerMpprId()==0)
					{
						testParamMpr.setCreatedBy(testMaster.getUpdatedBy());
						testParamMpr.setCreatedDate(new Date().getTime());
						testParamMpr.setOrgId(testMaster.getOrgId());
						testParamMpr.setOrgUnitId(testMaster.getOrgUnitId());
						iTestParamMpprDao.addTestParamMppr(testParamMpr);
					}
					return new Response(SUCCESS, SUCCESS_200, TEST_UPDATE_SUCC, null, null);
				}
			} else
			{
				return new Response(SUCCESS, SUCCESS_200, TEST_UPDATE_FAIL, null, null);
			}
		} catch (HibernateException exception)
		{
			logger.error("HibernateException", exception);
		} catch (Exception e)
		{
			logger.error("Exception", e);
		}
		return new Response(ERROR, ERR_500, TEST_UPDATE_FAIL, null, null);
	}

	@Override
	public Response activeInactiveMultiParamTest(Integer orgId, Integer orgUnitId, Integer testId,
			Character testStatus) throws ApplicationException
	{
		try
		{
			TestMaster testMaster = findById(testId);
			testMaster.setTestStatus(testStatus);
			TestMaster testMst = update(testMaster);
			Integer helpValueStatus = 0;
			if (testMst != null)
			{
				for (Iterator iterator = testMst.getListTestParamMppr().iterator(); iterator.hasNext();)
				{
					TestParamMppr testParamMppr = (TestParamMppr) iterator.next();
					testParamMppr.setTestParaStatus(testStatus);
					sessionFactory.getCurrentSession().update(testParamMppr);
					 ParameterMaster parameterMaster = (ParameterMaster) sessionFactory.getCurrentSession()
			              .get(ParameterMaster.class, testParamMppr.getParameterId());
			          Integer result = 0;
			          Integer paraResult = sessionFactory.getCurrentSession()
			              .getNamedQuery("UPDATE_PARAMETER_STATUS").setCharacter("status", testStatus)
			              .setInteger("parameterId", parameterMaster.getParameterId()).executeUpdate();
			          if (paraResult > 0) {
			            if (parameterMaster.getRefrangeTypeId() == REFERENCE_VALUE) {
			              result = sessionFactory.getCurrentSession().getNamedQuery("UPDATE_REF_RANGE_MASTER")
			                  .setCharacter("status", testStatus)
			                  .setInteger("parameterId", parameterMaster.getParameterId()).executeUpdate();
			            } else if (parameterMaster.getRefrangeTypeId() == TEXTUAL_RANGE) {
			              result = sessionFactory.getCurrentSession().getNamedQuery("UPDATE_TEXTUAL_STATUS")
			                  .setCharacter("status", testStatus)
			                  .setInteger("parameterId", parameterMaster.getParameterId()).executeUpdate();
			            } else if (parameterMaster.getRefrangeTypeId() == MULTIVALUED_RANGE) {
			              result = sessionFactory.getCurrentSession()
			                  .getNamedQuery("UPDATE_MULTIVALUED_TEXTUAL_STATUS")
			                  .setCharacter("status", testStatus)
			                  .setInteger("parameterId", parameterMaster.getParameterId()).executeUpdate();
			            }
			             helpValueStatus = sessionFactory.getCurrentSession()
			                .getNamedQuery("UPDATE_HELP_MASTER_STATUS").setCharacter("status", testStatus)
			                .setInteger("parameterId", parameterMaster.getParameterId()).executeUpdate();
			          }
					if(result>0||helpValueStatus>0)
					{
					  if (testMst.getTestStatus() == ACTIVE)
		                {
		                    return new Response(SUCCESS, SUCCESS_200, TEST_ACTIVATE_SUCC, null, null);
		                } else if (testMst.getTestStatus() == INACTIVE)
		                {
		                    return new Response(SUCCESS, SUCCESS_200, TEST_ACTIVATE_FAIL, null, null);
		                }
					}
					else 
					{
					  if (testMst.getTestStatus() == INACTIVE)
                      {
                          return new Response(SUCCESS, SUCCESS_200, TEST_INACTIVATE_SUCC, null, null);
                          
                      } else if (testMst.getTestStatus() == INACTIVE)
                      {
                          return new Response(SUCCESS, SUCCESS_200, TEST_INACTIVATE_FAIL, null, null);
                      }
					}
				}
			
			}

		} catch (HibernateException exception)
		{
			logger.error("Exection", exception);
		} catch (Exception e)
		{
			logger.error("Exection", e);
		}
		return new Response(ERROR, ERR_500, TEST_UPDATE_FAIL, null, null);
	}

	@Override
	public Response listMultiParamTestMaster(Integer orgId, Character isHistoCyto,Integer orgUnitId, Integer offset, Integer recordPerPage)
			throws ApplicationException
	{
		List<TestMasterDto> testMasterDtoList = new ArrayList();
		try
		{
			List<TestMaster> testMasterList = (List<TestMaster>) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_TEST_MASTER_LIST")
					.setInteger("orgId", orgId)
					.setCharacter("isHistoCyto", isHistoCyto) 
					.setInteger("orgUnitId", orgUnitId)
					.setInteger("testType", MULTY_PARAM_TEST)
					.setFirstResult(offset != null ? offset : 0)
					.setMaxResults(recordPerPage != null ? recordPerPage : 10).list();
			for (Iterator iterator = testMasterList.iterator(); iterator.hasNext();)
			{
				TestMaster testMaster = (TestMaster) iterator.next();
				TestMasterDto testMasterDto = mapper.map(testMaster, TestMasterDto.class, "TestMasterDtoTOTestMaster");
				testMasterDto.setSampleName(testMaster.getSampleMaster().getDesc());
				testMasterDto.setTechniqueName(testMaster.getTechniqueMaster().getDesc());
				testMasterDto.setPrerequisitName(testMaster.getPrerequisitesMaster().getDesc());
				testMasterDto.setNormlTime(testMaster.getNorTurnAroundTimeMaster().getTatTimeName()==null?"":
					                     testMaster.getNorTurnAroundTimeMaster().getTatTimeName());
			    testMasterDto.setUrgentTime(testMaster.getUrgTurnAroundTimeMaster().getTatTimeName()==null?"":
			    							testMaster.getUrgTurnAroundTimeMaster().getTatTimeName());
				testMasterDto.setSampleName(testMaster.getSampleMaster().getDesc());
				testMasterDto.setDeptName(testMaster.getSubSpecialityMaster().getSubSpecialityMasterName());
			    List<ParameterMasterDto> listParameterMasterDto = new ArrayList();
	            List<TestParamMpprDto> listTestParamMpprDto = new ArrayList();
				for (Iterator iterator1 = testMaster.getListTestParamMppr().iterator(); iterator1.hasNext();)
				{
				  TestParamMppr testParamMppr = (TestParamMppr) iterator1.next();
				  if(testParamMppr.getIsDeleted()=='N'&& testParamMppr.getTestId().intValue()==testMaster.getTestId())
                  {
					 Response<ParameterMasterDto, Integer> response =
			              iParameterMasterDao.getParameterById(testParamMppr.getOrgId(),
			                  testParamMppr.getOrgUnitId(), testParamMppr.getParameterId());
			          ParameterMasterDto parameterMasterDto = (ParameterMasterDto) response.getObject();
					TestParamMpprDto testParamMpprDto = testParamMppr != null
									? mapper.map(testParamMppr, TestParamMpprDto.class, "TestParamMpprDtoToTestParamMppr")
									: null;		
					testParamMpprDto.setParameterDesc(parameterMasterDto.getParameterName());
					testParamMpprDto.setHeaderDesc(testParamMppr.getHeaderMaster().getDesc());
					listParameterMasterDto.add(parameterMasterDto);
					listTestParamMpprDto.add(testParamMpprDto);
                  }
				}
				testMasterDto.setListParameterMasterDto(listParameterMasterDto);
				testMasterDto.setListTestParamMppr(listTestParamMpprDto);
				testMasterDtoList.add(testMasterDto);
			}

			return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, testMasterDtoList, null);

		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, TEST_NOT_FOUND, null, null);
		}
	}

	@Override
	public Response getTotalMultiTestRecord(Integer orgId,Character isHistoCyto, Integer orgUnitId) throws ApplicationException
	{
		try
		{
			BigInteger testMasterTotalRecordCount = (BigInteger) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_TOTAL_TEST_RECORD")
					.setInteger("orgId", orgId)
					.setCharacter("isHistoCyto", isHistoCyto)
					.setInteger("orgUnitId", orgUnitId)
					.setInteger("testType", MULTY_PARAM_TEST)
					.uniqueResult();
			if (testMasterTotalRecordCount.compareTo(BigInteger.ZERO) == 1)
			{
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, testMasterTotalRecordCount);
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
