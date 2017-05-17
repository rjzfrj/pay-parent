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

import com.roncoo.pay.permission.entity.ZyppPermission;

/**
 * 权限点dao
 *
 * ：
 * 
 * 
 */
public interface ZyppPermissionDao extends PermissionBaseDao<ZyppPermission> {
	/**
	 * 根据实体ID集字符串获取对象列表.
	 * 
	 * @param ids
	 * @return
	 */
	List<ZyppPermission> findByIds(String ids);

	/**
	 * 检查权限名称是否已存在
	 * 
	 * @param trim
	 * @return
	 */
	ZyppPermission getByPermissionName(String permissionName);

	/**
	 * 检查权限是否已存在
	 * 
	 * @param permission
	 * @return
	 */
	ZyppPermission getByPermission(String permission);

	/**
	 * 检查权限名称是否已存在(其他id)
	 * 
	 * @param permissionName
	 * @param id
	 * @return
	 */
	ZyppPermission getByPermissionNameNotEqId(String permissionName, Long id);

	/**
	 * 获取叶子菜单下所有的功能权限
	 * 
	 * @param valueOf
	 * @return
	 */
	List<ZyppPermission> listAllByMenuId(Long menuId);

}
