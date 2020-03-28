package com.demo.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "jum_heirarchy")
public class Heirarchy implements Serializable {
	@Id
	@Column(name = "heirarchy_id")
	private Long heirarchyId;

	@Column(name = "heirarchy")
	private Long heirarchy ;

	public Long getHeirarchyId() {
		return heirarchyId;
	}

	public void setHeirarchyId(Long heirarchyId) {
		this.heirarchyId = heirarchyId;
	}

	public Long getHeirarchy() {
		return heirarchy;
	}

	public void setHeirarchy(Long heirarchy) {
		this.heirarchy = heirarchy;
	}

	


}