package com.param.billing.unit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.billing.unit.dao.ITariffMasterDao;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.TariffMasterDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class TariffMasterServiceImpl implements ITariffMasterService, ICommonConstants {

	@Autowired
	ITariffMasterDao iTariffMasterDao;

	@Override
	public Response saveTariffMaster(TariffMasterDto tariffMasterDto) throws ApplicationException {
		try {
			
			Response response = iTariffMasterDao.getTariffListByName(tariffMasterDto);
			
			if (response.getListObject() != null && response.getListObject().size() > 0)
				return new Response(ERROR, null, ALREADY_EXIST, null, null);
			else
			{
				Response res= iTariffMasterDao.saveTariffMaster(tariffMasterDto);
				if(res.getStatus().equals(SUCCESS))
				{
					return iTariffMasterDao.saveTariffPaymentEntitlementMapper((TariffMasterDto)res.getObject());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getTariffMasterList(TariffMasterDto tariffMasterDto) throws ApplicationException {
		try {
			return iTariffMasterDao.getTariffMasterList(tariffMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getPaymentEntitlementListByTariffid(TariffMasterDto tariffMasterDto) throws ApplicationException {
		try {
			return iTariffMasterDao.getPaymentEntitlementListByTariffid(tariffMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getTariffListById(TariffMasterDto tariffMasterDto) throws ApplicationException {
		try {
			return iTariffMasterDao.getTariffListById(tariffMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateTariffMaster(TariffMasterDto tariffMasterDto) throws ApplicationException {
		try {
			Response response = iTariffMasterDao.getTariffListByNameNotId(tariffMasterDto);
			if (response.getObject() != null) {
				return new Response(ERROR, null, ALREADY_EXIST, null, null);
			} else {
				Response res = iTariffMasterDao.updateTariffMaster(tariffMasterDto);
				if(res.getStatus().equals(SUCCESS))
				{
					if(tariffMasterDto.getApplicablePaymentEntitlementIdListOld().contains(tariffMasterDto.getApplicablePaymentEntitlementIdList())==true){
						return new Response(SUCCESS, null, COMMON_UPDATE, null, null);
					}
					else
					{
						Response res2= iTariffMasterDao.inactiveOldPaymentEntitlements(tariffMasterDto);
						if(res2.getStatus().equals(SUCCESS)&&tariffMasterDto.getApplicablePaymentEntitlementIdList().size()>0)
						{
							iTariffMasterDao.saveTariffPaymentEntitlementMapper(tariffMasterDto);
						}
						return new Response(SUCCESS, null, COMMON_UPDATE, null, null);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateStatusForTariff(TariffMasterDto tariffMasterDto) throws ApplicationException {
		try {
				return iTariffMasterDao.updateStatusForTariff(tariffMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateStatusForTariffPaymentMapper(TariffMasterDto tariffMasterDto) throws ApplicationException {
		try {
			return iTariffMasterDao.updateStatusForTariffPaymentMapper(tariffMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);

	}

	@Override
	public Response getActiveTariffMasterList(TariffMasterDto tariffMasterDto) throws ApplicationException {
		try {
			return iTariffMasterDao.getActiveTariffMasterList(tariffMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getPaymentEntitlementListByTariffIdList(TariffMasterDto tariffMasterDto)
			throws ApplicationException {
		try {
			return iTariffMasterDao.getPaymentEntitlementListByTariffIdList(tariffMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
