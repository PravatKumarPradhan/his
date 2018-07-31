package com.param.billing.unit.dao;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.billing.common.IError;
import com.param.billing.exception.ServiceException;
import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.CompanyMasterDto;
import com.param.service.dto.CommonDto;
import com.param.service.dto.MCompanyContractMasterDto;
import com.param.service.dto.ServiceForCompnayContarctReqDto;
import com.param.service.dto.ServiceForPackageResDto;
import com.param.service.global.model.MCompanyContractMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;


@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class CompanyContractDaoImpl extends GenericDao<MCompanyContractMaster, Integer> implements ICompanyContractDao,ICommonConstants, IError{

	final static Logger logger = Logger.getLogger(CompanyContractDaoImpl.class);
	
	@Autowired
	DozerBeanMapper mapper;
	
	@Autowired
	SessionFactory sessionFactory;
	
	public CompanyContractDaoImpl() {
		super(MCompanyContractMaster.class);
		// TODO Auto-generated constructor stub
	}


	@Override
	public Response getCompanyMasterList(CompanyMasterDto companyMasterDto) throws ApplicationException {
		List<CommonDto>  listCompanyMasterList = null; 
		try
		{
			listCompanyMasterList = (List<CommonDto>) sessionFactory.getCurrentSession()
					.createSQLQuery("SELECT "
							+"	insurance.insurance_id AS id, "
							+"	insurance.insurance_desc  AS name "
							+"FROM "
							+"	adt.t_insurance insurance "
							+"WHERE "
							+"	insurance.status = 'A' "
							+"	AND insurance.organization_id = :orgId "
							+"	AND insurance.unit_id = :orgUnitId ")
					.setInteger("orgId", companyMasterDto.getOrganizationId())
					.setInteger("orgUnitId", companyMasterDto.getUnitId())
                    .setResultTransformer(Transformers.aliasToBean(CommonDto.class)).list();					
			return new Response(SUCCESS, SUCCESS_CODE, SUCCESS_CODE, listCompanyMasterList, null);

		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}




	@Override
	public Response getAssociateCompanyMaster(CompanyMasterDto companyMasterDto) throws ApplicationException {
		List<CommonDto>  listAssociateCompanyMasterList = null; 
		try
		{
			listAssociateCompanyMasterList = (List<CommonDto>) sessionFactory.getCurrentSession()
					.createSQLQuery("SELECT "
							+"	companymst.company_id AS id, "
							+"	companymst.company_desc  AS name "
							+"FROM "
							+"	t_company_master  companymst "
							+"WHERE "
							+"	companymst.status = 'A' "
							+"	AND companymst.organization_id = :orgId "
							+"	AND companymst.unit_id = :orgUnitId ")
					.setInteger("orgId", companyMasterDto.getOrganizationId())
					.setInteger("orgUnitId", companyMasterDto.getUnitId())
                    .setResultTransformer(Transformers.aliasToBean(CommonDto.class)).list();					
			return new Response(SUCCESS, SUCCESS_CODE, SUCCESS_CODE, listAssociateCompanyMasterList, null);

		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	

	@Override
	public Response getGradeListByAssociateCompanyId(CompanyMasterDto companyMasterDto) throws ApplicationException {
		List<CommonDto>  listAssociateCompanyMasterList = null; 
		try
		{
			listAssociateCompanyMasterList = (List<CommonDto>) sessionFactory.getCurrentSession()
					.createSQLQuery("SELECT "
							+" grademst.contract_grade_master_id AS id, "
							+"	grademst.grade_desc AS name "
							+"FROM "
							+"	service.t_company_contarct_grade_mpper tcompany "
							+ " inner join service.m_contract_grade_master grademst on grademst.contract_grade_master_id = tcompany.contract_grade_master_id "
							+" WHERE "
							+" tcompany.org_id  = :orgId "
							+"	AND tcompany.org_unit_id = :orgUnitId "
							+ " GROUP BY "
							+ "   grademst.contract_grade_master_id ")
					.setInteger("orgId", companyMasterDto.getOrganizationId())
					.setInteger("orgUnitId", companyMasterDto.getUnitId())
                    .setResultTransformer(Transformers.aliasToBean(CommonDto.class)).list();					
			return new Response(SUCCESS, SUCCESS_CODE, SUCCESS_CODE, listAssociateCompanyMasterList, null);

		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@Override
	public Response getVisitTypeList(CompanyMasterDto companyMasterDto) throws ApplicationException {
		List<CommonDto>  listVisitType = null; 
		try
		{
			listVisitType = (List<CommonDto>) sessionFactory.getCurrentSession()
					.createSQLQuery("SELECT "
							+"	visittype.visit_type_id AS id, "
							+"	visittype.visit_type_name  AS name "
							+"FROM "
							+"	m_visit_type_master visittype "
							+"WHERE "
							+"	visittype.status = 'A' "
							+"	AND visittype.organization_id = :orgId LIMIT 4 ")
					.setInteger("orgId", companyMasterDto.getOrganizationId())
                    .setResultTransformer(Transformers.aliasToBean(CommonDto.class)).list();					
			return new Response(SUCCESS, SUCCESS_CODE, SUCCESS_CODE, listVisitType, null);

		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@Override
	public Response createCompanyContarct(MCompanyContractMasterDto masterDto)
			throws ApplicationException {
		try{
			MCompanyContractMaster mCompanyContractMaster = new MCompanyContractMaster();
			mCompanyContractMaster.setBillingBedCategoryId(masterDto.getBillingBedCategoryId());
			mCompanyContractMaster.setCreatedBy(masterDto.getCreatedBy());
			mCompanyContractMaster.setCreatedDate(new Date());
			mCompanyContractMaster.setUnitId(masterDto.getUnitId());
			mCompanyContractMaster.setOrgId(masterDto.getOrgId());
			mCompanyContractMaster.setUpdatedBy(masterDto.getUpdatedBy());
			//mCompanyContractMaster.setUpdatedDate(masterDto.getUpdatedDate() != null && !masterDto.getUpdatedDate().isEmpty() ? GlobalCommonDateUtils.getDate(masterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss") : new Date());
			mCompanyContractMaster.setValidityFrom(masterDto.getValidityFrom() != null && !masterDto.getValidityFrom().isEmpty() ? GlobalCommonDateUtils.getDate(masterDto.getValidityFrom(), "dd-M-yyyy") : new Date());
			mCompanyContractMaster.setValidityTo(masterDto.getValidityTo() != null && !masterDto.getValidityTo().isEmpty() ? GlobalCommonDateUtils.getDate(masterDto.getValidityTo(), "dd-M-yyyy") : new Date());
			mCompanyContractMaster.setVisitTypeId(masterDto.getVisitTypeId());
			mCompanyContractMaster.setPatientTypeId(1);
			mCompanyContractMaster.setAssociateCompanyId(masterDto.getAssociateCompanyId());
			mCompanyContractMaster.setBillingBedCategoryId(masterDto.getBillingBedCategoryId());
			mCompanyContractMaster.setCompanyId(masterDto.getCompanyId());
			mCompanyContractMaster.setCoSharePercentage(masterDto.getCoSharePercentage());
			mCompanyContractMaster.setContractName(masterDto.getContractName());
			mCompanyContractMaster.setGradeId(masterDto.getGradeId());
			mCompanyContractMaster.setIsTariffRateApplicable(masterDto.getIsTariffRateApplicable());
			mCompanyContractMaster.setTariffId(masterDto.getTariffId());
			mCompanyContractMaster.setPaymentEntitlementId(masterDto.getPaymentEntitlementId());
			mCompanyContractMaster = save(mCompanyContractMaster);
			return new Response<>(SUCCESS, SUCCESS_CODE, null, null, mCompanyContractMaster);
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public Response searchCompanyContract(ServiceForCompnayContarctReqDto reqDto) throws ServiceException {
		try{
			   StringBuilder query = new StringBuilder(sessionFactory.getCurrentSession().getNamedQuery("SEARCH_FOR_COMPANY_CONTRACT").getQueryString());
				query.append(reqDto.getTariffId() != null ? " and tariffmst.tariff_id ="+reqDto.getTariffId() : " ");
				query.append(reqDto.getPaymentEntitlementId() != null ? " and paymst.payment_entitlement_id ="+reqDto.getPaymentEntitlementId() : " ");
				query.append(reqDto.getCompanyId()!= null ? " and insurance.insurance_id =" + reqDto.getCompanyId() : " ");
				query.append(reqDto.getAssociateCompanyId() != null ? " and assocompany.company_id  =" + reqDto.getAssociateCompanyId() : " ");
			List<ServiceForCompnayContarctReqDto> listServiceForCompnayContarctResDto = sessionFactory.getCurrentSession().createSQLQuery(query.toString()).setInteger("orgId", reqDto.getOrgId()).setInteger("orgUnitId", reqDto.getUnitId()).setResultTransformer(Transformers.aliasToBean(ServiceForCompnayContarctReqDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, listServiceForCompnayContarctResDto, null);
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}



	
}
