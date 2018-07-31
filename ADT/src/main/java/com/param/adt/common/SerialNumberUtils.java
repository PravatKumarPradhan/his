package com.param.adt.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class SerialNumberUtils {
	
	public String generateDoctorNumber(int doctorId,SessionObject sessionObject){
		String docId = String.valueOf(doctorId + 1);
		String doctorNumber ="";
		try{
			Date date =  ADTCommonDateUtils.getDateByzone(sessionObject.getZoneDesc());
			doctorNumber = "DC-"+new SimpleDateFormat("yy").format(date)+"-";
			if(docId.length() ==1 )
				doctorNumber+="000"+docId;
			else if(docId.length() ==2)
				doctorNumber+="00"+docId;
			else if(docId.length() ==3)
				doctorNumber+="0"+docId;
			else
				doctorNumber += docId;
		
			System.out.println(doctorNumber);
			
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return doctorNumber;
	}
	
	
	
	public String generateMrnNumber(int patientId,SessionObject sessionObject){
		String docId = String.valueOf(patientId + 1);
		String mrntNumber ="";
		try{
			Date date = ADTCommonDateUtils.getDateByzone(sessionObject.getZoneDesc());
			mrntNumber = "PC-"+new SimpleDateFormat("yy").format(date)+"-";
			if(docId.length() ==1 )
				mrntNumber+="000"+docId;
			else if(docId.length() ==2)
				mrntNumber+="00"+docId;
			else if(docId.length() ==3)
				mrntNumber+="0"+docId;
			else
				mrntNumber += docId;
		
			System.out.println(mrntNumber);
			
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return mrntNumber;
	}
	
	public String generateMrnNumber(int patientId,Date date){
		String docId = String.valueOf(patientId + 1);
		String mrntNumber ="";
		try{
			mrntNumber = "PC-"+new SimpleDateFormat("yy").format(date)+"-";
			if(docId.length() ==1 )
				mrntNumber+="000"+docId;
			else if(docId.length() ==2)
				mrntNumber+="00"+docId;
			else if(docId.length() ==3)
				mrntNumber+="0"+docId;
			else
				mrntNumber += docId;
		
			System.out.println(mrntNumber);
			
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return mrntNumber;
	}
	
	public String generateEmployeeNumber(int userId,SessionObject sessionObject){
		String docId = String.valueOf(userId + 1);
		String mrntNumber ="";
		try{
			Date date = ADTCommonDateUtils.getDateByzone(sessionObject.getZoneDesc());
			mrntNumber = "PCU-"+new SimpleDateFormat("yy").format(date)+"-";
			if(docId.length() ==1 )
				mrntNumber+="000"+docId;
			else if(docId.length() ==2)
				mrntNumber+="00"+docId;
			else if(docId.length() ==3)
				mrntNumber+="0"+docId;
			else
				mrntNumber += docId;
		
			System.out.println(mrntNumber);
			
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return mrntNumber;
	}
	
	public String generateOpdNumber(SessionObject sessionObject){
		
		
		String opdNumber = "";
		try{
			Date date = ADTCommonDateUtils.getDateByzone(sessionObject.getZoneDesc());
			//+new SimpleDateFormat("m").format(date)+new SimpleDateFormat("ss").format(date);

			String pattern = new SimpleDateFormat("yy").format(date)+"-"+new SimpleDateFormat("mm").format(date)+new SimpleDateFormat("dd").format(date)+new SimpleDateFormat("ss").format(date);
		opdNumber = "OPD-"+ pattern;
		
			/*if(opdMasterId.length() == 1){
				opdNumber += "000" + i;
				
			}else if(opdMasterId.length() == 2){
				opdNumber += "00" + i;
			
			}else if(opdMasterId.length() == 3){
				opdNumber += "0" + i;
			}else{
				opdNumber += i;
			}
			System.out.println(opdNumber);
			*/
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return opdNumber;
		
	}
	
	/*public static void main(String a[]){
		SerialNumberUtils utils = new SerialNumberUtils();
		utils.generateDoctorNumber(11);
	}*/
}
