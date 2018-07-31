package com.param.global.org.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.entity.org.model.LicenceTypeMaster;
import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.org.dto.LiceneceTypeMasterDto;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class LiceneceTypeMasterDaoImpl extends GenericDao<LicenceTypeMaster, Integer>
		implements ILiceneceTypeMasterDao, ICommonConstants {

	@Autowired
	SessionFactory sessionFactory;

	public LiceneceTypeMasterDaoImpl() {
		super(LicenceTypeMaster.class);
	}

	@Override
	public Response getLiceneceTypeMasterByType(LiceneceTypeMasterDto liceneceTypeMasterDto)
			throws ApplicationException {
		try {
			List<LiceneceTypeMasterDto> liceneceTypeMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_LICENECE_TYPE_MASTER_LIST_BY_TYPE")
					.setString("liceneceType", liceneceTypeMasterDto.getLicenceType().toLowerCase())
					.setInteger("orgId", liceneceTypeMasterDto.getOrganizationId())
					.setResultTransformer(Transformers.aliasToBean(LiceneceTypeMasterDto.class)).list();
			return new Response(SUCCESS, null, null, liceneceTypeMasterDtoList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, null, null, null);
	}

	public Response saveLiceneceTypeMaster(LiceneceTypeMasterDto liceneceTypeMasterDto) throws ApplicationException {
		try {
			LicenceTypeMaster licenceTypeMaster = new LicenceTypeMaster();
			licenceTypeMaster.setLiceenceTypeCode(liceneceTypeMasterDto.getLiceenceTypeCode());
			licenceTypeMaster.setLicenceType(liceneceTypeMasterDto.getLicenceType());
			licenceTypeMaster.setCreatedBy(liceneceTypeMasterDto.getCreatedBy());
			licenceTypeMaster.setCreatedDate(GlobalCommonDateUtils.getDate(liceneceTypeMasterDto.getCreatedDate(), "dd-M-yyyy HH:mm:ss"));
			licenceTypeMaster.setStatus(liceneceTypeMasterDto.getStatus());
			licenceTypeMaster.setUpdatedBy(liceneceTypeMasterDto.getUpdatedBy());
			licenceTypeMaster.setUpdatedDate(GlobalCommonDateUtils.getDate(liceneceTypeMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			licenceTypeMaster.setOrganizationId(liceneceTypeMasterDto.getOrganizationId());
			save(licenceTypeMaster);
			return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getLiceneceTypeMasterById(int licenceId, int orgId) throws ApplicationException {
		try {
			List<LiceneceTypeMasterDto> liceneceTypeMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_LICENECE_TYPE_MASTER_BY_ID").setInteger("licenceId", licenceId)
					.setInteger("orgId", orgId)
					.setResultTransformer(Transformers.aliasToBean(LiceneceTypeMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, liceneceTypeMasterDtosList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getLiceneceTypeMasterList(int orgId) throws ApplicationException {
		try {
			List<LiceneceTypeMasterDto> liceneceTypeMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_LICENECE_TYPE_MASTER").setInteger("orgId", orgId)
					.setResultTransformer(Transformers.aliasToBean(LiceneceTypeMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, liceneceTypeMasterDtosList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateLiceneceTypeMaster(LiceneceTypeMasterDto liceneceTypeMasterDto) throws ApplicationException {
		try {
			LicenceTypeMaster licenceTypeMaster = findById(liceneceTypeMasterDto.getLicenceTypeId());
			licenceTypeMaster.setLiceenceTypeCode(liceneceTypeMasterDto.getLiceenceTypeCode());
			licenceTypeMaster.setLicenceType(liceneceTypeMasterDto.getLicenceType());
			licenceTypeMaster.setUpdatedBy(liceneceTypeMasterDto.getUpdatedBy());
			licenceTypeMaster.setUpdatedDate(GlobalCommonDateUtils.getDate(liceneceTypeMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(licenceTypeMaster);
			return new Response(SUCCESS, COMMON_UPDATE, COMMON_UPDATE, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateLiceneceTypeMasterStatus(LiceneceTypeMasterDto liceneceTypeMasterDto)
			throws ApplicationException {
		try {
			LicenceTypeMaster licenceTypeMaster = findById(liceneceTypeMasterDto.getLicenceTypeId());
			
			licenceTypeMaster.setStatus(liceneceTypeMasterDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			licenceTypeMaster.setUpdatedBy(liceneceTypeMasterDto.getUpdatedBy());
			licenceTypeMaster.setUpdatedDate(
					GlobalCommonDateUtils.getDate(liceneceTypeMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));

			update(licenceTypeMaster);
			return new Response<LiceneceTypeMasterDto, Integer>(SUCCESS, null, COMMON_UPDATE, null, null);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getLicenceByNameNotId(LiceneceTypeMasterDto liceneceTypeMasterDto) throws ApplicationException {
		try {
			List<LiceneceTypeMasterDto> liceneceTypeMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_LICENECE_LIST_BY_NAME_NOT_ID")
					.setInteger("licenseId", liceneceTypeMasterDto.getLicenceTypeId())
					.setString("licenceType", liceneceTypeMasterDto.getLicenceType())
					.setResultTransformer(Transformers.aliasToBean(LiceneceTypeMasterDto.class)).list();
			return new Response(SUCCESS, null, null, liceneceTypeMasterDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, null, null, null);
	}

}
