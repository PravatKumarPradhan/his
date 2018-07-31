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
import com.param.entity.lis.global.LabUnitMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.GlobalCommonDto;
import com.param.lis.global.dto.UnitMasterDto;

@Repository
@SuppressWarnings("rawtypes")
public class UnitMasterDaoImpl extends  GenericDao<LabUnitMaster, Integer> implements IUnitMasterDao, ICommonConstants, IError{

	public UnitMasterDaoImpl() {
		super(LabUnitMaster.class);
	}

	@Autowired
	private DozerBeanMapper mapper;

	@Override
	public Response addUnitMaster(UnitMasterDto unitMasterDto)throws ApplicationException {
		try{
			LabUnitMaster labUnitMaster = new LabUnitMaster();
			labUnitMaster.setCreatedBy(unitMasterDto.getCreatedBy());
			labUnitMaster.setCreatedDate(unitMasterDto.getCreatedDate());
			labUnitMaster.setCode(unitMasterDto.getCode());
			labUnitMaster.setDesc(unitMasterDto.getDesc());
			labUnitMaster.setStatus(unitMasterDto.getStatus());
			labUnitMaster.setOrgId(unitMasterDto.getOrgId());
			labUnitMaster.setUpdatedBy(unitMasterDto.getUpdatedBy());
			labUnitMaster.setUpdatedDate(unitMasterDto.getUpdatedDate());
			labUnitMaster = save(labUnitMaster);
			return new Response<>(SUCCESS, SUCCESS_200, UNIT_ADD_SUCC, null,null);
		}catch(HibernateException he){
			he.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201, UNIT_ADD_FAIL, null,null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response checkUnitCodeAvaiable(UnitMasterDto unitMasterDto)throws ApplicationException {
		try
		{
			List<GlobalCommonDto> listUnitMasteDtosessionFactory = sessionFactory.getCurrentSession().getNamedQuery("GET_UNIT_BY_UNIT_CODE").setString("code", unitMasterDto.getCode().trim().toLowerCase()).setInteger("orgId", unitMasterDto.getOrgId()).setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).list();
			return new Response(SUCCESS, null, null, listUnitMasteDtosessionFactory, null);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getUnitMasterById(Integer orgId, Integer  unitId)
			throws ApplicationException {
		
		try
		{
			GlobalCommonDto containerMaster = (GlobalCommonDto) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_UNIT_BY_UNIT_ID").setInteger("orgId", orgId)
					.setInteger("unitId", unitId)
                    .setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class))					
					.uniqueResult();
			
			return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, containerMaster);

		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, UNIT_NOT_FOUND, null, null);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response updateUnitMaster(UnitMasterDto unitMasterDto)
			throws ApplicationException {
		try
		{
			LabUnitMaster labUnitMaster = mapper.map(unitMasterDto, LabUnitMaster.class,"UnitMasterDtoTOUnitMaster");
			update(labUnitMaster);
			return new Response(SUCCESS, null, null, null, null);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500, UNIT_UPDATE_FAIL, null, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response ActivateInactivateUnitMaster(Integer orgId,
			Integer unitId, Character unitStatus)
			throws ApplicationException {

		try
		{
			LabUnitMaster labUnitMaster = findById(unitId);
			if (labUnitMaster.getUnitId() != 0)
			{
				labUnitMaster.setStatus(unitStatus);;
				LabUnitMaster unitMst = update(labUnitMaster);
				return new Response(SUCCESS, SUCCESS_200, UNIT_ACTIVATE_SUCC, null, unitMst);
				
			} else
			{
				return new Response(ERROR, ERR_500,UNIT_NOT_FOUND, null, null);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500,UNIT_NOT_FOUND, null, null);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response listUnitMaster(Integer orgId, Integer offset,
			Integer recordPerPage) throws ApplicationException {
		try
		{
			List<GlobalCommonDto> listUnitMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PAGINATED_UNIT_MASTER_LIST").setInteger("orgId", orgId)
					.setFirstResult(offset != null ? offset : 0)
					.setMaxResults(recordPerPage != null ? recordPerPage : 10)
					.setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).list();
			return new Response(SUCCESS, null, null, listUnitMasterDto, null);
			/*if(listUnitMasterDto.isEmpty())
			{
				return new Response(ERROR, ERR_500, SAMPLE_RECORDS_NOT_FOUND, null, null);
			}
			else{
				return new Response(SUCCESS, SUCCESS_200, null, listUnitMasterDto, null);
			}*/
			
			
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public Response getToTalUnitMasterRecord(Integer orgId)
			throws ApplicationException {
		try
		{
			BigInteger unitMasterTotalRecordCount = (BigInteger) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_TOTAL_UNIT_RECORD").setInteger("orgId", orgId).uniqueResult();
			
			
			if (unitMasterTotalRecordCount.compareTo(BigInteger.ZERO) == 1)
			{
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, unitMasterTotalRecordCount);
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
	public Response updateCheckUnitCodeAvaiable(UnitMasterDto unitMasterDto)
			throws ApplicationException {
		try {
			List<GlobalCommonDto> listCheckPhlebotomyCodeFactory = sessionFactory
					.getCurrentSession()
					.getNamedQuery("UPDATE_GET_UNIT_BY_UNIT_CODE")
					.setString("code",unitMasterDto.getCode().trim().toLowerCase())
					.setInteger("orgId", unitMasterDto.getOrgId())
					.setInteger("unitId",	unitMasterDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).list();
			return new Response(SUCCESS, null, null,listCheckPhlebotomyCodeFactory, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


}
