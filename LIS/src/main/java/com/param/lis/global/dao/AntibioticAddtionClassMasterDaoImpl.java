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
import com.param.entity.lis.global.AntibioticAdditionMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.AntibioticAdditionMasterDto;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class AntibioticAddtionClassMasterDaoImpl extends GenericDao<AntibioticAdditionMaster, Integer>
		implements IAntibioticAddtionClassMasterDao, ICommonConstants, IError {

	public AntibioticAddtionClassMasterDaoImpl() {
		super(AntibioticAdditionMaster.class);
	}

	@Autowired
	private DozerBeanMapper mapper;

	@Override
	public Response addAntibioticAddtionClassMaster(AntibioticAdditionMasterDto antibioticAdditionMasterDto)
			throws ApplicationException {
		try {
			AntibioticAdditionMaster antibioticAdditionMaster = new AntibioticAdditionMaster();
			antibioticAdditionMaster.setCreatedBy(antibioticAdditionMasterDto.getCreatedBy());
			antibioticAdditionMaster.setCreatedDate(antibioticAdditionMasterDto.getCreatedDate());
			antibioticAdditionMaster.setStatus(antibioticAdditionMasterDto.getStatus());
			antibioticAdditionMaster.setOrgId(antibioticAdditionMasterDto.getOrgId());
			antibioticAdditionMaster.setUpdatedBy(antibioticAdditionMasterDto.getUpdatedBy());
			antibioticAdditionMaster.setUpdatedDate(antibioticAdditionMasterDto.getUpdatedDate());
			antibioticAdditionMaster.setAntiboiticClassId(antibioticAdditionMasterDto.getAntiboiticClassId());
			antibioticAdditionMaster.setAntiboiticId(antibioticAdditionMasterDto.getAntiboiticId());
			antibioticAdditionMaster = save(antibioticAdditionMaster);

			return new Response<>(SUCCESS, SUCCESS_200, ANTIBIOTICS_ADDITION_ADD_SUCC, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201, ANTIBIOTICS_ADDITION_ADD_FAIL, null, null);
	}

	@Override
	public Response listAntibioticAddtionClassMaster(Integer orgId, Integer offset, Integer recordPerPage)
			throws ApplicationException {
		try {
			List<AntibioticAdditionMasterDto> listAntibioticAddtionClassMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PAGINATED_ANTIBIOTIC_CLASS_MAPPER_MASTER_LIST").setInteger("orgId", orgId)
					.setFirstResult(offset != null ? offset : 0)
					.setMaxResults(recordPerPage != null ? recordPerPage : 10)
					.setResultTransformer(Transformers.aliasToBean(AntibioticAdditionMasterDto.class)).list();
			return new Response(SUCCESS, null, null, listAntibioticAddtionClassMasterDto, null);

		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response getToTalAntibioticAddtionClassMaster(Integer orgId) throws ApplicationException {
		try {
			BigInteger antibioticAddtionClassMasterCount = (BigInteger) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_TOTAL_ANTIBIOTIC_CLASS_MAPPER_RECORD").setInteger("orgId", orgId)
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
	public Response activateInactivateAntibioticAddtionClassMaster(Integer orgId, Integer antiboiticClassId,
			Character status) throws ApplicationException {

		try {
			if (antiboiticClassId != 0) {

				Integer result = sessionFactory.getCurrentSession()
						.getNamedQuery("ACTIVE_INACTIVE_ANTIBIOTIC_CLASS_MAPPER").setInteger("orgId", orgId)
						.setCharacter("status", status).setInteger("antiboiticClassId", antiboiticClassId)
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
	public Response updateAntibioticAddtionClassMaster(
			List<AntibioticAdditionMasterDto> listAntibioticAdditionMasterDto) throws ApplicationException {
		try {

			Integer result = sessionFactory.getCurrentSession().getNamedQuery("UPDATE_ANTIBIOTIC_CLASS_MAPPER")
					.setCharacter("isDeleted", 'Y')
					.setInteger("orgId", listAntibioticAdditionMasterDto.get(0).getOrgId())
					.setInteger("antiboiticClassId", listAntibioticAdditionMasterDto.get(0).getAntiboiticClassId())
					.executeUpdate();
			if (result > 0) {
				for (Iterator iterator = listAntibioticAdditionMasterDto.iterator(); iterator.hasNext();) {
					AntibioticAdditionMasterDto listAntibioticAdditionMaster = (AntibioticAdditionMasterDto) iterator
							.next();
					AntibioticAdditionMaster antibioticAdditionMaster = mapper.map(listAntibioticAdditionMaster,
							AntibioticAdditionMaster.class, "AntibioticAdditionMasterDtoTOAntibioticAdditionMaster");
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
	public Response checkAntibioticAddtionClassAvaiable(AntibioticAdditionMasterDto antibioticAdditionMasterDto)
			throws ApplicationException {
		try {
			List<AntibioticAdditionMasterDto> listAgeGroupMasteDtosessionFactory = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ANTIBIOTIC_CLASS_MAPPER_TYPE_BY_ANTIBIOTIC_CODE")
					.setInteger("orgId", antibioticAdditionMasterDto.getOrgId())
					.setInteger("antiboiticClassId", antibioticAdditionMasterDto.getAntiboiticClassId())
					.setResultTransformer(Transformers.aliasToBean(AntibioticAdditionMasterDto.class)).list();
			return new Response(SUCCESS, null, null, listAgeGroupMasteDtosessionFactory, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response getAntibioticAddtionClassMasterById(Integer orgId, Integer antiboiticClassId)
			throws ApplicationException {
		try {
			List<AntibioticAdditionMasterDto> listContainerMaster = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ANTIBIOTIC_CLASS_MAPPER_BY_ANTIBIOTIC_ID").setInteger("orgId", orgId)
					.setInteger("antiboiticClassId", antiboiticClassId)
					.setResultTransformer(Transformers.aliasToBean(AntibioticAdditionMasterDto.class)).list();

			return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, listContainerMaster, null);

		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ANTIBIOTICS_ADDITION_CORDS_NOT_FOUND, null, null);
		}
	}

	@Override
	public Response getAntibioticMasterClassById(Integer antiboiticClassId) throws ApplicationException {
		try {
			List<AntibioticAdditionMasterDto> listAntibioticAdditionMaster = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ANTIBIOTIC_BY_ANTIBIOTIC_CLASS_ID")
					.setInteger("antiboiticClassId", antiboiticClassId)
					.setResultTransformer(Transformers.aliasToBean(AntibioticAdditionMasterDto.class)).list();

			return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, listAntibioticAdditionMaster, null);

		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ANTIBIOTICS_ADDITION_CORDS_NOT_FOUND, null, null);
		}
	}

}
