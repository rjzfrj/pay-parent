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
package com.roncoo.pay.permission.dao;

import java.util.List;

import com.roncoo.pay.permission.entity.ZyppOperator;

/**
 * 操作员dao
 *
 * ：
 * 
 * 
 */
public interface ZyppOperatorDao extends PermissionBaseDao<ZyppOperator> {

	/**
	 * 根据操作员登录名获取操作员信息.
	 * 
	 * @param loginName
	 *            .
	 * @return operator .
	 */
	ZyppOperator findByLoginName(String loginName);

	/**
	 * 根据角色ID找到操作员列表.
	 * 
	 * @param roleId
	 * @return
	 */
	List<ZyppOperator> listByRoleId(Long roleId);
}
