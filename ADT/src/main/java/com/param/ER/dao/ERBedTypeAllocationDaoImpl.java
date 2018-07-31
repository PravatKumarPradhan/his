package com.param.ER.dao;

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

import com.param.ER.dto.ERBedTypeAllocationDto;
import com.param.ER.model.ERBedTypeAllocation;
import com.param.adt.admission.model.Admission;
import com.param.adt.admission.model.AdmissionDetails;
import com.param.adt.common.ADTCommonDateUtils;
import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.global.dto.BedMasterDto;
import com.param.adt.master.global.model.BedLogStatus;
import com.param.adt.transfer.dto.TransferDto;
import com.param.adt.transfer.model.Transfer;
import com.param.global.dto.AdmissionDto;
import com.param.global.dto.DoctorMasterDto;
import com.param.global.dto.UnknownPatientRegistrationDto;
import com.param.global.org.common.dto.PriorityMasterDto;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Repository
public class ERBedTypeAllocationDaoImpl extends GenericDao<ERBedTypeAllocation, Integer>
		implements IERBedTypeAllocationDao, ICommonConstants {

	
	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DozerBeanMapper mapper;

	public ERBedTypeAllocationDaoImpl() {
		super(ERBedTypeAllocation.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Response getUnknownPatientsList(UnknownPatientRegistrationDto unknownPatientRegistrationDto)
			throws ApplicationException {
		try {

			Session session = sessionFactory.openSession();
			StringBuilder newQuery = getSearchQuery(unknownPatientRegistrationDto);
			Query actualQuery = session.createQuery(newQuery.toString());

			List<UnknownPatientRegistrationDto> unknownPatientRegistrationDtosList = actualQuery
					.setInteger("orgId", unknownPatientRegistrationDto.getOrganizationId())
					.setInteger("unitId", unknownPatientRegistrationDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(PriorityMasterDto.class)).list();
			if (unknownPatientRegistrationDtosList != null) {
				return new Response(SUCCESS, null, null, unknownPatientRegistrationDtosList, null);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getERBedTypeAllocationList(ERBedTypeAllocationDto erBedTypeAllocationDto) {
		try {

			List<ERBedTypeAllocationDto> erBedTypeAllocationDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ER_BED_TYPE_ALLOCATION_LIST")
					.setInteger("orgId", erBedTypeAllocationDto.getOrganizationId())
					.setInteger("unitId", erBedTypeAllocationDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(AdmissionDto.class)).list();
			if (erBedTypeAllocationDtosList != null) {
				return new Response(SUCCESS, null, null, erBedTypeAllocationDtosList, null);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getCount(ERBedTypeAllocationDto erBedTypeAllocationDto) {
		try {
			List<ERBedTypeAllocationDto> eRBedTypeAllocationDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ER_BED_TYPE_ALLOCATION_LIST")
					.setInteger("orgId", erBedTypeAllocationDto.getOrganizationId())
					.setInteger("unitId", erBedTypeAllocationDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(AdmissionDto.class)).list();
			if (eRBedTypeAllocationDtosList != null) {
				Integer count = eRBedTypeAllocationDtosList.size();
				return new Response(SUCCESS, null, null, null, count);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getERBedList(ERBedTypeAllocationDto erBedTypeAllocationDto) throws ApplicationException {
		try {

			List<BedMasterDto> bedMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_ER_BED_LIST")
					.setInteger("organizationId", erBedTypeAllocationDto.getOrganizationId())
					.setInteger("unitId", erBedTypeAllocationDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(BedMasterDto.class)).list();

			return new Response(SUCCESS, null, null, bedMasterDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateERPatientAdmission(AdmissionDto admissionDto) throws ApplicationException {
		try {

			Admission admission = mapper.map(admissionDto, Admission.class, "AdmissionDto_to_Admission");
			Session session = sessionFactory.openSession();
			Transaction tr = session.beginTransaction();
			session.update(admission);
			tr.commit();
			session.close();
			return new Response<AdmissionDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<AdmissionDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<AdmissionDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}

	@Override
	public Response updateERPatientDetails(AdmissionDto admissionDto) throws ApplicationException {
		try {
			Session session = sessionFactory.openSession();

			Transaction tr = session.beginTransaction();
			AdmissionDetails admissionDetails = mapper.map(admissionDto, AdmissionDetails.class,
					"AdmissionDto_to_AdmissionDetails");
			session.update(admissionDetails);
			tr.commit();

			if (admissionDto.getBedId() != null)
			// <-----Changing status of bed to Booked (5 is the ID of 'booked'
			// status)
			{
				Query qry3 = session
						.createQuery("update BedMaster bed set bed.bedStatusId=" + admissionDto.getBedStatusId()
								+ ", bed.updatedBy=" + admissionDto.getUpdatedBy() + ", bed.updatedDate='"
								+ ADTCommonDateUtils.getDate(admissionDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss") + "'"
								+ " where bed.bedId=" + admissionDto.getBedId());

				qry3.executeUpdate();
				// ----->

				// <-----Updating status of current bed to I in BedLogStatus
				Query qry4 = session.createQuery("update BedLogStatus bedStatus set bedStatus.status='I'"
						+ ", bedStatus.updatedBy=" + admissionDto.getUpdatedBy() + ", bedStatus.updatedDate='"
						+ ADTCommonDateUtils.getDate(admissionDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss") + "'"
						+ " where bedStatus.status='A' AND bedStatus.bedId=" + admissionDto.getBedId());
				qry4.executeUpdate();
				// ----->

				// <-----Adding new entry in log as the status of bed is
				// changing
				BedLogStatus bedLogStatus = mapper.map(admissionDto, BedLogStatus.class,
						"AdmissionDto_to_BedLogStatus");
				Transaction tr2 = session.beginTransaction();
				session.save(bedLogStatus);
				tr2.commit();
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

	private StringBuilder getSearchQuery(UnknownPatientRegistrationDto unknownPatientRegistrationDto) {
		Session session = sessionFactory.openSession();
		Query query = session.getNamedQuery("GET_UNKNOWN_PATIENT_LIST");
		StringBuilder qry = new StringBuilder(query.getQueryString());
		if (unknownPatientRegistrationDto.getErBedType() > 0
				&& unknownPatientRegistrationDto.getPatientName() == null) {
			qry.append("AND admd.er_bed_type_allocation=" + unknownPatientRegistrationDto.getErBedType());
			return qry;

		} else if (unknownPatientRegistrationDto.getErBedType() > 0
				&& unknownPatientRegistrationDto.getPatientName() != null) {
			qry.append("AND admd.er_bed_type_allocation=" + unknownPatientRegistrationDto.getErBedType()
					+ " AND patient.patent_name=" + unknownPatientRegistrationDto.getPatientName());
			return qry;
		} else if (unknownPatientRegistrationDto.getErBedType() == 0
				&& unknownPatientRegistrationDto.getPatientName() != null) {
			qry.append("AND adm.status='B'");
			return qry;

		} else {
			return qry;
		}
	}

	@Override
	public Response getActiveDoctorList(DoctorMasterDto doctorMasterDto) throws ApplicationException {
		try {

			List<DoctorMasterDto> doctorMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_DOCTOR_LIST").setInteger("orgId", doctorMasterDto.getOrganizationId())
					.setInteger("unitId", doctorMasterDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(DoctorMasterDto.class)).list();

			return new Response(SUCCESS, null, null, doctorMasterDtoList, null);

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response saveTransfer(TransferDto transferDto) throws ApplicationException {
		try {

			Session session = sessionFactory.openSession();
			Transaction tr = session.beginTransaction();
			Transfer transfer = mapper.map(transferDto, Transfer.class, "TransferDto_to_Transfer");
				session.save(transfer);
			tr.commit();
			session.close();
			return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}
}
