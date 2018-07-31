package com.param.global.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.VisitTypeMasterDto;
import com.param.global.model.VisitTypeMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "unchecked", "rawtypes" })
public class VisitTypeMasterDaoImpl extends GenericDao<VisitTypeMaster, Integer> implements IVisitTypeMasterDao,ICommonConstants{

	public VisitTypeMasterDaoImpl() {
		super(VisitTypeMaster.class);
	}

	@Override
	public Response getActiveVisitTypeList(Integer unitId, Integer orgId) throws ApplicationException {
		try {
			List<VisitTypeMasterDto> listVisitTypeMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_VISIT_TYPE_LIST")
					.setInteger("orgId", orgId)
					.setResultTransformer(Transformers.aliasToBean(VisitTypeMasterDto.class))
					.list();
			return new Response (SUCCESS, SUCCESS_CODE, null, listVisitTypeMasterDto, null);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	
}
