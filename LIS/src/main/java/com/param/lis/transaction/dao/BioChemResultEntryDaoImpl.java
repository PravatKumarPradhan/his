package com.param.lis.transaction.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.param.entity.lis.transaction.LabResultDetailsMaster;
import com.param.entity.lis.transaction.LabResultMaster;
import com.param.entity.lis.transaction.LabSampleDetailsMaster;
import com.param.entity.lis.unit.ParamRefrengMaster;
import com.param.entity.lis.unit.ParameterMaster;
import com.param.entity.lis.unit.TestMaster;
import com.param.entity.lis.unit.TestParamMppr;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.ITransactionConstants;
import com.param.lis.global.common.Response;
import com.param.lis.transaction.dto.BioChemParamDto;
import com.param.lis.transaction.dto.LabResultMasterDto;
import com.param.lis.unit.dao.IParameterMasterDao;
import com.param.lis.unit.dto.HelpValueMasterDto;
import com.param.lis.unit.dto.ParamRefrengMasterDto;
import com.param.lis.unit.dto.ParameterMasterDto;
import com.param.lis.unit.dto.TestMasterDto;
import com.param.lis.unit.dto.TestParamMpprDto;
import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class BioChemResultEntryDaoImpl extends GenericDao<LabResultMaster, Integer>
		implements IBioChemResultEntryDao, ICommonConstants, ITransactionConstants, IError {

	public BioChemResultEntryDaoImpl() {
		super(LabResultMaster.class);
	}

	@Autowired
	private DozerBeanMapper mapper;
	
	@Autowired
    private IParameterMasterDao iParameterMasterDao;
	

	// @Autowired
	// private SessionFactory sessionFactory;

	final static Logger logger = Logger.getLogger(BioChemResultEntryDaoImpl.class);

	@Override
	public Response getResultEntryList(BioChemParamDto bioChemParamDto) throws ApplicationException {
		List<LabResultMasterDto> listLabResultMasterDto = null;
		try {
			listLabResultMasterDto = sessionFactory.getCurrentSession().getNamedQuery("GET_BIOCHEM_RESULT_ENTRY_LIST")
					.setInteger("orgId", bioChemParamDto.getOrgId())
					.setInteger("orgUnitId", bioChemParamDto.getOrgUnitId())
					.setInteger("sampleStatusId", SEND_TO_REPORT_ENTRY)
					.setInteger("deptId", bioChemParamDto.getDeptId())
					.setInteger("subDeptId", bioChemParamDto.getSubDeptId())
					.setFirstResult(bioChemParamDto.getOffset() != null ? bioChemParamDto.getOffset() : 0)
					.setMaxResults(bioChemParamDto.getRecordPerPage() != null ? bioChemParamDto.getRecordPerPage() : 10)
					.setResultTransformer(Transformers.aliasToBean(LabResultMasterDto.class)).list();
			return new Response(SUCCESS, SUCCESS_200, null, listLabResultMasterDto, null);
		} catch (Exception e) {
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response getResultEntryListCount(BioChemParamDto bioChemParamDto) throws ApplicationException {
		try {
			BigInteger sampleResultEntryCount = (BigInteger) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_BIOCHEM_RESULT_ENTRY_LIST_COUNT")
					.setInteger("orgId", bioChemParamDto.getOrgId())
					.setInteger("sampleStatusId", SEND_TO_REPORT_ENTRY)
					.setInteger("deptId", bioChemParamDto.getDeptId())
					.setInteger("subDeptId", bioChemParamDto.getSubDeptId())
					.setInteger("orgUnitId", bioChemParamDto.getOrgUnitId()).uniqueResult();
			if (sampleResultEntryCount.compareTo(BigInteger.ZERO) == 1) {
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, sampleResultEntryCount);
			} else {
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, BigInteger.ZERO);
			}

		} catch (Exception e) {
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response getResultEntryDetails(BioChemParamDto bioChemParamDto) throws ApplicationException {
		try {

		} catch (Exception e) {
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, TEST_NOT_FOUND, null, null);
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}

	@Override
	public Response saveEntryDetails(List<LabResultMasterDto> listLabResultMasterDto) throws ApplicationException {
	  

    try {

      for (Iterator iterator = listLabResultMasterDto.iterator(); iterator.hasNext();) {
        LabResultMasterDto labResultMasterDto = (LabResultMasterDto) iterator.next();
        LabResultMaster labResultMaster = mapper.map(labResultMasterDto, LabResultMaster.class,
            "LabResultMasterDtoToLabResultMaster");
        labResultMaster.setResultEnterBy(labResultMasterDto.getCreatedBy());
        labResultMaster.setCreatedDate(new Date().getTime());
        labResultMaster.setCreatedBy(labResultMasterDto.getCreatedBy());
        labResultMaster.setResultEnterDatetime(new Date().getTime());
        LabResultMaster labResultMst = save(labResultMaster);
        
        LabSampleDetailsMaster labSampleDetailsMaster =
            (LabSampleDetailsMaster) sessionFactory.getCurrentSession()
                .get(LabSampleDetailsMaster.class, labResultMst.getLabSampleDtlsId());
        labSampleDetailsMaster.setUpdatedBy(labResultMst.getUpdatedBy());
        labSampleDetailsMaster.setUpdatedDate(new Date().getTime());
        labSampleDetailsMaster.setSampleStatusId(RESULT_ENTRY_DONE);
        sessionFactory.getCurrentSession().update(labSampleDetailsMaster);
        for (Iterator iterator1 =
            labResultMst.getListLabSampleResultDetailsMaster().iterator(); iterator1.hasNext();) {
          LabResultDetailsMaster labResultDetailsMaster = (LabResultDetailsMaster) iterator1.next();
          labResultDetailsMaster.setLabResultId(labResultMst.getLabTestResId());
          labResultDetailsMaster.setFirstLevelResult(labResultDetailsMaster.getFinalResult());
          labResultDetailsMaster.setCreatedBy(labResultMasterDto.getCreatedBy());
          labResultDetailsMaster.setCreatedDate(new Date().getTime());
          sessionFactory.getCurrentSession().save(labResultDetailsMaster);
        }

      }

      return new Response(SUCCESS, SUCCESS_200, SAMPLE_RESULT_ENTRY_SUCC, null, null);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, SAMPLE_RESULT_ENTRY_FAIL, null, null);
    }
  }

	@Override
	public Response getSingleParamTestResult(BioChemParamDto bioChemParamDto) throws ApplicationException {
		try {
			List<ParameterMasterDto> listParameterMasterDto = new ArrayList();
			List<ParamRefrengMasterDto> listParamRefrengMasterDto = new ArrayList();
			TestMaster testMaster = (TestMaster) sessionFactory.getCurrentSession().getNamedQuery("GET_TEST_BY_TEST_ID")
					.setInteger("orgId", bioChemParamDto.getOrgId())
					.setInteger("orgUnitId", bioChemParamDto.getOrgUnitId())
					.setInteger("testId", bioChemParamDto.getTestId())
					.setInteger("testType", SINGLE_PARAM_TEST)
					.uniqueResult();

			TestMasterDto testMasterDto = testMaster != null
					? mapper.map(testMaster, TestMasterDto.class, "TestMasterDtoTOTestMaster") : null;
			testMasterDto.setTechniqueName(testMaster.getTechniqueMaster().getDesc());
			if (testMasterDto != null) {
				for (Iterator iterator = testMasterDto.getListTestParamMppr().iterator(); iterator.hasNext();) 
				{
					TestParamMpprDto testParamMpprDto = (TestParamMpprDto) iterator.next();
				/*	ParameterMaster parameterMst = (ParameterMaster) sessionFactory.getCurrentSession()
							.getNamedQuery("GET_PARAMETER_BY_PARAMETER_ID")
							.setInteger("orgId", bioChemParamDto.getOrgId())
							.setInteger("orgUnitId", bioChemParamDto.getOrgUnitId())
							.setInteger("parameterId", testParamMpprDto.getParameterId())
							.uniqueResult();

					for (Iterator iterator2 = parameterMst.getListParamRefrengMaster().iterator(); iterator2
							.hasNext();) {
						ParamRefrengMaster paramRefrengMaster = (ParamRefrengMaster) iterator2.next();
						if (paramRefrengMaster.getIsDeleted() != 'Y') {
							ParamRefrengMasterDto paramRefrengMasterDto = paramRefrengMaster != null
									? mapper.map(paramRefrengMaster, ParamRefrengMasterDto.class,
											"ParamRefrengMasterDtoToParamRefrengMaster")
									: null;
							paramRefrengMasterDto.setAgeFromDay(paramRefrengMaster.getAgeGroupMaster().getAgeFromDay());
							paramRefrengMasterDto.setAgeToDay(paramRefrengMaster.getAgeGroupMaster().getAgeToday());
							listParamRefrengMasterDto.add(paramRefrengMasterDto);
						}
					}
					ParameterMasterDto parameterMasterDto = parameterMst != null
							? mapper.map(parameterMst, ParameterMasterDto.class, "ParameterMasterDtoTOParameterMaster")
							: null;
					List<HelpValueMasterDto> listHelpValueMasterDto = parameterMasterDto.getListHelpValueMaster()
							.stream().filter(help -> help.getIsDeleted() != 'Y').collect(Collectors.toList());
					parameterMasterDto.setListParamRefrengMaster(listParamRefrengMasterDto);
					parameterMasterDto.setListHelpValueMaster(listHelpValueMasterDto);
					parameterMasterDto.setUnitName(parameterMst.getLabUnitMaster().getDesc());
					parameterMasterDto.setParameterName(parameterMst.getParameterName());*/
					
					 Response<ParameterMasterDto, Integer> paramRes = iParameterMasterDao
			              .getParameterById(bioChemParamDto.getOrgId(), bioChemParamDto.getOrgUnitId(), testParamMpprDto.getParameterId());
			          ParameterMasterDto parameterMasterDto = paramRes.getObject();
					listParameterMasterDto.add(parameterMasterDto);
				}
				testMasterDto.setListParameterMasterDto(listParameterMasterDto);
				testMasterDto.setTechniqueName(testMaster.getTechniqueMaster().getDesc());
			}
			return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, testMasterDto);

		} catch (Exception e) {
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response getMultyParamTestResult(BioChemParamDto bioChemParamDto) throws ApplicationException {
		try {
			List<ParameterMasterDto> listParameterMasterDto = new ArrayList();
			List<TestParamMpprDto> listTestParamMpprDto = new ArrayList();

			TestMaster testMaster = (TestMaster) sessionFactory.getCurrentSession().getNamedQuery("GET_TEST_BY_TEST_ID")
					.setInteger("orgId", bioChemParamDto.getOrgId())
					.setInteger("orgUnitId", bioChemParamDto.getOrgUnitId())
					.setInteger("testId", bioChemParamDto.getTestId()).setInteger("testType", bioChemParamDto.getTestType())
					.uniqueResult();

			TestMasterDto testMasterDto = testMaster != null
					? mapper.map(testMaster, TestMasterDto.class, "TestMasterDtoTOTestMaster") : null;
			testMasterDto.setTechniqueName(testMaster.getTechniqueMaster().getDesc());
			
			if (testMasterDto != null) 
			{
				for (Iterator iterator = testMaster.getListTestParamMppr().iterator(); iterator.hasNext();) 
				{
			      TestParamMppr testParamMppr = (TestParamMppr) iterator.next();
				  if(testMaster.getTestId()==testParamMppr.getTestId().intValue()&&testParamMppr.getIsDeleted()=='N')
				  {
				    TestParamMpprDto testParamMpprDto = testParamMppr != null
                        ? mapper.map(testParamMppr, TestParamMpprDto.class, "TestParamMpprDtoToTestParamMppr")
                        : null;

                     testParamMpprDto.setHeaderDesc(testParamMppr.getHeaderMaster().getDesc());
                     testParamMpprDto.setHeaderCode(testParamMppr.getHeaderMaster().getCode());
                     listTestParamMpprDto.add(testParamMpprDto);
				  }
					
				}
				testMasterDto.setListParameterMasterDto(listParameterMasterDto);
				testMasterDto.setListTestParamMppr(listTestParamMpprDto);
				testMasterDto.setTechniqueName(testMaster.getTechniqueMaster().getDesc());
			}
			return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, testMasterDto);
		} catch (Exception e) {
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

}
