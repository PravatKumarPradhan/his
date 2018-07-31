package com.param.lis.transaction.dao;

import java.math.BigInteger;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.param.entity.lis.global.ReportHistoryMaster;
import com.param.entity.lis.transaction.LabResultDetailsMaster;
import com.param.entity.lis.transaction.LabResultMaster;
import com.param.entity.lis.transaction.LabSampleDetailsMaster;
import com.param.lis.common.dto.CommonAutoCompleteDto;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.ITransactionConstants;
import com.param.lis.global.common.Response;
import com.param.lis.global.common.SearchDto;
import com.param.lis.global.common.StringCommonMethods;
import com.param.lis.transaction.dto.BioChemParamDto;
import com.param.lis.transaction.dto.LabDashBoardDto;
import com.param.lis.transaction.dto.LabResultDetailsViewDto;
import com.param.lis.transaction.dto.LabResultMasterDto;
import com.param.lis.transaction.dto.LabSampleDetailsMasterDto;
import com.param.lis.transaction.dto.ParameterHistoryDto;
import com.param.lis.transaction.dto.ParameterResultDto;
import com.param.lis.transaction.dto.ParameterSearchDto;
import com.param.lis.transaction.dto.ResultEntryDataDto;
import com.param.lis.transaction.dto.SampleAcceptancePendingDto;
import com.param.lis.transaction.dto.SearchCommonDto;
import com.param.lis.transaction.dto.SingParamTestResDto;
import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;


@Repository
@SuppressWarnings({"rawtypes", "unchecked"})
public class BioChemistryDaoImpl extends GenericDao<LabSampleDetailsMaster, Integer>
    implements IBioChemistryDao, ICommonConstants, ITransactionConstants, IError {
  @Autowired
  private SessionFactory sessionFactory;

  public BioChemistryDaoImpl() {
    super(LabSampleDetailsMaster.class);
  }

  final static Logger logger = Logger.getLogger(BioChemistryDaoImpl.class);

  @Autowired
  private DozerBeanMapper mapper;


  @Override
  public Response searchbioChemistryPatientlist(SearchCommonDto searchCommonDto)
      throws ApplicationException {
    List<CommonAutoCompleteDto> patientList = null;
    try {
      patientList = (List<CommonAutoCompleteDto>) sessionFactory.getCurrentSession()
          .getNamedQuery("SEARCH_BIOCHECMISTRY_PATIENT_LIST")
          .setInteger("orgId", searchCommonDto.getOrgId())
          .setInteger("orgUnitId", searchCommonDto.getOrgUnitId())
          .setInteger("deptId", searchCommonDto.getDeptId())
          .setInteger("subDeptId", searchCommonDto.getSubDeptId())
          .setParameterList("sampleStatusIds", searchCommonDto.getSampleStatusIds())
          .setParameterList("visitTypes", searchCommonDto.getVisitTypes())
          .setString("searchKeyword",
              "%" + searchCommonDto.getSearchKeyword().trim().toLowerCase().trim() + "%")
          .setResultTransformer(Transformers.aliasToBean(CommonAutoCompleteDto.class)).list();
      return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, patientList, null);

    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  public Response searchBioChemistrySampleNo(SearchCommonDto searchCommonDto)
      throws ApplicationException {
    List<LabDashBoardDto> patientList = null;
    try {
      patientList = (List<LabDashBoardDto>) sessionFactory.getCurrentSession()
          .getNamedQuery("SEARCH_BIOCHECMISTRY_SAMPLE_NO_LIST")
          .setInteger("orgId", searchCommonDto.getOrgId())
          .setInteger("orgUnitId", searchCommonDto.getOrgUnitId())
          .setInteger("deptId", searchCommonDto.getDeptId())
          .setInteger("subDeptId", searchCommonDto.getSubDeptId())
          .setParameterList("sampleStatusIds", searchCommonDto.getSampleStatusIds())
          .setParameterList("visitTypes", searchCommonDto.getVisitTypes())
          .setString("searchKeyword",
              "%" + searchCommonDto.getSearchKeyword().trim().toLowerCase().trim() + "%")
          .setResultTransformer(Transformers.aliasToBean(LabDashBoardDto.class)).list();
      return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, patientList, null);

    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  public Response getFilteredBioChemistryList(SearchDto searchDto) throws ApplicationException {
    try {

      String getAcceptPendingQuery = sessionFactory.getCurrentSession()
          .getNamedQuery("GET_ACCEPTANCE_PENDING_LIST").getQueryString().toString();

      String[] orderSplit = getAcceptPendingQuery.split("ORDER", 2);

      String orderPart1 = orderSplit[0];
      String orderPart2 = "ORDER " + orderSplit[1];


      StringBuilder getWorklistForDepartmentQueryQueryPart1 = new StringBuilder(orderPart1);


      if (searchDto.getFromDate() != null && !searchDto.getFromDate().isEmpty()) {
        /*
         * getDashBoardSearchQuery.append(" AND labSampleDetailsMst.createdDate > '"+searchDto.
         * getFromDate()+"' ");
         */
        getWorklistForDepartmentQueryQueryPart1.append(
            " AND lbsampledtls.sample_collection_datetime > '" + searchDto.getFromDate() + "' ");
      }

      if (searchDto.getToDate() != null && !searchDto.getToDate().isEmpty()) {
        /*
         * getDashBoardSearchQuery.append(" AND labSampleDetailsMst.createdDate < '"+searchDto.
         * getToDate()+"' ");
         */
        getWorklistForDepartmentQueryQueryPart1.append(
            " AND lbsampledtls.sample_collection_datetime < '" + searchDto.getToDate() + "' ");
      }

      if (searchDto.getPatientId() != null && searchDto.getPatientId() > 0)
        getWorklistForDepartmentQueryQueryPart1
            .append(" AND pati_mst.patient_id = " + searchDto.getPatientId());

      if (searchDto.getVisitTypes() != null && searchDto.getVisitTypes().size() > 0)
        getWorklistForDepartmentQueryQueryPart1.append(" AND visit_type_master.visit_type_id IN ("
            + StringCommonMethods.convertToStringJoiner(searchDto.getVisitTypes()) + ")");

      if (searchDto.getSubDepts() != null && searchDto.getSubDepts().size() > 0)
        getWorklistForDepartmentQueryQueryPart1.append(" AND test_mst.dept_id IN ( "
            + StringCommonMethods.convertToStringJoiner(searchDto.getSubDepts()) + ")");

      if (searchDto.getLabSampleDtlsId() != null && searchDto.getLabSampleDtlsId() > 0)
        getWorklistForDepartmentQueryQueryPart1
            .append(" AND lbsampledtls.lab_sample_dtls_id =" + searchDto.getLabSampleDtlsId());

      StringBuilder getAcceptPendingSearchQueryPart2 = new StringBuilder(orderPart2);
      getWorklistForDepartmentQueryQueryPart1.append(getAcceptPendingSearchQueryPart2);

      Query createdQuery = sessionFactory.getCurrentSession()
          .createSQLQuery(getWorklistForDepartmentQueryQueryPart1.toString());


      List<SampleAcceptancePendingDto> listSampleAcceptancePendingDto = createdQuery
          .setInteger("orgId", searchDto.getOrgId())
          .setInteger("orgUnitId", searchDto.getOrgUnitId())
          .setInteger("sampleStatusId", SAMPLE_SEND_TO_DEPT)
          .setInteger("deptId", searchDto.getDeptId())
          .setInteger("subDeptId", searchDto.getSubDeptId())
          .setResultTransformer(Transformers.aliasToBean(SampleAcceptancePendingDto.class)).list();


      if (listSampleAcceptancePendingDto.isEmpty()) {
        return new Response(SUCCESS, SUCCESS_200_MESSAGE, "No Records Found.",
            listSampleAcceptancePendingDto, null);
      } else {
        return new Response(SUCCESS, SUCCESS_200_MESSAGE, null, listSampleAcceptancePendingDto,
            null);
      }


    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  public Response getFilteredWorklistForDepartment(SearchDto searchDto)
      throws ApplicationException {
    try {

      String getWorklistForDepartmentQuery = sessionFactory.getCurrentSession()
          .getNamedQuery("GET_BIOCHEMISTRY_WORKLIST").getQueryString().toString();

      String[] orderSplit = getWorklistForDepartmentQuery.split("ORDER", 2);

      String orderPart1 = orderSplit[0];
      String orderPart2 = "ORDER " + orderSplit[1];


      StringBuilder getWorklistForDepartmentQueryQueryPart1 = new StringBuilder(orderPart1);


      if (searchDto.getFromDate() != null && !searchDto.getFromDate().isEmpty()) {
        /*
         * getDashBoardSearchQuery.append(" AND labSampleDetailsMst.createdDate > '"+searchDto.
         * getFromDate()+"' ");
         */
        getWorklistForDepartmentQueryQueryPart1.append(
            " AND lbsampledtls.sample_collection_datetime > '" + searchDto.getFromDate() + "' ");
      }

      if (searchDto.getToDate() != null && !searchDto.getToDate().isEmpty()) {
        /*
         * getDashBoardSearchQuery.append(" AND labSampleDetailsMst.createdDate < '"+searchDto.
         * getToDate()+"' ");
         */
        getWorklistForDepartmentQueryQueryPart1.append(
            " AND lbsampledtls.sample_collection_datetime < '" + searchDto.getToDate() + "' ");
      }

      if (searchDto.getPatientId() != null && searchDto.getPatientId() > 0)
        getWorklistForDepartmentQueryQueryPart1
            .append(" AND pati_mst.patient_id = " + searchDto.getPatientId());

      if (searchDto.getVisitTypes() != null && searchDto.getVisitTypes().size() > 0)
        getWorklistForDepartmentQueryQueryPart1.append(" AND visit_type_master.visit_type_id IN ("
            + StringCommonMethods.convertToStringJoiner(searchDto.getVisitTypes()) + ")");

      if (searchDto.getSubDepts() != null && searchDto.getSubDepts().size() > 0)
        getWorklistForDepartmentQueryQueryPart1.append(" AND test_mst.dept_id IN ( "
            + StringCommonMethods.convertToStringJoiner(searchDto.getSubDepts()) + ")");

      if (searchDto.getLabSampleDtlsId() != null && searchDto.getLabSampleDtlsId() > 0)
        getWorklistForDepartmentQueryQueryPart1
            .append(" AND lbsampledtls.lab_sample_dtls_id =" + searchDto.getLabSampleDtlsId());

      StringBuilder getWorklistForDepartmentQueryPart2 = new StringBuilder(orderPart2);
      getWorklistForDepartmentQueryQueryPart1.append(getWorklistForDepartmentQueryPart2);

      Query createdQuery = sessionFactory.getCurrentSession()
          .createSQLQuery(getWorklistForDepartmentQueryQueryPart1.toString());


      List<SampleAcceptancePendingDto> listSampleAcceptancePendingDto = createdQuery

          .setInteger("orgId", searchDto.getOrgId())
          .setInteger("orgUnitId", searchDto.getOrgUnitId())
          .setParameterList("sampleStatusIds", bioChemStatus)
          .setInteger("deptId", searchDto.getDeptId())
          .setInteger("subDeptId", searchDto.getSubDeptId())
          .setResultTransformer(Transformers.aliasToBean(SampleAcceptancePendingDto.class)).list();


      if (listSampleAcceptancePendingDto.isEmpty()) {
        return new Response(SUCCESS, SUCCESS_200_MESSAGE, "No Records Found.",
            listSampleAcceptancePendingDto, null);
      } else {
        return new Response(SUCCESS, SUCCESS_200_MESSAGE, null, listSampleAcceptancePendingDto,
            null);
      }


    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  public Response sampleAcceptancePending(BioChemParamDto bioChemParamDto)
      throws ApplicationException {
    List<SampleAcceptancePendingDto> listSampleAcceptancePendingDto = null;
    try {
      listSampleAcceptancePendingDto = sessionFactory.getCurrentSession()
          .getNamedQuery("GET_ACCEPTANCE_PENDING_LIST")
          .setInteger("orgId", bioChemParamDto.getOrgId())
          .setInteger("orgUnitId", bioChemParamDto.getOrgUnitId())
          .setInteger("sampleStatusId", SAMPLE_SEND_TO_DEPT)
          .setInteger("deptId", bioChemParamDto.getDeptId())
          .setInteger("subDeptId", bioChemParamDto.getSubDeptId())
          .setFirstResult(bioChemParamDto.getOffset() != null ? bioChemParamDto.getOffset() : 0)
          .setMaxResults(
              bioChemParamDto.getRecordPerPage() != null ? bioChemParamDto.getRecordPerPage() : 10)
          .setResultTransformer(Transformers.aliasToBean(SampleAcceptancePendingDto.class)).list();
      return new Response(SUCCESS, SUCCESS_200, null, listSampleAcceptancePendingDto, null);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  public Response sampleAcceptancePendingCount(BioChemParamDto bioChemParamDto)
      throws ApplicationException {
    try {
      BigInteger sampleAcceptancePendingCount = (BigInteger) sessionFactory.getCurrentSession()
          .getNamedQuery("GET_ACCEPTANCE_PENDING_COUNT")
          .setInteger("orgId", bioChemParamDto.getOrgId())
          .setInteger("sampleStatusId", SAMPLE_SEND_TO_DEPT)
          .setInteger("deptId", bioChemParamDto.getDeptId())
          .setInteger("subDeptId", bioChemParamDto.getSubDeptId())
          .setInteger("orgUnitId", bioChemParamDto.getOrgUnitId()).uniqueResult();
      if (sampleAcceptancePendingCount.compareTo(BigInteger.ZERO) == 1) {
        return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null,
            sampleAcceptancePendingCount);
      } else {
        return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, BigInteger.ZERO);
      }

    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  public Response bioChemistryAcceptRejectSample(
      List<LabSampleDetailsMasterDto> listlabSampleDetailsMasterDto) throws ApplicationException {
    try {
      Integer flag = null;
      LabSampleDetailsMaster labSampleDetailsMaster;
      if (!listlabSampleDetailsMasterDto.isEmpty()) {
        for (Iterator iterator = listlabSampleDetailsMasterDto.iterator(); iterator.hasNext();) {
          LabSampleDetailsMasterDto labSampleDetailsMasterDto =
              (LabSampleDetailsMasterDto) iterator.next();
          labSampleDetailsMaster = findById(labSampleDetailsMasterDto.getLabSampleDtlsId());
          if (labSampleDetailsMasterDto.getCurrStatus().equals(ACCEPT)) {
            labSampleDetailsMaster.setSampleAcceptBy(labSampleDetailsMasterDto.getSampleAcceptBy());
            labSampleDetailsMaster
                .setSamplePendingCount(labSampleDetailsMasterDto.getSamplePendingCount());
            labSampleDetailsMaster.setSampleAcceptDatetime(new Date().getTime());
            labSampleDetailsMaster.setSampleStatusId(SAMPLE_ACCEPTED);
            update(labSampleDetailsMaster);
            flag = 0;

          } else if (labSampleDetailsMasterDto.getCurrStatus().equals(REJECT)) {
            labSampleDetailsMaster.setSampleStatusId(SAMPLE_REJECTED);
            labSampleDetailsMaster
                .setSampleRejectReason(labSampleDetailsMasterDto.getSampleRejectReason());
            labSampleDetailsMaster
                .setSampleRejectReasonId(labSampleDetailsMasterDto.getSampleRejectReasonId());
            labSampleDetailsMaster.setSampleRejectBy(labSampleDetailsMasterDto.getSampleRejectBy());
            labSampleDetailsMaster.setUpdatedBy(labSampleDetailsMasterDto.getUpdatedBy());
            labSampleDetailsMaster.setUpdatedDate(new Date().getTime());
            update(labSampleDetailsMaster);
            flag = 1;
          }
        }
        if (flag == 0) {
          return new Response(SUCCESS, SUCCESS_200, SAMPLE_ACCEPT_SUCC, null, null);
        } else {
          return new Response(ERROR, ERR_500, SAMPLE_REJECT_SUCC, null, null);
        }
        // return new Response(SUCCESS, SUCCESS_200, SAMPLE_COLLECT_SUCC, null, null);
      }
    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, SAMPLE_REJECT_SUCC, null, null);
    }
    return null;

  }

  @Override
  public Response getBioChemistryWorklist(BioChemParamDto bioChemParamDto)
      throws ApplicationException {
    List<SampleAcceptancePendingDto> listSampleAcceptancePendingDto = null;
    try {
      listSampleAcceptancePendingDto = sessionFactory.getCurrentSession()
          .getNamedQuery("GET_BIOCHEMISTRY_WORKLIST")
          .setInteger("orgId", bioChemParamDto.getOrgId())
          .setInteger("orgUnitId", bioChemParamDto.getOrgUnitId())
          .setParameterList("sampleStatusIds", bioChemStatus)
          .setInteger("deptId", bioChemParamDto.getDeptId())
          .setInteger("subDeptId", bioChemParamDto.getSubDeptId())
          .setFirstResult(bioChemParamDto.getOffset() != null ? bioChemParamDto.getOffset() : 0)
          .setMaxResults(
              bioChemParamDto.getRecordPerPage() != null ? bioChemParamDto.getRecordPerPage() : 10)
          .setResultTransformer(Transformers.aliasToBean(SampleAcceptancePendingDto.class)).list();
      return new Response(SUCCESS, SUCCESS_200, null, listSampleAcceptancePendingDto, null);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  public Response getBioChemistryWorklistCount(BioChemParamDto bioChemParamDto)
      throws ApplicationException {
    try {
      BigInteger sampleAcceptancePendingCount = (BigInteger) sessionFactory.getCurrentSession()
          .getNamedQuery("GET_BIOCHEMISTRY_WORKLIST_COUNT")
          .setInteger("orgId", bioChemParamDto.getOrgId())
          .setParameterList("sampleStatusIds", bioChemStatus)
          .setInteger("deptId", bioChemParamDto.getDeptId())
          .setInteger("subDeptId", bioChemParamDto.getSubDeptId())
          .setInteger("orgUnitId", bioChemParamDto.getOrgUnitId()).uniqueResult();
      if (sampleAcceptancePendingCount.compareTo(BigInteger.ZERO) == 1) {
        return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null,
            sampleAcceptancePendingCount);
      } else {
        return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, BigInteger.ZERO);
      }

    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  public Response sendToResultEntry(List<LabSampleDetailsMasterDto> listlabSampleDetailsMasterDto)
      throws ApplicationException {
    try {
      LabSampleDetailsMaster labSampleDetailsMaster;
      if (!listlabSampleDetailsMasterDto.isEmpty()) {
        for (Iterator iterator = listlabSampleDetailsMasterDto.iterator(); iterator.hasNext();) {
          LabSampleDetailsMasterDto labSampleDetailsMasterDto =
              (LabSampleDetailsMasterDto) iterator.next();
          labSampleDetailsMaster = findById(labSampleDetailsMasterDto.getLabSampleDtlsId());
          labSampleDetailsMaster.setUpdatedBy(labSampleDetailsMasterDto.getUpdatedBy());
          labSampleDetailsMaster.setUpdatedDate(new Date().getTime());
          labSampleDetailsMaster.setSampleStatusId(SEND_TO_REPORT_ENTRY);
          update(labSampleDetailsMaster);
        }
      }
      return new Response(SUCCESS, SUCCESS_200, SAMPLE_SEND_TO_RES_ENTRY_SUCC, null, null);
    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, SAMPLE_SEND_TO_RES_ENTRY_FAIL, null, null);
    }
  }

  @Override
  public Response sampleRetest(List<LabResultMasterDto> listLabResultMasterDto)
      throws ApplicationException {

    try {

      for (Iterator iterator = listLabResultMasterDto.iterator(); iterator.hasNext();) {
        LabResultMasterDto labResultMasterDto = (LabResultMasterDto) iterator.next();
        LabResultMaster labResultMaster = mapper.map(labResultMasterDto, LabResultMaster.class,
            "LabResultMasterDtoToLabResultMaster");
        labResultMaster.setResultEnterBy(labResultMasterDto.getCreatedBy());
        labResultMaster.setCreatedDate(new Date().getTime());
        labResultMaster.setCreatedBy(labResultMasterDto.getCreatedBy());
        labResultMaster.setResultEnterDatetime(new Date().getTime());
        Integer labResultId = (Integer) sessionFactory.getCurrentSession().save(labResultMaster);
        LabSampleDetailsMaster labSampleDetailsMaster =
            (LabSampleDetailsMaster) sessionFactory.getCurrentSession()
                .get(LabSampleDetailsMaster.class, labResultMasterDto.getLabSampleDtlsId());
        labSampleDetailsMaster.setUpdatedBy(labResultMasterDto.getUpdatedBy());
        labSampleDetailsMaster.setUpdatedDate(new Date().getTime());
        labSampleDetailsMaster.setSampleStatusId(RETESTING);
        sessionFactory.getCurrentSession().save(labSampleDetailsMaster);
        for (Iterator iterator1 =
            labResultMasterDto.getListLabSampleResultDetailsMaster().iterator(); iterator1
                .hasNext();) {
          LabResultDetailsViewDto labResultDetailsMasterDto =
              (LabResultDetailsViewDto) iterator1.next();
          LabResultDetailsMaster labResultDetailsMaster = mapper.map(labResultDetailsMasterDto,
              LabResultDetailsMaster.class, "LabResultDetailsViewDtoTOLabResultDetailsMaster");
          labResultDetailsMaster.setLabResultId(labResultId);
          labResultDetailsMaster.setFirstLevelResult(labResultDetailsMaster.getFinalResult());
          labResultDetailsMaster.setCreatedBy(labResultMasterDto.getCreatedBy());
          labResultDetailsMaster.setCreatedDate(new Date().getTime());
          sessionFactory.getCurrentSession().save(labResultDetailsMaster);
        }

      }
      return new Response(SUCCESS, SUCCESS_200, SAMPLE_RETEST_SUCC, null, null);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, SAMPLE_RETEST_FAIL, null, null);
    }
  }

  @Override
  public Response biochemFinalReportRelease(LabSampleDetailsMasterDto labSampleDetailsMasterDto)
      throws ApplicationException {
    try {
      LabSampleDetailsMaster labSampleDetailsMaster = null;
      if (labSampleDetailsMasterDto != null) {

        labSampleDetailsMaster = findById(labSampleDetailsMasterDto.getLabSampleDtlsId());
        labSampleDetailsMaster.setUpdatedBy(labSampleDetailsMasterDto.getUpdatedBy());
        labSampleDetailsMaster.setUpdatedDate(new Date().getTime());
        labSampleDetailsMaster.setSampleStatusId(FINAL_REPORT_RELEASED);
        labSampleDetailsMaster = update(labSampleDetailsMaster);
      }

      return new Response(SUCCESS, SUCCESS_200, SAMPLE_RETEST_SUCC, null, labSampleDetailsMaster);


    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, SAMPLE_RETEST_FAIL, null, null);
    }
  }

  @Override
  public Response sampleRecollect(List<LabResultMasterDto> listLabResultMasterDto)
      throws ApplicationException {


    try {

      for (Iterator iterator = listLabResultMasterDto.iterator(); iterator.hasNext();) {
        LabResultMasterDto labResultMasterDto = (LabResultMasterDto) iterator.next();
        LabResultMaster labResultMaster = mapper.map(labResultMasterDto, LabResultMaster.class,
            "LabResultMasterDtoToLabResultMaster");
        labResultMaster.setResultEnterBy(labResultMasterDto.getCreatedBy());
        labResultMaster.setCreatedDate(new Date().getTime());
        labResultMaster.setCreatedBy(labResultMasterDto.getCreatedBy());
        labResultMaster.setResultEnterDatetime(new Date().getTime());
        Integer labResultId = (Integer) sessionFactory.getCurrentSession().save(labResultMaster);
        LabSampleDetailsMaster labSampleDetailsMaster =
            (LabSampleDetailsMaster) sessionFactory.getCurrentSession()
                .get(LabSampleDetailsMaster.class, labResultMasterDto.getLabSampleDtlsId());
        labSampleDetailsMaster.setUpdatedBy(labResultMasterDto.getUpdatedBy());
        labSampleDetailsMaster.setUpdatedDate(new Date().getTime());
        labSampleDetailsMaster.setSampleStatusId(SAMPLE_REJECTED);
        sessionFactory.getCurrentSession().update(labSampleDetailsMaster);
        for (Iterator iterator1 =
            labResultMasterDto.getListLabSampleResultDetailsMaster().iterator(); iterator1
                .hasNext();) {
          LabResultDetailsViewDto labResultDetailsMasterDto =
              (LabResultDetailsViewDto) iterator1.next();
          LabResultDetailsMaster labResultDetailsMaster = mapper.map(labResultDetailsMasterDto,
              LabResultDetailsMaster.class, "LabResultDetailsViewDtoTOLabResultDetailsMaster");
          labResultDetailsMaster.setLabResultId(labResultId);
          labResultDetailsMaster.setFirstLevelResult(labResultDetailsMaster.getFinalResult());
          labResultDetailsMaster.setCreatedBy(labResultMasterDto.getCreatedBy());
          labResultDetailsMaster.setCreatedDate(new Date().getTime());
          sessionFactory.getCurrentSession().save(labResultDetailsMaster);
        }

      }
      return new Response(SUCCESS, SUCCESS_200, SAMPLE_SEND_RECOLLECT_SUCC, null, null);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, SAMPLE_SEND_RECOLLECT_FAIL, null, null);
    }

  }

  @Override
  public Response resultEntryData(ResultEntryDataDto resultEntryDataDto)
      throws ApplicationException {
    List<ResultEntryDataDto> listResultEntryDataDto = null;
    try {
      String level_data = "";
      switch (resultEntryDataDto.getFirstLevelResult()) {
        case "firstLevel":
          level_data = ".first_level_result";
          break;

        case "secondLevel":
          level_data = ".second_level_result";
          break;

        case "thirdLevel":
          level_data = ".third_level_result";
          break;

        default:
          level_data = "";
          break;
      }


      listResultEntryDataDto = (List<ResultEntryDataDto>) sessionFactory.getCurrentSession()
          .createSQLQuery("  SELECT " + " COALESCE(labresdtls" + level_data
              + ",0) AS \"firstlevelresult\" " + " FROM " + " lab.t_lab_res_dtls labresdtls "
              + " INNER JOIN lab.t_lab_test_result labtestresult ON "
              + " labtestresult.lab_sample_dtls_id =:labSampleDetailsId "
              + " AND labresdtls.org_id = :orgId " + " AND labresdtls.org_unit_id = :orgUnitId "
              + " WHERE " + " labresdtls.lab_result_id = labtestresult.lab_test_res_id "
              + " ORDER BY labtestresult.lab_test_res_id DESC ")
          .setInteger("labSampleDetailsId", resultEntryDataDto.getSampleRecollectAgainstId())
          .setInteger("orgId", resultEntryDataDto.getOrgId())
          .setInteger("orgUnitId", resultEntryDataDto.getOrgUnitId())
          .setResultTransformer(Transformers.aliasToBean(ResultEntryDataDto.class)).list();
      return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, listResultEntryDataDto, null);


      /*
       * listResultEntryDataDto = sessionFactory.getCurrentSession()
       * .getNamedQuery("GET_FIRST_LEVEL_RESULT") .setInteger("orgId",
       * resultEntryDataDto.getOrgId()) .setInteger("orgUnitId", resultEntryDataDto.getOrgUnitId())
       * .setInteger("labResultId", resultEntryDataDto.getLabResultId())
       * .setResultTransformer(Transformers.aliasToBean(ResultEntryDataDto.class)).list(); return
       * new Response(SUCCESS, SUCCESS_200, null, listResultEntryDataDto, null);
       */
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  public Response addReportHistroyMaster(LabSampleDetailsMaster labSampleDetailsMaster,
      Integer resultId) throws ApplicationException {
    try {
      ReportHistoryMaster reportHistoryMaster = new ReportHistoryMaster();
      reportHistoryMaster.setCreatedBy(labSampleDetailsMaster.getCreatedBy());
      reportHistoryMaster.setCreatedDate(labSampleDetailsMaster.getCreatedDate());
      reportHistoryMaster.setUpdatedBy(labSampleDetailsMaster.getUpdatedBy());
      reportHistoryMaster.setUpdatedDate(labSampleDetailsMaster.getUpdatedDate());
      reportHistoryMaster.setDeptId(labSampleDetailsMaster.getDeptId());
      reportHistoryMaster.setIsReportRelease('Y');
      reportHistoryMaster.setOrgId(labSampleDetailsMaster.getOrgId());
      reportHistoryMaster.setOrgUnitId(labSampleDetailsMaster.getOrgUnitId());
      reportHistoryMaster.setStatus('A');
      reportHistoryMaster.setPatientId(labSampleDetailsMaster.getPatientId());
      reportHistoryMaster.setResultId(resultId);
      reportHistoryMaster.setSubdeptId(labSampleDetailsMaster.getSubDeptId());
      reportHistoryMaster.setLabSampleDtlsId(labSampleDetailsMaster.getLabSampleDtlsId());
      reportHistoryMaster.setTestId(labSampleDetailsMaster.getTestId());

      Integer result = (Integer) sessionFactory.getCurrentSession().save(reportHistoryMaster);
      if (result > 0) {
        return new Response<>(SUCCESS, SUCCESS_200, REPORT_HISTORY_ADD_SUCC, null, null);
      } else {
        return new Response<>(ERROR, ERR_201, REPORT_HISTORY_ADD_FAIL, null, null);
      }

    } catch (HibernateException he) {
      he.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return new Response<>(ERROR, ERR_201, REPORT_HISTORY_ADD_FAIL, null, null);
  }

  @Override
  public Response getParameterHistory(ParameterSearchDto parameterSearchDto)
      throws ApplicationException {
    try {

      String GET_PARAMETER_FOR_HISTORY = sessionFactory.getCurrentSession()
          .getNamedQuery("GET_PARAMETER_FOR_HISTORY").getQueryString().toString();
      StringBuilder GET_PARAMETER_DETAILS = new StringBuilder(GET_PARAMETER_FOR_HISTORY);
      List<ParameterHistoryDto> parameterHistoryDtoList = null;
      if (parameterSearchDto.getParameterIds().size() > 0
          && !parameterSearchDto.getParameterIds().isEmpty()) {
        GET_PARAMETER_DETAILS.append(" AND parameter_master.parameter_id IN ("
            + StringCommonMethods.convertToStringJoiner(parameterSearchDto.getParameterIds())
            + ")");
        parameterHistoryDtoList = (List<ParameterHistoryDto>) sessionFactory.getCurrentSession()
            .createSQLQuery(GET_PARAMETER_DETAILS.toString())
            .setInteger("testId", parameterSearchDto.getTestId())
            .setInteger("orgId", parameterSearchDto.getOrgId())
            .setInteger("orgUnitId", parameterSearchDto.getOrgUnitId())
            .setResultTransformer(Transformers.aliasToBean(ParameterHistoryDto.class)).list();
      } else {
        parameterHistoryDtoList = (List<ParameterHistoryDto>) sessionFactory.getCurrentSession()
            .createSQLQuery(GET_PARAMETER_DETAILS.toString())
            .setInteger("testId", parameterSearchDto.getTestId())
            .setInteger("orgId", parameterSearchDto.getOrgId())
            .setInteger("orgUnitId", parameterSearchDto.getOrgUnitId())
            .setResultTransformer(Transformers.aliasToBean(ParameterHistoryDto.class)).list();
      }
      if (!parameterHistoryDtoList.isEmpty()) {
        for (Iterator iterator = parameterHistoryDtoList.iterator(); iterator.hasNext();) {
          ParameterHistoryDto parameterHistoryDto = (ParameterHistoryDto) iterator.next();
          List<ParameterResultDto> parameterResultDtoList =
              (List<ParameterResultDto>) sessionFactory.getCurrentSession()
                  .createSQLQuery(
                      " SELECT  "
                      +" CAST(  "
                      +"     lab_res_dtls.final_result AS TEXT  "
                      +" ) AS \"result\",  "
                      +" lab_res_dtls.created_date AS \"resultDateTime\"  "
                      +"FROM  "
                      +" lab.t_lab_test_result lab_test_result  "
                      +"INNER JOIN lab.t_lab_res_dtls lab_res_dtls ON  "
                      +" lab_res_dtls.lab_result_id = lab_test_result.lab_test_res_id  "
                      +"WHERE  "
                      +" lab_test_result.sample_status_id = "+REPORT_RELEASED
                      +" AND lab_test_result.test_id =" + parameterSearchDto.getTestId()
                      +" AND lab_test_result.patient_id = "+ parameterSearchDto.getPatientId()
                      +" AND lab_res_dtls.parameter_id = "+ parameterHistoryDto.getParameterId()
                      +" AND lab_test_result.org_id = "+ parameterSearchDto.getOrgId()
                      +" AND lab_test_result.org_unit_id ="+parameterSearchDto.getOrgUnitId()
                      +" AND lab_test_result.result_level = 3  "
                      +" ORDER BY  "
                      +" lab_res_dtls.created_date DESC LIMIT "+ parameterSearchDto.getLimit())
                  .setResultTransformer(Transformers.aliasToBean(ParameterResultDto.class)).list();
          parameterHistoryDto.setListParameterResultDto(parameterResultDtoList);
        }
        return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, parameterHistoryDtoList,
            null);
      } else {
        return new Response(SUCCESS, SUCCESS_200, "No Parameter Found.", null, null);
      }


    } catch (HibernateException he) {
      he.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return new Response<>(ERROR, ERR_201, REPORT_HISTORY_ADD_FAIL, null, null);
  }

  @Override
  public Response getPreviousResultIdByTest(BioChemParamDto bioChemParamDto)
      throws ApplicationException {
    try {
      Integer labResultId =
          (Integer) sessionFactory.getCurrentSession().getNamedQuery("GET_PRIVIOUS_TEST_RELEASE_ID")
              .setInteger("orgId", bioChemParamDto.getOrgId())
              .setInteger("orgUnitId", bioChemParamDto.getOrgUnitId())
              .setInteger("deptId", bioChemParamDto.getDeptId())
              .setInteger("subDeptId", bioChemParamDto.getSubDeptId())
              .setInteger("sampleStatusId", REPORT_RELEASED)
              .setInteger("patientId", bioChemParamDto.getPatientId())
              .setInteger("testId", bioChemParamDto.getTestId()).uniqueResult();
      if (labResultId != null && labResultId > 0) {
        return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, labResultId);
      } else {
        return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, BigInteger.ZERO);
      }

    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  public Response getRetestedRecollectedResult(BioChemParamDto bioChemParamDto)
      throws ApplicationException {
    List<SingParamTestResDto> listLabResultMasterDto = null;
    try {
      listLabResultMasterDto =
          sessionFactory.getCurrentSession().getNamedQuery("GET_RETEST_RECOLLECT_LAB_RESULT_ID")
              .setInteger("orgId", bioChemParamDto.getOrgId())
              .setInteger("orgUnitId", bioChemParamDto.getOrgUnitId())
              .setInteger("retest", RETESTING)
              .setInteger("recollect", SAMPLE_REJECTED)
              .setInteger("deptId", bioChemParamDto.getDeptId())
              .setInteger("subDeptId", bioChemParamDto.getSubDeptId())
              .setInteger("patientId", bioChemParamDto.getPatientId())
              .setInteger("labSampleDtlsId", bioChemParamDto.getLabSampleDtlsId())
              .setInteger("testId", bioChemParamDto.getTestId())
              .setResultTransformer(Transformers.aliasToBean(SingParamTestResDto.class)).list();
      return new Response(SUCCESS, SUCCESS_200, null, listLabResultMasterDto, null);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  public Response getResultEnteryByDateTime(BioChemParamDto bioChemParamDto)
      throws ApplicationException {
    try {
      SingParamTestResDto singParamTestResDto =
          (SingParamTestResDto) sessionFactory.getCurrentSession().getNamedQuery("GET_RESULT_ENTERY_BY_DATE_TIME")
          .setInteger("orgId", bioChemParamDto.getOrgId())
          .setInteger("orgUnitId", bioChemParamDto.getOrgUnitId())
          .setInteger("labTestResId", bioChemParamDto.getLabResultId())
          .setResultTransformer(Transformers.aliasToBean(SingParamTestResDto.class)).uniqueResult();
      return new Response(SUCCESS, SUCCESS_200, null, null, singParamTestResDto);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  public Response getResultValidatedByDateTime(BioChemParamDto bioChemParamDto)
      throws ApplicationException {
    try {
      SingParamTestResDto singParamTestResDto =
          (SingParamTestResDto) sessionFactory.getCurrentSession().getNamedQuery("GET_RESULT_VALIDATED_BY_DATE_TIME")
          .setInteger("orgId", bioChemParamDto.getOrgId())
          .setInteger("orgUnitId", bioChemParamDto.getOrgUnitId())
          .setInteger("labTestResId", bioChemParamDto.getLabResultId())
          .setResultTransformer(Transformers.aliasToBean(SingParamTestResDto.class)).uniqueResult();
      return new Response(SUCCESS, SUCCESS_200, null, null, singParamTestResDto);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  public Response getResultReleaseByDateTime(BioChemParamDto bioChemParamDto)
      throws ApplicationException {
    try {
      SingParamTestResDto singParamTestResDto =
          (SingParamTestResDto) sessionFactory.getCurrentSession().getNamedQuery("GET_RESULT_RELEASE_BY_DATE_TIME")
          .setInteger("orgId", bioChemParamDto.getOrgId())
          .setInteger("orgUnitId", bioChemParamDto.getOrgUnitId())
          .setInteger("labTestResId", bioChemParamDto.getLabResultId())
          .setResultTransformer(Transformers.aliasToBean(SingParamTestResDto.class)).uniqueResult();
      return new Response(SUCCESS, SUCCESS_200, null, null, singParamTestResDto);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }
}
