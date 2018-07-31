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

import com.param.adt.common.ADTCommonDateUtils;
import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.transfer.dto.TransferDto;
import com.param.adt.transfer.model.TransferOfCareRequest;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;


@Repository
@SuppressWarnings({"rawtypes","unchecked"})
public class TransferOfCareDaoImpl extends GenericDao<TransferOfCareRequest, Integer> implements ITransferOfCareDao,ICommonConstants{

	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	DozerBeanMapper mapper;
	
	public TransferOfCareDaoImpl() 
	{
		super(TransferOfCareRequest.class);
	}

	
	@Override
	public Response saveTransferOfCareRequest(TransferDto transferDto) throws ApplicationException {
		try {
			TransferOfCareRequest transferOfCareRequest = mapper.map(transferDto, TransferOfCareRequest.class,
					"TransferDto_to_TransferOfCareRequest");
			transferOfCareRequest = save(transferOfCareRequest);

			return new Response(SUCCESS, null, null, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}


	@Override
	public Response getTransferOfCareRequestList(TransferDto transferDto) throws ApplicationException {
		try {
			List<TransferDto> transferOfCareList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_TRANSFER_OF_CARE_REQ_LIST")
					.setInteger("organizationId", transferDto.getOrganizationId())
					.setInteger("unitId", transferDto.getUnitId())
					.setInteger("transferTypeId", transferDto.getTransferTypeId())
					.setResultTransformer(Transformers.aliasToBean(TransferDto.class)).list();
			return new Response(SUCCESS, null, null, transferOfCareList, null);
		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}


	@Override
	public Response getTransferOfCareRequestListForDoctor(TransferDto transferDto) throws ApplicationException {
		try {
			List<TransferDto> transferOfCareList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_TRANSFER_OF_CARE_REQ_LIST_FOR_DOC")
					.setInteger("organizationId", transferDto.getOrganizationId())
					.setInteger("unitId", transferDto.getUnitId())
					.setInteger("transferTypeId", transferDto.getTransferTypeId())
					.setResultTransformer(Transformers.aliasToBean(TransferDto.class)).list();
			return new Response(SUCCESS, null, null, transferOfCareList, null);
		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}


	@Override
	public Response acceptTransferOfCareRequest(TransferDto transferDto) throws ApplicationException {
		try {

			Session session=sessionFactory.openSession();
			
			//<----- Changing doctor and speciality id's in Admission table 
			Query query=session.createQuery("update Admission adm set "
					+ "adm.doctorId=:doctorId , "
					+ "adm.specialityId=:specialityId , "
					+ "adm.updatedBy=:updatedBy ,"
					+ "adm.updatedDate=:updatedDate "
					+ "WHERE adm.admissionId=:admissionId "
					+ "AND adm.organizationId=:organizationId "
					+ "AND adm.unitId=:unitId "
					);
				query.setInteger("doctorId", transferDto.getRequestTo());
				query.setInteger("specialityId", transferDto.getToSpecialityId());
				query.setInteger("updatedBy", transferDto.getUpdatedBy());
				query.setInteger("admissionId", transferDto.getAdmissionId());
				query.setInteger("organizationId", transferDto.getOrganizationId());
				query.setInteger("unitId",transferDto.getUnitId());
				query.setDate("updatedDate", ADTCommonDateUtils.getDate(transferDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
			query.executeUpdate();
			//----->
			
			//<-----Updating isTransferOfCare to Y for that admissionDetails
			Query query2=session.createQuery("update AdmissionDetails adl set "
					+ "adl.isTransferOfCare='Y' ,"
					+ "adl.updatedBy=:updatedBy ,"
					+ "adl.updatedDate=:updatedDate "
					+ "WHERE adl.admissionId=:admissionId "
					+ "AND adl.organizationId=:organizationId "
					+ "AND adl.unitId=:unitId "
					+ "AND adl.status='A' ");
				query2.setInteger("updatedBy", transferDto.getUpdatedBy());
				query2.setInteger("admissionId", transferDto.getAdmissionId());
				query2.setInteger("organizationId", transferDto.getOrganizationId());
				query2.setInteger("unitId",transferDto.getUnitId());
				query2.setDate("updatedDate", ADTCommonDateUtils.getDate(transferDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
			query2.executeUpdate();
			//----->
			
			
			//<-----Updating isTransferOfCare to Y for that admission
			Query query3=session.createQuery("update TransferOfCareRequest toc set "
					+ "toc.transferStatusId=:transferStatusId ,"
					+ "toc.updatedBy=:updatedBy ,"
					+ "toc.updatedDate=:updatedDate, "
					+ "toc.authorizedBy=:authorizedBy "
					//+ "toc.remark=:remark "
					+ "WHERE toc.transferOfCareId=:transferOfCareId "
					+ "AND toc.organizationId=:organizationId "
					+ "AND toc.unitId=:unitId "
					+ "AND toc.transferTypeId=4 ");
				query3.setInteger("transferStatusId", transferDto.getTransferStatusId());
				query3.setInteger("updatedBy", transferDto.getUpdatedBy());
				query3.setInteger("transferOfCareId", transferDto.getTransferOfCareId());
				query3.setInteger("organizationId", transferDto.getOrganizationId());
				query3.setInteger("unitId",transferDto.getUnitId());
				query3.setInteger("authorizedBy", transferDto.getAuthorizedBy());
				query3.setDate("updatedDate", ADTCommonDateUtils.getDate(transferDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
				//query3.setString("remark", transferDto.getRemark());
			query3.executeUpdate();
			//----->
			
			return new Response(SUCCESS, null, null, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}


	@Override
	public Response rejectTransferOfCareRequest(TransferDto transferDto) throws ApplicationException {
		try {

			Session session=sessionFactory.openSession();
			
			Query query3=session.createQuery("update TransferOfCareRequest toc set "
					+ "toc.transferStatusId=:transferStatusId ,"
					+ "toc.updatedBy=:updatedBy ,"
					+ "toc.updatedDate=:updatedDate, "
					+ "toc.authorizedBy=:authorizedBy, "
					+ "toc.rejectReasonId=:rejectReasonId, "
					+ "toc.remark=:remark "
					+ "WHERE toc.transferOfCareId=:transferOfCareId "
					+ "AND toc.organizationId=:organizationId "
					+ "AND toc.unitId=:unitId "
					+ "AND toc.transferTypeId=4 ");
				query3.setInteger("transferStatusId", transferDto.getTransferStatusId());
				query3.setInteger("updatedBy", transferDto.getUpdatedBy());
				query3.setInteger("transferOfCareId", transferDto.getTransferOfCareId());
				query3.setInteger("organizationId", transferDto.getOrganizationId());
				query3.setInteger("unitId",transferDto.getUnitId());
				query3.setInteger("authorizedBy", transferDto.getAuthorizedBy());
				query3.setInteger("rejectReasonId", transferDto.getRejectReasonId());
				query3.setDate("updatedDate", ADTCommonDateUtils.getDate(transferDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
				query3.setString("remark", transferDto.getRemark());
			query3.executeUpdate();
			
			/*
			 * authorizedBy : 1 organizationId : 1 rejectReasonId : "2" remark :
			 * "Reject i have no time Pla forwarsd Other doctor..Thanks"
			 * transferOfCareId : 3 transferStatusId : 5 transferTypeId : 4
			 * unitId : 1 updatedBy : 1 updatedDate : "23-12-2017 12:17:12"
			 */
			
			return new Response(SUCCESS, null, null, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}


	@Override
	public Response acceptConsultOrderRequest(TransferDto transferDto) throws ApplicationException {
		try {

			Session session=sessionFactory.openSession();
			
			Query query3=session.createQuery("update TransferOfCareRequest toc set "
					+ "toc.updatedBy=:updatedBy ,"
					+ "toc.updatedDate=:updatedDate, "
					+ "toc.authorizedBy=:authorizedBy, "
					+ "toc.transferStatusId=11 "
					+ "WHERE toc.transferOfCareId=:transferOfCareId "
					+ "AND toc.organizationId=:organizationId "
					+ "AND toc.unitId=:unitId "
					);
				query3.setInteger("updatedBy", transferDto.getUpdatedBy());
				query3.setInteger("transferOfCareId", transferDto.getTransferOfCareId());
				query3.setInteger("organizationId", transferDto.getOrganizationId());
				query3.setInteger("unitId",transferDto.getUnitId());
				query3.setInteger("authorizedBy", transferDto.getAuthorizedBy());
				query3.setDate("updatedDate", ADTCommonDateUtils.getDate(transferDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
			query3.executeUpdate();
			
			return new Response(SUCCESS, null, null, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}


	@Override
	public Response searchConsultOrderRequestByAdmissionId(TransferDto transferDto) throws ApplicationException {
		try {
			List<TransferDto> transferOfCareList = sessionFactory.getCurrentSession()
					.getNamedQuery("SEARCH_CONSULT_REQ_ORD_BY_ADMISSION_ID")
					.setInteger("organizationId", transferDto.getOrganizationId())
					.setInteger("unitId", transferDto.getUnitId())
					.setInteger("admissionId", transferDto.getAdmissionId())
					.setInteger("transferTypeId", transferDto.getTransferTypeId())
					.setResultTransformer(Transformers.aliasToBean(TransferDto.class)).list();
			
			return new Response(SUCCESS, null, null, transferOfCareList, null);
			
		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}


	

}
