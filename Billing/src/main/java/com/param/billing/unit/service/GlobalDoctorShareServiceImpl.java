package com.param.billing.unit.service;

import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.billing.exception.ServiceException;
import com.param.billing.unit.dao.IGlobalDoctorShareGroupWiseDao;
import com.param.billing.unit.dao.IGlobalDoctorShareServiceWiseDao;
import com.param.billing.unit.dto.DoctorShareReqDto;
import com.param.billing.unit.dto.TGlobalDocShareGroupWiseDto;
import com.param.billing.unit.dto.TGlobalDocShareServiceWiseDto;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.service.dto.CommonDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class GlobalDoctorShareServiceImpl implements IGlobalDoctorShareService, ICommonConstants {

	@Autowired
	IGlobalDoctorShareGroupWiseDao iGlobalDoctorShareGroupWiseDao;
	
	@Autowired
	IGlobalDoctorShareServiceWiseDao iGlobalDoctorShareServiceWiseDao;
	

	@Override
	public Response saveGlobalDoctorShare(DoctorShareReqDto reqDto)
			throws ApplicationException {
		try{
			if(reqDto != null){
			//save Global Doctor Share Group Wise details
			if(reqDto.getListTGlobalDocShareGroupWiseDto() != null && reqDto.getListTGlobalDocShareGroupWiseDto().size() > 0){
				ListIterator<TGlobalDocShareGroupWiseDto> servicDtlsItr = reqDto.getListTGlobalDocShareGroupWiseDto().listIterator();
				TGlobalDocShareGroupWiseDto tGlobalDocShareGroupWiseDto = null;
				while(servicDtlsItr.hasNext()){
					tGlobalDocShareGroupWiseDto = servicDtlsItr.next();
					tGlobalDocShareGroupWiseDto.setOrgId(reqDto.getOrgId());
					tGlobalDocShareGroupWiseDto.setUnitId(reqDto.getUnitId());
					iGlobalDoctorShareGroupWiseDao.saveGlobalDoctorShareGroupWiseDao(tGlobalDocShareGroupWiseDto);
				}
			}
			
			//save Global Doctor Share Service Wise details
			if(reqDto.getListTGlobalDocShareServiceWiseDto() != null && reqDto.getListTGlobalDocShareServiceWiseDto().size() > 0){
				ListIterator<TGlobalDocShareServiceWiseDto> servicDtlsItr = reqDto.getListTGlobalDocShareServiceWiseDto().listIterator();
				TGlobalDocShareServiceWiseDto tGlobalDocShareServiceWiseDto = null;
				while(servicDtlsItr.hasNext()){
					tGlobalDocShareServiceWiseDto = servicDtlsItr.next();
					tGlobalDocShareServiceWiseDto.setOrgId(reqDto.getOrgId());
					tGlobalDocShareServiceWiseDto.setUnitId(reqDto.getUnitId());
					iGlobalDoctorShareServiceWiseDao.saveGlobalDoctorShareServiceWiseDao(tGlobalDocShareServiceWiseDto);
				}
			}
			return new Response<>(SUCCESS, SUCCESS_CODE, DOCTOR_SHARE, null, null);
		 }
			
		  return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
			
		}catch(ServiceException se){
			se.printStackTrace();
			throw se;
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
/*
	@Override
	public Response searchCompanyContract(ServiceForCompnayContarctReqDto reqDto) throws ServiceException {
		try {
			return iCompanyContractDao.searchCompanyContract(reqDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}*/


	@Override
	public Response getDoctorGradeMaster(Integer orgId, Integer orgUnitId) throws ApplicationException {
		try {
			
			return iGlobalDoctorShareGroupWiseDao.getDoctorGradeList(orgId, orgUnitId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	
}
