package com.param.lis.global.dao;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.param.entity.lis.global.OrganismGroupOrganismMapperMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.OrganismGroupOrganismMapperMasterDto;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class OrganismGroupOrganismMapperMasterDaoImpl extends GenericDao<OrganismGroupOrganismMapperMaster, Integer>
		implements IOrganismGroupOrganismMapperMasterDao, ICommonConstants, IError {

	public OrganismGroupOrganismMapperMasterDaoImpl() {
		super(OrganismGroupOrganismMapperMaster.class);
	}

	@Autowired
	private DozerBeanMapper mapper;

	@Override
	public Response addOrganismGroupOrganismMapperMaster(OrganismGroupOrganismMapperMasterDto organismGroupOrganismMapperMaster)
			throws ApplicationException {
		try {
			 OrganismGroupOrganismMapperMaster organismGroupOrganismMapperMst = new  OrganismGroupOrganismMapperMaster();
			 organismGroupOrganismMapperMst.setCreatedBy(organismGroupOrganismMapperMaster.getCreatedBy());
			 organismGroupOrganismMapperMst.setCreatedDate(organismGroupOrganismMapperMaster.getCreatedDate());
			 organismGroupOrganismMapperMst.setStatus(organismGroupOrganismMapperMaster.getStatus());
			 organismGroupOrganismMapperMst.setOrgId(organismGroupOrganismMapperMaster.getOrgId());
			 organismGroupOrganismMapperMst.setUpdatedBy(organismGroupOrganismMapperMaster.getUpdatedBy());
			 organismGroupOrganismMapperMst.setUpdatedDate(organismGroupOrganismMapperMaster.getUpdatedDate());
			 organismGroupOrganismMapperMst.setOrganismGroupId(organismGroupOrganismMapperMaster.getOrganismGroupId());
			 organismGroupOrganismMapperMst.setOrganismId(organismGroupOrganismMapperMaster.getOrganismId());
			 organismGroupOrganismMapperMst.setIsDeleted(organismGroupOrganismMapperMaster.getIsDeleted());
			 organismGroupOrganismMapperMst = save(organismGroupOrganismMapperMst);

			return new Response<>(SUCCESS, SUCCESS_200, ORGANIGROUP_ORGANI_MAPPER_ADD_SUCC, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201, ORGANIGROUP_ORGANI_MAPPER_ADD_FAIL, null, null);
	}

	@Override
	public Response listOrganismGroupOrganismMapperMaster(Integer orgId, Integer offset, Integer recordPerPage)
			throws ApplicationException {
		try {
			List<OrganismGroupOrganismMapperMasterDto> listOrganismGroupOrganismMapperMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PAGINATED_ORGANIGROUP_ORGANI_CLASS_MAPPER_MASTER_LIST").setInteger("orgId", orgId)
					.setFirstResult(offset != null ? offset : 0)
					.setMaxResults(recordPerPage != null ? recordPerPage : 10)
					.setResultTransformer(Transformers.aliasToBean(OrganismGroupOrganismMapperMasterDto.class)).list();
			return new Response(SUCCESS, null, null, listOrganismGroupOrganismMapperMasterDto, null);

		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response getToTalOrganismGroupOrganismMapperMaster(Integer orgId) throws ApplicationException {
		try {
			BigInteger organismGroupOrganismMasterCount = (BigInteger) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_TOTAL_ORGANIGROUP_ORGANI_CLASS_MAPPER_RECORD").setInteger("orgId", orgId)
					.uniqueResult();

			if (organismGroupOrganismMasterCount.compareTo(BigInteger.ZERO) == 1) {
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, organismGroupOrganismMasterCount);
			} else {
				return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, BigInteger.ZERO);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response activateInactivateOrganismGroupOrganismMapperMaster(Integer orgId, Integer organismGroupId,
			Character status) throws ApplicationException {

		try {
			if (organismGroupId != 0) {

				Integer result = sessionFactory.getCurrentSession()
						.getNamedQuery("ACTIVE_INACTIVE_ORGANIGROUP_ORGANI_CLASS_MAPPER").setInteger("orgId", orgId)
						.setCharacter("status", status).setInteger("organismGroupId", organismGroupId)
						.executeUpdate();
				if (result > 0) {
					if (status.equals(ACTIVE)) {
						return new Response(SUCCESS, SUCCESS_200, ORGANIGROUP_ORGANI_MAPPER_ACTIVATE_SUCC, null, null);
					} else if (status.equals(INACTIVE)) {
						return new Response(SUCCESS, SUCCESS_200, ORGANIGROUP_ORGANI_MAPPER_INACTIVATE_SUCC, null, null);
					}
				} else {
					return new Response(ERROR, ERR_500, ORGANIGROUP_ORGANI_MAPPER_CORDS_NOT_FOUND, null, null);
				}

			} else {
				return new Response(ERROR, ERR_500, ORGANIGROUP_ORGANI_MAPPER_CORDS_NOT_FOUND, null, null);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ORGANIGROUP_ORGANI_MAPPER_CORDS_NOT_FOUND, null, null);
		}
		return new Response(ERROR, ERR_500, ORGANIGROUP_ORGANI_MAPPER_CORDS_NOT_FOUND, null, null);
	}

	@Override
	public Response updateOrganismGroupOrganismMapperMaster(
			List<OrganismGroupOrganismMapperMasterDto> listmediaColonyMapperMasterDto)
			throws ApplicationException {
		try
		{
		
			Integer result = sessionFactory.getCurrentSession()
					        .getNamedQuery("UPDATE_ORGANIGROUP_ORGANI_CLASS_MAPPER")
					        .setCharacter("isDeleted", 'Y')
					        .setInteger("orgId",listmediaColonyMapperMasterDto.get(0).getOrgId())
					        .setInteger("organismGroupId", listmediaColonyMapperMasterDto.get(0).getOrganismGroupId()).executeUpdate();
			System.out.println("===========>"+result);
			if(result>0)
			{
			for (Iterator iterator = listmediaColonyMapperMasterDto.iterator(); iterator.hasNext();) {
				 OrganismGroupOrganismMapperMasterDto listOrganismGroupOrganismMapperMaster = (OrganismGroupOrganismMapperMasterDto) iterator
						.next();
				 OrganismGroupOrganismMapperMaster mediaColonyMapperMaster = mapper.map(listOrganismGroupOrganismMapperMaster,  OrganismGroupOrganismMapperMaster.class,"OrganismGroupOrganismMapperMasterDtoTOOrganismGroupOrganismMapperMaster");
				 mediaColonyMapperMaster.setIsDeleted('N');
				 mediaColonyMapperMaster.setCreatedBy(mediaColonyMapperMaster.getUpdatedBy());
				 mediaColonyMapperMaster.setCreatedDate(mediaColonyMapperMaster.getUpdatedDate());
					save(mediaColonyMapperMaster);
				}
			  return new Response(SUCCESS, SUCCESS_200, ANTIBIOTIC_UPDATE_SUCC, null, null);
			}
			else {
				return new Response(ERROR, ERR_500, ORGANIGROUP_ORGANI_MAPPER_UPDATE_FAIL, null, null);
		}
		}catch(

	Exception e)
	{
		e.printStackTrace();
		return new Response(ERROR, ERR_500, ORGANIGROUP_ORGANI_MAPPER_UPDATE_FAIL, null, null);
	}

	}

	@Override
	public Response checkOrganismGroupMapperAvaiable(OrganismGroupOrganismMapperMasterDto mediaColonyMapperMasterDto)
			throws ApplicationException {
		try {
			List<OrganismGroupOrganismMapperMasterDto> listAgeGroupMasteDtosessionFactory = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ORGANIGROUP_ORGANI_CLASS_MAPPER_TYPE_BY_ORGANIGROUP_ORGANI_CODE")
					.setInteger("orgId", mediaColonyMapperMasterDto.getOrgId())
				     .setInteger("organismGroupId", mediaColonyMapperMasterDto.getOrganismGroupId())
					.setResultTransformer(Transformers.aliasToBean(OrganismGroupOrganismMapperMasterDto.class)).list();
			return new Response(SUCCESS, null, null, listAgeGroupMasteDtosessionFactory, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response getOrganismGroupOrganismMapperMasterById(Integer orgId, Integer organismGroupId)
			throws ApplicationException {
		try {
			List< OrganismGroupOrganismMapperMasterDto> listContainerMaster = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ORGANIGROUP_ORGANI_CLASS_MAPPER_BY_ORGANIGROUP_ORGANI_ID").setInteger("orgId", orgId)
					.setInteger("organismGroupId", organismGroupId)
					.setResultTransformer(Transformers.aliasToBean(OrganismGroupOrganismMapperMasterDto.class)).list();

			return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, listContainerMaster, null);

		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ORGANIGROUP_ORGANI_MAPPER_CORDS_NOT_FOUND, null, null);
		}
	}

	@Override
	public Response getOrganismGroupMasterClassById(Integer organismGroupId) throws ApplicationException {
		try {
			List<OrganismGroupOrganismMapperMasterDto> listmediaColonyMapperMaster = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ORGANIGROUP_ORGANI_BY_ORGANIGROUP_ORGANI_CLASS_ID")
					.setInteger("organismGroupId", organismGroupId)
					.setResultTransformer(Transformers.aliasToBean(OrganismGroupOrganismMapperMasterDto.class)).list();

			return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE,listmediaColonyMapperMaster, null);

		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ORGANIGROUP_ORGANI_MAPPER_CORDS_NOT_FOUND, null, null);
		}
	}

}
