package com.auth.system.service.impl;

import com.auth.model.system.SysMenu;
import com.auth.model.system.SysRoleMenu;
import com.auth.model.vo.AssginMenuVo;
import com.auth.model.vo.RouterVo;
import com.auth.system.exception.AuthException;
import com.auth.system.mapper.SysMenuMapper;
import com.auth.system.mapper.SysRoleMenuMapper;
import com.auth.system.result.MenuHelper;
import com.auth.system.result.RouterHelper;
import com.auth.system.service.SysMenuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author chizizhixin
 * @since 2022-11-28
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;


	//根据角色分配菜单
	@Override
	public List<SysMenu> findMenuByRoleId(String roleId) {
        //获取所有的菜单 status=1
		QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
		wrapper.eq("status",1);
		List<SysMenu> menuList = baseMapper.selectList(wrapper);
		//根据角色id查询角色分配过的哪些菜单
        QueryWrapper<SysRoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",roleId);
		List<SysRoleMenu> roleMenus = sysRoleMenuMapper.selectList(queryWrapper);
		//从第二步查询的列表中，获取角色分配的所有菜单id
		ArrayList<String> list = new ArrayList<>();
		for (SysRoleMenu sysRoleMenu:roleMenus){
			String menuId = sysRoleMenu.getMenuId();
			list.add(menuId);
		}
		//数据处理 isSelect 如果选中true 否则false
		//拿着已经分配的id和所有菜单进行比对 让isSelect进行比对
		for (SysMenu sysMenu:menuList){
			if (list.contains(sysMenu.getId())){
				sysMenu.setSelect(true);
			}else {
				sysMenu.setSelect(false);
			}
		}
		List<SysMenu> menus = MenuHelper.buildTree(menuList);
		//转换成树形结构以最终展示
		return menus;
	}

	//给角色分配菜单
	@Override
	public void doAssign(AssginMenuVo assginMenuVo) {
		//根据角色id删除菜单权限
		QueryWrapper<SysRoleMenu> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("role_id",assginMenuVo.getRoleId());
		sysRoleMenuMapper.delete(queryWrapper);
		//遍历菜单id列，依次进行添加
		List<String> menuIdList = assginMenuVo.getMenuIdList();
		for (String menuId:menuIdList){
			SysRoleMenu sysRoleMenu = new SysRoleMenu();
			sysRoleMenu.setMenuId(menuId);
			sysRoleMenu.setRoleId(assginMenuVo.getRoleId());
			sysRoleMenuMapper.insert(sysRoleMenu);
		}
	}

	//根据用户id查询菜单权限值
	@Override
	public List<RouterVo> getUserMenuList(String userId) {
		//admin是超级管理员，操作所有内容
        List<SysMenu> sysMenuList = null;
		//判断userid值是1代表超级管理员，查询所有数据
		if ("1".equals(userId)){
			QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
			wrapper.eq("status",1);
			wrapper.orderByAsc("sort_value");
			 sysMenuList = baseMapper.selectList(wrapper);
		}
		//userid不是1，其他类型的用户查询这个数据
		else{
		    sysMenuList	= baseMapper.findMenuListUserId(userId);
		}
		//构建成一个树形结构
		List<SysMenu> sysMenuList1 = MenuHelper.buildTree(sysMenuList);
		//转换成前端路由要求格式
		List<RouterVo> routers = RouterHelper.buildRouters(sysMenuList1);
		return routers;
	}

	//根据用户id查询按钮权限值
	@Override
	public List<String> getUserButtonList(String userId) {
         List<SysMenu> sysMenuList = null;
		//判断当前用户是否是超级管理员
		if ("1".equals(userId)){
			sysMenuList = baseMapper.selectList(new QueryWrapper<SysMenu>().eq("status",1));
		}else {
			sysMenuList = baseMapper.findMenuListUserId(userId);
		}
		//sysMenuList遍历
		ArrayList<String> arrayList = new ArrayList<>();
		for (SysMenu sysMenu:sysMenuList){
			if (sysMenu.getType() == 2){
				String perms = sysMenu.getPerms();
				arrayList.add(perms);
			}
		}
		return arrayList;
	}

	//删除菜单
	@Override
	public void removeMenuById(String id) {
		//查询当前删除菜单下是否有子菜单
		//根据id=parentId
		QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
		wrapper.eq("parent_id",id);
		Integer count = baseMapper.selectCount(wrapper);
		if (count>0){//有子菜单
			throw new AuthException(201,"请先删除子菜单");
		}else {
			 baseMapper.deleteById(id);
		}
	}

	//菜单列表（树形）
	@Override
	public List<SysMenu> findNodes() {
		//获取所有菜单
		List<SysMenu> sysMenus = baseMapper.selectList(null);
		//所有菜单遍历转换要求数据格式
		List<SysMenu> resultList =  MenuHelper.buildTree(sysMenus);
		return resultList;
	}
}
