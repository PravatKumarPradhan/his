package com.param.service.dao;

import java.util.List;

import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

import com.param.billing.dto.TPackageConsumptionCapDetailsDto;
import com.param.billing.exception.ServiceException;
import com.param.billing.packages.model.TPackageConsumptionCapDetails;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.service.dto.PackageSearchRequestDto;
import com.param.service.dto.TPackageCapDetailsDto;

@Repository
public class PackageConsumptionDaoImpl extends GenericDao<TPackageConsumptionCapDetails, Integer> implements IPackageConsumptionDao, ICommonConstants {

	public PackageConsumptionDaoImpl() {
		super(TPackageConsumptionCapDetails.class);
	}

	@Override
	public Response getPackageServiceByPackageType(
			PackageSearchRequestDto packageSearchRequestDto)
			throws ApplicationException {
		
		try{
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public Response getPackageConsumptionCapDetails(
			Integer packageConsumptionMasterId) throws ApplicationException {
		try{
			List<TPackageConsumptionCapDetailsDto> listTPackageCapDetailsDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PACKAGE_CONSUMPTION_CAP_DETAILS")
					.setInteger("packageConsumptionMasterId", packageConsumptionMasterId)
					.setResultTransformer(Transformers.aliasToBean(TPackageConsumptionCapDetailsDto.class))
					.list();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, listTPackageCapDetailsDto, null);
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

}
