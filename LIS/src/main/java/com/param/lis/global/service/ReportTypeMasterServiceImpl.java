package com.param.lis.global.service;

import in.param.exception.ApplicationException;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.param.entity.lis.global.ReportTypeMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dao.IReportTypeMasterDao;
import com.param.lis.global.dto.GlobalCommonDto;
import com.param.lis.global.dto.ReportTypeMasterDto;

@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class ReportTypeMasterServiceImpl implements IReportTypeMasterService,ICommonConstants, IError{

	@Autowired
	private IReportTypeMasterDao iReportTypeMasterDao;
	
	@Override
	@Transactional
	public Response addReportTypeMaster(ReportTypeMasterDto reportTypeMasterDto)throws ApplicationException {
		try{
			Response res = iReportTypeMasterDao.checkReportTypeMaster(reportTypeMasterDto);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				return new Response<>(ERROR, ERR_500,REPORT_TYPRCODE_EXISIS, null, null);
			}else{
				long time = new Date().getTime();
				reportTypeMasterDto.setCreatedDate(time);
				reportTypeMasterDto.setUpdatedDate(time);
				iReportTypeMasterDao.addReportTypeMaster(reportTypeMasterDto);
				return new Response<>(SUCCESS, SUCCESS_200,REPORT_TYPR_ADD_SUCC, null, null);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201, REPORT_TYPRADD_FAIL, null, null);
	}

	@Override
	@Transactional
	public Response getaddReportTypeMasterById(Integer orgId, Integer reagentId)
			throws ApplicationException {
		try
		{
			return iReportTypeMasterDao.getaddReportTypeMasterById(orgId, reagentId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@Override
	@Transactional
	public Response ActivateInactivateReportTypeMaster(Integer orgId,
			Integer reagentId, Character reagentStatus)
			throws ApplicationException {
		try
		{
			Response resp= iReportTypeMasterDao.ActivateInactivateReportTypeMaster(orgId, reagentId, reagentStatus);
			ReportTypeMaster reportTypeMst = resp.getObject() != null ? (ReportTypeMaster)resp.getObject() : null;
			if(reportTypeMst != null){
				if (reportTypeMst.getStatus() == ACTIVE)
				{
					return new Response(SUCCESS, SUCCESS_200,REPORT_TYPRACTIVATE_SUCC, null, null);
				} else if (reportTypeMst.getStatus()== INACTIVE)
				{
					return new Response(SUCCESS, SUCCESS_200,REPORT_TYPRINACTIVATE_SUCC, null, null);
				} else
				{
					if (reagentStatus == ACTIVE)
					{
						return new Response(SUCCESS, SUCCESS_200,REPORT_TYPRINACTIVATE_SUCC, null, null);
					} else
					{
						return new Response(SUCCESS, SUCCESS_200,REPORT_TYPRINACTIVATE_FAIL, null, null);
					}
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return null;
	}

	@Override
	@Transactional
	public Response listReportTypeMaster(Integer orgId, Integer offset,
			Integer recordPerPage) throws ApplicationException {
		try
		{
			Response res =  iReportTypeMasterDao.listReportTypeMaster(orgId, offset, recordPerPage);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				List<GlobalCommonDto> unitMst = res.getListObject() != null ? (List<GlobalCommonDto>)res.getListObject() : null;
				if(unitMst.isEmpty())
				{
					return new Response(ERROR, ERR_500, REPORT_TYPRRECORDS_NOT_FOUND, null, null);
				}
				else{
					return new Response(SUCCESS, SUCCESS_200, null, unitMst, null);
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}

	@Override
	@Transactional
	public Response getToTalReportTypeMasterRecord(Integer orgId)
			throws ApplicationException {
		try
		{
			return iReportTypeMasterDao.getToTalReportTypeMasterRecord(orgId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response updateReportTypeMaster(ReportTypeMasterDto reportTypeMasterDto)
			throws ApplicationException {
		
		try{
			Response res = iReportTypeMasterDao.updateCheckReportTypeCodeAvaiable(reportTypeMasterDto);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				return new Response<>(ERROR, ERR_500,REPORT_TYPRCODE_EXISIS, null, null);
			}else{
				reportTypeMasterDto.setUpdatedDate(new Date().getTime());
				Response resdata =  iReportTypeMasterDao.updateReportTypeMaster(reportTypeMasterDto);
				if (resdata.getStatus().equalsIgnoreCase(SUCCESS))
				{
					return new Response(SUCCESS, SUCCESS_200,REPORT_TYPRUPDATE_SUCC, null, null);
				} else
				{
					return new Response(ERROR, ERR_500,REPORT_TYPRUPDATE_FAIL, null, null);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,REPORT_TYPRUPDATE_FAIL, null, null);
		
	}


}
