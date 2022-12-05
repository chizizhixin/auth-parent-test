package com.auth.system.controller;

import com.auth.model.system.SysRole;
import com.auth.model.vo.AssginRoleVo;
import com.auth.model.vo.SysRoleQueryVo;
import com.auth.system.annotation.Log;
import com.auth.system.enums.BusinessType;
import com.auth.system.exception.AuthException;
import com.auth.system.result.Result;
import com.auth.system.service.SysRoleService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "角色管理")
@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {

	@Autowired
	private SysRoleService sysRoleService;

	//查询所有的记录
	@ApiOperation("查询所有接口")
	@GetMapping("findAll")
	public Result findAllRole(){
		List<SysRole> list = sysRoleService.list();
		return Result.ok(list);
	}

	//逻辑删除接口
	@PreAuthorize("hasAuthority('bnt.sysRole.remove')")
	@ApiOperation("根据Id删除角色信息")
	@DeleteMapping("remove/{id}")
	public Result removeRole(@PathVariable String id){
		boolean removeById = sysRoleService.removeById(id);
		if (removeById){
			return Result.ok();
		}else {
			return Result.fail();
		}
	}

	//条件分页查询
	//page 当前页  limit每页的记录数
	@PreAuthorize("hasAuthority('bnt.sysRole.list')")
	@ApiOperation("条件分页查询")
	@GetMapping("{page}/{limit}")
	public Result findPageQueryRole(@PathVariable Long page,
									@PathVariable Long limit,
									SysRoleQueryVo sysRoleQueryVo){
		//插件对象
		Page<SysRole> pageParam = new Page<>(page,limit);
		//调用service方法
		IPage<SysRole> pageModel =  sysRoleService.selectPage(pageParam,sysRoleQueryVo);
       return Result.ok(pageModel);
	}

	@Log(title = "角色管理",businessType = BusinessType.INSERT)
	@PreAuthorize("hasAuthority('bnt.sysRole.add')")
	@ApiOperation("添加角色")
	@PostMapping("save")
	public Result saveRole(@RequestBody  SysRole sysRole){
		boolean save = sysRoleService.save(sysRole);
		if (save){
			return Result.ok();
		}else {
			return Result.fail();
		}

	}

	@ApiOperation("根据id查询")
	@PreAuthorize("hasAuthority('bnt.sysRole.list')")
	@GetMapping("findRoleById/{id}")
	public Result findRoleById(@PathVariable String id){
		SysRole sysRole = sysRoleService.getById(id);
		return Result.ok(sysRole);
	}

	@PreAuthorize("hasAuthority('bnt.sysRole.update')")
	@ApiOperation("修改接口")
	@PutMapping("update")
	public Result updateRole(@RequestBody SysRole sysRole){
		boolean updateById = sysRoleService.updateById(sysRole);
		if (updateById){
			return Result.ok();
		}else {
			return Result.fail();
		}
	}

	//得到多个id值
	@PreAuthorize("hasAuthority('bnt.sysRole.remove')")
	@ApiOperation("批量删除接口")
	@DeleteMapping("batchRemove")
	public Result batchRemove(@RequestBody List<String> ids){
		boolean b = sysRoleService.removeByIds(ids);
		if (b){
			return Result.ok();
		}else {
			return Result.fail();
		}
	}

	//根据用户获取角色数据
	@ApiOperation("根据用户获取角色数据")
	@GetMapping("/toAssign/{userId}")
	public Result toAssign(@PathVariable String userId){
		Map<String,Object> roleMap =  sysRoleService.getRolesByUserId(userId);
		return Result.ok(roleMap);
	}

	//重新为用户分配角色
	@ApiOperation("重新给用户分配角色")
	@PreAuthorize("hasAuthority('bnt.sysUser.assignRole')")
	@PostMapping("doAssign")
	public Result doAssign(@RequestBody AssginRoleVo assginRoleVo){
        sysRoleService.doAssign(assginRoleVo);
		return Result.ok();
	}




}
