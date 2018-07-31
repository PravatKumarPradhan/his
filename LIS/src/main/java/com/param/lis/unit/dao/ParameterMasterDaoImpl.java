package com.param.lis.unit.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.param.entity.lis.unit.HelpValueMaster;
import com.param.entity.lis.unit.ParamMultiTextualRangeMaster;
import com.param.entity.lis.unit.ParamRefrengMaster;
import com.param.entity.lis.unit.ParamTextualRanageMaster;
import com.param.entity.lis.unit.ParameterMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.ITransactionConstants;
import com.param.lis.global.common.Response;
import com.param.lis.unit.dto.HelpValueMasterDto;
import com.param.lis.unit.dto.ParamMultiTextualRangeMasterDto;
import com.param.lis.unit.dto.ParamRefrengMasterDto;
import com.param.lis.unit.dto.ParamTextualRanageMasterDto;
import com.param.lis.unit.dto.ParameterMasterDto;
import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({"rawtypes", "unchecked"})
public class ParameterMasterDaoImpl extends GenericDao<ParameterMaster, Integer>
    implements IParameterMasterDao, ICommonConstants, IError, ITransactionConstants {

  public ParameterMasterDaoImpl() {
    super(ParameterMaster.class);
  }

  /*
   * @Autowired private SessionFactory sessionFactory;
   */

  @Autowired
  private DozerBeanMapper mapper;

  final static Logger logger = Logger.getLogger(ParameterMasterDaoImpl.class);

  @Override
  public Response addParameter(ParameterMasterDto parameterMasterDto) throws ApplicationException {
    try {

      ParameterMaster parameterMaster = mapper.map(parameterMasterDto, ParameterMaster.class,
          "ParameterMasterDtoTOParameterMaster");
      ParameterMaster parameterMst = save(parameterMaster);
      if (parameterMst.getListParamRefrengMaster() != null
          && parameterMst.getListParamRefrengMaster().size() > 0) {
        for (Iterator iterator = parameterMst.getListParamRefrengMaster().iterator(); iterator
            .hasNext();) {
          ParamRefrengMaster paramRefrengMaster = (ParamRefrengMaster) iterator.next();
          paramRefrengMaster.setParameterId(parameterMst.getParameterId());
          paramRefrengMaster.setOrgId(parameterMst.getOrgId());
          paramRefrengMaster.setOrgUnitId(parameterMst.getOrgUnitId());
          paramRefrengMaster.setRefrengStatus(parameterMst.getStatus());
          paramRefrengMaster.setCreatedDate(new Date().getTime());
          sessionFactory.getCurrentSession().save(paramRefrengMaster);
        }
      }
      if (parameterMst.getListHelpValueMaster() != null
          && parameterMst.getListHelpValueMaster().size() > 0) {
        for (Iterator iterator = parameterMst.getListHelpValueMaster().iterator(); iterator
            .hasNext();) {
          HelpValueMaster helpValueMaster = (HelpValueMaster) iterator.next();
          helpValueMaster.setParameterId(parameterMst.getParameterId());
          helpValueMaster.setOrgId(parameterMst.getOrgId());
          helpValueMaster.setOrgUnitId(parameterMst.getOrgUnitId());
          helpValueMaster.setStatus(parameterMst.getStatus());
          helpValueMaster.setCreatedDate(new Date().getTime());
          sessionFactory.getCurrentSession().save(helpValueMaster);
        }
      }
      if (parameterMst.getListParamTextualRanageMaster() != null
          && parameterMst.getListParamTextualRanageMaster().size() > 0) {
        for (Iterator iterator = parameterMst.getListParamTextualRanageMaster().iterator(); iterator
            .hasNext();) {
          ParamTextualRanageMaster paramTextualRanageMaster =
              (ParamTextualRanageMaster) iterator.next();
          paramTextualRanageMaster.setParameterId(parameterMst.getParameterId());
          paramTextualRanageMaster.setOrgId(parameterMst.getOrgId());
          paramTextualRanageMaster.setOrgUnitId(parameterMst.getOrgUnitId());
          paramTextualRanageMaster.setStatus(parameterMst.getStatus());
          paramTextualRanageMaster.setCreatedDate(new Date().getTime());
          sessionFactory.getCurrentSession().save(paramTextualRanageMaster);
        }
      }
      if (parameterMst.getListParamMultiTextualRangeMaster() != null
          && parameterMst.getListParamMultiTextualRangeMaster().size() > 0) {
        for (Iterator iterator =
            parameterMst.getListParamMultiTextualRangeMaster().iterator(); iterator.hasNext();) {
          ParamMultiTextualRangeMaster paramMultiTextualRangeMaster =
              (ParamMultiTextualRangeMaster) iterator.next();
          paramMultiTextualRangeMaster.setParameterId(parameterMst.getParameterId());
          paramMultiTextualRangeMaster.setOrgId(parameterMst.getOrgId());
          paramMultiTextualRangeMaster.setOrgUnitId(parameterMst.getOrgUnitId());
          paramMultiTextualRangeMaster.setStatus(parameterMst.getStatus());
          paramMultiTextualRangeMaster.setCreatedDate(new Date().getTime());
          sessionFactory.getCurrentSession().save(paramMultiTextualRangeMaster);
        }
      }
      return new Response(SUCCESS, SUCCESS_200, PARAMATER_ADD_SUCC, null, parameterMaster);

    } catch (HibernateException exception) {
      logger.error("Exection", exception);
    } catch (Exception e) {
      logger.error("Exection", e);
    }
    return new Response(ERROR, ERR_500, PARAMATER_ADD_FAIL, null, null);
  }

  @Override
  public Response getParameterById(Integer orgId, Integer orgUnitId, Integer parameterId)
      throws ApplicationException {
    try {

      ParameterMasterDto parameterMasterDto = (ParameterMasterDto) sessionFactory
          .getCurrentSession().getNamedQuery("GET_PARAMETER_BY_PARAMETER_ID")
          .setInteger("orgUnitId", orgUnitId).setInteger("orgId", orgId)
          .setInteger("parameterId", parameterId)
          .setResultTransformer(Transformers.aliasToBean(ParameterMasterDto.class)).uniqueResult();

      List<HelpValueMasterDto> listHelpValueMasterDto = sessionFactory.getCurrentSession()
          .getNamedQuery("GET_HELP_VALUE_BY_PARAMETER_ID").setInteger("orgId", orgId)
          .setInteger("orgUnitId", orgUnitId).setInteger("parameterId", parameterId)
          .setResultTransformer(Transformers.aliasToBean(HelpValueMasterDto.class)).list();

      if (parameterMasterDto.getRefrangeTypeId().intValue() == REFERENCE_VALUE.intValue()) {
        List<ParamRefrengMasterDto> listParamRefrengMasterDto = sessionFactory.getCurrentSession()
            .getNamedQuery("GET_PARAM_REF_RANGE_VALUE_BY_PARAMETER").setInteger("orgId", orgId)
            .setInteger("orgUnitId", orgUnitId).setInteger("parameterId", parameterId)
            .setResultTransformer(Transformers.aliasToBean(ParamRefrengMasterDto.class)).list();
        parameterMasterDto.setListParamRefrengMaster(listParamRefrengMasterDto);
      }
      if (parameterMasterDto.getRefrangeTypeId().intValue() == TEXTUAL_RANGE.intValue()) {
        List<ParamTextualRanageMasterDto> listParamTextualRanageMasterDto =
            sessionFactory.getCurrentSession().getNamedQuery("GET_TEXTUAL_VALUE_BY_PARAMETER_ID")
                .setInteger("orgId", orgId).setInteger("orgUnitId", orgUnitId)
                .setInteger("parameterId", parameterId)
                .setResultTransformer(Transformers.aliasToBean(ParamTextualRanageMasterDto.class))
                .list();
        parameterMasterDto.setListParamTextualRanageMaster(listParamTextualRanageMasterDto);
      }
      if (parameterMasterDto.getRefrangeTypeId().intValue() == MULTIVALUED_RANGE.intValue()) {
        List<ParamMultiTextualRangeMasterDto> listParamMultiTextualRangeMasterDto = sessionFactory
            .getCurrentSession().getNamedQuery("GET_MULTI_TEXTUAL_VALUE_BY_PARAMETER_ID")
            .setInteger("orgId", orgId).setInteger("orgUnitId", orgUnitId)
            .setInteger("parameterId", parameterId)
            .setResultTransformer(Transformers.aliasToBean(ParamMultiTextualRangeMasterDto.class))
            .list();
        parameterMasterDto.setListParamMultiTextualRangeMaster(listParamMultiTextualRangeMasterDto);
      }

      parameterMasterDto.setListHelpValueMaster(listHelpValueMasterDto);
      return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, parameterMasterDto);


    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, PARAMATER_NOT_FOUND, null, null);
    }

  }

  @Override
  public Response updateParameter(ParameterMasterDto parameterMasterDto)
      throws ApplicationException {

    ParameterMaster parameterMaster = mapper.map(parameterMasterDto, ParameterMaster.class,
        "ParameterMasterDtoTOParameterMaster");
    ParameterMaster parameterMst = update(parameterMaster);
    if (parameterMst != null) {
      if (parameterMst.getListParamRefrengMaster() != null
          && parameterMst.getListParamRefrengMaster().size() > 0) {
        for (Iterator iterator = parameterMst.getListParamRefrengMaster().iterator(); iterator
            .hasNext();) {
          ParamRefrengMaster paramRefrengMaster = (ParamRefrengMaster) iterator.next();
          if (paramRefrengMaster.getRefrengId() == 0) {
            paramRefrengMaster.setCreatedDate(new Date().getTime());
            sessionFactory.getCurrentSession().save(paramRefrengMaster);
          } else {
            paramRefrengMaster.setParameterId(parameterMaster.getParameterId());
            paramRefrengMaster.setOrgId(parameterMaster.getOrgId());
            paramRefrengMaster.setOrgUnitId(parameterMaster.getOrgUnitId());
            paramRefrengMaster.setRefrengStatus(parameterMaster.getStatus());
            paramRefrengMaster.setUpdatedDate(new Date().getTime());
            sessionFactory.getCurrentSession().update(paramRefrengMaster);
          }

        }
      }
      if (parameterMst.getListHelpValueMaster() != null
          && parameterMst.getListHelpValueMaster().size() > 0) {
        for (Iterator iterator = parameterMst.getListHelpValueMaster().iterator(); iterator
            .hasNext();) {
          HelpValueMaster helpValueMaster = (HelpValueMaster) iterator.next();
          if (helpValueMaster.getHelpValueId() == 0) {
            helpValueMaster.setUpdatedDate(new Date().getTime());
            sessionFactory.getCurrentSession().save(helpValueMaster);
          } else {
            helpValueMaster.setParameterId(parameterMaster.getParameterId());
            helpValueMaster.setOrgId(parameterMaster.getOrgId());
            helpValueMaster.setOrgUnitId(parameterMaster.getOrgUnitId());
            helpValueMaster.setStatus(parameterMaster.getStatus());
            helpValueMaster.setUpdatedDate(new Date().getTime());
            sessionFactory.getCurrentSession().update(helpValueMaster);
          }
        }
      }

      if (parameterMst.getListParamTextualRanageMaster() != null
          && parameterMst.getListParamTextualRanageMaster().size() > 0) {
        for (Iterator iterator = parameterMst.getListParamTextualRanageMaster().iterator(); iterator
            .hasNext();) {
          ParamTextualRanageMaster paramTextualRanageMaster =
              (ParamTextualRanageMaster) iterator.next();
          if (paramTextualRanageMaster.getParamTextualRangeId() == 0) {
            paramTextualRanageMaster.setUpdatedDate(new Date().getTime());
            sessionFactory.getCurrentSession().save(paramTextualRanageMaster);
          } else {
            paramTextualRanageMaster.setParameterId(parameterMaster.getParameterId());
            paramTextualRanageMaster.setOrgId(parameterMaster.getOrgId());
            paramTextualRanageMaster.setOrgUnitId(parameterMaster.getOrgUnitId());
            paramTextualRanageMaster.setStatus(parameterMaster.getStatus());
            paramTextualRanageMaster.setUpdatedDate(new Date().getTime());
            sessionFactory.getCurrentSession().update(paramTextualRanageMaster);
          }
        }
      }
      if (parameterMst.getListParamMultiTextualRangeMaster() != null
          && parameterMst.getListParamMultiTextualRangeMaster().size() > 0) {
        for (Iterator iterator =
            parameterMst.getListParamMultiTextualRangeMaster().iterator(); iterator.hasNext();) {
          ParamMultiTextualRangeMaster paramMultiTextualRangeMaster =
              (ParamMultiTextualRangeMaster) iterator.next();
          if (paramMultiTextualRangeMaster.getParamMultiTextualRangeId() == 0) {
            paramMultiTextualRangeMaster.setUpdatedDate(new Date().getTime());
            sessionFactory.getCurrentSession().save(paramMultiTextualRangeMaster);
          } else {
            paramMultiTextualRangeMaster.setParameterId(parameterMaster.getParameterId());
            paramMultiTextualRangeMaster.setOrgId(parameterMaster.getOrgId());
            paramMultiTextualRangeMaster.setOrgUnitId(parameterMaster.getOrgUnitId());
            paramMultiTextualRangeMaster.setStatus(parameterMaster.getStatus());
            paramMultiTextualRangeMaster.setUpdatedDate(new Date().getTime());
            sessionFactory.getCurrentSession().update(paramMultiTextualRangeMaster);
          }
        }
      }
      return new Response(SUCCESS, SUCCESS_200, PARAMATER_UPDATE_SUCC, null, null);
    } else {
      return new Response(SUCCESS, SUCCESS_200, PARAMATER_UPDATE_FAIL, null, null);
    }
  }

  @Override
  public Response activeInactiveParameter(Integer orgId, Integer orgUnitId,
      Character parameterStatus) throws ApplicationException {
    try {
      ParameterMaster parameterMaster = findById(orgUnitId);
      Integer result = 0;
      if (parameterMaster.getParameterId() != 0) {
        parameterMaster.setStatus(parameterStatus);
        ParameterMaster departmentMst = update(parameterMaster);
        if (departmentMst.getRefrangeTypeId() == REFERENCE_VALUE) {
          result = sessionFactory.getCurrentSession().getNamedQuery("UPDATE_REF_RANGE_MASTER")
              .setCharacter("status", parameterStatus)
              .setInteger("parameterId", parameterMaster.getParameterId()).executeUpdate();
        } else if (departmentMst.getRefrangeTypeId() == TEXTUAL_RANGE) {
          result = sessionFactory.getCurrentSession().getNamedQuery("UPDATE_TEXTUAL_STATUS")
              .setCharacter("status", parameterStatus)
              .setInteger("parameterId", parameterMaster.getParameterId()).executeUpdate();
        } else if (departmentMst.getRefrangeTypeId() == MULTIVALUED_RANGE) {
          result =
              sessionFactory.getCurrentSession().getNamedQuery("UPDATE_MULTIVALUED_TEXTUAL_STATUS")
                  .setCharacter("status", parameterStatus)
                  .setInteger("parameterId", parameterMaster.getParameterId()).executeUpdate();
        }
        if (result > 0) {
          if (departmentMst.getStatus() == ACTIVE) {
            return new Response(SUCCESS, SUCCESS_200, PARAMATER_ACTIVATE_SUCC, null, null);
          } else if (departmentMst.getStatus() == INACTIVE) {
            return new Response(SUCCESS, SUCCESS_200, PARAMATER_INACTIVATE_SUCC, null, null);
          } else {
            if (parameterStatus == ACTIVE) {
              return new Response(SUCCESS, SUCCESS_200, PARAMATER_ACTIVATE_FAIL, null, null);
            } else {
              return new Response(SUCCESS, SUCCESS_200, PARAMATER_INACTIVATE_FAIL, null, null);
            }
          }
        } else {
          if (parameterStatus == ACTIVE) {
            return new Response(SUCCESS, SUCCESS_200, PARAMATER_ACTIVATE_FAIL, null, null);
          } else {
            return new Response(SUCCESS, SUCCESS_200, PARAMATER_INACTIVATE_FAIL, null, null);
          }
        }

      } else {
        return new Response(ERROR, ERROR, PARAMATER_NOT_FOUND, null, null);
      }

    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, PARAMATER_NOT_FOUND, null, null);
    }
  }

  @Override
  public Response listParameterMaster(Integer orgId, Integer orgUnitId, Integer offset,
      Integer recordPerPage) throws ApplicationException {
    try {

      List<ParameterMasterDto> listParameterMaster = sessionFactory.getCurrentSession()
          .getNamedQuery("GET_PAGINATED_PARAMETER_MASTER_LIST").setInteger("orgId", orgId)
          .setInteger("orgUnitId", orgUnitId).setFirstResult(offset != null ? offset : 0)
          .setMaxResults(recordPerPage != null ? recordPerPage : 10)
          .setResultTransformer(Transformers.aliasToBean(ParameterMasterDto.class)).list();

      if (listParameterMaster.isEmpty()) {
        return new Response(ERROR, ERR_500, PARAMATER_RECORDS_NOT_FOUND, null, null);
      } else {
        return new Response(SUCCESS, SUCCESS_200, null, listParameterMaster, null);
      }

    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  public Response getTotalParameterMasterRecord(Integer orgId, Integer orgUnitId)
      throws ApplicationException {
    try {
      BigInteger parameterMasterTotalRecordCount = (BigInteger) sessionFactory.getCurrentSession()
          .getNamedQuery("GET_TOTAL_PARAMETER_RECORD").setInteger("orgId", orgId)
          .setInteger("orgUnitId", orgUnitId).uniqueResult();
      if (parameterMasterTotalRecordCount.compareTo(BigInteger.ZERO) == 1) {
        return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null,
            parameterMasterTotalRecordCount);
      } else {
        return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, BigInteger.ZERO);
      }

    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  public Response getHelpValueByParameter(Integer orgId, Integer orgUnitId, Integer parameterId)
      throws ApplicationException {
    try {


      List<HelpValueMasterDto> listHelpValueMasterDto = sessionFactory.getCurrentSession()
          .getNamedQuery("GET_HELP_VALUE_BY_PARAMETER")
          .setInteger("orgId", orgId)
          .setInteger("orgUnitId", orgUnitId)
          .setInteger("parameterId", parameterId)
          .setResultTransformer(Transformers.aliasToBean(HelpValueMasterDto.class)).list();

      if (listHelpValueMasterDto.isEmpty()) {
        return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
      } else {
        return new Response(SUCCESS, SUCCESS_200, null, listHelpValueMasterDto, null);
      }

    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  public Response getRefReangeByParameter(Integer parameterId, Integer refrangeTypeId,
      Integer orgId, Integer orgUnitId) throws ApplicationException {

    try {

      if (refrangeTypeId == REFERENCE_VALUE.intValue()) {
        List<ParamRefrengMasterDto> listParamRefrengMasterDto = sessionFactory.getCurrentSession()
            .getNamedQuery("GET_PARAM_REF_RANGE_VALUE_BY_PARAMETER").setInteger("orgId", orgId)
            .setInteger("orgUnitId", orgUnitId).setInteger("parameterId", parameterId)
            .setResultTransformer(Transformers.aliasToBean(ParamRefrengMasterDto.class)).list();
        return new Response(SUCCESS, SUCCESS_200, null, listParamRefrengMasterDto, null);
      }
      else if (refrangeTypeId == TEXTUAL_RANGE.intValue()) {
        List<ParamTextualRanageMasterDto> listParamTextualRanageMasterDto =
            sessionFactory.getCurrentSession().getNamedQuery("GET_TEXTUAL_VALUE_BY_PARAMETER_ID")
                .setInteger("orgId", orgId).setInteger("orgUnitId", orgUnitId)
                .setInteger("parameterId", parameterId)
                .setResultTransformer(Transformers.aliasToBean(ParamTextualRanageMasterDto.class))
                .list();
        return new Response(SUCCESS, SUCCESS_200, null, listParamTextualRanageMasterDto, null);
      }
      else if (refrangeTypeId.intValue() == MULTIVALUED_RANGE.intValue()) {
        List<ParamMultiTextualRangeMasterDto> listParamMultiTextualRangeMasterDto = sessionFactory
            .getCurrentSession().getNamedQuery("GET_MULTI_TEXTUAL_VALUE_BY_PARAMETER_ID")
            .setInteger("orgId", orgId).setInteger("orgUnitId", orgUnitId)
            .setInteger("parameterId", parameterId)
            .setResultTransformer(Transformers.aliasToBean(ParamMultiTextualRangeMasterDto.class))
            .list();
        return new Response(SUCCESS, SUCCESS_200, null, listParamMultiTextualRangeMasterDto, null);
      }
      else 
      {
        return new Response(SUCCESS, SUCCESS_200, "No Records Found.", null, null);
      }
      
    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }
}

