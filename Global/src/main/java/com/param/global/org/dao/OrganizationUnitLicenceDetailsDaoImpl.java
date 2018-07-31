package com.param.global.org.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.param.entity.org.model.OrganizationUnitLicenceDetails;
import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.org.dto.OrganizationUnitLicenceDetailsDto;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({"rawtypes","unchecked"})
public class OrganizationUnitLicenceDetailsDaoImpl extends GenericDao<OrganizationUnitLicenceDetails, Integer> implements IOrganizationUnitLicenceDetailsDao ,ICommonConstants{

	public OrganizationUnitLicenceDetailsDaoImpl() {
		super(OrganizationUnitLicenceDetails.class);
	}

	
	@Override
	public Response saveOrganizationUnitLicenceDetails(OrganizationUnitLicenceDetailsDto organizationUnitLicenceDetailsDto)throws ApplicationException {
		try{
			OrganizationUnitLicenceDetails organizationUnitLicenceDetails = new OrganizationUnitLicenceDetails();
			organizationUnitLicenceDetails.setExpiryDate(GlobalCommonDateUtils.getDate(organizationUnitLicenceDetailsDto.getExpiryDate(), "dd-M-yyyy"));
			organizationUnitLicenceDetails.setCreatedBy(1);
			organizationUnitLicenceDetails.setCreatedDate(GlobalCommonDateUtils.getDate(new Date(), "dd-M-yyyy"));
			organizationUnitLicenceDetails.setLicenceTypeId(organizationUnitLicenceDetailsDto.getLicenceTypeId());
			organizationUnitLicenceDetails.setLicenceNumber(organizationUnitLicenceDetailsDto.getLicenceNumber());
			organizationUnitLicenceDetails.setIsUnit(organizationUnitLicenceDetailsDto.getIsUnit());
			organizationUnitLicenceDetails.setOrganizationId(organizationUnitLicenceDetailsDto.getOrganizationId());
			organizationUnitLicenceDetails.setUnitId(organizationUnitLicenceDetailsDto.getUnitId());
			organizationUnitLicenceDetails = save(organizationUnitLicenceDetails);
			return new Response<>(SUCCESS, null, null, null, organizationUnitLicenceDetails);
			
		}catch(HibernateException he){
			he.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, null, null, null, null);
	}


	@Override
	public Response getLicenceDetailsByOrgUnitId(OrganizationUnitLicenceDetailsDto organizationUnitLicenceDetailsDto)throws ApplicationException {
	try{
		List<OrganizationUnitLicenceDetailsDto> listLicenceDetailsDtos = null;
		String query = sessionFactory.getCurrentSession().getNamedQuery("GET_LICENCE_DETAILS").getQueryString();
		if(organizationUnitLicenceDetailsDto.getOrganizationId() != null){
			query = (query + " WHERE licence.organization_id ="+organizationUnitLicenceDetailsDto.getOrganizationId());
		}else if(organizationUnitLicenceDetailsDto.getUnitId() != null){
			query = (query + " WHERE licence.unit_id ="+organizationUnitLicenceDetailsDto.getUnitId());
		}
		
		listLicenceDetailsDtos = sessionFactory.getCurrentSession().createSQLQuery(query).setResultTransformer(Transformers.aliasToBean(OrganizationUnitLicenceDetailsDto.class)).list();
		
		return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, listLicenceDetailsDtos, null);
	}catch(Exception e){
		e.printStackTrace();
	}
	return new Response<>(ERROR, null, null, null, null);
	}

}
