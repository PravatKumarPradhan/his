package com.param.global.service;

import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dao.IDependentDetailsDao;
import com.param.global.dto.DependentDetailsDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({ "unchecked", "rawtypes"})
public class DependentDetailsServiceImpl implements IDependentDetailsService,ICommonConstants{

	@Autowired
	IDependentDetailsDao iDependentDetailsDao;
	
	@Override
	public Response saveDependentDetails(DependentDetailsDto dependentDetailsDto) throws ApplicationException {
		try{
			Integer employeeId = dependentDetailsDto.getEmpDocId();
			Integer typeId = dependentDetailsDto.getTypeId();
			ListIterator<DependentDetailsDto> listIterator = dependentDetailsDto.getDependentDetailsDtoList().listIterator();
			while(listIterator.hasNext())
			{
				DependentDetailsDto detailsDto = listIterator.next();
				detailsDto.setEmpDocId(employeeId);
				detailsDto.setTypeId(typeId);
				iDependentDetailsDao.saveDependentDetails(detailsDto);
			}
			
			return new Response(SUCCESS, SUCCESS_CODE, COMMON_SUCCESS, null, null);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getDependentDetails(DependentDetailsDto dependentDetailsDto) throws ApplicationException {
		try{
			return iDependentDetailsDao.getDependentDetails(dependentDetailsDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateDependentDetails(DependentDetailsDto dependentDetailsDto) throws ApplicationException {
		try{
			
			
			if(dependentDetailsDto.getDependentDetailsDtoList().size()>0)
			{
				Response res =iDependentDetailsDao.inactivePreviousDependents(dependentDetailsDto);
				
				if(res.getStatus().equals(SUCCESS))
					saveDependentDetails(dependentDetailsDto);
				
				
			}
			return new Response(SUCCESS, null, COMMON_UPDATE, null, null);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response searchDependentDetailsByCriteria(DependentDetailsDto dependentDetailsDto)
			throws ApplicationException {
		try {
			Response res = null;
			if(dependentDetailsDto.getTypeId()==1)
			{
				res =iDependentDetailsDao.searchDependentDetailsOfDoctor(dependentDetailsDto);
			}else
			{
				res =iDependentDetailsDao.searchDependentDetailsOfEmployee(dependentDetailsDto);
			}
			
			
			return new Response(SUCCESS, null, null, res.getListObject(), null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
