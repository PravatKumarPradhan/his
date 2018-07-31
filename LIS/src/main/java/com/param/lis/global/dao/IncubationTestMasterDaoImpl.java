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
import com.param.entity.lis.global.IncubationTestMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.GlobalCommonDto;
import com.param.lis.global.dto.IncubationTestMasterDto;

@Repository
@SuppressWarnings("rawtypes")
public class IncubationTestMasterDaoImpl extends  GenericDao<IncubationTestMaster, Integer> implements IIncubationTestMasterDao, ICommonConstants, IError{

	public IncubationTestMasterDaoImpl() {
		super(IncubationTestMaster.class);
	}

	@Autowired
	private DozerBeanMapper mapper;

	@Override
	public Response addIncubationTest(IncubationTestMasterDto incubationTestMasterDto)throws ApplicationException {
		try{
			IncubationTestMaster incubationTestMaster = new IncubationTestMaster();
			incubationTestMaster.setCreatedBy(incubationTestMasterDto.getCreatedBy());
			incubationTestMaster.setCreatedDate(incubationTestMasterDto.getCreatedDate());
			incubationTestMaster.setCode(incubationTestMasterDto.getCode());
			incubationTestMaster.setDesc(incubationTestMasterDto.getDesc());
			incubationTestMaster.setStatus(incubationTestMasterDto.getStatus());
			incubationTestMaster.setOrgId(incubationTestMasterDto.getOrgId());
			incubationTestMaster.setUpdatedBy(incubationTestMasterDto.getUpdatedBy());
			incubationTestMaster.setUpdatedDate(incubationTestMasterDto.getUpdatedDate());
			incubationTestMaster = save(incubationTestMaster);
			return new Response<>(SUCCESS, SUCCESS_200,INCUBATION_TEST_MASTER_ADD_SUCC, null,null);
		}catch(HibernateException he){
			he.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,INCUBATION_TEST_MASTER_ADD_FAIL, null,null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response checkIncubationTest(IncubationTestMasterDto incubationTestMasterDto)throws ApplicationException {
		try
		{
			List<IncubationTestMasterDto> listIncubationTestMasterFactory = sessionFactory.getCurrentSession().getNamedQuery("GET_REPORT_TYPE_BY_INCUBATION_TEST_CODE").setString("code", incubationTestMasterDto.getCode().trim().toLowerCase()).setInteger("orgId", incubationTestMasterDto.getOrgId()).setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).list();
			return new Response(SUCCESS, null, null, listIncubationTestMasterFactory, null);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getIncubationTestById(Integer orgId, Integer  incubationTestId)
			throws ApplicationException {
		
		try
		{
			GlobalCommonDto incubationTestMaster = (GlobalCommonDto) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_REPORT_TYPE_BY_INCUBATION_TEST_MASTER_ID").setInteger("orgId", orgId)
					.setInteger("incubationTestId", incubationTestId)
                    .setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class))					
					.uniqueResult();
			
			return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, incubationTestMaster);

		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500,INCUBATION_TEST_MASTER__CORDS_NOT_FOUND, null, null);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response updateIncubationTest(IncubationTestMasterDto IncubationTestMasterDto)
			throws ApplicationException {
		try
		{
			IncubationTestMaster reportTypeMaster = mapper.map(IncubationTestMasterDto, IncubationTestMaster.class,"IncubationTestMasterDtoTOIncubationTestMaster");
			update(reportTypeMaster);
			return new Response(SUCCESS, null, null, null, null);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500,INCUBATION_TEST_MASTER_ADD_FAIL, null, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response ActivateInactivateIncubationTest(Integer orgId,
			Integer unitId, Character unitStatus)
			throws ApplicationException {

		try
		{
			IncubationTestMaster incubationTestMaster = findById(unitId);
			if (incubationTestMaster.getIncubationTestId() != 0)
			{
				incubationTestMaster.setStatus(unitStatus);;
				IncubationTestMaster incubationMst = update(incubationTestMaster);
				return new Response(SUCCESS, SUCCESS_200,INCUBATION_TEST_MASTER_ADD_SUCC, null, incubationMst);
				
			} else
			{
				return new Response(ERROR, ERR_500,INCUBATION_TEST_MASTER_NOT_FOUND, null, null);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500,INCUBATION_TEST_MASTER_NOT_FOUND, null, null);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response listIncubationTest(Integer orgId, Integer offset,
			Integer recordPerPage) throws ApplicationException {
		try
		{
			List<GlobalCommonDto> listUnitMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PAGINATED_INCUBATION_TEST_MASTER_LIST").setInteger("orgId", orgId)
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
	public Response getToTalIncubationTest(Integer orgId)
			throws ApplicationException {
		try
		{
			BigInteger incubationTestMasterCount = (BigInteger) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_TOTAL_INCUBATION_TEST_RECORD").setInteger("orgId", orgId).uniqueResult();
			
			
			if (incubationTestMasterCount.compareTo(BigInteger.ZERO) == 1)
			{
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, incubationTestMasterCount);
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
	public Response updateCheckIncubationTestCodeAvaiable(
			IncubationTestMasterDto incubationTestMasterDto)
			throws ApplicationException {
		try {
			List<GlobalCommonDto> listCheckPhlebotomyCodeFactory = sessionFactory
					.getCurrentSession()
					.getNamedQuery("UPDATE_GET_REPORT_TYPE_BY_INCUBATION_TEST_CODE")
					.setString("code",incubationTestMasterDto.getCode().trim().toLowerCase())
					.setInteger("orgId", incubationTestMasterDto.getOrgId())
					.setInteger("incubationTestId",	incubationTestMasterDto.getIncubationTestId())
					.setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).list();
			return new Response(SUCCESS, null, null,listCheckPhlebotomyCodeFactory, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


}
