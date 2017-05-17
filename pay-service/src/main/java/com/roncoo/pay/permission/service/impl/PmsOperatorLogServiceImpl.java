package com.roncoo.pay.permission.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.roncoo.pay.common.core.page.PageBean;
import com.roncoo.pay.common.core.page.PageParam;
import com.roncoo.pay.permission.dao.PmsOperatorLogDao;
import com.roncoo.pay.permission.entity.PmsOperatorLog;
import com.roncoo.pay.permission.service.PmsOperatorLogService;

/**
 * 操作员service接口实现
 *
 * ：
 * 
 * 
 */
@Service("pmsOperatorLogService")
public class PmsOperatorLogServiceImpl implements PmsOperatorLogService {
	@Autowired
	private PmsOperatorLogDao zyppOperatorLogDao;

	/**
	 * 创建zyppOperator
	 */
	public void saveData(PmsOperatorLog zyppOperatorLog) {
		zyppOperatorLogDao.insert(zyppOperatorLog);
	}

	/**
	 * 修改zyppOperator
	 */
	public void updateData(PmsOperatorLog zyppOperatorLog) {
		zyppOperatorLogDao.update(zyppOperatorLog);
	}

	/**
	 * 根据id获取数据zyppOperator
	 * 
	 * @param id
	 * @return
	 */
	public PmsOperatorLog getDataById(Long id) {
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
	public PageBean listPage(PageParam pageParam, PmsOperatorLog zyppOperatorLog) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		return zyppOperatorLogDao.listPage(pageParam, paramMap);
	}

}
