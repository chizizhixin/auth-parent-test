package com.auth.system.controller.system;

import com.auth.model.system.SysOperLog;
import com.auth.model.vo.SysOperLogQueryVo;
import com.auth.system.result.Result;
import com.auth.system.service.OperLogService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.*;

@Api(value = "SysOperLog管理", tags = "SysOperLog管理")
@RestController
@RequestMapping(value="/admin/system/sysOperLog")
public class SysOperLogController {


	@Autowired
	private OperLogService operLogService;
	@ApiOperation(value = "获取分页列表")
	@GetMapping("{page}/{limit}")
	public Result index(
			@ApiParam(name = "page", value = "当前页码", required = true)
			@PathVariable Long page,

			@ApiParam(name = "limit", value = "每页记录数", required = true)
			@PathVariable Long limit,

			@ApiParam(name = "sysOperLogVo", value = "查询对象", required = false)
					SysOperLogQueryVo sysOperLogQueryVo) {
		IPage<SysOperLog> pageModel = operLogService.selectPage(page,limit, sysOperLogQueryVo);
		return Result.ok(pageModel);
	}

	@ApiOperation(value = "删除")
	@DeleteMapping("remove/{id}")
	public Result delete(@PathVariable String id){
		boolean remove = operLogService.removeById(id);
		if (remove){
			return Result.ok();
		}else {
			return Result.fail();
		}}

}
