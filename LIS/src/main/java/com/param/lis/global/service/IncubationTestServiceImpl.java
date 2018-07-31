package com.param.lis.global.service;

import in.param.exception.ApplicationException;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.param.entity.lis.global.IncubationTestMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dao.IIncubationTestMasterDao;
import com.param.lis.global.dto.GlobalCommonDto;
import com.param.lis.global.dto.IncubationTestMasterDto;

@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class IncubationTestServiceImpl implements IIncubationTestMasterService,ICommonConstants, IError{

	@Autowired
	private IIncubationTestMasterDao iIncubationTestMasterDao;
	
	@Override
	@Transactional
	public Response addIncubationTest(IncubationTestMasterDto incubationTestDto)throws ApplicationException {
		try{
			Response res = iIncubationTestMasterDao.checkIncubationTest(incubationTestDto);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				return new Response<>(ERROR, ERR_500,INCUBATION_TEST_MASTER_CODE_EXISIS, null, null);
			}else{
				long time = new Date().getTime();
				incubationTestDto.setCreatedDate(time);
				incubationTestDto.setUpdatedDate(time);
				iIncubationTestMasterDao.addIncubationTest(incubationTestDto);
				return new Response<>(SUCCESS, SUCCESS_200,INCUBATION_TEST_MASTER_ADD_SUCC, null, null);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,BACTERIA_ADD_FAIL, null, null);
	}

	@Override
	@Transactional
	public Response getIncubationTestById(Integer orgId, Integer reagentId)
			throws ApplicationException {
		try
		{
			return iIncubationTestMasterDao.getIncubationTestById(orgId, reagentId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@Override
	@Transactional
	public Response ActivateInactivateIncubationTest(Integer orgId,
			Integer reagentId, Character reagentStatus)
			throws ApplicationException {
		try
		{
			Response resp= iIncubationTestMasterDao.ActivateInactivateIncubationTest(orgId, reagentId, reagentStatus);
			IncubationTestMaster IncubationTestMst = resp.getObject() != null ? (IncubationTestMaster)resp.getObject() : null;
			if(IncubationTestMst != null){
				if (IncubationTestMst.getStatus() == ACTIVE)
				{
					return new Response(SUCCESS, SUCCESS_200,INCUBATION_TEST_MASTER_ACTIVATE_SUCC, null, null);
				} else if (IncubationTestMst.getStatus()== INACTIVE)
				{
					return new Response(SUCCESS, SUCCESS_200,INCUBATION_TEST_MASTER_INACTIVATE_SUCC, null, null);
				} else
				{
					if (reagentStatus == ACTIVE)
					{
						return new Response(SUCCESS, SUCCESS_200,INCUBATION_TEST_MASTER_ACTIVATE_SUCC, null, null);
					} else
					{
						return new Response(SUCCESS, SUCCESS_200,INCUBATION_TEST_MASTER_INACTIVATE_FAIL, null, null);
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
	public Response listIncubationTest(Integer orgId, Integer offset,
			Integer recordPerPage) throws ApplicationException {
		try
		{
			Response res =  iIncubationTestMasterDao.listIncubationTest(orgId, offset, recordPerPage);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				List<GlobalCommonDto> unitMst = res.getListObject() != null ? (List<GlobalCommonDto>)res.getListObject() : null;
				if(unitMst.isEmpty())
				{
					return new Response(ERROR, ERR_500, INCUBATION_TEST_MASTER_NOT_FOUND, null, null);
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
	public Response getToTalIncubationTest(Integer orgId)
			throws ApplicationException {
		try
		{
			return iIncubationTestMasterDao.getToTalIncubationTest(orgId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response updateIncubationTest(IncubationTestMasterDto incubationTestMasterDto)
			throws ApplicationException {
		
		try{
			Response res = iIncubationTestMasterDao.updateCheckIncubationTestCodeAvaiable(incubationTestMasterDto);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				return new Response<>(ERROR, ERR_500,INCUBATION_TEST_MASTER_CODE_EXISIS, null, null);
			}else{
				incubationTestMasterDto.setUpdatedDate(new Date().getTime());
				Response resdata =  iIncubationTestMasterDao.updateIncubationTest(incubationTestMasterDto);
				if (resdata.getStatus().equalsIgnoreCase(SUCCESS))
				{
					return new Response(SUCCESS, SUCCESS_200,INCUBATION_TEST_MASTER_UPDATE_SUCC, null, null);
				} else
				{
					return new Response(ERROR, ERR_500,INCUBATION_TEST_MASTER_UPDATE_FAIL, null, null);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,INCUBATION_TEST_MASTER_UPDATE_FAIL, null, null);
		
		/*try
		{
			incubationTestMasterDto.setUpdatedDate(new Date().getTime());
			Response res =  iIncubationTestMasterDao.updateIncubationTest(incubationTestMasterDto);
			if (res.getStatus().equalsIgnoreCase(SUCCESS))
			{
				return new Response(SUCCESS, SUCCESS_200,INCUBATION_TEST_MASTER_UPDATE_SUCC, null, null);
			} else
			{
				return new Response(ERROR, ERR_500,INCUBATION_TEST_MASTER_UPDATE_FAIL, null, null);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}*/
	}


}
