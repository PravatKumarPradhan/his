package com.param.lis.transaction.dao;

import java.math.BigInteger;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.param.entity.lis.transaction.LabPatientArrivalMapperMaster;
import com.param.entity.lis.transaction.LabSampleDetailsMaster;
import com.param.entity.lis.transaction.PatientArrivalMasterId;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.ITransactionConstants;
import com.param.lis.global.common.Response;
import com.param.lis.transaction.dto.LabCommonDto;
import com.param.lis.transaction.dto.PatientArrivalDto;
import com.param.lis.transaction.dto.PatientArrivalMapperMasterDto;
import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings(
{ "rawtypes", "unchecked" })
public class PatientArrivalDaoImpl extends  GenericDao<LabPatientArrivalMapperMaster, Integer> implements IPatientArrivalDao, ICommonConstants, IError,ITransactionConstants{
	
  public PatientArrivalDaoImpl() {
	super(LabPatientArrivalMapperMaster.class);
	}

	@Autowired
	private DozerBeanMapper mapper;

    final static Logger logger = Logger.getLogger(PatientArrivalDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Response patientArrival(LabCommonDto labCommonDto)
			throws ApplicationException {
		
		List<PatientArrivalDto>  listPatientArrivalDto  = null; 
		try
		{
			listPatientArrivalDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PATENT_ARRIVAL_LIST")
					.setInteger("orgId", labCommonDto.getOrgId())
					.setInteger("orgUnitId", labCommonDto.getOrgUnitId())
					.setCharacter("isOutSource", IS_OUT_SOURCE)
					.setInteger("statusId", labCommonDto.getStatusId())
					.setInteger("deptId", labCommonDto.getDeptId())
					.setFirstResult(labCommonDto.getOffset() != null ? labCommonDto.getOffset()  : 0)
					.setMaxResults(labCommonDto.getNoOfRecordsPerPage() != null ? labCommonDto.getNoOfRecordsPerPage() : 10)
					.setResultTransformer(Transformers.aliasToBean(PatientArrivalDto.class)).list();
			
			if(listPatientArrivalDto.isEmpty())
			{
				return new Response(SUCCESS, SUCCESS_200,null, null, null);
				
			}
			else{
				return new Response(SUCCESS, SUCCESS_200, null, listPatientArrivalDto, null);
			}
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, PATIENT_NOT_ARRVIAL, null, null);
		}
		
	
	}

	@Override
	public Response patientArrivalList(LabCommonDto labCommonDto)
			throws ApplicationException {
		try
		{
			BigInteger patientArrivalTotalRecordCount = (BigInteger) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PATENT_ARRIVAL_COUNT")
					.setInteger("orgId", labCommonDto.getOrgId())
					.setInteger("orgUnitId", labCommonDto.getOrgUnitId())
					.setInteger("statusId", labCommonDto.getStatusId())
					.setInteger("deptId", labCommonDto.getDeptId())
					.setCharacter("isOutSource", IS_OUT_SOURCE).uniqueResult();
			
					//.setCharacter("currentStatus",REJECT).uniqueResult();
			if (patientArrivalTotalRecordCount!=null && patientArrivalTotalRecordCount.compareTo(BigInteger.ZERO) == 1)
			{
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, patientArrivalTotalRecordCount);
			} else
			{
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, BigInteger.ZERO);
			}

		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response patientArrivalMapperMaster(
			List<PatientArrivalMapperMasterDto> listPatientArrivalMapperMasterDto)
			throws ApplicationException {
		
		try
		{
			LabPatientArrivalMapperMaster labPatientArrivalMapperMst =null;
			for (Iterator iterator = listPatientArrivalMapperMasterDto.iterator(); iterator.hasNext();)
			{	
				PatientArrivalMapperMasterDto patientArrivalMapperMasterDto = (PatientArrivalMapperMasterDto) iterator.next();
				/*LabPatientArrivalMapperMaster labPatientArrivalMapperMaster = mapper.map(patientArrivalMapperMasterDto, LabPatientArrivalMapperMaster.class,
						"PatientArrivalMapperMasterDtoTOLabPatientArrivalMapperMaster");*/
				
				labPatientArrivalMapperMst = new LabPatientArrivalMapperMaster();
				labPatientArrivalMapperMst.setCollectionTime(patientArrivalMapperMasterDto.getCollectionTime());
				labPatientArrivalMapperMst.setCreatedBy(patientArrivalMapperMasterDto.getCreatedBy());
				labPatientArrivalMapperMst.setCreatedDate(new Date().getTime());
				labPatientArrivalMapperMst.setDeptId(patientArrivalMapperMasterDto.getDeptId());
				labPatientArrivalMapperMst.setDoctorId(patientArrivalMapperMasterDto.getDoctorId());
				labPatientArrivalMapperMst.setOrgId(patientArrivalMapperMasterDto.getOrgId());
				labPatientArrivalMapperMst.setOrgUnitId(patientArrivalMapperMasterDto.getOrgUnitId());
				labPatientArrivalMapperMst.setPendingCount(patientArrivalMapperMasterDto.getPendingCount());
				labPatientArrivalMapperMst.setTestId(patientArrivalMapperMasterDto.getTestId());
				
				PatientArrivalMasterId patientArrivalMasterId= new PatientArrivalMasterId();
				patientArrivalMasterId.setLabSmpDtlsId(patientArrivalMapperMasterDto.getPatientArrivalMasterId().getLabSmpDtlsId());
				patientArrivalMasterId.setLabSmpMstId(patientArrivalMapperMasterDto.getPatientArrivalMasterId().getLabSmpMstId());
				patientArrivalMasterId.setOrderId(patientArrivalMapperMasterDto.getPatientArrivalMasterId().getOrderId());
				patientArrivalMasterId.setPatientId(patientArrivalMapperMasterDto.getPatientArrivalMasterId().getPatientId());
				patientArrivalMasterId.setStatusId(patientArrivalMapperMasterDto.getPatientArrivalMasterId().getStatusId());
				labPatientArrivalMapperMst.setPatientArrivalMasterId(patientArrivalMasterId);
				
				
				labPatientArrivalMapperMst.setCreatedDate(new Date().getTime());
				labPatientArrivalMapperMst.setCollectionTime(patientArrivalMapperMasterDto.getCollectionTime());
				labPatientArrivalMapperMst = save(labPatientArrivalMapperMst);
			}
			
			if (labPatientArrivalMapperMst != null)
			{
				return new Response(SUCCESS, SUCCESS_200, PATIENT_ARRIVAL_SUCC, null, null);
			} else
			{
				return new Response(SUCCESS, SUCCESS_200, PATIENT_ARRIVAL_FAIL, null, null);
			}
			
		}catch(HibernateException exception){
			logger.error("Exection", exception);
		} catch (Exception e)
		{
			logger.error("Exection", e);
		}
		return new Response(ERROR, ERR_500, PATIENT_ARRIVAL_FAIL, null, null);
		
	}

	@Override
	public Response patientNotArrivalData(
			LabSampleDetailsMaster labSampleDetailsMaster)
			throws ApplicationException {
		try
		{
			LabPatientArrivalMapperMaster labPatientArrivalMapperMaster= new LabPatientArrivalMapperMaster();
			PatientArrivalMasterId PatientArrivalMasterId = new PatientArrivalMasterId();
			
			labPatientArrivalMapperMaster.setCreatedBy(labSampleDetailsMaster.getCreatedBy());
			labPatientArrivalMapperMaster.setTestId(labSampleDetailsMaster.getTestId());
			labPatientArrivalMapperMaster.setDeptId(labSampleDetailsMaster.getDeptId());
			labPatientArrivalMapperMaster.setPendingCount(labSampleDetailsMaster.getSamplePendingCount());
			labPatientArrivalMapperMaster.setOrgId(labSampleDetailsMaster.getOrgId());
			labPatientArrivalMapperMaster.setOrgUnitId(labSampleDetailsMaster.getOrgUnitId());
			labPatientArrivalMapperMaster.setDoctorId(labSampleDetailsMaster.getDoctorId());
			labPatientArrivalMapperMaster.setCreatedDate(new Date().getTime());
			labPatientArrivalMapperMaster.setOrderDetailsId(labSampleDetailsMaster.getOrderDetailsId());
			PatientArrivalMasterId.setPatientId(labSampleDetailsMaster.getPatientId());
			PatientArrivalMasterId.setOrderId(labSampleDetailsMaster.getOrderId());
			PatientArrivalMasterId.setLabSmpDtlsId(Long.valueOf(labSampleDetailsMaster.getLabSampleDtlsId()));
			PatientArrivalMasterId.setStatusId(PATIRNT_NOT_ARRIVAL);
			PatientArrivalMasterId.setLabSmpMstId(labSampleDetailsMaster.getLabSampleId());
			labPatientArrivalMapperMaster.setPatientArrivalMasterId(PatientArrivalMasterId);
			LabPatientArrivalMapperMaster labPatientArrivalMapperMst =save(labPatientArrivalMapperMaster);
			
			if (labPatientArrivalMapperMst != null)
			{
				return new Response(SUCCESS, SUCCESS_200, PATIENT_ARRIVAL_SUCC, null, null);
			} else
			{
				return new Response(SUCCESS, SUCCESS_200, PATIENT_ARRIVAL_FAIL, null, null);
			}
		}catch(HibernateException exception){
			logger.error("Exection", exception);
		} catch (Exception e)
		{
			logger.error("Exection", e);
		}
		return new Response(ERROR, ERR_500, PATIENT_ARRIVAL_FAIL, null, null);
	}

}
