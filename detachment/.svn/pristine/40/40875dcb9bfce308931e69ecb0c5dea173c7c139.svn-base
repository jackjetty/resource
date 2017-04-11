package com.detachment.pojo;

/**
 * TbRole2menuId entity. @author MyEclipse Persistence Tools
 */

public class TbRole2menuId implements java.io.Serializable {

	// Fields

	private TbRole tbRole;
	private TbMenu tbMenu;

	// Constructors

	/** default constructor */
	public TbRole2menuId() {
	}

	/** full constructor */
	public TbRole2menuId(TbRole tbRole, TbMenu tbMenu) {
		this.tbRole = tbRole;
		this.tbMenu = tbMenu;
	}

	// Property accessors

	public TbRole getTbRole() {
		return this.tbRole;
	}

	public void setTbRole(TbRole tbRole) {
		this.tbRole = tbRole;
	}

	public TbMenu getTbMenu() {
		return this.tbMenu;
	}

	public void setTbMenu(TbMenu tbMenu) {
		this.tbMenu = tbMenu;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TbRole2menuId))
			return false;
		TbRole2menuId castOther = (TbRole2menuId) other;

		return ((this.getTbRole() == castOther.getTbRole()) || (this
				.getTbRole() != null
				&& castOther.getTbRole() != null && this.getTbRole().equals(
				castOther.getTbRole())))
				&& ((this.getTbMenu() == castOther.getTbMenu()) || (this
						.getTbMenu() != null
						&& castOther.getTbMenu() != null && this.getTbMenu()
						.equals(castOther.getTbMenu())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getTbRole() == null ? 0 : this.getTbRole().hashCode());
		result = 37 * result
				+ (getTbMenu() == null ? 0 : this.getTbMenu().hashCode());
		return result;
	}

}