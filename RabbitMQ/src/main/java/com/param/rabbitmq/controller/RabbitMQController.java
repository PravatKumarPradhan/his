package com.param.rabbitmq.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.param.common.dto.ErrorResponse;
import com.param.rabbitmq.dto.base.Request;
import com.param.rabbitmq.service.EmrService.EmrService;
import com.param.rabbitmq.service.EmrService.IEmrService;
import com.param.rabbitmq.service.HisService.HisService;
import com.param.rabbitmq.service.HisService.IHisService;
import com.param.rabbitmq.service.OtimsService.IOtimsService;
import com.param.rabbitmq.service.OtimsService.OtimsService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/api/rabbitmq")
public class RabbitMQController {

	@RequestMapping(value = "/publish/otims", method = RequestMethod.POST)
	@ApiOperation(value = "Publish in OTIMS queue", response = Request.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	public ResponseEntity<Object> publishOtims(@RequestBody Request request) throws Exception {
		try {
			IOtimsService otimsService = new OtimsService();

			return new ResponseEntity<Object>(otimsService.publish(request), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Object>(
					new ErrorResponse("HIS-0000", "Error while publishing master in RabbitMQ", ex),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/publish/emr", method = RequestMethod.POST)
	@ApiOperation(value = "Publish in EMR queue", response = Request.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	public ResponseEntity<Object> publishEmr(@RequestBody Request request) throws Exception {
		try {
			IEmrService emrService = new EmrService();

			return new ResponseEntity<Object>(emrService.publish(request), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Object>(
					new ErrorResponse("HIS-0000", "Error while publishing master in RabbitMQ", ex),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/publish/his", method = RequestMethod.POST)
	@ApiOperation(value = "Publish in HIS queue", response = Request.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	public ResponseEntity<Object> publishHis(@RequestBody Request request) throws Exception {
		try {
			IHisService hisService = new HisService();

			return new ResponseEntity<Object>(hisService.publish(request), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Object>(
					new ErrorResponse("HIS-0000", "Error while publishing master in RabbitMQ", ex),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
