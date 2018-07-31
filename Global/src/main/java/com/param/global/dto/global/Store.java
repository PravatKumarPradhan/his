package com.param.global.dto.global;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Store {

	protected Integer id;

	protected String store;
	
	protected Boolean isMainStore;

	public Store(Integer id, String store) {
		super();
		this.id = id;
		this.store = store;
	}

	public Store(Integer id, String store, Boolean isMainStore) {
		super();
		this.id = id;
		this.store = store;
		this.isMainStore = isMainStore;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public Boolean getIsMainStore() {
		return isMainStore;
	}

	public void setIsMainStore(Boolean isMainStore) {
		this.isMainStore = isMainStore;
	}	
}
