package com.roncoo.pay.permission.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.roncoo.pay.common.core.page.PageBean;
import com.roncoo.pay.common.core.page.PageParam;
import com.roncoo.pay.permission.dao.ZyppRoleDao;
import com.roncoo.pay.permission.entity.ZyppRole;
import com.roncoo.pay.permission.service.ZyppRoleService;

/**
 * 角色service接口实现
 *
 * ：
 * 
 * 
 */
@Service("zyppRoleService")
public class ZyppRoleServiceImpl implements ZyppRoleService {
	@Autowired
	private ZyppRoleDao zyppRoleDao;

	/**
	 * 创建zyppOperator
	 */
	public void saveData(ZyppRole zyppRole) {
		zyppRoleDao.insert(zyppRole);
	}

	/**
	 * 修改zyppOperator
	 */
	public void updateData(ZyppRole zyppRole) {
		zyppRoleDao.update(zyppRole);
	}

	/**
	 * 根据id获取数据zyppOperator
	 * 
	 * @param id
	 * @return
	 */
	public ZyppRole getDataById(Long id) {
		return zyppRoleDao.getById(id);

	}

	/**
	 * 分页查询zyppOperator
	 * 
	 * @param pageParam
	 * @param ActivityVo
	 *            zyppOperator
	 * @return
	 */
	public PageBean listPage(PageParam pageParam, ZyppRole zyppRole) {
		Map<String, Object> paramMap = new HashMap<String, Object>(); // 业务条件查询参数
		paramMap.put("roleName", zyppRole.getRoleName()); // 角色名称（模糊查询）
		return zyppRoleDao.listPage(pageParam, paramMap);
	}

	/**
	 * 获取所有角色列表，以供添加操作员时选择.
	 * 
	 * @return roleList .
	 */
	public List<ZyppRole> listAllRole() {
		return zyppRoleDao.listAll();
	}

	/**
	 * 判断此权限是否关联有角色
	 * 
	 * @param permissionId
	 * @return
	 */
	public List<ZyppRole> listByPermissionId(Long permissionId) {
		return zyppRoleDao.listByPermissionId(permissionId);
	}

	/**
	 * 根据角色名或者角色编号查询角色
	 * 
	 * @param roleName
	 * @param roleCode
	 * @return
	 */
	public ZyppRole getByRoleNameOrRoleCode(String roleName, String roleCode) {
		return zyppRoleDao.getByRoleNameOrRoleCode(roleName, roleCode);
	}

	/**
	 * 删除
	 * 
	 * @param roleId
	 */
	public void delete(Long roleId) {
		zyppRoleDao.delete(roleId);
	}
}
