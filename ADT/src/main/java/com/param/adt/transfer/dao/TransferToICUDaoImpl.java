package com.param.adt.transfer.dao;

import java.util.List;

import org.dozer.DozerBeanMapper;
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
import com.param.adt.master.global.dto.BedMasterDto;
import com.param.adt.transfer.dto.TransferDto;
import com.param.adt.transfer.model.Transfer;
import com.param.adt.transfer.model.TransferRequest;
import com.param.global.dto.DoctorMasterDto;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({"rawtypes","unchecked"})
public class TransferToICUDaoImpl extends GenericDao<TransferRequest, Integer> implements ITransferToICUDao,ICommonConstants {

	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	DozerBeanMapper mapper;
	
	public TransferToICUDaoImpl() {
		super(TransferRequest.class);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public Response getDoctorForICU(DoctorMasterDto doctorMasterDto) throws ApplicationException {
		try {
			List<TransferDto> pendingTransferRequestList = sessionFactory.getCurrentSession()

					.getNamedQuery("GET_DOCTOR_FOR_ICU")
					.setInteger("orgId", doctorMasterDto.getOrganizationId())
					.setInteger("unitId", doctorMasterDto.getUnitId())
					.setInteger("specialityId", 9)// Speciality Id 9 is for Intensivist
					.setResultTransformer(Transformers.aliasToBean(DoctorMasterDto.class)).list();
			return new Response(SUCCESS, null, null, pendingTransferRequestList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}


	@Override
	public Response saveICUTransferRequest(TransferDto transferDto) throws ApplicationException {
		try {
			
			Session session= sessionFactory.openSession();
			
			//<-----In-activating previous TransferRequest entry if any
			Query q=session.createQuery("update TransferRequest traReq set traReq.status='I', "
					+ "traReq.updatedBy="+transferDto.getUpdatedBy()+", "
					+ "traReq.updatedDate='"+ADTCommonDateUtils.getDate(transferDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss")+"' "
					+ "WHERE traReq.admissionId="+transferDto.getAdmissionId()+" "
					+ "AND traReq.unitId="+transferDto.getUnitId()+" "
					+ "AND traReq.organizationId="+transferDto.getOrganizationId());
			q.executeUpdate();
			//----->
			//<-----Generating new transferRequest
			TransferRequest transferRequest = mapper.map(transferDto, TransferRequest.class,
					"TransferDto_to_TransferRequest");
			transferRequest=save(transferRequest);
			//----->
			return new Response(SUCCESS, null, null, null, transferRequest);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}


	@Override
	public Response saveICUTransfer(TransferDto transferDto) throws ApplicationException {
		try {
			Session session=sessionFactory.openSession();
			//<-----In-activating previous TransferRequest entry if any
			Query q=session.createQuery("update Transfer traReq set traReq.status='I', "
					+ "traReq.updatedBy="+transferDto.getUpdatedBy()+", "
					+ "traReq.updatedDate='"+ADTCommonDateUtils.getDate(transferDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss")+"' "
					+ "WHERE traReq.admissionId="+transferDto.getAdmissionId()+" "
					+ "AND traReq.unitId="+transferDto.getUnitId()+" "
					+ "AND traReq.organizationId="+transferDto.getOrganizationId());
			q.executeUpdate();
			//----->
			
			
			Transaction tr=session.beginTransaction();
			Transfer transfer = mapper.map(transferDto, Transfer.class,
					"TransferDto_to_Transfer");
			
			transfer.setFromDate(ADTCommonDateUtils.getDate(transferDto.getCreatedDate(),"dd-M-yyyy HH:mm:ss"));
			transfer.setFromTime(ADTCommonDateUtils.getDate(transferDto.getCreatedDate(),"dd-M-yyyy HH:mm:ss"));
			session.save(transfer);
			tr.commit();
			return new Response(SUCCESS, null, null, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}


	@Override
	public Response getICUTransferRequestList(TransferDto transferDto) throws ApplicationException {
		try {
			List<TransferDto> getICUTransferRequestList = sessionFactory.getCurrentSession()

					.getNamedQuery("GET_ICU_TRANSFER_REQ_LIST")
					.setInteger("organizationId", transferDto.getOrganizationId())
					.setInteger("unitId", transferDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(TransferDto.class)).list();
			return new Response(SUCCESS, null, null, getICUTransferRequestList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}


	@Override
	public Response getICUBedsByIUCTypeId(TransferDto transferDto) throws ApplicationException {
		try {
			List<TransferDto> getICUTransferRequestList = sessionFactory.getCurrentSession()

					.getNamedQuery("GET_ICU_BED_LIST_BY_ICU_TYPE_ID")
					.setInteger("organizationId", transferDto.getOrganizationId())
					.setInteger("unitId", transferDto.getUnitId())
					.setInteger("icuTypeId", transferDto.getIcuTypeId())
					.setResultTransformer(Transformers.aliasToBean(BedMasterDto.class)).list();
			return new Response(SUCCESS, null, null, getICUTransferRequestList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}


	@Override
	public Response getICUTransferRequestListByAdmissionId(TransferDto transferDto) throws ApplicationException {
		try {
			List<TransferDto> getICUTransferRequestList = sessionFactory.getCurrentSession()

					.getNamedQuery("GET_ICU_TRANSFER_REQ_LIST_BY_ADMISSION_ID")
					.setInteger("organizationId", transferDto.getOrganizationId())
					.setInteger("unitId", transferDto.getUnitId())
					.setInteger("admissionId", transferDto.getAdmissionId())
					.setResultTransformer(Transformers.aliasToBean(TransferDto.class)).list();
			return new Response(SUCCESS, null, null, getICUTransferRequestList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
