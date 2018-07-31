package com.param.lis.global.dao;

import java.math.BigInteger;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.param.entity.lis.global.AgeGroupMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.AgeGroupMasterDto;
import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings("rawtypes")
public class AgeGroupDaoImpl extends  GenericDao<AgeGroupMaster, Integer> implements IAgeGroupMasterDao, ICommonConstants, IError{

	public AgeGroupDaoImpl() {
		super(AgeGroupMaster.class);
	}

	@Autowired
	private DozerBeanMapper mapper;

	@Override
	public Response addAgeGroupMaster(AgeGroupMasterDto ageGroupMasterDto)throws ApplicationException {
		try{
			AgeGroupMaster ageGroupMaster = new AgeGroupMaster();
			ageGroupMaster.setCreatedBy(ageGroupMasterDto.getCreatedBy());
			ageGroupMaster.setCreatedDate(ageGroupMasterDto.getCreatedDate());
			ageGroupMaster.setAgeGroupCode(ageGroupMasterDto.getAgeGroupCode());
			ageGroupMaster.setAgeTypeGrpfromId(ageGroupMasterDto.getAgeTypeGrpfromId());
			ageGroupMaster.setAgeTypeGrptoId(ageGroupMasterDto.getAgeTypeGrptoId());
			ageGroupMaster.setAgeGroupStatus(ageGroupMasterDto.getAgeGroupStatus());
			ageGroupMaster.setAgeGroupFrom(ageGroupMasterDto.getAgeGroupFrom());
			ageGroupMaster.setOrgId(ageGroupMasterDto.getOrgId());
			ageGroupMaster.setAgeGroupTo(ageGroupMasterDto.getAgeGroupTo());
			ageGroupMaster.setUpdatedBy(ageGroupMasterDto.getUpdatedBy());
			ageGroupMaster.setUpdatedDate(ageGroupMasterDto.getUpdatedDate());
			ageGroupMaster.setAgeToday(ageGroupMasterDto.getAgeToday());
			ageGroupMaster.setAgeFromDay(ageGroupMasterDto.getAgeFromDay());
			ageGroupMaster.setAgeTypeGrpName(ageGroupMasterDto.getAgeTypeGrpName());
			ageGroupMaster = save(ageGroupMaster);
			return new Response<>(SUCCESS, SUCCESS_200, AGE_GROUP_ADD_SUCC, null,null);
		}catch(HibernateException he){
			he.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201, AGE_GROUP_ADD_FAIL, null,null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response checkAgeGroupCodeAvaiable(AgeGroupMasterDto ageGroupMasterDto)throws ApplicationException {
		try
		{
			List<AgeGroupMasterDto> listAgeGroupMasteDtosessionFactory = sessionFactory.getCurrentSession().getNamedQuery("GET_AGE_GROUP_BY_CODE").setString("ageGroupCode", ageGroupMasterDto.getAgeGroupCode().trim().toLowerCase()).setInteger("orgId", ageGroupMasterDto.getOrgId()).setResultTransformer(Transformers.aliasToBean(AgeGroupMasterDto.class)).list();
			return new Response(SUCCESS, null, null, listAgeGroupMasteDtosessionFactory, null);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getAgeGroupMasterById(Integer orgId, Integer  ageGroupId)
			throws ApplicationException {
		
		try
		{
			AgeGroupMasterDto containerMaster = (AgeGroupMasterDto) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_AGE_GROUP_BY_ID").setInteger("orgId", orgId)
					.setInteger("ageGroupId", ageGroupId)
                    .setResultTransformer(Transformers.aliasToBean(AgeGroupMasterDto.class))					
					.uniqueResult();
			
			return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, containerMaster);

		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, AGE_GROUP_NOT_FOUND, null, null);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response updateAgeGroupMaster(AgeGroupMasterDto ageGroupMasterDto)
			throws ApplicationException {
		try
		{
			AgeGroupMaster ageGroupMaster = mapper.map(ageGroupMasterDto, AgeGroupMaster.class,"AgeGroupMasterDtoTOAgeGroupMaster");
			update(ageGroupMaster);
			return new Response(SUCCESS, null, null, null, null);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500, AGE_GROUP_UPDATE_FAIL, null, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response ActivateInactivateAgeGroupMaster(Integer orgId,
			Integer ageGroupId, Character ageGroupStatus)
			throws ApplicationException {

		try
		{
			AgeGroupMaster ageGroupMaster = findById(ageGroupId);
			if (ageGroupMaster.getAgeGroupId() != 0)
			{
				ageGroupMaster.setAgeGroupStatus(ageGroupStatus);
				AgeGroupMaster unitMst = update(ageGroupMaster);
				return new Response(SUCCESS, SUCCESS_200, AGE_GROUP_ACTIVATE_SUCC, null, unitMst);
				
			} else
			{
				return new Response(ERROR, ERR_500,AGE_GROUP_NOT_FOUND, null, null);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500,AGE_GROUP_NOT_FOUND, null, null);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response listAgeGroupMaster(Integer orgId, Integer offset,
			Integer recordPerPage) throws ApplicationException {
		try
		{
			List<AgeGroupMasterDto> listAgeGroupMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PAGINATED_AGE_GROUP_MASTER_LIST").setInteger("orgId", orgId)
					.setFirstResult(offset != null ? offset : 0)
					.setMaxResults(recordPerPage != null ? recordPerPage : 10)
					.setResultTransformer(Transformers.aliasToBean(AgeGroupMasterDto.class)).list();
			return new Response(SUCCESS, null, null, listAgeGroupMasterDto, null);
			
			
			
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public Response getToTalAgeGroupMasterRecord(Integer orgId)
			throws ApplicationException {
		try
		{
			BigInteger ageGroupMasterTotalRecordCount = (BigInteger) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_TOTAL_AGE_GROUP_RECORD").setInteger("orgId", orgId).uniqueResult();
			
			
			if (ageGroupMasterTotalRecordCount.compareTo(BigInteger.ZERO) == 1)
			{
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, ageGroupMasterTotalRecordCount);
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
	public Response updateCheckAgeGroupCodeAvaiable(
			AgeGroupMasterDto ageGroupMasterDto) throws ApplicationException {
		try {
			List<AgeGroupMasterDto> listCheckPhlebotomyCodeFactory = sessionFactory
					.getCurrentSession()
					.getNamedQuery("UPDATE_GET_AGE_GROUP_BY_CODE")
					.setString("ageGroupCode",ageGroupMasterDto.getAgeGroupCode().trim().toLowerCase())
					.setInteger("orgId", ageGroupMasterDto.getOrgId())
					.setInteger("ageGroupId",	ageGroupMasterDto.getAgeGroupId())
					.setResultTransformer(Transformers.aliasToBean(AgeGroupMasterDto.class)).list();
			return new Response(SUCCESS, null, null,listCheckPhlebotomyCodeFactory, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


}
