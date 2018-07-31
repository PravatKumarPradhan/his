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
import com.param.entity.lis.global.SensitivityMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.GlobalCommonDto;
import com.param.lis.global.dto.SensitivityMasterDto;

@Repository
@SuppressWarnings("rawtypes")
public class SensitivityTestResultMasterDaoImpl extends  GenericDao<SensitivityMaster, Integer> implements ISensitivityTestResultMasterDao, ICommonConstants, IError{

	public SensitivityTestResultMasterDaoImpl() {
		super(SensitivityMaster.class);
	}

	@Autowired
	private DozerBeanMapper mapper;

	@Override
	public Response addSensitivityTestResultMaster(SensitivityMasterDto sensitivityTestResultMasterDto)throws ApplicationException {
		try{
			SensitivityMaster sensitivityTestResultMaster = new SensitivityMaster();
			sensitivityTestResultMaster.setCreatedBy(sensitivityTestResultMasterDto.getCreatedBy());
			sensitivityTestResultMaster.setCreatedDate(sensitivityTestResultMasterDto.getCreatedDate());
			sensitivityTestResultMaster.setCode(sensitivityTestResultMasterDto.getCode());
			sensitivityTestResultMaster.setDesc(sensitivityTestResultMasterDto.getDesc());
			sensitivityTestResultMaster.setStatus(sensitivityTestResultMasterDto.getStatus());
			sensitivityTestResultMaster.setOrgId(sensitivityTestResultMasterDto.getOrgId());
			sensitivityTestResultMaster.setUpdatedBy(sensitivityTestResultMasterDto.getUpdatedBy());
			sensitivityTestResultMaster.setUpdatedDate(sensitivityTestResultMasterDto.getUpdatedDate());
			sensitivityTestResultMaster = save(sensitivityTestResultMaster);
			return new Response<>(SUCCESS, SUCCESS_200,SENSITIVITY_ADD_SUCC, null,null);
		}catch(HibernateException he){
			he.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,SENSITIVITY_ADD_FAIL, null,null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response checkSensitivityTestResultMaster(SensitivityMasterDto sensitivityTestResultMasterDto)throws ApplicationException {
		try
		{
			List<GlobalCommonDto> listsensitivityTestResultMasterDtoFactory = sessionFactory.getCurrentSession().getNamedQuery("GET_SENSITIVITY_TYPE_RESULT_BY_SENSITIVITY_CODE").setString("code", sensitivityTestResultMasterDto.getCode().trim().toLowerCase()).setInteger("orgId", sensitivityTestResultMasterDto.getOrgId()).setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).list();
			return new Response(SUCCESS, null, null, listsensitivityTestResultMasterDtoFactory, null);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getSensitivityTestResultMasterById(Integer orgId, Integer  sensitivityTestResultId)
			throws ApplicationException {
		
		try
		{
			GlobalCommonDto containerMaster = (GlobalCommonDto) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_SENSITIVITY_TYPE_RESULT_BY_SENSITIVITY_ID").setInteger("orgId", orgId)
					.setInteger("sensitivityId", sensitivityTestResultId)
                    .setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class))					
					.uniqueResult();
			
			return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, containerMaster);

		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500,SENSITIVITY_NOT_FOUND, null, null);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response updateSensitivityTestResultMaster(SensitivityMasterDto sensitivityTestResultMasterDto)
			throws ApplicationException {
		try
		{
			SensitivityMaster reportTypeMaster = mapper.map(sensitivityTestResultMasterDto, SensitivityMaster.class,"SensitivityTestResultMasterDtoTOSensitivityTestResultMaster");
			update(reportTypeMaster);
			return new Response(SUCCESS, null, null, null, null);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500,SENSITIVITY_UPDATE_FAIL, null, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response ActivateInactivateSensitivityTestResultMaster(Integer orgId,
			Integer sensitivityTestResulId, Character sensitivityTestResultStatus)
			throws ApplicationException {

		try
		{
			SensitivityMaster sensitivityTestResultMaster = findById(sensitivityTestResulId);
			if (sensitivityTestResultMaster.getSensitivityId() != 0)
			{
				sensitivityTestResultMaster.setStatus(sensitivityTestResultStatus);;
				SensitivityMaster wtfilmStudyMst = update(sensitivityTestResultMaster);
				return new Response(SUCCESS, SUCCESS_200,SENSITIVITY_ACTIVATE_SUCC, null, wtfilmStudyMst);
				
			} else
			{
				return new Response(ERROR, ERR_500,SENSITIVITY_NOT_FOUND, null, null);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500,SENSITIVITY_NOT_FOUND, null, null);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response listSensitivityTestResultMaster(Integer orgId, Integer offset,
			Integer recordPerPage) throws ApplicationException {
		try
		{
			List<GlobalCommonDto> listSensitivityTestResultMaster = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_SENSITIVITY_TYPE_RESULT_MASTER_LIST").setInteger("orgId", orgId)
					.setFirstResult(offset != null ? offset : 0)
					.setMaxResults(recordPerPage != null ? recordPerPage : 10)
					.setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).list();
			return new Response(SUCCESS, null, null, listSensitivityTestResultMaster, null);
		
			
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public Response getToTalSensitivityTestResultMaster(Integer orgId)
			throws ApplicationException {
		try
		{
			BigInteger getToTalSensitivityTestResultMaster = (BigInteger) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_TOTAL_SENSITIVITY_TYPE_RESULT_RECORD").setInteger("orgId", orgId).uniqueResult();
			
			
			if (getToTalSensitivityTestResultMaster.compareTo(BigInteger.ZERO) == 1)
			{
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, getToTalSensitivityTestResultMaster);
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
	public Response updateCheckSensitivityTestResultCodeAvaiable(
			SensitivityMasterDto sensitivityTestResultMasterDto)
			throws ApplicationException {
		try {
			List<GlobalCommonDto> listCheckPhlebotomyCodeFactory = sessionFactory
					.getCurrentSession()
					.getNamedQuery("UPDATE_GET_SENSITIVITY_TYPE_RESULT_BY_SENSITIVITY_CODE")
					.setString("code",sensitivityTestResultMasterDto.getCode().trim().toLowerCase())
					.setInteger("orgId", sensitivityTestResultMasterDto.getOrgId())
					.setInteger("sensitivityId",	sensitivityTestResultMasterDto.getSensitivityId())
					.setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).list();
			return new Response(SUCCESS, null, null,listCheckPhlebotomyCodeFactory, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


}
