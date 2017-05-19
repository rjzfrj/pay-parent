package com.zyzf.polymer.pay.permission.entity;

/**
 * 权限-权限表实体..
 * 
 * ：
 * 
 * 
 */
public class PmsPermission extends PermissionBaseEntity {

	private static final long serialVersionUID = -2175597348886393330L;
	private String permissionName; // 权限名称
	private String permission; // 权限标识

	/**
	 * 权限名称
	 * 
	 * @return
	 */
	public String getPermissionName() {
		return permissionName;
	}

	/**
	 * 权限名称
	 * 
	 * @return
	 */
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	/**
	 * 权限标识
	 * 
	 * @return
	 */
	public String getPermission() {
		return permission;
	}

	/**
	 * 权限标识
	 * 
	 * @return
	 */
	public void setPermission(String permission) {
		this.permission = permission;
	}

}
