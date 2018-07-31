package com.param.entity.model.master;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.param.entity.model.base.BaseEntity;
import com.param.entity.model.procurement.ShelfItem;

@Entity
@Table(name = "m_shelf", schema = "public")
public class Shelf extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;

	@Column(nullable = false, length = 10)
	private String code;

	@Column(nullable = false, length = 500)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rack_id")
	private Rack rack;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "shelf")
	private List<ShelfItem> shelfList;

	public Shelf() {
	}

	public Shelf(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Rack getRack() {
		return rack;
	}

	public void setRack(Rack rack) {
		this.rack = rack;
	}

	public List<ShelfItem> getShelfItem() {
		return shelfList;
	}

	public void setShelfItem(List<ShelfItem> shelfItem) {
		this.shelfList = shelfItem;
	}

	public Shelf(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	public Shelf(Integer id, String code, String name) {
		this.id=id;
		this.code = code;
		this.name = name;
	}


	public void updateShelf(Integer id,String code, String name) {
		this.id=id;
		this.code = code;
		this.name = name;
	}

}
