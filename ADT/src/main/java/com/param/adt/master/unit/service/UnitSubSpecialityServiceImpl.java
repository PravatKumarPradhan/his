package com.param.adt.master.unit.service;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.unit.dao.IUnitSubSpecialityDao;
import com.param.adt.master.unit.dto.UnitSpecialtyMapperDto;
import com.param.adt.master.unit.dto.UnitSubSpecialityMapperDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class UnitSubSpecialityServiceImpl implements IUnitSubSpecialityService, ICommonConstants {

	@Autowired
	IUnitSubSpecialityDao iUnitSubSpecialityDao;

	@Override
	public Response saveUnitSubSpecialtiy(UnitSubSpecialityMapperDto unitSubSpecialityMapperDto)
			throws ApplicationException {
		try {
//			Response resTrunc = iUnitSubSpecialityDao.truncateUnitSubSpecialtiy(unitSubSpecialityMapperDto);
//			if (resTrunc.getStatus().equals(SUCCESS)) {
				Iterator<UnitSubSpecialityMapperDto> itr = unitSubSpecialityMapperDto
						.getUnitSubSpecialityMapperDtosList().iterator();
				while (itr.hasNext()) {
					UnitSubSpecialityMapperDto subSpecialityMapperDto = new UnitSubSpecialityMapperDto();

					UnitSubSpecialityMapperDto obj = itr.next();

					subSpecialityMapperDto.setUnitId(unitSubSpecialityMapperDto.getUnitId());
					subSpecialityMapperDto.setOrganizationId(unitSubSpecialityMapperDto.getOrganizationId());
					subSpecialityMapperDto.setSpecialityId(unitSubSpecialityMapperDto.getSpecialityId());
					subSpecialityMapperDto.setSubSpecialityId(obj.getSubSpecialityMasterId());
					subSpecialityMapperDto.setStatus('A');
					subSpecialityMapperDto.setCreatedBy(unitSubSpecialityMapperDto.getCreatedBy());
					subSpecialityMapperDto.setCreatedDate(unitSubSpecialityMapperDto.getCreatedDate());
					subSpecialityMapperDto.setUpdatedBy(unitSubSpecialityMapperDto.getUpdatedBy());
					subSpecialityMapperDto.setUpdatedDate(unitSubSpecialityMapperDto.getUpdatedDate());

					iUnitSubSpecialityDao.saveUnitSubSpecialitiy(subSpecialityMapperDto);
				}
//			} else {
//				return new Response(ERROR, null, null, null, null);
//			}

			return new Response(SUCCESS, null, COMMON_UPDATE, null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);

	}

	@Override
	public Response getUnitSubSpecialityList(UnitSubSpecialityMapperDto unitSubSpecialityMapperDto)
			throws ApplicationException {
		try {

			return iUnitSubSpecialityDao.getUnitSubSpecialityList(unitSubSpecialityMapperDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateStatusForUnitSubSpeciality(UnitSubSpecialityMapperDto unitSubSpecialityMapperDto)
			throws ApplicationException {
		try {
			Response deptResponse = iUnitSubSpecialityDao.getUnitSubSpecialityById(unitSubSpecialityMapperDto);
			if (deptResponse.getStatus().equals(SUCCESS)) {

				iUnitSubSpecialityDao.updateStatusForUnitSpecialityList(unitSubSpecialityMapperDto);
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
	public Response getUnitSubSpecialityCount(UnitSubSpecialityMapperDto unitSubSpecialityMapperDto)
			throws ApplicationException {
		try {

			return iUnitSubSpecialityDao.getUnitSubSpecialityCount(unitSubSpecialityMapperDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getSubSpecialityBySpecialityId(UnitSubSpecialityMapperDto unitSubSpecialityMapperDto)
			throws ApplicationException {
		try {
			return iUnitSubSpecialityDao.getSubSpecialityBySpecialityId(unitSubSpecialityMapperDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getSubSpecialityBySpecialityArray(UnitSubSpecialityMapperDto unitSubSpecialityMapperDto)
			throws ApplicationException {
		try {
			return iUnitSubSpecialityDao.getSubSpecialityBySpecialityArray(unitSubSpecialityMapperDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	@Override
	public Response getActiveUnitSubSpecialityList(UnitSubSpecialityMapperDto unitSpecialtyMapperDto)
			throws ApplicationException {
		try {

			return iUnitSubSpecialityDao.getActiveUnitSubSpecialityList(unitSpecialtyMapperDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getSubSpecialityBySpecialityArrayForTariff(UnitSubSpecialityMapperDto unitSubSpecialityMapperDto)
			throws ApplicationException {
		try {
			return iUnitSubSpecialityDao.getSubSpecialityBySpecialityArrayForTariff(unitSubSpecialityMapperDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
