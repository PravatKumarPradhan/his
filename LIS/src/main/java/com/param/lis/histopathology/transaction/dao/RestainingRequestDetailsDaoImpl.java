package com.param.lis.histopathology.transaction.dao;

import java.util.Date;
import java.util.Iterator;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.param.entity.lis.histo.TRestainingReqSubDetailsMaster;
import com.param.entity.lis.histo.TRestainingRequestDetailsMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.ITransactionConstants;
import com.param.lis.global.common.Response;
import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({"rawtypes", "unchecked"})
public class RestainingRequestDetailsDaoImpl
    extends GenericDao<TRestainingRequestDetailsMaster, Integer>
    implements IRestainingRequestDetailsDao, ICommonConstants, IError, ITransactionConstants {

  final static Logger logger = Logger.getLogger(RestainingRequestDetailsDaoImpl.class);
  
  @Autowired
  SessionFactory sessionFactory;

  public RestainingRequestDetailsDaoImpl() {
    super(TRestainingRequestDetailsMaster.class);
  }

  @Override
  public Response saveRestainingRequestDetails(
      TRestainingRequestDetailsMaster tRestainingRequestDetailsMaster) throws ApplicationException {
    try 
    {
      int count = 0;
      tRestainingRequestDetailsMaster.setCreatedDate(new Date().getTime());
      TRestainingRequestDetailsMaster tRestainingRequestDetailsMst =
          save(tRestainingRequestDetailsMaster);
      for (Iterator iterator =
          tRestainingRequestDetailsMst.getListTRestainingReqSubDetailsMaster().iterator(); iterator
              .hasNext();) {
        TRestainingReqSubDetailsMaster tRestainingReqSubDetailsMaster =
            (TRestainingReqSubDetailsMaster) iterator.next();
        tRestainingReqSubDetailsMaster.setLabSampleDtlsId(tRestainingRequestDetailsMst.getLabSampleDtlsId());
        tRestainingReqSubDetailsMaster.setCreatedDate(new Date().getTime());
        tRestainingReqSubDetailsMaster.settRestainingDetailId(tRestainingRequestDetailsMst.gettRestainingDetailId());
           sessionFactory.getCurrentSession().save(tRestainingReqSubDetailsMaster);
           count++;
        }
      if(count>0)
      {
        return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, null);
      }
      else
      {
        return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
      }
    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  public Integer updateRestainSlideCreated(Integer tRestainingSubDetailId, Integer orgId,
      Integer orgUnitId) throws ApplicationException 
  {
    Integer restainSlideResult = 0;
    try 
    {
       restainSlideResult = (Integer) sessionFactory.getCurrentSession()
          .getNamedQuery("UPDATE_SLIDE_CREATED_STATUS")
          .setInteger("tRestainingSubDetailId", tRestainingSubDetailId)
          .setInteger("orgId", orgId)
          .setInteger("orgUnitId", orgUnitId).executeUpdate();
       
      return restainSlideResult;
    } catch (Exception e) 
    {
      logger.error("Exection", e);
       return restainSlideResult;
    }
  }
}

