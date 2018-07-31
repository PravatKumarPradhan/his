package com.param.billing.global.dto;

import java.util.List;

import com.param.billing.master.dto.DepositMasterDto;

public class DepositCollectionReqDto {
	private DepositMasterDto depositMasterDto;
	private List<PaymentDepositDetailsDto> listPaymentDepositDetailsDto;
	public DepositMasterDto getDepositMasterDto() {
		return depositMasterDto;
	}
	public void setDepositMasterDto(DepositMasterDto depositMasterDto) {
		this.depositMasterDto = depositMasterDto;
	}
	public List<PaymentDepositDetailsDto> getListPaymentDepositDetailsDto() {
		return listPaymentDepositDetailsDto;
	}
	public void setListPaymentDepositDetailsDto(List<PaymentDepositDetailsDto> listPaymentDepositDetailsDto) {
		this.listPaymentDepositDetailsDto = listPaymentDepositDetailsDto;
	}
}
