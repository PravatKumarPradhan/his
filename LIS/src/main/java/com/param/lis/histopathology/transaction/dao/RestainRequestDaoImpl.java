package com.param.lis.histopathology.transaction.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.hibernate.transform.Transformers;
import org.jboss.logging.annotations.Transform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.param.entity.lis.histo.TGrossMaster;
import com.param.entity.lis.histo.TRestainingRequestDetailsMaster;
import com.param.entity.lis.histo.TRestainingRequestMaster;
import com.param.lis.common.dto.CommonDto;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.ITransactionConstants;
import com.param.lis.global.common.Response;
import com.param.lis.histopathology.transaction.dto.HistoParamDto;
import com.param.lis.histopathology.transaction.dto.TBlockMasterDto;
import com.param.lis.histopathology.transaction.dto.TGrossMasterDto;
import com.param.lis.histopathology.transaction.dto.TRestainingReqSubDetailsMasterDto;
import com.param.lis.histopathology.transaction.dto.TRestainingRequestDetailsMasterDto;
import com.param.lis.histopathology.transaction.dto.TRestainingRequestMasterDto;
import com.param.lis.histopathology.transaction.dto.TSlideMasterDto;
import com.param.lis.histopathology.transaction.dto.TSubSpecimanMasterDto;
import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;


@Repository
@SuppressWarnings(
{ "rawtypes", "unchecked" })
public class RestainRequestDaoImpl  extends GenericDao<TRestainingRequestMaster, Integer>
implements IRestainRequestDao, ICommonConstants, IError,ITransactionConstants{

  public RestainRequestDaoImpl() {
    super(TRestainingRequestMaster.class);
  }
  
  @Autowired
  private DozerBeanMapper mapper;
  
  @Autowired
  private IRestainingRequestDetailsDao tRestainingRequestDetailsDao;
  
  final static Logger logger = Logger.getLogger(RestainRequestDaoImpl.class);

  @Override
  public Response getSlidesDetails(TSubSpecimanMasterDto tSubSpecimanMasterDto)
      throws ApplicationException {
    try {
      List<TGrossMasterDto> listTGrossMasterDto =
               sessionFactory.getCurrentSession().getNamedQuery("GET_GROSS_LIST_FOR_RESTAINING")
              .setInteger("orgId", tSubSpecimanMasterDto.getOrgId())
              .setInteger("orgUnitId", tSubSpecimanMasterDto.getOrgUnitId())
              .setInteger("tSubSpecimanId", tSubSpecimanMasterDto.gettSubSpecimanId())
              .setResultTransformer(Transformers.aliasToBean(TGrossMasterDto.class)).list();

      if (!listTGrossMasterDto.isEmpty())
      {
        for (Iterator iterator = listTGrossMasterDto.iterator(); iterator.hasNext();) 
        {
          List<TRestainingRequestDetailsMasterDto> listTRestainingRequestDetailsMasterDto = new ArrayList<>();
          TGrossMasterDto tGrossMasterDto = (TGrossMasterDto) iterator.next();
          List<TRestainingRequestDetailsMasterDto> tRestainingRequestDetailsMstDto =
              sessionFactory.getCurrentSession().getNamedQuery("GET_BLOCK_LIST_FOR_RESTAINING")
             .setInteger("orgId", tGrossMasterDto.getOrgId())
             .setInteger("orgUnitId", tGrossMasterDto.getOrgUnitId())
             .setInteger("tGrossId", tGrossMasterDto.gettGrossId())
             .setResultTransformer(Transformers.aliasToBean(TRestainingRequestDetailsMasterDto.class)).list();
         
          if (!tRestainingRequestDetailsMstDto.isEmpty())
          {
            for (Iterator iterator1 = tRestainingRequestDetailsMstDto.iterator(); iterator1.hasNext();) 
            {
              TRestainingRequestDetailsMasterDto tRestainingRequestDetailsMasterDto = (TRestainingRequestDetailsMasterDto) iterator1.next();
              List<CommonDto> listCommonDto =
                  sessionFactory.getCurrentSession().getNamedQuery("GET_SLIDE_LIST_FOR_RESTAINING")
                 .setInteger("orgId", tRestainingRequestDetailsMasterDto.getOrgId())
                 .setInteger("orgUnitId", tRestainingRequestDetailsMasterDto.getOrgUnitId())
                 .setInteger("tBlockId", tRestainingRequestDetailsMasterDto.gettBlockId())
                 .setResultTransformer(Transformers.aliasToBean(CommonDto.class)).list();
              tRestainingRequestDetailsMasterDto.setListSlidesNo(listCommonDto);
              listTRestainingRequestDetailsMasterDto.add(tRestainingRequestDetailsMasterDto);
            }
          }
          tGrossMasterDto.setListTRestainingRequestDetailsMasterDto(listTRestainingRequestDetailsMasterDto);
        }
        return new Response(SUCCESS, SUCCESS_200, null, listTGrossMasterDto, null);
      } else {

        return new Response(ERROR, ERR_500, "No Gross Found..", null, null);
      }

    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  public Response saveRestainRequest(TRestainingRequestMasterDto tRestainingRequestMasterDto)
      throws ApplicationException 
  {
    try {
          if(tRestainingRequestMasterDto!=null)
          {
            tRestainingRequestMasterDto.setCreatedDate(new Date().getTime());
            TRestainingRequestMaster tRestainingRequestMaster = mapper.map(tRestainingRequestMasterDto, TRestainingRequestMaster.class,"TRestainingRequestMasterDtoToTRestainingRequestMaster");
            TRestainingRequestMaster tRestainingRequestMst =  save(tRestainingRequestMaster);
            for (Iterator iterator = tRestainingRequestMst.getListTRestainingRequestDetailsMaster().iterator(); iterator
                .hasNext();) {
              TRestainingRequestDetailsMaster tRestainingRequestDetailsMaster = (TRestainingRequestDetailsMaster) iterator.next();
              tRestainingRequestDetailsMaster.setLabSampleDtlsId(tRestainingRequestMst.getLabSampleDtlsId());
              tRestainingRequestDetailsMaster.settRestainingReqId(tRestainingRequestMst.gettRestainingReqId());
              tRestainingRequestDetailsMaster.setIsDeleted('N');
              tRestainingRequestDetailsDao.saveRestainingRequestDetails(tRestainingRequestDetailsMaster);
            }
              return new Response(SUCCESS, SUCCESS_200, RESTAIN_REQUEST_SAVE_SUCCESS, null, null);
          }
          else {
            return new Response(SUCCESS, SUCCESS_200, RESTAIN_REQUEST_SAVE_FAIL, null, null);
          }
  } catch (Exception e) {
    logger.error("Exection", e);
    return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
  }
  }

  @Override
  public Response getRestainRequestWorkList(HistoParamDto histoParamDto)
      throws ApplicationException {
    try {
      List<TRestainingRequestMasterDto> listTRestainingRequestMasterDto = sessionFactory.getCurrentSession()
          .getNamedQuery("GET_RESTAIN_WORKLIST")
          .setInteger("orgId", histoParamDto.getOrgId())
          .setInteger("orgUnitId", histoParamDto.getOrgUnitId())
          .setFirstResult(histoParamDto.getOffset() != null ? histoParamDto.getOffset() : 0)
          .setMaxResults(histoParamDto.getRecordPerPage() != null ? histoParamDto.getRecordPerPage() : 10)
          .setResultTransformer(Transformers.aliasToBean(TRestainingRequestMasterDto.class)).list();

      if (listTRestainingRequestMasterDto.isEmpty()) {
        return new Response(ERROR, ERR_500, "No Records Found.", null, null);
      } else {
        return new Response(SUCCESS, SUCCESS_200, null, listTRestainingRequestMasterDto, null);
      }

    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  public Response getRestainRequestWorkListCount(HistoParamDto histoParamDto)
      throws ApplicationException {
    try {
      Long restainWorkListCount =
          (Long) sessionFactory.getCurrentSession().getNamedQuery("GET_RESTAIN_WORKLIST_COUNT")
              .setInteger("orgId", histoParamDto.getOrgId())
              .setInteger("orgUnitId", histoParamDto.getOrgUnitId())
              .uniqueResult();
      if (restainWorkListCount != null) {
        return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, restainWorkListCount);
      } else {
        return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, BigInteger.ZERO);
      }

    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  public Response getRestainRequestWorkListDetails(Integer tRestainingReqId, Integer orgId,
      Integer orgUnitId) throws ApplicationException {
    try {
      List<TRestainingRequestDetailsMasterDto> listAgainstSlide = sessionFactory.getCurrentSession()
          .getNamedQuery("RESTAIN_REUEST_DETAILS_AGAINST_SLIDE")
          .setInteger("orgId", orgId)
          .setInteger("orgUnitId", orgUnitId)
          .setInteger("tRestainingReqId", tRestainingReqId)
          .setResultTransformer(Transformers.aliasToBean(TRestainingRequestDetailsMasterDto.class)).list();
      
     List<TRestainingRequestDetailsMasterDto> listAgainstNewSlide = sessionFactory.getCurrentSession()
          .getNamedQuery("RESTAIN_REUEST_DETAILS_AGAINST_NEW_SLIDE")
          .setInteger("orgId",orgId)
          .setInteger("orgUnitId", orgUnitId)
          .setInteger("tRestainingReqId", tRestainingReqId)
          .setResultTransformer(Transformers.aliasToBean(TRestainingRequestDetailsMasterDto.class)).list();

     if(!listAgainstSlide.isEmpty()||!listAgainstNewSlide.isEmpty()) 
     {
       listAgainstSlide.addAll(listAgainstNewSlide);
        List<TRestainingRequestDetailsMasterDto> filteredList = listAgainstSlide
                                                  .stream()
                                                  .sorted(Comparator.comparing(TRestainingRequestDetailsMasterDto::gettRestainingDetailId))
                                                  .collect(Collectors.toList());
        return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, filteredList, null);    
     }
     else 
     {
       return new Response(SUCCESS, SUCCESS_200, "No Record Found..", null, null);    
     }

    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  public Response getRestainRequestWorkListSlides(Integer tRestainingDetailId,Integer tRestainingSubDetailId,Character isNew, Integer orgId,
      Integer orgUnitId) throws ApplicationException 
  {
    try {
      List<TGrossMasterDto> listTGrossMasterDto =
          sessionFactory.getCurrentSession().getNamedQuery("GET_GROSS_LIST_FOR_RE_SLIDE_CREATION")
         .setInteger("orgId", orgId)
         .setInteger("orgUnitId", orgUnitId)
         .setInteger("tRestainingDetailId", tRestainingDetailId)
         .setResultTransformer(Transformers.aliasToBean(TGrossMasterDto.class)).list();

 if (!listTGrossMasterDto.isEmpty())
 {
   for (Iterator iterator = listTGrossMasterDto.iterator(); iterator.hasNext();) 
   {
     TGrossMasterDto tGrossMasterDto = (TGrossMasterDto) iterator.next();
     List<TBlockMasterDto> listTBlockMasterDto =
         sessionFactory.getCurrentSession().getNamedQuery("GET_BLOCK_LIST_FOR_RE_SLIDE_CREATION")
        .setInteger("orgId", tGrossMasterDto.getOrgId())
        .setInteger("orgUnitId", tGrossMasterDto.getOrgUnitId())
        .setInteger("tRestainingDetailId", tRestainingDetailId)
        .setResultTransformer(Transformers.aliasToBean(TBlockMasterDto.class)).list();
     tGrossMasterDto.setListTBlockMaster(listTBlockMasterDto);
     if (!listTBlockMasterDto.isEmpty())
     {
       for (Iterator iterator1 = listTBlockMasterDto.iterator(); iterator1.hasNext();) 
       {
         TBlockMasterDto tBlockMasterDto = (TBlockMasterDto) iterator1.next();
         List<TSlideMasterDto> listTSlideMasterDto =
             sessionFactory.getCurrentSession().getNamedQuery("GET_SLIDE_LIST_FOR_RE_SLIDE_CREATION")
            .setInteger("orgId", tBlockMasterDto.getOrgId())
            .setInteger("orgUnitId", tBlockMasterDto.getOrgUnitId())
            .setInteger("tRestainingDetailId",tRestainingDetailId)
            .setInteger("tRestainingSubDetailId",tRestainingSubDetailId)
            .setCharacter("isNew",isNew)
            .setResultTransformer(Transformers.aliasToBean(TSlideMasterDto.class)).list();
         tBlockMasterDto.setListTSlideMaster(listTSlideMasterDto);
       }
     }
   }
   return new Response(SUCCESS, SUCCESS_200, null, listTGrossMasterDto, null);
 } else {

   return new Response(ERROR, ERR_500, "No Gross Found..", null, null);
 }

    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  public Response getMaxSlideNumber(Integer orgId, Integer orgUnitId, Integer tBlockId)
      throws ApplicationException {
    try 
    {
      BigInteger maxLideNumber =
          (BigInteger) sessionFactory.getCurrentSession().getNamedQuery("GET_MAX_SLIDE_NUMBER")
              .setInteger("orgId", orgId)
              .setInteger("orgUnitId",orgUnitId)
              .setInteger("tBlockId", tBlockId)
              .uniqueResult();
      if (maxLideNumber != null)
      {
        return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, maxLideNumber);
      } else {
        return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, BigInteger.ZERO);
      }

    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }


}
