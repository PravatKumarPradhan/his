package com.param.lis.histopathology.transaction.dao;

import java.math.BigInteger;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.param.entity.lis.histo.TMicroExaDetails;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.ITransactionConstants;
import com.param.lis.global.common.Response;
import com.param.lis.histopathology.transaction.dto.HistoParamDto;
import com.param.lis.histopathology.transaction.dto.TMicroExaDetailsDto;
import com.param.lis.histopathology.transaction.dto.TSpecimanMasterDto;
import com.param.lis.histopathology.transaction.dto.TSubSpecimanMasterDto;
import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({"rawtypes", "unchecked"})
public class MicroScopicExaminationDaoImpl extends GenericDao<TMicroExaDetails, Integer>
    implements IMicroScopicExaminationDao, ICommonConstants, IError, ITransactionConstants {

  final static Logger logger = Logger.getLogger(MicroScopicExaminationDaoImpl.class);

  public MicroScopicExaminationDaoImpl() {
    super(TMicroExaDetails.class);
  }

  @Autowired
  private DozerBeanMapper mapper;


  @Override
  public Response getMicroscopicWorkList(HistoParamDto histoParamDto) throws ApplicationException {
    try {
      List<TSubSpecimanMasterDto> listTSubSpecimanMasterDto = sessionFactory.getCurrentSession()
          .getNamedQuery("GET_SUB_SPCIMEN_LIST")
          .setInteger("orgId", histoParamDto.getOrgId())
          .setInteger("orgUnitId", histoParamDto.getOrgUnitId())
          .setParameterList("sampleStatusIds", HISTO_MICROEXA_STATUS)
          .setInteger("deptId", histoParamDto.getDeptId())
          .setFirstResult(histoParamDto.getOffset() != null ? histoParamDto.getOffset() : 0)
          .setMaxResults(
              histoParamDto.getRecordPerPage() != null ? histoParamDto.getRecordPerPage() : 10)
          .setResultTransformer(Transformers.aliasToBean(TSubSpecimanMasterDto.class)).list();

      if (listTSubSpecimanMasterDto.isEmpty()) {
        return new Response(ERROR, ERR_500, "No Speciman Records Found.", null, null);
      } else {
        return new Response(SUCCESS, SUCCESS_200, null, listTSubSpecimanMasterDto, null);
      }

    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  public Response getMicroscopicWorkListCount(HistoParamDto histoParamDto)
      throws ApplicationException {
    try {
      Long subSpecimenListCount = (Long) sessionFactory.getCurrentSession()
          .getNamedQuery("GET_SUB_SPCIMEN_LIST_COUNT")
          .setInteger("orgId", histoParamDto.getOrgId())
          .setInteger("orgUnitId", histoParamDto.getOrgUnitId())
          .setParameterList("sampleStatusIds", HISTO_MICROEXA_STATUS)
          .setInteger("deptId", histoParamDto.getDeptId())
          .uniqueResult();
      if (subSpecimenListCount != null) {
        return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, subSpecimenListCount);
      } else {
        return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, BigInteger.ZERO);
      }

    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  public Response getMiscroscopicSlides(TSubSpecimanMasterDto tSubSpecimanMasterDto)
      throws ApplicationException {
    try {
      List<TMicroExaDetailsDto> listTMicroExaDetailsDto =
          sessionFactory.getCurrentSession().getNamedQuery("GET_TOTAL_SLIDES_BY_SPECIMEN")
              .setInteger("orgId", tSubSpecimanMasterDto.getOrgId())
              .setInteger("orgUnitId", tSubSpecimanMasterDto.getOrgUnitId())
              .setInteger("tSubSpecimanId", tSubSpecimanMasterDto.gettSubSpecimanId())
              .setResultTransformer(Transformers.aliasToBean(TMicroExaDetailsDto.class)).list();
      if (listTMicroExaDetailsDto.isEmpty()) {
        return new Response(ERROR, ERR_500, "No Records Found..", null, null);
      } else {
        return new Response(SUCCESS, SUCCESS_200, null, listTMicroExaDetailsDto, null);
      }

    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  public Response saveSlideExaminationsDetails(List<TMicroExaDetailsDto> listTMicroExaDetailsDto)
      throws ApplicationException {	
    try {
      if (!listTMicroExaDetailsDto.isEmpty()) {
        for (Iterator iterator = listTMicroExaDetailsDto.iterator(); iterator.hasNext();) {
          TMicroExaDetailsDto tMicroExaDetailsDto = (TMicroExaDetailsDto) iterator.next();
          TMicroExaDetails tMicroExaDetails =
              mapper.map(tMicroExaDetailsDto, TMicroExaDetails.class, "TMicroExaDetailsDtoToTMicroExaDetails");
          tMicroExaDetails.setCreatedDate(new Date().getTime());
          save(tMicroExaDetails);
  
        }
        return new Response(SUCCESS, SUCCESS_200, SLIDES_EXAMINATION_SAVE_SUCCESS, null, null);
      } else {
        return new Response(ERROR, ERR_500, SLIDES_EXAMINATION_SAVE_FAIL, null, null);
      }

    } catch (Exception e) {
      logger.error("Exection", e);
      e.printStackTrace();
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }


}
