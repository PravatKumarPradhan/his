package com.param.adt.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
@SuppressWarnings({"deprecation"})
public class BillNoUtils {

	public String generateOpdBillNo(SessionObject sessionObject){
		String billNo = "";
		try{
			Date date = ADTCommonDateUtils.getDateByzone(sessionObject.getZoneDesc());
			billNo = "OP-"+new SimpleDateFormat("yy").format(date)+"-"+date.getDate()+date.getMinutes()+date.getSeconds();
			System.out.println(billNo);
		}catch(Exception e){
			e.printStackTrace();
		}
		return billNo;
	}		
}
