package com.param.billing.common;

import org.springframework.stereotype.Service;

@Service
public class SerialNoUtility {

	public String generateDepositNo(Long id){
		try{
			String depNo = "DP-";
			String strId = String.valueOf(id);
			int length = strId.length();
			depNo = depNo + (length == 1 ? "000"+strId : length == 2 ? "00"+strId : length == 3 ? "0"+strId : strId);
			return depNo;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
