package com.auth.system.controller.system;


import com.auth.model.system.SysUser;
import com.auth.model.vo.LoginVo;
import com.auth.system.exception.AuthException;
import com.auth.system.result.Result;
import com.auth.system.service.SysUserService;
import com.auth.system.utils.JwtHelper;
import com.auth.system.utils.Md5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "用户登录接口")
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {

	@Autowired
	private SysUserService sysUserService;
	//login
	@ApiOperation("登录")
	@PostMapping("login")
	public Result login(@RequestBody LoginVo loginVo){
		//根据用户名称查询数据库
		SysUser sysUser =  sysUserService.getUserInfoByUserName(loginVo.getUsername());
		//如果查询为空
//		System.out.println(loginVo.getPassword());
		if (sysUser == null){
			throw new AuthException(2001,"用户不存在");
		}
		//判断密码是否一致
		String password = loginVo.getPassword();
		String md5Password =  Md5Util.md5(password);
		if (!sysUser.getPassword().equals(md5Password)){
			throw new AuthException(2002,"用户密码错误");
		}
		//判断用户是否可用
		if (sysUser.getStatus().intValue() ==0){
			throw new AuthException(2003,"用户已经被禁用");
		}
		//根据userid和username生成token字符串，通过map返回
		String token = JwtHelper.createToken(sysUser.getId(), sysUser.getUsername());
		Map<String,Object> map = new HashMap<>();
		map.put("token",token);
		return Result.ok(map);
	}

	//info
	@ApiOperation("获取登录信息")
	@GetMapping("info")
	public Result info(HttpServletRequest request) {
		//获取请求头的token字符串
		String token = request.getHeader("token");
		//从token字符串获取用户名称（id）
        String username = JwtHelper.getUsername(token);
		//根据用户名称获取用户信息（基本信息 菜单权限 按钮权限数据）
		Map<String, Object> map = sysUserService.getUserInfo(username);
		return Result.ok(map);
	}
}
