package com.auth.system.service.impl;

import com.auth.model.system.SysLoginLog;
import com.auth.model.system.SysUser;
import com.auth.model.vo.SysLoginLogQueryVo;
import com.auth.system.mapper.SysLoginLogMapper;
import com.auth.system.mapper.SysUserMapper;
import com.auth.system.service.LoginLogService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class LoginLogServiceImpl extends ServiceImpl<SysLoginLogMapper, SysLoginLog> implements LoginLogService {

	@Autowired
	private SysLoginLogMapper sysLoginLogMapper;

	@Override
	public IPage<SysLoginLog> selectPage(Long page, Long limit, SysLoginLogQueryVo sysLoginLogQueryVo) {
		Page<SysLoginLog> page1 = new Page(page, limit);
		String username = sysLoginLogQueryVo.getUsername();
		String createTimeBegin = sysLoginLogQueryVo.getCreateTimeBegin();
		String end = sysLoginLogQueryVo.getCreateTimeEnd();
		QueryWrapper<SysLoginLog> wrapper = new QueryWrapper<>();
		if (!StringUtils.isEmpty(username)){
			wrapper.like("username",username);
		}
		if (!StringUtils.isEmpty(createTimeBegin)){
			wrapper.ge("create_time",username);
		}
		if (!StringUtils.isEmpty(end)){
			wrapper.le("create_time",end);
		}
		IPage<SysLoginLog> page2 = sysLoginLogMapper.selectPage(page1, wrapper);
		return page2;
	}

	@Override
	public void recordLoginLog(String username, Integer status, String ipaddr, String message) {
		SysLoginLog log = new SysLoginLog();
		log.setUsername(username);
		log.setStatus(status);
		log.setIpaddr(ipaddr);
		log.setMsg(message);
		sysLoginLogMapper.insert(log);
	}
}
