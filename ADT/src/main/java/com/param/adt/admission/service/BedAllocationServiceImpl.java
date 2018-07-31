package com.param.adt.admission.service;

import java.util.Iterator;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.adt.admission.dao.IBedAllocationDao;
import com.param.adt.admission.dto.BedAllocationDto;
import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.global.dto.BedCategoryMasterDto;
import com.param.adt.master.global.dto.BedMasterDto;
import com.param.adt.master.unit.dto.BillingBedCategoryMasterDto;
import com.param.adt.master.unit.dto.MealPreferenceMasterDto;
import com.param.adt.transfer.dao.IBedToBedTransferDao;
import com.param.adt.transfer.dto.TransferDto;
import com.param.global.dto.AdmissionDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class BedAllocationServiceImpl implements IBedAllocationService, ICommonConstants {

	@Autowired
	IBedAllocationDao iBedAllocationDao;
	
	@Autowired
	IBedToBedTransferDao iBedToBedTransferDao;

	public static String getAdmissionNumber() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder("ADM000");
		Random rnd = new Random();
		while (salt.length() < 10) {
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;

	}
	
	@Override
	public Response getActiveBedList(BedMasterDto bedMasterDto) throws ApplicationException {
		try {
			if(bedMasterDto.getTransferTypeId()==null)
			{
				return iBedAllocationDao.getActiveBedList(bedMasterDto);
			}
			else if(bedMasterDto.getTransferTypeId()==1)
			{
				return iBedAllocationDao.getActiveBedList(bedMasterDto);
			}else
			{
				return iBedToBedTransferDao.getBedListByWardId(bedMasterDto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getActiveFloorMasterList(BedAllocationDto bedAllocationDto) throws ApplicationException {
		try {
			return iBedAllocationDao.getActiveFloorMasterList(bedAllocationDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getWardListByFloorId(BedAllocationDto bedAllocationDto) throws ApplicationException {
		try {
			return iBedAllocationDao.getWardListByFloorId(bedAllocationDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getNursingStationListByWard(BedAllocationDto bedAllocationDto) throws ApplicationException {
		try {
			return iBedAllocationDao.getNursingStationListByWard(bedAllocationDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getBedCategoryList(BedCategoryMasterDto bedCategoryMasterDto) throws ApplicationException {
		try {
			return iBedAllocationDao.getBedCategoryList(bedCategoryMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getBedStatusList() throws ApplicationException {
		try {
			return iBedAllocationDao.getBedStatusList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getMealPreference(MealPreferenceMasterDto mealPreferenceMasterDto) throws ApplicationException {
		try {
			return iBedAllocationDao.getMealPreference(mealPreferenceMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response savePatientAdmission(AdmissionDto admissionDto) throws ApplicationException {
		try {
			admissionDto.setAdmissionNumber(getAdmissionNumber());
			return iBedAllocationDao.savePatientAdmission(admissionDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response savePatientDetails(AdmissionDto admissionDto) throws ApplicationException {
		try {
			/*admissionDto.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/

			return iBedAllocationDao.savePatientDetails(admissionDto);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	

	@Override
	public Response getBedListByMultipleCriteria(BedMasterDto bedMasterDto) throws ApplicationException {
		try {
			return iBedAllocationDao.getBedListByMultipleCriteria(bedMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response saveTransfer(AdmissionDto admission) {
		try {
			
			TransferDto transferDto=new TransferDto();
				transferDto.setOrganizationId(admission.getOrganizationId());
				transferDto.setUnitId(admission.getUnitId());
				transferDto.setAdmissionId(admission.getAdmissionId());
				transferDto.setFromBedCategoryId(admission.getBedCategoryId());
				transferDto.setFromBillingBedCategoryId(admission.getBillingBedCategoryId());
				transferDto.setFromWardId(admission.getWardId());
				transferDto.setFromRoomId(admission.getRoomId());
				transferDto.setFromBedId(admission.getBedId());
				transferDto.setFromDate(admission.getCreatedDate());
				transferDto.setFromTime(admission.getCreatedDate());
				transferDto.setCreatedBy(admission.getCreatedBy());
				transferDto.setCreatedDate(admission.getCreatedDate());
				transferDto.setUpdatedBy(admission.getUpdatedBy());
				transferDto.setUpdatedDate(admission.getUpdatedDate());
				transferDto.setStatus('A');
				transferDto.setTransferStatusId(1);
				transferDto.setTransferTypeId(1);
			return iBedAllocationDao.saveTransfer(transferDto);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updateWatingListNumber(AdmissionDto admissionDto) throws ApplicationException {
		try {
			Response res = iBedAllocationDao.getWatingListNumbers(admissionDto);
			if(res.getStatus().equals(SUCCESS) && !res.getListObject().isEmpty())
			{
				Iterator<AdmissionDto> itr = res.getListObject().iterator();
			 
				while (itr.hasNext()) 
				{
					
					AdmissionDto bedCategoryWaitingList =new AdmissionDto();
			    
					AdmissionDto obj = itr.next();
						bedCategoryWaitingList.setOrganizationId(obj.getOrganizationId());
						bedCategoryWaitingList.setUnitId(obj.getUnitId());
						bedCategoryWaitingList.setAdmissionNoteId(obj.getAdmissionNoteId());
						bedCategoryWaitingList.setBedCategoryId(obj.getBedCategoryId());
						bedCategoryWaitingList.setDoa(admissionDto.getDoa());
						bedCategoryWaitingList.setWaitListNumber(obj.getWaitListNumber()-1);
						bedCategoryWaitingList.setStatus('A');
						bedCategoryWaitingList.setCreatedBy(admissionDto.getUpdatedBy());
						bedCategoryWaitingList.setUpdatedBy(admissionDto.getUpdatedBy());
						bedCategoryWaitingList.setCreatedDate(admissionDto.getUpdatedDate());
						bedCategoryWaitingList.setUpdatedDate(admissionDto.getUpdatedDate());
					iBedAllocationDao.updateWatingListNumber(bedCategoryWaitingList);
			  }
				
			}
			return new Response(SUCCESS, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

}
