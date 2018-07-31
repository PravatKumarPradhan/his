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
import com.param.entity.lis.global.SpecimanMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.GlobalCommonDto;
import com.param.lis.global.dto.SpecimanMasterDto;

@Repository
@SuppressWarnings("rawtypes")
public class SpecimanMasterDaoImpl extends  GenericDao<SpecimanMaster, Integer> implements ISpecimanMasterDao, ICommonConstants, IError{

	public SpecimanMasterDaoImpl() {
		super(SpecimanMaster.class);
	}

	@Autowired
	private DozerBeanMapper mapper;

	@Override
	public Response addSpecimanMaster(SpecimanMasterDto specimanMasterDto)throws ApplicationException {
		try{
			SpecimanMaster specimanMaster = new SpecimanMaster();
			specimanMaster.setCreatedBy(specimanMasterDto.getCreatedBy());
			specimanMaster.setCreatedDate(specimanMasterDto.getCreatedDate());
			specimanMaster.setCode(specimanMasterDto.getCode());
			specimanMaster.setDesc(specimanMasterDto.getDesc());
			specimanMaster.setStatus(specimanMasterDto.getStatus());
			specimanMaster.setOrgId(specimanMasterDto.getOrgId());
			specimanMaster.setUpdatedBy(specimanMasterDto.getUpdatedBy());
			specimanMaster.setUpdatedDate(specimanMasterDto.getUpdatedDate());
			specimanMaster = save(specimanMaster);
			return new Response<>(SUCCESS, SUCCESS_200,SPECIMAN_ADD_SUCC, null,null);
		}catch(HibernateException he){
			he.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,SPECIMAN_ADD_FAIL, null,null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response checkSpecimanMaster(SpecimanMasterDto specimanMasterDto)throws ApplicationException {
		try
		{
			List<GlobalCommonDto> listUnitMasteDtosessionFactory = sessionFactory.getCurrentSession().getNamedQuery("GET_SPECIMAN_TYPE_BY_SPECIMAN_CODE").setString("code", specimanMasterDto.getCode().trim().toLowerCase()).setInteger("orgId", specimanMasterDto.getOrgId()).setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).list();
			return new Response(SUCCESS, null, null, listUnitMasteDtosessionFactory, null);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getSpecimanMasteById(Integer orgId, Integer  specimanId)
			throws ApplicationException {
		
		try
		{
			SpecimanMasterDto containerMaster = (SpecimanMasterDto) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_SPECIMAN_TYPE_BY_SPECIMAN_ID").setInteger("orgId", orgId)
					.setInteger("specimanId", specimanId)
                    .setResultTransformer(Transformers.aliasToBean(SpecimanMasterDto.class))					
					.uniqueResult();
			
			return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, containerMaster);

		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500,SPECIMAN_NOT_FOUND, null, null);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response updateSpecimanMaster(SpecimanMasterDto specimanMasterDto)
			throws ApplicationException {
		try
		{
			SpecimanMaster reportTypeMaster = mapper.map(specimanMasterDto, SpecimanMaster.class,"SpecimanMasterDtoTOSpecimanMaster");
			update(reportTypeMaster);
			return new Response(SUCCESS, null, null, null, null);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500,SPECIMAN_UPDATE_FAIL, null, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response ActivateInactivateSpecimanMaster(Integer orgId,
			Integer unitId, Character unitStatus)
			throws ApplicationException {

		try
		{
			SpecimanMaster specimanMaster = findById(unitId);
			if (specimanMaster.getSpecimanId() != 0)
			{
				specimanMaster.setStatus(unitStatus);;
				SpecimanMaster antibioticMst = update(specimanMaster);
				return new Response(SUCCESS, SUCCESS_200,SPECIMAN_ACTIVATE_SUCC, null, antibioticMst);
				
			} else
			{
				return new Response(ERROR, ERR_500,SPECIMAN_NOT_FOUND, null, null);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500,SPECIMAN_NOT_FOUND, null, null);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response listSpecimanMaster(Integer orgId, Integer offset,
			Integer recordPerPage) throws ApplicationException {
		try
		{
			List<SpecimanMasterDto> listUnitMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PAGINATED_SPECIMAN_MASTER_LIST").setInteger("orgId", orgId)
					.setFirstResult(offset != null ? offset : 0)
					.setMaxResults(recordPerPage != null ? recordPerPage : 10)
					.setResultTransformer(Transformers.aliasToBean(SpecimanMasterDto.class)).list();
			return new Response(SUCCESS, null, null, listUnitMasterDto, null);
		
			
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public Response getToTalSpecimanMaster(Integer orgId)
			throws ApplicationException {
		try
		{
			BigInteger getToTalSpecimanMasterCount = (BigInteger) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_TOTAL_SPECIMAN_RECORD").setInteger("orgId", orgId).uniqueResult();
			
			
			if (getToTalSpecimanMasterCount.compareTo(BigInteger.ZERO) == 1)
			{
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, getToTalSpecimanMasterCount);
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
	public Response updateCheckReportTypeCodeAvaiable(
			SpecimanMasterDto specimanMasterDto) throws ApplicationException {
		try {
			List<SpecimanMasterDto> listCheckPhlebotomyCodeFactory = sessionFactory
					.getCurrentSession()
					.getNamedQuery("UPDATE_GET_SPECIMAN_TYPE_BY_SPECIMAN_CODE")
					.setString("code",specimanMasterDto.getCode().trim().toLowerCase())
					.setInteger("orgId", specimanMasterDto.getOrgId())
					.setInteger("specimanId",	specimanMasterDto.getSpecimanId())
					.setResultTransformer(Transformers.aliasToBean(SpecimanMasterDto.class)).list();
			return new Response(SUCCESS, null, null,listCheckPhlebotomyCodeFactory, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public Response specimanGrossRequriedInSpecimanMaster(Integer orgId,
			Integer specimanId, Character specimanGross)
			throws ApplicationException {
		try
		{
			SpecimanMaster specimanMaster = findById(specimanId);
			if (specimanMaster.getSpecimanId() != 0)
			{
				specimanMaster.setSpecimanGross(specimanGross);
				SpecimanMaster specimanMst = update(specimanMaster);
				return new Response(SUCCESS, SUCCESS_200,SPECIMAN_GROSS_SUCC, null, specimanMst);
				
			} else
			{
				return new Response(ERROR, ERR_500,SPECIMAN_GROSS_FAIL, null, null);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500,SPECIMAN_NOT_FOUND, null, null);
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public Response specimanBlockRequriedInSpecimanMaster(Integer orgId,
			Integer specimanId, Character specimanBlock)
			throws ApplicationException {
		try
		{
			SpecimanMaster specimanMaster = findById(specimanId);
			if (specimanMaster.getSpecimanId() != 0)
			{
				specimanMaster.setSpecimanBlock(specimanBlock);
				SpecimanMaster specimanMst = update(specimanMaster);
				return new Response(SUCCESS, SUCCESS_200,SPECIMAN_GROSS_SUCC, null, specimanMst);
				
			} else
			{
				return new Response(ERROR, ERR_500,SPECIMAN_GROSS_FAIL, null, null);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500,SPECIMAN_NOT_FOUND, null, null);
		}
	}


}
