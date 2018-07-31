package com.param.global.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.adt.master.global.model.UnitMaster;
import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.sync.UnitMasterDtoSync;
import com.param.global.org.dto.OrganizationUnitLicenceDetailsDto;
import com.param.global.org.dto.UnitMasterDto;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({"rawtypes","unchecked"})
public class GlobalUnitMasterDaoImpl extends GenericDao<UnitMaster, Integer> implements IGlobalUnitMasterDao, ICommonConstants {

	public GlobalUnitMasterDaoImpl() {
		super(UnitMaster.class);
	}

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Response getUnitMasterForSyncById(Integer unitId) throws ApplicationException {
		try {
			List<UnitMasterDtoSync> unitMasterList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_UNITMASTERRECORD_FORSYNC_BY_ID")
					.setInteger("unitId", unitId)
					.setResultTransformer(Transformers.aliasToBean(UnitMasterDtoSync.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, unitMasterList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(SUCCESS, SUCCESS_CODE, null, null, null);
	}

	@Override
	public Response saveUnitMaster(UnitMasterDto unitMasterDto)throws ApplicationException {
		try{
			UnitMaster unitMaster = new UnitMaster();
			unitMaster.setOrganizationId(unitMasterDto.getOrganizationId());
			unitMaster.setUnitCode(unitMasterDto.getUnitCode());
			unitMaster.setCreatedBy(1);
			unitMaster.setCreatedDate(GlobalCommonDateUtils.getDate(new Date(), "dd-M-yyyy"));
			unitMaster.setPostCode(unitMasterDto.getPostCode());
			unitMaster.setUnitName(unitMasterDto.getUnitName());
			unitMaster.setUnitCode(unitMasterDto.getUnitCode());
			unitMaster.setUnitCountryId(unitMasterDto.getUnitCountryId());
			unitMaster.setUnitStateId(unitMasterDto.getUnitStateId());
			unitMaster.setUnitCityId(unitMasterDto.getUnitCityId());
			unitMaster.setUnitDistrictId(unitMasterDto.getUnitDistrictId());
			unitMaster.setUnitAreaId(unitMasterDto.getUnitAreaId());
			unitMaster.setUnitAddress(unitMasterDto.getUnitAddress());
			unitMaster.setUnitEmailId(unitMasterDto.getUnitEmailId());
			unitMaster.setUnitLogo(unitMasterDto.getUnitLogo());
			unitMaster.setStatus(unitMasterDto.getStatus());
			unitMaster.setUnitDescription(unitMasterDto.getUnitDescription());
			unitMaster.setUnitContact(unitMasterDto.getUnitContact());
			
			unitMaster = save(unitMaster);
			
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, unitMaster);
			
		}catch(HibernateException he){
			he.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, null, null, null);
	}

	@Override
	public Response updateUnitMaster(UnitMasterDto unitMasterDto)throws ApplicationException {
		try{
			
			UnitMaster unitMaster = new UnitMaster();
			unitMaster.setUnitId(unitMasterDto.getUnitId());
			unitMaster.setOrganizationId(unitMasterDto.getOrganizationId());
			unitMaster.setUnitCode(unitMasterDto.getUnitCode());
			unitMaster.setCreatedBy(1);
			unitMaster.setCreatedDate(GlobalCommonDateUtils.getDate(new Date(), "dd-M-yyyy"));
			unitMaster.setPostCode(unitMasterDto.getPostCode());
			unitMaster.setUnitName(unitMasterDto.getUnitName());
			unitMaster.setUnitCode(unitMasterDto.getUnitCode());
			unitMaster.setUnitCountryId(unitMasterDto.getUnitCountryId());
			unitMaster.setUnitStateId(unitMasterDto.getUnitStateId());
			unitMaster.setUnitCityId(unitMasterDto.getUnitCityId());
			unitMaster.setUnitDistrictId(unitMasterDto.getUnitDistrictId());
			unitMaster.setUnitAreaId(unitMasterDto.getUnitAreaId());
			unitMaster.setUnitAddress(unitMasterDto.getUnitAddress());
			unitMaster.setUnitEmailId(unitMasterDto.getUnitEmailId());
			unitMaster.setUnitLogo(unitMasterDto.getUnitLogo());
			unitMaster.setStatus(unitMasterDto.getStatus());
			unitMaster.setUnitDescription(unitMasterDto.getUnitDescription());
			unitMaster.setUnitContact(unitMasterDto.getUnitContact());
			
			unitMaster = update(unitMaster);
			
			return new Response<>(SUCCESS, SUCCESS_CODE, COMMON_UPDATE, null, unitMaster);
		}catch(HibernateException he){
			he.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, null, null, null);
	}

	@Override
	public Response getUnitById(Integer unitId)throws ApplicationException {
		try{
			UnitMasterDto unitMasterDto = (UnitMasterDto)sessionFactory.getCurrentSession().getNamedQuery("GET_UNIT_MASTER_BY_ID").setInteger("unitId", unitId).setResultTransformer(Transformers.aliasToBean(UnitMasterDto.class)).uniqueResult();
			if(unitId != null){
				List<OrganizationUnitLicenceDetailsDto> listOrganizationUnitLicenceDetailsDto = null;
				String query = sessionFactory.getCurrentSession().getNamedQuery("GET_LICENCE_DETAILS").getQueryString();
				query = (query + " WHERE licence.unit_id ="+unitId);
				listOrganizationUnitLicenceDetailsDto = sessionFactory.getCurrentSession().createSQLQuery(query).setResultTransformer(Transformers.aliasToBean(OrganizationUnitLicenceDetailsDto.class)).list();
				unitMasterDto.setListOrganizationUnitLicenceDetailsDto(listOrganizationUnitLicenceDetailsDto);
			}
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, unitMasterDto);
		}catch(HibernateException he){
			he.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, null, null, null);
	}

	@Override
	public Response getUnitByName(UnitMasterDto unitMasterDto)throws ApplicationException {
		try{
			List<UnitMasterDto> listUnitMasterDto = sessionFactory.getCurrentSession().getNamedQuery("GET_UNIT_BY_NAME").setString("unitName", unitMasterDto.getUnitName().toLowerCase().trim()).setResultTransformer(Transformers.aliasToBean(UnitMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, listUnitMasterDto, null);
		}catch(HibernateException he){
			he.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, null, null, null);
	}

	@Override
	public Response getUnitByNameAndNotById(UnitMasterDto unitMasterDto)throws ApplicationException {
		try{
			List<UnitMasterDto> listUnitMasterDto = sessionFactory.getCurrentSession().getNamedQuery("GET_UNIT_BY_NAME_AND_NOT_BY_ID").setString("unitName", unitMasterDto.getUnitName().toLowerCase().trim()).setInteger("unitId", unitMasterDto.getUnitId()).setResultTransformer(Transformers.aliasToBean(UnitMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, listUnitMasterDto, null);
		}catch(HibernateException he){
			he.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, null, null, null);
	}

	@Override
	public Response updateUnitStatusById(UnitMasterDto unitMasterDto)throws ApplicationException {
		try{
			Integer result = sessionFactory.getCurrentSession().getNamedQuery("UPDATE_UNIT_STATUS_BY_ID").setInteger("unitId", unitMasterDto.getUnitId()).setCharacter("status", unitMasterDto.getStatus()).executeUpdate();
			if(result != null){
				return new Response<>(SUCCESS, SUCCESS_CODE, COMMON_UPDATE, null, null);
			}
					
		}catch(HibernateException he){
			he.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, null, null, null);
	}

	@Override
	public Response getAllUnitList(UnitMasterDto unitMasterDto)throws ApplicationException {
		try{
			List<UnitMasterDto> listUnitMasterDto = sessionFactory.getCurrentSession().getNamedQuery("GET_ALL_UNIT_LIST").
													setFirstResult(unitMasterDto.getOffSet() != null ? unitMasterDto.getOffSet() : 0).
													setMaxResults(unitMasterDto.getNoOfRecordsPerPage() != null ? unitMasterDto.getNoOfRecordsPerPage() : 10).
													setResultTransformer(Transformers.aliasToBean(UnitMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, listUnitMasterDto, null);
		}catch(HibernateException he){
			he.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, null, null, null);
	}

	@Override
	public Response getUnitTotalCount()throws ApplicationException {
		try{
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(UnitMaster.class);
			criteria.setProjection(Projections.rowCount());
			Long count = (Long) criteria.uniqueResult();
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, count);
		}catch(HibernateException he){
			he.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
