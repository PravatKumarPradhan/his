package com.param.global.dao;

import java.util.List;

import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.AssociatedCompanyMasterDto;
import com.param.global.model.AssociatedCompanyMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "unchecked","rawtypes" })
public class AssociatedCompanyMasterDaoImpl extends GenericDao<AssociatedCompanyMaster, Integer> implements IAssociatedCompanyMasterDao, ICommonConstants{

	public AssociatedCompanyMasterDaoImpl() {
		super(AssociatedCompanyMaster.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Response getAssociatedCompanyMaster(AssociatedCompanyMasterDto associatedCompanyMasterDto)
			throws ApplicationException {
		try {
			List<AssociatedCompanyMasterDto> listAssociatedCompanyMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ASSOCIATED_COMPANY_MASTER_LIST_BY_COMPANY_ID")
					.setInteger("companyId", associatedCompanyMasterDto.getCompanyMasterId())
					.setInteger("unitId", associatedCompanyMasterDto.getUnitId())
					.setInteger("orgId", associatedCompanyMasterDto.getOrganizationId())
					.setResultTransformer(Transformers.aliasToBean(AssociatedCompanyMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, listAssociatedCompanyMasterDto, null);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, null, null, null);
	}
	

}
