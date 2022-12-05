package com.auth.system.service;

import com.auth.model.system.SysLoginLog;
import com.auth.model.vo.SysLoginLogQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface LoginLogService extends IService<SysLoginLog> {

	//登录日志
	public void recordLoginLog(String username,Integer status,String ipaddr,String message);


	IPage<SysLoginLog> selectPage(Long page, Long limit, SysLoginLogQueryVo sysLoginLogQueryVo);
}
