package com.param.adt.transfer.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.global.dto.BedMasterDto;
import com.param.adt.transfer.dto.TransferDto;
import com.param.adt.transfer.model.Transfer;
import com.param.global.dto.AdmissionDto;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;
@Repository
@SuppressWarnings({"rawtypes","unchecked"})
public class BedToBedTransferDaoImpl extends GenericDao<Transfer, Integer> implements IBedToBedTransferDao,ICommonConstants {

	public BedToBedTransferDaoImpl() {
		super(Transfer.class);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public Response getInitiateTransferRequestList(TransferDto transferDto) throws ApplicationException {
		try {
			List<TransferDto> pendingTransferRequestList = sessionFactory.getCurrentSession()

					.getNamedQuery("GET_INITIATE_TRANSFER_REQ_LIST")
					.setInteger("organizationId", transferDto.getOrganizationId())
					.setInteger("unitId", transferDto.getUnitId())
					.setParameterList("wards", transferDto.getWardList())
					.setResultTransformer(Transformers.aliasToBean(AdmissionDto.class)).list();
			return new Response(SUCCESS, null, null, pendingTransferRequestList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}


	@Override
	public Response getBedListByWardId(BedMasterDto bedMasterDto) {
		try {
			List<TransferDto> pendingTransferRequestList = sessionFactory.getCurrentSession()

					.getNamedQuery("GET_BED_LIST_BY_WARD_ID")
					.setInteger("organizationId", bedMasterDto.getOrganizationId())
					.setInteger("unitId", bedMasterDto.getUnitId())
					.setInteger("wardId", bedMasterDto.getWardId())
					.setResultTransformer(Transformers.aliasToBean(AdmissionDto.class)).list();
			return new Response(SUCCESS, null, null, pendingTransferRequestList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
