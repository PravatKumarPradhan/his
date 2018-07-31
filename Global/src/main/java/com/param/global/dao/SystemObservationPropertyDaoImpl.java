package com.param.global.dao;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.SystemObservationMasterDto;
import com.param.global.model.SystemObervationMaster;
import com.param.global.model.SystemObervationPropertyMaster;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class SystemObservationPropertyDaoImpl extends GenericDao<SystemObervationMaster, Integer> implements ISystemObservationPropertyDao, ICommonConstants{


	public SystemObservationPropertyDaoImpl() {
		super(SystemObervationMaster.class);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private ISystemPropertyMasterDao iSystemPropertyMasterDao;

	@Override
	public Response saveSystemObservationProperty(SystemObservationMasterDto systemObservationMasterDto)throws ApplicationException {
		try {
			SystemObervationMaster systemObervationMaster = new SystemObervationMaster();
			systemObervationMaster.setCreatedBy(systemObservationMasterDto.getCreatedBy());
			systemObervationMaster.setCreatedDate(GlobalCommonDateUtils.getDate(systemObservationMasterDto.getCreatedDate(), "dd-mm-yyyy"));
			systemObervationMaster.setIsPropertyRequired(systemObservationMasterDto.getIsPropertyRequired());
			systemObervationMaster.setObservationCode(systemObservationMasterDto.getObservationCode());
			systemObervationMaster.setObservationName(systemObservationMasterDto.getObservationName());
			systemObervationMaster.setOrganizationId(systemObservationMasterDto.getOrganizationId());
			systemObervationMaster.setStatus(systemObservationMasterDto.getStatus());
			systemObervationMaster.setSystemId(systemObservationMasterDto.getSystemId());
			systemObervationMaster.setUnitId(systemObservationMasterDto.getUnitId());
			systemObervationMaster.setUpdatedBy(systemObservationMasterDto.getUpdatedBy());
			systemObervationMaster.setUpdatedDate(GlobalCommonDateUtils.getDate(systemObservationMasterDto.getUpdatedDate(), "dd-mm-yyyy"));
			
			SystemObervationMaster systemObervation = save(systemObervationMaster);
			
			if(systemObservationMasterDto.getListSystemPropertyDto() != null)
			{
				for(int i=0; i<systemObservationMasterDto.getListSystemPropertyDto().size();i++)
				{
					SystemObervationPropertyMaster systemObervationPropertyMaster = new SystemObervationPropertyMaster();
					
					systemObervationPropertyMaster.setObservationId(systemObervation.getObservationId());
					systemObervationPropertyMaster.setPropertyName(systemObservationMasterDto.getListSystemPropertyDto().get(i).getPropertyName());
					systemObervationPropertyMaster.setCreatedBy(systemObservationMasterDto.getCreatedBy());
					systemObervationPropertyMaster.setCreatedDate(GlobalCommonDateUtils.getDate(systemObservationMasterDto.getCreatedDate(), "dd-mm-yyyy"));
					systemObervationPropertyMaster.setOrganizationId(systemObservationMasterDto.getOrganizationId());
					systemObervationPropertyMaster.setStatus(systemObservationMasterDto.getStatus());
					systemObervationPropertyMaster.setUnitId(systemObservationMasterDto.getUnitId());
					systemObervationPropertyMaster.setUpdatedBy(systemObservationMasterDto.getUpdatedBy());
					systemObervationPropertyMaster.setUpdatedDate(GlobalCommonDateUtils.getDate(systemObservationMasterDto.getUpdatedDate(), "dd-mm-yyyy"));
					
					iSystemPropertyMasterDao.saveSystemObservationProperty(systemObervationPropertyMaster);
				}
			}
			return new Response(SUCCESS, SUCCESS_CODE, COMMON_SUCCESS, null, null);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}

	@Override
	public Response getSystemObservationProperty(SystemObservationMasterDto systemObservationPropertyMasterDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		try{
			List<SystemObservationMasterDto> listSystemObservationMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_LIST_SYSTEM_OBSERVATION_PROPERTY")
					.setInteger("unitId", systemObservationPropertyMasterDto.getUnitId())
					.setInteger("organizationId", systemObservationPropertyMasterDto.getOrganizationId())
					.setResultTransformer(Transformers.aliasToBean(SystemObservationMasterDto.class)).list();
			return new Response(SUCCESS, SUCCESS_CODE, COMMON_SUCCESS, listSystemObservationMasterDto, null);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}

	@Override
	public Response updateSystemObservationPropertyStatus(SystemObservationMasterDto systemObservationPropertyMasterDto)
			throws ApplicationException {
		try {

			SystemObervationMaster systemObervationMaster = findById(systemObservationPropertyMasterDto.getObservationId());
			systemObervationMaster.setStatus(systemObservationPropertyMasterDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			systemObervationMaster.setUpdatedBy(systemObservationPropertyMasterDto.getUpdatedBy());
			systemObervationMaster.setUpdatedDate(GlobalCommonDateUtils.getDate(systemObservationPropertyMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(systemObervationMaster);
			
			return new Response(SUCCESS, SUCCESS_CODE, COMMON_SUCCESS, null, null);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}

	@Override
	public Response updateSystemObservationSinglePropertyStatus(
			SystemObservationMasterDto systemObservationPropertyMasterDto) throws ApplicationException {
	try {
		Session session=sessionFactory.openSession();
		Query qry =  session.createQuery("update SystemObervationPropertyMaster set "
				+ "status='" + systemObservationPropertyMasterDto.getStatus() + "',"
				+ "updatedBy="+ systemObservationPropertyMasterDto.getUpdatedBy() + ","
				+ "updatedDate='"+ GlobalCommonDateUtils.getDate(systemObservationPropertyMasterDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss") + "' "
				+ "WHERE propertyId="+ systemObservationPropertyMasterDto.getPropertyId() + " AND observationId ="+ systemObservationPropertyMasterDto.getObservationId());
	
		qry.executeUpdate();
		return new Response(SUCCESS, SUCCESS, SUCCESS, null, null);
		
	} catch (HibernateException he) {
		he.printStackTrace();
	}
	return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}
}
