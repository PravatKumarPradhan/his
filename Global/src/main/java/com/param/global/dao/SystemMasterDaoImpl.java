package com.param.global.dao;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.MasterOfSytemDto;
import com.param.global.dto.SystemMasterDto;
import com.param.global.model.SystemMaster;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class SystemMasterDaoImpl extends GenericDao<SystemMaster, Integer>
		implements ISystemMasterDao, ICommonConstants {

	public SystemMasterDaoImpl() {
		super(SystemMaster.class);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private DozerBeanMapper mapper;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Response getListOfSystemMaster(SystemMasterDto systemMasterDto) throws ApplicationException {
		try {
			Session statlessSession = sessionFactory.openSession();
		
			/* List<GeneralExaminationAppoMapperDto> listGeneralExaminationAppoMapperDto =
			  statlessSession.getNamedQuery("GET_LIST_SYSTEM_MASTER")
			  .setInteger("unitId", systemMasterDto.getUnitId())
			  .setInteger("organizationId",
			  systemMasterDto.getOrganizationId()).list();
			  statlessSession.close();*/
			 

			List<SystemMaster> listSystemMaster = statlessSession
					.createQuery(
							"from SystemMaster p where unit_id=:unitId AND organization_id=:organizationId AND status = 'A' and (typeId=:typeId OR typeId=3) AND (genderId=:genderId OR genderId=4)")
					.setInteger("genderId", systemMasterDto.getGenderId())
					.setInteger("unitId", systemMasterDto.getUnitId())
					.setInteger("typeId", systemMasterDto.getTypeId())
					.setInteger("organizationId", systemMasterDto.getOrganizationId()).list();

			for (int i = 0; i < listSystemMaster.size(); i++) {

				for (int j = 0; j < listSystemMaster.get(i).getSystemObervationMasterList().size(); j++) {
					listSystemMaster.get(i).getSystemObervationMasterList().get(j).setSystemMaster(null);

					/*
					 * for(int k=0; k <
					 * listSystemMaster.get(i).getSystemObervationMasterList().
					 * get(j).getSystemObervationPropertyMasterList().size();
					 * k++){
					 * listSystemMaster.get(i).getSystemObervationMasterList().
					 * get(j).getSystemObervationPropertyMasterList().get(k) }
					 */
				}

			}
			/*
			 * Iterator i = listSystemMaster.iterator(); while(i.hasNext()){
			 * SystemMaster sys = (SystemMaster) i.next(); Iterator i1 =
			 * sys.getSystemObervationMasterList().iterator();
			 * while(i1.hasNext()){
			 * 
			 * } }
			 */

			return new Response(SUCCESS, SUCCESS_CODE, null, listSystemMaster, null);
		} catch (HibernateException he) {
			he.printStackTrace();
			throw new HibernateException(he.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}

	@Override
	public Response saveSystemMaster(MasterOfSytemDto masterOfSytemDto) throws ApplicationException {
		try {
			SystemMaster systemMaster = mapper.map(masterOfSytemDto, SystemMaster.class,
					"masterOfSytemDto_to_systemMaster");
			systemMaster = save(systemMaster);
			return new Response(SUCCESS, SUCCESS_CODE, COMMON_SUCCESS, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}

	@Override
	public Response getSystemMaster(MasterOfSytemDto masterOfSytemDto) throws ApplicationException {
		try {
			List<MasterOfSytemDto> listmasterOfSytemDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_SYSTEM_LIST").setInteger("unitId", masterOfSytemDto.getUnitId())
					.setInteger("organizationId", masterOfSytemDto.getOrganizationId())
					.setResultTransformer(Transformers.aliasToBean(MasterOfSytemDto.class)).list();
			return new Response(SUCCESS, null, null, listmasterOfSytemDto, null);

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateSystemMasterStatus(MasterOfSytemDto masterOfSytemDto) throws ApplicationException {
		try {

			SystemMaster systemMaster = findById(masterOfSytemDto.getSystemId());
			systemMaster.setStatus(masterOfSytemDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			systemMaster.setUpdatedBy(masterOfSytemDto.getUpdatedBy());
			systemMaster.setUpdatedDate(
					GlobalCommonDateUtils.getDate(masterOfSytemDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(systemMaster);
			return new Response<MasterOfSytemDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<MasterOfSytemDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<MasterOfSytemDto, Integer>(ERROR, null, null, null, null);
		}
	}

	// added by dinesh jagatap on 21-04-18 Start
	@Override
	public Response getListOfSystemMasterById(MasterOfSytemDto masterOfSytemDto) throws ApplicationException {
		try {
			List<SystemMaster> listSystemMaster = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_LIST_OF_SYSTEM_MASTER_BY_ID")
					.setInteger("systemId", masterOfSytemDto.getSystemId())
					.setResultTransformer(Transformers.aliasToBean(MasterOfSytemDto.class)).list();
			return new Response(SUCCESS, null, null, listSystemMaster, null);

		} catch (Exception e) {
			e.printStackTrace();
			return new Response<MasterOfSytemDto, Integer>(ERROR, null, null, null, null);
		}
	}

	@Override
	public Response updateSystemMaster(MasterOfSytemDto masterOfSytemDto) throws ApplicationException {
		try {
			SystemMaster systemMaster = findById(masterOfSytemDto.getSystemId());
			systemMaster.setGenderId(masterOfSytemDto.getGenderId());
			systemMaster.setTypeId(masterOfSytemDto.getTypeId());
			systemMaster.setSystemCode(masterOfSytemDto.getSystemCode());
			systemMaster.setSystemName(masterOfSytemDto.getSystemName());
			systemMaster.setUpdatedBy(masterOfSytemDto.getUpdatedBy());
			systemMaster.setUpdatedDate(
					GlobalCommonDateUtils.getDate(masterOfSytemDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(systemMaster);
			return new Response<MasterOfSytemDto, Integer>(SUCCESS, null, COMMON_UPDATE, null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<MasterOfSytemDto, Integer>(ERROR, null, null, null, null);
	}

	@Override
	public Response getListOfSystemMasterByType(MasterOfSytemDto masterOfSytemDto) throws ApplicationException {
		try {
			// if (masterOfSytemDto.getTypeId() == 1 ||
			// masterOfSytemDto.getTypeId() == 3) {

			List<SystemMaster> listSystemMasterType = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_LIST_OF_SYSTEM_MASTER_BY_TYPE")
					.setInteger("typeId", masterOfSytemDto.getTypeId())
					.setInteger("unitId", masterOfSytemDto.getUnitId())
					.setInteger("organizationId", masterOfSytemDto.getOrganizationId())
					.setResultTransformer(Transformers.aliasToBean(MasterOfSytemDto.class)).list();
			return new Response(SUCCESS, null, null, listSystemMasterType, null);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<MasterOfSytemDto, Integer>(ERROR, null, null, null, null);
	}

}
