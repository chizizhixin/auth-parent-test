package com.auth.system.controller.system;


import com.auth.model.system.SysUser;
import com.auth.model.vo.SysRoleQueryVo;
import com.auth.model.vo.SysUserQueryVo;
import com.auth.system.result.Result;
import com.auth.system.service.SysUserService;
import com.auth.system.utils.Md5Util;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author chizizhixin
 * @since 2022-11-27
 */
@Api(tags = "系统用户管理")
@RestController
@RequestMapping("/admin/system/sysUser")
public class SysUserController {

	@Autowired
	private SysUserService sysUserService;

	//条件分页查询用户
	@ApiOperation("用户列表")
	@PreAuthorize("hasAuthority('bnt.sysUser.list')")
	@GetMapping("/{page}/{limit}")
	public Result list(@PathVariable Long page,
					   @PathVariable Long limit,
					   SysUserQueryVo sysUserQueryVo){
		//创建page对象
		Page<SysUser> pageParam = new Page<>(page,limit);
		IPage<SysUser> pageModel =  sysUserService.selectPage(pageParam,sysUserQueryVo);
		return Result.ok(pageModel);
	}

	//用户添加
	@ApiOperation("添加用户")
	@PreAuthorize("hasAuthority('bnt.sysUser.add')")
	@PostMapping("save")
	public  Result save(@RequestBody SysUser sysUser){
		//密码加密
		String md5 = Md5Util.md5(sysUser.getPassword());
		sysUser.setPassword(md5);
		boolean save = sysUserService.save(sysUser);
		if (save){
			return Result.ok();
		}else{
			return Result.fail();
		}
	}


	//用户修改
	@ApiOperation("根据id查询")
	@PreAuthorize("hasAuthority('bnt.sysUser.list')")
	@GetMapping("getUser/{id}")
	public Result getUser(@PathVariable String id){
		SysUser sysUser = sysUserService.getById(id);
		return Result.ok(sysUser);
	}

	@ApiOperation("修改用户")
	@PreAuthorize("hasAuthority('bnt.sysUser.update')")
	@PutMapping("update")
	public  Result update(@RequestBody SysUser sysUser){
		boolean updateById = sysUserService.updateById(sysUser);
		if (updateById){
			return Result.ok();
		}else{
			return Result.fail();
		}
	}

	//用户删除
	@ApiOperation("删除用户")
	@PreAuthorize("hasAuthority('bnt.sysUser.remove')")
	@DeleteMapping("remove/{id}")
	public Result remove(@PathVariable String id){
		boolean removeById = sysUserService.removeById(id);
		if (removeById){
			return  Result.ok();
		}else {
			return Result.fail();
		}
	}
	//更改用户状态接口
	@ApiOperation("更改用户状态")
	@PreAuthorize("hasAuthority('bnt.sysUser.update')")
	@GetMapping("updateStatus/{id}/{status}")
	public Result updateStatus(@PathVariable String id,
							   @PathVariable Integer status){
		sysUserService.updateStatus(id,status);
		return Result.ok();
	}


}

