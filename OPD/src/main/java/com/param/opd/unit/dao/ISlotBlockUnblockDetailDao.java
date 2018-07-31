package com.param.opd.unit.dao;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.global.dto.BlockSlotReqDto;
import com.param.opd.unit.model.SlotBlockUnblockDetail;
@SuppressWarnings("rawtypes")
public interface ISlotBlockUnblockDetailDao extends IGenericDao<SlotBlockUnblockDetail, Integer>{

	Response saveSlotBlockDetails(BlockSlotReqDto blockSlotReqDto)throws ApplicationException;

	Response saveUpdatePrevSlotBlockDetails(BlockSlotReqDto blockSlotReqDto)throws ApplicationException;
	
}
