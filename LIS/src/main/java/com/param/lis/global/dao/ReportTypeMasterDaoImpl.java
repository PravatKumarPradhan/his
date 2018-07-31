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
import com.param.entity.lis.global.ReportTypeMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.GlobalCommonDto;
import com.param.lis.global.dto.ReagentMasterDto;
import com.param.lis.global.dto.ReportTypeMasterDto;

@Repository
@SuppressWarnings("rawtypes")
public class ReportTypeMasterDaoImpl extends  GenericDao<ReportTypeMaster, Integer> implements IReportTypeMasterDao, ICommonConstants, IError{

	public ReportTypeMasterDaoImpl() {
		super(ReportTypeMaster.class);
	}

	@Autowired
	private DozerBeanMapper mapper;

	@Override
	public Response addReportTypeMaster(ReportTypeMasterDto reportTypeMasterDto)throws ApplicationException {
		try{
			ReportTypeMaster reportTypeMaster = new ReportTypeMaster();
			reportTypeMaster.setCreatedBy(reportTypeMasterDto.getCreatedBy());
			reportTypeMaster.setCreatedDate(reportTypeMasterDto.getCreatedDate());
			reportTypeMaster.setCode(reportTypeMasterDto.getCode());
			reportTypeMaster.setDesc(reportTypeMasterDto.getDesc());
			reportTypeMaster.setStatus(reportTypeMasterDto.getStatus());
			reportTypeMaster.setOrgId(reportTypeMasterDto.getOrgId());
			reportTypeMaster.setUpdatedBy(reportTypeMasterDto.getUpdatedBy());
			reportTypeMaster.setUpdatedDate(reportTypeMasterDto.getUpdatedDate());
			reportTypeMaster = save(reportTypeMaster);
			return new Response<>(SUCCESS, SUCCESS_200,REPORT_TYPR_ADD_SUCC, null,null);
		}catch(HibernateException he){
			he.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,REPORT_TYPRADD_FAIL, null,null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response checkReportTypeMaster(ReportTypeMasterDto reportTypeMasterDto)throws ApplicationException {
		try
		{
			List<GlobalCommonDto> listUnitMasteDtosessionFactory = sessionFactory.getCurrentSession().getNamedQuery("GET_REPORT_TYPE_BY_REAGENT_CODE").setString("code", reportTypeMasterDto.getCode().trim().toLowerCase()).setInteger("orgId", reportTypeMasterDto.getOrgId()).setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).list();
			return new Response(SUCCESS, null, null, listUnitMasteDtosessionFactory, null);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getaddReportTypeMasterById(Integer orgId, Integer  reportTypeId)
			throws ApplicationException {
		
		try
		{
			GlobalCommonDto containerMaster = (GlobalCommonDto) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_REPORT_TYPE_BY_REAGENT_ID").setInteger("orgId", orgId)
					.setInteger("reportTypeId", reportTypeId)
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
	public Response updateReportTypeMaster(ReportTypeMasterDto reportTypeMasterDto)
			throws ApplicationException {
		try
		{
			ReportTypeMaster reportTypeMaster = mapper.map(reportTypeMasterDto, ReportTypeMaster.class,"ReportTypeMasterDtoTOReportTypeMaster");
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
	public Response ActivateInactivateReportTypeMaster(Integer orgId,
			Integer unitId, Character unitStatus)
			throws ApplicationException {

		try
		{
			ReportTypeMaster reportTypeMaster = findById(unitId);
			if (reportTypeMaster.getReportTypeId() != 0)
			{
				reportTypeMaster.setStatus(unitStatus);;
				ReportTypeMaster reagentMst = update(reportTypeMaster);
				return new Response(SUCCESS, SUCCESS_200,REPORT_TYPRACTIVATE_SUCC, null, reagentMst);
				
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
	public Response listReportTypeMaster(Integer orgId, Integer offset,
			Integer recordPerPage) throws ApplicationException {
		try
		{
			List<GlobalCommonDto> listUnitMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PAGINATED_REPORT_TYPE_MASTER_LIST").setInteger("orgId", orgId)
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
	public Response getToTalReportTypeMasterRecord(Integer orgId)
			throws ApplicationException {
		try
		{
			BigInteger reportTypeMasterCount = (BigInteger) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_TOTAL_REPORT_TYPE_RECORD").setInteger("orgId", orgId).uniqueResult();
			
			
			if (reportTypeMasterCount.compareTo(BigInteger.ZERO) == 1)
			{
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, reportTypeMasterCount);
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
			ReportTypeMasterDto reportTypeMasterDto) throws ApplicationException {
		try {
			List<GlobalCommonDto> listCheckPhlebotomyCodeFactory = sessionFactory
					.getCurrentSession()
					.getNamedQuery("UPDATE_GET_REPORT_TYPE_BY_REAGENT_CODE")
					.setString("code",reportTypeMasterDto.getCode().trim().toLowerCase())
					.setInteger("orgId", reportTypeMasterDto.getOrgId())
					.setInteger("reportTypeId",	reportTypeMasterDto.getReportTypeId())
					.setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).list();
			return new Response(SUCCESS, null, null,listCheckPhlebotomyCodeFactory, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


}
