package com.param.lis.common.service;

import in.param.exception.ApplicationException;
import javax.transaction.Transactional;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.param.lis.common.dao.ICommonListDao;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.SelectedAntibioticListDto;

@Service
@SuppressWarnings({"rawtypes", "unchecked"})
public class CommonListServiceImpl implements ICommonListService, ICommonConstants, IError {
  @Autowired
  ICommonListDao iCommonListDao;

  @Autowired
  private SessionFactory sessionFactory;

  final static Logger logger = Logger.getLogger(CommonListServiceImpl.class);

  @Override
  @Transactional
  public Response getSampleStatusList(Integer orgId) throws ApplicationException {
    try {
      return iCommonListDao.getSampleStatusList(orgId);
    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getRejectionReasonList(Integer orgId) throws ApplicationException {
    try {
      return iCommonListDao.getRejectionReasonList(orgId);
    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getDeparmentList(Integer orgId) throws ApplicationException {
    try {
      return iCommonListDao.getDeparmentList(orgId);
    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getSampleTypeList(Integer orgId) throws ApplicationException {
    try {
      return iCommonListDao.getSampleTypeList(orgId);
    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getUnitList(Integer orgId) throws ApplicationException {
    try {
      return iCommonListDao.getUnitList(orgId);
    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getContainerList(Integer orgId) throws ApplicationException {
    try {
      return iCommonListDao.getContainerList(orgId);
    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getReportTypeList(Integer orgId) throws ApplicationException {
    try {
      return iCommonListDao.getReportTypeList(orgId);
    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getTechniqueList(Integer orgId) throws ApplicationException {
    try {
      return iCommonListDao.getTechniqueList(orgId);
    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getPrerequisitesList(Integer orgId) throws ApplicationException {
    try {
      return iCommonListDao.getPrerequisitesList(orgId);
    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getHeaderList(Integer orgId, Integer orgUnitId) throws ApplicationException {
    try {
      return iCommonListDao.getHeaderList(orgId, orgUnitId);
    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getParameterList(Integer orgId, Integer orgUnitId) throws ApplicationException {
    try {
      return iCommonListDao.getParameterList(orgId, orgUnitId);
    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getAgeGroupList(Integer orgId) throws ApplicationException {
    try {
      return iCommonListDao.getAgeGroupList(orgId);
    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getGenderList(Integer orgId) throws ApplicationException {
    try {
      return iCommonListDao.getGenderList(orgId);
    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getDayList(Integer orgId) throws ApplicationException {
    try {
      return iCommonListDao.getDayList(orgId);
    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getTrunAroundTime(Integer orgId) throws ApplicationException {
    try {
      return iCommonListDao.getTrunAroundTime(orgId);
    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getAutoCompleteServices(Integer orgId, Integer orgUnitId, Integer deptId,
      String serviceName) throws ApplicationException {
    try {
      return iCommonListDao.getAutoCompleteServices(orgId, orgUnitId, deptId, serviceName);
    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getMediaList(Integer orgId) throws ApplicationException {
    try {
      return iCommonListDao.getMediaList(orgId);
    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getColonyList(Integer orgId) throws ApplicationException {
    try {
      return iCommonListDao.getColonyList(orgId);
    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getAntibioticGroupList(Integer orgId) throws ApplicationException {
    try {
      return iCommonListDao.getAntibioticGroupList(orgId);
    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getOrganismGroupList(Integer orgId) throws ApplicationException {
    try {
      return iCommonListDao.getOrganismGroupList(orgId);
    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getMediaListBySampleType(Integer sampleId, Integer orgId)
      throws ApplicationException {
    try {
      return iCommonListDao.getMediaListBySampleType(sampleId, orgId);
    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getIncubationPeriodList(Integer orgId) throws ApplicationException {
    try {
      return iCommonListDao.getIncubationPeriodList(orgId);
    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getIncubationTimeList(Integer orgId) throws ApplicationException {
    try {
      return iCommonListDao.getIncubationTimeList(orgId);
    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getStainingListByOrgId(Integer orgId) throws ApplicationException {
    try {
      return iCommonListDao.getStainingListByOrgId(orgId);
    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getMicroOrganismByGroup(Integer organismGroupId, Integer orgId)
      throws ApplicationException {
    try {
      return iCommonListDao.getMicroOrganismByGroup(organismGroupId, orgId);
    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getOrganismList(Integer orgId) throws ApplicationException {
    try {
      return iCommonListDao.getOrganismList(orgId);
    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getAntibioticGroupByGroup(SelectedAntibioticListDto selectedAntibioticListDto)
      throws ApplicationException {
    try {
      return iCommonListDao.getAntibioticGroupByGroup(selectedAntibioticListDto);
    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getAutoCompleteServicesForAntibiotic(Integer orgId, String antibioticName)
      throws ApplicationException {
    try {
      return iCommonListDao.getAutoCompleteServicesForAntibiotic(orgId, antibioticName);
    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getMicrolabPriorityList(Integer orgId) throws ApplicationException {
    try {
      return iCommonListDao.getMicrolabPriorityList(orgId);
    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getAntibioticsByOrganismIdList(Integer orgId, Integer organismId)
      throws ApplicationException {
    try {
      return iCommonListDao.getAntibioticsByOrganismIdList(orgId, organismId);
    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getSpecimanTypeList(Integer orgId) throws ApplicationException {
    try {
      return iCommonListDao.getSpecimanTypeList(orgId);
    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getAccountPayableList(Integer orgId) throws ApplicationException {
    try {
      return iCommonListDao.getAccountPayableList(orgId);
    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getRackList(Integer orgId) throws ApplicationException {
    try {
      return iCommonListDao.getRackList(orgId);
    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getRackListByShelfId(Integer shelfId, Integer orgId) throws ApplicationException {
    try {
      return iCommonListDao.getRackListByShelfId(shelfId, orgId);
    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getSampleStatus(Integer orgId) throws ApplicationException {
    try {
      return iCommonListDao.getSampleStatus(orgId);
    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getTestParameters(Integer testId, Integer orgId, Integer orgUnitId)
      throws ApplicationException {
    try {
      return iCommonListDao.getTestParameters(testId,orgId,orgUnitId);
    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getRefRangeTypeList(Integer orgId, Integer orgUnitId)
      throws ApplicationException {
    try {
      return iCommonListDao.getRefRangeTypeList(orgId,orgUnitId);
    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getTextualReferenceRanges(Integer orgId, Integer orgUnitId)
      throws ApplicationException {
    try {
      return iCommonListDao.getTextualReferenceRanges(orgId,orgUnitId);
    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getMicrobiologyTestList(Integer orgId, Integer orgUnitId) {
    try {
      return iCommonListDao.getMicrobiologyTestList(orgId,orgUnitId);
    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getMicrobiologyStainingList(Integer orgId) throws ApplicationException {
    try {
      return iCommonListDao.getMicrobiologyStainingList(orgId);
    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

}
