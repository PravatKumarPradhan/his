package com.param.global.opd.dao;

import java.math.BigInteger;
import java.util.List;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.entity.model.master.Tax;
import com.param.global.common.ICommonConstants;
import com.param.global.common.IError;
import com.param.global.common.Response;
import com.param.global.dto.EncounterSyncDto;
import com.param.global.dto.OrderDetailsMasterDto;
import com.param.global.dto.ServiceMasterDto;
import com.param.global.dto.SlotMasterDto;
import com.param.global.dto.UnitServiceTariffMasterDto;
import com.param.opd.coversheet.model.EncounterMaster;
import com.param.opd.encounter.dto.AppointmentStatusMasterDto;
import com.param.opd.encounter.dto.EncounterMasterDto;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "unchecked", "rawtypes" })
public class EncounterMasterDaoImpl extends GenericDao<EncounterMaster, Integer> implements IEncounterMasterDao, ICommonConstants,IError{

	public EncounterMasterDaoImpl() {
		super(EncounterMaster.class);
	}

	@Autowired
	private DozerBeanMapper mapper;
	
	final static Logger logger = Logger.getLogger(EncounterMasterDaoImpl.class);
	
	@Override
	public Response saveEncounterMaster(EncounterMasterDto encounterMasterDto) throws ApplicationException {
		try {
			EncounterMaster encounterMaster = mapper.map(encounterMasterDto, EncounterMaster.class, "opd_EncounterMasterDto_to_EncounterMaster");
			encounterMaster.setEncounterTime(encounterMasterDto.getEncounterTime());
			encounterMaster.setStatusId(5); // EncounterStatusId
			encounterMaster = save(encounterMaster);
			
			encounterMasterDto.setEncounterId(encounterMaster.getEncounterId());
			
			return new Response(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, encounterMasterDto);
		}catch (HibernateException he) {
			logger.error("Exection", he);
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}

	@Override
	public Response getEncounterDetailsById(Integer encounterId) throws ApplicationException {
		try {
			EncounterMasterDto encounterMasterDto = (EncounterMasterDto) sessionFactory.getCurrentSession().getNamedQuery("GET_ENCOUNTER_DETAILS_BY_ENCOUNTER_ID")
					.setInteger("encounterId", encounterId)
					.setResultTransformer(Transformers.aliasToBean(EncounterMasterDto.class)).uniqueResult();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, null, encounterMasterDto);
		}catch (HibernateException he) {
			logger.error("Exection", he);
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}

	@Override
	public Long getNextValEncounterMasterId() throws ApplicationException {
		try {
			Session session = sessionFactory.openSession();
			SQLQuery query = session.createSQLQuery("select nextval('opd.encounter_master_seq')");
			Long id = ((BigInteger) query.uniqueResult()).longValue();

			SQLQuery query2 = session.createSQLQuery("select setval('opd.encounter_master_seq'," + (id-1) + ")");
			query2.uniqueResult();

			return id;
		}catch (HibernateException he) {
			logger.error("Exection", he);
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	@Override
	public List<EncounterMasterDto> getEncounterDetailsByPatientDoctorVisitTypeId(EncounterMasterDto encounterMasterDto) throws ApplicationException {
		try {
			List<EncounterMasterDto> encounterMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ENCOUNTER_DETAILS_FOR_VISIT_TYPE_SERVICE_BY_DOCTOR_AND_PATIENT_ID")
					.setInteger("orgId", encounterMasterDto.getOrganizationId())
					.setInteger("unitId", encounterMasterDto.getUnitId())
					.setInteger("doctorId", encounterMasterDto.getDoctorId())
					.setInteger("patientId", encounterMasterDto.getPatientId())
					.setResultTransformer(Transformers.aliasToBean(EncounterMasterDto.class)).list();
			return  encounterMasterDtosList;
		}catch (HibernateException he) {
			logger.error("Exection", he);
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Response getEncounterDetails(EncounterMasterDto encounterMasterDto)
			throws ApplicationException {
		try {
			List<EncounterMasterDto> encounterMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ENCOUNTER_DETAILS")
					.setInteger("orgId", encounterMasterDto.getOrganizationId())
					.setInteger("unitId", encounterMasterDto.getUnitId())
					.setInteger("doctorId", encounterMasterDto.getDoctorId())
					.setInteger("patientId", encounterMasterDto.getPatientId())
					.setResultTransformer(Transformers.aliasToBean(EncounterMasterDto.class)).list();
			return new Response(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, encounterMasterDtosList, null);
		}catch (HibernateException he) {
			logger.error("Exection", he);
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getListOfEncounterMasterDao(SlotMasterDto slotMasterDto) throws ApplicationException {
		try {
			List<SlotMasterDto> encounterMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_LIST_ENCOUNTER_MASTER")
					.setInteger("orgId", slotMasterDto.getOrganizationId())
					.setInteger("unitId", slotMasterDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(SlotMasterDto.class)).list();
			return new Response(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, encounterMasterDtosList, null);
		}catch (HibernateException he) {
			logger.error("Exection", he);
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getSearchedListEncounterMaster(SlotMasterDto slotMasterDto)throws ApplicationException {
		try {
			StringBuilder stringQuery=new StringBuilder(sessionFactory.getCurrentSession().getNamedQuery("GET_SEARCH_ENCOUNTERED_LIST").getQueryString().toString());

			/*String quer = this.getFinalQueryAfterAppendConditions(stringQuery,slotMasterDto).toString();
			System.out.println("query : "+quer);*/
			SQLQuery query=sessionFactory.getCurrentSession().createSQLQuery((this.getFinalQueryAfterAppendConditions(stringQuery,slotMasterDto)).toString());
				
			List<SlotMasterDto> slotMasterDtosList = query
				.setInteger("orgId", slotMasterDto.getOrganizationId())
				.setInteger("unitId", slotMasterDto.getUnitId())
				.setResultTransformer(Transformers.aliasToBean(SlotMasterDto.class)).list();
			Integer count=slotMasterDtosList.size();
			return new Response(SUCCESS, null, null, slotMasterDtosList, count);
			
		} catch (HibernateException he) {
			he.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	
	private StringBuilder getFinalQueryAfterAppendConditions(StringBuilder namedQuery, SlotMasterDto slotMasterDto) {
		try {
				
			/*if (slotMasterDto.getAppointmentDate() != null)
				namedQuery.append(" AND sm.appointment_date='" + GlobalCommonDateUtils.getDate(slotMasterDto.getAppointmentDate(), "yyyy-M-dd") + "' ");*/

			if (slotMasterDto.getPatientId() != null && slotMasterDto.getPatientId() > 0)
				namedQuery.append(" AND enMst.patient_id = " + slotMasterDto.getPatientId());

			if (slotMasterDto.getFromDate() != null)
				namedQuery.append(" AND enMst.encounter_date >='" + slotMasterDto.getFromDate()+"' ");

			if (slotMasterDto.getToDate() != null)
				namedQuery.append(" AND enMst.encounter_date <='"	+ slotMasterDto.getToDate() + "' ");

			if (slotMasterDto.getSpecialityId() != null && slotMasterDto.getSpecialityId() > 0)
				namedQuery.append(" AND sm.speciality_id =" + slotMasterDto.getSpecialityId());

			if (slotMasterDto.getDoctorId() != null && slotMasterDto.getDoctorId() > 0)
				namedQuery.append(" AND enMst.doctor_id =" + slotMasterDto.getDoctorId());

			if ( slotMasterDto.getNursingStationId() != null && slotMasterDto.getNursingStationId() > 0)
				namedQuery.append(" AND nurStatClinMapp.nursing_station_id = " + slotMasterDto.getNursingStationId());

			/*if (slotMasterDto.getModalityId() > 0)
				namedQuery.append(" AND sm.modality_id = " + slotMasterDto.getModalityId());

			if (slotMasterDto.getVisitTypeId() > 0)
				namedQuery.append(" AND am.visit_type_id =" + slotMasterDto.getVisitTypeId());*/
			
			if (slotMasterDto.getVisitTypeId() != null && slotMasterDto.getVisitTypeId() > 0)
				namedQuery.append(" AND enMst.clinic_id =" + slotMasterDto.getClinicId()); 
			
			if ( slotMasterDto.getStatusId() > 0)
				namedQuery.append(" AND enMst.status_id =" + slotMasterDto.getStatusId());

			namedQuery.append(" ORDER BY enMst.encounter_id ");

			return namedQuery;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return namedQuery;
	}

	@Override
	public Response getListOfAppointmentStatus(AppointmentStatusMasterDto appointmentStatusMasterDto)
			throws ApplicationException {
		try {
			List<AppointmentStatusMasterDto> appointmentStatusMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_LIST_APPOINTMENT_STATUS_MASTER")
					.setInteger("organizationId", appointmentStatusMasterDto.getOrganizationId())
					.setInteger("unitId", appointmentStatusMasterDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(AppointmentStatusMasterDto.class)).list();
			return new Response(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, appointmentStatusMasterDtoList, null);
		}catch (HibernateException he) {
			logger.error("Exection", he);
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getEncounterByIdForSync(int encounterId)throws ApplicationException {
		try{
			EncounterSyncDto encounterSyncDto = (EncounterSyncDto) sessionFactory.getCurrentSession().getNamedQuery("GET_ENCOUNTER_BY_ID_FOR_GLOBAL_SCHEDULING_SYNC").setInteger("encounterId", encounterId).setResultTransformer(Transformers.aliasToBean(EncounterSyncDto.class)).uniqueResult();
			return new Response<>(SUCCESS, null, null, null, encounterSyncDto);
			
		}catch(HibernateException he){
			he.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getListOfEncountersByPatientId(EncounterMasterDto encounterMasterDto) throws ApplicationException {
		try {
			List<AppointmentStatusMasterDto> appointmentStatusMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_LIST_OF_ENCOUNTERS_BY_PATIENT_ID")
					.setInteger("orgId", encounterMasterDto.getOrganizationId())
					.setInteger("unitId", encounterMasterDto.getUnitId())
					.setInteger("patientId", encounterMasterDto.getPatientId())
					.setResultTransformer(Transformers.aliasToBean(EncounterMasterDto.class)).list();
			return new Response(SUCCESS, SUCCESS_CODE, null, appointmentStatusMasterDtoList, null);
		}catch (HibernateException he) {
			logger.error("Exection", he);
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getAutoRenderedServicesByPatient(EncounterMasterDto encounterMasterDto)
			throws ApplicationException {
		try {
			List<ServiceMasterDto> listServiceMasterDto = sessionFactory.getCurrentSession().getNamedQuery("GET_AUTO_RENDERED_SERVICES_BY_PATIENT")
					.setInteger("patientId", encounterMasterDto.getPatientId())
					.setInteger("unitId", encounterMasterDto.getUnitId())
					.setInteger("orgId", encounterMasterDto.getOrganizationId())
					.setResultTransformer(Transformers.aliasToBean(ServiceMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, listServiceMasterDto, null);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}
	

	@Override
	public UnitServiceTariffMasterDto getBasePriceByServiceTariffMaster(
			UnitServiceTariffMasterDto unitServiceTariffMasterDto) throws ApplicationException {
	try {
				
				Query query = sessionFactory.getCurrentSession().createQuery("SELECT ustm.unitServiceTriffId as unitServiceTriffId, "
				+ "ustm.rate as rate " 
				+ "FROM UnitServiceTariffMaster ustm "
				+ "WHERE ustm.serviceId=:serviceId "
				+ "AND ustm.tariffId=:tariffId "
				+ "AND ustm.paymentEntitlementId=:paymentEntitlementId "
				+ "AND ustm.visitTypeId=:visitTypeId "
				+ "AND ustm.patientTypeId=:patientTypeId "
				+ "AND ustm.billingBedCategoryId=:billingBedCategoryId "
				+ "AND ustm.organizationId=:organizationId "
				+ "AND ustm.unitId=:unitId "
				+ "AND ustm.effectiveDate < '"+unitServiceTariffMasterDto.getOrderDate()+"'"
				);
				
				UnitServiceTariffMasterDto unitServiceTariffMasterDtos = (UnitServiceTariffMasterDto)query
						.setInteger("serviceId", unitServiceTariffMasterDto.getServiceId())
						.setInteger("tariffId", unitServiceTariffMasterDto.getTariffId())
						.setInteger("paymentEntitlementId", unitServiceTariffMasterDto.getPaymentEntitlementId())
						.setInteger("visitTypeId", unitServiceTariffMasterDto.getVisitTypeId())
						.setInteger("patientTypeId", unitServiceTariffMasterDto.getPatientTypeId())
						.setInteger("billingBedCategoryId", unitServiceTariffMasterDto.getBillingBedCategoryId())
						.setInteger("organizationId", unitServiceTariffMasterDto.getOrganizationId())
						.setInteger("unitId", unitServiceTariffMasterDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(UnitServiceTariffMasterDto.class)).uniqueResult();
				
				return unitServiceTariffMasterDtos;
	
			} catch (HibernateException he) {
				he.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
		} return null;
		
	}

	@Override
	public Response updateAutorenderServices(OrderDetailsMasterDto orderDetailsMasterDto) throws ApplicationException {
		try {
			
			sessionFactory.getCurrentSession().createQuery("UPDATE OrderMaster set encounterId="+orderDetailsMasterDto.getEncounterId()+" "
					+ "WHERE orderId="+orderDetailsMasterDto.getOrderId()+" AND encounterId is null ").executeUpdate();
			
			
			Query query = sessionFactory.getCurrentSession().createQuery("UPDATE OrderDetailsMaster orderDtls SET "
					+ "orderDtls.serviceAmt=:serviceAmt, "
					+ "orderDtls.ordConcession=:ordConcession, "
					+ "orderDtls.ordDiscount=:ordDiscount, "
					+ "orderDtls.tariffId=:tariffId, "
					+ "orderDtls.billingClassId=:billingClassId, "
					+ "orderDtls.orderEmergencyFlag=:orderEmergencyFlag, "
					+ "orderDtls.serviceRendered=:serviceRendered, "
					+ "orderDtls.serviceChargeable=:serviceChargeable, "
					+ "orderDtls.serviceRenderingDeptId=:serviceRenderingDeptId, "
					+ "orderDtls.serviceIsBilled=:serviceIsBilled, "
					+ "orderDtls.isDrug=:isDrug, "
					+ "orderDtls.taxId=:taxId, "
					+ "orderDtls.taxPer=:taxPer, "
					+ "orderDtls.ordTotalAmt=:orderTotalAmt, "
					+ "orderDtls.taxAmount=:taxAmount, "
					+ "orderDtls.netAmount=:netAmount, "
					+ "orderDtls.selfPayable=:selfPayable, "
					+ "orderDtls.payeeId=:payeeId, "
					+ "orderDtls.creditPayable=:creditPayable "
					+ "WHERE orderDtls.orderDetailsId=:orderDetailsId "
			);
			
				Integer res =	query
					.setInteger("orderDetailsId",orderDetailsMasterDto.getOrderDetailsId())
					.setBigDecimal("serviceAmt", orderDetailsMasterDto.getServiceAmt())
					.setBigDecimal("ordConcession",orderDetailsMasterDto.getOrdConcession())
					.setBigDecimal("ordDiscount",orderDetailsMasterDto.getOrdDiscount())
					.setInteger("tariffId",orderDetailsMasterDto.getTariffId())
					.setInteger("billingClassId", orderDetailsMasterDto.getBillingClassId())
					.setCharacter("orderEmergencyFlag", orderDetailsMasterDto.getOrderEmergencyFlag())
					.setInteger("serviceRendered", orderDetailsMasterDto.getServiceRendered())
					.setInteger("serviceChargeable", orderDetailsMasterDto.getServiceChargeable())
					.setInteger("serviceRenderingDeptId", orderDetailsMasterDto.getServiceRenderingDeptId())
					.setInteger("serviceIsBilled", orderDetailsMasterDto.getServiceIsBilled())
					.setCharacter("isDrug", orderDetailsMasterDto.getIsDrug())
					.setInteger("taxId", orderDetailsMasterDto.getTaxId())
					.setBigDecimal("taxPer", orderDetailsMasterDto.getTaxPer())
					.setBigDecimal("orderTotalAmt",orderDetailsMasterDto.getOrdTotalAmt())
					.setBigDecimal("taxAmount",orderDetailsMasterDto.getTaxAmount())
					.setBigDecimal("netAmount", orderDetailsMasterDto.getNetAmount())
					.setBigDecimal("selfPayable", orderDetailsMasterDto.getSelfPayable())
					.setInteger("payeeId", orderDetailsMasterDto.getPayeeId())
					.setBigDecimal("creditPayable", orderDetailsMasterDto.getCreditPayable())
				.executeUpdate();
			
				if(res==1)
					return new Response(SUCCESS, null, null, null, null);
				else
					return new Response(ERROR, null, null, null, null);
				

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
	} return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	
	}

	@Override
	public Tax getTaxPercentageByServiceId(Integer serviceId) {
		try {
			
			SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("SELECT tax.id as \"id\"," 
					+	"tax.tax_percentage as \"taxPercentage\" "
					+	"FROM public.m_tax tax "
					+	"INNER JOIN service.t_unit_service_master_details usmd on usmd.gst_type_id = tax.id "
					+	"WHERE usmd.service_master_id = "+serviceId
	
			);
			
			Tax tax = (Tax)query
				.setResultTransformer(Transformers.aliasToBean(Tax.class)).uniqueResult();
			
			return tax;

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
	} return null;
	}
	
}
