package com.roncoo.pay.permission.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.roncoo.pay.permission.dao.ZyppMenuDao;
import com.roncoo.pay.permission.dao.ZyppMenuRoleDao;
import com.roncoo.pay.permission.entity.ZyppMenu;
import com.roncoo.pay.permission.entity.ZyppMenuRole;
import com.roncoo.pay.permission.service.ZyppMenuService;

/**
 * 菜单service接口实现
 *
 * ：
 * 
 * 
 */
@Service("zyppMenuService")
public class ZyppMenuServiceImpl implements ZyppMenuService {

	@Autowired
	private ZyppMenuDao zyppMenuDao;
	@Autowired
	private  ZyppMenuRoleDao zyppMenuRoleDao;

	/**
	 * 保存菜单zyppMenuDao
	 * 
	 * @param menu
	 */
	public void savaMenu(ZyppMenu menu) {
		zyppMenuDao.insert(menu);
	}

	/**
	 * 根据父菜单ID获取该菜单下的所有子孙菜单.<br/>
	 * 
	 * @param parentId
	 *            (如果为空，则为获取所有的菜单).<br/>
	 * @return menuList.
	 */
	@SuppressWarnings("rawtypes")
	public List getListByParent(Long parentId) {
		return zyppMenuDao.listByParent(parentId);
	}

	/**
	 * 根据id删除菜单
	 */
	public void delete(Long id) {
		this.zyppMenuDao.delete(id);
	}

	/**
	 * 根据角色id串获取菜单
	 * 
	 * @param roleIdsStr
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List listByRoleIds(String roleIdsStr) {
		return this.zyppMenuDao.listByRoleIds(roleIdsStr);
	}

	/**
	 * 根据菜单ID查找菜单（可用于判断菜单下是否还有子菜单）.
	 * 
	 * @param parentId
	 *            .
	 * @return menuList.
	 */
	public List<ZyppMenu> listByParentId(Long parentId) {
		return zyppMenuDao.listByParentId(parentId);
	}

	/***
	 * 根据名称和是否叶子节点查询数据
	 * 
	 * @param isLeaf
	 *            是否是叶子节点
	 * @param name
	 *            节点名称
	 * @return
	 */
	public List<ZyppMenu> getMenuByNameAndIsLeaf(Map<String, Object> map) {
		return zyppMenuDao.getMenuByNameAndIsLeaf(map);
	}

	/**
	 * 根据菜单ID获取菜单.
	 * 
	 * @param pid
	 * @return
	 */
	public ZyppMenu getById(Long pid) {
		return zyppMenuDao.getById(pid);
	}

	/**
	 * 更新菜单.
	 * 
	 * @param menu
	 */
	public void update(ZyppMenu menu) {
		zyppMenuDao.update(menu);

	}

	/**
	 * 根据角色查找角色对应的菜单ID集
	 * 
	 * @param roleId
	 * @return
	 */
	public String getMenuIdsByRoleId(Long roleId) {
		List<ZyppMenuRole> menuList = zyppMenuRoleDao.listByRoleId(roleId);
		StringBuffer menuIds = new StringBuffer("");
		if (menuList != null && !menuList.isEmpty()) {
			for (ZyppMenuRole rm : menuList) {
				menuIds.append(rm.getMenuId()).append(",");
			}
		}
		return menuIds.toString();

	}
}
