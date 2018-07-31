package com.param.adt.master.unit.dao;

import com.param.adt.common.Response;
import com.param.adt.master.unit.dto.MortuaryBedMasterDto;
import com.param.adt.master.unit.model.MortuaryBedMaster;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IMortuaryBedMasterDao extends IGenericDao<MortuaryBedMaster, Integer> {

	Response saveMortuaryBeds(MortuaryBedMasterDto mortuaryBedMasterDto)throws ApplicationException;

	Response getMortuaryBedByName(MortuaryBedMasterDto mortuaryBedMasterDto)throws ApplicationException;

	Response getMortuaryBedList(MortuaryBedMasterDto mortuaryBedMasterDto)throws ApplicationException;

	Response getActiveMortuaryBeds(MortuaryBedMasterDto mortuaryBedMasterDto)throws ApplicationException;

	Response getMortuaryBedCount(MortuaryBedMasterDto mortuaryBedMasterDto)throws ApplicationException;

	Response getMortuaryListBedById(MortuaryBedMasterDto mortuaryBedMasterDto)throws ApplicationException;

	Response updateMortuaryBedStatus(MortuaryBedMasterDto mortuaryBedMasterDto)throws ApplicationException;

	Response getMortuaryBedByNameNotId(MortuaryBedMasterDto mortuaryBedMasterDto) throws ApplicationException;

	Response updateMortuaryBedMaster(MortuaryBedMasterDto mortuaryBedMasterDto);

	Response getMorturyBedListByStatusId(MortuaryBedMasterDto mortuaryBedMasterDto)throws ApplicationException;

	Response saveMortuaryBedLogStatus(MortuaryBedMasterDto mortuaryBedMasterDto)throws ApplicationException;

}
