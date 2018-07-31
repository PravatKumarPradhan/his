package com.param.lis.global.service;

import in.param.exception.ApplicationException;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.param.entity.lis.global.SpecimanMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dao.ISpecimanMasterDao;
import com.param.lis.global.dto.SpecimanMasterDto;

@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class SpecimanMasterServiceImpl implements ISpecimanMasterService,ICommonConstants, IError{

	@Autowired
	private ISpecimanMasterDao iSpecimanMasterDao;
	
	@Override
	@Transactional
	public Response addSpecimanMaster(SpecimanMasterDto specimanMasterDto)throws ApplicationException {
		try{
			Response res = iSpecimanMasterDao.checkSpecimanMaster(specimanMasterDto);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				return new Response<>(ERROR, ERR_500,SPECIMAN_CODE_EXISIS, null, null);
			}else{
				long time = new Date().getTime();
				specimanMasterDto.setCreatedDate(time);
				specimanMasterDto.setUpdatedDate(time);
				iSpecimanMasterDao.addSpecimanMaster(specimanMasterDto);
				return new Response<>(SUCCESS, SUCCESS_200,SPECIMAN_ADD_SUCC, null, null);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,SPECIMAN_ADD_FAIL, null, null);
	}

	@Override
	@Transactional
	public Response getSpecimanMasteById(Integer orgId, Integer reagentId)
			throws ApplicationException {
		try
		{
			return iSpecimanMasterDao.getSpecimanMasteById(orgId, reagentId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@Override
	@Transactional
	public Response ActivateInactivateSpecimanMaster(Integer orgId,
			Integer reagentId, Character reagentStatus)
			throws ApplicationException {
		try
		{
			Response resp= iSpecimanMasterDao.ActivateInactivateSpecimanMaster(orgId, reagentId, reagentStatus);
			SpecimanMaster specimanMst= resp.getObject() != null ? (SpecimanMaster)resp.getObject() : null;
			if(specimanMst != null){
				if (specimanMst.getStatus() == ACTIVE)
				{
					return new Response(SUCCESS, SUCCESS_200,SPECIMAN_ACTIVATE_SUCC, null, null);
				} else if (specimanMst.getStatus()== INACTIVE)
				{
					return new Response(SUCCESS, SUCCESS_200,SPECIMAN_INACTIVATE_SUCC, null, null);
				} else
				{
					if (reagentStatus == ACTIVE)
					{
						return new Response(SUCCESS, SUCCESS_200,SPECIMAN_ACTIVATE_FAIL, null, null);
					} else
					{
						return new Response(SUCCESS, SUCCESS_200,SPECIMAN_INACTIVATE_FAIL, null, null);
					}
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
	public Response listSpecimanMaster(Integer orgId, Integer offset,
			Integer recordPerPage) throws ApplicationException {
		try
		{
			Response res =  iSpecimanMasterDao.listSpecimanMaster(orgId, offset, recordPerPage);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				List<SpecimanMasterDto> specimanMst = res.getListObject() != null ? (List<SpecimanMasterDto>)res.getListObject() : null;
				if(specimanMst.isEmpty())
				{
					return new Response(ERROR, ERR_500, SAMPLE_RECORDS_NOT_FOUND, null, null);
				}
				else{
					return new Response(SUCCESS, SUCCESS_200, null, specimanMst, null);
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
	public Response getToTalSpecimanMaster(Integer orgId)
			throws ApplicationException {
		try
		{
			return iSpecimanMasterDao.getToTalSpecimanMaster(orgId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response updateSpecimanMaster(SpecimanMasterDto specimanMasterDto)
			throws ApplicationException {
		
		try{
			Response res = iSpecimanMasterDao.updateCheckReportTypeCodeAvaiable(specimanMasterDto);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				return new Response<>(ERROR, ERR_500,SPECIMAN_CODE_EXISIS, null, null);
			}else{
				specimanMasterDto.setUpdatedDate(new Date().getTime());
				Response resdata =  iSpecimanMasterDao.updateSpecimanMaster(specimanMasterDto);
				if (resdata.getStatus().equalsIgnoreCase(SUCCESS))
				{
					return new Response(SUCCESS, SUCCESS_200,SPECIMAN_UPDATE_SUCC, null, null);
				} else
				{
					return new Response(ERROR, ERR_500,SPECIMAN_UPDATE_FAIL, null, null);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,SPECIMAN_UPDATE_FAIL, null, null);
		
	
	}

	@Override
	@Transactional
	public Response specimanGrossRequriedInSpecimanMaster(Integer orgId,
			Integer specimanId, Character specimanGross)
			throws ApplicationException {
		try
		{
			Response resp= iSpecimanMasterDao.specimanGrossRequriedInSpecimanMaster(orgId, specimanId, specimanGross);
			SpecimanMaster specimanMst= resp.getObject() != null ? (SpecimanMaster)resp.getObject() : null;
			if(specimanMst != null){
				if (specimanMst.getSpecimanGross() == YES)
				{
					return new Response(SUCCESS, SUCCESS_200,SPECIMAN_GROSS_SUCC, null, null);
				} else if (specimanMst.getSpecimanGross()== NO)
				{
					return new Response(SUCCESS, SUCCESS_200,SPECIMAN_GROSS_FAIL, null, null);
				} else
				{
					if (specimanGross == YES)
					{
						return new Response(SUCCESS, SUCCESS_200,SPECIMAN_GROSS_FAIL, null, null);
					} else
					{
						return new Response(SUCCESS, SUCCESS_200,SPECIMAN_GROSS_FAIL, null, null);
					}
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
	public Response specimanBlockRequriedInSpecimanMaster(Integer orgId,
			Integer specimanId, Character specimanBlock)
			throws ApplicationException {
		try
		{
			Response resp= iSpecimanMasterDao.specimanBlockRequriedInSpecimanMaster(orgId, specimanId, specimanBlock);
			SpecimanMaster specimanMst= resp.getObject() != null ? (SpecimanMaster)resp.getObject() : null;
			if(specimanMst != null){
				if (specimanMst.getSpecimanBlock() == YES)
				{
					return new Response(SUCCESS, SUCCESS_200,SPECIMAN_BLOCK_SUCC, null, null);
				} else if (specimanMst.getSpecimanBlock()== NO)
				{
					return new Response(SUCCESS, SUCCESS_200,SPECIMAN_BLOCK_FAIL, null, null);
				} else
				{
					if (specimanBlock == YES)
					{
						return new Response(SUCCESS, SUCCESS_200,SPECIMAN_BLOCK_FAIL, null, null);
					} else
					{
						return new Response(SUCCESS, SUCCESS_200,SPECIMAN_BLOCK_FAIL, null, null);
					}
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}


}
