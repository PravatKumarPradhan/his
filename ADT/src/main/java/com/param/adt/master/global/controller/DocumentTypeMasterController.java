package com.param.adt.master.global.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.global.dto.DocumentTypeMasterDto;
import com.param.adt.master.global.service.IDocumentTypeMasterService;

@Controller
@RequestMapping("/adt")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class DocumentTypeMasterController implements ICommonConstants 
{

		@Autowired
		private IDocumentTypeMasterService iDocumentTypeMasterService;
		
		@RequestMapping(value="/saveDocumentTypeMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody Response saveDocumentTypeMaster(@RequestBody DocumentTypeMasterDto documentTypeMasterDto){
			try{
				
				return iDocumentTypeMasterService.addDocumentTypeMaster(documentTypeMasterDto);
			}catch(Exception e){
				e.printStackTrace();
			}
			return new Response(ERROR, null, null, null,null);
		}
		
		@RequestMapping(value="/getDocumentTypeMasterList", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody Response getDocumentTypeMasterList(@RequestBody DocumentTypeMasterDto documentTypeMasterDto){
			try{
				return iDocumentTypeMasterService.getDocumentTypeMasterList(documentTypeMasterDto);
			}catch(Exception e){
				e.printStackTrace();
			}
			return new Response(ERROR, null, null, null,null);
		}
		
		 
		@RequestMapping(value="/updateDocumentTypeMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody Response updateDocumentTypeMaster(@RequestBody DocumentTypeMasterDto documentTypeMasterDto){
			try{
				return iDocumentTypeMasterService.updateDocumentTypeMaster(documentTypeMasterDto);
			}catch(Exception e){
				e.printStackTrace();
			}
			return new Response(ERROR, null, null, null,null);
		}
		
		@RequestMapping(value="/getDocumentTypeById" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody Response getDocumentTypeById(@RequestBody DocumentTypeMasterDto documentTypeMasterDto){
			try{
				
				return iDocumentTypeMasterService.getDocumentTypeById(documentTypeMasterDto);
			}catch(Exception e){
				e.printStackTrace();
			}
			return new Response(ERROR, null, null, null,null);
		}
		
		@RequestMapping(value="/updateStatusForDocumentType" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody Response updateStatusForDocumentType(@RequestBody DocumentTypeMasterDto documentTypeMasterDto){
			try{
				
				return iDocumentTypeMasterService.updateStatusForDocumentType(documentTypeMasterDto);
			}catch(Exception e){
				e.printStackTrace();
			}
			return new Response(ERROR, null, null, null,null);
		}
		
		@RequestMapping(value="/getActiveDocumentTypeList", method = RequestMethod.GET)
		public @ResponseBody Response getActiveDocumentTypeList(){
			try{
				return iDocumentTypeMasterService.getActiveDocumentTypeList();
			}catch(Exception e){
				e.printStackTrace();
			}
			return new Response(ERROR, null, null, null,null);
		}
		
		@RequestMapping(value="/getDocumentTypeCount" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody Response getDocumentTypeCount(@RequestBody DocumentTypeMasterDto documentTypeMasterDto){
			try{
				
				return iDocumentTypeMasterService.getDocumentTypeCount(documentTypeMasterDto);
			}catch(Exception e){
				e.printStackTrace();
			}
			return new Response(ERROR, null, null, null,null);
		}
}
