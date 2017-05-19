package com.zyzf.polymer.pay.permission.dao;

import java.util.List;

import com.zyzf.polymer.pay.permission.entity.PmsOperator;

/**
 * 操作员dao
 *
 * ：
 * 
 * 
 */
public interface PmsOperatorDao extends PermissionBaseDao<PmsOperator> {

	/**
	 * 根据操作员登录名获取操作员信息.
	 * 
	 * @param loginName
	 *            .
	 * @return operator .
	 */
	PmsOperator findByLoginName(String loginName);

	/**
	 * 根据角色ID找到操作员列表.
	 * 
	 * @param roleId
	 * @return
	 */
	List<PmsOperator> listByRoleId(Long roleId);
}
