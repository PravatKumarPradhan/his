package com.param.global.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@NamedQueries({
	
	@NamedQuery(name="GET_PENDING_SCHEDULED_LOGS",
				query="SELECT 	schedule.scheduleLogsId as scheduleLogsId ,schedule.tableName as tableName , schedule.status as status,"
						+ "	  	to_char(schedule.dateTime , 'HH24:MI:SS') as dateTime , schedule.recordId as recordId , schedule.priority as priority,"
						+ "		schedule.errorCount as errorCount , schedule.addedBy as addedBy , to_char(schedule.addedDate, 'HH24:MI:SS')as addedDate , "
						+ "		schedule.action as action  , schedule.application as application "
						+ "FROM ScheduleLogs schedule "
						+ "WHERE TRIM(schedule.status) = 'PENDING' "
						+ "ORDER BY schedule.scheduleLogsId ASC "),
						
						
	@NamedQuery(name = "MARK_SCHEDULED_LOGS_COMPLETED",
				query= "UPDATE	ScheduleLogs schlog "
					 + "SET 	schlog.status = 'COMPLETED' "
					 + "WHERE	schlog.scheduleLogsId =:scheduleLogsId "),
			
	@NamedQuery(name = "MARK_SCHEDULED_LOGS_ERROR",
				query= "UPDATE	ScheduleLogs schlog "
					 + "SET 	schlog.status = 'ERROR' "
					 + "WHERE	schlog.scheduleLogsId =:scheduleLogsId ")
})


@Entity
@Table(name="t_schedule_logs" , schema="public")
@SequenceGenerator(name="schedule_logs_seq",sequenceName="public.schedule_logs_seq",allocationSize= 1)
public class ScheduleLogs {

	@Id
	@GeneratedValue(generator="schedule_logs_seq",strategy=GenerationType.SEQUENCE)
	@Column(name="schedule_logs_id")
	private int scheduleLogsId;
	
	@Column(name="table_name")
	private String tableName;
	
	@Column(name="status")
	private String status;
	
	@Column(name="date_time")
	private Date dateTime;
	
	@Column(name="record_id")
	private int recordId;
	
	@Column(name="priority")
	private String priority;
	
	@Column(name="error_count")
	private int errorCount;
	
	@Column(name="added_by")
	private int addedBy;
	
	@Column(name="added_date")
	private Date addedDate;
	
	@Column(name="action")
	private String action;
	
	@Column(name="application")
	private String application;

	public int getScheduleLogsId() {
		return scheduleLogsId;
	}

	public void setScheduleLogsId(int scheduleLogsId) {
		this.scheduleLogsId = scheduleLogsId;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public int getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}

	public int getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(int addedBy) {
		this.addedBy = addedBy;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}
	
}
