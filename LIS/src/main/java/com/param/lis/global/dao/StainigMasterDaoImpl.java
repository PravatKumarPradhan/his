package com.param.lis.global.dao;

import java.math.BigInteger;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.param.entity.lis.global.StainigMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.GlobalCommonDto;
import com.param.lis.global.dto.StainigMasterDto;
import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings("rawtypes")
public class StainigMasterDaoImpl extends  GenericDao<StainigMaster, Integer> implements IStainigMasterDao, ICommonConstants, IError{

	public StainigMasterDaoImpl() {
		super(StainigMaster.class);
	}

	@Autowired
	private DozerBeanMapper mapper;

	@Override
	public Response addStainigMaster(StainigMasterDto stainigMasterDto)throws ApplicationException {
		try{
			StainigMaster stainigMaster = new StainigMaster();
			stainigMaster.setCreatedBy(stainigMasterDto.getCreatedBy());
			stainigMaster.setCreatedDate(stainigMasterDto.getCreatedDate());
			stainigMaster.setCode(stainigMasterDto.getCode());
			stainigMaster.setDesc(stainigMasterDto.getDesc());
			stainigMaster.setStatus(stainigMasterDto.getStatus());
			stainigMaster.setOrgId(stainigMasterDto.getOrgId());
			stainigMaster.setUpdatedBy(stainigMasterDto.getUpdatedBy());
			stainigMaster.setUpdatedDate(stainigMasterDto.getUpdatedDate());
			stainigMaster = save(stainigMaster);
			return new Response<>(SUCCESS, SUCCESS_200,STAINIG_ADD_SUCC, null,null);
		}catch(HibernateException he){
			he.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,STAINIG_ADD_FAIL, null,null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response checkStainigMaster(StainigMasterDto organismMasterDto)throws ApplicationException {
		try
		{
			List<GlobalCommonDto> listStainigMasterDtosessionFactory = sessionFactory.getCurrentSession().getNamedQuery("GET_STAINIG_BY_STAINIG_CODE").setString("code", organismMasterDto.getCode().trim().toLowerCase()).setInteger("orgId", organismMasterDto.getOrgId()).setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).list();
			return new Response(SUCCESS, null, null, listStainigMasterDtosessionFactory, null);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getStainigMasterById(Integer orgId, Integer  stainigId)
			throws ApplicationException {
		
		try
		{
			GlobalCommonDto containerMaster = (GlobalCommonDto) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_STAINIG_BY_STAINIG_ID").setInteger("orgId", orgId)
					.setInteger("stainigId", stainigId)
                    .setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class))					
					.uniqueResult();
			
			return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, containerMaster);

		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500,STAINIG_NOT_FOUND, null, null);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response updateStainigMaster(StainigMasterDto organisumGroupMasterDto)
			throws ApplicationException {
		try
		{
			StainigMaster reportTypeMaster = mapper.map(organisumGroupMasterDto, StainigMaster.class,"StainigMasterDtoTOStainigMaster");
			update(reportTypeMaster);
			return new Response(SUCCESS, null, null, null, null);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500,STAINIG_UPDATE_FAIL, null, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response ActivateInactivateStainigMaster(Integer orgId,
			Integer organisumGroupId, Character organisumGroupStatus)
			throws ApplicationException {

		try
		{
			StainigMaster organisumGroupMaster = findById(organisumGroupId);
			if (organisumGroupMaster.getStainigId() != 0)
			{
				organisumGroupMaster.setStatus(organisumGroupStatus);;
				StainigMaster organisumGroupMst = update(organisumGroupMaster);
				return new Response(SUCCESS, SUCCESS_200,STAINIG_ACTIVATE_SUCC, null, organisumGroupMst);
				
			} else
			{
				return new Response(ERROR, ERR_500,STAINIG_NOT_FOUND, null, null);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500,STAINIG_NOT_FOUND, null, null);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response listStainigMaster(Integer orgId, Integer offset,
			Integer recordPerPage) throws ApplicationException {
		try
		{
			List<GlobalCommonDto> lisColonyMaster = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_STAINIG_MASTER_LIST").setInteger("orgId", orgId)
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
	public Response getToTalStainigMaster(Integer orgId)
			throws ApplicationException {
		try
		{
			BigInteger getOrganisumGroupMasterCount = (BigInteger) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_TOTAL_STAINIG_RECORD").setInteger("orgId", orgId).uniqueResult();
			
			
			if (getOrganisumGroupMasterCount.compareTo(BigInteger.ZERO) == 1)
			{
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, getOrganisumGroupMasterCount);
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
	public Response updateCheckStainigMasterCodeAvaiable(
			StainigMasterDto organisumGroupMasterDto)
			throws ApplicationException {
		try {
			List<GlobalCommonDto> listCheckPhlebotomyCodeFactory = sessionFactory
					.getCurrentSession()
					.getNamedQuery("UPDATE_GET_STAINIG_TYPE_BY_STAINIG_CODE")
					.setString("code",organisumGroupMasterDto.getCode().trim().toLowerCase())
					.setInteger("orgId", organisumGroupMasterDto.getOrgId())
					.setInteger("stainigId",	organisumGroupMasterDto.getStainigId())
					.setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).list();
			return new Response(SUCCESS, null, null,listCheckPhlebotomyCodeFactory, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


}
