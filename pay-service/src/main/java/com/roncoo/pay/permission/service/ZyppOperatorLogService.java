/*
 * Copyright 2015-2102 RonCoo(http://) Group.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.roncoo.pay.permission.service;

import com.roncoo.pay.common.core.page.PageBean;
import com.roncoo.pay.common.core.page.PageParam;
import com.roncoo.pay.permission.entity.ZyppOperatorLog;

/**
 * 操作员日志service接口
 *
 * ：
 * 
 * 
 */
public interface ZyppOperatorLogService {

	/**
	 * 创建pmsOperatorLog
	 */
	void saveData(ZyppOperatorLog pmsOperatorLog);

	/**
	 * 修改pmsOperatorLog
	 */
	void updateData(ZyppOperatorLog pmsOperatorLog);

	/**
	 * 根据id获取数据pmsOperatorLog
	 * 
	 * @param id
	 * @return
	 */
	ZyppOperatorLog getDataById(Long id);

	/**
	 * 分页查询pmsOperatorLog
	 * 
	 * @param pageParam
	 * @param ActivityVo
	 *            PmsOperatorLog
	 * @return
	 */
	PageBean listPage(PageParam pageParam, ZyppOperatorLog pmsOperatorLog);

}
