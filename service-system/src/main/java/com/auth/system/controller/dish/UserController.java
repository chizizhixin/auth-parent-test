package com.auth.system.controller.dish;


import com.auth.model.shop.User;
import com.auth.model.vo.UserVo;
import com.auth.system.result.Result;
import com.auth.system.service.UserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

/**
 * <p>
 * 用户信息 前端控制器
 * </p>
 *
 * @author chizizhixin
 * @since 2022-12-06
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/admin/dish/user")
public class UserController {

	@Autowired
	private UserService userService;

	//根据id查询用户
	@ApiOperation("根据id查询用户")
	@GetMapping("findById/{id}")
	public Result findById(@PathVariable String id){
		User byId = userService.getById(id);
		return Result.ok(byId);
	}
	//查询所有用户
	@ApiOperation("查询所有用户信息")
	@GetMapping("findAll")
	public Result findAll(){
		List<User> users = userService.list();
		return Result.ok(users);
	}
	//更新用户的状态
	@ApiOperation("更新用户状态")
	@GetMapping("updateStatus/{id}/{status}")
	public Result updateByStatus(@PathVariable String id,
								 @PathVariable Integer status){
		userService.updateByStatus(id,status);
		return Result.ok();
	}
	//分页查询所有用户
	@ApiOperation("条件分页查询")
	@GetMapping("/{page}/{limit}")
	public Result findPage(@PathVariable Long page,
						   @PathVariable Long limit,
						   UserVo userVo){
		Page<User> userPage = new Page<>(page, limit);
		IPage<User> pageModel = userService.selectPage(userPage,userVo);
		return Result.ok(pageModel);
	}
	//批量删除
	@ApiOperation("批量删除")
	@DeleteMapping("batchRemove")
	public Result batchRemove(@RequestBody List<String> ids){
		boolean b = userService.removeByIds(ids);
		if (b){
			return Result.ok();
		}else {
			return Result.fail();
		}
	}

	@ApiOperation("根据id删除")
	@DeleteMapping("remove/{id}")
	public Result removeById(@PathVariable String id){
		boolean b = userService.removeById(id);
		if (b){
			return Result.ok();
		}else {
			return Result.fail();
		}
	}


}

