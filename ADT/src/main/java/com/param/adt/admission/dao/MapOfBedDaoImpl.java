package com.param.adt.admission.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.transfer.dto.TransferDto;
import com.param.adt.transfer.model.Transfer;
import com.param.global.dto.AdmissionDto;

import in.param.common.dao.GenericDao;
@Repository
@SuppressWarnings({"rawtypes","unchecked"})
public class MapOfBedDaoImpl extends GenericDao<Transfer, Integer> implements IMapOfBedDao,ICommonConstants 
{

	public MapOfBedDaoImpl() {
		super(Transfer.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Response getTransferRequestListByAdmissoinId(TransferDto transferDto) {
		try {
			List<TransferDto> pendingTransferRequestList = sessionFactory.getCurrentSession()

					.getNamedQuery("GET_TRANSFER_REQ_LIST_BY_ADMISSION_ID")
					.setInteger("organizationId", transferDto.getOrganizationId())
					.setInteger("unitId", transferDto.getUnitId())
					.setInteger("admissionId", transferDto.getAdmissionId())
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
