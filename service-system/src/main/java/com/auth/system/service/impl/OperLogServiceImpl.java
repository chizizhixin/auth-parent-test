package com.auth.system.service.impl;

import com.auth.model.system.SysLoginLog;
import com.auth.model.system.SysOperLog;
import com.auth.model.system.SysUser;
import com.auth.model.vo.SysOperLogQueryVo;
import com.auth.system.mapper.OperLogMapper;
import com.auth.system.mapper.SysUserMapper;
import com.auth.system.service.OperLogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class OperLogServiceImpl extends ServiceImpl<OperLogMapper, SysOperLog> implements OperLogService {

	@Autowired
	private OperLogMapper operLogMapper;

	@Override
	public void saveSysLog(SysOperLog sysOperLog) {
		operLogMapper.insert(sysOperLog);
	}

	@Override
	public IPage<SysOperLog> selectPage(Long page, Long limit, SysOperLogQueryVo sysOperLogQueryVo) {
		Page<SysOperLog> page1 = new Page<>(page, limit);

		String title = sysOperLogQueryVo.getTitle();
		String operName = sysOperLogQueryVo.getOperName();
		String createTimeBegin = sysOperLogQueryVo.getCreateTimeBegin();
		String createTimeEnd = sysOperLogQueryVo.getCreateTimeEnd();
		QueryWrapper<SysOperLog> wrapper = new QueryWrapper<>();
		if (!StringUtils.isEmpty(title)){
			wrapper.eq("title",title);
		}
		if (!StringUtils.isEmpty(operName)){
			wrapper.ge("oper_name",operName);
		}
		if (!StringUtils.isEmpty(createTimeBegin)){
			wrapper.ge("create_time",createTimeBegin);
		}
		if (!StringUtils.isEmpty(createTimeEnd)){
			wrapper.le("create_time",createTimeEnd);
		}
		IPage<SysOperLog> page2 = operLogMapper.selectPage(page1, wrapper);
		return page2;
	}
}
