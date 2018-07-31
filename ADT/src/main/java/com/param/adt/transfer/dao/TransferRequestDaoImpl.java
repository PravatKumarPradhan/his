package com.param.adt.transfer.dao;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.transfer.dto.TransferDto;
import com.param.adt.transfer.model.TransferRequest;
import com.param.global.dto.AdmissionDto;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class TransferRequestDaoImpl extends GenericDao<TransferRequest, Integer>
		implements ITransferRequestDao, ICommonConstants {

	public TransferRequestDaoImpl() {
		super(TransferRequest.class);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	DozerBeanMapper mapper;

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Response getTransferRequestList(TransferDto transferDto) throws ApplicationException {
		try{
			List<AdmissionDto> transferRequestList = sessionFactory.getCurrentSession()
		
				.getNamedQuery("GET_TRANSFER_REQ_LIST")
				.setInteger("organizationId", transferDto.getOrganizationId())
				.setInteger("unitId", transferDto.getUnitId())
				//.setInteger("transferTypeId",transferDto.getTransferTypeId())
				.setResultTransformer(Transformers.aliasToBean(AdmissionDto.class)).list();
		return new Response(SUCCESS, null, null, transferRequestList, null);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response saveTransferRequest(TransferDto transferDto) throws ApplicationException {
		try {

			TransferRequest transferRequest = mapper.map(transferDto, TransferRequest.class,
					"TransferDto_to_TransferRequest");
			transferRequest = save(transferRequest);
			if(transferDto.getTransferTypeId()==1)
			{
			// <----- Updating transfer status
			Session session = sessionFactory.openSession();
			// Transaction tr =session.beginTransaction();
			Query query = session.createQuery("update Transfer tr set tr.toBedCategoryId="+transferDto.getToBedCategoryId()+", tr.transferRequestId="+ transferRequest.getTransferRequestId() + ""
					+ ", tr.transferStatusId=" + transferDto.getTransferStatusId()
					+ " where tr.transferId=" + transferDto.getTransferId());
			query.executeUpdate();
			// tr.commit();
			// ----->
			}
			else
			{
				// <----- Updating transfer status
				Session session = sessionFactory.openSession();
				// Transaction tr =session.beginTransaction();
				Query query = session.createQuery("update Transfer tr set tr.toBedId="+transferDto.getToBedId()+", tr.toBedCategoryId="+transferDto.getToBedCategoryId()+", tr.transferRequestId="+ transferRequest.getTransferRequestId() + ""
						+ ", tr.transferStatusId=" + transferDto.getTransferStatusId()
						+ " where tr.transferId=" + transferDto.getTransferId());
				query.executeUpdate();
				// tr.commit();
				// ----->
			}
			return new Response(SUCCESS, null, null, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getTransferRequestListByAdmissionId(TransferDto transferDto) throws ApplicationException {
		try{
			List<AdmissionDto> transferRequestList = sessionFactory.getCurrentSession()
		
				.getNamedQuery("GET_TRANSFER_REQ_LIST_BY_ADMISSION_ID_FOR_B2B")
				.setInteger("organizationId", transferDto.getOrganizationId())
				.setInteger("unitId", transferDto.getUnitId())
				.setInteger("admissionId", transferDto.getAdmissionId())
				//.setInteger("transferTypeId",transferDto.getTransferTypeId())
				.setResultTransformer(Transformers.aliasToBean(AdmissionDto.class)).list();
		return new Response(SUCCESS, null, null, transferRequestList, null);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
