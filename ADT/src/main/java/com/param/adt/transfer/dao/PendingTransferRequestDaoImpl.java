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
import com.param.adt.master.global.model.BedLogStatus;
import com.param.adt.transfer.dto.TransferDto;
import com.param.adt.transfer.model.Transfer;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PendingTransferRequestDaoImpl extends GenericDao<Transfer, Integer>
		implements IPendingTransferRequestDao, ICommonConstants {

	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	DozerBeanMapper mapper;

	public PendingTransferRequestDaoImpl() {
		super(Transfer.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Response getPendingTransferRequestListForDoctor(TransferDto transferDto) throws ApplicationException {
		try {
			List<TransferDto> pendingTransferRequestList = sessionFactory.getCurrentSession()

					.getNamedQuery("GET_PENDING_TRANSFER_REQ_LIST_FOR_DOCTOR")
					.setInteger("organizationId", transferDto.getOrganizationId())
					.setInteger("unitId", transferDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(TransferDto.class)).list();
			return new Response(SUCCESS, null, null, pendingTransferRequestList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response acceptTransferRequestForDoctor(TransferDto newTransferDto) throws ApplicationException {
		try {

			Session session = sessionFactory.openSession();
			//<-----Updating Transfer Table -->setting transferStatus = 4(Request sent to doctor) 
			Query query = session.createQuery(
					"update Transfer tr set tr.updatedBy=" + newTransferDto.getUpdatedBy() + ",tr.updatedDate='"
							+ ADTCommonDateUtils.getDate(newTransferDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss")
							+ "' , tr.transferStatusId="+newTransferDto.getTransferStatusId()
							+ " where tr.organizationId=" + newTransferDto.getOrganizationId()
							+ " AND tr.unitId=" + newTransferDto.getUnitId() + " AND tr.transferRequestId="
							+ newTransferDto.getTransferRequestId());
			query.executeUpdate();
			//----->
			
			
			//<-----Updating TransferRequest Table -->setting transferStatus = 4(Request sent to doctor)
			Query query2 = session.createQuery(
					"update TransferRequest tr set tr.updatedBy=" + newTransferDto.getUpdatedBy() + ",tr.updatedDate='"
							+ ADTCommonDateUtils.getDate(newTransferDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss")
							+ "', tr.transferStatusId="+newTransferDto.getTransferStatusId()+" , "
							+ "tr.doctorNote='"+ newTransferDto.getDoctorNote() + "' "
							+ " where tr.organizationId=" + newTransferDto.getOrganizationId()
							+ " AND tr.unitId=" + newTransferDto.getUnitId() + " "
							+ "AND tr.transferRequestId="
							+ newTransferDto.getTransferRequestId());
			System.out.println(query2.toString());
			query2.executeUpdate();
			//----->
			return new Response(SUCCESS, null, "Accepted", null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response rejectTransferRequestForDoctor(TransferDto newTransferDto) throws ApplicationException {
		try {
			Session session = sessionFactory.openSession();
			
			//<-----Updating Transfer Table -->setting transferStatus = 5(Request reject) 
			Query query = session.createQuery(
					"update Transfer tr set tr.updatedBy=" + newTransferDto.getUpdatedBy() + ",tr.updatedDate='"
							+ ADTCommonDateUtils.getDate(newTransferDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss")
							+ "' "
							//+ ", tr.status='I'"
							+ ", tr.transferStatusId=5 where tr.organizationId="
							+ newTransferDto.getOrganizationId() + " AND tr.unitId=" + newTransferDto.getUnitId()
							+ " AND tr.transferRequestId=" + newTransferDto.getTransferRequestId());
			query.executeUpdate();
			//----->
			
			//<-----Updating TransferRequest Table -->setting transferStatus = 5(Request reject) 
			Query query2 = session.createQuery(
					"update TransferRequest tr set tr.updatedBy=" + newTransferDto.getUpdatedBy() + ",tr.updatedDate='"
							+ ADTCommonDateUtils.getDate(newTransferDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss")
							+ "' "
							//+ ",tr.status='I'"
							+ ", tr.transferStatusId=5,tr.doctorNote='"
							+ newTransferDto.getDoctorNote() + "', tr.doctorReasonId="
							+ newTransferDto.getDoctorReasonId() + " where tr.organizationId="
							+ newTransferDto.getOrganizationId() + " AND tr.unitId=" + newTransferDto.getUnitId()
							+ " AND tr.transferRequestId=" + newTransferDto.getTransferRequestId());
			query2.executeUpdate();
			//----->
			
			return new Response(SUCCESS, null, "Rejected", null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getPendingTransferRequestListForADT(TransferDto transferDto) {
		try {
			List<TransferDto> pendingTransferRequestList = sessionFactory.getCurrentSession()

					.getNamedQuery("GET_PENDING_TRANSFER_REQ_LIST_FOR_ADT")
					.setInteger("organizationId", transferDto.getOrganizationId())
					.setInteger("unitId", transferDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(TransferDto.class)).list();
			return new Response(SUCCESS, null, null, pendingTransferRequestList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response acceptTransferRequestForADT(TransferDto transferDto) throws ApplicationException {
		try {
			//<-----Updating transfer 
			Session session = sessionFactory.openSession();
			Query query = session.createQuery(
					"update Transfer transfer set transfer.updatedBy=:updatedBy,transfer.updatedDate=:updatedDate, transfer.toBedCategoryId=:toBedCategoryId, transfer.toBillingBedCategoryId=:toBillingBedCategoryId, transfer.toWardId=:toWardId, transfer.toRoomId=:toRoomId, transfer.toBedId=:toBedId, transfer.transferStatusId=6 WHERE transfer.transferId=:transferId AND transfer.unitId=:unitId AND transfer.organizationId=:orgId");
			query.setInteger("toBedCategoryId", transferDto.getToBedCategoryId());
			query.setInteger("toBillingBedCategoryId", transferDto.getToBillingBedCategoryId());
			query.setInteger("toWardId", transferDto.getToWardId());
			query.setInteger("toRoomId", transferDto.getToRoomId());
			query.setInteger("toBedId", transferDto.getToBedId());
			query.setInteger("transferId", transferDto.getTransferId());
			query.setInteger("updatedBy", transferDto.getUpdatedBy());
			query.setDate("updatedDate", ADTCommonDateUtils.getDate(transferDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
			query.setInteger("unitId", transferDto.getUnitId());
			query.setInteger("orgId", transferDto.getOrganizationId());
			query.executeUpdate();
			//------>
			
			//<------Updating transfer request
			Query query2=session.createQuery("update TransferRequest traReq set traReq.updatedBy=:updatedBy,traReq.updatedDate=:updatedDate,traReq.toWardId=:toWardId,traReq.transferStatusId=6 WHERE traReq.transferRequestId=:transferRequestId AND traReq.unitId=:unitId AND traReq.organizationId=:orgId");
			query2.setInteger("updatedBy", transferDto.getUpdatedBy());
			query2.setDate("updatedDate", ADTCommonDateUtils.getDate(transferDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
			query2.setInteger("unitId", transferDto.getUnitId());
			query2.setInteger("orgId", transferDto.getOrganizationId());
			query2.setInteger("transferRequestId", transferDto.getTransferRequestId());
			query2.setInteger("toWardId", transferDto.getToWardId());
			query2.executeUpdate();
			//------>
			
			//<-------Booking requested bed so that it should not get allocated to any other 
			Query query3=session.createQuery("update BedMaster bm set bm.updatedBy=:updatedBy,bm.updatedDate=:updatedDate, bm.bedStatusId=5 WHERE bm.bedId=:bedId AND bm.unitId=:unitId AND bm.organizationId=:orgId");
			query3.setInteger("updatedBy", transferDto.getUpdatedBy());
			query3.setDate("updatedDate", ADTCommonDateUtils.getDate(transferDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
			query3.setInteger("unitId", transferDto.getUnitId());
			query3.setInteger("orgId", transferDto.getOrganizationId());
			query3.setInteger("bedId", transferDto.getToBedId());
			query3.executeUpdate();
			//----->
			
			//<------Adding new entry in bed log status by in-activating previous active record
			Query query4=session.createQuery("update BedLogStatus bls set bls.updatedBy=:updatedBy,bls.updatedDate=:updatedDate, bls.status='I' WHERE bls.bedId=:bedId AND bls.unitId=:unitId AND bls.organizationId=:orgId AND bls.status='A'");
			query4.setInteger("updatedBy", transferDto.getUpdatedBy());
			query4.setDate("updatedDate", ADTCommonDateUtils.getDate(transferDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
			query4.setInteger("unitId", transferDto.getUnitId());
			query4.setInteger("orgId", transferDto.getOrganizationId());
			query4.setInteger("bedId", transferDto.getToBedId());
			query4.executeUpdate();
			
			Transaction tr=session.beginTransaction();
			BedLogStatus bedLogStatus=new BedLogStatus();
			bedLogStatus.setBedId(transferDto.getToBedId());
			bedLogStatus.setBedStatusId(5);
			bedLogStatus.setStatus('A');
			bedLogStatus.setCreatedBy(transferDto.getUpdatedBy());
			bedLogStatus.setCreatedDate(ADTCommonDateUtils.getDate(transferDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
			bedLogStatus.setUpdatedBy(transferDto.getUpdatedBy());
			bedLogStatus.setUpdatedDate(ADTCommonDateUtils.getDate(transferDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
			bedLogStatus.setOrganizationId(transferDto.getOrganizationId());
			bedLogStatus.setUnitId(transferDto.getUnitId());
			session.save(bedLogStatus);
			tr.commit();
			//------>	
			return new Response(SUCCESS, null, null, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response rejectTransferRequestForADT(TransferDto transferDto) throws ApplicationException {
		try {

			Session session = sessionFactory.openSession();
			Query query = session.createQuery(
					"update Transfer transfer set transfer.updatedBy=:updatedBy,transfer.updatedDate=:updatedDate, transfer.transferStatusId=5 WHERE transfer.transferId=:transferId AND transfer.unitId=:unitId AND transfer.organizationId=:orgId");
			query.setInteger("transferId", transferDto.getTransferId());
			query.setInteger("updatedBy", transferDto.getUpdatedBy());
			query.setDate("updatedDate", ADTCommonDateUtils.getDate(transferDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
			query.setInteger("unitId", transferDto.getUnitId());
			query.setInteger("orgId", transferDto.getOrganizationId());
			query.executeUpdate();
			
			Query query2=session.createQuery("update TransferRequest traReq set traReq.adtRejectReason=:adtRejectReason, traReq.updatedBy=:updatedBy,traReq.updatedDate=:updatedDate,traReq.transferStatusId=5,traReq.adtReasonId=:reasonId WHERE traReq.transferRequestId=:transferRequestId AND traReq.unitId=:unitId AND traReq.organizationId=:orgId");
			query2.setInteger("updatedBy", transferDto.getUpdatedBy());
			query2.setDate("updatedDate", ADTCommonDateUtils.getDate(transferDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
			query2.setInteger("unitId", transferDto.getUnitId());
			query2.setInteger("orgId", transferDto.getOrganizationId());
			query2.setInteger("transferRequestId", transferDto.getTransferRequestId());
			query2.setInteger("reasonId", transferDto.getAdtReasonId());
			query2.setString("adtRejectReason", transferDto.getAdtRejectReason());
			query2.executeUpdate();
			
			
			return new Response(SUCCESS, null, null, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response acceptFinalTransferRequest(TransferDto transferDto) throws ApplicationException {
		try {

			Session session = sessionFactory.openSession();
			
			if(transferDto.getTransferTypeId()!=null)	//
			{
				Query query = session.createQuery(
						"update Transfer transfer set transfer.updatedBy=:updatedBy,transfer.updatedDate=:updatedDate, transfer.toBedCategoryId=:toBedCategoryId, transfer.toBillingBedCategoryId=:toBillingBedCategoryId, transfer.toWardId=:toWardId, transfer.toRoomId=:toRoomId, transfer.toBedId=:toBedId, transfer.transferStatusId=9 WHERE transfer.transferId=:transferId AND transfer.unitId=:unitId AND transfer.organizationId=:orgId");
				query.setInteger("toBedCategoryId", transferDto.getToBedCategoryId());
				query.setInteger("toBillingBedCategoryId", transferDto.getToBillingBedCategoryId());
				query.setInteger("toWardId", transferDto.getToWardId());
				query.setInteger("toRoomId", transferDto.getToRoomId());
				query.setInteger("toBedId", transferDto.getToBedId());
				query.setInteger("transferId", transferDto.getTransferId());
				query.setInteger("updatedBy", transferDto.getUpdatedBy());
				query.setDate("updatedDate", ADTCommonDateUtils.getDate(transferDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
				query.setInteger("unitId", transferDto.getUnitId());
				query.setInteger("orgId", transferDto.getOrganizationId());
				query.executeUpdate();
				//------>
				
				//<------Updating transfer request
				Query query2=session.createQuery("update TransferRequest traReq set traReq.updatedBy=:updatedBy,traReq.updatedDate=:updatedDate,traReq.toWardId=:toWardId,traReq.toBedCategoryId=:toBedCategoryId ,traReq.transferStatusId=9 WHERE traReq.transferRequestId=:transferRequestId AND traReq.unitId=:unitId AND traReq.organizationId=:orgId");
				query2.setInteger("updatedBy", transferDto.getUpdatedBy());
				query2.setDate("updatedDate", ADTCommonDateUtils.getDate(transferDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
				query2.setInteger("unitId", transferDto.getUnitId());
				query2.setInteger("orgId", transferDto.getOrganizationId());
				query2.setInteger("transferRequestId", transferDto.getTransferRequestId());
				query2.setInteger("toWardId", transferDto.getToWardId());
				query2.setInteger("toBedCategoryId", transferDto.getToBedCategoryId());
				query2.executeUpdate();
				//------>
				
				//<-------Booking requested bed so that it should not get allocated to any other 
				Query query3=session.createQuery("update BedMaster bm set bm.updatedBy=:updatedBy,bm.updatedDate=:updatedDate, bm.bedStatusId=5 WHERE bm.bedId=:bedId AND bm.unitId=:unitId AND bm.organizationId=:orgId");
				query3.setInteger("updatedBy", transferDto.getUpdatedBy());
				query3.setDate("updatedDate", ADTCommonDateUtils.getDate(transferDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
				query3.setInteger("unitId", transferDto.getUnitId());
				query3.setInteger("orgId", transferDto.getOrganizationId());
				query3.setInteger("bedId", transferDto.getToBedId());
				query3.executeUpdate();
				//----->
				
				//<------Adding new entry in bed log status by in-activating previous active record
				Query query4=session.createQuery("update BedLogStatus bls set bls.updatedBy=:updatedBy,bls.updatedDate=:updatedDate, bls.status='I' WHERE bls.bedId=:bedId AND bls.unitId=:unitId AND bls.organizationId=:orgId AND bls.status='A'");
				query4.setInteger("updatedBy", transferDto.getUpdatedBy());
				query4.setDate("updatedDate", ADTCommonDateUtils.getDate(transferDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
				query4.setInteger("unitId", transferDto.getUnitId());
				query4.setInteger("orgId", transferDto.getOrganizationId());
				query4.setInteger("bedId", transferDto.getToBedId());
				query4.executeUpdate();
				
				Transaction tr=session.beginTransaction();
				BedLogStatus bedLogStatus=new BedLogStatus();
				bedLogStatus.setBedId(transferDto.getToBedId());
				bedLogStatus.setBedStatusId(5);
				bedLogStatus.setStatus('A');
				bedLogStatus.setCreatedBy(transferDto.getUpdatedBy());
				bedLogStatus.setCreatedDate(ADTCommonDateUtils.getDate(transferDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
				bedLogStatus.setUpdatedBy(transferDto.getUpdatedBy());
				bedLogStatus.setUpdatedDate(ADTCommonDateUtils.getDate(transferDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
				bedLogStatus.setOrganizationId(transferDto.getOrganizationId());
				bedLogStatus.setUnitId(transferDto.getUnitId());
				session.save(bedLogStatus);
				tr.commit();
				//------>	
				
			}
			
			if(transferDto.getTransferTypeId()==null)
			{
			Query query = session.createQuery(
					"update Transfer transfer set transfer.updatedBy=:updatedBy,transfer.updatedDate=:updatedDate, transfer.transferStatusId=9,transfer.status='I' WHERE transfer.transferId=:transferId AND transfer.unitId=:unitId AND transfer.organizationId=:orgId");
				query.setInteger("transferId", transferDto.getTransferId());
				query.setInteger("updatedBy", transferDto.getUpdatedBy());
				query.setDate("updatedDate", ADTCommonDateUtils.getDate(transferDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
				query.setInteger("unitId", transferDto.getUnitId());
				query.setInteger("orgId", transferDto.getOrganizationId());
			query.executeUpdate();
			
			Query query2=session.createQuery("update TransferRequest traReq set traReq.workstationNote=:workStationNote, traReq.updatedBy=:updatedBy,traReq.updatedDate=:updatedDate,traReq.transferStatusId=9,traReq.status='I' WHERE traReq.transferRequestId=:transferRequestId AND traReq.unitId=:unitId AND traReq.organizationId=:orgId");
				query2.setInteger("updatedBy", transferDto.getUpdatedBy());
				query2.setDate("updatedDate", ADTCommonDateUtils.getDate(transferDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
				query2.setInteger("unitId", transferDto.getUnitId());
				query2.setInteger("orgId", transferDto.getOrganizationId());
				query2.setInteger("transferRequestId", transferDto.getTransferRequestId());
				query2.setString("workStationNote", transferDto.getWorkstationNote());
			query2.executeUpdate();
			}
			
			Query query3=session.createQuery("update AdmissionDetails admt set admt.updatedBy=:updatedBy,admt.updatedDate=:updatedDate,admt.floorId=:floorId,admt.wardId=:wardId,admt.bedId=:bedId,roomId=:roomId WHERE admt.admissionId=:admissionId AND admt.unitId=:unitId AND admt.organizationId=:orgId AND admt.status='A'");
				query3.setInteger("updatedBy", transferDto.getUpdatedBy());
				query3.setDate("updatedDate", ADTCommonDateUtils.getDate(transferDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
				query3.setInteger("unitId", transferDto.getUnitId());
				query3.setInteger("orgId", transferDto.getOrganizationId());
				query3.setInteger("admissionId", transferDto.getAdmissionId());
				//query3.setInteger("admissionDetailsId", transferDto.getAdmissionDetailsId());
				query3.setInteger("floorId", transferDto.getToFloorId());
				query3.setInteger("wardId", transferDto.getToWardId());
				query3.setInteger("roomId",transferDto.getToRoomId());
				query3.setInteger("bedId", transferDto.getToBedId());
			
			query3.executeUpdate();
			
			//<-------Releasing previous bed of a patient 
			Query query4=session.createQuery("update BedMaster bm set bm.updatedBy=:updatedBy,bm.updatedDate=:updatedDate, bm.bedStatusId=2 WHERE bm.bedId=:bedId AND bm.unitId=:unitId AND bm.organizationId=:orgId");
				query4.setInteger("updatedBy", transferDto.getUpdatedBy());
				query4.setDate("updatedDate", ADTCommonDateUtils.getDate(transferDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
				query4.setInteger("unitId", transferDto.getUnitId());
				query4.setInteger("orgId", transferDto.getOrganizationId());
				query4.setInteger("bedId", transferDto.getFromBedId());
			query4.executeUpdate();
			//----->
			
			//<------Adding new entry in bed log status by in-activating previous active record
			Query query5=session.createQuery("update BedLogStatus bls set bls.updatedBy=:updatedBy,bls.updatedDate=:updatedDate, bls.status='I' WHERE bls.bedId=:bedId AND bls.unitId=:unitId AND bls.organizationId=:orgId AND bls.status='A'");
				query5.setInteger("updatedBy", transferDto.getUpdatedBy());
				query5.setDate("updatedDate", ADTCommonDateUtils.getDate(transferDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
				query5.setInteger("unitId", transferDto.getUnitId());
				query5.setInteger("orgId", transferDto.getOrganizationId());
				query5.setInteger("bedId", transferDto.getFromBedId());
			query4.executeUpdate();
			
			Transaction tr=session.beginTransaction();
			BedLogStatus bedLogStatus=new BedLogStatus();
				bedLogStatus.setBedId(transferDto.getFromBedId());
				bedLogStatus.setBedStatusId(2);
				bedLogStatus.setStatus('A');
				bedLogStatus.setCreatedBy(transferDto.getUpdatedBy());
				bedLogStatus.setCreatedDate(ADTCommonDateUtils.getDate(transferDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
				bedLogStatus.setUpdatedBy(transferDto.getUpdatedBy());
				bedLogStatus.setUpdatedDate(ADTCommonDateUtils.getDate(transferDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
				bedLogStatus.setOrganizationId(transferDto.getOrganizationId());
				bedLogStatus.setUnitId(transferDto.getUnitId());
			session.save(bedLogStatus);
			tr.commit();
			//------>	
			Transaction tr1=session.beginTransaction();
			Transfer transfer=new Transfer();
				transfer.setOrganizationId(transferDto.getOrganizationId());
				transfer.setUnitId(transferDto.getUnitId());
				transfer.setAdmissionId(transferDto.getAdmissionId());
				transfer.setFromBedCategoryId(transferDto.getToBedCategoryId());
				transfer.setFromBillingBedCategoryId(transferDto.getToBillingBedCategoryId());
				transfer.setFromWardId(transferDto.getToWardId());
				transfer.setFromRoomId(transferDto.getToRoomId());
				transfer.setFromBedId(transferDto.getToBedId());
				transfer.setFromDate(ADTCommonDateUtils.getDate(transferDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
				transfer.setFromTime(ADTCommonDateUtils.getDate(transferDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
				transfer.setCreatedDate(ADTCommonDateUtils.getDate(transferDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
				transfer.setUpdatedDate(ADTCommonDateUtils.getDate(transferDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
				transfer.setTransferStatusId(1);
				transfer.setIsPrimary('N');
				transfer.setStatus('A');
				transfer.setCreatedBy(transferDto.getUpdatedBy());
				transfer.setUpdatedBy(transferDto.getUpdatedBy());
			session.save(transfer);
			tr1.commit();
			return new Response(SUCCESS, null, null, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response rejectFinalTransferRequest(TransferDto transferDto) throws ApplicationException {
	try
	{
		//<-----Updating transfer 
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(
				"update Transfer transfer set transfer.updatedBy=:updatedBy,transfer.updatedDate=:updatedDate, transfer.transferStatusId=5 WHERE transfer.transferId=:transferId AND transfer.unitId=:unitId AND transfer.organizationId=:orgId");
			query.setInteger("transferId", transferDto.getTransferId());
			query.setInteger("updatedBy", transferDto.getUpdatedBy());
			query.setDate("updatedDate", ADTCommonDateUtils.getDate(transferDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
			query.setInteger("unitId", transferDto.getUnitId());
			query.setInteger("orgId", transferDto.getOrganizationId());
		query.executeUpdate();
		//------>
		
		//<------Updating transfer request
		Query query2=session.createQuery("update TransferRequest traReq set traReq.workstationReasonId=:workstationReasonId, traReq.workstationNote=:workStationNote, traReq.updatedBy=:updatedBy,traReq.updatedDate=:updatedDate,traReq.transferStatusId=5 WHERE traReq.transferRequestId=:transferRequestId AND traReq.unitId=:unitId AND traReq.organizationId=:orgId");
			query2.setInteger("updatedBy", transferDto.getUpdatedBy());
			query2.setDate("updatedDate", ADTCommonDateUtils.getDate(transferDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
			query2.setInteger("unitId", transferDto.getUnitId());
			query2.setInteger("orgId", transferDto.getOrganizationId());
			query2.setInteger("transferRequestId", transferDto.getTransferRequestId());
			query2.setString("workStationNote", transferDto.getWorkstationNote());
			query2.setInteger("workstationReasonId", transferDto.getWorkstationReasonId());
		query2.executeUpdate();
		//------>
		
		//<-------Booking requested bed so that it should not get allocated to any other patient
		Query query3=session.createQuery("update BedMaster bm set bm.updatedBy=:updatedBy,bm.updatedDate=:updatedDate, bm.bedStatusId=3 WHERE bm.bedId=:bedId AND bm.unitId=:unitId AND bm.organizationId=:orgId");
			query3.setInteger("updatedBy", transferDto.getUpdatedBy());
			query3.setDate("updatedDate", ADTCommonDateUtils.getDate(transferDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
			query3.setInteger("unitId", transferDto.getUnitId());
			query3.setInteger("orgId", transferDto.getOrganizationId());
			query3.setInteger("bedId", transferDto.getToBedId());
		query3.executeUpdate();
		//----->
		
		//<------Adding new entry in bed log status by in-activating previous active record
		Query query4=session.createQuery("update BedLogStatus bls set bls.updatedBy=:updatedBy,bls.updatedDate=:updatedDate, bls.status='I' WHERE bls.bedId=:bedId AND bls.unitId=:unitId AND bls.organizationId=:orgId AND bls.status='A'");
			query4.setInteger("updatedBy", transferDto.getUpdatedBy());
			query4.setDate("updatedDate", ADTCommonDateUtils.getDate(transferDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
			query4.setInteger("unitId", transferDto.getUnitId());
			query4.setInteger("orgId", transferDto.getOrganizationId());
			query4.setInteger("bedId", transferDto.getToBedId());
		query4.executeUpdate();
		
		Transaction tr=session.beginTransaction();
		BedLogStatus bedLogStatus=new BedLogStatus();
			bedLogStatus.setBedId(transferDto.getToBedId());
			bedLogStatus.setBedStatusId(3);
			bedLogStatus.setStatus('A');
			bedLogStatus.setCreatedBy(transferDto.getUpdatedBy());
			bedLogStatus.setCreatedDate(ADTCommonDateUtils.getDate(transferDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
			bedLogStatus.setUpdatedBy(transferDto.getUpdatedBy());
			bedLogStatus.setUpdatedDate(ADTCommonDateUtils.getDate(transferDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
			bedLogStatus.setOrganizationId(transferDto.getOrganizationId());
			bedLogStatus.setUnitId(transferDto.getUnitId());
		session.save(bedLogStatus);
		tr.commit();
		//------>	
		return new Response(SUCCESS, null, null, null, null);
	} catch (HibernateException he) {
		he.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getFinalTransferRequestList(TransferDto transferDto) throws ApplicationException {
		try {
			List<TransferDto> pendingTransferRequestList = sessionFactory.getCurrentSession()

					.getNamedQuery("GET_FINAL_TRANSFER_REQUEST_LIST")
					.setInteger("organizationId", transferDto.getOrganizationId())
					.setInteger("unitId", transferDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(TransferDto.class)).list();
			return new Response(SUCCESS, null, null, pendingTransferRequestList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response acceptTransferRequest(TransferDto transferDto) throws ApplicationException {
		try {

			Session session = sessionFactory.openSession();
			Query query = session.createQuery(
					"update Transfer transfer set transfer.updatedBy=:updatedBy,transfer.updatedDate=:updatedDate, transfer.transferStatusId=7 WHERE transfer.transferId=:transferId AND transfer.unitId=:unitId AND transfer.organizationId=:orgId");
				query.setInteger("transferId", transferDto.getTransferId());
				query.setInteger("updatedBy", transferDto.getUpdatedBy());
				query.setDate("updatedDate", ADTCommonDateUtils.getDate(transferDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
				query.setInteger("unitId", transferDto.getUnitId());
				query.setInteger("orgId", transferDto.getOrganizationId());
			query.executeUpdate();
			
			Query query2=session.createQuery("update TransferRequest traReq set traReq.finalNote=:finalNote, traReq.updatedBy=:updatedBy,traReq.updatedDate=:updatedDate,traReq.transferStatusId=7 WHERE traReq.transferRequestId=:transferRequestId AND traReq.unitId=:unitId AND traReq.organizationId=:orgId");
				query2.setInteger("updatedBy", transferDto.getUpdatedBy());
				query2.setDate("updatedDate", ADTCommonDateUtils.getDate(transferDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
				query2.setInteger("unitId", transferDto.getUnitId());
				query2.setInteger("orgId", transferDto.getOrganizationId());
				query2.setInteger("transferRequestId", transferDto.getTransferRequestId());
				query2.setString("finalNote", transferDto.getFinalNote());
			query2.executeUpdate();
			
			
			return new Response(SUCCESS, null, null, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response rejectTransferRequest(TransferDto transferDto) throws ApplicationException {
		try
		{
			//<-----Updating transfer 
			Session session = sessionFactory.openSession();
			Query query = session.createQuery(
					"update Transfer transfer set transfer.updatedBy=:updatedBy,transfer.updatedDate=:updatedDate, transfer.transferStatusId=5 WHERE transfer.transferId=:transferId AND transfer.unitId=:unitId AND transfer.organizationId=:orgId");
				query.setInteger("transferId", transferDto.getTransferId());
				query.setInteger("updatedBy", transferDto.getUpdatedBy());
				query.setDate("updatedDate", ADTCommonDateUtils.getDate(transferDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
				query.setInteger("unitId", transferDto.getUnitId());
				query.setInteger("orgId", transferDto.getOrganizationId());
			query.executeUpdate();
			//------>
			
			//<------Updating transfer request
			Query query2=session.createQuery("update TransferRequest traReq set traReq.finalReasonId=:finalReasonId, traReq.finalNote=:finalNote, traReq.updatedBy=:updatedBy,traReq.updatedDate=:updatedDate,traReq.transferStatusId=5 WHERE traReq.transferRequestId=:transferRequestId AND traReq.unitId=:unitId AND traReq.organizationId=:orgId");
				query2.setInteger("updatedBy", transferDto.getUpdatedBy());
				query2.setDate("updatedDate", ADTCommonDateUtils.getDate(transferDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
				query2.setInteger("unitId", transferDto.getUnitId());
				query2.setInteger("orgId", transferDto.getOrganizationId());
				query2.setInteger("transferRequestId", transferDto.getTransferRequestId());
				query2.setString("finalNote", transferDto.getFinalNote());
				query2.setInteger("finalReasonId", transferDto.getFinalReasonId());
			query2.executeUpdate();
			//------>
			
			//<-------Un-booking requested bed
			Query query3=session.createQuery("update BedMaster bm set bm.updatedBy=:updatedBy,bm.updatedDate=:updatedDate, bm.bedStatusId=3 WHERE bm.bedId=:bedId AND bm.unitId=:unitId AND bm.organizationId=:orgId");
				query3.setInteger("updatedBy", transferDto.getUpdatedBy());
				query3.setDate("updatedDate", ADTCommonDateUtils.getDate(transferDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
				query3.setInteger("unitId", transferDto.getUnitId());
				query3.setInteger("orgId", transferDto.getOrganizationId());
				query3.setInteger("bedId", transferDto.getToBedId());
			query3.executeUpdate();
			//----->
			
			//<------Adding new entry in bed log status by in-activating previous active record
			Query query4=session.createQuery("update BedLogStatus bls set bls.updatedBy=:updatedBy,bls.updatedDate=:updatedDate, bls.status='I' WHERE bls.bedId=:bedId AND bls.unitId=:unitId AND bls.organizationId=:orgId AND bls.status='A'");
				query4.setInteger("updatedBy", transferDto.getUpdatedBy());
				query4.setDate("updatedDate", ADTCommonDateUtils.getDate(transferDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
				query4.setInteger("unitId", transferDto.getUnitId());
				query4.setInteger("orgId", transferDto.getOrganizationId());
				query4.setInteger("bedId", transferDto.getToBedId());
			query4.executeUpdate();
			
			Transaction tr=session.beginTransaction();
			BedLogStatus bedLogStatus=new BedLogStatus();
				bedLogStatus.setBedId(transferDto.getToBedId());
				bedLogStatus.setBedStatusId(3);
				bedLogStatus.setStatus('A');
				bedLogStatus.setCreatedBy(transferDto.getUpdatedBy());
				bedLogStatus.setCreatedDate(ADTCommonDateUtils.getDate(transferDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
				bedLogStatus.setUpdatedBy(transferDto.getUpdatedBy());
				bedLogStatus.setUpdatedDate(ADTCommonDateUtils.getDate(transferDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
				bedLogStatus.setOrganizationId(transferDto.getOrganizationId());
				bedLogStatus.setUnitId(transferDto.getUnitId());
			session.save(bedLogStatus);
			tr.commit();
			//------>	
			return new Response(SUCCESS, null, null, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getFinalTransferRequestListByAdmission(TransferDto transferDto) throws ApplicationException {
		try {
			List<TransferDto> pendingTransferRequestList = sessionFactory.getCurrentSession()

					.getNamedQuery("GET_FINAL_TRANSFER_REQUEST_LIST_BY_ADMISSION_ID")
					.setInteger("organizationId", transferDto.getOrganizationId())
					.setInteger("unitId", transferDto.getUnitId())
					.setInteger("admissionId", transferDto.getAdmissionId())
					.setResultTransformer(Transformers.aliasToBean(TransferDto.class)).list();
			return new Response(SUCCESS, null, null, pendingTransferRequestList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
}


