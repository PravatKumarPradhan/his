package com.param.global.dao;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.AllergyMasterDto;
import com.param.global.dto.AllergyTypeMasterDto;
import com.param.global.model.AllergyMaster;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class AllergyMasterDaoImpl extends GenericDao<AllergyMaster, Integer> implements IAllergyMasterDao, ICommonConstants {

	
	public AllergyMasterDaoImpl() {
		super(AllergyMaster.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Response getListOfAllergyMaster(AllergyMasterDto allergyMasterDto) throws ApplicationException {
		try
		{
			StatelessSession statlessSession = sessionFactory.openStatelessSession();
			List<AllergyMasterDto> listAllergyMasterDto = statlessSession.getNamedQuery("GET_ALLERGY_MASTER_LIST_BY_ALLERGY_TYPE_ID")
					.setInteger("organizationId", allergyMasterDto.getOrganizationId())
					.setInteger("unitId", allergyMasterDto.getUnitId())
					.setInteger("allergyTypeId", allergyMasterDto.getAllergyTypeId())
					.setResultTransformer(Transformers.aliasToBean(AllergyMasterDto.class)).list();
			statlessSession.close();
			return new Response(SUCCESS, SUCCESS_CODE, null, listAllergyMasterDto, null);
		}catch(HibernateException he) { 
			he.printStackTrace();
			throw new HibernateException(he.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}

}
