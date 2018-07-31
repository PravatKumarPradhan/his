package com.param.lis.reports.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.param.lis.global.common.Response;
import com.param.lis.transaction.dto.LabResultMasterDto;
import com.param.lis.transaction.dto.MultyParamTestResDto;
import com.param.lis.transaction.dto.SingParamTestResDto;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IBiochemReportService {
	public Response printBiochemistryReports(List<LabResultMasterDto> listLabResultMasterDto,
			HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ApplicationException;

	public Response generateSingleParamReport(LabResultMasterDto listLabResultMasterDto,
			SingParamTestResDto singParamTestResDto,HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ApplicationException;

	public Response generateMultyParamReport(LabResultMasterDto listLabResultMasterDto,
	    SingParamTestResDto singParamTestResDto,HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ApplicationException;
}
