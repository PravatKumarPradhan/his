package com.param.lis.global.dao;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.param.entity.lis.global.AntibioticGroupAdditionMaster;
import com.param.entity.lis.global.SampleTypeMediaMappingMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.AntibioticAdditionMasterDto;
import com.param.lis.global.dto.AntibioticGroupAdditionMasterDto;
import com.param.lis.global.dto.MediaColonyMapperMasterDto;
import com.param.lis.global.dto.SampleTypeMediaMappingMasterDto;

@Repository
@SuppressWarnings("rawtypes")
public class SampleTypeMediaMappingMasterDaoImpl extends  GenericDao<SampleTypeMediaMappingMaster, Integer> implements ISampleTypeMediaMappingMasterDao, ICommonConstants, IError{

	public SampleTypeMediaMappingMasterDaoImpl() {
		super(SampleTypeMediaMappingMaster.class);
	}

	@Autowired
	private DozerBeanMapper mapper;

	@Override
	public Response addSampleTypeMediaMappingMaster(SampleTypeMediaMappingMasterDto sampleTypeMediaMappingMasterDto)throws ApplicationException {
		try{
			SampleTypeMediaMappingMaster sampleTypeMediaMappingMaster = new SampleTypeMediaMappingMaster();
			sampleTypeMediaMappingMaster.setSampleId(sampleTypeMediaMappingMasterDto.getSampleId());
			sampleTypeMediaMappingMaster.setMediaId(sampleTypeMediaMappingMasterDto.getMediaId());
			sampleTypeMediaMappingMaster.setCreatedBy(sampleTypeMediaMappingMasterDto.getCreatedBy());
			sampleTypeMediaMappingMaster.setCreatedDate(sampleTypeMediaMappingMasterDto.getCreatedDate());
			sampleTypeMediaMappingMaster.setSampleMediaStatus(sampleTypeMediaMappingMasterDto.getSampleMediaStatus());
			sampleTypeMediaMappingMaster.setOrgId(sampleTypeMediaMappingMasterDto.getOrgId());
			sampleTypeMediaMappingMaster.setUpdatedBy(sampleTypeMediaMappingMasterDto.getUpdatedBy());
			sampleTypeMediaMappingMaster.setCreatedDate(sampleTypeMediaMappingMasterDto.getCreatedDate());
			sampleTypeMediaMappingMaster.setUpdatedDate(sampleTypeMediaMappingMasterDto.getUpdatedDate());
			sampleTypeMediaMappingMaster.setIsDeleted('N');
			sampleTypeMediaMappingMaster = save(sampleTypeMediaMappingMaster);
			return new Response<>(SUCCESS, SUCCESS_200,SAMPLE_TYPE_ADD_SUCC, null,null);
		}catch(HibernateException he){
			he.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,SAMPLE_TYPE_ADD_FAIL, null,null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response checkSampleTypeMediaMappingMaster(SampleTypeMediaMappingMasterDto sampleTypeMediaMappingMasterDto)throws ApplicationException {
		try
		{
			List listSampleTypeMediaMappingFactory = sessionFactory.getCurrentSession().getNamedQuery("GET_SAMPLE_TYPE_MEDIA_MAPPER_TYPE_BY_MEDIA_CODE")
					.setInteger("orgId", sampleTypeMediaMappingMasterDto.getOrgId())
					.setInteger("sampleId", sampleTypeMediaMappingMasterDto.getSampleId()).list();
			return new Response(SUCCESS, null, null, listSampleTypeMediaMappingFactory, null);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getSampleTypeMediaMappingMasterById(Integer orgId, Integer  sampleId)
			throws ApplicationException {
		try {
			List<SampleTypeMediaMappingMasterDto> listContainerMaster = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_SAMPLE_TYPE_MAPPER_BY_MEDIA_ID").setInteger("orgId", orgId)
					.setInteger("sampleId", sampleId)
					.setResultTransformer(Transformers.aliasToBean(SampleTypeMediaMappingMasterDto.class)).list();

			return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, listContainerMaster, null);

		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, MEDIA_COLONY_MAPPER_CORDS_NOT_FOUND, null, null);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response updateSampleTypeMediaMappingMaster(List<SampleTypeMediaMappingMasterDto> listsampleTypeMediaMappingMasterDto)
			throws ApplicationException {
		try {

			Integer result = sessionFactory.getCurrentSession().getNamedQuery("UPDATE_SAMPLE_TYPE_MEDIA_MAPPER")
					.setCharacter("isDeleted", 'Y')
					.setInteger("orgId", listsampleTypeMediaMappingMasterDto.get(0).getOrgId())
					.setInteger("sampleId", listsampleTypeMediaMappingMasterDto.get(0).getSampleId())
					.executeUpdate();
			if (result > 0) {
				for (Iterator iterator = listsampleTypeMediaMappingMasterDto.iterator(); iterator.hasNext();) {
					SampleTypeMediaMappingMasterDto listSampleTypeMediaMappingMaster = (SampleTypeMediaMappingMasterDto) iterator
							.next();
					SampleTypeMediaMappingMaster sampleTypeMediaMappingMaster = mapper.map(listSampleTypeMediaMappingMaster,
							SampleTypeMediaMappingMaster.class, "SampleTypeMediaMappingMasterDtoTOSampleTypeMediaMappingMaster");
					sampleTypeMediaMappingMaster.setIsDeleted('N');
					sampleTypeMediaMappingMaster.setCreatedBy(sampleTypeMediaMappingMaster.getUpdatedBy());
					sampleTypeMediaMappingMaster.setCreatedDate(sampleTypeMediaMappingMaster.getUpdatedDate());
					save(sampleTypeMediaMappingMaster);
				}
				return new Response(SUCCESS, SUCCESS_200, SAMPLE_TYPE_USAMPLE_TYPE_SUCC, null, null);
			} else {
				return new Response(ERROR, ERR_500, SAMPLE_TYPE_UPDATE_FAIL, null, null);
			}
		} catch (

		Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500,SAMPLE_TYPE_UPDATE_FAIL, null, null);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response ActivateInactivateSampleTypeMediaMappingMaster(Integer orgId,
			Integer sampleMediaId, Character sampleMediaStatus)
			throws ApplicationException {

		try {
			if (sampleMediaId != 0) {

				Integer result = sessionFactory.getCurrentSession()
						.getNamedQuery("ACTIVE_INACTIVE_SAMPLE_TYPE_MEDIA_MAPPER").setInteger("orgId", orgId)
						.setCharacter("status", sampleMediaStatus).setInteger("sampleId", sampleMediaId)
						.executeUpdate();
				if (result > 0) {
					if (sampleMediaStatus.equals(ACTIVE)) {
						return new Response(SUCCESS, SUCCESS_200, SAMPLE_TYPE_ACTIVATE_SUCC, null, null);
					} else if (sampleMediaStatus.equals(INACTIVE)) {
						return new Response(SUCCESS, SUCCESS_200, SAMPLE_TYPE_INACTIVATE_SUCC, null, null);
					}
				} else {
					return new Response(ERROR, ERR_500, SAMPLE_TYPE_NOT_FOUND, null, null);
				}

			} else {
				return new Response(ERROR, ERR_500, SAMPLE_TYPE_NOT_FOUND, null, null);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, SAMPLE_TYPE_NOT_FOUND, null, null);
		}
		return new Response(ERROR, ERR_500, SAMPLE_TYPE_NOT_FOUND, null, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response listSampleTypeMediaMappingMaster(Integer orgId, Integer offset,
			Integer recordPerPage) throws ApplicationException {
		try
		{
			List<SampleTypeMediaMappingMasterDto> listSampleTypeMediaMappingMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PAGINATED_SAMPLE_TYPE_MEDIA_LIST").setInteger("orgId", orgId)
					.setFirstResult(offset != null ? offset : 0)
					.setMaxResults(recordPerPage != null ? recordPerPage : 10)
					.setResultTransformer(Transformers.aliasToBean(SampleTypeMediaMappingMasterDto.class)).list();
			return new Response(SUCCESS, null, null, listSampleTypeMediaMappingMasterDto, null);
		
			
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public Response getToTalSampleTypeMediaMappingMaster(Integer orgId)
			throws ApplicationException {
		try
		{
			BigInteger SampleTypeMediaMappingMasterCount = (BigInteger) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_TOTAL_SAMPLE_TYPE_MEDIA_RECORD").setInteger("orgId", orgId).uniqueResult();
			
			
			if (SampleTypeMediaMappingMasterCount.compareTo(BigInteger.ZERO) == 1)
			{
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, SampleTypeMediaMappingMasterCount);
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
	public Response getSampleTypeAddtionClassMasterById(Integer orgId,
			Integer sampleId) throws ApplicationException {
		try {
			List<SampleTypeMediaMappingMasterDto> listContainerMaster = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_SAMPLE_TYPE_MAPPER_BY_MEDIA_ID").setInteger("orgId", orgId)
					.setInteger("sampleId", sampleId)
					.setResultTransformer(Transformers.aliasToBean(SampleTypeMediaMappingMasterDto.class)).list();

			return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, listContainerMaster, null);

		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ANTIBIOTICS_ADDITION_CORDS_NOT_FOUND, null, null);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getMediaMasterClassById(Integer sampleId)
			throws ApplicationException {
		try {
			List<SampleTypeMediaMappingMasterDto> listSampleTypeMediaMappingMaster = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_SAMPLE_TYPE_BY_MEDIA_ID")
					.setInteger("sampleId", sampleId)
					.setResultTransformer(Transformers.aliasToBean(SampleTypeMediaMappingMasterDto.class)).list();

			return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, listSampleTypeMediaMappingMaster, null);

		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ANTIBIOTICS_ADDITION_CORDS_NOT_FOUND, null, null);
		}
	}


}
