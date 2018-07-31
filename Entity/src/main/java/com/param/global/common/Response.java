package com.param.global.common;

import java.io.Serializable;
import java.util.List;

public class Response<T, ID extends Serializable>{
	private Class<T> entityClass;

	protected Class<T> getEntityClass() {
		return entityClass;
	}
	
	public Response(final Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	
	private String status;
	private String code;
	private String message;
	private List<T> listObject;
	private Object object;
	private String exceptionMessage;
	
	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the listObject
	 */
	public List<T> getListObject() {
		return listObject;
	}
	/**
	 * @param listObject the listObject to set
	 */
	public void setListObject(List<T> listObject) {
		this.listObject = listObject;
	}
	
	public Response(String status,String code, String message,
			List<T> listObject, Object object) {
		this.status = status;
		this.message = message;
		this.code = code;
		this.listObject = listObject;
		this.object=object;
	}

	public Response(String status,String code, String message,
			List<T> listObject, Object object, String exceptionMessage) {
		this.status = status;
		this.message = message;
		this.code = code;
		this.listObject = listObject;
		this.object=object;
		this.exceptionMessage = exceptionMessage;
	}
}
