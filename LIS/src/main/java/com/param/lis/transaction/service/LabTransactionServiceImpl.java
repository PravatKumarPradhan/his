package com.param.lis.transaction.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.common.SearchDto;
import com.param.lis.transaction.dao.ILabTransactionDao;
import com.param.lis.transaction.dto.LabSampleDetailsMasterDto;
import com.param.lis.transaction.dto.LabSampleMasterDto;
import com.param.lis.transaction.dto.SearchCommonDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class LabTransactionServiceImpl implements ILabTransactionService, ICommonConstants, IError {
	@Autowired
	ILabTransactionDao iLabTransactionDao;

	@Autowired
	private SessionFactory sessionFactory;

	final static Logger logger = Logger.getLogger(LabTransactionServiceImpl.class);

	@Override
	@Transactional
	public Response getPhlebotomyWorklist(Integer orgId, Integer orgUnitId,Integer deptId, Integer offset, Integer recordPerPage)
			throws ApplicationException {
		try {
			Response<LabSampleMasterDto, Integer> response = iLabTransactionDao.getPhlebotomyWorklist(orgId,
					orgUnitId,deptId, offset, recordPerPage);
			List<LabSampleMasterDto> listLabSampleMasterDto = response.getListObject();
			Comparator<LabSampleMasterDto> filteredWorklist = (e1, e2) -> {
				if (e1 == null)
					return (e2 == null) ? 0 : -1;
				if (e2 == null)
					return -1;
				if (e1.getCollectionTime() == null)
					return 0;
				return e1.getCollectionTime().isAfter(e1.getCollectionTime()) ? 1 : -1;
			};
			List<LabSampleMasterDto> result = listLabSampleMasterDto.stream().sorted(filteredWorklist)
					.collect(Collectors.toList());

			return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, result, null);
		} catch (Exception e) {
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getTotalPhlobotomyRecord(Integer orgId, Integer orgUnitId, Integer deptId) throws ApplicationException {
		try {
			return iLabTransactionDao.getTotalPhlobotomyRecord(orgId, orgUnitId,deptId);
		} catch (Exception e) {
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getPhlebotomyWorklistDetails(Integer orgId, Integer orgUnitId, Integer orderId,Integer deptId)
			throws ApplicationException {
		try {
			return iLabTransactionDao.getPhlebotomyWorklistDetails(orgId, orgUnitId, orderId,deptId);
		} catch (Exception e) {
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response sampleCollection(LabSampleMasterDto labSampleMasterDto) throws ApplicationException {
		try {
			return iLabTransactionDao.sampleCollection(labSampleMasterDto);
		} catch (Exception e) {
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getCollectedSample(Integer orgId, Integer orgUnitId, Integer deptId,Integer offset, Integer recordPerPage)
			throws ApplicationException {
		try {
			return iLabTransactionDao.getCollectedSample(orgId, orgUnitId,deptId, offset, recordPerPage);
		} catch (Exception e) {
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response sendToCr(List<LabSampleDetailsMasterDto> listLabSampleDetailsMasterDto)
			throws ApplicationException {
		try {
			return iLabTransactionDao.sendToCr(listLabSampleDetailsMasterDto);
		} catch (Exception e) {
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@Override
	@Transactional
	public Response getTotalSendToCrRecord(Integer orgId, Integer orgUnitId,Integer deptId) throws ApplicationException {
		try {
			return iLabTransactionDao.getTotalSendToCrRecord(orgId, orgUnitId,deptId);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	
	

	@Override
	@Transactional
	public Response v1GetPhlebotomyWorklistDetails(Integer orgId, Integer orgUnitId, Integer orderId)
			throws ApplicationException {
		try {
			return iLabTransactionDao.v1GetPhlebotomyWorklistDetails(orgId, orgUnitId, orderId);
		} catch (Exception e) {
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response searchPhlebotomyWorklistPatient(SearchCommonDto searchCommonDto) throws ApplicationException {
		try {
			return iLabTransactionDao.searchPhlebotomyWorklistPatient(searchCommonDto);
		} catch (Exception e) {
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getFilteredPhlebotomyWorklistPatient(SearchDto searchDto) throws ApplicationException {
		try {
			return iLabTransactionDao.getFilteredPhlebotomyWorklistPatient(searchDto);
		} catch (Exception e) {
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response searchSendToCRPatientlist(SearchCommonDto searchCommonDto) throws ApplicationException {
		try {
			return iLabTransactionDao.searchSendToCRPatientlist(searchCommonDto);
		} catch (Exception e) {
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getFilteredSendToCRPatientlist(SearchDto searchDto) throws ApplicationException {
		try {
			return iLabTransactionDao.getFilteredSendToCRPatientlist(searchDto);
		} catch (Exception e) {
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response searchLabGenralLabSampleNo(SearchCommonDto searchCommonDto) throws ApplicationException {
		try {
			return iLabTransactionDao.searchLabGenralLabSampleNo(searchCommonDto);
		} catch (Exception e) {
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	
	

}
