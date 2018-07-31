package com.param.lis.transaction.service;

import java.util.List;
import com.param.entity.lis.transaction.LabSampleDetailsMaster;
import com.param.lis.global.common.Response;
import com.param.lis.global.common.SearchDto;
import com.param.lis.transaction.dto.BioChemParamDto;
import com.param.lis.transaction.dto.LabResultMasterDto;
import com.param.lis.transaction.dto.LabSampleDetailsMasterDto;
import com.param.lis.transaction.dto.ParameterSearchDto;
import com.param.lis.transaction.dto.ResultEntryDataDto;
import com.param.lis.transaction.dto.SearchCommonDto;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IBioChemistryService {
  public Response sampleAcceptancePending(BioChemParamDto bioChemParamDto)
      throws ApplicationException;

  public Response sampleAcceptancePendingCount(BioChemParamDto bioChemParamDto)
      throws ApplicationException;

  public Response bioChemistryAcceptRejectSample(
      List<LabSampleDetailsMasterDto> listlabSampleDetailsMasterDto) throws ApplicationException;

  public Response getBioChemistryWorklist(BioChemParamDto bioChemParamDto)
      throws ApplicationException;

  public Response getBioChemistryWorklistCount(BioChemParamDto bioChemParamDto)
      throws ApplicationException;

  public Response sendToResultEntry(List<LabSampleDetailsMasterDto> listlabSampleDetailsMasterDto)
      throws ApplicationException;

  public Response sampleRetest(List<LabResultMasterDto> listLabResultMasterDto)
      throws ApplicationException;

  public Response sampleRecollect(List<LabResultMasterDto> listLabResultMasterDto)
      throws ApplicationException;

  public Response biochemFinalReportRelease(LabSampleDetailsMasterDto labSampleDetailsMasterDto)
      throws ApplicationException;

  public Response resultEntryData(ResultEntryDataDto resultEntryDataDto)
      throws ApplicationException;

  public Response addReportHistroyMaster(LabSampleDetailsMaster labSampleDetailsMaster,
      Integer resultId) throws ApplicationException;

  public Response searchbioChemistryPatientlist(SearchCommonDto searchCommonDto)
      throws ApplicationException;

  public Response searchBioChemistrySampleNo(SearchCommonDto searchCommonDto)
      throws ApplicationException;

  public Response getFilteredBioChemistryList(SearchDto searchDto) throws ApplicationException;

  public Response getFilteredWorklistForDepartment(SearchDto searchDto) throws ApplicationException;

  public Response getParameterHistory(ParameterSearchDto parameterSearchDto)
      throws ApplicationException;

  public Response getPreviousResultIdByTest(BioChemParamDto bioChemParamDto)
      throws ApplicationException;

  public Response getRetestedRecollectedResult(BioChemParamDto bioChemParamDto)
      throws ApplicationException;

  public Response getResultEnteryByDateTime(BioChemParamDto bioChemParamDto)
      throws ApplicationException;

  public Response getResultValidatedByDateTime(BioChemParamDto bioChemParamDto)
      throws ApplicationException;

  public Response getResultReleaseByDateTime(BioChemParamDto bioChemParamDto)
      throws ApplicationException;

}
