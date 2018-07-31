package com.param.opd.unit.dao;

import java.util.List;

import com.param.global.common.Response;
import com.param.global.dto.BlockSlotReqDto;
import com.param.global.dto.SlotBlockUnblockRequestDto;
import com.param.global.dto.SlotMasterDto;
import com.param.opd.unit.model.SlotMaster;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface ISlotMasterDao extends IGenericDao<SlotMaster, Integer>{


	Response generateSlots(SlotMasterDto slotMasterDtosObj)throws ApplicationException;

	Response getSlots(SlotMasterDto slotMasterDto)throws ApplicationException;
	
	Response getSlotsForBlockUnblock(SlotBlockUnblockRequestDto slotReqDto)throws ApplicationException;

	Response getSlotCount(SlotMasterDto slotMasterDto)throws ApplicationException;

	Response getSlotDetails(SlotMasterDto slotMasterDto)throws ApplicationException;

	Response updateSlotStatus(SlotMasterDto slotMasterDto)throws ApplicationException;

	List<SlotMasterDto> getAppointmentsForMonth(SlotMasterDto slotMasterDto)throws ApplicationException;

	Response getYearlySlots(SlotMasterDto slotMasterDto)throws ApplicationException;

	Response getDailySlots(SlotMasterDto slotMasterDto)throws ApplicationException;

	Response getWeeklySlots(SlotMasterDto slotMasterDto)throws ApplicationException;
	
	Response blockUnblockSlots(BlockSlotReqDto blockSlotReqDto) throws ApplicationException;

	Response getBlockedUnblockedSlotDetails(SlotMasterDto slotMasterDto)throws ApplicationException;

	Response updateStatusOfSlot(SlotMasterDto slotMasterDto)throws ApplicationException;

	Response isOverBookingAllowedOrNot(SlotMasterDto slotMasterDto)throws ApplicationException;

}
