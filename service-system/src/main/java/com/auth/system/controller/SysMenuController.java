package com.auth.system.controller;


import com.auth.model.system.SysMenu;
import com.auth.model.vo.AssginMenuVo;
import com.auth.system.result.Result;
import com.auth.system.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author chizizhixin
 * @since 2022-11-28
 */
@Api(tags = "菜单管理")
@RestController
@RequestMapping("/admin/system/sysMenu")
public class SysMenuController {
	@Autowired
	private SysMenuService sysMenuService;
	//菜单列表
	@ApiOperation("菜单列表")
	@PreAuthorize("hasAuthority('bnt.sysMenu.list')")
	@GetMapping("findNodes")
	public Result findNodes(){
		List<SysMenu> list =  sysMenuService.findNodes();
		return Result.ok(list);
	}

	//添加菜单
	@ApiOperation("添加菜单")
	@PreAuthorize("hasAuthority('bnt.sysMenu.add')")
	@PostMapping("save")
	public  Result save(@RequestBody SysMenu sysMenu){
		sysMenuService.save(sysMenu);
		return Result.ok();
	}

	@ApiOperation("根据id查询菜单")
	@PreAuthorize("hasAuthority('bnt.sysMenu.list')")
	@GetMapping("findNode/{id}")
	public Result findNode(@PathVariable String id){
		SysMenu sysMenu = sysMenuService.getById(id);
		return Result.ok(sysMenu);
	}

	@ApiOperation("修改菜单")
	@PreAuthorize("hasAuthority('bnt.sysMenu.update')")
	@PutMapping("update")
	public Result update(@RequestBody SysMenu sysMenu){
		boolean byId = sysMenuService.updateById(sysMenu);
		if (byId) {
			return Result.ok();
		}else {
			return Result.fail();
		}
	}

	@ApiOperation("删除菜单")
	@PreAuthorize("hasAuthority('bnt.sysMenu.remove')")
	@DeleteMapping("remove/{id}")
	public Result remove(@PathVariable String id){
		sysMenuService.removeMenuById(id);
		return Result.ok();
	}

	@ApiOperation("根据角色分配菜单")
	@GetMapping("/toAssign/{roleId}")
	public Result toAssign(@PathVariable String roleId){
		List<SysMenu> menuList = sysMenuService.findMenuByRoleId(roleId);
		return  Result.ok(menuList);
	}

	@ApiOperation("给角色分配菜单")
	@PreAuthorize("hasAuthority('bnt.sysRole.assignAuth')")
	@PostMapping("/doAssign")
	public Result doAssign(@RequestBody AssginMenuVo assginMenuVo){
		sysMenuService.doAssign(assginMenuVo);
		return Result.ok();
	}


}

