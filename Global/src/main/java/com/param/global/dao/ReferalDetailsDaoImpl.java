package com.param.global.dao;

import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.ReferalDetailsDto;
import com.param.global.model.ReferalDetails;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;
@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReferalDetailsDaoImpl extends GenericDao<ReferalDetails, Integer>implements IReferalDetailsDao,ICommonConstants{

	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	DozerBeanMapper mapper;
	
	
	public ReferalDetailsDaoImpl() {
		super(ReferalDetails.class);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public Response saveReferalDetails(ReferalDetailsDto referalDetailsDto) throws ApplicationException {
		try {
			ReferalDetails referalDetails = mapper.map(referalDetailsDto,
					ReferalDetails.class, "ReferalDetailsDto_to_ReferalDetails");
			save(referalDetails);
			return new Response(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, null);
		} catch (HibernateException he) {

			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
