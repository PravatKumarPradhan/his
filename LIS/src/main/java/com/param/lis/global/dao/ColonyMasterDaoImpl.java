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
import com.param.entity.lis.global.ColonyMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.ColonyMasterDto;
import com.param.lis.global.dto.GlobalCommonDto;
import com.param.lis.global.dto.ReportTypeMasterDto;

@Repository
@SuppressWarnings("rawtypes")
public class ColonyMasterDaoImpl extends  GenericDao<ColonyMaster, Integer> implements IColonyMasterDao, ICommonConstants, IError{

	public ColonyMasterDaoImpl() {
		super(ColonyMaster.class);
	}

	@Autowired
	private DozerBeanMapper mapper;

	@Override
	public Response addColonyMaster(ColonyMasterDto colonyMasterDto)throws ApplicationException {
		try{
			ColonyMaster colonyMaster = new ColonyMaster();
			colonyMaster.setCreatedBy(colonyMasterDto.getCreatedBy());
			colonyMaster.setCreatedDate(colonyMasterDto.getCreatedDate());
			colonyMaster.setCode(colonyMasterDto.getCode());
			colonyMaster.setDesc(colonyMasterDto.getDesc());
			colonyMaster.setStatus(colonyMasterDto.getStatus());
			colonyMaster.setOrgId(colonyMasterDto.getOrgId());
			colonyMaster.setUpdatedBy(colonyMasterDto.getUpdatedBy());
			colonyMaster.setUpdatedDate(colonyMasterDto.getUpdatedDate());
			colonyMaster = save(colonyMaster);
			return new Response<>(SUCCESS, SUCCESS_200,COLONY_ADD_SUCC, null,null);
		}catch(HibernateException he){
			he.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,COLONY_ADD_FAIL, null,null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response checkColonyMaster(ColonyMasterDto colonyMasterDto)throws ApplicationException {
		try
		{
			List<GlobalCommonDto> listUnitMasteDtosessionFactory = sessionFactory.getCurrentSession().getNamedQuery("GET_COLONY_TYPE_BY_BACTERIA_CODE").setString("code", colonyMasterDto.getCode().trim().toLowerCase()).setInteger("orgId", colonyMasterDto.getOrgId()).setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).list();
			return new Response(SUCCESS, null, null, listUnitMasteDtosessionFactory, null);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getColonyMasterById(Integer orgId, Integer  colonyId)
			throws ApplicationException {
		
		try
		{
			GlobalCommonDto containerMaster = (GlobalCommonDto) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_COLONY_TYPE_BY_BACTERIA_ID").setInteger("orgId", orgId)
					.setInteger("colonyId", colonyId)
                    .setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class))					
					.uniqueResult();
			
			return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, containerMaster);

		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500,COLONY_NOT_FOUND, null, null);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response updateColonyMaster(ColonyMasterDto colonyMasterDto)
			throws ApplicationException {
		try
		{
			ColonyMaster reportTypeMaster = mapper.map(colonyMasterDto, ColonyMaster.class,"ColonyMasterDtoTOColonyMaster");
			update(reportTypeMaster);
			return new Response(SUCCESS, null, null, null, null);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500,COLONY_UPDATE_FAIL, null, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response ActivateInactivateColonyMaster(Integer orgId,
			Integer colonyId, Character colonyStatus)
			throws ApplicationException {

		try
		{
			ColonyMaster colonyMaster = findById(colonyId);
			if (colonyMaster.getColonyId() != 0)
			{
				colonyMaster.setStatus(colonyStatus);;
				ColonyMaster colonyMst = update(colonyMaster);
				return new Response(SUCCESS, SUCCESS_200,COLONY_ACTIVATE_SUCC, null, colonyMst);
				
			} else
			{
				return new Response(ERROR, ERR_500,COLONY_NOT_FOUND, null, null);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500,COLONY_NOT_FOUND, null, null);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response listColonyMaster(Integer orgId, Integer offset,
			Integer recordPerPage) throws ApplicationException {
		try
		{
			List<GlobalCommonDto> lisColonyMaster = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PAGINATED_COLONY_MASTER_LIST").setInteger("orgId", orgId)
					.setFirstResult(offset != null ? offset : 0)
					.setMaxResults(recordPerPage != null ? recordPerPage : 10)
					.setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).list();
			return new Response(SUCCESS, null, null, lisColonyMaster, null);
		
			
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public Response getToTalColonyMaster(Integer orgId)
			throws ApplicationException {
		try
		{
			BigInteger getAntibioticsMasterCount = (BigInteger) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_TOTAL_COLONY_RECORD").setInteger("orgId", orgId).uniqueResult();
			
			
			if (getAntibioticsMasterCount.compareTo(BigInteger.ZERO) == 1)
			{
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, getAntibioticsMasterCount);
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
	public Response updateCheckColonyCodeAvaiable(
			ColonyMasterDto colonyMasterDto)
			throws ApplicationException {
		try {
			List<GlobalCommonDto> listCheckPhlebotomyCodeFactory = sessionFactory
					.getCurrentSession()
					.getNamedQuery("UPDATE_GET_COLONY_TYPE_BY_BACTERIA_CODE")
					.setString("code",colonyMasterDto.getCode().trim().toLowerCase())
					.setInteger("orgId", colonyMasterDto.getOrgId())
					.setInteger("colonyId",	colonyMasterDto.getColonyId())
					.setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).list();
			return new Response(SUCCESS, null, null,listCheckPhlebotomyCodeFactory, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


}
