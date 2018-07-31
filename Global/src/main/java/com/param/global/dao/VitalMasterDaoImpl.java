package com.param.global.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.ModalityMasterDto;
import com.param.global.dto.VitalMasterDto;
import com.param.global.model.VitalMaster;

@Repository
@SuppressWarnings({"rawtypes","unchecked"})
public class VitalMasterDaoImpl extends GenericDao<VitalMaster, Integer> implements IVitalMasterDao, ICommonConstants {

	public VitalMasterDaoImpl() {
		super(VitalMaster.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Response getVitalMasterList(VitalMasterDto vitalMasterDto)throws ApplicationException {
		try {
			List<VitalMasterDto> VitalMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_VITAL_MASTER_LIST")
					.setInteger("orgId", vitalMasterDto.getOrganizationId())
					.setInteger("unitId", vitalMasterDto.getUnitId())	
					.setResultTransformer(Transformers.aliasToBean(VitalMasterDto.class)).list();
			return new Response(SUCCESS, null, null, VitalMasterDtoList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, null, null, null);
	}

}
