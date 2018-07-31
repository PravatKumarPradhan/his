package com.param.adt.master.unit.service;

import javax.transaction.Transactional;

import com.param.adt.master.unit.dto.WardMasterDto;
import com.param.global.common.Response;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
@Transactional
public interface IWardMasterService {


	Response saveWardMasterMaster(WardMasterDto wardMasterDto) throws ApplicationException;

	Response getWardMasterById(int wardId, int orgId, int unitId) throws ApplicationException;

	Response getWardMasterList(WardMasterDto wardMasterDto) throws ApplicationException;

	Response updateWardMaster(WardMasterDto wardMasterDto) throws ApplicationException;

	Response updateWardMasterStatus(WardMasterDto wardMasterDto) throws ApplicationException;

	Response getWardMasterCount(WardMasterDto wardMasterDto) throws ApplicationException;

}
