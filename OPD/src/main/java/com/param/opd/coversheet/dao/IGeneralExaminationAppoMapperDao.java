package com.param.opd.coversheet.dao;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.opd.coversheet.dto.GeneralExaminationAppoMapperDto;
import com.param.opd.coversheet.model.GeneralExaminationAppoMapper;

public interface IGeneralExaminationAppoMapperDao extends IGenericDao<GeneralExaminationAppoMapper, Integer>{

	public Response getListOfGeneralExaminationMapper(GeneralExaminationAppoMapperDto generalExaminationAppoMapperDto)throws ApplicationException;
	
	public Response getListOfSavedGeneralExaminationMapper(GeneralExaminationAppoMapperDto generalExaminationAppoMapperDto)throws ApplicationException;
	
	//SAVE FUNCTIONS FOR GENERAL EXAM 
	public Response saveGeneralExaminationSystem(GeneralExaminationAppoMapper generalExamination)throws ApplicationException;
	public Response saveGeneralExaminationObservationMapper(GeneralExaminationAppoMapper generalExamination)throws ApplicationException;
	public Response saveGeneralExamSystemProperty(GeneralExaminationAppoMapper generalExamination)throws ApplicationException;
}
