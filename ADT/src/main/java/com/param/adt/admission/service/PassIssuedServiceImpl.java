package com.param.adt.admission.service;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.adt.admission.dao.IPassIssuedDao;
import com.param.adt.admission.dto.VisitorDto;
import com.param.adt.admission.model.VisitorPatientMapper;
import com.param.adt.common.ADTCommonDateUtils;
import com.param.adt.common.IADTConstants;
import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.global.dto.BedMasterDto;
import com.param.global.dto.AdmissionDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class PassIssuedServiceImpl implements ICommonConstants,IPassIssuedService,IADTConstants
{
	
	@Autowired
	IPassIssuedDao iPassIssuedDao;
	@Override
	public Response getVisitorTypeList(AdmissionDto admissionDto) throws ApplicationException {
		try {
			return iPassIssuedDao.getVisitorTypeList(admissionDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}


	@Override
	public Response saveVisitorAgainsPatient(VisitorDto visitorDto) throws ApplicationException {
		try {
			
			Response res=iPassIssuedDao.saveVisitorAgainsPatient(visitorDto);
			return new Response(SUCCESS, null, COMMON_SUCCESS, null, res.getObject());
		}catch (Exception e) 
		{
				e.printStackTrace();
			}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);

		}


		@Override
		public Response saveVisitorPatientMapper(VisitorDto visitorDto) throws ApplicationException {
			try{
			return iPassIssuedDao.saveVisitorPatientMapper(visitorDto);
			
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
		}


		@Override
		public Response getVisitorsList(VisitorDto visitorDto) throws ApplicationException {
			try {
				return iPassIssuedDao.getVisitorsList(visitorDto);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
		}


		
		@Override
		public Response updateVisitorsDetails(VisitorDto visitorDto) throws ApplicationException {
			try{
				
				Iterator<VisitorDto> itr=visitorDto.getVisitorDtosList().iterator();
				while(itr.hasNext())
				{
					
					VisitorDto obj=itr.next();
					VisitorPatientMapper visitorPatientMapper=new VisitorPatientMapper();
					//<-----setting new expiry date if VisitorPassStatus is E(Extended)
					if(obj.getVisitorPassStatus()=='E')
					{
						visitorPatientMapper.setExpiryDate(ADTCommonDateUtils.getDate(obj.getTxtNewDate(),"dd-M-yyyy HH:mm:ss"));
					}
					else
					{
						visitorPatientMapper.setExpiryDate(ADTCommonDateUtils.getDate(obj.getExpiryDate(),"dd-M-yyyy HH:mm:ss"));
					}
					//----->
					
					visitorPatientMapper.setOrganizationId(obj.getOrganizationId());
					visitorPatientMapper.setUnitId(obj.getUnitId());
					visitorPatientMapper.setVisitorPatientId(obj.getVisitorPatientId());
					visitorPatientMapper.setVisitorPassStatus(obj.getVisitorPassStatus());
					visitorPatientMapper.setVisitorPassTypeId(obj.getVisitorPassTypeId());
					visitorPatientMapper.setCreatedBy(obj.getCreatedBy());
					visitorPatientMapper.setUpdatedBy(obj.getUpdatedBy());
					visitorPatientMapper.setCreatedDate(ADTCommonDateUtils.getDate(obj.getCreatedDate(), "dd-M-yyyy HH:mm:ss"));
					visitorPatientMapper.setUpdatedDate(ADTCommonDateUtils.getDate(obj.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
					visitorPatientMapper.setStatus('A');

					iPassIssuedDao.updateVisitorsDetails(visitorPatientMapper,obj.getVisitorPatientId(),obj.getVisitorPatientMapperId());
				}
				return new Response(SUCCESS, null, UPDATE_VISITOR, null,null);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
		}


		@Override
		public Response getBedListByWardId(BedMasterDto bedMasterDto) throws ApplicationException {
			try {
				return iPassIssuedDao.getBedListByWardId(bedMasterDto);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
		}


		@Override
		public Response getAdmittedPatientListByMultipleCriteria(AdmissionDto admissionDto) {
			try {
				return iPassIssuedDao.getAdmittedPatientListByMultipleCriteria(admissionDto);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
		}
}
