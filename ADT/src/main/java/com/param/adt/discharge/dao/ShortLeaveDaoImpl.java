package com.param.adt.discharge.dao;

import java.util.List;

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
import com.param.adt.discharge.dto.DischargeDto;
import com.param.adt.discharge.model.ShortLeave;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({"rawtypes","unchecked"})
public class ShortLeaveDaoImpl extends GenericDao<ShortLeave, Integer> implements IShortLeaveDao,ICommonConstants 
{

	public ShortLeaveDaoImpl() {
		super(ShortLeave.class);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public Response saveShortLeaveRequest(DischargeDto dischargeDto) {
		try {
			ShortLeave shortLeave =new ShortLeave();
				shortLeave.setOrganizationId(dischargeDto.getOrganizationId());
				shortLeave.setUnitId(dischargeDto.getUnitId());
				shortLeave.setPatientId(dischargeDto.getPatientId());
				shortLeave.setAdmissionId(dischargeDto.getAdmissionId());
				shortLeave.setFromDate(ADTCommonDateUtils.getDate(dischargeDto.getFromDate(),"dd-M-yyyy HH:mm:ss"));
				shortLeave.setToDate(ADTCommonDateUtils.getDate(dischargeDto.getToDate(),"dd-M-yyyy HH:mm:ss"));
				shortLeave.setNote(dischargeDto.getNote());
				shortLeave.setCreatedBy(dischargeDto.getCreatedBy());
				shortLeave.setCreatedDate(ADTCommonDateUtils.getDate(dischargeDto.getCreatedDate(),"dd-M-yyyy HH:mm:ss"));
				shortLeave.setUpdatedBy(dischargeDto.getUpdatedBy());
				shortLeave.setUpdatedDate(ADTCommonDateUtils.getDate(dischargeDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
				shortLeave.setStatus(dischargeDto.getStatus());
				shortLeave.settPatientId(dischargeDto.gettPatientId());
				shortLeave.setFromTime(dischargeDto.getFromTime());
				shortLeave.setToTime(dischargeDto.getToTime());
				shortLeave.setShortLeaveStatusId(dischargeDto.getShortLeaveStatusId());
				shortLeave.setShortLeaveReasonId(dischargeDto.getShortLeaveReasonId());
				shortLeave.setTreatingDoctorId(dischargeDto.getDoctorId());
			shortLeave = save(shortLeave);
			return new Response(SUCCESS, null, null, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getShortLeaveRequestList(DischargeDto dischargeDto) throws ApplicationException {
		try {
			List<DischargeDto> getShortLeaveRequestList = sessionFactory.getCurrentSession()

					.getNamedQuery("GET_SHORT_LEAVE_REQUEST_LIST")
					.setInteger("organizationId", dischargeDto.getOrganizationId())
					.setInteger("unitId", dischargeDto.getUnitId())
					.setParameterList("shortLeaveStatusIdList", dischargeDto.getShortLeaveStatusIdList())
					.setResultTransformer(Transformers.aliasToBean(DischargeDto.class)).list();
			return new Response(SUCCESS, null, null, getShortLeaveRequestList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getShortLeaveRequestListForDoctor(DischargeDto dischargeDto) throws ApplicationException {
		try {
			List<DischargeDto> getShortLeaveRequestListForDoctor = sessionFactory.getCurrentSession()

					.getNamedQuery("GET_SHORT_LEAVE_REQUEST_LIST_FOR_DOCTOR")
					.setInteger("organizationId", dischargeDto.getOrganizationId())
					.setInteger("unitId", dischargeDto.getUnitId())
					.setInteger("treatingDoctorId", dischargeDto.getDoctorId())
					.setResultTransformer(Transformers.aliasToBean(DischargeDto.class)).list();
			return new Response(SUCCESS, null, null, getShortLeaveRequestListForDoctor, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response acceptShotLeaveByDoctor(DischargeDto dischargeDto) throws ApplicationException {
		try {

			Session session = sessionFactory.openSession();
			Query query = session.createQuery("update ShortLeave sl set sl.updatedBy=" + dischargeDto.getUpdatedBy()+ ", " 
					+ " sl.updatedDate='"+ ADTCommonDateUtils.getDate(dischargeDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss")+ "',"
					+ " sl.shortLeaveStatusId=2, "
					+ " sl.doctorsNote='"+dischargeDto.getDoctorsNote()+"', "
					+ " sl.authorizedBy="+dischargeDto.getDoctorId()
					+ " WHERE sl.shortLeaveId=" + dischargeDto.getShortLeaveId() 
					+ " AND sl.organizationId="+ dischargeDto.getOrganizationId() 
					+ " AND sl.unitId=" + dischargeDto.getUnitId()
					+ " AND sl.status='A' ");
			query.executeUpdate();
			return new Response(SUCCESS, null, null, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response rejectShotLeaveByDoctor(DischargeDto dischargeDto) throws ApplicationException {
		try {

			Session session = sessionFactory.openSession();
			Query query = session.createQuery("update ShortLeave sl set sl.updatedBy=" + dischargeDto.getUpdatedBy()+ ", " 
					+ " sl.updatedDate='"+ ADTCommonDateUtils.getDate(dischargeDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss")+ "',"
					+ " sl.shortLeaveStatusId=7, "
					+ " sl.rejectionReasonId="+dischargeDto.getRejectionReasonId()+", "
					+ " sl.doctorsNote='"+dischargeDto.getDoctorsNote()+"' "
					//+ " sl.status='I' "
					+ " WHERE sl.shortLeaveId=" + dischargeDto.getShortLeaveId() 
					+ " AND sl.organizationId="+ dischargeDto.getOrganizationId() 
					+ " AND sl.unitId=" + dischargeDto.getUnitId()
					+ " AND sl.status='A' ");
			query.executeUpdate();
			return new Response(SUCCESS, null, null, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response acceptShotLeaveByBilling(DischargeDto dischargeDto) throws ApplicationException {
		try {

			Session session = sessionFactory.openSession();
			Query query = session.createQuery("update ShortLeave sl set sl.updatedBy=" + dischargeDto.getUpdatedBy()+ ", " 
					+ " sl.updatedDate='"+ ADTCommonDateUtils.getDate(dischargeDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss")+ "',"
					+ " sl.billingsNote='"+dischargeDto.getBillingsNote()+"', "
					+ " sl.shortLeaveStatusId=3 "
					+ " WHERE sl.shortLeaveId=" + dischargeDto.getShortLeaveId() 
					+ " AND sl.organizationId="+ dischargeDto.getOrganizationId() 
					+ " AND sl.unitId=" + dischargeDto.getUnitId()
					+ " AND sl.status='A' ");
			query.executeUpdate();
			return new Response(SUCCESS, null, null, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response rejectShotLeaveByBilling(DischargeDto dischargeDto) throws ApplicationException {
		try {

			Session session = sessionFactory.openSession();
			Query query = session.createQuery("update ShortLeave sl set sl.updatedBy=" + dischargeDto.getUpdatedBy()+ ", " 
					+ " sl.updatedDate='"+ ADTCommonDateUtils.getDate(dischargeDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss")+ "',"
					+ " sl.billingsNote='"+dischargeDto.getBillingsNote()+"', "
					+ " sl.shortLeaveStatusId=8 "
					//+ " sl.status='I' "
					+ " WHERE sl.shortLeaveId=" + dischargeDto.getShortLeaveId() 
					+ " AND sl.organizationId="+ dischargeDto.getOrganizationId() 
					+ " AND sl.unitId=" + dischargeDto.getUnitId()
					+ " AND sl.status='A' ");
			query.executeUpdate();
			return new Response(SUCCESS, null, null, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response releasePatientForShotLeave(DischargeDto dischargeDto) throws ApplicationException {
		try {

			Session session = sessionFactory.openSession();
				Query query = session.createQuery("update ShortLeave sl set sl.updatedBy=" + dischargeDto.getUpdatedBy()+ ", " 
						+ " sl.updatedDate='"+ ADTCommonDateUtils.getDate(dischargeDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss")+ "',"
						+ " sl.shortLeaveStatusId=4 "
						+ " WHERE sl.shortLeaveId=" + dischargeDto.getShortLeaveId() 
						+ " AND sl.organizationId="+ dischargeDto.getOrganizationId() 
						+ " AND sl.unitId=" + dischargeDto.getUnitId()
						+ " AND sl.status='A' ");
				query.executeUpdate();
				
				Query query2 = session.createQuery("update AdmissionDetails sl set sl.updatedBy=" + dischargeDto.getUpdatedBy()+ ", " 
						+ " sl.updatedDate='"+ ADTCommonDateUtils.getDate(dischargeDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss")+ "',"
						+ " sl.isShortLeave='Y' "
						+ " WHERE sl.admissionId=" + dischargeDto.getAdmissionId() 
						+ " AND sl.organizationId="+ dischargeDto.getOrganizationId() 
						+ " AND sl.unitId=" + dischargeDto.getUnitId()
						+ " AND sl.status='A' ");
				query2.executeUpdate();
			return new Response(SUCCESS, null, null, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, null, null, null);
	}

	
}
