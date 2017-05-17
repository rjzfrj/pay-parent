package com.roncoo.pay.permission.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.roncoo.pay.common.core.page.PageBean;
import com.roncoo.pay.common.core.page.PageParam;
import com.roncoo.pay.permission.dao.ZyppPermissionDao;
import com.roncoo.pay.permission.dao.ZyppRolePermissionDao;
import com.roncoo.pay.permission.entity.ZyppPermission;
import com.roncoo.pay.permission.entity.ZyppRolePermission;
import com.roncoo.pay.permission.service.ZyppOperatorRoleService;
import com.roncoo.pay.permission.service.ZyppRolePermissionService;

/**
 * 角色权限service接口实现
 *
 * ：
 * 
 * 
 */
@Service("zyppRolePermissionService")
public class ZyppRolePermissionServiceImpl implements ZyppRolePermissionService {
	@Autowired
	private ZyppRolePermissionDao zyppRolePermissionDao;

	@Autowired
	private ZyppPermissionDao zyppPermissionDao;
	@Autowired
	private ZyppOperatorRoleService zyppOperatorRoleService;

	/**
	 * 根据操作员ID，获取所有的功能权限集
	 * 
	 * @param operatorId
	 */
	public Set<String> getPermissionsByOperatorId(Long operatorId) {
		// 根据操作员Id查询出关联的所有角色id
		String roleIds = zyppOperatorRoleService.getRoleIdsByOperatorId(operatorId);

		String permissionIds = getActionIdsByRoleIds(roleIds);
		Set<String> permissionSet = new HashSet<String>();

		// 根据角色ID字符串得到该用户的所有权限拼成的字符串
		if (!StringUtils.isEmpty(permissionIds)) {
			List<ZyppPermission> permissions = zyppPermissionDao.findByIds(permissionIds);
			for (ZyppPermission permission : permissions) {
				permissionSet.add(permission.getPermission());
			}
		}
		return permissionSet;
	}

	/**
	 * 根据角色ID集得到所有权限ID集
	 * 
	 * @param roleIds
	 * @return actionIds
	 */
	private String getActionIdsByRoleIds(String roleIds) {
		// 得到角色－权限表中roleiId在ids中的所有关联对象
		List<ZyppRolePermission> listRolePermission = zyppRolePermissionDao.listByRoleIds(roleIds); // 构建StringBuffer
		StringBuffer actionIdsBuf = new StringBuffer("");
		// 拼接字符串
		for (ZyppRolePermission zyppRolePermission : listRolePermission) {
			actionIdsBuf.append(zyppRolePermission.getPermissionId()).append(",");
		}
		String actionIds = actionIdsBuf.toString();
		// 截取字符串
		if (StringUtils.isEmpty(actionIds) && actionIds.length() > 0) {
			actionIds = actionIds.substring(0, actionIds.length() - 1); // 去掉最后一个逗号
		}
		return actionIds;
	}

	// /////////////////////////////下面：基本操作方法///////////////////////////////////////////////

	/**
	 * 创建zyppOperator
	 */
	public void saveData(ZyppRolePermission zyppRolePermission) {
		zyppRolePermissionDao.insert(zyppRolePermission);
	}

	/**
	 * 修改zyppOperator
	 */
	public void updateData(ZyppRolePermission zyppRolePermission) {
		zyppRolePermissionDao.update(zyppRolePermission);
	}

	/**
	 * 根据id获取数据zyppOperator
	 * 
	 * @param id
	 * @return
	 */
	public ZyppRolePermission getDataById(Long id) {
		return zyppRolePermissionDao.getById(id);

	}

	/**
	 * 分页查询zyppOperator
	 * 
	 * @param pageParam
	 * @param ActivityVo
	 *            zyppOperator
	 * @return
	 */
	public PageBean listPage(PageParam pageParam, ZyppRolePermission zyppRolePermission) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		return zyppRolePermissionDao.listPage(pageParam, paramMap);
	}
	
	/**
	 * 保存角色和权限之间的关联关系
	 */
	@Transactional(rollbackFor = Exception.class)
	public void saveRolePermission(Long roleId, String rolePermissionStr){
		// 删除原来的角色与权限关联
		zyppRolePermissionDao.deleteByRoleId(roleId);
		if (!StringUtils.isEmpty(rolePermissionStr)) {
			// 创建新的关联
			String[] permissionIds = rolePermissionStr.split(",");
			for (int i = 0; i < permissionIds.length; i++) {
				Long permissionId = Long.valueOf(permissionIds[i]);
				ZyppRolePermission item = new ZyppRolePermission();
				item.setPermissionId(permissionId);
				item.setRoleId(roleId);
				zyppRolePermissionDao.insert(item);
			}
		}
	}

}
