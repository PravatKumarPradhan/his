package com.param.billing.unit.dao;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Repository;

import com.param.billing.global.transaction.model.UnitServiceTariffMaster;
import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.UnitServiceTariffMasterDto;

import in.param.common.dao.GenericDao;

@Repository
@SuppressWarnings({"unchecked","rawtypes"})
public class UnitServiceTariffMasterDaoImpl extends GenericDao<UnitServiceTariffMaster, Integer>implements IUnitServiceTariffMasterDao,ICommonConstants{

	public UnitServiceTariffMasterDaoImpl() {
		super(UnitServiceTariffMaster.class);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private DozerBeanMapper mapper;

	@Autowired
	private SessionFactory sessionFactory;

	
	
	@Override
	public Response saveUnitServiceTariffMaster(UnitServiceTariffMasterDto unitServiceTariffMasterDto)
			throws ApplicationContextException {
		try {
			UnitServiceTariffMaster serviceTariffMaster = mapper.map(unitServiceTariffMasterDto, UnitServiceTariffMaster.class, "UnitServiceTariffMasterDto_to_UnitServiceTariffMaster");
			serviceTariffMaster.setEffectiveDate(GlobalCommonDateUtils.getDate(unitServiceTariffMasterDto.getEffectiveDate(),"yyyy-M-dd HH:mm:ss"));
			serviceTariffMaster.setBillingBedCategoryId(unitServiceTariffMasterDto.getBillingBedCatId());
			save(serviceTariffMaster);
			return new Response(SUCCESS, null, null, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getUnitServiceTariffsUniqueness(UnitServiceTariffMasterDto unitServiceTariffMasterDto)
			throws ApplicationContextException {
		try {
			System.out.println("Date===>"+GlobalCommonDateUtils.getDate(unitServiceTariffMasterDto.getEffectiveDate(),"yyyy-M-dd HH:mm:ss"));
			System.out.println("Date###>"+GlobalCommonDateUtils.getDate(unitServiceTariffMasterDto.getEffectiveDate(),"dd-M-yyyy HH:mm:ss"));
			
			Query query= sessionFactory.getCurrentSession().createQuery("SELECT ustm.unitServiceTriffId as unitServiceTriffId "
			+ "FROM UnitServiceTariffMaster ustm "
			+ "WHERE ustm.serviceId="+unitServiceTariffMasterDto.getServiceId()+" "
			+ "AND ustm.tariffId="+unitServiceTariffMasterDto.getTariffId()+" "
			+ "AND ustm.paymentEntitlementId="+unitServiceTariffMasterDto.getPaymentEntitlementId()+" "
			+ "AND ustm.visitTypeId="+unitServiceTariffMasterDto.getVisitTypeId()+" "
			+ "AND ustm.patientTypeId="+unitServiceTariffMasterDto.getPatientTypeId()+" "
			+ "AND ustm.billingBedCategoryId="+unitServiceTariffMasterDto.getBillingBedCatId()+" "
			+ "AND ustm.organizationId="+unitServiceTariffMasterDto.getOrganizationId()+" "
			+ "AND ustm.unitId="+unitServiceTariffMasterDto.getUnitId()+" "
			+ "AND ustm.effectiveDate='"+unitServiceTariffMasterDto.getEffectiveDate()+"' ");
			
			List<UnitServiceTariffMasterDto> areaMasterDtoList = query
					/*.setInteger("serviceId", unitServiceTariffMasterDto.getServiceId())
					.setInteger("tariffId", unitServiceTariffMasterDto.getTariffId())
					.setInteger("paymentEntitlementId", unitServiceTariffMasterDto.getPaymentEntitlementId())
					.setInteger("visitTypeId", unitServiceTariffMasterDto.getVisitTypeId())
					.setInteger("patientTypeId", unitServiceTariffMasterDto.getPatientTypeId())
					.setInteger("billingBedCategoryId", unitServiceTariffMasterDto.getBillingBedCatId())
					.setInteger("organizationId", unitServiceTariffMasterDto.getOrganizationId())
					.setInteger("unitId", unitServiceTariffMasterDto.getUnitId())*/
					.setResultTransformer(Transformers.aliasToBean(UnitServiceTariffMasterDto.class)).list();
			
			return new Response(SUCCESS, null, null, areaMasterDtoList, null);

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} return new Response(ERROR, null, null, null, null);
	}

	@Override
	public UnitServiceTariffMasterDto getBasePriceByServiceTariffMaster(UnitServiceTariffMasterDto unitServiceTariffMasterDto)
			throws ApplicationContextException {
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
					//.setDate("orderDate", GlobalCommonDateUtils.getDate(,"yyyy-mm-dd"))
					.setResultTransformer(Transformers.aliasToBean(UnitServiceTariffMasterDto.class)).uniqueResult();
			
			return unitServiceTariffMasterDtos;

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} return null;
		
		
	}

	@Override
	public Response getBasePriceOfServicesFromTariffMasterByServiceList(
			UnitServiceTariffMasterDto unitServiceTariffMasterDto) throws ApplicationContextException {
try {
			
			Query query = sessionFactory.getCurrentSession().createQuery("SELECT ustm.unitServiceTriffId as unitServiceTriffId, "
			+ "ustm.rate as rate " 
			+ "FROM UnitServiceTariffMaster ustm "
			+ "WHERE ustm.organizationId=:organizationId "
			+ "AND ustm.unitId=:unitId "
			+ "AND ustm.tariffId=:tariffId "
			+ "AND ustm.paymentEntitlementId=:paymentEntitlementId "
			+ "AND ustm.visitTypeId=:visitTypeId "
			+ "AND ustm.patientTypeId=:patientTypeId "
			+ "AND ustm.billingBedCategoryId=:billingBedCategoryId "
			+ "AND ustm.effectiveDate < '"+unitServiceTariffMasterDto.getOrderDate()+"' "
			+ "AND ustm.serviceId IN (:serviceIdList) "
			);
			
			UnitServiceTariffMasterDto unitServiceTariffMasterDtos = (UnitServiceTariffMasterDto)query
					.setParameterList("serviceIdList", unitServiceTariffMasterDto.getServiceList())
					.setInteger("tariffId", unitServiceTariffMasterDto.getTariffId())
					.setInteger("paymentEntitlementId", unitServiceTariffMasterDto.getPaymentEntitlementId())
					.setInteger("visitTypeId", unitServiceTariffMasterDto.getVisitTypeId())
					.setInteger("patientTypeId", unitServiceTariffMasterDto.getPatientTypeId())
					.setInteger("billingBedCategoryId", unitServiceTariffMasterDto.getBillingBedCategoryId())
					.setInteger("organizationId", unitServiceTariffMasterDto.getOrganizationId())
					.setInteger("unitId", unitServiceTariffMasterDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(UnitServiceTariffMasterDto.class)).uniqueResult();
			
			

			return new Response(SUCCESS, null, null, null, unitServiceTariffMasterDtos);
			
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} return new Response(ERROR, null, null, null, null);
	}


}
