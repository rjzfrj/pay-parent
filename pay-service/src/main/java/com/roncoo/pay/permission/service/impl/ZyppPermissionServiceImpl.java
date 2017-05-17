package com.roncoo.pay.permission.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.roncoo.pay.common.core.page.PageBean;
import com.roncoo.pay.common.core.page.PageParam;
import com.roncoo.pay.permission.dao.ZyppPermissionDao;
import com.roncoo.pay.permission.dao.ZyppRolePermissionDao;
import com.roncoo.pay.permission.entity.ZyppPermission;
import com.roncoo.pay.permission.entity.ZyppRolePermission;
import com.roncoo.pay.permission.service.ZyppPermissionService;

/**
 * 权限service接口实现
 *
 * ：
 * 
 * 
 */
@Service("zyppPermissionService")
public class ZyppPermissionServiceImpl implements ZyppPermissionService {
	@Autowired
	private ZyppPermissionDao zyppPermissionDao;
	@Autowired
	private ZyppRolePermissionDao zyppRolePermissionDao;

	/**
	 * 创建zyppOperator
	 */
	public void saveData(ZyppPermission zyppPermission) {
		zyppPermissionDao.insert(zyppPermission);
	}

	/**
	 * 修改zyppOperator
	 */
	public void updateData(ZyppPermission zyppPermission) {
		zyppPermissionDao.update(zyppPermission);
	}

	/**
	 * 根据id获取数据zyppOperator
	 * 
	 * @param id
	 * @return
	 */
	public ZyppPermission getDataById(Long id) {
		return zyppPermissionDao.getById(id);

	}

	/**
	 * 分页查询zyppOperator
	 * 
	 * @param pageParam
	 * @param ActivityVo
	 *            zyppOperator
	 * @return
	 */
	public PageBean listPage(PageParam pageParam, ZyppPermission zyppPermission) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("permissionName", zyppPermission.getPermissionName()); // 权限名称（模糊查询）
		paramMap.put("permission", zyppPermission.getPermission()); // 权限（精确查询）
		return zyppPermissionDao.listPage(pageParam, paramMap);
	}

	/**
	 * 检查权限名称是否已存在
	 * 
	 * @param trim
	 * @return
	 */
	public ZyppPermission getByPermissionName(String permissionName) {
		return zyppPermissionDao.getByPermissionName(permissionName);
	}

	/**
	 * 检查权限是否已存在
	 * 
	 * @param permission
	 * @return
	 */
	public ZyppPermission getByPermission(String permission) {
		return zyppPermissionDao.getByPermission(permission);
	}

	/**
	 * 检查权限名称是否已存在(其他id)
	 * 
	 * @param permissionName
	 * @param id
	 * @return
	 */
	public ZyppPermission getByPermissionNameNotEqId(String permissionName, Long id) {
		return zyppPermissionDao.getByPermissionNameNotEqId(permissionName, id);
	}

	/**
	 * zyppPermissionDao 删除
	 * 
	 * @param permission
	 */
	public void delete(Long permissionId) {
		zyppPermissionDao.delete(permissionId);
	}

	/**
	 * 根据角色查找角色对应的功能权限ID集
	 * 
	 * @param roleId
	 * @return
	 */
	public String getPermissionIdsByRoleId(Long roleId) {
		List<ZyppRolePermission> rmList = zyppRolePermissionDao.listByRoleId(roleId);
		StringBuffer actionIds = new StringBuffer();
		if (rmList != null && !rmList.isEmpty()) {
			for (ZyppRolePermission rm : rmList) {
				actionIds.append(rm.getPermissionId()).append(",");
			}
		}
		return actionIds.toString();
	}

	/**
	 * 查询所有的权限
	 */
	public List<ZyppPermission> listAll() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		return zyppPermissionDao.listBy(paramMap);
	}
}
