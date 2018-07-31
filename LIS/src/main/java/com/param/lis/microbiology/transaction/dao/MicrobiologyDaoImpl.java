package com.param.lis.microbiology.transaction.dao;

import java.math.BigInteger;
import java.util.Arrays;
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
import com.param.entity.lis.global.SampleMaster;
import com.param.entity.lis.micro.TMicroSampleMedia;
import com.param.entity.lis.transaction.LabSampleDetailsMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.ITransactionConstants;
import com.param.lis.global.common.Response;
import com.param.lis.microbiology.transaction.dto.TMicroSampleMediaDto;
import com.param.lis.transaction.dto.BioChemParamDto;
import com.param.lis.transaction.dto.LabSampleDetailsMasterDto;
import com.param.lis.transaction.dto.SampleAcceptancePendingDto;
import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;


@Repository
@SuppressWarnings({"rawtypes", "unchecked"})
public class MicrobiologyDaoImpl extends GenericDao<LabSampleDetailsMaster, Integer>
    implements IMicrobiologyDao, ICommonConstants, ITransactionConstants, IError {
  @Autowired
  private SessionFactory sessionFactory;

  public MicrobiologyDaoImpl() {
    super(LabSampleDetailsMaster.class);
  }

  @Autowired
  private DozerBeanMapper mapper;

  final static Logger logger = Logger.getLogger(MicrobiologyDaoImpl.class);

  @Override
  public Response microAcceptancePending(BioChemParamDto bioChemParamDto)
      throws ApplicationException {
    List<SampleAcceptancePendingDto> listSampleAcceptancePendingDto = null;
    try {
      listSampleAcceptancePendingDto = sessionFactory.getCurrentSession()
          .getNamedQuery("MICROBIOLOGY_WORKLIST_BY_STATUS")
          .setInteger("orgId", bioChemParamDto.getOrgId())
          .setInteger("orgUnitId", bioChemParamDto.getOrgUnitId())
          .setParameterList("sampleStatusIds", Arrays.asList(SAMPLE_COLLECTED))
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
  public Response microAcceptancePendingCount(BioChemParamDto bioChemParamDto)
      throws ApplicationException {
    try {
      BigInteger sampleAcceptancePendingCount = (BigInteger) sessionFactory.getCurrentSession()
          .getNamedQuery("MICROBIOLOGY_WORKLIST_COUNT_BY_STATUS")
          .setInteger("orgId", bioChemParamDto.getOrgId())
          .setParameterList("sampleStatusIds", Arrays.asList(SAMPLE_COLLECTED))
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
  public Response microChemistryAcceptRejectSample(
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
            labSampleDetailsMaster.setCreatedBy(labSampleDetailsMasterDto.getCreatedBy());
            labSampleDetailsMaster.setCreatedDate(new Date().getTime());
            update(labSampleDetailsMaster);
            flag = 1;
          }
        }
        if (flag == 0) {
          return new Response(SUCCESS, SUCCESS_200, SAMPLE_ACCEPT_SUCC, null, null);
        } else {
          return new Response(ERROR, ERR_500, SAMPLE_REJECT_SUCC, null, null);
        }

      }
    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, SAMPLE_REJECT_SUCC, null, null);
    }
    return null;

  }

  @Override
  public Response getMicroChemistryWorklist(BioChemParamDto bioChemParamDto)
      throws ApplicationException {
    List<SampleAcceptancePendingDto> listSampleAcceptancePendingDto = null;
    try {
      listSampleAcceptancePendingDto = sessionFactory.getCurrentSession()
          .getNamedQuery("MICROBIOLOGY_WORKLIST_BY_STATUS")
          .setInteger("orgId", bioChemParamDto.getOrgId())
          .setInteger("orgUnitId", bioChemParamDto.getOrgUnitId())
          .setParameterList("sampleStatusIds", Arrays.asList(SAMPLE_ACCEPTED))
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
  public Response getMicroChemistryWorklistCount(BioChemParamDto bioChemParamDto)
      throws ApplicationException {
    try {
      BigInteger sampleAcceptancePendingCount = (BigInteger) sessionFactory.getCurrentSession()
          .getNamedQuery("MICROBIOLOGY_WORKLIST_COUNT_BY_STATUS")
          .setInteger("orgId", bioChemParamDto.getOrgId())
          .setParameterList("sampleStatusIds", Arrays.asList(SAMPLE_ACCEPTED))
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
  public Response sendForIncubationObjservation(
      List<LabSampleDetailsMasterDto> listlabSampleDetailsMasterDto) throws ApplicationException {
    try {
      Integer labSampleDetailsStatus = 0;
      if (!listlabSampleDetailsMasterDto.isEmpty()) {
        for (Iterator iterator = listlabSampleDetailsMasterDto.iterator(); iterator.hasNext();) {
          LabSampleDetailsMasterDto labSampleDetailsMasterDto =
              (LabSampleDetailsMasterDto) iterator.next();
          labSampleDetailsStatus = (Integer) sessionFactory.getCurrentSession()
              .getNamedQuery("UPDATE_LAB_SAMPLE_DETAILS")
              .setInteger("updatedBy", labSampleDetailsMasterDto.getCreatedBy())
              .setDate("updatedDate", labSampleDetailsMasterDto.getCreatedDate())
              .setInteger("sampleStatausId", SEND_FOR_INCUBATION_OBERVATION)
              .setInteger("labSampleDtlsId", labSampleDetailsMasterDto.getLabSampleDtlsId())
              .executeUpdate();
        }
        if (labSampleDetailsStatus > 0) {
          return new Response(SUCCESS, SUCCESS_200, SAMPLE_SEND_TO_INCUBATION_OBJSERVATION_SUCC,
              null, null);
        } else {
          return new Response(ERROR, ERR_500, SAMPLE_SEND_TO_INCUBATION_OBJSERVATION_FAIL, null,
              null);
        }
      } else {
        return new Response(ERROR, ERR_500, SAMPLE_SEND_TO_INCUBATION_OBJSERVATION_FAIL, null,
            null);
      }

    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, SAMPLE_SEND_TO_INCUBATION_OBJSERVATION_FAIL, null, null);
    }
  }


  @Override
  public Response updateLabSampleDetailsMaster(LabSampleDetailsMasterDto labSampleDetailsMasterDto)
      throws ApplicationException {
    try {
      Integer labSampleDetailsStatus =
          (Integer) sessionFactory.getCurrentSession().getNamedQuery("UPDATE_LAB_SAMPLE_DETAILS")
              .setInteger("updatedBy", labSampleDetailsMasterDto.getCreatedBy())
              .setDate("updatedDate", labSampleDetailsMasterDto.getCreatedDate())
              .setInteger("sampleStatausId", labSampleDetailsMasterDto.getSampleStatusId())
              .setInteger("labSampleDtlsId", labSampleDetailsMasterDto.getLabSampleDtlsId())
              .executeUpdate();
      if (labSampleDetailsStatus > 0) {
        return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null,
            labSampleDetailsStatus);
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

  @Override
  public Response getAddedMediaForIncuabation(Integer labSampleDtlsId, Integer orgId,
      Integer orgUniId) throws ApplicationException {
    List<TMicroSampleMediaDto> listTMicroSampleMediaDto = null;
    try {
      listTMicroSampleMediaDto = sessionFactory.getCurrentSession()
          .getNamedQuery("GET_INCUBATION_MEDIA")
          .setInteger("orgId", orgId)
          .setInteger("orgUnitId", orgUniId)
          .setInteger("labSampleDtlsId", labSampleDtlsId)
          .setResultTransformer(Transformers.aliasToBean(TMicroSampleMediaDto.class)).list();
      return new Response(SUCCESS, SUCCESS_200, null, listTMicroSampleMediaDto, null);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  public Response addMediaForIncuabation(List<TMicroSampleMediaDto> listTMicroSampleMediaDto)
      throws ApplicationException {

    try {
      if (!listTMicroSampleMediaDto.isEmpty()) {
        for (Iterator iterator = listTMicroSampleMediaDto.iterator(); iterator.hasNext();) {
          TMicroSampleMediaDto tMicroSampleMediaDto = (TMicroSampleMediaDto) iterator.next();
          TMicroSampleMedia tMicroSampleMedia =
              mapper.map(tMicroSampleMediaDto, TMicroSampleMedia.class, "TMicroSampleMediaDtoToTMicroSampleMedia");
          sessionFactory.getCurrentSession().save(tMicroSampleMedia);
        }
        return new Response(SUCCESS, SUCCESS_200, MEDIA_ADD_SUCC, null, null);
      }
      else {
        return new Response(ERROR, ERR_500, MEDIA_ADD_FAIL, null, null);
      }

    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, MEDIA_ADD_FAIL, null, null);
    }
  }
}
