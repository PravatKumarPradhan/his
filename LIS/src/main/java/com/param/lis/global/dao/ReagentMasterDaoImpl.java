package com.param.lis.global.dao;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

import java.math.BigInteger;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.param.entity.lis.global.ReagentMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.GlobalCommonDto;
import com.param.lis.global.dto.ReagentMasterDto;

@Repository
@SuppressWarnings("rawtypes")
public class ReagentMasterDaoImpl extends  GenericDao<ReagentMaster, Integer> implements IReagentMasterDao, ICommonConstants, IError{

	public ReagentMasterDaoImpl() {
		super(ReagentMaster.class);
	}

	@Autowired
	private DozerBeanMapper mapper;

	@Override
	public Response addReagentMaster(ReagentMasterDto reagentMasterDto)throws ApplicationException {
		try{
			ReagentMaster reagentMaster = new ReagentMaster();
			reagentMaster.setCreatedBy(reagentMasterDto.getCreatedBy());
			reagentMaster.setCreatedDate(reagentMasterDto.getCreatedDate());
			reagentMaster.setCode(reagentMasterDto.getCode());
			reagentMaster.setDesc(reagentMasterDto.getDesc());
			reagentMaster.setStatus(reagentMasterDto.getStatus());
			reagentMaster.setOrgId(reagentMasterDto.getOrgId());
			reagentMaster.setUpdatedBy(reagentMasterDto.getUpdatedBy());
			reagentMaster.setUpdatedDate(reagentMasterDto.getUpdatedDate());
			reagentMaster = save(reagentMaster);
			return new Response<>(SUCCESS, SUCCESS_200, REAGENT_ADD_SUCC, null,null);
		}catch(HibernateException he){
			he.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201, REAGENT_ADD_FAIL, null,null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response checkReagentCodeAvaiable(ReagentMasterDto reagentMasterDto)throws ApplicationException {
		try
		{
			List<GlobalCommonDto> listUnitMasteDtosessionFactory = sessionFactory.getCurrentSession().getNamedQuery("GET_REAGENT_BY_REAGENT_CODE").setString("code", reagentMasterDto.getCode().trim().toLowerCase()).setInteger("orgId", reagentMasterDto.getOrgId()).setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).list();
			return new Response(SUCCESS, null, null, listUnitMasteDtosessionFactory, null);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getReagentMasterById(Integer orgId, Integer  reagentId)
			throws ApplicationException {
		
		try
		{
			GlobalCommonDto containerMaster = (GlobalCommonDto) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_REAGENT_BY_REAGENT_ID").setInteger("orgId", orgId)
					.setInteger("reagentId", reagentId)
                    .setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class))					
					.uniqueResult();
			
			return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, containerMaster);

		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, REAGENT_NOT_FOUND, null, null);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response updateReagentMaster(ReagentMasterDto reagentMasterDto)
			throws ApplicationException {
		try
		{
			ReagentMaster reagentMaster = mapper.map(reagentMasterDto, ReagentMaster.class,"ReagentMasterDtoTOReagentMaster");
			update(reagentMaster);
			return new Response(SUCCESS, null, null, null, null);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500, REAGENT_UPDATE_FAIL, null, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response ActivateInactivateReagentMaster(Integer orgId,
			Integer unitId, Character unitStatus)
			throws ApplicationException {

		try
		{
			ReagentMaster reagentMaster = findById(unitId);
			if (reagentMaster.getReagentId() != 0)
			{
				reagentMaster.setStatus(unitStatus);;
				ReagentMaster reagentMst = update(reagentMaster);
				return new Response(SUCCESS, SUCCESS_200, REAGENT_ACTIVATE_SUCC, null, reagentMst);
				
			} else
			{
				return new Response(ERROR, ERR_500,REAGENT_NOT_FOUND, null, null);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500,REAGENT_NOT_FOUND, null, null);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response listReagentMaster(Integer orgId, Integer offset,
			Integer recordPerPage) throws ApplicationException {
		try
		{
			List<GlobalCommonDto> listUnitMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PAGINATED_REAGENT_MASTER_LIST").setInteger("orgId", orgId)
					.setFirstResult(offset != null ? offset : 0)
					.setMaxResults(recordPerPage != null ? recordPerPage : 10)
					.setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).list();
			return new Response(SUCCESS, null, null, listUnitMasterDto, null);
		
			
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public Response getToTalReagentMasterRecord(Integer orgId)
			throws ApplicationException {
		try
		{
			BigInteger reagentTotalRecordCount = (BigInteger) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_TOTAL_REAGENT_RECORD").setInteger("orgId", orgId).uniqueResult();
			
			
			if (reagentTotalRecordCount.compareTo(BigInteger.ZERO) == 1)
			{
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, reagentTotalRecordCount);
			} else
			{
				return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, BigInteger.ZERO);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public Response updateCheckReagentCodeAvaiable(
			ReagentMasterDto reagentMasterDto) throws ApplicationException {
		try {
			List<GlobalCommonDto> listCheckPhlebotomyCodeFactory = sessionFactory
					.getCurrentSession()
					.getNamedQuery("UPDATE_GET_REAGENT_BY_REAGENT_CODE")
					.setString("code",reagentMasterDto.getCode().trim().toLowerCase())
					.setInteger("orgId", reagentMasterDto.getOrgId())
					.setInteger("reagentId",	reagentMasterDto.getReagentId())
					.setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).list();
			return new Response(SUCCESS, null, null,listCheckPhlebotomyCodeFactory, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


}
