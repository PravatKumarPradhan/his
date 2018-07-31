package com.param.global.service;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dao.IEmployeeRegistrationDao;
import com.param.global.dto.DependentDetailsDto;
import com.param.global.dto.EmployeeRegistrationDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class EmployeeRegistrationServiceImpl implements IEmployeeRegistrationService,ICommonConstants{

	@Autowired
	IEmployeeRegistrationDao iEmployeeRegistrationDao;
	
	@Autowired
	IDependentDetailsService iDependentDetailsService;
	
	@Override
	public Response saveEmployeeRegistration(EmployeeRegistrationDto employeeRegistrationDto)
			throws ApplicationException {
		try {

			Response res = iEmployeeRegistrationDao.checkUniqueEmployee(employeeRegistrationDto);
			if (res.getListObject().size() > 0) {
				return new Response(ERROR, null, "Already exist !!", null, null);
			} else {
				Response res1 = iEmployeeRegistrationDao.saveEmployeeRegistration(employeeRegistrationDto);
				employeeRegistrationDto.setEmployeeId((Integer) res1.getObject());
				if (res1.getStatus().equals(SUCCESS)) {

					Response patientResponse = iEmployeeRegistrationDao.saveEmployeeDetails(employeeRegistrationDto);

					if (patientResponse.getStatus().equals(SUCCESS)
							&& employeeRegistrationDto.getDependentDetailsDtosList().size() > 0) {
						
							DependentDetailsDto dependentDetailsDto = new DependentDetailsDto();
							dependentDetailsDto.setTypeId(employeeRegistrationDto.getTypeId());
							dependentDetailsDto.setEmpDocId(employeeRegistrationDto.getEmployeeId());
							dependentDetailsDto.setDependentDetailsDtoList(employeeRegistrationDto.getDependentDetailsDtosList());
							Response DependentRes = iDependentDetailsService.saveDependentDetails(dependentDetailsDto);
							if (DependentRes.getStatus().equals(SUCCESS)) {
								return new Response(SUCCESS, SUCCESS_CODE, COMMON_SUCCESS, null, null);
							}
						
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getEmployeeDetails(EmployeeRegistrationDto employeeRegistrationDto) throws ApplicationException {
		try {
			List<EmployeeRegistrationDto> employeeRegistrationDtosList = new LinkedList<EmployeeRegistrationDto>(); 
			Response responseEmployeeDetails = iEmployeeRegistrationDao.getEmployeeDetails(employeeRegistrationDto);
			if(responseEmployeeDetails.getListObject().size()>0)
			{
				ListIterator<EmployeeRegistrationDto> iterator = responseEmployeeDetails.getListObject().listIterator();
				while(iterator.hasNext())
				{
					EmployeeRegistrationDto employeeRegistrationDtoList = iterator.next();
					
					
					DependentDetailsDto dependentDetailsDto = new DependentDetailsDto();
					dependentDetailsDto.setTypeId(employeeRegistrationDto.getTypeId());
					dependentDetailsDto.setEmpDocId(employeeRegistrationDtoList.getEmployeeId());
					
					Response dependentRes= iDependentDetailsService.getDependentDetails(dependentDetailsDto);
					employeeRegistrationDtoList.setDependentDetailsDtosList(dependentRes.getListObject());
					
					employeeRegistrationDtosList.add(employeeRegistrationDtoList);
					
				}
				
			}
			
			return new Response(SUCCESS, SUCCESS_CODE, null, employeeRegistrationDtosList, null);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updateEmployeeDetails(EmployeeRegistrationDto employeeRegistrationDto) throws ApplicationException {
		try {
				Response resEmpDependent=null;	
				Response res = iEmployeeRegistrationDao.checkUniqueEmployee(employeeRegistrationDto);
				if(res.getStatus().equals(SUCCESS))
				{
					Response resEmpDetails = iEmployeeRegistrationDao.updateEmployeeDetails(employeeRegistrationDto);
					if(resEmpDetails.getStatus().equals(SUCCESS)){
					
					DependentDetailsDto dependentDetailsDto=new DependentDetailsDto();
						dependentDetailsDto.setEmpDocId(employeeRegistrationDto.getEmployeeId());
						dependentDetailsDto.setTypeId(employeeRegistrationDto.getTypeId());
						dependentDetailsDto.setUpdatedBy(employeeRegistrationDto.getUpdatedBy());
						dependentDetailsDto.setUpdatedDate(employeeRegistrationDto.getUpdatedDate());
						dependentDetailsDto.setDependentDetailsDtoList(employeeRegistrationDto.getDependentDetailsDtosList());
					 resEmpDependent = iDependentDetailsService.updateDependentDetails(dependentDetailsDto);
					
					 if(resEmpDependent.getStatus().equals(SUCCESS))
					 		return new Response(SUCCESS, null, COMMON_UPDATE, null, null);
					 else
					 		return new Response(ERROR, null, "Problem in updating Dependent Details !!", null, null);
					}
				}
				else
					return new Response(ERROR, null, "Employee does not exist !!", null, null);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updateEmployeeStatus(EmployeeRegistrationDto employeeRegistrationDto) throws ApplicationException {
		try{
			
			return iEmployeeRegistrationDao.updateEmployeeStatus(employeeRegistrationDto);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response searchEmployeeByCriteria(EmployeeRegistrationDto employeeRegistrationDto)
			throws ApplicationException {
		try {
			List<EmployeeRegistrationDto> employeeRegistrationDtosList = new LinkedList<EmployeeRegistrationDto>();
			Response responseEmployeeDetails = iEmployeeRegistrationDao.searchEmployeeByCriteria(employeeRegistrationDto);
			if(responseEmployeeDetails.getListObject().size()>0)
			{
				ListIterator<EmployeeRegistrationDto> iterator = responseEmployeeDetails.getListObject().listIterator();
				while(iterator.hasNext())
				{
					EmployeeRegistrationDto employeeRegistrationDtoList = iterator.next();
					
					
					DependentDetailsDto dependentDetailsDto = new DependentDetailsDto();
					dependentDetailsDto.setTypeId(employeeRegistrationDto.getTypeId());
					dependentDetailsDto.setEmpDocId(employeeRegistrationDtoList.getEmployeeId());
					
					Response dependentRes= iDependentDetailsService.getDependentDetails(dependentDetailsDto);
					employeeRegistrationDtoList.setDependentDetailsDtosList(dependentRes.getListObject());
					
					employeeRegistrationDtosList.add(employeeRegistrationDtoList);
					
				}
				
			}
			
			return new Response(SUCCESS, SUCCESS_CODE, null, employeeRegistrationDtosList, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}

}
