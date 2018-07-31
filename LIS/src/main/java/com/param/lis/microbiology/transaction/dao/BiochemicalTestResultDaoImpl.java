package com.param.lis.microbiology.transaction.dao;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.param.entity.lis.micro.TBiochemicalTestDetailsMaster;
import com.param.entity.lis.micro.TBiochemicalTestMaster;
import com.param.entity.lis.micro.TMicroIncubationDetailsMaster;
import com.param.entity.lis.micro.TMicroIncubationMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.ITransactionConstants;
import com.param.lis.global.common.Response;
import com.param.lis.microbiology.transaction.dto.MicrobioParamDto;
import com.param.lis.microbiology.transaction.dto.TBiochemicalTestMasterDto;
import com.param.lis.microbiology.transaction.dto.TMicroIncubationMasterDto;
import com.param.lis.transaction.dto.SampleAcceptancePendingDto;
import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class BiochemicalTestResultDaoImpl extends GenericDao<TBiochemicalTestMaster, Integer>
implements IBiochemicalTestResultDao, ICommonConstants, ITransactionConstants, IError {

  public BiochemicalTestResultDaoImpl() {
    super(TBiochemicalTestMaster.class);
  }
  
  @Autowired
  private SessionFactory sessionFactory;
  
  final static Logger logger = Logger.getLogger(BiochemicalTestResultDaoImpl.class);

  @Autowired
  private DozerBeanMapper mapper;

  @Override
  public Response getBiochemicalWorkList(MicrobioParamDto microbioParamDto)
      throws ApplicationException {
    List<SampleAcceptancePendingDto> listMicrobioResultEntryMasterDto = null;
    try {
        listMicrobioResultEntryMasterDto = sessionFactory.getCurrentSession()
                .getNamedQuery("BIOCHEMICAL_RESULT_ENTRY_WORKLIST")
                .setInteger("orgId", microbioParamDto.getOrgId())
                .setInteger("orgUnitId", microbioParamDto.getOrgUnitId())
                .setParameterList("sampleStatusIds", BIOCHEMICAL_SAMPLE_STATUS_IDS)
                .setInteger("deptId", microbioParamDto.getDeptId())
                .setInteger("subDeptId", microbioParamDto.getSubDeptId())
                .setFirstResult(microbioParamDto.getOffset() != null ? microbioParamDto.getOffset() : 0)
                .setMaxResults(
                        microbioParamDto.getRecordPerPage() != null ? microbioParamDto.getRecordPerPage() : 10)
                .setResultTransformer(Transformers.aliasToBean(SampleAcceptancePendingDto.class)).list();
        return new Response(SUCCESS, SUCCESS_200, null, listMicrobioResultEntryMasterDto, null);
    } catch (Exception e) {
        logger.error("Exception", e);
        return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  public Response getBiochemicalWorkListCount(MicrobioParamDto microbioParamDto)
      throws ApplicationException {
    try {
      BigInteger incubationObservationListCount = (BigInteger) sessionFactory.getCurrentSession()
              .getNamedQuery("BIOCHEMICAL_RESULT_ENTRY_WORKLIST_COUNT")
              .setInteger("orgId", microbioParamDto.getOrgId())
              .setParameterList("sampleStatusIds", BIOCHEMICAL_SAMPLE_STATUS_IDS)
              .setInteger("deptId", microbioParamDto.getDeptId())
              .setInteger("subDeptId", microbioParamDto.getSubDeptId())
              .setInteger("orgUnitId", microbioParamDto.getOrgUnitId()).uniqueResult();
      if (incubationObservationListCount.compareTo(BigInteger.ZERO) == 1) {
          return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, incubationObservationListCount);
      } else {
          return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, BigInteger.ZERO);
      }

  } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
  }
  }

  @Override
  public Response saveBiochemicalResult(TBiochemicalTestMasterDto tBiochemicalTestMasterDto)
      throws ApplicationException {
    try {

      if(tBiochemicalTestMasterDto!=null)
      {
        TBiochemicalTestMaster tBiochemicalTestMaster = mapper.map(tBiochemicalTestMasterDto,
            TBiochemicalTestMaster.class, "TBiochemicalTestMasterDtoToTBiochemicalTestMaster");
        TBiochemicalTestMaster tMicroIncubationMst = save(tBiochemicalTestMaster);
          for (Iterator iterator = tMicroIncubationMst.getListTBiochemicalTestDetailsMaster().iterator(); iterator.hasNext();) 
          {
            TBiochemicalTestDetailsMaster tBiochemicalTestDetailsMaster = (TBiochemicalTestDetailsMaster) iterator.next();
            tBiochemicalTestDetailsMaster.setBiochemTestId(tBiochemicalTestMaster.gettBiochemicalTestId());
            sessionFactory.getCurrentSession().save(tBiochemicalTestDetailsMaster);
          }
          return new Response(SUCCESS, SUCCESS_200,SUCCESS_200_MESSAGE, null, null);
      }
      else {
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
  public Response updateBiochemicalResult(TBiochemicalTestMasterDto tBiochemicalTestMasterDto)
      throws ApplicationException {
    try {

      if(tBiochemicalTestMasterDto!=null)
      {
        TBiochemicalTestMaster tBiochemicalTestMaster = mapper.map(tBiochemicalTestMasterDto,
            TBiochemicalTestMaster.class, "TBiochemicalTestMasterDtoToTBiochemicalTestMaster");
        TBiochemicalTestMaster tBiochemicalTestMst = update(tBiochemicalTestMaster);
          for (Iterator iterator = tBiochemicalTestMst.getListTBiochemicalTestDetailsMaster().iterator(); iterator.hasNext();) 
          {
            TBiochemicalTestDetailsMaster tBiochemicalTestDetailsMaster = (TBiochemicalTestDetailsMaster) iterator.next();
            sessionFactory.getCurrentSession().update(tBiochemicalTestDetailsMaster);
          }
          return new Response(SUCCESS, SUCCESS_200,SUCCESS_200_MESSAGE, null, null);
      }
      else {
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
  public Response getBiochemicalResultDetails(Integer tBiochemicalTestId, Integer orgId,
      Integer orgUnitId) throws ApplicationException {
    try 
    {
        TBiochemicalTestMaster tBiochemicalTestMaster =findById(tBiochemicalTestId);
        if(tBiochemicalTestMaster!=null)
        {
          TBiochemicalTestMasterDto tBiochemicalTestMasterDto =   mapper.map( tBiochemicalTestMaster, TBiochemicalTestMasterDto.class, "TBiochemicalTestMasterDtoToTBiochemicalTestMaster");
          return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, tBiochemicalTestMasterDto);
        }
        else 
        {
          return new Response(ERROR, ERR_500, "No Previous Details Found.", null, null);
        }
    } catch (HibernateException exception) 
    {
      logger.error("Exection", exception);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    } catch (Exception e)
    {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }
  
 

}
