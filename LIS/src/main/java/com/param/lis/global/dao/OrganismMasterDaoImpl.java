package com.param.lis.global.dao;

import java.math.BigInteger;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.param.entity.lis.global.OrganismMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.GlobalCommonDto;
import com.param.lis.global.dto.OrganismMasterDto;
import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings("rawtypes")
public class OrganismMasterDaoImpl extends  GenericDao<OrganismMaster, Integer> implements IOrganismMasterDao, ICommonConstants, IError{

	public OrganismMasterDaoImpl() {
		super(OrganismMaster.class);
	}

	@Autowired
	private DozerBeanMapper mapper;

	@Override
	public Response addOrganismMaster(OrganismMasterDto organismMasterDto)throws ApplicationException {
		try{
			OrganismMaster organismMaster = new OrganismMaster();
			organismMaster.setCreatedBy(organismMasterDto.getCreatedBy());
			organismMaster.setCreatedDate(organismMasterDto.getCreatedDate());
			organismMaster.setCode(organismMasterDto.getCode());
			organismMaster.setDesc(organismMasterDto.getDesc());
			organismMaster.setStatus(organismMasterDto.getStatus());
			organismMaster.setOrgId(organismMasterDto.getOrgId());
			organismMaster.setUpdatedBy(organismMasterDto.getUpdatedBy());
			organismMaster.setUpdatedDate(organismMasterDto.getUpdatedDate());
			organismMaster = save(organismMaster);
			return new Response<>(SUCCESS, SUCCESS_200,ORGANISUM_ADD_SUCC, null,null);
		}catch(HibernateException he){
			he.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,ORGANISUM_ADD_FAIL, null,null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response checkOrganismMaster(OrganismMasterDto organismMasterDto)throws ApplicationException {
		try
		{
			List<GlobalCommonDto> listOrganismMasterDtosessionFactory = sessionFactory.getCurrentSession().getNamedQuery("GET_ORGANISM_BY_ORGANISM_CODE").setString("code", organismMasterDto.getCode().trim().toLowerCase()).setInteger("orgId", organismMasterDto.getOrgId()).setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).list();
			return new Response(SUCCESS, null, null, listOrganismMasterDtosessionFactory, null);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getOrganismMasterById(Integer orgId, Integer  organismId)
			throws ApplicationException {
		
		try
		{
			GlobalCommonDto containerMaster = (GlobalCommonDto) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ORGANISM_BY_ORGANISM_ID").setInteger("orgId", orgId)
					.setInteger("organismId", organismId)
                    .setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class))					
					.uniqueResult();
			
			return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, containerMaster);

		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500,ORGANISUM_NOT_FOUND, null, null);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response updateOrganismMaster(OrganismMasterDto organismMasterDto)
			throws ApplicationException {
		try
		{
			OrganismMaster reportTypeMaster = mapper.map(organismMasterDto, OrganismMaster.class,"OrganismMasterDtoTOOrganismMaster");
			update(reportTypeMaster);
			return new Response(SUCCESS, null, null, null, null);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500,ORGANISUM_UPDATE_FAIL, null, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response ActivateInactivateOrganismMaster(Integer orgId,
			Integer organisumGroupId, Character organisumGroupStatus)
			throws ApplicationException {

		try
		{
			OrganismMaster organisumGroupMaster = findById(organisumGroupId);
			if (organisumGroupMaster.getOrganismId() != 0)
			{
				organisumGroupMaster.setStatus(organisumGroupStatus);;
				OrganismMaster organisumGroupMst = update(organisumGroupMaster);
				return new Response(SUCCESS, SUCCESS_200,ORGANISUM_ACTIVATE_SUCC, null, organisumGroupMst);
				
			} else
			{
				return new Response(ERROR, ERR_500,ORGANISUM_NOT_FOUND, null, null);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500,ORGANISUM_NOT_FOUND, null, null);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response listOrganismMaster(Integer orgId, Integer offset,
			Integer recordPerPage) throws ApplicationException {
		try
		{
			List<GlobalCommonDto> lisColonyMaster = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ORGANIsm_MASTER_LIST").setInteger("orgId", orgId)
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
	public Response getToTalOrganismMaster(Integer orgId)
			throws ApplicationException {
		try
		{
			BigInteger getOrganisumGroupMasterCount = (BigInteger) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_TOTAL_ORGANISM_RECORD").setInteger("orgId", orgId).uniqueResult();
			
			
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
	public Response updateCheckOrganismMasterCodeAvaiable(
			OrganismMasterDto organisumGroupMasterDto)
			throws ApplicationException {
		try {
			List<GlobalCommonDto> listCheckPhlebotomyCodeFactory = sessionFactory
					.getCurrentSession()
					.getNamedQuery("UPDATE_GET_ORGANISM_TYPE_BY_ORGANISM_CODE")
					.setString("code",organisumGroupMasterDto.getCode().trim().toLowerCase())
					.setInteger("orgId", organisumGroupMasterDto.getOrgId())
					.setInteger("organismId",	organisumGroupMasterDto.getOrganismId())
					.setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).list();
			return new Response(SUCCESS, null, null,listCheckPhlebotomyCodeFactory, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


}
