package com.param.service.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.param.billing.exception.ServiceException;
import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.ServiceSearchReqDto;
import com.param.global.dto.ServiceSearchResDto;
import com.param.service.dto.MPackageMasterDto;
import com.param.service.dto.ServiceForPackageReqDto;
import com.param.service.dto.ServiceForPackageResDto;
import com.param.service.global.model.MPackageMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({"rawtypes","unchecked"})
public class MPackageMasterDaoImpl extends GenericDao<MPackageMaster, Integer> implements IMPackageMasterDao, ICommonConstants{

	public MPackageMasterDaoImpl() {
		super(MPackageMaster.class);
	}

	@Override
	public Response savePackageMaster(MPackageMasterDto masterDto) throws ServiceException {
		try{
			MPackageMaster mPackageMaster = new MPackageMaster();
			mPackageMaster.setBillingBedCategoryId(masterDto.getBillingBedCategoryId());
			mPackageMaster.setCreatedBy(masterDto.getCreatedBy());
			mPackageMaster.setCreatedDate(masterDto.getCreatedDate() != null && !masterDto.getCreatedDate().isEmpty() ? GlobalCommonDateUtils.getDate(masterDto.getCreatedDate(), "dd-M-yyyy HH:mm:ss") : new Date());
			mPackageMaster.setIsManualRoundingIsAllow(masterDto.getIsManualRoundingIsAllow());
			mPackageMaster.setManualRoundoffAmount(masterDto.getManualRoundoffAmount());
			mPackageMaster.setMarkupDownInPercentage(masterDto.getMarkupDownInPercentage());
			mPackageMaster.setMaxAge(masterDto.getMaxAge());
			mPackageMaster.setMinAge(masterDto.getMinAge());
			mPackageMaster.setOrgId(masterDto.getOrgId());
			mPackageMaster.setPackageCost(masterDto.getPackageCost());
			mPackageMaster.setPackagePrice(masterDto.getPackagePrice());
			mPackageMaster.setPackageTypeId(masterDto.getPackageTypeId());
			mPackageMaster.setPatientTypeId(masterDto.getPatientTypeId());
			mPackageMaster.setPaymentEntitlementTypeId(masterDto.getPaymentEntitlementTypeId());
			mPackageMaster.setServiceId(masterDto.getServiceId());
			mPackageMaster.setSexId(masterDto.getSexId());
			mPackageMaster.setStatus(ACTIVE);
			mPackageMaster.setUnitId(masterDto.getUnitId());
			mPackageMaster.setUpdatedBy(masterDto.getUpdatedBy());
			mPackageMaster.setUpdatedDate(masterDto.getUpdatedDate() != null && !masterDto.getUpdatedDate().isEmpty() ? GlobalCommonDateUtils.getDate(masterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss") : new Date());
			mPackageMaster.setValidityFromDate(masterDto.getValidityFromDate() != null && !masterDto.getValidityFromDate().isEmpty() ? GlobalCommonDateUtils.getDate(masterDto.getValidityFromDate(), "dd-M-yyyy") : null);
			mPackageMaster.setValidityPeriodIndays(masterDto.getValidityPeriodIndays());
			mPackageMaster.setValidityToDate(masterDto.getValidityToDate() != null && !masterDto.getValidityToDate().isEmpty() ? GlobalCommonDateUtils.getDate(masterDto.getValidityToDate(), "dd-M-yyyy") : null);
			mPackageMaster.setVisitTypeId(masterDto.getVisitTypeId());
			mPackageMaster.setTariffId(masterDto.getTariffId());
			mPackageMaster.setIsMarkupDown(masterDto.getIsMarkupDown());
			mPackageMaster.setRoundOffPositNeg(masterDto.getRoundOffPositNeg());
			mPackageMaster.setNoOfEncounters(masterDto.getNoOfEncounters());
			mPackageMaster = save(mPackageMaster);
			return new Response<>(SUCCESS, SUCCESS_CODE, null, null, mPackageMaster);
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public Response searchServiceForPackage(ServiceForPackageReqDto reqDto) throws ServiceException {
		try{
			StringBuilder query = new StringBuilder(sessionFactory.getCurrentSession().getNamedQuery("SEARCH_SERVICE_FOR_PACKAGES").getQueryString());
			if(reqDto.getTariffId() != null){
				query.append(reqDto.getTariffId() != null ? " and srvtrf.tariff_id ="+reqDto.getTariffId() : " ");
				query.append(reqDto.getPaymentEntitlementId() != null ? " and srvtrf.payment_entitlement_id ="+reqDto.getPaymentEntitlementId() : " ");
				query.append(reqDto.getPatientTypeId() != null ? " and srvtrf.patient_type_id =" + reqDto.getPatientTypeId() : " ");
			}
			
			query.append(reqDto.getIsDoctorRequired() != null ? " and serv.is_doctor_required ='"+reqDto.getIsDoctorRequired()+"'"  : " ");
			query.append(reqDto.getGroupId() != null ? " and sp.speciality_id ="+reqDto.getGroupId() : " ");
			query.append(reqDto.getSubGroupId() != null ? " and subsp.sub_speciality_id ="+reqDto.getSubGroupId() : " ");
			query.append((reqDto.getServiceCode() != null && !reqDto.getServiceCode().isEmpty()) ? " and lower(trim(serv.service_standard_code)) like '%"+ reqDto.getServiceCode().trim().toLowerCase() +"%'" : " ");
			query.append((reqDto.getServiceDescription() != null && !reqDto.getServiceDescription().isEmpty()) ? " and lower(trim(serv.service_standard_name)) like '%"+ reqDto.getServiceDescription().trim().toLowerCase() +"%'" : " ");
			query.append(" order by serv.service_standard_code ");
			List<ServiceForPackageResDto> listServiceForPackageResDto = sessionFactory.getCurrentSession().createSQLQuery(query.toString()).setInteger("orgId", reqDto.getOrgId()).setResultTransformer(Transformers.aliasToBean(ServiceForPackageResDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, listServiceForPackageResDto, null);
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public Response getEHCPackagesList(ServiceForPackageReqDto reqDto) throws ApplicationException {
		try {
			List<ServiceForPackageReqDto> slotMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_EHC_PACKAGES_LIST")
					.setInteger("orgId", reqDto.getOrgId())
					.setInteger("unitId", reqDto.getUnitId())
					.setInteger("age",reqDto.getAge())
					.setInteger("sexId",reqDto.getSexId())
					.setResultTransformer(Transformers.aliasToBean(ServiceForPackageReqDto.class)).list();
			return new Response(SUCCESS, null, null, slotMasterDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response searchServicesForBilling(ServiceSearchReqDto reqDto) throws ServiceException {
		try {
			StringBuilder query = new StringBuilder(sessionFactory.getCurrentSession().getNamedQuery("SEARCH_PACKAGE_SERVICES_FOR_BILLING").getQueryString());
			query.append((reqDto.getServiceCode() != null && !reqDto.getServiceCode().isEmpty()) ? " and lower(trim(service.service_standard_code))like '%"+reqDto.getServiceCode().toLowerCase().trim()+"%'" : " ");
			query.append((reqDto.getServiceName() != null && !reqDto.getServiceName().isEmpty()) ? " and lower(trim(service.service_standard_name)) like '%"+reqDto.getServiceName().toLowerCase().trim()+"%'" : " ");
			query.append(reqDto.getVisitTypeId() != null ? " and pack.visit_type_id ="+reqDto.getVisitTypeId() : " ");
			query.append(reqDto.getBedBillingCategoryId() != null ? " and pack.billing_bed_category_id =" +reqDto.getBedBillingCategoryId() : " ");
			List<ServiceSearchResDto> listServiceSearchResDto = sessionFactory.getCurrentSession().createSQLQuery(query.toString()).setInteger("unitId", reqDto.getUnitId()).setInteger("orgId", reqDto.getOrganisationId()).setInteger("packageTypeId", reqDto.getPackageTypeId()).setResultTransformer(Transformers.aliasToBean(ServiceSearchResDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, listServiceSearchResDto, null);
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public Response getPackageById(int packageId) throws ServiceException {
		try {
			MPackageMasterDto mPackage = (MPackageMasterDto)sessionFactory.getCurrentSession().getNamedQuery("GET_PACKAGE_BY_ID").setInteger("packageId", packageId).setResultTransformer(Transformers.aliasToBean(MPackageMasterDto.class)).uniqueResult();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, null, mPackage);
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

}
