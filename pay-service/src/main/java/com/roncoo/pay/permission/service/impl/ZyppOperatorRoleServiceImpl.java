package com.roncoo.pay.permission.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.roncoo.pay.permission.dao.ZyppOperatorDao;
import com.roncoo.pay.permission.dao.ZyppOperatorRoleDao;
import com.roncoo.pay.permission.dao.ZyppRoleDao;
import com.roncoo.pay.permission.entity.ZyppOperator;
import com.roncoo.pay.permission.entity.ZyppOperatorRole;
import com.roncoo.pay.permission.entity.ZyppRole;
import com.roncoo.pay.permission.service.ZyppOperatorRoleService;

/**
 * 操作员角色service接口实现
 *
 * ：
 * 
 * 
 */
@Service("zyppOperatorRoleService")
public class ZyppOperatorRoleServiceImpl implements ZyppOperatorRoleService {
	@Autowired
	private ZyppOperatorRoleDao zyppOperatorRoleDao;

	@Autowired
	private ZyppOperatorDao zyppOperatorDao;

	@Autowired
	private ZyppRoleDao zyppRoleDao;

	/**
	 * 根据操作员ID获得该操作员的所有角色id所拼成的String，每个ID用“,”分隔
	 * 
	 * @param operatorId
	 *            操作员ID
	 * @return roleIds
	 */
	public String getRoleIdsByOperatorId(Long operatorId) {
		// 得到操作员和角色列表
		List<ZyppOperatorRole> rpList = zyppOperatorRoleDao.listByOperatorId(operatorId);
		// 构建StringBuffer来拼字符串
		StringBuffer roleIdsBuf = new StringBuffer("");
		for (ZyppOperatorRole rp : rpList) {
			roleIdsBuf.append(rp.getRoleId()).append(",");
		}
		String roleIds = roleIdsBuf.toString();
		// 截取字符串
		if (StringUtils.isNotBlank(roleIds) && roleIds.length() > 0) {
			roleIds = roleIds.substring(0, roleIds.length() - 1);
		}
		return roleIds;
	}

	/**
	 * 根据操作员id，获取该操作员所有角色的编码集合
	 * 
	 * @param operatorId
	 * @return
	 */
	public Set<String> getRoleCodeByOperatorId(Long operatorId) {
		// 得到操作员和角色列表
		List<ZyppOperatorRole> rpList = zyppOperatorRoleDao.listByOperatorId(operatorId);
		Set<String> roleCodeSet = new HashSet<String>();

		for (ZyppOperatorRole rp : rpList) {
			Long roleId = rp.getRoleId();
			ZyppRole role = zyppRoleDao.getById(roleId);
			if (role == null) {
				continue;
			}
			roleCodeSet.add(role.getRoleCode());
		}

		return roleCodeSet;

	}

	/**
	 * 根据角色ID统计有多少个操作员关联到此角色.
	 * 
	 * @param roleId
	 *            .
	 * @return count.
	 */
	public int countOperatorByRoleId(Long roleId) {
		List<ZyppOperatorRole> operatorList = zyppOperatorRoleDao.listByRoleId(roleId);
		if (operatorList == null || operatorList.isEmpty()) {
			return 0;
		} else {
			return operatorList.size();
		}
	}

	/**
	 * 根据操作员ID获得所有操作员－角色关联列表
	 */
	public List<ZyppOperatorRole> listOperatorRoleByOperatorId(Long operatorId) {
		return zyppOperatorRoleDao.listByOperatorId(operatorId);
	}

	/**
	 * 保存操作員信息及其关联的角色.
	 * 
	 * @param zyppOperator
	 *            .
	 * @param OperatorRoleStr
	 *            .
	 */
	public void saveOperator(ZyppOperator zyppOperator, String OperatorRoleStr) {
		// 保存操作员信息
		zyppOperatorDao.insert(zyppOperator);
		// 保存角色关联信息
		if (StringUtils.isNotBlank(OperatorRoleStr) && OperatorRoleStr.length() > 0) {
			saveOrUpdateOperatorRole(zyppOperator.getId(), OperatorRoleStr);
		}
	}

	/**
	 * 根据角色ID查询用户
	 * 
	 * @param roleId
	 * @return
	 */
	public List<ZyppOperator> listOperatorByRoleId(Long roleId) {
		return zyppOperatorDao.listByRoleId(roleId);
	}

	/**
	 * 修改操作員信息及其关联的角色.
	 * 
	 * @param zyppOperator
	 *            .
	 * @param OperatorRoleStr
	 *            .
	 */
	public void updateOperator(ZyppOperator zyppOperator, String OperatorRoleStr) {
		zyppOperatorDao.update(zyppOperator);
		// 更新角色信息
		saveOrUpdateOperatorRole(zyppOperator.getId(), OperatorRoleStr);
	}

	/**
	 * 保存用户和角色之间的关联关系
	 */
	private void saveOrUpdateOperatorRole(Long operatorId, String roleIdsStr) {
		// 删除原来的角色与操作员关联
		List<ZyppOperatorRole> listzyppOperatorRoles = zyppOperatorRoleDao.listByOperatorId(operatorId);
		Map<Long, ZyppOperatorRole> delMap = new HashMap<Long, ZyppOperatorRole>();
		for (ZyppOperatorRole zyppOperatorRole : listzyppOperatorRoles) {
			delMap.put(zyppOperatorRole.getRoleId(), zyppOperatorRole);
		}
		if (StringUtils.isNotBlank(roleIdsStr)) {
			// 创建新的关联
			String[] roleIds = roleIdsStr.split(",");
			for (int i = 0; i < roleIds.length; i++) {
				Long roleId = Long.valueOf(roleIds[i]);
				if (delMap.get(roleId) == null) {
					ZyppOperatorRole zyppOperatorRole = new ZyppOperatorRole();
					zyppOperatorRole.setOperatorId(operatorId);
					zyppOperatorRole.setRoleId(roleId);
					zyppOperatorRoleDao.insert(zyppOperatorRole);
				} else {
					delMap.remove(roleId);
				}
			}
		}

		Iterator<Long> iterator = delMap.keySet().iterator();
		while (iterator.hasNext()) {
			Long roleId = iterator.next();
			zyppOperatorRoleDao.deleteByRoleIdAndOperatorId(roleId, operatorId);
		}
	}

}
