package com.param.billing.global.dao;

import java.util.List;

import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.param.billing.common.ICommonConstants;
import com.param.billing.common.Response;
import com.param.billing.global.dto.CardTypeMasterDto;
import com.param.billing.global.model.CardTypeMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({"unchecked", "rawtypes"})
public class CardTypeMasterDaoImpl extends GenericDao<CardTypeMaster, Integer> implements ICardTypeMasterDao,ICommonConstants{

	public CardTypeMasterDaoImpl() {
		super(CardTypeMaster.class);
	}
	
	@Override
	public Response gerActiveCardTypeMaster(CardTypeMasterDto cardTypeMasterDto) throws ApplicationException {
		try {
			List cardTypeList = sessionFactory.getCurrentSession().getNamedQuery("GET_ACTIVE_CARD_TYPE_NAME")
					.setInteger("unitId", cardTypeMasterDto.getUnitId())
					.setInteger("orgId", cardTypeMasterDto.getOrgnisationId())
					.setResultTransformer(Transformers.aliasToBean(CardTypeMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, cardTypeList, null);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	
}
