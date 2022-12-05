package com.auth.system.service.impl;

import com.auth.model.system.SysUser;
import com.auth.system.custom.CustomUser;
import com.auth.system.service.SysMenuService;
import com.auth.system.service.SysUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private SysMenuService sysMenuService;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUser sysUser = sysUserService.getUserInfoByUserName(username);
		if(null == sysUser) {
			throw new UsernameNotFoundException("用户名不存在！");
		}

		if(sysUser.getStatus().intValue() == 0) {
			throw new RuntimeException("账号已停用");
		}
		//根据用户id查询操权限数据
		List<String> userButtonList = sysMenuService.getUserButtonList(sysUser.getId());
		//转换成security要求的格式数据
		ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (String perm:userButtonList){
			authorities.add(new SimpleGrantedAuthority(perm.trim()));
		}
		return new CustomUser(sysUser, authorities);
	}
}