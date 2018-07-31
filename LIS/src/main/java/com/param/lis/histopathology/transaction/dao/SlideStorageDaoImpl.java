package com.param.lis.histopathology.transaction.dao;

import java.math.BigInteger;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.param.entity.lis.histo.TSlideStorageMaster;
import com.param.entity.lis.micro.MicrobioResultEntryMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.ITransactionConstants;
import com.param.lis.global.common.Response;
import com.param.lis.histopathology.transaction.dto.HistoParamDto;
import com.param.lis.histopathology.transaction.dto.OutSourceMasterDto;
import com.param.lis.histopathology.transaction.dto.TSlideStorageMasterDto;
import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({"rawtypes", "unchecked"})
public class SlideStorageDaoImpl extends GenericDao<TSlideStorageMaster, Integer>
    implements ISlideStorageDao, ICommonConstants, IError, ITransactionConstants {


  final static Logger logger = Logger.getLogger(SlideStorageDaoImpl.class);

  public SlideStorageDaoImpl() {
    super(TSlideStorageMaster.class);
  }

  @Autowired
  private DozerBeanMapper mapper;

  @Override
  public Response getSlidesForStorage(HistoParamDto histoParamDto) throws ApplicationException 
  {
    try {
      List<TSlideStorageMasterDto> listTSlideStorageMasterDto = sessionFactory.getCurrentSession()
          .getNamedQuery("SLIDE_LIST_FOR_STORAGE").setInteger("orgId", histoParamDto.getOrgId())
          .setInteger("orgUnitId", histoParamDto.getOrgUnitId())
          .setFirstResult(histoParamDto.getOffset() != null ? histoParamDto.getOffset() : 0)
          .setMaxResults(
              histoParamDto.getRecordPerPage() != null ? histoParamDto.getRecordPerPage() : 10)
          .setResultTransformer(Transformers.aliasToBean(TSlideStorageMasterDto.class)).list();

      if (listTSlideStorageMasterDto.isEmpty()) {
        return new Response(ERROR, ERR_500, "No Speciman Records Found.", null, null);
      } else {
        return new Response(SUCCESS, SUCCESS_200, null, listTSlideStorageMasterDto, null);
      }

    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  public Response getSlidesForStorageCount(HistoParamDto histoParamDto)
      throws ApplicationException {
    try {
      Long slideListStorageCount =
          (Long) sessionFactory.getCurrentSession().getNamedQuery("SLIDE_LIST_FOR_STORAGE_COUNT")
              .setInteger("orgId", histoParamDto.getOrgId())
              .setInteger("orgUnitId", histoParamDto.getOrgUnitId())
              .uniqueResult();
      if (slideListStorageCount != null) {
        return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, slideListStorageCount);
      } else {
        return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, BigInteger.ZERO);
      }

    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  public Response saveSlideStorageDetails(List<TSlideStorageMasterDto> tSlideStorageMasterDto )
      throws ApplicationException {
    try {
    	
    	 if(!tSlideStorageMasterDto.isEmpty()) {
    		 TSlideStorageMaster tSlideStorageMst =null;
    		 for (Iterator iterator =tSlideStorageMasterDto.iterator(); iterator.hasNext();) 
        	 {
        		 
        		 TSlideStorageMasterDto tSlideStorageMstDto = (TSlideStorageMasterDto) iterator.next();
        		 TSlideStorageMaster  tSlideStorageMaster = mapper.map(tSlideStorageMstDto,
        		          TSlideStorageMaster.class, "TSlideStorageMasterDtoToTSlideStorageMaster");
        		      tSlideStorageMaster.setCreatedDate(new Date().getTime());
        		       tSlideStorageMst = save(tSlideStorageMaster);
        		      
        		    
        	 }
    		 
    		  if (tSlideStorageMst.gettSlideStorageId() != 0) {
		    	  return new Response(SUCCESS, SUCCESS_200, SLIDE_ACCEPT_SUCC, null, null);
		        } else {
		          return new Response(SUCCESS, SUCCESS_200, SLIDE_ACCEPT_FAIL, null, null);
		        }
    		   
    	 }else {
    		  return new Response(SUCCESS, SUCCESS_200, SLIDE_ACCEPT_FAIL, null, null);
    	 }
    	
     
     
    } catch (HibernateException exception) {
      logger.error("Exection", exception);
    } catch (Exception e) {
      logger.error("Exection", e);
    }
    return new Response(ERROR, ERR_500, SAMPLE_ADD_FAIL, null, null);
  }

  @Override
  public Response getStoredSlideDetails(Integer rackId, Integer shelfId, Integer orgId,
      Integer orgUnitId) throws ApplicationException {
    try
    {
        List<TSlideStorageMasterDto> listTSlideStorageMasterDto = (List<TSlideStorageMasterDto>) sessionFactory.getCurrentSession()
                .getNamedQuery("GET_STORED_SLIDE_BY_SHELF")
                .setInteger("rackId", rackId)
                .setInteger("shelfId", shelfId)
                .setInteger("orgId", orgId)
                .setInteger("orgUnitId", orgUnitId)
                .setResultTransformer(Transformers.aliasToBean(TSlideStorageMasterDto.class))                  
                .list();
        
        if(listTSlideStorageMasterDto.isEmpty())
        {
          return new Response(SUCCESS, SUCCESS_200, "No Records Found.", null, null);

        }
        else {
          return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, listTSlideStorageMasterDto);

        }
       
    } catch (Exception e)
    {
        logger.error("Exection", e);
        return new Response(ERROR, ERR_500, SAMPLE_NOT_FOUND, null, null);
    }
  }

  @Override
  public Response getStoredSlideDetails(HistoParamDto histoParamDto) throws ApplicationException {
    try {
      List<TSlideStorageMasterDto> listTSlideStorageMasterDto = sessionFactory.getCurrentSession()
          .getNamedQuery("STORED_SLIDE_LIST").setInteger("orgId", histoParamDto.getOrgId())
          .setInteger("orgUnitId", histoParamDto.getOrgUnitId())
          .setFirstResult(histoParamDto.getOffset() != null ? histoParamDto.getOffset() : 0)
          .setMaxResults(
              histoParamDto.getRecordPerPage() != null ? histoParamDto.getRecordPerPage() : 10)
          .setResultTransformer(Transformers.aliasToBean(TSlideStorageMasterDto.class)).list();

      if (listTSlideStorageMasterDto.isEmpty()) {
        return new Response(ERROR, ERR_500, "No Speciman Records Found.", null, null);
      } else {
        return new Response(SUCCESS, SUCCESS_200, null, listTSlideStorageMasterDto, null);
      }

    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  public Response getStoredSlideDetailsCount(HistoParamDto histoParamDto)
      throws ApplicationException {
    try {
      Long storedSlideListCount = (Long) sessionFactory.getCurrentSession()
          .getNamedQuery("STORED_SLIDE_LIST_COUNT")
          .setInteger("orgId", histoParamDto.getOrgId())
          .setInteger("orgUnitId", histoParamDto.getOrgUnitId())
          .uniqueResult();
      if (storedSlideListCount != null) {
        return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, storedSlideListCount);
      } else {
        return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, BigInteger.ZERO);
      }

    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }

  }
}
