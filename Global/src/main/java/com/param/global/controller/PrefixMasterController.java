package com.param.global.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.PrefixMasterDto;
import com.param.global.service.IPrefixMasterService;

@Controller
@RequestMapping("/adt")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PrefixMasterController implements ICommonConstants 
{

		@Autowired
		private IPrefixMasterService iPrefixMasterService;
		
		@RequestMapping(value = "/savePrefixMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody Response savePrefixMaster(@RequestBody PrefixMasterDto prefixMasterDto) {
			try {

				return iPrefixMasterService.addPrefixMaster(prefixMasterDto);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new Response(ERROR, null, null, null, null);
		}

		@RequestMapping(value = "/getPrefixMasterList",  method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody Response getPrefixMasterList(@RequestBody PrefixMasterDto prefixMasterDto) {
			try {
				return iPrefixMasterService.getPrefixMasterList(prefixMasterDto);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new Response(ERROR, null, null, null, null);
		}

		@RequestMapping(value = "/updatePrefixMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody Response updatePrefixMaster(@RequestBody PrefixMasterDto prefixMasterDto) {
			try {
				return iPrefixMasterService.updatePrefixMaster(prefixMasterDto);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new Response(ERROR, null, null, null, null);
		}

		@RequestMapping(value = "/getPrefixById", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody Response getPrefixById(@RequestBody PrefixMasterDto prefixMasterDto) {
			try {

				return iPrefixMasterService.getPrefixById(prefixMasterDto);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new Response(ERROR, null, null, null, null);
		}

		@RequestMapping(value = "/updateStatusForPrefix", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody Response updateStatusForPrefix(@RequestBody PrefixMasterDto prefixMasterDto) {
			try {

				return iPrefixMasterService.updateStatusForPrefix(prefixMasterDto);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new Response(ERROR, null, null, null, null);
		}

		@RequestMapping(value = "/getActivePrefixList", method = RequestMethod.GET)
		public @ResponseBody Response getActivePrefixList() {
			try {
				return iPrefixMasterService.getActivePrefixList();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new Response(ERROR, null, null, null, null);
		}
		
		@RequestMapping(value = "/getPrefixCount",  method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody Response getPrefixCount(@RequestBody PrefixMasterDto prefixMasterDto) {
			try {
				return iPrefixMasterService.getPrefixCount(prefixMasterDto);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new Response(ERROR, null, null, null, null);
		}
}
