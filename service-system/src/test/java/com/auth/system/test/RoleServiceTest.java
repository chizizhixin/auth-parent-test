package com.auth.system.test;

import com.auth.model.system.SysRole;
import com.auth.system.service.SysRoleService;
import com.auth.system.utils.JwtHelper;
import com.auth.system.utils.Md5Util;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class RoleServiceTest {

	@Autowired
	private SysRoleService service;

	@Test
	public void findAll(){
		//service方法实现
		List<SysRole> list = service.list();
		System.out.println(list);
	}

	@Test
	public void add(){
		SysRole sysRole = new SysRole();
		sysRole.setRoleName("潘鹏");
		sysRole.setRoleCode("panpeng");
		boolean save = service.save(sysRole);
		System.out.println(save);
	}

	@Test
	public void update(){

		SysRole byId = service.getById(1);
		byId.setDescription("test");
		boolean b = service.updateById(byId);
		System.out.println(b);
	}

	@Test
	public void Delete(){
		boolean removeById = service.removeById(2);
		System.out.println(removeById);
	}

	@Test
	public void select(){
		QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
		wrapper.eq("role_code","panpeng2");
		List<SysRole> list = service.list(wrapper);
		System.out.println(list);
	}

	@Test
	public void Jwt(){
		String token = JwtHelper.createToken("1", "admin");
		System.out.println(token);
	}
	@Test
	public void Md5(){
		String s = Md5Util.md5("123456");
		System.out.println(s);
	}

}
