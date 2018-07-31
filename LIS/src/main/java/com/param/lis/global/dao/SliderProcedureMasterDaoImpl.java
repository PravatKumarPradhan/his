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
import com.param.entity.lis.global.SlideProcedureMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.GlobalCommonDto;
import com.param.lis.global.dto.SlideProcedureMasterDto;

@Repository
@SuppressWarnings("rawtypes")
public class SliderProcedureMasterDaoImpl extends  GenericDao<SlideProcedureMaster, Integer> implements ISliderProcedureMasterDao, ICommonConstants, IError{

	public SliderProcedureMasterDaoImpl() {
		super(SlideProcedureMaster.class);
	}

	@Autowired
	private DozerBeanMapper mapper;

	@Override
	public Response addSlideProcedureMaster(SlideProcedureMasterDto slideProcedureMasterDto)throws ApplicationException {
		try{
			SlideProcedureMaster slideProcedureMaster = new SlideProcedureMaster();
			slideProcedureMaster.setCreatedBy(slideProcedureMasterDto.getCreatedBy());
			slideProcedureMaster.setCreatedDate(slideProcedureMasterDto.getCreatedDate());
			slideProcedureMaster.setCode(slideProcedureMasterDto.getCode());
			slideProcedureMaster.setDesc(slideProcedureMasterDto.getDesc());
			slideProcedureMaster.setStatus(slideProcedureMasterDto.getStatus());
			slideProcedureMaster.setOrgId(slideProcedureMasterDto.getOrgId());
			slideProcedureMaster.setUpdatedBy(slideProcedureMasterDto.getUpdatedBy());
			slideProcedureMaster.setUpdatedDate(slideProcedureMasterDto.getUpdatedDate());
			slideProcedureMaster = save(slideProcedureMaster);
			return new Response<>(SUCCESS, SUCCESS_200,SLIDEPROCEDURE_ADD_SUCC, null,null);
		}catch(HibernateException he){
			he.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,SLIDEPROCEDURE_ADD_FAIL, null,null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response checkSlideProcedureMaster(SlideProcedureMasterDto slideProcedureMasterDto)throws ApplicationException {
		try
		{
			List<GlobalCommonDto> listUnitMasteDtosessionFactory = sessionFactory.getCurrentSession().getNamedQuery("GET_REPORT_TYPE_BY_SLIDERPROCEDURE_CODE").setString("code", slideProcedureMasterDto.getCode().trim().toLowerCase()).setInteger("orgId", slideProcedureMasterDto.getOrgId()).setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).list();
			return new Response(SUCCESS, null, null, listUnitMasteDtosessionFactory, null);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getSlideProcedureMasterById(Integer orgId, Integer  slideProcedureId)
			throws ApplicationException {
		
		try
		{
			GlobalCommonDto containerMaster = (GlobalCommonDto) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_REPORT_TYPE_BY_SLIDERPROCEDURE_ID").setInteger("orgId", orgId)
					.setInteger("slideProcedureId", slideProcedureId)
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
	public Response updateSlideProcedureMaster(SlideProcedureMasterDto slideProcedureMasterDto)
			throws ApplicationException {
		try
		{
			SlideProcedureMaster reportTypeMaster = mapper.map(slideProcedureMasterDto, SlideProcedureMaster.class,"SlideProcedureMasterDtoTOSlideProcedureMaster");
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
	public Response ActivateInactivateSlideProcedureMaster(Integer orgId,
			Integer slideProcedureId, Character slideProcedureStatus)
			throws ApplicationException {

		try
		{
			SlideProcedureMaster slideProcedureMaster = findById(slideProcedureId);
			if (slideProcedureMaster.getSlideProcedureId() != 0)
			{
				slideProcedureMaster.setStatus(slideProcedureStatus);;
				SlideProcedureMaster bacteriaMst = update(slideProcedureMaster);
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
	public Response listSlideProcedureMaster(Integer orgId, Integer offset,
			Integer recordPerPage) throws ApplicationException {
		try
		{
			List<GlobalCommonDto> listSlideProcedureMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PAGINATED_SLIDERPROCEDURE_MASTER_LIST").setInteger("orgId", orgId)
					.setFirstResult(offset != null ? offset : 0)
					.setMaxResults(recordPerPage != null ? recordPerPage : 10)
					.setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).list();
			return new Response(SUCCESS, null, null, listSlideProcedureMasterDto, null);
		
			
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public Response getToTalSlideProcedureMaster(Integer orgId)
			throws ApplicationException {
		try
		{
			BigInteger bacteriaMasterCount = (BigInteger) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_TOTAL_SLIDERPROCEDURE_RECORD").setInteger("orgId", orgId).uniqueResult();
			
			
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
	public Response updateCheckSlideProcedureMaster(
			SlideProcedureMasterDto slideProcedureMasterDto)
			throws ApplicationException {
		try
		{
			List<GlobalCommonDto> listUnitMasteDtosessionFactory = sessionFactory.getCurrentSession().getNamedQuery("UPDATE_GET_REPORT_TYPE_BY_SLIDERPROCEDURE_CODE").setString("code", slideProcedureMasterDto.getCode().trim().toLowerCase()).setInteger("orgId", slideProcedureMasterDto.getOrgId()).setInteger("slideProcedureId", slideProcedureMasterDto.getSlideProcedureId()).setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).list();
			return new Response(SUCCESS, null, null, listUnitMasteDtosessionFactory, null);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


}
