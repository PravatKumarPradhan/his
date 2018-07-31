package com.param.adt.common;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@SessionAttributes("sessionObject")
public class SMSExpressionUtility{
	
	@Autowired
	private SessionObject sessionObject;

	public String[] getExpression(String sms) {
		try{
		char strArr[] = sms.toCharArray();
		String literals = "";
		for (int i = 0; i < strArr.length; i++) {
			if (strArr[i] == '(' && strArr[i + 1] == '(') {
				i++;
				i++;
				boolean flag = true;
				while (flag) {
					if (strArr[i] != ')') {
						literals = literals + strArr[i];
						i++;
					} else {
						literals = literals + "_";
						flag = false;
					}
				}
			}
		}
		return literals.split("_");
		}catch(Exception e){
			e.printStackTrace();
		}
		return new String[]{};
		
	}
	
	/*// here expressions are the keys in map
	public String getStringWithData(JsonNode node, String message){
		try{
			String expArr[] = getExpression(message);
			message = message.replaceAll("\\p{P}","");
			for(String temp : expArr){
				System.out.println(node.get(temp));
				message = message.replaceAll(temp, node.get(temp).toString());
			}
			System.out.println("message = " + message);
			return message;
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}*/
	  
	public String getMessageStringWithData(Object object, Integer messageType){
		try{
			Map<Integer,String> listMessageConfig = sessionObject.getListMessageConfiguration();
			String message = listMessageConfig.get(messageType);
			ObjectMapper mapper = new ObjectMapper();
			if(message != null && message != ""){
				String expressionArr[] = getExpression(message);
				message = message.replaceAll("\\p{P}","");
				JsonNode node = mapper.readTree(mapper.writeValueAsString(object));
				for(String temp : expressionArr){
					try{
						String data = "";
						if(node.get(temp).isObject()){
							JsonNode tempNode = node.get(temp);
							String time = tempNode.get("hour").toString() + ":" + tempNode.get("minute").toString();
							data = convert24HrTo12HrTime(time);
						}else{
							data = node.get(temp).toString() != null ? node.get(temp).toString() : "";
						}
						message = message.replaceAll(temp, data);
					}catch(Exception e){
						
					}
				}
				return message;
				}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	
	public String convert24HrTo12HrTime(String time){
		String result = "";
		try{
			result = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm")).format(DateTimeFormatter.ofPattern("hh:mm a"));
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public String getMessageTimeSlotStringWithData(List<LocalTime> listTimeSlots, Integer messageType){
		try{
			Map<Integer,String> listMessageConfig = sessionObject.getListMessageConfiguration();
			String message = listMessageConfig.get(messageType);
			ObjectMapper mapper = new ObjectMapper();
			if(message != null && message != ""){
				String expressionArr[] = getExpression(message);
				message = message.replaceAll("\\p{P}","");
				JsonNode node = mapper.readTree(mapper.writeValueAsString(listTimeSlots));
				for(String temp : expressionArr){
					try{
						String data = "";
						if(node.get(temp).isObject()){
							JsonNode tempNode = node.get(temp);
							String time = tempNode.get("hour").toString() + ":" + tempNode.get("minute").toString();
							data = data + "," + convert24HrTo12HrTime(time);
						}
						message = message.replaceAll(temp, data);
					}catch(Exception e){
						
					}
				}
				return message;
				}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
}
