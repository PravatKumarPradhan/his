package com.param.adt.master.unit.service;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.unit.dao.IUnitICUDao;
import com.param.adt.master.unit.dto.UnitICUTypeMapperDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class UnitICUServiceImpl implements IUnitICUService, ICommonConstants {

	@Autowired
	IUnitICUDao iUnitICUDao;

	@Override
	public Response saveUnitSubSpecialtiy(UnitICUTypeMapperDto unitICUTypeMapperDto) throws ApplicationException {
		try {
			Response resTrunc = iUnitICUDao.truncateUnitICUType(unitICUTypeMapperDto);
			if (resTrunc.getStatus() == SUCCESS) {

				Iterator<UnitICUTypeMapperDto> itr = unitICUTypeMapperDto.getIcuTypeMapperDtosList().iterator();
				while (itr.hasNext()) {
					UnitICUTypeMapperDto unIcuTypeMapperDto = new UnitICUTypeMapperDto();

					UnitICUTypeMapperDto obj = itr.next();

					unIcuTypeMapperDto.setUnitId(obj.getUnitId());
					unIcuTypeMapperDto.setOrganizationId(obj.getOrganizationId());
					unIcuTypeMapperDto.setStatus('A');
					unIcuTypeMapperDto.setCreatedBy(obj.getCreatedBy());
					unIcuTypeMapperDto.setCreatedDate(obj.getCreatedDate());
					unIcuTypeMapperDto.setUpdatedBy(obj.getUpdatedBy());
					unIcuTypeMapperDto.setUpdatedDate(obj.getUpdatedDate());

					iUnitICUDao.saveUnitICUType(unIcuTypeMapperDto);
				}
				return new Response(SUCCESS, null, COMMON_UPDATE, null, null);
			} else {
				return new Response(ERROR, null, null, null, null);

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getUnitICUTypeList(UnitICUTypeMapperDto unitICUTypeMapperDto) throws ApplicationException {
		try {

			return iUnitICUDao.getUnitICUTypeList(unitICUTypeMapperDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateStatusForUnitICUType(UnitICUTypeMapperDto unitICUTypeMapperDto) throws ApplicationException {
		try {
			Response deptResponse = iUnitICUDao.getUnitICUTypeById(unitICUTypeMapperDto);
			if (deptResponse.getStatus().equals(SUCCESS)) {

				iUnitICUDao.updateStatusForUnitICUType(unitICUTypeMapperDto);
				return new Response(SUCCESS, null, COMMON_UPDATE, null, null);

			} else {
				return new Response(ERROR, null, "ID Does not exist", null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getUnitICUTypeCount(UnitICUTypeMapperDto unitICUTypeMapperDto) throws ApplicationException {
		try {

			return iUnitICUDao.getCount(unitICUTypeMapperDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getActiveUnitICUTypeList(UnitICUTypeMapperDto unitICUTypeMapperDto) throws ApplicationException {
		try {

			return iUnitICUDao.getActiveUnitICUTypeList(unitICUTypeMapperDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
