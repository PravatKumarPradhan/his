package com.param.lis.global.service;

import in.param.exception.ApplicationException;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.param.entity.lis.global.BactClassificationMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dao.IBacteriaClassficationMasterDao;
import com.param.lis.global.dto.BactClassificationMasterDto;
import com.param.lis.global.dto.GlobalCommonDto;

@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class BacteriaClassficationMasterServiceImpl implements IBacteriaClassficationMasterService,ICommonConstants, IError{

	@Autowired
	private IBacteriaClassficationMasterDao iBacteriaClassficationMasterDao;
	
	@Override
	@Transactional
	public Response addBacteriaClassficationMaster(BactClassificationMasterDto bactClassificationMasterDto)throws ApplicationException {
		try{
			Response res = iBacteriaClassficationMasterDao.checkBacteriaClassficationMaster(bactClassificationMasterDto);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				return new Response<>(ERROR, ERR_500,BACTERIA_CLASSFICATION_CODE_EXISIS, null, null);
			}else{
				long time = new Date().getTime();
				bactClassificationMasterDto.setCreatedDate(time);
				bactClassificationMasterDto.setUpdatedDate(time);
				iBacteriaClassficationMasterDao.addBacteriaClassficationMaster(bactClassificationMasterDto);
				return new Response<>(SUCCESS, SUCCESS_200,BACTERIA_CLASSFICATION_ADD_SUCC, null, null);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,BACTERIA_ADD_FAIL, null, null);
	}

	@Override
	@Transactional
	public Response getBacteriaClassficationMasterById(Integer orgId, Integer reagentId)
			throws ApplicationException {
		try
		{
			return iBacteriaClassficationMasterDao.getBacteriaClassficationMasterById(orgId, reagentId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@Override
	@Transactional
	public Response ActivateInactivateBacteriaClassficationMaster(Integer orgId,
			Integer reagentId, Character reagentStatus)
			throws ApplicationException {
		try
		{
			Response resp= iBacteriaClassficationMasterDao.ActivateInactivateBacteriaClassficationMaster(orgId, reagentId, reagentStatus);
			BactClassificationMaster bacteriaMst = resp.getObject() != null ? (BactClassificationMaster)resp.getObject() : null;
			if(bacteriaMst != null){
				if (bacteriaMst.getStatus() == ACTIVE)
				{
					return new Response(SUCCESS, SUCCESS_200,BACTERIA_CLASSFICATION_ACTIVATE_SUCC, null, null);
				} else if (bacteriaMst.getStatus()== INACTIVE)
				{
					return new Response(SUCCESS, SUCCESS_200,BACTERIA_CLASSFICATION_INACTIVATE_SUCC, null, null);
				} else
				{
					if (reagentStatus == ACTIVE)
					{
						return new Response(SUCCESS, SUCCESS_200,BACTERIA_CLASSFICATION_ACTIVATE_FAIL, null, null);
					} else
					{
						return new Response(SUCCESS, SUCCESS_200,BACTERIA_CLASSFICATION_INACTIVATE_FAIL, null, null);
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
	public Response listBacteriaClassficationMaster(Integer orgId, Integer offset,
			Integer recordPerPage) throws ApplicationException {
		try
		{
			Response res =  iBacteriaClassficationMasterDao.listBacteriaClassficationMaster(orgId, offset, recordPerPage);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				List<GlobalCommonDto> unitMst = res.getListObject() != null ? (List<GlobalCommonDto>)res.getListObject() : null;
				if(unitMst.isEmpty())
				{
					return new Response(ERROR, ERR_500, SAMPLE_RECORDS_NOT_FOUND, null, null);
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
	public Response getToTalBacteriaClassficationMaster(Integer orgId)
			throws ApplicationException {
		try
		{
			return iBacteriaClassficationMasterDao.getToTalBacteriaClassficationMaster(orgId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response updateBacteriaClassficationMaster(BactClassificationMasterDto bactClassificationMasterDto)
			throws ApplicationException {
		
		try{
			Response res = iBacteriaClassficationMasterDao.updateCheckBacteriaClassficationCodeAvaiable(bactClassificationMasterDto);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				return new Response<>(ERROR, ERR_500,BACTERIA_CLASSFICATION_CODE_EXISIS, null, null);
			}else{
				bactClassificationMasterDto.setUpdatedDate(new Date().getTime());
				Response resdata =  iBacteriaClassficationMasterDao.updateBacteriaClassficationMaster(bactClassificationMasterDto);
				if (resdata.getStatus().equalsIgnoreCase(SUCCESS))
				{
					return new Response(SUCCESS, SUCCESS_200,BACTERIA_CLASSFICATION_UPDATE_SUCC, null, null);
				} else
				{
					return new Response(ERROR, ERR_500,BACTERIA_CLASSFICATION_UPDATE_FAIL, null, null);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,BACTERIA_CLASSFICATION_UPDATE_FAIL, null, null);
	}


}
