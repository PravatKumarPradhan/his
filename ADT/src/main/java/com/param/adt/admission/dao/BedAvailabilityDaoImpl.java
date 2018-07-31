


package com.param.adt.admission.dao;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.adt.admission.dto.BedAvailibailityDto;
import com.param.adt.common.ADTCommonDateUtils;
import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.global.dto.BedCategoryMasterDto;
import com.param.global.model.BedCategoryMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;
@Repository
@SuppressWarnings({"rawtypes","unchecked"})
public class BedAvailabilityDaoImpl extends GenericDao<BedCategoryMaster, Integer> implements IBedAvailabilityDao,ICommonConstants
{

	public BedAvailabilityDaoImpl() {
		super(BedCategoryMaster.class);
		// TODO Auto-generated constructor stub
	}
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public Response getBedCategoryList(BedCategoryMasterDto bedCategoryMasterDto) throws ApplicationException {
		try {
			List<BedCategoryMasterDto> bedAllocationDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_BED_CATEGORY_LIST_BY_ORG_UNIT")
					.setInteger("organizationId", bedCategoryMasterDto.getOrganizationId())
					.setInteger("unitId", bedCategoryMasterDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(BedCategoryMasterDto.class)).list();
			return new Response(SUCCESS, null, null, bedAllocationDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response bedAvailiblityList(BedAvailibailityDto bedAvailibailityDto) {
		Session session=sessionFactory.openSession();
		
		List<BedAvailibailityDto> bedAvailibailityDtosList = session.getNamedQuery("GET_WATING_LIST_DETAILS")
				.setInteger("organizationId", bedAvailibailityDto.getOrganizationId())
				.setInteger("unitId", bedAvailibailityDto.getUnitId())
				.setDate("startDate", ADTCommonDateUtils.getDate(bedAvailibailityDto.getStartDate(), "yyyy-M-dd"))
				.setDate("endDate", ADTCommonDateUtils.getDate(bedAvailibailityDto.getEndDate(), "yyyy-M-dd"))
				.setResultTransformer(Transformers.aliasToBean(BedAvailibailityDto.class)).list();
		return new Response(SUCCESS, null, null, bedAvailibailityDtosList, null);
	}

}
