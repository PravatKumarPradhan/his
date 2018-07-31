package com.param.entity.model.inventory;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.param.entity.model.base.BaseEntity;
import com.param.entity.model.master.Screen;
import com.param.entity.model.master.Store;

@Entity(name = "StockTransaction")
@Table(name = "t_stock_transaction", schema = "inventory")
public class StockTransaction extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long id;

	private Double cop;

	@Column(name = "current_bal")
	private Integer currentBal;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "doc_id")
	private Screen doc;

	@Column(name = "doc_type", length = 50)
	private String docType;

	@Column(name = "lending_rate")
	private Double lendingRate;

	private Integer quantity;

	private Double rate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_id")
	private Store store;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "batch_id")
	private Batch batch;

	public StockTransaction() {
	}
	
	public StockTransaction(Integer currentBal, Screen doc, String docType, Integer quantity, Store store) {
		super();
		this.currentBal = currentBal;
		this.doc = doc;
		this.docType = docType;
		this.quantity = quantity;
		this.store = store;
	}

	public StockTransaction(Integer currentBal, Screen doc, String docType, Integer quantity, Store store, Batch batch) {
		this(currentBal, doc, docType, quantity, store);
		this.batch = batch;
	}

	public StockTransaction(Integer currentBal, Screen doc, String docType, Integer quantity, Double rate, Store store,
			Batch batch) {
		this(currentBal, doc, docType, quantity, store, batch);
		this.rate = rate;
	}

	public StockTransaction(Double cop, Integer currentBal, Screen doc, String docType, Double lendingRate,
			Integer quantity, Double rate, Store store, Batch batch) {
		this(currentBal, doc, docType, quantity, rate, store, batch);
		this.cop = cop;
		this.lendingRate = lendingRate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getCop() {
		return cop;
	}

	public void setCop(double cop) {
		this.cop = cop;
	}

	public Integer getCurrentBal() {
		return currentBal;
	}

	public void setCurrentBal(Integer currentBal) {
		this.currentBal = currentBal;
	}

	public Screen getDoc() {
		return doc;
	}

	public void setDoc(Screen doc) {
		this.doc = doc;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public double getLendingRate() {
		return lendingRate;
	}

	public void setLendingRate(double lendingRate) {
		this.lendingRate = lendingRate;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

}