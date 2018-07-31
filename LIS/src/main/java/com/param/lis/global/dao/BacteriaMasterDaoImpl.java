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
import com.param.entity.lis.global.BacteriaMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.BacteriaMasterDto;
import com.param.lis.global.dto.GlobalCommonDto;

@Repository
@SuppressWarnings("rawtypes")
public class BacteriaMasterDaoImpl extends  GenericDao<BacteriaMaster, Integer> implements IBacteriaMasterDao, ICommonConstants, IError{

	public BacteriaMasterDaoImpl() {
		super(BacteriaMaster.class);
	}

	@Autowired
	private DozerBeanMapper mapper;

	@Override
	public Response addBacteriaMaster(BacteriaMasterDto bacteriaMasterDto)throws ApplicationException {
		try{
			BacteriaMaster bacteriaMaster = new BacteriaMaster();
			bacteriaMaster.setCreatedBy(bacteriaMasterDto.getCreatedBy());
			bacteriaMaster.setCreatedDate(bacteriaMasterDto.getCreatedDate());
			bacteriaMaster.setCode(bacteriaMasterDto.getCode());
			bacteriaMaster.setDesc(bacteriaMasterDto.getDesc());
			bacteriaMaster.setStatus(bacteriaMasterDto.getStatus());
			bacteriaMaster.setOrgId(bacteriaMasterDto.getOrgId());
			bacteriaMaster.setUpdatedBy(bacteriaMasterDto.getUpdatedBy());
			bacteriaMaster.setUpdatedDate(bacteriaMasterDto.getUpdatedDate());
			bacteriaMaster = save(bacteriaMaster);
			return new Response<>(SUCCESS, SUCCESS_200,BACTERIA_ADD_SUCC, null,null);
		}catch(HibernateException he){
			he.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,REPORT_TYPRADD_FAIL, null,null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response checkBacteriaMaster(BacteriaMasterDto bacteriaMasterDto)throws ApplicationException {
		try
		{
			List<GlobalCommonDto> listUnitMasteDtosessionFactory = sessionFactory.getCurrentSession().getNamedQuery("GET_REPORT_TYPE_BY_BACTERIA_CODE").setString("code", bacteriaMasterDto.getCode().trim().toLowerCase()).setInteger("orgId", bacteriaMasterDto.getOrgId()).setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).list();
			return new Response(SUCCESS, null, null, listUnitMasteDtosessionFactory, null);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getBacteriaMasterById(Integer orgId, Integer  bacteriaId)
			throws ApplicationException {
		
		try
		{
			GlobalCommonDto containerMaster = (GlobalCommonDto) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_REPORT_TYPE_BY_BACTERIA_ID").setInteger("orgId", orgId)
					.setInteger("bacteriaId", bacteriaId)
                    .setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class))					
					.uniqueResult();
			
			return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, containerMaster);

		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500,REPORT_TYPRNOT_FOUND, null, null);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response updateBacteriaMaster(BacteriaMasterDto bacteriaMasterDto)
			throws ApplicationException {
		try
		{
			BacteriaMaster reportTypeMaster = mapper.map(bacteriaMasterDto, BacteriaMaster.class,"BacteriaMasterDtoTOBacteriaMaster");
			update(reportTypeMaster);
			return new Response(SUCCESS, null, null, null, null);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500,REPORT_TYPRUPDATE_FAIL, null, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response ActivateInactivateBacteriaMaster(Integer orgId,
			Integer unitId, Character unitStatus)
			throws ApplicationException {

		try
		{
			BacteriaMaster bacteriaMaster = findById(unitId);
			if (bacteriaMaster.getBacteriaId() != 0)
			{
				bacteriaMaster.setStatus(unitStatus);;
				BacteriaMaster bacteriaMst = update(bacteriaMaster);
				return new Response(SUCCESS, SUCCESS_200,REPORT_TYPRACTIVATE_SUCC, null, bacteriaMst);
				
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
	public Response listBacteriaMaster(Integer orgId, Integer offset,
			Integer recordPerPage) throws ApplicationException {
		try
		{
			List<GlobalCommonDto> listUnitMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PAGINATED_BACTERIA_MASTER_LIST").setInteger("orgId", orgId)
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
	public Response getToTalBacteriaMaster(Integer orgId)
			throws ApplicationException {
		try
		{
			BigInteger bacteriaMasterCount = (BigInteger) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_TOTAL_BACTERIA_RECORD").setInteger("orgId", orgId).uniqueResult();
			
			
			if (bacteriaMasterCount.compareTo(BigInteger.ZERO) == 1)
			{
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, bacteriaMasterCount);
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
	public Response updateCheckBacteriaCodeAvaiable(
			BacteriaMasterDto bacteriaMasterDto) throws ApplicationException {
		try {
			List<GlobalCommonDto> listCheckPhlebotomyCodeFactory = sessionFactory
					.getCurrentSession()
					.getNamedQuery("UPDATE_GET_REPORT_TYPE_BY_BACTERIA_CODE")
					.setString("code",bacteriaMasterDto.getCode().trim().toLowerCase())
					.setInteger("orgId", bacteriaMasterDto.getOrgId())
					.setInteger("bacteriaId",	bacteriaMasterDto.getBacteriaId())
					.setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).list();
			return new Response(SUCCESS, null, null,listCheckPhlebotomyCodeFactory, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


}
