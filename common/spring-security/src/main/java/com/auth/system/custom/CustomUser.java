package com.auth.system.custom;

import com.auth.model.system.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

//自定义用户信息
public class CustomUser extends User {
	/**
	 * 我们自己的用户实体对象，要调取用户信息时直接获取这个实体对象
	 */
	private SysUser sysUser;

	public CustomUser(SysUser sysUser, Collection<? extends GrantedAuthority> authorities) {
		super(sysUser.getUsername(), sysUser.getPassword(), authorities);
		this.sysUser = sysUser;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}
}
