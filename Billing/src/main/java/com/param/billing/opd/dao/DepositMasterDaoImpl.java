package com.param.billing.opd.dao;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.param.billing.exception.BillingException;
import com.param.billing.global.dto.DepositSearchReqDto;
import com.param.billing.global.dto.DepositSearchResDto;
import com.param.billing.global.dto.VisitTypeEncounterReqDto;
import com.param.billing.global.dto.VisitTypeEncounterResDto;
import com.param.billing.global.model.DepositMaster;
import com.param.billing.master.dto.DepositMasterDto;
import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;

import in.param.common.dao.GenericDao;

@Repository
@SuppressWarnings({"rawtypes","unchecked"})
public class DepositMasterDaoImpl extends GenericDao<DepositMaster, Integer> implements IDepositMasterDao, ICommonConstants{

	public DepositMasterDaoImpl() {
		super(DepositMaster.class);
	}

	@Override
	public Response saveDeposit(DepositMasterDto dto) throws BillingException {
		try{
			DepositMaster master = new DepositMaster();
			master.setAdmNoteId(dto.getAdmNoteId());
			master.setAvailableDeposit(dto.getAvailableDeposit());
			master.setConsumedDeposit(dto.getConsumedDeposit());
			master.setCreatedBy(dto.getCreatedBy());
			master.setCreatedDate(dto.getCreatedDate() != null && !dto.getCreatedDate().isEmpty() ? GlobalCommonDateUtils.getDate(dto.getCreatedDate(), "dd-M-yyyy HH:mm:ss") : new Date());
			master.setDepositAmount(dto.getDepositAmount());
			master.setDepositDate(dto.getDepositDate() != null && !dto.getDepositDate().isEmpty() ? GlobalCommonDateUtils.getDate(dto.getDepositDate(), "dd-M-yyyy HH:mm:ss") : new Date());
			master.setDepositNo(dto.getDepositNo());
			master.setDepositTypeId(dto.getDepositTypeId());
			master.setIdentificationNo(dto.getIdentificationNo());
			master.setOrgId(dto.getOrgId());
			master.setPatientId(dto.getPatientId());
			master.setPayeeId(dto.getPayeeId());
			master.setReceivedFrom(dto.getReceivedFrom());
			master.setRemarks(dto.getRemarks());
			master.setStatus(ACTIVE);
			master.setUnitId(dto.getUnitId());
			master.setUpdatedBy(dto.getUpdatedBy());
			master.setUpdatedDate(dto.getUpdatedDate() != null && !dto.getUpdatedDate().isEmpty() ? GlobalCommonDateUtils.getDate(dto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss") : new Date());
			master.setVisitAdmId(dto.getVisitAdmId());
			master.setVisitTypeId(dto.getVisitTypeId());
			master = save(master);
			return new Response<>(SUCCESS, SUCCESS_CODE, null, null, master);
		}catch(HibernateException he){
			he.printStackTrace();
			throw new BillingException(he.getMessage());
		}catch(Exception e){
			e.printStackTrace();
			throw new BillingException(e.getMessage());
		}
	}

	@Override
	public Long getCurrentId() throws BillingException {
		try{
			BigInteger id = (BigInteger)sessionFactory.getCurrentSession().getNamedQuery("GET_CURRNET_DEPOSIT_ID").uniqueResult();
			return id.longValue();
		}catch(HibernateException he){
			he.printStackTrace();
			throw new BillingException(he.getMessage());
		}catch(Exception e){
			e.printStackTrace();
			throw new BillingException(e.getMessage());
		}
	}

	@Override
	public Response searchDeposit(DepositSearchReqDto dto) throws BillingException {
		try{
			StringBuilder query = new StringBuilder(sessionFactory.getCurrentSession().getNamedQuery("DEPOSIT_DETAILS_SEARCH").getQueryString());
			query.append((dto.getFromDate() != null && dto.getToDate() != null && !dto.getFromDate().isEmpty() && !dto.getToDate().isEmpty()) ? " to_date(to_char(dept.deposit_date,'DD-MM-YYYY'),'DD-MM-YYYY') between to_date('"+dto.getFromDate()+"','DD-MM-YYYY') and to_date('"+dto.getToDate()+"','DD-MM-YYYY') " : " ");
			query.append((dto.getPatientName() != null && !dto.getPatientName().isEmpty()) ? " and ( trim(lower(pat.first_name)) like '%"+dto.getPatientName()+"%' or trim(lower(pat.middle_name)) like '%"+dto.getPatientName()+"%' or trim(lower(pat.last_name)) like '%"+dto.getPatientName()+"%' ) " : " ");
			query.append((dto.getUhidNumber() != null && !dto.getUhidNumber().isEmpty()) ? " and trim(lower(pat.uhid_number)) like '%"+dto.getUhidNumber()+"%' " : " ");
			query.append(dto.getDepositTypeId() != null ? " and dept.deposit_type_id = "+dto.getDepositTypeId() : " ");
			query.append(dto.getVisitTypeId() != null ? " and dept.visit_type_id = " + dto.getVisitTypeId() : " ");
			query.append(" order by dept.deposit_date desc ");
			List<DepositSearchResDto> listDepositSearchResDto = sessionFactory.getCurrentSession().createSQLQuery(query.toString()).setInteger("orgId", dto.getOrgId()).setInteger("unitId", dto.getUnitId()).setResultTransformer(Transformers.aliasToBean(DepositSearchResDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, listDepositSearchResDto, null);
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}
	

	@Override
	public Response getEncountersByPatIdForDeposit(VisitTypeEncounterReqDto reqDto) throws BillingException {
		try{
			List<VisitTypeEncounterResDto> listVisitTypeEncounterResDto = sessionFactory.getCurrentSession().getNamedQuery("GET_ENC_VISIT_NOS_BY_PATIENT_ID_FOR_DEPOSIT").setInteger("patientId", reqDto.getPatientId()).setInteger("orgId", reqDto.getOrgId()).setInteger("unitId", reqDto.getUnitId()).setResultTransformer(Transformers.aliasToBean(VisitTypeEncounterResDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, listVisitTypeEncounterResDto, null);
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public Response getAdmissionByPatVisitForDeposit(VisitTypeEncounterReqDto reqDto) throws BillingException {
		try{
			List<VisitTypeEncounterResDto> listVisitTypeEncounterResDto = sessionFactory.getCurrentSession().getNamedQuery("GET_ADM_VISIT_NOS_BY_PATIENT_ID_FOR_DEPOSIT").setInteger("visitTypeId", reqDto.getVisitTypeId()).setInteger("patientId", reqDto.getPatientId()).setInteger("orgId", reqDto.getOrgId()).setInteger("unitId", reqDto.getUnitId()).setResultTransformer(Transformers.aliasToBean(VisitTypeEncounterResDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, listVisitTypeEncounterResDto, null);
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public Response getAvailableDepositByPatient(int orgId, int unitId, int patientId) throws BillingException {
		try{
			List<DepositSearchResDto> listDepositSearchResDto = sessionFactory.getCurrentSession().getNamedQuery("GET_AVAILABLE_DEPOSIT_BY_PATIENT").setInteger("orgId", orgId).setInteger("unitId", unitId).setInteger("patientId", patientId).setResultTransformer(Transformers.aliasToBean(DepositSearchResDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, listDepositSearchResDto, null);
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}

	

}
