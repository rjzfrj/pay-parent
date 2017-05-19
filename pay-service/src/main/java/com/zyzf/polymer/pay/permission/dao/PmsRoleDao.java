package com.zyzf.polymer.pay.permission.dao;

import java.util.List;

import com.zyzf.polymer.pay.permission.entity.PmsRole;

/**
 * 权限角色dao
 *
 * ：
 * 
 * 
 */
public interface PmsRoleDao extends PermissionBaseDao<PmsRole> {

	/**
	 * 获取所有角色列表，以供添加操作员时选择.
	 * 
	 * @return roleList .
	 */
	public List<PmsRole> listAll();

	/**
	 * 判断此权限是否关联有角色
	 * 
	 * @param permissionId
	 * @return
	 */
	public List<PmsRole> listByPermissionId(Long permissionId);

	/**
	 * 根据角色名或者角色编号查询角色
	 * 
	 * @param roleName
	 * @param roleCode
	 * @return
	 */
	public PmsRole getByRoleNameOrRoleCode(String roleName, String roleCode);

}
