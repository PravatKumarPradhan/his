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
import com.param.global.dto.DoctorMasterDto;
import com.param.global.dto.DrugMasterDto;
import com.param.global.model.DrugMaster;

@Repository
@SuppressWarnings({"rawtypes","unchecked"})
public class DrugMasterDaoImpl extends GenericDao<DrugMaster, Integer> implements IDrugMasterDao, ICommonConstants {

	
	public DrugMasterDaoImpl() {
		super(DrugMaster.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Response getDrugMasterList(DrugMasterDto drugMasterDto)throws ApplicationException {
		try
		{
			StatelessSession statlessSession = sessionFactory.openStatelessSession();
			List<DrugMasterDto> listDrugMasterDto = statlessSession.getNamedQuery("GET_DRUG_MASTER_LIST")
					.setInteger("unitId", drugMasterDto.getUnitId())
					.setInteger("orgId", drugMasterDto.getOrganizationId())
					.setResultTransformer(Transformers.aliasToBean(DrugMasterDto.class)).list();
			statlessSession.close();
			return new Response(SUCCESS, SUCCESS_CODE, null, listDrugMasterDto, null);
		}catch(HibernateException he) { 
			he.printStackTrace();
			throw new HibernateException(he.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}

}
