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
import com.param.entity.lis.global.AntibioticGroupAdditionMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.AntibioticGroupAdditionMasterDto;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class AntibioticGroupAddtionMasterDaoImpl extends GenericDao<AntibioticGroupAdditionMaster, Integer>
		implements IAntibioticGroupAddtionMasterDao, ICommonConstants, IError {

	public AntibioticGroupAddtionMasterDaoImpl() {
		super(AntibioticGroupAdditionMaster.class);
	}

	@Autowired
	private DozerBeanMapper mapper;

	@Override
	public Response addAntibioticGroupAddtionMaster(AntibioticGroupAdditionMasterDto antibioticAdditionMasterDto)
			throws ApplicationException {
		try {
			AntibioticGroupAdditionMaster antibioticGroupAdditionMaster = new AntibioticGroupAdditionMaster();
			antibioticGroupAdditionMaster.setCreatedBy(antibioticAdditionMasterDto.getCreatedBy());
			antibioticGroupAdditionMaster.setCreatedDate(antibioticAdditionMasterDto.getCreatedDate());
			antibioticGroupAdditionMaster.setStatus(antibioticAdditionMasterDto.getStatus());
			antibioticGroupAdditionMaster.setOrgId(antibioticAdditionMasterDto.getOrgId());
			antibioticGroupAdditionMaster.setUpdatedBy(antibioticAdditionMasterDto.getUpdatedBy());
			antibioticGroupAdditionMaster.setUpdatedDate(antibioticAdditionMasterDto.getUpdatedDate());
			antibioticGroupAdditionMaster.setAntiboiticGroupId(antibioticAdditionMasterDto.getAntiboiticGroupId());
			antibioticGroupAdditionMaster.setAntiboiticId(antibioticAdditionMasterDto.getAntiboiticId());
			antibioticGroupAdditionMaster.setIsDeleted('N');
			antibioticGroupAdditionMaster = save(antibioticGroupAdditionMaster);

			return new Response<>(SUCCESS, SUCCESS_200, ANTIBIOTICS_ADDITION_ADD_SUCC, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201, ANTIBIOTICS_ADDITION_ADD_FAIL, null, null);
	}

	@Override
	public Response listAntibioticGroupAddtionMaster(Integer orgId, Integer offset, Integer recordPerPage)
			throws ApplicationException {
		try {
			List<AntibioticGroupAdditionMasterDto> listAntibioticGroupAddtionMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PAGINATED_ANTIBIOTIC_GROUP_MAPPER_MASTER_LIST").setInteger("orgId", orgId)
					.setFirstResult(offset != null ? offset : 0)
					.setMaxResults(recordPerPage != null ? recordPerPage : 10)
					.setResultTransformer(Transformers.aliasToBean(AntibioticGroupAdditionMasterDto.class)).list();
			return new Response(SUCCESS, null, null, listAntibioticGroupAddtionMasterDto, null);

		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response getToTalAntibioticGroupAddtionMaster(Integer orgId) throws ApplicationException {
		try {
			BigInteger antibioticAddtionClassMasterCount = (BigInteger) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_TOTAL_ANTIBIOTIC_GROUP_MAPPER_RECORD").setInteger("orgId", orgId)
					.uniqueResult();

			if (antibioticAddtionClassMasterCount.compareTo(BigInteger.ZERO) == 1) {
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, antibioticAddtionClassMasterCount);
			} else {
				return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, BigInteger.ZERO);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response activateInactivateAntibioticGroupAddtionMaster(Integer orgId, Integer antiboiticGroupId,
			Character status) throws ApplicationException {

		try {
			if (antiboiticGroupId != 0) {

				Integer result = sessionFactory.getCurrentSession()
						.getNamedQuery("ACTIVE_INACTIVE_ANTIBIOTIC_GROUP_MAPPER").setInteger("orgId", orgId)
						.setCharacter("status", status).setInteger("antiboiticGroupId", antiboiticGroupId)
						.executeUpdate();
				if (result > 0) {
					if (status.equals(ACTIVE)) {
						return new Response(SUCCESS, SUCCESS_200, ANTIBIOTICS_ADDITION_ACTIVATE_SUCC, null, null);
					} else if (status.equals(INACTIVE)) {
						return new Response(SUCCESS, SUCCESS_200, ANTIBIOTICS_ADDITION_INACTIVATE_SUCC, null, null);
					}
				} else {
					return new Response(ERROR, ERR_500, ANTIBIOTICS_ADDITION_CORDS_NOT_FOUND, null, null);
				}

			} else {
				return new Response(ERROR, ERR_500, ANTIBIOTICS_ADDITION_CORDS_NOT_FOUND, null, null);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ANTIBIOTICS_ADDITION_CORDS_NOT_FOUND, null, null);
		}
		return new Response(ERROR, ERR_500, ANTIBIOTICS_ADDITION_CORDS_NOT_FOUND, null, null);
	}

	@Override
	public Response updateAntibioticGroupAddtionMaster(
			List<AntibioticGroupAdditionMasterDto> listAntibioticGroupAdditionMasterDto) throws ApplicationException {
		try {

			Integer result = sessionFactory.getCurrentSession().getNamedQuery("UPDATE_ANTIBIOTIC_GROUP_MAPPER")
					.setCharacter("isDeleted", 'Y')
					.setInteger("orgId", listAntibioticGroupAdditionMasterDto.get(0).getOrgId())
					.setInteger("antiboiticGroupId", listAntibioticGroupAdditionMasterDto.get(0).getAntiboiticGroupId())
					.executeUpdate();
			if (result > 0) {
				for (Iterator iterator = listAntibioticGroupAdditionMasterDto.iterator(); iterator.hasNext();) {
					AntibioticGroupAdditionMasterDto listAntibioticAdditionMaster = (AntibioticGroupAdditionMasterDto) iterator
							.next();
					AntibioticGroupAdditionMaster antibioticAdditionMaster = mapper.map(listAntibioticAdditionMaster,
							AntibioticGroupAdditionMaster.class, "AntibioticGroupAdditionMasterDtoTOAntibioticGroupAdditionMaster");
					antibioticAdditionMaster.setIsDeleted('N');
					antibioticAdditionMaster.setCreatedBy(antibioticAdditionMaster.getUpdatedBy());
					antibioticAdditionMaster.setCreatedDate(antibioticAdditionMaster.getUpdatedDate());
					save(antibioticAdditionMaster);
				}
				return new Response(SUCCESS, SUCCESS_200, ANTIBIOTIC_UPDATE_SUCC, null, null);
			} else {
				return new Response(ERROR, ERR_500, ANTIBIOTICS_ADDITION_UPDATE_FAIL, null, null);
			}
		} catch (

		Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ANTIBIOTICS_ADDITION_UPDATE_FAIL, null, null);
		}

	}

	@Override
	public Response checkAntibioticGroupAvaiable(AntibioticGroupAdditionMasterDto antibioticAdditionMasterDto)
			throws ApplicationException {
		try {
			List<AntibioticGroupAdditionMasterDto> listAgeGroupMasteDtosessionFactory = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ANTIBIOTIC_GROUP_MAPPER_TYPE_BY_ANTIBIOTIC_CODE")
					.setInteger("orgId", antibioticAdditionMasterDto.getOrgId())
					.setInteger("antiboiticGroupId", antibioticAdditionMasterDto.getAntiboiticGroupId())
					.setResultTransformer(Transformers.aliasToBean(AntibioticGroupAdditionMasterDto.class)).list();
			return new Response(SUCCESS, null, null, listAgeGroupMasteDtosessionFactory, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response getAntibioticGroupAddtionMasterById(Integer orgId, Integer antiboiticGroupId)
			throws ApplicationException {
		try {
			List<AntibioticGroupAdditionMasterDto> listContainerMaster = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ANTIBIOTIC_GROUP_MAPPER_BY_ANTIBIOTIC_ID").setInteger("orgId", orgId)
					.setInteger("antiboiticGroupId", antiboiticGroupId)
					.setResultTransformer(Transformers.aliasToBean(AntibioticGroupAdditionMasterDto.class)).list();

			return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, listContainerMaster, null);

		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ANTIBIOTICS_ADDITION_CORDS_NOT_FOUND, null, null);
		}
	}

	@Override
	public Response getAntibioticGroupClassById(Integer antiboiticGroupId) throws ApplicationException {
		try {
			List<AntibioticGroupAdditionMasterDto> listAntibioticAdditionMaster = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ANTIBIOTIC_BY_ANTIBIOTIC_GROUP_ID")
					.setInteger("antiboiticGroupId", antiboiticGroupId)
					.setResultTransformer(Transformers.aliasToBean(AntibioticGroupAdditionMasterDto.class)).list();

			return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, listAntibioticAdditionMaster, null);

		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ANTIBIOTICS_ADDITION_CORDS_NOT_FOUND, null, null);
		}
	}

}
