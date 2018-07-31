package com.param.opd.unit.dao;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.BlockSlotReqDto;
import com.param.opd.unit.model.SlotBlockUnblockDetail;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;


@Repository
@SuppressWarnings("rawtypes")
public class SlotBlockUnblockDetailDaoImpl extends GenericDao<SlotBlockUnblockDetail, Integer> implements ISlotBlockUnblockDetailDao, ICommonConstants {

	public SlotBlockUnblockDetailDaoImpl() {
		super(SlotBlockUnblockDetail.class);
	}

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public Response saveSlotBlockDetails(BlockSlotReqDto blockSlotReqDto)
			throws ApplicationException {
		try{
			for (Integer slotId : blockSlotReqDto.getListSlotId()) {
				SlotBlockUnblockDetail slotBDetail = new SlotBlockUnblockDetail();
				slotBDetail.setSlotId(slotId);
				slotBDetail.setReasonId(blockSlotReqDto.getReasonId());
				slotBDetail.setRemark(blockSlotReqDto.getRemark());
				slotBDetail.setCreatedBy(blockSlotReqDto.getCreatedBy());
				slotBDetail.setCreatedDate(GlobalCommonDateUtils.getDate(blockSlotReqDto.getCreatedDate(), "dd-M-yyyy HH:mm:ss"));
				slotBDetail.setIsBlockUnblock(blockSlotReqDto.getIsBlockUnblock());
				slotBDetail.setOrganisationId(blockSlotReqDto.getOrganisationId());
				slotBDetail.setUnitId(blockSlotReqDto.getUnitId());
				slotBDetail.setStatus('A');
				save(slotBDetail);
			}
			return new Response<>(SUCCESS, COMMON_SUCCESS, SUCCESS_MESSAGE, null, null);
		}catch(HibernateException he){
			he.printStackTrace();
			throw he;
		}catch (ApplicationException ae) {
			ae.printStackTrace();
			throw ae;
		}
		
	}

	@Override
	public Response saveUpdatePrevSlotBlockDetails(BlockSlotReqDto blockSlotReqDto) throws ApplicationException {
		try{
			for (Integer slotId : blockSlotReqDto.getListSlotId()) {
				sessionFactory.getCurrentSession().
				createQuery("UPDATE SlotBlockUnblockDetail set status='I' "
						+ "WHERE slotId="+slotId
						+ "AND status='A' ").executeUpdate();
			}
			return new Response<>(SUCCESS, COMMON_SUCCESS, SUCCESS_MESSAGE, null, null);
			
		}catch(HibernateException he){
			he.printStackTrace();
			throw he;
		}catch (ApplicationException ae) {
			ae.printStackTrace();
			throw ae;
		}
	}

}
