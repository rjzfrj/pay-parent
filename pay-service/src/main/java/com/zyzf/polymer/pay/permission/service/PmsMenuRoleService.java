package com.zyzf.polymer.pay.permission.service;

/**
 * 菜单角色service接口
 *
 * ：
 * 
 * 
 */
public interface PmsMenuRoleService {

	/**
	 * 根据角色ID统计关联到此角色的菜单数.
	 * 
	 * @param roleId
	 *            角色ID.
	 * @return count.
	 */
	public int countMenuByRoleId(Long roleId);

	/**
	 * 根据角色id，删除该角色关联的所有菜单权限
	 * 
	 * @param roleId
	 */
	public void deleteByRoleId(Long roleId);

	public void saveRoleMenu(Long roleId, String roleMenuStr);

}
