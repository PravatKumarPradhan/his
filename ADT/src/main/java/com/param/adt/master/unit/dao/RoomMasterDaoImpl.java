package com.param.adt.master.unit.dao;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.adt.master.unit.dto.RoomMasterDto;
import com.param.adt.master.unit.model.RoomMaster;
import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;
@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class RoomMasterDaoImpl extends GenericDao<RoomMaster, Integer> implements IRoomMasterDao, ICommonConstants {

	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	private DozerBeanMapper mapper;

	public RoomMasterDaoImpl() {
		super(RoomMaster.class);
	}

	@Override
	public Response saveRoomMasterMaster(RoomMasterDto roomMasterDto) throws ApplicationException {
		try {
			RoomMaster roomMaster = mapper.map(roomMasterDto, RoomMaster.class, "RoomMasterDto_to_RoomMaster");
			save(roomMaster);
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	
	@Override
	public Response getRoomMasterById(int roomId, int orgId, int unitId) throws ApplicationException {
		try {
			List<RoomMasterDto> roomMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ROOM_MASTER_BY_ID")
					.setInteger("roomId", roomId)
					.setInteger("orgId", orgId)
					.setInteger("unitId", unitId)
					.setResultTransformer(Transformers.aliasToBean(RoomMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, roomMasterDtoList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getRoomMasterList(RoomMasterDto roomMasterDto) throws ApplicationException {
		try {
			List<RoomMasterDto> roomMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ROOM_MASTER_LIST")
					.setInteger("orgId", roomMasterDto.getOrganizationId())
					.setInteger("unitId", roomMasterDto.getUnitId())
					.setFirstResult(roomMasterDto.getOffset() != null ? roomMasterDto.getOffset() : 0)
					.setMaxResults(
							roomMasterDto.getNoOfRecordsPerPage() != null ? roomMasterDto.getNoOfRecordsPerPage() : 10)
					.setResultTransformer(Transformers.aliasToBean(RoomMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, roomMasterDtoList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updateRoomMaster(RoomMasterDto roomMasterDto) throws ApplicationException {
		try {
			RoomMaster roomMaster=findById(roomMasterDto.getRoomId());
			roomMaster.setBedCategoryId(roomMasterDto.getBedCategoryId());
			roomMaster.setBillingBedCategoryId(roomMasterDto.getBillingBedCategoryId());
			roomMaster.setIsIsolation(roomMasterDto.getIsIsolation());
			roomMaster.setRoomCode(roomMasterDto.getRoomCode());
			roomMaster.setRoomDesc(roomMasterDto.getRoomDesc());
			roomMaster.setWardId(roomMasterDto.getWardId());			
			roomMaster.setUpdatedBy(roomMasterDto.getUpdatedBy());
			roomMaster.setUpdatedDate(
					GlobalCommonDateUtils.getDate(roomMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(roomMaster);
			return new Response<>(SUCCESS, SUCCESS_CODE, COMMON_UPDATE, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updateRoomMasterStatus(RoomMasterDto roomMasterDto) throws ApplicationException {
		try {
			RoomMaster roomMaster=findById(roomMasterDto.getRoomId());
			roomMaster.setStatus(roomMasterDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			roomMaster.setUpdatedBy(roomMasterDto.getUpdatedBy());
			roomMaster.setUpdatedDate(
					GlobalCommonDateUtils.getDate(roomMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(roomMaster);
			return new Response<>(SUCCESS, SUCCESS_CODE, COMMON_UPDATE, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getRoomMasterCount(RoomMasterDto roomMasterDto) throws ApplicationException {
		try {
			Session session = sessionFactory.openSession();
			Criteria c = session.createCriteria(RoomMaster.class);
			c.add(Restrictions.eq("organizationId", roomMasterDto.getOrganizationId()));
			c.setProjection(Projections.rowCount());
			Long count = (Long) c.uniqueResult();
			return new Response(SUCCESS, null, null, null, count);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getRoomMasterByName(RoomMasterDto roomMasterDto) throws ApplicationException {
		try {
			List<RoomMasterDto> roomMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ROOM_MASTER_BY_NAME")
					.setInteger("orgId", roomMasterDto.getOrganizationId())
					.setString("roomName", roomMasterDto.getRoomDesc().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(RoomMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, roomMasterDtoList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getRoomMasterByNameNotById(RoomMasterDto roomMasterDto) throws ApplicationException {
		try {
			List<RoomMasterDto> roomMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ROOM_MASTER_BY_NAME_NOT_BY_ID")
					.setInteger("roomId", roomMasterDto.getRoomId())
					.setString("roomName", roomMasterDto.getRoomDesc().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(RoomMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, roomMasterDtoList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

}
