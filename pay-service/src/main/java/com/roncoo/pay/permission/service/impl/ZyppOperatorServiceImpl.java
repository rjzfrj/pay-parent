package com.roncoo.pay.permission.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.roncoo.pay.common.core.enums.PublicStatusEnum;
import com.roncoo.pay.common.core.page.PageBean;
import com.roncoo.pay.common.core.page.PageParam;
import com.roncoo.pay.permission.dao.ZyppOperatorDao;
import com.roncoo.pay.permission.dao.ZyppOperatorRoleDao;
import com.roncoo.pay.permission.entity.ZyppOperator;
import com.roncoo.pay.permission.entity.ZyppOperatorRole;
import com.roncoo.pay.permission.service.ZyppOperatorService;

/**
 * 操作员service接口实现
 *
 * ：
 * 
 * 
 */
@Service("zyppOperatorService")
public class ZyppOperatorServiceImpl implements ZyppOperatorService {
	@Autowired
	private ZyppOperatorDao zyppOperatorDao;

	@Autowired
	private ZyppOperatorRoleDao zyppOperatorRoleDao;

	/**
	 * 创建zyppOperator
	 */
	public void saveData(ZyppOperator zyppOperator) {
		zyppOperatorDao.insert(zyppOperator);
	}

	/**
	 * 修改zyppOperator
	 */
	public void updateData(ZyppOperator zyppOperator) {
		zyppOperatorDao.update(zyppOperator);
	}

	/**
	 * 根据id获取数据zyppOperator
	 * 
	 * @param id
	 * @return
	 */
	public ZyppOperator getDataById(Long id) {
		return zyppOperatorDao.getById(id);

	}

	/**
	 * 分页查询zyppOperator
	 * 
	 * @param pageParam
	 * @param ActivityVo
	 *            zyppOperator
	 * @return
	 */
	public PageBean listPage(PageParam pageParam, ZyppOperator zyppOperator) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loginName", zyppOperator.getLoginName()); // 操作员登录名（精确查询）
		paramMap.put("realName", zyppOperator.getRealName()); // 操作员姓名（模糊查询）
		paramMap.put("status", zyppOperator.getStatus()); // 状态

		return zyppOperatorDao.listPage(pageParam, paramMap);
	}

	/**
	 * 根据ID删除一个操作员，同时删除与该操作员关联的角色关联信息. type="1"的超级管理员不能删除.
	 * 
	 * @param id
	 *            操作员ID.
	 */
	public void deleteOperatorById(Long operatorId) {
		ZyppOperator zyppOperator = zyppOperatorDao.getById(operatorId);
		if (zyppOperator != null) {
			if ("admin".equals(zyppOperator.getType())) {
				throw new RuntimeException("【" + zyppOperator.getLoginName() + "】为超级管理员，不能删除！");
			}
			zyppOperatorDao.delete(operatorId);
			// 删除原来的角色与操作员关联
			zyppOperatorRoleDao.deleteByOperatorId(operatorId);
		}
	}

	/**
	 * 更新操作员信息.
	 * 
	 * @param operator
	 */
	public void update(ZyppOperator operator) {
		zyppOperatorDao.update(operator);

	}

	/**
	 * 根据操作员ID更新操作员密码.
	 * 
	 * @param operatorId
	 * @param newPwd
	 *            (已进行SHA1加密)
	 */
	public void updateOperatorPwd(Long operatorId, String newPwd) {
		ZyppOperator zyppOperator = zyppOperatorDao.getById(operatorId);
		zyppOperator.setLoginPwd(newPwd);
		zyppOperatorDao.update(zyppOperator);
	}

	/**
	 * 根据登录名取得操作员对象
	 */
	public ZyppOperator findOperatorByLoginName(String loginName) {
		return zyppOperatorDao.findByLoginName(loginName);
	}

	/**
	 * 保存操作員信息及其关联的角色.
	 * 
	 * @param zyppOperator
	 *            .
	 * @param roleOperatorStr
	 *            .
	 */

	@Transactional
	public void saveOperator(ZyppOperator zyppOperator, String roleOperatorStr) {
		// 保存操作员信息
		zyppOperatorDao.insert(zyppOperator);
		// 保存角色关联信息
		if (StringUtils.isNotBlank(roleOperatorStr) && roleOperatorStr.length() > 0) {
			saveOrUpdateOperatorRole(zyppOperator, roleOperatorStr);
		}
	}

	/**
	 * 保存用户和角色之间的关联关系
	 */
	private void saveOrUpdateOperatorRole(ZyppOperator zyppOperator, String roleIdsStr) {
		// 删除原来的角色与操作员关联
		List<ZyppOperatorRole> listzyppOperatorRoles = zyppOperatorRoleDao.listByOperatorId(zyppOperator.getId());
		Map<Long, ZyppOperatorRole> delMap = new HashMap<Long, ZyppOperatorRole>();
		for (ZyppOperatorRole zyppOperatorRole : listzyppOperatorRoles) {
			delMap.put(zyppOperatorRole.getRoleId(), zyppOperatorRole);
		}
		if (StringUtils.isNotBlank(roleIdsStr)) {
			// 创建新的关联
			String[] roleIds = roleIdsStr.split(",");
			for (int i = 0; i < roleIds.length; i++) {
				long roleId = Long.parseLong(roleIds[i]);
				if (delMap.get(roleId) == null) {
					ZyppOperatorRole zyppOperatorRole = new ZyppOperatorRole();
					zyppOperatorRole.setOperatorId(zyppOperator.getId());
					zyppOperatorRole.setRoleId(roleId);
					zyppOperatorRole.setCreater(zyppOperator.getCreater());
					zyppOperatorRole.setCreateTime(new Date());
					zyppOperatorRole.setStatus(PublicStatusEnum.ACTIVE.name());
					zyppOperatorRoleDao.insert(zyppOperatorRole);
				} else {
					delMap.remove(roleId);
				}
			}
		}

		Iterator<Long> iterator = delMap.keySet().iterator();
		while (iterator.hasNext()) {
			long roleId = iterator.next();
			zyppOperatorRoleDao.deleteByRoleIdAndOperatorId(roleId, zyppOperator.getId());
		}
	}

	/**
	 * 修改操作員信息及其关联的角色.
	 * 
	 * @param zyppOperator
	 *            .
	 * @param roleOperatorStr
	 *            .
	 */
	public void updateOperator(ZyppOperator zyppOperator, String roleOperatorStr) {
		zyppOperatorDao.update(zyppOperator);
		// 更新角色信息
		this.saveOrUpdateOperatorRole(zyppOperator, roleOperatorStr);
	}

}
