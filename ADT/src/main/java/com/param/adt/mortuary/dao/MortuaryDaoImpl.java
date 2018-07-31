package com.param.adt.mortuary.dao;

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
import com.param.adt.master.unit.model.MortuaryBedLogStatus;
import com.param.adt.mortuary.model.MortuaryRequest;
import com.param.global.common.Response;
import com.param.global.dto.MortuaryDto;
import com.param.global.model.DeathRegistration;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MortuaryDaoImpl extends GenericDao<DeathRegistration, Integer> implements IMortuaryDao, ICommonConstants {

	public MortuaryDaoImpl() {
		super(DeathRegistration.class);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	DozerBeanMapper mapper;

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Response saveDeathRegistation(MortuaryDto mortuaryDto) {
		try {
			DeathRegistration dethRegistration = mapper.map(mortuaryDto, DeathRegistration.class,
					"MortuaryDto_to_DeathRegistration");
			dethRegistration = save(dethRegistration);

			mortuaryDto.setDethRegistrationId(dethRegistration.getDethRegistrationId());
			mortuaryDto.setdPatientId(dethRegistration.getDethRegistrationId());
			return new Response(SUCCESS, null, COMMON_SUCCESS, null, mortuaryDto);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
		}
	}

	@Override
	public Response saveMortuaryRequest(MortuaryDto mortuaryDto) {
		try {
			Session session = sessionFactory.openSession();
			Transaction tr=session.beginTransaction();
			MortuaryRequest mortuaryRequest = new MortuaryRequest();
				mortuaryRequest.setOrganizationId(mortuaryDto.getOrganizationId());
				mortuaryRequest.setUnitId(mortuaryDto.getUnitId());
				mortuaryRequest.setAdmissionId(mortuaryDto.getAdmissionId());
				mortuaryRequest.settPatientId(mortuaryDto.gettPatientId());
				mortuaryRequest.setdPatientId(mortuaryDto.getdPatientId());
				mortuaryRequest.setPatientId(mortuaryDto.getPatientId());
				mortuaryRequest.setVisitTypeId(mortuaryDto.getVisitTypeId());
				mortuaryRequest.setDurationFormat(mortuaryDto.getDurationFormat());
				mortuaryRequest.setDurationValue(mortuaryDto.getDurationValue());
				mortuaryRequest.setMortuaryStatusId(mortuaryDto.getMortuaryStatusId());
				if(mortuaryDto.getExpectedArrivalDate()!=null)
					mortuaryRequest.setExpectedArrivalDate(ADTCommonDateUtils.getDate(mortuaryDto.getExpectedArrivalDate(),"dd-M-yyyy HH:mm:ss"));
				mortuaryRequest.setExpectedArrivalTime(mortuaryDto.getExpectedArrivalTime());
				mortuaryRequest.setStatus(mortuaryDto.getStatus());
				mortuaryRequest.setCreatedBy(mortuaryDto.getCreatedBy());
				mortuaryRequest.setUpdatedBy(mortuaryDto.getUpdatedBy());
				mortuaryRequest.setCreatedDate(ADTCommonDateUtils.getDate(mortuaryDto.getCreatedDate(),"dd-M-yyyy HH:mm:ss"));
				mortuaryRequest.setUpdatedDate(ADTCommonDateUtils.getDate(mortuaryDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
			session.save(mortuaryRequest);
			tr.commit();
			return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
		}
	}

	@Override
	public Response getPendingMortuaryRequestList(MortuaryDto mortuaryDto) throws ApplicationException {
		try{
			List<MortuaryDto> pendingMortuaryRequestList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PENDING_MORTUARY_REQUEST_LIST")
					.setInteger("orgId", mortuaryDto.getOrganizationId())
					.setInteger("unitId", mortuaryDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(MortuaryDto.class)).list();
			return new Response(SUCCESS, null, null, pendingMortuaryRequestList, null);
			} catch (HibernateException he) {
				he.printStackTrace();

			} catch (Exception e) {
				e.printStackTrace();
			} 
			return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response acceptRejectMortuaryRequestFromPendingList(MortuaryDto mortuaryDto) throws ApplicationException {
		try{
			Session session=sessionFactory.openSession();
			Query query=session.createQuery("update MortuaryRequest mor set mor.mortuaryStatusId="+mortuaryDto.getMortuaryStatusId()+", "
					+ " mor.updatedBy = "+mortuaryDto.getUpdatedBy()+", "
					+ " mor.updatedDate = '"+ADTCommonDateUtils.getDate(mortuaryDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss")+"' "
					+ " WHERE mor.organizationId="+mortuaryDto.getOrganizationId() 
					+ " AND mor.unitId="+mortuaryDto.getUnitId()
					+ " AND mor.mortuaryRequestId="+mortuaryDto.getMortuaryRequestId()
					+ " AND mor.status='A' ");
			query.executeUpdate();
			return new Response(SUCCESS, null, null, null, null);
			} catch (HibernateException he) {
				he.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} 
			return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getReservedMortuaryRequestList(MortuaryDto mortuaryDto) throws ApplicationException {
		try{
			List<MortuaryDto> pendingMortuaryRequestList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_RESERVED_MORTUARY_REQUEST_LIST")
					.setInteger("orgId", mortuaryDto.getOrganizationId())
					.setInteger("unitId", mortuaryDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(MortuaryDto.class)).list();
			return new Response(SUCCESS, null, null, pendingMortuaryRequestList, null);
			} catch (HibernateException he) {
				he.printStackTrace();

			} catch (Exception e) {
				e.printStackTrace();
			} 
			return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getMorturyBedList(MortuaryDto mortuaryDto) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response saveBedAllocationMortuary(MortuaryDto mortuaryDto) throws ApplicationException {
		try {
			Session session=sessionFactory.openSession();
			//<-----updating mortuary bed request table
			Query query=session.createQuery("update MortuaryRequest mbm set "
					+ "mbm.updatedBy="+mortuaryDto.getUpdatedBy()+", "
					+ "mbm.durationFormat="+mortuaryDto.getDurationFormat()+", "
					+ "mbm.durationValue="+mortuaryDto.getDurationValue()+", "
					+ "mbm.mortuaryStatusId="+mortuaryDto.getMortuaryStatusId()+", "
					+ "mbm.mortuaryBedId="+mortuaryDto.getMortuaryBedId()+", "
					+ "mbm.toeBandId='"+mortuaryDto.getToeBandId()+"', "
					+ "mbm.admissionKinId="+mortuaryDto.getAdmissionKinId()+", "
					+ "mbm.updatedDate='"+ADTCommonDateUtils.getDate(mortuaryDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss")+"' "
					+ " WHERE mbm.mortuaryRequestId="+mortuaryDto.getMortuaryRequestId()+" "
					+ " AND mbm.organizationId="+mortuaryDto.getOrganizationId()+" "
					+ " AND mbm.unitId="+mortuaryDto.getUnitId());
			query.executeUpdate();
			//----->
			
			//<----- Adding new entry in MortuaryBedLogStatus be deactivating previous record
			Query query2=session.createQuery("update MortuaryBedLogStatus mbls set "
					+ "mbls.status='I', "
					+ "mbls.updatedBy="+mortuaryDto.getUpdatedBy()+", "
					+ "mbls.updatedDate='"+ADTCommonDateUtils.getDate(mortuaryDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss")+"' "
					+ " WHERE mbls.mortuaryBedId="+mortuaryDto.getMortuaryBedId()+" "
					+ " AND mbls.organizationId="+mortuaryDto.getOrganizationId()+" "
					+ " AND mbls.unitId="+mortuaryDto.getUnitId()+" "
					+ " AND mbls.status='A' ");
			query2.executeUpdate();
			
			Transaction tr=session.beginTransaction();
			MortuaryBedLogStatus mortuaryBedLogStatus=new MortuaryBedLogStatus();
				mortuaryBedLogStatus.setMortuaryBedId(mortuaryDto.getMortuaryBedId());
				mortuaryBedLogStatus.setOrganizationId(mortuaryDto.getOrganizationId());
				mortuaryBedLogStatus.setUnitId(mortuaryDto.getUnitId());
				mortuaryBedLogStatus.setBedStatusId(mortuaryDto.getBedStatusId());
				mortuaryBedLogStatus.setCreatedBy(mortuaryDto.getUpdatedBy());
				mortuaryBedLogStatus.setUpdatedBy(mortuaryDto.getUpdatedBy());
				mortuaryBedLogStatus.setCreatedDate(ADTCommonDateUtils.getDate(mortuaryDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
				mortuaryBedLogStatus.setUpdatedDate(ADTCommonDateUtils.getDate(mortuaryDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
				mortuaryBedLogStatus.setStatus('A');
			session.save(mortuaryBedLogStatus);
			tr.commit();
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
	public Response getAdmittedMortuaryList(MortuaryDto mortuaryDto) throws ApplicationException {
		try{
			List<MortuaryDto> pendingMortuaryRequestList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ADMITTED_MORTUARY_LIST")
					.setInteger("orgId", mortuaryDto.getOrganizationId())
					.setInteger("unitId", mortuaryDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(MortuaryDto.class)).list();
			return new Response(SUCCESS, null, null, pendingMortuaryRequestList, null);
			} catch (HibernateException he) {
				he.printStackTrace();

			} catch (Exception e) {
				e.printStackTrace();
			} 
			return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response releaseMorturyBed(MortuaryDto mortuaryDto) throws ApplicationException 
	{
		try{
		Session session=sessionFactory.openSession();
		//<-----updating Mortuary bed request table
		Query query=session.createQuery("update MortuaryRequest mbm set mbm.status='I', "
				+ " mbm.releaseNote='"+mortuaryDto.getReleaseNote()+"', "
				+ " mbm.outTime='"+ADTCommonDateUtils.getDate(mortuaryDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss")+"', "
				+ " mbm.releaseKinId="+mortuaryDto.getReleaseKinId()+", "
				+ " mbm.updatedBy="+mortuaryDto.getUpdatedBy()+", "
				+ " mbm.mortuaryStatusId="+mortuaryDto.getMortuaryStatusId()+", "
				+ " mbm.updatedDate='"+ADTCommonDateUtils.getDate(mortuaryDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss")+"' "
				+ " WHERE mbm.mortuaryRequestId="+mortuaryDto.getMortuaryRequestId()+" "
				+ " AND mbm.organizationId="+mortuaryDto.getOrganizationId()+" "
				+ " AND mbm.unitId="+mortuaryDto.getUnitId()+" "
				+ " AND mbm.status='A' ");
		query.executeUpdate();
		//----->
		
		//<----- Adding new entry in MortuaryBedLogStatus by deactivating previous record
		Query query2=session.createQuery("update MortuaryBedLogStatus mbls set "
				+ "mbls.status='I', "
				+ "mbls.updatedBy="+mortuaryDto.getUpdatedBy()+", "
				+ "mbls.updatedDate='"+ADTCommonDateUtils.getDate(mortuaryDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss")+"' "
				+ " WHERE mbls.mortuaryBedId="+mortuaryDto.getMortuaryBedId()+" "
				+ " AND mbls.organizationId="+mortuaryDto.getOrganizationId()+" "
				+ " AND mbls.unitId="+mortuaryDto.getUnitId()+" "
				+ " AND mbls.status='A' ");
		query2.executeUpdate();
		
		Transaction tr=session.beginTransaction();
		MortuaryBedLogStatus mortuaryBedLogStatus=new MortuaryBedLogStatus();
			mortuaryBedLogStatus.setMortuaryBedId(mortuaryDto.getMortuaryBedId());
			mortuaryBedLogStatus.setOrganizationId(mortuaryDto.getOrganizationId());
			mortuaryBedLogStatus.setUnitId(mortuaryDto.getUnitId());
			mortuaryBedLogStatus.setBedStatusId(6);
			mortuaryBedLogStatus.setCreatedBy(mortuaryDto.getUpdatedBy());
			mortuaryBedLogStatus.setUpdatedBy(mortuaryDto.getUpdatedBy());
			mortuaryBedLogStatus.setCreatedDate(ADTCommonDateUtils.getDate(mortuaryDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
			mortuaryBedLogStatus.setUpdatedDate(ADTCommonDateUtils.getDate(mortuaryDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
			mortuaryBedLogStatus.setStatus('A');
		session.save(mortuaryBedLogStatus);
		tr.commit();
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
	public Response getDataForMorturyMapOfBed(MortuaryDto mortuaryDto) throws ApplicationException {
		try{
			List<MortuaryDto> dataForMorturyMapOfBedsList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_DATA_FOR_MORTURY_MAP_OF_BED")
					.setInteger("orgId", mortuaryDto.getOrganizationId())
					.setInteger("unitId", mortuaryDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(MortuaryDto.class)).list();
			return new Response(SUCCESS, null, null, dataForMorturyMapOfBedsList, null);
			} catch (HibernateException he) {
				he.printStackTrace();

			} catch (Exception e) {
				e.printStackTrace();
			} 
			return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response cancelMorgueRequest(MortuaryDto mortuaryDto) throws ApplicationException {
		try {
			Session session=sessionFactory.openSession();
			//<-----updating Mortuary bed request table
			Query query=session.createQuery("update MortuaryRequest mbm set "
					+ "mbm.status='I', "
					+ "mbm.rejectReasonId="+mortuaryDto.getRejectReasonId()+", "
					+ "mbm.rejectionNote='"+mortuaryDto.getRejectionNote()+"', "
					+ "mbm.updatedBy="+mortuaryDto.getUpdatedBy()+", "
					+ "mbm.mortuaryStatusId=5, "
					+ "mbm.updatedDate='"+ADTCommonDateUtils.getDate(mortuaryDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss")+"' "
					+ " WHERE mbm.mortuaryRequestId="+mortuaryDto.getMortuaryRequestId()+" "
					+ " AND mbm.organizationId="+mortuaryDto.getOrganizationId()+" "
					+ " AND mbm.unitId="+mortuaryDto.getUnitId());
			query.executeUpdate();
			//----->
			if(mortuaryDto.getMortuaryBedId()!=null)
			{
			//<----- Adding new entry in MortuaryBedLogStatus be deactivating previous record
			Query query2=session.createQuery("update MortuaryBedLogStatus mbls set "
					+ "mbls.status='I', "
					+ "mbls.updatedBy="+mortuaryDto.getUpdatedBy()+", "
					+ "mbls.updatedDate='"+ADTCommonDateUtils.getDate(mortuaryDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss")+"' "
					+ " WHERE mbls.mortuaryBedId="+mortuaryDto.getMortuaryBedId()+" "
					+ " AND mbls.organizationId="+mortuaryDto.getOrganizationId()+" "
					+ " AND mbls.unitId="+mortuaryDto.getUnitId()+" "
					+ " AND mbls.status='A' ");
			query2.executeUpdate();
			
			Transaction tr=session.beginTransaction();
			MortuaryBedLogStatus mortuaryBedLogStatus=new MortuaryBedLogStatus();
				mortuaryBedLogStatus.setMortuaryBedId(mortuaryDto.getMortuaryBedId());
				mortuaryBedLogStatus.setOrganizationId(mortuaryDto.getOrganizationId());
				mortuaryBedLogStatus.setUnitId(mortuaryDto.getUnitId());
				mortuaryBedLogStatus.setBedStatusId(6);
				mortuaryBedLogStatus.setCreatedBy(mortuaryDto.getUpdatedBy());
				mortuaryBedLogStatus.setUpdatedBy(mortuaryDto.getUpdatedBy());
				mortuaryBedLogStatus.setCreatedDate(ADTCommonDateUtils.getDate(mortuaryDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
				mortuaryBedLogStatus.setUpdatedDate(ADTCommonDateUtils.getDate(mortuaryDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
				mortuaryBedLogStatus.setStatus('A');
			session.save(mortuaryBedLogStatus);
			tr.commit();
			//----->
			}
			return new Response(SUCCESS, null, null, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

}