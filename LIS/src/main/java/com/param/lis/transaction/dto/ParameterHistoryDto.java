package com.param.lis.transaction.dto;

import java.util.List;

public class ParameterHistoryDto {
  
  private Integer parameterId;
  private String parameterName;
  private Integer refrangeTypeId;
  
  
  private List<ParameterResultDto> listParameterResultDto;
  
  public Integer getParameterId() {
    return parameterId;
  }
  public void setParameterId(Integer parameterId) {
    this.parameterId = parameterId;
  }
  public String getParameterName() {
    return parameterName;
  }
  public void setParameterName(String parameterName) {
    this.parameterName = parameterName;
  }
  public List<ParameterResultDto> getListParameterResultDto() {
    return listParameterResultDto;
  }
  public void setListParameterResultDto(List<ParameterResultDto> listParameterResultDto) {
    this.listParameterResultDto = listParameterResultDto;
  }
  public Integer getRefrangeTypeId() {
    return refrangeTypeId;
  }
  public void setRefrangeTypeId(Integer refrangeTypeId) {
    this.refrangeTypeId = refrangeTypeId;
  }
  

}
