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
import com.param.entity.lis.global.AntibioticGroupMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.AntibioticGroupMasterDto;
import com.param.lis.global.dto.GlobalCommonDto;

@Repository
@SuppressWarnings("rawtypes")
public class AntibioticsGroupMasterDaoImpl extends  GenericDao<AntibioticGroupMaster, Integer> implements IAntibioticsGroupMasterDao, ICommonConstants, IError{

	public AntibioticsGroupMasterDaoImpl() {
		super(AntibioticGroupMaster.class);
	}

	@Autowired
	private DozerBeanMapper mapper;

	@Override
	public Response addAntibioticsGroupMaster(AntibioticGroupMasterDto antibioticGroupMasterDto)throws ApplicationException {
		try{
			AntibioticGroupMaster antibioticGroupMaster = new AntibioticGroupMaster();
			antibioticGroupMaster.setCreatedBy(antibioticGroupMasterDto.getCreatedBy());
			antibioticGroupMaster.setCreatedDate(antibioticGroupMasterDto.getCreatedDate());
			antibioticGroupMaster.setCode(antibioticGroupMasterDto.getCode());
			antibioticGroupMaster.setDesc(antibioticGroupMasterDto.getDesc());
			antibioticGroupMaster.setStatus(antibioticGroupMasterDto.getStatus());
			antibioticGroupMaster.setOrgId(antibioticGroupMasterDto.getOrgId());
			antibioticGroupMaster.setUpdatedBy(antibioticGroupMasterDto.getUpdatedBy());
			antibioticGroupMaster.setUpdatedDate(antibioticGroupMasterDto.getUpdatedDate());
			antibioticGroupMaster = save(antibioticGroupMaster);
			return new Response<>(SUCCESS, SUCCESS_200,ANTIBIOTICS_GROUP_ADD_SUCC, null,null);
		}catch(HibernateException he){
			he.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201, ANTIBIOTICS_GROUPADD_FAIL, null,null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response checkAntibioticsGroupMaster(AntibioticGroupMasterDto antibioticGroupMasterDto)throws ApplicationException {
		try
		{
			List<GlobalCommonDto> listAntibioticsGroupMaster = sessionFactory.getCurrentSession().getNamedQuery("GET_REPORT_TYPE_BY_ANTIBIOTIC_GROUP_CODE").setString("code", antibioticGroupMasterDto.getCode().trim().toLowerCase()).setInteger("orgId", antibioticGroupMasterDto.getOrgId()).setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).list();
			return new Response(SUCCESS, null, null, listAntibioticsGroupMaster, null);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getAntibioticsGroupMasterById(Integer orgId, Integer  antibioticGroupId)
			throws ApplicationException {
		
		try
		{
			GlobalCommonDto containerMaster = (GlobalCommonDto) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_REPORT_TYPE_BY_ANTIBIOTIC_GROUP_ID").setInteger("orgId", orgId)
					.setInteger("antibioticGroupId", antibioticGroupId)
                    .setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class))					
					.uniqueResult();
			
			return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, containerMaster);

		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ANTIBIOTICS_GROUPNOT_FOUND, null, null);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response updateAntibioticsGroupMaster(AntibioticGroupMasterDto AntibioticGroupMasterDto)
			throws ApplicationException {
		try
		{
			AntibioticGroupMaster reportTypeMaster = mapper.map(AntibioticGroupMasterDto, AntibioticGroupMaster.class,"AntibioticGroupMasterDtoTOAntibioticGroupMaster");
			update(reportTypeMaster);
			return new Response(SUCCESS, null, null, null, null);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500, ANTIBIOTICS_GROUPUPDATE_FAIL, null, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response ActivateInactivateAntibioticsGroupMaster(Integer orgId,
			Integer unitId, Character antibioticStatus)
			throws ApplicationException {

		try
		{
			AntibioticGroupMaster antibioticGroupMaster = findById(unitId);
			if (antibioticGroupMaster.getAntibioticGroupId() != 0)
			{
				antibioticGroupMaster.setStatus(antibioticStatus);
				AntibioticGroupMaster antibioticMst = update(antibioticGroupMaster);
				return new Response(SUCCESS, SUCCESS_200, ANTIBIOTICS_GROUPACTIVATE_SUCC, null, antibioticMst);
				
			} else
			{
				return new Response(ERROR, ERR_500, ANTIBIOTICS_GROUPNOT_FOUND, null, null);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ANTIBIOTICS_GROUPNOT_FOUND, null, null);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response listAntibioticsGroupMaster(Integer orgId, Integer offset,
			Integer recordPerPage) throws ApplicationException {
		try
		{
			List<GlobalCommonDto> listUnitMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PAGINATED_ANTIBIOTIC_GROUP_MASTER_LIST").setInteger("orgId", orgId)
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
	public Response getToTalAntibioticsGroupMaster(Integer orgId)
			throws ApplicationException {
		try
		{
			BigInteger getAntibioticsMasterCount = (BigInteger) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_TOTAL_ANTIBIOTIC_GROUP_RECORD").setInteger("orgId", orgId).uniqueResult();
			
			
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
	public Response updateCheckAntibioticsGroupCodeAvaiable(
			AntibioticGroupMasterDto antibioticGroupMasterDto)
			throws ApplicationException {
		try {
			List<GlobalCommonDto> listCheckPhlebotomyCodeFactory = sessionFactory
					.getCurrentSession()
					.getNamedQuery("UPDATE_GET_REPORT_TYPE_BY_ANTIBIOTIC_GROUP_CODE")
					.setString("code",antibioticGroupMasterDto.getCode().trim().toLowerCase())
					.setInteger("orgId", antibioticGroupMasterDto.getOrgId())
					.setInteger("antibioticGroupId",	antibioticGroupMasterDto.getAntibioticGroupId())
					.setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).list();
			return new Response(SUCCESS, null, null,listCheckPhlebotomyCodeFactory, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


}
