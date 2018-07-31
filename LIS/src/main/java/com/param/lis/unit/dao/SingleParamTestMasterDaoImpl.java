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
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.param.entity.lis.unit.HelpValueMaster;
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
import com.param.lis.unit.dto.ParamTextualRanageMasterDto;
import com.param.lis.unit.dto.ParameterMasterDto;
import com.param.lis.unit.dto.TestMasterDto;
import com.param.lis.unit.dto.TestParamMpprDto;
import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({"rawtypes", "unchecked"})
public class SingleParamTestMasterDaoImpl extends GenericDao<TestMaster, Integer>
    implements ISingleParamTestMasterDao, ICommonConstants, IError, ITransactionConstants {

  public SingleParamTestMasterDaoImpl() {
    super(TestMaster.class);
  }

  @Autowired
  private DozerBeanMapper mapper;

  @Autowired
  private SessionFactory sessionFactory;

  @Autowired
  private IParameterMasterDao iParameterMasterDao;

  final static Logger logger = Logger.getLogger(SingleParamTestMasterDaoImpl.class);

  @Override
  public Response addSingleParameterTest(TestMasterDto testMasterDto) throws ApplicationException {
    try {
      TestMaster testMaster =
          mapper.map(testMasterDto, TestMaster.class, "TestMasterDtoTOTestMaster");
      testMaster.setCreatedDate(new Date().getTime());
      TestMaster testMst = save(testMaster);
      if (testMst != null) {
        ParameterMasterDto parameterMasterDto = testMasterDto.getListParameterMasterDto().get(0);
        parameterMasterDto.setTestType(SINGLE_PARAM_TEST);
        parameterMasterDto.setIsMultyparameter('N');
        parameterMasterDto.setStatus('A');
        parameterMasterDto.setCreatedDate(new Date().getTime());
        Response<ParameterMaster, Integer> paramRes =
            iParameterMasterDao.addParameter(parameterMasterDto);
        if (paramRes.getObject() != null) {
          TestParamMppr testParamMppr = new TestParamMppr();
          testParamMppr.setTestId(testMst.getTestId());
          testParamMppr.setParameterId(paramRes.getObject().getParameterId());
          testParamMppr.setParaSequence(0);
          testParamMppr.setHeaderId(null);
          testParamMppr.setCreatedBy(testMaster.getCreatedBy());
          testParamMppr.setCreatedDate(new Date().getTime());
          testParamMppr.setOrgId(testMaster.getOrgId());
          testParamMppr.setOrgUnitId(testMaster.getOrgUnitId());
          testParamMppr.setIsDeleted('N');
          testParamMppr.setTestParaStatus('A');
          sessionFactory.getCurrentSession().save(testParamMppr);
          return new Response(SUCCESS, SUCCESS_200, TEST_ADD_SUCC, null, null);
        } else {
          return new Response(SUCCESS, SUCCESS_200, TEST_ADD_FAIL, null, null);
        }

      } else {
        return new Response(SUCCESS, SUCCESS_200, TEST_ADD_FAIL, null, null);
      }
    } catch (HibernateException exception) {
      logger.error("Exection", exception);
    } catch (Exception e) {
      logger.error("Exection", e);
    }
    return new Response(ERROR, ERR_500, TEST_ADD_FAIL, null, null);
  }

  @Override
  public Response getSingleParameterTest(Integer orgId, Integer orgUnitId, Integer testId)
      throws ApplicationException {
    try {
      List<ParameterMasterDto> listParameterMasterDto = new ArrayList();
      TestMaster testMaster = (TestMaster) sessionFactory.getCurrentSession()
          .getNamedQuery("GET_TEST_BY_TEST_ID").setInteger("orgId", orgId)
          .setInteger("orgUnitId", orgUnitId).setInteger("testId", testId)
          .setInteger("testType", SINGLE_PARAM_TEST).uniqueResult();
      TestMasterDto testMasterDto = testMaster != null
          ? mapper.map(testMaster, TestMasterDto.class, "TestMasterDtoTOTestMaster")
          : null;
      if (testMasterDto != null) {
        for (Iterator iterator = testMasterDto.getListTestParamMppr().iterator(); iterator
            .hasNext();) {
          TestParamMpprDto testParamMpprDto = (TestParamMpprDto) iterator.next();
          Response<ParameterMasterDto, Integer> paramRes = iParameterMasterDao
              .getParameterById(orgId, orgUnitId, testParamMpprDto.getParameterId());
          ParameterMasterDto parameterMasterDto = paramRes.getObject();
          listParameterMasterDto.add(parameterMasterDto);
        }
        testMasterDto.setListParameterMasterDto(listParameterMasterDto);
      }

      return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, testMasterDto);

    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, TEST_NOT_FOUND, null, null);
    }
  }

  @Override
  public Response updateSingleParameterTest(TestMasterDto testMasterDto)
      throws ApplicationException {
    try {
      TestMaster testMaster =
          mapper.map(testMasterDto, TestMaster.class, "TestMasterDtoTOTestMaster");
      TestMaster testMst = update(testMaster);
      if (testMst != null) {
        for (Iterator iterator = testMasterDto.getListParameterMasterDto().iterator(); iterator
            .hasNext();) {
          ParameterMasterDto parameterMasterDto = (ParameterMasterDto) iterator.next();
          parameterMasterDto.setTestType(SINGLE_PARAM_TEST);
          parameterMasterDto.setIsMultyparameter('N');
          parameterMasterDto.setUpdatedBy(testMst.getCreatedBy());
          parameterMasterDto.setUpdatedDate(new Date().getTime());
          Response<ParameterMaster, Integer> paramRes =
              iParameterMasterDao.updateParameter(parameterMasterDto);
          if (paramRes.getObject() != null) {
            for (Iterator iterator2 = testMst.getListTestParamMppr().iterator(); iterator2
                .hasNext();) {
              TestParamMppr testParamMppr = (TestParamMppr) iterator2.next();
              testParamMppr.setTestParaStatus('A');
              testParamMppr.setIsDeleted('N');
              testParamMppr.setUpdatedBy(testMaster.getUpdatedBy());
              testParamMppr.setUpdatedDate(new Date().getTime());
              testParamMppr.setOrgId(testMaster.getOrgId());
              testParamMppr.setOrgUnitId(testMaster.getOrgUnitId());
              sessionFactory.getCurrentSession().update(testParamMppr);
            }
            return new Response(SUCCESS, SUCCESS_200, TEST_UPDATE_SUCC, null, null);
          } else {
            return new Response(SUCCESS, SUCCESS_200, TEST_UPDATE_SUCC, null, null);
          }
        }
      } else {
        return new Response(SUCCESS, SUCCESS_200, TEST_UPDATE_FAIL, null, null);
      }
    } catch (HibernateException exception) {
      logger.error("Exection", exception);
    } catch (Exception e) {
      logger.error("Exection", e);
    }
    return new Response(ERROR, ERR_500, TEST_UPDATE_FAIL, null, null);
  }

  @Override
  public Response activeInactiveTest(Integer orgId, Integer orgUnitId, Integer testId,
      Integer parameterId, Character testStatus) throws ApplicationException {
    try {
      int count = 0;
      Integer helpValueStatus = 0;
      TestMaster testMaster = findById(testId);
      testMaster.setTestStatus(testStatus);
      TestMaster testMst = update(testMaster);
      count++;
      if (count > 0) {
        for (Iterator iterator = testMst.getListTestParamMppr().iterator(); iterator.hasNext();) {
          TestParamMppr testParamMppr = (TestParamMppr) iterator.next();
          testParamMppr.setTestParaStatus(testStatus);
          sessionFactory.getCurrentSession().update(testParamMppr);
          count++;
        }
        if (count > 1) {
          ParameterMaster parameterMaster = (ParameterMaster) sessionFactory.getCurrentSession()
              .get(ParameterMaster.class, parameterId);
          parameterMaster.setStatus(testStatus);
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
          if (result > 0 || helpValueStatus > 0) {
            if (testMst.getTestStatus() == ACTIVE) {
              return new Response(SUCCESS, SUCCESS_200, TEST_ACTIVATE_SUCC, null, null);
            } else if (testMst.getTestStatus() == INACTIVE) {
              return new Response(SUCCESS, SUCCESS_200, TEST_INACTIVATE_SUCC, null, null);
            }
          } else if (testMst.getTestStatus() == INACTIVE) {
            return new Response(SUCCESS, SUCCESS_200, TEST_INACTIVATE_FAIL, null, null);
          } else if (testMst.getTestStatus() == INACTIVE) {
            return new Response(SUCCESS, SUCCESS_200, TEST_INACTIVATE_FAIL, null, null);
          }
        }
      }
    } catch (HibernateException exception) {
      logger.error("Exection", exception);
    } catch (Exception e) {
      logger.error("Exection", e);
    }
    return new Response(ERROR, ERR_500, TEST_UPDATE_FAIL, null, null);

  }

  @Override
  public Response listTestMaster(Integer orgId, Character isHistoCyto, Integer orgUnitId,
      Integer offset, Integer recordPerPage) throws ApplicationException {

    List<TestMasterDto> testMasterDtoList = new ArrayList();
    try {
      List<ParameterMasterDto> listParameterMasterDto = new ArrayList();
      List<ParamRefrengMasterDto> listParamRefrengMasterDto = new ArrayList();

      List<TestMaster> testMasterList = (List<TestMaster>) sessionFactory.getCurrentSession()
          .getNamedQuery("GET_TEST_MASTER_LIST").setInteger("orgId", orgId)
          .setInteger("orgUnitId", orgUnitId).setCharacter("isHistoCyto", isHistoCyto)
          .setInteger("testType", SINGLE_PARAM_TEST).setFirstResult(offset != null ? offset : 0)
          .setMaxResults(recordPerPage != null ? recordPerPage : 10).list();

      for (Iterator iterator = testMasterList.iterator(); iterator.hasNext();) {
        listParameterMasterDto.clear();
        TestMaster testMaster = (TestMaster) iterator.next();
        TestMasterDto testMasterDto =
            mapper.map(testMaster, TestMasterDto.class, "TestMasterDtoTOTestMaster");
        testMasterDto.setSampleName(testMaster.getSampleMaster().getDesc());

        if (isHistoCyto == 'N') {
          testMasterDto.setTechniqueName(testMaster.getTechniqueMaster() == null ? ""
              : testMaster.getTechniqueMaster().getDesc());
          testMasterDto.setPrerequisitName(testMaster.getPrerequisitesMaster() == null ? ""
              : testMaster.getPrerequisitesMaster().getDesc());
        } else if (isHistoCyto == 'Y') {
          testMasterDto.setContainerName(testMaster.getContainerMaster().getDesc());
          testMasterDto.setReportTypeName(testMaster.getReportTypeMaster().getDesc());
          testMasterDto.setSpecimanName(testMaster.getSpecimanMaster().getDesc());
        }

        testMasterDto
            .setNormlTime(testMaster.getNorTurnAroundTimeMaster().getTatTimeName() == null ? ""
                : testMaster.getNorTurnAroundTimeMaster().getTatTimeName());
        testMasterDto
            .setUrgentTime(testMaster.getUrgTurnAroundTimeMaster().getTatTimeName() == null ? ""
                : testMaster.getUrgTurnAroundTimeMaster().getTatTimeName());
        testMasterDto.setSampleName(testMaster.getSampleMaster().getDesc());
        testMasterDto.setDeptName(testMaster.getSubSpecialityMaster().getSubSpecialityMasterName());
        for (Iterator iterator1 = testMasterDto.getListTestParamMppr().iterator(); iterator1
            .hasNext();) {
          listParamRefrengMasterDto.clear();
          TestParamMpprDto testParamMpprDto = (TestParamMpprDto) iterator1.next();

          Response<ParameterMasterDto, Integer> response =
              iParameterMasterDao.getParameterById(testParamMpprDto.getOrgId(),
                  testParamMpprDto.getOrgUnitId(), testParamMpprDto.getParameterId());
          ParameterMasterDto parameterMasterDto = (ParameterMasterDto) response.getObject();

          if (isHistoCyto == 'N') {
            List<HelpValueMasterDto> listHelpValueMasterDto =
                parameterMasterDto.getListHelpValueMaster().stream()
                    .filter(help -> help.getIsDeleted() != 'Y').collect(Collectors.toList());
            // parameterMasterDto.setListParamRefrengMaster(listParamRefrengMasterDto);
            parameterMasterDto.setListHelpValueMaster(listHelpValueMasterDto);
          }

          listParameterMasterDto.add(parameterMasterDto);
          // parameterMasterDto.setDeltaDays(parameterMst.getLabDayMaster().getDay()!=null?parameterMst.getLabDayMaster().getDay():null);
          listParameterMasterDto.add(parameterMasterDto);
        }
        List<ParameterMasterDto> newParamList = new ArrayList<>(listParameterMasterDto);
        testMasterDto.setListParameterMasterDto(newParamList);
        testMasterDtoList.add(testMasterDto);
      }

      return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, testMasterDtoList, null);

    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, TEST_NOT_FOUND, null, null);
    }
  }

  @Override
  public Response getTotalTestRecord(Integer orgId, Character isHistoCyto, Integer orgUnitId)
      throws ApplicationException {
    try {
      BigInteger testMasterTotalRecordCount = (BigInteger) sessionFactory.getCurrentSession()
          .getNamedQuery("GET_TOTAL_TEST_RECORD").setInteger("orgId", orgId)
          .setCharacter("isHistoCyto", isHistoCyto).setInteger("orgUnitId", orgUnitId)
          .setInteger("testType", SINGLE_PARAM_TEST).uniqueResult();
      if (testMasterTotalRecordCount.compareTo(BigInteger.ZERO) == 1) {
        return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null,
            testMasterTotalRecordCount);
      } else {
        return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, BigInteger.ZERO);
      }

    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  public Response checkServiceAvaiable(TestMasterDto testMasterDto) throws ApplicationException {
    try {
      BigInteger serviceCount = (BigInteger) sessionFactory.getCurrentSession()
          .getNamedQuery("GET_TOTAL_TEST_IN_SERVICE_RECORD")
          .setInteger("orgId", testMasterDto.getOrgId())
          .setInteger("orgUnitId", testMasterDto.getOrgUnitId())
          .setInteger("serviceId", testMasterDto.getServiceId()).uniqueResult();
      return new Response(SUCCESS, null, null, null, serviceCount);
    } catch (Exception e) {
      e.printStackTrace();
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

}
