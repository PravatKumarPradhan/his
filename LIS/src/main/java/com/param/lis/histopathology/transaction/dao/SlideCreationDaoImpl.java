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
import com.param.entity.lis.histo.TSlideMaster;
import com.param.entity.lis.transaction.LabSampleDetailsMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.ITransactionConstants;
import com.param.lis.global.common.Response;
import com.param.lis.histopathology.transaction.dto.HistoParamDto;
import com.param.lis.histopathology.transaction.dto.TBlockMasterDto;
import com.param.lis.histopathology.transaction.dto.TGrossMasterDto;
import com.param.lis.histopathology.transaction.dto.TSlideMasterDto;
import com.param.lis.histopathology.transaction.dto.TSubSpecimanMasterDto;
import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;


@Repository
@SuppressWarnings({"rawtypes", "unchecked"})
public class SlideCreationDaoImpl extends GenericDao<TSlideMaster, Integer>
    implements ISlideCreationDao, ICommonConstants, IError, ITransactionConstants {

  final static Logger logger = Logger.getLogger(SlideCreationDaoImpl.class);

  public SlideCreationDaoImpl() {
    super(TSlideMaster.class);
  }

  @Autowired
  private DozerBeanMapper mapper;
  
  @Autowired
  IRestainingRequestDetailsDao iRestainingRequestDetailsDao;

  @Override
  public Response getSlideCreationWorkList(HistoParamDto histoParamDto)
      throws ApplicationException {
    try {
      List<TSubSpecimanMasterDto> listTSubSpecimanMasterDto = sessionFactory.getCurrentSession()
          .getNamedQuery("GET_GROSS_SPCIMEN_LIST").setInteger("orgId", histoParamDto.getOrgId())
          .setInteger("orgUnitId", histoParamDto.getOrgUnitId())
          .setInteger("sampleStatusId", BLOCK_CREATED)
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
  public Response getSlideCreationWorkListCount(HistoParamDto histoParamDto)
      throws ApplicationException {
    try {
      Long subSpeciamanCount =
          (Long) sessionFactory.getCurrentSession().getNamedQuery("GET_GROSS_SPCIMEN_LIST_COUNT")
              .setInteger("orgId", histoParamDto.getOrgId())
              .setInteger("orgUnitId", histoParamDto.getOrgUnitId())
              .setInteger("sampleStatusId", BLOCK_CREATED).uniqueResult();
      if (subSpeciamanCount != null) {
        return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, subSpeciamanCount);
      } else {
        return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, BigInteger.ZERO);
      }

    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  public Response getCreatedBlockList(TSubSpecimanMasterDto tSubSpecimanMasterDto)
      throws ApplicationException {
    try {
      List<TGrossMasterDto> listTGrossMasterDto =
          sessionFactory.getCurrentSession().getNamedQuery("GET_GROSS_LIST_FOR_SLIDE_CREATION")
         .setInteger("orgId", tSubSpecimanMasterDto.getOrgId())
         .setInteger("orgUnitId", tSubSpecimanMasterDto.getOrgUnitId())
         .setInteger("tSubSpecimanId", tSubSpecimanMasterDto.gettSubSpecimanId())
         .setResultTransformer(Transformers.aliasToBean(TGrossMasterDto.class)).list();

 if (!listTGrossMasterDto.isEmpty())
 {
   for (Iterator iterator = listTGrossMasterDto.iterator(); iterator.hasNext();) 
   {
     TGrossMasterDto tGrossMasterDto = (TGrossMasterDto) iterator.next();
     List<TBlockMasterDto> listTBlockMasterDto =
         sessionFactory.getCurrentSession().getNamedQuery("GET_BLOCK_LIST_FOR_SLIDE_CREATION")
        .setInteger("orgId", tGrossMasterDto.getOrgId())
        .setInteger("orgUnitId", tGrossMasterDto.getOrgUnitId())
        .setInteger("tGrossId", tGrossMasterDto.gettGrossId())
        .setResultTransformer(Transformers.aliasToBean(TBlockMasterDto.class)).list();
     tGrossMasterDto.setListTBlockMaster(listTBlockMasterDto);
     if (!listTBlockMasterDto.isEmpty())
     {
       for (Iterator iterator1 = listTBlockMasterDto.iterator(); iterator1.hasNext();) 
       {
         TBlockMasterDto tBlockMasterDto = (TBlockMasterDto) iterator1.next();
         List<TSlideMasterDto> listTSlideMasterDto =
             sessionFactory.getCurrentSession().getNamedQuery("GET_SLIDE_LIST_FOR_SLIDE_CREATION")
            .setInteger("orgId", tBlockMasterDto.getOrgId())
            .setInteger("orgUnitId", tBlockMasterDto.getOrgUnitId())
            .setInteger("tBlockId", tBlockMasterDto.gettBlockId())
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
  public Response createSlides(List<TGrossMasterDto> listTGrossMasterDto)
      throws ApplicationException {
    try {
      if (!listTGrossMasterDto.isEmpty()) 
      {
        LabSampleDetailsMaster labSampleDetailsMaster = new LabSampleDetailsMaster();
        for (Iterator iterator = listTGrossMasterDto.iterator(); iterator.hasNext();) {
          TGrossMasterDto tGrossMasterDto = (TGrossMasterDto) iterator.next();
          for (Iterator iterator2 = tGrossMasterDto.getListTBlockMaster().iterator(); iterator2
              .hasNext();) {
            TBlockMasterDto tBlockMasterDto = (TBlockMasterDto) iterator2.next();
            for (Iterator iterator3 = tBlockMasterDto.getListTSlideMaster().iterator(); iterator3
                .hasNext();) 
            { 
              TSlideMasterDto tSlideMasterDto = (TSlideMasterDto) iterator3.next();
              TSlideMaster tSlideMaster = mapper.map(tSlideMasterDto, TSlideMaster.class, "TSlideMasterDtoToTSlideMaster");
              tSlideMaster.setLabSampleDtlsId(tBlockMasterDto.getLabSampleDtlsId());
              tSlideMaster.settBlockId(tBlockMasterDto.gettBlockId());
              tSlideMaster.settGrossId(tGrossMasterDto.gettGrossId());
              tSlideMaster.setCreatedDate(new Date().getTime());
              tSlideMaster.setIsDeleted('N');
              tSlideMaster.settSubSpecimanId(tGrossMasterDto.gettSubSpecimanId());
              TSlideMaster tslideMst = save(tSlideMaster);
              labSampleDetailsMaster.setUpdatedBy(tslideMst.getCreatedBy());
              labSampleDetailsMaster.setUpdatedDate(tslideMst.getCreatedDate());
              labSampleDetailsMaster.setLabSampleDtlsId(tslideMst.getLabSampleDtlsId());
              if(tSlideMasterDto.gettRestainingSubDetailId()!=null&& tSlideMasterDto.gettRestainingSubDetailId()>0)
              {
                iRestainingRequestDetailsDao.updateRestainSlideCreated(tSlideMasterDto.gettRestainingSubDetailId(), tSlideMasterDto.getOrgId(), tSlideMasterDto.getOrgUnitId());
              }
            }
          }
        }
        return new Response(SUCCESS, SUCCESS_200, SLIDES_CREATE_SUCCESS, null,
            labSampleDetailsMaster);
      } else {
        return new Response(ERROR, ERR_500, SLIDES_CREATE_FAIL, null, null);
      }

    } catch (Exception e) {
      logger.error("Exection", e);
      e.printStackTrace();
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }



}
