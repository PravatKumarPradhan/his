package com.param.lis.microbiology.transaction.dao;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.param.entity.lis.micro.TMicroIncubationDetailsMaster;
import com.param.entity.lis.micro.TMicroIncubationMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.ITransactionConstants;
import com.param.lis.global.common.Response;
import com.param.lis.microbiology.transaction.dto.MicrobioParamDto;
import com.param.lis.microbiology.transaction.dto.TMicroIncubationMasterDto;
import com.param.lis.transaction.dto.SampleAcceptancePendingDto;
import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IncubationObservationDaoImpl extends GenericDao<TMicroIncubationMaster, Integer>
		implements IincubationObservationDao, ICommonConstants, ITransactionConstants, IError {

	@Autowired
	private SessionFactory sessionFactory;



	@Autowired
	private DozerBeanMapper mapper;

	public IncubationObservationDaoImpl() {
		super(TMicroIncubationMaster.class);
	}

	final static Logger logger = Logger.getLogger(IncubationObservationDaoImpl.class);

	@Override
	public Response getIncubationObservationList(MicrobioParamDto microbioParamDto) throws ApplicationException {
		List<SampleAcceptancePendingDto> listMicrobioResultEntryMasterDto = null;
		try {
			listMicrobioResultEntryMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("INCUBATION_OBJSERVATION_WORKLIST")
					.setInteger("orgId", microbioParamDto.getOrgId())
					.setInteger("orgUnitId", microbioParamDto.getOrgUnitId())
					.setParameterList("sampleStatusIds", MICRO_INCUBATION_SAMPLE_STATUS_IDS)
					.setInteger("deptId", microbioParamDto.getDeptId())
					.setInteger("subDeptId", microbioParamDto.getSubDeptId())
					.setFirstResult(microbioParamDto.getOffset() != null ? microbioParamDto.getOffset() : 0)
					.setMaxResults(
							microbioParamDto.getRecordPerPage() != null ? microbioParamDto.getRecordPerPage() : 10)
					.setResultTransformer(Transformers.aliasToBean(SampleAcceptancePendingDto.class)).list();
			return new Response(SUCCESS, SUCCESS_200, null, listMicrobioResultEntryMasterDto, null);
		} catch (Exception e) {
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response getIncubationObservationListCount(MicrobioParamDto microbioParamDto) throws ApplicationException {
		try {
			BigInteger incubationObservationListCount = (BigInteger) sessionFactory.getCurrentSession()
					.getNamedQuery("INCUBATION_OBJSERVATION_WORKLIST_COUNT")
					.setInteger("orgId", microbioParamDto.getOrgId())
					.setParameterList("sampleStatusIds", MICRO_INCUBATION_SAMPLE_STATUS_IDS)
					.setInteger("deptId", microbioParamDto.getDeptId())
					.setInteger("subDeptId", microbioParamDto.getSubDeptId())
					.setInteger("orgUnitId", microbioParamDto.getOrgUnitId()).uniqueResult();
			if (incubationObservationListCount.compareTo(BigInteger.ZERO) == 1) {
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, incubationObservationListCount);
			} else {
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, BigInteger.ZERO);
			}

		} catch (Exception e) {
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response saveIncubationTrasaction(TMicroIncubationMasterDto tMicroIncubationMasterDto)
			throws ApplicationException 
	{
	  try {

	    if(tMicroIncubationMasterDto!=null)
	    {
	      TMicroIncubationMaster tMicroIncubationMaster = mapper.map(tMicroIncubationMasterDto,
	            TMicroIncubationMaster.class, "TMicroIncubationMasterDtoToTMicroIncubationMaster");
	        TMicroIncubationMaster tMicroIncubationMst = save(tMicroIncubationMaster);
	        for (Iterator iterator = tMicroIncubationMaster.getListTMicroIncubationDetailsMaster().iterator(); iterator.hasNext();) 
	        {
	          TMicroIncubationDetailsMaster tMicroIncubationDetailsMaster = (TMicroIncubationDetailsMaster) iterator.next();
	          tMicroIncubationDetailsMaster.settIncubationId(tMicroIncubationMst.gettIncubationId());
	          sessionFactory.getCurrentSession().save(tMicroIncubationDetailsMaster);
	        }
	        return new Response(SUCCESS, SUCCESS_200,SUCCESS_200_MESSAGE, null, null);
	    }
	    else {
	      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	    }
	      
    } catch (HibernateException exception) {
        logger.error("Exection", exception);
    } catch (Exception e) {
        logger.error("Exception", e);
        return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
    return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}
	
	
	
	@Override
    public Response updateIncubationTransaction(TMicroIncubationMasterDto tMicroIncubationMasterDto)
            throws ApplicationException 
    {
      try {

        if(tMicroIncubationMasterDto!=null)
        {
          TMicroIncubationMaster tMicroIncubationMaster = mapper.map(tMicroIncubationMasterDto,
                TMicroIncubationMaster.class, "TMicroIncubationMasterDtoToTMicroIncubationMaster");
            TMicroIncubationMaster tMicroIncubationMst = update(tMicroIncubationMaster);
            for (Iterator iterator = tMicroIncubationMaster.getListTMicroIncubationDetailsMaster().iterator(); iterator.hasNext();) 
            {
              TMicroIncubationDetailsMaster tMicroIncubationDetailsMaster = (TMicroIncubationDetailsMaster) iterator.next();
              tMicroIncubationDetailsMaster.settIncubationId(tMicroIncubationMst.gettIncubationId());
              sessionFactory.getCurrentSession().update(tMicroIncubationDetailsMaster);
            }
            return new Response(SUCCESS, SUCCESS_200,SUCCESS_200_MESSAGE, null, null);
        }
        else {
          return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
        }
          
    } catch (HibernateException exception) {
        logger.error("Exection", exception);
    } catch (Exception e) {
        logger.error("Exception", e);
        return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
    return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
	

	
	@Override
	public Response getIncubationDetails(Integer tIncubationId,Integer orgId,Integer orgUnitId) throws ApplicationException {
		  try 
	      {
	          TMicroIncubationMaster tMicroIncubationMaster = findById(tIncubationId); 
	          if(tMicroIncubationMaster!=null)
	          {
	            TMicroIncubationMasterDto tMicroIncubationMasterDto = 
	                mapper.map(tMicroIncubationMaster, TMicroIncubationMasterDto.class, "TMicroIncubationMasterDtoToTMicroIncubationMaster");
	            return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, tMicroIncubationMasterDto);
	          }
	          else 
	          {
	            return new Response(ERROR, ERR_500, "No Previous Details Found.", null, null);
	          }
	      } catch (HibernateException exception) 
	      {
	        logger.error("Exection", exception);
	        return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	      } catch (Exception e)
	      {
	        logger.error("Exception", e);
	        return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	      }
	}


	
}
