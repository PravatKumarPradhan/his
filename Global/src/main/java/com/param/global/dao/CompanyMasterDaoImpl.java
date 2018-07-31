package com.param.global.dao;

import java.util.List;

import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.CompanyMasterDto;
import com.param.global.model.CompanyMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CompanyMasterDaoImpl extends GenericDao<CompanyMaster, Integer> implements ICompanyMasterDao, ICommonConstants{

	public CompanyMasterDaoImpl() {
		super(CompanyMaster.class);
	}

	@Override
	public Response getActiveCompanyMasterList(CompanyMasterDto companyMasterDto) throws ApplicationException {
		try {
			List<CompanyMasterDto> listCompanyMasterDto = sessionFactory.getCurrentSession().getNamedQuery("GET_ACTIVE_COMPANY_MASTER_LIST")
				.setInteger("unitId", companyMasterDto.getUnitId())
				.setInteger("orgId", companyMasterDto.getOrganizationId())
				.setResultTransformer(Transformers.aliasToBean(CompanyMasterDto.class)).list();
			return new Response(SUCCESS, SUCCESS_CODE, null, listCompanyMasterDto, null);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE,COMMON_ERROR_MESSAGE, null, null);
	}

	
}
