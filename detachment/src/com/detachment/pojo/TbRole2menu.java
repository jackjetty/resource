package com.detachment.pojo;

/**
 * TbRole2menu entity. @author MyEclipse Persistence Tools
 */

public class TbRole2menu implements java.io.Serializable {

	// Fields

	private TbRole2menuId id;
	private Boolean valid;

	// Constructors

	/** default constructor */
	public TbRole2menu() {
	}

	/** minimal constructor */
	public TbRole2menu(TbRole2menuId id) {
		this.id = id;
	}

	/** full constructor */
	public TbRole2menu(TbRole2menuId id, Boolean valid) {
		this.id = id;
		this.valid = valid;
	}

	// Property accessors

	public TbRole2menuId getId() {
		return this.id;
	}

	public void setId(TbRole2menuId id) {
		this.id = id;
	}

	public Boolean getValid() {
		return this.valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

}