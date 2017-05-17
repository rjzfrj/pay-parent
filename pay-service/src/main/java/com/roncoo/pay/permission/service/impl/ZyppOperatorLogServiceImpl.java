package com.roncoo.pay.permission.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.roncoo.pay.common.core.page.PageBean;
import com.roncoo.pay.common.core.page.PageParam;
import com.roncoo.pay.permission.dao.ZyppOperatorLogDao;
import com.roncoo.pay.permission.entity.ZyppOperatorLog;
import com.roncoo.pay.permission.service.ZyppOperatorLogService;

/**
 * 操作员service接口实现
 *
 * ：
 * 
 * 
 */
@Service("zyppOperatorLogService")
public class ZyppOperatorLogServiceImpl implements ZyppOperatorLogService {
	@Autowired
	private ZyppOperatorLogDao zyppOperatorLogDao;

	/**
	 * 创建zyppOperator
	 */
	public void saveData(ZyppOperatorLog zyppOperatorLog) {
		zyppOperatorLogDao.insert(zyppOperatorLog);
	}

	/**
	 * 修改zyppOperator
	 */
	public void updateData(ZyppOperatorLog zyppOperatorLog) {
		zyppOperatorLogDao.update(zyppOperatorLog);
	}

	/**
	 * 根据id获取数据zyppOperator
	 * 
	 * @param id
	 * @return
	 */
	public ZyppOperatorLog getDataById(Long id) {
		return zyppOperatorLogDao.getById(id);

	}

	/**
	 * 分页查询zyppOperator
	 * 
	 * @param pageParam
	 * @param ActivityVo
	 *            zyppOperator
	 * @return
	 */
	public PageBean listPage(PageParam pageParam, ZyppOperatorLog zyppOperatorLog) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		return zyppOperatorLogDao.listPage(pageParam, paramMap);
	}

}
