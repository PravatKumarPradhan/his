package com.param.global.unit.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.param.adt.master.unit.model.WingMaster;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.unit.dto.WingMasterDto;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class WingMasterDaoImpl extends GenericDao<WingMaster, Integer> implements IWingMasterDao , ICommonConstants{

	public WingMasterDaoImpl() {
		super(WingMaster.class);
	}

	
	@Override
	public Response getWingMasterListByOrgAndUnit(WingMasterDto wingMasterDto)throws ApplicationException {
		try{
			List<WingMasterDto> listWingMasterDto = sessionFactory.getCurrentSession().getNamedQuery("GET_WING_MASTER_LIST").setInteger("organizationId", wingMasterDto.getOrganizationId()).setInteger("unitId", wingMasterDto.getUnitId()).setResultTransformer(Transformers.aliasToBean(WingMasterDto.class)).list();
			listWingMasterDto.forEach(obj->System.out.println(obj.getWingCode()));
			return new Response<>(SUCCESS, SUCCESS_CODE, null, listWingMasterDto, null);
		}catch(HibernateException he){
			he.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, ERROR_CODE, null, null, null);
	}

}
