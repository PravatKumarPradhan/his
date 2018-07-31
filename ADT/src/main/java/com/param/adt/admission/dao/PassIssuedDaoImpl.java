package com.param.adt.admission.dao;

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

import com.param.adt.admission.dto.VisitorDto;
import com.param.adt.admission.model.VisitorAgainstPatient;
import com.param.adt.admission.model.VisitorPatientMapper;
import com.param.adt.common.IADTConstants;
import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.global.dto.BedMasterDto;
import com.param.global.dto.AdmissionDto;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;
@Repository
@SuppressWarnings({"rawtypes","unchecked"})
public class PassIssuedDaoImpl extends GenericDao<VisitorAgainstPatient, Integer> implements IPassIssuedDao,ICommonConstants,IADTConstants
{

	public PassIssuedDaoImpl() {
		super(VisitorAgainstPatient.class);
		// TODO Auto-generated constructor stub
	}
	
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	DozerBeanMapper mapper;
	
	@Override
	public Response getVisitorTypeList(AdmissionDto admissionDto) throws ApplicationException {
		try {
			List<AdmissionDto> admissionDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_VISITOR_TYPE_LIST")
					.setInteger("organizationId", admissionDto.getOrganizationId())
					.setInteger("unitId", admissionDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(AdmissionDto.class)).list();
			return new Response(SUCCESS, null, null, admissionDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response saveVisitorAgainsPatient(VisitorDto visitorDto) throws ApplicationException {
		try {
			Session session = sessionFactory.openSession();
			Transaction tr = session.beginTransaction();
			VisitorAgainstPatient visitorAgainstPatient = mapper.map(visitorDto, VisitorAgainstPatient.class,
					"VisitorDto_to_VisitorAgainstPatient");

			session.save(visitorAgainstPatient);
			tr.commit();
			session.close();
			return new Response(SUCCESS, null, null, null, visitorAgainstPatient);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);

	}

	@Override
	public Response saveVisitorPatientMapper(VisitorDto visitorDto) throws ApplicationException {
		try {
			Session session = sessionFactory.openSession();
			Transaction tr = session.beginTransaction();

			VisitorPatientMapper visitorPatientMapper = mapper.map(visitorDto, VisitorPatientMapper.class,
					"VisitorDto_to_VisitorPatientMapper");
			session.save(visitorPatientMapper);
			tr.commit();
			session.close();
			return new Response(SUCCESS, null, VISTORS_SAVED, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getVisitorsList(VisitorDto visitorDto) throws ApplicationException {
		try {
			List<VisitorDto> visitorDtosList = sessionFactory.getCurrentSession().getNamedQuery("GET_VISITORS_LIST")
					.setInteger("organizationId", visitorDto.getOrganizationId())
					.setInteger("unitId", visitorDto.getUnitId())
					.setInteger("admissionId", visitorDto.getAdmissionId())
					.setResultTransformer(Transformers.aliasToBean(VisitorDto.class)).list();
			return new Response(SUCCESS, null, null, visitorDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}


	@Override
	public Response updateVisitorsDetails(VisitorPatientMapper visitorPatientMapper, int visitorPatientId,
			Integer visitorPatientMapperId) throws ApplicationException 
	{
		try {
			Session session = sessionFactory.openSession();
			
			//<-----
			
			/* If the Visitor pass status is A(Accept) or L(Lost) then the status from VisitorAgainstPatient will be I(Inactive) 
			 But status will remain A(active) if we extend the visitors pass */   
			Transaction tr1 = session.beginTransaction();
			if(visitorPatientMapper.getVisitorPassStatus()=='A'||visitorPatientMapper.getVisitorPassStatus()=='L')
			{
				Query q=session.createQuery("update VisitorAgainstPatient vap set vap.status='I', updatedBy="+visitorPatientMapper.getUpdatedBy()+", updatedDate='"+visitorPatientMapper.getUpdatedDate()+"' where vap.visitorPatientId="+visitorPatientId);
				q.executeUpdate();
			}
			
			tr1.commit();
			//----->
			
			//<-----
				// Making previous entry inactive 
			Transaction tr2=session.beginTransaction();
			
			Query q=session.createQuery("update VisitorPatientMapper vpm set vpm.status='I', updatedBy="+visitorPatientMapper.getUpdatedBy()+", updatedDate='"+visitorPatientMapper.getUpdatedDate()+"' where vpm.visitorPatientMapperId="+visitorPatientMapperId);
			q.executeUpdate();
			
			tr2.commit();
			//----->
			
			
			
			//<-----
				//Adding new entry in mapper 
			Transaction tr3=session.beginTransaction();
				session.save(visitorPatientMapper);
			tr3.commit();
			//----->
			
			session.close();
			return new Response(SUCCESS, null, null, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);

	}

	@Override
	public Response getBedListByWardId(BedMasterDto bedMasterDto) throws ApplicationException {
		try {
			List<BedMasterDto> bedMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_BED_BY_WARD_ID")
					.setInteger("organizationId", bedMasterDto.getOrganizationId())
					.setInteger("unitId", bedMasterDto.getUnitId())
					.setInteger("wardId", bedMasterDto.getWardId())
					.setResultTransformer(Transformers.aliasToBean(AdmissionDto.class)).list();
			return new Response(SUCCESS, null, null, bedMasterDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getAdmittedPatientListByMultipleCriteria(AdmissionDto admissionDto) throws ApplicationException {
		try {

			Session session = sessionFactory.openSession();
			Query query = session.getNamedQuery("GET_ADMITTED_PATIENT_LIST");
			String stringQuery = query.getQueryString();
			StringBuilder resQuery = this.searchPatient(admissionDto, stringQuery);
			Query actualQuery = session.createQuery(resQuery.toString());

			List<AdmissionDto> admissionDtosList = actualQuery
					.setInteger("organizationId", admissionDto.getOrganizationId())
					.setInteger("unitId", admissionDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(AdmissionDto.class)).list();
			return new Response(SUCCESS, null, null, admissionDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	private StringBuilder searchPatient(AdmissionDto admissionDto, String stringQuery) {
		int floor = admissionDto.getFloorId();
		int ward = admissionDto.getWardId();
		int bed =admissionDto.getBedId();

		StringBuilder ANDFloor = new StringBuilder(" AND bm.floorId=" + floor);
		StringBuilder ANDWard = new StringBuilder(" AND bm.wardId=" + ward);
		StringBuilder ANDBed = new StringBuilder(" AND bm.bedId=" + bed);
		

		StringBuilder query = new StringBuilder(stringQuery);
		
		if(floor>0 && ward==0 && bed==0)
		{
			query.append(ANDFloor);
		}
		else if(floor>0 && ward>0 && bed==0)
		{
			query.append(ANDFloor).append(ANDWard);
		}
		else if(floor>0 && ward>0 && bed>0)
		{
			query.append(ANDFloor).append(ANDWard).append(ANDBed);
		}
		return query;
	}
}
