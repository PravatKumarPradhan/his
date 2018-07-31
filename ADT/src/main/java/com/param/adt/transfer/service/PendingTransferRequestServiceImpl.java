package com.param.adt.transfer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.transfer.dao.IPendingTransferRequestDao;
import com.param.adt.transfer.dto.TransferDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PendingTransferRequestServiceImpl implements IPendingTransferRequestService, ICommonConstants {

	static String accStatus, rejStatus;
	static StringBuilder message = new StringBuilder();

	@Autowired
	IPendingTransferRequestDao iPendingTransferRequestDao;

	@Override
	public Response getPendingTransferRequestListForDoctor(TransferDto transferDto) throws ApplicationException {
		try {
			return iPendingTransferRequestDao.getPendingTransferRequestListForDoctor(transferDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	/*@Override
	public Response acceptRejectTransferRequest(TransferDto transferDto) throws ApplicationException {

		try {
			if (transferDto.getAcceptTransferReqList().size() > 0  ) {
				Iterator<TransferDto> itr = transferDto.getAcceptTransferReqList().iterator();

				while (itr.hasNext()) {
					TransferDto newTransferDto = new TransferDto();
					TransferDto obj = itr.next();
					newTransferDto.setTransferRequestId(obj.getTransferRequestId());
					newTransferDto.setOrganizationId(transferDto.getOrganizationId());
					newTransferDto.setUnitId(transferDto.getUnitId());
					newTransferDto.setUpdatedBy(transferDto.getUpdatedBy());
					newTransferDto.setUpdatedDate(transferDto.getUpdatedDate());
					Response resAcc = iPendingTransferRequestDao.acceptRequest(newTransferDto);
					if(resAcc.getStatus().equals(SUCCESS))
					{
						accStatus = SUCCESS;
						message.append("Accept list is not empty");
					}
					else
					{
						accStatus=ERROR;
					}
				}
			}
			else
			{
				accStatus=SUCCESS;
				message.append("Accept List is empty");
			}
			if (transferDto.getRejectTransferReqList().size() > 0) {
				Iterator<TransferDto> itr = transferDto.getRejectTransferReqList().iterator();

				while (itr.hasNext()) {
					TransferDto newTransferDto = new TransferDto();
					TransferDto obj = itr.next();
					newTransferDto.setTransferRequestId(obj.getTransferRequestId());
					newTransferDto.setRejectReason(obj.getRejectReason());
					newTransferDto.setOrganizationId(transferDto.getOrganizationId());
					newTransferDto.setUnitId(transferDto.getUnitId());
					newTransferDto.setUpdatedBy(transferDto.getUpdatedBy());
					newTransferDto.setUpdatedDate(transferDto.getUpdatedDate());
					Response resRej = iPendingTransferRequestDao.rejectRequest(newTransferDto);
					if(resRej.getStatus().equals(SUCCESS))
					{
						rejStatus = SUCCESS;
						message.append("Reject list is not empty");
					}
					else {
						rejStatus = ERROR;
					}
				}
				
			}else
			{
				rejStatus=SUCCESS;
				message.append("Reject list is empty");
			}
			
			if (accStatus.equals(SUCCESS) && rejStatus.equals(SUCCESS)) {
				return new Response(SUCCESS, null, message.toString(), null, null);
			}else {
				return new Response(ERROR, null, message.toString(), null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}*/

	@Override
	public Response getPendingTransferRequestListForADT(TransferDto transferDto) throws ApplicationException {
		try {
			return iPendingTransferRequestDao.getPendingTransferRequestListForADT(transferDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response acceptTransferRequestForADT(TransferDto transferDto) throws ApplicationException {
		try {
			return iPendingTransferRequestDao.acceptTransferRequestForADT(transferDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response rejectTransferRequestForADT(TransferDto transferDto) throws ApplicationException {
		try {
			return iPendingTransferRequestDao.rejectTransferRequestForADT(transferDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response acceptFinalTransferRequest(TransferDto transferDto) throws ApplicationException {
		try {
			return iPendingTransferRequestDao.acceptFinalTransferRequest(transferDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response rejectFinalTransferRequest(TransferDto transferDto) throws ApplicationException {
		try {
			return iPendingTransferRequestDao.rejectFinalTransferRequest(transferDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response acceptTransferRequestForDoctor(TransferDto transferDto) throws ApplicationException {
		try {
			return iPendingTransferRequestDao.acceptTransferRequestForDoctor(transferDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response rejectTransferRequestForDoctor(TransferDto transferDto) throws ApplicationException {
		try {
			return iPendingTransferRequestDao.rejectTransferRequestForDoctor(transferDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getFinalTransferRequestList(TransferDto transferDto) throws ApplicationException {
		try {
			return iPendingTransferRequestDao.getFinalTransferRequestList(transferDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response acceptTransferRequest(TransferDto transferDto) throws ApplicationException {
		try {
			return iPendingTransferRequestDao.acceptTransferRequest(transferDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response rejectTransferRequest(TransferDto transferDto) throws ApplicationException {
		try {
			return iPendingTransferRequestDao.rejectTransferRequest(transferDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getFinalTransferRequestListByAdmission(TransferDto transferDto) throws ApplicationException {
		try {
			return iPendingTransferRequestDao.getFinalTransferRequestListByAdmission(transferDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
