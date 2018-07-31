package com.param.adt.master.unit.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.unit.dao.IMortuaryBedMasterDao;
import com.param.adt.master.unit.dto.MortuaryBedMasterDto;

import in.param.exception.ApplicationException;
@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class MortuaryBedMasterServiceImpl implements IMortuaryBedMasterService,ICommonConstants{

	@Autowired
	IMortuaryBedMasterDao iModalityTransferDao;
	
	@Override
	public Response saveMortuaryBeds(MortuaryBedMasterDto mortuaryBedMasterDto) throws ApplicationException 
	{
		try {
			Response deptResponse = iModalityTransferDao.getMortuaryBedByName(mortuaryBedMasterDto);
				if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) 
				{
					return new Response(ERROR, null, ALREADY_EXIST, null, null);
				} 
				else 
				{
					mortuaryBedMasterDto.setMortuaryBedNumber(generateBedNumber());
					return iModalityTransferDao.saveMortuaryBeds(mortuaryBedMasterDto);
					 
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getMortuaryBedList(MortuaryBedMasterDto mortuaryBedMasterDto) throws ApplicationException {
		try {
			return iModalityTransferDao.getMortuaryBedList(mortuaryBedMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateStatusForMortuaryBed(MortuaryBedMasterDto mortuaryBedMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iModalityTransferDao.getMortuaryListBedById(mortuaryBedMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {
				iModalityTransferDao.updateMortuaryBedStatus(mortuaryBedMasterDto);
				return new Response(SUCCESS, null, COMMON_UPDATE, null, null);

			} else {
				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getActiveMortuaryBeds(MortuaryBedMasterDto mortuaryBedMasterDto) throws ApplicationException {
		try {
			return iModalityTransferDao.getActiveMortuaryBeds(mortuaryBedMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getMortuaryBedCount(MortuaryBedMasterDto mortuaryBedMasterDto) throws ApplicationException {
		try {
			return iModalityTransferDao.getMortuaryBedCount(mortuaryBedMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	private String generateBedNumber()
	{
			String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
			StringBuilder salt = new StringBuilder("MBED00");
			Random rnd = new Random();
			while (salt.length() < 10) {
				int index = (int) (rnd.nextFloat() * SALTCHARS.length());
				salt.append(SALTCHARS.charAt(index));
			}
			String saltStr = salt.toString();
			return saltStr;

	}

	@Override
	public Response updateMortuaryBedMaster(MortuaryBedMasterDto mortuaryBedMasterDto) throws ApplicationException {
		try {
			Response res = iModalityTransferDao.getMortuaryBedByNameNotId(mortuaryBedMasterDto);
			if (res.getListObject() != null && res.getListObject().size() > 0) {

				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {
				/*floorMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iModalityTransferDao.updateMortuaryBedMaster(mortuaryBedMasterDto);
				return new Response(SUCCESS, null, COMMON_UPDATE, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getMortuaryBedListById(MortuaryBedMasterDto mortuaryBedMasterDto) throws ApplicationException {
		try {
			return iModalityTransferDao.getMortuaryListBedById(mortuaryBedMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getMorturyBedListByStatusId(MortuaryBedMasterDto mortuaryBedMasterDto) throws ApplicationException {
		try {
			return iModalityTransferDao.getMorturyBedListByStatusId(mortuaryBedMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response saveMortuaryBedLogStatus(MortuaryBedMasterDto mortuaryBedMasterDto) throws ApplicationException {
		try {
			return iModalityTransferDao.saveMortuaryBedLogStatus(mortuaryBedMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response allotMortuaryBed(MortuaryBedMasterDto mortuaryBedMasterDto) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}
	/*
	 * "createdBy" : 1, 
	 * "createdDate" : "30-01-2018 12:44:22", 
	 * "mortuaryBedCode" : "MOGR-01", 
	 * "mortuaryBedDesc" : "Mortuary_01", 
	 * "organizationId" : 1, 
	 * "status" : "A",
	 * "unitId" : 1, 
	 * "updatedBy" : 1, 
	 * "updatedDate" : "30-01-2018 12:44:22"
	 */

}
