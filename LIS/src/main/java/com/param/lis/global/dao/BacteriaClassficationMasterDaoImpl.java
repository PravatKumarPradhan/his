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
import com.param.entity.lis.global.BactClassificationMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.BactClassificationMasterDto;
import com.param.lis.global.dto.GlobalCommonDto;
//BacteriaClassfication new commit
@Repository
@SuppressWarnings("rawtypes")
public class BacteriaClassficationMasterDaoImpl extends  GenericDao<BactClassificationMaster, Integer> implements IBacteriaClassficationMasterDao, ICommonConstants, IError{

	public BacteriaClassficationMasterDaoImpl() {
		super(BactClassificationMaster.class);
	}

	@Autowired
	private DozerBeanMapper mapper;

	@Override
	public Response addBacteriaClassficationMaster(BactClassificationMasterDto bactClassificationMasterDto)throws ApplicationException {
		try{
			BactClassificationMaster bactClassificationMaster = new BactClassificationMaster();
			bactClassificationMaster.setCreatedBy(bactClassificationMasterDto.getCreatedBy());
			bactClassificationMaster.setCreatedDate(bactClassificationMasterDto.getCreatedDate());
			bactClassificationMaster.setCode(bactClassificationMasterDto.getCode());
			bactClassificationMaster.setDesc(bactClassificationMasterDto.getDesc());
			bactClassificationMaster.setStatus(bactClassificationMasterDto.getStatus());
			bactClassificationMaster.setOrgId(bactClassificationMasterDto.getOrgId());
			bactClassificationMaster.setUpdatedBy(bactClassificationMasterDto.getUpdatedBy());
			bactClassificationMaster.setUpdatedDate(bactClassificationMasterDto.getUpdatedDate());
			bactClassificationMaster = save(bactClassificationMaster);
			return new Response<>(SUCCESS, SUCCESS_200,BACTERIA_CLASSFICATION_ADD_SUCC, null,null);
		}catch(HibernateException he){
			he.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,BACTERIA_CLASSFICATION_ADD_FAIL, null,null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response checkBacteriaClassficationMaster(BactClassificationMasterDto bactClassificationMasterDto)throws ApplicationException {
		try
		{
			List<GlobalCommonDto> listUnitMasteDtosessionFactory = sessionFactory.getCurrentSession().getNamedQuery("GET_REPORT_TYPE_BY_BACTERIA_CLASSIFICATION_CODE").setString("code", bactClassificationMasterDto.getCode().trim().toLowerCase()).setInteger("orgId", bactClassificationMasterDto.getOrgId()).setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).list();
			return new Response(SUCCESS, null, null, listUnitMasteDtosessionFactory, null);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getBacteriaClassficationMasterById(Integer orgId, Integer  bactClassificationId)
			throws ApplicationException {
		
		try
		{
			GlobalCommonDto containerMaster = (GlobalCommonDto) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_REPORT_TYPE_BY_BACTERIA_CLASSIFICATION_ID").setInteger("orgId", orgId)
					.setInteger("bactClassificationId", bactClassificationId)
                    .setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class))					
					.uniqueResult();
			
			return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, containerMaster);

		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500,BACTERIA_CLASSFICATION_NOT_FOUND, null, null);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response updateBacteriaClassficationMaster(BactClassificationMasterDto bactClassificationMasterDto)
			throws ApplicationException {
		try
		{
			BactClassificationMaster reportTypeMaster = mapper.map(bactClassificationMasterDto, BactClassificationMaster.class,"BactClassificationMasterDtoTOBactClassificationMaster");
			update(reportTypeMaster);
			return new Response(SUCCESS, null, null, null, null);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500,BACTERIA_CLASSFICATION_UPDATE_FAIL, null, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response ActivateInactivateBacteriaClassficationMaster(Integer orgId,
			Integer unitId, Character unitStatus)
			throws ApplicationException {

		try
		{
			BactClassificationMaster BacteriaClassficationMaster = findById(unitId);
			if (BacteriaClassficationMaster.getBactClassificationId() != 0)
			{
				BacteriaClassficationMaster.setStatus(unitStatus);;
				BactClassificationMaster bacteriaClassficationMst = update(BacteriaClassficationMaster);
				return new Response(SUCCESS, SUCCESS_200,BACTERIA_CLASSFICATION_INACTIVATE_SUCC, null, bacteriaClassficationMst);
				
			} else
			{
				return new Response(ERROR, ERR_500,BACTERIA_CLASSFICATION_NOT_FOUND, null, null);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500,BACTERIA_CLASSFICATION_NOT_FOUND, null, null);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response listBacteriaClassficationMaster(Integer orgId, Integer offset,
			Integer recordPerPage) throws ApplicationException {
		try
		{
			List<GlobalCommonDto> listBacteriaClassficationMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PAGINATED_BACTERIA_CLASSIFICATION_MASTER_LIST").setInteger("orgId", orgId)
					.setFirstResult(offset != null ? offset : 0)
					.setMaxResults(recordPerPage != null ? recordPerPage : 10)
					.setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).list();
			return new Response(SUCCESS, null, null, listBacteriaClassficationMasterDto, null);
		
			
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public Response getToTalBacteriaClassficationMaster(Integer orgId)
			throws ApplicationException {
		try
		{
			BigInteger bacteriaClassficationMasterCount = (BigInteger) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_TOTAL_BACTERIA_CLASSIFICATION_RECORD").setInteger("orgId", orgId).uniqueResult();
			
			
			if (bacteriaClassficationMasterCount.compareTo(BigInteger.ZERO) == 1)
			{
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, bacteriaClassficationMasterCount);
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
	public Response updateCheckBacteriaClassficationCodeAvaiable(
			BactClassificationMasterDto bactClassificationMasterDto)
			throws ApplicationException {
		try {
			List<GlobalCommonDto> listCheckPhlebotomyCodeFactory = sessionFactory
					.getCurrentSession()
					.getNamedQuery("UPDATE_GET_REPORT_TYPE_BY_BACTERIA_CLASSIFICATION_CODE")
					.setString("code",bactClassificationMasterDto.getCode().trim().toLowerCase())
					.setInteger("orgId", bactClassificationMasterDto.getOrgId())
					.setInteger("bactClassificationId",	bactClassificationMasterDto.getBactClassificationId())
					.setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).list();
			return new Response(SUCCESS, null, null,listCheckPhlebotomyCodeFactory, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


}
