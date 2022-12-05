package com.auth.system.service;

import com.auth.model.system.SysUser;
import com.auth.model.vo.SysRoleQueryVo;
import com.auth.model.vo.SysUserQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author chizizhixin
 * @since 2022-11-27
 */
public interface SysUserService extends IService<SysUser> {


	IPage<SysUser> selectPage(Page<SysUser> pageParam, SysUserQueryVo sysUserQueryVo);

	void updateStatus(String id, Integer status);

	SysUser getUserInfoByUserName(String username);

	Map<String, Object> getUserInfo(String username);

}
