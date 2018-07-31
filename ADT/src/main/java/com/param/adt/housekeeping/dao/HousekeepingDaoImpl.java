package com.param.adt.housekeeping.dao;

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
import com.param.adt.housekeeping.model.Housekeeping;
import com.param.adt.housekeeping.model.HousekeepingLog;
import com.param.adt.master.global.dto.BedMasterDto;
import com.param.adt.master.global.dto.UserMasterDto;
import com.param.adt.master.global.model.BedLogStatus;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({"rawtypes","unchecked"})
public class HousekeepingDaoImpl extends GenericDao<Housekeeping, Integer> implements IHousekeepingDao,ICommonConstants{

	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	DozerBeanMapper mapper;
	
	public HousekeepingDaoImpl() {
		super(Housekeeping.class);
		// TODO Auto-generated constructor stub
	}


	@Override
	public Response getBedsForHousekeepingList(BedMasterDto bedMasterDto) {
		try {
			List<BedMasterDto> bedMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_BEDS_FOR_HOUSEKEEPING_LIST")
					.setInteger("organizationId", bedMasterDto.getOrganizationId())
					.setInteger("unitId", bedMasterDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean( BedMasterDto.class)).list();
			return new Response(SUCCESS, null, null, bedMasterDtosList, null);
		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}


	@Override
	public Response getActiveUserList(BedMasterDto bedMasterDto) throws ApplicationException {
		try {
			List<UserMasterDto> userMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_USERS_LIST")
					.setInteger("organizationId", bedMasterDto.getOrganizationId())
					.setInteger("unitId", bedMasterDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(UserMasterDto.class)).list();
			return new Response(SUCCESS, null, null, userMasterDtosList, null);
		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}


	@Override
	public Response assignUserForCleaning(BedMasterDto bedMasterDto) throws ApplicationException {
		try {
			Session session=sessionFactory.openSession();
			//<------ Updating housekeepingStatusId=2(Assigned) in Housekeeping	
			Query query=session.createQuery("update Housekeeping hskp set hskp.assignedPersonId="+bedMasterDto.getAssignedPersonId()+", "
						+ "hskp.housekeepingStatusId=2, "
						+ "updatedBy="+bedMasterDto.getUpdatedBy()+", "
						+ "updatedDate='"+ADTCommonDateUtils.getDate(bedMasterDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss")+"' "
						+ " WHERE hskp.housekeepingId="+bedMasterDto.getHousekeepingId()+" "
						+ " AND hskp.organizationId="+bedMasterDto.getOrganizationId()+" "
						+ " AND hskp.unitId="+bedMasterDto.getUnitId());
			query.executeUpdate();
			//----->	
			
			
			Query query2=session.createQuery("update HousekeepingLog hskp set "
					+ "hskp.status='I', "
					+ "updatedBy="+bedMasterDto.getUpdatedBy()+", "
					+ "updatedDate='"+ADTCommonDateUtils.getDate(bedMasterDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss")+"' "
					+ " WHERE hskp.housekeepingId="+bedMasterDto.getHousekeepingId()+" "
					+ " AND hskp.organizationId="+bedMasterDto.getOrganizationId()+" "
					+ " AND hskp.unitId="+bedMasterDto.getUnitId()+" "
					+ " AND hskp.status='A' ");
			query2.executeUpdate();
			
			Transaction tr=session.beginTransaction();
			HousekeepingLog housekeepingLog=new HousekeepingLog();
				housekeepingLog.setHousekeepingId(bedMasterDto.getHousekeepingId());
				housekeepingLog.setOrganizationId(bedMasterDto.getOrganizationId());
				housekeepingLog.setUnitId(bedMasterDto.getUnitId());
				housekeepingLog.setPriorityId(bedMasterDto.getPriorityId());
				housekeepingLog.setAssignedPersonId(bedMasterDto.getAssignedPersonId());
				housekeepingLog.setHousekeepingStatusId(2);
				housekeepingLog.setCreatedBy(bedMasterDto.getUpdatedBy());
				housekeepingLog.setUpdatedBy(bedMasterDto.getUpdatedBy());
				housekeepingLog.setCreatedDate(ADTCommonDateUtils.getDate(bedMasterDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
				housekeepingLog.setUpdatedDate(ADTCommonDateUtils.getDate(bedMasterDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
				housekeepingLog.setStatus('A');
			session.save(housekeepingLog);
			tr.commit();
			return new Response(SUCCESS, null, null, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}


	@Override
	public Response markAsCleaned(BedMasterDto bedMasterDto) throws ApplicationException {
		try {
			Session session=sessionFactory.openSession();
			//<------ Updating housekeepingStatusId=2(Assigned) in Housekeeping	
			Query query=session.createQuery("update Housekeeping hskp set "
						+ "hskp.housekeepingStatusId=3, "
						+ "updatedBy="+bedMasterDto.getUpdatedBy()+", "
						+ "updatedDate='"+ADTCommonDateUtils.getDate(bedMasterDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss")+"' "
						+ " WHERE hskp.housekeepingId="+bedMasterDto.getHousekeepingId()+" "
						+ " AND hskp.organizationId="+bedMasterDto.getOrganizationId()+" "
						+ " AND hskp.unitId="+bedMasterDto.getUnitId());
			query.executeUpdate();
			//----->	
			
			//<------ In-activating previous active entry
			Query query2=session.createQuery("update HousekeepingLog hskp set "
					+ "hskp.status='I', "
					+ "updatedBy="+bedMasterDto.getUpdatedBy()+", "
					+ "updatedDate='"+ADTCommonDateUtils.getDate(bedMasterDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss")+"' "
					+ " WHERE hskp.housekeepingId="+bedMasterDto.getHousekeepingId()+" "
					+ " AND hskp.organizationId="+bedMasterDto.getOrganizationId()+" "
					+ " AND hskp.unitId="+bedMasterDto.getUnitId()+" "
					+ " AND hskp.status='A' ");
			query2.executeUpdate();
			//----->
			Transaction tr=session.beginTransaction();
			HousekeepingLog housekeepingLog=new HousekeepingLog();
				housekeepingLog.setHousekeepingId(bedMasterDto.getHousekeepingId());
				housekeepingLog.setOrganizationId(bedMasterDto.getOrganizationId());
				housekeepingLog.setUnitId(bedMasterDto.getUnitId());
				housekeepingLog.setPriorityId(bedMasterDto.getPriorityId());
				housekeepingLog.setAssignedPersonId(bedMasterDto.getAssignedPersonId());
				housekeepingLog.setHousekeepingStatusId(3);
				housekeepingLog.setCreatedBy(bedMasterDto.getUpdatedBy());
				housekeepingLog.setUpdatedBy(bedMasterDto.getUpdatedBy());
				housekeepingLog.setCreatedDate(ADTCommonDateUtils.getDate(bedMasterDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
				housekeepingLog.setUpdatedDate(ADTCommonDateUtils.getDate(bedMasterDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
				housekeepingLog.setStatus('A');
			session.save(housekeepingLog);
			tr.commit();
			//<-----Changing status of bed to Booked (6 is the ID of 'Vacant'status)
			Query qry2 = session.createQuery("update BedMaster bed set bed.bedStatusId=6, "
					+ "bed.updatedBy=" + bedMasterDto.getUpdatedBy() + ", "
					+ "bed.updatedDate='"+ ADTCommonDateUtils.getDate(bedMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss") + "'"
					+ "WHERE bed.bedId=" + bedMasterDto.getBedId()
					+ "AND bed.organizationId="+bedMasterDto.getOrganizationId()
					+ "AND bed.unitId="+bedMasterDto.getUnitId()
					+ "AND bed.status='A' ");

			qry2.executeUpdate();
			// ----->

			// <-----Updating status of current bed to I in BedLogStatus
			Query qry3 = session.createQuery("update BedLogStatus bedStatus set bedStatus.status='I', "
					+ "bedStatus.updatedBy=" + bedMasterDto.getUpdatedBy() + ", "
					+ "bedStatus.updatedDate='"	+ ADTCommonDateUtils.getDate(bedMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss") + "'"
					+ " WHERE bedStatus.status='A' "
					+ " AND bedStatus.bedId=" + bedMasterDto.getBedId()
					+ " AND bedStatus.organizationId="+bedMasterDto.getOrganizationId()
					+ " AND bedStatus.unitId="+bedMasterDto.getUnitId());
			qry3.executeUpdate();
			// ----->

			// <-----Adding new entry in log as the status of bed is changing
			Transaction tr2 = session.beginTransaction();
			BedLogStatus bedLogStatus =new BedLogStatus();
				bedLogStatus.setBedId(bedMasterDto.getBedId());
				bedLogStatus.setBedStatusId(6);
				bedLogStatus.setOrganizationId(bedMasterDto.getOrganizationId());
				bedLogStatus.setUnitId(bedMasterDto.getUnitId());
				bedLogStatus.setStatus('A');
				bedLogStatus.setCreatedBy(bedMasterDto.getUpdatedBy());
				bedLogStatus.setUpdatedBy(bedMasterDto.getUpdatedBy());
				bedLogStatus.setCreatedDate(ADTCommonDateUtils.getDate(bedMasterDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
				bedLogStatus.setUpdatedDate(ADTCommonDateUtils.getDate(bedMasterDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
			session.save(bedLogStatus);
			tr2.commit();
			// ----->
			
			return new Response(SUCCESS, null, null, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}


	@Override
	public Response getListOfVacantBedsForNursing(BedMasterDto bedMasterDto) throws ApplicationException {
		try {
			List<BedMasterDto> bedMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("LIST_OF_VACANT_BEDS_FOR_NURSING")
					.setInteger("organizationId", bedMasterDto.getOrganizationId())
					.setInteger("unitId", bedMasterDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(BedMasterDto.class)).list();
			return new Response(SUCCESS, null, null, bedMasterDtosList, null);
		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}


	@Override
	public Response acceptRequestForNursing(BedMasterDto bedMasterDto) throws ApplicationException {
		try {
			Session session=sessionFactory.openSession();
			
			//<------ Updating housekeepingStatusId=4(Accepted By Nursing) in Housekeeping	
			Query query=session.createQuery("update Housekeeping hskp set hskp.status='I', "
						+ "hskp.housekeepingStatusId=4, "
						+ "hskp.updatedBy="+bedMasterDto.getUpdatedBy()+", "
						+ "hskp.updatedDate='"+ADTCommonDateUtils.getDate(bedMasterDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss")+"' "
						+ " WHERE hskp.housekeepingId="+bedMasterDto.getHousekeepingId()+" "
						+ " AND hskp.organizationId="+bedMasterDto.getOrganizationId()+" "
						+ " AND hskp.unitId="+bedMasterDto.getUnitId());
			query.executeUpdate();
			//----->	
			
			//<------ In-activating previous active entry
			Query query2=session.createQuery("update HousekeepingLog hskp set "
					+ "hskp.housekeepingStatusId=4, "
					+ "hskp.status='I', "
					+ "hskp.note='"+bedMasterDto.getNote()+"', "
					+ "updatedBy="+bedMasterDto.getUpdatedBy()+", "
					+ "updatedDate='"+ADTCommonDateUtils.getDate(bedMasterDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss")+"' "
					+ " WHERE hskp.housekeepingId="+bedMasterDto.getHousekeepingId()+" "
					+ " AND hskp.organizationId="+bedMasterDto.getOrganizationId()+" "
					+ " AND hskp.unitId="+bedMasterDto.getUnitId()+" "
					+ " AND hskp.status='A' ");
			query2.executeUpdate();
			//----->
			
			//<-----Changing status of bed to Booked (3 is the ID of 'Vacant and Ready to Receive Patient 'status)
			Query qry2 = session.createQuery("update BedMaster bed set bed.bedStatusId=3, "
					+ "bed.updatedBy=" + bedMasterDto.getUpdatedBy() + ", "
					+ "bed.updatedDate='"+ ADTCommonDateUtils.getDate(bedMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss") + "'"
					+ "WHERE bed.bedId=" + bedMasterDto.getBedId()
					+ "AND bed.organizationId="+bedMasterDto.getOrganizationId()
					+ "AND bed.unitId="+bedMasterDto.getUnitId()
					+ "AND bed.status='A' ");

			qry2.executeUpdate();
			// ----->

			// <-----Updating status of current bed to I in BedLogStatus
			Query qry3 = session.createQuery("update BedLogStatus bedStatus set bedStatus.status='I', "
					+ "bedStatus.updatedBy=" + bedMasterDto.getUpdatedBy() + ", "
					+ "bedStatus.updatedDate='"	+ ADTCommonDateUtils.getDate(bedMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss") + "'"
					+ " WHERE bedStatus.status='A' "
					+ " AND bedStatus.bedId=" + bedMasterDto.getBedId()
					+ " AND bedStatus.organizationId="+bedMasterDto.getOrganizationId()
					+ " AND bedStatus.unitId="+bedMasterDto.getUnitId());
			qry3.executeUpdate();
			// ----->

			// <-----Adding new entry in log as the status of bed is changing
			Transaction tr2 = session.beginTransaction();
			BedLogStatus bedLogStatus =new BedLogStatus();
				bedLogStatus.setBedId(bedMasterDto.getBedId());
				bedLogStatus.setBedStatusId(3);
				bedLogStatus.setOrganizationId(bedMasterDto.getOrganizationId());
				bedLogStatus.setUnitId(bedMasterDto.getUnitId());
				bedLogStatus.setStatus('A');
				bedLogStatus.setCreatedBy(bedMasterDto.getUpdatedBy());
				bedLogStatus.setUpdatedBy(bedMasterDto.getUpdatedBy());
				bedLogStatus.setCreatedDate(ADTCommonDateUtils.getDate(bedMasterDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
				bedLogStatus.setUpdatedDate(ADTCommonDateUtils.getDate(bedMasterDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
			session.save(bedLogStatus);
			tr2.commit();
			// ----->
			
			return new Response(SUCCESS, null, null, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}


	@Override
	public Response rejectRequestForNursing(BedMasterDto bedMasterDto) throws ApplicationException {
		try {
			Session session=sessionFactory.openSession();
			
			//<------ Updating housekeepingStatusId=5(Rejected By Nursing) in Housekeeping	
			Query query=session.createQuery("update Housekeeping hskp set "
						+ "hskp.housekeepingStatusId=5, "
						+ "hskp.updatedBy="+bedMasterDto.getUpdatedBy()+", "
						+ "hskp.updatedDate='"+ADTCommonDateUtils.getDate(bedMasterDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss")+"' "
						+ " WHERE hskp.housekeepingId="+bedMasterDto.getHousekeepingId()+" "
						+ " AND hskp.organizationId="+bedMasterDto.getOrganizationId()+" "
						+ " AND hskp.unitId="+bedMasterDto.getUnitId());
			query.executeUpdate();
			//----->	
			
			//<------ In-activating previous active entry
			Query query2=session.createQuery("update HousekeepingLog hskp set "
					+ "hskp.status='I', "
					+ "updatedBy="+bedMasterDto.getUpdatedBy()+", "
					+ "updatedDate='"+ADTCommonDateUtils.getDate(bedMasterDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss")+"' "
					+ " WHERE hskp.housekeepingId="+bedMasterDto.getHousekeepingId()+" "
					+ " AND hskp.organizationId="+bedMasterDto.getOrganizationId()+" "
					+ " AND hskp.unitId="+bedMasterDto.getUnitId()+" "
					+ " AND hskp.status='A' ");
			query2.executeUpdate();
			//----->
			
			
			Transaction tr=session.beginTransaction();
			HousekeepingLog housekeepingLog=new HousekeepingLog();
				housekeepingLog.setHousekeepingId(bedMasterDto.getHousekeepingId());
				housekeepingLog.setOrganizationId(bedMasterDto.getOrganizationId());
				housekeepingLog.setUnitId(bedMasterDto.getUnitId());
				housekeepingLog.setPriorityId(bedMasterDto.getPriorityId());
				housekeepingLog.setAssignedPersonId(bedMasterDto.getAssignedPersonId());
				housekeepingLog.setHousekeepingStatusId(5);
				housekeepingLog.setNote(bedMasterDto.getNote());
				housekeepingLog.setRejectionReasonId(bedMasterDto.getRejectionReasonId());
				housekeepingLog.setCreatedBy(bedMasterDto.getUpdatedBy());
				housekeepingLog.setUpdatedBy(bedMasterDto.getUpdatedBy());
				housekeepingLog.setCreatedDate(ADTCommonDateUtils.getDate(bedMasterDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
				housekeepingLog.setUpdatedDate(ADTCommonDateUtils.getDate(bedMasterDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
				housekeepingLog.setStatus('A');
			session.save(housekeepingLog);
			tr.commit();

			
			//<-----Changing status of bed to Booked (2 is the ID of 'Housekeeping'status)
			Query qry2 = session.createQuery("update BedMaster bed set bed.bedStatusId=2, "
					+ "bed.updatedBy=" + bedMasterDto.getUpdatedBy() + ", "
					+ "bed.updatedDate='"+ ADTCommonDateUtils.getDate(bedMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss") + "'"
					+ "WHERE bed.bedId=" + bedMasterDto.getBedId()
					+ "AND bed.organizationId="+bedMasterDto.getOrganizationId()
					+ "AND bed.unitId="+bedMasterDto.getUnitId()
					+ "AND bed.status='A' ");

			qry2.executeUpdate();
			// ----->
			
			
			// <-----Updating status of current bed to I in BedLogStatus
			Query qry3 = session.createQuery("update BedLogStatus bedStatus set bedStatus.status='I', "
					+ "bedStatus.updatedBy=" + bedMasterDto.getUpdatedBy() + ", "
					+ "bedStatus.updatedDate='"	+ ADTCommonDateUtils.getDate(bedMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss") + "'"
					+ " WHERE bedStatus.status='A' "
					+ " AND bedStatus.bedId=" + bedMasterDto.getBedId()
					+ " AND bedStatus.organizationId="+bedMasterDto.getOrganizationId()
					+ " AND bedStatus.unitId="+bedMasterDto.getUnitId());
			qry3.executeUpdate();
			// ----->

			// <-----Adding new entry in log as the status of bed is changing
			Transaction tr2 = session.beginTransaction();
			BedLogStatus bedLogStatus =new BedLogStatus();
				bedLogStatus.setBedId(bedMasterDto.getBedId());
				bedLogStatus.setBedStatusId(2);
				bedLogStatus.setOrganizationId(bedMasterDto.getOrganizationId());
				bedLogStatus.setUnitId(bedMasterDto.getUnitId());
				bedLogStatus.setStatus('A');
				bedLogStatus.setCreatedBy(bedMasterDto.getUpdatedBy());
				bedLogStatus.setUpdatedBy(bedMasterDto.getUpdatedBy());
				bedLogStatus.setCreatedDate(ADTCommonDateUtils.getDate(bedMasterDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
				bedLogStatus.setUpdatedDate(ADTCommonDateUtils.getDate(bedMasterDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
			session.save(bedLogStatus);
			tr2.commit();
			// ----->
			
			return new Response(SUCCESS, null, null, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}


	@Override
	public Response searchBedByMultipleCriteriaForHousekeeping(BedMasterDto bedMasterDto) throws ApplicationException {
		try {
			Session sesison=sessionFactory.openSession();
			StringBuilder query=new StringBuilder(sesison.getNamedQuery("GET_BEDS_FOR_HOUSEKEEPING_LIST").getQueryString());
			
			StringBuilder finalStringQuery=generateQueryForHousekeeping(bedMasterDto,query);
			
			Query finalQuery= sesison.createQuery(finalStringQuery.toString());
			
			List<BedMasterDto> bedMasterDtosList = finalQuery
					.setInteger("organizationId", bedMasterDto.getOrganizationId())
					.setInteger("unitId", bedMasterDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(BedMasterDto.class)).list();
			return new Response(SUCCESS, null, null, bedMasterDtosList, null);
		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}
	
	private StringBuilder generateQueryForHousekeeping(BedMasterDto bedMasterDto,StringBuilder query)
	{
		
		StringBuilder condition=new StringBuilder();
		
		if(bedMasterDto.getFloorId()>0)
		{
			condition.append(" AND bm.floorId="+bedMasterDto.getFloorId());
		}
		if(bedMasterDto.getWardId()>0)
		{
			condition.append(" AND bm.wardId="+bedMasterDto.getWardId());
		}
		if(bedMasterDto.getNursingStationId()>0)
		{
			condition.append(" AND nurward.nursingStationId="+bedMasterDto.getNursingStationId());
		}
		if(bedMasterDto.getBedCategoryId()>0)
		{
			condition.append(" AND bm.bedCategoryId="+bedMasterDto.getBedCategoryId());
		}
		if(bedMasterDto.getHousekeepingStatusId()>0)
		{
			condition.append(" AND hskp.housekeepingStatusId="+bedMasterDto.getHousekeepingStatusId());
		}
		return query.append(condition);
	}


	@Override
	public Response searchBedByMultipleCriteriaForNursing(BedMasterDto bedMasterDto) throws ApplicationException {
		try {
			Session sesison=sessionFactory.openSession();
			StringBuilder query=new StringBuilder(sesison.getNamedQuery("LIST_OF_VACANT_BEDS_FOR_NURSING").getQueryString());
			
			StringBuilder finalStringQuery=generateQueryForNursing(bedMasterDto,query);
			
			Query finalQuery= sesison.createQuery(finalStringQuery.toString());
			
			List<BedMasterDto> bedMasterDtosList = finalQuery
					.setInteger("organizationId", bedMasterDto.getOrganizationId())
					.setInteger("unitId", bedMasterDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(BedMasterDto.class)).list();
			return new Response(SUCCESS, null, null, bedMasterDtosList, null);
		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}
	
	private StringBuilder generateQueryForNursing(BedMasterDto bedMasterDto,StringBuilder query)
	{
		
		StringBuilder condition=new StringBuilder();
		
		if(bedMasterDto.getFloorId()>0)
		{
			condition.append(" AND bm.floorId="+bedMasterDto.getFloorId());
		}
		if(bedMasterDto.getWardId()>0)
		{
			condition.append(" AND bm.wardId="+bedMasterDto.getWardId());
		}
		if(bedMasterDto.getNursingStationId()>0)
		{
			condition.append(" AND nurward.nursingStationId="+bedMasterDto.getNursingStationId());
		}
		if(bedMasterDto.getBedCategoryId()>0)
		{
			condition.append(" AND bm.bedCategoryId="+bedMasterDto.getBedCategoryId());
		}
		return query.append(condition);
	}
}
