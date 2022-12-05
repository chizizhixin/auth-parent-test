package com.auth.system.service;

import com.auth.model.system.SysRole;
import com.auth.model.vo.AssginRoleVo;
import com.auth.model.vo.SysRoleQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

public interface SysRoleService extends IService<SysRole> {
	/**
	 * 条件分页查询
	 * @param pageParam
	 * @param sysRoleQueryVo
	 * @return
	 */
	IPage<SysRole> selectPage(Page<SysRole> pageParam, SysRoleQueryVo sysRoleQueryVo);

	/**
	 * 获取用户角色数据
	 * @param userId
	 * @return
	 */
	Map<String, Object> getRolesByUserId(String userId);

	/**
	 * 给用户重新分配角色
	 * @param assginRoleVo
	 */
	void doAssign(AssginRoleVo assginRoleVo);
}
