package com.param.billing.unit.service;

import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.billing.exception.ServiceException;
import com.param.billing.unit.dao.IIndividualDoctorShareGroupWiseDao;
import com.param.billing.unit.dao.IIndividualDoctorShareServiceWiseDao;
import com.param.billing.unit.dto.IndividualDoctorShareReqDto;
import com.param.billing.unit.dto.TGlobalDocShareGroupWiseDto;
import com.param.billing.unit.dto.TGlobalDocShareServiceWiseDto;
import com.param.billing.unit.dto.TIndividualDocShareGroupWiseDto;
import com.param.billing.unit.dto.TIndividualDocShareServiceWiseDto;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IndividualDoctorShareServiceImpl implements IIndividualDoctorShareService, ICommonConstants {

	@Autowired
	IIndividualDoctorShareGroupWiseDao iIndividualDoctorShareGroupWiseDao;
	
	@Autowired
	IIndividualDoctorShareServiceWiseDao iIndividualDoctorShareServiceWiseDao;
	

	@Override
	public Response saveIndividualDoctorShare(IndividualDoctorShareReqDto reqDto)
			throws ApplicationException {
		try{
			if(reqDto != null){
			//save Individual Doctor Share Group Wise details
			if(reqDto.getListTIndividualDocShareGroupWiseDto() != null && reqDto.getListTIndividualDocShareGroupWiseDto().size() > 0){
				ListIterator<TIndividualDocShareGroupWiseDto> servicDtlsItr = reqDto.getListTIndividualDocShareGroupWiseDto().listIterator();
				TIndividualDocShareGroupWiseDto tIndividualDocShareGroupWiseDto = null;
				while(servicDtlsItr.hasNext()){
					tIndividualDocShareGroupWiseDto = servicDtlsItr.next();
					tIndividualDocShareGroupWiseDto.setOrgId(reqDto.getOrgId());
					tIndividualDocShareGroupWiseDto.setUnitId(reqDto.getUnitId());
					iIndividualDoctorShareGroupWiseDao.saveIndividualDoctorShareGroupWise(tIndividualDocShareGroupWiseDto);
				}
			}
			
			//save Individual Doctor Share Service Wise details
			if(reqDto.getListTIndividualDocShareServiceWiseDto() != null && reqDto.getListTIndividualDocShareServiceWiseDto().size() > 0){
				ListIterator<TIndividualDocShareServiceWiseDto> servicDtlsItr = reqDto.getListTIndividualDocShareServiceWiseDto().listIterator();
				TIndividualDocShareServiceWiseDto tIndividualDocShareServiceWiseDto = null;
				while(servicDtlsItr.hasNext()){
					tIndividualDocShareServiceWiseDto = servicDtlsItr.next();
					tIndividualDocShareServiceWiseDto.setOrgId(reqDto.getOrgId());
					tIndividualDocShareServiceWiseDto.setUnitId(reqDto.getUnitId());
					iIndividualDoctorShareServiceWiseDao.saveIndividualDocShareServiceWise(tIndividualDocShareServiceWiseDto);
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

	
}
