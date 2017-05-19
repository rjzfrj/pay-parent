package com.zyzf.polymer.pay.permission.dao;

import java.util.List;

import com.zyzf.polymer.pay.permission.entity.PmsRolePermission;

/**
 * 角色权限dao
 *
 * 
 * 
 */
public interface PmsRolePermissionDao extends PermissionBaseDao<PmsRolePermission> {

	/**
	 * 根据角色ID找到角色关联的权限点.
	 * 
	 * @param roleId
	 *            .
	 * @return rolePermissionList .
	 */
	public List<PmsRolePermission> listByRoleId(final long roleId);

	/**
	 * 根据角色ID字符串获取相应角色-权限关联信息.
	 * 
	 * @param roleIds
	 * @return
	 */
	public List<PmsRolePermission> listByRoleIds(String roleIdsStr);

	public void deleteByRoleIdAndPermissionId(Long roleId, Long permissionId);
	
	public void deleteByRoleId(Long roleId);
}
