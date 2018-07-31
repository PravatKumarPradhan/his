package com.param.adt.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(proxyMode=ScopedProxyMode.TARGET_CLASS, value = "session")
public class SessionObject implements Serializable{
	private static final long serialVersionUID = 1L;

	public SessionObject(){
		allUrlMap = new LinkedHashMap<Integer, String>();
		allUrlMap.put(6, "appointments");
		allUrlMap.put(7, "appointmentList");
		allUrlMap.put(8, "patientRegistration");
		allUrlMap.put(19, "getlistOfPatient");
		allUrlMap.put(10, "doctorRegistration");
		allUrlMap.put(11, "doctorSlots");
		allUrlMap.put(12, "doctorSchedule");
		allUrlMap.put(20, "getlistOfDoctor");
		allUrlMap.put(26, "serviceSchedule");
		allUrlMap.put(51, "listDoctorType");
		allUrlMap.put(52, "listGroup");
		allUrlMap.put(53, "listSubGroup");
		allUrlMap.put(54, "listClasificationMaster");
		allUrlMap.put(55, "listDoctorCharge");
		allUrlMap.put(52, "listDoctorRoster");
		allUrlMap.put(21, "roleConfig");
		allUrlMap.put(93, "adminLandingPage");
		allUrlMap.put(57, "getPatientCategoryList");
		
	}
	
	private LinkedHashMap<Integer, String> allUrlMap;
	private LinkedHashMap<String, String> accessUrlMap;
	private HashMap<Long, String> listAccionIds;
	private HashMap<Long, String> listTabIds;
	private HashMap<Long, String> listModuleIds;
	private Integer roleMasterId;
	private Integer userMasterId;
	private Integer unitId;
	private Integer orgaizationId;
	private Integer doctorId;
	private Integer patientId;
	private String userName;
	private String zoneDesc;
	private String opdNumber;
	private Integer nurseId;
	private Map<Integer, String> listMessageConfiguration;
	private String doctorName;
	private Integer patientAppointmentId;
	
	public LinkedHashMap<Integer, String> getAllUrlMap() {
		return allUrlMap;
	}
	public void setAllUrlMap(LinkedHashMap<Integer, String> allUrlMap) {
		this.allUrlMap = allUrlMap;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}
	public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	public HashMap<Long, String> getListAccionIds() {
		return listAccionIds;
	}
	public void setListAccionIds(HashMap<Long, String> listAccionIds) {
		this.listAccionIds = listAccionIds;
	}
	public HashMap<Long, String> getListTabIds() {
		return listTabIds;
	}
	public void setListTabIds(HashMap<Long, String> listTabIds) {
		this.listTabIds = listTabIds;
	}
	public HashMap<Long, String> getListModuleIds() {
		return listModuleIds;
	}
	public void setListModuleIds(HashMap<Long, String> listModuleIds) {
		this.listModuleIds = listModuleIds;
	}
	public Integer getRoleMasterId() {
		return roleMasterId;
	}
	public void setRoleMasterId(Integer roleMasterId) {
		this.roleMasterId = roleMasterId;
	}
	public Integer getUserMasterId() {
		return userMasterId;
	}
	public void setUserMasterId(Integer userMasterId) {
		this.userMasterId = userMasterId;
	}
	public Integer getUnitId() {
		return unitId;
	}
	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}
	public String getZoneDesc() {
		return zoneDesc;
	}
	public void setZoneDesc(String zoneDesc) {
		this.zoneDesc = zoneDesc;
	}
	public String getOpdNumber() {
		return opdNumber;
	}
	public void setOpdNumber(String opdNumber) {
		this.opdNumber = opdNumber;
	}
	public Map<Integer, String> getListMessageConfiguration() {
		return listMessageConfiguration;
	}
	public void setListMessageConfiguration(Map<Integer, String> listMessageConfiguration) {
		this.listMessageConfiguration = listMessageConfiguration;
	}
	public LinkedHashMap<String, String> getAccessUrlMap() {
		return accessUrlMap;
	}
	public void setAccessUrlMap(LinkedHashMap<String, String> accessUrlMap) {
		this.accessUrlMap = accessUrlMap;
	}
	
	
	
	public Integer getOrgaizationId() {
		return orgaizationId;
	}
	public void setOrgaizationId(Integer orgaizationId) {
		this.orgaizationId = orgaizationId;
	}
	public Integer getNurseId() {
		return nurseId;
	}
	public void setNurseId(Integer nurseId) {
		this.nurseId = nurseId;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public Integer getPatientAppointmentId() {
		return patientAppointmentId;
	}
	public void setPatientAppointmentId(Integer patientAppointmentId) {
		this.patientAppointmentId = patientAppointmentId;
	}
	
	
	
}
