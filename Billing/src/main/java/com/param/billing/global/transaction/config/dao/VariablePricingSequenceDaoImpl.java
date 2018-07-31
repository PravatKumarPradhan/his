package com.param.billing.global.transaction.config.dao;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.billing.common.ICommonConstants;
import com.param.billing.common.Response;
import com.param.billing.global.transaction.config.dto.VariablePricingSequenceDto;
import com.param.billing.global.transaction.config.model.VariablePricingSequenceMaster;

@Repository
@SuppressWarnings({"unchecked","rawtypes"})
public class VariablePricingSequenceDaoImpl extends GenericDao<VariablePricingSequenceMaster, Integer> implements IVariablePricingSequenceDao, ICommonConstants {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private DozerBeanMapper mapper;
	
	public VariablePricingSequenceDaoImpl(){
		super(VariablePricingSequenceMaster.class);
	}

	@Override
	public Response saveVariablePricingSequenceMaster(
			VariablePricingSequenceDto variablePricingSequenceDto)
			throws ApplicationException {
		
		try {
			VariablePricingSequenceMaster variablePricingSequenceMaster = mapper.map(variablePricingSequenceDto, VariablePricingSequenceMaster.class,
					"variablePricingSequenceDto_to_variablePricingSequenceMaster");
			variablePricingSequenceMaster = save(variablePricingSequenceMaster);

			return new Response(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, variablePricingSequenceMaster);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getVariablePricingSequenceByOrganisationId(
			VariablePricingSequenceDto variablePricingSequenceDto) throws ApplicationException {
		
		List<VariablePricingSequenceDto> listVariablePricingDto = sessionFactory.getCurrentSession().
				getNamedQuery("GET_VARIABLE_PRICING_SEQ_BY_ORG_ID")
				.setInteger("orgId", variablePricingSequenceDto.getOrgId())
				.setInteger("unitId", variablePricingSequenceDto.getUnitId())
				.setInteger("visitType", variablePricingSequenceDto.getVisitType())
				.setResultTransformer(Transformers.aliasToBean(VariablePricingSequenceDto.class)).list();
		
		return new Response(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, listVariablePricingDto, null);
	}
	
}
