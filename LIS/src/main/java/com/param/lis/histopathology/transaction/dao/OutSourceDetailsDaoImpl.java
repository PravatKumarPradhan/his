package com.param.lis.histopathology.transaction.dao;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.param.entity.lis.histo.OutSourceDetailMaster;
import com.param.entity.lis.histo.TRestainingReqSubDetailsMaster;
import com.param.entity.lis.histo.TRestainingRequestDetailsMaster;
import com.param.lis.common.dto.CommonAutoCompleteDto;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.ITransactionConstants;
import com.param.lis.global.common.Response;
import com.param.lis.histopathology.transaction.dto.OutSourceDetailMasterDto;
import com.param.lis.transaction.dto.SearchCommonDto;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({"rawtypes", "unchecked"})
public class OutSourceDetailsDaoImpl
    extends GenericDao<OutSourceDetailMaster, Integer>
    implements IOutSourceDetailsDao, ICommonConstants, IError, ITransactionConstants {

  final static Logger logger = Logger.getLogger(OutSourceDetailsDaoImpl.class);
  
  @Autowired
  SessionFactory sessionFactory;

  public OutSourceDetailsDaoImpl() {
    super(OutSourceDetailMaster.class);
  }

@Override
public Response getOutSourceDetailsByOutSourceId(OutSourceDetailMasterDto outSourceDetailMasterDto)
		throws ApplicationException {
	try
	{
		List<OutSourceDetailMasterDto> listOutSourceDetailMasterDto = sessionFactory.getCurrentSession()
				.getNamedQuery("GET_OUT_SOURCE_DETAILS_BY_OUT_SOURCE_ID")
				.setInteger("orgId", outSourceDetailMasterDto.getOrgId())
				.setInteger("orgUnitId", outSourceDetailMasterDto.getOrgUnitId())
				.setInteger("outSourcedId", outSourceDetailMasterDto.getOutSourcedId())
				.setResultTransformer(Transformers.aliasToBean(OutSourceDetailMasterDto.class)).list();
		
		if(listOutSourceDetailMasterDto != null && listOutSourceDetailMasterDto.size() > 0)
			return new Response(SUCCESS, SUCCESS_200, null, null, listOutSourceDetailMasterDto.get(0));
		else
			return new Response(ERROR, ERR_500, "No Out Source Details Records Found.", null, null);
		
	} catch (Exception e)
	{
		logger.error("Exection", e);
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}
}


  
}

