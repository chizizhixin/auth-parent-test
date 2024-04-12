package com.auth.system.service;

import com.auth.model.system.SysOperLog;
import com.auth.model.vo.SysOperLogQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

public interface OperLogService  extends IService<SysOperLog> {

	public void saveSysLog(SysOperLog sysOperLog);

	IPage<SysOperLog> selectPage(Long page, Long limit, SysOperLogQueryVo sysOperLogQueryVo);
}
