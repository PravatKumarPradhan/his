package com.param.global.org.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.adt.master.global.model.OrganizationMaster;
import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.sync.OrganisationMasterDtoSync;
import com.param.global.model.CountryMaster;
import com.param.global.org.dto.OrganizationMasterDto;
import com.param.global.org.dto.OrganizationUnitLicenceDetailsDto;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "unchecked", "rawtypes" })
public class GlobalOrganisationMasterDaoImpl extends GenericDao<OrganizationMaster, Integer> implements IGlobalOrganisationMasterDao, ICommonConstants {

	
	public GlobalOrganisationMasterDaoImpl() {
		super(OrganizationMaster.class);
	}

	@Override
	public Response getGlobalOrganisationMasterForSyncById(Integer organisationId) throws ApplicationException {
		try {
			List<OrganisationMasterDtoSync> organisationMasterList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ORGANISATION_MASTER_RECORD_FORSYNC_BY_ID")
					.setInteger("orgId", organisationId)
					.setResultTransformer(Transformers.aliasToBean(OrganisationMasterDtoSync.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, organisationMasterList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, ERROR_CODE, null, null, null);
	}

	@Override
	public Response saveOrganizationMaster(OrganizationMasterDto organizationMasterDto)throws ApplicationException {
		try{
			OrganizationMaster organizationMaster = new OrganizationMaster();
			organizationMaster.setAreaId(organizationMasterDto.getAreaId());
			organizationMaster.setCityId(organizationMasterDto.getCityId());
			organizationMaster.setCountryId(organizationMasterDto.getCountryId());
			organizationMaster.setCreatedBy(1);
			organizationMaster.setUpdatedBy(1);
			organizationMaster.setCreatedDate(GlobalCommonDateUtils.getDate(new Date(), "dd-M-yyyy hh:mm:ss"));
			organizationMaster.setUpdatedDate(GlobalCommonDateUtils.getDate(new Date(), "dd-M-yyyy hh:mm:ss"));
			organizationMaster.setDistrictId(organizationMasterDto.getDistrictId());
			organizationMaster.setOrganizationAddress(organizationMasterDto.getOrganizationAddress());
			organizationMaster.setOrganizationCode(organizationMasterDto.getOrganizationCode());
			organizationMaster.setOrganizationContact(organizationMasterDto.getOrganizationContact());
			organizationMaster.setOrganizationDesc(organizationMasterDto.getOrganizationDesc());
			organizationMaster.setOrganizationEmailId(organizationMasterDto.getOrganizationEmailId());
			organizationMaster.setOrganizationName(organizationMasterDto.getOrganizationName());
			organizationMaster.setOrganizationLogo(organizationMasterDto.getOrganizationLogo());
			organizationMaster.setPostCode(organizationMasterDto.getPostCode());
			organizationMaster.setStateId(organizationMasterDto.getStateId());
			organizationMaster.setStatus(organizationMasterDto.getStatus());
			
			organizationMaster = save(organizationMaster);
			return new Response<>(SUCCESS, SUCCESS_CODE, null, null, organizationMaster);
			
		}catch(HibernateException he){
			he.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, null, null, null, null);
	}

	@Override
	public Response updateOrganizationMaster(OrganizationMasterDto organizationMasterDto)throws ApplicationException {
		try{
			OrganizationMaster organizationMaster = new OrganizationMaster();
			organizationMaster.setOrganizationId(organizationMasterDto.getOrganizationId());
			organizationMaster.setAreaId(organizationMasterDto.getAreaId());
			organizationMaster.setCityId(organizationMasterDto.getCityId());
			organizationMaster.setCountryId(organizationMasterDto.getCountryId());
			organizationMaster.setCreatedBy(1);
			organizationMaster.setUpdatedBy(1);
			organizationMaster.setCreatedDate(GlobalCommonDateUtils.getDate(new Date(), "dd-M-yyyy hh:mm:ss"));
			organizationMaster.setUpdatedDate(GlobalCommonDateUtils.getDate(new Date(), "dd-M-yyyy hh:mm:ss"));
			organizationMaster.setDistrictId(organizationMasterDto.getDistrictId());
			organizationMaster.setOrganizationAddress(organizationMasterDto.getOrganizationAddress());
			organizationMaster.setOrganizationCode(organizationMasterDto.getOrganizationCode());
			organizationMaster.setOrganizationContact(organizationMasterDto.getOrganizationContact());
			organizationMaster.setOrganizationDesc(organizationMasterDto.getOrganizationDesc());
			organizationMaster.setOrganizationEmailId(organizationMasterDto.getOrganizationEmailId());
			organizationMaster.setOrganizationName(organizationMasterDto.getOrganizationName());
			organizationMaster.setOrganizationLogo(organizationMasterDto.getOrganizationLogo());
			organizationMaster.setPostCode(organizationMasterDto.getPostCode());
			organizationMaster.setStateId(organizationMasterDto.getStateId());
			organizationMaster.setStatus(organizationMasterDto.getStatus());
			
			organizationMaster = update(organizationMaster);
			return new Response<>(SUCCESS, SUCCESS_CODE, null, null, organizationMaster);
			
		}catch(HibernateException he){
			he.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, null, null, null, null);
	}

	@Override
	public Response getOrganizationByName(String organizationName)throws ApplicationException {
		try{
			List<OrganizationMasterDto> listOrganizationMasterDto = sessionFactory.getCurrentSession().getNamedQuery("GET_ORGANIZATION_BY_NAME").setString("organizationName", organizationName.toLowerCase().trim()).setResultTransformer(Transformers.aliasToBean(OrganizationMasterDto.class)).list();
			return new Response<>(SUCCESS, null, null, listOrganizationMasterDto, null);
		}catch(HibernateException he){
			he.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, null, null, null, null);
	}

	@Override
	public Response getOrganizationById(Integer organizationId)throws ApplicationException {
		try{
			OrganizationMasterDto organizationMasterDto = (OrganizationMasterDto) sessionFactory.getCurrentSession().getNamedQuery("GET_ORGANIZATION_BY_ID").setInteger("organizationId", organizationId).setResultTransformer(Transformers.aliasToBean(OrganizationMasterDto.class)).uniqueResult();
			if(organizationId != null){
				List<OrganizationUnitLicenceDetailsDto> listLicenceDetailsDtos = null;
				String query = sessionFactory.getCurrentSession().getNamedQuery("GET_LICENCE_DETAILS").getQueryString();
				query = (query + " WHERE licence.organization_id ="+organizationId);
				listLicenceDetailsDtos = sessionFactory.getCurrentSession().createSQLQuery(query).setResultTransformer(Transformers.aliasToBean(OrganizationUnitLicenceDetailsDto.class)).list();
				organizationMasterDto.setListOrganizationUnitLicenceDetailsDto(listLicenceDetailsDtos);
			}
			return new Response<>(SUCCESS, null, null, null, organizationMasterDto);
		}catch(HibernateException he){
			he.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, null, null, null, null);
	}

	@Override
	public Response getOrganizationByNameAndNotById(OrganizationMasterDto organizationMasterDto)throws ApplicationException {
		try{
			List<OrganizationMasterDto> listOrganizationMasterDto = sessionFactory.getCurrentSession().getNamedQuery("GET_ORGANIZATION_BY_NAME_AND_NOT_BY_ID").setString("organizationName", organizationMasterDto.getOrganizationName().toLowerCase().trim()).setInteger("organizationId", organizationMasterDto.getOrganizationId()).setResultTransformer(Transformers.aliasToBean(OrganizationMasterDto.class)).list();
			return new Response<>(SUCCESS, null, null, listOrganizationMasterDto, null);
		}catch(HibernateException he){
			he.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, null, null, null, null);
	}

	@Override
	public Response getAllOrganizationMasterList(OrganizationMasterDto organizationMasterDto) throws ApplicationException {
		try{
			List<OrganizationMasterDto> listOrganizationMasterDto = sessionFactory.getCurrentSession()
																	.getNamedQuery("GET_ALL_ORGNIZATION_LIST")
																	.setFirstResult(organizationMasterDto.getOffset() != null ? organizationMasterDto.getOffset() : 0)
																	.setMaxResults(organizationMasterDto.getNoOfRecordsPerPage() != null ? organizationMasterDto.getNoOfRecordsPerPage() : 10)
																	.setResultTransformer(Transformers.aliasToBean(OrganizationMasterDto.class))
																	.list();
			return new Response<>(SUCCESS, null, null, listOrganizationMasterDto, null);
		}catch(HibernateException he){
			he.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, null, null, null, null);
	}

	@Override
	public Response getOrganizationCount() throws ApplicationException {
		try{
			Session session=sessionFactory.openSession();
			Criteria c=session.createCriteria(OrganizationMaster.class); 
			//c.add(Restrictions.eq("organizationId", countryMasterDto.getOrganizationId()));
			c.setProjection(Projections.rowCount());  
			Long count = (Long)c.uniqueResult();
			return new Response(SUCCESS, null, null, null, count);
		}catch(Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, null, null, null, null);
		}
	}

	@Override
	public Response updateOrganizationStatus(OrganizationMasterDto organizationMasterDto)throws ApplicationException {
		try{
			Integer result = sessionFactory.getCurrentSession().getNamedQuery("UPDATE_ORGANIZATION_STATUS").setCharacter("status", organizationMasterDto.getStatus()).setInteger("organizationId", organizationMasterDto.getOrganizationId()).executeUpdate();
			if(result != null){
				return new Response<>(SUCCESS, null, null, null, null);
			}
		}catch(HibernateException he){
			he.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, null, null, null, null);
	}

	@Override
	public Response getActiveOrganizationList() throws ApplicationException {
		try{
			List<OrganizationMasterDto> listOrganizationMasterDto = sessionFactory.getCurrentSession().getNamedQuery("GET_ACTIVE_ORGANIZATION_MASTER_LIST").setResultTransformer(Transformers.aliasToBean(OrganizationMasterDto.class)).list();
			return new Response<>(SUCCESS, null, null, listOrganizationMasterDto, null);
		}catch(HibernateException he){
			he.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, null, null, null, null);
	}

	

}
