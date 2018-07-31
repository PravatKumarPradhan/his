package com.param.lis.global.dao;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.param.entity.lis.global.MediaColonyMapperMaster;
import com.param.lis.common.dto.CheckTranSactionExistsDto;
import com.param.lis.common.dto.CommonDto;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.MediaColonyMapperMasterDto;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MediaColonyMapperMasterDaoImpl extends GenericDao<MediaColonyMapperMaster, Integer>
		implements IMediaColonyMapperMasterDao, ICommonConstants, IError {

	public MediaColonyMapperMasterDaoImpl() {
		super(MediaColonyMapperMaster.class);
	}

	@Autowired
	private DozerBeanMapper mapper;

	@Override
	public Response addMediaColonyMapperMaster(MediaColonyMapperMasterDto mediaColonyMapperMasterDto)
			throws ApplicationException {
		try {
			 MediaColonyMapperMaster mediaColonyMapperMaster = new  MediaColonyMapperMaster();
			 mediaColonyMapperMaster.setCreatedBy(mediaColonyMapperMasterDto.getCreatedBy());
			 mediaColonyMapperMaster.setCreatedDate(mediaColonyMapperMasterDto.getCreatedDate());
			 mediaColonyMapperMaster.setStatus(mediaColonyMapperMasterDto.getStatus());
			 mediaColonyMapperMaster.setOrgId(mediaColonyMapperMasterDto.getOrgId());
			 mediaColonyMapperMaster.setUpdatedBy(mediaColonyMapperMasterDto.getUpdatedBy());
			 mediaColonyMapperMaster.setUpdatedDate(mediaColonyMapperMasterDto.getUpdatedDate());
			 mediaColonyMapperMaster.setMediaId(mediaColonyMapperMasterDto.getMediaId());
			 mediaColonyMapperMaster.setColonyId(mediaColonyMapperMasterDto.getColonyId());
			 mediaColonyMapperMaster.setIsDeleted(mediaColonyMapperMasterDto.getIsDeleted());
			 mediaColonyMapperMaster = save(mediaColonyMapperMaster);

			return new Response<>(SUCCESS, SUCCESS_200, MEDIA_COLONY_MAPPER_ADD_SUCC, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201, MEDIA_COLONY_MAPPER_ADD_FAIL, null, null);
	}

	@Override
	public Response listMediaColonyMapperMaster(Integer orgId, Integer offset, Integer recordPerPage)
			throws ApplicationException {
		try {
			List< MediaColonyMapperMasterDto> listMediaColonyMapperMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PAGINATED_MEDIACOLONY_CLASS_MAPPER_MASTER_LIST").setInteger("orgId", orgId)
					.setFirstResult(offset != null ? offset : 0)
					.setMaxResults(recordPerPage != null ? recordPerPage : 10)
					.setResultTransformer(Transformers.aliasToBean( MediaColonyMapperMasterDto.class)).list();
			return new Response(SUCCESS, null, null, listMediaColonyMapperMasterDto, null);

		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response getToTalMediaColonyMapperMaster(Integer orgId) throws ApplicationException {
		try {
			BigInteger mediaColonyMapperMasterCount = (BigInteger) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_TOTAL_ANTIBIOTIC_CLASS_MAPPER_RECORD").setInteger("orgId", orgId)
					.uniqueResult();

			if (mediaColonyMapperMasterCount.compareTo(BigInteger.ZERO) == 1) {
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, mediaColonyMapperMasterCount);
			} else {
				return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, BigInteger.ZERO);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response activateInactivateMediaColonyMapperMaster(Integer orgId, Integer mediaId,
			Character status) throws ApplicationException {

		try {
			if (mediaId != 0) {

				Integer result = sessionFactory.getCurrentSession()
						.getNamedQuery("ACTIVE_INACTIVE_MEDIACOLONY_CLASS_MAPPER").setInteger("orgId", orgId)
						.setCharacter("status", status).setInteger("mediaId", mediaId)
						.executeUpdate();
				if (result > 0) {
					if (status.equals(ACTIVE)) {
						return new Response(SUCCESS, SUCCESS_200, MEDIA_COLONY_MAPPER_ACTIVATE_SUCC, null, null);
					} else if (status.equals(INACTIVE)) {
						return new Response(SUCCESS, SUCCESS_200, MEDIA_COLONY_MAPPER_INACTIVATE_SUCC, null, null);
					}
				} else {
					return new Response(ERROR, ERR_500, MEDIA_COLONY_MAPPER_CORDS_NOT_FOUND, null, null);
				}

			} else {
				return new Response(ERROR, ERR_500, MEDIA_COLONY_MAPPER_CORDS_NOT_FOUND, null, null);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, MEDIA_COLONY_MAPPER_CORDS_NOT_FOUND, null, null);
		}
		return new Response(ERROR, ERR_500, MEDIA_COLONY_MAPPER_CORDS_NOT_FOUND, null, null);
	}

	@Override
	public Response updateMediaColonyMapperMaster(
			List<MediaColonyMapperMasterDto> listmediaColonyMapperMasterDto)
			throws ApplicationException {
		try
		{
		
			Integer result = sessionFactory.getCurrentSession()
					        .getNamedQuery("UPDATE_MEDIACOLONY_CLASS_MAPPER")
					        .setCharacter("isDeleted", 'Y')
					        .setInteger("orgId",listmediaColonyMapperMasterDto.get(0).getOrgId())
					        .setInteger("mediaId", listmediaColonyMapperMasterDto.get(0).getMediaId()).executeUpdate();
			System.out.println("===========>"+result);
			if(result>0)
			{
			for (Iterator iterator = listmediaColonyMapperMasterDto.iterator(); iterator.hasNext();) {
				 MediaColonyMapperMasterDto listMediaColonyMapperMaster = ( MediaColonyMapperMasterDto) iterator
						.next();
				 MediaColonyMapperMaster mediaColonyMapperMaster = mapper.map(listMediaColonyMapperMaster,  MediaColonyMapperMaster.class,"MediaColonyMapperMasterDtoTOMediaColonyMapperMaster");
				 mediaColonyMapperMaster.setIsDeleted('N');
				 mediaColonyMapperMaster.setCreatedBy(mediaColonyMapperMaster.getUpdatedBy());
				 mediaColonyMapperMaster.setCreatedDate(mediaColonyMapperMaster.getUpdatedDate());
					save(mediaColonyMapperMaster);
				}
			  return new Response(SUCCESS, SUCCESS_200, ANTIBIOTIC_UPDATE_SUCC, null, null);
			}
			else {
				return new Response(ERROR, ERR_500, MEDIA_COLONY_MAPPER_UPDATE_FAIL, null, null);
		}
		}catch(

	Exception e)
	{
		e.printStackTrace();
		return new Response(ERROR, ERR_500, MEDIA_COLONY_MAPPER_UPDATE_FAIL, null, null);
	}

	}

	@Override
	public Response checkMediaColonyMapperAvaiable(MediaColonyMapperMasterDto mediaColonyMapperMasterDto)
			throws ApplicationException {
		try {
			List<MediaColonyMapperMasterDto> listAgeGroupMasteDtosessionFactory = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_MEDIACOLONY_CLASS_MAPPER_TYPE_BY_MEDIACOLONY_CODE")
					.setInteger("orgId", mediaColonyMapperMasterDto.getOrgId())
				     .setInteger("mediaId", mediaColonyMapperMasterDto.getMediaId())
					.setResultTransformer(Transformers.aliasToBean(MediaColonyMapperMasterDto.class)).list();
			return new Response(SUCCESS, null, null, listAgeGroupMasteDtosessionFactory, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response getMediaColonyMapperMasterById(Integer orgId, Integer mediaId)
			throws ApplicationException {
		try {
			List< MediaColonyMapperMasterDto> listContainerMaster = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_MEDIACOLONY_CLASS_MAPPER_BY_MEDIACOLONY_ID").setInteger("orgId", orgId)
					.setInteger("mediaId", mediaId)
					.setResultTransformer(Transformers.aliasToBean(MediaColonyMapperMasterDto.class)).list();

			return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, listContainerMaster, null);

		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, MEDIA_COLONY_MAPPER_CORDS_NOT_FOUND, null, null);
		}
	}

	@Override
	public Response getMediaMasterClassById(Integer mediaId) throws ApplicationException {
		try {
			List<MediaColonyMapperMasterDto> listmediaColonyMapperMaster = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_MEDIACOLONY_BY_MEDIACOLONY_CLASS_ID")
					.setInteger("mediaId", mediaId)
					.setResultTransformer(Transformers.aliasToBean( MediaColonyMapperMasterDto.class)).list();

			return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE,listmediaColonyMapperMaster, null);

		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, MEDIA_COLONY_MAPPER_CORDS_NOT_FOUND, null, null);
		}
	}

	@Override
	public Response getToTalMediaRecordCount(CheckTranSactionExistsDto checkTranSactionExistsDto)
			throws ApplicationException {
		try
		{
			BigInteger mediaTotalRecordCount = (BigInteger) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_TOTAL_MEDIACOLONYS_MAPPER_COUNT").setParameterList("mediaId", checkTranSactionExistsDto.getListCommonId()).
					setInteger("orgId", checkTranSactionExistsDto.getOrgId()).uniqueResult();
			if (mediaTotalRecordCount.compareTo(BigInteger.ZERO) == 1)
			{
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, mediaTotalRecordCount);
			} else
			{
				return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, BigInteger.ZERO);
			}

		} catch (Exception e)
		{
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

}
