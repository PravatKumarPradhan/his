package com.param.global.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="t_order_status_master",schema="public")
public class OrderStatusMaster 
{
	@Id 
	@Column(name="order_status_id")
	 private int orderStatusId;
	 
	 @Column(name="order_code")
	 private char orderCode;
	  
	 @Column(name="order_desc")
	 private char orderDesc;
	 
	 
}
