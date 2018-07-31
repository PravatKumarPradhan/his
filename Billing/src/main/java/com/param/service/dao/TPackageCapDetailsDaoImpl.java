package com.param.service.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.param.billing.exception.ServiceException;
import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.service.dto.TPackageCapDetailsDto;
import com.param.service.global.model.TPackageCapDetails;

import in.param.common.dao.GenericDao;

@Repository
@SuppressWarnings({"rawtypes","unchecked"})
public class TPackageCapDetailsDaoImpl extends GenericDao<TPackageCapDetails, Integer> implements ITPackageCapDetailsDao, ICommonConstants{

	public TPackageCapDetailsDaoImpl() {
		super(TPackageCapDetails.class);
	}

	@Override
	public Response saveTPackageCapDetails(TPackageCapDetailsDto detailsDto) throws ServiceException {
		try{
			TPackageCapDetails capDetails = new TPackageCapDetails();
			capDetails.setCreatedBy(detailsDto.getCreatedBy());
			capDetails.setCreatedDate(detailsDto.getCreatedDate() != null && !detailsDto.getCreatedDate().isEmpty() ? GlobalCommonDateUtils.getDate(detailsDto.getCreatedDate(), "dd-M-yyyy HH:mm:ss") : new Date());
			capDetails.setDepartmentCapAmount(detailsDto.getDepartmentCapAmount());
			capDetails.setDepartmentId(detailsDto.getDepartmentId());
			capDetails.setIsServiceItem(detailsDto.getIsServiceItem());
			capDetails.setOrganisationId(detailsDto.getOrganisationId());
			capDetails.setPackageId(detailsDto.getPackageId());
			capDetails.setProductCateroyCapAmount(detailsDto.getProductCateroyCapAmount());
			capDetails.setProductCateroyId(detailsDto.getProductCateroyId());
			capDetails.setStatus(ACTIVE);
			capDetails.setSubDepartmentCapAmount(detailsDto.getSubDepartmentCapAmount());
			capDetails.setSubDepartmentId(detailsDto.getSubDepartmentId());
			capDetails.setUnitId(detailsDto.getUnitId());
			capDetails = save(capDetails);
			return new Response<>(SUCCESS, SUCCESS_CODE, null, null, capDetails);
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public Response getPackageCapDetailsByPackageIdForBilling(int packageId) throws ServiceException {
		try{
			List<TPackageCapDetailsDto> listTPackageCapDetailsDto = sessionFactory.getCurrentSession().getNamedQuery("GET_CAP_DETAILS_BY_PACKAGE_ID_FOR_BILLING").setInteger("packageId", packageId).setResultTransformer(Transformers.aliasToBean(TPackageCapDetailsDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, listTPackageCapDetailsDto, null);
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public Response getPackageCapDetailsByPackageId(int packageId) throws ServiceException {
		try{
			List<TPackageCapDetailsDto> listTPackageCapDetailsDto = sessionFactory.getCurrentSession().getNamedQuery("GET_CAP_DETAILS_BY_PACKAGE_ID").setInteger("packageId", packageId).setResultTransformer(Transformers.aliasToBean(TPackageCapDetailsDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, listTPackageCapDetailsDto, null);
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
}
