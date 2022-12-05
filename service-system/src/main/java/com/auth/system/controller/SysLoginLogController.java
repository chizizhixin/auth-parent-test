package com.auth.system.controller;

import com.auth.model.system.SysLoginLog;
import com.auth.model.vo.SysLoginLogQueryVo;
import com.auth.system.result.Result;
import com.auth.system.service.LoginLogService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(value = "SysLoginLog管理", tags = "SysLoginLog管理")
@RestController
@RequestMapping(value="/admin/system/sysLoginLog")
public class SysLoginLogController {

	@Resource
	private LoginLogService sysLoginLogService;

	@ApiOperation(value = "获取分页列表")
	@GetMapping("{page}/{limit}")
	public Result index(
			@ApiParam(name = "page", value = "当前页码", required = true)
			@PathVariable Long page,

			@ApiParam(name = "limit", value = "每页记录数", required = true)
			@PathVariable Long limit,

			@ApiParam(name = "sysLoginLogVo", value = "查询对象", required = false)
					SysLoginLogQueryVo sysLoginLogQueryVo) {
		IPage<SysLoginLog> pageModel = sysLoginLogService.selectPage(page,limit, sysLoginLogQueryVo);
		return Result.ok(pageModel);
	}

	@ApiOperation(value = "删除")
	@DeleteMapping("remove/{id}")
	public Result delete(@PathVariable String id){
		boolean remove = sysLoginLogService.removeById(id);
		if (remove){
			return Result.ok();
		}else {
			return Result.fail();
	}}
}
