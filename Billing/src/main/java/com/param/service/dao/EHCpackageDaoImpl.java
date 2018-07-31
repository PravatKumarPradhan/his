package com.param.service.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.billing.common.ICommonConstants;
import com.param.billing.common.IError;
import com.param.billing.exception.ServiceException;
import com.param.global.common.Response;
import com.param.service.dto.CommonDto;
import com.param.service.dto.EhcPackageSearchResDto;
import com.param.service.dto.ServiceForPackageReqDto;

import in.param.exception.ApplicationException;


@Repository
@SuppressWarnings(
{ "rawtypes", "unchecked" })
public class EHCpackageDaoImpl   implements IEHCpackageDao, ICommonConstants, IError {

	final static Logger logger = Logger.getLogger(EHCpackageDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Response getPackgeType(Integer orgId, Integer orgUnitId) throws ApplicationException {
		List<CommonDto>  listPackgeType  = null; 
		try
		{
			listPackgeType = (List<CommonDto>) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PACKAGE_TYPE_MASTER_LIST").setInteger("orgId", orgId)
					.setInteger("orgUnitId", orgUnitId)
                    .setResultTransformer(Transformers.aliasToBean(CommonDto.class)).list();					
			return new Response(SUCCESS, SUCCESS_CODE, SUCCESS, listPackgeType, null);

		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, PACKAGE_TYPE_ERROR, null, null);
		}
	}

	@Override
	public Response getAutoCompleteServices(Integer orgId, Integer orgUnitId, String serviceName)
			throws ApplicationException {
		List<CommonDto>  listAutoComplete  = null; 
		try
		{
			listAutoComplete = (List<CommonDto>) sessionFactory.getCurrentSession()
					.createSQLQuery("SELECT	serv.service_master_id as id, "
							+ "  	serv.service_standard_name as name "
							+ " from service.m_service_master serv "
							+ " inner join service.t_unit_service_master_details srvdtls "
							+ " on srvdtls.service_master_id = serv.service_master_id "
							+ " where srvdtls.organization_id = :orgId and srvdtls.unit_id = :unitId "
							+ " and srvdtls.is_package = 'A' "
							+ " and lower(trim(serv.service_standard_name)) LIKE :serviceName ")
					.setInteger("orgId", orgId)
					.setInteger("unitId", orgUnitId)
					.setString("serviceName", "%"+serviceName.trim().toLowerCase()+"%")
                    .setResultTransformer(Transformers.aliasToBean(CommonDto.class)).list();					
			return new Response(SUCCESS, SUCCESS_CODE, SUCCESS, listAutoComplete, null);

		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response getTariff(Integer orgId, Integer orgUnitId) throws ApplicationException {
		List<CommonDto>  listTariff  = null; 
		try
		{
			 String pattern = "dd-MM-yyyy";
			String toDate =new SimpleDateFormat(pattern).format(new Date());
			listTariff = (List<CommonDto>) sessionFactory.getCurrentSession()
					.createSQLQuery("SELECT"
							+ "   tariffmst.tariff_id as \"id\","
							+ "   tariffmst.tariff_desc as\"name\" "
							+ " FROM"
							+ "   service.m_tariff_master tariffmst"
							+ " WHERE"
							+ "   to_date('"+toDate+"', 'DD-MM-YYYY') BETWEEN tariffmst.valid_from"
							+ "   AND tariffmst.valid_to"
							+ "   AND tariffmst.organization_id = :orgId"
							+ "   AND tariffmst.unit_id = :orgUnitId")
					 .setInteger("orgId", orgId)
					 .setInteger("orgUnitId", orgUnitId)
                    .setResultTransformer(Transformers.aliasToBean(CommonDto.class)).list();					
			return new Response(SUCCESS, SUCCESS_CODE, SUCCESS, listTariff, null);

		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response getPaymentEntitlementByTariffId(Integer orgId, Integer orgUnitId, Integer tariffId)
			throws ApplicationException {
		List<CommonDto>  listPaymentEntitlement  = null; 
		try
		{
			listPaymentEntitlement = (List<CommonDto>) sessionFactory.getCurrentSession()
					.createSQLQuery( "SELECT "
							+ " payen.payment_entitlement_desc  AS \"name\","
							+ " mppr.payment_entitlement_id  AS \"id\" "
							+ " FROM service.t_tariff_payment_entitlement_mapper mppr "
							+ " INNER JOIN public.m_payment_entitlement_master payen "
							+ " ON payen.payment_entitlement_id = mppr.payment_entitlement_id "
							+ " WHERE mppr.tariff_id = :tariffId "
							+ " AND mppr.organization_id = :orgId "
							+ " AND mppr.unit_id = :orgUnitId ")
					 .setInteger("orgId", orgId)
					 .setInteger("orgUnitId", orgUnitId)
					 .setInteger("tariffId", tariffId)
                    .setResultTransformer(Transformers.aliasToBean(CommonDto.class)).list();					
			return new Response(SUCCESS, SUCCESS_CODE, SUCCESS, listPaymentEntitlement, null);

		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response getPatientTypeByTariffId(Integer orgId, Integer orgUnitId, Integer tariffId)
			throws ApplicationException {
		List<CommonDto>  listPatientType  = null; 
		try
		{
			listPatientType = (List<CommonDto>) sessionFactory.getCurrentSession()
					.createSQLQuery("SELECT"
							+ " payen.patient_category AS \"name\", "
							+ " mapper.patient_type_id AS \"id\" "
							+ " FROM"
							+ " service.t_tariff_patient_type_mapper mapper"
							+ "   INNER JOIN public.m_patient_category_master payen ON payen.patient_category_id = mapper.patient_type_id"
							+ " WHERE"
							+ "  mapper.tariff_id =:tariffId"
							+ " AND mapper.organization_id = :orgId "
							+ " AND mapper.unit_id = :orgUnitId ")
					 .setInteger("orgId", orgId)
					 .setInteger("orgUnitId", orgUnitId)
					 .setInteger("tariffId", tariffId)
                    .setResultTransformer(Transformers.aliasToBean(CommonDto.class)).list();					
			return new Response(SUCCESS, SUCCESS_CODE, SUCCESS, listPatientType, null);

		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response getAutoCompletePackageCode(ServiceForPackageReqDto reqDto)
			throws ApplicationException {
		try{
			StringBuilder query = new StringBuilder(sessionFactory.getCurrentSession().getNamedQuery("SEARCH_EHC_PACKAGES").getQueryString());
			query.append(reqDto.getPackagetypeId() != null ? " and pack.package_type_id ="+reqDto.getPackagetypeId() : " ");
			query.append((reqDto.getServiceDescription() != null && !reqDto.getServiceDescription().isEmpty()) ? " and lower(trim(serv.service_standard_name)) like '%"+ reqDto.getServiceDescription().trim().toLowerCase() +"%'" : " ");
			query.append((reqDto.getServiceCode() != null && !reqDto.getServiceCode().isEmpty()) ? " and lower(trim(serv.service_standard_code)) like '%"+ reqDto.getServiceCode().trim().toLowerCase() +"%'" : " ");
			query.append("order by pack.package_master_id ");
			List<EhcPackageSearchResDto> listEhcPackageSearchResDto = sessionFactory.getCurrentSession().createSQLQuery(query.toString())
					.setInteger("orgId", reqDto.getOrgId()).setInteger("unitId", reqDto.getUnitId()).setResultTransformer(Transformers.aliasToBean(EhcPackageSearchResDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, listEhcPackageSearchResDto, null);
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public Response getAutoCompletePackageName(Integer orgId, Integer orgUnitId, String serviceName)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

}
