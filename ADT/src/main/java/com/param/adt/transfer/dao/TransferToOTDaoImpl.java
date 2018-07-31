package com.param.adt.transfer.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.adt.common.ADTCommonDateUtils;
import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.transfer.dto.TransferOTRequestDto;
import com.param.adt.transfer.model.TransferOTRequest;
import com.param.global.dto.AdmissionDto;
import com.param.global.dto.PatientRegistrationDto;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;
@Repository
@SuppressWarnings({"rawtypes","unchecked"})
public class TransferToOTDaoImpl extends GenericDao<TransferOTRequest, Integer>implements ITransferToOTDao,ICommonConstants{

	public TransferToOTDaoImpl() 
	{
		super(TransferOTRequest.class);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public Response getOTRequestListByAdmissionId(AdmissionDto admissionDto) throws ApplicationException {
		try{
			List<PatientRegistrationDto> patientRegistrationDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_OT_REQUEST_BY_ADMISSION_ID")
					.setInteger("orgId", admissionDto.getOrganizationId())
					.setInteger("unitId", admissionDto.getUnitId())
					.setInteger("admissionId", admissionDto.getAdmissionId())
					//.setInteger("transferTypeId", admissionDto.getTransferTypeId())
					//.setInteger("visitTypeId", admissionDto.getVisitTypeId())
					.setResultTransformer(Transformers.aliasToBean(AdmissionDto.class)).list();
			return new Response(SUCCESS, null, null, patientRegistrationDtosList, null);
			} catch (HibernateException he) {
				he.printStackTrace();

			} catch (Exception e) {
				e.printStackTrace();
			} 
			return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response saveOTTransferRequest(TransferOTRequestDto transferOTRequestDto) throws ApplicationException {
		try{
			Session session=sessionFactory.openSession();
			
			 //<----- Updating patient service order's is_mortality_transfer to 'Y'
			 Query query=session.createQuery("update PatientSurgeryOrder pso set pso.updatedBy="+transferOTRequestDto.getUpdatedBy()+",pso.updatedDate='"+ADTCommonDateUtils.getDate(transferOTRequestDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss")+"', pso.isOtTransfer='Y' where pso.patientSurgeryOrderId="+transferOTRequestDto.getPatientSurgeryOrderId());
			 query.executeUpdate();
			 //----->
			
			Transaction tr=session.beginTransaction();
			
			TransferOTRequest transferOTRequest = new TransferOTRequest();
				transferOTRequest.setOrganizationId(transferOTRequestDto.getOrganizationId());
				transferOTRequest.setUnitId(transferOTRequestDto.getUnitId());
				transferOTRequest.setAdmissionId(transferOTRequestDto.getAdmissionId());
				transferOTRequest.setSurgeryId(transferOTRequestDto.getSurgeryId());
				transferOTRequest.setSurgeryTime(transferOTRequestDto.getSurgeryTime());
				transferOTRequest.setTransferTime(transferOTRequestDto.getTransferTime());
				transferOTRequest.setIsRetained(transferOTRequestDto.getIsRetained());
				transferOTRequest.setDestinationId(transferOTRequestDto.getDestinationId());
				//transferOTRequest.setSchedularId(transferOTRequestDto.getSchedularId());
				transferOTRequest.setPatientSurgeryOrderId(transferOTRequestDto.getPatientSurgeryOrderId());
				transferOTRequest.setStatus('A');
				transferOTRequest.setCreatedDate(ADTCommonDateUtils.getDate(transferOTRequestDto.getCreatedDate(),"dd-M-yyyy HH:mm:ss"));
				transferOTRequest.setUpdatedDate(ADTCommonDateUtils.getDate(transferOTRequestDto.getCreatedDate(),"dd-M-yyyy HH:mm:ss"));
				transferOTRequest.setCreatedBy(transferOTRequestDto.getCreatedBy());
				transferOTRequest.setUpdatedBy(transferOTRequestDto.getUpdatedBy());
				transferOTRequest.setTransferStatusId(1);
			session.save(transferOTRequest);
			tr.commit();
			//----->
			 
			 return new Response(SUCCESS, null, "done !!", null, null);
			} catch (HibernateException he) {
				he.printStackTrace();

			} catch (Exception e) {
				e.printStackTrace();
			} 
			return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
		
	}

	@Override
	public Response getOTTransferRequestList(TransferOTRequestDto transferOTRequestDto)
			throws ApplicationException {
		try{
			List<TransferOTRequestDto> patientRegistrationDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_OT_TRANSFER_REQUEST_LIST")
					.setInteger("orgId", transferOTRequestDto.getOrganizationId())
					.setInteger("unitId", transferOTRequestDto.getUnitId())
					.setInteger("admissionId", transferOTRequestDto.getAdmissionId())
					.setResultTransformer(Transformers.aliasToBean(TransferOTRequestDto.class)).list();
			return new Response(SUCCESS, null, null, patientRegistrationDtosList, null);
			} catch (HibernateException he) {
				he.printStackTrace();

			} catch (Exception e) {
				e.printStackTrace();
			} 
			return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getOTRequestList(AdmissionDto admissionDto) throws ApplicationException {
		try{
			List<AdmissionDto> patientRegistrationDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_OT_REQUEST_LIST")
					.setInteger("orgId", admissionDto.getOrganizationId())
					.setInteger("unitId", admissionDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(AdmissionDto.class)).list();
			return new Response(SUCCESS, null, null, patientRegistrationDtosList, null);
			} catch (HibernateException he) {
				he.printStackTrace();

			} catch (Exception e) {
				e.printStackTrace();
			} 
			return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getOTTransferRequestListAdmissionId(TransferOTRequestDto transferOTRequestDto)
			throws ApplicationException {
		try{
			List<AdmissionDto> patientRegistrationDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_OT_REQUEST_LIST_BY_ADMISSION_ID")
					.setInteger("orgId", transferOTRequestDto.getOrganizationId())
					.setInteger("unitId", transferOTRequestDto.getUnitId())
					.setInteger("admissionId", transferOTRequestDto.getAdmissionId())
					.setResultTransformer(Transformers.aliasToBean(AdmissionDto.class)).list();
			return new Response(SUCCESS, null, null, patientRegistrationDtosList, null);
			} catch (HibernateException he) {
				he.printStackTrace();

			} catch (Exception e) {
				e.printStackTrace();
			} 
			return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

}
