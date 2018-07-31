package com.param.lis.unit.service;

import com.param.lis.global.common.Response;
import com.param.lis.unit.dto.FormulaMasterDto;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IFormulaMasterService {

  public Response addFormula(FormulaMasterDto formulaMasterDto) throws ApplicationException;
  public Response getFormulaById(Integer formulaId,Integer orgId,Integer orgUnitId) throws ApplicationException;
  public Response updateFormula(FormulaMasterDto formulaMasterDto) throws ApplicationException;
  public Response activeInactiveFormula(Integer formulaId,Integer orgId, Integer orgUnitid, Character status) throws ApplicationException;
  public Response getFormulaList(Integer orgId,Integer orgUnitId, Integer offset, Integer recordPerPage) throws ApplicationException;
  public Response getFormulaListCount(Integer orgId,Integer orgUnitId) throws ApplicationException;
  public Response getParameterListForFormula(Integer orgId,Integer orgUnitId, Character isFormula) throws ApplicationException;
  public Response getFormulaDetails(Integer formulaId,Integer orgId, Integer orgUnitId)  throws ApplicationException;
  public Response getFormulaByParameter(Integer parameterId,Integer orgId, Integer orgUnitId)  throws ApplicationException;
}
