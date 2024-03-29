package com.param.lis.unit.dao;

import com.param.lis.global.common.Response;
import com.param.lis.unit.dto.TPanelMasterDto;
import in.param.exception.ApplicationException;


@SuppressWarnings("rawtypes")
public interface IPanelTestDao {

  public Response addPanel(TPanelMasterDto tpanelTestMpprMasterDto) throws ApplicationException;
  public Response getPanelById(Integer panelId,Integer orgId,Integer orgUnitId) throws ApplicationException;
  public Response updatePanel(TPanelMasterDto tpanelTestMpprMasterDto) throws ApplicationException;
  public Response activeInactivePanelStatus(Integer panelId,Integer orgId, Integer orgUnitid, Character status) throws ApplicationException;
  public Response getPanelList(Integer orgId,Integer orgUnitId, Integer offset, Integer recordPerPage) throws ApplicationException;
  public Response getPanelListCount(Integer orgId,Integer orgUnitId) throws ApplicationException;
  public Response getUnitServiceMapperList(Integer orgId,Integer orgUnitId) throws ApplicationException;
  public Response getPanelDescByPanelId(Integer panelId,Integer orgId,Integer orgUnitId) throws ApplicationException;
}
