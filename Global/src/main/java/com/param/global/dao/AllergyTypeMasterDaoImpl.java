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
import com.param.global.dto.AllergyTypeMasterDto;
import com.param.global.model.AllergyTypeMaster;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class AllergyTypeMasterDaoImpl extends GenericDao<AllergyTypeMaster, Integer> implements IAllergyTypeMasterDao, ICommonConstants {

	
	public AllergyTypeMasterDaoImpl() {
		super(AllergyTypeMaster.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Response getListOfAllergyTypeMaster(AllergyTypeMasterDto allergyTypeMasterDto)throws ApplicationException {
		try
		{
			StatelessSession statlessSession = sessionFactory.openStatelessSession();
			List<AllergyTypeMasterDto> listAllergyPatientMapperDto = statlessSession.getNamedQuery("GET_LIST_ALLERGY_TYPE_MAPPER")
					.setInteger("organizationId", allergyTypeMasterDto.getOrganizationId())
					.setInteger("unitId", allergyTypeMasterDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(AllergyTypeMasterDto.class)).list();
			statlessSession.close();
			return new Response(SUCCESS, SUCCESS_CODE, null, listAllergyPatientMapperDto, null);
		}catch(HibernateException he) { 
			he.printStackTrace();
			throw new HibernateException(he.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}

}
