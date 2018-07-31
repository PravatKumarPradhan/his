package com.param.lis.unit.dao;

import java.math.BigInteger;
import java.util.List;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.param.entity.lis.unit.PhlebotomyMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.GlobalCommonDto;
import com.param.lis.unit.dto.PhlebotomyMasterDto;

@Repository
@SuppressWarnings({"rawtypes","unchecked"})
public class PhlebotomyMasterDaoImpl extends GenericDao<PhlebotomyMaster, Integer> implements IPhlebotomyMasterDao, ICommonConstants, IError{

	@Autowired
	private DozerBeanMapper mapper;

	public PhlebotomyMasterDaoImpl() {
		super(PhlebotomyMaster.class);
	}

	@Override
	public Response addPhlebotomyMaster(PhlebotomyMasterDto phlebotomyMasterDto)throws ApplicationException {
		try{
			PhlebotomyMaster phlebotomyMaster = new PhlebotomyMaster();
			phlebotomyMaster.setCreatedBy(phlebotomyMasterDto.getCreatedBy());
			phlebotomyMaster.setCreatedDate(phlebotomyMasterDto.getCreatedDate());
			phlebotomyMaster.setCode(phlebotomyMasterDto.getCode());
			phlebotomyMaster.setDesc(phlebotomyMasterDto.getDesc());
			phlebotomyMaster.setStatus(phlebotomyMasterDto.getStatus());
			phlebotomyMaster.setOrgId(phlebotomyMasterDto.getOrgId());
			phlebotomyMaster.setUnitId(phlebotomyMasterDto.getUnitId());
			phlebotomyMaster.setDeptId(phlebotomyMasterDto.getDeptId());
			phlebotomyMaster.setUpdatedBy(phlebotomyMasterDto.getUpdatedBy());
			phlebotomyMaster.setUpdatedBy(phlebotomyMasterDto.getUpdatedBy());
			phlebotomyMaster.setUpdatedDate(phlebotomyMasterDto.getUpdatedDate());
			phlebotomyMaster = save(phlebotomyMaster);
			return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null,null);
		}catch(HibernateException he){
			he.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, null, null, null,null);
	}

	@Override
	public Response checkPhlebotomyCodeAvaiable(PhlebotomyMasterDto phlebotomyMasterDto)throws ApplicationException {
		try
		{
			List<GlobalCommonDto> listPhlebotomyMasterDtosessionFactory = sessionFactory.getCurrentSession().getNamedQuery("GET_PHLEBOTOMY_BY_CODE").setString("code", phlebotomyMasterDto.getCode().trim().toLowerCase()).setInteger("orgId", phlebotomyMasterDto.getOrgId()).setInteger("unitId", phlebotomyMasterDto.getUnitId()).setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).list();
			return new Response(SUCCESS, null, null, listPhlebotomyMasterDtosessionFactory, null);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		
	}

	@Override
	public Response getPhlebotomyMasteById(Integer orgId,Integer unitId,Integer  phlebotomyId)
			throws ApplicationException {
		
		try
		{
			GlobalCommonDto containerMaster = (GlobalCommonDto) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PHLEBOTOMY_BY_PHLEBOTOMY_ID").setInteger("orgId", orgId)
					.setInteger("phlebotomyId", phlebotomyId)
					.setInteger("unitId", unitId)
                    .setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class))					
					.uniqueResult();
			
			return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, containerMaster);

		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, PHLEBOTOMY_NOT_FOUND, null, null);
		}
	}

	@Override
	public Response updatePhlebotomyMaster(PhlebotomyMasterDto phlebotomyMasterDto)
			throws ApplicationException {
		try
		{
			PhlebotomyMaster phlebotomyMaster = mapper.map(phlebotomyMasterDto, PhlebotomyMaster.class,
					"PhlebotomyMasterDtoTOPhlebotomyMaster");
			PhlebotomyMaster prerequisitesMst = save(phlebotomyMaster);
			if (prerequisitesMst != null)
			{
				return new Response(SUCCESS, SUCCESS_200, PHLEBOTOMY_UPDATE_SUCC, null, null);
			} else
			{
				return new Response(SUCCESS, SUCCESS_200, PHLEBOTOMY_UPDATE_FAIL, null, null);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, PHLEBOTOMY_UPDATE_FAIL, null, null);
		}
	}

	@Override
	public Response ActivateInactivatePhlebotomyMaster(Integer orgId,
			Integer mediaId, Character mediaStatus)
			throws ApplicationException {

		try
		{
			PhlebotomyMaster phlebotomyMaster = findById(mediaId);
			if (phlebotomyMaster.getPhlebotomyId() != 0)
			{
				phlebotomyMaster.setStatus(mediaStatus);
				PhlebotomyMaster phlebotomyMst = update(phlebotomyMaster);
				if (phlebotomyMst.getStatus() == ACTIVE)
				{
					return new Response(SUCCESS, SUCCESS_200, PHLEBOTOMY_ACTIVATE_SUCC, null, null);
				} else if (phlebotomyMst.getStatus()== INACTIVE)
				{
					return new Response(SUCCESS, SUCCESS_200, PHLEBOTOMY_INACTIVATE_SUCC, null, null);
				} else
				{
					if (mediaStatus == ACTIVE)
					{
						return new Response(SUCCESS, SUCCESS_200, PHLEBOTOMY_ACTIVATE_FAIL, null, null);
					} else
					{
						return new Response(SUCCESS, SUCCESS_200, PHLEBOTOMY_INACTIVATE_FAIL, null, null);
					}
				}
			} else
			{
				return new Response(SUCCESS, SUCCESS_200, PHLEBOTOMY_NOT_FOUND, null, null);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500,PHLEBOTOMY_NOT_FOUND, null, null);
		}
	}

	@Override
	public Response listPhlebotomyMaster(Integer orgId, Integer offset,
			Integer recordPerPage) throws ApplicationException {
		try
		{
			List<GlobalCommonDto> listPhlebotomyMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PAGINATED_PHLEBOTOMY_MASTER_LIST").setInteger("orgId", orgId)
					.setFirstResult(offset != null ? offset : 0)
					.setMaxResults(recordPerPage != null ? recordPerPage : 10)
					.setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).list();
			
			if(listPhlebotomyMasterDto.isEmpty())
			{
				return new Response(ERROR, ERR_500,PHLEBOTOMY_RECORDS_NOT_FOUND, null, null);
			}
			else{
				return new Response(SUCCESS, SUCCESS_200, null, listPhlebotomyMasterDto, null);
			}
			
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public Response getToTalPhlebotomyMasterRecord(Integer orgId)
			throws ApplicationException {
		try
		{
			BigInteger phlebotomyMasterTotalRecordCount = (BigInteger) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_TOTAL_PHLEBOTOMY_MASTER_RECORD").setInteger("orgId", orgId).uniqueResult();
			if (phlebotomyMasterTotalRecordCount.compareTo(BigInteger.ZERO) == 1)
			{
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, phlebotomyMasterTotalRecordCount);
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

	@Override
	public Response updateCheckPhlebotomyCodeAvaiable(
			PhlebotomyMasterDto phlebotomyMasterDto)
			throws ApplicationException {
		try {
			List<GlobalCommonDto> listCheckPhlebotomyCodeFactory = sessionFactory
					.getCurrentSession()
					.getNamedQuery("UPDATE_GET_PHLEBOTOMY_BY_CODE")
					.setString("code",phlebotomyMasterDto.getCode().trim().toLowerCase())
					.setInteger("orgId", phlebotomyMasterDto.getOrgId())
					.setInteger("unitId", phlebotomyMasterDto.getUnitId())
					.setInteger("phlebotomyId",	phlebotomyMasterDto.getPhlebotomyId())
					.setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).list();
			return new Response(SUCCESS, null, null,listCheckPhlebotomyCodeFactory, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

}
