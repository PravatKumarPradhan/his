package com.param.opd.coversheet.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.opd.coversheet.dao.IEncounterAssignmenMapperDao;
import com.param.opd.coversheet.dto.EncounterAssignmenMapperDto;

@Service
public class EncounterAssignmenMapperServiceImpl implements IEncounterAssignmenMapperService {

	
	@Autowired
	private IEncounterAssignmenMapperDao iEncounterAssignmenMapperDao;
	
	@Override
	@Transactional
	public Response saveEncounterAssignment(EncounterAssignmenMapperDto encounterAssignmenMapperDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iEncounterAssignmenMapperDao.saveEncounterAssignment(encounterAssignmenMapperDto);
	}

	@Override
	@Transactional
	public Response getOldListEncounterAssignment(
			EncounterAssignmenMapperDto encounterAssignmenMapperDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iEncounterAssignmenMapperDao.getOldListEncounterAssignment(encounterAssignmenMapperDto);
	}

}
