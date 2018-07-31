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

import com.param.adt.admission.dto.BedAllocationDto;
import com.param.adt.admission.model.Admission;
import com.param.adt.admission.model.AdmissionDetails;
import com.param.adt.admission.model.AdmissionPatientMapper;
import com.param.adt.common.ADTCommonDateUtils;
import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.global.dto.BedCategoryMasterDto;
import com.param.adt.master.global.dto.BedMasterDto;
import com.param.adt.master.global.dto.BedStatusMasterDto;
import com.param.adt.master.global.model.BedCategoryWaitingList;
import com.param.adt.master.global.model.BedLogStatus;
import com.param.adt.master.unit.dto.BillingBedCategoryMasterDto;
import com.param.adt.master.unit.dto.MealPreferenceMasterDto;
import com.param.adt.transfer.dto.TransferDto;
import com.param.adt.transfer.model.Transfer;
import com.param.global.dto.AdmissionDto;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class BedAllocationDaoImpl extends GenericDao<AdmissionDetails, Integer>
		implements IBedAllocationDao, ICommonConstants {

	public BedAllocationDaoImpl() {
		super(AdmissionDetails.class);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DozerBeanMapper mapper;

	@Override
	public Response getActiveBedList(BedMasterDto bedMasterDto) throws ApplicationException {
		try {
			List<BedMasterDto> bedMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_BED_LIST").setInteger("organizationId", bedMasterDto.getOrganizationId())
					.setInteger("unitId", bedMasterDto.getUnitId())
					.setInteger("bedCategoryId", bedMasterDto.getBedCategoryId())

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
	public Response getActiveFloorMasterList(BedAllocationDto bedAllocationDto) throws ApplicationException {
		try {
			List<BedAllocationDto> bedAllocationDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_FLOOR_LIST")
					.setInteger("organizationId", bedAllocationDto.getOrganizationId())
					.setInteger("unitId", bedAllocationDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(BedAllocationDto.class)).list();
			return new Response(SUCCESS, null, null, bedAllocationDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getWardListByFloorId(BedAllocationDto bedAllocationDto) throws ApplicationException {
		try {
			List<BedAllocationDto> bedAllocationDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_WARD_LIST_BY_FLOOR_ID")
					.setInteger("organizationId", bedAllocationDto.getOrganizationId())
					.setInteger("unitId", bedAllocationDto.getUnitId())
					.setInteger("floorId", bedAllocationDto.getFloorId())
					.setInteger("genderId", bedAllocationDto.getGenderId())
					.setResultTransformer(Transformers.aliasToBean(BedAllocationDto.class)).list();
			return new Response(SUCCESS, null, null, bedAllocationDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getNursingStationListByWard(BedAllocationDto bedAllocationDto) throws ApplicationException {
		try {
			List<BedAllocationDto> bedAllocationDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_NURSING_STATION_BY_WARD")
					.setInteger("organizationId", bedAllocationDto.getOrganizationId())
					.setInteger("unitId", bedAllocationDto.getUnitId())
					.setInteger("wardId", bedAllocationDto.getWardId())
					.setResultTransformer(Transformers.aliasToBean(BedAllocationDto.class)).list();
			return new Response(SUCCESS, null, null, bedAllocationDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getBedCategoryList(BedCategoryMasterDto bedCategoryMasterDto) throws ApplicationException {
		try {
			List<BedCategoryMasterDto> bedAllocationDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_BED_CATEGORY_LIST_BY_ORG_UNIT")
					.setInteger("organizationId", bedCategoryMasterDto.getOrganizationId())
					.setInteger("unitId", bedCategoryMasterDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(BedCategoryMasterDto.class)).list();
			return new Response(SUCCESS, null, null, bedAllocationDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getBedStatusList() throws ApplicationException {
		try {
			List<BedStatusMasterDto> bedStatusMasterDtos = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_BED_STATUS_LIST")
					.setResultTransformer(Transformers.aliasToBean(BedStatusMasterDto.class)).list();
			return new Response(SUCCESS, null, null, bedStatusMasterDtos, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}


	@Override
	public Response getMealPreference(MealPreferenceMasterDto mealPreferenceMasterDto) throws ApplicationException {
		try {
			List<BedAllocationDto> bedAllocationDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_MEAL_PREFERENCE")
					.setInteger("organizationId", mealPreferenceMasterDto.getOrganizationId())
					.setInteger("unitId", mealPreferenceMasterDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(MealPreferenceMasterDto.class)).list();
			return new Response(SUCCESS, null, null, bedAllocationDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response savePatientAdmission(AdmissionDto admissionDto) throws ApplicationException {
		try {

			Admission admission = mapper.map(admissionDto, Admission.class, "AdmissionDto_to_Admission");
			Session session = sessionFactory.openSession();
				Transaction tr = session.beginTransaction();
				Integer id = (Integer) session.save(admission);
			admissionDto.setAdmissionId(id);
			tr.commit();
			session.close();
			return new Response(SUCCESS, null, COMMON_SUCCESS, null, admissionDto);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
		}
	}

	@Override
	public Response savePatientDetails(AdmissionDto admissionDto) throws ApplicationException {

		try {

			// <-----Adding data in patient details
			AdmissionDetails admissionDetails = mapper.map(admissionDto, AdmissionDetails.class,
					"AdmissionDto_to_AdmissionDetails");
			save(admissionDetails);
			// ----->

			Session session = sessionFactory.openSession();
			// <-----Updating admission note's admission status to B(Booked)
			// from P(Pending) or R(Reserved)
			Query qry = session.createQuery("update AdmissionNote note set note.admissionStatus='B', "
					+ "note.admissionId=" + admissionDto.getAdmissionId() + ", " + "note.updatedBy="
					+ admissionDto.getUpdatedBy() + ", " + "note.updatedDate='"
					+ ADTCommonDateUtils.getDate(admissionDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss") + "'"
					+ " where note.admissionNoteId=" + admissionDto.getAdmissionNoteId());
			qry.executeUpdate();
			// ----->

			// <-----Updating status of last admission patient mapper record to
			// I(inactive)
			Query qry1 = session.createQuery("update AdmissionPatientMapper m set m.status='I'" + ", m.updatedBy="
					+ admissionDto.getUpdatedBy() + ", m.updatedDate='"
					+ ADTCommonDateUtils.getDate(admissionDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss") + "'"
					+ " where m.admissionNoteId=" + admissionDto.getAdmissionNoteId() + " AND  m.status='A'");

			qry1.executeUpdate();
			// ----->

			// <-----Adding new entry in admission patient mapper where active
			// status will be B(Booked) from P(Pending)
			AdmissionPatientMapper admissionPatientMapper = mapper.map(admissionDto, AdmissionPatientMapper.class,
					"AdmissionDto_to_AdmissionPatientMapper");
			Transaction tr = session.beginTransaction();
			session.save(admissionPatientMapper);
			tr.commit();
			// ----->

			// <-----Changing status of bed to Booked (5 is the ID of
			// 'booked'status)
			Query qry2 = session.createQuery("update BedMaster bed set bed.bedStatusId=" + admissionDto.getBedStatusId()
					+ ", bed.updatedBy=" + admissionDto.getUpdatedBy() + ", " + "bed.updatedDate='"
					+ ADTCommonDateUtils.getDate(admissionDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss") + "'"
					+ " where bed.bedId=" + admissionDto.getBedId() + " AND bed.organizationId="
					+ admissionDto.getOrganizationId() + " AND bed.unitId=" + admissionDto.getUnitId()
					+ " AND bed.status='A' ");

			qry2.executeUpdate();
			// ----->

			// <-----Updating status of current bed to I in BedLogStatus
			Query qry3 = session.createQuery("update BedLogStatus bedStatus set bedStatus.status='I', "
					+ " bedStatus.updatedBy=" + admissionDto.getUpdatedBy() + ", " + " bedStatus.updatedDate='"
					+ ADTCommonDateUtils.getDate(admissionDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss") + "'"
					+ " WHERE bedStatus.status='A' " + " AND bedStatus.bedId=" + admissionDto.getBedId()
					+ " AND bedStatus.organizationId=" + admissionDto.getOrganizationId() + " AND bedStatus.unitId="
					+ admissionDto.getUnitId());
			qry3.executeUpdate();
			// ----->

			// <-----Adding new entry in log as the status of bed is changing
			BedLogStatus bedLogStatus = mapper.map(admissionDto, BedLogStatus.class, "AdmissionDto_to_BedLogStatus");
			Transaction tr2 = session.beginTransaction();
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
	public Response getBedListByMultipleCriteria(BedMasterDto bedMasterDto) throws ApplicationException {
		try {

			Session session = sessionFactory.openSession();
			StringBuilder resQuery = this.searchBed(bedMasterDto);
			Query actualQuery = session.createQuery(resQuery.toString());

			List<BedMasterDto> bedMasterDtosList = actualQuery
					.setInteger("organizationId", bedMasterDto.getOrganizationId())
					.setInteger("unitId", bedMasterDto.getUnitId())
					.setCharacter("isVirtual", bedMasterDto.getIsVirtual())
					.setResultTransformer(Transformers.aliasToBean(BedMasterDto.class)).list();
			return new Response(SUCCESS, null, null, bedMasterDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	private StringBuilder searchBed(BedMasterDto bedMasterDto) {

		StringBuilder strQuery = new StringBuilder("SELECT bed.bedId as bedId, " 
				+ "bed.bedNumber as bedNumber, "
				+ "bed.floorId as floorId, " 
				+ "bed.roomId as roomId, " 
				+ "floor.floorName as floorName, "
				+ "bed.wardId as wardId, " 
				+ "ward.wardName as wardName, " 
				+ "bed.bedCategoryId as bedCategoryId, "
				+ "bed.billingBedCategoryId as billingBedCategoryId, " 
				+ "bc.bedCategoryDesc as bedCategoryDesc "
				+ "FROM BedMaster bed " 
				+ "INNER JOIN bed.floorMaster floor " 
				+ "INNER JOIN bed.wardMaster ward "
				+ "INNER JOIN bed.bedCategoryMaster bc " 
				+ "LEFT JOIN ward.nursingStationWardMappersList nursing "
				+ "LEFT JOIN bed.listBedLogStatus bedlog " 
				+ "WHERE bed.unitId=:unitId "
				+ "AND bed.organizationId=:organizationId " 
				+ "AND bed.status='A' " 
				+ "AND bed.bedStatusId=3 "
				+ "AND ward.isEr ='N' " 
				+ "AND ward.isIcu='N' " 
				+ "AND bed.isVirtual=:isVirtual ");

		if (bedMasterDto.getFloorId() > 0)
			strQuery.append(" AND bed.floorId=" + bedMasterDto.getFloorId());

		if (bedMasterDto.getWardId() > 0)
			strQuery.append(" AND bed.wardId=" + bedMasterDto.getWardId());

		if (bedMasterDto.getNursingStationId() > 0)
			strQuery.append(" AND nursing.nursingStationId=" + bedMasterDto.getNursingStationId());

		if (bedMasterDto.getBedCategoryId() > 0)
			strQuery.append(" AND bed.bedCategoryId=" + bedMasterDto.getBedCategoryId());

		if (bedMasterDto.getBedStatusId() > 0)
			strQuery.append(" AND bed.bedStatusId=" + bedMasterDto.getBedStatusId());

		return strQuery;
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
			return new Response<AdmissionDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<AdmissionDto, Integer>(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<AdmissionDto, Integer>(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
		}
	}

	@Override
	public Response getWatingListNumbers(AdmissionDto admissionDto) throws ApplicationException {
		try {
				List<AdmissionDto> updateWatingListNumbersList = sessionFactory.getCurrentSession()
						.getNamedQuery(
								"GET_WAITING_LIST_DATA_WHICH_ARE_GREATER_THAN_WAITING_LIST_NUMBER_OF_CURRENT_ADMISSION")
						.setInteger("organizationId", admissionDto.getOrganizationId())
						.setInteger("unitId", admissionDto.getUnitId())
						.setDate("doa", ADTCommonDateUtils.getDate(admissionDto.getDoa(), "dd-M-yyyy HH:mm:ss"))
						.setInteger("bedCategoryId", admissionDto.getBedCategoryId())
						.setInteger("bedCategoryWaitingListId", admissionDto.getBedCategoryWaitingListId())
						.setResultTransformer(Transformers.aliasToBean(AdmissionDto.class)).list();
				
				
				Session session=sessionFactory.openSession();
				
					Query qry = session.createQuery("update BedCategoryWaitingList bcwl set bcwl.status='I', "
							+ "bcwl.updatedBy=" + admissionDto.getUpdatedBy() + ", " + "bcwl.updatedDate='"
							+ ADTCommonDateUtils.getDate(admissionDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss") + "'"
							+ " WHERE bcwl.bedCategoryWaitingListId=" + admissionDto.getBedCategoryWaitingListId()
							+" AND bcwl.status='A' ");
					qry.executeUpdate();
				
					
				return new Response(SUCCESS, null, null, updateWatingListNumbersList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	
	@Override
	public Response updateWatingListNumber(AdmissionDto admissionDto) throws ApplicationException {
		try {
			Session session = sessionFactory.openSession();
				Query qry = session.createQuery("update BedCategoryWaitingList bcwl set bcwl.status='I', "
						+ "bcwl.updatedBy=" + admissionDto.getUpdatedBy() + ", " 
						+ "bcwl.updatedDate='"+ ADTCommonDateUtils.getDate(admissionDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss") + "'"
						+ " where bcwl.admissionNoteId=" + admissionDto.getAdmissionNoteId()
						+ " AND bcwl.status='A'");
				qry.executeUpdate();
				
				
			Transaction tr = session.beginTransaction();
			BedCategoryWaitingList bedCategoryWaitingList = mapper.map(admissionDto, BedCategoryWaitingList.class,
					"AdmissionDto_to_BedCategoryWaitingList");
			session.save(bedCategoryWaitingList);
			tr.commit();
			session.close();
			return new Response<AdmissionDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<AdmissionDto, Integer>(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<AdmissionDto, Integer>(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
		}
	}

}
