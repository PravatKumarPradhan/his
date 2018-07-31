package com.param.billing.global.transaction.dao;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.param.billing.exception.BillingException;
import com.param.billing.global.dto.BillingStatusMapperDto;
import com.param.billing.global.transaction.model.BillingStatusMapper;
import com.param.billing.global.transaction.model.BillingStatusMapperId;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;

import in.param.common.dao.GenericDao;

@Repository
@SuppressWarnings("rawtypes")
public class BillStatusMapperDaoImpl extends GenericDao<BillingStatusMapper, BillingStatusMapperId> implements IBillStatusMapperDao, ICommonConstants{

	public BillStatusMapperDaoImpl() {
		super(BillingStatusMapper.class);
	}

	@Override
	public Response saveBillStatus(BillingStatusMapperDto mapperDto) throws BillingException {
		try{
			Date today = new Date();
			BillingStatusMapper mapper = new BillingStatusMapper();
			mapper.setBillingStatusMapperId(mapperDto.getBillingStatusMapperId());
			mapper.setCreatedBy(mapperDto.getCreatedBy());
			mapper.setCreatedDate(today);
			mapper.setDateTime(today);
			mapper.setOrgnisationId(mapperDto.getOrgnisationId());
			mapper.setUnitId(mapperDto.getUnitId());
			mapper.setUpdatedBy(mapperDto.getUpdatedBy());
			mapper.setUpdatedDate(null);
			mapper.setStatus(mapperDto.getStatus());
			mapper = save(mapper);
			return new Response<>(SUCCESS, SUCCESS_CODE, null, null, null);
		}catch(Exception e){
			e.printStackTrace();
			throw new BillingException(e.getMessage());
		}
	}

}
