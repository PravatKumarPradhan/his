package com.param.lis.global.dao;

import java.math.BigInteger;
import java.util.List;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

import org.apache.poi.hssf.util.HSSFColor.GOLD;
import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.param.entity.lis.global.MediaMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.GlobalCommonDto;
import com.param.lis.global.dto.MediaMasterDto;

@Repository
@SuppressWarnings("rawtypes")
public class MediaMasterDaoImpl extends GenericDao<MediaMaster, Integer> implements IMediaMasterDao, ICommonConstants, IError{

	@Autowired
	private DozerBeanMapper mapper;

	public MediaMasterDaoImpl() {
		super(MediaMaster.class);
	}

	@Override
	public Response saveMediaMaster(MediaMasterDto mediaMasterDto)throws ApplicationException {
		try{
			MediaMaster mediaMaster = new MediaMaster();
			mediaMaster.setCreatedBy(mediaMasterDto.getCreatedBy());
			mediaMaster.setCreatedDate(mediaMasterDto.getCreatedDate());
			mediaMaster.setCode(mediaMasterDto.getCode());
			mediaMaster.setDesc(mediaMasterDto.getDesc());
			mediaMaster.setStatus(mediaMasterDto.getStatus());
			mediaMaster.setOrgId(mediaMasterDto.getOrgId());
			mediaMaster.setUpdatedBy(mediaMasterDto.getUpdatedBy());
			mediaMaster.setUpdatedDate(mediaMasterDto.getUpdatedDate());
			mediaMaster = save(mediaMaster);
			return new Response<>(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null,null);
		}catch(HibernateException he){
			he.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, null, null, null,null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response checkMediaCodeAvaiable(MediaMasterDto mediaMasterDto)throws ApplicationException {
		try
		{
			List<GlobalCommonDto> listMediaMasterDtosessionFactory = sessionFactory.getCurrentSession().getNamedQuery("GET_MEDIA_BY_CODE").setString("code", mediaMasterDto.getCode().trim().toLowerCase()).setInteger("orgId", mediaMasterDto.getOrgId()).setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).list();
			return new Response(SUCCESS, null, null, listMediaMasterDtosessionFactory, null);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getMediaMasterById(Integer orgId, Integer  mediaId)
			throws ApplicationException {
		
		try
		{
			GlobalCommonDto containerMaster = (GlobalCommonDto) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_MEDIA_BY_MEDIA_ID").setInteger("orgId", orgId)
					.setInteger("mediaId", mediaId)
                    .setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class))					
					.uniqueResult();
			
			return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, containerMaster);

		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, MEDIA_NOT_FOUND, null, null);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response updateMediaMaster(MediaMasterDto mediaMasterDto)
			throws ApplicationException {
		try
		{
			MediaMaster mediaMaster = mapper.map(mediaMasterDto, MediaMaster.class,
					"MediaMasterDtoTOMediaMaster");
			MediaMaster prerequisitesMst = save(mediaMaster);
			if (prerequisitesMst != null)
			{
				return new Response(SUCCESS, SUCCESS_200, MEDIA_UPDATE_SUCC, null, null);
			} else
			{
				return new Response(SUCCESS, SUCCESS_200, MEDIA_UPDATE_FAIL, null, null);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, MEDIA_UPDATE_FAIL, null, null);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response ActivateInactivateMediaMaster(Integer orgId,
			Integer mediaId, Character mediaStatus)
			throws ApplicationException {

		try
		{
			MediaMaster mediaMaster = findById(mediaId);
			if (mediaMaster.getMediaId() != 0)
			{
				mediaMaster.setStatus(mediaStatus);
				MediaMaster mediaMst = update(mediaMaster);
				if (mediaMst.getStatus() == ACTIVE)
				{
					return new Response(SUCCESS, SUCCESS_200, MEDIA_ACTIVATE_SUCC, null, null);
				} else if (mediaMst.getStatus()== INACTIVE)
				{
					return new Response(SUCCESS, SUCCESS_200, MEDIA_INACTIVATE_SUCC, null, null);
				} else
				{
					if (mediaStatus == ACTIVE)
					{
						return new Response(SUCCESS, SUCCESS_200, MEDIA_ACTIVATE_FAIL, null, null);
					} else
					{
						return new Response(SUCCESS, SUCCESS_200, MEDIA_INACTIVATE_FAIL, null, null);
					}
				}
			} else
			{
				return new Response(SUCCESS, SUCCESS_200, MEDIA_NOT_FOUND, null, null);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500,MEDIA_NOT_FOUND, null, null);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response listMediaMaster(Integer orgId, Integer offset,
			Integer recordPerPage) throws ApplicationException {
		try
		{
			List<GlobalCommonDto> listMediaMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PAGINATED_MEDIA_MASTER_LIST").setInteger("orgId", orgId)
					.setFirstResult(offset != null ? offset : 0)
					.setMaxResults(recordPerPage != null ? recordPerPage : 10)
					.setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).list();
			
			if(listMediaMasterDto.isEmpty())
			{
				return new Response(ERROR, ERR_500,MEDIA_RECORDS_NOT_FOUND, null, null);
			}
			else{
				return new Response(SUCCESS, SUCCESS_200, null, listMediaMasterDto, null);
			}
			
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public Response getToTalMediaMasterRecord(Integer orgId)
			throws ApplicationException {
		try
		{
			BigInteger mediaMasterTotalRecordCount = (BigInteger) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_TOTAL_MEDIA_RECORD").setInteger("orgId", orgId).uniqueResult();
			if (mediaMasterTotalRecordCount.compareTo(BigInteger.ZERO) == 1)
			{
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, mediaMasterTotalRecordCount);
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
	public Response updateCheckMediaCodeAvaiable(MediaMasterDto mediaMasterDto)
			throws ApplicationException {
		try {
			List<GlobalCommonDto> listCheckPhlebotomyCodeFactory = sessionFactory
					.getCurrentSession()
					.getNamedQuery("UPDATE_GET_MEDIA_BY_CODE")
					.setString("code",mediaMasterDto.getCode().trim().toLowerCase())
					.setInteger("orgId", mediaMasterDto.getOrgId())
					.setInteger("mediaId",	mediaMasterDto.getMediaId())
					.setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).list();
			return new Response(SUCCESS, null, null,listCheckPhlebotomyCodeFactory, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

}
