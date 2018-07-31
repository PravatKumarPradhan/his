package com.param.billing.global.transaction.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.param.billing.dto.TPackageConsumptionMasterDto;
import com.param.billing.exception.BillingException;
import com.param.billing.exception.ServiceException;
import com.param.billing.packages.model.TPackageConsumptionMaster;
import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.service.dto.MPackageMasterDto;
import com.param.service.dto.PackageConsumptionMasterResDto;
import com.param.service.dto.PackageConsumptionServiceDetailDto;

import in.param.common.dao.GenericDao;

@Repository
@SuppressWarnings({"rawtypes"})
public class TPackageConsumptionMasterDaoImpl extends GenericDao<TPackageConsumptionMaster, Integer> implements ITPackageConsumptionMasterDao, ICommonConstants{

	public TPackageConsumptionMasterDaoImpl() {
		super(TPackageConsumptionMaster.class);
	}

	@Override
	public Response saveTPackageConsumptionMaster(TPackageConsumptionMasterDto dto) throws BillingException {
		try{
			TPackageConsumptionMaster master = new TPackageConsumptionMaster();
			master.setAdmissionId(dto.getAdmissionId());
			master.setApplicableNoOfVisit(dto.getApplicableNoOfVisit());
			master.setBalanceDeposite(dto.getBalanceDeposite());
			master.setCancelledBy(dto.getCancelledBy());
			master.setCancelledDate(dto.getCancelledDate() != null && !dto.getCancelledDate().isEmpty() ? GlobalCommonDateUtils.getDate(dto.getCancelledDate(), "dd-M-yyyy") : null);
			master.setCancelledReasonId(dto.getCancelledReasonId());
			master.setCancelledRemark(dto.getCancelledRemark());
			master.setCoShareInPercentage(dto.getCoShareInPercentage());
			master.setCreatedBy(dto.getCreatedBy());
			master.setCreatedDate(dto.getCreatedDate() != null && !dto.getCreatedDate().isEmpty() ? GlobalCommonDateUtils.getDate(dto.getCreatedDate(), "dd-M-yyyy HH:mm:ss") : new Date());
			master.setDiscountinedBy(dto.getDiscountinedBy());
			master.setDiscountinedDate(dto.getDiscountinedDate() != null && !dto.getDiscountinedDate().isEmpty() ? GlobalCommonDateUtils.getDate(dto.getDiscountinedDate(), "dd-M-yyyy") : null);
			master.setDiscountinuedReasonId(dto.getDiscountinuedReasonId());
			master.setDiscountinuedRemark(dto.getDiscountinuedRemark());
			master.setEncounterId(dto.getEncounterId());
			master.setIsCancelled(dto.getIsCancelled());
			master.setIsDiscountinued(dto.getIsDiscountinued());
			master.setOrderDetailId(dto.getOrderDetailId());
			master.setOrganisationId(dto.getOrganisationId());
			master.setPackageId(dto.getPackageId());
			master.setPackageTypeId(dto.getPackageTypeId());
			master.setPatientId(dto.getPatientId());
			master.setPayeeId(dto.getPayeeId());
			master.setRate(dto.getRate());
			master.setStatus(ACTIVE);
			master.setTariffId(dto.getTariffId());
			master.setTotalDeposite(dto.getTotalDeposite());
			master.setUnitId(dto.getUnitId());
			master.setUpdatedBy(dto.getUpdatedBy());
			master.setUpdatedDate(dto.getUpdatedDate() != null && !dto.getUpdatedDate().isEmpty() ? GlobalCommonDateUtils.getDate(dto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss") : null);
			master.setValidityFrom(dto.getValidityFrom() != null && !dto.getValidityFrom().isEmpty() ? GlobalCommonDateUtils.getDate(dto.getValidityFrom(), "dd-M-yyyy") : null);
			master.setValidityTo(dto.getValidityTo() != null && !dto.getValidityTo().isEmpty() ? GlobalCommonDateUtils.getDate(dto.getValidityTo(), "dd-M-yyyy") : null);
			master.setVisitType(dto.getVisitType());
			master = save(master);
			return new Response<>(SUCCESS, SUCCESS_CODE, null, null, master);
		}catch(Exception e){
			e.printStackTrace();
			throw new BillingException(e.getMessage());
		}
	}

	@Override
	public Response getExistingActivePackageByPatientEncounter(
			Integer patientId, Integer encounterId, Integer visitTypeId, Integer organisationId, Integer unitId)
			throws ServiceException {
		try {
			List<PackageConsumptionMasterResDto> list = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_EXISITNG_PACKAGE_DETAILS_BY_ENCOUNTERID_VISITTYPE_PATIENTID")
					.setInteger("patientId", patientId)
					.setInteger("encounterId", encounterId)
					.setInteger("visitTypeId",visitTypeId)
					.setInteger("organisationId", organisationId)
					.setInteger("unitId", unitId)
					.setResultTransformer(Transformers.aliasToBean(PackageConsumptionMasterResDto.class))
					.list();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, list, null);
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public Response getPackageConsumptionServiceDetailsByPackageIdPatientId(
			Integer packageId, Integer patientId) throws ServiceException {
		try {
			List<PackageConsumptionServiceDetailDto> list = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PACAKAGE_CONSUMPTION_SERVICE_DETAILS_BY_PACKAGEID_PATIENTID")
					.setInteger("packageId",packageId)
					.setInteger("patientId", patientId)
					.setResultTransformer(Transformers.aliasToBean(PackageConsumptionServiceDetailDto.class))
					.list();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, list, null);
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	
}