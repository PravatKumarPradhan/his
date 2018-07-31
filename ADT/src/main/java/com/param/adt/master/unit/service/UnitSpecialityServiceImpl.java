package com.param.adt.master.unit.service;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.unit.dao.IUnitSpecialityDao;
import com.param.adt.master.unit.dto.UnitSpecialtyMapperDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })

public class UnitSpecialityServiceImpl implements IUnitSpecialityService, ICommonConstants {

	@Autowired
	IUnitSpecialityDao iUnitSpecialityDao;

	@Override
	public Response saveUnitSpecialtiy(UnitSpecialtyMapperDto unitSpecialtyMapperDto) throws ApplicationException {
		try {
			//updating status as T ,is removed 
//			Response resTrunc = iUnitSpecialityDao.truncateUnitSpecialtiy(unitSpecialtyMapperDto);
//			if (resTrunc.getStatus().equals(SUCCESS)) {
				Iterator<UnitSpecialtyMapperDto> itr = unitSpecialtyMapperDto.getUnitSpecialtyMapperDtosList()
						.iterator();
				while (itr.hasNext()) {
					UnitSpecialtyMapperDto unMapperDto = new UnitSpecialtyMapperDto();

					UnitSpecialtyMapperDto obj = itr.next();

					unMapperDto.setUnitId(unitSpecialtyMapperDto.getUnitId());
					unMapperDto.setOrganizationId(unitSpecialtyMapperDto.getOrganizationId());
					unMapperDto.setSpecialityId(obj.getSpecialityId());
					unMapperDto.setStatus('A');
					unMapperDto.setCreatedBy(unitSpecialtyMapperDto.getCreatedBy());
					unMapperDto.setCreatedDate(unitSpecialtyMapperDto.getCreatedDate());
					unMapperDto.setUpdatedBy(unitSpecialtyMapperDto.getUpdatedBy());
					unMapperDto.setUpdatedDate(unitSpecialtyMapperDto.getUpdatedDate());

					iUnitSpecialityDao.saveUnitSpecialtiy(unMapperDto);
				}
//			} else {
//				return new Response(ERROR, null, null, null, null);
//			}

			return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getUnitSpecialityList(UnitSpecialtyMapperDto unitSpecialtyMapperDto) throws ApplicationException {
		try {

			return iUnitSpecialityDao.getUnitSpecialtiyList(unitSpecialtyMapperDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateStatusForUnitSpecialityList(UnitSpecialtyMapperDto unitSpecialtyMapperDto)
			throws ApplicationException {
		try {
			Response deptResponse = iUnitSpecialityDao.getUnitSpecialityById(unitSpecialtyMapperDto);
			if (deptResponse.getStatus().equals(SUCCESS)) {

				iUnitSpecialityDao.updateStatusForUnitSpecialityList(unitSpecialtyMapperDto);
				return new Response(SUCCESS, null, COMMON_UPDATE, null, null);

			} else {
				return new Response(ERROR, null, "ID doen not exist", null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getUnitSpecialityCount(UnitSpecialtyMapperDto unitSpecialtyMapperDto) throws ApplicationException {
		try {

			return iUnitSpecialityDao.getCount(unitSpecialtyMapperDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getSpecialityListByDoctorId(UnitSpecialtyMapperDto unitSpecialtyMapperDto)
			throws ApplicationException {
		try {
			return iUnitSpecialityDao.getSpecialityListByDoctorId(unitSpecialtyMapperDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getUnitSpecialityById(UnitSpecialtyMapperDto unitSpecialtyMapperDto) throws ApplicationException {
		try {
			return iUnitSpecialityDao.getUnitSpecialityById(unitSpecialtyMapperDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getActiveUnitSpecialityList(UnitSpecialtyMapperDto unitSpecialtyMapperDto)
			throws ApplicationException {
		try {
			return iUnitSpecialityDao.getActiveUnitSpecialityList(unitSpecialtyMapperDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	

}
