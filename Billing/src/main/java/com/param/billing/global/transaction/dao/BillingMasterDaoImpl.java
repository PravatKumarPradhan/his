package com.param.billing.global.transaction.dao;

import java.util.Date;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.billing.common.IError;
import com.param.billing.exception.BillingException;
import com.param.billing.global.dto.BillListReqDto;
import com.param.billing.global.dto.BillListResDto;
import com.param.billing.global.transaction.dto.BillDetailsByBillIdDto;
import com.param.billing.global.transaction.dto.BillingMasterDto;
import com.param.billing.global.transaction.dto.DiscountTypeDto;
import com.param.billing.global.transaction.dto.OrderDetailsByEncounterIdDto;
import com.param.billing.global.transaction.model.BillingMaster;
import com.param.entity.model.master.DiscountType;
import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.Response;
import com.param.global.dto.ServiceMasterDto;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class BillingMasterDaoImpl extends GenericDao<BillingMaster, Integer> implements IBillingMasterDao,com.param.billing.common.ICommonConstants,IError{

	public BillingMasterDaoImpl() {
		super(BillingMaster.class);
	}

	@Autowired
	private DozerBeanMapper mapper;
	
	@Override
	public Response saveBillingMaster(BillingMasterDto billingMasterDto) throws ApplicationException {
		try {
			BillingMaster billingMaster = mapper.map(billingMasterDto, BillingMaster.class,"billing_BillingMasterDto_to_BillingMaster");
			billingMaster = save(billingMaster);
			return new Response<>(SUCCESS, SUCCESS_CODE, null, null, billingMaster.getBillingMasterId());
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}

	@Override
	public Response getBillDetailsForPayment(BillingMasterDto billingMasterDto) throws ApplicationException {
		try {
			billingMasterDto = (BillingMasterDto) sessionFactory.getCurrentSession().getNamedQuery("GET_BILL_DETAILS_FOR_PAYMENT")
					.setInteger("billMasterId", billingMasterDto.getBillingMasterId())
					.setResultTransformer(Transformers.aliasToBean(BillingMasterDto.class)).uniqueResult();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, null, billingMasterDto);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}

	@Override
	public Response updateBillSummaryAmount(BillingMasterDto billingMasterDto) throws ApplicationException {
		try {
			String query=" UPDATE billing.m_billing_master SET net_amount="+ billingMasterDto.getNetAmount() +" , concession_amount="+ billingMasterDto.getConcessionAmount() +" , total_bill_amount="+ billingMasterDto.getTotalBillAmount() +" , " 
					+ "outstanding="+billingMasterDto.getOutstanding()+", bill_status_id="+billingMasterDto.getBillStatusId()+" WHERE billing_master_id="+billingMasterDto.getBillingMasterId();
			SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(query);
			sqlQuery.executeUpdate();
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, null);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}

	@Override
	public Response getBillMasterById(Integer billMasterId) throws ApplicationException {
		try {
			BillingMasterDto billingMasterDto = (BillingMasterDto) sessionFactory.getCurrentSession().getNamedQuery("GET_BILL_MASTER_BY_ID")
					.setInteger("billMasterId", billMasterId)
					.setResultTransformer(Transformers.aliasToBean(BillingMasterDto.class)).uniqueResult();
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, billingMasterDto);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}
	
	@Override
	public Response getAutoRenderedServicesByPatient(ServiceMasterDto serviceMasterDto) throws ApplicationException {
		try {
			List<ServiceMasterDto> listServiceMasterDto = sessionFactory.getCurrentSession().getNamedQuery("GET_AUTO_RENDERED_SERVICES_BY_PATIENT")
					.setInteger("patientId", serviceMasterDto.getPatientId())
					.setInteger("unitId", serviceMasterDto.getUnitId())
					.setInteger("orgId", serviceMasterDto.getOrganizationId())
					.setResultTransformer(Transformers.aliasToBean(ServiceMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, listServiceMasterDto, null);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}

	@Override
	public Response getBillsByFilterForBillList(BillListReqDto reqDto) throws BillingException {
		try{
			boolean dateFlag = false;
			Date fromDate = reqDto.getFromDate() != null && !reqDto.getFromDate().isEmpty() ? GlobalCommonDateUtils.getDate(reqDto.getFromDate(), "dd-M-yyyy") : null;
			Date toDate = reqDto.getToDate() != null && !reqDto.getToDate().isEmpty() ? GlobalCommonDateUtils.getDate(reqDto.getToDate(), "dd-M-yyyy") : null;
			StringBuilder query = new StringBuilder(sessionFactory.getCurrentSession().getNamedQuery("GET_BILL_DETAILS_FOR_BILL_LIST_BY_FILTER").getQueryString());
			dateFlag = (fromDate != null && toDate != null ? true : false);
			query.append(dateFlag ? " and to_date(bill.\"billDate\",'DD-MM-YYYY') between :fromDate and :toDate " : " " );
			query.append(reqDto.getVisitTypeId() != null ? " and bill.\"visitTypeId\" ="+reqDto.getVisitTypeId() : " ");
			query.append((reqDto.getBillNo() != null && !reqDto.getBillNo().isEmpty()) ? " and trim(lower(bill.\"billNumber\")) ='"+reqDto.getBillNo().toLowerCase().trim()+"'" : " ");
			query.append((reqDto.getVisitNo() != null && !reqDto.getVisitNo().isEmpty()) ? " and trim(lower(bill.\"visitNo\")) like '%"+ reqDto.getVisitNo().trim().toLowerCase() +"%'" : " ");
			query.append(reqDto.getBillStatusId() != null ? " and bill.\"billStatusId\" ="+reqDto.getBillStatusId() : " " );
			query.append(reqDto.getIsOtcReg() != null ? " and bill.\"isOtcReg\" = '"+reqDto.getIsOtcReg()+"'" : " ");
			query.append(reqDto.getPatientId() != null ? " and bill.\"patientId\"="+reqDto.getPatientId() : " ");
			query.append(" order by bill.\"billDate\" desc ");
			List<BillListResDto> listBills = null;
			if(dateFlag)
				listBills = sessionFactory.getCurrentSession().createSQLQuery(query.toString()).setInteger("orgId", reqDto.getOrgId()).setInteger("unitId", reqDto.getUnitId()).setTimestamp("fromDate", fromDate).setTimestamp("toDate", toDate).setResultTransformer(Transformers.aliasToBean(BillListResDto.class)).list();
			else
				listBills = sessionFactory.getCurrentSession().createSQLQuery(query.toString()).setInteger("orgId", reqDto.getOrgId()).setInteger("unitId", reqDto.getUnitId()).setResultTransformer(Transformers.aliasToBean(BillListResDto.class)).list();
		
			return new Response<>(SUCCESS, SUCCESS_CODE, null, listBills, null);
		}catch(Exception e){
			throw new BillingException(e.getMessage());
		}
	}

	@Override
	public Response getRenderedServicesByEncounterId(ServiceMasterDto serviceMasterDto) throws ApplicationException {
		try {
			List<ServiceMasterDto> listServiceMasterDto = sessionFactory.getCurrentSession().getNamedQuery("GET_RENDERED_SERVICES_BY_ENCOUNTER_ID")
					.setInteger("encounterId", serviceMasterDto.getEncounterId())
					.setResultTransformer(Transformers.aliasToBean(OrderDetailsByEncounterIdDto.class)).list();
			
			return new Response<>(SUCCESS, SUCCESS_CODE, null, listServiceMasterDto, null);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}

	@Override
	public Response getActiveDiscountCategories(Integer orgId,Integer unitId) throws ApplicationException {
		try {
			List<DiscountType> listServiceMasterDto = sessionFactory.getCurrentSession().createSQLQuery("SELECT id as \"id\", type as \"type\" FROM public.m_discount_type WHERE organization_id="+orgId+" AND unit_id="+unitId)
					.setResultTransformer(Transformers.aliasToBean(DiscountTypeDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, listServiceMasterDto, null);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}
	
	@Override
	public Response getBillDetailsByBillId(Integer billId) throws ApplicationException {
		try {
				List<BillDetailsByBillIdDto> detailsByBillIdDtosList = sessionFactory.getCurrentSession().getNamedQuery("GET_BILL_DETAILS_BY_BILL_ID")
						.setInteger("billId", billId)
						.setResultTransformer(Transformers.aliasToBean(BillDetailsByBillIdDto.class)).list();
			
			return new Response<>(SUCCESS, SUCCESS_CODE, null, detailsByBillIdDtosList, null);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}

}
