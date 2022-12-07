package com.auth.system.service.impl;

import com.auth.model.system.SysMenu;
import com.auth.model.system.SysUser;
import com.auth.model.vo.RouterVo;
import com.auth.model.vo.SysRoleQueryVo;
import com.auth.model.vo.SysUserQueryVo;
import com.auth.system.mapper.SysUserMapper;
import com.auth.system.service.SysMenuService;
import com.auth.system.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author chizizhixin
 * @since 2022-11-27
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

	@Autowired
	private SysMenuService sysMenuService;

	//获取用户信息
	@Override
	public Map<String, Object> getUserInfo(String username) {

		//根据用户名称查询用户基本信息
		SysUser sysUser = this.getUserInfoByUserName(username);
		//根据用户id查询菜单权限值
		List<RouterVo> routerVoList = sysMenuService.getUserMenuList(sysUser.getId());
		//根据userid查询按钮权限值
		List<String> permsList =  sysMenuService.getUserButtonList(sysUser.getId());

		Map<String, Object> map = new HashMap<>();
		map.put("id",sysUser.getId());
		map.put("name",username);
		map.put("roles","[\"admin\"]");
		map.put("avatar","https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg");
		//菜单权限数据
		map.put("routers",routerVoList);
		//按钮权限数据
		map.put("buttons",permsList);
		return map;
	}

	@Override
	public void updateStatus(String id, Integer status) {
		//根据用户id查询数据
		SysUser sysUser = baseMapper.selectById(id);
		//设置修改状态
		sysUser.setStatus(status);
		//调用方法修改
		baseMapper.updateById(sysUser);
	}

	@Override
	public SysUser getUserInfoByUserName(String username) {
		QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
		wrapper.eq("username",username);
		return baseMapper.selectOne(wrapper);
	}

	//用户列表
	@Override
	public IPage<SysUser> selectPage(Page<SysUser> pageParam, SysUserQueryVo sysUserQueryVo) {
		return baseMapper.selectPage(pageParam,sysUserQueryVo);
	}
}
