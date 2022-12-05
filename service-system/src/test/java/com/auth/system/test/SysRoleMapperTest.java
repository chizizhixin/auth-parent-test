package com.auth.system.test;

import com.auth.model.system.SysRole;
import com.auth.system.mapper.SysRoleMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class SysRoleMapperTest {


	@Autowired
	private SysRoleMapper sysRoleMapper;

	//1.查询所有记录
	@Test
	public void testFind(){
		List<SysRole> sysRoles = sysRoleMapper.selectList(null);
		sysRoles.forEach(System.out::println);
	}

	//2.添加操作
	@Test
	public void testAdd(){
		SysRole sysRole = new SysRole();
		sysRole.setRoleName("测试角色");
		sysRole.setRoleCode("testManager");
		sysRole.setDescription("测试角色");
		int insert = sysRoleMapper.insert(sysRole);
		System.out.println(insert);
	}
    //3.修改操作
	@Test
	public void testUpdate(){
		SysRole selectById = sysRoleMapper.selectById(1);
		selectById.setDescription("潘鹏");
		sysRoleMapper.updateById(selectById);
	}

	//4.删除操作  逻辑删除 设置删除标记位，通过判断逻辑来实现无法查询删除  （好处：方便删除后的数据恢复）
	//4.1 根据id删除
	@Test
	public void testDeleteById(){
		int deleteById = sysRoleMapper.deleteById(9);
	}
	//4.3 批量删除
	@Test
	public void testDelete(){
		int i = sysRoleMapper.deleteBatchIds(Arrays.asList(1, 2));
	}

	//条件构造器
	//6.条件查询
	@Test
	public void testSelect(){
        //创建条件构造器
		QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        //设置条件
//		wrapper.eq("role_name","用户管理员");、
		wrapper.like("role_name","管理员");
        //调用方法查询
		List<SysRole> list = sysRoleMapper.selectList(wrapper);
		System.out.println(list);
	}

	//7.条件删除
	@Test
	public void testDeleteQuery(){
		QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
		wrapper.eq("role_name","用户管理员");
		sysRoleMapper.delete(wrapper);
	}


}
