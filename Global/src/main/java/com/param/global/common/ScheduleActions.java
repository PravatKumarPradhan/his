package com.param.global.common;

public interface ScheduleActions {

	public static final String INSERT = "INSERT";
	public static final String UPDATE = "UPDATE";
	public static final String DELETE = "DELETE";

	public static final String HIS = "HIS";

	public static final String PRIORITY_HIGH = "HIGH";
	public static final String PRIORITY_NORM = "NORMAL";
	public static final String PRIORITY_LOW = "LOW";

	public static final String STATUS_PENDING = "PENDING";
	public static final String STATUS_INPROGRESS = "IN_PROGRESS";
	public static final String STATUS_COMPLETED = "COMPLETED";
	public static final String STATUS_ERROR = "ERROR";
	//Global-Scheduling
	public static final String BASE_URL = "http://localhost:9191/GlobalScheduling/api";//"http://ec2-54-218-176-8.us-west-2.compute.amazonaws.com:8080/GlobalSchedulingV1.0-0.0.1-SNAPSHOT/api"; // "http://ec2-54-71-105-235.us-west-2.compute.amazonaws.com:8080/GlobalSchedulingV1.0-0.0.1-SNAPSHOT/api";
	//My-Life-Global

	public static final String MYLIFE_GLOBAL_URI = "http://localhost:9090/MyLifeGlobal/api";//"http://ec2-54-218-176-8.us-west-2.compute.amazonaws.com:8080/MyLifeGlobal-0.0.1-SNAPSHOT/api";// "http://ec2-54-71-105-235.us-west-2.compute.amazonaws.com:8080/MyLifeGlobal-0.0.1-SNAPSHOT/api";//"http://34.212.43.230:8080/MyLifeGlobal-0.0.1-SNAPSHOT/api";
	//Hearth-Huddle
	public static final String HH_URI = "http://ec2-35-154-13-63.ap-south-1.compute.amazonaws.com:4002/api";
	public static final String HH_AUTH_TOKEN = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjpudWxsLCJyb2xlX2lkIjpudWxsLCJvcmdfaWQiOm51bGx9.EwwhaYaKZpiU_N0FzU4_hGa2IW7fBRVaQiZESxvp0bA";

}
