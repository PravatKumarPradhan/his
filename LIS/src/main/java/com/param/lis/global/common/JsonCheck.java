package com.param.lis.global.common;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.param.lis.global.dto.TextualRangeMasterDto;
import com.param.lis.microbiology.transaction.dto.TBiochemicalTestDetailsMasterDto;
import com.param.lis.microbiology.transaction.dto.TBiochemicalTestMasterDto;
import com.param.lis.microbiology.transaction.dto.TMacroscopicExaminationMasterDto;
import com.param.lis.microbiology.transaction.dto.TMicroIncubationDetailsMasterDto;
import com.param.lis.microbiology.transaction.dto.TMicroIncubationMasterDto;
import com.param.lis.microbiology.transaction.dto.TMicroSampleMediaDto;
import com.param.lis.transaction.dto.BioChemParamDto;
import com.param.lis.unit.dto.FormulaDetailsDto;
import com.param.lis.unit.dto.FormulaMasterDto;
import com.param.lis.unit.dto.ParamMultiTextualRangeMasterDto;
import com.param.lis.unit.dto.ParamTextualRanageMasterDto;

public class JsonCheck<T>
{

	public String convertPojoToJson(T inputObject)
	{
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = null;
		try
		{
			jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(inputObject);
			System.out.println("jsonString :" + jsonString);
		} catch (JsonProcessingException e)
		{
			e.printStackTrace();
		}
		return jsonString;
	}

	public static <T> Response mapJsonToObjectList(String json, Class clazz) throws Exception
	{
		List<T> list;
		ObjectMapper mapper = new ObjectMapper();
		System.out.println("OLD =>" + json);
		System.out.println("NEW =>" + json);
		TypeFactory t = TypeFactory.defaultInstance();
		list = (json != null) ? mapper.readValue(json, t.constructCollectionType(ArrayList.class, clazz)) : null;
		System.out.println(list);
		System.out.println(list.get(0).getClass());
		return null;
	}

	public static <T> Response mapJsonToObject(String json, Class<T> clazz) throws Exception
	{
		try
		{
			T tt;
			ObjectMapper mapper = new ObjectMapper();
			System.out.println("OLD =>" + json);
			System.out.println("NEW =>" + json);
			TypeFactory t = TypeFactory.defaultInstance();
			tt = mapper.readValue(json, clazz);
			return null;

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args)
	{
		JsonCheck<FormulaMasterDto> jsonCheck  = new JsonCheck<>();
		FormulaMasterDto testParamMpprDto  = new FormulaMasterDto(); 
		jsonCheck.convertPojoToJson(testParamMpprDto);
		
		  JsonCheck<FormulaDetailsDto> jsonCheck1  = new JsonCheck<>();
		  FormulaDetailsDto testParamMpprDto1  = new FormulaDetailsDto(); 
		  jsonCheck1.convertPojoToJson(testParamMpprDto1);
	}
}
