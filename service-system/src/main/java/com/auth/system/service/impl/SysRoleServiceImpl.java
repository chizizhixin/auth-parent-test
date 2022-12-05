package com.auth.system.service.impl;

import com.auth.model.system.SysRole;
import com.auth.model.system.SysUserRole;
import com.auth.model.vo.AssginRoleVo;
import com.auth.model.vo.SysRoleQueryVo;
import com.auth.system.mapper.SysRoleMapper;
import com.auth.system.mapper.SysUserRoleMapper;
import com.auth.system.service.SysRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	@Override
	public Map<String, Object> getRolesByUserId(String userId) {
		//获取所有的角色
		List<SysRole> roles = baseMapper.selectList(null);
       //根据用户id查询
		QueryWrapper<SysUserRole> wrapper = new QueryWrapper<>();
		wrapper.eq("user_id",userId);
		List<SysUserRole> userRoles = sysUserRoleMapper.selectList(wrapper);
        //从userRoles集合里面获取角色id
		List<String> userRoleIds = new ArrayList<>();
		for (SysUserRole userRole:userRoles){
			String roleId = userRole.getRoleId();
            userRoleIds.add(roleId);
		}
		Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("allRoles",roles);//所有角色
		hashMap.put("userRoleIds",userRoleIds);//用户已经分配的map集合
		return hashMap;
	}

	@Override
	public void doAssign(AssginRoleVo assginRoleVo) {
		//先根据用户id删除之前分配的角色信息
		QueryWrapper<SysUserRole> wrapper = new QueryWrapper<>();
		wrapper.eq("user_id",assginRoleVo.getUserId());
		sysUserRoleMapper.delete(wrapper);

		//获取所有的角色id，添加到角色用户关系表
		List<String> roleIdList = assginRoleVo.getRoleIdList();
		for (String roleId:roleIdList){
			SysUserRole userRole = new SysUserRole();
			userRole.setUserId(assginRoleVo.getUserId());
			userRole.setRoleId(roleId);
			sysUserRoleMapper.insert(userRole);
		}

	}

	//条件分页查询
	@Override
	public IPage<SysRole> selectPage(Page<SysRole> pageParam, SysRoleQueryVo sysRoleQueryVo) {
		IPage<SysRole> pageModel =  baseMapper.selectPage(pageParam,sysRoleQueryVo);
		return pageModel;
	}
}
