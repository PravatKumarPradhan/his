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
import com.param.entity.lis.global.WtfilmStudyMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.GlobalCommonDto;
import com.param.lis.global.dto.WtfilmStudyMasterDto;

@Repository
@SuppressWarnings("rawtypes")
public class WtfilmStudyMasterDaoImpl extends  GenericDao<WtfilmStudyMaster, Integer> implements IWtfilmStudyMasterDao, ICommonConstants, IError{

	public WtfilmStudyMasterDaoImpl() {
		super(WtfilmStudyMaster.class);
	}

	@Autowired
	private DozerBeanMapper mapper;

	@Override
	public Response addWtfilmStudyMaster(WtfilmStudyMasterDto wtfilmStudyMasterDto)throws ApplicationException {
		try{
			WtfilmStudyMaster wtfilmStudyMaster = new WtfilmStudyMaster();
			wtfilmStudyMaster.setCreatedBy(wtfilmStudyMasterDto.getCreatedBy());
			wtfilmStudyMaster.setCreatedDate(wtfilmStudyMasterDto.getCreatedDate());
			wtfilmStudyMaster.setCode(wtfilmStudyMasterDto.getCode());
			wtfilmStudyMaster.setDesc(wtfilmStudyMasterDto.getDesc());
			wtfilmStudyMaster.setStatus(wtfilmStudyMasterDto.getStatus());
			wtfilmStudyMaster.setOrgId(wtfilmStudyMasterDto.getOrgId());
			wtfilmStudyMaster.setUpdatedBy(wtfilmStudyMasterDto.getUpdatedBy());
			wtfilmStudyMaster.setUpdatedDate(wtfilmStudyMasterDto.getUpdatedDate());
			wtfilmStudyMaster = save(wtfilmStudyMaster);
			return new Response<>(SUCCESS, SUCCESS_200,WTIFILMSTUDY_ADD_SUCC, null,null);
		}catch(HibernateException he){
			he.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,WTIFILMSTUDY_ADD_FAIL, null,null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response checkWtfilmStudyMaster(WtfilmStudyMasterDto wtfilmStudyMasterDto)throws ApplicationException {
		try
		{
			List<GlobalCommonDto> listwtfilmStudyMasterDtoFactory = sessionFactory.getCurrentSession().getNamedQuery("GET_WTFILMSTUDY_TYPE_BY_WTFILMSTUDY_CODE").setString("code", wtfilmStudyMasterDto.getCode().trim().toLowerCase()).setInteger("orgId", wtfilmStudyMasterDto.getOrgId()).setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).list();
			return new Response(SUCCESS, null, null, listwtfilmStudyMasterDtoFactory, null);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getWtfilmStudyMasterById(Integer orgId, Integer  wtfilmStudyClassId)
			throws ApplicationException {
		
		try
		{
			GlobalCommonDto containerMaster = (GlobalCommonDto) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_WTFILMSTUDY_TYPE_BY_WTFILMSTUDY_ID").setInteger("orgId", orgId)
					.setInteger("wetfilmStudyId", wtfilmStudyClassId)
                    .setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class))					
					.uniqueResult();
			
			return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, containerMaster);

		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500,WTIFILMSTUDY_NOT_FOUND, null, null);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response updateWtfilmStudyMaster(WtfilmStudyMasterDto wtfilmStudyMasterDto)
			throws ApplicationException {
		try
		{
			WtfilmStudyMaster reportTypeMaster = mapper.map(wtfilmStudyMasterDto, WtfilmStudyMaster.class,"WtfilmStudyMasterDtoTOWtfilmStudyMaster");
			update(reportTypeMaster);
			return new Response(SUCCESS, null, null, null, null);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500,WTIFILMSTUDY_UPDATE_FAIL, null, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response ActivateInactivateWtfilmStudyMaster(Integer orgId,
			Integer unitId, Character unitStatus)
			throws ApplicationException {

		try
		{
			WtfilmStudyMaster wtfilmStudyMaster = findById(unitId);
			if (wtfilmStudyMaster.getWetfilmStudyId() != 0)
			{
				wtfilmStudyMaster.setStatus(unitStatus);;
				WtfilmStudyMaster wtfilmStudyMst = update(wtfilmStudyMaster);
				return new Response(SUCCESS, SUCCESS_200,WTIFILMSTUDY_ACTIVATE_SUCC, null, wtfilmStudyMst);
				
			} else
			{
				return new Response(ERROR, ERR_500,WTIFILMSTUDY_NOT_FOUND, null, null);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500,WTIFILMSTUDY_NOT_FOUND, null, null);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response listWtfilmStudyMaster(Integer orgId, Integer offset,
			Integer recordPerPage) throws ApplicationException {
		try
		{
			List<GlobalCommonDto> listWtfilmStudyDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PAGINATED_WTFILMSTUDY_MASTER_LIST").setInteger("orgId", orgId)
					.setFirstResult(offset != null ? offset : 0)
					.setMaxResults(recordPerPage != null ? recordPerPage : 10)
					.setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).list();
			return new Response(SUCCESS, null, null, listWtfilmStudyDto, null);
		
			
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public Response getToTalWtfilmStudyMaster(Integer orgId)
			throws ApplicationException {
		try
		{
			BigInteger getlistWtfilmStudyMasterCount = (BigInteger) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_TOTAL_WTFILMSTUDY_RECORD").setInteger("orgId", orgId).uniqueResult();
			
			
			if (getlistWtfilmStudyMasterCount.compareTo(BigInteger.ZERO) == 1)
			{
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, getlistWtfilmStudyMasterCount);
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
	public Response updateCheckWtfilmStudyCodeAvaiable(
			WtfilmStudyMasterDto wtfilmStudyMasterDto)
			throws ApplicationException {
		try {
			List<GlobalCommonDto> listCheckPhlebotomyCodeFactory = sessionFactory
					.getCurrentSession()
					.getNamedQuery("UPDATE_GET_WTFILMSTUDY_TYPE_BY_WTFILMSTUDY_CODE")
					.setString("code",wtfilmStudyMasterDto.getCode().trim().toLowerCase())
					.setInteger("orgId", wtfilmStudyMasterDto.getOrgId())
					.setInteger("wetfilmStudyId",wtfilmStudyMasterDto.getWetfilmStudyId())
					.setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).list();
			return new Response(SUCCESS, null, null,listCheckPhlebotomyCodeFactory, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


}
