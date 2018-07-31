package com.param.service.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.param.billing.exception.ServiceException;
import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.service.dto.TPackageEitherorGroupDetailsDto;
import com.param.service.global.model.TPackageEitherorGroupDetails;

import in.param.common.dao.GenericDao;

@Repository
@SuppressWarnings({"rawtypes","unchecked"})
public class TPackageEitherOrGroupDetailsDaoImpl extends GenericDao<TPackageEitherorGroupDetails, Integer> implements ITPackageEitherOrGroupDetailsDao, ICommonConstants{

	public TPackageEitherOrGroupDetailsDaoImpl() {
		super(TPackageEitherorGroupDetails.class);
	}

	@Override
	public Response savePackageEitherOrGroupDetails(TPackageEitherorGroupDetailsDto detailsDto)throws ServiceException {
		try{
			TPackageEitherorGroupDetails details = new TPackageEitherorGroupDetails();
			details.setAvgPrice(detailsDto.getAvgPrice());
			details.setCreatedBy(detailsDto.getCreatedBy());
			details.setCreatedDate(detailsDto.getCreatedDate() != null && !detailsDto.getCreatedDate().isEmpty() ? GlobalCommonDateUtils.getDate(detailsDto.getCreatedDate(), "dd-M-yyyy HH:mm:ss") : new Date());
			details.setGroupId(detailsDto.getGroupId());
			details.setGroupPrice(detailsDto.getGroupPrice());
			details.setMaxPrice(detailsDto.getMaxPrice());
			details.setMinPrice(detailsDto.getMinPrice());
			details.setOrgId(detailsDto.getOrgId());
			details.setOrgUnitId(detailsDto.getOrgUnitId());
			details.setPackageId(detailsDto.getPackageId());
			details.setUpdatedBy(detailsDto.getUpdatedBy());
			details.setPackageGroupName(detailsDto.getPackageGroupName());
			details.setNumberServiceUsable(detailsDto.getNumberServiceUsable());
			details.setUpdatedDate(detailsDto.getUpdatedDate() != null && !detailsDto.getUpdatedDate().isEmpty() ? GlobalCommonDateUtils.getDate(detailsDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss") : new Date());
			details = save(details);
			return new Response<>(SUCCESS, SUCCESS_CODE, null, null, details);
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public Response getPackageEitherOrGroupDetailsByPackageId(int packageId) throws ServiceException {
		try{
			List<TPackageEitherorGroupDetailsDto> listTPackageEitherorGroupDetailsDto = sessionFactory.getCurrentSession().getNamedQuery("GET_EO_DETAILS_BY_PACKAGE_ID").setInteger("packageId", packageId).setResultTransformer(Transformers.aliasToBean(TPackageEitherorGroupDetailsDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, listTPackageEitherorGroupDetailsDto, null);
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
}
