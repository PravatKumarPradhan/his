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
import com.param.entity.lis.micro.MicroscopicExaminationDetailsMaster;
import com.param.entity.lis.micro.MicroscopicExaminationMaster;
import com.param.entity.lis.micro.MicroscopicExaminationSubDetailsMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.ITransactionConstants;
import com.param.lis.global.common.Response;
import com.param.lis.microbiology.transaction.dto.MicrobioParamDto;
import com.param.lis.microbiology.transaction.dto.MicroscopicExaminationDetailsMasterDto;
import com.param.lis.microbiology.transaction.dto.MicroscopicExaminationMasterDto;
import com.param.lis.microbiology.transaction.dto.MicroscopicExaminationSubDetailsMasterDto;
import com.param.lis.microbiology.transaction.dto.TMacroscopicExaminationMasterDto;
import com.param.lis.transaction.dto.SampleAcceptancePendingDto;
import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({"rawtypes", "unchecked"})
public class MicroscopicExaminationDaoImpl extends GenericDao<MicroscopicExaminationMaster, Integer>
    implements IMicroscopicExaminationDao, ICommonConstants, ITransactionConstants, IError {


  @Autowired
  private SessionFactory sessionFactory;

  @Autowired
  private DozerBeanMapper mapper;

  @Autowired
  private ImicroscopicExaminationDetailsMaster imicroscopicExaminationDetailsMaster;

  public MicroscopicExaminationDaoImpl() {
    super(MicroscopicExaminationMaster.class);
  }

  final static Logger logger = Logger.getLogger(MicroscopicExaminationDaoImpl.class);

  @Override
  public Response getMicroScopicExaminationList(MicrobioParamDto microbioParamDto)
      throws ApplicationException {
    List<SampleAcceptancePendingDto> listMicrobioResultEntryMasterDto = null;
    try {
      listMicrobioResultEntryMasterDto = sessionFactory.getCurrentSession()
          .getNamedQuery("MACROSCOPIC_EXAMINATION_WORKLIST")
          .setInteger("orgId", microbioParamDto.getOrgId())
          .setInteger("orgUnitId", microbioParamDto.getOrgUnitId())
          .setParameterList("sampleStatusIds", MICROSCOPI_EXA_SAMPLE_STATUS_IDS)
          .setInteger("deptId", microbioParamDto.getDeptId())
          .setInteger("subDeptId", microbioParamDto.getSubDeptId())
          .setFirstResult(microbioParamDto.getOffset() != null ? microbioParamDto.getOffset() : 0)
          .setMaxResults(
              microbioParamDto.getRecordPerPage() != null ? microbioParamDto.getRecordPerPage()
                  : 10)
          .setResultTransformer(Transformers.aliasToBean(SampleAcceptancePendingDto.class)).list();
      return new Response(SUCCESS, SUCCESS_200, null, listMicrobioResultEntryMasterDto, null);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  public Response getMicroScopicExaminationListCount(MicrobioParamDto microbioParamDto)
      throws ApplicationException {
    try {
      BigInteger incubationObservationListCount = (BigInteger) sessionFactory.getCurrentSession()
          .getNamedQuery("MACROSCOPIC_EXAMINATION_WORKLIST_COUNT")
          .setInteger("orgId", microbioParamDto.getOrgId())
          .setParameterList("sampleStatusIds", MICROSCOPI_EXA_SAMPLE_STATUS_IDS)
          .setInteger("deptId", microbioParamDto.getDeptId())
          .setInteger("subDeptId", microbioParamDto.getSubDeptId())
          .setInteger("orgUnitId", microbioParamDto.getOrgUnitId()).uniqueResult();
      if (incubationObservationListCount.compareTo(BigInteger.ZERO) == 1) {
        return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null,
            incubationObservationListCount);
      } else {
        return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, BigInteger.ZERO);
      }

    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  public Response saveMicroScopicExamination(
      MicroscopicExaminationMasterDto microscopicExaminationMasterDto)
      throws ApplicationException {

    try {
      MicroscopicExaminationMaster microscopicExaminationMaster =
          microscopicExaminationMasterDto != null
              ? mapper.map(microscopicExaminationMasterDto, MicroscopicExaminationMaster.class, "MicroscopicExaminationMasterDtoToMicroscopicExaminationMaster")
              : null;
      MicroscopicExaminationMaster micrscopiceExam = save(microscopicExaminationMaster);
      if (micrscopiceExam.getListMicroscopicExaminationDetailsMasters() != null) {
        for (Iterator iterator =
            micrscopiceExam.getListMicroscopicExaminationDetailsMasters().iterator(); iterator
                .hasNext();) {
          MicroscopicExaminationDetailsMaster microscopicExaminationDetailsMaster =
              (MicroscopicExaminationDetailsMaster) iterator.next();
          microscopicExaminationDetailsMaster
              .setMicroscopicExaId(micrscopiceExam.getMicroscopicExaId());
          microscopicExaminationDetailsMaster.setCreatedDate(new Date().getTime());
          
          Response<MicroscopicExaminationDetailsMaster, Integer> microscopicExaminationMstRes =    imicroscopicExaminationDetailsMaster
              .savemicroscopicExaminationDetailsMaster(microscopicExaminationDetailsMaster);
          if(microscopicExaminationMstRes.getCode().equals(SUCCESS_200))
          {
            continue;
          }
          else {
            return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
          }
        }
        return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, null);

      } else {
        return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
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
  public Response updateMicroScopicExamination(
      MicroscopicExaminationMasterDto microscopicExaminationMasterDto)
      throws ApplicationException {

      try {
        MicroscopicExaminationMaster microscopicExaminationMaster =
            microscopicExaminationMasterDto != null
                ? mapper.map(microscopicExaminationMasterDto, MicroscopicExaminationMaster.class, "MicroscopicExaminationMasterDtoToMicroscopicExaminationMaster")
                : null;
        MicroscopicExaminationMaster micrscopiceExam = update(microscopicExaminationMaster);
        if (micrscopiceExam.getListMicroscopicExaminationDetailsMasters() != null) 
        {
          for (Iterator iterator =  micrscopiceExam.getListMicroscopicExaminationDetailsMasters().iterator(); iterator
                  .hasNext();) {
            MicroscopicExaminationDetailsMaster microscopicExaminationDetailsMaster =
                (MicroscopicExaminationDetailsMaster) iterator.next();
            microscopicExaminationDetailsMaster
                .setMicroscopicExaId(micrscopiceExam.getMicroscopicExaId());
            if(microscopicExaminationDetailsMaster.getExaminationDetailsId()==0)
            {
              microscopicExaminationDetailsMaster.setCreatedDate(new Date().getTime());
              Response<MicroscopicExaminationSubDetailsMaster, Integer> MicroscopicExaminationSubDetailsMasterRes =  imicroscopicExaminationDetailsMaster
                  .savemicroscopicExaminationDetailsMaster(microscopicExaminationDetailsMaster);
              if(MicroscopicExaminationSubDetailsMasterRes.getCode().equals(SUCCESS_200))
              {
                continue;
              }
              else {
                return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
              }
            }
            else {
              microscopicExaminationDetailsMaster.setUpdatedDate(new Date().getTime());
              Response<MicroscopicExaminationSubDetailsMaster, Integer> MicroscopicExaminationSubDetailsMasterRes =imicroscopicExaminationDetailsMaster
                  .updatemicroscopicExaminationDetailsMaster(microscopicExaminationDetailsMaster);
              if(MicroscopicExaminationSubDetailsMasterRes.getCode().equals(SUCCESS_200))
              {
                continue;
              }
              else {
                return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
              }
            }
           
          }
          return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, null);

        } else {
          return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
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
  public Response getMicroScopicExamination(Integer labSampleDtlsId, Integer orgId, Integer orgUnitId)
      throws ApplicationException {
    try {
      List<MicroscopicExaminationMasterDto> listMicroscopicExaminationMasterDto = new ArrayList<>();
      List<MicroscopicExaminationMasterDto> listMicroscopicExaminationMstDto =
          (List<MicroscopicExaminationMasterDto>) sessionFactory.getCurrentSession()
              .getNamedQuery("GET_MICRO_SCOPIC_EXAMINATION_MASTER_BY_RESULT_ID")
              .setInteger("labSampleDtlsId", labSampleDtlsId)
              .setInteger("orgId", orgId)
              .setInteger("orgUnitId", orgUnitId)
              .setResultTransformer(Transformers.aliasToBean(MicroscopicExaminationMasterDto.class))
              .list();

      for (Iterator iterator = listMicroscopicExaminationMstDto.iterator(); iterator.hasNext();) {
        MicroscopicExaminationMasterDto microscopicExaminationMasterDto =
            (MicroscopicExaminationMasterDto) iterator.next();
        if (microscopicExaminationMasterDto != null) {


          List<MicroscopicExaminationDetailsMasterDto> listMicroscopicExaminationDetailsDtoMst =
              (List<MicroscopicExaminationDetailsMasterDto>) sessionFactory.getCurrentSession()
                  .getNamedQuery("GET_MICRO_SCOPIC_EXAMINATION_DETAILS")
                  .setInteger("microscopicExaId",
                      microscopicExaminationMasterDto.getMicroscopicExaId())
                  .setInteger("orgId", orgId).setInteger("orgUnitId", orgUnitId)
                  .setResultTransformer(
                      Transformers.aliasToBean(MicroscopicExaminationDetailsMasterDto.class))
                  .list();

          if (listMicroscopicExaminationDetailsDtoMst != null) {
            List<MicroscopicExaminationDetailsMasterDto> listMicroscopicExaminationDetailsMasterDto =
                new ArrayList<>();
            for (Iterator iterator2 = listMicroscopicExaminationDetailsDtoMst.iterator(); iterator2
                .hasNext();) {

              MicroscopicExaminationDetailsMasterDto microscopicExaminationDetailsMasterDto =
                  (MicroscopicExaminationDetailsMasterDto) iterator2.next();

              List<MicroscopicExaminationSubDetailsMasterDto> listMicroscopicExaminationSubDetailsMaster =
                  (List<MicroscopicExaminationSubDetailsMasterDto>) sessionFactory
                      .getCurrentSession().getNamedQuery("GET_MICRO_SCOPIC_EXAMINATION_SUB_DETAILS")
                      .setInteger("examinationDetailsId",
                          microscopicExaminationDetailsMasterDto.getExaminationDetailsId())
                      .setInteger("orgId", orgId).setInteger("orgUnitId", orgUnitId)
                      .setResultTransformer(
                          Transformers.aliasToBean(MicroscopicExaminationSubDetailsMasterDto.class))
                      .list();

              if (listMicroscopicExaminationSubDetailsMaster != null) {
                List<MicroscopicExaminationSubDetailsMasterDto> listMicroscopicExaminationSubDetailsMasterDto =
                    new ArrayList<>();
                for (Iterator iterator3 =
                    listMicroscopicExaminationSubDetailsMaster.iterator(); iterator3.hasNext();) {
                  MicroscopicExaminationSubDetailsMasterDto microscopicExaminationSubDetailsMasterDto =
                      (MicroscopicExaminationSubDetailsMasterDto) iterator3.next();

                  listMicroscopicExaminationSubDetailsMasterDto
                      .add(microscopicExaminationSubDetailsMasterDto);
                }
                microscopicExaminationDetailsMasterDto
                    .setListMicroscopicExaminationSubDetailsMaster(
                        listMicroscopicExaminationSubDetailsMasterDto);
                listMicroscopicExaminationDetailsMasterDto
                    .add(microscopicExaminationDetailsMasterDto);
              }

            }
            microscopicExaminationMasterDto.setListMicroscopicExaminationDetailsMasters(
                listMicroscopicExaminationDetailsMasterDto);
            listMicroscopicExaminationMasterDto.add(microscopicExaminationMasterDto);
          }

        }

      }
      return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE,
          listMicroscopicExaminationMasterDto, null);

    } catch (HibernateException exception) {
      logger.error("Exection", exception);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
    return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
  }

}
